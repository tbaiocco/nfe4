package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.master.ed.Fluxo_CaixaED;
import com.master.rn.Fluxo_CaixaRN;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.Utilitaria;

public class Fin103Bean {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);

  public Fluxo_CaixaED inclui (HttpServletRequest request) throws Excecoes {

    Fluxo_CaixaED ed = new Fluxo_CaixaED ();

    ed.setDT_Fluxo (request.getParameter ("FT_DT_Fluxo"));
    ed.setDT_Vencimento (request.getParameter ("FT_DT_Fluxo"));

    ed.setOid_Unidade (new Long (request.getParameter ("oid_Unidade")));
    ed.setOid_Conta_Corrente (request.getParameter ("oid_Conta_Corrente"));
    ed.setNR_Documento (request.getParameter ("FT_NR_Documento"));

    ed.setTX_Observacao (" ");
    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    if (util.doValida (TX_Observacao)) {
      ed.setTX_Observacao (TX_Observacao);
    }

    ed.setNM_Fluxo (" ");
    String NM_Fluxo = request.getParameter ("FT_NM_Fluxo");
    if (util.doValida (NM_Fluxo)) {
      ed.setNM_Fluxo (NM_Fluxo);
    }

    ed.setDM_Fluxo (request.getParameter ("FT_DM_Fluxo"));

    if ("4".equals (ed.getDM_Fluxo ())) {
      ed.setDM_Entrada_Saida ("E");
    }
    if ("6".equals (ed.getDM_Fluxo ())) {
      ed.setDM_Entrada_Saida ("S");
    }

    ed.setDM_Tipo_Lancamento ("L");
    ed.setDM_Situacao ("L");
    ed.setVL_Fluxo (new Double (request.getParameter ("FT_VL_Fluxo")).doubleValue ());

    return new Fluxo_CaixaRN ().inclui (ed);
  }

  public Fluxo_CaixaED atualiza (HttpServletRequest request) throws Excecoes {

    Fluxo_CaixaED ed = new Fluxo_CaixaED ();

    String oid_Empresa = request.getParameter ("oid_Empresa");
    if (util.doValida (oid_Empresa)) {
      ed.setOID_Empresa (Long.valueOf (oid_Empresa).longValue ());
    }
    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (util.doValida (oid_Unidade)) {
      ed.setOid_Unidade (new Long (request.getParameter ("oid_Unidade")));
    }
    String OID_Usuario = request.getParameter ("oid_Usuario");
    if (util.doValida (OID_Usuario)) {
      ed.setOID_Usuario (new Long (OID_Usuario).longValue ());
    }

    ed.setDT_Inicial (request.getParameter ("FT_DT_Inicial"));
    ed.setDT_Final (request.getParameter ("FT_DT_Final"));
    ed.setOid_Conta_Corrente (request.getParameter ("oid_Conta_Corrente"));
    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));
    ed.setDM_Atualiza_Saldo (request.getParameter ("FT_DM_Atualiza_Saldo"));
    ed.setDM_Pagamentos (request.getParameter ("FT_DM_Pagamentos"));

    String NR_Dias = request.getParameter ("FT_NR_Dias");
    ed.setNR_Dias (1);
    if (util.doValida (NR_Dias)) {
      ed.setNR_Dias (Long.valueOf (NR_Dias).longValue ());
    }

    String vl_Cotacao_Informada = request.getParameter ("FT_VL_Cotacao_Informada");
    if (util.doValida (vl_Cotacao_Informada)) {
      ed.setVL_Cotacao_Informada (Double.valueOf (vl_Cotacao_Informada).doubleValue ());
    }

    return new Fluxo_CaixaRN ().atualiza (ed);
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    Fluxo_CaixaED ed = new Fluxo_CaixaED ();

    ed.setDT_Fluxo (request.getParameter ("FT_DT_Fluxo"));
    ed.setNR_Documento (request.getParameter ("FT_NR_Documento"));
    ed.setTX_Observacao (request.getParameter ("FT_TX_Observacao"));
    ed.setDM_Entrada_Saida (request.getParameter ("FT_DM_Entrada_Saida"));
    ed.setDM_Tipo_Lancamento (request.getParameter ("FT_DM_Tipo_Lancamento"));

    ed.setOID_Tipo_Documento (new Integer (request.getParameter ("oid_Tipo_Documento")));
    ed.setOid_Historico (new Integer (request.getParameter ("oid_Historico")));
    ed.setVL_Fluxo (new Double (request.getParameter ("FT_VL_Fluxo")).doubleValue ());
    ed.setOid_Fluxo_Caixa (new Long (request.getParameter ("oid_Fluxo_Caixa")).longValue ());

    ed.setTX_Observacao (" ");
    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    if (util.doValida (TX_Observacao)) {
      ed.setTX_Observacao (TX_Observacao);
    }

    new Fluxo_CaixaRN ().altera (ed);
  }

  public void liberaBloqueia (HttpServletRequest request) throws Excecoes {

    Fluxo_CaixaED ed = new Fluxo_CaixaED ();
    ed.setOid_Fluxo_Caixa (new Long (request.getParameter ("oid_Fluxo_Caixa")).longValue ());

    new Fluxo_CaixaRN ().liberaBloqueia (ed);
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    Fluxo_CaixaED ed = new Fluxo_CaixaED ();
    ed.setOid_Fluxo_Caixa (new Long (request.getParameter ("oid_Fluxo_Caixa")).longValue ());

    new Fluxo_CaixaRN ().deleta (ed);
  }

  public ArrayList Fluxo_Caixa_Lista (HttpServletRequest request) throws Excecoes {

    Fluxo_CaixaED ed = new Fluxo_CaixaED ();
    String oid_Empresa = request.getParameter ("oid_Empresa");
    if (util.doValida (oid_Empresa)) {
      ed.setOID_Empresa (Long.valueOf (oid_Empresa).longValue ());
    }
    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (util.doValida (oid_Unidade)) {
      ed.setOid_Unidade (new Long (request.getParameter ("oid_Unidade")));
    }
    String OID_Usuario = request.getParameter ("oid_Usuario");
    if (util.doValida (OID_Usuario)) {
      ed.setOID_Usuario (new Long (OID_Usuario).longValue ());
    }

    ed.setDT_Inicial (request.getParameter ("FT_DT_Inicial"));
    ed.setDT_Final (request.getParameter ("FT_DT_Final"));
    ed.setOid_Conta_Corrente (request.getParameter ("oid_Conta_Corrente"));

    return new Fluxo_CaixaRN ().lista (ed);
  }

  public Fluxo_CaixaED getByRecord (HttpServletRequest request) throws Excecoes {

    Fluxo_CaixaED ed = new Fluxo_CaixaED ();

    String oid_Fluxo_Caixa = request.getParameter ("oid_Fluxo_Caixa");
    if (util.doValida (oid_Fluxo_Caixa)) {
      ed.setOid_Fluxo_Caixa (new Long (request.getParameter ("oid_Fluxo_Caixa")).longValue ());
    }
    return new Fluxo_CaixaRN ().getByRecord (ed);
  }

  public byte[] imprime_Fluxo_Caixa (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    Fluxo_CaixaED ed = new Fluxo_CaixaED ();

    String oid_Empresa = request.getParameter ("oid_Empresa");
    if (util.doValida (oid_Empresa)) {
      ed.setOID_Empresa (Long.valueOf (oid_Empresa).longValue ());
    }
    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (util.doValida (oid_Unidade)) {
      ed.setOid_Unidade (new Long (request.getParameter ("oid_Unidade")));
    }
    String OID_Usuario = request.getParameter ("oid_Usuario");
    if (util.doValida (OID_Usuario)) {
      ed.setOID_Usuario (new Long (OID_Usuario).longValue ());
    }

    ed.setDT_Inicial (request.getParameter ("FT_DT_Inicial"));
    ed.setDT_Final (request.getParameter ("FT_DT_Final"));
    ed.setOid_Conta_Corrente (request.getParameter ("oid_Conta_Corrente"));
    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));
    ed.setDM_Pagamentos (request.getParameter ("FT_DM_Pagamentos"));

    return new Fluxo_CaixaRN ().imprime_Fluxo_Caixa (ed);
  }
}
