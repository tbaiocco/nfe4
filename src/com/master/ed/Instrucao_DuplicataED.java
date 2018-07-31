package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Instrucao_DuplicataED extends MasterED {

  private double vl_Instrucao;
  private String oid_Pessoa;
  private long oid_Duplicata;
  private long nr_Remessa;
  private long oid_Instrucao;
  private String dt_Instrucao;
  private String hr_Instrucao;
  private String dm_Situacao;
  private String dt_Novo_Vencimento;
  private String dt_Retorno;
  private String dt_Remessa;
  private String nm_Tipo_Instrucao;
  private String nm_Banco;
  private String cd_Carteira;
  private long oid_Tipo_Instrucao;
  private String dt_Stamp;
  private String tx_Observacao;
  private long NR_Duplicata;
  private String cd_Tipo_Instrucao;

  private Long nr_Documento;
  private Integer nr_Parcela;
  private String dt_Emissao;
  private Integer nr_Duplicata;
  private Integer oid_Tipo_Documento;
  private Long oid_Unidade;
  private String nr_CNPJ_CPF;
  private String nm_Razao_Social;
  private String cd_Tipo_Documento;
  private String nm_Tipo_Documento;
  private String cd_Vendedor;
  private String nm_Vendedor;
  private String nm_Carteira;
  private String cd_Unidade;
  private String nm_Fantasia;
  private String dm_Numeracao_Automatica;
  private String nr_Proximo_Numero;
  private String oid_Vendedor;
  private String CD_Conta_Corrente;
  private String NM_Banco;
  private String NR_Bancario;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String NM_Razao_Social;
  private String NM_Endereco;
  private String NR_CEP;
  private String NM_Bairro;
  private String NM_Cidade;
  private String CD_Estado;
  private String NM_Inscricao_Estadual;
  private Integer nr_Duplicata_Final;
  private String CD_Moeda;
  private Integer OID_Moeda;
  private String CD_Banco;
  private String NR_Convenio;
  private String NM_Razao_Social_Empresa;
  private String CD_Empresa_Banco;
  private String CD_Ocorrencia;
  private double vl_Duplicata;
  private double VL_Juro_Mora_Dia;
  private double vl_Desconto_Faturamento;
  private double VL_Multa;
  private double vl_Saldo;
  private double vl_Taxa_Cobranca;
  private String dt_Vencimento;
  private Integer oid_Carteira;
  private int NR_Dias_Protesto;
  private String CD_Remessa;
  private String CD_Baixa;
  private String CD_Desconto;
  private String CD_Alteracao_Vencimento;
  private String CD_Protesto;
  private String CD_Sustar_Protesto;
  private String CD_Tipo_Documento;
  private String CD_Primeira_Instr_Protesto;
  private String CD_Segunda_Instr_Protesto;
  private int NR_Dias_Protesto_Banco;










  public long getOid_Instrucao() {
    return oid_Instrucao;
  }
  public void setOid_Instrucao(long oid_Instrucao) {
    this.oid_Instrucao = oid_Instrucao;
  }
  public void setVl_Instrucao(double vl_Instrucao) {
    this.vl_Instrucao = vl_Instrucao;
  }
  public double getVl_Instrucao() {
    return vl_Instrucao;
  }
  public void setOid_Pessoa(String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }
  public String getOid_Pessoa() {
    return oid_Pessoa;
  }
  public void setOid_Carteira(Integer oid_Carteira) {
    this.oid_Carteira = oid_Carteira;
  }
  public Integer getOid_Carteira() {
    return oid_Carteira;
  }
  public void setOid_Duplicata(long oid_Duplicata) {
    this.oid_Duplicata = oid_Duplicata;
  }
  public long getOid_Duplicata() {
    return oid_Duplicata;
  }
  public void setNr_Remessa(long nr_Remessa) {
    this.nr_Remessa = nr_Remessa;
  }
  public long getNr_Remessa() {
    return nr_Remessa;
  }
  public void setDt_Instrucao(String dt_Instrucao) {
    this.dt_Instrucao = dt_Instrucao;
  }
  public String getDt_Instrucao() {
    return dt_Instrucao;
  }
  public void setHr_Instrucao(String hr_Instrucao) {
    this.hr_Instrucao = hr_Instrucao;
  }
  public String getHr_Instrucao() {
    return hr_Instrucao;
  }
  public void setDm_Situacao(String dm_Situacao) {
    this.dm_Situacao = dm_Situacao;
  }
  public String getDm_Situacao() {
    return dm_Situacao;
  }
  public void setDt_Novo_Vencimento(String dt_Novo_Vencimento) {
    this.dt_Novo_Vencimento = dt_Novo_Vencimento;
  }
  public String getDt_Novo_Vencimento() {
    return dt_Novo_Vencimento;
  }
  public void setDt_Retorno(String dt_Retorno) {
    this.dt_Retorno = dt_Retorno;
  }
  public String getDt_Retorno() {
    return dt_Retorno;
  }
  public void setDt_Remessa(String dt_Remessa) {
    this.dt_Remessa = dt_Remessa;
  }
  public String getDt_Remessa() {
    return dt_Remessa;
  }
  public void setNm_Tipo_Instrucao(String nm_Tipo_Instrucao) {
    this.nm_Tipo_Instrucao = nm_Tipo_Instrucao;
  }
  public String getNm_Tipo_Instrucao() {
    return nm_Tipo_Instrucao;
  }
  public void setNm_Banco(String nm_Banco) {
    this.nm_Banco = nm_Banco;
  }
  public String getNm_Banco() {
    return nm_Banco;
  }
  public void setCd_Carteira(String cd_Carteira) {
    this.cd_Carteira = cd_Carteira;
  }
  public String getCd_Carteira() {
    return cd_Carteira;
  }
  public void setOid_Tipo_Instrucao(long oid_Tipo_Instrucao) {
    this.oid_Tipo_Instrucao = oid_Tipo_Instrucao;
  }
  public long getOid_Tipo_Instrucao() {
    return oid_Tipo_Instrucao;
  }
  public void setDt_Stamp(String dt_Stamp) {
    this.dt_Stamp = dt_Stamp;
  }
  public String getDt_Stamp() {
    return dt_Stamp;
  }
  public String getUsuario_Stamp() {
    return super.getUsuario_Stamp();
  }
  public String getDm_Stamp() {
    return super.getDm_Stamp();
  }
  public void setTx_Observacao(String tx_Observacao) {
    this.tx_Observacao = tx_Observacao;
  }
  public String getTx_Observacao() {
    return tx_Observacao;
  }
  public void setNR_Duplicata(long NR_Duplicata) {
    this.NR_Duplicata = NR_Duplicata;
  }
  public long getNR_Duplicata() {
    return NR_Duplicata;
  }
  public void setCd_Tipo_Instrucao(String cd_Tipo_Instrucao) {
    this.cd_Tipo_Instrucao = cd_Tipo_Instrucao;
  }
  public String getCd_Tipo_Instrucao() {
    return cd_Tipo_Instrucao;
  }
  public String getCD_Conta_Corrente() {
    return CD_Conta_Corrente;
  }
  public String getCD_Estado() {
    return CD_Estado;
  }
  public String getCD_Moeda() {
    return CD_Moeda;
  }
  public String getCd_Tipo_Documento() {
    return cd_Tipo_Documento;
  }
  public String getCd_Unidade() {
    return cd_Unidade;
  }
  public String getCd_Vendedor() {
    return cd_Vendedor;
  }
  public String getDm_Numeracao_Automatica() {
    return dm_Numeracao_Automatica;
  }
  public String getDt_Emissao() {
    return dt_Emissao;
  }
  public String getDT_Emissao_Final() {
    return DT_Emissao_Final;
  }
  public String getDT_Emissao_Inicial() {
    return DT_Emissao_Inicial;
  }
  public void setDT_Emissao_Inicial(String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }
  public void setDT_Emissao_Final(String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }
  public void setDt_Emissao(String dt_Emissao) {
    this.dt_Emissao = dt_Emissao;
  }
  public void setDm_Numeracao_Automatica(String dm_Numeracao_Automatica) {
    this.dm_Numeracao_Automatica = dm_Numeracao_Automatica;
  }
  public void setCd_Vendedor(String cd_Vendedor) {
    this.cd_Vendedor = cd_Vendedor;
  }
  public void setCd_Unidade(String cd_Unidade) {
    this.cd_Unidade = cd_Unidade;
  }
  public void setCd_Tipo_Documento(String cd_Tipo_Documento) {
    this.cd_Tipo_Documento = cd_Tipo_Documento;
  }
  public void setCD_Moeda(String CD_Moeda) {
    this.CD_Moeda = CD_Moeda;
  }
  public void setCD_Estado(String CD_Estado) {
    this.CD_Estado = CD_Estado;
  }
  public void setCD_Conta_Corrente(String CD_Conta_Corrente) {
    this.CD_Conta_Corrente = CD_Conta_Corrente;
  }
  public String getNM_Bairro() {
    return NM_Bairro;
  }
  public void setNM_Bairro(String NM_Bairro) {
    this.NM_Bairro = NM_Bairro;
  }
  public void setNM_Banco(String NM_Banco) {
    this.NM_Banco = NM_Banco;
  }
  public void setNm_Carteira(String nm_Carteira) {
    this.nm_Carteira = nm_Carteira;
  }
  public void setNM_Cidade(String NM_Cidade) {
    this.NM_Cidade = NM_Cidade;
  }
  public void setNM_Endereco(String NM_Endereco) {
    this.NM_Endereco = NM_Endereco;
  }
  public String getNM_Banco() {
    return NM_Banco;
  }
  public String getNm_Carteira() {
    return nm_Carteira;
  }
  public String getNM_Cidade() {
    return NM_Cidade;
  }
  public String getNM_Endereco() {
    return NM_Endereco;
  }
  public void setNm_Fantasia(String nm_Fantasia) {
    this.nm_Fantasia = nm_Fantasia;
  }
  public void setNM_Inscricao_Estadual(String NM_Inscricao_Estadual) {
    this.NM_Inscricao_Estadual = NM_Inscricao_Estadual;
  }
  public void setNm_Razao_Social(String nm_Razao_Social) {
    this.nm_Razao_Social = nm_Razao_Social;
  }
  public void setNM_Razao_Social(String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }
  public void setNm_Tipo_Documento(String nm_Tipo_Documento) {
    this.nm_Tipo_Documento = nm_Tipo_Documento;
  }
  public String getNm_Fantasia() {
    return nm_Fantasia;
  }
  public String getNM_Inscricao_Estadual() {
    return NM_Inscricao_Estadual;
  }
  public String getNm_Razao_Social() {
    return nm_Razao_Social;
  }
  public String getNM_Razao_Social() {
    return NM_Razao_Social;
  }
  public String getNm_Tipo_Documento() {
    return nm_Tipo_Documento;
  }
  public String getNm_Vendedor() {
    return nm_Vendedor;
  }
  public String getNR_Bancario() {
    return NR_Bancario;
  }
  public String getNR_CEP() {
    return NR_CEP;
  }
  public String getNr_CNPJ_CPF() {
    return nr_CNPJ_CPF;
  }
  public Long getNr_Documento() {
    return nr_Documento;
  }
  public void setNr_Documento(Long nr_Documento) {
    this.nr_Documento = nr_Documento;
  }
  public void setNr_CNPJ_CPF(String nr_CNPJ_CPF) {
    this.nr_CNPJ_CPF = nr_CNPJ_CPF;
  }
  public void setNR_CEP(String NR_CEP) {
    this.NR_CEP = NR_CEP;
  }
  public void setNR_Bancario(String NR_Bancario) {
    this.NR_Bancario = NR_Bancario;
  }
  public void setNm_Vendedor(String nm_Vendedor) {
    this.nm_Vendedor = nm_Vendedor;
  }
  public void setNr_Duplicata(Integer nr_Duplicata) {
    this.nr_Duplicata = nr_Duplicata;
  }
  public void setNr_Duplicata_Final(Integer nr_Duplicata_Final) {
    this.nr_Duplicata_Final = nr_Duplicata_Final;
  }
  public void setNr_Parcela(Integer nr_Parcela) {
    this.nr_Parcela = nr_Parcela;
  }
  public void setNr_Proximo_Numero(String nr_Proximo_Numero) {
    this.nr_Proximo_Numero = nr_Proximo_Numero;
  }
  public Integer getNr_Duplicata() {
    return nr_Duplicata;
  }
  public Integer getNr_Duplicata_Final() {
    return nr_Duplicata_Final;
  }
  public Integer getNr_Parcela() {
    return nr_Parcela;
  }
  public String getNr_Proximo_Numero() {
    return nr_Proximo_Numero;
  }
  public Integer getOID_Moeda() {
    return OID_Moeda;
  }
  public void setOID_Moeda(Integer OID_Moeda) {
    this.OID_Moeda = OID_Moeda;
  }
  public void setOid_Tipo_Documento(Integer oid_Tipo_Documento) {
    this.oid_Tipo_Documento = oid_Tipo_Documento;
  }
  public Integer getOid_Tipo_Documento() {
    return oid_Tipo_Documento;
  }
  public void setOid_Unidade(Long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }
  public void setOid_Vendedor(String oid_Vendedor) {
    this.oid_Vendedor = oid_Vendedor;
  }
  public Long getOid_Unidade() {
    return oid_Unidade;
  }
  public String getOid_Vendedor() {
    return oid_Vendedor;
  }
  public double getVl_Desconto_Faturamento() {
    return vl_Desconto_Faturamento;
  }
  public void setVl_Desconto_Faturamento(double vl_Desconto_Faturamento) {
    this.vl_Desconto_Faturamento = vl_Desconto_Faturamento;
  }
  public void setVl_Duplicata(double vl_Duplicata) {
    this.vl_Duplicata = vl_Duplicata;
  }
  public double getVl_Duplicata() {
    return vl_Duplicata;
  }
  public double getVL_Juro_Mora_Dia() {
    return VL_Juro_Mora_Dia;
  }
  public double getVL_Multa() {
    return VL_Multa;
  }
  public double getVl_Saldo() {
    return vl_Saldo;
  }
  public double getVl_Taxa_Cobranca() {
    return vl_Taxa_Cobranca;
  }
  public void setVL_Juro_Mora_Dia(double VL_Juro_Mora_Dia) {
    this.VL_Juro_Mora_Dia = VL_Juro_Mora_Dia;
  }
  public void setVL_Multa(double VL_Multa) {
    this.VL_Multa = VL_Multa;
  }
  public void setVl_Saldo(double vl_Saldo) {
    this.vl_Saldo = vl_Saldo;
  }
  public void setVl_Taxa_Cobranca(double vl_Taxa_Cobranca) {
    this.vl_Taxa_Cobranca = vl_Taxa_Cobranca;
  }
  public void setCD_Banco(String CD_Banco) {
    this.CD_Banco = CD_Banco;
  }
  public String getCD_Banco() {
    return CD_Banco;
  }
  public void setNR_Convenio(String NR_Convenio) {
    this.NR_Convenio = NR_Convenio;
  }
  public String getNR_Convenio() {
    return NR_Convenio;
  }
  public void setNM_Razao_Social_Empresa(String NM_Razao_Social_Empresa) {
    this.NM_Razao_Social_Empresa = NM_Razao_Social_Empresa;
  }
  public String getNM_Razao_Social_Empresa() {
    return NM_Razao_Social_Empresa;
  }
  public void setCD_Empresa_Banco(String CD_Empresa_Banco) {
    this.CD_Empresa_Banco = CD_Empresa_Banco;
  }
  public String getCD_Empresa_Banco() {
    return CD_Empresa_Banco;
  }
  public void setCD_Ocorrencia(String CD_Ocorrencia) {
    this.CD_Ocorrencia = CD_Ocorrencia;
  }
  public String getCD_Ocorrencia() {
    return CD_Ocorrencia;
  }
  public void setDt_Vencimento(String dt_Vencimento) {
    this.dt_Vencimento = dt_Vencimento;
  }
  public String getDt_Vencimento() {
    return dt_Vencimento;
  }
  public void setNR_Dias_Protesto(int NR_Dias_Protesto) {
    this.NR_Dias_Protesto = NR_Dias_Protesto;
  }
  public int getNR_Dias_Protesto() {
    return NR_Dias_Protesto;
  }
  public void setCD_Remessa(String CD_Remessa) {
    this.CD_Remessa = CD_Remessa;
  }
  public String getCD_Remessa() {
    return CD_Remessa;
  }
  public void setCD_Baixa(String CD_Baixa) {
    this.CD_Baixa = CD_Baixa;
  }
  public String getCD_Baixa() {
    return CD_Baixa;
  }
  public void setCD_Desconto(String CD_Desconto) {
    this.CD_Desconto = CD_Desconto;
  }
  public String getCD_Desconto() {
    return CD_Desconto;
  }
  public void setCD_Alteracao_Vencimento(String CD_Alteracao_Vencimento) {
    this.CD_Alteracao_Vencimento = CD_Alteracao_Vencimento;
  }
  public String getCD_Alteracao_Vencimento() {
    return CD_Alteracao_Vencimento;
  }
  public void setCD_Protesto(String CD_Protesto) {
    this.CD_Protesto = CD_Protesto;
  }
  public String getCD_Protesto() {
    return CD_Protesto;
  }
  public void setCD_Sustar_Protesto(String CD_Sustar_Protesto) {
    this.CD_Sustar_Protesto = CD_Sustar_Protesto;
  }
  public String getCD_Sustar_Protesto() {
    return CD_Sustar_Protesto;
  }
  public void setCD_Tipo_Documento(String CD_Tipo_Documento) {
    this.CD_Tipo_Documento = CD_Tipo_Documento;
  }
  public String getCD_Tipo_Documento() {
    return CD_Tipo_Documento;
  }
  public void setCD_Primeira_Instr_Protesto(String CD_Primeira_Instr_Protesto) {
    this.CD_Primeira_Instr_Protesto = CD_Primeira_Instr_Protesto;
  }
  public String getCD_Primeira_Instr_Protesto() {
    return CD_Primeira_Instr_Protesto;
  }
  public void setCD_Segunda_Instr_Protesto(String CD_Segunda_Instr_Protesto) {
    this.CD_Segunda_Instr_Protesto = CD_Segunda_Instr_Protesto;
  }
  public String getCD_Segunda_Instr_Protesto() {
    return CD_Segunda_Instr_Protesto;
  }
  public void setNR_Dias_Protesto_Banco(int NR_Dias_Protesto_Banco) {
    this.NR_Dias_Protesto_Banco = NR_Dias_Protesto_Banco;
  }
  public int getNR_Dias_Protesto_Banco() {
    return NR_Dias_Protesto_Banco;
  }

}