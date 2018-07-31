package com.master.ed;
import com.master.root.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */


public class CompromissoED extends MasterED {

private Integer oid_Compromisso;
private String nr_Documento;
private Integer nr_Parcela;
private String dt_Emissao;
private String dt_Vencimento;
private Double vl_Compromisso = new Double (0);
private Double vl_Multa_Apos_Vencimento = new Double (0);
private Double vl_Juro_Mora_dia = new Double (0);
private String tx_Observacao;
private Integer nr_Compromisso;
private Integer nr_Compromisso_Final;
private Double vl_Desconto_Ate_Vencimento = new Double (0);
private Double vl_Saldo = new Double (0);
private Integer oid_Tipo_Documento;
private Integer oid_Conta;
private Integer oid_Grupo_Economico;
private Integer oid_Conta_Nova;
private Integer oid_Centro_Custo;
private String oid_Pessoa;
private Long oid_Unidade;
private String nr_CNPJ_CPF;
private String nm_Razao_Social;
private String cd_Tipo_Documento;
private String nm_Tipo_Documento;
private String cd_Centro_Custo;
private String nm_Centro_Custo;
private String cd_Conta;
private String nm_Conta;
private String cd_Unidade;
private String nm_Fantasia;
private String CD_Unidade_Pagamento;
private String NM_Unidade_Pagamento;
private String dm_Numeracao_Automatica;
private String nr_Proximo_Numero;
private String OID_Ordem_Frete;
private Integer OID_Movimento_Ordem_Servico;
private String DM_Lote_Pagamento;
private Double vl_Imposto = new Double (0);
private Double pe_Imposto = new Double (0);
private Integer oid_Natureza_Operacao;
private String dt_Entrada;
private String cd_Natureza_Operacao;
private String nm_Natureza_Operacao;
private Integer oid_Compromisso_Vinculado;
private String DM_Vinculador;
private Double VL_Compromissos_Vinculados = new Double (0);
private String oid_pessoa_Vinculado;
private String nr_cnpj_cpf_Vinculado;
private String nm_razao_Social_Vinculado;
private String cd_tipo_documento_Vinculado;
private String nm_tipo_documento_Vinculado;
private Integer nr_Compromisso_Vinculado;
private Double vl_saldo_Vinculado = new Double (0);
private String nr_documento_Vinculado;
private Integer nr_parcela_Vinculado;
private String Dt_Inicial;
private String Dt_Final;
private Integer oid_Compromisso_Parcela;
private String cd_Conta_Credito;
private String nm_Conta_Credito;
private Integer oid_Conta_Credito;
private Double vl_Taxa_Banco = new Double (0);
private String DM_Tipo_Documento;
private String DM_Tipo_Pagamento;
private String CD_Barra;

private int oid_Unidade_Pagamento;
private int oid_Moeda;
public MoedaBean edMoeda = new MoedaBean ();
private double VL_Cotacao;
private double VL_Cotacao_Padrao;
private String DT_Cotacao;
private double VL_Saldo_Atualizado;
private Integer NR_Dia_Provisao;
private Integer NR_Qtde_Provisao;
private String DM_Situacao;
private String DM_Debito_Credito;
private Double VL_Compromisso_Original = new Double (0);
private String dt_Vencimento_Inicial;
private String dt_Vencimento_Final;
private String DM_Relatorio;
private String dt_Pagamento_Inicial;
private String dt_Pagamento_Final;
private String dt_Entrada_Inicial;
private String dt_Entrada_Final;
private String dt_Liberado;
private String dt_Stamp;

private double VL_Debito;
private double VL_Credito;
private double VL_Saldo_Inicial;

private java.util.Collection DiarioDetalhes;
private String dataRel;
private String siglaRel;
private long OID_Usuario;
private long oid_Fatura_Compromisso;
private double VL_Custas;
private String DT_Cartorio;
private String DT_Vencimento_Original;
private String DM_Cartorio;
private int nr_Proxima_Parcela;
private int nr_Dias_Vencimento;
private int nr_Parcelas;
private long OID_Usuario_Aprovacao;
private String DT_Aprovacao;
private String DM_Aprovacao;
private String DM_Liberado;
private long OID_Usuario_Liberacao;
private String DM_Classificar;
private String NM_Favorecido;
private String NR_Identificacao_Favorecido;
private String CD_Banco_Favorecido;
private String NM_Banco_Favorecido;
private String NM_Agencia_Favorecido;
private String NR_Conta_Corrente_Favorecido;
private String DM_Agendamento;
private String NM_Titulo_Relatorio1;
private String NM_Titulo_Relatorio2;
private String NM_Titulo_Relatorio3;
private String NM_Titulo_Relatorio4;
private long oid_Lote_Pagamento;
private int oid_Empresa;

public CompromissoED () {
}

public CompromissoED (Integer compromisso) {
oid_Compromisso = compromisso;
}

public String getDescDM_Tipo_Pagamento () {
if ("1".equals (DM_Tipo_Pagamento)) {
  return "Boleto";
}
else if ("2".equals (DM_Tipo_Pagamento)) {
  return "Cheque";
}
else if ("3".equals (DM_Tipo_Pagamento)) {
  return "Depósito CC";
}
else if ("4".equals (DM_Tipo_Pagamento)) {
  return "Carteira";
}
else if ("5".equals (DM_Tipo_Pagamento)) {
  return "Dinheiro";
}
else if ("6".equals (DM_Tipo_Pagamento)) {
  return "Debito Conta";
}
else {
  return "Não informado!";
}
}

public static String getDescDM_Tipo_Pagamento (String dmTipo_Pagamento) {
if ("1".equals (dmTipo_Pagamento)) {
  return "Boleto";
}
else if ("2".equals (dmTipo_Pagamento)) {
  return "Cheque";
}
else if ("3".equals (dmTipo_Pagamento)) {
  return "Depósito CC";
}
else if ("4".equals (dmTipo_Pagamento)) {
  return "Carteira";
}
else if ("5".equals (dmTipo_Pagamento)) {
  return "Dinheiro";
}
else {
  return "Não informado!";
}
}

public String getDt_Emissao () {
return dt_Emissao;
}

public String getDt_Vencimento () {
return dt_Vencimento;
}

public Integer getNr_Compromisso () {
return nr_Compromisso;
}

public String getNr_Documento () {
return nr_Documento;
}

public Integer getNr_Parcela () {
return nr_Parcela;
}

public Integer getOid_Centro_Custo () {
return oid_Centro_Custo;
}

public Integer getOid_Compromisso () {
return oid_Compromisso;
}

public Integer getOid_Conta () {
return oid_Conta;
}

public String getOid_Pessoa () {
return oid_Pessoa;
}

public Integer getOid_Tipo_Documento () {
return oid_Tipo_Documento;
}

public Long getOid_Unidade () {
return oid_Unidade;
}

public String getTx_Observacao () {
return tx_Observacao;
}

public Double getVl_Compromisso () {
return vl_Compromisso;
}

public Double getVl_Desconto_Ate_Vencimento () {
return vl_Desconto_Ate_Vencimento;
}

public Double getVl_Juro_Mora_dia () {
return vl_Juro_Mora_dia;
}

public Double getVl_Multa_Apos_Vencimento () {
return vl_Multa_Apos_Vencimento;
}

public Double getVl_Saldo () {
return vl_Saldo;
}

public void setVl_Saldo (Double vl_Saldo) {
this.vl_Saldo = vl_Saldo;
}

public void setVl_Multa_Apos_Vencimento (Double vl_Multa_Apos_Vencimento) {
this.vl_Multa_Apos_Vencimento = vl_Multa_Apos_Vencimento;
}

public void setVl_Juro_Mora_dia (Double vl_Juro_Mora_dia) {
this.vl_Juro_Mora_dia = vl_Juro_Mora_dia;
}

public void setVl_Desconto_Ate_Vencimento (Double vl_Desconto_Ate_Vencimento) {
this.vl_Desconto_Ate_Vencimento = vl_Desconto_Ate_Vencimento;
}

public void setVl_Compromisso (Double vl_Compromisso) {
this.vl_Compromisso = vl_Compromisso;
}

public void setTx_Observacao (String tx_Observacao) {
this.tx_Observacao = tx_Observacao;
}

public void setOid_Unidade (Long oid_Unidade) {
this.oid_Unidade = oid_Unidade;
}

public void setOid_Tipo_Documento (Integer oid_Tipo_Documento) {
this.oid_Tipo_Documento = oid_Tipo_Documento;
}

public void setOid_Pessoa (String oid_Pessoa) {
this.oid_Pessoa = oid_Pessoa;
}

public void setOid_Conta (Integer oid_Conta) {
this.oid_Conta = oid_Conta;
}

public void setOid_Compromisso (Integer oid_Compromisso) {
this.oid_Compromisso = oid_Compromisso;
}

public void setOid_Centro_Custo (Integer oid_Centro_Custo) {
this.oid_Centro_Custo = oid_Centro_Custo;
}

public void setNr_Parcela (Integer nr_Parcela) {
this.nr_Parcela = nr_Parcela;
}

public void setNr_Documento (String nr_Documento) {
this.nr_Documento = nr_Documento;
}

public void setNr_Compromisso (Integer nr_Compromisso) {
this.nr_Compromisso = nr_Compromisso;
}

public void setDt_Vencimento (String dt_Vencimento) {
this.dt_Vencimento = dt_Vencimento;
}

public void setDt_Emissao (String dt_Emissao) {
this.dt_Emissao = dt_Emissao;
}

public void setNr_CNPJ_CPF (String nr_CNPJ_CPF) {
this.nr_CNPJ_CPF = nr_CNPJ_CPF;
}

public String getNr_CNPJ_CPF () {
return nr_CNPJ_CPF;
}

public void setNm_Razao_Social (String nm_Razao_Social) {
this.nm_Razao_Social = nm_Razao_Social;
}

public String getNm_Razao_Social () {
return nm_Razao_Social;
}

public void setCd_Tipo_Documento (String cd_Tipo_Documento) {
this.cd_Tipo_Documento = cd_Tipo_Documento;
}

public String getCd_Tipo_Documento () {
return cd_Tipo_Documento;
}

public void setNm_Tipo_Documento (String nm_Tipo_Documento) {
this.nm_Tipo_Documento = nm_Tipo_Documento;
}

public String getNm_Tipo_Documento () {
return nm_Tipo_Documento;
}

public void setCd_Centro_Custo (String cd_Centro_Custo) {
this.cd_Centro_Custo = cd_Centro_Custo;
}

public String getCd_Centro_Custo () {
return cd_Centro_Custo;
}

public void setNm_Centro_Custo (String nm_Centro_Custo) {
this.nm_Centro_Custo = nm_Centro_Custo;
}

public String getNm_Centro_Custo () {
return nm_Centro_Custo;
}

public void setCd_Conta (String cd_Conta) {
this.cd_Conta = cd_Conta;
}

public String getCd_Conta () {
return cd_Conta;
}

public void setNm_Conta (String nm_Conta) {
this.nm_Conta = nm_Conta;
}

public String getNm_Conta () {
return nm_Conta;
}

public void setCd_Unidade (String cd_Unidade) {
this.cd_Unidade = cd_Unidade;
}

public String getCd_Unidade () {
return cd_Unidade;
}

public void setNm_Fantasia (String nm_Fantasia) {
this.nm_Fantasia = nm_Fantasia;
}

public String getNm_Fantasia () {
return nm_Fantasia;
}

public void setDm_Numeracao_Automatica (String dm_Numeracao_Automatica) {
this.dm_Numeracao_Automatica = dm_Numeracao_Automatica;
}

public String getDm_Numeracao_Automatica () {
return dm_Numeracao_Automatica;
}

public void setNr_Proximo_Numero (String nr_Proximo_Numero) {
this.nr_Proximo_Numero = nr_Proximo_Numero;
}

public String getNr_Proximo_Numero () {
return nr_Proximo_Numero;
}

public void setOID_Ordem_Frete (String OID_Ordem_Frete) {
this.OID_Ordem_Frete = OID_Ordem_Frete;
}

public String getOID_Ordem_Frete () {
return OID_Ordem_Frete;
}

public void setOID_Movimento_Ordem_Servico (Integer OID_Movimento_Ordem_Servico) {
this.OID_Movimento_Ordem_Servico = OID_Movimento_Ordem_Servico;
}

public Integer getOID_Movimento_Ordem_Servico () {
return OID_Movimento_Ordem_Servico;
}

public void setDM_Lote_Pagamento (String DM_Lote_Pagamento) {
this.DM_Lote_Pagamento = DM_Lote_Pagamento;
}

public String getDM_Lote_Pagamento () {
return DM_Lote_Pagamento;
}

public void setVl_Imposto (Double vl_Imposto) {
this.vl_Imposto = vl_Imposto;
}

public Double getVl_Imposto () {
return vl_Imposto;
}

public void setPe_Imposto (Double pe_Imposto) {
this.pe_Imposto = pe_Imposto;
}

public Double getPe_Imposto () {
return pe_Imposto;
}

public void setOid_Natureza_Operacao (Integer oid_Natureza_Operacao) {
this.oid_Natureza_Operacao = oid_Natureza_Operacao;
}

public Integer getOid_Natureza_Operacao () {
return oid_Natureza_Operacao;
}

public void setDt_Entrada (String dt_Entrada) {
this.dt_Entrada = dt_Entrada;
}

public String getDt_Entrada () {
return dt_Entrada;
}

public void setCd_Natureza_Operacao (String cd_Natureza_Operacao) {
this.cd_Natureza_Operacao = cd_Natureza_Operacao;
}

public String getCd_Natureza_Operacao () {
return cd_Natureza_Operacao;
}

public void setNm_Natureza_Operacao (String nm_Natureza_Operacao) {
this.nm_Natureza_Operacao = nm_Natureza_Operacao;
}

public String getNm_Natureza_Operacao () {
return nm_Natureza_Operacao;
}

public void setOid_Compromisso_Vinculado (Integer oid_Compromisso_Vinculado) {
this.oid_Compromisso_Vinculado = oid_Compromisso_Vinculado;
}

public Integer getOid_Compromisso_Vinculado () {
return oid_Compromisso_Vinculado;
}

public void setDM_Vinculador (String DM_Vinculador) {
this.DM_Vinculador = DM_Vinculador;
}

public String getDM_Vinculador () {
return DM_Vinculador;
}

public void setVL_Compromissos_Vinculados (Double VL_Compromissos_Vinculados) {
this.VL_Compromissos_Vinculados = VL_Compromissos_Vinculados;
}

public Double getVL_Compromissos_Vinculados () {
return VL_Compromissos_Vinculados;
}

public void setOid_pessoa_Vinculado (String oid_pessoa_Vinculado) {
this.oid_pessoa_Vinculado = oid_pessoa_Vinculado;
}

public String getOid_pessoa_Vinculado () {
return oid_pessoa_Vinculado;
}

public void setNr_cnpj_cpf_Vinculado (String nr_cnpj_cpf_Vinculado) {
this.nr_cnpj_cpf_Vinculado = nr_cnpj_cpf_Vinculado;
}

public String getNr_cnpj_cpf_Vinculado () {
return nr_cnpj_cpf_Vinculado;
}

public void setNm_razao_Social_Vinculado (String nm_razao_Social_Vinculado) {
this.nm_razao_Social_Vinculado = nm_razao_Social_Vinculado;
}

public String getNm_razao_Social_Vinculado () {
return nm_razao_Social_Vinculado;
}

public void setCd_tipo_documento_Vinculado (String cd_tipo_documento_Vinculado) {
this.cd_tipo_documento_Vinculado = cd_tipo_documento_Vinculado;
}

public String getCd_tipo_documento_Vinculado () {
return cd_tipo_documento_Vinculado;
}

public void setNm_tipo_documento_Vinculado (String nm_tipo_documento_Vinculado) {
this.nm_tipo_documento_Vinculado = nm_tipo_documento_Vinculado;
}

public String getNm_tipo_documento_Vinculado () {
return nm_tipo_documento_Vinculado;
}

public void setNr_Compromisso_Vinculado (Integer nr_Compromisso_Vinculado) {
this.nr_Compromisso_Vinculado = nr_Compromisso_Vinculado;
}

public Integer getNr_Compromisso_Vinculado () {
return nr_Compromisso_Vinculado;
}

public void setVl_saldo_Vinculado (Double vl_saldo_Vinculado) {
this.vl_saldo_Vinculado = vl_saldo_Vinculado;
}

public Double getVl_saldo_Vinculado () {
return vl_saldo_Vinculado;
}

public void setNr_documento_Vinculado (String nr_documento_Vinculado) {
this.nr_documento_Vinculado = nr_documento_Vinculado;
}

public String getNr_documento_Vinculado () {
return nr_documento_Vinculado;
}

public void setNr_parcela_Vinculado (Integer nr_parcela_Vinculado) {
this.nr_parcela_Vinculado = nr_parcela_Vinculado;
}

public Integer getNr_parcela_Vinculado () {
return nr_parcela_Vinculado;
}

public void setDt_Inicial (String Dt_Inicial) {
this.Dt_Inicial = Dt_Inicial;
}

public String getDt_Inicial () {
return Dt_Inicial;
}

public void setDt_Final (String Dt_Final) {
this.Dt_Final = Dt_Final;
}

public String getDt_Final () {
return Dt_Final;
}

public void setOid_Compromisso_Parcela (Integer oid_Compromisso_Parcela) {
this.oid_Compromisso_Parcela = oid_Compromisso_Parcela;
}

public Integer getOid_Compromisso_Parcela () {
return oid_Compromisso_Parcela;
}

public void setCd_Conta_Credito (String cd_Conta_Credito) {
this.cd_Conta_Credito = cd_Conta_Credito;
}

public String getCd_Conta_Credito () {
return cd_Conta_Credito;
}

public void setNm_Conta_Credito (String nm_Conta_Credito) {
this.nm_Conta_Credito = nm_Conta_Credito;
}

public String getNm_Conta_Credito () {
return nm_Conta_Credito;
}

public void setOid_Conta_Credito (Integer oid_Conta_Credito) {
this.oid_Conta_Credito = oid_Conta_Credito;
}

public Integer getOid_Conta_Credito () {
return oid_Conta_Credito;
}

public void setVl_Taxa_Banco (Double vl_Taxa_Banco) {
this.vl_Taxa_Banco = vl_Taxa_Banco;
}

public Double getVl_Taxa_Banco () {
return vl_Taxa_Banco;
}

public void setDM_Tipo_Documento (String DM_Tipo_Documento) {
this.DM_Tipo_Documento = DM_Tipo_Documento;
}

public String getDM_Tipo_Documento () {
return DM_Tipo_Documento;
}

public void setDM_Tipo_Pagamento (String DM_Tipo_Pagamento) {
this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
}

public String getDM_Tipo_Pagamento () {
return DM_Tipo_Pagamento;
}

public void setNR_Dia_Provisao (Integer NR_Dia_Provisao) {
this.NR_Dia_Provisao = NR_Dia_Provisao;
}

public Integer getNR_Dia_Provisao () {
return NR_Dia_Provisao;
}

public void setNR_Qtde_Provisao (Integer NR_Qtde_Provisao) {
this.NR_Qtde_Provisao = NR_Qtde_Provisao;
}

public Integer getNR_Qtde_Provisao () {
return NR_Qtde_Provisao;
}

public void setDM_Situacao (String DM_Situacao) {
this.DM_Situacao = DM_Situacao;
}

public String getDM_Situacao () {
return DM_Situacao;
}

public void setDM_Debito_Credito (String DM_Debito_Credito) {
this.DM_Debito_Credito = DM_Debito_Credito;
}

public String getDM_Debito_Credito () {
return DM_Debito_Credito;
}

public void setVL_Compromisso_Original (Double VL_Compromisso_Original) {
this.VL_Compromisso_Original = VL_Compromisso_Original;
}

public Double getVL_Compromisso_Original () {
return VL_Compromisso_Original;
}

public void setDt_Vencimento_Inicial (String dt_Vencimento_Inicial) {
this.dt_Vencimento_Inicial = dt_Vencimento_Inicial;
}

public String getDt_Vencimento_Inicial () {
return dt_Vencimento_Inicial;
}

public void setDt_Vencimento_Final (String dt_Vencimento_Final) {
this.dt_Vencimento_Final = dt_Vencimento_Final;
}

public String getDt_Vencimento_Final () {
return dt_Vencimento_Final;
}

public void setDM_Relatorio (String DM_Relatorio) {
this.DM_Relatorio = DM_Relatorio;
}

public String getDM_Relatorio () {
return DM_Relatorio;
}

public void setDt_Pagamento_Inicial (String dt_Pagamento_Inicial) {
this.dt_Pagamento_Inicial = dt_Pagamento_Inicial;
}

public String getDt_Pagamento_Inicial () {
return dt_Pagamento_Inicial;
}

public void setDt_Pagamento_Final (String dt_Pagamento_Final) {
this.dt_Pagamento_Final = dt_Pagamento_Final;
}

public void setDt_Entrada_Inicial (String dt_Entrada_Inicial) {
this.dt_Entrada_Inicial = dt_Entrada_Inicial;
}

public void setDt_Entrada_Final (String dt_Entrada_Final) {
this.dt_Entrada_Final = dt_Entrada_Final;
}

public String getDt_Pagamento_Final () {
return dt_Pagamento_Final;
}

public String getDt_Entrada_Inicial () {
return dt_Entrada_Inicial;
}

public String getDt_Entrada_Final () {
return dt_Entrada_Final;
}

public String getCD_Barra () {
return CD_Barra;
}

public void setCD_Barra (String barra) {
CD_Barra = barra;
}

public String getDT_Cotacao () {
return DT_Cotacao;
}

public void setDT_Cotacao (String cotacao) {
DT_Cotacao = cotacao;
}

public int getOid_Moeda () {
return oid_Moeda;
}

public void setOid_Moeda (int oid_Moeda) {
this.oid_Moeda = oid_Moeda;
}

public int getOid_Unidade_Pagamento () {
return oid_Unidade_Pagamento;
}

public String getDt_Liberado () {

return dt_Liberado;
}

public String getDt_Stamp () {
return dt_Stamp;
}

public void setOid_Unidade_Pagamento (int oid_Unidade_Pagamento) {
this.oid_Unidade_Pagamento = oid_Unidade_Pagamento;
}

public void setDt_Liberado (String dt_Liberado) {

this.dt_Liberado = dt_Liberado;
}

public void setDt_Stamp (String dt_Stamp) {
this.dt_Stamp = dt_Stamp;
}

public double getVL_Cotacao () {
return VL_Cotacao;
}

public void setVL_Cotacao (double cotacao) {
VL_Cotacao = cotacao;
}

public double getVL_Cotacao_Padrao () {
return VL_Cotacao_Padrao;
}

public void setVL_Cotacao_Padrao (double cotacao_Padrao) {
VL_Cotacao_Padrao = cotacao_Padrao;
}

public double getVL_Saldo_Atualizado () {
return VL_Saldo_Atualizado;
}

public void setVL_Saldo_Atualizado (double saldo_Atualizado) {
VL_Saldo_Atualizado = saldo_Atualizado;
}

public String getCD_Unidade_Pagamento () {
return CD_Unidade_Pagamento;
}

public void setCD_Unidade_Pagamento (String unidade_Pagamento) {
CD_Unidade_Pagamento = unidade_Pagamento;
}

public String getNM_Unidade_Pagamento () {
return NM_Unidade_Pagamento;
}

public void setNM_Unidade_Pagamento (String unidade_Pagamento) {
NM_Unidade_Pagamento = unidade_Pagamento;
}

public double getVL_Credito () {
return VL_Credito;
}

public void setVL_Credito (double credito) {
VL_Credito = credito;
}

public double getVL_Debito () {
return VL_Debito;
}

public void setVL_Debito (double debito) {
VL_Debito = debito;
}

public String getDataRel () {
return dataRel;
}

public void setDataRel (String dataRel) {
this.dataRel = dataRel;
}

public java.util.Collection getDiarioDetalhes () {
return DiarioDetalhes;
}

public void setDiarioDetalhes (java.util.Collection diarioDetalhes) {
DiarioDetalhes = diarioDetalhes;
}

public String getSiglaRel () {
return siglaRel;
}

public long getOid_Fatura_Compromisso () {
return oid_Fatura_Compromisso;
}

public int getNr_Proxima_Parcela () {
return nr_Proxima_Parcela;
}

public Integer getOid_Conta_Nova () {
return oid_Conta_Nova;
}

public int getNr_Dias_Vencimento () {
return nr_Dias_Vencimento;
}

public int getNr_Parcelas () {
return nr_Parcelas;
}

public Integer getOid_Grupo_Economico () {
return oid_Grupo_Economico;
}

public String getNM_Titulo_Relatorio4 () {
return NM_Titulo_Relatorio4;
}

public String getNM_Titulo_Relatorio3 () {
return NM_Titulo_Relatorio3;
}

public String getNM_Titulo_Relatorio2 () {
return NM_Titulo_Relatorio2;
}

public String getNM_Titulo_Relatorio1 () {
return NM_Titulo_Relatorio1;
}

public String getDM_Agendamento () {
return DM_Agendamento;
}

public String getNR_Conta_Corrente_Favorecido () {
return NR_Conta_Corrente_Favorecido;
}

public String getNM_Agencia_Favorecido () {
return NM_Agencia_Favorecido;
}

public String getNM_Banco_Favorecido () {
return NM_Banco_Favorecido;
}

public String getCD_Banco_Favorecido () {
return CD_Banco_Favorecido;
}

public String getNR_Identificacao_Favorecido () {
return NR_Identificacao_Favorecido;
}

public String getNM_Favorecido () {
return NM_Favorecido;
}

public String getDM_Classificar () {
return DM_Classificar;
}

public long getOID_Usuario_Liberacao () {
return OID_Usuario_Liberacao;
}

public String getDM_Liberado () {
return DM_Liberado;
}

public String getDM_Aprovacao () {
return DM_Aprovacao;
}

public String getDT_Aprovacao () {
return DT_Aprovacao;
}

public long getOID_Usuario_Aprovacao () {
return OID_Usuario_Aprovacao;
}

public String getDM_Cartorio () {
return DM_Cartorio;
}

public String getDT_Vencimento_Original () {
return DT_Vencimento_Original;
}

public String getDT_Cartorio () {
return DT_Cartorio;
}

public double getVL_Custas () {
return VL_Custas;
}

public long getOID_Usuario () {
return OID_Usuario;
}

public void setSiglaRel (String siglaRel) {
this.siglaRel = siglaRel;
}

public void setOid_Fatura_Compromisso (long oid_Fatura_Compromisso) {
this.oid_Fatura_Compromisso = oid_Fatura_Compromisso;
}

public void setNr_Proxima_Parcela (int nr_Proxima_Parcela) {
this.nr_Proxima_Parcela = nr_Proxima_Parcela;
}

public void setOid_Conta_Nova (Integer oid_Conta_Nova) {
this.oid_Conta_Nova = oid_Conta_Nova;
}

public void setNr_Dias_Vencimento (int nr_Dias_Vencimento) {
this.nr_Dias_Vencimento = nr_Dias_Vencimento;
}

public void setNr_Parcelas (int nr_Parcelas) {
this.nr_Parcelas = nr_Parcelas;
}

public void setOid_Grupo_Economico (Integer oid_Grupo_Economico) {
this.oid_Grupo_Economico = oid_Grupo_Economico;
}

public void setNM_Titulo_Relatorio4 (String NM_Titulo_Relatorio4) {
this.NM_Titulo_Relatorio4 = NM_Titulo_Relatorio4;
}

public void setNM_Titulo_Relatorio3 (String NM_Titulo_Relatorio3) {
this.NM_Titulo_Relatorio3 = NM_Titulo_Relatorio3;
}

public void setNM_Titulo_Relatorio2 (String NM_Titulo_Relatorio2) {
this.NM_Titulo_Relatorio2 = NM_Titulo_Relatorio2;
}

public void setNM_Titulo_Relatorio1 (String NM_Titulo_Relatorio1) {
this.NM_Titulo_Relatorio1 = NM_Titulo_Relatorio1;
}

public void setDM_Agendamento (String DM_Agendamento) {
this.DM_Agendamento = DM_Agendamento;
}

public void setNR_Conta_Corrente_Favorecido (String NR_Conta_Corrente_Favorecido) {
this.NR_Conta_Corrente_Favorecido = NR_Conta_Corrente_Favorecido;
}

public void setNM_Agencia_Favorecido (String NM_Agencia_Favorecido) {
this.NM_Agencia_Favorecido = NM_Agencia_Favorecido;
}

public void setNM_Banco_Favorecido (String NM_Banco_Favorecido) {
this.NM_Banco_Favorecido = NM_Banco_Favorecido;
}

public void setCD_Banco_Favorecido (String CD_Banco_Favorecido) {
this.CD_Banco_Favorecido = CD_Banco_Favorecido;
}

public void setNR_Identificacao_Favorecido (String NR_Identificacao_Favorecido) {
this.NR_Identificacao_Favorecido = NR_Identificacao_Favorecido;
}

public void setNM_Favorecido (String NM_Favorecido) {
this.NM_Favorecido = NM_Favorecido;
}

public void setDM_Classificar (String DM_Classificar) {
this.DM_Classificar = DM_Classificar;
}

public void setOID_Usuario_Liberacao (long OID_Usuario_Liberacao) {
this.OID_Usuario_Liberacao = OID_Usuario_Liberacao;
}

public void setDM_Liberado (String DM_Liberado) {
this.DM_Liberado = DM_Liberado;
}

public void setDM_Aprovacao (String DM_Aprovacao) {
this.DM_Aprovacao = DM_Aprovacao;
}

public void setDT_Aprovacao (String DT_Aprovacao) {
this.DT_Aprovacao = DT_Aprovacao;
}

public void setOID_Usuario_Aprovacao (long OID_Usuario_Aprovacao) {
this.OID_Usuario_Aprovacao = OID_Usuario_Aprovacao;
}

public void setDM_Cartorio (String DM_Cartorio) {
this.DM_Cartorio = DM_Cartorio;
}

public void setDT_Vencimento_Original (String DT_Vencimento_Original) {
this.DT_Vencimento_Original = DT_Vencimento_Original;
}

public void setDT_Cartorio (String DT_Cartorio) {
this.DT_Cartorio = DT_Cartorio;
}

public void setVL_Custas (double VL_Custas) {
this.VL_Custas = VL_Custas;
}

public void setOID_Usuario (long OID_Usuario) {
this.OID_Usuario = OID_Usuario;
}

public double getVL_Saldo_Inicial () {
return VL_Saldo_Inicial;
}

public void setVL_Saldo_Inicial (double saldo_Inicial) {
VL_Saldo_Inicial = saldo_Inicial;
}

public int getOid_Empresa() {
return oid_Empresa;
}

public void setOid_Empresa(int oid_Empresa) {
this.oid_Empresa = oid_Empresa;
}

public Integer getNr_Compromisso_Final() {
return nr_Compromisso_Final;
}

public void setNr_Compromisso_Final(Integer nr_Compromisso_Final) {
this.nr_Compromisso_Final = nr_Compromisso_Final;
}

public MoedaBean getEdMoeda() {
return edMoeda;
}

public void setEdMoeda(MoedaBean edMoeda) {
this.edMoeda = edMoeda;
}

public long getOid_Lote_Pagamento() {
return oid_Lote_Pagamento;
}

public void setOid_Lote_Pagamento(long oid_Lote_Pagamento) {
this.oid_Lote_Pagamento = oid_Lote_Pagamento;
}
}