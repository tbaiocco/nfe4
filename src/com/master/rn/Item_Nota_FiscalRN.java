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

import com.master.bd.Item_Nota_FiscalBD;
import com.master.ed.Item_Nota_FiscalED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Item_Nota_FiscalRN
    extends Transacao {
  Item_Nota_FiscalBD Item_Nota_FiscalBD = null;

  public Item_Nota_FiscalRN () {
    //Item_Nota_Fiscalbd = new Item_Nota_FiscalBD(this.sql);
  }

  public void inclui (Item_Nota_FiscalED ed) throws Excecoes {

    if (ed.getOID_Nota_Fiscal ().compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Referencia não foi informado !!!");
      throw exc;
    }

    try {


      if (ed.getNR_Volumes () <= 0) {
        Excecoes exc = new Excecoes ();
        exc.setMensagem ("Volumes do item nota fiscal menor ou igual a zero");
        exc.setClasse (this.getClass ().getName ());
        exc.setMetodo ("inclui(Item_Nota_FiscalED ed)");
        throw exc;
      }

      this.inicioTransacao ();

      Item_Nota_FiscalBD = new Item_Nota_FiscalBD (this.sql);

      Item_Nota_FiscalBD.inclui (ed);

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

  public void altera (Item_Nota_FiscalED ed) throws Excecoes {

    if (String.valueOf (ed.getOID_Item_Nota_Fiscal ()).compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Item_Nota_Fiscal não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      Item_Nota_FiscalBD = new Item_Nota_FiscalBD (this.sql);
      Item_Nota_FiscalBD.altera (ed);

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

  public void deleta (Item_Nota_FiscalED ed) throws Excecoes {

    if (String.valueOf (ed.getOID_Item_Nota_Fiscal ()).compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Item_Nota_Fiscal não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      Item_Nota_FiscalBD = new Item_Nota_FiscalBD (this.sql);
      Item_Nota_FiscalBD.deleta (ed);

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

  public ArrayList lista (Item_Nota_FiscalED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new Item_Nota_FiscalBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }

  public Item_Nota_FiscalED getByRecord (Item_Nota_FiscalED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Item_Nota_FiscalED edVolta = new Item_Nota_FiscalED ();
    //atribui ao ed de retorno
    edVolta = new Item_Nota_FiscalBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public void geraRelatorio (Item_Nota_FiscalED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    Item_Nota_FiscalBD = new Item_Nota_FiscalBD (this.sql);
    Item_Nota_FiscalBD.geraRelatorio (ed);
    this.fimTransacao (false);

  }

}