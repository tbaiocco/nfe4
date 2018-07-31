package com.master.ed;



/**
 * @author Regis
 *
 */
public class Tipo_PneuED extends RelatorioBaseED {

	private static final long serialVersionUID = -4665476889620959701L;

	public Tipo_PneuED() {
	}

	private long oid_Tipo_Pneu;
	private String nm_Tipo_Pneu;
	private long oid_Empresa;

	public String getNm_Tipo_Pneu() {
		return nm_Tipo_Pneu;
	}
	public void setNm_Tipo_Pneu(String nm_Tipo_Pneu) {
		this.nm_Tipo_Pneu = nm_Tipo_Pneu;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public long getOid_Tipo_Pneu() {
		return oid_Tipo_Pneu;
	}
	public void setOid_Tipo_Pneu(long oid_Tipo_Pneu) {
		this.oid_Tipo_Pneu = oid_Tipo_Pneu;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
