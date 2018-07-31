package com.master.ed;

import java.io.Serializable;

/**
 * @author Cristian Vianna Garcia
 *
 */
public class Combo_SituacaoED extends MasterED implements Serializable {

	private static final long serialVersionUID = 8020316821180021992L;

	public Combo_SituacaoED() {
	}
	
	private long oid_Situacao;
	private String cd_Situacao;
	private String nm_Situacao;
	private String dt_stamp;
	private String usuario_stamp;
	private String dm_stamp;
	
	public String getDm_stamp() {
		return dm_stamp;
	}
	public void setDm_stamp(String dm_stamp) {
		this.dm_stamp = dm_stamp;
	}
	public String getDt_stamp() {
		return dt_stamp;
	}
	public void setDt_stamp(String dt_stamp) {
		this.dt_stamp = dt_stamp;
	}
	public String getUsuario_stamp() {
		return usuario_stamp;
	}
	public void setUsuario_stamp(String usuario_stamp) {
		this.usuario_stamp = usuario_stamp;
	}
	public String getCd_Situacao() {
		return cd_Situacao;
	}
	public void setCd_Situacao(String cd_Situacao) {
		this.cd_Situacao = cd_Situacao;
	}
	public String getNm_Situacao() {
		return nm_Situacao;
	}
	public void setNm_Situacao(String nm_Situacao) {
		this.nm_Situacao = nm_Situacao;
	}
	public long getOid_Situacao() {
		return oid_Situacao;
	}
	public void setOid_Situacao(long oid_Situacao) {
		this.oid_Situacao = oid_Situacao;
	}	
}