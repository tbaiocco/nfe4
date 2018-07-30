package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Fluxo_CaixaBD;
import com.master.ed.Fluxo_CaixaED;
import com.master.ed.ProdutoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Fluxo_CaixaRN
    extends Transacao {

  Fluxo_CaixaBD Fluxo_CaixaBD = null;

  public Fluxo_CaixaRN () {
  }

  public Fluxo_CaixaED inclui (Fluxo_CaixaED ed) throws Excecoes {

    Fluxo_CaixaED Fluxo_CaixaED = new Fluxo_CaixaED ();

    try {
      this.inicioTransacao ();
      Fluxo_CaixaBD = new Fluxo_CaixaBD (this.sql);
      Fluxo_CaixaED = Fluxo_CaixaBD.inclui (ed);
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
    return Fluxo_CaixaED;
  }

  public Fluxo_CaixaED atualiza (Fluxo_CaixaED ed) throws Excecoes {

    Fluxo_CaixaED Fluxo_CaixaED = new Fluxo_CaixaED ();

    try {
      this.inicioTransacao ();
      Fluxo_CaixaBD = new Fluxo_CaixaBD (this.sql);
      Fluxo_CaixaED = Fluxo_CaixaBD.atualiza (ed);
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
    return Fluxo_CaixaED;
  }

  public Fluxo_CaixaED consultaSaldo (Fluxo_CaixaED ed) throws Excecoes {

    Fluxo_CaixaED Fluxo_CaixaED = new Fluxo_CaixaED ();

    try {
      this.inicioTransacao ();
      Fluxo_CaixaBD = new Fluxo_CaixaBD (this.sql);
      Fluxo_CaixaED = Fluxo_CaixaBD.consultaSaldo (ed);
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
    return Fluxo_CaixaED;
  }

  public void altera (Fluxo_CaixaED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      Fluxo_CaixaBD = new Fluxo_CaixaBD (this.sql);
      Fluxo_CaixaBD.altera (ed);
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

  public void liberaBloqueia (Fluxo_CaixaED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      Fluxo_CaixaBD = new Fluxo_CaixaBD (this.sql);
      Fluxo_CaixaBD.liberaBloqueia (ed);
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

  public void deleta (Fluxo_CaixaED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      Fluxo_CaixaBD = new Fluxo_CaixaBD (this.sql);
      Fluxo_CaixaBD.deleta (ed);
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

  public ArrayList lista (Fluxo_CaixaED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Fluxo_CaixaBD (sql).lista (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public Fluxo_CaixaED getByRecord (Fluxo_CaixaED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Fluxo_CaixaBD (this.sql).getByRecord (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public byte[] imprime_Fluxo_Caixa (Fluxo_CaixaED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Fluxo_CaixaBD (this.sql).imprime_Fluxo_Caixa (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  protected void finalize () throws Throwable {
    if (this.sql != null) {
      this.abortaTransacao ();
    }
  }
}