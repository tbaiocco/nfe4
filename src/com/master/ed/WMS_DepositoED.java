package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class WMS_DepositoED extends MasterED {

  private Integer oid_Deposito;
  private String nm_Deposito;
  private String NM_Senha;
  private String DM_Tipo;
  private Integer oid_Unidade;
  private String CD_Unidade;
  private String NM_Razao_Social;

  private String oid_Pessoa;
  private String cd_Deposito;
  private String NR_CNPJ_CPF;
  private String NM_Fantasia;
  private String CD_Local_Picking;
  private String CD_Local_Carga;
  private String CD_Local_Descarga;
  private String CD_Local_Entrada;
  private String CD_Local_Saida;

  public String getNm_Deposito() {
    return nm_Deposito;
  }
  public Integer getOid_Deposito() {
    return oid_Deposito;
  }
  public void setOid_Deposito(Integer oid_Deposito) {
    this.oid_Deposito = oid_Deposito;
  }
  public void setNm_Deposito(String nm_Deposito) {
    this.nm_Deposito = nm_Deposito;
  }
  public void setNM_Senha(String NM_Senha) {
    this.NM_Senha = NM_Senha;
  }
  public String getNM_Senha() {
    return NM_Senha;
  }
  public void setDM_Tipo(String DM_Tipo) {
    this.DM_Tipo = DM_Tipo;
  }
  public String getDM_Tipo() {
    return DM_Tipo;
  }
  public void setOid_Unidade(Integer oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }
  public Integer getOid_Unidade() {
    return oid_Unidade;
  }
  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
  }
  public void setNM_Razao_Social(String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }
  public String getNM_Razao_Social() {
    return NM_Razao_Social;
  }

  public String getOid_Pessoa() {
    return oid_Pessoa;
  }
  public void setOid_Pessoa(String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }
  public void setCd_Deposito(String cd_Deposito) {
    this.cd_Deposito = cd_Deposito;
  }
  public String getCd_Deposito() {
    return cd_Deposito;
  }
  public void setNR_CNPJ_CPF(String NR_CNPJ_CPF) {
    this.NR_CNPJ_CPF = NR_CNPJ_CPF;
  }
  public String getNR_CNPJ_CPF() {
    return NR_CNPJ_CPF;
  }
  public void setNM_Fantasia(String NM_Fantasia) {
    this.NM_Fantasia = NM_Fantasia;
  }
  public String getNM_Fantasia() {
    return NM_Fantasia;
  }
  public void setCD_Local_Picking(String CD_Local_Picking) {
    this.CD_Local_Picking = CD_Local_Picking;
  }
  public String getCD_Local_Picking() {
    return CD_Local_Picking;
  }
  public void setCD_Local_Carga(String CD_Local_Carga) {
    this.CD_Local_Carga = CD_Local_Carga;
  }
  public String getCD_Local_Carga() {
    return CD_Local_Carga;
  }
  public void setCD_Local_Descarga(String CD_Local_Descarga) {
    this.CD_Local_Descarga = CD_Local_Descarga;
  }
  public String getCD_Local_Descarga() {
    return CD_Local_Descarga;
  }
  public void setCD_Local_Entrada(String CD_Local_Entrada) {
    this.CD_Local_Entrada = CD_Local_Entrada;
  }
  public String getCD_Local_Entrada() {
    return CD_Local_Entrada;
  }
  public void setCD_Local_Saida(String CD_Local_Saida) {
    this.CD_Local_Saida = CD_Local_Saida;
  }
  public String getCD_Local_Saida() {
    return CD_Local_Saida;
  }

}
