package com.master.bd;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.CompromissoED;
import com.master.ed.Item_Nota_Fiscal_CompraED;
import com.master.ed.Lancamento_ContabilED;
import com.master.ed.Livro_FiscalED;
import com.master.ed.Lote_CompromissoED;
import com.master.ed.ModeloNotaFiscalED;
import com.master.ed.Nota_Fiscal_CompraED;
import com.master.ed.Parcela_CompromissoED;
import com.master.ed.Posto_CompromissoED;
import com.master.ed.Solicitacao_CompraED;
import com.master.rl.Solicitacao_CompraRL;
import com.master.rn.ModeloNotaFiscalRN;
import com.master.root.DateFormatter;
import com.master.root.FormataDataBean;
import com.master.root.Movimento_EstoqueBean;
import com.master.root.PessoaBean;
import com.master.root.UnidadeBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Valor;
import com.master.util.Valores;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;



public class Nota_Fiscal_CompraBD
    extends Transacao {

  private ExecutaSQL executasql;

  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

  public Nota_Fiscal_CompraBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Nota_Fiscal_CompraED inclui (Nota_Fiscal_CompraED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = null;
    String finalizado = "N";

    Nota_Fiscal_CompraED conED = new Nota_Fiscal_CompraED ();

    try {
      //chave antiga
      //chave = String.valueOf(ed.getOid_pessoa()) + String.valueOf(ed.getNr_nota_fiscal()) + ed.getNm_serie();

      //chave nova - leva em considera��o o tipo da nota
      chave = String.valueOf (ed.getOid_pessoa ()) + String.valueOf (ed.getNr_nota_fiscal ()) + ed.getNm_serie () + ed.getDm_tipo_nota_fiscal ();

      ed.setOid_nota_fiscal (chave);

      if (ed.getDm_finalizado () != null && ed.getDm_finalizado ().equals ("S")) {
        finalizado = "S";
      }

      sql = "Insert Into notas_fiscais_transacoes (" +
          "oid_nota_fiscal, " +
          "nr_nota_fiscal, " +
          "dt_emissao, " +
          "nr_volumes, " +
          "nm_serie, " +
          "dt_entrada, " +
          "hr_entrada, " +
          "oid_pessoa, " +
          "oid_pessoa_destinatario, " +
          "oid_natureza_operacao,  " +
          "oid_modelo_nota_fiscal, " +
          "vl_total_frete, " +
          "vl_icms, " +
          "VL_ICMS_Substituicao, " +
          "vl_servico, " +
          "vl_outros, " +
          "vl_inss, " +
          "vl_irrf, " +
          "vl_ipi, " +
          "vl_isqn, " +
          "vl_total_seguro, " +
          "vl_total_despesas, " +
          "vl_nota_fiscal, " +
          "vl_liquido_nota_fiscal, " +
          "dm_tipo_nota_fiscal, " +
          "dt_stamp, " +
          "dm_observacao, " +
          "dm_forma_pagamento, " +
          "nr_parcelas, " +
          "vl_descontos, " +
          "dm_finalizado, " +
          "oid_unidade_fiscal, " +
          "oid_unidade_contabil, " +
          "oid_Unidade_Custo, " +
          "vl_pis, " +
          "vl_cofins, " +
          "vl_csll, " +
          "oid_unidade_pagadora, " +
          "oid_conta, " +
          "oid_centro_custo," +
          "oid_conta_outros, " +
          "oid_natureza_operacao_outros, " +
          "oid_conta_servico, " +
          "oid_natureza_operacao_servico, " +
          "nm_chave_nfe, nm_arquivo_imp " +
          ") values ";

      sql += "('" +
          ed.getOid_nota_fiscal () + "'," +
          ed.getNr_nota_fiscal () + ", '" +
          ed.getDt_emissao () + "', " +
          ed.getNr_volumes () + ", '" +
          ed.getNm_serie () + "', '" +
          ed.getDt_entrada () + "','" +
          ed.getHr_entrada () + "', '" +
          ed.getOid_pessoa () + "', '" +
          ed.getOid_pessoa_destinatario () + "', " +
          ed.getOid_natureza_operacao () + ", " +
          ed.getOid_modelo_nota_fiscal () + ", " +
          ed.getVl_total_frete () + ", " +
          ed.getVl_icms () + ", " +
          ed.getVl_icms_substituicao() + ", " +
          ed.getVL_Servico () + "," +
          ed.getVL_Outros () + "," +
          ed.getVl_inss () + ", " +
          ed.getVl_irrf () + ", " +
          ed.getVl_ipi () + ", " +
          ed.getVl_isqn () + "," +
          ed.getVl_total_seguro () + ", " +
          ed.getVl_total_despesas () + ", " +
          ed.getVl_nota_fiscal () + ", " +
          ed.getVl_liquido_nota_fiscal () + ", '" +
          ed.getDm_tipo_nota_fiscal () + "','" +
          ed.getDt_stamp () + "', " +
          " UPPER('" + ed.getDm_observacao () + "'),'" +
          ed.getDm_forma_pagamento () + "'," +
          ed.getNr_parcelas () + "," +
          ed.getVl_descontos () + ",'" +
          finalizado + "'," +
          ed.getOID_Unidade_Fiscal () + "," +
          ed.getOID_Unidade_Contabil () + "," +
          ed.getOID_Unidade_Fiscal () + "," +
          ed.getVL_Pis () + "," +
          ed.getVL_Cofins () + "," +
          ed.getVL_Csll () + "," +
          ed.getOID_Unidade_Pagadora () + "," +
          ed.getOid_conta () + "," +
          ed.getOid_centro_custo () + "," +
          ed.getOid_conta_outros () + "," +
          ed.getOid_natureza_operacao_outros ()+ "," +
          ed.getOid_conta_servico () + "," +
          ed.getOid_natureza_operacao_servico () + "," +
          "'" + ed.getNM_Chave_NFE() + "', '" + ed.getNm_arquivo_imp() + "' " +
          ")";
//      System.out.println(sql);

      int i = executasql.executarUpdate (sql);

      // .println("INCLUIU OK");

      conED.setOid_nota_fiscal (ed.getOid_nota_fiscal ());
      conED.setNr_nota_fiscal (ed.getNr_nota_fiscal ());
      conED.setDt_emissao (ed.getDt_emissao ());
      conED.setNr_volumes (ed.getNr_volumes ());
      conED.setNm_serie (ed.getNm_serie ());
      conED.setOid_natureza_operacao (ed.getOid_natureza_operacao ());
      conED.setOid_modelo_nota_fiscal (ed.getOid_modelo_nota_fiscal ());
      conED.setVl_total_frete (ed.getVl_total_frete ());
      conED.setVl_icms (ed.getVl_icms ());
      conED.setVl_icms_substituicao(ed.getVl_icms_substituicao());
      conED.setVl_inss (ed.getVl_inss ());
      conED.setVl_irrf (ed.getVl_irrf ());
      conED.setVl_ipi (ed.getVl_ipi ());
      conED.setVl_isqn (ed.getVl_isqn ());
      conED.setVL_Servico (ed.getVL_Servico ());
      conED.setVl_total_seguro (ed.getVl_total_seguro ());
      conED.setVl_total_despesas (ed.getVl_total_despesas ());
      conED.setVl_nota_fiscal (ed.getVl_nota_fiscal ());
      conED.setVl_liquido_nota_fiscal (ed.getVl_liquido_nota_fiscal ());
      conED.setDm_tipo_nota_fiscal (ed.getDm_tipo_nota_fiscal ());
      conED.setDm_observacao (ed.getDm_observacao ());
      conED.setDm_forma_pagamento (ed.getDm_forma_pagamento ());
      conED.setNr_parcelas (ed.getNr_parcelas ());
      conED.setVl_descontos (ed.getVl_descontos ());
      conED.setOID_Unidade_Fiscal (ed.getOID_Unidade_Fiscal ());
      conED.setOID_Unidade_Contabil (ed.getOID_Unidade_Contabil ());
      conED.setDm_finalizado (finalizado);

      conED.setOid_conta (ed.getOid_conta ());

      conED.setVL_Pis (ed.getVL_Pis ());
      conED.setVL_Cofins (ed.getVL_Cofins ());
      conED.setVL_Csll (ed.getVL_Csll ());

      if (ed.getNr_parcelas () > 0) {
          // .println("vai inc parc");

    	  this.inclui_Parcelamento (conED);
      }
      return conED;
    }

    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void altera (Nota_Fiscal_CompraED ed) throws Excecoes {
	  String sql = null;
	  double
		  vl_descontos = 0,
		  vl_pis = 0,
		  vl_cofins = 0,
		  vl_csll = 0,
		  vl_total_frete = 0,
		  vl_icms = 0,
		  VL_ICMS_Substituicao = 0,
		  vl_servico = 0,
		  vl_outros = 0,
		  vl_inss = 0,
		  vl_irrf = 0,
		  vl_ipi = 0,
		  vl_isqn = 0,
		  vl_total_seguro = 0,
		  vl_total_despesas = 0,
		  vl_nota_fiscal = 0,
		  vl_liquido_nota_fiscal = 0;
	  double
		  vl_descontos2 = 0,
		  vl_pis2 = 0,
		  vl_cofins2 = 0,
		  vl_csll2 = 0,
		  vl_total_frete2 = 0,
		  vl_icms2 = 0,
		  vl_servico2 = 0,
		  vl_outros2 = 0,
		  vl_inss2 = 0,
		  vl_irrf2 = 0,
		  vl_ipi2 = 0,
		  vl_isqn2 = 0,
		  vl_total_seguro2 = 0,
		  vl_total_despesas2 = 0,
		  vl_nota_fiscal2 = 0,
		  vl_liquido_nota_fiscal2 = 0;

    try {

      sql = "select vl_descontos, vl_pis, vl_cofins, vl_csll, vl_total_frete, vl_icms, VL_ICMS_Substituicao," +
  			"       vl_servico, vl_outros, vl_inss, vl_irrf, vl_ipi, vl_isqn, vl_total_seguro, " +
  			"       vl_total_despesas, vl_nota_fiscal, vl_liquido_nota_fiscal " +
  			" FROM notas_fiscais_transacoes " +
  			" WHERE oid_nota_fiscal = '" + ed.getOid_nota_fiscal() + "'";

	  ResultSet res = executasql.executarConsulta (sql);
	  while (res.next ()) {
		  vl_descontos = res.getDouble("vl_descontos");
		  vl_pis = res.getDouble("vl_pis");
		  vl_cofins = res.getDouble("vl_cofins");
		  vl_csll = res.getDouble("vl_csll");
		  vl_total_frete = res.getDouble("vl_total_frete");
		  vl_icms = res.getDouble("vl_icms");
		  VL_ICMS_Substituicao = res.getDouble("VL_ICMS_Substituicao");
		  vl_servico = res.getDouble("vl_servico");
		  vl_outros = res.getDouble("vl_outros");
		  vl_inss = res.getDouble("vl_inss");
		  vl_irrf = res.getDouble("vl_irrf");
		  vl_ipi = res.getDouble("vl_ipi");
		  vl_isqn = res.getDouble("vl_isqn");
		  vl_total_seguro = res.getDouble("vl_total_seguro");
		  vl_total_despesas = res.getDouble("vl_total_despesas");
		  vl_nota_fiscal = res.getDouble("vl_nota_fiscal");
		  vl_liquido_nota_fiscal = res.getDouble("vl_liquido_nota_fiscal");
	  }

      sql = "UPDATE notas_fiscais_transacoes \n";
      sql += "SET nr_nota_fiscal =" + ed.getNr_nota_fiscal () + ", \n";
      sql += "dt_emissao ='" + ed.getDt_emissao () + "', \n";
      sql += "nr_volumes =" + ed.getNr_volumes () + ", \n";
      sql += "nm_serie ='" + ed.getNm_serie () + "', \n";
      sql += "dt_entrada ='" + ed.getDt_entrada () + "', \n";
      sql += "hr_entrada ='" + ed.getHr_entrada () + "', \n";
      sql += "oid_pessoa ='" + ed.getOid_pessoa () + "', \n";
      sql += "oid_pessoa_destinatario ='" + ed.getOid_pessoa_destinatario () + "', \n";
      sql += "oid_natureza_operacao =" + ed.getOid_natureza_operacao () + ", \n";
      sql += "oid_modelo_nota_fiscal =" + ed.getOid_modelo_nota_fiscal () + ", \n";
      sql += "oid_conta =" + ed.getOid_conta () + ", \n";
      sql += "oid_centro_custo =" + ed.getOid_centro_custo () + ", \n";
      sql += "vl_total_frete =" + ed.getVl_total_frete () + ", \n";
      sql += "vl_icms =" + ed.getVl_icms () + ", \n";
      sql += "VL_ICMS_Substituicao =" + ed.getVl_icms_substituicao() + ", \n";
      sql += "vl_inss =" + ed.getVl_inss () + ", \n";
      sql += "vl_irrf =" + ed.getVl_irrf () + ", \n";
      sql += "vl_ipi =" + ed.getVl_ipi () + ", \n";
      sql += "vl_isqn =" + ed.getVl_isqn () + ", \n";
      sql += "vl_servico =" + ed.getVL_Servico () + ", \n";
      sql += "vl_total_seguro =" + ed.getVl_total_seguro () + ", \n";
      sql += "vl_total_despesas =" + ed.getVl_total_despesas () + ", \n";
      sql += "vl_nota_fiscal =" + ed.getVl_nota_fiscal () + ", \n";
      sql += "vl_liquido_nota_fiscal =" + ed.getVl_liquido_nota_fiscal () + ", \n";
      sql += "dm_tipo_nota_fiscal ='" + ed.getDm_tipo_nota_fiscal () + "', \n";
      sql += "dt_stamp ='" + ed.getDt_stamp () + "', \n";
      sql += "dm_observacao ='" + ed.getDm_observacao () + "', \n";
      sql += "nm_chave_nfe ='" + ed.getNM_Chave_NFE() + "', \n";
      sql += "dm_forma_pagamento ='" + ed.getDm_forma_pagamento () + "', \n";
      sql += "nr_parcelas =" + ed.getNr_parcelas () + ", \n";
      sql += "vl_descontos =" + ed.getVl_descontos () + ", \n";
      sql += "oid_unidade_fiscal =" + ed.getOID_Unidade_Fiscal () + ", \n";
      sql += "oid_conta_servico = " + ed.getOid_conta_servico () + ", \n";
      sql += "oid_natureza_operacao_servico = " + ed.getOid_natureza_operacao_servico () + ", \n";
      sql += "oid_conta_outros = " + ed.getOid_conta_outros () + ", \n";
      sql += "oid_natureza_operacao_outros = " + ed.getOid_natureza_operacao_outros () + ", \n";
      if (ed.getDm_tipo_nota_fiscal ().equals ("0") || ed.getDm_tipo_nota_fiscal ().equals ("1") || ed.getDm_tipo_nota_fiscal ().equals ("P")) {
        sql += "dm_finalizado ='S', \n";
      }
      sql += "vl_pis=" + ed.getVL_Pis () + ", vl_cofins=" + ed.getVL_Cofins () + ", vl_csll=" + ed.getVL_Csll () + ", \n";
      sql += "oid_unidade_contabil =" + ed.getOID_Unidade_Contabil () + ",  \n";
      sql += "oid_unidade_pagadora =" + ed.getOID_Unidade_Pagadora () + "  \n";
      sql += "WHERE oid_nota_fiscal ='" + ed.getOid_nota_fiscal () + "'";

      int i = executasql.executarUpdate (sql);


      sql = "select vl_descontos, vl_pis, vl_cofins, vl_csll, vl_total_frete, vl_icms, VL_ICMS_Substituicao," +
			"       vl_servico, vl_outros, vl_inss, vl_irrf, vl_ipi, vl_isqn, vl_total_seguro, " +
			"       vl_total_despesas, vl_nota_fiscal, vl_liquido_nota_fiscal " +
			" FROM notas_fiscais_transacoes " +
			" WHERE oid_nota_fiscal = '" + ed.getOid_nota_fiscal() + "'";

	  res = executasql.executarConsulta (sql);
	  while (res.next ()) {
			  vl_descontos2 = res.getDouble("vl_descontos");
			  vl_pis2 = res.getDouble("vl_pis");
			  vl_cofins2 = res.getDouble("vl_cofins");
			  vl_csll2 = res.getDouble("vl_csll");
			  vl_total_frete2 = res.getDouble("vl_total_frete");
			  vl_icms2 = res.getDouble("vl_icms");
			  vl_servico2 = res.getDouble("vl_servico");
			  vl_outros2 = res.getDouble("vl_outros");
			  vl_inss2 = res.getDouble("vl_inss");
			  vl_irrf2 = res.getDouble("vl_irrf");
			  vl_ipi2 = res.getDouble("vl_ipi");
			  vl_isqn2 = res.getDouble("vl_isqn");
			  vl_total_seguro2 = res.getDouble("vl_total_seguro");
			  vl_total_despesas2 = res.getDouble("vl_total_despesas");
			  vl_nota_fiscal2 = res.getDouble("vl_nota_fiscal");
			  vl_liquido_nota_fiscal2 = res.getDouble("vl_liquido_nota_fiscal");
	  }

      if (vl_descontos != vl_descontos2 ||
		  vl_pis != vl_pis2 ||
		  vl_cofins != vl_cofins2 ||
		  vl_csll != vl_csll2 ||
		  vl_total_frete != vl_total_frete2 ||
		  vl_icms != vl_icms2 ||
		  vl_servico != vl_servico2 ||
		  vl_outros != vl_outros2 ||
		  vl_inss != vl_inss2 ||
		  vl_irrf != vl_irrf2 ||
		  vl_ipi != vl_ipi2 ||
		  vl_isqn != vl_isqn2 ||
		  vl_total_seguro != vl_total_seguro2 ||
		  vl_total_despesas != vl_total_despesas2 ||
		  vl_nota_fiscal != vl_nota_fiscal2 ||
		  vl_liquido_nota_fiscal != vl_liquido_nota_fiscal2){

          deleta_Parcelamento(ed);
          deleta_Dependencias(ed);
          inclui_Parcelamento(ed);

    	  new Livro_FiscalBD(executasql).geraLivro_Fiscal_Entrada_Simplificada(new Livro_FiscalED(ed.getOid_nota_fiscal(), "NF"));

      }
    }

    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar BD");
      excecoes.setMetodo ("altera");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void altera_Apos_Finalizada (Nota_Fiscal_CompraED ed) throws Excecoes {
	  String sql = null;

    try {

      sql = "UPDATE notas_fiscais_transacoes \n";
      sql += "SET nr_nota_fiscal =" + ed.getNr_nota_fiscal () + ", \n";
      sql += "dt_emissao ='" + ed.getDt_emissao () + "', \n";
      sql += "nm_serie ='" + ed.getNm_serie () + "', \n";
      sql += "dt_entrada ='" + ed.getDt_entrada () + "', \n";
      sql += "hr_entrada ='" + ed.getHr_entrada () + "', \n";
      sql += "oid_natureza_operacao =" + ed.getOid_natureza_operacao () + ", \n";
      sql += "oid_pessoa_destinatario ='" + ed.getOid_pessoa_destinatario () + "', \n";
      sql += "oid_conta =" + ed.getOid_conta () + ", \n";
      sql += "oid_centro_custo =" + ed.getOid_centro_custo () + ", \n";
      sql += "dt_stamp ='" + ed.getDt_stamp () + "', \n";
      sql += "dm_observacao ='" + ed.getDm_observacao () + "', \n";
      sql += "oid_unidade_fiscal =" + ed.getOID_Unidade_Fiscal () + ", \n";
      sql += "oid_unidade_contabil =" + ed.getOID_Unidade_Contabil() + ", \n";
      sql += "dm_Contabilizado = 'N', oid_conta_servico = " + ed.getOid_conta_servico () + ", \n";
      sql += "oid_natureza_operacao_servico = " + ed.getOid_natureza_operacao_servico () + ", \n";
      sql += "oid_conta_outros = " + ed.getOid_conta_outros () + ", \n";
      sql += "oid_natureza_operacao_outros = " + ed.getOid_natureza_operacao_outros () + " \n";
      sql += "WHERE oid_nota_fiscal ='" + ed.getOid_nota_fiscal () + "'";

      int i = executasql.executarUpdate (sql);

      if ("S".equals (parametro_FixoED.getDM_Gera_Livro_Fiscal())) {
    	  new Livro_FiscalBD(executasql).geraLivro_Fiscal_Entrada_Simplificada(new Livro_FiscalED(ed.getOid_nota_fiscal(), "NF"));
      }

      if ("S".equals (parametro_FixoED.getDM_Gera_Lancamento_Contabil ())) {
          //faz os lancamentos contabeis do movimento
          sql = " delete from lancamentos_contabeis where oid_nota_fiscal = '" +  ed.getOid_nota_fiscal () + "'";
          // System.out.println(sql);
          executasql.executarUpdate (sql);
          sql = " delete from movimentos_contabeis where oid_origem = '7' and tx_chave_origem = '" +  ed.getOid_nota_fiscal () + "'";
// System.out.println(sql);
          executasql.executarUpdate (sql);
          Lancamento_ContabilED lc = new Lancamento_ContabilED ();
          new Lancamento_ContabilBD (this.executasql).inclui_CTB_Nota_Fiscal (ed);
        }

    }

    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar BD");
      excecoes.setMetodo ("altera");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (Nota_Fiscal_CompraED ed) throws Excecoes {
    String sql = null;
    String chave = null;
    ResultSet res = null;
    boolean passo = false;

    try {
      //verifica ligacoes antes de excluir
      sql = " SELECT Compromissos.oid_Compromisso " +
            " FROM   Parcelamentos_Financeiros, Parcelas_Compromissos, Compromissos " +
            " WHERE  Parcelamentos_Financeiros.oid_Parcelamento = Parcelas_Compromissos.oid_parcelamento " +
            " AND    Parcelas_Compromissos.oid_Compromisso      = Compromissos.oid_Compromisso " +
            " AND    Compromissos.vl_compromisso > Compromissos.vl_saldo" +
            " AND    Parcelamentos_Financeiros.oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
       // .println(sql);
      res = executasql.executarConsulta (sql);
      if (res.next ()) {
        Exception exc = new Exception ("Exclua todos os Compromissos gerado pelo Parcelamento!");
        throw exc;
      }
      // Exclui todos os lancamentos contabeis da NF
      //sql = "Select * from Lancamentos_Contabeis where oid_nota_fiscal = '"+ed.getOid_nota_fiscal()+"'";
      // // .println(sql);
      //res = executasql.executarConsulta(sql);
      //while(res.next()){
      // passo = false;
      // Exception exc = new Exception("Exclua todos os lan�amentos primeiro");
      // throw exc;
      //}

      sql = "Select * from Itens_Notas_Fiscais_Transacoes where oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
      res = executasql.executarConsulta (sql);
      while (res.next ()) {
        Exception exc = new Exception ("Exclua todos os itens da nota fiscal primeiro");
        throw exc;
      }

      this.deleta_Dependencias(ed);

      sql = " DELETE FROM notas_fiscais_transacoes " +
            " WHERE oid_nota_fiscal ='" + ed.getOid_nota_fiscal () + "'";
      //////// // System.out.println(sql);
      int i = executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem (exc.getMessage ());
      excecoes.setMetodo ("deleta");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta_Dependencias (Nota_Fiscal_CompraED ed) throws Excecoes {
	    String sql = null;
	    ResultSet res = null;
	    try {
	      //verifica ligacoes antes de excluir

      if ("S".equals (parametro_FixoED.getDM_Gera_Lancamento_Contabil ())) {
        //desfaz os lancamentos contabeis do movimento
        new Lancamento_ContabilBD (this.executasql).deleta_CTB_Nota_Fiscal (ed);
      }


      sql = " DELETE FROM Compromissos " +
      		" WHERE oid_Compromisso in (SELECT Parcelas_Compromissos.oid_Compromisso " +
									        " FROM   Parcelamentos_Financeiros, Parcelas_Compromissos " +
									        " WHERE  Parcelamentos_Financeiros.oid_Parcelamento = Parcelas_Compromissos.oid_parcelamento " +
									        " AND    Parcelamentos_Financeiros.oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "')";
      executasql.executarUpdate (sql);

      sql = " DELETE FROM Parcelas_Compromissos " +
            " WHERE oid_parcelamento in (SELECT oid_Parcelamento FROM Parcelamentos_Financeiros WHERE oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "')";
       // .println(sql);
      executasql.executarUpdate (sql);

      sql = " DELETE FROM Parcelamentos_Financeiros WHERE oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
       // .println(sql);
      executasql.executarUpdate (sql);

      sql = " DELETE FROM Livros_Fiscais WHERE oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
      // .println(sql);
     executasql.executarUpdate (sql);

//      sql = " DELETE FROM notas_fiscais_transacoes " +
//            " WHERE oid_nota_fiscal ='" + ed.getOid_nota_fiscal () + "'";
      //////// // .println(sql);
//      int i = executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem (exc.getMessage ());
      excecoes.setMetodo ("deleta");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  /*Passo 1: A soma dos itens da nota fiscal deve ser igual ao bruto da nota*/
  public boolean verificaItens (Nota_Fiscal_CompraED ed) throws Excecoes {
    String sql = null;
    String chave = null;
    ResultSet res = null;
    double Bruto = 0;
    double outros = 0;
    int nr_itens = 0;
    int nr_cont = 0;
    double Desconto = 0;
    double IPI = 0;
    double Total_Itens = 0;
    boolean passo = false;

    try {
//    	.println("ITENS VERIFICACAO!");
      sql = "Select (vl_nota_fiscal+vl_servico) as bruto, vl_outros, vl_descontos, vl_ipi, nr_volumes from Notas_Fiscais_Transacoes Where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "'";
      res = executasql.executarConsulta (sql);
      while (res.next ()) {
//        Bruto = res.getDouble ("vl_nota_fiscal");
    	  Bruto = res.getDouble ("bruto");
    	  outros = res.getDouble ("vl_outros");
        IPI = res.getDouble ("vl_ipi");
        Desconto = res.getDouble ("vl_descontos");
        nr_itens = res.getInt("nr_volumes");
      }
//Verifica soma dos itens
      res = null;
//      sql = "Select ((Itens_Notas_Fiscais_Transacoes.vl_produto * Itens_Notas_Fiscais_Transacoes.nr_volumes)-vl_desconto) as total from Itens_Notas_Fiscais_Transacoes where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "'";
      sql = "Select (vl_liquido) as total from Itens_Notas_Fiscais_Transacoes where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "'";
      res = executasql.executarConsulta (sql);
      while (res.next ()) {
        Total_Itens += Valores.trataDouble(res.getDouble ("total"),2);
      }
      if (Bruto != 0.0 && Total_Itens != 0.0) {
        if (Valores.trataDouble(Total_Itens,2) != Valores.trataDouble(Bruto,2)) {
System.out.println("it:"+Total_Itens+"| nf:"+Valores.trataDouble(Bruto,2));
          passo = false;
          Exception exc = new Exception ("Soma dos Itens n�o condiz com o valor total dos produtos da NF");
          throw exc;
        }
        else {
          passo = true;
        }
      }
      else {
        passo = true;
      }

//      res = null;
//      sql = "Select sum(Itens_Notas_Fiscais_Transacoes.vl_desconto) as total from Itens_Notas_Fiscais_Transacoes where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "'";
//      res = executasql.executarConsulta (sql);
//      while (res.next ()) {
//        Total_Itens = res.getDouble ("total");
//      }
//      if (Desconto != 0.0) {
//        if (Total_Itens != Desconto) {
//          passo = false;
//          Exception exc = new Exception ("Soma dos Descontos n�o condiz com o valor da NF");
//          throw exc;
//        }
//        else {
//          passo = true;
//        }
//      }

      res = null;
      sql = "Select sum(Itens_Notas_Fiscais_Transacoes.vl_ipi) as total from Itens_Notas_Fiscais_Transacoes where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "'";
      res = executasql.executarConsulta (sql);
      while (res.next ()) {
        Total_Itens = res.getDouble ("total");
      }
      if (IPI != 0.0) {
        if (Total_Itens != IPI) {
          passo = false;
          Exception exc = new Exception ("Soma dos IPI nao condiz com o valor da NF");
          throw exc;
        }
        else {
          passo = true;
        }
      }

      // res = null;
      // sql = "Select count(Itens_Notas_Fiscais_Transacoes.oid_item_nota_fiscal) as total from Itens_Notas_Fiscais_Transacoes where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "'";
      // res = executasql.executarConsulta (sql);
      // while (res.next ()) {
      //  nr_cont = res.getInt("total");
      // }
      // if (nr_cont != nr_itens) {
      // passo = false;
      // Exception exc = new Exception ("Quantidade de itens nao condiz com o declarado na NF");
      // throw exc;
      // }
      // else {
      //  passo = true;
      // }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem (exc.getMessage ());
      excecoes.setMetodo ("verificaItens");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return passo;
  }

  /*Passo 2: OS d�tos e cr�tos devem gerar numero 0. E o particionamento
   do centros de custo para cada lancto deve somar 100% na conta Debito*/
  public boolean verificaLancamentos (Nota_Fiscal_CompraED ed) throws Excecoes {
    String sql = null;
    ResultSet res = null;
    ResultSet r1 = null;
    ResultSet rs = null;
    double Debito = 0 , Credito = 0;
    long total = 0;
    boolean passo = false;
    boolean existe_lacto = false;

    try {
      //Verifica todos os lancamentos daquela nota fiscal
      sql = "Select oid_lancamento, dm_acao, vl_lancamento " +
          " from Lancamentos_Contabeis " +
          " Where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "'";
      res = executasql.executarConsulta (sql);

      while (res.next ()) {
        sql = "Select sum(vl_lancamento) as Debito from Lancamentos_Contabeis  Where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "' and dm_acao='D'";
        r1 = executasql.executarConsulta (sql);
        while (r1.next ()) {
          Debito = r1.getDouble ("Debito");
        }
        r1 = null;
        sql = "Select sum(vl_lancamento) as Credito from Lancamentos_Contabeis  Where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "' and dm_acao='C'";
        r1 = executasql.executarConsulta (sql);
        while (r1.next ()) {
          Credito = r1.getDouble ("Credito");
        }
        existe_lacto = true;
      }

//Primeiro verifica se houveram lan�entos
      if (existe_lacto) {
        passo = true;
      }
      else {
        passo = false;
        Exception exc = new Exception ("A nota fiscal nao pode ser finalizada sem lancamentos");
        throw exc;
      }
//Depois se os lan�entos fecham a regra de partidas dobradas
      if ( ( (Debito - Credito) == 0) && (passo)) {
        passo = true;
      }
      else {
        passo = false;
        Exception exc = new Exception ("O somatorio dos lancamentos esta incorreto. Valor da diferen�a" + (Debito - Credito));
        throw exc;
      }

//Para ent�verificar se as aloca�s entre contas cont�is fecham o esperado
      if (passo) {
        //Verifica o pior caso!!
        sql = "select sum(lcc.vl_lancamento) as VL_Lancamento ";
        sql += "from lancamentos_centros_custos lcc, lancamentos_contabeis lc ";
        sql += "where lc.oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "' ";
        sql += "and lcc.oid_lancamento = lc.oid_lancamento ";
        sql += "and dm_acao='C' ";
        rs = executasql.executarConsulta (sql);
        while (rs.next ()) {
          if (rs.getDouble ("VL_Lancamento") > 0) {
            passo = false;
            Exception exc = new Exception ("As alocacoes em centros de custo devem ser gravadas na conta de debito");
            throw exc;
          }
        }
        rs = null;
        sql = "select oid_lancamento " +
            "from  lancamentos_contabeis lc,  contas ct " +
            "where lc.oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "' " +
            "and ct.oid_conta = lc.oid_conta " +
            "and dm_acao='D' and ct.dm_despesa = 'S'";
//////// // .println("RICARDI"+sql);
        res = executasql.executarConsulta (sql);

        while (res.next ()) {
          //J�ei que existem lancamentos de despesa
          sql = "Select sum(vl_lancamento) as VL_Lancamento  from lancamentos_centros_custos where oid_lancamento in(";
          sql += "select oid_lancamento " +
              "from  lancamentos_contabeis lc,  contas ct " +
              "where lc.oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "' " +
              "and ct.oid_conta = lc.oid_conta " +
              "and dm_acao='D' and ct.dm_despesa = 'S')";
//////// // .println("ROBERTI"+sql);

          rs = executasql.executarConsulta (sql);

          while (rs.next ()) {
            if (rs.getDouble ("VL_Lancamento") > 0) {
              if (! ( (rs.getDouble ("VL_Lancamento") == ed.getVl_nota_fiscal ()) && (rs.getDouble ("VL_Lancamento") == Debito))) {
                passo = false;
                Exception exc = new Exception ("A alocacao entre centros de custos deve estar na conta de debito que contenha o valor bruto");
                throw exc;
              }
            }
            else {
              passo = false;
              Exception exc = new Exception ("A alocacao entre centros de custos na conta de debito deve ser maior que zero");
              throw exc;
            }
          }
        }
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem (exc.getMessage ());
      excecoes.setMetodo ("verificaLancamentos");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return passo;
  }

  /*Passo 3: As parcelas de pagamento devem somar 100%*/
  public boolean verificaParcelamentos (Nota_Fiscal_CompraED ed) throws Excecoes {
    String sql = null;
    ResultSet res = null;
    boolean passo = false;

    try {
      sql = "Select sum(vl_parcela) as parcelas, count(oid_parcelamento) as numero from Parcelamentos_Financeiros Where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "'";
      res = executasql.executarConsulta (sql);
      while (res.next ()) {

        if (res.getDouble ("parcelas") != ed.getVl_liquido_nota_fiscal ()) {
          passo = false;
          Exception exc = new Exception ("Valor liquido n�o condiz com o valor total das parcelas");
          throw exc;
        }
        else {
          passo = true;
        }
        if (res.getLong ("numero") != ed.getNr_parcelas ()) {
          passo = false;
          Exception exc = new Exception ("Numero das parcelas n�o eh o mesmo inserido na nota fiscal");
          throw exc;
        }
        else {
          passo = true;
        }
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem (exc.getMessage ());
      excecoes.setMetodo ("verificaParcelamentos");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return passo;
  }

  /*Passo 4: Atualiza a tabela para dm_finaliza = "S"*/
  public void finaliza (Nota_Fiscal_CompraED ed) throws Excecoes {
    String sql = null;
    ResultSet res = null;
    String dt_vcto = "";
    double Bruto = 0;

    try {

      if ("S".equals(Parametro_FixoED.getInstancia ().getDM_Gera_Compromisso_Nota_Fiscal_Compra())){
        this.geraMovimento (ed);
      }

      ed = this.getByRecord(ed);
      dt_vcto = ed.getDt_emissao();

      sql = "Select Pessoas.NM_Razao_Social "+
		" From  Pessoas "+
		" Where Pessoas.OID_Pessoa = '" + ed.getOid_pessoa() + "'";
		res = this.executasql.executarConsulta(sql);
	  while (res.next()){
			  ed.setNM_Pessoa(res.getString("NM_Razao_Social"));
	  }

      sql = "Select pr.oid_parcelamento, pr.nr_parcelamento, pr.vl_parcela, pr.dt_pagamento "+
      		"From  parcelamentos_financeiros pr, notas_fiscais_transacoes nf "+
      		" Where pr.OID_Nota_Fiscal = nf.OID_Nota_Fiscal " +
      		" AND pr.nr_parcelamento = 1 "+
      		" AND pr.oid_nota_fiscal = '" + ed.getOid_nota_fiscal() + "'";
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
    	  dt_vcto = new FormataDataBean().getDT_FormataData(res.getString("dt_pagamento"));
      }

      if(JavaUtil.doValida(Parametro_FixoED.getInstancia().getOID_Fornecedor_INSS())
              && ed.getVl_inss()>0){
          this.geraINSS(ed);
      }
      if(JavaUtil.doValida(Parametro_FixoED.getInstancia().getOID_Fornecedor_IRRF())
              && ed.getVl_irrf()>0){
          this.geraIR(ed);
      }
  	  //issqn
	  if(JavaUtil.doValida(Parametro_FixoED.getInstancia().getOID_Fornecedor_ISSQN())
	        && ed.getVl_isqn()>0){
		  this.geraISSQN(ed);
	  }
      //cofins
	  if(JavaUtil.doValida(Parametro_FixoED.getInstancia().getOID_Fornecedor_COFINS())
	        && ed.getVL_Cofins()>0){
		  this.geraPIS_COFINS_CSLL(ed, ed.getVL_Cofins(), dt_vcto);
	  }
	  //csll
  	  if(JavaUtil.doValida(Parametro_FixoED.getInstancia().getOID_Fornecedor_CSLL())
  	        && ed.getVL_Csll()>0){
  		this.geraPIS_COFINS_CSLL(ed, ed.getVL_Csll(), dt_vcto);
  	  }
	  //pis
  	  if(JavaUtil.doValida(Parametro_FixoED.getInstancia().getOID_Fornecedor_PIS())
  	        && ed.getVL_Pis()>0){
  		this.geraPIS_COFINS_CSLL(ed, ed.getVL_Pis(), dt_vcto);
  	  }

      sql = "update Notas_Fiscais_Transacoes Set dm_finalizado = 'S' " +
          "Where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "'";
      int i = executasql.executarUpdate (sql);

      if ("S".equals (parametro_FixoED.getDM_Gera_Lancamento_Contabil ())) {
        //faz os lancamentos contabeis do movimento
        Lancamento_ContabilED lc = new Lancamento_ContabilED ();
        new Lancamento_ContabilBD (this.executasql).inclui_CTB_Nota_Fiscal (ed);
      }

	  new Livro_FiscalBD(executasql).geraLivro_Fiscal_Entrada_Simplificada(new Livro_FiscalED(ed.getOid_nota_fiscal(), "NF"));

      //ENTRADA NO ESTOQUE
//    .println("ESTOQUE "+ed.getDm_tipo_nota_fiscal());
    if (ed.getDm_tipo_nota_fiscal().equals("E")) {
  	  String fornecedor = PessoaBean.getByOID(ed.getOid_pessoa()).getNM_Razao_Social();
  	  Item_Nota_Fiscal_CompraED item = new Item_Nota_Fiscal_CompraED();
  	  item.setOID_Nota_Fiscal(ed.getOid_nota_fiscal ());
  	  Iterator itens = new Item_Nota_Fiscal_CompraBD(this.executasql).lista(item).iterator();
  	  while(itens.hasNext()){
  		  item = (Item_Nota_Fiscal_CompraED)itens.next();
  		  Movimento_EstoqueBean Movimento_Estoque = new Movimento_EstoqueBean();
            if(JavaUtil.doValida(item.getOID_Produto())){
          	  Movimento_Estoque.setDM_Movimento ("E");
      		  Movimento_Estoque.setDT_Movimento (Data.getDataDMY());
      		  Movimento_Estoque.setNR_Quantidade (item.getVl_quantidade());
      		  Movimento_Estoque.setOID_Estoque (new Long(item.getOID_Produto()).longValue());
      		  Movimento_Estoque.setVL_Unitario (item.getVL_Produto());
      		  Movimento_Estoque.setNM_Fornecedor(fornecedor);
      		  Movimento_Estoque.setOid_Veiculo ("");
      		  Movimento_Estoque.setOid_Nota_Fiscal(ed.getOid_nota_fiscal ());
      		  Movimento_Estoque.insert ();
            }

  	  }
    }

    }
    catch(Excecoes e){
    	throw e;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao Finalizar");
      excecoes.setMetodo ("finaliza");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void geraImposto () throws Excecoes {
	    String sql = null;
	    ResultSet res = null;
	    String dt_vcto = "";
	    double Bruto = 0;

	    try {
	    	Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED();
	      sql = "Select nr_nota_fiscal, oid_nota_fiscal from notas_fiscais_transacoes " +
	      		" where (vl_inss > 0 or vl_irrf > 0 or vl_isqn > 0 or vl_pis > 0 or vl_cofins > 0 " +
	      		" or vl_csll > 0) and nr_nota_fiscal = 769";

	      res = this.executasql.executarConsulta(sql);
		  while (res.next()){
			 ed.setOid_nota_fiscal(res.getString("oid_nota_fiscal"));

			 ed = this.getByRecord(ed);
			 dt_vcto = ed.getDt_emissao();

			 sql = "Select Pessoas.NM_Razao_Social "+
			 	" From  Pessoas "+
			 	" Where Pessoas.OID_Pessoa = '" + ed.getOid_pessoa() + "'";
			 ResultSet resLocal = this.executasql.executarConsulta(sql);
			 while (resLocal.next()){
				  ed.setNM_Pessoa(resLocal.getString("NM_Razao_Social"));
			 }

			 sql = "Select pr.oid_parcelamento, pr.nr_parcelamento, pr.vl_parcela, pr.dt_pagamento "+
	      		"From  parcelamentos_financeiros pr, notas_fiscais_transacoes nf "+
	      		" Where pr.OID_Nota_Fiscal = nf.OID_Nota_Fiscal " +
	      		" AND pr.nr_parcelamento = 1 "+
	      		" AND pr.oid_nota_fiscal = '" + ed.getOid_nota_fiscal() + "'";
			 resLocal = this.executasql.executarConsulta(sql);
			 while (resLocal.next()){
				 dt_vcto = new FormataDataBean().getDT_FormataData(resLocal.getString("dt_pagamento"));
			 }

		     if(JavaUtil.doValida(Parametro_FixoED.getInstancia().getOID_Fornecedor_INSS())
		              && ed.getVl_inss()>0){
		          this.geraINSS(ed);
		     }

		     if(JavaUtil.doValida(Parametro_FixoED.getInstancia().getOID_Fornecedor_IRRF())
		              && ed.getVl_irrf()>0){
		          this.geraIR(ed);
		     }
		     //cofins
			 if(JavaUtil.doValida(Parametro_FixoED.getInstancia().getOID_Fornecedor_COFINS())
			        && ed.getVL_Cofins()>0){
				  this.geraPIS_COFINS_CSLL(ed, ed.getVL_Cofins(), dt_vcto);
			 }
			 //csll
		  	 if(JavaUtil.doValida(Parametro_FixoED.getInstancia().getOID_Fornecedor_CSLL())
		  	        && ed.getVL_Csll()>0){
		  		 this.geraPIS_COFINS_CSLL(ed, ed.getVL_Csll(), dt_vcto);
		  	 }
		  	 //issqn
			 if(JavaUtil.doValida(Parametro_FixoED.getInstancia().getOID_Fornecedor_ISSQN())
			        && ed.getVl_isqn()>0){
				  this.geraISSQN(ed);//alterar!!!
			 }
			 //pis
		  	 if(JavaUtil.doValida(Parametro_FixoED.getInstancia().getOID_Fornecedor_PIS())
		  	        && ed.getVL_Pis()>0){
		  		this.geraPIS_COFINS_CSLL(ed, ed.getVL_Pis(), dt_vcto);
		  	 }
		  }
	    }
	    catch(Excecoes e){
	    	throw e;
	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao Finalizar");
	      excecoes.setMetodo ("finaliza");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }


  /*Passo 4: Atualiza a tabela para dm_finaliza = "S"*/
  public void inclui_Contabilizacao (Nota_Fiscal_CompraED ed) throws Excecoes {
    String sql = null;
    ResultSet res = null;
    try {

      if ("S".equals (parametro_FixoED.getDM_Gera_Lancamento_Contabil ())) {
        //faz os lancamentos contabeis do movimento
        Lancamento_ContabilED lc = new Lancamento_ContabilED ();
        new Lancamento_ContabilBD (this.executasql).inclui_CTB_Nota_Fiscal (ed);
      }
    }
    catch(Excecoes e){
    	throw e;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao Finalizar");
      excecoes.setMetodo ("finaliza");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void geraMovimento (Nota_Fiscal_CompraED ed) throws Excecoes {

    String sql = null;
    String chave = null;
    String nr_Parcela = null;
    long valOid = 0;

    Parcela_CompromissoED Parcela_CompromissoED = new Parcela_CompromissoED ();
    Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

    CompromissoED compromissoED = new CompromissoED ();
    CompromissoED compromissoVoltaED = new CompromissoED ();
    CompromissoBD compromissoBD = new CompromissoBD (this.executasql);
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {

      sql = "Select pr.oid_parcelamento, pr.nr_parcelamento, pr.vl_parcela, pr.dt_pagamento " +
            "From  parcelamentos_financeiros pr, notas_fiscais_transacoes nf " +
          " Where pr.OID_Nota_Fiscal = nf.OID_Nota_Fiscal " +
          " AND pr.oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        compromissoED.setDt_Vencimento (res.getString ("dt_pagamento"));
        dataFormatada.setDT_FormataData (compromissoED.getDt_Vencimento ());
        compromissoED.setDt_Vencimento (dataFormatada.getDT_FormataData ());

        if(!JavaUtil.doValida(compromissoED.getDt_Vencimento()))
        	throw new Excecoes("Preencha, primeiro, as datas de vencimento das parcelas!");

        nr_Parcela = res.getString ("nr_parcelamento");
        if (nr_Parcela != null) {
          compromissoED.setNr_Parcela (new Integer (nr_Parcela));
        }

        compromissoED.setVl_Compromisso (new Double (res.getDouble ("vl_parcela")));
        compromissoED.setVl_Saldo (new Double (res.getDouble ("vl_parcela")));

        compromissoED.setOid_Pessoa (ed.getOid_pessoa ());
        compromissoED.setDt_Entrada (ed.getDt_entrada ());
        compromissoED.setDt_Emissao (ed.getDt_emissao ());
        compromissoED.setNr_Documento (String.valueOf (ed.getNr_nota_fiscal ()));

        // deve ser unidade contabil
        compromissoED.setOid_Unidade (new Long (ed.getOID_Unidade_Fiscal ()));

        //compromissoED.setOid_Unidade_Emissora(new Long(ed.getOID_Unidade_Fiscal()));

        compromissoED.setDM_Tipo_Pagamento (ed.getDm_forma_pagamento ());
        compromissoED.setOid_Centro_Custo (new Integer (new Long (ed.getOid_centro_custo ()).intValue ()));
        if (ed.getDm_tipo_nota_fiscal () != null && (ed.getDm_tipo_nota_fiscal ().equals ("E") || ed.getDm_tipo_nota_fiscal ().equals ("0"))) {
          String busca = "select oid_centro_custo from solicitacoes_compras, pedidos_compras, pedidos_compras_notas_fiscais where " +
              " solicitacoes_compras.oid_solicitacao_compra = pedidos_compras.oid_solicitacao_compra " +
              " and pedidos_compras.oid_pedido_compra = pedidos_compras_notas_fiscais.oid_pedido_compra " +
              " and pedidos_compras_notas_fiscais.oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
          ResultSet cc = executasql.executarConsulta (busca);
          while (cc.next ()) {
            compromissoED.setOid_Centro_Custo (new Integer (cc.getInt ("oid_centro_custo")));
          }
        }

        double bruto = ed.getVl_liquido_nota_fiscal (); // + ed.getVl_descontos();

        //compromissoED.setOid_Conta(new Integer(186));
        if (JavaUtil.doValida (String.valueOf (ed.getOid_conta ()))) {
          compromissoED.setOid_Conta (new Integer (new Long (ed.getOid_conta ()).intValue ()));
        }
        else {
          compromissoED.setOid_Conta (new Integer (186));
        }
//    if (compromissoED.getOid_Pessoa().length() > 11){
//          ResultSet conta = executasql.executarConsulta("select oid_conta from lancamentos_contabeis where oid_nota_fiscal = '"+ed.getOid_nota_fiscal()+"' and vl_lancamento = '"+bruto+"' and dm_acao='C'");
//	 	if (conta == null){
//	 	    // .println("CONTA == NULL");
//
//	 	    while(conta !=null && conta.next()){
//	            // compromissoED.setOid_Conta(new Integer(parametro_FixoED.getOID_Conta_Juridica_Ordem_Frete()));
//	 	        compromissoED.setOid_Conta(new Integer(conta.getString("oid_conta")));
//	        }
//	 	} else compromissoED.setOid_Conta(new Integer(186));
//     }else{
//          compromissoED.setOid_Conta(new Integer(parametro_FixoED.getOID_Conta_Ordem_Frete()));
//        }

        compromissoED.setOid_Conta_Credito (new Integer (parametro_FixoED.getOID_Conta_Credito_Ordem_Frete ()));
        compromissoED.setOid_Natureza_Operacao (new Integer (String.valueOf (ed.getOid_natureza_operacao ())));
        Integer OID_Tipo_Documento = new Integer (12);
        compromissoED.setOid_Tipo_Documento (OID_Tipo_Documento);
        compromissoED.setTx_Observacao(ed.getDm_observacao());
        //compromissoED.setOid_Natureza_Operacao(new Integer(parametro_FixoED.getOID_Natureza_Operacao_Ordem_Frete()));
        compromissoVoltaED = compromissoBD.inclui (compromissoED);
        compromissoED.setOid_Compromisso (compromissoVoltaED.getOid_Compromisso ());

        ResultSet rs = executasql.executarConsulta ("select max(OID_Parcela_Compromisso) as result from Parcelas_Compromissos ");
        while (rs != null && rs.next ()) {
          valOid = rs.getLong ("result");
          Parcela_CompromissoED.setOID_Parcela_Compromisso (String.valueOf (++valOid));
        }

        Parcela_CompromissoED.setDT_Emissao (ed.getDt_emissao ());
        Parcela_CompromissoED.setHR_Parcela_Compromisso (ed.getHr_stamp ());
        Parcela_CompromissoED.setDT_Parcela_Compromisso (ed.getDt_emissao ());
        Parcela_CompromissoED.setOID_Compromisso (compromissoED.getOid_Compromisso ());
        Parcela_CompromissoED.setOID_Parcelamento (res.getString ("oid_parcelamento"));
        Parcela_CompromissoED.setDt_stamp (ed.getDt_stamp ());
        Parcela_CompromissoED.setUsuario_Stamp (ed.getUsuario_Stamp ());
        Parcela_CompromissoED.setDm_Stamp (ed.getDm_Stamp ());

        sql = " insert into Parcelas_Compromissos (OID_Parcela_Compromisso, OID_Compromisso, OID_Parcelamento, DT_Parcela_Compromisso, HR_Parcela_Compromisso ) values ";
        sql += "(" + Parcela_CompromissoED.getOID_Parcela_Compromisso () + ",'" + Parcela_CompromissoED.getOID_Compromisso () + "'," + Parcela_CompromissoED.getOID_Parcelamento () + ",'" + Parcela_CompromissoED.getDT_Parcela_Compromisso () + "','" + Parcela_CompromissoED.getHR_Parcela_Compromisso () + "')";

        int i = executasql.executarUpdate (sql);
      }
    }
    catch(Excecoes e){
    	throw e;
    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao gerar compromisso");
      excecoes.setMetodo ("geraMovimento(Nota_Fiscal_TransacoesED ed)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista (Nota_Fiscal_CompraED edV) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean DataFormatada = new FormataDataBean ();

    try {
      sql = "SELECT * FROM notas_fiscais_transacoes WHERE 1=1 ";

      if (edV.getNr_nota_fiscal () != 0) {
        sql += "and nr_nota_fiscal=" + edV.getNr_nota_fiscal ();
      }

      if (edV.getNm_serie () != null) {
        sql += " and nm_serie ='" + edV.getNm_serie () + "' ";
      }

      if (edV.getOid_modelo_nota_fiscal () != 0) {
        sql += " and Oid_modelo_nota_fiscal =" + edV.getOid_modelo_nota_fiscal ();
      }

      if ( (edV.getOid_pessoa () != null) && (!edV.getOid_pessoa ().equals ("")) && (!edV.getOid_pessoa ().equals ("null"))) {
        sql += " and oid_pessoa ='" + edV.getOid_pessoa () + "' ";
      }

      if ( (edV.getDt_emissao () != null) && (!edV.getDt_emissao ().equals (""))) {
        sql += " and dt_emissao >='" + edV.getDt_emissao () + "' ";
      }
      if ( (edV.getDt_stamp () != null) && (!edV.getDt_stamp ().equals (""))) {
        sql += " and dt_emissao <='" + edV.getDt_stamp () + "' ";
      }

      if ( (edV.getDt_entrada () != null) && (!edV.getDt_entrada ().equals (""))) {
          sql += " and dt_entrada >='" + edV.getDt_entrada () + "' ";
      }
      if ( (edV.getDt_entrada_final () != null) && (!edV.getDt_entrada_final ().equals (""))) {
         sql += " and dt_entrada <='" + edV.getDt_entrada_final () + "' ";
      }

      if ( (edV.getDm_finalizado () != null) && (!edV.getDm_finalizado ().equals (""))) {
        if (edV.getDm_finalizado ().equals ("T")) {
          sql += " and (Dm_finalizado ='S' or Dm_finalizado ='N' or Dm_finalizado ='R' or Dm_finalizado ='E')";
        }
        else {
          sql += " and Dm_finalizado ='" + edV.getDm_finalizado () + "' ";
        }
      }
      if (String.valueOf (edV.getOID_Unidade_Contabil ()) != null &&
          !String.valueOf (edV.getOID_Unidade_Contabil ()).equals ("0") &&
          !String.valueOf (edV.getOID_Unidade_Contabil ()).equals ("null")
          ) {
        sql += " and oid_unidade_contabil = " + edV.getOID_Unidade_Contabil ();
      }
      if (String.valueOf (edV.getOid_conta()) != null &&
              !String.valueOf (edV.getOid_conta ()).equals ("0") &&
              !String.valueOf (edV.getOid_conta ()).equals ("null")
              ) {
        sql += " and oid_Conta = " + edV.getOid_conta ();
      }

      if (JavaUtil.doValida(edV.getNM_Chave_NFE())) {
        sql += " and nm_chave_nfe = '" + edV.getNM_Chave_NFE() + "'";
      }

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {
        Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED ();
        ed.setOid_nota_fiscal (res.getString ("oid_nota_fiscal"));
        ed.setNr_nota_fiscal (res.getLong ("nr_nota_fiscal"));

        ed.setDt_emissao (res.getString ("dt_emissao"));
        DataFormatada.setDT_FormataData (ed.getDt_emissao ());
        ed.setDt_emissao (DataFormatada.getDT_FormataData ());
        ed.setDt_entrada (res.getString ("dt_entrada"));
        DataFormatada.setDT_FormataData (ed.getDt_entrada ());
        ed.setDt_entrada (DataFormatada.getDT_FormataData ());

        ed.setNr_volumes (res.getLong ("nr_volumes"));
        ed.setNm_serie (res.getString ("nm_serie"));

        ed.setHr_entrada (res.getString ("hr_entrada"));
        ed.setOid_pessoa (res.getString ("oid_pessoa"));
        ed.setOid_pessoa_destinatario (res.getString ("oid_pessoa_destinatario"));
        ed.setOid_natureza_operacao (res.getLong ("oid_natureza_operacao"));
        ed.setOid_natureza_operacao_servico (res.getLong ("oid_natureza_operacao_servico"));
        ed.setOid_modelo_nota_fiscal (res.getLong ("oid_modelo_nota_fiscal"));
        ed.setVl_total_frete (res.getDouble ("vl_total_frete"));
        ed.setVl_icms (res.getDouble ("vl_icms"));
        ed.setVl_icms_substituicao(res.getDouble ("VL_ICMS_Substituicao"));
        ed.setVl_inss (res.getDouble ("vl_inss"));
        ed.setVl_irrf (res.getDouble ("vl_irrf"));
        ed.setVl_ipi (res.getDouble ("vl_ipi"));
        ed.setVl_ipi (res.getDouble ("vl_servico"));
        ed.setVl_isqn (res.getDouble ("vl_isqn"));
        ed.setVl_total_seguro (res.getDouble ("vl_total_seguro"));
        ed.setVl_total_despesas (res.getDouble ("vl_total_despesas"));
        ed.setVl_nota_fiscal (res.getDouble ("vl_nota_fiscal"));
        ed.setVl_liquido_nota_fiscal (res.getDouble ("vl_liquido_nota_fiscal"));
        ed.setDm_tipo_nota_fiscal (res.getString ("dm_tipo_nota_fiscal"));
        ed.setDt_stamp (res.getString ("dt_stamp"));
        ed.setDm_observacao (res.getString ("dm_observacao"));
        ed.setDm_forma_pagamento (res.getString ("dm_forma_pagamento"));
        ed.setNr_parcelas (res.getLong ("nr_parcelas"));
        ed.setVl_descontos (res.getDouble ("vl_descontos"));
        ed.setDm_finalizado (res.getString ("dm_finalizado"));
        ed.setOID_Unidade_Contabil (res.getLong ("oid_unidade_fiscal"));
        ed.setOID_Unidade_Fiscal (res.getLong ("oid_unidade_contabil"));
        ed.setNM_Chave_NFE(res.getString ("nm_chave_nfe"));
        list.add (ed);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar Notas Fiscais");
      excecoes.setMetodo ("Sele��o");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return list;
  }

  public Nota_Fiscal_CompraED getByRecord (Nota_Fiscal_CompraED edV) throws Excecoes {

    String sql = null;
    Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED ();
    FormataDataBean DataFormatada = new FormataDataBean ();
    ModeloNotaFiscalED modelo = new ModeloNotaFiscalED();
    ModeloNotaFiscalRN modelorn = new ModeloNotaFiscalRN();

    try {
      sql = "SELECT * FROM notas_fiscais_transacoes WHERE 1=1 ";

      if ( (edV.getNr_nota_fiscal () != 0)) {
        sql += "and nr_nota_fiscal=" + edV.getNr_nota_fiscal ();
      }
      if ( (edV.getNm_serie () != null)) {
        sql += " and nm_serie ='" + edV.getNm_serie () + "' ";
      }
      if (edV.getOid_pessoa () != null) {
        sql += " and oid_pessoa ='" + edV.getOid_pessoa () + "' ";
      }
      if (edV.getOid_pessoa_destinatario () != null) {
        sql += " and oid_pessoa_destinatario ='" + edV.getOid_pessoa_destinatario () + "' ";
      }
      if (edV.getOid_modelo_nota_fiscal () != 0) {
        sql += " and Oid_modelo_nota_fiscal =" + edV.getOid_modelo_nota_fiscal ();
      }
      if (edV.getOid_nota_fiscal () != null) {
        sql += " and Oid_nota_fiscal ='" + edV.getOid_nota_fiscal () + "'";
      }
      if (edV.getDm_tipo_nota_fiscal () != null) {
        sql += " and dm_tipo_nota_fiscal = '" + edV.getDm_tipo_nota_fiscal () + "'";
      }
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {

        ed.setOid_nota_fiscal (res.getString ("oid_nota_fiscal"));
        ed.setNr_nota_fiscal (res.getLong ("nr_nota_fiscal"));
        ed.setNr_volumes (res.getLong ("nr_volumes"));
        ed.setNm_serie (res.getString ("nm_serie"));
        ed.setDt_emissao (res.getString ("dt_emissao"));
        DataFormatada.setDT_FormataData (ed.getDt_emissao ());
        ed.setDt_emissao (DataFormatada.getDT_FormataData ());
        ed.setDt_entrada (res.getString ("dt_entrada"));
        DataFormatada.setDT_FormataData (ed.getDt_entrada ());
        ed.setDt_entrada (DataFormatada.getDT_FormataData ());

        // .println("NF 1");

        ed.setHr_entrada (res.getString ("hr_entrada"));
        ed.setOid_pessoa (res.getString ("oid_pessoa"));
        ed.setOid_pessoa_destinatario (res.getString ("oid_pessoa_destinatario"));
        ed.setOid_natureza_operacao (res.getLong ("oid_natureza_operacao"));
        ed.setOid_modelo_nota_fiscal (res.getLong ("oid_modelo_nota_fiscal"));
        ed.setVl_total_frete (res.getDouble ("vl_total_frete"));
        ed.setVl_icms (res.getDouble ("vl_icms"));
        ed.setVl_icms_substituicao(res.getDouble ("VL_ICMS_Substituicao"));
        ed.setVl_inss (res.getDouble ("vl_inss"));
        ed.setVl_irrf (res.getDouble ("vl_irrf"));
        ed.setVl_ipi (res.getDouble ("vl_ipi"));
        ed.setVL_Servico (res.getDouble ("vl_servico"));
        ed.setVL_Outros (res.getDouble ("vl_outros"));

        // .println("NF 2");

        ed.setVl_isqn (res.getDouble ("vl_isqn"));
        ed.setVl_total_seguro (res.getDouble ("vl_total_seguro"));
        ed.setVl_total_despesas (res.getDouble ("vl_total_despesas"));
        ed.setVl_nota_fiscal (res.getDouble ("vl_nota_fiscal"));
        ed.setVl_liquido_nota_fiscal (res.getDouble ("vl_liquido_nota_fiscal"));
        ed.setDm_tipo_nota_fiscal (res.getString ("dm_tipo_nota_fiscal"));
        ed.setDt_stamp (res.getString ("dt_stamp"));
        ed.setDm_observacao (res.getString ("dm_observacao"));
        ed.setDm_forma_pagamento (res.getString ("dm_forma_pagamento"));
        ed.setNr_parcelas (res.getLong ("nr_parcelas"));
        ed.setVl_descontos (res.getDouble ("vl_descontos"));
        ed.setOID_Unidade_Contabil (res.getLong ("oid_unidade_contabil"));
        ed.setOID_Unidade_Fiscal (res.getLong ("oid_unidade_fiscal"));
        ed.setOID_Unidade_Pagadora (res.getLong ("oid_unidade_pagadora"));
        ed.setDm_finalizado (res.getString ("dm_finalizado"));

        ed.setOid_conta (res.getLong ("oid_conta"));
        ed.setOid_conta_servico (res.getLong ("oid_conta_servico"));
        ed.setOid_natureza_operacao_servico (res.getLong ("oid_natureza_operacao_servico"));
        ed.setOid_conta_outros (res.getLong ("oid_conta_outros"));
        ed.setOid_natureza_operacao_outros (res.getLong ("oid_natureza_operacao_outros"));
        ed.setOid_centro_custo (res.getLong ("oid_centro_custo"));

        ed.setNM_Chave_NFE(res.getString ("nm_chave_nfe"));

        ed.setVL_Pis (res.getDouble ("VL_Pis"));
        ed.setVL_Cofins (res.getDouble ("VL_Cofins"));
        ed.setVL_Csll (res.getDouble ("VL_Csll"));
        // .println("NF 3");

        ed.setDM_Romaneio (res.getString ("dm_romaneio"));

        ed.setNM_Extensao("");
//// .println("ARQUIVO>"+Parametro_FixoED.getInstancia().getAppPath() + "/documentos/notaentrada/"+res.getString("oid_nota_fiscal")+".pdf");
        File file = new File(Parametro_FixoED.getInstancia().getAppPath() + "/documentos/notaentrada/"+res.getString("oid_nota_fiscal")+".jpg");
        if(file.exists()){
            ed.setNM_Extensao(".jpg");
        }
        file = new File(Parametro_FixoED.getInstancia().getAppPath() + "/documentos/notaentrada/"+res.getString("oid_nota_fiscal")+".pdf");
        if(file.exists()){
            ed.setNM_Extensao(".pdf");
        }
        file = new File(Parametro_FixoED.getInstancia().getAppPath() + "/documentos/notaentrada/"+res.getString("oid_nota_fiscal")+".gif");
        if(file.exists()){
            ed.setNM_Extensao(".gif");
        }
        file = new File(Parametro_FixoED.getInstancia().getAppPath() + "/documentos/notaentrada/"+res.getString("oid_nota_fiscal")+".psd");
        if(file.exists()){
            ed.setNM_Extensao(".psd");
        }

        // .println("NF 4");

        //parcelamento
        ed.setDM_Parcelamento("N");
        ed.setDM_Estoque("S");

        sql = "Select sum(vl_parcela) as tt_parcela, count(oid_parcelamento) as numero from Parcelamentos_Financeiros Where dt_pagamento is not null and oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "'";
//         .println(sql);

        res = executasql.executarConsulta (sql);
        if (res.next ()) {
          if (res.getDouble ("tt_parcela") == ed.getVl_liquido_nota_fiscal ()) {
              ed.setDM_Parcelamento("S");
          }
        }

//         .println("NF 5");
        //estoque
        ed.setDM_Estoque("S");
        if ("E".equals(ed.getDm_tipo_nota_fiscal())) {
            ed.setDM_Estoque("N");
            sql = "Select sum(vl_liquido) as ttEstoque  from itens_notas_fiscais_transacoes Where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "'";
//          System.out.println(sql);
	        res = executasql.executarConsulta (sql);
	         if (res.next ()) {
	        	 System.out.println(res.getDouble ("ttEstoque")+"|"+Valores.trataDouble(ed.getVl_nota_fiscal()+ed.getVL_Servico(),2));
	           if (res.getDouble ("ttEstoque") == Valores.trataDouble(ed.getVl_nota_fiscal()+ed.getVL_Servico(),2)) {
	               ed.setDM_Estoque("S");
	           }
	        }
        }

        //fiscal
        ed.setDM_Fiscal("I");
        if (ed.getOid_natureza_operacao()>0 && ed.getVl_nota_fiscal() > 0){
            sql = "SELECT DM_Tipo_Imposto FROM Naturezas_Operacoes WHERE DM_Tipo_Imposto<>'N' AND oid_Natureza_Operacao="+ed.getOid_natureza_operacao();
//            .println(sql);
            res = executasql.executarConsulta (sql);
            if (res.next ()) {
            	ed.setDM_Fiscal("N");
            }
        }
        if (ed.getOid_natureza_operacao_servico()>0 && ed.getVL_Servico() > 0 && !"N".equals(ed.getDM_Fiscal())){
            sql = "SELECT DM_Tipo_Imposto FROM Naturezas_Operacoes WHERE DM_Tipo_Imposto<>'N' AND oid_Natureza_Operacao="+ed.getOid_natureza_operacao_servico();
            res = executasql.executarConsulta (sql);
            if (res.next ()) {
            	ed.setDM_Fiscal("N");
            }
        }
        if (ed.getOid_natureza_operacao_outros()>0 && ed.getVL_Outros() > 0 && !"N".equals(ed.getDM_Fiscal())){
            sql = "SELECT DM_Tipo_Imposto FROM Naturezas_Operacoes WHERE DM_Tipo_Imposto<>'N' AND oid_Natureza_Operacao="+ed.getOid_natureza_operacao_outros();
            res = executasql.executarConsulta (sql);
            if (res.next ()) {
            	ed.setDM_Fiscal("N");
            }
        }

        if ("N".equals(ed.getDM_Fiscal())){
            sql = "Select sum(VL_Contabil) as tt_Contabil from Livros_Fiscais Where oid_Pessoa_Emitente = '"  +  ed.getOid_pessoa() + "' AND  oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "'";
            // .println(sql);

            res = executasql.executarConsulta (sql);
            if (res.next ()) {
              if (res.getDouble ("tt_Contabil") == Valor.round((ed.getVl_nota_fiscal()-ed.getVl_descontos()+ed.getVL_Servico()+ed.getVL_Outros()+ed.getVl_ipi()+ed.getVl_total_frete()+ed.getVl_icms_substituicao()+ed.getVl_total_despesas()),2)) {
                  ed.setDM_Fiscal("S");
              }
            }
        }

        modelo.setOid_Modelo_Nota_Fiscal(ed.getOid_modelo_nota_fiscal());
        modelo = modelorn.getByRecord(modelo);
        ed.setDM_Contabiliza(modelo.getDM_Aceita_Contabilizacao());

        // .println("getVl_liquido_nota_fiscal>"+ed.getVl_liquido_nota_fiscal());
        // .println("getDM_Parcelamento>"+ed.getDM_Parcelamento());
        // .println("getDM_Fiscal>"+ed.getDM_Fiscal());
        // .println("setDM_Contabiliza>"+ed.getDM_Contabiliza());


      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar Sugestao");
      excecoes.setMetodo ("Sele��o");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }


  public void inclui_Lancamento (Nota_Fiscal_CompraED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = null;
    Lancamento_ContabilED lan = new Lancamento_ContabilED ();
    Lancamento_ContabilBD geralan = new Lancamento_ContabilBD (this.executasql);

    int valor = 0;
    ResultSet rs = null;
    double quantia = 0;

    try {

      ed = this.getByRecord(ed);

      if("S".equals(ed.getDM_Contabiliza())){

	      sql = "Select * from Sugestoes_Modelos sm, eventos_contabeis ev ";
	      sql += "where sm.oid_sugestao_contabil=ev.oid_sugestao_contabil and sm.oid_modelo_nota_fiscal=" + ed.getOid_modelo_nota_fiscal ();
	      ResultSet res = null;

	      res = this.executasql.executarConsulta (sql);

	      while (res.next ()) {
	        lan.setOID_Conta (res.getLong ("oid_conta"));
	        lan.setDM_Acao (res.getString ("Dm_tipo_contabilizacao"));
	        lan.setCD_Historico (res.getLong ("Oid_historico_complementar"));
	        valor = res.getInt ("cd_valor");
	        switch (valor) {
	          case 1:
	            quantia = ed.getVl_nota_fiscal ();
	            break;
	          case 2:
	            quantia = ed.getVl_liquido_nota_fiscal ();
	            break;
	          case 3:
	            quantia = ed.getVl_icms ();
	            break;
	          case 4:
	            quantia = ed.getVl_irrf ();
	            break;
	          case 5:
	            quantia = ed.getVl_inss ();
	            break;
	          case 6:
	            quantia = ed.getVl_isqn ();
	            break;
	          case 7:
	            quantia = ed.getVl_descontos ();
	            break;
	          case 8:
	            quantia = ed.getVl_ipi ();
	            break;
	          case 9:
	            quantia = ed.getVl_total_frete ();
	            break;
	          case 10:
	            quantia = ed.getVl_total_seguro ();
	            break;
	          case 11:
	            quantia = ed.getVl_total_despesas ();
	            break;
	          case 12:
	            quantia = ed.getVL_Servico ();
	            break;
	          case 20:
	            quantia = ed.getVL_Pis ();
	            break;
	          case 21:
	            quantia = ed.getVL_Cofins ();
	            break;
	          case 22:
	            quantia = ed.getVL_Csll ();
	            break;

	        }
	        lan.setOID_Unidade_Contabil (ed.getOID_Unidade_Contabil ());
	        lan.setVL_Lancamento (quantia);

	        lan.setOID_Nota_Fiscal (ed.getOid_nota_fiscal ());

	        lan.setNM_Complementar ("");
	        lan.setDM_Stamp (" ");
	        lan.setDT_Stamp (Data.getDataDMY ());
	        lan.setDT_Lancamento (ed.getDt_entrada ());
	        if (quantia > 0) {
	          geralan.inclui (lan);
	        }
	      }
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui");

      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  /*Atualiza a tabela para dm_finaliza = "R"*/
  public void reabre (Nota_Fiscal_CompraED ed) throws Excecoes {
    String sql = null;

    try {

      this.deleta_Parcelamento(ed);

      //se a exclus�o de todos os compromisso n�o for problam�tica ent�o reabre a nota
      sql = "update Notas_Fiscais_Transacoes Set dm_finalizado = 'R' " +
          "Where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "'";
      int i = executasql.executarUpdate (sql);

      if ("S".equals (parametro_FixoED.getDM_Gera_Lancamento_Contabil ())) {
        //desfaz os lancamentos contabeis do movimento
        new Lancamento_ContabilBD (this.executasql).deleta_CTB_Nota_Fiscal (ed);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao Reabir, verifique compromissos j� vinculados a lote de pagamento");
      excecoes.setMetodo ("reabre");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta_Parcelamento (Nota_Fiscal_CompraED ed) throws Excecoes {
	    String sql = null;

	    // .println("deleta_Parcelamento");
	    try {
	      //exclui os compromissos e depois reabre
	      sql = "DELETE from Parcelamentos_financeiros where oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
	      // .println(sql);
	      executasql.executarUpdate (sql);

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro excluir Parcelamento");
	      excecoes.setMetodo ("deleta_Parcelamento");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
  }

  public void inclui_Lancamentos_Pagamento_Compromisso (Lote_CompromissoED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = null;
    Lancamento_ContabilED lan = new Lancamento_ContabilED ();
    Lancamento_ContabilBD geralan = new Lancamento_ContabilBD (this.executasql);

    int valor = 0;
    ResultSet rs = null;
    Double quantia = null;

    try {
      sql = "Select * from Sugestoes_Modelos sm, eventos_contabeis ev ";
      sql += "where sm.oid_sugestao_contabil=ev.oid_sugestao_contabil and sm.oid_modelo_nota_fiscal=" + ed.getOID_Modelo ();
      ResultSet res = null;

      // // .println( ed.getOID_Modelo() );

      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        lan.setOID_Conta (res.getLong ("oid_conta"));
        lan.setDM_Acao (res.getString ("Dm_tipo_contabilizacao"));
        lan.setCD_Historico (res.getLong ("Oid_historico_complementar"));
        valor = res.getInt ("cd_valor");

        // // .println("valor = "+ valor);
        switch (valor) {

          case 1:
            quantia = ed.getVl_Compromisso ();
            break;
          case 2:
            quantia = ed.getVL_Pedagio_Pagar ();
            break;
          case 3:
            quantia = ed.getVL_Pedagio_Receber ();
            break;
          case 4:
            quantia = ed.getVl_Lote_Pagamento ();
            break;
          case 5:
            quantia = ed.getVl_Multa_Pagamento ();
            break;
          case 6:
            quantia = ed.getVl_Outras_Despesas ();
            break;
          case 7:
            quantia = ed.getVl_Desconto ();
            break;
          case 11:
            quantia = ed.getVl_Outras_Despesas ();
            break;
          case 13:
          case 14:
          case 15:
            quantia = ed.getVl_Pagamento ();
            break;
          case 16:
            quantia = ed.getVL_Juro_Pagamento ();
            break;
          case 17:
            quantia = ed.getVl_Multa_Pagamento ();
            break;
          case 18:
            quantia = ed.getVL_Pedagio_Pagar ();
            break;
          case 19:
            quantia = ed.getVL_Pedagio_Receber ();
            break;

        }

        double VL_Lancto = new Double (String.valueOf (quantia)).doubleValue ();
        lan.setVL_Lancamento (VL_Lancto);
        lan.setOID_Nota_Fiscal ("");
        lan.setNM_Complementar ("");
        lan.setDM_Stamp (" ");
        lan.setDT_Stamp (Data.getDataDMY ());
        lan.setDT_Lancamento (ed.getDt_Pagamento ());
        lan.setOID_Unidade_Contabil (ed.getOID_Unidade ());
        lan.setOID_Compromisso (new Long (String.valueOf (ed.getOid_Compromisso ())).longValue ());
        lan.setOID_Lote_Pagamento (new Long (String.valueOf (ed.getOid_Lote_Pagamento ())).longValue ());

        if (VL_Lancto > 0) {
          geralan.inclui_Pagamento (lan);
        }
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui_Lancamentos_Pagamento_Compromisso");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void geraRelatorioImpostoSemanalNotasFiscais (String dt_ini , String dt_fim , String oid_unidade , String nm_fantasia , HttpServletResponse response) throws Excecoes {

  }

  public void inclui_Lancamentos_Lote_Posto (Posto_CompromissoED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = null;
    Lancamento_ContabilED lan = new Lancamento_ContabilED ();
    Lancamento_ContabilBD geralan = new Lancamento_ContabilBD (this.executasql);

    int valor = 0;
    ResultSet rs = null;
    Double quantia = null;

    try {
      sql = "Select * from Sugestoes_Modelos sm, eventos_contabeis ev ";
      sql += "where sm.oid_sugestao_contabil=ev.oid_sugestao_contabil and sm.oid_modelo_nota_fiscal=" + ed.getOID_Modelo ();
      ResultSet res = null;

      // // .println( ed.getOID_Modelo() );

      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        lan.setOID_Conta (res.getLong ("oid_conta"));
        lan.setDM_Acao (res.getString ("Dm_tipo_contabilizacao"));
        lan.setCD_Historico (res.getLong ("Oid_historico_complementar"));
        valor = res.getInt ("cd_valor");

        switch (valor) {

          case 1:
            quantia = ed.getVl_Compromisso ();
            break;
          case 2:
            quantia = ed.getVL_Pedagio_Pagar ();
            break;
          case 3:
            quantia = ed.getVL_Pedagio_Receber ();
            break;
          case 4:
            quantia = ed.getVl_Lote_Posto ();
            break;
          case 5:
            quantia = ed.getVl_Multa_Pagamento ();
            break;
          case 6:
            quantia = ed.getVl_Outras_Despesas ();
            break;
          case 7:
            quantia = ed.getVl_Desconto ();
            break;
          case 11:
            quantia = ed.getVl_Outras_Despesas ();
            break;
          case 13:
          case 14:
          case 15:
            quantia = ed.getVl_Pagamento ();
            break;
          case 16:
            quantia = ed.getVL_Juro_Pagamento ();
            break;
          case 17:
            quantia = ed.getVl_Multa_Pagamento ();
            break;
          case 18:
            quantia = ed.getVL_Pedagio_Pagar ();
            break;
          case 19:
            quantia = ed.getVL_Pedagio_Receber ();
            break;

        }

        double VL_Lancto = new Double (String.valueOf (quantia)).doubleValue ();
        lan.setVL_Lancamento (VL_Lancto);
        lan.setOID_Nota_Fiscal ("");
        lan.setNM_Complementar ("");
        lan.setDM_Stamp (" ");
        lan.setDT_Stamp (Data.getDataDMY ());
        lan.setDT_Lancamento (ed.getDt_Pagamento ());
        lan.setOID_Unidade_Contabil (ed.getOID_Unidade ());
        lan.setOID_Compromisso (new Long (String.valueOf (ed.getOid_Compromisso ())).longValue ());
        lan.setOID_Lote_Posto (new Long (String.valueOf (ed.getOid_Lote_Posto ())).longValue ());

        if (VL_Lancto > 0) {
          geralan.inclui_Pagamento (lan);
        }
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui_Lancamentos_Pagamento_Compromisso");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public Nota_Fiscal_CompraED calculaPISCOFINSCSLL (Nota_Fiscal_CompraED ed) throws Excecoes {

    String sql = null;
    String OID_Estado_Origem = "";
    String OID_Estado_Destino = "";
    double PE_Aliquota_COFINS = 0 , PE_Aliquota_PIS = 0 , PE_Aliquota_CSLL = 0;
    double somaNFSv = 0 , somaPISDescontado = 0 , somaCOFINSDescontado = 0 , somaCSLLDescontado = 0;
    double valorPISDesc = 0 , valorCOFINSDesc = 0 , valorCSLLDesc = 0;
    Nota_Fiscal_CompraED conED = new Nota_Fiscal_CompraED ();

    try {

      long natOper = ed.getOid_natureza_operacao ();

      if (natOper == 21 || natOper == 47) {

        sql = " SELECT Estados.OID_Estado FROM Cidades, Regioes_Estados, Estados, Pessoas " +
            " WHERE Estados.OID_Estado = Regioes_Estados.OID_Estado " +
            " AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
            " AND Cidades.OID_Cidade = Pessoas.OID_Cidade" +
            " AND Pessoas.OID_Pessoa = '" + ed.getOid_pessoa () + "' "; //Nota_Fiscal.OID_Pessoa_Remetente";

        ResultSet rs = executasql.executarConsulta (sql);

        while (rs.next ()) {
          OID_Estado_Origem = String.valueOf (rs.getLong ("OID_Estado"));
        }

        sql = " SELECT Estados.OID_Estado FROM Cidades, Regioes_Estados, Estados " +
            " WHERE Estados.OID_Estado = Regioes_Estados.OID_Estado " +
            " AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
            " AND Cidades.OID_Cidade = Pessoas.OID_Cidade" +
            " AND Pessoas.OID_Pessoa = '" + ed.getOid_pessoa_destinatario () + "'"; //Nota_Fiscal.OID_Pessoa_Destinatario";

        rs = null;
        rs = executasql.executarConsulta (sql);

        while (rs.next ()) {
          OID_Estado_Destino = String.valueOf (rs.getLong ("OID_Estado"));
        }

        sql = " SELECT * FROM Taxas " +
            " WHERE Taxas.OID_Estado = " + OID_Estado_Origem +
            " AND   Taxas.OID_Estado_Destino = " + OID_Estado_Destino;

        rs = null;
        rs = executasql.executarConsulta (sql);

        while (rs.next ()) {
          PE_Aliquota_PIS = rs.getDouble ("pe_aliquota_pis_servico");
          PE_Aliquota_COFINS = rs.getDouble ("pe_aliquota_cofins_servico");
          PE_Aliquota_CSLL = rs.getDouble ("pe_aliquota_csll_servico");
        }

        String dataInicialMes = ("01" + ed.getDt_emissao ().substring (2 , 10));

        sql = "SELECT " +
            "sum(NFTrans.vl_nota_fiscal) as somaNFSv, " +
            "sum(NFTrans.vl_pis) as somaPISDescontado, " +
            "sum(NFTrans.vl_cofins) as somaCOFINSDescontado, " +
            "sum(NFTrans.vl_csll) as somaCSLLDescontado " +
            "FROM notas_fiscais_transacoes NFTrans, Unidades " +
            "WHERE NFTrans.oid_unidade_fiscal = Unidades.oid_unidade " +
            "AND NFTrans.dt_emissao >= '" + dataInicialMes + "' " +
            "AND NFTrans.dt_emissao <= '" + ed.getDt_emissao () + "' " +
            "AND NFTrans.oid_pessoa = '" + ed.getOid_pessoa () + "' " +
            "AND NFTrans.oid_pessoa_destinatario ='" + ed.getOid_pessoa_destinatario () + "' " +
            "AND NFTrans.dm_tipo_nota_fiscal = 'E' " +
            "AND NFTrans.nr_nota_fiscal <> " + String.valueOf (ed.getNr_nota_fiscal ());

        ResultSet somasImpostos = executasql.executarConsulta (sql);
        while (somasImpostos.next ()) {
          somaNFSv = somasImpostos.getDouble ("somaNFSv");
          somaPISDescontado = somasImpostos.getDouble ("somaPISDescontado");
          somaCOFINSDescontado = somasImpostos.getDouble ("somaCOFINSDescontado");
          somaCSLLDescontado = somasImpostos.getDouble ("somaCSLLDescontado");
        }

        double valorNFAtual = ed.getVl_nota_fiscal ();
        somaNFSv = somaNFSv + valorNFAtual;

        if (somaNFSv > 5000) {
          valorPISDesc = (somaNFSv * PE_Aliquota_PIS / 100) - somaPISDescontado;
          valorCOFINSDesc = (somaNFSv * PE_Aliquota_COFINS / 100) - somaCOFINSDescontado;
          valorCSLLDesc = (somaNFSv * PE_Aliquota_CSLL / 100) - somaCSLLDescontado;

          double somatorioImpostos = valorPISDesc + valorCOFINSDesc + valorCSLLDesc;

          if (somatorioImpostos >= valorNFAtual) {
            double somaAlicotas = PE_Aliquota_PIS + PE_Aliquota_COFINS + PE_Aliquota_CSLL;
            valorPISDesc = ed.getVl_nota_fiscal () * PE_Aliquota_PIS / somaAlicotas;
            valorCOFINSDesc = ed.getVl_nota_fiscal () * PE_Aliquota_COFINS / somaAlicotas;
            valorCSLLDesc = ed.getVl_nota_fiscal () * PE_Aliquota_CSLL / somaAlicotas;
          }
        }
      }

      conED.setVL_Pis (valorPISDesc);
      conED.setVL_Cofins (valorCOFINSDesc);
      conED.setVL_Csll (valorCSLLDesc);

      return conED;
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista_Pedidos (Nota_Fiscal_CompraED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean DataFormatada = new FormataDataBean ();

    try {
      sql = "SELECT pedidos_compras.*, notas_fiscais_transacoes.nr_nota_fiscal, notas_fiscais_transacoes.oid_nota_fiscal as oid_nota, notas_fiscais_transacoes.dt_entrada, pedidos_compras_notas_fiscais.oid_pedido_compra_nota_fiscal  FROM pedidos_compras, notas_fiscais_transacoes, pedidos_compras_notas_fiscais " +
          " WHERE pedidos_compras.oid_pedido_compra = pedidos_compras_notas_fiscais.oid_pedido_compra" +
          " and pedidos_compras_notas_fiscais.oid_nota_fiscal = notas_fiscais_transacoes.oid_nota_fiscal";
      if (ed.getOid_nota_fiscal () != null && !ed.getOid_nota_fiscal ().equals ("null") && !ed.getOid_nota_fiscal ().equals ("")) {
        sql += " and notas_fiscais_transacoes.oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
      }
      if (ed.getOid_Pedido_Compra_Nota_Fiscal () != null && !ed.getOid_Pedido_Compra_Nota_Fiscal ().equals ("null") && !ed.getOid_Pedido_Compra_Nota_Fiscal ().equals ("")) {
        sql += " and pedidos_compras.oid_pedido_compra = '" + ed.getOid_Pedido_Compra_Nota_Fiscal () + "'";
      }
      sql += " order by pedidos_compras_notas_fiscais.oid_pedido_compra";

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {
        Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_Pedido_compra (res.getLong ("oid_pedido_compra"));
        edVolta.setOid_Solicitacao_compra (res.getLong ("oid_Solicitacao_compra"));
        edVolta.setOid_nota_fiscal (res.getString ("oid_nota"));
        edVolta.setNr_nota_fiscal (res.getString ("nr_nota_fiscal"));
        DataFormatada.setDT_FormataData (res.getString ("dt_entrada"));
        edVolta.setDt_entrega (DataFormatada.getDT_FormataData ());
        edVolta.setOid_Pedido_compra_nota_fiscal (res.getString ("oid_pedido_compra_nota_fiscal"));
        edVolta.setDt_pedido (res.getString ("dt_pedido_compra"));
        DataFormatada.setDT_FormataData (edVolta.getDt_pedido ());
        edVolta.setDt_pedido (DataFormatada.getDT_FormataData ());
        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar Pedidos");
      excecoes.setMetodo ("lista_Pedidos");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return list;
  }

  public Solicitacao_CompraED inclui_Pedido_Nota_Fiscal (Solicitacao_CompraED ed) throws Excecoes {

    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    String sql = null;
    String data = "";
    edVolta.setOid_Pedido_compra_nota_fiscal (String.valueOf (ed.getOid_Pedido_compra ()) + ed.getOid_nota_fiscal ());
    try {

      sql = "INSERT INTO Pedidos_Compras_Notas_Fiscais (" +
          " oid_Nota_Fiscal," +
          " oid_pedido_compra," +
          " dt_stamp," +
          " dm_finalizado, " +
          " usuario_stamp," +
          " dm_stamp," +
          " oid_pedido_compra_Nota_Fiscal" +
          ")";
      sql += " values ( '";
      sql += ed.getOid_nota_fiscal () + "'," +
          ed.getOid_Pedido_compra () + ",'" +
          Data.getDataDMY () + "','N','','S'," +
          "'" + edVolta.getOid_Pedido_compra_nota_fiscal () +
          "')";

      int i = executasql.executarUpdate (sql);

//        seta pedido para status apropriado
      sql = "UPDATE Pedidos_Compras set " +
          " dm_status = 'N'";
      sql += " WHERE oid_Pedido_compra = " + ed.getOid_Pedido_compra ();
      int u = executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir!");
      excecoes.setMetodo ("inclui_Pedido_Nota_Fiscal()");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public Solicitacao_CompraED getByRecord_Pedido_Nota_Fiscal (Solicitacao_CompraED ed) throws Excecoes {

    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    String sql = null;
    String sqlBusca = null;
    FormataDataBean DataFormatada = new FormataDataBean ();

    try {
      sql = "select * from pedidos_compras_notas_fiscais where 1=1 ";
      if (ed.getOid_nota_fiscal () != null && !ed.getOid_nota_fiscal ().equals ("0")) {
        sql += " and Oid_nota_fiscal ='" + ed.getOid_nota_fiscal () + "'";
      }
      if (String.valueOf (ed.getOid_Pedido_compra ()) != null && !String.valueOf (ed.getOid_Pedido_compra ()).equals ("0")) {
        sql += " and Oid_Pedido_compra ='" + ed.getOid_Pedido_compra () + "'";
      }
      if (ed.getOid_Pedido_compra_nota_fiscal () != null && !ed.getOid_Pedido_compra_nota_fiscal ().equals ("0")) {
        sql += " and Oid_Pedido_compra_nota_fiscal ='" + ed.getOid_Pedido_compra_nota_fiscal () + "'";
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        edVolta.setOid_Pedido_compra (res.getLong ("oid_pedido_compra"));
        sqlBusca = "select pedidos_compras.dm_status, pedidos_compras.dt_pedido_compra," +
            " solicitacoes_compras.oid_unidade from pedidos_compras, solicitacoes_compras" +
            " where pedidos_compras.oid_solicitacao_compra = solicitacoes_compras.oid_solicitacao_compra" +
            " and pedidos_compras.oid_pedido_compra = " + edVolta.getOid_Pedido_compra ();
        ResultSet rs = null;
        rs = this.executasql.executarConsulta (sqlBusca);
        while (rs.next ()) {
          edVolta.setDm_status_pedido (rs.getString (1));
          if (edVolta.getDm_status_pedido () != null &&
              edVolta.getDm_status_pedido ().equals ("P")) {
            edVolta.setDm_status_pedido ("Pendente");
          }
          if (edVolta.getDm_status_pedido () != null &&
              edVolta.getDm_status_pedido ().equals ("C")) {
            edVolta.setDm_status_pedido ("Cancelado");
          }
          if (edVolta.getDm_status_pedido () != null &&
              edVolta.getDm_status_pedido ().equals ("E")) {
            edVolta.setDm_status_pedido ("Encerrado");
          }
          if (edVolta.getDm_status_pedido () != null &&
              edVolta.getDm_status_pedido ().equals ("A")) {
            edVolta.setDm_status_pedido ("Parcial");
          }
          if (edVolta.getDm_status_pedido () != null &&
              edVolta.getDm_status_pedido ().equals ("N")) {
            edVolta.setDm_status_pedido ("Nota Fiscal");
          }
          edVolta.setDt_pedido (rs.getString (2));
          DataFormatada.setDT_FormataData (edVolta.getDt_pedido ());
          edVolta.setDt_pedido (DataFormatada.getDT_FormataData ());
          edVolta.setOid_unidade (rs.getLong (3));
        }
        edVolta.setOid_nota_fiscal (res.getString ("oid_nota_fiscal"));
        sqlBusca = "select nr_nota_fiscal from notas_fiscais_transacoes " +
            " where oid_nota_fiscal = '" + edVolta.getOid_nota_fiscal () + "'";
        rs = null;
        rs = this.executasql.executarConsulta (sqlBusca);
        while (rs.next ()) {
          edVolta.setNr_nota_fiscal (rs.getString (1));
        }
        edVolta.setOid_Pedido_compra_nota_fiscal (res.getString ("oid_pedido_compra_nota_fiscal"));
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar!");
      excecoes.setMetodo ("getByRecord_Pedido_Nota_Fiscal()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void deleta_Pedido_Nota_Fiscal (Nota_Fiscal_CompraED ed) throws Excecoes {

    String sql = null;
    try {
      sql = "delete from pedidos_compras_notas_fiscais where" +
          " oid_pedido_compra_nota_fiscal = '" + ed.getOid_Pedido_Compra_Nota_Fiscal () + "'";
      int i = this.executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir!");
      excecoes.setMetodo ("deleta_Pedido_Nota_Fiscal()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista_Romaneio (Nota_Fiscal_CompraED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean DataFormatada = new FormataDataBean ();

    try {
      sql = "SELECT * FROM romaneios_notas_fiscais " +
          " WHERE romaneios_notas_fiscais.oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
      sql += " order by romaneios_notas_fiscais.oid_estoque";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      //popula
      while (res.next ()) {
        Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_nota_fiscal (res.getString ("oid_nota_fiscal"));
        sql = "select nr_nota_fiscal from notas_fiscais_transacoes" +
            " where oid_nota_fiscal = '" + edVolta.getOid_nota_fiscal () + "'";
        ResultSet rs = null;
        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          edVolta.setNr_nota_fiscal (rs.getString (1));
        }
        edVolta.setOid_estoque (res.getLong ("oid_estoque"));
        sql = "select cd_estoque, nm_estoque, dm_tipo_produto from estoques" +
            " where oid_estoque = " + edVolta.getOid_estoque ();
        rs = null;
        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          edVolta.setCd_estoque (rs.getString (1));
          edVolta.setNm_estoque (rs.getString (2));
          if (rs.getString (3) != null && rs.getString (3).equals ("E")) {
            edVolta.setDm_status_item ("Estoque");
          }
          else {
            edVolta.setDm_status_item ("Diverso");
          }
        }
        edVolta.setOid_romaneio_nota_fiscal (res.getString ("oid_romaneio_nota_fiscal"));
//          if(res.getString("oid_localizacao") != null){
//              edVolta.setOid_localizacao(res.getString("oid_localizacao"));
//          } else edVolta.setOid_localizacao("");
        edVolta.setVl_quantidade (res.getDouble ("vl_quantidade"));
        edVolta.setDt_stamp (res.getString ("dt_stamp"));
        DataFormatada.setDT_FormataData (edVolta.getDt_stamp ());
        edVolta.setDt_stamp (DataFormatada.getDT_FormataData ());
        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar Romaneio");
      excecoes.setMetodo ("lista_Romaneio");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return list;
  }

  public Solicitacao_CompraED inclui_Romaneio (Nota_Fiscal_CompraED ed) throws Excecoes {

    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    String sql = null;
    String inclui = null;
    try {

      sql = "select oid_estoque, oid_item_nota_fiscal, oid_nota_fiscal" +
          " from itens_notas_fiscais_transacoes" +
          " where oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {

        inclui = "INSERT INTO Romaneios_Notas_Fiscais (" +
            " oid_Nota_Fiscal," +
            " oid_estoque," +
            " dt_stamp," +
            " usuario_stamp," +
            " dm_stamp," +
            " oid_Romaneio_Nota_Fiscal," +
            " dm_finalizado)";
        inclui += " values ('" +
            res.getString ("oid_nota_fiscal") + "', '" +
            res.getString ("oid_estoque") + "', '" +
            Data.getDataDMY () + "', '','N', '" +
            res.getString ("oid_item_nota_fiscal") + "', 'N')";

        int i = executasql.executarUpdate (inclui);

        sql = "update notas_fiscais_transacoes set dm_romaneio = 'S' where oid_nota_fiscal =	'" +
            res.getString ("oid_nota_fiscal") + "'";
        i = executasql.executarUpdate (sql);

      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir!");
      excecoes.setMetodo ("inclui_Romaneio");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public Solicitacao_CompraED getByRecord_Romaneio (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    FormataDataBean DataFormatada = new FormataDataBean ();

    try {
      sql = "SELECT * FROM romaneios_notas_fiscais " +
          " WHERE romaneios_notas_fiscais.oid_romaneio_nota_fiscal = '" + ed.getOid_romaneio_nota_fiscal () + "'";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      //popula
      while (res.next ()) {
        edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_nota_fiscal (res.getString ("oid_nota_fiscal"));
        edVolta.setOid_estoque (res.getLong ("oid_estoque"));

        edVolta.setOid_romaneio_nota_fiscal (res.getString ("oid_romaneio_nota_fiscal"));
        //edVolta.setOid_localizacao(res.getString("oid_localizacao"));
        edVolta.setVl_quantidade (res.getDouble ("vl_quantidade"));
        edVolta.setDt_stamp (res.getString ("dt_stamp"));
        DataFormatada.setDT_FormataData (edVolta.getDt_stamp ());
        edVolta.setDt_stamp (DataFormatada.getDT_FormataData ());
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar Romaneio");
      excecoes.setMetodo ("getByRecord_Romaneio");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public void inclui_Local_Qtde (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    FormataDataBean DataFormatada = new FormataDataBean ();

    try {
      sql = "update romaneios_notas_fiscais set " +
          " vl_quantidade = " + ed.getVl_quantidade () +
          ", dt_stamp = '" + Data.getDataDMY () +
          "' WHERE romaneios_notas_fiscais.oid_romaneio_nota_fiscal = '" + ed.getOid_romaneio_nota_fiscal () + "'";

      int i = this.executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao Alterar Romaneio");
      excecoes.setMetodo ("inclui_Local_Qtde()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void imprime_Romaneio (Nota_Fiscal_CompraED ed , HttpServletResponse response) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    Solicitacao_CompraED Soled = new Solicitacao_CompraED ();
    FormataDataBean DataFormatada = new FormataDataBean ();

    try {
      sql = "SELECT * FROM romaneios_notas_fiscais " +
          " WHERE romaneios_notas_fiscais.oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
      sql += " order by romaneios_notas_fiscais.oid_estoque";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      //popula
      while (res.next ()) {
        Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_nota_fiscal (res.getString ("oid_nota_fiscal"));
        sql = "select nr_nota_fiscal from notas_fiscais_transacoes" +
            " where oid_nota_fiscal = '" + edVolta.getOid_nota_fiscal () + "'";
        ResultSet rs = null;
        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          edVolta.setNr_nota_fiscal (rs.getString (1));
          Soled.setNr_nota_fiscal (rs.getString (1));
        }
        edVolta.setOid_estoque (res.getLong ("oid_estoque"));
        sql = "select cd_estoque, nm_estoque, dm_tipo_produto from estoques" +
            " where oid_estoque = " + edVolta.getOid_estoque ();
        rs = null;
        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          edVolta.setCd_estoque (rs.getString (1));
          edVolta.setNm_estoque (rs.getString (2));
          if (rs.getString (3) != null && rs.getString (3).equals ("E")) {
            edVolta.setDm_status_item ("Estoque");
          }
          else {
            edVolta.setDm_status_item ("Diverso");
          }
        }
        edVolta.setOid_romaneio_nota_fiscal (res.getString ("oid_romaneio_nota_fiscal"));
        if (res.getString ("oid_localizacao") != null) {
          edVolta.setOid_localizacao (res.getString ("oid_localizacao"));
        }
        else {
          edVolta.setOid_localizacao ("");
        }
        edVolta.setVl_quantidade (res.getDouble ("vl_quantidade"));
        edVolta.setDt_stamp (res.getString ("dt_stamp"));
        DataFormatada.setDT_FormataData (edVolta.getDt_stamp ());
        edVolta.setDt_stamp (DataFormatada.getDT_FormataData ());
        list.add (edVolta);
      }

      Solicitacao_CompraRL rl = new Solicitacao_CompraRL ();
      rl.relRomaneio (list , response , Soled);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao imprimir Romaneio");
      excecoes.setMetodo ("imprime_Romaneio()");
      excecoes.setExc (exc);
      throw excecoes;
    }

  }

  public boolean verifica_Pedidos (Nota_Fiscal_CompraED ed) throws Excecoes {

    String sql = null;
    boolean ok = false;

    try {
    	if(parametro_FixoED.isExigePedidoNotaCompra()){
    		sql = "SELECT * from pedidos_compras_notas_fiscais " +
	            " WHERE oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
	        ResultSet res = null;
	        res = this.executasql.executarConsulta (sql);
	        if (res.next ()) {
	          ok = true;
	        }
    	} else {
    		ok = true;
    	}
    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar Pedidos");
      excecoes.setMetodo ("lista_Pedidos");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ok;
  }

  public void inclui_Parcelamento (Nota_Fiscal_CompraED ed) throws Excecoes {

      // .println("inclui_ParcelamentoARRUMADO");

	  String sql = null;
    long valOid = 0;
    String chave = null;
    String SHORT_DATE = "dd/MM/yyyy";
    DateFormatter dateFormatter = new DateFormatter ();
    long i = 1;
    long oid = 0 , caso1 = 0 , caso2 = 0;
    double valor = 0;
    double VL_Parcela1 = 0 , VL_Parcela = 0;
    String parc = "" , aux = "";
    double resto = 0;
    long arred = 0;
    int u = 0;
    double valorParc = 0;

    try {
      valor = ed.getVl_liquido_nota_fiscal () / ed.getNr_parcelas ();
      VL_Parcela1 = Valor.getValorArredondado (valor);
      VL_Parcela = Valor.getValorArredondado (valor);

      while (i <= ed.getNr_parcelas ()) {
        ResultSet rs = executasql.executarConsulta ("select max(oid_parcelamento) as result from parcelamentos_financeiros ");
        //pega proximo valor da chave
        while (rs.next ()) {
          valOid = rs.getLong ("result");
          oid = ++valOid;
        }
        Calendar cal = Data.stringToCalendar (ed.getDt_emissao () , "dd/MM/yyyy");
        int diasAtuais = cal.get (Calendar.DAY_OF_MONTH);
        cal.set (Calendar.DAY_OF_MONTH , diasAtuais + 30);
        ed.setDt_emissao (dateFormatter.calendarToString (cal , SHORT_DATE));

        sql = "Insert into parcelamentos_financeiros (oid_parcelamento, oid_nota_fiscal, vl_parcela, dt_stamp, usuario_stamp, dm_stamp, nr_parcelamento) values ";
        sql += "(" + oid + ",'" + ed.getOid_nota_fiscal () + "',";

        if (i == ed.getNr_parcelas ()) {
          VL_Parcela1 = Valor.getValorArredondado (ed.getVl_liquido_nota_fiscal () - valorParc);
          sql += VL_Parcela1;

        }
        else {
          sql += VL_Parcela;
        }

        sql += ",'" + Data.getDataDMY () + "',' ',' '," + (i) + ")";

        u = executasql.executarUpdate (sql);
        valorParc += VL_Parcela;
        i++;
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui lancamentos");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void inclui_Parcelamento (String oid_Nota_Fiscal, long nr_parcelas, double vl_liquido_nota_fiscal ) throws Excecoes {

		String sql = null;
	    long valOid = 0;
	    DateFormatter dateFormatter = new DateFormatter ();
	    long i = 1;
	    double vl_parcela = 0;
	    double tt_parcela = 0;
	    int dias_vencimento=10;
	    String dt_pagamento="";
	    String dm_vencimento_conta="";
	    String dt_pedido_compra=Data.getDataDMY();
	    String dt_vencimento_minimo=Data.getDataDMY();
	    FormataDataBean dataFormatada = new FormataDataBean();

//	    System.out.println(" inclui_Parcelamento vl_liquido_nota_fiscal==" +  vl_liquido_nota_fiscal);

	    try {

	      if (vl_liquido_nota_fiscal>0 && nr_parcelas>0){
			      sql =" SELECT Pedidos_Compras.DT_Pedido_Compra " +
				   	   " FROM   Pedidos_Compras, Notas_Fiscais_Transacoes " +
				   	   " WHERE  Notas_Fiscais_Transacoes.oid_pedido_compra = Pedidos_Compras.oid_pedido_compra " +
					   " AND    Notas_Fiscais_Transacoes.oid_Nota_Fiscal='" + oid_Nota_Fiscal + "'";
				  ResultSet resNFC = executasql.executarConsulta (sql);
				  if (resNFC.next ()) {
				 	    dataFormatada.setDT_FormataData (resNFC.getString("DT_Pedido_Compra"));
				 	    dt_pedido_compra=dataFormatada.getDT_FormataData ();
				  }

			      sql =" SELECT Contas.dm_vencimento " +
			      	   " FROM   Contas, Notas_Fiscais_Transacoes " +
			      	   " WHERE 	Notas_Fiscais_Transacoes.oid_Conta = Contas.oid_Conta " +
			 	   	   " AND    Contas.dm_vencimento IS NOT NULL" +
				       " AND    Notas_Fiscais_Transacoes.oid_Nota_Fiscal='" + oid_Nota_Fiscal + "'";
			      ResultSet resCta = executasql.executarConsulta (sql);
			      if (resCta.next ()) {
			    	     if (resCta.getString("dm_vencimento")!= null && !resCta.getString("dm_vencimento").equals("null") && !resCta.getString("dm_vencimento").equals("")){
				    	     dm_vencimento_conta=resCta.getString("dm_vencimento");
				    	     dias_vencimento=resCta.getInt("dm_vencimento");
				    	     dt_vencimento_minimo=Data.manipulaDias(dt_pedido_compra, dias_vencimento);
			    	     }
			      }

			      dt_pagamento=Data.manipulaDias(dt_pedido_compra, dias_vencimento);
			      while (i <= nr_parcelas) {

				      sql =" SELECT nr_parcelamento, vl_parcela, dt_pagamento  " +
				      	   " FROM   parcelamentos_pedidos, notas_fiscais_transacoes " +
				      	   " WHERE 	parcelamentos_pedidos.oid_pedido_compra =  notas_fiscais_transacoes.oid_pedido_compra  " +
				      	   " AND   	notas_fiscais_transacoes.oid_Nota_Fiscal='" + oid_Nota_Fiscal + "'" +
				      	   " AND   	parcelamentos_pedidos.nr_parcelamento='" + i + "'";

//				      System.out.println(sql);

				      ResultSet resPar = executasql.executarConsulta (sql);
				      if (resPar.next ()) {
				    	  System.out.println("dt_pagamento tab=" + resPar.getString("dt_pagamento"));
				    	  System.out.println("dt_pagamento calc=" + dt_pagamento);


				    	  dataFormatada.setDT_FormataData (resPar.getString("dt_pagamento"));
				    	  dt_pagamento=dataFormatada.getDT_FormataData ();
				      }


				      if (Data.comparaData(dt_vencimento_minimo, dt_pagamento)){
				    	  dt_pagamento=dt_vencimento_minimo;
				      }

				      ResultSet rs = executasql.executarConsulta ("select max(oid_parcelamento) as result from parcelamentos_financeiros ");
				      if (rs.next ()) {
				          valOid = rs.getLong ("result") + 1;
				      }

				        vl_parcela = vl_liquido_nota_fiscal;

				        if (nr_parcelas>1){
				            vl_parcela = Valor.getValorArredondado (vl_liquido_nota_fiscal / nr_parcelas );
				            if (i == nr_parcelas) {
				            	vl_parcela = Valor.getValorArredondado (vl_liquido_nota_fiscal - tt_parcela);
				            }
				        }

				        sql = "Insert into parcelamentos_financeiros (oid_parcelamento, oid_nota_fiscal, vl_parcela, dt_stamp, usuario_stamp, dm_stamp, nr_parcelamento) values ";
				        sql += "(" + valOid + ",'" + oid_Nota_Fiscal + "'," + vl_parcela + ", '" + Data.getDataDMY () + "',' ',' '," + (i) + ")";
				        executasql.executarUpdate (sql);

				        if (!"".equals(dt_pagamento)){
				        	sql =" UPDATE parcelamentos_financeiros SET dm_vencimento_conta='" + dm_vencimento_conta + "', dt_vencimento_minimo='" + dt_vencimento_minimo + "', dt_pagamento='" + dt_pagamento + "' WHERE oid_parcelamento = " + valOid;
				            executasql.executarUpdate (sql);
				   	        dt_pagamento=Data.manipulaDias(dt_pagamento, 30);
				        }

				        tt_parcela += vl_parcela;
				        i++;
			     }
	      }
	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void geraIR(Nota_Fiscal_CompraED ed)throws Excecoes{

      String sql = null;
      String chave = null;
      String nr_Parcela = null;
      long valOid = 0;
      long oid = 0;

      Parcela_CompromissoED Parcela_CompromissoED = new Parcela_CompromissoED();
      Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

      CompromissoED compromissoED = new CompromissoED();
      CompromissoED compromissoVoltaED = new CompromissoED();
      CompromissoBD compromissoBD = new CompromissoBD(this.executasql);
      FormataDataBean dataFormatada = new FormataDataBean();

      try{

//      String data = ed.getDt_emissao();
//          Calendar cal = Data.stringToCalendar(data,"dd/MM/yyyy");
//    	 seta dia 10 do mes seguinte
//          if(Calendar.MONTH<12){
//        	  int soma = cal.get(Calendar.MONTH);
//              cal.set(Calendar.MONTH, soma+1);
//          } else cal.set(Calendar.MONTH, 1);
//          cal.set(Calendar.DAY_OF_MONTH, 20);
//          if(cal.get(Calendar.DAY_OF_WEEK)==1)
//        	  cal.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+1);
//          if(cal.get(Calendar.DAY_OF_WEEK)==7)
//              cal.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+2);
//          Date d = new Date();
//          d = cal.getTime();
//          SimpleDateFormat formatter_date = new SimpleDateFormat("dd/MM/yyyy");
//          compromissoED.setDt_Vencimento(formatter_date.format(d));

          String data = ed.getDt_emissao();

          if (!"30".equals(data.substring(0, 2)) && !"31".equals(data.substring(0, 2))){
              data = "20" + Data.getSomaMesesData(data, 1).substring(2 , 10);
          }else{
        	  data = "20"+data.substring(2,10);
              data = "20" + Data.getSomaMesesData(data, 1).substring(2 , 10);
          }

          compromissoED.setDt_Vencimento(data);

//        parcelamento do Imposto
          ResultSet rs1 = executasql.executarConsulta(
                  "select max(oid_parcelamento) as result from parcelamentos_financeiros ");
          //pega proximo valor da chave
          while (rs1.next()){
            valOid = rs1.getLong("result");
            oid = ++valOid;
          }
          //ed.setDt_emissao(compromissoED.getDt_Vencimento());

          sql = "Insert into parcelamentos_financeiros (oid_parcelamento, oid_nota_fiscal, vl_parcela, dt_stamp, usuario_stamp, dm_stamp, nr_parcelamento, dt_pagamento) values ";
          sql += "("+oid+",'"+ed.getOid_nota_fiscal()+"',";
          sql += ed.getVl_irrf();
          sql += ",'"+Data.getDataDMY()+"',' ',' ',"+0+",'"+compromissoED.getDt_Vencimento() +"')";
          int u = executasql.executarUpdate(sql);
          //FIM do parcelamento

          compromissoED.setNr_Parcela(new Integer(1));

          compromissoED.setVl_Compromisso(new Double(ed.getVl_irrf()));
          compromissoED.setVl_Saldo(new Double(ed.getVl_irrf()));

          compromissoED.setOid_Pessoa(Parametro_FixoED.getInstancia().getOID_Fornecedor_IRRF());
          compromissoED.setDt_Entrada(ed.getDt_entrada());
          compromissoED.setDt_Emissao(ed.getDt_emissao());
          compromissoED.setNr_Documento(String.valueOf(ed.getNr_nota_fiscal()));

          // deve ser unidade contabil
          compromissoED.setOid_Unidade(new Long(ed.getOID_Unidade_Fiscal()));

          //compromissoED.setOid_Unidade_Emissora(new Long(ed.getOID_Unidade_Fiscal()));

          compromissoED.setDM_Tipo_Pagamento("1");
          compromissoED.setOid_Centro_Custo(new Integer(new Long(ed.getOid_centro_custo()).intValue()));
          if (ed.getDm_tipo_nota_fiscal() != null && (ed.getDm_tipo_nota_fiscal().equals("E") || ed.getDm_tipo_nota_fiscal().equals("0"))){
              String busca = "select oid_centro_custo from solicitacoes_compras,pedidos_compras,pedidos_compras_notas_fiscais where "+
  						" solicitacoes_compras.oid_solicitacao_compra = pedidos_compras.oid_solicitacao_compra "+
  						" and pedidos_compras.oid_pedido_compra = pedidos_compras_notas_fiscais.oid_pedido_compra "+
  						" and pedidos_compras_notas_fiscais.oid_nota_fiscal = '"+ed.getOid_nota_fiscal()+"'";
              ResultSet cc = executasql.executarConsulta(busca);
              while (cc.next()) compromissoED.setOid_Centro_Custo(new Integer(cc.getInt("oid_centro_custo")));
          }

          double bruto = ed.getVl_liquido_nota_fiscal();// + ed.getVl_descontos();

	      compromissoED.setOid_Conta(new Integer(new Long(parametro_FixoED.getOID_Conta_IRRF()).intValue()));

          compromissoED.setOid_Conta_Credito(new Integer(parametro_FixoED.getOID_Conta_IRRF()));
          compromissoED.setOid_Natureza_Operacao(new Integer(String.valueOf(ed.getOid_natureza_operacao())));
          Integer OID_Tipo_Documento = new Integer(11);
          compromissoED.setOid_Tipo_Documento(OID_Tipo_Documento);
          compromissoED.setTx_Observacao(ed.getNM_Pessoa() + " NF nr. "+ed.getNr_nota_fiscal());

          compromissoVoltaED = compromissoBD.inclui(compromissoED);
          compromissoED.setOid_Compromisso(compromissoVoltaED.getOid_Compromisso());

          ResultSet rs = executasql.executarConsulta("select OID_Parcela_Compromisso as result from Parcelas_Compromissos order by OID_Parcela_Compromisso desc limit 1 ");
  	      while (rs != null && rs.next()){
            valOid = rs.getLong("result");
            Parcela_CompromissoED.setOID_Parcela_Compromisso(String.valueOf(++valOid));
          }

          Parcela_CompromissoED.setDT_Emissao(ed.getDt_emissao());
          Parcela_CompromissoED.setHR_Parcela_Compromisso(ed.getHr_stamp());
          Parcela_CompromissoED.setDT_Parcela_Compromisso(ed.getDt_emissao());
          Parcela_CompromissoED.setOID_Compromisso(compromissoED.getOid_Compromisso());
          Parcela_CompromissoED.setOID_Parcelamento(String.valueOf(oid));
          Parcela_CompromissoED.setDt_stamp(ed.getDt_stamp());
          Parcela_CompromissoED.setUsuario_Stamp(ed.getUsuario_Stamp());
          Parcela_CompromissoED.setDm_Stamp(ed.getDm_Stamp());

          sql = " insert into Parcelas_Compromissos (OID_Parcela_Compromisso, OID_Compromisso, OID_Parcelamento, DT_Parcela_Compromisso, HR_Parcela_Compromisso ) values ";
          sql += "(" + Parcela_CompromissoED.getOID_Parcela_Compromisso() + ",'" + Parcela_CompromissoED.getOID_Compromisso() + "'," + Parcela_CompromissoED.getOID_Parcelamento() + ",'"  + Parcela_CompromissoED.getDT_Parcela_Compromisso() + "','" + Parcela_CompromissoED.getHR_Parcela_Compromisso() + "')";

          int i = executasql.executarUpdate(sql);

      }
      catch(Exception exc){
          exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao gerar compromisso IR");
        excecoes.setMetodo("geraMovimento(Nota_Fiscal_TransacoesED ed)");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

  public void geraINSS(Nota_Fiscal_CompraED ed)throws Excecoes{

      String sql = null;
      String chave = null;
      String nr_Parcela = null;
      long valOid = 0;
      long oid = 0;

      Parcela_CompromissoED Parcela_CompromissoED = new Parcela_CompromissoED();
      Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

      CompromissoED compromissoED = new CompromissoED();
      CompromissoED compromissoVoltaED = new CompromissoED();
      CompromissoBD compromissoBD = new CompromissoBD(this.executasql);
      FormataDataBean dataFormatada = new FormataDataBean();

      try{

//          String data = ed.getDt_emissao();
//          Calendar cal = Data.stringToCalendar(data,"dd/MM/yyyy");
//          // seta dia 10 do mes seguinte
//          if(Calendar.MONTH<12){
//        	  int soma = cal.get(Calendar.MONTH);
//              cal.set(Calendar.MONTH, soma+1);
//          } else cal.set(Calendar.MONTH, 1);
//          cal.set(Calendar.DAY_OF_MONTH, 20);
//
//          if(cal.get(Calendar.DAY_OF_WEEK)==1){
//        	  cal.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+1);
//          }
//          if(cal.get(Calendar.DAY_OF_WEEK)==7){
//        	  cal.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+2);
//          }
//
//          Date d = new Date();
//          d = cal.getTime();
//
//          SimpleDateFormat formatter_date = new SimpleDateFormat("dd/MM/yyyy");
//          compromissoED.setDt_Vencimento(formatter_date.format(d));

          String data = ed.getDt_emissao();

          if (!"30".equals(data.substring(0, 2)) && !"31".equals(data.substring(0, 2))){
              data = "20" + Data.getSomaMesesData(data, 1).substring(2 , 10);
          }else{
        	  data = "20"+data.substring(2,10);
              data = "20" + Data.getSomaMesesData(data, 1).substring(2 , 10);
          }

          compromissoED.setDt_Vencimento(data);

//        parcelamento do Imposto
          ResultSet rs1 = executasql.executarConsulta(
                  "select oid_parcelamento as result from parcelamentos_financeiros order by oid_parcelamento desc limit 1 ");
          //pega proximo valor da chave
          while (rs1.next()){
            valOid = rs1.getLong("result");
            oid = ++valOid;
          }
          //ed.setDt_emissao(compromissoED.getDt_Vencimento());

          sql = "Insert into parcelamentos_financeiros (oid_parcelamento, oid_nota_fiscal, vl_parcela, dt_stamp, usuario_stamp, dm_stamp, nr_parcelamento, dt_pagamento) values ";
          sql += "("+oid+",'"+ed.getOid_nota_fiscal()+"',";
          sql += ed.getVl_inss();
          sql += ",'"+Data.getDataDMY()+"',' ',' ',"+0+",'"+compromissoED.getDt_Vencimento()+"')";
          int u = executasql.executarUpdate(sql);
          //FIM do parcelamento

          compromissoED.setNr_Parcela(new Integer(1));

          compromissoED.setVl_Compromisso(new Double(ed.getVl_inss()));
          compromissoED.setVl_Saldo(new Double(ed.getVl_inss()));

          compromissoED.setOid_Pessoa(Parametro_FixoED.getInstancia().getOID_Fornecedor_INSS());
          compromissoED.setDt_Entrada(ed.getDt_entrada());
          compromissoED.setDt_Emissao(ed.getDt_emissao());
          compromissoED.setNr_Documento(String.valueOf(ed.getNr_nota_fiscal()));

          // deve ser unidade contabil
          compromissoED.setOid_Unidade(new Long(ed.getOID_Unidade_Fiscal()));

          //compromissoED.setOid_Unidade_Emissora(new Long(ed.getOID_Unidade_Fiscal()));

          compromissoED.setDM_Tipo_Pagamento("1");
          compromissoED.setOid_Centro_Custo(new Integer(new Long(ed.getOid_centro_custo()).intValue()));
          if (ed.getDm_tipo_nota_fiscal() != null && (ed.getDm_tipo_nota_fiscal().equals("E") || ed.getDm_tipo_nota_fiscal().equals("0"))){
              String busca = "select oid_centro_custo from solicitacoes_compras,pedidos_compras,pedidos_compras_notas_fiscais where "+
  						" solicitacoes_compras.oid_solicitacao_compra = pedidos_compras.oid_solicitacao_compra "+
  						" and pedidos_compras.oid_pedido_compra = pedidos_compras_notas_fiscais.oid_pedido_compra "+
  						" and pedidos_compras_notas_fiscais.oid_nota_fiscal = '"+ed.getOid_nota_fiscal()+"'";
              ResultSet cc = executasql.executarConsulta(busca);
              while (cc.next()) compromissoED.setOid_Centro_Custo(new Integer(cc.getInt("oid_centro_custo")));
          }

          double bruto = ed.getVl_liquido_nota_fiscal();// + ed.getVl_descontos();

	      compromissoED.setOid_Conta(new Integer(new Long(parametro_FixoED.getOID_Conta_INSS()).intValue()));

          compromissoED.setOid_Conta_Credito(new Integer(parametro_FixoED.getOID_Conta_INSS()));
          compromissoED.setOid_Natureza_Operacao(new Integer(String.valueOf(ed.getOid_natureza_operacao())));
          Integer OID_Tipo_Documento = new Integer(11);
          compromissoED.setOid_Tipo_Documento(OID_Tipo_Documento);
          compromissoED.setTx_Observacao(ed.getNM_Pessoa() + " NF nr. "+ed.getNr_nota_fiscal());

          compromissoVoltaED = compromissoBD.inclui(compromissoED);
          compromissoED.setOid_Compromisso(compromissoVoltaED.getOid_Compromisso());

          ResultSet rs = executasql.executarConsulta("select OID_Parcela_Compromisso as result from Parcelas_Compromissos order by OID_Parcela_Compromisso desc limit 1 ");
  	      while (rs != null && rs.next()){
            valOid = rs.getLong("result");
            Parcela_CompromissoED.setOID_Parcela_Compromisso(String.valueOf(++valOid));
          }

          Parcela_CompromissoED.setDT_Emissao(ed.getDt_emissao());
          Parcela_CompromissoED.setHR_Parcela_Compromisso(ed.getHr_stamp());
          Parcela_CompromissoED.setDT_Parcela_Compromisso(ed.getDt_emissao());
          Parcela_CompromissoED.setOID_Compromisso(compromissoED.getOid_Compromisso());
          Parcela_CompromissoED.setOID_Parcelamento(String.valueOf(oid));
          Parcela_CompromissoED.setDt_stamp(ed.getDt_stamp());
          Parcela_CompromissoED.setUsuario_Stamp(ed.getUsuario_Stamp());
          Parcela_CompromissoED.setDm_Stamp(ed.getDm_Stamp());

          sql = " insert into Parcelas_Compromissos (OID_Parcela_Compromisso, OID_Compromisso, OID_Parcelamento, DT_Parcela_Compromisso, HR_Parcela_Compromisso ) values ";
          sql += "(" + Parcela_CompromissoED.getOID_Parcela_Compromisso() + ",'" + Parcela_CompromissoED.getOID_Compromisso() + "'," + Parcela_CompromissoED.getOID_Parcelamento() + ",'"  + Parcela_CompromissoED.getDT_Parcela_Compromisso() + "','" + Parcela_CompromissoED.getHR_Parcela_Compromisso() + "')";

          int i = executasql.executarUpdate(sql);

      }
      catch(Exception exc){
          exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao gerar compromisso INSS");
        excecoes.setMetodo("geraMovimento(Nota_Fiscal_TransacoesED ed)");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

  public void geraPIS_COFINS_CSLL(Nota_Fiscal_CompraED ed, double valor, String data)throws Excecoes{

      String sql = null;
      String chave = null;
      String nr_Parcela = null;
      long valOid = 0;
      long oid = 0;

      Parcela_CompromissoED Parcela_CompromissoED = new Parcela_CompromissoED();
      Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

      CompromissoED compromissoED = new CompromissoED();
      CompromissoED compromissoVoltaED = new CompromissoED();
      CompromissoBD compromissoBD = new CompromissoBD(this.executasql);
      FormataDataBean dataFormatada = new FormataDataBean();

      try{

          String datav = data;
    	  int dia = new Integer(data.substring(0,2)).intValue();

    	  if (dia >= 1 && dia <= 15){
              datav = "30" + data.substring(2,10);
    	  }else{
              if (!"30".equals(data.substring(0, 2)) && !"31".equals(data.substring(0, 2))){
                  data = "15" + Data.getSomaMesesData(data, 1).substring(2 , 10);
              }else{
            	  data = "15"+data.substring(2,10);
                  data = "15" + Data.getSomaMesesData(data, 1).substring(2 , 10);
              }
    		  datav = data;
    	  }

    	  if ("30/02/2012".equals(datav)){
    		  datav = "01/03/2012";
    	  }
          compromissoED.setDt_Vencimento(datav);

//        parcelamento do Imposto
          ResultSet rs1 = executasql.executarConsulta(
        		  "select max(oid_parcelamento) as result from parcelamentos_financeiros ");
          //pega proximo valor da chave
          while (rs1.next()){
            valOid = rs1.getLong("result");
            oid = ++valOid;
          }
          //ed.setDt_emissao(compromissoED.getDt_Vencimento());

          sql = "Insert into parcelamentos_financeiros (oid_parcelamento, oid_nota_fiscal, vl_parcela, dt_stamp, usuario_stamp, dm_stamp, nr_parcelamento, dt_pagamento) values ";
          sql += "("+oid+",'"+ed.getOid_nota_fiscal()+"',";
          sql += valor;
          sql += ",'"+Data.getDataDMY()+"',' ',' ',"+0+",'"+compromissoED.getDt_Vencimento()+"')";
          int u = executasql.executarUpdate(sql);
          //FIM do parcelamento

          compromissoED.setNr_Parcela(new Integer(1));

          compromissoED.setVl_Compromisso(new Double(valor));
          compromissoED.setVl_Saldo(new Double(valor));

          compromissoED.setOid_Pessoa(Parametro_FixoED.getInstancia().getOID_Fornecedor_PIS());
          compromissoED.setDt_Entrada(ed.getDt_entrada());
          compromissoED.setDt_Emissao(ed.getDt_emissao());
          compromissoED.setNr_Documento(String.valueOf(ed.getNr_nota_fiscal()));

          // deve ser unidade contabil
          compromissoED.setOid_Unidade(new Long(ed.getOID_Unidade_Fiscal()));

          //compromissoED.setOid_Unidade_Emissora(new Long(ed.getOID_Unidade_Fiscal()));

          compromissoED.setDM_Tipo_Pagamento("1");
          compromissoED.setOid_Centro_Custo(new Integer(new Long(ed.getOid_centro_custo()).intValue()));
          if (ed.getDm_tipo_nota_fiscal() != null && (ed.getDm_tipo_nota_fiscal().equals("E") || ed.getDm_tipo_nota_fiscal().equals("0"))){
              String busca = "select oid_centro_custo from solicitacoes_compras,pedidos_compras,pedidos_compras_notas_fiscais where "+
  						" solicitacoes_compras.oid_solicitacao_compra = pedidos_compras.oid_solicitacao_compra "+
  						" and pedidos_compras.oid_pedido_compra = pedidos_compras_notas_fiscais.oid_pedido_compra "+
  						" and pedidos_compras_notas_fiscais.oid_nota_fiscal = '"+ed.getOid_nota_fiscal()+"'";
              ResultSet cc = executasql.executarConsulta(busca);
              while (cc.next()) compromissoED.setOid_Centro_Custo(new Integer(cc.getInt("oid_centro_custo")));
          }

          double bruto = ed.getVl_liquido_nota_fiscal();// + ed.getVl_descontos();

	      compromissoED.setOid_Conta(new Integer(new Long(parametro_FixoED.getOID_Conta_PIS_COFINS_CSLL()).intValue()));

          compromissoED.setOid_Conta_Credito(new Integer(parametro_FixoED.getOID_Conta_PIS_COFINS_CSLL()));
          compromissoED.setOid_Natureza_Operacao(new Integer(String.valueOf(ed.getOid_natureza_operacao())));
          Integer OID_Tipo_Documento = new Integer(11);
          compromissoED.setOid_Tipo_Documento(OID_Tipo_Documento);
          compromissoED.setTx_Observacao(ed.getNM_Pessoa() + " NF nr. "+ed.getNr_nota_fiscal());

          compromissoVoltaED = compromissoBD.inclui(compromissoED);
          compromissoED.setOid_Compromisso(compromissoVoltaED.getOid_Compromisso());

          ResultSet rs = executasql.executarConsulta("select OID_Parcela_Compromisso as result from Parcelas_Compromissos order by OID_Parcela_Compromisso desc limit 1 ");
  	      while (rs != null && rs.next()){
            valOid = rs.getLong("result");
            Parcela_CompromissoED.setOID_Parcela_Compromisso(String.valueOf(++valOid));
          }

          Parcela_CompromissoED.setDT_Emissao(ed.getDt_emissao());
          Parcela_CompromissoED.setHR_Parcela_Compromisso(ed.getHr_stamp());
          Parcela_CompromissoED.setDT_Parcela_Compromisso(ed.getDt_emissao());
          Parcela_CompromissoED.setOID_Compromisso(compromissoED.getOid_Compromisso());
          Parcela_CompromissoED.setOID_Parcelamento(String.valueOf(oid));
          Parcela_CompromissoED.setDt_stamp(ed.getDt_stamp());
          Parcela_CompromissoED.setUsuario_Stamp(ed.getUsuario_Stamp());
          Parcela_CompromissoED.setDm_Stamp(ed.getDm_Stamp());

          sql = " insert into Parcelas_Compromissos (OID_Parcela_Compromisso, OID_Compromisso, OID_Parcelamento, DT_Parcela_Compromisso, HR_Parcela_Compromisso ) values ";
          sql += "(" + Parcela_CompromissoED.getOID_Parcela_Compromisso() + ",'" + Parcela_CompromissoED.getOID_Compromisso() + "'," + Parcela_CompromissoED.getOID_Parcelamento() + ",'"  + Parcela_CompromissoED.getDT_Parcela_Compromisso() + "','" + Parcela_CompromissoED.getHR_Parcela_Compromisso() + "')";

          int i = executasql.executarUpdate(sql);

      }
      catch(Exception exc){
          exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao gerar compromisso PIS/COFINS/CSLL");
        excecoes.setMetodo("geraMovimento(Nota_Fiscal_TransacoesED ed)");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

  public void geraISSQN(Nota_Fiscal_CompraED ed)throws Excecoes{

      String sql = null;
      String chave = null;
      String nr_Parcela = null;
      long valOid = 0;
      long oid = 0;

      Parcela_CompromissoED Parcela_CompromissoED = new Parcela_CompromissoED();
      Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

      CompromissoED compromissoED = new CompromissoED();
      CompromissoED compromissoVoltaED = new CompromissoED();
      CompromissoBD compromissoBD = new CompromissoBD(this.executasql);
      FormataDataBean dataFormatada = new FormataDataBean();

      try{

//          String data = ed.getDt_emissao();
//          Calendar cal = Data.stringToCalendar(data,"dd/MM/yyyy");
//          // seta dia 10 do mes seguinte
//          if(Calendar.MONTH<12){
//        	  int soma = cal.get(Calendar.MONTH);
//              cal.set(Calendar.MONTH, soma+1);
//          } else cal.set(Calendar.MONTH, 1);
//          cal.set(Calendar.DAY_OF_MONTH, 10);
//
//          if(cal.get(Calendar.DAY_OF_WEEK)==1){
//        	  cal.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+1);
//          }
//          if(cal.get(Calendar.DAY_OF_WEEK)==7){
//        	  cal.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+2);
//          }
//          Date d = new Date();
//          d = cal.getTime();
//          SimpleDateFormat formatter_date = new SimpleDateFormat("dd/MM/yyyy");
//          compromissoED.setDt_Vencimento(formatter_date.format(d));

          String data = ed.getDt_emissao();

          if (!"30".equals(data.substring(0, 2)) && !"31".equals(data.substring(0, 2))){
              data = "10" + Data.getSomaMesesData(data, 1).substring(2 , 10);
          }else{
        	  data = "10"+data.substring(2,10);
              data = "10" + Data.getSomaMesesData(data, 1).substring(2 , 10);
          }

          compromissoED.setDt_Vencimento(data);

//        parcelamento do Imposto
          ResultSet rs1 = executasql.executarConsulta(
        		  "select max(oid_parcelamento) as result from parcelamentos_financeiros ");
          //pega proximo valor da chave
          while (rs1.next()){
            valOid = rs1.getLong("result");
            oid = ++valOid;
          }
          //ed.setDt_emissao(compromissoED.getDt_Vencimento());

          sql = "Insert into parcelamentos_financeiros (oid_parcelamento, oid_nota_fiscal, vl_parcela, dt_stamp, usuario_stamp, dm_stamp, nr_parcelamento, dt_pagamento) values ";
          sql += "("+oid+",'"+ed.getOid_nota_fiscal()+"',";
          sql += ed.getVl_isqn();
          sql += ",'"+Data.getDataDMY()+"',' ',' ',"+0+",'"+compromissoED.getDt_Vencimento()+"')";
          int u = executasql.executarUpdate(sql);
          //FIM do parcelamento

          compromissoED.setNr_Parcela(new Integer(1));

          compromissoED.setVl_Compromisso(new Double(ed.getVl_isqn()));
          compromissoED.setVl_Saldo(new Double(ed.getVl_isqn()));

          compromissoED.setOid_Pessoa(Parametro_FixoED.getInstancia().getOID_Fornecedor_ISSQN());
          compromissoED.setDt_Entrada(ed.getDt_entrada());
          compromissoED.setDt_Emissao(ed.getDt_emissao());
          compromissoED.setNr_Documento(String.valueOf(ed.getNr_nota_fiscal()));

          compromissoED.setOid_Unidade(new Long(ed.getOID_Unidade_Fiscal()));
          compromissoED.setOid_Pessoa(Parametro_FixoED.getInstancia().getOID_Fornecedor_ISSQN());

          //compromissoED.setOid_Unidade_Emissora(new Long(ed.getOID_Unidade_Fiscal()));

          compromissoED.setDM_Tipo_Pagamento("1");
          compromissoED.setOid_Centro_Custo(new Integer(new Long(ed.getOid_centro_custo()).intValue()));
          if (ed.getDm_tipo_nota_fiscal() != null && (ed.getDm_tipo_nota_fiscal().equals("E") || ed.getDm_tipo_nota_fiscal().equals("0"))){
              String busca = "select oid_centro_custo from solicitacoes_compras where "+
  						" solicitacoes_compras.oid_solicitacao_compra = pedidos_compras.oid_solicitacao_compra "+
  						" and pedidos_compras.oid_pedido_compra = pedidos_compras_notas_fiscais.oid_pedido_compra "+
  						" and pedidos_compras_notas_fiscais.oid_nota_fiscal = '"+ed.getOid_nota_fiscal()+"'";
              ResultSet cc = executasql.executarConsulta(busca);
              while (cc.next()) compromissoED.setOid_Centro_Custo(new Integer(cc.getInt("oid_centro_custo")));
          }

          double bruto = ed.getVl_liquido_nota_fiscal();// + ed.getVl_descontos();

	      compromissoED.setOid_Conta(new Integer(new Long(parametro_FixoED.getOID_Conta_ISSQN()).intValue()));

          compromissoED.setOid_Conta_Credito(new Integer(parametro_FixoED.getOID_Conta_ISSQN()));
          compromissoED.setOid_Natureza_Operacao(new Integer(String.valueOf(ed.getOid_natureza_operacao())));
          Integer OID_Tipo_Documento = new Integer(11);
          compromissoED.setOid_Tipo_Documento(OID_Tipo_Documento);
          compromissoED.setTx_Observacao(ed.getNM_Pessoa() + " NF nr. "+ed.getNr_nota_fiscal());

          compromissoVoltaED = compromissoBD.inclui(compromissoED);
          compromissoED.setOid_Compromisso(compromissoVoltaED.getOid_Compromisso());

          ResultSet rs = executasql.executarConsulta("select max(OID_Parcela_Compromisso) as result from Parcelas_Compromissos ");
  	      while (rs != null && rs.next()){
            valOid = rs.getLong("result");
            Parcela_CompromissoED.setOID_Parcela_Compromisso(String.valueOf(++valOid));
          }

          Parcela_CompromissoED.setDT_Emissao(ed.getDt_emissao());
          Parcela_CompromissoED.setHR_Parcela_Compromisso(ed.getHr_stamp());
          Parcela_CompromissoED.setDT_Parcela_Compromisso(ed.getDt_emissao());
          Parcela_CompromissoED.setOID_Compromisso(compromissoED.getOid_Compromisso());
          Parcela_CompromissoED.setOID_Parcelamento(String.valueOf(oid));
          Parcela_CompromissoED.setDt_stamp(ed.getDt_stamp());
          Parcela_CompromissoED.setUsuario_Stamp(ed.getUsuario_Stamp());
          Parcela_CompromissoED.setDm_Stamp(ed.getDm_Stamp());

          sql = " insert into Parcelas_Compromissos (OID_Parcela_Compromisso, OID_Compromisso, OID_Parcelamento, DT_Parcela_Compromisso, HR_Parcela_Compromisso ) values ";
          sql += "(" + Parcela_CompromissoED.getOID_Parcela_Compromisso() + ",'" + Parcela_CompromissoED.getOID_Compromisso() + "'," + Parcela_CompromissoED.getOID_Parcelamento() + ",'"  + Parcela_CompromissoED.getDT_Parcela_Compromisso() + "','" + Parcela_CompromissoED.getHR_Parcela_Compromisso() + "')";

          int i = executasql.executarUpdate(sql);

      }
      catch(Exception exc){
          exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao gerar compromisso");
        excecoes.setMetodo("geraMovimento(Nota_Fiscal_TransacoesED ed)");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }


  public void geraRel_NFEntrada (Nota_Fiscal_CompraED edV , HttpServletResponse response) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean DataFormatada = new FormataDataBean ();
    int un = 0;

    try {
      sql = "SELECT notas_fiscais_transacoes.*, contas.cd_conta, centros_custos.cd_centro_custo FROM notas_fiscais_transacoes, contas, centros_custos " +
          " WHERE notas_fiscais_transacoes.oid_centro_custo = centros_custos.oid_centro_custo " +
          " AND notas_fiscais_transacoes.oid_conta = contas.oid_conta ";

      if (edV.getNr_nota_fiscal () != 0) {
        sql += "and notas_fiscais_transacoes.nr_nota_fiscal=" + edV.getNr_nota_fiscal ();
      }

      if (edV.getNm_serie () != null) {
        sql += " and notas_fiscais_transacoes.nm_serie ='" + edV.getNm_serie () + "' ";
      }

      if (edV.getOid_centro_custo () != 0) {
        sql += " and notas_fiscais_transacoes.Oid_centro_custo =" + edV.getOid_centro_custo ();
      }

      if ( (edV.getOid_pessoa () != null) && (!edV.getOid_pessoa ().equals (""))) {
        sql += " and notas_fiscais_transacoes.oid_pessoa ='" + edV.getOid_pessoa () + "' ";
      }

      if ( (edV.getDt_emissao () != null) && (!edV.getDt_emissao ().equals (""))) {
        sql += " and notas_fiscais_transacoes.dt_emissao >='" + edV.getDt_emissao () + "' ";
      }
      if ( (edV.getDt_emissao_final () != null) && (!edV.getDt_emissao_final ().equals (""))) {
        sql += " and notas_fiscais_transacoes.dt_emissao <='" + edV.getDt_emissao_final () + "' ";
      }

      if ( (edV.getDt_entrada () != null) && (!edV.getDt_entrada ().equals (""))) {
        sql += " and notas_fiscais_transacoes.dt_entrada >='" + edV.getDt_entrada () + "' ";
      }
      if ( (edV.getDt_entrada_final () != null) && (!edV.getDt_entrada_final ().equals (""))) {
        sql += " and notas_fiscais_transacoes.dt_entrada <='" + edV.getDt_entrada_final () + "' ";
      }

      if (JavaUtil.doValida (edV.getDm_finalizado ()) && (!edV.getDm_finalizado ().equals ("T"))) {
        sql += " and notas_fiscais_transacoes.Dm_finalizado ='" + edV.getDm_finalizado () + "' ";
      }
      if (String.valueOf (edV.getOID_Unidade_Contabil ()) != null &&
          !String.valueOf (edV.getOID_Unidade_Contabil ()).equals ("0") &&
          !String.valueOf (edV.getOID_Unidade_Contabil ()).equals ("null")
          ) {
        sql += " and notas_fiscais_transacoes.oid_unidade_contabil = " + edV.getOID_Unidade_Contabil ();
      }
      if (JavaUtil.doValida (String.valueOf (edV.getOID_Unidade_Fiscal ()))) {
        sql += " and notas_fiscais_transacoes.oid_unidade_fiscal = " + edV.getOID_Unidade_Fiscal ();
      }

      if (JavaUtil.doValida (String.valueOf (edV.getOid_Pedido_Compra_Nota_Fiscal ()))) {
        sql += " and notas_fiscais_transacoes.oid_nota_fiscal = pedidos_compras_notas_fiscais.oid_nota_fiscal " +
            " and pedidos_compras_notas_fiscais.oid_pedido_compra = " + edV.getOid_Pedido_Compra_Nota_Fiscal ();
      }

      sql += " order by notas_fiscais_transacoes.oid_unidade_fiscal, notas_fiscais_transacoes.dt_entrada ";

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {
        Nota_Fiscal_CompraED ed = new Nota_Fiscal_CompraED ();
        ed.setOid_nota_fiscal (res.getString ("oid_nota_fiscal"));
        ed.setNr_nota_fiscal (res.getLong ("nr_nota_fiscal"));
        ed.setDt_emissao (res.getString ("dt_emissao"));
        DataFormatada.setDT_FormataData (ed.getDt_emissao ());
        ed.setDt_emissao (DataFormatada.getDT_FormataData ());
        ed.setDt_entrada (res.getString ("dt_entrada"));
        DataFormatada.setDT_FormataData (ed.getDt_entrada ());
        ed.setDt_entrada (DataFormatada.getDT_FormataData ());

        ed.setNm_serie (res.getString ("nm_serie"));
        ed.setHr_entrada (res.getString ("hr_entrada"));

        ed.setOid_pessoa (res.getString ("oid_pessoa"));
        if (JavaUtil.doValida (String.valueOf (ed.getOid_pessoa ()))) {
          ed.setNM_Pessoa (PessoaBean.getByOID (ed.getOid_pessoa ()).getNM_Razao_Social ());
        }
        ed.setVl_nota_fiscal (res.getDouble ("vl_nota_fiscal"));
        ed.setVl_liquido_nota_fiscal (res.getDouble ("vl_liquido_nota_fiscal"));
        ed.setDm_tipo_nota_fiscal (res.getString ("dm_tipo_nota_fiscal"));
        ed.setDt_stamp (res.getString ("dt_stamp"));
        ed.setDm_observacao (res.getString ("dm_observacao"));
        ed.setDm_forma_pagamento (res.getString ("dm_forma_pagamento"));
        ed.setNr_parcelas (res.getLong ("nr_parcelas"));
        ed.setVl_descontos (res.getDouble ("vl_descontos"));
        ed.setDm_finalizado (res.getString ("dm_finalizado"));

        ed.setOID_Unidade_Contabil (res.getLong ("oid_unidade_contabil"));
        if (JavaUtil.doValida (String.valueOf (ed.getOID_Unidade_Contabil ()))) {
          un = new Long (ed.getOID_Unidade_Contabil ()).intValue ();
          ed.setCD_Unidade_Contabil (UnidadeBean.getByOID_Unidade (un).getCD_Unidade ());
        }
        ed.setOID_Unidade_Fiscal (res.getLong ("oid_unidade_fiscal"));
        if (JavaUtil.doValida (String.valueOf (ed.getOID_Unidade_Fiscal ()))) {
          un = new Long (ed.getOID_Unidade_Fiscal ()).intValue ();
          ed.setCD_Unidade_Fiscal (UnidadeBean.getByOID_Unidade (un).getCD_Unidade ());
        }

        ed.setNm_centro_custo (res.getString ("cd_centro_custo"));
        ed.setNm_conta_debito (res.getString ("cd_conta"));

        list.add (ed);
      }


    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao imprimir Relacao de NFs de Entrada");
      excecoes.setMetodo ("geraRel_NFEntrada()");
      excecoes.setExc (exc);
      throw excecoes;
    }

  }

  public Solicitacao_CompraED inclui_Movimento_OS_Nota_Fiscal (Solicitacao_CompraED ed) throws Excecoes {

    Solicitacao_CompraED edVolta = (Solicitacao_CompraED) ed;
    String sql = null;

    try {

//        seta oid_NF no movimento de servico apropriado!
      sql = "UPDATE movimentos_ordens_servicos set " +
          " oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
      sql += " WHERE Oid_movimento_ordem_servico = " + ed.getOid_movimento_ordem_servico ();
      int u = executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir!");
      excecoes.setMetodo ("inclui_Movimento_OS_Nota_Fiscal()");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public void deleta_Movimento_OS_Nota_Fiscal (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    try {
//        seta oid_NF para null no movimento de servico apropriado!
      sql = "UPDATE movimentos_ordens_servicos set " +
          " oid_nota_fiscal = null";
      sql += " WHERE Oid_movimento_ordem_servico = " + ed.getOid_movimento_ordem_servico ();
      int u = executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir!");
      excecoes.setMetodo ("deleta_Movimento_OS_Nota_Fiscal()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList listaMov_ServicoToNF (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    ArrayList list = new ArrayList ();

    try {

      sql = " select movimentos_ordens_servicos.*, ordens_servicos.nr_ordem_servico " +
          " from movimentos_ordens_servicos, ordens_servicos ";
      sql += " where ordens_servicos.Oid_ordem_servico = movimentos_ordens_servicos.Oid_ordem_servico ";
      if (String.valueOf (ed.getOid_fornecedor ()) != null &&
          !String.valueOf (ed.getOid_fornecedor ()).equals ("null") &&
          !String.valueOf (ed.getOid_fornecedor ()).equals ("0")) {
        sql += " and movimentos_ordens_servicos.oid_pessoa = '" + ed.getOid_fornecedor () + "'";
      }
      if (String.valueOf (ed.getNr_documento ()) != null &&
          !String.valueOf (ed.getNr_documento ()).equals ("null") &&
          !String.valueOf (ed.getNr_documento ()).equals ("0")) {
        sql += " and movimentos_ordens_servicos.nr_documento = " + ed.getNr_documento ();
      }

      if (String.valueOf (ed.getOid_nota_fiscal ()) != null &&
          !String.valueOf (ed.getOid_nota_fiscal ()).equals ("null") &&
          !String.valueOf (ed.getOid_nota_fiscal ()).equals ("0")) {
        sql += " and movimentos_ordens_servicos.oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
      }
      if (String.valueOf (ed.getOid_movimento_ordem_servico ()) != null &&
          !String.valueOf (ed.getOid_movimento_ordem_servico ()).equals ("null") &&
          !String.valueOf (ed.getOid_movimento_ordem_servico ()).equals ("0")) {
        sql += " and movimentos_ordens_servicos.Oid_movimento_ordem_servico = " + ed.getOid_movimento_ordem_servico ();
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_movimento_ordem_servico (res.getLong ("Oid_movimento_ordem_servico"));
        edVolta.setDt_servico (res.getString ("Dt_movimento_ordem_servico"));
        dataFormatada.setDT_FormataData (edVolta.getDt_servico ());
        edVolta.setDt_servico (dataFormatada.getDT_FormataData ());
        edVolta.setVl_servico (res.getDouble ("vl_movimento"));
        edVolta.setNr_quantidade (res.getDouble ("nr_quantidade"));
        edVolta.setNr_documento (res.getString ("nr_documento"));

        edVolta.setOid_ordem_servico (res.getLong ("nr_ordem_servico"));

        edVolta.setOid_nota_fiscal (res.getString ("oid_nota_fiscal"));

        edVolta.setOid_fornecedor (res.getString ("oid_pessoa"));

        edVolta.setDm_status_Solicitacao (res.getString ("Dm_faturado_pago"));
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("P")) {
          edVolta.setDm_status_Solicitacao ("Pago");
        }
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("F")) {
          edVolta.setDm_status_Solicitacao ("Faturado");
        }
        list.add (edVolta);

      }

    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getByRecord()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Solicitacao_CompraED getMov_ServicoToNF (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();

    try {

      sql = " select movimentos_ordens_servicos.*, notas_fiscais_transacoes.nr_nota_fiscal " +
          " from movimentos_ordens_servicos, notas_fiscais_transacoes ";
      sql += " where movimentos_ordens_servicos.oid_nota_fiscal = notas_fiscais_transacoes.oid_nota_fiscal ";
      if (String.valueOf (ed.getOid_fornecedor ()) != null &&
          !String.valueOf (ed.getOid_fornecedor ()).equals ("null") &&
          !String.valueOf (ed.getOid_fornecedor ()).equals ("0")) {
        sql += " and movimentos_ordens_servicos.oid_pessoa = '" + ed.getOid_fornecedor () + "'";
      }
      if (String.valueOf (ed.getNr_documento ()) != null &&
          !String.valueOf (ed.getNr_documento ()).equals ("null") &&
          !String.valueOf (ed.getNr_documento ()).equals ("0")) {
        sql += " and movimentos_ordens_servicos.nr_documento = " + ed.getNr_documento ();
      }

      if (String.valueOf (ed.getOid_nota_fiscal ()) != null &&
          !String.valueOf (ed.getOid_nota_fiscal ()).equals ("null") &&
          !String.valueOf (ed.getOid_nota_fiscal ()).equals ("0")) {
        sql += " and movimentos_ordens_servicos.oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
      }
      if (String.valueOf (ed.getOid_movimento_ordem_servico ()) != null &&
          !String.valueOf (ed.getOid_movimento_ordem_servico ()).equals ("null") &&
          !String.valueOf (ed.getOid_movimento_ordem_servico ()).equals ("0")) {
        sql += " and movimentos_ordens_servicos.Oid_movimento_ordem_servico = " + ed.getOid_movimento_ordem_servico ();
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_movimento_ordem_servico (res.getLong ("Oid_movimento_ordem_servico"));
        edVolta.setDt_servico (res.getString ("Dt_movimento_ordem_servico"));
        dataFormatada.setDT_FormataData (edVolta.getDt_servico ());
        edVolta.setDt_servico (dataFormatada.getDT_FormataData ());
        edVolta.setVl_servico (res.getDouble ("vl_movimento"));
        edVolta.setNr_quantidade (res.getDouble ("nr_quantidade"));
        edVolta.setNr_documento (res.getString ("nr_documento"));

        edVolta.setNr_nota_fiscal (res.getString ("nr_nota_fiscal"));

        edVolta.setOid_fornecedor (res.getString ("oid_pessoa"));

        edVolta.setDm_status_Solicitacao (res.getString ("Dm_faturado_pago"));
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("P")) {
          edVolta.setDm_status_Solicitacao ("Pago");
        }
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("F")) {
          edVolta.setDm_status_Solicitacao ("Faturado");
        }

      }

    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getByRecord()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public boolean verificaFiscal(Nota_Fiscal_CompraED ed) throws Excecoes {

		try {
			BancoUtil butil = new BancoUtil(this.executasql);
			//*** Verifica se existe Livro Fiscal
			if (!butil.doExiste("Livros_Fiscais", " oid_Nota_Fiscal ='"
					+ ed.getOid_nota_fiscal() + "'"))
				new Livro_FiscalBD(executasql).geraLivro_Fiscal_Entrada_Simplificada(new Livro_FiscalED(ed.getOid_nota_fiscal(), "NF"));
			//throw new Mensagens("Voc� precisa gerar o Livro Fiscal primeiro!
			// Nota N�: "+ed.getNr_nota_fiscal());
			//*** Verifica o Valor Cont�bil com o Valor da NF
			double vlContabil = butil.getTableDoubleValue("sum(VL_Contabil)",
					"Livros_Fiscais", "oid_Nota_Fiscal ='"
							+ ed.getOid_nota_fiscal() + "'"
							+ " AND oid_Pessoa_Emitente ='"
							+ ed.getOid_pessoa() + "'");
			if (vlContabil != Valor.round(ed.getVl_liquido_nota_fiscal(), 2))
				throw new Mensagens(
						"Valor da NF n�o condiz com o valor cont�bil do Livro Fiscal! Nota N�: "
								+ ed.getNr_nota_fiscal());
			return true;

		} catch (Mensagens e) {
			throw e;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "verificaFiscal()");
		}
	}


}
