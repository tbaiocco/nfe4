package com.master.iu;

import java.util.*;
import javax.servlet.http.*;
import com.master.ed.*;
import com.master.iu.*;
import com.master.rn.*;
import com.master.util.*;

public class Evento_EmailBean {

  public Evento_EMailED inclui (HttpServletRequest request) throws Excecoes {

    try {
      Evento_EMailRN Evento_EMailrn = new Evento_EMailRN ();
      Evento_EMailED ed = new Evento_EMailED ();

      ed.setCd_Evento (request.getParameter ("FT_CD_Evento"));
      ed.setNm_Evento (request.getParameter ("FT_NM_Evento"));
      
      // System.out.println("inclui ev 1");
      return Evento_EMailrn.inclui (ed);
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

  public ArrayList lista (HttpServletRequest request) throws Excecoes {
    Evento_EMailED ed = new Evento_EMailED ();
    ed.setCd_Evento (request.getParameter ("FT_CD_Evento"));
    ed.setNm_Evento (request.getParameter ("FT_NM_Evento"));
    return new Evento_EMailRN ().lista (ed);
  }

  public boolean deleta (HttpServletRequest request) throws Excecoes {

    try {
      Evento_EMailRN rn = new Evento_EMailRN ();
      Evento_EMailED ed = new Evento_EMailED ();

      String oid_Evento = request.getParameter ("oid_Evento");
      if (String.valueOf (oid_Evento) != null && String.valueOf (oid_Evento).length () > 0) {
        ed.setOid_Evento (new Long (oid_Evento).longValue ());
      }
      return rn.deleta (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao deletar");
      excecoes.setMetodo ("deleta");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public Evento_EMailED getByRecord (HttpServletRequest request) throws Excecoes {

    Evento_EMailED ed = new Evento_EMailED ();

    String oid_Evento = request.getParameter ("oid_Evento");
    if (String.valueOf (oid_Evento) != null && String.valueOf (oid_Evento).length () > 0) {
      ed.setOid_Evento (new Long (oid_Evento).longValue ());
    }
    ed.setCd_Evento (request.getParameter ("FT_CD_Evento"));

    return new Evento_EMailRN ().getByRecord (ed);

  }

  public Evento_EMailED getByRecord (long oid_Evento , String CD_Evento) throws Excecoes {

    Evento_EMailED ed = new Evento_EMailED ();
    ed.setOid_Evento (oid_Evento);
    ed.setCd_Evento (CD_Evento);
    return new Evento_EMailRN ().getByRecord (ed);

  }

  public void update (HttpServletRequest request) throws Excecoes {

    try {
      Evento_EMailED ed = new Evento_EMailED ();
      Evento_EMailRN Evento_EMailrn = new Evento_EMailRN ();
      ed.setCd_Evento (request.getParameter ("FT_CD_Evento"));
      ed.setNm_Evento (request.getParameter ("FT_NM_Evento"));
      String oid_Evento = request.getParameter ("oid_Evento");
      if (String.valueOf (oid_Evento) != null && String.valueOf (oid_Evento).length () > 0) {
        ed.setOid_Evento (new Long (oid_Evento).longValue ());
      }

      Evento_EMailrn.update (ed);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao alterar");
      excecoes.setMetodo ("update");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }
}