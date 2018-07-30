package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.EDI_RemessaED;
import com.master.rn.EDI_RemessaRN;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;

public class EDI_RemessaBean {

  Utilitaria util = new Utilitaria ();

  ///peso cubado


  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      EDI_RemessaRN EDI_RemessaRN = new EDI_RemessaRN ();
      EDI_RemessaED ed = new EDI_RemessaED ();

      ed.setOID_EDI_Remessa (request.getParameter ("oid_EDI_Remessa"));

      EDI_RemessaRN.deleta (ed);
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

  public void deleta_Lista (ArrayList lista) throws Excecoes {

    EDI_RemessaRN EDI_RemessaRN = new EDI_RemessaRN ();
    EDI_RemessaRN.deleta_Lista (lista);

  }

  public void calcula_Lista (ArrayList lista) throws Excecoes {

    EDI_RemessaRN EDI_RemessaRN = new EDI_RemessaRN ();
    EDI_RemessaRN.calcula_Lista (lista);

  }


  public ArrayList lista (HttpServletRequest request) throws Excecoes {
    String DM_Situacao = request.getParameter ("FT_DM_Situacao");
    String DM_Origem_Remessa = request.getParameter ("FT_DM_Origem_Remessa");
    String DT_Remessa_Inicial = request.getParameter ("FT_DT_Remessa_Inicial");
    String DT_Remessa_Final = request.getParameter ("FT_DT_Remessa_Final");
    String NR_Remessa = request.getParameter ("FT_NR_Remessa");
    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    String oid_Pessoa_Destinatario = request.getParameter ("oid_Pessoa_Destinatario");
    String NR_Lote = request.getParameter ("FT_NR_Lote");

    EDI_RemessaED ed = new EDI_RemessaED ();

    ed.setNR_Lote ("");
    if (String.valueOf (NR_Lote) != null && !String.valueOf (NR_Lote).equals ("")
        && !String.valueOf (NR_Lote).equals ("null")) {
      ed.setNR_Lote (NR_Lote);
    }

    if (util.doValida (DM_Situacao)) {
      ed.setDM_Situacao (DM_Situacao);
    }
    if (util.doValida (DM_Origem_Remessa)) {
        ed.setDM_Origem_Remessa (DM_Origem_Remessa);
    }
    if (util.doValida (DT_Remessa_Inicial)) {
      ed.setDT_Remessa_Inicial (DT_Remessa_Inicial);
    }
    if (util.doValida (DT_Remessa_Final)) {
      ed.setDT_Remessa_Final (DT_Remessa_Final);
    }
    if (util.doValida (NR_Remessa)) {
      ed.setNR_Remessa (NR_Remessa);
    }
    if (util.doValida (oid_Pessoa_Remetente)) {
      ed.setOID_Pessoa (oid_Pessoa_Remetente);
    }
    if (util.doValida (oid_Pessoa_Destinatario)) {
      ed.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
    }
    return new EDI_RemessaRN ().lista (ed);
  }

  public ArrayList listaEDI_RemessaConhecimento (HttpServletRequest request) throws Excecoes {
    String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
    EDI_RemessaED ed = new EDI_RemessaED ();

    if (util.doValida (oid_Conhecimento)) {
      ed.setOID_Conhecimento (oid_Conhecimento);
    }
    return new EDI_RemessaRN ().listaEDI_RemessaConhecimento (ed);
  }

  public ArrayList listaEDI_RemessaLote (String NR_Lote) throws Excecoes {
    EDI_RemessaED ed = new EDI_RemessaED ();

    if (util.doValida (NR_Lote)) {
      ed.setNR_Lote (NR_Lote);
    }
    return new EDI_RemessaRN ().listaEDI_RemessaConhecimento (ed);
  }

  public ArrayList listaEDI_RemessaConhecimento (String oid_Conhecimento) throws Excecoes {
    EDI_RemessaED ed = new EDI_RemessaED ();
    if (util.doValida (oid_Conhecimento)) {
      ed.setOID_Conhecimento (oid_Conhecimento);
    }
    return new EDI_RemessaRN ().listaEDI_RemessaConhecimento (ed);
  }


  public ArrayList gera_Remessa (HttpServletRequest request, String NM_Arquivo) throws Excecoes {
    String DT_Remessa = request.getParameter ("FT_DT_Remessa");
    String NM_Natureza = request.getParameter ("FT_NM_Natureza");
    String DM_Origem_Remessa = request.getParameter ("FT_DM_Origem_Remessa");
    
    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    String oid_Pessoa_Entregadora = request.getParameter ("oid_Pessoa_Entregadora");
    String oid_Modal = request.getParameter ("oid_Modal");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String oid_Produto = request.getParameter ("oid_Produto");
    String oid_Armazem = request.getParameter ("oid_Armazem");
    
    //System.out.println("gera_Remessa");

    EDI_RemessaED ed = new EDI_RemessaED ();

    if (util.doValida (DT_Remessa)) {
      ed.setDT_Remessa (DT_Remessa);
    }
    ed.setNM_Natureza ("--");

    if (util.doValida (DM_Origem_Remessa)) {
        ed.setDM_Origem_Remessa (DM_Origem_Remessa);
    }

    if (util.doValida (NM_Natureza)) {
      ed.setNM_Natureza (NM_Natureza);
    }
    if (util.doValida (oid_Pessoa_Remetente)) {
      ed.setOID_Pessoa (oid_Pessoa_Remetente);
    }
    if (util.doValida (oid_Pessoa_Entregadora)) {
        ed.setOID_Pessoa_Entregadora (oid_Pessoa_Entregadora);
      }
    if (util.doValida (oid_Unidade)) {
      ed.setOID_Unidade (Long.parseLong (oid_Unidade));
    }
    if (util.doValida (oid_Modal)) {
      ed.setOID_Modal (Long.parseLong (oid_Modal));
    }
    if (util.doValida (oid_Produto)) {
      ed.setOID_Produto (Long.parseLong (oid_Produto));
    }
    if (util.doValida (oid_Armazem)) {
      ed.setOID_Armazem (Long.parseLong (oid_Armazem));
    }
    if (util.doValida (DT_Remessa)) {
      ed.setDT_Remessa (DT_Remessa);
    }
    if (util.doValida (NM_Arquivo)) {
      ed.setNM_Arquivo (NM_Arquivo);
    }

    return new EDI_RemessaRN ().gera_Remessa (ed);
  }

  public EDI_RemessaED getByRecord (HttpServletRequest request) throws Excecoes {

    EDI_RemessaED ed = new EDI_RemessaED ();

    String NR_Remessa = request.getParameter ("FT_NR_Remessa");
    if (NR_Remessa != null && NR_Remessa.length () > 0) {
      ed.setNR_Remessa (request.getParameter ("FT_NR_Remessa"));
    }
    ed.setOID_EDI_Remessa (request.getParameter ("oid_EDI_Remessa"));
    return new EDI_RemessaRN ().getByRecord (ed);

  }



}
