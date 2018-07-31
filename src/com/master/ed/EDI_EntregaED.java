package com.master.ed;

public class EDI_EntregaED extends MasterED{


  private String OID_EDI_Entrega;
  private String NR_Entrega;
  private String DM_Situacao;
  private String DT_Entrega;
  private String OID_Pessoa_Entregadora;
  private String HR_Entrega;
  private double VL_Total_Frete;
  private String NM_Tipo_Ocorrencia;
  private String DT_Entrega_EDI;
  private String HR_Entrega_EDI;
  private String DT_Entrega_Inicial;
  private String DT_Entrega_Final; 
  private long OID_Padrao_EDI;
  private String NR_Conhecimento;
  private String OID_Conhecimento;
  private double VL_Entrega;
  private long OID_Unidade;
  private String NR_Lote;
  private String NM_Pessoa_Entrega;
  private String DT_Entrada;
  private String DM_Relatorio;
  private String TX_Observacao;
  private String CD_Estado;
  private String DM_Tipo_Servico;
  private String DM_Tem_Conhecimento;
  private long OID_Tipo_Ocorrencia;
  private String NM_Arquivo;
  private String DM_Tipo_Entrega;
  private long NR_Nota_Fiscal;
  private String CD_Tipo_Ocorrencia;
  private String NM_Situacao;
  private String OID_Pessoa;
  public void setOID_EDI_Entrega(String OID_EDI_Entrega) {
    this.OID_EDI_Entrega = OID_EDI_Entrega;
  }
  public String getOID_EDI_Entrega() {
    return OID_EDI_Entrega;
  }
  public void setNR_Entrega (String NR_Entrega) {

    this.NR_Entrega = NR_Entrega;
  }
  public String getNR_Entrega () {

    return NR_Entrega;
  }

  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }

  public void setDT_Entrega(String DT_Entrega) {
    this.DT_Entrega = DT_Entrega;
  }
  public String getDT_Entrega() {
    return DT_Entrega;
  }
  public void setOID_Pessoa_Entregadora(String OID_Pessoa_Entregadora) {
    this.OID_Pessoa_Entregadora = OID_Pessoa_Entregadora;
  }
  public String getOID_Pessoa_Entregadora() {
    return OID_Pessoa_Entregadora;
  }

  public void setHR_Entrega(String HR_Entrega) {
    this.HR_Entrega = HR_Entrega;
  }
  public String getHR_Entrega() {
    return HR_Entrega;
  }

  public String getDT_Entrega_Final() {
    return DT_Entrega_Final;
  }
  public String getDT_Entrega_Inicial() {
    return DT_Entrega_Inicial;
  }
  public void setDT_Entrega_Final(String DT_Entrega_Final) {
    this.DT_Entrega_Final = DT_Entrega_Final;
  }
  public void setDT_Entrega_Inicial(String DT_Entrega_Inicial) {
    this.DT_Entrega_Inicial = DT_Entrega_Inicial;
  }

  public String getNM_Tipo_Ocorrencia() {
    return NM_Tipo_Ocorrencia;
  }

  public void setNM_Tipo_Ocorrencia(String NM_Tipo_Ocorrencia) {
    this.NM_Tipo_Ocorrencia = NM_Tipo_Ocorrencia;
  }

  public void setOID_Unidade (long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }

  public long getOID_Unidade () {
    return OID_Unidade;
  }

  public void setOID_Padrao_EDI(long OID_Padrao_EDI) {
    this.OID_Padrao_EDI = OID_Padrao_EDI;
  }
  public long getOID_Padrao_EDI() {
    return OID_Padrao_EDI;
  }

  public void setNR_Conhecimento(String NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }
  public String getNR_Conhecimento() {
    return NR_Conhecimento;
  }
  public void setOID_Conhecimento(String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }
  public String getOID_Conhecimento() {
    return OID_Conhecimento;
  }

  public void setVL_Entrega(double VL_Entrega) {
    this.VL_Entrega = VL_Entrega;
  }
  public double getVL_Entrega() {
    return VL_Entrega;
  }
  public void setVL_Total_Frete(double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }
  public double getVL_Total_Frete() {
    return VL_Total_Frete;
  }

  public void setNR_Lote (String NR_Lote) {
    this.NR_Lote = NR_Lote;
  }

  public String getNR_Lote () {
    return NR_Lote;
  }

  public void setNM_Pessoa_Entrega (String NM_Pessoa_Entrega) {
    this.NM_Pessoa_Entrega = NM_Pessoa_Entrega;
  }

  public String getNM_Pessoa_Entrega () {
    return NM_Pessoa_Entrega;
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

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
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

  public void setOID_Tipo_Ocorrencia (long OID_Tipo_Ocorrencia) {
    this.OID_Tipo_Ocorrencia = OID_Tipo_Ocorrencia;
  }

  public long getOID_Tipo_Ocorrencia () {
    return OID_Tipo_Ocorrencia;
  }

  public void setNM_Arquivo (String NM_Arquivo) {
    this.NM_Arquivo = NM_Arquivo;
  }

  public String getNM_Arquivo () {
    return NM_Arquivo;
  }

  public void setDM_Tipo_Entrega (String DM_Tipo_Entrega) {

    this.DM_Tipo_Entrega = DM_Tipo_Entrega;
  }

  public String getDM_Tipo_Entrega () {

    return DM_Tipo_Entrega;
  }

  public void setNR_Nota_Fiscal (long NR_Nota_Fiscal) {
    this.NR_Nota_Fiscal = NR_Nota_Fiscal;
  }

  public long getNR_Nota_Fiscal () {
    return NR_Nota_Fiscal;
  }

  public void setCD_Tipo_Ocorrencia (String CD_Tipo_Ocorrencia) {
    this.CD_Tipo_Ocorrencia = CD_Tipo_Ocorrencia;
  }

  public String getCD_Tipo_Ocorrencia () {
    return CD_Tipo_Ocorrencia;
  }

  public void setNM_Situacao (String NM_Situacao) {
    this.NM_Situacao = NM_Situacao;
  }

  public String getNM_Situacao () {
    return NM_Situacao;
  }

  public void setOID_Pessoa (String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }
public String getDM_Tem_Conhecimento() {
	return DM_Tem_Conhecimento;
}
public void setDM_Tem_Conhecimento(
		String tem_Conhecimento_ou_Nota_Fiscal) {
	DM_Tem_Conhecimento = tem_Conhecimento_ou_Nota_Fiscal;
}
public String getDT_Entrega_EDI() {
	return DT_Entrega_EDI;
}
public void setDT_Entrega_EDI(String entrega_EDI) {
	DT_Entrega_EDI = entrega_EDI;
}
public String getHR_Entrega_EDI() {
	return HR_Entrega_EDI;
}
public void setHR_Entrega_EDI(String entrega_EDI) {
	HR_Entrega_EDI = entrega_EDI;
}

}
