package com.master.ed;

/**
 * Título: WMS_Tipo_PalletED
 * Descrição: Tipos de Pallet - ED
 * Data da criação: 02/2004
 * Atualizado em: 02/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

public class WMS_Tipo_PalletED extends MasterED {

  private int oid_Tipo_Pallet;
  private String nm_Descricao;
  private String nm_Material;
  private double nr_Largura;
  private double nr_Profundidade;

  public String getNm_Descricao() {
    return nm_Descricao;
  }
  public String getNm_Material() {
    return nm_Material;
  }
  public double getNr_Largura() {
    return nr_Largura;
  }
  public double getNr_Profundidade() {
    return nr_Profundidade;
  }
  public int getOid_Tipo_Pallet() {
    return oid_Tipo_Pallet;
  }
  public void setNm_Descricao(String nm_Descricao) {
    this.nm_Descricao = nm_Descricao;
  }
  public void setNm_Material(String nm_Material) {
    this.nm_Material = nm_Material;
  }
  public void setNr_Largura(double nr_Largura) {
    this.nr_Largura = nr_Largura;
  }
  public void setNr_Profundidade(double nr_Profundidade) {
    this.nr_Profundidade = nr_Profundidade;
  }
  public void setOid_Tipo_Pallet(int oid_Tipo_Pallet) {
    this.oid_Tipo_Pallet = oid_Tipo_Pallet;
  }
}