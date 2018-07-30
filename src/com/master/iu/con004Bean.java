package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Item_Nota_FiscalED;
import com.master.rn.Item_Nota_FiscalRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class con004Bean {

  public void inclui (HttpServletRequest request) throws Excecoes {

    try {
      Item_Nota_FiscalRN Item_Nota_Fiscalrn = new Item_Nota_FiscalRN ();
      Item_Nota_FiscalED ed = new Item_Nota_FiscalED ();

      //request em cima dos campos dos forms html

      ed.setNR_Volumes (new Long (request.getParameter ("FT_NR_Volumes")).longValue ());
      ed.setVL_Produto (new Double (request.getParameter ("FT_VL_Produto")).doubleValue ());
      ed.setVL_Unitario (new Double (request.getParameter ("FT_VL_Unitario")).doubleValue ());
      ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
      ed.setCD_Referencia (request.getParameter ("FT_CD_Referencia"));
      ed.setNM_Transporte (request.getParameter ("FT_NM_Transporte"));
      ed.setDM_Embalagem (request.getParameter ("FT_DM_Embalagem"));
      ed.setCD_Chassis_Serie (request.getParameter ("FT_CD_Chassis_Serie"));
      ed.setOID_Pessoa (request.getParameter ("oid_Pessoa_Remetente"));
      Item_Nota_Fiscalrn.inclui (ed);
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "inclui(HttpServletRequest request)");
    }
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      Item_Nota_FiscalRN Item_Nota_Fiscalrn = new Item_Nota_FiscalRN ();
      Item_Nota_FiscalED ed = new Item_Nota_FiscalED ();

      ed.setOID_Item_Nota_Fiscal (request.getParameter ("oid_Item_Nota_Fiscal"));
      ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
      ed.setNR_Volumes (new Long (request.getParameter ("FT_NR_Volumes")).longValue ());
      ed.setVL_Produto (new Double (request.getParameter ("FT_VL_Produto")).doubleValue ());
      ed.setVL_Unitario (new Double (request.getParameter ("FT_VL_Unitario")).doubleValue ());
      ed.setCD_Chassis_Serie (request.getParameter ("FT_CD_Chassis_Serie"));
      ed.setNM_Transporte (request.getParameter ("FT_NM_Transporte"));
      ed.setDM_Embalagem (request.getParameter ("FT_DM_Embalagem"));

      Item_Nota_Fiscalrn.altera (ed);
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
      Item_Nota_FiscalRN Item_Nota_Fiscalrn = new Item_Nota_FiscalRN ();
      Item_Nota_FiscalED ed = new Item_Nota_FiscalED ();

      ed.setOID_Item_Nota_Fiscal (request.getParameter ("oid_Item_Nota_Fiscal"));
      ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
      Item_Nota_Fiscalrn.deleta (ed);
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

  public ArrayList Item_Nota_Fiscal_Lista (HttpServletRequest request) throws Excecoes {
    String OID_Nota_Fiscal = request.getParameter ("oid_Nota_Fiscal");
    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    String oid_Pessoa_Destinatario = request.getParameter ("oid_Pessoa_Destinatario");

    Item_Nota_FiscalED ed = new Item_Nota_FiscalED ();

    if (JavaUtil.doValida (OID_Nota_Fiscal)) {
      ed.setOID_Nota_Fiscal (OID_Nota_Fiscal);
    }
    if (JavaUtil.doValida (oid_Pessoa_Remetente)) {
      ed.setOID_Pessoa (oid_Pessoa_Remetente);
    }
    if (JavaUtil.doValida (oid_Pessoa_Destinatario)) {
      ed.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
    }

    return new Item_Nota_FiscalRN ().lista (ed);
  }

  public Item_Nota_FiscalED getByRecord (HttpServletRequest request) throws Excecoes {

    Item_Nota_FiscalED ed = new Item_Nota_FiscalED ();

    String oid_Item_Nota_Fiscal = request.getParameter ("oid_Item_Nota_Fiscal");
    if (String.valueOf (oid_Item_Nota_Fiscal) != null &&
        !String.valueOf (oid_Item_Nota_Fiscal).equals ("") &&
        !String.valueOf (oid_Item_Nota_Fiscal).equals ("null")) {
      ed.setOID_Item_Nota_Fiscal (request.getParameter ("oid_Item_Nota_Fiscal"));
    }

    String oid_Nota_Fiscal = request.getParameter ("oid_Nota_Fiscal");
    if (String.valueOf (oid_Nota_Fiscal) != null &&
        !String.valueOf (oid_Nota_Fiscal).equals ("") &&
        !String.valueOf (oid_Nota_Fiscal).equals ("null")) {
      ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
    }

    String CD_Chassis_Serie = request.getParameter ("FT_CD_Chassis_Serie");
    if (String.valueOf (CD_Chassis_Serie) != null &&
        !String.valueOf (CD_Chassis_Serie).equals ("") &&
        !String.valueOf (CD_Chassis_Serie).equals ("null")) {
      ed.setCD_Chassis_Serie (CD_Chassis_Serie);
    }

    String NR_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");
    String NM_Serie = request.getParameter ("FT_NM_Serie");
    if (String.valueOf (NR_Nota_Fiscal) != null &&
        !String.valueOf (NR_Nota_Fiscal).equals ("") &&
        !String.valueOf (NR_Nota_Fiscal).equals ("null") &&
        String.valueOf (NM_Serie) != null &&
        !String.valueOf (NM_Serie).equals ("") &&
        !String.valueOf (NM_Serie).equals ("null")) {
      ed.setNR_Nota_Fiscal (new Long (NR_Nota_Fiscal).longValue ());
      ed.setNM_Serie (NM_Serie);
    }

    return new Item_Nota_FiscalRN ().getByRecord (ed);

  }

  public void geraRelatorio (HttpServletRequest req) throws Excecoes {
    Item_Nota_FiscalED ed = new Item_Nota_FiscalED ();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
         /* SETAR O ED PARA PESQUISA NO BD
      */


     Item_Nota_FiscalRN geRN = new Item_Nota_FiscalRN ();
    geRN.geraRelatorio (ed);
  }

}