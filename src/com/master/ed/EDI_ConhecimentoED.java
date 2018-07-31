package com.master.ed;

public class EDI_ConhecimentoED extends MasterED{

// Dados remetente
  private String NR_CNPJ_CPF_Remetente;
  private String NM_INSCRICAO_Remetente;
  private String NM_Endereco_Remetente;
  private String NM_Bairro_Remetente;
  private String NM_Cidade_Remetente;
  private String NR_CEP_Remetente;
  private String CD_Estado_Remetente;
  private String DT_Embarque;
  private String NM_Razao_Social_Remetente;
  private long OID_Cidade_Remetente;

// Dados Destinatario
  private String NM_Razao_Social_Destinatario;
  private String NR_CNPJ_CPF_Destinatario;
  private String NM_INSCRICAO_Destinatario;
  private String NM_Endereco_Destinatario;
  private String NM_Bairro_Destinatario;
  private String NM_Cidade_Destinatario;
  private String NR_CEP_Destinatario;
  private String CD_Estado_Destinatario;
  private String CD_Area_Frete_Destinatario;
  private String CD_Cidade_Destinatario;
  private long OID_Cidade_Destinatario;

// Dados Nota Fiscal
  private String CD_Condicao_Frete;
  private String NR_NOTA_FISCAL;
  private String DT_Emissao_Nota_Fiscal;
  private String NM_Natureza_Mercadoria;
  private double NR_Volumes;
  private double VL_Nota_Fiscal;
  private double VL_Peso;

// Dados Consignatario
  private String NM_Razao_Social_Consignatario;
  private String NR_CNPJ_CPF_Consignatario;
  private String NM_INSCRICAO_Consignatario;
  private String NM_Endereco_Consignatario;
  private String NM_Bairro_Consignatario;
  private String NM_Cidade_Consignatario;
  private String NR_CEP_Consignatario;
  private String CD_Estado_Consignatario;

// Dados Redespacho
  private String NM_Razao_Social_Redespacho;
  private String NR_CNPJ_CPF_Redespacho;
  private String NM_INSCRICAO_Redespacho;
  private String NM_Endereco_Redespacho;
  private String NM_Bairro_Redespacho;
  private String NM_Cidade_Redespacho;
  private String NR_CEP_Redespacho;
  private String CD_Estado_Redespacho;
  private String CD_Area_Frete_Redespacho;

// Dados Pagador
  private String NM_Razao_Social_Pagador;
  private String NR_CNPJ_CPF_Pagador;
  private String NM_INSCRICAO_Pagador;
  private String NM_Endereco_Pagador;
  private String NM_Bairro_Pagador;
  private String NM_Cidade_Pagador;
  private String NR_CEP_Pagador;
  private String CD_Estado_Pagador;

// outros
  private String NM_Arquivo;
  private String CD_Tipo;
  private String NM_Produto;
  private long OID_Produto;
  private String CD_Produto;
  private String NR_Pedido;
  private String NM_Natureza_Operacao;
  private String CD_Companhia;

  private String OID_EDI_Conhecimento;
  private String NR_EDI_Conhecimento;
  private String DM_Responsavel_Cobranca;
  private String DM_Tipo_Pagamento;
  private double PE_Aliquota_ICMS;
  private String TX_Observacao;
  private String DM_Isento_Seguro;
  private String NM_Atendente;
  private String NM_Pessoa_Entrega;
  private long OID_Unidade;
  private String OID_Vendedor;
  private String OID_Tabela_Frete;
  private String CD_Unidade;
  private double VL_Frete;
  private double VL_ICMS;
  private String DM_Tipo_EDI_Conhecimento;
  private String DM_Impresso;
  private String CD_CFO;
  private long OID_Taxa;
  private double VL_FRETE_PESO;
  private double VL_FRETE_VALOR;
  private double VL_SEC_CAT;
  private double VL_PEDAGIO;
  private double VL_DESPACHO;
  private double VL_OUTROS1;
  private double VL_OUTROS2;
  private double VL_TOTAL_FRETE;
  private double VL_BASE_CALCULO_ICMS;
  private String NM_Cidade_CTRC_Origem;
  private String NM_Cidade_CTRC_Destino;
  private String CD_Estado_CTRC_Origem;
  private String CD_Estado_CTRC_Destino;
  private double PE_ALIQUOTA_ICMS;
  private String CD_Referencia;
  private String VL_Nota_Fiscal_formatada;
  private String NM_Fantasia_Unidade;
  private String OID_Nota_Fiscal;
  private String DM_Situacao;
  private long NR_Duplicata;
  private String NM_Serie;
  private long OID_Estado_Destino;
  private long OID_Estado_Origem;
  private long OID_Pessoa;
  private long OID_Pessoa_Destinatario;
  private long OID_Pessoa_Consignatario;
  private long OID_Pessoa_Redespacho;
  private long OID_Pessoa_Pagador;
  private long OID_Modal;
  private long OID_Cidade;
  private long OID_Lote;
  private String NM_Natureza;
  private String DT_Emissao;

  ///###15052003 Transito
  private long NR_EDI_Conhecimento_Final;
  private long NR_EDI_Conhecimento_Inicial;
  private long OID_Coleta;
  private String DM_Tipo_Conhecimento;
  private String DT_Importacao;
  private String DT_Ocorrencia;
  private String HR_Ocorrencia;
  private String CD_Ocorrencia;
  private String DM_Tipo_EDI;
  private String NM_Ocorrencia;
  private String DM_Tipo_Padrao;

  public String getCD_Area_Frete_Destinatario() {
    return CD_Area_Frete_Destinatario;
  }
  public String getCD_Area_Frete_Redespacho() {
    return CD_Area_Frete_Redespacho;
  }
  public String getCD_CFO() {
    return CD_CFO;
  }
  public String getCD_Condicao_Frete() {
    return CD_Condicao_Frete;
  }
  public String getCD_Estado_Consignatario() {
    return CD_Estado_Consignatario;
  }
  public String getCD_Estado_CTRC_Destino() {
    return CD_Estado_CTRC_Destino;
  }
  public String getCD_Estado_CTRC_Origem() {
    return CD_Estado_CTRC_Origem;
  }
  public String getCD_Estado_Destinatario() {
    return CD_Estado_Destinatario;
  }
  public String getCD_Estado_Pagador() {
    return CD_Estado_Pagador;
  }
  public String getCD_Estado_Redespacho() {
    return CD_Estado_Redespacho;
  }
  public String getCD_Estado_Remetente() {
    return CD_Estado_Remetente;
  }
  public String getCD_Referencia() {
    return CD_Referencia;
  }
  public String getCD_Tipo() {
    return CD_Tipo;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
  }
  public String getDM_Impresso() {
    return DM_Impresso;
  }
  public String getDM_Isento_Seguro() {
    return DM_Isento_Seguro;
  }
  public String getDM_Responsavel_Cobranca() {
    return DM_Responsavel_Cobranca;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public String getDM_Tipo_EDI_Conhecimento() {
    return DM_Tipo_EDI_Conhecimento;
  }
  public String getDM_Tipo_Pagamento() {
    return DM_Tipo_Pagamento;
  }
  public String getDT_Embarque() {
    return DT_Embarque;
  }
  public String getDT_Emissao() {
    return DT_Emissao;
  }
  public String getDT_Emissao_Nota_Fiscal() {
    return DT_Emissao_Nota_Fiscal;
  }
  public String getNM_Arquivo() {
    return NM_Arquivo;
  }
  public String getNM_Atendente() {
    return NM_Atendente;
  }
  public String getNM_Bairro_Consignatario() {
    return NM_Bairro_Consignatario;
  }
  public String getNM_Bairro_Destinatario() {
    return NM_Bairro_Destinatario;
  }
  public String getNM_Bairro_Pagador() {
    return NM_Bairro_Pagador;
  }
  public String getNM_Bairro_Redespacho() {
    return NM_Bairro_Redespacho;
  }
  public String getNM_Cidade_Consignatario() {
    return NM_Cidade_Consignatario;
  }
  public String getNM_Cidade_CTRC_Destino() {
    return NM_Cidade_CTRC_Destino;
  }
  public String getNM_Cidade_CTRC_Origem() {
    return NM_Cidade_CTRC_Origem;
  }
  public String getNM_Cidade_Destinatario() {
    return NM_Cidade_Destinatario;
  }
  public String getNM_Cidade_Pagador() {
    return NM_Cidade_Pagador;
  }
  public String getNM_Cidade_Redespacho() {
    return NM_Cidade_Redespacho;
  }
  public String getNM_Cidade_Remetente() {
    return NM_Cidade_Remetente;
  }
  public String getNM_Endereco_Consignatario() {
    return NM_Endereco_Consignatario;
  }
  public String getNM_Endereco_Destinatario() {
    return NM_Endereco_Destinatario;
  }
  public String getNM_Endereco_Pagador() {
    return NM_Endereco_Pagador;
  }
  public String getNM_Endereco_Redespacho() {
    return NM_Endereco_Redespacho;
  }
  public String getNM_Endereco_Remetente() {
    return NM_Endereco_Remetente;
  }
  public String getNM_Fantasia_Unidade() {
    return NM_Fantasia_Unidade;
  }
  public String getNM_INSCRICAO_Consignatario() {
    return NM_INSCRICAO_Consignatario;
  }
  public String getNM_INSCRICAO_Destinatario() {
    return NM_INSCRICAO_Destinatario;
  }
  public String getNM_INSCRICAO_Pagador() {
    return NM_INSCRICAO_Pagador;
  }
  public String getNM_INSCRICAO_Redespacho() {
    return NM_INSCRICAO_Redespacho;
  }
  public String getNM_INSCRICAO_Remetente() {
    return NM_INSCRICAO_Remetente;
  }
  public String getNM_Natureza() {
    return NM_Natureza;
  }
  public String getNM_Natureza_Mercadoria() {
    return NM_Natureza_Mercadoria;
  }
  public String getNM_Pessoa_Entrega() {
    return NM_Pessoa_Entrega;
  }
  public String getNM_Razao_Social_Consignatario() {
    return NM_Razao_Social_Consignatario;
  }
  public String getNM_Razao_Social_Destinatario() {
    return NM_Razao_Social_Destinatario;
  }
  public String getNM_Razao_Social_Pagador() {
    return NM_Razao_Social_Pagador;
  }
  public String getNM_Razao_Social_Redespacho() {
    return NM_Razao_Social_Redespacho;
  }
  public String getNM_Razao_Social_Remetente() {
    return NM_Razao_Social_Remetente;
  }
  public String getNM_Serie() {
    return NM_Serie;
  }
  public String getNR_CEP_Consignatario() {
    return NR_CEP_Consignatario;
  }
  public String getNR_CEP_Destinatario() {
    return NR_CEP_Destinatario;
  }
  public String getNR_CEP_Pagador() {
    return NR_CEP_Pagador;
  }
  public String getNR_CEP_Redespacho() {
    return NR_CEP_Redespacho;
  }
  public String getNR_CEP_Remetente() {
    return NR_CEP_Remetente;
  }
  public String getNR_CNPJ_CPF_Consignatario() {
    return NR_CNPJ_CPF_Consignatario;
  }
  public String getNR_CNPJ_CPF_Destinatario() {
    return NR_CNPJ_CPF_Destinatario;
  }
  public String getNR_CNPJ_CPF_Pagador() {
    return NR_CNPJ_CPF_Pagador;
  }
  public String getNR_CNPJ_CPF_Redespacho() {
    return NR_CNPJ_CPF_Redespacho;
  }
  public String getNR_CNPJ_CPF_Remetente() {
    return NR_CNPJ_CPF_Remetente;
  }
  public long getNR_Duplicata() {
    return NR_Duplicata;
  }
  public long getNR_EDI_Conhecimento_Final() {
    return NR_EDI_Conhecimento_Final;
  }
  public long getNR_EDI_Conhecimento_Inicial() {
    return NR_EDI_Conhecimento_Inicial;
  }
  public String getNR_NOTA_FISCAL() {
    return NR_NOTA_FISCAL;
  }
  public double getNR_Volumes() {
    return NR_Volumes;
  }
  public long getOID_Cidade() {
    return OID_Cidade;
  }

  public String getOID_EDI_Conhecimento() {
    return OID_EDI_Conhecimento;
  }
  public long getOID_Estado_Destino() {
    return OID_Estado_Destino;
  }
  public long getOID_Estado_Origem() {
    return OID_Estado_Origem;
  }
  public long getOID_Modal() {
    return OID_Modal;
  }
  public String getOID_Nota_Fiscal() {
    return OID_Nota_Fiscal;
  }
  public long getOID_Pessoa() {
    return OID_Pessoa;
  }
  public long getOID_Pessoa_Consignatario() {
    return OID_Pessoa_Consignatario;
  }
  public long getOID_Pessoa_Destinatario() {
    return OID_Pessoa_Destinatario;
  }
  public long getOID_Pessoa_Pagador() {
    return OID_Pessoa_Pagador;
  }
  public long getOID_Pessoa_Redespacho() {
    return OID_Pessoa_Redespacho;
  }
  public long getOID_Produto() {
    return OID_Produto;
  }
  public String getOID_Tabela_Frete() {
    return OID_Tabela_Frete;
  }
  public long getOID_Taxa() {
    return OID_Taxa;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  public String getOID_Vendedor() {
    return OID_Vendedor;
  }
  public double getPE_Aliquota_ICMS() {
    return PE_Aliquota_ICMS;
  }
  public double getPE_ALIQUOTA_ICMS() {
    return PE_ALIQUOTA_ICMS;
  }
  public String getTX_Observacao() {
    return TX_Observacao;
  }
  public double getVL_BASE_CALCULO_ICMS() {
    return VL_BASE_CALCULO_ICMS;
  }
  public double getVL_DESPACHO() {
    return VL_DESPACHO;
  }
  public double getVL_Frete() {
    return VL_Frete;
  }
  public double getVL_FRETE_PESO() {
    return VL_FRETE_PESO;
  }
  public double getVL_FRETE_VALOR() {
    return VL_FRETE_VALOR;
  }
  public double getVL_ICMS() {
    return VL_ICMS;
  }
  public double getVL_Nota_Fiscal() {
    return VL_Nota_Fiscal;
  }
  public double getVL_OUTROS1() {
    return VL_OUTROS1;
  }
  public double getVL_OUTROS2() {
    return VL_OUTROS2;
  }
  public double getVL_PEDAGIO() {
    return VL_PEDAGIO;
  }
  public double getVL_Peso() {
    return VL_Peso;
  }
  public double getVL_SEC_CAT() {
    return VL_SEC_CAT;
  }
  public double getVL_TOTAL_FRETE() {
    return VL_TOTAL_FRETE;
  }
  public void setCD_Area_Frete_Destinatario(String CD_Area_Frete_Destinatario) {
    this.CD_Area_Frete_Destinatario = CD_Area_Frete_Destinatario;
  }
  public void setCD_Area_Frete_Redespacho(String CD_Area_Frete_Redespacho) {
    this.CD_Area_Frete_Redespacho = CD_Area_Frete_Redespacho;
  }
  public void setCD_CFO(String CD_CFO) {
    this.CD_CFO = CD_CFO;
  }
  public void setCD_Condicao_Frete(String CD_Condicao_Frete) {
    this.CD_Condicao_Frete = CD_Condicao_Frete;
  }
  public void setCD_Estado_Consignatario(String CD_Estado_Consignatario) {
    this.CD_Estado_Consignatario = CD_Estado_Consignatario;
  }
  public void setCD_Estado_CTRC_Destino(String CD_Estado_CTRC_Destino) {
    this.CD_Estado_CTRC_Destino = CD_Estado_CTRC_Destino;
  }
  public void setCD_Estado_CTRC_Origem(String CD_Estado_CTRC_Origem) {
    this.CD_Estado_CTRC_Origem = CD_Estado_CTRC_Origem;
  }
  public void setCD_Estado_Destinatario(String CD_Estado_Destinatario) {
    this.CD_Estado_Destinatario = CD_Estado_Destinatario;
  }
  public void setCD_Estado_Pagador(String CD_Estado_Pagador) {
    this.CD_Estado_Pagador = CD_Estado_Pagador;
  }
  public void setCD_Estado_Redespacho(String CD_Estado_Redespacho) {
    this.CD_Estado_Redespacho = CD_Estado_Redespacho;
  }
  public void setCD_Estado_Remetente(String CD_Estado_Remetente) {
    this.CD_Estado_Remetente = CD_Estado_Remetente;
  }
  public void setCD_Referencia(String CD_Referencia) {
    this.CD_Referencia = CD_Referencia;
  }
  public void setCD_Tipo(String CD_Tipo) {
    this.CD_Tipo = CD_Tipo;
  }
  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public void setDM_Impresso(String DM_Impresso) {
    this.DM_Impresso = DM_Impresso;
  }
  public void setDM_Isento_Seguro(String DM_Isento_Seguro) {
    this.DM_Isento_Seguro = DM_Isento_Seguro;
  }
  public void setDM_Responsavel_Cobranca(String DM_Responsavel_Cobranca) {
    this.DM_Responsavel_Cobranca = DM_Responsavel_Cobranca;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public void setDM_Tipo_EDI_Conhecimento(String DM_Tipo_EDI_Conhecimento) {
    this.DM_Tipo_EDI_Conhecimento = DM_Tipo_EDI_Conhecimento;
  }
  public void setDM_Tipo_Pagamento(String DM_Tipo_Pagamento) {
    this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
  }
  public void setDT_Embarque(String DT_Embarque) {
    this.DT_Embarque = DT_Embarque;
  }
  public void setDT_Emissao(String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }
  public void setDT_Emissao_Nota_Fiscal(String DT_Emissao_Nota_Fiscal) {
    this.DT_Emissao_Nota_Fiscal = DT_Emissao_Nota_Fiscal;
  }
  public void setNM_Arquivo(String NM_Arquivo) {
    this.NM_Arquivo = NM_Arquivo;
  }
  public void setNM_Atendente(String NM_Atendente) {
    this.NM_Atendente = NM_Atendente;
  }
  public void setNM_Bairro_Consignatario(String NM_Bairro_Consignatario) {
    this.NM_Bairro_Consignatario = NM_Bairro_Consignatario;
  }
  public void setNM_Bairro_Destinatario(String NM_Bairro_Destinatario) {
    this.NM_Bairro_Destinatario = NM_Bairro_Destinatario;
  }
  public void setNM_Bairro_Pagador(String NM_Bairro_Pagador) {
    this.NM_Bairro_Pagador = NM_Bairro_Pagador;
  }
  public void setNM_Bairro_Redespacho(String NM_Bairro_Redespacho) {
    this.NM_Bairro_Redespacho = NM_Bairro_Redespacho;
  }
  public void setNM_Cidade_Consignatario(String NM_Cidade_Consignatario) {
    this.NM_Cidade_Consignatario = NM_Cidade_Consignatario;
  }
  public void setNM_Cidade_CTRC_Destino(String NM_Cidade_CTRC_Destino) {
    this.NM_Cidade_CTRC_Destino = NM_Cidade_CTRC_Destino;
  }
  public void setNM_Cidade_CTRC_Origem(String NM_Cidade_CTRC_Origem) {
    this.NM_Cidade_CTRC_Origem = NM_Cidade_CTRC_Origem;
  }
  public void setNM_Cidade_Destinatario(String NM_Cidade_Destinatario) {
    this.NM_Cidade_Destinatario = NM_Cidade_Destinatario;
  }
  public void setNM_Cidade_Pagador(String NM_Cidade_Pagador) {
    this.NM_Cidade_Pagador = NM_Cidade_Pagador;
  }
  public void setNM_Cidade_Redespacho(String NM_Cidade_Redespacho) {
    this.NM_Cidade_Redespacho = NM_Cidade_Redespacho;
  }
  public void setNM_Cidade_Remetente(String NM_Cidade_Remetente) {
    this.NM_Cidade_Remetente = NM_Cidade_Remetente;
  }
  public void setNM_Endereco_Consignatario(String NM_Endereco_Consignatario) {
    this.NM_Endereco_Consignatario = NM_Endereco_Consignatario;
  }
  public void setNM_Endereco_Destinatario(String NM_Endereco_Destinatario) {
    this.NM_Endereco_Destinatario = NM_Endereco_Destinatario;
  }
  public void setNM_Endereco_Pagador(String NM_Endereco_Pagador) {
    this.NM_Endereco_Pagador = NM_Endereco_Pagador;
  }
  public void setNM_Endereco_Redespacho(String NM_Endereco_Redespacho) {
    this.NM_Endereco_Redespacho = NM_Endereco_Redespacho;
  }
  public void setNM_Endereco_Remetente(String NM_Endereco_Remetente) {
    this.NM_Endereco_Remetente = NM_Endereco_Remetente;
  }
  public void setNM_Fantasia_Unidade(String NM_Fantasia_Unidade) {
    this.NM_Fantasia_Unidade = NM_Fantasia_Unidade;
  }
  public void setNM_INSCRICAO_Consignatario(String NM_INSCRICAO_Consignatario) {
    this.NM_INSCRICAO_Consignatario = NM_INSCRICAO_Consignatario;
  }
  public void setNM_INSCRICAO_Destinatario(String NM_INSCRICAO_Destinatario) {
    this.NM_INSCRICAO_Destinatario = NM_INSCRICAO_Destinatario;
  }
  public void setNM_INSCRICAO_Pagador(String NM_INSCRICAO_Pagador) {
    this.NM_INSCRICAO_Pagador = NM_INSCRICAO_Pagador;
  }
  public void setNM_INSCRICAO_Redespacho(String NM_INSCRICAO_Redespacho) {
    this.NM_INSCRICAO_Redespacho = NM_INSCRICAO_Redespacho;
  }
  public void setNM_INSCRICAO_Remetente(String NM_INSCRICAO_Remetente) {
    this.NM_INSCRICAO_Remetente = NM_INSCRICAO_Remetente;
  }
  public void setNM_Natureza(String NM_Natureza) {
    this.NM_Natureza = NM_Natureza;
  }
  public void setNM_Natureza_Mercadoria(String NM_Natureza_Mercadoria) {
    this.NM_Natureza_Mercadoria = NM_Natureza_Mercadoria;
  }
  public void setNM_Pessoa_Entrega(String NM_Pessoa_Entrega) {
    this.NM_Pessoa_Entrega = NM_Pessoa_Entrega;
  }
  public void setNM_Razao_Social_Consignatario(String NM_Razao_Social_Consignatario) {
    this.NM_Razao_Social_Consignatario = NM_Razao_Social_Consignatario;
  }
  public void setNM_Razao_Social_Destinatario(String NM_Razao_Social_Destinatario) {
    this.NM_Razao_Social_Destinatario = NM_Razao_Social_Destinatario;
  }
  public void setNM_Razao_Social_Pagador(String NM_Razao_Social_Pagador) {
    this.NM_Razao_Social_Pagador = NM_Razao_Social_Pagador;
  }
  public void setNM_Razao_Social_Redespacho(String NM_Razao_Social_Redespacho) {
    this.NM_Razao_Social_Redespacho = NM_Razao_Social_Redespacho;
  }
  public void setNM_Razao_Social_Remetente(String NM_Razao_Social_Remetente) {
    this.NM_Razao_Social_Remetente = NM_Razao_Social_Remetente;
  }
  public void setNM_Serie(String NM_Serie) {
    this.NM_Serie = NM_Serie;
  }
  public void setNR_CEP_Consignatario(String NR_CEP_Consignatario) {
    this.NR_CEP_Consignatario = NR_CEP_Consignatario;
  }
  public void setNR_CEP_Destinatario(String NR_CEP_Destinatario) {
    this.NR_CEP_Destinatario = NR_CEP_Destinatario;
  }
  public void setNR_CEP_Pagador(String NR_CEP_Pagador) {
    this.NR_CEP_Pagador = NR_CEP_Pagador;
  }
  public void setNR_CEP_Redespacho(String NR_CEP_Redespacho) {
    this.NR_CEP_Redespacho = NR_CEP_Redespacho;
  }
  public void setNR_CEP_Remetente(String NR_CEP_Remetente) {
    this.NR_CEP_Remetente = NR_CEP_Remetente;
  }
  public void setNR_CNPJ_CPF_Consignatario(String NR_CNPJ_CPF_Consignatario) {
    this.NR_CNPJ_CPF_Consignatario = NR_CNPJ_CPF_Consignatario;
  }
  public void setNR_CNPJ_CPF_Destinatario(String NR_CNPJ_CPF_Destinatario) {
    this.NR_CNPJ_CPF_Destinatario = NR_CNPJ_CPF_Destinatario;
  }
  public void setNR_CNPJ_CPF_Pagador(String NR_CNPJ_CPF_Pagador) {
    this.NR_CNPJ_CPF_Pagador = NR_CNPJ_CPF_Pagador;
  }
  public void setNR_CNPJ_CPF_Redespacho(String NR_CNPJ_CPF_Redespacho) {
    this.NR_CNPJ_CPF_Redespacho = NR_CNPJ_CPF_Redespacho;
  }
  public void setNR_CNPJ_CPF_Remetente(String NR_CNPJ_CPF_Remetente) {
    this.NR_CNPJ_CPF_Remetente = NR_CNPJ_CPF_Remetente;
  }
  public void setNR_Duplicata(long NR_Duplicata) {
    this.NR_Duplicata = NR_Duplicata;
  }
  public void setNR_EDI_Conhecimento_Final(long NR_EDI_Conhecimento_Final) {
    this.NR_EDI_Conhecimento_Final = NR_EDI_Conhecimento_Final;
  }
  public void setNR_EDI_Conhecimento_Inicial(long NR_EDI_Conhecimento_Inicial) {
    this.NR_EDI_Conhecimento_Inicial = NR_EDI_Conhecimento_Inicial;
  }
  public void setNR_NOTA_FISCAL(String NR_NOTA_FISCAL) {
    this.NR_NOTA_FISCAL = NR_NOTA_FISCAL;
  }
  public void setNR_Volumes(double NR_Volumes) {
    this.NR_Volumes = NR_Volumes;
  }
  public void setOID_Cidade(long OID_Cidade) {
    this.OID_Cidade = OID_Cidade;
  }

  public void setOID_Estado_Destino(long OID_Estado_Destino) {
    this.OID_Estado_Destino = OID_Estado_Destino;
  }
  public void setOID_Estado_Origem(long OID_Estado_Origem) {
    this.OID_Estado_Origem = OID_Estado_Origem;
  }
  public void setOID_Modal(long OID_Modal) {
    this.OID_Modal = OID_Modal;
  }
  public void setOID_Nota_Fiscal(String OID_Nota_Fiscal) {
    this.OID_Nota_Fiscal = OID_Nota_Fiscal;
  }
  public void setOID_Pessoa(long OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public void setOID_Pessoa_Consignatario(long OID_Pessoa_Consignatario) {
    this.OID_Pessoa_Consignatario = OID_Pessoa_Consignatario;
  }
  public void setOID_Pessoa_Destinatario(long OID_Pessoa_Destinatario) {
    this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
  }
  public void setOID_Pessoa_Pagador(long OID_Pessoa_Pagador) {
    this.OID_Pessoa_Pagador = OID_Pessoa_Pagador;
  }
  public void setOID_Pessoa_Redespacho(long OID_Pessoa_Redespacho) {
    this.OID_Pessoa_Redespacho = OID_Pessoa_Redespacho;
  }
  public void setOID_Produto(long OID_Produto) {
    this.OID_Produto = OID_Produto;
  }
  public void setOID_Tabela_Frete(String OID_Tabela_Frete) {
    this.OID_Tabela_Frete = OID_Tabela_Frete;
  }
  public void setOID_Taxa(long OID_Taxa) {
    this.OID_Taxa = OID_Taxa;
  }
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public void setOID_Vendedor(String OID_Vendedor) {
    this.OID_Vendedor = OID_Vendedor;
  }
  public void setPE_Aliquota_ICMS(double PE_Aliquota_ICMS) {
    this.PE_Aliquota_ICMS = PE_Aliquota_ICMS;
  }
  public void setPE_ALIQUOTA_ICMS(double PE_ALIQUOTA_ICMS) {
    this.PE_ALIQUOTA_ICMS = PE_ALIQUOTA_ICMS;
  }
  public void setTX_Observacao(String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }
  public void setVL_BASE_CALCULO_ICMS(double VL_BASE_CALCULO_ICMS) {
    this.VL_BASE_CALCULO_ICMS = VL_BASE_CALCULO_ICMS;
  }
  public void setVL_DESPACHO(double VL_DESPACHO) {
    this.VL_DESPACHO = VL_DESPACHO;
  }
  public void setVL_Frete(double VL_Frete) {
    this.VL_Frete = VL_Frete;
  }
  public void setVL_FRETE_PESO(double VL_FRETE_PESO) {
    this.VL_FRETE_PESO = VL_FRETE_PESO;
  }
  public void setVL_FRETE_VALOR(double VL_FRETE_VALOR) {
    this.VL_FRETE_VALOR = VL_FRETE_VALOR;
  }
  public void setVL_ICMS(double VL_ICMS) {
    this.VL_ICMS = VL_ICMS;
  }
  public void setVL_Nota_Fiscal(double VL_Nota_Fiscal) {
    this.VL_Nota_Fiscal = VL_Nota_Fiscal;
  }
  public void setVL_OUTROS1(double VL_OUTROS1) {
    this.VL_OUTROS1 = VL_OUTROS1;
  }
  public void setVL_OUTROS2(double VL_OUTROS2) {
    this.VL_OUTROS2 = VL_OUTROS2;
  }
  public void setVL_PEDAGIO(double VL_PEDAGIO) {
    this.VL_PEDAGIO = VL_PEDAGIO;
  }
  public void setVL_Peso(double VL_Peso) {
    this.VL_Peso = VL_Peso;
  }
  public void setVL_SEC_CAT(double VL_SEC_CAT) {
    this.VL_SEC_CAT = VL_SEC_CAT;
  }
  public void setVL_TOTAL_FRETE(double VL_TOTAL_FRETE) {
    this.VL_TOTAL_FRETE = VL_TOTAL_FRETE;
  }
  public String getVL_Nota_Fiscal_formatada() {
    return VL_Nota_Fiscal_formatada;
  }
  public void setVL_Nota_Fiscal_formatada(String VL_Nota_Fiscal_formatada) {
    this.VL_Nota_Fiscal_formatada = VL_Nota_Fiscal_formatada;
  }
  public String getNM_Produto() {
    return NM_Produto;
  }
  public void setNM_Produto(String NM_Produto) {
    this.NM_Produto = NM_Produto;
  }
  public String getCD_Produto() {
    return CD_Produto;
  }
  public void setCD_Produto(String CD_Produto) {
    this.CD_Produto = CD_Produto;
  }
  public long getOID_Lote() {
    return OID_Lote;
  }
  public void setOID_Lote(long OID_Lote) {
    this.OID_Lote = OID_Lote;
  }
  public void setOID_EDI_Conhecimento(String OID_EDI_Conhecimento) {
    this.OID_EDI_Conhecimento = OID_EDI_Conhecimento;
  }
  public String getNR_EDI_Conhecimento() {
    return NR_EDI_Conhecimento;
  }
  public void setNR_EDI_Conhecimento(String NR_EDI_Conhecimento) {
    this.NR_EDI_Conhecimento = NR_EDI_Conhecimento;
  }
  public String getNM_Bairro_Remetente() {
    return NM_Bairro_Remetente;
  }
  public void setNM_Bairro_Remetente(String NM_Bairro_Remetente) {
    this.NM_Bairro_Remetente = NM_Bairro_Remetente;
  }
  public String getNM_Natureza_Operacao() {
    return NM_Natureza_Operacao;
  }
  public void setNM_Natureza_Operacao(String NM_Natureza_Operacao) {
    this.NM_Natureza_Operacao = NM_Natureza_Operacao;
  }
  public String getNR_Pedido() {
    return NR_Pedido;
  }
  public void setNR_Pedido(String NR_Pedido) {
    this.NR_Pedido = NR_Pedido;
  }
  public String getCD_Companhia() {
    return CD_Companhia;
  }
  public void setCD_Companhia(String CD_Companhia) {
    this.CD_Companhia = CD_Companhia;
  }
  public long getOID_Cidade_Destinatario() {
    return OID_Cidade_Destinatario;
  }
  public long getOID_Cidade_Remetente() {
    return OID_Cidade_Remetente;
  }
  public void setOID_Cidade_Destinatario(long OID_Cidade_Destinatario) {
    this.OID_Cidade_Destinatario = OID_Cidade_Destinatario;
  }
  public void setOID_Cidade_Remetente(long OID_Cidade_Remetente) {
    this.OID_Cidade_Remetente = OID_Cidade_Remetente;
  }
  public void setOID_Coleta(long OID_Coleta) {
    this.OID_Coleta = OID_Coleta;
  }
  public long getOID_Coleta() {
    return OID_Coleta;
  }
  public void setDM_Tipo_Conhecimento(String DM_Tipo_Conhecimento) {
    this.DM_Tipo_Conhecimento = DM_Tipo_Conhecimento;
  }
  public String getDM_Tipo_Conhecimento() {
    return DM_Tipo_Conhecimento;
  }
  public void setDT_Importacao(String DT_Importacao) {
    this.DT_Importacao = DT_Importacao;
  }
  public String getDT_Importacao() {
    return DT_Importacao;
  }
  public String getCD_Cidade_Destinatario() {
    return CD_Cidade_Destinatario;
  }
  public void setCD_Cidade_Destinatario(String CD_Cidade_Destinatario) {
    this.CD_Cidade_Destinatario = CD_Cidade_Destinatario;
  }
  public void setDT_Ocorrencia(String DT_Ocorrencia) {
    this.DT_Ocorrencia = DT_Ocorrencia;
  }
  public String getDT_Ocorrencia() {
    return DT_Ocorrencia;
  }
  public void setHR_Ocorrencia(String HR_Ocorrencia) {
    this.HR_Ocorrencia = HR_Ocorrencia;
  }
  public String getHR_Ocorrencia() {
    return HR_Ocorrencia;
  }
  public void setCD_Ocorrencia(String CD_Ocorrencia) {
    this.CD_Ocorrencia = CD_Ocorrencia;
  }
  public String getCD_Ocorrencia() {
    return CD_Ocorrencia;
  }
  public void setDM_Tipo_EDI(String DM_Tipo_EDI) {
    this.DM_Tipo_EDI = DM_Tipo_EDI;
  }
  public String getDM_Tipo_EDI() {
    return DM_Tipo_EDI;
  }
  public void setNM_Ocorrencia(String NM_Ocorrencia) {
    this.NM_Ocorrencia = NM_Ocorrencia;
  }
  public String getNM_Ocorrencia() {
    return NM_Ocorrencia;
  }
  public void setDM_Tipo_Padrao(String DM_Tipo_Padrao) {
    this.DM_Tipo_Padrao = DM_Tipo_Padrao;
  }
  public String getDM_Tipo_Padrao() {
    return DM_Tipo_Padrao;
  }



}