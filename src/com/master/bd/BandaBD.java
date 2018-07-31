package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.BandaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Dimensões de pneus
 * @serialData 09/2007
 */
public class BandaBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public BandaBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public BandaED inclui(BandaED ed) throws Excecoes {
		try {
			ed.setOid_Banda(getAutoIncremento("oid_Banda", "Bandas"));
			sql = "INSERT INTO Bandas (" +
			"oid_Empresa," +
			"oid_Banda," +
			"cd_banda, " +
			"nm_Banda, " +
			"oid_Fabricante_Banda, "+
			"nr_Largura, " +
			"nr_Profundidade " +
			",dm_Stamp" +
            ",dt_Stamp" +
	  	    ",usuario_Stamp"+
			") " +
			" VALUES (" +
			ed.getOid_Empresa() +
			"," + ed.getOid_Banda() +
			",'" + ed.getCd_Banda() + "' " +
			",'" + ed.getNm_Banda() + "' " +
			", " + ed.getOid_Fabricante_Banda() +
			", " + ed.getNr_Largura() +
			", " + ed.getNr_Profundidade() +
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(BandaED ed)");
		}
	}

	public void altera(BandaED ed) throws Excecoes {
		try {
			sql = "UPDATE Bandas SET " +
			"nm_Banda = '" + ed.getNm_Banda() + "' " +
			",cd_Banda = '" + ed.getCd_Banda() + "' " +
			",oid_Fabricante_Banda = " + ed.getOid_Fabricante_Banda() +
			",nr_Largura = " + ed.getNr_Largura() +
			",nr_Profundidade = " + ed.getNr_Profundidade() +
			",dt_Stamp = '" + ed.getDt_stamp() + "' " +
			",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " +
			",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
			"WHERE " +
			"oid_Banda = " + ed.getOid_Banda();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(BandaED ed)");
		}
	}

	public void delete(BandaED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Bandas " +
			"WHERE " +
			"oid_Banda = " + ed.getOid_Banda();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"delete(BandaED ed)");
		}
	}

	public ArrayList lista(BandaED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
	  		",Bandas.usuario_Stamp as usu_Stmp " +
	  		",Bandas.dt_Stamp as dt_Stmp " +
	  		",Bandas.dm_Stamp as dm_Stmp " +
			"FROM Bandas,Fabricantes_Bandas " +
			"WHERE " +
			"Bandas.oid_Fabricante_Banda = Fabricantes_Bandas.oid_Fabricante_Banda " ;
			if (ed.getNr_Profundidade()>999999) {
				if (ed.getNr_Profundidade()==9999998)
					sql += " and Bandas.Oid_Empresa = 0 "; // pega só original
				else
					sql += " and ( Bandas.Oid_Empresa = " + ed.getOid_Empresa() + " or Bandas.Oid_Empresa = 0 ) "; //pega tudo da empresa + original
			} else {
				sql += "and Bandas.Oid_Empresa = " + ed.getOid_Empresa() + " ";
			}
			if (doValida(ed.getNm_Banda()))
				sql += " and Bandas.nm_Banda like '" + ed.getNm_Banda() + "%' ";
			if (ed.getOid_Fabricante_Banda()>0)
				sql += " and Bandas.oid_Fabricante_Banda =" + ed.getOid_Fabricante_Banda() + " ";

			sql += "ORDER BY " +
			"Bandas.oid_Empresa" +
			",Bandas.nm_Banda";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista(BandaED ed)");
		}
	}

	public BandaED getByRecord(BandaED ed) throws Excecoes {

		BandaED edQBR = new BandaED();
		try {
			sql = "SELECT * " +
	  		",Bandas.usuario_Stamp as usu_Stmp " +
	  		",Bandas.dt_Stamp as dt_Stmp " +
	  		",Bandas.dm_Stamp as dm_Stmp " +
			"FROM Bandas,Fabricantes_Bandas " +
			"WHERE " +
			"Bandas.oid_Fabricante_Banda = Fabricantes_Bandas.oid_Fabricante_Banda " +
			"and oid_Banda = " + ed.getOid_Banda() ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(BandaED ed)");
		}
		return edQBR;
	}

	private BandaED populaRegistro(ResultSet res) throws SQLException {
		BandaED ed = new BandaED();
		ed.setOid_Banda(res.getInt("oid_Banda"));
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setNm_Banda(res.getString("nm_Banda"));
		ed.setCd_Banda(res.getString("cd_Banda"));
		ed.setOid_Fabricante_Banda(res.getLong("oid_Fabricante_Banda"));
		ed.setCd_Fabricante_Banda(res.getString("cd_Fabricante_Banda"));
		ed.setNm_Fabricante_Banda(res.getString("nm_Fabricante_Banda"));
		ed.setNr_Largura(res.getDouble("nr_Largura"));
		ed.setNr_Profundidade(res.getDouble("nr_Profundidade"));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;
	}
}
