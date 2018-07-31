package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.FornecedorED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis
 * @serial Fornecedores
 * @serialData 06/2007
 */
public class FornecedorBD extends BancoUtil {

	private ExecutaSQL executasql;

	String sql = null;

	public FornecedorBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public FornecedorED inclui(FornecedorED ed) throws Excecoes {
		try {

			ed.setOid_Fornecedor(String.valueOf(getAutoIncremento("oid_sequencial", "Fornecedores")));
			sql = "INSERT INTO Fornecedores " +
			"(" +
			"oid_sequencial," +
			"oid_Empresa, " +
			"oid_Fornecedor," +
			"oid_Pessoa," +
			"nm_Razao_Social," +
			"nm_Endereco," +
			"nm_Bairro," +
			"nm_Cidade," +
			"cd_Estado," +
			"nr_Cep," +
			"nm_Inscricao_Estadual," +
			"nr_Telefone," +
			"nr_Fax," +
			"nm_Email, " +
			"dm_frota_pneu, " +
			"dm_Stamp," +
		  	"dt_Stamp," +
		  	"usuario_Stamp"+
			") " +
			" VALUES " +
			"(" +
			Integer.parseInt(ed.getOid_Fornecedor()) +
			","  + ed.getOid_Empresa() +
			",'" + ed.getOid_Fornecedor() + "' " +
			",'" + ed.getOid_Pessoa() + "' " +
			",'" + ed.getNm_Razao_Social() + "' " +
			",'" + (doValida(ed.getNm_Endereco()) ? ed.getNm_Endereco() : "") + "' " +
			",'" + (doValida(ed.getNm_Bairro()) ? ed.getNm_Bairro() : "") + "' " +
			",'" + (doValida(ed.getNm_Cidade()) ? ed.getNm_Cidade() : "") + "' " +
			",'" + (doValida(ed.getCd_Estado()) ? ed.getCd_Estado() : "") + "' " +
			",'" + (doValida(ed.getNr_Cep()) ? ed.getNr_Cep() : "") + "' " +
			",'" + (doValida(ed.getNm_Inscricao_Estadual()) ? ed.getNm_Inscricao_Estadual(): "") + "' " +
			",'" + (doValida(ed.getNr_Telefone()) ? ed.getNr_Telefone(): "") + "' " +
			",'" + (doValida(ed.getNr_Fax())? ed.getNr_Fax(): "") + "' " +
			",'" + (doValida(ed.getNm_eMail())? ed.getNm_eMail(): "") + "' " +
			",'S'" +
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
	  		")";
			executasql.executarUpdate(sql);
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"inclui(FornecedorED ed)");
		}
	}

	public void altera(FornecedorED ed) throws Excecoes {
		try {
			sql = "UPDATE Fornecedores SET " ;
			sql+="nm_Razao_Social = '" + ed.getNm_Razao_Social() + "' " ;
			sql+=",nm_Endereco = '" + (doValida(ed.getNm_Endereco()) ? ed.getNm_Endereco() : "") + "' " ;
			sql+=",nm_Bairro = '" + (doValida(ed.getNm_Bairro()) ? ed.getNm_Bairro() : "") + "' " ;
			sql+=",nm_Cidade = '" + (doValida(ed.getNm_Cidade()) ? ed.getNm_Cidade() : "") + "' " ;
			sql+=",cd_Estado = '" + (doValida(ed.getCd_Estado()) ? ed.getCd_Estado() : "") + "' " ;
			sql+=",nr_Cep = '" + (doValida(ed.getNr_Cep()) ? ed.getNr_Cep() : "") + "' " ;
			sql+=",nm_Inscricao_Estadual = '" + (doValida(ed.getNm_Inscricao_Estadual()) ? ed.getNm_Inscricao_Estadual(): "") + "' " ;
			sql+=",nr_Telefone = '" + (doValida(ed.getNr_Telefone()) ? ed.getNr_Telefone(): "") + "' " ;
			sql+=",nr_Fax = '" + (doValida(ed.getNr_Fax()) ? ed.getNr_Fax(): "") + "' " ;
			sql+=",nm_eMail = '" + (doValida(ed.getNm_eMail())? ed.getNm_eMail(): "") + "' " ;
			sql+=",dt_Stamp = '" + ed.getDt_stamp() + "' " +
			",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " +
			",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
			"WHERE " +
			"oid_Empresa = " + ed.getOid_Empresa() + " and " +
			"oid_Fornecedor = '" + ed.getOid_Fornecedor() + "'";
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"altera(FornecedorED ed)");
		}
	}

	public void deleta(FornecedorED ed) throws Excecoes {
		try {
			sql = "DELETE FROM Fornecedores " +
			"WHERE " +
			"oid_Fornecedor = '" + ed.getOid_Fornecedor() + "'";
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"deleta(FornecedorED ed)");
		}
	}


	public ArrayList lista(FornecedorED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {

			sql = "SELECT " +
			//Pega direto da tabela de fornecedores
//			"f.oid_sequencial as foid_sequencial, " +
			"f.oid_empresa as foid_empresa," +
			"f.oid_fornecedor as foid_fornecedor," +
			"f.oid_pessoa as foid_pessoa," +
//			"f.nm_razao_social as fnm_razao_social," +
			"f.nm_endereco as fnm_endereco," +
			"f.nm_bairro as fnm_bairro," +
			"f.nm_cidade as fnm_cidade," +
			"f.nr_cep as fnr_cep," +
			"f.nm_inscricao_estadual as fnm_inscricao_estadual," +
			"f.nr_telefone as fnr_telefone," +
			"f.nm_email as fnm_email," +
			"f.cd_estado as fcd_estado," +
			"f.nr_fax as fnr_fax," +
			"f.dm_frota_pneu as fdm_frota_pneu," +
			"f.usuario_Stamp as usu_Stmp ," +
			"f.dt_Stamp as dt_Stmp ," +
			"f.dm_Stamp as dm_Stmp," +
			//Pega da tabela de pessoas e junta com cidades e estados
			"p.nm_razao_social as pnm_razao_social," +
			"p.nm_fantasia as pnm_fantasia," +
			"p.nm_endereco as pnm_endereco," +
			"p.nm_bairro as pnm_bairro," +
			"c.nm_cidade as pnm_cidade," +
			"p.nr_cep as pnr_cep," +
			"p.nm_inscricao_estadual as pnm_inscricao_estadual," +
			"p.nr_telefone as pnr_telefone," +
			"p.nr_fax as pnr_fax," +
			"p.email as pnm_email," +
			"e.cd_estado as pcd_estado " +
			"FROM " +
			"fornecedores as f " +
			"left join pessoas as p on f.oid_pessoa = p.oid_pessoa " +
			"left join cidades as c on p.oid_cidade = c.oid_cidade " +
			"left join regioes_estados as r on c.oid_regiao_estado = r.oid_regiao_estado " +
			"left join estados as e on r.oid_estado = e.oid_estado "       +
			"WHERE " +
			"f.oid_Empresa = " + ed.getOid_Empresa() ;

			if (ed.getOid_Pessoa() != null)
				if (doValida(ed.getOid_Pessoa()) || ed.getOid_Pessoa().trim().length()>0 )
					sql += " and f.oid_Pessoa LIKE '" + ed.getOid_Pessoa() + "%' ";
			if (doValida(ed.getNm_Razao_Social())) {
				sql += " and " +
						"p.nm_Razao_Social LIKE '" + ed.getNm_Razao_Social() + "%' ";
			}
			sql += "ORDER BY " +
//			"f.nm_Razao_Social," +
			"p.nm_Razao_Social ";
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				if ("S".equals(res.getString("fdm_Frota_Pneu")))
						list.add(populaRegistro(res));
			}
			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}




	public FornecedorED getByRecord(FornecedorED ed) throws Excecoes {

		FornecedorED edQBR = new FornecedorED();
		try {
			sql = "SELECT " +
			//Pega direto da tabela de fornecedores
			"f.oid_sequencial as foid_sequencial, " +
			"f.oid_empresa as foid_empresa," +
			"f.oid_fornecedor as foid_fornecedor," +
			"f.oid_pessoa as foid_pessoa," +
//			"f.nm_razao_social as fnm_razao_social," +
			"f.nm_endereco as fnm_endereco," +
			"f.nm_bairro as fnm_bairro," +
			"f.nm_cidade as fnm_cidade," +
			"f.nr_cep as fnr_cep," +
			"f.nm_inscricao_estadual as fnm_inscricao_estadual," +
			"f.nr_telefone as fnr_telefone," +
			"f.nm_email as fnm_email," +
			"f.cd_estado as fcd_estado," +
			"f.nr_fax as fnr_fax," +
			"f.dm_frota_pneu as fdm_frota_pneu," +
			"f.usuario_Stamp as usu_Stmp ," +
			"f.dt_Stamp as dt_Stmp ," +
			"f.dm_Stamp as dm_Stmp," +
			//Pega da tabela de pessoas e junta com cidades e estados
			"p.nm_razao_social as pnm_razao_social," +
			"p.nm_fantasia as pnm_fantasia," +
			"p.nm_endereco as pnm_endereco," +
			"p.nm_bairro as pnm_bairro," +
			"c.nm_cidade as pnm_cidade," +
			"p.nr_cep as pnr_cep," +
			"p.nm_inscricao_estadual as pnm_inscricao_estadual," +
			"p.nr_telefone as pnr_telefone," +
			"p.nr_fax as pnr_fax," +
			"p.email as pnm_email," +
			"e.cd_estado as pcd_estado " +
			"FROM " +
			"fornecedores as f " +
			"left join pessoas as p on f.oid_pessoa = p.oid_pessoa " +
			"left join cidades as c on p.oid_cidade = c.oid_cidade " +
			"left join regioes_estados as r on c.oid_regiao_estado = r.oid_regiao_estado " +
			"left join estados as e on r.oid_estado = e.oid_estado "       +
			"WHERE " ;

			if (doValida(ed.getOid_Fornecedor()))
				sql+="  f.oid_Fornecedor = '" + ed.getOid_Fornecedor() + "' " ;
			else
			if (doValida(ed.getOid_Pessoa())) {
				sql+=" f.oid_Empresa = " + ed.getOid_Empresa()  +
					 " and f.oid_Pessoa = '" + ed.getOid_Pessoa	() + "' " ;
			} else
				if (doValida(ed.getNr_Cnpj_Cic())) {
					sql+=" f.oid_Empresa = " + ed.getOid_Empresa()  +
						 " and p.nr_cnpj_cpf = '" + ed.getNr_Cnpj_Cic() + "' " ;
				}
//			System.out.println(sql);
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
//				if ("S".equals(res.getString("fdm_frota_pneu"))) {
					edQBR = populaRegistro(res);
//				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"getByRecord(FornecedorED ed)");
		}
		return edQBR;
	}

	private FornecedorED populaRegistro(ResultSet res) throws SQLException {
		FornecedorED ed = new FornecedorED();
		ed.setOid_Empresa(res.getInt("foid_Empresa"));
		ed.setOid_Fornecedor(res.getString("foid_Fornecedor"));
		ed.setOid_Pessoa(res.getString("foid_Pessoa"));
		ed.setNr_Cnpj_Cic(res.getString("foid_Pessoa"));
//		if (Utilitaria.doValida(res.getString("fnm_Razao_Social"))) {
//			ed.setNm_Razao_Social(res.getString("fnm_Razao_Social"));
//			ed.setNm_Endereco(res.getString("fnm_Endereco"));
//			ed.setNm_Bairro(res.getString("fnm_Bairro"));
//			ed.setNm_Cidade(res.getString("fnm_Cidade"));
//			ed.setCd_Estado(res.getString("fcd_Estado"));
//			ed.setNm_Inscricao_Estadual(res.getString("fnm_Inscricao_Estadual"));
//			ed.setNr_Cep(res.getString("fnr_Cep"));
//			ed.setNr_Telefone(res.getString("fnr_Telefone"));
//			ed.setNr_Fax(res.getString("fnr_Fax"));
//			ed.setNm_eMail(res.getString("fnm_eMail"));
//		} else {
			if (Utilitaria.doValida(res.getString("pnm_Fantasia"))) {
				ed.setNm_Razao_Social(res.getString("pnm_Fantasia"));
			} else {
				ed.setNm_Razao_Social(res.getString("pnm_Razao_Social"));
			}
			ed.setNm_Endereco(res.getString("pnm_Endereco"));
			ed.setNm_Bairro(res.getString("pnm_Bairro"));
			ed.setNm_Cidade(res.getString("pnm_Cidade"));
			ed.setCd_Estado(res.getString("pcd_Estado"));
			ed.setNm_Inscricao_Estadual(res.getString("pnm_Inscricao_Estadual"));
			ed.setNr_Cep(res.getString("pnr_Cep"));
			ed.setNr_Telefone(res.getString("pnr_Telefone"));
			ed.setNr_Fax(res.getString("pnr_Fax"));
			ed.setNm_eMail(res.getString("pnm_eMail"));
//		}
		ed.setUsuario_Stamp(res.getString("usu_Stmp"));
		ed.setMsg_Stamp(("I".equals(res.getString("dm_Stmp"))? "Incluído":"Alterado") + " por " + res.getString("usu_Stmp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stmp")));
		return ed;
	}
}
