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

import com.master.bd.Instrucao_DuplicataBD;
import com.master.ed.Instrucao_DuplicataED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Instrucao_DuplicataRN extends Transacao  {
  Instrucao_DuplicataBD Instrucao_DuplicataBD = null;


  public Instrucao_DuplicataRN() {
    //Instrucao_Duplicatabd = new Instrucao_DuplicataBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public Instrucao_DuplicataED inclui(Instrucao_DuplicataED ed)throws Excecoes{

    Instrucao_DuplicataED Instrucao_DuplicataED = new Instrucao_DuplicataED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

 // System.err.println("r1");

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Instrucao_DuplicataBD = new Instrucao_DuplicataBD(this.sql);

 // System.err.println("r2");
      //chama o inclui passando a estrutura de dados
      //como parametro
      Instrucao_DuplicataED = Instrucao_DuplicataBD.inclui(ed);

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
      excecoes.setMensagem("Erro ao incluir Instrucao_Duplicata");
      excecoes.setMetodo("Inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return Instrucao_DuplicataED;
  }


/********************************************************
 *
 *******************************************************/
  public void deleta(Instrucao_DuplicataED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Instrucao_DuplicataBD = new Instrucao_DuplicataBD(this.sql);
      Instrucao_DuplicataBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Instrucao_Duplicata");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista(Instrucao_DuplicataED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Instrucao_DuplicataBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

/********************************************************
 *
 *******************************************************/
  public Instrucao_DuplicataED getByRecord(Instrucao_DuplicataED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Instrucao_DuplicataED edVolta = new Instrucao_DuplicataED();
      //atribui ao ed de retorno
      edVolta = new Instrucao_DuplicataBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

/********************************************************
 *
 *******************************************************/
  public void geraRelatorio(Instrucao_DuplicataED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Instrucao_DuplicataBD = new Instrucao_DuplicataBD(this.sql);
    Instrucao_DuplicataBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}