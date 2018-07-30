package com.master.rn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.master.bd.PostagemBD;
import com.master.ed.PostagemED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class PostagemRN
    extends Transacao {
  PostagemBD PostagemBD = null;

  public PostagemRN () {

  }

  public PostagemED inclui (PostagemED ed) throws Excecoes {

    PostagemED conED = new PostagemED ();
    try {

      this.inicioTransacao ();

      PostagemBD = new PostagemBD (this.sql);
      conED = PostagemBD.inclui (ed);

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

  public void altera (PostagemED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      PostagemBD = new PostagemBD (this.sql);
      PostagemBD.altera (ed);

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

  public void deleta (PostagemED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      PostagemBD = new PostagemBD (this.sql);
      PostagemBD.deleta (ed);

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

  public void deletaLote (PostagemED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      PostagemBD = new PostagemBD (this.sql);
      PostagemBD.deletaLote (ed);

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
      excecoes.setMetodo ("deletaLote");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }


  public void deletaFatura (PostagemED ed) throws Excecoes {

    try {
      // System.out.println ("deletaFatura RN");

      this.inicioTransacao ();

      PostagemBD = new PostagemBD (this.sql);
      // System.out.println ("deletaFatura RN 2");

      PostagemBD.deletaFatura (ed);

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
      excecoes.setMetodo ("deletaLote");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }


  public void importa_Arquivo (String arquivo, String tipo, String NR_Fatura) throws Excecoes {

    try {

      this.inicioTransacao ();

      PostagemBD = new PostagemBD (this.sql);
      PostagemBD.importa_Arquivo(arquivo, tipo, NR_Fatura);

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

  public ArrayList lista (PostagemED ed) throws Excecoes {
    this.inicioTransacao ();
    ArrayList lista = null;
    try {
      lista = new PostagemBD (sql).lista (ed);
      return lista;
    }
    finally {
      this.fimTransacao (true);
    }
  }

  public ArrayList listaPostagemConhecimento (PostagemED ed) throws Excecoes {
    this.inicioTransacao ();
    ArrayList lista = null;
    try {
      lista = new PostagemBD (sql).listaPostagemConhecimento (ed);
      return lista;
    }
    finally {
      this.fimTransacao (true);
    }
  }

  public ArrayList gera_Remessa (PostagemED ed) throws Excecoes {
    this.inicioTransacao ();
    ArrayList lista = null;
    try {
      lista = new PostagemBD (sql).gera_Remessa (ed);
      return lista;
    }
    finally {
      this.fimTransacao (true);
    }
  }

  public PostagemED getByRecord (PostagemED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    PostagemED edVolta = new PostagemED ();
    //atribui ao ed de retorno
    edVolta = new PostagemBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

}
