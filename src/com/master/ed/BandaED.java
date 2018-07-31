/*
 * Created on 29/09/2007
 *
 */
package com.master.ed;

/**
 * @author Régis Steigleder
 * @serial Bandas de recapagens
 * @serialData 06/2007
 */
public class BandaED  extends RelatorioBaseED {

	private static final long serialVersionUID = 2263295955710086386L;

	private long oid_Banda;
	private long oid_Fabricante_Banda;
	//fk fabricante
	private String cd_Fabricante_Banda;
	private String nm_Fabricante_Banda;

	private long oid_Empresa;
	private String cd_Banda;
	private String nm_Banda;
	private double nr_Largura;
	private double nr_Profundidade;

	public BandaED () {

	}

	public String getCd_Banda() {
		return cd_Banda;
	}

	public void setCd_Banda(String cd_Banda) {
		this.cd_Banda = cd_Banda;
	}

	public String getNm_Banda() {
		return nm_Banda;
	}

	public void setNm_Banda(String nm_Banda) {
		this.nm_Banda = nm_Banda;
	}

	public String getNm_Fabricante_Banda() {
		return nm_Fabricante_Banda;
	}

	public void setNm_Fabricante_Banda(String nm_Fabricante_Banda) {
		this.nm_Fabricante_Banda = nm_Fabricante_Banda;
	}

	public double getNr_Largura() {
		return nr_Largura;
	}

	public void setNr_Largura(double nr_Largura) {
		this.nr_Largura = nr_Largura;
	}

	public double getNr_Profundidade() {
		return nr_Profundidade;
	}

	public void setNr_Profundidade(double nr_Profundidade) {
		this.nr_Profundidade = nr_Profundidade;
	}

	public long getOid_Banda() {
		return oid_Banda;
	}

	public void setOid_Banda(long oid_Banda) {
		this.oid_Banda = oid_Banda;
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

	public String getCd_Fabricante_Banda() {
		return cd_Fabricante_Banda;
	}

	public void setCd_Fabricante_Banda(String cd_Fabricante_Banda) {
		this.cd_Fabricante_Banda = cd_Fabricante_Banda;
	}

}