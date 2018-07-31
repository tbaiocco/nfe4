package com.master.ed;

import java.io.Serializable;

/**
 * @author Ralph 
 * @serial Tipos de Veículos
 * @serialData 06/2007
 */
public class Tipo_VeiculoED extends RelatorioBaseED {

	private static final long serialVersionUID = -4514942979654556619L;

	public Tipo_VeiculoED() {
	}
	  private long oid_Tipo_Veiculo;
	  private long oid_Empresa;
	  private String nm_Tipo_Veiculo;
		private String msg_Stamp;

	public String getMsg_Stamp() {
			return msg_Stamp;
		}
		public void setMsg_Stamp(String msg_Stamp) {
			this.msg_Stamp = msg_Stamp;
		}
	public String getNm_Tipo_Veiculo() {
		return nm_Tipo_Veiculo;
	}
	public void setNm_Tipo_Veiculo(String nm_Tipo_Veiculo) {
		this.nm_Tipo_Veiculo = nm_Tipo_Veiculo;
	}
	public long getOid_Tipo_Veiculo() {
		return oid_Tipo_Veiculo;
	}
	public void setOid_Tipo_Veiculo(long oid_Tipo_Veiculo) {
		this.oid_Tipo_Veiculo = oid_Tipo_Veiculo;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	

}
