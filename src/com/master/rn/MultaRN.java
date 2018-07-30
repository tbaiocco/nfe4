package com.master.rn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.master.bd.MultaBD;
import com.master.ed.MultaED;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

public class MultaRN
    extends Transacao {
  MultaBD MultaBD = null;

  public MultaRN () {
    //Multabd = new MultaBD(this.sql);
  }

  public MultaED inclui (MultaED ed) throws Excecoes {
    MultaED manED = new MultaED ();
    try {

      this.inicioTransacao ();

      MultaBD = new MultaBD (this.sql);

      manED = MultaBD.inclui (ed);

      this.fimTransacao (true);

    }

    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return manED;

  }

  public MultaED inclui_Parcela_Conta_Corrente (MultaED ed) throws Excecoes {
    MultaED manED = new MultaED ();
    try {

      this.inicioTransacao ();

      MultaBD = new MultaBD (this.sql);

      manED = MultaBD.inclui_Parcela_Conta_Corrente (ed);

      this.fimTransacao (true);

    }

    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui_Parcela_Conta_Corrente");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return manED;

  }


  public void altera (MultaED ed) throws Excecoes {

    if (String.valueOf (ed.getOID_Multa ()).compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Multa não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      MultaBD = new MultaBD (this.sql);
      MultaBD.altera (ed);

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
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

  }

  public void deleta (MultaED ed) throws Excecoes {
    if (!JavaUtil.doValida (ed.getOID_Multa ())) {
      Excecoes exc = new Excecoes ("Código do Multa não foi informado!");
    }
    try {
      this.inicioTransacao ();
      new MultaBD (this.sql).deleta (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  public ArrayList lista_Parcela_Conta_Corrente (MultaED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new MultaBD (sql).lista_Parcela_Conta_Corrente (ed);
    this.fimTransacao (false);
    return lista;
  }

  public ArrayList lista (MultaED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new MultaBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }


  public MultaED getByRecord (MultaED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    MultaED edVolta = new MultaED ();
    //atribui ao ed de retorno
    edVolta = new MultaBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public byte[] gera_Relatorio_Multa (MultaED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    try {
      MultaBD = new MultaBD (this.sql);
      byte[] b = MultaBD.gera_Relatorio_Multa (ed);
      return b;
    }
    finally {
      this.fimTransacao (false);
    }
  }

}
