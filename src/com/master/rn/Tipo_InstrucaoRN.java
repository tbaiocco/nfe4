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

import com.master.bd.Tipo_InstrucaoBD;
import com.master.ed.Tipo_InstrucaoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Tipo_InstrucaoRN extends Transacao  {
  Tipo_InstrucaoBD tipo_InstrucaoBD = null;


/********************************************************
 *
 *******************************************************/
  public Tipo_InstrucaoRN() {
    //Tipo_Instrucaobd = new Tipo_InstrucaoBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public Tipo_InstrucaoED inclui(Tipo_InstrucaoED ed)throws Excecoes{

    Tipo_InstrucaoED tipo_instrucaoED = new Tipo_InstrucaoED();

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      tipo_InstrucaoBD = new Tipo_InstrucaoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      tipo_instrucaoED = tipo_InstrucaoBD.inclui(ed);

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
      excecoes.setMensagem("Erro ao incluir tipo de instrução");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return tipo_instrucaoED;

  }

/********************************************************
 *
 *******************************************************/
  public void altera(Tipo_InstrucaoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      tipo_InstrucaoBD = new Tipo_InstrucaoBD(this.sql);
      tipo_InstrucaoBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar tipo de instrução");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public void deleta(Tipo_InstrucaoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      tipo_InstrucaoBD = new Tipo_InstrucaoBD(this.sql);
      tipo_InstrucaoBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir tipo de instrução");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista(Tipo_InstrucaoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Tipo_InstrucaoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Tipo_InstrucaoED getByRecord(Tipo_InstrucaoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Tipo_InstrucaoED edVolta = new Tipo_InstrucaoED();
      //atribui ao ed de retorno
      edVolta = new Tipo_InstrucaoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);
      return edVolta;
  }

/********************************************************
 *
 *******************************************************/
  public void geraRelatorio(Tipo_InstrucaoED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    tipo_InstrucaoBD = new Tipo_InstrucaoBD(this.sql);
    tipo_InstrucaoBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}