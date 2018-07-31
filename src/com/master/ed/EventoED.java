package com.master.ed;

/**
 * <p>Title: Evento Contábil</p>
 * <p>Description: Contém todos as contas contabeis para cada debito e credito</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Delta Guia </p>
 * @author Claudia Galmarini Welter
 * @version 1.0
 */
public class EventoED extends MasterED{

  private long oid_evento_contabil;
  private String nm_evento_contabil;
  private long oid_conta;
  private String dm_tipo_contabilizacao;
  private String cd_valor;
  private String dm_acumula;
  private long oid_sugestao_contabil;
  private long oid_Historico;
  private String nm_historico;
  private String cd_historico;


  public void setCd_valor(String cd_valor) {
    this.cd_valor = cd_valor;
  }
  public void setDm_acumula(String dm_acumula) {
    this.dm_acumula = dm_acumula;
  }
  public void setDm_tipo_contabilizacao(String dm_tipo_contabilizacao) {
    this.dm_tipo_contabilizacao = dm_tipo_contabilizacao;
  }
  public void setNm_evento_contabil(String nm_evento_contabil) {
    this.nm_evento_contabil = nm_evento_contabil;
  }
  public void setOid_conta(long oid_conta) {
    this.oid_conta = oid_conta;
  }
  public void setOid_evento_contabil(long oid_evento_contabil) {
    this.oid_evento_contabil = oid_evento_contabil;
  }
  public void setOid_Historico(long oid_Historico) {
    this.oid_Historico = oid_Historico;
  }
  public void setOid_sugestao_contabil(long oid_sugestao_contabil) {
    this.oid_sugestao_contabil = oid_sugestao_contabil;
  }
  public String getCd_valor() {
    return cd_valor;
  }
  public String getDm_acumula() {
    return dm_acumula;
  }
  public String getNm_evento_contabil() {
    return nm_evento_contabil;
  }
  public String getDm_tipo_contabilizacao() {
    return dm_tipo_contabilizacao;
  }
  public long getOid_conta() {
    return oid_conta;
  }
  public long getOid_evento_contabil() {
    return oid_evento_contabil;
  }
  public long getOid_Historico() {
    return oid_Historico;
  }
  public long getOid_sugestao_contabil() {
    return oid_sugestao_contabil;
  }
  public void setNm_historico(String nm_historico) {
    this.nm_historico = nm_historico;
  }
  public String getNm_historico() {
    return nm_historico;
  }
  public void setCd_historico(String cd_historico) {
    this.cd_historico = cd_historico;
  }
  public String getCd_historico() {
    return cd_historico;
  }

}