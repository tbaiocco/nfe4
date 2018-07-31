package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ImpostoPesquisaED extends ImpostoED {

  public ImpostoPesquisaED() {
  }
  private String dt_Pgto_Inicial;
  private String dt_Pgto_Final;
  private String oid_Pessoa;
  private String nr_CNPJ_CPF;
  private String nm_Razao_Social;
  private String nm_Tipo_Imposto;
public String getDt_Pgto_Final() {
	return dt_Pgto_Final;
}
public void setDt_Pgto_Final(String dt_Pgto_Final) {
	this.dt_Pgto_Final = dt_Pgto_Final;
}
public String getDt_Pgto_Inicial() {
	return dt_Pgto_Inicial;
}
public void setDt_Pgto_Inicial(String dt_Pgto_Inicial) {
	this.dt_Pgto_Inicial = dt_Pgto_Inicial;
}
public String getNm_Razao_Social() {
	return nm_Razao_Social;
}
public void setNm_Razao_Social(String nm_Razao_Social) {
	this.nm_Razao_Social = nm_Razao_Social;
}
public String getNm_Tipo_Imposto() {
	return nm_Tipo_Imposto;
}
public void setNm_Tipo_Imposto(String nm_Tipo_Imposto) {
	this.nm_Tipo_Imposto = nm_Tipo_Imposto;
}
public String getNr_CNPJ_CPF() {
	return nr_CNPJ_CPF;
}
public void setNr_CNPJ_CPF(String nr_CNPJ_CPF) {
	this.nr_CNPJ_CPF = nr_CNPJ_CPF;
}
public String getOid_Pessoa() {
	return oid_Pessoa;
}
public void setOid_Pessoa(String oid_Pessoa) {
	this.oid_Pessoa = oid_Pessoa;
}

}