package com.master.rn;

import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Solicitacao_CompraBD;
import com.master.ed.Solicitacao_CompraED;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

public class Solicitacao_CompraRN
    extends Transacao {
  Solicitacao_CompraBD Solicitacao_CompraBD = null;

  public Solicitacao_CompraRN () {
    //Solicitacao_CompraBD = new Solicitacao_CompraBD(this.sql);
  }

  public Solicitacao_CompraED inclui (Solicitacao_CompraED ed) throws Excecoes {

    Solicitacao_CompraED Solicitacao_CompraED = new Solicitacao_CompraED ();

    try {
      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();
     // System.out.println("incluzaoRN");
      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Solicitacao_CompraBD = new Solicitacao_CompraBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro

      Solicitacao_CompraED = Solicitacao_CompraBD.inclui (ed);

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

    return Solicitacao_CompraED;
  }

  /********************************************************
   *
   *******************************************************/
  public void altera (Solicitacao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

     //System.out.println("alteraçãoRN");

      Solicitacao_CompraBD = new Solicitacao_CompraBD (this.sql);
      Solicitacao_CompraBD.altera (ed);

      //System.out.println("alteraçãoRN2");

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
  public void deleta (Solicitacao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Solicitacao_CompraBD = new Solicitacao_CompraBD (this.sql);
      Solicitacao_CompraBD.deleta (ed);

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
  public void cancela (Solicitacao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Solicitacao_CompraBD = new Solicitacao_CompraBD (this.sql);
      Solicitacao_CompraBD.cancela (ed);

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

  public void exclui_Pedido (Solicitacao_CompraED ed) throws Excecoes {

	    try {

	      this.inicioTransacao ();

	      Solicitacao_CompraBD = new Solicitacao_CompraBD (this.sql);
	      Solicitacao_CompraBD.exclui_Pedido (ed);

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


  public void inclui_Nota_Fiscal_Itens_Recebidos (Solicitacao_CompraED ed) throws Excecoes {

	    try {

	      this.inicioTransacao ();

	      System.out.println("inclui_Nota_Fiscal_Itens_RecebidosRN");


	      Solicitacao_CompraBD = new Solicitacao_CompraBD (this.sql);
	      Solicitacao_CompraBD.inclui_Nota_Fiscal_Itens_Recebidos (ed);

	      System.out.println("inclui_Nota_Fiscal_Itens_RecebidosRN2");


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

  public void atualiza_Estoque_Itens_Recebidos (Solicitacao_CompraED ed) throws Excecoes {

	    try {

	      this.inicioTransacao ();

	      Solicitacao_CompraBD = new Solicitacao_CompraBD (this.sql);
	      Solicitacao_CompraBD.atualiza_Estoque_Itens_Recebidos (ed);

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
  public void finaliza (Solicitacao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Solicitacao_CompraBD = new Solicitacao_CompraBD (this.sql);
      Solicitacao_CompraBD.finaliza (ed);

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
  public void gera_Pedido (Solicitacao_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      System.out.println("Solicitacao_CompraED");

      Solicitacao_CompraBD = new Solicitacao_CompraBD (this.sql);
      Solicitacao_CompraBD.gera_Pedido (ed);

      System.out.println("Solicitacao_CompraED2");

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
  public Solicitacao_CompraED getByRecord (Solicitacao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    //atribui ao ed de retorno
    edVolta = new Solicitacao_CompraBD (this.sql).getByRecord (ed);
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
    lista = new Solicitacao_CompraBD (this.sql).Lista (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList Lista_Itens (Solicitacao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new Solicitacao_CompraBD (this.sql).Lista_Itens (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }


  public ArrayList Lista_Itens_Entrega (Solicitacao_CompraED ed) throws Excecoes {
	    //inicia conexao com bd
	    this.inicioTransacao ();
	    //instancia ed de retorno
	    ArrayList lista = new ArrayList ();
	    //atribui ao ed de retorno
	    lista = new Solicitacao_CompraBD (this.sql).Lista_Itens_Entrega (ed);
	    //libera conexao nao "commitando"
	    this.fimTransacao (false);

	    return lista;
	  }

  /********************************************************
   *
   *******************************************************/
  public Solicitacao_CompraED getByRecord_pedido (Solicitacao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    //atribui ao ed de retorno
    edVolta = new Solicitacao_CompraBD (this.sql).getByRecord_pedido (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  /********************************************************
  *
  *******************************************************/
  public void imprime_pedido (Solicitacao_CompraED ed , HttpServletResponse resp) throws Excecoes {
   //inicia conexao com bd
   this.inicioTransacao ();
   //instancia ed de retorno
   Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
   //atribui ao ed de retorno
   new Solicitacao_CompraBD (this.sql).imprime_pedido (ed , resp);
   //libera conexao nao "commitando"
   this.fimTransacao (false);
 }

 /********************************************************
 *
 *******************************************************/
 public void imprime_solicitacao (Solicitacao_CompraED ed , HttpServletResponse resp) throws Excecoes {
  //inicia conexao com bd
  this.inicioTransacao ();
  //instancia ed de retorno
  Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
  //atribui ao ed de retorno
  new Solicitacao_CompraBD (this.sql).imprime_solicitacao (ed , resp);
  //libera conexao nao "commitando"
  this.fimTransacao (false);
 }

  /********************************************************
   *
   *******************************************************/
  public ArrayList Lista_Fornecedores_Cotacoes (Solicitacao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new Solicitacao_CompraBD (this.sql).Lista_fornecedores_cotacoes (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList Lista_Pedidos (Solicitacao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new Solicitacao_CompraBD (this.sql).Lista_Pedidos (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }

  /********************************************************
   *
   *******************************************************/
  public String finaliza_pedido (Solicitacao_CompraED ed) throws Excecoes {

    String msg = "";
    try {
      this.inicioTransacao ();
      Solicitacao_CompraBD = new Solicitacao_CompraBD (this.sql);
      msg = Solicitacao_CompraBD.Finaliza_Pedido (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao finalizar pedido");
      excecoes.setMetodo ("finaliza_pedido()");
      excecoes.setExc (e);
      throw excecoes;
    }
    return msg;
  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList Lista_Pedidos_NF (Solicitacao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new Solicitacao_CompraBD (this.sql).Lista_Pedidos_NF (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }

  /********************************************************
   *
   *******************************************************/
  public void relSolicitacao (Solicitacao_CompraED ed , HttpServletResponse resp) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    //atribui ao ed de retorno
    new Solicitacao_CompraBD (this.sql).relSolicitacao (ed , resp);
    //libera conexao nao "commitando"
    this.fimTransacao (false);
  }

  /********************************************************
   *
   *******************************************************/
  public void relPedido_Compra (Solicitacao_CompraED ed , HttpServletResponse resp) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    //atribui ao ed de retorno
    new Solicitacao_CompraBD (this.sql).relPedido_Compra (ed , resp);
    //libera conexao nao "commitando"
    this.fimTransacao (false);
  }

  /********************************************************
   *
   *******************************************************/
  public void relPrazoMedio (Solicitacao_CompraED ed , HttpServletResponse resp) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    //atribui ao ed de retorno
    new Solicitacao_CompraBD (this.sql).relPrazoMedio (ed , resp);
    //libera conexao nao "commitando"
    this.fimTransacao (false);
  }

  /********************************************************
   *
   *******************************************************/
  public Solicitacao_CompraED getMov_ServicoToNF (Solicitacao_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    // System.out.println ("instancia ed de retorno");
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    //atribui ao ed de retorno
    edVolta = new Solicitacao_CompraBD (this.sql).getMov_ServicoToNF (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

}