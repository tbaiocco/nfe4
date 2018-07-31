package com.master.ed;

/**
 * @author Regis
 *
 */
public class Cotacao_PneuED extends RelatorioBaseED {

	private static final long serialVersionUID = 5276925579696307189L;
	
	public Cotacao_PneuED() {
	}
	
	private long oid_Empresa;
	private long oid_Cotacao_Pneu;
	private long oid_Dimensao_Pneu;
	private long oid_Modelo_Pneu;
	private double vl_Cotacao_Pneu;
	private String dt_Cotacao_Pneu;

	private String nm_Dimensao_Pneu;
	private String nm_Modelo_Pneu;
	private String nm_Fabricante_Pneu;

	private String array;
	private String msg_Stamp;
	
	private long oid_Fabricante_Pneu;
	private String nm_Tipo_Pneu;
	private long nr_Quantidade;
	private double nr_Km_Acumulada;
	
	// Campos para consultas
	
	
	public String getDt_Cotacao_Pneu() {
		return dt_Cotacao_Pneu;
	}
	public void setDt_Cotacao_Pneu(String dt_Cotacao_Pneu) {
		this.dt_Cotacao_Pneu = dt_Cotacao_Pneu;
	}
	public long getOid_Cotacao_Pneu() {
		return oid_Cotacao_Pneu;
	}
	public void setOid_Cotacao_Pneu(long oid_Cotacao_Pneu) {
		this.oid_Cotacao_Pneu = oid_Cotacao_Pneu;
	}
	public long getOid_Dimensao_Pneu() {
		return oid_Dimensao_Pneu;
	}
	public void setOid_Dimensao_Pneu(long oid_Dimensao_Pneu) {
		this.oid_Dimensao_Pneu = oid_Dimensao_Pneu;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public long getOid_Modelo_Pneu() {
		return oid_Modelo_Pneu;
	}
	public void setOid_Modelo_Pneu(long oid_Modelo_Pneu) {
		this.oid_Modelo_Pneu = oid_Modelo_Pneu;
	}
	public double getVl_Cotacao_Pneu() {
		return vl_Cotacao_Pneu;
	}
	public void setVl_Cotacao_Pneu(double vl_Cotacao_Pneu) {
		this.vl_Cotacao_Pneu = vl_Cotacao_Pneu;
	}
	public String getNm_Dimensao_Pneu() {
		return nm_Dimensao_Pneu;
	}
	public void setNm_Dimensao_Pneu(String nm_Dimensao_Pneu) {
		this.nm_Dimensao_Pneu = nm_Dimensao_Pneu;
	}
	public String getNm_Modelo_Pneu() {
		return nm_Modelo_Pneu;
	}
	public void setNm_Modelo_Pneu(String nm_Modelo_Pneu) {
		this.nm_Modelo_Pneu = nm_Modelo_Pneu;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getNm_Fabricante_Pneu() {
		return nm_Fabricante_Pneu;
	}
	public void setNm_Fabricante_Pneu(String nm_Fabricante_Pneu) {
		this.nm_Fabricante_Pneu = nm_Fabricante_Pneu;
	}
	public String getArray() {
		return array;
	}
	public void setArray(String array) {
		this.array = array;
	}
	public String getNm_Tipo_Pneu() {
		return nm_Tipo_Pneu;
	}
	public void setNm_Tipo_Pneu(String nm_Tipo_Pneu) {
		this.nm_Tipo_Pneu = nm_Tipo_Pneu;
	}
	public double getNr_Km_Acumulada() {
		return nr_Km_Acumulada;
	}
	public void setNr_Km_Acumulada(double nr_Km_Acumulada) {
		this.nr_Km_Acumulada = nr_Km_Acumulada;
	}
	public long getNr_Quantidade() {
		return nr_Quantidade;
	}
	public void setNr_Quantidade(long nr_Quantidade) {
		this.nr_Quantidade = nr_Quantidade;
	}
	public long getOid_Fabricante_Pneu() {
		return oid_Fabricante_Pneu;
	}
	public void setOid_Fabricante_Pneu(long oid_Fabricante_Pneu) {
		this.oid_Fabricante_Pneu = oid_Fabricante_Pneu;
	}
	public String getMsg_Stamp() {
		return msg_Stamp;
	}
	public void setMsg_Stamp(String msg_Stamp) {
		this.msg_Stamp = msg_Stamp;
	}

	
}	