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

import com.master.ed.Tipo_InstrucaoED;
import com.master.rn.Tipo_InstrucaoRN;
import com.master.util.Excecoes;

public class Fin010Bean {

/*******************************************************
 *
 *******************************************************/
  public Tipo_InstrucaoED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Tipo_InstrucaoRN tipo_InstrucaoRN = new Tipo_InstrucaoRN();
      Tipo_InstrucaoED ed = new Tipo_InstrucaoED();

      ed.setCd_Tipo_Instrucao(request.getParameter("FT_CD_Tipo_Instrucao"));
      ed.setCd_Conta_Contabil(request.getParameter("FT_CD_Conta_Contabil"));
      ed.setNm_Tipo_Instrucao(request.getParameter("FT_NM_Tipo_Instrucao"));
      ed.setDM_Atualiza_Saldo(request.getParameter("FT_DM_Atualiza_Saldo"));
      ed.setDm_Altera_Titulo(request.getParameter("FT_DM_Tipo_Alteracao"));
      ed.setDm_Gera_Instrucao(request.getParameter("FT_DM_Gera_Instrucao"));
      ed.setDm_Gera_Movimento(request.getParameter("FT_DM_Gera_Movimento"));
      ed.setDm_Gera_Ocorrencia(request.getParameter("FT_DM_Gera_Ocorrencia"));
      ed.setCd_Valor(request.getParameter("FT_Cd_Valor"));

      ed.setDM_Diario_Razao(request.getParameter("FT_DM_Diario_Razao"));

      return tipo_InstrucaoRN.inclui(ed);
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
      Tipo_InstrucaoRN tipo_InstrucaoRN = new Tipo_InstrucaoRN();
      Tipo_InstrucaoED ed = new Tipo_InstrucaoED();

      ed.setOid_Tipo_Instrucao(new Integer(request.getParameter("oid_Tipo_Instrucao")));
      ed.setCd_Tipo_Instrucao(request.getParameter("FT_CD_Tipo_Instrucao"));
      ed.setDM_Atualiza_Saldo(request.getParameter("FT_DM_Atualiza_Saldo"));

      ed.setNm_Tipo_Instrucao(request.getParameter("FT_NM_Tipo_Instrucao"));
      ed.setDm_Altera_Titulo(request.getParameter("FT_DM_Tipo_Alteracao"));
      ed.setDm_Gera_Instrucao(request.getParameter("FT_DM_Gera_Instrucao"));
      ed.setDm_Gera_Movimento(request.getParameter("FT_DM_Gera_Movimento"));
      ed.setDm_Gera_Ocorrencia(request.getParameter("FT_DM_Gera_Ocorrencia"));
      ed.setCd_Conta_Contabil(request.getParameter("FT_CD_Conta_Contabil"));
      ed.setCd_Valor(request.getParameter("FT_Cd_Valor"));

      ed.setDM_Diario_Razao(request.getParameter("FT_DM_Diario_Razao"));

      tipo_InstrucaoRN.altera(ed);
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
      Tipo_InstrucaoRN Tipo_Instrucaorn = new Tipo_InstrucaoRN();
      Tipo_InstrucaoED ed = new Tipo_InstrucaoED();

      ed.setOid_Tipo_Instrucao(new Integer(request.getParameter("oid_Tipo_Instrucao")));

      Tipo_Instrucaorn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList tipo_Instrucao_Lista(HttpServletRequest request)throws Excecoes{

      Tipo_InstrucaoED ed = new Tipo_InstrucaoED();

      String cd_Tipo_Instrucao = request.getParameter("FT_CD_Tipo_Instrucao");
      String cd_Conta_Contabil = request.getParameter("FT_CD_Conta_Contabil");
      String nm_Tipo_Instrucao = request.getParameter("FT_NM_Tipo_Instrucao");

      if (cd_Tipo_Instrucao != null && !cd_Tipo_Instrucao.equals(""))
        ed.setCd_Tipo_Instrucao(cd_Tipo_Instrucao);

      if (cd_Conta_Contabil != null && !cd_Conta_Contabil.equals(""))
        ed.setCd_Conta_Contabil(cd_Conta_Contabil);

      if (nm_Tipo_Instrucao != null && !nm_Tipo_Instrucao.equals(""))
        ed.setNm_Tipo_Instrucao(nm_Tipo_Instrucao);

      ed.setDM_Diario_Razao(request.getParameter("FT_DM_Diario_Razao"));


      return new Tipo_InstrucaoRN().lista(ed);

  }

/********************************************************
 *
 *******************************************************/
  public Tipo_InstrucaoED getByRecord(HttpServletRequest request)throws Excecoes{

      // //// System.out.println("bel");


      Tipo_InstrucaoED ed = new Tipo_InstrucaoED();

      String oid_Tipo_Instrucao = request.getParameter("oid_Tipo_Instrucao");
      String oid_Historico = request.getParameter("oid_Historico");
      String cd_Conta_Contabil = request.getParameter("FT_CD_Conta_Contabil");
      String cd_Tipo_Instrucao = request.getParameter("FT_CD_Tipo_Instrucao");

     if (cd_Conta_Contabil != null && !cd_Conta_Contabil.equals(""))
        ed.setCd_Conta_Contabil(cd_Conta_Contabil);

      if (oid_Tipo_Instrucao != null && oid_Tipo_Instrucao.length() > 0)
      {
        ed.setOid_Tipo_Instrucao(new Integer(oid_Tipo_Instrucao));
      }
      if (oid_Historico != null && oid_Historico.length() > 0)
      {
        ed.setOid_Historico(new Integer(oid_Historico));
      }
      if (cd_Tipo_Instrucao != null && cd_Tipo_Instrucao.length() > 0)
      {
        ed.setCd_Tipo_Instrucao(cd_Tipo_Instrucao);
      }

      // //// System.out.println("be2");

     return new Tipo_InstrucaoRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Tipo_InstrucaoED ed = new Tipo_InstrucaoED();

//    ed.setCD_Tipo_Instrucao(req.getParameter("codigo"));
//    ed.setCD_Remessa(req.getParameter("nome"));

    Tipo_InstrucaoRN geRN = new Tipo_InstrucaoRN();
    geRN.geraRelatorio(ed);
  }


}
