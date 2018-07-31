package com.master.ed;

public class BancoED extends MasterED implements java.io.Serializable{

    public BancoED() {
        super();
    }
    public BancoED(long banco) {
        OID_Banco = banco;
    }
    
  private String CD_Banco;
  private long OID_Banco;
  private String CD_Remessa;
  private String CD_Baixa;
  private String CD_Desconto;
  private String CD_Alteracao_Vencimento;
  private String CD_Protesto;
  private String CD_Sustar_Protesto;
  private String CD_Tipo_Documento;
  private String CD_Primeira_Instr_Protesto;
  private String CD_Segunda_Instr_Protesto;
  private String NR_Dias_Protesto;
  private String OID_Pessoa;
  private String NM_Banco;
  private String NR_Bloqueto_informado;
  private String NR_Bloqueto_Calculado;
  private String DM_Formulario;

  public void setOID_Banco(long OID_Banco) {
    this.OID_Banco = OID_Banco;
  }
  public long getOID_Banco() {
    return OID_Banco;
  }
  public void setCD_Banco(String CD_Banco) {
    this.CD_Banco = CD_Banco;
  }
  public String getCD_Banco() {
    return CD_Banco;
  }
  public void setCD_Remessa(String CD_Remessa) {
    this.CD_Remessa = CD_Remessa;
  }
  public String getCD_Remessa() {
    return CD_Remessa;
  }
  public void setCD_Baixa(String CD_Baixa) {
    this.CD_Baixa = CD_Baixa;
  }
  public String getCD_Baixa() {
    return CD_Baixa;
  }
  public void setCD_Desconto(String CD_Desconto) {
    this.CD_Desconto = CD_Desconto;
  }
  public String getCD_Desconto() {
    return CD_Desconto;
  }
  public void setCD_Alteracao_Vencimento(String CD_Alteracao_Vencimento) {
    this.CD_Alteracao_Vencimento = CD_Alteracao_Vencimento;
  }
  public String getCD_Alteracao_Vencimento() {
    return CD_Alteracao_Vencimento;
  }
  public void setCD_Protesto(String CD_Protesto) {
    this.CD_Protesto = CD_Protesto;
  }
  public String getCD_Protesto() {
    return CD_Protesto;
  }
  public void setCD_Sustar_Protesto(String CD_Sustar_Protesto) {
    this.CD_Sustar_Protesto = CD_Sustar_Protesto;
  }
  public String getCD_Sustar_Protesto() {
    return CD_Sustar_Protesto;
  }
  public void setCD_Tipo_Documento(String CD_Tipo_Documento) {
    this.CD_Tipo_Documento = CD_Tipo_Documento;
  }
  public String getCD_Tipo_Documento() {
    return CD_Tipo_Documento;
  }
  public void setCD_Primeira_Instr_Protesto(String CD_Primeira_Instr_Protesto) {
    this.CD_Primeira_Instr_Protesto = CD_Primeira_Instr_Protesto;
  }
  public String getCD_Primeira_Instr_Protesto() {
    return CD_Primeira_Instr_Protesto;
  }
  public void setCD_Segunda_Instr_Protesto(String CD_Segunda_Instr_Protesto) {
    this.CD_Segunda_Instr_Protesto = CD_Segunda_Instr_Protesto;
  }
  public String getCD_Segunda_Instr_Protesto() {
    return CD_Segunda_Instr_Protesto;
  }
  public void setNR_Dias_Protesto(String NR_Dias_Protesto) {
    this.NR_Dias_Protesto = NR_Dias_Protesto;
  }
  public String getNR_Dias_Protesto() {
    return NR_Dias_Protesto;
  }
  public void setOID_Pessoa(String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public String getOID_Pessoa() {
    return OID_Pessoa;
  }
  public void setNM_Banco(String NM_Banco) {
    this.NM_Banco = NM_Banco;
  }
  public String getNM_Banco() {
    return NM_Banco;
  }
  public void setNR_Bloqueto_informado(String NR_Bloqueto_informado) {
    this.NR_Bloqueto_informado = NR_Bloqueto_informado;
  }
  public String getNR_Bloqueto_informado() {
    return NR_Bloqueto_informado;
  }
  public void setNR_Bloqueto_Calculado(String NR_Bloqueto_Calculado) {
    this.NR_Bloqueto_Calculado = NR_Bloqueto_Calculado;
  }
  public String getNR_Bloqueto_Calculado() {
    return NR_Bloqueto_Calculado;
  }
  public void setDM_Formulario(String DM_Formulario) {
    this.DM_Formulario = DM_Formulario;
  }
  public String getDM_Formulario() {
    return DM_Formulario;
  }

}