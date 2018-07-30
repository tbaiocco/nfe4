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

import com.master.ed.Tipo_EventoED;
import com.master.rn.Tipo_EventoRN;
import com.master.util.Excecoes;

public class Edi001Bean {

/********************************************************
 *
 *******************************************************/
  public Tipo_EventoED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Tipo_EventoRN Tipo_EventoRN = new Tipo_EventoRN();
      Tipo_EventoED ed = new Tipo_EventoED();

      ed.setCd_Tipo_Evento(request.getParameter("FT_CD_Tipo_Evento"));
      ed.setNm_Tipo_Evento(request.getParameter("FT_NM_Tipo_Evento"));
      ed.setOid_Historico(new Integer(request.getParameter("oid_Historico")));
      ed.setCd_Conta_Credito(request.getParameter("FT_CD_Conta_Credito_Editada"));
      ed.setCd_Conta_Debito(request.getParameter("FT_CD_Conta_Debito_Editada"));
      ed.setNm_Arquivo_Saida(request.getParameter("FT_NM_Arquivo_Saida"));

      ed.setOid_Centro_Custo(new Integer(request.getParameter("oid_Centro_Custo")));
      ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));
      ed.setOid_Conta_Credito(new Integer(request.getParameter("oid_Conta_Credito")));
      ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
      ed.setOid_Natureza_Operacao(new Integer(request.getParameter("oid_Natureza_Operacao")));




      return Tipo_EventoRN.inclui(ed);

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
      Tipo_EventoRN Tipo_EventoRN = new Tipo_EventoRN();
      Tipo_EventoED ed = new Tipo_EventoED();
      ed.setOid_Tipo_Evento(new Integer(request.getParameter("oid_Tipo_Evento")));
      ed.setCd_Tipo_Evento(request.getParameter("FT_CD_Tipo_Evento"));

      ed.setCd_Tipo_Evento(request.getParameter("FT_CD_Tipo_Evento"));
      ed.setNm_Tipo_Evento(request.getParameter("FT_NM_Tipo_Evento"));
      ed.setOid_Historico(new Integer(request.getParameter("oid_Historico")));
      ed.setCd_Conta_Credito(request.getParameter("FT_CD_Conta_Credito_Editada"));
      ed.setCd_Conta_Debito(request.getParameter("FT_CD_Conta_Debito_Editada"));
      ed.setNm_Arquivo_Saida(request.getParameter("FT_NM_Arquivo_Saida"));

      ed.setOid_Centro_Custo(new Integer(request.getParameter("oid_Centro_Custo")));
      ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));
      ed.setOid_Conta_Credito(new Integer(request.getParameter("oid_Conta_Credito")));
      ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
      ed.setOid_Natureza_Operacao(new Integer(request.getParameter("oid_Natureza_Operacao")));

      Tipo_EventoRN.altera(ed);
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
      Tipo_EventoRN Tipo_Eventorn = new Tipo_EventoRN();
      Tipo_EventoED ed = new Tipo_EventoED();

      ed.setOid_Tipo_Evento(new Integer(request.getParameter("oid_Tipo_Evento")));

      Tipo_Eventorn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList Tipo_Evento_Lista(HttpServletRequest request)throws Excecoes{

      Tipo_EventoED ed = new Tipo_EventoED();

      String cd_Tipo_Evento = request.getParameter("FT_CD_Tipo_Evento");
      String nm_Tipo_Evento = request.getParameter("FT_NM_Tipo_Evento");

      if (cd_Tipo_Evento != null && !cd_Tipo_Evento.equals(""))
        ed.setCd_Tipo_Evento(cd_Tipo_Evento);

      if (nm_Tipo_Evento != null && !nm_Tipo_Evento.equals(""))
        ed.setNm_Tipo_Evento(nm_Tipo_Evento);

      return new Tipo_EventoRN().lista(ed);

  }

/********************************************************
 *
 *******************************************************/
  public Tipo_EventoED getByRecord(HttpServletRequest request)throws Excecoes{

      Tipo_EventoED ed = new Tipo_EventoED();

      String oid_Tipo_Evento = request.getParameter("oid_Tipo_Evento");
      String cd_Tipo_Evento = request.getParameter("FT_CD_Tipo_Evento");

      if (oid_Tipo_Evento != null && oid_Tipo_Evento.length() > 0)
      {
        ed.setOid_Tipo_Evento(new Integer(oid_Tipo_Evento));
      }
      if (cd_Tipo_Evento != null && !cd_Tipo_Evento.equals("0")){
        ed.setCd_Tipo_Evento(cd_Tipo_Evento);
      }

     return new Tipo_EventoRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Tipo_EventoED ed = new Tipo_EventoED();

//    ed.setCD_Tipo_Evento(req.getParameter("codigo"));
//    ed.setCD_Remessa(req.getParameter("nome"));

    Tipo_EventoRN geRN = new Tipo_EventoRN();
    geRN.geraRelatorio(ed);
  }


}
