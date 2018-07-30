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

import com.master.ed.Grupo_Pessoa_CargaED;
import com.master.rn.Grupo_Pessoa_CargaRN;
import com.master.util.Excecoes;

public class Cad109Bean {

/********************************************************
 *
 *******************************************************/
  public Grupo_Pessoa_CargaED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Grupo_Pessoa_CargaRN Grupo_Pessoa_CargaRN = new Grupo_Pessoa_CargaRN();
      Grupo_Pessoa_CargaED ed = new Grupo_Pessoa_CargaED();

      ed.setOID_Pessoa(request.getParameter("FT_NR_CNPJ_CPF"));
      ed.setCD_Grupo_Pessoa(request.getParameter("FT_CD_Grupo_Pessoa"));
      ed.setDM_Tipo_Pessoa(request.getParameter("FT_DM_Tipo_Pessoa"));

      return Grupo_Pessoa_CargaRN.inclui(ed);

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
      Grupo_Pessoa_CargaRN Grupo_Pessoa_CargaRN = new Grupo_Pessoa_CargaRN();
      Grupo_Pessoa_CargaED ed = new Grupo_Pessoa_CargaED();

      ed.setOID_Pessoa(request.getParameter("FT_NR_CNPJ_CPF"));
      ed.setCD_Grupo_Pessoa(request.getParameter("FT_CD_Grupo_Pessoa"));
      ed.setDM_Tipo_Pessoa(request.getParameter("FT_DM_Tipo_Pessoa"));
      ed.setCD_Grupo_Pessoa_Anterior(request.getParameter("FT_CD_Grupo_Pessoa_Anterior"));

      Grupo_Pessoa_CargaRN.altera(ed);
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
      Grupo_Pessoa_CargaRN Grupo_Pessoa_Cargarn = new Grupo_Pessoa_CargaRN();
      Grupo_Pessoa_CargaED ed = new Grupo_Pessoa_CargaED();

      ed.setOID_Pessoa(request.getParameter("FT_NR_CNPJ_CPF"));
      ed.setCD_Grupo_Pessoa(request.getParameter("FT_CD_Grupo_Pessoa"));
      ed.setDM_Tipo_Pessoa(request.getParameter("FT_DM_Tipo_Pessoa"));

      Grupo_Pessoa_Cargarn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList Grupo_Pessoa_Carga_Lista(HttpServletRequest request)throws Excecoes{

      Grupo_Pessoa_CargaED ed = new Grupo_Pessoa_CargaED();

      ed.setOID_Pessoa(request.getParameter("FT_NR_CNPJ_CPF"));

      return new Grupo_Pessoa_CargaRN().lista(ed);

  }

/********************************************************
 *
 *******************************************************/
  public Grupo_Pessoa_CargaED getByRecord(HttpServletRequest request)throws Excecoes{

      Grupo_Pessoa_CargaED ed = new Grupo_Pessoa_CargaED();

      ed.setOID_Pessoa(request.getParameter("FT_NR_CNPJ_CPF"));
      ed.setCD_Grupo_Pessoa(request.getParameter("FT_CD_Grupo_Pessoa"));
      ed.setDM_Tipo_Pessoa(request.getParameter("FT_DM_Tipo_Pessoa"));

     return new Grupo_Pessoa_CargaRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Grupo_Pessoa_CargaED ed = new Grupo_Pessoa_CargaED();

//    ed.setCD_Grupo_Pessoa_Carga(req.getParameter("codigo"));
//    ed.setCD_Remessa(req.getParameter("nome"));

    Grupo_Pessoa_CargaRN geRN = new Grupo_Pessoa_CargaRN();
    geRN.geraRelatorio(ed);
  }


}
