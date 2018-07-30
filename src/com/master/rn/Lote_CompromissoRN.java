package com.master.rn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.master.bd.CompromissoBD;
import com.master.bd.Lote_CompromissoBD;
import com.master.ed.CompromissoED;
import com.master.ed.Lote_CompromissoED;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;

public class Lote_CompromissoRN
    extends Transacao {

  public Lote_CompromissoRN () {
  }

  public Lote_CompromissoED inclui (Lote_CompromissoED ed) throws Exception {
    try {
      this.inicioTransacao ();
      ed = new Lote_CompromissoBD (this.sql).inclui (ed);
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

  public void altera (Lote_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      new Lote_CompromissoBD (this.sql).altera (ed);
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

  public void deleta (Lote_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      new Lote_CompromissoBD (this.sql).deleta (ed);
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

  public void estorna_Pagamento (Lote_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      new Lote_CompromissoBD (this.sql).estorna_Pagamento (ed);
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

  
  public ArrayList lista (Lote_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Lote_CompromissoBD (sql).lista (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public Lote_CompromissoED getByRecord (Lote_CompromissoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Lote_CompromissoBD (this.sql).getByRecord (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public void subtraiSaldo (CompromissoED ed) throws Excecoes {
    new CompromissoBD (this.sql).subtraiSaldo (ed);
  }

  protected void finalize () throws Throwable {
    if (this.sql != null)
      this.abortaTransacao ();
  }
}