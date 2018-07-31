package com.master.ed;

import java.io.Serializable;

/**
 * @author Régis Steigleder
 * @serial 
 * @serialData 02/12/2005
 */
public class Periodo_AbertoED extends MasterED implements Serializable {

	public Periodo_AbertoED() {
        super();
    }
    
    private long oid_Periodo_Aberto;
    private String dt_Inicial;
    private String dt_Final;
    private String nm_Periodo_Aberto;

	private static final long serialVersionUID = 7479190088388125281L;
	
	public String getDt_Final() {
		return dt_Final;
	}
	public String getDt_Inicial() {
		return dt_Inicial;
	}
	public String getNm_Periodo_Aberto() {
		return nm_Periodo_Aberto;
	}
	public long getOid_Periodo_Aberto() {
		return oid_Periodo_Aberto;
	}
	public void setDt_Final(String dt_Final) {
		this.dt_Final = dt_Final;
	}
	public void setDt_Inicial(String dt_Inicial) {
		this.dt_Inicial = dt_Inicial;
	}
	public void setNm_Periodo_Aberto(String nm_Periodo_Aberto) {
		this.nm_Periodo_Aberto = nm_Periodo_Aberto;
	}
	public void setOid_Periodo_Aberto(long oid_Periodo_Aberto) {
		this.oid_Periodo_Aberto = oid_Periodo_Aberto;
	}

}