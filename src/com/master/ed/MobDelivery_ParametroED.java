package com.master.ed;

import java.io.Serializable;

/**
 * @author Régis Steigleder
 * @serial Eecutor de tarefa no ModDelivery 
 * @serialData 19/05/2008
 */

public class MobDelivery_ParametroED extends RelatorioBaseED implements Serializable  {

	private static final long serialVersionUID = -527241827172894789L;
	
	public MobDelivery_ParametroED() {
		super();
	}
	private String nm_Parametro;
	private long nr_Frequencia_Refresh_Tela;
	private long nr_Frequencia_Refresh_Cel;
	private String dt_Aberto;

	public String getDt_Aberto() {
		return dt_Aberto;
	}
	public void setDt_Aberto(String dt_Aberto) {
		this.dt_Aberto = dt_Aberto;
	}
	public long getNr_Frequencia_Refresh_Cel() {
		return nr_Frequencia_Refresh_Cel;
	}
	public void setNr_Frequencia_Refresh_Cel(long nr_Frequencia_Refresh_Cel) {
		this.nr_Frequencia_Refresh_Cel = nr_Frequencia_Refresh_Cel;
	}
	public long getNr_Frequencia_Refresh_Tela() {
		return nr_Frequencia_Refresh_Tela;
	}
	public void setNr_Frequencia_Refresh_Tela(long nr_Frequencia_Refresh_Tela) {
		this.nr_Frequencia_Refresh_Tela = nr_Frequencia_Refresh_Tela;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getNm_Parametro() {
		return nm_Parametro;
	}
	public void setNm_Parametro(String nm_Parametro) {
		this.nm_Parametro = nm_Parametro;
	}
	
	
}
