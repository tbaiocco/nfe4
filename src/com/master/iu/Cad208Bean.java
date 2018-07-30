package com.master.iu;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed 
 * @version 1.0
 */

import javax.servlet.http.*;
import com.master.rn.Unidade_NegocioRN;
import com.master.ed.Unidade_NegocioED;
import com.master.util.Excecoes;
import java.util.*;

public class Cad208Bean {

/********************************************************
 *
 *******************************************************/
  public Unidade_NegocioED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Unidade_NegocioRN Unidade_NegocioRN = new Unidade_NegocioRN();
      Unidade_NegocioED ed = new Unidade_NegocioED();

      ed.setOid_Unidade_Negocio(new Integer(request.getParameter("FT_OID_Unidade_Negocio")));
      ed.setNm_Unidade_Negocio(request.getParameter("FT_NM_Unidade_Negocio"));

      return Unidade_NegocioRN.inclui(ed);
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
      Unidade_NegocioRN Unidade_NegocioRN = new Unidade_NegocioRN();
      Unidade_NegocioED ed = new Unidade_NegocioED();

      ed.setOid_Unidade_Negocio(new Integer(request.getParameter("FT_OID_Unidade_Negocio")));
      ed.setNm_Unidade_Negocio(request.getParameter("FT_NM_Unidade_Negocio"));

      Unidade_NegocioRN.altera(ed);
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
      Unidade_NegocioRN Unidade_Negociorn = new Unidade_NegocioRN();
      Unidade_NegocioED ed = new Unidade_NegocioED();

      ed.setOid_Unidade_Negocio(new Integer(request.getParameter("FT_OID_Unidade_Negocio")));

      Unidade_Negociorn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList Unidade_Negocio_Lista(HttpServletRequest request)throws Excecoes{

      Unidade_NegocioED ed = new Unidade_NegocioED();

      String oid_Unidade_Negocio = request.getParameter("FT_OID_Unidade_Negocio");
      String nm_Unidade_Negocio = request.getParameter("FT_NM_Unidade_Negocio");

      if (oid_Unidade_Negocio != null && !oid_Unidade_Negocio.equals(""))
        ed.setOid_Unidade_Negocio(new Integer(oid_Unidade_Negocio));

      if (nm_Unidade_Negocio != null && !nm_Unidade_Negocio.equals(""))
        ed.setNm_Unidade_Negocio(nm_Unidade_Negocio);


      return new Unidade_NegocioRN().lista(ed);

  }

/********************************************************
 *
 *******************************************************/
  public Unidade_NegocioED getByRecord(HttpServletRequest request)throws Excecoes{

      Unidade_NegocioED ed = new Unidade_NegocioED();

      String oid_Unidade_Negocio = request.getParameter("FT_OID_Unidade_Negocio");
      if (oid_Unidade_Negocio != null && oid_Unidade_Negocio.length() > 0)
      {
        ed.setOid_Unidade_Negocio(new Integer(oid_Unidade_Negocio));
      }

     return new Unidade_NegocioRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Unidade_NegocioED ed = new Unidade_NegocioED();

//    ed.setCD_Unidade_Negocio(req.getParameter("codigo"));
//    ed.setCD_Remessa(req.getParameter("nome"));

    Unidade_NegocioRN geRN = new Unidade_NegocioRN();
    geRN.geraRelatorio(ed);
  }

  public Unidade_NegocioED getByRecordUnidade(HttpServletRequest request)throws Excecoes{

      Unidade_NegocioED ed = new Unidade_NegocioED();

      String oid_Unidade_Negocio = request.getParameter("FT_OID_Unidade_Negocio");

      ed.setOID_Unidade(request.getParameter("oid_Unidade"));

      if (oid_Unidade_Negocio != null && oid_Unidade_Negocio.length() > 0)
      {
        ed.setOid_Unidade_Negocio(new Integer(oid_Unidade_Negocio));
      }

     return new Unidade_NegocioRN().getByRecordUnidade(ed);

  }

  public ArrayList Unidade_Negocio_Lista_Unidade(HttpServletRequest request)throws Excecoes{

      Unidade_NegocioED ed = new Unidade_NegocioED();

      String oid_Unidade_Negocio = request.getParameter("FT_OID_Unidade_Negocio");
      String nm_Unidade_Negocio = request.getParameter("FT_NM_Unidade_Negocio");

      if (oid_Unidade_Negocio != null && !oid_Unidade_Negocio.equals(""))
        ed.setOid_Unidade_Negocio(new Integer(oid_Unidade_Negocio));

      if (nm_Unidade_Negocio != null && !nm_Unidade_Negocio.equals(""))
        ed.setNm_Unidade_Negocio(nm_Unidade_Negocio);

      ed.setOID_Unidade(request.getParameter("oid_Unidade"));

      return new Unidade_NegocioRN().lista_Unidade(ed);

  }


}
