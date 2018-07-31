
package com.master.bd;

/**
 * @author Jeanine e Vinícius
 * date: 21/05/2008
 */

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Help_DeskED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;


public class Help_DeskBD extends BancoUtil {

	private ExecutaSQL executasql;
	FormataDataBean dataFormatada = new FormataDataBean ();

	public Help_DeskBD(ExecutaSQL sql) {
		// super ();
		this.executasql = sql;
	}

	// =================== Inclui Usuário =======================\\
	public Help_DeskED inclui_Usuario(Help_DeskED ed) throws Excecoes {


		String sql = "select max(oid_Help_Desk) as result, max (nr_Help_Desk) as nr_Help_Desk from help_desk ";
		long valOid = 1;
		long nr_Help_Desk = 0;

		Help_DeskED Help_DeskED = new Help_DeskED();

		try {
			ResultSet rs = executasql.executarConsulta(sql);
			while (rs.next()) {
				valOid = rs.getLong("result");
				nr_Help_Desk = rs.getLong("nr_Help_Desk");
			}

			valOid++;
			nr_Help_Desk++;

			sql = " insert into help_desk ( " 
					+ "oid_Help_Desk, "
					+ "nr_Help_Desk, " 
					+ "oid_Empresa, " 
					+ "oid_Usuario, "
					+ "nm_Solicitante, "
					+ "nm_Descricao_Servico1, " 
					+ "nm_Descricao_Servico2, "
					+ "nm_Descricao_Servico3, " 
					+ "nm_Descricao_Servico4, "
					+ "nm_Descricao_Servico5, " 
					+ "dm_Classificacao, " 
					+ "dm_Prioridade, "
					+ "dt_Necessidade_Entrega, "
					+ "dm_Situacao_Ordem_Servico, " 
					+ "dm_Origem, "
					+ "dt_Solicitacao, "
					+ "hr_Solicitacao) values";

			
			sql += "(" + valOid + "," 
					+ nr_Help_Desk + ","
					+ ed.getOid_Empresa() + "," 
					+ ed.getOid_Usuario() + ","
					+ (doValida(ed.getNm_Solicitante()) ? "'" + ed.getNm_Solicitante()+ "'" : "null") + ", "
					+ (doValida(ed.getNm_Descricao_Servico1()) ? "'" + ed.getNm_Descricao_Servico1()+ "'" : "null") + ", "
					+ (doValida(ed.getNm_Descricao_Servico2()) ? "'" + ed.getNm_Descricao_Servico2()+ "'" : "null") + ", "
					+ (doValida(ed.getNm_Descricao_Servico3()) ? "'" + ed.getNm_Descricao_Servico3()+ "'" : "null") + ", "
					+ (doValida(ed.getNm_Descricao_Servico4()) ? "'" + ed.getNm_Descricao_Servico4()+ "'" : "null") + ", "
					+ (doValida(ed.getNm_Descricao_Servico5()) ? "'" + ed.getNm_Descricao_Servico5()+ "'" : "null") + ", '"
					+ ed.getDm_Classificacao() + "','" 
					+ ed.getDm_Prioridade()+ "'," 
					+ (doValida(ed.getDt_Necessidade_Entrega()) ? "'" + ed.getDt_Necessidade_Entrega()+ "'" : "null") + ",'"
					+ ed.getDm_Situacao_Ordem_Servico() + "','"
					+ ed.getDm_Origem() +"'," 
					+ (doValida(ed.getDt_Solicitacao()) ? "'" + ed.getDt_Solicitacao()+ "'" : "null") + ", "
					+ (doValida(ed.getHr_Solicitacao()) ? "'" + ed.getHr_Solicitacao()+ "'" : "null") + ")";

				executasql.executarUpdate(sql);
				
				new Pessoa_EMailBD (this.executasql).envia_eMail (String.valueOf ("123") , "RECOS" , "" , " Recebimento da OS NR: " + nr_Help_Desk, "" , "","");
		        
			Help_DeskED.setOid_Help_Desk(valOid);

			// this.enviaMail(Help_DeskED, "N");

		} catch (Exception exc) {
			exc.printStackTrace();
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao incluir a Ordem de Serviço");
			excecoes.setMetodo("inclui");
			excecoes.setExc(exc);
			throw excecoes;
		}
		return Help_DeskED;

	}

	//=================== Inclui Suporte =======================\\
	public Help_DeskED inclui_Suporte(Help_DeskED ed) throws Excecoes {

		
		String sql = "select max(oid_Help_Desk) as result, max (nr_Help_Desk) as nr_Help_Desk from help_desk ";
		long valOid = 1;
		long nr_Help_Desk = 1;

		Help_DeskED Help_DeskED = new Help_DeskED();

		try {
			
			ResultSet rs = executasql.executarConsulta(sql);
			while (rs.next()) {
				valOid = rs.getLong("result");
				nr_Help_Desk = rs.getLong("nr_Help_Desk");
			}

			valOid++;
			nr_Help_Desk++;
			
			if (ed.getNr_Help_Desk() == 0)
				ed.setNr_Help_Desk(nr_Help_Desk++);
			
			sql = " insert into help_desk ( " + "oid_Help_Desk, "
					+ "nr_Help_Desk, " 
					+ "oid_Empresa, " 
					+ "oid_Usuario, "
					+ "nm_Descricao_Servico1, " 
					+ "nm_Descricao_Servico2, "
					+ "nm_Descricao_Servico3, " 
					+ "nm_Descricao_Servico4, "
					+ "nm_Descricao_Servico5, "
					+ "nm_Solicitante, "
					+ "dm_Classificacao, " 
					+ "dm_Prioridade, "
					+ "dt_Necessidade_Entrega, "
					+ "dm_Situacao_Ordem_Servico, " 
					+ "dm_Origem, "
					+ "dt_Solicitacao, "
					+ "hr_Solicitacao) values";

			sql += "(" + valOid + "," 
					+ ed.getNr_Help_Desk() + ","
					+ ed.getOid_Empresa() + "," 
					+ ed.getOid_Usuario() + ","
					+ (doValida(ed.getNm_Descricao_Servico1()) ? "'" + ed.getNm_Descricao_Servico1()+ "'" : "null") + ", "
					+ (doValida(ed.getNm_Descricao_Servico2()) ? "'" + ed.getNm_Descricao_Servico2()+ "'" : "null") + ", "
					+ (doValida(ed.getNm_Descricao_Servico3()) ? "'" + ed.getNm_Descricao_Servico3()+ "'" : "null") + ", "
					+ (doValida(ed.getNm_Descricao_Servico4()) ? "'" + ed.getNm_Descricao_Servico4()+ "'" : "null") + ", "
					+ (doValida(ed.getNm_Descricao_Servico5()) ? "'" + ed.getNm_Descricao_Servico5()+ "'" : "null") + ", "
					+ (doValida(ed.getNm_Solicitante()) ? "'" + ed.getNm_Solicitante()+ "'" : "null") + ", '"
					+ ed.getDm_Classificacao() + "','" 
					+ ed.getDm_Prioridade() + "'," 
					+ (doValida(ed.getDt_Necessidade_Entrega()) ? "'" + ed.getDt_Necessidade_Entrega()+ "'" : "null") + ",'"
					+ ed.getDm_Situacao_Ordem_Servico() + "','"
					+ ed.getDm_Origem() +"'," 
					+ (doValida(ed.getDt_Solicitacao()) ? "'" + ed.getDt_Solicitacao()+ "'" : "null") + ", "
					+ (doValida(ed.getHr_Solicitacao()) ? "'" + ed.getHr_Solicitacao()+ "'" : "null") + ")";
				 
			executasql.executarUpdate(sql);
     
			Help_DeskED.setOid_Help_Desk(valOid);

			// this.enviaMail(Help_DeskED, "N");

		} catch (Exception exc) {
			exc.printStackTrace();
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao incluir a Ordem de Serviço");
			excecoes.setMetodo("inclui");
			excecoes.setExc(exc);
			throw excecoes;
		}
		return Help_DeskED;

	}
	
	// ============== Altera Tela Suporte_SPM =======================\\
	public void altera_Suporte_Spm(Help_DeskED ed) throws Excecoes {
		String sql = null;
		try {
						
			sql = "UPDATE " + " Help_Desk " + "SET " 
					+ " dm_classificacao = '" + ed.getDm_Classificacao() + "', "
					+ " dm_prioridade = '" + ed.getDm_Prioridade() + "', "
					+ " dt_necessidade_entrega = " + (doValida(ed.getDt_Necessidade_Entrega()) ? "'" + ed.getDt_Necessidade_Entrega()+ "'" : "null") + ", "
					+ " dt_solicitacao = " + (doValida(ed.getDt_Solicitacao()) ? "'" + ed.getDt_Solicitacao()+ "'" : "null") + ", "
					+ " hr_solicitacao = " + (doValida(ed.getHr_Solicitacao()) ? "'" + ed.getHr_Solicitacao()+ "'" : "null") + ", "
					+ " dm_situacao_ordem_servico = '" + ed.getDm_Situacao_Ordem_Servico() + "', "
					+ " nm_descricao_servico1 = " + (doValida(ed.getNm_Descricao_Servico1()) ? "'" + ed.getNm_Descricao_Servico1()+ "'" : "null") + ", "
					+ " nm_descricao_servico2 = " + (doValida(ed.getNm_Descricao_Servico2()) ? "'" + ed.getNm_Descricao_Servico2()+ "'" : "null") + ", "
					+ " nm_descricao_servico3 = " + (doValida(ed.getNm_Descricao_Servico3()) ? "'" + ed.getNm_Descricao_Servico3()+ "'" : "null") + ", "
					+ " nm_descricao_servico4 = " + (doValida(ed.getNm_Descricao_Servico4()) ? "'" + ed.getNm_Descricao_Servico4()+ "'" : "null") + ", "
					+ " nm_descricao_servico5 = " + (doValida(ed.getNm_Descricao_Servico5()) ? "'" + ed.getNm_Descricao_Servico5()+ "'" : "null") + ", "
					+ "nm_solicitante = " + (doValida(ed.getNm_Solicitante()) ? "'" + ed.getNm_Solicitante()+ "'" : "null") + " ";
			
			sql += " where oid_help_desk = '" + ed.getOid_Help_Desk() + "'";

			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(),
					"altera_Suporte_Spm(Help_DeskED ed)");
		}
	}

	// =============== Altera Tela Usuario =======================\\
	public void altera_Usuario(Help_DeskED ed) throws Excecoes {
		String sql = null;
		try {
			
			sql = "UPDATE " + " Help_Desk " + "SET "
					+ " dm_classificacao = '" + ed.getDm_Classificacao() + "', "
					+ " dm_prioridade = '" + ed.getDm_Prioridade() + "', "
					+ " dt_necessidade_entrega = " + (doValida(ed.getDt_Necessidade_Entrega()) ? "'" + ed.getDt_Necessidade_Entrega()+ "'" : "null") + ", "
					+ " nm_Solicitante = " + (doValida(ed.getNm_Solicitante()) ? "'" + ed.getNm_Solicitante()+ "'" : "null") + ", "
					+ " nm_descricao_servico1 = " + (doValida(ed.getNm_Descricao_Servico1()) ? "'" + ed.getNm_Descricao_Servico1()+ "'" : "null") + ", "
					+ " nm_descricao_servico2 = " + (doValida(ed.getNm_Descricao_Servico2()) ? "'" + ed.getNm_Descricao_Servico2()+ "'" : "null") + ", "
					+ " nm_descricao_servico3 = " + (doValida(ed.getNm_Descricao_Servico3()) ? "'" + ed.getNm_Descricao_Servico3()+ "'" : "null") + ", "
					+ " nm_descricao_servico4 = " + (doValida(ed.getNm_Descricao_Servico4()) ? "'" + ed.getNm_Descricao_Servico4()+ "'" : "null") + ", "
					+ " nm_descricao_servico5 = " + (doValida(ed.getNm_Descricao_Servico5()) ? "'" + ed.getNm_Descricao_Servico5()+ "'" : "null") + " ";
			
			sql += " where oid_help_desk = '" + ed.getOid_Help_Desk() + "'";

			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "altera_Usuario(Help_DeskED ed)");
		}
	}
	
	//=============== Altera Tela Orçamento =======================\\
	public void altera_Orcamento(Help_DeskED ed) throws Excecoes {
		String sql = null;
		try {
			
			
			sql = "UPDATE " + " Help_Desk " + "SET "
					+ " oid_Tecnico_Orcamento = " + ed.getOid_Tecnico_Orcamento() + ", "
					+ " dm_situacao_orcamento = '"+ ed.getDm_Situacao_Orcamento() + "', "
					+ " dt_orcamento = " + (doValida(ed.getDt_Orcamento()) ? "'" + ed.getDt_Orcamento()+ "'" : "null") + ", "
					+ " hr_orcamento = " + (doValida(ed.getHr_Orcamento()) ? "'" + ed.getHr_Orcamento()+ "'" : "null") + ", "
					+ " dm_adicional = '" + ed.getDm_Adicional() + "', "
					+ " hr_Orcadas = " + ed.getHr_Orcadas() + ", "
					+ " dm_situacao_ordem_servico = '" + ed.getDm_Situacao_Ordem_Servico() + "', "
					+ " vl_investimento = " + (doValida(ed.getVl_Investimento()) ? "'" + ed.getVl_Investimento()+ "'" : "null") + " ";
			
			sql += " where oid_help_desk = '" + ed.getOid_Help_Desk() + "'";

			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(),
					"altera_Orcamento(Help_DeskED ed)");
		}
	}
	
	//=============== Altera Tela Aceite =======================\\
	public void altera_Aceite(Help_DeskED ed) throws Excecoes {
		String sql = null;
		try {
			
			
			sql = "UPDATE " + " Help_Desk " + "SET "
					+ " dt_aceite = " + (doValida(ed.getDt_Aceite()) ? "'" + ed.getDt_Aceite()+ "'" : "null") + ", "
					+ " hr_aceite = " + (doValida(ed.getHr_Aceite()) ? "'" + ed.getHr_Aceite()+ "'" : "null") + ", "
					+ " vl_aceite = " + (doValida(ed.getVl_Aceite()) ? "'" + ed.getVl_Aceite()+ "'" : "null") + ", "
					+ " nm_responsavel_aceite = '" + ed.getNm_Responsavel_Aceite() + "', "
					+ " dm_situacao_ordem_servico = '" + ed.getDm_Situacao_Ordem_Servico() + "', "
					+ " nm_visto_eletronico = " + (doValida(ed.getNm_Visto_Eletronico()) ? "'" + ed.getNm_Visto_Eletronico()+ "'" : "null") + " ";
			
			sql += " where oid_help_desk = '" + ed.getOid_Help_Desk() + "'";

			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(),
					"altera_Aceite(Help_DeskED ed)");
		}
	}

	// ================= Altera Tela Desenvolvimento ===================\\
	public void altera_Desenvolvimento(Help_DeskED ed) throws Excecoes {
		String sql = null;
		try {
			
			sql = "UPDATE " + " Help_Desk " + "SET "
			        + " oid_Tecnico_Desenvolvedor = " + ed.getOid_Tecnico_Desenvolvedor() + ", "
			        + " vl_custo = " + (doValida(ed.getVl_Custo()) ? "'" + ed.getVl_Custo()+ "'" : "null") + ", "
			        + " dt_conclusao = "  + (doValida(ed.getDt_Conclusao()) ? "'" + ed.getDt_Conclusao()+ "'" : "null") + ", "
					+ " dt_inicio = "  + (doValida(ed.getDt_Inicio()) ? "'" + ed.getDt_Inicio()+ "'" : "null") + ", "
					+ " hr_conclusao = "  + (doValida(ed.getHr_Conclusao()) ? "'" + ed.getHr_Conclusao()+ "'" : "null") + ", "
					+ " hr_inicio = "  + (doValida(ed.getHr_Inicio()) ? "'" + ed.getHr_Inicio()+ "'" : "null") + ", "
					+ " dm_situacao_ordem_servico = '" + ed.getDm_Situacao_Ordem_Servico() + "', "
					+ " tx_desenvolvimento1 = "  + (doValida(ed.getTx_Desenvolvimento1()) ? "'" + ed.getTx_Desenvolvimento1() + "'" : "null") + ", "
					+ " tx_desenvolvimento2 = " + (doValida(ed.getTx_Desenvolvimento2()) ? "'" + ed.getTx_Desenvolvimento2() + "'" : "null") + " ";
			
			sql += " where oid_help_desk = '" + ed.getOid_Help_Desk() + "'";

			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(),
					"altera_Desenvolvimento(Help_DeskED ed)");
		}
	}

	// ================== Altera Tela Homologação ========================\\
	public void altera_Homologacao(Help_DeskED ed) throws Excecoes {
		String sql = null;
		try {
			
			sql = "UPDATE " + " Help_Desk " + "SET "
			        + " oid_Tecnico_Homologacao = " + ed.getOid_Tecnico_Homologacao() + ", "
					+ " dt_homologacao = " + (doValida(ed.getDt_Homologacao()) ? "'" + ed.getDt_Homologacao()+ "'" : "null") + ", "
					+ " hr_homologacao = " + (doValida(ed.getHr_Homologacao()) ? "'" + ed.getHr_Homologacao()+ "'" : "null") + ", "
					+ " dm_situacao_ordem_servico = '" + ed.getDm_Situacao_Ordem_Servico() + "', "
					+ " tx_homologacao1 = " + (doValida(ed.getTx_Homologacao1()) ? "'" + ed.getTx_Homologacao1()+ "'" : "null") + ", "
					+ " tx_homologacao2 = " + (doValida(ed.getTx_Homologacao2()) ? "'" + ed.getTx_Homologacao2()+ "'" : "null") + " ";
			
			sql += " where oid_help_desk = '" + ed.getOid_Help_Desk() + "'";

			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(),
					"altera_Homologacao(Help_DeskED ed)");
		}
	}

	// ==================== Lista Tela Usuário ===========================\\
	public ArrayList lista(Help_DeskED ed) throws Excecoes {

		String sql = null;
		ArrayList list = new ArrayList();
		try {

			sql = " SELECT " +
			  " e.nm_empresa, " +
			  " u.nm_usuario, " +
			  " h.*, " +
			  " t1.cd_tecnico as cd_Tecnico_Orcamento,  " +
			  " t1.nm_tecnico as nm_Tecnico_Orcamento, " +
			  " t2.cd_tecnico as cd_Tecnico_Homologacao, " +
			  " t2.nm_tecnico as nm_Tecnico_Homologacao, " +
			  " t3.cd_tecnico as cd_Tecnico_Desenvolvedor, " +
			  " t3.nm_tecnico as nm_Tecnico_Desenvolvedor" + 
			  " FROM " +
			  " help_desk as h " +
			  " left join empresas_help as e on h.oid_empresa = e.oid_empresa " +
			  " left join usuarios as u on h.oid_usuario = u.oid_usuario " +
			  " left join tecnicos as t1 on h.oid_tecnico_orcamento = t1.oid_tecnico " +
			  " left join tecnicos as t2 on h.oid_tecnico_homologacao = t2.oid_tecnico " +
			  " left join tecnicos as t3 on h.oid_tecnico_desenvolvedor = t3.oid_tecnico " + 
			  " WHERE 1=1 ";
			
			if (ed.getOid_Help_Desk() > 0)
				sql += "   AND h.oid_help_desk = "
						+ ed.getOid_Help_Desk();
			else {
				if (ed.getNr_Help_Desk() > 0)
					sql += "   AND h.nr_help_desk = "
							+ ed.getNr_Help_Desk();
				
				if (doValida(ed.getDt_Solicitacao()))
					sql += "   AND h.dt_Solicitacao = '"
							+ ed.getDt_Solicitacao() + "'";
				
				if (ed.getOid_Empresa() > 0)
					sql += "   AND h.oid_Empresa = "
							+ ed.getOid_Empresa();
				
				if (doValida(ed.getNm_Empresa()))
					sql += "   AND h.nm_Empresa = '"
							+ ed.getNm_Empresa() + "'";
				 
				if (doValida(ed.getNm_Usuario()))
					sql += "   AND h.nm_Usuario = '"
							+ ed.getNm_Usuario() + "'";
				
				if (doValida(ed.getDm_Classificacao()))
					sql += "   AND h.dm_Classificacao = '"
							+ ed.getDm_Classificacao() + "'";

				if (doValida(ed.getNm_Descricao_Servico1()))
					sql += "   AND h.nm_Descricao_Servico1 = '"
							+ ed.getNm_Descricao_Servico1() + "'";

				if (doValida(ed.getNm_Descricao_Servico2()))
					sql += "   AND h.nm_Descricao_Servico2 = '"
							+ ed.getNm_Descricao_Servico2() + "'";

				if (doValida(ed.getNm_Descricao_Servico3()))
					sql += "   AND h.nm_Descricao_Servico3 = '"
							+ ed.getNm_Descricao_Servico3() + "'";

				if (doValida(ed.getNm_Descricao_Servico4()))
					sql += "   AND h.nm_Descricao_Servico4 = '"
							+ ed.getNm_Descricao_Servico4() + "'";

				if (doValida(ed.getNm_Descricao_Servico5()))
					sql += "   AND h.nm_Descricao_Servico5 = '"
							+ ed.getNm_Descricao_Servico5() + "%'";
				
				if (doValida(ed.getDt_Solicitacao_Inicial())) {
				    sql += " AND h.dt_Solicitacao >= '" + ed.getDt_Solicitacao_Inicial()+ "' ";
				}
				if (doValida(ed.getDt_Solicitacao_Final())) {
				    sql += " AND h.dt_Solicitacao <= '" + ed.getDt_Solicitacao_Final()+ "' ";
				}
				
			}	
	            
			sql += " ORDER BY h.oid_help_desk";

			ResultSet res = this.executasql.executarConsulta(sql);

			
			while (res.next()) {
				Help_DeskED edVolta = new Help_DeskED();

				edVolta.setOid_Help_Desk(new Long(res.getString("oid_help_desk")).longValue());
				edVolta.setOid_Empresa(res.getLong("oid_Empresa"));
				edVolta.setNr_Help_Desk(res.getLong("nr_help_desk"));
				edVolta.setDt_Solicitacao(FormataData.formataDataBT(res.getString("dt_solicitacao")));
				edVolta.setNm_Empresa(res.getString("nm_empresa"));
				edVolta.setNm_Usuario(res.getString("nm_usuario"));
				edVolta.setNm_Solicitante(res.getString("nm_Solicitante"));
				edVolta.setDm_Classificacao(res.getString("dm_classificacao"));
				edVolta.setNm_Descricao_Servico1(res.getString("nm_descricao_servico1"));
				edVolta.setNm_Descricao_Servico2(res.getString("nm_descricao_servico2"));
				edVolta.setNm_Descricao_Servico3(res.getString("nm_descricao_servico3"));
				edVolta.setNm_Descricao_Servico4(res.getString("nm_descricao_servico4"));
				edVolta.setNm_Descricao_Servico5(res.getString("nm_descricao_servico5"));
				edVolta.setDm_Situacao_Ordem_Servico(res.getString("dm_situacao_ordem_servico"));
				
				list.add(edVolta);
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "lista(Help_DeskED ed)");
		}
	}

	// ================ Lista Tela Suporte ==========================\\
	public ArrayList lista2(Help_DeskED ed) throws Excecoes {

		String sql = null;
		ArrayList list2 = new ArrayList();
		try {

			sql = " SELECT " +
			  " e.nm_empresa, " +
			  " u.nm_usuario, " +
			  " h.*, " +
			  " t1.cd_tecnico as cd_Tecnico_Orcamento,  " +
			  " t1.nm_tecnico as nm_Tecnico_Orcamento, " +
			  " t2.cd_tecnico as cd_Tecnico_Homologacao, " +
			  " t2.nm_tecnico as nm_Tecnico_Homologacao, " +
			  " t3.cd_tecnico as cd_Tecnico_Desenvolvedor, " +
			  " t3.nm_tecnico as nm_Tecnico_Desenvolvedor" + 
			  " FROM " +
			  " help_desk as h " +
			  " left join empresas_help as e on h.oid_empresa = e.oid_empresa " +
			  " left join usuarios as u on h.oid_usuario = u.oid_usuario " +
			  " left join tecnicos as t1 on h.oid_tecnico_orcamento = t1.oid_tecnico " +
			  " left join tecnicos as t2 on h.oid_tecnico_homologacao = t2.oid_tecnico " +
			  " left join tecnicos as t3 on h.oid_tecnico_desenvolvedor = t3.oid_tecnico " + 
			  " WHERE 1=1 ";

			if (ed.getOid_Help_Desk() > 0)
				sql += "   AND h.oid_help_desk = " + ed.getOid_Help_Desk();
			else {
				if (ed.getOid_Empresa()>0)
					sql += "   AND h.oid_Empresa = " + ed.getOid_Empresa();
				
				if (ed.getNr_Help_Desk()>0)
					sql += "   AND h.nr_Help_Desk = " + ed.getNr_Help_Desk();
				
				if (ed.getOid_Tecnico_Orcamento()>0)
					sql += "   AND h.oid_Tecnico_Orcamento = " + ed.getOid_Tecnico_Orcamento();
				
				if (ed.getOid_Tecnico_Desenvolvedor()>0)
					sql += "   AND h.oid_Tecnico_Desenvolvedor = " + ed.getOid_Tecnico_Desenvolvedor();
				
				if (ed.getOid_Tecnico_Homologacao()>0)
					sql += "   AND h.oid_Tecnico_Homologacao = " + ed.getOid_Tecnico_Homologacao();
				
				if (doValida(ed.getDt_Solicitacao()))
					sql += "   AND h.dt_Solicitacao = '" + ed.getDt_Solicitacao() + "'";
				
				if (doValida(ed.getNm_Empresa()))
					sql += "   AND h.nm_Empresa = '" + ed.getNm_Empresa() + "'";
				
				if (doValida(ed.getDm_Classificacao()))
					sql += "   AND h.dm_Classificacao = '" + ed.getDm_Classificacao() + "'";
				
				if (doValida(ed.getDm_Situacao_Ordem_Servico())){
					if("Não Homologado".equals(ed.getDm_Situacao_Ordem_Servico())){
						sql += " AND h.dm_Situacao_Ordem_Servico not in ('Cancelada','Homologado')";
					}
					else
						sql += "   AND h.dm_Situacao_Ordem_Servico = '" + ed.getDm_Situacao_Ordem_Servico() + "'";
				}
				if (doValida(ed.getDt_Solicitacao_Inicial())) {
				    sql += " AND h.dt_Solicitacao >= '" + ed.getDt_Solicitacao_Inicial()+ "' ";
				}
				if (doValida(ed.getDt_Solicitacao_Final())) {
				    sql += " AND h.dt_Solicitacao <= '" + ed.getDt_Solicitacao_Final()+ "' ";
				}
			}
			
			sql += " ORDER BY ";
			if (doValida(ed.getDm_Ordenacao())){
				if ("OS".equals(ed.getDm_Ordenacao())) {
					sql += "h.nr_Help_Desk";
				} 
				if ("Origem".equals(ed.getDm_Ordenacao())) {
					sql += "h.dm_Origem";
				} 
				else if ("Solicitação".equals(ed.getDm_Ordenacao())) {
					sql += "h.dt_Solicitacao";
				}
				else if ("Empresa".equals(ed.getDm_Ordenacao())) {
					sql += "h.oid_Empresa";
				}
				else if ("Solicitante".equals(ed.getDm_Ordenacao())) {
					sql += "h.nm_Solicitante";
				}
				else if ("Classificação".equals(ed.getDm_Ordenacao())) {
					sql += "h.dm_Classificacao";
				}
				else if ("Prioridade".equals(ed.getDm_Ordenacao())) {
					sql += "h.dm_Prioridade";
				}
				else if ("Situação OS".equals(ed.getDm_Ordenacao())) {
					sql += "h.dm_Situacao_Ordem_Servico";
				}
			}
			else
				sql+="h.nr_help_desk";
			
			
			//sql += " ORDER BY h.nr_help_desk";

			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				Help_DeskED edVolta = new Help_DeskED();

				edVolta.setOid_Help_Desk(new Long(res.getString("oid_help_desk")).longValue());
				edVolta.setOid_Empresa(res.getLong("oid_Empresa"));
				edVolta.setOid_Tecnico_Orcamento(res.getLong("oid_Tecnico_Orcamento"));
				edVolta.setOid_Tecnico_Desenvolvedor(res.getLong("oid_Tecnico_Desenvolvedor"));
				edVolta.setOid_Tecnico_Homologacao(res.getLong("oid_Tecnico_Homologacao"));
				edVolta.setNr_Help_Desk(res.getLong("nr_Help_Desk"));
				edVolta.setHr_Orcadas(res.getLong("hr_Orcadas"));
				edVolta.setDt_Solicitacao(FormataData.formataDataBT(res.getString("dt_solicitacao")));
				edVolta.setDm_Classificacao(res.getString("dm_classificacao"));
				edVolta.setDm_Origem(res.getString("dm_Origem"));
				edVolta.setNm_Empresa(res.getString("nm_empresa"));
				edVolta.setNm_Usuario(res.getString("nm_usuario"));
				edVolta.setNm_Solicitante(res.getString("nm_Solicitante"));
				edVolta.setNm_Descricao_Servico1(res.getString("nm_descricao_servico1"));
				edVolta.setNm_Descricao_Servico2(res.getString("nm_descricao_servico2"));
				edVolta.setNm_Descricao_Servico3(res.getString("nm_descricao_servico3"));
				edVolta.setNm_Descricao_Servico4(res.getString("nm_descricao_servico4"));
				edVolta.setNm_Descricao_Servico5(res.getString("nm_descricao_servico5"));
				edVolta.setNm_Solicitante(res.getString("nm_solicitante"));
				edVolta.setDm_Prioridade(res.getString("dm_prioridade"));
				edVolta.setDm_Situacao_Ordem_Servico(res.getString("dm_situacao_ordem_servico"));
								
				list2.add(edVolta);
			}
			
			return list2;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "lista2(Help_DeskED ed)");
		}
	}

	// ==========================================================================\\
	public Help_DeskED getByRecord(Help_DeskED ed) throws Excecoes {
		String sql = null;
		Help_DeskED edQBR = new Help_DeskED();
		try {
			sql = " SELECT " +
			  " e.nm_empresa, " +
			  " u.nm_usuario, " +
			  " h.*, " +
			  " t1.cd_tecnico as cd_Tecnico_Orcamento,  " +
			  " t1.nm_tecnico as nm_Tecnico_Orcamento, " +
			  " t2.cd_tecnico as cd_Tecnico_Homologacao, " +
			  " t2.nm_tecnico as nm_Tecnico_Homologacao, " +
			  " t3.cd_tecnico as cd_Tecnico_Desenvolvedor, " +
			  " t3.nm_tecnico as nm_Tecnico_Desenvolvedor" + 
			  " FROM " +
			  " help_desk as h " +
			  " left join empresas_help as e on h.oid_empresa = e.oid_empresa " +
			  " left join usuarios as u on h.oid_usuario = u.oid_usuario " +
			  " left join tecnicos as t1 on h.oid_tecnico_orcamento = t1.oid_tecnico " +
			  " left join tecnicos as t2 on h.oid_tecnico_homologacao = t2.oid_tecnico " +
			  " left join tecnicos as t3 on h.oid_tecnico_desenvolvedor = t3.oid_tecnico " + 
			  " WHERE 1=1" ;
			
			if (ed.getOid_Help_Desk()>0) 
				sql+=" and oid_Help_Desk = " + ed.getOid_Help_Desk();
			if (ed.getNr_Help_Desk()>0)
				sql+=" and nr_Help_Desk = " + ed.getNr_Help_Desk();
				
			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			while (res.next()) {

				edQBR.setOid_Help_Desk(res.getLong("oid_Help_Desk"));
				edQBR.setNr_Help_Desk(res.getLong("nr_Help_Desk"));
				edQBR.setOid_Empresa(res.getLong("oid_Empresa"));
				edQBR.setOid_Usuario(res.getLong("oid_Usuario"));
				edQBR.setOid_Tecnico_Orcamento(res.getLong("oid_Tecnico_Orcamento"));
				edQBR.setOid_Tecnico_Homologacao(res.getLong("oid_Tecnico_Homologacao"));
				edQBR.setOid_Tecnico_Desenvolvedor(res.getLong("oid_Tecnico_Desenvolvedor"));
				edQBR.setCd_Tecnico_Orcamento(res.getString("cd_Tecnico_Orcamento"));
				edQBR.setCd_Tecnico_Homologacao(res.getString("cd_Tecnico_Homologacao"));
				edQBR.setCd_Tecnico_Desenvolvedor(res.getString("cd_Tecnico_Desenvolvedor"));
				edQBR.setVl_Investimento(res.getString("vl_Investimento"));
				edQBR.setVl_Aceite(res.getString("vl_Aceite"));
				edQBR.setVl_Custo(res.getString("vl_Custo"));
				edQBR.setNm_Descricao_Servico1(res.getString("nm_Descricao_Servico1"));
				edQBR.setNm_Descricao_Servico2(res.getString("nm_Descricao_Servico2"));
				edQBR.setNm_Descricao_Servico3(res.getString("nm_Descricao_Servico3"));
				edQBR.setNm_Descricao_Servico4(res.getString("nm_Descricao_Servico4"));
				edQBR.setNm_Descricao_Servico5(res.getString("nm_Descricao_Servico5"));
				edQBR.setNm_Descricao_Servico5(res.getString("nm_Descricao_Servico5"));
				edQBR.setNm_Solicitante(res.getString("nm_Solicitante"));
				edQBR.setNm_Empresa(res.getString("nm_Empresa"));
				edQBR.setNm_Usuario(res.getString("nm_Usuario"));
				edQBR.setNm_Tecnico_Orcamento(res.getString("nm_Tecnico_Orcamento"));
				edQBR.setNm_Tecnico_Desenvolvedor(res.getString("nm_Tecnico_Desenvolvedor"));
				edQBR.setNm_Tecnico_Homologacao(res.getString("nm_Tecnico_Homologacao"));
				edQBR.setNm_Visto_Eletronico(res.getString("nm_Visto_Eletronico"));
				edQBR.setNm_Responsavel_Aceite(res.getString("nm_Responsavel_Aceite"));
				edQBR.setDm_Classificacao(res.getString("dm_Classificacao"));
				edQBR.setDm_Adicional(res.getString("dm_Adicional"));
				edQBR.setDm_Situacao_Ordem_Servico(res.getString("dm_Situacao_Ordem_Servico"));
				edQBR.setDm_Situacao_Orcamento(res.getString("dm_Situacao_Orcamento"));
				edQBR.setDm_Valor_Hora(res.getString("dm_Valor_Hora"));
				edQBR.setDm_Prioridade(res.getString("dm_Prioridade"));
				edQBR.setDm_Origem(res.getString("dm_Origem"));
				edQBR.setDt_Orcamento(FormataData.formataDataBT(res.getString("dt_Orcamento")));
				edQBR.setDt_Necessidade_Entrega(FormataData.formataDataBT(res.getString("dt_Necessidade_Entrega")));
				edQBR.setDt_Solicitacao(FormataData.formataDataBT(res.getString("dt_Solicitacao")));
				edQBR.setDt_Aceite(FormataData.formataDataBT(res.getString("dt_Aceite")));
				edQBR.setDt_Conclusao(FormataData.formataDataBT(res.getString("dt_Conclusao")));
				edQBR.setDt_Inicio(FormataData.formataDataBT(res.getString("dt_Inicio")));
				edQBR.setDt_Homologacao(FormataData.formataDataBT(res.getString("dt_Homologacao")));
				edQBR.setHr_Solicitacao(res.getString("hr_Solicitacao"));
				edQBR.setHr_Orcadas(res.getLong("hr_Orcadas"));
				edQBR.setHr_Orcamento(res.getString("hr_Orcamento"));
				edQBR.setHr_Aceite(res.getString("hr_Aceite"));
				edQBR.setHr_Conclusao(res.getString("hr_Conclusao"));
				edQBR.setHr_Inicio(res.getString("hr_Inicio"));
				edQBR.setHr_Homologacao(res.getString("hr_Homologacao"));
				edQBR.setTx_Desenvolvimento1(res.getString("tx_Desenvolvimento1"));
				edQBR.setTx_Desenvolvimento2(res.getString("tx_Desenvolvimento2"));
				edQBR.setTx_Homologacao1(res.getString("tx_Homologacao1"));
				edQBR.setTx_Homologacao2(res.getString("tx_Homologacao2"));

			}
		} catch (Exception e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(),
					"getByRecord(SistemaED ed)");
		}
		return edQBR;
	}

}