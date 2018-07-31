package com.master.ed;

public class Ocorrencia_ConhecimentoED extends MasterED{
  public Ocorrencia_ConhecimentoED () {
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

  private String OID_Conhecimento;
  private String TX_Observacao;
  private String DT_Ocorrencia_Conhecimento;
  private String HR_Ocorrencia_Conhecimento;
  private long OID_Ocorrencia_Conhecimento;
  private long OID_Tipo_Ocorrencia;
  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String OID_Pessoa_Consignatario;
  private String DT_Emissao;
  private String DT_Emissao_Final;
  private long NR_Conhecimento;
  private long NR_Nota_Fiscal;
  private long OID_Unidade;
  private long OID_Usuario;
  private String NM_Tipo_Ocorrencia;
  private String CD_Unidade;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private String DM_Tipo;
  private String DM_Acesso;
  private String DM_Avaria;
  private String DM_Status;
  private String NM_Pessoa_Entrega;
  private String NM_Observacao;
  private String OID_Pessoa_Redespacho;
  private String OID_Comprovante_Entrega;
  private long NR_Postagem;
  private String DM_Tipo_Postagem;
  private double VL_Avaria;

  private String OID_Pessoa_Entregadora;

  public String getOID_Pessoa_Entregadora() {
	return OID_Pessoa_Entregadora;
}
public void setOID_Pessoa_Entregadora(String pessoa_Entregadora) {
	OID_Pessoa_Entregadora = pessoa_Entregadora;
}
public void setOID_Conhecimento(String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }
  public String getOID_Conhecimento() {
    return OID_Conhecimento;
  }
  public void setTX_Observacao(String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }
  public String getTX_Observacao() {
    return TX_Observacao;
  }
  public void setOID_Ocorrencia_Conhecimento(long OID_Ocorrencia_Conhecimento) {
    this.OID_Ocorrencia_Conhecimento = OID_Ocorrencia_Conhecimento;
  }
  public long getOID_Ocorrencia_Conhecimento() {
    return OID_Ocorrencia_Conhecimento;
  }
  public void setDT_Ocorrencia_Conhecimento(String DT_Ocorrencia_Conhecimento) {
    this.DT_Ocorrencia_Conhecimento = DT_Ocorrencia_Conhecimento;
  }
  public String getDT_Ocorrencia_Conhecimento() {
    return DT_Ocorrencia_Conhecimento;
  }
  public void setHR_Ocorrencia_Conhecimento(String HR_Ocorrencia_Conhecimento) {
    this.HR_Ocorrencia_Conhecimento = HR_Ocorrencia_Conhecimento;
  }
  public String getHR_Ocorrencia_Conhecimento() {
    return HR_Ocorrencia_Conhecimento;
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
  public void setNR_Conhecimento(long NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }
  public long getNR_Conhecimento() {
    return NR_Conhecimento;
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
  public void setDM_Tipo(String DM_Tipo) {
    this.DM_Tipo = DM_Tipo;
  }
  public String getDM_Tipo() {
    return DM_Tipo;
  }
  public void setDM_Acesso(String DM_Acesso) {
    this.DM_Acesso = DM_Acesso;
  }
  public String getDM_Acesso() {
    return DM_Acesso;
  }
  public void setDM_Avaria(String DM_Avaria) {
    this.DM_Avaria = DM_Avaria;
  }
  public String getDM_Avaria() {
    return DM_Avaria;
  }
  public void setDM_Status(String DM_Status) {
    this.DM_Status = DM_Status;
  }
  public String getDM_Status() {
    return DM_Status;
  }
  public void setNM_Pessoa_Entrega(String NM_Pessoa_Entrega) {
    this.NM_Pessoa_Entrega = NM_Pessoa_Entrega;
  }
  public String getNM_Pessoa_Entrega() {
    return NM_Pessoa_Entrega;
  }
  public void setNM_Observacao(String NM_Observacao) {
    this.NM_Observacao = NM_Observacao;
  }
  public String getNM_Observacao() {
    return NM_Observacao;
  }

  public void setOID_Pessoa_Redespacho (String OID_Pessoa_Redespacho) {
    this.OID_Pessoa_Redespacho = OID_Pessoa_Redespacho;
  }

  public String getOID_Pessoa_Redespacho () {
    return OID_Pessoa_Redespacho;
  }

  private void jbInit () throws Exception {
  }

  public void setOID_Comprovante_Entrega (String OID_Comprovante_Entrega) {
    this.OID_Comprovante_Entrega = OID_Comprovante_Entrega;
  }

  public String getOID_Comprovante_Entrega () {
    return OID_Comprovante_Entrega;
  }

  public void setNR_Postagem (long NR_Postagem) {
    this.NR_Postagem = NR_Postagem;
  }

  public long getNR_Postagem () {
    return NR_Postagem;
  }

  public void setDM_Tipo_Postagem (String DM_Tipo_Postagem) {
    this.DM_Tipo_Postagem = DM_Tipo_Postagem;
  }

  public String getDM_Tipo_Postagem () {
    return DM_Tipo_Postagem;
  }
public long getOID_Usuario() {
	return OID_Usuario;
}
public void setOID_Usuario(long usuario) {
	OID_Usuario = usuario;
}
public double getVL_Avaria() {
	return VL_Avaria;
}
public void setVL_Avaria(double avaria) {
	VL_Avaria = avaria;
}
public String getDT_Emissao_Final() {
	return DT_Emissao_Final;
}
public void setDT_Emissao_Final(String emissao_Final) {
	DT_Emissao_Final = emissao_Final;
}
public long getNR_Nota_Fiscal() {
	return NR_Nota_Fiscal;
}
public void setNR_Nota_Fiscal(long nota_Fiscal) {
	NR_Nota_Fiscal = nota_Fiscal;
}

}