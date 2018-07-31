package com.master.ed;

public class Origem_DuplicataED
    extends MasterED {

  public Origem_DuplicataED () {
    super ();
  }

  public Origem_DuplicataED (int oid_Parcelamento , String oid_Nota_Fiscal) {
    this.oid_Parcelamento = oid_Parcelamento;
    this.oid_Nota_Fiscal = oid_Nota_Fiscal;
  }

  private String OID_Conhecimento_Faturamento;
  private String OID_Conhecimento;
  private String DT_Origem_Duplicata;
  private String HR_Origem_Duplicata;
  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String DT_Emissao;
  private long NR_Conhecimento;
  private long OID_Unidade;
  private String CD_Unidade;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private long NR_Duplicata;
  private String OID_Duplicata;
  private String OID_Origem_Duplicata;
  private String DM_Situacao;
  private double VL_Total_Frete;
  private String OID_Pessoa_Pagador;

  private double VL_Desconto;
  private long OID_Tipo_Ocorrencia;
  private String TX_Observacao;
  private double VL_Desconto_Concedido;

  private int oid_Parcelamento;
  private String oid_Nota_Fiscal;

  private long NR_Nota_Fiscal;

  private int oid_Carteira;
  private double VL_Saldo;
  private String DM_Tipo_Origem;
  private String DM_Pode_Estornar;
  private long OID_Lote_Faturamento;

  public String getOID_Conhecimento_Faturamento () {
    return OID_Conhecimento_Faturamento;
  }

  public void setOID_Conhecimento_Faturamento (String conhecimento_Faturamento) {
    OID_Conhecimento_Faturamento = conhecimento_Faturamento;
  }

  public String getOid_Nota_Fiscal () {
    return oid_Nota_Fiscal;
  }

  public void setOid_Nota_Fiscal (String oid_Nota_Fiscal) {
    this.oid_Nota_Fiscal = oid_Nota_Fiscal;
  }

  public int getOid_Parcelamento () {
    return oid_Parcelamento;
  }

  public void setOid_Parcelamento (int oid_Parcelamento) {
    this.oid_Parcelamento = oid_Parcelamento;
  }

  public void setOID_Conhecimento (String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }

  public String getOID_Conhecimento () {
    return OID_Conhecimento;
  }

  public void setOID_Origem_Duplicata (String OID_Origem_Duplicata) {
    this.OID_Origem_Duplicata = OID_Origem_Duplicata;
  }

  public String getOID_Origem_Duplicata () {
    return OID_Origem_Duplicata;
  }

  public void setDT_Origem_Duplicata (String DT_Origem_Duplicata) {
    this.DT_Origem_Duplicata = DT_Origem_Duplicata;
  }

  public String getDT_Origem_Duplicata () {
    return DT_Origem_Duplicata;
  }

  public void setHR_Origem_Duplicata (String HR_Origem_Duplicata) {
    this.HR_Origem_Duplicata = HR_Origem_Duplicata;
  }

  public String getHR_Origem_Duplicata () {
    return HR_Origem_Duplicata;
  }

  public void setOID_Pessoa (String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }

  public void setOID_Pessoa_Destinatario (String OID_Pessoa_Destinatario) {
    this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
  }

  public String getOID_Pessoa_Destinatario () {
    return OID_Pessoa_Destinatario;
  }

  public void setOID_Unidade (long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }

  public long getOID_Unidade () {
    return OID_Unidade;
  }

  public void setDT_Emissao (String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }

  public String getDT_Emissao () {
    return DT_Emissao;
  }

  public void setNR_Conhecimento (long NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }

  public long getNR_Conhecimento () {
    return NR_Conhecimento;
  }

  public void setCD_Unidade (String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }

  public String getCD_Unidade () {
    return CD_Unidade;
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

  public void setOID_Duplicata (String OID_Duplicata) {
    this.OID_Duplicata = OID_Duplicata;
  }

  public String getOID_Duplicata () {
    return OID_Duplicata;
  }

  public void setNR_Duplicata (long NR_Duplicata) {
    this.NR_Duplicata = NR_Duplicata;
  }

  public long getNR_Duplicata () {
    return NR_Duplicata;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setVL_Total_Frete (double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }

  public double getVL_Total_Frete () {
    return VL_Total_Frete;
  }

  public void setOID_Pessoa_Pagador (String OID_Pessoa_Pagador) {
    this.OID_Pessoa_Pagador = OID_Pessoa_Pagador;
  }

  public String getOID_Pessoa_Pagador () {
    return OID_Pessoa_Pagador;
  }

  public long getOID_Tipo_Ocorrencia () {
    return OID_Tipo_Ocorrencia;
  }

  public void setOID_Tipo_Ocorrencia (long OID_Tipo_Ocorrencia) {
    this.OID_Tipo_Ocorrencia = OID_Tipo_Ocorrencia;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public void setVL_Desconto (double VL_Desconto) {
    this.VL_Desconto = VL_Desconto;
  }

  public void setVL_Desconto_Concedido (double VL_Desconto_Concedido) {
    this.VL_Desconto_Concedido = VL_Desconto_Concedido;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public double getVL_Desconto () {
    return VL_Desconto;
  }

  public double getVL_Desconto_Concedido () {
    return VL_Desconto_Concedido;
  }

  public int getOid_Carteira () {
    return oid_Carteira;
  }

  public long getOID_Lote_Faturamento () {
    return OID_Lote_Faturamento;
  }

  public String getDM_Tipo_Origem () {
    return DM_Tipo_Origem;
  }

  public double getVL_Saldo () {
    return VL_Saldo;
  }

  public void setOid_Carteira (int oid_Carteira) {
    this.oid_Carteira = oid_Carteira;
  }

  public void setOID_Lote_Faturamento (long OID_Lote_Faturamento) {
    this.OID_Lote_Faturamento = OID_Lote_Faturamento;
  }

  public void setDM_Tipo_Origem (String DM_Tipo_Origem) {
    this.DM_Tipo_Origem = DM_Tipo_Origem;
  }

  public void setVL_Saldo (double VL_Saldo) {
    this.VL_Saldo = VL_Saldo;
  }

public String getDM_Pode_Estornar() {
	return DM_Pode_Estornar;
}

public void setDM_Pode_Estornar(String pode_Estornar) {
	DM_Pode_Estornar = pode_Estornar;
}

public long getNR_Nota_Fiscal() {
	return NR_Nota_Fiscal;
}

public void setNR_Nota_Fiscal(long nota_Fiscal) {
	NR_Nota_Fiscal = nota_Fiscal;
}
}