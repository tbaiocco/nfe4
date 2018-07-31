package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Fluxo_CaixaED
    extends MasterED {

  private String oid_Conta_Corrente;

  private String TX_Observacao;
  private Integer NR_Lote_Pagamento;
  private String oid_Pessoa;
  private Long oid_Unidade;
  private String NM_Razao_Social;
  private String CD_Unidade;
  private String NM_Fantasia;
  private String NR_Conta;
  private String NM_Favorecido;
  private String NR_Documento;
  private String CD_Tipo_Documento;
  private String NM_Tipo_Documento;
  private Integer OID_Tipo_Documento;
  private String NM_Razao_Social_Banco;
  private Integer NR_Compromisso;
  private String DT_Inicial;
  private String DT_Final;
  private Integer oid_Historico;
  private String DM_Entrada_Saida;
  private String DM_Tipo_Lancamento;
  private String DT_Fluxo;
  private String NM_Historico;
  private long oid_Fluxo_Caixa;
  private String CD_Historico;
  private long oid_modelo_Solicitacao;
  private String DM_Aceita_Contabilizacao;
  private String DM_Contabiliza;
  private String CD_Conta_Corrente;
  private double VL_Saldo_Inicial;
  private double VL_Saldo_Final;
  private String DM_Saldo_Inicial;
  private String DM_Situacao;
  private String DM_Fluxo;
  private String NM_Fluxo;
  private long oid_Compromisso;
  private long oid_Duplicata;
  private double VL_Fluxo;
  private long NR_Dias;
  private String NM_Tipo_Fluxo;
  private double VL_Saldo;
  private String DT_Vencimento;
  private String DM_Relatorio;
  private String NR_Conta_Corrente;
  private double VL_Entrada;
  private double VL_Saida;
  private String VL_E;
  private String VL_S;
  private long OID_Empresa;
  private long OID_Moeda;
  private String VL_I;
  private long OID_Usuario;
  private String DM_Tipo_Fluxo;
  private String NM_Conta_Corrente;
  private double VL_Cotacao_Padrao;
  private double VL_Cotacao;
  private long OID_Carteira;
  private String VL_SF;
  private double VL_Cotacao_Informada;
  private String DM_Atualiza_Saldo;
  private String DM_Pagamentos;
  private String DT_Saldo;
  private double VL_Limite;
  private double VL_Nao_Compensados;

  public Fluxo_CaixaED () {
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

  public Integer getNR_Lote_Pagamento () {
    return NR_Lote_Pagamento;
  }

  public String getNR_Documento () {
    return NR_Documento;
  }

  public long getOid_Fluxo_Caixa () {
    return oid_Fluxo_Caixa;
  }

  public String getOid_Pessoa () {
    return oid_Pessoa;
  }

  public Long getOid_Unidade () {
    return oid_Unidade;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public double getVL_Fluxo () {
    return VL_Fluxo;
  }

  public void setVL_Fluxo (double VL_Fluxo) {
    this.VL_Fluxo = VL_Fluxo;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public void setOid_Unidade (Long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }

  public void setOid_Pessoa (String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }

  public void setOid_Fluxo_Caixa (long oid_Fluxo_Caixa) {
    this.oid_Fluxo_Caixa = oid_Fluxo_Caixa;
  }

  public void setNR_Documento (String NR_Documento) {
    this.NR_Documento = NR_Documento;
  }

  public void setNR_Lote_Pagamento (Integer NR_Lote_Pagamento) {
    this.NR_Lote_Pagamento = NR_Lote_Pagamento;
  }

  public void setNM_Razao_Social (String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public void setCD_Unidade (String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }

  public String getCD_Unidade () {
    return CD_Unidade;
  }

  public void setNM_Fantasia (String NM_Fantasia) {
    this.NM_Fantasia = NM_Fantasia;
  }

  public String getNM_Fantasia () {
    return NM_Fantasia;
  }

  public void setNR_Conta (String NR_Conta) {
    this.NR_Conta = NR_Conta;
  }

  public String getNR_Conta () {
    return NR_Conta;
  }

  public void setNM_Favorecido (String NM_Favorecido) {
    this.NM_Favorecido = NM_Favorecido;
  }

  public String getNM_Favorecido () {
    return NM_Favorecido;
  }

  public void setCD_Tipo_Documento (String CD_Tipo_Documento) {
    this.CD_Tipo_Documento = CD_Tipo_Documento;
  }

  public String getCD_Tipo_Documento () {
    return CD_Tipo_Documento;
  }

  public void setNM_Tipo_Documento (String NM_Tipo_Documento) {
    this.NM_Tipo_Documento = NM_Tipo_Documento;
  }

  public String getNM_Tipo_Documento () {
    return NM_Tipo_Documento;
  }

  public void setOID_Tipo_Documento (Integer OID_Tipo_Documento) {
    this.OID_Tipo_Documento = OID_Tipo_Documento;
  }

  public Integer getOID_Tipo_Documento () {
    return OID_Tipo_Documento;
  }

  public void setNM_Razao_Social_Banco (String NM_Razao_Social_Banco) {
    this.NM_Razao_Social_Banco = NM_Razao_Social_Banco;
  }

  public String getNM_Razao_Social_Banco () {
    return NM_Razao_Social_Banco;
  }

  public void setNR_Compromisso (Integer NR_Compromisso) {
    this.NR_Compromisso = NR_Compromisso;
  }

  public Integer getNR_Compromisso () {
    return NR_Compromisso;
  }

  public void setDT_Inicial (String DT_Inicial) {
    this.DT_Inicial = DT_Inicial;
  }

  public String getDT_Inicial () {
    return DT_Inicial;
  }

  public void setDT_Final (String DT_Final) {
    this.DT_Final = DT_Final;
  }

  public String getDT_Final () {
    return DT_Final;
  }

  public void setOid_Historico (Integer oid_Historico) {
    this.oid_Historico = oid_Historico;
  }

  public Integer getOid_Historico () {
    return oid_Historico;
  }

  public void setDM_Entrada_Saida (String DM_Entrada_Saida) {
    this.DM_Entrada_Saida = DM_Entrada_Saida;
  }

  public String getDM_Entrada_Saida () {
    return DM_Entrada_Saida;
  }

  public void setDM_Tipo_Lancamento (String DM_Tipo_Lancamento) {
    this.DM_Tipo_Lancamento = DM_Tipo_Lancamento;
  }

  public String getDM_Tipo_Lancamento () {
    return DM_Tipo_Lancamento;
  }

  public void setDT_Fluxo (String DT_Fluxo) {
    this.DT_Fluxo = DT_Fluxo;
  }

  public String getDT_Fluxo () {
    return DT_Fluxo;
  }

  public void setNM_Historico (String NM_Historico) {
    this.NM_Historico = NM_Historico;
  }

  public String getNM_Historico () {
    return NM_Historico;
  }

  public void setCD_Historico (String CD_Historico) {
    this.CD_Historico = CD_Historico;
  }

  public String getCD_Historico () {
    return CD_Historico;
  }

  public void setOid_modelo_Solicitacao (long oid_modelo_Solicitacao) {
    this.oid_modelo_Solicitacao = oid_modelo_Solicitacao;
  }

  public long getOid_modelo_Solicitacao () {
    return oid_modelo_Solicitacao;
  }

  public void setDM_Aceita_Contabilizacao (String DM_Aceita_Contabilizacao) {
    this.DM_Aceita_Contabilizacao = DM_Aceita_Contabilizacao;
  }

  public String getDM_Aceita_Contabilizacao () {
    return DM_Aceita_Contabilizacao;
  }

  public void setDM_Contabiliza (String DM_Contabiliza) {
    this.DM_Contabiliza = DM_Contabiliza;
  }

  public String getDM_Contabiliza () {
    return DM_Contabiliza;
  }

  public String getOid_Conta_Corrente () {
    return oid_Conta_Corrente;
  }

  public void setOid_Conta_Corrente (String oid_Conta_Corrente) {
    this.oid_Conta_Corrente = oid_Conta_Corrente;
  }

  public void setCD_Conta_Corrente (String CD_Conta_Corrente) {
    this.CD_Conta_Corrente = CD_Conta_Corrente;
  }

  public String getCD_Conta_Corrente () {
    return CD_Conta_Corrente;
  }

  public void setVL_Saldo_Inicial (double VL_Saldo_Inicial) {
    this.VL_Saldo_Inicial = VL_Saldo_Inicial;
  }

  public double getVL_Saldo_Inicial () {
    return VL_Saldo_Inicial;
  }

  public void setVL_Saldo_Final (double VL_Saldo_Final) {
    this.VL_Saldo_Final = VL_Saldo_Final;
  }

  public double getVL_Saldo_Final () {
    return VL_Saldo_Final;
  }

  public void setDM_Saldo_Inicial (String DM_Saldo_Inicial) {
    this.DM_Saldo_Inicial = DM_Saldo_Inicial;
  }

  public String getDM_Saldo_Inicial () {
    return DM_Saldo_Inicial;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setOid_Compromisso (long oid_Compromisso) {
    this.oid_Compromisso = oid_Compromisso;
  }

  public long getOid_Compromisso () {
    return oid_Compromisso;
  }

  public long getOid_Duplicata () {
    return oid_Duplicata;
  }

  public double getVL_Nao_Compensados () {
    return VL_Nao_Compensados;
  }

  public double getVL_Limite () {
    return VL_Limite;
  }

  public String getDT_Saldo () {
    return DT_Saldo;
  }

  public String getDM_Pagamentos () {
    return DM_Pagamentos;
  }

  public String getDM_Atualiza_Saldo () {
    return DM_Atualiza_Saldo;
  }

  public double getVL_Cotacao_Informada () {
    return VL_Cotacao_Informada;
  }

  public String getVL_SF () {
    return VL_SF;
  }

  public long getOID_Carteira () {
    return OID_Carteira;
  }

  public double getVL_Cotacao () {
    return VL_Cotacao;
  }

  public double getVL_Cotacao_Padrao () {
    return VL_Cotacao_Padrao;
  }

  public String getNM_Conta_Corrente () {
    return NM_Conta_Corrente;
  }

  public String getDM_Tipo_Fluxo () {
    return DM_Tipo_Fluxo;
  }

  public long getOID_Usuario () {
    return OID_Usuario;
  }

  public String getVL_I () {
    return VL_I;
  }

  public long getOID_Moeda () {
    return OID_Moeda;
  }

  public long getOID_Empresa () {
    return OID_Empresa;
  }

  public String getVL_S () {
    return VL_S;
  }

  public String getVL_E () {
    return VL_E;
  }

  public double getVL_Saida () {
    return VL_Saida;
  }

  public double getVL_Entrada () {
    return VL_Entrada;
  }

  public String getNR_Conta_Corrente () {
    return NR_Conta_Corrente;
  }

  public void setOid_Duplicata (long oid_Duplicata) {
    this.oid_Duplicata = oid_Duplicata;
  }

  public void setVL_Nao_Compensados (double VL_Nao_Compensados) {
    this.VL_Nao_Compensados = VL_Nao_Compensados;
  }

  public void setVL_Limite (double VL_Limite) {
    this.VL_Limite = VL_Limite;
  }

  public void setDT_Saldo (String DT_Saldo) {
    this.DT_Saldo = DT_Saldo;
  }

  public void setDM_Pagamentos (String DM_Pagamentos) {
    this.DM_Pagamentos = DM_Pagamentos;
  }

  public void setDM_Atualiza_Saldo (String DM_Atualiza_Saldo) {
    this.DM_Atualiza_Saldo = DM_Atualiza_Saldo;
  }

  public void setVL_Cotacao_Informada (double VL_Cotacao_Informada) {
    this.VL_Cotacao_Informada = VL_Cotacao_Informada;
  }

  public void setVL_SF (String VL_SF) {
    this.VL_SF = VL_SF;
  }

  public void setOID_Carteira (long OID_Carteira) {
    this.OID_Carteira = OID_Carteira;
  }

  public void setVL_Cotacao (double VL_Cotacao) {
    this.VL_Cotacao = VL_Cotacao;
  }

  public void setVL_Cotacao_Padrao (double VL_Cotacao_Padrao) {
    this.VL_Cotacao_Padrao = VL_Cotacao_Padrao;
  }

  public void setNM_Conta_Corrente (String NM_Conta_Corrente) {
    this.NM_Conta_Corrente = NM_Conta_Corrente;
  }

  public void setDM_Tipo_Fluxo (String DM_Tipo_Fluxo) {
    this.DM_Tipo_Fluxo = DM_Tipo_Fluxo;
  }

  public void setOID_Usuario (long OID_Usuario) {
    this.OID_Usuario = OID_Usuario;
  }

  public void setVL_I (String VL_I) {
    this.VL_I = VL_I;
  }

  public void setOID_Moeda (long OID_Moeda) {
    this.OID_Moeda = OID_Moeda;
  }

  public void setOID_Empresa (long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }

  public void setVL_S (String VL_S) {
    this.VL_S = VL_S;
  }

  public void setVL_E (String VL_E) {
    this.VL_E = VL_E;
  }

  public void setVL_Saida (double VL_Saida) {
    this.VL_Saida = VL_Saida;
  }

  public void setVL_Entrada (double VL_Entrada) {
    this.VL_Entrada = VL_Entrada;
  }

  public void setNR_Conta_Corrente (String NR_Conta_Corrente) {
    this.NR_Conta_Corrente = NR_Conta_Corrente;
  }

  public void setNM_Fluxo (String NM_Fluxo) {
    this.NM_Fluxo = NM_Fluxo;
  }

  public String getNM_Fluxo () {
    return NM_Fluxo;
  }

  public void setDM_Fluxo (String DM_Fluxo) {
    this.DM_Fluxo = DM_Fluxo;
  }

  public String getDM_Fluxo () {
    return DM_Fluxo;
  }

  public void setNR_Dias (long NR_Dias) {
    this.NR_Dias = NR_Dias;
  }

  public long getNR_Dias () {
    return NR_Dias;
  }

  public void setNM_Tipo_Fluxo (String NM_Tipo_Fluxo) {
    this.NM_Tipo_Fluxo = NM_Tipo_Fluxo;
  }

  public String getNM_Tipo_Fluxo () {
    return NM_Tipo_Fluxo;
  }

  public void setVL_Saldo (double VL_Saldo) {
    this.VL_Saldo = VL_Saldo;
  }

  public double getVL_Saldo () {
    return VL_Saldo;
  }

  public void setDT_Vencimento (String DT_Vencimento) {
    this.DT_Vencimento = DT_Vencimento;
  }

  public String getDT_Vencimento () {
    return DT_Vencimento;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  private void jbInit () throws Exception {
  }
}
