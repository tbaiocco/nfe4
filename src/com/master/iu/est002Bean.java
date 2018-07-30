package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Grupo_EstoqueED;
import com.master.rn.Grupo_EstoqueRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class est002Bean {

  public void inclui(HttpServletRequest request)throws Excecoes{

    try{
      Grupo_EstoqueRN Grupo_Estoquern = new Grupo_EstoqueRN();
      Grupo_EstoqueED ed = new Grupo_EstoqueED();

      //request em cima dos campos dos forms html
      ed.setNM_Grupo_Estoque(request.getParameter("FT_NM_Grupo_Estoque"));
      ed.setCD_Grupo_Estoque(request.getParameter("FT_CD_Grupo_Estoque"));

      Grupo_Estoquern.inclui(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Grupo_EstoqueRN Grupo_Estoquern = new Grupo_EstoqueRN();
      Grupo_EstoqueED ed = new Grupo_EstoqueED();

      ed.setNM_Grupo_Estoque(request.getParameter("FT_NM_Grupo_Estoque"));
      ed.setCD_Grupo_Estoque(request.getParameter("FT_CD_Grupo_Estoque"));
      ed.setOID_Grupo_Estoque(new Integer(request.getParameter("oid_Grupo_Estoque")).intValue());

      Grupo_Estoquern.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Grupo_EstoqueRN Grupo_Estoquern = new Grupo_EstoqueRN();
      Grupo_EstoqueED ed = new Grupo_EstoqueED();

      ed.setOID_Grupo_Estoque(new Integer(request.getParameter("oid_Grupo_Estoque")).intValue());

      Grupo_Estoquern.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public ArrayList Grupo_Estoque_Lista(HttpServletRequest request)throws Excecoes{

      Grupo_EstoqueED ed = new Grupo_EstoqueED();

      ed.setCD_Grupo_Estoque(request.getParameter("FT_CD_Grupo_Estoque"));
      ed.setNM_Grupo_Estoque(request.getParameter("FT_NM_Grupo_Estoque"));
      return new Grupo_EstoqueRN().lista(ed);

  }

  public Grupo_EstoqueED getByRecord(HttpServletRequest request)throws Excecoes{

      Grupo_EstoqueED ed = new Grupo_EstoqueED();

      String oid_Grupo_Estoque = request.getParameter("oid_Grupo_Estoque");
      if (oid_Grupo_Estoque != null && oid_Grupo_Estoque.length() > 0)
      {
        ed.setOID_Grupo_Estoque(new Long(request.getParameter("oid_Grupo_Estoque")).longValue());
      }
      ed.setCD_Grupo_Estoque(request.getParameter("FT_CD_Grupo_Estoque"));
      ed.setNM_Grupo_Estoque(request.getParameter("FT_NM_Grupo_Estoque"));
      return new Grupo_EstoqueRN().getByRecord(ed);

  }

  public byte[] geraRelatorio(HttpServletRequest req,HttpServletResponse res)throws Excecoes{
    byte[] b=null;
    String arquivo = null;
    try{
      Grupo_EstoqueED ed = new Grupo_EstoqueED();

      ed.setCD_Grupo_Estoque(req.getParameter("codigo"));
      ed.setNM_Grupo_Estoque(req.getParameter("nome"));

      Grupo_EstoqueRN geRN = new Grupo_EstoqueRN();
      b = geRN.geraRelatorio(ed);


      new EnviaPDF().enviaBytes(req,res,b);

    }
    catch(Exception exc){
      exc.printStackTrace();
    }
    return b;
  }

}