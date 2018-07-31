package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Movimento_ChequeED {

  private Integer oid_Mov_Cheque;
  private int oid_Cheque;
  private String dt_Pagamento;
  private Double vl_Pagamento;
  private Double vl_Multa_Pagamento;
  private Double vl_Juros_Pagamento;
  private Double vl_Desconto;
  private Double vl_Estorno;
  private Double vl_Outras_Despesas;
  private String tx_Observacao;
  private String dm_Tipo_Pagamento;
  private String dm_Situacao;
  private Integer nr_Lote_Pagamento;
  private Integer oid_Lote_Pagamento;
  private String DT_Stamp;
  private String usuario_Stamp;
  private String DM_Stamp;
  private double vl_Saldo;
  private int NR_Cheque;
  private int NR_Parcela;
  private double vl_Cheque;

  public Movimento_ChequeED() {
  }
  public String getDm_Tipo_Pagamento() {
    return dm_Tipo_Pagamento;
  }
  public String getDt_Pagamento() {
    return dt_Pagamento;
  }
  public Integer getNr_Lote_Pagamento() {
    return nr_Lote_Pagamento;
  }
  public Integer getOid_Mov_Cheque() {
    return oid_Mov_Cheque;
  }
  public String getTx_Observacao() {
    return tx_Observacao;
  }
  public Double getVl_Desconto() {
    return vl_Desconto;
  }
  public Double getVl_Juros_Pagamento() {
    return vl_Juros_Pagamento;
  }
  public Double getVl_Multa_Pagamento() {
    return vl_Multa_Pagamento;
  }
  public Double getVl_Outras_Despesas() {
    return vl_Outras_Despesas;
  }
  public Double getVl_Pagamento() {
    return vl_Pagamento;
  }

  public Integer getOid_Lote_Pagamento () {
    return oid_Lote_Pagamento;
  }

  public String getDm_Situacao () {
    return dm_Situacao;
  }

  public Double getVl_Estorno () {
    return vl_Estorno;
  }

  public int getOid_Cheque () {

    return oid_Cheque;
  }

  public String getUsuario_Stamp () {
    return usuario_Stamp;
  }

  public double getVl_Saldo () {
    return vl_Saldo;
  }

  public double getVl_Cheque () {

    return vl_Cheque;
  }

  public int getNR_Parcela () {
    return NR_Parcela;
  }

  public int getNR_Cheque () {
    return NR_Cheque;
  }

  public String getDM_Stamp () {
    return DM_Stamp;
  }

  public String getDT_Stamp () {
    return DT_Stamp;
  }

  public void setVl_Pagamento(Double vl_Pagamento) {
    this.vl_Pagamento = vl_Pagamento;
  }
  public void setVl_Outras_Despesas(Double vl_Outras_Despesas) {
    this.vl_Outras_Despesas = vl_Outras_Despesas;
  }
  public void setVl_Multa_Pagamento(Double vl_Multa_Pagamento) {
    this.vl_Multa_Pagamento = vl_Multa_Pagamento;
  }
  public void setVl_Juros_Pagamento(Double vl_Juros_Pagamento) {
    this.vl_Juros_Pagamento = vl_Juros_Pagamento;
  }
  public void setVl_Desconto(Double vl_Desconto) {
    this.vl_Desconto = vl_Desconto;
  }
  public void setTx_Observacao(String tx_Observacao) {
    this.tx_Observacao = tx_Observacao;
  }
  public void setOid_Mov_Cheque(Integer oid_Mov_Cheque) {
    this.oid_Mov_Cheque = oid_Mov_Cheque;
  }
  public void setNr_Lote_Pagamento(Integer nr_Lote_Pagamento) {
    this.nr_Lote_Pagamento = nr_Lote_Pagamento;
  }
  public void setDt_Pagamento(String dt_Pagamento) {
    this.dt_Pagamento = dt_Pagamento;
  }
  public void setDm_Tipo_Pagamento(String dm_Tipo_Pagamento) {
    this.dm_Tipo_Pagamento = dm_Tipo_Pagamento;
  }

  public void setOid_Lote_Pagamento (Integer oid_Lote_Pagamento) {
    this.oid_Lote_Pagamento = oid_Lote_Pagamento;
  }

  public void setVl_Estorno (Double vl_Estorno) {
    this.vl_Estorno = vl_Estorno;
  }

  public void setDm_Situacao (String dm_Situacao) {
    this.dm_Situacao = dm_Situacao;
  }

  public void setOid_Cheque (int oid_Cheque) {

    this.oid_Cheque = oid_Cheque;
  }

  public void setUsuario_Stamp (String usuario_Stamp) {
    this.usuario_Stamp = usuario_Stamp;
  }

  public void setVl_Saldo (double vl_Saldo) {
    this.vl_Saldo = vl_Saldo;
  }

  public void setVl_Cheque (double vl_Cheque) {

    this.vl_Cheque = vl_Cheque;
  }

  public void setNR_Parcela (int NR_Parcela) {
    this.NR_Parcela = NR_Parcela;
  }

  public void setNR_Cheque (int NR_Cheque) {
    this.NR_Cheque = NR_Cheque;
  }

  public void setDM_Stamp (String DM_Stamp) {
    this.DM_Stamp = DM_Stamp;
  }

  public void setDT_Stamp (String DT_Stamp) {
    this.DT_Stamp = DT_Stamp;
  }

}
