package com.master.ed;

public class Fatura_MasterED
    extends MasterED {

  private String OID_Fatura_Master;
  private long NR_Fatura_Master;
  private double VL_Master;
  private String DM_Situacao;
  private String DT_Fatura_Master;
  private String OID_Pessoa;
  private String DM_Cia_Aerea;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private double NR_Peso;
  private double NR_Volumes;
  private String HR_Entrada;
  private double VL_Total_Frete;
  private String NM_Produto;
  private String DT_Fatura_Master_Inicial;
  private String DT_Fatura_Master_Final;
  private long OID_Modal;
  private String NR_Conhecimento;
  private String OID_Conhecimento;
  private double NR_Peso_Cubado;
  private long OID_Unidade;
  private String NR_Master;
  private String NM_Servico;
  private String NM_Trecho;
  private double VL_Seguro;
  private double VL_Frete;
  private double VL_Taxas;
  private double VL_Liquido;
  private String NR_Fatura;
  private String DT_Entrada;
  private String DM_Relatorio;
  private String NM_Destinatario;
  private String CD_Estado;
  private String DM_Tipo_Servico;
  private long OID_Cidade;
  private long OID_Produto;
  private String NM_Arquivo;
  private String NM_Natureza;
  private String DM_Tipo_Fatura_Master;
  private String DM_Conhecimento_Impresso;
  
  private long OID_Lote_Fornecedor;
  
  public void setOID_Fatura_Master (String OID_Fatura_Master) {
    this.OID_Fatura_Master = OID_Fatura_Master;
  }

  public String getOID_Fatura_Master () {
    return OID_Fatura_Master;
  }

  public void setNR_Fatura_Master (long NR_Fatura_Master) {
    this.NR_Fatura_Master = NR_Fatura_Master;
  }

  public long getNR_Fatura_Master () {
    return NR_Fatura_Master;
  }

  public void setVL_Master (double VL_Master) {

    this.VL_Master = VL_Master;
  }

  public double getVL_Master () {

    return VL_Master;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setDT_Fatura_Master (String DT_Fatura_Master) {
    this.DT_Fatura_Master = DT_Fatura_Master;
  }

  public String getDT_Fatura_Master () {
    return DT_Fatura_Master;
  }

  public void setOID_Pessoa (String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }

  public void setDM_Cia_Aerea (String DM_Cia_Aerea) {

    this.DM_Cia_Aerea = DM_Cia_Aerea;
  }

  public String getDM_Cia_Aerea () {

    return DM_Cia_Aerea;
  }

  public void setNM_Pessoa_Remetente (String NM_Pessoa_Remetente) {
    this.NM_Pessoa_Remetente = NM_Pessoa_Remetente;
  }

  public String getNM_Pessoa_Remetente () {
    return NM_Pessoa_Remetente;
  }

  public void setNM_Pessoa_Destinatario (String NM_Pessoa_Destinatario) {
    this.NM_Pessoa_Destinatario = NM_Pessoa_Destinatario;
  }

  public String getNM_Pessoa_Destinatario () {
    return NM_Pessoa_Destinatario;
  }

  public void setNR_Peso (double NR_Peso) {
    this.NR_Peso = NR_Peso;
  }

  public double getNR_Peso () {
    return NR_Peso;
  }

  public void setNR_Volumes (double NR_Volumes) {
    this.NR_Volumes = NR_Volumes;
  }

  public double getNR_Volumes () {
    return NR_Volumes;
  }

  public void setHR_Entrada (String HR_Entrada) {
    this.HR_Entrada = HR_Entrada;
  }

  public String getHR_Entrada () {
    return HR_Entrada;
  }

  public String getDT_Fatura_Master_Final () {
    return DT_Fatura_Master_Final;
  }

  public String getDT_Fatura_Master_Inicial () {
    return DT_Fatura_Master_Inicial;
  }

  public void setDT_Fatura_Master_Final (String DT_Fatura_Master_Final) {
    this.DT_Fatura_Master_Final = DT_Fatura_Master_Final;
  }

  public void setDT_Fatura_Master_Inicial (String DT_Fatura_Master_Inicial) {
    this.DT_Fatura_Master_Inicial = DT_Fatura_Master_Inicial;
  }

  public String getNM_Produto () {
    return NM_Produto;
  }

  public void setNM_Produto (String NM_Produto) {
    this.NM_Produto = NM_Produto;
  }

  public void setOID_Unidade (long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }

  public long getOID_Unidade () {
    return OID_Unidade;
  }

  public void setOID_Modal (long OID_Modal) {
    this.OID_Modal = OID_Modal;
  }

  public long getOID_Modal () {
    return OID_Modal;
  }

  public void setNR_Conhecimento (String NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }

  public String getNR_Conhecimento () {
    return NR_Conhecimento;
  }

  public void setOID_Conhecimento (String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }

  public String getOID_Conhecimento () {
    return OID_Conhecimento;
  }

  public void setNR_Peso_Cubado (double NR_Peso_Cubado) {
    this.NR_Peso_Cubado = NR_Peso_Cubado;
  }

  public double getNR_Peso_Cubado () {
    return NR_Peso_Cubado;
  }

  public void setVL_Total_Frete (double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }

  public double getVL_Total_Frete () {
    return VL_Total_Frete;
  }

  public void setNR_Master (String NR_Master) {

    this.NR_Master = NR_Master;
  }

  public String getNR_Master () {

    return NR_Master;
  }

  public void setNM_Servico (String NM_Servico) {
    this.NM_Servico = NM_Servico;
  }

  public String getNM_Servico () {
    return NM_Servico;
  }

  public void setNM_Trecho (String NM_Trecho) {

    this.NM_Trecho = NM_Trecho;
  }

  public String getNM_Trecho () {

    return NM_Trecho;
  }

  public void setVL_Seguro (double VL_Seguro) {

    this.VL_Seguro = VL_Seguro;
  }

  public double getVL_Seguro () {

    return VL_Seguro;
  }

  public void setVL_Frete (double VL_Frete) {

    this.VL_Frete = VL_Frete;
  }

  public double getVL_Frete () {

    return VL_Frete;
  }

  public void setVL_Taxas (double VL_Taxas) {

    this.VL_Taxas = VL_Taxas;
  }

  public double getVL_Taxas () {

    return VL_Taxas;
  }

  public void setVL_Liquido (double VL_Liquido) {
    this.VL_Liquido = VL_Liquido;
  }

  public double getVL_Liquido () {
    return VL_Liquido;
  }

  public void setNR_Fatura (String NR_Fatura) {
    this.NR_Fatura = NR_Fatura;
  }

  public String getNR_Fatura () {
    return NR_Fatura;
  }

  public void setDT_Entrada (String DT_Entrada) {
    this.DT_Entrada = DT_Entrada;
  }

  public String getDT_Entrada () {
    return DT_Entrada;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public void setNM_Destinatario (String NM_Destinatario) {
    this.NM_Destinatario = NM_Destinatario;
  }

  public String getNM_Destinatario () {
    return NM_Destinatario;
  }

  public void setCD_Estado (String CD_Estado) {
    this.CD_Estado = CD_Estado;
  }

  public String getCD_Estado () {
    return CD_Estado;
  }

  public void setDM_Tipo_Servico (String DM_Tipo_Servico) {
    this.DM_Tipo_Servico = DM_Tipo_Servico;
  }

  public String getDM_Tipo_Servico () {
    return DM_Tipo_Servico;
  }

  public void setOID_Cidade (long OID_Cidade) {
    this.OID_Cidade = OID_Cidade;
  }

  public long getOID_Cidade () {
    return OID_Cidade;
  }

  public void setOID_Produto (long OID_Produto) {
    this.OID_Produto = OID_Produto;
  }

  public long getOID_Produto () {
    return OID_Produto;
  }

  public void setNM_Arquivo (String NM_Arquivo) {
    this.NM_Arquivo = NM_Arquivo;
  }

  public String getNM_Arquivo () {
    return NM_Arquivo;
  }

  public void setNM_Natureza (String NM_Natureza) {
    this.NM_Natureza = NM_Natureza;
  }

  public String getNM_Natureza () {
    return NM_Natureza;
  }

  public void setDM_Tipo_Fatura_Master (String DM_Tipo_Fatura_Master) {
    this.DM_Tipo_Fatura_Master = DM_Tipo_Fatura_Master;
  }

  public String getDM_Tipo_Fatura_Master () {
    return DM_Tipo_Fatura_Master;
  }

  public void setDM_Conhecimento_Impresso (String DM_Conhecimento_Impresso) {
    this.DM_Conhecimento_Impresso = DM_Conhecimento_Impresso;
  }

  public String getDM_Conhecimento_Impresso () {
    return DM_Conhecimento_Impresso;
  }

  public long getOID_Lote_Fornecedor () {
    return OID_Lote_Fornecedor;
  }

  public void setOID_Lote_Fornecedor (long OID_Lote_Fornecedor) {
    this.OID_Lote_Fornecedor = OID_Lote_Fornecedor;
  }
}
