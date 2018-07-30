package com.master.rn;

/**
 * @author Andre Valadas
 */

import java.util.ArrayList;

import com.master.bd.Parcelamento_PedidoBD;
import com.master.ed.Parcelamento_PedidoED;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;

public class Parcelamento_PedidoRN
    extends Transacao {

  public Parcelamento_PedidoRN () {
  }

  public void inclui (Parcelamento_PedidoED ed) throws Excecoes {

    if (ed.getVL_Parcela () <= 0) {
      throw new Mensagens ("Valor da Parcela não pode igual ou menor que Zero!");
    }
    try {
      this.inicioTransacao ();
      new Parcelamento_PedidoBD (this.sql).inclui (ed);
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

  public String gera_Parcelamento (Parcelamento_PedidoED ed) throws Excecoes {
	  	String retorno="";
	    try {
	      this.inicioTransacao ();
	      retorno = new Parcelamento_PedidoBD (this.sql).gera_Parcelamento (ed);
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
	    return retorno;
	  }


  public String altera (Parcelamento_PedidoED ed) throws Excecoes {

	String retorno="";
    try {
      this.inicioTransacao ();
      retorno = new Parcelamento_PedidoBD (this.sql).altera (ed);
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
    return retorno;
  }

  public void deleta (Parcelamento_PedidoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      new Parcelamento_PedidoBD (this.sql).deleta (ed);
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

  public ArrayList lista (Parcelamento_PedidoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Parcelamento_PedidoBD (this.sql).lista (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public Parcelamento_PedidoED getByRecord (Parcelamento_PedidoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Parcelamento_PedidoBD (this.sql).getByRecord (ed);
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