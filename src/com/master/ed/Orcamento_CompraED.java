package com.master.ed;


public class Orcamento_CompraED
    extends MasterED {

  private long oid_Orcamento_Compra;
  private long oid_Solicitacao_Compra;
  private String oid_Orcamento_Conta;
  private String cd_Conta;
  private String nm_Conta;
  private double vl_Compra;
  private long oid_Pedido_compra;
  private long oid_Conta;
  private String dm_Orcado;
  private double vl_Orcado;
  private double vl_Relatorio;
  public String getoid_Orcamento_Conta () {
    return oid_Orcamento_Conta;
  }

  public void setoid_Orcamento_Conta (String oid_Orcamento_Conta) {
    this.oid_Orcamento_Conta = oid_Orcamento_Conta;
  }

  public long getoid_Solicitacao_Compra () {
    return oid_Solicitacao_Compra;
  }

  public void setoid_Solicitacao_Compra (long oid_Solicitacao_Compra) {
    this.oid_Solicitacao_Compra = oid_Solicitacao_Compra;
  }

  public long getoid_Orcamento_Compra () {
    return oid_Orcamento_Compra;
  }

  public void setoid_Orcamento_Compra (long oid_Orcamento_Compra) {
    this.oid_Orcamento_Compra = oid_Orcamento_Compra;
  }

  public double getvl_Compra () {
    return vl_Compra;
  }

  public void setvl_Compra (double vl_Compra) {
    this.vl_Compra = vl_Compra;
  }

  public String getCd_Conta () {
    return cd_Conta;
  }

  public void setCd_Conta (String cd_Conta) {
    this.cd_Conta = cd_Conta;
  }

  public String getNm_Conta () {
    return nm_Conta;
  }

  public void setNm_Conta (String nm_Conta) {
    this.nm_Conta = nm_Conta;
  }

  public long getOid_Pedido_compra () {
    return oid_Pedido_compra;
  }

  public void setOid_Pedido_compra (long oid_Pedido_compra) {
    this.oid_Pedido_compra = oid_Pedido_compra;
  }

  public long getOid_Conta () {
    return oid_Conta;
  }

  public String getDm_Orcado () {
    return dm_Orcado;
  }

  public double getVl_Orcado () {
    return vl_Orcado;
  }

  public void setOid_Conta (long oid_Conta) {
    this.oid_Conta = oid_Conta;
  }

  public void setDm_Orcado (String dm_Orcado) {
    this.dm_Orcado = dm_Orcado;
  }

  public void setVl_Orcado (double vl_Orcado) {
    this.vl_Orcado = vl_Orcado;
  }

public double getVl_Compra() {
	return vl_Compra;
}
public void setVl_Compra(double vl_Compra) {
	this.vl_Compra = vl_Compra;
}

}