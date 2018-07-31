package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.MobDelivery_UnidadeED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Unidade de Negócio no MobDelivery
 * @serialData 19/05/2008
 */
public class MobDelivery_UnidadeBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public MobDelivery_UnidadeBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public MobDelivery_UnidadeED inclui(MobDelivery_UnidadeED ed) throws Excecoes {
		try {
			ed.setOid_Unidade(getAutoIncremento("oid_Unidade", "MobDelivery_Unidades"));
			sql = "INSERT INTO MobDelivery_Unidades (" +
			"oid_Unidade," +
			"nr_Unidade,"+
			"nm_unidade,"+
			"dm_Stamp," +
	  	    "dt_Stamp," +
	  	    "usuario_Stamp"+
		    ") " +
			" VALUES (" +
			ed.getOid_Unidade()+
			",'"+ed.getNr_Unidade()+"' "+
			",'"+ed.getNm_Unidade()+"' "+
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(MobDelivery_UnidadeED ed)");
		}
	}

	public void altera(MobDelivery_UnidadeED ed) throws Excecoes {
		try {
			sql = "UPDATE MobDelivery_Unidades SET " +
			"oid_Unidade="+ed.getOid_Unidade()+
			",nr_Unidade='"+ed.getNr_Unidade()+"' "+
			",nm_Unidade='"+ed.getNm_Unidade()+"' "+
			",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " +
			"WHERE " +
			"oid_Unidade = " + ed.getOid_Unidade();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(MobDelivery_UnidadeED ed)");
		}
	}

	public void delete(MobDelivery_UnidadeED ed) throws Excecoes {
		try {
			sql = "DELETE FROM MobDelivery_Unidades " +
			"WHERE " +
			"oid_Unidade = " + ed.getOid_Unidade();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"delete(MobDelivery_UnidadeED ed)");
		}
	}
	
	public ArrayList lista(MobDelivery_UnidadeED ed) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * " +
	  		",u.usuario_Stamp as usu_Stmp " +
	  		",u.dt_Stamp as dt_Stmp " +
	  		",u.dm_Stamp as dm_Stmp " +
			"FROM " +
			"MobDelivery_Unidades as u " +
			"WHERE 1=1 " ;
			
			if (ed.getOid_Unidade()>0)
				sql+="AND u.oid_Unidade="+ed.getOid_Unidade()+ " ";
			else {
				if (doValida(ed.getNr_Unidade()))
					sql+="AND u.nr_Unidade='"+ed.getNr_Unidade()+ "' ";
				if (doValida(ed.getNm_Unidade()))
					sql+="AND u.nm_Unidade='"+ed.getNm_Unidade()+ "' ";				
			}
			
			sql += "ORDER BY " +
			" u.nm_Unidade";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(MobDelivery_UnidadeED ed)");
		}
	}

	
	public MobDelivery_UnidadeED getByRecord (MobDelivery_UnidadeED ed) throws Excecoes {
		ArrayList lista = this.lista (ed);
		Iterator iterator = lista.iterator ();
		if (iterator.hasNext ()) {
			return (MobDelivery_UnidadeED) iterator.next ();
		}
		else {
			return new MobDelivery_UnidadeED();
		}
	}	
	
	private MobDelivery_UnidadeED populaRegistro(ResultSet res) throws SQLException {
		MobDelivery_UnidadeED ed = new MobDelivery_UnidadeED();
		ed.setOid_Unidade(res.getInt("oid_Unidade"));
		ed.setNr_Unidade(res.getString("nr_Unidade"));
		ed.setNm_Unidade(res.getString("nm_Unidade"));
		ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;	
	}
}