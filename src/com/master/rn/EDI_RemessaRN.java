package com.master.rn;

import java.util.*;

import com.master.bd.*;
import com.master.ed.*;
import com.master.util.*;
import com.master.util.bd.*;

public class EDI_RemessaRN
    extends Transacao {
  EDI_RemessaBD EDI_RemessaBD = null;

  public EDI_RemessaRN () {

  }

  public void deleta (EDI_RemessaED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      EDI_RemessaBD = new EDI_RemessaBD (this.sql);
      EDI_RemessaBD.deleta (ed);

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

    public void deleta_Lista(ArrayList lista) throws Excecoes {

        try {
            this.inicioTransacao();
            new EDI_RemessaBD(this.sql).deleta_Lista(lista);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void calcula_Lista(ArrayList lista) throws Excecoes {

        try {
            this.inicioTransacao();
            new EDI_RemessaBD(this.sql).calcula_Lista(lista);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }


  public ArrayList lista (EDI_RemessaED ed) throws Excecoes {
    this.inicioTransacao ();
    ArrayList lista = null;
    try {
      lista = new EDI_RemessaBD (sql).lista (ed);
      return lista;
    }
    finally {
      this.fimTransacao (true);
    }
  }

  public ArrayList listaEDI_RemessaConhecimento (EDI_RemessaED ed) throws Excecoes {
    this.inicioTransacao ();
    ArrayList lista = null;
    try {
      lista = new EDI_RemessaBD (sql).listaEDI_RemessaConhecimento (ed);
      return lista;
    }
    finally {
      this.fimTransacao (true);
    }
  }

  public ArrayList gera_Remessa (EDI_RemessaED ed) throws Excecoes {
    this.inicioTransacao ();
    ArrayList lista = null;
    try {
      lista = new EDI_RemessaBD (sql).gera_Remessa (ed);
      return lista;
    }
    finally {
      this.fimTransacao (true);
    }
  }

  public EDI_RemessaED getByRecord (EDI_RemessaED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    EDI_RemessaED edVolta = new EDI_RemessaED ();
    //atribui ao ed de retorno
    edVolta = new EDI_RemessaBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

}
