package com.master.rn;

import java.io.*;

import com.master.bd.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.ed.EDI_ImportacaoED;

import java.util.ArrayList;

public class EDI_ImportacaoRN
    extends Transacao {

  EDI_ImportacaoBD EDI_ImportacaoBD = null;

  public EDI_ImportacaoRN () {
  }

  public void importaArquivo (EDI_ImportacaoED ed , String acao , String arquivo , String padrao , String pasta) throws Exception {

    // // System.out.println (" importaArquivo RN --");

    try {
        // System.out.println (" rn importa BD importaArquivo arquivo->> " + arquivo);
        // System.out.println (" rn importa BD importaArquivo padrao->> " + padrao);
        // System.out.println (" rn importa BD importaArquivo acao->> " + acao);
        // System.out.println (" rn importa BD importaArquivo pasta->> " + pasta);

      this.inicioTransacao ();
      EDI_ImportacaoBD = new EDI_ImportacaoBD (this.sql);
      EDI_ImportacaoBD.importaArquivo (ed , acao , arquivo , padrao , pasta);
      this.fimTransacao (true);
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("ERRO NO LAY-OUT DO ARQUIVO. VOCE DEVE LIMPAR O DIRETORIO E REFAZER O PROCESSO !!! ");
      excecoes.setMetodo ("importaArquivo");

      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public ArrayList lista_Nota_Fiscal_EDI (EDI_ImportacaoED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new EDI_ImportacaoBD (sql).lista_Nota_Fiscal_EDI (ed);
    this.fimTransacao (true);
    return lista;
  }

  public boolean deleta (EDI_ImportacaoED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    //// // System.out.println("rn");
    boolean deletado = new EDI_ImportacaoBD (sql).deleta (ed);
    this.fimTransacao (true);
    return deletado;
  }

  public boolean deletaTudo (EDI_ImportacaoED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    //// // System.out.println("rn");
    boolean deletado = new EDI_ImportacaoBD (sql).deletaTudo (ed);
    this.fimTransacao (true);
    return deletado;
  }

  public void inclui (EDI_ImportacaoED ed) throws Excecoes {

	    //retorna um arraylist de ED´s
	    this.inicioTransacao ();
	    //// // System.out.println("rn");
	    new EDI_ImportacaoBD (sql).inclui (ed);
	    this.fimTransacao (true);
  }

  public String confirma_Nota_Fiscal (EDI_ImportacaoED ed) throws Excecoes {
    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    //// // System.out.println("rn");
    String OID_Nota_Fiscal = new EDI_ImportacaoBD (sql).confirma_Nota_Fiscal (ed);

    this.fimTransacao (true);
    return OID_Nota_Fiscal;
  }

  public String confirma_Nota_Fiscal_Filtro (EDI_ImportacaoED ed) throws Excecoes {
    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    //// // System.out.println("rn");
    String OID_Nota_Fiscal = new EDI_ImportacaoBD (sql).confirma_Nota_Fiscal_Filtro (ed);
    this.fimTransacao (true);
    return OID_Nota_Fiscal;
  }

  public long gera_Conhecimento (EDI_ImportacaoED ed) throws Excecoes {
    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    //// // System.out.println("rn");
    long QT_Conhecimento = new EDI_ImportacaoBD (sql).gera_Conhecimento (ed);
    this.fimTransacao (true);
    return QT_Conhecimento;
  }

  public EDI_ImportacaoED getByRecord (EDI_ImportacaoED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    EDI_ImportacaoED edi = new EDI_ImportacaoED ();
    //atribui ao ed de retorno
    edi = new EDI_ImportacaoBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edi;
  }

  public boolean getByRecord_Diretorio_EDI (EDI_ImportacaoED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    EDI_ImportacaoED edi = new EDI_ImportacaoED ();
    //atribui ao ed de retorno
    boolean DM_Diretorio_EDI = new EDI_ImportacaoBD (this.sql).getByRecord_Diretorio_EDI (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return DM_Diretorio_EDI;
  }

  public EDI_ImportacaoED getByRecord_Nota_Com_Item (EDI_ImportacaoED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    EDI_ImportacaoED edi = new EDI_ImportacaoED ();
    //atribui ao ed de retorno
    edi = new EDI_ImportacaoBD (this.sql).getByRecord_Nota_Com_Item (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edi;
  }

  public String importaNFeXML (EDI_ImportacaoED ed) throws Excecoes {

	    //ed.getDM_Tipo_EDI:
	    //1 - Compras
	    //2 - Transporte
	  String toReturn = "";
		  try{
			  this.inicioTransacao ();
		     System.out.println ("AUXILIAR rn ->> TIPO: "+ed.getDM_Tipo_EDI());
//		    return new Auxiliar1BD(this.sql).leituraNFE(arquivo);
		     if(JavaUtil.doValida(ed.getDM_Tipo_EDI()) && "1".equals(ed.getDM_Tipo_EDI())){
		    	 toReturn = new EDI_ImportacaoBD(this.sql).importaNFeXML_Compras(ed);
		     } else if(JavaUtil.doValida(ed.getDM_Tipo_EDI()) && "2".equals(ed.getDM_Tipo_EDI())){
		    	 toReturn = new EDI_ImportacaoBD(this.sql).importaNFeXML_Transporte_SaidaWMS(ed);
		     } else if(JavaUtil.doValida(ed.getDM_Tipo_EDI()) && "3".equals(ed.getDM_Tipo_EDI())){
		    	 toReturn = new EDI_ImportacaoBD(this.sql).importaNFeXML_EntradaWMS(ed);
		     }
		     System.out.println("fim RN");
		  } catch(Excecoes e){
			  toReturn += e.getMensagem()+" | ";
			  throw new Excecoes(toReturn);
		  } catch(Exception e){
			  toReturn += e.getMessage()+" | ";
			  throw new Excecoes(toReturn);
		  } finally {
			  this.fimTransacao (true);
		  }
//		  return JavaUtil.doValida(toReturn)?toReturn:"Importado com sucesso!";
		  return toReturn;
	  }
  
	public String importaNFeXML_Propria(EDI_ImportacaoED ed) throws Excecoes {

		// ed.getDM_Tipo_EDI:
		// 1 - Compras
		// 2 - Transporte
		String toReturn = "";
		toReturn += " Log importacao <" + Data.getDataCompleta() + ">"
				+ "\n>Dir=" + ed.getNM_Arquivo() + "\n>PESSOA="
				+ ed.getOID_Pessoa_Entregadora() + "\n>Unidade="
				+ ed.getOID_Unidade();
		toReturn += "\n ------------------------------------------------------------------------------";
		int qt_files = 0, erro = 0, ok = 0;
		try {
			String pasta = ed.getNM_Arquivo();
			System.out.println("AUXILIAR rn ->> Importa notas proprias, pasta:"
					+ pasta);
			File dir = new File(pasta);
			if (dir.exists()) {

				if (dir.isDirectory()) {

					File[] files = dir.listFiles();

					if (files != null) {

						for (int i = 0; i < files.length; i++) {

							// Pega o nome do arquivo
							String arquivo = files[i].getAbsolutePath();
							toReturn += "\nARQUIVO: " + arquivo;
							ed.setNM_Arquivo(arquivo);
							qt_files++;
							// importar (new File (filename) , filename , path +
							// "/recebidos/" , path + "/temp/" , padrao);
							this.inicioTransacao();
							try {
								String ret = new EDI_ImportacaoBD(this.sql)
										.importaNFeXML_Propria(ed);
								if (JavaUtil.doValida(ret)) {
									toReturn += " - Problemas:"
											+ ret
											+ "\n ------------------------------------------------------------------------------";
									erro++;
								} else {
									toReturn += " - OK!\n ------------------------------------------------------------------------------";
									ok++;
									FileUtil.deleteFile(arquivo);
								}

								this.fimTransacao(true);
							} catch (Exception e) {
								toReturn += " - Problemas:"
										+ e.getMessage()
										+ "\n ------------------------------------------------------------------------------";
								erro++;
								this.fimTransacao(true);
							}

						}
					}
				}
			}
			toReturn += "\n -------------------------- TOTAL xmls " + qt_files
					+ " --------------------------------------";
			toReturn += "\nERROS " + erro + " arquivos";
			toReturn += "\nCERTOS " + ok + " arquivos";
			toReturn += "\n ----------------------------- FIM --------------------------------------------";
			FileUtil.deleteFile("/data/tmp/log_" + ed.getOID_Unidade() + ".log");
			FileUtil.saveToFile(
					"/data/tmp/log_" + ed.getOID_Unidade() + ".log", toReturn);
			System.out.println("fim RN");
		} catch (Excecoes e) {
			toReturn += e.getMensagem() + " | ";
			throw new Excecoes(toReturn);
		} catch (Exception e) {
			toReturn += e.getMessage() + " | ";
			throw new Excecoes(toReturn);
		} finally {
			this.fimTransacao(true);
		}
		// return JavaUtil.doValida(toReturn)?toReturn:"Importado com sucesso!";
		return toReturn.replace("\n", "<br>");
	}

  public void ediMuller (String arquivo) throws Excecoes {

	    try {

	      this.inicioTransacao ();

	      EDI_ImportacaoED ed = new EDI_ImportacaoED();
	      EDI_ImportacaoBD = new EDI_ImportacaoBD (this.sql);

	      EDI_ImportacaoBD.importaPadrao (ed, arquivo, "Muller");

	      this.fimTransacao (true);

	    }
	    catch (Exception e) {
	      Excecoes exc = new Excecoes ();
	      exc.setMensagem ("Erro de inclusão");

	      //faz rollback pois deu algum erro
	      this.abortaTransacao ();

	      throw exc;
	    }

	  }

}