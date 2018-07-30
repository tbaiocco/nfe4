package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Conhecimento_RomaneioED;
import com.master.rn.Conhecimento_RomaneioRN;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ent006Bean {

  public void inclui(HttpServletRequest request)throws Excecoes{

    try{
      Conhecimento_RomaneioRN Conhecimento_Romaneiorn = new Conhecimento_RomaneioRN();
      Conhecimento_RomaneioED ed = new Conhecimento_RomaneioED();

      //request em cima dos campos dos forms html

      ed.setDT_Conhecimento_Romaneio(request.getParameter("FT_DT_Conhecimento_Romaneio"));
      ed.setHR_Conhecimento_Romaneio(request.getParameter("FT_HR_Conhecimento_Romaneio"));
      ed.setOID_Romaneio_Entrega(request.getParameter("oid_Romaneio_Entrega"));
      ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));

      String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");

      if (String.valueOf(nr_Conhecimento) != null &&
        !String.valueOf(nr_Conhecimento).equals("") &&
        !String.valueOf(nr_Conhecimento).equals("null")){
        ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
      }

      Conhecimento_Romaneiorn.inclui(ed);
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
      Conhecimento_RomaneioRN Conhecimento_Romaneiorn = new Conhecimento_RomaneioRN();
      Conhecimento_RomaneioED ed = new Conhecimento_RomaneioED();

      ed.setOID_Conhecimento_Romaneio(request.getParameter("oid_Conhecimento_Romaneio"));
      ed.setOID_Romaneio_Entrega(request.getParameter("oid_Romaneio_Entrega"));
      ed.setDT_Conhecimento_Romaneio(request.getParameter("FT_DT_Conhecimento_Romaneio"));
      ed.setHR_Conhecimento_Romaneio(request.getParameter("FT_HR_Conhecimento_Romaneio"));

      Conhecimento_Romaneiorn.altera(ed);
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
      Conhecimento_RomaneioRN Conhecimento_Romaneiorn = new Conhecimento_RomaneioRN();
      Conhecimento_RomaneioED ed = new Conhecimento_RomaneioED();

      ed.setOID_Conhecimento_Romaneio(request.getParameter("oid_Conhecimento_Romaneio"));

      Conhecimento_Romaneiorn.deleta(ed);
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

  public ArrayList Conhecimento_Romaneio_Lista(HttpServletRequest request)throws Excecoes{

      Conhecimento_RomaneioED ed = new Conhecimento_RomaneioED();

      String OID_Romaneio_Entrega = request.getParameter("oid_Romaneio_Entrega");
      if (String.valueOf(OID_Romaneio_Entrega) != null &&
          !String.valueOf(OID_Romaneio_Entrega).equals("") &&
          !String.valueOf(OID_Romaneio_Entrega).equals("null")){
      ed.setOID_Romaneio_Entrega(request.getParameter("oid_Romaneio_Entrega"));
      }
      ed.setDT_Conhecimento_Romaneio(request.getParameter("FT_DT_Conhecimento_Romaneio"));

      String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");

      if (String.valueOf(nr_Conhecimento) != null &&
        !String.valueOf(nr_Conhecimento).equals("") &&
        !String.valueOf(nr_Conhecimento).equals("null")){
        ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
      }
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null &&
        !String.valueOf(oid_Unidade).equals("") &&
        !String.valueOf(oid_Unidade).equals("null")){
        ed.setOID_Unidade(new Long(oid_Unidade).longValue());
      }

      return new Conhecimento_RomaneioRN().lista(ed);

  }

  public Conhecimento_RomaneioED getByRecord(HttpServletRequest request)throws Excecoes{

      Conhecimento_RomaneioED ed = new Conhecimento_RomaneioED();

      String oid_Conhecimento_Romaneio = request.getParameter("oid_Conhecimento_Romaneio");
      if (String.valueOf(oid_Conhecimento_Romaneio) != null &&
          !String.valueOf(oid_Conhecimento_Romaneio).equals("") &&
          !String.valueOf(oid_Conhecimento_Romaneio).equals("null")){
      ed.setOID_Conhecimento_Romaneio(request.getParameter("oid_Conhecimento_Romaneio"));
      }

      return new Conhecimento_RomaneioRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Conhecimento_RomaneioED ed = new Conhecimento_RomaneioED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
    /* SETAR O ED PARA PESQUISA NO BD
    */


    Conhecimento_RomaneioRN geRN = new Conhecimento_RomaneioRN();
    geRN.geraRelatorio(ed);
  }

}