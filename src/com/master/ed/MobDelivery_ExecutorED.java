package com.master.ed;

import java.io.Serializable;

/**
 * @author Régis Steigleder
 * @serial Eecutor de tarefa no ModDelivery 
 * @serialData 19/05/2008
 */

public class MobDelivery_ExecutorED extends RelatorioBaseED implements Serializable  {

	private static final long serialVersionUID = -7047260804653734576L;

	public MobDelivery_ExecutorED() {
		super();
	}

	private long oid_Executor;
	private long oid_Unidade;
	private String nm_Executor;
	private String nr_Celular;
	private String tx_Senha;
	private String dm_Reenviar;
	//-----------------------//
	private String nm_Unidade;
	private String dt_Dia;
	
	public String getNm_Executor() {
		return nm_Executor;
	}
	public void setNm_Executor(String nm_Executor) {
		this.nm_Executor = nm_Executor;
	}
	public String getNr_Celular() {
		return nr_Celular;
	}
	public void setNr_Celular(String nr_Celular) {
		this.nr_Celular = nr_Celular;
	}
	public long getOid_Executor() {
		return oid_Executor;
	}
	public void setOid_Executor(long oid_Executor) {
		this.oid_Executor = oid_Executor;
	}
	public long getOid_Unidade() {
		return oid_Unidade;
	}
	public void setOid_Unidade(long oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}
	public String getTx_Senha() {
		return tx_Senha;
	}
	public void setTx_Senha(String tx_Senha) {
		this.tx_Senha = tx_Senha;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getNm_Unidade() {
		return nm_Unidade;
	}
	public void setNm_Unidade(String nm_Unidade) {
		this.nm_Unidade = nm_Unidade;
	}
	public String getDt_Dia() {
		return dt_Dia;
	}
	public void setDt_Dia(String dt_Dia) {
		this.dt_Dia = dt_Dia;
	}
	public String getDm_Reenviar() {
		return dm_Reenviar;
	}
	public void setDm_Reenviar(String dm_Reenviar) {
		this.dm_Reenviar = dm_Reenviar;
	}

}
