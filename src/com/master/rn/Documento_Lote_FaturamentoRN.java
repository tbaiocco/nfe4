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

import com.master.bd.Documento_Lote_FaturamentoBD;
import com.master.ed.Documento_Lote_FaturamentoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Documento_Lote_FaturamentoRN extends Transacao  {
  Documento_Lote_FaturamentoBD Documento_Lote_FaturamentoBD = null;


  public Documento_Lote_FaturamentoRN() {
    //Documento_Lote_Faturamentobd = new Documento_Lote_FaturamentoBD(this.sql);
  }

  public Documento_Lote_FaturamentoED inclui(Documento_Lote_FaturamentoED ed)throws Excecoes{

    Documento_Lote_FaturamentoED edVolta = new Documento_Lote_FaturamentoED();

    try{

      this.inicioTransacao();

      Documento_Lote_FaturamentoBD = new Documento_Lote_FaturamentoBD(this.sql);

      edVolta = Documento_Lote_FaturamentoBD.inclui(ed);

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

    return edVolta;

  }

  public Documento_Lote_FaturamentoED inclui_Cto_Filtro(Documento_Lote_FaturamentoED ed)throws Excecoes{

    Documento_Lote_FaturamentoED edVolta = new Documento_Lote_FaturamentoED();

    try{

      this.inicioTransacao();

      Documento_Lote_FaturamentoBD = new Documento_Lote_FaturamentoBD(this.sql);

      edVolta = Documento_Lote_FaturamentoBD.inclui_Cto_Filtro(ed);

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

    return edVolta;

  }


  public void altera(Documento_Lote_FaturamentoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Documento_Lote_FaturamentoBD = new Documento_Lote_FaturamentoBD(this.sql);

      Documento_Lote_FaturamentoBD.altera(ed);

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




  public void deleta(Documento_Lote_FaturamentoED ed)throws Excecoes{


    try{

      this.inicioTransacao();

      Documento_Lote_FaturamentoBD = new Documento_Lote_FaturamentoBD(this.sql);
      Documento_Lote_FaturamentoBD.deleta(ed);

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

  public ArrayList lista(Documento_Lote_FaturamentoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Documento_Lote_FaturamentoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }


  public Documento_Lote_FaturamentoED getByRecord(Documento_Lote_FaturamentoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Documento_Lote_FaturamentoED edVolta = new Documento_Lote_FaturamentoED();
      //atribui ao ed de retorno
      edVolta = new Documento_Lote_FaturamentoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }


}
