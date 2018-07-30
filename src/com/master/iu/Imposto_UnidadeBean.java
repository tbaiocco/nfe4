package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import com.master.util.*;

import com.master.ed.Imposto_UnidadeED;
import com.master.rn.Imposto_UnidadeRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class Imposto_UnidadeBean {

  public Imposto_UnidadeED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Imposto_UnidadeRN Imposto_UnidadeRN = new Imposto_UnidadeRN();
      Imposto_UnidadeED ed = new Imposto_UnidadeED();

      ed.setOid_Imposto(new Integer(request.getParameter("oid_Tipo_Imposto")).intValue());
      ed.setOid_Centro_Custo(new Integer(request.getParameter("oid_Centro_Custo")).intValue());
      ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")).intValue());
      ed.setOid_Unidade(new Integer(request.getParameter("oid_Unidade")).intValue());
      ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
      ed.setDm_Vencimento(request.getParameter("FT_DM_Vencimento"));

      return Imposto_UnidadeRN.inclui(ed);

    }

    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Imposto_UnidadeRN Imposto_UnidadeRN = new Imposto_UnidadeRN();
      Imposto_UnidadeED ed = new Imposto_UnidadeED();

      ed.setOid_Imposto_Unidade(new Integer(request.getParameter("oid_Imposto_Unidade")).intValue());
      ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
      ed.setOid_Centro_Custo(new Integer(request.getParameter("oid_Centro_Custo")).intValue());
      ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")).intValue());
      
      ed.setDm_Vencimento(request.getParameter("FT_DM_Vencimento"));

      Imposto_UnidadeRN.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Imposto_UnidadeRN Imposto_Unidadern = new Imposto_UnidadeRN();
      Imposto_UnidadeED ed = new Imposto_UnidadeED();

      ed.setOid_Imposto_Unidade(new Integer(request.getParameter("oid_Imposto_Unidade")).intValue());

      Imposto_Unidadern.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public ArrayList Imposto_Unidade_Lista(HttpServletRequest request)throws Excecoes{

      Imposto_UnidadeED ed = new Imposto_UnidadeED();

      if (JavaUtil.doValida (request.getParameter("oid_Tipo_Imposto"))){
          ed.setOid_Imposto(new Integer(request.getParameter("oid_Tipo_Imposto")).intValue());
      }
      if (JavaUtil.doValida (request.getParameter("oid_Unidade"))){
    	  ed.setOid_Unidade(new Integer(request.getParameter("oid_Unidade")).intValue());
      }

      return new Imposto_UnidadeRN().lista(ed);

  }

  public Imposto_UnidadeED getByRecord(HttpServletRequest request)throws Excecoes{

      Imposto_UnidadeED ed = new Imposto_UnidadeED();

      String oid_Imposto_Unidade = request.getParameter("oid_Imposto_Unidade");
      if (oid_Imposto_Unidade != null && oid_Imposto_Unidade.length() > 0)
      {
          ed.setOid_Imposto_Unidade(new Integer(request.getParameter("oid_Imposto_Unidade")).intValue());
      }

     return new Imposto_UnidadeRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Imposto_UnidadeED ed = new Imposto_UnidadeED();

//    ed.setCD_Imposto_Unidade(req.getParameter("codigo"));
//    ed.setCD_Remessa(req.getParameter("nome"));

    Imposto_UnidadeRN geRN = new Imposto_UnidadeRN();
    geRN.geraRelatorio(ed);
  }


}
