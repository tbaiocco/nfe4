package com.master.rn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.DuplicataRelBD;
import com.master.ed.DuplicataED;
import com.master.ed.DuplicataPesquisaED;
import com.master.ed.Tipo_EventoED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;

public class DuplicataRelRN
    extends Transacao {

  public DuplicataRelRN () {
  }

  public byte[] geraRelTitVencUnidade (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataRelBD (this.sql).geraRelTitVencUnidade (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public byte[] geraRelTitulos (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataRelBD (this.sql).geraRelTitulos (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public ArrayList geraEdiRelTitulos (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataRelBD (this.sql).geraEdiRelTitulos (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public byte[] geraDiario_Auxiliar_Clientes (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();

      byte[] b = new DuplicataRelBD (this.sql).geraDiario_Auxiliar_Clientes (ed);
      this.fimTransacao (true);
      return b;
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw e;
    }
  }

  public byte[] geraRelTitEmitidosPeriodo (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataRelBD (this.sql).geraRelTitEmitidosPeriodo (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public byte[] geraRelTitLiquidadosCart (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataRelBD (this.sql).geraRelTitLiquidadosCart (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public byte[] geraRelTitulosDespesaCobranca (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataRelBD (this.sql).geraRelTitulosDespesaCobranca (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public byte[] geraRelTitCliente (DuplicataPesquisaED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataRelBD (this.sql).geraRelTitCliente (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public byte[] geraRelFatura (DuplicataPesquisaED ed) throws Exception {
    byte [] b = null;
    this.inicioTransacao ();
    try {
      if ("2".equals (ed.getDM_Tipo_Documento ()) && "JASPER".equals (ed.getDM_Relatorio ())) {
        new DuplicataRelBD (this.sql).imprimeFatura (ed);
        return null;
      }
      else if ("1".equals (ed.getDM_Tipo_Documento ()) && "JASPER".equals (ed.getDM_Relatorio ())) {
        new DuplicataRelBD (this.sql).imprimeFaturaCto (ed);
        return null;
      }
      else if ("XSL".equals (ed.getDM_Relatorio ())) {
        return new DuplicataRelBD(this.sql).geraRelFatura(ed);
      }
      return b;
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public byte[] geraDemonstrativo_Cobranca (DuplicataED ed) throws Excecoes {

	    try {
	      this.inicioTransacao ();
	      return new DuplicataRelBD (this.sql).geraDemonstrativo_Cobranca (ed);
	    }
	    finally {
	      this.fimTransacao (false);
	    }
  }


  public String imprime_Fatura_Matricial (DuplicataPesquisaED ed) throws Exception {
	  	try {
	      this.inicioTransacao ();
	      return new DuplicataRelBD (this.sql).imprime_Fatura_Matricial (ed);
	    }
	    finally {
	      this.fimTransacao (false);
	    }
  }

  public byte[] imprime_Fatura_PDF (DuplicataPesquisaED ed) throws Excecoes {
	  try {
		  this.inicioTransacao ();
		  return new DuplicataRelBD (this.sql).imprime_Fatura_PDF (ed);
	  }
	  finally {
		  this.fimTransacao (false);
	  }
  }

  public void geraProtocoloJasper (DuplicataPesquisaED ed) throws Exception {
	    this.inicioTransacao ();
	    try {
	      new DuplicataRelBD (this.sql).ProtocoloJasper(ed);

	    }
	    finally {
	      this.fimTransacao (false);
	    }
	  }

  public void ReciboAdiantamentoJasper (DuplicataPesquisaED ed) throws Exception {
	    this.inicioTransacao ();
	    try {
	      new DuplicataRelBD (this.sql).ReciboAdiantamentoJasper(ed);

	    }
	    finally {
	      this.fimTransacao (false);
	    }
	  }

  public byte[] geraProtocolo_Cobranca (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataRelBD (this.sql).geraProtocolo_Cobranca (ed);
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

  public byte[] geraRelTitEDI (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataRelBD (this.sql).geraRelTitEDI (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public void geraDiario_Razao_Clientes (DuplicataED ed , HttpServletResponse response) throws Excecoes {

    try {
      this.inicioTransacao ();
      new DuplicataRelBD (this.sql).geraDiario_Razao_Clientes (ed , response);
      this.fimTransacao (true);
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw e;
    }
  }
}
