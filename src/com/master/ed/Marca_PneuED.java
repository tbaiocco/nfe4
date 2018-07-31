package com.master.ed;

import java.io.Serializable;

/**
 * @author Regis
 *
 */
public class Marca_PneuED extends MasterED implements Serializable {

	private static final long serialVersionUID = 7565709400038136233L;

	public Marca_PneuED() {
	}

	private long oid_Marca_Pneu;
	private String nm_Marca_Pneu;
	private long oid_Empresa;

	public String getNm_Marca_Pneu() {
		return nm_Marca_Pneu;
	}
	public void setNm_Marca_Pneu(String nm_Marca_Pneu) {
		this.nm_Marca_Pneu = nm_Marca_Pneu;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public long getOid_Marca_Pneu() {
		return oid_Marca_Pneu;
	}
	public void setOid_Marca_Pneu(long oid_Marca_Pneu) {
		this.oid_Marca_Pneu = oid_Marca_Pneu;
	}

}
