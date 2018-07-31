package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Movimento_CompromissoED extends CompromissoED{

  private Integer oid_Mov_Compromisso;
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
  private String NR_Compromissos;

  public Movimento_CompromissoED() {
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
  public Integer getOid_Mov_Compromisso() {
    return oid_Mov_Compromisso;
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

  public String getDm_Situacao () {
    return dm_Situacao;
  }

  public Double getVl_Estorno () {
    return vl_Estorno;
  }

  public String getNR_Compromissos () {
    return NR_Compromissos;
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
  public void setOid_Mov_Compromisso(Integer oid_Mov_Compromisso) {
    this.oid_Mov_Compromisso = oid_Mov_Compromisso;
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

  public void setVl_Estorno (Double vl_Estorno) {
    this.vl_Estorno = vl_Estorno;
  }

  public void setDm_Situacao (String dm_Situacao) {
    this.dm_Situacao = dm_Situacao;
  }

  public void setNR_Compromissos (String NR_Compromissos) {
    this.NR_Compromissos = NR_Compromissos;
  }

}
