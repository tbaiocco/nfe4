package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.FaturamentoCRTED;
import com.master.rn.FaturamentoCRTRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class fat001CRTBean {



  public  ArrayList inclui(HttpServletRequest request)throws Excecoes{

    try{
      FaturamentoCRTED ed = new FaturamentoCRTED();

      String VL_Fatura = request.getParameter("FT_VL_Fatura");
      if (String.valueOf(VL_Fatura) != null && !String.valueOf(VL_Fatura).equals("")){
        ed.setVL_Fatura(new Double(VL_Fatura).doubleValue());
      }
      String VL_Cotacao = request.getParameter("FT_VL_Cotacao");
      if (String.valueOf(VL_Cotacao) != null && !String.valueOf(VL_Cotacao).equals("")){
        ed.setVL_Cotacao(new Double(VL_Cotacao).doubleValue());
      }

      // System.out.println("faturam  incluiFatura bean ");
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      //// System.out.println("faturam  bean 11");
      ed.setDM_Tipo_Faturamento(request.getParameter("FT_DM_Tipo_Faturamento"));
      //// System.out.println("faturam  bean 12");
      ed.setDT_Emissao_Inicial(request.getParameter("FT_DT_Emissao_Inicial"));

      String DT_Vencimento = request.getParameter("FT_DT_Vencimento");
      // System.out.println("faturam  bean DT_Vencimento" + DT_Vencimento);
      if (String.valueOf(DT_Vencimento) != null && !String.valueOf(DT_Vencimento).equals("") && !String.valueOf(DT_Vencimento).equals("null")){
        ed.setDT_Vencimento(DT_Vencimento);
      }

      String DT_Cotacao = request.getParameter("FT_DT_Cotacao");
      // System.out.println("faturam  bean DT_Cotacao" + DT_Cotacao);
      if (String.valueOf(DT_Cotacao) != null && !String.valueOf(DT_Cotacao).equals("") && !String.valueOf(DT_Cotacao).equals("null")){
        ed.setDT_Cotacao(DT_Cotacao);
      }

 
      String DT_Vencimento_Minimo = request.getParameter("FT_DT_Vencimento_Minimo");
      // System.out.println("faturam  bean DT_Vencimento_Minimo" + DT_Vencimento_Minimo);
      if (String.valueOf(DT_Vencimento_Minimo) != null && !String.valueOf(DT_Vencimento_Minimo).equals("") && !String.valueOf(DT_Vencimento_Minimo).equals("null")){
        ed.setDT_Vencimento_Minimo(DT_Vencimento_Minimo);
      }

      //// System.out.println("faturam  bean 13");
      ed.setDT_Emissao_Final(request.getParameter("FT_DT_Emissao_Final"));
      //// System.out.println("faturam  bean 14");
      ed.setOID_Pessoa_Pagador(request.getParameter("oid_Pessoa_Pagador"));
      //// System.out.println("faturam  bean 15");

      String NR_Conhecimento_Sequencia = request.getParameter("FT_NR_Conhecimento_Sequencia");
      if (String.valueOf(NR_Conhecimento_Sequencia) != null && 
      		!String.valueOf(NR_Conhecimento_Sequencia).equals("") && 
			!String.valueOf(NR_Conhecimento_Sequencia).equals("null")){
        ed.setNR_Conhecimento_Sequencia(NR_Conhecimento_Sequencia);
      }else{
        String NR_Conhecimento_Inicial = request.getParameter("FT_NR_Conhecimento_Inicial");
        //// System.out.println("faturam  bean 16");
        if (String.valueOf(NR_Conhecimento_Inicial) != null && !String.valueOf(NR_Conhecimento_Inicial).equals("")){
          ed.setNR_Conhecimento_Inicial(new Long(NR_Conhecimento_Inicial).longValue());
        }
        //// System.out.println("faturam  bean 17");
        String NR_Conhecimento_Final = request.getParameter("FT_NR_Conhecimento_Final");
        if (String.valueOf(NR_Conhecimento_Final) != null && !String.valueOf(NR_Conhecimento_Final).equals("")){
          ed.setNR_Conhecimento_Final(new Long(NR_Conhecimento_Final).longValue());
        }
      }
      
      //// System.out.println("faturam  bean 18");

      String OID_Unidade = request.getParameter("oid_Unidade");
      // System.out.println("faturam  bean Unidade->" + OID_Unidade);
      if (String.valueOf(OID_Unidade) != null && !String.valueOf(OID_Unidade).equals("")){
        ed.setOID_Unidade_CTRC(new Long(OID_Unidade).longValue());
      }
      // System.out.println("faturam  bean Unidade depois->" + ed.getOID_Unidade());

      String OID_Unidade_Cobranca = request.getParameter("oid_Unidade_Cobranca");
      // System.out.println("faturam  bean Unidade_Cobranca->" + OID_Unidade_Cobranca);
      if (String.valueOf(OID_Unidade_Cobranca) != null && !String.valueOf(OID_Unidade_Cobranca).equals("")){
        ed.setOID_Unidade_Cobranca(new Long(OID_Unidade_Cobranca).longValue());
      }

      //// System.out.println("faturam  bean 18");
      String OID_Produto = request.getParameter("oid_Produto");
      if (String.valueOf(OID_Produto) != null && !String.valueOf(OID_Produto).equals("")){
        ed.setOID_Produto(new Long(OID_Produto).longValue());
      }
      // System.out.println("faturam  bean OID_Produto depois->" + ed.getOID_Produto());

      //// System.out.println("faturam  bean 18");
      String OID_Tipo_Faturamento = request.getParameter("oid_Tipo_Faturamento");
      if (String.valueOf(OID_Tipo_Faturamento) != null && !String.valueOf(OID_Tipo_Faturamento).equals("")){
        ed.setOid_Tipo_Faturamento(new Long(OID_Tipo_Faturamento).longValue());
      }

      //// System.out.println("faturam  bean 19");
      ed.setOID_Carteira_Informada(0);
      String OID_Carteira_Informada = request.getParameter("oid_Carteira");
      if (String.valueOf(OID_Carteira_Informada) != null && !String.valueOf(OID_Carteira_Informada).equals("")){
        ed.setOID_Carteira_Informada(new Long(OID_Carteira_Informada).longValue());
      }

      String DM_Tipo_Conhecimento = request.getParameter("FT_DM_Tipo_Conhecimento");
      if (String.valueOf(DM_Tipo_Conhecimento) != null && !String.valueOf(DM_Tipo_Conhecimento).equals("")){
        ed.setDM_Tipo_Conhecimento(DM_Tipo_Conhecimento);
      }
      String NM_Lote_Faturamento = request.getParameter("FT_NM_Lote_Faturamento");
      if (JavaUtil.doValida(NM_Lote_Faturamento)) {
          ed.setNM_Lote_Faturamento(NM_Lote_Faturamento);
      }

      ////// System.out.println("faturam  bean 20");
      return new FaturamentoCRTRN().inclui(ed);

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
      FaturamentoCRTRN FaturamentoCRTrn = new FaturamentoCRTRN();
      FaturamentoCRTED ed = new FaturamentoCRTED();

      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));

      FaturamentoCRTrn.altera(ed);
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
      FaturamentoCRTRN FaturamentoCRTrn = new FaturamentoCRTRN();
      FaturamentoCRTED ed = new FaturamentoCRTED();

      ed.setOID_Fatura(request.getParameter("oid_Faturamento"));

      FaturamentoCRTrn.deleta(ed);
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

  public ArrayList Faturamento_Lista(HttpServletRequest request)throws Excecoes{

      FaturamentoCRTED ed = new FaturamentoCRTED();

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      String nr_Faturamento = request.getParameter("FT_NR_Faturamento");
      if (String.valueOf(nr_Faturamento) != null && !String.valueOf(nr_Faturamento).equals("")){
        ed.setNR_Fatura(new Long(nr_Faturamento).longValue());
      }
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")){
        ed.setOID_Unidade(new Long(oid_Unidade).longValue());
      }
      return new FaturamentoCRTRN().lista(ed);

  }

  public FaturamentoCRTED getByRecord(HttpServletRequest request)throws Excecoes{

      FaturamentoCRTED ed = new FaturamentoCRTED();

      String NR_Faturamento = request.getParameter("FT_NR_Faturamento");
      if (NR_Faturamento != null && NR_Faturamento.length() > 0)
      {
        ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
      }
      ed.setOID_Fatura(request.getParameter("oid_Faturamento"));
      return new FaturamentoCRTRN().getByRecord(ed);

  }

//  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
//    FaturamentoED ed = new FaturamentoED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
    /* SETAR O ED PARA PESQUISA NO BD
    */


//    FaturamentoRN geRN = new FaturamentoRN();
//    geRN.geraRelatorio(ed);
//  }

}
