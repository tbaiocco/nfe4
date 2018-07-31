/*
 * Created on 12/11/2004
 *
 */
package com.master.ed;

/**
 * @author Régis Steigleder
 * @serial Modelos de pneus
 * @serialData 06/2007
 */
public class Modelo_PneuED  extends RelatorioBaseED {

	private static final long serialVersionUID = 7928915324972837643L;

	private int oid_Modelo_Pneu;
	private long oid_Fabricante_Pneu;
	private String nm_Fabricante_Pneu;
	private long oid_Empresa;
	private String CD_Modelo_Pneu;
	private String NM_Modelo_Pneu;

	public Modelo_PneuED () {
	}

	public Modelo_PneuED (int oid_Modelo_Pneu , String CD_modelo_Pneu , String NM_modelo_Pneu) {
		super ();
		this.oid_Modelo_Pneu = oid_Modelo_Pneu;
		this.CD_Modelo_Pneu = CD_modelo_Pneu;
		this.NM_Modelo_Pneu = NM_modelo_Pneu;
	}

	public String getCD_Modelo_Pneu() {
		return CD_Modelo_Pneu;
	}

	public void setCD_Modelo_Pneu(String modelo_Pneu) {
		CD_Modelo_Pneu = modelo_Pneu;
	}

	public String getNM_Modelo_Pneu() {
		return NM_Modelo_Pneu;
	}

	public void setNM_Modelo_Pneu(String modelo_Pneu) {
		NM_Modelo_Pneu = modelo_Pneu;
	}

	public long getOid_Empresa() {
		return oid_Empresa;
	}

	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}

	public long getOid_Fabricante_Pneu() {
		return oid_Fabricante_Pneu;
	}

	public void setOid_Fabricante_Pneu(long oid_Fabricante_Pneu) {
		this.oid_Fabricante_Pneu = oid_Fabricante_Pneu;
	}

	public int getOid_Modelo_Pneu() {
		return oid_Modelo_Pneu;
	}

	public void setOid_Modelo_Pneu(int oid_Modelo_Pneu) {
		this.oid_Modelo_Pneu = oid_Modelo_Pneu;
	}

	public String getNm_Fabricante_Pneu() {
		return nm_Fabricante_Pneu;
	}

	public void setNm_Fabricante_Pneu(String nm_Fabricante_Pneu) {
		this.nm_Fabricante_Pneu = nm_Fabricante_Pneu;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
