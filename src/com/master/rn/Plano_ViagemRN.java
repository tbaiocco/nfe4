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

import com.master.bd.Plano_ViagemBD;
import com.master.ed.Plano_ViagemED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Plano_ViagemRN extends Transacao  {
  Plano_ViagemBD Plano_ViagemBD = null;


  public Plano_ViagemRN() {
    //Plano_Viagembd = new Plano_ViagemBD(this.sql);
  }

  public void inclui(Plano_ViagemED ed)throws Excecoes{


    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Plano_ViagemBD = new Plano_ViagemBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Plano_ViagemBD.inclui(ed);

      //faz o commit em cima do objeto transacao
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


  public void altera(Plano_ViagemED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Plano_Viagem()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Plano_Viagem não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Plano_ViagemBD = new Plano_ViagemBD(this.sql);
      Plano_ViagemBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public void deleta(Plano_ViagemED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Plano_Viagem()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Plano_Viagem não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Plano_ViagemBD = new Plano_ViagemBD(this.sql);
      Plano_ViagemBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir");
      excecoes.setMetodo("excluir");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public ArrayList lista(Plano_ViagemED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Plano_ViagemBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Plano_ViagemED getByRecord(Plano_ViagemED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Plano_ViagemED edVolta = new Plano_ViagemED();
      //atribui ao ed de retorno
      edVolta = new Plano_ViagemBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }



}