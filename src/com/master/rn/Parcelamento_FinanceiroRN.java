package com.master.rn;

/**
 * @author Andre Valadas
 */

import java.util.ArrayList;

import com.master.bd.CompromissoBD;
import com.master.bd.Parcelamento_FinanceiroBD;
import com.master.ed.CompromissoED;
import com.master.ed.Parcelamento_FinanceiroED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;

public class Parcelamento_FinanceiroRN
    extends Transacao {

  public Parcelamento_FinanceiroRN () {
  }

  public void inclui (Parcelamento_FinanceiroED ed) throws Excecoes {

    if (ed.getVL_Parcela () <= 0) {
      throw new Mensagens ("Valor da Parcela não pode igual ou menor que Zero!");
    }
    try {
      this.inicioTransacao ();
      new Parcelamento_FinanceiroBD (this.sql).inclui (ed);
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

  public String altera (Parcelamento_FinanceiroED ed) throws Excecoes {

	String retorno="";
    try {
      this.inicioTransacao ();
      retorno = new Parcelamento_FinanceiroBD (this.sql).altera (ed);
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

  public void deleta (Parcelamento_FinanceiroED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      new Parcelamento_FinanceiroBD (this.sql).deleta (ed);
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

  public ArrayList lista (Parcelamento_FinanceiroED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Parcelamento_FinanceiroBD (this.sql).lista (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public Parcelamento_FinanceiroED getByRecord (Parcelamento_FinanceiroED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Parcelamento_FinanceiroBD (this.sql).getByRecord (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public double checkSoma (String nota) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Parcelamento_FinanceiroBD (sql).checkSoma (nota);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public double vlTotalParcelas (String oid_Nota_Fiscal) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Parcelamento_FinanceiroBD (sql).vlTotalParcelas (oid_Nota_Fiscal);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public Parcelamento_FinanceiroED getByRecord_Compra (Parcelamento_FinanceiroED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new Parcelamento_FinanceiroBD (this.sql).getByRecord_Compra (ed);
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