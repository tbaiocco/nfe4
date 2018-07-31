package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.master.ed.Marca_VeiculoED;
import com.master.ed.Tipo_PneuED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Tipos de pneus
 * @serialData 06/2007
 */
public class Tipo_PneuBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Tipo_PneuBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Tipo_PneuED inclui(Tipo_PneuED ed) throws Excecoes {
		try {
			ed.setOid_Tipo_Pneu(getAutoIncremento("oid_Tipo_Pneu", "Tipos_Pneus"));
			sql = "INSERT INTO Tipos_Pneus (" +
			"oid_Empresa, " +
			"oid_Tipo_Pneu," +
			"nm_Tipo_Pneu, " +
			"dm_Stamp, " +
		  	"dt_Stamp, " +
		  	"usuario_Stamp"+
		  	",oid_usuario"+
	  	    ",time_millis"+
			") " +
			" VALUES (" +
			ed.getOid_Empresa() +
			"," + ed.getOid_Tipo_Pneu() +
			",'" + ed.getNm_Tipo_Pneu() +
			"','I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		"," + ed.getUser() +
		  	"," + ed.getTime_millis() +
	  		")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Tipo_PneuED ed)");
		}
	}

	public void altera(Tipo_PneuED ed) throws Excecoes {
		try {
			sql = "UPDATE Tipos_Pneus SET " +
			"nm_Tipo_Pneu = '" + ed.getNm_Tipo_Pneu() + "' " +
			",dt_Stamp = '" + ed.getDt_stamp() + "' " +
			",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " +
			",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
			",oid_usuario = " + ed.getUser() +
			",time_millis = " + ed.getTime_millis() +
			"WHERE " +
			"oid_Tipo_Pneu = " + ed.getOid_Tipo_Pneu();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Tipo_PneuED ed)");
		}
	}

	public void deleta(Tipo_PneuED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Tipos_Pneus " +
			"WHERE " +
			"oid_Tipo_Pneu = " + ed.getOid_Tipo_Pneu();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Tipo_PneuED ed)");
		}
	}

	public ArrayList lista(Tipo_PneuED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
			", usuario_Stamp as usu_Stmp,  " +
	  		"dt_Stamp as dt_Stmp, " +
	  		"dm_Stamp as dm_Stmp " +
			"FROM " +
			"Tipos_Pneus " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() ;
			if (ed.getOid_Tipo_Pneu()>0)
				sql += " and oid_Tipo_Pneu = " + ed.getOid_Tipo_Pneu() ;
			if (doValida(ed.getNm_Tipo_Pneu()))
				sql += " and Tipos_Pneus.nm_Tipo_Pneu like '" + ed.getNm_Tipo_Pneu() + "%' ";
			sql += "ORDER BY " +
			"Tipos_Pneus.nm_Tipo_Pneu";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public Tipo_PneuED getByRecord(Tipo_PneuED ed) throws Excecoes {

		Tipo_PneuED edQBR = new Tipo_PneuED();
		try {
			sql = "SELECT * " +
				  ",usuario_Stamp as usu_Stmp " +
				  ",dt_Stamp as dt_Stmp " +
				  ",dm_Stamp as dm_Stmp " +
				  "FROM Tipos_Pneus " +
				  "WHERE " ;
			if (ed.getOid_Tipo_Pneu()>0)
				sql+="oid_Tipo_Pneu = " + ed.getOid_Tipo_Pneu() ;
			else
			if (doValida(ed.getNm_Tipo_Pneu()))
				sql += "oid_Empresa = " + ed.getOid_Empresa() + " " +
				" and Tipos_Pneus.nm_Tipo_Pneu = '" + ed.getNm_Tipo_Pneu() + "'" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Tipo_PneuED ed)");
		}
		return edQBR;
	}

	private Tipo_PneuED populaRegistro(ResultSet res) throws SQLException {
		Tipo_PneuED ed = new Tipo_PneuED();
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setOid_Tipo_Pneu(res.getInt("oid_Tipo_Pneu"));
		ed.setNm_Tipo_Pneu(res.getString("Nm_Tipo_Pneu"));
	    ed.setUsuario_Stamp(res.getString("usu_Stmp"));
	    //Padrao
		if(!"31/12/1969 21:00:00".equals(FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))))
				&& JavaUtil.doValida(res.getString("usuario_Stamp"))){
			ed.setMsg_Stamp(("I".equals(res.getString("dm_Stamp"))? "Incluído":"Alterado") + " por " + res.getString("usuario_Stamp")+ " em " + FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))));
		}
	    return ed;
	}
}
