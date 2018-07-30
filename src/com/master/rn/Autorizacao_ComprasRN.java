package com.master.rn;

/**
 * <p>Title: Autorizacao_ComprasBD </p>
 * <p>Description: Cadastro, exclusão e alteração de Autorizadores. Gerenciamento de autorizações
 * de Solicitações e requisições.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2004</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */


import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Autorizacao_ComprasBD;
import com.master.bd.UsuarioBD;
import com.master.ed.Autorizacao_ComprasED;
import java.util.*;

public class Autorizacao_ComprasRN
    extends Transacao {
  Autorizacao_ComprasBD Autorizacao_ComprasBD = null;

  public Autorizacao_ComprasRN () {
  }

  public Autorizacao_ComprasED inclui_autorizador (Autorizacao_ComprasED ed) throws Excecoes {

    Autorizacao_ComprasED edVolta = new Autorizacao_ComprasED ();
    try {

      this.inicioTransacao ();
      Autorizacao_ComprasBD = new Autorizacao_ComprasBD (this.sql);

      edVolta = Autorizacao_ComprasBD.inclui_autorizador (ed);

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
      this.abortaTransacao ();

      throw excecoes;
    }
    return edVolta;
  }

  public void altera_autorizador (Autorizacao_ComprasED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Autorizacao_ComprasBD = new Autorizacao_ComprasBD (this.sql);
      Autorizacao_ComprasBD.altera_autorizador (ed);

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

  public void deleta_autorizador (Autorizacao_ComprasED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Autorizacao_ComprasBD = new Autorizacao_ComprasBD (this.sql);
      Autorizacao_ComprasBD.deleta_autorizador (ed);

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

  public ArrayList lista_autorizador (Autorizacao_ComprasED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new Autorizacao_ComprasBD (sql).lista_autorizadores (ed);
    this.fimTransacao (false);
    return lista;
  }

  public Autorizacao_ComprasED getByRecord_autorizador (Autorizacao_ComprasED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Autorizacao_ComprasED edVolta = new Autorizacao_ComprasED ();
    //atribui ao ed de retorno
    edVolta = new Autorizacao_ComprasBD (this.sql).getByRecord_autorizador (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public void inclui_perfil (Autorizacao_ComprasED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Autorizacao_ComprasBD = new Autorizacao_ComprasBD (this.sql);

      Autorizacao_ComprasBD.inclui_perfil (ed);

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
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void altera_perfil (Autorizacao_ComprasED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Autorizacao_ComprasBD = new Autorizacao_ComprasBD (this.sql);
      Autorizacao_ComprasBD.altera_perfil (ed);

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

  public void deleta_perfil (Autorizacao_ComprasED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Autorizacao_ComprasBD = new Autorizacao_ComprasBD (this.sql);
      Autorizacao_ComprasBD.deleta_perfil (ed);

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

  public ArrayList lista_perfis (Autorizacao_ComprasED ed) throws Excecoes {
    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new Autorizacao_ComprasBD (sql).lista_perfis (ed);
    this.fimTransacao (false);
    return lista;
  }

  public Autorizacao_ComprasED getByRecord_perfil (Autorizacao_ComprasED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Autorizacao_ComprasED edVolta = new Autorizacao_ComprasED ();
    //atribui ao ed de retorno
    edVolta = new Autorizacao_ComprasBD (this.sql).getByRecord_perfil (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public boolean getByEncrypt (String chave , String usuario , long oid) throws Excecoes {
    Excecoes excecoes = new Excecoes ();
    boolean ok = false;
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    try {
      // // System.out.println("entrou no rn");
      Autorizacao_ComprasBD = new Autorizacao_ComprasBD (this.sql);
      ok = Autorizacao_ComprasBD.getByEncrypt (chave , usuario , oid);
      //libera conexao nao "commitando"
      this.fimTransacao (false);
    }
    catch (Exception e) {
      this.abortaTransacao ();
      excecoes.setClasse (this.getClass ().getName ());
      e.getMessage ();
      excecoes.setMetodo ("getByEncrypt");
      excecoes.setExc (e);
      throw excecoes;
    }
    return ok;
  }

}