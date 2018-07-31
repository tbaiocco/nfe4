/*
 * Created on 03/09/2004
 */
package com.master.ed;

/**
 * @author Andre Valadas
 */
public class Comissao_AgenciamentoED
    extends MasterED {

  private int oid_Comissao_Agenciamento;
  private String oid_Transportador;
  private double PE_Comissao;
  private int oid_Produto;
  private String DM_Situacao;
  private String CD_Produto;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String DM_Relatorio;
  private String DM_Tipo_Documento;
  private long oid_Unidade;
  private String NM_Fantasia; /**
   * @return Returns the cD_Produto.
   */
  public String getCD_Produto () {
    return CD_Produto;
  }

  /**
   * @param produto The cD_Produto to set.
   */
  public void setCD_Produto (String produto) {
    CD_Produto = produto;
  }
  /**
   * @return Returns the dM_Situacao.
   */
  public String getDM_Situacao () {
    return DM_Situacao;
  }

  /**
   * @param situacao The dM_Situacao to set.
   */
  public void setDM_Situacao (String situacao) {
    DM_Situacao = situacao;
  }
  /**
   * @return Returns the oid_Comissao_Agenciamento.
   */
  public int getOid_Comissao_Agenciamento () {
    return oid_Comissao_Agenciamento;
  }

  /**
   * @param oid_Comissao_Agenciamento The oid_Comissao_Agenciamento to set.
   */
  public void setOid_Comissao_Agenciamento (int oid_Comissao_Agenciamento) {
    this.oid_Comissao_Agenciamento = oid_Comissao_Agenciamento;
  }
  /**
   * @return Returns the oid_Produto.
   */
  public int getOid_Produto () {
    return oid_Produto;
  }

  /**
   * @param oid_Produto The oid_Produto to set.
   */
  public void setOid_Produto (int oid_Produto) {
    this.oid_Produto = oid_Produto;
  }
  /**
   * @return Returns the oid_Transportador.
   */
  public String getoid_Transportador () {
    return oid_Transportador;
  }

  public long getOid_Unidade () {
    return oid_Unidade;
  }

  public String getNM_Fantasia () {
    return NM_Fantasia;
  }

  public String getDM_Tipo_Documento () {
    return DM_Tipo_Documento;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public String getDT_Emissao_Final () {
    return DT_Emissao_Final;
  }

  public String getDT_Emissao_Inicial () {
    return DT_Emissao_Inicial;
  }

  /**
   * @param oid_Transportador The oid_Transportador to set.
   */
  public void setoid_Transportador (String oid_Transportador) {
    this.oid_Transportador = oid_Transportador;
  }

  public void setOid_Unidade (long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }

  public void setNM_Fantasia (String NM_Fantasia) {
    this.NM_Fantasia = NM_Fantasia;
  }

  public void setDM_Tipo_Documento (String DM_Tipo_Documento) {
    this.DM_Tipo_Documento = DM_Tipo_Documento;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public void setDT_Emissao_Final (String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }

  public void setDT_Emissao_Inicial (String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }
  public double getPE_Comissao () {
    return PE_Comissao;
  }

  /**
   * @param comissao The pE_Comissao to set.
   */
  public void setPE_Comissao (double comissao) {
    PE_Comissao = comissao;
  }
}
