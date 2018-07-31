package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Lote_PagamentoPesquisaED extends Lote_PagamentoED {

  public Lote_PagamentoPesquisaED() {
  }
  private String DT_Programada_Inicial;
  private String DT_Programada_Final;
  private String dt_Inicial;
  private String dt_Final;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String NR_Documento;
  public String getDT_Programada_Inicial() {
    return DT_Programada_Inicial;
  }
  public void setDT_Programada_Inicial(String DT_Programada_Inicial) {
    this.DT_Programada_Inicial = DT_Programada_Inicial;
  }
  public void setDT_Programada_Final(String DT_Programada_Final) {
    this.DT_Programada_Final = DT_Programada_Final;
  }
  public String getDT_Programada_Final() {
    return DT_Programada_Final;
  }
  public void setDt_Inicial(String dt_Inicial) {
    this.dt_Inicial = dt_Inicial;
  }
  public String getDt_Inicial() {
    return dt_Inicial;
  }
  public void setDt_Final(String dt_Final) {
    this.dt_Final = dt_Final;
  }
  public String getDt_Final() {
    return dt_Final;
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
  public void setNR_Documento(String NR_Documento) {
    this.NR_Documento = NR_Documento;
  }
  public String getNR_Documento() {
    return NR_Documento;
  }
}