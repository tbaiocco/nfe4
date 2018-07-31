package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ImpostoED {

  private int oid_Imposto;
  private double PE_Imposto;
  private double VL_Imposto;
  private double VL_Imposto_Pagar;
  private int oid_Tipo_Imposto;
  private String DM_Periodo;
  private String DM_Tipo_Beneficiario;
  private String NM_Ano;
  private String NM_Mes;
  private String DM_Situacao;
  private String DM_Retem_Imposto;
  private String oid_Beneficiario;
  private long oid_Compromisso;
  private String NM_Imposto;
  
  private int nr_Parcela;
  private int nr_Compromisso;
  private String nr_Documento;
  private double vl_Saldo;
  
  private String dt_stamp;
  private String usuario_Stamp;
  private String dm_Stamp;
  
  
  public ImpostoED() {
  }

public String getDM_Periodo() {
	return DM_Periodo;
}

public void setDM_Periodo(String periodo) {
	DM_Periodo = periodo;
}

public String getDM_Retem_Imposto() {
	return DM_Retem_Imposto;
}

public void setDM_Retem_Imposto(String retem_Imposto) {
	DM_Retem_Imposto = retem_Imposto;
}

public String getDM_Situacao() {
	return DM_Situacao;
}

public void setDM_Situacao(String situacao) {
	DM_Situacao = situacao;
}

public String getDM_Tipo_Beneficiario() {
	return DM_Tipo_Beneficiario;
}

public void setDM_Tipo_Beneficiario(String tipo_Beneficiario) {
	DM_Tipo_Beneficiario = tipo_Beneficiario;
}

public String getNM_Ano() {
	return NM_Ano;
}

public void setNM_Ano(String ano) {
	NM_Ano = ano;
}

public String getNM_Imposto() {
	return NM_Imposto;
}

public void setNM_Imposto(String imposto) {
	NM_Imposto = imposto;
}

public String getNM_Mes() {
	return NM_Mes;
}

public void setNM_Mes(String mes) {
	NM_Mes = mes;
}

public String getOid_Beneficiario() {
	return oid_Beneficiario;
}

public void setOid_Beneficiario(String oid_Beneficiario) {
	this.oid_Beneficiario = oid_Beneficiario;
}

public long getOid_Compromisso() {
	return oid_Compromisso;
}

public void setOid_Compromisso(long oid_Compromisso) {
	this.oid_Compromisso = oid_Compromisso;
}

public int getOid_Imposto() {
	return oid_Imposto;
}

public void setOid_Imposto(int oid_Imposto) {
	this.oid_Imposto = oid_Imposto;
}

public int getOid_Tipo_Imposto() {
	return oid_Tipo_Imposto;
}

public void setOid_Tipo_Imposto(int oid_Tipo_Imposto) {
	this.oid_Tipo_Imposto = oid_Tipo_Imposto;
}

public double getPE_Imposto() {
	return PE_Imposto;
}

public void setPE_Imposto(double imposto) {
	PE_Imposto = imposto;
}

public double getVL_Imposto() {
	return VL_Imposto;
}

public void setVL_Imposto(double imposto) {
	VL_Imposto = imposto;
}

public double getVL_Imposto_Pagar() {
	return VL_Imposto_Pagar;
}

public void setVL_Imposto_Pagar(double imposto_Pagar) {
	VL_Imposto_Pagar = imposto_Pagar;
}

public int getNr_Compromisso() {
	return nr_Compromisso;
}

public void setNr_Compromisso(int nr_Compromisso) {
	this.nr_Compromisso = nr_Compromisso;
}

public String getNr_Documento() {
	return nr_Documento;
}

public void setNr_Documento(String nr_Documento) {
	this.nr_Documento = nr_Documento;
}

public int getNr_Parcela() {
	return nr_Parcela;
}

public void setNr_Parcela(int nr_Parcela) {
	this.nr_Parcela = nr_Parcela;
}


public double getVl_Saldo() {
	return vl_Saldo;
}

public void setVl_Saldo(double vl_Saldo) {
	this.vl_Saldo = vl_Saldo;
}

public String getDm_Stamp() {
	return dm_Stamp;
}

public void setDm_Stamp(String dm_Stamp) {
	this.dm_Stamp = dm_Stamp;
}

public String getDt_stamp() {
	return dt_stamp;
}

public void setDt_stamp(String dt_stamp) {
	this.dt_stamp = dt_stamp;
}

public String getUsuario_Stamp() {
	return usuario_Stamp;
}

public void setUsuario_Stamp(String usuario_Stamp) {
	this.usuario_Stamp = usuario_Stamp;
}



}