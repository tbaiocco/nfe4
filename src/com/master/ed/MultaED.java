package com.master.ed;

import javax.servlet.http.*;

public class MultaED extends RelatorioBaseED {
  public MultaED () {
  }


  public MultaED(HttpServletResponse response, String nomeRelatorio) {
      super(response, nomeRelatorio);
  }

  private String OID_Pessoa;
  private String NR_CNPJ_CPF_Pessoa;
  private String NM_Razao_Social_Pessoa;
  private String NM_Fantasia_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String DT_Emissao;
  private long oid_Empresa;
  private long OID_Unidade;
  private String CD_Unidade;
  private String DM_Culpa;
  private long NR_Multa;
  private long NR_Odometro_Inicial;
  private String DT_Troca_Motorista;
  private long OID_Cidade;
  private long OID_Tipo_Ocorrencia;
  private String NM_Tipo_Ocorrencia;
  private String NR_Placa;
  private String OID_Veiculo;
  private String OID_Multa;
  private long NR_Acerto;
  private long OID_Acerto;
  private String NM_Local;
  private String DM_Situacao;
  private String TX_Observacao;
  private String DM_Tipo;
  private String OID_Ordem_Frete;
  private String HR_Multa;
  private double VL_Multa;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String DM_Relatorio;
  private long OID_Empresa;
  private String DM_Tipo_Multa;
  private double VL_Saldo;
  private double VL_Parcela;
  private double VL_Parcelado;
  private String DT_Parcela;
  private long OID_Movimento_Conta_Corrente;

  public void setOID_Multa(String OID_Multa) {
    this.OID_Multa = OID_Multa;
  }
  public String getOID_Multa() {
    return OID_Multa;
  }
  public void setOID_Pessoa(String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public String getOID_Pessoa() {
    return OID_Pessoa;
  }
  public void setOID_Pessoa_Destinatario(String OID_Pessoa_Destinatario) {
    this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
  }
  public String getOID_Pessoa_Destinatario() {
    return OID_Pessoa_Destinatario;
  }
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  public void setDT_Emissao(String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }
  public String getDT_Emissao() {
    return DT_Emissao;
  }
  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
  }
  public void setDM_Culpa(String DM_Culpa) {
    this.DM_Culpa = DM_Culpa;
  }
  public String getDM_Culpa() {
    return DM_Culpa;
  }
  public void setNR_Multa(long NR_Multa) {
    this.NR_Multa = NR_Multa;
  }
  public long getNR_Multa() {
    return NR_Multa;
  }
  public void setNR_Odometro_Inicial(long NR_Odometro_Inicial) {
    this.NR_Odometro_Inicial = NR_Odometro_Inicial;
  }
  public long getNR_Odometro_Inicial() {
    return NR_Odometro_Inicial;
  }
  public void setNM_Tipo_Ocorrencia(String NM_Tipo_Ocorrencia) {
    this.NM_Tipo_Ocorrencia = NM_Tipo_Ocorrencia;
  }
  public String getNM_Tipo_Ocorrencia() {
    return NM_Tipo_Ocorrencia;
  }
  public void setDT_Troca_Motorista(String DT_Troca_Motorista) {
    this.DT_Troca_Motorista = DT_Troca_Motorista;
  }
  public String getDT_Troca_Motorista() {
    return DT_Troca_Motorista;
  }
  public void setOID_Cidade(long OID_Cidade) {
    this.OID_Cidade = OID_Cidade;
  }
  public long getOID_Cidade() {
    return OID_Cidade;
  }
  public void setOID_Veiculo(String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }
  public String getOID_Veiculo() {
    return OID_Veiculo;
  }
  public void setOID_Tipo_Ocorrencia(long OID_Tipo_Ocorrencia) {
    this.OID_Tipo_Ocorrencia = OID_Tipo_Ocorrencia;
  }
  public long getOID_Tipo_Ocorrencia() {
    return OID_Tipo_Ocorrencia;
  }
  public void setNR_Placa(String NR_Placa) {
    this.NR_Placa = NR_Placa;
  }
  public String getNR_Placa() {
    return NR_Placa;
  }
  public void setNR_Acerto(long NR_Acerto) {
    this.NR_Acerto = NR_Acerto;
  }
  public long getNR_Acerto() {
    return NR_Acerto;
  }
  public void setOID_Acerto(long OID_Acerto) {
    this.OID_Acerto = OID_Acerto;
  }
  public long getOID_Acerto() {
    return OID_Acerto;
  }
  public void setNM_Local(String NM_Local) {
    this.NM_Local = NM_Local;
  }
  public String getNM_Local() {
    return NM_Local;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public void setTX_Observacao(String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }
  public String getTX_Observacao() {
    return TX_Observacao;
  }
  public void setDM_Tipo(String DM_Tipo) {
    this.DM_Tipo = DM_Tipo;
  }
  public String getDM_Tipo() {
    return DM_Tipo;
  }
  public void setOID_Ordem_Frete(String OID_Ordem_Frete) {
    this.OID_Ordem_Frete = OID_Ordem_Frete;
  }
  public String getOID_Ordem_Frete() {
    return OID_Ordem_Frete;
  }
  public void setHR_Multa(String HR_Multa) {
    this.HR_Multa = HR_Multa;
  }
  public String getHR_Multa() {
    return HR_Multa;
  }

  public double getVL_Multa() {
	return VL_Multa;
  }
  public void setVL_Multa(double frete_Carreteiro) {
	VL_Multa = frete_Carreteiro;
  }


  public long getOid_Empresa() {
      return this.oid_Empresa;
  }

  public long getOID_Movimento_Conta_Corrente () {
    return OID_Movimento_Conta_Corrente;
  }

  public String getDT_Parcela () {
    return DT_Parcela;
  }

  public double getVL_Parcela () {
    return VL_Parcela;
  }

  public double getVL_Saldo () {
    return VL_Saldo;
  }

  public String getDM_Tipo_Multa () {
    return DM_Tipo_Multa;
  }

  public long getOID_Empresa () {
    return OID_Empresa;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public void setOid_Empresa(long oid_Empresa) {
      this.oid_Empresa = oid_Empresa;
  }

  public void setOID_Movimento_Conta_Corrente (long OID_Movimento_Conta_Corrente) {
    this.OID_Movimento_Conta_Corrente = OID_Movimento_Conta_Corrente;
  }

  public void setDT_Parcela (String DT_Parcela) {
    this.DT_Parcela = DT_Parcela;
  }

  public void setVL_Parcela (double VL_Parcela) {
    this.VL_Parcela = VL_Parcela;
  }

  public void setVL_Saldo (double VL_Saldo) {
    this.VL_Saldo = VL_Saldo;
  }

  public void setDM_Tipo_Multa (String DM_Tipo_Multa) {
    this.DM_Tipo_Multa = DM_Tipo_Multa;
  }

  public void setOID_Empresa (long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public String getDT_Emissao_Final() {
      return this.DT_Emissao_Final;
  }
  public void setDT_Emissao_Final(String emissao_Final) {
      this.DT_Emissao_Final = emissao_Final;
  }
  public String getDT_Emissao_Inicial() {
      return this.DT_Emissao_Inicial;
  }
  public void setDT_Emissao_Inicial(String emissao_Inicial) {
      this.DT_Emissao_Inicial = emissao_Inicial;
  }
  public String getNM_Fantasia_Pessoa() {
      return this.NM_Fantasia_Pessoa;
  }
  public void setNM_Fantasia_Pessoa(String fantasia_Pessoa) {
      this.NM_Fantasia_Pessoa = fantasia_Pessoa;
  }
  public String getNM_Razao_Social_Pessoa() {
      return this.NM_Razao_Social_Pessoa;
  }
  public void setNM_Razao_Social_Pessoa(String razao_Social_Pessoa) {
      this.NM_Razao_Social_Pessoa = razao_Social_Pessoa;
  }
  public String getNR_CNPJ_CPF_Pessoa() {
      return this.NR_CNPJ_CPF_Pessoa;
  }
  public void setNR_CNPJ_CPF_Pessoa(String pessoa) {
      this.NR_CNPJ_CPF_Pessoa = pessoa;
  }
}
