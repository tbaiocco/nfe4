package com.master.ed;

public class WMS_LocalizacaoED
    extends MasterED {

  public WMS_LocalizacaoED (int oid_Localizacao) {
    this.oid_Localizacao = oid_Localizacao;
  }

  public WMS_LocalizacaoED () {
    super ();
  }

  private int oid_Localizacao;
  private int oid_Deposito;
  private String CD_Localizacao;
  private String nm_Rua;
  private String nr_Endereco;
  private String nr_Apartamento;
  private String NM_Deposito;
  private String NM_Fantasia;
  private String NM_Razao_Social;
  private String DM_Situacao;

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setDM_Situacao (String situacao) {
    DM_Situacao = situacao;
  }

  public String getNM_Deposito () {
    return NM_Deposito;
  }

  public void setNM_Deposito (String deposito) {
    NM_Deposito = deposito;
  }

  public String getNM_Fantasia () {
    return NM_Fantasia;
  }

  public void setNM_Fantasia (String fantasia) {
    NM_Fantasia = fantasia;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public void setNM_Razao_Social (String razao_Social) {
    NM_Razao_Social = razao_Social;
  }

  public String getNm_Rua () {
    return nm_Rua;
  }

  public void setNm_Rua (String nm_Rua) {
    this.nm_Rua = nm_Rua;
  }

  public String getNr_Apartamento () {
    return nr_Apartamento;
  }

  public void setNr_Apartamento (String nr_Apartamento) {
    this.nr_Apartamento = nr_Apartamento;
  }

  public String getNr_Endereco () {
    return nr_Endereco;
  }

  public void setNr_Endereco (String nr_Endereco) {
    this.nr_Endereco = nr_Endereco;
  }

  public int getOid_Deposito () {
    return oid_Deposito;
  }

  public void setOid_Deposito (int oid_Deposito) {
    this.oid_Deposito = oid_Deposito;
  }

  public int getOid_Localizacao () {
    return oid_Localizacao;
  }

  public void setOid_Localizacao (int oid_Localizacao) {
    this.oid_Localizacao = oid_Localizacao;
  }

  public String getCD_Localizacao () {
    return CD_Localizacao;
  }

  public void setCD_Localizacao (String localizacao) {
    CD_Localizacao = localizacao;
  }
}