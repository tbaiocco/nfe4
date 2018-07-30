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

import com.master.ed.AgenciaED;
import com.master.rn.AgenciaRN;
import com.master.util.Excecoes;

public class Fin003Bean {

/********************************************************
 *
 *******************************************************/
  public AgenciaED inclui(HttpServletRequest request)throws Excecoes{

    try{
      AgenciaRN agenciaRN = new AgenciaRN();
      AgenciaED ed = new AgenciaED();

      ed.setCd_Agencia(request.getParameter("FT_CD_Agencia"));
      ed.setOid_Banco(new Integer(request.getParameter("oid_Banco")));
      ed.setOid_Cidade(new Integer(request.getParameter("oid_Cidade")));

      return agenciaRN.inclui(ed);
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
      AgenciaRN agenciaRN = new AgenciaRN();
      AgenciaED ed = new AgenciaED();

      ed.setOid_Agencia(new Integer(request.getParameter("oid_Agencia")));
      ed.setCd_Agencia(request.getParameter("FT_CD_Agencia"));
      ed.setOid_Banco(new Integer(request.getParameter("oid_Banco")));
      ed.setOid_Cidade(new Integer(request.getParameter("oid_Cidade")));

      agenciaRN.altera(ed);
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
      AgenciaRN Agenciarn = new AgenciaRN();
      AgenciaED ed = new AgenciaED();

      ed.setOid_Agencia(new Integer(request.getParameter("oid_Agencia")));

      Agenciarn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList Agencia_Lista(HttpServletRequest request)throws Excecoes{

      AgenciaED ed = new AgenciaED();

      String cd_Agencia = request.getParameter("FT_CD_Agencia");
      String oid_Banco = request.getParameter("oid_Banco");

      if (cd_Agencia != null && !cd_Agencia.equals(""))
        ed.setCd_Agencia(cd_Agencia);

      if (oid_Banco != null && !oid_Banco.equals(""))
        ed.setOid_Banco(new Integer(oid_Banco));


      return new AgenciaRN().lista(ed);

  }

/********************************************************
 *
 *******************************************************/
  public AgenciaED getByRecord(HttpServletRequest request)throws Excecoes{

      AgenciaED ed = new AgenciaED();

      String oid_Agencia = request.getParameter("oid_Agencia");
      if (oid_Agencia != null && oid_Agencia.length() > 0)
      {
        ed.setOid_Agencia(new Integer(request.getParameter("oid_Agencia")));
      }
//      ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
//      ed.setCD_Banco(request.getParameter("FT_CD_Banco"));
//      ed.setNM_Banco(request.getParameter("FT_NM_Banco"));
      return new AgenciaRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    AgenciaED ed = new AgenciaED();

//    ed.setCD_Agencia(req.getParameter("codigo"));
//    ed.setCD_Remessa(req.getParameter("nome"));

    AgenciaRN geRN = new AgenciaRN();
    geRN.geraRelatorio(ed);
  }


}