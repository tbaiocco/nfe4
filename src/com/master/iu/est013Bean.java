package com.master.iu;

import javax.servlet.http.*;
import com.master.rn.Autorizacao_ComprasRN;
import com.master.ed.Autorizacao_ComprasED;
import com.master.util.Excecoes;
import java.util.*;
import com.master.util.Data;

public class est013Bean {

  public Autorizacao_ComprasED inclui_autorizador (HttpServletRequest request) throws Excecoes {

    Autorizacao_ComprasED edVolta = new Autorizacao_ComprasED ();
    try {
      Autorizacao_ComprasRN Autorizacao_ComprasRN = new Autorizacao_ComprasRN ();
      Autorizacao_ComprasED ed = new Autorizacao_ComprasED ();
//// // System.out.println("E1");
      String oid_Usuario = request.getParameter ("oid_Usuario");
      if (oid_Usuario != null && !oid_Usuario.equals ("") && !oid_Usuario.equals ("null")) {
        ed.setOid_Usuario (Long.parseLong (oid_Usuario));
      }
//// // System.out.println("E2");

      String oid_Perfil_Compra = request.getParameter ("oid_perfil_compra");
      if ( (oid_Perfil_Compra != null) && (!oid_Perfil_Compra.equals ("")) && (!oid_Perfil_Compra.equals ("null"))) {
        ed.setOid_Perfil_Compra (Long.parseLong (oid_Perfil_Compra));
      }
//// // System.out.println("E3");

      String dt_vencimento = request.getParameter ("FT_DT_Vencimento");
      if ( (dt_vencimento != null) && (!dt_vencimento.equals ("")) && (!dt_vencimento.equals ("null"))) {
        ed.setDT_Vencimento (dt_vencimento);
      }
//// // System.out.println("E4");

      String Valor = request.getParameter ("FT_VL_Alcada");
      if ( (Valor != null) && (!Valor.equals ("")) && (!Valor.equals ("null"))) {
        ed.setVL_Alcada (Double.parseDouble (Valor));
      }
      else {
        ed.setVL_Alcada (0);
      }
//// // System.out.println("E6");

      ed.setDt_stamp (Data.getDataDMY ());
//// // System.out.println("E7");
      ed.setDm_Stamp ("S");
//// // System.out.println("E8");
      edVolta = Autorizacao_ComprasRN.inclui_autorizador (ed);
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
    return edVolta;
  }

  public void altera_autorizador (HttpServletRequest request) throws Excecoes {

    try {
      Autorizacao_ComprasRN Autorizacao_ComprasRN = new Autorizacao_ComprasRN ();
      Autorizacao_ComprasED ed = new Autorizacao_ComprasED ();

      String oid_Autorizador = request.getParameter ("oid_Autorizador");
      if (oid_Autorizador != null && !oid_Autorizador.equals ("") && !oid_Autorizador.equals ("null")) {
        ed.setOid_Autorizador (Long.parseLong (oid_Autorizador));
      }

      String oid_Perfil_Compra = request.getParameter ("oid_perfil_compra");
      if ( (oid_Perfil_Compra != null) && (!oid_Perfil_Compra.equals ("")) && (!oid_Perfil_Compra.equals ("null"))) {
        ed.setOid_Perfil_Compra (Long.parseLong (oid_Perfil_Compra));
      }

      String dt_vencimento = request.getParameter ("FT_DT_Vencimento");
      if ( (dt_vencimento != null) && (!dt_vencimento.equals ("")) && (!dt_vencimento.equals ("null"))) {
        ed.setDT_Vencimento (dt_vencimento);
      }

      String Valor = request.getParameter ("FT_VL_Alcada");
      if (Valor != null && !Valor.equals ("") && !Valor.equals ("null")) {
        ed.setVL_Alcada (Double.parseDouble (Valor));
      }
      else {
        ed.setVL_Alcada (0);
      }

      ed.setDt_stamp (Data.getDataDMY ());
      ed.setDm_Stamp (" ");

      Autorizacao_ComprasRN.altera_autorizador (ed);
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

  public void deleta_autorizador (HttpServletRequest request) throws Excecoes {

    try {
      Autorizacao_ComprasRN Autorizacao_ComprasRN = new Autorizacao_ComprasRN ();
      Autorizacao_ComprasED ed = new Autorizacao_ComprasED ();
      String oid_Autorizador = request.getParameter ("oid_Autorizador");
      ed.setOid_Autorizador (Long.parseLong (oid_Autorizador));
      Autorizacao_ComprasRN.deleta_autorizador (ed);
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

  public Autorizacao_ComprasED getByRecord_autorizador (HttpServletRequest request) throws Excecoes {

    Autorizacao_ComprasED ed = new Autorizacao_ComprasED ();

    String oid_Autorizador = request.getParameter ("oid_Autorizador");
    if (oid_Autorizador != null && !oid_Autorizador.equals ("") && !oid_Autorizador.equals ("null")) {
      ed.setOid_Autorizador (Long.parseLong (oid_Autorizador));
    }

    return new Autorizacao_ComprasRN ().getByRecord_autorizador (ed);

  }

  public Autorizacao_ComprasED getByOid_autorizador (long oid) throws Excecoes {

    Autorizacao_ComprasED ed = new Autorizacao_ComprasED ();

    if (String.valueOf (oid) != null && !String.valueOf (oid).equals ("") && !String.valueOf (oid).equals ("null")) {
      ed.setOid_Autorizador (oid);
    }

    return new Autorizacao_ComprasRN ().getByRecord_autorizador (ed);

  }

  public ArrayList Lista_autorizador (HttpServletRequest request) throws Excecoes {

    Autorizacao_ComprasED ed = new Autorizacao_ComprasED ();
    String oid_Autorizador = request.getParameter ("oid_Autorizador");
    if (oid_Autorizador != null && !oid_Autorizador.equals ("") && !oid_Autorizador.equals ("null")) {
      ed.setOid_Autorizador (Long.parseLong (oid_Autorizador));
    }

    String oid_Usuario = request.getParameter ("oid_Usuario");
    if (oid_Usuario != null && !oid_Usuario.equals ("") && !oid_Usuario.equals ("null")) {
      ed.setOid_Usuario (Long.parseLong (oid_Usuario));
    }

    String oid_Perfil_Compra = request.getParameter ("oid_perfil_compra");
    if ( (oid_Perfil_Compra != null) && (!oid_Perfil_Compra.equals ("")) && (!oid_Perfil_Compra.equals ("null"))) {
      ed.setOid_Perfil_Compra (Long.parseLong (oid_Perfil_Compra));
    }

    return new Autorizacao_ComprasRN ().lista_autorizador (ed);
  }

  public void inclui_perfil (HttpServletRequest request) throws Excecoes {

    try {
      Autorizacao_ComprasRN Autorizacao_ComprasRN = new Autorizacao_ComprasRN ();
      Autorizacao_ComprasED ed = new Autorizacao_ComprasED ();
      String oid_Perfil_Compra = request.getParameter ("oid_perfil_compra");
      if ( (oid_Perfil_Compra != null) && (!oid_Perfil_Compra.equals ("")) && (!oid_Perfil_Compra.equals ("null"))) {
        ed.setOid_Perfil_Compra (Long.parseLong (oid_Perfil_Compra));
      }
////     // System.out.println("E3");
      String nm_Perfil_Compra = request.getParameter ("FT_NM_Perfil");
      if ( (nm_Perfil_Compra != null) && (!nm_Perfil_Compra.equals ("")) && (!nm_Perfil_Compra.equals ("null"))) {
        ed.setNM_Perfil_Compra (nm_Perfil_Compra);
      }

      ed.setDt_stamp (Data.getDataDMY ());
////     // System.out.println("E7");
      ed.setDm_Stamp ("S");
////     // System.out.println("E8");
      Autorizacao_ComprasRN.inclui_perfil (ed);
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

  public void altera_perfil (HttpServletRequest request) throws Excecoes {

    try {
      Autorizacao_ComprasRN Autorizacao_ComprasRN = new Autorizacao_ComprasRN ();
      Autorizacao_ComprasED ed = new Autorizacao_ComprasED ();

      String oid_Perfil_Compra = request.getParameter ("oid_perfil_compra");
      if ( (oid_Perfil_Compra != null) && (!oid_Perfil_Compra.equals ("")) && (!oid_Perfil_Compra.equals ("null"))) {
        ed.setOid_Perfil_Compra (Long.parseLong (oid_Perfil_Compra));
      }

      String nm_Perfil_Compra = request.getParameter ("FT_NM_Perfil");
      if ( (nm_Perfil_Compra != null) && (!nm_Perfil_Compra.equals ("")) && (!nm_Perfil_Compra.equals ("null"))) {
        ed.setNM_Perfil_Compra (nm_Perfil_Compra);
      }

      ed.setDt_stamp (Data.getDataDMY ());
      ed.setDm_Stamp (" ");

      Autorizacao_ComprasRN.altera_perfil (ed);
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

  public void deleta_perfil (HttpServletRequest request) throws Excecoes {

    try {
      Autorizacao_ComprasRN Autorizacao_ComprasRN = new Autorizacao_ComprasRN ();
      Autorizacao_ComprasED ed = new Autorizacao_ComprasED ();
      String oid_Perfil_Compra = request.getParameter ("oid_perfil_compra");
      ed.setOid_Perfil_Compra (Long.parseLong (oid_Perfil_Compra));
      Autorizacao_ComprasRN.deleta_perfil (ed);
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

  public Autorizacao_ComprasED getByRecord_perfil (HttpServletRequest request) throws Excecoes {

    Autorizacao_ComprasED ed = new Autorizacao_ComprasED ();

    String oid_Perfil_Compra = request.getParameter ("oid_perfil_compra");
    if ( (oid_Perfil_Compra != null) && (!oid_Perfil_Compra.equals ("")) && (!oid_Perfil_Compra.equals ("null"))) {
      ed.setOid_Perfil_Compra (Long.parseLong (oid_Perfil_Compra));
    }

    return new Autorizacao_ComprasRN ().getByRecord_perfil (ed);

  }

  public ArrayList Lista_perfil (HttpServletRequest request) throws Excecoes {

    Autorizacao_ComprasED ed = new Autorizacao_ComprasED ();

    String oid_Perfil_Compra = request.getParameter ("oid_perfil_compra");
    if ( (oid_Perfil_Compra != null) && (!oid_Perfil_Compra.equals ("")) && (!oid_Perfil_Compra.equals ("null"))) {
      ed.setOid_Perfil_Compra (Long.parseLong (oid_Perfil_Compra));
    }
    String nm_Perfil_Compra = request.getParameter ("FT_NM_Perfil");
    if ( (nm_Perfil_Compra != null) && (!nm_Perfil_Compra.equals ("")) && (!nm_Perfil_Compra.equals ("null"))) {
      ed.setNM_Perfil_Compra (nm_Perfil_Compra);
    }

    return new Autorizacao_ComprasRN ().lista_perfis (ed);
  }

  public boolean getByEncrypt (String chave , String usuario , long oid) throws Excecoes {
    boolean ok = false;

    try {
      // // System.out.println("entrou no bean");
      Autorizacao_ComprasRN geRN = new Autorizacao_ComprasRN ();
      ok = geRN.getByEncrypt (chave , usuario , oid);
    }
    catch (Exception exc) {
      Excecoes excecoes = (Excecoes) exc;
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return ok;
  }

}
