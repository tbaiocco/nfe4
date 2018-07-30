package com.master.rn;

import java.util.ArrayList;

import com.master.bd.WMS_DepositoBD;
import com.master.ed.WMS_DepositoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class WMS_DepositoRN
    extends Transacao {

  WMS_DepositoBD WMS_DepositoBD = null;

  public WMS_DepositoRN () {
  }

  public WMS_DepositoED inclui (WMS_DepositoED ed) throws Excecoes {

    this.inicioTransacao ();
    WMS_DepositoBD = new WMS_DepositoBD (this.sql);
    ed = WMS_DepositoBD.inclui (ed);
    this.fimTransacao (true);

    return ed;
  }

  public void altera (WMS_DepositoED ed) throws Excecoes {

    this.inicioTransacao ();
    WMS_DepositoBD = new WMS_DepositoBD (this.sql);
    WMS_DepositoBD.altera (ed);
    this.fimTransacao (true);

  }

  public void deleta (WMS_DepositoED ed) throws Excecoes {

    this.inicioTransacao ();
    WMS_DepositoBD = new WMS_DepositoBD (this.sql);
    WMS_DepositoBD.deleta (ed);
    this.fimTransacao (true);

  }

  public ArrayList lista (WMS_DepositoED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new WMS_DepositoBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }

  public WMS_DepositoED getByRecord (WMS_DepositoED ed) throws Excecoes {

    this.inicioTransacao ();
    ed = new WMS_DepositoBD (this.sql).getByRecord (ed);
    this.fimTransacao (false);

    return ed;
  }

  public WMS_DepositoED getByCD_Deposito (String CD_Deposito) throws Excecoes {

    this.inicioTransacao ();
    WMS_DepositoED edVolta = new WMS_DepositoED ();
    edVolta = new WMS_DepositoBD (this.sql).getByCD_Deposito (CD_Deposito);
    this.fimTransacao (false);

    return edVolta;
  }

  public void geraRelatorio (WMS_DepositoED ed) throws Excecoes {

    this.inicioTransacao ();
    WMS_DepositoBD = new WMS_DepositoBD (this.sql);
    WMS_DepositoBD.geraRelatorio (ed);
    this.fimTransacao (false);
  }

  protected void finalize () throws Throwable {
    super.finalize ();
    if (this.sql != null) {
      this.abortaTransacao ();
    }
  }
}