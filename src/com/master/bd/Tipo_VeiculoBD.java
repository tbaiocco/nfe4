package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Tipo_VeiculoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Ralph
 * @serial Tipos de Veículos
 * @serialData 06/2006
 */
public class Tipo_VeiculoBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Tipo_VeiculoBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Tipo_VeiculoED inclui(Tipo_VeiculoED ed) throws Excecoes {

		try {
			ed.setOid_Tipo_Veiculo(getAutoIncremento("oid_Tipo_Veiculo", "Tipos_Veiculos"));
			sql = "INSERT INTO Tipos_Veiculos (" +
				  " oid_Empresa, " +
			      " oid_Tipo_Veiculo," +
			      " nm_Tipo_Veiculo, " +
			      " dm_Stamp, " +
			  	  " dt_Stamp, " +
			  	  " usuario_Stamp "+
				  ") " +
			      " VALUES (" +
			      ed.getOid_Empresa() + 
			      "," + ed.getOid_Tipo_Veiculo() + 
			      ",'" + ed.getNm_Tipo_Veiculo() + 
			      "','I'" +
			  	  ",'" + ed.getDt_stamp() + "'" +
			  	  ",'" + ed.getUsuario_Stamp() + "'" +
				  ")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Tipo_VeiculoED ed)");
		}
	}

	public void altera(Tipo_VeiculoED ed) throws Excecoes {

		try {
			sql = "UPDATE Tipos_Veiculos SET " +
				  "nm_Tipo_Veiculo = '" + ed.getNm_Tipo_Veiculo() + "' " +
				  ",dt_Stamp = '" + ed.getDt_stamp() + "' " +
				  ",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " + 
				  ",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
				  "WHERE " +
				  "oid_Empresa = " + ed.getOid_Empresa() + " and " +
				  "oid_Tipo_Veiculo = " + ed.getOid_Tipo_Veiculo();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Tipo_VeiculoED ed)");
		}
	}

	public void deleta(Tipo_VeiculoED ed) throws Excecoes {

		try {
			sql = "DELETE FROM Tipos_Veiculos " +
				  "WHERE " +
				  "oid_Empresa = " + ed.getOid_Empresa() + " and " +
				  "oid_Tipo_Veiculo = " + ed.getOid_Tipo_Veiculo();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Tipo_VeiculoED ed)");
		}
	}

	public ArrayList lista(Tipo_VeiculoED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
				  ",usuario_Stamp as usu_Stmp " +
		  		  ",dt_Stamp as dt_Stmp " +
		  		  ",dm_Stamp as dm_Stmp " +
				  "FROM Tipos_Veiculos " +
				  "WHERE " +
				  "oid_Empresa = " + ed.getOid_Empresa() ; 
			if (doValida(ed.getNm_Tipo_Veiculo()))
				sql += " and Tipos_Veiculos.nm_Tipo_Veiculo like '" + ed.getNm_Tipo_Veiculo() + "%' ";
			sql += " ORDER BY Tipos_Veiculos.nm_Tipo_Veiculo";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public Tipo_VeiculoED getByRecord(Tipo_VeiculoED ed) throws Excecoes {

		Tipo_VeiculoED edQBR = new Tipo_VeiculoED();
		try {
			sql = "SELECT * " +
				  ",usuario_Stamp as usu_Stmp " +
		  		   ",dt_Stamp as dt_Stmp " +
		  		  ",dm_Stamp as dm_Stmp " +
				  "FROM Tipos_Veiculos " +
				  "WHERE " ;
			if (ed.getOid_Tipo_Veiculo()>0)
				sql+="oid_tipo_veiculo = " + ed.getOid_Tipo_Veiculo() ;
			else
			if (doValida(ed.getNm_Tipo_Veiculo()))
				sql += "oid_Empresa = " + ed.getOid_Empresa() + " " + 
				" and Tipos_Veiculos.nm_Tipo_Veiculo = '" + ed.getNm_Tipo_Veiculo() + "'" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Tipo_VeiculoED ed)");
		}
		return edQBR;
	}

	private Tipo_VeiculoED populaRegistro(ResultSet res) throws SQLException {
		Tipo_VeiculoED ed = new Tipo_VeiculoED();
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setOid_Tipo_Veiculo(res.getInt("oid_Tipo_Veiculo"));
		ed.setNm_Tipo_Veiculo(res.getString("Nm_Tipo_Veiculo"));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;
	}
}
