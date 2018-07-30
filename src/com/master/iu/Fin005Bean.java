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

import com.master.ed.Ocorrencia_BancariaED;
import com.master.rn.Ocorrencia_BancariaRN;
import com.master.util.Excecoes;

public class Fin005Bean {

/********************************************************
 *
 *******************************************************/
  public Ocorrencia_BancariaED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Ocorrencia_BancariaRN ocorrencia_BancariaRN = new Ocorrencia_BancariaRN();
      Ocorrencia_BancariaED ed = new Ocorrencia_BancariaED();

      ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
      ed.setCd_Ocorrencia(request.getParameter("FT_CD_Ocorrencia"));
      ed.setDm_Tipo_Ocorrencia(request.getParameter("FT_DM_Ocorrencia"));
      ed.setNm_Ocorrencia(request.getParameter("FT_NM_Ocorrencia"));
      ed.setOid_Tipo_Instrucao(new Integer(request.getParameter("oid_Tipo_Instrucao")));

      String vl_Movimento = request.getParameter("FT_VL_Movimento");
      if (!vl_Movimento.equals("") && vl_Movimento != null)
        ed.setVl_Movimento(new Double(request.getParameter("FT_VL_Movimento")));

      ed.setTx_Descricao(request.getParameter("FT_TX_Descricao"));

      return ocorrencia_BancariaRN.inclui(ed);

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
      Ocorrencia_BancariaRN ocorrencia_BancariaRN = new Ocorrencia_BancariaRN();
      Ocorrencia_BancariaED ed = new Ocorrencia_BancariaED();

      ed.setOid_Ocorrencia_Bancaria(new Integer(request.getParameter("oid_Ocorrencia_Bancaria")));
      ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
      ed.setCd_Ocorrencia(request.getParameter("FT_CD_Ocorrencia"));
      ed.setDm_Tipo_Ocorrencia(request.getParameter("FT_DM_Ocorrencia"));
      ed.setNm_Ocorrencia(request.getParameter("FT_NM_Ocorrencia"));
      ed.setOid_Tipo_Instrucao(new Integer(request.getParameter("oid_Tipo_Instrucao")));

      String vl_Movimento = request.getParameter("FT_VL_Movimento");
      if (!vl_Movimento.equals("") && vl_Movimento != null)
        ed.setVl_Movimento(new Double(request.getParameter("FT_VL_Movimento")));

      ed.setTx_Descricao(request.getParameter("FT_TX_Descricao"));

      ocorrencia_BancariaRN.altera(ed);
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
      Ocorrencia_BancariaRN Ocorrencia_Bancariarn = new Ocorrencia_BancariaRN();
      Ocorrencia_BancariaED ed = new Ocorrencia_BancariaED();

      ed.setOid_Ocorrencia_Bancaria(new Integer(request.getParameter("oid_Ocorrencia_Bancaria")));

      Ocorrencia_Bancariarn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList Ocorrencia_Bancaria_Lista(HttpServletRequest request)throws Excecoes{

      Ocorrencia_BancariaED ed = new Ocorrencia_BancariaED();

      String oid_Ocorrencia_Bancaria = request.getParameter("oid_Ocorrencia_Bancaria");
      String oid_Pessoa = request.getParameter("oid_Pessoa");

      if (oid_Ocorrencia_Bancaria != null && !oid_Ocorrencia_Bancaria.equals(""))
        ed.setOid_Ocorrencia_Bancaria(new Integer(oid_Ocorrencia_Bancaria));

      if (oid_Pessoa != null && !oid_Pessoa.equals(""))
        ed.setOid_Pessoa(oid_Pessoa);

      return new Ocorrencia_BancariaRN().lista(ed);

  }

/********************************************************
 *
 *******************************************************/
  public Ocorrencia_BancariaED getByRecord(HttpServletRequest request)throws Excecoes{

      Ocorrencia_BancariaED ed = new Ocorrencia_BancariaED();

      String oid_Ocorrencia_Bancaria = request.getParameter("oid_Ocorrencia_Bancaria");
      String cd_Ocorrencia_Bancaria = request.getParameter("FT_CD_Ocorrencia");

      if (oid_Ocorrencia_Bancaria != null && !oid_Ocorrencia_Bancaria.equals(""))
      {
        ed.setOid_Ocorrencia_Bancaria(new Integer(oid_Ocorrencia_Bancaria));
      }
      if (cd_Ocorrencia_Bancaria != null && !cd_Ocorrencia_Bancaria.equals(""))
      {
        ed.setCd_Ocorrencia(cd_Ocorrencia_Bancaria);
      }

     return new Ocorrencia_BancariaRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Ocorrencia_BancariaED ed = new Ocorrencia_BancariaED();

//    ed.setCD_Ocorrencia_Bancaria(req.getParameter("codigo"));
//    ed.setCD_Remessa(req.getParameter("nome"));

    Ocorrencia_BancariaRN geRN = new Ocorrencia_BancariaRN();
    geRN.geraRelatorio(ed);
  }


}
