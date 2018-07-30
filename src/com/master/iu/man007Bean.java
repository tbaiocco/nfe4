package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Movimento_OrdemED;
import com.master.rn.Movimento_OrdemRN;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class man007Bean {

  public void inclui(HttpServletRequest request)throws Excecoes{

    try{
      Movimento_OrdemRN Movimento_Ordemrn = new Movimento_OrdemRN();
      Movimento_OrdemED ed = new Movimento_OrdemED();

      //request em cima dos campos dos forms html

      ed.setDT_Movimento_Ordem(request.getParameter("FT_DT_Movimento_Ordem"));
      ed.setHR_Movimento_Ordem(request.getParameter("FT_HR_Movimento_Ordem"));
      ed.setOID_Ordem_Frete(request.getParameter("oid_Ordem_Frete"));
      ed.setOID_Compromisso(new Integer(request.getParameter("oid_Compromisso")));

      String nr_Compromisso = request.getParameter("FT_NR_Compromisso");

      if (String.valueOf(nr_Compromisso) != null &&
        !String.valueOf(nr_Compromisso).equals("") &&
        !String.valueOf(nr_Compromisso).equals("null")){
        ed.setNR_Compromisso(new Long(nr_Compromisso).longValue());
      }

      Movimento_Ordemrn.inclui(ed);
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
      Movimento_OrdemRN Movimento_Ordemrn = new Movimento_OrdemRN();
      Movimento_OrdemED ed = new Movimento_OrdemED();

      ed.setOID_Movimento_Ordem(request.getParameter("oid_Movimento_Ordem"));
      ed.setOID_Ordem_Frete(request.getParameter("oid_Ordem_Frete"));
      ed.setDT_Movimento_Ordem(request.getParameter("FT_DT_Movimento_Ordem"));
      ed.setHR_Movimento_Ordem(request.getParameter("FT_HR_Movimento_Ordem"));

      Movimento_Ordemrn.altera(ed);
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
      Movimento_OrdemRN Movimento_Ordemrn = new Movimento_OrdemRN();
      Movimento_OrdemED ed = new Movimento_OrdemED();

      ed.setOID_Movimento_Ordem(request.getParameter("oid_Movimento_Ordem"));

      Movimento_Ordemrn.deleta(ed);
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

  public ArrayList Movimento_Ordem_Lista(HttpServletRequest request)throws Excecoes{

      Movimento_OrdemED ed = new Movimento_OrdemED();

      String OID_Ordem_Frete = request.getParameter("oid_Ordem_Frete");
      if (String.valueOf(OID_Ordem_Frete) != null &&
          !String.valueOf(OID_Ordem_Frete).equals("") &&
          !String.valueOf(OID_Ordem_Frete).equals("null")){
        ed.setOID_Ordem_Frete(OID_Ordem_Frete);
      }
      ed.setDT_Movimento_Ordem(request.getParameter("FT_DT_Movimento_Ordem"));

      String nr_Compromisso = request.getParameter("FT_NR_Compromisso");

      if (String.valueOf(nr_Compromisso) != null &&
        !String.valueOf(nr_Compromisso).equals("") &&
        !String.valueOf(nr_Compromisso).equals("null")){
        ed.setNR_Compromisso(new Long(nr_Compromisso).longValue());
      }

      return new Movimento_OrdemRN().lista(ed);

  }

  public Movimento_OrdemED getByRecord(HttpServletRequest request)throws Excecoes{

      Movimento_OrdemED ed = new Movimento_OrdemED();

      String oid_Movimento_Ordem = request.getParameter("oid_Movimento_Ordem");
      if (String.valueOf(oid_Movimento_Ordem) != null &&
          !String.valueOf(oid_Movimento_Ordem).equals("") &&
          !String.valueOf(oid_Movimento_Ordem).equals("null")){
        ed.setOID_Movimento_Ordem(oid_Movimento_Ordem);
      }

      return new Movimento_OrdemRN().getByRecord(ed);
  }
}