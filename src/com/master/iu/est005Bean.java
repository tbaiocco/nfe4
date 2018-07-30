package com.master.iu;

import javax.servlet.http.*;
import com.master.rn.Item_Nota_Fiscal_CompraRN;
import com.master.ed.Item_Nota_Fiscal_CompraED;
import com.master.util.Excecoes;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class est005Bean {

  public void inclui (HttpServletRequest request) throws Excecoes {

    try {
      Item_Nota_Fiscal_CompraRN Item_Nota_Fiscal_Comprarn = new Item_Nota_Fiscal_CompraRN ();
      Item_Nota_Fiscal_CompraED ed = new Item_Nota_Fiscal_CompraED ();

      // System.out.println ("CHEgou!!!");

      //ed.setNR_Volumes(new Long(request.getParameter("FT_NR_Volumes")).longValue());
      ed.setVl_quantidade (new Double (request.getParameter ("FT_NR_Volumes")).doubleValue ());
      ed.setVL_Produto (new Double (request.getParameter ("FT_VL_Produto")).doubleValue ());
      ed.setVL_Desconto (Double.parseDouble (request.getParameter ("FT_VL_Desconto")));
      // System.out.println ("CHEgou!!! 2");
      ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
      ed.setVL_Liquido (Double.parseDouble (request.getParameter ("FT_VL_Liquido")));
      ed.setVL_IPI (Double.parseDouble (request.getParameter ("FT_VL_IPI")));
      // System.out.println ("CHEgou!!! 3");
      ed.setOID_Produto (request.getParameter ("oid_Estoque"));
      ed.setCD_Imobiliz (request.getParameter ("FT_NR_Patrimonio"));
      ed.setOID_Unidade_Produto (request.getParameter ("oid"));

      if (request.getParameter ("oid_Ordem_Servico") !=null && !request.getParameter ("oid_Ordem_Servico").equals("null") && !request.getParameter ("oid_Ordem_Servico").equals("")){
    	  ed.setOid_Ordem_Servico(new Long (request.getParameter ("oid_Ordem_Servico")).longValue());
      }

      Item_Nota_Fiscal_Comprarn.inclui (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      Item_Nota_Fiscal_CompraRN Item_Nota_Fiscal_Comprarn = new Item_Nota_Fiscal_CompraRN ();
      Item_Nota_Fiscal_CompraED ed = new Item_Nota_Fiscal_CompraED ();

      //ed.setNR_Volumes(new Long(request.getParameter("FT_NR_Volumes")).longValue());
      ed.setVl_quantidade (new Double (request.getParameter ("FT_NR_Volumes")).doubleValue ());
      ed.setVL_Produto (new Double (request.getParameter ("FT_VL_Produto")).doubleValue ());
      ed.setVL_Desconto (Double.parseDouble (request.getParameter ("FT_VL_Desconto")));
      ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
      ed.setVL_Liquido (Double.parseDouble (request.getParameter ("FT_VL_Liquido")));
      ed.setVL_IPI (Double.parseDouble (request.getParameter ("FT_VL_IPI")));
      ed.setOID_Produto (request.getParameter ("oid_Estoque"));
      ed.setCD_Imobiliz (request.getParameter ("FT_NR_Patrimonio"));
      ed.setOID_Unidade_Produto (request.getParameter ("oid"));
      ed.setNR_Item_Nota_Fiscal (Long.parseLong (request.getParameter ("FT_NR_Item")));
      ed.setOID_Item_Nota_Fiscal (request.getParameter ("oid_Item_Nota_Fiscal"));

      if (request.getParameter ("oid_Ordem_Servico") !=null && !request.getParameter ("oid_Ordem_Servico").equals("null") && !request.getParameter ("oid_Ordem_Servico").equals("")){
    	  ed.setOid_Ordem_Servico(new Long (request.getParameter ("oid_Ordem_Servico")).longValue());
      }

      Item_Nota_Fiscal_Comprarn.altera (ed);
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
      Item_Nota_Fiscal_CompraRN Item_Nota_Fiscal_Comprarn = new Item_Nota_Fiscal_CompraRN ();
      Item_Nota_Fiscal_CompraED ed = new Item_Nota_Fiscal_CompraED ();

      ed.setOID_Item_Nota_Fiscal (request.getParameter ("oid_Item_Nota_Fiscal"));
      ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
      Item_Nota_Fiscal_Comprarn.deleta (ed);
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

  public Item_Nota_Fiscal_CompraED getByRecord (HttpServletRequest request) throws Excecoes {

    Item_Nota_Fiscal_CompraED ed = new Item_Nota_Fiscal_CompraED ();

    String OID_Item_Nota_Fiscal = request.getParameter ("oid_Item_Nota_Fiscal");
    if (String.valueOf (OID_Item_Nota_Fiscal) != null &&
        !String.valueOf (OID_Item_Nota_Fiscal).equals ("") &&
        !String.valueOf (OID_Item_Nota_Fiscal).equals ("null")) {
      ed.setOID_Item_Nota_Fiscal (request.getParameter ("oid_Item_Nota_Fiscal"));
    }

    String OID_Nota_Fiscal = request.getParameter ("oid_Nota_Fiscal");
    if (String.valueOf (OID_Nota_Fiscal) != null &&
        !String.valueOf (OID_Nota_Fiscal).equals ("") &&
        !String.valueOf (OID_Nota_Fiscal).equals ("null")) {
      ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
    }

    String CD_Imobiliz = request.getParameter ("FT_CD_Imobiliz");
    if (String.valueOf (CD_Imobiliz) != null &&
        !String.valueOf (CD_Imobiliz).equals ("") &&
        !String.valueOf (CD_Imobiliz).equals ("null")) {
      ed.setCD_Imobiliz (CD_Imobiliz);
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

    return new Item_Nota_Fiscal_CompraRN ().getByRecord (ed);

  }

  public ArrayList Lista (HttpServletRequest request) throws Excecoes {

    Item_Nota_Fiscal_CompraED ed = new Item_Nota_Fiscal_CompraED ();

    String OID_Nota_Fiscal = request.getParameter ("oid_Nota_Fiscal");

    if (String.valueOf (OID_Nota_Fiscal) != null &&
        !String.valueOf (OID_Nota_Fiscal).equals ("") &&
        !String.valueOf (OID_Nota_Fiscal).equals ("null")) {
      ed.setOID_Nota_Fiscal (OID_Nota_Fiscal);
    }
    String OID_Item_Nota_Fiscal = request.getParameter ("oid_Item_Nota_Fiscal");

    if (String.valueOf (OID_Item_Nota_Fiscal) != null &&
        !String.valueOf (OID_Item_Nota_Fiscal).equals ("") &&
        !String.valueOf (OID_Item_Nota_Fiscal).equals ("null")) {
      ed.setOID_Item_Nota_Fiscal (OID_Item_Nota_Fiscal);
    }

    return new Item_Nota_Fiscal_CompraRN ().lista (ed);
  }

}
