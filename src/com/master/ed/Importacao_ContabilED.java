package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */


public class Importacao_ContabilED
    extends MasterED implements java.io.Serializable {

  private String DM_Tipo_Lancamento;
  private String DT_Lancamento_Inicial;
  private String DT_Lancamento_Final;
  private long oid_Unidade;

  public String getDM_Tipo_Lancamento () {
    return DM_Tipo_Lancamento;
  }

  public void setDM_Tipo_Lancamento (String tipo_Lancamento) {
    DM_Tipo_Lancamento = tipo_Lancamento;
  }

  public String getDT_Lancamento_Final () {
    return DT_Lancamento_Final;
  }

  public void setDT_Lancamento_Final (String lancamento_Final) {
    DT_Lancamento_Final = lancamento_Final;
  }

  public String getDT_Lancamento_Inicial () {
    return DT_Lancamento_Inicial;
  }

  public void setDT_Lancamento_Inicial (String lancamento_Inicial) {
    DT_Lancamento_Inicial = lancamento_Inicial;
  }

  public void setOid_Unidade (long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }

  public long getOid_Unidade () {
    return oid_Unidade;
  }
}