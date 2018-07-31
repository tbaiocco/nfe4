package com.master.ed;

public class Ordem_Frete_TerceiroED extends MasterED{

  private long OID_Unidade;
  private String CD_Unidade;
  private String NM_Unidade;
  private long NR_Ordem_Frete_Terceiro;
  private double VL_Adiantamento;
  private String TX_Observacao;
  private String DM_Frete;
  private long OID_Acerto;
  private double VL_Ordem_Frete_Terceiro;
  private String DT_Emissao;
  private String OID_Ordem_Frete_Terceiro;
  private String NM_Serie;
  private String OID_Pessoa;
  private String OID_Veiculo;
  private double VL_Descontos;
  private long NR_Recibo;
  private double VL_Devolvido;
  private double VL_Especie;
  private double VL_Cheque;
  private double VL_Saldo;
  private String OID_Fornecedor;
  private String NM_Origem;
  private String NM_Destino;
  private double VL_Despesas;
  private String OID_Motorista;
  private double VL_Frete_Devolvido;
  private double VL_Deposito;
  private String DM_Situacao;
  private String DM_Impresso;
  private long oid_Lote_Faturamento;
  private double VL_Carga;
  private double VL_Descarga;
  private double VL_Agenciamento;
  private String DT_Coleta;
  private String DT_Entrega;
  private String NM_Produto;
  private long NR_Programacao;
  private long OID_Usuario;
  private String NM_Usuario;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String DM_Acerto;
  private String DM_Relatorio;
  private long OID_Empresa;

  public void setOID_Ordem_Frete_Terceiro(String OID_Ordem_Frete_Terceiro) {
    this.OID_Ordem_Frete_Terceiro = OID_Ordem_Frete_Terceiro;
  }
  public String getOID_Ordem_Frete_Terceiro() {
    return OID_Ordem_Frete_Terceiro;
  }
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
  }
  public void setNM_Unidade(String NM_Unidade) {
    this.NM_Unidade = NM_Unidade;
  }
  public String getNM_Unidade() {
    return NM_Unidade;
  }
  public void setNR_Ordem_Frete_Terceiro(long NR_Ordem_Frete_Terceiro) {
    this.NR_Ordem_Frete_Terceiro = NR_Ordem_Frete_Terceiro;
  }
  public long getNR_Ordem_Frete_Terceiro() {
    return NR_Ordem_Frete_Terceiro;
  }
  public void setVL_Ordem_Frete_Terceiro(double VL_Ordem_Frete_Terceiro) {
    this.VL_Ordem_Frete_Terceiro = VL_Ordem_Frete_Terceiro;
  }
  public double getVL_Ordem_Frete_Terceiro() {
    return VL_Ordem_Frete_Terceiro;
  }
  public void setVL_Adiantamento(double VL_Adiantamento) {
    this.VL_Adiantamento = VL_Adiantamento;
  }
  public double getVL_Adiantamento() {
    return VL_Adiantamento;
  }
  public void setTX_Observacao(String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }
  public String getTX_Observacao() {
    return TX_Observacao;
  }
  public void setDM_Frete(String DM_Frete) {
    this.DM_Frete = DM_Frete;
  }
  public String getDM_Frete() {
    return DM_Frete;
  }
  public void setOID_Acerto(long OID_Acerto) {
    this.OID_Acerto = OID_Acerto;
  }
  public long getOID_Acerto() {
    return OID_Acerto;
  }
  public void setDT_Emissao(String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }
  public String getDT_Emissao() {
    return DT_Emissao;
  }
  public void setNM_Serie(String NM_Serie) {
    this.NM_Serie = NM_Serie;
  }
  public String getNM_Serie() {
    return NM_Serie;
  }
  public void setOID_Pessoa(String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public String getOID_Pessoa() {
    return OID_Pessoa;
  }
  public void setOID_Veiculo(String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }
  public String getOID_Veiculo() {
    return OID_Veiculo;
  }
  public void setVL_Descontos(double VL_Descontos) {
    this.VL_Descontos = VL_Descontos;
  }
  public double getVL_Descontos() {
    return VL_Descontos;
  }
  public void setNR_Recibo(long NR_Recibo) {
    this.NR_Recibo = NR_Recibo;
  }
  public long getNR_Recibo() {
    return NR_Recibo;
  }

  public void setVL_Devolvido (double VL_Devolvido) {
    this.VL_Devolvido = VL_Devolvido;
  }

  public double getVL_Devolvido () {
    return VL_Devolvido;
  }

  public void setVL_Especie (double VL_Especie) {
    this.VL_Especie = VL_Especie;
  }

  public double getVL_Especie () {
    return VL_Especie;
  }

  public void setVL_Cheque (double VL_Cheque) {
    this.VL_Cheque = VL_Cheque;
  }

  public double getVL_Cheque () {
    return VL_Cheque;
  }

  public void setVL_Saldo (double VL_Saldo) {
    this.VL_Saldo = VL_Saldo;
  }

  public double getVL_Saldo () {
    return VL_Saldo;
  }

  public void setOID_Fornecedor (String OID_Fornecedor) {

    this.OID_Fornecedor = OID_Fornecedor;
  }

  public void setNM_Destino (String NM_Destino) {
    this.NM_Destino = NM_Destino;
  }

  public void setNM_Origem (String NM_Origem) {
    this.NM_Origem = NM_Origem;
  }

  public String getOID_Fornecedor () {

    return OID_Fornecedor;
  }

  public String getNM_Destino () {
    return NM_Destino;
  }

  public String getNM_Origem () {
    return NM_Origem;
  }

  public void setVL_Despesas (double VL_Despesas) {
    this.VL_Despesas = VL_Despesas;
  }

  public double getVL_Despesas () {
    return VL_Despesas;
  }

  public void setOID_Motorista (String OID_Motorista) {
    this.OID_Motorista = OID_Motorista;
  }

  public String getOID_Motorista () {
    return OID_Motorista;
  }

  public void setVL_Frete_Devolvido (double VL_Frete_Devolvido) {
    this.VL_Frete_Devolvido = VL_Frete_Devolvido;
  }

  public double getVL_Frete_Devolvido () {
    return VL_Frete_Devolvido;
  }

  public void setVL_Deposito (double VL_Deposito) {
    this.VL_Deposito = VL_Deposito;
  }

  public double getVL_Deposito () {
    return VL_Deposito;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setDM_Impresso (String DM_Impresso) {
    this.DM_Impresso = DM_Impresso;
  }

  public String getDM_Impresso () {
    return DM_Impresso;
  }

  public void setOid_Lote_Faturamento (long oid_Lote_Faturamento) {
    this.oid_Lote_Faturamento = oid_Lote_Faturamento;
  }

  public void setOID_Empresa (long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public void setDM_Acerto (String DM_Acerto) {
    this.DM_Acerto = DM_Acerto;
  }

  public void setDT_Emissao_Final (String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }

  public void setDT_Emissao_Inicial (String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }

  public void setNM_Usuario (String NM_Usuario) {
    this.NM_Usuario = NM_Usuario;
  }

  public void setOID_Usuario (long OID_Usuario) {
    this.OID_Usuario = OID_Usuario;
  }

  public void setNR_Programacao (long NR_Programacao) {
    this.NR_Programacao = NR_Programacao;
  }

  public void setNM_Produto (String NM_Produto) {
    this.NM_Produto = NM_Produto;
  }

  public void setDT_Entrega (String DT_Entrega) {
    this.DT_Entrega = DT_Entrega;
  }

  public void setDT_Coleta (String DT_Coleta) {
    this.DT_Coleta = DT_Coleta;
  }

  public void setVL_Agenciamento (double VL_Agenciamento) {
    this.VL_Agenciamento = VL_Agenciamento;
  }

  public void setVL_Descarga (double VL_Descarga) {
    this.VL_Descarga = VL_Descarga;
  }

  public void setVL_Carga (double VL_Carga) {
    this.VL_Carga = VL_Carga;
  }

  public long getOid_Lote_Faturamento () {
    return oid_Lote_Faturamento;
  }

  public long getOID_Empresa () {
    return OID_Empresa;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public String getDM_Acerto () {
    return DM_Acerto;
  }

  public String getDT_Emissao_Final () {
    return DT_Emissao_Final;
  }

  public String getDT_Emissao_Inicial () {
    return DT_Emissao_Inicial;
  }

  public String getNM_Usuario () {
    return NM_Usuario;
  }

  public long getOID_Usuario () {
    return OID_Usuario;
  }

  public long getNR_Programacao () {
    return NR_Programacao;
  }

  public String getNM_Produto () {
    return NM_Produto;
  }

  public String getDT_Entrega () {
    return DT_Entrega;
  }

  public String getDT_Coleta () {
    return DT_Coleta;
  }

  public double getVL_Agenciamento () {
    return VL_Agenciamento;
  }

  public double getVL_Descarga () {
    return VL_Descarga;
  }

  public double getVL_Carga () {
    return VL_Carga;
  }

}
