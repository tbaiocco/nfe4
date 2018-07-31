package com.master.ed;

import java.io.Serializable;

/**
 * @author Regis
 *
 */
public class Dimensao_PneuED extends RelatorioBaseED {

	private static final long serialVersionUID = -8669299342587174348L;

	public Dimensao_PneuED() {
	}

	private long oid_Dimensao_Pneu;
	private String nm_Dimensao_Pneu;

	private long oid_Empresa;

	private String dm_Todos;

	private String dm_Nao_Sucateados;
	private String dm_Sucateados;
	private double vl_Dimensao_Pneu;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNm_Dimensao_Pneu() {
		return nm_Dimensao_Pneu;
	}

	public void setNm_Dimensao_Pneu(String nm_Dimensao_Pneu) {
		this.nm_Dimensao_Pneu = nm_Dimensao_Pneu;
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

	public String getDm_Nao_Sucateados() {
		return dm_Nao_Sucateados;
	}

	public void setDm_Nao_Sucateados(String dm_Nao_Sucateados) {
		this.dm_Nao_Sucateados = dm_Nao_Sucateados;
	}

	public String getDm_Sucateados() {
		return dm_Sucateados;
	}

	public void setDm_Sucateados(String dm_Sucateados) {
		this.dm_Sucateados = dm_Sucateados;
	}

	public String getDm_Todos() {
		return dm_Todos;
	}

	public void setDm_Todos(String dm_Todos) {
		this.dm_Todos = dm_Todos;
	}

	public double getVl_Dimensao_Pneu() {
		return vl_Dimensao_Pneu;
	}

	public void setVl_Dimensao_Pneu(double vl_Dimensao_Pneu) {
		this.vl_Dimensao_Pneu = vl_Dimensao_Pneu;
	}

}
