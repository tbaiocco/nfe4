package com.master.ed;

/**
 * @Título: Cotacao_CompraED
 * @Descrição: Cotacao de Compras - ED
 * @Data da criação: 11/2004
 * @Atualizado em: 11/2004
 * @Empresa: ÊxitoLogística Mastercom
 * @author: Teofilo Poletto Baiocco
 */

public class Cotacao_CompraED
    extends MasterED {

  private String dt_stamp;
  private String usuario_stamp;
  private String dm_stamp;
  private long oid_Solicitacao_compra;
  private long oid_item_Solicitacao_compra;
  private long oid_cotacao_compra;
  private String nm_observacoes;

  private long oid_item_cotacao_compra;
  private String oid_fornecedor;
  private String nm_fornecedor;
  private double vl_preco;
  private String condicao_pgto;
  private String dm_frete;
  private int nr_prazo_entrega;
  private String dm_status_item;
  private String dt_cotacao;

  private long oid_estoque;
  private String cd_estoque;
  private String nm_estoque;
  private String nm_produto;
  private double vl_quantidade;

  public String getDm_stamp () {
    return dm_stamp;
  }

  public void setDm_stamp (String dm_stamp) {
    this.dm_stamp = dm_stamp;
  }

  public String getDt_stamp () {
    return dt_stamp;
  }

  public void setDt_stamp (String dt_stamp) {
    this.dt_stamp = dt_stamp;
  }

  public String getNm_observacoes () {
    return nm_observacoes;
  }

  public void setNm_observacoes (String nm_observacoes) {
    this.nm_observacoes = nm_observacoes;
  }

  public long getOid_cotacao_compra () {
    return oid_cotacao_compra;
  }

  public void setOid_cotacao_compra (long oid_cotacao) {
    this.oid_cotacao_compra = oid_cotacao;
  }

  public long getOid_item_Solicitacao_compra () {
    return oid_item_Solicitacao_compra;
  }

  public void setOid_item_Solicitacao_compra (long oid_item_Solicitacao_compra) {
    this.oid_item_Solicitacao_compra = oid_item_Solicitacao_compra;
  }

  public long getOid_Solicitacao_compra () {
    return oid_Solicitacao_compra;
  }

  public void setOid_Solicitacao_compra (long oid_Solicitacao_compra) {
    this.oid_Solicitacao_compra = oid_Solicitacao_compra;
  }

  public String getUsuario_stamp () {
    return usuario_stamp;
  }

  public void setUsuario_stamp (String usuario_stamp) {
    this.usuario_stamp = usuario_stamp;
  }

  public String getCondicao_pgto () {
    return condicao_pgto;
  }

  public void setCondicao_pgto (String condicao_pgto) {
    this.condicao_pgto = condicao_pgto;
  }

  public String getDm_frete () {
    return dm_frete;
  }

  public void setDm_frete (String dm_frete) {
    this.dm_frete = dm_frete;
  }

  public String getDm_status_item () {
    return dm_status_item;
  }

  public void setDm_status_item (String dm_status_item) {
    this.dm_status_item = dm_status_item;
  }

  public int getNr_prazo_entrega () {
    return nr_prazo_entrega;
  }

  public void setNr_prazo_entrega (int nr_prazo_entrega) {
    this.nr_prazo_entrega = nr_prazo_entrega;
  }

  public String getOid_fornecedor () {
    return oid_fornecedor;
  }

  public void setOid_fornecedor (String oid_fornecedor) {
    this.oid_fornecedor = oid_fornecedor;
  }

  public long getOid_item_cotacao_compra () {
    return oid_item_cotacao_compra;
  }

  public void setOid_item_cotacao_compra (long oid_item_cotacao_compra) {
    this.oid_item_cotacao_compra = oid_item_cotacao_compra;
  }

  public double getVl_preco () {
    return vl_preco;
  }

  public void setVl_preco (double vl_preco) {
    this.vl_preco = vl_preco;
  }

  public String getDt_cotacao () {
    return dt_cotacao;
  }

  public void setDt_cotacao (String dt_cotacao) {
    this.dt_cotacao = dt_cotacao;
  }

  public String getNm_fornecedor () {
    return nm_fornecedor;
  }

  public void setNm_fornecedor (String nm_fornecedor) {
    this.nm_fornecedor = nm_fornecedor;
  }

  public long getOid_estoque () {
    return oid_estoque;
  }

  public void setOid_estoque (long oid_estoque) {
    this.oid_estoque = oid_estoque;
  }

  public String getNm_produto () {
    return nm_produto;
  }

  public void setNm_produto (String nm_produto) {
    this.nm_produto = nm_produto;
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

  public double getVl_quantidade () {
    return vl_quantidade;
  }

  public void setVl_quantidade (double vl_quantidade) {
    this.vl_quantidade = vl_quantidade;
  }
}