package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.EmpresaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Empresas
 * @serialData 06/2007
 */
public class EmpresaBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public EmpresaBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public EmpresaED inclui(EmpresaED ed) throws Excecoes {
		try {
			ed.setOid_Empresa(getAutoIncremento("oid_Empresa", "Empresas"));
			sql = "INSERT INTO Empresas " +
			"(" +
			"oid_Empresa, " +
			"Nm_Empresa," +
			"nr_Cnpj_Cpf, " +
			"oid_Pessoa, " +
			"dm_Situacao " +
			") " +
			" VALUES (" +
			ed.getOid_Empresa() + 
			",'" + ed.getNm_Empresa() + "' " +
			",'" + ed.getNr_Cnpj_Cpf() + "' " +
			",'" + ed.getOid_Pessoa() + "' " +
			",'" + ed.getDm_Situacao() + "' " +
			")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(EmpresaED ed)");
		}
	}

	public void altera(EmpresaED ed) throws Excecoes {
		try {
			sql = "UPDATE Empresas SET " +
			"nm_Empresa = '" + ed.getNm_Empresa() + "' " +
			"dm_Situacao = '" + ed.getDm_Situacao() + "' " +
			",dt_Stamp = '" + ed.getDt_stamp() + "' " +
			",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " + 
			",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(EmpresaED ed)");
		}
	}

	public void deleta(EmpresaED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Empresas " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(EmpresaED ed)");
		}
	}

	public ArrayList lista(EmpresaED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
			"FROM " +
			"Empresas " +
			"WHERE " ; 
			if (doValida(ed.getNm_Empresa()))
				sql += " Empresas.nm_Empresa like '" + ed.getNm_Empresa() + "%' ";
			sql += "ORDER BY " +
			"Empresas.nm_Empresa";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(EmpresaED ed)");
		}
	}

	public EmpresaED getByRecord(EmpresaED ed) throws Excecoes {

		EmpresaED edQBR = new EmpresaED();
		try {
			sql = "SELECT * " +
			"FROM Empresas " +
			"WHERE " ;
			if ( ed.getOid_Empresa()>0 ) 
				sql +=" oid_Empresa = " + ed.getOid_Empresa() ; 
			if (doValida(ed.getNm_Empresa()))
				sql +=" Empresas.nm_Empresa = '" + ed.getNm_Empresa() + "'" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(EmpresaED ed)");
		}
		return edQBR;
	}

	private EmpresaED populaRegistro(ResultSet res) throws SQLException {
		EmpresaED ed = new EmpresaED();
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setNm_Empresa(res.getString("nm_Empresa"));
		ed.setNr_Cnpj_Cpf(res.getString("nr_Cnpj_Cpf"));
		ed.setOid_Pessoa(res.getString("oid_Pessoa"));
		ed.setDm_Situacao(res.getString("dm_Situacao"));
		return ed;
	}
}
