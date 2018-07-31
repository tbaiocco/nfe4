/*
 * Created on 15/10/2004
 */
package com.master.ed;

/**
 * @author Andre Valadas
 */
public class SegmentoED extends MasterED{
	
	private int oid_Segmento;
	private String cd_Segmento;
	private String nm_Segmento;

	/**
	 * @return Returns the cd_Segmento.
	 */
	public String getCd_Segmento() {
		return cd_Segmento;
	}
	/**
	 * @param cd_Segmento The cd_Segmento to set.
	 */
	public void setCd_Segmento(String cd_Segmento) {
		this.cd_Segmento = cd_Segmento;
	}
	/**
	 * @return Returns the nm_Segmento.
	 */
	public String getNm_Segmento() {
		return nm_Segmento;
	}
	/**
	 * @param nm_Segmento The nm_Segmento to set.
	 */
	public void setNm_Segmento(String nm_Segmento) {
		this.nm_Segmento = nm_Segmento;
	}
	/**
	 * @return Returns the oid_Segmento.
	 */
	public int getOid_Segmento() {
		return oid_Segmento;
	}
	/**
	 * @param oid_Segmento The oid_Segmento to set.
	 */
	public void setOid_Segmento(int oid_Segmento) {
		this.oid_Segmento = oid_Segmento;
	}
}
