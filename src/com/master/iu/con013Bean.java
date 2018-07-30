package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.SugestaoED;
import com.master.rn.SugestaoRN;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class con013Bean {

  public SugestaoED inclui(SugestaoED ed)throws Excecoes{

    try{
      SugestaoRN Sugestaorn = new SugestaoRN();

      return Sugestaorn.inclui(ed);
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
      SugestaoRN Sugestaorn = new SugestaoRN();
      SugestaoED ed = new SugestaoED();
      ed.setOid_Sugestao(new Long (request.getParameter("FT_CD_Sugestao")).longValue());
      ed.setNM_Sugestao(request.getParameter("FT_NM_Sugestao"));

      Sugestaorn.altera(ed);
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
      SugestaoRN Sugestaorn = new SugestaoRN();
      SugestaoED ed = new SugestaoED();

      ed.setOid_Sugestao(new Long (request.getParameter("FT_CD_Sugestao")).longValue());
      ed.setNM_Sugestao(request.getParameter("FT_NM_Sugestao"));

      Sugestaorn.deleta(ed);
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

  public ArrayList Sugestao_Lista(HttpServletRequest request)throws Excecoes{
      SugestaoED ed = new SugestaoED();
      return new SugestaoRN().lista(ed);

  }

  public SugestaoED getByRecord(HttpServletRequest request)throws Excecoes{

      SugestaoED ed = new SugestaoED();
      String oid_Sugestao = request.getParameter("FT_CD_Sugestao");
      if (String.valueOf(oid_Sugestao) != null &&
          !String.valueOf(oid_Sugestao).equals("") &&
          !String.valueOf(oid_Sugestao).equals("null")){
        ed.setOid_Sugestao(new Long(oid_Sugestao).longValue());
      }

      return new SugestaoRN().getByRecord(ed);

  }

    public SugestaoED getByModelo(String oid)throws Excecoes{

      SugestaoED ed = new SugestaoED();


      return new SugestaoRN().getByModelo(oid);
  }


    public SugestaoED getByOid(String Oid)throws Excecoes{

      SugestaoED ed = new SugestaoED();
      if (String.valueOf(Oid) != null &&
          !String.valueOf(Oid).equals("") &&
          !String.valueOf(Oid).equals("null")){
        ed.setOid_Sugestao(new Long(Oid).longValue());
      }

      return new SugestaoRN().getByRecord(ed);

  }


    public ArrayList getByNM_Sugestao(HttpServletRequest request)throws Excecoes{

      SugestaoED ed = new SugestaoED();
      String nm_Sugestao = request.getParameter("FT_NM_Sugestao");

      if (nm_Sugestao != null &&
          !nm_Sugestao.equals("") &&
          !nm_Sugestao.equals("null")){
        ed.setNM_Sugestao(nm_Sugestao);
      }

      return new SugestaoRN().getByNM_Sugestao(ed);

  }


}