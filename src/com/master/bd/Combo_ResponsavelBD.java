package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.master.ed.Combo_ResponsavelED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Cristian Vianna Garcia
 * @serial Responsavel
 * @serialData 05/2007
 */
public class Combo_ResponsavelBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Combo_ResponsavelBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Combo_ResponsavelED inclui(Combo_ResponsavelED ed) throws Excecoes {

		try {
			ed.setOid_Responsavel(getAutoIncremento("oid_Responsavel", "Controle_Tarefas"));
			sql = "INSERT INTO Combo_Responsavel (" +
			      "oid_Responsavel," +
			      "cd_Responsavel," +
			      "nm_Responsavel " +
			      ") " +
			      " VALUES (" +
			      ed.getOid_Responsavel() + 
			      ",'" + ed.getCd_Responsavel() + 
			      ",'" + ed.getNm_Responsavel() +
			      "')";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Combo_ResponsavelED ed)");
		}
	}

	public void altera(Combo_ResponsavelED ed) throws Excecoes {

		try {
			sql = "UPDATE Combo_Responsavel SET " +
				  "cd_Responsavel = '" + ed.getCd_Responsavel() + "' " +
				  ",nm_Responsavel = '" + ed.getNm_Responsavel() + "' " +
				  ",dt_Stamp = '" + ed.getDt_stamp() + "' " +
				  ",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " + 
				  ",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
				  "WHERE " +
				  "oid_Responsavel = " + ed.getOid_Responsavel();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Combo_ResponsavelED ed)");
		}
	}

	public void deleta(Combo_ResponsavelED ed) throws Excecoes {

		try {
			sql = "DELETE FROM Combo_Responsavel " +
				  "WHERE " +
				  "oid_Responsavel = " + ed.getOid_Responsavel();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Combo_ResponsavelED ed)");
		}
	}

	public ArrayList lista(Combo_ResponsavelED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
				  "FROM Combo_Responsavel " +
				  "ORDER BY Combo_Responsavel.nm_Responsavel";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public Combo_ResponsavelED getByRecord(Combo_ResponsavelED ed) throws Excecoes {

		Combo_ResponsavelED edQBR = new Combo_ResponsavelED();
		try {
			sql = "SELECT * " +
				  "FROM Combo_Responsavel " +
				  "WHERE " +
				  "Combo_Responsavel.nm_Responsavel = '" + ed.getNm_Responsavel() + "'" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Combo_ResponsavelED ed)");
		}
		return edQBR;
	}

	private Combo_ResponsavelED populaRegistro(ResultSet res) throws SQLException {
		Combo_ResponsavelED ed = new Combo_ResponsavelED();
		ed.setOid_Responsavel(res.getInt("oid_Responsavel"));
		ed.setCd_Responsavel(res.getString("cd_Responsavel"));
		ed.setNm_Responsavel(res.getString("Nm_Responsavel"));
		return ed;
	}
}
