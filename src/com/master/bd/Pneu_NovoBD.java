package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Pneu_NovoED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Pneus Novos
 * @since 10/2008
 */
public class Pneu_NovoBD extends BancoUtil {

	private ExecutaSQL executaSQL;
	String sql=null;

	public Pneu_NovoBD (ExecutaSQL executaSQL) {
		super(executaSQL);
		this.executaSQL = executaSQL;
	}

	public Pneu_NovoED inclui(Pneu_NovoED ed) throws Excecoes {
		try {
			ed.setOid_Pneu_Novo(getAutoIncremento ("oid_Pneu_Novo" , "PNEUS_NOVOS"));
			sql="INSERT INTO PNEUS_NOVOS (" +
				"oid_Pneu_Novo " +
				",oid_Fornecedor " +
				",oid_Fabricante_Pneu " +
				",oid_Dimensao_Pneu " +
				",oid_Modelo_Pneu " +
				",oid_Material " +
				",oid_Nota_Fiscal_Frota " +
				",oid_Empresa " +
				",vl_Unitario " +
				",nr_Quantidade " +
				",dm_Stamp" +
			  	",dt_Stamp" +
			  	",usuario_Stamp" ;
				sql+=") VALUES ( " + 
				ed.getOid_Pneu_Novo() +
				"," + getSQLString(ed.getOid_Fornecedor()) +
				"," + ed.getOid_Fabricante_Pneu() +
				"," + ed.getOid_Dimensao_Pneu() +
				"," + ed.getOid_Modelo_Pneu() +
				"," + ed.getOid_Material() +
				"," + ed.getOid_Nota_Fiscal_Frota() +
				"," + ed.getOid_Empresa() +
				"," + ed.getVl_Unitario() +
				"," + ed.getNr_Quantidade() +
				",'I'" +
		  		"," + getSQLString(ed.getDt_stamp()) +
		  		"," + getSQLString(ed.getUsuario_Stamp());
				sql+=")";
			executaSQL.executarUpdate(sql);
			return ed;
		} catch (SQLException e) {
			throw new Excecoes (e.getMessage() , e, this.getClass().getName() , "inclui(Pneu_NovoED ed)");
		}
	}

	public void altera (Pneu_NovoED ed) throws Excecoes {
		try {
			sql="UPDATE PNEUS_NOVOS SET " +
				"oid_Fornecedor =" +getSQLString(ed.getOid_Fornecedor())+
				",oid_Fabricante_Pneu = " + ed.getOid_Fabricante_Pneu()+
				",oid_Dimensao_Pneu = " + ed.getOid_Dimensao_Pneu()+
				",oid_Modelo_Pneu = " + ed.getOid_Modelo_Pneu()+
				",oid_Material = " + ed.getOid_Material()+
				",oid_Nota_Fiscal_Frota = " + ed.getOid_Nota_Fiscal_Frota()+
				",vl_Unitario = " + ed.getVl_Unitario()+
				",nr_Quantidade = " + ed.getNr_Quantidade() +
				",dm_Stamp = 'A'" +
	  	   		",dt_Stamp = " + getSQLDate(Data.getDataDMY()) +
	  	   		",usuario_Stamp = " + getSQLString(ed.getUsuario_Stamp())+
				" WHERE oid_Pneu_Novo = " + ed.getOid_Pneu_Novo();
			executaSQL.executarUpdate(sql);
		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "altera(Pneu_NovoED ed)");
		}
	}

	public void delete(Pneu_NovoED ed) throws Excecoes {
		try {
			sql=" DELETE FROM PNEUS_NOVOS " +
				" WHERE oid_Pneu_Novo = " + ed.getOid_Pneu_Novo();
			executaSQL.executarUpdate (sql);
		} catch (SQLException e) {
			throw new Excecoes (e.getMessage() , e, this.getClass().getName(), "delete(Pneu_NovoED ed)");
		}
	}

	public ArrayList lista(Pneu_NovoED ed) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			sql="SELECT p.* " +
				"	,f.nm_Razao_Social" +
		  		"	,p.usuario_Stamp as usu_Stmp " +
		  		"	,p.dt_Stamp as dt_Stmp " +
		  		"	,p.dm_Stamp as dm_Stmp " +
				"FROM PNEUS_NOVOS as p " +
				"	LEFT JOIN FORNECEDORES as f " +
				  "		ON (p.oid_Fornecedor = f.oid_Fornecedor) " +
				"WHERE ";
			if (ed.getOid_Pneu_Novo () > 0) { 
				sql += " p.oid_Pneu_Novo = " + ed.getOid_Pneu_Novo();
			} else {
				sql+=" p.oid_Empresa = " + ed.getOid_Empresa();
				if (doValida(ed.getOid_Fornecedor())) {
					sql+=" AND p.oid_Fornecedor  = " + getSQLString(ed.getOid_Fornecedor());
				}
				if (ed.getOid_Fabricante_Pneu() > 0) {
					sql+=" AND p.oid_Fabricante_Pneu = " + ed.getOid_Fabricante_Pneu();
				}
				if (ed.getOid_Dimensao_Pneu() > 0) {
					sql+=" AND p.oid_Dimensao_Pneu = " + ed.getOid_Dimensao_Pneu();
				}
				if (ed.getOid_Modelo_Pneu() > 0) {
					sql+=" AND p.oid_Modelo_Pneu = " + ed.getOid_Modelo_Pneu();
				}
				if (ed.getOid_Nota_Fiscal_Frota() > 0) {
					sql+=" AND p.oid_Nota_Fiscal_Frota = " + ed.getOid_Nota_Fiscal_Frota();
				}
			}
			ResultSet rs = executaSQL.executarConsulta(sql);
			while (rs.next()) {
				list.add(populaRegistro(rs));
			}
			closeResultset(rs);
		} catch (SQLException e) {
			throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista (Pneu_NovoED ed)");
		}
		return list;
	}

	public Pneu_NovoED getByRecord(Pneu_NovoED ed) throws Excecoes {
		try {
			ArrayList lista = this.lista(ed);
			if(!lista.isEmpty()) {
				return (Pneu_NovoED) lista.get(0);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Pneu_NovoED ed)");
		}
		return new Pneu_NovoED();
	}

	public Pneu_NovoED populaRegistro(ResultSet res) throws SQLException {
		Pneu_NovoED ed = new Pneu_NovoED();
		ed.setOid_Pneu_Novo(res.getLong("oid_Pneu_Novo"));
		ed.setOid_Fornecedor(res.getString("oid_Fornecedor"));
		ed.setOid_Fabricante_Pneu(res.getLong("oid_Fabricante_Pneu"));

		ed.setOid_Dimensao_Pneu(res.getLong("oid_Dimensao_Pneu"));
		ed.setOid_Modelo_Pneu(res.getLong("oid_Modelo_Pneu"));
		ed.setOid_Material(res.getLong("oid_Material"));

		ed.setOid_Nota_Fiscal_Frota(res.getLong("oid_Nota_Fiscal_Frota"));
		ed.setOid_Empresa(res.getInt("oid_Empresa"));
		ed.setVl_Unitario(res.getDouble("vl_Unitario"));
		ed.setNr_Quantidade(res.getLong("nr_Quantidade"));

		ed.getFornecedor().setNm_Razao_Social(res.getString("nm_Razao_Social"));
		
	    ed.setUsuario_Stamp(res.getString("usu_Stmp"));
	    ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
	    return ed;
	}
}