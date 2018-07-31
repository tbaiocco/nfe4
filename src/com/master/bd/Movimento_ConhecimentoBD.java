package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.master.ed.Movimento_ConhecimentoED;
import com.master.ed.UsuarioED;
import com.master.rl.Movimento_ConhecimentoRL;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.bd.ExecutaSQL;
import com.master.ed.Lote_FornecedorED;
import com.master.util.ed.Parametro_FixoED;

public class Movimento_ConhecimentoBD
extends BancoUtil {

	private ExecutaSQL executasql;
	String CD_Tipo_Movimento = null;
	String DM_Tipo_Movimento = null;
	String DM_Totaliza = null;

	public Movimento_ConhecimentoBD (ExecutaSQL sql) {
		this.executasql = sql;
	}

	public Movimento_ConhecimentoED inclui (Movimento_ConhecimentoED ed) throws Excecoes {

		// System.out.println ("Movimento Conhecimento inclui NOVO 33 ");

		String sql = "";
		String chave = null;
		String oid_Ordem_Frete_Movimento = "";
		int cont = 0;
		ResultSet res=null;
		
     	Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();
		chave = String.valueOf (System.currentTimeMillis ()).toString ();

		try {

			if ("D".equals (ed.getDM_Tipo_Movimento ())) {
				String retorno_custo=custo_Liberado(ed.getOID_Conhecimento());
				if (!"".equals(retorno_custo)){
					throw new Mensagens(retorno_custo);
				} 
			}

			if (!"ATUALIZA_LOTE_FORNECEDOR".equals(ed.getDM_Origem_Lancamento())) {
				if (ed.getOID_Conhecimento () != null && ed.getOID_Conhecimento ().length () > 4) {
					sql = " SELECT Conhecimentos.oid_Conhecimento, Conhecimentos.NR_Conhecimento, Movimentos_Conhecimentos.oid_Ordem_Frete, Movimentos_Conhecimentos.oid_Viagem  " + 
					" FROM Conhecimentos, Movimentos_Conhecimentos, Tipos_Movimentos " +
					" WHERE  Conhecimentos.oid_Conhecimento ='" + ed.getOID_Conhecimento () + "'" +
					" AND    Conhecimentos.oid_Conhecimento = Movimentos_Conhecimentos.oid_Conhecimento " +
					" AND    Tipos_Movimentos.oid_Tipo_Movimento = Movimentos_Conhecimentos.oid_Tipo_Movimento " +
					" AND    (Tipos_Movimentos.DM_Lancamento_Multiplo is null OR  Tipos_Movimentos.DM_Lancamento_Multiplo='N' )" +				
					" AND    Tipos_Movimentos.oid_Tipo_Movimento ='" + ed.getOID_Tipo_Movimento () + "'";
					//chave = (ed.getOID_Conhecimento () + ed.getOID_Tipo_Movimento () + String.valueOf (System.currentTimeMillis ()).toString ().substring (10 , 13));

				}
				else if (ed.getOID_Cotacao () != null && ed.getOID_Cotacao ().length () > 4) {
					sql = " SELECT oid_Conhecimento, oid_Ordem_Frete FROM Movimentos_Conhecimentos " +
					" WHERE  oid_Cotacao ='" + ed.getOID_Cotacao () + "'" +
					" AND    oid_Tipo_Movimento ='" + ed.getOID_Tipo_Movimento () + "'";
				}

				// System.out.println ("Movimento  getOID_Ordem_Frete= " + ed.getOID_Ordem_Frete ());

				// System.out.println ("BUSCA movs.: " + sql);
				if (!"".equals (sql)) {
					res = this.executasql.executarConsulta (sql);
					while (res.next () && cont == 0) {
						cont++;
						oid_Ordem_Frete_Movimento = res.getString ("oid_Ordem_Frete");

						// System.out.println ("Movimento já Incluido->>: " + res.getString ("NR_Conhecimento"));

						// ->>p/RP 08.05.06
						if (ed.getOID_Lote_Fornecedor () == 0 && !oid_Ordem_Frete_Movimento.equals (ed.getOID_Ordem_Frete ())) {
							if (ed.getOID_Viagem() != null && ed.getOID_Viagem().equals(res.getString("oid_Viagem"))) { 
								//throw new Excecoes ("Movimento já Incluido para o Cto: " + res.getString ("NR_Conhecimento"));
							}else {
								//throw new Excecoes ("Movimento já Incluido para o Cto: " + res.getString ("NR_Conhecimento"));
							}
						}
						//
					}
				}

				// System.out.println ("Movimento  oid_Ordem_Frete_Movimento= " + oid_Ordem_Frete_Movimento);

				if (ed.getOID_Ordem_Frete () != null && ed.getOID_Ordem_Frete ().length () > 4) {
					if (!oid_Ordem_Frete_Movimento.equals (ed.getOID_Ordem_Frete ()) && !oid_Ordem_Frete_Movimento.equals ("")) {
						cont = 0;
					}
					//// System.out.println ("inclui sempre quando vem da ordem de frete");
				}
				if (ed.getNR_Postagem () > 0) {
					cont = 0;
				}


				// System.out.println ("Movimento  cont= " + cont);

				if (ed.getOID_Manifesto () != null && ed.getOID_Manifesto ().length () > 4) {
					cont = 0;
					// System.out.println ("inclui sempre quando vem da getOID_Manifesto");
				}
			}

			// System.out.println ("movs.: " + cont);
			if (cont == 0) {
				// System.out.println ("Movimento Conhecimento chave >> " + chave);

				if (ed.getOID_Lote_Fornecedor () > 0) {
					ed.setDT_Realizado (Data.getDataDMY ());
					ed.setVL_Realizado (ed.getVL_Movimento ());
				}
				else {
					ed.setDT_Realizado ("");
					ed.setVL_Realizado (0);
					ed.setVL_Previsto (ed.getVL_Movimento ());
				}

				if (!"D".equals (ed.getDM_Tipo_Movimento ())) {
					ed.setDT_Realizado (Data.getDataDMY ());
					ed.setVL_Realizado (ed.getVL_Movimento ());
					ed.setVL_Previsto (ed.getVL_Movimento ());
				}

				sql = " insert into Movimentos_Conhecimentos (OID_Movimento_Conhecimento, OID_Fornecedor, NR_Documento, NR_Movimento_Conhecimento, VL_Movimento, OID_Conhecimento, OID_Ordem_Frete, OID_Cotacao, OID_Tipo_Movimento, DT_Movimento_Conhecimento, HR_Movimento_Conhecimento, DM_Tipo_Movimento, DM_Lancado_Gerado , OID_Tabela_Frete, OID_Lote_Fornecedor, VL_Previsto, VL_Realizado, VL_Tarifa, TX_OBservacao, OID_Manifesto, NR_Postagem, oid_Usuario, oid_Viagem) values ";
				sql += "('" + chave + "','" + ed.getOID_Fornecedor () + "','" + ed.getNR_Documento () + "'," + ed.getNR_Movimento_Conhecimento () + "," + ed.getVL_Movimento () + ",'" + ed.getOID_Conhecimento () + "','" + ed.getOID_Ordem_Frete () + "','" + ed.getOID_Cotacao () + "'," + ed.getOID_Tipo_Movimento () + ",'" +
				ed.getDT_Movimento_Conhecimento () + "','" + ed.getHR_Movimento_Conhecimento () + "','" + ed.getDM_Tipo_Movimento () + "','" + ed.getDM_Lancado_Gerado () + "','" + ed.getOID_Tabela_Frete () + "'," + ed.getOID_Lote_Fornecedor () + "," + ed.getVL_Previsto () + "," +
				ed.getVL_Realizado () + ", " + ed.getVL_Tarifa () + ",'" + ed.getTX_Observacao () + "','" + ed.getOID_Manifesto () + "','" + ed.getNR_Postagem () + "', " +ed.getOID_Usuario()+ ", '" + ed.getOID_Viagem() + "')";

				// System.out.println ("Movimento Conhecimento inclui 3 " + sql);

				if ( (ed.getVL_Previsto () + ed.getVL_Realizado ()) > 0) {
					int i = executasql.executarUpdate (sql);
					// System.out.println ("Movimento Conhecimento inclui  4 ");
					movimento_ConhecimentoED.setOID_Movimento_Conhecimento (chave);
				}
			}

			if ("D".equals (ed.getDM_Tipo_Movimento ()) && !"ATUALIZA_LOTE_FORNECEDOR".equals(ed.getDM_Origem_Lancamento()) && !"VIAGEM".equals(ed.getDM_Origem_Lancamento())) {
				this.verifica_Custo_Minuta (ed.getOID_Conhecimento ());
			}

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "inclui()");
		}
		return movimento_ConhecimentoED;
	}

	public Movimento_ConhecimentoED recalculaMargem (Movimento_ConhecimentoED ed) throws Excecoes {

		// System.out.println (" recalculaMargem 1");

		String sql = null;
		ResultSet rs = null;

		try {

			sql = " SELECT oid_Conhecimento, DT_Emissao from Conhecimentos " +
			" WHERE Conhecimentos.dm_impresso = 'S' " +
			" AND   Conhecimentos.DT_Emissao >= '" + ed.getDT_Movimento_Inicial () + "'" +
			" AND   Conhecimentos.DT_Emissao <= '" + ed.getDT_Movimento_Final () + "'";

			rs = this.executasql.executarConsulta (sql);
			int cont=0;
			while (rs.next ()) {
				cont++;  
				// System.out.println("CALC MARGEM="+rs.getString("DT_Emissao")+" ->>>"+cont);
				ed.setOID_Conhecimento (rs.getString ("oid_Conhecimento"));
				//this.inicioTransacao ();

				this.calcula_Margem (ed);

				//this.fimTransacao (true);

			}
		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao recalcular Conhecimento");
			excecoes.setMetodo ("inclui");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return ed;
	}

	public Movimento_ConhecimentoED acerta_Margem (Movimento_ConhecimentoED ed) throws Excecoes {

		String sql = null;
		long valOid = 0;
		String chave = null;
		String CD_Tipo_Movimento = null;
		double VL_Debito = 0;
		double VL_Credito = 0;
		double VL_Movimento = 0;
		long OID_Tipo_Movimento = 0;

		Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();

		try {

//			sql = "select oid_tipo_movimento from Tipos_Movimentos ";
//			sql += "WHERE Tipos_Movimentos.CD_Tipo_Movimento = 'TF'";
//			ResultSet rs = null;
//			rs = this.executasql.executarConsulta(sql);

//			while (rs.next()){
//			OID_Tipo_Movimento=rs.getLong("oid_tipo_movimento");
//			}

//			sql = "select oid_movimento_conhecimento from Movimentos_Conhecimentos ";
//			sql += "WHERE oid_Conhecimento = ";
//			sql += "'" + ed.getOID_Conhecimento() + "' ";
//			sql += " AND oid_Tipo_Movimento = ";
//			sql += OID_Tipo_Movimento ;

//			rs = null;
//			rs = this.executasql.executarConsulta(sql);

//			while (rs.next()){
//			sql = "delete from Movimentos_Conhecimentos WHERE oid_Movimento_Conhecimento = ";
//			sql += "('" + rs.getString("oid_movimento_conhecimento") + "')";
//			int i = executasql.executarUpdate(sql);
//			}

//			sql =  " SELECT SUM(Movimentos_Conhecimentos.VL_Movimento) AS Valor_Movimento FROM Movimentos_Conhecimentos, Tipos_Movimentos ";
//			sql += " WHERE Tipos_Movimentos.OID_Tipo_Movimento = Movimentos_Conhecimentos.OID_Tipo_Movimento ";
//			sql += " AND Tipos_Movimentos.DM_Debito_Credito = 'C'" ;
//			sql += " AND Tipos_Movimentos.DM_Calcula_Frete = 'S'" ;
//			sql += " AND Movimentos_Conhecimentos.OID_Conhecimento = '" + ed.getOID_Conhecimento()  + "'";

//			rs = null;
//			rs = this.executasql.executarConsulta(sql);

//			while (rs.next()){
//			VL_Credito = rs.getDouble("Valor_Movimento");
//			}

//			sql =  " SELECT SUM(Movimentos_Conhecimentos.VL_Movimento) AS Valor_Movimento FROM Movimentos_Conhecimentos, Tipos_Movimentos ";
//			sql += " WHERE Tipos_Movimentos.OID_Tipo_Movimento = Movimentos_Conhecimentos.OID_Tipo_Movimento ";
//			sql += " AND Tipos_Movimentos.DM_Debito_Credito = 'D'" ;
//			sql += " AND Tipos_Movimentos.DM_Calcula_Frete = 'N'" ;
//			sql += " AND Movimentos_Conhecimentos.OID_Conhecimento = '" + ed.getOID_Conhecimento()  + "'";

//			rs = null;
//			rs = this.executasql.executarConsulta(sql);


//			while (rs.next()){
//			VL_Debito = rs.getDouble("Valor_Movimento");
//			}

//			VL_Movimento = (VL_Credito - VL_Debito);
//			Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED();

//			edMovimento_Conhecimento.setOID_Tipo_Movimento(OID_Tipo_Movimento);
//			edMovimento_Conhecimento.setVL_Movimento(new Double(VL_Movimento).doubleValue());

//			edMovimento_Conhecimento.setDT_Movimento_Conhecimento(Data.getDataDMY());
//			edMovimento_Conhecimento.setHR_Movimento_Conhecimento(Data.getHoraHM());

//			edMovimento_Conhecimento.setOID_Conhecimento(ed.getOID_Conhecimento());
//			edMovimento_Conhecimento.setDM_Tipo_Movimento("R");
//			edMovimento_Conhecimento.setDM_Lancado_Gerado("G");
//			edMovimento_Conhecimento.setNM_Pessoa_Entrega("");

//			this.inclui(edMovimento_Conhecimento);

		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao incluir");
			excecoes.setMetodo ("inclui");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return movimento_ConhecimentoED;
	}

	public void altera (Movimento_ConhecimentoED ed) throws Excecoes {

		String sql = null;

		try {
			String retorno_custo=custo_Liberado(ed.getOID_Conhecimento());
			if (!"".equals(retorno_custo)){
				throw new Mensagens(retorno_custo);
			}
			
			sql =  " UPDATE Movimentos_Conhecimentos SET oid_Usuario=" + ed.getOID_Usuario();

			if (ed.getOID_Tipo_Movimento()>0) {
				sql+=  " ,OID_Tipo_Movimento=" + ed.getOID_Tipo_Movimento();
			}
			if (JavaUtil.doValida (ed.getDT_Realizado ())) {
				sql+=  " ,DT_Realizado='" + ed.getDT_Realizado() + "'";
			}
			if (JavaUtil.doValida (ed.getDM_Tipo_Movimento ())) {
				sql+=  " ,DM_Tipo_Movimento='" + ed.getDM_Tipo_Movimento() + "'";
			}
			if (JavaUtil.doValida (ed.getDT_Movimento_Conhecimento ())) {
				sql+=  " ,DT_Movimento_Conhecimento='" + ed.getDT_Movimento_Conhecimento() + "'";
			}
			if (JavaUtil.doValida (ed.getHR_Movimento_Conhecimento ())) {
				sql+=  " ,HR_Movimento_Conhecimento='" + ed.getHR_Movimento_Conhecimento() + "'";
			}
			if (ed.getVL_Movimento()>0) {
				sql+=  " ,VL_Movimento=" + ed.getVL_Movimento();
			}
			if (ed.getVL_Previsto()>0) {
				sql+=  " ,VL_Movimento=" + ed.getVL_Previsto();
			}
			if (JavaUtil.doValida (ed.getOID_Fornecedor ())) {
				sql+=  " ,OID_Fornecedor='" + ed.getOID_Fornecedor() + "'";
			}
			if (ed.getOID_Lote_Fornecedor()>0) {
				sql+=  " ,oid_Lote_Fornecedor='" + ed.getOID_Lote_Fornecedor() +"'";
			}
			
			sql += " where oid_Movimento_Conhecimento = '" + ed.getOID_Movimento_Conhecimento () + "'";

			// System.out.println(sql);
			
			executasql.executarUpdate (sql);

			sql = " SELECT * from Tipos_Movimentos " +
			" WHERE Tipos_Movimentos.OID_Tipo_Movimento = " + ed.getOID_Tipo_Movimento ();

			ResultSet res = null;
			res = this.executasql.executarConsulta (sql);

			while (res.next ()) {
				CD_Tipo_Movimento = (res.getString ("CD_Tipo_Movimento"));
				DM_Tipo_Movimento = (res.getString ("DM_Tipo_Movimento")); ///###
			}

			if (DM_Tipo_Movimento != null && DM_Tipo_Movimento.equals ("I")) {

				if ("I".equals (ed.getDM_Nacional_Internacional ())) {
					sql = " update Conhecimentos_Internacionais set ";
				}
				else {
					sql = " update Conhecimentos set ";
				}
				sql +=
					" VL_Total_Frete = 0, VL_ICMS = 0, VL_Base_Calculo_ICMS = 0 " +
					" where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

				executasql.executarUpdate (sql);
			}

			if ("D".equals (ed.getDM_Tipo_Movimento ())) {

				this.verifica_Custo_Minuta (ed.getOID_Conhecimento ());
			}

		}

		catch (SQLException e) {
			e.printStackTrace ();
			throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera(Movimento_ConhecimentoED ed)");
		}
		catch (Exception e) {
			e.printStackTrace ();
			throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera(Movimento_ConhecimentoED ed)");
		}
	}

	public void atualiza_Custo_Realizado (Movimento_ConhecimentoED ed) throws Excecoes {

		this.atualiza_Custo (ed);

	}


	public String atualiza_Custo (Movimento_ConhecimentoED ed) throws Excecoes {

		String sql = null;
		ResultSet res = null;
		int cont = 0;
		String oid_Movimento_Conhecimento = "";

		try {

			// System.out.println ("atualiza_Custo= getNR_Documento= " + ed.getNR_Documento ());

			if (ed.getNR_Documento () != null && !ed.getNR_Documento ().equals ("null") && !ed.getNR_Documento ().equals ("")) {

				sql = " SELECT OID_Movimento_Conhecimento, VL_Movimento FROM Movimentos_Conhecimentos " +
				" WHERE  1=1 " + //Movimentos_Conhecimentos.VL_Realizado <=0 " +
				" AND    Movimentos_Conhecimentos.OID_Conhecimento  = '" + ed.getOID_Conhecimento () + "'" +
				" AND    Movimentos_Conhecimentos.OID_Tipo_Movimento  = '" + ed.getOID_Tipo_Movimento () + "'" +
				" AND    Movimentos_Conhecimentos.NR_Documento = '" + ed.getNR_Documento () + "'";
				if (ed.getNR_Postagem () > 0) {
					sql += " AND    Movimentos_Conhecimentos.NR_Postagem  = '" + ed.getNR_Postagem () + "'";
				}

				// System.out.println ("inclui_altera (1) " + sql);

				res = this.executasql.executarConsulta (sql);
				while (res.next ()) {
					cont++;
					// System.out.println ("ok alterar");
					oid_Movimento_Conhecimento = res.getString ("OID_Movimento_Conhecimento");
					ed.setDT_Realizado (Data.getDataDMY ());
					ed.setVL_Movimento (ed.getVL_Realizado ());
					ed.setVL_Previsto (res.getDouble ("VL_Movimento"));

					sql = "  update Movimentos_Conhecimentos set OID_Fornecedor= '" + ed.getOID_Fornecedor () + "', VL_Movimento = " + ed.getVL_Movimento () + ", OID_Lote_Fornecedor = " + ed.getOID_Lote_Fornecedor () + ", VL_Previsto = " + ed.getVL_Previsto () + ", VL_Realizado = " + ed.getVL_Realizado () + ", DT_Realizado = '" +
					ed.getDT_Realizado () + "'";
					sql += " where oid_Movimento_Conhecimento = '" + res.getString ("OID_Movimento_Conhecimento") + "'";
					// System.out.println ("ok alterar" + sql);

					int i = executasql.executarUpdate (sql);

				}
			}
			if (cont == 0) {
				sql = " SELECT OID_Movimento_Conhecimento, VL_Movimento from Movimentos_Conhecimentos ";
				sql += " WHERE  1=1 "; //Movimentos_Conhecimentos.VL_Realizado <=0 ";
				sql += " AND    Movimentos_Conhecimentos.OID_Conhecimento  = '" + ed.getOID_Conhecimento () + "'" +
				" AND    Movimentos_Conhecimentos.OID_Tipo_Movimento = " + ed.getOID_Tipo_Movimento ();

				if (ed.getNR_Postagem () > 0) {
					sql += " AND    Movimentos_Conhecimentos.NR_Postagem  = '" + ed.getNR_Postagem () + "'";
				}

				// System.out.println ("inclui_altera (2) " + sql);

				res = this.executasql.executarConsulta (sql);
				while (res.next ()) {
					cont++;
					// System.out.println ("ok alterar");
					oid_Movimento_Conhecimento = res.getString ("OID_Movimento_Conhecimento");

					ed.setDT_Realizado (Data.getDataDMY ());
					ed.setVL_Movimento (ed.getVL_Realizado ());
					ed.setVL_Previsto (res.getDouble ("VL_Movimento"));

					sql = "  update Movimentos_Conhecimentos set OID_Fornecedor= '" + ed.getOID_Fornecedor () + "', VL_Movimento = " + ed.getVL_Movimento () + ", OID_Lote_Fornecedor = " + ed.getOID_Lote_Fornecedor () + ", VL_Previsto = " + ed.getVL_Previsto () + ", VL_Realizado = " + ed.getVL_Realizado () + ", DT_Realizado = '" +
					ed.getDT_Realizado () + "'";
					sql += " where oid_Movimento_Conhecimento = '" + res.getString ("OID_Movimento_Conhecimento") + "'";
					// System.out.println ("ok alterar" + sql);

					int i = executasql.executarUpdate (sql);

				}
			}
			if (cont <= 0) {
				ed.setVL_Movimento (ed.getVL_Realizado ());
				// System.out.println ("ok inluir");

				Movimento_ConhecimentoED mcED = new Movimento_ConhecimentoED ();

				mcED = inclui (ed);

				oid_Movimento_Conhecimento = mcED.getOID_Movimento_Conhecimento ();

			}

		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao alterar");
			excecoes.setMetodo ("alterar");
			excecoes.setExc (exc);
			throw excecoes;
		}

		// System.out.println ("oid_Movimento_Conhecimento->>" + oid_Movimento_Conhecimento);

		return oid_Movimento_Conhecimento;
	}

	public void deleta (Movimento_ConhecimentoED ed) throws Excecoes {
		String sql = null;
		String dm_Tipo_Movimento="";
		String oid_Conhecimento="";
		try {
			
			sql = " SELECT Movimentos_Conhecimentos.oid_Conhecimento, Tipos_Movimentos.DM_Tipo_Movimento " +
			" FROM  Movimentos_Conhecimentos, Tipos_Movimentos " +
			" WHERE Tipos_Movimentos.OID_Tipo_Movimento = Movimentos_Conhecimentos.OID_Tipo_Movimento " +
			" AND   Movimentos_Conhecimentos.oid_Movimento_Conhecimento  = '" + ed.getOID_Movimento_Conhecimento () + "'";

			// System.out.println (sql);

			ResultSet  res = this.executasql.executarConsulta (sql);

			if (res.next ()) {
				oid_Conhecimento = (res.getString ("oid_Conhecimento")); 
				dm_Tipo_Movimento = (res.getString ("DM_Tipo_Movimento")); 
			}

			String retorno_custo=custo_Liberado(oid_Conhecimento);
			if (!"".equals(retorno_custo)){
				throw new Mensagens(retorno_custo);
			}
			
			// System.out.println (" Movimento DM_Tipo_Movimento ->>>"+dm_Tipo_Movimento);

			sql = "delete from Movimentos_Conhecimentos WHERE oid_Movimento_Conhecimento = ";
			sql += "('" + ed.getOID_Movimento_Conhecimento () + "')";

			int i = executasql.executarUpdate (sql);

			// System.out.println (" Movimento Exclui oid_Conhecimento="+oid_Conhecimento);
			if (oid_Conhecimento!=null && oid_Conhecimento.length()>4){

				if ("I".equals (dm_Tipo_Movimento)) {
					if ("I".equals (ed.getDM_Nacional_Internacional ())) {
						sql = " update Conhecimentos_Internacionais set ";
					}
					else {
						sql = " update Conhecimentos set ";
					}
					sql += " VL_Total_Frete = 0, VL_ICMS = 0, VL_Base_Calculo_ICMS = 0 " +
					" where oid_Conhecimento = '" + oid_Conhecimento + "'";

					executasql.executarUpdate (sql);

				}

				if ("D".equals (ed.getDM_Tipo_Movimento ()) && !"ATUALIZA_LOTE_FORNECEDOR".equals (ed.getDM_Origem_Lancamento ())) {
					this.verifica_Custo_Minuta (oid_Conhecimento);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace ();
			throw new Excecoes (e.getMessage () , e , getClass ().getName () , "deleta(Movimento_ConhecimentoED ed)");
		}
		catch (Exception e) {
			e.printStackTrace ();
			throw new Excecoes (e.getMessage () , e , getClass ().getName () , "deleta(Movimento_ConhecimentoED ed)");
		}
	}

	public ArrayList lista (Movimento_ConhecimentoED ed) throws Excecoes {

		ArrayList list = new ArrayList ();
		double VL_Margem = 0;

		String sql =
			" SELECT Movimentos_Conhecimentos.*, ";

		if ("I".equals (ed.getDM_Nacional_Internacional ())) {
			sql +=
				"        Conhecimentos_Internacionais.NR_Conhecimento, " +
				"        Conhecimentos_Internacionais.OID_Conhecimento, " +
				"        Conhecimentos_Internacionais.DT_Emissao, " +
				"        Conhecimentos_Internacionais.vl_total_frete, " +
				"        Conhecimentos_Internacionais.vl_ressarcimento, " +
				"        Conhecimentos_Internacionais.vl_total_custo, ";
		}
		else {
			sql +=
				"        Conhecimentos.NR_Conhecimento, " +
				"        Conhecimentos.OID_Conhecimento, " +
				"        Conhecimentos.DT_Emissao, " +
				"        Conhecimentos.DM_Tipo_Tabela_Frete , " +
				"        Conhecimentos.vl_total_frete, " +
				"        Conhecimentos.vl_ressarcimento, " +
				"        Conhecimentos.vl_total_custo, ";
		}
		sql +=
			"        Tipos_Movimentos.DM_Debito_Credito, " +
			"        Tipos_Movimentos.DM_Totaliza, " +
			"        Tipos_Movimentos.DM_Tipo_Movimento, " +
			"        Tipos_Movimentos.NM_Tipo_Movimento, " +
			"        Tipos_Movimentos.DM_Calcula_Frete, " +
			"        Tipos_Movimentos.DM_Nacional_Internacional " +
			" from Movimentos_Conhecimentos, ";
		if ("I".equals (ed.getDM_Nacional_Internacional ())) {
			sql += "      Conhecimentos_Internacionais, ";
		}
		else {
			sql += "      Conhecimentos, ";
		}
		sql +=
			"      Tipos_Movimentos ";
		sql +=
			" WHERE Movimentos_Conhecimentos.OID_Tipo_Movimento = Tipos_Movimentos.OID_Tipo_Movimento";
		if ("I".equals (ed.getDM_Nacional_Internacional ())) {
			sql +=
				"   and Movimentos_Conhecimentos.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento ";
		}
		else {
			sql +=
				"   and Movimentos_Conhecimentos.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
		}

		if (JavaUtil.doValida (ed.getOID_Conhecimento ())) {
			if ("I".equals (ed.getDM_Nacional_Internacional ())) {
				sql += " and Conhecimentos_Internacionais.oid_Conhecimento = " + JavaUtil.getSQLString (ed.getOID_Conhecimento ());
			}
			else {
				sql += " and Conhecimentos.oid_Conhecimento = " + JavaUtil.getSQLString (ed.getOID_Conhecimento ());
			}
		}

		if (JavaUtil.doValida (ed.getOID_Cotacao ())) {
			sql += " and Cotacaos.oid_Cotacao = " + JavaUtil.getSQLString (ed.getOID_Cotacao ());
		}

		if (JavaUtil.doValida (ed.getDM_Nacional_Internacional ())) {
			sql += " and Tipos_Movimentos.DM_Nacional_Internacional = " + JavaUtil.getSQLString (ed.getDM_Nacional_Internacional ());
		}

		if ("I".equals (ed.getDM_Nacional_Internacional ())) {
			sql += " Order by Conhecimentos_Internacionais.NR_Conhecimento, ";
		}
		else {
			sql += " Order by Conhecimentos.NR_Conhecimento, ";
		}
		sql +=
			" Tipos_Movimentos.NR_Sequencia_Calculo, " +
			" Movimentos_Conhecimentos.DT_Movimento_Conhecimento, " +
			" Movimentos_Conhecimentos.HR_Movimento_Conhecimento ";

		// System.out.println ("movs. >> " + sql);

		FormataDataBean DataFormatada = new FormataDataBean ();
		DecimalFormat dec = new DecimalFormat ("###,###,##0.00");
		double VL_Total_Frete = 0;
		double VL_Total_Custo = 0;
		double VL_Ressarcimento = 0;
		ResultSet res = this.executasql.executarConsulta (sql);
		try {
			try {
				//popula
				while (res.next ()) {
					this.inicioTransacao ();
					this.executasql = this.sql;

					Movimento_ConhecimentoED edVolta = new Movimento_ConhecimentoED ();
					VL_Total_Custo = res.getDouble ("VL_Total_Custo");
					VL_Total_Frete = res.getDouble ("VL_Total_Frete");
					VL_Ressarcimento = res.getDouble ("VL_Ressarcimento");
					edVolta.setOID_Movimento_Conhecimento (res.getString ("OID_Movimento_Conhecimento"));
					edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
					edVolta.setOID_Cotacao (res.getString ("OID_Cotacao"));
					edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));
					edVolta.setOID_Lote_Fornecedor (res.getLong ("OID_Lote_Fornecedor"));
					edVolta.setNR_Movimento_Conhecimento (res.getLong ("NR_Movimento_Conhecimento"));
					edVolta.setVL_Movimento (res.getDouble ("VL_Movimento"));
					edVolta.setVL_Previsto (res.getDouble ("VL_Previsto"));
					edVolta.setVL_Realizado (res.getDouble ("VL_Realizado"));
					edVolta.setVL_Movimento_Formatado (dec.format (edVolta.getVL_Movimento ()));

					edVolta.setPE_Margem (dec.format ( (res.getDouble ("VL_Movimento") / (VL_Total_Frete + VL_Ressarcimento)) * 100));
					edVolta.setDT_Movimento_Conhecimento (res.getString ("DT_Movimento_Conhecimento"));
					DataFormatada.setDT_FormataData (edVolta.getDT_Movimento_Conhecimento ());
					edVolta.setDT_Movimento_Conhecimento (DataFormatada.getDT_FormataData ());
					edVolta.setHR_Movimento_Conhecimento (res.getString ("HR_Movimento_Conhecimento"));
					edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));

					edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
					DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
					edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

					edVolta.setNM_Tipo_Movimento (res.getString ("NM_Tipo_Movimento"));
					edVolta.setCD_Unidade (" ");
					edVolta.setNM_Pessoa_Remetente (" ");
					edVolta.setNM_Pessoa_Destinatario (" ");

					edVolta.setDM_Lancado_Gerado (res.getString ("DM_Lancado_Gerado"));
					edVolta.setDM_Tipo_Movimento (res.getString ("DM_Tipo_Movimento"));

					edVolta.setOID_Tabela_Frete (" ");

					if (edVolta.getDM_Lancado_Gerado () != null &&
							edVolta.getDM_Lancado_Gerado ().equals ("G") &&
							res.getString ("oid_tabela_Frete") != null &&
							!res.getString ("oid_tabela_Frete").equals ("") &&
							!res.getString ("oid_tabela_Frete").equals ("null")) {

						edVolta.setOID_Tabela_Frete (res.getString ("oid_tabela_Frete"));

						edVolta.setNM_Tabela_Frete ("comM001");
						if ("Standard".equals (res.getString ("DM_Tipo_Tabela_Frete")))
							edVolta.setNM_Tabela_Frete ("comM001Aereo01");
						if ("Expressa".equals (res.getString ("DM_Tipo_Tabela_Frete")))
							edVolta.setNM_Tabela_Frete ("comM001Aereo02");
						if ("Sedex".equals (res.getString ("DM_Tipo_Tabela_Frete")))
							edVolta.setNM_Tabela_Frete ("comM001Aereo03");
						if ("Emergencial".equals (res.getString ("DM_Tipo_Tabela_Frete")))
							edVolta.setNM_Tabela_Frete ("comM001Aereo04");
						if ("Expressa2".equals (res.getString ("DM_Tipo_Tabela_Frete")))
							edVolta.setNM_Tabela_Frete ("comM001Aereo05");
						if ("Pac".equals (res.getString ("DM_Tipo_Tabela_Frete")))
							edVolta.setNM_Tabela_Frete ("comM001Aereo06");
						if ("RodExp".equals (res.getString ("DM_Tipo_Tabela_Frete")))
							edVolta.setNM_Tabela_Frete ("comM001Aereo07");

					}
					edVolta.setOID_Lote ("");

					if (res.getLong ("oid_Lote_Fornecedor") > 0) {
						edVolta.setOID_Lote (res.getString ("oid_Lote_Fornecedor"));
					}
					if (edVolta.getVL_Realizado () > 0) {
						edVolta.setDM_Previsto_Realizado ("R");
					}
					else {
						edVolta.setDM_Previsto_Realizado ("P");
					}

					edVolta.setNM_Fornecedor ("");
					edVolta.setOID_Fornecedor ("");
					if (res.getString ("OID_Fornecedor") != null && !res.getString ("OID_Fornecedor").equals ("null") && !res.getString ("OID_Fornecedor").equals ("")) {
						sql = " SELECT NM_Razao_Social from Pessoas WHERE oid_Pessoa='" + res.getString ("OID_Fornecedor") + "'";
						// System.out.println ("SELECT2 " + sql);
						ResultSet res2 = this.executasql.executarConsulta (sql);
						try {
							while (res2.next ()) {
								edVolta.setOID_Fornecedor (res.getString ("OID_Fornecedor"));
								edVolta.setNM_Fornecedor (res2.getString ("NM_Razao_Social"));
							}
						}
						finally {
							closeResultset (res2);
						}
					}
					edVolta.setDM_Nacional_Internacional (res.getString ("DM_Nacional_Internacional"));
					this.fimTransacao (true);

					list.add (edVolta);
				}
			}
			catch (SQLException e) {
				throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(Movimento_ConhecimentoED ed)");
			}
		}
		finally {
			closeResultset (res);
		}

		VL_Margem = (VL_Total_Frete + VL_Ressarcimento - VL_Total_Custo);

		Movimento_ConhecimentoED edVolta = new Movimento_ConhecimentoED ();
		edVolta.setOID_Movimento_Conhecimento ("");

		edVolta.setNM_Fornecedor ("");
		edVolta.setDT_Movimento_Conhecimento ("");
		edVolta.setHR_Movimento_Conhecimento ("");
		edVolta.setOID_Tabela_Frete ("");
		edVolta.setNR_Conhecimento (ed.getNR_Conhecimento ());
		edVolta.setDT_Emissao ("");
		edVolta.setCD_Unidade ("");
		edVolta.setOID_Lote ("");
		edVolta.setDM_Previsto_Realizado ("");

		edVolta.setDM_Lancado_Gerado ("G");

		edVolta.setNM_Tipo_Movimento ("  Margem  ");
		edVolta.setVL_Movimento_Formatado (dec.format (VL_Margem));
		edVolta.setPE_Margem (dec.format ( (VL_Margem / (VL_Total_Frete + VL_Ressarcimento)) * 100));

		list.add (edVolta);
		return list;
	}

	public ArrayList listaCotacao (Movimento_ConhecimentoED ed) throws Excecoes {

		ArrayList list = new ArrayList ();
		double VL_Margem = 0;

		String sql =
			" SELECT Movimentos_Conhecimentos.*, " +
			"        Cotacoes.*, " +
			"        Tipos_Movimentos.DM_Debito_Credito, " +
			"        Tipos_Movimentos.DM_Totaliza, " +
			"        Tipos_Movimentos.DM_Tipo_Movimento, " +
			"        Tipos_Movimentos.NM_Tipo_Movimento, " +
			"        Tipos_Movimentos.DM_Calcula_Frete, " +
			"        Pessoa_Remetente.NM_Razao_Social    as NM_Razao_Social_Remetente, " +
			"        Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario " +
			" from Movimentos_Conhecimentos,  " +
			"      Cotacoes, " +
			"      Unidades, " +
			"      Pessoas Pessoa_Remetente, " +
			"      Pessoas Pessoa_Destinatario, " +
			"      Tipos_Movimentos " +
			" WHERE Movimentos_Conhecimentos.OID_Tipo_Movimento = Tipos_Movimentos.OID_Tipo_Movimento" +
			"   and Unidades.oid_Unidade                        = Cotacoes.oid_Unidade " +
			"   and Cotacoes.oid_Pessoa                         = Pessoa_Remetente.oid_Pessoa " +
			"   and Cotacoes.oid_Pessoa_Destinatario            = Pessoa_Destinatario.oid_Pessoa " +
			"   and Movimentos_Conhecimentos.OID_Cotacao        = Cotacoes.oid_Cotacao " +
			"   and Movimentos_Conhecimentos.oid_Cotacao  = " + JavaUtil.getSQLString (ed.getOID_Cotacao ());

		if (ed.getOID_Movimento_Cotacao () != null && ed.getOID_Movimento_Cotacao ().length () > 4) {
			sql = " SELECT Movimentos_Conhecimentos.*, " +
			"        Movimentos_Cotacoes.VL_Total_Custo, " +
			"        Movimentos_Cotacoes.VL_Total_Frete, " +
			"        Cotacoes.*, " +
			"        Tipos_Movimentos.DM_Debito_Credito, " +
			"        Tipos_Movimentos.DM_Totaliza, " +
			"        Tipos_Movimentos.DM_Tipo_Movimento, " +
			"        Tipos_Movimentos.NM_Tipo_Movimento, " +
			"        Tipos_Movimentos.DM_Calcula_Frete, " +
			"        Pessoa_Remetente.NM_Razao_Social    as NM_Razao_Social_Remetente, " +
			"        Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario " +
			" from Movimentos_Conhecimentos,  " +
			"      Movimentos_Cotacoes, " +
			"      Cotacoes, " +
			"      Unidades, " +
			"      Pessoas Pessoa_Remetente, " +
			"      Pessoas Pessoa_Destinatario, " +
			"      Tipos_Movimentos " +
			" WHERE Movimentos_Conhecimentos.OID_Tipo_Movimento     = Tipos_Movimentos.OID_Tipo_Movimento" +
			"   and Unidades.oid_Unidade                            = Cotacoes.oid_Unidade " +
			"   and Cotacoes.oid_Pessoa                             = Pessoa_Remetente.oid_Pessoa " +
			"   and Cotacoes.oid_Pessoa_Destinatario                = Pessoa_Destinatario.oid_Pessoa " +
			"   and Movimentos_Conhecimentos.OID_Movimento_Cotacao  = Movimentos_Cotacoes.OID_Movimento_Cotacao " +
			"   and Movimentos_Cotacoes.OID_Cotacao                 = Cotacoes.oid_Cotacao " +
			"   and Movimentos_Conhecimentos.oid_Movimento_Cotacao   = " + JavaUtil.getSQLString (ed.getOID_Movimento_Cotacao ());
		}

		//// System.out.println ("movs. >> " + sql);

		FormataDataBean DataFormatada = new FormataDataBean ();
		DecimalFormat dec = new DecimalFormat ("###,###,##0.00");
		double VL_Total_Frete = 0;
		double VL_Total_Custo = 0;
		ResultSet res = this.executasql.executarConsulta (sql);
		try {
			try {
				//popula
				while (res.next ()) {
					this.inicioTransacao ();
					this.executasql = this.sql;

					Movimento_ConhecimentoED edVolta = new Movimento_ConhecimentoED ();

					VL_Total_Custo = res.getDouble ("VL_Total_Custo");
					VL_Total_Frete = res.getDouble ("VL_Total_Frete");

					edVolta.setOID_Movimento_Conhecimento (res.getString ("OID_Movimento_Conhecimento"));
					edVolta.setOID_Cotacao (res.getString ("OID_Cotacao"));
					edVolta.setNR_Cotacao (res.getLong ("NR_Cotacao"));
					edVolta.setNR_Movimento_Conhecimento (res.getLong ("NR_Movimento_Conhecimento"));
					edVolta.setVL_Movimento (res.getDouble ("VL_Movimento"));
					edVolta.setVL_Movimento_Formatado (dec.format (edVolta.getVL_Movimento ()));
					edVolta.setPE_Margem (dec.format ( (res.getDouble ("VL_Movimento") / VL_Total_Frete) * 100));
					edVolta.setDT_Movimento_Conhecimento (res.getString ("DT_Movimento_Conhecimento"));
					DataFormatada.setDT_FormataData (edVolta.getDT_Movimento_Conhecimento ());
					edVolta.setDT_Movimento_Conhecimento (DataFormatada.getDT_FormataData ());
					edVolta.setHR_Movimento_Conhecimento (res.getString ("HR_Movimento_Conhecimento"));
					edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
					DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
					edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

					edVolta.setNM_Tipo_Movimento (res.getString ("NM_Tipo_Movimento"));
					edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
					edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));

					edVolta.setDM_Lancado_Gerado (res.getString ("DM_Lancado_Gerado"));
					edVolta.setDM_Tipo_Movimento (res.getString ("DM_Tipo_Movimento"));

					edVolta.setOID_Tabela_Frete (" ");
					if (edVolta.getDM_Lancado_Gerado () != null &&
							edVolta.getDM_Lancado_Gerado ().equals ("G") &&
							res.getString ("oid_tabela_Frete") != null &&
							!res.getString ("oid_tabela_Frete").equals ("") &&
							!res.getString ("oid_tabela_Frete").equals ("null")) {

						edVolta.setOID_Tabela_Frete (res.getString ("oid_tabela_Frete"));
					}

					edVolta.setNM_Fornecedor ("");
					edVolta.setOID_Fornecedor ("");
					if (res.getString ("OID_Fornecedor") != null && !res.getString ("OID_Fornecedor").equals ("null") && !res.getString ("OID_Fornecedor").equals ("")) {
						sql = " SELECT NM_Razao_Social from Pessoas WHERE oid_Pessoa='" + res.getString ("OID_Fornecedor") + "'";
						ResultSet res2 = this.executasql.executarConsulta (sql);
						try {
							while (res2.next ()) {
								edVolta.setOID_Fornecedor (res.getString ("OID_Fornecedor"));
								edVolta.setNM_Fornecedor (res2.getString ("NM_Razao_Social"));
							}
						}
						finally {
							closeResultset (res2);
						}
					}

					this.fimTransacao (true);

					list.add (edVolta);
				}
			}
			catch (SQLException e) {
				throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(Movimento_ConhecimentoED ed)");
			}
		}
		finally {
			closeResultset (res);
		}

		VL_Margem = (VL_Total_Frete - VL_Total_Custo);

		Movimento_ConhecimentoED edVolta = new Movimento_ConhecimentoED ();
		edVolta.setOID_Movimento_Conhecimento ("");

		edVolta.setNM_Fornecedor ("");
		edVolta.setDT_Movimento_Conhecimento ("");
		edVolta.setHR_Movimento_Conhecimento ("");
		edVolta.setOID_Tabela_Frete ("");
		edVolta.setNR_Conhecimento (ed.getNR_Conhecimento ());
		edVolta.setDT_Emissao ("");
		edVolta.setOID_Lote ("");
		edVolta.setDM_Previsto_Realizado ("");

		edVolta.setDM_Lancado_Gerado ("G");

		edVolta.setNM_Tipo_Movimento ("  Margem  ");
		edVolta.setVL_Movimento_Formatado (dec.format (VL_Margem));
		edVolta.setPE_Margem (dec.format ( (VL_Margem / VL_Total_Frete) * 100));

		list.add (edVolta);
		return list;
	}

	public Movimento_ConhecimentoED getByRecord (Movimento_ConhecimentoED ed) throws Excecoes {
		Movimento_ConhecimentoED edVolta = new Movimento_ConhecimentoED ();

		String sql =
			" SELECT Movimentos_Conhecimentos.OID_Movimento_Conhecimento, " +
			"        Movimentos_Conhecimentos.OID_Fornecedor, " +
			"        Movimentos_Conhecimentos.OID_Usuario, " +
			"        Movimentos_Conhecimentos.VL_Previsto, " +
			"        Movimentos_Conhecimentos.VL_Realizado, " +
			"        Movimentos_Conhecimentos.OID_Lote_Fornecedor, " +
			"        Movimentos_Conhecimentos.OID_Tipo_Movimento, " +
			"        Movimentos_Conhecimentos.DT_Movimento_Conhecimento, " +
			"        Movimentos_Conhecimentos.TX_Observacao, " +
			"        Movimentos_Conhecimentos.DM_Lancado_Gerado, " +
			"        Movimentos_Conhecimentos.VL_Movimento, " +
			"        Movimentos_Conhecimentos.HR_Movimento_Conhecimento, " +
			"        Movimentos_Conhecimentos.DM_Tipo_Movimento, ";
		if ("I".equals (ed.getDM_Nacional_Internacional ())) {
			sql +=
				"        Conhecimentos_Internacionais.oid_Conhecimento, " +
				"        Conhecimentos_Internacionais.oid_Pessoa, " +
				"        Conhecimentos_Internacionais.oid_Pessoa_Destinatario, " +
				"        Conhecimentos_Internacionais.NR_Conhecimento, " +
				"        Conhecimentos_Internacionais.DT_Emissao, " +
				"        Conhecimentos_Internacionais.DM_Impresso, " +
				"        Conhecimentos_Internacionais.oid_Unidade, " +
				"        Conhecimentos_Internacionais.NM_Pessoa_Entrega, ";
		}
		else {
			sql +=
				"        Conhecimentos.oid_Conhecimento, " +
				"        Conhecimentos.oid_Pessoa, " +
				"        Conhecimentos.oid_Pessoa_Destinatario, " +
				"        Conhecimentos.NR_Conhecimento, " +
				"        Conhecimentos.DT_Emissao, " +
				"        Conhecimentos.DM_Impresso, " +
				"        Conhecimentos.oid_Unidade, " +
				"        Conhecimentos.NM_Pessoa_Entrega, ";
		}
		sql +=
			"        Tipos_Movimentos.DM_Nacional_Internacional,  " +
			"        Tipos_Movimentos.DM_calculo_misto " +
			" FROM Tipos_Movimentos, Movimentos_Conhecimentos, ";
		if ("I".equals (ed.getDM_Nacional_Internacional ())) {
			sql += "      Conhecimentos_Internacionais  " +
			"  WHERE Movimentos_Conhecimentos.oid_Conhecimento = Conhecimentos_Internacionais.oid_Conhecimento ";

		}
		else {
			sql += "      Conhecimentos  " +
			"  WHERE Movimentos_Conhecimentos.oid_Conhecimento = Conhecimentos.oid_Conhecimento ";
		}
		sql += " AND Movimentos_Conhecimentos.OID_Tipo_Movimento = Tipos_Movimentos.OID_Tipo_Movimento ";
		if (String.valueOf (ed.getOID_Movimento_Conhecimento ()) != null &&
				!String.valueOf (ed.getOID_Movimento_Conhecimento ()).equals ("") &&
				!String.valueOf (ed.getOID_Movimento_Conhecimento ()).equals ("null")) {
			sql += " and OID_Movimento_Conhecimento = '" + ed.getOID_Movimento_Conhecimento () + "'";
		}
		else {
			if (String.valueOf (ed.getOID_Conhecimento ()) != null &&
					!String.valueOf (ed.getOID_Conhecimento ()).equals ("") &&
					String.valueOf (ed.getOID_Tipo_Movimento ()) != null &&
					!String.valueOf (ed.getOID_Tipo_Movimento ()).equals ("")) {
				sql += " AND Movimentos_Conhecimentos.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'" +
					   " AND    (Tipos_Movimentos.DM_Lancamento_Multiplo is null OR  Tipos_Movimentos.DM_Lancamento_Multiplo='N' )" +				
  					   " AND    Movimentos_Conhecimentos.OID_Tipo_Movimento = " + ed.getOID_Tipo_Movimento ();
			}
		}
		FormataDataBean DataFormatada = new FormataDataBean ();

		// System.out.println (" getByRecord ok" + sql);

		ResultSet res = this.executasql.executarConsulta (sql);
		try {
			try {
				while (res.next ()) {
					// System.out.println (" getByRecord ok");
					this.inicioTransacao ();
					this.executasql = this.sql;

					edVolta.setOID_Movimento_Conhecimento (res.getString ("OID_Movimento_Conhecimento"));
					edVolta.setOID_Tipo_Movimento (res.getLong ("OID_Tipo_Movimento"));
					edVolta.setOID_Usuario (res.getLong ("oid_Usuario"));
					edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
					edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
					edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
					edVolta.setNM_Pessoa_Entrega (res.getString ("NM_Pessoa_Entrega"));
					edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));
					edVolta.setDM_Impresso (res.getString ("DM_Impresso"));
					edVolta.setTX_Observacao (res.getString ("TX_Observacao"));

					edVolta.setVL_Movimento (res.getDouble ("VL_Movimento"));
					// System.out.println (" getByRecord ok 2");

					edVolta.setDT_Movimento_Conhecimento (res.getString ("DT_Movimento_Conhecimento"));
					DataFormatada.setDT_FormataData (edVolta.getDT_Movimento_Conhecimento ());
					edVolta.setDT_Movimento_Conhecimento (DataFormatada.getDT_FormataData ());

					edVolta.setHR_Movimento_Conhecimento (res.getString ("HR_Movimento_Conhecimento"));

					edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
					DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
					edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

					edVolta.setOID_Unidade (res.getLong ("oid_Unidade"));

					edVolta.setOID_Lote_Fornecedor (res.getLong ("OID_Lote_Fornecedor"));
					edVolta.setVL_Previsto (res.getDouble ("VL_Previsto"));
					edVolta.setVL_Realizado (res.getDouble ("VL_Realizado"));

					edVolta.setDM_Tipo_Movimento (res.getString ("DM_Tipo_Movimento"));
					edVolta.setDM_Lancado_Gerado (res.getString ("DM_Lancado_Gerado"));

					if ("M".equals (res.getString ("DM_calculo_misto"))) {
						edVolta.setDM_Tipo_Movimento ("");
						edVolta.setDM_Lancado_Gerado ("");
					}

					// System.out.println (" getByRecord ok 3");

					edVolta.setNM_Fornecedor ("");
					edVolta.setOID_Fornecedor ("");
					if (res.getString ("OID_Fornecedor") != null && !res.getString ("OID_Fornecedor").equals ("null") && !res.getString ("OID_Fornecedor").equals ("")) {

						// System.out.println (" getByRecord ok 4");

						sql = " SELECT NM_Razao_Social from Pessoas WHERE oid_Pessoa='" + res.getString ("OID_Fornecedor") + "'";
						// System.out.println ("SELECT2 " + sql);
						ResultSet res2 = this.executasql.executarConsulta (sql);
						try {
							while (res2.next ()) {
								// System.out.println (" getByRecord ok 5");
								edVolta.setOID_Fornecedor (res.getString ("OID_Fornecedor"));
								edVolta.setNM_Fornecedor (res2.getString ("NM_Razao_Social"));
							}
						}
						finally {
							closeResultset (res2);
						}
					}
					edVolta.setDM_Nacional_Internacional (res.getString ("DM_Nacional_Internacional"));

					this.fimTransacao (true);
				}
				return edVolta;
			}
			catch (SQLException e) {
				throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecord(Movimento_ConhecimentoED ed)");
			}
		}
		finally {
			closeResultset (res);
		}
	}

	public Movimento_ConhecimentoED consulta_Cotacao (Movimento_ConhecimentoED ed) throws Excecoes {
		Movimento_ConhecimentoED edVolta = new Movimento_ConhecimentoED ();

		String sql =
			" SELECT Movimentos_Conhecimentos.OID_Movimento_Conhecimento, " +
			"        Movimentos_Conhecimentos.OID_Fornecedor, " +
			"        Movimentos_Conhecimentos.VL_Previsto, " +
			"        Movimentos_Conhecimentos.VL_Realizado, " +
			"        Movimentos_Conhecimentos.OID_Lote_Fornecedor, " +
			"        Movimentos_Conhecimentos.OID_Tipo_Movimento, " +
			"        Movimentos_Conhecimentos.DT_Movimento_Conhecimento, " +
			"        Movimentos_Conhecimentos.DM_Lancado_Gerado, " +
			"        Movimentos_Conhecimentos.VL_Movimento, " +
			"        Movimentos_Conhecimentos.HR_Movimento_Conhecimento, " +
			"        Movimentos_Conhecimentos.DM_Tipo_Movimento, " +
			"        Cotacoes.*, " +
			"        Tipos_Movimentos.DM_Nacional_Internacional,  " +
			"        Tipos_Movimentos.DM_calculo_misto " +
			" FROM Tipos_Movimentos, Movimentos_Conhecimentos, " +
			"      Cotacoes  " +
			"  WHERE Movimentos_Conhecimentos.oid_Cotacao = Cotacoes.oid_Cotacao ";

		if (String.valueOf (ed.getOID_Movimento_Conhecimento ()) != null &&
				!String.valueOf (ed.getOID_Movimento_Conhecimento ()).equals ("") &&
				!String.valueOf (ed.getOID_Movimento_Conhecimento ()).equals ("null")) {
			sql += " and OID_Movimento_Conhecimento = '" + ed.getOID_Movimento_Conhecimento () + "'";
		}
		else {
			if (String.valueOf (ed.getOID_Cotacao ()) != null &&
					!String.valueOf (ed.getOID_Cotacao ()).equals ("") &&
					String.valueOf (ed.getOID_Tipo_Movimento ()) != null &&
					!String.valueOf (ed.getOID_Tipo_Movimento ()).equals ("")) {
				sql += " and Movimentos_Conhecimentos.OID_Cotacao = '" + ed.getOID_Cotacao () + "'" +
				" and Movimentos_Conhecimentos.OID_Tipo_Movimento = " + ed.getOID_Tipo_Movimento ();
			}

		}

		FormataDataBean DataFormatada = new FormataDataBean ();

		// System.out.println (" getByRecord ok" + sql);

		ResultSet res = this.executasql.executarConsulta (sql);
		try {
			try {
				while (res.next ()) {
					// System.out.println (" getByRecord ok");
					this.inicioTransacao ();
					this.executasql = this.sql;

					edVolta.setOID_Movimento_Conhecimento (res.getString ("OID_Movimento_Conhecimento"));
					edVolta.setOID_Tipo_Movimento (res.getLong ("OID_Tipo_Movimento"));
					edVolta.setOID_Cotacao (res.getString ("OID_Cotacao"));
					edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
					edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
					edVolta.setNR_Cotacao (res.getLong ("NR_Cotacao"));
					edVolta.setDM_Impresso (res.getString ("DM_Impresso"));
					edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

					edVolta.setVL_Movimento (res.getDouble ("VL_Movimento"));
					// System.out.println (" getByRecord ok 2");

					edVolta.setDT_Movimento_Conhecimento (res.getString ("DT_Movimento_Conhecimento"));
					DataFormatada.setDT_FormataData (edVolta.getDT_Movimento_Conhecimento ());
					edVolta.setDT_Movimento_Conhecimento (DataFormatada.getDT_FormataData ());

					edVolta.setHR_Movimento_Conhecimento (res.getString ("HR_Movimento_Conhecimento"));

					edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
					DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
					edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

					edVolta.setOID_Unidade (res.getLong ("oid_Unidade"));

					edVolta.setVL_Previsto (res.getDouble ("VL_Previsto"));
					edVolta.setVL_Realizado (res.getDouble ("VL_Realizado"));

					edVolta.setDM_Tipo_Movimento (res.getString ("DM_Tipo_Movimento"));
					edVolta.setDM_Lancado_Gerado (res.getString ("DM_Lancado_Gerado"));

					if ("M".equals (res.getString ("DM_calculo_misto"))) {
						edVolta.setDM_Tipo_Movimento ("");
						edVolta.setDM_Lancado_Gerado ("");
					}

					// System.out.println (" getByRecord ok 3");

					edVolta.setNM_Fornecedor ("");

					edVolta.setOID_Fornecedor (" ");
					if (res.getString ("OID_Fornecedor") != null && !res.getString ("OID_Fornecedor").equals ("null") && !res.getString ("OID_Fornecedor").equals ("")) {

						// System.out.println (" getByRecord ok 4");

						sql = " SELECT NM_Razao_Social from Pessoas WHERE oid_Pessoa='" + res.getString ("OID_Fornecedor") + "'";
						// System.out.println ("SELECT2 " + sql);
						ResultSet res2 = this.executasql.executarConsulta (sql);
						try {
							while (res2.next ()) {
								// System.out.println (" getByRecord ok 5");
								edVolta.setOID_Fornecedor (res.getString ("OID_Fornecedor"));
								edVolta.setNM_Fornecedor (res2.getString ("NM_Razao_Social"));
							}
						}
						finally {
							closeResultset (res2);
						}
					}

					this.fimTransacao (true);
				}
				return edVolta;
			}
			catch (SQLException e) {
				throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecord(Movimento_ConhecimentoED ed)");
			}
		}
		finally {
			closeResultset (res);
		}
	}

	public void geraRelatorio (Movimento_ConhecimentoED ed) throws Excecoes {

		String sql = null;

		Movimento_ConhecimentoED edVolta = new Movimento_ConhecimentoED ();

		try {

			sql = "select * from Movimento_Conhecimentos where 1=1";

			ResultSet res = null;
			res = this.executasql.executarConsulta (sql);

			Movimento_ConhecimentoRL Movimento_Conhecimento_rl = new Movimento_ConhecimentoRL ();
			Movimento_Conhecimento_rl.geraRelatEstoque (res);
		}
		catch (Excecoes e) {
			throw e;
		}
		catch (Exception exc) {
			Excecoes exce = new Excecoes ();
			exce.setExc (exc);
			exce.setMensagem ("Erro no método listar");
			exce.setClasse (this.getClass ().getName ());
			exce.setMetodo ("geraRelatorio(Movimento_ConhecimentoED ed)");
		}

	}

	public Movimento_ConhecimentoED calcula_Margem (Movimento_ConhecimentoED ed) throws Excecoes {

		// System.out.println ("=============calcula_Margem============== ");

		String sql = null;
		//Movimento_ConhecimentoED movCtoED = this.consulta_Margem (ed , executasql);
		Movimento_ConhecimentoED movCtoED = this.consulta_Margem (ed);

		try {

			if ("I".equals (ed.getDM_Nacional_Internacional ())) {
				sql = " update Conhecimentos_Internacionais set ";
			}
			else {
				sql = " update Conhecimentos set ";
			}
			sql +=
				"  VL_Custo_Coleta = " + movCtoED.getVL_Custo_Coleta () +
				" ,VL_Ressarcimento = " + movCtoED.getVL_Ressarcimento () +
				" ,VL_Custo_Transferencia = " + movCtoED.getVL_Custo_Transferencia () +
				" ,VL_Custo_Entrega = " + movCtoED.getVL_Custo_Entrega () +
				" ,VL_Custo_Seguro = " + movCtoED.getVL_Custo_Seguro () +
				" ,VL_Custo_Imposto = " + movCtoED.getVL_Custo_Imposto () +
				" ,VL_Custo_Outros  = " + movCtoED.getVL_Custo_Outros () +
				" ,VL_Custo_Monitoramento  = " + movCtoED.getVL_Custo_Monitoramento () +
				" ,VL_Custo_Comunicacao  = " + movCtoED.getVL_Custo_Comunicacao () +
				" ,VL_Custo_Gerenciamento_Risco  = " + movCtoED.getVL_Custo_Gerenciamento_Risco () +
				" ,VL_Custo_Comissao  = " + movCtoED.getVL_Custo_Comissao () +
				" ,VL_Custo_Financeiro  = " + movCtoED.getVL_Custo_Financeiro () +
				" ,VL_Custo_Administrativo  = " + movCtoED.getVL_Custo_Administrativo () +
				" ,VL_Total_Custo  = " + movCtoED.getVL_Total_Custo () +
				" where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

			// System.out.println ("Movimento Conhecimento  calc margem update cto " + ed.getOID_Conhecimento ());
			//new Movimento_ConhecimentoBD (executasql).executarUpdate (sql);

			executasql.executarUpdate (sql);

			movCtoED.setVL_Despesas (movCtoED.getVL_Total_Custo ());
			//// System.out.println ("Movimento Conhecimento update cto ok");

		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao incluir");
			excecoes.setMetodo ("inclui");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return movCtoED;
	}

	public Movimento_ConhecimentoED calcula_MargemOLLLD (Movimento_ConhecimentoED ed) throws Excecoes {

		// System.out.println ("Movimento Conhecimento  calc margem 1 ");

		String sql = null;
		double VL_Ressarcimento = 0 , VL_Total_Custo = 0 , VL_Custo_Coleta = 0 , VL_Custo_Transferencia = 0 , VL_Custo_Entrega = 0 , VL_Custo_Imposto = 0 , VL_Custo_Seguro = 0 , VL_Custo_Outros = 0;
		ResultSet rs = null;
		Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();

		try {

			sql = " SELECT * FROM Tipos_Movimentos, Movimentos_Conhecimentos " +
			" WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento" +
			" AND Tipos_Movimentos.DM_Tipo_Movimento = 'D'" +
			" AND Movimentos_Conhecimentos.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

			rs = this.executasql.executarConsulta (sql);

			while (rs.next ()) {
				// System.out.println ("achou mov 1 ");
				VL_Total_Custo = VL_Total_Custo + rs.getDouble ("VL_Movimento");
				if (rs.getString ("DM_Totaliza").equals ("K")) {
					VL_Custo_Coleta = VL_Custo_Coleta + rs.getDouble ("VL_Movimento");
				}
				if (rs.getString ("DM_Totaliza").equals ("T")) {
					VL_Custo_Transferencia = VL_Custo_Transferencia + rs.getDouble ("VL_Movimento");
				}
				if (rs.getString ("DM_Totaliza").equals ("M")) {
					VL_Custo_Entrega = VL_Custo_Entrega + rs.getDouble ("VL_Movimento");
				}
				if (rs.getString ("DM_Totaliza").equals ("U") || //icms.pis,cofins
						rs.getString ("DM_Totaliza").equals ("9") ||
						rs.getString ("DM_Totaliza").equals ("V") ||
						rs.getString ("DM_Totaliza").equals ("X")) {
					VL_Custo_Imposto = VL_Custo_Imposto + rs.getDouble ("VL_Movimento");
				}
				if (rs.getString ("DM_Totaliza").equals ("S")) {
					VL_Custo_Seguro = VL_Custo_Seguro + rs.getDouble ("VL_Movimento");
				}
				// System.out.println ("achou mov 9 ");

				if (VL_Total_Custo > (VL_Custo_Coleta + VL_Custo_Transferencia + VL_Custo_Entrega + VL_Custo_Imposto + VL_Custo_Seguro)) {
					VL_Custo_Outros = VL_Total_Custo - (VL_Custo_Coleta + VL_Custo_Transferencia + VL_Custo_Entrega + VL_Custo_Imposto + VL_Custo_Seguro);
				}
			}

			sql = " SELECT * FROM Tipos_Movimentos, Movimentos_Conhecimentos " +
			" WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento" +
			" AND Tipos_Movimentos.DM_Tipo_Movimento = 'R'" +
			" AND Movimentos_Conhecimentos.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

			rs = this.executasql.executarConsulta (sql);

			while (rs.next ()) {
				VL_Ressarcimento = VL_Ressarcimento + rs.getDouble ("VL_Movimento");
			}

			// System.out.println ("acerta VL_Total_Custo = " + VL_Total_Custo);

			if ("I".equals (ed.getDM_Nacional_Internacional ())) {
				sql = " update Conhecimentos_Internacionais set ";
			}
			else {
				sql = " update Conhecimentos set ";
			}
			sql +=
				"  VL_Custo_Coleta = " + VL_Custo_Coleta +
				" ,VL_Ressarcimento = " + VL_Ressarcimento +
				" ,VL_Custo_Transferencia = " + VL_Custo_Transferencia +
				" ,VL_Custo_Entrega = " + VL_Custo_Entrega +
				" ,VL_Custo_Seguro = " + VL_Custo_Seguro +
				" ,VL_Custo_Imposto = " + VL_Custo_Imposto +
				" ,VL_Custo_Outros  = " + VL_Custo_Outros +
				" ,VL_Total_Custo  = " + VL_Total_Custo +
				" where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

			// System.out.println ("Movimento Conhecimento  calc margem update cto " + sql);

			int i = executasql.executarUpdate (sql);
			movimento_ConhecimentoED.setVL_Despesas (VL_Total_Custo);
			// System.out.println ("Movimento Conhecimento update cto ok");

		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao incluir");
			excecoes.setMetodo ("inclui");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return movimento_ConhecimentoED;
	}

	public Movimento_ConhecimentoED consulta_Margem (Movimento_ConhecimentoED ed ) throws Excecoes {

		// System.out.println ("===========consulta_Margem============ ");

		String sqlBusca = null;
		double VL_Margem = 0 , VL_Total_Custo = 0 , VL_Ressarcimento = 0 , VL_Custo_Coleta = 0 , VL_Custo_Transferencia = 0 , VL_Custo_Entrega = 0 , VL_Custo_Imposto = 0 , VL_Custo_Seguro = 0 , VL_Custo_Outros = 0;
		double VL_Custo_Monitoramento = 0 , VL_Custo_Comunicacao = 0 , VL_Custo_Gerenciamento_Risco = 0 , VL_Custo_Comissao = 0 , VL_Custo_Financeiro = 0 , VL_Custo_Administrativo = 0;
		ResultSet resLocal = null;

		try {

			sqlBusca = " SELECT VL_Total_Frete FROM Conhecimentos " +
            			" WHERE Conhecimentos.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

			resLocal = this.executasql.executarConsulta (sqlBusca);	
			while (resLocal.next ()) {
				ed.setVL_Total_Frete (resLocal.getDouble ("VL_Total_Frete"));
			}

			VL_Total_Custo = this.soma_Movimento(ed.getOID_Conhecimento (), "");
			VL_Custo_Coleta = this.soma_Movimento(ed.getOID_Conhecimento (), "K");
			VL_Custo_Transferencia = this.soma_Movimento(ed.getOID_Conhecimento (), "T");
			VL_Custo_Entrega = this.soma_Movimento(ed.getOID_Conhecimento (), "M");
			VL_Custo_Imposto = this.soma_Movimento(ed.getOID_Conhecimento (), "U");
			VL_Custo_Imposto += this.soma_Movimento(ed.getOID_Conhecimento (), "9");
			VL_Custo_Imposto += this.soma_Movimento(ed.getOID_Conhecimento (), "V");
			VL_Custo_Imposto += this.soma_Movimento(ed.getOID_Conhecimento (), "X");      
			VL_Custo_Seguro = this.soma_Movimento(ed.getOID_Conhecimento (), "S");
			VL_Custo_Outros = this.soma_Movimento(ed.getOID_Conhecimento (), "A");
			VL_Custo_Monitoramento = this.soma_Movimento(ed.getOID_Conhecimento (), "CM");
			VL_Custo_Comunicacao = this.soma_Movimento(ed.getOID_Conhecimento (), "CC");
			VL_Custo_Gerenciamento_Risco = this.soma_Movimento(ed.getOID_Conhecimento (), "GR");
			VL_Custo_Comissao = this.soma_Movimento(ed.getOID_Conhecimento (), "CV");
			VL_Custo_Financeiro = this.soma_Movimento(ed.getOID_Conhecimento (), "CF");
			VL_Custo_Administrativo = this.soma_Movimento(ed.getOID_Conhecimento (), "CAD");
			VL_Ressarcimento = 0;

			sqlBusca = " SELECT SUM (VL_Movimento) as VL_Movimento FROM Tipos_Movimentos, Movimentos_Conhecimentos " +
			" WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento" +
			" AND   Movimentos_Conhecimentos.OID_Conhecimento =  '" + ed.getOID_Conhecimento () + "'" +
			" AND   Tipos_Movimentos.DM_Tipo_Movimento = 'R' ";

			resLocal = this.executasql.executarConsulta (sqlBusca);
			if (resLocal.next ()) {
				VL_Ressarcimento += resLocal.getDouble ("VL_Movimento");
			}


			VL_Margem = ed.getVL_Total_Frete () - VL_Total_Custo + VL_Ressarcimento;

			ed.setVL_Custo_Coleta (VL_Custo_Coleta);
			ed.setVL_Custo_Transferencia (VL_Custo_Transferencia);
			ed.setVL_Custo_Entrega (VL_Custo_Entrega);
			ed.setVL_Custo_Seguro (VL_Custo_Seguro);
			ed.setVL_Custo_Administrativo (VL_Custo_Administrativo);
			ed.setVL_Custo_Outros (VL_Custo_Outros);
			ed.setVL_Custo_Imposto (VL_Custo_Imposto);
			ed.setVL_Custo_Comunicacao (VL_Custo_Comunicacao);
			ed.setVL_Custo_Comissao (VL_Custo_Comissao);
			ed.setVL_Custo_Financeiro (VL_Custo_Financeiro);
			ed.setVL_Ressarcimento (VL_Ressarcimento);
			ed.setVL_Custo_Gerenciamento_Risco (VL_Custo_Gerenciamento_Risco);
			ed.setVL_Custo_Monitoramento (VL_Custo_Monitoramento);
			ed.setVL_Total_Custo (VL_Total_Custo);
			ed.setVL_Margem (VL_Margem);

			DecimalFormat dec = new DecimalFormat ("###,###,##0.00");
			ed.setPE_Margem (dec.format (VL_Margem / (ed.getVL_Total_Frete () + VL_Ressarcimento) * 100));

		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro consultar Margem");
			excecoes.setMetodo ("consulta_Margem");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return ed;
	}

	public Movimento_ConhecimentoED consulta_MargemOLLLLD (Movimento_ConhecimentoED ed ) throws Excecoes {

		// System.out.println ("consulta_Margem-> " + ed.getOID_Conhecimento ());

		String sqlBusca = null;
		double VL_Margem = 0 , VL_Total_Custo = 0 , VL_Ressarcimento = 0 , VL_Custo_Coleta = 0 , VL_Custo_Transferencia = 0 , VL_Custo_Entrega = 0 , VL_Custo_Imposto = 0 , VL_Custo_Seguro = 0 , VL_Custo_Outros = 0;
		double VL_Custo_Monitoramento = 0 , VL_Custo_Comunicacao = 0 , VL_Custo_Gerenciamento_Risco = 0 , VL_Custo_Comissao = 0 , VL_Custo_Financeiro = 0 , VL_Custo_Administrativo = 0;
		ResultSet resLocal = null;

		try {

			VL_Total_Custo = 0;
			VL_Custo_Coleta = 0;
			VL_Custo_Transferencia = 0;
			VL_Custo_Entrega = 0;
			VL_Custo_Imposto = 0;
			VL_Custo_Seguro = 0;
			VL_Custo_Outros = 0;
			VL_Custo_Monitoramento = 0;
			VL_Custo_Comunicacao = 0;
			VL_Custo_Gerenciamento_Risco = 0;
			VL_Custo_Comissao = 0;
			VL_Custo_Financeiro = 0;
			VL_Custo_Administrativo = 0;
			VL_Ressarcimento = 0;

			sqlBusca = " SELECT VL_Total_Frete FROM Conhecimentos " +
			" WHERE Conhecimentos.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

			resLocal = executarConsulta (sqlBusca);

			while (resLocal.next ()) {
				ed.setVL_Total_Frete (resLocal.getDouble ("VL_Total_Frete"));
			}

			sqlBusca = " SELECT DM_Totaliza, VL_Movimento " +
			" FROM Tipos_Movimentos, Movimentos_Conhecimentos " +
			" WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento" +
			" AND   Movimentos_Conhecimentos.OID_Conhecimento =  '" + ed.getOID_Conhecimento () + "'" +
			" AND Tipos_Movimentos.DM_Tipo_Movimento = 'D' ";

			resLocal = executarConsulta (sqlBusca);
			boolean somou_custo = false;
			while (resLocal.next ()) {
				//// System.out.println(" totaliza_movimento ");

				VL_Total_Custo += resLocal.getDouble ("VL_Movimento");

				if (resLocal.getString ("DM_Totaliza").equals ("K")) {
					VL_Custo_Coleta += resLocal.getDouble ("VL_Movimento");
					somou_custo = true;
				}
				if (resLocal.getString ("DM_Totaliza").equals ("T")) {
					VL_Custo_Transferencia += resLocal.getDouble ("VL_Movimento");
					somou_custo = true;
				}
				if (resLocal.getString ("DM_Totaliza").equals ("M")) {
					VL_Custo_Entrega += resLocal.getDouble ("VL_Movimento");
					somou_custo = true;
				}
				if (resLocal.getString ("DM_Totaliza").equals ("U") || //icms.pis,cofins
						resLocal.getString ("DM_Totaliza").equals ("9") ||
						resLocal.getString ("DM_Totaliza").equals ("V") ||
						resLocal.getString ("DM_Totaliza").equals ("X")) {
					VL_Custo_Imposto += resLocal.getDouble ("VL_Movimento");
					somou_custo = true;
				}
				if (resLocal.getString ("DM_Totaliza").equals ("S")) {
					VL_Custo_Seguro += resLocal.getDouble ("VL_Movimento");
					somou_custo = true;
				}

				if (resLocal.getString ("DM_Totaliza").equals ("CM")) {
					VL_Custo_Monitoramento += resLocal.getDouble ("VL_Movimento");
					somou_custo = true;
				}

				if (resLocal.getString ("DM_Totaliza").equals ("CC")) {
					VL_Custo_Comunicacao += resLocal.getDouble ("VL_Movimento");
					somou_custo = true;
				}

				if (resLocal.getString ("DM_Totaliza").equals ("GR")) {
					VL_Custo_Gerenciamento_Risco += resLocal.getDouble ("VL_Movimento");
					somou_custo = true;
				}

				if (resLocal.getString ("DM_Totaliza").equals ("CV")) {
					VL_Custo_Comissao += resLocal.getDouble ("VL_Movimento");
					somou_custo = true;
				}

				if (resLocal.getString ("DM_Totaliza").equals ("CF")) {
					VL_Custo_Financeiro += resLocal.getDouble ("VL_Movimento");
					somou_custo = true;
				}

				if (resLocal.getString ("DM_Totaliza").equals ("CAD")) {
					VL_Custo_Administrativo += resLocal.getDouble ("VL_Movimento");
					somou_custo = true;
				}

				if (resLocal.getString ("DM_Totaliza").equals ("A")) {
					VL_Custo_Outros += resLocal.getDouble ("VL_Movimento");
					somou_custo = true;
				}

				if (!somou_custo) {
					VL_Custo_Outros += resLocal.getDouble ("VL_Movimento");
					somou_custo = true;
				}

			}

			sqlBusca = " SELECT SUM (VL_Movimento) as VL_Movimento FROM Tipos_Movimentos, Movimentos_Conhecimentos " +
			" WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento" +
			" AND   Movimentos_Conhecimentos.OID_Conhecimento =  '" + ed.getOID_Conhecimento () + "'" +
			" AND   Tipos_Movimentos.DM_Tipo_Movimento = 'R' ";

			resLocal = executarConsulta (sqlBusca);
			while (resLocal.next ()) {
				VL_Ressarcimento += resLocal.getDouble ("VL_Movimento");
			}

			VL_Margem = ed.getVL_Total_Frete () - VL_Total_Custo + VL_Ressarcimento;

			ed.setVL_Custo_Coleta (VL_Custo_Coleta);
			ed.setVL_Custo_Transferencia (VL_Custo_Transferencia);
			ed.setVL_Custo_Entrega (VL_Custo_Entrega);
			ed.setVL_Custo_Seguro (VL_Custo_Seguro);
			ed.setVL_Custo_Administrativo (VL_Custo_Administrativo);
			ed.setVL_Custo_Outros (VL_Custo_Outros);
			ed.setVL_Custo_Imposto (VL_Custo_Imposto);
			ed.setVL_Custo_Comunicacao (VL_Custo_Comunicacao);
			ed.setVL_Custo_Comissao (VL_Custo_Comissao);
			ed.setVL_Custo_Financeiro (VL_Custo_Financeiro);
			ed.setVL_Ressarcimento (VL_Ressarcimento);
			ed.setVL_Custo_Gerenciamento_Risco (VL_Custo_Gerenciamento_Risco);
			ed.setVL_Custo_Monitoramento (VL_Custo_Monitoramento);
			ed.setVL_Total_Custo (VL_Total_Custo);
			ed.setVL_Margem (VL_Margem);

			DecimalFormat dec = new DecimalFormat ("###,###,##0.00");
			ed.setPE_Margem (dec.format (VL_Margem / (ed.getVL_Total_Frete () + VL_Ressarcimento) * 100));

		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro consultar Margem");
			excecoes.setMetodo ("consulta_Margem");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return ed;
	}

	public Movimento_ConhecimentoED inclui_Rateio (Movimento_ConhecimentoED ed) throws Excecoes {

		String sql = null;
		long valOid = 0;
		String chave = null;

		double VL_Total_Volumes = 0;
		double VL_Total_Frete = 0;
		double VL_Total_Peso = 0;
		double VL_Total_Nota_Fiscal = 0;
		double VL_Movimento = 0;

		// System.out.print ("inclui_Rateio 1");

		Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();

		try {

			sql = " select SUM(Conhecimentos.nr_volumes) as VL_Total_Volumes, " +
			" SUM(Conhecimentos.vl_total_frete)          as VL_Total_Frete, " +
			" SUM(Conhecimentos.nr_peso)                 as VL_Total_Peso," +
			" SUM(Conhecimentos.vl_nota_fiscal)          as VL_Total_Nota_Fiscal" +
			" from Conhecimentos " +
			" WHERE (Conhecimentos.DM_Situacao = 'G' " +
			" or Conhecimentos.DM_Situacao = 'F')" +
			" AND Conhecimentos.DM_Impresso = 'S' " +
			" AND Conhecimentos.VL_Total_Frete > 0";

			if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
				sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
			}
			if (String.valueOf (ed.getDT_Movimento_Inicial ()) != null &&
					!String.valueOf (ed.getDT_Movimento_Inicial ()).equals ("") &&
					!String.valueOf (ed.getDT_Movimento_Inicial ()).equals ("null")) {
				sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Movimento_Inicial () + "'";
			}
			if (String.valueOf (ed.getDT_Movimento_Final ()) != null &&
					!String.valueOf (ed.getDT_Movimento_Final ()).equals ("") &&
					!String.valueOf (ed.getDT_Movimento_Final ()).equals ("null")) {
				sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Movimento_Final () + "'";
			}
			if (String.valueOf (ed.getOID_Pessoa ()) != null &&
					!String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
					!String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
				sql += " and Conhecimentos.oid_Pessoa = '" + ed.getOID_Pessoa () + "'";
			}

			ResultSet res = null;
			res = this.executasql.executarConsulta (sql);

			while (res.next ()) {
				VL_Total_Volumes = res.getDouble ("VL_Total_Volumes");
				VL_Total_Frete = res.getDouble ("VL_Total_Frete");
				VL_Total_Peso = res.getDouble ("VL_Total_Peso");
				VL_Total_Nota_Fiscal = res.getDouble ("VL_Total_Nota_Fiscal");
			}

			// System.out.print ("inclui_Rateio TOT Frete=" + VL_Total_Frete);

			sql = " select Conhecimentos.OID_Conhecimento, " +
			" Conhecimentos.nr_volumes, " +
			" Conhecimentos.vl_total_frete, " +
			" Conhecimentos.nr_peso," +
			" Conhecimentos.vl_nota_fiscal" +
			" from Conhecimentos " +
			" WHERE (Conhecimentos.DM_Situacao = 'G' " +
			" or Conhecimentos.DM_Situacao = 'F')" +
			" AND Conhecimentos.DM_Impresso = 'S' " +
			" AND Conhecimentos.VL_Total_Frete > 0";

			if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
				sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
			}
			if (String.valueOf (ed.getDT_Movimento_Inicial ()) != null &&
					!String.valueOf (ed.getDT_Movimento_Inicial ()).equals ("") &&
					!String.valueOf (ed.getDT_Movimento_Inicial ()).equals ("null")) {
				sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Movimento_Inicial () + "'";
			}
			if (String.valueOf (ed.getDT_Movimento_Final ()) != null &&
					!String.valueOf (ed.getDT_Movimento_Final ()).equals ("") &&
					!String.valueOf (ed.getDT_Movimento_Final ()).equals ("null")) {
				sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Movimento_Final () + "'";
			}
			if (String.valueOf (ed.getOID_Pessoa ()) != null &&
					!String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
					!String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
				sql += " and Conhecimentos.oid_Pessoa = '" + ed.getOID_Pessoa () + "'";
			}

			res = null;
			res = this.executasql.executarConsulta (sql);

			while (res.next ()) {

				// System.out.print ("inclui_Rateio Cto=" + res.getString ("OID_Conhecimento"));

				if ("1".equals (ed.getDM_Tipo_Rateio ()) && VL_Total_Peso > 0) {
					VL_Movimento = ( (ed.getVL_Rateio () / VL_Total_Peso) * res.getDouble ("nr_peso"));
				}
				if ("2".equals (ed.getDM_Tipo_Rateio ()) && VL_Total_Volumes > 0) {
					VL_Movimento = ( (ed.getVL_Rateio () / VL_Total_Volumes) * res.getDouble ("nr_peso"));
				}
				if ("3".equals (ed.getDM_Tipo_Rateio ()) && VL_Total_Nota_Fiscal > 0) {
					VL_Movimento = ( (ed.getVL_Rateio () / VL_Total_Nota_Fiscal) * res.getDouble ("nr_peso"));
				}
				if ("4".equals (ed.getDM_Tipo_Rateio ()) && VL_Total_Frete > 0) {
					VL_Movimento = ( (ed.getVL_Rateio () / VL_Total_Frete) * res.getDouble ("nr_peso"));
				}

				ed.setVL_Movimento (VL_Movimento);
				ed.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
				ed.setDT_Movimento_Conhecimento (Data.getDataDMY ());
				ed.setHR_Movimento_Conhecimento (Data.getHoraHM ());
				ed.setDM_Lancado_Gerado ("R");
				ed.setDM_Tipo_Movimento ("D");

				// System.out.print ("inclui_Rateio VL_Movimento=" + VL_Movimento);

				if (VL_Movimento > 0.01) {
					this.inclui (ed);
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
		return movimento_ConhecimentoED;
	}

	public Movimento_ConhecimentoED deleta_Rateio (Movimento_ConhecimentoED ed) throws Excecoes {

		String sql = null;

		Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();

		try {

			sql = " select Conhecimentos.OID_Conhecimento " +
			" from Conhecimentos " +
			" WHERE (Conhecimentos.DM_Situacao = 'G' " +
			" or Conhecimentos.DM_Situacao = 'F')" +
			" AND Conhecimentos.DM_Impresso = 'S' " +
			" AND Conhecimentos.VL_Total_Frete > 0";

			if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
				sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
			}
			if (String.valueOf (ed.getDT_Movimento_Inicial ()) != null &&
					!String.valueOf (ed.getDT_Movimento_Inicial ()).equals ("") &&
					!String.valueOf (ed.getDT_Movimento_Inicial ()).equals ("null")) {
				sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Movimento_Inicial () + "'";
			}
			if (String.valueOf (ed.getDT_Movimento_Final ()) != null &&
					!String.valueOf (ed.getDT_Movimento_Final ()).equals ("") &&
					!String.valueOf (ed.getDT_Movimento_Final ()).equals ("null")) {
				sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Movimento_Final () + "'";
			}
			if (String.valueOf (ed.getOID_Pessoa ()) != null &&
					!String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
					!String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
				sql += " and Conhecimentos.oid_Pessoa = '" + ed.getOID_Pessoa () + "'";
			}

			ResultSet res = null;
			res = this.executasql.executarConsulta (sql);

			while (res.next ()) {
				sql = "delete from Movimentos_Conhecimentos WHERE DM_Lancado_Gerado = 'R' and oid_Conhecimento = '";
				sql += res.getString ("OID_Conhecimento") + "'";
				sql += " AND OID_Tipo_Movimento = " + ed.getOID_Tipo_Movimento ();

				int i = executasql.executarUpdate (sql);

				ed.setOID_Conhecimento (res.getString ("OID_Conhecimento"));

				this.inicioTransacao ();

				this.calcula_Margem (ed);

				this.fimTransacao (true);

			}
		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao deletar");
			excecoes.setMetodo ("Movimento_ConhecimentoED deleta_Rateio(Movimento_ConhecimentoED ed)");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return movimento_ConhecimentoED;
	}

	public void verifica_Custo_Minuta (String oid_Minuta) throws Excecoes {

		// System.out.println ("verifica_Lancamento_Lote_Faturameto oid_Minuta=>" + oid_Minuta);

		String sql = "";
		ResultSet res = null;
		int qt_Mov = 0;
		String DM_Tipo_Documento = "";
		Movimento_ConhecimentoED ed = new Movimento_ConhecimentoED ();

		try {

			sql = " SELECT DM_Tipo_Documento   " +
			" FROM   Conhecimentos " +
			" WHERE  Conhecimentos.oid_Lote_Faturamento>0 " +
			" AND    Conhecimentos.oid_Conhecimento ='" + oid_Minuta + "'";
			// System.out.println (sql);

			res = this.executasql.executarConsulta (sql);
			if (res.next ()) {
				DM_Tipo_Documento = res.getString ("DM_Tipo_Documento");
			}
			if ("M".equals (DM_Tipo_Documento)) {

				sql = " SELECT Conhecimentos.oid_Lote_Faturamento, Conhecimentos.oid_Conhecimento, Conhecimentos.NR_Conhecimento  " +
				" FROM   Conhecimentos, Conhecimentos Minutas " +
				" WHERE  Conhecimentos.oid_Lote_Faturamento = Minutas.oid_Lote_Faturamento " +
				" AND    Conhecimentos.DM_Impresso='S' " +
				" AND    Conhecimentos.DM_Tipo_Documento='C' " +
				" AND    Minutas.oid_Lote_Faturamento>0 " +
				" AND    Minutas.oid_Conhecimento ='" + oid_Minuta + "'";

				// System.out.println (sql);

				res = this.executasql.executarConsulta (sql);
				if (res.next ()) {

					// System.out.println ("oid_Lote_Faturamento=>" + res.getLong ("oid_Lote_Faturamento"));
					// System.out.println ("NR_Conhecimento=>" + res.getLong ("NR_Conhecimento"));

					sql = " SELECT SUM (Movimentos_Conhecimentos.VL_Movimento) as vl_movimento, Tipos_Movimentos.oid_Tipo_Movimento " +
					" FROM Movimentos_Conhecimentos, Documentos_Lotes_Faturamentos, Tipos_Movimentos, Conhecimentos " +
					" WHERE  Movimentos_Conhecimentos.oid_Conhecimento = Documentos_Lotes_Faturamentos.oid_Conhecimento " +
					" AND  Movimentos_Conhecimentos.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
					" AND  Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento " +
					" AND  Tipos_Movimentos.DM_Tipo_Movimento = 'D' " +
					" AND  Conhecimentos.DM_Tipo_Documento = 'M' " +
					" AND  Documentos_Lotes_Faturamentos.oid_Lote_Faturamento = '" + res.getLong ("oid_Lote_Faturamento") + "'" +
					" GROUP BY  Tipos_Movimentos.oid_Tipo_Movimento ";

					// System.out.println (" sql exclui nao impressos " + sql);

					ResultSet rsDoc = this.executasql.executarConsulta (sql);

					while (rsDoc.next ()) {
						// System.out.println ("achou tipo mov >> " + rsDoc.getString ("oid_Tipo_Movimento"));
						// System.out.println ("achou tipo mov >> " + rsDoc.getDouble ("vl_movimento"));

						if (rsDoc.getDouble ("vl_movimento")>0){
							sql = " DELETE FROM Movimentos_Conhecimentos " +
							" WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = " + rsDoc.getLong ("oid_Tipo_Movimento") +
							" AND   Movimentos_Conhecimentos.oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";

							// System.out.println ("deletar -> " + sql);

							executasql.executarUpdate (sql);

							ed.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
							ed.setOID_Tipo_Movimento (rsDoc.getLong ("oid_Tipo_Movimento"));
							ed.setVL_Movimento (rsDoc.getDouble ("vl_movimento"));
							ed.setDT_Movimento_Conhecimento (Data.getDataDMY ());
							ed.setHR_Movimento_Conhecimento (Data.getHoraHM ());
							ed.setDM_Tipo_Movimento ("D");
							ed.setDM_Lancado_Gerado ("G");

							sql = " insert into Movimentos_Conhecimentos (OID_Movimento_Conhecimento, OID_Fornecedor, NR_Documento, NR_Movimento_Conhecimento, VL_Movimento, OID_Conhecimento, OID_Ordem_Frete, OID_Cotacao, OID_Tipo_Movimento, DT_Movimento_Conhecimento, HR_Movimento_Conhecimento, DM_Tipo_Movimento, DM_Lancado_Gerado , OID_Tabela_Frete, OID_Lote_Fornecedor, VL_Previsto, VL_Realizado, VL_Tarifa, TX_OBservacao, OID_Manifesto, NR_Postagem) values ";
							sql += "('" + String.valueOf (System.currentTimeMillis ()).toString () + "','" + ed.getOID_Fornecedor () + "','" + ed.getNR_Documento () + "'," + ed.getNR_Movimento_Conhecimento () + "," + ed.getVL_Movimento () + ",'" + ed.getOID_Conhecimento () + "','" + ed.getOID_Ordem_Frete () + "','" + ed.getOID_Cotacao () +
							"'," +
							ed.getOID_Tipo_Movimento () + ",'" +
							ed.getDT_Movimento_Conhecimento () + "','" + ed.getHR_Movimento_Conhecimento () + "','" + ed.getDM_Tipo_Movimento () + "','" + ed.getDM_Lancado_Gerado () + "','" + ed.getOID_Tabela_Frete () + "'," + ed.getOID_Lote_Fornecedor () + "," + ed.getVL_Previsto () + "," +
							ed.getVL_Realizado () + ", " + ed.getVL_Tarifa () + ",'" + ed.getTX_Observacao () + "','" + ed.getOID_Manifesto () + "','" + ed.getNR_Postagem () + "')";

							// System.out.println ("inclui->" + sql);

							executasql.executarUpdate (sql);

							qt_Mov++;
						}
					}

					if (qt_Mov > 0) {
						ed.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
						// System.out.println ("calcula margem -> " + sql);
						calcula_Margem (ed);
					}
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace ();
			throw new Excecoes (e.getMessage () , e , getClass ().getName () , "verifica_Lancamento_Lote_Faturameto(Movimento_ConhecimentoED ed)");
		}
		catch (Exception e) {
			e.printStackTrace ();
			throw new Excecoes (e.getMessage () , e , getClass ().getName () , "verifica_Lancamento_Lote_Faturameto(Movimento_ConhecimentoED ed)");
		}
		return ;
	}


	public Lote_FornecedorED atualiza_Lote_Fornecedor(Lote_FornecedorED ed)throws Excecoes{
		Parametro_FixoED  edParametro_Fixo = new Parametro_FixoED();

		String sql = null;
		double vl_Desconto=0;
		double vl_Rateio=0;
		double NR_Peso_Total = 0;
		double vl_Movimento = 0;
		int qt_ctos=0, qt_ctos_ler=9999;
		try{

			sql = " update Documentos_Lotes_Fornecedores set  dm_atualiza1='', dm_atualiza2='', oid_Movimento_Conhecimento = '' ";
			sql += " where oid_Lote_Fornecedor = " + ed.getOID_Lote_Fornecedor();
			// System.out.println ("altera " + sql);
			executasql.executarUpdate (sql);

			sql =  " SELECT *, Lotes_Fornecedores.oid_lote_fornecedor " +
			" FROM Lotes_Fornecedores, Documentos_Lotes_Fornecedores, Tipos_Movimentos " +
			" WHERE Documentos_Lotes_Fornecedores.oid_tipo_Movimento = Tipos_Movimentos.oid_tipo_Movimento " +
			" AND   Documentos_Lotes_Fornecedores.oid_Lote_Fornecedor = Lotes_Fornecedores.oid_Lote_Fornecedor "+
			//" AND   Documentos_Lotes_Fornecedores.DM_Atualiza1 <> 'S' "+
			//" AND   Documentos_Lotes_Fornecedores.NR_Documento='28386' "+
			" AND   Lotes_Fornecedores.OID_Lote_Fornecedor = " + ed.getOID_Lote_Fornecedor();

			// System.out.println ("ATUALIZA APROV INCLUSAO= " + sql);

			ResultSet res = null;
			ResultSet resMov = null;
			res = this.executasql.executarConsulta(sql);
			qt_ctos=0;
			String acao="";
			while (res.next() && qt_ctos<qt_ctos_ler){
				qt_ctos++;
				// System.out.println ("ATUALIZA APROV INCLUSAO===>> " +qt_ctos+" cto =" + res.getString ("OID_Conhecimento"));
				// System.out.println ("vl_cobrado===>> " +res.getDouble ("vl_cobrado"));

				//this.inicioTransacao ();

				vl_Desconto=res.getDouble("vl_Desconto");
				vl_Rateio=res.getDouble("vl_Rateio");
				Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();
				movimento_ConhecimentoED.setOID_Fornecedor (res.getString ("oid_pessoa"));
				movimento_ConhecimentoED.setDT_Movimento_Conhecimento (Data.getDataDMY ());
				movimento_ConhecimentoED.setHR_Movimento_Conhecimento (Data.getHoraHM ());
				movimento_ConhecimentoED.setOID_Tipo_Movimento (res.getLong ("oid_Tipo_Movimento"));
				movimento_ConhecimentoED.setOID_Usuario (res.getLong ("oid_Usuario"));
				movimento_ConhecimentoED.setNR_Postagem (res.getLong ("nr_Postagem"));
				movimento_ConhecimentoED.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
				movimento_ConhecimentoED.setDM_Tipo_Movimento ("D");
				movimento_ConhecimentoED.setDM_Lancado_Gerado ("L");
				movimento_ConhecimentoED.setNM_Pessoa_Entrega ("");
				movimento_ConhecimentoED.setVL_Movimento (res.getDouble ("vl_cobrado"));
				movimento_ConhecimentoED.setVL_Realizado (res.getDouble ("vl_cobrado"));
				movimento_ConhecimentoED.setOID_Lote_Fornecedor (res.getLong ("OID_Lote_Fornecedor"));
				movimento_ConhecimentoED.setNR_Documento (res.getString ("nr_documento_cobranca"));
				movimento_ConhecimentoED.setDM_Origem_Lancamento("ATUALIZA_LOTE_FORNECEDOR");
				acao="INCLUI";
				
				sql = " SELECT OID_Movimento_Conhecimento, VL_Movimento FROM Movimentos_Conhecimentos " +
				" WHERE  Movimentos_Conhecimentos.OID_Conhecimento  = '" + movimento_ConhecimentoED.getOID_Conhecimento () + "'" +
				" AND    Movimentos_Conhecimentos.oid_fornecedor = '" + movimento_ConhecimentoED.getOID_Fornecedor () + "'";
				// System.out.println ("inclui_altera (1) " + sql);

				resMov = this.executasql.executarConsulta (sql);
				if (resMov.next ()) {
					movimento_ConhecimentoED.setOID_Movimento_Conhecimento(resMov.getString ("OID_Movimento_Conhecimento"));
					acao="ALTERA";
				}else {				
					sql = " SELECT OID_Movimento_Conhecimento, VL_Movimento FROM Movimentos_Conhecimentos " +
					" WHERE  Movimentos_Conhecimentos.OID_Conhecimento  = '" + movimento_ConhecimentoED.getOID_Conhecimento () + "'" +
					" AND    Movimentos_Conhecimentos.oid_Tipo_Movimento = '" + movimento_ConhecimentoED.getOID_Tipo_Movimento () + "'";
					// System.out.println ("inclui_altera () " + sql);
					resMov = this.executasql.executarConsulta (sql);
					if (resMov.next ()) {
						movimento_ConhecimentoED.setOID_Movimento_Conhecimento(resMov.getString ("OID_Movimento_Conhecimento"));
						acao="ALTERA";
					}
				}

				if ("INCLUI".equals(acao)){
					movimento_ConhecimentoED=inclui (movimento_ConhecimentoED);
				}else {
					altera(movimento_ConhecimentoED);
				}

				if (movimento_ConhecimentoED.getOID_Movimento_Conhecimento() !=null && movimento_ConhecimentoED.getOID_Movimento_Conhecimento().length()>4){
					sql = " update Documentos_Lotes_Fornecedores set DM_Atualiza1='S', oid_Movimento_Conhecimento = '"+movimento_ConhecimentoED.getOID_Movimento_Conhecimento()+"'";
					sql += " where oid_Documento_Lote_Fornecedor = " + res.getString ("oid_Documento_Lote_Fornecedor");
					// System.out.println ("altera " + sql);
					executasql.executarUpdate (sql);
				}

				//this.fimTransacao(true);

			}

			//RESSARCIMENTO
			// System.out.println ("vl_Desconto: " + vl_Desconto);
			// System.out.println ("edParametro_Fixo: " + edParametro_Fixo.getOID_Tipo_Movimento_Ressarcimento ());
			if (vl_Desconto > 0 && edParametro_Fixo.getOID_Tipo_Movimento_Ressarcimento ()>0) {

				sql = " SELECT Conhecimentos.NR_Conhecimento, Conhecimentos.NR_Peso, Conhecimentos.NR_Peso_Cubado " +
				" FROM  Documentos_Lotes_Fornecedores, Conhecimentos " +
				" WHERE Documentos_Lotes_Fornecedores.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
				//" AND   Conhecimentos.oid_Pessoa='88978606000147'" +
				" AND   Conhecimentos.DM_Tipo_Documento='C'" +
				" AND   Documentos_Lotes_Fornecedores.oid_Lote_Fornecedor ='" + ed.getOID_Lote_Fornecedor () + "'";
				// System.out.println ("sql: " + sql);

				res = this.executasql.executarConsulta (sql);
				qt_ctos = 0;
				while (res.next () && qt_ctos < qt_ctos_ler) {
					qt_ctos++;
					if (res.getDouble ("NR_Peso_Cubado") > res.getDouble ("NR_Peso"))
						NR_Peso_Total += res.getDouble ("NR_Peso_Cubado");
					else
						NR_Peso_Total += res.getDouble ("NR_Peso");

					// System.out.println ("1 CTO : " + res.getString ("NR_Conhecimento"));

				}

				// System.out.println ("vl_Desconto: " + vl_Desconto);
				// System.out.println ("NR_Peso_Total: " + NR_Peso_Total);

				if (NR_Peso_Total > 0 ) {
					sql = " SELECT Documentos_Lotes_Fornecedores.oid_Usuario, Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Peso, Conhecimentos.NR_Peso_Cubado " +
					" FROM  Documentos_Lotes_Fornecedores, Conhecimentos " +
					" WHERE Documentos_Lotes_Fornecedores.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
					" AND   Conhecimentos.DM_Tipo_Documento='C'" +
					//" AND   Conhecimentos.oid_Pessoa='88978606000147'" +
					" AND   Documentos_Lotes_Fornecedores.oid_Lote_Fornecedor ='" + ed.getOID_Lote_Fornecedor () + "'";

					// System.out.println ("sql: " + sql);


					res = this.executasql.executarConsulta (sql);
					qt_ctos = 0;
					while (res.next () && qt_ctos<qt_ctos_ler) {
						qt_ctos++;
						//this.inicioTransacao ();

						// System.out.println ("ATUALIZA APROV RESSARCIMENTO===>> " +qt_ctos+" cto =" + res.getString ("OID_Conhecimento"));

						// System.out.println ("1 CTO --: ");

						if (res.getDouble ("NR_Peso_Cubado") > res.getDouble ("NR_Peso"))
							vl_Movimento = (vl_Desconto / NR_Peso_Total * res.getDouble ("NR_Peso_Cubado"));
						else
							vl_Movimento = (vl_Desconto / NR_Peso_Total * res.getDouble ("NR_Peso"));

						if (vl_Movimento > vl_Desconto) vl_Movimento = vl_Desconto;

						// System.out.println ("2 CTO --: " + res.getString ("OID_Conhecimento") + " Peso : " + res.getString ("NR_Peso") + " Peso Cub : " + res.getString ("NR_Peso_Cubado") + " VL_Movimento:" + vl_Movimento);

						Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();
						edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Ressarcimento ()).longValue ());

						edMovimento_Conhecimento.setVL_Movimento (new Double (vl_Movimento).doubleValue ());
						edMovimento_Conhecimento.setDT_Movimento_Conhecimento (Data.getDataDMY ());
						edMovimento_Conhecimento.setHR_Movimento_Conhecimento (Data.getHoraHM ());

						edMovimento_Conhecimento.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
						edMovimento_Conhecimento.setOID_Usuario (res.getLong ("oid_Usuario"));

						// System.out.println ("4 CTO --: ");
						edMovimento_Conhecimento.setOID_Fornecedor (ed.getOID_Pessoa ());
						edMovimento_Conhecimento.setOID_Lote_Fornecedor (ed.getOID_Lote_Fornecedor ());
						edMovimento_Conhecimento.setDM_Tipo_Movimento ("R");
						edMovimento_Conhecimento.setDM_Lancado_Gerado ("R");
						edMovimento_Conhecimento.setNM_Pessoa_Entrega ("");
						edMovimento_Conhecimento.setDM_Origem_Lancamento("ATUALIZA_LOTE_FORNECEDOR");

						// System.out.println ("99 CTO : ");

						// System.out.println ("APROV NR Doc 5===>> " + res.getString ("OID_Conhecimento"));

						Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();


						movimento_ConhecimentoED=inclui (movimento_ConhecimentoED);

						if (movimento_ConhecimentoED.getOID_Movimento_Conhecimento() !=null && movimento_ConhecimentoED.getOID_Movimento_Conhecimento().length()>4){
							sql = " update Documentos_Lotes_Fornecedores set DM_Atualiza1='S', oid_Movimento_Conhecimento = '"+movimento_ConhecimentoED.getOID_Movimento_Conhecimento()+"'";
							sql += " where oid_Documento_Lote_Fornecedor = " + res.getString ("oid_Documento_Lote_Fornecedor");
							// System.out.println ("altera " + sql);
							executasql.executarUpdate (sql);
						}
						

						//this.fimTransacao (true);

						// System.out.println ("99 b CTO : ");
					}
				}


			}

			//AJUSTA CUSTO MINUTAS
			// System.out.println ("AJUSTA custos das MInutas p.CTO ");

			sql = " SELECT Conhecimentos.oid_Conhecimento " +
			" FROM  Documentos_Lotes_Fornecedores, Conhecimentos " +
			" WHERE Documentos_Lotes_Fornecedores.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
			" AND   Conhecimentos.oid_Lote_Faturamento>0 " +
			" AND   Conhecimentos.DM_Tipo_Documento='M' " +
			" AND   Documentos_Lotes_Fornecedores.oid_Lote_Fornecedor ='" + ed.getOID_Lote_Fornecedor () + "'";
			// System.out.println ("sql: " + sql);

			res = this.executasql.executarConsulta (sql);
			qt_ctos = 0;
			while (res.next () && qt_ctos < qt_ctos_ler) {
				qt_ctos++;

				// System.out.println ("ATUALIZA APROV CUSTO MINUTAS===>> " +qt_ctos+" cto =" + res.getString ("OID_Conhecimento"));

				//this.inicioTransacao ();
				verifica_Custo_Minuta (res.getString ("OID_Conhecimento"));
				//this.fimTransacao (true);

			}

		}
		catch (SQLException e) {
			e.printStackTrace ();
			throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera(Movimento_ConhecimentoED ed)");
		}
		catch (Exception e) {
			e.printStackTrace ();
			throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera(Movimento_ConhecimentoED ed)");
		}

		return ed;
	}

	public void recalcula_Margem_Lote_Fornecedor(long oid_Lote_Fornecedor)throws Excecoes{

		String sql = null;
		int qt_ctos=0, qt_ctos_ler=9999, tt=0;
		try{


			//CALCULA MARGEM

			sql = " SELECT Conhecimentos.oid_Conhecimento " +
			" FROM  Documentos_Lotes_Fornecedores, Conhecimentos " +
			" WHERE Documentos_Lotes_Fornecedores.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
			" AND   Documentos_Lotes_Fornecedores.oid_Lote_Fornecedor ='" + oid_Lote_Fornecedor + "'";
			// System.out.println ("sql: " + sql);

			ResultSet res = this.executasql.executarConsulta (sql);
			qt_ctos = 0;
			while (res.next () && qt_ctos < qt_ctos_ler) {
				qt_ctos++;
				tt++;
				// System.out.println ("RECALCULA MARGEM LOT FOR===>> " + qt_ctos + " cto =" + res.getString ("OID_Conhecimento"));
				Movimento_ConhecimentoED movED = new Movimento_ConhecimentoED ();
				movED.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
				this.calcula_Margem (movED);
				if (tt>100) {
				  // System.out.println ("ATUALIZA TRANSACAO FECHA  ===>> ") ;
					
				  tt=0;
			      this.fimTransacao(true);
			      this.inicioTransacao();
			      
				  // System.out.println ("ATUALIZA TRANSACAO ABRE  ===>> ") ;

				}  
				
			}


		}
		catch(Exception exc){
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao atualiza Lote_Fornecedor");
			excecoes.setMetodo("Seleção");
			excecoes.setExc(exc);
			throw excecoes;
		}
		return;
	}


	public Lote_FornecedorED reabre_Lote_Fornecedor(Lote_FornecedorED ed)throws Excecoes{

		String sql = null;
		try{

			sql =  " SELECT Movimentos_Conhecimentos.oid_Movimento_Conhecimento, Movimentos_Conhecimentos.oid_Conhecimento " +
			" FROM Movimentos_Conhecimentos " +
			" WHERE Movimentos_Conhecimentos.OID_Lote_Fornecedor = " + ed.getOID_Lote_Fornecedor();

			// System.out.println ("reabre_Lote_Fornecedor NR Doc 5===>> " + sql);

			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			while (res.next()){
				Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();
				movimento_ConhecimentoED.setOID_Movimento_Conhecimento(res.getString ("oid_Movimento_Conhecimento"));
				movimento_ConhecimentoED.setOID_Conhecimento(res.getString ("oid_Conhecimento"));

				// System.out.println ("APROV Doc ==>> " + res.getString ("OID_Conhecimento"));
				movimento_ConhecimentoED.setDM_Origem_Lancamento("ATUALIZA_LOTE_FORNECEDOR");

				deleta(movimento_ConhecimentoED);

				// System.out.println ("REABRE APROV NR Doc 6===>> " + res.getString ("OID_Conhecimento"));

			}

		}
		catch(Exception exc){
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao atualiza Lote_Fornecedor");
			excecoes.setMetodo("Seleção");
			excecoes.setExc(exc);
			throw excecoes;
		}

		return ed;
	}

	private double soma_Movimento (String oid_Conhecimento, String DM_Totaliza) throws Excecoes {

		String sql = null;
		double VL_Movimento=0;
		try {

			sql = " SELECT SUM (VL_Movimento) as VL_Movimento " +
			" FROM Tipos_Movimentos, Movimentos_Conhecimentos " +
			" WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento" +
			" AND   Movimentos_Conhecimentos.OID_Conhecimento =  '" + oid_Conhecimento + "'" +
			" AND Tipos_Movimentos.DM_Tipo_Movimento = 'D' ";
			if (!"".equals(DM_Totaliza)){
				sql+= " AND   Tipos_Movimentos.DM_Totaliza = '" + DM_Totaliza+ "'";
			}
			ResultSet res = this.executasql.executarConsulta (sql);
			if (res.next ()) {
				VL_Movimento=res.getDouble("VL_Movimento");
			}
		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao soma_Movimento");
			excecoes.setMetodo ("soma_Movimento");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return VL_Movimento;
	}


	private String custo_Liberado (String oid_Conhecimento) throws Excecoes {

		String sql = null;
		ResultSet res=null;
		String retorna="";
		// System.out.println("custo_Liberado1 ");

		
		try {
			if (oid_Conhecimento != null && oid_Conhecimento.length () > 4) {
				sql =" SELECT Unidades.DT_Lancamento_Custo, Conhecimentos.NR_Conhecimento " +
				     " FROM Conhecimentos, Unidades " +
				     " WHERE Conhecimentos.oid_Unidade = Unidades.oid_Unidade " +
				     " AND   Unidades.DT_Lancamento_Custo > Conhecimentos.DT_Emissao " +
				     " AND   Conhecimentos.oid_Conhecimento ='" + oid_Conhecimento +"'";
				res = this.executasql.executarConsulta (sql);
				if (res.next ()) {
					retorna="A DATA LIMITE PARA LANCAMENTO DE CUSTO PARA ESTA UNIDADE É DIA=>"+ res.getString("DT_Lancamento_Custo") + " CONHECIMENTO : " + res.getString("NR_Conhecimento");
				}
			}
		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("");
			excecoes.setMetodo ("custo_Liberado");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return retorna;
	}

	
}
