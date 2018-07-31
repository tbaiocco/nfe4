package com.master.ed;

public class Lote_NFED extends MasterED {

       private long oid_Unidade;
       private long oid_Lote;
       private String oid_Pessoa;

       private double vl_Lote;
       private double vl_Total_Frete;
       private long nr_Volumes;

       private String nr_Lote;
       private String dt_Cadastro;
       private String usuario_stamp;
       private String dt_Stamp;

  public String getDt_Cadastro() {
    return dt_Cadastro;
  }
  public String getNr_Lote() {
    return nr_Lote;
  }
  public long getNr_Volumes() {
    return nr_Volumes;
  }
  public long getOid_Lote() {
    return oid_Lote;
  }
  public String getOid_Pessoa() {
    return oid_Pessoa;
  }
  public long getOid_Unidade() {
    return oid_Unidade;
  }
  public String getUsuario_stamp() {
    return usuario_stamp;
  }
  public double getVl_Lote() {
    return vl_Lote;
  }
  public double getVl_Total_Frete() {
    return vl_Total_Frete;
  }
  public void setDt_Cadastro(String dt_Cadastro) {
    this.dt_Cadastro = dt_Cadastro;
  }
  public void setNr_Lote(String nr_Lote) {
    this.nr_Lote = nr_Lote;
  }
  public void setNr_Volumes(long nr_Volumes) {
    this.nr_Volumes = nr_Volumes;
  }
  public void setOid_Lote(long oid_Lote) {
    this.oid_Lote = oid_Lote;
  }
  public void setOid_Pessoa(String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }
  public void setOid_Unidade(long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }
  public void setUsuario_stamp(String usuario_stamp) {
    this.usuario_stamp = usuario_stamp;
  }
  public void setVl_Lote(double vl_Lote) {
    this.vl_Lote = vl_Lote;
  }
  public void setVl_Total_Frete(double vl_Total_Frete) {
    this.vl_Total_Frete = vl_Total_Frete;
  }
  public String getDt_Stamp() {
    return dt_Stamp;
  }
  public void setDt_Stamp(String dt_Stamp) {
    this.dt_Stamp = dt_Stamp;
  }


}