package com.master.ed;

public class ColetaRCEED
    extends MasterED {
  public ColetaRCEED () {

  }

  private String OID_ColetaRCE;
  private String OID_Manifesto;
  private long NR_Manifesto;
  private String OID_Coleta;
  private String NR_Coleta;
  private long OID_Unidade;
  private String CD_Unidade;
  private String NM_Fantasia;

  private String OID_Pessoa;
  private String NR_CNPJ_CPF;
  private String NM_Pessoa_Remetente;
  private String OID_Pessoa_Destinatario;
  private String NR_CNPJ_CPF_Destinatario;
  private String NM_Pessoa_Destinatario;

  private double VL_Entrega;
  private String DM_Tipo;
  private String DT_Coleta_RCE;
  private String HR_Coleta_RCE;
  private String DT_Emissao;

  public String getCD_Unidade () {
    return CD_Unidade;
  }

  public void setCD_Unidade (String unidade) {
    CD_Unidade = unidade;
  }

  public String getDM_Tipo () {
    return DM_Tipo;
  }

  public void setDM_Tipo (String tipo) {
    DM_Tipo = tipo;
  }

  public String getNM_Fantasia () {
    return NM_Fantasia;
  }

  public void setNM_Fantasia (String fantasia) {
    NM_Fantasia = fantasia;
  }

  public String getNM_Pessoa_Destinatario () {
    return NM_Pessoa_Destinatario;
  }

  public void setNM_Pessoa_Destinatario (String pessoa_Destinatario) {
    NM_Pessoa_Destinatario = pessoa_Destinatario;
  }

  public String getNM_Pessoa_Remetente () {
    return NM_Pessoa_Remetente;
  }

  public void setNM_Pessoa_Remetente (String pessoa_Remetente) {
    NM_Pessoa_Remetente = pessoa_Remetente;
  }

  public String getNR_CNPJ_CPF () {
    return NR_CNPJ_CPF;
  }

  public void setNR_CNPJ_CPF (String nr_cnpj_cpf) {
    NR_CNPJ_CPF = nr_cnpj_cpf;
  }

  public String getNR_CNPJ_CPF_Destinatario () {
    return NR_CNPJ_CPF_Destinatario;
  }

  public void setNR_CNPJ_CPF_Destinatario (String destinatario) {
    NR_CNPJ_CPF_Destinatario = destinatario;
  }

  public String getNR_Coleta () {
    return NR_Coleta;
  }

  public void setNR_Coleta (String coleta) {
    NR_Coleta = coleta;
  }

  public long getNR_Manifesto () {
    return NR_Manifesto;
  }

  public void setNR_Manifesto (long manifesto) {
    NR_Manifesto = manifesto;
  }

  public String getOID_Coleta () {
    return OID_Coleta;
  }

  public void setOID_Coleta (String coleta) {
    OID_Coleta = coleta;
  }

  public String getOID_ColetaRCE () {
    return OID_ColetaRCE;
  }

  public void setOID_ColetaRCE (String coletaRCE) {
    OID_ColetaRCE = coletaRCE;
  }

  public String getOID_Manifesto () {
    return OID_Manifesto;
  }

  public void setOID_Manifesto (String manifesto) {
    OID_Manifesto = manifesto;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }

  public void setOID_Pessoa (String pessoa) {
    OID_Pessoa = pessoa;
  }

  public String getOID_Pessoa_Destinatario () {
    return OID_Pessoa_Destinatario;
  }

  public void setOID_Pessoa_Destinatario (String pessoa_Destinatario) {
    OID_Pessoa_Destinatario = pessoa_Destinatario;
  }

  public long getOID_Unidade () {
    return OID_Unidade;
  }

  public void setOID_Unidade (long unidade) {
    OID_Unidade = unidade;
  }

  public double getVL_Entrega () {
    return VL_Entrega;
  }

  public void setVL_Entrega (double entrega) {
    VL_Entrega = entrega;
  }

  public String getDT_Coleta_RCE () {
    return DT_Coleta_RCE;
  }

  public void setDT_Coleta_RCE (String coleta_RCE) {
    DT_Coleta_RCE = coleta_RCE;
  }

  public String getHR_Coleta_RCE () {
    return HR_Coleta_RCE;
  }

  public void setHR_Coleta_RCE (String coleta_RCE) {
    HR_Coleta_RCE = coleta_RCE;
  }

  public String getDT_Emissao () {
    return DT_Emissao;
  }

  public void setDT_Emissao (String emissao) {
    DT_Emissao = emissao;
  }

}
