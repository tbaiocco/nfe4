package com.master.ed;

/**
 * <p>Title: Lancamento Contabil </p>
 * <p>Description: Ao entrar com a nota fiscal de compra de produto/serviços,
 * o sistema gerará automaticamente os lancamentos contábeis através do modelo contábil escolhido pelo usuário.</p>
 * <p>Copyright: Delta Guia Copyright (c) 2002</p>
 * <p>Company: Exito/Delta Guia</p>
 * @author Claudia Galmarini Welter
 * @version 1.0 
 */

public class Lancamento_ContabilED extends MasterED implements java.io.Serializable{
  private long OID_Lancamento;
  private long OID_Conta; 
  private String OID_Nota_Fiscal;
  private String OID_Nota_Fiscal_Servico;
  private String OID_Conhecimento;
  private double VL_Lancamento;
  private String DT_Pagamento;
  private String DT_Stamp;
  private String Usuario_Stamp;
  private String DM_Stamp;
  private String DM_Acao;
  private String CD_Conta;
  private long NR_Lote;
  private String Tx_Chave_Origem;
  private long OID_Origem;
  private String NR_Nota_Fiscal;
  private String NR_Conhecimento;
  private String NR_Ordem_Frete;
  private String NM_Conta;
  private String NM_Historico;
  private String NM_Complementar;
  private long CD_Historico;
  private long OID_Unidade_Contabil;
  private String CD_Unidade_Contabil;
  private long OID_Historico;
  private String DT_Lancamento;
  private long OID_Compromisso;
  private String NR_Compromisso;
  private String OID_Solicitacao;
  private long NR_Solicitacao;
  private String OID_Acerto;
  private String DM_Tipo_Documento;
  private String OID_Movimento_Servico;
  private String OID_Ordem_Frete;
  private String OID_Caixa;
  private long OID_Lote_Pagamento;
  private long OID_Lote_Pagamento_Compensacao;
  private long oid_fatura_compromisso;
  private String oid_movimento_duplicata;
  private String OID_Lote_Compromisso;
  private String nr_duplicata;

  private long OID_Lote_Posto;
  private long OID_Movimento_Conta_Corrente;
  private String DT_Lancamento_Inicial;
  private String DT_Lancamento_Final;
  private long OID_Conta_Zera_Resultado;

  public void setOID_Caixa(String OID_Caixa) {
    this.OID_Caixa = OID_Caixa;
  }
  public String getOID_Caixa() {
    return OID_Caixa;
  }

  public void setDM_Acao(String DM_Acao) {
    this.DM_Acao = DM_Acao;
  }
  public void setDM_Stamp(String DM_Stamp) {
    this.DM_Stamp = DM_Stamp;
  }
  public void setDT_Pagamento(String DT_Pagamento) {
    this.DT_Pagamento = DT_Pagamento;
  }
  public void setDT_Stamp(String DT_Stamp) {
    this.DT_Stamp = DT_Stamp;
  }
  public void setOID_Conta(long OID_Conta) {
    this.OID_Conta = OID_Conta;
  }
  public void setOID_Lancamento(long OID_Lancamento) {
    this.OID_Lancamento = OID_Lancamento;
  }
  public void setOID_Nota_Fiscal(String OID_Nota_Fiscal) {
    this.OID_Nota_Fiscal = OID_Nota_Fiscal;
  }
  public void setUsuario_Stamp(String Usuario_Stamp) {
    this.Usuario_Stamp = Usuario_Stamp;
  }
  public void setVL_Lancamento(double VL_Lancamento) {
    this.VL_Lancamento = VL_Lancamento;
  }
  public String getDM_Acao() {
    return DM_Acao;
  }
  public String getDM_Stamp() {
    return DM_Stamp;
  }
  public String getDT_Pagamento() {
    return DT_Pagamento;
  }
  public String getDT_Stamp() {
    return DT_Stamp;
  }
  public long getOID_Conta() {
    return OID_Conta;
  }
  public long getOID_Lancamento() {
    return OID_Lancamento;
  }
  public String getOID_Nota_Fiscal() {
    return OID_Nota_Fiscal;
  }
  public String getUsuario_Stamp() {
    return Usuario_Stamp;
  }
  public double getVL_Lancamento() {
    return VL_Lancamento;
  }
  public void setCD_Conta(String CD_Conta) {
    this.CD_Conta = CD_Conta;
  }
  public String getCD_Conta() {
    return CD_Conta;
  }
  public void setNR_Nota_Fiscal(String NR_Nota_Fiscal) {
    this.NR_Nota_Fiscal = NR_Nota_Fiscal;
  }
  public String getNR_Nota_Fiscal() {
    return NR_Nota_Fiscal;
  }
  public void setNM_Conta(String NM_Conta) {
    this.NM_Conta = NM_Conta;
  }
  public String getNM_Conta() {
    return NM_Conta;
  }
  public void setNM_Historico(String NM_Historico) {
    this.NM_Historico = NM_Historico;
  }
  public String getNM_Historico() {
    return NM_Historico;
  }
  public void setNM_Complementar(String NM_Complementar) {
    this.NM_Complementar = NM_Complementar;
  }
  public String getNM_Complementar() {
    return NM_Complementar;
  }
  public void setCD_Historico(long CD_Historico) {
    this.CD_Historico = CD_Historico;
  }
  public long getCD_Historico() {
    return CD_Historico;
  }
  public void setOID_Unidade_Contabil(long OID_Unidade_Contabil) {
    this.OID_Unidade_Contabil = OID_Unidade_Contabil;
  }
  public long getOID_Unidade_Contabil() {
    return OID_Unidade_Contabil;
  }
  public void setCD_Unidade_Contabil(String CD_Unidade_Contabil) {
    this.CD_Unidade_Contabil = CD_Unidade_Contabil;
  }
  public String getCD_Unidade_Contabil() {
    return CD_Unidade_Contabil;
  }
  public void setOID_Historico(long OID_Historico) {
    this.OID_Historico = OID_Historico;
  }
  public long getOID_Historico() {
    return OID_Historico;
  }
  public void setDT_Lancamento(String DT_Lancamento) {
    this.DT_Lancamento = DT_Lancamento;
  }
  public String getDT_Lancamento() {
    return DT_Lancamento;
  }
  public void setOID_Compromisso(long OID_Compromisso) {
    this.OID_Compromisso = OID_Compromisso;
  }
  public long getOID_Compromisso() {
    return OID_Compromisso;
  }
  public void setNR_Compromisso(String NR_Compromisso) {
    this.NR_Compromisso = NR_Compromisso;
  }
  public String getNR_Compromisso() {
    return NR_Compromisso;
  }
  public void setOID_Solicitacao(String OID_Solicitacao) {
    this.OID_Solicitacao = OID_Solicitacao;
  }
  public String getOID_Solicitacao() {
    return OID_Solicitacao;
  }
  public void setNR_Solicitacao(long NR_Solicitacao) {
    this.NR_Solicitacao = NR_Solicitacao;
  }
  public long getNR_Solicitacao() {
    return NR_Solicitacao;
  }
  public void setOID_Acerto(String OID_Acerto) {
    this.OID_Acerto = OID_Acerto;
  }
  public String getOID_Acerto() {
    return OID_Acerto;
  }
  public void setDM_Tipo_Documento(String DM_Tipo_Documento) {
    this.DM_Tipo_Documento = DM_Tipo_Documento;
  }
  public String getDM_Tipo_Documento() {
    return DM_Tipo_Documento;
  }
  public void setOID_Movimento_Servico(String OID_Movimento_Servico) {
    this.OID_Movimento_Servico = OID_Movimento_Servico;
  }
  public String getOID_Movimento_Servico() {
    return OID_Movimento_Servico;
  }
  public void setOID_Ordem_Frete(String OID_Ordem_Frete) {
    this.OID_Ordem_Frete = OID_Ordem_Frete;
  }
  public String getOID_Ordem_Frete() {
    return OID_Ordem_Frete;
  }
  public void setOID_Lote_Pagamento(long OID_Lote_Pagamento) {
    this.OID_Lote_Pagamento = OID_Lote_Pagamento;
  }
  public long getOID_Lote_Pagamento() {
    return OID_Lote_Pagamento;
  }

  public String getOid_movimento_duplicata() {
    return oid_movimento_duplicata;
  }
  public void setOid_movimento_duplicata(String oid_movimento_duplicata) {
	this.oid_movimento_duplicata = oid_movimento_duplicata;
  }
  public String getNr_duplicata() {
	return nr_duplicata;
  }

  public long getOID_Conta_Zera_Resultado () {

    return OID_Conta_Zera_Resultado;
  }

  public String getDT_Lancamento_Final () {
    return DT_Lancamento_Final;
  }

  public String getDT_Lancamento_Inicial () {
    return DT_Lancamento_Inicial;
  }

  public void setNr_duplicata(String nr_duplicata) {
	this.nr_duplicata = nr_duplicata;
  }

  public void setOID_Conta_Zera_Resultado (long OID_Conta_Zera_Resultado) {

    this.OID_Conta_Zera_Resultado = OID_Conta_Zera_Resultado;
  }

  public void setDT_Lancamento_Final (String DT_Lancamento_Final) {
    this.DT_Lancamento_Final = DT_Lancamento_Final;
  }

  public void setDT_Lancamento_Inicial (String DT_Lancamento_Inicial) {
    this.DT_Lancamento_Inicial = DT_Lancamento_Inicial;
  }

  public long getOID_Lote_Posto() {
    return OID_Lote_Posto;
  } 
  public void setOID_Lote_Posto(long OID_Lote_Posto) {
    this.OID_Lote_Posto = OID_Lote_Posto;
  }
  public void setOID_Movimento_Conta_Corrente(long OID_Movimento_Conta_Corrente) {
    this.OID_Movimento_Conta_Corrente = OID_Movimento_Conta_Corrente;
  }
  public long getOID_Movimento_Conta_Corrente() {
    return OID_Movimento_Conta_Corrente;
  }

public String getOID_Lote_Compromisso() {
	return OID_Lote_Compromisso;
}
public void setOID_Lote_Compromisso(String lote_Compromisso) {
	OID_Lote_Compromisso = lote_Compromisso;
}
public String getOID_Conhecimento() {
	return OID_Conhecimento;
}
public void setOID_Conhecimento(String conhecimento) {
	OID_Conhecimento = conhecimento;
}
public String getNR_Conhecimento() {
	return NR_Conhecimento;
}
public void setNR_Conhecimento(String conhecimento) {
	NR_Conhecimento = conhecimento;
}
public String getNR_Ordem_Frete() {
	return NR_Ordem_Frete;
}
public void setNR_Ordem_Frete(String ordem_Frete) {
	NR_Ordem_Frete = ordem_Frete;
}
public long getNR_Lote() {
	return NR_Lote;
}
public void setNR_Lote(long lote) {
	NR_Lote = lote;
}
public long getOID_Origem() {
	return OID_Origem;
}
public void setOID_Origem(long origem) {
	OID_Origem = origem;
}
public String getTx_Chave_Origem() {
	return Tx_Chave_Origem;
}
public void setTx_Chave_Origem(String tx_Chave_Origem) {
	Tx_Chave_Origem = tx_Chave_Origem;
}
public long getOid_fatura_compromisso() {
	return oid_fatura_compromisso;
}
public void setOid_fatura_compromisso(long oid_fatura_compromisso) {
	this.oid_fatura_compromisso = oid_fatura_compromisso;
}
public long getOID_Lote_Pagamento_Compensacao() {
	return OID_Lote_Pagamento_Compensacao;
}
public void setOID_Lote_Pagamento_Compensacao(long lote_Pagamento_Compensacao) {
	OID_Lote_Pagamento_Compensacao = lote_Pagamento_Compensacao;
}
public String getOID_Nota_Fiscal_Servico() {
	return OID_Nota_Fiscal_Servico;
}
public void setOID_Nota_Fiscal_Servico(String nota_Fiscal_Servico) {
	OID_Nota_Fiscal_Servico = nota_Fiscal_Servico;
}
}
