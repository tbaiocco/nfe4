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

import javax.servlet.http.HttpServletResponse;

import com.master.bd.Ordem_CargaBD;
import com.master.ed.Ordem_CargaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Ordem_CargaRN
    extends Transacao {
  Ordem_CargaBD Ordem_CargaBD = null;

  public Ordem_CargaRN () {
    //Ordem_Cargabd = new Ordem_CargaBD(this.sql);
  }

  public Ordem_CargaED inclui (Ordem_CargaED ed) throws Excecoes {

    Ordem_CargaED conED = new Ordem_CargaED ();

    try {
      //// System.out.println(" inclui RN 1");
      this.inicioTransacao ();
      Ordem_CargaBD = new Ordem_CargaBD (this.sql);
      conED = Ordem_CargaBD.inclui (ed);
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

    return conED;

  }

  public Ordem_CargaED getByRecord (Ordem_CargaED ed) throws Excecoes {
    Ordem_CargaED edVolta = new Ordem_CargaED ();
    try {
      this.inicioTransacao ();
      edVolta = new Ordem_CargaBD (this.sql).getByRecord (ed);
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
    return edVolta;
  }

  public ArrayList Lista_Ordem_Carga (Ordem_CargaED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new Ordem_CargaBD (sql).Lista_Ordem_Carga (ed);
    this.fimTransacao (false);
    return lista;
  }

  public byte[] imprime_Ordem_Carga_Descarga (Ordem_CargaED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    Ordem_CargaBD = new Ordem_CargaBD (this.sql);
    byte[] b = Ordem_CargaBD.imprime_Ordem_Carga_Descarga (ed);
    this.fimTransacao (true);
    return b;
  }

  public byte[] gera_Rel_Carga_Descarga (Ordem_CargaED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    Ordem_CargaBD = new Ordem_CargaBD (this.sql);
    byte[] b = Ordem_CargaBD.gera_Rel_Carga_Descarga (ed);
    this.fimTransacao (true);
    return b;
  }

  public void altera (Ordem_CargaED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      Ordem_CargaBD = new Ordem_CargaBD (this.sql);
      Ordem_CargaBD.altera (ed);
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
      excecoes.setMetodo ("altera_Veiculo");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();
      throw excecoes;
    }
  }

  public void cancela (Ordem_CargaED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      Ordem_CargaBD = new Ordem_CargaBD (this.sql);
      Ordem_CargaBD.cancela (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao cancelar");
      excecoes.setMetodo ("cancela");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();
      throw excecoes;
    }
  }


  public void gera_Compromisso (Ordem_CargaED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      Ordem_CargaBD = new Ordem_CargaBD (this.sql);
      Ordem_CargaBD.gera_Compromisso (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao gera_Compromisso");
      excecoes.setMetodo ("gera_Compromisso");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();
      throw excecoes;
    }
  }

  public void vincula_CTRC (Ordem_CargaED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      Ordem_CargaBD = new Ordem_CargaBD (this.sql);
      Ordem_CargaBD.vincula_CTRC (ed);
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
      excecoes.setMetodo ("altera_Veiculo");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();
      throw excecoes;
    }
  }

  public void deleta (Ordem_CargaED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      Ordem_CargaBD = new Ordem_CargaBD (this.sql);
      Ordem_CargaBD.deleta (ed);
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
      excecoes.setMetodo ("deleta(Ordem_CargaED ed)");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();
      throw excecoes;
    }
  }

}
