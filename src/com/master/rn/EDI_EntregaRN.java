package com.master.rn;

import java.util.*;

import com.master.bd.*;
import com.master.ed.*;
import com.master.util.*;
import com.master.util.bd.*;

public class EDI_EntregaRN
    extends Transacao {
  EDI_EntregaBD EDI_EntregaBD = null;

  public EDI_EntregaRN () {

  }

  public void deleta (EDI_EntregaED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      EDI_EntregaBD = new EDI_EntregaBD (this.sql);
      EDI_EntregaBD.deleta (ed);

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

  public ArrayList lista (EDI_EntregaED ed) throws Excecoes {
    this.inicioTransacao ();
    ArrayList lista = null;
    try {
      lista = new EDI_EntregaBD (sql).lista (ed);
      return lista;
    }
    finally {
      this.fimTransacao (true);
    }
  }

  public ArrayList listaEDI_EntregaConhecimento (EDI_EntregaED ed) throws Excecoes {
    this.inicioTransacao ();
    ArrayList lista = null;
    try {
      lista = new EDI_EntregaBD (sql).listaEDI_EntregaConhecimento (ed);
      return lista;
    }
    finally {
      this.fimTransacao (true);
    }
  }

  public ArrayList gera_Entrega (EDI_EntregaED ed) throws Excecoes {
    this.inicioTransacao ();
    ArrayList lista = null;
    try {
      lista = new EDI_EntregaBD (sql).gera_Entrega (ed);
      return lista;
    }
    finally {
      this.fimTransacao (true);
    }
  }

  public EDI_EntregaED getByRecord (EDI_EntregaED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    EDI_EntregaED edVolta = new EDI_EntregaED ();
    //atribui ao ed de retorno
    edVolta = new EDI_EntregaBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

}
