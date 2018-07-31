package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.master.ed.Controle_TarefasED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Cristian Vianna Garcia
 * @serial Controle de Tarefas
 * @serialData 05/2006
 */
public class Controle_TarefasBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Controle_TarefasBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql; 
	}

	public Controle_TarefasED inclui(Controle_TarefasED ed) throws Excecoes {

		try {
			ed.setOid_Tarefa(getAutoIncremento("oid_tarefa", "controle_tarefas"));
			sql = "INSERT INTO controle_tarefas (" +
			      "oid_tarefa," +
			      "nm_titulo," +
			      "nm_descricao," +
			      "oid_responsavel," +
			      "oid_prioridade," +
			      "oid_situacao " +
			      ") " +
			      " VALUES (" +
			      ed.getOid_Tarefa() + 
			      ",'" + ed.getNM_Titulo() + 
			      "','" + ed.getNM_Descricao() +
			      "'," + ed.getOid_Responsavel() +
			      "," + ed.getOid_Prioridade() +
			      "," + ed.getOid_Situacao() +
			      ")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Controle_TarefaED ed)");
		}
	}

	public void altera(Controle_TarefasED ed) throws Excecoes {

		try {
			sql = "UPDATE controle_tarefas SET " +
				  "nm_titulo = '" + ed.getNM_Titulo() + "' " +
				  ",nm_descricao = '" + ed.getNM_Descricao() + "' " +
				  ",oid_responsavel = '" + ed.getOid_Responsavel() + "' " +
				  ",oid_prioridade = '" + ed.getOid_Prioridade() + "' " +
				  ",oid_situacao = '" + ed.getOid_Situacao() + "' " +
				  "WHERE " +
				  "oid_tarefa = " + ed.getOid_Tarefa();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Controle_TarefasED ed)");
		}
	}

	public void deleta(Controle_TarefasED ed) throws Excecoes {

		try {
			sql = "DELETE FROM controle_tarefas " +
				  "WHERE " +
				  "oid_tarefa = " + ed.getOid_Tarefa ();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Controle_TarefasED ed)");
		}
	}

	public ArrayList lista(Controle_TarefasED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			if ((ed.getOid_Responsavel()>0) && (ed.getOid_Prioridade()>0)){
					sql = "SELECT controle_tarefas.* , combo_responsavel.* , combo_prioridade.* , combo_situacao.* "       +
					"FROM controle_tarefas , combo_responsavel , combo_prioridade , combo_situacao " +
					"WHERE " +
					"controle_tarefas.oid_responsavel = " + ed.getOid_Responsavel();					
					sql += " and controle_tarefas.oid_responsavel = " + ed.getOid_Responsavel() + " ";
					sql += " and controle_tarefas.oid_responsavel = combo_responsavel.oid_responsavel ";
					sql += " and controle_tarefas.oid_prioridade = " + ed.getOid_Prioridade() + " ";
					sql += " and controle_tarefas.oid_prioridade = combo_prioridade.oid_prioridade ";
					sql += " and controle_tarefas.oid_situacao = combo_situacao.oid_situacao ";
					sql += " ORDER BY controle_tarefas.oid_responsavel";
			}else
			if ((ed.getOid_Responsavel()>0) && (ed.getOid_Situacao()>0)){
					sql = "SELECT controle_tarefas.* , combo_responsavel.* , combo_prioridade.* , combo_situacao.* "       +
					"FROM controle_tarefas , combo_responsavel , combo_prioridade , combo_situacao " +
					"WHERE " +
					"controle_tarefas.oid_responsavel = " + ed.getOid_Responsavel();					
					sql += " and controle_tarefas.oid_responsavel = " + ed.getOid_Responsavel() + " ";
					sql += " and controle_tarefas.oid_responsavel = combo_responsavel.oid_responsavel ";
					sql += " and controle_tarefas.oid_situacao = " + ed.getOid_Situacao() + " ";
					sql += " and controle_tarefas.oid_situacao = combo_situacao.oid_situacao ";
					sql += " and controle_tarefas.oid_prioridade = combo_prioridade.oid_prioridade ";
					sql += " ORDER BY controle_tarefas.oid_responsavel";
			}else
				if ((ed.getOid_Prioridade()>0) && (ed.getOid_Situacao()>0)){
					sql = "SELECT controle_tarefas.* , combo_responsavel.* , combo_prioridade.* , combo_situacao.* "       +
					"FROM controle_tarefas , combo_responsavel , combo_prioridade , combo_situacao " +
					"WHERE " +
					"controle_tarefas.oid_prioridade = " + ed.getOid_Prioridade();					
					sql += " and controle_tarefas.oid_prioridade = " + ed.getOid_Prioridade() + " ";
					sql += " and controle_tarefas.oid_prioridade = combo_prioridade.oid_prioridade ";
					sql += " and controle_tarefas.oid_situacao = " + ed.getOid_Situacao() + " ";
					sql += " and controle_tarefas.oid_situacao = combo_situacao.oid_situacao ";
					sql += " and controle_tarefas.oid_responsavel = combo_responsavel.oid_responsavel ";
					sql += " ORDER BY controle_tarefas.oid_prioridade";
			}else
			if (ed.getOid_Responsavel()>0){
			sql = "SELECT controle_tarefas.* , combo_responsavel.* , combo_prioridade.* , combo_situacao.* "       +
				"FROM controle_tarefas , combo_responsavel , combo_prioridade , combo_situacao " +
				"WHERE " +
				"controle_tarefas.oid_responsavel = " + ed.getOid_Responsavel() ;	
				sql += " and controle_tarefas.oid_responsavel = " + ed.getOid_Responsavel() + " ";
				sql += " and controle_tarefas.oid_responsavel = combo_responsavel.oid_responsavel ";
				sql += " and controle_tarefas.oid_prioridade = combo_prioridade.oid_prioridade ";
				sql += " and controle_tarefas.oid_situacao = combo_situacao.oid_situacao ";
				sql += " ORDER BY controle_tarefas.oid_responsavel";
			}else
			if (ed.getOid_Prioridade()>0){
				sql = "SELECT controle_tarefas.* , combo_responsavel.* , combo_prioridade.* , combo_situacao.* "       +
				"FROM controle_tarefas , combo_responsavel , combo_prioridade , combo_situacao " +
				"WHERE " +
				"controle_tarefas.oid_prioridade = " + ed.getOid_Prioridade() ;	
				sql += " and controle_tarefas.oid_prioridade = " + ed.getOid_Prioridade() + " ";
				sql += " and controle_tarefas.oid_prioridade = combo_prioridade.oid_prioridade ";
				sql += " and controle_tarefas.oid_responsavel = combo_responsavel.oid_responsavel ";
				sql += " and controle_tarefas.oid_situacao = combo_situacao.oid_situacao ";
				sql += " ORDER BY controle_tarefas.oid_prioridade";
			}else
			if (ed.getOid_Situacao()>0){
				sql = "SELECT controle_tarefas.* , combo_responsavel.* , combo_prioridade.* , combo_situacao.* "       +
				"FROM controle_tarefas , combo_responsavel , combo_prioridade , combo_situacao " +
				"WHERE " +
				"controle_tarefas.oid_situacao = " + ed.getOid_Situacao() ;	
				sql += " and controle_tarefas.oid_situacao = " + ed.getOid_Situacao() + " ";
				sql += " and controle_tarefas.oid_situacao = combo_situacao.oid_situacao ";
				sql += " and controle_tarefas.oid_responsavel = combo_responsavel.oid_responsavel ";
				sql += " and controle_tarefas.oid_prioridade = combo_prioridade.oid_prioridade ";
				sql += " ORDER BY controle_tarefas.oid_prioridade";
			}else	
			if ((ed.getOid_Responsavel()==0) && (ed.getOid_Prioridade()==0)){
				sql = "SELECT controle_tarefas.* , combo_situacao.* , combo_responsavel.* , combo_prioridade.* " +
				"FROM controle_tarefas , combo_responsavel , combo_situacao , combo_prioridade " +
				"WHERE " +
				"controle_tarefas.oid_prioridade = combo_prioridade.oid_prioridade ";	
				sql += " and controle_tarefas.oid_prioridade = combo_prioridade.oid_prioridade ";
				sql += " and controle_tarefas.oid_responsavel = combo_responsavel.oid_responsavel ";
				sql += " and controle_tarefas.oid_situacao = combo_situacao.oid_situacao ";
				sql += " ORDER BY controle_tarefas.oid_responsavel";
			}
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public Controle_TarefasED getByRecord(Controle_TarefasED ed) throws Excecoes {
		Controle_TarefasED edQBR = new Controle_TarefasED();
		try {
			sql = "SELECT * " +
				  "FROM controle_tarefas " +
				  "WHERE " +
				  "oid_tarefa = " + ed.getOid_Tarefa() ; 
			if (doValida(ed.getNM_Titulo()))
				sql += " and controle_tarefas.nm_titulo = '" + ed.getNM_Titulo() + "'" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Controle_TarefasED ed)");
		}
		return edQBR;
	}

	private Controle_TarefasED populaRegistro(ResultSet res) throws SQLException {
		Controle_TarefasED ed = new Controle_TarefasED();
		ed.setOid_Tarefa(res.getInt("oid_Tarefa"));
		ed.setNM_Titulo(res.getString("NM_Titulo"));
		ed.setNM_Descricao(res.getString("NM_Descricao"));
		ed.setOid_Responsavel(res.getInt("oid_Responsavel"));
		ed.setNM_Responsavel(res.getString("NM_Responsavel"));
		ed.setOid_Prioridade(res.getInt("oid_Prioridade"));
		ed.setNM_Prioridade(res.getString("NM_Prioridade"));
		ed.setOid_Situacao(res.getInt("oid_Situacao"));
		ed.setNM_Situacao(res.getString("NM_Situacao"));
		return ed;
	}
}
