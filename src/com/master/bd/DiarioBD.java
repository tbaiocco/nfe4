package com.master.bd;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.Auxiliar1ED;
import com.master.ed.CompromissoED;
import com.master.ed.DiarioED;
import com.master.rl.DiarioRL;
import com.master.rn.Auxiliar1RN;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class DiarioBD {

  private ExecutaSQL executasql;
  DecimalFormat dec = new DecimalFormat ("000000000000");
  Parametro_FixoED parametro_FixoED = Parametro_FixoED.getInstancia ();
  BloquetoBD bloquetoBD = new BloquetoBD (executasql);
  private FormataDataBean dataFormatada = new FormataDataBean ();
  Utilitaria util = new Utilitaria (executasql);

  public DiarioBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  public void geraDiario_Razao_Clientes_FAT(DiarioED edComp , HttpServletResponse response) throws
  Excecoes {

		String sql = null , sqlBusca = null;
		ArrayList list = new ArrayList ();
		ArrayList toReport = new ArrayList ();
		DiarioED ed = (DiarioED) edComp;
		double saldo_anterior = 0, vl_cot_pgto = 0, vl_deduzibles = 0;
		double saldo = 0, variacao = 0;
		String cliente = "" , clieAux = "", dt_variacao = "";

		try {
			//int delAux = this.executasql.executarUpdate("delete from auxiliar1");

		  //long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).toString ().substring (6 , 12)).longValue ());

		  sql = "select Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.vl_duplicata, Duplicatas.dt_vencimento, Duplicatas.oid_duplicata, " +
		      " Duplicatas.oid_pessoa, Pessoas.nm_razao_social, Duplicatas.oid_moeda from Duplicatas, pessoas where " +
		      " Duplicatas.oid_pessoa = Pessoas.oid_pessoa ";

		  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
		      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
		      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
		    sql += " and Duplicatas.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
		    sql += " and Duplicatas.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
		        "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
		    sql += " and Duplicatas.dt_Emissao <= '" + ed.getData_Emissao_Final () +
		        "'";
		  }
		  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
		      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
		      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
		    sql += " and Duplicatas.nr_documento = '" + ed.getNr_Proximo_Numero () + "'";
		  }
		  if (ed.getDM_Relatorio ().equals ("R")) {
		    sql += " order by Duplicatas.oid_pessoa ";
		  }
		  else {
		    sql += " order by Duplicatas.dt_Emissao ";
		  }

		  Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();

		  ResultSet rs = null;
//System.out.println("DIARIO FAT >" +sql);
		  ResultSet res = this.executasql.executarConsulta (sql.toString ());

		  while (res.next ()) {

			  DiarioED edDiario = new DiarioED();

		      cliente = res.getString ("oid_pessoa");

		      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_Emissao")));
		      edDiario.setNm_Tipo_Documento("EMISSAO");
		      edDiario.setNM_Razao_Social(res.getString ("nm_razao_social"));
		      edDiario.setNr_Duplicata(res.getString("nr_duplicata"));
		      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("dt_vencimento")));
		      if (JavaUtil.doValida(res.getString("oid_moeda")) && res.getInt("oid_moeda") != parametro_FixoED.getOID_Moeda_Padrao()) {
		    	  edDiario.setVL_Debito(convertToReal(res.getString("dt_Emissao"), res.getDouble("vl_duplicata") , res.getInt("oid_moeda")));
		      } else edDiario.setVL_Debito(res.getDouble("vl_duplicata"));
		      edDiario.setVL_Credito(0.0);
		      edDiario.setOid_Pessoa(cliente);
		      edDiario.setNr_Remessa("0");

		      list.add(edDiario);
		      clieAux = cliente;

		    //Variacao Cambial da duplicata
//		    sqlBusca = " SELECT vl_variacao_cambial, vl_frete, vl_cotacao_calculo_final, vl_cotacao_calculo_inicial, dt_periodo_final " +
//		    		   " FROM Variacoes_Cambiais " +
//		    		   " WHERE oid_Duplicata = '" + res.getString ("oid_duplicata") + "'";
//		    if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
//				!String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
//				!String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
//		        sqlBusca += " and dt_periodo_inicial >= '" + ed.getData_Emissao_Inicial () + "'";
//		    }
//		    if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
//			    !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
//			    !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
//		        sqlBusca += " and dt_periodo_final <= '" + ed.getData_Emissao_Final () + "'";
//		    }
//		    variacao = 0;
//		    rs = null;
//		    rs = this.executasql.executarConsulta (sqlBusca);
//		    while (rs.next()) {
//		        variacao = rs.getDouble("vl_variacao_cambial");
//		        dt_variacao = dataFormatada.getDT_FormataData(rs.getString("dt_periodo_final"));
//		        if(variacao < 0 || variacao > 0){
//		        	edDiario.setVL_Debito(variacao);
//		        	edDiario.setVL_Credito(0.0);
//		        	edDiario.setDt_Emissao(dt_variacao);
//		        	edDiario.setNm_Tipo_Documento("VARIA��O CAMBIAL");
//		        	list.add(edDiario);
//			    }
//		    }

		  }

		  //Movimentos
		  sql = "select Movimentos_Duplicatas.dt_movimento, Movimentos_Duplicatas.vl_debito, Movimentos_Duplicatas.vl_credito, Movimentos_Duplicatas.vl_cotacao_pagamento, " +
		      " Duplicatas.nr_duplicata, Duplicatas.dt_vencimento, tipos_instrucoes.nm_tipo_instrucao, Duplicatas.oid_duplicata, Movimentos_Duplicatas.vl_variacao_cambial, " +
		      " Duplicatas.dt_emissao, Duplicatas.vl_duplicata, tipos_instrucoes.oid_tipo_instrucao, Movimentos_Duplicatas.nr_sequencia_duplicata, " +
		      " Duplicatas.oid_pessoa, Pessoas.nm_razao_social, Duplicatas.oid_moeda from Movimentos_Duplicatas, Duplicatas, pessoas, tipos_instrucoes" +
		      " where movimentos_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata " +
		      " AND Duplicatas.oid_pessoa = Pessoas.oid_pessoa " +
		      " AND movimentos_Duplicatas.oid_tipo_instrucao = tipos_instrucoes.oid_tipo_instrucao ";

		  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
		      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
		      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
		    sql += " and Duplicatas.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
		    sql += " and movimentos_Duplicatas.dt_movimento >= '" +
		        ed.getData_Emissao_Inicial () + "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
		    sql += " and movimentos_Duplicatas.dt_movimento <= '" +
		        ed.getData_Emissao_Final () + "'";
		  }
		  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
		      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
		      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
		    sql += " and Duplicatas.nr_documento = '" + ed.getNr_Proximo_Numero () + "'";
		  }
		  if (ed.getDM_Relatorio ().equals ("R")) {
		    sql += " order by Duplicatas.oid_pessoa, Movimentos_Duplicatas.nr_sequencia_duplicata desc ";
		  }
		  else {
		    sql += " order by Movimentos_Duplicatas.dt_movimento, Movimentos_Duplicatas.nr_sequencia_duplicata desc ";
		  }
		  res = null;
//System.out.println("DIARIO FAT >" +sql);
		  res = this.executasql.executarConsulta (sql.toString ());

		  while (res.next ()) {

			  variacao = 0;
			  double debito = 0;
			  double credito = 0;

			  DiarioED edDiario = new DiarioED();

		      cliente = res.getString ("oid_pessoa");

		      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_movimento")));
		      edDiario.setNm_Tipo_Documento(res.getString("nm_tipo_instrucao").trim());
		      edDiario.setNM_Razao_Social(res.getString ("nm_razao_social"));
		      edDiario.setNr_Duplicata(res.getString("nr_duplicata"));
		      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("dt_vencimento")));
		      if(res.getString("nr_sequencia_duplicata").equals("9"))
			    	vl_cot_pgto = res.getDouble ("vl_cotacao_pagamento");
		      if (JavaUtil.doValida (res.getString ("oid_moeda"))) {
			    	if(vl_cot_pgto > 0){
			    		debito = res.getDouble("vl_debito") * vl_cot_pgto;
		    			credito = res.getDouble("vl_credito") * vl_cot_pgto;
			    	} else {
			    		debito = convertToReal(res.getString("dt_movimento") , res.getDouble("vl_debito") , res.getInt("oid_moeda"));
			    		credito = convertToReal(res.getString("dt_movimento") , res.getDouble("vl_credito") , res.getInt("oid_moeda"));
			    	}
			    }
			    else {
			      debito = res.getDouble("vl_debito");
			      credito = res.getDouble("vl_credito");
			    }
		      edDiario.setVL_Debito(debito);
		      edDiario.setVL_Credito(credito);
		      edDiario.setOid_Pessoa(cliente);
		      edDiario.setNr_Remessa(res.getString("nr_sequencia_duplicata"));
		      list.add(edDiario);

		      if(res.getDouble("vl_variacao_cambial")!=0){
		    	  edDiario = new DiarioED();
			      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_movimento")));
			      edDiario.setNm_Tipo_Documento("VARIACAO CAMBIAL TOTAL");
			      edDiario.setNM_Razao_Social(res.getString ("nm_razao_social"));
			      edDiario.setNr_Duplicata(res.getString("nr_duplicata"));
			      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("dt_vencimento")));
			      edDiario.setVL_Debito(res.getDouble("vl_variacao_cambial"));
			      edDiario.setOid_Pessoa(cliente);
			      edDiario.setNr_Remessa("0");
			      list.add(edDiario);
		      }

		      clieAux = cliente;

		  }
		  //Fim Movimentos

		  Collections.sort (list , new Comparator () {
	          public int compare (Object o1 , Object o2) {
	            DiarioED ed1 = (DiarioED) o1;
	            DiarioED ed2 = (DiarioED) o2;
	            return ed1.getDt_Emissao().compareTo(ed2.getDt_Emissao());
	          }
	        });

		  vl_deduzibles = 0;
		  String fatura_Aux = "";

		  if (ed.getDM_Relatorio ().equals ("R")) {
			  Collections.sort (list , new Comparator () {
		          public int compare (Object o1 , Object o2) {
		            DiarioED ed1 = (DiarioED) o1;
		            DiarioED ed2 = (DiarioED) o2;
		            return ed1.getNM_Razao_Social().compareTo(ed2.getNM_Razao_Social());
		          }
		        });
		  }

		  clieAux = "";
		  saldo_anterior = 0;
		  for(int t=0;t<list.size();t++){
			  DiarioED edLista = (DiarioED)list.get(t);
			  cliente = edLista.getOid_Pessoa();

			  if(edLista.getNr_Remessa().equals("6"))
			    	vl_deduzibles = edLista.getVL_Credito();
			  else if(!edLista.getNr_Duplicata().equals(fatura_Aux))
			    	vl_deduzibles = 0;

			  if (ed.getDM_Relatorio ().equals ("R")) {
				  if (!cliente.equals (clieAux)) {
					  saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
					  edLista.setVL_Saldo_Inicial (saldo_anterior);
					  saldo = saldo_anterior;
				  }
			  }

			  if(vl_deduzibles > 0 && edLista.getNr_Remessa().equals("9")){
			    	edLista.setVL_Credito(edLista.getVL_Credito() - vl_deduzibles);
			  } else {
			    	edLista.setVL_Credito(edLista.getVL_Credito());
			  }

			  saldo = saldo - edLista.getVL_Credito() + edLista.getVL_Debito();
			  edLista.setVL_Saldo (saldo);
			  toReport.add(edLista);
			  clieAux = cliente;
			  fatura_Aux = edLista.getNr_Duplicata();
		  }

		  util.closeResultset(res);
		  util.closeResultset(rs);

		  new DiarioRL().geraDiario_Razao_Clientes(toReport , ed , response);

		}
		catch (Exception e) {
		  e.printStackTrace ();
		  throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
		                      "geraDiario_Razao_Clientes_FAT()");
		}

	}

  public void geraDiario_Razao_Clientes_CRT(DiarioED edComp , HttpServletResponse response) throws
  Excecoes {

		String sql = null , sqlBusca = null;
		ArrayList list = new ArrayList ();
		ArrayList toReport = new ArrayList ();
		DiarioED ed = (DiarioED) edComp;
		double saldo_anterior = 0;
		double saldo = 0, variacao = 0;
		String cliente = "" , clieAux = "";

		try {
			//int delAux = this.executasql.executarUpdate("delete from auxiliar1");

		  //long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).toString ().substring (6 , 12)).longValue ());

		  // CRT
		  sql = "select Conhecimentos.dt_emissao, Conhecimentos.nr_conhecimento, Conhecimentos.oid_conhecimento, " +
		  		" (Conhecimentos.vl_gasto_remetente1 + Conhecimentos.vl_gasto_remetente2 + Conhecimentos.vl_gasto_remetente3 + Conhecimentos.vl_gasto_remetente4 +" +
		  		" Conhecimentos.vl_gasto_destinatario1 + Conhecimentos.vl_gasto_destinatario2 + Conhecimentos.vl_gasto_destinatario3 + Conhecimentos.vl_gasto_destinatario4) as vl_total_frete, " +
		  		" fat.vl_faturar, Conhecimentos.oid_pessoa_cotacao, Pessoas.nm_razao_social " +
		  		" from Conhecimentos_Internacionais Conhecimentos, pessoas, conhecimentos_faturamentos fat " +
		  		" where fat.oid_conhecimento = Conhecimentos.oid_conhecimento and " +
		  		" (Conhecimentos.dm_situacao = 'L' or Conhecimentos.dm_situacao = 'F') " +
		  		" and Conhecimentos.oid_pessoa_cotacao = Pessoas.oid_pessoa ";

		  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
		      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
		      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
		    sql += " and Conhecimentos.Oid_Pessoa_cotacao = '" + ed.getOid_Pessoa () + "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
		    sql += " and Conhecimentos.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
		        "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
		    sql += " and Conhecimentos.dt_Emissao <= '" + ed.getData_Emissao_Final () +
		        "'";
		  }
		  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
		      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
		      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
		    sql += " and Conhecimentos.nr_Conhecimento = '" + ed.getNr_Proximo_Numero () + "'";
		  }
		  if(ed.getDM_Relatorio().equals("R")){
		      sql += " order by oid_pessoa_cotacao, Conhecimentos.nr_conhecimento ";
		  } else {
		      sql += " order by dt_Emissao, Conhecimentos.nr_conhecimento ";
		  }

		  Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();
		  ResultSet rs = null;
//System.out.println("DR CRT>"+sql);
		  ResultSet res = this.executasql.executarConsulta (sql.toString());

		  double vl_faturar = 0;
		  while (res.next ()) {
			  DiarioED edDiario = new DiarioED();

			  cliente = res.getString ("oid_pessoa_cotacao");

			  vl_faturar = res.getDouble("vl_faturar");

			  edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString("dt_Emissao")));
			  edDiario.setNm_Tipo_Documento("EMISSAO INTERNACIONAL");
			  edDiario.setNM_Razao_Social(res.getString("nm_razao_social"));
			  edDiario.setNr_Duplicata(res.getString("nr_conhecimento"));
			  edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData(res.getString("dt_Emissao")));
			  edDiario.setVL_Debito(convertToReal(res.getString("dt_Emissao"), vl_faturar, parametro_FixoED.getOID_Moeda_Dollar()));
			  edDiario.setVL_Credito(0.0);
			  edDiario.setOid_Nota_Fiscal(res.getString("oid_conhecimento"));
			  edDiario.setOid_Pessoa(cliente);

			  clieAux = cliente;
			  list.add(edDiario);
		  }

		  // Faturamento do CRT
		  sql = "select Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.oid_duplicata, Conhecimentos_Internacionais.dt_emissao as emissao_CRT, " +
		  		" Conhecimentos_Internacionais.oid_Conhecimento, Conhecimentos_Internacionais.nr_Conhecimento, " +
		  		" (Conhecimentos_Internacionais.vl_gasto_remetente1 + Conhecimentos_Internacionais.vl_gasto_remetente2 + " +
		  		" Conhecimentos_Internacionais.vl_gasto_remetente3 + Conhecimentos_Internacionais.vl_gasto_remetente4 +" +
		  		" Conhecimentos_Internacionais.vl_gasto_destinatario1 + Conhecimentos_Internacionais.vl_gasto_destinatario2 + " +
		  		" Conhecimentos_Internacionais.vl_gasto_destinatario3 + Conhecimentos_Internacionais.vl_gasto_destinatario4) as vl_total_frete, " +
		  		" fat.vl_faturar, Conhecimentos_Internacionais.oid_pessoa_cotacao, Pessoas.nm_razao_social " +
		        " from Duplicatas, Origens_Duplicatas, Conhecimentos_Internacionais, pessoas, conhecimentos_faturamentos fat " +
		        " where Duplicatas.oid_duplicata = Origens_Duplicatas.oid_duplicata " +
		        " AND fat.oid_conhecimento = Conhecimentos_Internacionais.oid_conhecimento " +
		        " AND Origens_Duplicatas.oid_Conhecimento = Conhecimentos_Internacionais.oid_Conhecimento " +
		        " AND Origens_Duplicatas.dm_Situacao != 'E' " +
		        " and Conhecimentos_Internacionais.oid_pessoa_cotacao = Pessoas.oid_pessoa";

		  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
		      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
		      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
		    sql += " and Duplicatas.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
		    sql += " and Duplicatas.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
		        "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
		    sql += " and Duplicatas.dt_Emissao <= '" + ed.getData_Emissao_Final () +
		        "'";
		  }
		  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
		      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
		      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
		    sql += " and Conhecimentos_Internacionais.nr_Conhecimento = '" + ed.getNr_Proximo_Numero () + "'";
		  }
		  if (ed.getDM_Relatorio ().equals ("R")) {
		    sql += " order by Duplicatas.oid_pessoa, Conhecimentos_Internacionais.nr_conhecimento ";
		  }
		  else {
		    sql += " order by dt_Emissao, Conhecimentos_Internacionais.nr_conhecimento ";
		  }
//		System.out.println("FAT CRT > "+sql);
		  res = this.executasql.executarConsulta (sql.toString ());

		  vl_faturar = 0;
		  while (res.next ()) {
		      variacao = 0;
		      DiarioED edDiario = new DiarioED();

		      cliente = res.getString ("oid_pessoa_cotacao");
		      vl_faturar = res.getDouble("vl_faturar");

		      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_Emissao")));
		      edDiario.setNm_Tipo_Documento("FATURAMENTO");
		      edDiario.setNM_Razao_Social(res.getString ("nm_razao_social"));
		      edDiario.setNr_Duplicata(res.getString("nr_Conhecimento"));
		      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("emissao_CRT")));
		      edDiario.setVL_Debito(0.0);
		      edDiario.setVL_Credito (convertToReal(res.getString("dt_Emissao"), vl_faturar, parametro_FixoED.getOID_Moeda_Dollar()));
		      edDiario.setOid_Pessoa(cliente);

		      list.add (edDiario);

		      variacao = convertToReal(res.getString("dt_emissao"), vl_faturar, parametro_FixoED.getOID_Moeda_Dollar()) - convertToReal(res.getString("emissao_CRT"), vl_faturar, parametro_FixoED.getOID_Moeda_Dollar());

		      if(variacao < 0 || variacao > 0){
		    	  edDiario = new DiarioED();
			      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_Emissao")));
			      edDiario.setNM_Razao_Social(res.getString ("nm_razao_social"));
			      edDiario.setNr_Duplicata(res.getString("nr_Conhecimento"));
			      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("emissao_CRT")));
			      edDiario.setOid_Pessoa(cliente);
		    	  edDiario.setVL_Debito(variacao);
		    	  edDiario.setVL_Credito(0.0);
		    	  edDiario.setNm_Tipo_Documento("VARIA��O CAMBIAL");
		    	  list.add(edDiario);
		      }

		      clieAux = cliente;

		  }

		  if (ed.getDM_Relatorio ().equals ("R")) {
			  Collections.sort (list , new Comparator () {
		          public int compare (Object o1 , Object o2) {
		            DiarioED ed1 = (DiarioED) o1;
		            DiarioED ed2 = (DiarioED) o2;
		            return ed1.getNM_Razao_Social().compareTo(ed2.getNM_Razao_Social());
		          }
		        });

			  clieAux = "";
			  saldo_anterior = 0;
			  for(int t=0;t<list.size();t++){
				  DiarioED edLista = (DiarioED)list.get(t);
				  cliente = edLista.getOid_Pessoa();
				  if (!cliente.equals (clieAux)) {
					  saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
					  edLista.setVL_Saldo_Inicial (saldo_anterior);
				      saldo = saldo_anterior;
				  }
				  saldo = saldo - edLista.getVL_Credito() + edLista.getVL_Debito();
				  edLista.setVL_Saldo (saldo);
				  toReport.add(edLista);
				  clieAux = cliente;
			  }
			  list = new ArrayList(toReport);
		  }

//		  sql = " select * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
//		  sql += " order by auxiliar1.nm_classifica3 , auxiliar1.nm_classifica1 , auxiliar1.nm_classifica5  ";
//		  res = null;
//		  res = this.executasql.executarConsulta (sql.toString ());
//
//		  while (res.next ()) {
//		    DiarioED edDiario = new DiarioED();
//
//		    cliente = res.getString ("nm_classifica3");
//		    if (ed.getDM_Relatorio ().equals ("R")) {
//		      if (!cliente.equals (clieAux)) {
//		    	  saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
//		        //saldo_anterior = res.getDouble ("vl_classifica3");
//		        edDiario.setVL_Saldo_Inicial (saldo_anterior);
//		        saldo = saldo_anterior;
//		      }
//		    }
//
//		    edDiario.setDt_Emissao (dataFormatada.getDT_FormataData (res.getString ("nm_classifica1")));
//		    edDiario.setNm_Tipo_Documento (res.getString ("nm_classifica2"));
//		    edDiario.setNM_Razao_Social(res.getString ("nm_classifica3"));
//		    edDiario.setNr_Duplicata(res.getString("nm_classifica4"));
//		    edDiario.setDt_Vencimento (dataFormatada.getDT_FormataData (res.getString ("nm_classifica5")));
//		    edDiario.setVL_Debito (res.getDouble ("vl_classifica1"));
//		    edDiario.setVL_Credito (res.getDouble ("vl_classifica2"));
////System.out.println(edDiario.getNr_Duplicata() + " - " + edDiario.getNM_Razao_Social());
//		    saldo = saldo - res.getDouble ("vl_classifica2") + res.getDouble ("vl_classifica1");
//		    edDiario.setVL_Saldo (saldo);
//
//		    list.add (edDiario);
//
//		    clieAux = cliente;
//
//		  }
//
//		  sql = "select oid_auxiliar1 from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
//		  res = this.executasql.executarConsulta (sql.toString ());
//		  Auxiliar1RN AuxRN = new Auxiliar1RN ();
//		  while (res.next ()) {
//		    Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
//		    edAuxiliar1.setOID_auxiliar1 (new Integer (res.getInt ("oid_auxiliar1")));
//		    AuxRN.deleta (edAuxiliar1);
//		  }

		  util.closeResultset(res);
		  util.closeResultset(rs);

		  new DiarioRL().geraDiario_Razao_Clientes(list , ed , response);

		}
		catch (Exception e) {
		  e.printStackTrace ();
		  throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
		                      "geraDiario_Razao_Clientes_CRT()");
		}

	}

  public void geraDiario_Razao_Clientes_CTRC(DiarioED edComp , HttpServletResponse response) throws
  Excecoes {

		String sql = null , sqlBusca = null;
		ArrayList list = new ArrayList ();
		DiarioED ed = (DiarioED) edComp;
		double saldo_anterior = 0;
		double saldo = 0, variacao = 0;
		String cliente = "" , clieAux = "";

		try {
			//int delAux = this.executasql.executarUpdate("delete from auxiliar1");

		  long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).
		                            toString ().substring (6 , 12)).longValue ());

		  sql = "select Conhecimentos.dt_emissao, Conhecimentos.nr_conhecimento, Conhecimentos.oid_conhecimento, Conhecimentos.vl_total_frete, " +
	  		" Conhecimentos.oid_pessoa_pagador, Pessoas.nm_razao_social from Conhecimentos, pessoas " +
	  		" where Conhecimentos.dm_impresso = 'S' and Conhecimentos.vl_total_frete > 0 and Conhecimentos.dm_situacao != 'C' " +
	  		" and Conhecimentos.oid_pessoa_pagador = Pessoas.oid_pessoa ";

		  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
		      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
		      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
		    sql += " and Conhecimentos.Oid_Pessoa_pagador = '" + ed.getOid_Pessoa () + "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
		    sql += " and Conhecimentos.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
		        "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
		    sql += " and Conhecimentos.dt_Emissao <= '" + ed.getData_Emissao_Final () +
		        "'";
		  }
		  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
		      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
		      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
		    sql += " and Conhecimentos.nr_Conhecimento = '" + ed.getNr_Proximo_Numero () + "'";
		  }
		  if(ed.getDM_Relatorio().equals("R")){
		      sql += " order by oid_pessoa_pagador ";
		  } else {
		      sql += " order by dt_Emissao ";
		  }
//System.out.println("DR CTRC >" +sql);
		  Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();
		  ResultSet res = this.executasql.executarConsulta (sql.toString());

		  while (res.next ()) {
		    Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();

		    cliente = res.getString ("oid_pessoa_pagador");
//		    if (ed.getDM_Relatorio ().equals ("R")) {
//		        saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
//		        edAuxiliar1.setVL_Classifica3 (saldo_anterior);
//		    }

	        edAuxiliar1.setNM_Tabela ("Conhecimentos");
	        edAuxiliar1.setOID_Tabela (res.getString ("oid_conhecimento"));
	        edAuxiliar1.setNM_Classifica1(res.getString ("dt_Emissao"));
	        edAuxiliar1.setNM_Classifica2("EMISSAO NACIONAL");
	        edAuxiliar1.setNM_Classifica3(res.getString ("nm_razao_social"));
	        edAuxiliar1.setNM_Classifica6(res.getString ("nr_conhecimento"));
	        edAuxiliar1.setNM_Classifica5(res.getString ("dt_Emissao"));
	        edAuxiliar1.setNM_Classifica4(res.getString ("nr_conhecimento"));
	        edAuxiliar1.setVL_Classifica1(res.getDouble("vl_total_frete"));
	        edAuxiliar1.setVL_Classifica2(0.0);

	        edAuxiliar1.setNR_Sort (Nr_Sort);

	        Auxiliar1RN.inclui (edAuxiliar1);

	        clieAux = cliente;
		  }

		  sql = "select Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.oid_duplicata, Conhecimentos.dt_emissao as emissao_CTRC, " +
	  		" Conhecimentos.oid_Conhecimento, Conhecimentos.nr_Conhecimento, Conhecimentos.vl_total_frete, " +
	  		" Conhecimentos.oid_pessoa_pagador, Pessoas.nm_razao_social " +
	        " from Duplicatas, Origens_Duplicatas, Conhecimentos, pessoas " +
	        " where Duplicatas.oid_duplicata = Origens_Duplicatas.oid_duplicata " +
	        " AND Origens_Duplicatas.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
	        " AND Origens_Duplicatas.dm_Situacao != 'E' " +
	        " and Conhecimentos.oid_pessoa_pagador = Pessoas.oid_pessoa ";

		  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
		      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
		      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
		    sql += " and Duplicatas.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
		    sql += " and Duplicatas.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
		        "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
		    sql += " and Duplicatas.dt_Emissao <= '" + ed.getData_Emissao_Final () +
		        "'";
		  }
		  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
		      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
		      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
		    sql += " and Conhecimentos.nr_Conhecimento = '" + ed.getNr_Proximo_Numero () + "'";
		  }
		  if (ed.getDM_Relatorio ().equals ("R")) {
		    sql += " order by Duplicatas.oid_pessoa ";
		  }
		  else {
		    sql += " order by dt_Emissao ";
		  }
//System.out.println("DR CTRC >" +sql);
		  res = this.executasql.executarConsulta (sql.toString ());

		  while (res.next ()) {

		      Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();

		      cliente = res.getString ("oid_pessoa_pagador");
//		      if (ed.getDM_Relatorio ().equals ("R")) {
//		          saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
//		          edAuxiliar1.setVL_Classifica3 (saldo_anterior);
//		      }

		      edAuxiliar1.setNM_Tabela ("Duplicatas");
		      edAuxiliar1.setOID_Tabela (res.getString ("oid_duplicata"));
		      edAuxiliar1.setNM_Classifica1(res.getString ("dt_Emissao"));
		      edAuxiliar1.setNM_Classifica2("FATURAMENTO");
		      edAuxiliar1.setNM_Classifica3(res.getString ("nm_razao_social"));
		      edAuxiliar1.setNM_Classifica4(res.getString ("nr_Conhecimento"));
		      edAuxiliar1.setNM_Classifica5(res.getString ("emissao_CTRC"));
		      edAuxiliar1.setVL_Classifica2(res.getDouble("vl_total_frete"));
		      edAuxiliar1.setVL_Classifica1(0.0);

		      edAuxiliar1.setNR_Sort (Nr_Sort);
		      Auxiliar1RN.inclui (edAuxiliar1);

		      clieAux = cliente;

		  }


		  sql = " select * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
		  sql += " order by auxiliar1.nm_classifica3 , auxiliar1.nm_classifica1 , auxiliar1.nm_classifica5  ";
		  res = null;
		  res = this.executasql.executarConsulta (sql.toString ());

		  while (res.next ()) {
		    DiarioED edDiario = new DiarioED();

		    cliente = res.getString ("nm_classifica3");
		    if (ed.getDM_Relatorio ().equals ("R")) {
		      if (!cliente.equals (clieAux)) {
		    	  saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
		    	  //saldo_anterior = res.getDouble ("vl_classifica3");
		        edDiario.setVL_Saldo_Inicial (saldo_anterior);
		        saldo = saldo_anterior;
		      }
		    }

		    edDiario.setDt_Emissao (dataFormatada.getDT_FormataData (res.getString ("nm_classifica1")));
		    edDiario.setNm_Tipo_Documento (res.getString ("nm_classifica2"));
		    edDiario.setNM_Razao_Social(res.getString ("nm_classifica3"));
		    edDiario.setNr_Duplicata(res.getString("nm_classifica4"));
		    edDiario.setDt_Vencimento (dataFormatada.getDT_FormataData (res.getString ("nm_classifica5")));
		    edDiario.setVL_Debito (res.getDouble ("vl_classifica1"));
		    edDiario.setVL_Credito (res.getDouble ("vl_classifica2"));
//System.out.println(edDiario.getNr_Duplicata() + " - " + edDiario.getNM_Razao_Social());
		    saldo = saldo - res.getDouble ("vl_classifica2") + res.getDouble ("vl_classifica1");
		    edDiario.setVL_Saldo (saldo);

		    list.add (edDiario);

		    clieAux = cliente;

		  }

		  sql = "select oid_auxiliar1 from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
		  res = this.executasql.executarConsulta (sql.toString ());
		  Auxiliar1RN AuxRN = new Auxiliar1RN ();
		  while (res.next ()) {
		    Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
		    edAuxiliar1.setOID_auxiliar1 (new Integer (res.getInt ("oid_auxiliar1")));
		    AuxRN.deleta (edAuxiliar1);
		  }

		  util.closeResultset(res);

		  new DiarioRL().geraDiario_Razao_Clientes (list , ed , response);

		}
		catch (Exception e) {
		  e.printStackTrace ();
		  throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
		                      "geraDiario_Razao_Clientes_CTRC()");
		}

	}

	private double convertToReal(String data, double vl, int moeda) throws Excecoes {

	  double vl_real = 0;
//	  Indice_FinanceiroBean idx = new Indice_FinanceiroBean();
//	  Indice_FinanceiroBean idxPadrao = new Indice_FinanceiroBean();
	  try {

//	      Calendar cal = Data.stringToCalendar(data, "yyyy-MM-dd");
//	      if(cal.get(Calendar.DAY_OF_WEEK) == 7 || cal.get(Calendar.DAY_OF_WEEK) == 1){
//	            if(cal.get(Calendar.DAY_OF_WEEK) == 1){
//	                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
//	            } else if(cal.get(Calendar.DAY_OF_WEEK) == 7){
//	                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+2);
//	            }
//	            Date d = new Date();
//	            d = cal.getTime();
//	            SimpleDateFormat formatter_date = new SimpleDateFormat("yyyy-MM-dd");
//	            data = formatter_date.format(d);
//	      }
//	      idxPadrao = Indice_FinanceiroBean.getByUltimaCotacao(Parametro_FixoED.getInstancia().getOID_Moeda_Padrao(), data);
//	      idx = Indice_FinanceiroBean.getByUltimaCotacao(moeda, data);
//	      vl_real = vl / idx.getVL_Cotacao() * idxPadrao.getVL_Cotacao();
		  vl_real = vl;

	  } catch (Exception e) {
	      e.printStackTrace();
	      throw new Excecoes(e.getMessage(), e, getClass().getName(), "convertToReal(String data, double vl, int moeda)");
	  }
	  return Valor.round(vl_real, 2);
	}


	public double getSaldoAnterior(String cliente, String dt_inicial, String dt_final, String origem) throws Excecoes {

		  double vl_real = 0;
		  double vl_duplicatas_anterior = 0, vl_conhecimentos_anterior = 0;
		  double vl_movimentos_anterior = 0;
		  String sqlBusca = "";
		  String clieAux = "";

		  try {
		      if(origem.equals("FAT")){
		          vl_real = 0;
			      sqlBusca = "select sum(duplicatas.vl_saldo) as duplicata_anterior, duplicatas.oid_moeda " +
					   		 " from duplicatas where duplicatas.oid_pessoa = '" + cliente + "'" +
					   		 " AND duplicatas.dt_emissao < '" + dt_inicial + "' group by oid_moeda ";
//System.out.println(sqlBusca);
			      ResultSet rs = this.executasql.executarConsulta (sqlBusca);
			      //if(!cliente.equals(clieAux)) vl_duplicatas_anterior = 0;
			      while (rs.next ()) {
			          if (JavaUtil.doValida (rs.getString ("oid_moeda"))) {
			              vl_duplicatas_anterior += convertToReal (Data.getDataDMY () , rs.getDouble ("duplicata_anterior") , rs.getInt ("oid_moeda"));
			          }
			          else vl_duplicatas_anterior += rs.getDouble ("duplicata_anterior");
			      }

			      sqlBusca = "select sum(movimentos_duplicatas.vl_credito) as vl_c, sum(movimentos_duplicatas.vl_debito) as vl_d, duplicatas.oid_moeda " +
		           			 " from duplicatas, movimentos_duplicatas where duplicatas.oid_duplicata = movimentos_duplicatas.oid_duplicata " +
		           			 " and oid_tipo_instrucao != " + parametro_FixoED.getOID_Tipo_Instrucao_Tarifa() +
		           			 " and oid_tipo_instrucao != " + parametro_FixoED.getOID_Tipo_Instrucao_Juros() +
		           			 " AND duplicatas.dt_emissao < '" + dt_inicial + "' " +
		           			 " and duplicatas.oid_pessoa = '" + cliente + "'" +
		           			 " AND movimentos_duplicatas.dt_movimento >= '" + dt_inicial + "' " +
		           			 " AND movimentos_duplicatas.dt_movimento <= '" + dt_final + "' group by oid_moeda ";
//System.out.println("MOVTOS>> "+sqlBusca);
			      rs = this.executasql.executarConsulta (sqlBusca);
			      //if(!cliente.equals(clieAux)) vl_movimentos_anterior = 0;
			      while (rs.next ()) {
			          if (JavaUtil.doValida (rs.getString ("oid_moeda"))) {
			              vl_movimentos_anterior += (convertToReal(Data.getDataDMY () , rs.getDouble ("vl_c") , rs.getInt ("oid_moeda")) - convertToReal(Data.getDataDMY () , rs.getDouble ("vl_d") , rs.getInt ("oid_moeda")));
			          }
			          else vl_movimentos_anterior += (rs.getDouble ("vl_c") - rs.getDouble ("vl_d"));
			      }
			      vl_real = vl_duplicatas_anterior + vl_movimentos_anterior;

		      } else if(origem.equals("CRT")){
		          vl_real = 0;
		          sqlBusca = "select Conhecimentos.dt_emissao, sum(fat.vl_faturar) as saldo_anterior " +
		          			 " from Conhecimentos_Internacionais Conhecimentos, Conhecimentos_faturamentos fat " +
		          			 " where fat.oid_conhecimento = Conhecimentos.oid_conhecimento " +
		          			 " AND fat.oid_pessoa_pagador = '" + cliente + "'" +
		          			 " AND Conhecimentos.dt_Emissao < '" + dt_inicial + "' and Conhecimentos.dm_situacao = 'L' group by Conhecimentos.dt_Emissao ";
//System.out.println(sqlBusca);
				  ResultSet rs = null;
	      		  rs = this.executasql.executarConsulta (sqlBusca);
	      		  vl_conhecimentos_anterior = 0;
	      		  while (rs.next ()) {
	      		    vl_conhecimentos_anterior += convertToReal(rs.getString("dt_emissao"), rs.getDouble("saldo_anterior"), parametro_FixoED.getOID_Moeda_Dollar());
	      		  }
//System.out.println("vl_conhecimentos_anterior> "+vl_conhecimentos_anterior);
//	      		  sqlBusca = "select Conhecimentos.dt_Emissao, sum(fat.vl_faturar) as saldo_anterior " +
//	      		  			 " from Conhecimentos_Internacionais Conhecimentos, duplicatas, Origens_Duplicatas, Conhecimentos_faturamentos fat " +
//	      		  			 " where Duplicatas.oid_duplicata = Origens_Duplicatas.oid_duplicata " +
//	      		  			 " AND Origens_Duplicatas.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
//	      		  			 " AND fat.oid_conhecimento = Conhecimentos.oid_conhecimento " +
//	      		  			 " AND Origens_Duplicatas.dm_Situacao != 'E' " +
//	      		  			 " AND fat.oid_pessoa_pagador = '" + cliente + "'" +
//	      		  			 " AND duplicatas.dt_Emissao >= '" + dt_inicial + "' AND duplicatas.dt_Emissao <= '" + dt_final + "' " +
//	      		  			 " AND Conhecimentos.dm_situacao = 'F' AND Conhecimentos.dt_Emissao < '" + dt_inicial + "' group by Conhecimentos.dt_Emissao ";
//System.out.println(sqlBusca);
//		  		  rs = this.executasql.executarConsulta (sqlBusca);
//		  		  vl_duplicatas_anterior = 0;
//		  		  while (rs.next ()) {
//		  		      vl_duplicatas_anterior += convertToReal(rs.getString("dt_Emissao"), rs.getDouble("saldo_anterior"), parametro_FixoED.getOID_Moeda_Dollar());
//		  		      //vl_duplicatas_anterior += convertToReal(Data.getDataDMY(), rs.getDouble("saldo_anterior"), parametro_FixoED.getOID_Moeda_Dollar());
//		  		  }
//System.out.println("vl_duplicatas_anterior> "+vl_duplicatas_anterior);
		  		  vl_real = vl_conhecimentos_anterior + vl_duplicatas_anterior;

		      } else if(origem.equals("CTRC")){
		          vl_real = 0;
		          sqlBusca = "select sum(Conhecimentos.vl_total_frete) as saldo_anterior " +
		          			 " from Conhecimentos where oid_pessoa_pagador = '" + cliente + "'" +
		          			 " AND dt_Emissao < '" + dt_inicial + "' and dm_situacao = 'G' AND dm_impresso = 'S' ";
//System.out.println(sqlBusca);
				  ResultSet rs = null;
	      		  rs = this.executasql.executarConsulta (sqlBusca);
	      		  vl_conhecimentos_anterior = 0;
	      		  while (rs.next ()) {
	      		    vl_conhecimentos_anterior += rs.getDouble("saldo_anterior");
	      		  }
//System.out.println("vl_conhecimentos_anterior> "+vl_conhecimentos_anterior);
	      		  sqlBusca = "select sum(Conhecimentos.vl_total_frete) as saldo_anterior " +
	      		  			 " from Conhecimentos, duplicatas, Origens_Duplicatas " +
	      		  			 " where Duplicatas.oid_duplicata = Origens_Duplicatas.oid_duplicata " +
	      		  			 " AND Origens_Duplicatas.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
	      		  			 " AND Origens_Duplicatas.dm_Situacao != 'E' " +
	      		  			 " AND Conhecimentos.oid_pessoa_pagador = '" + cliente + "'" +
	      		  			 " AND duplicatas.dt_Emissao >= '" + dt_inicial + "' AND duplicatas.dt_Emissao <= '" + dt_final + "' " +
	      		  			 " AND Conhecimentos.dm_situacao = 'F' AND Conhecimentos.dt_Emissao < '" + dt_inicial + "' ";
//System.out.println(sqlBusca);
		  		  rs = this.executasql.executarConsulta (sqlBusca);
		  		  vl_duplicatas_anterior = 0;
		  		  while (rs.next ()) {
		  		      vl_duplicatas_anterior += rs.getDouble("saldo_anterior");
		  		  }
//System.out.println("vl_duplicatas_anterior> "+vl_duplicatas_anterior);
		  		  vl_real = vl_conhecimentos_anterior + vl_duplicatas_anterior;
		      }

		  } catch (Exception e) {
		      e.printStackTrace();
		      throw new Excecoes(e.getMessage(), e, getClass().getName(), "getSaldoAnterior(String cliente, String dt_inicial)");
		  }
		  return vl_real;
		}

	public void geraDiario_Razao_Fornecedores(CompromissoED edComp, HttpServletResponse response) throws
    Excecoes {

  		String sql = null, sqlBusca = null;
  		ArrayList list = new ArrayList();
  		ArrayList toReport = new ArrayList();
  		ArrayList saldos = new ArrayList();
  		CompromissoED ed = (CompromissoED) edComp;
  		double saldo_anterior = 0, saldo_inicial_geral = 0;
  		double saldo = 0;
  		String cliente = "", clieAux = "";
  		String oids_a_excluir = "";

  		try {
  			//int delAux = this.executasql.executarUpdate("delete from auxiliar1");

  		    long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).
                      toString ().substring (6 , 12)).longValue ());

  		  sql = "select compromissos.dt_emissao, compromissos.oid_compromisso, compromissos.nr_compromisso, compromissos.dt_vencimento," +
  		  		" compromissos.vl_compromisso, compromissos.oid_pessoa, Pessoas.nm_razao_social, compromissos.nr_documento, compromissos.oid_moeda " +
  		  		" ,Pessoas.nr_cnpj_cpf " +
  		  		" from compromissos, pessoas, contas, fornecedores where " +
  		  		" compromissos.oid_pessoa = Pessoas.oid_pessoa " +
  		  		" AND fornecedores.oid_pessoa = Pessoas.oid_pessoa " +
  		  		" AND compromissos.oid_compromisso_vincula is null " +
//  		  		" AND compromissos.dt_liberado is not null " +
  		  		" AND fornecedores.oid_conta = contas.oid_conta ";
  		  //" AND Compromissos.oid_conta = contas.oid_conta ";

  		  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
  		      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
  		      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
  		    sql += " and compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
  		  }
  		  if (String.valueOf(ed.getOid_Conta()) != null &&
    		      !String.valueOf (ed.getOid_Conta()).equals ("") &&
    		      !String.valueOf (ed.getOid_Conta()).equals ("null")) {
    		    sql += " and contas.Oid_Conta = " + ed.getOid_Conta();
    		  }
  		if (JavaUtil.doValida(ed.getNm_Conta())) {
  			String negacao = "";
  			if(JavaUtil.doValida(ed.getDM_Situacao()) && ed.getDM_Situacao().equals("N")){
  				negacao = "not";
  			}
	    	StringTokenizer stk = new StringTokenizer(ed.getNm_Conta(), ",");
			String strContas = "";
			while (stk != null && stk.hasMoreTokens()){
				strContas += "'"+stk.nextToken().trim()+"',";
			}
			strContas = strContas.substring(0,strContas.length()-1);
			sql += " and contas.cd_conta " + negacao + " in (" + strContas + ")";
			//System.out.println(" and Entregadores.cd_entregador in (" + strEntregadores + ")");
	    }
  		  if (String.valueOf (ed.getDt_Inicial()) != null &&
  		      !String.valueOf (ed.getDt_Inicial ()).equals ("") &&
  		      !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
  		    sql += " and compromissos.dt_Emissao >= '" + ed.getDt_Inicial () +
  		        "'";
  		  }
  		  if (String.valueOf (ed.getDt_Final()) != null &&
  		      !String.valueOf (ed.getDt_Final ()).equals ("") &&
  		      !String.valueOf (ed.getDt_Final ()).equals ("null")) {
  		    sql += " and compromissos.dt_Emissao <= '" + ed.getDt_Final () +
  		        "'";
  		  }
  		  if(ed.getDM_Relatorio().equals("R")){
  		      sql += " order by compromissos.oid_pessoa ";
  		  } else {
  		      sql += " order by compromissos.dt_Emissao ";
  		  }

  		  Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();
//System.out.println("DR_FORN "+sql);

  		  ResultSet res = this.executasql.executarConsulta (sql.toString());

  		  while (res.next ()) {
  			CompromissoED edDiario = new CompromissoED();
  			cliente = res.getString ("oid_pessoa");

  			edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_Emissao")));
  		    edDiario.setNm_Tipo_Documento("ENTRADA");
  		    edDiario.setNm_Razao_Social(res.getString ("nm_razao_social") + " (" + res.getString ("nr_cnpj_cpf") + ")");
  		    edDiario.setNr_Compromisso(new Integer(res.getInt("nr_compromisso")));
  		    edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData(res.getString ("dt_vencimento")));
  		    edDiario.setOid_Pessoa(res.getString("oid_pessoa"));
  		    edDiario.setVL_Debito(0.0);
  		    if(JavaUtil.doValida(res.getString("oid_moeda"))){
  		    	edDiario.setVL_Credito(convertToReal(res.getString ("dt_Emissao"), res.getDouble("vl_compromisso"), res.getInt("oid_moeda")));
	        } else edDiario.setVL_Credito(res.getDouble("vl_compromisso"));
  		    edDiario.setNr_Documento(res.getString("nr_documento"));

  		    list.add(edDiario);

  	        clieAux = cliente;

  		  }

  		  util.closeResultset(res);

  	    //Movimentos
  	      sql = "select Movimentos_Compromissos.dt_pagamento, Movimentos_Compromissos.vl_pagamento, compromissos.nr_documento, Movimentos_Compromissos.vl_multa_pagamento, " +
  	      		" compromissos.dt_emissao, Movimentos_Compromissos.vl_juros_pagamento, Movimentos_Compromissos.vl_desconto, Movimentos_Compromissos.vl_outras_despesas, " +
  		  		" Compromissos.nr_compromisso, Compromissos.oid_compromisso, Compromissos.dt_vencimento, Compromissos.oid_pessoa, Pessoas.nm_razao_social, Compromissos.oid_moeda " +
  		  	    " ,Pessoas.nr_cnpj_cpf " +
  		  		" from Lotes_Compromissos Movimentos_Compromissos, Compromissos, pessoas, contas, fornecedores " +
  		  		" where movimentos_Compromissos.oid_Compromisso = Compromissos.oid_Compromisso " +
  		  		" AND Compromissos.oid_pessoa = Pessoas.oid_pessoa " +
  		  		" AND Pessoas.oid_pessoa = fornecedores.oid_pessoa " +
  		  		" AND fornecedores.oid_conta = contas.oid_conta ";
  		  		//" AND Compromissos.oid_conta = contas.oid_conta ";

  	      if (String.valueOf (ed.getOid_Pessoa ()) != null &&
  		      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
  		      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
  		    sql += " and Compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
  		  }
  	      if (String.valueOf(ed.getOid_Conta()) != null &&
  		      !String.valueOf (ed.getOid_Conta()).equals ("") &&
  		      !String.valueOf (ed.getOid_Conta()).equals ("null")) {
  		    sql += " and contas.Oid_Conta = " + ed.getOid_Conta();
  		  }
  	    if (JavaUtil.doValida(ed.getNm_Conta())) {
  			String negacao = "";
  			if(JavaUtil.doValida(ed.getDM_Situacao()) && ed.getDM_Situacao().equals("N")){
  				negacao = "not";
  			}
	    	StringTokenizer stk = new StringTokenizer(ed.getNm_Conta(), ",");
			String strContas = "";
			while (stk != null && stk.hasMoreTokens()){
				strContas += "'"+stk.nextToken().trim()+"',";
			}
			strContas = strContas.substring(0,strContas.length()-1);
			sql += " and contas.cd_conta " + negacao + " in (" + strContas + ")";
			//System.out.println(" and Entregadores.cd_entregador in (" + strEntregadores + ")");
	    }
  	      if (String.valueOf (ed.getDt_Inicial() ) != null &&
  		      !String.valueOf (ed.getDt_Inicial() ).equals ("") &&
  		      !String.valueOf (ed.getDt_Inicial() ).equals ("null")) {
  		    sql += " and movimentos_Compromissos.dt_pagamento >= '" +
  		        ed.getDt_Inicial() + "'";
  		  }
  		  if (String.valueOf (ed.getDt_Final ()) != null &&
  		      !String.valueOf (ed.getDt_Final ()).equals ("") &&
  		      !String.valueOf (ed.getDt_Final ()).equals ("null")) {
  		    sql += " and movimentos_Compromissos.dt_pagamento <= '" +
  		        ed.getDt_Final () + "'";
  		  }
  		  if(ed.getDM_Relatorio().equals("R")){
  		      sql += " order by Compromissos.oid_pessoa ";
  		  } else {
  		      sql += " order by movimentos_Compromissos.dt_pagamento ";
  		  }
//System.out.println("DR_FORN2 "+sql);
  		  res = null;
  		  res = this.executasql.executarConsulta (sql.toString ());

  		  double soma_pgtos = 0;
  		  boolean primeiro = true;

  		  while (res.next ()) {

  			CompromissoED edDiario = new CompromissoED();
  			cliente = res.getString ("oid_pessoa");
//System.out.println("pgto > " + res.getDouble("vl_pagamento") + "multa > " + res.getDouble("vl_multa_pagamento") + "juro > " + res.getDouble("vl_juros_pagamento") + "outras > " + res.getDouble("vl_outras_despesas") + "desc > " + res.getDouble("vl_desconto"));
  			double vl_debito = res.getDouble("vl_pagamento") - res.getDouble("vl_multa_pagamento") - res.getDouble("vl_juros_pagamento") - res.getDouble("vl_outras_despesas") + res.getDouble("vl_desconto");
//System.out.println("VL > " + vl_debito);
  			edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString("dt_pagamento")));
  		    edDiario.setNm_Tipo_Documento("PAGAMENTO");
  		  edDiario.setNm_Razao_Social(res.getString ("nm_razao_social") + " (" + res.getString ("nr_cnpj_cpf") + ")");
  		    edDiario.setNr_Compromisso(new Integer(res.getInt("nr_Compromisso")));
  		    edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData(res.getString("dt_vencimento")));
  		    edDiario.setOid_Pessoa(res.getString("oid_pessoa"));
  		    if(JavaUtil.doValida(res.getString("oid_moeda"))){
  		    	edDiario.setVL_Debito(convertToReal(res.getString("dt_pagamento"), vl_debito, res.getInt("oid_moeda")));
	        } else {
	        	edDiario.setVL_Debito(vl_debito);
	        }
  		    edDiario.setVL_Credito(0.0);
  		    edDiario.setNr_Documento(res.getString("nr_documento"));

  		    list.add(edDiario);
//  		    int difMeses = Data.diferencaMeses(Data.stringToCalendar(dataFormatada.getDT_FormataData(res.getString("dt_emissao")),Data.SHORT_DATE),Data.stringToCalendar(dataFormatada.getDT_FormataData(res.getString("dt_pagamento")),Data.SHORT_DATE));

  		    if(ed.getDM_Relatorio().equals("R")){

	    		if(Data.comparaData(ed.getDt_Inicial(), dataFormatada.getDT_FormataData(res.getString("dt_emissao")))){
	    			soma_pgtos = edDiario.getVL_Debito();
	    			CompromissoED edSaldo = new CompromissoED();
  		    		edSaldo.setOid_Pessoa(res.getString("oid_pessoa"));
  		    		edSaldo.setNm_Razao_Social(res.getString ("nm_razao_social"));
  		    		edSaldo.setVL_Saldo_Inicial(soma_pgtos);
  		    		saldos.add(edSaldo);
//  		    		System.out.println("SALDO: "+ soma_pgtos + " |"+ res.getString("oid_pessoa"));
	    		}

  		    }

//  		  if(ed.getDM_Relatorio().equals("R")){
//		    	if(!clieAux.equals(cliente)){
//		    		if(!primeiro){
//	  		    		CompromissoED edSaldo = new CompromissoED();
//	  		    		edSaldo.setOid_Pessoa(res.getString("oid_pessoa"));
//	  		    		edSaldo.setNm_Razao_Social(res.getString ("nm_razao_social"));
//	  		    		edSaldo.setVL_Saldo_Inicial(soma_pgtos);
//	  		    		saldos.add(edSaldo);
//		    		}
//		    		if(difMeses>0)
//		    			soma_pgtos = edDiario.getVL_Debito();
//		    		else soma_pgtos = 0.0;
//		    	} else {
//		    		if(difMeses>0)
//		    			soma_pgtos += edDiario.getVL_Debito();
//		    	}
//		    }
  		    primeiro = false;
  		    clieAux = cliente;

  		  }
  		  //Fim Movimentos
  		  util.closeResultset(res);

  		  Collections.sort (list , new Comparator () {
	          public int compare (Object o1 , Object o2) {
	        	  CompromissoED ed1 = (CompromissoED) o1;
	        	  CompromissoED ed2 = (CompromissoED) o2;
	            return ed1.getDt_Emissao().compareTo(ed2.getDt_Emissao());
	          }
	        });

		  if (ed.getDM_Relatorio ().equals ("R")) {
			  Collections.sort (list , new Comparator () {
		          public int compare (Object o1 , Object o2) {
		        	  CompromissoED ed1 = (CompromissoED) o1;
		        	  CompromissoED ed2 = (CompromissoED) o2;
		            return ed1.getNm_Razao_Social().compareTo(ed2.getNm_Razao_Social());
		          }
		        });
			  Collections.sort (saldos , new Comparator () {
		          public int compare (Object o1 , Object o2) {
		        	  CompromissoED ed1 = (CompromissoED) o1;
		        	  CompromissoED ed2 = (CompromissoED) o2;
		            return ed1.getNm_Razao_Social().compareTo(ed2.getNm_Razao_Social());
		          }
		        });
		  }


//		  for(int y=0;y<saldos.size();y++){
//			  CompromissoED edSaldo = (CompromissoED)saldos.get(y);
//			  System.out.println("SALDOS -"+edSaldo.getNm_Razao_Social()+" / "+ edSaldo.getVL_Saldo_Inicial());
//		  }

  		  clieAux = "";
		  saldo_anterior = 0;
		  primeiro = true;
		  double pgtos = 0.0;
		  for(int t=0;t<list.size();t++){
			  CompromissoED edLista = (CompromissoED)list.get(t);
			  cliente = edLista.getOid_Pessoa();

			  if (ed.getDM_Relatorio ().equals ("R")) {
				  if (!cliente.equals (clieAux)) {
					  sqlBusca = "select sum(vl_saldo) as saldo_anterior, oid_moeda from compromissos where oid_pessoa = '"+cliente+"'" +
					  			 " AND dt_Emissao < '" + ed.getDt_Inicial() + "' group by oid_moeda";
					  ResultSet rs = this.executasql.executarConsulta(sqlBusca);
					  saldo_anterior = 0;
					  while (rs.next ()) {
						  if(JavaUtil.doValida(rs.getString("oid_moeda"))){
							  saldo_anterior += convertToReal(Data.getDataDMY(), rs.getDouble("saldo_anterior"), rs.getInt("oid_moeda"));
						  } else saldo_anterior += rs.getDouble("saldo_anterior");
					  }
					  //saldo_anterior = saldo_anterior*-1;
//System.out.println("PESSOA> "+edLista.getNm_Razao_Social());
//System.out.println("EM ABERTO> "+saldo_anterior);
					  pgtos = 0.0;
					  for(int y=0;y<saldos.size();y++){
						  CompromissoED edSaldo = (CompromissoED)saldos.get(y);
//						  if(cliente.equals(edSaldo.getOid_Pessoa()) && edLista.getNm_Tipo_Documento().equals("PAGAMENTO"))
						  if(cliente.equals(edSaldo.getOid_Pessoa()))
							  pgtos += edSaldo.getVL_Saldo_Inicial();
					  }
//System.out.println("+PGTOS> "+pgtos);

					  edLista.setVl_saldo_Vinculado(new Double(saldo_anterior+pgtos));
	  		          saldo = edLista.getVl_saldo_Vinculado().doubleValue();

	  		        saldo_inicial_geral += edLista.getVl_saldo_Vinculado().doubleValue();

//					  edLista.setVl_saldo_Vinculado(saldo_anterior);
//					  saldo = saldo_anterior;
				  }
			  } else {
				  //Saldo anterior vem da tela
				  if(primeiro){
					  edLista.setVl_saldo_Vinculado(new Double(ed.getVL_Saldo_Inicial()));
					  saldo = ed.getVL_Saldo_Inicial();
					  primeiro = false;
				  } else edLista.setVl_saldo_Vinculado(new Double(saldo));
			  }

			  saldo = saldo + edLista.getVL_Credito() - edLista.getVL_Debito();
			  edLista.setVl_Saldo(new Double(saldo));
			  toReport.add(edLista);
			  clieAux = cliente;

		  }

		  ed.setVL_Saldo_Inicial(saldo_inicial_geral);

		  if(JavaUtil.doValida(ed.getSO()) && ed.getSO().equalsIgnoreCase("CSV")){
			  new DiarioRL().geraDiario_Razao_FornecedoresTXT(toReport , ed , response);
		  } else {
			  new DiarioRL().geraDiario_Razao_Fornecedores(toReport , ed , response);
		  }

  		}
  		catch (Exception e) {
  		    e.printStackTrace();
  		  throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
  		                      "geraDiario_Auxiliar_Fornecedores()");
  		}

  	}

	public void geraDiario_Razao_Fornecedores_old(CompromissoED edComp, HttpServletResponse response) throws
    Excecoes {

  		String sql = null, sqlBusca = null;
  		ArrayList list = new ArrayList();
  		ArrayList list_aux = new ArrayList();
  		CompromissoED ed = (CompromissoED) edComp;
  		double saldo_anterior = 0;
  		double saldo = 0;
  		String cliente = "", clieAux = "";
  		String oids_a_excluir = "";

  		try {
  			//int delAux = this.executasql.executarUpdate("delete from auxiliar1");

  		    long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).
                      toString ().substring (6 , 12)).longValue ());

  		  sql = "select compromissos.dt_emissao, compromissos.oid_compromisso, compromissos.nr_compromisso, compromissos.dt_vencimento," +
  		  		" compromissos.vl_compromisso, compromissos.oid_pessoa, Pessoas.nm_razao_social, compromissos.nr_documento, compromissos.oid_moeda " +
  		  		" from compromissos, pessoas, contas, fornecedores where " +
  		  		" compromissos.oid_pessoa = Pessoas.oid_pessoa " +
  		  		" AND fornecedores.oid_pessoa = Pessoas.oid_pessoa " +
  		  		" AND compromissos.oid_compromisso_vincula is null " +
  		  		" AND compromissos.dt_liberado is not null " +
  		  		" AND fornecedores.oid_conta = contas.oid_conta ";
  		  //" AND Compromissos.oid_conta = contas.oid_conta ";

  		  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
  		      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
  		      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
  		    sql += " and compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
  		  }
  		  if (String.valueOf(ed.getOid_Conta()) != null &&
    		      !String.valueOf (ed.getOid_Conta()).equals ("") &&
    		      !String.valueOf (ed.getOid_Conta()).equals ("null")) {
    		    sql += " and contas.Oid_Conta = " + ed.getOid_Conta();
    		  }
  		if (JavaUtil.doValida(ed.getNm_Conta())) {
  			String negacao = "";
  			if(JavaUtil.doValida(ed.getDM_Situacao()) && ed.getDM_Situacao().equals("N")){
  				negacao = "not";
  			}
	    	StringTokenizer stk = new StringTokenizer(ed.getNm_Conta(), ",");
			String strContas = "";
			while (stk != null && stk.hasMoreTokens()){
				strContas += "'"+stk.nextToken().trim()+"',";
			}
			strContas = strContas.substring(0,strContas.length()-1);
			sql += " and contas.cd_conta " + negacao + " in (" + strContas + ")";
			//System.out.println(" and Entregadores.cd_entregador in (" + strEntregadores + ")");
	    }
  		  if (String.valueOf (ed.getDt_Inicial()) != null &&
  		      !String.valueOf (ed.getDt_Inicial ()).equals ("") &&
  		      !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
  		    sql += " and compromissos.dt_Emissao >= '" + ed.getDt_Inicial () +
  		        "'";
  		  }
  		  if (String.valueOf (ed.getDt_Final()) != null &&
  		      !String.valueOf (ed.getDt_Final ()).equals ("") &&
  		      !String.valueOf (ed.getDt_Final ()).equals ("null")) {
  		    sql += " and compromissos.dt_Emissao <= '" + ed.getDt_Final () +
  		        "'";
  		  }
  		  if(ed.getDM_Relatorio().equals("R")){
  		      sql += " order by compromissos.oid_pessoa ";
  		  } else {
  		      sql += " order by compromissos.dt_Emissao ";
  		  }

  		  Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();
//System.out.println("DR_FORN "+sql);

  		  ResultSet res = this.executasql.executarConsulta (sql.toString());

  		  oids_a_excluir = "";
  		  while (res.next ()) {
  		    Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();

  		    cliente = res.getString ("oid_pessoa");

  		    if(JavaUtil.doValida(oids_a_excluir)){
  		    	oids_a_excluir += ","+res.getString ("oid_compromisso");
  		    } else oids_a_excluir = res.getString ("oid_compromisso");

  	        edAuxiliar1.setNM_Tabela ("compromissos");
  	        edAuxiliar1.setOID_Tabela (res.getString ("oid_compromisso"));
  	        edAuxiliar1.setNM_Classifica1(res.getString ("dt_Emissao"));
  	        edAuxiliar1.setNM_Classifica2("ENTRADA");
  	        edAuxiliar1.setNM_Classifica3(res.getString ("nm_razao_social"));
  	        edAuxiliar1.setNM_Classifica4(res.getString ("nr_compromisso"));
  	        edAuxiliar1.setNM_Classifica5(res.getString ("dt_vencimento"));
  	        edAuxiliar1.setNM_Classifica6(res.getString ("oid_pessoa"));
  	        edAuxiliar1.setNM_Classifica7(res.getString ("nr_documento"));
  	        if(JavaUtil.doValida(res.getString("oid_moeda"))){
  	            edAuxiliar1.setVL_Classifica2(convertToReal(res.getString ("dt_Emissao"), res.getDouble("vl_compromisso"), res.getInt("oid_moeda")));
  	        } else edAuxiliar1.setVL_Classifica2(res.getDouble("vl_compromisso"));
  	        edAuxiliar1.setVL_Classifica1(0.0);
  	      edAuxiliar1.setVL_Classifica3(saldo_anterior);

  	        edAuxiliar1.setNR_Sort (Nr_Sort);
  	        Auxiliar1RN.inclui (edAuxiliar1);

  	        clieAux = cliente;

  		  }

  		  util.closeResultset(res);

  	    //Movimentos
  	      sql = "select Movimentos_Compromissos.dt_pagamento, Movimentos_Compromissos.vl_pagamento, compromissos.nr_documento, Movimentos_Compromissos.vl_multa_pagamento, " +
  	      		" Movimentos_Compromissos.vl_juros_pagamento, Movimentos_Compromissos.vl_desconto, Movimentos_Compromissos.vl_outras_despesas, " +
  		  		" Compromissos.nr_compromisso, Compromissos.oid_compromisso, Compromissos.dt_vencimento, Compromissos.oid_pessoa, Pessoas.nm_razao_social, Compromissos.oid_moeda " +
  		  		" from Movimentos_Compromissos, Compromissos, pessoas, contas, fornecedores " +
  		  		" where movimentos_Compromissos.oid_Compromisso = Compromissos.oid_Compromisso " +
  		  		" AND Compromissos.oid_pessoa = Pessoas.oid_pessoa " +
  		  		" AND Pessoas.oid_pessoa = fornecedores.oid_pessoa " +
  		  		" AND fornecedores.oid_conta = contas.oid_conta ";
  		  		//" AND Compromissos.oid_conta = contas.oid_conta ";

  	      if (String.valueOf (ed.getOid_Pessoa ()) != null &&
  		      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
  		      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
  		    sql += " and Compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
  		  }
  	      if (String.valueOf(ed.getOid_Conta()) != null &&
  		      !String.valueOf (ed.getOid_Conta()).equals ("") &&
  		      !String.valueOf (ed.getOid_Conta()).equals ("null")) {
  		    sql += " and contas.Oid_Conta = " + ed.getOid_Conta();
  		  }
  	    if (JavaUtil.doValida(ed.getNm_Conta())) {
  			String negacao = "";
  			if(JavaUtil.doValida(ed.getDM_Situacao()) && ed.getDM_Situacao().equals("N")){
  				negacao = "not";
  			}
	    	StringTokenizer stk = new StringTokenizer(ed.getNm_Conta(), ",");
			String strContas = "";
			while (stk != null && stk.hasMoreTokens()){
				strContas += "'"+stk.nextToken().trim()+"',";
			}
			strContas = strContas.substring(0,strContas.length()-1);
			sql += " and contas.cd_conta " + negacao + " in (" + strContas + ")";
			//System.out.println(" and Entregadores.cd_entregador in (" + strEntregadores + ")");
	    }
  	      if (String.valueOf (ed.getDt_Inicial() ) != null &&
  		      !String.valueOf (ed.getDt_Inicial() ).equals ("") &&
  		      !String.valueOf (ed.getDt_Inicial() ).equals ("null")) {
  		    sql += " and movimentos_Compromissos.dt_pagamento >= '" +
  		        ed.getDt_Inicial() + "'";
  		  }
  		  if (String.valueOf (ed.getDt_Final ()) != null &&
  		      !String.valueOf (ed.getDt_Final ()).equals ("") &&
  		      !String.valueOf (ed.getDt_Final ()).equals ("null")) {
  		    sql += " and movimentos_Compromissos.dt_pagamento <= '" +
  		        ed.getDt_Final () + "'";
  		  }
  		  if(ed.getDM_Relatorio().equals("R")){
  		      sql += " order by Compromissos.oid_pessoa ";
  		  } else {
  		      sql += " order by movimentos_Compromissos.dt_pagamento ";
  		  }
//System.out.println("DR_FORN2 "+sql);
  		  res = null;
  		  res = this.executasql.executarConsulta (sql.toString ());

  		  while (res.next ()) {

  		    Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
  		    cliente = res.getString ("oid_pessoa");

  	        edAuxiliar1.setNM_Tabela ("Movimentos_Compromissos");
  	        edAuxiliar1.setOID_Tabela (res.getString ("oid_Compromisso"));
  	        edAuxiliar1.setNM_Classifica1(res.getString ("dt_pagamento"));
  	        edAuxiliar1.setNM_Classifica3(res.getString ("nm_razao_social"));
  	        edAuxiliar1.setNM_Classifica4(res.getString ("nr_Compromisso"));
  	        edAuxiliar1.setNM_Classifica5(res.getString ("dt_vencimento"));
  	        edAuxiliar1.setNM_Classifica6(res.getString ("oid_pessoa"));
  	        edAuxiliar1.setNM_Classifica7(res.getString ("nr_documento"));
  	        if(JavaUtil.doValida(res.getString("oid_moeda"))){
  	            edAuxiliar1.setVL_Classifica1(convertToReal(res.getString("dt_pagamento"), res.getDouble("vl_pagamento"), res.getInt("oid_moeda")));
  	            //edAuxiliar1.setVL_Classifica2(convertToReal(res.getString("dt_pagamento"), res.getDouble("vl_pagamento"), res.getInt("oid_moeda")));
  	        } else {
  	            edAuxiliar1.setVL_Classifica1(res.getDouble("vl_pagamento"));
  	            //edAuxiliar1.setVL_Classifica2(res.getDouble("vl_pagamento"));
  	        }
  	      edAuxiliar1.setNM_Classifica2("PAGAMENTO");
  	    edAuxiliar1.setVL_Classifica3(saldo_anterior);

  	        edAuxiliar1.setNR_Sort (Nr_Sort);
  	        Auxiliar1RN.inclui (edAuxiliar1);

  		  }
  		  //Fim Movimentos
  		  util.closeResultset(res);

  		  sql = " select * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
  		  if(ed.getDM_Relatorio().equals("R")){
  			  sql += " order by auxiliar1.nm_classifica3 , auxiliar1.nm_classifica1 , auxiliar1.nm_classifica5  ";
  		  } else sql += " order by auxiliar1.nm_classifica1 , auxiliar1.nm_classifica3 , auxiliar1.nm_classifica5  ";
//System.out.println("DR_FORN3 > "+sql);
  	      res = null;
  	      res = this.executasql.executarConsulta (sql.toString ());

  	      clieAux = "";
  	      String dataAux = "";
  	      boolean primeiro = true;
  	      while (res.next()) {
  	        CompromissoED edDiario = new CompromissoED();

  	        cliente = res.getString ("nm_classifica6");
  		    if(ed.getDM_Relatorio().equals("R")){
  		        if(!cliente.equals(clieAux)){
  		        	sqlBusca = "select vl_saldo as saldo_anterior, oid_moeda from compromissos where oid_pessoa = '"+cliente+"'" +
			        		   " AND dt_Emissao < '" + ed.getDt_Inicial() + "'";
			        ResultSet rs = this.executasql.executarConsulta(sqlBusca);
			        saldo_anterior = 0;
			        while (rs.next ()) {
			            if(JavaUtil.doValida(rs.getString("oid_moeda"))){
			                saldo_anterior += convertToReal(Data.getDataDMY(), rs.getDouble("saldo_anterior"), rs.getInt("oid_moeda"));
			            } else saldo_anterior += rs.getDouble("saldo_anterior");
			        }
			        sqlBusca = "select sum(vl_classifica1) as mvtos from auxiliar1 where nm_classifica6 = '"+cliente+"' and Nr_Sort = " + Nr_Sort;
			        if(JavaUtil.doValida(oids_a_excluir)){
			        	sqlBusca += " and oid_tabela not in (" + oids_a_excluir + ")";
			        }
			        //System.out.println(sqlBusca);
			        rs = this.executasql.executarConsulta(sqlBusca);
			        while (rs.next ()) {
			            saldo_anterior += rs.getDouble("mvtos");
			        }
  		            edDiario.setVl_saldo_Vinculado(new Double((saldo_anterior)*-1));
  		            saldo = edDiario.getVl_saldo_Vinculado().doubleValue();
  		        }
//System.out.println(cliente+"saldo_ant:"+saldo_anterior);
  		    } else {
  		    	//Saldo anterior vem da tela
  		    	if(primeiro){
  		    		edDiario.setVl_saldo_Vinculado(new Double(ed.getVL_Saldo_Inicial()));
  		    		saldo = ed.getVL_Saldo_Inicial();
  		    		primeiro = false;
  		    	} else edDiario.setVl_saldo_Vinculado(new Double(saldo));
  		    }
//  		  System.out.println("DR_FORN4 > "+sqlBusca);
  		    edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString("nm_classifica1")));
  		    edDiario.setNm_Tipo_Documento(res.getString("nm_classifica2"));
  		    edDiario.setNm_Razao_Social(res.getString ("nm_classifica3"));
  		    edDiario.setNr_Compromisso(new Integer(res.getInt("nm_classifica4")));
  		    edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData(res.getString("nm_classifica5")));
  		    edDiario.setVL_Debito(res.getDouble("vl_classifica1"));
  		    edDiario.setVL_Credito(res.getDouble("vl_classifica2"));
  		    edDiario.setNr_Documento(res.getString("nm_classifica7"));

  		    saldo = saldo - res.getDouble("vl_classifica2") + res.getDouble("vl_classifica1");
  		    edDiario.setVl_Saldo(new Double(saldo));
  		    list.add(edDiario);

  		    clieAux = cliente;
  		    dataAux = res.getString("nm_classifica1");

  	      }

  	      sql = "delete from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
	      int del = this.executasql.executarUpdate(sql.toString());

//  	      sql = "select oid_auxiliar1 from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
//  	      res = this.executasql.executarConsulta (sql.toString());
//  	      Auxiliar1RN AuxRN = new Auxiliar1RN ();
//  	      while (res.next ()) {
//  	        Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
//  	        edAuxiliar1.setOID_auxiliar1 (new Integer (res.getInt ("oid_auxiliar1")));
//  	        AuxRN.deleta (edAuxiliar1);
//  	      }

  		  new DiarioRL().geraDiario_Razao_Fornecedores(list , ed , response);

  		}
  		catch (Exception e) {
  		    e.printStackTrace();
  		  throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
  		                      "geraDiario_Auxiliar_Fornecedores()");
  		}

  	}

	  public ArrayList getDadosDiario_Razao_Clientes_FAT(DiarioED edComp) throws
	  Excecoes {

			String sql = null , sqlBusca = null;
			ArrayList list = new ArrayList ();
			ArrayList toReport = new ArrayList ();
			DiarioED ed = (DiarioED) edComp;
			double saldo_anterior = 0, vl_cot_pgto = 0, vl_deduzibles = 0;
			double saldo = 0, variacao = 0;
			String cliente = "" , clieAux = "", dt_variacao = "";

			try {
				//int delAux = this.executasql.executarUpdate("delete from auxiliar1");

			  //long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).toString ().substring (6 , 12)).longValue ());

			  sql = "select Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.vl_duplicata, Duplicatas.dt_vencimento, " +
			  		" Duplicatas.oid_duplicata, Pessoas.nr_cnpj_cpf, " +
			        " Duplicatas.oid_pessoa, Pessoas.nm_razao_social, Duplicatas.oid_moeda " +
			        " from Duplicatas, pessoas where " +
			        " Duplicatas.oid_pessoa = Pessoas.oid_pessoa ";

			  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
			    sql += " and Duplicatas.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
			    sql += " and Duplicatas.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
			        "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
			    sql += " and Duplicatas.dt_Emissao <= '" + ed.getData_Emissao_Final () +
			        "'";
			  }
			  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
			    sql += " and Duplicatas.nr_documento = '" + ed.getNr_Proximo_Numero () + "'";
			  }
			  if (ed.getDM_Relatorio ().equals ("R")) {
			    sql += " order by Duplicatas.oid_pessoa ";
			  }
			  else {
			    sql += " order by Duplicatas.dt_Emissao ";
			  }

			  Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();

			  ResultSet rs = null;
			  ResultSet res = null;
//	System.out.println("DIARIO FAT >" +sql);
			  res = this.executasql.executarConsulta (sql.toString ());

			  while (res.next ()) {

				  DiarioED edDiario = new DiarioED();

			      cliente = res.getString ("oid_pessoa");

			      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_Emissao")));
			      edDiario.setNm_Tipo_Documento("EMISSAO FATURA nr.: " + res.getString("nr_duplicata"));
			      edDiario.setNM_Razao_Social(res.getString ("nm_razao_social") + " (" + res.getString ("nr_cnpj_cpf") + ")");
			      edDiario.setNr_Duplicata(res.getString("nr_duplicata"));
			      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("dt_vencimento")));
			      if (JavaUtil.doValida(res.getString("oid_moeda")) && res.getInt("oid_moeda") != parametro_FixoED.getOID_Moeda_Padrao()) {
			    	  edDiario.setVL_Debito(convertToReal(res.getString("dt_Emissao"), res.getDouble("vl_duplicata") , res.getInt("oid_moeda")));
			      } else edDiario.setVL_Debito(res.getDouble("vl_duplicata"));
			      edDiario.setVL_Credito(0.0);
			      edDiario.setOid_Pessoa(cliente);
			      edDiario.setNr_Remessa("0");

			      list.add(edDiario);
			      clieAux = cliente;

			  }

//REC AVULSO
			  sql = "select dt_movimento_conta_corrente,vl_lancamento,dm_tipo_lancamento,dm_debito_credito," +
			  		" nm_complemento_historico, nr_documento " +
			  		" from movimentos_contas_correntes " +
			  		" where oid_conta=238 " +
			  		" and dm_tipo_lancamento='L' ";
//
//		  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
//		      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
//		      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
//		    sql += " and Notas_Fiscais.oid_pessoa_destinatario = '" + ed.getOid_Pessoa () + "'";
//		  }
		  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
		    sql += " and dt_movimento_conta_corrente >= '" + ed.getData_Emissao_Inicial () +
		        "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
		    sql += " and dt_movimento_conta_corrente <= '" + ed.getData_Emissao_Final () +
		        "'";
		  }

		  if(ed.getDM_Relatorio().equals("R")){
		      sql += " order by nm_complemento_historico, dt_movimento_conta_corrente ";
		  } else {
		      sql += " order by dt_movimento_conta_corrente ";
		  }
//System.out.println("DR CTRC >" +sql);
//		  res = this.executasql.executarConsulta (sql.toString());
//
//		  while (res.next ()) {
//			  DiarioED edDiario = new DiarioED();
//
//		      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_movimento_conta_corrente")));
//		      edDiario.setNm_Tipo_Documento("RECEBIMENTO AVULSO nr.: " + res.getString("nr_documento"));
//		      edDiario.setNM_Razao_Social(res.getString ("nm_complemento_historico"));
//		      edDiario.setNr_Duplicata(res.getString("nr_documento"));
//		      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("dt_movimento_conta_corrente")));
//		      edDiario.setVL_Debito(res.getDouble("vl_lancamento"));
//		      if(res.getString ("dm_debito_credito").equals("D"))
//		    	  edDiario.setVL_Credito(res.getDouble("vl_lancamento"));
//		      else
//		    	  edDiario.setVL_Debito(res.getDouble("vl_lancamento"));
//		      edDiario.setOid_Pessoa(cliente);
//		      edDiario.setNr_Remessa("0");
//
//		      list.add(edDiario);
//
//		  }

//REC Duplicatas
		  sql = "select movimentos_contas_correntes.dt_movimento_conta_corrente, movimentos_contas_correntes.vl_lancamento, movimentos_contas_correntes.dm_tipo_lancamento," +
		  		" movimentos_contas_correntes.dm_debito_credito, pessoas.nm_razao_social, duplicatas.nr_duplicata, " +
		  		" movimentos_contas_correntes.nm_complemento_historico, movimentos_contas_correntes.nr_documento, " +
		  		" movimentos_contas_correntes.oid_historico, duplicatas.oid_pessoa " +
		  		" from movimentos_contas_correntes " +
		  		"      left join duplicatas" +
		  		"           on movimentos_contas_correntes.nr_documento = duplicatas.nr_duplicata" +
		  		"           left join pessoas on duplicatas.oid_pessoa = pessoas.oid_pessoa " +
		  		" where movimentos_contas_correntes.oid_tipo_documento = 15 " +
		  		" and movimentos_contas_correntes.oid_historico = 4 " +
		  		" and movimentos_contas_correntes.oid_conta=239 " +
		  		" and movimentos_contas_correntes.dm_tipo_lancamento='L' ";
//
	  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
	      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
	      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
	    sql += " and duplicatas.oid_pessoa = '" + ed.getOid_Pessoa () + "'";
	  }
	  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
	      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
	      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
	    sql += " and movimentos_contas_correntes.dt_movimento_conta_corrente >= '" + ed.getData_Emissao_Inicial () +
	        "'";
	  }
	  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
	      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
	      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
	    sql += " and movimentos_contas_correntes.dt_movimento_conta_corrente <= '" + ed.getData_Emissao_Final () +
	        "'";
	  }

	  if(ed.getDM_Relatorio().equals("R")){
	      sql += " order by duplicatas.nm_Razao_social, movimentos_contas_correntes.dt_movimento_conta_corrente ";
	  } else {
	      sql += " order by movimentos_contas_correntes.dt_movimento_conta_corrente ";
	  }
//System.out.println("LIQ >" +sql);
	  res = this.executasql.executarConsulta (sql.toString());

	  while (res.next ()) {
		  DiarioED edDiario = new DiarioED();

		  cliente = res.getString("oid_pessoa");

	      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_movimento_conta_corrente")));
	      edDiario.setNm_Tipo_Documento("LIQUIDACAO COBRANCA nr.: " + (JavaUtil.doValida(res.getString("nr_duplicata"))?res.getString("nr_duplicata"):res.getString("nr_documento")+"***"));
	      edDiario.setNM_Razao_Social(JavaUtil.doValida(res.getString("nm_razao_social"))?res.getString("nm_razao_social"):res.getString("nm_complemento_historico")+"***");
	      edDiario.setNr_Duplicata(res.getString("nr_duplicata"));
	      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("dt_movimento_conta_corrente")));
	      edDiario.setVL_Debito(0);
	      edDiario.setVL_Credito(res.getDouble("vl_lancamento"));
	      edDiario.setOid_Pessoa(cliente);
	      edDiario.setNr_Remessa("0");

	      list.add(edDiario);

	  }

//DIRETO CAIXA
	  sql = "select movimentos_contas_correntes.dt_movimento_conta_corrente, movimentos_contas_correntes.vl_lancamento, movimentos_contas_correntes.dm_tipo_lancamento," +
		" movimentos_contas_correntes.dm_debito_credito, pessoas.nm_razao_social, duplicatas.nr_duplicata, " +
		" movimentos_contas_correntes.nm_complemento_historico, movimentos_contas_correntes.nr_documento, " +
		" movimentos_contas_correntes.oid_historico, duplicatas.oid_pessoa, contas_correntes.dm_tipo_conta_corrente " +
		" from movimentos_contas_correntes" +
		"      left join duplicatas" +
  		"           on movimentos_contas_correntes.nr_documento = duplicatas.nr_duplicata" +
  		"           left join pessoas on duplicatas.oid_pessoa = pessoas.oid_pessoa " +
		" , contas_correntes " +
		" where movimentos_contas_correntes.oid_tipo_documento = 15 " +
//		" and movimentos_contas_correntes.oid_historico = 3 " +
		" and movimentos_contas_correntes.oid_historico != 4 " +
		" and movimentos_contas_correntes.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
		" and movimentos_contas_correntes.oid_conta=239 " +
		" and movimentos_contas_correntes.dm_tipo_lancamento='L' " +
//		" and contas_correntes.dm_tipo_conta_corrente = 'U' " +
		"";
//
if (String.valueOf (ed.getOid_Pessoa ()) != null &&
!String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
!String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
sql += " and duplicatas.oid_pessoa = '" + ed.getOid_Pessoa () + "'";
}
if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
!String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
!String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
sql += " and movimentos_contas_correntes.dt_movimento_conta_corrente >= '" + ed.getData_Emissao_Inicial () +
  "'";
}
if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
!String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
!String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
sql += " and movimentos_contas_correntes.dt_movimento_conta_corrente <= '" + ed.getData_Emissao_Final () +
  "'";
}

if(ed.getDM_Relatorio().equals("R")){
sql += " order by duplicatas.nm_Razao_social, movimentos_contas_correntes.dt_movimento_conta_corrente ";
} else {
sql += " order by movimentos_contas_correntes.dt_movimento_conta_corrente ";
}
//System.out.println("LIQ >" +sql);
res = this.executasql.executarConsulta (sql.toString());

while (res.next ()) {
DiarioED edDiario = new DiarioED();

cliente = res.getString("oid_pessoa");

edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_movimento_conta_corrente")));
edDiario.setNm_Tipo_Documento("LIQUIDACAO COBRANCA nr.: " + (JavaUtil.doValida(res.getString("nr_duplicata"))?res.getString("nr_duplicata"):res.getString("nr_documento")+"***"));
edDiario.setNM_Razao_Social(JavaUtil.doValida(res.getString("nm_razao_social"))?res.getString("nm_razao_social"):res.getString("nm_complemento_historico")+"***");
edDiario.setNr_Duplicata(res.getString("nr_duplicata"));
edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("dt_movimento_conta_corrente")));
edDiario.setVL_Debito(0);
edDiario.setVL_Credito(res.getDouble("vl_lancamento"));
edDiario.setOid_Pessoa(cliente);
edDiario.setNr_Remessa("0");

list.add(edDiario);

}

//ESTORNOS
	  sql = "select Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.oid_duplicata, movimentos_Duplicatas.dt_movimento, duplicatas.vl_duplicata, " +
		" sum( (movimentos_Duplicatas.vl_credito+movimentos_Duplicatas.vl_debito) ) as vl_estorno, Duplicatas.Oid_Pessoa, Pessoas.nm_razao_social " +
      " from Duplicatas, pessoas, movimentos_duplicatas " +
      " where Duplicatas.oid_duplicata = movimentos_Duplicatas.oid_duplicata " +
      " AND Duplicatas.dm_Situacao = 'C' " +
      " and Duplicatas.oid_pessoa = Pessoas.oid_pessoa " +
      " AND movimentos_Duplicatas.dm_estornado is null " +
      " AND movimentos_Duplicatas.oid_tipo_instrucao = 23 ";

	  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
	      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
	      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
	    sql += " and Duplicatas.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
	  }
	  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
	      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
	      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
	    sql += " and movimentos_duplicatas.dt_movimento >= '" + ed.getData_Emissao_Inicial () +
	        "'";
	  }
	  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
	      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
	      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
	    sql += " and movimentos_duplicatas.dt_movimento <= '" + ed.getData_Emissao_Final () +
	        "'";
	  }
	  sql += " group by Duplicatas.oid_pessoa, dt_movimento, Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.oid_duplicata, Pessoas.nm_razao_social, duplicatas.vl_duplicata ";
	  if (ed.getDM_Relatorio ().equals ("R")) {
	    sql += " order by Duplicatas.oid_pessoa, dt_movimento ";
	  }
	  else {
	    sql += " order by dt_movimento ";
	  }
//System.out.println("Est FAT >" +sql);
	  res = this.executasql.executarConsulta (sql.toString());

	  while (res.next ()) {
		  DiarioED edDiario = new DiarioED();

		  cliente = res.getString("oid_pessoa");

	      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_movimento")));
	      edDiario.setNm_Tipo_Documento("CANCELAMENTO FATURA nr.: " + res.getString("nr_duplicata"));
	      edDiario.setNM_Razao_Social(res.getString ("nm_razao_social"));
	      edDiario.setNr_Duplicata(res.getString("nr_duplicata"));
	      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("dt_movimento")));
	      edDiario.setVL_Debito(0);
	      edDiario.setVL_Credito(res.getDouble("vl_duplicata"));
	      edDiario.setOid_Pessoa(cliente);
	      edDiario.setNr_Remessa("0");

	      list.add(edDiario);

	  }


			  util.closeResultset(res);
			  util.closeResultset(rs);

			}
			catch (Exception e) {
			  e.printStackTrace ();
			  throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
			                      "geraDiario_Razao_Clientes_FAT()");
			}

			//return toReport;
			return list;

		}

	  public ArrayList getDadosDiario_Razao_Clientes_FATOLD(DiarioED edComp) throws
	  Excecoes {

			String sql = null , sqlBusca = null;
			ArrayList list = new ArrayList ();
			ArrayList toReport = new ArrayList ();
			DiarioED ed = (DiarioED) edComp;
			double saldo_anterior = 0, vl_cot_pgto = 0, vl_deduzibles = 0;
			double saldo = 0, variacao = 0;
			String cliente = "" , clieAux = "", dt_variacao = "";

			try {
				//int delAux = this.executasql.executarUpdate("delete from auxiliar1");

			  //long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).toString ().substring (6 , 12)).longValue ());

			  sql = "select Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.vl_duplicata, Duplicatas.dt_vencimento, " +
			  		" Duplicatas.oid_duplicata, Pessoas.nr_cnpj_cpf, " +
			        " Duplicatas.oid_pessoa, Pessoas.nm_razao_social, Duplicatas.oid_moeda " +
			        " from Duplicatas, pessoas where " +
			        " Duplicatas.oid_pessoa = Pessoas.oid_pessoa ";

			  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
			    sql += " and Duplicatas.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
			    sql += " and Duplicatas.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
			        "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
			    sql += " and Duplicatas.dt_Emissao <= '" + ed.getData_Emissao_Final () +
			        "'";
			  }
			  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
			    sql += " and Duplicatas.nr_documento = '" + ed.getNr_Proximo_Numero () + "'";
			  }
			  if (ed.getDM_Relatorio ().equals ("R")) {
			    sql += " order by Duplicatas.oid_pessoa ";
			  }
			  else {
			    sql += " order by Duplicatas.dt_Emissao ";
			  }

			  Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();

			  ResultSet rs = null;
			  ResultSet res = null;
//	System.out.println("DIARIO FAT >" +sql);
			  res = this.executasql.executarConsulta (sql.toString ());

			  while (res.next ()) {

				  DiarioED edDiario = new DiarioED();

			      cliente = res.getString ("oid_pessoa");

			      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_Emissao")));
			      edDiario.setNm_Tipo_Documento("EMISSAO FATURA nr.: " + res.getString("nr_duplicata"));
			      edDiario.setNM_Razao_Social(res.getString ("nm_razao_social") + " (" + res.getString ("nr_cnpj_cpf") + ")");
			      edDiario.setNr_Duplicata(res.getString("nr_duplicata"));
			      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("dt_vencimento")));
			      if (JavaUtil.doValida(res.getString("oid_moeda")) && res.getInt("oid_moeda") != parametro_FixoED.getOID_Moeda_Padrao()) {
			    	  edDiario.setVL_Debito(convertToReal(res.getString("dt_Emissao"), res.getDouble("vl_duplicata") , res.getInt("oid_moeda")));
			      } else edDiario.setVL_Debito(res.getDouble("vl_duplicata"));
			      edDiario.setVL_Credito(0.0);
			      edDiario.setOid_Pessoa(cliente);
			      edDiario.setNr_Remessa("0");

			      list.add(edDiario);
			      clieAux = cliente;

			  }

//REC AVULSO
			  sql = "select dt_movimento_conta_corrente,vl_lancamento,dm_tipo_lancamento,dm_debito_credito," +
			  		" nm_complemento_historico, nr_documento " +
			  		" from movimentos_contas_correntes " +
			  		" where oid_conta=238 " +
			  		" and dm_tipo_lancamento='L' ";
//
//		  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
//		      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
//		      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
//		    sql += " and Notas_Fiscais.oid_pessoa_destinatario = '" + ed.getOid_Pessoa () + "'";
//		  }
		  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
		    sql += " and dt_movimento_conta_corrente >= '" + ed.getData_Emissao_Inicial () +
		        "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
		    sql += " and dt_movimento_conta_corrente <= '" + ed.getData_Emissao_Final () +
		        "'";
		  }

		  if(ed.getDM_Relatorio().equals("R")){
		      sql += " order by nm_complemento_historico, dt_movimento_conta_corrente ";
		  } else {
		      sql += " order by dt_movimento_conta_corrente ";
		  }
//System.out.println("DR CTRC >" +sql);
		  res = this.executasql.executarConsulta (sql.toString());

		  while (res.next ()) {
			  DiarioED edDiario = new DiarioED();

		      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_movimento_conta_corrente")));
		      edDiario.setNm_Tipo_Documento("RECEBIMENTO AVULSO nr.: " + res.getString("nr_documento"));
		      edDiario.setNM_Razao_Social(res.getString ("nm_complemento_historico"));
		      edDiario.setNr_Duplicata(res.getString("nr_documento"));
		      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("dt_movimento_conta_corrente")));
		      edDiario.setVL_Debito(res.getDouble("vl_lancamento"));
		      if(res.getString ("dm_debito_credito").equals("D"))
		    	  edDiario.setVL_Credito(res.getDouble("vl_lancamento"));
		      else
		    	  edDiario.setVL_Debito(res.getDouble("vl_lancamento"));
		      edDiario.setOid_Pessoa(cliente);
		      edDiario.setNr_Remessa("0");

		      list.add(edDiario);

		  }

			  //Movimentos
			  sql = "select Movimentos_Duplicatas.dt_movimento, Movimentos_Duplicatas.vl_debito, Movimentos_Duplicatas.vl_credito, Movimentos_Duplicatas.vl_cotacao_pagamento, " +
			      " Duplicatas.nr_duplicata, Duplicatas.dt_vencimento, tipos_instrucoes.nm_tipo_instrucao, Duplicatas.oid_duplicata, Movimentos_Duplicatas.vl_variacao_cambial, " +
			      " Duplicatas.dt_emissao, Duplicatas.vl_duplicata, tipos_instrucoes.oid_tipo_instrucao, Movimentos_Duplicatas.nr_sequencia_duplicata, " +
			      " Pessoas.nr_cnpj_cpf, " +
			      " Duplicatas.oid_pessoa, Pessoas.nm_razao_social, Duplicatas.oid_moeda from Movimentos_Duplicatas, Duplicatas, pessoas, tipos_instrucoes" +
			      " where movimentos_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata " +
			      " AND Duplicatas.oid_pessoa = Pessoas.oid_pessoa " +
			      " AND movimentos_Duplicatas.oid_tipo_instrucao = tipos_instrucoes.oid_tipo_instrucao " +
			      " AND tipos_instrucoes.dm_diario_razao = 'S' ";

			  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
			    sql += " and Duplicatas.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
			    sql += " and movimentos_Duplicatas.dt_movimento >= '" +
			        ed.getData_Emissao_Inicial () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
			    sql += " and movimentos_Duplicatas.dt_movimento <= '" +
			        ed.getData_Emissao_Final () + "'";
			  }
			  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
			    sql += " and Duplicatas.nr_documento = '" + ed.getNr_Proximo_Numero () + "'";
			  }
			  if (ed.getDM_Relatorio ().equals ("R")) {
			    sql += " order by Duplicatas.oid_pessoa, Movimentos_Duplicatas.nr_sequencia_duplicata desc ";
			  }
			  else {
			    sql += " order by Movimentos_Duplicatas.dt_movimento, Movimentos_Duplicatas.nr_sequencia_duplicata desc ";
			  }
			  res = null;
//	System.out.println("DIARIO FAT >" +sql);
			  res = this.executasql.executarConsulta (sql.toString ());

			  while (res.next ()) {

				  variacao = 0;
				  double debito = 0;
				  double credito = 0;

				  DiarioED edDiario = new DiarioED();

			      cliente = res.getString ("oid_pessoa");

			      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_movimento")));
			      edDiario.setNm_Tipo_Documento(res.getString("nm_tipo_instrucao").trim() + " - Fat. nr.: " + res.getString("nr_duplicata") + " - VCTO: " + dataFormatada.getDT_FormataData(res.getString("dt_vencimento")));
			      edDiario.setNM_Razao_Social(res.getString ("nm_razao_social") + " (" + res.getString ("nr_cnpj_cpf") + ")");
			      edDiario.setNr_Duplicata(res.getString("nr_duplicata"));
			      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("dt_vencimento")));
			      if(JavaUtil.doValida(res.getString("nr_sequencia_duplicata")) && res.getString("nr_sequencia_duplicata").equals("9"))
				    	vl_cot_pgto = res.getDouble ("vl_cotacao_pagamento");
			      if (JavaUtil.doValida (res.getString ("oid_moeda"))) {
				    	if(vl_cot_pgto > 0){
				    		debito = res.getDouble("vl_debito") * vl_cot_pgto;
			    			credito = res.getDouble("vl_credito") * vl_cot_pgto;
				    	} else {
				    		debito = convertToReal(res.getString("dt_movimento") , res.getDouble("vl_debito") , res.getInt("oid_moeda"));
				    		credito = convertToReal(res.getString("dt_movimento") , res.getDouble("vl_credito") , res.getInt("oid_moeda"));
				    	}
				    }
				    else {
				      debito = res.getDouble("vl_debito");
				      credito = res.getDouble("vl_credito");
				    }
			      edDiario.setVL_Debito(debito);
			      edDiario.setVL_Credito(credito);
			      edDiario.setOid_Pessoa(cliente);
			      edDiario.setNr_Remessa(res.getString("nr_sequencia_duplicata"));
			      list.add(edDiario);

			      if(res.getDouble("vl_variacao_cambial")!=0){
			    	  edDiario = new DiarioED();
				      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_movimento")));

				      edDiario.setNM_Razao_Social(res.getString ("nm_razao_social") + " (" + res.getString ("nr_cnpj_cpf") + ")");
				      edDiario.setNr_Duplicata(res.getString("nr_duplicata"));
				      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("dt_vencimento")));
				      if(res.getDouble("vl_variacao_cambial")>0){
					      edDiario.setVL_Credito(res.getDouble("vl_variacao_cambial"));
					      edDiario.setNm_Tipo_Documento("VARIACAO CAMBIAL ATIVA" + " - Fat. nr.: " + res.getString("nr_duplicata"));
				      } else {
				    	  edDiario.setVL_Debito(res.getDouble("vl_variacao_cambial")*-1);
					      edDiario.setNm_Tipo_Documento("VARIACAO CAMBIAL PASSIVA" + " - Fat. nr.: " + res.getString("nr_duplicata"));
				      }
				      edDiario.setOid_Pessoa(cliente);
				      edDiario.setNr_Remessa("0");
				      list.add(edDiario);
			      }

			      clieAux = cliente;

			  }
			  //Fim Movimentos

//			  Collections.sort (list , new Comparator () {
//		          public int compare (Object o1 , Object o2) {
//		            DiarioED ed1 = (DiarioED) o1;
//		            DiarioED ed2 = (DiarioED) o2;
//		            return ed1.getDt_Emissao().compareTo(ed2.getDt_Emissao());
//		          }
//		        });

			  vl_deduzibles = 0;
			  String fatura_Aux = "";

//			  if (ed.getDM_Relatorio ().equals ("R")) {
//				  Collections.sort (list , new Comparator () {
//			          public int compare (Object o1 , Object o2) {
//			            DiarioED ed1 = (DiarioED) o1;
//			            DiarioED ed2 = (DiarioED) o2;
//			            return ed1.getNM_Razao_Social().compareTo(ed2.getNM_Razao_Social());
//			          }
//			        });
//			  }

//			  clieAux = "";
//			  saldo_anterior = 0;
//			  for(int t=0;t<list.size();t++){
//				  DiarioED edLista = (DiarioED)list.get(t);
//				  cliente = edLista.getOid_Pessoa();
//
//				  if(edLista.getNr_Remessa().equals("6"))
//				    	vl_deduzibles = edLista.getVL_Credito();
//				  else if(!edLista.getNr_Duplicata().equals(fatura_Aux))
//				    	vl_deduzibles = 0;
//
//				  if (ed.getDM_Relatorio ().equals ("R")) {
//					  if (!cliente.equals (clieAux)) {
//						  saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), "FAT");
//						  edLista.setVL_Saldo_Inicial (saldo_anterior);
//						  saldo = saldo_anterior;
//					  }
//				  }
//
//				  if(vl_deduzibles > 0 && edLista.getNr_Remessa().equals("9")){
//				    	edLista.setVL_Credito(edLista.getVL_Credito() - vl_deduzibles);
//				  } else {
//				    	edLista.setVL_Credito(edLista.getVL_Credito());
//				  }
//
//				  saldo = saldo - edLista.getVL_Credito() + edLista.getVL_Debito();
//				  edLista.setVL_Saldo (saldo);
//				  toReport.add(edLista);
//				  clieAux = cliente;
//				  fatura_Aux = edLista.getNr_Duplicata();
//			  }

			  util.closeResultset(res);
			  util.closeResultset(rs);

			}
			catch (Exception e) {
			  e.printStackTrace ();
			  throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
			                      "geraDiario_Razao_Clientes_FAT()");
			}

			//return toReport;
			return list;

		}

	  public ArrayList getDadosDiario_Razao_Clientes_CRT(DiarioED edComp) throws
	  Excecoes {

			String sql = null , sqlBusca = null;
			ArrayList list = new ArrayList ();
			ArrayList toReport = new ArrayList ();
			DiarioED ed = (DiarioED) edComp;
			double saldo_anterior = 0;
			double saldo = 0, variacao = 0;
			String cliente = "" , clieAux = "";

			try {
				//int delAux = this.executasql.executarUpdate("delete from auxiliar1");

			  //long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).toString ().substring (6 , 12)).longValue ());

			  // CRT
			  sql = "select Conhecimentos.dt_emissao, Conhecimentos.nr_conhecimento, Conhecimentos.oid_conhecimento, " +
			  		" (Conhecimentos.vl_gasto_remetente1 + Conhecimentos.vl_gasto_remetente2 + Conhecimentos.vl_gasto_remetente3 + Conhecimentos.vl_gasto_remetente4 +" +
			  		" Conhecimentos.vl_gasto_destinatario1 + Conhecimentos.vl_gasto_destinatario2 + Conhecimentos.vl_gasto_destinatario3 + Conhecimentos.vl_gasto_destinatario4) as vl_total_frete, " +
			  		" fat.vl_faturar, Conhecimentos.oid_pessoa_cotacao, Pessoas.nm_razao_social " +
			  		" ,Pessoas.nr_cnpj_cpf " +
			  		" from Conhecimentos_Internacionais Conhecimentos, pessoas, conhecimentos_faturamentos fat " +
			  		" where fat.oid_conhecimento = Conhecimentos.oid_conhecimento and " +
			  		" (Conhecimentos.dm_situacao = 'L' or Conhecimentos.dm_situacao = 'F') " +
			  		" and Conhecimentos.oid_pessoa_cotacao = Pessoas.oid_pessoa ";

			  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
			    sql += " and Conhecimentos.Oid_Pessoa_cotacao = '" + ed.getOid_Pessoa () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
			    sql += " and Conhecimentos.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
			        "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
			    sql += " and Conhecimentos.dt_Emissao <= '" + ed.getData_Emissao_Final () +
			        "'";
			  }
			  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
			    sql += " and Conhecimentos.nr_Conhecimento = '" + ed.getNr_Proximo_Numero () + "'";
			  }
			  if(ed.getDM_Relatorio().equals("R")){
			      sql += " order by oid_pessoa_cotacao, Conhecimentos.nr_conhecimento ";
			  } else {
			      sql += " order by dt_Emissao, Conhecimentos.nr_conhecimento ";
			  }

			  Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();
			  ResultSet rs = null;
//	System.out.println("DR CRT>"+sql);
			  ResultSet res = this.executasql.executarConsulta (sql.toString());

			  double vl_faturar = 0;
			  while (res.next ()) {
				  DiarioED edDiario = new DiarioED();

				  cliente = res.getString ("oid_pessoa_cotacao");

				  vl_faturar = res.getDouble("vl_faturar");

				  edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString("dt_Emissao")));
				  edDiario.setNm_Tipo_Documento("EMISSAO INTERNACIONAL - CRT nr.: " + res.getString("nr_conhecimento"));
				  edDiario.setNM_Razao_Social(res.getString ("nm_razao_social") + " (" + res.getString ("nr_cnpj_cpf") + ")");
				  edDiario.setNr_Duplicata(res.getString("nr_conhecimento"));
				  edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData(res.getString("dt_Emissao")));
				  edDiario.setVL_Debito(convertToReal(res.getString("dt_Emissao"), vl_faturar, parametro_FixoED.getOID_Moeda_Dollar()));
				  edDiario.setVL_Credito(0.0);
				  edDiario.setOid_Nota_Fiscal(res.getString("oid_conhecimento"));
				  edDiario.setOid_Pessoa(cliente);

				  clieAux = cliente;
				  list.add(edDiario);
			  }

			  // Faturamento do CRT
			  sql = "select Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.oid_duplicata, Conhecimentos_Internacionais.dt_emissao as emissao_CRT, " +
			  		" Conhecimentos_Internacionais.oid_Conhecimento, Conhecimentos_Internacionais.nr_Conhecimento, " +
			  		" (Conhecimentos_Internacionais.vl_gasto_remetente1 + Conhecimentos_Internacionais.vl_gasto_remetente2 + " +
			  		" Conhecimentos_Internacionais.vl_gasto_remetente3 + Conhecimentos_Internacionais.vl_gasto_remetente4 +" +
			  		" Conhecimentos_Internacionais.vl_gasto_destinatario1 + Conhecimentos_Internacionais.vl_gasto_destinatario2 + " +
			  		" Conhecimentos_Internacionais.vl_gasto_destinatario3 + Conhecimentos_Internacionais.vl_gasto_destinatario4) as vl_total_frete, " +
			  		" Pessoas.nr_cnpj_cpf, " +
			  		" fat.vl_faturar, Conhecimentos_Internacionais.oid_pessoa_cotacao, Pessoas.nm_razao_social " +
			        " from Duplicatas, Origens_Duplicatas, Conhecimentos_Internacionais, pessoas, conhecimentos_faturamentos fat " +
			        " where Duplicatas.oid_duplicata = Origens_Duplicatas.oid_duplicata " +
			        " AND fat.oid_conhecimento = Conhecimentos_Internacionais.oid_conhecimento " +
			        " AND Origens_Duplicatas.oid_Conhecimento = Conhecimentos_Internacionais.oid_Conhecimento " +
			        " AND Origens_Duplicatas.dm_Situacao != 'E' " +
			        " and Conhecimentos_Internacionais.oid_pessoa_cotacao = Pessoas.oid_pessoa";

			  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
			    sql += " and Duplicatas.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
			    sql += " and Duplicatas.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
			        "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
			    sql += " and Duplicatas.dt_Emissao <= '" + ed.getData_Emissao_Final () +
			        "'";
			  }
			  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
			    sql += " and Conhecimentos_Internacionais.nr_Conhecimento = '" + ed.getNr_Proximo_Numero () + "'";
			  }
			  if (ed.getDM_Relatorio ().equals ("R")) {
			    sql += " order by Duplicatas.oid_pessoa, Conhecimentos_Internacionais.nr_conhecimento ";
			  }
			  else {
			    sql += " order by dt_Emissao, Conhecimentos_Internacionais.nr_conhecimento ";
			  }
//			System.out.println("FAT CRT > "+sql);
//			  res = this.executasql.executarConsulta (sql.toString ());
//
//			  vl_faturar = 0;
//			  while (res.next ()) {
//			      variacao = 0;
//			      DiarioED edDiario = new DiarioED();
//
//			      cliente = res.getString ("oid_pessoa_cotacao");
//			      vl_faturar = res.getDouble("vl_faturar");
//
//			      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_Emissao")));
//			      edDiario.setNm_Tipo_Documento("FATURAMENTO - CRT nr.: " + res.getString("nr_conhecimento"));
//			      edDiario.setNM_Razao_Social(res.getString ("nm_razao_social"));
//			      edDiario.setNr_Duplicata(res.getString("nr_Conhecimento"));
//			      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("emissao_CRT")));
//			      edDiario.setVL_Debito(0.0);
//			      edDiario.setVL_Credito (convertToReal(res.getString("dt_Emissao"), vl_faturar, parametro_FixoED.getOID_Moeda_Dollar()));
//			      edDiario.setOid_Pessoa(cliente);
//
//			      list.add (edDiario);
//
//			      variacao = convertToReal(res.getString("dt_emissao"), vl_faturar, parametro_FixoED.getOID_Moeda_Dollar()) - convertToReal(res.getString("emissao_CRT"), vl_faturar, parametro_FixoED.getOID_Moeda_Dollar());
//
//			      if(variacao < 0 || variacao > 0){
//			    	  edDiario = new DiarioED();
//				      edDiario.setDt_Emissao(dataFormatada.getDT_FormataData(res.getString ("dt_Emissao")));
//				      edDiario.setNM_Razao_Social(res.getString ("nm_razao_social"));
//				      edDiario.setNr_Duplicata(res.getString("nr_Conhecimento"));
//				      edDiario.setDt_Vencimento(dataFormatada.getDT_FormataData (res.getString ("emissao_CRT")));
//				      edDiario.setOid_Pessoa(cliente);
//			    	  edDiario.setVL_Debito(variacao);
//			    	  edDiario.setVL_Credito(0.0);
//			    	  edDiario.setNm_Tipo_Documento("VARIA��O CAMBIAL - CRT nr.: " + res.getString("nr_conhecimento"));
//			    	  list.add(edDiario);
//			      }
//
//			      clieAux = cliente;
//
//			  }

			  if (ed.getDM_Relatorio ().equals ("R")) {
//				  Collections.sort (list , new Comparator () {
////			          public int compare (Object o1 , Object o2) {
////			            DiarioED ed1 = (DiarioED) o1;
////			            DiarioED ed2 = (DiarioED) o2;
////			            return ed1.getNM_Razao_Social().compareTo(ed2.getNM_Razao_Social());
////			          }
////			        });

//				  clieAux = "";
//				  saldo_anterior = 0;
//				  for(int t=0;t<list.size();t++){
//					  DiarioED edLista = (DiarioED)list.get(t);
//					  cliente = edLista.getOid_Pessoa();
//					  if (!cliente.equals (clieAux)) {
//						  saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), "CRT");
//						  edLista.setVL_Saldo_Inicial (saldo_anterior);
//					      saldo = saldo_anterior;
//					  }
//					  saldo = saldo - edLista.getVL_Credito() + edLista.getVL_Debito();
//					  edLista.setVL_Saldo (saldo);
//					  toReport.add(edLista);
//					  clieAux = cliente;
//				  }
//				  list = new ArrayList(toReport);
			  }


			  util.closeResultset(res);
			  util.closeResultset(rs);

			}
			catch (Exception e) {
			  e.printStackTrace ();
			  throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
			                      "geraDiario_Razao_Clientes_CRT()");
			}

			return list;

		}

	  public ArrayList getDadosDiario_Razao_Clientes_CTRC(DiarioED edComp) throws
	  Excecoes {

			String sql = null , sqlBusca = null;
			ArrayList list = new ArrayList ();
			DiarioED ed = (DiarioED) edComp;
			double saldo_anterior = 0;
			double saldo = 0, variacao = 0;
			String cliente = "" , clieAux = "";

			try {
				//int delAux = this.executasql.executarUpdate("delete from auxiliar1");

			  long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).
			                            toString ().substring (6 , 12)).longValue ());
// CONHECIMENTOS
			  sql = "select Conhecimentos.dt_emissao, Conhecimentos.nr_conhecimento, Conhecimentos.oid_conhecimento, Conhecimentos.vl_total_frete, " +
		  		" Conhecimentos.oid_pessoa_pagador, Pessoas.nm_razao_social, Pessoas.nr_cnpj_cpf, conhecimentos.dm_tipo_documento, conhecimentos.nr_recibo " +
		  		" from Conhecimentos, pessoas " +
		  		" where Conhecimentos.dm_impresso = 'S' and Conhecimentos.vl_total_frete > 0.01 and Conhecimentos.dm_situacao != 'C' " +
		  		" and Conhecimentos.oid_pessoa_pagador = Pessoas.oid_pessoa " +
		  		" ";

			  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
			    sql += " and Conhecimentos.Oid_Pessoa_pagador = '" + ed.getOid_Pessoa () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
			    sql += " and Conhecimentos.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
			        "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
			    sql += " and Conhecimentos.dt_Emissao <= '" + ed.getData_Emissao_Final () +
			        "'";
			  }
			  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
			    sql += " and Conhecimentos.nr_Conhecimento = '" + ed.getNr_Proximo_Numero () + "'";
			  }
			  if(ed.getDM_Relatorio().equals("R")){
			      sql += " order by oid_pessoa_pagador, dt_emissao ";
			  } else {
			      sql += " order by dt_Emissao ";
			  }
//	System.out.println("DR CTRC >" +sql);
			  Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();
			  ResultSet res = this.executasql.executarConsulta (sql.toString());

			  while (res.next ()) {
				  if(("M".equals(res.getString("dm_tipo_documento")) && JavaUtil.doValida(res.getString("nr_recibo")))
						  || (!"M".equals(res.getString("dm_tipo_documento")))){
					  Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();

					    cliente = res.getString ("oid_pessoa_pagador");
//					    if (ed.getDM_Relatorio ().equals ("R")) {
//					        saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
//					        edAuxiliar1.setVL_Classifica3 (saldo_anterior);
//					    }

				        edAuxiliar1.setNM_Tabela ("Conhecimentos");
				        edAuxiliar1.setOID_Tabela (res.getString ("oid_conhecimento"));
				        edAuxiliar1.setNM_Classifica1(res.getString ("dt_Emissao"));
				        edAuxiliar1.setNM_Classifica2("EMISSAO CTRC nr.: " + res.getString("nr_conhecimento"));
				        edAuxiliar1.setNM_Classifica3(res.getString ("nm_razao_social") + " (" + res.getString ("nr_cnpj_cpf") + ")");
				        edAuxiliar1.setNM_Classifica6(res.getString ("nr_conhecimento"));
				        edAuxiliar1.setNM_Classifica5(res.getString ("dt_Emissao"));
				        edAuxiliar1.setNM_Classifica4(res.getString ("nr_conhecimento"));
				        edAuxiliar1.setVL_Classifica1(res.getDouble("vl_total_frete"));
				        edAuxiliar1.setVL_Classifica2(0.0);

				        edAuxiliar1.setNM_Classifica7(cliente);

				        edAuxiliar1.setNR_Sort (Nr_Sort);

				        Auxiliar1RN.inclui (edAuxiliar1);

				        clieAux = cliente;
				  }

			  }
// FATURAMENTO
			  sql = "select Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.oid_duplicata, " +
		  		" duplicatas.oid_pessoa, Pessoas.nm_razao_social, duplicatas.vl_duplicata " +
		        " from Duplicatas, pessoas " +
		        " where 1 = 1 " +
//		        " AND Origens_Duplicatas.dm_Situacao != 'E' " +
		        " and Duplicatas.oid_pessoa = Pessoas.oid_pessoa ";

			  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
			    sql += " and Duplicatas.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
			    sql += " and Duplicatas.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
			        "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
			    sql += " and Duplicatas.dt_Emissao <= '" + ed.getData_Emissao_Final () +
			        "'";
			  }
			  if (ed.getDM_Relatorio ().equals ("R")) {
			    sql += " order by Duplicatas.oid_pessoa, dt_Emissao ";
			  }
			  else {
			    sql += " order by dt_Emissao ";
			  }
//System.out.println("FAT > " +sql);
			  res = this.executasql.executarConsulta (sql.toString ());

			  while (res.next ()) {

			      Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();

			      cliente = res.getString ("oid_pessoa");
//			      if (ed.getDM_Relatorio ().equals ("R")) {
//			          saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
//			          edAuxiliar1.setVL_Classifica3 (saldo_anterior);
//			      }

			      edAuxiliar1.setNM_Tabela ("Duplicatas");
			      edAuxiliar1.setOID_Tabela (res.getString ("oid_duplicata"));
			      edAuxiliar1.setNM_Classifica1(res.getString ("dt_Emissao"));
			      edAuxiliar1.setNM_Classifica2("EMISSAO FATURA nr.: " + res.getString("nr_duplicata"));
			      edAuxiliar1.setNM_Classifica3(res.getString ("nm_razao_social"));
			      edAuxiliar1.setNM_Classifica4(res.getString ("nr_duplicata"));
			      edAuxiliar1.setNM_Classifica5(res.getString ("dt_Emissao"));
			      edAuxiliar1.setVL_Classifica2(res.getDouble("vl_duplicata"));
//			      edAuxiliar1.setVL_Classifica2(res.getDouble("vl_total_frete"));
			      edAuxiliar1.setVL_Classifica1(0.0);

			      edAuxiliar1.setNR_Sort (Nr_Sort);
			      Auxiliar1RN.inclui (edAuxiliar1);

			      clieAux = cliente;

			  }
// ESTORNOS
			  sql = "select Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.oid_duplicata, movimentos_Duplicatas.dt_movimento, duplicatas.vl_duplicata, " +
		  		" sum( (movimentos_Duplicatas.vl_credito+movimentos_Duplicatas.vl_debito) ) as vl_estorno, Duplicatas.Oid_Pessoa, Pessoas.nm_razao_social " +
		        " from Duplicatas, pessoas, movimentos_duplicatas " +
		        " where Duplicatas.oid_duplicata = movimentos_Duplicatas.oid_duplicata " +
		        " AND Duplicatas.dm_Situacao = 'C' " +
		        " and Duplicatas.oid_pessoa = Pessoas.oid_pessoa " +
		        " AND movimentos_Duplicatas.dm_estornado is null " +
		        " AND movimentos_Duplicatas.oid_tipo_instrucao = 23 ";

			  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
			    sql += " and Duplicatas.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
			    sql += " and movimentos_duplicatas.dt_movimento >= '" + ed.getData_Emissao_Inicial () +
			        "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
			    sql += " and movimentos_duplicatas.dt_movimento <= '" + ed.getData_Emissao_Final () +
			        "'";
			  }
			  sql += " group by Duplicatas.oid_pessoa, dt_movimento, Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.oid_duplicata, Pessoas.nm_razao_social, duplicatas.vl_duplicata ";
			  if (ed.getDM_Relatorio ().equals ("R")) {
			    sql += " order by Duplicatas.oid_pessoa, dt_movimento ";
			  }
			  else {
			    sql += " order by dt_movimento ";
			  }
//System.out.println("Est FAT >" +sql);
			  res = this.executasql.executarConsulta (sql.toString());

			  while (res.next ()) {
			    Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();

			    cliente = res.getString ("oid_pessoa");
//			    if (ed.getDM_Relatorio ().equals ("R")) {
//			        saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
//			        edAuxiliar1.setVL_Classifica3 (saldo_anterior);
//			    }

		        edAuxiliar1.setNM_Tabela ("Movimentos_Duplicatas");
		        edAuxiliar1.setOID_Tabela (res.getString ("oid_duplicata"));
		        edAuxiliar1.setNM_Classifica1(res.getString ("dt_movimento"));
		        edAuxiliar1.setNM_Classifica2("CANCELAMENTO FATURA nr.: " + res.getString("nr_duplicata"));
		        edAuxiliar1.setNM_Classifica3(res.getString ("nm_razao_social"));
		        edAuxiliar1.setNM_Classifica5(res.getString ("dt_movimento"));
		        edAuxiliar1.setNM_Classifica4(res.getString ("nr_duplicata"));
		        edAuxiliar1.setVL_Classifica1(res.getDouble("vl_duplicata"));
//		        edAuxiliar1.setVL_Classifica1(res.getDouble("vl_estorno"));
		        edAuxiliar1.setVL_Classifica2(0.0);

		        edAuxiliar1.setNR_Sort (Nr_Sort);
		        Auxiliar1RN.inclui (edAuxiliar1);

		        clieAux = cliente;
			  }

			  sql = " select * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
			  sql += " order by auxiliar1.nm_classifica3 , auxiliar1.nm_classifica1 , auxiliar1.nm_classifica5  ";
			  res = null;
			  res = this.executasql.executarConsulta (sql.toString ());

			  while (res.next ()) {
			    DiarioED edDiario = new DiarioED();

			    cliente = res.getString ("nm_classifica7");
//			    if (ed.getDM_Relatorio ().equals ("R")) {
//			      if (!cliente.equals (clieAux)) {
//			    	  saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), "CTRC");
//			    	  //saldo_anterior = res.getDouble ("vl_classifica3");
//			        edDiario.setVL_Saldo_Inicial (saldo_anterior);
//			        saldo = saldo_anterior;
//			      }
//			    }

			    edDiario.setOid_Pessoa(cliente);
			    edDiario.setDt_Emissao (dataFormatada.getDT_FormataData (res.getString ("nm_classifica1")));
			    edDiario.setNm_Tipo_Documento (res.getString ("nm_classifica2"));
			    edDiario.setNM_Razao_Social(res.getString ("nm_classifica3"));
			    edDiario.setNr_Duplicata(res.getString("nm_classifica4"));
			    edDiario.setDt_Vencimento (dataFormatada.getDT_FormataData (res.getString ("nm_classifica5")));
			    edDiario.setVL_Debito (res.getDouble ("vl_classifica1"));
			    edDiario.setVL_Credito (res.getDouble ("vl_classifica2"));
//	System.out.println(edDiario.getNr_Duplicata() + " - " + edDiario.getNM_Razao_Social());
//			    saldo = saldo - res.getDouble ("vl_classifica2") + res.getDouble ("vl_classifica1");
//			    edDiario.setVL_Saldo (saldo);

			    list.add (edDiario);

			    clieAux = cliente;

			  }

			  sql = "delete from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
			  this.executasql.executarUpdate(sql.toString ());
//			  Auxiliar1RN AuxRN = new Auxiliar1RN ();
//			  while (res.next ()) {
//			    Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
//			    edAuxiliar1.setOID_auxiliar1 (new Integer (res.getInt ("oid_auxiliar1")));
//			    AuxRN.deleta (edAuxiliar1);
//			  }

			  util.closeResultset(res);

			}
			catch (Exception e) {
			  e.printStackTrace ();
			  throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
			                      "geraDiario_Razao_Clientes_CTRC()");
			}
			return list;
		}

	  public ArrayList getDadosDiario_Razao_Clientes_CTRCOLD(DiarioED edComp) throws
	  Excecoes {

			String sql = null , sqlBusca = null;
			ArrayList list = new ArrayList ();
			DiarioED ed = (DiarioED) edComp;
			double saldo_anterior = 0;
			double saldo = 0, variacao = 0;
			String cliente = "" , clieAux = "";

			try {
				//int delAux = this.executasql.executarUpdate("delete from auxiliar1");

			  long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).
			                            toString ().substring (6 , 12)).longValue ());
// CONHECIMENTOS
			  sql = "select Conhecimentos.dt_emissao, Conhecimentos.nr_conhecimento, Conhecimentos.oid_conhecimento, Conhecimentos.vl_total_frete, " +
		  		" Conhecimentos.oid_pessoa_pagador, Pessoas.nm_razao_social, Pessoas.nr_cnpj_cpf, conhecimentos.dm_tipo_documento, conhecimentos.nr_recibo " +
		  		" from Conhecimentos, pessoas " +
		  		" where Conhecimentos.dm_impresso = 'S' and Conhecimentos.vl_total_frete > 0.01 and Conhecimentos.dm_situacao != 'C' " +
		  		" and Conhecimentos.oid_pessoa_pagador = Pessoas.oid_pessoa " +
		  		" ";

			  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
			    sql += " and Conhecimentos.Oid_Pessoa_pagador = '" + ed.getOid_Pessoa () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
			    sql += " and Conhecimentos.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
			        "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
			    sql += " and Conhecimentos.dt_Emissao <= '" + ed.getData_Emissao_Final () +
			        "'";
			  }
			  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
			    sql += " and Conhecimentos.nr_Conhecimento = '" + ed.getNr_Proximo_Numero () + "'";
			  }
			  if(ed.getDM_Relatorio().equals("R")){
			      sql += " order by oid_pessoa_pagador, dt_emissao ";
			  } else {
			      sql += " order by dt_Emissao ";
			  }
//	System.out.println("DR CTRC >" +sql);
			  Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();
			  ResultSet res = this.executasql.executarConsulta (sql.toString());

			  while (res.next ()) {
				  if(("M".equals(res.getString("dm_tipo_documento")) && JavaUtil.doValida(res.getString("nr_recibo")))
						  || (!"M".equals(res.getString("dm_tipo_documento")))){
					  Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();

					    cliente = res.getString ("oid_pessoa_pagador");
//					    if (ed.getDM_Relatorio ().equals ("R")) {
//					        saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
//					        edAuxiliar1.setVL_Classifica3 (saldo_anterior);
//					    }

				        edAuxiliar1.setNM_Tabela ("Conhecimentos");
				        edAuxiliar1.setOID_Tabela (res.getString ("oid_conhecimento"));
				        edAuxiliar1.setNM_Classifica1(res.getString ("dt_Emissao"));
				        edAuxiliar1.setNM_Classifica2("EMISSAO NACIONAL - CTRC nr.: " + res.getString("nr_conhecimento"));
				        edAuxiliar1.setNM_Classifica3(res.getString ("nm_razao_social") + " (" + res.getString ("nr_cnpj_cpf") + ")");
				        edAuxiliar1.setNM_Classifica6(res.getString ("nr_conhecimento"));
				        edAuxiliar1.setNM_Classifica5(res.getString ("dt_Emissao"));
				        edAuxiliar1.setNM_Classifica4(res.getString ("nr_conhecimento"));
				        edAuxiliar1.setVL_Classifica1(res.getDouble("vl_total_frete"));
				        edAuxiliar1.setVL_Classifica2(0.0);

				        edAuxiliar1.setNM_Classifica7(cliente);

				        edAuxiliar1.setNR_Sort (Nr_Sort);

				        Auxiliar1RN.inclui (edAuxiliar1);

				        clieAux = cliente;
				  }

			  }
// FATURAMENTO
			  sql = "select Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.oid_duplicata, Conhecimentos.dt_emissao as emissao_CTRC, " +
		  		" Conhecimentos.oid_Conhecimento, Conhecimentos.nr_Conhecimento, Conhecimentos.vl_total_frete, " +
		  		" Conhecimentos.oid_pessoa_pagador, Pessoas.nm_razao_social, duplicatas.vl_duplicata " +
		        " from Duplicatas, Origens_Duplicatas, Conhecimentos, pessoas " +
		        " where Duplicatas.oid_duplicata = Origens_Duplicatas.oid_duplicata " +
		        " AND Origens_Duplicatas.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
//		        " AND Origens_Duplicatas.dm_Situacao != 'E' " +
		        " and Conhecimentos.oid_pessoa_pagador = Pessoas.oid_pessoa ";

			  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
			    sql += " and Duplicatas.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
			    sql += " and Duplicatas.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
			        "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
			    sql += " and Duplicatas.dt_Emissao <= '" + ed.getData_Emissao_Final () +
			        "'";
			  }
			  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
			    sql += " and Conhecimentos.nr_Conhecimento = '" + ed.getNr_Proximo_Numero () + "'";
			  }
			  if (ed.getDM_Relatorio ().equals ("R")) {
			    sql += " order by Duplicatas.oid_pessoa, dt_Emissao ";
			  }
			  else {
			    sql += " order by dt_Emissao ";
			  }
System.out.println("FAT > " +sql);
			  res = this.executasql.executarConsulta (sql.toString ());

			  while (res.next ()) {

			      Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();

			      cliente = res.getString ("oid_pessoa_pagador");
//			      if (ed.getDM_Relatorio ().equals ("R")) {
//			          saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
//			          edAuxiliar1.setVL_Classifica3 (saldo_anterior);
//			      }

			      edAuxiliar1.setNM_Tabela ("Duplicatas");
			      edAuxiliar1.setOID_Tabela (res.getString ("oid_duplicata"));
			      edAuxiliar1.setNM_Classifica1(res.getString ("dt_Emissao"));
			      edAuxiliar1.setNM_Classifica2("FATURAMENTO nr.: " + res.getString("nr_duplicata") +" - CTRC nr.: " + res.getString("nr_conhecimento"));
			      edAuxiliar1.setNM_Classifica3(res.getString ("nm_razao_social"));
			      edAuxiliar1.setNM_Classifica4(res.getString ("nr_Conhecimento"));
			      edAuxiliar1.setNM_Classifica5(res.getString ("emissao_CTRC"));
			      edAuxiliar1.setVL_Classifica2(res.getDouble("vl_total_frete"));
			      edAuxiliar1.setVL_Classifica1(0.0);

			      edAuxiliar1.setNR_Sort (Nr_Sort);
			      Auxiliar1RN.inclui (edAuxiliar1);

			      clieAux = cliente;

			  }
// ESTORNOS
			  sql = "select Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.oid_duplicata, movimentos_Duplicatas.dt_movimento, " +
		  		" sum( (movimentos_Duplicatas.vl_credito+movimentos_Duplicatas.vl_debito) ) as vl_estorno, Duplicatas.Oid_Pessoa, Pessoas.nm_razao_social " +
		        " from Duplicatas, pessoas, movimentos_duplicatas " +
		        " where Duplicatas.oid_duplicata = movimentos_Duplicatas.oid_duplicata " +
		        " AND Duplicatas.dm_Situacao = 'C' " +
		        " and Duplicatas.oid_pessoa = Pessoas.oid_pessoa " +
		        " AND movimentos_Duplicatas.dm_estornado is null " +
		        " AND movimentos_Duplicatas.oid_tipo_instrucao = 23 ";

			  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
			    sql += " and Duplicatas.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
			    sql += " and movimentos_duplicatas.dt_movimento >= '" + ed.getData_Emissao_Inicial () +
			        "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
			    sql += " and movimentos_duplicatas.dt_movimento <= '" + ed.getData_Emissao_Final () +
			        "'";
			  }
			  sql += " group by Duplicatas.oid_pessoa, dt_movimento, Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.oid_duplicata, Pessoas.nm_razao_social ";
			  if (ed.getDM_Relatorio ().equals ("R")) {
			    sql += " order by Duplicatas.oid_pessoa, dt_movimento ";
			  }
			  else {
			    sql += " order by dt_movimento ";
			  }
//System.out.println("Est FAT >" +sql);
			  res = this.executasql.executarConsulta (sql.toString());

			  while (res.next ()) {
			    Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();

			    cliente = res.getString ("oid_pessoa");
//			    if (ed.getDM_Relatorio ().equals ("R")) {
//			        saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
//			        edAuxiliar1.setVL_Classifica3 (saldo_anterior);
//			    }

		        edAuxiliar1.setNM_Tabela ("Movimentos_Duplicatas");
		        edAuxiliar1.setOID_Tabela (res.getString ("oid_duplicata"));
		        edAuxiliar1.setNM_Classifica1(res.getString ("dt_movimento"));
		        edAuxiliar1.setNM_Classifica2("ESTORNO FAT nr.: " + res.getString("nr_duplicata"));
		        edAuxiliar1.setNM_Classifica3(res.getString ("nm_razao_social"));
		        edAuxiliar1.setNM_Classifica5(res.getString ("dt_movimento"));
		        edAuxiliar1.setNM_Classifica4(res.getString ("nr_duplicata"));
		        edAuxiliar1.setVL_Classifica1(res.getDouble("vl_estorno"));
		        edAuxiliar1.setVL_Classifica2(0.0);

		        edAuxiliar1.setNR_Sort (Nr_Sort);
		        Auxiliar1RN.inclui (edAuxiliar1);

		        clieAux = cliente;
			  }

			  sql = " select * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
			  sql += " order by auxiliar1.nm_classifica3 , auxiliar1.nm_classifica1 , auxiliar1.nm_classifica5  ";
			  res = null;
			  res = this.executasql.executarConsulta (sql.toString ());

			  while (res.next ()) {
			    DiarioED edDiario = new DiarioED();

			    cliente = res.getString ("nm_classifica7");
//			    if (ed.getDM_Relatorio ().equals ("R")) {
//			      if (!cliente.equals (clieAux)) {
//			    	  saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), "CTRC");
//			    	  //saldo_anterior = res.getDouble ("vl_classifica3");
//			        edDiario.setVL_Saldo_Inicial (saldo_anterior);
//			        saldo = saldo_anterior;
//			      }
//			    }

			    edDiario.setOid_Pessoa(cliente);
			    edDiario.setDt_Emissao (dataFormatada.getDT_FormataData (res.getString ("nm_classifica1")));
			    edDiario.setNm_Tipo_Documento (res.getString ("nm_classifica2"));
			    edDiario.setNM_Razao_Social(res.getString ("nm_classifica3"));
			    edDiario.setNr_Duplicata(res.getString("nm_classifica4"));
			    edDiario.setDt_Vencimento (dataFormatada.getDT_FormataData (res.getString ("nm_classifica5")));
			    edDiario.setVL_Debito (res.getDouble ("vl_classifica1"));
			    edDiario.setVL_Credito (res.getDouble ("vl_classifica2"));
//	System.out.println(edDiario.getNr_Duplicata() + " - " + edDiario.getNM_Razao_Social());
//			    saldo = saldo - res.getDouble ("vl_classifica2") + res.getDouble ("vl_classifica1");
//			    edDiario.setVL_Saldo (saldo);

			    list.add (edDiario);

			    clieAux = cliente;

			  }

			  sql = "delete from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
			  this.executasql.executarUpdate(sql.toString ());
//			  Auxiliar1RN AuxRN = new Auxiliar1RN ();
//			  while (res.next ()) {
//			    Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
//			    edAuxiliar1.setOID_auxiliar1 (new Integer (res.getInt ("oid_auxiliar1")));
//			    AuxRN.deleta (edAuxiliar1);
//			  }

			  util.closeResultset(res);

			}
			catch (Exception e) {
			  e.printStackTrace ();
			  throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
			                      "geraDiario_Razao_Clientes_CTRC()");
			}
			return list;
		}

	  public ArrayList getDadosDiario_Razao_Clientes_NF(DiarioED edComp) throws
	  Excecoes {

			String sql = null , sqlBusca = null;
			ArrayList list = new ArrayList ();
			DiarioED ed = (DiarioED) edComp;
			double saldo_anterior = 0;
			double saldo = 0, variacao = 0;
			String cliente = "" , clieAux = "";

			try {
				//int delAux = this.executasql.executarUpdate("delete from auxiliar1");

			  long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).
			                            toString ().substring (6 , 12)).longValue ());
// NOTAS FISCAIS
			  sql = "select Notas_Fiscais.dt_emissao, Notas_Fiscais.nr_Nota_Fiscal, Notas_Fiscais.oid_nota_fiscal, " +
			  		"(Notas_Fiscais.vl_liquido_nota_fiscal-Notas_Fiscais.vl_inss) as vl_faturar, " +
		  		" Notas_Fiscais.oid_pessoa_destinatario, Pessoas.nm_razao_social, Pessoas.nr_cnpj_cpf from Notas_Fiscais, pessoas " +
		  		" where Notas_Fiscais.dm_impresso = 'S' " +
		  		" and Notas_Fiscais.DM_Tipo_Nota_Fiscal = 'S' " +
		  		" and (Notas_Fiscais.dm_situacao = 'F' or Notas_Fiscais.dm_situacao = 'A') " +
		  		" and Notas_Fiscais.dm_finalizado = 'F'" +
		  		" and Notas_Fiscais.oid_pessoa_destinatario = Pessoas.oid_pessoa " +
		  		" and notas_fiscais.oid_modelo_nota_fiscal = 14";

			  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
			    sql += " and Notas_Fiscais.oid_pessoa_destinatario = '" + ed.getOid_Pessoa () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
			    sql += " and Notas_Fiscais.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
			        "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
			    sql += " and Notas_Fiscais.dt_Emissao <= '" + ed.getData_Emissao_Final () +
			        "'";
			  }
			  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
			    sql += " and Notas_Fiscais.nr_nota_fiscal = '" + ed.getNr_Proximo_Numero () + "'";
			  }
			  if(ed.getDM_Relatorio().equals("R")){
			      sql += " order by oid_pessoa_destinatario, dt_emissao ";
			  } else {
			      sql += " order by dt_Emissao ";
			  }
//	System.out.println("DR CTRC >" +sql);
			  Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();
			  ResultSet res = this.executasql.executarConsulta (sql.toString());

			  while (res.next ()) {
			    Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();

			    cliente = res.getString ("oid_pessoa_destinatario");
//			    if (ed.getDM_Relatorio ().equals ("R")) {
//			        saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
//			        edAuxiliar1.setVL_Classifica3 (saldo_anterior);
//			    }

		        edAuxiliar1.setNM_Tabela ("Notas_Fiscais");
		        edAuxiliar1.setOID_Tabela (res.getString ("oid_nota_fiscal"));
		        edAuxiliar1.setNM_Classifica1(res.getString ("dt_Emissao"));
		        edAuxiliar1.setNM_Classifica2("EMISSAO NF nr.: " + res.getString("nr_nota_fiscal"));
		        edAuxiliar1.setNM_Classifica3(res.getString ("nm_razao_social") + " (" + res.getString ("nr_cnpj_cpf") + ")");
		        edAuxiliar1.setNM_Classifica6(res.getString ("nr_nota_fiscal"));
		        edAuxiliar1.setNM_Classifica5(res.getString ("dt_Emissao"));
		        edAuxiliar1.setNM_Classifica4(res.getString ("nr_nota_fiscal"));
		        edAuxiliar1.setVL_Classifica1(res.getDouble("vl_faturar"));
		        edAuxiliar1.setVL_Classifica2(0.0);

		        edAuxiliar1.setNM_Classifica7(cliente);

		        edAuxiliar1.setNR_Sort (Nr_Sort);

		        Auxiliar1RN.inclui (edAuxiliar1);

		        clieAux = cliente;
			  }

//REC AVULSO
			  sql = "select dt_movimento_conta_corrente,vl_lancamento,dm_tipo_lancamento,dm_debito_credito," +
			  		" nm_complemento_historico, nr_documento " +
			  		" from movimentos_contas_correntes " +
			  		" where oid_conta=238 " +
			  		" and dm_tipo_lancamento='L' ";
//
//		  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
//		      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
//		      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
//		    sql += " and Notas_Fiscais.oid_pessoa_destinatario = '" + ed.getOid_Pessoa () + "'";
//		  }
		  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
		    sql += " and dt_movimento_conta_corrente >= '" + ed.getData_Emissao_Inicial () +
		        "'";
		  }
		  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
		      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
		    sql += " and dt_movimento_conta_corrente <= '" + ed.getData_Emissao_Final () +
		        "'";
		  }

		  if(ed.getDM_Relatorio().equals("R")){
		      sql += " order by nm_complemento_historico, dt_movimento_conta_corrente ";
		  } else {
		      sql += " order by dt_movimento_conta_corrente ";
		  }
//System.out.println("DR CTRC >" +sql);
		  res = this.executasql.executarConsulta (sql.toString());

		  while (res.next ()) {
		    Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();

	        edAuxiliar1.setNM_Tabela ("movimentos_contas_correntes");
	        edAuxiliar1.setOID_Tabela (res.getString ("nr_documento"));
	        edAuxiliar1.setNM_Classifica1(res.getString ("dt_movimento_conta_corrente"));
	        edAuxiliar1.setNM_Classifica2("RECEBIMENTO AVULSO nr.: " + res.getString("nr_documento"));
	        edAuxiliar1.setNM_Classifica3(res.getString ("nm_complemento_historico"));
	        edAuxiliar1.setNM_Classifica6(res.getString ("nr_documento"));
	        edAuxiliar1.setNM_Classifica5(res.getString ("dt_movimento_conta_corrente"));
	        edAuxiliar1.setNM_Classifica4(res.getString ("nr_documento"));
	        if(res.getString ("dm_debito_credito").equals("D"))
	        	edAuxiliar1.setVL_Classifica1(res.getDouble("vl_lancamento"));
	        else
	        	edAuxiliar1.setVL_Classifica2(res.getDouble("vl_lancamento"));

	        edAuxiliar1.setNR_Sort (Nr_Sort);

	        Auxiliar1RN.inclui (edAuxiliar1);

		  }

			  sql = " select * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
			  sql += " order by auxiliar1.nm_classifica3 , auxiliar1.nm_classifica1 , auxiliar1.nm_classifica5  ";
			  res = null;
			  res = this.executasql.executarConsulta (sql.toString ());

			  while (res.next ()) {
			    DiarioED edDiario = new DiarioED();

			    cliente = res.getString ("nm_classifica7");
//			    if (ed.getDM_Relatorio ().equals ("R")) {
//			      if (!cliente.equals (clieAux)) {
//			    	  saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), "CTRC");
//			    	  //saldo_anterior = res.getDouble ("vl_classifica3");
//			        edDiario.setVL_Saldo_Inicial (saldo_anterior);
//			        saldo = saldo_anterior;
//			      }
//			    }

			    edDiario.setOid_Pessoa(cliente);
			    edDiario.setDt_Emissao (dataFormatada.getDT_FormataData (res.getString ("nm_classifica1")));
			    edDiario.setNm_Tipo_Documento (res.getString ("nm_classifica2"));
			    edDiario.setNM_Razao_Social(res.getString ("nm_classifica3"));
			    edDiario.setNr_Duplicata(res.getString("nm_classifica4"));
			    edDiario.setDt_Vencimento (dataFormatada.getDT_FormataData (res.getString ("nm_classifica5")));
			    edDiario.setVL_Debito (res.getDouble ("vl_classifica1"));
			    edDiario.setVL_Credito (res.getDouble ("vl_classifica2"));
//	System.out.println(edDiario.getNr_Duplicata() + " - " + edDiario.getNM_Razao_Social());
//			    saldo = saldo - res.getDouble ("vl_classifica2") + res.getDouble ("vl_classifica1");
//			    edDiario.setVL_Saldo (saldo);

			    list.add (edDiario);

			    clieAux = cliente;

			  }

			  sql = "delete from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
			  this.executasql.executarUpdate(sql.toString ());
//			  Auxiliar1RN AuxRN = new Auxiliar1RN ();
//			  while (res.next ()) {
//			    Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
//			    edAuxiliar1.setOID_auxiliar1 (new Integer (res.getInt ("oid_auxiliar1")));
//			    AuxRN.deleta (edAuxiliar1);
//			  }

			  util.closeResultset(res);

			}
			catch (Exception e) {
			  e.printStackTrace ();
			  throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
			                      "geraDiario_Razao_Clientes_CTRC()");
			}
			return list;
		}

	  public ArrayList getDadosDiario_Razao_Clientes_NFOLD(DiarioED edComp) throws
	  Excecoes {

			String sql = null , sqlBusca = null;
			ArrayList list = new ArrayList ();
			DiarioED ed = (DiarioED) edComp;
			double saldo_anterior = 0;
			double saldo = 0, variacao = 0;
			String cliente = "" , clieAux = "";

			try {
				//int delAux = this.executasql.executarUpdate("delete from auxiliar1");

			  long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).
			                            toString ().substring (6 , 12)).longValue ());
// CONHECIMENTOS
			  sql = "select Notas_Fiscais.dt_emissao, Notas_Fiscais.nr_Nota_Fiscal, Notas_Fiscais.oid_nota_fiscal, " +
			  		"(Notas_Fiscais.vl_liquido_nota_fiscal-Notas_Fiscais.vl_inss) as vl_faturar, " +
		  		" Notas_Fiscais.oid_pessoa_destinatario, Pessoas.nm_razao_social, Pessoas.nr_cnpj_cpf from Notas_Fiscais, pessoas " +
		  		" where Notas_Fiscais.dm_impresso = 'S' " +
		  		" and Notas_Fiscais.DM_Tipo_Nota_Fiscal = 'S' " +
		  		" and (Notas_Fiscais.dm_situacao = 'F' or Notas_Fiscais.dm_situacao = 'A') " +
		  		" and Notas_Fiscais.dm_finalizado = 'F'" +
		  		" and Notas_Fiscais.oid_pessoa_destinatario = Pessoas.oid_pessoa " +
		  		" and notas_fiscais.oid_modelo_nota_fiscal = 14";

			  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
			    sql += " and Notas_Fiscais.oid_pessoa_destinatario = '" + ed.getOid_Pessoa () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
			    sql += " and Notas_Fiscais.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
			        "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
			    sql += " and Notas_Fiscais.dt_Emissao <= '" + ed.getData_Emissao_Final () +
			        "'";
			  }
			  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
			    sql += " and Notas_Fiscais.nr_nota_fiscal = '" + ed.getNr_Proximo_Numero () + "'";
			  }
			  if(ed.getDM_Relatorio().equals("R")){
			      sql += " order by oid_pessoa_destinatario, dt_emissao ";
			  } else {
			      sql += " order by dt_Emissao ";
			  }
//	System.out.println("DR CTRC >" +sql);
			  Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();
			  ResultSet res = this.executasql.executarConsulta (sql.toString());

			  while (res.next ()) {
			    Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();

			    cliente = res.getString ("oid_pessoa_destinatario");
//			    if (ed.getDM_Relatorio ().equals ("R")) {
//			        saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
//			        edAuxiliar1.setVL_Classifica3 (saldo_anterior);
//			    }

		        edAuxiliar1.setNM_Tabela ("Notas_Fiscais");
		        edAuxiliar1.setOID_Tabela (res.getString ("oid_nota_fiscal"));
		        edAuxiliar1.setNM_Classifica1(res.getString ("dt_Emissao"));
		        edAuxiliar1.setNM_Classifica2("EMISSAO NACIONAL - NF nr.: " + res.getString("nr_nota_fiscal"));
		        edAuxiliar1.setNM_Classifica3(res.getString ("nm_razao_social") + " (" + res.getString ("nr_cnpj_cpf") + ")");
		        edAuxiliar1.setNM_Classifica6(res.getString ("nr_nota_fiscal"));
		        edAuxiliar1.setNM_Classifica5(res.getString ("dt_Emissao"));
		        edAuxiliar1.setNM_Classifica4(res.getString ("nr_nota_fiscal"));
		        edAuxiliar1.setVL_Classifica1(res.getDouble("vl_faturar"));
		        edAuxiliar1.setVL_Classifica2(0.0);

		        edAuxiliar1.setNM_Classifica7(cliente);

		        edAuxiliar1.setNR_Sort (Nr_Sort);

		        Auxiliar1RN.inclui (edAuxiliar1);

		        clieAux = cliente;
			  }
// FATURAMENTO
			  sql = "select Duplicatas.dt_emissao, Duplicatas.nr_duplicata, Duplicatas.oid_duplicata, Notas_Fiscais.dt_emissao as emissao_NF, " +
		  		" Notas_Fiscais.oid_nota_fiscal, Notas_Fiscais.nr_nota_fiscal, " +
		  		" (Notas_Fiscais.vl_liquido_nota_fiscal-Notas_Fiscais.vl_inss) as vl_faturar, " +
		  		" Notas_Fiscais.oid_pessoa_destinatario, Pessoas.nm_razao_social " +
		        " from Duplicatas, Origens_Duplicatas, Notas_Fiscais, pessoas " +
		        " where Duplicatas.oid_duplicata = Origens_Duplicatas.oid_duplicata " +
		        " AND Origens_Duplicatas.oid_nota_fiscal = Notas_Fiscais.oid_nota_fiscal " +
//		        " AND Origens_Duplicatas.dm_Situacao != 'E' " +
		        " and Notas_Fiscais.oid_pessoa_destinatario = Pessoas.oid_pessoa ";

			  if (String.valueOf (ed.getOid_Pessoa ()) != null &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
			      !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
			    sql += " and Duplicatas.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Inicial ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Inicial ()).equals ("null")) {
			    sql += " and Duplicatas.dt_Emissao >= '" + ed.getData_Emissao_Inicial () +
			        "'";
			  }
			  if (String.valueOf (ed.getData_Emissao_Final ()) != null &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("") &&
			      !String.valueOf (ed.getData_Emissao_Final ()).equals ("null")) {
			    sql += " and Duplicatas.dt_Emissao <= '" + ed.getData_Emissao_Final () +
			        "'";
			  }
			  if (String.valueOf (ed.getNr_Proximo_Numero()) != null &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("") &&
			      !String.valueOf (ed.getNr_Proximo_Numero()).equals ("null")) {
			    sql += " and Notas_Fiscais.nr_nota_fiscal = '" + ed.getNr_Proximo_Numero () + "'";
			  }
			  if (ed.getDM_Relatorio ().equals ("R")) {
			    sql += " order by Duplicatas.oid_pessoa, dt_Emissao ";
			  }
			  else {
			    sql += " order by dt_Emissao ";
			  }
//	System.out.println("DR CTRC >" +sql);
			  res = this.executasql.executarConsulta (sql.toString ());

			  while (res.next ()) {

			      Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();

			      cliente = res.getString ("oid_pessoa_destinatario");
//			      if (ed.getDM_Relatorio ().equals ("R")) {
//			          saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
//			          edAuxiliar1.setVL_Classifica3 (saldo_anterior);
//			      }

			      edAuxiliar1.setNM_Tabela ("Duplicatas");
			      edAuxiliar1.setOID_Tabela (res.getString ("oid_duplicata"));
			      edAuxiliar1.setNM_Classifica1(res.getString ("dt_Emissao"));
			      edAuxiliar1.setNM_Classifica2("FATURAMENTO nr.: " + res.getString("nr_duplicata") + " - NF nr.: " + res.getString("nr_nota_fiscal"));
			      edAuxiliar1.setNM_Classifica3(res.getString ("nm_razao_social"));
			      edAuxiliar1.setNM_Classifica4(res.getString ("nr_nota_fiscal"));
			      edAuxiliar1.setNM_Classifica5(res.getString ("emissao_nf"));
			      edAuxiliar1.setVL_Classifica2(res.getDouble("vl_faturar"));
			      edAuxiliar1.setVL_Classifica1(0.0);

			      edAuxiliar1.setNR_Sort (Nr_Sort);
			      Auxiliar1RN.inclui (edAuxiliar1);

			      clieAux = cliente;

			  }

			  sql = " select * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
			  sql += " order by auxiliar1.nm_classifica3 , auxiliar1.nm_classifica1 , auxiliar1.nm_classifica5  ";
			  res = null;
			  res = this.executasql.executarConsulta (sql.toString ());

			  while (res.next ()) {
			    DiarioED edDiario = new DiarioED();

			    cliente = res.getString ("nm_classifica7");
//			    if (ed.getDM_Relatorio ().equals ("R")) {
//			      if (!cliente.equals (clieAux)) {
//			    	  saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), "CTRC");
//			    	  //saldo_anterior = res.getDouble ("vl_classifica3");
//			        edDiario.setVL_Saldo_Inicial (saldo_anterior);
//			        saldo = saldo_anterior;
//			      }
//			    }

			    edDiario.setOid_Pessoa(cliente);
			    edDiario.setDt_Emissao (dataFormatada.getDT_FormataData (res.getString ("nm_classifica1")));
			    edDiario.setNm_Tipo_Documento (res.getString ("nm_classifica2"));
			    edDiario.setNM_Razao_Social(res.getString ("nm_classifica3"));
			    edDiario.setNr_Duplicata(res.getString("nm_classifica4"));
			    edDiario.setDt_Vencimento (dataFormatada.getDT_FormataData (res.getString ("nm_classifica5")));
			    edDiario.setVL_Debito (res.getDouble ("vl_classifica1"));
			    edDiario.setVL_Credito (res.getDouble ("vl_classifica2"));
//	System.out.println(edDiario.getNr_Duplicata() + " - " + edDiario.getNM_Razao_Social());
//			    saldo = saldo - res.getDouble ("vl_classifica2") + res.getDouble ("vl_classifica1");
//			    edDiario.setVL_Saldo (saldo);

			    list.add (edDiario);

			    clieAux = cliente;

			  }

			  sql = "delete from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
			  this.executasql.executarUpdate(sql.toString ());
//			  Auxiliar1RN AuxRN = new Auxiliar1RN ();
//			  while (res.next ()) {
//			    Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
//			    edAuxiliar1.setOID_auxiliar1 (new Integer (res.getInt ("oid_auxiliar1")));
//			    AuxRN.deleta (edAuxiliar1);
//			  }

			  util.closeResultset(res);

			}
			catch (Exception e) {
			  e.printStackTrace ();
			  throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
			                      "geraDiario_Razao_Clientes_CTRC()");
			}
			return list;
		}

}
