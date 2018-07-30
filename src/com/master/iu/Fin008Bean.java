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

import com.master.ed.HistoricoED;
import com.master.rn.HistoricoRN;
import com.master.util.Excecoes;

public class Fin008Bean {

/********************************************************
 *
 *******************************************************/
  public HistoricoED inclui(HttpServletRequest request)throws Excecoes{

    try{
      HistoricoRN HistoricoRN = new HistoricoRN();
      HistoricoED ed = new HistoricoED();

      ed.setCd_Historico(request.getParameter("FT_CD_Historico"));
      ed.setNm_Historico(request.getParameter("FT_NM_Historico"));
      ed.setDm_Lanca_Complemento(request.getParameter("FT_DM_Lanca_Complemento"));
      ed.setDm_Lanca_Data(request.getParameter("FT_DM_Lanca_Data"));
      ed.setDm_Lanca_Fornecedor(request.getParameter("FT_DM_Lanca_Fornecedor"));

      return HistoricoRN.inclui(ed);
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
      HistoricoRN HistoricoRN = new HistoricoRN();
      HistoricoED ed = new HistoricoED();

      ed.setOid_Historico(new Integer(request.getParameter("oid_Historico")));
      ed.setCd_Historico(request.getParameter("FT_CD_Historico"));
      ed.setNm_Historico(request.getParameter("FT_NM_Historico"));
      ed.setDm_Lanca_Complemento(request.getParameter("FT_DM_Lanca_Complemento"));
      ed.setDm_Lanca_Data(request.getParameter("FT_DM_Lanca_Data"));
      ed.setDm_Lanca_Fornecedor(request.getParameter("FT_DM_Lanca_Fornecedor"));

      HistoricoRN.altera(ed);
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
      HistoricoRN HistoricoRN = new HistoricoRN();
      HistoricoED ed = new HistoricoED();

      ed.setOid_Historico(new Integer(request.getParameter("oid_Historico")));

      HistoricoRN.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList Historico_Lista(HttpServletRequest request)throws Excecoes{

      HistoricoED ed = new HistoricoED();

      String cd_Historico = request.getParameter("FT_CD_Historico");
      String nm_Historico = request.getParameter("FT_NM_Historico");

      if (cd_Historico != null && !cd_Historico.equals(""))
        ed.setCd_Historico(cd_Historico);

      if (nm_Historico != null && !nm_Historico.equals(""))
        ed.setNm_Historico(nm_Historico);


      return new HistoricoRN().lista(ed);

  }

/********************************************************
 *
 *******************************************************/
  public HistoricoED getByRecord(HttpServletRequest request)throws Excecoes{

      HistoricoED ed = new HistoricoED();

      String oid_Historico = request.getParameter("oid_Historico");
      String cd_Historico = request.getParameter("FT_CD_Historico");

      if (oid_Historico != null && oid_Historico.length() > 0)
      {
        ed.setOid_Historico(new Integer(oid_Historico));
      }

      if (cd_Historico != null && cd_Historico.length() > 0)
      {
        ed.setCd_Historico(cd_Historico);
      }

     return new HistoricoRN().getByRecord(ed);

  }

  public HistoricoED getByRecord(String oid_Historico, String cd_Historico)throws Excecoes{

      HistoricoED ed = new HistoricoED();
      if (oid_Historico != null && oid_Historico.length() > 0)
      {
        ed.setOid_Historico(new Integer(oid_Historico));
      }

      if (cd_Historico != null && cd_Historico.length() > 0)
      {
        ed.setCd_Historico(cd_Historico);
      }

     return new HistoricoRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    HistoricoED ed = new HistoricoED();

//    ed.setCD_Historico(req.getParameter("codigo"));
//    ed.setCD_Remessa(req.getParameter("nome"));

    HistoricoRN geRN = new HistoricoRN();
    geRN.geraRelatorio(ed);
  }


}
