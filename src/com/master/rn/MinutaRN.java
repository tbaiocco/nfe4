package com.master.rn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.master.bd.MinutaBD;
import com.master.ed.Nota_FiscalED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class MinutaRN
    extends Transacao {
  MinutaBD MinutaBD = null;

  public MinutaRN () {

  }

  public Nota_FiscalED inclui (Nota_FiscalED ed) throws Excecoes {

    Nota_FiscalED conED = new Nota_FiscalED ();
    try {

      this.inicioTransacao ();

      MinutaBD = new MinutaBD (this.sql);
      conED = MinutaBD.inclui (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      exc.printStackTrace ();
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      e.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return conED;

  }

  public void altera (Nota_FiscalED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      MinutaBD = new MinutaBD (this.sql);
      MinutaBD.altera (ed);

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

  public void deleta (Nota_FiscalED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      MinutaBD = new MinutaBD (this.sql);
      MinutaBD.deleta (ed);

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

  public ArrayList lista (Nota_FiscalED ed) throws Excecoes {
    this.inicioTransacao ();
    ArrayList lista = null;
    try {
      lista = new MinutaBD (sql).lista (ed);
      return lista;
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public Nota_FiscalED getByRecord (Nota_FiscalED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Nota_FiscalED edVolta = new Nota_FiscalED ();
    //atribui ao ed de retorno
    edVolta = new MinutaBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public byte[] imprime_Minuta (Nota_FiscalED ed) throws Excecoes {

    byte[] b = null;

    try {
      this.inicioTransacao ();
      MinutaBD = new MinutaBD (this.sql);
      b = MinutaBD.imprime_Minuta (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar Minuta");
      excecoes.setMetodo ("imprime_Minuta(Nota_FiscalED ed)");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
    return b;
  }

}
