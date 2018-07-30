package com.master.rn;

/**
 * Título: Cotacao_CompraRN
 * Descrição: Cotacao de Compras - RN
 * Data da criação: 11/2004
 * Atualizado em: 11/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Teofilo Poletto Baiocco
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Cotacao_CompraBD;
import com.master.ed.Cotacao_CompraED;
import java.util.*;

public class Cotacao_CompraRN
    extends Transacao {
  Cotacao_CompraBD Cotacao_CompraBD = null;

  public Cotacao_CompraRN () {

  }

  /********************************************************
   *
   *******************************************************/
  public Cotacao_CompraED inclui_cotacao (Cotacao_CompraED ed) throws Excecoes {

    Cotacao_CompraED Cotacao_CompraED = new Cotacao_CompraED ();

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Cotacao_CompraBD = new Cotacao_CompraBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro

      Cotacao_CompraED = Cotacao_CompraBD.inclui_cotacao (ed);

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
      excecoes.setMensagem ("Erro ao incluir Cotação");
      excecoes.setMetodo ("Inclui");
      excecoes.setExc (e);
      throw excecoes;
    }

    return Cotacao_CompraED;
  }

  /********************************************************
   *
   *******************************************************/
  public void altera_item (Cotacao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Cotacao_CompraBD = new Cotacao_CompraBD (this.sql);
      Cotacao_CompraBD.altera_item (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar Ítem");
      excecoes.setMetodo ("altera");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public void deleta (Cotacao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Cotacao_CompraBD = new Cotacao_CompraBD (this.sql);
      Cotacao_CompraBD.deleta (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir Ítem");
      excecoes.setMetodo ("deleta");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public void rejeita_item (Cotacao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Cotacao_CompraBD = new Cotacao_CompraBD (this.sql);
      Cotacao_CompraBD.rejeita_item (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao rejeitar Ítem");
      excecoes.setMetodo ("rejeita_item()");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public void aceita_item (Cotacao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Cotacao_CompraBD = new Cotacao_CompraBD (this.sql);
      Cotacao_CompraBD.aceita_item (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao efetivar Ítem");
      excecoes.setMetodo ("aceita_item()");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public Cotacao_CompraED getByRecord_cotacao (Cotacao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Cotacao_CompraED edVolta = new Cotacao_CompraED ();
    //atribui ao ed de retorno
    edVolta = new Cotacao_CompraBD (this.sql).getByRecord_cotacao (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList Lista_cotacao (Cotacao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new Cotacao_CompraBD (this.sql).Lista_cotacoes (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }

  /********************************************************
   *
   *******************************************************/
  public Cotacao_CompraED inclui_item (Cotacao_CompraED ed) throws Excecoes {

    Cotacao_CompraED Cotacao_CompraED = new Cotacao_CompraED ();

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Cotacao_CompraBD = new Cotacao_CompraBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro

      Cotacao_CompraED = Cotacao_CompraBD.inclui_item (ed);

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
      excecoes.setMensagem ("Erro ao incluir Ítem");
      excecoes.setMetodo ("Inclui");
      excecoes.setExc (e);
      throw excecoes;
    }

    return Cotacao_CompraED;
  }

  /********************************************************
   *
   *******************************************************/
  public Cotacao_CompraED getByRecord_item (Cotacao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Cotacao_CompraED edVolta = new Cotacao_CompraED ();
    //atribui ao ed de retorno
    edVolta = new Cotacao_CompraBD (this.sql).getByRecord_item (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList Lista_item (Cotacao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new Cotacao_CompraBD (this.sql).Lista_itens (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }

}