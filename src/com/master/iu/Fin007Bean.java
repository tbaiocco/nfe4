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

import com.master.ed.Grupo_ContaED;
import com.master.rn.Grupo_ContaRN;
import com.master.util.Excecoes;

public class Fin007Bean {

/********************************************************
 *
 *******************************************************/
  public Grupo_ContaED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Grupo_ContaRN grupo_ContaRN = new Grupo_ContaRN();
      Grupo_ContaED ed = new Grupo_ContaED();

      ed.setOid_Grupo_Conta(new Integer(request.getParameter("FT_OID_Grupo_Conta")));
      ed.setNm_Grupo_Conta(request.getParameter("FT_NM_Grupo_Conta"));
      ed.setDM_DRE(request.getParameter("FT_DM_DRE"));

      return grupo_ContaRN.inclui(ed);
    }

    catch (Excecoes exc){
      throw exc;
    }
  }

/********************************************************
 *
 *******************************************************/
  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Grupo_ContaRN grupo_ContaRN = new Grupo_ContaRN();
      Grupo_ContaED ed = new Grupo_ContaED();

      ed.setOid_Grupo_Conta(new Integer(request.getParameter("FT_OID_Grupo_Conta")));
      ed.setNm_Grupo_Conta(request.getParameter("FT_NM_Grupo_Conta"));
      ed.setDM_DRE(request.getParameter("FT_DM_DRE"));

      grupo_ContaRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

/********************************************************
 *
 *******************************************************/
  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Grupo_ContaRN Grupo_Contarn = new Grupo_ContaRN();
      Grupo_ContaED ed = new Grupo_ContaED();

      ed.setOid_Grupo_Conta(new Integer(request.getParameter("FT_OID_Grupo_Conta")));

      Grupo_Contarn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList Grupo_Conta_Lista(HttpServletRequest request)throws Excecoes{

      Grupo_ContaED ed = new Grupo_ContaED();

      String oid_Grupo_Conta = request.getParameter("FT_OID_Grupo_Conta");
      String nm_Grupo_Conta = request.getParameter("FT_NM_Grupo_Conta");

      if (oid_Grupo_Conta != null && !oid_Grupo_Conta.equals(""))
        ed.setOid_Grupo_Conta(new Integer(oid_Grupo_Conta));

      if (nm_Grupo_Conta != null && !nm_Grupo_Conta.equals(""))
        ed.setNm_Grupo_Conta(nm_Grupo_Conta);


      return new Grupo_ContaRN().lista(ed);

  }

/********************************************************
 *
 *******************************************************/
  public Grupo_ContaED getByRecord(HttpServletRequest request)throws Excecoes{

      Grupo_ContaED ed = new Grupo_ContaED();

      String oid_Grupo_Conta = request.getParameter("FT_OID_Grupo_Conta");
      if (oid_Grupo_Conta != null && oid_Grupo_Conta.length() > 0)
      {
        ed.setOid_Grupo_Conta(new Integer(oid_Grupo_Conta));
      }

     return new Grupo_ContaRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Grupo_ContaED ed = new Grupo_ContaED();

//    ed.setCD_Grupo_Conta(req.getParameter("codigo"));
//    ed.setCD_Remessa(req.getParameter("nome"));

    Grupo_ContaRN geRN = new Grupo_ContaRN();
    geRN.geraRelatorio(ed);
  }


}
