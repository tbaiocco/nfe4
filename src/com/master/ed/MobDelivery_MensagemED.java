package com.master.ed;

import java.io.Serializable;

/**
 * @author Régis Steigleder
 * @serial Tarefa no ModDelivery 
 * @serialData 19/05/2008
 */

public class MobDelivery_MensagemED extends RelatorioBaseED implements Serializable  {

	private static final long serialVersionUID = -1542952900694719547L;
	
	public MobDelivery_MensagemED() {
		super();
	}
	
	private long oid_Mensagem;
	private long oid_Unidade;
	private long nr_Mensagem;
	private String tx_Mensagem;
	private String dt_Mensagem;
	private long oid_Executor;
	private String dm_Protocolo;
	private long nr_Protocolo;

	public String getDm_Protocolo() {
		return dm_Protocolo;
	}
	public void setDm_Protocolo(String dm_Protocolo) {
		this.dm_Protocolo = dm_Protocolo;
	}
	public String getDt_Mensagem() {
		return dt_Mensagem;
	}
	public void setDt_Mensagem(String dt_Mensagem) {
		this.dt_Mensagem = dt_Mensagem;
	}
	public long getNr_Mensagem() {
		return nr_Mensagem;
	}
	public void setNr_Mensagem(long nr_Mensagem) {
		this.nr_Mensagem = nr_Mensagem;
	}
	public long getNr_Protocolo() {
		return nr_Protocolo;
	}
	public void setNr_Protocolo(long nr_Protocolo) {
		this.nr_Protocolo = nr_Protocolo;
	}
	public long getOid_Executor() {
		return oid_Executor;
	}
	public void setOid_Executor(long oid_Executor) {
		this.oid_Executor = oid_Executor;
	}
	public long getOid_Mensagem() {
		return oid_Mensagem;
	}
	public void setOid_Mensagem(long oid_Mensagem) {
		this.oid_Mensagem = oid_Mensagem;
	}
	public long getOid_Unidade() {
		return oid_Unidade;
	}
	public void setOid_Unidade(long oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}
	public String getTx_Mensagem() {
		return tx_Mensagem;
	}
	public void setTx_Mensagem(String tx_Mensagem) {
		this.tx_Mensagem = tx_Mensagem;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}	
	