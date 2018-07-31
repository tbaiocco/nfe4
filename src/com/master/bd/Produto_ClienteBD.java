package com.master.bd;

/**
 * T�tulo: Produto_ClienteBD Descri��o: Produtos Clientes - BD Data da cria��o:
 * 10/2003 Atualizado em: 02/2004 Empresa: �xitoLog�stica Mastercom Autor:
 * Carlos Eduardo de Holleben
 */

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Produto_ClienteED;
import com.master.ed.Produto_ClienteRelED;
import com.master.ed.Produto_VendedorED;
import com.master.ed.WMS_EstoqueED;
import com.master.ed.WMS_LocalizacaoED;
import com.master.ed.WMS_Rastreamento_ProdutosED;
import com.master.iu.wms005Bean;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;

public class Produto_ClienteBD  {

    private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

    public Produto_ClienteBD(ExecutaSQL sql) {

        this.executasql = sql;
    }

    public Produto_ClienteED inclui(Produto_ClienteED ed) throws Excecoes {

        String sql = null;

        try {

            if (util.doExiste("Produtos_Clientes", "oid_Produto = "+ed.getOID_Produto()+
                    	 					  " AND oid_Pessoa = '"+ed.getOID_Pessoa()+"'"))
                throw new Mensagens("Esse Produto j� esta cadastrado para essa Distribuidora!");

            sql = " INSERT INTO Produtos_Clientes (" +
            	  "		 Oid_Produto_Cliente" +
            	  "		,OID_Produto" +
            	  "		,OID_Pessoa" +
            	  "		,OID_Embalagem" +
            	  "		,oid_Localizacao" +
            	  "		,Nr_Peso" +
            	  "		,Nr_Peso_Liquido" +
            	  "		,Nr_Peso_Bruto" +
            	  "		,Dm_Rotatividade" +
            	  "		,Nm_Referencia" +
            	  "		,DM_Situacao" +
            	  "		,DM_Validade" +
            	  "		,DM_Serie" +
            	  "		,Nr_Altura" +
            	  "		,Nr_Largura" +
            	  "		,Nr_Profundidade" +
            	  "		,Nr_Empilhagem" +
            	  "		,NR_QT_Minimo" +
            	  "		,VL_Preco_Custo" +
                  "     ,PE_Desconto_Avista"+
                  "     ,PE_Desconto_7_Dias"+
                  "     ,PE_Acrescimo_21_Dias"+
                  "     ,PE_Acrescimo_28_Dias"+
            	  "		,VL_Markup" +
            	  "		,PE_Comissao" +
            	  "		,VL_produto" +
            	  "		,DT_Cadastro" +
            	  "		,Oid_Tipo_Pallet)" +
            	  " VALUES (" +
            	  	"'" + ed.getOID_Produto() + ed.getOID_Pessoa() + "'" +
            	  	","+ ed.getOID_Produto() + "" +
            	  	",'" + ed.getOID_Pessoa() + "'" +
            	  	"," + ed.getOID_Embalagem() +
            	  	", " + ed.getOid_Localizacao() +
            	  	"," + ed.getNR_Peso() +
            	  	"," + ed.getNR_Peso_Liquido() +
            	  	"," + ed.getNR_Peso_Bruto() +
            	  	",'" + ed.getDM_Rotatividade() + "'" +
            	  	",'" + ed.getNM_Referencia() + "'" +
            	  	",'"+ ed.getDM_Situacao() + "'" +
            	  	",'" + ed.getDM_Validade() + "'" +
            	  	",'" + ed.getDM_Serie() + "'" +
            	  	","+ed.getNr_Altura() + ",";
            sql += ed.getNr_Largura() + ",";
            sql += ed.getNr_Profundidade() + ",";
            sql += ed.getNr_Empilhagem() + ",";
            sql += ed.getNR_QT_Minimo() + ",";
            sql += ed.getVL_Preco_Custo() + ",";
            sql += ed.getPE_Desconto_Avista() + ",";
            sql += ed.getPE_Desconto_7_Dias() + ",";
            sql += ed.getPE_Acrescimo_21_Dias() + ",";
            sql += ed.getPE_Acrescimo_28_Dias() + ",";
            sql += ed.getVL_Markup() + ",";
            sql += ed.getPE_Comissao() + ",";
            sql += ed.getVL_Produto() + ",";
            sql += "'"+Data.getDataDMY()+ "',";
            sql += ed.getOid_Tipo_Pallet() + ")";
// System.out.println("P C : "+sql);
            executasql.executarUpdate(sql);
            ed.setOID_Produto_Cliente(ed.getOID_Produto() + ed.getOID_Pessoa());

            WMS_EstoqueED edWE = new WMS_EstoqueED();

            edWE.setOID_Produto_Cliente(ed.getOID_Produto_Cliente());
            edWE.setOID_Localizacao(ed.getOid_Localizacao());

            String oid_Tipo_Produto = String.valueOf(ed.getOID_Tipo_Estoque());

            edWE.setOID_Tipo_Estoque(Integer.valueOf(oid_Tipo_Produto).intValue());
            edWE.setNR_Quantidade(ed.getNR_Quantidade());

           	edWE.setTx_Observacao("Saldo Inicial");

           	edWE = new WMS_EstoqueBD(this.executasql).inclui(edWE, true);

        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"inclui()");
        }
        return ed;
    }

    public void altera(Produto_ClienteED ed) throws Excecoes {

        String sql = null;
        ResultSet res = null;
        String DM_Situacao_old = "";
        double PE_Comissao_old = 0;
        int oid_Produto = 0;

        try {

            //*** Armazana os dados antigos pra verificar se houve altera��o para
            // Produto_Vendedor..
            sql = " SELECT oid_Produto, PE_Comissao, DM_Situacao " +
            	  " FROM Produtos_Clientes ";
            sql +=" WHERE oid_produto_cliente = '" + ed.getOID_Produto_Cliente() + "'";

            res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                oid_Produto = res.getInt("oid_Produto");
                DM_Situacao_old = res.getString("DM_Situacao");
                PE_Comissao_old = res.getDouble("PE_Comissao");
            }

            sql =  " UPDATE Produtos_Clientes SET ";
            sql += "    oid_embalagem = " + ed.getOID_Embalagem() + ", ";
            sql += "    oid_Localizacao = " + ed.getOid_Localizacao() + ", ";
            sql += "    nr_peso = " + ed.getNR_Peso() + ", ";
            sql += "    dm_rotatividade = '" + ed.getDM_Rotatividade() + "', ";
            sql += "    nm_referencia = '" + ed.getNM_Referencia() + "', ";
            sql += "    nr_altura = " + ed.getNr_Altura() + ", ";
            sql += "    VL_MARKUP = " + ed.getVL_Markup() + ", ";
            sql += "    PE_Desconto_Avista = "+ed.getPE_Desconto_Avista() + ",";
            sql += "    PE_Desconto_7_Dias = "+ed.getPE_Desconto_7_Dias() + ",";
            sql += "    PE_Acrescimo_21_Dias = "+ed.getPE_Acrescimo_21_Dias() + ",";
            sql += "    PE_Acrescimo_28_Dias = "+ed.getPE_Acrescimo_28_Dias() + ",";
            sql += "    PE_Comissao = " + ed.getPE_Comissao() + ", ";
            sql += "    VL_PRODUTO = " + ed.getVL_Produto() + ", ";
            sql += "    VL_Preco_Custo = " + ed.getVL_Preco_Custo() + ", ";
            sql += "    NR_Peso_Liquido = " + ed.getNR_Peso_Liquido() + ", ";
            sql += "    NR_Peso_Bruto = " + ed.getNR_Peso_Bruto() + ", ";
            sql += "    nr_largura = " + ed.getNr_Largura() + ", ";
            sql += "    nr_profundidade = " + ed.getNr_Profundidade() + ", ";
            sql += "    nr_empilhagem = " + ed.getNr_Empilhagem() + ", ";
            sql += "    dm_serie = '" + ed.getDM_Serie() + "', ";
            sql += "    dm_validade = '" + ed.getDM_Validade() + "', ";
            sql += "    dm_situacao = '" + ed.getDM_Situacao() + "', ";
            sql += "    Oid_Tipo_Pallet = " + ed.getOid_Tipo_Pallet();
            sql += " WHERE oid_produto_cliente = '" + ed.getOID_Produto_Cliente() + "'";
            executasql.executarUpdate(sql);

            //*** INSERE da Tabela Produtos_Vendedor
            if (ed.getPE_Comissao() != PE_Comissao_old || !ed.getDM_Situacao().equals(DM_Situacao_old))
            {
                Produto_VendedorED edProduto = new Produto_VendedorED();
                edProduto.setModulo(Produto_VendedorED.MODULO_PRODUTO);
                if (!ed.getDM_Situacao().equals(DM_Situacao_old) && !"L".equals(ed.getDM_Situacao()))
                    edProduto.setOperacao(Produto_VendedorED.OP_EXCLUIR);
                else if (!ed.getDM_Situacao().equals(DM_Situacao_old) && "L".equals(ed.getDM_Situacao()))
                    edProduto.setOperacao(Produto_VendedorED.OP_INCLUIR);
                else if (ed.getPE_Comissao() != PE_Comissao_old)
                    edProduto.setOperacao(Produto_VendedorED.OP_ALTERAR);
                edProduto.setOid_Pessoa(ed.getOID_Pessoa());
                edProduto.setOid_Produto(oid_Produto);
                edProduto.setPE_Comissao(ed.getPE_Comissao());
                new Produto_VendedorBD(executasql).GravaProdutos(edProduto);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "altera()");
        } finally {
        	util.closeResultset(res);
        }
    }

    public void atualizaCustoBySaldoFinanceiro(Produto_ClienteED ed) throws Excecoes {
        try {
            double nrQtDisponiel = new wms005Bean().getQtdEstoque(ed.getOID_Produto_Cliente());
            double vlPrecoCusto = util.getTableDoubleValue("VL_PRECO_CUSTO", "Produtos_Clientes", "oid_Produto_Cliente = "+util.getSQLString(ed.getOID_Produto_Cliente()));
            double saldoFinancAnterior = Valor.round(nrQtDisponiel*vlPrecoCusto, 2);
            double saldoAtual =  Valor.round(ed.getNR_QT_Atual()*ed.getVL_Preco_Custo(), 2);
            double nrQtTOTAL = Valor.round(nrQtDisponiel + ed.getNR_QT_Atual(), 3);
            double saldoTOTAL = Valor.round(saldoFinancAnterior + saldoAtual, 2);
            if (nrQtTOTAL > 0 && saldoTOTAL > 0)
                ed.setVL_Preco_Custo(Valor.round(saldoTOTAL/nrQtTOTAL,5));
            this.atualizaCusto(ed);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"atualizaCustoBySaldoFinanceiro()");
        }
    }

    public void atualizaCusto(Produto_ClienteED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Produtos_Clientes SET ";
            sql +="     VL_PRECO_CUSTO = "+ed.getVL_Preco_Custo();//Custo M�dio
            if (ed.getVL_Produto() > 0)
                sql +="     ,VL_PRODUTO = "+ed.getVL_Produto();//Custo Ultima Compra
            sql +=" WHERE oid_Produto_Cliente = "+util.getSQLString(ed.getOID_Produto_Cliente());
            executasql.executarUpdate(sql);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "atualizaCusto()");
        }
    }

    public void deleta(Produto_ClienteED ed) throws Excecoes {

        String sql = null;
        try {
            sql = "DELETE FROM produtos_clientes WHERE Oid_Produto_Cliente = ";
            sql += "'" + ed.getOID_Produto_Cliente() + "'";
            executasql.executarUpdate(sql);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta()");
        }
    }

    public Produto_ClienteED getByRecord(Produto_ClienteED ed) throws Excecoes {

        String sql = null;
        ResultSet res = null;
        Produto_ClienteED edVolta = new Produto_ClienteED();

        try {

            sql = " SELECT Produtos_Clientes.NR_Peso_Liquido as NR_PL,   " +
            	  "        Produtos_Clientes.NR_Peso_Bruto   as NR_PB, * " +
                  " FROM Produtos_Clientes" +
                  "     ,Pessoas" +
                  "     ,Embalagens" +
                  "     ,Tipos_Pallet " +
            	  " WHERE Produtos_Clientes.oid_Pessoa = Pessoas.oid_pessoa " +
            	  "   AND Produtos_Clientes.oid_Embalagem = Embalagens.oid_Embalagem " +
            	  "   AND Produtos_Clientes.oid_Tipo_Pallet = Tipos_Pallet.oid_Tipo_Pallet " ;
            	if (util.doValida(ed.getOID_Produto_Cliente())) {
            	  sql += "   AND Produtos_Clientes.oid_Produto_Cliente = '" + ed.getOID_Produto_Cliente() + "'";
            	} else {
            		if (ed.getOID_Produto() > 0 && util.doValida(ed.getOID_Pessoa()) ) {
            			sql += "   AND Produtos_Clientes.oid_produto = " + ed.getOID_Produto() + " " +
            			       "   AND Produtos_Clientes.oid_pessoa = '" + ed.getOID_Pessoa() + "'" ;
            		}
            	}
            res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta = new Produto_ClienteED();
                edVolta.setOID_Produto_Cliente(res.getString("OID_Produto_Cliente"));
                edVolta.setOID_Produto(res.getInt("OID_Produto"));
                edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
                edVolta.setOID_Embalagem(res.getInt("OID_Embalagem"));
                edVolta.setNM_Embalagem(res.getString("NM_Descricao"));
                edVolta.setNR_Peso(res.getDouble("NR_Peso"));
                edVolta.setDM_Rotatividade(res.getString("DM_Rotatividade"));
                edVolta.setNM_Referencia(res.getString("NM_Referencia"));
                edVolta.setDM_Serie(res.getString("DM_Serie"));
                edVolta.setDM_Validade(res.getString("DM_Validade"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                edVolta.setNR_CNPJ_CPF(res.getString("NR_CNPJ_CPF"));
                edVolta.setNM_Razao_Social(res.getString("NM_Razao_Social"));
                edVolta.setNR_QT_Minimo(res.getDouble("NR_QT_MINIMO"));
                edVolta.setVL_Preco_Custo(res.getDouble("VL_PRECO_CUSTO"));
                edVolta.setNR_Peso_Liquido(res.getDouble("NR_Peso_Liquido"));
                edVolta.setNR_Peso_Bruto(res.getDouble("NR_Peso_Bruto"));
                edVolta.setPE_Desconto_Avista(res.getDouble("PE_Desconto_Avista"));
                edVolta.setPE_Desconto_7_Dias(res.getDouble("PE_Desconto_7_Dias"));
                edVolta.setPE_Acrescimo_21_Dias(res.getDouble("PE_Acrescimo_21_Dias"));
                edVolta.setPE_Acrescimo_28_Dias(res.getDouble("PE_Acrescimo_28_Dias"));
                edVolta.setVL_Markup(res.getDouble("VL_MARKUP"));
                edVolta.setPE_Comissao(res.getDouble("PE_Comissao"));
                edVolta.setVL_Produto(res.getDouble("VL_PRODUTO"));
                edVolta.setNr_Altura(res.getDouble("NR_Altura"));
                edVolta.setNr_Largura(res.getDouble("NR_Largura"));
                edVolta.setNr_Profundidade(res.getDouble("NR_Profundidade"));
                edVolta.setNr_Empilhagem(res.getInt("NR_Empilhagem"));
                edVolta.setOid_Tipo_Pallet(res.getInt("Oid_Tipo_Pallet"));
                edVolta.setNM_Tipo_Pallet(res.getString("NM_Descricao"));
                edVolta.setOid_Localizacao(res.getInt("oid_Localizacao"));
                edVolta.setNR_Peso_Liquido(res.getDouble("NR_PL"));
                edVolta.setNR_Peso_Bruto(res.getDouble("NR_PB"));
                edVolta.setDT_Cadastro(new FormataDataBean().getDT_FormataData(res.getString("DT_Cadastro")));
                if (edVolta.getOid_Localizacao() > 0)
                    edVolta.edLocalizacao = new WMS_LocalizacaoBD(executasql).getByOid_Localizacao(new WMS_LocalizacaoED(edVolta.getOid_Localizacao()));

                edVolta.setNR_QT_Atual(new wms005Bean().getQtdEstoque(edVolta.getOID_Produto_Cliente(), String.valueOf(ed.getOID_Tipo_Estoque())));
            }
        } catch (Exception exc) {
        	exc.printStackTrace();
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"getByRecord()");
        }
        return edVolta;
    }

    public ArrayList listaRastreamento(String oid_produto_cliente, String orderby) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        ResultSet res = null;
        ResultSet res2 = null;
        String order_by = "";
        String dt_d = "";
        String dt_m = "";
        String dt_a = "";

        try {
            sql = " SELECT * FROM movimentos_produtos " +
            	  " WHERE oid_produto_cliente = '" + oid_produto_cliente + "'";
            order_by += orderby;

            sql += order_by;

            res2 = this.executasql.executarConsulta(sql);
            while (res2.next())
            {
                WMS_Rastreamento_ProdutosED edVolta = new WMS_Rastreamento_ProdutosED();

                edVolta.setDM_Tipo_Movimento(res2.getString("dm_tipo_movimento"));
                if (edVolta.getDM_Tipo_Movimento().equals("E")) {
                    edVolta.setDM_Tipo_Movimento("ENTRADA");
                } else if (edVolta.getDM_Tipo_Movimento().equals("S")) {
                    edVolta.setDM_Tipo_Movimento("SA�DA");
                } else if (edVolta.getDM_Tipo_Movimento().equals("T")) {
                    edVolta.setDM_Tipo_Movimento("TRANSFER�NCIA");
                }

                edVolta.setOID_Requisicao_Produto(res2.getInt("oid_requisicao_produto"));
                edVolta.setNR_Quantidade_Efetiva(res2.getInt("nr_quantidade_efetiva"));

                sql = "select * from requisicoes_produtos where oid_requisicao_produto = " + edVolta.getOID_Requisicao_Produto();
                res = this.executasql.executarConsulta(sql);
                while (res.next())
                {
                    edVolta.setOID_Nota_Fiscal_Transacao(res.getString("OID_Nota_Fiscal_Transacao"));
                    edVolta.setOID_Deposito(res.getInt("OID_Deposito"));
                    edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
                    edVolta.setOID_Pessoa_Destinatario(res.getString("OID_Pessoa_Destinatario"));
                    edVolta.setOID_Pessoa_Transportador(res.getString("OID_Pessoa_Transportador"));
                    if (res.getString("DT_Requisicao") != null && res.getString("DT_Requisicao").substring(4, 5).equals("-")) {
                        dt_a = res.getString("DT_Requisicao").substring(0, 4);
                        dt_d = res.getString("DT_Requisicao").substring(8, 10);
                        dt_m = res.getString("DT_Requisicao").substring(5, 7);
                        edVolta.setDT_Requisicao(dt_d + "/" + dt_m + "/" + dt_a);
                    } else
                        edVolta.setDT_Requisicao("");
                    edVolta.setHR_Requisicao(res.getString("HR_Requisicao"));
                }

                sql = "select nm_razao_social, nr_cnpj_cpf from pessoas where oid_pessoa = '" + edVolta.getOID_Pessoa() + "'";
                res = this.executasql.executarConsulta(sql);
                while (res.next()) {
                    edVolta.setNR_CNPJ_CPF_Pessoa(res.getString("nr_cnpj_cpf"));
                    edVolta.setNM_Razao_Social_Pessoa(res.getString("nm_razao_social"));
                }

                sql = "select nm_razao_social, nr_cnpj_cpf from pessoas where oid_pessoa = '" + edVolta.getOID_Pessoa_Destinatario() + "'";
                res = this.executasql.executarConsulta(sql);
                while (res.next()) {
                    edVolta.setNR_CNPJ_CPF_Destinatario(res.getString("nr_cnpj_cpf"));
                    edVolta.setNM_Razao_Social_Destinatario(res.getString("nm_razao_social"));
                }

                sql = "select nm_razao_social, nr_cnpj_cpf from pessoas where oid_pessoa = '" + edVolta.getOID_Pessoa_Transportador() + "'";
                res = this.executasql.executarConsulta(sql);
                while (res.next()) {
                    edVolta.setNR_CNPJ_CPF_Transportador(res.getString("nr_cnpj_cpf"));
                    edVolta.setNM_Razao_Social_Transportador(res.getString("nm_razao_social"));
                }

                sql = "select nm_deposito, oid_deposito from depositos where oid_deposito = " + edVolta.getOID_Deposito();
                res = this.executasql.executarConsulta(sql);
                while (res.next()) {
                    edVolta.setNM_Deposito(res.getString("nm_deposito"));
                    edVolta.setOID_Deposito(res.getInt("oid_deposito"));
                }

                sql = "select nr_nota_fiscal from notas_fiscais_transacoes where oid_nota_fiscal = '" + edVolta.getOID_Nota_Fiscal_Transacao() + "'";
                res = this.executasql.executarConsulta(sql);
                while (res.next())
                    edVolta.setNR_Nota_Fiscal_Transacao(res.getString("nr_nota_fiscal"));

                if (edVolta.getNR_Nota_Fiscal_Transacao() == null)
                    edVolta.setNR_Nota_Fiscal_Transacao("");

                list.add(edVolta);
            }

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
					"listaRastreamento()");
        } finally {
            util.closeResultset(res);
            util.closeResultset(res2);
        }
        return list;
    }

    public ArrayList lista(HttpServletRequest request, String orderby) throws Excecoes {

        String sql = null;
        ResultSet res = null;
        ArrayList list = new ArrayList();
        String order_by = " ORDER BY oid_produto_cliente";
        try {

            String FT_OID_Produto_Cliente = request.getParameter("FT_OID_Produto_Cliente");
            String FT_NM_Referencia = request.getParameter("FT_NM_Referencia");
            String FT_DM_Rotatividade = request.getParameter("FT_DM_Rotatividade");
            String FT_OID_Produto = request.getParameter("FT_OID_Produto");
            String FT_OID_Pessoa = request.getParameter("FT_OID_Pessoa");
            String FT_OID_Embalagem = request.getParameter("FT_OID_Embalagem");
            String FT_NR_Peso = request.getParameter("FT_NR_Peso");
            String FT_DM_Serie = request.getParameter("FT_DM_Serie");

            String FT_NR_Altura = request.getParameter("FT_NR_Altura");
            String FT_NR_Largura = request.getParameter("FT_NR_Largura");
            String FT_NR_Profundidade = request.getParameter("FT_NR_Profundidade");
            String FT_NR_Empilhagem = request.getParameter("FT_NR_Empilhagem");

            String FT_OID_Tipo_Pallet = request.getParameter("FT_OID_Tipo_Pallet");

            sql = "select Produtos_Clientes.*, Pessoas.nm_razao_social AS CLIENTE, ";
            sql += " Tipos_Pallet.Nm_Descricao AS NM_Pallet, ";
            sql += " Embalagens.oid_embalagem AS OID_EMBALAGEM, ";
            sql += " Produtos.oid_produto AS OID_PRODUTO, Pessoas.nr_cnpj_cpf AS CNPJ_CPF, ";
            sql += " Produtos.nm_produto AS PRODUTO, Embalagens.nm_tipo AS EMBALAGEM, ";
            sql += " Produtos.cd_produto AS CD_PRODUTO";
            sql += " FROM Produtos_Clientes, Pessoas, Produtos, Embalagens, Tipos_Pallet";
            sql += " WHERE Pessoas.oid_pessoa = Produtos_Clientes.oid_pessoa ";
            sql += " AND Produtos.oid_produto = Produtos_Clientes.oid_produto ";
            sql += " AND Embalagens.oid_embalagem = Produtos_Clientes.oid_embalagem ";
            sql += " AND Tipos_Pallet.oid_Tipo_Pallet = Produtos_Clientes.oid_Tipo_Pallet ";

            if (FT_OID_Produto_Cliente != null && !FT_OID_Produto_Cliente.equals("") && !FT_OID_Produto_Cliente.equals(FT_OID_Produto) && !FT_OID_Produto_Cliente.equals(FT_OID_Pessoa))
                sql += " AND Produtos_Clientes.OID_Produto_Cliente LIKE '" + FT_OID_Produto_Cliente + "%'";

            if (FT_OID_Produto != null && !FT_OID_Produto.equals(""))
                sql += " AND Produtos_Clientes.OID_Produto = " + FT_OID_Produto;

            if (FT_OID_Tipo_Pallet != null && !FT_OID_Tipo_Pallet.equals("0"))
                sql += " AND Produtos_Clientes.OID_Tipo_Pallet = " + FT_OID_Tipo_Pallet;

            if (FT_OID_Pessoa != null && !FT_OID_Pessoa.equals(""))
                sql += " AND Produtos_Clientes.OID_Pessoa LIKE '" + FT_OID_Pessoa + "%'";

            if (FT_OID_Embalagem != null && !FT_OID_Embalagem.equals(""))
                sql += " AND Produtos_Clientes.OID_Embalagem = " + FT_OID_Embalagem;

            if (FT_DM_Rotatividade != null && !FT_DM_Rotatividade.equals("0"))
                sql += " AND Produtos_Clientes.DM_Rotatividade LIKE '" + FT_DM_Rotatividade + "%'";

            if (FT_NR_Peso != null && !FT_NR_Peso.equals(""))
                sql += " AND Produtos_Clientes.NR_Peso = " + FT_NR_Peso;

            if (FT_NM_Referencia != null && !FT_NM_Referencia.equals(""))
                sql += " AND Produtos_Clientes.NM_Referencia LIKE '" + FT_NM_Referencia + "%'";

            if (FT_DM_Serie != null && !FT_DM_Serie.equals("0"))
                sql += " AND Produtos_Clientes.DM_Serie LIKE '" + FT_DM_Serie + "%'";

            if (FT_NR_Altura != null && !FT_NR_Altura.equals(""))
                sql += " AND Produtos_Clientes.NR_Altura = " + FT_NR_Altura;

            if (FT_NR_Largura != null && !FT_NR_Largura.equals(""))
                sql += " AND Produtos_Clientes.NR_Largura = " + FT_NR_Largura;

            if (FT_NR_Profundidade != null && !FT_NR_Profundidade.equals(""))
                sql += " AND Produtos_Clientes.NR_Profundidade = " + FT_NR_Profundidade;

            if (FT_NR_Empilhagem != null && !FT_NR_Empilhagem.equals(""))
                sql += " AND Produtos_Clientes.NR_Empilhagem = " + FT_NR_Empilhagem;

            sql += " AND Produtos_Clientes.oid_Produto_Cliente > 0";

            if (!order_by.equals(orderby))
                sql += orderby;
            else
                sql += order_by;

            res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Produto_ClienteED ed = new Produto_ClienteED();

                ed.setNM_Produto(res.getString("PRODUTO"));
                ed.setNM_Razao_Social(res.getString("CLIENTE"));
                ed.setNM_Embalagem(res.getString("EMBALAGEM"));
                ed.setNr_Altura(res.getDouble("Nr_Altura"));
                ed.setNr_Largura(res.getDouble("Nr_Largura"));
                ed.setNr_Profundidade(res.getDouble("Nr_Profundidade"));
                ed.setNr_Empilhagem(res.getInt("Nr_Empilhagem"));
                ed.setNR_Peso(res.getDouble("NR_Peso"));
                ed.setDM_Rotatividade(res.getString("DM_Rotatividade"));
                ed.setNM_Tipo_Pallet(res.getString("NM_Pallet"));
                ed.setDM_Serie(res.getString("DM_Serie"));
                ed.setDM_Validade(res.getString("DM_Validade"));
                ed.setNM_Referencia(res.getString("NM_Referencia"));
                ed.setOID_Embalagem(res.getInt("OID_EMBALAGEM"));
                ed.setOID_Produto(res.getInt("OID_PRODUTO"));
                ed.setOID_Pessoa(res.getString("CNPJ_CPF"));
                ed.setOID_Produto_Cliente(res.getString("oid_produto_cliente"));
                ed.setCD_Produto(res.getString("CD_PRODUTO"));
                ed.setPE_Desconto_Avista(res.getDouble("PE_Desconto_Avista"));
                ed.setPE_Desconto_7_Dias(res.getDouble("PE_Desconto_7_Dias"));
                ed.setPE_Acrescimo_21_Dias(res.getDouble("PE_Acrescimo_21_Dias"));
                ed.setPE_Acrescimo_28_Dias(res.getDouble("PE_Acrescimo_28_Dias"));

                list.add(ed);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
					"lista()");
        } finally {
        	util.closeResultset(res);
        }
        return list;
    }

    public ArrayList listaProdutoClienteEstoque(Produto_ClienteED ed) throws Excecoes {

        String sql = null;
        ResultSet res = null;
        ArrayList list = new ArrayList();

        try {

            sql = " SELECT * " +
                  " FROM Estoques_Clientes" +
                  "     ,Tipos_Estoques " +
            	  " WHERE Estoques_Clientes.oid_Tipo_Estoque = Tipos_Estoques.oid_Tipo_Estoque "+
            	  "   AND Estoques_Clientes.oid_Produto_Cliente = '" + ed.getOID_Produto_Cliente() + "'";
            sql +=" ORDER BY Tipos_Estoques.oid_Tipo_Estoque";

            res = this.executasql.executarConsulta(sql);
            long oid_Tipo_Estoque = 0;
            double nr_Quantidade = 0;
            double nr_Quantidade_Total = 0;
            String nm_Tipo_Estoque = "";
            //popula
            while (res.next())
            {
                nr_Quantidade_Total += res.getDouble("NR_QUANTIDADE");

                if (oid_Tipo_Estoque != res.getLong("oid_Tipo_Estoque")) {
                    if (nr_Quantidade > 0) {
                        Produto_ClienteED edVolta = new Produto_ClienteED();
                        edVolta.setOID_Produto_Cliente(ed.getOID_Produto_Cliente());
                        edVolta.setOID_Tipo_Estoque(oid_Tipo_Estoque);
                        edVolta.setNM_Estoque(nm_Tipo_Estoque);
                        edVolta.setNR_QT_Atual(nr_Quantidade);
                        list.add(edVolta);
                    }
                    oid_Tipo_Estoque = res.getLong("oid_Tipo_Estoque");
                    nm_Tipo_Estoque = res.getString("NM_Tipo_Estoque");
                    nr_Quantidade = 0;
                }
                nr_Quantidade += res.getDouble("NR_QUANTIDADE");
            }
            if (nr_Quantidade > 0) {
                Produto_ClienteED edVolta = new Produto_ClienteED();
                edVolta.setOID_Produto_Cliente(ed.getOID_Produto_Cliente());
                edVolta.setOID_Tipo_Estoque(oid_Tipo_Estoque);
                edVolta.setNM_Estoque(nm_Tipo_Estoque);
                edVolta.setNR_QT_Atual(nr_Quantidade);
                list.add(edVolta);
            }
            Produto_ClienteED edVolta = new Produto_ClienteED();
            edVolta.setOID_Produto_Cliente("");

            edVolta.setNM_Estoque("Total ->>");
            edVolta.setNR_QT_Atual(nr_Quantidade_Total);
            list.add(edVolta);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
					"listaProdutoClienteEstoque()");
        } finally {
        	util.closeResultset(res);
        }
        return list;
    }

    public ArrayList listaProdutoClienteLocal(Produto_ClienteED ed) throws Excecoes {

        String sql = null;
        ResultSet res = null;
        ArrayList list = new ArrayList();

        try {

            sql = " SELECT * from Estoques_Clientes, Localizacoes, Depositos, Tipos_Estoques " +
            	  " WHERE  Estoques_Clientes.oid_Tipo_Estoque = Tipos_Estoques.oid_Tipo_Estoque " +
            	  " AND    Estoques_Clientes.oid_Localizacao = Localizacoes.oid_Localizacao " +
            	  " AND    Localizacoes.oid_Deposito = Depositos.oid_Deposito " +
            	  " AND    Estoques_Clientes.oid_Produto_Cliente = '" + ed.getOID_Produto_Cliente().trim() + "'" +
            	  " AND    Estoques_Clientes.oid_Tipo_Estoque = " + ed.getOID_Tipo_Estoque();
            sql += " Order by Localizacoes.oid_Deposito, Localizacoes.NM_RUA, Localizacoes.NR_ENDERECO ";

            res = this.executasql.executarConsulta(sql);
            double nr_Quantidade_Total = 0;

            while (res.next())
            {
                //*** Soma QTD total
                nr_Quantidade_Total += res.getDouble("NR_QUANTIDADE");

                Produto_ClienteED edVolta = new Produto_ClienteED();
                edVolta.setNM_Deposito(res.getString("NM_Deposito"));
                edVolta.setNM_Rua(res.getString("NM_Rua"));
                edVolta.setNM_Endereco(res.getString("NR_ENDERECO"));
                edVolta.setNM_Apartamento(res.getString("NR_APARTAMENTO"));
                edVolta.setNR_QT_Atual(res.getDouble("NR_QUANTIDADE"));
                list.add(edVolta);
            }
            Produto_ClienteED edVolta = new Produto_ClienteED();
            edVolta.setNM_Deposito("Total >>>");
            edVolta.setNM_Rua("");
            edVolta.setNM_Endereco("");
            edVolta.setNM_Apartamento("");
            edVolta.setNR_QT_Atual(nr_Quantidade_Total);
            list.add(edVolta);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
					"listaProdutoClienteLocal()");
        } finally {
        	util.closeResultset(res);
        }
        return list;
    }

    //*** RELAT�RIOS
    public void RelProdutos_Cliente(HttpServletResponse response, Produto_ClienteRelED ed) throws Exception {

        String sql = null;
        ResultSet res = null;
        ArrayList lista = new ArrayList();

        try {

            sql = " select distinct " +
            	  "		   ven.oid_vendedor," +
            	  "        pro.cd_produto, " +
            	  "	       pro.nm_produto, " +
            	  " 	   pro_cli.pe_comissao " +
            	  " from produtos pro, " +
            	  "      produtos_clientes pro_cli, " +
            	  "	   	 mix_produtos mix_pro, " +
            	  "	     mix_vendedores mix_ven, " +
            	  "	     vendedores ven " +
            	  " where pro.oid_produto = pro_cli.oid_produto " +
            	  "   and pro.oid_produto = mix_pro.oid_produto " +
            	  "   and mix_pro.oid_mix = mix_ven.oid_mix " +
            	  "   and mix_ven.oid_Vendedor = ven.oid_Vendedor " +
            	  "   and ven.oid_Vendedor = '" + ed.getOid_vendedor() + "'" +
            	  "   and pro_cli.oid_Pessoa = '" + ed.getOid_pessoa() + "'";
            if (ed.getOrderBy().equals(Produto_ClienteRelED.ORDENAR_NOME))
                sql += " order by pro.nm_produto";
            else if (ed.getOrderBy().equals(Produto_ClienteRelED.ORDENAR_CODIGO))
                sql += " order by pro.cd_produto";

            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {

                Produto_ClienteRelED edVolta = new Produto_ClienteRelED();

                edVolta.setOid_vendedor(res.getString("oid_vendedor"));
                edVolta.setCd_produto(res.getString("cd_produto"));
                edVolta.setNm_produto(res.getString("nm_produto"));
                edVolta.setPe_comissao(res.getDouble("pe_comissao"));

                lista.add(edVolta);
            }

            //*** Mix do Cliente/Vendedor
            sql = " select distinct mix.cd_mix " +
            	  " from mix," +
            	  " 	 mix_vendedores mix_ven, " +
            	  " 	 vendedores ven" +
            	  " where mix.oid_mix = mix_ven.oid_mix " +
            	  "   and mix_ven.oid_Vendedor = ven.oid_Vendedor" +
            	  "   and ven.oid_Vendedor = '" + ed.getOid_vendedor() + "'" +
            	  " order by mix.cd_mix";

            res = this.executasql.executarConsulta(sql);
            String Mix = "";
            while (res.next()) {
                if (!Mix.equals(""))
                    Mix = Mix + "/";
                Mix = Mix + res.getString("cd_mix");
            }
            ed.setMix(Mix);


        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
					"RelProdutos_Cliente()");
        } finally {
        	util.closeResultset(res);
        }
    }
}