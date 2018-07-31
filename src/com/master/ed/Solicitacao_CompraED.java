package com.master.ed;

public class Solicitacao_CompraED
    extends MasterED {
  private String dt_stamp;
  private String usuario_stamp;
  private String dm_stamp;
  private long oid_Solicitacao_compra;
  private long oid_Ordem_Servico;
  private long oid_Movimento_Ordem_Servico;
  private long oid_Movimento_Estoque;
  private long oid_pedido;
  private long nr_pedido;
  private String nm_serie;
  private String dm_situacao_estoque;

  private String oid_Orcamento_Conta;
  private String nm_distribuidor;
  private String nm_contato;
  private String nm_comprador;
  private String nm_condicao_pagamento;
  private String tx_observacao;
  private String nm_tipo_pedido;
  private String dm_tipo_compra;
  private String nr_caixa;


  private long oid_unidade;
  private long nr_caixa2;

  private String cd_unidade;
  private String nm_unidade;
  private String nm_razao_social;
  private long nr_parcelas;
  private long oid_usuario;
  private String nm_usuario;
  private long oid_centro_custo;
  private String centro_custo;
  private long oid_modelo_nota_fiscal;
  private long oid_autorizador;
  private String nm_autorizador;
  private String nm_entrega;
  private String dt_Solicitacao;
  private String dm_status_Solicitacao;
  private long oid_item_Solicitacao_compra;
  private long oid_item_Solicitacao_entrega;
  private long oid_estoque;
  private String cd_estoque;
  private String nm_estoque;
  private double vl_quantidade;
  private double vl_quantidade_entrega;
  private double vl_item;
  private double pe_desconto;
  private double pe_aliquota_ipi;

  private double vl_pedido_entregue;
  private double vl_pedido_realizado;


  private double nr_qt_atendido;
  private String nm_observacoes;
  private String nm_produto;
  private String cd_produto;
  private String cd_fornecedor;
  private String cd_unidade_produto;
  private String dt_entrega;
  private long nr_prazo_entrega;
  private String nr_contrato;
  private String dm_status_item;
  private String cd_usuario_autorizador;
  private String conta;

  private long oid_Pedido_compra;
  private String dm_status_pedido;
  private String dt_pedido;
  private String dt_vencimento;
  private String dt_vencimento_minimo;
  private long oid_Cotacao_compra;
  private String dm_status_cotacao;
  private String dt_cotacao;
  private long quantidade_cotacoes_aceitas;
  private long quantidade_itens;

  private String oid_Pedido_compra_nota_fiscal;
  private String oid_nota_fiscal;
  private String nr_nota_fiscal;
  private String dt_entrada;
  private String dt_emissao;
  private String oid_romaneio_nota_fiscal;
  private String oid_localizacao;

  //relatorio
  private java.util.Collection RomaneioDetalhes;
  private java.util.Collection PedidoDetalhes;
  private java.util.Collection SolicitacaoDetalhes;
  private java.util.Collection Orcamento;
  private String dataRel;
  private String siglaRel;
  private String nm_endereco_entrega;
  private String nm_fornecedor;
  private String nm_endereco_fornecedor;
  private String nm_prazo_entrega;
  private String nm_cond_pgto;
  private double vl_unitario;
  private double vl_total_item;
  private double vl_total;
  private String oid_fornecedor;
  private double vl_total_produto_entrega;
  private double vl_total_produto_entrega_gerar_nota;
  private double vl_total_produto;
  private double vl_total_desconto;
  private double vl_total_ipi;
  private double vl_frete;

  private String dt_autorizacao;

  private long diasA_B;
  private long diasA_C;
  private long diasB_C;
  private long diasB_D;
  private long diasTotal;

  private double vl_servico;
  private String dt_servico;
  private String nr_documento;
  private double nr_quantidade;
  private long oid_movimento_ordem_servico;
  private long oid_ordem_servico;
  private String nm_status_Solicitacao;
  private String TX_Observacao;
  private long oid_Conta;
  private long oid_Conta_Servico;
  private String oid_Veiculo;
  private double vl_Pedido;
  private double vl_Pedido_Conta1;
  private double vl_Pedido_Conta2;
  private double vl_Orcamento_Compra;
  private double vl_Parcelamento;
  private double vl_Parcela_Entrada;

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

  public String getDm_status_Solicitacao () {
    return dm_status_Solicitacao;
  }

  public void setDm_status_Solicitacao (String dm_status_Solicitacao) {
    this.dm_status_Solicitacao = dm_status_Solicitacao;
  }

  public String getDt_entrega () {
    return dt_entrega;
  }

  public void setDt_entrega (String dt_entrega) {
    this.dt_entrega = dt_entrega;
  }

  public String getDt_Solicitacao () {
    return dt_Solicitacao;
  }

  public void setDt_Solicitacao (String dt_Solicitacao) {
    this.dt_Solicitacao = dt_Solicitacao;
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

  public long getOid_item_Solicitacao_compra () {
    return oid_item_Solicitacao_compra;
  }

  public void setOid_item_Solicitacao_compra (long oid_item_Solicitacao_compra) {
    this.oid_item_Solicitacao_compra = oid_item_Solicitacao_compra;
  }

  public long getOid_modelo_nota_fiscal () {
    return oid_modelo_nota_fiscal;
  }

  public void setOid_modelo_nota_fiscal (long oid_modelo_nota_fiscal) {
    this.oid_modelo_nota_fiscal = oid_modelo_nota_fiscal;
  }

  public long getOid_Solicitacao_compra () {
    return oid_Solicitacao_compra;
  }

  public void setOid_Solicitacao_compra (long oid_Solicitacao_compra) {
    this.oid_Solicitacao_compra = oid_Solicitacao_compra;
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

  public String getNm_produto () {
    return nm_produto;
  }

  public void setNm_produto (String nm_produto) {
    this.nm_produto = nm_produto;
  }

  public String getCd_usuario_autorizador () {
    return cd_usuario_autorizador;
  }

  public void setCd_usuario_autorizador (String cd_usuario_autorizador) {
    this.cd_usuario_autorizador = cd_usuario_autorizador;
  }

  public String getDm_status_pedido () {
    return dm_status_pedido;
  }

  public void setDm_status_pedido (String dm_status_pedido) {
    this.dm_status_pedido = dm_status_pedido;
  }

  public String getDt_pedido () {
    return dt_pedido;
  }

  public void setDt_pedido (String dt_pedido) {
    this.dt_pedido = dt_pedido;
  }

  public long getOid_Pedido_compra () {
    return oid_Pedido_compra;
  }

  public void setOid_Pedido_compra (long oid_Pedido_compra) {
    this.oid_Pedido_compra = oid_Pedido_compra;
  }

  public String getDm_status_cotacao () {
    return dm_status_cotacao;
  }

  public void setDm_status_cotacao (String dm_status_cotacao) {
    this.dm_status_cotacao = dm_status_cotacao;
  }

  public String getDt_cotacao () {
    return dt_cotacao;
  }

  public void setDt_cotacao (String dt_cotacao) {
    this.dt_cotacao = dt_cotacao;
  }

  public long getOid_Cotacao_compra () {
    return oid_Cotacao_compra;
  }

  public void setOid_Cotacao_compra (long oid_Cotacao_compra) {
    this.oid_Cotacao_compra = oid_Cotacao_compra;
  }

  public long getQuantidade_cotacoes_aceitas () {
    return quantidade_cotacoes_aceitas;
  }

  public void setQuantidade_cotacoes_aceitas (long quantidade_cotacoes_aceitas) {
    this.quantidade_cotacoes_aceitas = quantidade_cotacoes_aceitas;
  }

  public long getQuantidade_itens () {
    return quantidade_itens;
  }

  public void setQuantidade_itens (long quantidade_itens) {
    this.quantidade_itens = quantidade_itens;
  }

  public String getOid_nota_fiscal () {
    return oid_nota_fiscal;
  }

  public void setOid_nota_fiscal (String oid_nota_fiscal) {
    this.oid_nota_fiscal = oid_nota_fiscal;
  }

  public String getOid_Pedido_compra_nota_fiscal () {
    return oid_Pedido_compra_nota_fiscal;
  }

  public void setOid_Pedido_compra_nota_fiscal (
      String oid_Pedido_compra_nota_fiscal) {
    this.oid_Pedido_compra_nota_fiscal = oid_Pedido_compra_nota_fiscal;
  }

  public String getNr_nota_fiscal () {
    return nr_nota_fiscal;
  }

  public void setNr_nota_fiscal (String nr_nota_fiscal) {
    this.nr_nota_fiscal = nr_nota_fiscal;
  }

  public String getOid_localizacao () {
    return oid_localizacao;
  }

  public void setOid_localizacao (String oid_localizacao) {
    this.oid_localizacao = oid_localizacao;
  }

  public String getOid_romaneio_nota_fiscal () {
    return oid_romaneio_nota_fiscal;
  }

  public void setOid_romaneio_nota_fiscal (String oid_romaneio_nota_fiscal) {
    this.oid_romaneio_nota_fiscal = oid_romaneio_nota_fiscal;
  }

  public String getDataRel () {
    return dataRel;
  }

  public void setDataRel (String dataRel) {
    this.dataRel = dataRel;
  }

  public String getSiglaRel () {
    return siglaRel;
  }

  public void setSiglaRel (String siglaRel) {
    this.siglaRel = siglaRel;
  }

  public java.util.Collection getRomaneioDetalhes () {
    return RomaneioDetalhes;
  }

  public void setRomaneioDetalhes (java.util.Collection romaneioDetalhes) {
    RomaneioDetalhes = romaneioDetalhes;
  }

  public String getCentro_custo () {
    return centro_custo;
  }

  public void setCentro_custo (String centro_custo) {
    this.centro_custo = centro_custo;
  }

  public String getNm_cond_pgto () {
    return nm_cond_pgto;
  }

  public void setNm_cond_pgto (String nm_cond_pgto) {
    this.nm_cond_pgto = nm_cond_pgto;
  }

  public String getNm_endereco_entrega () {
    return nm_endereco_entrega;
  }

  public void setNm_endereco_entrega (String nm_endereco_entrega) {
    this.nm_endereco_entrega = nm_endereco_entrega;
  }

  public String getNm_endereco_fornecedor () {
    return nm_endereco_fornecedor;
  }

  public void setNm_endereco_fornecedor (String nm_endereco_fornecedor) {
    this.nm_endereco_fornecedor = nm_endereco_fornecedor;
  }

  public String getNm_fornecedor () {
    return nm_fornecedor;
  }

  public void setNm_fornecedor (String nm_fornecedor) {
    this.nm_fornecedor = nm_fornecedor;
  }

  public String getNm_prazo_entrega () {
    return nm_prazo_entrega;
  }

  public void setNm_prazo_entrega (String nm_prazo_entrega) {
    this.nm_prazo_entrega = nm_prazo_entrega;
  }

  public String getNm_unidade () {
    return nm_unidade;
  }

  public void setNm_unidade (String nm_unidade) {
    this.nm_unidade = nm_unidade;
  }

  public double getVl_total () {
    return vl_total;
  }

  public void setVl_total (double vl_total) {
    this.vl_total = vl_total;
  }

  public double getVl_total_item () {
    return vl_total_item;
  }

  public void setVl_total_item (double vl_total_item) {
    this.vl_total_item = vl_total_item;
  }

  public double getVl_unitario () {
    return vl_unitario;
  }

  public void setVl_unitario (double vl_unitario) {
    this.vl_unitario = vl_unitario;
  }

  public java.util.Collection getPedidoDetalhes () {
    return PedidoDetalhes;
  }

  public void setPedidoDetalhes (java.util.Collection pedidoDetalhes) {
    PedidoDetalhes = pedidoDetalhes;
  }

  public String getOid_fornecedor () {
    return oid_fornecedor;
  }

  public void setOid_fornecedor (String oid_fornecedor) {
    this.oid_fornecedor = oid_fornecedor;
  }

  public java.util.Collection getSolicitacaoDetalhes () {
    return SolicitacaoDetalhes;
  }

  public void setSolicitacaoDetalhes (java.util.Collection solicitacaoDetalhes) {
    SolicitacaoDetalhes = solicitacaoDetalhes;
  }

  public long getDiasA_B () {
    return diasA_B;
  }

  public void setDiasA_B (long diasA_B) {
    this.diasA_B = diasA_B;
  }

  public long getDiasA_C () {
    return diasA_C;
  }

  public void setDiasA_C (long diasA_C) {
    this.diasA_C = diasA_C;
  }

  public long getDiasB_C () {
    return diasB_C;
  }

  public void setDiasB_C (long diasB_C) {
    this.diasB_C = diasB_C;
  }

  public long getDiasB_D () {
    return diasB_D;
  }

  public void setDiasB_D (long diasB_D) {
    this.diasB_D = diasB_D;
  }

  public long getDiasTotal () {
    return diasTotal;
  }

  public void setDiasTotal (long diasTotal) {
    this.diasTotal = diasTotal;
  }

  public String getDt_emissao () {
    return dt_emissao;
  }

  public void setDt_emissao (String dt_emissao) {
    this.dt_emissao = dt_emissao;
  }

  public String getDt_entrada () {
    return dt_entrada;
  }

  public void setDt_entrada (String dt_entrada) {
    this.dt_entrada = dt_entrada;
  }

  public String getDt_autorizacao () {
    return dt_autorizacao;
  }

  public void setDt_autorizacao (String dt_autorizacao) {
    this.dt_autorizacao = dt_autorizacao;
  }

  public String getDt_servico () {
    return dt_servico;
  }

  public void setDt_servico (String dt_servico) {
    this.dt_servico = dt_servico;
  }

  public String getNr_documento () {
    return nr_documento;
  }

  public void setNr_documento (String nr_documento) {
    this.nr_documento = nr_documento;
  }

  public double getNr_quantidade () {
    return nr_quantidade;
  }

  public void setNr_quantidade (double nr_quantidade) {
    this.nr_quantidade = nr_quantidade;
  }

  public double getVl_servico () {
    return vl_servico;
  }

  public void setVl_servico (double vl_servico) {
    this.vl_servico = vl_servico;
  }

  public long getOid_movimento_ordem_servico () {
    return oid_movimento_ordem_servico;
  }

  public void setOid_movimento_ordem_servico (long oid_movimento_ordem_servico) {
    this.oid_movimento_ordem_servico = oid_movimento_ordem_servico;
  }

  public long getOid_ordem_servico () {
    return oid_ordem_servico;
  }

  public String getNm_status_Solicitacao () {
    return nm_status_Solicitacao;
  }

  public long getOid_Conta () {
    return oid_Conta;
  }

  public String getOid_Veiculo () {
    return oid_Veiculo;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setOid_ordem_servico (long oid_ordem_servico) {
    this.oid_ordem_servico = oid_ordem_servico;
  }

  public void setNm_status_Solicitacao (String nm_status_Solicitacao) {
    this.nm_status_Solicitacao = nm_status_Solicitacao;
  }

  public void setOid_Conta (long oid_Conta) {
    this.oid_Conta = oid_Conta;
  }

  public void setOid_Veiculo (String oid_Veiculo) {
    this.oid_Veiculo = oid_Veiculo;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public String getConta () {
    return conta;
  }

  public double getVl_Pedido () {
    return vl_Pedido;
  }

  public double getvl_Orcamento_Compra () {
    return vl_Orcamento_Compra;
  }

  public void setConta (String conta) {
    this.conta = conta;
  }

  public void setVl_Pedido (double vl_Pedido) {
    this.vl_Pedido = vl_Pedido;

  }

  public java.util.Collection getOrcamento () {
    return Orcamento;
  }

  public void setvl_Orcamento_Compra (double vl_Orcamento_Compra) {
    this.vl_Orcamento_Compra = vl_Orcamento_Compra;
  }

  public void setOrcamento (java.util.Collection orcamento) {
    Orcamento = orcamento;
  }

public String getCd_produto() {
	return cd_produto;
}

public void setCd_produto(String cd_produto) {
	this.cd_produto = cd_produto;
}

public String getCd_fornecedor() {
	return cd_fornecedor;
}

public void setCd_fornecedor(String cd_fornecedor) {
	this.cd_fornecedor = cd_fornecedor;
}

public String getCd_unidade_produto() {
	return cd_unidade_produto;
}

public void setCd_unidade_produto(String cd_unidade_produto) {
	this.cd_unidade_produto = cd_unidade_produto;
}

public double getNr_qt_atendido() {
	return nr_qt_atendido;
}

public void setNr_qt_atendido(double nr_qt_atendido) {
	this.nr_qt_atendido = nr_qt_atendido;
}

public double getVl_Orcamento_Compra() {
	return vl_Orcamento_Compra;
}

public void setVl_Orcamento_Compra(double vl_Orcamento_Compra) {
	this.vl_Orcamento_Compra = vl_Orcamento_Compra;
}

public double getVl_item() {
	return vl_item;
}

public void setVl_item(double vl_item) {
	this.vl_item = vl_item;
}

public double getPe_desconto() {
	return pe_desconto;
}

public void setPe_desconto(double pe_desconto) {
	this.pe_desconto = pe_desconto;
}

public double getPe_aliquota_ipi() {
	return pe_aliquota_ipi;
}

public void setPe_aliquota_ipi(double pe_aliquota_ipi) {
	this.pe_aliquota_ipi = pe_aliquota_ipi;
}

public String getNm_razao_social() {
	return nm_razao_social;
}

public void setNm_razao_social(String nm_razao_social) {
	this.nm_razao_social = nm_razao_social;
}

public long getOid_pedido() {
	return oid_pedido;
}

public void setOid_pedido(long oid_pedido) {
	this.oid_pedido = oid_pedido;
}

public String getNm_comprador() {
	return nm_comprador;
}

public void setNm_comprador(String nm_comprador) {
	this.nm_comprador = nm_comprador;
}

public String getNm_condicao_pagamento() {
	return nm_condicao_pagamento;
}

public void setNm_condicao_pagamento(String nm_condicao_pagamento) {
	this.nm_condicao_pagamento = nm_condicao_pagamento;
}

public String getNm_contato() {
	return nm_contato;
}

public void setNm_contato(String nm_contato) {
	this.nm_contato = nm_contato;
}

public String getNm_distribuidor() {
	return nm_distribuidor;
}

public void setNm_distribuidor(String nm_distribuidor) {
	this.nm_distribuidor = nm_distribuidor;
}

public String getNm_tipo_pedido() {
	return nm_tipo_pedido;
}

public void setNm_tipo_pedido(String nm_tipo_pedido) {
	this.nm_tipo_pedido = nm_tipo_pedido;
}

public String getNr_caixa() {
	return nr_caixa;
}

public void setNr_caixa(String nr_caixa) {
	this.nr_caixa = nr_caixa;
}

public long getNr_pedido() {
	return nr_pedido;
}

public void setNr_pedido(long nr_pedido) {
	this.nr_pedido = nr_pedido;
}

public String getTx_observacao() {
	return tx_observacao;
}

public void setTx_observacao(String tx_observacao) {
	this.tx_observacao = tx_observacao;
}

public double getVl_frete() {
	return vl_frete;
}

public void setVl_frete(double vl_frete) {
	this.vl_frete = vl_frete;
}

public double getVl_total_desconto() {
	return vl_total_desconto;
}

public void setVl_total_desconto(double vl_total_desconto) {
	this.vl_total_desconto = vl_total_desconto;
}

public double getVl_total_ipi() {
	return vl_total_ipi;
}

public void setVl_total_ipi(double vl_total_ipi) {
	this.vl_total_ipi = vl_total_ipi;
}

public double getVl_total_produto() {
	return vl_total_produto;
}

public void setVl_total_produto(double vl_total_produto) {
	this.vl_total_produto = vl_total_produto;
}

public long getNr_caixa2() {
	return nr_caixa2;
}

public void setNr_caixa2(long nr_caixa2) {
	this.nr_caixa2 = nr_caixa2;
}

public String getDm_tipo_compra() {
	return dm_tipo_compra;
}

public void setDm_tipo_compra(String dm_tipo_compra) {
	this.dm_tipo_compra = dm_tipo_compra;
}

public String getOid_Orcamento_Conta() {
	return oid_Orcamento_Conta;
}

public void setOid_Orcamento_Conta(String oid_Orcamento_Conta) {
	this.oid_Orcamento_Conta = oid_Orcamento_Conta;
}

public double getVl_quantidade_entrega() {
	return vl_quantidade_entrega;
}

public void setVl_quantidade_entrega(double vl_quantidade_entrega) {
	this.vl_quantidade_entrega = vl_quantidade_entrega;
}

public long getOid_Ordem_Servico() {
	return oid_Ordem_Servico;
}

public void setOid_Ordem_Servico(long oid_Ordem_Servico) {
	this.oid_Ordem_Servico = oid_Ordem_Servico;
}

public long getOid_item_Solicitacao_entrega() {
	return oid_item_Solicitacao_entrega;
}

public void setOid_item_Solicitacao_entrega(long oid_item_Solicitacao_entrega) {
	this.oid_item_Solicitacao_entrega = oid_item_Solicitacao_entrega;
}

public double getVl_total_produto_entrega() {
	return vl_total_produto_entrega;
}

public void setVl_total_produto_entrega(double vl_total_produto_entrega) {
	this.vl_total_produto_entrega = vl_total_produto_entrega;
}

public double getVl_total_produto_entrega_gerar_nota() {
	return vl_total_produto_entrega_gerar_nota;
}

public void setVl_total_produto_entrega_gerar_nota(
		double vl_total_produto_entrega_gerar_nota) {
	this.vl_total_produto_entrega_gerar_nota = vl_total_produto_entrega_gerar_nota;
}

public String getNm_serie() {
	return nm_serie;
}

public void setNm_serie(String nm_serie) {
	this.nm_serie = nm_serie;
}

public long getOid_Movimento_Estoque() {
	return oid_Movimento_Estoque;
}

public void setOid_Movimento_Estoque(long oid_Movimento_Estoque) {
	this.oid_Movimento_Estoque = oid_Movimento_Estoque;
}

public long getOid_Movimento_Ordem_Servico() {
	return oid_Movimento_Ordem_Servico;
}

public void setOid_Movimento_Ordem_Servico(long oid_Movimento_Ordem_Servico) {
	this.oid_Movimento_Ordem_Servico = oid_Movimento_Ordem_Servico;
}

public String getDm_situacao_estoque() {
	return dm_situacao_estoque;
}

public void setDm_situacao_estoque(String dm_situacao_estoque) {
	this.dm_situacao_estoque = dm_situacao_estoque;
}

public long getNr_parcelas() {
	return nr_parcelas;
}

public void setNr_parcelas(long nr_parcelas) {
	this.nr_parcelas = nr_parcelas;
}

public String getDt_vencimento() {
	return dt_vencimento;
}

public void setDt_vencimento(String dt_vencimento) {
	this.dt_vencimento = dt_vencimento;
}

public double getVl_pedido_entregue() {
	return vl_pedido_entregue;
}

public void setVl_pedido_entregue(double vl_pedido_entregue) {
	this.vl_pedido_entregue = vl_pedido_entregue;
}

public double getVl_pedido_realizado() {
	return vl_pedido_realizado;
}

public void setVl_pedido_realizado(double vl_pedido_realizado) {
	this.vl_pedido_realizado = vl_pedido_realizado;
}

public long getOid_Conta_Servico() {
	return oid_Conta_Servico;
}

public void setOid_Conta_Servico(long oid_Conta_Servico) {
	this.oid_Conta_Servico = oid_Conta_Servico;
}

public double getVl_Pedido_Conta2() {
	return vl_Pedido_Conta2;
}

public void setVl_Pedido_Conta2(double vl_Pedido_Conta2) {
	this.vl_Pedido_Conta2 = vl_Pedido_Conta2;
}

public double getVl_Pedido_Conta1() {
	return vl_Pedido_Conta1;
}

public void setVl_Pedido_Conta1(double vl_Pedido_Conta1) {
	this.vl_Pedido_Conta1 = vl_Pedido_Conta1;
}

public double getVl_Parcelamento() {
	return vl_Parcelamento;
}

public void setVl_Parcelamento(double vl_Parcelamento) {
	this.vl_Parcelamento = vl_Parcelamento;
}

public double getVl_Parcela_Entrada() {
	return vl_Parcela_Entrada;
}

public void setVl_Parcela_Entrada(double vl_Parcela_Entrada) {
	this.vl_Parcela_Entrada = vl_Parcela_Entrada;
}

public String getDt_vencimento_minimo() {
	return dt_vencimento_minimo;
}

public void setDt_vencimento_minimo(String dt_vencimento_minimo) {
	this.dt_vencimento_minimo = dt_vencimento_minimo;
}
}