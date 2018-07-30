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

import com.master.bd.ColetaRCEBD;
import com.master.ed.ColetaRCEED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class ColetaRCERN
    extends Transacao {
  ColetaRCEBD ColetaRCEBD = null;

  public ColetaRCERN () {
    //ColetaRCEBD = new ColetaRCEBD(this.sql);
  }

  public void inclui (ColetaRCEED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      ColetaRCEBD = new ColetaRCEBD (this.sql);

      ColetaRCEBD.inclui (ed);

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

  public void altera (ColetaRCEED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      ColetaRCEBD = new ColetaRCEBD (this.sql);
      ColetaRCEBD.altera (ed);

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

  public void deleta (ColetaRCEED ed) throws Excecoes {

    if (String.valueOf (ed.getOID_ColetaRCE ()).compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Viagem não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      ColetaRCEBD = new ColetaRCEBD (this.sql);
      ColetaRCEBD.deleta (ed);

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

  public ArrayList lista (ColetaRCEED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new ColetaRCEBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }

  public ColetaRCEED getByRecord (ColetaRCEED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ColetaRCEED edVolta = new ColetaRCEED ();
    //atribui ao ed de retorno
    edVolta = new ColetaRCEBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

}
