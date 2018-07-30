package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Tipo_ImpostoED;
import com.master.rn.Tipo_ImpostoRN;
import com.master.util.Excecoes;

public class cad027Bean {

  public Tipo_ImpostoED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Tipo_ImpostoRN Tipo_ImpostoRN = new Tipo_ImpostoRN();
      Tipo_ImpostoED ed = new Tipo_ImpostoED();

      ed.setCd_Tipo_Imposto(request.getParameter("FT_CD_Tipo_Imposto"));
      ed.setNm_Tipo_Imposto(request.getParameter("FT_NM_Tipo_Imposto"));
      ed.setDm_Tipo_Imposto(request.getParameter("FT_DM_Tipo_Imposto"));
      ed.setDm_Origem(request.getParameter("FT_DM_Origem"));
      ed.setDm_Aplicacao(request.getParameter("FT_DM_Aplicacao"));
      ed.setDm_Recolhimento(request.getParameter("FT_DM_Recolhimento"));

      return Tipo_ImpostoRN.inclui(ed);

    }

    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Tipo_ImpostoRN Tipo_ImpostoRN = new Tipo_ImpostoRN();
      Tipo_ImpostoED ed = new Tipo_ImpostoED();

      ed.setOid_Tipo_Imposto(new Integer(request.getParameter("oid_Tipo_Imposto")));
      ed.setCd_Tipo_Imposto(request.getParameter("FT_CD_Tipo_Imposto"));
      ed.setNm_Tipo_Imposto(request.getParameter("FT_NM_Tipo_Imposto"));
      ed.setDm_Tipo_Imposto(request.getParameter("FT_DM_Tipo_Imposto"));
      ed.setDm_Origem(request.getParameter("FT_DM_Origem"));
      ed.setDm_Aplicacao(request.getParameter("FT_DM_Aplicacao"));
      ed.setDm_Recolhimento(request.getParameter("FT_DM_Recolhimento"));

      Tipo_ImpostoRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Tipo_ImpostoRN Tipo_Impostorn = new Tipo_ImpostoRN();
      Tipo_ImpostoED ed = new Tipo_ImpostoED();

      ed.setOid_Tipo_Imposto(new Integer(request.getParameter("oid_Tipo_Imposto")));

      Tipo_Impostorn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public ArrayList Tipo_Imposto_Lista(HttpServletRequest request)throws Excecoes{

      Tipo_ImpostoED ed = new Tipo_ImpostoED();

      String cd_Tipo_Imposto = request.getParameter("FT_CD_Tipo_Imposto");
      String nm_Tipo_Imposto = request.getParameter("FT_NM_Tipo_Imposto");

      if (cd_Tipo_Imposto != null && !cd_Tipo_Imposto.equals(""))
        ed.setCd_Tipo_Imposto(cd_Tipo_Imposto);

      if (nm_Tipo_Imposto != null && !nm_Tipo_Imposto.equals(""))
        ed.setNm_Tipo_Imposto(nm_Tipo_Imposto);

      return new Tipo_ImpostoRN().lista(ed);

  }

  public Tipo_ImpostoED getByRecord(HttpServletRequest request)throws Excecoes{

      Tipo_ImpostoED ed = new Tipo_ImpostoED();

      String oid_Tipo_Imposto = request.getParameter("oid_Tipo_Imposto");
      String cd_Tipo_Imposto = request.getParameter("FT_CD_Tipo_Imposto");

      if (oid_Tipo_Imposto != null && oid_Tipo_Imposto.length() > 0)
      {
        ed.setOid_Tipo_Imposto(new Integer(oid_Tipo_Imposto));
      }
      if (cd_Tipo_Imposto != null && !cd_Tipo_Imposto.equals("0")){
        ed.setCd_Tipo_Imposto(cd_Tipo_Imposto);
      }


     return new Tipo_ImpostoRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Tipo_ImpostoED ed = new Tipo_ImpostoED();

//    ed.setCD_Tipo_Imposto(req.getParameter("codigo"));
//    ed.setCD_Remessa(req.getParameter("nome"));

    Tipo_ImpostoRN geRN = new Tipo_ImpostoRN();
    geRN.geraRelatorio(ed);
  }


}
