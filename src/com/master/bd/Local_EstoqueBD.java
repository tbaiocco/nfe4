package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.master.ed.Local_EstoqueED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Dimensões de pneus
 * @serialData 06/2007
 */
public class Local_EstoqueBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Local_EstoqueBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Local_EstoqueED inclui(Local_EstoqueED ed) throws Excecoes {
		try {
			ed.setOid_Local_Estoque(getAutoIncremento("oid_Local_Estoque", "Locais_Estoques"));
			sql = "INSERT INTO Locais_Estoques (" +
			"oid_Empresa, " +
			"oid_Unidade, " +
			"oid_Local_Estoque," +
			"nm_Local_Estoque " +
		    ",dm_Stamp" +
	  	    ",dt_Stamp" +
	  	    ",usuario_Stamp"+
	  	    ",oid_usuario"+
	  	    ",time_millis"+
			") " +
			" VALUES (" +
			ed.getOid_Empresa() +
			"," + ed.getOid_Unidade() +
			"," + ed.getOid_Local_Estoque() +
			",'" + ed.getNm_Local_Estoque() +
			"','I'" +
	  	 	",'" + ed.getDt_stamp() + "'" +
	  	 	",'" + ed.getUsuario_Stamp() + "'" +
	  	 	"," + ed.getUser() +
	  	 	"," + ed.getTime_millis() +
			")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Local_EstoqueED ed)");
		}
	}

	public void altera(Local_EstoqueED ed) throws Excecoes {
		try {
			sql = "UPDATE Locais_Estoques SET " +
			"nm_Local_Estoque = '" + ed.getNm_Local_Estoque() + "' " +
			",oid_Unidade = " + ed.getOid_Unidade() + " " +
			",dt_Stamp = '" + ed.getDt_stamp() + "' " +
			",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " +
			",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
			",oid_usuario = " + ed.getUser() +
			",time_millis = " + ed.getTime_millis() +
			"WHERE " +
			"oid_Local_Estoque = " + ed.getOid_Local_Estoque();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Local_EstoqueED ed)");
		}
	}

	public void deleta(Local_EstoqueED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Locais_Estoques " +
			"WHERE " +
			"oid_Local_Estoque = " + ed.getOid_Local_Estoque();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Local_EstoqueED ed)");
		}
	}

	public ArrayList lista(Local_EstoqueED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT *, "       +
			"Locais_Estoques.usuario_Stamp as usu_Stmp " +
			",Locais_Estoques.dt_Stamp as dt_Stmp " +
			",Locais_Estoques.dm_Stamp as dm_Stmp " +
			"FROM " +
			"Locais_Estoques " +
			"left join Unidades on Locais_estoques.oid_Unidade = Unidades.oid_Unidade  " +
			"left join Pessoas on Unidades.oid_Pessoa = Pessoas.oid_Pessoa " +
			"WHERE " +
			"Locais_Estoques.oid_Empresa = " + ed.getOid_Empresa() ;
			if (ed.getOid_Local_Estoque()>0)
				sql+=" and oid_Local_Estoque = " + ed.getOid_Local_Estoque() ;
			if (ed.getOid_Unidade()>0)
				sql += " and Locais_Estoques.oid_Unidade = " + ed.getOid_Unidade() + " " ;
			if (doValida(ed.getNm_Local_Estoque()))
				sql += " and Locais_Estoques.nm_Local_Estoque like '" + ed.getNm_Local_Estoque() + "%' ";
			sql += " ORDER BY " +
			"Locais_Estoques.nm_Local_Estoque, " +
			"Pessoas.nm_Fantasia";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public Local_EstoqueED getByRecord(Local_EstoqueED ed) throws Excecoes {
		Local_EstoqueED edQBR = new Local_EstoqueED();
		try {
			sql = "SELECT * , " +
				  "Locais_Estoques.usuario_Stamp as usu_Stmp " +
				  ",Locais_Estoques.dt_Stamp as dt_Stmp " +
				  ",Locais_Estoques.dm_Stamp as dm_Stmp " +
				  "FROM " +
				  "Locais_Estoques " +
				  "left join Unidades on Locais_estoques.oid_Unidade = Unidades.oid_Unidade  " +
				  "left join Pessoas on Unidades.oid_Pessoa = Pessoas.oid_Pessoa " +
				  "WHERE " ;
			if (ed.getOid_Local_Estoque()>0)
				sql+=" oid_Local_Estoque = " + ed.getOid_Local_Estoque() ;
			else {
				sql += " Locais_Estoques.oid_Empresa = " + ed.getOid_Empresa() + " " ;
				if (doValida(ed.getNm_Local_Estoque()))
					sql += "and Locais_Estoques.nm_Local_Estoque = '" + ed.getNm_Local_Estoque() + "' " ;
				if (ed.getOid_Unidade() > 0)
					sql +="and Locais_estoques.oid_Unidade = " + ed.getOid_Unidade() + " ";
			}
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Local_EstoqueED ed)");
		}
		return edQBR;
	}

	private Local_EstoqueED populaRegistro(ResultSet res) throws SQLException {
		Local_EstoqueED ed = new Local_EstoqueED();
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setOid_Unidade(res.getInt("oid_Unidade"));
		ed.setOid_Local_Estoque(res.getInt("oid_Local_Estoque"));
		ed.setNm_Local_Estoque(res.getString("nm_Local_Estoque"));
		ed.setNm_Unidade(res.getString("nm_Unidade"));
		ed.setCd_Unidade(res.getString("cd_Unidade"));

		if (!Utilitaria.doValida(ed.getNm_Unidade()))
			ed.setNm_Unidade(res.getString("nm_Fantasia"));

		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		//Padrao
		if(!"31/12/1969 21:00:00".equals(FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))))
				&& JavaUtil.doValida(res.getString("usuario_Stamp"))){
			ed.setMsg_Stamp(("I".equals(res.getString("dm_Stamp"))? "Incluído":"Alterado") + " por " + res.getString("usuario_Stamp")+ " em " + FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))));
		}
		return ed;
	}
}
