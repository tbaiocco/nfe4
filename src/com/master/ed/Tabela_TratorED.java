package com.master.ed;

public class Tabela_TratorED extends MasterED{

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
  private double PE_Reajuste;
  private String DM_Emissao;

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
  public void setDM_Emissao(String DM_Emissao) {
    this.DM_Emissao = DM_Emissao;
  }
  public String getDM_Emissao() {
    return DM_Emissao;
  }
}