package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Dimensao_PneuED;
import com.master.ed.Fabricante_PneuED;
import com.master.ed.Marca_VeiculoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Dimensões de pneus
 * @serialData 06/2007
 */
public class Dimensao_PneuBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Dimensao_PneuBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Dimensao_PneuED inclui(Dimensao_PneuED ed) throws Excecoes {
		try {
			ed.setOid_Dimensao_Pneu(getAutoIncremento("oid_Dimensao_Pneu", "Dimensoes_Pneus"));
			sql = "INSERT INTO Dimensoes_Pneus (" +
			"oid_Empresa, " +
			"oid_Dimensao_Pneu," +
			"nm_Dimensao_Pneu, " +
			"dm_Stamp, " +
		  	"dt_Stamp, " +
		  	"usuario_Stamp "+
			") " +
			" VALUES (" +
			ed.getOid_Empresa() + 
			"," + ed.getOid_Dimensao_Pneu() + 
			",'" + ed.getNm_Dimensao_Pneu() + 
			"','I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Dimensao_PneuED ed)");
		}
	}

	public void altera(Dimensao_PneuED ed) throws Excecoes {
		try {
			sql = "UPDATE Dimensoes_Pneus SET " +
			"nm_Dimensao_Pneu = '" + ed.getNm_Dimensao_Pneu() + "' " +
			",dt_Stamp = '" + ed.getDt_stamp() + "' " +
			",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " + 
			",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() + " and " +
			"oid_Dimensao_Pneu = " + ed.getOid_Dimensao_Pneu();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Dimensao_PneuED ed)");
		}
	}

	public void deleta(Dimensao_PneuED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Dimensoes_Pneus " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() + " and " +
			"oid_Dimensao_Pneu = " + ed.getOid_Dimensao_Pneu();
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Dimensao_PneuED ed)");
		}
	}

	public ArrayList lista(Dimensao_PneuED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
			",usuario_Stamp as usu_Stmp " +
	  		",dt_Stamp as dt_Stmp " +
	  		",dm_Stamp as dm_Stmp " +
			"FROM " +
			"Dimensoes_Pneus " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() ; 
			if (doValida(ed.getNm_Dimensao_Pneu()))
				sql += " and Dimensoes_Pneus.nm_Dimensao_Pneu like '" + ed.getNm_Dimensao_Pneu() + "%' ";
			sql += "ORDER BY " +
			"Dimensoes_Pneus.nm_Dimensao_Pneu";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}
	
	public ArrayList listaPneuPorDimensao(Dimensao_PneuED ed,String tipo) throws Excecoes {
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
			ResultSet rs = this.executasql.executarConsulta(sql);
			if (rs.next()){
				contador = (rs.getLong("qtd_pneus"));
			}
			sql = "SELECT " ;
			sql+=" d.nm_dimensao_Pneu, " +
			"count(p.oid_dimensao_Pneu) as qtd_dimensao "+ 
			"FROM " +
			"pneus p, " +
			"dimensoes_pneus d " +
			"WHERE " +
			"p.oid_empresa = " + ed.getOid_Empresa() + " and " +
			"d.oid_dimensao_pneu = p.oid_dimensao_pneu " ;
			if ("true".equals(ed.getDm_Sucateados())){			
				sql+=" and p.dt_sucateamento is not null " ;
			}
			if ("true".equals(ed.getDm_Nao_Sucateados())){			
				sql+=" and p.dt_sucateamento is null " ;
			}
			sql+="group by ";
			sql+="d.nm_dimensao_pneu ";
			sql+="order by d.nm_dimensao_pneu" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				Dimensao_PneuED pneuED = new Dimensao_PneuED();
				pneuED.setVl_Dimensao_Pneu( ((res.getDouble("qtd_dimensao") * 100) / contador) );
				pneuED.setNm_Dimensao_Pneu(res.getString("nm_dimensao_pneu"));
				list.add(pneuED);
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(MotivoSucateamentoED ed)");
		}
	}

	public Dimensao_PneuED getByRecord(Dimensao_PneuED ed) throws Excecoes {

		Dimensao_PneuED edQBR = new Dimensao_PneuED();
		try {
			sql = "SELECT * " +
				  ",usuario_Stamp as usu_Stmp " +
		  		  ",dt_Stamp as dt_Stmp " +
		  		  ",dm_Stamp as dm_Stmp " +
				  "FROM Dimensoes_Pneus " +
				  "WHERE " ;
			if (ed.getOid_Dimensao_Pneu()>0)
				sql+="oid_Dimensao_Pneu = " + ed.getOid_Dimensao_Pneu() ;
			else
			if (doValida(ed.getNm_Dimensao_Pneu()))
				sql += "oid_Empresa = " + ed.getOid_Empresa() + " " + 
				" and Dimensoes_Pneus.nm_Dimensao_Pneu = '" + ed.getNm_Dimensao_Pneu() + "'" ;
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Dimensao_PneuED ed)");
		}
		return edQBR;
	}
	
	private Dimensao_PneuED populaRegistro(ResultSet res) throws SQLException {
		Dimensao_PneuED ed = new Dimensao_PneuED();
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setOid_Dimensao_Pneu(res.getInt("oid_Dimensao_Pneu"));
		ed.setNm_Dimensao_Pneu(res.getString("Nm_Dimensao_Pneu"));
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;
	}
}
