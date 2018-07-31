package com.master.ed;

public class ReferenciaED
    extends MasterED {

  private long OID_Referencia;
  private long OID_Produto;
  private String CD_Referencia;
  private String NM_Referencia;
  private String DM_Tipo_Produto;
  private String CD_Produto;
  private String NM_Produto;


  public ReferenciaED () {
  }

  public ReferenciaED (int OID_Referencia) {
    this.OID_Referencia = OID_Referencia;
  }

  public ReferenciaED (long oid_Produto , String CD_Referencia , String NM_Referencia , String DM_Tipo_Produto) {
    this.OID_Produto = oid_Produto;
    this.CD_Referencia = CD_Referencia;
    this.NM_Referencia = NM_Referencia;
    this.DM_Tipo_Produto = DM_Tipo_Produto;
  }

  public String getNM_Referencia () {
    return NM_Referencia;
  }

  public void setNM_Referencia (String referencia) {
    NM_Referencia = referencia;
  }

  public void setOID_Referencia (long OID_Referencia) {
    this.OID_Referencia = OID_Referencia;
  }

  public long getOID_Referencia () {
    return OID_Referencia;
  }

  public void setCD_Produto (String CD_Produto) {
    this.CD_Produto = CD_Produto;
  }

  public String getCD_Produto () {
    return CD_Produto;
  }

  public void setCD_Referencia (String CD_Referencia) {
    this.CD_Referencia = CD_Referencia;
  }

  public String getCD_Referencia () {
    return CD_Referencia;
  }

  public void setOID_Produto (long OID_Produto) {
    this.OID_Produto = OID_Produto;
  }

  public long getOID_Produto () {
    return OID_Produto;
  }

  public void setNM_Produto (String NM_Produto) {
    this.NM_Produto = NM_Produto;
  }

  public String getNM_Produto () {
    return NM_Produto;
  }

  public void setDM_Tipo_Produto (String DM_Tipo_Produto) {
    this.DM_Tipo_Produto = DM_Tipo_Produto;
  }

  public String getDM_Tipo_Produto () {
    return DM_Tipo_Produto;
  }

}