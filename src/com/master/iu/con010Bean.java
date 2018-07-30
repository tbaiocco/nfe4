package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.LocalizaED;
import com.master.rn.LocalizaRN;
import com.master.root.ClienteBean;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class con010Bean {

  public LocalizaED inclui(HttpServletRequest request)throws Excecoes{

      /*
      * 1
      */

    try{
      LocalizaRN LocalizaRN = new LocalizaRN();
      LocalizaED ed = new LocalizaED();
      ClienteBean ClienteVolta = new ClienteBean();

      //request em cima dos campos dos forms html

      ed.setNR_Nota_Fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
      ed.setNM_Serie(request.getParameter("FT_NM_Serie"));
      ed.setDM_Transferencia(request.getParameter("FT_DM_Transferencia"));
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Entrada(request.getParameter("FT_DT_Entrada"));
      ed.setHR_Entrada(request.getParameter("FT_HR_Entrada"));
      ed.setNR_Pedido(request.getParameter("FT_NR_Pedido"));

      ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

      String NR_Peso = request.getParameter("FT_NR_Peso");
      if (String.valueOf(NR_Peso) != null && !String.valueOf(NR_Peso).equals("")){
        ed.setNR_Peso(new Double(NR_Peso).doubleValue());
      }
      String NR_Volumes = request.getParameter("FT_NR_Volumes");
      if (String.valueOf(NR_Volumes) != null && !String.valueOf(NR_Volumes).equals("")){
      ed.setNR_Volumes(new Long(request.getParameter("FT_NR_Volumes")).longValue());
      }
      String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
      if (String.valueOf(VL_Nota_Fiscal) != null && !String.valueOf(VL_Nota_Fiscal).equals("")){
        ed.setVL_Nota_Fiscal(new Double(VL_Nota_Fiscal).doubleValue());
      }

      return LocalizaRN.inclui(ed);
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
      LocalizaRN LocalizaRN = new LocalizaRN();
      LocalizaED ed = new LocalizaED();

      ed.setOID_Nota_Fiscal(request.getParameter("oid_Nota_Fiscal"));
      ed.setNR_Nota_Fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
      ed.setNM_Serie(request.getParameter("FT_NM_Serie"));
      ed.setDM_Transferencia(request.getParameter("FT_DM_Transferencia"));
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Entrada(request.getParameter("FT_DT_Entrada"));
      ed.setHR_Entrada(request.getParameter("FT_HR_Entrada"));
      ed.setNR_Pedido(request.getParameter("FT_NR_Pedido"));

      ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

      String NR_Peso = request.getParameter("FT_NR_Peso");
      if (String.valueOf(NR_Peso) != null && !String.valueOf(NR_Peso).equals("")){
        ed.setNR_Peso(new Double(NR_Peso).doubleValue());
      }
      String NR_Volumes = request.getParameter("FT_NR_Volumes");
      if (String.valueOf(NR_Volumes) != null && !String.valueOf(NR_Volumes).equals("")){
      ed.setNR_Volumes(new Long(request.getParameter("FT_NR_Volumes")).longValue());
      }
      String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
      if (String.valueOf(VL_Nota_Fiscal) != null && !String.valueOf(VL_Nota_Fiscal).equals("")){
        ed.setVL_Nota_Fiscal(new Double(VL_Nota_Fiscal).doubleValue());
      }

      LocalizaRN.altera(ed);
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
      LocalizaRN LocalizaRN = new LocalizaRN();
      LocalizaED ed = new LocalizaED();

      ed.setOID_Nota_Fiscal(request.getParameter("oid_Nota_Fiscal"));

      LocalizaRN.deleta(ed);
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

  public ArrayList Nota_Fiscal_Lista(HttpServletRequest request)throws Excecoes{

      LocalizaED ed = new LocalizaED();

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      String nr_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
      if (String.valueOf(nr_Nota_Fiscal) != null && !String.valueOf(nr_Nota_Fiscal).equals("")){
        ed.setNR_Nota_Fiscal(new Long(nr_Nota_Fiscal).longValue());
      }
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));

      return new LocalizaRN().lista(ed);

  }

  public LocalizaED getByRecord(HttpServletRequest request)throws Excecoes{

      LocalizaED ed = new LocalizaED();

      String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
      if (NR_Nota_Fiscal != null && NR_Nota_Fiscal.length() > 0)
      {
        ed.setNR_Nota_Fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
      }
      ed.setOID_Nota_Fiscal(request.getParameter("oid_Nota_Fiscal"));
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));

      return new LocalizaRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    LocalizaED ed = new LocalizaED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
    /* SETAR O ED PARA PESQUISA NO BD
    */


    LocalizaRN geRN = new LocalizaRN();
    geRN.geraRelatorio(ed);
  }

}