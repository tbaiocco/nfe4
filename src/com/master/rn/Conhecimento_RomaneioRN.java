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

import com.master.bd.Conhecimento_RomaneioBD;
import com.master.ed.Conhecimento_RomaneioED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Conhecimento_RomaneioRN extends Transacao  {
  Conhecimento_RomaneioBD Conhecimento_RomaneioBD = null;


  public Conhecimento_RomaneioRN() {
    //Conhecimento_Romaneiobd = new Conhecimento_RomaneioBD(this.sql);
  }

  public void inclui(Conhecimento_RomaneioED ed)throws Excecoes{

    if (ed.getOID_Conhecimento().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Conhecimento não foi informado !!!");
      throw exc;
    }

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Conhecimento_RomaneioBD = new Conhecimento_RomaneioBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Conhecimento_RomaneioBD.inclui(ed);

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

  public void altera(Conhecimento_RomaneioED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Conhecimento_Romaneio()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Conhecimento_Romaneio não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Conhecimento_RomaneioBD = new Conhecimento_RomaneioBD(this.sql);
      Conhecimento_RomaneioBD.altera(ed);

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

  public void deleta(Conhecimento_RomaneioED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Conhecimento_Romaneio()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Conhecimento_Romaneio não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Conhecimento_RomaneioBD = new Conhecimento_RomaneioBD(this.sql);
      Conhecimento_RomaneioBD.deleta(ed);

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

  public ArrayList lista(Conhecimento_RomaneioED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Conhecimento_RomaneioBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Conhecimento_RomaneioED getByRecord(Conhecimento_RomaneioED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Conhecimento_RomaneioED edVolta = new Conhecimento_RomaneioED();
      //atribui ao ed de retorno
      edVolta = new Conhecimento_RomaneioBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(Conhecimento_RomaneioED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Conhecimento_RomaneioBD = new Conhecimento_RomaneioBD(this.sql);
    Conhecimento_RomaneioBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}