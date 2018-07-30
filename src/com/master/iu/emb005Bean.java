package com.master.iu;

import javax.servlet.http.*;
import com.master.rn.Ocorrencia_EmbarqueRN;
import com.master.ed.Ocorrencia_EmbarqueED;
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

public class emb005Bean {

  public Ocorrencia_EmbarqueED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Ocorrencia_EmbarqueRN Ocorrencia_Embarquern = new Ocorrencia_EmbarqueRN();
      Ocorrencia_EmbarqueED ed = new Ocorrencia_EmbarqueED();

      //request em cima dos campos dos forms html

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Ocorrencia_Embarque(request.getParameter("FT_DT_Ocorrencia_Embarque"));
      ed.setHR_Ocorrencia_Embarque(request.getParameter("FT_HR_Ocorrencia_Embarque"));
      ed.setDT_Ocorrencia_Lancada(request.getParameter("FT_DT_Ocorrencia_Lancada"));
      ed.setHR_Ocorrencia_Lancada(request.getParameter("FT_HR_Ocorrencia_Lancada"));
      ed.setOID_Tipo_Ocorrencia(new Long(request.getParameter("oid_Tipo_Ocorrencia")).longValue());

      ed.setOID_Cidade(new Long(request.getParameter("oid_Cidade")).longValue());
      ed.setNM_Cidade(request.getParameter("FT_NM_Cidade"));
      ed.setCD_Estado(request.getParameter("FT_CD_Estado"));

      ed.setOID_Embarque(new Long(request.getParameter("oid_Embarque")).longValue());
      ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));

      if(request.getParameter("FT_NR_Odometro_Parcial") != null && 
        !request.getParameter("FT_NR_Odometro_Parcial").equals("null") &&
        !request.getParameter("FT_NR_Odometro_Parcial").equals("")){
          ed.setNR_Odometro_Parcial(new Long(request.getParameter("FT_NR_Odometro_Parcial")).longValue());
      }
      return Ocorrencia_Embarquern.inclui(ed);
    }

    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
exc.printStackTrace();
      excecoes.setMensagem("erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }

  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Ocorrencia_EmbarqueRN Ocorrencia_Embarquern = new Ocorrencia_EmbarqueRN();
      Ocorrencia_EmbarqueED ed = new Ocorrencia_EmbarqueED();

      ed.setOID_Ocorrencia_Embarque(new Long(request.getParameter("oid_Ocorrencia_Embarque")).longValue());
      ed.setOID_Embarque(new Long(request.getParameter("oid_Embarque")).longValue());
      ed.setOID_Cidade(new Long(request.getParameter("oid_Cidade")).longValue());
      ed.setOID_Tipo_Ocorrencia(new Long(request.getParameter("oid_Tipo_Ocorrencia")).longValue());
      ed.setDT_Ocorrencia_Embarque(request.getParameter("FT_DT_Ocorrencia_Embarque"));
      ed.setHR_Ocorrencia_Embarque(request.getParameter("FT_HR_Ocorrencia_Embarque"));
      ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));

      ed.setNR_Odometro_Parcial(new Long(request.getParameter("FT_NR_Odometro_Parcial")).longValue());

      Ocorrencia_Embarquern.altera(ed);
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
      Ocorrencia_EmbarqueRN Ocorrencia_Embarquern = new Ocorrencia_EmbarqueRN();
      Ocorrencia_EmbarqueED ed = new Ocorrencia_EmbarqueED();

      ed.setOID_Ocorrencia_Embarque(new Long(request.getParameter("oid_Ocorrencia_Embarque")).longValue());

      Ocorrencia_Embarquern.deleta(ed);
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

  public ArrayList Ocorrencia_Embarque_Lista(HttpServletRequest request)throws Excecoes{

      Ocorrencia_EmbarqueED ed = new Ocorrencia_EmbarqueED();

      String OID_Tipo_Ocorrencia = request.getParameter("oid_Tipo_Ocorrencia");
      if (String.valueOf(OID_Tipo_Ocorrencia) != null &&
          !String.valueOf(OID_Tipo_Ocorrencia).equals("") &&
          !String.valueOf(OID_Tipo_Ocorrencia).equals("null")){
        ed.setOID_Tipo_Ocorrencia(new Long(OID_Tipo_Ocorrencia).longValue());
      }
      ed.setDT_Ocorrencia_Embarque(request.getParameter("FT_DT_Ocorrencia_Embarque"));
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));

      String nr_Embarque = request.getParameter("FT_NR_Embarque");

      if (String.valueOf(nr_Embarque) != null &&
        !String.valueOf(nr_Embarque).equals("") &&
        !String.valueOf(nr_Embarque).equals("null")){
        ed.setNR_Embarque(new Long(nr_Embarque).longValue());
      }
      String oid_Embarque = request.getParameter("oid_Embarque");

      if (String.valueOf(oid_Embarque) != null &&
        !String.valueOf(oid_Embarque).equals("") &&
        !String.valueOf(oid_Embarque).equals("null")){
        ed.setOID_Embarque(new Long(oid_Embarque).longValue());
      }
      
      String oid_Manifesto = request.getParameter("oid_Manifesto");

      if (String.valueOf(oid_Manifesto) != null &&
          !String.valueOf(oid_Manifesto).equals("") &&
          !String.valueOf(oid_Manifesto).equals("null")){
        ed.setOID_Manifesto(oid_Manifesto);
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

      return new Ocorrencia_EmbarqueRN().lista(ed);

  }

  public Ocorrencia_EmbarqueED getByRecord(HttpServletRequest request)throws Excecoes{

      Ocorrencia_EmbarqueED ed = new Ocorrencia_EmbarqueED();

      String oid_Ocorrencia_Embarque = request.getParameter("oid_Ocorrencia_Embarque");
      if (String.valueOf(oid_Ocorrencia_Embarque) != null &&
          !String.valueOf(oid_Ocorrencia_Embarque).equals("") &&
          !String.valueOf(oid_Ocorrencia_Embarque).equals("null")){
        ed.setOID_Ocorrencia_Embarque(new Long(oid_Ocorrencia_Embarque).longValue());
      }

      return new Ocorrencia_EmbarqueRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Ocorrencia_EmbarqueED ed = new Ocorrencia_EmbarqueED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
    /* SETAR O ED PARA PESQUISA NO BD
    */


    Ocorrencia_EmbarqueRN geRN = new Ocorrencia_EmbarqueRN();
    geRN.geraRelatorio(ed);
  }

}
