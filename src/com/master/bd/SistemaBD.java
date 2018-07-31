package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.master.ed.SistemaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Sistemas
 * @serialData 05/2007
 */
public class SistemaBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public SistemaBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public SistemaED inclui(SistemaED ed) throws Excecoes {

		try {
			ed.setOid_Sistema(getAutoIncremento("oid_Sistema", "Sistemas"));
			sql = "INSERT INTO Sistemas (" +
			      "oid_Sistema," +
			      "cd_Sistema," +
			      "nm_Sistema " +
			      ") " +
			      " VALUES (" +
			      ed.getOid_Sistema() +  ",'" ;
			if (doValida(ed.getCd_Sistema()))
				sql+= ed.getCd_Sistema() ;
			else
				sql+= "";
			sql+= "','" + ed.getNm_Sistema() +
			      "')";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(SistemaED ed)");
		}
	}

	public void altera(SistemaED ed) throws Excecoes {

		
		
		try {
			sql = "UPDATE Sistemas SET "; 
			if (doValida(ed.getCd_Sistema()))
				sql +=  "cd_Sistema = '" + ed.getCd_Sistema()+ "' " ;
			else
				sql += "cd_Sistema = '' " ;
			sql +=",nm_Sistema = '" + ed.getNm_Sistema() + "' " +
				  ",dt_Stamp = '" + ed.getDt_stamp() + "' " +
				  ",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " + 
				  ",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
				  "WHERE " +
				  "oid_Sistema = " + ed.getOid_Sistema();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(SistemaED ed)");
		}
	}

	public void deleta(SistemaED ed) throws Excecoes {
		
		try {
			sql = "DELETE FROM Sistemas " +
				  "WHERE " +
				  "oid_Sistema = " + ed.getOid_Sistema();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(SistemaED ed)");
		}
	}

	public ArrayList lista(SistemaED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
				  "FROM Sistemas " ;
			if (doValida(ed.getNm_Sistema()))
				sql+= "WHERE nm_Sistema like '%" + ed.getNm_Sistema() + "%' "; 
			sql+= "ORDER BY Sistemas.nm_Sistema";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public SistemaED getByRecord(SistemaED ed) throws Excecoes {

		SistemaED edQBR = new SistemaED();
		try {
			sql = "SELECT * " +
				  "FROM Sistemas " +
				  "WHERE " +
				  "Sistemas.nm_Sistema = '" + ed.getNm_Sistema() + "'" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(SistemaED ed)");
		}
		return edQBR;
	}

	private SistemaED populaRegistro(ResultSet res) throws SQLException {
		SistemaED ed = new SistemaED();
		ed.setOid_Sistema(res.getInt("oid_Sistema"));
		ed.setCd_Sistema(res.getString("cd_Sistema"));
		ed.setNm_Sistema(res.getString("Nm_Sistema"));
		return ed;
	}
}
