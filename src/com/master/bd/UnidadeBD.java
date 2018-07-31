package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.EmpresaED;
import com.master.ed.UnidadeED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Régis Steigleder
 * @serial Unidades
 * @serialData 06/2007
 */
public class UnidadeBD extends BancoUtil {

	private ExecutaSQL executasql;

	public UnidadeBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public UnidadeED inclui(UnidadeED ed) throws Excecoes{
		String sql = null;
		try{
			// Acessar Empresas para pegar o cnpj-oid_pessoa para a unidade 
			EmpresaED empEd = new EmpresaED();
			empEd.setOid_Empresa(ed.getOid_Empresa());
			empEd = new EmpresaBD(this.executasql).getByRecord(empEd);
			ed.setOid_Pessoa(empEd.getOid_Pessoa());
			
			ed.setOid_Unidade(getAutoIncremento("oid_Unidade", "Unidades"));
			sql = " INSERT INTO " +
			" Unidades " +
			" ( " +
			" oid_Unidade " +
			",oid_pessoa " +
			",oid_moeda " +
			",nm_sigla " +
			",cd_unidade " +
			",cd_unidade_empresa " +
			",oid_empresa " +
			",oid_unidade_cobranca " +
			",cd_unidade_contabil " +
			",oid_subregiao " +
			",dm_situacao " +
			",dm_calcula_frete " +
			",dm_tipo_seguro " +
			",nr_poliza_seguro " +
			",dt_vcto_permiso " +
			",nr_permiso_complementar " +
			",nr_permiso_originario " +
			",oid_conta_corrente " +
			",oid_carteira " +
			",oid_unidade_fiscal " +
			",nr_regime_icms_especial " +
			",oid_emissor_padrao " +
			",oid_deposito " +
			",dm_tipo_documento_padrao " +
			" ) " +
			" VALUES " +
			" ( " +
			" " + ed.getOid_Unidade() +
			",'" + ed.getOid_Pessoa() + "' " +
			"," + ed.getOid_Moeda() +
			",'" + ed.getNm_Sigla() + "'" +
			",'" + ed.getCd_Unidade() + "'" +
			",'" + ed.getCd_Unidade_Empresa() + "'" +
			"," + ed.getOid_Empresa() +
			"," + ed.getOid_Unidade_Cobranca() + 
			",'" + ed.getCd_Unidade_Contabil() + "'" +
			"," + ed.getOid_Subregiao() +
			",'" + ed.getDm_Situacao() + "'" +
			",'" + ed.getDm_Calcula_Frete() + "'" +
			",'" + ed.getDm_Tipo_Seguro() + "'" +
			",'" + ed.getNr_Poliza_Seguro() + "'" +
			",'" + ed.getDt_Vcto() + "'" +
			"," + ed.getNr_Permiso_Complementar() +
			"," + ed.getNr_Permiso_Originario() +
			"," + ed.getOid_Conta_Corrente() +
			"," + ed.getOid_Carteira() +
			"," + ed.getOid_Unidade_Fiscal() +
			"," + ed.getNr_Regime_ICMS_Especial() +
			"," + ed.getOid_Emissor_Padrao() + 
			"," + ed.getOid_Deposito() +
			",'" + ed.getDm_Tipo_Documento_Padrao() + "'" +
			" ) ";    		  
			executasql.executarUpdate(sql);
			return ed;
		}
		catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui(UnidadeED ed)");
		}
	}

	public void altera(UnidadeED ed) throws Excecoes{
		String sql = null;
		try{
			sql = " UPDATE " +
			" Unidades " +
			" SET " +
			" nm_sigla = '" + ed.getNm_Sigla() + "' " +
			",oid_Empresa = " + ed.getOid_Empresa() + " " +
			",cd_Unidade = '" + ed.getCd_Unidade() + "' " +
			",cd_Unidade_Empresa = '" + ed.getCd_Unidade_Empresa() + "' " +
			",oid_Unidade_Cobranca = " + ed.getOid_Unidade_Cobranca() + " " +
			",oid_Pessoa = " + ed.getOid_Pessoa() + " " +
			",oid_Moeda = " + ed.getOid_Moeda() + " " +
			",cd_Unidade_Contabil = '" + ed.getCd_Unidade_Contabil() + "' " +
			",oid_Subregiao = " + ed.getOid_Subregiao() + " " +
			",dm_Situacao = '" + ed.getDm_Situacao() + "' " +
			",dm_Calcula_Frete = '" + ed.getDm_Calcula_Frete() + "' " +
			",dm_Tipo_Seguro = '" + ed.getDm_Tipo_Seguro() + "' " +
			",nr_Poliza_Seguro = " + ed.getNr_Poliza_Seguro() + " " +
			",dt_Seguro = '" + ed.getDt_Vcto() + "' " +
			",nr_Permiso_Complementar = " + ed.getNr_Permiso_Complementar() + " " + 
			",nr_Permiso_Originario = " + ed.getNr_Permiso_Originario() + " " +
			",oid_Conta_Corrente = " + ed.getOid_Conta_Corrente() + " " +
			",oid_Carteira = " + ed.getOid_Carteira() + " " +
			",oid_Unidade_Fiscal = " + ed.getOid_Unidade_Fiscal() + " " +
			",nr_Regime_ICMS_Especial = " + ed.getNr_Regime_ICMS_Especial() + " " +
			",oid_Emissor_Padrao = " + ed.getOid_Emissor_Padrao() + " " +
			",oid_Deposito = " + ed.getOid_Deposito() + " " +
			",dm_Tipo_Documento_Padrao = " + ed.getDm_Tipo_Documento_Padrao() + "' " +
			",dt_Stamp = '" + ed.getDt_stamp() + "' " +
			",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " +
			",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
			" WHERE " +
			" oid_Unidade = " + ed.getOid_Unidade();
			executasql.executarUpdate(sql);
		}
		catch(Exception exc){
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
					"altera(UnidadeED ed)");
		}
	}

	public void deleta(UnidadeED ed) throws Excecoes{
		String sql = null;
		try{
			sql = "DELETE " +
			"FROM " +
			"Unidades " +
			"WHERE " +
			"oid_Unidade = " + ed.getOid_Unidade();
			executasql.executarUpdate(sql);
		}
		catch(Exception exc){
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
					"deleta(UnidadeED ed)");
		}
	}

	public ArrayList lista(UnidadeED ed)throws Excecoes{
		String sql = null;
		ArrayList list = new ArrayList();
		try{
			sql = "SELECT * " +
			"FROM " +
			"Unidades, " +
			"Pessoas " +
			"WHERE " +
			"Unidades.oid_Pessoa = Pessoas.oid_Pessoa " +
			"and Unidades.oid_Unidade > 0 " ;
			if (ed.getOid_Empresa() > 0)
				sql += " and oid_Empresa = " + ed.getOid_Empresa();
			if (ed.getOid_Unidade() > 0 )
				sql += " and oid_Unidade = " + ed.getOid_Unidade();
			if (doValida(ed.getNm_Fantasia()) ) 
				sql += " and nm_Fantasia LIKE '" + ed.getNm_Fantasia() + "%'";
			if (doValida(ed.getNm_Unidade()) ) 
				sql += " and nm_Unidade LIKE '" + ed.getNm_Unidade() + "%'";
			sql+=" ORDER BY " +
			" Pessoas.nm_Fantasia";
			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			while (res.next())
			{
				UnidadeED edVolta = new UnidadeED();
				edVolta = populaRegistro(res, edVolta);
				list.add(edVolta);
			}
		}
		catch(Exception exc){
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
					"lista(UnidadeED ed)");
		}
		return list;
	}	

	public UnidadeED getByRecord(UnidadeED ed)throws Excecoes{
		String sql = null;
		UnidadeED edVolta = new UnidadeED();
		try{
			sql = "SELECT " +
			"* " +
			"FROM " +
			"Unidades, " +
			"Pessoas " +
			"WHERE " +
			"Unidades.oid_Pessoa = Pessoas.oid_Pessoa " ;
			if (ed.getOid_Empresa() > 0)
				sql += " and oid_Empresa = " + ed.getOid_Empresa();
			if (ed.getOid_Unidade() > 0 )
				sql += " and oid_Unidade = " + ed.getOid_Unidade();

			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			while (res.next()){
				edVolta = new UnidadeED();
				edVolta = populaRegistro(res, edVolta);
			}
		}
		catch(Exception exc){
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
					"getByRecord(UnidadeED ed)");
		}
		return edVolta;
	}
  
	private UnidadeED  populaRegistro(ResultSet res, UnidadeED edVolta) throws SQLException {
		edVolta.setOid_Unidade(res.getInt("oid_Unidade"));
		edVolta.setOid_Empresa(res.getInt("oid_Empresa"));
		edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
		edVolta.setNm_Sigla(res.getString("nm_Sigla"));
		edVolta.setNm_Fantasia(res.getString("nm_Fantasia"));
		edVolta.setCd_Unidade(res.getString("cd_Unidade"));
		edVolta.setCd_Unidade_Empresa(res.getString("cd_Unidade_Empresa"));
		edVolta.setOid_Unidade_Cobranca(res.getInt("oid_Unidade_Cobranca"));
		edVolta.setOid_Unidade_Fiscal(res.getInt("oid_Unidade_Fiscal"));
		edVolta.setOid_Moeda(res.getInt("oid_Moeda") );
		edVolta.setCd_Unidade_Contabil(res.getString("cd_Unidade_Contabil") );
		edVolta.setOid_Subregiao(res.getInt("oid_Subregiao") );
		edVolta.setDm_Situacao(res.getString("dm_Situacao") );
		edVolta.setDm_Calcula_Frete(res.getString("dm_Calcula_Frete") );
		edVolta.setDm_Tipo_Seguro(res.getString("dm_Tipo_Seguro") );
		edVolta.setNr_Poliza_Seguro(res.getString("nr_Poliza_Seguro") );
		edVolta.setDt_Vcto(FormataData.formataDataBT(res.getString("dt_Vcto_Permiso")));
		edVolta.setNr_Permiso_Complementar(res.getString("nr_Permiso_Complementar")); 
		edVolta.setNr_Permiso_Originario(res.getString("nr_Permiso_Originario"));
		edVolta.setOid_Conta_Corrente(res.getString("oid_Conta_Corrente"));
		edVolta.setOid_Carteira(res.getInt("oid_Carteira"));
		edVolta.setOid_Unidade_Fiscal(res.getInt("oid_Unidade_Fiscal"));
		edVolta.setNr_Regime_ICMS_Especial(res.getString("nr_Regime_ICMS_Especial"));
		edVolta.setOid_Emissor_Padrao(res.getString("oid_Emissor_Padrao"));
		edVolta.setOid_Deposito(res.getInt("oid_Deposito"));
		edVolta.setDm_Tipo_Documento_Padrao(res.getString("dm_Tipo_Documento_Padrao"));
		edVolta.setDt_stamp(FormataData.formataDataBT(res.getString("dt_Stamp")));
		edVolta.setUsuario_Stamp(res.getString("usuario_Stamp"));
		edVolta.setDm_Stamp(res.getString("dm_Stamp"));
		return edVolta;
	}
	
	// STGP - Itens incluidos para dar suporte ao STGP

	
	public UnidadeED incluiStgp(UnidadeED ed) throws Excecoes{
		String sql = null;
		try{
			ed.setOid_Unidade(getAutoIncremento("oid_Unidade", "Unidades"));
			sql = " INSERT INTO " +
			" Unidades " +
			" ( " +
			" oid_Unidade " +
			",nm_unidade " +
			",oid_empresa " +
			",dm_Stamp" +
		  	",dt_Stamp" +
		  	",usuario_Stamp"+
			" ) " +
			" VALUES " +
			" ( " +
			" " + ed.getOid_Unidade() +
			",'" + ed.getNm_Unidade() + "' " +
			"," + ed.getOid_Empresa() +
			",'I'" +
	  		",'" + ed.getDt_stamp() + "'" +
	  		",'" + ed.getUsuario_Stamp() + "'" +
			" ) ";    		  
			executasql.executarUpdate(sql);
			return ed;
		}
		catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui(UnidadeED ed)");
		}
	}

	public void alteraStgp(UnidadeED ed) throws Excecoes{
		String sql = null;
		try{
			sql = " UPDATE " +
			" Unidades " +
			" SET " +
			" nm_Unidade = '" + ed.getNm_Unidade() + "' " +
			",dt_Stamp = '" + ed.getDt_stamp() + "' " +
			",usuario_Stamp = '" + ed.getUsuario_Stamp() + "' " +
			",dm_Stamp = '" + ed.getDm_Stamp() + "' " +
			" WHERE " +
			" oid_Unidade = " + ed.getOid_Unidade();
			executasql.executarUpdate(sql);
		}
		catch(Exception exc){
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
					"altera(UnidadeED ed)");
		}
	}


	public ArrayList listaStgp(UnidadeED ed)throws Excecoes{
		String sql = null;
		ArrayList list = new ArrayList();
		try{
			sql = "SELECT * " +
	  		",usuario_Stamp as usu_Stmp " +
	  		",dt_Stamp as dt_Stmp " +
	  		",dm_Stamp as dm_Stmp " +
			"FROM " +
			"Unidades " +
			"WHERE " +
			"Unidades.oid_Unidade > 0 " ;
			if (ed.getOid_Empresa() > 0)
				sql += " and oid_Empresa = " + ed.getOid_Empresa();
			if (ed.getOid_Unidade() > 0 )
				sql += " and oid_Unidade = " + ed.getOid_Unidade();
			sql+=" ORDER BY " +
			" nm_unidade ";
			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			while (res.next())
			{
				UnidadeED edVolta = new UnidadeED();
				edVolta = populaRegistroStgp(res, edVolta);
				list.add(edVolta);
			}
		}
		catch(Exception exc){
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
					"lista(UnidadeED ed)");
		}
		return list;
	}	

	public UnidadeED getByRecordStgp(UnidadeED ed)throws Excecoes{
		String sql = null;
		UnidadeED edVolta = new UnidadeED();
		try{
			sql = "SELECT " +
			"* " +
			"FROM " +
			"Unidades " +
			"WHERE " +
			"Unidades.oid_Unidade > 0 " ;
			if (ed.getOid_Empresa() > 0)
				sql += " and oid_Empresa = " + ed.getOid_Empresa();
			if (ed.getOid_Unidade() > 0 )
				sql += " and oid_Unidade = " + ed.getOid_Unidade();
			if (doValida(ed.getNm_Unidade()) )
				sql += " and nm_Unidade = '" + ed.getNm_Unidade() + "' ";

			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			while (res.next()){
				edVolta = new UnidadeED();
				edVolta = populaRegistroStgp(res, edVolta);
			}
		}
		catch(Exception exc){
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
					"getByRecordStgp(UnidadeED ed)");
		}
		return edVolta;
	}

	private UnidadeED  populaRegistroStgp(ResultSet res, UnidadeED edVolta) throws SQLException {
		edVolta.setOid_Unidade(res.getInt("oid_Unidade"));
		edVolta.setNm_Unidade(res.getString("nm_Unidade"));
		edVolta.setOid_Empresa(res.getInt("oid_Empresa"));
		edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
		edVolta.setMsg_Stamp(("I".equals(res.getString("dm_Stamp"))? "Incluído":"Alterado") + " por " + res.getString("usuario_Stamp")+ " em " + FormataData.formataDataBT(res.getString("dt_Stamp")));
		return edVolta;
	}
	
}
