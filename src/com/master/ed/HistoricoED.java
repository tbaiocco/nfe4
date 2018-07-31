package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class HistoricoED extends MasterED {

  private Integer oid_Historico;
  private String cd_Historico;
  private String nm_Historico;
  private String dm_Lanca_Fornecedor;
  private String dm_Lanca_Data;
  private String dm_Lanca_Complemento;

  public String getCd_Historico() {
    return cd_Historico;
  }
  public String getDm_Lanca_Complemento() {
    return dm_Lanca_Complemento;
  }
  public String getDm_Lanca_Data() {
    return dm_Lanca_Data;
  }
  public String getDm_Lanca_Fornecedor() {
    return dm_Lanca_Fornecedor;
  }
  public String getNm_Historico() {
    return nm_Historico;
  }
  public Integer getOid_Historico() {
    return oid_Historico;
  }
  public void setOid_Historico(Integer oid_Historico) {
    this.oid_Historico = oid_Historico;
  }
  public void setNm_Historico(String nm_Historico) {
    this.nm_Historico = nm_Historico;
  }
  public void setDm_Lanca_Fornecedor(String dm_Lanca_Fornecedor) {
    this.dm_Lanca_Fornecedor = dm_Lanca_Fornecedor;
  }
  public void setDm_Lanca_Data(String dm_Lanca_Data) {
    this.dm_Lanca_Data = dm_Lanca_Data;
  }
  public void setDm_Lanca_Complemento(String dm_Lanca_Complemento) {
    this.dm_Lanca_Complemento = dm_Lanca_Complemento;
  }
  public void setCd_Historico(String cd_Historico) {
    this.cd_Historico = cd_Historico;
  }

}