package com.master.rn;

import java.util.ArrayList;

import com.master.bd.WMS_LocalizacaoBD;
import com.master.ed.WMS_LocalizacaoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class WMS_LocalizacaoRN
    extends Transacao {

  public WMS_LocalizacaoRN () {
  }

  public WMS_LocalizacaoED inclui (WMS_LocalizacaoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      ed = new WMS_LocalizacaoBD (this.sql).inclui (ed);
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

  public WMS_LocalizacaoED altera (WMS_LocalizacaoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      ed = new WMS_LocalizacaoBD (this.sql).altera (ed);
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

  public void deleta (WMS_LocalizacaoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      new WMS_LocalizacaoBD (this.sql).deleta (ed);
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

  public WMS_LocalizacaoED getByRecord (WMS_LocalizacaoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new WMS_LocalizacaoBD (this.sql).getByRecord (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public WMS_LocalizacaoED getByOid_Localizacao (WMS_LocalizacaoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new WMS_LocalizacaoBD (this.sql).getByOid_Localizacao (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public WMS_LocalizacaoED getByOid_Deposito (WMS_LocalizacaoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new WMS_LocalizacaoBD (this.sql).getByOid_Deposito (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public ArrayList lista (WMS_LocalizacaoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new WMS_LocalizacaoBD (sql).lista (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  protected void finalize () throws Throwable {
    super.finalize ();
    if (this.sql != null)
      this.abortaTransacao ();
  }
}