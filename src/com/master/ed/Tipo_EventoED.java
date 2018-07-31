package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Tipo_EventoED extends MasterED {

  private Integer oid_Tipo_Evento;
  private String nm_Tipo_Evento;
  private String cd_Tipo_Evento;
  private String dm_Totalizado;
  private Integer oid_Historico;
  private String nm_Historico;
  private String cd_Historico;
  private String cd_Tipo_Documento;
  private String nm_Tipo_Evento_Debito;
  private String nm_Tipo_Evento_Credito;
  private String nm_Arquivo_Saida;
  private String cd_Conta_Credito;
  private String cd_Conta_Debito;
  private String dt_Inicial;
  private String dt_Final;
  private String dt_Lancamento;
  private String nm_Complemento_Historico;
  private double vl_Lancamento;
  private String nr_Lancamento;
  private long nr_Documento;
  private long OID_Unidade;
  private String CD_Unidade_Contabil;
  private String CD_Conta_Credito_Editada;
  private String CD_Conta_Debito_Editada;
  private String oid_Pessoa;
  private Integer oid_Conta;
  private Integer oid_Conta_Credito;
  private Integer oid_Centro_Custo;
  private Integer oid_Natureza_Operacao;

  public String getCd_Tipo_Evento() {
    return cd_Tipo_Evento;
  }
  public String getDm_Totalizado() {
    return dm_Totalizado;
  }
  public String getNm_Tipo_Evento() {
    return nm_Tipo_Evento;
  }
  public Integer getOid_Tipo_Evento() {
    return oid_Tipo_Evento;
  }
  public Integer getOid_Historico() {
    return oid_Historico;
  }
  public void setOid_Historico(Integer oid_Historico) {
    this.oid_Historico = oid_Historico;
  }
  public void setOid_Tipo_Evento(Integer oid_Tipo_Evento) {
    this.oid_Tipo_Evento = oid_Tipo_Evento;
  }
  public void setNm_Tipo_Evento(String nm_Tipo_Evento) {
    this.nm_Tipo_Evento = nm_Tipo_Evento;
  }
  public void setDm_Totalizado(String dm_Totalizado) {
    this.dm_Totalizado = dm_Totalizado;
  }
  public void setCd_Tipo_Evento(String cd_Tipo_Evento) {
    this.cd_Tipo_Evento = cd_Tipo_Evento;
  }
  public void setNm_Historico(String nm_Historico) {
    this.nm_Historico = nm_Historico;
  }
  public String getNm_Historico() {
    return nm_Historico;
  }
  public void setCd_Historico(String cd_Historico) {
    this.cd_Historico = cd_Historico;
  }
  public String getCd_Historico() {
    return cd_Historico;
  }
  public void setCd_Tipo_Documento(String cd_Tipo_Documento) {
    this.cd_Tipo_Documento = cd_Tipo_Documento;
  }
  public String getCd_Tipo_Documento() {
    return cd_Tipo_Documento;
  }
  public void setNm_Tipo_Evento_Debito(String nm_Tipo_Evento_Debito) {
    this.nm_Tipo_Evento_Debito = nm_Tipo_Evento_Debito;
  }
  public String getNm_Tipo_Evento_Debito() {
    return nm_Tipo_Evento_Debito;
  }
  public void setNm_Tipo_Evento_Credito(String nm_Tipo_Evento_Credito) {
    this.nm_Tipo_Evento_Credito = nm_Tipo_Evento_Credito;
  }
  public String getNm_Tipo_Evento_Credito() {
    return nm_Tipo_Evento_Credito;
  }
  public void setNm_Arquivo_Saida(String nm_Arquivo_Saida) {
    this.nm_Arquivo_Saida = nm_Arquivo_Saida;
  }
  public String getNm_Arquivo_Saida() {
    return nm_Arquivo_Saida;
  }
  public void setCd_Conta_Credito(String cd_Conta_Credito) {
    this.cd_Conta_Credito = cd_Conta_Credito;
  }
  public String getCd_Conta_Credito() {
    return cd_Conta_Credito;
  }
  public void setCd_Conta_Debito(String cd_Conta_Debito) {
    this.cd_Conta_Debito = cd_Conta_Debito;
  }
  public String getCd_Conta_Debito() {
    return cd_Conta_Debito;
  }
  public void setDt_Inicial(String dt_Inicial) {
    this.dt_Inicial = dt_Inicial;
  }
  public String getDt_Inicial() {
    return dt_Inicial;
  }
  public void setDt_Final(String dt_Final) {
    this.dt_Final = dt_Final;
  }
  public String getDt_Final() {
    return dt_Final;
  }
  public void setDt_Lancamento(String dt_Lancamento) {
    this.dt_Lancamento = dt_Lancamento;
  }
  public String getDt_Lancamento() {
    return dt_Lancamento;
  }
  public void setNm_Complemento_Historico(String nm_Complemento_Historico) {
    this.nm_Complemento_Historico = nm_Complemento_Historico;
  }
  public String getNm_Complemento_Historico() {
    return nm_Complemento_Historico;
  }
  public void setVl_Lancamento(double vl_Lancamento) {
    this.vl_Lancamento = vl_Lancamento;
  }
  public double getVl_Lancamento() {
    return vl_Lancamento;
  }
  public void setNr_Lancamento(String nr_Lancamento) {
    this.nr_Lancamento = nr_Lancamento;
  }
  public String getNr_Lancamento() {
    return nr_Lancamento;
  }
  public void setNr_Documento(long nr_Documento) {
    this.nr_Documento = nr_Documento;
  }
  public long getNr_Documento() {
    return nr_Documento;
  }
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  public void setCD_Unidade_Contabil(String CD_Unidade_Contabil) {
    this.CD_Unidade_Contabil = CD_Unidade_Contabil;
  }
  public String getCD_Unidade_Contabil() {
    return CD_Unidade_Contabil;
  }
  public void setCD_Conta_Credito_Editada(String CD_Conta_Credito_Editada) {
    this.CD_Conta_Credito_Editada = CD_Conta_Credito_Editada;
  }
  public String getCD_Conta_Credito_Editada() {
    return CD_Conta_Credito_Editada;
  }
  public void setCD_Conta_Debito_Editada(String CD_Conta_Debito_Editada) {
    this.CD_Conta_Debito_Editada = CD_Conta_Debito_Editada;
  }
  public String getCD_Conta_Debito_Editada() {
    return CD_Conta_Debito_Editada;
  }
  public void setOid_Conta(Integer oid_Conta) {
    this.oid_Conta = oid_Conta;
  }
  public Integer getOid_Conta() {
    return oid_Conta;
  }
  public void setOid_Conta_Credito(Integer oid_Conta_Credito) {
    this.oid_Conta_Credito = oid_Conta_Credito;
  }
  public Integer getOid_Conta_Credito() {
    return oid_Conta_Credito;
  }
  public void setOid_Pessoa(String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }
  public String getOid_Pessoa() {
    return oid_Pessoa;
  }
  public void setOid_Centro_Custo(Integer oid_Centro_Custo) {
    this.oid_Centro_Custo = oid_Centro_Custo;
  }
  public Integer getOid_Centro_Custo() {
    return oid_Centro_Custo;
  }
  public void setOid_Natureza_Operacao(Integer oid_Natureza_Operacao) {
    this.oid_Natureza_Operacao = oid_Natureza_Operacao;
  }
  public Integer getOid_Natureza_Operacao() {
    return oid_Natureza_Operacao;
  }

}