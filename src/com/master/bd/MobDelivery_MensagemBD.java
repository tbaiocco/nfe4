package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.MobDelivery_MensagemED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Mensagens no MobDelivery
 * @serialData 19/05/2008
 */
public class MobDelivery_MensagemBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public MobDelivery_MensagemBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public MobDelivery_MensagemED inclui(MobDelivery_MensagemED ed) throws Excecoes {
		try {
			ed.setOid_Mensagem(getAutoIncremento("oid_Mensagem", "MobDelivery_Mensagens"));
			sql = "INSERT INTO MobDelivery_Mensagens (" +
			"oid_Mensagem," +
			"oid_Unidade,"+
			"nr_Mensagem,"+
			"tx_Mensagem, "+
			"dt_Mensagem, "+
			"oid_Executor, "+
			"dm_Stamp," +
	  	    "dt_Stamp," +
	  	    "usuario_Stamp"+
		    ") " +
			" VALUES (" +
			ed.getOid_Mensagem()+
			", "+ed.getOid_Unidade()+
			", "+ed.getNr_Mensagem()+
			",'"+ed.getTx_Mensagem()+"' "+  
			",'"+ed.getDt_Mensagem()+"' "+
			","+ed.getOid_Executor()+ 
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(MobDelivery_MensagemED ed)");
		}
	}

	public void altera(MobDelivery_MensagemED ed) throws Excecoes {
		try {
			sql = "UPDATE MobDelivery_Mensagens SET " +
			"oid_Mensagem="+ed.getOid_Mensagem()+
			",oid_Unidade="+ed.getOid_Unidade()+
			",nr_Mensagem="+ed.getNr_Mensagem()+
			",tx_Mensagem='"+ed.getTx_Mensagem()+"'"+
			",dt_Mensagem='"+ed.getDt_Mensagem()+"'"+
			",oid_Executor="+ed.getOid_Executor()+
			",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " +
			"WHERE " +
			"oid_Mensagem = " + ed.getOid_Mensagem();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(MobDelivery_MensagemED ed)");
		}
	}

	public void delete(MobDelivery_MensagemED ed) throws Excecoes {
		try {
			sql = "DELETE FROM MobDelivery_Mensagens " +
			"WHERE " +
			"oid_Mensasagem = " + ed.getOid_Mensagem();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"delete(MobDelivery_MensagemED ed)");
		}
	}
	
	public ArrayList lista(MobDelivery_MensagemED ed) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * " +
	  		",m.usuario_Stamp as usu_Stmp " +
	  		",m.dt_Stamp as dt_Stmp " +
	  		",m.dm_Stamp as dm_Stmp " +
			"FROM " +
			"MobDelivery_Mensagens as m " +
			"WHERE 1=1 " ;  
			
			if (ed.getOid_Mensagem()>0)
				sql+="AND m.oid_Mensagem="+ed.getOid_Mensagem()+ " ";
			if (ed.getOid_Executor()>0)
				sql+="AND m.oid_Executor="+ed.getOid_Executor()+ " ";
			if (doValida(ed.getDm_Protocolo()) ) {
				if ("C".equals(ed.getDm_Protocolo()))
					sql+=" AND m.dm_Protocolo is null ";
				else if (!"T".equals(ed.getDm_Protocolo()))
						sql+=" AND m.dm_Protocolo = '" + ed.getDm_Protocolo() +"' ";
				if(ed.getNr_Protocolo()>0)
					sql+=" AND m.nr_Protocolo = " + ed.getNr_Protocolo() +" ";
			}
			sql += "ORDER BY " +
			"m.oid_Unidade," +
			"m.nr_Mensagem";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(MobDelivery_MensagemED ed)");
		}
	}

	public void registraEnvio(MobDelivery_MensagemED ed) throws Excecoes {
		try {
			sql = "UPDATE  " +
			"MobDelivery_Mensagens " +
			"SET " +
			" dm_Protocolo = 'E' " +
			",nr_Protocolo = " + ed.getNr_Protocolo() +
			" WHERE " +
			" oid_Executor="+ed.getOid_Executor()+
			" AND dm_Protocolo is null ";
			this.executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"registraEnvio(MobDelivery_MensagemED ed)");
		}
	}

	public void registraRecebimento(MobDelivery_MensagemED ed) throws Excecoes {
		try {
			sql = "UPDATE  " +
			"MobDelivery_Mensagens " +
			"SET " +
			" dm_Protocolo = 'R' " +
			"WHERE " +
			" oid_Executor="+ed.getOid_Executor()+
			" and dm_Protocolo = 'E' " +
			" and nr_Protocolo = " + ed.getNr_Protocolo() ;
			this.executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"registraRecebimento(MobDelivery_MensagemED ed)");
		}
	}

	public void limpaProtocolo(MobDelivery_MensagemED ed) throws Excecoes {
		try {
			sql = "UPDATE  " +
			"MobDelivery_Mensagens " +
			"SET " +
			" dm_Protocolo = null " +
			",nr_Protocolo = null " +
			"WHERE " +
			" oid_Executor="+ed.getOid_Executor();
			this.executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"limpaProtocolo(MobDelivery_MensagemED ed)");
		}
	}


	public MobDelivery_MensagemED getByRecord (MobDelivery_MensagemED ed) throws Excecoes {
		ArrayList lista = this.lista (ed);
		Iterator iterator = lista.iterator ();
		if (iterator.hasNext ()) {
			return (MobDelivery_MensagemED) iterator.next ();
		}
		else {
			return new MobDelivery_MensagemED();
		}
	}	
	
	private MobDelivery_MensagemED populaRegistro(ResultSet res) throws SQLException {
		MobDelivery_MensagemED ed = new MobDelivery_MensagemED();
		ed.setOid_Mensagem(res.getInt("oid_Mensagem"));
		ed.setOid_Unidade(res.getInt("oid_Unidade"));
		ed.setNr_Mensagem(res.getInt("nr_Mensagem"));
		ed.setTx_Mensagem(res.getString("tx_Mensagem"));
		ed.setDt_Mensagem(res.getString("dt_Mensagem"));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;	
	}
}