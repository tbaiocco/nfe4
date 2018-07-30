package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Ocorrencia_Tabela_FreteED;
import com.master.rn.Ocorrencia_Tabela_FreteRN;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class com105Bean {

  public Ocorrencia_Tabela_FreteED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Ocorrencia_Tabela_FreteRN Ocorrencia_Tabela_Fretern = new Ocorrencia_Tabela_FreteRN();
      Ocorrencia_Tabela_FreteED ed = new Ocorrencia_Tabela_FreteED();

      //request em cima dos campos dos forms html

      ed.setDT_Ocorrencia_Tabela_Frete(request.getParameter("FT_DT_Ocorrencia_Tabela_Frete"));
      ed.setHR_Ocorrencia_Tabela_Frete(request.getParameter("FT_HR_Ocorrencia_Tabela_Frete"));
      ed.setOID_Tipo_Ocorrencia(new Long(request.getParameter("oid_Tipo_Ocorrencia")).longValue());
      ed.setOID_Tabela_Frete(new Long(request.getParameter("oid_Tabela_Frete")).longValue());
      ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
      if (ed.getTX_Observacao().equals("null") || ed.getTX_Observacao().equals(null) || ed.getTX_Observacao().equals("")) {
          ed.setTX_Observacao(" ");
      }
     
      ed.setDM_Tipo(request.getParameter("FT_DM_Tipo"));
      ed.setDM_Acesso(request.getParameter("FT_DM_Acesso"));
      ed.setDM_Avaria(request.getParameter("FT_DM_Avaria"));
      ed.setDM_Status(request.getParameter("FT_DM_Status"));

      return Ocorrencia_Tabela_Fretern.inclui(ed);
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
      Ocorrencia_Tabela_FreteRN Ocorrencia_Tabela_Fretern = new Ocorrencia_Tabela_FreteRN();
      Ocorrencia_Tabela_FreteED ed = new Ocorrencia_Tabela_FreteED();

      ed.setOID_Ocorrencia_Tabela_Frete(new Long(request.getParameter("oid_Ocorrencia_Tabela_Frete")).longValue());
      ed.setOID_Tipo_Ocorrencia(new Long(request.getParameter("oid_Tipo_Ocorrencia")).longValue());
      ed.setDT_Ocorrencia_Tabela_Frete(request.getParameter("FT_DT_Ocorrencia_Tabela_Frete"));
      ed.setHR_Ocorrencia_Tabela_Frete(request.getParameter("FT_HR_Ocorrencia_Tabela_Frete"));
      ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
      if (ed.getTX_Observacao().equals("null") || ed.getTX_Observacao().equals(null) || ed.getTX_Observacao().equals("")) {
          ed.setTX_Observacao(" ");
      }
      
      Ocorrencia_Tabela_Fretern.altera(ed);
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
      Ocorrencia_Tabela_FreteRN Ocorrencia_Tabela_Fretern = new Ocorrencia_Tabela_FreteRN();
      Ocorrencia_Tabela_FreteED ed = new Ocorrencia_Tabela_FreteED();

      ed.setOID_Ocorrencia_Tabela_Frete(new Long(request.getParameter("oid_Ocorrencia_Tabela_Frete")).longValue());

      Ocorrencia_Tabela_Fretern.deleta(ed);
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

  public ArrayList Ocorrencia_Tabela_Frete_Lista(HttpServletRequest request)throws Excecoes{

      Ocorrencia_Tabela_FreteED ed = new Ocorrencia_Tabela_FreteED();

      String OID_Tipo_Ocorrencia = request.getParameter("oid_Tipo_Ocorrencia");
      if (String.valueOf(OID_Tipo_Ocorrencia) != null &&
          !String.valueOf(OID_Tipo_Ocorrencia).equals("") &&
          !String.valueOf(OID_Tipo_Ocorrencia).equals("null")){
        ed.setOID_Tipo_Ocorrencia(new Long(OID_Tipo_Ocorrencia).longValue());
      }
      ed.setDT_Ocorrencia_Tabela_Frete(request.getParameter("FT_DT_Ocorrencia_Tabela_Frete"));
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));

      String nr_Cotacao = request.getParameter("FT_NR_Cotacao");

      if (String.valueOf(nr_Cotacao) != null &&
        !String.valueOf(nr_Cotacao).equals("") &&
        !String.valueOf(nr_Cotacao).equals("null")){
        ed.setNR_Cotacao(new Long(nr_Cotacao).longValue());
      }
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null &&
        !String.valueOf(oid_Unidade).equals("") &&
        !String.valueOf(oid_Unidade).equals("null")){
        ed.setOID_Unidade(new Long(oid_Unidade).longValue());
      }

      return new Ocorrencia_Tabela_FreteRN().lista(ed);

  }

  public Ocorrencia_Tabela_FreteED getByRecord(HttpServletRequest request)throws Excecoes{

      Ocorrencia_Tabela_FreteED ed = new Ocorrencia_Tabela_FreteED();

      String oid_Ocorrencia_Tabela_Frete = request.getParameter("oid_Ocorrencia_Tabela_Frete");
      if (String.valueOf(oid_Ocorrencia_Tabela_Frete) != null &&
          !String.valueOf(oid_Ocorrencia_Tabela_Frete).equals("") &&
          !String.valueOf(oid_Ocorrencia_Tabela_Frete).equals("null")){
        ed.setOID_Ocorrencia_Tabela_Frete(new Long(oid_Ocorrencia_Tabela_Frete).longValue());
      }

      return new Ocorrencia_Tabela_FreteRN().getByRecord(ed);

  }

/**  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Ocorrencia_Tabela_FreteED ed = new Ocorrencia_Tabela_FreteED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
    /* SETAR O ED PARA PESQUISA NO BD
    */


/**    Ocorrencia_Tabela_FreteRN geRN = new Ocorrencia_Tabela_FreteRN();
    geRN.geraRelatorio(ed);
  }*/

}