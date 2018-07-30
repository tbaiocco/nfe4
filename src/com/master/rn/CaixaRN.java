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

import com.master.bd.CaixaBD;
import com.master.ed.CaixaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class CaixaRN
    extends Transacao {
  CaixaBD CaixaBD = null;

  public CaixaRN () {
    //Caixabd = new CaixaBD(this.sql);
  }

//  public CaixaRN(ExecutaSQL sqlTrans) {
//    this.sql = sql;
//    new Transacao(sqlTrans);
//    new CaixaBD(this.sql);
//  }

  public CaixaED inclui (CaixaED ed) throws Excecoes {

    CaixaED CaixaED = new CaixaED ();

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      CaixaBD = new CaixaBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      CaixaED = CaixaBD.inclui (ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao (true);

    }

    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui(CaixaED)");
      excecoes.setExc (e);
      this.abortaTransacao ();
      throw excecoes;

    }

    return CaixaED;

  }

  public void altera (CaixaED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      CaixaBD = new CaixaBD (this.sql);
      CaixaBD.altera (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("inclui(CaixaED)");
      excecoes.setExc (e);
      this.abortaTransacao ();
      throw excecoes;
    }

  }

  public void atualiza (CaixaED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      CaixaBD = new CaixaBD (this.sql);
      CaixaBD.atualiza (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("inclui(CaixaED)");
      excecoes.setExc (e);
      this.abortaTransacao ();
      throw excecoes;
    }

  }

  public void estorna (CaixaED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      CaixaBD = new CaixaBD (this.sql);
      CaixaBD.estorna (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("inclui(CaixaED)");
      excecoes.setExc (e);
      this.abortaTransacao ();
      throw excecoes;
    }

  }

  public void deleta (CaixaED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      CaixaBD = new CaixaBD (this.sql);
      CaixaBD.deleta (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir");
      excecoes.setMetodo ("inclui(CaixaED)");
      excecoes.setExc (e);
      this.abortaTransacao ();
      throw excecoes;
    }

  }

  public ArrayList lista (CaixaED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    //// System.out.println("rn");

    ArrayList lista = new CaixaBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }

  public CaixaED getByRecord (CaixaED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    CaixaED edVolta = new CaixaED ();
    //atribui ao ed de retorno
    edVolta = new CaixaBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public byte[] imprime_Caixa (CaixaED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    CaixaBD = new CaixaBD (this.sql);
    byte[] b = CaixaBD.imprime_Caixa (ed);
    this.fimTransacao (false);
    return b;
  }

  public CaixaED geraSaldoInicial (CaixaED ed) throws Excecoes {

    CaixaED CaixaED = new CaixaED ();

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      CaixaBD = new CaixaBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      CaixaED = CaixaBD.geraSaldoInicial (ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao (true);

    }

    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar o valor do Caixa");
      excecoes.setMetodo ("inclui(CaixaED)");
      excecoes.setExc (e);
      this.abortaTransacao ();
      throw excecoes;

    }

    return CaixaED;

  }

  public CaixaED consultaSaldo (CaixaED ed) throws Excecoes {

    CaixaED CaixaED = new CaixaED ();

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      CaixaBD = new CaixaBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      CaixaED = CaixaBD.consultaSaldo (ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao (true);

    }

    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao consultar saldo");
      excecoes.setMetodo ("inclui(CaixaED)");
      excecoes.setExc (e);
      this.abortaTransacao ();
      throw excecoes;

    }

    return CaixaED;

  }

  public CaixaED finalizaMovimento (CaixaED ed) throws Excecoes {

    CaixaED CaixaED = new CaixaED ();

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      CaixaBD = new CaixaBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      CaixaED = CaixaBD.finalizaMovimento (ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao (true);

    }

    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao consultar saldo");
      excecoes.setMetodo ("inclui(CaixaED)");
      excecoes.setExc (e);
      this.abortaTransacao ();
      throw excecoes;

    }

    return CaixaED;

  }

}