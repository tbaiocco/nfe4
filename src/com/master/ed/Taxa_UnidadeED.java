package com.master.ed;

public class Taxa_UnidadeED extends MasterED{

	private long oid_Taxa_Unidade;
	private long oid_Unidade;
	private long oid_Tipo_Servico;
	private double pe_Issqn;
	private String cd_Unidade;
	private String nm_Fantasia;
  	private String cd_Tipo_Servico;
  	private String nm_Tipo_Servico;

	public String getCd_Tipo_Servico() {
		return cd_Tipo_Servico;
	}
	public void setCd_Tipo_Servico(String cd_Tipo_Servico) {
		this.cd_Tipo_Servico = cd_Tipo_Servico;
	}
	public String getCd_Unidade() {
		return cd_Unidade;
	}
	public void setCd_Unidade(String cd_Unidade) {
		this.cd_Unidade = cd_Unidade;
	}
	public String getNm_Fantasia() {
		return nm_Fantasia;
	}
	public void setNm_Fantasia(String nm_Fantasia) {
		this.nm_Fantasia = nm_Fantasia;
	}
	public String getNm_Tipo_Servico() {
		return nm_Tipo_Servico;
	}
	public void setNm_Tipo_Servico(String nm_Tipo_Servico) {
		this.nm_Tipo_Servico = nm_Tipo_Servico;
	}
	public long getOid_Taxa_Unidade() {
		return oid_Taxa_Unidade;
	}
	public void setOid_Taxa_Unidade(long oid_Taxa_Unidade) {
		this.oid_Taxa_Unidade = oid_Taxa_Unidade;
	}
	public long getOid_Tipo_Servico() {
		return oid_Tipo_Servico;
	}
	public void setOid_Tipo_Servico(long oid_Tipo_Servico) {
		this.oid_Tipo_Servico = oid_Tipo_Servico;
	}
	public long getOid_Unidade() {
		return oid_Unidade;
	}
	public void setOid_Unidade(long oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}
	public double getPe_Issqn() {
		return pe_Issqn;
	}
	public void setPe_Issqn(double pe_Issqn) {
		this.pe_Issqn = pe_Issqn;
	}

}