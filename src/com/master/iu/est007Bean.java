package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Lancamento_ContabilED;
import com.master.rn.Lancamento_ContabilRN;
import com.master.util.Data;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class est007Bean {

  public void inclui_Lancamento_Contabil (HttpServletRequest request) throws Excecoes {

    try {
      String oid_NF = null;
      Lancamento_ContabilRN Lancamento_ContabilRN = new Lancamento_ContabilRN ();
      Lancamento_ContabilED ed = new Lancamento_ContabilED ();

      String oid_Conta = request.getParameter ("oid_Conta");
      if ( (!oid_Conta.equals ("")) && (!oid_Conta.equals ("null")) && (oid_Conta != null)) {
        ed.setOID_Conta (Long.parseLong (oid_Conta));
      }

      String oid_Unidade_Contabil = request.getParameter ("oid_Unidade_Contabil");
      if ( (!oid_Unidade_Contabil.equals ("")) && (!oid_Unidade_Contabil.equals ("null")) && (oid_Unidade_Contabil != null)) {
        ed.setOID_Unidade_Contabil (Long.parseLong (oid_Unidade_Contabil));
      }

      String Valor = request.getParameter ("FT_VL_Lancamento");
      if ( (!Valor.equals ("")) && (!Valor.equals ("null")) && (Valor != null)) {
        ed.setVL_Lancamento (Double.parseDouble (Valor));
      }
      else {
        ed.setVL_Lancamento (0);
      }

      String Numero = request.getParameter ("FT_DM_Acao");
      if ( (!Numero.equals ("")) && (!Numero.equals ("null")) && (Numero != null)) {
        ed.setDM_Acao (Numero);
      }
      else {
        ed.setDM_Acao (" ");
      }

      String oid_Historico = request.getParameter ("oid_Historico");
      String nm_Complemento = request.getParameter ("FT_NM_Complementar");

      if ( (!oid_Historico.equals ("")) && (!oid_Historico.equals ("null")) && (oid_Historico != null)) {
        ed.setCD_Historico (Long.parseLong (oid_Historico));
      }
      else {
        ed.setCD_Historico (0);
      }
      ed.setNM_Complementar (nm_Complemento);

      ed.setDT_Stamp (Data.getDataDMY ());
      ed.setDT_Lancamento (request.getParameter ("FT_DT_Lancamento"));
      ed.setDM_Stamp (" ");

      oid_NF = request.getParameter ("oid_Lote_Compromisso");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Lote_Compromisso (oid_NF);
      }
      else {
        ed.setOID_Lote_Compromisso ("");
      }

      oid_NF = request.getParameter ("oid_Lote_Pagamento");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Lote_Pagamento (new Long (oid_NF).longValue ());
      }
      else {
        ed.setOID_Lote_Pagamento (0);
      }

      oid_NF = request.getParameter ("oid_Movimento_Duplicata");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOid_movimento_duplicata (oid_NF);
      }
      else {
        ed.setOid_movimento_duplicata ("");
      }

      oid_NF = request.getParameter ("oid_Conhecimento");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Conhecimento (oid_NF);
      }
      else {
        ed.setOID_Conhecimento ("");
      }

      oid_NF = request.getParameter ("oid_Ordem_Frete");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Ordem_Frete (oid_NF);
      }
      else {
        ed.setOID_Ordem_Frete ("");
      }

      oid_NF = request.getParameter ("oid_Nota_Fiscal");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Nota_Fiscal (oid_NF);
      }
      else {
        ed.setOID_Nota_Fiscal ("");
      }

      oid_NF = request.getParameter ("oid_Acerto");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Acerto (oid_NF);
      }
      else {
        ed.setOID_Acerto ("");
      }

      Lancamento_ContabilRN.inclui_Lancamento_Contabil (ed);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir lancamento");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void inclui_CTB_Acerto (HttpServletRequest request) throws Excecoes {

    try {
      String oid_NF = null;
      Lancamento_ContabilRN Lancamento_ContabilRN = new Lancamento_ContabilRN ();
      Lancamento_ContabilED ed = new Lancamento_ContabilED ();

      oid_NF = request.getParameter ("oid_Acerto");

      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Acerto (oid_NF);
      }
      else {
        ed.setOID_Acerto ("");
      }

      Lancamento_ContabilRN.inclui_CTB_Acerto (ed);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir lancamento");
      excecoes.setMetodo ("inclui_CTB_Acerto");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void inclui (HttpServletRequest request) throws Excecoes {

    try {
      String oid_NF = null;
      Lancamento_ContabilRN Lancamento_ContabilRN = new Lancamento_ContabilRN ();
      Lancamento_ContabilED ed = new Lancamento_ContabilED ();

      String oid_Conta = request.getParameter ("oid_Conta");
      if ( (!oid_Conta.equals ("")) && (!oid_Conta.equals ("null")) && (oid_Conta != null)) {
        ed.setOID_Conta (Long.parseLong (oid_Conta));
      }

      String oid_Unidade_Contabil = request.getParameter ("oid_Unidade_Contabil");
      if ( (!oid_Unidade_Contabil.equals ("")) && (!oid_Unidade_Contabil.equals ("null")) && (oid_Unidade_Contabil != null)) {
        ed.setOID_Unidade_Contabil (Long.parseLong (oid_Unidade_Contabil));
      }

      String oid_Pagto = request.getParameter ("oid_Lancamento");

      if ( (!oid_Pagto.equals ("")) && (!oid_Pagto.equals ("null")) && (oid_Pagto != null)) {
        ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
      }

      String Valor = request.getParameter ("FT_VL_Lancamento");
      if ( (!Valor.equals ("")) && (!Valor.equals ("null")) && (Valor != null)) {
        ed.setVL_Lancamento (Double.parseDouble (Valor));
      }
      else {
        ed.setVL_Lancamento (0);
      }

      String Numero = request.getParameter ("FT_DM_Acao");
      if ( (!Numero.equals ("")) && (!Numero.equals ("null")) && (Numero != null)) {
        ed.setDM_Acao (Numero);
      }
      else {
        ed.setDM_Acao (" ");
      }

      String oid_Historico = request.getParameter ("oid_Historico");
      String nm_Complemento = request.getParameter ("FT_NM_Complementar");

      if ( (!oid_Historico.equals ("")) && (!oid_Historico.equals ("null")) && (oid_Historico != null)) {
        ed.setCD_Historico (Long.parseLong (oid_Historico));
      }
      else {
        ed.setCD_Historico (0);
      }
      ed.setNM_Complementar (nm_Complemento);

      ed.setDT_Stamp (Data.getDataDMY ());
      ed.setDT_Lancamento (request.getParameter ("FT_DT_Lancamento"));
      ed.setDM_Stamp (" ");

      oid_NF = request.getParameter ("oid_Compromisso");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Compromisso (new Long (oid_NF).longValue ());
      }

      oid_NF = request.getParameter ("oid_Lote_Pagamento");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Lote_Pagamento (new Long (oid_NF).longValue ());
      }

      oid_NF = request.getParameter ("oid_Lote_Compromisso");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Lote_Compromisso (oid_NF);
      }

      oid_NF = request.getParameter ("oid_Nota_Fiscal");

      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Nota_Fiscal (oid_NF);
      }
      else {
        ed.setOID_Nota_Fiscal ("");
      }

      oid_NF = request.getParameter ("oid_Solicitacao");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Solicitacao (oid_NF);
      }

      oid_NF = request.getParameter ("oid_Acerto");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Acerto (oid_NF);
      }
      oid_NF = request.getParameter ("oid_Movimento");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Movimento_Servico (oid_NF);
      }
      oid_NF = request.getParameter ("oid_Ordem_Frete");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Ordem_Frete (oid_NF);
      }
      oid_NF = request.getParameter ("oid_Caixa");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOID_Caixa (oid_NF);
      }

      oid_NF = request.getParameter ("oid_Movimento_Duplicata");
      if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
        ed.setOid_movimento_duplicata (oid_NF);
      }

      Lancamento_ContabilRN.inclui (ed);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir lancamento");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      Lancamento_ContabilRN Lancamento_ContabilRN = new Lancamento_ContabilRN ();
      Lancamento_ContabilED ed = new Lancamento_ContabilED ();

      String oid_Conta = request.getParameter ("oid_Conta");
      if ( (!oid_Conta.equals ("")) && (!oid_Conta.equals ("null")) && (oid_Conta != null)) {
        ed.setOID_Conta (Long.parseLong (oid_Conta));
      }

      String oid_Pagto = request.getParameter ("oid_Lancamento");

      if ( (!oid_Pagto.equals ("")) && (!oid_Pagto.equals ("null")) && (oid_Pagto != null)) {
        ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
      }

      String Valor = request.getParameter ("FT_VL_Lancamento");
      if ( (!Valor.equals ("")) && (!Valor.equals ("null")) && (Valor != null)) {
        ed.setVL_Lancamento (Double.parseDouble (Valor));
      }
      else {
        ed.setVL_Lancamento (0);
      }

      String Numero = request.getParameter ("FT_DM_Acao");
      if ( (!Numero.equals ("")) && (!Numero.equals ("null")) && (Numero != null)) {
        ed.setDM_Acao (Numero);
      }
      else {
        ed.setDM_Acao (" ");
      }

      String oid_Historico = request.getParameter ("oid_Historico");
      String nm_Complemento = request.getParameter ("FT_NM_Complementar");

      if ( (!oid_Historico.equals ("")) && (!oid_Historico.equals ("null")) && (oid_Historico != null)) {
        ed.setCD_Historico (Long.parseLong (oid_Historico));
      }
      else {
        ed.setCD_Historico (0);
      }
      ed.setNM_Complementar (nm_Complemento);

      Lancamento_ContabilRN.altera (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Lancamento_ContabilRN Lancamento_ContabilRN = new Lancamento_ContabilRN ();
      Lancamento_ContabilED ed = new Lancamento_ContabilED ();
      ed.setOID_Lancamento (Long.parseLong (request.getParameter ("oid_Lancamento")));
      Lancamento_ContabilRN.deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao excluir");
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public Lancamento_ContabilED getByRecord (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    String oid_NF = request.getParameter ("oid_Nota_Fiscal");
    if (oid_NF != null && !oid_NF.equals ("") && !oid_NF.equals ("null")) {
      ed.setOID_Nota_Fiscal (oid_NF);
    }
    String oid_Pagto = request.getParameter ("oid_Lancamento");
    if (oid_Pagto != null && !oid_Pagto.equals ("") && !oid_Pagto.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
    }
    String cd_Conta = request.getParameter ("FT_CD_Conta_Credito");
    if ( (cd_Conta != null)) {
      ed.setCD_Conta (cd_Conta);
    }

    return new Lancamento_ContabilRN ().getByRecord (ed);

  }

  public Lancamento_ContabilED getByRecord_Nota_Fiscal (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    String oid_NF = request.getParameter ("oid_Nota_Fiscal");
    if (oid_NF != null && !oid_NF.equals ("") && !oid_NF.equals ("null")) {
      ed.setOID_Nota_Fiscal (oid_NF);
    }
    String oid_Pagto = request.getParameter ("oid_Lancamento");
    if (oid_Pagto != null && !oid_Pagto.equals ("") && !oid_Pagto.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
    }
    String cd_Conta = request.getParameter ("FT_CD_Conta_Credito");
    if ( (cd_Conta != null)) {
      ed.setCD_Conta (cd_Conta);
    }

    return new Lancamento_ContabilRN ().getByRecord_Nota_Fiscal (ed);

  }

  public ArrayList Lista (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
    return new Lancamento_ContabilRN ().lista (ed);
  }

  public ArrayList Lista_Contabil_Nota_Fiscal (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
    return new Lancamento_ContabilRN ().Lista_Contabil_Nota_Fiscal (ed);
  }

  public ArrayList GeraEDI_Notas (HttpServletRequest request) throws Excecoes {

    long unidade = Long.parseLong (request.getParameter ("oid_Unidade"));
    String dt_inicio = request.getParameter ("FT_DT_Inicial");
    String dt_fim = request.getParameter ("FT_DT_Final");

    return new Lancamento_ContabilRN ().GeraEDI_Notas (unidade , dt_inicio , dt_fim);
  }

  public ArrayList Lista_Contabil_Compromisso (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    ed.setOID_Compromisso (new Long (request.getParameter ("oid_Compromisso")).longValue ());

    return new Lancamento_ContabilRN ().lista_Contabil_Compromisso (ed);
  }

  public ArrayList Lista_Contabil_Lote_Pagamento (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    ed.setOID_Lote_Pagamento (new Long (request.getParameter ("oid_Lote_Pagamento")).longValue ());
    return new Lancamento_ContabilRN ().lista_Contabil_Lote_Pagamento (ed);
  }

  public ArrayList Lista_Contabil_Solicitacao (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    ed.setOID_Solicitacao (request.getParameter ("oid_Solicitacao"));
    return new Lancamento_ContabilRN ().lista_Contabil_Solicitacao (ed);
  }

  public Lancamento_ContabilED getByRecord_Solicitacao (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    String oid_NF = request.getParameter ("oid_Solicitacao");
    if (oid_NF != null && !oid_NF.equals ("") && !oid_NF.equals ("null")) {
      ed.setOID_Solicitacao (oid_NF);
    }
    String oid_Pagto = request.getParameter ("oid_Lancamento");
    if (oid_Pagto != null && !oid_Pagto.equals ("") && !oid_Pagto.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
    }
    String cd_Conta = request.getParameter ("FT_CD_Conta_Credito");
    if ( (cd_Conta != null)) {
      ed.setCD_Conta (cd_Conta);
    }

    return new Lancamento_ContabilRN ().getByRecord_Solicitacao (ed);

  }

  public Lancamento_ContabilED getByRecord_Compromisso (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    String oid_NF = request.getParameter ("oid_Compromisso");
    if (oid_NF != null && !oid_NF.equals ("") && !oid_NF.equals ("null")) {
      ed.setOID_Compromisso (new Long (oid_NF).longValue ());
    }
    String oid_Pagto = request.getParameter ("oid_Lancamento");
    if (oid_Pagto != null && !oid_Pagto.equals ("") && !oid_Pagto.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
    }
    String cd_Conta = request.getParameter ("FT_CD_Conta_Credito");
    if ( (cd_Conta != null)) {
      ed.setCD_Conta (cd_Conta);
    }

    return new Lancamento_ContabilRN ().getByRecord_Compromisso (ed);

  }

  public ArrayList Lista_Contabil_Caixa (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    ed.setOID_Caixa (request.getParameter ("oid_Caixa"));
    return new Lancamento_ContabilRN ().lista_Contabil_Caixa (ed);
  }

  public Lancamento_ContabilED getByRecord_Caixa (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    String oid_NF = request.getParameter ("oid_Caixa");
    if (oid_NF != null && !oid_NF.equals ("") && !oid_NF.equals ("null")) {
      ed.setOID_Caixa (oid_NF);
    }
    String oid_Pagto = request.getParameter ("oid_Lancamento");
    if (oid_Pagto != null && !oid_Pagto.equals ("") && !oid_Pagto.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
    }
    String cd_Conta = request.getParameter ("FT_CD_Conta_Credito");
    if ( (cd_Conta != null)) {
      ed.setCD_Conta (cd_Conta);
    }

    return new Lancamento_ContabilRN ().getByRecord_Caixa (ed);

  }

  public ArrayList GeraEDI_Acertos (HttpServletRequest request) throws Excecoes {

    long unidade = Long.parseLong (request.getParameter ("oid_Unidade"));
    String dt_inicio = request.getParameter ("FT_DT_Inicial");
    String dt_fim = request.getParameter ("FT_DT_Final");

    return new Lancamento_ContabilRN ().GeraEDI_Acertos (unidade , dt_inicio , dt_fim);
  }

  public ArrayList Lista_Contabil_Ordem_Frete (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    ed.setOID_Ordem_Frete (request.getParameter ("oid_Ordem_Frete"));
    return new Lancamento_ContabilRN ().lista_Contabil_Ordem_Frete (ed);
  }

  public ArrayList Lista_Contabil_Acerto (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    ed.setOID_Acerto (request.getParameter ("oid_Acerto"));
    return new Lancamento_ContabilRN ().lista_Contabil_Acerto (ed);
  }

  public ArrayList lista_Acerto_Completo (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    ed.setOID_Acerto (request.getParameter ("oid_Movimento"));
    return new Lancamento_ContabilRN ().lista_Acerto_Completo (ed);
  }

  public Lancamento_ContabilED getByRecord_Acerto (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    String oid_NF = request.getParameter ("oid_Acerto");

    if (oid_NF != null && !oid_NF.equals ("") && !oid_NF.equals ("null")) {
      ed.setOID_Acerto (oid_NF);
    }
    String oid_Pagto = request.getParameter ("oid_Lancamento");
    if (oid_Pagto != null && !oid_Pagto.equals ("") && !oid_Pagto.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
    }
    String cd_Conta = request.getParameter ("FT_CD_Conta_Credito");
    if ( (cd_Conta != null)) {
      ed.setCD_Conta (cd_Conta);
    }

    return new Lancamento_ContabilRN ().getByRecord_Acerto (ed);

  }

  public Lancamento_ContabilED getByRecord_Movimento_Conta_Corrente (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();

    String oid_Movimento_Conta_Corrente = request.getParameter ("oid_Movimento_Conta_Corrente");
    if (oid_Movimento_Conta_Corrente != null && !oid_Movimento_Conta_Corrente.equals ("") && !oid_Movimento_Conta_Corrente.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Movimento_Conta_Corrente));
    }

    String oid_Pagto = request.getParameter ("oid_Lancamento");
    if (oid_Pagto != null && !oid_Pagto.equals ("") && !oid_Pagto.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
    }
    String cd_Conta = request.getParameter ("FT_CD_Conta_Credito");
    if ( (cd_Conta != null)) {
      ed.setCD_Conta (cd_Conta);
    }

    return new Lancamento_ContabilRN ().getByRecord_Movimento_Conta_Corrente (ed);

  }

  public Lancamento_ContabilED getByRecord_Ajuste (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    String oid_NF = request.getParameter ("oid_Movimento");
    if (oid_NF != null && !oid_NF.equals ("") && !oid_NF.equals ("null")) {
      ed.setOID_Movimento_Servico (oid_NF);
    }
    String oid_Pagto = request.getParameter ("oid_Lancamento");
    if (oid_Pagto != null && !oid_Pagto.equals ("") && !oid_Pagto.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
    }

    return new Lancamento_ContabilRN ().getByRecord_Ajuste (ed);

  }

  public Lancamento_ContabilED getByRecord_Ordem_Frete (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    String oid_NF = request.getParameter ("oid_Ordem_Frete");
    if (oid_NF != null && !oid_NF.equals ("") && !oid_NF.equals ("null")) {
      ed.setOID_Ordem_Frete (oid_NF);
    }
    String oid_Pagto = request.getParameter ("oid_Lancamento");
    if (oid_Pagto != null && !oid_Pagto.equals ("") && !oid_Pagto.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
    }
    String cd_Conta = request.getParameter ("FT_CD_Conta_Credito");
    if ( (cd_Conta != null)) {
      ed.setCD_Conta (cd_Conta);
    }

    return new Lancamento_ContabilRN ().getByRecord_Ordem_Frete (ed);

  }

  public ArrayList GeraEDI_Solicitacao (HttpServletRequest request) throws Excecoes {

    long unidade = Long.parseLong (request.getParameter ("oid_Unidade"));
    String dt_inicio = request.getParameter ("FT_DT_Inicial");
    String dt_fim = request.getParameter ("FT_DT_Final");

    return new Lancamento_ContabilRN ().GeraEDI_Solicitacao (unidade , dt_inicio , dt_fim);
  }

  public ArrayList GeraEDI_Caixinha (HttpServletRequest request) throws Excecoes {

    long unidade = Long.parseLong (request.getParameter ("oid_Unidade"));
    String dt_inicio = request.getParameter ("FT_DT_Inicial");
    String dt_fim = request.getParameter ("FT_DT_Final");

    return new Lancamento_ContabilRN ().GeraEDI_Caixinha (unidade , dt_inicio , dt_fim);
  }

  public Lancamento_ContabilED getByRecord_Lote_Pagamento (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    String oid_NF = request.getParameter ("oid_Lote_Pagamento");
    if (oid_NF != null && !oid_NF.equals ("") && !oid_NF.equals ("null")) {
      ed.setOID_Lote_Pagamento (new Long (oid_NF).longValue ());
    }
    String oid_Pagto = request.getParameter ("oid_Lancamento");
    if (oid_Pagto != null && !oid_Pagto.equals ("") && !oid_Pagto.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
    }
    String cd_Conta = request.getParameter ("FT_CD_Conta_Credito");
    if ( (cd_Conta != null)) {
      ed.setCD_Conta (cd_Conta);
    }

    return new Lancamento_ContabilRN ().getByRecord_Lote_Pagamento (ed);

  }

  public ArrayList Lista_Contabil_Movimento_Duplicata (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();

    String oid_NF = request.getParameter ("oid_Movimento_Duplicata");
    if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
      ed.setOid_movimento_duplicata (oid_NF);
    }

    return new Lancamento_ContabilRN ().lista_Contabil_Duplicata (ed);
  }

  public ArrayList lista_Contabil_Movimento_Conta_Corrente (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();

    String oid_Movimento_Conta_Corrente = request.getParameter ("oid_Movimento_Conta_Corrente");
    if (oid_Movimento_Conta_Corrente != null && !oid_Movimento_Conta_Corrente.equals ("") && !oid_Movimento_Conta_Corrente.equals ("null")) {
      ed.setOID_Movimento_Conta_Corrente (Long.parseLong (oid_Movimento_Conta_Corrente));
    }

    return new Lancamento_ContabilRN ().lista_Contabil_Movimento_Conta_Corrente (ed);
  }

  public ArrayList Lista_Contabil_Conhecimento (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();

    String oid_NF = request.getParameter ("oid_Conhecimento");
    if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
      ed.setOID_Conhecimento (oid_NF);
    }

    return new Lancamento_ContabilRN ().Lista_Contabil_Conhecimento (ed);
  }

  public ArrayList Lista_Contabil_Duplicata (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();

    String oid_NF = request.getParameter ("oid_Movimento_Duplicata");
    if ( (oid_NF != null) && (!oid_NF.equals ("")) && (!oid_NF.equals ("null"))) {
      ed.setOid_movimento_duplicata (oid_NF);
    }

    return new Lancamento_ContabilRN ().lista_Contabil_Duplicata (ed);
  }

  public Lancamento_ContabilED getByRecord_Movimento_Duplicata (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    String oid_Pagto = request.getParameter ("oid_Lancamento");
    if (oid_Pagto != null && !oid_Pagto.equals ("") && !oid_Pagto.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
    }

    return new Lancamento_ContabilRN ().getByRecord_Duplicata (ed);

  }

  public Lancamento_ContabilED getByRecord_Conhecimento (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    String oid_Pagto = request.getParameter ("oid_Lancamento");
    if (oid_Pagto != null && !oid_Pagto.equals ("") && !oid_Pagto.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
    }

    return new Lancamento_ContabilRN ().getByRecord_Conhecimento (ed);

  }

  public Lancamento_ContabilED getByRecord_Duplicata (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    String oid_Pagto = request.getParameter ("oid_Lancamento");
    if (oid_Pagto != null && !oid_Pagto.equals ("") && !oid_Pagto.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
    }

    return new Lancamento_ContabilRN ().getByRecord_Duplicata (ed);

  }

  public ArrayList GeraEDI_Cobranca (HttpServletRequest request) throws Excecoes {

    long unidade = Long.parseLong (request.getParameter ("oid_Unidade"));
    String dt_inicio = request.getParameter ("FT_DT_Inicial");
    String dt_fim = request.getParameter ("FT_DT_Final");

    return new Lancamento_ContabilRN ().GeraEDI_Cobranca (unidade , dt_inicio , dt_fim);
  }

  public ArrayList Lista_Contabil_Lote_Posto (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    ed.setOID_Lote_Posto (new Long (request.getParameter ("oid_Lote_Posto")).longValue ());
    return new Lancamento_ContabilRN ().lista_Contabil_Lote_Posto (ed);
  }

  public Lancamento_ContabilED getByRecord_Lote_Posto (HttpServletRequest request) throws Excecoes {

    Lancamento_ContabilED ed = new Lancamento_ContabilED ();
    String oid_NF = request.getParameter ("oid_Lote_Posto");
    if (oid_NF != null && !oid_NF.equals ("") && !oid_NF.equals ("null")) {
      ed.setOID_Lote_Posto (new Long (oid_NF).longValue ());
    }
    String oid_Pagto = request.getParameter ("oid_Lancamento");
    if (oid_Pagto != null && !oid_Pagto.equals ("") && !oid_Pagto.equals ("null")) {
      ed.setOID_Lancamento (Long.parseLong (oid_Pagto));
    }
    String cd_Conta = request.getParameter ("FT_CD_Conta_Credito");
    if ( (cd_Conta != null)) {
      ed.setCD_Conta (cd_Conta);
    }

    return new Lancamento_ContabilRN ().getByRecord_Lote_Posto (ed);

  }

  public void GeraRelEDI_Caixinha (HttpServletRequest request , HttpServletResponse response) throws Excecoes {

    long unidade = Long.parseLong (request.getParameter ("oid_Unidade"));
    String dt_inicio = request.getParameter ("FT_DT_Inicial");
    String dt_fim = request.getParameter ("FT_DT_Final");
    String strUnidade = request.getParameter ("FT_NM_Fantasia");


  }

}
