package com.master.rn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.ConhecimentoBD;
import com.master.bd.Ocorrencia_ConhecimentoBD;
import com.master.bd.Programacao_CargaBD;
import com.master.ed.ConhecimentoED;
import com.master.ed.Ocorrencia_ConhecimentoED;
import com.master.ed.Programacao_CargaED;
import com.master.rl.ConhecimentoRL;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

public class ConhecimentoRN
    extends Transacao {

  ConhecimentoBD ConhecimentoBD = null;
  Ocorrencia_ConhecimentoBD Ocorrencia_ConhecimentoBD = null;

  public ConhecimentoRN () {
  }

  public ConhecimentoED inclui (ConhecimentoED ed) throws Excecoes {

    // System.out.println (" inclui RN ");

    ConhecimentoED conED = new ConhecimentoED ();
    if (!JavaUtil.doValida (ed.getOID_Pessoa ())) {
      throw new Mensagens ("Código do Conhecimento não foi informado!");
    }
    try {
	    SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
	    Calendar DT_Vencimento = Calendar.getInstance ();
	    Date DT_Emissao = formatter.parse(ed.getDT_Emissao ());
	    Calendar DT_Emissao_Calendario = Calendar.getInstance ();
	    DT_Emissao_Calendario.setTime (DT_Emissao);
	    Date DT_Hoje = new Date ();
	    Calendar DT_Hoje_Calendario = Calendar.getInstance ();
	    DT_Hoje_Calendario.setTime (DT_Hoje);

	    Calendar DT_Previsao = Calendar.getInstance ();
	    if (String.valueOf (ed.getDT_Previsao_Entrega()) != null &&
	    		!String.valueOf (ed.getDT_Previsao_Entrega ()).equals ("") &&
	    		!String.valueOf (ed.getDT_Previsao_Entrega ()).equals ("null")) {
	      Date Previsao = formatter.parse (ed.getDT_Previsao_Entrega ());
	      DT_Previsao.setTime (Previsao);
	      if (DT_Emissao_Calendario.after (DT_Previsao))
	        throw new Mensagens ("Data de Previsao tem de ser maior ou igual a data de emissão do CTRC!");
	    }
    }
    catch (Excecoes exc) {
      exc.printStackTrace ();
//      abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
//      abortaTransacao ();
      throw e;
    }
    catch (Exception e) {
//        abortaTransacao ();
        throw new Excecoes("msg de erro...");
      }

    inicioTransacao ();
    try {
      ConhecimentoBD conhecimentoBD = new ConhecimentoBD (sql);
      if (ed.getNR_Conhecimento () > 0) {
        ConhecimentoED edValida = new ConhecimentoED ();
        edValida.setOID_Unidade (ed.getOID_Unidade ());
        edValida.setNR_Conhecimento (ed.getNR_Conhecimento ());
        edValida.setNM_Serie (ed.getNM_Serie ());
        if (conhecimentoBD.existeConhecimento (edValida)) {
          throw new Mensagens ("Já existe um conhecimento com este número!");
        }
      }
      conED = conhecimentoBD.inclui (ed);
      if (JavaUtil.doValida (String.valueOf (ed.getOid_Programacao_Carga ()))) {
        Programacao_CargaED programacao = new Programacao_CargaED ();
        programacao.setOid_Programacao_Carga (ed.getOid_Programacao_Carga ());
        new Programacao_CargaBD (sql).vincula (programacao);
      }
      fimTransacao (true);
      return conED;
    }
    catch (Excecoes exc) {
      exc.printStackTrace ();
      abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  ///###
  public ConhecimentoED inclui_CTRC_Nota_Fiscal_Lote (ConhecimentoED ed) throws Excecoes {

    ConhecimentoED conED = new ConhecimentoED ();

    try {
      //// System.out.println(" inclui RN 1");
      this.inicioTransacao ();
      ConhecimentoBD = new ConhecimentoBD (this.sql);
      conED = ConhecimentoBD.inclui_CTRC_Nota_Fiscal_Lote (ed);
      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return conED;

  }

  public ConhecimentoED deleta_CTRC_Nota_Fiscal_Lote (ConhecimentoED ed) throws Excecoes {

    ConhecimentoED conED = new ConhecimentoED ();

    try {
      //// System.out.println(" inclui RN 1");
      this.inicioTransacao ();
      ConhecimentoBD = new ConhecimentoBD (this.sql);
      conED = ConhecimentoBD.deleta_CTRC_Nota_Fiscal_Lote (ed);
      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return conED;

  }

  public ConhecimentoED recalcula_CTRC_Nota_Fiscal_Lote (ConhecimentoED ed) throws Excecoes {

    ConhecimentoED conED = new ConhecimentoED ();

    try {
      //// System.out.println(" inclui RN 1");
      this.inicioTransacao ();
      ConhecimentoBD = new ConhecimentoBD (this.sql);
      conED = ConhecimentoBD.recalcula_CTRC_Nota_Fiscal_Lote (ed);
      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return conED;

  }

  public void altera (ConhecimentoED ed) throws Excecoes {
    if ("".equals (ed.getOID_Pessoa ())) {
      throw new Excecoes ("Pessoa não informada!");
    }
    try {
	    SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
	    Calendar DT_Vencimento = Calendar.getInstance ();
	    Date DT_Emissao = formatter.parse(ed.getDT_Emissao ());
	    Calendar DT_Emissao_Calendario = Calendar.getInstance ();
	    DT_Emissao_Calendario.setTime (DT_Emissao);
	    Date DT_Hoje = new Date ();
	    Calendar DT_Hoje_Calendario = Calendar.getInstance ();
	    DT_Hoje_Calendario.setTime (DT_Hoje);

	    Calendar DT_Previsao = Calendar.getInstance ();
	    if (String.valueOf (ed.getDT_Previsao_Entrega()) != null &&
	    		!String.valueOf (ed.getDT_Previsao_Entrega ()).equals ("") &&
	    		!String.valueOf (ed.getDT_Previsao_Entrega ()).equals ("null")) {
	      Date Previsao = formatter.parse (ed.getDT_Previsao_Entrega ());
	      DT_Previsao.setTime (Previsao);
	      if (DT_Emissao_Calendario.after (DT_Previsao))
	        throw new Mensagens ("Data de Previsao tem de ser maior ou igual a data de emissão do CTRC!");
	    }
    }
    catch (Excecoes exc) {
      exc.printStackTrace ();
      throw exc;
    }
    catch (RuntimeException e) {
      throw e;
    }
    catch (Exception e) {
        throw new Excecoes("msg de erro...");
    }

    try {
      this.inicioTransacao ();
      new ConhecimentoBD (this.sql).altera (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void altera_AWB (ConhecimentoED ed) throws Excecoes {
    if ("".equals (ed.getOID_Pessoa ())) {
      throw new Excecoes ("Pessoa não informada!");
    }
    try {
      this.inicioTransacao ();
      new ConhecimentoBD (this.sql).altera_AWB (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void altera_Minuta_Aereo (ConhecimentoED ed) throws Excecoes {
    if ("".equals (ed.getOID_Pessoa ())) {
      throw new Excecoes ("Pessoa não informada!");
    }
    try {
      this.inicioTransacao ();
      new ConhecimentoBD (this.sql).altera_Minuta_Aereo (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void altera_Cobranca (ConhecimentoED ed) throws Excecoes {
    if ("".equals (ed.getOID_Pessoa ())) {
      throw new Excecoes ("Pessoa não informada!");
    }
    try {
      this.inicioTransacao ();
      new ConhecimentoBD (this.sql).altera_Cobranca (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void atualiza_Minuta (ConhecimentoED ed) throws Excecoes {
    try {
      this.inicioTransacao ();
      new ConhecimentoBD (this.sql).atualiza_Minuta (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  public ConhecimentoED copia_Conhecimento (ConhecimentoED ed) throws Excecoes {
    ConhecimentoED conED = new ConhecimentoED ();
    inicioTransacao ();
    try {
      ConhecimentoBD conhecimentoBD = new ConhecimentoBD (sql);
      conED = conhecimentoBD.copia_Conhecimento (ed);
      fimTransacao (true);
      return conED;
    }
    catch (Excecoes exc) {
      exc.printStackTrace ();
      abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

   public void altera_Previsao_Entrega (ConhecimentoED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      ConhecimentoBD = new ConhecimentoBD (this.sql);
      ConhecimentoBD.altera_Previsao_Entrega (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void altera_Total_Frete (ConhecimentoED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      ConhecimentoBD = new ConhecimentoBD (this.sql);
      ConhecimentoBD.altera_Total_Frete (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void altera_Fiscal (ConhecimentoED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      ConhecimentoBD = new ConhecimentoBD (this.sql);
      ConhecimentoBD.altera_Fiscal (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void solicita_Cotacao (ConhecimentoED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      ConhecimentoBD = new ConhecimentoBD (this.sql);
      ConhecimentoBD.solicita_Cotacao (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void solicita_Coleta_Eletronica (ConhecimentoED ed) throws Excecoes {

	    try {

	      this.inicioTransacao ();

	      ConhecimentoBD = new ConhecimentoBD (this.sql);
	      ConhecimentoBD.solicita_Coleta_Eletronica (ed);

	      this.fimTransacao (true);

	    }
	    catch (Excecoes exc) {
	      this.abortaTransacao ();
	      throw exc;
	    }

	    catch (Exception e) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao alterar");
	      excecoes.setMetodo ("solicita_Coleta_Eletronica");
	      excecoes.setExc (e);
	      //faz rollback pois deu algum erro
	      this.abortaTransacao ();

	      throw excecoes;
	    }
	  }

  public void confirma_Coleta_Eletronica (ConhecimentoED ed, String recebida_confirmada) throws Excecoes {

	    try {

	      this.inicioTransacao ();

	      ConhecimentoBD = new ConhecimentoBD (this.sql);
	      ConhecimentoBD.confirma_Coleta_Eletronica (ed, recebida_confirmada);

	      this.fimTransacao (true);

	    }
	    catch (Excecoes exc) {
	      this.abortaTransacao ();
	      throw exc;
	    }

	    catch (Exception e) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao alterar");
	      excecoes.setMetodo ("confirma_Coleta_Eletronica");
	      excecoes.setExc (e);
	      //faz rollback pois deu algum erro
	      this.abortaTransacao ();

	      throw excecoes;
	    }
	  }

  public void altera_Custo (ConhecimentoED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      ConhecimentoBD = new ConhecimentoBD (this.sql);
      ConhecimentoBD.altera_Custo (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void altera_Pagador (ConhecimentoED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      ConhecimentoBD = new ConhecimentoBD (this.sql);
      ConhecimentoBD.altera_Pagador (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void altera_Entregador (ConhecimentoED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      ConhecimentoBD = new ConhecimentoBD (this.sql);
      ConhecimentoBD.altera_Entregador (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void altera_Situacao (ConhecimentoED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      ConhecimentoBD = new ConhecimentoBD (this.sql);
      ConhecimentoBD.altera_Situacao (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void altera_Rota_Entrega (ConhecimentoED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      ConhecimentoBD = new ConhecimentoBD (this.sql);
      ConhecimentoBD.altera_Rota_Entrega (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void altera_Placas (ConhecimentoED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      ConhecimentoBD = new ConhecimentoBD (this.sql);
      ConhecimentoBD.altera_Placas (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("altera_Placas");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void altera_Vendedor (ConhecimentoED ed) throws Excecoes {

    if (ed.getOID_Vendedor ().compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Vendedor não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      ConhecimentoBD = new ConhecimentoBD (this.sql);
      ConhecimentoBD.altera_Vendedor (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void deleta (ConhecimentoED ed) throws Excecoes {

    if (1 == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Conhecimento não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      ConhecimentoBD = new ConhecimentoBD (this.sql);
      ConhecimentoBD.deleta (ed);

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

  public String cancela (ConhecimentoED ed) throws Excecoes {

	String nm_situacao="";
    try {

      this.inicioTransacao ();

      Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

      ConhecimentoBD = new ConhecimentoBD (this.sql);

      // System.out.println ("cancela rn 1 ");
      nm_situacao = ConhecimentoBD.cancela (ed);

      if ("OK".equals(nm_situacao)) {
      // System.out.println ("cancela rn 2 ");

	      Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED ();
	      ocorrencia_ConhecimentoED.setDT_Ocorrencia_Conhecimento (Data.getDataDMY ());
	      ocorrencia_ConhecimentoED.setHR_Ocorrencia_Conhecimento (Data.getHoraHM ());
	      // System.out.println ("cancela rn 3 ");

	      ocorrencia_ConhecimentoED.setOID_Tipo_Ocorrencia (parametro_FixoED.getOID_Tipo_Ocorrencia_Cancelamento_CTRC ());
	      ocorrencia_ConhecimentoED.setOID_Conhecimento (ed.getOID_Conhecimento ());

	      // System.out.println ("cancela rn 4 ");

	      ocorrencia_ConhecimentoED.setTX_Observacao (ed.getTX_Observacao ());
	      ocorrencia_ConhecimentoED.setNM_Pessoa_Entrega (" ");
	      ocorrencia_ConhecimentoED.setDM_Tipo ("");
	      ocorrencia_ConhecimentoED.setDM_Acesso ("N");
	      ocorrencia_ConhecimentoED.setDM_Avaria ("N");
	      ocorrencia_ConhecimentoED.setDM_Status ("");

	      // System.out.println ("cancela rn 5 ");

	      Ocorrencia_ConhecimentoBD = new Ocorrencia_ConhecimentoBD (this.sql);
	      Ocorrencia_ConhecimentoBD.inclui (ocorrencia_ConhecimentoED);
      }
      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao cancelar");
      excecoes.setMetodo ("cancela");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
    return nm_situacao;
  }

  public void zeraCTRC (ConhecimentoED ed) throws Excecoes {

	    try {

	      this.inicioTransacao ();

	      Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

	      ConhecimentoBD = new ConhecimentoBD (this.sql);

	      // System.out.println ("cancela rn 1 ");
	      ConhecimentoBD.zeraCTRC (ed);
	      // System.out.println ("cancela rn 2 ");

	      Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED ();
	      ocorrencia_ConhecimentoED.setDT_Ocorrencia_Conhecimento (Data.getDataDMY ());
	      ocorrencia_ConhecimentoED.setHR_Ocorrencia_Conhecimento (Data.getHoraHM ());
	      // System.out.println ("cancela rn 3 ");

	      ocorrencia_ConhecimentoED.setOID_Tipo_Ocorrencia (parametro_FixoED.getOID_Tipo_Ocorrencia_Cancelamento_CTRC ());
	      ocorrencia_ConhecimentoED.setOID_Conhecimento (ed.getOID_Conhecimento ());

	      // System.out.println ("cancela rn 4 ");

	      ocorrencia_ConhecimentoED.setTX_Observacao (ed.getTX_Observacao ());
	      ocorrencia_ConhecimentoED.setNM_Pessoa_Entrega (" ");
	      ocorrencia_ConhecimentoED.setDM_Tipo ("");
	      ocorrencia_ConhecimentoED.setDM_Acesso ("N");
	      ocorrencia_ConhecimentoED.setDM_Avaria ("N");
	      ocorrencia_ConhecimentoED.setDM_Status ("");

	      // System.out.println ("cancela rn 5 ");

	      Ocorrencia_ConhecimentoBD = new Ocorrencia_ConhecimentoBD (this.sql);
	      Ocorrencia_ConhecimentoBD.inclui (ocorrencia_ConhecimentoED);

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

  public void alteraMinuta (ConhecimentoED ed) throws Excecoes {

	    try {

	      this.inicioTransacao ();

	      Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

	      ConhecimentoBD = new ConhecimentoBD (this.sql);

//	      ConhecimentoBD.alteraMinuta (ed);

	      Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED ();
	      ocorrencia_ConhecimentoED.setDT_Ocorrencia_Conhecimento (Data.getDataDMY ());
	      ocorrencia_ConhecimentoED.setHR_Ocorrencia_Conhecimento (Data.getHoraHM ());
	      ocorrencia_ConhecimentoED.setOID_Tipo_Ocorrencia (parametro_FixoED.getOID_Tipo_Ocorrencia_Cancelamento_CTRC ());
	      ocorrencia_ConhecimentoED.setOID_Conhecimento (ed.getOID_Conhecimento ());
	      ocorrencia_ConhecimentoED.setTX_Observacao (ed.getTX_Observacao ());
	      ocorrencia_ConhecimentoED.setNM_Pessoa_Entrega (" ");
	      ocorrencia_ConhecimentoED.setDM_Tipo ("");
	      ocorrencia_ConhecimentoED.setDM_Acesso ("N");
	      ocorrencia_ConhecimentoED.setDM_Avaria ("N");
	      ocorrencia_ConhecimentoED.setDM_Status ("");

	      Ocorrencia_ConhecimentoBD = new Ocorrencia_ConhecimentoBD (this.sql);
	      Ocorrencia_ConhecimentoBD.inclui (ocorrencia_ConhecimentoED);

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

  public String reentrega_Devolucao (ConhecimentoED ed) throws Excecoes {

    String oid_Conhecimento = "";
    if (1 == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Conhecimento não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

      ConhecimentoBD = new ConhecimentoBD (this.sql);

      // System.out.println ("reentrega_Devolucao rn 1 ");
      oid_Conhecimento = ConhecimentoBD.reentrega_Devolucao (ed);
      // System.out.println ("reentrega_Devolucao rn 2 ");

      Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED ();
      ocorrencia_ConhecimentoED.setDT_Ocorrencia_Conhecimento (Data.getDataDMY ());
      ocorrencia_ConhecimentoED.setHR_Ocorrencia_Conhecimento (Data.getHoraHM ());
      // System.out.println ("reentrega_Devolucao rn 3 ");

      if ("3".equals (ed.getDM_Tipo_Conhecimento ())) {
        ocorrencia_ConhecimentoED.setOID_Tipo_Ocorrencia (parametro_FixoED.getOID_Tipo_Ocorrencia_Devolucao_CTRC ());
      }
      if ("4".equals (ed.getDM_Tipo_Conhecimento ())) {
        ocorrencia_ConhecimentoED.setOID_Tipo_Ocorrencia (parametro_FixoED.getOID_Tipo_Ocorrencia_Reentrega_CTRC ());
      }

      ocorrencia_ConhecimentoED.setOID_Conhecimento (ed.getOID_Conhecimento ());

      // System.out.println ("reentrega_Devolucao rn 4 ");

      ocorrencia_ConhecimentoED.setTX_Observacao (ed.getTX_Observacao ());
      ocorrencia_ConhecimentoED.setNM_Pessoa_Entrega (" ");
      ocorrencia_ConhecimentoED.setDM_Tipo ("");
      ocorrencia_ConhecimentoED.setDM_Acesso ("N");
      ocorrencia_ConhecimentoED.setDM_Avaria ("N");
      ocorrencia_ConhecimentoED.setDM_Status ("");

      // System.out.println ("reentrega_Devolucao rn 5 ");

      Ocorrencia_ConhecimentoBD = new Ocorrencia_ConhecimentoBD (this.sql);
      Ocorrencia_ConhecimentoBD.inclui (ocorrencia_ConhecimentoED);

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
    return oid_Conhecimento;
  }

  public ArrayList lista_Fora_Intervalo (ConhecimentoED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new ConhecimentoBD (sql).lista_Fora_Intervalo (ed);
    this.fimTransacao (false);
    return lista;
  }

  public ArrayList lista (ConhecimentoED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new ConhecimentoBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }

  public ConhecimentoED getByRecord (ConhecimentoED ed) throws Excecoes {
    ConhecimentoED edVolta = new ConhecimentoED ();
    try {
      this.inicioTransacao ();
      edVolta = new ConhecimentoBD (this.sql).getByRecord (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
    return edVolta;
  }

  public ConhecimentoED getByAWB (ConhecimentoED ed) throws Excecoes {
    ConhecimentoED edVolta = new ConhecimentoED ();
    try {
      this.inicioTransacao ();
      edVolta = new ConhecimentoBD (this.sql).getByAWB (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
    return edVolta;
  }

  public ConhecimentoED getByControle_Entrega (ConhecimentoED ed) throws Excecoes {
    ConhecimentoED edVolta = new ConhecimentoED ();
    try {
      this.inicioTransacao ();
      edVolta = new ConhecimentoBD (this.sql).getByControle_Entrega (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
    return edVolta;
  }

  public ConhecimentoED getByPostagem (ConhecimentoED ed) throws Excecoes {
    ConhecimentoED edVolta = new ConhecimentoED ();
    try {
      this.inicioTransacao ();
      edVolta = new ConhecimentoBD (this.sql).getByPostagem (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
    return edVolta;
  }

  public byte[] geraConhecimentoEntrega (ConhecimentoED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    ConhecimentoBD = new ConhecimentoBD (this.sql);
    byte[] b = ConhecimentoBD.geraConhecimentoEntrega (ed);
    this.fimTransacao (false);
    return b;
  }

  public byte[] geraConhecimentoProduto (ConhecimentoED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    ConhecimentoBD = new ConhecimentoBD (this.sql);
    byte[] b = ConhecimentoBD.geraRelConhecimentoProduto (ed);
    this.fimTransacao (false);
    return b;
  }

  public byte[] geraRelConhecTransRodCarga (ConhecimentoED ed) throws Excecoes {

    byte[] b = null;

    try {
      this.inicioTransacao ();
      ConhecimentoBD = new ConhecimentoBD (this.sql);
      b = ConhecimentoBD.geraRelConhecTransRodCarga (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar conhecimento");
      excecoes.setMetodo ("geraRelConhecTransRodCarga(ConhecimentoED ed)");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return b;

  }

  public byte[] geraConhecimentoFatura (ConhecimentoED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    ConhecimentoBD = new ConhecimentoBD (this.sql);
    byte[] b = ConhecimentoBD.geraConhecimentoFatura (ed);
    this.fimTransacao (false);
    return b;
  }

  public byte[] imprimeRecibo_CTRC (ConhecimentoED ed) throws Excecoes {

    byte[] b = null;

    try {
      this.inicioTransacao ();
      ConhecimentoBD = new ConhecimentoBD (this.sql);
      b = ConhecimentoBD.imprimeRecibo_CTRC (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar conhecimento");
      excecoes.setMetodo ("geraRelConhecTransRodCarga(ConhecimentoED ed)");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return b;

  }

  public byte[] imprime_Conhecimento (ConhecimentoED ed) throws Excecoes {
    inicioTransacao ();
    try {
      byte[] b = new ConhecimentoBD (this.sql).imprime_Conhecimento (ed);
      if (JavaUtil.doValida (String.valueOf (ed.getOid_Programacao_Carga ()))) {
        Programacao_CargaED programacao = new Programacao_CargaED ();
        programacao.setOid_Programacao_Carga (ed.getOid_Programacao_Carga ());
        new Programacao_CargaBD (sql).vincula (programacao);
      }
      fimTransacao (true);
      return b;
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void imprime_ConhecimentoJasper (ConhecimentoED ed , HttpServletResponse response) throws Excecoes {
    inicioTransacao ();
    try {
      new ConhecimentoBD (this.sql).imprime_ConhecimentoJasper (ed , response);
      if (JavaUtil.doValida (String.valueOf (ed.getOid_Programacao_Carga ()))) {
        Programacao_CargaED programacao = new Programacao_CargaED ();
        programacao.setOid_Programacao_Carga (ed.getOid_Programacao_Carga ());
        new Programacao_CargaBD (sql).vincula (programacao);
      }
      fimTransacao (true);
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  public String imprime_ConhecimentoMatricial (ConhecimentoED ed) throws Excecoes {
    inicioTransacao ();
    try {
      String toReturn = new ConhecimentoBD (sql).imprime_ConhecimentoMatricial (ed);
      if (JavaUtil.doValida (String.valueOf (ed.getOid_Programacao_Carga ()))) {
        Programacao_CargaED programacao = new Programacao_CargaED ();
        programacao.setOid_Programacao_Carga (ed.getOid_Programacao_Carga ());
        new Programacao_CargaBD (sql).vincula (programacao);
      }
      fimTransacao (true);
      return toReturn;
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void alteraNM_Lote_Faturamento (ConhecimentoED dados) throws Excecoes {
    inicioTransacao ();
    try {
      new ConhecimentoBD (sql).alteraNM_Lote_Faturamento (dados);
      fimTransacao (true);
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void relICMSCliente (ConhecimentoED ed) throws Excecoes {
    inicioTransacao ();
    try {
      new ConhecimentoBD (sql).relICMSCliente (ed);
    }
    finally {
      fimTransacao (false);
    }
  }

  public void relConhecimentoEntrega (ConhecimentoED ed) throws Excecoes {
    inicioTransacao ();
    try {
      new ConhecimentoBD (sql).relConhecimentoEntrega (ed);
    }
    finally {
      fimTransacao (false);
    }
  }

  public void geraRelDAC (ConhecimentoED ed , HttpServletResponse res) throws Excecoes {

    this.inicioTransacao ();
    new ConhecimentoBD (this.sql).geraRelDAC (ed , res);
    this.fimTransacao (false);
  }

  public String substituiConhecimento (ConhecimentoED ed) throws Excecoes {

		String nm_situacao="";
	    try {

	      this.inicioTransacao ();

	      Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

	      ConhecimentoBD = new ConhecimentoBD (this.sql);
	      // System.out.println ("substituiConhecimento rn 1 ");
	      nm_situacao = ConhecimentoBD.substituiConhecimento (ed);
	      this.fimTransacao (true);

	    }
	    catch (Excecoes exc) {
	      this.abortaTransacao ();
	      throw exc;
	    }

	    catch (Exception e) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao substituiConhecimento");
	      excecoes.setMetodo ("substituiConhecimento");
	      excecoes.setExc (e);
	      //faz rollback pois deu algum erro
	      this.abortaTransacao ();

	      throw excecoes;
	    }
	    return nm_situacao;
	  }

}
