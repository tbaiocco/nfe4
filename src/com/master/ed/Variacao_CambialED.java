package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Variacao_CambialED extends MasterED {
  private long oid_Empresa;
  private String DT_Emissao_Inicial;
  private String DM_Relatorio;
  private String DM_Lista_Conhecimento;
  private long oid_Variacao_Cambial;
  private String oid_Conhecimento;
  private String DT_Periodo_Inicial;
  private String DT_Periodo_Final;
  private String NM_Periodo;
  private String DT_Liquidacao;
  private String oid_Movimento_Duplicata;
  private long oid_Duplicata;
  private double VL_Cotacao_Inicial;
  private double VL_Cotacao_Final;
  private double VL_Variacao_Cambial;
  private long oid_Unidade;
  private double VL_Frete;
  private double VL_Cotacao_Pagamento;
  private String DT_Cotacao_Calculo_Inicial;
  private String DT_Cotacao_Calculo_Final;
  private double VL_Cotacao_Calculo_Inicial;
  private double VL_Cotacao_Calculo_Final;
  public long getOid_Empresa () {

    return oid_Empresa;
  }
  public void setOid_Empresa (long oid_Empresa) {

    this.oid_Empresa = oid_Empresa;
  }

  public void setDT_Emissao_Inicial(String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }
  public String getDT_Emissao_Inicial() {
    return DT_Emissao_Inicial;
  }

  public void setDM_Relatorio(String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }
  public String getDM_Relatorio() {
    return DM_Relatorio;
  }
  public void setDM_Lista_Conhecimento(String DM_Lista_Conhecimento) {
    this.DM_Lista_Conhecimento = DM_Lista_Conhecimento;
  }
  public String getDM_Lista_Conhecimento() {
    return DM_Lista_Conhecimento;
  }

  public void setOid_Variacao_Cambial (long oid_Variacao_Cambial) {

    this.oid_Variacao_Cambial = oid_Variacao_Cambial;
  }
  public long getOid_Variacao_Cambial () {

    return oid_Variacao_Cambial;
  }

  public void setOid_Conhecimento (String oid_Conhecimento) {

    this.oid_Conhecimento = oid_Conhecimento;
  }
  public String getOid_Conhecimento () {

    return oid_Conhecimento;
  }

  public void setDT_Periodo_Inicial (String DT_Periodo_Inicial) {
    this.DT_Periodo_Inicial = DT_Periodo_Inicial;
  }

  public String getDT_Periodo_Inicial () {
    return DT_Periodo_Inicial;
  }

  public void setDT_Periodo_Final (String DT_Periodo_Final) {
    this.DT_Periodo_Final = DT_Periodo_Final;
  }

  public String getDT_Periodo_Final () {
    return DT_Periodo_Final;
  }

  public void setNM_Periodo (String NM_Periodo) {
    this.NM_Periodo = NM_Periodo;
  }

  public String getNM_Periodo () {
    return NM_Periodo;
  }

  public void setDT_Liquidacao (String DT_Liquidacao) {
    this.DT_Liquidacao = DT_Liquidacao;
  }

  public String getDT_Liquidacao () {
    return DT_Liquidacao;
  }

  public void setOid_Movimento_Duplicata (String oid_Movimento_Duplicata) {
    this.oid_Movimento_Duplicata = oid_Movimento_Duplicata;
  }

  public void setOid_Duplicata (long oid_Duplicata) {
    this.oid_Duplicata = oid_Duplicata;
  }

  public void setOid_Unidade (long oid_Unidade) {

    this.oid_Unidade = oid_Unidade;
  }

  public void setVL_Cotacao_Calculo_Final (double VL_Cotacao_Calculo_Final) {
    this.VL_Cotacao_Calculo_Final = VL_Cotacao_Calculo_Final;
  }

  public void setVL_Cotacao_Calculo_Inicial (double VL_Cotacao_Calculo_Inicial) {
    this.VL_Cotacao_Calculo_Inicial = VL_Cotacao_Calculo_Inicial;
  }

  public void setDT_Cotacao_Calculo_Final (String DT_Cotacao_Calculo_Final) {

    this.DT_Cotacao_Calculo_Final = DT_Cotacao_Calculo_Final;
  }

  public void setDT_Cotacao_Calculo_Inicial (String DT_Cotacao_Calculo_Inicial) {

    this.DT_Cotacao_Calculo_Inicial = DT_Cotacao_Calculo_Inicial;
  }

  public void setVL_Cotacao_Pagamento (double VL_Cotacao_Pagamento) {
    this.VL_Cotacao_Pagamento = VL_Cotacao_Pagamento;
  }

  public void setVL_Frete (double VL_Frete) {
    this.VL_Frete = VL_Frete;
  }

  public void setVL_Variacao_Cambial (double VL_Variacao_Cambial) {
    this.VL_Variacao_Cambial = VL_Variacao_Cambial;
  }

  public void setVL_Cotacao_Final (double VL_Cotacao_Final) {
    this.VL_Cotacao_Final = VL_Cotacao_Final;
  }

  public void setVL_Cotacao_Inicial (double VL_Cotacao_Inicial) {
    this.VL_Cotacao_Inicial = VL_Cotacao_Inicial;
  }

  public String getOid_Movimento_Duplicata () {
    return oid_Movimento_Duplicata;
  }

  public long getOid_Duplicata () {
    return oid_Duplicata;
  }

  public long getOid_Unidade () {

    return oid_Unidade;
  }

  public double getVL_Cotacao_Calculo_Final () {
    return VL_Cotacao_Calculo_Final;
  }

  public double getVL_Cotacao_Calculo_Inicial () {
    return VL_Cotacao_Calculo_Inicial;
  }

  public String getDT_Cotacao_Calculo_Final () {

    return DT_Cotacao_Calculo_Final;
  }

  public String getDT_Cotacao_Calculo_Inicial () {

    return DT_Cotacao_Calculo_Inicial;
  }

  public double getVL_Cotacao_Pagamento () {
    return VL_Cotacao_Pagamento;
  }

  public double getVL_Frete () {
    return VL_Frete;
  }

  public double getVL_Variacao_Cambial () {
    return VL_Variacao_Cambial;
  }

  public double getVL_Cotacao_Final () {
    return VL_Cotacao_Final;
  }

  public double getVL_Cotacao_Inicial () {
    return VL_Cotacao_Inicial;
  }

}
