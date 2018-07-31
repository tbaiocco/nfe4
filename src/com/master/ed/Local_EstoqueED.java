package com.master.ed;

/**
 * @author Regis
 *
 */
public class Local_EstoqueED extends RelatorioBaseED {

	private static final long serialVersionUID = -808741860542308265L;

	public Local_EstoqueED() {
	}

	private long oid_Local_Estoque;
	private String cd_Unidade;
	private String nm_Unidade;
	private String nm_Local_Estoque;
	private long oid_Empresa;
	private long oid_Unidade;

	public String getNm_Local_Estoque() {
		return nm_Local_Estoque;
	}
	public void setNm_Local_Estoque(String nm_Local_Estoque) {
		this.nm_Local_Estoque = nm_Local_Estoque;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public long getOid_Local_Estoque() {
		return oid_Local_Estoque;
	}
	public void setOid_Local_Estoque(long oid_Local_Estoque) {
		this.oid_Local_Estoque = oid_Local_Estoque;
	}
	public long getOid_Unidade() {
		return oid_Unidade;
	}
	public void setOid_Unidade(long oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getNm_Unidade() {
		return nm_Unidade;
	}
	public void setNm_Unidade(String nm_Unidade) {
		this.nm_Unidade = nm_Unidade;
	}
	public String getCd_Unidade() {
		return cd_Unidade;
	}
	public void setCd_Unidade(String cd_Unidade) {
		this.cd_Unidade = cd_Unidade;
	}


}
