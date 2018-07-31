package com.master.bd;

import java.sql.*;

import com.master.ed.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.*;
import com.master.util.bd.Transacao;

public class Importacao_ContabilBD extends Transacao{

  private ExecutaSQL executasql;
  private Base_EmpresaED base_EmpresaED = new Base_EmpresaED ();
  Utilitaria util = new Utilitaria (executasql);
  long oid_Origem = 0;
  String NM_Complemento_sql = "";
  Parametro_FixoED parametro_FixoED = Parametro_FixoED.getInstancia ();
  Parametro_ContaED paramConta = new Parametro_ContaED ();
  ContaED Conta = new ContaED ();

  public Importacao_ContabilBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  FormataDataBean dataFormatada = new FormataDataBean ();

  public void inclui (Importacao_ContabilED ed) throws Excecoes {

    //////ACERTOS CONTAS
    if (ed.getDM_Tipo_Lancamento ().equals ("1")) {
      NM_Complemento_sql = " and oid_acerto is not null " +
          " and oid_acerto <> 0 ";
      oid_Origem = 6;
      this.exclui_Origem (ed);
      this.contabiliza_Acerto (ed);
    }

    //////CONHECIMENTOS
    if (ed.getDM_Tipo_Lancamento ().equals ("2") || ed.getDM_Tipo_Lancamento ().equals ("T")) { ///
      NM_Complemento_sql = " and oid_conhecimento is not null " +
          " and oid_conhecimento <> '' " +
          " and oid_conhecimento <> 'null'";
      oid_Origem = 2;
      this.exclui_Origem (ed);
      this.contabiliza_Conhecimento (ed);
    }

    //////ORDEM FRETE
    if (ed.getDM_Tipo_Lancamento ().equals ("3") || ed.getDM_Tipo_Lancamento ().equals ("T")) { ///
      NM_Complemento_sql = " and oid_ordem_frete is not null " +
          " and oid_ordem_frete <> '' " +
          " and oid_ordem_frete <> 'null'";
      oid_Origem = 4;
      this.exclui_Origem (ed);
      this.contabiliza_Ordem_Frete (ed);
    }

    //////ORDEM FRETE ADIANTAMENTO
    if (ed.getDM_Tipo_Lancamento ().equals ("15")) { ///

	  String sql = " SELECT OID_Ordem_Frete FROM Ordens_Fretes  " +
	  			" WHERE Ordens_Fretes.DT_Emissao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
	  			" AND Ordens_Fretes.DT_Emissao <= '" + ed.getDT_Lancamento_Final () + "'" +
	  			" AND Ordens_Fretes.DM_Impresso = 'S'" +
	  			" AND Ordens_Fretes.dm_frete = 'A'";

      NM_Complemento_sql = " and oid_ordem_frete in ("+ sql + ")";
      oid_Origem = 109;
      this.exclui_Origem (ed);
      this.contabiliza_Ordem_Frete_Adto (ed);
    }

    //////LOTES PAGAMENTOS
    if (ed.getDM_Tipo_Lancamento ().equals ("4") || ed.getDM_Tipo_Lancamento ().equals ("T")) {
      NM_Complemento_sql = " and oid_lote_pagamento is not null " +
          " and oid_lote_pagamento <> 0 " +
          " and (oid_lote_pagamento_compensacao = 0 or oid_lote_pagamento_compensacao is null) ";
      oid_Origem = 3;
      this.exclui_Origem (ed);
      this.contabiliza_Lote_Pagamento (ed);
    }

    //////COMPENSAÇÃO LOTES PAGAMENTOS
    if (ed.getDM_Tipo_Lancamento ().equals ("14") || ed.getDM_Tipo_Lancamento ().equals ("T")) {
      NM_Complemento_sql = " and oid_lote_pagamento is not null " +
          " and oid_lote_pagamento <> 0 "+
      	  " and oid_lote_pagamento_compensacao is not null "+
      	  " and oid_lote_pagamento_compensacao <> 0 ";
      oid_Origem = 108;
      this.exclui_Origem (ed);
      this.contabiliza_Compensacao_Lote_Pagamento (ed);
    }

    //////MOVIMENTO DUPLICATA
    if (ed.getDM_Tipo_Lancamento ().equals ("5")) {
      NM_Complemento_sql = " and oid_movimento_duplicata is not null " +
          " and oid_movimento_duplicata <> '' " +
          " and oid_movimento_duplicata <> 'null'";
      oid_Origem = 8;
      this.exclui_Origem (ed);
      this.contabiliza_Movimento_Duplicata (ed);
    }

    //////NOTAS FISCAIS
    if (ed.getDM_Tipo_Lancamento ().equals ("6") || ed.getDM_Tipo_Lancamento ().equals ("T")) {
      NM_Complemento_sql = " and oid_nota_fiscal is not null " +
          " and oid_nota_fiscal <> '' " +
          " and oid_nota_fiscal <> 'null'";
      oid_Origem = 7;
      this.exclui_Origem (ed);
      this.contabiliza_Notas_Fiscais (ed);
    }

    //////NOTAS FISCAIS SERVIÇOS
    if (ed.getDM_Tipo_Lancamento ().equals ("16") || ed.getDM_Tipo_Lancamento ().equals ("T")) {
      NM_Complemento_sql = " and oid_nota_fiscal_servico is not null " +
          " and oid_nota_fiscal_servico <> '' " +
          " and oid_nota_fiscal_servico <> 'null'";
      oid_Origem = 109;
      this.exclui_Origem (ed);
      this.contabiliza_Notas_Fiscais_Servicos (ed);
    }


    //////MOVIMENTO CONTA CORRENTE
    if (ed.getDM_Tipo_Lancamento ().equals ("9") || ed.getDM_Tipo_Lancamento ().equals ("T")) {
      NM_Complemento_sql = " and oid_movimento_conta_corrente != 0 " +
          " and dm_stamp = 'X'";
      oid_Origem = 10;
      this.exclui_Origem (ed);
      this.contabiliza_Movimento_Conta_Corrente (ed);
    }

    //////ZERA RESULTADO
    if (ed.getDM_Tipo_Lancamento ().equals ("11")) {
      NM_Complemento_sql = " and oid_conta_zera_resultado > 0 " +
          " and dm_stamp = 'X'";
      oid_Origem = 11;
      this.exclui_Origem (ed);

      this.zera_Conta_Resultado (ed);
    }

    //////DUPLICATA
    if (ed.getDM_Tipo_Lancamento ().equals ("12") || ed.getDM_Tipo_Lancamento ().equals ("T")) {
      NM_Complemento_sql = " and oid_duplicata is not null " +
          " and oid_duplicata > 0 ";
      oid_Origem = 9;
      this.exclui_Origem (ed);
      this.contabiliza_Duplicata (ed);
    }

    //////Vincula Compromissos - Troca de CF nos postos
    if (ed.getDM_Tipo_Lancamento ().equals ("13") || ed.getDM_Tipo_Lancamento ().equals ("T")) {
      NM_Complemento_sql = " and oid_fatura_compromisso is not null " +
          " and oid_fatura_compromisso > 0 ";
      oid_Origem = 107;
      this.exclui_Origem (ed);
      this.contabiliza_Fatura_Compromisso (ed);
    }

    //////Depreciação Patrimonial
    if (ed.getDM_Tipo_Lancamento ().equals ("18") || ed.getDM_Tipo_Lancamento ().equals ("TTTTTTTTT")) {
      oid_Origem = 110; // Depreciação Patrimonial
      this.exclui_Origem (ed);
      oid_Origem = 111; // Venda Patrimonial
      this.exclui_Origem (ed);
      this.contabiliza_Depreciacao_Patrimonial (ed);
    }
  }


  public void exclui (Importacao_ContabilED ed) throws Excecoes {

    //////ACERTOS CONTAS
    if (ed.getDM_Tipo_Lancamento ().equals ("1")) {
      NM_Complemento_sql = " and oid_acerto is not null " +
          " and oid_acerto <> 0 ";
      oid_Origem = 6;
      this.exclui_Origem (ed);
    }

    //////CONHECIMENTOS
    if (ed.getDM_Tipo_Lancamento ().equals ("2")) { ///
      NM_Complemento_sql = " and oid_conhecimento is not null " +
          " and oid_conhecimento <> '' " +
          " and oid_conhecimento <> 'null'";
      oid_Origem = 2;
      this.exclui_Origem (ed);
    }

    //////ORDEM FRETE
    if (ed.getDM_Tipo_Lancamento ().equals ("3")) { ///
      NM_Complemento_sql = " and oid_ordem_frete is not null " +
          " and oid_ordem_frete <> '' " +
          " and oid_ordem_frete <> 'null'";
      oid_Origem = 4;
      this.exclui_Origem (ed);
    }

    //////LOTES PAGAMENTOS
    if (ed.getDM_Tipo_Lancamento ().equals ("4")) {
      NM_Complemento_sql = " and oid_lote_pagamento is not null " +
          " and oid_lote_pagamento <> 0 ";
      oid_Origem = 3;
      this.exclui_Origem (ed);
    }

    //////MOVIMENTO DUPLICATA
    if (ed.getDM_Tipo_Lancamento ().equals ("5")) {
      NM_Complemento_sql = " and oid_movimento_duplicata is not null " +
          " and oid_movimento_duplicata <> '' " +
          " and oid_movimento_duplicata <> 'null'";
      oid_Origem = 8;
      this.exclui_Origem (ed);
    }

    //////NOTAS FISCAIS
    if (ed.getDM_Tipo_Lancamento ().equals ("6")) {
      NM_Complemento_sql = " and oid_nota_fiscal is not null " +
          " and oid_nota_fiscal <> '' " +
          " and oid_nota_fiscal <> 'null'";
      oid_Origem = 7;
      this.exclui_Origem (ed);
    }

    //////CTO INTER
    if (ed.getDM_Tipo_Lancamento ().equals ("7")) {
      NM_Complemento_sql = " and oid_conhecimento is not null " +
          " and oid_conhecimento <> '' " +
          " and oid_conhecimento <> 'null'" +
          " and dm_stamp = 'C'";
      oid_Origem = 1;
      this.exclui_Origem (ed);
    }

    //////MOVIMENTO CONTA CORRENTE
    if (ed.getDM_Tipo_Lancamento ().equals ("9")) {
      NM_Complemento_sql = " and oid_movimento_conta_corrente != 0 " +
          " and dm_stamp = 'X'";
      oid_Origem = 10;
      this.exclui_Origem (ed);
    }

    //////ZERA RESULTADO
    if (ed.getDM_Tipo_Lancamento ().equals ("11")) {
      NM_Complemento_sql = " and oid_conta_zera_resultado > 0 " +
          " and dm_stamp = 'X'";
      oid_Origem = 11;
      this.exclui_Origem (ed);
    }

  }

  public void exclui_Origem (Importacao_ContabilED ed) throws Excecoes {
    String sql = null;

    try {

      sql = " DELETE from movimentos_contabeis " +
          " WHERE movimentos_contabeis.dt_movimento >= '" + ed.getDT_Lancamento_Inicial () + "'" +
          " AND movimentos_contabeis.dt_movimento <= '" + ed.getDT_Lancamento_Final () + "'" +
          " AND movimentos_contabeis.tx_chave_origem NOT IN('DIGITAÇÃO','IMPORTAÇÃO','DIGITACAO','IMPORTACAO') " +
          " AND movimentos_contabeis.oid_origem = " + oid_Origem;

      executasql.executarUpdate (sql);

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "exclui_Origem(Importacao_ContabilED ed)");
    }
  }


  private void contabiliza_Acerto (Importacao_ContabilED ed) throws Excecoes {
    String sql = null;

    try {

      sql = " UPDATE Acertos " +
          " SET    DM_Contabilizado = 'N'" +
          " WHERE  Acertos.DT_Emissao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
          " AND    Acertos.DT_Emissao <= '" + ed.getDT_Lancamento_Final () + "'";
      // executasql.executarUpdate (sql);

      sql = " SELECT OID_Acerto FROM Acertos  " +
          " WHERE Acertos.DT_Emissao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
          " AND Acertos.DT_Emissao <= '" + ed.getDT_Lancamento_Final () + "'";

      ResultSet resLocal = executasql.executarConsulta (sql);
      Lancamento_ContabilED lancamento_ContabilED = new Lancamento_ContabilED ();
      while (resLocal.next ()) {
        lancamento_ContabilED.setOID_Acerto (resLocal.getString ("OID_Acerto"));
        new Lancamento_ContabilBD (this.executasql).inclui_CTB_Acerto (lancamento_ContabilED);
      }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "contabiliza_Acerto(Importacao_ContabilED ed)");
    }
  }

  private void contabiliza_Conhecimento (Importacao_ContabilED ed) throws Excecoes {
    String sql = null;

    try {

      sql = " UPDATE Conhecimentos  " +
          " SET DM_Contabilizado = 'N'" +
          " WHERE Conhecimentos.DT_Emissao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
          " AND Conhecimentos.DT_Emissao <= '" + ed.getDT_Lancamento_Final () + "'" +
          " AND Conhecimentos.DM_Impresso = 'S'" +
          " AND Conhecimentos.dm_tipo_documento = 'C'" +
          " AND Conhecimentos.dm_Situacao in ('G','F','Q','B')";
      // executasql.executarUpdate (sql);

      sql = " SELECT OID_Conhecimento FROM Conhecimentos  " +
          " WHERE Conhecimentos.DT_Emissao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
          " AND Conhecimentos.DT_Emissao <= '" + ed.getDT_Lancamento_Final () + "'" +
          " AND Conhecimentos.DM_Impresso = 'S'" +
          " AND Conhecimentos.dm_tipo_documento = 'C'" +
          " AND Conhecimentos.dm_Situacao in ('G','F','Q','B')";

      ResultSet resLocal = executasql.executarConsulta (sql);
      ConhecimentoED conhecimentoED = new ConhecimentoED ();
      while (resLocal.next ()) {
        conhecimentoED.setOID_Conhecimento (resLocal.getString ("OID_Conhecimento"));
        new Lancamento_ContabilBD (this.executasql).inclui_CTB_Conhecimento (conhecimentoED);
      }

      sql = " SELECT OID_Conhecimento FROM Conhecimentos  " +
      		" WHERE Conhecimentos.DT_Emissao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
      		" AND Conhecimentos.DT_Emissao <= '" + ed.getDT_Lancamento_Final () + "'" +
      		" AND Conhecimentos.DM_Impresso = 'S'" +
      		" AND Conhecimentos.dm_tipo_documento = 'M'" +
      		" AND Conhecimentos.dm_Situacao in ('G','F','Q','B')" +
      		" AND Conhecimentos.NR_Recibo > 0 ";

      resLocal = executasql.executarConsulta (sql);
      while (resLocal.next ()) {
    	  conhecimentoED.setOID_Conhecimento (resLocal.getString ("OID_Conhecimento"));
    	  new Lancamento_ContabilBD (this.executasql).inclui_CTB_Minuta (conhecimentoED);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "contabiliza_Conhecimento(Importacao_ContabilED ed)");
    }
  }

  private void contabiliza_Ordem_Frete (Importacao_ContabilED ed) throws Excecoes {
    String sql = null;

    try {

      sql = " UPDATE Ordens_Fretes " +
          " SET DM_Contabilizado = 'N'" +
          " WHERE Ordens_Fretes.DT_Emissao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
          " AND Ordens_Fretes.DT_Emissao <= '" + ed.getDT_Lancamento_Final () + "'" +
          " AND Ordens_Fretes.DM_Impresso = 'S'" +
          " AND Ordens_Fretes.dm_frete = 'P'";
      //executasql.executarUpdate (sql);

      sql = " SELECT OID_Ordem_Frete FROM Ordens_Fretes  " +
          " WHERE Ordens_Fretes.DT_Emissao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
          " AND Ordens_Fretes.DT_Emissao <= '" + ed.getDT_Lancamento_Final () + "'" +
          " AND Ordens_Fretes.DM_Impresso = 'S'" +
          " AND Ordens_Fretes.dm_frete = 'P'";

      ResultSet resLocal = executasql.executarConsulta (sql);
      Ordem_FreteED ordem_FreteED = new Ordem_FreteED ();
      while (resLocal.next ()) {
        ordem_FreteED.setOID_Ordem_Frete (resLocal.getString ("OID_Ordem_Frete"));
        new Lancamento_ContabilBD (this.executasql).inclui_CTB_Ordem_Frete (ordem_FreteED);
      }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "contabiliza_Ordem_Frete(Importacao_ContabilED ed)");
    }
  }

  private void contabiliza_Ordem_Frete_Adto (Importacao_ContabilED ed) throws Excecoes {
	    String sql = null;

	    try {

	      sql = " UPDATE Ordens_Fretes " +
	          " SET DM_Contabilizado = 'N'" +
	          " WHERE Ordens_Fretes.DT_Emissao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
	          " AND Ordens_Fretes.DT_Emissao <= '" + ed.getDT_Lancamento_Final () + "'" +
	          " AND Ordens_Fretes.DM_Impresso = 'S'" +
	          " AND Ordens_Fretes.dm_frete = 'A'";
	      // executasql.executarUpdate (sql);

	      sql = " SELECT OID_Ordem_Frete FROM Ordens_Fretes  " +
	          " WHERE Ordens_Fretes.DT_Emissao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
	          " AND Ordens_Fretes.DT_Emissao <= '" + ed.getDT_Lancamento_Final () + "'" +
	          " AND Ordens_Fretes.DM_Impresso = 'S'" +
	          " AND Ordens_Fretes.dm_frete = 'A'";

	      ResultSet resLocal = executasql.executarConsulta (sql);
	      Ordem_FreteED ordem_FreteED = new Ordem_FreteED ();
	      while (resLocal.next ()) {
	        ordem_FreteED.setOID_Ordem_Frete (resLocal.getString ("OID_Ordem_Frete"));
	        new Lancamento_ContabilBD (this.executasql).inclui_CTB_Ordem_Frete_Adto (ordem_FreteED);
	      }

	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "contabiliza_Ordem_Frete(Importacao_ContabilED ed)");
	    }
	  }

  private void contabiliza_Lote_Pagamento (Importacao_ContabilED ed) throws Excecoes {
    String sql = null;

    try {
        this.paramConta = new Parametro_ContaBD (this.executasql).getByRecord (this.paramConta);

    	//VERIFICA SE HA CONTA TRANSITORIA, SENAO A CONTABILIZACAO É PELO CONTA CORRENTE

      this.Conta = this.buscaConta (this.paramConta.getCd_conta_transitoria_compensacao_cheque ());

      if (Conta.getOid_Conta().intValue()>0){
        sql = " UPDATE Lotes_Pagamentos  " +
            " SET DM_Contabilizado = 'N'" +
            " WHERE Lotes_Pagamentos.DT_Emissao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
            " AND Lotes_Pagamentos.DT_Emissao <= '" + ed.getDT_Lancamento_Final () + "'" +
            " AND Lotes_Pagamentos.DM_Situacao = 'L' " +
        	" AND Lotes_Pagamentos.VL_Lote_Pagamento > 0 " ;
        // executasql.executarUpdate (sql);

        sql = "update compromissos set oid_conta = 99 where oid_conta = 98 and length(oid_pessoa) = 14";

        executasql.executarUpdate (sql);

		sql = " UPDATE Lotes_Compromissos  " +
		      " SET VL_Multa_Pagamento = 0 where VL_Multa_Pagamento is null ";
		  executasql.executarUpdate (sql);

	    sql = " UPDATE Lotes_Compromissos  " +
		  		" SET vl_outras_despesas = 0 where vl_outras_despesas is null ";
	    executasql.executarUpdate (sql);

	    sql = " UPDATE Lotes_Compromissos  " +
				" SET vl_desconto = 0 where vl_desconto is null ";
	    executasql.executarUpdate (sql);

	    sql = " UPDATE Lotes_Compromissos  " +
			" SET vl_juros_pagamento = 0 where vl_juros_pagamento is null ";
	    executasql.executarUpdate (sql);

        sql = " SELECT OID_Lote_Pagamento FROM Lotes_Pagamentos  " +
            " WHERE Lotes_Pagamentos.DT_Emissao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
            " AND Lotes_Pagamentos.DT_Emissao <= '" + ed.getDT_Lancamento_Final () + "'" +
            // " AND (Lotes_Pagamentos.dm_Contabilizado <> 'S' " +
            // "	    or Lotes_Pagamentos.dm_Contabilizado is null)" +
            " AND Lotes_Pagamentos.DM_Situacao = 'L' " +
        	" AND Lotes_Pagamentos.VL_Lote_Pagamento > 0 " ;

        ResultSet resLocal = executasql.executarConsulta (sql);
        Lote_PagamentoED lote_PagamentoED = new Lote_PagamentoED ();
        while (resLocal.next ()) {
          lote_PagamentoED.setOid_Lote_Pagamento (new Integer (resLocal.getInt ("oid_Lote_Pagamento")));
          new Lancamento_ContabilBD (this.executasql).inclui_CTB_Lote_Pagamento (lote_PagamentoED);
        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "contabiliza_Lote_Pagamento(Importacao_ContabilED ed)");
    }
  }

  private void contabiliza_Compensacao_Lote_Pagamento (Importacao_ContabilED ed) throws Excecoes {
	    String sql = null;

	    try {
	        sql = " UPDATE Lotes_Pagamentos  " +
	            " SET DM_Contabilizado = 'N'" +
	            " WHERE Lotes_Pagamentos.DT_Compensacao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
	            " AND Lotes_Pagamentos.DT_Compensacao <= '" + ed.getDT_Lancamento_Final () + "'" +
	            " AND Lotes_Pagamentos.DM_Situacao = 'L' " +
	            " AND Lotes_Pagamentos.vl_lote_pagamento > 0 "+
	            " AND Lotes_Pagamentos.oid_tipo_documento in (select oid_tipo_documento from tipos_documentos where dm_compensacao <> 'I') ";
	        // executasql.executarUpdate (sql);

	        sql = " SELECT OID_Lote_Pagamento FROM Lotes_Pagamentos, tipos_documentos " +
	            " WHERE Lotes_Pagamentos.DT_Compensacao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
	            " AND Lotes_Pagamentos.DT_Compensacao <= '" + ed.getDT_Lancamento_Final () + "'" +
	            // " AND (Lotes_Pagamentos.dm_Contabilizado <> 'S' " +
	            // "	    or Lotes_Pagamentos.dm_Contabilizado is null)" +
	            " AND Lotes_Pagamentos.DM_Situacao = 'L' "+
	            " AND Lotes_Pagamentos.vl_lote_pagamento > 0 "+
	            " AND Lotes_Pagamentos.oid_tipo_documento = tipos_documentos.oid_tipo_documento " +
	            " AND tipos_documentos.dm_compensacao <> 'I' ";

	        ResultSet resLocal = executasql.executarConsulta (sql);
	        Lote_PagamentoED lote_PagamentoED = new Lote_PagamentoED ();
	        while (resLocal.next ()) {
	          lote_PagamentoED.setOid_Lote_Pagamento (new Integer (resLocal.getInt ("oid_Lote_Pagamento")));
	          new Lancamento_ContabilBD (this.executasql).inclui_CTB_Compensacao_Lote_Pagamento (lote_PagamentoED);
	        }
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "contabiliza_Compensacao_Lote_Pagamento(Importacao_ContabilED ed)");
	    }
	  }

  private void contabiliza_Movimento_Duplicata (Importacao_ContabilED ed) throws Excecoes {
    String sql = null;

    try {

      sql = " UPDATE Movimentos_Duplicatas " +
          " SET    DM_Contabilizado = 'N'" +
          " WHERE  Movimentos_Duplicatas.dt_movimento >= '" + ed.getDT_Lancamento_Inicial () + "'" +
          " AND    Movimentos_Duplicatas.dt_movimento <= '" + ed.getDT_Lancamento_Final () + "'" ;

          // executasql.executarUpdate (sql);

      sql = " SELECT OID_Movimento_Duplicata " +
          " FROM   Movimentos_Duplicatas  " +
          " WHERE Movimentos_Duplicatas.dt_movimento >= '" + ed.getDT_Lancamento_Inicial () + "'" +
          " AND Movimentos_Duplicatas.dt_movimento <= '" + ed.getDT_Lancamento_Final () + "'";

      ResultSet resLocal = executasql.executarConsulta (sql);
      Movimento_DuplicataED movimento_DuplicataED = new Movimento_DuplicataED ();
      while (resLocal.next ()) {
        movimento_DuplicataED.setOid_Movimento_Duplicata (resLocal.getString ("OID_Movimento_Duplicata"));
        new Lancamento_ContabilBD (this.executasql).inclui_CTB_Movimento_Duplicata (movimento_DuplicataED);
      }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "contabiliza_Lote_Pagamento(Importacao_ContabilED ed)");
    }
  }

  private void contabiliza_Duplicata (Importacao_ContabilED ed) throws Excecoes {
    String sql = null;

    try {

      sql = " SELECT OID_Duplicata, " +
            " vl_Duplicata " +
            " FROM  Duplicatas " +
//            " WHERE DM_Situacao <> 'C' " +
            " WHERE Duplicatas.dt_emissao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
            " AND Duplicatas.dt_emissao <= '" + ed.getDT_Lancamento_Final () + "'";

      ResultSet resLocal = executasql.executarConsulta (sql);
      DuplicataED duplicataED = new DuplicataED ();
      while (resLocal.next ()) {
    	  duplicataED.setOid_Duplicata (new Long(resLocal.getString ("OID_Duplicata")).longValue());
          new Lancamento_ContabilBD (this.executasql).inclui_CTB_Duplicata (duplicataED);
      }

      sql = " SELECT OID_Duplicata, " +
	        " vl_Duplicata " +
	        " FROM  Duplicatas " +
	        " WHERE DM_Situacao = 'C' " +
	        " AND Duplicatas.dt_cancelamento >= '" + ed.getDT_Lancamento_Inicial () + "'" +
	        " AND Duplicatas.dt_cancelamento <= '" + ed.getDT_Lancamento_Final () + "'";

	  resLocal = executasql.executarConsulta (sql);
	  duplicataED = new DuplicataED ();
	  while (resLocal.next ()) {
		  duplicataED.setOid_Duplicata (new Long(resLocal.getString ("OID_Duplicata")).longValue());
	      new Lancamento_ContabilBD (this.executasql).inclui_CTB_Duplicata_Cancelada (duplicataED);
	  }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "contabiliza_Lote_Pagamento(Importacao_ContabilED ed)");
    }
  }

  private void contabiliza_Fatura_Compromisso (Importacao_ContabilED ed) throws Excecoes {
	    String sql = null;

	    try {

	      sql = "update compromissos set oid_conta = 99 where oid_conta = 98 and length(oid_pessoa) = 14";

	      executasql.executarUpdate (sql);

	      sql = " SELECT oid_fatura_compromisso, " +
	          " vl_fatura_compromisso " +
	          " FROM  faturas_compromissos " +
	          " WHERE dm_situacao = 'I' " +
	          " AND vl_fatura_compromisso > 0 " +
	          " AND faturas_compromissos.dt_emissao >= '" + ed.getDT_Lancamento_Inicial () + "'" +
	          " AND faturas_compromissos.dt_emissao <= '" + ed.getDT_Lancamento_Final () + "'";

	      ResultSet resLocal = executasql.executarConsulta (sql);
	      Fatura_CompromissoED fatura_CompromissoED = new Fatura_CompromissoED ();
	      while (resLocal.next ()) {
	    	  fatura_CompromissoED.setOid_Fatura_Compromisso(new Integer(resLocal.getString("oid_fatura_compromisso")));
	          new Lancamento_ContabilBD (this.executasql).inclui_Fatura_Compromisso (fatura_CompromissoED);
	      }

	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "contabiliza_Lote_Pagamento(Importacao_ContabilED ed)");
	    }
	  }

  private void contabiliza_Depreciacao_Patrimonial (Importacao_ContabilED ed) throws Excecoes {

	    try {
	       // Contabilização da Depreciação Mensal
	       new Lancamento_ContabilBD (this.executasql).contabiliza_Depreciacao_Patrimonial (ed);
	 	   // Contabilização da Venda Patrimonial
	 	   new Lancamento_ContabilBD (this.executasql).contabiliza_Venda_Patrimonial (ed);

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

  private void contabiliza_Notas_Fiscais (Importacao_ContabilED ed) throws Excecoes {
    String sql = null;
    ResultSet res = null;

    try {

      sql = " UPDATE Notas_Fiscais_Transacoes SET ";
      sql += " DM_Contabilizado = 'N'";
      sql += " WHERE Notas_Fiscais_Transacoes.dt_entrada >= '" + ed.getDT_Lancamento_Inicial () + "'";
      sql += " AND Notas_Fiscais_Transacoes.dt_entrada <= '" + ed.getDT_Lancamento_Final () + "'";
      sql += " AND Notas_Fiscais_Transacoes.dm_finalizado = 'S'";
      // executasql.executarUpdate (sql);

      sql = " SELECT oid_nota_fiscal FROM Notas_Fiscais_Transacoes  ";
      sql += " WHERE Notas_Fiscais_Transacoes.dt_entrada >= '" + ed.getDT_Lancamento_Inicial () + "'";
      sql += " AND Notas_Fiscais_Transacoes.dt_entrada <= '" + ed.getDT_Lancamento_Final () + "'";
      sql += " AND Notas_Fiscais_Transacoes.dm_finalizado = 'S'";

      res = executasql.executarConsulta (sql);
      Nota_Fiscal_CompraED nota_Fiscal_TransacaoED = new Nota_Fiscal_CompraED ();
      while (res.next ()) {
        nota_Fiscal_TransacaoED.setOid_nota_fiscal (res.getString ("OID_Nota_Fiscal"));
        new Lancamento_ContabilBD (this.executasql).inclui_CTB_Nota_Fiscal (nota_Fiscal_TransacaoED);
      }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "contabiliza_Notas_Fiscais(Importacao_ContabilED ed)");
    }
  }

  private void contabiliza_Notas_Fiscais_Servicos (Importacao_ContabilED ed) throws Excecoes {
	    String sql = null;
	    ResultSet res = null;

	    try {

	      sql = " UPDATE Notas_Fiscais SET ";
	      sql += " DM_Contabilizado = 'N'";
	      sql += " WHERE Notas_Fiscais.dt_emissao >= '" + ed.getDT_Lancamento_Inicial () + "'";
	      sql += " AND Notas_Fiscais.dt_emissao <= '" + ed.getDT_Lancamento_Final () + "'";
	      sql += " AND Notas_Fiscais.dm_impresso = 'S' ";
	      sql += " AND Notas_Fiscais.dm_situacao <> 'C' ";
	      sql += " AND Notas_Fiscais.oid_modelo_nota_fiscal in (select oid_modelo_nota_fiscal from modelos_notas_fiscais where dm_permite_servico = 'S')";

	      // // .println(sql);
	      // executasql.executarUpdate (sql);

	      sql = " SELECT oid_nota_fiscal FROM Notas_Fiscais, modelos_notas_fiscais  ";
	      sql += " WHERE Notas_Fiscais.dt_emissao >= '" + ed.getDT_Lancamento_Inicial () + "'";
	      sql += " AND Notas_Fiscais.dt_emissao <= '" + ed.getDT_Lancamento_Final () + "'";
	      sql += " AND Notas_Fiscais.dm_situacao <> 'C' ";
	      sql += " AND Notas_Fiscais.dm_impresso = 'S' "+
	      		"  AND Notas_Fiscais.oid_modelo_nota_fiscal = modelos_notas_fiscais.oid_modelo_nota_fiscal" +
	      		"  AND modelos_notas_fiscais.dm_permite_servico = 'S'";
	      // // .println(sql);

	      res = executasql.executarConsulta (sql);
	      Nota_Fiscal_CompraED nota_Fiscal_TransacaoED = new Nota_Fiscal_CompraED ();
	      while (res.next ()) {
	        nota_Fiscal_TransacaoED.setOid_nota_fiscal (res.getString ("OID_Nota_Fiscal"));
	        new Lancamento_ContabilBD (this.executasql).inclui_CTB_Nota_Fiscal_Servicos (nota_Fiscal_TransacaoED);
	      }

	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "contabiliza_Notas_Fiscais(Importacao_ContabilED ed)");
	    }
	  }

  private void contabiliza_Movimento_Conta_Corrente (Importacao_ContabilED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " UPDATE Movimentos_Contas_Correntes " +
          " SET    DM_Contabilizado = 'N'" +
          " WHERE  Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente >= '" + ed.getDT_Lancamento_Inicial () + "'" +
          " AND    Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente <= '" + ed.getDT_Lancamento_Final () + "'";

          // executasql.executarUpdate (sql);

      sql = " SELECT Movimentos_Contas_Correntes.oid_Movimento_Conta_Corrente, Movimentos_Contas_Correntes.dt_movimento_conta_corrente, " +
      		" Movimentos_Contas_Correntes.oid_Conta_Corrente "
          + " FROM   Movimentos_Contas_Correntes, Contas_Correntes  "
          + " WHERE  Movimentos_Contas_Correntes.oid_Conta_Corrente =  Contas_Correntes.oid_Conta_Corrente "
          + " AND    Movimentos_Contas_Correntes.vl_lancamento > 0 "
          + " AND    Movimentos_Contas_Correntes.oid_lote_pagamento = 0 "
          + " AND    Movimentos_Contas_Correntes.oid_movimento_duplicata is null "
          + " AND    Movimentos_Contas_Correntes.nr_documento = 'TRANF001' "
          + " AND    Contas_Correntes.DM_Contabilizacao <> 'N' "
          + " AND    Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente >= '" + ed.getDT_Lancamento_Inicial() + "'"
          + " AND    Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente <= '" + ed.getDT_Lancamento_Final ()   + "'";

      ResultSet resLocal = executasql.executarConsulta (sql);
      Movimento_Conta_CorrenteED movimento_Conta_CorrenteED = new Movimento_Conta_CorrenteED ();
      while (resLocal.next ()) {

        movimento_Conta_CorrenteED.setOid_Movimento_Conta_Corrente(resLocal.getLong ("oid_Movimento_Conta_Corrente"));
        movimento_Conta_CorrenteED.setDT_Movimento_Conta_Corrente(resLocal.getString("dt_movimento_conta_corrente"));
        movimento_Conta_CorrenteED.setOid_Conta_Corrente(resLocal.getString("oid_Conta_Corrente"));

        new Lancamento_ContabilBD (this.executasql).inclui_CTB_Movimento_Conta_Corrente_Transferencia (movimento_Conta_CorrenteED);
      }

      sql = " SELECT Movimentos_Contas_Correntes.oid_Movimento_Conta_Corrente, Movimentos_Contas_Correntes.dt_movimento_conta_corrente, " +
    		" Movimentos_Contas_Correntes.oid_Conta_Corrente "
          + " FROM   Movimentos_Contas_Correntes, Contas_Correntes  "
          + " WHERE  Movimentos_Contas_Correntes.oid_Conta_Corrente =  Contas_Correntes.oid_Conta_Corrente "
          + " AND    Movimentos_Contas_Correntes.vl_lancamento > 0 "
          + " AND    Movimentos_Contas_Correntes.oid_lote_pagamento = 0 "
          + " AND    Movimentos_Contas_Correntes.oid_movimento_duplicata is null "
          + " AND    Movimentos_Contas_Correntes.oid_tipo_documento in (12,15,13,41,42,49,23,51,55,58,67,74,71) "
          + " AND    Contas_Correntes.DM_Contabilizacao <> 'N' "
          + " AND    Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente >= '" + ed.getDT_Lancamento_Inicial() + "'"
          + " AND    Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente <= '" + ed.getDT_Lancamento_Final ()   + "'";

      resLocal = executasql.executarConsulta (sql);
      movimento_Conta_CorrenteED = new Movimento_Conta_CorrenteED ();
      while (resLocal.next ()) {
        movimento_Conta_CorrenteED.setOid_Movimento_Conta_Corrente(resLocal.getLong ("oid_Movimento_Conta_Corrente"));
        movimento_Conta_CorrenteED.setDT_Movimento_Conta_Corrente(resLocal.getString("dt_movimento_conta_corrente"));
        movimento_Conta_CorrenteED.setOid_Conta_Corrente(resLocal.getString("oid_Conta_Corrente"));

        new Lancamento_ContabilBD (this.executasql).inclui_CTB_Movimento_Conta_Corrente_Juros_Tarifas (movimento_Conta_CorrenteED);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "contabiliza_Movimento_Conta_Corrente(Importacao_ContabilED ed)");
    }

  }

  private void zera_Conta_Resultado (Importacao_ContabilED ed) throws Excecoes {

    Lancamento_ContabilED lanc_contaED = new Lancamento_ContabilED ();
    lanc_contaED.setOID_Unidade_Contabil (ed.getOid_Unidade ());
    lanc_contaED.setDT_Lancamento_Inicial (ed.getDT_Lancamento_Inicial ());
    lanc_contaED.setDT_Lancamento_Final (ed.getDT_Lancamento_Final ());

    new Lancamento_ContabilBD (this.executasql).zera_Conta_Resultado (lanc_contaED);

  }


  private ContaED buscaConta (String cd_Conta) throws Excecoes {
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

  public void gera_Contabilizacao_Total () throws Excecoes {
    String sql = null;
    Importacao_ContabilED ed = new Importacao_ContabilED();
    try {
      this.inicioTransacao();
      sql = " SELECT periodos_abertos.dt_inicial "
          + " FROM   periodos_abertos  "
          + " order by periodos_abertos.dt_inicial desc limit 1 ";

      ResultSet resLocal = executasql.executarConsulta (sql);
      while (resLocal.next ()) {
          ed.setDT_Lancamento_Inicial(FormataData.formataDataBT (resLocal.getDate("dt_inicial")));
      }
      ed.setDT_Lancamento_Final(Data.getSomaDiaData(Data.getDataDMY(), -1));
      ed.setDM_Tipo_Lancamento("T");

      this.inclui(ed);

    }
    catch (Exception e) {
    	e.printStackTrace();
      this.abortaTransacao();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "exclui_Origem(Importacao_ContabilED ed)");
	} finally {
	  this.fimTransacao(false);
	}
  }


}