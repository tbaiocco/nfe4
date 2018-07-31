package com.master.ed;

public class Duplica_TabelaED extends MasterED{

  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private int OID_Produto_Origem;
  private int OID_Produto_Destino;
  private String DT_Encerramento;
  private String DT_Vigencia_Inicial;
  private String DT_Vigencia_Final;
  private long OID_Cidade_Origem;
  private long OID_Cidade_Destino;
  private String DM_Transferencia;
  private String DM_Tipo_Tabela;
  private double PE_Reajuste;
  private String DM_Tipo_Tabela_Frete;
  private double PE_Desconto;
  private double PE_E_AD_Valorem;
  private double PE_C_AD_Valorem;
  private double PE_R_AD_Valorem;
  private double PE_D_AD_Valorem;
  private String DM_Aplicacao;

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
  public void setOID_Produto_Origem(int OID_Produto_Origem) {
    this.OID_Produto_Origem = OID_Produto_Origem;
  }
  public int getOID_Produto_Origem() {
    return OID_Produto_Origem;
  }
  public void setOID_Produto_Destino(int OID_Produto_Destino) {
    this.OID_Produto_Destino = OID_Produto_Destino;
  }
  public int getOID_Produto_Destino() {
    return OID_Produto_Destino;
  }
  public void setDT_Encerramento(String DT_Encerramento) {
    this.DT_Encerramento = DT_Encerramento;
  }
  public String getDT_Encerramento() {
    return DT_Encerramento;
  }
  public void setDT_Vigencia_Inicial(String DT_Vigencia_Inicial) {
    this.DT_Vigencia_Inicial = DT_Vigencia_Inicial;
  }
  public String getDT_Vigencia_Inicial() {
    return DT_Vigencia_Inicial;
  }
  public void setDT_Vigencia_Final(String DT_Vigencia_Final) {
    this.DT_Vigencia_Final = DT_Vigencia_Final;
  }
  public String getDT_Vigencia_Final() {
    return DT_Vigencia_Final;
  }
  public void setOID_Cidade_Origem(long OID_Cidade_Origem) {
    this.OID_Cidade_Origem = OID_Cidade_Origem;
  }
  public long getOID_Cidade_Origem() {
    return OID_Cidade_Origem;
  }
  public void setOID_Cidade_Destino(long OID_Cidade_Destino) {
    this.OID_Cidade_Destino = OID_Cidade_Destino;
  }
  public long getOID_Cidade_Destino() {
    return OID_Cidade_Destino;
  }
  public void setDM_Transferencia(String DM_Transferencia) {
    this.DM_Transferencia = DM_Transferencia;
  }
  public String getDM_Transferencia() {
    return DM_Transferencia;
  }
  public void setPE_Reajuste(double PE_Reajuste) {
    this.PE_Reajuste = PE_Reajuste;
  }
  public double getPE_Reajuste() {
    return PE_Reajuste;
  }
  public void setDM_Tipo_Tabela_Frete(String DM_Tipo_Tabela_Frete) {
    this.DM_Tipo_Tabela_Frete = DM_Tipo_Tabela_Frete;
  }
  public String getDM_Tipo_Tabela_Frete() {
    return DM_Tipo_Tabela_Frete;
  }
  public void setPE_Desconto(double PE_Desconto) {
    this.PE_Desconto = PE_Desconto;
  }
  public double getPE_Desconto() {
    return PE_Desconto;
  }
  public void setPE_E_AD_Valorem(double PE_E_AD_Valorem) {
    this.PE_E_AD_Valorem = PE_E_AD_Valorem;
  }
  public double getPE_E_AD_Valorem() {
    return PE_E_AD_Valorem;
  }
  public void setPE_C_AD_Valorem(double PE_C_AD_Valorem) {
    this.PE_C_AD_Valorem = PE_C_AD_Valorem;
  }
  public double getPE_C_AD_Valorem() {
    return PE_C_AD_Valorem;
  }
  public void setPE_R_AD_Valorem(double PE_R_AD_Valorem) {
    this.PE_R_AD_Valorem = PE_R_AD_Valorem;
  }
  public double getPE_R_AD_Valorem() {
    return PE_R_AD_Valorem;
  }
  public void setPE_D_AD_Valorem(double PE_D_AD_Valorem) {
    this.PE_D_AD_Valorem = PE_D_AD_Valorem;
  }
  public double getPE_D_AD_Valorem() {
    return PE_D_AD_Valorem;
  }

  public void setDM_Aplicacao (String DM_Aplicacao) {
    this.DM_Aplicacao = DM_Aplicacao;
  }

  public String getDM_Aplicacao () {
    return DM_Aplicacao;
  }
public String getDM_Tipo_Tabela() {
	return DM_Tipo_Tabela;
}
public void setDM_Tipo_Tabela(String tipo_Tabela) {
	DM_Tipo_Tabela = tipo_Tabela;
}
}