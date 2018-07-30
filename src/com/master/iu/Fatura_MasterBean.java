package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Fatura_MasterED;
import com.master.rn.Fatura_MasterRN;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;

public class Fatura_MasterBean {

  Utilitaria util = new Utilitaria ();


  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Fatura_MasterRN Fatura_MasterRN = new Fatura_MasterRN ();
      Fatura_MasterED ed = new Fatura_MasterED ();

      ed.setOID_Fatura_Master (request.getParameter ("oid_Fatura_Master"));

      Fatura_MasterRN.deleta (ed);
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

  public void deleta_Fatura (HttpServletRequest request) throws Excecoes {

    try {
      Fatura_MasterRN Fatura_MasterRN = new Fatura_MasterRN ();
      Fatura_MasterED ed = new Fatura_MasterED ();

      String NR_Fatura = request.getParameter ("FT_NR_Fatura");
      if (String.valueOf (NR_Fatura) != null && !String.valueOf (NR_Fatura).equals ("")
          && !String.valueOf (NR_Fatura).equals ("null")) {
        ed.setNR_Fatura (NR_Fatura);
      }

      Fatura_MasterRN.deleta_Fatura (ed);
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
    String NR_Fatura = request.getParameter ("FT_NR_Fatura");
    String DM_Situacao = request.getParameter ("FT_DM_Situacao");
    String DT_Fatura_Master_Inicial = request.getParameter ("FT_DT_Fatura_Master_Inicial");
    String DT_Fatura_Master_Final = request.getParameter ("FT_DT_Fatura_Master_Final");
    String DM_Cia_Aerea = request.getParameter ("FT_DM_Cia_Aerea");
    String NR_Master = request.getParameter ("FT_NR_Master");

    Fatura_MasterED ed = new Fatura_MasterED ();

    ed.setNR_Master ("");
    if (String.valueOf (NR_Master) != null && !String.valueOf (NR_Master).equals ("")
        && !String.valueOf (NR_Master).equals ("null")) {
      ed.setNR_Master (NR_Master);
    }

    if (util.doValida (NR_Fatura)) {
      ed.setNR_Fatura (NR_Fatura);
    }
    if (util.doValida (DM_Situacao)) {
      ed.setDM_Situacao (DM_Situacao);
    }
    if (util.doValida (DT_Fatura_Master_Inicial)) {
      ed.setDT_Fatura_Master_Inicial (DT_Fatura_Master_Inicial);
    }
    if (util.doValida (DT_Fatura_Master_Final)) {
      ed.setDT_Fatura_Master_Final (DT_Fatura_Master_Final);
    }
    if (util.doValida (DM_Cia_Aerea)) {
      ed.setDM_Cia_Aerea (DM_Cia_Aerea);
    }
    return new Fatura_MasterRN ().lista (ed);
  }



  public Fatura_MasterED getByRecord (HttpServletRequest request) throws Excecoes {

    Fatura_MasterED ed = new Fatura_MasterED ();

    String NR_Fatura_Master = request.getParameter ("FT_NR_Fatura_Master");
    if (NR_Fatura_Master != null && NR_Fatura_Master.length () > 0) {
      ed.setNR_Fatura_Master (new Long (request.getParameter ("FT_NR_Fatura_Master")).longValue ());
    }
    ed.setOID_Fatura_Master (request.getParameter ("oid_Fatura_Master"));
    return new Fatura_MasterRN ().getByRecord (ed);

  }


  public void importa_Fatura (String arquivo , String DM_Cia_Aerea , String NR_Fatura) throws Excecoes {

      // System.out.println ("inicio importa_Arquivo  ");


    try {
      Fatura_MasterRN Fatura_MasterRN = new Fatura_MasterRN ();
      // System.out.println ("inicio  iu importacao arquivo=>  " + arquivo);
      // System.out.println ("inicio importacao  DM_Cia_Aerea=>  " + DM_Cia_Aerea);
      // System.out.println ("inicio importacao  NR_Fatura=>  " + NR_Fatura);

      Fatura_MasterRN.importa_Fatura (arquivo, DM_Cia_Aerea, NR_Fatura);
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro ao importa_Arquivo");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

}
