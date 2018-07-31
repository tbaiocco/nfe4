package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.MobDelivery_SituacaoED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Situacao de tarefas no MobDelivery
 * @serialData 10/2007
 */
public class MobDelivery_SituacaoBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public MobDelivery_SituacaoBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public MobDelivery_SituacaoED inclui(MobDelivery_SituacaoED ed) throws Excecoes {
		try {
			ed.setOid_Situacao(getAutoIncremento("oid_Situacao", "MobDelivery_Situacoes"));
			sql = "INSERT INTO MobDelivery_Situacoes (" +
			"oid_Situacao," +
			"cd_Situacao,"+
			"nm_Situacao,"+
			"nr_Cor,"+
			"dm_Exige_Recebedor,"+
			"cd_Externo,"+
			"dm_Stamp," +
	  	    "dt_Stamp," +
	  	    "usuario_Stamp"+
		    ") " +
			" VALUES (" +
			ed.getOid_Situacao()+
			",'"+ed.getCd_Situacao()+"' "+
			",'"+ed.getNm_Situacao()+"' "+
			", "+ed.getNr_Cor()+
			",'"+ed.getDm_Exige_Recebedor()+"' "+
			",'"+ed.getCd_Externo()+"' "+
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		")";
			
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(MobDelivery_SituacaoED ed)");
		}
	}

	public void altera(MobDelivery_SituacaoED ed) throws Excecoes {
		try {
			sql = "UPDATE MobDelivery_Situacoes SET " +
			"oid_Situacao="+ed.getOid_Situacao()+
			",nm_Situacao='"+ed.getNm_Situacao()+"' "+
			",cd_Situacao='"+ed.getCd_Situacao()+"' "+
			",nr_Cor="+ed.getNr_Cor()+
			",dm_Exige_Recebedor='"+ed.getDm_Exige_Recebedor()+"' "+
			",cd_Externo='"+ed.getCd_Externo()+"' "+
			",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " +
			"WHERE " +
			"oid_Situacao = " + ed.getOid_Situacao();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(MobDelivery_SituacaoED ed)");
		}
	}

	public void delete(MobDelivery_SituacaoED ed) throws Excecoes {
		try {
			sql = "DELETE FROM MobDelivery_Situacoes " +
			"WHERE " +
			"oid_Situacao = " + ed.getOid_Situacao();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"delete(MobDelivery_SituacaoED ed)");
		}
	}
	
	public ArrayList lista(MobDelivery_SituacaoED ed) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * " +
	  		",s.usuario_Stamp as usu_Stmp " +
	  		",s.dt_Stamp as dt_Stmp " +
	  		",s.dm_Stamp as dm_Stmp " +
			"FROM " +
			"MobDelivery_Situacoes as s " +
			"WHERE s.oid_Situacao > 0  " ;
			
			if (ed.getOid_Situacao()>0)
				sql+="AND s.oid_Situacao="+ed.getOid_Situacao()+ " ";
			else 
				if (doValida(ed.getNm_Situacao()))
					sql+="AND s.nm_Situacao='"+ed.getNm_Situacao()+ "' ";
				if (doValida(ed.getCd_Situacao()))
					sql+="AND s.cd_Situacao='"+ed.getCd_Situacao()+ "' ";
			
			sql += "ORDER BY " +
			" s.cd_Situacao";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(MobDelivery_SituacaoED ed)");
		}
	}

	
	public MobDelivery_SituacaoED getByRecord (MobDelivery_SituacaoED ed) throws Excecoes {
		ArrayList lista = this.lista (ed);
		Iterator iterator = lista.iterator ();
		if (iterator.hasNext ()) {
			return (MobDelivery_SituacaoED) iterator.next ();
		}
		else {
			return new MobDelivery_SituacaoED();
		}
	}	
	
	private MobDelivery_SituacaoED populaRegistro(ResultSet res) throws SQLException {
		MobDelivery_SituacaoED ed = new MobDelivery_SituacaoED();
		ed.setOid_Situacao(res.getInt("oid_Situacao"));
		ed.setNm_Situacao(res.getString("nm_Situacao"));
		ed.setCd_Situacao(res.getString("cd_Situacao"));
		ed.setNr_Cor(res.getInt("nr_Cor")); 
		ed.setDm_Exige_Recebedor(res.getString("dm_Exige_Recebedor"));
		ed.setCd_Externo(res.getString("cd_Externo"));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;	
	}
}