package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.master.ed.Combo_PrioridadeED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Cristian Vianna Garcia
 * @serial Combo_Prioridade
 * @serialData 05/2007
 */
public class Combo_PrioridadeBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Combo_PrioridadeBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Combo_PrioridadeED inclui(Combo_PrioridadeED ed) throws Excecoes {

		try {
			ed.setOid_Prioridade(getAutoIncremento("oid_Prioridade", "Controle_Tarefas"));
			sql = "INSERT INTO Combo_Prioridade (" +
			      "oid_Prioridade," +
			      "cd_Prioridade," +
			      "nm_Prioridade " +
			      ") " +
			      " VALUES (" +
			      ed.getOid_Prioridade() + 
			      ",'" + ed.getCd_Prioridade() + 
			      ",'" + ed.getNm_Prioridade() +
			      "')";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Combo_PrioridadeED ed)");
		}
	}

	public void altera(Combo_PrioridadeED ed) throws Excecoes {

		try {
			sql = "UPDATE Combo_Prioridade SET " +
				  "cd_Prioridade = '" + ed.getCd_Prioridade() + "' " +
				  ",nm_Prioridade = '" + ed.getNm_Prioridade() + "' " +
				  ",dt_Stamp = '" + ed.getDt_stamp() + "' " +
				  ",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " + 
				  ",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
				  "WHERE " +
				  "oid_Prioridade = " + ed.getOid_Prioridade();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Combo_PrioridadeED ed)");
		}
	}

	public void deleta(Combo_PrioridadeED ed) throws Excecoes {

		try {
			sql = "DELETE FROM Combo_Prioridade " +
				  "WHERE " +
				  "oid_Prioridade = " + ed.getOid_Prioridade();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Combo_PrioridadeED ed)");
		}
	}

	public ArrayList lista(Combo_PrioridadeED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
				  "FROM Combo_Prioridade " +
				  "ORDER BY Combo_Prioridade.oid_Prioridade";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public Combo_PrioridadeED getByRecord(Combo_PrioridadeED ed) throws Excecoes {

		Combo_PrioridadeED edQBR = new Combo_PrioridadeED();
		try {
			sql = "SELECT * " +
				  "FROM Combo_Prioridade " +
				  "WHERE " +
				  "Combo_Prioridade.nm_Prioridade = '" + ed.getNm_Prioridade() + "'" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Combo_PrioridadeED ed)");
		}
		return edQBR;
	}

	private Combo_PrioridadeED populaRegistro(ResultSet res) throws SQLException {
		Combo_PrioridadeED ed = new Combo_PrioridadeED();
		ed.setOid_Prioridade(res.getInt("oid_Prioridade"));
		ed.setCd_Prioridade(res.getString("cd_Prioridade"));
		ed.setNm_Prioridade(res.getString("Nm_Prioridade"));
		return ed;
	}
}
