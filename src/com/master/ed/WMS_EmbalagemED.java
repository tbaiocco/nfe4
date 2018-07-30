package com.master.ed;

/**
 * Título: WMS_EmbalagemED
 * Descrição: Embalagens - ED
 * Data da criação: 10/2003
 * Atualizado em: 02/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

public class WMS_EmbalagemED extends MasterED {

  private int oid_Embalagem;
  private double nr_Peso_Liquido;
  private double nr_Peso_Bruto;
  private String nm_Tipo;
  private String nm_Descricao;
  private String nm_Material;

  public String getNm_Descricao() {
    return nm_Descricao;
  }
  public String getNm_Material() {
    return nm_Material;
  }
  public String getNm_Tipo() {
    return nm_Tipo;
  }
  public int getOid_Embalagem() {
    return oid_Embalagem;
  }
  public void setNm_Descricao(String nm_Descricao) {
    this.nm_Descricao = nm_Descricao;
  }
  public void setNm_Material(String nm_Material) {
    this.nm_Material = nm_Material;
  }
  public void setNm_Tipo(String nm_Tipo) {
    this.nm_Tipo = nm_Tipo;
  }
  public void setOid_Embalagem(int oid_Embalagem) {
    this.oid_Embalagem = oid_Embalagem;
  }
public double getNr_Peso_Bruto() {
	return nr_Peso_Bruto;
}
public void setNr_Peso_Bruto(double nr_Peso_Bruto) {
	this.nr_Peso_Bruto = nr_Peso_Bruto;
}
public double getNr_Peso_Liquido() {
	return nr_Peso_Liquido;
}
public void setNr_Peso_Liquido(double nr_Peso_Liquido) {
	this.nr_Peso_Liquido = nr_Peso_Liquido;
}


}