package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.Auxiliar1ED;
import com.master.ed.CompromissoED;
import com.master.ed.Lote_PagamentoED;
import com.master.ed.Manifesto_InternacionalED;
import com.master.ed.Movimento_CompromissoED;
import com.master.ed.Movimento_ConhecimentoED;
import com.master.ed.Movimento_Conta_CorrenteED;
import com.master.ed.Movimento_OrdemED;
import com.master.ed.Ocorrencia_ConhecimentoED;
import com.master.ed.Ordem_FreteED;
import com.master.ed.Tipo_EventoED;
import com.master.rl.Ordem_FreteRL;
import com.master.rn.Auxiliar1RN;
import com.master.rn.Movimento_ConhecimentoRN;
import com.master.rn.Ocorrencia_ConhecimentoRN;
import com.master.root.CidadeBean;
import com.master.root.FormataDataBean;
import com.master.root.FornecedorBean;
import com.master.root.MotoristaBean;
import com.master.root.PessoaBean;
import com.master.root.SeguradoraBean;
import com.master.root.VeiculoBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

public class Ordem_FreteBD extends Transacao  {

	private ExecutaSQL executasql;
	Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();
	FormataDataBean DataFormatada = new FormataDataBean ();

	Utilitaria util = new Utilitaria(executasql);

	String DM_Tipo_Ordem_Frete = "NORMAL";
	double VL_IRRF_Acumulado = 0;
	double VL_Ordem_Frete_Acumulado = 0;
	double VL_Base_IRRF = 0;
	double VL_IRRF_Calculado = 0;
	long NR_Proximo = 0;
	long NR_Final = 0;
	long NR_DEPENDENTES = 0;

	public Ordem_FreteBD (ExecutaSQL sql) {
		this.executasql = sql;
	}

	public void alteraEnvio_CFE(Ordem_FreteED ed) throws Excecoes {
		String sql = null;

		try {

			sql = " update Ordens_Fretes set " +
				  " CIOT= '" + ed.getCIOT() + "'" +
				  ", VIAGEMID= '" + ed.getVIAGEMID() + "'" +
				  ", dt_envio_cfe= '" + ed.getDt_envio_cfe() + "'" +
				  ", dt_encerramento_cfe= '" + ed.getDt_encerramento_cfe() + "'" +
				  ", dt_cancelamento_cfe= '" + ed.getDt_cancelamento_cfe() + "'";
			sql += " where oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

			executasql.executarUpdate (sql);

			if(JavaUtil.doValida(ed.getDt_cancelamento_cfe())){
				sql = "delete from ordens_manifestos where oid_ordem_frete = '" + ed.getOID_Ordem_Frete () + "'";
				executasql.executarUpdate (sql);
			}
			this.atualiza_Compromisso_Ordem_Frete_Tarifas(ed); // Atualiza o valor das tarifas nas ordens de fretes e gera os compromissos
		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao alterar Ordem de Frete");
			excecoes.setMetodo ("alterarEnvio_CFE");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	public Ordem_FreteED atualiza_Compromisso_Ordem_Frete_Tarifas (Ordem_FreteED ed) // Atualiza o valor das tarifas nas ordens de fretes e gera os compromissos
	throws Excecoes {

		String sql = null;
		ResultSet resCTRC = null;
		double vl_tarifa_bancaria = 0, vl_tarifa_bancaria_transferencia=0;

		try {

			sql = " SELECT vl_tarifa_Pamcary_Transferencia, vl_tarifa_pamcary from empresas ";
			resCTRC = null;
			resCTRC = this.executasql.executarConsulta (sql);
			if (resCTRC.next ()) {
				vl_tarifa_bancaria = resCTRC.getDouble("vl_tarifa_pamcary") ;
				vl_tarifa_bancaria_transferencia =  resCTRC.getDouble("vl_tarifa_Pamcary_Transferencia");
				if ("A".equals(ed.getDM_Frete())){ // Se ordem frete for adiantamento da frota, a tarifa fica a metade.
					vl_tarifa_bancaria = vl_tarifa_bancaria / 2;
					vl_tarifa_bancaria_transferencia = vl_tarifa_bancaria_transferencia / 2;
				}
				sql = " update Ordens_Fretes " +
				  "        set vl_tarifa_bancaria = " +  vl_tarifa_bancaria +
				  "          , vl_tarifa_bancaria_transferencia = " + vl_tarifa_bancaria_transferencia +
				  " where oid_ordem_frete = '" + ed.getOID_Ordem_Frete () + "'" ;
				executasql.executarUpdate (sql);

			}

			sql = " select compromissos.oid_compromisso " +
					" from compromissos, movimentos_ordens " +
					" where compromissos.oid_compromisso = movimentos_ordens.oid_compromisso " +
					" and movimentos_ordens.oid_ordem_frete = '" + ed.getOID_Ordem_Frete () + "'" +
					" and compromissos.vl_compromisso = " + ed.getVL_Adiantamento1() +
					" order by compromissos.nr_parcela asc limit 1";
			resCTRC = null;
			resCTRC = this.executasql.executarConsulta (sql);

			boolean dm_tem_adiantamento = false;
			while (resCTRC.next ()) {
				dm_tem_adiantamento = true;
				sql  =  "update compromissos " +
					   " set vl_compromisso = vl_compromisso + " + (vl_tarifa_bancaria + vl_tarifa_bancaria_transferencia);
				sql +=  "   ,vl_saldo = vl_saldo + " + (vl_tarifa_bancaria + vl_tarifa_bancaria_transferencia);
				sql +=  " where oid_compromisso = " + resCTRC.getString ("oid_compromisso");
				executasql.executarUpdate (sql);
			}

			if (!dm_tem_adiantamento){

				sql = " select compromissos.oid_compromisso " +
					  " from compromissos, movimentos_ordens " +
					  " where compromissos.oid_compromisso = movimentos_ordens.oid_compromisso " +
					  " and movimentos_ordens.oid_ordem_frete = '" + ed.getOID_Ordem_Frete () + "'" +
					  " and compromissos.vl_compromisso = " + ed.getVL_Adiantamento2() +
					  " order by compromissos.nr_parcela asc limit 1";
				resCTRC = null;
				resCTRC = this.executasql.executarConsulta (sql);

				while (resCTRC.next ()) {
					dm_tem_adiantamento = true;
					sql  =  "update compromissos " +
							" set vl_compromisso = vl_compromisso + " + (vl_tarifa_bancaria + vl_tarifa_bancaria_transferencia);
					sql +=  "   ,vl_saldo = vl_saldo + " + (vl_tarifa_bancaria + vl_tarifa_bancaria_transferencia);
					sql +=  " where oid_compromisso = " + resCTRC.getString ("oid_compromisso");
					executasql.executarUpdate (sql);
				}

			}
			if (!dm_tem_adiantamento){
				sql = " select compromissos.oid_compromisso " +
					  " from compromissos, movimentos_ordens " +
					  " where compromissos.oid_compromisso = movimentos_ordens.oid_compromisso " +
					  " and movimentos_ordens.oid_ordem_frete = '" + ed.getOID_Ordem_Frete () + "'" +
					  " and compromissos.vl_compromisso = " + ed.getVL_Saldo() +
					  " order by compromissos.nr_parcela desc limit 1";
				resCTRC = null;
				resCTRC = this.executasql.executarConsulta (sql);

				while (resCTRC.next ()) {
					sql  =  "update compromissos " +
							" set vl_compromisso = vl_compromisso + " + (vl_tarifa_bancaria + vl_tarifa_bancaria_transferencia);
					sql +=  "   ,vl_saldo = vl_saldo + " + (vl_tarifa_bancaria + vl_tarifa_bancaria_transferencia);
					sql +=  " where oid_compromisso = " + resCTRC.getString ("oid_compromisso");
					executasql.executarUpdate (sql);

			}
		  }
		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "atualiza_Compromisso_Ordem_Frete_Tarifas (Ordem_FreteED ed)");
		}
		return ed;
	}

	public Ordem_FreteED inclui(Ordem_FreteED ed)
	throws Excecoes {

		String sql = null;
		long valOid = 0;
		String chave = null;
		String incluida_cadastrada="cadastrada";
		String oid_Local_Pagamento="";
		Ordem_FreteED ordem_FreteED = new Ordem_FreteED ();
		ResultSet rs = null;

		try {
			ed.setNM_Serie ("DIG");

			sql = " SELECT oid_Pessoa ";
			sql += " FROM Unidades ";
			sql += " WHERE Unidades.oid_Unidade = " + ed.getOID_Unidade();
			rs = this.executasql.executarConsulta (sql);

			if(rs.next ()) {
				oid_Local_Pagamento=rs.getString("oid_Pessoa");
			}

			if (String.valueOf (ed.getNR_Ordem_Frete ()) != null &&
					String.valueOf (ed.getNR_Ordem_Frete ()).equals ("0")) {

				incluida_cadastrada="incluida";

				sql = " SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
				sql += " FROM AIDOF ";
				sql += " WHERE AIDOF.NM_Serie = 'OF1' ";
				rs = this.executasql.executarConsulta (sql);

				while (rs.next ()) {
					ed.setNM_Serie (rs.getString ("NM_Serie"));
					ed.setNR_Ordem_Frete (rs.getLong ("NR_Proximo"));
					valOid = rs.getLong ("OID_AIDOF");
					NR_Proximo = rs.getLong ("NR_Proximo") + 1;
					NR_Final = rs.getLong ("NR_FINAL");
				}

				if (NR_Proximo > NR_Final) {
					throw new Excecoes("AIDOF sem numera��o dispon�vel");
				}

				sql = " UPDATE AIDOF SET NR_Proximo= " + (ed.getNR_Ordem_Frete () + 1);
				sql += " WHERE OID_AIDOF = " + valOid;
				int u = executasql.executarUpdate (sql);
			}

			chave = String.valueOf (ed.getOID_Unidade ()) +  String.valueOf (ed.getNR_Ordem_Frete ()) + ed.getNM_Serie ();

			ed.setOID_Ordem_Frete (chave);

			ed.setDM_Tipo_Pessoa(this.consultaTipo_Pessoa(ed.getOID_Fornecedor(),
					ed.getOID_Motorista()));


			sql = " insert into Ordens_Fretes (" +
					"OID_Ordem_Frete, " +
					"OID_Pessoa, " +
					"OID_Fornecedor, " +
					"OID_Local_Pagamento, " +
					"DM_Impresso, " +
					"DM_Tipo_Pedagio, " +
					"DM_Tipo_Pessoa, " +
					"NR_Transacao_Pedagio, " +
					"NM_Pagamento, " +
					"DM_Tipo_Pagamento, " +
					"DM_Tipo_Pagamento_Adiantamento1, " +
					"DM_Tipo_Pagamento_Adiantamento2, " +
					"DM_Tipo_Pagamento_Saldo, " +
					"DM_Adiantamento, " +
					"DT_Emissao, " +
					"OID_Unidade, " +
					"OID_Unidade_Adiantamento1, " +
					"OID_Unidade_Adiantamento2, " +
					"OID_Unidade_Saldo, " +
					"NR_Ordem_Frete, " +
					"VL_IRRF, " +
					"VL_Pedagio, " +
					"VL_Liquido_Ordem_Frete, " +
					"VL_Taxa_Expediente, " +
					"NR_QUANTIDADE_COLETA, " +
					"NR_QUANTIDADE_ENTREGA, " +
					"VL_Descontos, " +
					"VL_Multa_Atrazo, " +
					"TX_Observacao, " +
					"DM_Frete, " +
					"VL_Set_Senat, " +
					"VL_Ordem_Frete, " +
					"VL_INSS_Pago, " +
					"VL_Vale_Pedagio, " +
					"VL_Vale_Pedagio_Empresa, " +
					"VL_Outros, " +
					"VL_Premio, " +
					"VL_Coleta, " +
					"VL_Carga, " +
					"VL_Descarga, " +
					"VL_INSS_Prestador, " +
					"VL_INSS_Empresa, " +
					"VL_Total_Frete_CTRC, " +
					"oid_Usuario, " +
					"oid_Veiculo, ";

			if (String.valueOf (ed.getDT_Adiantamento1 ()) != null &&
					!String.valueOf (ed.getDT_Adiantamento1 ()).equals ("") &&
					!String.valueOf (ed.getDT_Adiantamento1 ()).equals ("null")) {
				sql += "DT_Adiantamento1, ";
			}

			if (String.valueOf (ed.getDT_Adiantamento2 ()) != null &&
					!String.valueOf (ed.getDT_Adiantamento2 ()).equals ("") &&
					!String.valueOf (ed.getDT_Adiantamento2 ()).equals ("null")) {
				sql += "DT_Adiantamento2, ";
			}

			if (String.valueOf (ed.getDT_Saldo ()) != null &&
					!String.valueOf (ed.getDT_Saldo ()).equals ("") &&
					!String.valueOf (ed.getDT_Saldo ()).equals ("null")) {
				sql += "DT_Saldo, ";
			}

			sql += "VL_Adiantamento1, " +
			"VL_Adiantamento2, " +
			"VL_Saldo, DM_Serie, oid_Motorista, dm_saldo_liberado, oid_programacao_carga ) values (";

			sql += ed.getOID_Ordem_Frete () == null ? "null," :
				"'" + ed.getOID_Ordem_Frete () + "',";
			sql += ed.getOID_Pessoa () == null ? "null," :
				"'" + ed.getOID_Pessoa () + "',";
			sql += ed.getOID_Fornecedor () == null ? "null," :
				"'" + ed.getOID_Fornecedor () + "',";
			sql += "'" + oid_Local_Pagamento + "',";
			sql += ed.getDM_Impresso () == null ? "null," :
				"'" + ed.getDM_Impresso () + "',";
			sql += ed.getDM_Tipo_Pedagio () == null ? "null," :
				"'" + ed.getDM_Tipo_Pedagio () + "',";
			sql += ed.getDM_Tipo_Pessoa () == null ? "null," :
				"'" + ed.getDM_Tipo_Pessoa () + "',";
			sql += ed.getNR_Transacao_Pedagio () == null ? "null," :
				"'" + ed.getNR_Transacao_Pedagio () + "',";
			sql += ed.getNM_Pagamento () == null ? "null," :
				"'" + ed.getNM_Pagamento () + "',";
			sql += ed.getDM_Tipo_Pagamento () == null ? "null," :
				"'" + ed.getDM_Tipo_Pagamento () + "',";
			sql += ed.getDM_Tipo_Pagamento_Adiantamento1 () == null ? "null," :
				"'" + ed.getDM_Tipo_Pagamento_Adiantamento1 () + "',";
			sql += ed.getDM_Tipo_Pagamento_Adiantamento2 () == null ? "null," :
				"'" + ed.getDM_Tipo_Pagamento_Adiantamento2 () + "',";
			sql += ed.getDM_Tipo_Pagamento_Saldo () == null ? "null," :
				"'" + ed.getDM_Tipo_Pagamento_Saldo () + "',";

			sql += ed.getDM_Adiantamento () == null ? "null," :
				"'" + ed.getDM_Adiantamento () + "',";
			sql += ed.getDT_Emissao () == null ? "null," :
				"'" + ed.getDT_Emissao () + "',";
			sql += ed.getOID_Unidade () + ",";
			sql += ed.getOID_Unidade_Adiantamento1 () + ",";
			sql += ed.getOID_Unidade_Adiantamento2 () + ",";
			sql += ed.getOID_Unidade_Saldo () + ",";

			sql += ed.getNR_Ordem_Frete () + ",";
			sql += ed.getVL_IRRF () + ",";
			sql += ed.getVL_Pedagio () + ",";
			sql += ed.getVL_Liquido_Ordem_Frete () + ",";
			sql += ed.getVL_Taxa_Expediente () + ",";
			sql += ed.getNR_Qtde_Coleta () + ",";
			sql += ed.getNR_Qtde_Entrega () + ",";
			sql += ed.getVL_Descontos () + ",";
			sql += ed.getVL_Multa_Atrazo () + ",";
			sql += ed.getTX_Observacao () == null ? "null," :
				"'" + ed.getTX_Observacao () + "',";
			sql += ed.getDM_Frete () == null ? "null," :
				"'" + ed.getDM_Frete () + "',";
			sql += ed.getVL_Set_Senat () + ",";
			sql += ed.getVL_Ordem_Frete () + ",";
			sql += ed.getVL_INSS_Pago () + ",";
			sql += ed.getVL_Vale_Pedagio () + ",";
			sql += ed.getVL_Vale_Pedagio_Empresa () + ",";
			sql += ed.getVL_Outros () + ",";
			sql += ed.getVL_Premio () + ",";
			sql += ed.getVL_Coleta () + ",";
			sql += ed.getVL_Carga () + ",";
			sql += ed.getVL_Descarga () + ",";
			sql += ed.getVL_INSS_Prestador () + ",";
			sql += ed.getVL_INSS_Empresa () + ",";
			sql += ed.getVL_Total_Frete_CTRC () + ",";
			sql += ed.getOID_Usuario () + ",";
			sql += ed.getOID_Veiculo () == null ? "null," :
				"'" + ed.getOID_Veiculo () + "',";

			if (String.valueOf (ed.getDT_Adiantamento1 ()) != null &&
					!String.valueOf (ed.getDT_Adiantamento1 ()).equals ("") &&
					!String.valueOf (ed.getDT_Adiantamento1 ()).equals ("null")) {
				sql += ed.getDT_Adiantamento1 () == null ? "null," :
					"'" + ed.getDT_Adiantamento1 () + "',";
			}

			if (String.valueOf (ed.getDT_Adiantamento2 ()) != null &&
					!String.valueOf (ed.getDT_Adiantamento2 ()).equals ("") &&
					!String.valueOf (ed.getDT_Adiantamento2 ()).equals ("null")) {
				sql += ed.getDT_Adiantamento2 () == null ? "null," :
					"'" + ed.getDT_Adiantamento2 () + "',";
			}

			if (String.valueOf (ed.getDT_Saldo ()) != null &&
					!String.valueOf (ed.getDT_Saldo ()).equals ("") &&
					!String.valueOf (ed.getDT_Saldo ()).equals ("null")) {
				sql += ed.getDT_Saldo () == null ? "null," :
					"'" + ed.getDT_Saldo () + "',";
			}
			sql += ed.getVL_Adiantamento1 () + ",";
			sql += ed.getVL_Adiantamento2 () + ",";
			sql += ed.getVL_Saldo () + ",";
			sql += "'" + ed.getNM_Serie () + "',";
//			sql += "'" + ed.getOID_Motorista () + "', 'N')";
			sql += "'" + ed.getOID_Motorista () + "', 'N' ";
			sql += ", " + ed.getOid_Programacao_Carga() + ") ";

			// .print(sql);

			executasql.executarUpdate (sql);

			ordem_FreteED.setOID_Ordem_Frete (ed.getOID_Ordem_Frete ());

			if (this.verifica_Valores_Ordem_Frete(ed)){
				Ordem_FreteED ordem_Frete_ChaveED = new Ordem_FreteED ();

				ordem_Frete_ChaveED = this.busca_Chaves();

				ed.setNR_Posicao(ordem_Frete_ChaveED.getNR_Posicao());
				ed.setNR_Chave(ordem_Frete_ChaveED.getNR_Chave());

				sql = " update Ordens_fretes " +
						" set nr_posicao = " + ed.getNR_Posicao() +
						"    ,nr_chave = " + ed.getNR_Chave() +
						" where oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete() + "'";

				// System.out.println(sql);

				executasql.executarUpdate (sql);
			}

			if (ed.getOID_Manifesto () != null && !ed.getOID_Manifesto ().equals ("") &&
					!ed.getOID_Manifesto ().equals ("null")) {
				chave = (ed.getOID_Manifesto () + ed.getOID_Ordem_Frete ());

				sql = " insert into Ordens_Manifestos (OID_Ordem_Manifesto, OID_Manifesto, OID_Ordem_Frete ) values ";
				sql += "('" + chave + "','" + ed.getOID_Manifesto () + "','" +
				ed.getOID_Ordem_Frete () + "')";

				executasql.executarUpdate (sql);

				sql = " update Manifestos set DM_Lancado_Ordem_Frete= 'S'" +
				" where oid_Manifesto = '" + ed.getOID_Manifesto () + "'";
				executasql.executarUpdate (sql);
			}

			if (ed.getOID_MIC () != null && !ed.getOID_MIC ().equals ("") &&
					!ed.getOID_MIC ().equals ("null")) {
				chave = (ed.getOID_MIC () + ed.getOID_Ordem_Frete ());

				sql = " insert into Ordens_MIC (OID_Ordem_MIC, OID_Manifesto_Internacional, OID_Ordem_Frete, DT_Ordem_MIC, HR_Ordem_MIC ) values ";
				sql += "('" + chave + "','" + ed.getOID_MIC() + "','" + ed.getOID_Ordem_Frete() + "','"  + Data.getDataDMY() + "','" + Data.getHoraHM() + "')";

				executasql.executarUpdate (sql);
			}

			if ("incluida".equals(incluida_cadastrada)){
				this.vincula_Adto_Ordem_Principal(ed);
			}

			if (ed.getVL_Ordem_Frete()==989898){// PARA AJUSTAR OF ANTIGAS
				this.ajustaTipo_Pessoa(ed);
			}


		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui(Ordem_FreteED ed)");
		}
		return ordem_FreteED;

	}

	public boolean verifica_Valores_Ordem_Frete (Ordem_FreteED ed)
	throws Excecoes {
		boolean toReturn=false;
		ResultSet res = null;
		try {
			double vl_margem = Valor.round((ed.getVL_Total_Frete_CTRC() * 0.87) ,2);

			// System.out.println("vl_margem1 == " + vl_margem);
			// System.out.println("ed.getVL_Total_Frete_CTRC() = "+ed.getVL_Total_Frete_CTRC());
			// System.out.println("ed.getVL_Ordem_Frete() = "+ed.getVL_Ordem_Frete());

			if (vl_margem < ed.getVL_Ordem_Frete() && ed.getVL_Total_Frete_CTRC() > 1){
				toReturn = true;
				// System.out.println(" ENTREI 1");
			}

			vl_margem = Valor.round((ed.getVL_Ordem_Frete() * 0.5) ,2);

			ed.setVL_Total ( (ed.getVL_Adiantamento1 () -
							  ed.getVL_Coleta () -
							  ed.getVL_Carga () -
							  ed.getVL_Descarga () -
							  ed.getVL_Pedagio () -
							  ed.getVL_Outros () -
							  ed.getVL_Premio () +
							  ed.getVL_Descontos ()));


			if (vl_margem < ed.getVL_Total()){
				toReturn = true;
				// System.out.println(" ENTREI 2");
			}

			// System.out.println("vl_margem2 == " + vl_margem);
			// System.out.println("ed.getVL_Adiantamento1() = "+ed.getVL_Adiantamento1());
			// System.out.println("ed.getVL_Ordem_Frete() = "+ed.getVL_Ordem_Frete());

		} finally {
			util.closeResultset(res);
		}
		return toReturn;
	}

	public Ordem_FreteED busca_Chaves ()
	throws Excecoes {
		Ordem_FreteED ordem_FreteED = new Ordem_FreteED ();
		ResultSet rs = null;
		try {

			String sql = " SELECT * ";
				   sql += " FROM chaves_ordens_frete ";
			       sql += " WHERE chaves_ordens_frete.dm_utilizado is null order by nr_chave limit 1 ";

			rs = this.executasql.executarConsulta (sql);

			boolean dm_tem_chave = false;
			while (rs.next ()) {
				dm_tem_chave = true;
				ordem_FreteED.setNR_Posicao(rs.getLong ("nr_posicao"));
				ordem_FreteED.setNR_Chave(rs.getLong ("nr_chave"));
			}

			if (!dm_tem_chave) {
				sql = " update chaves_ordens_frete set dm_utilizado = null " ;
				executasql.executarUpdate (sql);

				sql = " SELECT * ";
				sql += " FROM chaves_ordens_frete ";
			    sql += " WHERE chaves_ordens_frete.dm_utilizado is null order by nr_chave limit 1 ";

			    rs = this.executasql.executarConsulta (sql);

			    while (rs.next ()) {
			    	ordem_FreteED.setNR_Posicao(rs.getLong ("nr_posicao"));
			    	ordem_FreteED.setNR_Chave(rs.getLong ("nr_chave"));
			    }
			}

			sql = " update chaves_ordens_frete set dm_utilizado = 'S' where nr_posicao = " + ordem_FreteED.getNR_Posicao();

			executasql.executarUpdate (sql);
		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "busca_Chaves()");
		} finally {
			util.closeResultset(rs);
		}
		return ordem_FreteED;
	}

	public Ordem_FreteED consulta_Saldo_Adiantamento(Ordem_FreteED ed)
	throws Excecoes {

		String sql = null;
		ResultSet res = null;
		Ordem_FreteED edVolta = new Ordem_FreteED();

		try {


			sql =  " SELECT Ordens_Fretes.OID_Ordem_Frete, "+
			"        Ordens_Fretes.VL_Ordem_Frete, "+
			"        Ordens_Fretes.vl_adiantamento1, "+
			"        Ordens_Fretes.vl_adiantamento2, "+
			"        Ordens_Fretes.vl_saldo  "+
			" FROM   Ordens_Fretes " +
			" WHERE  Ordens_Fretes.DM_Tipo_Adiantamento='A' "+
			" AND    Ordens_Fretes.oid_Ordem_Principal = '' " +
			" AND    Ordens_Fretes.DM_Impresso = 'S' " +
			" AND    Ordens_Fretes.DM_Frete = 'A' " +
			" AND    Ordens_Fretes.VL_Ordem_Frete >0 " +
			" AND    Ordens_Fretes.oid_Pessoa ='" + ed.getOID_Pessoa() + "'";

			if ("MANIFESTO".equals (parametro_FixoED.getDM_Vincula_Adto_Ordem_Principal ())) {
				sql = " SELECT Ordens_Fretes.OID_Ordem_Frete, " +
				"        Ordens_Fretes.VL_Ordem_Frete, " +
				"        Ordens_Fretes.vl_adiantamento1, " +
				"        Ordens_Fretes.vl_adiantamento2, " +
				"        Ordens_Fretes.vl_saldo  " +
				" FROM   Ordens_Fretes, Ordens_Manifestos " +
				" WHERE  Ordens_Fretes.DM_Tipo_Adiantamento='A' " +
				" AND    Ordens_Fretes.oid_Ordem_Principal = '' " +
				" AND    Ordens_Fretes.DM_Impresso = 'S' " +
				" AND    Ordens_Fretes.DM_Frete = 'A' " +
				" AND    Ordens_Fretes.VL_Ordem_Frete >0 " +
				" AND    Ordens_Fretes.oid_Ordem_Frete = Ordens_Manifestos.oid_Ordem_Frete " +
				" AND    Ordens_Manifestos.oid_Manifesto = '" + ed.getOID_Manifesto() + "'";
			}

			res = null;
			res = this.executasql.executarConsulta (sql);
			while (res.next ()) {
				edVolta.setVL_Adiantamento2(edVolta.getVL_Adiantamento2()+res.getDouble("vl_adiantamento1")+res.getDouble("vl_adiantamento2"));
			}

		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "consulta_Saldo_Adiantamento(Ordem_FreteED ed)");
		}
		return edVolta;

	}

	private Ordem_FreteED vincula_Adto_Ordem_Principal(Ordem_FreteED ed)
	throws Excecoes {

		String sql = null;
		ResultSet res = null;
		Ordem_FreteED ordem_ED = new Ordem_FreteED ();

		try {


			ordem_ED = this.consulta_Saldo_Adiantamento (ed);
			if (ordem_ED.getVL_Adiantamento2 () == ed.getVL_Adiantamento2 ()) {

				sql = " SELECT Ordens_Fretes.OID_Ordem_Frete " +
				" FROM   Ordens_Fretes " +
				" WHERE  Ordens_Fretes.DM_Tipo_Adiantamento='A' " +
				" AND    Ordens_Fretes.oid_Ordem_Principal = '' " +
				" AND    Ordens_Fretes.DM_Impresso = 'S' " +
				" AND    Ordens_Fretes.DM_Frete = 'A' " +
				" AND    Ordens_Fretes.VL_Ordem_Frete >0 " +
				" AND    Ordens_Fretes.oid_Pessoa ='" + ed.getOID_Pessoa () + "'";

				if ("MANIFESTO".equals (parametro_FixoED.getDM_Vincula_Adto_Ordem_Principal ())) {
					sql = " SELECT Ordens_Fretes.OID_Ordem_Frete, " +
					"        Ordens_Fretes.VL_Ordem_Frete, " +
					"        Ordens_Fretes.vl_adiantamento1, " +
					"        Ordens_Fretes.vl_adiantamento2, " +
					"        Ordens_Fretes.vl_saldo  " +
					" FROM   Ordens_Fretes, Ordens_Manifestos " +
					" WHERE  Ordens_Fretes.DM_Tipo_Adiantamento='A' " +
					" AND    Ordens_Fretes.oid_Ordem_Principal = '' " +
					" AND    Ordens_Fretes.DM_Impresso = 'S' " +
					" AND    Ordens_Fretes.DM_Frete = 'A' " +
					" AND    Ordens_Fretes.VL_Ordem_Frete >0 " +
					" AND    Ordens_Fretes.oid_Ordem_Frete = Ordens_Manifestos.oid_Ordem_Frete " +
					" AND    Ordens_Manifestos.oid_Manifesto = '" + ed.getOID_Manifesto() + "'";
				}

				res = this.executasql.executarConsulta (sql);
				while (res.next ()) {
					sql = " update Ordens_Fretes set oid_Ordem_Principal= '" + ed.getOID_Ordem_Frete () + "'" +
					" where OID_Ordem_Frete = '" + res.getString ("OID_Ordem_Frete") + "'";
					executasql.executarUpdate (sql);
				}
			}

		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "consulta_Saldo_Adiantamento(Ordem_FreteED ed)");
		}
		return ed;

	}

	public long inclui_Compromisso (String oid_Ordem_Frete, String dm_Tipo_Ordem_Frete)
	throws Excecoes {
		long NR_Compromisso=0;
		DM_Tipo_Ordem_Frete=dm_Tipo_Ordem_Frete;
		Ordem_FreteED ed = new Ordem_FreteED ();
		ed.setOID_Ordem_Frete(oid_Ordem_Frete);

		ed = this.getByRecord(ed);

		double VL_Ordem_Frete = ed.getVL_Ordem_Frete ();
		if (ed.getVL_Cotacao() > 0 &&
				ed.getVL_Cotacao_Padrao() > 0 &&
				ed.getVL_Cotacao() != ed.getVL_Cotacao_Padrao()) {
			VL_Ordem_Frete = Valor.round (ed.getVL_Ordem_Frete () / ed.getVL_Cotacao() * ed.getVL_Cotacao_Padrao() , 2);
		}

		if (VL_Ordem_Frete > 0 && "ADIANTAMENTO".equals(dm_Tipo_Ordem_Frete)) {
			ed.setDT_Vencimento (ed.getDT_Adiantamento1 ());
			ed.setNR_Parcela (new Integer ("1"));
			ed.setVL_Compromisso (VL_Ordem_Frete); //leva (se for moeda dif.) valor convertido
			NR_Compromisso = this.geraCompromisso (ed);
		}


		return NR_Compromisso;
	}


	public int geraCompromisso_VinculaLotePagamento (Ordem_FreteED ed, String NR_Cheque)
	throws Excecoes {

		String sql = null;
		int NR_Compromisso = 0;
		int oid_Compromisso=0;

		try {

			NR_Compromisso = this.geraCompromisso(ed);

			sql =" SELECT oid_Compromisso FROM Compromissos WHERE NR_Compromisso=" + NR_Compromisso;
			ResultSet resLocal = this.executasql.executarConsulta (sql);
			if (resLocal.next ()) {
				oid_Compromisso=resLocal.getInt("oid_Compromisso");
			}

			if (oid_Compromisso>0 && NR_Cheque!=null && NR_Cheque.length()>0 && !NR_Cheque.equals("null")) {

		        Lote_PagamentoED edLote = new Lote_PagamentoED();
		        edLote.setOID_Fornecedor(ed.getOID_Pessoa());
		        edLote.setDt_Emissao(Data.getDataDMY());
		        edLote.setDT_Programada(ed.getDT_Vencimento());
		        edLote.setDT_Compensacao("");
		        edLote.setNr_Documento(NR_Cheque);
		        edLote.setTx_Observacao("Adto Ordem Frete : " + ed.getNR_Ordem_Frete());
		        edLote.setDM_Copia_Cheque("N");
		        edLote.setDM_Imprimir("S");
		        edLote.setNM_Favorecido("");
		        edLote.setOid_Unidade(new Long(ed.getOID_Unidade()));
		        edLote.setVl_Lote_Pagamento(new Double(ed.getVL_Compromisso()));
		        edLote.setOID_Compromisso(oid_Compromisso);

		        edLote.setOID_Tipo_Documento(new Integer(74));  //definir param fixo se outra empresa
		        //edLote.setOID_Tipo_Documento(new Integer(11));  //definir param fixo se outra empresa
		        edLote.setOID_Conta_Corrente("54372");
		        //edLote.setOID_Conta_Corrente("901");

		        Lote_PagamentoBD loteBD = new Lote_PagamentoBD (this.executasql);
		        edLote = loteBD.inclui(edLote);
				if (edLote.getOid_Lote_Pagamento().intValue()>0){
					sql = " UPDATE Lotes_Pagamentos SET DM_Situacao='L' WHERE oid_Lote_Pagamento="+edLote.getOid_Lote_Pagamento().intValue();
					//.println(sql);
					executasql.executarUpdate (sql);

					long oid_Lote_Compromisso = (new Long(String.valueOf(System.currentTimeMillis()).toString()).longValue());

		            sql = " INSERT INTO Lotes_Compromissos (" +
	                  "      Oid_Lote_Compromisso" +
	                  "     ,DT_PAGAMENTO" +
	                  "     ,VL_PAGAMENTO" +
	                  "     ,NR_LOTE_PAGAMENTO" +
	                  "     ,OID_COMPROMISSO" +
	                  "     ,OID_Lote_Pagamento" +
	                  "     ,DM_Situacao)" +
	                  " VALUES('" +
	                  oid_Lote_Compromisso + "'," +
	                  "'" + Data.getDataDMY() + "'," +
	                  ed.getVL_Compromisso()+ "," +
	                  edLote.getOid_Lote_Pagamento().intValue() + "," +
	                  oid_Compromisso + "," +
	                  edLote.getOid_Lote_Pagamento().intValue() + "," +
	                  "'" + "A" + "')";
						//.println(sql);

		            executasql.executarUpdate(sql);

		            sql =" UPDATE Compromissos SET VL_Saldo=0, Tx_Observacao='Ordem Frete: "+ ed.getNR_Ordem_Frete() +"' WHERE oid_Compromisso="+oid_Compromisso;
					//.println(sql);
		            executasql.executarUpdate(sql);

				    this.inicioTransacao ();
				    //this.executasql = this.sql;

		            Movimento_CompromissoBD movimento_CompromissoBD = new Movimento_CompromissoBD(executasql);
                    Movimento_CompromissoED edMovCompromisso = new Movimento_CompromissoED();
                    edMovCompromisso.setOid_Lote_Pagamento(edLote.getOid_Lote_Pagamento().intValue());
                    edMovCompromisso.setOid_Compromisso(new Integer (oid_Compromisso));
                    edMovCompromisso.setDt_Pagamento(Data.getDataDMY());
                    edMovCompromisso.setVl_Pagamento(new Double(ed.getVL_Compromisso()));
                    edMovCompromisso.setTx_Observacao("Liquicao Impressao Ordem Pagamento");
                    edMovCompromisso.setVl_Saldo(new Double(0));
                    movimento_CompromissoBD.inclui(edMovCompromisso);
                    this.fimTransacao(true);
					//.println("FIM");
				}
			}

		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "geraCompromisso_VinculaLotePagamento ");
		}

		return NR_Compromisso;
	}

	public int geraCompromisso (Ordem_FreteED ed)
	throws Excecoes {

		String sql = null;
		String chave = null;
		int oid_Natureza_Operacao=0;
		int oid_Tipo_Documento=0;
		ResultSet res = null;
		ResultSet resLocal = null;
		int NR_Compromisso = 0;
		String tipo_pessoa="PF";

		Movimento_OrdemED Movimento_OrdemED = new Movimento_OrdemED ();

		try {
			CompromissoED compromissoED = new CompromissoED ();
			CompromissoED compromissoVoltaED = new CompromissoED ();
			CompromissoBD compromissoBD = new CompromissoBD (this.executasql);

			if (compromissoED.getOid_Pessoa () != null && compromissoED.getOid_Pessoa ().length () > 11) {
				tipo_pessoa="PJ";
			}

			compromissoED.setOID_Usuario(ed.getOID_Usuario());
			compromissoED.setDt_Vencimento (ed.getDT_Vencimento ());
			compromissoED.setDt_Entrada (Data.getDataDMY ());
			compromissoED.setNr_Parcela (ed.getNR_Parcela ());
			compromissoED.setVl_Compromisso (new Double (ed.getVL_Compromisso ()));
			compromissoED.setVl_Saldo (new Double (ed.getVL_Compromisso ()));
			compromissoED.setDt_Emissao (Data.getDataDMY ());
			compromissoED.setNr_Documento(String.valueOf(ed.getNR_Ordem_Frete()));
			compromissoED.setOid_Unidade (new Long (ed.getOID_Unidade ()));

			compromissoED.setOid_Unidade_Pagamento(ed.getOID_Unidade_Pagamento());
			compromissoED.setDM_Tipo_Pagamento(ed.getDM_Tipo_Pagamento());

			compromissoED.setVl_Multa_Apos_Vencimento (new Double (0.0));
			compromissoED.setVl_Juro_Mora_dia (new Double (0.0));
			compromissoED.setVl_Taxa_Banco (new Double (0.0));
			compromissoED.setVl_Desconto_Ate_Vencimento (new Double (0.0));
			compromissoED.setDM_Tipo_Pagamento("2"); //CHEQUE

			compromissoED.setOid_Centro_Custo (new Integer (parametro_FixoED.getOID_Centro_Custo_Ordem_Frete ()));

			compromissoED.setOid_Pessoa (ed.getOID_Pessoa ());

			if ("PJ".equals(tipo_pessoa)) {
				compromissoED.setOid_Conta (new Integer (parametro_FixoED.getOID_Conta_Juridica_Ordem_Frete ()));
			}
			else {
				compromissoED.setOid_Conta (new Integer (parametro_FixoED.getOID_Conta_Ordem_Frete ()));
			}
			sql = " select NM_Razao_Social from Pessoas where oid_Pessoa = '" + ed.getOID_Pessoa() + "'";
			resLocal = this.executasql.executarConsulta (sql);
			String NM_Razao_Social = "";
			while (resLocal.next ()) {
				NM_Razao_Social = resLocal.getString ("NM_Razao_Social");
			}

			if(JavaUtil.doValida(ed.getTX_Observacao())){
				compromissoED.setTx_Observacao(NM_Razao_Social + " OF Nr. " + String.valueOf(ed.getNR_Ordem_Frete()) + " - " + ed.getTX_Observacao());
			}else{
				compromissoED.setTx_Observacao(NM_Razao_Social + " OF Nr. " + String.valueOf(ed.getNR_Ordem_Frete()));
			}

			if ("ADIANTAMENTO".equals (DM_Tipo_Ordem_Frete)) {

				if (ed.getOID_Motorista () != null &&
						!ed.getOID_Motorista ().equals ("null")
						&& !ed.getOID_Motorista ().equals ("")
						&& !"A".equals (ed.getDM_Tipo_Pagamento ())) {
					if ("N".equals (parametro_FixoED.getDM_Gera_Compromisso_Motorista_Proprietario ())) {
						compromissoED.setOid_Pessoa (ed.getOID_Motorista ()); /// PEGA O MOTORISTA
					}
					else {
						sql = " select oid_pessoa_proprietario from veiculos where oid_veiculo = '" + ed.getOID_Veiculo () + "'";
						resLocal = this.executasql.executarConsulta (sql);
						while (resLocal.next ()) {
							compromissoED.setOid_Pessoa (resLocal.getString ("oid_pessoa_proprietario")); // PEGA O PROPRIETARIO DO VEICULO
						}
					}
				}

				if ("A".equals(ed.getDM_Frete())){
					compromissoED.setOid_Pessoa (ed.getOID_Motorista ()); /// PEGA O MOTORISTA
				}

				if (compromissoED.getOid_Pessoa () != null && compromissoED.getOid_Pessoa ().length () > 11) {
					tipo_pessoa="PJ";
				}else{
					tipo_pessoa="PF";
				}

				if ("PJ".equals(tipo_pessoa) && parametro_FixoED.getOID_Conta_Juridica_Ordem_Frete_Adiantamento ()>0) {
					compromissoED.setOid_Conta (new Integer (parametro_FixoED.getOID_Conta_Juridica_Ordem_Frete_Adiantamento ()));
				}
				else {
					compromissoED.setOid_Conta (new Integer (parametro_FixoED.getOID_Conta_Ordem_Frete_Adiantamento ()));
				}
				sql = " select NM_Razao_Social from Pessoas where oid_Pessoa = '" + compromissoED.getOid_Pessoa() + "'";
				resLocal = this.executasql.executarConsulta (sql);
				while (resLocal.next ()) {
					NM_Razao_Social = resLocal.getString ("NM_Razao_Social");
				}

				compromissoED.setTx_Observacao(NM_Razao_Social + " Adto. Frota Nr. " + String.valueOf(ed.getNR_Ordem_Frete()));

			}

			if (ed.getDM_Tipo_Pedagio () != null &&
					ed.getDM_Tipo_Pedagio ().equals ("V") && ed.getVL_Pedagio () > 0 &&
					ed.getNR_Parcela ().equals ("2")) {
				compromissoED.setOid_Conta (new Integer (parametro_FixoED.getOID_Conta_Debito_Pedagio ()));
			}
			if ("INSS".equals(DM_Tipo_Ordem_Frete)) {
				compromissoED.setOid_Conta (new Integer (parametro_FixoED.getOID_Conta_INSS_Ordem_Frete()));
			} else if ("IRRF".equals(DM_Tipo_Ordem_Frete)) {
				compromissoED.setOid_Conta (new Integer (parametro_FixoED.getOID_Conta_IR_Ordem_Frete()));
			}

			sql = " SELECT oid_Natureza_Operacao, Oid_Tipo_Documento from Contas ";
			sql += " WHERE Contas.OID_Conta = '" + compromissoED.getOid_Conta() + "'";

			res = this.executasql.executarConsulta (sql);
			while (res.next ()) {
				oid_Natureza_Operacao = res.getInt ("oid_Natureza_Operacao");
				oid_Tipo_Documento = res.getInt ("Oid_Tipo_Documento");
			}
			if (oid_Tipo_Documento==0) oid_Tipo_Documento=parametro_FixoED.getOID_Tipo_Documento_Ordem_Frete ();

			if (oid_Natureza_Operacao==0) oid_Natureza_Operacao=parametro_FixoED.getOID_Natureza_Operacao_Ordem_Frete ();

			if ("INSS".equals(DM_Tipo_Ordem_Frete)) {
				compromissoED.setOid_Pessoa(parametro_FixoED.getOID_Fornecedor_INSS());
			} else if ("IRRF".equals(DM_Tipo_Ordem_Frete)) {
				compromissoED.setOid_Pessoa(parametro_FixoED.getOID_Fornecedor_IRRF());
			}

			if ("INSS".equals(DM_Tipo_Ordem_Frete)) {
				oid_Tipo_Documento = parametro_FixoED.getOID_Tipo_Documento_INSS();
			} else if ("IRRF".equals(DM_Tipo_Ordem_Frete)) {
				oid_Tipo_Documento = parametro_FixoED.getOID_Tipo_Documento_IR();
			}

			compromissoED.setOid_Tipo_Documento (new Integer (oid_Tipo_Documento));
			compromissoED.setOid_Natureza_Operacao (new Integer (oid_Natureza_Operacao));
			compromissoVoltaED = compromissoBD.inclui (compromissoED);

			compromissoED.setOid_Compromisso (compromissoVoltaED.getOid_Compromisso ());

			NR_Compromisso = compromissoVoltaED.getNr_Compromisso ().intValue ();

			chave = String.valueOf (ed.getOID_Ordem_Frete ()) +
			String.valueOf (compromissoED.getOid_Compromisso ());


			Movimento_OrdemED.setOID_Movimento_Ordem (chave);
			Movimento_OrdemED.setDT_Emissao (Data.getDataDMY ());
			Movimento_OrdemED.setHR_Movimento_Ordem (ed.getHR_Emissao ());
			Movimento_OrdemED.setDT_Movimento_Ordem (Data.getDataDMY ());

			Movimento_OrdemED.setOID_Compromisso (compromissoED.getOid_Compromisso ());
			Movimento_OrdemED.setOID_Ordem_Frete (ed.getOID_Ordem_Frete ());
			Movimento_OrdemED.setDt_stamp (Data.getDataDMY ());
			Movimento_OrdemED.setUsuario_Stamp (ed.getUsuario_Stamp ());
			Movimento_OrdemED.setDm_Stamp (ed.getDm_Stamp ());

			sql = " insert into Movimentos_Ordens (OID_Movimento_Ordem, OID_Compromisso, OID_Ordem_Frete, DT_Movimento_Ordem, HR_Movimento_Ordem ) values ";
			sql += "('" + Movimento_OrdemED.getOID_Movimento_Ordem () + "','" +
			Movimento_OrdemED.getOID_Compromisso () + "','" +
			Movimento_OrdemED.getOID_Ordem_Frete () + "','" +
			Movimento_OrdemED.getDT_Movimento_Ordem () + "','" +
			Movimento_OrdemED.getHR_Movimento_Ordem () + "')";


			int i = executasql.executarUpdate (sql);

			sql="";
			if (ed.getNR_Parcela().intValue()<=1) sql =" UPDATE Ordens_Fretes SET NR_Compromisso_Adto1="+NR_Compromisso + " WHERE oid_Ordem_Frete='"+ed.getOID_Ordem_Frete () + "'";
			if (ed.getNR_Parcela().intValue()==2) sql =" UPDATE Ordens_Fretes SET NR_Compromisso_Adto2="+NR_Compromisso + " WHERE oid_Ordem_Frete='"+ed.getOID_Ordem_Frete () + "'";
			if (ed.getNR_Parcela().intValue()==3) sql =" UPDATE Ordens_Fretes SET NR_Compromisso_Saldo="+NR_Compromisso + " WHERE oid_Ordem_Frete='"+ed.getOID_Ordem_Frete () + "'";
			executasql.executarUpdate (sql);


		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "geraCompromisso (Ordem_FreteED ed)");
		}

		return NR_Compromisso;
	}


	public void geraContaCorrente (Ordem_FreteED ed, double VL_Lancamento, String acao)
	throws Excecoes {

		String sql = null;
		String oid_Conta_Corrente="";
		int oid_Tipo_Documento=0;
		int oid_Historico=0;
		int oid_Conta=0;
		ResultSet res = null;

		try {
			if (parametro_FixoED.getDM_Gera_Lancamento_Conta_Corrente_Ordem_Frete ().equals ("S") ||
					parametro_FixoED.getDM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento ().
					equals ("S")) {

				sql = " SELECT Contas_Correntes.oid_Conta_Corrente, Contas.oid_Tipo_Documento " +
				" FROM  Contas_Correntes, Contas "+
				" WHERE Contas_Correntes.oid_Conta = Contas.oid_Conta" +
				" AND   Contas_Correntes.OID_Pessoa = '" + ed.getOID_Motorista() + "'";


				res = this.executasql.executarConsulta (sql);
				while (res.next ()) {
					oid_Conta_Corrente = res.getString ("oid_Conta_Corrente");
					oid_Tipo_Documento = res.getInt ("Oid_Tipo_Documento");
				}
				if (!"".equals(oid_Conta_Corrente)) {
					Movimento_Conta_CorrenteED movContaCorED = new Movimento_Conta_CorrenteED ();
					Movimento_Conta_CorrenteBD movContaCorBD = new Movimento_Conta_CorrenteBD (this.executasql);
					movContaCorED.setOid_Conta_Corrente(oid_Conta_Corrente);

					movContaCorED.setDM_Debito_Credito("C");

					oid_Conta=parametro_FixoED.getOID_Conta_Ordem_Frete();
					oid_Historico=parametro_FixoED.getOID_Historico_Lancamento_Ordem_Frete();

					if ("ADIANTAMENTO".equals(DM_Tipo_Ordem_Frete)){
						oid_Historico=parametro_FixoED.getOID_Historico_Lancamento_Ordem_Frete_Adiantamento();
						oid_Conta=parametro_FixoED.getOID_Conta_Ordem_Frete_Adiantamento();
					}

					movContaCorED.setDM_Tipo_Lancamento("G");

					movContaCorED.setDT_Movimento_Conta_Corrente(Data.getDataDMY());
					movContaCorED.setDT_Movimento_Conta_Corrente(Data.getDataDMY());

					movContaCorED.setOid_Lote_Pagamento(0);


					movContaCorED.setNR_Documento(String.valueOf(ed.getNR_Ordem_Frete()));

					movContaCorED.setNM_Complemento_Historico("Ordem Frete - "+DM_Tipo_Ordem_Frete);

					movContaCorED.setVL_Lancamento(new Double(VL_Lancamento));
					movContaCorED.setOid_Conta(oid_Conta);

					movContaCorED.setOID_Tipo_Documento(new Integer(oid_Tipo_Documento));

					if (acao.equals("CANCELA")) {
						movContaCorED.setDM_Debito_Credito("D");
						oid_Historico=parametro_FixoED.getOID_Historico_Cancelamento_Ordem_Frete();
						movContaCorED.setNM_Complemento_Historico("Cancelado Ordem Frete - "+DM_Tipo_Ordem_Frete);
					}

					movContaCorED.setOid_Historico(new Integer(oid_Historico));

					movContaCorBD.inclui (movContaCorED);
				}

			}
		} catch (Exception e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "geraContaCorrente (Ordem_FreteED ed)");
		}
	}

	public byte[] OrdemFreteRelatorio (Ordem_FreteED ed) throws Excecoes {

		String sql = null;
		ResultSet res = null;
		ResultSet rs = null;

		sql = null;
		byte[] b = null;
		sql = " SELECT Ordens_Fretes.*, " +
		" Ordens_Fretes.OID_pessoa as oid_Proprietario, " +
		" Unidades.CD_Unidade, " +
		" Pessoas.NM_Fantasia, " +
		" Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario, " +
		" Pessoa_Local_Pagamento.NM_Razao_Social as NM_Local_Pagamento, " +
		" Pessoa_Motorista.NM_Razao_Social as NM_Motorista, " +
		" Cidade_Unidade.NM_Cidade as NM_Cidade_Unidade " +
		" FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Proprietario, Pessoas Pessoa_Local_Pagamento, Pessoas Pessoa_Motorista, Cidades Cidade_Unidade  ";
		if (ed.getOID_Pessoa_Pagador () != null && ed.getOID_Pessoa_Pagador ().length () > 4) {
			sql += " , Conhecimentos, Viagens, Ordens_Manifestos ";
		}


		sql += " WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade ";
		sql += " AND Ordens_Fretes.OID_Fornecedor = Pessoa_Proprietario.OID_Pessoa ";
		//sql += " AND Ordens_Fretes.OID_Pessoa = Pessoa_Proprietario.OID_Pessoa ";
		sql += " AND Ordens_Fretes.OID_Local_Pagamento = Pessoa_Local_Pagamento.OID_Pessoa ";
		sql += " AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa ";
		sql += " AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa ";
		sql += " AND Pessoas.OID_Cidade = Cidade_Unidade.oid_cidade ";

		if (ed.getOID_Pessoa_Pagador () != null && ed.getOID_Pessoa_Pagador ().length () > 4) {
			sql += " AND Conhecimentos.OID_CONHECIMENTO = Viagens.OID_CONHECIMENTO " +
			" AND Ordens_Manifestos.OID_MANIFESTO = Viagens.OID_MANIFESTO " +
			" AND Ordens_Manifestos.OID_ORDEM_FRETE = Ordens_Fretes.OID_ORDEM_FRETE " +
			" AND Conhecimentos.oid_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador() + "'";
		}

		if (String.valueOf (ed.getOID_Empresa ()) != null &&
				!String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
			sql += " and Unidades.OID_Empresa = " + ed.getOID_Empresa ();
		}

		if (String.valueOf (ed.getOID_Unidade ()) != null &&
				!String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
			sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
		}
		if (String.valueOf (ed.getOID_Pessoa ()) != null &&
				!String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
				!String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
			sql += " and Ordens_Fretes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
		}
		if (String.valueOf (ed.getOID_Motorista ()) != null &&
				!String.valueOf (ed.getOID_Motorista ()).equals ("") &&
				!String.valueOf (ed.getOID_Motorista ()).equals ("null")) {
			sql += " and Ordens_Fretes.OID_Motorista = '" + ed.getOID_Motorista () + "'";
		}
		if (String.valueOf (ed.getOID_Local_Pagamento ()) != null &&
				!String.valueOf (ed.getOID_Local_Pagamento ()).equals ("") &&
				!String.valueOf (ed.getOID_Local_Pagamento ()).equals ("null")) {
			sql += " and Ordens_Fretes.OID_Local_Pagamento = '" + ed.getOID_Local_Pagamento () + "'";
		}
		if (String.valueOf (ed.getDt_Emissao_Inicial ()) != null &&
				!String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("") &&
				!String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("null")) {
			sql += " and Ordens_Fretes.DT_Emissao >= '" + ed.getDt_Emissao_Inicial () +
			"'";
		}
		if (String.valueOf (ed.getDt_Emissao_Final ()) != null &&
				!String.valueOf (ed.getDt_Emissao_Final ()).equals ("") &&
				!String.valueOf (ed.getDt_Emissao_Final ()).equals ("null")) {
			sql += " and Ordens_Fretes.DT_Emissao <= '" + ed.getDt_Emissao_Final () +
			"'";
		}
		if (String.valueOf (ed.getDM_Frete ()) != null &&
				!String.valueOf (ed.getDM_Frete ()).equals ("null") &&
				!String.valueOf (ed.getDM_Frete ()).equals ("X") &&
				!String.valueOf (ed.getDM_Frete ()).equals ("")) {
			sql += " and Ordens_Fretes.DM_Frete = '" + ed.getDM_Frete () + "'";
		}

		if ("A".equals(ed.getDM_Acerto ())){
			sql += " and Ordens_Fretes.oid_Acerto is not null ";
		}
		if ("P".equals(ed.getDM_Acerto ())){
			sql += " and Ordens_Fretes.oid_Acerto is null ";
		}

		if (!"T".equals (ed.getDM_Tipo_Pessoa ())) {
			if ("FJ".equals(ed.getDM_Tipo_Pessoa())){
				sql += " and Ordens_Fretes.DM_Tipo_Pessoa <>'FE'";
			}else{
				sql += " and Ordens_Fretes.DM_Tipo_Pessoa ='" + ed.getDM_Tipo_Pessoa () + "'";
			}
		}

		if ("A".equals(ed.getDM_Adiantamento ())){
			sql += " and Ordens_Fretes.oid_Ordem_Principal ='' ";
		}
		if ("P".equals(ed.getDM_Adiantamento ())){
			sql += " and Ordens_Fretes.oid_Ordem_Principal <> '' ";
		}

		if (String.valueOf (ed.getDM_Tipo_Pagamento ()) != null &&
				!String.valueOf (ed.getDM_Tipo_Pagamento ()).equals ("null") &&
				!String.valueOf (ed.getDM_Tipo_Pagamento ()).equals ("T") &&
				!String.valueOf (ed.getDM_Tipo_Pagamento ()).equals ("")) {
			sql += " and Ordens_Fretes.DM_Tipo_Pagamento = '" + ed.getDM_Tipo_Pagamento () + "'";
		}

		if (String.valueOf (ed.getDM_Situacao ()) != null &&
				!String.valueOf (ed.getDM_Situacao ()).equals ("null") &&
				!String.valueOf (ed.getDM_Situacao ()).equals ("T") &&
				!String.valueOf (ed.getDM_Situacao ()).equals ("")) {
			sql += " and Ordens_Fretes.DM_Situacao = '" + ed.getDM_Situacao () + "'";
		}

		if ("C".equals(ed.getDM_Situacao ())){
			sql += " AND Ordens_Fretes.DM_Impresso = 'C' " ;
		}else {
			sql += " AND Ordens_Fretes.DM_Impresso = 'S' " ;
		}


		if (ed.getDM_Relatorio ().equals ("R1") ||
				ed.getDM_Relatorio ().equals ("R2")) {
			sql +=  " order by Ordens_Fretes.oid_pessoa ";
		}else if (ed.getDM_Relatorio ().equals ("R1_Adto") || ed.getDM_Relatorio ().equals ("R3")) {
			sql +=  " order by Ordens_Fretes.oid_Motorista ";
		}else if (ed.getDM_Relatorio ().equals ("R2_Adto")) {
			sql +=  " order by Ordens_Fretes.OID_Local_Pagamento ";
		}else {
			if ("T".equals(ed.getDM_Frete ())){
				sql += " order by unidades.cd_unidade, Ordens_Fretes.NR_Recibo ";
			}else sql += " order by unidades.cd_unidade, Ordens_Fretes.NR_ORDEM_FRETE ";
		}

		try {

			res = null;
			Ordem_FreteRL conRL = new Ordem_FreteRL ();
			res = this.executasql.executarConsulta (sql.toString ());

			if (ed.getDM_Relatorio ().equals ("R1_Adto") ||
					ed.getDM_Relatorio ().equals ("R2_Adto") ||
					ed.getDM_Relatorio ().equals ("R1") ||
					ed.getDM_Relatorio ().equals ("R2") ||
					ed.getDM_Relatorio ().equals ("R3")) {

				Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();

				String OID_Pessoa = "PRIMEIRO";
				String OID_Proprietario_Motorista = "";
				double vl_ordem_frete = 0;
				double vl_ordem_total = 0;
				double vl_adto_pendente = 0;
				double vl_set_senat = 0;
				double vl_irrf = 0;
				double vl_inss_prestador = 0;
				double vl_inss_empresa = 0;
				double vl_total_ordem_frete = 0;
				double vl_total_adto_pendente = 0;
				double vl_total_set_senat = 0;
				double vl_total_irrf = 0;
				double vl_total_inss_prestador = 0;
				double vl_total_inss_empresa = 0;

				long Nr_Sort = (new Long(String.valueOf(System.currentTimeMillis()).toString().substring(3,12)).longValue());

				while (res.next ()) {
					if (!res.getString ("DM_Impresso").equals ("C")) {
						OID_Proprietario_Motorista = res.getString ("oid_Fornecedor");

						if (ed.getDM_Relatorio ().equals ("R1_Adto") || ed.getDM_Relatorio ().equals ("R3")) {
							OID_Proprietario_Motorista = res.getString ("OID_Motorista");
						}
						if (ed.getDM_Relatorio ().equals ("R2_Adto")) {
							OID_Proprietario_Motorista = res.getString ("OID_Local_Pagamento");
						}


						vl_ordem_total = res.getDouble ("VL_Ordem_Frete") + res.getDouble ("VL_Coleta") + res.getDouble ("VL_Carga") + res.getDouble ("VL_Descarga")+
						res.getDouble ("VL_Premio") + res.getDouble ("VL_Outros") +
						res.getDouble ("VL_Pedagio") - res.getDouble ("VL_Descontos");
						vl_ordem_frete = vl_ordem_total;
						vl_ordem_frete = res.getDouble ("VL_Ordem_Frete");
						vl_adto_pendente = 0;
						if (res.getLong ("oid_acerto") <= 0) {
							vl_adto_pendente = vl_ordem_total;
							//vl_adto_pendente = res.getDouble ("VL_Ordem_Frete");
						}
						vl_set_senat = res.getDouble ("vl_set_senat");
						vl_irrf = res.getDouble ("vl_irrf");
						vl_inss_prestador = res.getDouble ("vl_inss_prestador");
						vl_inss_empresa = res.getDouble ("vl_inss_empresa");

						if (OID_Pessoa.equals ("PRIMEIRO") || OID_Proprietario_Motorista.equals (OID_Pessoa)) {
							OID_Pessoa = OID_Proprietario_Motorista;
							vl_total_ordem_frete = vl_total_ordem_frete + vl_ordem_frete;
							vl_total_adto_pendente = vl_total_adto_pendente + vl_adto_pendente;
							vl_total_set_senat = vl_total_set_senat + vl_set_senat;
							vl_total_irrf = vl_total_irrf + vl_irrf;
							vl_total_inss_prestador = vl_total_inss_prestador + vl_inss_prestador;
							vl_total_inss_empresa = vl_total_inss_empresa + vl_inss_empresa;
						}
						else {
							if (vl_total_ordem_frete > 0) {
								Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
								edAuxiliar1.setNM_Classifica1 (OID_Pessoa);
								edAuxiliar1.setNR_Sort (Nr_Sort);
								edAuxiliar1.setVL_Classifica1 (vl_total_ordem_frete);
								edAuxiliar1.setVL_Classifica2 (vl_total_set_senat);
								edAuxiliar1.setVL_Classifica3 (vl_total_irrf);
								edAuxiliar1.setVL_Classifica4 (vl_total_inss_prestador);
								edAuxiliar1.setVL_Classifica5 (vl_total_inss_empresa);
								edAuxiliar1.setVL_Classifica6 (vl_total_adto_pendente);

								Auxiliar1RN.inclui (edAuxiliar1);
							}
							OID_Pessoa = OID_Proprietario_Motorista;
							vl_total_ordem_frete = vl_ordem_frete;
							vl_total_adto_pendente = vl_adto_pendente;
							vl_total_set_senat = vl_set_senat;
							vl_total_irrf = vl_irrf;
							vl_total_inss_prestador = vl_inss_prestador;
							vl_total_inss_empresa = vl_inss_empresa;
						}
					}
				}

				if (vl_total_ordem_frete > 0) {
					Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
					edAuxiliar1.setNM_Classifica1 (OID_Pessoa);
					edAuxiliar1.setNR_Sort (Nr_Sort);
					edAuxiliar1.setVL_Classifica1 (vl_total_ordem_frete);
					edAuxiliar1.setVL_Classifica2 (vl_total_set_senat);
					edAuxiliar1.setVL_Classifica3 (vl_total_irrf);
					edAuxiliar1.setVL_Classifica4 (vl_total_inss_prestador);
					edAuxiliar1.setVL_Classifica5 (vl_total_inss_empresa);
					edAuxiliar1.setVL_Classifica6 (vl_total_adto_pendente);

					Auxiliar1RN.inclui (edAuxiliar1);

				}
				sql = " select * from Auxiliar1, Pessoas where auxiliar1.nm_classifica1 = pessoas.oid_pessoa and   Auxiliar1.Nr_Sort = " + Nr_Sort;
				sql += " order by Pessoas.nm_razao_social  ";

				res = this.executasql.executarConsulta (sql.toString ());

				if (ed.getDM_Relatorio ().equals ("R1") || ed.getDM_Relatorio ().equals ("R3")) {
					b = conRL.geraResumoOrdem_Frete (ed , res);
				}
				if (ed.getDM_Relatorio ().equals ("R1_Adto")) {
					b = conRL.geraResumoOrdem_Frete_Adiantamento (ed , res);
				}
				if (ed.getDM_Relatorio ().equals ("R2_Adto")) {
					b = conRL.geraResumoOrdem_Frete_Adiantamento (ed , res);
				}
				if (ed.getDM_Relatorio ().equals ("R2")) {
					b = conRL.geraResumoOrdem_Frete_Contabil (ed , res);
				}

				sql = "select oid_auxiliar1 from auxiliar1 where Auxiliar1.Nr_Sort = " +
				Nr_Sort;

				res = this.executasql.executarConsulta (sql.toString ());

				Auxiliar1RN AuxRN = new Auxiliar1RN ();

				while (res.next ()) {
					Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
					edAuxiliar1.setOID_auxiliar1 (new Integer (res.getString (
							"oid_auxiliar1")));
					AuxRN.deleta (edAuxiliar1);
				}

			}
			if (ed.getDM_Relatorio ().equals ("M1_Adto")) {
				b = conRL.OrdemFreteRelatorio_Adiantamento (res , ed);
			}
			if (ed.getDM_Relatorio ().equals ("M1_Vale_Pedagio")) {
				b = conRL.OrdemFreteRelatorio_Vale_Pedagio (res , ed);
			}
			if (ed.getDM_Relatorio ().equals ("M1") || ed.getDM_Relatorio ().equals ("M2") || ed.getDM_Relatorio ().equals ("M3") || ed.getDM_Relatorio ().equals ("M4")) {
				b = conRL.OrdemFreteRelatorio (res , ed);
			}
		}

		catch (Excecoes e) {
			throw e;
		}
		catch (Exception exc) {
			Excecoes exce = new Excecoes ();
			exce.setExc (exc);
			exce.setMensagem ("Erro no m�todo listar");
			exce.setClasse (this.getClass ().getName ());
			exce.setMetodo ("OrdemFreteRelatorio(Ordem_FreteED ed)");
		}
		return b;
	}

	public void deleta (Ordem_FreteED ed)
	throws Excecoes {
		String sql = null;
		String erro = "";
		ResultSet res = null;

		int i = 0;
		int compromisso = 0;
		try {

			sql =
				" Select vl_pagamento from Movimentos_Ordens, Movimentos_Compromissos " +
				" WHERE Movimentos_ordens.oid_Compromisso = Movimentos_Compromissos.oid_Compromisso " +
				" AND   Movimentos_Ordens.oid_Ordem_Frete  = '" + ed.getOID_Ordem_Frete () + "'";
			res = this.executasql.executarConsulta (sql);
			if (res.next ()) {
				throw new Excecoes("H� pagamentos nos compromissos desta Ordem Frete/Adiantamento");
			}

			sql = " Select vl_pagamento from Movimentos_Ordens, Lotes_Compromissos " +
			" WHERE Movimentos_ordens.oid_Compromisso = Lotes_Compromissos.oid_Compromisso " +
			" AND   Movimentos_Ordens.oid_Ordem_Frete  = '" + ed.getOID_Ordem_Frete () + "'";

			res = this.executasql.executarConsulta (sql);
			if (res.next ()) {
				throw new Excecoes("H� Documento para Pagamento para desta Ordem Frete/Adiantamento");
			}

			sql =
				" select oid_Compromisso, oid_Movimento_Ordem from Movimentos_Ordens " +
				"  WHERE Movimentos_Ordens.oid_Ordem_Frete  = '" +
				ed.getOID_Ordem_Frete () + "'";

			res = this.executasql.executarConsulta (sql);
			while (res.next ()) {
				compromisso++;
				sql = "delete from Movimentos_Ordens WHERE oid_Movimento_Ordem = ";
				sql += "('" + res.getString ("oid_Movimento_Ordem") + "')";

				i = executasql.executarUpdate (sql);

				sql = "delete from Compromissos WHERE oid_Compromisso= " +
				res.getString ("oid_Compromisso");

				i = executasql.executarUpdate (sql);
			}

			this.exclui_rateio_Ordem_Frete (ed);


			sql = " SELECT Ordens_Manifestos.OID_Manifesto from Ordens_Manifestos ";
			sql += " WHERE Ordens_Manifestos.oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
			res = null;
			res = this.executasql.executarConsulta (sql);
			while (res.next ()) {

				sql = " update Manifestos set DM_LANCADO_ORDEM_FRETE = 'N'";
				sql += " where oid_Manifesto = '" + res.getString ("OID_Manifesto") + "'";

				i = executasql.executarUpdate (sql);
			}

			sql = "delete from Ordens_Manifestos WHERE oid_Ordem_Frete = ";
			sql += "('" + ed.getOID_Ordem_Frete () + "')";

			i = executasql.executarUpdate (sql);

			if (ed.getOID_Ordem_Principal () != null &&
					!ed.getOID_Ordem_Principal ().equals ("") &&
					!ed.getOID_Ordem_Principal ().equals ("null")) {
				this.calcula_Saldo_Adiantamento (ed);
			}

			sql = "Update Ordens_Fretes set oid_Ordem_Principal ='' WHERE oid_Ordem_Principal = ";
			sql += "('" + ed.getOID_Ordem_Frete () + "')";
			i = executasql.executarUpdate (sql);

			if (compromisso > 0) {

				sql = "Update Ordens_Fretes set DM_Impresso = 'C', VL_IRRF = 0 , TX_Observacao='Exclu�da' WHERE oid_Ordem_Frete = ";
				sql += "('" + ed.getOID_Ordem_Frete () + "')";
			}
			else {
				sql = "delete from Ordens_Fretes WHERE oid_Ordem_Frete = ";
				sql += "('" + ed.getOID_Ordem_Frete () + "')";
			}

			i = executasql.executarUpdate (sql);
		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "deleta (Ordem_FreteED ed)");
		}

	}

	public void Master_deleta (Ordem_FreteED ed) throws Excecoes {

		String sql = null;
		String erro = "";
		ResultSet res = null;

		try {


			exclui_rateio_Ordem_Frete(ed);

			sql = " SELECT Ordens_Manifestos.OID_Manifesto from Ordens_Manifestos ";
			sql += " WHERE Ordens_Manifestos.oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
			res = null;
			res = this.executasql.executarConsulta (sql);
			while (res.next ()) {

				sql = " update Manifestos set DM_LANCADO_ORDEM_FRETE = 'N'";
				sql += " where oid_Manifesto = '" + res.getString ("OID_Manifesto") + "'";

				executasql.executarUpdate (sql);
			}


			sql = "delete from Ordens_Manifestos WHERE oid_Ordem_Frete = ";
			sql += "('" + ed.getOID_Ordem_Frete () + "')";

			int i = executasql.executarUpdate (sql);

			sql = "delete from Ordens_Fretes WHERE oid_Ordem_Frete = ";
			sql += "('" + ed.getOID_Ordem_Frete () + "')";

			i = executasql.executarUpdate (sql);


		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao excluir Master");
			excecoes.setMetodo ("Master_deleta");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	public void confirma_Recuperacao_Master (Ordem_FreteED ed) throws Excecoes {

		String sql = null;

		try {
			sql = " SELECT DM_Situacao from Ordens_Fretes ";
			sql += " where oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

			ResultSet res = null;
			res = this.executasql.executarConsulta (sql);

			while (res.next ()) {

				ed.setDM_Situacao (res.getString ("DM_Situacao"));
			}

			if (ed.getDM_Situacao () != null &&
					!ed.getDM_Situacao ().equals ("") &&
					!ed.getDM_Situacao ().equals ("null") &&
					ed.getDM_Situacao ().equals ("S")) {
				Excecoes exc = new Excecoes ();
				throw exc;
			}

			sql = " update Ordens_Fretes set DT_Master_Recuperado= '" +
			ed.getDT_Master_Recuperado () + "', HR_Master_Recuperado= '" +
			ed.getHR_Master_Recuperado () + "',  DM_Situacao='S' ";
			sql += " where oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

			int i = executasql.executarUpdate (sql);

			if (parametro_FixoED.getDM_Gera_Ocorrencia_Viagem ().equals ("S")) {

				sql = " SELECT OID_Conhecimento from Viagens, Ordens_Manifestos ";
				sql +=
					" WHERE Viagens.OID_Manifesto = Ordens_Manifestos.oid_Manifesto ";
				sql += " AND Ordens_Manifestos.oid_Ordem_Frete = '" +
				ed.getOID_Ordem_Frete () + "'";

				res = this.executasql.executarConsulta (sql);


				//popula
				while (res.next ()) {

					Ocorrencia_ConhecimentoRN Ocorrencia_Conhecimentorn = new
					Ocorrencia_ConhecimentoRN ();
					Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new
					Ocorrencia_ConhecimentoED ();

					sql = " SELECT  * from Tipos_Ocorrencias Where DM_Tipo='R' ";
					ResultSet res2 = null;
					res2 = this.executasql.executarConsulta (sql);
					String TX_Observacao = "incluindo viagem";
					while (res2.next ()) {

						ocorrencia_ConhecimentoED.setDT_Ocorrencia_Conhecimento (Data.
								getDataDMY ());
						ocorrencia_ConhecimentoED.setHR_Ocorrencia_Conhecimento (Data.
								getHoraHM ());
						ocorrencia_ConhecimentoED.setOID_Tipo_Ocorrencia (res2.getLong (
								"oid_Tipo_Ocorrencia"));
						ocorrencia_ConhecimentoED.setOID_Conhecimento (res.getString (
						"oid_Conhecimento"));
						ocorrencia_ConhecimentoED.setTX_Observacao (TX_Observacao);
						ocorrencia_ConhecimentoED.setNM_Pessoa_Entrega ("");
						ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Tipo"));
						ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Acesso"));
						ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Avaria"));
						ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Status"));
						Ocorrencia_Conhecimentorn.inclui (ocorrencia_ConhecimentoED);
					}
				}
			}

		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao Confirmar Master");
			excecoes.setMetodo ("alterar");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	public void desvincula_Adto_Ordem_Principal (Ordem_FreteED ed) throws Excecoes {

		String sql = null;

		try {
			sql = "Select * " +
			" from " +
			" Ordens_Fretes " +
			" where Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

			ResultSet resCTRC = null;
			resCTRC = this.executasql.executarConsulta (sql);

			while (resCTRC.next ()) {

				ed.setOID_Ordem_Frete (resCTRC.getString ("OID_Ordem_Frete"));
				ed.setOID_Ordem_Principal (resCTRC.getString ("OID_Ordem_Principal"));

				if (ed.getOID_Ordem_Principal () != null &&
						!ed.getOID_Ordem_Principal ().equals ("") &&
						!ed.getOID_Ordem_Principal ().equals ("null")) {
					this.calcula_Saldo_Adiantamento (ed);
				}

				sql = " UPDATE Ordens_Fretes set OID_Ordem_Principal = '' "  +
				" WHERE oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
				executasql.executarUpdate (sql);

			}
		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro desvincula_Adto_Ordem_Principal");
			excecoes.setMetodo ("desvincula_Adto_Ordem_Principal(Ordem_FreteED ed)");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	public String cancela (Ordem_FreteED ed) throws Excecoes {

		String sql = null;
		String tx_Observacao="";
		String nm_situacao="";
		ResultSet res = null;
		try {

			sql = " SELECT Movimentos_Ordens.OID_Compromisso, Compromissos.NR_Compromisso " +
				  " FROM   Movimentos_Ordens, Compromissos " +
				  " WHERE  Movimentos_Ordens.oid_Compromisso = Compromissos.oid_Compromisso "+
				  " AND    Movimentos_Ordens.oid_Ordem_Frete = '" +  ed.getOID_Ordem_Frete () + "'";

			res = this.executasql.executarConsulta (sql);
			if (res.next ()) {
				nm_situacao=" Exclui o Compromisso NR: "+ res.getString("NR_Compromisso") + " vinculado a esta Ordem Frete! ";
			}

			sql = " SELECT Ordens_Fretes.NR_Ordem_Frete  " +
			  " FROM   Ordens_Fretes " +
			  " WHERE  (Ordens_Fretes.DM_Situacao <>'C' OR Ordens_Fretes.DM_Situacao is null) " +
			  " AND    Ordens_Fretes.oid_Ordem_Principal = '" +  ed.getOID_Ordem_Frete () + "'";

			res = this.executasql.executarConsulta (sql);
			if (res.next ()) {
				nm_situacao+=" Cancelar Adto NR: "+ res.getString("NR_Ordem_Frete") + " vinculado a esta Ordem Frete! ";
			}
			//.println(sql);


			sql = " SELECT Movimentos_Conhecimentos.oid_Lote_Fornecedor, Conhecimentos.NR_Conhecimento  " +
			  " FROM   Movimentos_Conhecimentos, Conhecimentos " +
			  " WHERE  Movimentos_Conhecimentos.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
			  " AND    Movimentos_Conhecimentos.oid_Ordem_Frete = '" +  ed.getOID_Ordem_Frete () + "'";

			res = this.executasql.executarConsulta (sql);
			if (res.next ()) {
				nm_situacao+=" Cancelar a Aprov.Pagamento NR: "+ res.getString("oid_Lote_Fornecedor") + " / Conhecimento" + res.getString("NR_Conhecimento") + " vinculado a esta Ordem Frete!";
			}

			if (nm_situacao.equals("")){
				sql = "SELECT * " +
					" FROM  Ordens_Fretes " +
					" WHERE Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

				ResultSet resCTRC =  this.executasql.executarConsulta (sql);

				double VL_Ordem_Frete=0;

				while (resCTRC.next ()) {

					tx_Observacao=resCTRC.getString ("tx_Observacao");
					ed.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
					ed.setOID_Ordem_Frete (resCTRC.getString ("OID_Ordem_Frete"));
					ed.setOID_Ordem_Principal (resCTRC.getString ("OID_Ordem_Principal"));
					ed.setOID_Pessoa (resCTRC.getString ("Oid_Pessoa"));
					ed.setOID_Motorista (resCTRC.getString ("OID_Motorista"));

					ed.setOID_Veiculo (resCTRC.getString ("OID_Veiculo"));
					ed.setNR_Placa (resCTRC.getString ("OID_Veiculo"));
					ed.setDM_Frete (resCTRC.getString ("DM_Frete"));

					ed.setDT_Emissao (resCTRC.getString ("DT_Emissao"));
					DataFormatada.setDT_FormataData (ed.getDT_Emissao ());
					ed.setDT_Emissao (DataFormatada.getDT_FormataData ());

					ed.setDT_Adiantamento1 (resCTRC.getString ("DT_Emissao"));
					DataFormatada.setDT_FormataData (ed.getDT_Adiantamento1 ());
					ed.setDT_Adiantamento1 (DataFormatada.getDT_FormataData ());

					ed.setDT_Adiantamento2 (resCTRC.getString ("DT_Emissao"));
					DataFormatada.setDT_FormataData (ed.getDT_Adiantamento2 ());
					ed.setDT_Adiantamento2 (DataFormatada.getDT_FormataData ());

					ed.setDT_Saldo (resCTRC.getString ("DT_Emissao"));
					DataFormatada.setDT_FormataData (ed.getDT_Saldo ());
					ed.setDT_Saldo (DataFormatada.getDT_FormataData ());

					ed.setVL_Adiantamento1 (resCTRC.getDouble ("VL_Adiantamento1"));
					ed.setVL_Adiantamento2 (resCTRC.getDouble ("VL_Adiantamento2"));
					ed.setVL_Saldo (resCTRC.getDouble ("VL_Saldo"));
					ed.setNR_Ordem_Frete (resCTRC.getLong ("NR_Ordem_Frete"));
					ed.setVL_Ordem_Frete (resCTRC.getDouble ("VL_Ordem_Frete"));

					if (ed.getOID_Ordem_Principal () != null &&
							!ed.getOID_Ordem_Principal ().equals ("") &&
							!ed.getOID_Ordem_Principal ().equals ("null")) {
						this.calcula_Saldo_Adiantamento (ed);
					}


					VL_Ordem_Frete = ed.getVL_Ordem_Frete ();


					double vl_cotacao = 1;
					double vl_cotacao_padrao = 1;
					if (resCTRC.getDouble ("VL_Cotacao") > 0 &&
							resCTRC.getDouble ("VL_Cotacao_Padrao") > 0 &&
							resCTRC.getDouble ("VL_Cotacao") !=
								resCTRC.getDouble ("VL_Cotacao_Padrao")) {
						vl_cotacao = resCTRC.getDouble ("VL_Cotacao");
						vl_cotacao_padrao = resCTRC.getDouble ("VL_Cotacao_Padrao");
						VL_Ordem_Frete = Valor.round (VL_Ordem_Frete / vl_cotacao *
								vl_cotacao_padrao , 2);
					}

					if ("A".equals(ed.getDM_Frete()) && parametro_FixoED.getDM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento (). equals ("S")) {
						this.geraContaCorrente(ed, VL_Ordem_Frete, "CANCELA");
					}
				}


				sql = " SELECT Ordens_Manifestos.OID_Manifesto from Ordens_Manifestos " +
					  " WHERE Ordens_Manifestos.oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
				res = this.executasql.executarConsulta (sql);
				while (res.next ()) {
					sql = " UPDATE Manifestos set DM_LANCADO_ORDEM_FRETE = 'N'" +
						  " WHERE  oid_Manifesto = '" + res.getString ("OID_Manifesto") + "'";
					executasql.executarUpdate (sql);
				}

				if ("S".equals(parametro_FixoED.getDM_Gera_Rateio_Custo_Ordem_Frete())){
					this.exclui_rateio_Ordem_Frete (ed);
				}


				sql = "DELETE FROM Ordens_Manifestos WHERE oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
				executasql.executarUpdate (sql);

				tx_Observacao=ed.getTX_Observacao()+ " (OBS:" + tx_Observacao + ")";

				if (tx_Observacao !=null && tx_Observacao.length()>200) {
					tx_Observacao=tx_Observacao.substring(0,200);
				}

				sql = " UPDATE Ordens_Fretes set TX_Observacao='" + tx_Observacao +"', DM_Impresso = 'C',  DM_Situacao = 'C',  VL_IRRF = 0" +
					  " WHERE  oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
				executasql.executarUpdate (sql);


				//desvincula adtos
				sql = " SELECT Ordens_Fretes.OID_Ordem_Frete from Ordens_Fretes " +
					  " WHERE Ordens_Fretes.oid_ordem_principal = '" + ed.getOID_Ordem_Frete () + "'";
				res = this.executasql.executarConsulta (sql);
				while (res.next ()) {
					sql = " UPDATE Ordens_Fretes set oid_ordem_principal = '' WHERE OID_Ordem_Frete = '" + res.getString ("OID_Ordem_Frete") + "'";
					executasql.executarUpdate (sql);
				}

				//desfaz os lancamentos contabeis do movimento
				if ("S_____".equals(parametro_FixoED.getDM_Gera_Lancamento_Contabil()))
				{
					new Lancamento_ContabilBD(this.executasql).deleta_CTB_Ordem_Frete(ed);
				}
				nm_situacao="OK";

			}
		}
		catch (Exception exc) {
			nm_situacao="ERRO ao Cancelar !!!";
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao excluir Ordem de Frete");
			excecoes.setMetodo ("cancela(Ordem_FreteED ed)");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return nm_situacao;
	}

	public ArrayList GeraEDI_Ordem_Frete (Tipo_EventoED ed) throws Excecoes {

		String sql = null;
		ArrayList list = new ArrayList ();
		double VL_Auxiliar = 0;
		try {

			if (String.valueOf (ed.getCd_Tipo_Evento ()) != null &&
					String.valueOf (ed.getCd_Tipo_Evento ()).equals ("IRF")) {

				sql = "SELECT SUM(VL_IRRF) as VL_Total_IRRF from Ordens_Fretes ";
				sql += " WHERE Ordens_Fretes.VL_IRRF > 0";

				if (String.valueOf (ed.getDt_Inicial ()) != null &&
						!String.valueOf (ed.getDt_Inicial ()).equals ("") &&
						!String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
					sql += " and Ordens_Fretes.DT_Emissao >= '" + ed.getDt_Inicial () +
					"'";
				}
				if (String.valueOf (ed.getDt_Final ()) != null &&
						!String.valueOf (ed.getDt_Final ()).equals ("") &&
						!String.valueOf (ed.getDt_Final ()).equals ("null")) {
					sql += " and Ordens_Fretes.DT_Emissao <= '" + ed.getDt_Final () + "'";
				}
				if (String.valueOf (ed.getOID_Unidade ()) != null &&
						!String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
					sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
				}

				sql += " and Ordens_Fretes.DM_Frete = 'P' ";
				sql += " and Ordens_Fretes.Dm_Impresso = 'S' ";

				ResultSet res = null;
				res = this.executasql.executarConsulta (sql);


				while (res.next ()) {
					Tipo_EventoED edVolta = new Tipo_EventoED ();
					sql =
						" select CD_UNIDADE_CONTABIL from Unidades where OID_UNIDADE = " +
						ed.getOID_Unidade ();

					ResultSet resTP = this.executasql.executarConsulta (sql);
					while (resTP.next ()) {
						edVolta.setCD_Unidade_Contabil (resTP.getString (
								"CD_UNIDADE_CONTABIL"));
					}

					edVolta.setCd_Conta_Credito (ed.getCd_Conta_Credito ());
					edVolta.setCd_Conta_Debito (" ");
					edVolta.setCd_Historico (ed.getCd_Historico ());
					edVolta.setNm_Complemento_Historico (" ");
					edVolta.setNm_Arquivo_Saida (ed.getNm_Arquivo_Saida ());

					edVolta.setDt_Lancamento (ed.getDt_Final ());

					edVolta.setVl_Lancamento (res.getDouble ("VL_Total_IRRF"));
					edVolta.setDm_Totalizado ("S");

					list.add (edVolta);

					edVolta = new Tipo_EventoED ();

					edVolta.setCd_Conta_Credito (" ");
					edVolta.setCd_Conta_Debito (ed.getCd_Conta_Debito ());
					edVolta.setCd_Historico (ed.getCd_Historico ());
					edVolta.setNm_Complemento_Historico (" ");
					edVolta.setNm_Arquivo_Saida (ed.getNm_Arquivo_Saida ());

					edVolta.setDt_Lancamento (ed.getDt_Final ());

					edVolta.setVl_Lancamento (res.getDouble ("VL_Total_IRRF"));
					edVolta.setDm_Totalizado ("S");

					list.add (edVolta);

				}
			}

			if (String.valueOf (ed.getCd_Tipo_Evento ()) != null &&
					String.valueOf (ed.getCd_Tipo_Evento ()).equals ("CFF")) {

				sql =
					"SELECT SUM(VL_Ordem_Frete) as VL_Ordem_Frete_Total from Ordens_Fretes ";
				sql += " WHERE Ordens_Fretes.VL_Ordem_Frete > 0";

				if (String.valueOf (ed.getDt_Inicial ()) != null &&
						!String.valueOf (ed.getDt_Inicial ()).equals ("") &&
						!String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
					sql += " and Ordens_Fretes.DT_Emissao >= '" + ed.getDt_Inicial () +
					"'";
				}
				if (String.valueOf (ed.getDt_Final ()) != null &&
						!String.valueOf (ed.getDt_Final ()).equals ("") &&
						!String.valueOf (ed.getDt_Final ()).equals ("null")) {
					sql += " and Ordens_Fretes.DT_Emissao <= '" + ed.getDt_Final () + "'";
				}
				if (String.valueOf (ed.getOID_Unidade ()) != null &&
						!String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
					sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
				}
				sql += " and Ordens_Fretes.DM_Frete = 'P' ";
				sql += " and Ordens_Fretes.Dm_Impresso = 'S' ";

				ResultSet res = null;
				res = this.executasql.executarConsulta (sql);


				//popula
				while (res.next ()) {

					Tipo_EventoED edVolta = new Tipo_EventoED ();
					sql =
						" select CD_UNIDADE_CONTABIL from Unidades where OID_UNIDADE = " +
						ed.getOID_Unidade ();

					ResultSet resTP = this.executasql.executarConsulta (sql);
					while (resTP.next ()) {
						edVolta.setCD_Unidade_Contabil (resTP.getString (
								"CD_UNIDADE_CONTABIL"));
					}

					edVolta.setCd_Conta_Credito (ed.getCd_Conta_Credito ());
					edVolta.setCd_Conta_Debito (" ");
					edVolta.setCd_Historico (ed.getCd_Historico ());
					edVolta.setNm_Complemento_Historico (" ");
					edVolta.setNm_Arquivo_Saida (ed.getNm_Arquivo_Saida ());

					edVolta.setDt_Lancamento (ed.getDt_Final ());

					edVolta.setVl_Lancamento (res.getDouble ("VL_Ordem_Frete_Total"));

					edVolta.setDm_Totalizado ("S");

					list.add (edVolta);

					edVolta = new Tipo_EventoED ();

					edVolta.setCd_Conta_Credito (" ");
					edVolta.setCd_Conta_Debito (ed.getCd_Conta_Debito ());

					edVolta.setCd_Historico (ed.getCd_Historico ());
					edVolta.setNm_Complemento_Historico (" ");
					edVolta.setNm_Arquivo_Saida (ed.getNm_Arquivo_Saida ());

					edVolta.setDt_Lancamento (ed.getDt_Final ());

					edVolta.setVl_Lancamento (res.getDouble ("VL_Ordem_Frete_Total"));
					edVolta.setDm_Totalizado ("S");

					edVolta.setDm_Totalizado ("S");

					list.add (edVolta);

				}
			}

			if (String.valueOf (ed.getCd_Tipo_Evento ()) != null &&
					String.valueOf (ed.getCd_Tipo_Evento ()).equals ("CFT")) {

				sql = "SELECT OID_Ordem_Frete, OID_VEICULO, DT_Emissao, NR_Ordem_Frete, VL_Ordem_Frete, VL_IRRF,  Pessoas.NM_Fantasia from Ordens_Fretes, Unidades, Pessoas ";
				sql += " WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade ";
				sql += " AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa ";
				sql += " AND Ordens_Fretes.VL_Ordem_Frete > 0";

				if (String.valueOf (ed.getDt_Inicial ()) != null &&
						!String.valueOf (ed.getDt_Inicial ()).equals ("") &&
						!String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
					sql += " and Ordens_Fretes.DT_Emissao >= '" + ed.getDt_Inicial () +
					"'";
				}
				if (String.valueOf (ed.getDt_Final ()) != null &&
						!String.valueOf (ed.getDt_Final ()).equals ("") &&
						!String.valueOf (ed.getDt_Final ()).equals ("null")) {
					sql += " and Ordens_Fretes.DT_Emissao <= '" + ed.getDt_Final () + "'";
				}

				sql += " and Ordens_Fretes.DM_Frete = 'P' ";
				sql +=
					" and Ordens_Fretes.Dm_Impresso = 'S' order by unidades.cd_unidade ";

				ResultSet res = null;
				res = this.executasql.executarConsulta (sql);


				//popula
				while (res.next ()) {

					Tipo_EventoED edVolta = new Tipo_EventoED ();
					sql =
						" select CD_UNIDADE_CONTABIL from Unidades where OID_UNIDADE = " +
						ed.getOID_Unidade ();

					ResultSet resTP = this.executasql.executarConsulta (sql);
					while (resTP.next ()) {
						edVolta.setCD_Unidade_Contabil (resTP.getString (
								"CD_UNIDADE_CONTABIL"));
					}

					edVolta.setCd_Conta_Credito (ed.getCd_Conta_Credito ());
					edVolta.setCd_Conta_Debito (" ");
					edVolta.setCd_Historico (ed.getCd_Historico ());
					edVolta.setNm_Complemento_Historico (" ");
					edVolta.setNm_Arquivo_Saida (ed.getNm_Arquivo_Saida ());

					edVolta.setDt_Lancamento (res.getString ("DT_Emissao"));
					DataFormatada.setDT_FormataData (edVolta.getDt_Lancamento ());
					edVolta.setDt_Lancamento (DataFormatada.getDT_FormataData ());

					edVolta.setNr_Documento (new Long (res.getLong ("NR_Ordem_Frete")).
							longValue ());
					edVolta.setVl_Lancamento (res.getDouble ("VL_Ordem_Frete"));

					edVolta.setDm_Totalizado ("N");

					list.add (edVolta);

					edVolta = new Tipo_EventoED ();

					edVolta.setCd_Conta_Credito (" ");
					edVolta.setCd_Conta_Debito (ed.getCd_Conta_Debito ());
					edVolta.setCd_Historico (ed.getCd_Historico ());
					edVolta.setNm_Complemento_Historico (" ");
					edVolta.setNm_Arquivo_Saida (ed.getNm_Arquivo_Saida ());

					edVolta.setDt_Lancamento (ed.getDt_Final ());

					edVolta.setNr_Documento (new Long (res.getLong ("NR_Ordem_Frete")).
							longValue ());

					edVolta.setVl_Lancamento (res.getDouble ("VL_Ordem_Frete"));
					edVolta.setDm_Totalizado ("N");

					list.add (edVolta);

				}
			}

		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao listar Ordem Frete");
			excecoes.setMetodo ("lista");
			excecoes.setExc (exc);
			throw excecoes;
		}

		return (list);
	}

	public Ordem_FreteED calcula_Ordem_Frete (Ordem_FreteED ed)
	throws Excecoes {
		String sql = null;
		String DT_Emissao_Ordem_Frete = null;
		double VL_IRRF_Acumulado = 0;
		double VL_INSS_Acumulado = 0;
		double VL_Ordem_Frete_Acumulado = 0;
		double VL_Base_IRRF = 0;
		double VL_IRRF_Calculado = 0;
		long NR_DEPENDENTES = 0;
		double VL_Base_Calculo = 0;


		Ordem_FreteED edVolta = new Ordem_FreteED ();
		try {
			ResultSet res = null;
            // O atributo DM_Calcula_INSS_Para_Motorista do par�metro_fixo serve para calcular o INSS e IRRF para o Motorista
			// se estiver igual a "S", caso contr�rio calcular� para o Propriet�rio.
     		ed.setDM_Tipo_Pessoa(consultaTipo_Pessoa(ed.getOID_Fornecedor(), ed.getOID_Motorista()));


			if ("PF".equals(ed.getDM_Tipo_Pessoa())) {
				if (parametro_FixoED.getDM_Calcula_INSS ().equals ("S") ||
						parametro_FixoED.getDM_Calcula_IRRF ().equals ("S")) {

					DT_Emissao_Ordem_Frete = ("01" + ed.getDT_Emissao ().substring (2 , 10));

					sql = " SELECT SUM(VL_IRRF) as VL_IRRF, " +
					" SUM(VL_INSS_Prestador) as VL_INSS, " +
					" SUM(VL_INSS_Pago) as VL_INSS_Pago, " +
					" SUM(VL_Coleta) as VL_Coleta, " +
					" SUM(VL_Carga) as VL_Carga, " +
					" SUM(VL_Descarga) as VL_Descarga, " +
					" SUM(VL_Premio) as VL_Premio, " +
					" SUM(VL_Outros) as VL_Outros, " +
					" SUM(VL_Pedagio) as VL_Pedagio, " +
					" SUM(VL_Descontos) as VL_Descontos, " +
					" SUM(VL_Ordem_Frete) as VL_Ordem_Frete " +
					" from Ordens_Fretes, Unidades " +
					" WHERE Ordens_Fretes.oid_unidade = Unidades.oid_unidade " +
					" AND Ordens_Fretes.DT_Emissao >= '" + DT_Emissao_Ordem_Frete + "'" +
					" AND Ordens_Fretes.DT_Emissao <= '" + ed.getDT_Emissao () + "'";

					if ("S".equals(parametro_FixoED.getDM_Calcula_INSS_Para_Motorista ())){
						sql += " AND Ordens_Fretes.OID_Motorista = '" + ed.getOID_Motorista() + "'";
					}else{
						sql += " AND Ordens_Fretes.OID_Pessoa = '" + ed.getOID_Fornecedor() + "'";
					}

					sql += " AND Ordens_Fretes.DM_Frete = 'P' " +
					" AND Ordens_Fretes.DM_Impresso = 'S' ";
					if (util.doValida(ed.getOID_Ordem_Frete ())) {
						sql += " AND Ordens_Fretes.OID_Ordem_Frete <> " + JavaUtil.getSQLString(ed.getOID_Ordem_Frete());
					}

					res = this.executasql.executarConsulta (sql);

					VL_IRRF_Calculado = 0;
					ed.setVL_INSS_Empresa (0);
					ed.setVL_INSS_Prestador (0);
					ed.setVL_Set_Senat (0);
					ed.setVL_IRRF (0);

					while (res.next ()) {
						VL_IRRF_Acumulado = res.getDouble ("VL_IRRF");
						VL_INSS_Acumulado = res.getDouble ("VL_INSS") + res.getDouble ("VL_INSS_PAGO");
						VL_Ordem_Frete_Acumulado = res.getDouble ("VL_Ordem_Frete");

						if ("S".equals(parametro_FixoED.getDM_Soma_Coleta_Saldo_Frete ())) {
							VL_Ordem_Frete_Acumulado += res.getDouble ("VL_Coleta");
						}
						if ("S".equals(parametro_FixoED.getDM_Soma_Carga_Saldo_Frete ())) {
							VL_Ordem_Frete_Acumulado += res.getDouble ("VL_Carga");
						}
						if ("S".equals(parametro_FixoED.getDM_Soma_Descarga_Saldo_Frete ())) {
							VL_Ordem_Frete_Acumulado += res.getDouble ("VL_Descarga");
						}
						if ("S".equals(parametro_FixoED.getDM_Soma_Premio_Saldo_Frete ())) {
							VL_Ordem_Frete_Acumulado += res.getDouble ("VL_Premio");
						}
						if ("S".equals(parametro_FixoED.getDM_Soma_Descontos_Saldo_Frete ())) {
							VL_Ordem_Frete_Acumulado = VL_Ordem_Frete_Acumulado - res.getDouble ("VL_Descontos");
						}
						if ("S".equals(parametro_FixoED.getDM_Soma_Pedagio_Saldo_Frete ())) {
							VL_Ordem_Frete_Acumulado += res.getDouble ("VL_Pedagio");
						}
						if ("S".equals(parametro_FixoED.getDM_Soma_Outros_Saldo_Frete ())) {
							VL_Ordem_Frete_Acumulado += res.getDouble ("VL_Outros");
						}

					}
					VL_INSS_Acumulado = VL_INSS_Acumulado + ed.getVL_INSS_Pago ();
				}

				if (parametro_FixoED.getDM_Calcula_INSS ().equals ("S") && ed.getVL_Ordem_Frete() > 0.01) {

					ed = this.calcula_Saldo_Ordem_Frete (ed);


					if (VL_INSS_Acumulado < parametro_FixoED.getVL_Maximo_INSS ()) {
						VL_Base_Calculo = ed.getVL_Base_Calculo() * parametro_FixoED.getPE_Base_INSS ();
						ed.setVL_INSS_Prestador (VL_Base_Calculo * parametro_FixoED.getPE_Aliquota_Prestador_INSS ());
						ed.setVL_INSS_Empresa (VL_Base_Calculo * parametro_FixoED.getPE_Aliquota_Empresa_INSS ());
						if ( (ed.getVL_INSS_Prestador () + VL_INSS_Acumulado) > parametro_FixoED.getVL_Maximo_INSS ()) {
							ed.setVL_INSS_Prestador (parametro_FixoED.getVL_Maximo_INSS () - VL_INSS_Acumulado);
						}
						if (ed.getVL_INSS_Prestador () < 0) {
							ed.setVL_INSS_Prestador (0);
						}
					}
					else {
						VL_Base_Calculo = ed.getVL_Base_Calculo() *  parametro_FixoED.getPE_Base_INSS ();
						ed.setVL_INSS_Empresa (VL_Base_Calculo *  parametro_FixoED.getPE_Aliquota_Empresa_INSS ());
					}

					VL_Base_Calculo =ed.getVL_Base_Calculo() * parametro_FixoED.getPE_Base_SET_SENAT ();
					ed.setVL_Set_Senat (VL_Base_Calculo * parametro_FixoED.getPE_Aliquota_Prestador_Set_Senat ());
				}

				VL_Ordem_Frete_Acumulado = VL_Ordem_Frete_Acumulado + ed.getVL_Base_Calculo();

				//PE_Base_Calculo=0 NAO CALC IR
				if (parametro_FixoED.getDM_Calcula_IRRF ().equals ("S")) {

					if ("S".equals(parametro_FixoED.getDM_Calcula_INSS_Para_Motorista ())){
						sql = " SELECT NR_DEPENDENTES " +
						      " from Motoristas " +
						      " WHERE Motoristas.OID_Motorista = '" +  ed.getOID_Motorista() + "'";
					}else{
						sql = " SELECT NR_DEPENDENTES " +
						      " from Fornecedores " +
						      " WHERE Fornecedores.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
					}

					res = null;
					res = this.executasql.executarConsulta (sql);

					NR_DEPENDENTES = 0;
					while (res.next ()) {
						NR_DEPENDENTES = res.getLong ("NR_DEPENDENTES");
					}

					VL_Base_IRRF = ( (parametro_FixoED.getPE_Base_Calculo () *
							VL_Ordem_Frete_Acumulado) - ed.getVL_INSS_Prestador () -
							VL_INSS_Acumulado -
							(NR_DEPENDENTES * parametro_FixoED.getVL_Dependente ()));

					if (VL_Base_IRRF >= parametro_FixoED.getVL_Faixa1 () &&	VL_Base_IRRF < parametro_FixoED.getVL_Faixa2 ()) {
						VL_IRRF_Calculado = ( (VL_Base_IRRF * parametro_FixoED.getPE_Imposto_Faixa1 ()) - parametro_FixoED.getVL_Deducao_Faixa1 ());
					}else if (VL_Base_IRRF >= parametro_FixoED.getVL_Faixa2 () &&	VL_Base_IRRF < parametro_FixoED.getVL_Faixa3 ()) {
						VL_IRRF_Calculado = ( (VL_Base_IRRF * parametro_FixoED.getPE_Imposto_Faixa2 ()) - parametro_FixoED.getVL_Deducao_Faixa2 ());
					}else if (VL_Base_IRRF >= parametro_FixoED.getVL_Faixa3 () &&	VL_Base_IRRF < parametro_FixoED.getVL_Faixa4 ()) {
						VL_IRRF_Calculado = ( (VL_Base_IRRF * parametro_FixoED.getPE_Imposto_Faixa3 ()) - parametro_FixoED.getVL_Deducao_Faixa3 ());
					}else if (VL_Base_IRRF >= parametro_FixoED.getVL_Faixa4 ()) {
						VL_IRRF_Calculado = ( (VL_Base_IRRF * parametro_FixoED.getPE_Imposto_Faixa4 ()) - parametro_FixoED.getVL_Deducao_Faixa4 ());
					}

					VL_IRRF_Calculado = VL_IRRF_Calculado - VL_IRRF_Acumulado;


				}
				if (VL_IRRF_Calculado < 0) {
					VL_IRRF_Calculado = 0;
				}
			}
			ed.setVL_IRRF (VL_IRRF_Calculado);
			ed = this.calcula_Saldo_Ordem_Frete (ed);

			if (ed.getVL_Ordem_Frete()>ed.getVL_Adiantamento1()) {
				Ordem_FreteED edAdto = new Ordem_FreteED ();
				edAdto = this.consulta_Saldo_Adiantamento (ed);
				if (edAdto.getVL_Adiantamento1 () > 0 &&
						edAdto.getVL_Adiantamento1 () < ed.getVL_Liquido_Ordem_Frete ()) {
					ed.setVL_Adiantamento1 (edAdto.getVL_Adiantamento1 ());
					ed.setDM_Adiantamento ("V");
				}
			}

			if (ed.getDM_Adiantamento () != null && ed.getVL_Adiantamento1()<=0) {
				if (ed.getVL_Liquido_Ordem_Frete () > 0 &&
						ed.getDM_Adiantamento ().equals ("1")) {
					ed.setVL_Adiantamento1 (ed.getVL_Liquido_Ordem_Frete () * 10 / 100);
				}
				if (ed.getVL_Liquido_Ordem_Frete () > 0 &&
						ed.getDM_Adiantamento ().equals ("2")) {
					ed.setVL_Adiantamento1 (ed.getVL_Liquido_Ordem_Frete () * 20 / 100);
				}
				if (ed.getVL_Liquido_Ordem_Frete () > 0 &&
						ed.getDM_Adiantamento ().equals ("3")) {
					ed.setVL_Adiantamento1 (ed.getVL_Liquido_Ordem_Frete () * 30 / 100);
				}
				if (ed.getVL_Liquido_Ordem_Frete () > 0 &&
						ed.getDM_Adiantamento ().equals ("4")) {
					ed.setVL_Adiantamento1 (ed.getVL_Liquido_Ordem_Frete () * 40 / 100);
				}
				if (ed.getVL_Liquido_Ordem_Frete () > 0 &&
						ed.getDM_Adiantamento ().equals ("5")) {
					ed.setVL_Adiantamento1 (ed.getVL_Liquido_Ordem_Frete () * 50 / 100);
				}
				if (ed.getVL_Liquido_Ordem_Frete () > 0 &&
						ed.getDM_Adiantamento ().equals ("6")) {
					ed.setVL_Adiantamento1 (ed.getVL_Liquido_Ordem_Frete () * 60 / 100);
				}
				if (ed.getVL_Liquido_Ordem_Frete () > 0 &&
						ed.getDM_Adiantamento ().equals ("7")) {
					ed.setVL_Adiantamento1 (ed.getVL_Liquido_Ordem_Frete () * 70 / 100);
				}

			}

			ed = this.calcula_Saldo_Ordem_Frete (ed);

			//      ed.setVL_Saldo((ed.getVL_Liquido_Ordem_Frete()-ed.getVL_Adiantamento1()-ed.getVL_Adiantamento2()));

			edVolta.setVL_IRRF (ed.getVL_IRRF ());
			edVolta.setVL_Saldo (ed.getVL_Saldo ());
			edVolta.setVL_Coleta (ed.getVL_Coleta ());
			edVolta.setVL_Carga (ed.getVL_Carga ());
			edVolta.setVL_Descarga (ed.getVL_Descarga ());
			edVolta.setVL_Outros (ed.getVL_Outros ());
			edVolta.setVL_Premio (ed.getVL_Premio ());
			edVolta.setVL_Vale_Pedagio (ed.getVL_Vale_Pedagio ());
			edVolta.setVL_Vale_Pedagio_Empresa (ed.getVL_Vale_Pedagio_Empresa ());

			edVolta.setVL_Estadia (ed.getVL_Estadia ());
			edVolta.setVL_Reembolso (ed.getVL_Reembolso ());
			edVolta.setVL_Multa (ed.getVL_Multa ());

			edVolta.setVL_Total (ed.getVL_Total ());
			edVolta.setVL_Ordem_Frete (ed.getVL_Ordem_Frete ());
			edVolta.setVL_Descontos (ed.getVL_Descontos ());
			edVolta.setVL_Adiantamento1 (ed.getVL_Adiantamento1 ());
			edVolta.setVL_Adiantamento2 (ed.getVL_Adiantamento2 ());
			edVolta.setVL_Pedagio (ed.getVL_Pedagio ());
			edVolta.setVL_Set_Senat (ed.getVL_Set_Senat ());
			edVolta.setVL_Liquido_Ordem_Frete (ed.getVL_Liquido_Ordem_Frete ());
			edVolta.setVL_INSS_Prestador (ed.getVL_INSS_Prestador ());
			edVolta.setVL_INSS_Empresa (ed.getVL_INSS_Empresa ());
			edVolta.setVL_INSS_Pago (ed.getVL_INSS_Pago ());
			edVolta.setDM_Adiantamento (ed.getDM_Adiantamento ());

			if(edVolta.getVL_Liquido_Ordem_Frete()>4000.0D){
				throw new Excecoes("Valor l�quida da O.F. maior que 4.000,00...",this.getClass().getName(),"calcula_Ordem_Frete(Ordem_FreteED ed)");
			}

		} catch (Excecoes e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Excecoes (e.getMessage(), e, getClass().getName(), "calcula_Ordem_Frete (Ordem_FreteED ed)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Excecoes (e.getMessage(), e, getClass().getName(), "calcula_Ordem_Frete (Ordem_FreteED ed)");
		}
		return edVolta;
	}

	public Ordem_FreteED calcula_Saldo_Ordem_Frete (Ordem_FreteED ed) throws
	Excecoes {

		try {

			double vl_adiantamento=ed.getVL_Adiantamento1();

			ed.setVL_Total ( (ed.getVL_Ordem_Frete () +
					ed.getVL_Coleta () +
					ed.getVL_Carga () +
					ed.getVL_Descarga () +
					ed.getVL_Pedagio () +
					ed.getVL_Outros () +
					ed.getVL_Premio () -
					ed.getVL_Descontos ()));

			ed.setVL_Base_Calculo (ed.getVL_Ordem_Frete ());
			ed.setVL_Liquido_Ordem_Frete (ed.getVL_Ordem_Frete ());

			if ("S".equals(parametro_FixoED.getDM_Soma_Coleta_Saldo_Frete ())) {
				ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () + ed.getVL_Coleta ());
			}
			if ("S".equals(parametro_FixoED.getDM_Soma_Carga_Saldo_Frete ())) {
				ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () + ed.getVL_Carga ());
			}
			if ("S".equals(parametro_FixoED.getDM_Soma_Descarga_Saldo_Frete ())) {
				ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () + ed.getVL_Descarga ());
			}
			if ("S".equals(parametro_FixoED.getDM_Soma_Premio_Saldo_Frete ())) {
				ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () + ed.getVL_Premio ());
			}
			if ("S".equals(parametro_FixoED.getDM_Soma_Descontos_Saldo_Frete ())) {
				ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () - ed.getVL_Descontos ());
			}
			if (parametro_FixoED.getDM_Soma_Pedagio_Saldo_Frete ().equals ("S")) {
				ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () + ed.getVL_Pedagio ());
			}
			if (parametro_FixoED.getDM_Soma_Outros_Saldo_Frete ().equals ("S")) {
				ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () + ed.getVL_Outros ());
			}



			if (parametro_FixoED.getDM_Soma_IRRF_Saldo_Frete ().equals ("S")) {
				ed.setVL_Liquido_Ordem_Frete ( (ed.getVL_Liquido_Ordem_Frete () -ed.getVL_IRRF()));
			}

			if (parametro_FixoED.getDM_Soma_INSS_Prestador_Saldo_Frete ().equals ("S")) {
				ed.setVL_Liquido_Ordem_Frete ( (ed.getVL_Liquido_Ordem_Frete () -ed.getVL_INSS_Prestador()));
			}

			if (parametro_FixoED.getDM_Soma_Sest_Senat_Saldo_Frete ().equals ("S")) {
				ed.setVL_Liquido_Ordem_Frete ( (ed.getVL_Liquido_Ordem_Frete () -ed.getVL_Set_Senat()));
			}

			ed.setVL_Liquido_Ordem_Frete (ed.getVL_Liquido_Ordem_Frete () + ed.getVL_Coleta ());
			ed.setVL_Liquido_Ordem_Frete (ed.getVL_Liquido_Ordem_Frete () + ed.getVL_Carga ());
			ed.setVL_Liquido_Ordem_Frete (ed.getVL_Liquido_Ordem_Frete () + ed.getVL_Descarga ());
			ed.setVL_Liquido_Ordem_Frete (ed.getVL_Liquido_Ordem_Frete () + ed.getVL_Premio ());
			ed.setVL_Liquido_Ordem_Frete (ed.getVL_Liquido_Ordem_Frete () - ed.getVL_Descontos ());
			ed.setVL_Liquido_Ordem_Frete (ed.getVL_Liquido_Ordem_Frete () + ed.getVL_Pedagio ());
			ed.setVL_Liquido_Ordem_Frete (ed.getVL_Liquido_Ordem_Frete () + ed.getVL_Outros ());

			ed.setVL_Saldo ( (ed.getVL_Liquido_Ordem_Frete () - ed.getVL_Adiantamento1 () - ed.getVL_Adiantamento2 ()));

			if (ed.getVL_Saldo () < 0.01) {
				if (ed.getVL_Adiantamento1 () > 0) {
					ed.setVL_Adiantamento1 (ed.getVL_Adiantamento1 () + ed.getVL_Saldo ());
				}
				else if (ed.getVL_Adiantamento2 () > 0) {
					ed.setVL_Adiantamento2 (ed.getVL_Adiantamento2 () + ed.getVL_Saldo ());
				}
				else {
					ed.setVL_Ordem_Frete (ed.getVL_Ordem_Frete () + ed.getVL_Saldo ());
				}
				ed.setVL_Saldo (0);
			}
			if (ed.getVL_Adiantamento1 () < 0 || ed.getVL_Adiantamento2 () < 0 ||
					ed.getVL_Ordem_Frete () < 0) {
				ed.setVL_Adiantamento1 (0);
				ed.setVL_Adiantamento2 (0);
				ed.setVL_Liquido_Ordem_Frete ( (ed.getVL_Total () -
						ed.getVL_IRRF () -
						ed.getVL_INSS_Prestador ()));

				ed.setVL_Saldo ( (ed.getVL_Liquido_Ordem_Frete () -
						ed.getVL_Adiantamento1 () -
						ed.getVL_Adiantamento2 ()));
				if (ed.getVL_Saldo () < 0) {
					ed.setVL_Adiantamento1 (0);
					ed.setVL_Adiantamento2 (0);
					ed.setVL_Saldo (0);
					ed.setVL_Descontos (0);
				}
			}

			if(ed.getVL_Liquido_Ordem_Frete()>4000.0D){
				throw new Excecoes("Valor l�quida da O.F. maior que 4.000,00...",this.getClass().getName(),"calcula_Saldo_Ordem_Frete(Ordem_FreteED ed)");
			}

			if (vl_adiantamento != ed.getVL_Adiantamento1())
				ed.setDM_Adiantamento("I");

		}
		catch(Excecoes e){
			throw e;
		}
		catch (Exception exc) {
			exc.printStackTrace();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao Calcular Saldo Ordem Frete");
			excecoes.setMetodo ("calcula_Saldo_Ordem_Frete(Ordem_FreteED ed)");
			excecoes.setExc (exc);
			throw excecoes;
		}

		return ed;
	}

	public ArrayList lista (Ordem_FreteED ed) throws Excecoes {

		String sql = null;
		ArrayList list = new ArrayList ();

		try {

			sql = "SELECT Ordens_Fretes.*, Ordens_Fretes.DM_Situacao as DM_Situacao_OF,  " +
			"Pessoas.NM_Fantasia " +
			"FROM Ordens_Fretes, Unidades, Pessoas " +
			"WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade " +
			"AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa ";

			if ("A".equals(ed.getDM_Frete ())){
				sql += " and Ordens_Fretes.DM_Frete = 'A' ";
			}else {
				if ("T".equals(ed.getDM_Frete ())){
					sql += " and (Ordens_Fretes.DM_Frete = 'A' OR Ordens_Fretes.DM_Frete = 'P' )";
				}else {
					sql += " and Ordens_Fretes.DM_Frete = 'P' ";
				}
			}
			if ("C".equals(ed.getDM_Situacao ())){
				sql += " and Ordens_Fretes.DM_Situacao = 'C' ";
			}
			if ("N".equals(ed.getDM_Situacao ())){
				sql += " and Ordens_Fretes.DM_Situacao <> 'N' ";
			}


			if (String.valueOf (ed.getDT_Emissao ()) != null &&
					!String.valueOf (ed.getDT_Emissao ()).equals ("") &&
					!String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
				sql += " and Ordens_Fretes.DT_Emissao >= '" + ed.getDT_Emissao () + "'";
			}
			if (String.valueOf (ed.getDt_Emissao_Final()) != null &&
					!String.valueOf (ed.getDt_Emissao_Final()).equals ("") &&
					!String.valueOf (ed.getDt_Emissao_Final()).equals ("null")) {
				sql += " and Ordens_Fretes.DT_Emissao <= '" + ed.getDt_Emissao_Final() + "'";
			}


			if (String.valueOf (ed.getNR_Placa ()) != null &&
					!String.valueOf (ed.getNR_Placa ()).equals ("") &&
					!String.valueOf (ed.getNR_Placa ()).equals ("null")) {
				sql += " and Ordens_Fretes.oid_Veiculo = '" + ed.getNR_Placa () + "'";
			}

			if (String.valueOf (ed.getNR_Ordem_Frete ()) != null &&
					!String.valueOf (ed.getNR_Ordem_Frete ()).equals ("0") &&
					!String.valueOf (ed.getNR_Ordem_Frete ()).equals ("null")) {
				sql += " and Ordens_Fretes.NR_Ordem_Frete = " + ed.getNR_Ordem_Frete ();
			}

			if (String.valueOf (ed.getOID_Unidade ()) != null &&
					!String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
					!String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
				sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
			}

			//			 Alteracao GM - consulta pelo nr_acerto ou s/ acerto
			if (String.valueOf (ed.getNR_Acerto ()) != null &&
					!String.valueOf (ed.getNR_Acerto ()).equals ("0") &&
					!String.valueOf (ed.getNR_Acerto ()).equals ("null")) {
				sql += " and ordens_fretes.oid_acerto = acertos.oid_acerto ";
				sql += " and acertos.nr_acerto = " + ed.getNR_Acerto ();
			}
			if (ed.getDm_Stamp () != null && ed.getDm_Stamp ().equals ("N")) {
				sql += " and ordens_fretes.oid_acerto is null ";
			}

			if (util.doValida(ed.getOID_Motorista())) {
				sql += " and Ordens_Fretes.oid_motorista = '" + ed.getOID_Motorista() + "'";
			}

			if (util.doValida(ed.getOID_Pessoa())) {
				sql += " and Ordens_Fretes.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
			}
			if (util.doValida(ed.getOID_Fornecedor())) {
				sql += " and Ordens_Fretes.oid_Fornecedor = '" + ed.getOID_Fornecedor() + "'";
			}

			sql +=
				" Order by Ordens_Fretes.DM_Impresso,Ordens_Fretes.NR_Ordem_Frete ";


			ResultSet res = null;
			res = this.executasql.executarConsulta (sql);


			//popula
			while (res.next ()) {


				Ordem_FreteED edVolta = new Ordem_FreteED ();

				edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));

				edVolta.setNR_Placa (res.getString ("OID_VEICULO"));
				edVolta.setDM_Frete (res.getString ("DM_Frete"));

				edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
				DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
				edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

				edVolta.setNR_Ordem_Frete (res.getLong ("NR_Ordem_Frete"));
				edVolta.setVL_Ordem_Frete (res.getDouble ("VL_Ordem_Frete"));
				edVolta.setNM_Unidade (res.getString ("NM_Fantasia"));

				edVolta.setDM_Situacao(res.getString ("DM_Situacao_OF"));
				edVolta.setDM_Impresso(res.getString ("DM_Impresso"));
				edVolta = this.consultaSituacao(edVolta);

				if (res.getString ("DM_Impresso") != null) {
					if (res.getString ("DM_Impresso").equals ("S")) {
						edVolta.setDM_Impresso ("Sim");
					}
					else {
						edVolta.setDM_Impresso ("N�o");
					}
				}


				list.add (edVolta);
			}
		}
		catch (Exception e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
					"lista(Ordem_FreteED ed)");
		}

		return list;
	}

	public ArrayList lista_Adiantamentos_Manifestos (Ordem_FreteED ed) throws
	Excecoes {
		ArrayList list = new ArrayList ();
		try {
			String sql =
				" SELECT Ordens_Fretes.DM_Impresso, " +
				"        Ordens_Fretes.OID_Ordem_Frete, " +
				"        Ordens_Fretes.OID_VEICULO, " +
				"        Ordens_Fretes.OID_Acerto, " +
				"        Ordens_Fretes.DM_Situacao, " +
				"        Motorista.NM_Razao_Social, " +
				"        Ordens_Fretes.DT_Emissao, " +
				"        Ordens_Fretes.NR_Ordem_Frete, " +
				"        Ordens_Fretes.VL_Ordem_Frete, " +
				"        Ordens_Fretes.DM_Tipo_Adiantamento, " +
				"        Pessoas.NM_Fantasia " +
				" from Ordens_Fretes, Unidades, Pessoas, Pessoas Motorista " +
				" WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade " +
				"   AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa " +
				"   AND Ordens_Fretes.OID_Motorista = Motorista.OID_Pessoa " ;
			//"   AND (Ordens_Fretes.OID_Ordem_Principal is null OR Ordens_Fretes.OID_Ordem_Principal = '') ";

			if (String.valueOf (ed.getDT_Emissao ()) != null &&
					!String.valueOf (ed.getDT_Emissao ()).equals ("") &&
					!String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
				sql += " and Ordens_Fretes.DT_Emissao = '" + ed.getDT_Emissao () + "'";
			}
			if (String.valueOf (ed.getDt_Emissao_Inicial ()) != null &&
					!String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("") &&
					!String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("null")) {
				sql += " and Ordens_Fretes.DT_Emissao >= '" + ed.getDt_Emissao_Inicial () +
				"'";
			}
			if (String.valueOf (ed.getDt_Emissao_Final ()) != null &&
					!String.valueOf (ed.getDt_Emissao_Final ()).equals ("") &&
					!String.valueOf (ed.getDt_Emissao_Final ()).equals ("null")) {
				sql += " and Ordens_Fretes.DT_Emissao <= '" + ed.getDt_Emissao_Final () +
				"'";
			}

			if (String.valueOf (ed.getDM_Frete ()) != null &&
					!String.valueOf (ed.getDM_Frete ()).equals ("") &&
					String.valueOf (ed.getDM_Frete ()).equals ("P")) {
				sql += " and Ordens_Fretes.DM_Frete = 'P' ";
			}
			if (String.valueOf (ed.getDM_Frete ()) != null &&
					!String.valueOf (ed.getDM_Frete ()).equals ("") &&
					String.valueOf (ed.getDM_Frete ()).equals ("A")) {
				sql += " and Ordens_Fretes.DM_Frete = 'A' ";
			}

			if (String.valueOf (ed.getNR_Placa ()) != null &&
					!String.valueOf (ed.getNR_Placa ()).equals ("") &&
					!String.valueOf (ed.getNR_Placa ()).equals ("null")) {
				sql += " and Ordens_Fretes.oid_Veiculo = '" + ed.getNR_Placa () + "'";
			}

			if (String.valueOf (ed.getNR_Ordem_Frete ()) != null &&
					!String.valueOf (ed.getNR_Ordem_Frete ()).equals ("0") &&
					!String.valueOf (ed.getNR_Ordem_Frete ()).equals ("null")) {
				sql += " and Ordens_Fretes.NR_Ordem_Frete = " + ed.getNR_Ordem_Frete ();
			}

			if (String.valueOf (ed.getOID_Unidade ()) != null &&
					!String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
					!String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
				sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
			}

			if ("A".equals (ed.getDM_Acerto ())) {
				sql += " and Ordens_Fretes.oid_Acerto is not null ";
			}
			if ("P".equals (ed.getDM_Acerto ())) {
				sql += " and Ordens_Fretes.oid_Acerto is null ";
			}

			if (util.doValida(ed.getOID_Motorista())) {
				sql += " and Ordens_Fretes.oid_motorista = '" + ed.getOID_Motorista() + "'";
			}

			if (util.doValida(ed.getOID_Pessoa())) {
				sql += " and Ordens_Fretes.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
			}
			if (util.doValida(ed.getDM_Tipo_Adiantamento())) {
				sql += " and Ordens_Fretes.DM_Tipo_Adiantamento = " + util.getSQLString(ed.getDM_Tipo_Adiantamento());
			}

			sql +=
				" Order by Ordens_Fretes.DM_Impresso,Ordens_Fretes.NR_Ordem_Frete ";


			ResultSet res = this.executasql.executarConsulta (sql);


			//popula
			while (res.next ()) {

				Ordem_FreteED edVolta = new Ordem_FreteED ();

				edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));

				edVolta.setNR_Placa (res.getString ("OID_VEICULO"));

				edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
				DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
				edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

				edVolta.setNR_Ordem_Frete (res.getLong ("NR_Ordem_Frete"));
				edVolta.setVL_Ordem_Frete (res.getDouble ("VL_Ordem_Frete"));
				edVolta.setNM_Unidade (res.getString ("NM_Fantasia"));

				edVolta.setNM_Razao_Social(res.getString ("NM_Razao_Social"));

				if (res.getString ("DM_Impresso") != null) {
					if (res.getString ("DM_Impresso").equals ("S")) {
						edVolta.setDM_Impresso ("Sim");
					}
					else {
						edVolta.setDM_Impresso ("N�o");
					}
				}
				edVolta.setDM_Tipo_Adiantamento(res.getString("DM_Tipo_Adiantamento"));

				if (res.getLong ("OID_Acerto")>0){
					ResultSet res2 = null;
					sql = " SELECT Acertos.NR_Acerto, Acertos.OID_Acerto FROM Acertos  " +
					" WHERE Acertos.oid_Acerto = " + res.getLong ("OID_Acerto") ;
					res2 = this.executasql.executarConsulta (sql);
					if (res2.next ()) {
						edVolta.setNR_Acerto(res2.getLong("NR_Acerto"));
						edVolta.setOID_Acerto(res2.getLong("OID_Acerto"));
					}
				}
				edVolta.setNM_Situacao("---");
				if ("C".equals(res.getString("DM_Situacao")) || "C".equals(res.getString("DM_Impresso"))){
					edVolta.setNM_Situacao("Cancelado");
				}
				list.add (edVolta);
			}
			return list;
		} catch (SQLException e) {
			throw new Excecoes (e.getMessage(), e, this.getClass().getName(), "lista(Ordem_FreteED ed)");
		}
	}

	public ArrayList lista_Adiantamento_Vinculado (Ordem_FreteED ed) throws
	Excecoes {


		String sql = null;
		ArrayList list = new ArrayList ();
		int i = 0;
		try {

			sql = " SELECT OID_Ordem_Principal, " +
					    " Unidades.CD_Unidade, " +
					    " Pessoas.NM_Razao_Social, " +
					    " DM_Tipo_Pagamento, " +
					    " DM_Impresso, " +
					    " Ordens_Fretes.DM_Situacao, " +
					    " OID_Ordem_Frete, " +
					    " OID_Ordem_Principal, " +
					    " OID_VEICULO, " +
					    " DT_Emissao, " +
					    " NR_Ordem_Frete, " +
					    " VL_Ordem_Frete, " +
					    " DT_Pagamento, " +
					    " NR_Cheque_Adto, " +
					    " Pessoas.NM_Fantasia " +
			       " FROM Ordens_Fretes, Unidades, Pessoas ";
			sql += " WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade ";
			sql += " AND Ordens_Fretes.OID_Pessoa = Pessoas.OID_Pessoa ";

			if (String.valueOf (ed.getOID_Ordem_Principal ()) != null &&
					!String.valueOf (ed.getOID_Ordem_Principal ()).equals ("") &&
					!String.valueOf (ed.getOID_Ordem_Principal ()).equals ("0") &&
					!String.valueOf (ed.getOID_Ordem_Principal ()).equals ("null")) {
				sql += " and Ordens_Fretes.oid_Ordem_Principal = '" +
				ed.getOID_Ordem_Principal () + "'";
			}

			sql += " Order by Ordens_Fretes.NR_Ordem_Frete ";


			ResultSet res = null;
			res = this.executasql.executarConsulta (sql);


			//popula
			while (res.next ()) {


				Ordem_FreteED edVolta = new Ordem_FreteED ();
				i++;
				edVolta.setNR_Adiantamento (i);
				edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));
				edVolta.setOID_Ordem_Principal (res.getString ("OID_Ordem_Principal"));

				edVolta.setNR_Placa (res.getString ("OID_VEICULO"));
				edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

				edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
				DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
				edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

				edVolta.setNR_Ordem_Frete (res.getLong ("NR_Ordem_Frete"));
				edVolta.setVL_Ordem_Frete (res.getDouble ("VL_Ordem_Frete"));
				edVolta.setNM_Unidade (res.getString ("CD_Unidade"));
				edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));
				if (res.getDate ("DT_Pagamento")!=null){
					edVolta.setDT_Pagamento (FormataData.formataDataBT (res.getDate ("DT_Pagamento")));
				}else{
					edVolta.setDT_Pagamento ("   -----   ");
				}
				if (res.getString ("NR_Cheque_Adto") != null){
					edVolta.setNR_Cheque_Adto(res.getString ("NR_Cheque_Adto"));
				}else{
					edVolta.setNR_Cheque_Adto("   -----   ");
				}

				if (res.getString ("DM_Impresso") != null) {
					if (res.getString ("DM_Impresso").equals ("S")) {
						edVolta.setDM_Impresso ("Sim");
					}
					else {
						edVolta.setDM_Impresso ("N�o");
					}
				}
				edVolta.setDM_Tipo_Pagamento ("");

				edVolta = this.consultaSituacao(edVolta);


				if (res.getString ("DM_Tipo_Pagamento") != null) {
					if (res.getString ("DM_Tipo_Pagamento").equals ("A")) {
						edVolta.setDM_Tipo_Pagamento ("Abastecimento");
					}
					else {
						edVolta.setDM_Tipo_Pagamento ("Dinheiro");
					}
				}


				list.add (edVolta);
			}
		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao listar Ordem Frete");
			excecoes.setMetodo ("lista");
			excecoes.setExc (exc);
			throw excecoes;
		}

		return list;
	}

	public void deleta_acerto (Ordem_FreteED ed) throws Excecoes {

		String sql = null;

		try {

			sql = " Update Conhecimentos set oid_Acerto = null " +
            	  " WHERE OID_Acerto = " + ed.getOID_Acerto();

	        executasql.executarUpdate(sql);

		   sql = " update Ordens_Fretes set oid_acerto = null ";
		   sql += " where oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

		   executasql.executarUpdate (sql);

		   this.ajusta_acerto(ed);

		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao excluir do acerto");
			excecoes.setMetodo ("deleta acerto");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	public void ajusta_acerto (Ordem_FreteED ed) throws Excecoes {

		String sql = null;
		boolean DM_Movimento_Compromisso = false;
		ResultSet res = null;
		ResultSet resLocal = null;

		try {

             double vl_base_comissao_acerto=0, pe_comissao_acerto=0, pe_base_comissao_motorista=0, vl_comissao=0;

             sql = " SELECT sum(vl_total_frete_ctrc) as vl_total_frete_ctrc " +
                   " FROM  Ordens_Fretes " +
                   " WHERE OID_Acerto = " + ed.getOID_Acerto();

             res = this.executasql.executarConsulta (sql);
             while (res.next ()) {

               vl_base_comissao_acerto=res.getDouble("vl_total_frete_ctrc");
               pe_comissao_acerto=parametro_FixoED.getPE_Base_Comissao_Motorista();
               pe_base_comissao_motorista=parametro_FixoED.getPE_Base_Comissao_Motorista();

               if (pe_base_comissao_motorista==0){
                 pe_base_comissao_motorista=100;
               }

                 if ("TF".equals(parametro_FixoED.getDM_Base_Comissao_Motorista())){
                	 vl_comissao=res.getDouble("vl_total_frete_ctrc")*pe_base_comissao_motorista/100;
                 }

                 if ("FP".equals(parametro_FixoED.getDM_Base_Comissao_Motorista())){
                	 vl_comissao=res.getDouble("vl_total_frete_ctrc")*pe_base_comissao_motorista/100;
                 }
                 sql=  " UPDATE Acertos SET vl_base_comissao = " + vl_base_comissao_acerto +
                 	   ", vl_comissao= " + vl_comissao +
                 	   ", pe_base_comissao= " + pe_base_comissao_motorista +
                       " WHERE OID_Acerto = " + ed.getOID_Acerto();
                 executasql.executarUpdate(sql);

             }

      	     sql = " select sum(vl_total_frete_ctrc) as vl_tot, oid_ordem_frete " +
	    	       " from ordens_fretes " +
	    		   " where ordens_fretes.oid_acerto = " + ed.getOID_Acerto() +
	    		   " group by ordens_fretes.oid_ordem_frete ";

      	    res = this.executasql.executarConsulta(sql);

	        double VL_Frete_Proprio = 0;
	        double VL_Frete_Terceiro = 0;

	        while (res.next()) {

	      	  sql = " select conhecimentos.dm_tipo_documento " +
	    	       " from ordens_manifestos, manifestos, viagens, conhecimentos  " +
	    		   " where ordens_manifestos.oid_manifesto = manifestos.oid_manifesto " +
	    		   " and manifestos.oid_manifesto = viagens.oid_manifesto" +
	    		   " and viagens.oid_conhecimento = conhecimentos.oid_conhecimento " +
	    		   " and ordens_manifestos.oid_ordem_frete = '" + res.getString("oid_ordem_frete") + "'" +
	    		   " group by conhecimentos.dm_tipo_documento " ;

	      	  resLocal = this.executasql.executarConsulta(sql);

	      	  String dm_tipo_documento = "";
	          while (resLocal.next()) {
	        	  dm_tipo_documento = resLocal.getString("dm_tipo_documento");
	          }
	          if ("C".equals(dm_tipo_documento)){
		     	 VL_Frete_Proprio = VL_Frete_Proprio + res.getDouble("vl_tot");
		      }else{
				 VL_Frete_Terceiro = VL_Frete_Terceiro + res.getDouble("vl_tot");
		      }

	        }

			sql = " UPDATE Acertos " +
			   " SET   vl_frete_proprio =  " + VL_Frete_Proprio +
			   "       ,vl_frete_terceiro =  " + VL_Frete_Terceiro +
			   " WHERE OID_Acerto = " + ed.getOID_Acerto();
			executasql.executarUpdate(sql);

		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao excluir do acerto");
			excecoes.setMetodo ("deleta acerto");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}


	public void Libera_Saldo (Ordem_FreteED ed) throws Excecoes {

		String sql = null;
		boolean DM_Movimento_Compromisso = false;

		try {

			sql = " update Ordens_Fretes set DM_Saldo_Liberado = 'S' ";
			sql += " where oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

			int i = executasql.executarUpdate (sql);
		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao Liberar Saldo");
			excecoes.setMetodo ("Libera_Saldo");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	public Ordem_FreteED inclui_adiantamento (Ordem_FreteED ed) throws Excecoes {
		String sql = null;
		long valOid = 0;
		String chave = null;
		long NR_Proximo = 0;
		long NR_Final = 0;
		try {
			if (ed.getNR_Ordem_Frete () == 0) {
				sql =
					" SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
				sql += " FROM AIDOF ";
				sql += " WHERE AIDOF.NM_Serie = 'OFA' ";
				ResultSet rs = this.executasql.executarConsulta (sql);

				while (rs.next ()) {
					ed.setNM_Serie (rs.getString ("NM_Serie"));
					ed.setNR_Ordem_Frete (rs.getLong ("NR_Proximo"));
					valOid = rs.getLong ("OID_AIDOF");
					NR_Proximo = rs.getLong ("NR_Proximo") + 1;
					NR_Final = rs.getLong ("NR_FINAL");
				}
				if (NR_Proximo > NR_Final) {
					throw new Excecoes ("AIDOF sem numera��o dispon�vel" ,
							this.getClass ().getName () ,
					"inclui_adiantamento(Ordem_FreteED ed)");
				}

				sql = " UPDATE AIDOF SET NR_Proximo= " + (ed.getNR_Ordem_Frete () + 1);
				sql += " WHERE OID_AIDOF = " + valOid;
				int u = executasql.executarUpdate (sql);
			}

			chave = String.valueOf (ed.getOID_Unidade ()) +
			String.valueOf (ed.getNR_Ordem_Frete ()) + ed.getNM_Serie ();
			if (ed.getOID_Ordem_Principal () != null &&
					!ed.getOID_Ordem_Principal ().equals ("null") &&
					!ed.getOID_Ordem_Principal ().equals ("")) {
				chave = String.valueOf (System.currentTimeMillis ());
			}
			ed.setOID_Ordem_Frete (chave);

			ed.setVL_Adiantamento1(ed.getVL_Ordem_Frete());
			double VL_Ordem_Frete_Convertida =0;
			if (ed.getVL_Cotacao () > 0 &&
					ed.getVL_Cotacao_Padrao () > 0) {
				VL_Ordem_Frete_Convertida =ed.getVL_Ordem_Frete();
				ed.setVL_Adiantamento1(0);
				if (ed.getVL_Cotacao () != ed.getVL_Cotacao_Padrao () ) {
					VL_Ordem_Frete_Convertida = Valor.round (ed.getVL_Ordem_Frete () / ed.getVL_Cotacao () * ed.getVL_Cotacao_Padrao () , 2);
				}
				ed.setVL_Adiantamento1(VL_Ordem_Frete_Convertida);

			}

			sql = " insert into Ordens_Fretes (" +
					" OID_Ordem_Frete, " +
					" OID_Pessoa, " +
					" DM_Impresso, " +
					" TX_Observacao, " +
					" DT_Emissao, " +
					" OID_Unidade, " +
					" NR_Ordem_Frete, " +
					" DM_Frete, " +
					" VL_Ordem_Frete, " +
					" oid_Veiculo, " +
					" DT_Saldo,  " +
					" DM_Tipo_Pagamento,  " +
					" NR_Transacao_Pedagio,  " +
					" DT_Adiantamento1,  " +
					" OID_Ordem_Principal,  " +
					" VL_Adiantamento1,  " +
					" VL_Cotacao,  " +
					" VL_Cotacao_Padrao,  " +
					" VL_Ordem_Frete_Convertida,  " +
					" VL_Saldo, " +
					" VL_Vale_Pedagio, " +
					" VL_Vale_Pedagio_Empresa, " +
					" VL_Outros, " +
					" VL_Coleta, " +
					" VL_Carga, " +
					" VL_Descarga, " +
					" VL_Total_Frete_CTRC," +
					" OID_Motorista, " +
					" OID_Fornecedor," +
					" OID_Local_Pagamento," +
					" OID_Unidade_Adiantamento1," +
					" OID_Usuario," +
					" DM_Tipo_Adiantamento," +
					" DT_Pagamento," +
					" NR_Cheque_Adto ) values ";
			sql += "('" + ed.getOID_Ordem_Frete () +
			"','" + ed.getOID_Pessoa () +
			"','" + ed.getDM_Impresso () +
			"','" + ed.getTX_Observacao () +
			"','" + ed.getDT_Emissao () +
			"'," + ed.getOID_Unidade () +
			"," + ed.getNR_Ordem_Frete () +
			",'" + ed.getDM_Frete () +
			"'," + ed.getVL_Ordem_Frete () +
			",'" + ed.getOID_Veiculo () +
			"','" + ed.getDT_Saldo () +
			"','" + ed.getDM_Tipo_Pagamento () +
			"','" + ed.getNR_Transacao_Pedagio () +
			"','" + ed.getDT_Adiantamento1 () +
			"','" + ed.getOID_Ordem_Principal () +
			"'," + ed.getVL_Adiantamento1 () +
			"," + ed.getVL_Cotacao () +
			"," + ed.getVL_Cotacao_Padrao () +
			"," + VL_Ordem_Frete_Convertida +
			",0,";
			sql += ed.getVL_Vale_Pedagio () + ",";
			sql += ed.getVL_Vale_Pedagio_Empresa () + ",";
			sql += ed.getVL_Outros () + ",";
			sql += ed.getVL_Coleta () + ",";
			sql += ed.getVL_Carga () + ",";
			sql += ed.getVL_Descarga () + ",";
			sql += ed.getVL_Total_Frete_CTRC () + ",";
			sql +=  "'" + ed.getOID_Motorista () + "' " +
			",'" + ed.getOID_Fornecedor () + "' " +
			",'" + ed.getOID_Local_Pagamento () + "' " +
			"," + ed.getOID_Unidade_Adiantamento1 () +
			"," + ed.getOID_Usuario () +
			"," + util.getSQLString(ed.getDM_Tipo_Adiantamento()) +
			"," + (util.doValida(ed.getDT_Pagamento ()) ? "'" + ed.getDT_Pagamento ()+ "'" : null) +
			",'" + ed.getNR_Cheque_Adto() +"')";

			int i = executasql.executarUpdate (sql);

			if (ed.getOID_Manifesto () != null && !ed.getOID_Manifesto ().equals ("") &&
					!ed.getOID_Manifesto ().equals ("null")) {
				chave = (ed.getOID_Manifesto () + ed.getOID_Ordem_Frete ());
				sql = " insert into Ordens_Manifestos (OID_Ordem_Manifesto, OID_Manifesto, OID_Ordem_Frete ) values ";
				sql += "('" + chave + "','" + ed.getOID_Manifesto () + "','" +
				ed.getOID_Ordem_Frete () + "')";
				i = executasql.executarUpdate (sql);
			}

			if (ed.getOID_MIC() != null && !ed.getOID_MIC().equals ("") &&
					!ed.getOID_MIC().equals ("null")) {
				chave = (ed.getOID_MIC() + ed.getOID_Ordem_Frete ());
				sql = " insert into Ordens_MIC (OID_Ordem_MIC, OID_Manifesto_Internacional, OID_Ordem_Frete, DT_Ordem_MIC, HR_Ordem_MIC ) values ";
				sql += "('" + chave + "','" + ed.getOID_MIC() + "','" +
				ed.getOID_Ordem_Frete () + "','" + Data.getDataDMY() + "','" + Data.getHoraHM() + "')";
				i = executasql.executarUpdate (sql);
			}

			if (ed.getOID_Ordem_Principal () != null &&
					!ed.getOID_Ordem_Principal ().equals ("null") &&
					!ed.getOID_Ordem_Principal ().equals ("")) {
				//this.acerta_Saldo_Adiantamento(ed);
				sql = " Select vl_adiantamento1, vl_adiantamento2, vl_saldo FROM Ordens_Fretes WHERE oid_Ordem_Frete = '" +
				ed.getOID_Ordem_Principal () + "'";
				ResultSet res = this.executasql.executarConsulta (sql);

				double vl_adiantamento1 = 0;
				double vl_adiantamento2 = 0;
				double vl_saldo = ed.getVL_Saldo ();
				if (res.next ()) {
					vl_adiantamento1 = res.getDouble ("vl_adiantamento1");
					vl_adiantamento2 = res.getDouble ("vl_adiantamento2");
					vl_saldo = res.getDouble ("vl_saldo");
				}
				if (ed.getDM_Tipo_Pagamento () != null &&
						ed.getDM_Tipo_Pagamento ().equals ("D") || ed.getDM_Tipo_Pagamento ().equals ("P")) {
					vl_adiantamento1 += ed.getVL_Ordem_Frete ();
				}
				else {
					vl_adiantamento2 += ed.getVL_Ordem_Frete ();
				}
				vl_saldo = vl_saldo - ed.getVL_Ordem_Frete ();

				sql = " UPDATE Ordens_Fretes set  vl_adiantamento1= " +
				vl_adiantamento1 + ", vl_adiantamento2=" + vl_adiantamento2 +
				", vl_saldo=" + vl_saldo +
				" WHERE oid_Ordem_Frete = '" + ed.getOID_Ordem_Principal () + "'";
				i = executasql.executarUpdate (sql);
			}
			Ordem_FreteED ordem_FreteED = new Ordem_FreteED ();
			ordem_FreteED.setOID_Ordem_Frete (ed.getOID_Ordem_Frete ());
			return ordem_FreteED;
		}
		catch (Excecoes e) {
			throw e;
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
					"inclui_adiantamento(Ordem_FreteED ed)");
		}
	}

	public void altera_Adiantamento (Ordem_FreteED ed) throws Excecoes {
		String sql = null;

		boolean DM_Movimento_Compromisso = false;
		double vl_adiantamento1 = 0 , vl_adiantamento2 = 0 , vl_saldo = ed.getVL_Saldo ();
		double vl_ordem_frete = 0;
		double VL_Ordem_Frete_Convertida =0;
		String DM_Tipo_Pagamento = "D";
		int i = 0;

		try {
			ed.setVL_Adiantamento1(ed.getVL_Ordem_Frete());
			if (ed.getVL_Cotacao () > 0 &&
					ed.getVL_Cotacao_Padrao () > 0) {
				VL_Ordem_Frete_Convertida = ed.getVL_Ordem_Frete ();
				ed.setVL_Adiantamento1 (0);
				vl_saldo= 0;
				if (ed.getVL_Cotacao () != ed.getVL_Cotacao_Padrao ()) {
					VL_Ordem_Frete_Convertida = Valor.round (ed.getVL_Ordem_Frete () /
							ed.getVL_Cotacao () *
							ed.getVL_Cotacao_Padrao () , 2);
				}
				ed.setVL_Adiantamento1 (VL_Ordem_Frete_Convertida);

			}

			if (ed.getOID_Ordem_Principal () != null &&
					!ed.getOID_Ordem_Principal ().equals ("") &&
					!ed.getOID_Ordem_Principal ().equals ("null")) {
				vl_saldo= ed.getVL_Ordem_Frete ();
				this.calcula_Saldo_Adiantamento (ed);
			}
			else {

				sql =  " update Ordens_Fretes set " +
 					   " oid_Veiculo= '" + ed.getOID_Veiculo () +
					   "', TX_Observacao= '" + ed.getTX_Observacao () +
					   "', VL_Saldo= " + vl_saldo+
					   ",  VL_Adiantamento1= " + ed.getVL_Adiantamento1 () +
					   ",  VL_Ordem_Frete_Convertida= " +VL_Ordem_Frete_Convertida +
					   ",  VL_Ordem_Frete= " + ed.getVL_Ordem_Frete () +
					   ",  OID_Motorista= '" + ed.getOID_Motorista () + "' " +
					   ",  OID_Local_Pagamento= '" + ed.getOID_Local_Pagamento () + "' " +
					   ",  DM_Tipo_Adiantamento = " + util.getSQLString(ed.getDM_Tipo_Adiantamento()) +
					   ",  DT_Pagamento = " + (util.doValida(ed.getDT_Pagamento ()) ? "'" + ed.getDT_Pagamento ()+ "'" : null) +
					   ",  NR_Cheque_Adto = '" + ed.getNR_Cheque_Adto()+"'" +
					   ",  NR_Transacao_Pedagio = " + util.getSQLString(ed.getNR_Transacao_Pedagio());
				sql += ", VL_Vale_Pedagio = " + ed.getVL_Vale_Pedagio ();
				sql += ", VL_Vale_Pedagio_Empresa = " + ed.getVL_Vale_Pedagio_Empresa ();
				sql += ", VL_Coleta = " + ed.getVL_Coleta ();
				sql += ", VL_Carga = " + ed.getVL_Carga ();
				sql += ", VL_Descarga = " + ed.getVL_Descarga ();
				sql += ", VL_Liquido_Ordem_Frete = " + ed.getVL_Liquido_Ordem_Frete ();
				sql += ", VL_Premio = " + ed.getVL_Premio ();
				sql += ", VL_Outros = " + ed.getVL_Outros ();
				sql += " where oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

				i = executasql.executarUpdate (sql);
			}
		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			if (DM_Movimento_Compromisso) {
				excecoes.setMensagem (
						"Compromisso com pagamentos ou vinculado a outro ou em lote de pagamento");
			}
			else {
				excecoes.setMensagem ("Erro ao alterar Ordem de Frete");
			}
			excecoes.setMensagem ("Erro ao alterar Ordem de Frete");
			excecoes.setMetodo ("alterar");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	public void calcula_Saldo_Adiantamento (Ordem_FreteED ed) throws Excecoes {
		String sql = null;

		double  vl_adiantamento1 = 0 , vl_adiantamento2 = 0 , vl_saldo =0;
		int i = 0;

		try {

			sql = " Select vl_adiantamento1, vl_adiantamento2, vl_saldo FROM Ordens_Fretes WHERE oid_Ordem_Frete = '" +  ed.getOID_Ordem_Principal () + "'";
			ResultSet res = null;
			res = this.executasql.executarConsulta (sql);
			while (res.next ()) {
				vl_adiantamento1 = res.getDouble ("vl_adiantamento1");
				vl_adiantamento2 = res.getDouble ("vl_adiantamento2");
				vl_saldo = res.getDouble ("vl_saldo");
			}
			sql = " Select vl_Ordem_Frete, DM_Tipo_Pagamento FROM Ordens_Fretes WHERE oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
			res = this.executasql.executarConsulta (sql);
			while (res.next ()) {
				if (res.getString ("DM_Tipo_Pagamento") != null &&
						res.getString ("DM_Tipo_Pagamento").equals ("D")) {
					vl_adiantamento1 -= res.getDouble ("vl_Ordem_Frete");
				}
				else {
					vl_adiantamento2 -= res.getDouble ("vl_Ordem_Frete");
				}
				vl_saldo += res.getDouble ("vl_Ordem_Frete");
			}

			sql = " UPDATE Ordens_Fretes set  vl_adiantamento1= " + vl_adiantamento1 +
			", vl_adiantamento2=" + vl_adiantamento2 + ", vl_saldo=" + vl_saldo +
			" WHERE oid_Ordem_Frete = '" + ed.getOID_Ordem_Principal () + "'";
			i = executasql.executarUpdate (sql);

		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao calcular saldo da Ordem de Frete");
			excecoes.setMetodo ("alterar");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}




	public void alteraVeiculo (Ordem_FreteED ed) throws Excecoes {
		String sql = null;

		try {

			sql = " update Ordens_Fretes set " +
			" oid_Veiculo= '" + ed.getOID_Veiculo () + "'";
			sql += " where oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

			int i = executasql.executarUpdate (sql);
		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao alterar Ordem de Frete");
			excecoes.setMetodo ("alterar");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	public void altera_Plano_Viagem (Ordem_FreteED ed) throws Excecoes {
		String sql = null;
		boolean DM_Movimento_Compromisso = false;

		try {

			sql = " update Ordens_Fretes set " +
			" TX_Observacao_Plano= '" + ed.getTX_Observacao_Plano () + "'";
			sql += " ,NM_Roteiro1= '" + ed.getNM_Roteiro1 () + "'";
			sql += " ,NM_Roteiro2= '" + ed.getNM_Roteiro2 () + "'";
			sql += " ,NM_Roteiro3= '" + ed.getNM_Roteiro3 () + "'";
			sql += " ,NM_Roteiro4= '" + ed.getNM_Roteiro4 () + "'";
			sql += " ,NM_Roteiro5= '" + ed.getNM_Roteiro5 () + "'";
			sql += " ,NM_Roteiro6= '" + ed.getNM_Roteiro6 () + "'";
			sql += " ,NM_Roteiro7= '" + ed.getNM_Roteiro7 () + "'";
			sql += " ,NM_Roteiro8= '" + ed.getNM_Roteiro8 () + "'";
			sql += " ,NM_Roteiro9= '" + ed.getNM_Roteiro9 () + "'";
			sql += " ,NM_Roteiro10= '" + ed.getNM_Roteiro10 () + "'";
			sql += " ,DM_Check1= '" + ed.getDM_Check1 () + "'";
			sql += " ,DM_Check2= '" + ed.getDM_Check2 () + "'";
			sql += " ,DM_Check3= '" + ed.getDM_Check3 () + "'";
			sql += " ,DM_Check4= '" + ed.getDM_Check4 () + "'";
			sql += " ,DM_Check5= '" + ed.getDM_Check5 () + "'";
			sql += " ,DM_Check6= '" + ed.getDM_Check6 () + "'";
			sql += " ,DM_Check7= '" + ed.getDM_Check7 () + "'";
			sql += " ,DM_Check8= '" + ed.getDM_Check8 () + "'";
			sql += " ,DM_Check9= '" + ed.getDM_Check9 () + "'";
			sql += " ,DM_Check10= '" + ed.getDM_Check10 () + "'";
			sql += " ,DM_Check11= '" + ed.getDM_Check11 () + "'";
			sql += " ,DT_Ponto1= '" + ed.getDT_Ponto1 () + "'";
			sql += " ,DT_Ponto2= '" + ed.getDT_Ponto2 () + "'";
			sql += " ,DT_Ponto3= '" + ed.getDT_Ponto3 () + "'";
			sql += " ,DT_Ponto4= '" + ed.getDT_Ponto4 () + "'";
			sql += " ,DT_Ponto5= '" + ed.getDT_Ponto5 () + "'";
			sql += " ,DT_Ponto6= '" + ed.getDT_Ponto6 () + "'";
			sql += " ,DT_Ponto7= '" + ed.getDT_Ponto7 () + "'";
			sql += " ,DT_Ponto8= '" + ed.getDT_Ponto8 () + "'";
			sql += " ,HR_Ponto1= '" + ed.getHR_Ponto1 () + "'";
			sql += " ,HR_Ponto2= '" + ed.getHR_Ponto2 () + "'";
			sql += " ,HR_Ponto3= '" + ed.getHR_Ponto3 () + "'";
			sql += " ,HR_Ponto4= '" + ed.getHR_Ponto4 () + "'";
			sql += " ,HR_Ponto5= '" + ed.getHR_Ponto5 () + "'";
			sql += " ,HR_Ponto6= '" + ed.getHR_Ponto6 () + "'";
			sql += " ,HR_Ponto7= '" + ed.getHR_Ponto7 () + "'";
			sql += " ,HR_Ponto8= '" + ed.getHR_Ponto8 () + "'";
			sql += " ,NM_Ponto1= '" + ed.getNM_Ponto1 () + "'";
			sql += " ,NM_Ponto2= '" + ed.getNM_Ponto2 () + "'";
			sql += " ,NM_Ponto3= '" + ed.getNM_Ponto3 () + "'";
			sql += " ,NM_Ponto4= '" + ed.getNM_Ponto4 () + "'";
			sql += " ,NM_Ponto5= '" + ed.getNM_Ponto5 () + "'";
			sql += " ,NM_Ponto6= '" + ed.getNM_Ponto6 () + "'";
			sql += " ,NM_Ponto7= '" + ed.getNM_Ponto7 () + "'";
			sql += " ,NM_Ponto8= '" + ed.getNM_Ponto8 () + "'";

			sql += " where oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

			int i = executasql.executarUpdate (sql);
		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			if (DM_Movimento_Compromisso) {
				excecoes.setMensagem (
						"Compromisso com pagamentos ou vinculado a outro ou em lote de pagamento");
			}
			else {
				excecoes.setMensagem ("Erro ao alterar Ordem de Frete");
			}
			excecoes.setMensagem ("Erro ao alterar Ordem de Frete");
			excecoes.setMetodo ("alterar");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	public byte[] imprime_Ordem_Frete_Adiantamento (Ordem_FreteED ed) throws Exception {
		String sql = null;
		byte[] b = null;
		long valOid = 0;
		long NR_Proximo = 0;
		long NR_Final = 0;
		int NR_Compromisso = 0;
		DM_Tipo_Ordem_Frete = "ADIANTAMENTO";

		try {
			sql = "Select * " +
			" from " +
			" Ordens_Fretes " +
			" where " +
			" Ordens_Fretes.DM_Impresso = 'N'  " +
			" AND Ordens_Fretes.DM_Frete = 'A'  " +
			" AND Ordens_Fretes.VL_ORDEM_FRETE > 0";

			if (ed.getNR_Ordem_Frete () > 0) {
				sql += " and Ordens_Fretes.NR_ORDEM_FRETE = " + ed.getNR_Ordem_Frete ();
			}
			if (ed.getOID_Unidade () > 0) {
				sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
			}
			if (util.doValida (ed.getOID_Ordem_Frete ())) {
				sql += " and Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () +
				"'";
			}


			ResultSet resCTRC = null;
			resCTRC = this.executasql.executarConsulta (sql);

			while (resCTRC.next ()) {
				ed.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
				ed.setOID_Ordem_Frete (resCTRC.getString ("OID_Ordem_Frete"));
				ed.setOID_Pessoa (resCTRC.getString ("Oid_Pessoa"));
				ed.setOID_Motorista (resCTRC.getString ("OID_Motorista"));

				ed.setOID_Veiculo (resCTRC.getString ("OID_Veiculo"));
				ed.setNR_Placa (resCTRC.getString ("OID_Veiculo"));

				ed.setDT_Emissao (resCTRC.getString ("DT_Emissao"));
				DataFormatada.setDT_FormataData (ed.getDT_Emissao ());
				ed.setDT_Emissao (DataFormatada.getDT_FormataData ());

				ed.setDT_Adiantamento1 (resCTRC.getString ("DT_Emissao"));
				DataFormatada.setDT_FormataData (ed.getDT_Adiantamento1 ());
				ed.setDT_Adiantamento1 (DataFormatada.getDT_FormataData ());

				ed.setDT_Adiantamento2 (resCTRC.getString ("DT_Emissao"));
				DataFormatada.setDT_FormataData (ed.getDT_Adiantamento2 ());
				ed.setDT_Adiantamento2 (DataFormatada.getDT_FormataData ());

				ed.setDT_Saldo (resCTRC.getString ("DT_Emissao"));
				DataFormatada.setDT_FormataData (ed.getDT_Saldo ());
				ed.setDT_Saldo (DataFormatada.getDT_FormataData ());

				ed.setVL_Adiantamento1 (resCTRC.getDouble ("VL_Adiantamento1"));
				ed.setVL_Adiantamento2 (resCTRC.getDouble ("VL_Adiantamento2"));
				ed.setVL_Saldo (resCTRC.getDouble ("VL_Saldo"));

				ed.setNR_Ordem_Frete (resCTRC.getLong ("NR_Ordem_Frete"));
				ed.setVL_Ordem_Frete (resCTRC.getDouble ("VL_Ordem_Frete"));
				ed.setDM_Frete (resCTRC.getString ("DM_Frete"));
				ed.setDM_Tipo_Pedagio (resCTRC.getString ("DM_Tipo_Pedagio"));
				ed.setDM_Tipo_Pagamento (resCTRC.getString ("DM_Tipo_Pagamento"));
				ed.setDM_Impresso (resCTRC.getString ("DM_Impresso"));
				ed.setTX_Observacao (resCTRC.getString ("TX_Observacao"));

				double VL_Ordem_Frete = ed.getVL_Ordem_Frete ();
				double vl_cotacao = 1;
				double vl_cotacao_padrao = 1;
				if (resCTRC.getDouble ("VL_Cotacao") > 0 &&
						resCTRC.getDouble ("VL_Cotacao_Padrao") > 0 &&
						resCTRC.getDouble ("VL_Cotacao") !=
							resCTRC.getDouble ("VL_Cotacao_Padrao")) {
					vl_cotacao = resCTRC.getDouble ("VL_Cotacao");
					vl_cotacao_padrao = resCTRC.getDouble ("VL_Cotacao_Padrao");
					VL_Ordem_Frete = Valor.round (VL_Ordem_Frete / vl_cotacao * vl_cotacao_padrao , 2);
				}

				int u;
				if (util.doValida (ed.getOID_Ordem_Principal ()) && !"P".equals (resCTRC.getString ("dm_tipo_pagamento"))) {
					sql =" select nr_ordem_frete from ordens_fretes where oid_ordem_frete = '" + ed.getOID_Ordem_Principal () + "'";
					ResultSet res = executasql.executarConsulta (sql);
					if (res.next ()) {
						ed.setNR_Ordem_Frete (res.getLong ("NR_Ordem_Frete"));
					}
				}
				else {


					sql =" SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie " +
					" FROM Parametros_Filiais, AIDOF " +
					" WHERE Parametros_Filiais.OID_AIDOF_ORDEM_FRETE_ADTO = AIDOF.OID_AIDOF ";
					if ("P".equals (resCTRC.getString ("dm_tipo_pagamento"))) { //ordem abastecimento
						sql = " SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie " +
						" FROM Parametros_Filiais, AIDOF " +
						" WHERE Parametros_Filiais.OID_AIDOF_ORDEM_ABASTECIMENTO = AIDOF.OID_AIDOF ";
					}


					sql += " AND Parametros_Filiais.OID_Unidade = " + ed.getOID_Unidade ();


					ResultSet resTP = null;
					resTP = this.executasql.executarConsulta (sql.toString ());

					while (resTP.next ()) {

						ed.setNR_Ordem_Frete (resTP.getLong ("NR_Proximo"));
						valOid = resTP.getLong ("OID_AIDOF");
						NR_Proximo = resTP.getLong ("NR_Proximo");
						NR_Final = resTP.getLong ("NR_FINAL");
					}

					if (NR_Proximo > NR_Final) {
						Excecoes exc = new Excecoes ("AIDOF sem numera��o dispon�vel");
						throw exc;
					}

					sql = " UPDATE AIDOF SET NR_Proximo= " + (ed.getNR_Ordem_Frete () + 1);
					sql += " WHERE OID_AIDOF = " + valOid;

					executasql.executarUpdate (sql);
				}

				sql = " update Ordens_Fretes set DM_Impresso='S', " +
				" NR_ORDEM_FRETE = " + ed.getNR_Ordem_Frete () +
				" where Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

				executasql.executarUpdate (sql);

				if ("P".equals (resCTRC.getString ("dm_tipo_pagamento"))) {
					sql = " select nr_ordem_frete from ordens_fretes where oid_ordem_frete = '" + ed.getOID_Ordem_Principal () + "'";
					ResultSet res = executasql.executarConsulta (sql);
					if (res.next ()) {
						sql = " update Ordens_Fretes set TX_Observacao = 'ORDEM PRINCIPAL=" + res.getString ("NR_Ordem_Frete")+ "'" +
						" where Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
						// .println ("IMP ADTO gera comp->>" +     sql);

						executasql.executarUpdate (sql);
					}
				}


				// .println ("IMP ADTO gera comp->>" +       parametro_FixoED.getDM_Gera_Compromisso_Ordem_Frete_Adiantamento ());
				// .println ("IMP ADTO getDM_Tipo_Pagamento ->>" +       ed.getDM_Tipo_Pagamento ());

				if (VL_Ordem_Frete > 0 && parametro_FixoED.getDM_Gera_Compromisso_Ordem_Frete_Adiantamento ().equals ("S")) {
					if (!"A".equals(ed.getDM_Tipo_Pagamento()) && !"P".equals(ed.getDM_Tipo_Pagamento())){/// A==Adto que vem do ACERTO de VIAGEM
						ed.setDT_Vencimento (ed.getDT_Adiantamento1 ());
						ed.setNR_Parcela (new Integer ("1"));

						//ed.setVL_Compromisso (VL_Ordem_Frete); //leva (se for moeda dif.) valor convertido
						ed.setVL_Compromisso (ed.getVL_Ordem_Frete ());
						NR_Compromisso = this.geraCompromisso (ed);
						// .println ("IMP geraCompromisso retorno");
					}
				}

				if (VL_Ordem_Frete > 0 && parametro_FixoED.getDM_Gera_Compromisso_Ordem_Abastecimento ().equals ("S")) {
					if ("P".equals(ed.getDM_Tipo_Pagamento())){/// P=Posto Abastecimento
						DM_Tipo_Ordem_Frete="ABASTECIMENTO";
						ed.setDT_Vencimento (ed.getDT_Adiantamento1 ());
						ed.setNR_Parcela (new Integer ("1"));
						ed.setVL_Compromisso (ed.getVL_Ordem_Frete ());
						NR_Compromisso = this.geraCompromisso (ed);
						// .println ("IMP geraCompromisso retorno");
					}
				}

				if (VL_Ordem_Frete > 0 && parametro_FixoED.getDM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento (). equals ("S")) {
					// .println ("IMP geraContaCorrente ");
					this.geraContaCorrente(ed, VL_Ordem_Frete, "INCLUSAO");
					// .println ("IMP geraContaCorrente retorno");
				}

			}


			sql = "Select *, " +
			" Ordens_Fretes.TX_OBSERVACAO," +
			" Veiculos.NR_PLACA," +
			" Veiculos.oid_Pessoa as OID_Motorista," +
			" Modelos_Veiculos.CD_MODELO_VEICULO," +
			" Modelos_Veiculos.NM_MODELO_VEICULO," +
			" Cidade_Veiculo.NM_CIDADE as NM_CIDADE_VEICULO, " +
			" Estado_Veiculo.CD_ESTADO as CD_ESTADO_VEICULO, " +
			" Pessoa_Proprietario.NR_CNPJ_CPF as NR_CNPJ_CPF_PROPRIETARIO, " +
			" Pessoa_Proprietario.NM_RAZAO_SOCIAL as NM_RAZAO_SOCIAL_PROPRIETARIO, " +
			" Pessoa_Proprietario.NM_ENDERECO as NM_ENDERECO_PROPRIETARIO, " +
			" Pessoa_Proprietario.NR_CEP as NR_CEP_PROPRIETARIO, " +
			" Pessoa_Proprietario.NM_BAIRRO as NM_BAIRRO_PROPRIETARIO, " +
			" Cidade_Proprietario.NM_CIDADE as NM_CIDADE_PROPRIETARIO, " +
			" Estado_Proprietario.CD_ESTADO as CD_ESTADO_PROPRIETARIO " +
			" FROM " +
			" Ordens_Fretes, Veiculos, Pessoas Pessoa_Proprietario, " +
			" Cidades Cidade_Proprietario, " +
			" Regioes_Estados Regiao_Estado_Proprietario, " +
			" Estados Estado_Proprietario, " +
			" Modelos_Veiculos, " +
			" Cidades Cidade_Veiculo, " +
			" Regioes_Estados Regiao_Estado_Veiculo, " +
			" Estados Estado_Veiculo " +
			" WHERE Ordens_Fretes.OID_Pessoa = Pessoa_Proprietario.OID_Pessoa " +
			" AND Ordens_Fretes.OID_Veiculo = Veiculos.OID_Veiculo " +
			" AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO " +
			" AND Veiculos.OID_CIDADE = Cidade_Veiculo.OID_CIDADE " +
			" AND Cidade_Veiculo.OID_REGIAO_ESTADO = Regiao_Estado_Veiculo.OID_REGIAO_ESTADO " +
			" AND Regiao_Estado_Veiculo.OID_ESTADO = Estado_Veiculo.OID_ESTADO " +
			" AND Pessoa_Proprietario.OID_CIDADE = Cidade_Proprietario.OID_CIDADE " +
			" AND Cidade_Proprietario.OID_REGIAO_ESTADO = Regiao_Estado_Proprietario.OID_REGIAO_ESTADO " +
			" AND Regiao_Estado_Proprietario.OID_ESTADO = Estado_Proprietario.OID_ESTADO " +
			" AND Ordens_Fretes.VL_Ordem_Frete > 0 " +
			" AND Ordens_Fretes.DM_Frete = 'A' " +
			" AND Ordens_Fretes.DM_Impresso = 'S'";

			if (ed.getNR_Ordem_Frete () > 0) {
				sql += " and Ordens_Fretes.OID_ORDEM_FRETE = '" + ed.getOID_Ordem_Frete () +
				"'";
			}
			if (ed.getOID_Unidade () > 0) {
				sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
			}
			if (util.doValida (ed.getOID_Ordem_Frete ())) {
				sql += " and Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () +
				"'";
			}

			sql += " order by Ordens_Fretes.NR_ORDEM_FRETE";

			ResultSet res = this.executasql.executarConsulta (sql.toString ());

			b = new Ordem_FreteRL ().imprime_Ordem_Frete_Adiantamento (res ,NR_Compromisso);

		}
		catch (Excecoes e) {
			throw e;
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
					"imprime_Ordem_Frete_Adiantamento(Ordem_FreteED ed)");
		}
		return b;
	}

	public ResultSet seleciona_Ordem_Frete_Adiantamento_Matricial (Ordem_FreteED ed) throws Exception {
		String sql = null;
		byte[] b = null;
		long valOid = 0;
		long NR_Proximo = 0;
		long NR_Final = 0;
		int NR_Compromisso = 0;
		DM_Tipo_Ordem_Frete = "ADIANTAMENTO";
		ResultSet res = null;
		try {
			sql = "Select * " +
			" from " +
			" Ordens_Fretes " +
			" where " +
			" Ordens_Fretes.DM_Impresso = 'N'  " +
			" AND Ordens_Fretes.DM_Frete = 'A'  " +
			" AND Ordens_Fretes.VL_ORDEM_FRETE > 0";

			if (ed.getNR_Ordem_Frete () > 0) {
				sql += " and Ordens_Fretes.NR_ORDEM_FRETE = " + ed.getNR_Ordem_Frete ();
			}
			if (ed.getOID_Unidade () > 0) {
				sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
			}
			if (util.doValida (ed.getOID_Ordem_Frete ())) {
				sql += " and Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () +
				"'";
			}


			ResultSet resCTRC = null;
			resCTRC = this.executasql.executarConsulta (sql);

			while (resCTRC.next ()) {
				ed.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
				ed.setOID_Ordem_Frete (resCTRC.getString ("OID_Ordem_Frete"));
				ed.setOID_Pessoa (resCTRC.getString ("Oid_Pessoa"));
				ed.setOID_Motorista (resCTRC.getString ("OID_Motorista"));

				ed.setOID_Veiculo (resCTRC.getString ("OID_Veiculo"));
				ed.setNR_Placa (resCTRC.getString ("OID_Veiculo"));

				ed.setDT_Emissao (resCTRC.getString ("DT_Emissao"));
				DataFormatada.setDT_FormataData (ed.getDT_Emissao ());
				ed.setDT_Emissao (DataFormatada.getDT_FormataData ());

				ed.setDT_Adiantamento1 (resCTRC.getString ("DT_Emissao"));
				DataFormatada.setDT_FormataData (ed.getDT_Adiantamento1 ());
				ed.setDT_Adiantamento1 (DataFormatada.getDT_FormataData ());

				ed.setDT_Adiantamento2 (resCTRC.getString ("DT_Emissao"));
				DataFormatada.setDT_FormataData (ed.getDT_Adiantamento2 ());
				ed.setDT_Adiantamento2 (DataFormatada.getDT_FormataData ());

				ed.setDT_Saldo (resCTRC.getString ("DT_Emissao"));
				DataFormatada.setDT_FormataData (ed.getDT_Saldo ());
				ed.setDT_Saldo (DataFormatada.getDT_FormataData ());

				ed.setVL_Adiantamento1 (resCTRC.getDouble ("VL_Adiantamento1"));
				ed.setVL_Adiantamento2 (resCTRC.getDouble ("VL_Adiantamento2"));
				ed.setVL_Saldo (resCTRC.getDouble ("VL_Saldo"));

				ed.setNR_Ordem_Frete (resCTRC.getLong ("NR_Ordem_Frete"));
				ed.setVL_Ordem_Frete (resCTRC.getDouble ("VL_Ordem_Frete"));
				ed.setDM_Frete (resCTRC.getString ("DM_Frete"));
				ed.setDM_Tipo_Pedagio (resCTRC.getString ("DM_Tipo_Pedagio"));
				ed.setDM_Tipo_Pagamento (resCTRC.getString ("DM_Tipo_Pagamento"));
				ed.setDM_Impresso (resCTRC.getString ("DM_Impresso"));
				ed.setTX_Observacao (resCTRC.getString ("TX_Observacao"));

				double VL_Ordem_Frete = ed.getVL_Ordem_Frete ();
				double vl_cotacao = 1;
				double vl_cotacao_padrao = 1;
				if (resCTRC.getDouble ("VL_Cotacao") > 0 &&
						resCTRC.getDouble ("VL_Cotacao_Padrao") > 0 &&
						resCTRC.getDouble ("VL_Cotacao") !=
							resCTRC.getDouble ("VL_Cotacao_Padrao")) {
					vl_cotacao = resCTRC.getDouble ("VL_Cotacao");
					vl_cotacao_padrao = resCTRC.getDouble ("VL_Cotacao_Padrao");
					VL_Ordem_Frete = Valor.round (VL_Ordem_Frete / vl_cotacao * vl_cotacao_padrao , 2);
				}

				int u;
				if (util.doValida (ed.getOID_Ordem_Principal ()) && !"P".equals (resCTRC.getString ("dm_tipo_pagamento"))) {
					sql =" select nr_ordem_frete from ordens_fretes where oid_ordem_frete = '" + ed.getOID_Ordem_Principal () + "'";
					res = executasql.executarConsulta (sql);
					if (res.next ()) {
						ed.setNR_Ordem_Frete (res.getLong ("NR_Ordem_Frete"));
					}
				}
				else {


					sql =" SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie " +
					" FROM Parametros_Filiais, AIDOF " +
					" WHERE Parametros_Filiais.OID_AIDOF_ORDEM_FRETE_ADTO = AIDOF.OID_AIDOF ";
					if ("P".equals (resCTRC.getString ("dm_tipo_pagamento"))) { //ordem abastecimento
						sql = " SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie " +
						" FROM Parametros_Filiais, AIDOF " +
						" WHERE Parametros_Filiais.OID_AIDOF_ORDEM_ABASTECIMENTO = AIDOF.OID_AIDOF ";
					}


					sql += " AND Parametros_Filiais.OID_Unidade = " + ed.getOID_Unidade ();


					ResultSet resTP = null;
					resTP = this.executasql.executarConsulta (sql.toString ());

					while (resTP.next ()) {

						ed.setNR_Ordem_Frete (resTP.getLong ("NR_Proximo"));
						valOid = resTP.getLong ("OID_AIDOF");
						NR_Proximo = resTP.getLong ("NR_Proximo");
						NR_Final = resTP.getLong ("NR_FINAL");
					}

					if (NR_Proximo > NR_Final) {
						Excecoes exc = new Excecoes ("AIDOF sem numera��o dispon�vel");
						throw exc;
					}

					sql = " UPDATE AIDOF SET NR_Proximo= " + (ed.getNR_Ordem_Frete () + 1);
					sql += " WHERE OID_AIDOF = " + valOid;

					executasql.executarUpdate (sql);
				}

				sql = " update Ordens_Fretes set DM_Impresso='S', " +
				" NR_ORDEM_FRETE = " + ed.getNR_Ordem_Frete () +
				" where Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

				executasql.executarUpdate (sql);

				if ("P".equals (resCTRC.getString ("dm_tipo_pagamento"))) {
					sql = " select nr_ordem_frete from ordens_fretes where oid_ordem_frete = '" + ed.getOID_Ordem_Principal () + "'";
					res = executasql.executarConsulta (sql);
					if (res.next ()) {
						sql = " update Ordens_Fretes set TX_Observacao = 'ORDEM PRINCIPAL=" + res.getString ("NR_Ordem_Frete")+ "'" +
						" where Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
						// .println ("IMP ADTO gera comp->>" +     sql);

						executasql.executarUpdate (sql);
					}
				}


				// .println ("IMP ADTO gera comp->>" +       parametro_FixoED.getDM_Gera_Compromisso_Ordem_Frete_Adiantamento ());
				// .println ("IMP ADTO getDM_Tipo_Pagamento ->>" +       ed.getDM_Tipo_Pagamento ());

				if (VL_Ordem_Frete > 0.01 && parametro_FixoED.getDM_Gera_Compromisso_Ordem_Frete_Adiantamento ().equals ("S")) {
					//if (!"A".equals(ed.getDM_Tipo_Pagamento()) && !"P".equals(ed.getDM_Tipo_Pagamento())){/// A==Adto que vem do ACERTO de VIAGEM
						ed.setDT_Vencimento (ed.getDT_Adiantamento1 ());
						ed.setNR_Parcela (new Integer ("1"));

						//ed.setVL_Compromisso (VL_Ordem_Frete); //leva (se for moeda dif.) valor convertido
						ed.setVL_Compromisso (ed.getVL_Ordem_Frete ());
						NR_Compromisso = this.geraCompromisso (ed);
						// .println ("IMP geraCompromisso retorno");
					//}
				}

				if (VL_Ordem_Frete > 0 && parametro_FixoED.getDM_Gera_Compromisso_Ordem_Abastecimento ().equals ("S")) {
					if ("P".equals(ed.getDM_Tipo_Pagamento())){/// P=Posto Abastecimento
						DM_Tipo_Ordem_Frete="ABASTECIMENTO";
						ed.setDT_Vencimento (ed.getDT_Adiantamento1 ());
						ed.setNR_Parcela (new Integer ("1"));
						ed.setVL_Compromisso (ed.getVL_Ordem_Frete ());
						NR_Compromisso = this.geraCompromisso (ed);
						// .println ("IMP geraCompromisso retorno");
					}
				}

				if (VL_Ordem_Frete > 0 && parametro_FixoED.getDM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento (). equals ("S")) {
					// .println ("IMP geraContaCorrente ");
					this.geraContaCorrente(ed, VL_Ordem_Frete, "INCLUSAO");
					// .println ("IMP geraContaCorrente retorno");
				}

			}


			sql = "Select " +
			" Ordens_Fretes.*," +
			" Veiculos.NR_PLACA," +
			" Modelos_Veiculos.CD_MODELO_VEICULO," +
			" Modelos_Veiculos.NM_MODELO_VEICULO," +
			" Cidade_Veiculo.NM_CIDADE as NM_CIDADE_VEICULO, " +
			" Estado_Veiculo.CD_ESTADO as CD_ESTADO_VEICULO, " +
			" Pessoa_Proprietario.NR_CNPJ_CPF as NR_CNPJ_CPF_PROPRIETARIO, " +
			" Pessoa_Proprietario.NM_RAZAO_SOCIAL as NM_RAZAO_SOCIAL_PROPRIETARIO, " +
			" Pessoa_Proprietario.NM_ENDERECO as NM_ENDERECO_PROPRIETARIO, " +
			" Pessoa_Proprietario.NR_CEP as NR_CEP_PROPRIETARIO, " +
			" Pessoa_Proprietario.NM_BAIRRO as NM_BAIRRO_PROPRIETARIO, " +
			" Pessoa_Proprietario.NR_TELEFONE as NR_TELEFONE_PROPRIETARIO, " +
			" Cidade_Proprietario.NM_CIDADE as NM_CIDADE_PROPRIETARIO, " +
			" Estado_Proprietario.CD_ESTADO as CD_ESTADO_PROPRIETARIO " +
			" FROM " +
			" Ordens_Fretes, Veiculos, Pessoas Pessoa_Proprietario, " +
			" Cidades Cidade_Proprietario, " +
			" Regioes_Estados Regiao_Estado_Proprietario, " +
			" Estados Estado_Proprietario, " +
			" Modelos_Veiculos, " +
			" Cidades Cidade_Veiculo, " +
			" Regioes_Estados Regiao_Estado_Veiculo, " +
			" Estados Estado_Veiculo " +
			" WHERE Ordens_Fretes.OID_Pessoa = Pessoa_Proprietario.OID_Pessoa " +
			" AND Ordens_Fretes.OID_Veiculo = Veiculos.OID_Veiculo " +
			" AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO " +
			" AND Veiculos.OID_CIDADE = Cidade_Veiculo.OID_CIDADE " +
			" AND Cidade_Veiculo.OID_REGIAO_ESTADO = Regiao_Estado_Veiculo.OID_REGIAO_ESTADO " +
			" AND Regiao_Estado_Veiculo.OID_ESTADO = Estado_Veiculo.OID_ESTADO " +
			" AND Pessoa_Proprietario.OID_CIDADE = Cidade_Proprietario.OID_CIDADE " +
			" AND Cidade_Proprietario.OID_REGIAO_ESTADO = Regiao_Estado_Proprietario.OID_REGIAO_ESTADO " +
			" AND Regiao_Estado_Proprietario.OID_ESTADO = Estado_Proprietario.OID_ESTADO " +
			" AND Ordens_Fretes.VL_Ordem_Frete > 0 " +
			" AND Ordens_Fretes.DM_Frete = 'A' " +
			" AND Ordens_Fretes.DM_Impresso = 'S'";

			if (ed.getNR_Ordem_Frete () > 0) {
				sql += " and Ordens_Fretes.OID_ORDEM_FRETE = '" + ed.getOID_Ordem_Frete () +
				"'";
			}
			if (ed.getOID_Unidade () > 0) {
				sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
			}
			if (util.doValida (ed.getOID_Ordem_Frete ())) {
				sql += " and Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () +
				"'";
			}

			sql += " order by Ordens_Fretes.NR_ORDEM_FRETE";

			res = this.executasql.executarConsulta (sql.toString ());


			// b = new Ordem_FreteRL ().imprime_Ordem_Frete_Adiantamento (res ,NR_Compromisso);

		}
		catch (Excecoes e) {
			throw e;
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
					"imprime_Ordem_Frete_Adiantamento(Ordem_FreteED ed)");
		}
		return res;
	}

	public ArrayList Ordem_Frete_Lista_Acerto (Ordem_FreteED ed)
	throws Excecoes {

		String sql = null;
		ArrayList list = new ArrayList ();

		try {

			sql =
				"SELECT Acertos.NR_Acerto, " +
				"       Acertos.oid_Acerto, " +
				"       Ordens_Fretes.OID_Ordem_Frete, " +
				"       Ordens_Fretes.OID_VEICULO, " +
				"       Ordens_Fretes.DT_Emissao, " +
				"       Ordens_Fretes.NR_Ordem_Frete, " +
				"       Ordens_Fretes.VL_Ordem_Frete, " +
				"       Ordens_Fretes.VL_Cotacao, " +
				"       Ordens_Fretes.VL_Ordem_Frete_Convertida, " +
				"       Ordens_Fretes.VL_Total_Frete_CTRC, " +
				"       Ordens_Fretes.DM_Frete,  " +
				"       Ordens_Fretes.TX_Observacao,  " +
				"       Ordens_Fretes.VL_Saldo, " +
				"       Ordens_Fretes.DM_Tipo_Adiantamento, " +
				"       Pessoas.NM_Fantasia " +
				" from Ordens_Fretes, " +
				"      Acertos, " +
				"      Unidades, " +
				"      Pessoas " +
				" WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade " +
				"   AND Ordens_Fretes.OID_Acerto = Acertos.OID_Acerto " +
				"   AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa ";

			if (String.valueOf (ed.getNR_Ordem_Frete ()) != null &&
					!String.valueOf (ed.getNR_Ordem_Frete ()).equals ("0") &&
					!String.valueOf (ed.getNR_Ordem_Frete ()).equals ("null")) {
				sql += " and Ordens_Fretes.NR_Ordem_Frete = " + ed.getNR_Ordem_Frete ();
			}

			if (String.valueOf (ed.getOID_Unidade ()) != null &&
					!String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
					!String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
				sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
			}

			if (String.valueOf (ed.getOID_Acerto ()) != null &&
					!String.valueOf (ed.getOID_Acerto ()).equals ("0") &&
					!String.valueOf (ed.getOID_Acerto ()).equals ("null")) {
				sql += " and Ordens_Fretes.OID_Acerto = " + ed.getOID_Acerto ();
			}

			if (String.valueOf (ed.getDM_Frete ()) != null &&
					!String.valueOf (ed.getDM_Frete ()).equals ("") &&
					String.valueOf (ed.getDM_Frete ()).equals ("P")) {
				sql += " and Ordens_Fretes.DM_Frete = 'P' ";
			}
			if (String.valueOf (ed.getDM_Frete ()) != null &&
					!String.valueOf (ed.getDM_Frete ()).equals ("") &&
					String.valueOf (ed.getDM_Frete ()).equals ("A")) {
				sql += " and Ordens_Fretes.DM_Frete = 'A' ";
			}

			sql += " Order by Ordens_Fretes.NR_Ordem_Frete ";

			ResultSet res = null;
			ResultSet rs = null;
			res = this.executasql.executarConsulta (sql);

			//popula
			while (res.next ()) {

				Ordem_FreteED edVolta = new Ordem_FreteED ();

				edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));

				edVolta.setNR_Placa (res.getString ("OID_VEICULO"));

				edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
				DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
				edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

				edVolta.setOID_Acerto (res.getLong ("oid_Acerto"));
				edVolta.setNR_Acerto (res.getLong ("NR_Acerto"));
				edVolta.setNR_Ordem_Frete (res.getLong ("NR_Ordem_Frete"));
				edVolta.setNR_Ordem_Frete (res.getLong ("NR_Ordem_Frete"));
				edVolta.setVL_Ordem_Frete (res.getDouble ("VL_Ordem_Frete"));
				edVolta.setVL_Cotacao (res.getDouble ("VL_Cotacao"));
				edVolta.setVL_Ordem_Frete_Convertida (res.getDouble ("VL_Ordem_Frete_Convertida"));
				edVolta.setVL_Total_Frete_CTRC (res.getDouble ("VL_Total_Frete_CTRC"));

				edVolta.setNM_Unidade (res.getString ("NM_Fantasia"));
				edVolta.setDM_Frete(res.getString("DM_Frete"));
				edVolta.setVL_Saldo(res.getDouble("VL_Saldo"));
				edVolta.setDM_Tipo_Adiantamento(res.getString("DM_Tipo_Adiantamento"));
				edVolta.setTX_Observacao("---");

				String NM_Razao_Social = "";
				sql = " SELECT Ordens_Manifestos.oid_Manifesto ";
				sql +=" FROM Ordens_Manifestos ";
				sql +=" WHERE Ordens_Manifestos.oid_Ordem_Frete = '" +  edVolta.getOID_Ordem_Frete () + "'";

				rs = this.executasql.executarConsulta (sql);
				while (rs.next ()) {

					sql = " SELECT Pessoas.NM_Razao_Social, Conhecimentos.dm_Tipo_Documento ";
					sql +=" FROM Viagens, Conhecimentos, Pessoas ";
					sql +=" WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
					sql +=" AND Conhecimentos.OID_Pessoa_Pagador =  Pessoas.OID_Pessoa ";
					sql +=" AND Viagens.OID_Manifesto = '" +  rs.getString("oid_Manifesto") + "'";

					ResultSet rs1 = this.executasql.executarConsulta (sql);
					while (rs1.next ()) {
						String NM_Tipo_Documento = "";

						if ("C".equals(rs1.getString("dm_Tipo_Documento"))){
							NM_Tipo_Documento = "PR�PRIO";
						}else{
							NM_Tipo_Documento = "TERCEIRO";
						}

						NM_Razao_Social = rs1.getString("NM_Razao_Social") + " - " + NM_Tipo_Documento;
					}
				}

				String TX_Observacao = NM_Razao_Social;
				if (res.getString("TX_Observacao")!=null && res.getString("TX_Observacao").length()>4){
					TX_Observacao = TX_Observacao + " - " + res.getString("TX_Observacao");
				}

				edVolta.setTX_Observacao(TX_Observacao + "                                                      .                                ".substring(0,80));

				list.add (edVolta);
			}
		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "Ordem_Frete_Lista_Acerto (Ordem_FreteED ed)");
		}

		return list;
	}

	public void altera (Ordem_FreteED ed) throws Excecoes {

		String sql = null;
		boolean DM_Movimento_Compromisso = false;
		ed = this.calcula_Saldo_Ordem_Frete (ed);

		try {

			sql = " update Ordens_Fretes set ";

			sql += " VL_IRRF = " + ed.getVL_IRRF () + ", ";

			if (ed.getOID_Veiculo () != null) {
				sql += " oid_Veiculo = '" + ed.getOID_Veiculo () + "', ";
			}
			else {
				sql += " oid_Veiculo = null,";
			}

			if (ed.getOID_Unidade_Adiantamento1 () >0) {
				sql += " oid_Unidade_Adiantamento1 = '" + ed.getOID_Unidade_Adiantamento1 () + "', ";
			}
			else {
				sql += " oid_Unidade_Adiantamento1 = null,";
			}
			if (ed.getOID_Unidade_Adiantamento2 () >0) {
				sql += " oid_Unidade_Adiantamento2 = '" + ed.getOID_Unidade_Adiantamento2 () + "', ";
			}
			else {
				sql += " oid_Unidade_Adiantamento2 = null,";
			}
			if (ed.getOID_Unidade_Saldo () >0) {
				sql += " oid_Unidade_Saldo = '" + ed.getOID_Unidade_Saldo () + "', ";
			}
			else {
				sql += " oid_Unidade_Saldo = null,";
			}


			sql += " VL_Pedagio = " + ed.getVL_Pedagio () + ", ";

			sql += " VL_Taxa_Expediente = " + ed.getVL_Taxa_Expediente () + ", ";

			sql += " NR_Quantidade_Coleta = " + ed.getNR_Qtde_Coleta () + ", ";

			sql += " NR_Quantidade_Entrega = " + ed.getNR_Qtde_Entrega () + ", ";

			sql += " VL_Descontos = " + ed.getVL_Descontos () + ", ";

			sql += " VL_Multa_Atrazo = " + ed.getVL_Multa_Atrazo () + ", ";

			sql += " VL_INSS_Prestador = " + ed.getVL_INSS_Prestador () + ", ";

			sql += " VL_INSS_Empresa = " + ed.getVL_INSS_Empresa () + ", ";

			if (ed.getTX_Observacao () != null) {
				sql += " TX_Observacao = '" + ed.getTX_Observacao () + "', ";
			}
			else {
				sql += " TX_Observacao = null,";
			}

			if (ed.getNM_Pagamento () != null) {
				sql += " NM_Pagamento = '" + ed.getNM_Pagamento () + "', ";
			}
			else {
				sql += " NM_Pagamento = null,";
			}

			if (ed.getNR_Transacao_Pedagio () != null) {
				sql += " NR_Transacao_Pedagio = '" + ed.getNR_Transacao_Pedagio () + "', ";
			}
			else {
				sql += " NR_Transacao_Pedagio = null,";
			}


			if (ed.getDM_Tipo_Pagamento () != null) {
				sql += " DM_Tipo_Pagamento = '" + ed.getDM_Tipo_Pagamento () + "', ";
			}
			else {
				sql += " DM_Tipo_Pagamento = null,";
			}

			if (ed.getDM_Tipo_Pagamento_Adiantamento1 () != null) {
				sql += " DM_Tipo_Pagamento_Adiantamento1 = '" + ed.getDM_Tipo_Pagamento_Adiantamento1 () + "', ";
			}
			else {
				sql += " DM_Tipo_Pagamento_Adiantamento1 = null,";
			}

			if (ed.getDM_Tipo_Pagamento_Adiantamento2 () != null) {
				sql += " DM_Tipo_Pagamento_Adiantamento2 = '" + ed.getDM_Tipo_Pagamento_Adiantamento2 () + "', ";
			}
			else {
				sql += " DM_Tipo_Pagamento_Adiantamento2 = null,";
			}

			if (ed.getDM_Tipo_Pagamento_Saldo () != null) {
				sql += " DM_Tipo_Pagamento_Saldo = '" + ed.getDM_Tipo_Pagamento_Saldo () + "', ";
			}
			else {
				sql += " DM_Tipo_Pagamento_Saldo = null,";
			}

			if (ed.getDT_Adiantamento1 () != null) {
				sql += " DT_Adiantamento1 = '" + ed.getDT_Adiantamento1 () + "', ";
			}
			else {
				sql += " DT_Adiantamento1 = null,";
			}

			if (ed.getDT_Adiantamento2 () != null) {
				sql += " DT_Adiantamento2 = '" + ed.getDT_Adiantamento2 () + "', ";
			}
			else {
				sql += " DT_Adiantamento2 = null,";
			}

			if (ed.getDT_Saldo () != null) {
				sql += " DT_Saldo = '" + ed.getDT_Saldo () + "', ";
			}
			else {
				sql += " DT_Saldo = null,";
			}

			sql += " VL_Set_Senat = " + ed.getVL_Set_Senat ();

			sql += ", VL_Coleta = " + ed.getVL_Coleta ();
			sql += ", VL_Carga = " + ed.getVL_Carga ();
			sql += ", VL_Descarga = " + ed.getVL_Descarga ();
			sql += ", VL_Liquido_Ordem_Frete = " + ed.getVL_Liquido_Ordem_Frete ();
			sql += ", VL_Premio = " + ed.getVL_Premio ();
			sql += ", VL_Outros = " + ed.getVL_Outros ();
			sql += ", VL_Vale_Pedagio = " + ed.getVL_Vale_Pedagio ();
			sql += ", VL_Vale_Pedagio_Empresa = " + ed.getVL_Vale_Pedagio_Empresa ();
			sql += ", VL_INSS_Pago = " + ed.getVL_INSS_Pago ();

			sql += ", VL_Ordem_Frete= " + ed.getVL_Ordem_Frete () +
			", VL_Adiantamento1= " + ed.getVL_Adiantamento1 () +
			", VL_Adiantamento2= " + ed.getVL_Adiantamento2 () +
			", VL_Saldo= " + ed.getVL_Saldo() +
			", oid_Motorista = '" + ed.getOID_Motorista() + "'";

			sql += " where oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

			executasql.executarUpdate (sql);

			ResultSet rs = null;

			sql = " SELECT nr_posicao, nr_chave, VL_Total_Frete_CTRC ";
			sql += " FROM ordens_fretes ";
			sql += " where oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
			// System.out.println(sql);
			rs = this.executasql.executarConsulta (sql);

			while (rs.next ()) {
				ed.setNR_Posicao(rs.getLong ("nr_posicao"));
				ed.setNR_Chave(rs.getLong ("nr_chave"));
				ed.setVL_Total_Frete_CTRC (rs.getDouble ("VL_Total_Frete_CTRC"));
			}

			if (this.verifica_Valores_Ordem_Frete(ed)){

				if (ed.getNR_Chave()==0){

					Ordem_FreteED ordem_Frete_ChaveED = new Ordem_FreteED ();

					ordem_Frete_ChaveED = this.busca_Chaves();

					ed.setNR_Posicao(ordem_Frete_ChaveED.getNR_Posicao());
					ed.setNR_Chave(ordem_Frete_ChaveED.getNR_Chave());

				}
			}else{
				ed.setNR_Posicao(0);
				ed.setNR_Chave(0);
			}
			sql = " update Ordens_fretes " +
				  " set nr_posicao = " + ed.getNR_Posicao() +
				  "    ,nr_chave = " + ed.getNR_Chave() +
				  " where oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete() + "'";

			executasql.executarUpdate (sql);

		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			if (DM_Movimento_Compromisso) {
				excecoes.setMensagem (
						"Compromisso com pagamentos ou vinculado a outro ou em lote de pagamento");
			}
			else {
				excecoes.setMensagem ("Erro ao alterar Ordem de Frete");
			}
			excecoes.setMensagem ("Erro ao alterar Ordem de Frete");
			excecoes.setMetodo ("alterar");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	public Ordem_FreteED getByRecord (Ordem_FreteED ed) throws Excecoes {
		Ordem_FreteED edVolta = new Ordem_FreteED ();
		String sql =
			" SELECT * from Ordens_Fretes " +
			" WHERE Ordens_Fretes.DM_Frete != 'C'  "+
			" AND Ordens_Fretes.DM_Frete <>'M' ";  //M=Master

		if (util.doValida (ed.getOID_Ordem_Frete ())) {
			sql += " and OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete() + "'";
		}else {
			sql += " and NR_Ordem_Frete = " + ed.getNR_Ordem_Frete();
			sql += " and oid_Unidade = " + ed.getOID_Unidade();

		}

		ResultSet res = this.executasql.executarConsulta (sql);
		ResultSet res2 = null;;
		try {
			if (res.next ()) {
				edVolta.setOID_Ordem_Principal (res.getString ("OID_Ordem_Principal"));
				edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));
				edVolta.setOID_Unidade (res.getLong ("OID_Unidade"));
				edVolta.setOID_Usuario (res.getLong ("OID_Usuario"));
				edVolta.setOID_Unidade_Adiantamento1 (res.getLong ("OID_Unidade_Adiantamento1"));
				edVolta.setOID_Unidade_Adiantamento2 (res.getLong ("OID_Unidade_Adiantamento2"));
				edVolta.setOID_Unidade_Saldo (res.getLong ("OID_Unidade_Saldo"));
				edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
				edVolta.setOID_Fornecedor (res.getString ("OID_Fornecedor"));
				edVolta.setOID_Local_Pagamento (res.getString ("OID_Local_Pagamento"));
				edVolta.setOID_Motorista (res.getString ("OID_Motorista"));
				edVolta.setOID_Acerto (res.getLong ("OID_Acerto"));
				edVolta.setOID_Veiculo (res.getString ("OID_Veiculo"));
				edVolta.setNR_Placa (res.getString ("OID_Veiculo"));
				edVolta.setDT_Emissao (FormataData.formataDataBT (res.getDate ("DT_Emissao")));
				edVolta.setDT_Adiantamento1 (FormataData.formataDataBT (res.getDate ( "DT_Adiantamento1")));
				edVolta.setDT_Adiantamento2 (FormataData.formataDataBT (res.getDate ( "DT_Adiantamento2")));
				edVolta.setDT_Saldo (FormataData.formataDataBT (res.getDate ("DT_Saldo")));
				edVolta.setVL_Adiantamento1 (res.getDouble ("VL_Adiantamento1"));
				edVolta.setVL_Adiantamento2 (res.getDouble ("VL_Adiantamento2"));
				edVolta.setVL_Saldo (res.getDouble ("VL_Saldo"));
				edVolta.setVL_Cotacao (res.getDouble ("VL_Cotacao"));
				edVolta.setVL_Total_Frete_CTRC (res.getDouble ("VL_Total_Frete_CTRC"));

				edVolta.setNM_Local_Troca(res.getString("NM_local_Troca"));

				//pamcard
				edVolta.setCIOT(res.getString("CIOT"));
				edVolta.setVIAGEMID(res.getString("VIAGEMID"));
				edVolta.setDt_envio_cfe(res.getString("dt_envio_cfe"));
				edVolta.setDt_encerramento_cfe(res.getString("dt_encerramento_cfe"));
				edVolta.setDt_cancelamento_cfe(res.getString("dt_cancelamento_cfe"));

				edVolta.setVL_Liquido_Ordem_Frete (res.getDouble ("VL_Liquido_Ordem_Frete"));
				edVolta.setVL_Cotacao_Padrao (res.getDouble ("VL_Cotacao_Padrao"));
				edVolta.setNR_Ordem_Frete (res.getLong ("NR_Ordem_Frete"));
				edVolta.setVL_Ordem_Frete (res.getDouble ("VL_Ordem_Frete"));
				edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
				edVolta.setDM_Frete (res.getString ("DM_Frete"));
				edVolta.setDM_Tipo_Pedagio (res.getString ("DM_Tipo_Pedagio"));
				edVolta.setNR_Transacao_Pedagio (res.getString ("NR_Transacao_Pedagio"));
				edVolta.setDM_Impresso (res.getString ("DM_Impresso"));
				edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
				edVolta.setVL_IRRF (res.getDouble ("VL_IRRF"));
				edVolta.setVL_Multa_Atrazo (res.getDouble ("VL_Multa_Atrazo"));
				edVolta.setVL_INSS_Empresa (res.getDouble ("VL_INSS_Empresa"));
				edVolta.setVL_INSS_Prestador (res.getDouble ("VL_INSS_Prestador"));
				edVolta.setVL_Ordem_Frete (res.getDouble ("VL_Ordem_Frete"));
				edVolta.setVL_Pedagio (res.getDouble ("VL_Pedagio"));
				edVolta.setVL_Set_Senat (res.getDouble ("VL_Set_Senat"));
				edVolta.setVL_Coleta (res.getDouble ("VL_Coleta"));
				edVolta.setVL_Carga (res.getDouble ("VL_Carga"));
				edVolta.setVL_Descarga (res.getDouble ("VL_Descarga"));
				edVolta.setVL_Base_Adto (res.getDouble ("VL_Base_Adto"));
				edVolta.setVL_Despesa (res.getDouble ("VL_Despesas"));
				edVolta.setVL_Premio (res.getDouble ("VL_Premio"));
				edVolta.setVL_Vale_Pedagio (res.getDouble ("VL_Vale_Pedagio"));
				edVolta.setVL_Vale_Pedagio_Empresa (res.getDouble ("VL_Vale_Pedagio_Empresa"));
				edVolta.setVL_Outros (res.getDouble ("VL_Outros"));
				edVolta.setVL_INSS_Pago (res.getDouble ("VL_INSS_Pago"));
				edVolta.setVL_Taxa_Expediente (res.getDouble ("VL_Taxa_Expediente"));
				edVolta.setVL_Descontos (res.getDouble ("VL_Descontos"));
				edVolta.setDM_Tipo_Pagamento (res.getString ("DM_Tipo_Pagamento"));
				edVolta.setNM_Pagamento (res.getString ("NM_Pagamento"));
				edVolta.setNR_Qtde_Coleta (res.getLong ("NR_Quantidade_Coleta"));
				edVolta.setNR_Qtde_Entrega (res.getLong ("NR_Quantidade_Entrega"));
				edVolta.setDM_Tipo_Adiantamento(res.getString("DM_Tipo_Adiantamento"));
				edVolta.setDM_Tipo_Pagamento_Adiantamento1(res.getString("DM_Tipo_Pagamento_Adiantamento1"));
				edVolta.setDM_Tipo_Pagamento_Adiantamento2(res.getString("DM_Tipo_Pagamento_Adiantamento2"));
				edVolta.setDM_Tipo_Pagamento_Saldo(res.getString("DM_Tipo_Pagamento_Saldo"));
				edVolta.setDM_Adiantamento(res.getString("DM_Adiantamento"));
				edVolta.setDM_Saldo_Liberado(res.getString("DM_Saldo_Liberado"));

				edVolta.setNR_Posicao(res.getLong ("nr_posicao"));
				edVolta.setNR_Chave(res.getLong ("nr_chave"));

				 //// ("ed.getNR_Chave() record " + ed.getNR_Chave());
				 //// ("ed.getNR_Posicao() record " + ed.getNR_Posicao());

				if (res.getDate ("DT_Pagamento")!=null){
					edVolta.setDT_Pagamento (FormataData.formataDataBT (res.getDate ("DT_Pagamento")));
				}else{
					edVolta.setDT_Pagamento (" ");
				}
				if (res.getString ("NR_Cheque_Adto") != null){
					edVolta.setNR_Cheque_Adto(res.getString ("NR_Cheque_Adto"));
				}else{
					edVolta.setNR_Cheque_Adto(" ");
				}

				edVolta.setOid_Programacao_Carga(res.getLong ("OID_Programacao_Carga"));

				edVolta = this.consultaSituacao(edVolta);

				edVolta.setDM_Compromisso("N");

				sql =" SELECT oid_Compromisso from movimentos_ordens " +
				" WHERE  movimentos_ordens.oid_ordem_frete= '" + ed.getOID_Ordem_Frete() + "'";
				res2 = this.executasql.executarConsulta (sql);
				if (res2.next ()) {
					edVolta.setDM_Compromisso ("S");
				}

				sql =" SELECT tp_tarifa_Pamcary_Transferencia, " +
						" vl_tarifa_Pamcary_Transferencia, " +
						" qt_tarifa_Pamcary_Transferencia, " +
						" vl_tarifa_pamcary, " +
						" qt_tarifa_pamcary, " +
						" tp_tarifa_pamcary " +
						" from empresas ";
				res2 = this.executasql.executarConsulta (sql);
				if (res2.next ()) {
					edVolta.setTpTarifaPamcary(res2.getString("tp_tarifa_pamcary"));
					edVolta.setVlTarifaPamcary(res2.getString("vl_tarifa_pamcary"));
					edVolta.setQtTarifaPamcary(res2.getString("qt_tarifa_pamcary"));
					edVolta.setTpTarifaPamcary_Transferencia(res2.getString("tp_tarifa_Pamcary_Transferencia"));
					edVolta.setVlTarifaPamcary_Transferencia(res2.getString("vl_tarifa_Pamcary_Transferencia"));
					edVolta.setQtTarifaPamcary_Transferencia(res2.getString("qt_tarifa_Pamcary_Transferencia"));
				}

			}
			return edVolta;
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
					"getByRecord(Ordem_FreteED ed)");
		}
	}

	public Ordem_FreteED getByRecord_Plano_Viagem (Ordem_FreteED ed) throws
	Excecoes {

		String sql = null;

		Ordem_FreteED edVolta = new Ordem_FreteED ();

		try {

			sql = " SELECT * from Ordens_Fretes ";
			sql += " WHERE Ordens_Fretes.DM_Frete != 'C'  ";

			if (String.valueOf (ed.getOID_Ordem_Frete ()) != null &&
					!String.valueOf (ed.getOID_Ordem_Frete ()).equals ("0")) {
				sql += " and OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
			}

			ResultSet res = null;
			res = this.executasql.executarConsulta (sql);


			while (res.next ()) {
				edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));
				edVolta.setOID_Unidade (res.getLong ("OID_Unidade"));
				edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
				edVolta.setOID_Veiculo (res.getString ("OID_Veiculo"));
				edVolta.setNR_Placa (res.getString ("OID_Veiculo"));

				edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
				DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
				edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

				edVolta.setNR_Ordem_Frete (res.getLong ("NR_Ordem_Frete"));
				edVolta.setTX_Observacao_Plano (res.getString ("TX_Observacao_Plano"));
				edVolta.setNM_Roteiro1 (res.getString ("NM_Roteiro1"));
				edVolta.setNM_Roteiro2 (res.getString ("NM_Roteiro2"));
				edVolta.setNM_Roteiro3 (res.getString ("NM_Roteiro3"));
				edVolta.setNM_Roteiro4 (res.getString ("NM_Roteiro4"));
				edVolta.setNM_Roteiro5 (res.getString ("NM_Roteiro5"));
				edVolta.setNM_Roteiro6 (res.getString ("NM_Roteiro6"));
				edVolta.setNM_Roteiro7 (res.getString ("NM_Roteiro7"));
				edVolta.setNM_Roteiro8 (res.getString ("NM_Roteiro8"));
				edVolta.setNM_Roteiro9 (res.getString ("NM_Roteiro9"));
				edVolta.setNM_Roteiro10 (res.getString ("NM_Roteiro10"));
				edVolta.setDM_Check1 (res.getString ("DM_Check1"));
				edVolta.setDM_Check2 (res.getString ("DM_Check2"));
				edVolta.setDM_Check3 (res.getString ("DM_Check3"));
				edVolta.setDM_Check4 (res.getString ("DM_Check4"));
				edVolta.setDM_Check5 (res.getString ("DM_Check5"));
				edVolta.setDM_Check6 (res.getString ("DM_Check6"));
				edVolta.setDM_Check7 (res.getString ("DM_Check7"));
				edVolta.setDM_Check8 (res.getString ("DM_Check8"));
				edVolta.setDM_Check9 (res.getString ("DM_Check9"));
				edVolta.setDM_Check10 (res.getString ("DM_Check10"));
				edVolta.setDM_Check11 (res.getString ("DM_Check11"));
				edVolta.setDT_Ponto1 (DataFormatada.getDT_FormataData (res.getString (
				"DT_Ponto1")));
				edVolta.setDT_Ponto2 (DataFormatada.getDT_FormataData (res.getString (
				"DT_Ponto2")));
				edVolta.setDT_Ponto3 (DataFormatada.getDT_FormataData (res.getString (
				"DT_Ponto3")));
				edVolta.setDT_Ponto4 (DataFormatada.getDT_FormataData (res.getString (
				"DT_Ponto4")));
				edVolta.setDT_Ponto5 (DataFormatada.getDT_FormataData (res.getString (
				"DT_Ponto5")));
				edVolta.setDT_Ponto6 (DataFormatada.getDT_FormataData (res.getString (
				"DT_Ponto6")));
				edVolta.setDT_Ponto7 (DataFormatada.getDT_FormataData (res.getString (
				"DT_Ponto7")));
				edVolta.setDT_Ponto8 (DataFormatada.getDT_FormataData (res.getString (
				"DT_Ponto8")));
				edVolta.setHR_Ponto1 (res.getString ("HR_Ponto1"));
				edVolta.setHR_Ponto2 (res.getString ("HR_Ponto2"));
				edVolta.setHR_Ponto3 (res.getString ("HR_Ponto3"));
				edVolta.setHR_Ponto4 (res.getString ("HR_Ponto4"));
				edVolta.setHR_Ponto5 (res.getString ("HR_Ponto5"));
				edVolta.setHR_Ponto6 (res.getString ("HR_Ponto6"));
				edVolta.setHR_Ponto7 (res.getString ("HR_Ponto7"));
				edVolta.setHR_Ponto8 (res.getString ("HR_Ponto8"));
				edVolta.setNM_Ponto1 (res.getString ("NM_Ponto1"));
				edVolta.setNM_Ponto2 (res.getString ("NM_Ponto2"));
				edVolta.setNM_Ponto3 (res.getString ("NM_Ponto3"));
				edVolta.setNM_Ponto4 (res.getString ("NM_Ponto4"));
				edVolta.setNM_Ponto5 (res.getString ("NM_Ponto5"));
				edVolta.setNM_Ponto6 (res.getString ("NM_Ponto6"));
				edVolta.setNM_Ponto7 (res.getString ("NM_Ponto7"));
				edVolta.setNM_Ponto8 (res.getString ("NM_Ponto8"));

			}
		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao Selecionar");
			excecoes.setMetodo ("Registro");
			excecoes.setExc (exc);
			throw excecoes;
		}

		return edVolta;
	}

	public byte[] imprime_Plano_Viagem (Ordem_FreteED ed) throws Excecoes {

		String sql = null;
		byte[] b = null;

		try {

			sql = "Select " +
			" Ordens_Fretes.*," +
			" Veiculos.NR_PLACA," +
			" Modelos_Veiculos.CD_MODELO_VEICULO," +
			" Modelos_Veiculos.NM_MODELO_VEICULO," +
			" Cidade_Veiculo.NM_CIDADE as NM_CIDADE_VEICULO, " +
			" Estado_Veiculo.CD_ESTADO as CD_ESTADO_VEICULO, " +
			" Pessoa_Proprietario.NR_CNPJ_CPF as NR_CNPJ_CPF_PROPRIETARIO, " +
			" Pessoa_Proprietario.NM_RAZAO_SOCIAL as NM_RAZAO_SOCIAL_PROPRIETARIO, " +
			" Pessoa_Proprietario.NM_ENDERECO as NM_ENDERECO_PROPRIETARIO, " +
			" Pessoa_Proprietario.NR_CEP as NR_CEP_PROPRIETARIO, " +
			" Pessoa_Proprietario.NM_BAIRRO as NM_BAIRRO_PROPRIETARIO, " +
			" Cidade_Proprietario.NM_CIDADE as NM_CIDADE_PROPRIETARIO, " +
			" Estado_Proprietario.CD_ESTADO as CD_ESTADO_PROPRIETARIO " +
			" FROM " +
			" Ordens_Fretes, Veiculos, Pessoas Pessoa_Proprietario, " +
			" Cidades Cidade_Proprietario, " +
			" Regioes_Estados Regiao_Estado_Proprietario, " +
			" Estados Estado_Proprietario, " +
			" Modelos_Veiculos, " +
			" Cidades Cidade_Veiculo, " +
			" Regioes_Estados Regiao_Estado_Veiculo, " +
			" Estados Estado_Veiculo " +
			" WHERE Veiculos.OID_PESSOA_PROPRIETARIO = Pessoa_Proprietario.OID_Pessoa " +
			" AND Ordens_Fretes.OID_Veiculo = Veiculos.OID_Veiculo " +
			" AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO " +
			" AND Veiculos.OID_CIDADE = Cidade_Veiculo.OID_CIDADE " +
			" AND Cidade_Veiculo.OID_REGIAO_ESTADO = Regiao_Estado_Veiculo.OID_REGIAO_ESTADO " +
			" AND Regiao_Estado_Veiculo.OID_ESTADO = Estado_Veiculo.OID_ESTADO " +
			" AND Pessoa_Proprietario.OID_CIDADE = Cidade_Proprietario.OID_CIDADE " +
			" AND Cidade_Proprietario.OID_REGIAO_ESTADO = Regiao_Estado_Proprietario.OID_REGIAO_ESTADO " +
			" AND Regiao_Estado_Proprietario.OID_ESTADO = Estado_Proprietario.OID_ESTADO " +
			" AND Ordens_Fretes.VL_Ordem_Frete > 0 " +
			" AND Ordens_Fretes.DM_Impresso = 'N' ";

			sql += " and Ordens_Fretes.OID_ORDEM_FRETE = '" + ed.getOID_Ordem_Frete () +
			"'";

			ResultSet res = null;
			res = this.executasql.executarConsulta (sql.toString ());

			Ordem_FreteRL ordem_FreteRL = new Ordem_FreteRL ();
			b = ordem_FreteRL.imprime_Plano_Viagem (res);

		}

		catch (Exception exc) {
			Excecoes exce = new Excecoes ();
			exce.setExc (exc);
			exce.setMensagem ("Erro ao gerar relat�rio de Acerto de Fretamento");
			exce.setClasse (this.getClass ().getName ());
			exce.setMetodo ("imprime_Ordem_Frete_MT_Acerto(Ordem_FreteED ed)");
		}
		return b;
	}

	public Ordem_FreteED rateio_Ordem_Frete (Ordem_FreteED ed) throws Excecoes {

		// .println("==================>>> rateio_Ordem_Frete parametro_FixoED=>> " + parametro_FixoED.getDM_Gera_Rateio_Custo_Ordem_Frete() );

		String sql = null;
		long usuario_ordem_frete=0;
		try {

			this.exclui_rateio_Ordem_Frete(ed);

			if ("S".equals(parametro_FixoED.getDM_Gera_Rateio_Custo_Ordem_Frete())){

				sql = " SELECT Ordens_Fretes.* , Ordens_Fretes.oid_Pessoa as oid_Pessoa_OF, Ordens_Fretes.oid_Usuario as usuario_ordem_frete, Ordens_Fretes.DM_Tipo_Pagamento AS DM_Tipo_Pagamento_Ordem_Frete, Viagens.DM_Tipo_Viagem, Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.NR_Peso as NR_Peso_CTRC  , Conhecimentos.NR_Peso_Cubado as NR_Peso_Cubado_CTRC";
				sql +=" FROM Viagens, Conhecimentos, Ordens_Manifestos, Ordens_Fretes ";
				sql +=" WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
				sql +=" AND Viagens.OID_Manifesto =  Ordens_Manifestos.oid_Manifesto ";
				sql +=" AND Ordens_Fretes.OID_Ordem_Frete =  Ordens_Manifestos.OID_Ordem_Frete ";
				sql +=" AND Ordens_Manifestos.oid_Ordem_Frete = '" +  ed.getOID_Ordem_Frete () + "'";
				sql +=" ORDER BY Conhecimentos.oid_Conhecimento ";

				// .println ("sql: " + sql);


				ResultSet rs = null;
				rs = this.executasql.executarConsulta (sql);
				String DM_Tipo_Pagamento = "";
				String DM_Tipo_Viagem = "";
				String oid_Fornecedor="";
				double NR_Peso_Total = 0;
				double VL_Movimento = 0 , VL_Ordem_Frete = 0;
				double NR_Peso = 0 , NR_Peso_Cubado = 0;
				double VL_Movimento_Lancado=0;
				int x = 0;
				String oid_Conhecimento="";
				while (rs.next () && x < 9999 ) {
				  if (!oid_Conhecimento.equals(rs.getString("oid_Conhecimento"))){
					x++;
					usuario_ordem_frete=rs.getLong("usuario_ordem_frete");
					oid_Fornecedor=rs.getString("oid_Pessoa_OF");
					DM_Tipo_Pagamento = rs.getString ("DM_Tipo_Pagamento_Ordem_Frete");
					DM_Tipo_Viagem    = rs.getString ("DM_Tipo_Viagem");

					// .println("DM_Tipo_Pagamento: " + DM_Tipo_Pagamento );
					// .println("usuario_ordem_frete: " + usuario_ordem_frete );

					//VL_Ordem_Frete = rs.getDouble ("VL_Ordem_Frete");
					VL_Ordem_Frete = 	rs.getDouble ("VL_Ordem_Frete") +
										rs.getDouble ("VL_Coleta") +
										rs.getDouble ("VL_Carga") +
										rs.getDouble ("VL_Descarga") +
										rs.getDouble ("VL_Premio") +
										rs.getDouble ("VL_Outros") +
										rs.getDouble ("VL_Pedagio") -
										rs.getDouble ("VL_Descontos") -
										rs.getDouble ("vl_set_senat") -
										rs.getDouble ("vl_inss_prestador") +
										rs.getDouble ("vl_inss_empresa") +
										rs.getDouble ("vl_vale_pedagio");

					// .println("VL_Ordem_Frete: " + rs.getDouble ("VL_Ordem_Frete"));
					// .println("VL_Pedagio: " + rs.getDouble ("VL_Pedagio"));
					// .println("NR_Peso_Total: " + NR_Peso_Total );

					NR_Peso = rs.getDouble ("NR_Peso_CTRC");
					NR_Peso_Cubado = rs.getDouble ("NR_Peso_Cubado_CTRC");
					if (NR_Peso_Cubado > NR_Peso) {
						NR_Peso = NR_Peso_Cubado;
					}

					NR_Peso_Total = NR_Peso_Total + NR_Peso;
					// .println("1 CTO : " + rs.getString("NR_Conhecimento") + " Peso : " + rs.getString("NR_Peso_CTRC") + " Peso Calc:" +NR_Peso );
				  }
				  oid_Conhecimento=rs.getString("oid_Conhecimento");
				}

				// .println("oid_Fornecedor: " + oid_Fornecedor );
				// .println("VL_Ordem_Frete: " + VL_Ordem_Frete );
				// .println("NR_Peso_Total: " + NR_Peso_Total );
				// .println("DM_Tipo_Pagamento: " + DM_Tipo_Pagamento );

				if (NR_Peso_Total > 0 && VL_Ordem_Frete > 0) {

					oid_Conhecimento="";

					sql =  " SELECT Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.NR_Peso as NR_Peso_CTRC , Conhecimentos.NR_Peso_Cubado as NR_Peso_Cubado_CTRC, Viagens.DM_Tipo_Viagem ";
					sql += " FROM Viagens, Conhecimentos, Ordens_Manifestos ";
					sql += " WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
					sql += " AND Viagens.OID_Manifesto =  Ordens_Manifestos.oid_Manifesto ";
					sql += " AND Ordens_Manifestos.oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
					sql += " ORDER BY Conhecimentos.OID_Conhecimento ";

					rs = this.executasql.executarConsulta (sql);
					while (rs.next ()) {
					  if (!oid_Conhecimento.equals(rs.getString("oid_Conhecimento"))){

						NR_Peso = rs.getDouble ("NR_Peso_CTRC");

						NR_Peso_Cubado = rs.getDouble ("NR_Peso_Cubado_CTRC");
						if (NR_Peso_Cubado > NR_Peso) {
							NR_Peso = NR_Peso_Cubado;
						}


						VL_Movimento = (VL_Ordem_Frete / NR_Peso_Total * NR_Peso);
						if (VL_Movimento>VL_Ordem_Frete) VL_Movimento=VL_Ordem_Frete;

						// .println("2 CTO --: " + rs.getString("NR_Conhecimento") + " Peso : " + rs.getString("NR_Peso_CTRC") + " Peso Cub : " + rs.getString("NR_Peso_Cubado_CTRC") + " Peso Calc:" +NR_Peso + " VL_Movimento:" +VL_Movimento );

						Movimento_ConhecimentoRN Movimento_Conhecimentorn = new	Movimento_ConhecimentoRN ();
						Movimento_ConhecimentoED edMovimento_Conhecimento = new	Movimento_ConhecimentoED ();



						//DM_Tipo_Viagem = "-------"; //marca p/nao pegar

						// CUSTO PELA ORDEM DE FRETE
						//SETA INICIAL custo transf
						edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long ( parametro_FixoED.getOID_Tipo_Movimento_Custo_Ordem_Frete ()).longValue ());

						if ("C".equals(DM_Tipo_Pagamento)) {
							edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (parametro_FixoED.getOID_Tipo_Movimento_Custo_Coleta ()).longValue ());
							// .println("2 CTO --Coleta");

						}
						//custo entrega
						if ("J".equals(DM_Tipo_Pagamento)) {
							edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (parametro_FixoED.getOID_Tipo_Movimento_Custo_Entrega ()).longValue ());
							// .println("2 CTO --entrega");
						}
						//custo coleta trans entrega
						if ("X".equals(DM_Tipo_Pagamento)) {
							edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (parametro_FixoED.getOID_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega ()).longValue ());
							// .println("2 CTO --coleta trans entrega");
						}


						// CUSTO PELA VIAGEM (MANIF)

						DM_Tipo_Viagem    = rs.getString ("DM_Tipo_Viagem");

						//custo coleta
						if ("C".equals(DM_Tipo_Viagem)) {
							edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (parametro_FixoED.getOID_Tipo_Movimento_Custo_Coleta ()).longValue ());
							// .println("2 CTO -VIAGEM-Coleta");
						}

						//custo entrega
						if ("E".equals(DM_Tipo_Viagem)) {
							edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (parametro_FixoED.getOID_Tipo_Movimento_Custo_Entrega ()).longValue ());
							// .println("2 CTO -VIAGEM-entrega");
						}

						//custo coleta trans entrega
						if ("X".equals(DM_Tipo_Viagem)) {
							edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (parametro_FixoED.getOID_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega ()).longValue ());
							// .println("2 CTO -VIAGEM-coleta trans entrega");
						}

						//custo trans entrega
						if ("R".equals(DM_Tipo_Viagem)) {
							edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (parametro_FixoED.getOID_Tipo_Movimento_Custo_Transferencia_Entrega ()).longValue ());
							// .println("2 CTO -VIAGEM-transf entrega");
						}

						//custo coleta trans
						if ("A".equals(DM_Tipo_Viagem)) {
							edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (parametro_FixoED.getOID_Tipo_Movimento_Custo_Coleta_Transferencia ()).longValue ());
							// .println("2 CTO -VIAGEM-coleta transf");
						}

						//custo reentrega
						if ("Y".equals(DM_Tipo_Viagem)) {
							edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (parametro_FixoED.getOID_Tipo_Movimento_Custo_Reentrega ()).longValue ());
							// .println("2 CTO -VIAGEM - reentrega");
						}


						// .println("3 CTO --: " + rs.getString("NR_Conhecimento") + " Peso : " + rs.getString("NR_Peso_CTRC") + " Peso Cub : " + rs.getString("NR_Peso_Cubado_CTRC") + " Peso Calc:" +NR_Peso + " VL_Movimento:" +VL_Movimento );

						edMovimento_Conhecimento.setOID_Fornecedor(oid_Fornecedor);

						edMovimento_Conhecimento.setVL_Movimento (new Double (VL_Movimento).doubleValue ());
						edMovimento_Conhecimento.setDT_Movimento_Conhecimento (Data.getDataDMY ());
						edMovimento_Conhecimento.setHR_Movimento_Conhecimento (Data.getHoraHM ());

						edMovimento_Conhecimento.setOID_Ordem_Frete (ed.getOID_Ordem_Frete ());
						edMovimento_Conhecimento.setOID_Conhecimento (rs.getString ("OID_Conhecimento"));

						edMovimento_Conhecimento.setOID_Usuario(usuario_ordem_frete);

						// .println("4 CTO --: " + rs.getString("NR_Conhecimento") + " Peso : " + rs.getString("NR_Peso_CTRC") + " Peso Cub : " + rs.getString("NR_Peso_Cubado_CTRC") + " Peso Calc:" +NR_Peso + " VL_Movimento:" +VL_Movimento );

						edMovimento_Conhecimento.setDM_Tipo_Movimento ("D");
						edMovimento_Conhecimento.setDM_Lancado_Gerado ("R");
						edMovimento_Conhecimento.setNM_Pessoa_Entrega ("");

						VL_Movimento_Lancado+=	VL_Movimento;

						// .println("99 CTO : " + rs.getString("NR_Conhecimento") + " Peso : " + rs.getString("NR_Peso_CTRC") + " Peso Cub : " + rs.getString("NR_Peso_Cubado_CTRC") + " Peso Calc:" +NR_Peso + " VL_Movimento:" +VL_Movimento );
						// .println("VL_Movimento_Lancado =>>> " + VL_Movimento_Lancado);

						sql = " SELECT Movimentos_Conhecimentos.oid_Movimento_Conhecimento  " +
						      " FROM   Movimentos_Conhecimentos " +
						      " WHERE  Movimentos_Conhecimentos.oid_Conhecimento ='" + edMovimento_Conhecimento.getOID_Conhecimento () + "'" +
						      " AND    Movimentos_Conhecimentos.oid_Tipo_Movimento = '" + edMovimento_Conhecimento.getOID_Tipo_Movimento () + "'";
						// .println(sql);
						ResultSet resMovCto = this.executasql.executarConsulta (sql);
						if (resMovCto.next ()) {
							sql = " UPDATE Movimentos_Conhecimentos SET oid_Ordem_Frete='" + ed.getOID_Ordem_Frete () + "' WHERE oid_Movimento_Conhecimento=" + resMovCto.getString("oid_Movimento_Conhecimento");
							// .println(sql);
							executasql.executarUpdate (sql);
						}else {
							Movimento_Conhecimentorn.inclui (edMovimento_Conhecimento);
						}
				     }
					 oid_Conhecimento=rs.getString("oid_Conhecimento");
				   }
				}
			}

		}
		catch (Excecoes e) {
			throw e;
		}
		catch (Exception exc) {
			Excecoes exce = new Excecoes ();
			exce.setExc (exc);
			exce.setMensagem ("Erro rateio_Ordem_Frete");
			exce.setClasse (this.getClass ().getName ());
			exce.setMetodo ("rateio_Ordem_Frete(Ordem_FreteED ed)");
		}
		return ed;
	}

	public Ordem_FreteED exclui_rateio_Ordem_Frete (Ordem_FreteED ed) throws
	Excecoes {

		String sql = null;

		try {
			// .println ("Cancela exclui_rateio_Ordem_Frete of->>" +ed.getOID_Ordem_Frete ());

			sql = " SELECT oid_Conhecimento, oid_Movimento_Conhecimento " +
			" FROM   Movimentos_Conhecimentos, Ordens_Fretes " +
			" WHERE  Movimentos_Conhecimentos.oid_Ordem_Frete = Ordens_Fretes.oid_Ordem_Frete " +
			" AND    Ordens_Fretes.oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
			ResultSet rs = null;
			rs = this.executasql.executarConsulta (sql);
			while (rs.next ()) {
				// .println ("Cancela exclui_rateio_Ordem_Frete     cto->>" + rs.getString ("OID_Conhecimento"));
				// .println ("Cancela exclui_rateio_Ordem_Frete mov cto->>" + rs.getString ("oid_Movimento_Conhecimento"));
				Movimento_ConhecimentoRN Movimento_Conhecimentorn = new Movimento_ConhecimentoRN ();
				Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();
				edMovimento_Conhecimento.setOID_Conhecimento (rs.getString ("OID_Conhecimento"));
				edMovimento_Conhecimento.setOID_Movimento_Conhecimento (rs.getString ("oid_Movimento_Conhecimento"));
				Movimento_Conhecimentorn.deleta (edMovimento_Conhecimento);
			}
		}

		catch (Excecoes e) {
			throw e;
		}
		catch (Exception exc) {
			Excecoes exce = new Excecoes ();
			exce.setExc (exc);
			exce.setMensagem ("Erro ao excluir rateio.");
			exce.setClasse (this.getClass ().getName ());
			exce.setMetodo ("exclui_rateio_Ordem_Frete(Ordem_FreteED ed)");
		}
		return ed;
	}

	public ArrayList Master_Lista (Ordem_FreteED ed) throws Excecoes {

		String sql = null;
		ArrayList list = new ArrayList ();

		try {
			sql = "SELECT DM_Impresso, OID_Ordem_Frete, OID_VEICULO, DT_Emissao, NR_Master, VL_Ordem_Frete, Pessoa_Unidade.NM_Fantasia, Pessoa_Empresa.NM_Razao_Social from Ordens_Fretes, Unidades, Pessoas Pessoa_Unidade, Pessoas Pessoa_Empresa ";
			sql += " WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade ";
			sql += " AND Unidades.OID_Pessoa = Pessoa_Unidade.OID_Pessoa ";
			sql += " AND Ordens_Fretes.OID_Pessoa = Pessoa_Empresa.OID_Pessoa ";
			//sql += " and Ordens_Fretes.DM_Frete = 'M' ";

			if (String.valueOf (ed.getDT_Emissao ()) != null &&
					!String.valueOf (ed.getDT_Emissao ()).equals ("null") &&
					!String.valueOf (ed.getDT_Emissao ()).equals ("")) {
				sql += " and Ordens_Fretes.DT_Emissao = '" + ed.getDT_Emissao () + "'";
			}

			if (String.valueOf (ed.getOID_Pessoa ()) != null &&
					!String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
					!String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
				sql += " and Ordens_Fretes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
			}

			if (String.valueOf (ed.getNR_Master ()) != null &&
					!String.valueOf (ed.getNR_Master ()).equals ("0") &&
					!String.valueOf (ed.getNR_Master ()).equals ("null")) {
				sql += " and Ordens_Fretes.NR_Master = '" + ed.getNR_Master () + "'";
			}

			if (String.valueOf (ed.getOID_Unidade ()) != null &&
					!String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
					!String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
				sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
			}

			sql += " Order by Ordens_Fretes.DT_Emissao,Ordens_Fretes.NR_Master ";

			//.println(sql);


			ResultSet res = null;
			res = this.executasql.executarConsulta (sql);


			//popula
			String NR_Master="";
			while (res.next ()) {
				if (!NR_Master.equals(res.getString ("NR_Master"))) {
					Ordem_FreteED edVolta = new Ordem_FreteED ();

					edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));
					edVolta.setNR_Master (" ");
					if (res.getString ("NR_Master")!=null && !res.getString ("NR_Master").equals("null")) {
						edVolta.setNR_Master (res.getString ("NR_Master"));
					}
					edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
					DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
					edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());
					edVolta.setVL_Ordem_Frete (res.getDouble ("VL_Ordem_Frete"));
					edVolta.setNM_Unidade (res.getString ("NM_Fantasia"));
					edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));

					list.add (edVolta);
				}
				NR_Master=res.getString ("NR_Master");
			}
		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao listar Ordem Frete");
			excecoes.setMetodo ("lista");
			excecoes.setExc (exc);
			throw excecoes;
		}

		return list;
	}

	public Ordem_FreteED Master_inclui (Ordem_FreteED ed) throws Excecoes {

		String sql = null;
		long valOid = 0;
		String chave = null;

		long NR_Proximo = 0;
		long NR_Final = 0;

		Ordem_FreteED ordem_FreteED = new Ordem_FreteED ();

		try {

			if (String.valueOf (ed.getNR_Ordem_Frete ()) != null &&
					String.valueOf (ed.getNR_Ordem_Frete ()).equals ("0")) {

				sql =
					" SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
				sql += " FROM AIDOF ";
				sql += " WHERE AIDOF.NM_Serie = 'OFM' ";
				ResultSet rs = null;
				rs = this.executasql.executarConsulta (sql);

				while (rs.next ()) {
					ed.setNM_Serie (rs.getString ("NM_Serie"));
					ed.setNR_Ordem_Frete (rs.getLong ("NR_Proximo"));
					valOid = rs.getLong ("OID_AIDOF");
					NR_Proximo = rs.getLong ("NR_Proximo") + 1;
					NR_Final = rs.getLong ("NR_FINAL");
				}

				if (NR_Proximo > NR_Final) {
					Excecoes exc = new Excecoes ();
					throw exc;
				}

				sql = " UPDATE AIDOF SET NR_Proximo= " + (ed.getNR_Ordem_Frete () + 1);
				sql += " WHERE OID_AIDOF = " + valOid;

				int u = executasql.executarUpdate (sql);
			}

			chave = String.valueOf (ed.getOID_Unidade ()) +
			String.valueOf (ed.getNR_Ordem_Frete ()) +
			ed.getNM_Serie ();

			ed.setOID_Ordem_Frete (chave);

			sql = " insert into Ordens_Fretes (" +
			"OID_Ordem_Frete, " +
			"OID_Pessoa, " +
			"DM_Impresso, " +
			"DT_Emissao, " +
			"OID_Unidade, " +
			"NR_Ordem_Frete, " +
			"NR_Master, " +
			"VL_ICMS, " +
			"PE_ICMS, " +
			"DT_Voo, " +
			"HR_Voo, " +
			"NR_Voo, " +
			"VL_Ordem_Frete, NR_Peso_Master, " +
			"VL_Saldo, DM_Frete ) values (";

			sql += ed.getOID_Ordem_Frete () == null ? "null," :
				"'" + ed.getOID_Ordem_Frete () + "',";
			sql += ed.getOID_Pessoa () == null ? "null," :
				"'" + ed.getOID_Pessoa () + "',";
			sql += ed.getDM_Impresso () == null ? "null," :
				"'" + ed.getDM_Impresso () + "',";
			sql += ed.getDT_Emissao () == null ? "null," :
				"'" + ed.getDT_Emissao () + "',";
			sql += ed.getOID_Unidade () + ",";
			sql += ed.getNR_Ordem_Frete () + ",'";
			sql += ed.getNR_Master () + "',";
			sql += ed.getVL_ICMS () + ",";
			sql += ed.getPE_ICMS () + ",";
			sql += ed.getDT_Voo () == null ? "null," : "'" + ed.getDT_Voo () + "',";
			sql += ed.getHR_Voo () == null ? "null," : "'" + ed.getHR_Voo () + "',";
			sql += ed.getNR_Voo () == null ? "null," : "'" + ed.getNR_Voo () + "',";
			sql += ed.getVL_Ordem_Frete () + ",";
			sql += ed.getNR_Peso_Master () + ",";
			sql += ed.getVL_Ordem_Frete () + ",";
			sql += "'M')";

			int i = executasql.executarUpdate (sql);

			ordem_FreteED.setOID_Ordem_Frete (ed.getOID_Ordem_Frete ());

			if (ed.getOID_Manifesto () != null && !ed.getOID_Manifesto ().equals ("") &&
					!ed.getOID_Manifesto ().equals ("null")) {
				chave = (ed.getOID_Manifesto () + ed.getOID_Ordem_Frete ());

				sql = " insert into Ordens_Manifestos (OID_Ordem_Manifesto, OID_Manifesto, OID_Ordem_Frete ) values ";
				sql += "('" + chave + "','" + ed.getOID_Manifesto () + "','" +
				ed.getOID_Ordem_Frete () + "')";

				i = executasql.executarUpdate (sql);

				sql = " update Manifestos set DM_Lancado_Ordem_Frete= 'S'" +
				" where oid_Manifesto = '" + ed.getOID_Manifesto () + "'";
				i = executasql.executarUpdate (sql);

				if (parametro_FixoED.getDM_Gera_Ocorrencia_Viagem ().equals ("S")) {

					sql = " SELECT Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.NR_Peso as NR_Peso_CTRC";
					sql += " FROM Viagens, Conhecimentos, Manifestos ";
					sql +=
						" WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
					sql += " AND Viagens.OID_Manifesto = Manifestos.OID_Manifesto ";
					sql += " AND Manifestos.OID_Manifesto = '" + ed.getOID_Manifesto () +
					"'";

					ResultSet rs = null;
					rs = this.executasql.executarConsulta (sql);
					double VL_Movimento = 0;
					double NR_Peso = 0;

					while (rs.next ()) {

						Ocorrencia_ConhecimentoRN Ocorrencia_Conhecimentorn = new
						Ocorrencia_ConhecimentoRN ();
						Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new
						Ocorrencia_ConhecimentoED ();

						sql = " SELECT  * from Tipos_Ocorrencias Where DM_Tipo='V' ";
						ResultSet res2 = null;
						res2 = this.executasql.executarConsulta (sql);
						String TX_Observacao = "incluindo viagem";
						while (res2.next ()) {
							ocorrencia_ConhecimentoED.setDT_Ocorrencia_Conhecimento (Data.
									getDataDMY ());
							ocorrencia_ConhecimentoED.setHR_Ocorrencia_Conhecimento (Data.
									getHoraHM ());
							ocorrencia_ConhecimentoED.setOID_Tipo_Ocorrencia (res2.getLong (
									"oid_Tipo_Ocorrencia"));
							ocorrencia_ConhecimentoED.setOID_Conhecimento (rs.getString (
							"OID_Conhecimento"));
							ocorrencia_ConhecimentoED.setTX_Observacao (TX_Observacao);
							ocorrencia_ConhecimentoED.setNM_Pessoa_Entrega ("");
							ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Tipo"));
							ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Acesso"));
							ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Avaria"));
							ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Status"));
							Ocorrencia_Conhecimentorn.inclui (ocorrencia_ConhecimentoED);
						}
					}

				}

			}

			if (parametro_FixoED.getDM_Gera_Compromisso_Master ().equals ("S")) {
				if (ed.getVL_Ordem_Frete () > 0) {
					ed.setDT_Vencimento (ed.getDT_Emissao ());
					ed.setNR_Parcela (new Integer ("1"));
					ed.setVL_Compromisso (ed.getVL_Ordem_Frete ());
					this.Master_geraCompromisso (ed);
				}
			}
		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			if (NR_Proximo > NR_Final) {
				excecoes.setMensagem ("AIDOF sem numera��o dispon�vel");
			}
			else {
				excecoes.setMensagem ("Erro ao incluir Ordem de Frete");
			}
			excecoes.setMetodo ("inclui");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return ordem_FreteED;

	}

	public Ordem_FreteED Master_rateio (Ordem_FreteED ed) throws Excecoes {

		String sql = null;

		long NR_Proximo = 0;
		long NR_Final = 0;
		int  QT_Ctos=0;
		String DM_Tipo_Viagem="";

		Ordem_FreteED ordem_FreteED = new Ordem_FreteED ();

		try {

			sql = " SELECT COUNT (Conhecimentos.oid_Conhecimento) as qt_ctos " +
			" FROM Viagens, Conhecimentos, Manifestos " +
			" WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento "+
			" AND Viagens.OID_Manifesto = Manifestos.OID_Manifesto " +
			" AND Manifestos.OID_Manifesto = '" + ed.getOID_Manifesto () + "'";
			// .println(sql);

			ResultSet rs = this.executasql.executarConsulta (sql);
			if (rs.next ()) {
				QT_Ctos=rs.getInt("qt_ctos");
			}

			// .println("QT_Ctos="+QT_Ctos);

			sql = " SELECT Conhecimentos.OID_Conhecimento, "+
			" Conhecimentos.NR_Conhecimento, "  +
			" Conhecimentos.NR_Peso_Cubado as NR_Peso_Cubado, " +
			" Viagens.DM_Tipo_Viagem, " +
			" Conhecimentos.NR_Peso as NR_Peso_CTRC" +
			" FROM Viagens, Conhecimentos, Manifestos " +
			" WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento "+
			" AND   Viagens.OID_Manifesto = Manifestos.OID_Manifesto " +
			" AND   Manifestos.OID_Manifesto = '" + ed.getOID_Manifesto () + "'" +
			" ORDER BY Conhecimentos.OID_Conhecimento ";

			rs = this.executasql.executarConsulta (sql);
			double VL_Movimento = 0;
			double NR_Peso = 0;
			String oid_Conhecimento="";
			while (rs.next ()) {
			  if (!oid_Conhecimento.equals(rs.getString("oid_Conhecimento"))){

				// .println(" w2 ");
				DM_Tipo_Viagem    = rs.getString ("DM_Tipo_Viagem");

				NR_Peso = rs.getDouble ("NR_Peso_CTRC");
				if (rs.getDouble ("NR_Peso_CTRC")<rs.getDouble ("NR_Peso_Cubado")) {
					NR_Peso = rs.getDouble ("NR_Peso_Cubado");
				}

				VL_Movimento = ed.getVL_Ordem_Frete ();
				if (QT_Ctos>1){
					VL_Movimento = (ed.getVL_Ordem_Frete () / ed.getNR_Total_Peso_CTRC () * NR_Peso);
				}

				Movimento_ConhecimentoRN Movimento_Conhecimentorn = new  Movimento_ConhecimentoRN ();
				Movimento_ConhecimentoED edMovimento_Conhecimento = new  Movimento_ConhecimentoED ();

				edMovimento_Conhecimento.setOID_Fornecedor (ed.getOID_Pessoa ());


				DM_Tipo_Viagem = rs.getString ("DM_Tipo_Viagem");
				// .println ("DM_Tipo_Viagem="+DM_Tipo_Viagem);

				edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (parametro_FixoED.getOID_Tipo_Movimento_Custo_Master ()).longValue ());

				if ("W".equals (DM_Tipo_Viagem)) {
					edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (parametro_FixoED.getOID_Tipo_Movimento_Custo_Devolucao ()).longValue ());
					// .println ("2 CTO -VIAGEM-Devolucao");
				}

				edMovimento_Conhecimento.setVL_Movimento (new Double (VL_Movimento).doubleValue ());
				edMovimento_Conhecimento.setDT_Movimento_Conhecimento (Data.getDataDMY ());
				edMovimento_Conhecimento.setHR_Movimento_Conhecimento (Data.getHoraHM ());
				edMovimento_Conhecimento.setOID_Conhecimento (rs.getString ("OID_Conhecimento"));
				edMovimento_Conhecimento.setOID_Ordem_Frete(ed.getOID_Ordem_Frete());
				edMovimento_Conhecimento.setDM_Tipo_Movimento ("D");
				edMovimento_Conhecimento.setDM_Lancado_Gerado ("M");
				edMovimento_Conhecimento.setNM_Pessoa_Entrega ("");
				edMovimento_Conhecimento.setNR_Documento (ed.getNR_Master ());

				Movimento_Conhecimentorn.inclui (edMovimento_Conhecimento);
			  }
			  oid_Conhecimento=rs.getString("oid_Conhecimento");
			}
			if (parametro_FixoED.getDM_Gera_Compromisso_Master ().equals ("S")) {
				if (ed.getVL_Ordem_Frete () > 0) {
					ed.setDT_Vencimento (ed.getDT_Emissao ());
					ed.setNR_Parcela (new Integer ("1"));
					ed.setVL_Compromisso (ed.getVL_Ordem_Frete ());
					this.Master_geraCompromisso (ed);
				}
			}
		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			if (NR_Proximo > NR_Final) {
				excecoes.setMensagem ("AIDOF sem numera��o dispon�vel");
			}
			else {
				excecoes.setMensagem ("Erro ao incluir Ordem de Frete");
			}
			excecoes.setMetodo ("inclui");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return ordem_FreteED;

	}

	public void Master_geraCompromisso (Ordem_FreteED ed) throws Excecoes {

		String sql = null;
		String chave = null;

		Movimento_OrdemED Movimento_OrdemED = new Movimento_OrdemED ();
		Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

		CompromissoED compromissoED = new CompromissoED ();
		CompromissoED compromissoVoltaED = new CompromissoED ();
		CompromissoBD compromissoBD = new CompromissoBD (this.executasql);

		try {
			compromissoED.setDt_Vencimento (ed.getDT_Emissao ());
			compromissoED.setDt_Entrada (ed.getDT_Emissao ());
			compromissoED.setNr_Parcela (ed.getNR_Parcela ());
			compromissoED.setVl_Compromisso (new Double (ed.getVL_Compromisso ()));
			compromissoED.setVl_Saldo (new Double (ed.getVL_Compromisso ()));

			compromissoED.setOid_Pessoa (ed.getOID_Pessoa ());
			compromissoED.setDt_Emissao (ed.getDT_Emissao ());
			compromissoED.setNr_Documento(String.valueOf(ed.getNR_Ordem_Frete ()));
			compromissoED.setOid_Unidade (new Long (ed.getOID_Unidade ()));
			compromissoED.setOid_Centro_Custo (new Integer (parametro_FixoED.
					getOID_Centro_Custo_Master ()));
			compromissoED.setOid_Conta (new Integer (parametro_FixoED.
					getOID_Conta_Debito_Master ()));
			compromissoED.setOid_Conta_Credito (new Integer (parametro_FixoED.
					getOID_Conta_Credito_Master ()));
			compromissoED.setDM_Tipo_Pagamento ("1");
			compromissoED.setOid_Tipo_Documento (new Integer (parametro_FixoED.
					getOID_Tipo_Documento_Master ()));

			compromissoED.setOid_Natureza_Operacao (new Integer (parametro_FixoED.
					getOID_Natureza_Operacao_Master ()));
			compromissoVoltaED = compromissoBD.inclui (compromissoED);
			compromissoED.setOid_Compromisso (compromissoVoltaED.getOid_Compromisso ());

			chave = String.valueOf (ed.getOID_Ordem_Frete ()) +
			String.valueOf (compromissoED.getOid_Compromisso ());
			Movimento_OrdemED.setOID_Movimento_Ordem (chave);
			Movimento_OrdemED.setDT_Emissao (ed.getDT_Emissao ());
			Movimento_OrdemED.setHR_Movimento_Ordem (ed.getHR_Emissao ());
			Movimento_OrdemED.setDT_Movimento_Ordem (ed.getDT_Emissao ());
			Movimento_OrdemED.setOID_Compromisso (compromissoED.getOid_Compromisso ());
			Movimento_OrdemED.setOID_Ordem_Frete (ed.getOID_Ordem_Frete ());
			Movimento_OrdemED.setDt_stamp (ed.getDt_stamp ());
			Movimento_OrdemED.setUsuario_Stamp (ed.getUsuario_Stamp ());
			Movimento_OrdemED.setDm_Stamp (ed.getDm_Stamp ());

			sql = " insert into Movimentos_Ordens (OID_Movimento_Ordem, OID_Compromisso, OID_Ordem_Frete, DT_Movimento_Ordem, HR_Movimento_Ordem ) values ";
			sql += "('" + Movimento_OrdemED.getOID_Movimento_Ordem () + "','" +
			Movimento_OrdemED.getOID_Compromisso () + "','" +
			Movimento_OrdemED.getOID_Ordem_Frete () + "','" +
			Movimento_OrdemED.getDT_Movimento_Ordem () + "','" +
			Movimento_OrdemED.getHR_Movimento_Ordem () + "')";

			int i = executasql.executarUpdate (sql);
		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao gerando movimento ordem");
			excecoes.setMetodo ("geraCompromisso(Ordem_FreteED");
			excecoes.setExc (exc);
			throw excecoes;
		}

	}

	public Ordem_FreteED Master_getByRecord (Ordem_FreteED ed) throws Excecoes {

		String sql = null;

		Ordem_FreteED edVolta = new Ordem_FreteED ();

		try {

			sql = " SELECT * from Ordens_Fretes, Pessoas ";
			sql += " WHERE Ordens_Fretes.oid_Pessoa = Pessoas.Oid_Pessoa and Ordens_Fretes.DM_Frete = 'M'  ";

			if (String.valueOf (ed.getOID_Ordem_Frete ()) != null &&
					!String.valueOf (ed.getOID_Ordem_Frete ()).equals ("") &&
					!String.valueOf (ed.getOID_Ordem_Frete ()).equals ("0") &&
					!String.valueOf (ed.getOID_Ordem_Frete ()).equals ("null")) {
				sql += " and OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
			}

			if (String.valueOf (ed.getNR_Master ()) != null &&
					!String.valueOf (ed.getNR_Master ()).equals ("0") &&
					!String.valueOf (ed.getNR_Master ()).equals ("") &&
					!String.valueOf (ed.getNR_Master ()).equals ("null")) {
				sql += " and NR_Master = '" + ed.getNR_Master () + "' ORDER BY Ordens_Fretes.DT_Emissao";
			}
			ResultSet res = null;
			res = this.executasql.executarConsulta (sql);


			while (res.next ()) {

				edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));
				edVolta.setOID_Unidade (res.getLong ("OID_Unidade"));
				edVolta.setOID_Lote_Fornecedor (res.getLong ("OID_Lote_Fornecedor"));

				edVolta.setOID_Pessoa (res.getString ("NR_CNPJ_CPF"));
				edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));

				edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
				DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
				edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

				edVolta.setVL_ICMS (res.getDouble ("VL_ICMS"));
				edVolta.setPE_ICMS (res.getDouble ("PE_ICMS"));
				edVolta.setVL_Saldo (res.getDouble ("VL_Saldo"));

				edVolta.setNR_Peso_Master (res.getDouble ("NR_Peso_Master"));

				edVolta.setNR_Ordem_Frete (res.getLong ("NR_Ordem_Frete"));
				edVolta.setVL_Ordem_Frete (res.getDouble ("VL_Ordem_Frete"));
				edVolta.setDM_Impresso (res.getString ("DM_Impresso"));
				edVolta.setVL_Ordem_Frete (res.getDouble ("VL_Ordem_Frete"));

				edVolta.setNR_Master (res.getString ("NR_Master"));

				edVolta.setDT_Voo (res.getString ("DT_Voo"));
				DataFormatada.setDT_FormataData (edVolta.getDT_Voo ());
				edVolta.setDT_Voo (DataFormatada.getDT_FormataData ());

				edVolta.setHR_Voo (res.getString ("HR_Voo"));
				edVolta.setNR_Voo (res.getString ("NR_Voo"));

			}
		}
		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao Selecionar");
			excecoes.setMetodo ("Registro");
			excecoes.setExc (exc);
			throw excecoes;
		}

		return edVolta;
	}

	public String imprime_Ordem_Frete_Matricial (Ordem_FreteED ed)
	throws Excecoes {
			String toReturn="";
			ResultSet res = this.imprime_Ordem_Frete(ed);
			try {
				toReturn = new Ordem_FreteRL().imprime_Ordem_Frete_Matricial(res, executasql);
			} finally {
				util.closeResultset(res);
			}
			return toReturn;
	}

	public String imprime_Ordem_Frete_Adiantamento_Matricial (Ordem_FreteED ed)
	throws Excecoes {
			String toReturn="";
			ResultSet res = null;
			try {
				res = this.seleciona_Ordem_Frete_Adiantamento_Matricial(ed);
				toReturn = new Ordem_FreteRL().imprime_Ordem_Frete_Adiantamento_Matricial(res, executasql);
			}
			catch (Exception exc) {
				Excecoes excecoes = new Excecoes ();
				excecoes.setClasse (this.getClass ().getName ());
				excecoes.setMensagem ("Erro ao Selecionar");
				excecoes.setMetodo ("Registro");
				excecoes.setExc (exc);
				throw excecoes;
			} finally {
				util.closeResultset(res);
			}
			return toReturn;
	}

	public byte[] imprime_Ordem_Frete_PDF (Ordem_FreteED ed)
	throws Excecoes {


			byte[] b=null;
			ResultSet res = this.imprime_Ordem_Frete(ed);

			try {
				b = new Ordem_FreteRL().imprime_Ordem_Frete_PDF(res);
			} finally {
				util.closeResultset(res);
			}
			return b;


	}

	private ResultSet imprime_Ordem_Frete (Ordem_FreteED ed)
	throws Excecoes {

		String sql = null;
		long valOid = 0;
		long NR_Proximo = 0;
		long NR_Final = 0;
		Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

		try {

			// .println ("imprime_Ordem_Frete 1" );

			sql = "Select * " +
			" from " +
			" Ordens_Fretes " +
			" where " +
			" Ordens_Fretes.DM_Impresso = 'N'  " +
			" AND Ordens_Fretes.DM_Frete <> 'T'  "+
			" AND Ordens_Fretes.VL_ORDEM_FRETE > 0";

			if (String.valueOf (ed.getNR_Ordem_Frete ()) != null &&
					!String.valueOf (ed.getNR_Ordem_Frete ()).equals ("0")) {
				sql += " and Ordens_Fretes.NR_ORDEM_FRETE = " + ed.getNR_Ordem_Frete ();
			}
			if (String.valueOf (ed.getOID_Unidade ()) != null &&
					!String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
				sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
			}
			if (util.doValida (ed.getOID_Ordem_Frete ())) {
				sql += " and Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () +
				"'";
			}

			ResultSet resCTRC = this.executasql.executarConsulta (sql);
			try {
				while (resCTRC.next ()) {
					// .println ("imprime_Ordem_Frete 2" );

					ed.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
					ed.setOID_Ordem_Frete (resCTRC.getString ("OID_Ordem_Frete"));

					sql = " SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie, AIDOF.DM_FORMULARIO ";
					sql += " FROM Parametros_Filiais, AIDOF ";
					sql +=
						" WHERE Parametros_Filiais.OID_AIDOF_ORDEM_FRETE = AIDOF.OID_AIDOF ";
					sql += " AND Parametros_Filiais.OID_Unidade = " + ed.getOID_Unidade ();

					ResultSet resTP = this.executasql.executarConsulta (sql.toString ());
					try {
						while (resTP.next ()) {
							ed.setNR_Ordem_Frete (resTP.getLong ("NR_Proximo"));
							valOid = resTP.getLong ("OID_AIDOF");
							NR_Proximo = resTP.getLong ("NR_Proximo");
							NR_Final = resTP.getLong ("NR_FINAL");
						}
					} finally {
						try {
							resTP.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						try {
							resTP.getStatement().close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					// .println ("imprime_Ordem_Frete 3" );

					if (NR_Proximo > NR_Final) {
						throw new Excecoes ("AIDOF sem numera��o dispon�vel");
					}

					sql = " UPDATE AIDOF SET NR_Proximo= " + (ed.getNR_Ordem_Frete () + 1);
					sql += " WHERE OID_AIDOF = " + valOid;

					int u = executasql.executarUpdate (sql);

					// .println ("imprime_Ordem_Frete 4" );

					sql = " update Ordens_Fretes set DM_Impresso='S', " +
					" NR_ORDEM_FRETE = " + ed.getNR_Ordem_Frete () + ", DT_Emissao = '" + Data.getDataDMY () + "'" +
					" where Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () +"'";

					u = executasql.executarUpdate (sql);

					// .println ("imprime_Ordem_Frete 5" );

					if (parametro_FixoED.getDM_Gera_Compromisso_Ordem_Frete ().equals ("S")) {
						this.atualiza_Compromisso_Ordem_Frete (ed);
					}

					if ("SFFFFF".equals(parametro_FixoED.getDM_Gera_Lancamento_Contabil()))
					{
						//faz os lancamentos contabeis do movimento
						new Lancamento_ContabilBD(this.executasql).inclui_CTB_Ordem_Frete(ed);
					}

				}
			} finally {
				util.closeResultset(resCTRC);
			}
			// .println ("imprime_Ordem_Frete 7" );

			sql = "Select " +
			" Ordens_Fretes.*," +
			" Veiculos.NR_PLACA," +
			" Modelos_Veiculos.CD_MODELO_VEICULO," +
			" Modelos_Veiculos.NM_MODELO_VEICULO," +
			" Cidade_Veiculo.NM_CIDADE as NM_CIDADE_VEICULO, " +
			" Estado_Veiculo.CD_ESTADO as CD_ESTADO_VEICULO, " +
			" Pessoa_Proprietario.NR_CNPJ_CPF as NR_CNPJ_CPF_PROPRIETARIO, " +
			" Pessoa_Proprietario.NM_RAZAO_SOCIAL as NM_RAZAO_SOCIAL_PROPRIETARIO, " +
			" Pessoa_Proprietario.NM_ENDERECO as NM_ENDERECO_PROPRIETARIO, " +
			" Pessoa_Proprietario.NR_CEP as NR_CEP_PROPRIETARIO, " +
			" Pessoa_Proprietario.NM_BAIRRO as NM_BAIRRO_PROPRIETARIO, " +
			" Pessoa_Proprietario.NR_TELEFONE as NR_TELEFONE_PROPRIETARIO, " +
			" Cidade_Proprietario.NM_CIDADE as NM_CIDADE_PROPRIETARIO, " +
			" Estado_Proprietario.CD_ESTADO as CD_ESTADO_PROPRIETARIO " +
			" FROM " +
			" Ordens_Fretes, Veiculos, Pessoas Pessoa_Proprietario, " +
			" Cidades Cidade_Proprietario, " +
			" Regioes_Estados Regiao_Estado_Proprietario, " +
			" Estados Estado_Proprietario, " +
			" Modelos_Veiculos, " +
			" Cidades Cidade_Veiculo, " +
			" Regioes_Estados Regiao_Estado_Veiculo, " +
			" Estados Estado_Veiculo " +
			" WHERE Ordens_Fretes.OID_Pessoa = Pessoa_Proprietario.OID_Pessoa " +
			" AND Ordens_Fretes.OID_Veiculo = Veiculos.OID_Veiculo " +
			" AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO " +
			" AND Veiculos.OID_CIDADE = Cidade_Veiculo.OID_CIDADE " +
			" AND Cidade_Veiculo.OID_REGIAO_ESTADO = Regiao_Estado_Veiculo.OID_REGIAO_ESTADO " +
			" AND Regiao_Estado_Veiculo.OID_ESTADO = Estado_Veiculo.OID_ESTADO " +
			" AND Pessoa_Proprietario.OID_CIDADE = Cidade_Proprietario.OID_CIDADE " +
			" AND Cidade_Proprietario.OID_REGIAO_ESTADO = Regiao_Estado_Proprietario.OID_REGIAO_ESTADO " +
			" AND Regiao_Estado_Proprietario.OID_ESTADO = Estado_Proprietario.OID_ESTADO " +
			" AND Ordens_Fretes.DM_Frete <> 'T'  "+
			" AND Ordens_Fretes.VL_Ordem_Frete > 0 " +
			" AND Ordens_Fretes.DM_Impresso = 'S' ";

			if (String.valueOf (ed.getNR_Ordem_Frete ()) != null &&
					!String.valueOf (ed.getNR_Ordem_Frete ()).equals ("0")) {
				sql += " and Ordens_Fretes.NR_ORDEM_FRETE = " + ed.getNR_Ordem_Frete ();
			}
			if (String.valueOf (ed.getOID_Unidade ()) != null &&
					!String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
				sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
			}

			if (util.doValida (ed.getOID_Ordem_Frete ())) {
				sql += " and Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () +
				"'";
			}

			sql += " order by Ordens_Fretes.NR_ORDEM_FRETE";

			//// .println(sql);

			ResultSet res = this.executasql.executarConsulta (sql.toString ());

			return res;
		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "imprime_Ordem_Frete (Ordem_FreteED ed)");
		}
	}


	public Ordem_FreteED atualiza_Compromisso_Ordem_Frete (Ordem_FreteED ed)
	throws Excecoes {

		String sql = null;
		Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();
		ResultSet resCTRC = null;
		String DM_Tem_Adiantamento = "N";
		int parcela = 0;

		//.println("atualiza_Compromisso_Ordem_Frete ");

		try {
			if (1==1 || parametro_FixoED.getDM_Gera_Compromisso_Ordem_Frete_Adiantamento().equals("S")){
				sql = " SELECT Ordem_Principal.NR_Ordem_Frete, Ordem_Principal.oid_Unidade, Ordem_Principal.oid_Motorista, Ordem_Principal.oid_Usuario, Ordem_Principal.oid_Unidade_Adiantamento1, Ordem_Principal.Oid_Ordem_Frete, Ordens_Fretes.vl_ordem_Frete, Ordens_Fretes.vl_adiantamento1, Ordens_Fretes.vl_adiantamento2, Ordens_Fretes.oid_pessoa, Ordens_Fretes.oid_Fornecedor, Ordens_Fretes.oid_veiculo, Ordens_Fretes.dt_pagamento, Ordens_Fretes.nr_cheque_adto " +
					  " FROM   Ordens_Fretes, Ordens_Fretes Ordem_Principal " +
					  " WHERE  Ordens_Fretes.oid_Ordem_principal = Ordem_Principal.oid_Ordem_Frete " +
					  " AND    Ordens_Fretes.oid_Ordem_principal = '" + ed.getOID_Ordem_Frete () + "'";

				//.println(sql);

				resCTRC = this.executasql.executarConsulta (sql);

				while (resCTRC.next ()) {
					DM_Tem_Adiantamento = "S";

					Ordem_FreteED edOrdem_Frete = new Ordem_FreteED ();

					String oid_Fornecedor=resCTRC.getString ("OID_Pessoa");
					if (resCTRC.getString ("oid_Fornecedor") != null && !resCTRC.getString ("oid_Fornecedor").equals("") && !resCTRC.getString ("oid_Fornecedor").equals("null")){
						oid_Fornecedor=resCTRC.getString ("oid_Fornecedor");
					}

					parcela++;
					edOrdem_Frete.setNR_Parcela (new Integer (parcela));
					edOrdem_Frete.setOID_Pessoa (oid_Fornecedor);
					edOrdem_Frete.setOID_Motorista (resCTRC.getString ("OID_Motorista"));
					edOrdem_Frete.setOID_Ordem_Frete (resCTRC.getString ("OID_Ordem_Frete"));
					edOrdem_Frete.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
					edOrdem_Frete.setOID_Unidade_Pagamento (resCTRC.getInt ("oid_Unidade_Adiantamento1"));
					edOrdem_Frete.setDM_Tipo_Pagamento("C");

					edOrdem_Frete.setOID_Veiculo (resCTRC.getString ("OID_Veiculo"));
					edOrdem_Frete.setNR_Ordem_Frete (resCTRC.getLong ("NR_Ordem_Frete"));
					edOrdem_Frete.setDT_Vencimento (resCTRC.getString ("dt_pagamento"));
					edOrdem_Frete.setVL_Compromisso (resCTRC.getDouble ("VL_Adiantamento1"));
					edOrdem_Frete.setOID_Usuario(resCTRC.getLong ("OID_Usuario"));
					this.geraCompromisso_VinculaLotePagamento (edOrdem_Frete, resCTRC.getString ("nr_cheque_adto"));

				}
			}

			sql = "SELECT * " +
			" FROM " +
			" Ordens_Fretes " +
			" WHERE  Ordens_Fretes.DM_Impresso = 'S' " +
			" AND    Ordens_Fretes.VL_ORDEM_FRETE > 0" +
			" AND    Ordens_Fretes.OID_ORDEM_FRETE = '" + ed.getOID_Ordem_Frete () +
			"'";

			resCTRC = null;
			resCTRC = this.executasql.executarConsulta (sql);

			while (resCTRC.next ()) {
				String oid_Fornecedor=resCTRC.getString ("OID_Pessoa");
				if (resCTRC.getString ("oid_Fornecedor") != null && !resCTRC.getString ("oid_Fornecedor").equals("") && !resCTRC.getString ("oid_Fornecedor").equals("null")){
					oid_Fornecedor=resCTRC.getString ("oid_Fornecedor");
				}

				if (resCTRC.getDouble ("VL_Adiantamento1") > 0 && "N".equals(DM_Tem_Adiantamento)) {
					Ordem_FreteED edOrdem_Frete = new Ordem_FreteED ();

					parcela++;
					edOrdem_Frete.setNR_Parcela (new Integer (parcela));
					edOrdem_Frete.setOID_Pessoa (oid_Fornecedor);
					edOrdem_Frete.setOID_Motorista (resCTRC.getString ("OID_Motorista"));
					edOrdem_Frete.setOID_Ordem_Frete (resCTRC.getString ("OID_Ordem_Frete"));
					edOrdem_Frete.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
					edOrdem_Frete.setOID_Unidade_Pagamento (resCTRC.getInt ("oid_Unidade_Adiantamento1"));
					edOrdem_Frete.setDM_Tipo_Pagamento(resCTRC.getString("DM_Tipo_Pagamento_Adiantamento1"));

					edOrdem_Frete.setOID_Veiculo (resCTRC.getString ("OID_Veiculo"));
					edOrdem_Frete.setNR_Ordem_Frete (resCTRC.getLong ("NR_Ordem_Frete"));
					edOrdem_Frete.setDT_Vencimento (resCTRC.getString ("DT_Adiantamento1"));
					edOrdem_Frete.setVL_Compromisso (resCTRC.getDouble ("VL_Adiantamento1"));
					edOrdem_Frete.setOID_Usuario(resCTRC.getLong ("OID_Usuario"));
					this.geraCompromisso (edOrdem_Frete);
				}
				if (resCTRC.getDouble ("VL_Adiantamento2") > 0 && "N".equals(DM_Tem_Adiantamento)) {
					Ordem_FreteED edOrdem_Frete = new Ordem_FreteED ();
					parcela++;
					edOrdem_Frete.setNR_Parcela (new Integer (parcela));
					edOrdem_Frete.setOID_Pessoa (oid_Fornecedor);
					edOrdem_Frete.setOID_Motorista (resCTRC.getString ("OID_Motorista"));
					edOrdem_Frete.setOID_Ordem_Frete (resCTRC.getString ("OID_Ordem_Frete"));
					edOrdem_Frete.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
					edOrdem_Frete.setOID_Unidade_Pagamento (resCTRC.getInt ("oid_Unidade_Adiantamento2"));
					edOrdem_Frete.setDM_Tipo_Pagamento(resCTRC.getString("DM_Tipo_Pagamento_Adiantamento2"));
					edOrdem_Frete.setOID_Veiculo (resCTRC.getString ("OID_Veiculo"));
					edOrdem_Frete.setNR_Ordem_Frete (resCTRC.getLong ("NR_Ordem_Frete"));
					edOrdem_Frete.setDT_Vencimento (resCTRC.getString ("DT_Adiantamento2"));
					edOrdem_Frete.setVL_Compromisso (resCTRC.getDouble ("VL_Adiantamento2"));
					edOrdem_Frete.setOID_Usuario(resCTRC.getLong ("OID_Usuario"));

					this.geraCompromisso (edOrdem_Frete);
				}

				if (resCTRC.getDouble ("VL_Saldo") > 0) {
					Ordem_FreteED edOrdem_Frete = new Ordem_FreteED ();
					parcela++;
					edOrdem_Frete.setNR_Parcela (new Integer (parcela));
					edOrdem_Frete.setOID_Pessoa (oid_Fornecedor);
					edOrdem_Frete.setOID_Motorista (resCTRC.getString ("OID_Motorista"));
					edOrdem_Frete.setOID_Ordem_Frete (resCTRC.getString ("OID_Ordem_Frete"));
					edOrdem_Frete.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
					edOrdem_Frete.setOID_Unidade_Pagamento (resCTRC.getInt ("oid_Unidade_Saldo"));
					edOrdem_Frete.setDM_Tipo_Pagamento(resCTRC.getString("DM_Tipo_Pagamento_Saldo"));
					edOrdem_Frete.setOID_Veiculo (resCTRC.getString ("OID_Veiculo"));
					edOrdem_Frete.setNR_Ordem_Frete (resCTRC.getLong ("NR_Ordem_Frete"));
					edOrdem_Frete.setDT_Vencimento (resCTRC.getString ("DT_Saldo"));
					edOrdem_Frete.setVL_Compromisso (resCTRC.getDouble ("VL_Saldo"));
					edOrdem_Frete.setOID_Usuario(resCTRC.getLong ("OID_Usuario"));
					this.geraCompromisso (edOrdem_Frete);
				}

				if(JavaUtil.doValida(parametro_FixoED.getDM_Gera_Compromisso_INSS_Ordem_Frete())
						&& parametro_FixoED.getDM_Gera_Compromisso_INSS_Ordem_Frete().equals("S")){
					if (resCTRC.getDouble ("VL_INSS") > 0) {
						Ordem_FreteED edOrdem_Frete = new Ordem_FreteED ();
						//rever
						parcela++;
						edOrdem_Frete.setNR_Parcela (new Integer (1));
						edOrdem_Frete.setOID_Pessoa (oid_Fornecedor);
						edOrdem_Frete.setOID_Motorista (resCTRC.getString ("OID_Motorista"));
						edOrdem_Frete.setOID_Ordem_Frete (resCTRC.getString ("OID_Ordem_Frete"));
						edOrdem_Frete.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
						edOrdem_Frete.setOID_Unidade_Pagamento (resCTRC.getInt ("oid_Unidade_Saldo"));
						edOrdem_Frete.setOID_Usuario(resCTRC.getLong ("OID_Usuario"));
						//rever
						edOrdem_Frete.setDM_Tipo_Pagamento(resCTRC.getString("DM_Tipo_Pagamento_Saldo"));
						edOrdem_Frete.setOID_Veiculo (resCTRC.getString ("OID_Veiculo"));
						edOrdem_Frete.setNR_Ordem_Frete (resCTRC.getLong ("NR_Ordem_Frete"));
						//rever
						edOrdem_Frete.setDT_Vencimento(this.getDT_Vencimento_INSS());
						edOrdem_Frete.setVL_Compromisso (resCTRC.getDouble ("VL_INSS"));
						DM_Tipo_Ordem_Frete = "INSS";
						this.geraCompromisso (edOrdem_Frete);
					}
				}
				if(JavaUtil.doValida(parametro_FixoED.getDM_Gera_Compromisso_IR_Ordem_Frete())
						&& parametro_FixoED.getDM_Gera_Compromisso_IR_Ordem_Frete().equals("S")){
					if (resCTRC.getDouble ("VL_IRRF") > 0) {
						Ordem_FreteED edOrdem_Frete = new Ordem_FreteED ();
						//rever
						parcela++;
						edOrdem_Frete.setNR_Parcela (new Integer (1));
						edOrdem_Frete.setOID_Pessoa (oid_Fornecedor);
						edOrdem_Frete.setOID_Motorista (resCTRC.getString ("OID_Motorista"));
						edOrdem_Frete.setOID_Ordem_Frete (resCTRC.getString ("OID_Ordem_Frete"));
						edOrdem_Frete.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
						edOrdem_Frete.setOID_Unidade_Pagamento (resCTRC.getInt ("oid_Unidade_Saldo"));
						//rever
						edOrdem_Frete.setDM_Tipo_Pagamento(resCTRC.getString("DM_Tipo_Pagamento_Saldo"));
						edOrdem_Frete.setOID_Veiculo (resCTRC.getString ("OID_Veiculo"));
						edOrdem_Frete.setNR_Ordem_Frete (resCTRC.getLong ("NR_Ordem_Frete"));
						//rever
						edOrdem_Frete.setDT_Vencimento(this.getDT_Vencimento_IRRF());
						edOrdem_Frete.setVL_Compromisso (resCTRC.getDouble ("VL_IRRF"));
						edOrdem_Frete.setOID_Usuario(resCTRC.getLong ("OID_Usuario"));
						DM_Tipo_Ordem_Frete = "IRRF";
						this.geraCompromisso (edOrdem_Frete);
					}
				}


			}
		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "atualiza_Compromisso_Ordem_Frete (Ordem_FreteED ed)");
		}
		return ed;
	}

	public void geraRelCFEmitido(Manifesto_InternacionalED ed, HttpServletResponse response)throws Excecoes{

		String sql, mic = null;
		ArrayList lista = new ArrayList();
		long valOid = 0;
		ResultSet res = null;

		try{

			sql = " select Manifestos_Internacionais.NR_Manifesto_Internacional, Manifestos_Internacionais.oid_Seguradora, "+
			" Manifestos_Internacionais.CD_Pais, Manifestos_Internacionais.NR_Permisso, Veiculos.NR_Placa, " +
			" ordens_fretes.* " +
			" from " +
			" Ordens_Fretes, Manifestos_Internacionais, Veiculos ";
			sql += " WHERE ordens_MIC.OID_Manifesto_Internacional = Manifestos_Internacionais.OID_Manifesto_Internacional ";
			sql += " AND ordens_fretes.oid_ordem_frete = ordens_MIC.oid_ordem_frete ";
			sql += " AND Manifestos_Internacionais.OID_Veiculo = Veiculos.OID_Veiculo ";

			if (String.valueOf(ed.getOID_Unidade_Origem()) != null && !String.valueOf(ed.getOID_Unidade_Origem()).equals("0")){
				sql += " and Manifestos_Internacionais.OID_Unidade = " + ed.getOID_Unidade_Origem();
			}
			if (String.valueOf(ed.getOID_Unidade_Destino()) != null && !String.valueOf(ed.getOID_Unidade_Destino()).equals("0")){
				sql += " and Manifestos_Internacionais.OID_Unidade_Destino = " + ed.getOID_Unidade_Destino();
			}
			if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("")){
				sql += " and ordens_fretes.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
			}
			if (String.valueOf(ed.getOID_Seguradora()) != null && !String.valueOf(ed.getOID_Seguradora()).equals("0")){
				sql += " and Manifestos_Internacionais.OID_Seguradora = " + ed.getOID_Seguradora();
			}
			if (String.valueOf(ed.getDT_Emissao_Inicial()) != null &&
					!String.valueOf(ed.getDT_Emissao_Inicial()).equals("") &&
					!String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
				sql += " and Manifestos_Internacionais.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
			}
			if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
					!String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
					!String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
				sql += " and Manifestos_Internacionais.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
			}
			if (util.doValida(String.valueOf(ed.getOID_Veiculo()))){
				sql += " and Manifestos_Internacionais.OID_Veiculo = " + ed.getOID_Veiculo() + "";
			}

			sql += "order by ordens_fretes.NR_ordem_frete ";
			// .println(sql.toString());
			res = this.executasql.executarConsulta(sql.toString());

			mic = "";

			while (res.next()){
				Manifesto_InternacionalED edVolta = new Manifesto_InternacionalED();

				edVolta.setNR_Manifesto_Internacional(res.getLong("NR_Manifesto_Internacional"));
				String nr_MIC = res.getString("NR_MANIFESTO_INTERNACIONAL");
				String nr_MIC_Parcial = res.getString("CD_PAIS") + "." + res.getString("NR_PERMISSO") + ".";
				int completa_Nr = 13 - nr_MIC_Parcial.length() - nr_MIC.length();
				for(int a = 0 ; a < completa_Nr ; a++){
					nr_MIC_Parcial = nr_MIC_Parcial + "0";
				}
				edVolta.setNM_Manifesto_Internacional(nr_MIC_Parcial+nr_MIC);

				edVolta.setDT_Emissao(res.getString("DT_Emissao"));
				DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
				edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

				edVolta.setNR_Placa(res.getString("NR_Placa"));
				PessoaBean pP = PessoaBean.getByOID(res.getString("oid_pessoa"));
				edVolta.setNM_Proprietario(pP.getNM_Razao_Social());

				edVolta.setNM_Ordem_Frete(res.getString("NR_Ordem_Frete"));
				SeguradoraBean s = SeguradoraBean.getByOID(res.getInt("oid_seguradora"));
				edVolta.setNM_Seguradora(s.getNM_Seguradora());

				edVolta.setVL_Ordem_Frete(res.getDouble("vl_ordem_frete"));
				edVolta.setVL_Adto(res.getDouble("vl_adiantamento1") + res.getDouble("vl_adiantamento2"));
				edVolta.setVL_Saldo(res.getDouble("vl_saldo"));
				edVolta.setVL_IRRF(res.getDouble("vl_irrf"));
				edVolta.setVL_INSS(res.getDouble("vl_inss_empresa"));
				if (res.getDouble("vl_inss_prestador") > 0){
					edVolta.setVL_INSS(res.getDouble("vl_inss_prestador"));
				}
				edVolta.setVL_Set_Senat(res.getDouble("vl_set_senat"));

				// .println("add...");
				lista.add(edVolta);
			}
			util.closeResultset(res);
			Ordem_FreteRL cfRL = new Ordem_FreteRL();
			cfRL.geraRelCFEmitido(ed, lista, response);


		} catch(Exception exc) {
			util.closeResultset(res);
			exc.printStackTrace();
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao listar");
			excecoes.setMetodo("listar");
			excecoes.setExc(exc);
			throw excecoes;
		}
	}

	public ArrayList lista_Fora_Intervalo(Ordem_FreteED ed)
	throws Excecoes {

		String sql = null;
		ArrayList list = new ArrayList();
		ResultSet res = null;
		int ok=0;
		long NR_Ordem_Frete_Inicial=ed.getNR_Ordem_Frete_Inicial();
		if (ed.getNR_Ordem_Frete_Final()<NR_Ordem_Frete_Inicial) {
			ed.setNR_Ordem_Frete_Final(NR_Ordem_Frete_Inicial);
		}
		try {
			while(NR_Ordem_Frete_Inicial<=ed.getNR_Ordem_Frete_Final()) {
				ok=0;
				sql = " SELECT oid_Ordem_Frete FROM Ordens_Fretes  " +
				" WHERE Ordens_Fretes.oid_unidade = " + ed.getOID_Unidade() +
				" AND   Ordens_Fretes.NR_Ordem_Frete = " + NR_Ordem_Frete_Inicial;

				res = this.executasql.executarConsulta(sql);
				while (res.next()) {
					ok++;
				}
				if (ok==0) {
					Ordem_FreteED edVolta = new Ordem_FreteED();
					edVolta.setNR_Ordem_Frete_Inicial(ed.getNR_Ordem_Frete_Inicial());
					edVolta.setNR_Ordem_Frete_Final(ed.getNR_Ordem_Frete_Final());
					edVolta.setNR_Ordem_Frete(NR_Ordem_Frete_Inicial);
					list.add(edVolta);
				}
				NR_Ordem_Frete_Inicial++;
			}

		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(Ordem_FreteED ed)");
		}

		return list;
	}


	private Ordem_FreteED consultaSituacao(Ordem_FreteED ed){

		ed.setNM_Situacao("---");
		if ("S".equals(ed.getDM_Impresso())){
			ed.setNM_Situacao("Impressa");
		}
		if ("C".equals(ed.getDM_Situacao())){
			ed.setNM_Situacao("Cancelada");
		}

		return ed;
	}

	private void ajustaTipo_Pessoa (Ordem_FreteED ed) throws Excecoes {
		String sql = null;

		try {
			String DM_Tipo_Pessoa="PJ";
			sql = " SELECT Ordens_Fretes.oid_Ordem_Frete, Ordens_Fretes.oid_Pessoa, Ordens_Fretes.oid_Motorista FROM Ordens_Fretes " ;
			ResultSet rs = null;
			ResultSet rs2 = null;
			rs = this.executasql.executarConsulta (sql);
			while (rs.next ()) {
				DM_Tipo_Pessoa="PJ";
				if (rs.getString("oid_Pessoa").length ()== 11){
					DM_Tipo_Pessoa="PF";
				}

				sql = " SELECT Motoristas.DM_Tipo_Funcao ";
				sql += " FROM   Motoristas ";
				sql += " WHERE  Motoristas.oid_Pessoa = '" + rs.getString("oid_Pessoa") + "'";
				rs2 = this.executasql.executarConsulta (sql);
				while (rs2.next ()) {
					if ("F".equals (rs2.getString ("DM_Tipo_Funcao"))) {
						DM_Tipo_Pessoa = "FE";
					}
				}




				sql = " update Ordens_Fretes set " +
				" DM_Tipo_Pessoa= '" +DM_Tipo_Pessoa + "'";
				sql += " where oid_Ordem_Frete = '" + rs.getString("oid_Ordem_Frete") + "'";

				executasql.executarUpdate (sql);

				// .println("OF: " + rs.getString("oid_Ordem_Frete") + " DM_Tipo_Pessoa: " + DM_Tipo_Pessoa );
			}

		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao alterar Ordem de Frete");
			excecoes.setMetodo ("alterar");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	private String consultaTipo_Pessoa (String oid_Fornecedor, String oid_Motorista) throws Excecoes {
		String sql = null;
		String dm_tipo_pessoa="PJ";

		try {
			if (oid_Fornecedor.length ()== 11) {
				dm_tipo_pessoa="PF";
			}
			sql =  " SELECT Motoristas.DM_Tipo_Funcao ";
			sql += " FROM   Motoristas ";
			sql += " WHERE  Motoristas.oid_Pessoa = '"+oid_Motorista+"'";

			// .println(sql);

			ResultSet rs = null;
			rs = this.executasql.executarConsulta (sql);
			while (rs.next ()) {
				if ("F".equals(rs.getString("DM_Tipo_Funcao"))){
					dm_tipo_pessoa="FE";
				}
			}
		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao alterar Ordem de Frete");
			excecoes.setMetodo ("alterar");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return dm_tipo_pessoa;
	}

	private String getDT_Vencimento_IRRF(){

		String retorno = "";

		try{
			//String data = Data.getDataDMY();
	        //Calendar cal = Data.stringToCalendar(data,"dd/MM/yyyy");
//	    	seta dia 10 do mes seguinte
	        //if(Calendar.MONTH<12){
	        //  int soma = cal.get(Calendar.MONTH);
	        //  cal.set(Calendar.MONTH, soma+1);
	        //} else cal.set(Calendar.MONTH, 1);
	        //cal.set(Calendar.DAY_OF_MONTH, 20);
	        //if(cal.get(Calendar.DAY_OF_WEEK)==1)
	        //  cal.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+1);
	        //if(cal.get(Calendar.DAY_OF_WEEK)==7)
	        //  cal.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+2);
	        //Date d = new Date();
	        //d = cal.getTime();
	        //SimpleDateFormat formatter_date = new SimpleDateFormat("dd/MM/yyyy");
			//retorno = formatter_date.format(d);

	        String data = Data.getDataDMY();

	        if (!"30".equals(data.substring(0, 2)) && !"31".equals(data.substring(0, 2))){
	           retorno = "20" + Data.getSomaMesesData(data, 1).substring(2 , 10);
	        }else{
	           data = "20"+data.substring(2,10);
	           retorno = "20" + Data.getSomaMesesData(data, 1).substring(2 , 10);
	        }

		} catch(Exception e){
			e.printStackTrace();
		}
		return retorno;
	}

	private String getDT_Vencimento_INSS(){

		String retorno = "";

		try{
			//String data = Data.getDataDMY();
            //Calendar cal = Data.stringToCalendar(data,"dd/MM/yyyy");
	        // seta dia 10 do mes seguinte
	        //if(Calendar.MONTH<12){
	        //  int soma = cal.get(Calendar.MONTH);
	        //  cal.set(Calendar.MONTH, soma+1);
	        //} else cal.set(Calendar.MONTH, 1);
	        //cal.set(Calendar.DAY_OF_MONTH, 10);
	        //if(cal.get(Calendar.DAY_OF_WEEK)==1)
	        //  cal.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+1);
	       // if(cal.get(Calendar.DAY_OF_WEEK)==7)
	       //   cal.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+2);
	        //Date d = new Date();
	        //d = cal.getTime();
	        //SimpleDateFormat formatter_date = new SimpleDateFormat("dd/MM/yyyy");
			//retorno = formatter_date.format(d);

			String data = Data.getDataDMY();

	        if (!"30".equals(data.substring(0, 2)) && !"31".equals(data.substring(0, 2))){
	          retorno = "20" + Data.getSomaMesesData(data, 1).substring(2 , 10);
	        }else{
	          data = "20"+data.substring(2,10);
	          retorno = "20" + Data.getSomaMesesData(data, 1).substring(2 , 10);
	        }

		} catch(Exception e){
			e.printStackTrace();
		}
		return retorno;
	}


	public Ordem_FreteED transfere_Adiantamento_para_Cota_Corrente (Ordem_FreteED ed) throws Excecoes {

		String sql = null;
		ResultSet res = null;

		try {

			sql = " SELECT Ordens_Fretes.oid_motorista, " +
			"        Ordens_Fretes.NR_Ordem_Frete, " +
			"        Ordens_Fretes.VL_Ordem_Frete, " +
			"        Ordens_Fretes.vl_adiantamento1, " +
			"        contas_correntes.oid_conta_corrente  " +
			" FROM   Ordens_Fretes, Contas_Correntes " +
			" WHERE  Ordens_Fretes.oid_motorista = contas_correntes.oid_Pessoa " +
			" AND    Ordens_Fretes.oid_Ordem_Frete ='" + ed.getOID_Ordem_Frete () + "'";

			// .println ("transfere_Adiantamento_para_Cota_Corrente 1->> " + sql);
			res = null;
			res = this.executasql.executarConsulta (sql);
			if (res.next ()) {

				Movimento_Conta_CorrenteED edMCC = new Movimento_Conta_CorrenteED ();
				edMCC.setOid_Conta_Corrente (res.getString ("oid_Conta_Corrente"));
				edMCC.setNR_Documento (res.getString ("NR_Ordem_Frete"));
				edMCC.setDM_Tipo_Lancamento ("G");
				edMCC.setDM_Debito_Credito ("D");

				edMCC.setVL_Lancamento (new Double (res.getDouble ("VL_Ordem_Frete")));
				edMCC.setNM_Complemento_Historico ("TRANSF.ADTO NR:" + res.getString ("NR_Ordem_Frete"));
				edMCC.setDT_Movimento_Conta_Corrente (Data.getDataDMY ()); //.getString("DT_Chegada"));

				sql = " SELECT Contas.oid_tipo_documento, Contas.oid_historico, Contas.oid_Conta  " +
				" FROM   Contas " +
				" WHERE  Contas.oid_Conta =" + parametro_FixoED.getOID_Conta_Acerto_Contas ();

				// .println ("transfere_Adiantamento_para_Cota_Corrente 2->> " + sql);

				res = this.executasql.executarConsulta (sql);
				if (res.next ()) {
					edMCC.setOid_Conta (res.getInt ("oid_Conta"));
					edMCC.setOID_Tipo_Documento (new Integer (res.getInt ("oid_tipo_documento")));
					edMCC.setOid_Historico (new Integer (res.getInt ("oid_historico")));

					// .println ("transfere_Adiantamento_para_Cota_Corrente W 2");
					edMCC.setOid_Lote_Pagamento (0);

					Movimento_Conta_CorrenteED edVolta = new Movimento_Conta_CorrenteED ();
					edVolta = new Movimento_Conta_CorrenteBD (this.executasql).inclui (edMCC);

					// .println (" W99");

					sql = " Update Ordens_Fretes set oid_acerto = 999, TX_Observacao='VALOR TRANSF.PARA CONTA CORRENTE' "  +
					" WHERE OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

					// .println ("transfere_Adiantamento_para_Cota_Corrente 3->> " + sql);

					executasql.executarUpdate (sql);

					ed.setOID_Acerto(999);

					// .println ("transfere_Adiantamento_para_Cota_Corrente 2->> " + sql);

				}
			}

		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , getClass ().getName () , "transfere_Adiantamento_para_Cota_Corrente(Ordem_FreteED ed)");
		}
		return ed;
	}

	public ArrayList listaDirfMotorista(Ordem_FreteED ed) throws Excecoes {

		String sql = null;
		ArrayList list = new ArrayList ();

		try {

			sql = "select pessoas.nr_cnpj_cpf, pessoas.nm_razao_social, sum(vl_ordem_frete) as vl_total_carregado," +
					"sum(vl_irrf) as irrf, " +
					"case when ordens_fretes.oid_pessoa=ordens_fretes.oid_motorista then sum(vl_inss_prestador) else 0.00 end as inss, " +
					"substring(dt_emissao,6,2) as mes " +
				  " from ordens_fretes, pessoas " +
				  " where ordens_fretes.oid_pessoa = pessoas.oid_pessoa " +
				  " and length(ordens_fretes.oid_pessoa)=11 " +
				  " and dm_impresso = 'S' " +
				  " and dm_tipo_pagamento = 'T' ";
//			sql += " and Ordens_Fretes.DM_Situacao != 'C' ";
//			sql += " and Ordens_Fretes.vl_ordem_frete > 0 ";

			if (String.valueOf (ed.getDt_Emissao_Inicial()) != null &&
					!String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("") &&
					!String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("null")) {
				sql += " and Ordens_Fretes.DT_Emissao >= '" + ed.getDt_Emissao_Inicial () + "'";
			}
			if (String.valueOf (ed.getDt_Emissao_Final()) != null &&
					!String.valueOf (ed.getDt_Emissao_Final()).equals ("") &&
					!String.valueOf (ed.getDt_Emissao_Final()).equals ("null")) {
				sql += " and Ordens_Fretes.DT_Emissao <= '" + ed.getDt_Emissao_Final() + "'";
			}
			if (String.valueOf (ed.getOID_Unidade ()) != null &&
					!String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
					!String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
				sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
			}

			sql +=
				" group by pessoas.nr_cnpj_cpf, pessoas.nm_razao_social, substring(dt_emissao,6,2) order by pessoas.nm_razao_social, substring(dt_emissao,6,2) ";

			ResultSet res = null;
			res = this.executasql.executarConsulta (sql);
System.out.println("DIRF:"+sql);

			//popula
			String motora="";
			double total=0,totalIR=0,totalIN=0;
			Auxiliar1ED aux = new Auxiliar1ED();
			while (res.next ()) {
				if(!motora.equals(res.getString("nr_cnpj_cpf"))){
					aux.setA1(total);
					aux.setA2(totalIR);
					aux.setA3(totalIN);
					if(JavaUtil.doValida(motora))
						list.add(aux);
					aux = new Auxiliar1ED();
					total=0;
					aux.setNM_Classifica1(res.getString("nr_cnpj_cpf"));
					aux.setNM_Classifica2(res.getString("nm_razao_social"));
					aux.setNM_Classifica3(res.getString("mes"));
				}
				total+=res.getDouble("vl_total_carregado");
				totalIR+=res.getDouble("irrf");
				totalIN+=res.getDouble("inss");
				switch (res.getInt("mes")) {
				case 1:
					aux.setD1(res.getDouble("vl_total_carregado"));
					aux.setD13(res.getDouble("irrf"));
					aux.setD25(res.getDouble("inss"));
					break;
				case 2:
					aux.setD2(res.getDouble("vl_total_carregado"));
					aux.setD14(res.getDouble("irrf"));
					aux.setD26(res.getDouble("inss"));
					break;
				case 3:
					aux.setD3(res.getDouble("vl_total_carregado"));
					aux.setD15(res.getDouble("irrf"));
					aux.setD27(res.getDouble("inss"));
					break;
				case 4:
					aux.setD4(res.getDouble("vl_total_carregado"));
					aux.setD16(res.getDouble("irrf"));
					aux.setD28(res.getDouble("inss"));
					break;
				case 5:
					aux.setD5(res.getDouble("vl_total_carregado"));
					aux.setD17(res.getDouble("irrf"));
					aux.setD29(res.getDouble("inss"));
					break;
				case 6:
					aux.setD6(res.getDouble("vl_total_carregado"));
					aux.setD18(res.getDouble("irrf"));
					aux.setD30(res.getDouble("inss"));
					break;
				case 7:
					aux.setD7(res.getDouble("vl_total_carregado"));
					aux.setD19(res.getDouble("irrf"));
					aux.setD31(res.getDouble("inss"));
					break;
				case 8:
					aux.setD8(res.getDouble("vl_total_carregado"));
					aux.setD20(res.getDouble("irrf"));
					aux.setD32(res.getDouble("inss"));
					break;
				case 9:
					aux.setD9(res.getDouble("vl_total_carregado"));
					aux.setD21(res.getDouble("irrf"));
					aux.setD33(res.getDouble("inss"));
					break;
				case 10:
					aux.setD10(res.getDouble("vl_total_carregado"));
					aux.setD22(res.getDouble("irrf"));
					aux.setD34(res.getDouble("inss"));
					break;
				case 11:
					aux.setD11(res.getDouble("vl_total_carregado"));
					aux.setD23(res.getDouble("irrf"));
					aux.setD35(res.getDouble("inss"));
					break;
				case 12:
					aux.setD12(res.getDouble("vl_total_carregado"));
					aux.setD24(res.getDouble("irrf"));
					aux.setD36(res.getDouble("inss"));
					break;
				default:
					break;
				}

				if(res.isLast()){
					aux.setA1(total);
					aux.setA2(totalIR);
					aux.setA3(totalIN);
					list.add(aux);
				}

				motora = res.getString("nr_cnpj_cpf");

			}

		}
		catch (Exception e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
					"listaDirfMotorista(Ordem_FreteED ed)");
		}

		return list;
	}

	public MotoristaBean getMotorista (String oid_Motorista) throws Excecoes {
		MotoristaBean p = new MotoristaBean ();
		try {
			StringBuffer buff = new StringBuffer ();
		      buff.append ("SELECT                             ");
		      buff.append (" Motoristas.OID_Motorista,             ");
		      buff.append (" Motoristas.OID_Pessoa,             ");
		      buff.append (" Motoristas.OID_Pessoa_Vinculo,               ");
		      buff.append (" Motoristas.NM_Apelido,                ");
		      buff.append (" Motoristas.NR_Registro_Geral,              ");
		      buff.append (" Motoristas.NM_Orgao_Emissor,      ");
		      buff.append (" Motoristas.NR_CNH,      ");
		      buff.append (" Motoristas.DT_Vencimento_CNH,      ");
		      buff.append (" Motoristas.NM_Categoria,      ");
		      buff.append (" Motoristas.DM_Carga_Perigosa,      ");
		      buff.append (" Motoristas.DM_Situacao,      ");
		      buff.append (" Motoristas.TX_Observacao,      ");
		      buff.append (" Motoristas.NR_PIS,      ");
		      buff.append (" Motoristas.NR_INSS,      ");
		      buff.append (" Motoristas.NR_Telefone,      ");
		      buff.append (" Motoristas.NR_Celular,      ");
		      buff.append (" Motoristas.TX_Referencia,      ");
		      buff.append (" Pessoas.NM_Razao_Social,              ");
		      buff.append (" Pessoa_Vinculo.NM_Razao_Social,    ");
		      buff.append (" Pessoa_Vinculo.NR_CNPJ_CPF,    ");
		      buff.append (" Motoristas.NM_Pai,                ");
		      buff.append (" Motoristas.NM_Mae,                ");
		      buff.append (" Motoristas.NR_Registro,              ");
		      buff.append (" Motoristas.DT_Nascimento,      ");
		      buff.append (" Motoristas.DM_Tipo_Funcao,              ");
		      buff.append (" Motoristas.PE_Comissao,              ");
		      buff.append (" Motoristas.PE_Gratificacao_0,              ");
		      buff.append (" Motoristas.PE_Gratificacao_1,              ");
		      buff.append (" Motoristas.PE_Gratificacao_2,              ");
		      buff.append (" Motoristas.PE_Gratificacao_3,              ");
		      buff.append (" Motoristas.DM_Pagamento_Diaria,              ");
		      buff.append (" Motoristas.DT_Vencimento_Aval_Med,              ");
		      buff.append (" Motoristas.DT_Liberacao_Cadastro,              ");
		      buff.append (" Motoristas.VL_INSS,              ");
		      buff.append (" Motoristas.VL_Limite_Credito,              ");
		      buff.append (" Motoristas.VL_Base_Adto,              ");
		      buff.append (" Motoristas.VL_Plano_Saude,              ");
		      buff.append (" Motoristas.DM_Qualificacao,      ");
		      buff.append (" Motoristas.CD_Motorista,      ");
		      buff.append (" Motoristas.PE_Comissao_Terceiro,       ");
		      buff.append (" Motoristas.PE_Comissao_Media1,              ");
		      buff.append (" Motoristas.PE_Comissao_Media2,              ");
		      buff.append (" Motoristas.NR_Media1,              ");
		      buff.append (" Motoristas.NR_Media2,              ");
		      buff.append (" Motoristas.NR_Dependentes       ");
		      buff.append (" ,Motoristas.NR_Cartao_CFE       ");

		      buff.append ("FROM ");
		      buff.append (" Motoristas,                    ");
		      buff.append (" Pessoas,                     ");
		      buff.append (" Pessoas Pessoa_Vinculo      ");
		      buff.append (
		          " WHERE Motoristas.OID_Pessoa           = Pessoas.OID_Pessoa             ");
		      buff.append (
		          " AND   Motoristas.OID_Pessoa_Vinculo   = Pessoa_Vinculo.OID_Pessoa   ");
		      buff.append (" AND   Motoristas.OID_Motorista = '");
		      buff.append (oid_Motorista);
		      buff.append ("'");

			ResultSet cursor =  this.executasql.executarConsulta(buff.toString());
			while (cursor.next ()) {
				p.setOID_Motorista (cursor.getString (1));
		        p.setOID_Pessoa (cursor.getString (2));
		        p.setOID_Pessoa_Vinculo (cursor.getString (3));
		        p.setNM_Apelido (cursor.getString (4));
		        p.setNR_Registro_Geral (cursor.getString (5));
		        p.setNM_Orgao_Emissor (cursor.getString (6));
		        p.setNR_CNH (cursor.getString (7));
		        p.setDT_Vencimento_CNH (cursor.getString (8));
		        p.setNM_Categoria (cursor.getString (9));
		        p.setDM_Carga_Perigosa (cursor.getString (10));
		        p.setDM_Situacao (cursor.getString (11));
		        p.setTX_Observacao (cursor.getString (12));
		        p.setNR_PIS (cursor.getString (13));
		        p.setNR_INSS (cursor.getString (14));
		        p.setNR_Telefone (cursor.getString (15));
		        p.setNR_Celular (cursor.getString (16));
		        p.setTX_Referencia (cursor.getString (17));
		        p.setNM_Razao_Social (cursor.getString (18));
		        p.setNM_Razao_Social_Vinculo (cursor.getString (19));
		        p.setNR_CNPJ_CPF_Vinculo (cursor.getString (20));
		        p.setNM_Pai (cursor.getString (21));
		        p.setNM_Mae (cursor.getString (22));
		        p.setNR_Registro (cursor.getString (23));
		        p.setDT_Nascimento (cursor.getString (24));
		        p.setDM_Tipo_Funcao (cursor.getString (25));
		        p.setPE_Comissao (cursor.getDouble (26));
		        p.setPE_Gratificacao_0 (cursor.getDouble (27));
		        p.setPE_Gratificacao_1 (cursor.getDouble (28));
		        p.setPE_Gratificacao_2 (cursor.getDouble (29));
		        p.setPE_Gratificacao_3 (cursor.getDouble (30));
		        p.setDM_Pagamento_Diaria (cursor.getString (31));
		        p.setDT_Vencimento_Aval_Med (cursor.getString (32));
		        p.setDT_Liberacao_Cadastro (cursor.getString (33));
		        p.setVL_INSS (cursor.getDouble (34));
		        p.setVL_Limite_Credito (cursor.getDouble (35));
		        p.setVL_Base_Adto (cursor.getDouble (36));
		        p.setVL_Plano_Saude (cursor.getDouble (37));
		        p.setDM_Qualificacao (cursor.getString (38));
		        p.setCD_Motorista (cursor.getString (39));
		        p.setPE_Comissao_Terceiro (cursor.getDouble (40));

		        p.setPE_Comissao_Media1 (cursor.getDouble (41));
		        p.setPE_Comissao_Media2 (cursor.getDouble (42));
		        p.setNR_Media1 (cursor.getDouble (43));
		        p.setNR_Media2 (cursor.getDouble (44));

		        p.setNR_Dependentes (cursor.getInt (45));

		        p.setNR_Cartao_CFE(cursor.getString(46));
			}
		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Buscar motorista");
			excecoes.setMetodo ("getMotorista");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return p;
	}

	public FornecedorBean getFornecedor(String oid) throws Excecoes {
		FornecedorBean p = new FornecedorBean();
		try {
			StringBuffer buff = new StringBuffer ();
			buff.append("SELECT                               ");
            buff.append("   Fornecedores.OID_Fornecedor,      ");
            buff.append("	Fornecedores.OID_Pessoa,          ");
            buff.append("	Fornecedores.OID_Ramo_Atividade,  ");
            buff.append("	Fornecedores.NM_Conta,            ");
            buff.append("	Fornecedores.NM_Prazo,      	  ");
            buff.append("	Fornecedores.NM_Conta_Contabil,   ");
            buff.append("	Fornecedores.NM_Representante,    ");
            buff.append("	Fornecedores.NM_Entrega,          ");
            buff.append("	Fornecedores.DM_Gera_Livro_Fiscal,");
            buff.append("	Fornecedores.TX_Observacao1,      ");
            buff.append("	Fornecedores.TX_Observacao2,      ");
            buff.append("	Fornecedores.TX_Observacao3,      ");
            buff.append("	Fornecedores.DM_Impostos,         ");
            buff.append("	Fornecedores.VL_Taxa_Cobranca,    ");
            buff.append("	Fornecedores.NR_Dependentes,      ");
            buff.append("	Pessoas.NR_CNPJ_CPF,              ");
            buff.append("	Pessoas.NM_Razao_Social,          ");
            buff.append("	Pessoas.NM_Fantasia,              ");
            buff.append("	Ramos_Atividades.CD_Ramo_Atividade,");
            buff.append("	Ramos_Atividades.NM_Ramo_Atividade,");
            buff.append("	Fornecedores.NM_Banco,            ");
            buff.append("	Fornecedores.NM_Agencia,          ");
            buff.append("	Fornecedores.CD_Conta_Corrente,   ");
            buff.append("	Fornecedores.CD_Banco,            ");
            buff.append("	Fornecedores.NM_MAE,              ");
            buff.append("	Fornecedores.NR_PIS,              ");
            buff.append("	Fornecedores.NR_INSS,             ");
            buff.append("	Fornecedores.DT_NASCIMENTO,       ");
            buff.append("	Fornecedores.DT_Qualificacao,     ");
            buff.append("	Fornecedores.DM_Qualificacao,     ");
            buff.append("	Fornecedores.NR_Vencto1,       	  ");
            buff.append("	Fornecedores.NR_Vencto2,       	  ");
            buff.append("	Fornecedores.NR_Vencto3,       	  ");
            buff.append("	Fornecedores.NR_Vencto4,       	  ");
            buff.append("	Fornecedores.DM_Tipo_Faturamento,  ");
            buff.append("	Fornecedores.DT_Vcto_Permiso,  	   ");
            buff.append("	Fornecedores.OID_Conta,  	   ");
            buff.append("	Fornecedores.OID_Conta_Debito,  	   ");
            buff.append("	Fornecedores.NR_Permiso_Originario, ");
            buff.append("	Fornecedores.CD_Fornecedor,  	   ");
            buff.append("	Fornecedores.OID_Grupo_Economico,  ");
            buff.append("	Fornecedores.OID_Tipo_Servico  	   ");
            buff.append(" FROM Fornecedores, Pessoas, Ramos_Atividades ");
            buff.append(" WHERE Fornecedores.OID_Pessoa          = Pessoas.OID_Pessoa                    ");
            buff.append(" AND   Fornecedores.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");
            buff.append(" AND Fornecedores.OID_Fornecedor = '");
            buff.append(oid);
            buff.append("'");
            buff.append(" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");

			ResultSet cursor =  this.executasql.executarConsulta(buff.toString());
			while (cursor.next ()) {
				p.setOID_Fornecedor(cursor.getString(1));
                p.setOID_Pessoa(cursor.getString(2));
                p.setOID_Ramo_Atividade(cursor.getInt(3));
                p.setNM_Conta(cursor.getString(4));
                p.setNM_Prazo(cursor.getString(5));
                p.setNM_Conta_Contabil(cursor.getString(6));
                p.setNM_Representante(cursor.getString(7));
                p.setNM_Entrega(cursor.getString(8));
                p.setDM_Gera_Livro_Fiscal(cursor.getString(9));
                p.setTX_Observacao1(cursor.getString(10));
                p.setTX_Observacao2(cursor.getString(11));
                p.setTX_Observacao3(cursor.getString(12));
                p.setDM_Impostos(cursor.getString(13));
                p.setVL_Taxa_Cobranca(cursor.getDouble(14));
                p.setNR_Dependentes(cursor.getDouble(15));
                p.setNR_CNPJ_CPF(cursor.getString(16));
                p.setNM_Razao_Social(cursor.getString(17));
                p.setNM_Fantasia(cursor.getString(18));
                p.setCD_Ramo_Atividade(cursor.getString(19));
                p.setNM_Ramo_Atividade(cursor.getString(20));
                p.setNM_Banco(cursor.getString(21));
                p.setNM_Agencia(cursor.getString(22));
                p.setCD_Conta_Corrente(cursor.getString(23));
                p.setCD_Banco(cursor.getString(24));
                p.setNM_Mae(cursor.getString(25));
                p.setNR_PIS(cursor.getString(26));
                p.setNR_INSS(cursor.getString(27));
                p.setDT_Nascimento(cursor.getString(28));
                p.setDT_Qualificacao(cursor.getString(29));
                p.setDM_Qualificacao(cursor.getString(30));
                p.setNR_Vencto1(cursor.getInt(31));
                p.setNR_Vencto2(cursor.getInt(32));
                p.setNR_Vencto3(cursor.getInt(33));
                p.setNR_Vencto4(cursor.getInt(34));
                p.setDM_Tipo_Faturamento(cursor.getString(35));
                p.setDT_Vcto(cursor.getString(36));
                p.setOid_Conta(cursor.getInt(37));
                p.setOid_Conta_Debito(cursor.getInt(38));
                p.setNR_Permiso_Originario(cursor.getString(39));
                p.setCD_Fornecedor(cursor.getString(40));
                p.setOid_Grupo_Economico(cursor.getInt(41));
                p.setOid_Tipo_Servico(cursor.getInt(42));

                if (p.getDT_Qualificacao() == null)
                    p.setDT_Qualificacao("");
                if (p.getNM_Mae() == null)
                    p.setNM_Mae("");
                if (p.getNR_PIS() == null)
                    p.setNR_PIS("");
                if (p.getNR_INSS() == null)
                    p.setNR_INSS("");
                if (p.getDT_Qualificacao() == null)
                    p.setDT_Qualificacao("");
			}
		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Buscar fornecedor");
			excecoes.setMetodo ("getFornecedor");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return p;
	}

	public VeiculoBean getVeiculo(String oid) throws Excecoes {
		VeiculoBean p = new VeiculoBean();
		try {
			StringBuffer buff = new StringBuffer ();
			 buff.append ("SELECT Veiculos.OID_Veiculo, ");
		      buff.append ("   Veiculos.OID_Cidade, ");
		      buff.append ("   Veiculos.OID_Modelo_Veiculo, ");
		      buff.append ("   Veiculos.OID_Pessoa_Proprietario, ");
		      buff.append ("   Veiculos.OID_Pessoa, ");
		      buff.append ("   Veiculos.NR_Placa, ");
		      buff.append ("   Veiculos.NR_Frota, ");
		      buff.append ("   Veiculos.TX_Observacao,  ");
		      buff.append ("   Proprietario.NM_Razao_Social,  ");
		      buff.append ("   Modelos_Veiculos.NM_Modelo_Veiculo,  ");
		      buff.append ("   Tipos_Veiculos.NM_Tipo_Veiculo,  ");
		      buff.append ("   Marcas_Veiculos.NM_Marca_Veiculo,  ");
		      buff.append ("   Veiculos.NR_Rastreador,  ");
		      buff.append ("   Veiculos.NR_Kilometragem_Atual,  ");
		      buff.append ("   Tipos_Veiculos.DM_Tipo_Implemento, ");
		      buff.append ("   Veiculos.DM_Rastreamento,  ");
		      buff.append ("   Veiculos.DM_Situacao,  ");
		      buff.append ("   Veiculos.oid_Ultima_Ocorrencia,  ");
		      buff.append ("   Veiculos.DM_Monitorado,  ");
		      buff.append ("	Cidades.NM_Cidade, ");
		      buff.append ("	Cidades.CD_Cidade, ");
		      buff.append ("	Cidades.NM_Codigo_Aereo, ");
		      buff.append ("	Cidades.DM_Tipo_Localizacao, ");
		      buff.append ("	Cidades.DM_Suframa, ");
		      buff.append ("	Regioes_Estados.OID_Regiao_Estado, ");
		      buff.append ("	Regioes_Estados.NM_Regiao_Estado, ");
		      buff.append ("	Regioes_Estados.CD_Regiao_Estado, ");
		      buff.append ("	Estados.OID_Estado, ");
		      buff.append ("	Estados.NM_Estado, ");
		      buff.append ("	Estados.CD_Estado, ");
		      buff.append ("	Regioes_Paises.OID_Regiao_Pais, ");
		      buff.append ("	Regioes_Paises.NM_Regiao_Pais, ");
		      buff.append ("	Regioes_Paises.CD_Regiao_Pais, ");
		      buff.append ("	Paises.OID_Pais, ");
		      buff.append ("	Paises.NM_Pais, ");
		      buff.append ("	Paises.CD_Pais ");
		      buff.append ("    ,Veiculos.NR_RNTRC  ");
		      buff.append ("FROM Veiculos, Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises, ");
		      buff.append ("     Pessoas Proprietario, ");
		      buff.append ("     Modelos_Veiculos, ");
		      buff.append ("     Marcas_Veiculos, ");
		      buff.append ("     Tipos_Veiculos");
		      buff.append (" WHERE Veiculos.oid_Pessoa_Proprietario   = Proprietario.oid_Pessoa ");
		      buff.append ("   AND Veiculos.oid_Modelo_Veiculo        = Modelos_Veiculos.oid_Modelo_Veiculo ");
		      buff.append ("   AND Modelos_Veiculos.oid_Tipo_Veiculo  = Tipos_Veiculos.oid_Tipo_Veiculo ");
		      buff.append ("   AND Modelos_Veiculos.oid_Marca_Veiculo = Marcas_Veiculos.oid_Marca_Veiculo ");
		      buff.append ("   AND Veiculos.OID_Cidade = Cidades.OID_Cidade ");
		      buff.append ("   AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
		      buff.append ("   AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
		      buff.append ("   AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
		      buff.append ("   AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
		      buff.append ("   AND Veiculos.OID_Veiculo = '");
		      buff.append (oid);
		      buff.append ("'");

			ResultSet cursor =  this.executasql.executarConsulta(buff.toString());
			while (cursor.next ()) {
				p.setOID (cursor.getString (1));
		        p.setOID_Cidade (cursor.getInt (2));
		        p.setOID_Modelo_Veiculo (cursor.getInt (3));
		        p.setOID_Pessoa (cursor.getString (4));
		        p.setOID_Motorista (cursor.getString (5));
		        p.setNR_Placa (cursor.getString (6));
		        p.setNR_Frota (cursor.getString (7));
		        p.setTX_Observacao (cursor.getString (8));
		        p.setNM_Proprietario (cursor.getString (9));
		        p.setNM_Modelo (cursor.getString (10));
		        p.setNM_Tipo (cursor.getString (11));
		        p.setNM_Marca (cursor.getString (12));
		        p.setNR_Rastreador (cursor.getString (13));
		        p.setNR_Kilometragem_Atual (cursor.getLong (14));
		        p.setDM_Tipo_Implemento (cursor.getString ("DM_Tipo_Implemento"));
		        p.setNM_Motorista (new Utilitaria ().getTableStringValue ("NM_Razao_Social" , "Pessoas" , "oid_Pessoa='" + p.getOID_Motorista () + "'"));
		        p.setDM_Rastreamento (cursor.getString ("DM_Rastreamento"));
		        p.setDM_Situacao (cursor.getString ("DM_Situacao"));
		        p.setOid_Ultima_Ocorrencia (cursor.getLong ("Oid_Ultima_Ocorrencia"));
		        p.setDM_Monitorado (cursor.getString ("DM_Monitorado"));
		        p.setNM_Cidade(cursor.getString ("NM_Cidade"));
		        p.setCD_Estado(cursor.getString ("CD_Estado"));

		        p.setNR_RNTRC(cursor.getString("NR_RNTRC"));
			}
		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Buscar veiculo");
			excecoes.setMetodo ("getVeiculo");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return p;
	}

	public CidadeBean getCidade(int oid) throws Excecoes {
		CidadeBean p = new CidadeBean();
		try {
			StringBuffer buff = new StringBuffer ();
			buff.append ("SELECT OID_Cidade, ");
		    buff.append ("	Cidades.NM_Cidade, ");
		    buff.append ("	Cidades.CD_Cidade, ");
		    buff.append ("	Cidades.NM_Codigo_Aereo, ");
		    buff.append ("	Cidades.DM_Tipo_Localizacao, ");
		    buff.append ("	Cidades.DM_Suframa, ");
		    buff.append ("	Regioes_Estados.OID_Regiao_Estado, ");
		    buff.append ("	Regioes_Estados.NM_Regiao_Estado, ");
		    buff.append ("	Regioes_Estados.CD_Regiao_Estado, ");
		    buff.append ("	Estados.OID_Estado, ");
		    buff.append ("	Estados.NM_Estado, ");
		    buff.append ("	Estados.CD_Estado, ");
		    buff.append ("	Regioes_Paises.OID_Regiao_Pais, ");
		    buff.append ("	Regioes_Paises.NM_Regiao_Pais, ");
		    buff.append ("	Regioes_Paises.CD_Regiao_Pais, ");
		    buff.append ("	Paises.OID_Pais, ");
		    buff.append ("	Paises.NM_Pais, ");
		    buff.append ("	Paises.CD_Pais, ");
		    buff.append ("	Regioes_Estados.OID_Subregiao, ");
		    buff.append ("	Cidades.NM_Codigo_IBGE, ");
		    buff.append ("	Cidades.DM_Fluvial ");
		    buff.append (" FROM Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
		    buff.append (" WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
		    buff.append (" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
		    buff.append (" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
		    buff.append (" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
		    buff.append (" AND Cidades.OID_Cidade= ");
		    buff.append (oid);

			ResultSet cursor =  this.executasql.executarConsulta(buff.toString());
			while (cursor.next ()) {
				p.setOID (cursor.getInt (1));
		        p.setNM_Cidade (cursor.getString (2));
		        p.setCD_Cidade (cursor.getString (3));
		        p.setNM_Codigo_Aereo (cursor.getString (4));
		        p.setDM_Tipo_Localizacao (cursor.getString (5));
		        p.setDM_Suframa (cursor.getString (6));
		        p.setOID_Regiao_Estado (cursor.getInt (7));
		        p.setNM_Regiao_Estado (cursor.getString (8));
		        p.setCD_Regiao_Estado (cursor.getString (9));
		        p.setOID_Estado (cursor.getInt (10));
		        p.setNM_Estado (cursor.getString (11));
		        p.setCD_Estado (cursor.getString (12));
		        p.setOID_Regiao_Pais (cursor.getInt (13));
		        p.setNM_Regiao_Pais (cursor.getString (14));
		        p.setCD_Regiao_Pais (cursor.getString (15));
		        p.setOID_Pais (cursor.getInt (16));
		        p.setNM_Pais (cursor.getString (17));
		        p.setCD_Pais (cursor.getString (18));
		        p.setOID_Subregiao (cursor.getInt (19));
		        p.setNM_Codigo_IBGE (cursor.getString (20));
		        if (cursor.getString (20) ==null || "null".equals(cursor.getString (20))) {
		            p.setNM_Codigo_IBGE (" ");
		        }

		        p.setDM_Fluvial (cursor.getString (21));
			}
		}

		catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Buscar cidade");
			excecoes.setMetodo ("getCidade");
			excecoes.setExc (exc);
			throw excecoes;
		}
		return p;
	}

	public int baixaCompromisso_ADTO_Pamcard(Ordem_FreteED ed, String CIOT)
	throws Excecoes {

		String sql = null;
		int NR_Compromisso = 0;
		int oid_Compromisso=0;
		double vl_compromisso=0;

		try {

			sql = "SELECT c.oid_compromisso,c.vl_compromisso,c.nr_compromisso, o.vl_adiantamento1, o.vl_adiantamento2 " +
				  " FROM compromissos c, movimentos_ordens m, ordens_fretes o " +
				  " WHERE c.oid_compromisso = m.oid_compromisso " +
				  " and m.oid_ordem_frete = o.oid_ordem_frete " +
				  " and c.nr_documento='"+ ed.getNR_Ordem_Frete() +"' " +
				  " and c.nr_compromisso = o.nr_compromisso_adto1 " +
				  " and m.oid_ordem_frete='"+ ed.getOID_Ordem_Frete() +"'";
			ResultSet resLocal = this.executasql.executarConsulta (sql);
			if (resLocal.next ()) {
				oid_Compromisso=resLocal.getInt("oid_Compromisso");
				NR_Compromisso=resLocal.getInt("nr_compromisso");
				vl_compromisso=resLocal.getDouble("vl_compromisso");

				if(resLocal.getDouble("vl_adiantamento1")<=0)
					oid_Compromisso=0;
				else if(resLocal.getDouble("vl_adiantamento2")<=0)
					oid_Compromisso=0;
			}

			if (oid_Compromisso>0 && JavaUtil.doValida(CIOT)) {

		        Lote_PagamentoED edLote = new Lote_PagamentoED();
		        edLote.setOID_Fornecedor(ed.getOID_Pessoa());
		        edLote.setDt_stamp(Data.getDataDMY());
		        edLote.setDt_Emissao(Data.getDataDMY());
		        edLote.setDT_Programada(Data.getDataDMY());
		        edLote.setDT_Compensacao(Data.getDataDMY());
		        edLote.setNr_Documento(CIOT);  //CIOT
		        edLote.setTx_Observacao("Adto CFe: " + ed.getNR_Ordem_Frete());
		        edLote.setDM_Copia_Cheque("N");
		        edLote.setDM_Imprimir("S");
		        edLote.setNM_Favorecido("");
		        edLote.setOid_Unidade(new Long(ed.getOID_Unidade()));
		        edLote.setVl_Lote_Pagamento(new Double(vl_compromisso));
		        edLote.setOID_Compromisso(oid_Compromisso);

		        edLote.setOID_Tipo_Documento(new Integer(78));  //pamcard autom
		        edLote.setOID_Conta_Corrente("237");

		        Lote_PagamentoBD loteBD = new Lote_PagamentoBD (this.executasql);
		        edLote = loteBD.inclui(edLote);
				if (edLote.getOid_Lote_Pagamento().intValue()>0){
					sql = " UPDATE Lotes_Pagamentos SET DM_Situacao='L' WHERE oid_Lote_Pagamento="+edLote.getOid_Lote_Pagamento().intValue();
					//.println(sql);
					executasql.executarUpdate (sql);

					long oid_Lote_Compromisso = (new Long(String.valueOf(System.currentTimeMillis()).toString()).longValue());

		            sql = " INSERT INTO Lotes_Compromissos (" +
	                  "      Oid_Lote_Compromisso" +
	                  "     ,DT_PAGAMENTO" +
	                  "     ,VL_PAGAMENTO" +
	                  "     ,NR_LOTE_PAGAMENTO" +
	                  "     ,OID_COMPROMISSO" +
	                  "     ,OID_Lote_Pagamento" +
	                  "     ,DM_Situacao)" +
	                  " VALUES('" +
	                  oid_Lote_Compromisso + "'," +
	                  "'" + Data.getDataDMY() + "'," +
	                  vl_compromisso+ "," +
	                  edLote.getOid_Lote_Pagamento().intValue() + "," +
	                  oid_Compromisso + "," +
	                  edLote.getOid_Lote_Pagamento().intValue() + "," +
	                  "'" + "A" + "')";
						//.println(sql);

		            executasql.executarUpdate(sql);

		            sql =" UPDATE Compromissos SET VL_Saldo=0, dt_vencimento='"+Data.getDataDMY()+" WHERE oid_Compromisso="+oid_Compromisso;
					//.println(sql);
		            executasql.executarUpdate(sql);

//				    this.inicioTransacao ();
//				    //this.executasql = this.sql;

                    Movimento_CompromissoED edMovCompromisso = new Movimento_CompromissoED();
                    edMovCompromisso.setOid_Lote_Pagamento(edLote.getOid_Lote_Pagamento().intValue());
                    edMovCompromisso.setOid_Compromisso(new Integer (oid_Compromisso));
                    edMovCompromisso.setDt_Pagamento(Data.getDataDMY());
                    edMovCompromisso.setVl_Pagamento(new Double(vl_compromisso));
                    edMovCompromisso.setTx_Observacao("Liquidacao ADTO CFe - Pamcard Automatico");
                    edMovCompromisso.setVl_Saldo(new Double(0));
                    Movimento_CompromissoBD movimento_CompromissoBD = new Movimento_CompromissoBD(this.executasql);
                    movimento_CompromissoBD.inclui(edMovCompromisso);

                    //compensar lote
                    sql = " SELECT * FROM Lotes_Pagamentos  " +
                    	  " WHERE Lotes_Pagamentos.oid_Lote_Pagamento = " + edLote.getOid_Lote_Pagamento().intValue() +
                    	  "   AND DT_Compensacao is null ";

                    ResultSet res = this.executasql.executarConsulta(sql);
                    while (res.next()) {
                    	int i = new Lote_PagamentoBD(this.executasql).compensacao(edLote, res,"");
                    }

//                    this.fimTransacao(true);
//					//.println("FIM");
				}
			}

		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "geraCompromisso_VinculaLotePagamento ");
		}

		return NR_Compromisso;
	}

}
