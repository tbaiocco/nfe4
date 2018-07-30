package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Taxa_UnidadeED;
import com.master.rn.Taxa_UnidadeRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class cad011Bean {

  public Taxa_UnidadeED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Taxa_UnidadeRN Taxa_Unidadern = new Taxa_UnidadeRN();
      Taxa_UnidadeED ed = new Taxa_UnidadeED();

      ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
      ed.setOid_Tipo_Servico(new Long(request.getParameter("oid_Tipo_Servico")).longValue());

      String PE_ISSQN = request.getParameter("FT_PE_ISSQN");
      if (PE_ISSQN != null && !PE_ISSQN.equals("") && !PE_ISSQN.equals("null")){
        ed.setPe_Issqn(new Double(PE_ISSQN).doubleValue());
      }

      return Taxa_Unidadern.inclui(ed);
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
      Taxa_UnidadeRN Taxa_Unidadern = new Taxa_UnidadeRN();
      Taxa_UnidadeED ed = new Taxa_UnidadeED();

      ed.setOid_Taxa_Unidade(new Long(request.getParameter("oid_Taxa_Unidade")).longValue());
      ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
      ed.setOid_Tipo_Servico(new Long(request.getParameter("oid_Tipo_Servico")).longValue());

      String PE_ISSQN = request.getParameter("FT_PE_ISSQN");
      if (PE_ISSQN != null && !PE_ISSQN.equals("") && !PE_ISSQN.equals("null")){
        ed.setPe_Issqn(new Double(PE_ISSQN).doubleValue());
      }

      Taxa_Unidadern.altera(ed);
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
      Taxa_UnidadeRN Taxa_Unidadern = new Taxa_UnidadeRN();
      Taxa_UnidadeED ed = new Taxa_UnidadeED();

      ed.setOid_Taxa_Unidade(new Long(request.getParameter("oid_Taxa_Unidade")).longValue());

      Taxa_Unidadern.deleta(ed);
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

  public ArrayList Taxa_Unidade_Lista(HttpServletRequest request)throws Excecoes{

      Taxa_UnidadeED ed = new Taxa_UnidadeED();

      if(JavaUtil.doValida(String.valueOf(request.getParameter("oid_Taxa_Unidade"))))
    	  ed.setOid_Taxa_Unidade(new Long(request.getParameter("oid_Taxa_Unidade")).longValue());
      if(JavaUtil.doValida(String.valueOf(request.getParameter("oid_Unidade"))))
    	  ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
      if(JavaUtil.doValida(String.valueOf(request.getParameter("oid_Tipo_Servico"))))
    	  ed.setOid_Tipo_Servico(new Long(request.getParameter("oid_Tipo_Servico")).longValue());

      return new Taxa_UnidadeRN().lista(ed);

  }

  public Taxa_UnidadeED getByRecord(HttpServletRequest request)throws Excecoes{

      Taxa_UnidadeED ed = new Taxa_UnidadeED();

      ed.setOid_Taxa_Unidade(new Long(request.getParameter("oid_Taxa_Unidade")).longValue());

      return new Taxa_UnidadeRN().getByRecord(ed);

  }

  public Taxa_UnidadeED getByRecord(long Taxa_Unidade)throws Excecoes{

      Taxa_UnidadeED ed = new Taxa_UnidadeED();

      ed.setOid_Taxa_Unidade(new Long(Taxa_Unidade).longValue());

      return new Taxa_UnidadeRN().getByRecord(ed);

  }

  public Taxa_UnidadeED getByRecord(long Unidade, long Tipo_Servico)throws Excecoes{

      Taxa_UnidadeED ed = new Taxa_UnidadeED();

      if(JavaUtil.doValida(String.valueOf(Unidade)))
    	  ed.setOid_Unidade(new Long(Unidade).longValue());
      if(JavaUtil.doValida(String.valueOf(Tipo_Servico)))
    	  ed.setOid_Tipo_Servico(new Long(Tipo_Servico).longValue());

      return new Taxa_UnidadeRN().getByRecord(ed);

  }


}