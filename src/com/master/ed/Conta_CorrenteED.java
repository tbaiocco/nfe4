package com.master.ed;

public class Conta_CorrenteED extends MasterED {

    public Conta_CorrenteED() {
        super();
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }
    public Conta_CorrenteED(String oid_Conta_Corrente) {
        this.oid_Conta_Corrente = oid_Conta_Corrente;
    }

  private String oid_Conta_Corrente;
  private String nr_Conta_Corrente;
  private Double vl_Saldo_Inicial;
  private String dm_Tipo_Conta_Corrente;
  private String cd_Conta_Corrente;
  private String nr_Agencia;
  private Integer oid_Conta;
  private Integer oid_Moeda;
  private String oid_Pessoa;
  private String nm_Razao_Social;
  private String cd_Moeda;
  private String cd_Conta;
  private String nr_CNPJ_CPF;
  private String nm_Moeda;
  private String nm_Conta;
  private String cd_Banco;
  private String Nm_Empresa;
  private Integer oid_Empresa;
  private String cd_Empresa;
  private int oid_Unidade;
  private String dm_Controle_Saldo;
  private String dm_Controle_Cobranca;
  private String dm_Contabilizacao;
  private String dm_Grupo;
  private long oid_Documento_Padrao;

  public int getOid_Unidade() {
    return oid_Unidade;
}
public void setOid_Unidade(int oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
}
  public String getCd_Conta_Corrente() {
    return cd_Conta_Corrente;
  }
  public String getDm_Tipo_Conta_Corrente() {
    return dm_Tipo_Conta_Corrente;
  }
  public String getNr_Agencia() {
    return nr_Agencia;
  }
  public String getNr_Conta_Corrente() {
    return nr_Conta_Corrente;
  }
  public Integer getOid_Conta() {
    return oid_Conta;
  }
  public String getOid_Conta_Corrente() {
    return oid_Conta_Corrente;
  }
  public Integer getOid_Moeda() {
    return oid_Moeda;
  }
  public String getOid_Pessoa() {
    return oid_Pessoa;
  }
  public Double getVl_Saldo_Inicial() {
    return vl_Saldo_Inicial;
  }
  public void setVl_Saldo_Inicial(Double vl_Saldo_Inicial) {
    this.vl_Saldo_Inicial = vl_Saldo_Inicial;
  }
  public void setOid_Pessoa(String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }
  public void setOid_Moeda(Integer oid_Moeda) {
    this.oid_Moeda = oid_Moeda;
  }
  public void setOid_Conta_Corrente(String oid_Conta_Corrente) {
    this.oid_Conta_Corrente = oid_Conta_Corrente;
  }
  public void setOid_Conta(Integer oid_Conta) {
    this.oid_Conta = oid_Conta;
  }
  public void setNr_Conta_Corrente(String nr_Conta_Corrente) {
    this.nr_Conta_Corrente = nr_Conta_Corrente;
  }
  public void setNr_Agencia(String nr_Agencia) {
    this.nr_Agencia = nr_Agencia;
  }
  public void setDm_Tipo_Conta_Corrente(String dm_Tipo_Conta_Corrente) {
    this.dm_Tipo_Conta_Corrente = dm_Tipo_Conta_Corrente;
  }
  public void setCd_Conta_Corrente(String cd_Conta_Corrente) {
    this.cd_Conta_Corrente = cd_Conta_Corrente;
  }
  public void setNm_Razao_Social(String nm_Razao_Social) {
    this.nm_Razao_Social = nm_Razao_Social;
  }
  public String getNm_Razao_Social() {
    return nm_Razao_Social;
  }
  public void setCd_Moeda(String cd_Moeda) {
    this.cd_Moeda = cd_Moeda;
  }
  public String getCd_Moeda() {
    return cd_Moeda;
  }
  public void setCd_Conta(String cd_Conta) {
    this.cd_Conta = cd_Conta;
  }
  public String getCd_Conta() {
    return cd_Conta;
  }
  public void setNr_CNPJ_CPF(String nr_CNPJ_CPF) {
    this.nr_CNPJ_CPF = nr_CNPJ_CPF;
  }
  public String getNr_CNPJ_CPF() {
    return nr_CNPJ_CPF;
  }
  public void setNm_Moeda(String nm_Moeda) {
    this.nm_Moeda = nm_Moeda;
  }
  public String getNm_Moeda() {
    return nm_Moeda;
  }
  public void setNm_Conta(String nm_Conta) {
    this.nm_Conta = nm_Conta;
  }
  public String getNm_Conta() {
    return nm_Conta;
  }
  public void setCd_Banco(String cd_Banco) {
    this.cd_Banco = cd_Banco;
  }
  public String getCd_Banco() {
    return cd_Banco;
  }
  public void setOid_Empresa(Integer oid_Empresa) {
    this.oid_Empresa = oid_Empresa;
  }
  public Integer getOid_Empresa() {
    return oid_Empresa;
  }
  public void setNm_Empresa(String Nm_Empresa) {
    this.Nm_Empresa = Nm_Empresa;
  }
  public String getNm_Empresa() {
    return Nm_Empresa;
  }
  public void setCd_Empresa(String cd_Empresa) {
    this.cd_Empresa = cd_Empresa;
  }

  public void setDm_Controle_Saldo (String dm_Controle_Saldo) {

    this.dm_Controle_Saldo = dm_Controle_Saldo;
  }

  public void setDm_Controle_Cobranca (String dm_Controle_Cobranca) {
    this.dm_Controle_Cobranca = dm_Controle_Cobranca;
  }

  public void setDm_Contabilizacao (String dm_Contabilizacao) {
    this.dm_Contabilizacao = dm_Contabilizacao;
  }

  public void setDm_Grupo (String dm_Grupo) {

    this.dm_Grupo = dm_Grupo;
  }

  public void setOid_Documento_Padrao (long oid_Documento_Padrao) {
    this.oid_Documento_Padrao = oid_Documento_Padrao;
  }

  public String getCd_Empresa() {
    return cd_Empresa;
  }

  public String getDm_Controle_Saldo () {

    return dm_Controle_Saldo;
  }

  public String getDm_Controle_Cobranca () {
    return dm_Controle_Cobranca;
  }

  public String getDm_Contabilizacao () {
    return dm_Contabilizacao;
  }

  public String getDm_Grupo () {

    return dm_Grupo;
  }

  public long getOid_Documento_Padrao () {
    return oid_Documento_Padrao;
  }

  private void jbInit () throws Exception {
  }

}
