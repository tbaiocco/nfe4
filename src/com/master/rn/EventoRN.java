package com.master.rn;

/**
 * <p>Title: Evento de parametrização</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Delta Guia </p>
 * @author Claudia Galmarini Welter
 * @version 1.0
 */

import java.util.ArrayList;

import com.master.bd.EventoBD;
import com.master.ed.EventoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;


public class EventoRN extends Transacao  {
  EventoBD EventoBD = null;


  public EventoRN() {
    //Eventobd = new EventoBD(this.sql);
  }

  public EventoED inclui(EventoED ed)throws Excecoes{

    EventoED manED = new EventoED();
    try{

      this.inicioTransacao();
      // System.out.println("inserir evento");
      EventoBD = new EventoBD(this.sql);
      manED = EventoBD.inclui(ed);
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

  public void altera(EventoED ed)throws Excecoes{

    try{
      this.inicioTransacao();
      // System.out.println("Entrou no RN do Evento");
      EventoBD = new EventoBD(this.sql);
      EventoBD.altera(ed);

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

  public void deleta(EventoED ed)throws Excecoes{
/*
    if (String.valueOf(ed.getOID_Evento()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Modelo da Nota Fiscal não foi informado !!!");
      throw exc;
    }*/

    try{

      this.inicioTransacao();

      EventoBD = new EventoBD(this.sql);
      EventoBD.deleta(ed);

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

  public ArrayList lista(EventoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new EventoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public EventoED getByRecord(EventoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      EventoED edVolta = new EventoED();
      //atribui ao ed de retorno
      EventoBD = new EventoBD(this.sql);
      edVolta = EventoBD.getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

    public EventoED getByOid(String Oid)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      EventoED edVolta = new EventoED();
      //atribui ao ed de retorno
      EventoBD = new EventoBD(this.sql);
      edVolta = EventoBD.getByOid(Oid);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }




    public ArrayList getByNM_Evento(EventoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      ArrayList edVolta = new ArrayList();
      //atribui ao ed de retorno
      edVolta = new EventoBD(this.sql).getByNM_Evento(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }



}