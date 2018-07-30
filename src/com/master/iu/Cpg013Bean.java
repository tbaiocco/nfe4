package com.master.iu;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.ImpostoED;
import com.master.ed.ImpostoPesquisaED;
import com.master.rn.ImpostoRN;
import com.master.util.Excecoes;

public class Cpg013Bean {

  public ImpostoED inclui(HttpServletRequest request)throws Excecoes{

    try{
      ImpostoRN ImpostoRN = new ImpostoRN();
      ImpostoED ed = new ImpostoED();

 //     ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));
 //     ed.setOid_Tipo_Imposto(new Integer(request.getParameter("oid_Tipo_Imposto")));
      ed.setDM_Retem_Imposto(new String(request.getParameter("FT_DM_Tipo_Imposto")));

  //    ed.setVl_Compromisso(new Double(request.getParameter("FT_VL_Compromisso")));
  //    ed.setVL_Imposto(new Double(request.getParameter("FT_VL_Imposto")));
  //    ed.setPE_Imposto(new Double(request.getParameter("FT_PE_Imposto")));

      return ImpostoRN.inclui(ed);
    }

    catch (Excecoes exc){
      throw exc;
    }

  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      ImpostoRN ImpostoRN = new ImpostoRN();
      ImpostoED ed = new ImpostoED();

//      ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));
//      ed.setOid_Imposto(new Integer(request.getParameter("oid_Imposto")));

//      ed.setVL_Imposto(new Double(request.getParameter("FT_VL_Imposto")));
//      ed.setPE_Imposto(new Double(request.getParameter("FT_PE_Imposto")));
      String PE_Imposto = request.getParameter("FT_PE_Imposto");

      ImpostoRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      ImpostoRN ImpostoRN = new ImpostoRN();
      ImpostoED ed = new ImpostoED();

//      ed.setOid_Imposto(new Integer(request.getParameter("oid_Imposto")));

      ImpostoRN.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public ArrayList Imposto_Lista(HttpServletRequest request)throws Excecoes{

      ImpostoPesquisaED ed = new ImpostoPesquisaED();

      String oid_Compromisso = request.getParameter("oid_Compromisso");
      if (oid_Compromisso != null && oid_Compromisso.length() > 0)
      {
        //ed.setOid_Compromisso(new Integer(oid_Compromisso));
      }

    return new ImpostoRN().lista(ed);

  }

  public ImpostoPesquisaED getByRecord(HttpServletRequest request)throws Excecoes{

      ImpostoPesquisaED ed = new ImpostoPesquisaED();

      String oid_Imposto = request.getParameter("oid_Imposto");
      if (oid_Imposto != null && oid_Imposto.length() > 0)
      {
//        ed.setOid_Imposto(new Integer(oid_Imposto));
      }

      return new ImpostoRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    ImpostoED ed = new ImpostoED();

//    ed.setCD_Imposto(req.getParameter("codigo"));
//    ed.setCD_Remessa(req.getParameter("nome"));

    ImpostoRN geRN = new ImpostoRN();
    geRN.geraRelatorio(ed);
  }


}