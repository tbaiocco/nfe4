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

import com.master.bd.AgenciaBD;
import com.master.ed.AgenciaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class AgenciaRN extends Transacao  {
  AgenciaBD agenciaBD = null;


  public AgenciaRN() {
    //Agenciabd = new AgenciaBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public AgenciaED inclui(AgenciaED ed)throws Excecoes{

    AgenciaED agenciaED = new AgenciaED();
//    if (ed.getCD_Agencia().compareTo("") == 0){
//      Excecoes exc = new Excecoes();
//      exc.setMensagem("Código do Agencia não foi informado.");
//      throw exc;
//    }

    try{

      this.inicioTransacao();
      agenciaBD = new AgenciaBD(this.sql);
      agenciaED = agenciaBD.inclui(ed);
      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de inclusão");
      exc.setClasse(this.getClass().getName());//o nome da propria classe
      //faz rollback pois deu algum erro
      this.abortaTransacao();
      throw exc;
    }

    return agenciaED;

  }

/********************************************************
 *
 *******************************************************/
  public void altera(AgenciaED ed)throws Excecoes{

//    if (ed.getCD_Agencia().compareTo("") == 0){
//      Excecoes exc = new Excecoes();
//      exc.setMensagem("Código do Agencia não foi informado !!!");
//      throw exc;
//    }

    try{

      this.inicioTransacao();

      agenciaBD = new AgenciaBD(this.sql);
      agenciaBD.altera(ed);

      this.fimTransacao(true);

    }

    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de alteração");

      this.abortaTransacao();

      throw exc;
    }

  }

/********************************************************
 *
 *******************************************************/
  public void deleta(AgenciaED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      agenciaBD = new AgenciaBD(this.sql);
      agenciaBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de exclusão");

      this.abortaTransacao();

      throw exc;
    }

  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista(AgenciaED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new AgenciaBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;

  }

/********************************************************
 *
 *******************************************************/
  public AgenciaED getByRecord(AgenciaED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      AgenciaED edVolta = new AgenciaED();
      //atribui ao ed de retorno
      edVolta = new AgenciaBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(AgenciaED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    agenciaBD = new AgenciaBD(this.sql);
    agenciaBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}