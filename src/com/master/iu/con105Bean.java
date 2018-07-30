package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Ocorrencia_ConhecimentoED;
import com.master.rn.Ocorrencia_Conhecimento_InternacionalRN;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class con105Bean {

  public Ocorrencia_ConhecimentoED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Ocorrencia_Conhecimento_InternacionalRN Ocorrencia_Conhecimentorn = new Ocorrencia_Conhecimento_InternacionalRN();
      Ocorrencia_ConhecimentoED ed = new Ocorrencia_ConhecimentoED();

      //request em cima dos campos dos forms html

      ed.setDT_Ocorrencia_Conhecimento(request.getParameter("FT_DT_Ocorrencia_Conhecimento"));
      ed.setHR_Ocorrencia_Conhecimento(request.getParameter("FT_HR_Ocorrencia_Conhecimento"));
      ed.setOID_Tipo_Ocorrencia(new Long(request.getParameter("oid_Tipo_Ocorrencia")).longValue());
      ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
      ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
      ed.setNM_Pessoa_Entrega(request.getParameter("FT_NM_Pessoa_Entrega"));
      if (ed.getTX_Observacao().equals("null") || ed.getTX_Observacao().equals(null) || ed.getTX_Observacao().equals("")) {
          ed.setTX_Observacao(" ");
      }
      if (ed.getNM_Pessoa_Entrega().equals("null") || ed.getNM_Pessoa_Entrega().equals(null) || ed.getNM_Pessoa_Entrega().equals("")) {
          ed.setNM_Pessoa_Entrega(" ");
      }

      ed.setDM_Tipo(request.getParameter("FT_DM_Tipo"));
      ed.setDM_Acesso(request.getParameter("FT_DM_Acesso"));
      ed.setDM_Avaria(request.getParameter("FT_DM_Avaria"));
      ed.setDM_Status(request.getParameter("FT_DM_Status"));

      return Ocorrencia_Conhecimentorn.inclui(ed);
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
      Ocorrencia_Conhecimento_InternacionalRN Ocorrencia_Conhecimentorn = new Ocorrencia_Conhecimento_InternacionalRN();
      Ocorrencia_ConhecimentoED ed = new Ocorrencia_ConhecimentoED();

      ed.setOID_Ocorrencia_Conhecimento(new Long(request.getParameter("oid_Ocorrencia_Conhecimento")).longValue());
      ed.setOID_Tipo_Ocorrencia(new Long(request.getParameter("oid_Tipo_Ocorrencia")).longValue());
      ed.setDT_Ocorrencia_Conhecimento(request.getParameter("FT_DT_Ocorrencia_Conhecimento"));
      ed.setHR_Ocorrencia_Conhecimento(request.getParameter("FT_HR_Ocorrencia_Conhecimento"));
      ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
      if (ed.getTX_Observacao().equals("null") || ed.getTX_Observacao().equals(null) || ed.getTX_Observacao().equals("")) {
          ed.setTX_Observacao(" ");
      }
      if (ed.getNM_Pessoa_Entrega().equals("null") || ed.getNM_Pessoa_Entrega().equals(null) || ed.getNM_Pessoa_Entrega().equals("")) {
          ed.setNM_Pessoa_Entrega(" ");
      }

      Ocorrencia_Conhecimentorn.altera(ed);
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
      Ocorrencia_Conhecimento_InternacionalRN Ocorrencia_Conhecimentorn = new Ocorrencia_Conhecimento_InternacionalRN();
      Ocorrencia_ConhecimentoED ed = new Ocorrencia_ConhecimentoED();

      ed.setOID_Ocorrencia_Conhecimento(new Long(request.getParameter("oid_Ocorrencia_Conhecimento")).longValue());

      Ocorrencia_Conhecimentorn.deleta(ed);
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

  public ArrayList Ocorrencia_Conhecimento_Lista(HttpServletRequest request)throws Excecoes{

      Ocorrencia_ConhecimentoED ed = new Ocorrencia_ConhecimentoED();

      String OID_Tipo_Ocorrencia = request.getParameter("oid_Tipo_Ocorrencia");
      if (String.valueOf(OID_Tipo_Ocorrencia) != null &&
          !String.valueOf(OID_Tipo_Ocorrencia).equals("") &&
          !String.valueOf(OID_Tipo_Ocorrencia).equals("null")){
        ed.setOID_Tipo_Ocorrencia(new Long(OID_Tipo_Ocorrencia).longValue());
      }
      ed.setDT_Ocorrencia_Conhecimento(request.getParameter("FT_DT_Ocorrencia_Conhecimento"));
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));

      String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");

      if (String.valueOf(nr_Conhecimento) != null &&
        !String.valueOf(nr_Conhecimento).equals("") &&
        !String.valueOf(nr_Conhecimento).equals("null")){
        ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
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

      return new Ocorrencia_Conhecimento_InternacionalRN().lista(ed);

  }

  public Ocorrencia_ConhecimentoED getByRecord(HttpServletRequest request)throws Excecoes{

      Ocorrencia_ConhecimentoED ed = new Ocorrencia_ConhecimentoED();

      String oid_Ocorrencia_Conhecimento = request.getParameter("oid_Ocorrencia_Conhecimento");
      if (String.valueOf(oid_Ocorrencia_Conhecimento) != null &&
          !String.valueOf(oid_Ocorrencia_Conhecimento).equals("") &&
          !String.valueOf(oid_Ocorrencia_Conhecimento).equals("null")){
        ed.setOID_Ocorrencia_Conhecimento(new Long(oid_Ocorrencia_Conhecimento).longValue());
      }

      return new Ocorrencia_Conhecimento_InternacionalRN().getByRecord(ed);

  }

/**  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Ocorrencia_ConhecimentoED ed = new Ocorrencia_ConhecimentoED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
    /* SETAR O ED PARA PESQUISA NO BD
    */


/**    Ocorrencia_ConhecimentoRN geRN = new Ocorrencia_ConhecimentoRN();
    geRN.geraRelatorio(ed);
  }*/

}