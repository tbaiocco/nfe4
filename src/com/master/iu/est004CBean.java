package com.master.iu;

import javax.servlet.http.*;

import com.master.rn.Modelo_Nota_FiscalRN;
import com.master.rn.Nota_Fiscal_CompraRN;
import com.master.rn.EstoqueRN;
import com.master.ed.Nota_Fiscal_CompraED;
import com.master.rn.ModeloNotaFiscalRN;
import com.master.ed.ModeloNotaFiscalED;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Valores;

import java.util.*;

import com.master.ed.Lote_CompromissoED;
import com.master.ed.Modelo_Nota_FiscalED;
import com.master.ed.Posto_CompromissoED;
import com.master.ed.Solicitacao_CompraED;

public class est004CBean {
  public est004CBean () {
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

  public Nota_Fiscal_CompraED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Nota_Fiscal_CompraRN Nota_Fiscal_CompraRN = new Nota_Fiscal_CompraRN();
      Modelo_Nota_FiscalRN Modelo_Nota_FiscalRN = new Modelo_Nota_FiscalRN();
      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();

      //request em cima dos campos dos forms html
      ed.setOid_modelo_nota_fiscal(new Long(request.getParameter("oid_Modelo")).longValue());
      Modelo_Nota_FiscalED Modelo = new Modelo_Nota_FiscalED();
      Modelo.setOid_Modelo_Nota_Fiscal(new Long(ed.getOid_modelo_nota_fiscal()).intValue());
      Modelo = Modelo_Nota_FiscalRN.getByRecord(Modelo);

      ed.setDM_Contabiliza(Modelo.getDM_Gera_Fiscal());

      ed.setOid_natureza_operacao(Long.parseLong(request.getParameter("oid_Natureza_Operacao")));
      if (JavaUtil.doValida(request.getParameter("oid_Natureza_Operacao_Servico"))) {
    	  ed.setOid_natureza_operacao_servico(Long.parseLong(request.getParameter("oid_Natureza_Operacao_Servico")));
      } else {
    	  ed.setOid_natureza_operacao_servico(0);
      }
      if (JavaUtil.doValida(request.getParameter("oid_Natureza_Operacao_Outros"))) {
    	  ed.setOid_natureza_operacao_outros(Long.parseLong(request.getParameter("oid_Natureza_Operacao_Outros")));
      } else {
    	  ed.setOid_natureza_operacao_outros(0);
      }
      ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      ed.setNm_serie(request.getParameter("FT_NM_Serie"));
      ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDt_entrada(request.getParameter("FT_DT_Entrada"));
      ed.setHr_entrada(request.getParameter("FT_HR_Entrada"));
      ed.setDm_forma_pagamento(request.getParameter("FT_FRM_PGTO"));
      if (JavaUtil.doValida(request.getParameter("FT_NR_PARCELAS"))) {
        ed.setNr_parcelas(Long.parseLong(request.getParameter("FT_NR_PARCELAS")));
      }
      ed.setOID_Unidade_Contabil(Long.parseLong(request.getParameter("oid_Unidade_Contabil")));
      ed.setOID_Unidade_Fiscal(Long.parseLong(request.getParameter("oid_Unidade_Fiscal")));
      ed.setOID_Unidade_Pagadora(Long.parseLong(request.getParameter("oid_Unidade_Fiscal")));

      if (request.getParameter("oid_Conta") != null && !request.getParameter("oid_Conta").equals("")) {
          ed.setOid_conta(Long.parseLong(request.getParameter("oid_Conta")));
      }
      if (JavaUtil.doValida(request.getParameter("oid_Conta_Servico") )) {
          ed.setOid_conta_servico(Long.parseLong(request.getParameter("oid_Conta_Servico")));
      }
      if (JavaUtil.doValida(request.getParameter("oid_Conta_Outros") )) {
          ed.setOid_conta_outros(Long.parseLong(request.getParameter("oid_Conta_Outros")));
      }
      if (JavaUtil.doValida(request.getParameter("oid_Centro_Custo"))) {
          ed.setOid_centro_custo(Long.parseLong(request.getParameter("oid_Centro_Custo")));
      }

 //Valor do ICMS
      String valor = request.getParameter("FT_VL_ICMS");
      double Cvalor = 0;

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_icms(Cvalor);


//Valor do INSS
      valor = request.getParameter("FT_VL_INSS");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_inss(Cvalor);


//Valor do IPI
      valor = request.getParameter("FT_VL_IPI");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_ipi(Cvalor);


//Valor do IRRF
      valor = request.getParameter("FT_VL_IRRF");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_irrf(Cvalor);


//Valor do ISQN
      valor = request.getParameter("FT_VL_ISQN");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_isqn(Cvalor);


//Valor do Frete
      valor = request.getParameter("FT_VL_Frete");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_total_frete(Cvalor);


//Valor do Seguro
      valor = request.getParameter("FT_VL_Seguro");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_total_seguro(Cvalor);

//Valor do Despesas
      valor = request.getParameter("FT_VL_Despesas");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_total_despesas(Cvalor);

//Valor do Despesas
      valor = request.getParameter("FT_VL_LIQUIDO");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }


      ed.setVl_liquido_nota_fiscal(Cvalor);


//Valor do Descontos
      valor = request.getParameter("FT_VL_Desconto");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_descontos(Cvalor);

//Valor do Servico
      valor = request.getParameter("FT_VL_Servico");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVL_Servico(Cvalor);

//    Valor do Outros
      valor = request.getParameter("FT_VL_Outros");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVL_Outros(Cvalor);

//Valor do PIS

      if(request.getParameter("FT_VL_PIS")!=null){
          valor = request.getParameter("FT_VL_PIS");
      }
      else{
          valor = request.getParameter("FT_VL_PIS2");
      }
      Cvalor = 0;

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
      	Cvalor = 0;
      else{
      	Cvalor = new Double(valor).doubleValue();
      }

      ed.setVL_Pis(Cvalor);

//Valor do Cofins

//      if(request.getParameter("FT_VL_COFINS")!=null){
//          valor = request.getParameter("FT_VL_COFINS");
//      }
//      else{
//          valor = request.getParameter("FT_VL_COFINS2");
//      }
//      Cvalor = 0;
      valor = request.getParameter("FT_VL_Cofins");
      if((valor == null) || (valor.equals("null"))||(valor.equals("")))
      	Cvalor = 0;
      else{
      	Cvalor = new Double(valor).doubleValue();
      }
      ed.setVL_Cofins(Cvalor);

//Valor do CSLL

      if(request.getParameter("FT_VL_CSLL")!=null){
          valor = request.getParameter("FT_VL_CSLL");
      }
      else{
          valor = request.getParameter("FT_VL_CSLL2");
      }
      Cvalor = 0;

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
      	Cvalor = 0;
      else{
      	Cvalor = new Double(valor).doubleValue();
      }

      ed.setVL_Csll(Cvalor);


      ed.setDm_observacao(request.getParameter("ft_mm_obs"));
      ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_TipoNota"));
      String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");

      if (String.valueOf(NR_Nota_Fiscal) != null && !String.valueOf(NR_Nota_Fiscal).equals("")
        && !String.valueOf(NR_Nota_Fiscal).equals("null")){
            ed.setNr_nota_fiscal(Long.parseLong(request.getParameter("FT_NR_Nota_Fiscal")));
      }
      else {
           ed.setNr_nota_fiscal(0);
      }
      String NR_Volumes = request.getParameter("FT_NR_Volumes");
      if (String.valueOf(NR_Volumes) != null && !String.valueOf(NR_Volumes).equals("")
        && !String.valueOf(NR_Volumes).equals("null")){
      ed.setNr_volumes(Long.parseLong(request.getParameter("FT_NR_Volumes")));
      }

      String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
      if (String.valueOf(VL_Nota_Fiscal) != null && !String.valueOf(VL_Nota_Fiscal).equals("")
        && !String.valueOf(VL_Nota_Fiscal).equals("null")){
        ed.setVl_nota_fiscal(Double.parseDouble(request.getParameter("FT_VL_Nota_Fiscal")));
      }else ed.setVl_nota_fiscal(0);

      if (ed.getDm_tipo_nota_fiscal().equals("0") || ed.getDm_tipo_nota_fiscal().equals("1") || ed.getDm_tipo_nota_fiscal().equals("P") )
        ed.setDm_finalizado("S");

      return Nota_Fiscal_CompraRN.inclui(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      exc.printStackTrace();
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

    // System.out.println ("altera 1=" + request.getParameter("FT_VL_LIQUIDO"));

      Nota_Fiscal_CompraRN Nota_Fiscal_CompraRN = new Nota_Fiscal_CompraRN();
      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();

      ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
      ed.setOid_modelo_nota_fiscal(Long.parseLong(request.getParameter("oid_Modelo")));
      ed.setOid_natureza_operacao(Long.parseLong(request.getParameter("oid_Natureza_Operacao")));
      if (request.getParameter("oid_Natureza_Operacao_Servico") != null && !request.getParameter("oid_Natureza_Operacao_Servico").equals("")) {
    	  ed.setOid_natureza_operacao_servico(Long.parseLong(request.getParameter("oid_Natureza_Operacao_Servico")));
      } else {
    	  ed.setOid_natureza_operacao_servico(0);
      }
      if (JavaUtil.doValida(request.getParameter("oid_Natureza_Operacao_Outros"))) {
    	  ed.setOid_natureza_operacao_outros(Long.parseLong(request.getParameter("oid_Natureza_Operacao_Outros")));
      } else {
    	  ed.setOid_natureza_operacao_outros(0);
      }

      ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      ed.setNm_serie(request.getParameter("FT_NM_Serie"));
      ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));
      ed.setVL_Servico(Double.parseDouble(request.getParameter("FT_VL_Servico")));
      ed.setVL_Outros(Double.parseDouble(request.getParameter("FT_VL_Outros")));
      ed.setDt_entrada(request.getParameter("FT_DT_Entrada"));
      ed.setHr_entrada(request.getParameter("FT_HR_Entrada"));
      ed.setDm_forma_pagamento(request.getParameter("FT_FRM_PGTO"));
      ed.setNr_parcelas(Long.parseLong(request.getParameter("FT_NR_PARCELAS")));
      ed.setOID_Unidade_Contabil(Long.parseLong(request.getParameter("oid_Unidade_Contabil")));
      ed.setOID_Unidade_Fiscal(Long.parseLong(request.getParameter("oid_Unidade_Fiscal")));
      ed.setOID_Unidade_Pagadora(Long.parseLong(request.getParameter("oid_Unidade_Fiscal")));
      String valor = request.getParameter("FT_VL_ICMS");

      if (request.getParameter("oid_Conta") != null && !request.getParameter("oid_Conta").equals("")) {
    	  ed.setOid_conta(Long.parseLong(request.getParameter("oid_Conta")));
      }
      if (request.getParameter("oid_Conta_Servico") != null && !request.getParameter("oid_Conta_Servico").equals("")) {
          ed.setOid_conta_servico(Long.parseLong(request.getParameter("oid_Conta_Servico")));
      }
      if (JavaUtil.doValida(request.getParameter("oid_Conta_Outros") )) {
          ed.setOid_conta_outros(Long.parseLong(request.getParameter("oid_Conta_Outros")));
      }

      if (request.getParameter("oid_Centro_Custo") != null && !request.getParameter("oid_Centro_Custo").equals("")) {
          ed.setOid_centro_custo(Long.parseLong(request.getParameter("oid_Centro_Custo")));
      }

      double Cvalor = 0;

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_icms(Cvalor);

//Valor do INSS
      valor = request.getParameter("FT_VL_INSS");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_inss(Cvalor);


//Valor do IPI
      valor = request.getParameter("FT_VL_IPI");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_ipi(Cvalor);


//Valor do IRRF
      valor = request.getParameter("FT_VL_IRRF");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_irrf(Cvalor);


//Valor do ISQN
      valor = request.getParameter("FT_VL_ISQN");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_isqn(Cvalor);


//Valor do Frete
      valor = request.getParameter("FT_VL_Frete");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_total_frete(Cvalor);


//Valor do Seguro
      valor = request.getParameter("FT_VL_Seguro");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_total_seguro(Cvalor);

//Valor do Despesas
      valor = request.getParameter("FT_VL_Despesas");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_total_despesas(Cvalor);

//Valor do Despesas
      valor = request.getParameter("FT_VL_LIQUIDO");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_liquido_nota_fiscal(Cvalor);


//Valor do Descontos
      valor = request.getParameter("FT_VL_Desconto");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_descontos(Cvalor);

//Valor do PIS

      if(request.getParameter("FT_VL_PIS")!=null){
          valor = request.getParameter("FT_VL_PIS");
      }
      else{
          valor = request.getParameter("FT_VL_PIS2");
      }
      Cvalor = 0;

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
      	Cvalor = 0;
      else{
      	Cvalor = new Double(valor).doubleValue();
      }

      ed.setVL_Pis(Cvalor);

//Valor do Cofins

//      if(request.getParameter("FT_VL_COFINS")!=null){
//          valor = request.getParameter("FT_VL_COFINS");
//      }
//      else{
//          valor = request.getParameter("FT_VL_COFINS2");
//      }
//      Cvalor = 0;
      valor = request.getParameter("FT_VL_Cofins");
      if((valor == null) || (valor.equals("null"))||(valor.equals("")))
      	Cvalor = 0;
      else{
      	Cvalor = new Double(valor).doubleValue();
      }
      ed.setVL_Cofins(Cvalor);

//Valor do CSLL
      if(request.getParameter("FT_VL_CSLL")!=null){
          valor = request.getParameter("FT_VL_CSLL");
      }
      else{
          valor = request.getParameter("FT_VL_CSLL2");
      }
      Cvalor = 0;

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
      	Cvalor = 0;
      else{
      	Cvalor = new Double(valor).doubleValue();
      }

      ed.setVL_Csll(Cvalor);

      ed.setDm_observacao(request.getParameter("ft_mm_obs"));
      ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_TipoNota"));
      String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
      if (String.valueOf(NR_Nota_Fiscal) != null && !String.valueOf(NR_Nota_Fiscal).equals("")
        && !String.valueOf(NR_Nota_Fiscal).equals("null")){
            ed.setNr_nota_fiscal(Long.parseLong(request.getParameter("FT_NR_Nota_Fiscal")));
      }
      else {
           ed.setNr_nota_fiscal(0);
      }

      String NR_Volumes = request.getParameter("FT_NR_Volumes");
      if (String.valueOf(NR_Volumes) != null && !String.valueOf(NR_Volumes).equals("")
        && !String.valueOf(NR_Volumes).equals("null")){
      ed.setNr_volumes(Long.parseLong(request.getParameter("FT_NR_Volumes")));
      }

      String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
      if (String.valueOf(VL_Nota_Fiscal) != null && !String.valueOf(VL_Nota_Fiscal).equals("")
        && !String.valueOf(VL_Nota_Fiscal).equals("null")){
        ed.setVl_nota_fiscal(Double.parseDouble(request.getParameter("FT_VL_Nota_Fiscal")));
      }else ed.setVl_nota_fiscal(0);

      if (ed.getDm_tipo_nota_fiscal().equals("0") || ed.getDm_tipo_nota_fiscal().equals("1") || ed.getDm_tipo_nota_fiscal().equals("P") )
        ed.setDm_finalizado("S");

      Nota_Fiscal_CompraRN.altera(ed);
    }
    catch(Exception exc){
      exc.printStackTrace();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar");
      excecoes.setMetodo("altera");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void altera_Apos_Finalizada(HttpServletRequest request)throws Excecoes{
	    try{

	    // // System.out.println ("altera 1=" + request.getParameter("FT_VL_LIQUIDO"));

	      Nota_Fiscal_CompraRN Nota_Fiscal_CompraRN = new Nota_Fiscal_CompraRN();
	      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();

	      ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
	      ed.setOid_modelo_nota_fiscal(Long.parseLong(request.getParameter("oid_Modelo")));
	      ed.setOid_natureza_operacao(Long.parseLong(request.getParameter("oid_Natureza_Operacao")));
	      if (request.getParameter("oid_Natureza_Operacao_Servico") != null && !request.getParameter("oid_Natureza_Operacao_Servico").equals("")) {
	    	  ed.setOid_natureza_operacao_servico(Long.parseLong(request.getParameter("oid_Natureza_Operacao_Servico")));
	      } else {
	    	  ed.setOid_natureza_operacao_servico(0);
	      }
	      if (JavaUtil.doValida(request.getParameter("oid_Natureza_Operacao_Outros"))) {
	    	  ed.setOid_natureza_operacao_outros(Long.parseLong(request.getParameter("oid_Natureza_Operacao_Outros")));
	      } else {
	    	  ed.setOid_natureza_operacao_outros(0);
	      }

	      ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
	      ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
	      ed.setNm_serie(request.getParameter("FT_NM_Serie"));
	      ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));
	      ed.setVL_Servico(Double.parseDouble(request.getParameter("FT_VL_Servico")));
	      ed.setVL_Outros(Double.parseDouble(request.getParameter("FT_VL_Outros")));
	      ed.setDt_entrada(request.getParameter("FT_DT_Entrada"));
	      ed.setHr_entrada(request.getParameter("FT_HR_Entrada"));
	      ed.setDm_forma_pagamento(request.getParameter("FT_FRM_PGTO"));
	      ed.setNr_parcelas(Long.parseLong(request.getParameter("FT_NR_PARCELAS")));
	      ed.setOID_Unidade_Contabil(Long.parseLong(request.getParameter("oid_Unidade_Contabil")));
	      ed.setOID_Unidade_Fiscal(Long.parseLong(request.getParameter("oid_Unidade_Fiscal")));
	      ed.setOID_Unidade_Pagadora(Long.parseLong(request.getParameter("oid_Unidade_Fiscal")));
	      String valor = request.getParameter("FT_VL_ICMS");

	      if (request.getParameter("oid_Conta") != null && !request.getParameter("oid_Conta").equals("")) {
	    	  ed.setOid_conta(Long.parseLong(request.getParameter("oid_Conta")));
	      }
	      if (request.getParameter("oid_Conta_Servico") != null && !request.getParameter("oid_Conta_Servico").equals("")) {
	          ed.setOid_conta_servico(Long.parseLong(request.getParameter("oid_Conta_Servico")));
	      }
	      if (JavaUtil.doValida(request.getParameter("oid_Conta_Outros") )) {
	          ed.setOid_conta_outros(Long.parseLong(request.getParameter("oid_Conta_Outros")));
	      }

	      if (request.getParameter("oid_Centro_Custo") != null && !request.getParameter("oid_Centro_Custo").equals("")) {
	          ed.setOid_centro_custo(Long.parseLong(request.getParameter("oid_Centro_Custo")));
	      }

	      double Cvalor = 0;

	      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
	          Cvalor = 0;
	      else{
	          Cvalor = new Double(valor).doubleValue();
	      }

	      ed.setVl_icms(Cvalor);

//	Valor do INSS
	      valor = request.getParameter("FT_VL_INSS");

	      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
	          Cvalor = 0;
	      else{
	          Cvalor = new Double(valor).doubleValue();
	      }

	      ed.setVl_inss(Cvalor);


//	Valor do IPI
	      valor = request.getParameter("FT_VL_IPI");

	      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
	          Cvalor = 0;
	      else{
	          Cvalor = new Double(valor).doubleValue();
	      }

	      ed.setVl_ipi(Cvalor);


//	Valor do IRRF
	      valor = request.getParameter("FT_VL_IRRF");

	      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
	          Cvalor = 0;
	      else{
	          Cvalor = new Double(valor).doubleValue();
	      }

	      ed.setVl_irrf(Cvalor);


//	Valor do ISQN
	      valor = request.getParameter("FT_VL_ISQN");

	      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
	          Cvalor = 0;
	      else{
	          Cvalor = new Double(valor).doubleValue();
	      }

	      ed.setVl_isqn(Cvalor);


//	Valor do Frete
	      valor = request.getParameter("FT_VL_Frete");

	      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
	          Cvalor = 0;
	      else{
	          Cvalor = new Double(valor).doubleValue();
	      }

	      ed.setVl_total_frete(Cvalor);


//	Valor do Seguro
	      valor = request.getParameter("FT_VL_Seguro");

	      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
	          Cvalor = 0;
	      else{
	          Cvalor = new Double(valor).doubleValue();
	      }

	      ed.setVl_total_seguro(Cvalor);

//	Valor do Despesas
	      valor = request.getParameter("FT_VL_Despesas");

	      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
	          Cvalor = 0;
	      else{
	          Cvalor = new Double(valor).doubleValue();
	      }

	      ed.setVl_total_despesas(Cvalor);

//	Valor do Despesas
	      valor = request.getParameter("FT_VL_LIQUIDO");

	      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
	          Cvalor = 0;
	      else{
	          Cvalor = new Double(valor).doubleValue();
	      }

	      ed.setVl_liquido_nota_fiscal(Cvalor);


//	Valor do Descontos
	      valor = request.getParameter("FT_VL_Desconto");

	      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
	          Cvalor = 0;
	      else{
	          Cvalor = new Double(valor).doubleValue();
	      }

	      ed.setVl_descontos(Cvalor);

//	Valor do PIS

	      if(request.getParameter("FT_VL_PIS")!=null){
	          valor = request.getParameter("FT_VL_PIS");
	      }
	      else{
	          valor = request.getParameter("FT_VL_PIS2");
	      }
	      Cvalor = 0;

	      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
	      	Cvalor = 0;
	      else{
	      	Cvalor = new Double(valor).doubleValue();
	      }

	      ed.setVL_Pis(Cvalor);

//	Valor do Cofins

//	      if(request.getParameter("FT_VL_COFINS")!=null){
//	          valor = request.getParameter("FT_VL_COFINS");
//	      }
//	      else{
//	          valor = request.getParameter("FT_VL_COFINS2");
//	      }
//	      Cvalor = 0;
	      valor = request.getParameter("FT_VL_Cofins");
	      if((valor == null) || (valor.equals("null"))||(valor.equals("")))
	      	Cvalor = 0;
	      else{
	      	Cvalor = new Double(valor).doubleValue();
	      }
	      ed.setVL_Cofins(Cvalor);

//	Valor do CSLL
	      if(request.getParameter("FT_VL_CSLL")!=null){
	          valor = request.getParameter("FT_VL_CSLL");
	      }
	      else{
	          valor = request.getParameter("FT_VL_CSLL2");
	      }
	      Cvalor = 0;

	      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
	      	Cvalor = 0;
	      else{
	      	Cvalor = new Double(valor).doubleValue();
	      }

	      ed.setVL_Csll(Cvalor);

	      ed.setDm_observacao(request.getParameter("ft_mm_obs"));
	      ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_TipoNota"));
	      String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
	      if (String.valueOf(NR_Nota_Fiscal) != null && !String.valueOf(NR_Nota_Fiscal).equals("")
	        && !String.valueOf(NR_Nota_Fiscal).equals("null")){
	            ed.setNr_nota_fiscal(Long.parseLong(request.getParameter("FT_NR_Nota_Fiscal")));
	      }
	      else {
	           ed.setNr_nota_fiscal(0);
	      }

	      String NR_Volumes = request.getParameter("FT_NR_Volumes");
	      if (String.valueOf(NR_Volumes) != null && !String.valueOf(NR_Volumes).equals("")
	        && !String.valueOf(NR_Volumes).equals("null")){
	      ed.setNr_volumes(Long.parseLong(request.getParameter("FT_NR_Volumes")));
	      }

	      String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
	      if (String.valueOf(VL_Nota_Fiscal) != null && !String.valueOf(VL_Nota_Fiscal).equals("")
	        && !String.valueOf(VL_Nota_Fiscal).equals("null")){
	        ed.setVl_nota_fiscal(Double.parseDouble(request.getParameter("FT_VL_Nota_Fiscal")));
	      }else ed.setVl_nota_fiscal(0);

	      if (ed.getDm_tipo_nota_fiscal().equals("0") || ed.getDm_tipo_nota_fiscal().equals("1") || ed.getDm_tipo_nota_fiscal().equals("P") )
	        ed.setDm_finalizado("S");

	      Nota_Fiscal_CompraRN.altera_Apos_Finalizada(ed);
	    }
	    catch(Exception exc){
	      exc.printStackTrace();
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("erro ao alterar");
	      excecoes.setMetodo("altera");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }
	  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Nota_Fiscal_CompraRN Nota_Fiscal_CompraRN = new Nota_Fiscal_CompraRN();
      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();

      ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
      ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_TipoNota"));
      ed.setDt_entrada(request.getParameter("FT_DT_Entrada"));

      Nota_Fiscal_CompraRN.deleta(ed);
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

  public void inclui_Contabilizacao(HttpServletRequest request)throws Excecoes{

	    try{
	      Nota_Fiscal_CompraRN Nota_Fiscal_CompraRN = new Nota_Fiscal_CompraRN();
	      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();

	      ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
	      Nota_Fiscal_CompraRN.inclui_Contabilizacao(ed);
	    }
	    catch (Excecoes exc){
	      throw exc;
	    }
	    catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("erro ao inclui_Contabilizacao");
	      excecoes.setMetodo("inclui_Contabilizacao");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }
	  }

    public void finaliza(HttpServletRequest request)throws Excecoes{
    double Cvalor = 0;
    String valor = null;
    try{
      Nota_Fiscal_CompraRN Nota_Fiscal_CompraRN = new Nota_Fiscal_CompraRN();
      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();
      ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
      ed.setOid_natureza_operacao(Long.parseLong(request.getParameter("oid_Natureza_Operacao")));
      if (request.getParameter("oid_Natureza_Operacao_Servico") != null && !request.getParameter("oid_Natureza_Operacao_Servico").equals("")) {
    	  ed.setOid_natureza_operacao_servico(Long.parseLong(request.getParameter("oid_Natureza_Operacao_Servico")));
      } else {
    	  ed.setOid_natureza_operacao_servico(0);
      }
      ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      ed.setNm_serie(request.getParameter("FT_NM_Serie"));
      ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDt_entrada(request.getParameter("FT_DT_Entrada"));
      ed.setHr_entrada(request.getParameter("FT_HR_Entrada"));
      ed.setNr_parcelas(Long.parseLong(request.getParameter("FT_NR_PARCELAS")));
      ed.setDm_forma_pagamento(request.getParameter("FT_FRM_PGTO"));
      ed.setOID_Unidade_Contabil(Long.parseLong(request.getParameter("oid_Unidade_Contabil")));
      ed.setOID_Unidade_Fiscal(Long.parseLong(request.getParameter("oid_Unidade_Fiscal")));
      ed.setOID_Unidade_Pagadora(Long.parseLong(request.getParameter("oid_Unidade_Fiscal")));
      ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_TipoNota"));

      if (request.getParameter("oid_Conta") != null && !request.getParameter("oid_Conta").equals("")) {
          ed.setOid_conta(Long.parseLong(request.getParameter("oid_Conta")));
      }
      if (request.getParameter("oid_Conta_Servico") != null && !request.getParameter("oid_Conta_Servico").equals("")) {
          ed.setOid_conta_servico(Long.parseLong(request.getParameter("oid_Conta_Servico")));
      }
      if (request.getParameter("oid_Centro_Custo") != null && !request.getParameter("oid_Centro_Custo").equals("")) {
          ed.setOid_centro_custo(Long.parseLong(request.getParameter("oid_Centro_Custo")));
      }

      String NR_Volumes = request.getParameter("FT_NR_Volumes");
      if (String.valueOf(NR_Volumes) != null && !String.valueOf(NR_Volumes).equals("")
        && !String.valueOf(NR_Volumes).equals("null")){
      ed.setNr_volumes(Long.parseLong(request.getParameter("FT_NR_Volumes")));
      }

      valor = request.getParameter("FT_VL_LIQUIDO");

      if((valor.equals("null"))||(valor.equals(""))||(valor == null))
          Cvalor = 0;
      else{
          Cvalor = new Double(valor).doubleValue();
      }

      ed.setVl_descontos(Double.parseDouble(request.getParameter("FT_VL_Desconto")));
      ed.setVl_liquido_nota_fiscal(Cvalor);
      String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
      if (String.valueOf(NR_Nota_Fiscal) != null && !String.valueOf(NR_Nota_Fiscal).equals("") && !String.valueOf(NR_Nota_Fiscal).equals("null")){
            ed.setNr_nota_fiscal(Long.parseLong(request.getParameter("FT_NR_Nota_Fiscal")));
      }
      else {
           ed.setNr_nota_fiscal(0);
      }
      String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
      if (String.valueOf(VL_Nota_Fiscal) != null && !String.valueOf(VL_Nota_Fiscal).equals("") && !String.valueOf(VL_Nota_Fiscal).equals("null")){
    	  ed.setVl_nota_fiscal(Double.parseDouble(request.getParameter("FT_VL_Nota_Fiscal")));
      } else {
    	  ed.setVl_nota_fiscal(0);
      }
      Nota_Fiscal_CompraRN.finaliza(ed);
    }
    catch (Excecoes exc){
        exc.printStackTrace();
      throw exc;
    }
  }


  ///### kieling 08072003 NR_despacho
  public ArrayList Nota_Fiscal_Lista(HttpServletRequest request)throws Excecoes{

      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();
      String OID_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(OID_Unidade) != null && !String.valueOf(OID_Unidade).equals("")
        && !String.valueOf(OID_Unidade).equals("null")){
        ed.setOID_Unidade_Contabil(new Long(OID_Unidade).longValue());
      }
      String OID_Conta = request.getParameter("oid_Conta");
      if (String.valueOf(OID_Conta) != null && !String.valueOf(OID_Conta).equals("")
        && !String.valueOf(OID_Conta).equals("null")){
        ed.setOid_conta(new Long(OID_Conta).longValue());
      }

      if(!request.getParameter("FT_NR_Nota_Fiscal").equals(""))
         ed.setNr_nota_fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
      if(!request.getParameter("FT_NM_Serie").equals(""))
         ed.setNm_serie(request.getParameter("FT_NM_Serie"));
      if(!request.getParameter("FT_DT_Emissao").equals(""))
        ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));


      if(!request.getParameter("FT_DT_Entrada_Inicial").equals(""))
          ed.setDt_entrada(request.getParameter("FT_DT_Entrada_Inicial"));

      if(!request.getParameter("FT_DT_Entrada_Final").equals(""))
          ed.setDt_entrada_final(request.getParameter("FT_DT_Entrada_Final"));

      if(!request.getParameter("FT_DT_Fim").equals(""))
          ed.setDt_stamp(request.getParameter("FT_DT_Fim"));
     ed.setDm_finalizado(request.getParameter("FT_CD_Situacao"));
     if(!request.getParameter("oid_Pessoa_Remetente").equals(""))
       ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));

    return new Nota_Fiscal_CompraRN().lista(ed);
  }



  public Nota_Fiscal_CompraED getByRecord(HttpServletRequest request)throws Excecoes{

      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();

      String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
      if (NR_Nota_Fiscal != null && NR_Nota_Fiscal.length() > 0)
      {
        ed.setNr_nota_fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
      }
      String NM_Serie = request.getParameter("FT_NM_Serie");
      if (NM_Serie != null && !NM_Serie.equals("") && !NM_Serie.equals("null"))
      {
        ed.setNm_serie(request.getParameter("FT_NM_Serie"));
      }

      ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_TipoNota"));

      ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));

      if (request.getParameter("oid_Modelo") != null && !request.getParameter("oid_Modelo").equals("")){
        ed.setOid_modelo_nota_fiscal(Long.parseLong(request.getParameter("oid_Modelo")));
      }

      return new Nota_Fiscal_CompraRN().getByRecord(ed);

  }
  public Nota_Fiscal_CompraED getByOid(String oid_Nota)throws Excecoes{

      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();

      ed.setOid_nota_fiscal(oid_Nota);


      return new Nota_Fiscal_CompraRN().getByRecord(ed);

  }

  public boolean inclui_Lancamentos(HttpServletRequest request)throws Excecoes{

	   Nota_Fiscal_CompraRN Nota_Fiscal_CompraRN = new Nota_Fiscal_CompraRN();
	   Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();

	    ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));

	    return new Nota_Fiscal_CompraRN().inclui_Lancamento(ed);
  }


  public boolean inclui_Lancamentos(Nota_Fiscal_CompraED ed)throws Excecoes{

    Nota_Fiscal_CompraED auxiliar = (Nota_Fiscal_CompraED)ed;
    ModeloNotaFiscalED modelo = new ModeloNotaFiscalED();
    ModeloNotaFiscalRN modelorn = new ModeloNotaFiscalRN();
    modelo.setOid_Modelo_Nota_Fiscal(ed.getOid_modelo_nota_fiscal());

    modelo = modelorn.getByRecord(modelo);

    auxiliar.setDM_Contabiliza(modelo.getDM_Aceita_Contabilizacao());

    return new Nota_Fiscal_CompraRN().inclui_Lancamento(auxiliar);
  }

  public boolean inclui_Parcelamentos(Nota_Fiscal_CompraED ed)throws Excecoes{

    Nota_Fiscal_CompraED auxiliar = (Nota_Fiscal_CompraED)ed;
    ModeloNotaFiscalED modelo = new ModeloNotaFiscalED();
    ModeloNotaFiscalRN modelorn = new ModeloNotaFiscalRN();
    modelo.setOid_Modelo_Nota_Fiscal(ed.getOid_modelo_nota_fiscal());
    modelo = modelorn.getByRecord(modelo);

    auxiliar.setDM_Financeiro(modelo.getDM_Aceita_Financeiro());
    return new Nota_Fiscal_CompraRN().inclui_Parcelamento(auxiliar);
  }

  public void reabre(HttpServletRequest request)throws Excecoes{

    double Cvalor = 0;
    String valor = null;
    try{
      Nota_Fiscal_CompraRN Nota_Fiscal_CompraRN = new Nota_Fiscal_CompraRN();
      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();

      ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
      ed.setNr_nota_fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());

      Nota_Fiscal_CompraRN.reabre(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao reabri");
      excecoes.setMetodo("reabre");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public boolean inclui_Lancamentos_Pagamento_Compromisso(Lote_CompromissoED ed)throws Excecoes{

    Lote_CompromissoED auxiliar = (Lote_CompromissoED)ed;
    ModeloNotaFiscalED modelo = new ModeloNotaFiscalED();
    ModeloNotaFiscalRN modelorn = new ModeloNotaFiscalRN();
    modelo.setOid_Modelo_Nota_Fiscal(ed.getOID_Modelo());
    modelo = modelorn.getByRecord(modelo);
    auxiliar.setDM_Contabiliza("S");
    return new Nota_Fiscal_CompraRN().inclui_Lancamentos_Pagamento_Compromisso(auxiliar);

  }
  public void estapaFinaliza(Nota_Fiscal_CompraED ed)throws Excecoes{

    double Cvalor = 0;
    String valor = null;
    try{
      Nota_Fiscal_CompraRN Nota_Fiscal_CompraRN = new Nota_Fiscal_CompraRN();
      Nota_Fiscal_CompraRN.finaliza(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void geraRelatorioImpostoSemanalNotasFiscais(HttpServletRequest request, HttpServletResponse response) throws Excecoes{

        Nota_Fiscal_CompraRN nftRN = new Nota_Fiscal_CompraRN();
        nftRN.geraRelatorioImpostoSemanalNotasFiscais(request.getParameter("FT_DT_Emissao_Inicial"), request.getParameter("FT_DT_Emissao_Final"), request.getParameter("oid_Unidade"), request.getParameter("FT_NM_Fantasia"),  response);

  }

  public boolean inclui_Lancamentos_Lote_Posto(Posto_CompromissoED ed)throws Excecoes{

    Posto_CompromissoED auxiliar = (Posto_CompromissoED)ed;
    ModeloNotaFiscalED modelo = new ModeloNotaFiscalED();
    ModeloNotaFiscalRN modelorn = new ModeloNotaFiscalRN();
    modelo.setOid_Modelo_Nota_Fiscal(ed.getOID_Modelo());
    modelo = modelorn.getByRecord(modelo);
    auxiliar.setDM_Contabiliza("S");
    return new Nota_Fiscal_CompraRN().inclui_Lancamentos_Lote_Posto(auxiliar);

  }

  public Nota_Fiscal_CompraED calculaPISCOFINSCSLL(HttpServletRequest request)throws Excecoes{

      try{
        Nota_Fiscal_CompraRN Nota_Fiscal_CompraRN = new Nota_Fiscal_CompraRN();
        Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();

        ed.setOid_natureza_operacao(Long.parseLong(request.getParameter("oid_Natureza_Operacao")));
        ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
        ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
        ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));

        String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");

        if ( String.valueOf(VL_Nota_Fiscal) != null &&
            !String.valueOf(VL_Nota_Fiscal).equals("") &&
            !String.valueOf(VL_Nota_Fiscal).equals("null")){
            	ed.setVl_nota_fiscal(Valores.converteStringToDouble(request.getParameter("FT_VL_Nota_Fiscal")));
        }
        else ed.setVl_nota_fiscal(0);

        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        if (String.valueOf(NR_Nota_Fiscal) != null && !String.valueOf(NR_Nota_Fiscal).equals("")
          && !String.valueOf(NR_Nota_Fiscal).equals("null")){
              ed.setNr_nota_fiscal(Long.parseLong(request.getParameter("FT_NR_Nota_Fiscal")));
        }
        else {
             ed.setNr_nota_fiscal(0);
        }

        return Nota_Fiscal_CompraRN.calculaPISCOFINSCSLL(ed);
      }
      catch (Excecoes exc){
        throw exc;
      }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("erro ao calcular");
        excecoes.setMetodo("calculaPISCOFINSCSLL");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

  public ArrayList Lista_Pedidos(HttpServletRequest request)throws Excecoes{

      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();
      String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
      if (oid_Nota_Fiscal != null && !oid_Nota_Fiscal.equals("")
        && !oid_Nota_Fiscal.equals("null")){
        ed.setOid_nota_fiscal(oid_Nota_Fiscal);
      }
      String oid_Pedido = request.getParameter("oid_Pedido");
      if (oid_Pedido != null && !oid_Pedido.equals("")
        && !oid_Pedido.equals("null")){
        ed.setOid_Pedido_Compra_Nota_Fiscal(oid_Pedido);
      }

    return new Nota_Fiscal_CompraRN().lista_Pedidos(ed);
  }

  public Solicitacao_CompraED inclui_Pedido_Nota_Fiscal(HttpServletRequest request)throws Excecoes{

      Solicitacao_CompraED ed = new Solicitacao_CompraED();

      ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
      ed.setOid_Pedido_compra(new Long(request.getParameter("oid_Pedido_Compra")).longValue());

      return new Nota_Fiscal_CompraRN().inclui_Pedido_Nota_Fiscal(ed);
    }

  public Solicitacao_CompraED getByRecord_pedido(HttpServletRequest request)throws Excecoes{

      Solicitacao_CompraED ed = new Solicitacao_CompraED();

      String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
		if (oid_Nota_Fiscal != null && oid_Nota_Fiscal.length() > 0)
		{
			ed.setOid_nota_fiscal(oid_Nota_Fiscal);
		}
		String oid_Pedido_Compra = request.getParameter("oid_Pedido_Compra");
		if (oid_Pedido_Compra != null && oid_Pedido_Compra.length() > 0)
		{
			ed.setOid_Pedido_compra(Long.parseLong(oid_Pedido_Compra));
		}

		String oid_Pedido_CompraNF = request.getParameter("oid_Pedido_Compra_Nota_Fiscal");
		if (oid_Pedido_CompraNF != null && oid_Pedido_CompraNF.length() > 0)
		{
			ed.setOid_Pedido_compra_nota_fiscal(oid_Pedido_CompraNF);
		}

     return new Nota_Fiscal_CompraRN().getByRecord_Pedido_Nota_Fiscal(ed);

  }

  public void deleta_Pedido_Nota_Fiscal(HttpServletRequest request)throws Excecoes{

      try{
        Nota_Fiscal_CompraRN Nota_Fiscal_CompraRN = new Nota_Fiscal_CompraRN();
        Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();

        ed.setOid_Pedido_Compra_Nota_Fiscal(request.getParameter("oid_Pedido_Compra_Nota_Fiscal"));
        Nota_Fiscal_CompraRN.deleta_Pedido_Nota_Fiscal(ed);
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

  public ArrayList Lista_Romaneio(String oid_Nota_Fiscal)throws Excecoes{

      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();
      if (oid_Nota_Fiscal != null && !oid_Nota_Fiscal.equals("")
        && !oid_Nota_Fiscal.equals("null")){
        ed.setOid_nota_fiscal(oid_Nota_Fiscal);
      }

    return new Nota_Fiscal_CompraRN().lista_Romaneio(ed);
  }

  public Solicitacao_CompraED inclui_Romaneio(HttpServletRequest request)throws Excecoes{

      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();

      ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));

      return new Nota_Fiscal_CompraRN().inclui_Romaneio(ed);
    }

  public Solicitacao_CompraED getByRecord_Romaneio(HttpServletRequest request)throws Excecoes{

      Solicitacao_CompraED ed = new Solicitacao_CompraED();

      String oid_Romaneio_Nota_Fiscal = request.getParameter("oid_Romaneio");
		if (oid_Romaneio_Nota_Fiscal != null && oid_Romaneio_Nota_Fiscal.length() > 0)
		{
			ed.setOid_romaneio_nota_fiscal(oid_Romaneio_Nota_Fiscal);
		}

     return new Nota_Fiscal_CompraRN().getByRecord_Romaneio(ed);

  }

  public void inclui_Local_Qtde(HttpServletRequest request)throws Excecoes{

      try{
        Nota_Fiscal_CompraRN Nota_Fiscal_CompraRN = new Nota_Fiscal_CompraRN();
        EstoqueRN Ern = new EstoqueRN();
        Solicitacao_CompraED ed = new Solicitacao_CompraED();

        ed.setOid_romaneio_nota_fiscal(request.getParameter("oid_Romaneio"));
        String VL_Quantidade = request.getParameter("FT_VL_Quantidade");
        if (VL_Quantidade != null && !VL_Quantidade.equals("0"))
            ed.setVl_quantidade(new Double(VL_Quantidade).doubleValue());
        String OID_Localizacao = request.getParameter("FT_OID_Localizacao");
        if (OID_Localizacao != null && !OID_Localizacao.equals("0"))
            ed.setOid_localizacao(OID_Localizacao);
        String oid_Estoque = request.getParameter("oid_Estoque");
        if (oid_Estoque != null && !oid_Estoque.equals("0"))
            ed.setOid_estoque(Long.parseLong(oid_Estoque));
        String oid_NF = request.getParameter("oid_Nota_Fiscal");
        if (oid_NF != null && !oid_NF.equals("0"))
            ed.setOid_nota_fiscal(oid_NF);

        //Nota_Fiscal_CompraRN.inclui_Local_Qtde(ed);
        Ern.entrada_Estoque(String.valueOf(ed.getOid_estoque()), ed.getVl_quantidade());
        Ern.Movimento_Entrada_Estoque(String.valueOf(ed.getOid_estoque()), ed.getOid_nota_fiscal(), ed.getVl_quantidade());

        Nota_Fiscal_CompraRN.inclui_Local_Qtde(ed);

      }
      catch (Excecoes exc){
          exc.printStackTrace();
        throw exc;
      }
      catch(Exception exc){
          exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("erro ao excluir");
        excecoes.setMetodo("excluir");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

  public void Imprime_Romaneio(HttpServletRequest request, HttpServletResponse response)throws Excecoes{

      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();
      String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
      if (oid_Nota_Fiscal != null && !oid_Nota_Fiscal.equals("")
        && !oid_Nota_Fiscal.equals("null")){
        ed.setOid_nota_fiscal(oid_Nota_Fiscal);
      }

    new Nota_Fiscal_CompraRN().imprime_Romaneio(ed, response);
  }

  public boolean verifica_Pedidos(HttpServletRequest request)throws Excecoes{

      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();
      String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
      if (oid_Nota_Fiscal != null && !oid_Nota_Fiscal.equals("")
        && !oid_Nota_Fiscal.equals("null")){
        ed.setOid_nota_fiscal(oid_Nota_Fiscal);
      }

    return new Nota_Fiscal_CompraRN().verifica_Pedidos(ed);
  }

  public void geraRel_NFEntrada(HttpServletRequest request, HttpServletResponse response)throws Excecoes{

      Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();
//// // System.out.println("Passo 1");
      String OID_Unidade = request.getParameter("oid_Unidade");
      if (JavaUtil.doValida(String.valueOf(OID_Unidade))){
        ed.setOID_Unidade_Fiscal(new Long(OID_Unidade).longValue());
      }
      if(JavaUtil.doValida(request.getParameter("FT_NR_Nota_Fiscal")))
         ed.setNr_nota_fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
//// // System.out.println("Passo 2");
      if(JavaUtil.doValida(request.getParameter("FT_NM_Serie")))
         ed.setNm_serie(request.getParameter("FT_NM_Serie"));
//// // System.out.println("Passo 3");
      if(JavaUtil.doValida(request.getParameter("FT_DT_Emissao")))
        ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));
      if(JavaUtil.doValida(request.getParameter("FT_DT_Fim")))
          ed.setDt_emissao_final(request.getParameter("FT_DT_Fim"));

      if(JavaUtil.doValida(request.getParameter("FT_DT_Entrada")))
          ed.setDt_entrada(request.getParameter("FT_DT_Entrada"));
	  if(JavaUtil.doValida(request.getParameter("FT_DT_Entrada_Fim")))
	      ed.setDt_entrada_final(request.getParameter("FT_DT_Entrada_Fim"));
//// // System.out.println("Passo 4"+request.getParameter("oid_Modelo"));
     if(JavaUtil.doValida(request.getParameter("oid_Centro_Custo")))
       ed.setOid_centro_custo(Long.parseLong(request.getParameter("oid_Centro_Custo")));
//// // System.out.println("Passo 5");
     ed.setDm_finalizado(request.getParameter("FT_DM_Situacao"));
//// // System.out.println("Passo 6");
     if(JavaUtil.doValida(request.getParameter("oid_Pessoa_Remetente")))
       ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));

     if(JavaUtil.doValida(request.getParameter("oid_Pedido_Compra")))
         ed.setOid_Pedido_Compra_Nota_Fiscal(request.getParameter("oid_Pedido_Compra"));

    new Nota_Fiscal_CompraRN().geraRel_NFEntrada(ed, response);
  }


  public Solicitacao_CompraED inclui_Movimento_OS_Nota_Fiscal(HttpServletRequest request)throws Excecoes{

      Solicitacao_CompraED ed = new Solicitacao_CompraED();

      ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
      ed.setOid_movimento_ordem_servico(new Long(request.getParameter("oid_Movimento_Ordem_Servico")).longValue());

      return new Nota_Fiscal_CompraRN().inclui_Movimento_OS_Nota_Fiscal(ed);
    }
  public void deleta_Movimento_OS_Nota_Fiscal(HttpServletRequest request)throws Excecoes{

      try{
        Nota_Fiscal_CompraRN Nota_Fiscal_CompraRN = new Nota_Fiscal_CompraRN();
        Solicitacao_CompraED ed = new Solicitacao_CompraED();

        ed.setOid_movimento_ordem_servico(new Long(request.getParameter("oid_Movimento_Ordem_Servico")).longValue());
        Nota_Fiscal_CompraRN.deleta_Movimento_OS_Nota_Fiscal(ed);
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

  public ArrayList listaMov_ServicoToNF(HttpServletRequest request)throws Excecoes{

      Solicitacao_CompraED ed = new Solicitacao_CompraED();
      String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
      if (oid_Nota_Fiscal != null && !oid_Nota_Fiscal.equals("")
        && !oid_Nota_Fiscal.equals("null")){
        ed.setOid_nota_fiscal(oid_Nota_Fiscal);
      }

    return new Nota_Fiscal_CompraRN().listaMov_ServicoToNF(ed);
  }

  public Solicitacao_CompraED getMov_ServicoToNF(HttpServletRequest request)throws Excecoes{

      Solicitacao_CompraED ed = new Solicitacao_CompraED();

		ed.setOid_fornecedor(request.getParameter("oid_Fornecedor"));

		ed.setNr_documento(request.getParameter("FT_NR_Documento"));

		String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
		if (oid_Nota_Fiscal != null && oid_Nota_Fiscal.length() > 0)
		{
			ed.setOid_nota_fiscal(oid_Nota_Fiscal);
		}
		String oid_Movimento_Ordem_Servico = request.getParameter("oid_Movimento_Ordem_Servico");
		if (oid_Movimento_Ordem_Servico != null && oid_Movimento_Ordem_Servico.length() > 0 && !oid_Movimento_Ordem_Servico.equals("null"))
		{
			ed.setOid_movimento_ordem_servico(Long.parseLong(oid_Movimento_Ordem_Servico));
		}

     return new Nota_Fiscal_CompraRN().getMov_ServicoToNF(ed);

  }

  private void jbInit () throws Exception {
  }

}
