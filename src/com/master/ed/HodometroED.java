package com.master.ed;

/**
 * @author Régis Steigleder
 * @serial Medicao de sulcos de pneus
 * @serialData 08/2007
 */
public class HodometroED  extends RelatorioED {
	
	private static final long serialVersionUID = -2283453071259798249L;

	private long oid_Odometro;
	private long oid_Empresa;
	private String oid_Veiculo;
	private double nr_Odometro_Retirado; 
	private double nr_Odometro_Colocado;
	private long nr_Odometro_Maximo;
	private String dt_Odometro_Troca;
	private double nr_Km_Acum_Troca;
	private double nr_Odometro_Anterior;
	private double nr_Km_Acum_Anterior;
	private long nr_Odometro_Maximo_Anterior;
	private String dm_Tipo_Troca;
	private String msg_Stamp;
	
	private String nr_Frota;
	
	private String dm_Retorno;
	private String dm_Virada;
	
	public String getDt_Odometro_Troca() {
		return dt_Odometro_Troca;
	}
	public void setDt_Odometro_Troca(String dt_Odometro_Troca) {
		this.dt_Odometro_Troca = dt_Odometro_Troca;
	}
	public double getNr_Km_Acum_Anterior() {
		return nr_Km_Acum_Anterior;
	}
	public void setNr_Km_Acum_Anterior(double nr_Km_Acum_Anterior) {
		this.nr_Km_Acum_Anterior = nr_Km_Acum_Anterior;
	}
	public double getNr_Km_Acum_Troca() {
		return nr_Km_Acum_Troca;
	}
	public void setNr_Km_Acum_Troca(double nr_Km_Acum_Troca) {
		this.nr_Km_Acum_Troca = nr_Km_Acum_Troca;
	}
	public double getNr_Odometro_Anterior() {
		return nr_Odometro_Anterior;
	}
	public void setNr_Odometro_Anterior(double nr_Odometro_Anterior) {
		this.nr_Odometro_Anterior = nr_Odometro_Anterior;
	}
	public double getNr_Odometro_Colocado() {
		return nr_Odometro_Colocado;
	}
	public void setNr_Odometro_Colocado(double nr_Odometro_Colocado) {
		this.nr_Odometro_Colocado = nr_Odometro_Colocado;
	}
	public long getNr_Odometro_Maximo() {
		return nr_Odometro_Maximo;
	}
	public void setNr_Odometro_Maximo(long nr_Odometro_Maximo) {
		this.nr_Odometro_Maximo = nr_Odometro_Maximo;
	}
	public long getNr_Odometro_Maximo_Anterior() {
		return nr_Odometro_Maximo_Anterior;
	}
	public void setNr_Odometro_Maximo_Anterior(long nr_Odometro_Maximo_Anterior) {
		this.nr_Odometro_Maximo_Anterior = nr_Odometro_Maximo_Anterior;
	}
	public double getNr_Odometro_Retirado() {
		return nr_Odometro_Retirado;
	}
	public void setNr_Odometro_Retirado(double nr_Odometro_Retirado) {
		this.nr_Odometro_Retirado = nr_Odometro_Retirado;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public long getOid_Odometro() {
		return oid_Odometro;
	}
	public void setOid_Odometro(long oid_Odometro) {
		this.oid_Odometro = oid_Odometro;
	}
	public String getOid_Veiculo() {
		return oid_Veiculo;
	}
	public void setOid_Veiculo(String oid_Veiculo) {
		this.oid_Veiculo = oid_Veiculo;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getNr_Frota() {
		return nr_Frota;
	}
	public void setNr_Frota(String nr_Frota) {
		this.nr_Frota = nr_Frota;
	}
	public String getDm_Retorno() {
		return dm_Retorno;
	}
	public void setDm_Retorno(String dm_Retorno) {
		this.dm_Retorno = dm_Retorno;
	}
	public String getDm_Virada() {
		return dm_Virada;
	}
	public void setDm_Virada(String dm_Virada) {
		this.dm_Virada = dm_Virada;
	}
	public String getDm_Tipo_Troca() {
		return dm_Tipo_Troca;
	}
	public void setDm_Tipo_Troca(String dm_Tipo_Troca) {
		this.dm_Tipo_Troca = dm_Tipo_Troca;
	}
	public String getMsg_Stamp() {
		return msg_Stamp;
	}
	public void setMsg_Stamp(String msg_Stamp) {
		this.msg_Stamp = msg_Stamp;
	}

	
}
