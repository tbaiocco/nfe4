package com.master.ed;

import java.io.Serializable;

/**
 * @author Régis Steigleder
 * @serial Agrupamento de tarefa no ModDelivery 
 * @serialData 19/05/2008
 */

public class MobDelivery_Grupo_TarefaED extends RelatorioBaseED implements Serializable  {

	private static final long serialVersionUID = 549373252580585981L;
	
	public MobDelivery_Grupo_TarefaED() {
		super();
	}
	
	private long oid_Grupo_Tarefa;
	private long oid_Unidade;
	private long nr_Grupo_Tarefa;
	private String nm_Grupo_Tarefa;
	private String dt_Grupo_Tarefa;
	private String oid_Romaneio;
	//--------------------------//
	private String cd_Unidade;
	private String dt_Inicial;
	private String dt_Final;
	
	
	public String getDt_Grupo_Tarefa() {
		return dt_Grupo_Tarefa;
	}
	public void setDt_Grupo_Tarefa(String dt_Grupo_Tarefa) {
		this.dt_Grupo_Tarefa = dt_Grupo_Tarefa;
	}
	
	public String getNm_Grupo_Tarefa() {
		return nm_Grupo_Tarefa;
	}
	public void setNm_Grupo_Tarefa(String nm_Grupo_Tarefa) {
		this.nm_Grupo_Tarefa = nm_Grupo_Tarefa;
	}
	public long getNr_Grupo_Tarefa() {
		return nr_Grupo_Tarefa;
	}
	public void setNr_Grupo_Tarefa(long nr_Grupo_Tarefa) {
		this.nr_Grupo_Tarefa = nr_Grupo_Tarefa;
	}
	public long getOid_Grupo_Tarefa() {
		return oid_Grupo_Tarefa;
	}
	public void setOid_Grupo_Tarefa(long oid_Grupo_Tarefa) {
		this.oid_Grupo_Tarefa = oid_Grupo_Tarefa;
	}
	public long getOid_Unidade() {
		return oid_Unidade;
	}
	public void setOid_Unidade(long oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getOid_Romaneio() {
		return oid_Romaneio;
	}
	public void setOid_Romaneio(String oid_Romaneio) {
		this.oid_Romaneio = oid_Romaneio;
	}
	public String getCd_Unidade() {
		return cd_Unidade;
	}
	public void setCd_Unidade(String cd_Unidade) {
		this.cd_Unidade = cd_Unidade;
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
		
}
