package com.master.rn;

import java.util.List;

import com.master.bd.ReferenciaBD;
import com.master.ed.ReferenciaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class ReferenciaRN
    extends Transacao {
  ReferenciaBD ReferenciaBD = null;

  public ReferenciaRN () {
  }

  
  public ReferenciaED inclui (ReferenciaED ed) throws Excecoes {
    ReferenciaED conED = new ReferenciaED ();
    try {
      this.inicioTransacao ();
      ReferenciaBD = new ReferenciaBD (this.sql);
      conED = ReferenciaBD.inclui (ed);
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
    return conED;

  }

  public void altera (ReferenciaED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      ReferenciaBD = new ReferenciaBD (this.sql);
      ReferenciaBD.altera (ed);

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

  public void deleta (ReferenciaED ed) throws Excecoes {

    if (String.valueOf (ed.getOID_Referencia ()).compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Referencia não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      ReferenciaBD = new ReferenciaBD (this.sql);
      ReferenciaBD.deleta (ed);

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

  public List lista (ReferenciaED ed) throws Excecoes {
    List lista;
    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    try {
      lista = new ReferenciaBD (sql).lista (ed);
    }
    finally {
      this.fimTransacao (false);
    }
    return lista;
  }

  public List listaReferencia (ReferenciaED ed) throws Excecoes {
    List lista;
    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    try {
      lista = new ReferenciaBD (sql).listaReferencia (ed);
    }
    finally {
      this.fimTransacao (false);
    }
    return lista;
  }

  public ReferenciaED getByRecord (ReferenciaED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ReferenciaED edVolta = new ReferenciaED ();
    //atribui ao ed de retorno
    edVolta = new ReferenciaBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public void geraRelatorio (ReferenciaED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    ReferenciaBD = new ReferenciaBD (this.sql);
    ReferenciaBD.geraRelatorio (ed);
    this.fimTransacao (false);

  }

}