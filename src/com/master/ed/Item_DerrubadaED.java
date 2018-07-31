package com.master.ed;

import com.master.util.JavaUtil;
import com.master.util.Valores;

public class Item_DerrubadaED extends DerrubadaED {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private long oid_item_derrubada;
	private String dt_lote;
	private double nr_quantidade;
	private double nr_quantidade_contada;
	private String nr_quantidade_TX;
	private String nr_quantidade_contada_TX;
	//private long oid_produto;

	//descritivos de FKs
	private String cd_produto;
	private String nm_produto;

	public String getDt_lote() {
		return dt_lote;
	}
	public void setDt_lote(String dt_lote) {
		this.dt_lote = dt_lote;
	}
	public double getNr_quantidade() {
		return nr_quantidade;
	}
	public void setNr_quantidade(double nr_quantidade) {
		this.nr_quantidade = nr_quantidade;
	}
	public double getNr_quantidade_contada() {
		return nr_quantidade_contada;
	}
	public void setNr_quantidade_contada(double nr_quantidade_contada) {
		this.nr_quantidade_contada = nr_quantidade_contada;
	}
	public String getNr_quantidade_contada_TX() {
		return nr_quantidade_contada_TX;
	}
	public void setNr_quantidade_contada_TX(String nr_quantidade_contada_TX) {
		this.nr_quantidade_contada_TX = nr_quantidade_contada_TX;
		this.setNr_quantidade_contada(Valores.converteStringToDouble(nr_quantidade_contada_TX));
	}
	public String getNr_quantidade_TX() {
		return nr_quantidade_TX;
	}
	public void setNr_quantidade_TX(String nr_quantidade_TX) {
		this.nr_quantidade_TX = nr_quantidade_TX;
		this.setNr_quantidade(Valores.converteStringToDouble(nr_quantidade_TX));
	}

	public long getOid_item_derrubada() {
		return oid_item_derrubada;
	}
	public void setOid_item_derrubada(long oid_item_derrubada) {
		this.oid_item_derrubada = oid_item_derrubada;
	}
//	public long getOid_produto() {
//		return oid_produto;
//	}
//	public void setOid_produto(long oid_produto) {
//		this.oid_produto = oid_produto;
//	}
	public String getCd_produto() {
		return cd_produto;
	}
	public void setCd_produto(String cd_produto) {
		this.cd_produto = cd_produto;
	}
	public String getNm_produto() {
		return nm_produto;
	}
	public void setNm_produto(String nm_produto) {
		this.nm_produto = nm_produto;
	}

}
