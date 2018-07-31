package com.master.ed;

import javax.servlet.http.*;

public class Comprovante_EntregaED extends RelatorioBaseED {
  public Comprovante_EntregaED () {
  }


  public Comprovante_EntregaED(HttpServletResponse response, String nomeRelatorio) {
      super(response, nomeRelatorio);
  }

  private String OID_Conhecimento;
  private String NM_Fantasia_Pessoa;
  private String DT_Emissao;
  private long NR_Conhecimento;
  private long OID_Unidade;
  private String CD_Unidade;
  private String NM_Pessoa_Remetente;
  private long NR_Comprovante_Entrega;
  private String NM_Cidade_Unidade;
  private String OID_Comprovante_Entrega;
  private String DM_Situacao;
  private String DT_Chegada;
  private String HR_Chegada;
  private String TX_Observacao;
  private String DM_Tipo;
  private String NM_Pessoa_Entregadora;
  private double PE_Custo_Entrega;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String DM_Relatorio;
  private long OID_Empresa;
  private String DM_Tipo_Comprovante_Entrega;
  private String DM_Tipo_Documento;
  private String DM_Acao;
  private String DT_Entrega;
  private String DT_Arquivo;

  public void setOID_Conhecimento(String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }
  public String getOID_Conhecimento() {
    return OID_Conhecimento;
  }
  public void setOID_Comprovante_Entrega(String OID_Comprovante_Entrega) {
    this.OID_Comprovante_Entrega = OID_Comprovante_Entrega;
  }
  public String getOID_Comprovante_Entrega() {
    return OID_Comprovante_Entrega;
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
  public void setNR_Conhecimento(long NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }
  public long getNR_Conhecimento() {
    return NR_Conhecimento;
  }
  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
  }
  public void setNM_Pessoa_Remetente(String NM_Pessoa_Remetente) {
    this.NM_Pessoa_Remetente = NM_Pessoa_Remetente;
  }
  public String getNM_Pessoa_Remetente() {
    return NM_Pessoa_Remetente;
  }

  public void setNR_Comprovante_Entrega(long NR_Comprovante_Entrega) {
    this.NR_Comprovante_Entrega = NR_Comprovante_Entrega;
  }
  public long getNR_Comprovante_Entrega() {
    return NR_Comprovante_Entrega;
  }

  public void setNM_Cidade_Unidade(String NM_Cidade_Unidade) {
    this.NM_Cidade_Unidade = NM_Cidade_Unidade;
  }
  public String getNM_Cidade_Unidade() {
    return NM_Cidade_Unidade;
  }

  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public void setDT_Chegada(String DT_Chegada) {
    this.DT_Chegada = DT_Chegada;
  }
  public String getDT_Chegada() {
    return DT_Chegada;
  }
  public void setHR_Chegada(String HR_Chegada) {
    this.HR_Chegada = HR_Chegada;
  }
  public String getHR_Chegada() {
    return HR_Chegada;
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

  public void setNM_Pessoa_Entregadora (String NM_Pessoa_Entregadora) {
    this.NM_Pessoa_Entregadora = NM_Pessoa_Entregadora;
  }

  public String getNM_Pessoa_Entregadora () {
    return NM_Pessoa_Entregadora;
  }

  public void setPE_Custo_Entrega (double PE_Custo_Entrega) {
    this.PE_Custo_Entrega = PE_Custo_Entrega;
  }

  public double getPE_Custo_Entrega () {
    return PE_Custo_Entrega;
  }

  public String getDM_Tipo_Comprovante_Entrega () {
    return DM_Tipo_Comprovante_Entrega;
  }

  public long getOID_Empresa () {
    return OID_Empresa;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public void setDM_Tipo_Comprovante_Entrega (String DM_Tipo_Comprovante_Entrega) {
    this.DM_Tipo_Comprovante_Entrega = DM_Tipo_Comprovante_Entrega;
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

  public void setDM_Tipo_Documento (String DM_Tipo_Documento) {
    this.DM_Tipo_Documento = DM_Tipo_Documento;
  }

  public String getDM_Tipo_Documento () {
    return DM_Tipo_Documento;
  }

  public void setDM_Acao (String DM_Acao) {
    this.DM_Acao = DM_Acao;
  }

  public String getDM_Acao () {
    return DM_Acao;
  }

  public void setDT_Entrega (String DT_Entrega) {
    this.DT_Entrega = DT_Entrega;
  }

  public String getDT_Entrega () {
    return DT_Entrega;
  }

  public void setDT_Arquivo (String DT_Arquivo) {
    this.DT_Arquivo = DT_Arquivo;
  }

  public String getDT_Arquivo () {
    return DT_Arquivo;
  }

}
