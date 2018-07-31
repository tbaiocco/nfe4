package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Unidade_NegocioED extends MasterED {

  private Integer oid_Unidade_Negocio;
  private String nm_Unidade_Negocio;
  private String OID_Unidade;
  public void setOID_Unidade(String OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public String getOID_Unidade() {
    return OID_Unidade;
  }


  public String getNm_Unidade_Negocio() {
    return nm_Unidade_Negocio;
  }
  public Integer getOid_Unidade_Negocio() {
    return oid_Unidade_Negocio;
  }
  public void setOid_Unidade_Negocio(Integer oid_Unidade_Negocio) {
    this.oid_Unidade_Negocio = oid_Unidade_Negocio;
  }
  public void setNm_Unidade_Negocio(String nm_Unidade_Negocio) {
    this.nm_Unidade_Negocio = nm_Unidade_Negocio;
  }

}
