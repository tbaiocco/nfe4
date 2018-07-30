package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.EDI_EntregaED;
import com.master.rn.EDI_EntregaRN;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;

public class EDI_EntregaBean {

  Utilitaria util = new Utilitaria ();

  ///peso cubado


  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      EDI_EntregaRN EDI_EntregaRN = new EDI_EntregaRN ();
      EDI_EntregaED ed = new EDI_EntregaED ();

      String NR_Lote = request.getParameter ("FT_NR_Lote");
      if (String.valueOf (NR_Lote) != null && !String.valueOf (NR_Lote).equals ("")
          && !String.valueOf (NR_Lote).equals ("null")) {
        ed.setNR_Lote (NR_Lote);
      }

      EDI_EntregaRN.deleta (ed);
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


  public ArrayList lista (HttpServletRequest request) throws Excecoes {
    String DM_Situacao = request.getParameter ("FT_DM_Situacao");
    String OID_Pessoa_Entregadora = request.getParameter ("oid_Pessoa_Entregadora");
    String NR_Lote = request.getParameter ("FT_NR_Lote");

    EDI_EntregaED ed = new EDI_EntregaED ();

    ed.setNR_Lote ("");
    if (String.valueOf (NR_Lote) != null && !String.valueOf (NR_Lote).equals ("")
        && !String.valueOf (NR_Lote).equals ("null")) {
      ed.setNR_Lote (NR_Lote);
    }

    if (util.doValida (DM_Situacao)) {
      ed.setDM_Situacao (DM_Situacao);
    }
    if (util.doValida (OID_Pessoa_Entregadora)) {
      ed.setOID_Pessoa_Entregadora (OID_Pessoa_Entregadora);
    }
    return new EDI_EntregaRN ().lista (ed);
  }

  public ArrayList listaEDI_EntregaConhecimento (HttpServletRequest request) throws Excecoes {
    String oid_Conhecimento = request.getParameter ("oid_Conhecimento");
    EDI_EntregaED ed = new EDI_EntregaED ();

    if (util.doValida (oid_Conhecimento)) {
      ed.setOID_Conhecimento (oid_Conhecimento);
    }
    return new EDI_EntregaRN ().listaEDI_EntregaConhecimento (ed);
  }

  public ArrayList listaEDI_EntregaLote (String NR_Lote) throws Excecoes {
    EDI_EntregaED ed = new EDI_EntregaED ();

    if (util.doValida (NR_Lote)) {
      ed.setNR_Lote (NR_Lote);
    }
    return new EDI_EntregaRN ().listaEDI_EntregaConhecimento (ed);
  }

  public ArrayList listaEDI_EntregaConhecimento (String oid_Conhecimento) throws Excecoes {
    EDI_EntregaED ed = new EDI_EntregaED ();
    if (util.doValida (oid_Conhecimento)) {
      ed.setOID_Conhecimento (oid_Conhecimento);
    }
    return new EDI_EntregaRN ().listaEDI_EntregaConhecimento (ed);
  }


  public ArrayList gera_Entrega (HttpServletRequest request, String NM_Arquivo) throws Excecoes {

    String OID_Pessoa_Entregadora = request.getParameter ("oid_Pessoa_Entregadora");
    EDI_EntregaED ed = new EDI_EntregaED ();

    if (util.doValida (OID_Pessoa_Entregadora)) {
      ed.setOID_Pessoa_Entregadora (OID_Pessoa_Entregadora);
    }
    if (util.doValida (NM_Arquivo)) {
      ed.setNM_Arquivo (NM_Arquivo);
    }

    return new EDI_EntregaRN ().gera_Entrega (ed);
  }


}
