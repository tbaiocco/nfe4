package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */


public class Documento_Conta_CorrenteED extends MasterED {

  private Integer oid_Documento_Conta_Corrente;
  private String Nm_Serie;  
  private String tx_Descricao;
  private long Nr_Inicial;
  private long Nr_Final;
  private long Nr_Atual;
  private Integer oid_Tipo_Documento;
  private String nm_Tipo_Documento;
  private String cd_Tipo_Documento;
  private String nm_Ocorrencia;
  private String dm_Tipo_Ocorrencia;
  private String nm_Razao_Social;
  private String nr_CNPJ_CPF;
  private String oid_Conta_Corrente;
  private String dm_Padrao;

  public String getNm_Serie() {
    return Nm_Serie;
  }
  public Integer getOid_Documento_Conta_Corrente() {
    return oid_Documento_Conta_Corrente;
  }
  public Integer getOid_Tipo_Documento() {
    return oid_Tipo_Documento;
  }
  public String getoid_Conta_Corrente() {
    return oid_Conta_Corrente;
  }
  public String getTx_Descricao() {
    return tx_Descricao;
  }
  public long getNr_Inicial() {
    return Nr_Inicial;
  }
  public void setNr_Inicial(long Nr_Inicial) {
    this.Nr_Inicial = Nr_Inicial;
  }
  public void setTx_Descricao(String tx_Descricao) {
    this.tx_Descricao = tx_Descricao;
  }
  public void setoid_Conta_Corrente(String oid_Conta_Corrente) {
    this.oid_Conta_Corrente = oid_Conta_Corrente;
  }
  public void setOid_Tipo_Documento(Integer oid_Tipo_Documento) {
    this.oid_Tipo_Documento = oid_Tipo_Documento;
  }
  public void setOid_Documento_Conta_Corrente(Integer oid_Documento_Conta_Corrente) {
    this.oid_Documento_Conta_Corrente = oid_Documento_Conta_Corrente;
  }
  public void setNm_Serie(String Nm_Serie) {
    this.Nm_Serie = Nm_Serie;
  }
  public void setNm_Tipo_Documento(String nm_Tipo_Documento) {
    this.nm_Tipo_Documento = nm_Tipo_Documento;
  }
  public String getNm_Tipo_Documento() {
    return nm_Tipo_Documento;
  }
  public void setCd_Tipo_Documento(String cd_Tipo_Documento) {
    this.cd_Tipo_Documento = cd_Tipo_Documento;
  }
  public String getCd_Tipo_Documento() {
    return cd_Tipo_Documento;
  }
  public void setNm_Ocorrencia(String nm_Ocorrencia) {
    this.nm_Ocorrencia = nm_Ocorrencia;
  }
  public String getNm_Ocorrencia() {
    return nm_Ocorrencia;
  }
  public void setDm_Tipo_Ocorrencia(String dm_Tipo_Ocorrencia) {
    this.dm_Tipo_Ocorrencia = dm_Tipo_Ocorrencia;
  }
  public String getDm_Tipo_Ocorrencia() {
    return dm_Tipo_Ocorrencia;
  }
  public void setNm_Razao_Social(String nm_Razao_Social) {
    this.nm_Razao_Social = nm_Razao_Social;
  }
  public String getNm_Razao_Social() {
    return nm_Razao_Social;
  }
  public void setNr_CNPJ_CPF(String nr_CNPJ_CPF) {
    this.nr_CNPJ_CPF = nr_CNPJ_CPF;
  }

  public void setDm_Padrao (String dm_Padrao) {
    this.dm_Padrao = dm_Padrao;
  }

  public void setNr_Atual (long Nr_Atual) {
    this.Nr_Atual = Nr_Atual;
  }

  public void setNr_Final (long Nr_Final) {
    this.Nr_Final = Nr_Final;
  }

  public String getNr_CNPJ_CPF() {
    return nr_CNPJ_CPF;
  }

  public String getDm_Padrao () {
    return dm_Padrao;
  }

  public long getNr_Final () {
    return Nr_Final;
  }

  public long getNr_Atual () {
    return Nr_Atual;
  }

}
