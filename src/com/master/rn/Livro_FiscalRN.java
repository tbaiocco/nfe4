package com.master.rn;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.ColetaBD;
import com.master.bd.Livro_FiscalBD;
import com.master.ed.ColetaED;
import com.master.ed.Livro_FiscalED;
import com.master.ed.RelatorioED;
import com.master.rl.FiscalRL;
import com.master.rl.JasperRL;
import com.master.util.ArquivoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.bd.Nota_Fiscal_EletronicaBD;
import com.master.ed.ConhecimentoED;
import com.master.bd.ConhecimentoBD;

public class Livro_FiscalRN extends Transacao {

    public Livro_FiscalED inclui(Livro_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Livro_FiscalBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    //*** Inclui Livro Fiscal
    public void geraLivro_Fiscal_Entradas(Livro_FiscalED ed, String DM_Tipo_Livro) throws Excecoes {
        try {
            this.inicioTransacao();
            new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Entradas(ed, DM_Tipo_Livro);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    public void geraLivro_Fiscal_Saidas(Livro_FiscalED ed, String DM_Tipo_Livro) throws Excecoes {
        try {
            this.inicioTransacao();
            new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Saidas(ed, DM_Tipo_Livro);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    public void geraLivro_Fiscal_PDV(Livro_FiscalED ed, String DM_Tipo_Livro) throws Excecoes {
        try {
            this.inicioTransacao();
            new Livro_FiscalBD(this.sql).geraLivro_Fiscal_PDV(ed, DM_Tipo_Livro);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void geraLivro_Fiscal_CTRC(Livro_FiscalED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            if(JavaUtil.doValida(ed.getDT_Inicial()) && JavaUtil.doValida(ed.getDT_Final()))
            	new Livro_FiscalBD(this.sql).regeraLivro_Fiscal_Temp_CTRC(ed.getDT_Inicial(), ed.getDT_Final());
            else
            	new Livro_FiscalBD(this.sql).geraLivro_Fiscal_CTRC(ed);

            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
        catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes();
        }
    }

    public void geraLivro_Fiscal_Entrada_Simplificada(Livro_FiscalED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            if(JavaUtil.doValida(ed.getDT_Inicial()) && JavaUtil.doValida(ed.getDT_Final()))
            	new Livro_FiscalBD(this.sql).regeraLivro_Fiscal_Temp_Entrada_Simplificada(ed.getDT_Inicial(), ed.getDT_Final());
            else
            	new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Entrada_Simplificada(ed);

            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
        catch (Exception e) {
            this.abortaTransacao();
            throw new Excecoes();
        }
    }

    public void altera(Livro_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Livro_FiscalBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Livro_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Livro_FiscalBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public Livro_FiscalED getByRecord(Livro_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Livro_FiscalBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista(Livro_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Livro_FiscalBD(sql).lista(ed); 
        } finally {
            this.fimTransacao(false);
        }
    }

    public void relRegistroFiscal(RelatorioED ed)throws Excecoes {
        try {   
            this.inicioTransacao();
            new FiscalRL(sql).relRegistroFiscal(ed);  

        } finally {
            this.fimTransacao(false);
        }
    }

    public void relRegistroISSQN(RelatorioED ed)throws Excecoes {
        try {
            this.inicioTransacao();
            new FiscalRL(sql).relRegistroISSQN(ed);

        } finally {
            this.fimTransacao(false);
        }
    }
    
    public void relResumoFiscal(RelatorioED ed)throws Excecoes {
        try {
            this.inicioTransacao();
            new FiscalRL(sql).relResumoFiscal(ed);

        } finally {
            this.fimTransacao(false);
        }
    }
    public void relResumoFiscal_Credito_Presumido(Livro_FiscalED ed,HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	ArrayList listFiscalRel = new ArrayList(); // Array para enviar para o relatório
    	try {
            this.inicioTransacao();
            ArrayList listFiscal = new ArrayList();
			Livro_FiscalED EDFiltro = new Livro_FiscalED();
			String FT_DT_Inicial = request.getParameter ("FT_DT_Inicial");
			String FT_DT_Final = request.getParameter ("FT_DT_Final");
			
			// Monta a parte fixa que veio do bean da tela de filtro

			if (JavaUtil.doValida (FT_DT_Inicial)){				
				EDFiltro.setDT_Inicial(FT_DT_Inicial);
			}
			if (JavaUtil.doValida (FT_DT_Final)){				
				EDFiltro.setDT_Final(FT_DT_Final);
			}
            
			listFiscal = new Livro_FiscalBD(this.sql).lista(EDFiltro);
			
			for (int i=0;i<listFiscal.size();i++) { 
				Livro_FiscalED FiscalLst = (Livro_FiscalED)listFiscal.get(i); 
				Livro_FiscalED Relatorio = new Livro_FiscalED();
				
				String Trunca = FiscalLst.getDT_Entrada();
				String Data_Entrada = Trunca.substring(3, Trunca.length());

				
				Relatorio.setDT_Emissao(FiscalLst.getDT_Emissao());				
				Relatorio.setNM_Razao_Social(FiscalLst.getNM_Unidade());
				Relatorio.setDT_Entrada(Data_Entrada);
				Relatorio.setDT_Inicial(EDFiltro.getDT_Inicial());
				Relatorio.setDT_Final(EDFiltro.getDT_Final());
				
				listFiscalRel.add(Relatorio); 	
				
			}
			ed.setLista(listFiscalRel); // Joga a lista de movimentos no ed para enviar pro relatório 
			ed.setResponse(response);
			ed.setNomeRelatorio("Relatorio_Creditos_Presumidos"); // Seta o nome do relatório
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed

        } finally {
            this.fimTransacao(false);
        }
    }

    public void relBonificaoRecebida(RelatorioED ed)throws Excecoes {
        try {
            this.inicioTransacao();
            new FiscalRL(sql).relBonificaoRecebida(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void relBonificaoConcedida(RelatorioED ed)throws Excecoes {
        try {
            this.inicioTransacao();
            new FiscalRL(sql).relBonificaoConcedida(ed);
        } finally {
            this.fimTransacao(false);
        }
    }



    public void relModeloB(RelatorioED ed, String Relatorio)throws Excecoes {
        try {
            this.inicioTransacao();
            new FiscalRL(sql).relModeloB(ed,Relatorio);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void relModeloB_Aberto(RelatorioED ed, String Relatorio)throws Excecoes {
        try {
            this.inicioTransacao();
            new FiscalRL(sql).relModeloB_Aberto(ed,Relatorio);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void validaContribuinte(RelatorioED ed)throws Exception {

        String strLinha = "";
        int nLinha = 0,
            count = 0;

        try {
            this.inicioTransacao();
            if (new File(ed.getNm_arquivo()).exists())
            {
                //*** Carrega arquivo
                LineNumberReader line = new LineNumberReader(new FileReader(ed.getNm_arquivo()));
                if (line.ready())
                {
                    sql.executarUpdate(" UPDATE Pessoas SET " +
                                       "     DM_Sefaz = null" +
                                       " WHERE length(NR_CNPJ_CPF) = 14" +
                                       "   AND DM_Valida_Sefaz = 'S'" +
                                       "   AND DM_Sefaz is not null");

                    ArrayList pessoas = new ArrayList();
                    ArrayList inscPessoas = new ArrayList();

                    ResultSet res = sql.executarConsulta(" SELECT Pessoas.NR_CNPJ_CPF" +
                                                         "    ,Pessoas.NM_Inscricao_Estadual" +
                                                         " FROM Pessoas" +
                                                         "        left join Clientes" +
                                                         "            ON (Pessoas.oid_Pessoa = Clientes.oid_Pessoa AND Clientes.DT_Desativado is null)" +
                                                         "        left join Fornecedores" +
                                                         "            ON (Pessoas.oid_Pessoa = Fornecedores.oid_Pessoa)" +
                                                         " WHERE length(Pessoas.NR_CNPJ_CPF) = 14" +
                                                         "   AND Pessoas.DM_Valida_Sefaz = 'S'" +
                                                         "   AND (Clientes.oid_Pessoa is not null OR Fornecedores.oid_Pessoa is not null)");
                    while (res.next())
                    {
                        if ((res.getRow() % 500)==0)
                        pessoas.add(res.getString("NR_CNPJ_CPF"));
                        inscPessoas.add(res.getString("NM_Inscricao_Estadual")+res.getString("NR_CNPJ_CPF"));
                        // Seta como Clientes ou Fornecedor p/ Validação
                        sql.executarUpdate(" UPDATE Pessoas SET DM_Sefaz = 'X' " +
                                           " WHERE oid_Pessoa = "+JavaUtil.getSQLString(res.getString("NR_CNPJ_CPF")));
                    }
                    //*** COMMIT
                    this.fimTransacao(true);
                    this.inicioTransacao();

                    while ((strLinha = line.readLine()) != null)
                    {
                        line.setLineNumber(++nLinha);
                        if (strLinha.length() > 1)
                        {
                            ArquivoUtil linha = new ArquivoUtil(strLinha, nLinha);
                            String nrInscricao = linha.getString(0,10);
                            String oidPessoa = "";
                            try {
                                oidPessoa = linha.getString(11,25);
                            } catch (Exception e) {
                                oidPessoa = linha.getString(11,22);
                            }
                            String nrInscricaoPessoa = new String(nrInscricao+oidPessoa);
                            //*** VALIDAÇÃO
                            if (pessoas.contains(oidPessoa))
                            {
                                if (!inscPessoas.contains(nrInscricaoPessoa))
                                {
                                    sql.executarUpdate(" UPDATE Pessoas SET " +
                                                       "     DM_Sefaz = 'D'" +
                                                       " WHERE oid_Pessoa = "+JavaUtil.getSQLString(oidPessoa));
                                }
                            }
                        } else // System.err.println("ERRO [Linha Vazia] linha: " + nLinha);

                        count++;
                        if (count == 500)
                        {
                            this.fimTransacao(true);
                            this.inicioTransacao();
                            count = 0;
                        }
                    }
                    sql.executarUpdate(" UPDATE Pessoas SET " +
                                       "     DM_Sefaz = 'N'" +//INSCRIÇÃO NÃO CADASTRADA
                                       " WHERE DM_Sefaz = 'X'" +//Clientes ou Fornecedores
                                       "   AND DM_Valida_Sefaz = 'S'");
                    line.close();
                    this.fimTransacao(true);
                }
            } else throw new Excecoes("Arquivo: "+ed.getNm_arquivo()+" nao existe!");
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void relValidacaoContribuinte(RelatorioED ed)throws Excecoes {
        try {
            this.inicioTransacao();
            new FiscalRL(sql).relValidacaoContribuinte(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void alteraAliquotas(ArrayList aliquotas) throws Exception {

        if (aliquotas.size() < 1)
            return;
        try {
            this.inicioTransacao();
            for (int i=0; i<aliquotas.size(); i++)
            {
                Livro_FiscalED edFiscal = (Livro_FiscalED) aliquotas.get(i);
                this.sql.executarUpdate(" UPDATE Livros_Fiscais SET " +
                                        "     VL_Contabil = "+edFiscal.getVL_Contabil()+
                                        "    ,VL_Base_Calculo = "+edFiscal.getVL_Base_Calculo()+
                                        "    ,PE_Aliquota = "+edFiscal.getPE_Aliquota()+
                                        "    ,VL_Imposto_Creditado = "+edFiscal.getVL_Imposto_Creditado()+
                                        "    ,VL_Imposto = "+edFiscal.getVL_Imposto()+
                                        "    ,VL_Outro = "+edFiscal.getVL_Outro()+
                                        " WHERE oid_Livro_Fiscal = "+edFiscal.getOID_Livro_Fiscal());
            }
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    /**
     * Regis - 06/2006
     * @param ed
     * @throws Excecoes
     * Regera o livro fiscal . Chamado da tela fisF010.jsp
     * Dado os parametros :  data inicial , data final e tipo de livro,
     * busca as notas do periodo e regera os livro fiscal.
     * Para tipo = "Nota de entrada" a busca e feita pela data de entrada nota
     * Para tipo = "Nota de saida" a busca é feita pela data de emissão emissao.
     * Dados entram por RelatorioED : dt_inicial, dt_final, dm_tipo
     * @throws SQLException
     */
    public void regeraLivro_Fiscal(RelatorioED ed) throws Excecoes, SQLException {
        try {
            this.inicioTransacao();
            // Acessar todas as notas do periodo e do tipo
            // pra cada nota montar o ed Livro_Fiscal e mandar gerar o livro
            Nota_Fiscal_EletronicaED edNFt = new Nota_Fiscal_EletronicaED();    //Instancia um ed de NF
            if ( "E".equals(ed.getDm_tipo()) ) {
                edNFt.setDt_entrada(ed.getDt_inicial());                        // Seta data de ENTRADA inicial
                edNFt.setDt_entrada_final(ed.getDt_final());                    // Seta data de ENTRADA final
                edNFt.setDM_Situacao("F"); 										// Somente notas finalizadas

            } else {
                edNFt.setDT_Inicial(ed.getDt_inicial());                        // Seta data de EMISSÃO inicial
                edNFt.setDT_Final(ed.getDt_final());                            // Seta data de EMISSÃO final
                edNFt.setDM_Situacao("1");                                      // Notas C-anceladas, D-evolvidas e F-inalizadas
            }

            edNFt.setDm_tipo_nota_fiscal(ed.getDm_tipo());                      // Seta o tipo de nota informado  - E - S
            ArrayList listaNF = new Nota_Fiscal_EletronicaBD(sql).lista(edNFt); // Busca as notas pelo periodo de entrada informado
            Livro_FiscalED edLivro = new Livro_FiscalED();                      // instancia um ed de Livro
            Livro_FiscalBD LivroBD = new Livro_FiscalBD(this.sql);              // Instancia o Livro para gravar
            //edLivro.setOID_Nota_Fiscal("068803950001401149270552650");
            //LivroBD.geraLivro_Fiscal_Saidas(edLivro, ed.getDm_tipo());        // Gera o Livro de saida
            //LivroBD.geraLivro_Fiscal_Entradas(edLivro, ed.getDm_tipo());		// Gera o Livro de entrada
            //LivroBD.regeraLivro_Fiscal_Temp(ed);// ****Use este para algo especial tipo para fazer a regeracao só para uma natureza ou etc...
            ///**
            for (int i=0; i<listaNF.size(); i++) {
                edNFt = (Nota_Fiscal_EletronicaED)listaNF.get(i);               // Busca a nf do array
                edLivro.setOID_Nota_Fiscal(edNFt.getOid_nota_fiscal());         // Set o oid da NF para gerar o livro
                if ( "E".equals(ed.getDm_tipo()) ) {
                	LivroBD.geraLivro_Fiscal_Entradas(edLivro, ed.getDm_tipo());// Gera o Livro de entrada
                } else {
                	LivroBD.geraLivro_Fiscal_Saidas(edLivro, ed.getDm_tipo());  // Gera o Livro de saida
                }
            }
            //*/
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void corrige() throws Excecoes, SQLException {
        try {
            this.inicioTransacao();
            // Acessar todas as notas do periodo e do tipo
            // pra cada nota montar o ed Livro_Fiscal e mandar gerar o livro
            Nota_Fiscal_EletronicaED edNFt = new Nota_Fiscal_EletronicaED();    //Instancia um ed de NF
            edNFt.setDT_Inicial("01/11/2006");                        // Seta data de EMISSÃO inicial
            edNFt.setDT_Final("10/12/2006");                            // Seta data de EMISSÃO final

            new Nota_Fiscal_EletronicaBD(sql).corrige(edNFt);          // Busca as notas pelo periodo de entrada informado
            //*/
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }


    public byte[] geraLivro_Fiscal_Conhecimento(ConhecimentoED ed) throws Excecoes {

        //antes de invocar chamada ao relatorio deve-se
        //fazer validacoes de regra de negocio

        this.inicioTransacao();
        byte[] b =  new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Conhecimento(ed);
        this.fimTransacao(true);
        return b;
    }

}