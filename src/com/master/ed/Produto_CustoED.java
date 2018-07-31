package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Produto_CustoED extends MasterED {

  private Integer oid_Produto_Custo;
  private String nm_Produto_Custo;
  private String cd_Produto_Custo;
  private Integer oid_Unidade_Grupo;
  private String nm_Unidade_Grupo;
  private String OID_Unidade_Negocio;


  public String getCd_Produto_Custo() {
    return cd_Produto_Custo;
  }
  public String getNm_Produto_Custo() {
    return nm_Produto_Custo;
  }
  public Integer getOid_Produto_Custo() {
    return oid_Produto_Custo;
  }
  public Integer getOid_Unidade_Grupo() {
    return oid_Unidade_Grupo;
  }
  public void setOid_Unidade_Grupo(Integer oid_Unidade_Grupo) {
    this.oid_Unidade_Grupo = oid_Unidade_Grupo;
  }
  public void setOid_Produto_Custo(Integer oid_Produto_Custo) {
    this.oid_Produto_Custo = oid_Produto_Custo;
  }
  public void setNm_Produto_Custo(String nm_Produto_Custo) {
    this.nm_Produto_Custo = nm_Produto_Custo;
  }
  public void setCd_Produto_Custo(String cd_Produto_Custo) {
    this.cd_Produto_Custo = cd_Produto_Custo;
  }
  public void setNm_Unidade_Grupo(String nm_Unidade_Grupo) {
    this.nm_Unidade_Grupo = nm_Unidade_Grupo;
  }
  public String getNm_Unidade_Grupo() {
    return nm_Unidade_Grupo;
  }
  public void setOID_Unidade_Negocio(String OID_Unidade_Negocio) {
    this.OID_Unidade_Negocio = OID_Unidade_Negocio;
  }
  public String getOID_Unidade_Negocio() {
    return OID_Unidade_Negocio;
  }

}
