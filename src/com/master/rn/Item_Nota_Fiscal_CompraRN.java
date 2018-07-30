package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Item_Nota_Fiscal_CompraBD;
import com.master.ed.Item_Nota_Fiscal_CompraED;
import java.util.*;

public class Item_Nota_Fiscal_CompraRN
    extends Transacao {
  Item_Nota_Fiscal_CompraBD Item_Nota_Fiscal_CompraBD = null;

  public Item_Nota_Fiscal_CompraRN () {
    //Item_Nota_Fiscal_Comprabd = new Item_Nota_Fiscal_CompraBD(this.sql);
  }

  public void inclui (Item_Nota_Fiscal_CompraED ed) throws Excecoes {

    if (ed.getOID_Nota_Fiscal ().compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Referencia não foi informado !!!");
      throw exc;
    }

    try {

      if (ed.getVL_Produto () <= 0) {
        Excecoes exc = new Excecoes ();
        exc.setMensagem ("Valor do produto menor ou igual a zero");
        exc.setClasse (this.getClass ().getName ());
        exc.setMetodo ("inclui(Item_Nota_Fiscal_CompraED ed)");
        throw exc;
      }

      if (ed.getVl_quantidade () <= 0) {
        Excecoes exc = new Excecoes ();
        exc.setMensagem ("Volumes do item nota fiscal menor ou igual a zero");
        exc.setClasse (this.getClass ().getName ());
        exc.setMetodo ("inclui(Item_Nota_Fiscal_CompraED ed)");
        throw exc;
      }

      this.inicioTransacao ();

      Item_Nota_Fiscal_CompraBD = new Item_Nota_Fiscal_CompraBD (this.sql);

      Item_Nota_Fiscal_CompraBD.inclui (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void altera (Item_Nota_Fiscal_CompraED ed) throws Excecoes {

    if (String.valueOf (ed.getOID_Item_Nota_Fiscal ()).compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Item_Nota_Fiscal_Compra não foi informado !!!");
      throw exc;
    }

    try {

      if (ed.getVL_Produto () <= 0) {
        Excecoes exc = new Excecoes ();
        exc.setMensagem ("Valor do produto menor ou igual a zero");
        exc.setClasse (this.getClass ().getName ());
        exc.setMetodo ("altera(Item_Nota_Fiscal_CompraED ed)");
        throw exc;
      }

      if (ed.getVl_quantidade () <= 0) {
        Excecoes exc = new Excecoes ();
        exc.setMensagem ("Volumes do item nota fiscal menor ou igual a zero");
        exc.setClasse (this.getClass ().getName ());
        exc.setMetodo ("altera(Item_Nota_Fiscal_CompraED ed)");
        throw exc;
      }

      this.inicioTransacao ();

      Item_Nota_Fiscal_CompraBD = new Item_Nota_Fiscal_CompraBD (this.sql);
      Item_Nota_Fiscal_CompraBD.altera (ed);

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
      excecoes.setMetodo ("alterar");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void deleta (Item_Nota_Fiscal_CompraED ed) throws Excecoes {

    if (String.valueOf (ed.getOID_Item_Nota_Fiscal ()).compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Item_Nota_Fiscal_Compra não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      Item_Nota_Fiscal_CompraBD = new Item_Nota_Fiscal_CompraBD (this.sql);
      Item_Nota_Fiscal_CompraBD.deleta (ed);

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
      excecoes.setMetodo ("excluir");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public ArrayList lista (Item_Nota_Fiscal_CompraED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    // // System.out.println("RN");
    ArrayList lista = new Item_Nota_Fiscal_CompraBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }

  public Item_Nota_Fiscal_CompraED getByRecord (Item_Nota_Fiscal_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Item_Nota_Fiscal_CompraED edVolta = new Item_Nota_Fiscal_CompraED ();
    //atribui ao ed de retorno
    edVolta = new Item_Nota_Fiscal_CompraBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

}