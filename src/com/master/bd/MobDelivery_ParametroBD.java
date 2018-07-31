package com.master.bd;

import java.sql.ResultSet;

import com.master.ed.MobDelivery_ParametroED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Parametrização do MobDelivery
 * @serialData 05/2008
 */
public class MobDelivery_ParametroBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public MobDelivery_ParametroBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public void altera(MobDelivery_ParametroED ed) throws Excecoes {
		try {
			MobDelivery_ParametroED parEd = getByRecord(ed);
			if ( !doValida(parEd.getNm_Parametro()) ) {
				sql = "INSERT INTO MobDelivery_Parametros (" +
				"nm_Parametro," +
			    ") " +
				" VALUES (" +
				"param" +
		  		")";
				executasql.executarUpdate(sql);
			}
			sql = "UPDATE MobDelivery_Parametros SET " +
			"nr_frequencia_refresh_tela="+ed.getNr_Frequencia_Refresh_Tela()+
			",nr_frequencia_refresh_cel="+ed.getNr_Frequencia_Refresh_Cel();
			if  (doValida(ed.getDt_Aberto()))
				sql+=",dt_Aberto='"+ed.getDt_Aberto()+"'";
			sql+=",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " ;
			executasql.executarUpdate(sql);
			
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(MobDelivery_ParametroED ed)");
		}
	}

	public MobDelivery_ParametroED getByRecord(MobDelivery_ParametroED ed) throws Excecoes {
		try {
			sql = "SELECT * " +
	  		",p.usuario_Stamp as usu_Stmp " +
	  		",p.dt_Stamp as dt_Stmp " +
	  		",p.dm_Stamp as dm_Stmp " +
			"FROM " +
			"MobDelivery_Parametros as p " ;  
			ResultSet res = this.executasql.executarConsulta(sql);
			
			MobDelivery_ParametroED edVolta = new MobDelivery_ParametroED();
			while (res.next()) {
				edVolta.setNm_Parametro(res.getString("nm_Parametro"));
				edVolta.setNr_Frequencia_Refresh_Tela(res.getInt("nr_Frequencia_Refresh_Tela"));
				edVolta.setNr_Frequencia_Refresh_Cel(res.getInt("nr_Frequencia_Refresh_Cel"));
				edVolta.setDt_Aberto(FormataData.formataDataBT(res.getString("dt_Aberto"))); 
				edVolta.setUsuario_Stamp(res.getString("usu_Stmp"));
				edVolta.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
			}
			return edVolta;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(MobDelivery_ParametroED ed)");
		}
	}

}