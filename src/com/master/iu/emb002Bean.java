package com.master.iu;

import javax.servlet.http.*;
import com.master.rn.Nota_Fiscal_EmbarqueRN;
import com.master.ed.Nota_Fiscal_EmbarqueED;
import com.master.util.Excecoes;
import java.util.*;
 
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class emb002Bean {

  public Nota_Fiscal_EmbarqueED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Nota_Fiscal_EmbarqueRN Nota_Fiscal_Embarquern = new Nota_Fiscal_EmbarqueRN();
      Nota_Fiscal_EmbarqueED ed = new Nota_Fiscal_EmbarqueED();

      ed.setDT_Nota_Fiscal_Embarque(request.getParameter("FT_DT_Nota_Fiscal_Embarque"));
      ed.setHR_Nota_Fiscal_Embarque(request.getParameter("FT_HR_Nota_Fiscal_Embarque"));
      ed.setOID_Nota_Fiscal(request.getParameter("oid_Nota_Fiscal"));
      ed.setOID_Embarque(request.getParameter("oid_Embarque"));

      return Nota_Fiscal_Embarquern.inclui(ed);
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
      Nota_Fiscal_EmbarqueRN Nota_Fiscal_Embarquern = new Nota_Fiscal_EmbarqueRN();
      Nota_Fiscal_EmbarqueED ed = new Nota_Fiscal_EmbarqueED();

      ed.setOID_Nota_Fiscal_Embarque(request.getParameter("oid_Nota_Fiscal_Embarque"));
      ed.setDT_Nota_Fiscal_Embarque(request.getParameter("FT_DT_Nota_Fiscal_Embarque"));
      ed.setHR_Nota_Fiscal_Embarque(request.getParameter("FT_HR_Nota_Fiscal_Embarque"));

      Nota_Fiscal_Embarquern.altera(ed);
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
      Nota_Fiscal_EmbarqueRN Nota_Fiscal_Embarquern = new Nota_Fiscal_EmbarqueRN();
      Nota_Fiscal_EmbarqueED ed = new Nota_Fiscal_EmbarqueED();

      ed.setOID_Nota_Fiscal_Embarque(request.getParameter("oid_Nota_Fiscal_Embarque"));
      ed.setOID_Nota_Fiscal(request.getParameter("oid_Nota_Fiscal"));

      Nota_Fiscal_Embarquern.deleta(ed);
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
 
  public ArrayList Nota_Fiscal_Embarque_Lista(HttpServletRequest request)throws Excecoes{

      Nota_Fiscal_EmbarqueED ed = new Nota_Fiscal_EmbarqueED();

      String nr_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");

      if (String.valueOf(nr_Nota_Fiscal) != null &&
        !String.valueOf(nr_Nota_Fiscal).equals("") &&
        !String.valueOf(nr_Nota_Fiscal).equals("null")){
        ed.setNR_Nota_Fiscal(new Long(nr_Nota_Fiscal).longValue());
      }
      ed.setOID_Embarque(request.getParameter("oid_Embarque"));

      String oid_Manifesto = request.getParameter("oid_Manifesto");

      if (String.valueOf(oid_Manifesto) != null &&
          !String.valueOf(oid_Manifesto).equals("") &&
          !String.valueOf(oid_Manifesto).equals("null")){
        ed.setOID_Manifesto(oid_Manifesto);
      }

      ed.setOid_Pessoa_Senha(request.getParameter("oid_Pessoa_Senha"));

      return new Nota_Fiscal_EmbarqueRN().lista(ed);

  }

  public Nota_Fiscal_EmbarqueED getByRecord(HttpServletRequest request)throws Excecoes{

      Nota_Fiscal_EmbarqueED ed = new Nota_Fiscal_EmbarqueED();

      String oid_Nota_Fiscal_Embarque = request.getParameter("oid_Nota_Fiscal_Embarque");
      if (String.valueOf(oid_Nota_Fiscal_Embarque) != null &&
          !String.valueOf(oid_Nota_Fiscal_Embarque).equals("") &&
          !String.valueOf(oid_Nota_Fiscal_Embarque).equals("null")){
        ed.setOID_Nota_Fiscal_Embarque(oid_Nota_Fiscal_Embarque);
      }

      return new Nota_Fiscal_EmbarqueRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Nota_Fiscal_EmbarqueED ed = new Nota_Fiscal_EmbarqueED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
    /* SETAR O ED PARA PESQUISA NO BD
    */


    Nota_Fiscal_EmbarqueRN geRN = new Nota_Fiscal_EmbarqueRN();
    geRN.geraRelatorio(ed);
  }

}
