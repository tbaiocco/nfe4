package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Variacao_CambialED;
import com.master.rn.Variacao_CambialRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;


public class Variacao_CambialBean  {

  Utilitaria util = new Utilitaria();


  public void geraVariacao_Cambial(HttpServletRequest request)throws Excecoes{
    String DT_Periodo_Inicial = request.getParameter("FT_DT_Periodo_Inicial");
    String DT_Periodo_Final = request.getParameter("FT_DT_Periodo_Final");
    String oid_Unidade = request.getParameter("oid_Unidade");
    String VL_Cotacao_Inicial = request.getParameter("FT_VL_Cotacao_Inicial");
    String VL_Cotacao_Final = request.getParameter("FT_VL_Cotacao_Final");

    Variacao_CambialED ed = new Variacao_CambialED();

    if (util.doValida(oid_Unidade)) {
      ed.setOid_Unidade(new Long(oid_Unidade).longValue());
    }

    if (util.doValida(DT_Periodo_Inicial)) {
        ed.setDT_Periodo_Inicial(DT_Periodo_Inicial);
    }
    if (util.doValida(DT_Periodo_Final)) {
        ed.setDT_Periodo_Final(DT_Periodo_Final);
    }
    if (util.doValida(VL_Cotacao_Inicial)) {
        ed.setVL_Cotacao_Inicial(new Double(VL_Cotacao_Inicial).doubleValue());
    }
    if (util.doValida(VL_Cotacao_Final)) {
        ed.setVL_Cotacao_Final(new Double(VL_Cotacao_Final).doubleValue());
    }


    new Variacao_CambialRN().geraVariacao_Cambial(ed);
}

//  public void deleta(HttpServletRequest request)throws Excecoes{
//
//    try{
//      Variacao_CambialRN Variacao_Cambialrn = new Variacao_CambialRN();
//      Variacao_CambialED ed = new Variacao_CambialED();
//
//      ed.setOID_Variacao_Cambial(request.getParameter("oid_Variacao_Cambial"));
//
//      Variacao_Cambialrn.deleta(ed);
//    }
//    catch (Excecoes exc){
//      throw exc;
//    }
//    catch(Exception exc){
//      Excecoes excecoes = new Excecoes();
//      excecoes.setClasse(this.getClass().getName());
//      excecoes.setMensagem("erro ao excluir");
//      excecoes.setMetodo("excluir");
//      excecoes.setExc(exc);
//      throw excecoes;
//    }
//  }


  public ArrayList lista(HttpServletRequest request)throws Excecoes{

      Variacao_CambialED ed = new Variacao_CambialED();


      return new Variacao_CambialRN().lista(ed);

  }

  public Variacao_CambialED getByRecord(HttpServletRequest request) throws Excecoes{
        Variacao_CambialED ed = new Variacao_CambialED();

        String oid_Variacao_Cambial = request.getParameter("oid_Variacao_Cambial");
        ed.setOid_Variacao_Cambial(new Long(oid_Variacao_Cambial).longValue());

        return new Variacao_CambialRN().getByRecord(ed);
  }




//    public  byte[] geraVariacao_CambialEmbarque(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
//
//      Variacao_CambialED ed = new Variacao_CambialED();
//
//      String nr_Variacao_Cambial = request.getParameter("FT_NR_Variacao_Cambial");
//      if (String.valueOf(nr_Variacao_Cambial) != null && !String.valueOf(nr_Variacao_Cambial).equals("")){
//        ed.setNR_Variacao_Cambial(new Long(nr_Variacao_Cambial).longValue());
//      }
//      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
//      ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
//      ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
//
//      String oid_Pessoa_Pagador = request.getParameter("oid_Pessoa_Pagador");
//      if (String.valueOf(oid_Pessoa_Pagador) != null && !String.valueOf(oid_Pessoa_Pagador).equals("")){
//        ed.setOID_Pessoa_Pagador(oid_Pessoa_Pagador);
//      }
//
//      String oid_Pessoa_Entregadora = request.getParameter("oid_Pessoa_Redespacho");
//      if (String.valueOf(oid_Pessoa_Entregadora) != null && !String.valueOf(oid_Pessoa_Entregadora).equals("")){
//        ed.setOID_Pessoa_Entregadora(oid_Pessoa_Entregadora);
//      }
//
//      String Dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
//      if (String.valueOf(Dt_Emissao_Inicial) != null && !String.valueOf(Dt_Emissao_Inicial).equals("")){
//        ed.setDt_Emissao_Inicial(Dt_Emissao_Inicial);
//      }
//
//      String Dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
//      if (String.valueOf(Dt_Emissao_Final) != null && !String.valueOf(Dt_Emissao_Final).equals("")){
//        ed.setDt_Emissao_Final(Dt_Emissao_Final);
//      }
//
//      String Dm_Situacao_Cobranca = request.getParameter("FT_DM_Situacao_Cobranca");
//      if (String.valueOf(Dm_Situacao_Cobranca) != null && !String.valueOf(Dm_Situacao_Cobranca).equals("")){
//        ed.setDm_Situacao_Cobranca(Dm_Situacao_Cobranca);
//      }
//
//      String DM_Tipo_Embarque = request.getParameter("FT_DM_Tipo_Embarque");
//      if (String.valueOf(DM_Tipo_Embarque) != null && !String.valueOf(DM_Tipo_Embarque).equals("")){
//        ed.setDM_Tipo_Embarque(DM_Tipo_Embarque);
//      }
//
//      String oid_Unidade = request.getParameter("oid_Unidade");
//      if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")){
//        ed.setOID_Unidade(new Long(oid_Unidade).longValue());
//      }
//
//      String Dm_Situacao_Entrega = request.getParameter("FT_DM_Situacao_Entrega");
//      if (String.valueOf(Dm_Situacao_Entrega) != null && !String.valueOf(Dm_Situacao_Entrega).equals("")){
//        ed.setDM_Situacao_Entrega(Dm_Situacao_Entrega);
//      }
//
//      String Dm_Situacao = request.getParameter("FT_DM_Situacao");
//      if (String.valueOf(Dm_Situacao) != null && !String.valueOf(Dm_Situacao).equals("")){
//        ed.setDM_Situacao(Dm_Situacao);
//      }
//
//      String Dm_Origem = request.getParameter("FT_DM_Origem");
//      if (String.valueOf(Dm_Origem) != null && !String.valueOf(Dm_Origem).equals("")){
//        ed.setDM_Origem(Dm_Origem);
//      }
//
//      String Dm_Destino = request.getParameter("FT_DM_Destino");
//      if (String.valueOf(Dm_Destino) != null && !String.valueOf(Dm_Destino).equals("")){
//        ed.setDM_Destino(Dm_Destino);
//      }
//
//      ed.setOID_Origem(0);
//      if (!Dm_Origem.equals("T")) {
//        String oid_Origem = request.getParameter("oid_Origem");
//        if (String.valueOf(oid_Origem) != null && !String.valueOf(oid_Origem).equals("0")){
//          ed.setOID_Origem(new Long(oid_Origem).longValue());
//        }
//      }
//
//      ed.setOID_Destino(0);
//      if (!Dm_Destino.equals("T")) {
//        String oid_Destino = request.getParameter("oid_Destino");
//        if (String.valueOf(oid_Destino) != null && !String.valueOf(oid_Destino).equals("0")){
//          ed.setOID_Destino(new Long(oid_Destino).longValue());
//        }
//      }
//
//      String DM_Tipo_Pagamento = request.getParameter("FT_DM_Tipo_Pagamento");
//      if (String.valueOf(DM_Tipo_Pagamento) != null && !String.valueOf(DM_Tipo_Pagamento).equals("")){
//        ed.setDM_Tipo_Pagamento(DM_Tipo_Pagamento);
//      }
//
//      String Dm_Tipo_Variacao_Cambial = request.getParameter("FT_DM_Tipo_Variacao_Cambial");
//      if (String.valueOf(Dm_Tipo_Variacao_Cambial) != null && !String.valueOf(Dm_Tipo_Variacao_Cambial).equals("")){
//        ed.setDM_Tipo_Variacao_Cambial(Dm_Tipo_Variacao_Cambial);
//      }
//
//      String VL_Nota_Fiscal_Inicial = request.getParameter("FT_VL_Nota_Fiscal_Inicial");
//      if (String.valueOf(VL_Nota_Fiscal_Inicial) != null && !String.valueOf(VL_Nota_Fiscal_Inicial).equals("") && !String.valueOf(VL_Nota_Fiscal_Inicial).equals("null")){
//        ed.setVL_Nota_Fiscal_Inicial(new Double(VL_Nota_Fiscal_Inicial).doubleValue());
//      }
//
//      String VL_Nota_Fiscal_Final = request.getParameter("FT_VL_Nota_Fiscal_Final");
//      if (String.valueOf(VL_Nota_Fiscal_Final) != null && !String.valueOf(VL_Nota_Fiscal_Final).equals("") && !String.valueOf(VL_Nota_Fiscal_Final).equals("null")){
//        ed.setVL_Nota_Fiscal_Final(new Double(VL_Nota_Fiscal_Final).doubleValue());
//      }
//
//       String oid_Empresa = request.getParameter("oid_Empresa");
//      if (String.valueOf(oid_Empresa) != null &&
//          !String.valueOf(oid_Empresa).equals("") &&
//          !String.valueOf(oid_Empresa).equals("null")){
//         ed.setOID_Empresa(new Long(request.getParameter("oid_Empresa")).longValue());
//    }
//
//
//      ed.setDM_Relatorio(request.getParameter("FT_DM_Relatorio"));
//
//      Variacao_CambialRN ctoRN = new Variacao_CambialRN();
//      byte[] b = ctoRN.geraRelatorioConheEmbarque(ed);
//
//      return b;
//
//    }


}
