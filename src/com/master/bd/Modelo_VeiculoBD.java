package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Modelo_VeiculoED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Cristian Vianna Garcia
 * @serial Modelo_Veiculo
 * @serialData 06/2007
 */
public class Modelo_VeiculoBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Modelo_VeiculoBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Modelo_VeiculoED inclui(Modelo_VeiculoED ed) throws Excecoes {
		try {
			ed.setOid_Modelo_Veiculo(getAutoIncremento("oid_Modelo_Veiculo", "Modelos_Veiculos"));
			sql = "INSERT INTO Modelos_Veiculos (" +
			"oid_Empresa, " +
			"oid_Modelo_Veiculo," +
			"nm_Modelo_Veiculo, " +
			"oid_Marca_Veiculo," +
			"oid_Tipo_Veiculo, " +
			"dm_Tipo_Chassis " +
			",dm_Stamp" +
		  	",dt_Stamp" +
		  	",usuario_Stamp"+
			") " +
			" VALUES (" +
			ed.getOid_Empresa() + 
			"," + ed.getOid_Modelo_Veiculo() + 
			",'" + ed.getNm_Modelo_Veiculo() +
			"'," + ed.getOid_Marca_Veiculo() +
			"," + ed.getOid_Tipo_Veiculo() +
			"," + ed.getDm_Tipo_Chassis() +
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Modelo_VeiculoED ed)");
		}
	}


	public void altera(Modelo_VeiculoED ed) throws Excecoes {
		try {
			sql = "UPDATE Modelos_Veiculos SET " +
			"nm_Modelo_Veiculo = '" + ed.getNm_Modelo_Veiculo() + "', " +
			"oid_Marca_Veiculo = " + ed.getOid_Marca_Veiculo() + ", " +
			"oid_Tipo_Veiculo = " + ed.getOid_Tipo_Veiculo() + ", " +
			"dm_Tipo_Chassis = " + ed.getDm_Tipo_Chassis() + " " +
			",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() + " and " +
			"oid_Modelo_Veiculo = " + ed.getOid_Modelo_Veiculo();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Modelo_VeiculoED ed)");
		}
	}

	public void deleta(Modelo_VeiculoED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Modelos_Veiculos " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() + " and " +
			"oid_Modelo_Veiculo = " + ed.getOid_Modelo_Veiculo();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Modelo_VeiculoED ed)");
		}
	}

	public ArrayList lista(Modelo_VeiculoED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT " +
			"* " +
			",Modelos_Veiculos.usuario_Stamp as usu_Stmp " +
	  		",Modelos_Veiculos.dt_Stamp as dt_Stmp " +
	  		",Modelos_Veiculos.dm_Stamp as dm_Stmp " +
			"FROM " +
			"Modelos_Veiculos , " +
			"Marcas_Veiculos , " +
			"Tipos_Veiculos " +
			"WHERE " +
			"Modelos_Veiculos.oid_Marca_Veiculo = Marcas_Veiculos.oid_Marca_veiculo "  +
			"and Modelos_Veiculos.oid_Tipo_Veiculo = Tipos_Veiculos.oid_Tipo_Veiculo " +
			"and Modelos_Veiculos.oid_Empresa = " + ed.getOid_Empresa() ;
			if (ed.getOid_Marca_Veiculo()>0)
				sql += " and Modelos_Veiculos.oid_Marca_Veiculo = " + ed.getOid_Marca_Veiculo() + " ";
			if (ed.getOid_Tipo_Veiculo()>0) 
				sql += " and Modelos_Veiculos.oid_Tipo_Veiculo = " + ed.getOid_Tipo_Veiculo() + " ";
			if (doValida(ed.getNm_Modelo_Veiculo()))
				sql += " and Modelos_Veiculos.nm_Modelo_Veiculo like '" + ed.getNm_Modelo_Veiculo() + "%' ";	
			sql += "ORDER BY " +
			"Modelos_Veiculos.nm_Modelo_Veiculo";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public Modelo_VeiculoED getByRecord(Modelo_VeiculoED ed) throws Excecoes {

		Modelo_VeiculoED edQBR = new Modelo_VeiculoED();
		try {
			sql = "SELECT " +
			"* " +
			",Modelos_Veiculos.usuario_Stamp as usu_Stmp " +
	  		",Modelos_Veiculos.dt_Stamp as dt_Stmp " +
	  		",Modelos_Veiculos.dm_Stamp as dm_Stmp " +
			"FROM " +
			"Modelos_Veiculos , " +
			"Marcas_Veiculos , " +
			"Tipos_Veiculos " +
			"WHERE " +	
			"Modelos_Veiculos.oid_Marca_Veiculo = Marcas_Veiculos.oid_Marca_veiculo "  +
			"and Modelos_Veiculos.oid_Tipo_Veiculo = Tipos_Veiculos.oid_Tipo_Veiculo " ;
			if (ed.getOid_Modelo_Veiculo() > 0 )
				sql+=" and Modelos_Veiculos.oid_Modelo_Veiculo = " + ed.getOid_Modelo_Veiculo();
			else {
				if (ed.getOid_Empresa()>0)
				sql += " and modelos_veiculos.oid_Empresa = " + ed.getOid_Empresa() ;
				if (doValida(ed.getNm_Modelo_Veiculo()))
					sql += " and Modelos_Veiculos.nm_Modelo_Veiculo = '" + ed.getNm_Modelo_Veiculo() + "'" ;
			    }
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Modelo_VeiculoED ed)");
		}
		return edQBR;
	}

	private Modelo_VeiculoED populaRegistro(ResultSet res) throws SQLException {
		Modelo_VeiculoED ed = new Modelo_VeiculoED();
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setOid_Modelo_Veiculo(res.getLong("oid_Modelo_Veiculo"));
		ed.setNm_Modelo_Veiculo(res.getString("Nm_Modelo_Veiculo"));
		ed.setOid_Marca_Veiculo(res.getLong("oid_Marca_Veiculo"));
		ed.setOid_Tipo_Veiculo(res.getLong("oid_Tipo_Veiculo"));
		ed.setNm_Marca_Veiculo(res.getString("Nm_Marca_Veiculo"));
		ed.setNm_Tipo_Veiculo(res.getString("Nm_Tipo_Veiculo"));
		ed.setDm_Tipo_Chassis(res.getLong("Dm_Tipo_Chassis"));
	    ed.setUsuario_Stamp(res.getString("usu_Stmp"));
	    ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
	    return ed;
	}
}
