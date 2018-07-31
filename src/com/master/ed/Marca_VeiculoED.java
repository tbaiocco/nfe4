package com.master.ed;

import java.io.Serializable;

/**
 * @author Ralph & Cristian
 * @serial Marcas de Veículos
 * @serialData 06/2007
 */
public class Marca_VeiculoED extends RelatorioBaseED {

	private static final long serialVersionUID = 7565709400038136233L;

	public Marca_VeiculoED() {
	}
	
	private long oid_Empresa;
	private long oid_Marca_Veiculo;
	private String nm_Marca_Veiculo;
	private String msg_Stamp;

	public String getMsg_Stamp() {
		return msg_Stamp;
	}
	public void setMsg_Stamp(String msg_Stamp) {
		this.msg_Stamp = msg_Stamp;
	}
	public String getNm_Marca_Veiculo() {
		return nm_Marca_Veiculo;
	}
	public void setNm_Marca_Veiculo(String nm_Marca_Veiculo) {
		this.nm_Marca_Veiculo = nm_Marca_Veiculo;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public long getOid_Marca_Veiculo() {
		return oid_Marca_Veiculo;
	}
	public void setOid_Marca_Veiculo(long oid_Marca_Veiculo) {
		this.oid_Marca_Veiculo = oid_Marca_Veiculo;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}



}
