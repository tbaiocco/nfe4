package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Posto_CompromissoED extends CompromissoED{

  private String dt_Pagamento;
  private Double vl_Pagamento;
  private Double vl_Multa_Pagamento;
  private Double vl_Juros_Pagamento;
  private Double vl_Desconto;
  private Double vl_Outras_Despesas;
  private String tx_Observacao;
  private String dm_Tipo_Pagamento;
  private Integer nr_Lote_Posto;
  private Integer oid_Lote_Posto;
  private String oid_Posto_Compromisso;
  private Double vl_Saldo_Posto_Compromisso;
  private String DM_Situacao;
  private Double vl_Lote_Posto;
  private long OID_Modelo;
  private String DM_Contabiliza;
  private Double VL_Pedagio_Receber;
  private Double VL_Pedagio_Pagar;
  private Double VL_Juro_Pagamento;
  private long OID_Unidade;
  private boolean DM_Tem_Lancamento_Contabil;

  public Posto_CompromissoED() {
  }
  public String getDm_Tipo_Pagamento() {
    return dm_Tipo_Pagamento;
  }
  public String getDt_Pagamento() {
    return dt_Pagamento;
  }
  public Integer getNr_Lote_Posto() {
    return nr_Lote_Posto;
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
  public void setNr_Lote_Posto(Integer nr_Lote_Posto) {
    this.nr_Lote_Posto = nr_Lote_Posto;
  }
  public void setDt_Pagamento(String dt_Pagamento) {
    this.dt_Pagamento = dt_Pagamento;
  }
  public void setDm_Tipo_Pagamento(String dm_Tipo_Pagamento) {
    this.dm_Tipo_Pagamento = dm_Tipo_Pagamento;
  }
  public void setOid_Lote_Posto(Integer oid_Lote_Posto) {
    this.oid_Lote_Posto = oid_Lote_Posto;
  }
  public Integer getOid_Lote_Posto() {
    return oid_Lote_Posto;
  }
  public void setOid_Posto_Compromisso(String oid_Posto_Compromisso) {
    this.oid_Posto_Compromisso = oid_Posto_Compromisso;
  }
  public String getOid_Posto_Compromisso() {
    return oid_Posto_Compromisso;
  }
  public void setVl_Saldo_Posto_Compromisso(Double vl_Saldo_Posto_Compromisso) {
    this.vl_Saldo_Posto_Compromisso = vl_Saldo_Posto_Compromisso;
  }
  public Double getVl_Saldo_Posto_Compromisso() {
    return vl_Saldo_Posto_Compromisso;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public void setVl_Lote_Posto(Double vl_Lote_Posto) {
    this.vl_Lote_Posto = vl_Lote_Posto;
  }
  public Double getVl_Lote_Posto() {
    return vl_Lote_Posto;
  }
  public void setOID_Modelo(long OID_Modelo) {
    this.OID_Modelo = OID_Modelo;
  }
  public long getOID_Modelo() {
    return OID_Modelo;
  }
  public void setDM_Contabiliza(String DM_Contabiliza) {
    this.DM_Contabiliza = DM_Contabiliza;
  }
  public String getDM_Contabiliza() {
    return DM_Contabiliza;
  }
  public void setVL_Pedagio_Receber(Double VL_Pedagio_Receber) {
    this.VL_Pedagio_Receber = VL_Pedagio_Receber;
  }
  public Double getVL_Pedagio_Receber() {
    return VL_Pedagio_Receber;
  }
  public void setVL_Pedagio_Pagar(Double VL_Pedagio_Pagar) {
    this.VL_Pedagio_Pagar = VL_Pedagio_Pagar;
  }
  public Double getVL_Pedagio_Pagar() {
    return VL_Pedagio_Pagar;
  }
  public void setVL_Juro_Pagamento(Double VL_Juro_Pagamento) {
    this.VL_Juro_Pagamento = VL_Juro_Pagamento;
  }
  public Double getVL_Juro_Pagamento() {
    return VL_Juro_Pagamento;
  }
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  public void setDM_Tem_Lancamento_Contabil(boolean DM_Tem_Lancamento_Contabil) {
    this.DM_Tem_Lancamento_Contabil = DM_Tem_Lancamento_Contabil;
  }
  public boolean isDM_Tem_Lancamento_Contabil() {
    return DM_Tem_Lancamento_Contabil;
  }



}
