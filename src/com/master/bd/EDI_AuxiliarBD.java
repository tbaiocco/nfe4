package com.master.bd;

/**
 * [Johann ALimentos - 10/11-2004] - Conversão de Clientes
 *
 * André Valadas
 */

import java.io.FileReader;
import java.io.LineNumberReader;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.core.importacao.v200.LeitorXML;

import com.master.ed.Cliente_VendedorED;
import com.master.ed.DuplicataED;
import com.master.ed.EDI_AuxiliarED;
import com.master.ed.PessoaED;
import com.master.ed.Preco_ProdutoED;
import com.master.ed.Produto_ClienteED;
import com.master.ed.Rota_VendaED;
import com.master.ed.Tabela_VendaED;
import com.master.ed.WMS_EstoqueED;
import com.master.root.ClienteBean;
import com.master.root.PessoaBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.SeparaEndereco;
import com.master.util.Validacao;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.util.print.TextPrint;
import com.master.util.ManipulaString;
import java.sql.Statement;
import com.master.util.ManipulaArquivo;
import java.io.BufferedReader;
import com.master.root.CidadeBean;
import auth.OracleConnection2;
import java.sql.Connection;
import java.util.StringTokenizer;

public class EDI_AuxiliarBD extends BancoUtil {

    private ExecutaSQL executasql;

    int lidos = 0,
    	incluidos = 0,
        clientes = 0,
        clientes_atualizados = 0,
    	falta_cidade = 0,
    	ja_cadastrado = 0,
        gravou = 0;

    public EDI_AuxiliarBD(ExecutaSQL sql) {
        super(sql, false);
        executasql = sql;
    }

    public EDI_AuxiliarBD() {
        super(false);
        this.inicioTransacao();
        executasql = this.sql;
    }

    //*** PESSOAS CLIENTES
    public void importaPessoa(EDI_AuxiliarED ed) throws Excecoes {

        String NM_Registro = "";
        String sql = null;
        ResultSet resCidade = null;
        ArrayList pessoas = new ArrayList();
        ArrayList pessoasDuplicadas = new ArrayList();
        TextPrint duplicidades = new TextPrint();
        TextPrint status = new TextPrint();
        boolean foundPessoa = false;

        try {
            LineNumberReader line = new LineNumberReader(new FileReader(ed.getNM_Arquivo()));//  NOME ////temp//arquivo.txt
            int linha = 0;
            int count = 0;
            int oidOBSCliente = 0;
            String nome_cidade = "";
            String cd_estado = "";

            linha = ed.getNR_Linha();
            if (line.ready())
            {
                this.inicioTransacao();
                // System.out.println("EXCLUINDO Observacoes anteriores...");
                this.sql.executarUpdate("DELETE FROM Observacoes_Clientes WHERE USUARIO_STAMP = 'IMPORTACAO'");

                status.writeNextLine("-- Conversao de Clientes -- ["+Data.getHoraHM()+"]");
                // System.out.println(status.getString());

                registros:
                while ((NM_Registro = line.readLine()) != null)
                {
                    count++;
                    linha++;
                    if ((linha%1000) == 0)
                        // System.out.println("LINHA >>>>>>> "+linha+" - "+Data.getHoraHM());
                    foundPessoa = false;
                    line.setLineNumber(linha);

                    if (NM_Registro.length() > 1)
                    {
                        lidos++;
                        //*** OBSERVACOES DOS CLIENTES
                        if ("#".equals(NM_Registro.substring(0, 1).trim()))
                        {
                            if (oidOBSCliente < 1)
                                oidOBSCliente = getAutoIncremento("oid_Observacao_Cliente", "Observacoes_Clientes");
                            else oidOBSCliente++;

                            this.sql.executarUpdate(" INSERT INTO Observacoes_Clientes (" +
                                    " OID_OBSERVACAO_CLIENTE" +
                                    ",OID_CLIENTE" +
                                    ",TX_OBSERVACAO" +
                                    ",DM_MOSTRA_COLETA" +
                                    ",DM_MOSTRA_ENTREGA" +
                                    ",DM_MOSTRA_CONHECIMENTO" +
                                    ",DM_MOSTRA_FATURAMENTO" +
                                    ",DM_MOSTRA_CADASTRO" +
                                    ",DT_STAMP" +
                                    ",DM_STAMP" +
                                    ",USUARIO_STAMP) " +
                                    " VALUES (" +
                                    oidOBSCliente+
                                    ",'"+ed.getOid_Pessoa()+"'" +
                                    ",'"+NM_Registro.substring(1, 73).trim()+"'" +
                                    ",'S','S','S','S','S'" +
                                    ",'"+Data.getDataDMY()+"','I','IMPORTACAO')");
                            continue registros;
                        }
                        //*** Dados da PESSOA
                        String oidPessoa = NM_Registro.substring(0, 14).trim();
                        String cdCliente = oidPessoa;
                        //String cdCliente = NM_Registro.substring(338, 344).trim();
                        /** IGNORAR
                        if ("87226528000161".equals(oidPessoa) || "03868193000195".equals(oidPessoa))
                        {
                            if (!doExiste("Clientes", "CD_Cliente_Palm = '"+cdCliente+"'"))
                            {
                                pessoasDuplicadas.add(oidPessoa);
                                /** GERAR CNPJ NOVO p/ Pessoas iguais **//**
                                oidPessoa = new PessoaBean().getDigitoVerificador("1");
                                /** ADICIONA STATUS DE DUPLICIDADES **//**
                                duplicidades.writeNextLine(" ++CPF/CNPJ: "+NM_Registro.substring(0, 14).trim()+" TO "+oidPessoa+" - COD: "+cdCliente+" - INSC: "+ed.getNM_Inscricao_Estadual()+" - DESATIVADO: "+NM_Registro.substring(394, 404));
                            } else continue registros;
                        } else**/
                        /** VALIDAR CPF/CNPJ **/
                        if (!doValida(oidPessoa) || (oidPessoa.length() == 14 && !Validacao.CNPJ(oidPessoa)) ||
                            (oidPessoa.length() == 11 && !Validacao.CPF(oidPessoa)))
                        {
                            status.writeNextLine(" **INVALIDO CPF/CNPJ - Cliente: "+cdCliente+" linha: " + linha);
                            continue registros;
                        }

                        /** VALIDAR CPF/CNPJ IGUAIS **/
                        if (!pessoas.contains(oidPessoa))
                            pessoas.add(oidPessoa);
                        else {
                            String oidCliente = getTableStringValue("oid_Pessoa", "Clientes", "CD_Cliente_Palm = '"+cdCliente+"'");
                            if (!doValida(oidCliente))
                            {
                                pessoasDuplicadas.add(oidPessoa);
                                /** GERAR CNPJ NOVO p/ Pessoas iguais **/
                                oidPessoa = new PessoaBean().getDigitoVerificador("1");
                                /** ADICIONA STATUS DE DUPLICIDADES **/
                                duplicidades.writeNextLine(" ++CPF/CNPJ: "+NM_Registro.substring(0, 14).trim()+" TO "+oidPessoa+" - COD: "+cdCliente+" - INSC: "+ed.getNM_Inscricao_Estadual()+" - DESATIVADO: "+NM_Registro.substring(394, 404));
                            } else oidPessoa = oidCliente;
                        }

                        //*** Dados da PESSOA
                        ed.setOid_Pessoa(oidPessoa);
                        ed.setNR_CNPJ_CPF(oidPessoa);
                        if (oidPessoa.length() == 11 || oidPessoa.length() == 14)
                        {
                            ed.setNM_INSCRICAO(SeparaEndereco.separaNumero(NM_Registro.substring(14, 28).trim()));
                            ed.setNM_Razao_Social(SeparaEndereco.tiraAspas(NM_Registro.toUpperCase().substring(29, 69).trim()));
                            ed.setNm_Fantasia(SeparaEndereco.tiraAspas(NM_Registro.toUpperCase().substring(127, 157).trim()));

                            ed.setNM_Endereco(NM_Registro.substring(177, 227).trim());
                            ed.setNR_CEP(NM_Registro.substring(228, 236));
                            ed.setNM_Bairro(NM_Registro.substring(237, 257).trim());
                            ed.setNM_Cidade((SeparaEndereco.tiraAspas(NM_Registro.substring(258, 288))).toUpperCase().trim());
                            ed.setCD_Estado(NM_Registro.substring(289, 291).trim().toUpperCase());
                            //*** Telefone
                            ed.setNR_Telefone(NM_Registro.substring(292, 322).trim());

                            nome_cidade = ed.getNM_Cidade().toUpperCase().trim();
                            cd_estado = ed.getCD_Estado().toUpperCase().trim();
                            sql = " SELECT oid_Cidade ";
                            sql +=" FROM CIDADES, REGIOES_ESTADOS, ESTADOS ";
                            sql +=" WHERE nm_cidade like '"+nome_cidade+"%' ";
                            sql +="   AND CIDADES.OID_REGIAO_ESTADO = REGIOES_ESTADOS.OID_REGIAO_ESTADO ";
                            sql +="   AND REGIOES_ESTADOS.OID_ESTADO = ESTADOS.OID_ESTADO ";
                            sql +="   AND ESTADOS.CD_ESTADO = '"+cd_estado+"' ";
                            sql +=" ORDER BY nm_cidade";

                            resCidade = executasql.executarConsulta(sql);
                            if (resCidade.next())
                            {
                                PessoaED edVolta = new PessoaED();
                                edVolta.setOid_Pessoa(ed.getNR_CNPJ_CPF());
                                edVolta.setNM_Razao_Social(ed.getNM_Razao_Social());
                                edVolta.setNM_Fantasia(ed.getNm_Fantasia());
                                edVolta.setNR_CNPJ_CPF(ed.getNR_CNPJ_CPF());
                                edVolta.setNM_Inscricao_Estadual(ed.getNM_INSCRICAO());
                                edVolta.setNM_Endereco(ed.getNM_Endereco());
                                edVolta.setNM_Bairro(ed.getNM_Bairro());
                                edVolta.setOid_Cidade(resCidade.getLong("oid_Cidade"));
                                edVolta.setNR_CEP(ed.getNR_CEP());
                                edVolta.setNR_Telefone(ed.getNR_Telefone());
                                edVolta.setEMail(NM_Registro.substring(413, 463).trim());

                                foundPessoa = this.inserePessoas(edVolta);
                            } else {
                                falta_cidade++;
                                status.writeNextLine("ERRO [(" + falta_cidade + ") Cidade: "+nome_cidade+" ou Estado: "+cd_estado+" invalidos] Cliente: " + ed.getNR_CNPJ_CPF() +" - "+cdCliente+" linha: " + linha);
                            }
                        } else status.writeNextLine("ERRO [CNPJ_CPF invalido] Cliente: " + ed.getNR_CNPJ_CPF() +" - "+cdCliente+" linha: " + linha);
                    } else status.writeNextLine("ERRO [Linha Vazia] linha: " + linha);

                    //*** Da um commit no banco
                    if (count == 500)
                    {
                        this.fimTransacao(true);
                        this.inicioTransacao();
                        executasql = this.sql;
                        count = 0;
                    }

                    //*** Caso nao tenho incluido a Pessoa
                    if (foundPessoa)
                    {
                        //*** Inclui como Clientes
                        ClienteBean edCliente = new ClienteBean();
                        edCliente.setOID_Pessoa(ed.getOid_Pessoa());

                        edCliente.setDM_Tipo_Cobranca("B");
                        edCliente.setDM_Forma_Pagamento(NM_Registro.substring(327, 328).trim());
                        if (doValida(NM_Registro.substring(325, 327)))
                        {
                            int cdCondicao = Integer.parseInt(NM_Registro.substring(325, 327));
                            if (cdCondicao == 98 || cdCondicao == 99)
                            {
                                edCliente.setOid_Condicao_Pagamento(12);//A VISTA
                                edCliente.setDM_Tipo_Cobranca("V");
                                edCliente.setDM_Forma_Pagamento(cdCondicao == 98 ? "5" : "2");
                            } else if (cdCondicao == 30 || cdCondicao == 55)
                                edCliente.setOid_Condicao_Pagamento(1);//5 Dias
                            else if (cdCondicao == 31 || cdCondicao == 57)
                                edCliente.setOid_Condicao_Pagamento(2);//7 Dias
                            else if (cdCondicao == 33 || cdCondicao == 64)
                                edCliente.setOid_Condicao_Pagamento(3);//14 Dias
                            else if (cdCondicao == 35 || cdCondicao == 71)
                                edCliente.setOid_Condicao_Pagamento(4);//21 Dias
                            else if (cdCondicao == 37 || cdCondicao == 78)
                                edCliente.setOid_Condicao_Pagamento(5);//28 Dias
                            else edCliente.setOid_Condicao_Pagamento(12);//A VISTA
                        } else {
                            edCliente.setOid_Condicao_Pagamento(12);//A VISTA
                            edCliente.setDM_Tipo_Cobranca("V");
                            //status.writeNextLine(" #CONDICAO INVAL.: Cliente: "+NM_Registro.substring(338, 344).trim()+" - DESATIVADO: "+NM_Registro.substring(394, 404));
                        }

                        try {
                            edCliente.setDT_Cadastro(new SimpleDateFormat(Data.SHORT_DATE).format(Data.strToDate(NM_Registro.substring(328, 338))));
                        } catch (Exception exception) {
                            edCliente.setDT_Cadastro(Data.getDataDMY());
                        }
                        edCliente.setCD_Cliente_Palm(NM_Registro.substring(338, 344).trim());

                        //*** DESATIVADOS
                        edCliente.setDT_Desativado(JavaUtil.getValueDef(JavaUtil.getValueDif(NM_Registro.substring(394, 404), "  /  /    "), null));
                        //*** SEGMENTO
                        String cdSegmento = NM_Registro.substring(404, 406).trim();
                        int oidSegmento = getTableIntValue("oid_Segmento", "Segmentos", "CD_Segmento='"+cdSegmento+"'");
                        if (doValida(cdSegmento) && oidSegmento > 0)
                            edCliente.setOID_Segmento(oidSegmento);
                        else edCliente.setOID_Segmento(12); // OUTROS
                        //*** CLIENTE PAGADOR
                        String cdClientePag = NM_Registro.substring(406, 412).trim();
                        String oidClientePag = getTableStringValue("oid_Pessoa", "Clientes", "CD_Cliente_Palm = '"+cdClientePag+"'");
                        if (doValida(cdClientePag) && doValida(oidClientePag))
                            edCliente.setOID_Cliente_Pagador(oidClientePag);
                        else edCliente.setOID_Cliente_Pagador(NM_Registro.substring(0, 14).trim());

                        edCliente.setDM_Isencao_ICMS("S".equals(NM_Registro.substring(412,413).trim()) ? "R" : "N");
                        edCliente.setDM_Credito("".equals(NM_Registro.substring(463,464).trim()) ? "N" : "S");
                        edCliente.setDM_Tipo_Faturamento("S".equals(NM_Registro.substring(464,465).trim()) ? "2" : "1");
                        edCliente.setDM_Resp_Vendedor("S".equals(NM_Registro.substring(465,466).trim()) ? "S" : "N");
                        edCliente.setNR_Dias_Protesto(3);

                        this.insereClientes(edCliente);
                    }
                }
                // System.out.println("LINHA FINAL >>>>>>> "+linha+" - "+Data.getHoraHM());
                line.close();
                this.fimTransacao(true);
                status.writeNextLine("Total de Arquivos Lidos: " + lidos +
                                    "\nInseridos: " + incluidos +
                                    "\nAtualizados: " + ja_cadastrado +
                                    "\nClientes Novos: " + clientes +
                                    "\nClientes Atualizados: " + clientes_atualizados +
                                    "\nGRAVADOS: " + gravou);
                if (pessoasDuplicadas.size() > 0)
                {
                    status.writeNextLine("--------------------------------------"+
                                        "\nDUPLICIDADES >>>\n");
                    status.write(duplicidades.getString().toString());
                }

                // System.out.println(status.getString());
                status.saveToFile((!"SERVIDOR".equals(Parametro_FixoED.getInstancia().getNM_Base()) ? "C:" : "")+"/IMPORTACAO/status/CLIENTES "+Data.getDataDMY().replaceAll("/","-")+" "+Data.getHoraHM().replaceAll(":","_")+".txt");
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "importaPessoa()");
        }
    }
    private boolean inserePessoas(PessoaED ed) throws Excecoes {

        gravou++;
        if (!doExiste("Pessoas","oid_Pessoa = '"+ed.getOid_Pessoa()+"'"))
        {
            incluidos++;
            return new PessoaBD(this.sql).inclui(ed);
        } else {
            ja_cadastrado++;
            return new PessoaBD(this.sql).altera(ed);
        }
    }
    private void insereClientes(ClienteBean ed) throws Exception {

        if (JavaUtil.doValida(ed.getOID_Pessoa()))
        {
            if (!doExiste("Clientes","oid_Cliente = '"+ed.getOID_Pessoa()+"'"))
            {
                this.fimTransacao(true);
                clientes++;
                new ClienteBean().insertByPessoa(ed.getOID_Pessoa());
                this.inicioTransacao();
                executasql = this.sql;
            } else clientes_atualizados++;

            this.sql.executarUpdate(" UPDATE Clientes SET" +
                                    "      oid_Segmento = "+ed.getOID_Segmento()+
                                    "     ,oid_Cliente_Pagador = '"+ed.getOID_Cliente_Pagador()+"'"+
                                    "     ,oid_Condicao_Pagamento = "+ed.getOid_Condicao_Pagamento()+
                                    "     ,NR_Dias_Vencimento = "+ed.getNR_Dias_Vencimento()+
                                    "     ,DM_Isencao_ICMS = '"+ed.getDM_Isencao_ICMS()+"'"+
                                    "     ,DM_Credito = '"+ed.getDM_Credito()+"'"+
                                    "     ,DM_Tipo_Cobranca = '"+ed.getDM_Tipo_Cobranca()+"'"+
                                    "     ,DM_Tipo_Faturamento = '"+ed.getDM_Tipo_Faturamento()+"'"+
                                    "     ,DM_Forma_Pagamento = '"+ed.getDM_Forma_Pagamento()+"'"+
                                    "     ,DM_Resp_Vendedor = '"+ed.getDM_Resp_Vendedor()+"'"+
                                    "     ,NR_Dias_Protesto = '"+ed.getNR_Dias_Protesto()+"'"+
                                    "     ,DT_Cadastro = "+getSQLDate(ed.getDT_Cadastro())+
                                    "     ,DT_Desativado = "+getSQLDate(ed.getDT_Desativado())+
                                    "     ,CD_Cliente_Palm = '"+ed.getCD_Cliente_Palm()+"'" +
                                    " WHERE oid_Cliente = '"+ed.getOID_Pessoa()+"'");
        }
    }

    //*** TABELAS DE PRECOS
    public void importaTabelaVenda(EDI_AuxiliarED ed) throws Excecoes {

        String NM_Registro = "";
        try {
            LineNumberReader line = new LineNumberReader(new FileReader(ed.getNM_Arquivo()));
            int linha = 0;
            int tabelas = 0, tabelasUpdate = 0, tabelasErase = 0;
            int produtos = 0, produtosUpdate = 0, produtosErase = 0, produtosZero = 0;
            int oid_Preco_Produto = 0, oid_Tabela_Venda = 0;
            String loadTable = "", tipoTable = "";
            boolean salved = false;
            ArrayList listaProdutosErase = new ArrayList();
            TextPrint status = new TextPrint();

            linha = ed.getNR_Linha();
            if (line.ready())
            {
                this.inicioTransacao();
                // System.out.println("-- Conversao de Tabela de Precos -- ["+Data.getHoraHM()+"]");
                status.writeNextLine("-- Conversao de Tabela de Precos -- ["+Data.getHoraHM()+"]");

                Tabela_VendaED edTabela = new Tabela_VendaED();
                while (((NM_Registro = line.readLine()) != null))
                {
                    linha++;
                    if ((linha%1000) == 0)
                        // System.out.println("LINHA >>> "+linha+" - "+Data.getHoraHM());
                    line.setLineNumber(linha);
                    if (NM_Registro.length() > 1)
                    {
                        String nrTabela = NM_Registro.substring(0, 6).trim();
                        lidos++;
                        //*** Carrega Precos Produtos
                        String cdProduto = NM_Registro.substring(6, 11).trim();
                        Preco_ProdutoED edPreco = new Preco_ProdutoED();
                        edPreco.setVL_Desconto_Avista(Double.parseDouble(NM_Registro.substring(21, 34).trim().replaceAll(",",".")));
                        edPreco.setVL_Desconto_7_Dias(Double.parseDouble(NM_Registro.substring(34, 47).trim().replaceAll(",",".")));
                        edPreco.setVL_Produto(Double.parseDouble(NM_Registro.substring(47, 60).trim().replaceAll(",",".")));
                        edPreco.setVL_Acrescimo_21_Dias(Double.parseDouble(NM_Registro.substring(60, 73).trim().replaceAll(",",".")));
                        edPreco.setVL_Acrescimo_28_Dias(Double.parseDouble(NM_Registro.substring(73, 86).trim().replaceAll(",",".")));
                        edPreco.setVL_Acrescimo_30_Dias(Double.parseDouble(NM_Registro.substring(86, 99).trim().replaceAll(",",".")));

                        double precoEspecial = Double.parseDouble(NM_Registro.substring(154, 167).trim().replaceAll(",","."));
                        boolean precoValido = validaPrecos(edPreco);
                        if (!precoValido && precoEspecial <= 0)
                        {
                            status.writeNextLine("#### - Produto: "+cdProduto+" nao possui Preco! Tabela Nº: "+nrTabela);
                            produtosZero++;
                        } else {
                            //*** Tabelas
                            if (!loadTable.equals(nrTabela) || !salved)
                            {
                                loadTable = nrTabela;
                                if (Integer.parseInt(nrTabela) == 741)
                                    tipoTable = "03";
                                else if (Integer.parseInt(nrTabela) == 1946)
                                    tipoTable = "02";
                                else tipoTable = getValueDef(NM_Registro.substring(102, 104).trim(),"12");
                                edTabela.setOid_Tipo_Tabela_Venda(getTableIntValue("oid_Tipo_Tabela_Venda","Tipos_Tabelas_Vendas", "CD_Tipo_Tabela_Venda = '"+tipoTable+"'"));
                                if (edTabela.getOid_Tipo_Tabela_Venda() < 1)
                                {
                                    status.writeNextLine(">>> Tipo de Tabela: "+tipoTable+" nao encontrado!");
                                    tabelasErase++;
                                    salved = false;
                                } else {
                                    edTabela.setOid_Pessoa("87226528000161");
                                    edTabela.setNR_Tabela(Integer.parseInt(nrTabela));
                                    edTabela.setDT_Validade("31/12/2006");
                                    try {
                                        edTabela.setDT_Vigencia(new SimpleDateFormat(Data.SHORT_DATE).format(Data.strToDate(NM_Registro.substring(11, 21).trim())));
                                    } catch (Exception exception) {
                                        edTabela.setDT_Vigencia(Data.getDataDMY());
                                    }
                                    edTabela.setDT_Inclusao(Data.getDataDMY());
                                    //*** Verifica se existe tabela
                                    edTabela.setOid_Tabela_Venda(getTableIntValue("oid_Tabela_Venda","Tabelas_Vendas","NR_Tabela = "+edTabela.getNR_Tabela()));
                                    if (edTabela.getOid_Tabela_Venda() < 1)
                                    {
                                        tabelas++;
                                        if (oid_Tabela_Venda < 1)
                                            oid_Tabela_Venda = getAutoIncremento("oid_Tabela_Venda", "Tabelas_Vendas");
                                        else ++oid_Tabela_Venda;
                                        edTabela.setOid_Tabela_Venda(oid_Tabela_Venda);
                                        edTabela = new Tabela_VendaBD(executasql).inclui(edTabela);
                                    } else {
                                        tabelasUpdate++;
                                        new Tabela_VendaBD(executasql).altera(edTabela);
                                    }
                                    salved = true;
                                }
                            }

                            //*** Precos Produtos
                            if (salved)
                            {
                                edPreco.setOid_Tabela_Venda(edTabela.getOid_Tabela_Venda());
                                if (!precoValido && precoEspecial > 0)
                                {
                                    edPreco.setVL_Desconto_Avista(precoEspecial);
                                    edPreco.setVL_Desconto_7_Dias(precoEspecial);
                                    edPreco.setVL_Produto(precoEspecial);
                                    edPreco.setVL_Acrescimo_21_Dias(precoEspecial);
                                    edPreco.setVL_Acrescimo_28_Dias(precoEspecial);
                                    edPreco.setVL_Acrescimo_30_Dias(precoEspecial);
                                }
                                edPreco.setOID_Produto_Cliente(getTableStringValue("oid_Produto_Cliente","Produtos_Clientes","Produtos_Clientes.oid_Produto = Produtos.oid_Produto AND Produtos.CD_Produto = '"+cdProduto+"'"));
                                if (!doValida(edPreco.getOID_Produto_Cliente()) && !listaProdutosErase.contains(cdProduto))
                                {
                                    status.writeNextLine("ERRO - Produto: "+cdProduto+" nao encontrado!");
                                    listaProdutosErase.add(cdProduto);
                                    produtosErase++;
                                } else {
                                    edPreco.setOid_Pessoa("87226528000161");

                                    //*** Verifica se existe
                                    edPreco.setOID_Preco_Produto(getTableIntValue("oid_Preco_Produto",
                                                                                  "Precos_Produtos",
                                                                                  "oid_Tabela_Venda = "+edPreco.getOid_Tabela_Venda()+
                                                                                  " AND oid_Produto_Cliente = '"+edPreco.getOID_Produto_Cliente()+"'" +
                                                                                  " AND oid_Pessoa = '"+edPreco.getOid_Pessoa()+"'"));
                                    if (edPreco.getOID_Preco_Produto() < 1)
                                    {
                                        produtos++;
                                        if (oid_Preco_Produto < 1)
                                            oid_Preco_Produto = getAutoIncremento("oid_Preco_Produto", "Precos_Produtos");
                                        else ++oid_Preco_Produto;
                                        edPreco.setOID_Preco_Produto(oid_Preco_Produto);
                                        new Preco_ProdutoBD(executasql).inclui(edPreco);
                                    } else {
                                        produtosUpdate++;
                                        new Preco_ProdutoBD(executasql).altera(edPreco);
                                    }
                                }
                            }
                        }
                    }
                }
                // System.out.println("LINHA FINAL >>> "+linha+" - "+Data.getHoraHM());
                line.close();
                this.fimTransacao(true);
                status.writeNextLine("--- RESUMO IMPORTACAO ["+Data.getHoraHM()+"]" +
                                    "\nTotal de Arquivos Lidos: " + lidos +
                                    "\n Tabelas Incluidas: " + tabelas +
                                    "\n Tabelas Alteradas: " + tabelasUpdate +
                                    "\n Tabelas ERROS: " + tabelasErase +
                                    "\n ------------------------------" +
                                    "\n Produtos Incluidos: " + produtos +
                                    "\n Produtos Alterados: " + produtosUpdate +
                                    "\n Produtos SEM PREcO: " + produtosZero +
                                    "\n Produtos ERROS: " + produtosErase);
                // System.out.println(status.getString());
                status.saveToFile((!"SERVIDOR".equals(Parametro_FixoED.getInstancia().getNM_Base()) ? "C:" : "")+"/IMPORTACAO/status/TABELAS_VENDAS "+Data.getDataDMY().replaceAll("/","-")+" "+Data.getHoraHM().replaceAll(":","_")+".txt");
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "importaTabelaVenda()");
        }
    }
    private boolean validaPrecos(Preco_ProdutoED ed) {

        return ed.getVL_Desconto_Avista() > 0 ||
               ed.getVL_Desconto_7_Dias() > 0 ||
               ed.getVL_Produto() > 0 ||
               ed.getVL_Acrescimo_21_Dias() > 0 ||
               ed.getVL_Acrescimo_28_Dias() > 0 ||
               ed.getVL_Acrescimo_30_Dias() > 0;
    }

    //*** PERCENTUAL SOBRE OS PRODUTOS DE VENDA
    public void importaPercProdutos(EDI_AuxiliarED ed) throws Excecoes {

        String NM_Registro = "";
        try {
            LineNumberReader line = new LineNumberReader(new FileReader(ed.getNM_Arquivo()));
            int linha = 0;
            int count = 0;

            linha = ed.getNR_Linha();
            if (line.ready())
            {
                this.inicioTransacao();
                // System.out.println("-- Conversao de Percentual dos Produtos -- ["+Data.getHoraHM()+"]");

                while ((NM_Registro = line.readLine()) != null)
                {
                    count++;
                    linha++;
                    if ((linha%1000) == 0)
                        // System.out.println("LINHA >>> "+linha+" - "+Data.getHoraHM());
                    line.setLineNumber(linha);
                    if (NM_Registro.length() > 1)
                    {
                        lidos++;
                        //*** Carrega Precos Produtos
                        String cdProduto = NM_Registro.substring(1, 6).trim();
                        this.sql.executarUpdate(" UPDATE Produtos_Clientes SET " +
                                                "    PE_Desconto_Avista = "+Double.parseDouble(NM_Registro.substring(6, 19).trim().replaceAll(",","."))+
                                                "   ,PE_Desconto_7_Dias = "+Double.parseDouble(NM_Registro.substring(26, 39).trim().replaceAll(",","."))+
                                                "   ,PE_Acrescimo_21_Dias = "+Double.parseDouble(NM_Registro.substring(46, 59).trim().replaceAll(",","."))+
                                                "   ,PE_Acrescimo_28_Dias = "+Double.parseDouble(NM_Registro.substring(66, 79).trim().replaceAll(",","."))+
                                                "   ,VL_Markup = "+Double.parseDouble(NM_Registro.substring(79, 95).trim().replaceAll(",","."))+
                                                " WHERE Produtos_Clientes.oid_Produto = Produtos.oid_Produto" +
                                                "   AND Produtos.CD_Produto = '"+cdProduto+"'");
                        //*** Da um commit no banco
                        if (count == 500)
                        {
                            this.fimTransacao(true);
                            this.inicioTransacao();
                            count = 0;
                        }
                    }
                }
                line.close();
                this.fimTransacao(true);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "importaPercProdutos()");
        }
    }

    //*** ESTOQUE
    public void importaEstoque(EDI_AuxiliarED ed) throws Excecoes {

        String NM_Registro = "";
        String oid_Pessoa_Distribuidor = "87226528000161";
        TextPrint status = new TextPrint();
        try {
            LineNumberReader line = new LineNumberReader(new FileReader(ed.getNM_Arquivo()));
            int linha = 0;
            int count = 0;

            linha = ed.getNR_Linha();
            if (line.ready())
            {
                this.inicioTransacao();
                // System.out.println("-- Importacao de ESTOQUE dos Produtos -- ["+Data.getHoraHM()+"]");
                status.writeNextLine("-- Importacao de ESTOQUE dos Produtos -- ["+Data.getHoraHM()+"]");
                //*** EXCLUI ESTOQUES
                // System.out.println("Excluindo Estoques...");
                this.sql.executarUpdate("DELETE FROM Estoques_Clientes");
                // System.out.println("Excluindo Movimentos Estoques...");
                this.sql.executarUpdate("DELETE FROM Movimentos_Produtos_Clientes");

                while ((NM_Registro = line.readLine()) != null)
                {
                    count++;
                    linha++;
                    if ((linha%1000) == 0)
                        // System.out.println("LINHA >>> "+linha+" - "+Data.getHoraHM());
                    line.setLineNumber(linha);
                    if (NM_Registro.length() > 1)
                    {
                        lidos++;
                        String cdProduto = NM_Registro.substring(1, 6).trim();
                        double nrQuantidade = Double.parseDouble(NM_Registro.substring(18, 30).trim().replaceAll(",","."));
                        String oidProdutoCliente = getTableStringValue("oid_Produto_Cliente",
                                                                       "Produtos_Clientes",
                                                                       "Produtos_Clientes.oid_Produto = Produtos.oid_Produto" +
                                                                       " AND Produtos_Clientes.oid_Pessoa = '"+oid_Pessoa_Distribuidor+"'"+
                                                                       " AND Produtos.CD_Produto = '"+cdProduto+"'");
                        if (doValida(oidProdutoCliente))
                        {
                            Produto_ClienteED edProduto = new Produto_ClienteED(oidProdutoCliente);
                            edProduto.setVL_Preco_Custo(Double.parseDouble(NM_Registro.substring(6, 18).trim().replaceAll(",",".")));
                            if ("D".equals(NM_Registro.substring(42, 43).trim()))
                                edProduto.setVL_Produto(Double.parseDouble(NM_Registro.substring(43, 55).trim().replaceAll(",",".")));
                            new Produto_ClienteBD(this.sql).atualizaCusto(edProduto);

                            WMS_EstoqueED edEstoque = new WMS_EstoqueED();
                            edEstoque.setOID_Produto_Cliente(oidProdutoCliente);
                            edEstoque.setNR_Quantidade(nrQuantidade);
                            edProduto.setOid_Localizacao(getTableIntValue("oid_Localizacao", "Produtos_Clientes","oid_Produto_Cliente = '"+oidProdutoCliente+"'"));
                            if (edProduto.getOid_Localizacao() > 0)
                                edEstoque.setOID_Localizacao(edProduto.getOid_Localizacao());
                            else edEstoque.setOID_Localizacao(274); //CODIGO: 0100X000
                            edEstoque.setOID_Tipo_Estoque("D".equals(NM_Registro.substring(42, 43).trim()) ? 1 : 5);

                            new WMS_EstoqueBD(this.sql).inclui(edEstoque, false);
                            //*** Pra evitar deadLock
                            try {
                                wait(500);
                            } catch (Exception e) {}

                        } else status.writeNextLine("ERRO - Produto: ["+cdProduto+"] nao encontrado! linha >> "+linha);
                        //*** Da um commit no banco
                        if (count == 500)
                        {
                            this.fimTransacao(true);
                            this.inicioTransacao();
                            count = 0;
                        }
                    }
                }
                // System.out.println("LINHA FINAL >>> "+linha+" - "+Data.getHoraHM());
                line.close();
                this.fimTransacao(true);
                status.writeNextLine("-- RESUMO -- ["+Data.getHoraHM()+"]" +
                                    "\n Total: "+linha);
                // System.out.println(status.getString());
                status.saveToFile((!"SERVIDOR".equals(Parametro_FixoED.getInstancia().getNM_Base()) ? "C:" : "")+"/IMPORTACAO/status/ESTOQUE "+Data.getDataDMY().replaceAll("/","-")+" "+Data.getHoraHM().replaceAll(":","_")+".txt");
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "importaEstoque()");
        }
    }

    //*** DUPLICATAS
    public void importaDuplicatas(EDI_AuxiliarED ed) throws Excecoes {

        String NM_Registro = "";
        String oid_Vendedor_Padrao = "87226528000161";
        int oid_Unidade = 1,//JOHANN
            oid_Tipo_Doc = 14;//DUP
        int erros = 0;
        TextPrint status = new TextPrint();
        ArrayList pessoasErase = new ArrayList();
        ArrayList vendedoresErase = new ArrayList();

        try {
            LineNumberReader line = new LineNumberReader(new FileReader(ed.getNM_Arquivo()));
            int linha = 0;
            int count = 0;
            linha = ed.getNR_Linha();
            if (line.ready())
            {
                this.inicioTransacao();
                status.writeNextLine("-- Importacao de DUPLICATAS dos Clientes -- ["+Data.getHoraHM()+"]");
                this.sql.executarUpdate(" DELETE FROM Duplicatas" +
                                        " WHERE (select count(*) from Origens_Duplicatas where duplicatas.oid_duplicata = Origens_Duplicatas.oid_duplicata) = 0" +
                                        "   AND TX_Observacao = 'IMPORTACAO'");
                // System.out.println(status.getString());
                registros:
                while (((NM_Registro = line.readLine()) != null))
                {
                    count++;
                    linha++;
                    if ((linha%1000) == 0)
                        // System.out.println("LINHA >>> "+linha+" - "+Data.getHoraHM());
                    line.setLineNumber(linha);
                    if (NM_Registro.length() > 1)
                    {
                        lidos++;
                        DuplicataED edDuplicata = new DuplicataED();
                        String oid_Pessoa = NM_Registro.substring(0, 14).trim();
                        if (!doValida(oid_Pessoa) || !doExiste("Pessoas","oid_Pessoa = '"+oid_Pessoa+"'"))
                        {
                            erros++;
                            if (!pessoasErase.contains(oid_Pessoa))
                            {
                                pessoasErase.add(oid_Pessoa);
                                status.writeNextLine("PESSOA: ["+oid_Pessoa+" nao encontrada!]");
                            }
                            continue registros;
                        }
                        edDuplicata.setOid_Pessoa(oid_Pessoa);
                        String cdVendedor = NM_Registro.substring(14, 17).trim();
                        String oid_Vendedor = getTableStringValue("oid_Vendedor", "Vendedores", "CD_Vendedor = '"+cdVendedor+"'");
                        if (!doValida(oid_Vendedor))
                        {
                            if (!vendedoresErase.contains(cdVendedor))
                            {
                                vendedoresErase.add(cdVendedor);
                                status.writeNextLine("VENDEDOR: ["+cdVendedor+" nao encontrado!] Cliente: " + oid_Pessoa);
                            }
                            oid_Vendedor = oid_Vendedor_Padrao;
                        }
                        edDuplicata.setOid_Vendedor(oid_Vendedor);
                        edDuplicata.setOid_Unidade(new Long(oid_Unidade));
                        edDuplicata.setOid_Tipo_Documento(new Integer(oid_Tipo_Doc));
                        edDuplicata.setNr_Parcela(new Integer(getSequenciaDuplicatas(NM_Registro.substring(25, 26).trim())));

                        edDuplicata.setNr_Documento(NM_Registro.substring(17, 27).trim());
                        edDuplicata.setNR_Bancario(NM_Registro.substring(27, 42).trim());

                        edDuplicata.setVl_Duplicata(new Double(NM_Registro.substring(42, 65).trim().replaceAll(",",".")));
                        edDuplicata.setVl_Saldo(new Double(NM_Registro.substring(65, 78).trim().replaceAll(",",".")));
                        if (edDuplicata.getVl_Saldo().doubleValue() <= 0)
                            edDuplicata.setVl_Saldo(edDuplicata.getVl_Duplicata());

                        edDuplicata.setDt_Emissao(NM_Registro.substring(78, 88).trim());
                        edDuplicata.setDt_Vencimento(NM_Registro.substring(88, 98).trim());
                        String cdCarteira = NM_Registro.substring(98, 101).trim();
                        if ("001".equals(cdCarteira))
                            edDuplicata.setOid_Carteira(new Integer(10));//banco brasil
                        else if ("041".equals(cdCarteira))
                            edDuplicata.setOid_Carteira(new Integer(6));//banrisul
                        else if ("901".equals(cdCarteira))
                            edDuplicata.setOid_Carteira(new Integer(2));//carteira johann
                        else if ("999".equals(cdCarteira))
                            edDuplicata.setOid_Carteira(new Integer(8));//incobraveis
                        else if ("902".equals(cdCarteira))
                            edDuplicata.setOid_Carteira(new Integer(12));//recuperaveis
                        else if ("903".equals(cdCarteira))
                            edDuplicata.setOid_Carteira(new Integer(14));//cartorio banrisul
                        else if ("906".equals(cdCarteira))
                            edDuplicata.setOid_Carteira(new Integer(16));//cartorio banco brasil
                        else if ("904".equals(cdCarteira))
                            edDuplicata.setOid_Carteira(new Integer(18));//parcial carteira
                        else if ("905".equals(cdCarteira))
                            edDuplicata.setOid_Carteira(new Integer(20));//parcial banco
                        else edDuplicata.setOid_Carteira(new Integer(2));//carteira johann

                        edDuplicata.setDM_Tipo_Cobranca("I");
                        edDuplicata.setTX_Observacao("IMPORTACAO");

                        new DuplicataBD(this.sql).inclui(edDuplicata);
                        //*** Da um commit no banco
                        if (count == 500)
                        {
                            this.fimTransacao(true);
                            this.inicioTransacao();
                            count = 0;
                        }
                    }
                }
                // System.out.println("LINHA FINAL >>> "+linha+" - "+Data.getHoraHM());
                line.close();
                this.fimTransacao(true);
                status.writeNextLine("-- RESUMO -- ["+Data.getHoraHM()+"]" +
                                    "\n ERRO Pessoas: "+pessoasErase.size()+
                                    "\n ERRO Vendedores: "+vendedoresErase.size()+
                                    "\n Duplicatas nao inclusas: "+erros+
                                    "\n Total Registros Lidos: "+linha);
                // System.out.println(status.getString());
                status.saveToFile((!"SERVIDOR".equals(Parametro_FixoED.getInstancia().getNM_Base()) ? "C:" : "")+"/IMPORTACAO/status/DUPLICATAS "+Data.getDataDMY().replaceAll("/","-")+" "+Data.getHoraHM().replaceAll(":","_")+".txt");
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "importaDuplicatas()");
        }
    }
    private int getSequenciaDuplicatas(String p) {
        int toReturn = 1;
        if (doValida(p))
        {
            return ("A".equals(p) ? 1 : "B".equals(p) ? 2 : "C".equals(p) ? 3 : "D".equals(p) ? 4 : "E".equals(p) ? 5 : "F".equals(p) ? 6 : "G".equals(p) ? 7 :
                "H".equals(p) ? 8 : "I".equals(p) ? 9 : "J".equals(p) ? 10 : "K".equals(p) ? 11 : "L".equals(p) ? 12 : "M".equals(p) ? 13 : "N".equals(p) ? 14 :
                    "O".equals(p) ? 15 : "P".equals(p) ? 16 : "Q".equals(p) ? 17 : "R".equals(p) ? 18 : "S".equals(p) ? 19 : "T".equals(p) ? 20 : "U".equals(p) ? 21 : "V".equals(p) ? 22 :
                        "W".equals(p) ? 23 : "X".equals(p) ? 24 : "Y".equals(p) ? 25 : "Z".equals(p) ? 26 : 1);
        } else return toReturn;
    }

    //*** ROTAS
    public void importaRotas(EDI_AuxiliarED ed) throws Excecoes {

        String NM_Registro = "";
        int erros = 0;
        TextPrint status = new TextPrint();
        ArrayList clientesVendedores = new ArrayList();
        ArrayList pessoasErase = new ArrayList();
        ArrayList vendedoresErase = new ArrayList();

        try {
            LineNumberReader line = new LineNumberReader(new FileReader(ed.getNM_Arquivo()));
            int linha = 0;
            int count = 0;

            linha = ed.getNR_Linha();
            if (line.ready())
            {
                this.inicioTransacao();
                status.writeNextLine("-- Importacao de ROTAS dos Vendedores -- ["+Data.getHoraHM()+"]");
                // System.out.println(status.getString());
                this.sql.executarUpdate("DELETE FROM Rotas_Vendas");
                this.sql.executarUpdate("DELETE FROM Clientes_Vendedores");
                this.fimTransacao(true);
                this.inicioTransacao();

                registros: while (((NM_Registro = line.readLine()) != null))
                {
                    count++;
                    linha++;
                    if ((linha%1000) == 0)
                        // System.out.println("LINHA >>> "+linha+" - "+Data.getHoraHM());
                    line.setLineNumber(linha);
                    if (NM_Registro.length() > 1)
                    {
                        lidos++;
                        Rota_VendaED edRota = new Rota_VendaED();

                        String cdVendedor = NM_Registro.substring(0, 3).trim();
                        String oid_Vendedor = getTableStringValue("oid_Vendedor", "Vendedores", "CD_Vendedor = '"+cdVendedor+"'");
                        if (!doValida(oid_Vendedor))
                        {
                            erros++;
                            if (!vendedoresErase.contains(cdVendedor))
                            {
                                vendedoresErase.add(cdVendedor);
                                status.writeNextLine("VENDEDOR: ["+cdVendedor+" nao encontrado!]");
                            }
                            continue registros;
                        }
                        edRota.setOid_Vendedor(oid_Vendedor);

                        String cdCliente = NM_Registro.substring(3, 9).trim();
                        String oid_Pessoa = getTableStringValue("oid_Pessoa", "Clientes", "CD_Cliente_Palm = '"+cdCliente+"'");
                        if (!doValida(oid_Pessoa))
                        {
                            erros++;
                            if (!pessoasErase.contains(cdCliente))
                            {
                                pessoasErase.add(cdCliente);
                                status.writeNextLine("CLIENTE: ["+cdCliente+" nao encontrado!]");
                            }
                            continue registros;
                        }
                        edRota.setOid_Cliente(oid_Pessoa);
                        edRota.setCD_Rota_Venda(NM_Registro.substring(9, 13).trim());
                        edRota.setDM_Dia(Rota_VendaED.getCD_Dia(NM_Registro.substring(9, 10).trim()));
                        edRota.setNR_Sequencia(Integer.parseInt(NM_Registro.substring(10, 13).trim()));
                        edRota.setDM_Situacao("A");
                        new Rota_VendaBD(this.sql).inclui(edRota);

                        //*** Cliente Vendedor
                        Cliente_VendedorED edCliVen = new Cliente_VendedorED(edRota.getOid_Vendedor());
                        edCliVen.setOid_Cliente(edRota.getOid_Cliente());
                        edCliVen.setDm_Situacao("L");// Liberado,  "B" == bloqueado;
                        if (!clientesVendedores.contains(edRota.getOid_Vendedor()+edRota.getOid_Cliente()))
                        {
                            new Cliente_VendedorBD(this.sql).inclui(edCliVen);
                            clientesVendedores.add(edRota.getOid_Vendedor()+edRota.getOid_Cliente());
                        }

                        //*** Da um commit no banco
                        if (count == 500)
                        {
                            this.fimTransacao(true);
                            this.inicioTransacao();
                            count = 0;
                        }
                    }
                }
                // System.out.println("LINHA FINAL >>> "+linha+" - "+Data.getHoraHM());
                line.close();
                this.fimTransacao(true);
                status.writeNextLine("-- RESUMO -- ["+Data.getHoraHM()+"]" +
                                    "\n ERRO Clientes: "+pessoasErase.size()+
                                    "\n ERRO Vendedores: "+vendedoresErase.size()+
                                    "\n Rotas nao inclusas: "+erros+
                                    "\n Total Registros Lidos: "+linha);
                // System.out.println(status.getString());
                status.saveToFile((!"SERVIDOR".equals(Parametro_FixoED.getInstancia().getNM_Base()) ? "C:" : "")+"/IMPORTACAO/status/ROTAS "+Data.getDataDMY().replaceAll("/","-")+" "+Data.getHoraHM().replaceAll(":","_")+".txt");
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "importaRotas()");
        }
    }

//  *** PESSOAS CLIENTES
    public void importaPessoa_Astral() throws Excecoes {

        String NM_Registro = "";
        String sql = null;
        ResultSet resCidade = null;
        ArrayList pessoas = new ArrayList();
        ArrayList pessoasDuplicadas = new ArrayList();
        TextPrint duplicidades = new TextPrint();
        TextPrint status = new TextPrint();
        boolean foundPessoa = false;

        try {
            LineNumberReader line = new LineNumberReader(new FileReader("/home/teo/pessoas.txt"));//  NOME ////temp//arquivo.txt
            int linha = 0;
            int count = 0;
            int oidOBSCliente = 0;
            String nome_cidade = "";
            String cd_estado = "";

            //linha = ed.getNR_Linha();
            if (line.ready())
            {
                this.inicioTransacao();
//                // System.out.println("EXCLUINDO Observacoes anteriores...");
//                this.sql.executarUpdate("DELETE FROM Observacoes_Clientes WHERE USUARIO_STAMP = 'IMPORTACAO'");

                status.writeNextLine("-- Conversao de Clientes -- ["+Data.getHoraHM()+"]");
                // System.out.println(status.getString());

                registros:
                while ((NM_Registro = line.readLine()) != null)
                {
                    count++;
                    linha++;
                    EDI_AuxiliarED ed = new EDI_AuxiliarED();
                    if ((linha%1000) == 0)
                        // System.out.println("LINHA >>>>>>> "+linha+" - "+Data.getHoraHM());
                    foundPessoa = false;
                    line.setLineNumber(linha);

                    if (NM_Registro.length() > 1)
                    {
                        lidos++;
                        //*** OBSERVACOES DOS CLIENTES

                        //*** Dados da PESSOA
                        String oidPessoa = NM_Registro.substring(0, 14).trim();
                        String cdCliente = oidPessoa;
                        //String cdCliente = NM_Registro.substring(338, 344).trim();
                        /** IGNORAR
                        if ("87226528000161".equals(oidPessoa) || "03868193000195".equals(oidPessoa))
                        {
                            if (!doExiste("Clientes", "CD_Cliente_Palm = '"+cdCliente+"'"))
                            {
                                pessoasDuplicadas.add(oidPessoa);
                                /** GERAR CNPJ NOVO p/ Pessoas iguais **//**
                                oidPessoa = new PessoaBean().getDigitoVerificador("1");
                                /** ADICIONA STATUS DE DUPLICIDADES **//**
                                duplicidades.writeNextLine(" ++CPF/CNPJ: "+NM_Registro.substring(0, 14).trim()+" TO "+oidPessoa+" - COD: "+cdCliente+" - INSC: "+ed.getNM_Inscricao_Estadual()+" - DESATIVADO: "+NM_Registro.substring(394, 404));
                            } else continue registros;
                        } else**/
                        /** VALIDAR CPF/CNPJ **/
//                        if (!doValida(oidPessoa) || (oidPessoa.length() == 14 && !Validacao.CNPJ(oidPessoa)) ||
//                            (oidPessoa.length() == 11 && !Validacao.CPF(oidPessoa)))
//                        {
//                            status.writeNextLine(" **INVALIDO CPF/CNPJ - Cliente: "+cdCliente+" linha: " + linha);
//                            continue registros;
//                        }

//                        /** VALIDAR CPF/CNPJ IGUAIS **/
//                        if (!pessoas.contains(oidPessoa))
//                            pessoas.add(oidPessoa);
//                        else {
//                            String oidCliente = getTableStringValue("oid_Pessoa", "Clientes", "CD_Cliente_Palm = '"+cdCliente+"'");
//                            if (!doValida(oidCliente))
//                            {
//                                pessoasDuplicadas.add(oidPessoa);
//                                /** GERAR CNPJ NOVO p/ Pessoas iguais **/
//                                oidPessoa = new PessoaBean().getDigitoVerificador("1");
//                                /** ADICIONA STATUS DE DUPLICIDADES **/
//                                duplicidades.writeNextLine(" ++CPF/CNPJ: "+NM_Registro.substring(0, 14).trim()+" TO "+oidPessoa+" - COD: "+cdCliente+" - INSC: "+ed.getNM_Inscricao_Estadual()+" - DESATIVADO: "+NM_Registro.substring(394, 404));
//                            } else oidPessoa = oidCliente;
//                        }

                        //*** Dados da PESSOA
                        ed.setOid_Pessoa(oidPessoa);
                        ed.setNR_CNPJ_CPF(oidPessoa);
                        if (oidPessoa.length() == 11 || oidPessoa.length() == 14)
                        {
                            ed.setNM_INSCRICAO(SeparaEndereco.separaNumero(NM_Registro.substring(66, 78).trim()));
                            ed.setNM_Razao_Social(SeparaEndereco.tiraAspas(NM_Registro.toUpperCase().substring(16, 66).trim()));
                            ed.setNm_Fantasia(SeparaEndereco.tiraAspas(NM_Registro.toUpperCase().substring(16, 66).trim()));

                            ed.setNM_Endereco(NM_Registro.substring(198, 254).trim());
                            ed.setNR_CEP("");
                            ed.setNM_Bairro("");
                            ed.setNM_Cidade((SeparaEndereco.tiraAspas(NM_Registro.substring(254, 300))).toUpperCase().trim());
                            ed.setCD_Estado(NM_Registro.substring(301, 303).trim().toUpperCase());
                            //*** Telefone
                            ed.setNR_Telefone(NM_Registro.substring(127, 140).trim());

                            nome_cidade = ed.getNM_Cidade().toUpperCase().trim();
                            cd_estado = ed.getCD_Estado().toUpperCase().trim();
                            sql = " SELECT oid_Cidade ";
                            sql +=" FROM CIDADES, REGIOES_ESTADOS, ESTADOS ";
                            sql +=" WHERE nm_cidade like '"+nome_cidade+"%' ";
                            sql +="   AND CIDADES.OID_REGIAO_ESTADO = REGIOES_ESTADOS.OID_REGIAO_ESTADO ";
                            sql +="   AND REGIOES_ESTADOS.OID_ESTADO = ESTADOS.OID_ESTADO ";
                            sql +="   AND ESTADOS.CD_ESTADO = '"+cd_estado+"' ";
                            sql +=" ORDER BY nm_cidade";

                            resCidade = executasql.executarConsulta(sql);
                            if (resCidade.next())
                            {
                                PessoaED edVolta = new PessoaED();
                                edVolta.setOid_Pessoa(ed.getNR_CNPJ_CPF());
                                edVolta.setNM_Razao_Social(ed.getNM_Razao_Social());
                                edVolta.setNM_Fantasia(ed.getNm_Fantasia());
                                edVolta.setNR_CNPJ_CPF(ed.getNR_CNPJ_CPF());
                                edVolta.setNM_Inscricao_Estadual(ed.getNM_INSCRICAO());
                                edVolta.setNM_Endereco(ed.getNM_Endereco());
                                edVolta.setNM_Bairro(ed.getNM_Bairro());
                                edVolta.setOid_Cidade(resCidade.getLong("oid_Cidade"));
                                edVolta.setNR_CEP(ed.getNR_CEP());
                                edVolta.setNR_Telefone(ed.getNR_Telefone());
                                edVolta.setEMail("");

                                foundPessoa = this.inserePessoas(edVolta);
                            } else {
                                falta_cidade++;
                                status.writeNextLine("ERRO [(" + falta_cidade + ") Cidade: "+nome_cidade+" ou Estado: "+cd_estado+" invalidos] Cliente: " + ed.getNR_CNPJ_CPF() +" - "+cdCliente+" linha: " + linha);
                            }
                        } else status.writeNextLine("ERRO [CNPJ_CPF invalido] Cliente: " + ed.getNR_CNPJ_CPF() +" - "+cdCliente+" linha: " + linha);
                    } else status.writeNextLine("ERRO [Linha Vazia] linha: " + linha);

                    //*** Da um commit no banco
                    if (count == 500)
                    {
                        this.fimTransacao(true);
                        this.inicioTransacao();
                        executasql = this.sql;
                        count = 0;
                    }

                    //*** Caso nao tenho incluido a Pessoa
//                    if (foundPessoa)
//                    {
//                        //*** Inclui como Clientes
//                        ClienteBean edCliente = new ClienteBean();
//                        edCliente.setOID_Pessoa(ed.getOid_Pessoa());
//
//                        edCliente.setDM_Tipo_Cobranca("B");
//                        edCliente.setDM_Forma_Pagamento(NM_Registro.substring(327, 328).trim());
//                        if (doValida(NM_Registro.substring(325, 327)))
//                        {
//                            int cdCondicao = Integer.parseInt(NM_Registro.substring(325, 327));
//                            if (cdCondicao == 98 || cdCondicao == 99)
//                            {
//                                edCliente.setOid_Condicao_Pagamento(12);//A VISTA
//                                edCliente.setDM_Tipo_Cobranca("V");
//                                edCliente.setDM_Forma_Pagamento(cdCondicao == 98 ? "5" : "2");
//                            } else if (cdCondicao == 30 || cdCondicao == 55)
//                                edCliente.setOid_Condicao_Pagamento(1);//5 Dias
//                            else if (cdCondicao == 31 || cdCondicao == 57)
//                                edCliente.setOid_Condicao_Pagamento(2);//7 Dias
//                            else if (cdCondicao == 33 || cdCondicao == 64)
//                                edCliente.setOid_Condicao_Pagamento(3);//14 Dias
//                            else if (cdCondicao == 35 || cdCondicao == 71)
//                                edCliente.setOid_Condicao_Pagamento(4);//21 Dias
//                            else if (cdCondicao == 37 || cdCondicao == 78)
//                                edCliente.setOid_Condicao_Pagamento(5);//28 Dias
//                            else edCliente.setOid_Condicao_Pagamento(12);//A VISTA
//                        } else {
//                            edCliente.setOid_Condicao_Pagamento(12);//A VISTA
//                            edCliente.setDM_Tipo_Cobranca("V");
//                            //status.writeNextLine(" #CONDICAO INVAL.: Cliente: "+NM_Registro.substring(338, 344).trim()+" - DESATIVADO: "+NM_Registro.substring(394, 404));
//                        }
//
//                        try {
//                            edCliente.setDT_Cadastro(new SimpleDateFormat(Data.SHORT_DATE).format(Data.strToDate(NM_Registro.substring(328, 338))));
//                        } catch (Exception exception) {
//                            edCliente.setDT_Cadastro(Data.getDataDMY());
//                        }
//                        edCliente.setCD_Cliente_Palm(NM_Registro.substring(338, 344).trim());
//
//                        //*** DESATIVADOS
//                        edCliente.setDT_Desativado(JavaUtil.getValueDef(JavaUtil.getValueDif(NM_Registro.substring(394, 404), "  /  /    "), null));
//                        //*** SEGMENTO
//                        String cdSegmento = NM_Registro.substring(404, 406).trim();
//                        int oidSegmento = getTableIntValue("oid_Segmento", "Segmentos", "CD_Segmento='"+cdSegmento+"'");
//                        if (doValida(cdSegmento) && oidSegmento > 0)
//                            edCliente.setOID_Segmento(oidSegmento);
//                        else edCliente.setOID_Segmento(12); // OUTROS
//                        //*** CLIENTE PAGADOR
//                        String cdClientePag = NM_Registro.substring(406, 412).trim();
//                        String oidClientePag = getTableStringValue("oid_Pessoa", "Clientes", "CD_Cliente_Palm = '"+cdClientePag+"'");
//                        if (doValida(cdClientePag) && doValida(oidClientePag))
//                            edCliente.setOID_Cliente_Pagador(oidClientePag);
//                        else edCliente.setOID_Cliente_Pagador(NM_Registro.substring(0, 14).trim());
//
//                        edCliente.setDM_Isencao_ICMS("S".equals(NM_Registro.substring(412,413).trim()) ? "R" : "N");
//                        edCliente.setDM_Credito("".equals(NM_Registro.substring(463,464).trim()) ? "N" : "S");
//                        edCliente.setDM_Tipo_Faturamento("S".equals(NM_Registro.substring(464,465).trim()) ? "2" : "1");
//                        edCliente.setDM_Resp_Vendedor("S".equals(NM_Registro.substring(465,466).trim()) ? "S" : "N");
//                        edCliente.setNR_Dias_Protesto(3);
//
//                        this.insereClientes(edCliente);
//                    }
                }
                // System.out.println("LINHA FINAL >>>>>>> "+linha+" - "+Data.getHoraHM());
                line.close();
                this.fimTransacao(true);
                status.writeNextLine("Total de Arquivos Lidos: " + lidos +
                                    "\nInseridos: " + incluidos +
                                    "\nAtualizados: " + ja_cadastrado +
                                    "\nClientes Novos: " + clientes +
                                    "\nClientes Atualizados: " + clientes_atualizados +
                                    "\nGRAVADOS: " + gravou);
                if (pessoasDuplicadas.size() > 0)
                {
                    status.writeNextLine("--------------------------------------"+
                                        "\nDUPLICIDADES >>>\n");
                    status.write(duplicidades.getString().toString());
                }

                // System.out.println(status.getString());
                status.saveToFile("/home/teo/CLIENTES"+Data.getDataDMY().replaceAll("/","-")+" "+Data.getHoraHM().replaceAll(":","_")+".txt");
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "importaPessoa()");
        }
    }


    public void importa_Pessoa_Busca_Cidade() throws Exception {
      /*
        Connection conn = null;
        long NR_Contador = 0;
        String logar = "";
        try {
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        try {
            ManipulaArquivo man = new ManipulaArquivo(";");
            BufferedReader buff = man.leArquivo("/home/teo/pessoas_daudt.txt");
            logar = "Entrou na importacao";
            LoggerRN.saveStrToFile("/home/teo/log_import_daudt.log", logar);

            StringTokenizer st = null;
            String a = null;
            while ((a = buff.readLine()) != null) {
            	a += ";\".\"";
            	logar = "Linha: " + a;
                LoggerRN.saveStrToFile("/home/teo/log_import_daudt.log", logar);
                st = new StringTokenizer(a, ";");

                NR_Contador = NR_Contador + 1;
                logar = " Linha : " + NR_Contador;
                this.oid = st.nextToken();
                this.oid = ManipulaString.limpaCampo(ManipulaString.tiraAspas(oid));
                logar += " >>> oid: " + this.oid;

                this.NR_CNPJ_CPF =  oid;

                this.NM_Inscricao_Estadual = ManipulaString.limpaCampo(ManipulaString.tiraAspas(st.nextToken()));
                logar += " | NM_Inscricao_Estadual: " + this.NM_Inscricao_Estadual;

                this.NM_Razao_Social = ManipulaString.limpaCaracteresEsp(ManipulaString.tiraAspas(st.nextToken()));
                logar += " | NM_Razao_Social: " + this.NM_Razao_Social;

                String Cidade_Temp = (ManipulaString.tiraAspas(st.nextToken()));
                logar += " | Cidade_Temp: " + Cidade_Temp;

                String Estado_temp = "";
                StringTokenizer st2 = new StringTokenizer(Cidade_Temp,"/");
                if(st2.hasMoreTokens())
                	Cidade_Temp = ManipulaString.limpaCaracteresEsp(ManipulaString.tiraAspas(st2.nextToken()));
            	logar += " | Cidade s/ UF: " + Cidade_Temp;

                if(st2.hasMoreTokens())
                	Estado_temp = ManipulaString.limpaCaracteresEsp(ManipulaString.tiraAspas(st2.nextToken()));
            	logar += " | UF: " + Estado_temp;

                CidadeBean cidade = CidadeBean.getByCidade(Cidade_Temp,Estado_temp);
                this.oid_Cidade = new Integer(cidade.getOID()).intValue();
                logar += " | oid_Cidade Localizado: " + this.oid_Cidade;

                if(st.hasMoreTokens())
                	this.NR_CEP = ManipulaString.limpaCaracteresEsp(ManipulaString.tiraAspas(st.nextToken()));
                logar += " | NR_CEP: " + this.NR_CEP;

                if(st.hasMoreTokens())
                	this.NM_Endereco = ManipulaString.limpaCaracteresEsp(ManipulaString.tiraAspas(st.nextToken()));
                logar += " | NM_Endereco: " + this.NM_Endereco;

                if(st.hasMoreTokens())
                	this.NR_Telefone = ManipulaString.limpaCaracteresEsp(ManipulaString.tiraAspas(st.nextToken()));
                logar += " | NR_Telefone: " + this.NR_Telefone;

                StringBuffer buffSelecao = new StringBuffer();
                buffSelecao.append(" SELECT Pessoas.OID_Pessoa ");
                buffSelecao.append(" FROM Pessoas ");
                buffSelecao.append(" WHERE OID_Pessoa = '");
                buffSelecao.append(oid);
                buffSelecao.append("'");
                Statement stmt = conn.createStatement();
                ResultSet cursor = stmt.executeQuery(buffSelecao.toString());
                boolean DM_Existe_Pessoa = false;
                while (cursor.next()) {
                    DM_Existe_Pessoa = true;
                    logar += "\n Ja existe este CNPJ/CPF... ";
                }
                if (!DM_Existe_Pessoa && this.oid_Cidade > 0) {
                    try {
                    	this.insert();
                    } catch (Exception except){
                    	logar += "\n Nao conseguiu incluir! Mensagem do StackTrace: " + except.getMessage() + " | " + except.getLocalizedMessage();
                    }
                } else {
                	logar += "\n Não conseguiu localizar a cidade... ";
                }
                LoggerRN.saveStrToFile("/home/teo/log_import_daudt.log", logar);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
       */
    }


    public void importa_Pessoa() throws Exception {
/*
        Connection conn = null;
        long NR_Contador = 0;
        try {
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        try {
            ManipulaArquivo man = new ManipulaArquivo(";");
            BufferedReader buff = man.leArquivo("C:\\temp\\pessoa.txt");

            StringTokenizer st = null;
            String a = null;
            while ((a = buff.readLine()) != null) {
                st = new StringTokenizer(a, ";");
                while (st.hasMoreTokens()) {
                    NR_Contador = NR_Contador + 1;
                    this.oid = st.nextToken();
                    this.NR_CNPJ_CPF = oid;
                    this.NM_Razao_Social = st.nextToken();
                    this.NM_Endereco = st.nextToken();
                    this.NM_Bairro = st.nextToken();
                    this.NR_CEP = st.nextToken();
                    this.NM_Inscricao_Estadual = st.nextToken();
                    String oid_Cidade_Temp = st.nextToken();
                    this.oid_Cidade = new Integer(oid_Cidade_Temp.trim()).intValue();
                    StringBuffer buffSelecao = new StringBuffer();
                    buffSelecao.append(" SELECT Pessoas.OID_Pessoa ");
                    buffSelecao.append(" FROM Pessoas ");
                    buffSelecao.append(" WHERE OID_Pessoa = '");
                    buffSelecao.append(oid);
                    buffSelecao.append("'");
                    Statement stmt = conn.createStatement();
                    ResultSet cursor = stmt.executeQuery(buffSelecao.toString());
                    boolean DM_Existe_Pessoa = false;
                    while (cursor.next()) {
                        DM_Existe_Pessoa = true;
                    }
                    if (!DM_Existe_Pessoa && this.oid_Cidade > 0) {
                        this.insert();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
 */
    }

}