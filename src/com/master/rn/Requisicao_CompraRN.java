package com.master.rn;

/**
 * Título: Requisicao_CompraRN
 * Descrição: Requisicao de Materiais - RN
 * Data da criação: 11/2004
 * Atualizado em: 11/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Teofilo Poletto Baiocco
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Requisicao_CompraBD;
import com.master.ed.Requisicao_CompraED;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

public class Requisicao_CompraRN
    extends Transacao {
  Requisicao_CompraBD Requisicao_CompraBD = null;

  public Requisicao_CompraRN () {
    //Requisicao_CompraBD = new Requisicao_CompraBD(this.sql);
  }

  /********************************************************
   *
   *******************************************************/
  public Requisicao_CompraED inclui (Requisicao_CompraED ed) throws Excecoes {

    Requisicao_CompraED Requisicao_CompraED = new Requisicao_CompraED ();

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Requisicao_CompraBD = new Requisicao_CompraBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro

      Requisicao_CompraED = Requisicao_CompraBD.inclui (ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      throw e;
    }

    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir Requisição");
      excecoes.setMetodo ("Inclui");
      excecoes.setExc (e);
      throw excecoes;
    }

    return Requisicao_CompraED;
  }

  /********************************************************
   *
   *******************************************************/
  public void altera (Requisicao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Requisicao_CompraBD = new Requisicao_CompraBD (this.sql);
      Requisicao_CompraBD.altera (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar Requisição");
      excecoes.setMetodo ("altera");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public void deleta (Requisicao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Requisicao_CompraBD = new Requisicao_CompraBD (this.sql);
      Requisicao_CompraBD.deleta (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir Requisição");
      excecoes.setMetodo ("deleta");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public void cancela (Requisicao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Requisicao_CompraBD = new Requisicao_CompraBD (this.sql);
      Requisicao_CompraBD.cancela (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir Requisição");
      excecoes.setMetodo ("deleta");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public void autoriza (Requisicao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Requisicao_CompraBD = new Requisicao_CompraBD (this.sql);
      Requisicao_CompraBD.autoriza (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir Requisição");
      excecoes.setMetodo ("deleta");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public Requisicao_CompraED getByRecord (Requisicao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Requisicao_CompraED edVolta = new Requisicao_CompraED ();
    //atribui ao ed de retorno
    edVolta = new Requisicao_CompraBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList Lista (Requisicao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new Requisicao_CompraBD (this.sql).Lista (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList Lista_Itens (Requisicao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new Requisicao_CompraBD (this.sql).Lista_Itens (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }

  /********************************************************
   *
   *******************************************************/
  public Requisicao_CompraED entregar (Requisicao_CompraED ed) throws Excecoes {

    Requisicao_CompraED edVolta = new Requisicao_CompraED ();
    try {
      this.inicioTransacao ();
      edVolta = new Requisicao_CompraBD (this.sql).entregar (ed);
      this.fimTransacao (true);
    }

    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao entregar Requisição");
      excecoes.setMetodo ("entregar()");
      excecoes.setExc (e);
      throw excecoes;
    }

    return edVolta;
  }

  /********************************************************
   *
   *******************************************************/
  public void imprime_Protocolo (Requisicao_CompraED ed , HttpServletResponse response) throws Excecoes {

    try {

      this.inicioTransacao ();

      Requisicao_CompraBD = new Requisicao_CompraBD (this.sql);
      Requisicao_CompraBD.imprime_Protocolo (ed , response);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao imprimir Protocolo");
      excecoes.setMetodo ("imprime_Protocolo()");
      excecoes.setExc (e);
      throw excecoes;
    }

  }
}