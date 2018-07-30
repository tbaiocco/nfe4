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

import com.master.bd.Tipo_EventoBD;
import com.master.ed.Tipo_EventoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Tipo_EventoRN extends Transacao  {
  Tipo_EventoBD Tipo_EventoBD = null;


  public Tipo_EventoRN() {
    //Tipo_Eventobd = new Tipo_EventoBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public Tipo_EventoED inclui(Tipo_EventoED ed)throws Excecoes{

    Tipo_EventoED Tipo_EventoED = new Tipo_EventoED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

 // System.err.println("r1");

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Tipo_EventoBD = new Tipo_EventoBD(this.sql);

 // System.err.println("r2");
      //chama o inclui passando a estrutura de dados
      //como parametro
      Tipo_EventoED = Tipo_EventoBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);
    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Tipo_Evento");
      excecoes.setMetodo("Inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return Tipo_EventoED;
  }

/********************************************************
 *
 *******************************************************/
  public void altera(Tipo_EventoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Tipo_EventoBD = new Tipo_EventoBD(this.sql);
      Tipo_EventoBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Tipo_Evento");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public void deleta(Tipo_EventoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Tipo_EventoBD = new Tipo_EventoBD(this.sql);
      Tipo_EventoBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Tipo_Evento");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista(Tipo_EventoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Tipo_EventoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

/********************************************************
 *
 *******************************************************/
  public Tipo_EventoED getByRecord(Tipo_EventoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Tipo_EventoED edVolta = new Tipo_EventoED();
      //atribui ao ed de retorno
      edVolta = new Tipo_EventoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

/********************************************************
 *
 *******************************************************/
  public void geraRelatorio(Tipo_EventoED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Tipo_EventoBD = new Tipo_EventoBD(this.sql);
    Tipo_EventoBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}