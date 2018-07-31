package com.master.ed;

public class Ocorrencia_EmbarqueED extends MasterED{

  private String TX_Observacao;
  private String DT_Ocorrencia_Embarque;
  private String HR_Ocorrencia_Embarque;
  private long OID_Ocorrencia_Embarque;
  private long OID_Tipo_Ocorrencia;
  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String OID_Pessoa_Consignatario;
  private String DT_Emissao;
  private long NR_Embarque;
  private long OID_Unidade;
  private String NM_Tipo_Ocorrencia;
  private String CD_Unidade;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private String OID_Nota_Fiscal;
  private String DT_Ocorrencia_Lancada;
  private String HR_Ocorrencia_Lancada;
  private long OID_Embarque;
  private String NM_Cidade;
  private String CD_Estado;
  private long OID_Cidade;
  private String TX_Descricao;
  private String OID_Manifesto;

  private long NR_Odometro_Parcial;

  public void setOID_Embarque(long OID_Embarque) {
    this.OID_Embarque = OID_Embarque;
  }
  public long getOID_Embarque() {
    return OID_Embarque;
  }
  public void setTX_Observacao(String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }
  public String getTX_Observacao() {
    return TX_Observacao;
  }
  public void setOID_Ocorrencia_Embarque(long OID_Ocorrencia_Embarque) {
    this.OID_Ocorrencia_Embarque = OID_Ocorrencia_Embarque;
  }
  public long getOID_Ocorrencia_Embarque() {
    return OID_Ocorrencia_Embarque;
  }
  public void setDT_Ocorrencia_Embarque(String DT_Ocorrencia_Embarque) {
    this.DT_Ocorrencia_Embarque = DT_Ocorrencia_Embarque;
  }
  public String getDT_Ocorrencia_Embarque() {
    return DT_Ocorrencia_Embarque;
  }
  public void setHR_Ocorrencia_Embarque(String HR_Ocorrencia_Embarque) {
    this.HR_Ocorrencia_Embarque = HR_Ocorrencia_Embarque;
  }
  public String getHR_Ocorrencia_Embarque() {
    return HR_Ocorrencia_Embarque;
  }
  public void setOID_Tipo_Ocorrencia(long OID_Tipo_Ocorrencia) {
    this.OID_Tipo_Ocorrencia = OID_Tipo_Ocorrencia;
  }
  public long getOID_Tipo_Ocorrencia() {
    return OID_Tipo_Ocorrencia;
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
  public void setOID_Pessoa_Consignatario(String OID_Pessoa_Consignatario) {
    this.OID_Pessoa_Consignatario = OID_Pessoa_Consignatario;
  }
  public String getOID_Pessoa_Consignatario() {
    return OID_Pessoa_Consignatario;
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
  public void setNR_Embarque(long NR_Embarque) {
    this.NR_Embarque = NR_Embarque;
  }
  public long getNR_Embarque() {
    return NR_Embarque;
  }
  public void setNM_Tipo_Ocorrencia(String NM_Tipo_Ocorrencia) {
    this.NM_Tipo_Ocorrencia = NM_Tipo_Ocorrencia;
  }
  public String getNM_Tipo_Ocorrencia() {
    return NM_Tipo_Ocorrencia;
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
  public void setNM_Pessoa_Destinatario(String NM_Pessoa_Destinatario) {
    this.NM_Pessoa_Destinatario = NM_Pessoa_Destinatario;
  }
  public String getNM_Pessoa_Destinatario() {
    return NM_Pessoa_Destinatario;
  }
  public void setOID_Nota_Fiscal(String OID_Nota_Fiscal) {
    this.OID_Nota_Fiscal = OID_Nota_Fiscal;
  }
  public String getOID_Nota_Fiscal() {
    return OID_Nota_Fiscal;
  }
  public void setDT_Ocorrencia_Lancada(String DT_Ocorrencia_Lancada) {
    this.DT_Ocorrencia_Lancada = DT_Ocorrencia_Lancada;
  }
  public String getDT_Ocorrencia_Lancada() {
    return DT_Ocorrencia_Lancada;
  }
  public void setHR_Ocorrencia_Lancada(String HR_Ocorrencia_Lancada) {
    this.HR_Ocorrencia_Lancada = HR_Ocorrencia_Lancada;
  }
  public String getHR_Ocorrencia_Lancada() {
    return HR_Ocorrencia_Lancada;
  }
  public void setNM_Cidade(String NM_Cidade) {
    this.NM_Cidade = NM_Cidade;
  }
  public String getNM_Cidade() {
    return NM_Cidade;
  }
  public void setCD_Estado(String CD_Estado) {
    this.CD_Estado = CD_Estado;
  }
  public String getCD_Estado() {
    return CD_Estado;
  }
  public void setOID_Cidade(long OID_Cidade) {
    this.OID_Cidade = OID_Cidade;
  }
  public long getOID_Cidade() {
    return OID_Cidade;
  }
  public void setTX_Descricao(String TX_Descricao) {
    this.TX_Descricao = TX_Descricao;
  }
  public String getTX_Descricao() {
    return TX_Descricao;
  }

  public long getNR_Odometro_Parcial() {
    return NR_Odometro_Parcial;
  }
  public void setNR_Odometro_Parcial(long NR_Odometro_Parcial) {
    this.NR_Odometro_Parcial = NR_Odometro_Parcial;
  }
	public String getOID_Manifesto() {
		return OID_Manifesto;
	}
	public void setOID_Manifesto(String OID_Manifesto) {
		this.OID_Manifesto = OID_Manifesto;
	}

}
