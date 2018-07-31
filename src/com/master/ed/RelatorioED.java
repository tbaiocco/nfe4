/*
 * Created on 16/11/2004
 */
package com.master.ed;

import javax.servlet.http.*;

/**
 * @author Andre Valadas
 */

public class RelatorioED
    extends RelatorioBaseED {
  long nr_pagina;
  long nr_livro;
  //*** Oids
   private String oid_pessoa_distribuidor;
  private String oid_pessoa_remetente;
  private String oid_pessoa_fornecedor;
  private String oid_localizacao;
  private String oid_pessoa;
  private String oid_fornecedor;
  private String oid_cliente;
  private String oid_vendedor;
  private String oid_produto_cliente;
  private int oid_carga_entrega;
  private int oid_cidade;
  private int oid_segmentacao;
  private int oid_entregador;
  private int oid_entrega;
  private int oid_lote_recebimento;
  private int oid_tipo_estoque;
  private int oid_deposito;
  private int oid_produto;
  private int oid_mix;
  private int oid_ocorrencia_pedido;
  private int oid_modelo;
  private String oid_unidade;
  private String oid_natureza_operacao;
  private String oid_unidade_contabil;
  private String oid_pais;
  private int oid_pedido;
  private int oid_Requisicao_Consumo;
  private int nr_Requisicao_Consumo;
  private int nr_Requisicao_Consumo_final;
  private int oid_tipo_Requisicao_Consumo;
  private int oid_tipo_pedido;
  private int oid_conta;
  private int oid_centro_custo;
  private String oid_conta_corrente;
  private int oid_tipo_documento;
  private int oid_tabela_venda;
  private int oid_tipo_tabela_venda;
  private int oid_grupo_nota_fiscal;
  private int oid_estrutura_produto;
  private int oid_estrutura_fornecedor;
  private int oid_nivel_produto1;
  private int oid_nivel_produto2;
  private int oid_nivel_produto3;
  private int oid_nivel_produto4;
  private int oid_nivel_produto5;
  private String oid_Supervisor;

  private String NM_Grupo_Economico;
  private String CD_Carteira;
  private String M_Tipo_Cobranca;
  private int NR_Dias_Vencimento;
  private double PE_Comissao;

  private int NR_Dias_Bloqueio;
  private String DT_Ultimo_Movimento;
  private String DT_Bloqueio;



  public RelatorioED () {
    super ();
  }

  public RelatorioED (HttpServletResponse response , String nomeRelatorio) {
    super (response , nomeRelatorio);
  }

  //*** DMs
   private String dm_situacao;
  private String dm_credito;
  private String dm_pendencia;
  private String dm_motivo;
  private String dm_forma_pagamento;
  private String dm_pedido;
  private String dm_tipo;
  private String dm_dia;
  private String dm_tipo_operacao;
  private String dm_tipo_nota_fiscal;
  private String dm_tipo_devolucao;
  private String dm_critica_financeira;
  private String dm_critica_estoque;

  //*** Pessoa, Cliente, Vendedor, Fornecedor, Entregador, Distribuidor
   private String cd_entregador;
  private String cd_vendedor;
  private String cd_vendedor_final;
  private String nm_vendedor;
  private String cd_fornecedor;
  private String cd_pais;
  private String cd_estado;
  private String cd_rota;
  private String cd_banco;
  private String nm_banco;
  private String cd_banco_lote;
  private String cd_nivel_produto;
  private String nm_nivel_produto;
  private String cd_estrutura_produto;
  private String nm_estrutura_produto;
  private String cd_estrutura_produto_final;
  private String nm_estrutura_produto_final;
  private String nr_cnpj_cpf;
  private String nm_razao_social;
  private String nm_entregador;
  private String nm_cliente;
  private String nm_distribuidor;
  private String nm_cidade;
  private String nm_estado;
  private String nm_pais;
  private String nm_endereco;
  private String nm_bairro;
  private String nr_cep;
  private String nr_telefone;
  private String nr_telefone2;
  private String nr_fax;
  private String nm_inscricao_estadual;
  private String nm_sigla;
  private String nm_contato;
  private String email;
  private String cd_tipo_tabela_venda;
  private String nm_tipo_tabela_venda;
  private String nm_situacao;
  private String nm_rotas;
  private String nr_cnpj_cpf_cobranca;
  private String nm_inscricao_estadual_cobranca;
  private String nr_telefone_cobranca;
  private String nm_endereco_cobranca;
  private String nm_bairro_cobranca;
  private String nm_cidade_cobranca;
  private String nr_cep_cobranca;
  private String nm_estado_cobranca;

  //*** MIX
   private String cd_mix;
  private String nm_mix;

  //*** Produtos, Estoque, Tipo Estoque, localizaçao, Deposito
   private String cd_produto;
  private String cd_produto_lista;
  private String nm_produto;
  private String nm_descricao;
  private boolean pesagem;
  private boolean incluiduplicata;
  private int nr_qt_caixa;
  private int nr_qt_nota_fiscal;
  private int nr_qt_item;
  private int nr_qt_cliente_atendido;
  private int nr_qt_cliente_ativo;
  private int nr_qt_cliente_cadastrado;
  private double pc_qt_cliente_positivo;
  private String cd_unidade;
  private String nm_unidade;
  private String cd_unidade_produto;
  private String nm_unidade_produto;
  private String nr_caixa;
  private String nr_caixa_estoque;
  private int nr_caixa2;
  private String nr_placa;
  private String nm_canhoto;
  private String cd_tipo_estoque;
  private String nm_tipo_estoque;
  private String cd_deposito;
  private String nm_deposito;
  private String nm_rua;
  private String nr_endereco;
  private String nr_apartamento;
  private double vl_diferenca;
  private double vl_total_produto;
  private double vl_total_item;
  private double vl_total_troca;
  private double vl_total_desconto;
  private double vl_total_ipi;
  private double nr_quantidade;
  private double nr_qt_atual;
  private double nr_qt_minimo;
  private double nr_peso_real;
  private double nr_peso_medio;
  private double nr_peso_liquido;

  private double vl_markup;
  private double vl_caixa;
  private double VL_Diferenca_Aliq_ICMS;
  private double vl_total_despesas;

  //*** Nota Fiscal, Itens Nota Fiscal, Fiscal
   private int nr_nota_fiscal;
  private int nr_nota_fiscal_final;
  private int nr_compromisso;
  private int nr_documento;
  private String nm_documento;
  private int nr_parcela;
  private String dt_emissao;
  private String dt_entrada;
  private String nm_entrada;
  private String dt_entrega;
  private double nr_qt_atendido;
  private double nr_qt_atendido_venda;
  private double vl_item;
  private double vl_troca;
  private double vl_unitario;
  private double vl_compra;
  private double vl_venda;
  private double vl_unitario_troca;
  private double vl_custo;
  private double vl_nota_fiscal;
  private double vl_contabil;

  private double vl_base_icms;
  private double vl_base_icms_subst;
  private double vl_icms;
  private double vl_icms_subst;
  private double vl_imposto;
  private double vl_imposto_creditado;
  private double vl_ipi;
  private double vl_outros;

  private double vl_dinheiro;
  private double vl_vale;
  private double vl_moeda;
  private double vl_lote;

  private double vl_produto;
  private double vl_produto_diferenca;
  private double vl_produto_atual;
  private double vl_desconto_avista;
  private double vl_desconto;
  private double vl_liquido_nota_fiscal;
  private double vl_desconto_7_dias;
  private double vl_acrescimo_21_dias;
  private double vl_acrescimo_28_dias;
  private double vl_acrescimo_30_dias;

  private double vl_desconto_avista_diferenca;
  private double vl_desconto_7_dias_diferenca;
  private double vl_acrescimo_21_dias_diferenca;
  private double vl_acrescimo_28_dias_diferenca;
  private double vl_acrescimo_30_dias_diferenca;

  private String dm_alterado;

  private int qt_avista;
  private int qt_aprazo;
  private int qt_cheque;
  private double vl_avista;
  private double vl_aprazo;
  private double vl_cheque;
  private double vl_compromisso;
  private double vl_saldo;
  private double vl_positivo;
  private double vl_negativo;

  private double valor_total;
  private double vl_frete;
  private double pe_desconto;
  private double pe_permitido;
  private double pe_margem;
  private double pe_aliquota_ipi;
  private String cd_condicao_pagamento;
  private String nm_condicao_pagamento;
  private double nr_qt_compras;
  private double nr_qt_anterior;
  private double nr_qt_acerto;
  private double nr_qt_estoque_trocas;
  private double nr_qt_trocas_entrada;
  private double nr_qt_trocas_saida;
  private double nr_qt_trocas;
  private double nr_qt_devolucao_entrada;
  private double nr_qt_devolucao_saida;
  private double nr_qt_outras_entrada;
  private double nr_qt_outras_saida;
  private double nr_qt_vendas;
  private int oid_cheque_cliente;
  private String nr_cheque;
  private String nr_agencia;
  private String nr_agencia_lote;
  private String nr_conta;
  private String cd_conta;
  private String nm_conta;
  private String nr_conta_lote;
  private int nr_projecao_dias;
  private int nr_lote_recebimento;
  private int nr_lote_pagamento;
  private String cd_especie;
  private String nm_serie;
  private String cd_natureza_operacao;
  private String cd_modelo_nota;
  private String tx_mensagem;
  private String nm_tipo_imposto;
  private String nm_tipo_documento;
  private String nm_forma_pagamento;

  //*** Pedidos de Venda e Compra
   private int nr_pedido;
  private String nr_pedido_palm;
  private int nr_acerto;
  private int nr_tabela;
  private int nr_tabela_anterior;
  private int nr_pedido_final;
  private String tx_observacao;
  private String hr_ocorrencia;
  private String nm_tipo_pedido;
  private String nm_tipo_Requisicao_Consumo;
  private String nm_comprador;
  private String nm_fornecedor;

  //*** Datas
   private String dt_inicial;
  private String dt_final;
  private String dt_emissao_inicial;
  private String dt_emissao_final;
  private String dt_entrada_inicial;
  private String dt_entrada_final;
  private String dt_entrega_inicial;
  private String dt_entrega_final;
  private String dt_acerto;
  private String dt_cheque;
  private String dt_ocorrencia;
  private String dt_validade;
  private String dt_pagamento;
  private String dt_pagamento_final;
  private String dt_vencimento;
  private String nm_vencimento;
  private String nm_emissao;
  private String nm_programada;
  private String nm_compensacao;
  private String nm_favorecido;
  private String dt_vencimento_final;
  private String dt_vigencia;
  private String dt_validade_final;
  private String dt_vigencia_final;
  private String dt_compensacao;
  private String dt_compensacao_final;
  private String dt_programada;
  private String dt_programada_final;
  private String dt_cadastro;

  private String dia_semana;

  //*** Cotacao e Proposta
   private String nr_cotacao;
  private String depto_setor;
  private String nm_mercadoria;
  private String per_sobre_invoice;
  private String nm_origem;
  private String nm_destino;
  private String peso_bruto;
  private String metro_cubico;
  private String valor_frete;
  private String per_ad_valoren;
  private String taxa_por_crt;
  private String transit_time;
  private String observacao;
  private String vl_excedente_estadia;
  private String validade_cotacao;
  private String nm_razao_social_transportador;
  private String nm_fantasia_transportador;
  private String nm_fantasia;
  private String nm_vendedor_transportador;
  private String nr_telefone_vendedor;
  private String nr_fax_vendedor;
  private String email_vendedor;
  private String idioma;
  private String dt_cotacao;
  private String faixas_peso;
  private String valor_frete_minimo;
  private String nm_arquivo;

  private String cd_cliente;
  private boolean produto;

  private long nr_quantidade_consumo;

  //** Fiscal - Apuracao ICMS **//
  private String nm_credito_1;
  private String nm_credito_2;
  private String nm_credito_3;
  private String nm_credito_4;
  private double vl_credito_1;
  private double vl_credito_2;
  private double vl_credito_3;
  private double vl_credito_4;
  private String nm_debito_1;
  private String nm_debito_2;
  private String nm_debito_3;
  private String nm_debito_4;
  private double vl_debito_1;
  private double vl_debito_2;
  private double vl_debito_3;
  private double vl_debito_4;
  private String nm_edebito_1;
  private String nm_edebito_2;
  private double vl_edebito_1;
  private double vl_edebito_2;
  private String nm_ecredito_1;
  private String nm_ecredito_2;
  private double vl_ecredito_1;
  private double vl_ecredito_2;
  private String descDM_Situacao;
  private String DM_Tipo_Cobranca;

  //*** Formas de Pagamento/Recebimento
   public static String getDescPagamento (String dmTipo) {
     if ("1".equals (dmTipo)) {
       return "Boleto";
     }
     else if ("2".equals (dmTipo)) {
       return "Cheque";
     }
     else if ("3".equals (dmTipo)) {
       return "Depósito CC";
     }
     else if ("4".equals (dmTipo)) {
       return "Carteira";
     }
     else if ("5".equals (dmTipo)) {
       return "Dinheiro";
     }
     else {
       return "Não informado!";
     }
   }

  public String getDescDM_Situacao () {
    if ("L".equals (dm_situacao) || "A".equals (dm_situacao)) {
      return "Liberado";
    }
    else if ("B".equals (dm_situacao) || "C".equals (dm_situacao)) {
      return "Bloqueado";
    }
    else {
      return "Não Informada!";
    }
  }

  public boolean isProduto () {
    return produto;
  }

  public void setProduto (boolean produto) {
    this.produto = produto;
  }

  public String getValor_frete_minimo () {
    return valor_frete_minimo;
  }

  public void setValor_frete_minimo (String valor_frete_minimo) {
    this.valor_frete_minimo = valor_frete_minimo;
  }

  public String getFaixas_peso () {
    return faixas_peso;
  }

  public void setFaixas_peso (String faixas_peso) {
    this.faixas_peso = faixas_peso;
  }

  public String getMetro_cubico () {
    return metro_cubico;
  }

  public void setMetro_cubico (String metro_cubico) {
    this.metro_cubico = metro_cubico;
  }

  public String getEmail_vendedor () {
    return email_vendedor;
  }

  public void setEmail_vendedor (String email_vendedor) {
    this.email_vendedor = email_vendedor;
  }

  public String getNm_vendedor_transportador () {
    return nm_vendedor_transportador;
  }

  public void setNm_vendedor_transportador (String nm_vendedor_transportador) {
    this.nm_vendedor_transportador = nm_vendedor_transportador;
  }

  public String getNr_fax_vendedor () {
    return nr_fax_vendedor;
  }

  public void setNr_fax_vendedor (String nr_fax_vendedor) {
    this.nr_fax_vendedor = nr_fax_vendedor;
  }

  public String getNr_telefone_vendedor () {
    return nr_telefone_vendedor;
  }

  public void setNr_telefone_vendedor (String nr_telefone_vendedor) {
    this.nr_telefone_vendedor = nr_telefone_vendedor;
  }

  public String getVl_excedente_estadia () {
    return vl_excedente_estadia;
  }

  public void setVl_excedente_estadia (String vl_excedente_estadia) {
    this.vl_excedente_estadia = vl_excedente_estadia;
  }

  public String getDt_cotacao () {
    return dt_cotacao;
  }

  public void setDt_cotacao (String dt_cotacao) {
    this.dt_cotacao = dt_cotacao;
  }

  public String getIdioma () {
    return idioma;
  }

  public void setIdioma (String idioma) {
    this.idioma = idioma;
  }

  public String getCd_pais () {
    return cd_pais;
  }

  public void setCd_pais (String cd_pais) {
    this.cd_pais = cd_pais;
  }

  public String getNm_pais () {
    return nm_pais;
  }

  public void setNm_pais (String nm_pais) {
    this.nm_pais = nm_pais;
  }

  public String getOid_pais () {
    return oid_pais;
  }

  public void setOid_pais (String oid_pais) {
    this.oid_pais = oid_pais;
  }

  public String getOid_unidade () {
    return oid_unidade;
  }

  public void setOid_unidade (String oid_unidade) {
    this.oid_unidade = oid_unidade;
  }

  public String getNm_fantasia_transportador () {
    return nm_fantasia_transportador;
  }

  public void setNm_fantasia_transportador (String nm_fantasia_transportador) {
    this.nm_fantasia_transportador = nm_fantasia_transportador;
  }

  public String getNm_razao_social_transportador () {
    return nm_razao_social_transportador;
  }

  public void setNm_razao_social_transportador (
      String nm_razao_social_transportador) {
    this.nm_razao_social_transportador = nm_razao_social_transportador;
  }

  public String getObservacao () {
    return observacao;
  }

  public void setObservacao (String observacao) {
    this.observacao = observacao;
  }

  public String getPer_ad_valoren () {
    return per_ad_valoren;
  }

  public void setPer_ad_valoren (String per_ad_valoren) {
    this.per_ad_valoren = per_ad_valoren;
  }

  public String getPeso_bruto () {
    return peso_bruto;
  }

  public void setPeso_bruto (String peso_bruto) {
    this.peso_bruto = peso_bruto;
  }

  public String getTaxa_por_crt () {
    return taxa_por_crt;
  }

  public void setTaxa_por_crt (String taxa_por_crt) {
    this.taxa_por_crt = taxa_por_crt;
  }

  public String getTransit_time () {
    return transit_time;
  }

  public void setTransit_time (String transit_time) {
    this.transit_time = transit_time;
  }

  public String getValidade_cotacao () {
    return validade_cotacao;
  }

  public void setValidade_cotacao (String validade_cotacao) {
    this.validade_cotacao = validade_cotacao;
  }

  public String getValor_frete () {
    return valor_frete;
  }

  public void setValor_frete (String valor_frete) {
    this.valor_frete = valor_frete;
  }

  public String getDepto_setor () {
    return depto_setor;
  }

  public void setDepto_setor (String depto_setor) {
    this.depto_setor = depto_setor;
  }

  public String getNm_contato () {
    return nm_contato;
  }

  public void setNm_contato (String nm_contato) {
    this.nm_contato = nm_contato;
  }

  public String getNm_destino () {
    return nm_destino;
  }

  public void setNm_destino (String nm_destino) {
    this.nm_destino = nm_destino;
  }

  public String getNm_mercadoria () {
    return nm_mercadoria;
  }

  public void setNm_mercadoria (String nm_mercadoria) {
    this.nm_mercadoria = nm_mercadoria;
  }

  public String getNm_origem () {
    return nm_origem;
  }

  public void setNm_origem (String nm_origem) {
    this.nm_origem = nm_origem;
  }

  public String getNm_sigla () {
    return nm_sigla;
  }

  public void setNm_sigla (String nm_sigla) {
    this.nm_sigla = nm_sigla;
  }

  public String getNr_cotacao () {
    return nr_cotacao;
  }

  public void setNr_cotacao (String nr_cotacao) {
    this.nr_cotacao = nr_cotacao;
  }

  public String getNr_fax () {
    return nr_fax;
  }

  public void setNr_fax (String nr_fax) {
    this.nr_fax = nr_fax;
  }

  public String getPer_sobre_invoice () {
    return per_sobre_invoice;
  }

  public void setPer_sobre_invoice (String per_sobre_invoice) {
    this.per_sobre_invoice = per_sobre_invoice;
  }

  public String getEmail () {
    return email;
  }

  public void setEmail (String email) {
    this.email = email;
  }

  public double getNr_qt_minimo () {
    return nr_qt_minimo;
  }

  public void setNr_qt_minimo (double nr_qt_minimo) {
    this.nr_qt_minimo = nr_qt_minimo;
  }

  public double getNr_qt_atual () {
    return nr_qt_atual;
  }

  public void setNr_qt_atual (double nr_qt_atual) {
    this.nr_qt_atual = nr_qt_atual;
  }

  public double getValor_total () {
    return valor_total;
  }

  public void setValor_total (double valor_total) {
    this.valor_total = valor_total;
  }

  public double getVl_nota_fiscal () {
    return vl_nota_fiscal;
  }

  public void setVl_nota_fiscal (double vl_nota_fiscal) {
    this.vl_nota_fiscal = vl_nota_fiscal;
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

  public int getNr_nota_fiscal () {
    return nr_nota_fiscal;
  }

  public void setNr_nota_fiscal (int nr_nota_fiscal) {
    this.nr_nota_fiscal = nr_nota_fiscal;
  }

  public double getNr_qt_atendido () {
    return nr_qt_atendido;
  }

  public void setNr_qt_atendido (double nr_qt_atendido) {
    this.nr_qt_atendido = nr_qt_atendido;
  }

  public double getVl_item () {
    return vl_item;
  }

  public void setVl_item (double vl_item) {
    this.vl_item = vl_item;
  }

  public String getDt_emissao_final () {
    return dt_emissao_final;
  }

  public void setDt_emissao_final (String dt_emissao_final) {
    this.dt_emissao_final = dt_emissao_final;
  }

  public String getDt_emissao_inicial () {
    return dt_emissao_inicial;
  }

  public void setDt_emissao_inicial (String dt_emissao_inicial) {
    this.dt_emissao_inicial = dt_emissao_inicial;
  }

  public String getDt_entrada_final () {
    return dt_entrada_final;
  }

  public void setDt_entrada_final (String dt_entrada_final) {
    this.dt_entrada_final = dt_entrada_final;
  }

  public String getDt_entrada_inicial () {
    return dt_entrada_inicial;
  }

  public void setDt_entrada_inicial (String dt_entrada_inicial) {
    this.dt_entrada_inicial = dt_entrada_inicial;
  }

  public String getCd_tipo_estoque () {
    return cd_tipo_estoque;
  }

  public void setCd_tipo_estoque (String cd_tipo_estoque) {
    this.cd_tipo_estoque = cd_tipo_estoque;
  }

  public String getNm_deposito () {
    return nm_deposito;
  }

  public void setNm_deposito (String nm_deposito) {
    this.nm_deposito = nm_deposito;
  }

  public String getNm_rua () {
    return nm_rua;
  }

  public void setNm_rua (String nm_rua) {
    this.nm_rua = nm_rua;
  }

  public String getNm_tipo_estoque () {
    return nm_tipo_estoque;
  }

  public void setNm_tipo_estoque (String nm_tipo_estoque) {
    this.nm_tipo_estoque = nm_tipo_estoque;
  }

  public String getNr_apartamento () {
    return nr_apartamento;
  }

  public void setNr_apartamento (String nr_apartamento) {
    this.nr_apartamento = nr_apartamento;
  }

  public String getNr_endereco () {
    return nr_endereco;
  }

  public void setNr_endereco (String nr_endereco) {
    this.nr_endereco = nr_endereco;
  }

  public double getNr_quantidade () {
    return nr_quantidade;
  }

  public void setNr_quantidade (double nr_quantidade) {
    this.nr_quantidade = nr_quantidade;
  }

  public String getCd_vendedor () {
    return cd_vendedor;
  }

  public void setCd_vendedor (String cd_vendedor) {
    this.cd_vendedor = cd_vendedor;
  }

  public String getNm_bairro () {
    return nm_bairro;
  }

  public void setNm_bairro (String nm_bairro) {
    this.nm_bairro = nm_bairro;
  }

  public String getNm_cidade () {
    return nm_cidade;
  }

  public void setNm_cidade (String nm_cidade) {
    this.nm_cidade = nm_cidade;
  }

  public String getNm_endereco () {
    return nm_endereco;
  }

  public void setNm_endereco (String nm_endereco) {
    this.nm_endereco = nm_endereco;
  }

  public String getNm_estado () {
    return nm_estado;
  }

  public void setNm_estado (String nm_estado) {
    this.nm_estado = nm_estado;
  }

  public String getNm_inscricao_estadual () {
    return nm_inscricao_estadual;
  }

  public void setNm_inscricao_estadual (String nm_inscricao_estadual) {
    this.nm_inscricao_estadual = nm_inscricao_estadual;
  }

  public String getNm_razao_social () {
    return nm_razao_social;
  }

  public void setNm_razao_social (String nm_razao_social) {
    this.nm_razao_social = nm_razao_social;
  }

  public String getNr_cep () {
    return nr_cep;
  }

  public void setNr_cep (String nr_cep) {
    this.nr_cep = nr_cep;
  }

  public String getNr_cnpj_cpf () {
    return nr_cnpj_cpf;
  }

  public void setNr_cnpj_cpf (String nr_cnpj_cpf) {
    this.nr_cnpj_cpf = nr_cnpj_cpf;
  }

  public String getNr_telefone () {
    return nr_telefone;
  }

  public String getNr_telefone2 () {
    return nr_telefone2;
  }

  public void setNr_telefone (String nr_telefone) {
    this.nr_telefone = nr_telefone;
  }

  public void setNr_telefone2 (String nr_telefone2) {
    this.nr_telefone2 = nr_telefone2;
  }

  public String getCd_mix () {
    return cd_mix;
  }

  public void setCd_mix (String cd_mix) {
    this.cd_mix = cd_mix;
  }

  public String getCd_produto () {
    return cd_produto;
  }

  public void setCd_produto (String cd_produto) {
    this.cd_produto = cd_produto;
  }

  public String getNm_produto () {
    return nm_produto;
  }

  public void setNm_produto (String nm_produto) {
    this.nm_produto = nm_produto;
  }

  public String getNm_mix () {
    return nm_mix;
  }

  public void setNm_mix (String nm_mix) {
    this.nm_mix = nm_mix;
  }

  public String getCd_fornecedor () {
    return cd_fornecedor;
  }

  public void setCd_fornecedor (String cd_fornecedor) {
    this.cd_fornecedor = cd_fornecedor;
  }

  public int getOid_deposito () {
    return oid_deposito;
  }

  public void setOid_deposito (int oid_deposito) {
    this.oid_deposito = oid_deposito;
  }

  public String getOid_localizacao () {
    return oid_localizacao;
  }

  public void setOid_localizacao (String oid_localizacao) {
    this.oid_localizacao = oid_localizacao;
  }

  public int getOid_mix () {
    return oid_mix;
  }

  public void setOid_mix (int oid_mix) {
    this.oid_mix = oid_mix;
  }

  public String getOid_pessoa () {
    return oid_pessoa;
  }

  public void setOid_pessoa (String oid_pessoa) {
    this.oid_pessoa = oid_pessoa;
  }

  public String getOid_pessoa_distribuidor () {
    return oid_pessoa_distribuidor;
  }

  public void setOid_pessoa_distribuidor (String oid_pessoa_distribuidor) {
    this.oid_pessoa_distribuidor = oid_pessoa_distribuidor;
  }

  public int getOid_produto () {
    return oid_produto;
  }

  public void setOid_produto (int oid_produto) {
    this.oid_produto = oid_produto;
  }

  public int getOid_tipo_estoque () {
    return oid_tipo_estoque;
  }

  public void setOid_tipo_estoque (int oid_tipo_estoque) {
    this.oid_tipo_estoque = oid_tipo_estoque;
  }

  public String getCd_vendedor_final () {
    return cd_vendedor_final;
  }

  public String getDm_motivo () {
    return dm_motivo;
  }

  public String getDt_entrega_final () {
    return dt_entrega_final;
  }

  public String getDt_entrega_inicial () {
    return dt_entrega_inicial;
  }

  public int getNr_pedido () {
    return nr_pedido;
  }

  public int getNr_pedido_final () {
    return nr_pedido_final;
  }

  public String getOid_cliente () {
    return oid_cliente;
  }

  public int getOid_entregador () {
    return oid_entregador;
  }

  public String getOid_vendedor () {
    return oid_vendedor;
  }

  public void setCd_vendedor_final (String cd_vendedor_final) {
    this.cd_vendedor_final = cd_vendedor_final;
  }

  public void setDm_motivo (String dm_motivo) {
    this.dm_motivo = dm_motivo;
  }

  public void setDt_entrega_final (String dt_entrega_final) {
    this.dt_entrega_final = dt_entrega_final;
  }

  public void setDt_entrega_inicial (String dt_entrega_inicial) {
    this.dt_entrega_inicial = dt_entrega_inicial;
  }

  public void setNr_pedido (int nr_pedido) {
    this.nr_pedido = nr_pedido;
  }

  public void setNr_pedido_final (int nr_pedido_final) {
    this.nr_pedido_final = nr_pedido_final;
  }

  public void setOid_cliente (String oid_cliente) {
    this.oid_cliente = oid_cliente;
  }

  public void setOid_entregador (int oid_entregador) {
    this.oid_entregador = oid_entregador;
  }

  public void setOid_vendedor (String oid_vendedor) {
    this.oid_vendedor = oid_vendedor;
  }

  public String getTx_observacao () {
    return tx_observacao;
  }

  public void setTx_observacao (String tx_observacao) {
    this.tx_observacao = tx_observacao;
  }

  public String getDt_ocorrencia () {
    return dt_ocorrencia;
  }

  public void setDt_ocorrencia (String dt_ocorrencia) {
    this.dt_ocorrencia = dt_ocorrencia;
  }

  public int getOid_ocorrencia_pedido () {
    return oid_ocorrencia_pedido;
  }

  public void setOid_ocorrencia_pedido (int oid_ocorrencia_pedido) {
    this.oid_ocorrencia_pedido = oid_ocorrencia_pedido;
  }

  public String getHr_ocorrencia () {
    return hr_ocorrencia;
  }

  public void setHr_ocorrencia (String hr_ocorrencia) {
    this.hr_ocorrencia = hr_ocorrencia;
  }

  public String getCd_unidade_produto () {
    return cd_unidade_produto;
  }

  public void setCd_unidade_produto (String cd_unidade_produto) {
    this.cd_unidade_produto = cd_unidade_produto;
  }

  public String getNr_caixa () {
    return nr_caixa;
  }

  public void setNr_caixa (String nr_caixa) {
    this.nr_caixa = nr_caixa;
  }

  public String getCd_entregador () {
    return cd_entregador;
  }

  public void setCd_entregador (String cd_entregador) {
    this.cd_entregador = cd_entregador;
  }

  public String getCd_deposito () {
    return cd_deposito;
  }

  public void setCd_deposito (String cd_deposito) {
    this.cd_deposito = cd_deposito;
  }

  public double getVl_unitario () {
    return vl_unitario;
  }

  public void setVl_unitario (double vl_unitario) {
    this.vl_unitario = vl_unitario;
  }

  public int getNr_nota_fiscal_final () {
    return nr_nota_fiscal_final;
  }

  public void setNr_nota_fiscal_final (int nr_nota_fiscal_final) {
    this.nr_nota_fiscal_final = nr_nota_fiscal_final;
  }

  public String getDm_pendencia () {
    return dm_pendencia;
  }

  public String getDm_situacao () {
    return dm_situacao;
  }

  public int getOid_modelo () {
    return oid_modelo;
  }

  public String getOid_pessoa_remetente () {
    return oid_pessoa_remetente;
  }

  public void setDm_pendencia (String dm_pendencia) {
    this.dm_pendencia = dm_pendencia;
  }

  public void setDm_situacao (String dm_situacao) {
    this.dm_situacao = dm_situacao;
  }

  public void setOid_modelo (int oid_modelo) {
    this.oid_modelo = oid_modelo;
  }

  public void setOid_pessoa_remetente (String oid_pessoa_remetente) {
    this.oid_pessoa_remetente = oid_pessoa_remetente;
  }

  public String getNm_cliente () {
    return nm_cliente;
  }

  public String getNm_entregador () {
    return nm_entregador;
  }

  public void setNm_cliente (String nm_cliente) {
    this.nm_cliente = nm_cliente;
  }

  public void setNm_entregador (String nm_entregador) {
    this.nm_entregador = nm_entregador;
  }

  public String getNm_condicao_pagamento () {
    return nm_condicao_pagamento;
  }

  public void setNm_condicao_pagamento (String nm_condicao_pagamento) {
    this.nm_condicao_pagamento = nm_condicao_pagamento;
  }

  public String getDm_forma_pagamento () {
    return dm_forma_pagamento;
  }

  public void setDm_forma_pagamento (String dm_forma_pagamento) {
    this.dm_forma_pagamento = dm_forma_pagamento;
  }

  public String getCd_condicao_pagamento () {
    return cd_condicao_pagamento;
  }

  public void setCd_condicao_pagamento (String cd_condicao_pagamento) {
    this.cd_condicao_pagamento = cd_condicao_pagamento;
  }

  public String getDm_pedido () {
    return dm_pedido;
  }

  public int getOid_pedido () {
    return oid_pedido;
  }

  public void setDm_pedido (String dm_pedido) {
    this.dm_pedido = dm_pedido;
  }

  public void setOid_pedido (int oid_pedido) {
    this.oid_pedido = oid_pedido;
  }

  public String getNm_comprador () {
    return nm_comprador;
  }

  public String getNm_distribuidor () {
    return nm_distribuidor;
  }

  public String getNm_tipo_pedido () {
    return nm_tipo_pedido;
  }

  public void setNm_comprador (String nm_comprador) {
    this.nm_comprador = nm_comprador;
  }

  public void setNm_distribuidor (String nm_distribuidor) {
    this.nm_distribuidor = nm_distribuidor;
  }

  public void setNm_tipo_pedido (String nm_tipo_pedido) {
    this.nm_tipo_pedido = nm_tipo_pedido;
  }

  public String getDt_entrega () {
    return dt_entrega;
  }

  public void setDt_entrega (String dt_entrega) {
    this.dt_entrega = dt_entrega;
  }

  public double getPe_aliquota_ipi () {
    return pe_aliquota_ipi;
  }

  public double getPe_desconto () {
    return pe_desconto;
  }

  public void setPe_aliquota_ipi (double pe_aliquota_ipi) {
    this.pe_aliquota_ipi = pe_aliquota_ipi;
  }

  public void setPe_desconto (double pe_desconto) {
    this.pe_desconto = pe_desconto;
  }

  public double getVl_total_desconto () {
    return vl_total_desconto;
  }

  public double getVl_total_ipi () {
    return vl_total_ipi;
  }

  public double getVl_total_item () {
    return vl_total_item;
  }

  public double getVl_total_produto () {
    return vl_total_produto;
  }

  public void setVl_total_desconto (double vl_total_desconto) {
    this.vl_total_desconto = vl_total_desconto;
  }

  public void setVl_total_ipi (double vl_total_ipi) {
    this.vl_total_ipi = vl_total_ipi;
  }

  public void setVl_total_item (double vl_total_item) {
    this.vl_total_item = vl_total_item;
  }

  public void setVl_total_produto (double vl_total_produto) {
    this.vl_total_produto = vl_total_produto;
  }

  public double getVl_frete () {
    return vl_frete;
  }

  public void setVl_frete (double vl_frete) {
    this.vl_frete = vl_frete;
  }

  public double getNr_peso_real () {
    return nr_peso_real;
  }

  public void setNr_peso_real (double nr_peso_real) {
    this.nr_peso_real = nr_peso_real;
  }

  public String getNm_descricao () {
    return nm_descricao;
  }

  public void setNm_descricao (String nm_descricao) {
    this.nm_descricao = nm_descricao;
  }

  public int getNr_qt_caixa () {
    return nr_qt_caixa;
  }

  public void setNr_qt_caixa (int nr_qt_caixa) {
    this.nr_qt_caixa = nr_qt_caixa;
  }

  public boolean isPesagem () {
    return pesagem;
  }

  public void setPesagem (boolean pesagem) {
    this.pesagem = pesagem;
  }

  public String getNm_unidade_produto () {
    return nm_unidade_produto;
  }

  public void setNm_unidade_produto (String nm_unidade_produto) {
    this.nm_unidade_produto = nm_unidade_produto;
  }

  public String getOid_produto_cliente () {
    return oid_produto_cliente;
  }

  public void setOid_produto_cliente (String oid_produto_cliente) {
    this.oid_produto_cliente = oid_produto_cliente;
  }

  public double getVl_custo () {
    return vl_custo;
  }

  public void setVl_custo (double vl_custo) {
    this.vl_custo = vl_custo;
  }

  public int getNr_projecao_dias () {
    return nr_projecao_dias;
  }

  public void setNr_projecao_dias (int nr_projecao_dias) {
    this.nr_projecao_dias = nr_projecao_dias;
  }

  public double getNr_qt_compras () {
    return nr_qt_compras;
  }

  public void setNr_qt_compras (double nr_qt_compras) {
    this.nr_qt_compras = nr_qt_compras;
  }

  public double getNr_qt_devolucao_entrada () {
    return nr_qt_devolucao_entrada;
  }

  public void setNr_qt_devolucao_entrada (double nr_qt_devolucao_entrada) {
    this.nr_qt_devolucao_entrada = nr_qt_devolucao_entrada;
  }

  public double getNr_qt_devolucao_saida () {
    return nr_qt_devolucao_saida;
  }

  public void setNr_qt_devolucao_saida (double nr_qt_devolucao_saida) {
    this.nr_qt_devolucao_saida = nr_qt_devolucao_saida;
  }

  public double getNr_qt_estoque_trocas () {
    return nr_qt_estoque_trocas;
  }

  public void setNr_qt_estoque_trocas (double nr_qt_estoque_trocas) {
    this.nr_qt_estoque_trocas = nr_qt_estoque_trocas;
  }

  public double getNr_qt_outras_entrada () {
    return nr_qt_outras_entrada;
  }

  public void setNr_qt_outras_entrada (double nr_qt_outras_entrada) {
    this.nr_qt_outras_entrada = nr_qt_outras_entrada;
  }

  public double getNr_qt_outras_saida () {
    return nr_qt_outras_saida;
  }

  public void setNr_qt_outras_saida (double nr_qt_outras_saida) {
    this.nr_qt_outras_saida = nr_qt_outras_saida;
  }

  public double getNr_qt_trocas_entrada () {
    return nr_qt_trocas_entrada;
  }

  public void setNr_qt_trocas_entrada (double nr_qt_trocas_entrada) {
    this.nr_qt_trocas_entrada = nr_qt_trocas_entrada;
  }

  public double getNr_qt_trocas_saida () {
    return nr_qt_trocas_saida;
  }

  public void setNr_qt_trocas_saida (double nr_qt_trocas_saida) {
    this.nr_qt_trocas_saida = nr_qt_trocas_saida;
  }

  public double getNr_qt_vendas () {
    return nr_qt_vendas;
  }

  public void setNr_qt_vendas (double nr_qt_vendas) {
    this.nr_qt_vendas = nr_qt_vendas;
  }

  public double getNr_qt_anterior () {
    return nr_qt_anterior;
  }

  public void setNr_qt_anterior (double nr_qt_anterior) {
    this.nr_qt_anterior = nr_qt_anterior;
  }

  public double getNr_qt_acerto () {
    return nr_qt_acerto;
  }

  public void setNr_qt_acerto (double nr_qt_acerto) {
    this.nr_qt_acerto = nr_qt_acerto;
  }

  public double getNr_qt_trocas () {
    return nr_qt_trocas;
  }

  public void setNr_qt_trocas (double nr_qt_trocas) {
    this.nr_qt_trocas = nr_qt_trocas;
  }

  public String getCd_especie () {
    return cd_especie;
  }

  public void setCd_especie (String cd_especie) {
    this.cd_especie = cd_especie;
  }

  public String getNm_serie () {
    return nm_serie;
  }

  public void setNm_serie (String nm_serie) {
    this.nm_serie = nm_serie;
  }

  public String getCd_estado () {
    return cd_estado;
  }

  public void setCd_estado (String cd_estado) {
    this.cd_estado = cd_estado;
  }

  public double getVl_contabil () {
    return vl_contabil;
  }

  public void setVl_contabil (double vl_contabil) {
    this.vl_contabil = vl_contabil;
  }

  public String getCd_natureza_operacao () {
    return cd_natureza_operacao;
  }

  public void setCd_natureza_operacao (String cd_natureza_operacao) {
    this.cd_natureza_operacao = cd_natureza_operacao;
  }

  public double getVl_base_icms () {
    return vl_base_icms;
  }

  public void setVl_base_icms (double vl_base_icms) {
    this.vl_base_icms = vl_base_icms;
  }

  public double getVl_base_icms_subst () {
    return vl_base_icms_subst;
  }

  public void setVl_base_icms_subst (double vl_base_icms_subst) {
    this.vl_base_icms_subst = vl_base_icms_subst;
  }

  public double getVl_icms () {
    return vl_icms;
  }

  public void setVl_icms (double vl_icms) {
    this.vl_icms = vl_icms;
  }

  public double getVl_icms_subst () {
    return vl_icms_subst;
  }

  public void setVl_icms_subst (double vl_icms_subst) {
    this.vl_icms_subst = vl_icms_subst;
  }

  public double getVl_imposto () {
    return vl_imposto;
  }

  public void setVl_imposto (double vl_imposto) {
    this.vl_imposto = vl_imposto;
  }

  public double getVl_outros () {
    return vl_outros;
  }

  public void setVl_outros (double vl_outros) {
    this.vl_outros = vl_outros;
  }

  public String getDm_tipo () {
    return dm_tipo;
  }

  public void setDm_tipo (String dm_tipo) {
    this.dm_tipo = dm_tipo;
  }

  public String getNm_tipo_imposto () {
    return nm_tipo_imposto;
  }

  public void setNm_tipo_imposto (String nm_tipo_imposto) {
    this.nm_tipo_imposto = nm_tipo_imposto;
  }

  public String getDm_tipo_operacao () {
    return dm_tipo_operacao;
  }

  public void setDm_tipo_operacao (String dm_tipo_operacao) {
    this.dm_tipo_operacao = dm_tipo_operacao;
  }

  public double getVl_ipi () {
    return vl_ipi;
  }

  public void setVl_ipi (double vl_ipi) {
    this.vl_ipi = vl_ipi;
  }

  public String getDm_tipo_nota_fiscal () {
    return dm_tipo_nota_fiscal;
  }

  public void setDm_tipo_nota_fiscal (String dm_tipo_nota_fiscal) {
    this.dm_tipo_nota_fiscal = dm_tipo_nota_fiscal;
  }

  public String getDm_tipo_devolucao () {
    return dm_tipo_devolucao;
  }

  public void setDm_tipo_devolucao (String dm_tipo_devolucao) {
    this.dm_tipo_devolucao = dm_tipo_devolucao;
  }

  public String getDt_validade () {
    return dt_validade;
  }

  public void setDt_validade (String dt_validade) {
    this.dt_validade = dt_validade;
  }

  public String getDt_validade_final () {
    return dt_validade_final;
  }

  public void setDt_validade_final (String dt_validade_final) {
    this.dt_validade_final = dt_validade_final;
  }

  public String getDt_vigencia () {
    return dt_vigencia;
  }

  public void setDt_vigencia (String dt_vigencia) {
    this.dt_vigencia = dt_vigencia;
  }

  public String getDt_vigencia_final () {
    return dt_vigencia_final;
  }

  public void setDt_vigencia_final (String dt_vigencia_final) {
    this.dt_vigencia_final = dt_vigencia_final;
  }

  public int getNr_tabela () {
    return nr_tabela;
  }

  public void setNr_tabela (int nr_tabela) {
    this.nr_tabela = nr_tabela;
  }

  public int getOid_tabela_venda () {
    return oid_tabela_venda;
  }

  public void setOid_tabela_venda (int oid_tabela_venda) {
    this.oid_tabela_venda = oid_tabela_venda;
  }

  public int getOid_tipo_tabela_venda () {
    return oid_tipo_tabela_venda;
  }

  public void setOid_tipo_tabela_venda (int oid_tipo_tabela_venda) {
    this.oid_tipo_tabela_venda = oid_tipo_tabela_venda;
  }

  public String getCd_tipo_tabela_venda () {
    return cd_tipo_tabela_venda;
  }

  public void setCd_tipo_tabela_venda (String cd_tipo_tabela_venda) {
    this.cd_tipo_tabela_venda = cd_tipo_tabela_venda;
  }

  public String getNm_tipo_tabela_venda () {
    return nm_tipo_tabela_venda;
  }

  public void setNm_tipo_tabela_venda (String nm_tipo_tabela_venda) {
    this.nm_tipo_tabela_venda = nm_tipo_tabela_venda;
  }

  public String getCd_unidade () {
    return cd_unidade;
  }

  public void setCd_unidade (String cd_unidade) {
    this.cd_unidade = cd_unidade;
  }

  public String getNm_unidade () {
    return nm_unidade;
  }

  public void setNm_unidade (String nm_unidade) {
    this.nm_unidade = nm_unidade;
  }

  public double getVl_acrescimo_21_dias () {
    return vl_acrescimo_21_dias;
  }

  public void setVl_acrescimo_21_dias (double vl_acrescimo_21_dias) {
    this.vl_acrescimo_21_dias = vl_acrescimo_21_dias;
  }

  public double getVl_acrescimo_28_dias () {
    return vl_acrescimo_28_dias;
  }

  public void setVl_acrescimo_28_dias (double vl_acrescimo_28_dias) {
    this.vl_acrescimo_28_dias = vl_acrescimo_28_dias;
  }

  public double getVl_desconto_7_dias () {
    return vl_desconto_7_dias;
  }

  public void setVl_desconto_7_dias (double vl_desconto_7_dias) {
    this.vl_desconto_7_dias = vl_desconto_7_dias;
  }

  public double getVl_desconto_avista () {
    return vl_desconto_avista;
  }

  public void setVl_desconto_avista (double vl_desconto_avista) {
    this.vl_desconto_avista = vl_desconto_avista;
  }

  public double getVl_produto () {
    return vl_produto;
  }

  public void setVl_produto (double vl_produto) {
    this.vl_produto = vl_produto;
  }

  public String getDm_alterado () {
    return dm_alterado;
  }

  public void setDm_alterado (String dm_alterado) {
    this.dm_alterado = dm_alterado;
  }

  public int getQt_aprazo () {
    return qt_aprazo;
  }

  public void setQt_aprazo (int qt_aprazo) {
    this.qt_aprazo = qt_aprazo;
  }

  public int getQt_avista () {
    return qt_avista;
  }

  public void setQt_avista (int qt_avista) {
    this.qt_avista = qt_avista;
  }

  public int getQt_cheque () {
    return qt_cheque;
  }

  public void setQt_cheque (int qt_cheque) {
    this.qt_cheque = qt_cheque;
  }

  public double getVl_aprazo () {
    return vl_aprazo;
  }

  public void setVl_aprazo (double vl_aprazo) {
    this.vl_aprazo = vl_aprazo;
  }

  public double getVl_avista () {
    return vl_avista;
  }

  public void setVl_avista (double vl_avista) {
    this.vl_avista = vl_avista;
  }

  public double getVl_cheque () {
    return vl_cheque;
  }

  public void setVl_cheque (double vl_cheque) {
    this.vl_cheque = vl_cheque;
  }

  public int getOid_grupo_nota_fiscal () {
    return oid_grupo_nota_fiscal;
  }

  public void setOid_grupo_nota_fiscal (int oid_grupo_nota_fiscal) {
    this.oid_grupo_nota_fiscal = oid_grupo_nota_fiscal;
  }

  public double getNr_peso_medio () {
    return nr_peso_medio;
  }

  public void setNr_peso_medio (double nr_peso_medio) {
    this.nr_peso_medio = nr_peso_medio;
  }

  public int getOid_estrutura_fornecedor () {
    return oid_estrutura_fornecedor;
  }

  public void setOid_estrutura_fornecedor (int oid_estrutura_fornecedor) {
    this.oid_estrutura_fornecedor = oid_estrutura_fornecedor;
  }

  public int getOid_estrutura_produto () {
    return oid_estrutura_produto;
  }

  public void setOid_estrutura_produto (int oid_estrutura_produto) {
    this.oid_estrutura_produto = oid_estrutura_produto;
  }

  public int getOid_nivel_produto1 () {
    return oid_nivel_produto1;
  }

  public void setOid_nivel_produto1 (int oid_nivel_produto1) {
    this.oid_nivel_produto1 = oid_nivel_produto1;
  }

  public int getOid_nivel_produto2 () {
    return oid_nivel_produto2;
  }

  public void setOid_nivel_produto2 (int oid_nivel_produto2) {
    this.oid_nivel_produto2 = oid_nivel_produto2;
  }

  public int getOid_nivel_produto3 () {
    return oid_nivel_produto3;
  }

  public void setOid_nivel_produto3 (int oid_nivel_produto3) {
    this.oid_nivel_produto3 = oid_nivel_produto3;
  }

  public int getOid_nivel_produto4 () {
    return oid_nivel_produto4;
  }

  public void setOid_nivel_produto4 (int oid_nivel_produto4) {
    this.oid_nivel_produto4 = oid_nivel_produto4;
  }

  public int getOid_nivel_produto5 () {
    return oid_nivel_produto5;
  }

  public void setOid_nivel_produto5 (int oid_nivel_produto5) {
    this.oid_nivel_produto5 = oid_nivel_produto5;
  }

  public String getCd_nivel_produto () {
    return cd_nivel_produto;
  }

  public void setCd_nivel_produto (String cd_nivel_produto) {
    this.cd_nivel_produto = cd_nivel_produto;
  }

  public String getNm_nivel_produto () {
    return nm_nivel_produto;
  }

  public void setNm_nivel_produto (String nm_nivel_produto) {
    this.nm_nivel_produto = nm_nivel_produto;
  }

  public String getNm_estrutura_produto () {
    return nm_estrutura_produto;
  }

  public void setNm_estrutura_produto (String nm_estrutura_produto) {
    this.nm_estrutura_produto = nm_estrutura_produto;
  }

  public String getCd_estrutura_produto () {
    return cd_estrutura_produto;
  }

  public void setCd_estrutura_produto (String cd_estrutura_produto) {
    this.cd_estrutura_produto = cd_estrutura_produto;
  }

  public String getNm_canhoto () {
    return nm_canhoto;
  }

  public void setNm_canhoto (String nm_canhoto) {
    this.nm_canhoto = nm_canhoto;
  }

  public String getNr_placa () {
    return nr_placa;
  }

  public void setNr_placa (String nr_placa) {
    this.nr_placa = nr_placa;
  }

  public int getOid_entrega () {
    return oid_entrega;
  }

  public void setOid_entrega (int oid_entrega) {
    this.oid_entrega = oid_entrega;
  }

  public double getVl_imposto_creditado () {
    return vl_imposto_creditado;
  }

  public void setVl_imposto_creditado (double vl_imposto_creditado) {
    this.vl_imposto_creditado = vl_imposto_creditado;
  }

  public String getDt_acerto () {
    return dt_acerto;
  }

  public void setDt_acerto (String dt_acerto) {
    this.dt_acerto = dt_acerto;
  }

  public String getCd_banco () {
    return cd_banco;
  }

  public void setCd_banco (String cd_banco) {
    this.cd_banco = cd_banco;
  }

  public String getDt_cheque () {
    return dt_cheque;
  }

  public void setDt_cheque (String dt_cheque) {
    this.dt_cheque = dt_cheque;
  }

  public String getNr_agencia () {
    return nr_agencia;
  }

  public void setNr_agencia (String nr_agencia) {
    this.nr_agencia = nr_agencia;
  }

  public String getNr_cheque () {
    return nr_cheque;
  }

  public void setNr_cheque (String nr_cheque) {
    this.nr_cheque = nr_cheque;
  }

  public String getNr_conta () {
    return nr_conta;
  }

  public void setNr_conta (String nr_conta) {
    this.nr_conta = nr_conta;
  }

  public double getVl_dinheiro () {
    return vl_dinheiro;
  }

  public void setVl_dinheiro (double vl_dinheiro) {
    this.vl_dinheiro = vl_dinheiro;
  }

  public double getVl_moeda () {
    return vl_moeda;
  }

  public void setVl_moeda (double vl_moeda) {
    this.vl_moeda = vl_moeda;
  }

  public double getVl_vale () {
    return vl_vale;
  }

  public void setVl_vale (double vl_vale) {
    this.vl_vale = vl_vale;
  }

  public String getNm_fornecedor () {
    return nm_fornecedor;
  }

  public void setNm_fornecedor (String nm_fornecedor) {
    this.nm_fornecedor = nm_fornecedor;
  }

  public int getOid_lote_recebimento () {
    return oid_lote_recebimento;
  }

  public void setOid_lote_recebimento (int oid_lote_recebimento) {
    this.oid_lote_recebimento = oid_lote_recebimento;
  }

  public String getDt_compensacao () {
    return dt_compensacao;
  }

  public void setDt_compensacao (String dt_compensacao) {
    this.dt_compensacao = dt_compensacao;
  }

  public int getNr_lote_recebimento () {
    return nr_lote_recebimento;
  }

  public void setNr_lote_recebimento (int nr_lote_recebimento) {
    this.nr_lote_recebimento = nr_lote_recebimento;
  }

  public double getVl_lote () {
    return vl_lote;
  }

  public void setVl_lote (double vl_lote) {
    this.vl_lote = vl_lote;
  }

  public String getNm_tipo_documento () {
    return nm_tipo_documento;
  }

  public void setNm_tipo_documento (String nm_tipo_documento) {
    this.nm_tipo_documento = nm_tipo_documento;
  }

  public String getCd_banco_lote () {
    return cd_banco_lote;
  }

  public void setCd_banco_lote (String cd_banco_lote) {
    this.cd_banco_lote = cd_banco_lote;
  }

  public String getNm_situacao () {
    return nm_situacao;
  }

  public void setNm_situacao (String nm_situacao) {
    this.nm_situacao = nm_situacao;
  }

  public String getNr_agencia_lote () {
    return nr_agencia_lote;
  }

  public void setNr_agencia_lote (String nr_agencia_lote) {
    this.nr_agencia_lote = nr_agencia_lote;
  }

  public String getNr_conta_lote () {
    return nr_conta_lote;
  }

  public void setNr_conta_lote (String nr_conta_lote) {
    this.nr_conta_lote = nr_conta_lote;
  }

  public String getDia_semana () {
    return dia_semana;
  }

  public void setDia_semana (String dia_semana) {
    this.dia_semana = dia_semana;
  }

  public int getOid_cheque_cliente () {
    return oid_cheque_cliente;
  }

  public void setOid_cheque_cliente (int oid_cheque_cliente) {
    this.oid_cheque_cliente = oid_cheque_cliente;
  }

  public String getTx_mensagem () {
    return tx_mensagem;
  }

  public void setTx_mensagem (String tx_mensagem) {
    this.tx_mensagem = tx_mensagem;
  }

  public int getNr_compromisso () {
    return nr_compromisso;
  }

  public void setNr_compromisso (int nr_compromisso) {
    this.nr_compromisso = nr_compromisso;
  }

  public String getDt_pagamento () {
    return dt_pagamento;
  }

  public void setDt_pagamento (String dt_pagamento) {
    this.dt_pagamento = dt_pagamento;
  }

  public double getVl_compromisso () {
    return vl_compromisso;
  }

  public void setVl_compromisso (double vl_compromisso) {
    this.vl_compromisso = vl_compromisso;
  }

  public boolean isIncluiduplicata () {
    return incluiduplicata;
  }

  public void setIncluiduplicata (boolean incluiduplicata) {
    this.incluiduplicata = incluiduplicata;
  }

  public String getCd_modelo_nota () {
    return cd_modelo_nota;
  }

  public void setCd_modelo_nota (String cd_modelo_nota) {
    this.cd_modelo_nota = cd_modelo_nota;
  }

  public int getNr_caixa2 () {
    return nr_caixa2;
  }

  public void setNr_caixa2 (int nr_caixa2) {
    this.nr_caixa2 = nr_caixa2;
  }

  public String getDt_pagamento_final () {
    return dt_pagamento_final;
  }

  public void setDt_pagamento_final (String dt_pagamento_final) {
    this.dt_pagamento_final = dt_pagamento_final;
  }

  public String getDt_vencimento () {
    return dt_vencimento;
  }

  public void setDt_vencimento (String dt_vencimento) {
    this.dt_vencimento = dt_vencimento;
  }

  public String getDt_vencimento_final () {
    return dt_vencimento_final;
  }

  public void setDt_vencimento_final (String dt_vencimento_final) {
    this.dt_vencimento_final = dt_vencimento_final;
  }

  public String getNm_banco () {
    return nm_banco;
  }

  public void setNm_banco (String nm_banco) {
    this.nm_banco = nm_banco;
  }

  public int getNr_documento () {
    return nr_documento;
  }

  public void setNr_documento (int nr_documento) {
    this.nr_documento = nr_documento;
  }

  public int getNr_parcela () {
    return nr_parcela;
  }

  public void setNr_parcela (int nr_parcela) {
    this.nr_parcela = nr_parcela;
  }

  public String getNm_forma_pagamento () {
    return nm_forma_pagamento;
  }

  public void setNm_forma_pagamento (String nm_forma_pagamento) {
    this.nm_forma_pagamento = nm_forma_pagamento;
  }

  public double getVl_saldo () {
    return vl_saldo;
  }

  public void setVl_saldo (double vl_saldo) {
    this.vl_saldo = vl_saldo;
  }

  public int getOid_centro_custo () {
    return oid_centro_custo;
  }

  public void setOid_centro_custo (int oid_centro_custo) {
    this.oid_centro_custo = oid_centro_custo;
  }

  public int getOid_conta () {
    return oid_conta;
  }

  public void setOid_conta (int oid_conta) {
    this.oid_conta = oid_conta;
  }

  public String getNm_entrada () {
    return nm_entrada;
  }

  public void setNm_entrada (String nm_entrada) {
    this.nm_entrada = nm_entrada;
  }

  public String getNm_vencimento () {
    return nm_vencimento;
  }

  public void setNm_vencimento (String nm_vencimento) {
    this.nm_vencimento = nm_vencimento;
  }

  public String getCd_conta () {
    return cd_conta;
  }

  public void setCd_conta (String cd_conta) {
    this.cd_conta = cd_conta;
  }

  public String getNm_conta () {
    return nm_conta;
  }

  public void setNm_conta (String nm_conta) {
    this.nm_conta = nm_conta;
  }

  public String getOid_conta_corrente () {
    return oid_conta_corrente;
  }

  public void setOid_conta_corrente (String oid_conta_corrente) {
    this.oid_conta_corrente = oid_conta_corrente;
  }

  public String getDt_compensacao_final () {
    return dt_compensacao_final;
  }

  public void setDt_compensacao_final (String dt_compensacao_final) {
    this.dt_compensacao_final = dt_compensacao_final;
  }

  public String getDt_programada () {
    return dt_programada;
  }

  public void setDt_programada (String dt_programada) {
    this.dt_programada = dt_programada;
  }

  public String getDt_programada_final () {
    return dt_programada_final;
  }

  public void setDt_programada_final (String dt_programada_final) {
    this.dt_programada_final = dt_programada_final;
  }

  public String getNm_favorecido () {
    return nm_favorecido;
  }

  public void setNm_favorecido (String nm_favorecido) {
    this.nm_favorecido = nm_favorecido;
  }

  public int getNr_lote_pagamento () {
    return nr_lote_pagamento;
  }

  public void setNr_lote_pagamento (int nr_lote_pagamento) {
    this.nr_lote_pagamento = nr_lote_pagamento;
  }

  public String getNm_compensacao () {
    return nm_compensacao;
  }

  public void setNm_compensacao (String nm_compensacao) {
    this.nm_compensacao = nm_compensacao;
  }

  public String getNm_emissao () {
    return nm_emissao;
  }

  public void setNm_emissao (String nm_emissao) {
    this.nm_emissao = nm_emissao;
  }

  public String getNm_programada () {
    return nm_programada;
  }

  public void setNm_programada (String nm_programada) {
    this.nm_programada = nm_programada;
  }

  public int getOid_tipo_documento () {
    return oid_tipo_documento;
  }

  public void setOid_tipo_documento (int oid_tipo_documento) {
    this.oid_tipo_documento = oid_tipo_documento;
  }

  public String getNm_documento () {
    return nm_documento;
  }

  public void setNm_documento (String nm_documento) {
    this.nm_documento = nm_documento;
  }

  public double getVl_acrescimo_30_dias () {
    return vl_acrescimo_30_dias;
  }

  public void setVl_acrescimo_30_dias (double vl_acrescimo_30_dias) {
    this.vl_acrescimo_30_dias = vl_acrescimo_30_dias;
  }

  public int getNr_acerto () {
    return nr_acerto;
  }

  public void setNr_acerto (int nr_acerto) {
    this.nr_acerto = nr_acerto;
  }

  public int getOid_tipo_pedido () {
    return oid_tipo_pedido;
  }

  public void setOid_tipo_pedido (int oid_tipo_pedido) {
    this.oid_tipo_pedido = oid_tipo_pedido;
  }

  public String getNr_pedido_palm () {
    return nr_pedido_palm;
  }

  public void setNr_pedido_palm (String nr_pedido_palm) {
    this.nr_pedido_palm = nr_pedido_palm;
  }

  public double getVl_troca () {
    return vl_troca;
  }

  public void setVl_troca (double vl_troca) {
    this.vl_troca = vl_troca;
  }

  public double getVl_total_troca () {
    return vl_total_troca;
  }

  public void setVl_total_troca (double vl_total_troca) {
    this.vl_total_troca = vl_total_troca;
  }

  public double getVl_unitario_troca () {
    return vl_unitario_troca;
  }

  public void setVl_unitario_troca (double vl_unitario_troca) {
    this.vl_unitario_troca = vl_unitario_troca;
  }

  public String getOid_fornecedor () {
    return oid_fornecedor;
  }

  public void setOid_fornecedor (String oid_fornecedor) {
    this.oid_fornecedor = oid_fornecedor;
  }

  public String getDt_final () {
    return dt_final;
  }

  public void setDt_final (String dt_final) {
    this.dt_final = dt_final;
  }

  public String getDt_inicial () {
    return dt_inicial;
  }

  public void setDt_inicial (String dt_inicial) {
    this.dt_inicial = dt_inicial;
  }

  public String getDm_critica_estoque () {
    return dm_critica_estoque;
  }

  public void setDm_critica_estoque (String dm_critica_estoque) {
    this.dm_critica_estoque = dm_critica_estoque;
  }

  public String getDm_critica_financeira () {
    return dm_critica_financeira;
  }

  public void setDm_critica_financeira (String dm_critica_financeira) {
    this.dm_critica_financeira = dm_critica_financeira;
  }

  public String getOid_unidade_contabil () {
    return oid_unidade_contabil;
  }

  public void setOid_unidade_contabil (String oid_unidade_contabil) {
    this.oid_unidade_contabil = oid_unidade_contabil;
  }

  public double getVl_negativo () {
    return vl_negativo;
  }

  public void setVl_negativo (double vl_negativo) {
    this.vl_negativo = vl_negativo;
  }

  public double getVl_positivo () {
    return vl_positivo;
  }

  public void setVl_positivo (double vl_positivo) {
    this.vl_positivo = vl_positivo;
  }

  public String getNm_arquivo () {
    return nm_arquivo;
  }

  public void setNm_arquivo (String nm_arquivo) {
    this.nm_arquivo = nm_arquivo;
  }

  public String getCd_cliente () {
    return cd_cliente;
  }

  public void setCd_cliente (String cd_cliente) {
    this.cd_cliente = cd_cliente;
  }

  public double getNr_peso_liquido () {
    return nr_peso_liquido;
  }

  public void setNr_peso_liquido (double nr_peso_liquido) {
    this.nr_peso_liquido = nr_peso_liquido;
  }

  public String getNm_vendedor () {
    return nm_vendedor;
  }

  public void setNm_vendedor (String nm_vendedor) {
    this.nm_vendedor = nm_vendedor;
  }

  public int getOid_carga_entrega () {
    return oid_carga_entrega;
  }

  public void setOid_carga_entrega (int oid_carga_entrega) {
    this.oid_carga_entrega = oid_carga_entrega;
  }

  public double getVl_compra () {
    return vl_compra;
  }

  public void setVl_compra (double vl_compra) {
    this.vl_compra = vl_compra;
  }

  public double getVl_venda () {
    return vl_venda;
  }

  public void setVl_venda (double vl_venda) {
    this.vl_venda = vl_venda;
  }

  public double getPe_margem () {
    return pe_margem;
  }

  public void setPe_margem (double pe_margem) {
    this.pe_margem = pe_margem;
  }

  public String getNr_caixa_estoque () {
    return nr_caixa_estoque;
  }

  public void setNr_caixa_estoque (String nr_caixa_estoque) {
    this.nr_caixa_estoque = nr_caixa_estoque;
  }

  public String getDm_dia () {
    return dm_dia;
  }

  public void setDm_dia (String dm_dia) {
    this.dm_dia = dm_dia;
  }

  public String getCd_rota () {
    return cd_rota;
  }

  public void setCd_rota (String cd_rota) {
    this.cd_rota = cd_rota;
  }

  public String getDm_credito () {
    return dm_credito;
  }

  public void setDm_credito (String dm_credito) {
    this.dm_credito = dm_credito;
  }

  public double getPe_permitido () {
    return pe_permitido;
  }

  public void setPe_permitido (double pe_permitido) {
    this.pe_permitido = pe_permitido;
  }

  public String getNm_rotas () {
    return nm_rotas;
  }

  public void setNm_rotas (String nm_rotas) {
    this.nm_rotas = nm_rotas;
  }

  public String getDt_cadastro () {
    return dt_cadastro;
  }

  public void setDt_cadastro (String dt_cadastro) {
    this.dt_cadastro = dt_cadastro;
  }

  public String getNr_cnpj_cpf_cobranca () {
    return nr_cnpj_cpf_cobranca;
  }

  public void setNr_cnpj_cpf_cobranca (String nr_cnpj_cpf_cobranca) {
    this.nr_cnpj_cpf_cobranca = nr_cnpj_cpf_cobranca;
  }

  public String getNm_inscricao_estadual_cobranca () {
    return nm_inscricao_estadual_cobranca;
  }

  public void setNm_inscricao_estadual_cobranca (String nm_inscricao_estadual_cobranca) {
    this.nm_inscricao_estadual_cobranca = nm_inscricao_estadual_cobranca;
  }

  public String getNr_telefone_cobranca () {
    return nr_telefone_cobranca;
  }

  public void setNr_telefone_cobranca (String nr_telefone_cobranca) {
    this.nr_telefone_cobranca = nr_telefone_cobranca;
  }

  public String getNm_endereco_cobranca () {
    return nm_endereco_cobranca;
  }

  public void setNm_endereco_cobranca (String nm_endereco_cobranca) {
    this.nm_endereco_cobranca = nm_endereco_cobranca;
  }

  public String getNm_bairro_cobranca () {
    return nm_bairro_cobranca;
  }

  public void setNm_bairro_cobranca (String nm_bairro_cobranca) {
    this.nm_bairro_cobranca = nm_bairro_cobranca;
  }

  public String getNm_cidade_cobranca () {
    return nm_cidade_cobranca;
  }

  public void setNm_cidade_cobranca (String nm_cidade_cobranca) {
    this.nm_cidade_cobranca = nm_cidade_cobranca;
  }

  public String getNr_cep_cobranca () {
    return nr_cep_cobranca;
  }

  public void setNr_cep_cobranca (String nr_cep_cobranca) {
    this.nr_cep_cobranca = nr_cep_cobranca;
  }

  public String getNm_estado_cobranca () {
    return nm_estado_cobranca;
  }

  public void setNm_estado_cobranca (String nm_estado_cobranca) {
    this.nm_estado_cobranca = nm_estado_cobranca;
  }

  public String getCd_produto_lista () {
    return cd_produto_lista;
  }

  public void setCd_produto_lista (String cd_produto_lista) {
    this.cd_produto_lista = cd_produto_lista;
  }

  public String getNm_fantasia () {
    return nm_fantasia;
  }

  public void setNm_fantasia (String nm_fantasia) {
    this.nm_fantasia = nm_fantasia;
  }

  public double getVl_desconto () {
    return vl_desconto;
  }

  public void setVl_desconto (double vl_desconto) {
    this.vl_desconto = vl_desconto;
  }

  public double getVl_liquido_nota_fiscal () {
    return vl_liquido_nota_fiscal;
  }

  public void setVl_liquido_nota_fiscal (double vl_liquido_nota_fiscal) {
    this.vl_liquido_nota_fiscal = vl_liquido_nota_fiscal;
  }

  public String getOid_Supervisor () {
    return oid_Supervisor;
  }

  public void setOid_Supervisor (String oid_Supervisor) {
    this.oid_Supervisor = oid_Supervisor;
  }

  public long getNr_livro () {
    return nr_livro;
  }

  public void setNr_livro (long nr_livro) {
    this.nr_livro = nr_livro;
  }

  public long getNr_pagina () {
    return nr_pagina;
  }

  public void setNr_pagina (long nr_pagina) {
    this.nr_pagina = nr_pagina;
  }

  public double getNr_qt_atendido_venda () {
    return nr_qt_atendido_venda;
  }

  public void setNr_qt_atendido_venda (double nr_qt_atendido_venda) {
    this.nr_qt_atendido_venda = nr_qt_atendido_venda;
  }

  public int getNr_qt_nota_fiscal () {
    return nr_qt_nota_fiscal;
  }

  public void setNr_qt_nota_fiscal (int nr_qt_nota_fiscal) {
    this.nr_qt_nota_fiscal = nr_qt_nota_fiscal;
  }

  public String getNm_credito_1 () {
    return nm_credito_1;
  }

  public void setNm_credito_1 (String nm_credito_1) {
    this.nm_credito_1 = nm_credito_1;
  }

  public String getNm_credito_2 () {
    return nm_credito_2;
  }

  public void setNm_credito_2 (String nm_credito_2) {
    this.nm_credito_2 = nm_credito_2;
  }

  public String getNm_credito_3 () {
    return nm_credito_3;
  }

  public void setNm_credito_3 (String nm_credito_3) {
    this.nm_credito_3 = nm_credito_3;
  }

  public String getNm_credito_4 () {
    return nm_credito_4;
  }

  public void setNm_credito_4 (String nm_credito_4) {
    this.nm_credito_4 = nm_credito_4;
  }

  public String getNm_debito_1 () {
    return nm_debito_1;
  }

  public void setNm_debito_1 (String nm_debito_1) {
    this.nm_debito_1 = nm_debito_1;
  }

  public String getNm_debito_2 () {
    return nm_debito_2;
  }

  public void setNm_debito_2 (String nm_debito_2) {
    this.nm_debito_2 = nm_debito_2;
  }

  public String getNm_debito_3 () {
    return nm_debito_3;
  }

  public void setNm_debito_3 (String nm_debito_3) {
    this.nm_debito_3 = nm_debito_3;
  }

  public String getNm_debito_4 () {
    return nm_debito_4;
  }

  public void setNm_debito_4 (String nm_debito_4) {
    this.nm_debito_4 = nm_debito_4;
  }

  public String getNm_ecredito_1 () {
    return nm_ecredito_1;
  }

  public void setNm_ecredito_1 (String nm_ecredito_1) {
    this.nm_ecredito_1 = nm_ecredito_1;
  }

  public String getNm_ecredito_2 () {
    return nm_ecredito_2;
  }

  public void setNm_ecredito_2 (String nm_ecredito_2) {
    this.nm_ecredito_2 = nm_ecredito_2;
  }

  public String getNm_edebito_1 () {
    return nm_edebito_1;
  }

  public void setNm_edebito_1 (String nm_edebito_1) {
    this.nm_edebito_1 = nm_edebito_1;
  }

  public String getNm_edebito_2 () {
    return nm_edebito_2;
  }

  public void setNm_edebito_2 (String nm_edebito_2) {
    this.nm_edebito_2 = nm_edebito_2;
  }

  public double getVl_credito_1 () {
    return vl_credito_1;
  }

  public void setVl_credito_1 (double vl_credito_1) {
    this.vl_credito_1 = vl_credito_1;
  }

  public double getVl_credito_2 () {
    return vl_credito_2;
  }

  public void setVl_credito_2 (double vl_credito_2) {
    this.vl_credito_2 = vl_credito_2;
  }

  public double getVl_credito_3 () {
    return vl_credito_3;
  }

  public void setVl_credito_3 (double vl_credito_3) {
    this.vl_credito_3 = vl_credito_3;
  }

  public double getVl_credito_4 () {
    return vl_credito_4;
  }

  public void setVl_credito_4 (double vl_credito_4) {
    this.vl_credito_4 = vl_credito_4;
  }

  public double getVl_debito_1 () {
    return vl_debito_1;
  }

  public void setVl_debito_1 (double vl_debito_1) {
    this.vl_debito_1 = vl_debito_1;
  }

  public double getVl_debito_2 () {
    return vl_debito_2;
  }

  public void setVl_debito_2 (double vl_debito_2) {
    this.vl_debito_2 = vl_debito_2;
  }

  public double getVl_debito_3 () {
    return vl_debito_3;
  }

  public void setVl_debito_3 (double vl_debito_3) {
    this.vl_debito_3 = vl_debito_3;
  }

  public double getVl_debito_4 () {
    return vl_debito_4;
  }

  public void setVl_debito_4 (double vl_debito_4) {
    this.vl_debito_4 = vl_debito_4;
  }

  public double getVl_ecredito_1 () {
    return vl_ecredito_1;
  }

  public void setVl_ecredito_1 (double vl_ecredito_1) {
    this.vl_ecredito_1 = vl_ecredito_1;
  }

  public double getVl_ecredito_2 () {
    return vl_ecredito_2;
  }

  public void setVl_ecredito_2 (double vl_ecredito_2) {
    this.vl_ecredito_2 = vl_ecredito_2;
  }

  public double getVl_edebito_1 () {
    return vl_edebito_1;
  }

  public void setVl_edebito_1 (double vl_edebito_1) {
    this.vl_edebito_1 = vl_edebito_1;
  }

  public double getVl_edebito_2 () {
    return vl_edebito_2;
  }

  public void setVl_edebito_2 (double vl_edebito_2) {
    this.vl_edebito_2 = vl_edebito_2;
  }

  public double getVl_markup () {
    return vl_markup;
  }

  public void setVl_markup (double vl_markup) {
    this.vl_markup = vl_markup;
  }

  public double getVl_caixa () {
    return vl_caixa;
  }

  public void setVl_caixa (double vl_caixa) {
    this.vl_caixa = vl_caixa;
  }

  public int getNr_Requisicao_Consumo () {
    return nr_Requisicao_Consumo;
  }

  public void setNr_Requisicao_Consumo (int nr_Requisicao_Consumo) {
    this.nr_Requisicao_Consumo = nr_Requisicao_Consumo;
  }

  public int getNr_Requisicao_Consumo_final () {
    return nr_Requisicao_Consumo_final;
  }

  public void setNr_Requisicao_Consumo_final (int nr_Requisicao_Consumo_final) {
    this.nr_Requisicao_Consumo_final = nr_Requisicao_Consumo_final;
  }

  public int getOid_Requisicao_Consumo () {
    return oid_Requisicao_Consumo;
  }

  public void setOid_Requisicao_Consumo (int oid_Requisicao_Consumo) {
    this.oid_Requisicao_Consumo = oid_Requisicao_Consumo;
  }

  public int getOid_tipo_Requisicao_Consumo () {
    return oid_tipo_Requisicao_Consumo;
  }

  public void setOid_tipo_Requisicao_Consumo (int oid_tipo_Requisicao_Consumo) {
    this.oid_tipo_Requisicao_Consumo = oid_tipo_Requisicao_Consumo;
  }

  public String getNm_tipo_Requisicao_Consumo () {
    return nm_tipo_Requisicao_Consumo;
  }

  public void setNm_tipo_Requisicao_Consumo (String nm_tipo_Requisicao_Consumo) {
    this.nm_tipo_Requisicao_Consumo = nm_tipo_Requisicao_Consumo;
  }

  public int getNr_qt_cliente_atendido () {
    return nr_qt_cliente_atendido;
  }

  public void setNr_qt_cliente_atendido (int nr_qt_cliente_atendido) {
    this.nr_qt_cliente_atendido = nr_qt_cliente_atendido;
  }

  public int getNr_qt_cliente_ativo () {
    return nr_qt_cliente_ativo;
  }

  public void setNr_qt_cliente_ativo (int nr_qt_cliente_ativo) {
    this.nr_qt_cliente_ativo = nr_qt_cliente_ativo;
  }

  public int getNr_qt_cliente_cadastrado () {
    return nr_qt_cliente_cadastrado;
  }

  public void setNr_qt_cliente_cadastrado (int nr_qt_cliente_cadastrado) {
    this.nr_qt_cliente_cadastrado = nr_qt_cliente_cadastrado;
  }

  public int getNr_qt_item () {
    return nr_qt_item;
  }

  public void setNr_qt_item (int nr_qt_item) {
    this.nr_qt_item = nr_qt_item;
  }

  public double getPc_qt_cliente_positivo () {
    return pc_qt_cliente_positivo;
  }

  public void setPc_qt_cliente_positivo (double pc_qt_cliente_positivo) {
    this.pc_qt_cliente_positivo = pc_qt_cliente_positivo;
  }

  public long getNr_quantidade_consumo () {
    return nr_quantidade_consumo;
  }

  public void setNr_quantidade_consumo (long nr_quantidade_consumo) {
    this.nr_quantidade_consumo = nr_quantidade_consumo;
  }

  public String getOid_pessoa_fornecedor () {
    return oid_pessoa_fornecedor;
  }

  public void setOid_pessoa_fornecedor (String oid_pessoa_fornecedor) {
    this.oid_pessoa_fornecedor = oid_pessoa_fornecedor;
  }

  public int getOid_cidade () {
    return oid_cidade;
  }

  public void setOid_cidade (int oid_cidade) {
    this.oid_cidade = oid_cidade;
  }

  public int getOid_segmentacao () {
    return oid_segmentacao;
  }

  public void setOid_segmentacao (int oid_segmentacao) {
    this.oid_segmentacao = oid_segmentacao;
  }

  public int getNr_tabela_anterior () {
    return nr_tabela_anterior;
  }

  public void setNr_tabela_anterior (int nr_tabela_anterior) {
    this.nr_tabela_anterior = nr_tabela_anterior;
  }

  public double getVl_produto_atual () {
    return vl_produto_atual;
  }

  public void setVl_produto_atual (double vl_produto_atual) {
    this.vl_produto_atual = vl_produto_atual;
  }

  public double getVl_diferenca () {
    return vl_diferenca;
  }

  public void setVl_diferenca (double vl_diferenca) {
    this.vl_diferenca = vl_diferenca;
  }

  public String getCd_estrutura_produto_final () {
    return cd_estrutura_produto_final;
  }

  public void setCd_estrutura_produto_final (String cd_estrutura_produto_final) {
    this.cd_estrutura_produto_final = cd_estrutura_produto_final;
  }

  public String getNm_estrutura_produto_final () {
    return nm_estrutura_produto_final;
  }

  public void setNm_estrutura_produto_final (String nm_estrutura_produto_final) {
    this.nm_estrutura_produto_final = nm_estrutura_produto_final;
  }

  public double getVl_acrescimo_21_dias_diferenca () {
    return vl_acrescimo_21_dias_diferenca;
  }

  public void setVl_acrescimo_21_dias_diferenca (
      double vl_acrescimo_21_dias_diferenca) {
    this.vl_acrescimo_21_dias_diferenca = vl_acrescimo_21_dias_diferenca;
  }

  public double getVl_acrescimo_28_dias_diferenca () {
    return vl_acrescimo_28_dias_diferenca;
  }

  public void setVl_acrescimo_28_dias_diferenca (
      double vl_acrescimo_28_dias_diferenca) {
    this.vl_acrescimo_28_dias_diferenca = vl_acrescimo_28_dias_diferenca;
  }

  public double getVl_acrescimo_30_dias_diferenca () {
    return vl_acrescimo_30_dias_diferenca;
  }

  public void setVl_acrescimo_30_dias_diferenca (
      double vl_acrescimo_30_dias_diferenca) {
    this.vl_acrescimo_30_dias_diferenca = vl_acrescimo_30_dias_diferenca;
  }

  public double getVl_desconto_7_dias_diferenca () {
    return vl_desconto_7_dias_diferenca;
  }

  public void setVl_desconto_7_dias_diferenca (
      double vl_desconto_7_dias_diferenca) {
    this.vl_desconto_7_dias_diferenca = vl_desconto_7_dias_diferenca;
  }

  public double getVl_desconto_avista_diferenca () {
    return vl_desconto_avista_diferenca;
  }

  public void setVl_desconto_avista_diferenca (
      double vl_desconto_avista_diferenca) {
    this.vl_desconto_avista_diferenca = vl_desconto_avista_diferenca;
  }

  public double getVl_produto_diferenca () {
    return vl_produto_diferenca;
  }

  public void setVl_produto_diferenca (double vl_produto_diferenca) {
    this.vl_produto_diferenca = vl_produto_diferenca;
  }

  public double getVL_Diferenca_Aliq_ICMS () {
    return VL_Diferenca_Aliq_ICMS;
  }

  public void setVL_Diferenca_Aliq_ICMS (double diferenca_Aliq_ICMS) {
    VL_Diferenca_Aliq_ICMS = diferenca_Aliq_ICMS;
  }

  public double getVl_total_despesas () {
    return vl_total_despesas;
  }

  public String getDM_Tipo_Cobranca () {
    return DM_Tipo_Cobranca;
  }

  public String getCD_Carteira () {
    return CD_Carteira;
  }

  public double getPE_Comissao () {
    return PE_Comissao;
  }

  public int getNR_Dias_Vencimento () {
    return NR_Dias_Vencimento;
  }

  public String getNM_Grupo_Economico () {
    return NM_Grupo_Economico;
  }

  public String getM_Tipo_Cobranca () {
    return M_Tipo_Cobranca;
  }

  public void setVl_total_despesas (double vl_total_despesas) {
    this.vl_total_despesas = vl_total_despesas;
  }

  public void setDescDM_Situacao (String descDM_Situacao) {
    this.descDM_Situacao = descDM_Situacao;
  }

  public void setDM_Tipo_Cobranca (String DM_Tipo_Cobranca) {
    this.DM_Tipo_Cobranca = DM_Tipo_Cobranca;
  }

  public void setCD_Carteira (String CD_Carteira) {
    this.CD_Carteira = CD_Carteira;
  }

  public void setPE_Comissao (double PE_Comissao) {
    this.PE_Comissao = PE_Comissao;
  }

  public void setNR_Dias_Vencimento (int NR_Dias_Vencimento) {
    this.NR_Dias_Vencimento = NR_Dias_Vencimento;
  }

  public void setNM_Grupo_Economico (String NM_Grupo_Economico) {
    this.NM_Grupo_Economico = NM_Grupo_Economico;
  }

  public void setM_Tipo_Cobranca (String M_Tipo_Cobranca) {
    this.M_Tipo_Cobranca = M_Tipo_Cobranca;
  }

public String getOid_natureza_operacao() {
	return oid_natureza_operacao;
}

public void setOid_natureza_operacao(String oid_natureza_operacao) {
	this.oid_natureza_operacao = oid_natureza_operacao;
}

public String getDT_Bloqueio() {
	return DT_Bloqueio;
}

public void setDT_Bloqueio(String bloqueio) {
	DT_Bloqueio = bloqueio;
}

public String getDT_Ultimo_Movimento() {
	return DT_Ultimo_Movimento;
}

public void setDT_Ultimo_Movimento(String ultimo_Movimento) {
	DT_Ultimo_Movimento = ultimo_Movimento;
}

public int getNR_Dias_Bloqueio() {
	return NR_Dias_Bloqueio;
}

public void setNR_Dias_Bloqueio(int dias_Bloqueio) {
	NR_Dias_Bloqueio = dias_Bloqueio;
}

}