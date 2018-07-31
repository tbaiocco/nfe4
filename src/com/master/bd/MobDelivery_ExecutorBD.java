package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.MobDelivery_ExecutorED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Executor de tarefas no MobDelivery
 * @serialData 10/2007
 */
public class MobDelivery_ExecutorBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public MobDelivery_ExecutorBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public MobDelivery_ExecutorED inclui(MobDelivery_ExecutorED ed) throws Excecoes {
		try {
			ed.setOid_Executor(getAutoIncremento("oid_Executor", "MobDelivery_Executores"));
			sql = "INSERT INTO MobDelivery_Executores (" +
			"oid_Executor," +
			"oid_Unidade,"+
			"nm_Executor,"+
			"nr_Celular,"+
			"tx_Senha, "+
			"dm_Stamp," +
	  	    "dt_Stamp," +
	  	    "usuario_Stamp"+
		    ") " +
			" VALUES (" +
			ed.getOid_Executor()+
			", "+ed.getOid_Unidade()+
			",'"+ed.getNm_Executor()+"' "+
			",'"+ed.getNr_Celular()+"' "+
			",'"+ed.getTx_Senha()+"' "+ 
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(MobDelivery_ExecutorED ed)");
		}
	}

	public void altera(MobDelivery_ExecutorED ed) throws Excecoes {
		try {
			sql = "UPDATE MobDelivery_Executores SET " +
			"oid_Executor="+ed.getOid_Executor()+
			",oid_Unidade="+ed.getOid_Unidade()+
			",nm_Executor='"+ed.getNm_Executor()+"' "+
			",nr_Celular='"+ed.getNr_Celular()+"' "+
			",tx_Senha='"+ed.getTx_Senha()+"' "+
			",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " +
			"WHERE " +
			"oid_Executor = " + ed.getOid_Executor();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(MobDelivery_ExecutorED ed)");
		}
	}

	public void registraInicioDia(MobDelivery_ExecutorED ed) throws Excecoes {
		try {
			sql = "UPDATE MobDelivery_Executores SET " +
  	   		"dt_Dia = '" + ed.getDt_Dia() + "' " +
			"WHERE " +
			"oid_Executor = " + ed.getOid_Executor();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(MobDelivery_ExecutorED ed)");
		}
	}

	public void registraFechamentoDia(MobDelivery_ExecutorED ed) throws Excecoes {
		try {
			sql = "UPDATE MobDelivery_Executores SET " +
  	   		"dt_Dia = null " +
			"WHERE " +
			"oid_Executor = " + ed.getOid_Executor();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(MobDelivery_ExecutorED ed)");
		}
	}

	public void registraDmReenvio(MobDelivery_ExecutorED ed) throws Excecoes {
		try {
			sql = "UPDATE MobDelivery_Executores SET " +
  	   		"dm_Reenviar = '" + ed.getDm_Reenviar() + "' " +
			"WHERE " +
			"oid_Executor = " + ed.getOid_Executor();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(MobDelivery_ExecutorED ed)");
		}
	}

	public void delete(MobDelivery_ExecutorED ed) throws Excecoes {
		try {
			sql = "DELETE FROM MobDelivery_Executores " +
			"WHERE " +
			"oid_Executor = " + ed.getOid_Executor();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"delete(MobDelivery_ExecutorED ed)");
		}
	}
	
	public ArrayList lista(MobDelivery_ExecutorED ed) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * " +
	  		",e.usuario_Stamp as usu_Stmp " +
	  		",e.dt_Stamp as dt_Stmp " +
	  		",e.dm_Stamp as dm_Stmp " +
			"FROM " +
			"MobDelivery_Executores as e, " +
			"MobDelivery_Unidades as u " +
			"WHERE " +
			"u.oid_Unidade = e.oid_Unidade " ;  
			
			if (ed.getOid_Executor()>0)
				sql+="AND e.oid_Executor="+ed.getOid_Executor()+ " ";
			else 
				if (ed.getOid_Unidade()>0)
					sql+="AND e.oid_Unidade="+ed.getOid_Unidade()+ " ";
				if (doValida(ed.getNm_Executor()))
					sql+="AND e.nm_Executor='"+ed.getNm_Executor()+ "' ";
			
			sql += "ORDER BY " +
			" e.nm_Executor";
			
			ResultSet res = this.executasql.executarConsulta(sql);
			
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(MobDelivery_ExecutorED ed)");
		}
	}

	
	public MobDelivery_ExecutorED getByRecord (MobDelivery_ExecutorED ed) throws Excecoes {
		ArrayList lista = this.lista (ed);
		Iterator iterator = lista.iterator ();
		if (iterator.hasNext ()) {
			return (MobDelivery_ExecutorED) iterator.next ();
		}
		else {
			return new MobDelivery_ExecutorED();
		}
	}	
	
	private MobDelivery_ExecutorED populaRegistro(ResultSet res) throws SQLException {
		MobDelivery_ExecutorED ed = new MobDelivery_ExecutorED();
		ed.setOid_Executor(res.getInt("oid_Executor"));
		ed.setOid_Unidade(res.getInt("oid_Unidade"));
		ed.setNm_Executor(res.getString("nm_Executor"));
		ed.setNr_Celular(res.getString("nr_Celular")); 
		ed.setTx_Senha(res.getString("tx_Senha"));
		ed.setNm_Unidade(res.getString("nm_Unidade"));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;	
	}
}