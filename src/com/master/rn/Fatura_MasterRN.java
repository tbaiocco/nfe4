package com.master.rn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.master.bd.Fatura_MasterBD;
import com.master.ed.Fatura_MasterED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Fatura_MasterRN
    extends Transacao {
  Fatura_MasterBD Fatura_MasterBD = null;

  public Fatura_MasterRN () {

  }

  public Fatura_MasterED inclui (Fatura_MasterED ed) throws Excecoes {

    Fatura_MasterED conED = new Fatura_MasterED ();
    try {

      this.inicioTransacao ();

      Fatura_MasterBD = new Fatura_MasterBD (this.sql);
      conED = Fatura_MasterBD.inclui (ed);

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

  public void deleta (Fatura_MasterED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Fatura_MasterBD = new Fatura_MasterBD (this.sql);
      Fatura_MasterBD.deleta (ed);

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

  public void deleta_Fatura (Fatura_MasterED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Fatura_MasterBD = new Fatura_MasterBD (this.sql);
      Fatura_MasterBD.deleta_Fatura (ed);

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

  public void importa_Fatura (String arquivo , String DM_Cia_Aerea , String NR_Fatura) throws Excecoes {

    try {

      this.inicioTransacao ();

      Fatura_MasterBD = new Fatura_MasterBD (this.sql);
      Fatura_MasterBD.importa_Fatura(arquivo, DM_Cia_Aerea, NR_Fatura);

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

  public ArrayList lista (Fatura_MasterED ed) throws Excecoes {
    this.inicioTransacao ();
    ArrayList lista = null;
    try {
      lista = new Fatura_MasterBD (sql).lista (ed);
      return lista;
    }
    finally {
      this.fimTransacao (true);
    }
  }


  public Fatura_MasterED getByRecord (Fatura_MasterED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Fatura_MasterED edVolta = new Fatura_MasterED ();
    //atribui ao ed de retorno
    edVolta = new Fatura_MasterBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

}
