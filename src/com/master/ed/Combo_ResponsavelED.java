package com.master.ed;

import java.io.Serializable;

/**
 * @author Cristian Vianna Garcia
 *
 */
public class Combo_ResponsavelED extends MasterED implements Serializable {

	private static final long serialVersionUID = 8020316821180021992L;

	public Combo_ResponsavelED() {
	}
	
	private long oid_Responsavel;
	private String cd_Responsavel;
	private String nm_Responsavel;
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
	public String getCd_Responsavel() {
		return cd_Responsavel;
	}
	public void setCd_Responsavel(String cd_Responsavel) {
		this.cd_Responsavel = cd_Responsavel;
	}
	public String getNm_Responsavel() {
		return nm_Responsavel;
	}
	public void setNm_Responsavel(String nm_Responsavel) {
		this.nm_Responsavel = nm_Responsavel;
	}
	public long getOid_Responsavel() {
		return oid_Responsavel;
	}
	public void setOid_Responsavel(long oid_Responsavel) {
		this.oid_Responsavel = oid_Responsavel;
	}
	
}