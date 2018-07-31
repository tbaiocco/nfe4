/*
 * Created on 11/11/2004
 *
 */
package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Fabricante_PneuED;
import com.master.ed.Marca_PneuED;
import com.master.ed.Marca_VeiculoED;
import com.master.ed.Modelo_PneuED;
import com.master.ed.Motivo_SucateamentoED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis Steigleder
 * @serial Fabricantes de pneus
 * @serialData 06/2007
 */
public class Fabricante_PneuBD extends BancoUtil {

	private ExecutaSQL executaSQL;

	String sql=null;

	public Fabricante_PneuBD (ExecutaSQL executaSQL) {
		super(executaSQL);
		this.executaSQL = executaSQL;
	}

	public Fabricante_PneuED inclui (Fabricante_PneuED ed) throws Excecoes {
		try {
			ed.setDt_stamp(Data.getDataDMY());
			ed.setOid_Fabricante_Pneu (getAutoIncremento ("oid_Fabricante_Pneu" , "Fabricantes_Pneus"));
			sql = "INSERT INTO fabricantes_pneus (" +
			"oid_Fabricante_Pneu " +
			",cd_Fabricante_Pneu " +
			",nm_Fabricante_Pneu " ;
			if (ed.getOid_Empresa()>0)
				sql+=",oid_Empresa ";
			sql+=  ",dm_Stamp" +
			  	   ",dt_Stamp" +
			  	   ",usuario_Stamp"+
			  ") " +
			"values (" +
			ed.getOid_Fabricante_Pneu () +
			", '" + (doValida(ed.getCD_Fabricante_Pneu()) ? ed.getCD_Fabricante_Pneu(): "" ) + "'" +
			", '" + ed.getNM_Fabricante_Pneu () + "'" ;
			if (ed.getOid_Empresa()>0)
				sql+=","+ ed.getOid_Empresa();
			sql+= ",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		")";
			executaSQL.executarUpdate (sql);
			return ed;
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "inclui (Fabricante_PneuED ed)");
		}
	}

	public Fabricante_PneuED incluiMarca(Marca_PneuED ed) throws Excecoes {
		try {
			Fabricante_PneuED ret = new Fabricante_PneuED();
			ed.setDt_stamp(Data.getDataDMY());
//			ed.setOid_Marca_Pneu(getAutoIncremento ("oid_Fabricante_Pneu" , "Fabricantes_Pneus"));
			sql = "INSERT INTO fabricantes_pneus (" +
			"oid_Fabricante_Pneu " +
			",cd_Fabricante_Pneu " +
			",nm_Fabricante_Pneu " ;
			if (ed.getOid_Empresa()>0)
				sql+=",oid_Empresa ";
			sql+=  ",dm_Stamp" +
			  	   ",dt_Stamp" +
			  	   ",usuario_Stamp"+
			  ") " +
			"values (" +
			ed.getOid_Marca_Pneu() +
			", '" + ed.getOid_Marca_Pneu() + "'" +
			", '" + ed.getNm_Marca_Pneu() + "'" ;
			if (ed.getOid_Empresa()>0)
				sql+=","+ ed.getOid_Empresa();
			sql+= ",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		")";
			executaSQL.executarUpdate (sql);
			return ret;
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "inclui (Fabricante_PneuED ed)");
		}
	}

	public void altera (Fabricante_PneuED ed) throws Excecoes {
		try {
			sql = "UPDATE Fabricantes_Pneus SET " +
			"cd_Fabricante_Pneu = " + ed.getCD_Fabricante_Pneu () +
			",nm_Fabricante_Pneu = '" + ed.getNM_Fabricante_Pneu () + "' " +
			",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " +
			"WHERE " +
			"oid_fabricante_pneu = " + ed.getOid_Fabricante_Pneu ();
			executaSQL.executarUpdate (sql);
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera (Fabricante_PneuED ed)");
		}
	}

	public void alteraMarca(Marca_PneuED ed) throws Excecoes {
		try {
			sql = "UPDATE Fabricantes_Pneus SET " +
			"cd_Fabricante_Pneu = " + ed.getOid_Marca_Pneu() +
			",nm_Fabricante_Pneu = '" + ed.getNm_Marca_Pneu() + "' " +
			",dm_Stamp = 'A'" +
  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' " +
			"WHERE " +
			"oid_fabricante_pneu = " + ed.getOid_Marca_Pneu();
			executaSQL.executarUpdate (sql);
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera (Fabricante_PneuED ed)");
		}
	}

	public void delete (Fabricante_PneuED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Fabricantes_Pneus " +
			"WHERE " +
			"oid_Fabricante_Pneu = " + ed.getOid_Fabricante_Pneu ();
			executaSQL.executarUpdate (sql);
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "delete (Fabricante_PneuED ed)");
		}
	}

	public void deleteMarca(Marca_PneuED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Fabricantes_Pneus " +
			"WHERE " +
			"oid_Fabricante_Pneu = " + ed.getOid_Marca_Pneu();
			executaSQL.executarUpdate (sql);
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "delete (Fabricante_PneuED ed)");
		}
	}

	public ArrayList lista (Fabricante_PneuED ed) throws Excecoes {
		ArrayList list = new ArrayList ();
		try {
			sql = "SELECT * " +
	  		",Fabricantes_Pneus.usuario_Stamp as usu_Stmp " +
	  		",Fabricantes_Pneus.dt_Stamp as dt_Stmp " +
	  		",Fabricantes_Pneus.dm_Stamp as dm_Stmp " +
			"FROM " +
			"Fabricantes_Pneus " +
			"WHERE 1=1 ";
			if (ed.getOid_Empresa() > 0)
				sql+= " and oid_Empresa = " + ed.getOid_Empresa ();
			if (ed.getOid_Fabricante_Pneu () > 0)
				sql += " and oid_Fabricante_Pneu = " + ed.getOid_Fabricante_Pneu ();
			if (doValida(ed.getCD_Fabricante_Pneu ()))
				sql += " and cd_Fabricante_Pneu = '" + ed.getCD_Fabricante_Pneu () + "'";
			if (doValida(ed.getNM_Fabricante_Pneu()))
				sql += " and nm_Fabricante_Pneu like '%" + ed.getNM_Fabricante_Pneu () + "%'";
			sql += " ORDER BY " +
			"nm_Fabricante_Pneu";
			ResultSet rs = executaSQL.executarConsulta (sql);
			while (rs.next ()) {
				list.add (populaRegistro(rs));
			}
		}
		catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista (Fabricante_PneuED ed)");
		}
		return list;
	}

	public ArrayList listaPneuPorMarca(Fabricante_PneuED ed,String tipo) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			long contador = 0;
			sql = "SELECT  count(oid_pneu) as qtd_pneus " ;
			sql+="FROM " +
			"pneus p " ;
			if ("true".equals(ed.getDm_Sucateados())){
				sql+="where dt_sucateamento is not null " ;
			}
			if ("true".equals(ed.getDm_Nao_Sucateados())){
				sql+="where dt_sucateamento is null " ;
			}
			ResultSet rs = this.executaSQL.executarConsulta(sql);
			if (rs.next()){
				contador = (rs.getLong("qtd_pneus"));
			}
			sql = "SELECT " ;
			sql+=" f.nm_Fabricante_Pneu, " +
			"count(p.oid_fabricante_Pneu) as qtd_marca "+
			"FROM " +
			"pneus p, " +
			"fabricantes_pneus f " +
			"WHERE " +
			"p.oid_empresa = " + ed.getOid_Empresa() + " and " +
			"f.oid_fabricante_pneu = p.oid_fabricante_pneu " ;
			if ("true".equals(ed.getDm_Sucateados())){
				sql+=" and p.dt_sucateamento is not null " ;
			}
			if ("true".equals(ed.getDm_Nao_Sucateados())){
				sql+=" and p.dt_sucateamento is null " ;
			}
			sql+="group by ";
			sql+="f.nm_fabricante_pneu ";
			sql+="order by f.nm_fabricante_pneu" ;
			ResultSet res = this.executaSQL.executarConsulta(sql);
			while (res.next()) {
				Fabricante_PneuED pneuED = new Fabricante_PneuED();
				pneuED.setVl_Fabricante_Pneu( ((res.getDouble("qtd_marca") * 100) / contador) );
				pneuED.setNM_Fabricante_Pneu(res.getString("nm_fabricante_pneu"));
				list.add(pneuED);
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(MotivoSucateamentoED ed)");
		}
	}

	public Fabricante_PneuED getByRecord(Fabricante_PneuED ed) throws Excecoes {

		Fabricante_PneuED edQBR = new Fabricante_PneuED();
		try {
			sql = "SELECT * " +
				  ",Fabricantes_Pneus.usuario_Stamp as usu_Stmp " +
			  	  ",Fabricantes_Pneus.dt_Stamp as dt_Stmp " +
			  	  ",Fabricantes_Pneus.dm_Stamp as dm_Stmp " +
				  "FROM Fabricantes_Pneus " +
				  "WHERE " ;
			if (ed.getOid_Fabricante_Pneu()>0)
				sql+="oid_Fabricante_Pneu = " + ed.getOid_Fabricante_Pneu() ;
			else
			if (doValida(ed.getNM_Fabricante_Pneu()))
				sql += "oid_Empresa = " + ed.getOid_Empresa() + " " +
				" and Fabricantes_Pneus.nm_Fabricante_Pneu = '" + ed.getNM_Fabricante_Pneu() + "'" ;
			ResultSet res = executaSQL.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Fabricante_PneuED ed)");
		}
		return edQBR;
	}

	public Fabricante_PneuED getByRecord (int oid) throws Excecoes {
		ArrayList lista = lista (new Fabricante_PneuED (oid , "" , ""));
		Iterator iterator = lista.iterator ();
		if (iterator.hasNext ()) {
			return (Fabricante_PneuED) iterator.next ();
		}
		else {
			return new Fabricante_PneuED ();
		}
	}

	public Fabricante_PneuED getByCodigo (String codigo) throws Excecoes {
		ArrayList lista = this.lista (new Fabricante_PneuED (0 , codigo , ""));
		Iterator iterator = lista.iterator ();
		if (iterator.hasNext ()) {
			return (Fabricante_PneuED) iterator.next ();
		}
		else {
			return new Fabricante_PneuED ();
		}
	}
	public Fabricante_PneuED populaRegistro(ResultSet res) throws SQLException {
		Fabricante_PneuED ed = new Fabricante_PneuED();
		ed.setOid_Fabricante_Pneu(res.getInt("oid_Fabricante_Pneu"));
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setCD_Fabricante_Pneu(res.getString("cd_Fabricante_Pneu"));
		ed.setNM_Fabricante_Pneu(res.getString("nm_Fabricante_Pneu"));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;
	}

}