package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Tipo_ImpostoED extends MasterED {

  private Integer oid_Tipo_Imposto;
  private String nm_Tipo_Imposto;
  private String cd_Tipo_Imposto;
  private String dm_Tipo_Imposto;
  private String dm_Aplicacao;
  private String dm_Origem;
  private String dm_Recolhimento;

  public String getCd_Tipo_Imposto() {
    return cd_Tipo_Imposto;
  }
  public String getNm_Tipo_Imposto() {
    return nm_Tipo_Imposto;
  }
  public Integer getOid_Tipo_Imposto() {
    return oid_Tipo_Imposto;
  }
  public void setOid_Tipo_Imposto(Integer oid_Tipo_Imposto) {
    this.oid_Tipo_Imposto = oid_Tipo_Imposto;
  }
  public void setNm_Tipo_Imposto(String nm_Tipo_Imposto) {
    this.nm_Tipo_Imposto = nm_Tipo_Imposto;
  }
  public void setCd_Tipo_Imposto(String cd_Tipo_Imposto) {
    this.cd_Tipo_Imposto = cd_Tipo_Imposto;
  }
  public void setDm_Tipo_Imposto(String dm_Tipo_Imposto) {
    this.dm_Tipo_Imposto = dm_Tipo_Imposto;
  }
  public String getDm_Tipo_Imposto() {
    return dm_Tipo_Imposto;
  }
public String getDm_Aplicacao() {
	return dm_Aplicacao;
}
public void setDm_Aplicacao(String dm_Aplicacao) {
	this.dm_Aplicacao = dm_Aplicacao;
}
public String getDm_Origem() {
	return dm_Origem;
}
public void setDm_Origem(String dm_Origem) {
	this.dm_Origem = dm_Origem;
}
public String getDm_Recolhimento() {
	return dm_Recolhimento;
}
public void setDm_Recolhimento(String dm_Recolhimento) {
	this.dm_Recolhimento = dm_Recolhimento;
}

}