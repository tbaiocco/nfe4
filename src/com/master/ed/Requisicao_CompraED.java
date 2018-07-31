package com.master.ed;

/**
 * @Título: Requisicao_CompraED
 * @Descrição: Requisicao de Materiais - ED
 * @Data da criação: 11/2004
 * @Atualizado em: 11/2004
 * @Empresa: ÊxitoLogística Mastercom
 * @author: Teofilo Poletto Baiocco
 */

public class Requisicao_CompraED
    extends MasterED {

  private String dt_stamp;
  private String usuario_stamp;
  private String dm_stamp;
  private long oid_requisicao_compra;
  private long oid_unidade;
  private String nm_unidade;
  private String cd_unidade;
  private long oid_usuario;
  private String nm_usuario;
  private long oid_centro_custo;
  private long oid_modelo_nota_fiscal;
  private long oid_autorizador;
  private String nm_autorizador;
  private String nm_entrega;
  private String dt_requisicao;
  private String dm_status_requisicao;
  private String dm_item_requisicao;
  private long oid_item_requisicao_compra;
  private long oid_estoque;
  private String cd_estoque;
  private String nm_estoque;
  private double vl_quantidade;
  private String nm_observacoes;
  private String dt_entrega;
  private long nr_prazo_entrega;
  private String nr_contrato;
  private String dm_status_item;

  private double vl_quantidade_entrega;
  private double vl_estoque;

//  relatorio
  private java.util.Collection ProtocoloDetalhes;
  private String dataRel;
  private String siglaRel;
  private String centro_custo;

  public String getDm_stamp () {
    return dm_stamp;
  }

  public void setDm_stamp (String dm_stamp) {
    this.dm_stamp = dm_stamp;
  }

  public String getDm_status_item () {
    return dm_status_item;
  }

  public void setDm_status_item (String dm_status_item) {
    this.dm_status_item = dm_status_item;
  }

  public String getDm_status_requisicao () {
    return dm_status_requisicao;
  }

  public void setDm_status_requisicao (String dm_status_requisicao) {
    this.dm_status_requisicao = dm_status_requisicao;
  }

  public String getDt_entrega () {
    return dt_entrega;
  }

  public void setDt_entrega (String dt_entrega) {
    this.dt_entrega = dt_entrega;
  }

  public String getDt_requisicao () {
    return dt_requisicao;
  }

  public void setDt_requisicao (String dt_requisicao) {
    this.dt_requisicao = dt_requisicao;
  }

  public String getDt_stamp () {
    return dt_stamp;
  }

  public void setDt_stamp (String dt_stamp) {
    this.dt_stamp = dt_stamp;
  }

  public String getNm_entrega () {
    return nm_entrega;
  }

  public void setNm_entrega (String nm_entrega) {
    this.nm_entrega = nm_entrega;
  }

  public String getNm_observacoes () {
    return nm_observacoes;
  }

  public void setNm_observacoes (String nm_observacoes) {
    this.nm_observacoes = nm_observacoes;
  }

  public String getNr_contrato () {
    return nr_contrato;
  }

  public void setNr_contrato (String nr_contrato) {
    this.nr_contrato = nr_contrato;
  }

  public long getNr_prazo_entrega () {
    return nr_prazo_entrega;
  }

  public void setNr_prazo_entrega (long nr_prazo_entrega) {
    this.nr_prazo_entrega = nr_prazo_entrega;
  }

  public long getOid_autorizador () {
    return oid_autorizador;
  }

  public void setOid_autorizador (long oid_autorizador) {
    this.oid_autorizador = oid_autorizador;
  }

  public long getOid_centro_custo () {
    return oid_centro_custo;
  }

  public void setOid_centro_custo (long oid_centro_custo) {
    this.oid_centro_custo = oid_centro_custo;
  }

  public long getOid_estoque () {
    return oid_estoque;
  }

  public void setOid_estoque (long oid_estoque) {
    this.oid_estoque = oid_estoque;
  }

  public long getOid_item_requisicao_compra () {
    return oid_item_requisicao_compra;
  }

  public void setOid_item_requisicao_compra (long oid_item_requisicao_compra) {
    this.oid_item_requisicao_compra = oid_item_requisicao_compra;
  }

  public long getOid_modelo_nota_fiscal () {
    return oid_modelo_nota_fiscal;
  }

  public void setOid_modelo_nota_fiscal (long oid_modelo_nota_fiscal) {
    this.oid_modelo_nota_fiscal = oid_modelo_nota_fiscal;
  }

  public long getOid_requisicao_compra () {
    return oid_requisicao_compra;
  }

  public void setOid_requisicao_compra (long oid_requisicao_compra) {
    this.oid_requisicao_compra = oid_requisicao_compra;
  }

  public long getOid_unidade () {
    return oid_unidade;
  }

  public void setOid_unidade (long oid_unidade) {
    this.oid_unidade = oid_unidade;
  }

  public long getOid_usuario () {
    return oid_usuario;
  }

  public void setOid_usuario (long oid_usuario) {
    this.oid_usuario = oid_usuario;
  }

  public String getUsuario_stamp () {
    return usuario_stamp;
  }

  public void setUsuario_stamp (String usuario_stamp) {
    this.usuario_stamp = usuario_stamp;
  }

  public double getVl_quantidade () {
    return vl_quantidade;
  }

  public void setVl_quantidade (double vl_quantidade) {
    this.vl_quantidade = vl_quantidade;
  }

  public String getCd_unidade () {
    return cd_unidade;
  }

  public void setCd_unidade (String cd_unidade) {
    this.cd_unidade = cd_unidade;
  }

  public String getNm_autorizador () {
    return nm_autorizador;
  }

  public void setNm_autorizador (String nm_autorizador) {
    this.nm_autorizador = nm_autorizador;
  }

  public String getNm_usuario () {
    return nm_usuario;
  }

  public void setNm_usuario (String nm_usuario) {
    this.nm_usuario = nm_usuario;
  }

  public String getCd_estoque () {
    return cd_estoque;
  }

  public void setCd_estoque (String cd_estoque) {
    this.cd_estoque = cd_estoque;
  }

  public String getNm_estoque () {
    return nm_estoque;
  }

  public void setNm_estoque (String nm_estoque) {
    this.nm_estoque = nm_estoque;
  }

  public double getVl_quantidade_entrega () {
    return vl_quantidade_entrega;
  }

  public void setVl_quantidade_entrega (double vl_quantidade_entrega) {
    this.vl_quantidade_entrega = vl_quantidade_entrega;
  }

  public String getDataRel () {
    return dataRel;
  }

  public void setDataRel (String dataRel) {
    this.dataRel = dataRel;
  }

  public java.util.Collection getProtocoloDetalhes () {
    return ProtocoloDetalhes;
  }

  public void setProtocoloDetalhes (java.util.Collection protocoloDetalhes) {
    ProtocoloDetalhes = protocoloDetalhes;
  }

  public String getSiglaRel () {
    return siglaRel;
  }

  public void setSiglaRel (String siglaRel) {
    this.siglaRel = siglaRel;
  }

  public double getVl_estoque () {
    return vl_estoque;
  }

  public void setVl_estoque (double vl_estoque) {
    this.vl_estoque = vl_estoque;
  }

  public String getCentro_custo () {
    return centro_custo;
  }

  public void setCentro_custo (String centro_custo) {
    this.centro_custo = centro_custo;
  }

  public String getNm_unidade () {
    return nm_unidade;
  }

  public void setNm_unidade (String nm_unidade) {
    this.nm_unidade = nm_unidade;
  }

public String getDm_item_requisicao() {
	return dm_item_requisicao;
}

public void setDm_item_requisicao(String dm_item_requisicao) {
	this.dm_item_requisicao = dm_item_requisicao;
}
}