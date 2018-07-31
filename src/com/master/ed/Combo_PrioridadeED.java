package com.master.ed;

import java.io.Serializable;

/**
 * @author Cristian Vianna Garcia
 *
 */
public class Combo_PrioridadeED extends MasterED implements Serializable {

	private static final long serialVersionUID = 8020316821180021992L;

	public Combo_PrioridadeED() {
	}
	
	private long oid_Prioridade;
	private String cd_Prioridade;
	private String nm_Prioridade;
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
	public String getCd_Prioridade() {
		return cd_Prioridade;
	}
	public void setCd_Prioridade(String cd_Prioridade) {
		this.cd_Prioridade = cd_Prioridade;
	}
	public String getNm_Prioridade() {
		return nm_Prioridade;
	}
	public void setNm_Prioridade(String nm_Prioridade) {
		this.nm_Prioridade = nm_Prioridade;
	}
	public long getOid_Prioridade() {
		return oid_Prioridade;
	}
	public void setOid_Prioridade(long oid_Prioridade) {
		this.oid_Prioridade = oid_Prioridade;
	}
	
}