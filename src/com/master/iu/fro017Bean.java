package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.RotaED;
import com.master.rn.RotaRN;
import com.master.util.Excecoes;

public class fro017Bean {

  public RotaED inclui(HttpServletRequest request)throws Excecoes{

    try{
      RotaRN Rotarn = new RotaRN();
      RotaED ed = new RotaED();

      ed.setNM_Rodovia(request.getParameter("FT_NM_Rodovia"));
      ed.setNR_KM(new Double(request.getParameter("FT_NR_KM")).doubleValue());
      ed.setNR_HR(request.getParameter("FT_NR_HR"));
      ed.setNR_Trecho(request.getParameter("FT_NR_Trecho"));
      ed.setNR_Media_Horaria(request.getParameter("FT_NR_Media"));

      ed.setOID_Cidade(new Long(request.getParameter("oid_Cidade_Origem")).longValue());
      ed.setOID_Cidade_Destino(new Long(request.getParameter("oid_Cidade_Destino")).longValue());

      return Rotarn.inclui(ed);
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
      RotaRN Rotarn = new RotaRN();
      RotaED ed = new RotaED();

      ed.setOID_Rota(request.getParameter("oid_Rota"));
      ed.setOID_Cidade(new Long(request.getParameter("oid_Cidade_Origem")).longValue());
      ed.setOID_Cidade_Destino(new Long(request.getParameter("oid_Cidade_Destino")).longValue());
      ed.setNM_Rodovia(request.getParameter("FT_NM_Rodovia"));
      ed.setNR_KM(new Double(request.getParameter("FT_NR_KM")).doubleValue());
      ed.setNR_HR(request.getParameter("FT_NR_HR"));
      ed.setNR_Trecho(request.getParameter("FT_NR_Trecho"));
      ed.setNR_Media_Horaria(request.getParameter("FT_NR_Media"));

      Rotarn.altera(ed);
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
      RotaRN Rotarn = new RotaRN();
      RotaED ed = new RotaED();

      ed.setOID_Rota(request.getParameter("oid_Rota"));

      Rotarn.deleta(ed);
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

  public ArrayList Rota_Lista(HttpServletRequest request)throws Excecoes{

      RotaED ed = new RotaED();

      String OID_Cidade = request.getParameter("oid_Cidade_Origem");
      // //// // System.out.println(OID_Cidade);
      if (OID_Cidade != null && !OID_Cidade.equals("null") && !OID_Cidade.equals("")){
        ed.setOID_Cidade(new Long(OID_Cidade).longValue());
      }

      String OID_Cidade_Destino = request.getParameter("oid_Cidade_Destino");
      if (OID_Cidade_Destino != null && !OID_Cidade_Destino.equals("null") && !OID_Cidade_Destino.equals("")){
        ed.setOID_Cidade_Destino(new Long(OID_Cidade_Destino).longValue());
      }
      ed.setNM_Rodovia(request.getParameter("FT_NM_Rodovia"));
      ed.setOID_Rota(request.getParameter("FT_NR_Trecho"));

      return new RotaRN().lista(ed);

  }

  public RotaED getByRecord(HttpServletRequest request)throws Excecoes{

      RotaED ed = new RotaED();
      ed.setOID_Rota(request.getParameter("oid_Rota"));
      return new RotaRN().getByRecord(ed);
  }
}