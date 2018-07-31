package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Ocorrencia_BancariaED extends MasterED {

  private Integer oid_Ocorrencia_Bancaria;
  private String cd_Ocorrencia;
  private String tx_Descricao;
  private Double vl_Movimento;
  private Integer oid_Tipo_Instrucao;
  private String nm_Tipo_Instrucao;
  private String cd_Tipo_Instrucao;
  private String nm_Ocorrencia;
  private String dm_Tipo_Ocorrencia;
  private String nm_Razao_Social;
  private String nr_CNPJ_CPF;
  private String oid_Pessoa;

  public String getCd_Ocorrencia() {
    return cd_Ocorrencia;
  }
  public Integer getOid_Ocorrencia_Bancaria() {
    return oid_Ocorrencia_Bancaria;
  }
  public Integer getOid_Tipo_Instrucao() {
    return oid_Tipo_Instrucao;
  }
  public String getOid_Pessoa() {
    return oid_Pessoa;
  }
  public String getTx_Descricao() {
    return tx_Descricao;
  }
  public Double getVl_Movimento() {
    return vl_Movimento;
  }
  public void setVl_Movimento(Double vl_Movimento) {
    this.vl_Movimento = vl_Movimento;
  }
  public void setTx_Descricao(String tx_Descricao) {
    this.tx_Descricao = tx_Descricao;
  }
  public void setOid_Pessoa(String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }
  public void setOid_Tipo_Instrucao(Integer oid_Tipo_Instrucao) {
    this.oid_Tipo_Instrucao = oid_Tipo_Instrucao;
  }
  public void setOid_Ocorrencia_Bancaria(Integer oid_Ocorrencia_Bancaria) {
    this.oid_Ocorrencia_Bancaria = oid_Ocorrencia_Bancaria;
  }
  public void setCd_Ocorrencia(String cd_Ocorrencia) {
    this.cd_Ocorrencia = cd_Ocorrencia;
  }
  public void setNm_Tipo_Instrucao(String nm_Tipo_Instrucao) {
    this.nm_Tipo_Instrucao = nm_Tipo_Instrucao;
  }
  public String getNm_Tipo_Instrucao() {
    return nm_Tipo_Instrucao;
  }
  public void setCd_Tipo_Instrucao(String cd_Tipo_Instrucao) {
    this.cd_Tipo_Instrucao = cd_Tipo_Instrucao;
  }
  public String getCd_Tipo_Instrucao() {
    return cd_Tipo_Instrucao;
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
  public String getNr_CNPJ_CPF() {
    return nr_CNPJ_CPF;
  }

}