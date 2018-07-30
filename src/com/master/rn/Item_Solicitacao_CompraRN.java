package com.master.rn;

/**
 * Título: Item_Solicitacao_CompraRN
 * Descrição: Itens da Solicitacao de Compras - RN
 * Data da criação: 11/2004
 * Atualizado em: 11/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Teofilo Poletto Baiocco
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Item_Solicitacao_CompraBD;
import com.master.ed.Solicitacao_CompraED;
import java.util.*;

public class Item_Solicitacao_CompraRN extends Transacao {
  Item_Solicitacao_CompraBD Item_Solicitacao_CompraBD = null;

  public Item_Solicitacao_CompraRN () {
    //Solicitacao_CompraBD = new Solicitacao_CompraBD(this.sql);
  }

  public Solicitacao_CompraED recebe_Itens (Solicitacao_CompraED ed) throws Excecoes {

    Solicitacao_CompraED Solicitacao_CompraED = new Solicitacao_CompraED ();

    try {
      this.inicioTransacao ();
      Item_Solicitacao_CompraBD = new Item_Solicitacao_CompraBD (this.sql);
      Solicitacao_CompraED = Item_Solicitacao_CompraBD.recebe_Itens (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      throw e;
    }

    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir Solicitação");
      excecoes.setMetodo ("Inclui");
      excecoes.setExc (e);
      throw excecoes;
    }

    return Solicitacao_CompraED;
  }


  public Solicitacao_CompraED exclui_Recebimento (Solicitacao_CompraED ed) throws Excecoes {

	    Solicitacao_CompraED Solicitacao_CompraED = new Solicitacao_CompraED ();

	    try {
	      this.inicioTransacao ();
	      Item_Solicitacao_CompraBD = new Item_Solicitacao_CompraBD (this.sql);
	      Solicitacao_CompraED = Item_Solicitacao_CompraBD.exclui_Recebimento (ed);
	      this.fimTransacao (true);
	    }
	    catch (Excecoes e) {
	      throw e;
	    }

	    catch (Exception e) {
	      this.abortaTransacao ();
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao incluir Solicitação");
	      excecoes.setMetodo ("Inclui");
	      excecoes.setExc (e);
	      throw excecoes;
	    }

	    return Solicitacao_CompraED;
	  }


  public Solicitacao_CompraED inclui (Solicitacao_CompraED ed) throws Excecoes {

	    Solicitacao_CompraED Solicitacao_CompraED = new Solicitacao_CompraED ();

	    try {

	      //a chamada a este metodo da superclasse
	      //iniciotransacao faz com que se abra uma conexao
	      //e a deixe no pool
	      this.inicioTransacao ();

	      //instancia objeto sql, que eh
	      //uma referencia ao objeto ExecutaSQL, que por sua
	      //vez possui a referencia a conexao ativa
	      Item_Solicitacao_CompraBD = new Item_Solicitacao_CompraBD (this.sql);

	      //chama o inclui passando a estrutura de dados
	      //como parametro

	      Solicitacao_CompraED = Item_Solicitacao_CompraBD.inclui (ed);

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
	      excecoes.setMensagem ("Erro ao incluir Solicitação");
	      excecoes.setMetodo ("Inclui");
	      excecoes.setExc (e);
	      throw excecoes;
	    }

	    return Solicitacao_CompraED;
	  }

  /********************************************************
   *
   *******************************************************/
  public void altera (Solicitacao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Item_Solicitacao_CompraBD = new Item_Solicitacao_CompraBD (this.sql);
      Item_Solicitacao_CompraBD.altera (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar Solicitação");
      excecoes.setMetodo ("altera");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public void deleta (Solicitacao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Item_Solicitacao_CompraBD = new Item_Solicitacao_CompraBD (this.sql);
      Item_Solicitacao_CompraBD.deleta (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir Solicitação");
      excecoes.setMetodo ("deleta");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public void cancela (Solicitacao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Item_Solicitacao_CompraBD = new Item_Solicitacao_CompraBD (this.sql);
      Item_Solicitacao_CompraBD.cancela (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir Solicitação");
      excecoes.setMetodo ("deleta");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public Solicitacao_CompraED getByRecord (Solicitacao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    //atribui ao ed de retorno
    edVolta = new Item_Solicitacao_CompraBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList Lista (Solicitacao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new Item_Solicitacao_CompraBD (this.sql).Lista (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }

}