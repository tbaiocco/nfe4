package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Movimento_ProcessoED extends MasterED{

  private String oid_Processo;
  private String oid_Processo_Destino;

  private Double VL_Lancamento;
  private String NM_Complemento_Historico;
  private Integer NR_Lote_Pagamento;
  private String oid_Pessoa;
  private Long oid_Unidade;
  private String NM_Razao_Social;
  private String CD_Unidade;
  private String NM_Fantasia;
  private String NR_Conta;
  private String NM_Favorecido;
  private String NR_Documento;
  private String CD_Tipo_Documento;
  private String NM_Tipo_Documento;
  private Integer OID_Tipo_Documento;
  private String NM_Razao_Social_Banco;
  private Integer NR_Compromisso;
  private String DT_Inicial;
  private String DT_Final;
  private Integer oid_Historico;
  private String DM_Debito_Credito;
  private String DM_Tipo_Lancamento;
  private String DT_Movimento_Processo;
  private String NM_Historico;
  private long oid_Movimento_Processo;
  private String CD_Historico;
  private long oid_modelo_Solicitacao;
  private String CD_Processo;
  private double VL_Saldo_Inicial;
  private double VL_Saldo_Final;
  private String DM_Saldo_Inicial;
  private String DM_Situacao;
  private long oid_Lote_Pagamento;
  private String NM_Moeda;
  private String CD_Moeda;
  private String DM_Relatorio;

  public Movimento_ProcessoED() {
  }
  public Integer getNR_Lote_Pagamento() {
    return NR_Lote_Pagamento;
  }
  public String getNR_Documento() {
    return NR_Documento;
  }
  public long getOid_Movimento_Processo() {
    return oid_Movimento_Processo;
  }
  public String getOid_Pessoa() {
    return oid_Pessoa;
  }
  public Long getOid_Unidade() {
    return oid_Unidade;
  }
  public String getNM_Complemento_Historico() {
    return NM_Complemento_Historico;
  }
  public Double getVL_Lancamento() {
    return VL_Lancamento;
  }
  public void setVL_Lancamento(Double VL_Lancamento) {
    this.VL_Lancamento = VL_Lancamento;
  }
  public void setNM_Complemento_Historico(String NM_Complemento_Historico) {
    this.NM_Complemento_Historico = NM_Complemento_Historico;
  }
  public void setOid_Unidade(Long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }
  public void setOid_Pessoa(String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }
  public void setOid_Movimento_Processo(long oid_Movimento_Processo) {
    this.oid_Movimento_Processo = oid_Movimento_Processo;
  }
  public void setNR_Documento(String NR_Documento) {
    this.NR_Documento = NR_Documento;
  }
  public void setNR_Lote_Pagamento(Integer NR_Lote_Pagamento) {
    this.NR_Lote_Pagamento = NR_Lote_Pagamento;
  }
  public void setNM_Razao_Social(String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }
  public String getNM_Razao_Social() {
    return NM_Razao_Social;
  }
  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
  }
  public void setNM_Fantasia(String NM_Fantasia) {
    this.NM_Fantasia = NM_Fantasia;
  }
  public String getNM_Fantasia() {
    return NM_Fantasia;
  }
  public void setNR_Conta(String NR_Conta) {
    this.NR_Conta = NR_Conta;
  }
  public String getNR_Conta() {
    return NR_Conta;
  }
  public void setNM_Favorecido(String NM_Favorecido) {
    this.NM_Favorecido = NM_Favorecido;
  }
  public String getNM_Favorecido() {
    return NM_Favorecido;
  }
  public void setCD_Tipo_Documento(String CD_Tipo_Documento) {
    this.CD_Tipo_Documento = CD_Tipo_Documento;
  }
  public String getCD_Tipo_Documento() {
    return CD_Tipo_Documento;
  }
  public void setNM_Tipo_Documento(String NM_Tipo_Documento) {
    this.NM_Tipo_Documento = NM_Tipo_Documento;
  }
  public String getNM_Tipo_Documento() {
    return NM_Tipo_Documento;
  }
  public void setOID_Tipo_Documento(Integer OID_Tipo_Documento) {
    this.OID_Tipo_Documento = OID_Tipo_Documento;
  }
  public Integer getOID_Tipo_Documento() {
    return OID_Tipo_Documento;
  }
  public void setNM_Razao_Social_Banco(String NM_Razao_Social_Banco) {
    this.NM_Razao_Social_Banco = NM_Razao_Social_Banco;
  }
  public String getNM_Razao_Social_Banco() {
    return NM_Razao_Social_Banco;
  }
  public void setNR_Compromisso(Integer NR_Compromisso) {
    this.NR_Compromisso = NR_Compromisso;
  }
  public Integer getNR_Compromisso() {
    return NR_Compromisso;
  }
  public void setDT_Inicial(String DT_Inicial) {
    this.DT_Inicial = DT_Inicial;
  }
  public String getDT_Inicial() {
    return DT_Inicial;
  }
  public void setDT_Final(String DT_Final) {
    this.DT_Final = DT_Final;
  }
  public String getDT_Final() {
    return DT_Final;
  }
  public void setOid_Historico(Integer oid_Historico) {
    this.oid_Historico = oid_Historico;
  }
  public Integer getOid_Historico() {
    return oid_Historico;
  }
  public void setDM_Debito_Credito(String DM_Debito_Credito) {
    this.DM_Debito_Credito = DM_Debito_Credito;
  }
  public String getDM_Debito_Credito() {
    return DM_Debito_Credito;
  }
  public void setDM_Tipo_Lancamento(String DM_Tipo_Lancamento) {
    this.DM_Tipo_Lancamento = DM_Tipo_Lancamento;
  }
  public String getDM_Tipo_Lancamento() {
    return DM_Tipo_Lancamento;
  }
  public void setDT_Movimento_Processo(String DT_Movimento_Processo) {
    this.DT_Movimento_Processo = DT_Movimento_Processo;
  }
  public String getDT_Movimento_Processo() {
    return DT_Movimento_Processo;
  }
  public void setNM_Historico(String NM_Historico) {
    this.NM_Historico = NM_Historico;
  }
  public String getNM_Historico() {
    return NM_Historico;
  }
  public void setCD_Historico(String CD_Historico) {
    this.CD_Historico = CD_Historico;
  }
  public String getCD_Historico() {
    return CD_Historico;
  }
  public void setOid_modelo_Solicitacao(long oid_modelo_Solicitacao) {
    this.oid_modelo_Solicitacao = oid_modelo_Solicitacao;
  }
  public long getOid_modelo_Solicitacao() {
    return oid_modelo_Solicitacao;
  }

  public String getOid_Processo() {
    return oid_Processo;
  }
  public void setOid_Processo(String oid_Processo) {
    this.oid_Processo = oid_Processo;
  }
  public void setCD_Processo(String CD_Processo) {
    this.CD_Processo = CD_Processo;
  }
  public String getCD_Processo() {
    return CD_Processo;
  }
  public void setVL_Saldo_Inicial(double VL_Saldo_Inicial) {
    this.VL_Saldo_Inicial = VL_Saldo_Inicial;
  }
  public double getVL_Saldo_Inicial() {
    return VL_Saldo_Inicial;
  }
  public void setVL_Saldo_Final(double VL_Saldo_Final) {
    this.VL_Saldo_Final = VL_Saldo_Final;
  }
  public double getVL_Saldo_Final() {
    return VL_Saldo_Final;
  }
  public void setDM_Saldo_Inicial(String DM_Saldo_Inicial) {
    this.DM_Saldo_Inicial = DM_Saldo_Inicial;
  }
  public String getDM_Saldo_Inicial() {
    return DM_Saldo_Inicial;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public void setOid_Lote_Pagamento(long oid_Lote_Pagamento) {
    this.oid_Lote_Pagamento = oid_Lote_Pagamento;
  }

  public void setOid_Processo_Destino (String oid_Processo_Destino) {
    this.oid_Processo_Destino = oid_Processo_Destino;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public void setCD_Moeda (String CD_Moeda) {
    this.CD_Moeda = CD_Moeda;
  }

  public void setNM_Moeda (String NM_Moeda) {
    this.NM_Moeda = NM_Moeda;
  }

  public long getOid_Lote_Pagamento() {
    return oid_Lote_Pagamento;
  }

  public String getOid_Processo_Destino () {
    return oid_Processo_Destino;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public String getCD_Moeda () {
    return CD_Moeda;
  }

  public String getNM_Moeda () {
    return NM_Moeda;
  }
}
