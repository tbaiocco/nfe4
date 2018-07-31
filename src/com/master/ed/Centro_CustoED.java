package com.master.ed;

public class Centro_CustoED
    extends MasterED {

  public Centro_CustoED () {
    super ();
  }

  public Centro_CustoED (Integer custo) {
    oid_Centro_Custo = custo;
  }

  private Integer oid_Centro_Custo;
  private String nm_Centro_Custo;
  private String cd_Centro_Custo;
  private String DM_Aplicacao;

  public String getNm_Centro_Custo () {
    return nm_Centro_Custo;
  }

  public Integer getOid_Centro_Custo () {
    return oid_Centro_Custo;
  }

  public void setOid_Centro_Custo (Integer oid_Centro_Custo) {
    this.oid_Centro_Custo = oid_Centro_Custo;
  }

  public void setNm_Centro_Custo (String nm_Centro_Custo) {
    this.nm_Centro_Custo = nm_Centro_Custo;
  }

  public void setCd_Centro_Custo (String cd_Centro_Custo) {
    this.cd_Centro_Custo = cd_Centro_Custo;
  }

  public void setDM_Aplicacao (String DM_Aplicacao) {
    this.DM_Aplicacao = DM_Aplicacao;
  }

  public String getCd_Centro_Custo () {
    return cd_Centro_Custo;
  }

  public String getDM_Aplicacao () {
    return DM_Aplicacao;
  }
}