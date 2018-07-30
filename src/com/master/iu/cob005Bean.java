package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Ocorrencia_DuplicataED;
import com.master.rn.Ocorrencia_DuplicataRN;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class cob005Bean {

  public Ocorrencia_DuplicataED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Ocorrencia_DuplicataRN Ocorrencia_Duplicatarn = new Ocorrencia_DuplicataRN();
      Ocorrencia_DuplicataED ed = new Ocorrencia_DuplicataED();

      //request em cima dos campos dos forms html

      ed.setDT_Ocorrencia_Duplicata(request.getParameter("FT_DT_Ocorrencia_Duplicata"));
      ed.setHR_Ocorrencia_Duplicata(request.getParameter("FT_HR_Ocorrencia_Duplicata"));
      ed.setOID_Tipo_Ocorrencia(new Long(request.getParameter("oid_Tipo_Ocorrencia")).longValue());
      ed.setOID_Duplicata(new Long(request.getParameter("oid_Duplicata")).longValue());
      ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));

      return Ocorrencia_Duplicatarn.inclui(ed);
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
      Ocorrencia_DuplicataRN Ocorrencia_Duplicatarn = new Ocorrencia_DuplicataRN();
      Ocorrencia_DuplicataED ed = new Ocorrencia_DuplicataED();

      ed.setOID_Ocorrencia_Duplicata(new Long(request.getParameter("oid_Ocorrencia_Duplicata")).longValue());
      ed.setOID_Tipo_Ocorrencia(new Long(request.getParameter("oid_Tipo_Ocorrencia")).longValue());
      ed.setDT_Ocorrencia_Duplicata(request.getParameter("FT_DT_Ocorrencia_Duplicata"));
      ed.setHR_Ocorrencia_Duplicata(request.getParameter("FT_HR_Ocorrencia_Duplicata"));
      ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));

      Ocorrencia_Duplicatarn.altera(ed);
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
      Ocorrencia_DuplicataRN Ocorrencia_Duplicatarn = new Ocorrencia_DuplicataRN();
      Ocorrencia_DuplicataED ed = new Ocorrencia_DuplicataED();

      ed.setOID_Ocorrencia_Duplicata(new Long(request.getParameter("oid_Ocorrencia_Duplicata")).longValue());

      Ocorrencia_Duplicatarn.deleta(ed);
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

  public ArrayList Ocorrencia_Duplicata_Lista(HttpServletRequest request)throws Excecoes{

      Ocorrencia_DuplicataED ed = new Ocorrencia_DuplicataED();

      String OID_Tipo_Ocorrencia = request.getParameter("oid_Tipo_Ocorrencia");
      if (String.valueOf(OID_Tipo_Ocorrencia) != null &&
          !String.valueOf(OID_Tipo_Ocorrencia).equals("") &&
          !String.valueOf(OID_Tipo_Ocorrencia).equals("null")){
        ed.setOID_Tipo_Ocorrencia(new Long(OID_Tipo_Ocorrencia).longValue());
      }
      ed.setDT_Ocorrencia_Duplicata(request.getParameter("FT_DT_Ocorrencia_Duplicata"));
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));

      String nr_Duplicata = request.getParameter("FT_NR_Duplicata");

      if (String.valueOf(nr_Duplicata) != null &&
        !String.valueOf(nr_Duplicata).equals("") &&
        !String.valueOf(nr_Duplicata).equals("null")){
        ed.setNR_Duplicata(new Long(nr_Duplicata).longValue());
      }
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
      ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null &&
        !String.valueOf(oid_Unidade).equals("") &&
        !String.valueOf(oid_Unidade).equals("null")){
        ed.setOID_Unidade(new Long(oid_Unidade).longValue());
      }

      return new Ocorrencia_DuplicataRN().lista(ed);

  }

  public Ocorrencia_DuplicataED getByRecord(HttpServletRequest request)throws Excecoes{

      Ocorrencia_DuplicataED ed = new Ocorrencia_DuplicataED();

      String oid_Ocorrencia_Duplicata = request.getParameter("oid_Ocorrencia_Duplicata");
      if (String.valueOf(oid_Ocorrencia_Duplicata) != null &&
          !String.valueOf(oid_Ocorrencia_Duplicata).equals("") &&
          !String.valueOf(oid_Ocorrencia_Duplicata).equals("null")){
        ed.setOID_Ocorrencia_Duplicata(new Long(oid_Ocorrencia_Duplicata).longValue());
      }

      return new Ocorrencia_DuplicataRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Ocorrencia_DuplicataED ed = new Ocorrencia_DuplicataED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
    /* SETAR O ED PARA PESQUISA NO BD
    */


    Ocorrencia_DuplicataRN geRN = new Ocorrencia_DuplicataRN();
    geRN.geraRelatorio(ed);
  }

}