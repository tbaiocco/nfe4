package com.master.ed;

public class Ordem_ServicoED extends MasterED{

  private long OID_Unidade;
  private String CD_Unidade;
  private String NM_Unidade;
  private long OID_Acerto;
  private String DT_Emissao;
  private String OID_Ordem_Frete;
  private String NR_Placa;
  private String OID_Veiculo;
  private String OID_Pessoa;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private long NR_Acerto;
  private String DM_Tipo_Pagamento;
  private String NM_Razao_Social;
  private String DM_Relatorio;
  private long OID_Empresa;
  private String NM_Empresa;
  private long OID_Ordem_Servico;
  private long NR_Ordem_Servico;
  private long OID_Tipo_Servico;
  private long OID_Movimento_Ordem_Servico;
  private String DM_Tipo_Despesa;
  private String TX_Observacao;
  private double VL_Rateio;
  private String DM_Tipo_Implemento;

  public void setOID_Ordem_Frete(String OID_Ordem_Frete) {
    this.OID_Ordem_Frete = OID_Ordem_Frete;
  }
  public String getOID_Ordem_Frete() {
    return OID_Ordem_Frete;
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
  public void setNR_Placa(String NR_Placa) {
    this.NR_Placa = NR_Placa;
  }
  public String getNR_Placa() {
    return NR_Placa;
  }
  public void setOID_Veiculo(String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }
  public String getOID_Veiculo() {
    return OID_Veiculo;
  }
  public void setOID_Pessoa(String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public String getOID_Pessoa() {
    return OID_Pessoa;
  }
  public void setNR_Acerto(long NR_Acerto) {
    this.NR_Acerto = NR_Acerto;
  }
  public long getNR_Acerto() {
    return NR_Acerto;
  }
  public void setDM_Tipo_Pagamento(String DM_Tipo_Pagamento) {
    this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
  }
  public String getDM_Tipo_Pagamento() {
    return DM_Tipo_Pagamento;
  }
  public void setNM_Razao_Social(String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }
  public String getNM_Razao_Social() {
    return NM_Razao_Social;
  }
  public void setDM_Relatorio(String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }
  public String getDM_Relatorio() {
    return DM_Relatorio;
  }
  public void setOID_Empresa(long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }
  public long getOID_Empresa() {
    return OID_Empresa;
  }
  public void setNM_Empresa(String NM_Empresa) {
    this.NM_Empresa = NM_Empresa;
  }
  public String getNM_Empresa() {
    return NM_Empresa;
  }
  public void setOID_Ordem_Servico(long OID_Ordem_Servico) {
    this.OID_Ordem_Servico = OID_Ordem_Servico;
  }
  public long getOID_Ordem_Servico() {
    return OID_Ordem_Servico;
  }
  public String getDT_Emissao_Final() {
    return DT_Emissao_Final;
  }
  public String getDT_Emissao_Inicial() {
    return DT_Emissao_Inicial;
  }
  public void setDT_Emissao_Final(String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }
  public void setDT_Emissao_Inicial(String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }
  public void setNR_Ordem_Servico(long NR_Ordem_Servico) {
    this.NR_Ordem_Servico = NR_Ordem_Servico;
  }
  public long getNR_Ordem_Servico() {
    return NR_Ordem_Servico;
  }
  public void setOID_Tipo_Servico(long OID_Tipo_Servico) {
    this.OID_Tipo_Servico = OID_Tipo_Servico;
  }
  public long getOID_Tipo_Servico() {
    return OID_Tipo_Servico;
  }
  public void setOID_Movimento_Ordem_Servico(long OID_Movimento_Ordem_Servico) {
    this.OID_Movimento_Ordem_Servico = OID_Movimento_Ordem_Servico;
  }
  public long getOID_Movimento_Ordem_Servico() {
    return OID_Movimento_Ordem_Servico;
  }

  public void setDM_Tipo_Despesa (String DM_Tipo_Despesa) {
    this.DM_Tipo_Despesa = DM_Tipo_Despesa;
  }

  public String getDM_Tipo_Despesa () {
    return DM_Tipo_Despesa;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setVL_Rateio (double VL_Rateio) {
    this.VL_Rateio = VL_Rateio;
  }

  public double getVL_Rateio () {
    return VL_Rateio;
  }

  public void setDM_Tipo_Implemento (String DM_Tipo_Implemento) {
    this.DM_Tipo_Implemento = DM_Tipo_Implemento;
  }

  public String getDM_Tipo_Implemento () {
    return DM_Tipo_Implemento;
  }

}
