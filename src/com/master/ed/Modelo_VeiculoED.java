/*
* Created on 25/06/2007
*
*/
package com.master.ed;

/**
 * @author Cristian Vianna Garcia
 * @serial Modelos de Veículos
 * @serialData 06/2007
 */
public class Modelo_VeiculoED  extends RelatorioBaseED {
	

	private static final long serialVersionUID = 2925709799391984270L;

  private long oid_Modelo_Veiculo;
  private long oid_Empresa;
  private String nm_Modelo_Veiculo;
  private long oid_Tipo_Veiculo;
  private String nm_Tipo_Veiculo;
  private long oid_Marca_Veiculo;
  private String nm_Marca_Veiculo;
  private long dm_Tipo_Chassis;
  private double nr_Media_Inferior;
  private double nr_Media_Superior;
	private String msg_Stamp;
  
public String getMsg_Stamp() {
		return msg_Stamp;
	}
	public void setMsg_Stamp(String msg_Stamp) {
		this.msg_Stamp = msg_Stamp;
	}
public long getDm_Tipo_Chassis() {
	return dm_Tipo_Chassis;
}
public void setDm_Tipo_Chassis(long dm_Tipo_Chassis) {
	this.dm_Tipo_Chassis = dm_Tipo_Chassis;
}
public String getNm_Marca_Veiculo() {
	return nm_Marca_Veiculo;
}
public void setNm_Marca_Veiculo(String nm_Marca_Veiculo) {
	this.nm_Marca_Veiculo = nm_Marca_Veiculo;
}
public String getNm_Modelo_Veiculo() {
	return nm_Modelo_Veiculo;
}
public void setNm_Modelo_Veiculo(String nm_Modelo_Veiculo) {
	this.nm_Modelo_Veiculo = nm_Modelo_Veiculo;
}
public String getNm_Tipo_Veiculo() {
	return nm_Tipo_Veiculo;
}
public void setNm_Tipo_Veiculo(String nm_Tipo_Veiculo) {
	this.nm_Tipo_Veiculo = nm_Tipo_Veiculo;
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
public long getOid_Modelo_Veiculo() {
	return oid_Modelo_Veiculo;
}
public void setOid_Modelo_Veiculo(long oid_Modelo_Veiculo) {
	this.oid_Modelo_Veiculo = oid_Modelo_Veiculo;
}
public long getOid_Tipo_Veiculo() {
	return oid_Tipo_Veiculo;
}
public void setOid_Tipo_Veiculo(long oid_Tipo_Veiculo) {
	this.oid_Tipo_Veiculo = oid_Tipo_Veiculo;
}
public static long getSerialVersionUID() {
	return serialVersionUID;
}
public double getNr_Media_Inferior() {
	return nr_Media_Inferior;
}
public void setNr_Media_Inferior(double nr_Media_Inferior) {
	this.nr_Media_Inferior = nr_Media_Inferior;
}
public double getNr_Media_Superior() {
	return nr_Media_Superior;
}
public void setNr_Media_Superior(double nr_Media_Superior) {
	this.nr_Media_Superior = nr_Media_Superior;
}
}