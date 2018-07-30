package com.master.rn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.DuplicataBD;
import com.master.ed.DuplicataED;
import com.master.ed.DuplicataPesquisaED;
import com.master.ed.Tipo_EventoED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;

public class DuplicataRN
    extends Transacao {

  public DuplicataRN () {
  }

  public DuplicataED inclui (DuplicataED ed) throws Exception {

    try {
      SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
      Calendar DT_Vencimento = Calendar.getInstance ();
      Date DT_Emissao = formatter.parse (ed.getDt_Emissao ());
      Calendar DT_Emissao_Calendario = Calendar.getInstance ();
      DT_Emissao_Calendario.setTime (DT_Emissao);
      Date DT_Hoje = new Date ();
      Calendar DT_Hoje_Calendario = Calendar.getInstance ();
      DT_Hoje_Calendario.setTime (DT_Hoje);

      if (String.valueOf (ed.getDt_Vencimento ()) != null && !String.valueOf (ed.getDt_Vencimento ()).equals ("") && !String.valueOf (ed.getDt_Vencimento ()).equals ("null")) {
        Date Vencimento = formatter.parse (ed.getDt_Vencimento ());
        DT_Vencimento.setTime (Vencimento);
        if (DT_Emissao_Calendario.after (DT_Vencimento))
          throw new Mensagens ("Data de vencimento tem de ser menor ou igual a hoje!");
      }
      if (DT_Emissao_Calendario.after (DT_Hoje_Calendario))
        throw new Mensagens ("Data emissão tem de ser menor ou igual a data de hoje!");
      if ( (ed.getVl_Duplicata ()).doubleValue () <= 0.0)
        throw new Mensagens ("Valor da duplicata menor ou igual a zero!");
      if ( (ed.getVl_Desconto_Faturamento ()).doubleValue () < 0.0)
        throw new Mensagens ("Valor do desconto até vencimento menor ou igual a zero!");
      if ( (ed.getVL_Juro_Mora_Dia ()).doubleValue () < 0.0)
        throw new Mensagens ("Valor do juro mora dia mora dia menor ou igual a zero!");
      if ( (ed.getVL_Multa ()).doubleValue () < 0.0)
        throw new Mensagens ("Valor do multa após vencimento menor ou igual a zero!");
      if ( (ed.getVl_Taxa_Cobranca ()).doubleValue () < 0.0)
        throw new Mensagens ("Valor do taxa cobrança após vencimento menor ou igual a zero!");

      this.inicioTransacao ();
      ed = new DuplicataBD (this.sql).inclui (ed);
      this.fimTransacao (true);
      return ed;
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "inclui()");
    }
    catch (Exception e) {
      this.abortaTransacao ();
      throw e;
    }
  }

  public DuplicataED calculaSaldo (DuplicataED ed) throws Exception {

    try {
      // System.out.println ("calculaSaldo RN 1");

      this.inicioTransacao ();
      ed = new DuplicataBD (this.sql).calculaSaldo (ed);
      this.fimTransacao (true);
      return ed;
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "calculaSaldo()");
    }
    catch (Exception e) {
      this.abortaTransacao ();
      throw e;
    }
  }

  public void altera (DuplicataED ed) throws Exception {

    try {
      SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
      Calendar DT_Vencimento = Calendar.getInstance ();
      Date DT_Emissao = formatter.parse (ed.getDt_Emissao ());
      Calendar DT_Emissao_Calendario = Calendar.getInstance ();
      DT_Emissao_Calendario.setTime (DT_Emissao);
      Date DT_Hoje = new Date ();
      Calendar DT_Hoje_Calendario = Calendar.getInstance ();
      DT_Hoje_Calendario.setTime (DT_Hoje);

      if (String.valueOf (ed.getDt_Vencimento ()) != null && !String.valueOf (ed.getDt_Vencimento ()).equals ("") && !String.valueOf (ed.getDt_Vencimento ()).equals ("null")) {
        Date Vencimento = formatter.parse (ed.getDt_Vencimento ());
        DT_Vencimento.setTime (Vencimento);
        if (DT_Emissao_Calendario.after (DT_Vencimento))
          throw new Mensagens ("Data de vencimento tem de ser menor ou igual a hoje!");
      }
      if (DT_Emissao_Calendario.after (DT_Hoje_Calendario))
        throw new Mensagens ("Data emissão tem de ser menor ou igual a data de hoje!");
      if ( (ed.getVl_Desconto_Faturamento ()).doubleValue () < 0.0)
        throw new Mensagens ("Valor do desconto até vencimento menor ou igual a zero!");
      if ( (ed.getVL_Juro_Mora_Dia ()).doubleValue () < 0.0)
        throw new Mensagens ("Valor do juro mora dia mora dia menor ou igual a zero!");
      if ( (ed.getVL_Multa ()).doubleValue () < 0.0)
        throw new Mensagens ("Valor do multa após vencimento menor ou igual a zero!");
      if ( (ed.getVl_Taxa_Cobranca ()).doubleValue () < 0.0)
        throw new Mensagens ("Valor do taxa cobrança após vencimento menor ou igual a zero!");

      this.inicioTransacao ();
      new DuplicataBD (this.sql).altera (ed);
      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera()");
    }
    catch (Exception e) {
      this.abortaTransacao ();
      throw e;
    }
  }

  public void deleta (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      new DuplicataBD (this.sql).deleta (ed);
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

  public DuplicataED incluiParcela (DuplicataED ed) throws Excecoes {

    try {
      //if (Data.comparaData(ed.getDt_Vencimento(), Data.getDataDMY()))
      //  throw new Mensagens("Data de Vencimento tem de ser menor ou igual a Hoje!");
      // if (Data.comparaData(ed.getDt_Emissao(), Data.getDataDMY()))
      //   throw new Mensagens("Data Emissão tem de ser menor ou igual a data de Hoje!");
      if (ed.getVl_Duplicata ().doubleValue () <= 0.0)
        throw new Mensagens ("Valor do Duplicata menor ou igual a zero!");

      this.inicioTransacao ();
      ed = new DuplicataBD (this.sql).incluiParcela (ed);
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

  public DuplicataED excluiParcela (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      ed = new DuplicataBD (this.sql).excluiParcela (ed);
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

  public DuplicataED cancelaParcela (DuplicataED ed) throws Excecoes {

	    try {
	      this.inicioTransacao ();
	      ed = new DuplicataBD (this.sql).cancelaParcela (ed);
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
  
  public ArrayList listaParcela (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataBD (sql).listaParcela (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public ArrayList lista (DuplicataED ed) throws Exception {

    try {
      this.inicioTransacao ();
      return new DuplicataBD (sql).lista (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public ArrayList listaByNota (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataBD (sql).listaByNota (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public ArrayList duplicata_ListaByMoeda (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataBD (sql).duplicata_ListaByMoeda (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public DuplicataED getByRecord (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataBD (this.sql).getByRecord (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public DuplicataED getByRecordByMoeda (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataBD (this.sql).getByRecordByMoeda (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public void geraRelatorio (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
//      new DuplicataBD (this.sql).geraRelatorio (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public ArrayList GeraEDI_Cobranca (Tipo_EventoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataBD (sql).GeraEDI_Cobranca (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public ArrayList GeraMinuta_Protocolo_Entrega (DuplicataED ed) throws Excecoes {

	    try {
	      this.inicioTransacao ();
	      return new DuplicataBD (sql).GeraMinuta_Protocolo_Entrega (ed);
	    }
	    finally {
	      this.fimTransacao (true);
	    }
	  }

  public ArrayList GeraEDI_Cobranca_Nota_Servico (Tipo_EventoED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataBD (sql).GeraEDI_Cobranca_Nota_Servico (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public DuplicataED getByRecordCTRC (DuplicataED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      return new DuplicataBD (this.sql).getByRecordCTRC (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  protected void finalize () throws Throwable {
    if (this.sql != null)
      this.abortaTransacao ();
  }

}
