package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.LogradouroED;
import com.master.rn.LogradouroRN;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class cad200Bean {

  public LogradouroED inclui(HttpServletRequest request)throws Excecoes{

    try{
      LogradouroRN Logradourorn = new LogradouroRN();
      LogradouroED ed = new LogradouroED();

      ed.setNM_Logradouro(request.getParameter("FT_NM_Logradouro"));
      ed.setNM_Contato(request.getParameter("FT_NM_Contato"));
      ed.setNM_Telefone(request.getParameter("FT_NM_Telefone"));
      ed.setNM_Referencia(request.getParameter("FT_NM_Referencia"));
      ed.setNM_Endereco(request.getParameter("FT_NM_Endereco"));
      ed.setNR_CEP(request.getParameter("FT_NR_CEP"));
      ed.setOID_Rota(new Long(request.getParameter("oid_Rota")).longValue());
      ed.setOID_Cidade_Logradouro(new Long(request.getParameter("oid_Cidade_Logradouro")).longValue());

      String FT_NR_KM = request.getParameter("FT_NR_KM");
      if (FT_NR_KM != null && !FT_NR_KM.equals("") && !FT_NR_KM.equals("null")){
        ed.setNR_KM(new Double(FT_NR_KM).doubleValue());
      }

      ed.setDM_Tipo_Logradouro(request.getParameter("FT_DM_Tipo_Logradouro"));
      ed.setDM_Logradouro_Rastreado(request.getParameter("FT_DM_Logradouro_Rastreado"));

      return Logradourorn.inclui(ed);
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
      LogradouroRN Logradourorn = new LogradouroRN();
      LogradouroED ed = new LogradouroED();

      ed.setDM_Logradouro_Rastreado(request.getParameter("FT_DM_Logradouro_Rastreado"));

      ed.setOID_Logradouro(new Long(request.getParameter("oid_Logradouro")).longValue());
      ed.setOID_Rota(new Long(request.getParameter("oid_Rota")).longValue());
      ed.setOID_Cidade_Logradouro(new Long(request.getParameter("oid_Cidade_Logradouro")).longValue());
      ed.setNM_Contato(request.getParameter("FT_NM_Contato"));
      ed.setNM_Telefone(request.getParameter("FT_NM_Telefone"));
      ed.setDM_Tipo_Logradouro(request.getParameter("FT_DM_Tipo_Logradouro"));
      String FT_NR_KM = request.getParameter("FT_NR_KM");
      if (FT_NR_KM != null && !FT_NR_KM.equals("") && !FT_NR_KM.equals("null")){
        ed.setNR_KM(new Double(FT_NR_KM).doubleValue());
      }
      ed.setNM_Logradouro(request.getParameter("FT_NM_Logradouro"));
      ed.setNM_Referencia(request.getParameter("FT_NM_Referencia"));
      ed.setDM_Tipo_Logradouro(request.getParameter("FT_DM_Tipo_Logradouro"));
      ed.setOID_Cidade_Logradouro(new Long(request.getParameter("oid_Cidade_Logradouro")).longValue());
      ed.setNM_Endereco(request.getParameter("FT_NM_Endereco"));
      ed.setNR_CEP(request.getParameter("FT_NR_CEP"));

      Logradourorn.altera(ed);
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
      LogradouroRN Logradourorn = new LogradouroRN();
      LogradouroED ed = new LogradouroED();

      ed.setOID_Logradouro(new Long(request.getParameter("oid_Logradouro")).longValue());

      Logradourorn.deleta(ed);
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

  public ArrayList Logradouro_Lista(HttpServletRequest request)throws Excecoes{

      LogradouroED ed = new LogradouroED();

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

      return new LogradouroRN().lista(ed);

  }

  public LogradouroED getByRecord(HttpServletRequest request)throws Excecoes{

      LogradouroED ed = new LogradouroED();

      String oid_Logradouro = request.getParameter("oid_Logradouro");
      if (String.valueOf(oid_Logradouro) != null &&
          !String.valueOf(oid_Logradouro).equals("") &&
          !String.valueOf(oid_Logradouro).equals("null")){
      ed.setOID_Logradouro(new Long(oid_Logradouro).longValue());
      }

      return new LogradouroRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    LogradouroED ed = new LogradouroED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
    /* SETAR O ED PARA PESQUISA NO BD
    */


    LogradouroRN geRN = new LogradouroRN();
    geRN.geraRelatorio(ed);
  }

}