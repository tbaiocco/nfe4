package com.master.ed;

public class Grupo_EstoqueED extends MasterED implements java.io.Serializable{

  private String NM_Grupo_Estoque;
  private String CD_Grupo_Estoque;
  private long OID_Grupo_Estoque;

  public void setOID_Grupo_Estoque(long OID_Grupo_Estoque) {
    this.OID_Grupo_Estoque = OID_Grupo_Estoque;
  }
  public long getOID_Grupo_Estoque() {
    return OID_Grupo_Estoque;
  }
  public void setNM_Grupo_Estoque(String NM_Grupo_Estoque) {
    this.NM_Grupo_Estoque = NM_Grupo_Estoque;
  }
  public String getNM_Grupo_Estoque() {
    return NM_Grupo_Estoque;
  }
  public void setCD_Grupo_Estoque(String CD_Grupo_Estoque) {
    this.CD_Grupo_Estoque = CD_Grupo_Estoque;
  }
  public String getCD_Grupo_Estoque() {
    return CD_Grupo_Estoque;
  }

}