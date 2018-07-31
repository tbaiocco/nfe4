package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.master.ed.Combo_SituacaoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Cristian Vianna Garcia
 * @serial Combo_Prioridade
 * @serialData 05/2007
 */
public class Combo_SituacaoBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Combo_SituacaoBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Combo_SituacaoED inclui(Combo_SituacaoED ed) throws Excecoes {

		try {
			ed.setOid_Situacao(getAutoIncremento("oid_Situacao", "Controle_Tarefas"));
			sql = "INSERT INTO Combo_Situacao (" +
			      "oid_Situacao," +
			      "cd_Situacao," +
			      "nm_Situacao " +
			      ") " +
			      " VALUES (" +
			      ed.getOid_Situacao() + 
			      ",'" + ed.getCd_Situacao() + 
			      ",'" + ed.getNm_Situacao() +
			      "')";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Combo_SituacaoED ed)");
		}
	}

	public void altera(Combo_SituacaoED ed) throws Excecoes {

		try {
			sql = "UPDATE Combo_Situacao SET " +
				  "cd_Situacao = '" + ed.getCd_Situacao() + "' " +
				  ",nm_Situacao = '" + ed.getNm_Situacao() + "' " +
				  ",dt_Stamp = '" + ed.getDt_stamp() + "' " +
				  ",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " + 
				  ",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
				  "WHERE " +
				  "oid_Situacao = " + ed.getOid_Situacao();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Combo_SituacaoED ed)");
		}
	}

	public void deleta(Combo_SituacaoED ed) throws Excecoes {

		try {
			sql = "DELETE FROM Combo_Situacao " +
				  "WHERE " +
				  "oid_Situacao = " + ed.getOid_Situacao();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Combo_SituacaoED ed)");
		}
	}

	public ArrayList lista(Combo_SituacaoED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
				  "FROM Combo_Situacao " +
				  "ORDER BY Combo_Situacao.oid_Situacao";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public Combo_SituacaoED getByRecord(Combo_SituacaoED ed) throws Excecoes {

		Combo_SituacaoED edQBR = new Combo_SituacaoED();
		try {
			sql = "SELECT * " +
				  "FROM Combo_Situacao " +
				  "WHERE " +
				  "Combo_Situacao.nm_Situacao = '" + ed.getNm_Situacao() + "'" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Combo_SituacaoED ed)");
		}
		return edQBR;
	}

	private Combo_SituacaoED populaRegistro(ResultSet res) throws SQLException {
		Combo_SituacaoED ed = new Combo_SituacaoED();
		ed.setOid_Situacao(res.getInt("oid_Situacao"));
		ed.setCd_Situacao(res.getString("cd_Situacao"));
		ed.setNm_Situacao(res.getString("Nm_Situacao"));
		return ed;
	}
}
