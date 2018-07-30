package com.master.ed;

/**
 * @author Regis
 *
 */
public class EmpresaED extends MasterED {

	private static final long serialVersionUID = 5578523549524591041L;

	public EmpresaED() {
	}
	
	private long oid_Empresa;
	private String oid_Pessoa;
	private String nm_Empresa;
	private String nr_Cnpj_Cpf;
	private String dm_Situacao;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDm_Situacao() {
		return dm_Situacao;
	}

	public void setDm_Situacao(String dm_Situacao) {
		this.dm_Situacao = dm_Situacao;
	}

	public String getNm_Empresa() {
		return nm_Empresa;
	}

	public void setNm_Empresa(String nm_Empresa) {
		this.nm_Empresa = nm_Empresa;
	}

	public String getNr_Cnpj_Cpf() {
		return nr_Cnpj_Cpf;
	}

	public void setNr_Cnpj_Cpf(String nr_Cnpj_Cpf) {
		this.nr_Cnpj_Cpf = nr_Cnpj_Cpf;
	}

	public long getOid_Empresa() {
		return oid_Empresa;
	}

	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}

	public String getOid_Pessoa() {
		return oid_Pessoa;
	}

	public void setOid_Pessoa(String oid_Pessoa) {
		this.oid_Pessoa = oid_Pessoa;
	}

			
}
