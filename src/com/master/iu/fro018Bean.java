package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.ApoioED;
import com.master.rn.ApoioRN;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class fro018Bean {

  public ApoioED inclui(HttpServletRequest request)throws Excecoes{

    try{
      ApoioRN Apoiorn = new ApoioRN();
      ApoioED ed = new ApoioED();

      ed.setNM_Apoio(request.getParameter("FT_NM_Apoio"));
      ed.setNM_Contato(request.getParameter("FT_NM_Contato"));
      ed.setNM_Telefone(request.getParameter("FT_NM_Telefone"));
      ed.setNM_Referencia(request.getParameter("FT_NM_Referencia"));
      ed.setNM_Endereco(request.getParameter("FT_NM_Endereco"));
      ed.setNR_CEP(request.getParameter("FT_NR_CEP"));
      ed.setOID_Rota(new Long(request.getParameter("oid_Rota")).longValue());
      ed.setOID_Cidade_Apoio(new Long(request.getParameter("oid_Cidade_Apoio")).longValue());

      String FT_NR_KM = request.getParameter("FT_NR_KM");
      if (FT_NR_KM != null && !FT_NR_KM.equals("") && !FT_NR_KM.equals("null")){
        ed.setNR_KM(new Double(FT_NR_KM).doubleValue());
      }

      ed.setDM_Tipo_Apoio(request.getParameter("FT_DM_Tipo_Apoio"));
      ed.setDM_Apoio_Rastreado(request.getParameter("FT_DM_Apoio_Rastreado"));

      return Apoiorn.inclui(ed);
    }

    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }

  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      ApoioRN Apoiorn = new ApoioRN();
      ApoioED ed = new ApoioED();

      ed.setDM_Apoio_Rastreado(request.getParameter("FT_DM_Apoio_Rastreado"));

      ed.setOID_Apoio(new Long(request.getParameter("oid_Apoio")).longValue());
      ed.setOID_Rota(new Long(request.getParameter("oid_Rota")).longValue());
      ed.setOID_Cidade_Apoio(new Long(request.getParameter("oid_Cidade_Apoio")).longValue());
      ed.setNM_Contato(request.getParameter("FT_NM_Contato"));
      ed.setNM_Telefone(request.getParameter("FT_NM_Telefone"));
      ed.setDM_Tipo_Apoio(request.getParameter("FT_DM_Tipo_Apoio"));
      String FT_NR_KM = request.getParameter("FT_NR_KM");
      if (FT_NR_KM != null && !FT_NR_KM.equals("") && !FT_NR_KM.equals("null")){
        ed.setNR_KM(new Double(FT_NR_KM).doubleValue());
      }
      ed.setNM_Apoio(request.getParameter("FT_NM_Apoio"));
      ed.setNM_Referencia(request.getParameter("FT_NM_Referencia"));
      ed.setDM_Tipo_Apoio(request.getParameter("FT_DM_Tipo_Apoio"));
      ed.setOID_Cidade_Apoio(new Long(request.getParameter("oid_Cidade_Apoio")).longValue());
      ed.setNM_Endereco(request.getParameter("FT_NM_Endereco"));
      ed.setNR_CEP(request.getParameter("FT_NR_CEP"));

      Apoiorn.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      ApoioRN Apoiorn = new ApoioRN();
      ApoioED ed = new ApoioED();

      ed.setOID_Apoio(new Long(request.getParameter("oid_Apoio")).longValue());

      Apoiorn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao excluir");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList Apoio_Lista(HttpServletRequest request)throws Excecoes{

      ApoioED ed = new ApoioED();

      if (request.getParameter("oid_Rota") != null){
        ed.setOID_Rota(new Long(request.getParameter("oid_Rota")).longValue());
      }

      ed.setCD_Roteiro(request.getParameter("FT_CD_Roteiro"));
      ed.setNM_Contato(request.getParameter("FT_NM_Contato"));
      ed.setNM_Referencia(request.getParameter("FT_NM_Referencia"));

      String nr_Rota = request.getParameter("FT_NR_Rota");

      String NR_KM = request.getParameter("NR_KM");
      if (String.valueOf(NR_KM) != null &&
        !String.valueOf(NR_KM).equals("") &&
        !String.valueOf(NR_KM).equals("null")){
        ed.setNR_KM(new Long(NR_KM).longValue());
      }

      return new ApoioRN().lista(ed);

  }

  public ApoioED getByRecord(HttpServletRequest request)throws Excecoes{

      ApoioED ed = new ApoioED();

      String oid_Apoio = request.getParameter("oid_Apoio");
      if (String.valueOf(oid_Apoio) != null &&
          !String.valueOf(oid_Apoio).equals("") &&
          !String.valueOf(oid_Apoio).equals("null")){
      ed.setOID_Apoio(new Long(oid_Apoio).longValue());
      }

      return new ApoioRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    ApoioED ed = new ApoioED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
    /* SETAR O ED PARA PESQUISA NO BD
    */


    ApoioRN geRN = new ApoioRN();
    geRN.geraRelatorio(ed);
  }

}