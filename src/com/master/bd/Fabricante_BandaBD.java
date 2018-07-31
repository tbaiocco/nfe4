/*
 * Created on 11/11/2004
 *
 */
package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.master.ed.Fabricante_BandaED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis Steigleder
 * @serial Fabricantes de
 * @serialData 06/2007
 */
public class Fabricante_BandaBD extends BancoUtil {

	private ExecutaSQL executaSQL;

	String sql=null;

	public Fabricante_BandaBD (ExecutaSQL executaSQL) {
		super(executaSQL);
		this.executaSQL = executaSQL;
	}

	public Fabricante_BandaED inclui (Fabricante_BandaED ed) throws Excecoes {
		try {
			ed.setOid_Fabricante_Banda (getAutoIncremento ("oid_Fabricante_Banda" , "Fabricantes_Bandas"));
			sql = "INSERT INTO Fabricantes_Bandas (" +
			"oid_Fabricante_Banda " +
			",oid_Empresa " +
			",cd_Fabricante_Banda " +
			",nm_Fabricante_Banda " +
			//			Padrao
		    ",dm_Stamp" +
	        ",dt_Stamp" +
		  	",usuario_Stamp"+
		  	",oid_usuario"+
		  	",time_millis" ;
			sql+=") values ( " +
			ed.getOid_Fabricante_Banda () +
			",  " + ed.getOid_Empresa () +
			", '" + (doValida(ed.getCd_Fabricante_Banda()) ? ed.getCd_Fabricante_Banda(): "" ) + "'" +
			", '" + ed.getNm_Fabricante_Banda () + "'" +
			//			Padrao
			",'" + ed.getDm_Stamp() + "'" +
			",'" + ed.getDt_stamp() + "'" +
			",'" + ed.getUsuario_Stamp() + "'" +
			"," + ed.getUser() +
			"," + ed.getTime_millis() ;
			sql+=")";
			executaSQL.executarUpdate (sql);
			return ed;
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "inclui (Fabricante_BandaED ed)");
		}
	}

	public void altera (Fabricante_BandaED ed) throws Excecoes {
		try {
			sql = "UPDATE Fabricantes_Bandas SET " +
			"cd_Fabricante_Banda = '" + (doValida(ed.getCd_Fabricante_Banda ()) ? ed.getCd_Fabricante_Banda () : "" ) + "'" +
			",nm_Fabricante_Banda = '" + ed.getNm_Fabricante_Banda () + "' " +
			//			Padrao
			",dt_Stamp = '" + ed.getDt_stamp() + "' " +
			",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " +
			",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
			",oid_usuario = " + ed.getUser() +
			",time_millis = " + ed.getTime_millis() +
			"WHERE " +
			"oid_Fabricante_Banda = " + ed.getOid_Fabricante_Banda ();
			executaSQL.executarUpdate (sql);
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera (Fabricante_BandaED ed)");
		}
	}

	public void delete (Fabricante_BandaED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Fabricantes_Bandas " +
			"WHERE " +
			"oid_Fabricante_Banda = " + ed.getOid_Fabricante_Banda ();
			executaSQL.executarUpdate (sql);
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "delete (Fabricante_BandaED ed)");
		}
	}

	public ArrayList lista (Fabricante_BandaED ed) throws Excecoes {
		ArrayList list = new ArrayList ();
		try {
			sql = "SELECT * " +
	  		",usuario_Stamp as usu_Stmp " +
	  		",dt_Stamp as dt_Stmp " +
	  		",dm_Stamp as dm_Stmp " +
			"FROM " +
			"Fabricantes_Bandas " +
			"WHERE ";
			if (ed.getOid_Fabricante_Banda () > 0)
				sql += " oid_Fabricante_Banda = " + ed.getOid_Fabricante_Banda ();
			else {
				if ("999999".equals(ed.getCd_Fabricante_Banda()) ) {
					sql+=" (oid_Empresa = " + ed.getOid_Empresa() + " or oid_Empresa = 0) " ;
				} else {
					sql+=" oid_Empresa = " + ed.getOid_Empresa() + " " ;
				}
				if (doValida(ed.getNm_Fabricante_Banda()))
					sql += " and nm_Fabricante_Banda like '%" + ed.getNm_Fabricante_Banda () + "%'";
			}
			sql += " ORDER BY " +
			"oid_Empresa"+
			",nm_Fabricante_Banda";
			ResultSet rs = executaSQL.executarConsulta (sql);
			while (rs.next ()) {
				list.add (populaRegistro(rs));
			}
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista (Fabricante_BandaED ed)");
		}
		return list;
	}

	public Fabricante_BandaED getByRecord(Fabricante_BandaED ed) throws Excecoes {

		Fabricante_BandaED edQBR = new Fabricante_BandaED();
		try {
			sql = "SELECT * " +
		  		  ",usuario_Stamp as usu_Stmp " +
		  		  ",dt_Stamp as dt_Stmp " +
		  		  ",dm_Stamp as dm_Stmp " +
				  "FROM Fabricantes_Bandas " +
				  "WHERE " ;
			if (ed.getOid_Fabricante_Banda()>0)
				sql+=" oid_Fabricante_Banda = " + ed.getOid_Fabricante_Banda() ;
			else {
				sql+=" oid_Empresa = " + ed.getOid_Empresa() + " " ;
				if (doValida(ed.getNm_Fabricante_Banda()))
					sql +=" and Fabricantes_Bandas.nm_Fabricante_Banda = '" + ed.getNm_Fabricante_Banda() + "'" ;
				if (doValida(ed.getCd_Fabricante_Banda()))
					sql +=" and Fabricantes_Bandas.cd_Fabricante_Banda = '" + ed.getCd_Fabricante_Banda() + "'" ;
			}
			ResultSet res = executaSQL.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Fabricante_BandaED ed)");
		}
		return edQBR;
	}


	public Fabricante_BandaED populaRegistro(ResultSet res) throws SQLException {
		Fabricante_BandaED ed = new Fabricante_BandaED();
		ed.setOid_Fabricante_Banda(res.getInt("oid_Fabricante_Banda"));
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setCd_Fabricante_Banda(res.getString("cd_Fabricante_Banda"));
		ed.setNm_Fabricante_Banda(res.getString("nm_Fabricante_Banda"));
	    ed.setUsuario_Stamp(res.getString("usu_Stmp"));
	    //	  Padrao
		if(!"31/12/1969 21:00:00".equals(FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))))
				&& JavaUtil.doValida(res.getString("usuario_Stamp"))){
			ed.setMsg_Stamp(("I".equals(res.getString("dm_Stamp"))? "Incluído":"Alterado") + " por " + res.getString("usuario_Stamp")+ " em " + FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))));
		}
	    return ed;
	}
}