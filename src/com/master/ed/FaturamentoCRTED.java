package com.master.ed;

public class FaturamentoCRTED extends MasterED{

  private String NM_Serie;
  private String DT_Emissao;
  private String OID_Pessoa;
  private long OID_Unidade;
  private long OID_Unidade_Cobranca;
  private String OID_Vendedor;
  private String OID_Tabela_Frete;
  private String OID_Pessoa_Pagador;
  private long OID_Produto;
  private String CD_Unidade;
  private double VL_Peso;
  private double VL_Peso_Cubado;
  private double VL_Total_Frete;
  private double VL_ICMS;
  private long NR_Fatura;
  private String OID_Fatura;
  private String DT_Cotacao;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String OID_Conhecimento;
  private long NR_Conhecimento;
  private String NR_Conhecimento_Sequencia;
  private double VL_Duplicata;
  private String DT_Vencimento;
  private String DT_Vencimento_Minimo;
  private double VL_Taxa_Cobranca;
  private double VL_Cotacao;
  private double VL_Desconto_Faturamento;
  private double VL_Taxa_Refaturamento;
  private double VL_Saldo;
  private long oid_Tipo_Faturamento;
  private long oid_Tipo_Documento;
  private long oid_Carteira;
  private long NR_Dias_Vencimento;
  private double VL_Juro_Mora_Dia;
  private double VL_Multa;
  private long NR_Conhecimento_Inicial;
  private long NR_Conhecimento_Final;
  private long OID_Carteira_Informada;
  private double VL_Fatura;
  private long OID_Unidade_Faturamento;
  private String DM_Tipo_Faturamento;
  private String DM_Tipo_Conhecimento;
  private long OID_Unidade_CTRC;
  private String NM_Lote_Faturamento;
  private int oid_Moeda_Unidade_Cobranca;

  
  
public int getOid_Moeda_Unidade_Cobranca() {
    return oid_Moeda_Unidade_Cobranca;
}
public void setOid_Moeda_Unidade_Cobranca(int oid_Moeda_Unidade_Cobranca) {
    this.oid_Moeda_Unidade_Cobranca = oid_Moeda_Unidade_Cobranca;
}
  public void setNM_Serie(String NM_Serie) {
    this.NM_Serie = NM_Serie;
  }
  public String getNM_Serie() {
    return NM_Serie;
  }
  public void setDT_Emissao(String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }
  public String getDT_Emissao() {
    return DT_Emissao;
  }
  public void setOID_Pessoa(String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public String getOID_Pessoa() {
    return OID_Pessoa;
  }
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  public void setOID_Vendedor(String OID_Vendedor) {
    this.OID_Vendedor = OID_Vendedor;
  }
  public String getOID_Vendedor() {
    return OID_Vendedor;
  }
  public void setOID_Tabela_Frete(String OID_Tabela_Frete) {
    this.OID_Tabela_Frete = OID_Tabela_Frete;
  }
  public String getOID_Tabela_Frete() {
    return OID_Tabela_Frete;
  }
  public void setOID_Pessoa_Pagador(String OID_Pessoa_Pagador) {
    this.OID_Pessoa_Pagador = OID_Pessoa_Pagador;
  }
  public String getOID_Pessoa_Pagador() {
    return OID_Pessoa_Pagador;
  }
  public void setOID_Produto(long OID_Produto) {
    this.OID_Produto = OID_Produto;
  }
  public long getOID_Produto() {
    return OID_Produto;
  }
  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
  }
  public void setVL_Peso(double VL_Peso) {
    this.VL_Peso = VL_Peso;
  }
  public double getVL_Peso() {
    return VL_Peso;
  }
  public void setVL_Peso_Cubado(double VL_Peso_Cubado) {
    this.VL_Peso_Cubado = VL_Peso_Cubado;
  }
  public double getVL_Peso_Cubado() {
    return VL_Peso_Cubado;
  }
  public void setVL_Total_Frete(double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }
  public double getVL_Total_Frete() {
    return VL_Total_Frete;
  }
  public void setVL_ICMS(double VL_ICMS) {
    this.VL_ICMS = VL_ICMS;
  }
  public double getVL_ICMS() {
    return VL_ICMS;
  }
  public void setNR_Fatura(long NR_Fatura) {
    this.NR_Fatura = NR_Fatura;
  }
  public long getNR_Fatura() {
    return NR_Fatura;
  }
  public void setOID_Fatura(String OID_Fatura) {
    this.OID_Fatura = OID_Fatura;
  }
  public String getOID_Fatura() {
    return OID_Fatura;
  }
  public void setDT_Emissao_Inicial(String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }
  public String getDT_Emissao_Inicial() {
    return DT_Emissao_Inicial;
  }
  public void setDT_Emissao_Final(String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }
  public String getDT_Emissao_Final() {
    return DT_Emissao_Final;
  }
  public void setOID_Conhecimento(String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }
  public String getOID_Conhecimento() {
    return OID_Conhecimento;
  }
  public void setNR_Conhecimento(long NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }
  public long getNR_Conhecimento() {
    return NR_Conhecimento;
  }
  public void setVL_Duplicata(double VL_Duplicata) {
    this.VL_Duplicata = VL_Duplicata;
  }
  public double getVL_Duplicata() {
    return VL_Duplicata;
  }
  public void setDT_Vencimento(String DT_Vencimento) {
    this.DT_Vencimento = DT_Vencimento;
  }
  public String getDT_Vencimento() {
    return DT_Vencimento;
  }
  public void setVL_Taxa_Cobranca(double VL_Taxa_Cobranca) {
    this.VL_Taxa_Cobranca = VL_Taxa_Cobranca;
  }
  public double getVL_Taxa_Cobranca() {
    return VL_Taxa_Cobranca;
  }
  public void setVL_Desconto_Faturamento(double VL_Desconto_Faturamento) {
    this.VL_Desconto_Faturamento = VL_Desconto_Faturamento;
  }
  public double getVL_Desconto_Faturamento() {
    return VL_Desconto_Faturamento;
  }
  public void setVL_Taxa_Refaturamento(double VL_Taxa_Refaturamento) {
    this.VL_Taxa_Refaturamento = VL_Taxa_Refaturamento;
  }
  public double getVL_Taxa_Refaturamento() {
    return VL_Taxa_Refaturamento;
  }
  public void setVL_Saldo(double VL_Saldo) {
    this.VL_Saldo = VL_Saldo;
  }
  public double getVL_Saldo() {
    return VL_Saldo;
  }
  public void setOid_Tipo_Documento(long oid_Tipo_Documento) {
    this.oid_Tipo_Documento = oid_Tipo_Documento;
  }
  public long getOid_Tipo_Documento() {
    return oid_Tipo_Documento;
  }
  public void setOid_Carteira(long oid_Carteira) {
    this.oid_Carteira = oid_Carteira;
  }

  public void setOid_Tipo_Faturamento (long oid_Tipo_Faturamento) {
    this.oid_Tipo_Faturamento = oid_Tipo_Faturamento;
  }

  public void setVL_Cotacao (double VL_Cotacao) {
    this.VL_Cotacao = VL_Cotacao;
  }

  public void setOID_Unidade_Cobranca (long OID_Unidade_Cobranca) {
    this.OID_Unidade_Cobranca = OID_Unidade_Cobranca;
  }

  public void setDT_Cotacao (String DT_Cotacao) {
    this.DT_Cotacao = DT_Cotacao;
  }

  public long getOid_Carteira() {
    return oid_Carteira;
  }

  public long getOid_Tipo_Faturamento () {
    return oid_Tipo_Faturamento;
  }

  public double getVL_Cotacao () {
    return VL_Cotacao;
  }

  public long getOID_Unidade_Cobranca () {
    return OID_Unidade_Cobranca;
  }

  public String getDT_Cotacao () {
    return DT_Cotacao;
  }

  public void setNR_Dias_Vencimento(long NR_Dias_Vencimento) {
    this.NR_Dias_Vencimento = NR_Dias_Vencimento;
  }
  public long getNR_Dias_Vencimento() {
    return NR_Dias_Vencimento;
  }
  public void setVL_Juro_Mora_Dia(double VL_Juro_Mora_Dia) {
    this.VL_Juro_Mora_Dia = VL_Juro_Mora_Dia;
  }
  public double getVL_Juro_Mora_Dia() {
    return VL_Juro_Mora_Dia;
  }
  public void setVL_Multa(double VL_Multa) {
    this.VL_Multa = VL_Multa;
  }
  public double getVL_Multa() {
    return VL_Multa;
  }

  public void setNR_Conhecimento_Sequencia(String NR_Conhecimento_Sequencia) {
    this.NR_Conhecimento_Sequencia = NR_Conhecimento_Sequencia;
  }
  public String getNR_Conhecimento_Sequencia() {
    return NR_Conhecimento_Sequencia;
  }
  
  public void setNR_Conhecimento_Inicial(long NR_Conhecimento_Inicial) {
    this.NR_Conhecimento_Inicial = NR_Conhecimento_Inicial;
  }
  public long getNR_Conhecimento_Inicial() {
    return NR_Conhecimento_Inicial;
  }
  public void setNR_Conhecimento_Final(long NR_Conhecimento_Final) {
    this.NR_Conhecimento_Final = NR_Conhecimento_Final;
  }
  public long getNR_Conhecimento_Final() {
    return NR_Conhecimento_Final;
  }
  public void setOID_Carteira_Informada(long OID_Carteira_Informada) {
    this.OID_Carteira_Informada = OID_Carteira_Informada;
  }
  public long getOID_Carteira_Informada() {
    return OID_Carteira_Informada;
  }
  public void setVL_Fatura(double VL_Fatura) {
    this.VL_Fatura = VL_Fatura;
  }
  public double getVL_Fatura() {
    return VL_Fatura;
  }
  public void setOID_Unidade_Faturamento(long OID_Unidade_Faturamento) {
    this.OID_Unidade_Faturamento = OID_Unidade_Faturamento;
  }
  public long getOID_Unidade_Faturamento() {
    return OID_Unidade_Faturamento;
  }
  public void setDM_Tipo_Faturamento(String DM_Tipo_Faturamento) {
    this.DM_Tipo_Faturamento = DM_Tipo_Faturamento;
  }
  public String getDM_Tipo_Faturamento() {
    return DM_Tipo_Faturamento;
  }
  public void setDM_Tipo_Conhecimento(String DM_Tipo_Conhecimento) {
    this.DM_Tipo_Conhecimento = DM_Tipo_Conhecimento;
  }
  public String getDM_Tipo_Conhecimento() {
    return DM_Tipo_Conhecimento;
  }
  public void setOID_Unidade_CTRC(long OID_Unidade_CTRC) {
    this.OID_Unidade_CTRC = OID_Unidade_CTRC;
  }
  public long getOID_Unidade_CTRC() {
    return OID_Unidade_CTRC;
  }
  public String getDT_Vencimento_Minimo() {
    return DT_Vencimento_Minimo;
  }
  public void setDT_Vencimento_Minimo(String DT_Vencimento_Minimo) {
    this.DT_Vencimento_Minimo = DT_Vencimento_Minimo;
  }
  public String getNM_Lote_Faturamento() {
      return NM_Lote_Faturamento;
  }
  public void setNM_Lote_Faturamento(String NM_Lote_Faturamento) {
      this.NM_Lote_Faturamento = NM_Lote_Faturamento;
  }
}
