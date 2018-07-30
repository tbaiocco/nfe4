package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Ordem_ManifestoED;
import com.master.rn.Ordem_ManifestoRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;

public class man003Bean {

  Utilitaria util = new Utilitaria();

  public void inclui(HttpServletRequest request) throws Excecoes {
    Ordem_ManifestoRN Ordem_Manifestorn = new Ordem_ManifestoRN();
    Ordem_ManifestoED ed = new Ordem_ManifestoED();

    //request em cima dos campos dos forms html

    ed.setDT_Ordem_Manifesto(request.getParameter("FT_DT_Ordem_Manifesto"));
    ed.setHR_Ordem_Manifesto(request.getParameter("FT_HR_Ordem_Manifesto"));
    ed.setOID_Ordem_Frete(request.getParameter("oid_Ordem_Frete"));
    ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));

    String nr_Manifesto = request.getParameter("FT_NR_Manifesto");
    if (util.doValida(nr_Manifesto)) {
      ed.setNR_Manifesto(new Long(nr_Manifesto).longValue());
    }

    String DM_Frete = request.getParameter("FT_DM_Frete");
    if (util.doValida(DM_Frete)) {
      ed.setDM_Frete(DM_Frete);
    }

    Ordem_Manifestorn.inclui(ed);
  }

  public void altera(HttpServletRequest request) throws Excecoes {
    Ordem_ManifestoRN Ordem_Manifestorn = new Ordem_ManifestoRN();
    Ordem_ManifestoED ed = new Ordem_ManifestoED();

    ed.setOID_Ordem_Manifesto(request.getParameter("oid_Ordem_Manifesto"));
    ed.setOID_Ordem_Frete(request.getParameter("oid_Ordem_Frete"));
    ed.setDT_Ordem_Manifesto(request.getParameter("FT_DT_Ordem_Manifesto"));
    ed.setHR_Ordem_Manifesto(request.getParameter("FT_HR_Ordem_Manifesto"));

    String DM_Frete = request.getParameter("FT_DM_Frete");
    if (util.doValida(DM_Frete)) {
      ed.setDM_Frete(DM_Frete);
    }

    Ordem_Manifestorn.altera(ed);
  }

  public void deleta(HttpServletRequest request) throws Excecoes {
    Ordem_ManifestoED ed = new Ordem_ManifestoED();

    ed.setOID_Ordem_Manifesto(request.getParameter("oid_Ordem_Manifesto"));
    ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));
    ed.setOID_Ordem_Frete(request.getParameter("oid_Ordem_Frete"));

    String DM_Frete = request.getParameter("FT_DM_Frete");
    if (util.doValida(DM_Frete)) {
      ed.setDM_Frete(DM_Frete);
    }

    new Ordem_ManifestoRN().deleta(ed);
  }


  public ArrayList Ordem_Manifesto_Lista(HttpServletRequest request) throws
      Excecoes {

    Ordem_ManifestoED ed = new Ordem_ManifestoED();

    String OID_Ordem_Frete = request.getParameter("oid_Ordem_Frete");
    if (String.valueOf(OID_Ordem_Frete) != null &&
        !String.valueOf(OID_Ordem_Frete).equals("") &&
        !String.valueOf(OID_Ordem_Frete).equals("null")) {
      ed.setOID_Ordem_Frete(OID_Ordem_Frete);
    }
    ed.setDT_Ordem_Manifesto(request.getParameter("FT_DT_Ordem_Manifesto"));

    String nr_Manifesto = request.getParameter("FT_NR_Manifesto");
    if (String.valueOf(nr_Manifesto) != null &&
        !String.valueOf(nr_Manifesto).equals("") &&
        !String.valueOf(nr_Manifesto).equals("null")) {
      ed.setNR_Manifesto(new Long(nr_Manifesto).longValue());
    }

    String OID_Manifesto = request.getParameter("oid_Manifesto");

    if (String.valueOf(OID_Manifesto) != null &&
        !String.valueOf(OID_Manifesto).equals("") &&
        !String.valueOf(OID_Manifesto).equals("null")) {
      ed.setOID_Manifesto(OID_Manifesto);
    }

    String DM_Frete = request.getParameter("FT_DM_Frete");
    if (JavaUtil.doValida(DM_Frete)) {
      ed.setDM_Frete(DM_Frete);
    }
    else ed.setDM_Frete("");

    return new Ordem_ManifestoRN().lista(ed);

  }

  public Ordem_ManifestoED getByRecord(HttpServletRequest request) throws
      Excecoes {

    Ordem_ManifestoED ed = new Ordem_ManifestoED();

    String oid_Ordem_Manifesto = request.getParameter("oid_Ordem_Manifesto");
    if (String.valueOf(oid_Ordem_Manifesto) != null &&
        !String.valueOf(oid_Ordem_Manifesto).equals("") &&
        !String.valueOf(oid_Ordem_Manifesto).equals("null")) {
      ed.setOID_Ordem_Manifesto(oid_Ordem_Manifesto);
    }

    return new Ordem_ManifestoRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req) throws Excecoes {
    Ordem_ManifestoED ed = new Ordem_ManifestoED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
     */
    /* SETAR O ED PARA PESQUISA NO BD
     */


    Ordem_ManifestoRN geRN = new Ordem_ManifestoRN();
    geRN.geraRelatorio(ed);
  }

  public void inclui_MIC(HttpServletRequest request) throws Excecoes {
    Ordem_ManifestoRN Ordem_Manifestorn = new Ordem_ManifestoRN();
    Ordem_ManifestoED ed = new Ordem_ManifestoED();

    //request em cima dos campos dos forms html

    ed.setDT_Ordem_Manifesto(request.getParameter("FT_DT_Ordem_Manifesto"));
    ed.setHR_Ordem_Manifesto(request.getParameter("FT_HR_Ordem_Manifesto"));
    ed.setOID_Ordem_Frete(request.getParameter("oid_Ordem_Frete"));
    ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));

    String nr_Manifesto = request.getParameter("FT_NR_Manifesto");
    if (util.doValida(nr_Manifesto)) {
      ed.setNR_Manifesto(new Long(nr_Manifesto).longValue());
    }

    String DM_Frete = request.getParameter("FT_DM_Frete");
    if (util.doValida(DM_Frete)) {
      ed.setDM_Frete(DM_Frete);
    }

    Ordem_Manifestorn.inclui_MIC(ed);
  }

  public void altera_MIC(HttpServletRequest request) throws Excecoes {
    Ordem_ManifestoRN Ordem_Manifestorn = new Ordem_ManifestoRN();
    Ordem_ManifestoED ed = new Ordem_ManifestoED();

    ed.setOID_Ordem_Manifesto(request.getParameter("oid_Ordem_Manifesto"));
    ed.setOID_Ordem_Frete(request.getParameter("oid_Ordem_Frete"));
    ed.setDT_Ordem_Manifesto(request.getParameter("FT_DT_Ordem_Manifesto"));
    ed.setHR_Ordem_Manifesto(request.getParameter("FT_HR_Ordem_Manifesto"));

    String DM_Frete = request.getParameter("FT_DM_Frete");
    if (util.doValida(DM_Frete)) {
      ed.setDM_Frete(DM_Frete);
    }

    Ordem_Manifestorn.altera_MIC(ed);
  }

  public void deleta_MIC(HttpServletRequest request) throws Excecoes {
    Ordem_ManifestoED ed = new Ordem_ManifestoED();

    ed.setOID_Ordem_Manifesto(request.getParameter("oid_Ordem_Manifesto"));
    ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));
    ed.setOID_Ordem_Frete(request.getParameter("oid_Ordem_Frete"));

    String DM_Frete = request.getParameter("FT_DM_Frete");
    if (util.doValida(DM_Frete)) {
      ed.setDM_Frete(DM_Frete);
    }

    new Ordem_ManifestoRN().deleta_MIC(ed);
  }


  public ArrayList Ordem_Manifesto_Lista_MIC(HttpServletRequest request) throws
      Excecoes {

    Ordem_ManifestoED ed = new Ordem_ManifestoED();

    String OID_Ordem_Frete = request.getParameter("oid_Ordem_Frete");
    if (String.valueOf(OID_Ordem_Frete) != null &&
        !String.valueOf(OID_Ordem_Frete).equals("") &&
        !String.valueOf(OID_Ordem_Frete).equals("null")) {
      ed.setOID_Ordem_Frete(OID_Ordem_Frete);
    }
    ed.setDT_Ordem_Manifesto(request.getParameter("FT_DT_Ordem_Manifesto"));

    String nr_Manifesto = request.getParameter("FT_NR_Manifesto");

    if (String.valueOf(nr_Manifesto) != null &&
        !String.valueOf(nr_Manifesto).equals("") &&
        !String.valueOf(nr_Manifesto).equals("null")) {
      ed.setNR_Manifesto(new Long(nr_Manifesto).longValue());
    }

    String OID_Manifesto = request.getParameter("oid_Manifesto");

    if (String.valueOf(OID_Manifesto) != null &&
        !String.valueOf(OID_Manifesto).equals("") &&
        !String.valueOf(OID_Manifesto).equals("null")) {
      ed.setOID_Manifesto(OID_Manifesto);
    }

    String DM_Frete = request.getParameter("FT_DM_Frete");
    if (JavaUtil.doValida(DM_Frete)) {
      ed.setDM_Frete(DM_Frete);
    }
    else ed.setDM_Frete("");

    return new Ordem_ManifestoRN().lista_MIC(ed);

  }

  public Ordem_ManifestoED getByRecord_MIC(HttpServletRequest request) throws
      Excecoes {

    Ordem_ManifestoED ed = new Ordem_ManifestoED();

    String oid_Ordem_Manifesto = request.getParameter("oid_Ordem_Manifesto");
    if (String.valueOf(oid_Ordem_Manifesto) != null &&
        !String.valueOf(oid_Ordem_Manifesto).equals("") &&
        !String.valueOf(oid_Ordem_Manifesto).equals("null")) {
      ed.setOID_Ordem_Manifesto(oid_Ordem_Manifesto);
    }

    return new Ordem_ManifestoRN().getByRecord_MIC(ed);

  }

}
