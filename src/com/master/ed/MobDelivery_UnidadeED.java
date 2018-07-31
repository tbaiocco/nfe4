package com.master.ed;

import java.io.Serializable;

/**
 * @author Régis Steigleder
 * @serial Unidade de negócio no ModDelivery 
 * @serialData 19/05/2008
 */

public class MobDelivery_UnidadeED extends RelatorioBaseED implements Serializable  {

	private static final long serialVersionUID = 1689204569338107250L;

	public MobDelivery_UnidadeED() {
		super();
	}
	
	private long oid_Unidade;
	private String nr_Unidade;
	private String nm_Unidade;

	public String getNm_Unidade() {
		return nm_Unidade;
	}
	public void setNm_Unidade(String nm_Unidade) {
		this.nm_Unidade = nm_Unidade;
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
	public String getNr_Unidade() {
		return nr_Unidade;
	}
	public void setNr_Unidade(String nr_Unidade) {
		this.nr_Unidade = nr_Unidade;
	}


}
