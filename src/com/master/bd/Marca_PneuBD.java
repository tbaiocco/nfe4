package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.master.ed.Marca_PneuED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Marcas de pneus
 * @serialData 02/2006
 */
public class Marca_PneuBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Marca_PneuBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Marca_PneuED inclui(Marca_PneuED ed) throws Excecoes {

		try {
			ed.setOid_Marca_Pneu(getAutoIncremento("oid_Marca_Pneu", "Marcas_Pneus"));
			sql = "INSERT INTO Marcas_Pneus (" +
				  "oid_Empresa, " +
			      "oid_Marca_Pneu," +
			      "nm_Marca_Pneu, " +
			      //Padrao
			      "dm_Stamp" +
		          ",dt_Stamp" +
			  	  ",usuario_Stamp"+
			  	  ",oid_usuario"+
			  	  ",time_millis"+
			      ") " +
			      " VALUES (" +
			      ed.getOid_Empresa() +
			      "," + ed.getOid_Marca_Pneu() +
			      ",'" + ed.getNm_Marca_Pneu() + "'" +
			      //Padrao
			      ",'" + ed.getDm_Stamp() + "'" +
			  	  ",'" + ed.getDt_stamp() + "'" +
			  	  ",'" + ed.getUsuario_Stamp() + "'" +
			  	  "," + ed.getUser() +
			  	  "," + ed.getTime_millis() +
			      ")";
			System.out.println("MARCA - inclui():"+sql);
			executasql.executarUpdate(sql);
			new Fabricante_PneuBD(executasql).incluiMarca(ed);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Marca_PneuED ed)");
		}
	}

	public void altera(Marca_PneuED ed) throws Excecoes {

		try {
			sql = "UPDATE Marcas_Pneus SET " +
				  "nm_Marca_Pneu = '" + ed.getNm_Marca_Pneu() + "' " +
				  //Padrao
				  ",dt_Stamp = '" + ed.getDt_stamp() + "' " +
				  ",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " +
				  ",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
				  ",oid_usuario = " + ed.getUser() +
				  ",time_millis = " + ed.getTime_millis() +
				  " WHERE " +
				  "oid_Empresa = " + ed.getOid_Empresa() + " and " +
				  "oid_Marca_Pneu = " + ed.getOid_Marca_Pneu();
			System.out.println("MARCA - altera():"+sql);
			executasql.executarUpdate(sql);
			new Fabricante_PneuBD(executasql).alteraMarca(ed);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Marca_PneuED ed)");
		}
	}

	public void deleta(Marca_PneuED ed) throws Excecoes {

		try {
			sql = "DELETE FROM Marcas_Pneus " +
				  "WHERE " +
				  "oid_Empresa = " + ed.getOid_Empresa() + " and " +
				  "oid_Marca_Pneu = " + ed.getOid_Marca_Pneu();
			System.out.println("MARCA - deleta():"+sql);
			executasql.executarUpdate(sql);
			new Fabricante_PneuBD(executasql).deleteMarca(ed);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Marca_PneuED ed)");
		}
	}

	public ArrayList lista(Marca_PneuED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
				  "FROM Marcas_Pneus " +
				  "WHERE " +
				  "oid_Empresa = " + ed.getOid_Empresa() ;
			if (doValida(ed.getNm_Marca_Pneu()))
				sql += " and Marcas_Pneus.nm_Marca_Pneu like '" + ed.getNm_Marca_Pneu() + "%' ";
			if (doValida(String.valueOf(ed.getOid_Marca_Pneu())))
				sql += " and Marcas_Pneus.oid_Marca_Pneu = " + ed.getOid_Marca_Pneu();
			sql += " ORDER BY Marcas_Pneus.nm_Marca_Pneu";
			System.out.println("MARCA - lista():"+sql);
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public Marca_PneuED getByRecord(Marca_PneuED ed) throws Excecoes {

		Marca_PneuED edQBR = new Marca_PneuED();
		try {
			sql = "SELECT * " +
				  "FROM Marcas_Pneus " +
				  "WHERE " +
				  "oid_Empresa = " + ed.getOid_Empresa() ;
			if (doValida(ed.getNm_Marca_Pneu()))
				sql += " Marcas_Pneus.nm_Marca_Pneu = '" + ed.getNm_Marca_Pneu() + "'" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Marca_PneuED ed)");
		}
		return edQBR;
	}

	private Marca_PneuED populaRegistro(ResultSet res) throws SQLException {
		Marca_PneuED ed = new Marca_PneuED();
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setOid_Marca_Pneu(res.getInt("oid_Marca_Pneu"));
		ed.setNm_Marca_Pneu(res.getString("Nm_Marca_Pneu"));
		//Padrao
		if(!"31/12/1969 21:00:00".equals(FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))))
				&& JavaUtil.doValida(res.getString("usuario_Stamp"))){
			ed.setMsg_Stamp(("I".equals(res.getString("dm_Stamp"))? "Incluído":"Alterado") + " por " + res.getString("usuario_Stamp")+ " em " + FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))));
		}
		return ed;
	}
}
