package com.master.ed;

public class EstoqueED {

  public long oid_estoque;
  public String nm_estoque;
  public String nm_referencia;
  public double vl_custo;
  public long oid_unidade_produto;
  public int oid_sub_grupo_estoque;
  public double vl_custo_minimo;
  public double vl_custo_maximo;
  public double vl_estoque_minimo;
  public double vl_estoque_maximo;
  public double vl_estoque;
  public String dm_status;
  public String dm_tipo_produto;
  public String nm_classificacao_fiscal;
  public String dm_negativo;
  public long nr_leadtime;
  public long nr_leadtime_maximo;
  public String cd_estoque;
  public long oid_produto;
  public int oid_grupo_estoque;
  public long nr_proximo_item;
  public long oid_item_fornecedor;
  public String oid_fornecedor;
  public String nm_fornecedor;
  public String nm_aplicacao;

  public String produto_cliente;
  public String dm_preferencial;

  public double vl_medio;

  public String oid_Solicitacao_Compra;
  public String nr_Nota_Fiscal;
  public String oid_Nota_Fiscal;
  public String dt_Nota_Fiscal;
  public String dt_Entrada;
  public double vl_Quantidade;
  public double vl_Unitario;

  public String oid_Requisicao_Compra;
  public String dt_Requisicao;
  public double vl_Quantidade_Entrega;

  private String centro_custo;

  public String dt_ultima_compra;
  public double vl_ultima_compra;

  public double vl_lista_preco;
  private String DM_Relatorio;
  private String DT_Inicial;
  private String DT_Final;
  private String NR_Placa;
  private String DM_Tipo_Movimento;
  private String DM_Movimento;


  private String CD_Deposito;
  private String NM_Rua;
  private String NR_Endereco;
  private String NR_Apartamento;
  private String NM_Grupo_Estoque;


  private int oid_Usuario;
  private String NM_Usuario;
  private int NR_Dias_Contagem;
  private double NR_Quantidade_Tolerancia;
  private String DT_Contagem;
  private String HR_Contagem;
  private double NR_Quantidade_Contagem;
  private String DM_Divergencia;
  private String DT_Ultima_Contagem;
  private double NR_Divergencia_Ultima_Contagem;
  
  public String nm_referencia2;
  public String nm_referencia3;
  public String nm_referencia4;
  public String nm_referencia5;
  public String nm_referencia6;

  public String getDT_Ultima_Contagem() {
	return DT_Ultima_Contagem;
}

public void setDT_Ultima_Contagem(String ultima_Contagem) {
	DT_Ultima_Contagem = ultima_Contagem;
}

public double getNR_Divergencia_Ultima_Contagem() {
	return NR_Divergencia_Ultima_Contagem;
}

public void setNR_Divergencia_Ultima_Contagem(double divergencia_Ultima_Contagem) {
	NR_Divergencia_Ultima_Contagem = divergencia_Ultima_Contagem;
}

public String getDM_Divergencia() {
	return DM_Divergencia;
}

public void setDM_Divergencia(String divergencia) {
	DM_Divergencia = divergencia;
}

public String getDT_Contagem() {
	return DT_Contagem;
}

public void setDT_Contagem(String contagem) {
	DT_Contagem = contagem;
}

public String getHR_Contagem() {
	return HR_Contagem;
}

public void setHR_Contagem(String contagem) {
	HR_Contagem = contagem;
}

public double getNR_Quantidade_Contagem() {
	return NR_Quantidade_Contagem;
}

public void setNR_Quantidade_Contagem(double quantidade_Contagem) {
	NR_Quantidade_Contagem = quantidade_Contagem;
}

public int getNR_Dias_Contagem() {
	return NR_Dias_Contagem;
}

public void setNR_Dias_Contagem(int dias_Contagem) {
	NR_Dias_Contagem = dias_Contagem;
}

public double getNR_Quantidade_Tolerancia() {
	return NR_Quantidade_Tolerancia;
}

public void setNR_Quantidade_Tolerancia(double quantidade_Tolerancia) {
	NR_Quantidade_Tolerancia = quantidade_Tolerancia;
}

public String getNM_Grupo_Estoque() {
	return NM_Grupo_Estoque;
}

public void setNM_Grupo_Estoque(String grupo_Estoque) {
	NM_Grupo_Estoque = grupo_Estoque;
}

public String getCD_Deposito() {
	return CD_Deposito;
}

public void setCD_Deposito(String deposito) {
	CD_Deposito = deposito;
}

public String getNM_Rua() {
	return NM_Rua;
}

public void setNM_Rua(String rua) {
	NM_Rua = rua;
}

public String getNR_Apartamento() {
	return NR_Apartamento;
}

public void setNR_Apartamento(String apartamento) {
	NR_Apartamento = apartamento;
}

public String getNR_Endereco() {
	return NR_Endereco;
}

public void setNR_Endereco(String endereco) {
	NR_Endereco = endereco;
}

public String getCd_estoque () {
    return cd_estoque;
  }

  public void setCd_estoque (String cd_estoque) {
    this.cd_estoque = cd_estoque;
  }

  public String getDm_negativo () {
    return dm_negativo;
  }

  public void setDm_negativo (String dm_negativo) {
    this.dm_negativo = dm_negativo;
  }

  public String getDm_status () {
    return dm_status;
  }

  public void setDm_status (String dm_status) {
    this.dm_status = dm_status;
  }

  public String getDm_tipo_produto () {
    return dm_tipo_produto;
  }

  public void setDm_tipo_produto (String dm_tipo_produto) {
    this.dm_tipo_produto = dm_tipo_produto;
  }

  public String getNm_classificacao_fiscal () {
    return nm_classificacao_fiscal;
  }

  public void setNm_classificacao_fiscal (String nm_classificacao_fiscal) {
    this.nm_classificacao_fiscal = nm_classificacao_fiscal;
  }

  public String getNm_estoque () {
    return nm_estoque;
  }

  public void setNm_estoque (String nm_estoque) {
    this.nm_estoque = nm_estoque;
  }

  public String getNm_referencia () {
    return nm_referencia;
  }

  public void setNm_referencia (String nm_referencia) {
    this.nm_referencia = nm_referencia;
  }

  public long getNr_leadtime () {
    return nr_leadtime;
  }

  public void setNr_leadtime (long nr_leadtime) {
    this.nr_leadtime = nr_leadtime;
  }

  public long getNr_leadtime_maximo () {
    return nr_leadtime_maximo;
  }

  public void setNr_leadtime_maximo (long nr_leadtime_maximo) {
    this.nr_leadtime_maximo = nr_leadtime_maximo;
  }

  public long getOid_estoque () {
    return oid_estoque;
  }

  public void setOid_estoque (long oid_estoque) {
    this.oid_estoque = oid_estoque;
  }

  public long getOid_produto () {
    return oid_produto;
  }

  public void setOid_produto (long oid_produto) {
    this.oid_produto = oid_produto;
  }

  public int getOid_sub_grupo_estoque () {
    return oid_sub_grupo_estoque;
  }

  public void setOid_sub_grupo_estoque (int oid_sub_grupo_estoque) {
    this.oid_sub_grupo_estoque = oid_sub_grupo_estoque;
  }

  public long getOid_unidade_produto () {
    return oid_unidade_produto;
  }

  public void setOid_unidade_produto (long oid_unidade_produto) {
    this.oid_unidade_produto = oid_unidade_produto;
  }

  public double getVl_custo () {
    return vl_custo;
  }

  public void setVl_custo (double vl_custo) {
    this.vl_custo = vl_custo;
  }

  public double getVl_custo_maximo () {
    return vl_custo_maximo;
  }

  public void setVl_custo_maximo (double vl_custo_maximo) {
    this.vl_custo_maximo = vl_custo_maximo;
  }

  public double getVl_custo_minimo () {
    return vl_custo_minimo;
  }

  public void setVl_custo_minimo (double vl_custo_minimo) {
    this.vl_custo_minimo = vl_custo_minimo;
  }

  public double getVl_estoque_maximo () {
    return vl_estoque_maximo;
  }

  public void setVl_estoque_maximo (double vl_estoque_maximo) {
    this.vl_estoque_maximo = vl_estoque_maximo;
  }

  public double getVl_estoque_minimo () {
    return vl_estoque_minimo;
  }

  public void setVl_estoque_minimo (double vl_estoque_minimo) {
    this.vl_estoque_minimo = vl_estoque_minimo;
  }

  public int getOid_grupo_estoque () {
    return oid_grupo_estoque;
  }

  public void setOid_grupo_estoque (int oid_grupo_estoque) {
    this.oid_grupo_estoque = oid_grupo_estoque;
  }

  public long getNr_proximo_item () {
    return nr_proximo_item;
  }

  public void setNr_proximo_item (long nr_proximo_item) {
    this.nr_proximo_item = nr_proximo_item;
  }

  public String getOid_fornecedor () {
    return oid_fornecedor;
  }

  public void setOid_fornecedor (String oid_fornecedor) {
    this.oid_fornecedor = oid_fornecedor;
  }

  public long getOid_item_fornecedor () {
    return oid_item_fornecedor;
  }

  public void setOid_item_fornecedor (long oid_item_fornecedor) {
    this.oid_item_fornecedor = oid_item_fornecedor;
  }

  public String getNm_fornecedor () {
    return nm_fornecedor;
  }

  public void setNm_fornecedor (String nm_fornecedor) {
    this.nm_fornecedor = nm_fornecedor;
  }

  public String getNm_aplicacao () {
    return nm_aplicacao;
  }

  public void setNm_aplicacao (String nm_aplicacao) {
    this.nm_aplicacao = nm_aplicacao;
  }

  public String getProduto_cliente () {
    return produto_cliente;
  }

  public void setProduto_cliente (String produto_cliente) {
    this.produto_cliente = produto_cliente;
  }

  public double getVl_estoque () {
    return vl_estoque;
  }

  public void setVl_estoque (double vl_estoque) {
    this.vl_estoque = vl_estoque;
  }

  public String getDm_preferencial () {
    return dm_preferencial;
  }

  public void setDm_preferencial (String dm_preferencial) {
    this.dm_preferencial = dm_preferencial;
  }

  public String getDt_Entrada () {
    return dt_Entrada;
  }

  public void setDt_Entrada (String dt_Entrada) {
    this.dt_Entrada = dt_Entrada;
  }

  public String getDt_Nota_Fiscal () {
    return dt_Nota_Fiscal;
  }

  public void setDt_Nota_Fiscal (String dt_Nota_Fiscal) {
    this.dt_Nota_Fiscal = dt_Nota_Fiscal;
  }

  public String getDt_Requisicao () {
    return dt_Requisicao;
  }

  public void setDt_Requisicao (String dt_Requisicao) {
    this.dt_Requisicao = dt_Requisicao;
  }

  public String getDt_ultima_compra () {
    return dt_ultima_compra;
  }

  public void setDt_ultima_compra (String dt_ultima_compra) {
    this.dt_ultima_compra = dt_ultima_compra;
  }

  public String getNr_Nota_Fiscal () {
    return nr_Nota_Fiscal;
  }

  public void setNr_Nota_Fiscal (String nr_Nota_Fiscal) {
    this.nr_Nota_Fiscal = nr_Nota_Fiscal;
  }

  public String getOid_Nota_Fiscal () {
    return oid_Nota_Fiscal;
  }

  public void setOid_Nota_Fiscal (String oid_Nota_Fiscal) {
    this.oid_Nota_Fiscal = oid_Nota_Fiscal;
  }

  public String getOid_Requisicao_Compra () {
    return oid_Requisicao_Compra;
  }

  public void setOid_Requisicao_Compra (String oid_Requisicao_Compra) {
    this.oid_Requisicao_Compra = oid_Requisicao_Compra;
  }

  public String getOid_Solicitacao_Compra () {
    return oid_Solicitacao_Compra;
  }

  public void setOid_Solicitacao_Compra (String oid_Solicitacao_Compra) {
    this.oid_Solicitacao_Compra = oid_Solicitacao_Compra;
  }

  public double getVl_medio () {
    return vl_medio;
  }

  public void setVl_medio (double vl_medio) {
    this.vl_medio = vl_medio;
  }

  public double getVl_Quantidade () {
    return vl_Quantidade;
  }

  public void setVl_Quantidade (double vl_Quantidade) {
    this.vl_Quantidade = vl_Quantidade;
  }

  public double getVl_Quantidade_Entrega () {
    return vl_Quantidade_Entrega;
  }

  public void setVl_Quantidade_Entrega (double vl_Quantidade_Entrega) {
    this.vl_Quantidade_Entrega = vl_Quantidade_Entrega;
  }

  public double getVl_ultima_compra () {
    return vl_ultima_compra;
  }

  public void setVl_ultima_compra (double vl_ultima_compra) {
    this.vl_ultima_compra = vl_ultima_compra;
  }

  public double getVl_Unitario () {
    return vl_Unitario;
  }

  public void setVl_Unitario (double vl_Unitario) {
    this.vl_Unitario = vl_Unitario;
  }

  public double getVl_lista_preco () {
    return vl_lista_preco;
  }

  public void setVl_lista_preco (double vl_lista_preco) {
    this.vl_lista_preco = vl_lista_preco;
  }

  public String getCentro_custo () {
    return centro_custo;
  }

  public String getDM_Tipo_Movimento () {
    return DM_Tipo_Movimento;
  }

  public String getNR_Placa () {
    return NR_Placa;
  }

  public String getDT_Final () {
    return DT_Final;
  }

  public String getDT_Inicial () {
    return DT_Inicial;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public void setCentro_custo (String centro_custo) {
    this.centro_custo = centro_custo;
  }

  public void setDM_Tipo_Movimento (String DM_Tipo_Movimento) {
    this.DM_Tipo_Movimento = DM_Tipo_Movimento;
  }

  public void setNR_Placa (String NR_Placa) {
    this.NR_Placa = NR_Placa;
  }

  public void setDT_Final (String DT_Final) {
    this.DT_Final = DT_Final;
  }

  public void setDT_Inicial (String DT_Inicial) {
    this.DT_Inicial = DT_Inicial;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

public String getDM_Movimento() {
	return DM_Movimento;
}

public void setDM_Movimento(String movimento) {
	DM_Movimento = movimento;
}

public int getOid_Usuario() {
	return oid_Usuario;
}

public void setOid_Usuario(int oid_Usuario) {
	this.oid_Usuario = oid_Usuario;
}

public String getNM_Usuario() {
	return NM_Usuario;
}

public void setNM_Usuario(String usuario) {
	NM_Usuario = usuario;
}

public String getNm_referencia2() {
	return nm_referencia2;
}

public void setNm_referencia2(String nm_referencia2) {
	this.nm_referencia2 = nm_referencia2;
}

public String getNm_referencia3() {
	return nm_referencia3;
}

public void setNm_referencia3(String nm_referencia3) {
	this.nm_referencia3 = nm_referencia3;
}

public String getNm_referencia4() {
	return nm_referencia4;
}

public void setNm_referencia4(String nm_referencia4) {
	this.nm_referencia4 = nm_referencia4;
}

public String getNm_referencia5() {
	return nm_referencia5;
}

public void setNm_referencia5(String nm_referencia5) {
	this.nm_referencia5 = nm_referencia5;
}

public String getNm_referencia6() {
	return nm_referencia6;
}

public void setNm_referencia6(String nm_referencia6) {
	this.nm_referencia6 = nm_referencia6;
}
}