package com.master.ed;

/**
 * Título: WMS_Rastreamento_ProdutosED
 * Descrição: Rastreamento de Produtos - ED
 * Data da criação: 12/2003
 * Atualizado em: 12/2003
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

public class WMS_Rastreamento_ProdutosED extends MasterED {

  private int OID_Requisicao_Produto;
  private String DM_Tipo_Movimento;
  private int NR_Quantidade_Efetiva;

  private int OID_Deposito;
  private String NM_Deposito;

  private String OID_Pessoa;
  private String NM_Razao_Social_Pessoa;
  private String NR_CNPJ_CPF_Pessoa;

  private String OID_Pessoa_Destinatario;
  private String NM_Razao_Social_Destinatario;
  private String NR_CNPJ_CPF_Destinatario;

  private String OID_Pessoa_Transportador;
  private String NM_Razao_Social_Transportador;
  private String NR_CNPJ_CPF_Transportador;

  private int OID_Tipo_Movimento_Produto;
  private String NM_Tipo_Movimento_Produto;

  private String DT_Requisicao;
  private String HR_Requisicao;
  private String DM_Situacao;
  private String DT_Conclusao;
  private String HR_Conclusao;

  private String OID_Nota_Fiscal_Transacao;
  private String NR_Nota_Fiscal_Transacao;

  private String DT_Stamp;
  private String DM_Stamp;
  private String Usuario_Stamp;

  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public String getDM_Stamp() {
    return DM_Stamp;
  }
  public String getDT_Conclusao() {
    return DT_Conclusao;
  }
  public String getDT_Requisicao() {
    return DT_Requisicao;
  }
  public String getDT_Stamp() {
    return DT_Stamp;
  }
  public String getHR_Conclusao() {
    return HR_Conclusao;
  }
  public String getHR_Requisicao() {
    return HR_Requisicao;
  }
  public String getNM_Deposito() {
    return NM_Deposito;
  }
  public String getNM_Razao_Social_Destinatario() {
    return NM_Razao_Social_Destinatario;
  }
  public String getNM_Razao_Social_Pessoa() {
    return NM_Razao_Social_Pessoa;
  }
  public String getNM_Razao_Social_Transportador() {
    return NM_Razao_Social_Transportador;
  }
  public String getNR_CNPJ_CPF_Destinatario() {
    return NR_CNPJ_CPF_Destinatario;
  }
  public String getNM_Tipo_Movimento_Produto() {
    return NM_Tipo_Movimento_Produto;
  }
  public String getNR_CNPJ_CPF_Pessoa() {
    return NR_CNPJ_CPF_Pessoa;
  }
  public String getNR_CNPJ_CPF_Transportador() {
    return NR_CNPJ_CPF_Transportador;
  }
  public String getNR_Nota_Fiscal_Transacao() {
    return NR_Nota_Fiscal_Transacao;
  }
  public int getOID_Deposito() {
    return OID_Deposito;
  }
  public String getOID_Nota_Fiscal_Transacao() {
    return OID_Nota_Fiscal_Transacao;
  }
  public String getOID_Pessoa() {
    return OID_Pessoa;
  }
  public String getOID_Pessoa_Destinatario() {
    return OID_Pessoa_Destinatario;
  }
  public String getOID_Pessoa_Transportador() {
    return OID_Pessoa_Transportador;
  }
  public int getOID_Requisicao_Produto() {
    return OID_Requisicao_Produto;
  }
  public int getOID_Tipo_Movimento_Produto() {
    return OID_Tipo_Movimento_Produto;
  }
  public String getUsuario_Stamp() {
    return Usuario_Stamp;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public void setDM_Stamp(String DM_Stamp) {
    this.DM_Stamp = DM_Stamp;
  }
  public void setDT_Conclusao(String DT_Conclusao) {
    this.DT_Conclusao = DT_Conclusao;
  }
  public void setDT_Requisicao(String DT_Requisicao) {
    this.DT_Requisicao = DT_Requisicao;
  }
  public void setDT_Stamp(String DT_Stamp) {
    this.DT_Stamp = DT_Stamp;
  }
  public void setHR_Conclusao(String HR_Conclusao) {
    this.HR_Conclusao = HR_Conclusao;
  }
  public void setHR_Requisicao(String HR_Requisicao) {
    this.HR_Requisicao = HR_Requisicao;
  }
  public void setNM_Deposito(String NM_Deposito) {
    this.NM_Deposito = NM_Deposito;
  }
  public void setNM_Razao_Social_Destinatario(String NM_Razao_Social_Destinatario) {
    this.NM_Razao_Social_Destinatario = NM_Razao_Social_Destinatario;
  }
  public void setNM_Razao_Social_Pessoa(String NM_Razao_Social_Pessoa) {
    this.NM_Razao_Social_Pessoa = NM_Razao_Social_Pessoa;
  }
  public void setNM_Razao_Social_Transportador(String NM_Razao_Social_Transportador) {
    this.NM_Razao_Social_Transportador = NM_Razao_Social_Transportador;
  }
  public void setNM_Tipo_Movimento_Produto(String NM_Tipo_Movimento_Produto) {
    this.NM_Tipo_Movimento_Produto = NM_Tipo_Movimento_Produto;
  }
  public void setNR_CNPJ_CPF_Destinatario(String NR_CNPJ_CPF_Destinatario) {
    this.NR_CNPJ_CPF_Destinatario = NR_CNPJ_CPF_Destinatario;
  }
  public void setNR_CNPJ_CPF_Pessoa(String NR_CNPJ_CPF_Pessoa) {
    this.NR_CNPJ_CPF_Pessoa = NR_CNPJ_CPF_Pessoa;
  }
  public void setNR_CNPJ_CPF_Transportador(String NR_CNPJ_CPF_Transportador) {
    this.NR_CNPJ_CPF_Transportador = NR_CNPJ_CPF_Transportador;
  }
  public void setNR_Nota_Fiscal_Transacao(String NR_Nota_Fiscal_Transacao) {
    this.NR_Nota_Fiscal_Transacao = NR_Nota_Fiscal_Transacao;
  }
  public void setOID_Deposito(int OID_Deposito) {
    this.OID_Deposito = OID_Deposito;
  }
  public void setOID_Nota_Fiscal_Transacao(String OID_Nota_Fiscal_Transacao) {
    this.OID_Nota_Fiscal_Transacao = OID_Nota_Fiscal_Transacao;
  }
  public void setOID_Pessoa(String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public void setOID_Pessoa_Destinatario(String OID_Pessoa_Destinatario) {
    this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
  }
  public void setOID_Pessoa_Transportador(String OID_Pessoa_Transportador) {
    this.OID_Pessoa_Transportador = OID_Pessoa_Transportador;
  }
  public void setOID_Requisicao_Produto(int OID_Requisicao_Produto) {
    this.OID_Requisicao_Produto = OID_Requisicao_Produto;
  }
  public void setOID_Tipo_Movimento_Produto(int OID_Tipo_Movimento_Produto) {
    this.OID_Tipo_Movimento_Produto = OID_Tipo_Movimento_Produto;
  }
  public void setUsuario_Stamp(String Usuario_Stamp) {
    this.Usuario_Stamp = Usuario_Stamp;
  }
  public String getDM_Tipo_Movimento() {
    return DM_Tipo_Movimento;
  }
  public void setDM_Tipo_Movimento(String DM_Tipo_Movimento) {
    this.DM_Tipo_Movimento = DM_Tipo_Movimento;
  }
  public int getNR_Quantidade_Efetiva() {
    return NR_Quantidade_Efetiva;
  }
  public void setNR_Quantidade_Efetiva(int NR_Quantidade_Efetiva) {
    this.NR_Quantidade_Efetiva = NR_Quantidade_Efetiva;
  }


}