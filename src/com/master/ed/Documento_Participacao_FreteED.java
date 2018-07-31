package com.master.ed;

public class Documento_Participacao_FreteED extends MasterED{

  private String OID_Conhecimento;
  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String DT_Emissao;
  private long NR_Conhecimento;
  private long OID_Unidade;
  private String CD_Unidade;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private long NR_Participacao_Frete;
  private String OID_Documento_Participacao_Frete;
  private double VL_Total_Frete;
  private long NR_Conhecimento_Inicial;
  private long NR_Conhecimento_Final;
  private int OID_Subregiao;
  private long OID_Unidade_Entregadora;
  private String OID_Pessoa_Entregadora;
  private String DT_Vencimento;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private long OID_Cidade;
  private long OID_Participacao_Frete;
  private String NR_Documento;
  private String DM_Tipo_Documento;
  private String OID_Ordem_Frete;
  private String OID_Processo;
  private double VL_Faturado;
  private double VL_Comissao;
  private double PE_Comissao;
  private double VL_Frete_Minimo;

  private String OID_Ordem_Frete_Terceiro;
  private long OID_Modal;
  public void setOID_Conhecimento(String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }
  public String getOID_Conhecimento() {
    return OID_Conhecimento;
  }
  public void setOID_Documento_Participacao_Frete(String OID_Documento_Participacao_Frete) {
    this.OID_Documento_Participacao_Frete = OID_Documento_Participacao_Frete;
  }
  public String getOID_Documento_Participacao_Frete() {
    return OID_Documento_Participacao_Frete;
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
  public void setOID_Participacao_Frete(long OID_Participacao_Frete) {
    this.OID_Participacao_Frete = OID_Participacao_Frete;
  }
  public long getOID_Participacao_Frete() {
    return OID_Participacao_Frete;
  }
  public void setNR_Participacao_Frete(long NR_Participacao_Frete) {
    this.NR_Participacao_Frete = NR_Participacao_Frete;
  }
  public long getNR_Participacao_Frete() {
    return NR_Participacao_Frete;
  }
  public void setVL_Total_Frete(double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }
  public double getVL_Total_Frete() {
    return VL_Total_Frete;
  }
  public void setNR_Conhecimento_Inicial(long NR_Conhecimento_Inicial) {
    this.NR_Conhecimento_Inicial = NR_Conhecimento_Inicial;
  }
  public long getNR_Conhecimento_Inicial() {
    return NR_Conhecimento_Inicial;
  }
  public void setNR_Conhecimento_Final(long NR_Conhecimento_Final) {
    this.NR_Conhecimento_Final = NR_Conhecimento_Final;
  }
  public long getNR_Conhecimento_Final() {
    return NR_Conhecimento_Final;
  }
  public void setOID_Subregiao(int OID_Subregiao) {
    this.OID_Subregiao = OID_Subregiao;
  }
  public int getOID_Subregiao() {
    return OID_Subregiao;
  }
  public void setOID_Unidade_Entregadora(long OID_Unidade_Entregadora) {
    this.OID_Unidade_Entregadora = OID_Unidade_Entregadora;
  }
  public long getOID_Unidade_Entregadora() {
    return OID_Unidade_Entregadora;
  }
  public void setOID_Pessoa_Entregadora(String OID_Pessoa_Entregadora) {
    this.OID_Pessoa_Entregadora = OID_Pessoa_Entregadora;
  }
  public String getOID_Pessoa_Entregadora() {
    return OID_Pessoa_Entregadora;
  }
  public void setDT_Vencimento(String DT_Vencimento) {
    this.DT_Vencimento = DT_Vencimento;
  }
  public String getDT_Vencimento() {
    return DT_Vencimento;
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

  public void setNR_Documento(String NR_Documento) {
    this.NR_Documento = NR_Documento;
  }
  public String getNR_Documento() {
    return NR_Documento;
  }
  public void setDM_Tipo_Documento(String DM_Tipo_Documento) {
    this.DM_Tipo_Documento = DM_Tipo_Documento;
  }
  public String getDM_Tipo_Documento() {
    return DM_Tipo_Documento;
  }

  public void setOID_Ordem_Frete(String OID_Ordem_Frete) {
    this.OID_Ordem_Frete = OID_Ordem_Frete;
  }
  public String getOID_Ordem_Frete() {
    return OID_Ordem_Frete;
  }

  public void setOID_Processo (String OID_Processo) {

    this.OID_Processo = OID_Processo;
  }

  public String getOID_Processo () {

    return OID_Processo;
  }

  public void setVL_Faturado (double VL_Faturado) {
    this.VL_Faturado = VL_Faturado;
  }

  public double getVL_Faturado () {
    return VL_Faturado;
  }

  public void setOID_Ordem_Frete_Terceiro (String OID_Ordem_Frete_Terceiro) {
    this.OID_Ordem_Frete_Terceiro = OID_Ordem_Frete_Terceiro;
  }

  public String getOID_Ordem_Frete_Terceiro () {
    return OID_Ordem_Frete_Terceiro;
  }

  public void setOID_Modal (long OID_Modal) {
    this.OID_Modal = OID_Modal;
  }

  public long getOID_Modal () {
    return OID_Modal;
  }
public double getPE_Comissao() {
	return PE_Comissao;
}
public void setPE_Comissao(double comissao) {
	PE_Comissao = comissao;
}
public long getOID_Cidade() {
	return OID_Cidade;
}
public void setOID_Cidade(long cidade) {
	OID_Cidade = cidade;
}
public double getVL_Comissao() {
	return VL_Comissao;
}
public void setVL_Comissao(double comissao) {
	VL_Comissao = comissao;
}
public double getVL_Frete_Minimo() {
	return VL_Frete_Minimo;
}
public void setVL_Frete_Minimo(double frete_Minimo) {
	VL_Frete_Minimo = frete_Minimo;
}

}
