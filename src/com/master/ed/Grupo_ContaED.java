package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Grupo_ContaED extends MasterED {

  private Integer oid_Grupo_Conta;
  private String nm_Grupo_Conta;
  private String DM_DRE;
  private String NM_DRE;

  public String getNm_Grupo_Conta() {
    return nm_Grupo_Conta;
  }
  public Integer getOid_Grupo_Conta() {
    return oid_Grupo_Conta;
  }

  public String getNM_DRE () {
    return NM_DRE;
  }

  public String getDM_DRE () {
    return DM_DRE;
  }

  public void setOid_Grupo_Conta(Integer oid_Grupo_Conta) {
    this.oid_Grupo_Conta = oid_Grupo_Conta;
  }
  public void setNm_Grupo_Conta(String nm_Grupo_Conta) {
    this.nm_Grupo_Conta = nm_Grupo_Conta;
  }

  public void setNM_DRE (String NM_DRE) {
    this.NM_DRE = NM_DRE;
  }

  public void setDM_DRE (String DM_DRE) {
    this.DM_DRE = DM_DRE;
  }

}