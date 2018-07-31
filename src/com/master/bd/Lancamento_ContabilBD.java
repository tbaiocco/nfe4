package com.master.bd;

import java.sql.*;
import java.text.*;
import java.util.*;

import com.master.ed.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.*;

public class Lancamento_ContabilBD {

  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();
  Parametro_ContaED paramConta = new Parametro_ContaED ();
  FormataDataBean dataFormatada = new FormataDataBean ();
  ContaED Conta = new ContaED ();

  private ExecutaSQL executasql;
  private Base_EmpresaED base_EmpresaED = new Base_EmpresaED ();

  public Lancamento_ContabilBD (ExecutaSQL sql) throws Excecoes {
    this.executasql = sql;
    try {
      this.paramConta = new Parametro_ContaBD (this.executasql).getByRecord (this.paramConta);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      exc.printStackTrace ();
      excecoes.setMetodo ("Lancamento_ContabilBD - Construtor");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ContaED buscaConta (String cd_Conta) throws Excecoes {
    try {
      ContaED cta = new ContaED ();
      cta.setCd_Conta (cd_Conta);
      return new ContaBD (this.executasql).getByCD (cta);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      exc.printStackTrace ();
      excecoes.setMetodo ("Lancamento_ContabilBD - Construtor");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }
  public void inclui_CTB_Ordem_Frete_Adto (Ordem_FreteED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " SELECT Ordens_Fretes.oid_Ordem_Frete" +
          "     ,Ordens_Fretes.nr_Ordem_Frete " +
          "     ,Ordens_Fretes.oid_unidade" +
          "     ,Ordens_Fretes.oid_Pessoa as OID_Pessoa_Fornecedor" +
          "     ,Ordens_Fretes.dt_Emissao" +
          "     ,Ordens_Fretes.vl_ordem_frete" +
          "     ,Pessoas.nm_razao_social as nm_fornecedor" +
          "     ,Pessoas.nr_cnpj_cpf as NR_CNPJ_Pessoa_Fornecedor" +
          " FROM Ordens_Fretes" +
          "     ,Pessoas" +
          " WHERE Ordens_Fretes.oid_motorista = Pessoas.oid_pessoa " +
          // "   and (Ordens_Fretes.dm_Contabilizado <> 'S' " +
          // "		or Ordens_Fretes.dm_Contabilizado is null)" +
          "   and Ordens_Fretes.oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      String NM_Complementar = null;

      while (res.next ()) {
        NM_Complementar = "Adiantamento Nr. " + res.getString ("nr_ordem_frete") + " Motorista.: " + res.getString ("nm_fornecedor");

        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
        edLC.setOID_Compromisso (0);
        edLC.setOID_Nota_Fiscal ("");
        edLC.setOID_Lote_Pagamento (0);
        edLC.setOID_Lote_Compromisso ("");
        edLC.setOID_Solicitacao ("");
        edLC.setOID_Acerto ("0");
        edLC.setOID_Movimento_Servico ("");
        edLC.setOID_Conhecimento ("");
        edLC.setOID_Caixa ("");
        edLC.setOid_movimento_duplicata ("");
        edLC.setDM_Stamp (" ");

        edLC.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));
        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
        edLC.setDT_Lancamento (res.getString ("dt_Emissao"));
        edLC.setDT_Stamp (Data.getDataDMY ());
        edLC.setTx_Chave_Origem (String.valueOf (ed.getOID_Ordem_Frete ()));
        edLC.setOID_Origem (109);

        if (res.getDouble ("vl_ordem_frete") > 0) {

            this.Conta = this.buscaConta ("11903");
            edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
            edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	        edLC.setNM_Complementar (NM_Complementar);
  	        edLC.setVL_Lancamento (res.getDouble ("vl_ordem_frete"));
  	        edLC.setDM_Acao ("C");
  	        edLC.setNR_Lote (0);

  	        edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

	        this.Conta = this.buscaConta ("11910");
	        edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	        edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());

	        edLC.setDM_Acao ("D");
	        this.inclui_Lancamento_Contabil (edLC);

		    sql = " UPDATE Ordens_Fretes SET ";
		    sql += " DM_Contabilizado = 'S'";
		    sql += " WHERE oid_Ordem_Frete = '" + res.getString ("OID_Ordem_Frete") + "'";
		    // executasql.executarUpdate (sql);
  	    }
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      exc.printStackTrace ();
      excecoes.setMetodo ("inclui lancamentos");
      excecoes.setExc (exc);
      throw excecoes;
    }
}

  public void inclui_CTB_Lote_Pagamento (Lote_PagamentoED ed) throws Excecoes {

    String sql = null;

    try {
      this.inclui_Lancamento_Lote_Pagamento (ed.getOid_Lote_Pagamento ().longValue () , "LP",0,0,"");

      sql = " UPDATE Lotes_Pagamentos SET ";
      sql += " DM_Contabilizado = 'S'";
      sql += " WHERE oid_Lote_Pagamento = " + ed.getNr_Lote_Pagamento ();

      // executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      exc.printStackTrace ();
      excecoes.setMetodo ("inclui lancamentos");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void inclui_CTB_Compensacao_Lote_Pagamento (Lote_PagamentoED ed) throws Excecoes {

	    String sql = null;

	    try {
	      this.inclui_Lancamento_Compensacao_Lote_Pagamento (ed.getOid_Lote_Pagamento ().longValue () , "LP",0,0,"");

	      sql = " UPDATE Lotes_Pagamentos SET ";
	      sql += " DM_Contabilizado = 'S'";
	      sql += " WHERE oid_Lote_Pagamento = " + ed.getNr_Lote_Pagamento ();

	      // executasql.executarUpdate (sql);

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      exc.printStackTrace ();
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void inclui_CTB_Movimento_Duplicata (Movimento_DuplicataED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " SELECT " +
          "      duplicatas.oid_unidade" +
          "     ,duplicatas.oid_Duplicata" +
          "     ,duplicatas.NR_Duplicata" +
          "     ,duplicatas.OID_Pessoa as OID_Pessoa_Cliente" +
          "     ,Pessoas.NM_Razao_Social" +
          "     ,movimentos_duplicatas.dt_movimento " +
          "     ,movimentos_duplicatas.dm_Principal " +
          "     ,movimentos_duplicatas.oid_movimento_duplicata" +
          "     ,movimentos_duplicatas.VL_Variacao_Cambial" +
          "     ,movimentos_duplicatas.oid_tipo_instrucao" +
          "     ,(movimentos_duplicatas.vl_debito + movimentos_duplicatas.vl_credito) as VL_Pagamento" +
          "     ,movimentos_duplicatas.vl_variacao_cambial" +
          "     ,tipos_instrucoes.cd_Conta_Contabil as cd_Conta_Contabil_Instrucao" +
          "     ,tipos_instrucoes.DM_Gera_Movimento " +
          "     ,tipos_instrucoes.DM_Atualiza_Saldo " +
          "     ,conta_instrucao.oid_historico as oid_historico_instrucao" +
          "     ,conta_instrucao.oid_conta as oid_conta_instrucao" +
          "     ,contas.oid_historico" +
          "     ,contas.oid_conta" +
          " FROM duplicatas" +
          "     ,Pessoas" +
          "     ,movimentos_duplicatas" +
          "     ,tipos_instrucoes" +
          "     ,carteiras" +
          "     ,contas " +
          "     ,contas conta_instrucao " +
          "     ,contas_correntes " +
          " WHERE movimentos_duplicatas.oid_duplicata = duplicatas.oid_duplicata " +
          "   and duplicatas.oid_Pessoa = Pessoas.oid_Pessoa " +
          "   and movimentos_duplicatas.oid_carteira = carteiras.oid_carteira " +
          "   and movimentos_duplicatas.oid_Tipo_Instrucao = tipos_instrucoes.oid_Tipo_Instrucao " +
          "   and tipos_instrucoes.cd_Conta_Contabil =  conta_instrucao.cd_conta " +
          "   and tipos_instrucoes.DM_Gera_Movimento <>'N' " +
          "   and carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
          "   and contas_correntes.oid_conta = Contas.oid_conta " +
          // "   and (movimentos_duplicatas.dm_Contabilizado <> 'S' " +
          // "		or movimentos_duplicatas.dm_Contabilizado is null)" +
          "   and movimentos_duplicatas.oid_movimento_duplicata = '" + ed.getOid_Movimento_Duplicata () + "'";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      String DM_Acao = null;
      long OID_Conta = 0;
      long OID_Historico = 0;
      long nr_Lote = 0;
      String nm_Complemento="";
      while (res.next ()) {

          Lancamento_ContabilED edLC = new Lancamento_ContabilED ();

          edLC.setOID_Compromisso (0);
          edLC.setOID_Conhecimento ("");
          edLC.setOID_Nota_Fiscal ("");
          edLC.setOID_Solicitacao ("");
          edLC.setOID_Acerto ("0");
          edLC.setOID_Movimento_Servico ("");
          edLC.setOID_Ordem_Frete ("");
          edLC.setOID_Caixa ("");
          edLC.setOID_Lote_Compromisso ("");
          edLC.setOID_Lote_Pagamento (0);

          edLC.setDM_Stamp (" ");

          edLC.setDT_Lancamento (res.getString ("dt_movimento"));
          edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
          edLC.setOid_movimento_duplicata (res.getString ("oid_movimento_duplicata"));
          edLC.setDT_Stamp (Data.getDataDMY ());
          edLC.setTx_Chave_Origem (res.getString("oid_Duplicata"));
          edLC.setOID_Origem (8);

          sql = " SELECT NR_Lote " +
          		" FROM movimentos_contabeis " +
          		" WHERE movimentos_contabeis.Tx_Chave_Origem = '" + res.getString("oid_Duplicata") + "'" +
          		" AND movimentos_contabeis.OID_Origem = 8";

          ResultSet resLocal = this.executasql.executarConsulta (sql);

          if (resLocal.next ()) {
        	  nr_Lote = resLocal.getLong ("NR_Lote");
          }
          edLC.setNR_Lote (nr_Lote);

          if ("S".equals(res.getString ("DM_Principal"))) {

              // boolean dm_encontrou_MCC = false;

              //sql = " SELECT dt_movimento_conta_corrente " +
      	      //  " FROM movimentos_contas_correntes " +
      	      //  " WHERE nr_documento = '" + res.getString ("NR_Duplicata") + "'" +
      	      //  " and oid_tipo_documento = 15 " +
      	      //  " and vl_lancamento = " + res.getDouble ("VL_Pagamento") + " order by dt_movimento_conta_corrente desc ";

              //resLocal = this.executasql.executarConsulta (sql);
              //while (resLocal.next ()) {
              //    edLC.setDT_Lancamento (resLocal.getString ("dt_movimento_conta_corrente"));
              //    dm_encontrou_MCC = true;
             // }

              //if (!dm_encontrou_MCC){
      	      //  sql = " SELECT dt_movimento_conta_corrente " +
      	      //  	" FROM movimentos_contas_correntes " +
      	      //  	" WHERE nr_documento = '" + res.getString ("NR_Duplicata") + "'" +
      	      //  	" and oid_tipo_documento = 15 " + " order by dt_movimento_conta_corrente desc ";

      		  //  resLocal = this.executasql.executarConsulta (sql);
      		  //  while (resLocal.next ()) {
      		  //      edLC.setDT_Lancamento (resLocal.getString ("dt_movimento_conta_corrente"));
      		  //  }
              //}

        	  this.Conta = this.buscaConta ("1002");
        	  OID_Conta = this.Conta.getOid_Conta ().longValue ();
        	  OID_Historico = this.Conta.getOid_Historico ().longValue ();
        	  edLC.setVL_Lancamento (res.getDouble ("VL_Pagamento"));
              edLC.setOID_Conta (OID_Conta);
              edLC.setCD_Historico (OID_Historico);
              edLC.setDM_Acao ("C");
              edLC.setNM_Complementar ("Recebimento Fatura Nr. "+res.getString("NR_Duplicata") + " Cliente "+res.getString("NM_Razao_Social"));
              nr_Lote = this.inclui_Lancamento_Contabil (edLC);

              edLC.setNR_Lote (nr_Lote);
              edLC.setOID_Conta (res.getLong ("oid_conta"));
    	      edLC.setCD_Historico (res.getLong ("oid_historico"));
              edLC.setDM_Acao ("D");
              this.inclui_Lancamento_Contabil (edLC);

          }else{
              if (res.getDouble ("VL_Pagamento") > 0 && res.getLong ("oid_conta_instrucao") > 0) {

	              edLC.setNR_Lote (nr_Lote);
	        	  edLC.setVL_Lancamento (res.getDouble ("VL_Pagamento"));
	              edLC.setNM_Complementar ("Recebimento Fatura Nr. "+res.getString("NR_Duplicata") + " Cliente "+res.getString("NM_Razao_Social"));
	              edLC.setOID_Conta (res.getLong ("oid_conta"));
	    	      edLC.setCD_Historico (res.getLong ("oid_historico"));
	              edLC.setDM_Acao ("C");
	              nr_Lote = this.inclui_Lancamento_Contabil (edLC);

	              edLC.setOID_Conta (res.getLong ("oid_conta_instrucao"));
	              edLC.setCD_Historico (res.getLong ("oid_historico_instrucao"));
	              edLC.setDM_Acao ("D");
	              edLC.setNM_Complementar ("Recebimento Fatura Nr. "+res.getString("NR_Duplicata") + " Cliente "+res.getString("NM_Razao_Social"));
	              this.inclui_Lancamento_Contabil (edLC);

              }
          }

        sql = " UPDATE Movimentos_Duplicatas SET ";
        sql += " DM_Contabilizado = 'S'";
        sql += " WHERE oid_movimento_duplicata = '" + res.getString ("oid_movimento_duplicata") + "'";
        // executasql.executarUpdate (sql);

      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      exc.printStackTrace ();
      excecoes.setMetodo ("inclui lancamentos");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void inclui_CTB_Duplicata (DuplicataED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " SELECT " +
          "      duplicatas.oid_unidade" +
          "     ,duplicatas.oid_Duplicata" +
          "     ,duplicatas.NR_Duplicata" +
          "     ,duplicatas.Vl_Duplicata" +
          "     ,duplicatas.dt_Emissao" +
          "     ,duplicatas.OID_Pessoa as OID_Pessoa_Cliente" +
          "     ,Pessoas.nm_razao_social as nm_cliente " +
          " FROM duplicatas, Pessoas" +
          " WHERE duplicatas.oid_pessoa = Pessoas.oid_Pessoa" +
          // "   and (duplicatas.dm_Contabilizado <> 'S' " +
          // "		or duplicatas.dm_Contabilizado is null)" +
          "   and duplicatas.oid_duplicata = " + ed.getOid_Duplicata ();

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      long OID_Conta = 0;
      long OID_Historico = 0;
      long nr_Lote = 0;
      String NM_Complementar="";
      while (res.next ()) {
        NM_Complementar = "Emissao Fatura Nr.: " + res.getString ("NR_Duplicata") + " Cliente: " + res.getString ("nm_cliente");

        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
        edLC.setOID_Compromisso (0);
        edLC.setOID_Nota_Fiscal ("");
        edLC.setOID_Lote_Pagamento (0);
        edLC.setOID_Lote_Compromisso ("");
        edLC.setOID_Solicitacao ("");
        edLC.setOID_Acerto ("0");
        edLC.setOID_Movimento_Servico ("");
        edLC.setOID_Ordem_Frete ("");
        edLC.setOID_Caixa ("");
        edLC.setOid_movimento_duplicata (res.getString ("oid_Duplicata"));
        edLC.setDM_Stamp (" ");
        edLC.setOID_Conhecimento ("");
        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());

        edLC.setDT_Lancamento (res.getString ("dt_Emissao"));

        edLC.setDT_Stamp (Data.getDataDMY ());
        edLC.setTx_Chave_Origem (res.getString ("oid_Duplicata"));
        edLC.setOID_Origem (9);

        if (res.getDouble ("Vl_Duplicata") > 0) {

          this.Conta = this.buscaConta (this.paramConta.getCd_conta_cliente_geral ());
          OID_Conta = this.Conta.getOid_Conta ().longValue ();
          OID_Historico = this.Conta.getOid_Historico ().longValue ();
          edLC.setVL_Lancamento (res.getDouble ("vl_duplicata"));
          edLC.setOID_Conta (OID_Conta);
          edLC.setCD_Historico (OID_Historico);
          edLC.setDM_Acao ("C");
          edLC.setNM_Complementar (NM_Complementar);
          edLC.setNR_Lote (0);
          edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

          this.Conta = this.buscaConta ("1002");
          OID_Conta = this.Conta.getOid_Conta ().longValue ();
          OID_Historico = this.Conta.getOid_Historico ().longValue ();

          edLC.setVL_Lancamento (res.getDouble ("vl_duplicata"));
          edLC.setOID_Conta (OID_Conta);
          edLC.setCD_Historico (OID_Historico);
          edLC.setDM_Acao ("D");
          edLC.setNM_Complementar (NM_Complementar);
          this.inclui_Lancamento_Contabil (edLC);

        }

	    sql = " UPDATE Duplicatas SET ";
        sql += " DM_Contabilizado = 'S'";
        sql += " WHERE oid_duplicata = " + res.getString ("oid_duplicata");
        // executasql.executarUpdate (sql);

      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      exc.printStackTrace ();
      excecoes.setMetodo ("inclui lancamentos");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void inclui_CTB_Duplicata_Cancelada (DuplicataED ed) throws Excecoes {

	    String sql = null;

	    try {

	      sql = " SELECT " +
	          "      duplicatas.oid_unidade" +
	          "     ,duplicatas.oid_Duplicata" +
	          "     ,duplicatas.NR_Duplicata" +
	          "     ,duplicatas.Vl_Duplicata" +
	          "     ,duplicatas.dt_cancelamento" +
	          "     ,duplicatas.OID_Pessoa as OID_Pessoa_Cliente" +
	          "     ,Pessoas.nm_razao_social as nm_cliente " +
	          " FROM duplicatas, Pessoas" +
	          " WHERE duplicatas.oid_pessoa = Pessoas.oid_Pessoa" +
	          "   and duplicatas.oid_duplicata = " + ed.getOid_Duplicata ();

	      ResultSet res = null;
	      res = this.executasql.executarConsulta (sql);
	      long OID_Conta = 0;
	      long OID_Historico = 0;
	      long nr_Lote = 0;
	      String NM_Complementar="";
	      while (res.next ()) {
	        NM_Complementar = "Cancelamento Fatura Nr.: " + res.getString ("NR_Duplicata") + " Cliente: " + res.getString ("nm_cliente");

	        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
	        edLC.setOID_Compromisso (0);
	        edLC.setOID_Nota_Fiscal ("");
	        edLC.setOID_Lote_Pagamento (0);
	        edLC.setOID_Lote_Compromisso ("");
	        edLC.setOID_Solicitacao ("");
	        edLC.setOID_Acerto ("0");
	        edLC.setOID_Movimento_Servico ("");
	        edLC.setOID_Ordem_Frete ("");
	        edLC.setOID_Caixa ("");
	        edLC.setOid_movimento_duplicata (res.getString ("oid_Duplicata"));
	        edLC.setDM_Stamp (" ");
	        edLC.setOID_Conhecimento ("");
	        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());

	        edLC.setDT_Lancamento (res.getString ("dt_cancelamento"));

	        edLC.setDT_Stamp (Data.getDataDMY ());
	        edLC.setTx_Chave_Origem (res.getString ("oid_Duplicata"));
	        edLC.setOID_Origem (9);

	        if (res.getDouble ("Vl_Duplicata") > 0) {

	          this.Conta = this.buscaConta (this.paramConta.getCd_conta_cliente_geral ());
	          OID_Conta = this.Conta.getOid_Conta ().longValue ();
	          OID_Historico = this.Conta.getOid_Historico ().longValue ();
	          edLC.setVL_Lancamento (res.getDouble ("vl_duplicata"));
	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setDM_Acao ("D");
	          edLC.setNM_Complementar (NM_Complementar);
	          edLC.setNR_Lote (0);
	          edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

	          this.Conta = this.buscaConta ("1002");
	          OID_Conta = this.Conta.getOid_Conta ().longValue ();
	          OID_Historico = this.Conta.getOid_Historico ().longValue ();

	          edLC.setVL_Lancamento (res.getDouble ("vl_duplicata"));
	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setDM_Acao ("C");
	          edLC.setNM_Complementar (NM_Complementar);
	          this.inclui_Lancamento_Contabil (edLC);

	        }
	      }

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      exc.printStackTrace ();
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void inclui_Fatura_Compromisso (Fatura_CompromissoED ed) throws Excecoes {

	    String sql = null;

	    try {

	      sql = " SELECT " +
	          "      faturas_compromissos.oid_unidade" +
	          "     ,faturas_compromissos.oid_fatura_compromisso" +
	          "     ,faturas_compromissos.nr_documento" +
	          "     ,faturas_compromissos.vl_fatura_compromisso" +
	          "     ,faturas_compromissos.dt_Emissao" +
	          "     ,faturas_compromissos.OID_Pessoa as OID_Pessoa_Posto" +
	          "     ,Compromissos.oid_Conta" +
	          "     ,Contas.oid_Historico" +
	          "     ,Pessoas.nm_razao_social as nm_Pessoa_Posto " +
	          " FROM faturas_compromissos, Pessoas, Compromissos, contas" +
	          " WHERE faturas_compromissos.oid_pessoa = Pessoas.oid_Pessoa" +
	          " AND faturas_compromissos.oid_Compromisso = Compromissos.oid_Compromisso " +
	          " AND compromissos.oid_Conta = Contas.oid_Conta " +
	          " AND faturas_compromissos.vl_fatura_compromisso > 0 " +
	          "   and faturas_compromissos.oid_fatura_compromisso = " + ed.getOid_Fatura_Compromisso();

	      //System.out.println(sql);

	      ResultSet res = null;
	      res = this.executasql.executarConsulta (sql);
	      long OID_Conta = 0;
	      long OID_Historico = 0;
	      long nr_Lote = 0;
	      String NM_Complementar="";
	      while (res.next ()) {
	        NM_Complementar = "Vincula Nr.: " + res.getString ("nr_documento") + " Fornecedor: " + res.getString ("nm_Pessoa_Posto");

	        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
	        edLC.setOID_Compromisso (0);
	        edLC.setOID_Nota_Fiscal ("");
	        edLC.setOID_Lote_Pagamento (0);
	        edLC.setOID_Lote_Compromisso ("");
	        edLC.setOID_Solicitacao ("");
	        edLC.setOID_Acerto ("0");
	        edLC.setOID_Movimento_Servico ("");
	        edLC.setOID_Ordem_Frete ("");
	        edLC.setOID_Caixa ("");
	        edLC.setOid_movimento_duplicata ("");
	        edLC.setOid_fatura_compromisso(res.getLong("oid_fatura_compromisso"));
	        edLC.setDM_Stamp (" ");
	        edLC.setOID_Conhecimento ("");
	        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());

	        edLC.setDT_Lancamento (res.getString ("dt_Emissao"));

	        edLC.setDT_Stamp (Data.getDataDMY ());
	        edLC.setTx_Chave_Origem (res.getString ("oid_fatura_compromisso"));
	        edLC.setOID_Origem (107);

	        if (res.getDouble ("vl_fatura_compromisso") > 0) {

	          OID_Conta = res.getLong("oid_Conta");
	          OID_Historico = res.getLong("oid_Historico");
	          edLC.setVL_Lancamento (res.getDouble ("vl_fatura_compromisso"));
	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setDM_Acao ("C");
	          edLC.setNM_Complementar (NM_Complementar);
	          edLC.setNR_Lote (0);
	          edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

	          sql = " SELECT contas.oid_Conta, contas.oid_Historico, compromissos.oid_Pessoa, compromissos.VL_Compromisso, " +
	          		" compromissos.NR_Documento, Pessoas.NM_Razao_Social " +
	            " FROM compromissos, contas, Pessoas " +
	            " WHERE compromissos.oid_Conta = Contas.oid_Conta " +
	            " AND compromissos.oid_Pessoa = Pessoas.oid_Pessoa " +
	            " AND compromissos.oid_fatura_compromisso = " + res.getLong("oid_fatura_compromisso");

		      ResultSet resLocal = this.executasql.executarConsulta (sql);

		      while (resLocal.next ()) {

		    	  if(resLocal.getLong("oid_Conta") == 98){
			          this.Conta = this.buscaConta ("11001");
			          OID_Conta = this.Conta.getOid_Conta ().longValue ();
			          OID_Historico = this.Conta.getOid_Historico ().longValue ();
			          edLC.setOID_Conta (OID_Conta);
			          edLC.setCD_Historico (OID_Historico);
		    	  }else if(resLocal.getLong("oid_Conta") == 99){
			          this.Conta = this.buscaConta ("11002");
			          OID_Conta = this.Conta.getOid_Conta ().longValue ();
			          OID_Historico = this.Conta.getOid_Historico ().longValue ();
			          edLC.setOID_Conta (OID_Conta);
			          edLC.setCD_Historico (OID_Historico);
		    	  }else{
			    	  edLC.setOID_Conta (resLocal.getLong("oid_Conta"));
			          edLC.setCD_Historico (resLocal.getLong("oid_Historico"));
		    	  }

		          edLC.setVL_Lancamento (resLocal.getDouble ("VL_Compromisso"));
		          edLC.setDM_Acao ("D");
			      NM_Complementar = "Vincula Nr.: " + res.getString ("nr_documento") + " Fornecedor: " + res.getString ("nm_Pessoa_Posto") + " S/ OF Nr. " + resLocal.getString ("nr_documento") + " Fornecedor: " + resLocal.getString ("NM_Razao_Social");
		          edLC.setNM_Complementar (NM_Complementar);
		          this.inclui_Lancamento_Contabil (edLC);
		      }
	        }
	      }

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      exc.printStackTrace ();
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void contabiliza_Depreciacao_Patrimonial (Importacao_ContabilED ed) throws Excecoes {

	    String sql = null;

	    try {

            sql = " select sum(Vl_depreciacao_mes) as Vl_depreciacao_mes, " +
		    	  " produtos_patrimonios.oid_conta as oid_conta_devedora, " +
		    	  " contas.oid_historico as oid_historico_devedor, " +
		    	  " categorias.oid_conta as oid_conta_credora, " +
		    	  " con_cat.oid_historico as oid_historico_credor, " +
		    	  " categorias.oid_categoria, " +
		    	  " categorias.nm_categoria  " +
		    	  " from Depreciacoes_Meses, Depreciacoes, produtos_patrimonios, categorias, contas, contas con_cat  " +
		    	  " WHERE Depreciacoes_Meses.oid_depreciacao = Depreciacoes.oid_depreciacao " +
		    	  " and depreciacoes.oid_produto_patrimonio = produtos_patrimonios.oid_produto_patrimonio " +
		    	  " and produtos_patrimonios.oid_categoria = categorias.oid_categoria " +
		    	  " and produtos_patrimonios.oid_conta = contas.oid_conta " +
		    	  " and categorias.oid_conta = con_cat.oid_conta " +
				  " AND Depreciacoes_Meses.DM_Situacao = 'L'" +
		          " AND Depreciacoes_Meses.dt_depreciacao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
		          " AND Depreciacoes_Meses.dt_depreciacao <= '" + ed.getDT_Lancamento_Final () + "'";
            sql +=" group by produtos_patrimonios.oid_conta, categorias.oid_categoria, categorias.nm_categoria, categorias.oid_conta, contas.oid_historico, con_cat.oid_historico ";
            sql +=" ORDER BY produtos_patrimonios.oid_conta, categorias.oid_categoria, categorias.nm_categoria, categorias.oid_conta, contas.oid_historico, con_cat.oid_historico  ";

	      //System.out.println(sql);

	      ResultSet res = null;
	      res = this.executasql.executarConsulta (sql);
	      long OID_Conta = 0;
	      long OID_Historico = 0;
	      long nr_Lote = 0;
	      String NM_Complementar="";
	      while (res.next ()) {
	        NM_Complementar = "DEPRECIACAO CATEGORIA.: " + res.getString ("nm_categoria");

	        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
	        edLC.setOID_Compromisso (0);
	        edLC.setOID_Nota_Fiscal ("");
	        edLC.setOID_Lote_Pagamento (0);
	        edLC.setOID_Lote_Compromisso ("");
	        edLC.setOID_Solicitacao ("");
	        edLC.setOID_Acerto ("0");
	        edLC.setOID_Movimento_Servico ("");
	        edLC.setOID_Ordem_Frete ("");
	        edLC.setOID_Caixa ("");
	        edLC.setOid_movimento_duplicata ("");
	        edLC.setOid_fatura_compromisso(0);
	        edLC.setDM_Stamp (" ");
	        edLC.setOID_Conhecimento ("");
	        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());

	        edLC.setDT_Lancamento (ed.getDT_Lancamento_Inicial ());

	        edLC.setDT_Stamp (Data.getDataDMY ());
	        edLC.setTx_Chave_Origem (res.getString ("oid_categoria"));
	        edLC.setOID_Origem (110);

	        if (res.getDouble ("Vl_depreciacao_mes") > 0) {

	          edLC.setVL_Lancamento (res.getDouble ("Vl_depreciacao_mes"));
	          edLC.setNM_Complementar (NM_Complementar);

	          OID_Conta = res.getLong("oid_conta_credora");
	          OID_Historico = res.getLong("oid_historico_credor");
	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setDM_Acao ("C");
	          edLC.setNR_Lote (0);
	          edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

	          OID_Conta = res.getLong("oid_conta_devedora");
	          OID_Historico = res.getLong("oid_historico_devedor");
	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setDM_Acao ("D");
      		  this.inclui_Lancamento_Contabil (edLC);

	        }
	      }
	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      exc.printStackTrace ();
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void contabiliza_Venda_Patrimonial (Importacao_ContabilED ed) throws Excecoes {

	    String sql = null;

	    try {

          sql = " select Depreciacoes.vl_venda as vl_venda, " +
          		  " Depreciacoes.vl_total_depreciacao as vl_total_depreciacao, " +
          		  " Depreciacoes.vl_original as vl_original, " +
          		  " Depreciacoes.dt_venda as dt_venda, " +
          		  " Depreciacoes.tx_observacao as tx_observacao, " +
          		  " Depreciacoes.oid_depreciacao as oid_depreciacao, " +
          		  " produtos_patrimonios.nm_produto_patrimonio, " +

          		  " conta_credora_venda.oid_Conta as oid_conta_credora_venda, " +
          		  " conta_devedora_venda.oid_Conta as oid_conta_devedora_venda, " +
          		  " conta_credora_baixa_depreciacao.oid_Conta as oid_conta_credora_baixa_depreciacao, " +
          		  " conta_devedora_baixa_depreciacao.oid_Conta as oid_conta_devedora_baixa_depreciacao, " +
          		  " conta_credora_ganho_venda.oid_Conta as oid_conta_credora_ganho_venda, " +
          		  " conta_devedora_ganho_venda.oid_Conta as oid_conta_devedora_ganho_venda, " +
          		  " conta_credora_perda_venda.oid_Conta as oid_conta_credora_perda_venda, " +
          		  " conta_devedora_perda_venda.oid_Conta as oid_conta_devedora_perda_venda, " +

          		  " conta_credora_venda.oid_Historico as oid_Historico_credora_venda, " +
          		  " conta_devedora_venda.oid_Historico as oid_Historico_devedora_venda, " +
          		  " conta_credora_baixa_depreciacao.oid_Historico as oid_Historico_credora_baixa_depreciacao, " +
          		  " conta_devedora_baixa_depreciacao.oid_Historico as oid_Historico_devedora_baixa_depreciacao, " +
          		  " conta_credora_ganho_venda.oid_Historico as oid_Historico_credora_ganho_venda, " +
          		  " conta_devedora_ganho_venda.oid_Historico as oid_Historico_devedora_ganho_venda, " +
          		  " conta_credora_perda_venda.oid_Historico as oid_Historico_credora_perda_venda, " +
          		  " conta_devedora_perda_venda.oid_Historico as oid_Historico_devedora_perda_venda " +

          		  " from Depreciacoes, produtos_patrimonios, categorias, " +
		    	  " contas conta_credora_venda, " +
		    	  " contas conta_devedora_venda, " +
		    	  " contas conta_credora_baixa_depreciacao, " +
		    	  " contas conta_devedora_baixa_depreciacao, " +
		    	  " contas conta_credora_ganho_venda, " +
		    	  " contas conta_devedora_ganho_venda, " +
		    	  " contas conta_credora_perda_venda, " +
		    	  " contas conta_devedora_perda_venda " +
		    	  " WHERE Depreciacoes.oid_produto_patrimonio = produtos_patrimonios.oid_produto_patrimonio " +
		    	  " and produtos_patrimonios.oid_categoria = categorias.oid_categoria " +

                  " and categorias.oid_conta_credora_venda = conta_credora_venda.oid_conta " +
                  " and categorias.oid_conta_devedora_venda = conta_devedora_venda.oid_conta " +
                  " and categorias.oid_conta_credora_baixa_depreciacao = conta_credora_baixa_depreciacao.oid_conta " +
                  " and categorias.oid_conta_devedora_baixa_depreciacao = conta_devedora_baixa_depreciacao.oid_conta " +
                  " and categorias.oid_conta_credora_ganho_venda = conta_credora_ganho_venda.oid_conta " +
                  " and categorias.oid_conta_devedora_ganho_venda = conta_devedora_ganho_venda.oid_conta " +
                  " and categorias.oid_conta_credora_perda_venda = conta_credora_perda_venda.oid_conta " +
                  " and categorias.oid_conta_devedora_perda_venda = conta_devedora_perda_venda.oid_conta " +

		    	  " AND Depreciacoes.DM_Situacao = 'V'" +
		          " AND Depreciacoes.dt_venda >= '" + ed.getDT_Lancamento_Inicial () + "'" +
		          " AND Depreciacoes.dt_venda <= '" + ed.getDT_Lancamento_Final () + "'";
          sql +=" ORDER BY Depreciacoes.dt_venda ";

	      ResultSet res = null;
	      res = this.executasql.executarConsulta (sql);
	      long OID_Conta = 0;
	      long OID_Historico = 0;
	      long nr_Lote = 0;
	      String NM_Complementar="";
	      while (res.next ()) {
	        NM_Complementar = "VENDA PATRIMONIAL.: " + res.getString ("nm_produto_patrimonio") + "-" +res.getString ("tx_observacao");

	        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
	        edLC.setOID_Compromisso (0);
	        edLC.setOID_Nota_Fiscal ("");
	        edLC.setOID_Lote_Pagamento (0);
	        edLC.setOID_Lote_Compromisso ("");
	        edLC.setOID_Solicitacao ("");
	        edLC.setOID_Acerto ("0");
	        edLC.setOID_Movimento_Servico ("");
	        edLC.setOID_Ordem_Frete ("");
	        edLC.setOID_Caixa ("");
	        edLC.setOid_movimento_duplicata ("");
	        edLC.setOid_fatura_compromisso(0);
	        edLC.setDM_Stamp (" ");
	        edLC.setOID_Conhecimento ("");
	        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());

	        edLC.setDT_Lancamento (res.getString ("dt_venda"));

	        edLC.setDT_Stamp (Data.getDataDMY ());
	        edLC.setTx_Chave_Origem (res.getString ("oid_depreciacao"));
	        edLC.setOID_Origem (111);

	        if (res.getDouble ("vl_original") > 0) {
	          edLC.setVL_Lancamento (res.getDouble ("vl_original"));
	          edLC.setNM_Complementar (NM_Complementar);
	          OID_Conta = res.getLong("oid_conta_credora_venda");
	          OID_Historico = res.getLong("oid_historico_credora_venda");
	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setDM_Acao ("C");
	          edLC.setNR_Lote (0);
	          edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

	          OID_Conta = res.getLong("oid_conta_devedora_venda");
	          OID_Historico = res.getLong("oid_historico_devedora_venda");
	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setDM_Acao ("D");
    		  this.inclui_Lancamento_Contabil (edLC);
	        }

	        if (res.getDouble ("vl_total_depreciacao") > 0) {
	          edLC.setVL_Lancamento (res.getDouble ("vl_total_depreciacao"));
	          edLC.setNM_Complementar (NM_Complementar);
	          OID_Conta = res.getLong("oid_conta_credora_baixa_depreciacao");
	          OID_Historico = res.getLong("oid_Historico_credora_baixa_depreciacao");
	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setDM_Acao ("C");
	          edLC.setNR_Lote (0);
	          edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

	          OID_Conta = res.getLong("oid_conta_devedora_baixa_depreciacao");
	          OID_Historico = res.getLong("oid_Historico_devedora_baixa_depreciacao");
	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setDM_Acao ("D");
    		  this.inclui_Lancamento_Contabil (edLC);
	        }

	        double vl_saldo = res.getDouble ("vl_original") - res.getDouble ("vl_total_depreciacao") - res.getDouble ("vl_venda");

	        if (vl_saldo < 0){

	           vl_saldo = vl_saldo * -1;

	           edLC.setVL_Lancamento (vl_saldo);
	           edLC.setNM_Complementar (NM_Complementar);
	           OID_Conta = res.getLong("oid_conta_credora_ganho_venda");
	           OID_Historico = res.getLong("oid_Historico_credora_ganho_venda");
	           edLC.setOID_Conta (OID_Conta);
	           edLC.setCD_Historico (OID_Historico);
	           edLC.setDM_Acao ("C");
	           edLC.setNR_Lote (0);
	           edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

	           OID_Conta = res.getLong("oid_conta_devedora_ganho_venda");
	           OID_Historico = res.getLong("oid_Historico_devedora_ganho_venda");
	           edLC.setOID_Conta (OID_Conta);
	           edLC.setCD_Historico (OID_Historico);
	           edLC.setDM_Acao ("D");
    		   this.inclui_Lancamento_Contabil (edLC);

	        }else if (vl_saldo > 0){

	           edLC.setVL_Lancamento (vl_saldo);
	           edLC.setNM_Complementar (NM_Complementar);
	           OID_Conta = res.getLong("oid_conta_credora_perda_venda");
	           OID_Historico = res.getLong("oid_Historico_credora_perda_venda");
	           edLC.setOID_Conta (OID_Conta);
	           edLC.setCD_Historico (OID_Historico);
	           edLC.setDM_Acao ("C");
	           edLC.setNR_Lote (0);
	           edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

	           OID_Conta = res.getLong("oid_conta_devedora_perda_venda");
	           OID_Historico = res.getLong("oid_Historico_devedora_perda_venda");
	           edLC.setOID_Conta (OID_Conta);
	           edLC.setCD_Historico (OID_Historico);
	           edLC.setDM_Acao ("D");
    		   this.inclui_Lancamento_Contabil (edLC);
	        }
	      }
	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      exc.printStackTrace ();
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void inclui_CTB_Conhecimento (ConhecimentoED ed) throws Excecoes {

	    String sql = null;
	    ResultSet resLocal = null;
	    String NM_Complementar = null;

	    try {

	      sql = " SELECT Conhecimentos.oid_conhecimento" +
	          "     ,Conhecimentos.nr_conhecimento " +
	          "     ,Conhecimentos.oid_unidade" +
	          "     ,Conhecimentos.oid_pessoa_pagador as OID_Pessoa_Cliente" +
	          "     ,Conhecimentos.dt_Emissao" +
	          "     ,Conhecimentos.VL_TOTAL_FRETE" +
	          "     ,Conhecimentos.VL_PIS" +
	          "     ,Conhecimentos.VL_COFINS" +
	          "     ,Conhecimentos.VL_ICMS" +
	          "     ,Conhecimentos.VL_Custo_Seguro" +
	          "     ,Conhecimentos.VL_Pedagio" +
	          "     ,Pessoas.NM_Razao_Social as nm_cliente" +
	          " FROM Conhecimentos" +
	          "     ,Pessoas" +
	          " WHERE Conhecimentos.oid_pessoa_pagador = Pessoas.oid_pessoa " +
	          "   AND Conhecimentos.dm_tipo_documento = 'C'" +
	          // "   and (Conhecimentos.dm_Contabilizado <> 'S' " +
	          // "		or Conhecimentos.dm_Contabilizado is null)" +
	          "   and Conhecimentos.oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

	      ResultSet res = null;
	      res = this.executasql.executarConsulta (sql);

	      while (res.next ()) {
	        NM_Complementar = "Emissao CTRC Nr.: " + res.getString ("nr_conhecimento") + " Cliente: " + res.getString ("nm_cliente");

	        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
	        edLC.setOID_Compromisso (0);
	        edLC.setOID_Nota_Fiscal ("");
	        edLC.setOID_Lote_Pagamento (0);
	        edLC.setOID_Lote_Compromisso ("");
	        edLC.setOID_Solicitacao ("");
	        edLC.setOID_Acerto ("0");
	        edLC.setOID_Movimento_Servico ("");
	        edLC.setOID_Ordem_Frete ("");
	        edLC.setOID_Caixa ("");
	        edLC.setOid_movimento_duplicata ("");
	        edLC.setDM_Stamp (" ");

	        edLC.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
	        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());

	        edLC.setDT_Lancamento (res.getString ("dt_Emissao"));
	        edLC.setDT_Stamp (Data.getDataDMY ());
	        edLC.setTx_Chave_Origem (String.valueOf (ed.getOID_Conhecimento ()));
	        edLC.setOID_Origem (2);

	        if (res.getDouble ("vl_total_frete") > 0) {
	          long OID_Conta , OID_Historico;
	          this.Conta = this.buscaConta (this.paramConta.getCd_conta_cliente_geral ());
	          OID_Conta = this.Conta.getOid_Conta ().longValue ();
	          OID_Historico = this.Conta.getOid_Historico ().longValue ();
	          //}
	          edLC.setVL_Lancamento (res.getDouble ("vl_total_frete"));
	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setDM_Acao ("D");
	          edLC.setNM_Complementar (NM_Complementar);
	          edLC.setNR_Lote (0);
	          edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

	          //Cr�dito faturamento
	          sql = "SELECT " +
	                "contas.oid_historico, " +
	                "contas.oid_conta " +
	                "FROM " +
	                "contas, " +
	                "unidades " +
	                "WHERE " +
	                "unidades.OID_Unidade = " + res.getLong ("OID_Unidade") + " and " +
	                "unidades.oid_conta = contas.oid_conta "; //conta de cr�dito

	                resLocal = this.executasql.executarConsulta (sql);
	                OID_Conta = 0;
	                OID_Historico = 0;
	          while (resLocal.next ()) {
	            OID_Conta = resLocal.getLong ("oid_conta");
	            OID_Historico = resLocal.getLong ("oid_historico");
	          }

	          edLC.setVL_Lancamento (res.getDouble ("vl_total_frete") - res.getDouble ("vl_pedagio")); // Retira o ped�gio
	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setDM_Acao ("C");
	          edLC.setNM_Complementar (NM_Complementar);
	          this.inclui_Lancamento_Contabil (edLC);

	          //Ped�gio - � contabilizado a parte - � retirado do valor do conhecimento para a conta de venda de frete
	          if (res.getDouble ("vl_pedagio") > 0) {
	            this.Conta = this.buscaConta (this.paramConta.getCd_conta_pedagio ()); // Cd_conta_a_faturar se contabilizar a venda d-cliente c-venda
	            OID_Conta = this.Conta.getOid_Conta ().longValue ();
	            OID_Historico = this.Conta.getOid_Historico ().longValue ();
	            edLC.setVL_Lancamento (res.getDouble ("vl_pedagio")); // Vl do ped�gio
	            edLC.setOID_Conta (OID_Conta);
	            edLC.setCD_Historico (OID_Historico);
	            edLC.setDM_Acao ("C");
		        NM_Complementar = "Pedagio S/ CTRC Nr.: " + res.getString ("nr_conhecimento") + " Cliente: " + res.getString ("nm_cliente");
	            edLC.setNM_Complementar (NM_Complementar);
	            this.inclui_Lancamento_Contabil (edLC);
	          }
	        }

	        if (res.getDouble ("VL_ICMS") > 0) {
	          //Cr�dito ICMS
	          sql = "SELECT " +
	            "contas.oid_historico, " +
	            "contas.oid_conta " +
	            "FROM " +
	            "contas, " +
	            "unidades " +
	            "WHERE " +
	            "unidades.OID_Unidade = " + res.getLong ("OID_Unidade") + " and " +
	            "unidades.oid_conta_credito_icms = contas.oid_conta "; //conta de cr�dito

	          resLocal = this.executasql.executarConsulta (sql);
	          long OID_Conta = 0;
	          long OID_Historico = 0;
	          while (resLocal.next ()) {
	           	OID_Conta = resLocal.getLong ("oid_conta");
	           	OID_Historico = resLocal.getLong ("oid_historico");
	          }

	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setVL_Lancamento (res.getDouble ("VL_ICMS"));
	          edLC.setDM_Acao ("C");
		      NM_Complementar = "ICMS S/ CTRC Nr.: " + res.getString ("nr_conhecimento") + " Cliente: " + res.getString ("nm_cliente");
	          edLC.setNM_Complementar (NM_Complementar);
	          this.inclui_Lancamento_Contabil (edLC);
	          //D�bito ICMS

	          sql = "SELECT " +
	            "contas.oid_historico, " +
	            "contas.oid_conta " +
	            "FROM " +
	            "contas, " +
	            "unidades " +
	            "WHERE " +
	            "unidades.OID_Unidade = " + res.getLong ("OID_Unidade") + " and " +
	            "unidades.oid_conta_debito_icms = contas.oid_conta "; //conta de cr�dito

	          resLocal = this.executasql.executarConsulta (sql);
	          OID_Conta = 0;
	          OID_Historico = 0;
	          while (resLocal.next ()) {
	            OID_Conta = resLocal.getLong ("oid_conta");
	            OID_Historico = resLocal.getLong ("oid_historico");
	          }

	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setVL_Lancamento (res.getDouble ("VL_ICMS"));
	          edLC.setDM_Acao ("D");
		      NM_Complementar = "ICMS S/ CTRC Nr.: " + res.getString ("nr_conhecimento") + " Cliente: " + res.getString ("nm_cliente");
	          edLC.setNM_Complementar (NM_Complementar);
	          this.inclui_Lancamento_Contabil (edLC);
	        }

	        // sql = "SELECT vl_movimento from Movimentos_Conhecimentos " +
	        //		" where oid_Tipo_Movimento = 9 " +
	        //		" and oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";

            // resLocal = this.executasql.executarConsulta (sql);
             double vl_movimento = 0;
	        // while (resLocal.next ()) {
	        //	vl_movimento = resLocal.getDouble ("vl_movimento");
	        // }

	        if (vl_movimento > 0 && 1==2) {
	          //Cr�dito PIS
	          if (JavaUtil.doValida (this.paramConta.getCd_conta_pis_credito ())) {
	            this.Conta = this.buscaConta (this.paramConta.getCd_conta_pis_credito ());
	            edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	            edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	            edLC.setVL_Lancamento (vl_movimento);
	            edLC.setDM_Acao ("C");
		        NM_Complementar = "PIS S/ CTRC Nr.: " + res.getString ("nr_conhecimento") + " Cliente: " + res.getString ("nm_cliente");
	            edLC.setNM_Complementar (NM_Complementar);
	            this.inclui_Lancamento_Contabil (edLC);
	            //D�bito PIS
	            this.Conta = this.buscaConta (this.paramConta.getCd_conta_pis_debito ());
	            edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	            edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	            edLC.setVL_Lancamento (vl_movimento);
	            edLC.setDM_Acao ("D");
	            edLC.setNM_Complementar (NM_Complementar);
	            this.inclui_Lancamento_Contabil (edLC);
	          }
	        }

	        // sql = "SELECT vl_movimento from Movimentos_Conhecimentos " +
    		// " where oid_Tipo_Movimento = 10 " +
    		// " and oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";

		    // resLocal = this.executasql.executarConsulta (sql);
		    // vl_movimento = 0;
		    // while (resLocal.next ()) {
		    //	 vl_movimento = resLocal.getDouble ("vl_movimento");
		    // }

		    if (vl_movimento > 0 && 1==2) {
	          //Cr�dito COFINS
	          if (JavaUtil.doValida (this.paramConta.getCd_conta_cofins_credito ())) {
	            this.Conta = this.buscaConta (this.paramConta.getCd_conta_cofins_credito ());
	            edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	            edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	            edLC.setVL_Lancamento (vl_movimento);
	            edLC.setDM_Acao ("C");
		        NM_Complementar = "COFINS S/ CTRC Nr.: " + res.getString ("nr_conhecimento") + " Cliente: " + res.getString ("nm_cliente");
	            edLC.setNM_Complementar (NM_Complementar);
	            this.inclui_Lancamento_Contabil (edLC);
	            //D�bito COFINS
	            this.Conta = this.buscaConta (this.paramConta.getCd_conta_cofins_debito ());
	            edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	            edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	            edLC.setVL_Lancamento (vl_movimento);
	            edLC.setDM_Acao ("D");
	            edLC.setNM_Complementar (NM_Complementar);
	            this.inclui_Lancamento_Contabil (edLC);
	          }
	        }

	        sql = " UPDATE Conhecimentos SET ";
	        sql += " DM_Contabilizado = 'S'";
	        sql += " WHERE oid_Conhecimento = '" + res.getString ("OID_Conhecimento") + "'";
	        // executasql.executarUpdate (sql);
	      }

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setMensagem (exc.getMessage ());
	      excecoes.setClasse (this.getClass ().getName ());
	      exc.printStackTrace ();
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;

	    }
	  }

  public void inclui_CTB_Minuta (ConhecimentoED ed) throws Excecoes {

	    String sql = null;
	    ResultSet resLocal = null;
	    String NM_Complementar = null;

	    try {

	      sql = " SELECT Conhecimentos.oid_conhecimento" +
	          "     ,Conhecimentos.nr_recibo as nr_conhecimento " +
	          "     ,Conhecimentos.oid_unidade" +
	          "     ,Conhecimentos.oid_pessoa_pagador as OID_Pessoa_Cliente" +
	          "     ,Conhecimentos.dt_Emissao" +
	          "     ,Conhecimentos.VL_TOTAL_FRETE" +
	          "     ,Conhecimentos.VL_PIS" +
	          "     ,Conhecimentos.VL_COFINS" +
	          "     ,Conhecimentos.VL_ICMS" +
	          "     ,Conhecimentos.VL_Custo_Seguro" +
	          "     ,Conhecimentos.VL_Pedagio" +
	          "     ,Pessoas.NM_Razao_Social as nm_cliente" +
	          " FROM Conhecimentos" +
	          "     ,Pessoas" +
	          " WHERE Conhecimentos.oid_pessoa_pagador = Pessoas.oid_pessoa " +
	          "   AND Conhecimentos.dm_tipo_documento = 'M'" +
	          // "   and (Conhecimentos.dm_Contabilizado <> 'S' " +
	          // "		or Conhecimentos.dm_Contabilizado is null)" +
	          "   and Conhecimentos.oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

	      ResultSet res = null;
	      res = this.executasql.executarConsulta (sql);

	      while (res.next ()) {
	        NM_Complementar = "Emissao RECIBO Nr.: " + res.getString ("nr_conhecimento") + " Cliente: " + res.getString ("nm_cliente");

	        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
	        edLC.setOID_Compromisso (0);
	        edLC.setOID_Nota_Fiscal ("");
	        edLC.setOID_Lote_Pagamento (0);
	        edLC.setOID_Lote_Compromisso ("");
	        edLC.setOID_Solicitacao ("");
	        edLC.setOID_Acerto ("0");
	        edLC.setOID_Movimento_Servico ("");
	        edLC.setOID_Ordem_Frete ("");
	        edLC.setOID_Caixa ("");
	        edLC.setOid_movimento_duplicata ("");
	        edLC.setDM_Stamp (" ");

	        edLC.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
	        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());

	        edLC.setDT_Lancamento (res.getString ("dt_Emissao"));
	        edLC.setDT_Stamp (Data.getDataDMY ());
	        edLC.setTx_Chave_Origem (String.valueOf (ed.getOID_Conhecimento ()));
	        edLC.setOID_Origem (2);

	        if (res.getDouble ("vl_total_frete") > 0) {
	          long OID_Conta , OID_Historico;
	          this.Conta = this.buscaConta (this.paramConta.getCd_conta_cliente_geral ());
	          OID_Conta = this.Conta.getOid_Conta ().longValue ();
	          OID_Historico = this.Conta.getOid_Historico ().longValue ();
	          //}
	          edLC.setVL_Lancamento (res.getDouble ("vl_total_frete"));
	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setDM_Acao ("D");
	          edLC.setNM_Complementar (NM_Complementar);
	          edLC.setNR_Lote (0);
	          edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

	          //Cr�dito faturamento
	          sql = "SELECT " +
	                "contas.oid_historico, " +
	                "contas.oid_conta " +
	                "FROM " +
	                "contas, " +
	                "unidades " +
	                "WHERE " +
	                "unidades.OID_Unidade = " + res.getLong ("OID_Unidade") + " and " +
	                "unidades.oid_conta = contas.oid_conta "; //conta de cr�dito

	                resLocal = this.executasql.executarConsulta (sql);
	                OID_Conta = 0;
	                OID_Historico = 0;
	          while (resLocal.next ()) {
	            OID_Conta = resLocal.getLong ("oid_conta");
	            OID_Historico = resLocal.getLong ("oid_historico");
	          }

	          edLC.setVL_Lancamento (res.getDouble ("vl_total_frete") - res.getDouble ("vl_pedagio")); // Retira o ped�gio
	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setDM_Acao ("C");
	          edLC.setNM_Complementar (NM_Complementar);
	          this.inclui_Lancamento_Contabil (edLC);

	          //Ped�gio - � contabilizado a parte - � retirado do valor do conhecimento para a conta de venda de frete
	          if (res.getDouble ("vl_pedagio") > 0) {
	            this.Conta = this.buscaConta (this.paramConta.getCd_conta_pedagio ()); // Cd_conta_a_faturar se contabilizar a venda d-cliente c-venda
	            OID_Conta = this.Conta.getOid_Conta ().longValue ();
	            OID_Historico = this.Conta.getOid_Historico ().longValue ();
	            edLC.setVL_Lancamento (res.getDouble ("vl_pedagio")); // Vl do ped�gio
	            edLC.setOID_Conta (OID_Conta);
	            edLC.setCD_Historico (OID_Historico);
	            edLC.setDM_Acao ("C");
		        NM_Complementar = "Pedagio S/ RECIBO Nr.: " + res.getString ("nr_conhecimento") + " Cliente: " + res.getString ("nm_cliente");
	            edLC.setNM_Complementar (NM_Complementar);
	            this.inclui_Lancamento_Contabil (edLC);
	          }
	        }

	        if (res.getDouble ("VL_ICMS") > 0) {
	          //Cr�dito ICMS
	          sql = "SELECT " +
	            "contas.oid_historico, " +
	            "contas.oid_conta " +
	            "FROM " +
	            "contas, " +
	            "unidades " +
	            "WHERE " +
	            "unidades.OID_Unidade = " + res.getLong ("OID_Unidade") + " and " +
	            "unidades.oid_conta_credito_icms = contas.oid_conta "; //conta de cr�dito

	          resLocal = this.executasql.executarConsulta (sql);
	          long OID_Conta = 0;
	          long OID_Historico = 0;
	          while (resLocal.next ()) {
	           	OID_Conta = resLocal.getLong ("oid_conta");
	           	OID_Historico = resLocal.getLong ("oid_historico");
	          }

	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setVL_Lancamento (res.getDouble ("VL_ICMS"));
	          edLC.setDM_Acao ("C");
		      NM_Complementar = "ICMS S/ RECIBO Nr.: " + res.getString ("nr_conhecimento") + " Cliente: " + res.getString ("nm_cliente");
	          edLC.setNM_Complementar (NM_Complementar);
	          this.inclui_Lancamento_Contabil (edLC);
	          //D�bito ICMS

	          sql = "SELECT " +
	            "contas.oid_historico, " +
	            "contas.oid_conta " +
	            "FROM " +
	            "contas, " +
	            "unidades " +
	            "WHERE " +
	            "unidades.OID_Unidade = " + res.getLong ("OID_Unidade") + " and " +
	            "unidades.oid_conta_debito_icms = contas.oid_conta "; //conta de cr�dito

	          resLocal = this.executasql.executarConsulta (sql);
	          OID_Conta = 0;
	          OID_Historico = 0;
	          while (resLocal.next ()) {
	            OID_Conta = resLocal.getLong ("oid_conta");
	            OID_Historico = resLocal.getLong ("oid_historico");
	          }

	          edLC.setOID_Conta (OID_Conta);
	          edLC.setCD_Historico (OID_Historico);
	          edLC.setVL_Lancamento (res.getDouble ("VL_ICMS"));
	          edLC.setDM_Acao ("D");
		      NM_Complementar = "ICMS S/ RECIBO Nr.: " + res.getString ("nr_conhecimento") + " Cliente: " + res.getString ("nm_cliente");
	          edLC.setNM_Complementar (NM_Complementar);
	          this.inclui_Lancamento_Contabil (edLC);
	        }

	        sql = " UPDATE Conhecimentos SET ";
	        sql += " DM_Contabilizado = 'S'";
	        sql += " WHERE oid_Conhecimento = '" + res.getString ("OID_Conhecimento") + "'";
//	        executasql.executarUpdate (sql);
	      }

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setMensagem (exc.getMessage ());
	      excecoes.setClasse (this.getClass ().getName ());
	      exc.printStackTrace ();
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;

	    }
	  }

  public void inclui_CTB_Acerto (Lancamento_ContabilED ed) throws Excecoes {

	    String sql = null;
	    ResultSet resLocal = null;
	    double VL_Saldo = 0;
	    double VL_Despesas_Pagas = 0;
	    double VL_Adiantamento = 0;
	    double VL_Lancamento = 0;
	    String OID_Acerto = null;
	    String dt_Emissao = null;
	    long OID_Unidade = 0;

	    try {

	      sql = " SELECT Acertos.oid_Acerto" +
	          "     ,Acertos.nr_Acerto " +
	          "     ,Ordens_Servicos.oid_unidade" +
	          "     ,Acertos.dt_Emissao" +
	          "     ,Acertos.vl_total_despesas_pagas" +
	          "     ,Acertos.vl_adiantamento_viagem" +
	          "     ,pessoas.nm_razao_social as nm_motorista" +
	          "     ,fornec.nm_razao_social  as nm_fornec " +
	          "     ,(Acertos.vl_adiantamento_viagem - Acertos.vl_total_despesas_pagas) as vl_saldo" +
	          "     ,movimentos_ordens_servicos.OID_Pessoa as OID_Pessoa_Fornecedor " +
	          "     ,movimentos_ordens_servicos.vl_documento " +
	          "     ,movimentos_ordens_servicos.nr_documento " +
	          "     ,movimentos_ordens_servicos.oid_Moeda " +
	          "     ,movimentos_ordens_servicos.oid_tipo_servico " +
	          "     ,round(movimentos_ordens_servicos.vl_movimento * acertos.vl_cotacao,2) as vl_movimento " +
	          " FROM Acertos" +
	          "     ,movimentos_ordens_servicos " +

	          // ESSE SQL EST� ERRADO POIS PODE HAVER ACERTO SEM MOVIMENTOS_ORDENS_SERVICOS.
	          // SE ACONTECER ISSO NAO IRA CONTABILIZAR O ACERTO E FICARA FURADO
	          // PRECISAMOS RETIRAR O MOVIMENTOS_ORDENS_SERVICOS DESSE SQL E PROCURAR ABAIXO SE HOUVE ALGUM.

	          "     ,ordens_servicos " +
	          "     ,pessoas " +
	          "     ,pessoas fornec " +
	          " WHERE Ordens_Servicos.oid_ordem_servico = movimentos_ordens_servicos.oid_ordem_servico " +
	          "   and acertos.oid_ordem_servico = Ordens_Servicos.oid_ordem_servico " +
	          "   and movimentos_ordens_servicos.oid_pessoa = fornec.oid_pessoa " +
	          "   and acertos.oid_motorista = pessoas.oid_pessoa " +
	          "   and movimentos_ordens_servicos.dm_faturado_pago = 'P' " +
	          // "   and (Acertos.dm_Contabilizado <> 'S' " +
	          // "		or Acertos.dm_Contabilizado is null)" +
	          "   and Acertos.oid_Acerto = " + ed.getOID_Acerto () +
	          " order by movimentos_ordens_servicos.oid_Moeda";

	      ResultSet res = null;
	      res = this.executasql.executarConsulta (sql);
	      boolean dm_acerto = false;
	      long NR_Lote = 0;
	      String NM_Complementar = null;
	      String DM_Tipo_Despesa = "OUTROS";
	      while (res.next ()) {

	        NM_Complementar = "Acv: " + res.getString ("NR_Acerto") + "(Mot:" + res.getString ("nm_motorista") + ") - " + res.getString ("nm_fornec") + " / " + res.getString ("nr_documento");

	        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
	        edLC.setOID_Compromisso (0);
	        edLC.setOID_Nota_Fiscal ("");
	        edLC.setOID_Lote_Pagamento (0);
	        edLC.setOID_Lote_Compromisso ("");
	        edLC.setOID_Solicitacao ("");
	        edLC.setOID_Conhecimento ("");
	        edLC.setOID_Movimento_Servico ("");
	        edLC.setOID_Ordem_Frete ("");
	        edLC.setOID_Caixa ("");
	        edLC.setOid_movimento_duplicata ("");
	        edLC.setDM_Stamp (" ");

	        edLC.setOID_Acerto (res.getString ("OID_Acerto"));
	        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
	        edLC.setDT_Lancamento (res.getString ("dt_Emissao"));
	        edLC.setDT_Stamp (Data.getDataDMY ());
	        edLC.setTx_Chave_Origem (String.valueOf (ed.getOID_Acerto ()));
	        edLC.setOID_Origem (6);
	        edLC.setNR_Lote (NR_Lote);

	        OID_Acerto = res.getString ("OID_Acerto");
	        dt_Emissao = res.getString ("dt_Emissao");
	        OID_Unidade = res.getLong ("OID_Unidade");

	        if (!dm_acerto && res.getDouble ("vl_adiantamento_viagem") > 0) {
	          dm_acerto = true;
	          edLC.setVL_Lancamento (res.getDouble ("vl_adiantamento_viagem"));
	          this.Conta = this.buscaConta (this.paramConta.getCd_conta_adiantamento_viagem ());
	          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	          edLC.setDM_Acao ("C");
	          edLC.setNM_Complementar (NM_Complementar);
	          edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));
	          NR_Lote = edLC.getNR_Lote ();
	          VL_Adiantamento = edLC.getVL_Lancamento ();
	        }

	        if (res.getDouble ("vl_movimento") > 0) {

	          sql = " SELECT" +
	              "	contas.oid_historico" +
	              " ,contas.oid_conta," +
	              " Tipos_Servicos.dm_tipo_despesa" +
	              " FROM" +
	              " Tipos_Servicos, contas" +
	              " WHERE" +
	              " Tipos_Servicos.oid_conta = contas.oid_conta" +
	              " and Tipos_Servicos.oid_tipo_servico = " + res.getString ("oid_tipo_servico");

	          resLocal = this.executasql.executarConsulta (sql);

	          edLC.setOID_Conta (0);

	          while (resLocal.next ()) {

	            edLC.setOID_Conta (resLocal.getLong ("oid_conta"));
	            edLC.setCD_Historico (resLocal.getLong ("oid_historico"));
	            if (resLocal.getString ("dm_tipo_despesa") != null && resLocal.getString ("dm_tipo_despesa").equals ("T")) { // T = Presta��o de servi�o
	              DM_Tipo_Despesa = "PRESTACAO_SERVICO";
	            }

	          }

	          if (edLC.getOID_Conta () == 0) {
	            throw new Excecoes ("Tipo de Servi�o sem conta cont�bil !!!  ---> CNPJ ==" + res.getString ("OID_Pessoa_Fornecedor"));
	          }
	          if (res.getString ("OID_Moeda").equals ("1")) {
	            VL_Lancamento = res.getDouble ("vl_documento");
	          }
	          else {
	            if (!res.isLast ()) {
	              VL_Lancamento = res.getDouble ("vl_movimento");
	            }
	            else {
	              VL_Lancamento = res.getDouble ("vl_total_despesas_pagas") - VL_Despesas_Pagas;
	            }
	          }
	          edLC.setVL_Lancamento (VL_Lancamento);

	          VL_Despesas_Pagas = VL_Despesas_Pagas + edLC.getVL_Lancamento ();
	          edLC.setDM_Acao ("D");
	          edLC.setNM_Complementar (NM_Complementar);
	          edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));
	          NR_Lote = edLC.getNR_Lote ();
	        }
	      }

	      Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
	      VL_Saldo = VL_Adiantamento - VL_Despesas_Pagas;

	      edLC.setOID_Compromisso (0);
	      edLC.setOID_Nota_Fiscal ("");
	      edLC.setOID_Lote_Pagamento (0);
	      edLC.setOID_Lote_Compromisso ("");
	      edLC.setOID_Solicitacao ("");
	      edLC.setOID_Conhecimento ("");
	      edLC.setOID_Movimento_Servico ("");
	      edLC.setOID_Ordem_Frete ("");
	      edLC.setOID_Caixa ("");
	      edLC.setOid_movimento_duplicata ("");
	      edLC.setDM_Stamp (" ");

	      edLC.setOID_Acerto (OID_Acerto);
	      edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
	      edLC.setDT_Lancamento (dt_Emissao);
	      edLC.setDT_Stamp (Data.getDataDMY ());
	      edLC.setTx_Chave_Origem (String.valueOf (ed.getOID_Acerto ()));
	      edLC.setOID_Origem (6);
	      edLC.setNR_Lote (NR_Lote);

	      if (VL_Saldo > 0) {
	        this.Conta = this.buscaConta (this.paramConta.getCd_conta_adiantamento_viagem ());
	        edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	        edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	        edLC.setVL_Lancamento (VL_Saldo);
	        edLC.setDM_Acao ("D");
	        edLC.setNM_Complementar (NM_Complementar);
	        this.inclui_Lancamento_Contabil (edLC);
	      }
	      else if (VL_Saldo < 0) {
	        if (DM_Tipo_Despesa.equals ("PRESTACAO_SERVICO")) {
	          this.Conta = this.buscaConta (this.paramConta.getCd_conta_adiantamento_terceiro ());
	          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	        }
	        else {
	          this.Conta = this.buscaConta (this.paramConta.getCd_conta_adiantamento_funcionario ());
	          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	        }
	        edLC.setVL_Lancamento (VL_Saldo * ( -1));
	        edLC.setDM_Acao ("C");
	        edLC.setNM_Complementar (NM_Complementar);
	        this.inclui_Lancamento_Contabil (edLC);
	      }

	      sql = " UPDATE Acertos SET ";
	      sql += " DM_Contabilizado = 'S'";
	      sql += " WHERE oid_Acerto = " + ed.getOID_Acerto ();
	      // executasql.executarUpdate (sql);

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem (exc.getMessage ());
	      exc.printStackTrace ();
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void inclui_CTB_Ordem_Frete (Ordem_FreteED ed) throws Excecoes {

	    String sql = null;
	    ResultSet resLocal = null;

	    try {

	      sql = " SELECT Ordens_Fretes.oid_Ordem_Frete" +
	          "     ,Ordens_Fretes.nr_Ordem_Frete " +
	          "     ,Ordens_Fretes.oid_unidade" +
	          "     ,Ordens_Fretes.oid_Pessoa as OID_Pessoa_Fornecedor" +
	          "     ,Ordens_Fretes.dt_Emissao" +
	          "     ,Ordens_Fretes.vl_ordem_frete" +
	          "     ,Ordens_Fretes.vl_set_senat" +
	          "     ,Ordens_Fretes.vl_irrf" +
	          "     ,Ordens_Fretes.VL_Pedagio" +
	          "     ,Ordens_Fretes.VL_Coleta" +
	          "     ,Ordens_Fretes.VL_Premio" +
	          "     ,Ordens_Fretes.VL_Outros" +
	          "     ,Ordens_Fretes.VL_Carga" +
	          "     ,Ordens_Fretes.VL_Descarga" +
	          "     ,Ordens_Fretes.VL_Descontos" +
	          "     ,Ordens_Fretes.vl_inss_prestador" +
	          "     ,Ordens_Fretes.vl_inss_empresa" +
	          "     ,Pessoas.nm_razao_social as nm_fornecedor" +
	          "     ,Pessoas.nr_cnpj_cpf as NR_CNPJ_Pessoa_Fornecedor" +
	          " FROM Ordens_Fretes" +
	          "     ,Pessoas" +
	          " WHERE Ordens_Fretes.oid_fornecedor = Pessoas.oid_pessoa " +
	         // "   and (Ordens_Fretes.dm_Contabilizado <> 'S' " +
	         // "		or Ordens_Fretes.dm_Contabilizado is null)" +
	          "   and Ordens_Fretes.oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

	      ResultSet res = null;
	      res = this.executasql.executarConsulta (sql);
	      String NM_Complementar = null;

	      while (res.next ()) {
	        NM_Complementar = "Emissao Ordem de Frete Nr. " + res.getString ("nr_ordem_frete") + " Fornecedor: " + res.getString ("nm_fornecedor");

	        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
	        edLC.setOID_Compromisso (0);
	        edLC.setOID_Nota_Fiscal ("");
	        edLC.setOID_Lote_Pagamento (0);
	        edLC.setOID_Lote_Compromisso ("");
	        edLC.setOID_Solicitacao ("");
	        edLC.setOID_Acerto ("0");
	        edLC.setOID_Movimento_Servico ("");
	        edLC.setOID_Conhecimento ("");
	        edLC.setOID_Caixa ("");
	        edLC.setOid_movimento_duplicata ("");
	        edLC.setDM_Stamp (" ");

	        edLC.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));
	        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
	        edLC.setDT_Lancamento (res.getString ("dt_Emissao"));
	        edLC.setDT_Stamp (Data.getDataDMY ());
	        edLC.setTx_Chave_Origem (String.valueOf (ed.getOID_Ordem_Frete ()));
	        edLC.setOID_Origem (4);

	        if (res.getDouble ("vl_ordem_frete") > 0) {

	  	        if (res.getString ("NR_CNPJ_Pessoa_Fornecedor").length () == 14) {
	               this.Conta = this.buscaConta (this.paramConta.getCd_conta_ofpj_credito ());
	               edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	               edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	  	        }else{
	               this.Conta = this.buscaConta (this.paramConta.getCd_conta_ofpf_credito ());
	               edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	               edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	  	        }
	  	        ed.setVL_Liquido_Ordem_Frete (res.getDouble ("vl_ordem_frete") - res.getDouble ("VL_Descontos") - res.getDouble ("vl_irrf") - res.getDouble ("vl_inss_prestador")- res.getDouble ("vl_set_senat"));
	  	        edLC.setVL_Lancamento (ed.getVL_Liquido_Ordem_Frete ());
	  	        edLC.setDM_Acao ("C");
		        NM_Complementar = "Emissao Ordem de Frete Nr. " + res.getString ("nr_ordem_frete") + " Fornecedor: " + res.getString ("nm_fornecedor");
	  	        edLC.setNM_Complementar (NM_Complementar);
	  	        edLC.setNR_Lote (0);

	  	        edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

	  	        double VL_Carga_Descarga = res.getDouble ("VL_Descarga") + res.getDouble ("VL_Carga");

                if (VL_Carga_Descarga > 0) {
    	  	       edLC.setDM_Acao ("C");
    		       NM_Complementar = "Descarga S/ Ordem de Frete Nr. " + res.getString ("nr_ordem_frete") + " Fornecedor: " + res.getString ("nm_fornecedor");
    	  	       edLC.setNM_Complementar (NM_Complementar);
			       edLC.setVL_Lancamento (VL_Carga_Descarga);
			       this.inclui_Lancamento_Contabil (edLC);
                }

			    if (res.getDouble ("VL_Outros") > 0) {
		  	       edLC.setDM_Acao ("C");
    		       NM_Complementar = "Outros S/ Ordem de Frete Nr. " + res.getString ("nr_ordem_frete") + " Fornecedor: " + res.getString ("nm_fornecedor");
		  	       edLC.setNM_Complementar (NM_Complementar);
			       edLC.setVL_Lancamento (res.getDouble ("VL_Outros"));
			       this.inclui_Lancamento_Contabil (edLC);
			    }

                if (VL_Carga_Descarga > 0) {
 			       this.Conta = this.buscaConta ("4146");
 			       edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
 			       edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
 			       edLC.setVL_Lancamento (VL_Carga_Descarga);
 			       edLC.setDM_Acao ("D");
    		       NM_Complementar = "Descarga S/ Ordem de Frete Nr. " + res.getString ("nr_ordem_frete") + " Fornecedor: " + res.getString ("nm_fornecedor");
 			       edLC.setNM_Complementar (NM_Complementar);
 			       this.inclui_Lancamento_Contabil (edLC);
                }

                if (res.getDouble ("VL_Outros") > 0) {
  			       this.Conta = this.buscaConta ("4146");
 			       edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
 			       edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
                   edLC.setDM_Acao ("D");
    		       NM_Complementar = "Outros S/ Ordem de Frete Nr. " + res.getString ("nr_ordem_frete") + " Fornecedor: " + res.getString ("nm_fornecedor");
 			       edLC.setNM_Complementar (NM_Complementar);
                   edLC.setVL_Lancamento (res.getDouble ("VL_Outros"));
				   this.inclui_Lancamento_Contabil (edLC);
				}

                if (edLC.getNR_Lote()>0) {
	  	          if (res.getString ("NR_CNPJ_Pessoa_Fornecedor").length () == 14) {
		            this.Conta = this.buscaConta (this.paramConta.getCd_conta_ofpj_debito ());
		            edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
		            edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
		          }
		          else {
		            this.Conta = this.buscaConta (this.paramConta.getCd_conta_ofpf_debito ());
		            edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
		            edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
		          }
		          edLC.setVL_Lancamento (res.getDouble ("vl_ordem_frete"));
		          edLC.setDM_Acao ("D");
   		          NM_Complementar = "Emissao Ordem de Frete Nr. " + res.getString ("nr_ordem_frete") + " Fornecedor: " + res.getString ("nm_fornecedor");
		          edLC.setNM_Complementar (NM_Complementar);
		          this.inclui_Lancamento_Contabil (edLC);

			      if (res.getDouble ("VL_IRRF") > 0) {
			          this.Conta = this.buscaConta (this.paramConta.getCd_conta_irrf_credito ());
			          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
			          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
			          edLC.setVL_Lancamento (res.getDouble ("VL_IRRF"));
			          edLC.setDM_Acao ("C");
	    		      NM_Complementar = "IRRF S/ Ordem de Frete Nr. " + res.getString ("nr_ordem_frete") + " Fornecedor: " + res.getString ("nm_fornecedor");
			          edLC.setNM_Complementar (NM_Complementar);
			          this.inclui_Lancamento_Contabil (edLC);
			      }

			      if (res.getDouble ("VL_Descontos") > 0) {
			          this.Conta = this.buscaConta ("9102");
			          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
			          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
			          edLC.setVL_Lancamento (res.getDouble ("VL_Descontos"));
			          edLC.setDM_Acao ("C");
	    		      NM_Complementar = "Descontos S/ Ordem de Frete Nr. " + res.getString ("nr_ordem_frete") + " Fornecedor: " + res.getString ("nm_fornecedor");
			          edLC.setNM_Complementar (NM_Complementar);
			          this.inclui_Lancamento_Contabil (edLC);
			      }

			      if (res.getDouble ("VL_SET_SENAT") > 0) {
			          this.Conta = this.buscaConta (this.paramConta.getCd_conta_sest_senat_credito ());
			          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
			          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
			          edLC.setVL_Lancamento (res.getDouble ("VL_SET_SENAT"));
			          edLC.setDM_Acao ("C");
	    		       NM_Complementar = "Sest-Senat S/ Ordem de Frete Nr. " + res.getString ("nr_ordem_frete") + " Fornecedor: " + res.getString ("nm_fornecedor");
			          edLC.setNM_Complementar (NM_Complementar);
			          this.inclui_Lancamento_Contabil (edLC);
			      }

			      if (res.getDouble ("VL_INSS_Empresa") > 0) {
			          this.Conta = this.buscaConta (this.paramConta.getCd_conta_inss_empresa_credito ());
			          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
			          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
			          edLC.setVL_Lancamento (res.getDouble ("VL_INSS_Empresa"));
			          edLC.setDM_Acao ("C");
	    		      NM_Complementar = "INSS Empresa S/ Ordem de Frete Nr. " + res.getString ("nr_ordem_frete") + " Fornecedor: " + res.getString ("nm_fornecedor");
			          edLC.setNM_Complementar (NM_Complementar);
			          this.inclui_Lancamento_Contabil (edLC);

			          this.Conta = this.buscaConta (this.paramConta.getCd_conta_inss_empresa_debito ());
			          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
			          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
			          edLC.setVL_Lancamento (res.getDouble ("VL_INSS_Empresa"));
			          edLC.setDM_Acao ("D");
	    		      NM_Complementar = "INSS Empresa S/ Ordem de Frete Nr. " + res.getString ("nr_ordem_frete") + " Fornecedor: " + res.getString ("nm_fornecedor");
			          edLC.setNM_Complementar (NM_Complementar);
			          this.inclui_Lancamento_Contabil (edLC);
			      }

			      if (res.getDouble ("VL_INSS_Prestador") > 0) {
			    	  this.Conta = this.buscaConta (this.paramConta.getCd_conta_inss_terceiro_credito ());
			          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
			          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
			          edLC.setVL_Lancamento (res.getDouble ("VL_INSS_Prestador"));
			          edLC.setDM_Acao ("C");
	    		      NM_Complementar = "INSS S/ Ordem de Frete Nr. " + res.getString ("nr_ordem_frete") + " Fornecedor: " + res.getString ("nm_fornecedor");
			          edLC.setNM_Complementar (NM_Complementar);
			          this.inclui_Lancamento_Contabil (edLC);

			      }
			      sql = " UPDATE Ordens_Fretes SET ";
			      sql += " DM_Contabilizado = 'S'";
			      sql += " WHERE oid_Ordem_Frete = '" + res.getString ("OID_Ordem_Frete") + "'";
			      // executasql.executarUpdate (sql);
	  	        }
	  	      }
	      }

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      exc.printStackTrace ();
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
  }

  public void inclui_CTB_Nota_Fiscal (Nota_Fiscal_CompraED ed) throws Excecoes {

	  // miro
    String sql = null;
    String NM_Complemento = null;
    ResultSet resLocal = null;
    long OID_Conta_Debito_Fornecedor = 0;
    long OID_Historico_Debito_Fornecedor = 0;

    try {

      sql = " SELECT Notas_Fiscais_Transacoes.oid_Nota_Fiscal" +
          "     ,Notas_Fiscais_Transacoes.nr_Nota_Fiscal " +
          "     ,Notas_Fiscais_Transacoes.oid_unidade_fiscal as oid_unidade" +
          "     ,Notas_Fiscais_Transacoes.oid_Pessoa as OID_Pessoa_Fornecedor" +
          "     ,Notas_Fiscais_Transacoes.dt_entrada" +
          "     ,Notas_Fiscais_Transacoes.oid_conta" +
          "     ,Notas_Fiscais_Transacoes.oid_conta_servico" +
          "     ,Notas_Fiscais_Transacoes.oid_conta_outros" +
          "     ,Notas_Fiscais_Transacoes.vl_Nota_Fiscal" +
          "     ,Notas_Fiscais_Transacoes.vl_total_despesas" +
          "     ,Notas_Fiscais_Transacoes.vl_total_frete" +
          "     ,Notas_Fiscais_Transacoes.vl_icms" +
          "     ,Notas_Fiscais_Transacoes.vl_irrf" +
          "     ,Notas_Fiscais_Transacoes.vl_inss" +
          "     ,Notas_Fiscais_Transacoes.vl_ipi" +
          "     ,Notas_Fiscais_Transacoes.vl_isqn" +
          "     ,Notas_Fiscais_Transacoes.vl_liquido_nota_fiscal" +
          "     ,Notas_Fiscais_Transacoes.vl_descontos" +
          "     ,Notas_Fiscais_Transacoes.vl_servico" +
          "     ,Notas_Fiscais_Transacoes.vl_outros" +
          "     ,Notas_Fiscais_Transacoes.vl_pis" +
          "     ,Notas_Fiscais_Transacoes.vl_cofins" +
          "     ,Notas_Fiscais_Transacoes.vl_csll" +
          "     ,Pessoas.NM_Razao_Social" +
          " FROM Notas_Fiscais_Transacoes" +
          "     ,Fornecedores" +
          "     ,Pessoas" +
          " WHERE Notas_Fiscais_Transacoes.oid_pessoa = Fornecedores.oid_pessoa " +
          " AND Fornecedores.oid_pessoa = Pessoas.oid_pessoa " +
          // "   and (Notas_Fiscais_Transacoes.dm_Contabilizado <> 'S' " +
          // "		or Notas_Fiscais_Transacoes.dm_Contabilizado is null)" +
          "   and Notas_Fiscais_Transacoes.oid_Nota_Fiscal = '" + ed.getOid_nota_fiscal () + "'";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
        edLC.setOID_Compromisso (0);
        edLC.setOID_Ordem_Frete ("");
        edLC.setOID_Lote_Pagamento (0);
        edLC.setOID_Lote_Compromisso ("");
        edLC.setOID_Solicitacao ("");
        edLC.setOID_Acerto ("0");
        edLC.setOID_Movimento_Servico ("");
        edLC.setOID_Conhecimento ("");
        edLC.setOID_Caixa ("");
        edLC.setOid_movimento_duplicata ("");
        edLC.setDM_Stamp (" ");

        edLC.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
        edLC.setDT_Lancamento (res.getString ("dt_entrada"));
        edLC.setDT_Stamp (Data.getDataDMY ());
        edLC.setTx_Chave_Origem (String.valueOf (ed.getOid_nota_fiscal ()));
        edLC.setOID_Origem (7);
        NM_Complemento = "Entrada NF nr.: " + res.getString ("NR_Nota_Fiscal") + " Fornecedor: " + res.getString ("NM_Razao_Social");

        edLC.setNR_Lote (0);
        // Valor das mercadorias - Vai � d�bito
        if (res.getDouble ("vl_Nota_Fiscal") > 0) {
          edLC.setOID_Conta (res.getLong ("oid_conta")); // Conta de d�bito de mercadorias
          sql = " SELECT " +
              " contas.oid_historico" +
              ",contas.oid_conta" +
              " FROM " +
              " contas " +
              " WHERE " +
              " contas.oid_conta = " + res.getString ("oid_conta");
          resLocal = this.executasql.executarConsulta (sql);
          while (resLocal.next ()) {
            edLC.setCD_Historico (resLocal.getLong ("oid_historico"));
          }

          edLC.setVL_Lancamento (res.getDouble ("vl_Nota_Fiscal") + res.getDouble ("vl_ipi"));
          edLC.setDM_Acao ("D");
          NM_Complemento = "Entrada NF nr.: " + res.getString ("NR_Nota_Fiscal") + " Fornecedor: " + res.getString ("NM_Razao_Social");
          edLC.setNM_Complementar (NM_Complemento);
          edLC.setNR_Lote (this.inclui_Lancamento_Contabil_Nota (edLC));
        }

        // Valor Frete- Vai � d�bito
        if (res.getDouble ("vl_total_frete") > 0) {
            edLC.setOID_Conta (res.getLong ("oid_conta")); // Conta de d�bito de mercadorias
          sql = " SELECT " +
              " contas.oid_historico" +
              ",contas.oid_conta" +
              " FROM " +
              " contas " +
              " WHERE " +
              " contas.oid_conta = " + res.getString ("oid_conta");
          resLocal = this.executasql.executarConsulta (sql);
          while (resLocal.next ()) {
            edLC.setCD_Historico (resLocal.getLong ("oid_historico"));
          }
          edLC.setVL_Lancamento (res.getDouble ("vl_total_frete"));
          edLC.setDM_Acao ("D");
          NM_Complemento = "Frete S/ NF nr.: " + res.getString ("NR_Nota_Fiscal") + " Fornecedor: " + res.getString ("NM_Razao_Social");
          edLC.setNM_Complementar (NM_Complemento);

          this.inclui_Lancamento_Contabil_Nota (edLC);
        }

        // Valor Despeasas Acess�rias - Vai � d�bito
        if (res.getDouble ("vl_total_despesas") > 0) {
            edLC.setOID_Conta (res.getLong ("oid_conta")); // Conta de d�bito de mercadorias
          sql = " SELECT " +
              " contas.oid_historico" +
              ",contas.oid_conta" +
              " FROM " +
              " contas " +
              " WHERE " +
              " contas.oid_conta = " + res.getString ("oid_conta");
          resLocal = this.executasql.executarConsulta (sql);
          while (resLocal.next ()) {
            edLC.setCD_Historico (resLocal.getLong ("oid_historico"));
          }
          edLC.setVL_Lancamento (res.getDouble ("vl_total_despesas"));
          edLC.setDM_Acao ("D");
          NM_Complemento = "Despesas S/ NF nr.: " + res.getString ("NR_Nota_Fiscal") + " Fornecedor: " + res.getString ("NM_Razao_Social");
          edLC.setNM_Complementar (NM_Complemento);

          this.inclui_Lancamento_Contabil_Nota (edLC);
        }

        // Valor Outros- Vai � d�bito
        if (res.getDouble ("vl_Outros") > 0) {
          edLC.setOID_Conta (res.getLong ("oid_conta_outros")); // Conta de d�bito de servi�os
          sql = " SELECT " +
              " contas.oid_historico" +
              ",contas.oid_conta" +
              " FROM " +
              " contas " +
              " WHERE " +
              " contas.oid_conta = " + res.getString ("oid_conta_outros");
          resLocal = this.executasql.executarConsulta (sql);
          while (resLocal.next ()) {
            edLC.setCD_Historico (resLocal.getLong ("oid_historico"));
          }
          edLC.setVL_Lancamento (res.getDouble ("vl_Outros"));
          edLC.setDM_Acao ("D");
          NM_Complemento = "Outros S/ NF nr.: " + res.getString ("NR_Nota_Fiscal") + " Fornecedor: " + res.getString ("NM_Razao_Social");
          edLC.setNM_Complementar (NM_Complemento);
          this.inclui_Lancamento_Contabil_Nota (edLC);
        }

        // Valor dos servicos - Vai � d�bito
        if (res.getDouble ("vl_Servico") > 0) {
          edLC.setOID_Conta (res.getLong ("oid_conta_servico")); // Conta de d�bito de servi�os
          sql = " SELECT " +
              " contas.oid_historico" +
              ",contas.oid_conta" +
              " FROM " +
              " contas " +
              " WHERE " +
              " contas.oid_conta = " + res.getString ("oid_conta_servico");
          resLocal = this.executasql.executarConsulta (sql);
          while (resLocal.next ()) {
            edLC.setCD_Historico (resLocal.getLong ("oid_historico"));
          }
          edLC.setVL_Lancamento (res.getDouble ("vl_Servico"));
          edLC.setDM_Acao ("D");
          NM_Complemento = "Entrada NF nr.: " + res.getString ("NR_Nota_Fiscal") + " Fornecedor: " + res.getString ("NM_Razao_Social");
          edLC.setNM_Complementar (NM_Complemento);
          this.inclui_Lancamento_Contabil_Nota (edLC);
        }

        // Valor total da nota - Vai a cr�dito / conta do fornecedor ou fornecedor generico
        if (res.getDouble ("vl_liquido_nota_fiscal") > 0) {

          sql = " SELECT " +
              "	contas.oid_historico" +
              ",contas.oid_conta" +
              " FROM " +
              " contas " +
              ",fornecedores" +
              " WHERE " +
              " fornecedores.oid_conta = contas.oid_conta" +
              " and fornecedores.oid_pessoa = '" + res.getString ("OID_Pessoa_Fornecedor") + "'";
          resLocal = this.executasql.executarConsulta (sql);
          edLC.setOID_Conta (0);
          while (resLocal.next ()) {
            edLC.setOID_Conta (resLocal.getLong ("oid_conta"));
            edLC.setCD_Historico (resLocal.getLong ("oid_historico"));
          }

          if (edLC.getOID_Conta () == 0) { // Conta fornecedor generico

        	  SQLException e = new SQLException();
              throw new Excecoes (e.getMessage () , e , getClass ().getName () , "Fornecedor sem conta contabil cadastrada !!!");

            //this.Conta = this.buscaConta (this.paramConta.getCd_conta_fornecedores_geral ());
            //edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
            //edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
          }
          edLC.setVL_Lancamento (res.getDouble ("vl_liquido_nota_fiscal"));
          edLC.setDM_Acao ("C");
          NM_Complemento = "Entrada NF nr.: " + res.getString ("NR_Nota_Fiscal") + " Fornecedor: " + res.getString ("NM_Razao_Social");
          edLC.setNM_Complementar (NM_Complemento);
          this.inclui_Lancamento_Contabil_Nota (edLC);
        }

        if (res.getDouble("vl_descontos") > 0){

          this.Conta = this.buscaConta(this.paramConta.getCd_conta_desconto_nota_fiscal());
          edLC.setOID_Conta(this.Conta.getOid_Conta().longValue());
          edLC.setCD_Historico(this.Conta.getOid_Historico().longValue());

          edLC.setVL_Lancamento(res.getDouble("vl_descontos"));
          edLC.setDM_Acao("C");
          NM_Complemento = "Descontos S/ NF nr.: " + res.getString ("NR_Nota_Fiscal") + " Fornecedor: " + res.getString ("NM_Razao_Social");
          edLC.setNM_Complementar(NM_Complemento);
          this.inclui_Lancamento_Contabil_Nota(edLC);
        }


        if (res.getDouble ("vl_pis") > 0) {

          this.Conta = this.buscaConta (this.paramConta.getCd_conta_pis_nota_fiscal ());
          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());

          edLC.setVL_Lancamento (res.getDouble ("vl_pis"));
          edLC.setDM_Acao ("C");
          NM_Complemento = "PIS S/ NF nr.: " + res.getString ("NR_Nota_Fiscal") + " Fornecedor: " + res.getString ("NM_Razao_Social");
          edLC.setNM_Complementar (NM_Complemento);
          this.inclui_Lancamento_Contabil_Nota (edLC);
        }

        if (res.getDouble ("vl_isqn") > 0) {
            this.Conta = this.buscaConta (this.paramConta.getCd_conta_isqn_nota_fiscal ());
            edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
            edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
            edLC.setVL_Lancamento (res.getDouble ("vl_isqn"));
            edLC.setDM_Acao ("C");
            NM_Complemento = "ISSQN S/ NF nr.: " + res.getString ("NR_Nota_Fiscal") + " Fornecedor: " + res.getString ("NM_Razao_Social");
            edLC.setNM_Complementar (NM_Complemento);
            this.inclui_Lancamento_Contabil_Nota (edLC);
        }

        if (res.getDouble ("vl_cofins") > 0) {

          this.Conta = this.buscaConta (this.paramConta.getCd_conta_cofins_nota_fiscal ());
          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());

          edLC.setVL_Lancamento (res.getDouble ("vl_cofins"));
          edLC.setDM_Acao ("C");
          NM_Complemento = "COFINS S/ NF nr.: " + res.getString ("NR_Nota_Fiscal") + " Fornecedor: " + res.getString ("NM_Razao_Social");
          edLC.setNM_Complementar (NM_Complemento);
          this.inclui_Lancamento_Contabil_Nota (edLC);
        }

        if (res.getDouble ("vl_csll") > 0) {

          this.Conta = this.buscaConta (this.paramConta.getCd_conta_csll_nota_fiscal ());
          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());

          edLC.setVL_Lancamento (res.getDouble ("vl_csll"));
          edLC.setDM_Acao ("C");
          NM_Complemento = "CSLL S/ NF nr.: " + res.getString ("NR_Nota_Fiscal") + " Fornecedor: " + res.getString ("NM_Razao_Social");
          edLC.setNM_Complementar (NM_Complemento);
          this.inclui_Lancamento_Contabil_Nota (edLC);
        }

        if (res.getDouble ("VL_IRRF") > 0) {

          this.Conta = this.buscaConta (this.paramConta.getCd_conta_irrf_nota_fiscal ());
          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());

          edLC.setVL_Lancamento (res.getDouble ("VL_IRRF"));
          edLC.setDM_Acao ("C");
          NM_Complemento = "IRRF S/ NF nr.: " + res.getString ("NR_Nota_Fiscal") + " Fornecedor: " + res.getString ("NM_Razao_Social");
          edLC.setNM_Complementar (NM_Complemento);
          this.inclui_Lancamento_Contabil_Nota (edLC);
        }

        if (res.getDouble ("VL_INSS") > 0) {

          this.Conta = this.buscaConta (this.paramConta.getCd_conta_inss_nota_fiscal ());
          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());

          edLC.setVL_Lancamento (res.getDouble ("VL_INSS"));
          edLC.setDM_Acao ("C");
          NM_Complemento = "INSS S/ NF nr.: " + res.getString ("NR_Nota_Fiscal") + " Fornecedor: " + res.getString ("NM_Razao_Social");
          edLC.setNM_Complementar (NM_Complemento);
          this.inclui_Lancamento_Contabil_Nota (edLC);
        }

        sql = " UPDATE Notas_Fiscais_Transacoes SET ";
        sql += " DM_Contabilizado = 'S'";
        sql += " WHERE oid_Nota_Fiscal = '" + res.getString ("OID_Nota_Fiscal") + "'";
        // executasql.executarUpdate (sql);

      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      exc.printStackTrace ();
      excecoes.setMetodo ("inclui lancamentos");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void inclui_CTB_Nota_Fiscal_Servicos (Nota_Fiscal_CompraED ed) throws Excecoes {

	    String sql = null;
	    String NM_Complemento = null;
	    ResultSet resLocal = null;
	    long OID_Conta_Debito_Fornecedor = 0;
	    long OID_Historico_Debito_Fornecedor = 0;

	    try {

	      sql = " SELECT Notas_Fiscais.oid_Nota_Fiscal" +
	          "     ,Notas_Fiscais.nr_Nota_Fiscal " +
	          "     ,Notas_Fiscais.oid_unidade_fiscal as oid_unidade" +
	          "     ,Notas_Fiscais.oid_pessoa_destinatario as OID_Pessoa_Fornecedor" +
	          "     ,Notas_Fiscais.dt_emissao" +
	          "     ,Notas_Fiscais.vl_Nota_Fiscal" +
	          "     ,Notas_Fiscais.vl_INSS" +
	          "     ,Notas_Fiscais.vl_isqn" +
	          "     ,Notas_Fiscais.dm_recolhedor_issqn " +
	          "     ,Pessoas.NM_Razao_Social" +
	          " FROM Notas_Fiscais" +
	          "     ,Pessoas" +
	          " WHERE Notas_Fiscais.oid_pessoa_destinatario = Pessoas.oid_pessoa " +
	          //"   and (Notas_Fiscais.dm_Contabilizado <> 'S' " +
	          //"		or Notas_Fiscais.dm_Contabilizado is null)" +
	          "   and Notas_Fiscais.oid_Nota_Fiscal = '" + ed.getOid_nota_fiscal () + "'";

	      // // .println(sql);

	      ResultSet res = null;
	      res = this.executasql.executarConsulta (sql);

	      while (res.next ()) {
	        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
	        edLC.setOID_Compromisso (0);
	        edLC.setOID_Ordem_Frete ("");
	        edLC.setOID_Lote_Pagamento (0);
	        edLC.setOID_Lote_Compromisso ("");
	        edLC.setOID_Solicitacao ("");
	        edLC.setOID_Acerto ("0");
	        edLC.setOID_Movimento_Servico ("");
	        edLC.setOID_Conhecimento ("");
	        edLC.setOID_Caixa ("");
	        edLC.setOid_movimento_duplicata ("");
	        edLC.setDM_Stamp (" ");

	        edLC.setOID_Nota_Fiscal ("");
	        edLC.setOID_Nota_Fiscal_Servico (res.getString ("OID_Nota_Fiscal"));
	        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
	        edLC.setDT_Lancamento (res.getString ("dt_emissao"));
	        edLC.setDT_Stamp (Data.getDataDMY ());
	        edLC.setTx_Chave_Origem (String.valueOf (ed.getOid_nota_fiscal ()));
	        edLC.setOID_Origem (109);
	        NM_Complemento = "NF Servico nr.: " + res.getString ("NR_Nota_Fiscal") + " Cliente: " + res.getString ("NM_Razao_Social");

	        edLC.setNR_Lote (0);
	        // Valor das mercadorias - Vai � d�bito
	        if (res.getDouble ("vl_Nota_Fiscal") > 0) {

		      this.Conta = this.buscaConta ("3031");
		      edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
		      edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	          edLC.setVL_Lancamento (res.getDouble ("vl_Nota_Fiscal"));
	          edLC.setDM_Acao ("C");
	          edLC.setNM_Complementar (NM_Complemento);
	          edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

        	  this.Conta = this.buscaConta (this.paramConta.getCd_conta_cliente_geral ());
	          edLC.setVL_Lancamento (res.getDouble ("vl_Nota_Fiscal") - res.getDouble ("vl_INSS"));
		      edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
		      edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	          edLC.setDM_Acao ("D");
	          this.inclui_Lancamento_Contabil (edLC);

		      if (res.getDouble ("vl_INSS") > 0) {
	        	  this.Conta = this.buscaConta ("82");
			      edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
			      edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
		          edLC.setVL_Lancamento (res.getDouble ("vl_INSS"));
		          edLC.setDM_Acao ("D");
		          this.inclui_Lancamento_Contabil (edLC);
		      }

	          if (res.getDouble ("vl_isqn") > 0) {
	        	  this.Conta = this.buscaConta ("521");
			      edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
			      edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
		          edLC.setVL_Lancamento (res.getDouble ("vl_isqn"));
		          edLC.setDM_Acao ("C");
		          this.inclui_Lancamento_Contabil (edLC);

	        	  this.Conta = this.buscaConta ("3301");
			      edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
			      edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
		          edLC.setVL_Lancamento (res.getDouble ("vl_isqn"));
		          edLC.setDM_Acao ("D");
		          this.inclui_Lancamento_Contabil (edLC);
		      }
	        }

	        sql = " UPDATE Notas_Fiscais SET ";
	        sql += " DM_Contabilizado = 'S'";
	        sql += " WHERE oid_Nota_Fiscal = '" + res.getString ("OID_Nota_Fiscal") + "'";
	        //executasql.executarUpdate (sql);

	      }

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      exc.printStackTrace ();
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void zera_Conta_Resultado (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    double VL_Credito = 0;
    double VL_Debito = 0;
    double VL_Saldo = 0;
    long NR_Lote = 0;
    long OID_Historico = 0;
    long OID_Conta_Resultado = 0;
    String NM_Complementar = null;
    ResultSet resConta = null;
    ResultSet resMov = null;
    String dm_debito_credito_conta = "";
    String dm_debito_credito_resultado = "";

    try {

      this.Conta = this.buscaConta (paramConta.getCd_conta_zera_resultado ());
      OID_Conta_Resultado = Conta.getOid_Conta ().longValue ();
      OID_Historico = this.Conta.getOid_Historico ().longValue ();

      sql = " UPDATE Contas SET ";
      sql += " vl_saldo_zera_resultado = 0 ";
      executasql.executarUpdate (sql);

      sql = " SELECT Contas.* " +
          " FROM Contas " +
          " WHERE DM_Zera='T' "; //and cd_conta='3153' ";

      resConta = this.executasql.executarConsulta (sql);
      while (resConta.next ()) {

        VL_Credito = 0;
        VL_Debito = 0;
        sql = " SELECT sum(vl_lancamento) as vl_lancamento" +
            " FROM   movimentos_contabeis " +
            " WHERE  movimentos_contabeis.oid_Unidade='" + ed.getOID_Unidade_Contabil () + "'" +
            " AND    movimentos_contabeis.dt_movimento>='" + ed.getDT_Lancamento_Inicial () + "'" +
            " AND    movimentos_contabeis.dt_movimento<='" + ed.getDT_Lancamento_Final () + "'" +
            " AND    dm_debito_credito ='D' " +
            " AND    oid_conta=" + resConta.getLong ("oid_Conta");

        resMov = this.executasql.executarConsulta (sql);
        while (resMov.next ()) {
          VL_Debito = resMov.getDouble ("vl_lancamento");
        }

        sql = " SELECT sum(vl_lancamento) as vl_lancamento" +
            " FROM   movimentos_contabeis " +
            " WHERE  movimentos_contabeis.oid_Unidade='" + ed.getOID_Unidade_Contabil () + "'" +
            " AND    movimentos_contabeis.dt_movimento>='" + ed.getDT_Lancamento_Inicial () + "'" +
            " AND    movimentos_contabeis.dt_movimento<='" + ed.getDT_Lancamento_Final () + "'" +
            " AND    dm_debito_credito ='C' " +
            " AND    oid_conta=" + resConta.getLong ("oid_Conta");

        resMov = this.executasql.executarConsulta (sql);
        while (resMov.next ()) {
          VL_Credito = resMov.getDouble ("vl_lancamento");
        }

        VL_Saldo = VL_Credito - VL_Debito;

        if (VL_Saldo != 0) {

          dm_debito_credito_conta = "D";
          dm_debito_credito_resultado = "C";
          if (VL_Saldo < 0) {
            dm_debito_credito_conta = "C";
            dm_debito_credito_resultado = "D";
            VL_Saldo=VL_Saldo*-1;
          }

          NM_Complementar = "CONTA :" + resConta.getString ("nm_Conta");

          Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
          edLC.setOID_Compromisso (0);
          edLC.setOID_Nota_Fiscal ("");
          edLC.setOID_Lote_Pagamento (0);
          edLC.setOID_Lote_Compromisso ("");
          edLC.setOID_Solicitacao ("");
          edLC.setOID_Acerto ("0");
          edLC.setOID_Movimento_Servico ("");
          edLC.setOID_Ordem_Frete ("");
          edLC.setOID_Caixa ("");
          edLC.setOid_movimento_duplicata ("");
          edLC.setOID_Conta_Zera_Resultado (OID_Conta_Resultado);
          edLC.setDM_Stamp (" ");

          edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
          edLC.setDT_Lancamento (ed.getDT_Lancamento_Final ());
          edLC.setDT_Stamp (Data.getDataDMY ());
          edLC.setTx_Chave_Origem (resConta.getString ("oid_Conta"));
          edLC.setOID_Origem (11);
          edLC.setNR_Lote (NR_Lote);

          edLC.setVL_Lancamento (VL_Saldo);

          edLC.setOID_Conta (OID_Conta_Resultado);
          edLC.setCD_Historico (OID_Historico);
          edLC.setDM_Acao (dm_debito_credito_resultado);
          edLC.setNM_Complementar (NM_Complementar);

          edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

          NR_Lote = edLC.getNR_Lote ();

          edLC.setOID_Conta (resConta.getLong ("oid_Conta"));
          edLC.setCD_Historico (OID_Historico);
          edLC.setDM_Acao (dm_debito_credito_conta);
          edLC.setNM_Complementar (NM_Complementar);

          this.inclui_Lancamento_Contabil (edLC);

        }

        sql = " UPDATE Contas SET ";
        sql += " vl_saldo_zera_resultado = " + VL_Saldo;
        sql += " WHERE oid_Conta = " + resConta.getLong ("oid_Conta");

        executasql.executarUpdate (sql);

      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem (exc.getMessage ());
      exc.printStackTrace ();
      excecoes.setMetodo ("inclui lancamentos");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public long inclui_Lancamento_Contabil (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    Movimento_ContabilED movimento_contabil = new Movimento_ContabilED ();

    try {

    Movimento_Contabil_TempED edMovCtb = new Movimento_Contabil_TempED ();
    edMovCtb.setDt_Movimento (ed.getDT_Lancamento ());
    if (!edMovCtb.isPeriodo_Aberto ()) {
      throw new Excecoes ("Per�odo fechado !!! " + ed.getDT_Lancamento ());
    }

     sql = " SELECT oid_Conta  " +
           " FROM Contas " +
           " WHERE Contas.oid_Conta = " + ed.getOID_Conta ();

      ResultSet resLocal = this.executasql.executarConsulta (sql);

      while (resLocal.next ()) {

	      movimento_contabil.setDt_Movimento (ed.getDT_Lancamento ());
	      movimento_contabil.setDt_Digitacao (ed.getDT_Lancamento ());
	      movimento_contabil.setTx_Chave_Origem (ed.getTx_Chave_Origem ());
	      movimento_contabil.setOid_Unidade (ed.getOID_Unidade_Contabil ());
	      movimento_contabil.setOid_Origem (ed.getOID_Origem ());
	      movimento_contabil.setOid_Conta (ed.getOID_Conta ());
	      movimento_contabil.setTx_Historico_Complementar (ed.getNM_Complementar ().toUpperCase());
	      movimento_contabil.setDm_Debito_Credito (ed.getDM_Acao ());
	      movimento_contabil.setVl_Lancamento (ed.getVL_Lancamento ());
	      movimento_contabil.setOid_Historico (ed.getCD_Historico ());
	      movimento_contabil.setOid_Centro_Custo (0);
	      movimento_contabil.setNr_Lote (ed.getNR_Lote ());

	      sql = " SELECT nm_historico " +
	          " FROM historicos" +
	          " WHERE historicos.oid_historico = " + ed.getCD_Historico ();

	      resLocal = this.executasql.executarConsulta (sql);

	      while (resLocal.next ()) {
	        movimento_contabil.setTx_Historico (resLocal.getString ("nm_historico"));
	      }
	      movimento_contabil = new Movimento_ContabilBD (this.executasql).inclui (movimento_contabil);
      }
      return movimento_contabil.getNr_Lote ();
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      exc.printStackTrace ();
      excecoes.setMetodo ("inclui lancamentos");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public long inclui_Lancamento_Contabil_Nota (Lancamento_ContabilED ed) throws Excecoes {

	    String sql = null;
	    long valOid = 0;
	    Movimento_ContabilED movimento_contabil = new Movimento_ContabilED ();

	    try {

	    Movimento_Contabil_TempED edMovCtb = new Movimento_Contabil_TempED ();
	    edMovCtb.setDt_Movimento (ed.getDT_Lancamento ());
	    if (!edMovCtb.isPeriodo_Aberto ()) {
	      throw new Excecoes ("Per�odo fechado !!! " + ed.getDT_Lancamento ());
	    }

	     sql = " SELECT oid_Conta  " +
	           " FROM Contas " +
	           " WHERE Contas.oid_Conta = " + ed.getOID_Conta ();

	      ResultSet resLocal = this.executasql.executarConsulta (sql);

	      while (resLocal.next ()) {

		     ResultSet rs = executasql.executarConsulta("select oid_lancamento as result from lancamentos_contabeis order by oid_lancamento desc limit 1");

			 while (rs.next()){
				 valOid = rs.getLong("result");
			 }
			 ed.setOID_Lancamento(++valOid);

		     sql = " insert into " +
		          " lancamentos_contabeis (" +
		          " OID_lancamento," +
		          " OID_Lote_Pagamento," +
		          " OID_Lote_Pagamento_Compensacao," +
		          " dm_acao," +
		          " vl_lancamento," +
		          " oid_conta," +
		          " dt_stamp," +
		          " dt_lancamento," +
		          " usuario_stamp," +
		          " dm_stamp," +
		          " oid_Historico," +
		          " nm_complementar," +
		          " oid_unidade_contabil," +
		          " oid_nota_fiscal," +
		          " oid_lote_compromisso," +
		          " OID_movimento_duplicata," +
		          " OID_Conhecimento," +
		          " OID_Ordem_Frete," +
		          " OID_Movimento_Conta_Corrente," +
		          " OID_Conta_Zera_Resultado," +
		          " OID_Acerto," +
		          " oid_fatura_compromisso" +
		          ") values ";
		      sql += "(" +
		          ed.getOID_Lancamento () + "," +
		          ed.getOID_Lote_Pagamento () + "," +
		          ed.getOID_Lote_Pagamento_Compensacao () + ",'" +
		          ed.getDM_Acao () + "'," +
		          ed.getVL_Lancamento () + "," +
		          ed.getOID_Conta () + ",'" +
		          ed.getDT_Stamp () + "','" +
		          ed.getDT_Lancamento () + "','" +
		          ed.getUsuario_Stamp () + "','" +
		          ed.getDM_Stamp () + "'," +
		          ed.getCD_Historico () + ",'" +
		          ed.getNM_Complementar ().toUpperCase() + "'," +
		          ed.getOID_Unidade_Contabil () + ",'" +
		          ed.getOID_Nota_Fiscal () + "','" +
		          ed.getOID_Lote_Compromisso () + "','" +
		          ed.getOid_movimento_duplicata () + "','" +
		          ed.getOID_Conhecimento () + "','" +
		          ed.getOID_Ordem_Frete () + "'," +
		          ed.getOID_Movimento_Conta_Corrente () + "," +
		          ed.getOID_Conta_Zera_Resultado () + "," +
		          ed.getOID_Acerto () + "," +
		          ed.getOid_fatura_compromisso() +
		          ")";
		      executasql.executarUpdate (sql);

		      movimento_contabil.setDt_Movimento (ed.getDT_Lancamento ());
		      movimento_contabil.setDt_Digitacao (ed.getDT_Lancamento ());
		      movimento_contabil.setTx_Chave_Origem (ed.getTx_Chave_Origem ());
		      movimento_contabil.setOid_Unidade (ed.getOID_Unidade_Contabil ());
		      movimento_contabil.setOid_Origem (ed.getOID_Origem ());
		      movimento_contabil.setOid_Conta (ed.getOID_Conta ());
		      movimento_contabil.setTx_Historico_Complementar (ed.getNM_Complementar ().toUpperCase());
		      movimento_contabil.setDm_Debito_Credito (ed.getDM_Acao ());
		      movimento_contabil.setVl_Lancamento (ed.getVL_Lancamento ());
		      movimento_contabil.setOid_Historico (ed.getCD_Historico ());
		      movimento_contabil.setOid_Centro_Custo (0);
		      movimento_contabil.setNr_Lote (ed.getNR_Lote ());

		      sql = " SELECT nm_historico " +
		          " FROM historicos" +
		          " WHERE historicos.oid_historico = " + ed.getCD_Historico ();

		      resLocal = this.executasql.executarConsulta (sql);

		      while (resLocal.next ()) {
		        movimento_contabil.setTx_Historico (resLocal.getString ("nm_historico"));
		      }
		      movimento_contabil = new Movimento_ContabilBD (this.executasql).inclui (movimento_contabil);
	      }
	      return movimento_contabil.getNr_Lote ();
	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      exc.printStackTrace ();
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }


  public void deleta_CTB_Lote_Pagamento (Lote_PagamentoED ed) throws Excecoes {

    String sql = null;

    try {
      sql = "update lotes_pagamentos set dm_contabilizado = null Where OID_Lote_Pagamento =" + ed.getOid_Lote_Pagamento ();

      int o = executasql.executarUpdate (sql);

      sql = "delete from lancamentos_contabeis Where OID_Lote_Pagamento = " + ed.getOid_Lote_Pagamento ();
      int i = executasql.executarUpdate (sql);
      Movimento_ContabilED movimento_contabil = new Movimento_ContabilED ();

      movimento_contabil.setTx_Chave_Origem (String.valueOf (ed.getOid_Lote_Pagamento ()));
      movimento_contabil.setOid_Origem (3);
      new Movimento_ContabilBD (this.executasql).desfaz_CTB (movimento_contabil);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("deleta_CTB_Lote_Pagamento(Lote_PagamentoED ed)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta_CTB_Movimento_Conta_Corrente (Movimento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;

    try {
      if (ed.getOid_Movimento_Conta_Corrente () > 0) {
        sql = "delete from lancamentos_contabeis Where OID_Movimento_Conta_Corrente = " + ed.getOid_Movimento_Conta_Corrente ();
        int i = executasql.executarUpdate (sql);
        Movimento_ContabilED movimento_contabil = new Movimento_ContabilED ();

        movimento_contabil.setTx_Chave_Origem (String.valueOf (ed.getOid_Movimento_Conta_Corrente ()));
        movimento_contabil.setOid_Origem (10);
        new Movimento_ContabilBD (this.executasql).desfaz_CTB (movimento_contabil);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("deleta_CTB_Movimento_Conta_Corrente(Lote_PagamentoED ed)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta_CTB_Movimento_Duplicata (Movimento_DuplicataED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "update Movimentos_Duplicatas set dm_contabilizado = null Where oid_movimento_duplicata ='" + ed.getOid_Movimento_Duplicata () + "'";

      int o = executasql.executarUpdate (sql);

      sql = "delete from lancamentos_contabeis Where Oid_Movimento_Duplicata = '" + ed.getOid_Movimento_Duplicata () + "'";
      int i = executasql.executarUpdate (sql);
      Movimento_ContabilED movimento_contabil = new Movimento_ContabilED ();

      movimento_contabil.setTx_Chave_Origem (String.valueOf (ed.getOid_Duplicata ()));
      movimento_contabil.setOid_Origem (8);
      new Movimento_ContabilBD (this.executasql).desfaz_CTB (movimento_contabil);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("deleta_CTB_Movimento_Duplicata(Movimento_DuplicataED ed)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta_CTB_Conhecimento (ConhecimentoED ed) throws Excecoes {

    String sql = null;

    try {
      sql = "update Conhecimentos set dm_contabilizado = null Where OID_Conhecimento ='" + ed.getOID_Conhecimento () + "'";

      int o = executasql.executarUpdate (sql);

      sql = "delete from lancamentos_contabeis Where OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
      int i = executasql.executarUpdate (sql);
      Movimento_ContabilED movimento_contabil = new Movimento_ContabilED ();

      movimento_contabil.setTx_Chave_Origem (String.valueOf (ed.getOID_Conhecimento ()));
      movimento_contabil.setOid_Origem (2);
      new Movimento_ContabilBD (this.executasql).desfaz_CTB (movimento_contabil);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("deleta_CTB_Conhecimento(ConhecimentoED ed)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta_CTB_Acerto (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;

    try {
      sql = "update Acertos set dm_contabilizado = null Where OID_Acerto =" + ed.getOID_Acerto ();

      int o = executasql.executarUpdate (sql);

      sql = "delete from lancamentos_contabeis Where OID_Acerto = " + ed.getOID_Acerto ();
      int i = executasql.executarUpdate (sql);
      Movimento_ContabilED movimento_contabil = new Movimento_ContabilED ();

      movimento_contabil.setTx_Chave_Origem (String.valueOf (ed.getOID_Acerto ()));
      movimento_contabil.setOid_Origem (6);
      new Movimento_ContabilBD (this.executasql).desfaz_CTB (movimento_contabil);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("deleta_CTB_Acerto(Lancamento_ContabilED ed)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta_CTB_Ordem_Frete (Ordem_FreteED ed) throws Excecoes {

    String sql = null;

    try {
      sql = "update Ordens_Fretes set dm_contabilizado = null Where OID_Ordem_Frete ='" + ed.getOID_Ordem_Frete () + "'";

      int o = executasql.executarUpdate (sql);

      sql = "delete from lancamentos_contabeis Where OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

      int i = executasql.executarUpdate (sql);
      Movimento_ContabilED movimento_contabil = new Movimento_ContabilED ();

      movimento_contabil.setTx_Chave_Origem (String.valueOf (ed.getOID_Ordem_Frete ()));
      movimento_contabil.setOid_Origem (4);
      new Movimento_ContabilBD (this.executasql).desfaz_CTB (movimento_contabil);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("deleta_CTB_Ordem_Frete(Ordem_FreteED ed)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta_CTB_Nota_Fiscal (Nota_Fiscal_CompraED ed) throws Excecoes {

    String sql = null;

    try {
	  Movimento_Contabil_TempED edMovCtb = new Movimento_Contabil_TempED ();
	  edMovCtb.setDt_Movimento (ed.getDt_entrada());
	  if (!edMovCtb.isPeriodo_Aberto ()) {
	    throw new Excecoes ("Per�odo fechado na Contabilidade !!! " + ed.getDt_entrada());
	  }

      sql = " update Notas_Fiscais_Transacoes set dm_contabilizado = null Where OID_Nota_Fiscal ='" + ed.getOid_nota_fiscal () + "'";
      executasql.executarUpdate (sql);

      Movimento_ContabilED movimento_contabil = new Movimento_ContabilED ();
      movimento_contabil.setTx_Chave_Origem (String.valueOf (ed.getOid_nota_fiscal ()));
      movimento_contabil.setOid_Origem (7);
      new Movimento_ContabilBD (this.executasql).desfaz_CTB (movimento_contabil);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("deleta_CTB_Nota_Fiscal(Nota_Fiscal_CompraED ed)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void inclui (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    Nota_Fiscal_EletronicaED data = new Nota_Fiscal_EletronicaED ();

    try {

      ResultSet rs = executasql.executarConsulta ("select max(oid_lancamento) as result from lancamentos_contabeis ");

      //pega proximo valor da chave
      while (rs.next ()) {
        valOid = rs.getLong ("result");
        ed.setOID_Lancamento (++valOid);
      }
      data.setOid_nota_fiscal (ed.getOID_Nota_Fiscal ());
      data = new Nota_Fiscal_EletronicaBD (this.executasql).getByRecord (data);

      if (ed.getOID_Lote_Pagamento () > 0) {
        sql = " insert into lancamentos_contabeis (OID_lancamento, OID_Lote_Pagamento, dm_acao, vl_lancamento, oid_conta, dt_stamp, dt_lancamento, usuario_stamp, dm_stamp,oid_Historico, nm_complementar,oid_unidade_contabil, oid_nota_fiscal) values ";
        sql += "(" + ed.getOID_Lancamento () + "," + ed.getOID_Lote_Pagamento () + ",'" + ed.getDM_Acao () + "'," + ed.getVL_Lancamento () + "," + ed.getOID_Conta () + ",'" + ed.getDT_Stamp () + "','" + ed.getDT_Lancamento () + "','" + ed.getUsuario_Stamp () + "','" + ed.getDM_Stamp () + "'," + ed.getCD_Historico () + ",'" + ed.getNM_Complementar () + "'," + ed.getOID_Unidade_Contabil () + ", ' ')";
      }

      if ( (ed.getOID_Compromisso () > 0)) {
        sql = " insert into lancamentos_contabeis (OID_lancamento, OID_Compromisso, OID_Nota_Fiscal, dm_acao, vl_lancamento, oid_conta, dt_stamp, dt_lancamento, usuario_stamp, dm_stamp,oid_Historico, nm_complementar,oid_unidade_contabil) values ";
        sql += "(" + ed.getOID_Lancamento () + "," + ed.getOID_Compromisso () + ",'" + ed.getOID_Nota_Fiscal () + "','" + ed.getDM_Acao () + "'," + ed.getVL_Lancamento () + "," + ed.getOID_Conta () + ",'" + ed.getDT_Stamp () + "','" + ed.getDT_Lancamento () + "','" + ed.getUsuario_Stamp () + "','" + ed.getDM_Stamp () + "'," + ed.getCD_Historico () + ",'" + ed.getNM_Complementar () + "'," + ed.getOID_Unidade_Contabil () + ")";
      }

      if (ed.getOID_Nota_Fiscal () != null && !ed.getOID_Nota_Fiscal ().equals ("")
          && !ed.getOID_Nota_Fiscal ().equals ("null")) {
        sql = " insert into lancamentos_contabeis (OID_lancamento, OID_Nota_Fiscal, dm_acao, vl_lancamento, oid_conta, dt_stamp, dt_lancamento, usuario_stamp, dm_stamp,oid_Historico, nm_complementar,oid_unidade_contabil) values ";
        sql += "(" + ed.getOID_Lancamento () + ",'" + ed.getOID_Nota_Fiscal () + "','" + ed.getDM_Acao () + "'," + ed.getVL_Lancamento () + "," + ed.getOID_Conta () + ",'" + ed.getDT_Stamp () + "','" + data.getDt_entrada () + "','" + ed.getUsuario_Stamp () + "','" + ed.getDM_Stamp () + "'," + ed.getCD_Historico () + ",'" + ed.getNM_Complementar () + "'," + ed.getOID_Unidade_Contabil () + ")";
      }

      if (ed.getOID_Solicitacao () != null && !ed.getOID_Solicitacao ().equals ("")
          && !ed.getOID_Solicitacao ().equals ("null")) {
        sql = " insert into lancamentos_contabeis (OID_lancamento, OID_Solicitacao, OID_Nota_Fiscal, dm_acao, vl_lancamento, oid_conta, dt_stamp, dt_lancamento, usuario_stamp, dm_stamp,oid_Historico, nm_complementar,oid_unidade_contabil) values ";
        sql += "(" + ed.getOID_Lancamento () + "," + ed.getOID_Solicitacao () + ",'" + ed.getOID_Nota_Fiscal () + "','" + ed.getDM_Acao () + "'," + ed.getVL_Lancamento () + "," + ed.getOID_Conta () + ",'" + ed.getDT_Stamp () + "','" + ed.getDT_Lancamento () + "','" + ed.getUsuario_Stamp () + "','" + ed.getDM_Stamp () + "'," + ed.getCD_Historico () + ",'" + ed.getNM_Complementar () + "'," + ed.getOID_Unidade_Contabil () + ")";
      }

      if (ed.getOID_Acerto () != null && !ed.getOID_Acerto ().equals ("null")
          && !ed.getOID_Acerto ().equals ("")) {
        sql = " insert into lancamentos_contabeis (OID_lancamento, OID_Acerto, dm_acao, vl_lancamento, oid_conta, dt_stamp, dt_lancamento, usuario_stamp, dm_stamp,oid_Historico, nm_complementar,oid_unidade_contabil, oid_nota_fiscal) values ";
        sql += "(" + ed.getOID_Lancamento () + "," + ed.getOID_Acerto () + ",'" + ed.getDM_Acao () + "'," + ed.getVL_Lancamento () + "," + ed.getOID_Conta () + ",'" + ed.getDT_Stamp () + "','" + ed.getDT_Lancamento () + "','" + ed.getUsuario_Stamp () + "','" + ed.getDM_Stamp () + "'," + ed.getCD_Historico () + ",'" + ed.getNM_Complementar () + "'," + ed.getOID_Unidade_Contabil () + ", ' ')";
      }

      if (ed.getOID_Ordem_Frete () != null && !ed.getOID_Ordem_Frete ().equals ("null")
          && !ed.getOID_Ordem_Frete ().equals ("")) {
        sql = " insert into lancamentos_contabeis (OID_lancamento, OID_Ordem_Frete, dm_acao, vl_lancamento, oid_conta, dt_stamp, dt_lancamento, usuario_stamp, dm_stamp,oid_Historico, nm_complementar,oid_unidade_contabil, oid_nota_fiscal) values ";
        sql += "(" + ed.getOID_Lancamento () + ",'" + ed.getOID_Ordem_Frete () + "','" + ed.getDM_Acao () + "'," + ed.getVL_Lancamento () + "," + ed.getOID_Conta () + ",'" + ed.getDT_Stamp () + "','" + ed.getDT_Lancamento () + "','" + ed.getUsuario_Stamp () + "','" + ed.getDM_Stamp () + "'," + ed.getCD_Historico () + ",'" + ed.getNM_Complementar () + "'," + ed.getOID_Unidade_Contabil () + ", ' ')";
      }

      if (ed.getOID_Movimento_Servico () != null && !ed.getOID_Movimento_Servico ().equals ("null")
          && !ed.getOID_Movimento_Servico ().equals ("")) {
        sql = " insert into lancamentos_contabeis (OID_lancamento, OID_Movimento, dm_acao, vl_lancamento, oid_conta, dt_stamp, dt_lancamento, usuario_stamp, dm_stamp,oid_Historico, nm_complementar,oid_unidade_contabil, oid_nota_fiscal) values ";
        sql += "(" + ed.getOID_Lancamento () + ",'" + ed.getOID_Movimento_Servico () + "','" + ed.getDM_Acao () + "'," + ed.getVL_Lancamento () + "," + ed.getOID_Conta () + ",'" + ed.getDT_Stamp () + "','" + ed.getDT_Lancamento () + "','" + ed.getUsuario_Stamp () + "','" + ed.getDM_Stamp () + "'," + ed.getCD_Historico () + ",'" + ed.getNM_Complementar () + "'," + ed.getOID_Unidade_Contabil () + ", ' ')";
      }
      if (ed.getOID_Caixa () != null && !ed.getOID_Caixa ().equals ("")
          && !ed.getOID_Caixa ().equals ("null")) {
        sql = " insert into lancamentos_contabeis (OID_lancamento, OID_Caixa, dm_acao, vl_lancamento, oid_conta, dt_stamp, dt_lancamento, usuario_stamp, dm_stamp,oid_Historico, nm_complementar,oid_unidade_contabil, oid_nota_fiscal) values ";
        sql += "(" + ed.getOID_Lancamento () + "," + ed.getOID_Caixa () + ",'" + ed.getDM_Acao () + "'," + ed.getVL_Lancamento () + "," + ed.getOID_Conta () + ",'" + ed.getDT_Stamp () + "','" + ed.getDT_Lancamento () + "','" + ed.getUsuario_Stamp () + "','" + ed.getDM_Stamp () + "'," + ed.getCD_Historico () + ",'" + ed.getNM_Complementar () + "'," + ed.getOID_Unidade_Contabil () + ", ' ')";
      }
      if (ed.getOid_movimento_duplicata () != null && !ed.getOid_movimento_duplicata ().equals ("")
          && !ed.getOid_movimento_duplicata ().equals ("null")) {
        sql = " insert into lancamentos_contabeis (OID_lancamento, OID_movimento_duplicata, dm_acao, vl_lancamento, oid_conta, dt_stamp, dt_lancamento, usuario_stamp, dm_stamp,oid_Historico, nm_complementar,oid_unidade_contabil, oid_nota_fiscal) values ";
        sql += "(" + ed.getOID_Lancamento () + "," + ed.getOid_movimento_duplicata () + ",'" + ed.getDM_Acao () + "'," + ed.getVL_Lancamento () + "," + ed.getOID_Conta () + ",'" + ed.getDT_Stamp () + "','" + ed.getDT_Lancamento () + "','" + ed.getUsuario_Stamp () + "','" + ed.getDM_Stamp () + "'," + ed.getCD_Historico () + ",'" + ed.getNM_Complementar () + "'," + ed.getOID_Unidade_Contabil () + ", ' ')";
      }


      if (ed.getOID_Lote_Posto () > 0) {
        sql = " insert into lancamentos_contabeis (OID_lancamento, OID_Lote_Posto, dm_acao, vl_lancamento, oid_conta, dt_stamp, dt_lancamento, usuario_stamp, dm_stamp,oid_Historico, nm_complementar,oid_unidade_contabil, oid_nota_fiscal) values ";
        sql += "(" + ed.getOID_Lancamento () + "," + ed.getOID_Lote_Posto () + ",'" + ed.getDM_Acao () + "'," + ed.getVL_Lancamento () + "," + ed.getOID_Conta () + ",'" + ed.getDT_Stamp () + "','" + ed.getDT_Lancamento () + "','" + ed.getUsuario_Stamp () + "','" + ed.getDM_Stamp () + "'," + ed.getCD_Historico () + ",'" + ed.getNM_Complementar () + "'," + ed.getOID_Unidade_Contabil () + ", ' ')";
      }

      int i = executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      exc.printStackTrace ();
      excecoes.setMetodo ("inclui lancamentos");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void altera (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {

      sql = "update lancamentos_contabeis " +
          "set dm_acao ='" + ed.getDM_Acao () + "'" +
          ", vl_lancamento =" + ed.getVL_Lancamento () +
          ", oid_conta =" + ed.getOID_Conta () +
          ", dt_stamp ='" + Data.getDataDMY () + "'" +
          ", usuario_stamp ='" + ed.getUsuario_Stamp () + "'" +
          ", dm_stamp =' ' " +
          ", oid_Historico =" + ed.getCD_Historico () +
          ", nm_complementar ='" + ed.getNM_Complementar () + "' " +
          "Where OID_lancamento =" + ed.getOID_Lancamento ();

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    String DM_Impresso = null;

    try {
      sql = "delete from lancamentos_contabeis Where OID_lancamento =" + ed.getOID_Lancamento ();

      int i = executasql.executarUpdate (sql);

      sql = "delete from lancamentos_centros_custos Where OID_lancamento =" + ed.getOID_Lancamento ();

      i = executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, pr.OID_Unidade_Contabil, nf.nr_nota_fiscal, pr.dm_acao, nf.vl_nota_fiscal, pr.oid_Historico, his.nm_historico, pr.oid_nota_fiscal, pr.oid_conta, cc.nm_conta, cc.cd_conta, pr.vl_lancamento, pr.nm_complementar " +
          "From  lancamentos_contabeis pr, notas_fiscais_transacoes nf, contas cc, Historicos his " +
          "Where pr.oid_nota_fiscal = nf.oid_nota_fiscal and pr.oid_conta=cc.oid_conta" +
          " and his.oid_Historico=pr.oid_Historico";

      if (String.valueOf (ed.getOID_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getOID_Nota_Fiscal ()).equals ("") &&
          !String.valueOf (ed.getOID_Nota_Fiscal ()).equals ("null")) {
        sql += " AND pr.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";
      }
      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      sql += " Order by pr.oid_Lancamento ";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      //popula
      while (res.next ()) {
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));
        edVolta.setOID_Nota_Fiscal (res.getString ("oid_nota_fiscal"));
        edVolta.setDM_Acao (res.getString ("dm_acao"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));

        edVolta.setNR_Nota_Fiscal (res.getString ("nr_nota_fiscal"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setCD_Historico (res.getLong ("oid_Historico"));

        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));
        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }

        edVolta.setVL_Lancamento (valor);

        sql = "Select CD_Unidade from Unidades where OID_Unidade=" + res.getLong ("OID_Unidade_Contabil");
        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);

        while (resLocal.next ()) {
          edVolta.setCD_Unidade_Contabil (resLocal.getString ("CD_Unidade"));
        }
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar -.");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList Lista_Contabil_Nota_Fiscal (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, " +
          "pr.OID_Unidade_Contabil, nf.nr_nota_fiscal, pr.dm_acao, " +
          "nf.vl_nota_fiscal, pr.oid_Historico, his.nm_historico, " +
          "pr.oid_nota_fiscal, pr.oid_conta, cc.nm_conta, cc.cd_conta, " +
          "pr.vl_lancamento, pr.nm_complementar, Unidades.CD_Unidade " +
          "From  lancamentos_contabeis pr, notas_fiscais_transacoes nf, " +
          "contas cc, Historicos his, Unidades " +
          "Where pr.oid_nota_fiscal = nf.oid_nota_fiscal " +
          "and pr.oid_conta=cc.oid_conta " +
          "and his.oid_Historico=pr.oid_Historico " +
          "and pr.OID_Unidade_Contabil=Unidades.OID_Unidade ";

      if (String.valueOf (ed.getOID_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getOID_Nota_Fiscal ()).equals ("") &&
          !String.valueOf (ed.getOID_Nota_Fiscal ()).equals ("null")) {
        sql += " AND pr.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";
      }
      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      sql += " Order by pr.oid_Lancamento ";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      //popula
      while (res.next ()) {
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));
        edVolta.setOID_Nota_Fiscal (res.getString ("oid_nota_fiscal"));
        edVolta.setDM_Acao (res.getString ("dm_acao"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));

        edVolta.setNR_Nota_Fiscal (res.getString ("nr_nota_fiscal"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setCD_Historico (res.getLong ("oid_Historico"));

        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));
        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }

        edVolta.setVL_Lancamento (valor);

        edVolta.setCD_Unidade_Contabil (res.getString ("CD_Unidade"));

        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar -.");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Lancamento_ContabilED getByRecord (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    double valor = 0;
    Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, pr.OID_Unidade_Contabil, nf.nr_nota_fiscal, pr.dm_acao, nf.vl_nota_fiscal, pr.oid_Historico, his.nm_historico, his.cd_historico, pr.oid_nota_fiscal, pr.oid_conta, cc.nm_conta, cc.cd_conta, pr.vl_lancamento, pr.nm_complementar " +
          "From  lancamentos_contabeis pr, notas_fiscais_transacoes nf, contas cc, Historicos his " +
          "Where pr.oid_nota_fiscal = nf.oid_nota_fiscal " +
          " and his.oid_Historico=pr.oid_Historico" +
          " and pr.oid_conta=cc.oid_conta";

      if (String.valueOf (ed.getOID_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getOID_Nota_Fiscal ()).equals ("") &&
          !String.valueOf (ed.getOID_Nota_Fiscal ()).equals ("null")) {
        sql += " AND pr.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";
      }
      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      if (String.valueOf (ed.getCD_Conta ()) != null &&
          !String.valueOf (ed.getCD_Conta ()).equals ("") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("null") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("0")) {
        sql += " AND cc.cd_conta = '" + ed.getCD_Conta () + "'";
      }

      sql += " Order by pr.oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));
        edVolta.setOID_Nota_Fiscal (res.getString ("oid_nota_fiscal"));
        edVolta.setDM_Acao (res.getString ("dm_acao"));
        edVolta.setOID_Conta (res.getLong ("oid_conta"));

        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));

        edVolta.setNR_Nota_Fiscal (res.getString ("nr_nota_fiscal"));
        edVolta.setOID_Historico (res.getLong ("oid_Historico"));
        edVolta.setCD_Historico (res.getLong ("cd_historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));
        edVolta.setOID_Unidade_Contabil (res.getLong ("OID_Unidade_Contabil"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }
        edVolta.setVL_Lancamento (valor);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public Lancamento_ContabilED getByRecord_Nota_Fiscal (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    double valor = 0;
    Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, " +
          "pr.OID_Unidade_Contabil, nf.nr_nota_fiscal, pr.dm_acao, " +
          "nf.vl_nota_fiscal, pr.oid_Historico, his.nm_historico, " +
          "his.cd_historico, pr.oid_nota_fiscal, pr.oid_conta, " +
          "cc.nm_conta, cc.cd_conta, pr.vl_lancamento, pr.nm_complementar " +
          "From  lancamentos_contabeis pr, notas_fiscais_transacoes nf, " +
          "contas cc, Historicos his " +
          "Where pr.oid_nota_fiscal = nf.oid_nota_fiscal " +
          " and his.oid_Historico=pr.oid_Historico" +
          " and pr.oid_conta=cc.oid_conta";

      if (String.valueOf (ed.getOID_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getOID_Nota_Fiscal ()).equals ("") &&
          !String.valueOf (ed.getOID_Nota_Fiscal ()).equals ("null")) {
        sql += " AND pr.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";
      }
      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      if (String.valueOf (ed.getCD_Conta ()) != null &&
          !String.valueOf (ed.getCD_Conta ()).equals ("") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("null") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("0")) {
        sql += " AND cc.cd_conta = '" + ed.getCD_Conta () + "'";
      }

      sql += " Order by pr.oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));
        edVolta.setOID_Nota_Fiscal (res.getString ("oid_nota_fiscal"));
        edVolta.setDM_Acao (res.getString ("dm_acao"));
        edVolta.setOID_Conta (res.getLong ("oid_conta"));

        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));

        edVolta.setNR_Nota_Fiscal (res.getString ("nr_nota_fiscal"));
        edVolta.setOID_Historico (res.getLong ("oid_Historico"));
        edVolta.setCD_Historico (res.getLong ("cd_historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));
        edVolta.setOID_Unidade_Contabil (res.getLong ("OID_Unidade_Contabil"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }
        edVolta.setVL_Lancamento (valor);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void alteraExportado (long oid_unidade , String dt_inicio , String dt_fim) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {

      sql = "update notas_fiscais_transacoes set dm_finalizado='E' ";
      sql += "where dt_entrada between '" + dt_inicio + "' and '" + dt_fim + "' ";
      sql += "and oid_unidade_contabil=" + oid_unidade;

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar status para exportado");
      excecoes.setMetodo ("alteraExportado");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista_Contabil_Compromisso (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, pr.OID_Unidade_Contabil, Com.nr_compromisso, pr.dm_acao, Com.vl_compromisso, pr.oid_Historico, his.nm_historico, pr.oid_compromisso, pr.oid_conta, cc.nm_conta, cc.cd_conta, pr.vl_lancamento, pr.nm_complementar " +
          "From  lancamentos_contabeis pr, Compromissos Com, contas cc, Historicos his " +
          "Where pr.oid_Compromisso = Com.oid_Compromisso and pr.oid_conta=cc.oid_conta" +
          " and his.oid_Historico=pr.oid_Historico";

      if (String.valueOf (ed.getOID_Compromisso ()) != null &&
          !String.valueOf (ed.getOID_Compromisso ()).equals ("0") &&
          !String.valueOf (ed.getOID_Compromisso ()).equals ("") &&
          !String.valueOf (ed.getOID_Compromisso ()).equals ("null")) {
        sql += " AND pr.OID_Compromisso = '" + ed.getOID_Compromisso () + "'";
      }

      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      if (String.valueOf (ed.getOid_movimento_duplicata ()) != null &&
          !String.valueOf (ed.getOid_movimento_duplicata ()).equals ("") &&
          !String.valueOf (ed.getOid_movimento_duplicata ()).equals ("null") &&
          !String.valueOf (ed.getOid_movimento_duplicata ()).equals ("0")) {
        sql += " AND pr.oid_movimento_duplicata = '" + ed.getOid_movimento_duplicata () + "'";
      }
      sql += " Order by pr.oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      while (res.next ()) {
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));

        edVolta.setOID_Compromisso (res.getLong ("oid_Compromisso"));

        edVolta.setDM_Acao (res.getString ("dm_acao"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));

        edVolta.setNR_Compromisso (res.getString ("nr_Compromisso"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setCD_Historico (res.getLong ("oid_Historico"));

        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }

        edVolta.setVL_Lancamento (valor);

        sql = "Select CD_Unidade from Unidades where OID_Unidade=" + res.getLong ("OID_Unidade_Contabil");
        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);

        while (resLocal.next ()) {
          edVolta.setCD_Unidade_Contabil (resLocal.getString ("CD_Unidade"));
        }
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar - compromisso");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList lista_Contabil_Solicitacao (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, pr.OID_Unidade_Contabil, " +
          " Com.nr_Solicitacao, pr.dm_acao, Com.vl_Solicitacao, pr.oid_Historico, " +
          " his.nm_historico, pr.oid_Solicitacao, pr.oid_conta, cc.nm_conta, " +
          " cc.cd_conta, pr.vl_lancamento, pr.nm_complementar " +
          "From  lancamentos_contabeis pr, Solicitacoes_Transacoes Com, contas cc, " +
          "Historicos his " +
          "Where pr.oid_Solicitacao = Com.oid_Solicitacao and pr.oid_conta=cc.oid_conta" +
          " and his.oid_Historico=pr.oid_Historico";

      if (String.valueOf (ed.getOID_Solicitacao ()) != null &&
          !String.valueOf (ed.getOID_Solicitacao ()).equals ("") &&
          !String.valueOf (ed.getOID_Solicitacao ()).equals ("null")) {
        sql += " AND pr.OID_Solicitacao = " + ed.getOID_Solicitacao ();
      }

      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      sql += " Order by pr.oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      while (res.next ()) {
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));

        edVolta.setOID_Solicitacao (res.getString ("oid_Solicitacao"));

        edVolta.setDM_Acao (res.getString ("dm_acao"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));

        edVolta.setNR_Solicitacao (res.getLong ("nr_Solicitacao"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setCD_Historico (res.getLong ("oid_Historico"));

        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }

        edVolta.setVL_Lancamento (valor);

        sql = "Select CD_Unidade from Unidades where OID_Unidade=" + res.getLong ("OID_Unidade_Contabil");
        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);

        while (resLocal.next ()) {
          edVolta.setCD_Unidade_Contabil (resLocal.getString ("CD_Unidade"));
        }
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar - solicitacao");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList lista_Acerto_Completo (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();
    ResultSet rx = null;

    try {

      sql = "select * " +
          "from lancamentos_contabeis " +
          "where oid_ordem_frete in (select oid_ordem_frete from ordens_fretes where oid_acerto=" + ed.getOID_Acerto () + ") " +
          "or oid_movimento = " + ed.getOID_Acerto () + " " +
          //"or oid_solicitacao in  (select oid_solicitacao from solicitacoes_transacoes where oid_acerto="+ed.getOID_Acerto()+") "+
          "or oid_acerto in (select oid_movimento_ordem_servico " +
          "from movimentos_ordens_servicos, acertos " +
          "where movimentos_ordens_servicos.oid_ordem_servico=acertos.oid_ordem_servico " +
          "and oid_acerto=" + ed.getOID_Acerto () + ")";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      while (res.next ()) {
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));

        edVolta.setOID_Acerto (res.getString ("oid_Acerto"));

        edVolta.setDM_Acao (res.getString ("dm_acao"));
        edVolta.setOID_Movimento_Servico (res.getString ("oid_movimento"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));

        sql = "Select * from contas where oid_conta = " + res.getLong ("oid_conta");

        rx = this.executasql.executarConsulta (sql);
        while (rx.next ()) {
          edVolta.setCD_Conta (rx.getString ("cd_conta"));
          edVolta.setNM_Conta (rx.getString ("nm_conta"));
        }
        rx = null;
        sql = "Select * from Historicos where oid_Historico = " + res.getLong ("oid_Historico");

        rx = this.executasql.executarConsulta (sql);

        while (rx.next ()) {
          edVolta.setCD_Historico (rx.getLong ("cd_historico"));
          edVolta.setNM_Historico (rx.getString ("nm_historico"));
        }

        edVolta.setNM_Complementar (res.getString ("nm_complementar"));
        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }
        edVolta.setVL_Lancamento (valor);

        sql = "Select CD_Unidade from Unidades where OID_Unidade=" + res.getLong ("OID_Unidade_Contabil");
        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);

        while (resLocal.next ()) {
          edVolta.setCD_Unidade_Contabil (resLocal.getString ("CD_Unidade"));
        }
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar todos os lancamentos do acerto");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList lista_Contabil_Acerto (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = " SELECT DISTINCT on(Lancamentos_Contabeis.oid_lancamento) Lancamentos_Contabeis.oid_lancamento, " +
          " Lancamentos_Contabeis.OID_Unidade_Contabil, " +
          " Lancamentos_Contabeis.dm_acao, " +
          " Lancamentos_Contabeis.oid_Historico, " +
          " Lancamentos_Contabeis.oid_Solicitacao, " +
          " Lancamentos_Contabeis.oid_conta, " +
          " Lancamentos_Contabeis.oid_acerto, " +
          " Lancamentos_Contabeis.vl_lancamento, " +
          " Lancamentos_Contabeis.nm_complementar, " +
          " Historicos.nm_historico, " +
          " Contas.nm_conta, " +
          " Contas.cd_conta " +
          " FROM  Lancamentos_Contabeis, Contas, Historicos " +
          " WHERE Lancamentos_Contabeis.oid_conta = Contas.oid_conta " +
          " AND   Lancamentos_Contabeis.oid_Historico=Historicos.oid_Historico";

      if (String.valueOf (ed.getOID_Acerto ()) != null &&
          !String.valueOf (ed.getOID_Acerto ()).equals ("") &&
          !String.valueOf (ed.getOID_Acerto ()).equals ("null")) {
        sql += " AND Lancamentos_Contabeis.OID_Acerto = " + ed.getOID_Acerto ();
      }

      sql += " Order by Lancamentos_Contabeis.oid_Lancamento ";



      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      while (res.next ()) {
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));
        edVolta.setOID_Acerto (res.getString ("oid_Acerto"));
        edVolta.setDM_Acao (res.getString ("dm_acao"));
        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setCD_Historico (res.getLong ("oid_Historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));
        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }
        edVolta.setVL_Lancamento (valor);
        sql = "Select CD_Unidade from Unidades where OID_Unidade=" + res.getLong ("OID_Unidade_Contabil");
        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);

        while (resLocal.next ()) {
          edVolta.setCD_Unidade_Contabil (resLocal.getString ("CD_Unidade"));
        }
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar - movimento");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList lista_Contabil_Movimento_Conta_Corrente (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = " SELECT DISTINCT on(Lancamentos_Contabeis.oid_lancamento) Lancamentos_Contabeis.oid_lancamento, " +
          " Lancamentos_Contabeis.OID_Unidade_Contabil, " +
          " Lancamentos_Contabeis.dm_acao, " +
          " Lancamentos_Contabeis.oid_Historico, " +
          " Lancamentos_Contabeis.oid_Solicitacao, " +
          " Lancamentos_Contabeis.oid_conta, " +
          " Lancamentos_Contabeis.OID_Movimento_Conta_Corrente, " +
          " Lancamentos_Contabeis.vl_lancamento, " +
          " Lancamentos_Contabeis.nm_complementar, " +
          " Historicos.nm_historico, " +
          " Contas.nm_conta, " +
          " Contas.cd_conta " +
          " FROM  Lancamentos_Contabeis, Contas, Historicos " +
          " WHERE Lancamentos_Contabeis.oid_conta = Contas.oid_conta " +
          " AND   Lancamentos_Contabeis.oid_Historico=Historicos.oid_Historico";

      if (String.valueOf (ed.getOID_Movimento_Conta_Corrente ()) != null &&
          !String.valueOf (ed.getOID_Movimento_Conta_Corrente ()).equals ("") &&
          !String.valueOf (ed.getOID_Movimento_Conta_Corrente ()).equals ("null")) {
        sql += " AND Lancamentos_Contabeis.OID_Movimento_Conta_Corrente = " + ed.getOID_Movimento_Conta_Corrente ();
      }

      sql += " Order by Lancamentos_Contabeis.oid_Lancamento ";



      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      while (res.next ()) {
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));
        edVolta.setOID_Movimento_Conta_Corrente (res.getLong ("OID_Movimento_Conta_Corrente"));
        edVolta.setDM_Acao (res.getString ("dm_acao"));
        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setCD_Historico (res.getLong ("oid_Historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));
        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }
        edVolta.setVL_Lancamento (valor);
        sql = "Select CD_Unidade from Unidades where OID_Unidade=" + res.getLong ("OID_Unidade_Contabil");
        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);

        while (resLocal.next ()) {
          edVolta.setCD_Unidade_Contabil (resLocal.getString ("CD_Unidade"));
        }
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar - movimento");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList lista_Contabil_Ordem_Frete (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(Lanc.oid_lancamento) Lanc.oid_lancamento, Lanc.OID_Unidade_Contabil, " +
          "Ord.oid_ordem_frete, Lanc.dm_acao, Lanc.oid_Historico, " +
          "his.nm_historico,  Lanc.oid_conta, cc.nm_conta, " +
          "cc.cd_conta, Lanc.vl_lancamento, Lanc.nm_complementar, Unidades.CD_Unidade " +
          "From  lancamentos_contabeis Lanc, Ordens_Fretes Ord, contas cc, " +
          "Historicos his, Unidades " +
          "Where Lanc.oid_ordem_frete = Ord.oid_ordem_frete and Lanc.oid_conta=cc.oid_conta " +
          "and his.oid_Historico=Lanc.oid_Historico " +
          "and Lanc.OID_Unidade_Contabil=Unidades.OID_Unidade ";

      if (String.valueOf (ed.getOID_Ordem_Frete ()) != null &&
          !String.valueOf (ed.getOID_Ordem_Frete ()).equals ("") &&
          !String.valueOf (ed.getOID_Ordem_Frete ()).equals ("null")) {
        sql += " AND Lanc.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
      }

      sql += " Order by Lanc.oid_Lancamento ";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      while (res.next ()) {
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));
        edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));
        edVolta.setDM_Acao (res.getString ("dm_acao"));
        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setCD_Historico (res.getLong ("oid_Historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));
        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }
        edVolta.setVL_Lancamento (valor);
        edVolta.setCD_Unidade_Contabil (res.getString ("CD_Unidade"));
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar Lancamentos da ordem de frete");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Lancamento_ContabilED getByRecord_Solicitacao (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    double valor = 0;
    Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, pr.dt_lancamento,pr.OID_Unidade_Contabil, nf.nr_Solicitacao, pr.dm_acao, nf.vl_Solicitacao, pr.oid_Historico, his.nm_historico, his.cd_historico, pr.oid_Solicitacao, pr.oid_conta, cc.nm_conta, cc.cd_conta, pr.vl_lancamento, pr.nm_complementar " +
          "From  lancamentos_contabeis pr, Solicitacoes_Transacoes nf, contas cc, Historicos his " +
          "Where pr.oid_Solicitacao = nf.oid_Solicitacao " +
          " and his.oid_Historico=pr.oid_Historico" +
          " and pr.oid_conta=cc.oid_conta";

      if (String.valueOf (ed.getOID_Solicitacao ()) != null &&
          !String.valueOf (ed.getOID_Solicitacao ()).equals ("") &&
          !String.valueOf (ed.getOID_Solicitacao ()).equals ("null")) {
        sql += " AND pr.OID_Solicitacao = " + ed.getOID_Solicitacao ();
      }
      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      if (String.valueOf (ed.getCD_Conta ()) != null &&
          !String.valueOf (ed.getCD_Conta ()).equals ("") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("null") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("0")) {
        sql += " AND cc.cd_conta = '" + ed.getCD_Conta () + "'";
      }

      sql += " Order by pr.oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));
        edVolta.setOID_Solicitacao (res.getString ("oid_Solicitacao"));
        edVolta.setDM_Acao (res.getString ("dm_acao"));
        edVolta.setOID_Conta (res.getLong ("oid_conta"));

        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));

        edVolta.setDT_Lancamento (res.getString ("dt_lancamento"));
        dataFormatada.setDT_FormataData (edVolta.getDT_Lancamento ());
        edVolta.setDT_Lancamento (dataFormatada.getDT_FormataData ());

        edVolta.setCD_Conta (res.getString ("cd_conta"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));

        edVolta.setNR_Solicitacao (res.getLong ("nr_Solicitacao"));
        edVolta.setOID_Historico (res.getLong ("oid_Historico"));
        edVolta.setCD_Historico (res.getLong ("cd_historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));
        edVolta.setOID_Unidade_Contabil (res.getLong ("OID_Unidade_Contabil"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }
        edVolta.setVL_Lancamento (valor);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public Lancamento_ContabilED getByRecord_Acerto (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "select * " +
          "from movimentos_ordens_servicos, lancamentos_contabeis, contas, " +
          "Historicos, Unidades " +
          "where oid_movimento_ordem_servico=oid_acerto " +
          "and contas.oid_conta=lancamentos_contabeis.oid_conta " +
          "and Unidades.OID_Unidade=lancamentos_contabeis.OID_Unidade_Contabil " +
          "and Historicos.oid_Historico =  lancamentos_contabeis.oid_Historico ";

      if (String.valueOf (ed.getOID_Acerto ()) != null &&
          !String.valueOf (ed.getOID_Acerto ()).equals ("") &&
          !String.valueOf (ed.getOID_Acerto ()).equals ("null")) {
        sql += " AND OID_Acerto = " + ed.getOID_Acerto ();
      }
      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }

      sql += " Order by oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      double valor = 0;
      while (res.next ()) {

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));

        edVolta.setOID_Acerto (res.getString ("oid_Acerto"));

        edVolta.setDM_Acao (res.getString ("dm_acao"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));

        edVolta.setDT_Lancamento (res.getString ("dt_lancamento"));
        dataFormatada.setDT_FormataData (edVolta.getDT_Lancamento ());
        edVolta.setDT_Lancamento (dataFormatada.getDT_FormataData ());

        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setOID_Historico (res.getLong ("oid_Historico"));
        edVolta.setCD_Historico (res.getLong ("cd_historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));

        edVolta.setNM_Complementar (res.getString ("nm_complementar"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }

        edVolta.setVL_Lancamento (valor);
        edVolta.setOID_Unidade_Contabil (res.getLong ("OID_Unidade_Contabil"));
        edVolta.setCD_Unidade_Contabil (res.getString ("CD_Unidade"));
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public Lancamento_ContabilED getByRecord_Movimento_Conta_Corrente (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "select * " +
          "from movimentos_ordens_servicos, lancamentos_contabeis, contas, " +
          "Historicos, Unidades " +
          "where oid_movimento_ordem_servico=oid_Movimento_Conta_Corrente " +
          "and contas.oid_conta=lancamentos_contabeis.oid_conta " +
          "and Unidades.OID_Unidade=lancamentos_contabeis.OID_Unidade_Contabil " +
          "and Historicos.oid_Historico =  lancamentos_contabeis.oid_Historico ";

      if (String.valueOf (ed.getOID_Movimento_Conta_Corrente ()) != null &&
          !String.valueOf (ed.getOID_Movimento_Conta_Corrente ()).equals ("") &&
          !String.valueOf (ed.getOID_Movimento_Conta_Corrente ()).equals ("null")) {
        sql += " AND OID_Movimento_Conta_Corrente = " + ed.getOID_Movimento_Conta_Corrente ();
      }
      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }

      sql += " Order by oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      double valor = 0;
      while (res.next ()) {

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));

        edVolta.setOID_Movimento_Conta_Corrente (res.getLong ("oid_Movimento_Conta_Corrente"));

        edVolta.setDM_Acao (res.getString ("dm_acao"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));

        edVolta.setDT_Lancamento (res.getString ("dt_lancamento"));
        dataFormatada.setDT_FormataData (edVolta.getDT_Lancamento ());
        edVolta.setDT_Lancamento (dataFormatada.getDT_FormataData ());

        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setOID_Historico (res.getLong ("oid_Historico"));
        edVolta.setCD_Historico (res.getLong ("cd_historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));

        edVolta.setNM_Complementar (res.getString ("nm_complementar"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }

        edVolta.setVL_Lancamento (valor);
        edVolta.setOID_Unidade_Contabil (res.getLong ("OID_Unidade_Contabil"));
        edVolta.setCD_Unidade_Contabil (res.getString ("CD_Unidade"));
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public Lancamento_ContabilED getByRecord_Ajuste (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "select * " +
          "from lancamentos_contabeis, contas, Historicos " +
          "where contas.oid_conta=lancamentos_contabeis.oid_conta " +
          "and Historicos.oid_Historico =  lancamentos_contabeis.oid_Historico ";

      if (String.valueOf (ed.getOID_Acerto ()) != null &&
          !String.valueOf (ed.getOID_Acerto ()).equals ("") &&
          !String.valueOf (ed.getOID_Acerto ()).equals ("null")) {
        sql += " AND OID_Acerto = " + ed.getOID_Acerto ();
      }
      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }

      if (String.valueOf (ed.getOID_Movimento_Servico ()) != null &&
          !String.valueOf (ed.getOID_Movimento_Servico ()).equals ("") &&
          !String.valueOf (ed.getOID_Movimento_Servico ()).equals ("null")) {
        sql += " AND OID_movimento = " + ed.getOID_Movimento_Servico ();
      }

      sql += " Order by oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      while (res.next ()) {

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));

        edVolta.setOID_Acerto (res.getString ("oid_Acerto"));

        edVolta.setDM_Acao (res.getString ("dm_acao"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));

        edVolta.setNM_Conta (res.getString ("nm_conta"));

        edVolta.setDT_Lancamento (res.getString ("dt_lancamento"));
        dataFormatada.setDT_FormataData (edVolta.getDT_Lancamento ());
        edVolta.setDT_Lancamento (dataFormatada.getDT_FormataData ());

        edVolta.setOID_Historico (res.getLong ("oid_Historico"));
        edVolta.setCD_Historico (res.getLong ("cd_historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));

        edVolta.setNM_Complementar (res.getString ("nm_complementar"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }

        edVolta.setVL_Lancamento (valor);
        edVolta.setOID_Unidade_Contabil (res.getLong ("OID_Unidade_Contabil"));
        sql = "Select CD_Unidade from Unidades where OID_Unidade=" + res.getLong ("OID_Unidade_Contabil");
        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);

        while (resLocal.next ()) {
          edVolta.setCD_Unidade_Contabil (resLocal.getString ("CD_Unidade"));
        }
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public Lancamento_ContabilED getByRecord_Ordem_Frete (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "select * " +
          "from ordens_fretes, lancamentos_contabeis, contas, Historicos " +
          "where ordens_fretes.oid_ordem_frete=lancamentos_contabeis.oid_ordem_frete " +
          "and contas.oid_conta=lancamentos_contabeis.oid_conta " +
          "and Historicos.oid_Historico =  lancamentos_contabeis.oid_Historico ";

      if (String.valueOf (ed.getOID_Ordem_Frete ()) != null &&
          !String.valueOf (ed.getOID_Ordem_Frete ()).equals ("") &&
          !String.valueOf (ed.getOID_Ordem_Frete ()).equals ("null")) {
        sql += " AND ordens_fretes.OID_Ordem_frete = '" + ed.getOID_Ordem_Frete () + "'";
      }
      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }

      sql += " Order by oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      while (res.next ()) {

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));

        edVolta.setOID_Ordem_Frete (res.getString ("oid_Ordem_Frete"));

        edVolta.setDM_Acao (res.getString ("dm_acao"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));

        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setOID_Historico (res.getLong ("oid_Historico"));
        edVolta.setCD_Historico (res.getLong ("cd_historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));

        edVolta.setNM_Complementar (res.getString ("nm_complementar"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }

        edVolta.setVL_Lancamento (valor);
        edVolta.setOID_Unidade_Contabil (res.getLong ("OID_Unidade_Contabil"));
        sql = "Select CD_Unidade from Unidades where OID_Unidade=" + res.getLong ("OID_Unidade_Contabil");
        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);

        while (resLocal.next ()) {
          edVolta.setCD_Unidade_Contabil (resLocal.getString ("CD_Unidade"));
        }
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public Lancamento_ContabilED getByRecord_Compromisso (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    double valor = 0;
    Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, pr.OID_Unidade_Contabil, nf.nr_Compromisso, pr.dm_acao, nf.vl_Compromisso, pr.oid_Historico, his.nm_historico, his.cd_historico, pr.oid_Compromisso, pr.oid_conta, cc.nm_conta, cc.cd_conta, pr.vl_lancamento, pr.nm_complementar " +
          "From  lancamentos_contabeis pr, Compromissos nf, contas cc, Historicos his " +
          "Where pr.oid_Compromisso = nf.oid_Compromisso " +
          " and his.oid_Historico=pr.oid_Historico" +
          " and pr.oid_conta=cc.oid_conta";

      if (String.valueOf (ed.getOID_Compromisso ()) != null &&
          !String.valueOf (ed.getOID_Compromisso ()).equals ("") &&
          !String.valueOf (ed.getOID_Compromisso ()).equals ("null") &&
          !String.valueOf (ed.getOID_Compromisso ()).equals ("0")) {
        sql += " AND pr.OID_Compromisso = " + ed.getOID_Compromisso ();
      }
      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      if (String.valueOf (ed.getCD_Conta ()) != null &&
          !String.valueOf (ed.getCD_Conta ()).equals ("") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("null") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("0")) {
        sql += " AND cc.cd_conta = '" + ed.getCD_Conta () + "'";
      }

      sql += " Order by pr.oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));
        edVolta.setOID_Compromisso (res.getLong ("oid_Compromisso"));
        edVolta.setDM_Acao (res.getString ("dm_acao"));
        edVolta.setOID_Conta (res.getLong ("oid_conta"));

        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));

        edVolta.setNR_Compromisso (res.getString ("nr_Compromisso"));
        edVolta.setOID_Historico (res.getLong ("oid_Historico"));
        edVolta.setCD_Historico (res.getLong ("cd_historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));
        edVolta.setOID_Unidade_Contabil (res.getLong ("OID_Unidade_Contabil"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }
        edVolta.setVL_Lancamento (valor);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void inclui_Pagamento (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = null;
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      ResultSet rs = executasql.executarConsulta ("select max(oid_lancamento) as result from lancamentos_contabeis ");

      //pega proximo valor da chave
      while (rs.next ()) {
        valOid = rs.getLong ("result");
        ed.setOID_Lancamento (++valOid);
      }

      // sql = " insert into lancamentos_contabeis (OID_lancamento, OID_Compromisso, dm_acao, vl_lancamento, oid_conta, dt_stamp, dt_lancamento, usuario_stamp, dm_stamp,oid_Historico, nm_complementar,oid_unidade_contabil, oid_nota_fiscal) values ";
      // sql += "(" + ed.getOID_Lancamento() + ","+ed.getOID_Compromisso()+",'"+ed.getDM_Acao()+"',"+ed.getVL_Lancamento()+","+ed.getOID_Conta()+",'"+ed.getDT_Stamp()+"','"+ed.getDT_Lancamento()+"','"+ed.getUsuario_Stamp()+"','"+ed.getDM_Stamp()+"',"+ed.getCD_Historico()+",'"+ed.getNM_Complementar()+"',"+ed.getOID_Unidade_Contabil()+",' ')";

      sql = " insert into lancamentos_contabeis (OID_lancamento, OID_Compromisso, OID_Lote_Pagamento,  OID_Lote_Posto, dm_acao, vl_lancamento, oid_conta, dt_stamp, dt_lancamento, usuario_stamp, dm_stamp,oid_Historico, nm_complementar,oid_unidade_contabil, oid_nota_fiscal) values ";
      sql += "(" + ed.getOID_Lancamento () + "," + ed.getOID_Compromisso () + "," + ed.getOID_Lote_Pagamento () + "," + ed.getOID_Lote_Posto () + ",'" + ed.getDM_Acao () + "'," + ed.getVL_Lancamento () + "," + ed.getOID_Conta () + ",'" + ed.getDT_Stamp () + "','" + ed.getDT_Lancamento () + "','" + ed.getUsuario_Stamp () + "','" + ed.getDM_Stamp () + "'," + ed.getCD_Historico () + ",'" + ed.getNM_Complementar () + "'," + ed.getOID_Unidade_Contabil () + ",' ')";

      int i = executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      exc.printStackTrace ();
      excecoes.setMetodo ("inclui lancamentos");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList GeraEDI_Acertos (long oid_unidade , String dt_inicio , String dt_fim) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();
    Lancamento_Centro_CustoED centro = new Lancamento_Centro_CustoED ();
    ResultSet res = null;
    ResultSet rs = null;
    ResultSet rz = null;
    ResultSet rx = null;
    ResultSet rf = null;
    ResultSet cc = null;
    String acumulado = "";
    String centros = "";
    String Historico = "";
    String Valor = "";
    String acento = "";
    String nr_acerto = "";
    int j = 0 , i = 0 , conta = 0;

    try {
      //Primeiro buscamos todas as contas que ACUMULAM valores
      sql = "SELECT * ";
      sql += "FROM acertos, ordens_servicos ";
      sql += "WHERE acertos.dt_emissao between '" + dt_inicio + "' and '" + dt_fim + "' ";
      sql += "and acertos.oid_ordem_servico = ordens_servicos.oid_ordem_servico ";
      sql += "and ordens_servicos.oid_unidade = " + oid_unidade;
      sql += " order by dt_emissao ";
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {

        //Selecionamos as informa�s requisitadas pelo RM
        sql = "select * " +
            "from lancamentos_contabeis " +
            "where oid_ordem_frete in (select oid_ordem_frete from ordens_fretes where oid_acerto=" + res.getString ("oid_acerto") + ") " +
            "or oid_movimento = " + res.getString ("oid_acerto") + " " +
            "or oid_acerto in (select oid_movimento_ordem_servico " +
            "from movimentos_ordens_servicos, acertos " +
            "where movimentos_ordens_servicos.oid_ordem_servico=acertos.oid_ordem_servico " +
            "and oid_acerto=" + res.getString ("oid_acerto") + ")";

        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          Tipo_EventoED ed = new Tipo_EventoED ();
          sql = "Select targget_rce(nm_historico) as nm_historico, cd_historico from Historicos where oid_Historico = " + rs.getString ("oid_Historico");
          rf = this.executasql.executarConsulta (sql);
          while (rf.next ()) {
            Historico = rf.getString ("cd_historico");
            if ( (Historico == null)) {
              Historico = "";
            }
            else {
              Historico = rf.getString ("cd_historico");
            }

            ed.setCd_Historico (Historico);
          }

          Historico += "NR: " + res.getString ("nr_acerto");
          nr_acerto = res.getString ("nr_acerto");

          //if(rs.getLong("oid_movimento")>0){
          sql = "Select targget_rce(pessoas.nm_razao_social)as nm_razao_social from pessoas ";
          sql += "where pessoas.oid_pessoa=" + res.getString ("oid_motorista");

          rz = this.executasql.executarConsulta (sql);

          while (rz.next ()) {
            Historico += " MOT: " + rz.getString ("nm_razao_social");
          }
          //}

          if (rs.getLong ("oid_movimento") > 0) {
            sql = "Select cd_centro_custo as nr_frota from acertos ac, centros_custos cc ";
            sql += "where ac.oid_frota=cc.oid_centro_custo and ac.oid_acerto=" + res.getString ("oid_acerto");

          }
          else if (rs.getLong ("oid_acerto") > 0) {
            sql = "Select vei.nr_frota From movimentos_ordens_servicos mv, pessoas pp, ordens_servicos os, veiculos vei ";
            sql += "Where mv.oid_ordem_servico = os.oid_ordem_servico ";
            sql += "and mv.oid_movimento_ordem_servico=" + rs.getString ("oid_acerto") + " ";
            sql += "and os.oid_veiculo = vei.oid_veiculo ";

          }
          else if (rs.getString ("oid_ordem_frete") != null && rs.getString ("oid_ordem_frete").length () > 0) {
            sql = "Select nr_frota from ordens_fretes fre,  veiculos vv where oid_ordem_frete='" + rs.getString ("oid_ordem_frete") + "' ";
            sql += "and fre.oid_veiculo=vv.oid_veiculo ";

          }

          rx = this.executasql.executarConsulta (sql);

//            while(rx.next()){
//              Historico +=" - "+rx.getString("nr_frota");
//            }


          Historico += " " + rs.getString ("nm_complementar");

          ed.setNm_Historico (Historico);

          if (rs.getString ("vl_lancamento") == null) {
            ed.setVl_Lancamento (0);
          }
          else {
            double auxiliar = Double.parseDouble (rs.getString ("vl_lancamento"));
            ed.setVl_Lancamento (auxiliar);
          }

          acumulado = ed.getNm_Historico () + acumulado;
          if (conta != 0) {
            ed.setNm_Historico (acumulado);
          }
          else {
            ed.setNm_Historico (ed.getNm_Historico ());
          }

          rf = null;
          sql = "Select cd_conta from contas where oid_conta = " + rs.getLong ("oid_conta");
          rf = this.executasql.executarConsulta (sql);

          while (rf.next ()) {
            if (Valor.equals ("")) {
              Valor = rf.getString ("cd_conta");
            }
            else {
              Valor += "," + rf.getString ("cd_conta");
            }

            if (rs.getString ("dm_acao").equals ("D")) {
              ed.setCd_Conta_Debito (rf.getString ("cd_conta"));
              ed.setCd_Conta_Credito ("    ");
              acento = "";

            }

            if (rs.getString ("dm_acao").equals ("C")) {
              ed.setCd_Conta_Debito ("    ");
              ed.setCd_Conta_Credito (rf.getString ("cd_conta"));
              acento = "-";
            }
          }

          /*Verifica os centros de custos de cada lancamento*/
//          sql ="select cc.cd_centro_custo, cc.cd_centro_analitico,lcc.vl_lancamento, lc.oid_conta, lc.oid_nota_fiscal ";
//          sql +="from lancamentos_contabeis lc, lancamentos_centros_custos lcc, centros_custos cc ";
//          sql +="where lc.oid_lancamento = lcc.oid_lancamento ";
//          sql +="and cc.oid_centro_custo = lcc.oid_centro_custo ";
//          sql +="and lc.oid_lancamento="+rs.getLong("oid_lancamento");
//          cc = this.executasql.executarConsulta(sql);
          centros = "";
//          String espacos = "                    ";
//          int yy = 0;
//          while(cc.next()){
//            int xx = String.valueOf(espacos).length() - String.valueOf(cc.getString("cd_centro_analitico")).length();
//            yy = 0;
//            if(acento.equals("-")){
//              yy = (String.valueOf(espacos).length()-2-1) - String.valueOf(cc.getString("vl_lancamento")).length();
//            }else yy = (String.valueOf(espacos).length()-2) - String.valueOf(cc.getString("vl_lancamento")).length();
//            centros += cc.getString("cd_centro_analitico") + espacos.substring(0,xx) + espacos.substring(0,yy) + acento + cc.getString("vl_lancamento");
//          }
          ed.setNm_Complemento_Historico (centros);
          ed.setOID_Unidade (oid_unidade);
          ed.setDm_Totalizado ("N");

          rf = null;
          sql = "Select cd_unidade_contabil from unidades where oid_unidade = " + oid_unidade;
          rf = this.executasql.executarConsulta (sql);

          while (rf.next ()) {
            ed.setCD_Unidade_Contabil (rf.getString ("cd_unidade_contabil"));
          }

          ed.setDt_Lancamento (res.getString ("dt_emissao"));
          if ( (nr_acerto == null) || (nr_acerto.equals (""))) {
            ed.setNr_Documento (0);
          }
          else {
            ed.setNr_Documento (Long.parseLong (nr_acerto));
          }
          ed.setNm_Tipo_Evento (rs.getString ("dm_acao"));
          int aux = rs.getInt ("oid_Historico");
          ed.setOid_Historico (new Integer (aux));
          //conta++;
          //}
          list.add (ed);
        }
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }
    alteraExportadoAcerto (oid_unidade , dt_inicio , dt_fim);
    return list;
  }

  public void alteraExportadoAcerto (long oid_unidade , String dt_inicio , String dt_fim) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {

      sql = "update acertos set dm_finalizado='E' ";
      sql += "where dt_emissao between '" + dt_inicio + "' and '" + dt_fim + "' ";
      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar status para exportado");
      excecoes.setMetodo ("alteraExportado");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista_Contabil_Caixa (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, pr.OID_Unidade_Contabil, " +
          " Com.OID_caixa, pr.dm_acao, pr.oid_Historico, " +
          " his.nm_historico, pr.oid_Caixa, pr.oid_conta, cc.nm_conta, " +
          " cc.cd_conta, pr.vl_lancamento, pr.nm_complementar " +
          "From  lancamentos_contabeis pr, Caixas Com, contas cc, " +
          "Historicos his " +
          "Where pr.oid_Caixa = Com.oid_Caixa and pr.oid_conta=cc.oid_conta" +
          " and his.oid_Historico=pr.oid_Historico";

      if (String.valueOf (ed.getOID_Caixa ()) != null &&
          !String.valueOf (ed.getOID_Caixa ()).equals ("") &&
          !String.valueOf (ed.getOID_Caixa ()).equals ("null")) {
        sql += " AND pr.OID_Caixa = " + ed.getOID_Caixa ();
      }

      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      sql += " Order by pr.oid_Lancamento ";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      while (res.next ()) {
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));

        edVolta.setOID_Caixa (res.getString ("oid_Caixa"));

        edVolta.setDM_Acao (res.getString ("dm_acao"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));

        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setCD_Historico (res.getLong ("oid_Historico"));

        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }

        edVolta.setVL_Lancamento (valor);

        sql = "Select CD_Unidade from Unidades where OID_Unidade=" + res.getLong ("OID_Unidade_Contabil");
        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);

        while (resLocal.next ()) {
          edVolta.setCD_Unidade_Contabil (resLocal.getString ("CD_Unidade"));
        }
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Lancamento_ContabilED getByRecord_Caixa (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    double valor = 0;
    Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, pr.OID_Unidade_Contabil, " +
          " pr.dm_acao, pr.oid_Historico, " +
          " his.nm_historico, his.cd_historico, pr.oid_Caixa, pr.oid_conta, cc.nm_conta, " +
          " cc.cd_conta, pr.vl_lancamento, pr.nm_complementar " +
          "From  lancamentos_contabeis pr, Caixas nf, contas cc, " +
          " Historicos his " +
          "Where pr.oid_Caixa = nf.oid_Caixa " +
          " and his.oid_Historico=pr.oid_Historico" +
          " and pr.oid_conta=cc.oid_conta";

      if (String.valueOf (ed.getOID_Caixa ()) != null &&
          !String.valueOf (ed.getOID_Caixa ()).equals ("") &&
          !String.valueOf (ed.getOID_Caixa ()).equals ("null")) {
        sql += " AND pr.OID_Caixa = " + ed.getOID_Caixa ();
      }
      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      if (String.valueOf (ed.getCD_Conta ()) != null &&
          !String.valueOf (ed.getCD_Conta ()).equals ("") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("null") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("0")) {
        sql += " AND cc.cd_conta = '" + ed.getCD_Conta () + "'";
      }

      sql += " Order by pr.oid_Lancamento ";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));
        edVolta.setOID_Caixa (res.getString ("oid_Caixa"));
        edVolta.setDM_Acao (res.getString ("dm_acao"));
        edVolta.setOID_Conta (res.getLong ("oid_conta"));

        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));

        edVolta.setOID_Historico (res.getLong ("oid_Historico"));
        edVolta.setCD_Historico (res.getLong ("cd_historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));
        edVolta.setOID_Unidade_Contabil (res.getLong ("OID_Unidade_Contabil"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }
        edVolta.setVL_Lancamento (valor);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public ArrayList GeraEDI_Caixinha_old (long oid_unidade , String dt_inicio , String dt_fim) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();
    Lancamento_Centro_CustoED centro = new Lancamento_Centro_CustoED ();
    ResultSet res = null;
    ResultSet rs = null;
    ResultSet rz = null;
    ResultSet cc = null;
    String acumulado = "";
    String centros = "";
    String Historico = "";
    String Valor = "";
    String acento = "";
    int j = 0 , i = 0 , conta = 0;

    try {
      //Primeiro buscamos todas as contas que ACUMULAM valores
      sql = "SELECT sum(lc.vl_lancamento) as valor, lc.oid_conta ";
      sql += "FROM lancamentos_contabeis lc, eventos_contabeis ev ";
      sql += "WHERE lc.oid_unidade_contabil= " + oid_unidade + " ";
      sql += "and lc.dt_lancamento between '" + dt_inicio + "' and '" + dt_fim + "' ";
      sql += "and lc.oid_conta=ev.oid_conta and lc.oid_caixa is not null ";
      sql += "group by lc.oid_conta, ev.dm_acumula ";
      sql += "having ev.dm_acumula='S' ";

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        Tipo_EventoED ed = new Tipo_EventoED ();
        //Selecionamos as informa�s requisitadas pelo RM
        sql = "SELECT cx.cd_historico, lc.dt_lancamento, targget_rce(lc.nm_complementar) as nm_complementar, lc.oid_conta, ct.cd_conta, lc.oid_nota_fiscal, cxn.oid_caixa, lc.oid_lancamento, lc.dm_acao, lc.oid_Historico, un.cd_unidade_contabil , lc.dt_lancamento ";
        sql += "FROM Historicos cx, lancamentos_contabeis lc, contas ct, caixas cxn, unidades un ";
        sql += "WHERE lc.oid_conta in(" + res.getString ("oid_conta") + ") ";
        sql += "and lc.oid_conta=ct.oid_conta ";
        sql += "and un.oid_unidade=lc.oid_unidade_contabil ";
        sql += "and lc.oid_caixa=cxn.oid_caixa ";
        sql += "and lc.dt_lancamento between '" + dt_inicio + "' and '" + dt_fim + "' ";
        sql += "and lc.oid_unidade_contabil= " + oid_unidade + " ";
        sql += "and lc.oid_Historico=cx.oid_Historico ";
        sql += "order by lc.oid_caixa";

        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          //busca os valores para o historico para aquela linha em questao
          Historico = rs.getString ("cd_historico");
          if ( (Historico == null)) {
            Historico = "";
          }
          else {
            Historico = rs.getString ("cd_historico");
          }

          ed.setCd_Historico (Historico);

          Historico = Historico;
          Historico += "NR: " + rs.getString ("oid_caixa");

          Historico += " " + rs.getString ("nm_complementar");
          ed.setNm_Historico (Historico);
          ed.setVl_Lancamento (res.getDouble ("valor"));
          acumulado = ed.getNm_Historico () + acumulado;
          if (conta != 0) {
            ed.setNm_Historico (acumulado);
          }
          else {
            ed.setNm_Historico (ed.getNm_Historico ());
          }

          if (Valor.equals ("")) {
            Valor = rs.getString ("oid_conta");
          }
          else {
            Valor += "," + rs.getString ("oid_conta");
          }
          if (rs.getString ("dm_acao").equals ("D")) {
            ed.setCd_Conta_Debito (rs.getString ("cd_conta"));
            ed.setCd_Conta_Credito ("    ");
            acento = "";
          }
          if (rs.getString ("dm_acao").equals ("C")) {
            ed.setCd_Conta_Debito ("    ");
            ed.setCd_Conta_Credito (rs.getString ("cd_conta"));
            acento = "-";
          }

          /*Verifica os centros de custos de cada lancamento*/
          sql = "select cc.cd_centro_custo, cc.cd_centro_analitico,lcc.vl_lancamento, lc.oid_conta, lc.oid_caixa ";
          sql += "from lancamentos_contabeis lc, lancamentos_centros_custos lcc, centros_custos cc ";
          sql += "where lc.oid_lancamento = lcc.oid_lancamento ";
          sql += "and cc.oid_centro_custo = lcc.oid_centro_custo ";
          sql += "and lc.oid_lancamento=" + rs.getLong ("oid_lancamento");
          cc = this.executasql.executarConsulta (sql);
          centros = "";
          String espacos = "                    ";
          int yy = 0;
          while (cc.next ()) {
            int xx = String.valueOf (espacos).length () - String.valueOf (cc.getString ("cd_centro_custo")).length ();
            yy = 0;
            if (acento.equals ("-")) {
              yy = (String.valueOf (espacos).length () - 2 - 1) - String.valueOf (cc.getString ("vl_lancamento")).length ();
            }
            else {
              yy = (String.valueOf (espacos).length () - 2) - String.valueOf (cc.getString ("vl_lancamento")).length ();
            }
            centros += cc.getString ("cd_centro_analitico") + espacos.substring (0 , xx) + espacos.substring (0 , yy) + acento + cc.getString ("vl_lancamento");
          }
          ed.setNm_Complemento_Historico (centros);
          ed.setOID_Unidade (oid_unidade);
          ed.setCD_Unidade_Contabil (rs.getString ("cd_unidade_contabil"));
          ed.setDm_Totalizado ("N");
          ed.setDt_Lancamento (rs.getString ("dt_lancamento"));
          ed.setNr_Documento (rs.getLong ("oid_caixa"));
          ed.setNm_Tipo_Evento (rs.getString ("dm_acao"));
          int aux = rs.getInt ("oid_Historico");
          ed.setOid_Historico (new Integer (aux));
          conta++;
        }
        list.add (ed);

      }
      /*******Agora todos os outros casos****/

      sql = "SELECT cx.cd_historico, lc.dt_lancamento, targget_rce(lc.nm_complementar) as nm_complementar, lc.oid_conta, ct.cd_conta, lc.oid_nota_fiscal, cxn.oid_caixa, lc.oid_lancamento, lc.dm_acao, lc.oid_Historico, un.cd_unidade_contabil , lc.dt_lancamento, lc.vl_lancamento as vl_lancamento ";
      sql += "FROM Historicos cx, lancamentos_contabeis lc, contas ct, caixas cxn, unidades un ";
      if (!Valor.equals ("")) {
        sql += "where lc.oid_conta NOT IN(" + Valor + ") and ";
      }
      else {
        sql += "Where ";
      }
      sql += "lc.oid_conta=ct.oid_conta ";
      sql += "and lc.oid_conta=ct.oid_conta ";
      sql += "and un.oid_unidade=lc.oid_unidade_contabil ";
      sql += "and lc.oid_caixa=cxn.oid_caixa ";
      sql += "and lc.dt_lancamento between '" + dt_inicio + "' and '" + dt_fim + "' ";
      sql += "and lc.oid_unidade_contabil= " + oid_unidade + " ";
      sql += "and lc.oid_Historico=cx.oid_Historico ";
      sql += "order by lc.oid_caixa";

      rs = null;
      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        Tipo_EventoED ed = new Tipo_EventoED ();
        //busca os valores para o historico para aquela linha em questao
        Historico = rs.getString ("cd_historico");
        if ( (Historico == null)) {
          Historico = "";
        }
        else {
          Historico = rs.getString ("cd_historico");
        }

        ed.setCd_Historico (Historico);

        Historico += "NR: " + rs.getString ("oid_caixa");

        Historico += " " + rs.getString ("nm_complementar");
        ed.setNm_Historico (Historico);
        ed.setVl_Lancamento (rs.getDouble ("vl_lancamento"));

        if (Valor.equals ("")) {
          Valor = rs.getString ("cd_conta");
        }
        else {
          Valor += "," + rs.getDouble ("cd_conta");
        }

        if (rs.getString ("dm_acao").equals ("D")) {
          ed.setCd_Conta_Debito (rs.getString ("cd_conta"));
          ed.setCd_Conta_Credito ("    ");
          acento = "";
        }
        if (rs.getString ("dm_acao").equals ("C")) {
          ed.setCd_Conta_Debito ("    ");
          ed.setCd_Conta_Credito (rs.getString ("cd_conta"));
          acento = "-";
        }

        /*Verifica os centros de custos de cada lancamento*/
        sql = "select cc.cd_centro_custo, lcc.vl_lancamento, cc.cd_centro_analitico, lc.oid_conta, lc.oid_caixa ";
        sql += "from lancamentos_contabeis lc, lancamentos_centros_custos lcc, centros_custos cc ";
        sql += "where lc.oid_lancamento = lcc.oid_lancamento ";
        sql += "and cc.oid_centro_custo = lcc.oid_centro_custo ";
        sql += "and lc.oid_lancamento=" + rs.getLong ("oid_lancamento");
        cc = this.executasql.executarConsulta (sql);
        centros = "";
        String espacos = "                    ";
        int yy = 0;
        while (cc.next ()) {
          int xx = String.valueOf (espacos).length () - String.valueOf (cc.getString ("cd_centro_custo")).length ();
          yy = 0;
          if (acento.equals ("-")) {
            yy = (String.valueOf (espacos).length () - 2 - 1) - String.valueOf (cc.getString ("vl_lancamento")).length ();
          }
          else {
            yy = (String.valueOf (espacos).length () - 2) - String.valueOf (cc.getString ("vl_lancamento")).length ();
          }
          centros += cc.getString ("cd_centro_analitico") + espacos.substring (0 , xx) + espacos.substring (0 , yy) + acento + cc.getString ("vl_lancamento");
        }
        ed.setNm_Complemento_Historico (centros);
        ed.setOID_Unidade (oid_unidade);
        ed.setCD_Unidade_Contabil (rs.getString ("cd_unidade_contabil"));
        ed.setDm_Totalizado ("N");
        ed.setDt_Lancamento (rs.getString ("dt_lancamento"));
        ed.setNr_Documento (rs.getLong ("oid_caixa"));
        ed.setNm_Tipo_Evento (rs.getString ("dm_acao"));
        int aux = rs.getInt ("oid_Historico");
        ed.setOid_Historico (new Integer (aux));

        list.add (ed);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao gerar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }
    // alteraExportado(oid_unidade, dt_inicio, dt_fim);
    return list;
  }

  public ArrayList GeraEDI_Solicitacao (long oid_unidade , String dt_inicio , String dt_fim) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();
    Lancamento_Centro_CustoED centro = new Lancamento_Centro_CustoED ();
    ResultSet res = null;
    ResultSet rs = null;
    ResultSet rz = null;
    ResultSet cc = null;
    String acumulado = "";
    String centros = "";
    String Historico = "";
    String Valor = "";
    String acento = "";
    int j = 0 , i = 0 , conta = 0;

    try {
      //Primeiro buscamos todas as contas que ACUMULAM valores
      sql = "SELECT sum(lc.vl_lancamento) as valor, lc.oid_conta ";
      sql += "FROM lancamentos_contabeis lc, eventos_contabeis ev ";
      sql += "WHERE lc.oid_unidade_contabil= " + oid_unidade + " ";
      sql += "and lc.dt_lancamento between '" + dt_inicio + "' and '" + dt_fim + "' ";
      sql += "and lc.oid_conta=ev.oid_conta and lc.oid_solicitacao is not null ";
      sql += "group by lc.oid_conta, ev.dm_acumula ";
      sql += "having ev.dm_acumula='S' ";
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        Tipo_EventoED ed = new Tipo_EventoED ();
        //Selecionamos as informa�s requisitadas pelo RM
        sql = "SELECT cx.cd_historico, lc.dt_lancamento, targget_rce(lc.nm_complementar) as nm_complementar, lc.oid_conta, ct.cd_conta, lc.oid_nota_fiscal, sc.oid_solicitacao, lc.oid_lancamento, lc.dm_acao, lc.oid_Historico, un.cd_unidade_contabil , lc.dt_lancamento, lc.vl_lancamento ";
        sql += "FROM Historicos cx, lancamentos_contabeis lc, contas ct, solicitacoes_transacoes sc, unidades un ";
        sql += "WHERE lc.oid_conta in(" + res.getString ("oid_conta") + ") ";
        sql += "and lc.oid_conta=ct.oid_conta ";
        sql += "and un.oid_unidade=lc.oid_unidade_contabil ";
        sql += "and lc.oid_solicitacao=sc.oid_solicitacao ";
        sql += "and lc.dt_lancamento between '" + dt_inicio + "' and '" + dt_fim + "' ";
        sql += "and lc.oid_unidade_contabil= " + oid_unidade + " ";
        sql += "and lc.oid_Historico=cx.oid_Historico ";
        sql += "order by lc.oid_solicitacao";
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          //busca os valores para o historico para aquela linha em questao
          Historico = rs.getString ("cd_historico");
          if ( (Historico == null)) {
            Historico = "";
          }
          else {
            Historico = rs.getString ("cd_historico");
          }

          ed.setCd_Historico (Historico);

          Historico = Historico;
          Historico += "NR: " + rs.getString ("oid_solicitacao");

          sql = "select sc.oid_pessoa_destinatario, targget_rce(p.nm_razao_social) as nm_razao_social ";
          sql += "from pessoas p, solicitacoes_transacoes sc ";
          sql += "where sc.oid_solicitacao = '" + rs.getString ("oid_solicitacao") + "' ";
          sql += "and sc.oid_pessoa_destinatario = p.oid_pessoa ";
          rz = this.executasql.executarConsulta (sql);

          while (rz.next ()) {
            Historico += " " + rz.getString ("nm_razao_social");
          }

          Historico += " " + rs.getString ("nm_complementar");
          ed.setNm_Historico (Historico);
          ed.setVl_Lancamento (res.getDouble ("valor"));
          acumulado = ed.getNm_Historico () + acumulado;
          if (conta != 0) {
            ed.setNm_Historico (acumulado);
          }
          else {
            ed.setNm_Historico (ed.getNm_Historico ());
          }

          if (Valor.equals ("")) {
            Valor = rs.getString ("oid_conta");
          }
          else {
            Valor += "," + rs.getString ("oid_conta");
          }
          if (rs.getString ("dm_acao").equals ("D")) {
            ed.setCd_Conta_Debito (rs.getString ("cd_conta"));
            ed.setCd_Conta_Credito ("    ");
            acento = "";
          }
          if (rs.getString ("dm_acao").equals ("C")) {
            ed.setCd_Conta_Debito ("    ");
            ed.setCd_Conta_Credito (rs.getString ("cd_conta"));
            acento = "-";
          }

          /*Verifica os centros de custos de cada lancamento*/
          sql = "select cc.cd_centro_custo, cc.cd_centro_analitico,lcc.vl_lancamento, lc.oid_conta, lc.oid_solicitacao ";
          sql += "from lancamentos_contabeis lc, lancamentos_centros_custos lcc, centros_custos cc ";
          sql += "where lc.oid_lancamento = lcc.oid_lancamento ";
          sql += "and cc.oid_centro_custo = lcc.oid_centro_custo ";
          sql += "and lc.oid_lancamento=" + rs.getLong ("oid_lancamento");
          cc = this.executasql.executarConsulta (sql);
          centros = "";
          String espacos = "                    ";
          int yy = 0;
          while (cc.next ()) {
            int xx = String.valueOf (espacos).length () - String.valueOf (cc.getString ("cd_centro_custo")).length ();
            yy = 0;
            if (acento.equals ("-")) {
              yy = (String.valueOf (espacos).length () - 2 - 1) - String.valueOf (cc.getString ("vl_lancamento")).length ();
            }
            else {
              yy = (String.valueOf (espacos).length () - 2) - String.valueOf (cc.getString ("vl_lancamento")).length ();
            }
            centros += cc.getString ("cd_centro_analitico") + espacos.substring (0 , xx) + espacos.substring (0 , yy) + acento + cc.getString ("vl_lancamento");
          }
          ed.setNm_Complemento_Historico (centros);
          ed.setOID_Unidade (oid_unidade);
          ed.setCD_Unidade_Contabil (rs.getString ("cd_unidade_contabil"));
          ed.setDm_Totalizado ("N");
          ed.setDt_Lancamento (rs.getString ("dt_lancamento"));
          ed.setNr_Documento (rs.getLong ("oid_solicitacao"));
          ed.setNm_Tipo_Evento (rs.getString ("dm_acao"));
          int aux = rs.getInt ("oid_Historico");
          ed.setOid_Historico (new Integer (aux));
          conta++;
        }
        list.add (ed);

      }
      /*******Agora todos os outros casos****/

      sql = "SELECT cx.cd_historico, lc.dt_lancamento, targget_rce(lc.nm_complementar) as nm_complementar, lc.oid_conta, ct.cd_conta, lc.oid_nota_fiscal, sc.oid_solicitacao, lc.oid_lancamento, lc.dm_acao, lc.oid_Historico, un.cd_unidade_contabil , lc.dt_lancamento, lc.vl_lancamento ";
      sql += "FROM Historicos cx, lancamentos_contabeis lc, contas ct, solicitacoes_transacoes sc, unidades un ";
      if (!Valor.equals ("")) {
        sql += "where lc.oid_conta NOT IN(" + Valor + ") and ";
      }
      else {
        sql += "Where ";
      }
      sql += "lc.oid_conta=ct.oid_conta ";
      sql += "and lc.oid_conta=ct.oid_conta ";
      sql += "and un.oid_unidade=lc.oid_unidade_contabil ";
      sql += "and lc.oid_solicitacao=sc.oid_solicitacao ";
      sql += "and lc.dt_lancamento between '" + dt_inicio + "' and '" + dt_fim + "' ";
      sql += "and lc.oid_unidade_contabil= " + oid_unidade + " ";
      sql += "and lc.oid_Historico=cx.oid_Historico ";
      sql += "order by lc.oid_solicitacao";

      rs = null;
      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        Tipo_EventoED ed = new Tipo_EventoED ();
        //busca os valores para o historico para aquela linha em questao
        Historico = rs.getString ("cd_historico");
        if ( (Historico == null)) {
          Historico = "";
        }
        else {
          Historico = rs.getString ("cd_historico");
        }

        ed.setCd_Historico (Historico);

        Historico += "NR: " + rs.getString ("oid_solicitacao");

        sql = "select sc.oid_pessoa_destinatario, targget_rce(p.nm_razao_social) as nm_razao_social ";
        sql += "from pessoas p, solicitacoes_transacoes sc ";
        sql += "where sc.oid_solicitacao = '" + rs.getString ("oid_solicitacao") + "' ";
        sql += "and sc.oid_pessoa_destinatario = p.oid_pessoa ";
        rz = this.executasql.executarConsulta (sql);

        while (rz.next ()) {
          Historico += " " + rz.getString ("nm_razao_social");
        }
        Historico += " " + rs.getString ("nm_complementar");
        ed.setNm_Historico (Historico);
        ed.setVl_Lancamento (rs.getDouble ("vl_lancamento"));

        if (Valor.equals ("")) {
          Valor = rs.getString ("cd_conta");
        }
        else {
          Valor += "," + rs.getDouble ("cd_conta");
        }

        if (rs.getString ("dm_acao").equals ("D")) {
          ed.setCd_Conta_Debito (rs.getString ("cd_conta"));
          ed.setCd_Conta_Credito ("    ");
          acento = "";
        }
        if (rs.getString ("dm_acao").equals ("C")) {
          ed.setCd_Conta_Debito ("    ");
          ed.setCd_Conta_Credito (rs.getString ("cd_conta"));
          acento = "-";
        }

        /*Verifica os centros de custos de cada lancamento*/
        sql = "select cc.cd_centro_custo, lcc.vl_lancamento, cc.cd_centro_analitico, lc.oid_conta, lc.oid_solicitacao ";
        sql += "from lancamentos_contabeis lc, lancamentos_centros_custos lcc, centros_custos cc ";
        sql += "where lc.oid_lancamento = lcc.oid_lancamento ";
        sql += "and cc.oid_centro_custo = lcc.oid_centro_custo ";
        sql += "and lc.oid_lancamento=" + rs.getLong ("oid_lancamento");
        cc = this.executasql.executarConsulta (sql);
        centros = "";
        String espacos = "                    ";
        int yy = 0;
        while (cc.next ()) {
          int xx = String.valueOf (espacos).length () - String.valueOf (cc.getString ("cd_centro_custo")).length ();
          yy = 0;
          if (acento.equals ("-")) {
            yy = (String.valueOf (espacos).length () - 2 - 1) - String.valueOf (cc.getString ("vl_lancamento")).length ();
          }
          else {
            yy = (String.valueOf (espacos).length () - 2) - String.valueOf (cc.getString ("vl_lancamento")).length ();
          }
          centros += cc.getString ("cd_centro_analitico") + espacos.substring (0 , xx) + espacos.substring (0 , yy) + acento + cc.getString ("vl_lancamento");
        }
        ed.setNm_Complemento_Historico (centros);
        ed.setOID_Unidade (oid_unidade);
        ed.setCD_Unidade_Contabil (rs.getString ("cd_unidade_contabil"));
        ed.setDm_Totalizado ("N");
        ed.setDt_Lancamento (rs.getString ("dt_lancamento"));
        ed.setNr_Documento (rs.getLong ("oid_solicitacao"));
        ed.setNm_Tipo_Evento (rs.getString ("dm_acao"));
        int aux = rs.getInt ("oid_Historico");
        ed.setOid_Historico (new Integer (aux));

        list.add (ed);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao gerar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }
    // alteraExportado(oid_unidade, dt_inicio, dt_fim);
    return list;
  }

  public ArrayList GeraEDI_Notas (long oid_unidade , String dt_inicio , String dt_fim) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();
    Lancamento_Centro_CustoED centro = new Lancamento_Centro_CustoED ();
    ResultSet res = null;
    ResultSet rs = null;
    ResultSet rz = null;
    ResultSet cc = null;
    ResultSet lote = null;
    String acumulado = "";
    String centros = "";
    String Historico = "";
    String Valor = "";
    String acento = "";
    int j = 0 , i = 0;

    try {

      sql = "Select Notas_Fiscais_Transacoes.DT_Entrada, Notas_Fiscais_Transacoes.OID_Nota_Fiscal, " +
          " Notas_Fiscais_Transacoes.NR_Nota_Fiscal " +
          " FROM Notas_Fiscais_Transacoes " +
          " WHERE Notas_Fiscais_Transacoes.DT_Entrada >= '" + dt_inicio + "'" +
          " AND Notas_Fiscais_Transacoes.DT_Entrada <= '" + dt_fim + "'" +
          " AND Notas_Fiscais_Transacoes.OID_Unidade_Contabil = " + oid_unidade +
          " Order BY Notas_Fiscais_Transacoes.DT_Entrada";
      lote = null;
      lote = this.executasql.executarConsulta (sql);
      String OID_Nota_Fiscal = "";

      StringBuffer sbMensagem = new StringBuffer ("<br>");
      ArrayList arrOidLote = new ArrayList ();
      DecimalFormat dec = new DecimalFormat ("###,###,##0.00");

      while (lote.next ()) {
        OID_Nota_Fiscal = lote.getString ("OID_Nota_Fiscal");
        Historico = "";

        if (!arrOidLote.contains (OID_Nota_Fiscal)) {
          sql = "select sum(vl_lancamento) from lancamentos_contabeis where dm_acao = 'D' AND OID_Nota_Fiscal = '" + OID_Nota_Fiscal + "'";
          ResultSet rsDebito = this.executasql.executarConsulta (sql);
          double valorDebito = 0;
          double valorCredito = 0;
          while (rsDebito.next ()) {
            valorDebito = rsDebito.getDouble (1);
          }

          sql = "select sum(vl_lancamento) from lancamentos_contabeis where dm_acao = 'C' AND OID_Nota_Fiscal = '" + OID_Nota_Fiscal + "'";

          ResultSet rsCredito = this.executasql.executarConsulta (sql);
          while (rsCredito.next ()) {
            valorCredito += rsCredito.getDouble (1);
          }

          if (valorDebito != valorCredito) {
            sbMensagem.append ("Valor debito = " + dec.format (valorDebito) + " valor credito = " + dec.format (valorCredito) + " Nota Fiscal : " + lote.getString ("NR_Nota_Fiscal") + "<br>");
            arrOidLote.add (OID_Nota_Fiscal);
          }
        }
        //depois busca os lancamentos por um ou por outro!

        sql = "select Historicos.CD_Historico, " +
            " Lancamentos_Contabeis.OID_Lancamento, " +
            " Lancamentos_Contabeis.VL_Lancamento, " +
            " targget_rce(Lancamentos_Contabeis.NM_Complementar) as NM_Complementar, " +
            " Lancamentos_Contabeis.OID_Nota_Fiscal, " +
            " Contas.CD_Conta, " +
            " Lancamentos_Contabeis.DM_Acao, " +
            " Lancamentos_Contabeis.OID_Historico, " +
            " Unidades.CD_Unidade_Contabil, " +
            " Lancamentos_Contabeis.DT_Lancamento ";
        sql += " FROM Historicos, Lancamentos_Contabeis, Contas, Unidades ";
        sql += " WHERE Unidades.OID_Unidade = Lancamentos_Contabeis.OID_Unidade_Contabil ";
        sql += " AND Lancamentos_Contabeis.OID_Conta = Contas.OID_Conta ";
        sql += " AND Lancamentos_Contabeis.OID_Historico = Historicos.OID_Historico ";
        sql += " AND Lancamentos_Contabeis.OID_Unidade_Contabil= " + oid_unidade;
        sql += " AND Lancamentos_Contabeis.OID_Nota_Fiscal = '" + OID_Nota_Fiscal + "'";
        if (!Valor.equals ("")) {
          sql += " AND Lancamentos_Contabeis.OID_Lancamento NOT IN(" + Valor + ") ";
        }

        rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          Tipo_EventoED ed = new Tipo_EventoED ();
          //busca os valores para o historico para aquela linha em questao
          Historico = rs.getString ("cd_historico");
          if ( (Historico == null)) {
            Historico = "";
          }
          else {
            Historico = rs.getString ("cd_historico");
          }

          ed.setCd_Historico (Historico);

          ed.setVl_Lancamento (rs.getDouble ("vl_lancamento"));

          if (Valor.equals ("")) {
            Valor = rs.getString ("OID_Lancamento");
          }
          else {
            Valor += "," + rs.getString ("OID_Lancamento");
          }

          if (rs.getString ("nm_complementar") != null) {
            Historico += " " + rs.getString ("nm_complementar");
          }

          sql = "select nf.oid_pessoa,targget_rce(p.nm_razao_social) as nm_razao_social ";
          sql += "from pessoas p, notas_fiscais_transacoes nf ";
          sql += "where nf.oid_nota_fiscal = '" + OID_Nota_Fiscal + "' ";
          sql += "and nf.oid_pessoa = p.oid_pessoa ";
          rz = this.executasql.executarConsulta (sql);

          while (rz.next ()) {
            Historico += " NR. " + lote.getString ("NR_Nota_Fiscal") + " " + rz.getString ("nm_razao_social");
          }

          ed.setVl_Lancamento (rs.getDouble ("VL_Lancamento"));

          ed.setNm_Historico (Historico);

          if (rs.getString ("dm_acao").equals ("D")) {
            ed.setCd_Conta_Debito (rs.getString ("cd_conta"));
            ed.setCd_Conta_Credito ("    ");
            acento = "";
          }
          if (rs.getString ("dm_acao").equals ("C")) {
            ed.setCd_Conta_Debito ("    ");
            ed.setCd_Conta_Credito (rs.getString ("cd_conta"));
            acento = "-";
          }

          //Verifica os centros de custos de cada lancamento
          //String sqlBusca ="select cc.cd_centro_custo, lcc.vl_lancamento, "+
          //" cc.cd_centro_analitico, lc.oid_conta, lc.oid_compromisso ";
          //sqlBusca +="from lancamentos_contabeis lc, "+
          //" lancamentos_centros_custos lcc, centros_custos cc ";
          //sqlBusca +="where lc.oid_lancamento = lcc.oid_lancamento ";
          //sqlBusca +="and cc.oid_centro_custo = lcc.oid_centro_custo ";
          //sqlBusca +="and lc.oid_lancamento="+rs.getLong("oid_lancamento");
          //cc = this.executasql.executarConsulta(sqlBusca);
          centros = "";
          String espacos = "                                        ";

          //int yy = 0;
          //String vl_lancamento = "0.00";
          //yy = (espacos.length()-2) - vl_lancamento.length();
          //centros = espacos.substring(0,yy) + vl_lancamento;

          //while(cc.next()){
          //  int xx = String.valueOf(espacos).length() - String.valueOf(cc.getString("cd_centro_analitico")).length();
          //  yy = 0;
          //  if(acento.equals("-")){
          //    yy = (String.valueOf(espacos).length()-2-1) - String.valueOf(cc.getString("vl_lancamento")).length();
          //  }else yy = (String.valueOf(espacos).length()-2) - String.valueOf(cc.getString("vl_lancamento")).length();
          //  centros += cc.getString("cd_centro_analitico") + espacos.substring(0,xx) + espacos.substring(0,yy)  + acento + cc.getString("vl_lancamento");
          //}
          ed.setNm_Complemento_Historico (centros);
          ed.setOID_Unidade (oid_unidade);
          ed.setCD_Unidade_Contabil (rs.getString ("cd_unidade_contabil"));
          ed.setDm_Totalizado ("N");
          ed.setDt_Lancamento (lote.getString ("DT_Entrada"));
          ed.setNr_Documento (lote.getLong ("nr_nota_fiscal"));
          ed.setNm_Tipo_Evento (rs.getString ("dm_acao"));
          int aux = rs.getInt ("oid_Historico");
          ed.setOid_Historico (new Integer (aux));
          list.add (ed);
        }
      }

      if (arrOidLote.size () > 0) {
        Excecoes excecoes = new Excecoes ();
        excecoes.setClasse (this.getClass ().getName ());
        excecoes.setMensagem (sbMensagem.toString ());
        excecoes.setMetodo ("GeraEDI_Notas");
        throw excecoes;
      }

    }
    catch (Exception exc) {
      if (exc instanceof Excecoes) {
        throw (Excecoes) exc;
      }
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao gerar EDI de Notas");
      excecoes.setMetodo ("GeraEDI_Notas");
      excecoes.setExc (exc);
      throw excecoes;
    }

    alteraExportado (oid_unidade , dt_inicio , dt_fim);
    return list;
  }

  public ArrayList lista_Contabil_Lote_Pagamento (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, " +
          "pr.OID_Unidade_Contabil, Com.oid_Lote_Pagamento, pr.dm_acao, " +
          "pr.oid_Historico, his.nm_historico, pr.oid_conta, cc.nm_conta, " +
          "cc.cd_conta, pr.vl_lancamento, pr.nm_complementar, pr.dt_lancamento " +
          "From  lancamentos_contabeis pr, Lotes_Pagamentos Com, contas cc, Historicos his " +
          "Where pr.oid_Lote_Pagamento = Com.oid_Lote_Pagamento and pr.oid_conta=cc.oid_conta" +
          " and his.oid_Historico=pr.oid_Historico";

      if (String.valueOf (ed.getOID_Lote_Pagamento ()) != null &&
          !String.valueOf (ed.getOID_Lote_Pagamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lote_Pagamento ()).equals ("null")) {
        sql += " AND pr.OID_Lote_Pagamento = '" + ed.getOID_Lote_Pagamento () + "'";
      }

      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      sql += " Order by pr.oid_Lancamento ";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      while (res.next ()) {
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));

        edVolta.setOID_Lote_Pagamento (res.getLong ("OID_Lote_Pagamento"));

        edVolta.setDT_Lancamento (res.getString ("dt_lancamento"));
        dataFormatada.setDT_FormataData (edVolta.getDT_Lancamento ());
        edVolta.setDT_Lancamento (dataFormatada.getDT_FormataData ());

        edVolta.setDM_Acao (res.getString ("dm_acao"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));

        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setCD_Historico (res.getLong ("oid_Historico"));

        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }

        edVolta.setVL_Lancamento (valor);

        sql = "Select CD_Unidade from Unidades where OID_Unidade=" + res.getLong ("OID_Unidade_Contabil");
        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);

        while (resLocal.next ()) {
          edVolta.setCD_Unidade_Contabil (resLocal.getString ("CD_Unidade"));
        }
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar - compromisso");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Lancamento_ContabilED getByRecord_Lote_Pagamento (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    double valor = 0;
    Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, pr.OID_Unidade_Contabil, " +
          "pr.dm_acao, pr.oid_Historico, " +
          "his.nm_historico, his.cd_historico, pr.OID_Lote_Pagamento, pr.oid_conta, cc.nm_conta, " +
          "cc.cd_conta, pr.vl_lancamento, pr.nm_complementar " +
          "From  lancamentos_contabeis pr, Lotes_Pagamentos nf, contas cc, Historicos his " +
          "Where pr.OID_Lote_Pagamento = nf.OID_Lote_Pagamento " +
          " and his.oid_Historico=pr.oid_Historico" +
          " and pr.oid_conta=cc.oid_conta";

      if (String.valueOf (ed.getOID_Lote_Pagamento ()) != null &&
          !String.valueOf (ed.getOID_Lote_Pagamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lote_Pagamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lote_Pagamento ()).equals ("0")) {
        sql += " AND pr.OID_Lote_Pagamento = " + ed.getOID_Lote_Pagamento ();
      }
      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      if (String.valueOf (ed.getCD_Conta ()) != null &&
          !String.valueOf (ed.getCD_Conta ()).equals ("") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("null") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("0")) {
        sql += " AND cc.cd_conta = '" + ed.getCD_Conta () + "'";
      }

      sql += " Order by pr.oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));
        edVolta.setDM_Acao (res.getString ("dm_acao"));
        edVolta.setOID_Conta (res.getLong ("oid_conta"));

        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));

        edVolta.setOID_Historico (res.getLong ("oid_Historico"));
        edVolta.setCD_Historico (res.getLong ("cd_historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));
        edVolta.setOID_Unidade_Contabil (res.getLong ("OID_Unidade_Contabil"));
        edVolta.setOID_Lote_Pagamento (res.getLong ("OID_Lote_Pagamento"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }
        edVolta.setVL_Lancamento (valor);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public ArrayList lista_Contabil_Duplicata (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, " +
          "pr.OID_Unidade_Contabil, pr.dm_acao, pr.oid_Historico, " +
          "his.nm_historico, pr.oid_compromisso, pr.oid_conta, " +
          "cc.nm_conta, cc.cd_conta, pr.vl_lancamento, " +
          "pr.nm_complementar, pr.oid_movimento_duplicata " +
          "From  lancamentos_contabeis pr, contas cc, Historicos his " +
          "Where pr.oid_conta=cc.oid_conta" +
          " and his.oid_Historico=pr.oid_Historico";

      if (String.valueOf (ed.getOID_Compromisso ()) != null &&
          !String.valueOf (ed.getOID_Compromisso ()).equals ("0") &&
          !String.valueOf (ed.getOID_Compromisso ()).equals ("") &&
          !String.valueOf (ed.getOID_Compromisso ()).equals ("null")) {
        sql += " AND pr.OID_Compromisso = '" + ed.getOID_Compromisso () + "'";
      }

      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      if (String.valueOf (ed.getOid_movimento_duplicata ()) != null &&
          !String.valueOf (ed.getOid_movimento_duplicata ()).equals ("") &&
          !String.valueOf (ed.getOid_movimento_duplicata ()).equals ("null") &&
          !String.valueOf (ed.getOid_movimento_duplicata ()).equals ("0")) {
        sql += " AND pr.oid_movimento_duplicata = '" + ed.getOid_movimento_duplicata () + "'";
      }
      sql += " Order by pr.oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      while (res.next ()) {
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));

        edVolta.setOid_movimento_duplicata (res.getString ("oid_movimento_duplicata"));

        sql = "select nr_duplicata from duplicatas where oid_duplicata in (" +
            "select oid_duplicata from movimentos_duplicatas where oid_movimento_duplicata = '" + edVolta.getOid_movimento_duplicata () + "')";
        ResultSet rs = null;
        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          edVolta.setNr_duplicata (rs.getString ("nr_duplicata"));
        }

        edVolta.setDM_Acao (res.getString ("dm_acao"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));

        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setCD_Historico (res.getLong ("oid_Historico"));

        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }

        edVolta.setVL_Lancamento (valor);

        sql = "Select CD_Unidade from Unidades where OID_Unidade=" + res.getLong ("OID_Unidade_Contabil");
        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);

        while (resLocal.next ()) {
          edVolta.setCD_Unidade_Contabil (resLocal.getString ("CD_Unidade"));
        }
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar - compromisso");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList Lista_Contabil_Conhecimento (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, " +
          "pr.OID_Unidade_Contabil, pr.dm_acao, pr.oid_Historico, " +
          "his.nm_historico, pr.oid_conhecimento, pr.oid_conta, " +
          "cc.nm_conta, cc.cd_conta, pr.vl_lancamento, " +
          "pr.nm_complementar, conhecimentos.nr_conhecimento, unidades.CD_Unidade " +
          "From  lancamentos_contabeis pr, contas cc, Historicos his, conhecimentos, unidades " +
          "Where pr.oid_conta=cc.oid_conta" +
          " and his.oid_Historico=pr.oid_Historico" +
          " and pr.oid_conhecimento = conhecimentos.oid_conhecimento " +
          " and pr.OID_Unidade_Contabil = unidades.OID_Unidade ";

      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      if (String.valueOf (ed.getOID_Conhecimento ()) != null &&
          !String.valueOf (ed.getOID_Conhecimento ()).equals ("") &&
          !String.valueOf (ed.getOID_Conhecimento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conhecimento ()).equals ("0")) {
        sql += " AND pr.oid_conhecimento = '" + ed.getOID_Conhecimento () + "'";
      }
      sql += " Order by pr.oid_Lancamento ";



      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      while (res.next ()) {
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));

        edVolta.setOID_Conhecimento (res.getString ("oid_conhecimento"));

        edVolta.setNR_Conhecimento (res.getString ("NR_Conhecimento"));

        edVolta.setDM_Acao (res.getString ("dm_acao"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));

        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setCD_Historico (res.getLong ("oid_Historico"));

        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }

        edVolta.setVL_Lancamento (valor);

        edVolta.setCD_Unidade_Contabil (res.getString ("CD_Unidade"));

        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar - compromisso");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Lancamento_ContabilED getByRecord_Duplicata (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    double valor = 0;
    Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, pr.OID_Unidade_Contabil, pr.dm_acao, pr.oid_Historico, his.nm_historico, his.cd_historico, pr.oid_Compromisso, pr.oid_conta, cc.nm_conta, cc.cd_conta, pr.vl_lancamento, pr.nm_complementar, pr.oid_movimento_duplicata " +
          "From  lancamentos_contabeis pr, contas cc, Historicos his " +
          "Where his.oid_Historico=pr.oid_Historico" +
          " and pr.oid_conta=cc.oid_conta";

      if (String.valueOf (ed.getOID_Compromisso ()) != null &&
          !String.valueOf (ed.getOID_Compromisso ()).equals ("") &&
          !String.valueOf (ed.getOID_Compromisso ()).equals ("null") &&
          !String.valueOf (ed.getOID_Compromisso ()).equals ("0")) {
        sql += " AND pr.OID_Compromisso = " + ed.getOID_Compromisso ();
      }
      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      if (String.valueOf (ed.getCD_Conta ()) != null &&
          !String.valueOf (ed.getCD_Conta ()).equals ("") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("null") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("0")) {
        sql += " AND cc.cd_conta = '" + ed.getCD_Conta () + "'";
      }

      sql += " Order by pr.oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));
        edVolta.setDM_Acao (res.getString ("dm_acao"));
        edVolta.setOID_Conta (res.getLong ("oid_conta"));

        edVolta.setOid_movimento_duplicata (res.getString ("oid_movimento_duplicata"));

        sql = "select nr_duplicata from duplicatas where oid_duplicata in (" +
            "select oid_duplicata from movimentos_duplicatas where oid_movimento_duplicata = '" + edVolta.getOid_movimento_duplicata () + "')";
        ResultSet rs = null;
        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          edVolta.setNr_duplicata (rs.getString ("nr_duplicata"));
        }

        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));

        edVolta.setOID_Historico (res.getLong ("oid_Historico"));
        edVolta.setCD_Historico (res.getLong ("cd_historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));
        edVolta.setOID_Unidade_Contabil (res.getLong ("OID_Unidade_Contabil"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }
        edVolta.setVL_Lancamento (valor);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public Lancamento_ContabilED getByRecord_Conhecimento (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    double valor = 0;
    Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, " +
          "pr.OID_Unidade_Contabil, pr.dm_acao, pr.oid_Historico, " +
          "his.nm_historico, pr.oid_conhecimento, pr.oid_conta, " +
          "cc.nm_conta, cc.cd_conta, pr.vl_lancamento, " +
          "pr.nm_complementar, conhecimentos.nr_conhecimento, unidades.CD_Unidade " +
          "From  lancamentos_contabeis pr, contas cc, Historicos his, conhecimentos, unidades " +
          "Where pr.oid_conta=cc.oid_conta" +
          " and his.oid_Historico=pr.oid_Historico" +
          " and pr.oid_conhecimento = conhecimentos.oid_conhecimento " +
          " and pr.OID_Unidade_Contabil = unidades.OID_Unidade ";

      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      if (String.valueOf (ed.getOID_Conhecimento ()) != null &&
          !String.valueOf (ed.getOID_Conhecimento ()).equals ("") &&
          !String.valueOf (ed.getOID_Conhecimento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conhecimento ()).equals ("0")) {
        sql += " AND pr.oid_conhecimento = '" + ed.getOID_Conhecimento () + "'";
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));

        edVolta.setOID_Conhecimento (res.getString ("oid_conhecimento"));

        edVolta.setNR_Conhecimento (res.getString ("NR_Conhecimento"));

        edVolta.setDM_Acao (res.getString ("dm_acao"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));

        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setCD_Historico (res.getLong ("oid_Historico"));

        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }

        edVolta.setVL_Lancamento (valor);

        edVolta.setCD_Unidade_Contabil (res.getString ("CD_Unidade"));
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public ArrayList lista_Contabil_Lote_Posto (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, pr.OID_Unidade_Contabil, Com.oid_Lote_Posto, pr.dm_acao, pr.oid_Historico, his.nm_historico, pr.oid_conta, cc.nm_conta, cc.cd_conta, pr.vl_lancamento, pr.nm_complementar " +
          "From  lancamentos_contabeis pr, Lotes_Postos Com, contas cc, Historicos his " +
          "Where pr.oid_Lote_Posto = Com.oid_Lote_Posto and pr.oid_conta=cc.oid_conta" +
          " and his.oid_Historico=pr.oid_Historico";

      if (String.valueOf (ed.getOID_Lote_Posto ()) != null &&
          !String.valueOf (ed.getOID_Lote_Posto ()).equals ("") &&
          !String.valueOf (ed.getOID_Lote_Posto ()).equals ("null")) {
        sql += " AND pr.OID_Lote_Posto = '" + ed.getOID_Lote_Posto () + "'";
      }

      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      sql += " Order by pr.oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      while (res.next ()) {
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));

        edVolta.setOID_Lote_Posto (res.getLong ("OID_Lote_Posto"));

        edVolta.setDM_Acao (res.getString ("dm_acao"));

        edVolta.setOID_Conta (res.getLong ("oid_conta"));
        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));

        edVolta.setNM_Conta (res.getString ("nm_conta"));
        edVolta.setCD_Historico (res.getLong ("oid_Historico"));

        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }

        edVolta.setVL_Lancamento (valor);

        sql = "Select CD_Unidade from Unidades where OID_Unidade=" + res.getLong ("OID_Unidade_Contabil");
        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);

        while (resLocal.next ()) {
          edVolta.setCD_Unidade_Contabil (resLocal.getString ("CD_Unidade"));
        }
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar - compromisso");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Lancamento_ContabilED getByRecord_Lote_Posto (Lancamento_ContabilED ed) throws Excecoes {

    String sql = null;
    double valor = 0;
    Lancamento_ContabilED edVolta = new Lancamento_ContabilED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select distinct on(pr.oid_lancamento) pr.oid_lancamento, pr.OID_Unidade_Contabil, " +
          "pr.dm_acao, pr.oid_Historico, " +
          "his.nm_historico, his.cd_historico, pr.OID_Lote_Posto, pr.oid_conta, cc.nm_conta, " +
          "cc.cd_conta, pr.vl_lancamento, pr.nm_complementar " +
          "From  lancamentos_contabeis pr, Lotes_Postos nf, contas cc, Historicos his " +
          "Where pr.OID_Lote_Posto = nf.OID_Lote_Posto " +
          " and his.oid_Historico=pr.oid_Historico" +
          " and pr.oid_conta=cc.oid_conta";

      if (String.valueOf (ed.getOID_Lote_Posto ()) != null &&
          !String.valueOf (ed.getOID_Lote_Posto ()).equals ("") &&
          !String.valueOf (ed.getOID_Lote_Posto ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lote_Posto ()).equals ("0")) {
        sql += " AND pr.OID_Lote_Posto = " + ed.getOID_Lote_Posto ();
      }
      if (String.valueOf (ed.getOID_Lancamento ()) != null &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("null") &&
          !String.valueOf (ed.getOID_Lancamento ()).equals ("0")) {
        sql += " AND pr.oid_lancamento = '" + ed.getOID_Lancamento () + "'";
      }
      if (String.valueOf (ed.getOID_Conta ()) != null &&
          !String.valueOf (ed.getOID_Conta ()).equals ("") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOID_Conta ()).equals ("0")) {
        sql += " AND pr.oid_conta = '" + ed.getOID_Conta () + "'";
      }
      if (String.valueOf (ed.getCD_Conta ()) != null &&
          !String.valueOf (ed.getCD_Conta ()).equals ("") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("null") &&
          !String.valueOf (ed.getCD_Conta ()).equals ("0")) {
        sql += " AND cc.cd_conta = '" + ed.getCD_Conta () + "'";
      }

      sql += " Order by pr.oid_Lancamento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        edVolta.setOID_Lancamento (res.getLong ("oid_lancamento"));
        edVolta.setDM_Acao (res.getString ("dm_acao"));
        edVolta.setOID_Conta (res.getLong ("oid_conta"));

        edVolta.setVL_Lancamento (res.getDouble ("vl_lancamento"));
        edVolta.setCD_Conta (res.getString ("cd_conta"));
        edVolta.setNM_Conta (res.getString ("nm_conta"));

        edVolta.setOID_Historico (res.getLong ("oid_Historico"));
        edVolta.setCD_Historico (res.getLong ("cd_historico"));
        edVolta.setNM_Historico (res.getString ("nm_historico"));
        edVolta.setNM_Complementar (res.getString ("nm_complementar"));
        edVolta.setOID_Unidade_Contabil (res.getLong ("OID_Unidade_Contabil"));
        edVolta.setOID_Lote_Posto (res.getLong ("OID_Lote_Posto"));

        if (res.getDouble ("vl_lancamento") != 0) {
          valor = res.getDouble ("vl_lancamento");
        }
        else {
          valor = 0;
        }
        edVolta.setVL_Lancamento (valor);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public ArrayList GeraEDI_Cobranca (long oid_unidade , String dt_inicio , String dt_fim) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();
    Lancamento_Centro_CustoED centro = new Lancamento_Centro_CustoED ();
    ResultSet res = null;
    ResultSet rs = null;
    ResultSet rz = null;
    ResultSet cc = null;
    ResultSet lote = null;
    String acumulado = "";
    String centros = "";
    String Historico = "";
    String Valor = "";
    String acento = "";
    int j = 0 , i = 0;

    try {

      Historico = "";

      //depois busca os lancamentos por um ou por outro!

      sql = " select Historicos.CD_Historico, targget_rce(Historicos.nm_Historico) as nm_historico,  sum(Lancamentos_Contabeis.VL_Lancamento) as VL_Lancamento, " +
          " targget_rce(Lancamentos_Contabeis.NM_Complementar) as NM_Complementar,  Contas.CD_Conta, " +
          " Lancamentos_Contabeis.DM_Acao,  Lancamentos_Contabeis.OID_Historico, " +
          " Unidades.CD_Unidade_Contabil,  Lancamentos_Contabeis.DT_Lancamento ";
      sql += " FROM Historicos, Lancamentos_Contabeis, Contas, Unidades ";
      sql += " WHERE Unidades.OID_Unidade = Lancamentos_Contabeis.OID_Unidade_Contabil ";
      sql += " AND Lancamentos_Contabeis.OID_Conta = Contas.OID_Conta ";
      sql += " AND Lancamentos_Contabeis.OID_Historico = Historicos.OID_Historico ";
      sql += " AND Lancamentos_Contabeis.OID_Unidade_Contabil= " + oid_unidade;
      sql += " AND Lancamentos_Contabeis.OID_Movimento_Duplicata is not null";
      sql += " and lancamentos_contabeis.dt_lancamento between '" + dt_inicio + "' and '" + dt_fim + "'";
      sql += " group by lancamentos_contabeis.dt_lancamento, contas.cd_conta, Historicos.cd_historico, Historicos.nm_Historico, " +
          " lancamentos_contabeis.oid_Historico, lancamentos_contabeis.nm_complementar, " +
          " lancamentos_contabeis.dm_acao, unidades.cd_unidade_contabil";

      rs = null;
      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        Tipo_EventoED ed = new Tipo_EventoED ();
        //busca os valores para o historico para aquela linha em questao
        Historico = rs.getString ("cd_historico");
        if ( (Historico == null)) {
          Historico = "";
        }
        else {
          Historico = rs.getString ("cd_historico");
        }

        ed.setCd_Historico (Historico);

        ed.setVl_Lancamento (rs.getDouble ("vl_lancamento"));

        if (rs.getString ("nm_complementar") != null) {
          Historico += " " + rs.getString ("nm_complementar");
        }

        ed.setVl_Lancamento (rs.getDouble ("VL_Lancamento"));

        ed.setNm_Historico (Historico);

        if (rs.getString ("dm_acao").equals ("D")) {
          ed.setCd_Conta_Debito (rs.getString ("cd_conta"));
          ed.setCd_Conta_Credito ("    ");
          acento = "";
        }
        if (rs.getString ("dm_acao").equals ("C")) {
          ed.setCd_Conta_Debito ("    ");
          ed.setCd_Conta_Credito (rs.getString ("cd_conta"));
          acento = "-";
        }

        centros = "";
        String espacos = "                                          ";

        ed.setNm_Complemento_Historico (centros);
        ed.setOID_Unidade (oid_unidade);
        ed.setCD_Unidade_Contabil (rs.getString ("cd_unidade_contabil"));
        ed.setDm_Totalizado ("N");
        ed.setDt_Lancamento (rs.getString ("DT_lancamento"));
        ed.setNm_Tipo_Evento (rs.getString ("dm_acao"));
        int aux = rs.getInt ("oid_Historico");
        ed.setOid_Historico (new Integer (aux));
        list.add (ed);
      }

    }
    catch (Exception exc) {
      if (exc instanceof Excecoes) {
        throw (Excecoes) exc;
      }
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao gerar EDI de Cobranca");
      excecoes.setMetodo ("GeraEDI_Cobranca");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList GeraEDI_Caixinha (long oid_unidade , String dt_inicio , String dt_fim) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();
    Lancamento_Centro_CustoED centro = new Lancamento_Centro_CustoED ();
    ResultSet res = null;
    ResultSet rs = null;
    ResultSet rz = null;
    ResultSet cc = null;
    String acumulado = "";
    String centros = "";
    String Historico = "";
    String Valor = "";
    String acento = "";
    int j = 0 , i = 0 , conta = 0;
    String DM_Acao = "";
    String CD_Conta_Caixa = "";
    try {

      sql = "SELECT cx.cd_historico, lc.dt_lancamento, " +
          "targget_rce(lc.nm_complementar) as nm_complementar, lc.oid_conta, " +
          "ct.cd_conta, lc.oid_nota_fiscal, cxn.oid_caixa, lc.oid_lancamento, " +
          "lc.dm_acao, lc.oid_Historico, un.cd_unidade_contabil , " +
          "un.cd_unidade, lc.dt_lancamento, lc.vl_lancamento as vl_lancamento ";
      sql += "FROM Historicos cx, lancamentos_contabeis lc, contas ct, caixas cxn, unidades un ";
      if (!Valor.equals ("")) {
        sql += "where lc.oid_caixa NOT IN(" + Valor + ") and ";
      }
      else {
        sql += "Where ";
      }
      sql += "lc.oid_conta=ct.oid_conta ";
      sql += "and lc.oid_conta=ct.oid_conta ";
      sql += "and un.oid_unidade=lc.oid_unidade_contabil ";
      sql += "and lc.oid_caixa=cxn.oid_caixa ";
      sql += "and lc.dt_lancamento between '" + dt_inicio + "' and '" + dt_fim + "' ";
      sql += "and lc.oid_unidade_contabil= " + oid_unidade + " ";
      sql += "and lc.oid_Historico=cx.oid_Historico ";
      sql += "order by cxn.dt_caixa, lc.oid_caixa";

      rs = null;
      rs = this.executasql.executarConsulta (sql);

      String DT_Data_Inicial = "";
      String DT_Data_Final = "";
      double VL_Lancamento_Caixa = 0;
      String cd_unidade_contabil = null;

      while (rs.next ()) {

        if (rs.getString ("CD_Unidade").equals ("001")) {
          CD_Conta_Caixa = "1001";
        }
        else if (rs.getString ("CD_Unidade").equals ("002")) {
          CD_Conta_Caixa = "1002";
        }
        else if (rs.getString ("CD_Unidade").equals ("003")) {
          CD_Conta_Caixa = "1003";
        }
        else if (rs.getString ("CD_Unidade").equals ("004")) {
          CD_Conta_Caixa = "1004";
        }
        else if (rs.getString ("CD_Unidade").equals ("025")) {
          CD_Conta_Caixa = "1009";
        }
        else if (rs.getString ("CD_Unidade").equals ("026")) {
          CD_Conta_Caixa = "1006";
        }
        else if (rs.getString ("CD_Unidade").equals ("029")) {
          CD_Conta_Caixa = "1019";
        }
        else {
          CD_Conta_Caixa = "1002";
        }

        Tipo_EventoED ed = new Tipo_EventoED ();

        DT_Data_Inicial = rs.getString ("DT_Lancamento");
        DM_Acao = rs.getString ("dm_acao");

        if (DT_Data_Final.equals ("") || DT_Data_Inicial.equals (DT_Data_Final)) {
          cd_unidade_contabil = rs.getString ("cd_unidade_contabil");

          if (DM_Acao.equals ("D")) {
            VL_Lancamento_Caixa = VL_Lancamento_Caixa + rs.getDouble ("vl_lancamento");
          }
          else {
            VL_Lancamento_Caixa = VL_Lancamento_Caixa - rs.getDouble ("vl_lancamento");
          }
        }
        else {
          ed.setCd_Historico ("168");
          Historico = "168";
          ed.setNm_Historico (Historico);

          if (VL_Lancamento_Caixa < 0) {
            ed.setCd_Conta_Debito (CD_Conta_Caixa);
            ed.setCd_Conta_Credito ("    ");
            VL_Lancamento_Caixa = VL_Lancamento_Caixa * -1;
          }
          else {
            ed.setCd_Conta_Debito ("    ");
            ed.setCd_Conta_Credito (CD_Conta_Caixa);
          }
          ed.setVl_Lancamento (VL_Lancamento_Caixa);

          acento = "-";
          centros = "";
          ed.setNm_Complemento_Historico (centros);
          ed.setOID_Unidade (oid_unidade);
          ed.setCD_Unidade_Contabil (rs.getString ("cd_unidade_contabil"));
          ed.setDm_Totalizado ("N");
          ed.setDt_Lancamento (DT_Data_Final);
          ed.setNr_Documento (1);
          ed.setNm_Tipo_Evento ("C");
          int aux = 174;
          ed.setOid_Historico (new Integer (aux));

          list.add (ed);

          VL_Lancamento_Caixa = 0;
          ed = new Tipo_EventoED ();
          if (DM_Acao.equals ("D")) {
            VL_Lancamento_Caixa = VL_Lancamento_Caixa + rs.getDouble ("VL_Lancamento");
          }
          else {
            VL_Lancamento_Caixa = VL_Lancamento_Caixa - rs.getDouble ("VL_Lancamento");
          }
        }

        DT_Data_Final = rs.getString ("DT_Lancamento");

        Historico = rs.getString ("cd_historico");
        if ( (Historico == null)) {
          Historico = "";
        }
        else {
          Historico = rs.getString ("cd_historico");
        }

        ed.setCd_Historico (Historico);

        Historico += "NR: " + rs.getString ("oid_caixa");

        Historico += " " + rs.getString ("nm_complementar");
        ed.setNm_Historico (Historico);
        ed.setVl_Lancamento (rs.getDouble ("vl_lancamento"));

        if (Valor.equals ("")) {
          Valor = rs.getString ("oid_caixa");
        }
        else {
          Valor += "," + rs.getDouble ("oid_caixa");
        }

        if (rs.getString ("dm_acao").equals ("D")) {
          ed.setCd_Conta_Debito (rs.getString ("cd_conta"));
          ed.setCd_Conta_Credito ("    ");
          acento = "";
        }
        if (rs.getString ("dm_acao").equals ("C")) {
          ed.setCd_Conta_Debito ("    ");
          ed.setCd_Conta_Credito (rs.getString ("cd_conta"));
          acento = "-";
        }

        /*Verifica os centros de custos de cada lancamento*/
        sql = "select cc.cd_centro_custo, lcc.vl_lancamento, cc.cd_centro_analitico, lc.oid_conta, lc.oid_caixa ";
        sql += "from lancamentos_contabeis lc, lancamentos_centros_custos lcc, centros_custos cc ";
        sql += "where lc.oid_lancamento = lcc.oid_lancamento ";
        sql += "and cc.oid_centro_custo = lcc.oid_centro_custo ";
        sql += "and lc.oid_lancamento=" + rs.getLong ("oid_lancamento");
        cc = this.executasql.executarConsulta (sql);
        centros = "";
        String espacos = "                    ";
        int yy = 0;
        while (cc.next ()) {
          int xx = String.valueOf (espacos).length () - String.valueOf (cc.getString ("cd_centro_custo")).length ();
          yy = 0;
          if (acento.equals ("-")) {
            yy = (String.valueOf (espacos).length () - 2 - 1) - String.valueOf (cc.getString ("vl_lancamento")).length ();
          }
          else {
            yy = (String.valueOf (espacos).length () - 2) - String.valueOf (cc.getString ("vl_lancamento")).length ();
          }
          centros += cc.getString ("cd_centro_analitico") + espacos.substring (0 , xx) + espacos.substring (0 , yy) + acento + cc.getString ("vl_lancamento");
        }
        ed.setNm_Complemento_Historico (centros);
        ed.setOID_Unidade (oid_unidade);
        ed.setCD_Unidade_Contabil (rs.getString ("cd_unidade_contabil"));
        ed.setDm_Totalizado ("N");
        ed.setDt_Lancamento (rs.getString ("dt_lancamento"));
        ed.setNr_Documento (rs.getLong ("oid_caixa"));
        ed.setNm_Tipo_Evento (rs.getString ("dm_acao"));
        int aux = rs.getInt ("oid_Historico");
        ed.setOid_Historico (new Integer (aux));

        list.add (ed);
      }

      Tipo_EventoED ed = new Tipo_EventoED ();

      ed.setCd_Historico ("168");
      Historico = "168";
      ed.setNm_Historico (Historico);

      if (VL_Lancamento_Caixa < 0) {
        ed.setCd_Conta_Debito (CD_Conta_Caixa);
        ed.setCd_Conta_Credito ("    ");
        VL_Lancamento_Caixa = VL_Lancamento_Caixa * -1;
      }
      else {
        ed.setCd_Conta_Debito ("    ");
        ed.setCd_Conta_Credito (CD_Conta_Caixa);
      }
      ed.setVl_Lancamento (VL_Lancamento_Caixa);

      acento = "-";
      centros = "";
      ed.setNm_Complemento_Historico (centros);
      ed.setOID_Unidade (oid_unidade);
      ed.setCD_Unidade_Contabil (cd_unidade_contabil);
      ed.setDm_Totalizado ("N");
      ed.setDt_Lancamento (DT_Data_Final);
      ed.setNr_Documento (1);
      ed.setNm_Tipo_Evento ("C");
      int aux = 174;
      ed.setOid_Historico (new Integer (aux));
      list.add (ed);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao gerar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return list;
  }

  public void inclui_CTB_Movimento_Conta_Corrente_Transferencia (Movimento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    ResultSet resLocal = null;
    ResultSet resLocal2 = null;
    String NM_Complementar="";
    try {

      sql = " SELECT *, Historicos.NM_Historico, Contas_Correntes.oid_Conta as oid_Conta_CC "
          + " FROM   Movimentos_Contas_Correntes, Contas_Correntes, Historicos  "
          + " WHERE  Movimentos_Contas_Correntes.oid_Conta_Corrente =  Contas_Correntes.oid_Conta_Corrente "
          + " AND    Movimentos_Contas_Correntes.oid_historico =  Historicos.oid_historico "
          + " AND    Contas_Correntes.DM_Contabilizacao <> 'N' "
          + " AND    Movimentos_Contas_Correntes.oid_Movimento_Conta_Corrente = '" + ed.getOid_Movimento_Conta_Corrente () + "'"
          + " AND    Movimentos_Contas_Correntes.oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente() + "'"
      	  + " AND    Movimentos_Contas_Correntes.dt_movimento_conta_corrente = '" + ed.getDT_Movimento_Conta_Corrente() + "'";


      resLocal = executasql.executarConsulta (sql);
      while (resLocal.next ()) {

        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
        NM_Complementar=resLocal.getString("NR_Documento")+" "+resLocal.getString ("NM_Complemento_Historico"); //+"("+resLocal.getString ("oid_Movimento_Conta_Corrente")+")" ;

        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
        edLC.setCD_Historico (resLocal.getLong ("oid_historico"));
        edLC.setVL_Lancamento (resLocal.getDouble ("vl_lancamento"));
        edLC.setOID_Movimento_Conta_Corrente (resLocal.getLong ("OID_Movimento_Conta_Corrente"));
        edLC.setOID_Origem (10);
        edLC.setNM_Complementar (NM_Complementar);
        edLC.setDT_Lancamento (resLocal.getString ("DT_Movimento_Conta_Corrente"));
        edLC.setDT_Stamp (Data.getDataDMY ());
        edLC.setDM_Stamp ("X");
        edLC.setTx_Chave_Origem (String.valueOf (edLC.getOID_Movimento_Conta_Corrente ()));

        if (resLocal.getString ("dm_tipo_conta_corrente").equals ("U")) {
          if (resLocal.getString ("DM_Debito_Credito").equals ("C")) {
            //transferencias so lanca o mvto credito e se nao for caixa

            edLC.setDM_Acao ("D");
            edLC.setOID_Conta (resLocal.getLong ("oid_conta"));
            edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

            edLC.setDM_Acao ("C");
            edLC.setOID_Conta (resLocal.getLong ("oid_Conta_CC"));
            this.inclui_Lancamento_Contabil (edLC);
          }
        } else {
          if (resLocal.getString ("DM_Debito_Credito").equals ("D")) {

            edLC.setDM_Acao ("D");
            edLC.setOID_Conta (resLocal.getLong ("oid_conta"));
            edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

            edLC.setDM_Acao ("C");
            edLC.setOID_Conta (resLocal.getLong ("oid_Conta_CC"));
            this.inclui_Lancamento_Contabil (edLC);
          }
        }

      }


      sql = " UPDATE Movimentos_Contas_Correntes SET ";
      sql += " DM_Contabilizado = 'S'";
      sql += " WHERE oid_Movimento_Conta_Corrente = " + ed.getOid_Movimento_Conta_Corrente ();
      // executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      exc.printStackTrace ();
      excecoes.setMetodo ("inclui lancamentos");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void inclui_CTB_Movimento_Conta_Corrente_Juros_Tarifas (Movimento_Conta_CorrenteED ed) throws Excecoes {

	    String sql = null;
	    ResultSet resLocal = null;
	    ResultSet resLocal2 = null;
	    String NM_Complementar="";
	    try {

	      sql = " SELECT *, Historicos.NM_Historico, Contas_Correntes.oid_Conta as oid_Conta_CC, Contas_Correntes.dm_tipo_conta_corrente "
	          + " FROM   Movimentos_Contas_Correntes, Contas_Correntes, Historicos  "
	          + " WHERE  Movimentos_Contas_Correntes.oid_Conta_Corrente =  Contas_Correntes.oid_Conta_Corrente "
	          + " AND    Movimentos_Contas_Correntes.oid_historico =  Historicos.oid_historico "
	          + " AND    Contas_Correntes.DM_Contabilizacao <> 'N' "
	          + " AND    Movimentos_Contas_Correntes.oid_Movimento_Conta_Corrente = '" + ed.getOid_Movimento_Conta_Corrente () + "'"
	          + " AND    Movimentos_Contas_Correntes.oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente() + "'"
	      	  + " AND    Movimentos_Contas_Correntes.dt_movimento_conta_corrente = '" + ed.getDT_Movimento_Conta_Corrente() + "'";
	      resLocal = executasql.executarConsulta (sql);
	      while (resLocal.next ()) {

	        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
	        NM_Complementar=resLocal.getString("NR_Documento")+" "+resLocal.getString ("NM_Complemento_Historico"); //+"("+resLocal.getString ("oid_Movimento_Conta_Corrente")+")" ;

	        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
	        edLC.setCD_Historico (resLocal.getLong ("oid_historico"));
	        edLC.setVL_Lancamento (resLocal.getDouble ("vl_lancamento"));
	        edLC.setOID_Movimento_Conta_Corrente (resLocal.getLong ("OID_Movimento_Conta_Corrente"));
	        edLC.setOID_Origem (10);
	        edLC.setNM_Complementar (NM_Complementar);
	        edLC.setDT_Lancamento (resLocal.getString ("DT_Movimento_Conta_Corrente"));
	        edLC.setDT_Stamp (Data.getDataDMY ());
	        edLC.setDM_Stamp ("X");
	        edLC.setTx_Chave_Origem (String.valueOf (edLC.getOID_Movimento_Conta_Corrente ()));

            if (resLocal.getString ("DM_Debito_Credito").equals ("C")) {
                //transferencias so lanca o mvto credito e se nao for caixa
                if ("U".equals(resLocal.getString("dm_tipo_conta_corrente"))){
                    edLC.setDM_Acao ("D");
                }else{
                    edLC.setDM_Acao ("C");
                }
                edLC.setOID_Conta (resLocal.getLong ("oid_conta"));
                edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

                if ("U".equals(resLocal.getString("dm_tipo_conta_corrente"))){
                    edLC.setDM_Acao ("C");
                }else{
                    edLC.setDM_Acao ("D");
                }
                edLC.setOID_Conta (resLocal.getLong ("oid_Conta_CC"));
                this.inclui_Lancamento_Contabil (edLC);
              } else {
                  if ("U".equals(resLocal.getString("dm_tipo_conta_corrente"))){
                     edLC.setDM_Acao ("C");
                  }else{
                     edLC.setDM_Acao ("D");
                  }
                  edLC.setOID_Conta (resLocal.getLong ("oid_conta"));
                  edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

                  if ("U".equals(resLocal.getString("dm_tipo_conta_corrente"))){
                    edLC.setDM_Acao ("D");
                  }else{
                    edLC.setDM_Acao ("C");
                  }
                  edLC.setOID_Conta (resLocal.getLong ("oid_Conta_CC"));
                  this.inclui_Lancamento_Contabil (edLC);
              }
  	      }

	      sql = " UPDATE Movimentos_Contas_Correntes SET ";
	      sql += " DM_Contabilizado = 'S'";
	      sql += " WHERE oid_Movimento_Conta_Corrente = " + ed.getOid_Movimento_Conta_Corrente ();
	      //executasql.executarUpdate (sql);

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      exc.printStackTrace ();
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void inclui_CTB_Movimento_Conta_Correnteold (Movimento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    ResultSet resLocal = null;
    String NM_Complementar="";
    try {

      sql = " SELECT *, Historicos.NM_Historico, Contas_Correntes.oid_Conta as oid_Conta_CC "
          + " FROM   Movimentos_Contas_Correntes, Contas_Correntes  "
          + " WHERE  Movimentos_Contas_Correntes.oid_Conta_Corrente =  Contas_Correntes.oid_Conta_Corrente "
          + " AND    Movimentos_Contas_Correntes.oid_historico =  Historicos.oid_historico "
          + " AND    Contas_Correntes.DM_Contabilizacao <>'N' "
          + " AND    Movimentos_Contas_Correntes.oid_Movimento_Conta_Corrente = '" + ed.getOid_Movimento_Conta_Corrente () + "'";
      resLocal = executasql.executarConsulta (sql);
      while (resLocal.next ()) {

        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();
        NM_Complementar=resLocal.getString("NM_Historico")+" "+ resLocal.getString("NR_Documento")+" "+resLocal.getString ("NM_Complemento_Historico"); //+"("+resLocal.getString ("oid_Movimento_Conta_Corrente")+")" ;

        double vl_Lancamento = resLocal.getDouble ("vl_lancamento");
        edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
        edLC.setCD_Historico (resLocal.getLong ("oid_historico"));
        if (resLocal.getInt ("oid_moeda") != 0 &&
            resLocal.getInt ("oid_moeda") != Parametro_FixoED.getInstancia ().getOID_Moeda_Padrao ()) {
        }
        edLC.setVL_Lancamento (vl_Lancamento);
        edLC.setOID_Movimento_Conta_Corrente (resLocal.getLong ("OID_Movimento_Conta_Corrente"));
        edLC.setOID_Origem (10);

        edLC.setNM_Complementar (NM_Complementar);

        edLC.setDM_Stamp ("X");
        edLC.setDT_Stamp (Data.getDataDMY ());
        edLC.setDT_Lancamento (resLocal.getString ("DT_Movimento_Conta_Corrente"));

        //
        if (!"TRANF001".equals (resLocal.getString ("nr_documento"))) {
          //lncamentos do caixa
          if (resLocal.getString ("dm_tipo_conta_corrente").equals ("U")) {

              //BLOCO TESTADO
            if (resLocal.getString ("DM_Debito_Credito").equals ("C")) {
              edLC.setDM_Acao ("C");
              edLC.setNM_Complementar (NM_Complementar);
              edLC.setOID_Conta (resLocal.getLong ("oid_Conta_CC"));
              edLC.setNR_Lote (0);
              edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

              edLC.setDM_Acao ("D");
              edLC.setNM_Complementar (NM_Complementar);
              if (resLocal.getLong ("oid_lote_pagamento") > 0) { //
                this.inclui_Lancamento_Lote_Pagamento (resLocal.getLong ("oid_lote_pagamento") , "MCC" , resLocal.getLong ("OID_Movimento_Conta_Corrente") , edLC.getNR_Lote (),resLocal.getString ("cd_Conta_Corrente"));
              }
              else {
                edLC.setOID_Conta (resLocal.getLong ("oid_Conta"));
                edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));
              }
              //BLOCO TESTADO
            }
            else {

              //BLOCO TESTADO
              edLC.setDM_Acao ("C");
              edLC.setNM_Complementar (NM_Complementar);
              edLC.setOID_Conta (resLocal.getLong ("oid_conta"));
              edLC.setNR_Lote (0);
              edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

              edLC.setDM_Acao ("D");
              edLC.setNM_Complementar (NM_Complementar);
              edLC.setOID_Conta (resLocal.getLong ("oid_Conta_CC"));
              edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));
              //BLOCO TESTADO
            }
          }
          else {
            //lancamentos bancos
              //BLOCO TESTADO

            if (resLocal.getString ("DM_Debito_Credito").equals ("C")) {

            	if (resLocal.getString ("oid_movimento_duplicata")!=null &&
                  resLocal.getString ("oid_movimento_duplicata").length()>4) { //

            	  if (1==2) {
	                String oid_Cliente="";
	                String oid_Bordero="";
	                sql = " SELECT Pessoas.NM_Razao_Social, Duplicatas.NR_Duplicata, Duplicatas.oid_Pessoa as oid_Cliente, Movimentos_Duplicatas.oid_Bordero  "
	                    + " FROM   Movimentos_Duplicatas, Duplicatas "
	                    + " WHERE  Movimentos_Duplicatas.oid_Duplicata =  Duplicatas.oid_Duplicata "
	                    + " AND    Duplicatas.oid_Pessoa = Pessoas.oid_Pessoa "
	                    + " AND    Movimentos_Duplicatas.oid_movimento_duplicata = '" + resLocal.getString ("oid_movimento_duplicata") + "'";
	                ResultSet resDup = executasql.executarConsulta (sql);
	                if (resDup.next ()) {
	                  NM_Complementar = "VLR LIQ.TIT:"+resDup.getString ("NR_Duplicata") + " " + resDup.getString ("NM_Razao_Social");
	                  oid_Cliente=resDup.getString("oid_Cliente");
	                  oid_Bordero=resDup.getString("oid_Bordero");
	                }

	              edLC.setDM_Acao ("D");
	              edLC.setNR_Lote (0);
	              edLC.setNM_Complementar (NM_Complementar);
	              edLC.setOID_Conta (resLocal.getLong ("oid_Conta_CC"));
	              edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

	              edLC.setDM_Acao ("C");
	              edLC.setOID_Conta (resLocal.getLong ("oid_conta"));

	              if (!"".equals(oid_Cliente)) { //

	                  this.Conta = this.buscaConta (this.paramConta.getCd_conta_cliente_geral());
	                  edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());

	                  sql = " SELECT Clientes.oid_Conta as oid_Conta_Cliente "
	                      + " FROM   Clientes, Contas  "
	                      + " WHERE  Clientes.oid_Conta = Contas.oid_Conta "
	                      + " AND    Clientes.oid_Conta >0 "
	                      + " AND    Clientes.oid_Cliente = '" + oid_Cliente + "'";
	                  ResultSet resCli = executasql.executarConsulta (sql);
	                  if (resCli.next ()) {
	                    edLC.setOID_Conta (resCli.getLong ("oid_Conta_Cliente"));
	                  }
	                }
	                NM_Complementar = "VLR LIQ.TIT:"+resDup.getString ("NR_Duplicata") + " Bordero: " + oid_Bordero;
	                edLC.setNM_Complementar (NM_Complementar);
	                edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));
            	  }
              }else {
                edLC.setDM_Acao ("D");
                edLC.setNR_Lote (0);
                edLC.setNM_Complementar (NM_Complementar);
                edLC.setOID_Conta (resLocal.getLong ("oid_Conta_CC"));
                edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

                edLC.setDM_Acao ("C");
                edLC.setOID_Conta (resLocal.getLong ("oid_conta"));
                edLC.setNM_Complementar (NM_Complementar);
                edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));
              }

            }
            else {
              edLC.setDM_Acao ("C");
              edLC.setNM_Complementar (NM_Complementar);
              edLC.setOID_Conta (resLocal.getLong ("oid_Conta_CC"));
              edLC.setNR_Lote (0);
              edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

              edLC.setDM_Acao ("D");
              edLC.setNM_Complementar (NM_Complementar);
              if (resLocal.getLong ("oid_lote_pagamento") > 0) { //
                this.inclui_Lancamento_Lote_Pagamento (resLocal.getLong ("oid_lote_pagamento") , "MCC" , resLocal.getLong ("OID_Movimento_Conta_Corrente") , edLC.getNR_Lote () ,resLocal.getString ("cd_Conta_Corrente"));
              }
              else {
                edLC.setOID_Conta (resLocal.getLong ("oid_Conta"));
                edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));
              }
            }
          }
        }

        if ("TRANF001".equals (resLocal.getString ("nr_documento"))) {
          //lncamentos do caixa

          if (resLocal.getString ("dm_tipo_conta_corrente").equals ("U")) {
            if (resLocal.getString ("DM_Debito_Credito").equals ("C")) {
              //transferencias so lanca o mvto credito e se nao for caixa

              edLC.setDM_Acao ("D");
              edLC.setNM_Complementar (NM_Complementar);
              edLC.setOID_Conta (resLocal.getLong ("oid_conta"));
              edLC.setNR_Lote (0);
              edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

              edLC.setDM_Acao ("C");
              edLC.setNM_Complementar (NM_Complementar);
              edLC.setOID_Conta (resLocal.getLong ("oid_Conta_CC"));
              edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));
            }
          }
          else {
            if (resLocal.getString ("DM_Debito_Credito").equals ("D")) {

              edLC.setDM_Acao ("C");
              edLC.setNM_Complementar (NM_Complementar);
              edLC.setOID_Conta (resLocal.getLong ("oid_conta"));
              edLC.setNR_Lote (0);
              //edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

              edLC.setDM_Acao ("D");
              edLC.setNM_Complementar (NM_Complementar);
              edLC.setOID_Conta (resLocal.getLong ("oid_Conta_CC"));
            }
          }
        }
      }
      sql = " UPDATE Movimentos_Contas_Correntes SET ";
      sql += " DM_Contabilizado = 'S'";
      sql += " WHERE oid_Movimento_Conta_Corrente = " + ed.getOid_Movimento_Conta_Corrente ();
      //executasql.executarUpdate (sql);


    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      exc.printStackTrace ();
      excecoes.setMetodo ("inclui lancamentos");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  private void inclui_Lancamento_Lote_Pagamento (long oid_Lote_Pagamento , String dm_tipo_lancamento, long oid_Movimento_Conta_Corrente, long NR_Lote, String cd_Conta_Corrente) throws Excecoes {

	    String sql = null;
	    String NM_Complemento_Cheque = null;
	    String NM_Complemento_Compromisso = null;
	    double VL_Indice = 1;
	    ResultSet res = null;
	    ResultSet resLocal = null;
	    ResultSet rf = null;

	    try {
	      sql = " SELECT vl_lote_pagamento " +
				" FROM lotes_pagamentos " +
          		" WHERE oid_lote_pagamento = " + oid_Lote_Pagamento;

          res = this.executasql.executarConsulta(sql);

          while (res.next()) {
	          sql = " select sum(lotes_compromissos.vl_pagamento ) as vl_pagto " +
	                " from lotes_compromissos " +
	                " where dm_situacao = 'A' " +
	          		" and oid_lote_pagamento = " + oid_Lote_Pagamento;

	          resLocal = this.executasql.executarConsulta(sql);

	          while (resLocal.next()) {
	            if (res.getDouble("vl_lote_pagamento") != resLocal.getDouble("vl_pagto")){

	      	      sql = " UPDATE lotes_pagamentos  " +
	      	      		" SET vl_lote_pagamento = " + resLocal.getDouble("vl_pagto") +
	              		" WHERE oid_lote_pagamento = " + oid_Lote_Pagamento;
	      	      executasql.executarUpdate (sql);
	            }
	          }
          }

          sql = " SELECT lotes_pagamentos.oid_lote_pagamento" +
	          "     ,lotes_pagamentos.nr_documento as nr_documento_lote" +
	          "     ,lotes_pagamentos.vl_lote_pagamento as vl_lote_pagamento" +
	          "     ,lotes_pagamentos.oid_unidade" +
	          "     ,lotes_pagamentos.dt_Emissao" +
	          "     ,lotes_pagamentos.dt_compensacao" +
	          "     ,lotes_compromissos.oid_lote_compromisso" +
	          "     ,(lotes_compromissos.vl_pagamento - lotes_compromissos.vl_multa_pagamento - lotes_compromissos.vl_outras_despesas + lotes_compromissos.vl_desconto) as vl_pagamento" +
	          "     ,lotes_compromissos.vl_multa_pagamento" +
	          "     ,lotes_compromissos.vl_juros_pagamento" +
	          "     ,lotes_compromissos.vl_desconto" +
	          "     ,lotes_compromissos.vl_outras_despesas" +
	          "     ,compromissos.nr_documento as nr_documento_compromisso" +
	          "     ,compromissos.nr_compromisso" +
	          "     ,compromissos.oid_compromisso" +
	          "     ,compromissos.oid_unidade as oid_unidade_compromisso" +
	          "     ,compromissos.oid_conta as OID_Conta_Compromisso" +
	          "     ,compromissos.oid_pessoa as OID_Pessoa_Fornecedor" +
	          "     ,compromissos.tx_observacao as tx_observacao_compromisso" +
	          "     ,contas.oid_historico" +
	          "     ,contas.oid_conta" +
	          "     ,contas_correntes.nr_conta_corrente" +
	          "     ,contas_correntes.oid_conta_transitoria as OID_conta_transitoria_compensacao_cheque" +
	          "     ,tipos_documentos_lote.nm_tipo_documento as nm_tipo_documento_lote" +
	          "     ,tipos_documentos_lote.dm_compensacao " +
	          "     ,tipos_documentos_compromisso.nm_tipo_documento as nm_tipo_documento_compromisso" +
	          "     ,tipos_documentos_compromisso.oid_tipo_documento as oid_tipo_documento_compromisso" +
	          "     ,pessoas_compromisso.nm_razao_social  as nm_razao_social_compromisso" +
	          "     ,pessoas_conta_corrente.nm_razao_social  as nm_razao_social_conta_corrente " +
	          " FROM compromissos" +
	          "     ,pessoas pessoas_compromisso" +
	          "     ,pessoas pessoas_conta_corrente" +
	          "     ,tipos_documentos tipos_documentos_lote" +
	          "     ,tipos_documentos tipos_documentos_compromisso" +
	          "     ,lotes_compromissos" +
	          "     ,lotes_pagamentos" +
	          "     ,contas_correntes" +
	          "     ,contas " +
	          " WHERE Lotes_Compromissos.oid_compromisso = compromissos.oid_compromisso " +
	          "   and Lotes_Compromissos.oid_Lote_Pagamento = Lotes_Pagamentos.oid_Lote_Pagamento " +
	          "   and Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos_lote.oid_tipo_documento " +
	          "   and Lotes_Pagamentos.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
	          "   and Contas_correntes.oid_pessoa = Pessoas_conta_corrente.oid_pessoa " +
	          "   and Contas_correntes.oid_conta = Contas.oid_conta " +
	          "   and compromissos.oid_pessoa = pessoas_compromisso.oid_pessoa " +
	          "   and Lotes_Compromissos.dm_Situacao <> 'E' " +
	          "   and compromissos.oid_tipo_documento = tipos_documentos_compromisso.oid_tipo_documento" +
	          "   and Lotes_Pagamentos.oid_Lote_Pagamento = " + oid_Lote_Pagamento;
    	  double vl_credito = 0;
    	  double vl_debito = 0;

	      res = null;
	      res = this.executasql.executarConsulta (sql);
	      while (res.next ()) {

	        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();

	        NM_Complemento_Cheque =  res.getString ("nm_razao_social_conta_corrente") + " - Ch Nr. " + res.getString ("nr_documento_lote");

	        edLC.setOID_Compromisso (0);
	        edLC.setOID_Nota_Fiscal ("");
	        edLC.setOID_Conhecimento ("");
	        edLC.setOID_Solicitacao ("");
	        edLC.setOID_Acerto ("0");
	        edLC.setOID_Movimento_Servico ("");
	        edLC.setOID_Ordem_Frete ("");
	        edLC.setOID_Caixa ("");
	        edLC.setOid_movimento_duplicata ("");
	        edLC.setDM_Stamp (" ");
	        edLC.setOID_Movimento_Conta_Corrente(oid_Movimento_Conta_Corrente);

	        edLC.setDT_Lancamento (res.getString ("dt_Emissao"));
	        edLC.setOID_Lote_Pagamento (res.getLong ("OID_Lote_Pagamento"));
	        edLC.setOID_Lote_Pagamento_Compensacao (0);
	        edLC.setOID_Lote_Compromisso (res.getString ("OID_Lote_Compromisso"));
	        edLC.setDT_Stamp (Data.getDataDMY ());
	        edLC.setTx_Chave_Origem (String.valueOf (oid_Lote_Pagamento));
	        edLC.setOID_Origem (3);
	        if ("MCC".equals (dm_tipo_lancamento)) {
	          edLC.setOID_Origem (10);
	        }
	    	  // //System.out.println("   ");
	    	  // //System.out.println("   ");
	    	  // //System.out.println("   ");
	    	  // //System.out.println("Lote "+res.getString("oid_lote_pagamento"));

	        edLC.setNR_Lote (NR_Lote);
	    	  // //System.out.println("Pagamento "+res.getDouble ("VL_Pagamento"));
	    	  // //System.out.println("   1");

	        if (res.getDouble ("VL_Pagamento") > 0) {
	          edLC.setVL_Lancamento (res.getDouble ("VL_Pagamento"));
	    	  // //System.out.println("   2");
	          edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());

	          String OID_Conta_Pesquisa = "";

	          if ("98".equals(res.getString("OID_Conta_Compromisso"))){
	        	  OID_Conta_Pesquisa = "412";
	        	  if ("99999900006788".equals(res.getString ("OID_Pessoa_Fornecedor"))){
		        	  NM_Complemento_Compromisso = "Pagto OF Nr. " + res.getString ("nr_documento_compromisso") + " Fornec. " + res.getString ("tx_observacao_compromisso") + " Comp Nr. " + res.getString ("nr_compromisso");
	        	  }else{
		        	  NM_Complemento_Compromisso = "Pagto OF Nr. " + res.getString ("nr_documento_compromisso") + " Fornec. " + res.getString ("nm_razao_social_compromisso") + " Comp Nr. " + res.getString ("nr_compromisso");
	        	  }
	          }else if ("99".equals(res.getString("OID_Conta_Compromisso"))){
	        	  OID_Conta_Pesquisa = "413";
	        	  if ("99999900006788".equals(res.getString ("OID_Pessoa_Fornecedor"))){
		        	  NM_Complemento_Compromisso = "Pagto OF Nr. " + res.getString ("nr_documento_compromisso") + " Fornec. " + res.getString ("tx_observacao_compromisso") + " Comp Nr. " + res.getString ("nr_compromisso");
	        	  }else{
		        	  NM_Complemento_Compromisso = "Pagto OF Nr. " + res.getString ("nr_documento_compromisso") + " Fornec. " + res.getString ("nm_razao_social_compromisso") + " Comp Nr. " + res.getString ("nr_compromisso");
	        	  }
	          }

	    	  // //System.out.println("   3");
        	  if ("99999900007326".equals(res.getString ("OID_Pessoa_Fornecedor")) ||
        		  "99999900007164".equals(res.getString ("OID_Pessoa_Fornecedor")) ||
        		  "99999900007407".equals(res.getString ("OID_Pessoa_Fornecedor"))){
	        	  OID_Conta_Pesquisa = res.getString("OID_Conta_Compromisso");
	        	  NM_Complemento_Compromisso = "Pagto Imposto Doc. Nr: " + res.getString ("nr_documento_compromisso") + res.getString ("tx_observacao_compromisso") + " Comp Nr. " + res.getString ("nr_compromisso");
        	  }
	    	  // //System.out.println("   4");

	          if ("".equals(OID_Conta_Pesquisa)){

	        	  sql = " select Fornecedores.oid_Conta " +
	        	  		" from fornecedores, parcelamentos_financeiros, parcelas_compromissos, notas_fiscais_transacoes " +
	        	  		" WHERE parcelas_compromissos.oid_parcelamento = parcelamentos_financeiros.oid_parcelamento " +
	        	  		" AND parcelamentos_financeiros.oid_nota_fiscal = notas_fiscais_transacoes.oid_nota_fiscal " +
	        	  		" AND notas_fiscais_transacoes.oid_pessoa = fornecedores.oid_pessoa " +
	        	  		" AND Fornecedores.oid_Conta > 0 " +
	        	  		" AND parcelas_compromissos.oid_compromisso = " + res.getLong ("oid_compromisso");
		    	  // //System.out.println("   5");

		          rf = this.executasql.executarConsulta (sql);
		          while (rf.next ()) {
		        	  OID_Conta_Pesquisa = rf.getString("oid_Conta");
		        	  NM_Complemento_Compromisso = "Pagto NF. Nr.: " + res.getString ("nr_documento_compromisso") + " Fornec. " + res.getString ("nm_razao_social_compromisso") + " Comp Nr. " + res.getString ("nr_compromisso");
		          }
	          }
	          if ("".equals(OID_Conta_Pesquisa)){
		    	  // //System.out.println("   6");

	        	  sql = " select Fornecedores.oid_Conta " +
	        	  		" from fornecedores, parcelamentos_financeiros, parcelas_compromissos, notas_fiscais_transacoes, compromissos " +
	        	  		" WHERE parcelas_compromissos.oid_parcelamento = parcelamentos_financeiros.oid_parcelamento " +
	        	  		" AND parcelamentos_financeiros.oid_nota_fiscal = notas_fiscais_transacoes.oid_nota_fiscal " +
	        	  		" AND notas_fiscais_transacoes.oid_pessoa = fornecedores.oid_pessoa " +
	        	  		" AND Fornecedores.oid_Conta > 0 " +
	        	  		" AND parcelas_compromissos.oid_compromisso = compromissos.oid_compromisso_parcela" +
	        	  		" AND compromissos.oid_compromisso = " + res.getLong ("oid_compromisso");

		          rf = this.executasql.executarConsulta (sql);
		          while (rf.next ()) {
		        	  OID_Conta_Pesquisa = rf.getString("oid_Conta");
		        	  NM_Complemento_Compromisso = "Pagto NF. Nr.: " + res.getString ("nr_documento_compromisso") + " Fornec. " + res.getString ("nm_razao_social_compromisso") + " Comp Nr. " + res.getString ("nr_compromisso");
		          }
	          }
	    	  // //System.out.println("   7");
	          if ("".equals(OID_Conta_Pesquisa)){
	        	  OID_Conta_Pesquisa = res.getString("OID_Conta_Compromisso");
	        	  if ("99999900006788".equals(res.getString ("OID_Pessoa_Fornecedor"))){
		        	  NM_Complemento_Compromisso = "Entrada e Pagto do Doc: " + res.getString ("nr_documento_compromisso") + " Fornec. " + res.getString ("tx_observacao_compromisso") + " Comp Nr. " + res.getString ("nr_compromisso");
	        	  }else{
		        	  NM_Complemento_Compromisso = "Entrada e Pagto do Doc: " + res.getString ("nr_documento_compromisso") + " Fornec. " + res.getString ("nm_razao_social_compromisso") + " Comp Nr. " + res.getString ("nr_compromisso");
	        	  }
	          }
	    	  // //System.out.println("   8");
	          edLC.setOID_Conta(new Long(OID_Conta_Pesquisa).longValue());
	          sql = "Select OID_Historico from contas where oid_conta = " + OID_Conta_Pesquisa;

	          rf = this.executasql.executarConsulta (sql);
	          while (rf.next ()) {
	             edLC.setCD_Historico(rf.getLong("OID_Historico"));
	          }
	    	  // //System.out.println("   9");

	          if (edLC.getOID_Conta () == 0) { // conta generica de fernecedor
	            this.Conta = this.buscaConta (this.paramConta.getCd_conta_fornecedores_geral ());
	            edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	            edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	          }
	    	  // //System.out.println("   10");

	          edLC.setDM_Acao ("D");
	          edLC.setNM_Complementar (NM_Complemento_Compromisso);
	          vl_debito = vl_debito + edLC.getVL_Lancamento();
	    	  // //System.out.println("Debito principal "+edLC.getVL_Lancamento());
	          edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));
	          NR_Lote = edLC.getNR_Lote ();

	    	  // //System.out.println("   11");

	        if (res.getDouble ("vl_multa_pagamento") > 0) {
		    	  // //System.out.println("   12");
	          edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
	          edLC.setVL_Lancamento (res.getDouble ("vl_multa_pagamento") * VL_Indice);

	          if ("73".equals(res.getString("oid_tipo_documento_compromisso"))){
		          this.Conta = this.buscaConta ("9008");
	          }else if ("11".equals(res.getString("oid_tipo_documento_compromisso"))){
		          this.Conta = this.buscaConta ("9003");
	          }else{
		          this.Conta = this.buscaConta (this.paramConta.getCd_conta_multa_pagamento ());
	          }

	          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	          edLC.setDM_Acao ("D");
	          edLC.setNM_Complementar (NM_Complemento_Compromisso);
	    	  // //System.out.println("Debito multa"+edLC.getVL_Lancamento());
	          vl_debito = vl_debito + edLC.getVL_Lancamento();
	          this.inclui_Lancamento_Contabil (edLC);
	        }
	    	  // //System.out.println("   13");

	        if (res.getDouble ("vl_juros_pagamento") > 0) {
		    	  // //System.out.println("  14 ");
	          edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
	          edLC.setVL_Lancamento (res.getDouble ("vl_juros_pagamento") * VL_Indice);

	          if ("73".equals(res.getString("oid_tipo_documento_compromisso"))){
		          this.Conta = this.buscaConta ("9008");
	          }else if ("11".equals(res.getString("oid_tipo_documento_compromisso"))){
		          this.Conta = this.buscaConta ("9003");
	          }else{
		          this.Conta = this.buscaConta (this.paramConta.getCd_conta_juros_pagamento ());
	          }

	          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	          edLC.setDM_Acao ("D");
	          edLC.setNM_Complementar (NM_Complemento_Compromisso);
	    	  // //System.out.println("Debito juros"+edLC.getVL_Lancamento());
	          vl_debito = vl_debito + edLC.getVL_Lancamento();
	          this.inclui_Lancamento_Contabil (edLC);
	        }
	    	  // //System.out.println("   15");

	        if (res.getDouble ("vl_desconto") > 0) {
		    	  // //System.out.println("   16");
	          edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
	          edLC.setVL_Lancamento (res.getDouble ("vl_desconto") * VL_Indice);
	          this.Conta = this.buscaConta (this.paramConta.getCd_conta_desconto_pagamento ());
	          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	          edLC.setDM_Acao ("C");
	          edLC.setNM_Complementar (NM_Complemento_Compromisso);
	    	  // //System.out.println("Credito descontos"+edLC.getVL_Lancamento());
	          vl_credito = vl_credito + edLC.getVL_Lancamento();
	          this.inclui_Lancamento_Contabil (edLC);
	        }
	    	  // //System.out.println("   17");

	        if (res.getDouble ("vl_outras_despesas") > 0) {
		    	  // //System.out.println("  18 ");
	          edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
	          edLC.setVL_Lancamento (res.getDouble ("vl_outras_despesas") * VL_Indice);
	          this.Conta = this.buscaConta (this.paramConta.getCd_conta_outras_despesas_pagamento ());
	          edLC.setOID_Conta (this.Conta.getOid_Conta ().longValue ());
	          edLC.setCD_Historico (this.Conta.getOid_Historico ().longValue ());
	          edLC.setDM_Acao ("D");
	          edLC.setNM_Complementar (NM_Complemento_Compromisso);
	    	  // //System.out.println("Debito outras"+edLC.getVL_Lancamento());
	          vl_debito = vl_debito + edLC.getVL_Lancamento();
	          this.inclui_Lancamento_Contabil (edLC);
	        }
	    	  // //System.out.println("   19");

	        if ("LP".equals (dm_tipo_lancamento)) {
	          if (res.isLast ()) {
	              // //System.out.println("Cheguei na Transitoria 1  ");
		    	  // //System.out.println("   20");

	        	edLC.setVL_Lancamento (res.getDouble ("vl_lote_pagamento"));
	            if (res.getString ("dm_compensacao") != null && res.getString ("dm_compensacao").equals ("I")) {
	              edLC.setOID_Conta (res.getLong ("OID_Conta"));
	              edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
	              edLC.setCD_Historico (res.getLong ("OID_Historico"));
	              edLC.setDM_Acao ("C");
	              edLC.setNM_Complementar (NM_Complemento_Cheque);
		    	  // //System.out.println("Credito banco"+edLC.getVL_Lancamento());
		          vl_credito = vl_credito + edLC.getVL_Lancamento();
		    	  // //System.out.println("  21 ");
	              this.inclui_Lancamento_Contabil (edLC);
	            }
	            else {
	  	    	  // //System.out.println("   22");
	              edLC.setOID_Conta (res.getLong("OID_conta_transitoria_compensacao_cheque"));
		          sql = "Select OID_Historico from contas where oid_conta = " + res.getLong("OID_conta_transitoria_compensacao_cheque");
		          rf = this.executasql.executarConsulta (sql);
		          while (rf.next ()) {
		             edLC.setCD_Historico(rf.getLong("OID_Historico"));
		          }
	              edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
	              edLC.setDM_Acao ("C");
	              edLC.setNM_Complementar (NM_Complemento_Cheque);
		    	  // //System.out.println("Credito transitoria 1  "+edLC.getVL_Lancamento());
		          vl_credito = vl_credito + edLC.getVL_Lancamento();
		    	  // //System.out.println("   23");
	              this.inclui_Lancamento_Contabil (edLC);
	            }
		    	  // //System.out.println("  24 ");
			      if (Valor.round(vl_credito,2) > Valor.round(vl_debito,2)){
			    	  // //System.out.println("Credito maior que Debito");
			    	  // //System.out.println("Lote Credito "+vl_credito);
			    	  // //System.out.println("Lote Debito "+vl_debito);
			    	  // //System.out.println("Lote "+res.getString("oid_lote_pagamento"));
			    	  Excecoes excecoes = new Excecoes ();
				      excecoes.setMensagem("Credito maior que Debito no Lote nr " + oid_Lote_Pagamento);
				      throw excecoes;
			      }else if (Valor.round(vl_credito,2) < Valor.round(vl_debito,2)){
			    	  // //System.out.println("  25 ");
			    	  // //System.out.println("Credito menor que Debito");
			    	  // //System.out.println("Lote Credito "+vl_credito);
			    	  // //System.out.println("Lote Debito "+vl_debito);
			    	  // //System.out.println("Lote "+res.getString("oid_lote_pagamento"));
			    	  Excecoes excecoes = new Excecoes ();
				      excecoes.setMensagem ("Credito menor que Debito no Lote nr " + oid_Lote_Pagamento);
				      throw excecoes;
			      }

	          }
	        }
	      }


	    }

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      exc.printStackTrace ();
	      excecoes.setMetodo ("inclui lancamentos");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  private void inclui_Lancamento_Compensacao_Lote_Pagamento (long oid_Lote_Pagamento , String dm_tipo_lancamento, long oid_Movimento_Conta_Corrente, long NR_Lote, String cd_Conta_Corrente) throws Excecoes {

	    String sql = null;
	    String NM_Complemento_Cheque = null;
	    ResultSet res = null;
	    ResultSet resLocal = null;
	    ResultSet rf = null;

	    try {

          sql = " SELECT oid_lote_pagamento, vl_lote_pagamento " +
                " FROM lotes_pagamentos " +
  	            " WHERE Lotes_Pagamentos.oid_Lote_Pagamento = " + oid_Lote_Pagamento;

          res = this.executasql.executarConsulta(sql);

          while (res.next()) {
           	 sql = " select sum(lotes_compromissos.vl_pagamento ) as vl_pagto " +
                   " from lotes_compromissos " +
            	   " where dm_situacao = 'A' " +
            	   " and oid_lote_pagamento = " + res.getString("oid_lote_pagamento");

             resLocal = this.executasql.executarConsulta(sql);

             while (resLocal.next()) {
               if (res.getDouble("vl_lote_pagamento") != resLocal.getDouble("vl_pagto")){

         	      sql = " UPDATE Lotes_Pagamentos SET ";
        	      sql += " vl_lote_pagamento = " + resLocal.getDouble("vl_pagto");
        	      sql += " WHERE oid_Lote_Pagamento = " + oid_Lote_Pagamento ;

        	      executasql.executarUpdate (sql);
               }
             }
	      }

	      sql = " SELECT lotes_pagamentos.oid_lote_pagamento" +
	          "     ,lotes_pagamentos.nr_documento as nr_documento_lote" +
	          "     ,lotes_pagamentos.vl_lote_pagamento as vl_lote_pagamento" +
	          "     ,lotes_pagamentos.oid_unidade" +
	          "     ,lotes_pagamentos.dt_Emissao" +
	          "     ,lotes_pagamentos.dt_compensacao" +
	          "     ,contas.oid_historico" +
	          "     ,contas.oid_conta" +
	          "     ,contas_correntes.oid_conta_transitoria as OID_conta_transitoria_compensacao_cheque" +
	          "     ,tipos_documentos_lote.dm_compensacao " +
	          " FROM tipos_documentos tipos_documentos_lote" +
	          "     ,lotes_pagamentos" +
	          "     ,contas_correntes" +
	          "     ,contas " +
	          " WHERE Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos_lote.oid_tipo_documento " +
	          "   and Lotes_Pagamentos.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
	          "   and Contas_correntes.oid_conta = Contas.oid_conta " +
	          "   and Lotes_Pagamentos.oid_Lote_Pagamento = " + oid_Lote_Pagamento;
  	      double vl_credito = 0;
  	      double vl_debito = 0;

	      res = this.executasql.executarConsulta (sql);
	      while (res.next ()) {

	        Lancamento_ContabilED edLC = new Lancamento_ContabilED ();

	        NM_Complemento_Cheque = "Compensa��o - Ch Nr. " + res.getString ("nr_documento_lote");

	        edLC.setOID_Compromisso (0);
	        edLC.setOID_Nota_Fiscal ("");
	        edLC.setOID_Conhecimento ("");
	        edLC.setOID_Solicitacao ("");
	        edLC.setOID_Acerto ("0");
	        edLC.setOID_Movimento_Servico ("");
	        edLC.setOID_Ordem_Frete ("");
	        edLC.setOID_Caixa ("");
	        edLC.setOid_movimento_duplicata ("");
	        edLC.setDM_Stamp (" ");
	        edLC.setOID_Movimento_Conta_Corrente(oid_Movimento_Conta_Corrente);

	        edLC.setOID_Lote_Pagamento (res.getLong ("OID_Lote_Pagamento"));
	        edLC.setOID_Lote_Pagamento_Compensacao (res.getLong ("OID_Lote_Pagamento"));
	        edLC.setOID_Lote_Compromisso ("");
	        edLC.setDT_Stamp (Data.getDataDMY ());
	        edLC.setTx_Chave_Origem (String.valueOf (oid_Lote_Pagamento));
	        edLC.setOID_Origem (108);

            edLC.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao ());
            edLC.setVL_Lancamento (res.getDouble ("vl_lote_pagamento"));
            edLC.setDT_Lancamento(res.getString("dt_compensacao"));

            edLC.setOID_Conta (res.getLong("OID_conta_transitoria_compensacao_cheque"));
	        sql = "Select OID_Historico from contas where oid_conta = " + res.getLong("OID_conta_transitoria_compensacao_cheque");
	        rf = this.executasql.executarConsulta (sql);
	        while (rf.next ()) {
	          edLC.setCD_Historico(rf.getLong("OID_Historico"));
	        }
     	    edLC.setDM_Acao("D");
            edLC.setNM_Complementar(NM_Complemento_Cheque);
            vl_debito = vl_debito + edLC.getVL_Lancamento();

            edLC.setNR_Lote (this.inclui_Lancamento_Contabil (edLC));

    	    edLC.setOID_Conta(res.getLong("OID_Conta"));
    	    edLC.setCD_Historico(res.getLong("OID_Historico"));
       	    edLC.setDM_Acao("C");
            vl_credito = vl_credito + edLC.getVL_Lancamento();
            this.inclui_Lancamento_Contabil(edLC);

	        if (Valor.round(vl_credito,2) > Valor.round(vl_debito,2)){
	    	  // .println("Credito maior que Debito");
	    	  // .println("Lote Credito "+vl_credito);
	    	  // .println("Lote Debito "+vl_debito);
	    	  // .println("Lote "+res.getString("oid_lote_pagamento"));
	    	  Excecoes excecoes = new Excecoes ();
		      excecoes.setMetodo ("Credito maior que Debito");
		      throw excecoes;
	        }else if (Valor.round(vl_credito,2) < Valor.round(vl_debito,2)){
	    	  // .println("Credito menor que Debito");
	    	  // .println("Lote Credito "+vl_credito);
	    	  // .println("Lote Debito "+vl_debito);
	    	  // .println("Lote "+res.getString("oid_lote_pagamento"));
	    	  Excecoes excecoes = new Excecoes ();
		      excecoes.setMetodo ("Credito menor que Debito");
		      throw excecoes;
	        }
	    }

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      exc.printStackTrace ();
	      excecoes.setMetodo ("inclui lancamentos compensacao cheques ");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

}
