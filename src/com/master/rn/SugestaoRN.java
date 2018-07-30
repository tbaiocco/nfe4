package com.master.rn;

/**
 * <p>Title: Modelo da Nota Fiscal de parametrização</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Delta Guia </p>
 * @author Claudia Galmarini Welter
 * @version 1.0
 */

import java.util.ArrayList;

import com.master.bd.SugestaoBD;
import com.master.ed.SugestaoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;


public class SugestaoRN extends Transacao  {
  SugestaoBD SugestaoBD = null;


  public SugestaoRN() {
    //Sugestaobd = new SugestaoBD(this.sql);
  }

  public SugestaoED inclui(SugestaoED ed)throws Excecoes{

    SugestaoED manED = new SugestaoED();
    try{

      this.inicioTransacao();
      SugestaoBD = new SugestaoBD(this.sql);
      manED = SugestaoBD.inclui(ed);
      this.fimTransacao(true);
    }

    catch(Excecoes exc){throw exc;}

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

    return manED;

  }

  public void altera(SugestaoED ed)throws Excecoes{

    try{
      this.inicioTransacao();
      // System.out.println("Entrou no RN do Sugestao");
      SugestaoBD = new SugestaoBD(this.sql);
      SugestaoBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){throw exc;}

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

  public void deleta(SugestaoED ed)throws Excecoes{
/*
    if (String.valueOf(ed.getOID_Sugestao()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Modelo da Nota Fiscal não foi informado !!!");
      throw exc;
    }*/

    try{

      this.inicioTransacao();

      SugestaoBD = new SugestaoBD(this.sql);
      SugestaoBD.deletaVinculo(ed);
      SugestaoBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){throw exc;}

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

  public ArrayList lista(SugestaoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new SugestaoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public SugestaoED getByRecord(SugestaoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      SugestaoED edVolta = new SugestaoED();
      //atribui ao ed de retorno
      edVolta = new SugestaoBD(this.sql).getByRecord(ed);
      // System.out.println("Entrou no RN");
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

    public ArrayList getByNM_Sugestao(SugestaoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      ArrayList edVolta = new ArrayList();
      //atribui ao ed de retorno
      edVolta = new SugestaoBD(this.sql).getByNM_Sugestao(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public SugestaoED getByModelo(String oid)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      SugestaoED edVolta = new SugestaoED();
      //atribui ao ed de retorno
      edVolta = new SugestaoBD(this.sql).getByModelo(oid);
      // System.out.println("Entrou no RN");
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

}