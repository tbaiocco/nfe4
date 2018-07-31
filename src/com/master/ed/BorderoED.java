package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class BorderoED extends MasterED{

  private String dt_Emissao;
  private String dt_Operacao;
  private Double vl_Bordero;
  private Integer nr_Duplicata;
  private Integer oid_Carteira;
  private Long oid_Unidade;
  private String cd_Carteira;
  private String nm_Carteira;
  private String cd_Unidade;
  private String CD_Conta_Corrente;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String CD_Banco;
  private String DM_Bordero;
  private Integer oid_Bordero;
  private Integer nr_Bordero;
  private String NM_Banco;
  private String acao;
  private String DM_Relatorio;
  private String NM_Tipo_Bordero;
  private double VL_Recebimento;

  public BorderoED() {
  }

public String getDt_Operacao() {
    return dt_Operacao;
}
public void setDt_Operacao(String dt_Operacao) {
    this.dt_Operacao = dt_Operacao;
}
  public String getDt_Emissao() {
    return dt_Emissao;
  }
  public void setDt_Emissao(String dt_Emissao) {
      this.dt_Emissao = dt_Emissao;
  }
  public Integer getNr_Duplicata() {
    return nr_Duplicata;
  }
  public Integer getNr_Bordero() {
    return nr_Bordero;
  }
  public Integer getOid_Carteira() {
    return oid_Carteira;
  }
  public Long getOid_Unidade() {
    return oid_Unidade;
  }
  public Double getVl_Bordero() {
    return vl_Bordero;
  }
  public void setVl_Bordero(Double vl_Bordero) {
    this.vl_Bordero = vl_Bordero;
  }
  public void setOid_Unidade(Long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }
  public void setOid_Carteira(Integer oid_Carteira) {
    this.oid_Carteira = oid_Carteira;
  }
  public void setNr_Bordero(Integer nr_Bordero) {
    this.nr_Bordero = nr_Bordero;
  }
  public void setNr_Duplicata(Integer nr_Duplicata) {
    this.nr_Duplicata = nr_Duplicata;
  }

  public void setCd_Carteira(String cd_Carteira) {
    this.cd_Carteira = cd_Carteira;
  }
  public String getCd_Carteira() {
    return cd_Carteira;
  }
  public void setNm_Carteira(String nm_Carteira) {
    this.nm_Carteira = nm_Carteira;
  }
  public String getNm_Carteira() {
    return nm_Carteira;
  }
  public void setCd_Unidade(String cd_Unidade) {
    this.cd_Unidade = cd_Unidade;
  }
  public String getCd_Unidade() {
    return cd_Unidade;
  }
  public void setCD_Conta_Corrente(String CD_Conta_Corrente) {
    this.CD_Conta_Corrente = CD_Conta_Corrente;
  }
  public String getCD_Conta_Corrente() {
    return CD_Conta_Corrente;
  }
  public void setDT_Emissao_Inicial(String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }
  public String getDT_Emissao_Inicial() {
    return DT_Emissao_Inicial;
  }
  public void setDT_Emissao_Final(String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }
  public String getDT_Emissao_Final() {
    return DT_Emissao_Final;
  }
  public void setCD_Banco(String CD_Banco) {
    this.CD_Banco = CD_Banco;
  }
  public String getCD_Banco() {
    return CD_Banco;
  }
  public void setDM_Bordero(String DM_Bordero) {
    this.DM_Bordero = DM_Bordero;
  }
  public String getDM_Bordero() {
    return DM_Bordero;
  }
  public void setOid_Bordero(Integer oid_Bordero) {
    this.oid_Bordero = oid_Bordero;
  }
  public Integer getOid_Bordero() {
    return oid_Bordero;
  }
  public void setNM_Banco(String NM_Banco) {
    this.NM_Banco = NM_Banco;
  }
  public String getNM_Banco() {
    return NM_Banco;
  }
  public void setAcao(String acao) {
    this.acao = acao;
  }

  public void setVL_Recebimento (double VL_Recebimento) {
    this.VL_Recebimento = VL_Recebimento;
  }

  public void setNM_Tipo_Bordero (String NM_Tipo_Bordero) {
    this.NM_Tipo_Bordero = NM_Tipo_Bordero;
  }

  public String getAcao() {
    return acao;
  }

  public double getVL_Recebimento () {
    return VL_Recebimento;
  }

  public String getNM_Tipo_Bordero () {
    return NM_Tipo_Bordero;
  }

  public void setDM_Relatorio(String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }
  public String getDM_Relatorio() {
    return DM_Relatorio;
  }
}
