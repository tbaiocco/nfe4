package com.master.ed;

public class Movimento_CotacaoED extends MasterED{

  private long OID_Modal;
  private String OID_Cotacao;
  private String OID_Movimento_Cotacao;
  private double VL_Coleta;
  private double VL_Entrega;
  private double VL_Frete_Peso;
  private double VL_Frete_Valor;
  private double VL_Taxas;
  private double VL_Pedagio;
  private double VL_Outros1;
  private double VL_Total_Frete;
  private double VL_ICMS;
  private double VL_Desconto;
  private double PE_Desconto;
  private String DM_Situacao;

  public void setOID_Cotacao(String OID_Cotacao) {
    this.OID_Cotacao = OID_Cotacao;
  }
  public String getOID_Cotacao() {
    return OID_Cotacao;
  }

  public void setOID_Movimento_Cotacao (String OID_Movimento_Cotacao) {
    this.OID_Movimento_Cotacao = OID_Movimento_Cotacao;
  }

  public String getOID_Movimento_Cotacao () {
    return OID_Movimento_Cotacao;
  }

  public void setVL_Coleta (double VL_Coleta) {
    this.VL_Coleta = VL_Coleta;
  }

  public double getVL_Coleta () {
    return VL_Coleta;
  }

  public void setVL_Entrega (double VL_Entrega) {
    this.VL_Entrega = VL_Entrega;
  }

  public double getVL_Entrega () {
    return VL_Entrega;
  }

  public void setVL_Frete_Peso (double VL_Frete_Peso) {
    this.VL_Frete_Peso = VL_Frete_Peso;
  }

  public double getVL_Frete_Peso () {
    return VL_Frete_Peso;
  }

  public void setVL_Frete_Valor (double VL_Frete_Valor) {
    this.VL_Frete_Valor = VL_Frete_Valor;
  }

  public double getVL_Frete_Valor () {
    return VL_Frete_Valor;
  }

  public void setVL_Taxas (double VL_Taxas) {
    this.VL_Taxas = VL_Taxas;
  }

  public double getVL_Taxas () {
    return VL_Taxas;
  }

  public void setVL_Pedagio (double VL_Pedagio) {
    this.VL_Pedagio = VL_Pedagio;
  }

  public double getVL_Pedagio () {
    return VL_Pedagio;
  }

  public void setVL_Outros1 (double VL_Outros1) {

    this.VL_Outros1 = VL_Outros1;
  }

  public double getVL_Outros1 () {

    return VL_Outros1;
  }

  public void setVL_Total_Frete (double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }

  public double getVL_Total_Frete () {
    return VL_Total_Frete;
  }

  public void setVL_ICMS (double VL_ICMS) {
    this.VL_ICMS = VL_ICMS;
  }

  public double getVL_ICMS () {
    return VL_ICMS;
  }

  public void setVL_Desconto (double VL_Desconto) {
    this.VL_Desconto = VL_Desconto;
  }

  public double getVL_Desconto () {
    return VL_Desconto;
  }

  public void setPE_Desconto (double PE_Desconto) {
    this.PE_Desconto = PE_Desconto;
  }

  public double getPE_Desconto () {
    return PE_Desconto;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public long getOID_Modal () {
    return OID_Modal;
  }

  public void setOID_Modal (long OID_Modal) {
    this.OID_Modal = OID_Modal;
  }
}
