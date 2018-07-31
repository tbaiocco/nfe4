package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Empresa_StgpED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Kayel
 * @serial Empresas
 * @serialData 12/2007
 */
public class Empresa_StgpBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public Empresa_StgpBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Empresa_StgpED inclui(Empresa_StgpED ed) throws Excecoes {
		try {
			
			ed.setOid_Empresa(getAutoIncremento("oid_Empresa", "empresas_stgp"));
		
			sql = "INSERT INTO empresas_stgp " +
			"(" +
			"oid_Empresa, " +
			"nm_Razao_Social," +
			"nr_cnpj," +
			"nm_Endereco," +
			"nm_Bairro," +
			"nm_Cidade," +
			"cd_Estado," +
			"nr_Cep," +
			"nm_Inscricao_Estadual," +
			"nr_Telefone," +
			"nr_Fax," +
			"nm_Email, " +
			"dm_Tipo_Frota" +
			") " +
			" VALUES " +
			"(" +
			ed.getOid_Empresa() +
			",'" + (doValida(ed.getNm_Razao_Social()) ? ed.getNm_Razao_Social() : "" )+"'" + 
			",'" + (doValida(ed.getNr_Cnpj()) ? ed.getNr_Cnpj() : "" )+"'" + 
			",'" + (doValida(ed.getNm_Endereco()) ? ed.getNm_Endereco() : "" )+"'" + 
			",'" + (doValida(ed.getNm_Bairro()) ? ed.getNm_Bairro() : "" )+"'" + 
			",'" + (doValida(ed.getNm_Cidade()) ? ed.getNm_Cidade() : "" )+"'" + 
			",'" + (doValida(ed.getCd_Estado()) ? ed.getCd_Estado() : "" )+"'" + 
			",'" + (doValida(ed.getNr_Cep()) ? ed.getNr_Cep() : "" )+"'" + 
			",'" + (doValida(ed.getNm_Inscricao_Estadual())? ed.getNm_Inscricao_Estadual() : "" )+"'" + 
			",'" + (doValida(ed.getNr_Telefone())? ed.getNr_Telefone() : "" )+"'"+
			",'" + (doValida(ed.getNr_Fax())? ed.getNr_Fax(): "" )+"'"+ 
			",'" + (doValida(ed.getNm_Email())? ed.getNm_Email() : "" )+"'"+
			",'" + (doValida(ed.getDm_Tipo_Frota())? ed.getDm_Tipo_Frota(): "" )+"'"+
			")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(Empresa_StgpED ed)");
		}
	}

	public void altera(Empresa_StgpED ed) throws Excecoes {
		try {
			sql = "UPDATE empresas_stgp SET " +
			"nr_Cnpj= '" + ed.getNr_Cnpj()+ "' " +
			",nm_Razao_Social = '" + ed.getNm_Razao_Social() + "' " +
			",nm_Endereco = '" + ed.getNm_Endereco()  + "' " +
			",nm_Bairro = '" + ed.getNm_Bairro()  + "' " +
			",nm_Cidade = '" + ed.getNm_Cidade() + "' " +
			",cd_Estado = '" + ed.getCd_Estado()  + "' " +
			",nr_Cep = '" + ed.getNr_Cep() + "' " +
			",nm_Inscricao_Estadual = '" + ed.getNm_Inscricao_Estadual() + "' " +
			",nr_Telefone = '" + ed.getNr_Telefone() + "' " +
			",nr_Fax = '" +  ed.getNr_Fax() + "' " +
			",nm_eMail = '" +  ed.getNm_Email() + "' " +
			",dt_Stamp = '" + ed.getDt_stamp() + "' " +
			",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " + 
			",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
			",dm_Tipo_Frota = '" + ed.getDm_Tipo_Frota() + "' " + 
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() ;
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(Empresa_StgpED ed)");
		}
	}

	public void deleta(Empresa_StgpED ed) throws Excecoes {
		try {
			sql = "DELETE FROM empresas_stgp " +
			"WHERE " +
			"oid_Empresa_Stgp = '" + ed.getOid_Empresa() + "'";
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(Empresa_StgpEDED ed)");
		}
	}

	public ArrayList lista(Empresa_StgpED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT * "       +
			"FROM " +
			"Empresas " +
			"WHERE " +
			"Empresas.oid_Empresa_Stgp = " + ed.getOid_Empresa() ;
			
			if (doValida(ed.getNm_Razao_Social()))
			sql += " and Empresas.nm_Razao_Social LIKE '" + ed.getNm_Razao_Social() + "%' ";
			sql += "ORDER BY " +
			"Empresas.nm_Razao_Social ";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

	public Empresa_StgpED getByRecord(Empresa_StgpED ed) throws Excecoes {

		Empresa_StgpED edQBR = new Empresa_StgpED();
		try {
			sql = "SELECT * " +
			"FROM " +
			"empresas_stgp " +
			"WHERE " ;
			if (ed.getOid_Empresa()>0) 
				sql+=" empresas_stgp.oid_empresa = " + ed.getOid_Empresa()  ;
			if (doValida(ed.getNr_Cnpj() ) ) 
				sql+=" empresas_stgp.nr_cnpj = '" + ed.getNr_Cnpj() + "' " ;
			
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edQBR = populaRegistro(res);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(Empresa_StgpED ed)");
		}
		return edQBR;
	}

	private Empresa_StgpED populaRegistro(ResultSet res) throws SQLException {
		Empresa_StgpED ed = new Empresa_StgpED();
		ed.setOid_Empresa(res.getLong("oid_Empresa"));
		ed.setNm_Razao_Social(res.getString("nm_Razao_Social"));
		ed.setNm_Endereco(res.getString("nm_Endereco"));
		ed.setNm_Bairro(res.getString("nm_Bairro"));
		ed.setNm_Cidade(res.getString("nm_Cidade"));
		ed.setCd_Estado(res.getString("cd_Estado"));
		ed.setNm_Inscricao_Estadual(res.getString("nm_Inscricao_Estadual"));
		ed.setNr_Cep(res.getString("nr_Cep"));
		ed.setNr_Telefone(res.getString("nr_Telefone"));
		ed.setNr_Fax(res.getString("nr_Fax"));
		ed.setNm_Email(res.getString("nm_Email"));
		ed.setNr_Cnpj(res.getString("nr_cnpj"));
		ed.setDm_Tipo_Frota(res.getString("dm_Tipo_Frota"));
		return ed;
	}
}
