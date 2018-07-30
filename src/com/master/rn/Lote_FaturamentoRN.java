package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.ArrayList;

import com.master.bd.EDI_ImportacaoBD;
import com.master.bd.Lote_FaturamentoBD;
import com.master.ed.EDI_ImportacaoED;
import com.master.ed.Lote_FaturamentoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Lote_FaturamentoRN extends Transacao  {
  Lote_FaturamentoBD Lote_FaturamentoBD = null;


  public Lote_FaturamentoRN() {
    //Lote_Faturamentobd = new Lote_FaturamentoBD(this.sql);
  }

  public Lote_FaturamentoED inclui(Lote_FaturamentoED ed)throws Excecoes{

    Lote_FaturamentoED manED = new Lote_FaturamentoED();

    try{

      this.inicioTransacao();

      Lote_FaturamentoBD = new Lote_FaturamentoBD(this.sql);

      manED = Lote_FaturamentoBD.inclui(ed);

      this.fimTransacao(true);

    }

    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

    return manED;

  }

  public void altera(Lote_FaturamentoED ed)throws Excecoes{


    try{


      this.inicioTransacao();

      Lote_FaturamentoBD = new Lote_FaturamentoBD(this.sql);
      Lote_FaturamentoBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

  }




  public void deleta(Lote_FaturamentoED ed)throws Excecoes{


    try{

      this.inicioTransacao();

      Lote_FaturamentoBD = new Lote_FaturamentoBD(this.sql);
      Lote_FaturamentoBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

  }

  public ArrayList lista(Lote_FaturamentoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Lote_FaturamentoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }


  public Lote_FaturamentoED getByRecord(Lote_FaturamentoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Lote_FaturamentoED edVolta = new Lote_FaturamentoED();
      //atribui ao ed de retorno
      edVolta = new Lote_FaturamentoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }


  public byte[] imprime_Lote_Faturamento(Lote_FaturamentoED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Lote_FaturamentoBD = new Lote_FaturamentoBD(this.sql);
    byte[] b = Lote_FaturamentoBD.imprime_Lote_Faturamento(ed);
    this.fimTransacao(false);
    return b;
  }

  public void importaArquivo (String arquivo, String padrao, Lote_FaturamentoED ed) throws Exception {

	    // System.out.println (" importaArquivo RN --");

	    try {

	      this.inicioTransacao ();
	      new EDI_ImportacaoBD(this.sql).importaPreFat_E_Sales(arquivo , padrao, ed);
	      this.fimTransacao (true);
	    }
	    catch(Excecoes e){
	    	this.abortaTransacao();
	    	throw e;
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

}
