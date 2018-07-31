package com.master.ed;

import java.io.Serializable;

/**
 * @author Ralph
 * @serial Cadastro de Indexadores
 * @serialData 06/2007
 */
public class IndexadorED extends RelatorioBaseED {

	private static final long serialVersionUID = 3017584884927401284L;

	public IndexadorED() {
	}

	private long oid_Indexador;
	private double vl_Indexador;
	private String dt_Indexador;
	private String dt_Ano_Indexador;
	private String dt_Mes_Indexador;
	private String dt_Inicial;
	private String dt_Final;
	private long oid_Empresa;

	public String getDt_Indexador() {
		return dt_Indexador;
	}
	public void setDt_Indexador(String dt_Indexador) {
		this.dt_Indexador = dt_Indexador;
	}
	public String getDt_Ano_Indexador() {
		return dt_Ano_Indexador;
	}
	public void setDt_Ano_Indexador(String dt_Ano_Indexador) {
		this.dt_Ano_Indexador = dt_Ano_Indexador;
	}
	public String getDt_Mes_Indexador() {
		return dt_Mes_Indexador;
	}
	public void setDt_Mes_Indexador(String dt_Mes_Indexador) {
		this.dt_Mes_Indexador = dt_Mes_Indexador;
	}
	public String getDt_Final() {
		return dt_Final;
	}
	public void setDt_Final(String dt_Final) {
		this.dt_Final = dt_Final;
	}
	public String getDt_Inicial() {
		return dt_Inicial;
	}
	public void setDt_Inicial(String dt_Inicial) {
		this.dt_Inicial = dt_Inicial;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public long getOid_Indexador() {
		return oid_Indexador;
	}
	public void setOid_Indexador(long oid_Indexador) {
		this.oid_Indexador = oid_Indexador;
	}
	public double getVl_Indexador() {
		return vl_Indexador;
	}
	public void setVl_Indexador(double vl_Indexador) {
		this.vl_Indexador = vl_Indexador;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


}
