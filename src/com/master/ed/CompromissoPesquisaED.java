package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CompromissoPesquisaED extends CompromissoED {

  public CompromissoPesquisaED() {
  }
  private String data_Vencimento_Inicial;
  private String data_Vencimento_Final;
  private String data_Emissao_Inicial;
  private String data_Emissao_Final;
  private String data_Pagamento_Inicial;
  private String data_Pagamento_Final;
  private long OID_Parcelamento;
  private double VL_Previsto_Inicial;
  private double VL_Previsto_Final;
  private String dm_Ordenar;

  public String getData_Vencimento_Inicial() {
    return data_Vencimento_Inicial;
  }
  public void setData_Vencimento_Inicial(String data_Vencimento_Inicial) {
    this.data_Vencimento_Inicial = data_Vencimento_Inicial;
  }
  public void setData_Vencimento_Final(String data_Vencimento_Final) {
    this.data_Vencimento_Final = data_Vencimento_Final;
  }
  public String getData_Vencimento_Final() {
    return data_Vencimento_Final;
  }
  public String getData_Emissao_Final() {
    return data_Emissao_Final;
  }
  public String getData_Emissao_Inicial() {
    return data_Emissao_Inicial;
  }
  public String getData_Pagamento_Final() {
    return data_Pagamento_Final;
  }
  public String getData_Pagamento_Inicial() {
    return data_Pagamento_Inicial;
  }

  public String getDm_Ordenar () {
    return dm_Ordenar;
  }

  public double getVL_Previsto_Final () {
    return VL_Previsto_Final;
  }

  public double getVL_Previsto_Inicial () {
    return VL_Previsto_Inicial;
  }

  public void setData_Emissao_Final(String data_Emissao_Final) {
    this.data_Emissao_Final = data_Emissao_Final;
  }
  public void setData_Emissao_Inicial(String data_Emissao_Inicial) {
    this.data_Emissao_Inicial = data_Emissao_Inicial;
  }
  public void setData_Pagamento_Final(String data_Pagamento_Final) {
    this.data_Pagamento_Final = data_Pagamento_Final;
  }
  public void setData_Pagamento_Inicial(String data_Pagamento_Inicial) {
    this.data_Pagamento_Inicial = data_Pagamento_Inicial;
  }

  public void setDm_Ordenar (String dm_Ordenar) {
    this.dm_Ordenar = dm_Ordenar;
  }

  public void setVL_Previsto_Final (double VL_Previsto_Final) {
    this.VL_Previsto_Final = VL_Previsto_Final;
  }

  public void setVL_Previsto_Inicial (double VL_Previsto_Inicial) {
    this.VL_Previsto_Inicial = VL_Previsto_Inicial;
  }

  public void setOID_Parcelamento(long OID_Parcelamento) {
    this.OID_Parcelamento = OID_Parcelamento;
  }
  public long getOID_Parcelamento() {
    return OID_Parcelamento;
  }
}