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

import com.master.bd.Grupo_Pessoa_CargaBD;
import com.master.ed.Grupo_Pessoa_CargaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Grupo_Pessoa_CargaRN extends Transacao  {
  Grupo_Pessoa_CargaBD Grupo_Pessoa_CargaBD = null;


  public Grupo_Pessoa_CargaRN() {
    //Grupo_Pessoa_Cargabd = new Grupo_Pessoa_CargaBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public Grupo_Pessoa_CargaED inclui(Grupo_Pessoa_CargaED ed)throws Excecoes{

    Grupo_Pessoa_CargaED Grupo_Pessoa_CargaED = new Grupo_Pessoa_CargaED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

 // System.err.println("r1");

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Grupo_Pessoa_CargaBD = new Grupo_Pessoa_CargaBD(this.sql);

 // System.err.println("r2");
      //chama o inclui passando a estrutura de dados
      //como parametro
      Grupo_Pessoa_CargaED = Grupo_Pessoa_CargaBD.inclui(ed);

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
      excecoes.setMensagem("Erro ao incluir Grupo_Pessoa_Carga");
      excecoes.setMetodo("Inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return Grupo_Pessoa_CargaED;
  }

/********************************************************
 *
 *******************************************************/
  public void altera(Grupo_Pessoa_CargaED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Grupo_Pessoa_CargaBD = new Grupo_Pessoa_CargaBD(this.sql);
      Grupo_Pessoa_CargaBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Grupo_Pessoa_Carga");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public void deleta(Grupo_Pessoa_CargaED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Grupo_Pessoa_CargaBD = new Grupo_Pessoa_CargaBD(this.sql);
      Grupo_Pessoa_CargaBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Grupo_Pessoa_Carga");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista(Grupo_Pessoa_CargaED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Grupo_Pessoa_CargaBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

/********************************************************
 *
 *******************************************************/
  public Grupo_Pessoa_CargaED getByRecord(Grupo_Pessoa_CargaED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Grupo_Pessoa_CargaED edVolta = new Grupo_Pessoa_CargaED();
      //atribui ao ed de retorno
      edVolta = new Grupo_Pessoa_CargaBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

/********************************************************
 *
 *******************************************************/
  public void geraRelatorio(Grupo_Pessoa_CargaED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Grupo_Pessoa_CargaBD = new Grupo_Pessoa_CargaBD(this.sql);
    Grupo_Pessoa_CargaBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}