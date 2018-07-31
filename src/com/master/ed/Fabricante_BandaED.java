/*
 * Created on 11/11/2004
 */
package com.master.ed;

/**
 * @author Régis Steigleder
 * @serial Fabricantes de pneus
 * @serialData 06/2007
 */
public class Fabricante_BandaED extends RelatorioBaseED {

	private static final long serialVersionUID = -702531689095492809L;

	private long oid_Fabricante_Banda;
	private String cd_Fabricante_Banda;
	private String nm_Fabricante_Banda;
	private long oid_Empresa;

	public Fabricante_BandaED () {
	}

	public String getCd_Fabricante_Banda() {
		return cd_Fabricante_Banda;
	}

	public void setCd_Fabricante_Banda(String cd_Fabricante_Banda) {
		this.cd_Fabricante_Banda = cd_Fabricante_Banda;
	}

	public String getNm_Fabricante_Banda() {
		return nm_Fabricante_Banda;
	}

	public void setNm_Fabricante_Banda(String nm_Fabricante_Banda) {
		this.nm_Fabricante_Banda = nm_Fabricante_Banda;
	}

	public long getOid_Empresa() {
		return oid_Empresa;
	}

	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}

	public long getOid_Fabricante_Banda() {
		return oid_Fabricante_Banda;
	}

	public void setOid_Fabricante_Banda(long oid_Fabricante_Banda) {
		this.oid_Fabricante_Banda = oid_Fabricante_Banda;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
