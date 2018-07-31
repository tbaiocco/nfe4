package com.master.ed;

public class Participacao_FreteED extends MasterED{

  private String OID_Conhecimento;
  private String OID_Pessoa;
  private String DT_Emissao;
  private long NR_Conhecimento;
  private long OID_Unidade;
  private String CD_Unidade;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private long NR_Participacao_Frete;
  private String DT_Vencimento;
  private long NR_Duplicata;
  private String DM_Situacao;
  private String NM_Cidade_Unidade;
  private String DM_Tipo_Documento;
  private String TX_Observacao;
  private String NM_Pessoa_Entrega;
  private long OID_Participacao_Frete;
  private double VL_Cobranca;
  private double VL_Comissao;
  private double VL_Descontos;
  private double VL_Total_Frete;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String NM_Razao_Social;
  private long OID_Duplicata;
  private String DM_Documento;
  private String OID_Ordem_Frete_Terceiro;

  public void setOID_Conhecimento(String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }
  public String getOID_Conhecimento() {
    return OID_Conhecimento;
  }
  public void setOID_Participacao_Frete(long OID_Participacao_Frete) {
    this.OID_Participacao_Frete = OID_Participacao_Frete;
  }
  public long getOID_Participacao_Frete() {
    return OID_Participacao_Frete;
  }
  public void setOID_Pessoa(String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public String getOID_Pessoa() {
    return OID_Pessoa;
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
  public void setNM_Pessoa_Destinatario(String NM_Pessoa_Destinatario) {
    this.NM_Pessoa_Destinatario = NM_Pessoa_Destinatario;
  }
  public String getNM_Pessoa_Destinatario() {
    return NM_Pessoa_Destinatario;
  }
  public void setNR_Participacao_Frete(long NR_Participacao_Frete) {
    this.NR_Participacao_Frete = NR_Participacao_Frete;
  }
  public long getNR_Participacao_Frete() {
    return NR_Participacao_Frete;
  }
  public void setDT_Vencimento(String DT_Vencimento) {
    this.DT_Vencimento = DT_Vencimento;
  }
  public String getDT_Vencimento() {
    return DT_Vencimento;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public void setNR_Duplicata(long NR_Duplicata) {
    this.NR_Duplicata = NR_Duplicata;
  }
  public long getNR_Duplicata() {
    return NR_Duplicata;
  }
  public void setNM_Cidade_Unidade(String NM_Cidade_Unidade) {
    this.NM_Cidade_Unidade = NM_Cidade_Unidade;
  }
  public String getNM_Cidade_Unidade() {
    return NM_Cidade_Unidade;
  }
  public void setDM_Tipo_Documento(String DM_Tipo_Documento) {
    this.DM_Tipo_Documento = DM_Tipo_Documento;
  }
  public String getDM_Tipo_Documento() {
    return DM_Tipo_Documento;
  }
  public void setTX_Observacao(String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }
  public String getTX_Observacao() {
    return TX_Observacao;
  }
  public void setNM_Pessoa_Entrega(String NM_Pessoa_Entrega) {
    this.NM_Pessoa_Entrega = NM_Pessoa_Entrega;
  }
  public String getNM_Pessoa_Entrega() {
    return NM_Pessoa_Entrega;
  }
  public void setVL_Cobranca(double VL_Cobranca) {
    this.VL_Cobranca = VL_Cobranca;
  }
  public double getVL_Cobranca() {
    return VL_Cobranca;
  }
  public void setDT_Emissao_Inicial(String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }
  public String getDT_Emissao_Inicial() {
    return DT_Emissao_Inicial;
  }
  public void setDT_Emissao_Final(String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }
  public String getDT_Emissao_Final() {
    return DT_Emissao_Final;
  }
  public void setNM_Razao_Social(String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }
  public String getNM_Razao_Social() {
    return NM_Razao_Social;
  }

  public void setOID_Duplicata (long OID_Duplicata) {
    this.OID_Duplicata = OID_Duplicata;
  }

  public long getOID_Duplicata () {
    return OID_Duplicata;
  }

  public void setDM_Documento (String DM_Documento) {
    this.DM_Documento = DM_Documento;
  }

  public String getDM_Documento () {
    return DM_Documento;
  }

  public void setOID_Ordem_Frete_Terceiro (String OID_Ordem_Frete_Terceiro) {
    this.OID_Ordem_Frete_Terceiro = OID_Ordem_Frete_Terceiro;
  }

  public String getOID_Ordem_Frete_Terceiro () {
    return OID_Ordem_Frete_Terceiro;
  }
public double getVL_Descontos() {
	return VL_Descontos;
}
public void setVL_Descontos(double descontos) {
	VL_Descontos = descontos;
}
public double getVL_Total_Frete() {
	return VL_Total_Frete;
}
public void setVL_Total_Frete(double total_Frete) {
	VL_Total_Frete = total_Frete;
}
public double getVL_Comissao() {
	return VL_Comissao;
}
public void setVL_Comissao(double comissao) {
	VL_Comissao = comissao;
}

}
