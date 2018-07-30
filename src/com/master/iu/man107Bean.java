package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.ColetaRCEED;
import com.master.rn.ColetaRCERN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class man107Bean
    extends JavaUtil {

  public void altera (HttpServletRequest request) throws Excecoes {

    try {
      new ColetaRCERN ().altera (carregaED (request));
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
      new ColetaRCERN ().deleta (carregaED (request));
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

  public ArrayList ColetaRCE_Lista (HttpServletRequest request) throws Excecoes {

    return new ColetaRCERN ().lista (carregaED (request));

  }

  public ColetaRCEED getByRecord (HttpServletRequest request) throws Excecoes {

    return new ColetaRCERN ().getByRecord (carregaED (request));

  }

  public void inclui (HttpServletRequest request) throws Excecoes {

    try {
      new ColetaRCERN ().inclui (carregaED (request));
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  private ColetaRCEED carregaED (HttpServletRequest request) {

    ColetaRCEED ed = new ColetaRCEED ();

    ed.setDT_Coleta_RCE (request.getParameter ("FT_DT_Coleta_RCE"));
    ed.setHR_Coleta_RCE (request.getParameter ("FT_HR_Coleta_RCE"));
    ed.setOID_ColetaRCE (request.getParameter ("oid_Coleta_RCE"));

    ed.setOID_Manifesto (request.getParameter ("oid_Manifesto"));
    ed.setOID_Coleta (request.getParameter ("oid_Coleta"));

    String OID_Unidade = request.getParameter ("oid_Unidade");
    if (doValida (OID_Unidade)) {
      ed.setOID_Unidade (new Long (OID_Unidade).longValue ());
    }

    String nr_Manifesto = request.getParameter ("FT_NR_Manifesto");
    if (doValida (nr_Manifesto)) {
      ed.setNR_Manifesto (new Long (nr_Manifesto).longValue ());
    }

    String VL_Entrega = request.getParameter ("FT_VL_Entrega");
    if (doValida (VL_Entrega)) {
      ed.setVL_Entrega (new Double (VL_Entrega).doubleValue ());
    }

    return ed;

  }

}
