package com.master.ed;

import java.io.Serializable;

/**
 * @author Régis Steigleder
 * @serial Situação da tarefa no ModDelivery 
 * @serialData 19/05/2008
 */

public class MobDelivery_SituacaoED extends RelatorioBaseED implements Serializable  {

	private static final long serialVersionUID = 1202708444771116776L;
	
	public MobDelivery_SituacaoED() {
		super();
	}

	private long oid_Situacao;
	private String cd_Situacao;
	private String nm_Situacao;
	private long nr_Cor;
	private String dm_Exige_Recebedor ;
	private String cd_Externo;
	
	public String getCd_Situacao() {
		return cd_Situacao;
	}
	public void setCd_Situacao(String cd_Situacao) {
		this.cd_Situacao = cd_Situacao;
	}
	public String getNm_Situacao() {
		return nm_Situacao;
	}
	public void setNm_Situacao(String nm_Situacao) {
		this.nm_Situacao = nm_Situacao;
	}
	public long getOid_Situacao() {
		return oid_Situacao;
	}
	public void setOid_Situacao(long oid_Situacao) {
		this.oid_Situacao = oid_Situacao;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public long getNr_Cor() {
		return nr_Cor;
	}
	public void setNr_Cor(long nr_Cor) {
		this.nr_Cor = nr_Cor;
	}
	public String getDm_Exige_Recebedor() {
		return dm_Exige_Recebedor;
	}
	public void setDm_Exige_Recebedor(String dm_Exige_Recebedor) {
		this.dm_Exige_Recebedor = dm_Exige_Recebedor;
	}
	public String getCd_Externo() {
		return cd_Externo;
	}
	public void setCd_Externo(String cd_Externo) {
		this.cd_Externo = cd_Externo;
	}
	
}
