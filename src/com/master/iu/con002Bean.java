package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Conhecimento_Nota_FiscalED;
import com.master.rn.Conhecimento_Nota_FiscalRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class con002Bean {

  public Conhecimento_Nota_FiscalED inclui (HttpServletRequest request , String OID_Nota_Fiscal) throws Excecoes {
    String DM_Tipo_Conhecimento = request.getParameter ("FT_DM_Tipo_Conhecimento");

    Conhecimento_Nota_FiscalED ed = new Conhecimento_Nota_FiscalED ();

    //request em cima dos campos dos forms html

    ed.setDT_Conhecimento_Nota_Fiscal (request.getParameter ("FT_DT_Conhecimento_Nota_Fiscal"));
    ed.setHR_Conhecimento_Nota_Fiscal (request.getParameter ("FT_HR_Conhecimento_Nota_Fiscal"));

    ed.setOID_Nota_Fiscal (OID_Nota_Fiscal);

    String NR_Peso = request.getParameter ("FT_NR_Peso");
    if (String.valueOf (NR_Peso) != null && !String.valueOf (NR_Peso).equals ("")
        && !String.valueOf (NR_Peso).equals ("null")) {
      ed.setNR_Peso (new Double (NR_Peso).doubleValue ());
    }
    String NR_Peso_Cubado = request.getParameter ("FT_NR_Peso_Cubado");
    if (String.valueOf (NR_Peso_Cubado) != null && !String.valueOf (NR_Peso_Cubado).equals ("")
        && !String.valueOf (NR_Peso_Cubado).equals ("null")) {
      ed.setNR_Peso_Cubado (new Double (NR_Peso_Cubado).doubleValue ());
    }

    String NR_Cubagem = request.getParameter ("FT_NR_Cubagem");
    if (String.valueOf (NR_Cubagem) != null && !String.valueOf (NR_Cubagem).equals ("")
        && !String.valueOf (NR_Cubagem).equals ("null")) {
      ed.setNR_Cubagem (new Double (NR_Cubagem).doubleValue ());
    }

    String NR_Volumes = request.getParameter ("FT_NR_Volumes");
    if (String.valueOf (NR_Volumes) != null && !String.valueOf (NR_Volumes).equals ("")
        && !String.valueOf (NR_Volumes).equals ("null")) {
      ed.setNR_Volumes(new Long (request.getParameter ("FT_NR_Volumes")).longValue ());
    }

    ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));
    ed.setOID_Unidade (new Long (request.getParameter ("oid_Unidade")).longValue ());

    if (JavaUtil.doValida (DM_Tipo_Conhecimento)) {
      ed.setDM_Tipo_Conhecimento (DM_Tipo_Conhecimento);
    }
    return new Conhecimento_Nota_FiscalRN ().inclui (ed);
  }

  public void altera (HttpServletRequest request) throws Excecoes {
    Conhecimento_Nota_FiscalRN Conhecimento_Nota_Fiscalrn = new Conhecimento_Nota_FiscalRN ();
    Conhecimento_Nota_FiscalED ed = new Conhecimento_Nota_FiscalED ();

    ed.setOID_Conhecimento_Nota_Fiscal (request.getParameter ("oid_Conhecimento_Nota_Fiscal"));
    ed.setDT_Conhecimento_Nota_Fiscal (request.getParameter ("FT_DT_Conhecimento_Nota_Fiscal"));
    ed.setHR_Conhecimento_Nota_Fiscal (request.getParameter ("FT_HR_Conhecimento_Nota_Fiscal"));

    Conhecimento_Nota_Fiscalrn.altera (ed);
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Conhecimento_Nota_FiscalRN Conhecimento_Nota_Fiscalrn = new Conhecimento_Nota_FiscalRN ();
      Conhecimento_Nota_FiscalED ed = new Conhecimento_Nota_FiscalED ();

      ed.setOID_Conhecimento_Nota_Fiscal (request.getParameter ("oid_Conhecimento_Nota_Fiscal"));
      ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));

      Conhecimento_Nota_Fiscalrn.deleta (ed);
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

  public ArrayList Conhecimento_Nota_Fiscal_Lista (HttpServletRequest request) throws Excecoes {

    Conhecimento_Nota_FiscalED ed = new Conhecimento_Nota_FiscalED ();

    String nr_Nota_Fiscal = request.getParameter ("FT_NR_Nota_Fiscal");

//      if (String.valueOf(nr_Nota_Fiscal) != null &&
//        !String.valueOf(nr_Nota_Fiscal).equals("") &&
//        !String.valueOf(nr_Nota_Fiscal).equals("null")){
//        ed.setNR_Nota_Fiscal(new Long(nr_Nota_Fiscal).longValue());
//      }
    ed.setOID_Nota_Fiscal (request.getParameter ("oid_Nota_Fiscal"));
    ed.setOID_Conhecimento (request.getParameter ("oid_Conhecimento"));

    return new Conhecimento_Nota_FiscalRN ().lista (ed);

  }

  public Conhecimento_Nota_FiscalED getByRecord (HttpServletRequest request) throws Excecoes {

    Conhecimento_Nota_FiscalED ed = new Conhecimento_Nota_FiscalED ();

    String oid_Conhecimento_Nota_Fiscal = request.getParameter ("oid_Conhecimento_Nota_Fiscal");
    if (String.valueOf (oid_Conhecimento_Nota_Fiscal) != null &&
        !String.valueOf (oid_Conhecimento_Nota_Fiscal).equals ("") &&
        !String.valueOf (oid_Conhecimento_Nota_Fiscal).equals ("null")) {
      ed.setOID_Conhecimento_Nota_Fiscal (oid_Conhecimento_Nota_Fiscal);
    }

    return new Conhecimento_Nota_FiscalRN ().getByRecord (ed);

  }

  public int qtde_Notas_Fiscais_Conhecimento (String oid_Conhecimento) throws Excecoes {

	    return new Conhecimento_Nota_FiscalRN ().qtde_Notas_Fiscais_Conhecimento (oid_Conhecimento);

  }
  
}