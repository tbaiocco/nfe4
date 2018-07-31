package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.master.ed.Cotacao_PneuED;
import com.master.ed.Motivo_SucateamentoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Ralph
 * @serial Motivos de Sucateamento
 * @serialData 06/2006
 */
public class Motivo_SucateamentoBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Motivo_SucateamentoBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Motivo_SucateamentoED inclui(Motivo_SucateamentoED ed) throws Excecoes {

		try {
			ed.setOid_Motivo_Sucateamento(getAutoIncremento("oid_Motivo_Sucateamento", "Motivos_Sucateamentos"));
			sql = "INSERT INTO Motivos_Sucateamentos (" +
				  "oid_Empresa, " +
			      "oid_Motivo_Sucateamento," +
			      "nm_Motivo_Sucateamento " +
			      ",dm_Stamp" +
			  	  ",dt_Stamp" +
			  	  ",usuario_Stamp"+
			  	  ",oid_usuario"+
			  	  ",time_millis"+
			      ") " +
			      " VALUES (" +
			      ed.getOid_Empresa() +
			      "," + ed.getOid_Motivo_Sucateamento() +
			      ",'" + ed.getNm_Motivo_Sucateamento() +
			      "','I'" +
			  	  ",'" + ed.getDt_stamp() + "'" +
			  	  ",'" + ed.getUsuario_Stamp() + "'" +
			  	  "," + ed.getUser() +
			  	  "," + ed.getTime_millis() +
				  ")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Motivo_SucateamentoED ed)");
		}
	}

	public void altera(Motivo_SucateamentoED ed) throws Excecoes {

		try {
			sql = "UPDATE Motivos_Sucateamentos SET " +
				  "nm_Motivo_Sucateamento = '" + ed.getNm_Motivo_Sucateamento() + "' " +
				  ",dt_Stamp = '" + ed.getDt_stamp() + "' " +
				  ",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " +
				  ",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
				  ",oid_usuario = " + ed.getUser() +
				  ",time_millis = " + ed.getTime_millis() +
				  "WHERE " +
				  "oid_Empresa = " + ed.getOid_Empresa() + " and " +
				  "oid_Motivo_Sucateamento = " + ed.getOid_Motivo_Sucateamento();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Motivo_SucateamentoED ed)");
		}
	}

	public void deleta(Motivo_SucateamentoED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Motivos_Sucateamentos " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() + " and " +
			"oid_Motivo_Sucateamento = " + ed.getOid_Motivo_Sucateamento();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Motivo_SucateamentoED ed)");
		}
	}

	public ArrayList lista(Motivo_SucateamentoED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
				  ",usuario_Stamp as usu_Stmp " +
				  ",dt_Stamp as dt_Stmp " +
			  	  ",dm_Stamp as dm_Stmp " +
				  "FROM "+
				  "Motivos_Sucateamentos " +
				  "WHERE " +
				  "oid_Empresa = " + ed.getOid_Empresa() ;
			if (ed.getOid_Motivo_Sucateamento()>0) {
				sql+=" and oid_motivo_sucateamento = " + ed.getOid_Motivo_Sucateamento() + " ";
			}
			if (doValida(ed.getNm_Motivo_Sucateamento()))
				sql += " and Motivos_Sucateamentos.nm_Motivo_Sucateamento like '" + ed.getNm_Motivo_Sucateamento() + "%' ";
			sql += "ORDER BY Motivos_Sucateamentos.nm_Motivo_Sucateamento";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public ArrayList listaMotivoSucateamento(Motivo_SucateamentoED ed,String tipo) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			long contador = 0;
			sql = "SELECT  count(oid_pneu) as qtd_sucateamento " ;
			sql+="FROM " +
			"pneus p " +
			"where dt_sucateamento is not null " ;
			if (ed.getOid_Fabricante_Pneu()>0 ) {
	    		sql+=" and p.oid_Fabricante_Pneu = " + ed.getOid_Fabricante_Pneu() + " ";
	    	}
			ResultSet rs = this.executasql.executarConsulta(sql);
			if (rs.next()){
				contador = (rs.getLong("qtd_sucateamento"));
			}
			sql = "SELECT " ;
			sql+=" m.nm_motivo_sucateamento, " +
			"count(p.oid_Motivo_Sucateamento) as qtd_motivo "+
			"FROM " +
			"pneus p, " +
			"motivos_Sucateamentos m, " +
			"fabricantes_pneus f " +
			"WHERE " +
			"p.oid_empresa = " + ed.getOid_Empresa() + " and " +
			"p.dt_sucateamento is not null and " +
			"p.oid_motivo_sucateamento = m.oid_motivo_sucateamento and " +
			"f.oid_fabricante_pneu = p.oid_fabricante_pneu " ;
	    	if (ed.getOid_Fabricante_Pneu()>0 ) {
	    		sql+="and p.oid_Fabricante_Pneu = " + ed.getOid_Fabricante_Pneu() + " ";
	    	}
			sql+="group by ";
			sql+="m.nm_motivo_Sucateamento ";
			sql+="order by m.nm_motivo_sucateamento" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				Motivo_SucateamentoED pneuED = new Motivo_SucateamentoED();
				pneuED.setVl_Motivo_Sucateamento( ((res.getDouble("qtd_motivo") * 100) / contador) );
				pneuED.setNm_Motivo_Sucateamento(res.getString("nm_motivo_sucateamento"));
				list.add(pneuED);
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(MotivoSucateamentoED ed)");
		}
	}


	public Motivo_SucateamentoED getByRecord(Motivo_SucateamentoED ed) throws Excecoes {

		Motivo_SucateamentoED edQBR = new Motivo_SucateamentoED();
		try {
			sql = "SELECT * " +
		  		  ",usuario_Stamp as usu_Stmp " +
		  	  	  ",dt_Stamp as dt_Stmp " +
		  		  ",dm_Stamp as dm_Stmp " +
				  "FROM " +
				  "Motivos_Sucateamentos " +
				  "WHERE " ;
			if (ed.getOid_Motivo_Sucateamento()>0) {
				sql+=" oid_motivo_sucateamento = " + ed.getOid_Motivo_Sucateamento() + " ";
			} else
			if (doValida(ed.getNm_Motivo_Sucateamento()))
				sql+="oid_Empresa = " + ed.getOid_Empresa() + " " +
					 "and Motivos_Sucateamentos.nm_Motivo_Sucateamento = '" + ed.getNm_Motivo_Sucateamento() + "'" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Motivo_SucateamentoED ed)");
		}
		return edQBR;
	}

	private Motivo_SucateamentoED populaRegistro(ResultSet res) throws SQLException {
		Motivo_SucateamentoED ed = new Motivo_SucateamentoED();
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setOid_Motivo_Sucateamento(res.getInt("oid_Motivo_Sucateamento"));
		ed.setNm_Motivo_Sucateamento(res.getString("Nm_Motivo_Sucateamento"));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		//Padrao
		if(!"31/12/1969 21:00:00".equals(FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))))
				&& JavaUtil.doValida(res.getString("usuario_Stamp"))){
			ed.setMsg_Stamp(("I".equals(res.getString("dm_Stamp"))? "Incluído":"Alterado") + " por " + res.getString("usuario_Stamp")+ " em " + FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))));
		}
		return ed;
	}
}
