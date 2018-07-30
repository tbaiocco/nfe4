package com.master.rn;

import java.util.ArrayList;
import com.master.bd.Fatura_CompromissoBD;
import com.master.ed.Fatura_CompromissoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Fatura_CompromissoRN
    extends Transacao {
  public Fatura_CompromissoRN () {
  }

  public Fatura_CompromissoED inclui (Fatura_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      ed = new Fatura_CompromissoBD (this.sql).inclui (ed);
      this.fimTransacao (true);
      return ed;
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw e;
    }
  }

  public void altera (Fatura_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      new Fatura_CompromissoBD (this.sql).altera (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw e;
    }
  }

  public void incluiCompromissoVinculado (Fatura_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      new Fatura_CompromissoBD (this.sql).incluiCompromissoVinculado (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw e;
    }
  }

  public void deleta (Fatura_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      new Fatura_CompromissoBD (this.sql).deleta (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw e;
    }
  }

  public void desvinculaTudo (Fatura_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      new Fatura_CompromissoBD (this.sql).desvinculaTudo (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw e;
    }
  }


  public void excluiCompromisso (Fatura_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      new Fatura_CompromissoBD (this.sql).excluiCompromisso (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw e;
    }
  }

  public ArrayList lista (Fatura_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Fatura_CompromissoBD (sql).lista (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public ArrayList listaCompromissoVinculado (Fatura_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Fatura_CompromissoBD (sql).listaCompromissoVinculado (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public Fatura_CompromissoED getByRecord (Fatura_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Fatura_CompromissoBD (this.sql).getByRecord (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public byte[] imprime_Fatura_Compromisso (Fatura_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      byte[] b = new Fatura_CompromissoBD (this.sql).imprime_Fatura_Compromisso (ed);
      this.fimTransacao (true);
      return b;
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw e;
    }
  }

  protected void finalize () throws Throwable {
    if (this.sql != null) {
      this.abortaTransacao ();
    }
  }
}