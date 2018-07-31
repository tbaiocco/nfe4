package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Marca_VeiculoED;
import com.master.ed.Tipo_VeiculoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Ralph & Cristian
 * @serial Marcas de veículos
 * @serialData 02/2006
 */
public class Marca_VeiculoBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Marca_VeiculoBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Marca_VeiculoED inclui(Marca_VeiculoED ed) throws Excecoes {

		try {
			ed.setOid_Marca_Veiculo(getAutoIncremento("oid_Marca_Veiculo", "Marcas_Veiculos"));
			sql = "INSERT INTO Marcas_Veiculos (" +
				  "oid_Empresa, " +
			      "oid_Marca_Veiculo, " +
			      "nm_Marca_Veiculo, " +
			      "dm_Stamp, " +
			  	  "dt_Stamp, " +
			  	  "usuario_Stamp"+
				  ") " +
			      " VALUES (" +
			      ed.getOid_Empresa() + 
			      "," + ed.getOid_Marca_Veiculo() + 
			      ",'" + ed.getNm_Marca_Veiculo() + 
			      "','I'" +
			  	  ",'" + ed.getDt_stamp() + "'" +
			  	  ",'" + ed.getUsuario_Stamp() + "'" +
				  ")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Marca_VeiculoED ed)");
		}
	}

	public void altera(Marca_VeiculoED ed) throws Excecoes {

		try {
			sql = "UPDATE Marcas_Veiculos SET " +
				  "nm_Marca_Veiculo = '" + ed.getNm_Marca_Veiculo() + "' " +
				  ",dt_Stamp = '" + ed.getDt_stamp() + "' " +
				  ",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " + 
				  ",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
				  "WHERE " +
				  "oid_Empresa = " + ed.getOid_Empresa() + " and " +
				  "oid_Marca_Veiculo = " + ed.getOid_Marca_Veiculo();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Marca_VeiculoED ed)");
		}
	}

	public void deleta(Marca_VeiculoED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Marcas_Veiculos " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() + " and " +
			"oid_Marca_Veiculo = " + ed.getOid_Marca_Veiculo();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Marca_VeiculoED ed)");
		}
	}

	public ArrayList lista(Marca_VeiculoED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
	  			  ",usuario_Stamp as usu_Stmp " +
		  		  ",dt_Stamp as dt_Stmp " +
		  		  ",dm_Stamp as dm_Stmp " +
				  "FROM "+
				  "Marcas_Veiculos " +
				  "WHERE " +
				  "oid_Empresa = " + ed.getOid_Empresa() ; 
			if (doValida(ed.getNm_Marca_Veiculo()))
				sql += " and Marcas_Veiculos.nm_Marca_Veiculo like '" + ed.getNm_Marca_Veiculo() + "%' ";
			sql += " ORDER BY Marcas_Veiculos.nm_Marca_Veiculo";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public Marca_VeiculoED getByRecord(Marca_VeiculoED ed) throws Excecoes {

		Marca_VeiculoED edQBR = new Marca_VeiculoED();
		try {
			sql = "SELECT * " +
				  ",usuario_Stamp as usu_Stmp " +
		  		  ",dt_Stamp as dt_Stmp " +
		  		  ",dm_Stamp as dm_Stmp " +
				  "FROM Marcas_Veiculos " +
				  "WHERE " ;
			if (ed.getOid_Marca_Veiculo()>0)
				sql+="oid_marca_veiculo = " + ed.getOid_Marca_Veiculo() ;
			else
			if (doValida(ed.getNm_Marca_Veiculo()))
				sql += "oid_Empresa = " + ed.getOid_Empresa() + " " + 
				" and Marcas_Veiculos.nm_Marca_Veiculo = '" + ed.getNm_Marca_Veiculo() + "'" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Marca_VeiculoED ed)");
		}
		return edQBR;
	}

	private Marca_VeiculoED populaRegistro(ResultSet res) throws SQLException {
		Marca_VeiculoED ed = new Marca_VeiculoED();
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setOid_Marca_Veiculo(res.getInt("oid_Marca_Veiculo"));
		ed.setNm_Marca_Veiculo(res.getString("Nm_Marca_Veiculo"));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;
	}
}
