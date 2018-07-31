package com.master.ed;

import java.io.Serializable;

/**
 * @author Regis
 *
 */
public class SistemaED extends MasterED implements Serializable {

	private static final long serialVersionUID = 8020316821180021992L;

	public SistemaED() {
	}
	
	private long oid_Sistema;
	private String cd_Sistema;
	private String nm_Sistema;
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
	public String getCd_Sistema() {
		return cd_Sistema;
	}
	public void setCd_Sistema(String cd_Sistema) {
		this.cd_Sistema = cd_Sistema;
	}
	public String getNm_Sistema() {
		return nm_Sistema;
	}
	public void setNm_Sistema(String nm_Sistema) {
		this.nm_Sistema = nm_Sistema;
	}
	public long getOid_Sistema() {
		return oid_Sistema;
	}
	public void setOid_Sistema(long oid_Sistema) {
		this.oid_Sistema = oid_Sistema;
	}
	
}
