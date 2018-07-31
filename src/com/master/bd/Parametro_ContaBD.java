package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.master.ed.Parametro_ContaED;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class Parametro_ContaBD {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);

  public Parametro_ContaBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  public Parametro_ContaED getByRecord (Parametro_ContaED ed) throws Excecoes {
    String sql = " select * from Parametros_Contas ";
    Parametro_ContaED edVolta = new Parametro_ContaED ();
    try {
      ResultSet res = this.executasql.executarConsulta (sql);

      try {
        while (res.next ()) {
          //dados de conta corrente
          edVolta = new Parametro_ContaED ();

          edVolta.setCd_conta_icms_credito (res.getString ("cd_conta_icms_credito"));
          edVolta.setCd_conta_icms_debito (res.getString ("cd_conta_icms_debito"));

          edVolta.setCd_conta_pis_credito (res.getString ("cd_conta_pis_credito"));
          edVolta.setCd_conta_pis_debito (res.getString ("cd_conta_pis_debito"));

          edVolta.setCd_conta_cofins_credito (res.getString ("cd_conta_cofins_credito"));
          edVolta.setCd_conta_cofins_debito (res.getString ("cd_conta_cofins_debito"));

          edVolta.setCd_conta_seguro_credito (res.getString ("cd_conta_seguro_credito"));
          edVolta.setCd_conta_seguro_debito (res.getString ("cd_conta_seguro_debito"));

          edVolta.setCd_conta_irrf_credito (res.getString ("cd_conta_irrf_credito"));

          edVolta.setCd_conta_pedagio (res.getString ("cd_conta_pedagio"));
          edVolta.setCd_conta_zera_resultado (res.getString ("cd_conta_zera_resultado"));

          edVolta.setCd_conta_sest_senat_credito (res.getString ("cd_conta_sest_senat_credito"));
          edVolta.setCd_conta_inss_empresa_credito (res.getString ("cd_conta_inss_empresa_credito"));
          edVolta.setCd_conta_inss_empresa_debito (res.getString ("cd_conta_inss_empresa_debito"));
          edVolta.setCd_conta_inss_terceiro_credito (res.getString ("cd_conta_inss_terceiro_credito"));

          edVolta.setCd_conta_liquido_nota_fiscal (res.getString ("cd_conta_liquido_nota_fiscal"));
          edVolta.setCd_conta_ipi_nota_fiscal (res.getString ("cd_conta_ipi_nota_fiscal"));
          edVolta.setCd_conta_ipi_nota_fiscal_credito (res.getString ("cd_conta_ipi_nota_fiscal_credito"));
          edVolta.setCd_conta_servico_nota_fiscal (res.getString ("cd_conta_servico_nota_fiscal"));

          edVolta.setCd_conta_icms_nota_fiscal (res.getString ("cd_conta_icms_nota_fiscal"));
          edVolta.setCd_conta_icms_nota_fiscal_credito (res.getString ("cd_conta_icms_nota_fiscal_credito"));
          edVolta.setCd_conta_isqn_nota_fiscal (res.getString ("cd_conta_isqn_nota_fiscal"));
          edVolta.setCd_conta_desconto_nota_fiscal (res.getString ("cd_conta_desconto_nota_fiscal"));

          edVolta.setCd_conta_irrf_nota_fiscal (res.getString ("cd_conta_irrf_nota_fiscal"));
          edVolta.setCd_conta_inss_nota_fiscal (res.getString ("cd_conta_inss_nota_fiscal"));
          edVolta.setCd_conta_pis_nota_fiscal (res.getString ("cd_conta_pis_nota_fiscal"));
          edVolta.setCd_conta_pis_nota_fiscal_credito (res.getString ("cd_conta_pis_nota_fiscal_credito"));
          edVolta.setCd_conta_cofins_nota_fiscal (res.getString ("cd_conta_cofins_nota_fiscal"));
          edVolta.setCd_conta_cofins_nota_fiscal_credito (res.getString ("cd_conta_cofins_nota_fiscal_credito"));
          edVolta.setCd_conta_csll_nota_fiscal (res.getString ("cd_conta_csll_nota_fiscal"));
          edVolta.setCd_conta_csll_nota_fiscal_credito (res.getString ("cd_conta_csll_nota_fiscal_credito"));

          edVolta.setCd_conta_despesas_nota_fiscal (res.getString ("cd_conta_despesas_nota_fiscal"));
          edVolta.setCd_conta_frete_nota_fiscal (res.getString ("cd_conta_frete_nota_fiscal"));

          edVolta.setCd_conta_multa_pagamento (res.getString ("cd_conta_multa_pagamento"));
          edVolta.setCd_conta_juros_pagamento (res.getString ("cd_conta_juros_pagamento"));
          edVolta.setCd_conta_desconto_pagamento (res.getString ("cd_conta_desconto_pagamento"));
          edVolta.setCd_conta_outras_despesas_pagamento (res.getString ("cd_conta_outras_despesas_pagamento"));

          edVolta.setCd_conta_transitoria_compensacao_cheque (res.getString ("cd_conta_transitoria_compensacao_cheque"));
          edVolta.setCd_conta_fornecedores_geral (res.getString ("cd_conta_fornecedores_geral"));

          edVolta.setCd_conta_cliente_geral (res.getString ("cd_conta_cliente_geral"));
          edVolta.setCd_conta_venda (res.getString ("cd_conta_venda"));
          edVolta.setCd_conta_a_faturar (res.getString ("cd_conta_a_faturar"));

          edVolta.setCd_conta_adiantamento_viagem (res.getString ("cd_conta_adiantamento_viagem"));
          edVolta.setCd_conta_adiantamento_funcionario (res.getString ("cd_conta_adiantamento_funcionario"));
          edVolta.setCd_conta_adiantamento_terceiro (res.getString ("cd_conta_adiantamento_terceiro"));

          edVolta.setCd_conta_frete_pj (res.getString ("cd_conta_frete_pj"));
          edVolta.setCd_conta_frete_pf (res.getString ("cd_conta_frete_pf"));

          edVolta.setCd_conta_juros_duplicata (res.getString ("cd_conta_juros_duplicata"));
          edVolta.setCd_conta_desconto_duplicata (res.getString ("cd_conta_desconto_duplicata"));
          edVolta.setCd_conta_despesas_duplicata (res.getString ("cd_conta_despesas_duplicata"));

          edVolta.setCd_conta_compensacao_cheque (res.getString ("cd_conta_compensacao_cheque"));
          edVolta.setCd_conta_ofpf_debito (res.getString ("cd_conta_ofpf_debito"));
          edVolta.setCd_conta_ofpf_credito (res.getString ("cd_conta_ofpf_credito"));
          edVolta.setCd_conta_ofpj_debito (res.getString ("cd_conta_ofpj_debito"));
          edVolta.setCd_conta_ofpj_credito (res.getString ("cd_conta_ofpj_credito"));

          edVolta.setTx_Assinatura1(res.getString ("tx_Assinatura1"));
          edVolta.setTx_Assinatura2(res.getString ("tx_Assinatura2"));
          edVolta.setTx_Cargo1(res.getString ("tx_Cargo1"));
          edVolta.setTx_Cargo2(res.getString ("tx_Cargo2"));
          edVolta.setTx_Cic1(res.getString ("tx_Cic1"));
          edVolta.setTx_Cic2(res.getString ("tx_Cic2"));
          edVolta.setTx_Fone1(res.getString ("tx_Fone1"));
          edVolta.setTx_Fone2(res.getString ("tx_Fone2"));


        }
        return edVolta;

      }
      finally {
        util.closeResultset (res);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecord(Parametro_ContaED ed)");
    }
  }

  public void altera (Parametro_ContaED ed) throws Excecoes {


	try {
	    String sql = " select * from Parametros_Contas ";
	    ResultSet res = this.executasql.executarConsulta (sql);

	    if (!res.next ()) {
		    sql = "  INSERT INTO Parametros_Contas ( cd_conta_cofins_credito ) values (null) ";
	      	executasql.executarUpdate(sql);
	    }


    sql = " update Parametros_Contas set ";

    if (!"".equals (ed.getCd_conta_cofins_credito ())) {
      sql += " cd_conta_cofins_credito = '" + ed.getCd_conta_cofins_credito () + "', ";
    }
    else {
      sql += " cd_conta_cofins_credito = null, ";
    }

    if (!"".equals (ed.getCd_conta_cofins_debito ())) {
      sql += " cd_conta_cofins_debito = '" + ed.getCd_conta_cofins_debito () + "', ";
    }
    else {
      sql += " cd_conta_cofins_debito = null, ";
    }

    if (!"".equals (ed.getCd_conta_cofins_nota_fiscal ())) {
      sql += " cd_conta_cofins_nota_fiscal = '" + ed.getCd_conta_cofins_nota_fiscal () + "', ";
    }
    else {
      sql += " cd_conta_cofins_nota_fiscal = null, ";
    }

    if (!"".equals (ed.getCd_conta_csll_nota_fiscal ())) {
      sql += " cd_conta_csll_nota_fiscal = '" + ed.getCd_conta_csll_nota_fiscal () + "', ";
    }
    else {
      sql += " cd_conta_csll_nota_fiscal = null, ";
    }

    if (!"".equals (ed.getCd_conta_desconto_nota_fiscal ())) {
      sql += " cd_conta_desconto_nota_fiscal = '" + ed.getCd_conta_desconto_nota_fiscal () + "', ";
    }
    else {
      sql += " cd_conta_desconto_nota_fiscal = null, ";
    }

    if (!"".equals (ed.getCd_conta_icms_credito ())) {
      sql += " cd_conta_icms_credito = '" + ed.getCd_conta_icms_credito () + "', ";
    }
    else {
      sql += " cd_conta_icms_credito = null, ";
    }

    if (!"".equals (ed.getCd_conta_icms_debito ())) {
      sql += " cd_conta_icms_debito = '" + ed.getCd_conta_icms_debito () + "', ";
    }
    else {
      sql += " cd_conta_icms_debito = null, ";
    }

    if (!"".equals (ed.getCd_conta_icms_nota_fiscal ())) {
      sql += " cd_conta_icms_nota_fiscal = '" + ed.getCd_conta_icms_nota_fiscal () + "', ";
    }
    else {
      sql += " cd_conta_icms_nota_fiscal = null, ";
    }

    if (!"".equals (ed.getCd_conta_inss_empresa_credito ())) {
      sql += " cd_conta_inss_empresa_credito = '" + ed.getCd_conta_inss_empresa_credito () + "', ";
    }
    else {
      sql += " cd_conta_inss_empresa_credito = null, ";
    }

    if (!"".equals (ed.getCd_conta_inss_empresa_debito ())) {
      sql += " cd_conta_inss_empresa_debito = '" + ed.getCd_conta_inss_empresa_debito () + "', ";
    }
    else {
      sql += " cd_conta_inss_empresa_debito = null, ";
    }

    if (!"".equals (ed.getCd_conta_inss_nota_fiscal ())) {
      sql += " cd_conta_inss_nota_fiscal = '" + ed.getCd_conta_inss_nota_fiscal () + "', ";
    }
    else {
      sql += " cd_conta_inss_nota_fiscal = null, ";
    }

    if (!"".equals (ed.getCd_conta_inss_terceiro_credito ())) {
      sql += " cd_conta_inss_terceiro_credito = '" + ed.getCd_conta_inss_terceiro_credito () + "', ";
    }
    else {
      sql += " cd_conta_inss_terceiro_credito = null, ";
    }

    if (!"".equals (ed.getCd_conta_ipi_nota_fiscal ())) {
      sql += " cd_conta_ipi_nota_fiscal = '" + ed.getCd_conta_ipi_nota_fiscal () + "', ";
    }
    else {
      sql += " cd_conta_ipi_nota_fiscal = null, ";
    }

    if (!"".equals (ed.getCd_conta_irrf_credito ())) {
      sql += " cd_conta_irrf_credito = '" + ed.getCd_conta_irrf_credito () + "', ";
    }
    else {
      sql += " cd_conta_irrf_credito = null, ";
    }

    if (!"".equals (ed.getCd_conta_irrf_nota_fiscal ())) {
      sql += " cd_conta_irrf_nota_fiscal = '" + ed.getCd_conta_irrf_nota_fiscal () + "', ";
    }
    else {
      sql += " cd_conta_irrf_nota_fiscal = null, ";
    }

    if (!"".equals (ed.getCd_conta_isqn_nota_fiscal ())) {
      sql += " cd_conta_isqn_nota_fiscal = '" + ed.getCd_conta_isqn_nota_fiscal () + "', ";
    }
    else {
      sql += " cd_conta_isqn_nota_fiscal = null, ";
    }

    if (!"".equals (ed.getCd_conta_liquido_nota_fiscal ())) {
      sql += " cd_conta_liquido_nota_fiscal = '" + ed.getCd_conta_liquido_nota_fiscal () + "', ";
    }
    else {
      sql += " cd_conta_liquido_nota_fiscal = null, ";
    }

    if (!"".equals (ed.getCd_conta_pis_credito ())) {
      sql += " cd_conta_pis_credito = '" + ed.getCd_conta_pis_credito () + "', ";
    }
    else {
      sql += " cd_conta_pis_credito = null, ";
    }

    if (!"".equals (ed.getCd_conta_pis_debito ())) {
      sql += " cd_conta_pis_debito = '" + ed.getCd_conta_pis_debito () + "', ";
    }
    else {
      sql += " cd_conta_pis_debito = null, ";
    }

    if (!"".equals (ed.getCd_conta_pis_nota_fiscal ())) {
      sql += " cd_conta_pis_nota_fiscal = '" + ed.getCd_conta_pis_nota_fiscal () + "', ";
    }
    else {
      sql += " cd_conta_pis_nota_fiscal = null, ";
    }

    if (!"".equals (ed.getCd_conta_seguro_credito ())) {
      sql += " cd_conta_seguro_credito = '" + ed.getCd_conta_seguro_credito () + "', ";
    }
    else {
      sql += " cd_conta_seguro_credito = null, ";
    }

    if (!"".equals (ed.getCd_conta_seguro_debito ())) {
      sql += " cd_conta_seguro_debito = '" + ed.getCd_conta_seguro_debito () + "', ";
    }
    else {
      sql += " cd_conta_seguro_debito = null, ";
    }

    if (!"".equals (ed.getCd_conta_servico_nota_fiscal ())) {
      sql += " cd_conta_servico_nota_fiscal = '" + ed.getCd_conta_servico_nota_fiscal () + "', ";
    }
    else {
      sql += " cd_conta_servico_nota_fiscal = null, ";
    }

    if (!"".equals (ed.getCd_conta_sest_senat_credito ())) {
      sql += " cd_conta_sest_senat_credito = '" + ed.getCd_conta_sest_senat_credito () + "', ";
    }
    else {
      sql += " cd_conta_sest_senat_credito = null, ";
    }

    if (!"".equals (ed.getCd_conta_multa_pagamento ())) {
      sql += " cd_conta_multa_pagamento = '" + ed.getCd_conta_multa_pagamento () + "', ";
    }
    else {
      sql += " cd_conta_multa_pagamento = null, ";
    }

    if (!"".equals (ed.getCd_conta_juros_pagamento ())) {
      sql += " cd_conta_juros_pagamento = '" + ed.getCd_conta_juros_pagamento () + "', ";
    }
    else {
      sql += " cd_conta_juros_pagamento = null, ";
    }

    if (!"".equals (ed.getCd_conta_desconto_pagamento ())) {
      sql += " cd_conta_desconto_pagamento = '" + ed.getCd_conta_desconto_pagamento () + "', ";
    }
    else {
      sql += " cd_conta_desconto_pagamento = null, ";
    }

    if (!"".equals (ed.getCd_conta_outras_despesas_pagamento ())) {
      sql += " cd_conta_outras_despesas_pagamento = '" + ed.getCd_conta_outras_despesas_pagamento () + "', ";
    }
    else {
      sql += " cd_conta_outras_despesas_pagamento = null, ";
    }

    if (!"".equals (ed.getCd_conta_transitoria_compensacao_cheque ())) {
      sql += " cd_conta_transitoria_compensacao_cheque = '" + ed.getCd_conta_transitoria_compensacao_cheque () + "', ";
    }
    else {
      sql += " cd_conta_transitoria_compensacao_cheque = null ";
    }

    if (!"".equals (ed.getCd_conta_ipi_nota_fiscal_credito ())) {
      sql += " cd_conta_ipi_nota_fiscal_credito = '" + ed.getCd_conta_ipi_nota_fiscal_credito () + "', ";
    }
    else {
      sql += " cd_conta_ipi_nota_fiscal_credito = null, ";
    }

    if (!"".equals (ed.getCd_conta_pis_nota_fiscal_credito ())) {
      sql += " cd_conta_pis_nota_fiscal_credito = '" + ed.getCd_conta_pis_nota_fiscal_credito () + "', ";
    }
    else {
      sql += " cd_conta_pis_nota_fiscal_credito= null, ";
    }

    if (!"".equals (ed.getCd_conta_cofins_nota_fiscal_credito ())) {
      sql += " cd_conta_cofins_nota_fiscal_credito = '" + ed.getCd_conta_cofins_nota_fiscal_credito () + "', ";
    }
    else {
      sql += " cd_conta_cofins_nota_fiscal_credito = null, ";
    }

    if (!"".equals (ed.getCd_conta_csll_nota_fiscal_credito ())) {
      sql += " cd_conta_csll_nota_fiscal_credito = '" + ed.getCd_conta_csll_nota_fiscal_credito () + "', ";
    }
    else {
      sql += " cd_conta_csll_nota_fiscal_credito = null, ";
    }

    if (!"".equals (ed.getCd_conta_icms_nota_fiscal_credito ())) {
      sql += " cd_conta_icms_nota_fiscal_credito = '" + ed.getCd_conta_icms_nota_fiscal_credito () + "', ";
    }
    else {
      sql += " cd_conta_icms_nota_fiscal_credito = null, ";
    }

    if (!"".equals (ed.getCd_conta_despesas_nota_fiscal ())) {
      sql += " cd_conta_despesas_nota_fiscal = '" + ed.getCd_conta_despesas_nota_fiscal () + "', ";
    }
    else {
      sql += " cd_conta_despesas_nota_fiscal = null, ";
    }

    if (!"".equals (ed.getCd_conta_frete_nota_fiscal ())) {
      sql += " cd_conta_frete_nota_fiscal = '" + ed.getCd_conta_frete_nota_fiscal () + "', ";
    }
    else {
      sql += " cd_conta_frete_nota_fiscal = null, ";
    }

    if (!"".equals (ed.getCd_conta_fornecedores_geral ())) {
      sql += " cd_conta_fornecedores_geral = '" + ed.getCd_conta_fornecedores_geral () + "', ";
    }
    else {
      sql += " cd_conta_fornecedores_geral = null, ";
    }

    if (!"".equals (ed.getCd_conta_cliente_geral ())) {
      sql += " cd_conta_cliente_geral = '" + ed.getCd_conta_cliente_geral () + "', ";
    }
    else {
      sql += " cd_conta_cliente_geral = null, ";
    }

    if (!"".equals (ed.getCd_conta_venda ())) {
      sql += " cd_conta_venda = '" + ed.getCd_conta_venda () + "', ";
    }
    else {
      sql += " cd_conta_venda = null, ";
    }

    if (!"".equals (ed.getCd_conta_a_faturar ())) {
      sql += " cd_conta_a_faturar = '" + ed.getCd_conta_a_faturar () + "', ";
    }
    else {
      sql += " cd_conta_a_faturar = null, ";
    }

    if (!"".equals (ed.getCd_conta_adiantamento_viagem ())) {
      sql += " cd_conta_adiantamento_viagem = '" + ed.getCd_conta_adiantamento_viagem () + "', ";
    }
    else {
      sql += " cd_conta_adiantamento_viagem = null, ";
    }

    if (!"".equals (ed.getCd_conta_adiantamento_funcionario ())) {
      sql += " cd_conta_adiantamento_funcionario = '" + ed.getCd_conta_adiantamento_funcionario () + "', ";
    }
    else {
      sql += " cd_conta_adiantamento_funcionario = null, ";
    }

    if (!"".equals (ed.getCd_conta_adiantamento_terceiro ())) {
      sql += " cd_conta_adiantamento_terceiro = '" + ed.getCd_conta_adiantamento_terceiro () + "', ";
    }
    else {
      sql += " cd_conta_adiantamento_terceiro = null, ";
    }

    if (!"".equals (ed.getCd_conta_frete_pj ())) {
      sql += " cd_conta_frete_pj = '" + ed.getCd_conta_frete_pj () + "', ";
    }
    else {
      sql += " cd_conta_frete_pj = null, ";
    }

    if (!"".equals (ed.getCd_conta_frete_pf ())) {
      sql += " cd_conta_frete_pf = '" + ed.getCd_conta_frete_pf () + "', ";
    }
    else {
      sql += " cd_conta_frete_pf = null, ";
    }


    if (!"".equals (ed.getCd_conta_compensacao_cheque ())) {
        sql += " cd_conta_compensacao_cheque = '" + ed.getCd_conta_compensacao_cheque () + "', ";
      }
      else {
        sql += " cd_conta_compensacao_cheque = null, ";
      }

    if (!"".equals (ed.getCd_conta_ofpf_debito ())) {
        sql += " cd_conta_ofpf_debito = '" + ed.getCd_conta_ofpf_debito () + "', ";
      }
      else {
        sql += " cd_conta_ofpf_debito = null, ";
      }

    if (!"".equals (ed.getCd_conta_ofpf_credito ())) {
        sql += " cd_conta_ofpf_credito = '" + ed.getCd_conta_ofpf_credito () + "', ";
      }
      else {
        sql += " cd_conta_ofpf_credito = null, ";
      }

    if (!"".equals (ed.getCd_conta_ofpj_debito ())) {
        sql += " cd_conta_ofpj_debito = '" + ed.getCd_conta_ofpj_debito () + "', ";
      }
      else {
        sql += " cd_conta_ofpj_debito = null, ";
      }

    if (!"".equals (ed.getCd_conta_ofpj_credito ())) {
        sql += " cd_conta_ofpj_credito = '" + ed.getCd_conta_ofpj_credito () + "', ";
      }
      else {
        sql += " cd_conta_ofpj_credito = null, ";
      }

    if (!"".equals (ed.getCd_conta_juros_duplicata ())) {
      sql += " cd_conta_juros_duplicata = '" + ed.getCd_conta_juros_duplicata () + "', ";
    }
    else {
      sql += " cd_conta_juros_duplicata = null, ";
    }

    if (!"".equals (ed.getCd_conta_desconto_duplicata ())) {
      sql += " cd_conta_desconto_duplicata = '" + ed.getCd_conta_desconto_duplicata () + "', ";
    }
    else {
      sql += " cd_conta_desconto_duplicata = null, ";
    }

    if (!"".equals (ed.getCd_conta_despesas_duplicata ())) {
      sql += " cd_conta_despesas_duplicata = '" + ed.getCd_conta_despesas_duplicata () + "', ";
    }
    else {
      sql += " cd_conta_despesas_duplicata = null, ";
    }

    if (!"".equals (ed.getCd_conta_zera_resultado ())) {
      sql += " cd_conta_zera_resultado = '" + ed.getCd_conta_zera_resultado () + "', ";
    }
    else {
      sql += " cd_conta_zera_resultado = null ";
    }

    if (!"".equals (ed.getCd_conta_pedagio ())) {
      sql += " cd_conta_pedagio = '" + ed.getCd_conta_pedagio () + "' ";
    }
    else {
      sql += " cd_conta_pedagio = null ";
    }

    // System.out.println(sql);

      executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera(Parametro_ContaED ed)");
    }
  }
}