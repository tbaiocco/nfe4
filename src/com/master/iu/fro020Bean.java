package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Rota_RoteiroED;
import com.master.rn.Rota_RoteiroRN;
import com.master.util.Excecoes;

public class fro020Bean {

  public Rota_RoteiroED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Rota_RoteiroRN Rota_Roteirorn = new Rota_RoteiroRN();
      Rota_RoteiroED ed = new Rota_RoteiroED();
      ed.setOID_Rota(new Long(request.getParameter("oid_Rota")).longValue());
      ed.setCD_Roteiro(request.getParameter("FT_CD_Roteiro"));
      ed.setNR_Sequencia(new Long(request.getParameter("FT_NR_Sequencia")).longValue());

      return Rota_Roteirorn.inclui(ed);
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
      Rota_RoteiroRN Rota_Roteirorn = new Rota_RoteiroRN();
      Rota_RoteiroED ed = new Rota_RoteiroED();
      ed.setOID_Rota_Roteiro(request.getParameter("oid_Rota_Roteiro"));
      ed.setNR_Sequencia(new Long(request.getParameter("FT_NR_Sequencia")).longValue());
      Rota_Roteirorn.altera(ed);
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
      Rota_RoteiroRN Rota_Roteirorn = new Rota_RoteiroRN();
      Rota_RoteiroED ed = new Rota_RoteiroED();

      ed.setOID_Rota_Roteiro(request.getParameter("oid_Rota_Roteiro"));

      Rota_Roteirorn.deleta(ed);
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

  public ArrayList Rota_Roteiro_Lista(HttpServletRequest request)throws Excecoes{

      Rota_RoteiroED ed = new Rota_RoteiroED();

      String OID_Rota = request.getParameter("oid_Rota");
      if (String.valueOf(OID_Rota) != null &&
          !String.valueOf(OID_Rota).equals("") &&
          !String.valueOf(OID_Rota).equals("null")){
        ed.setOID_Rota(new Long(OID_Rota).longValue());
      }

      String CD_Roteiro = request.getParameter("FT_CD_Roteiro");
      if (String.valueOf(CD_Roteiro) != null &&
        !String.valueOf(CD_Roteiro).equals("") &&
        !String.valueOf(CD_Roteiro).equals("null")){
        ed.setCD_Roteiro(CD_Roteiro);
      }

      return new Rota_RoteiroRN().lista(ed);
  }

  public Rota_RoteiroED getByRecord(HttpServletRequest request)throws Excecoes{

      Rota_RoteiroED ed = new Rota_RoteiroED();

      String oid_Rota_Roteiro = request.getParameter("oid_Rota_Roteiro");
      if (String.valueOf(oid_Rota_Roteiro) != null &&
          !String.valueOf(oid_Rota_Roteiro).equals("") &&
          !String.valueOf(oid_Rota_Roteiro).equals("null")){
        ed.setOID_Rota_Roteiro(request.getParameter("oid_Rota_Roteiro"));
      }

      return new Rota_RoteiroRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Rota_RoteiroED ed = new Rota_RoteiroED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
    /* SETAR O ED PARA PESQUISA NO BD
    */


    Rota_RoteiroRN geRN = new Rota_RoteiroRN();
    geRN.geraRelatorio(ed);
  }

}