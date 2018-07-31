package com.master.ed;

public class EDI_BancoED extends MasterED{


// Dados remetente
  private String NR_CNPJ_CPF;
  private String NM_INSCRICAO;
  private String NM_Endereco;
  private String NM_Bairro;
  private String NM_Cidade;
  private String NR_CEP;
  private String CD_Estado;
  private String NM_Razao_Social;
  private long OID_Cidade;

// outros
  private String NM_Arquivo;
  private String CD_Banco;
  private String NM_Banco;
  private long OID_Banco;

  private double VL_Pago;
  private double VL_Juros;
  private double VL_Tarifa;
  private double VL_Desconto;
  private String DM_Situacao;
  private String NR_Bancario;

  private String NM_Serie;
  private long OID_Pessoa;
  private String DT_Emissao;

  private String TX_Verso;

  //Duplicata
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
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String NM_Inscricao_Estadual;
  private Integer nr_Duplicata_Final;
  private String CD_Moeda;
  private Integer OID_Moeda;
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

  private String DT_Inicial;
  private String DT_Final;
  private long OID_EDI_Banco;
  private String DT_Ocorrencia;
  private String CD_Rejeicao;
  private double VL_Despesas;
  private long OID_Duplicata;
  private double VL_Abatimento;
  private double VL_IOF;
  private String DT_Credito;
  private int NR_Registro;
  private String DT_Importacao;
  private String TX_Observacao;
  private String NM_Situacao;

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

  private String nr_Documento;
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
  private String NR_Titulo;
  private String DM_Tipo_EDI;
  private double VL_Cambial_Ativo;
  private double VL_Cambial_Passivo;
  private double VL_Reembolso;
  private String DM_Tipo_Emissao;
  private String DM_Remete_EDI;
  private String DM_Carteira;
  private String NR_Agencia;
  private String NR_Conta_Corrente;
  private String NR_Duplicata_Parcela;
  private String NR_Documento;
  private String NR_Autorizacao;
  private String NM_Contato_Cobranca;
  private double VL_Desconto_Vencimento;
  public String getCD_Alteracao_Vencimento() {
    return CD_Alteracao_Vencimento;
  }
  public void setCD_Alteracao_Vencimento(String CD_Alteracao_Vencimento) {
    this.CD_Alteracao_Vencimento = CD_Alteracao_Vencimento;
  }
  public void setCD_Baixa(String CD_Baixa) {
    this.CD_Baixa = CD_Baixa;
  }
  public String getCD_Baixa() {
    return CD_Baixa;
  }
  public String getCD_Banco() {
    return CD_Banco;
  }
  public String getCd_Carteira() {
    return cd_Carteira;
  }
  public String getCD_Conta_Corrente() {
    return CD_Conta_Corrente;
  }
  public String getCD_Desconto() {
    return CD_Desconto;
  }
  public String getCD_Empresa_Banco() {
    return CD_Empresa_Banco;
  }
  public void setCD_Banco(String CD_Banco) {
    this.CD_Banco = CD_Banco;
  }
  public void setCd_Carteira(String cd_Carteira) {
    this.cd_Carteira = cd_Carteira;
  }
  public void setCD_Conta_Corrente(String CD_Conta_Corrente) {
    this.CD_Conta_Corrente = CD_Conta_Corrente;
  }
  public void setCD_Desconto(String CD_Desconto) {
    this.CD_Desconto = CD_Desconto;
  }
  public void setCD_Empresa_Banco(String CD_Empresa_Banco) {
    this.CD_Empresa_Banco = CD_Empresa_Banco;
  }
  public void setCD_Estado(String CD_Estado) {
    this.CD_Estado = CD_Estado;
  }
  public String getCD_Estado() {
    return CD_Estado;
  }
  public String getCD_Moeda() {
    return CD_Moeda;
  }
  public void setCD_Moeda(String CD_Moeda) {
    this.CD_Moeda = CD_Moeda;
  }
  public String getCD_Ocorrencia() {
    return CD_Ocorrencia;
  }
  public String getCD_Primeira_Instr_Protesto() {
    return CD_Primeira_Instr_Protesto;
  }
  public String getCD_Protesto() {
    return CD_Protesto;
  }
  public String getCD_Rejeicao() {
    return CD_Rejeicao;
  }
  public String getCD_Remessa() {
    return CD_Remessa;
  }
  public String getCD_Segunda_Instr_Protesto() {
    return CD_Segunda_Instr_Protesto;
  }
  public String getCD_Sustar_Protesto() {
    return CD_Sustar_Protesto;
  }
  public void setCD_Ocorrencia(String CD_Ocorrencia) {
    this.CD_Ocorrencia = CD_Ocorrencia;
  }
  public void setCD_Primeira_Instr_Protesto(String CD_Primeira_Instr_Protesto) {
    this.CD_Primeira_Instr_Protesto = CD_Primeira_Instr_Protesto;
  }
  public void setCD_Protesto(String CD_Protesto) {
    this.CD_Protesto = CD_Protesto;
  }
  public void setCD_Rejeicao(String CD_Rejeicao) {
    this.CD_Rejeicao = CD_Rejeicao;
  }
  public void setCD_Remessa(String CD_Remessa) {
    this.CD_Remessa = CD_Remessa;
  }
  public void setCD_Segunda_Instr_Protesto(String CD_Segunda_Instr_Protesto) {
    this.CD_Segunda_Instr_Protesto = CD_Segunda_Instr_Protesto;
  }
  public void setCD_Sustar_Protesto(String CD_Sustar_Protesto) {
    this.CD_Sustar_Protesto = CD_Sustar_Protesto;
  }
  public void setCd_Tipo_Documento(String cd_Tipo_Documento) {
    this.cd_Tipo_Documento = cd_Tipo_Documento;
  }
  public void setCD_Tipo_Documento(String CD_Tipo_Documento) {
    this.CD_Tipo_Documento = CD_Tipo_Documento;
  }
  public String getCd_Tipo_Documento() {
    return cd_Tipo_Documento;
  }
  public String getCD_Tipo_Documento() {
    return CD_Tipo_Documento;
  }
  public String getCd_Tipo_Instrucao() {
    return cd_Tipo_Instrucao;
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
  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public String getDm_Situacao() {
    return dm_Situacao;
  }
  public String getDT_Credito() {
    return DT_Credito;
  }
  public String getDT_Emissao() {
    return DT_Emissao;
  }
  public String getDt_Emissao() {
    return dt_Emissao;
  }
  public String getDT_Emissao_Final() {
    return DT_Emissao_Final;
  }
  public void setCd_Tipo_Instrucao(String cd_Tipo_Instrucao) {
    this.cd_Tipo_Instrucao = cd_Tipo_Instrucao;
  }
  public void setCd_Unidade(String cd_Unidade) {
    this.cd_Unidade = cd_Unidade;
  }
  public void setCd_Vendedor(String cd_Vendedor) {
    this.cd_Vendedor = cd_Vendedor;
  }
  public void setDm_Numeracao_Automatica(String dm_Numeracao_Automatica) {
    this.dm_Numeracao_Automatica = dm_Numeracao_Automatica;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public void setDm_Situacao(String dm_Situacao) {
    this.dm_Situacao = dm_Situacao;
  }
  public void setDT_Credito(String DT_Credito) {
    this.DT_Credito = DT_Credito;
  }
  public void setDT_Emissao(String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }
  public void setDt_Emissao(String dt_Emissao) {
    this.dt_Emissao = dt_Emissao;
  }
  public void setDT_Emissao_Final(String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }
  public String getDT_Emissao_Inicial() {
    return DT_Emissao_Inicial;
  }
  public String getDT_Final() {
    return DT_Final;
  }
  public String getDT_Importacao() {
    return DT_Importacao;
  }
  public String getDT_Inicial() {
    return DT_Inicial;
  }
  public String getDt_Instrucao() {
    return dt_Instrucao;
  }
  public String getDt_Novo_Vencimento() {
    return dt_Novo_Vencimento;
  }
  public String getDT_Ocorrencia() {
    return DT_Ocorrencia;
  }
  public String getDt_Remessa() {
    return dt_Remessa;
  }
  public String getDt_Retorno() {
    return dt_Retorno;
  }
  public void setDT_Emissao_Inicial(String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }
  public void setDT_Final(String DT_Final) {
    this.DT_Final = DT_Final;
  }
  public void setDT_Importacao(String DT_Importacao) {
    this.DT_Importacao = DT_Importacao;
  }
  public void setDT_Inicial(String DT_Inicial) {
    this.DT_Inicial = DT_Inicial;
  }
  public void setDt_Instrucao(String dt_Instrucao) {
    this.dt_Instrucao = dt_Instrucao;
  }
  public void setDt_Novo_Vencimento(String dt_Novo_Vencimento) {
    this.dt_Novo_Vencimento = dt_Novo_Vencimento;
  }
  public void setDT_Ocorrencia(String DT_Ocorrencia) {
    this.DT_Ocorrencia = DT_Ocorrencia;
  }
  public void setDt_Remessa(String dt_Remessa) {
    this.dt_Remessa = dt_Remessa;
  }
  public void setDt_Retorno(String dt_Retorno) {
    this.dt_Retorno = dt_Retorno;
  }
  public String getDt_Stamp() {
    return dt_Stamp;
  }
  public String getDt_Vencimento() {
    return dt_Vencimento;
  }
  public String getHr_Instrucao() {
    return hr_Instrucao;
  }
  public String getNM_Arquivo() {
    return NM_Arquivo;
  }
  public String getNM_Bairro() {
    return NM_Bairro;
  }
  public String getNM_Banco() {
    return NM_Banco;
  }
  public String getNm_Banco() {
    return nm_Banco;
  }
  public String getNm_Carteira() {
    return nm_Carteira;
  }
  public String getNM_Cidade() {
    return NM_Cidade;
  }
  public void setDt_Stamp(String dt_Stamp) {
    this.dt_Stamp = dt_Stamp;
  }
  public void setDt_Vencimento(String dt_Vencimento) {
    this.dt_Vencimento = dt_Vencimento;
  }
  public void setHr_Instrucao(String hr_Instrucao) {
    this.hr_Instrucao = hr_Instrucao;
  }
  public void setNM_Arquivo(String NM_Arquivo) {
    this.NM_Arquivo = NM_Arquivo;
  }
  public void setNM_Bairro(String NM_Bairro) {
    this.NM_Bairro = NM_Bairro;
  }
  public void setNM_Banco(String NM_Banco) {
    this.NM_Banco = NM_Banco;
  }
  public void setNm_Banco(String nm_Banco) {
    this.nm_Banco = nm_Banco;
  }
  public void setNm_Carteira(String nm_Carteira) {
    this.nm_Carteira = nm_Carteira;
  }
  public void setNM_Cidade(String NM_Cidade) {
    this.NM_Cidade = NM_Cidade;
  }
  public String getNM_Endereco() {
    return NM_Endereco;
  }
  public String getNm_Fantasia() {
    return nm_Fantasia;
  }
  public String getNM_INSCRICAO() {
    return NM_INSCRICAO;
  }
  public String getNM_Inscricao_Estadual() {
    return NM_Inscricao_Estadual;
  }
  public String getNM_Razao_Social() {
    return NM_Razao_Social;
  }
  public String getNm_Razao_Social() {
    return nm_Razao_Social;
  }
  public String getNM_Razao_Social_Empresa() {
    return NM_Razao_Social_Empresa;
  }
  public String getNM_Serie() {
    return NM_Serie;
  }
  public String getNM_Situacao() {
    return NM_Situacao;
  }
  public void setNM_Endereco(String NM_Endereco) {
    this.NM_Endereco = NM_Endereco;
  }
  public void setNm_Fantasia(String nm_Fantasia) {
    this.nm_Fantasia = nm_Fantasia;
  }
  public void setNM_INSCRICAO(String NM_INSCRICAO) {
    this.NM_INSCRICAO = NM_INSCRICAO;
  }
  public void setNM_Inscricao_Estadual(String NM_Inscricao_Estadual) {
    this.NM_Inscricao_Estadual = NM_Inscricao_Estadual;
  }
  public void setNM_Razao_Social(String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }
  public void setNm_Razao_Social(String nm_Razao_Social) {
    this.nm_Razao_Social = nm_Razao_Social;
  }
  public void setNM_Razao_Social_Empresa(String NM_Razao_Social_Empresa) {
    this.NM_Razao_Social_Empresa = NM_Razao_Social_Empresa;
  }
  public void setNM_Serie(String NM_Serie) {
    this.NM_Serie = NM_Serie;
  }
  public void setNM_Situacao(String NM_Situacao) {
    this.NM_Situacao = NM_Situacao;
  }
  public String getNm_Tipo_Documento() {
    return nm_Tipo_Documento;
  }
  public String getNm_Tipo_Instrucao() {
    return nm_Tipo_Instrucao;
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
  public String getNR_CNPJ_CPF() {
    return NR_CNPJ_CPF;
  }
  public String getNr_CNPJ_CPF() {
    return nr_CNPJ_CPF;
  }
  public String getNR_Convenio() {
    return NR_Convenio;
  }
  public int getNR_Dias_Protesto() {
    return NR_Dias_Protesto;
  }
  public int getNR_Dias_Protesto_Banco() {
    return NR_Dias_Protesto_Banco;
  }
  public void setNm_Tipo_Documento(String nm_Tipo_Documento) {
    this.nm_Tipo_Documento = nm_Tipo_Documento;
  }
  public void setNm_Tipo_Instrucao(String nm_Tipo_Instrucao) {
    this.nm_Tipo_Instrucao = nm_Tipo_Instrucao;
  }
  public void setNm_Vendedor(String nm_Vendedor) {
    this.nm_Vendedor = nm_Vendedor;
  }
  public void setNR_Bancario(String NR_Bancario) {
    this.NR_Bancario = NR_Bancario;
  }
  public void setNR_CEP(String NR_CEP) {
    this.NR_CEP = NR_CEP;
  }
  public void setNR_CNPJ_CPF(String NR_CNPJ_CPF) {
    this.NR_CNPJ_CPF = NR_CNPJ_CPF;
  }
  public void setNr_CNPJ_CPF(String nr_CNPJ_CPF) {
    this.nr_CNPJ_CPF = nr_CNPJ_CPF;
  }
  public void setNR_Convenio(String NR_Convenio) {
    this.NR_Convenio = NR_Convenio;
  }
  public void setNR_Dias_Protesto(int NR_Dias_Protesto) {
    this.NR_Dias_Protesto = NR_Dias_Protesto;
  }
  public void setNR_Dias_Protesto_Banco(int NR_Dias_Protesto_Banco) {
    this.NR_Dias_Protesto_Banco = NR_Dias_Protesto_Banco;
  }
  public String getNr_Documento() {
    return nr_Documento;
  }
  public Integer getNr_Duplicata() {
    return nr_Duplicata;
  }
  public long getNR_Duplicata() {
    return NR_Duplicata;
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
  public int getNR_Registro() {
    return NR_Registro;
  }
  public long getNr_Remessa() {
    return nr_Remessa;
  }
  public long getOID_Banco() {
    return OID_Banco;
  }
  public Integer getOid_Carteira() {
    return oid_Carteira;
  }
  public void setOid_Carteira(Integer oid_Carteira) {
    this.oid_Carteira = oid_Carteira;
  }
  public void setOID_Banco(long OID_Banco) {
    this.OID_Banco = OID_Banco;
  }
  public void setNr_Remessa(long nr_Remessa) {
    this.nr_Remessa = nr_Remessa;
  }
  public void setNR_Registro(int NR_Registro) {
    this.NR_Registro = NR_Registro;
  }
  public void setNr_Proximo_Numero(String nr_Proximo_Numero) {
    this.nr_Proximo_Numero = nr_Proximo_Numero;
  }
  public void setNr_Parcela(Integer nr_Parcela) {
    this.nr_Parcela = nr_Parcela;
  }
  public void setNr_Duplicata_Final(Integer nr_Duplicata_Final) {
    this.nr_Duplicata_Final = nr_Duplicata_Final;
  }
  public void setNR_Duplicata(long NR_Duplicata) {
    this.NR_Duplicata = NR_Duplicata;
  }
  public void setNr_Duplicata(Integer nr_Duplicata) {
    this.nr_Duplicata = nr_Duplicata;
  }
  public void setNr_Documento (String nr_Documento) {
    this.nr_Documento = nr_Documento;
  }
  public long getOID_Cidade() {
    return OID_Cidade;
  }
  public long getOID_Duplicata() {
    return OID_Duplicata;
  }
  public long getOid_Duplicata() {
    return oid_Duplicata;
  }
  public long getOID_EDI_Banco() {
    return OID_EDI_Banco;
  }
  public long getOid_Instrucao() {
    return oid_Instrucao;
  }
  public Integer getOID_Moeda() {
    return OID_Moeda;
  }
  public long getOID_Pessoa() {
    return OID_Pessoa;
  }
  public String getOid_Pessoa() {
    return oid_Pessoa;
  }
  public Integer getOid_Tipo_Documento() {
    return oid_Tipo_Documento;
  }
  public void setOid_Tipo_Documento(Integer oid_Tipo_Documento) {
    this.oid_Tipo_Documento = oid_Tipo_Documento;
  }
  public void setOid_Pessoa(String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }
  public void setOID_Pessoa(long OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public void setOID_Moeda(Integer OID_Moeda) {
    this.OID_Moeda = OID_Moeda;
  }
  public void setOid_Instrucao(long oid_Instrucao) {
    this.oid_Instrucao = oid_Instrucao;
  }
  public void setOID_EDI_Banco(long OID_EDI_Banco) {
    this.OID_EDI_Banco = OID_EDI_Banco;
  }
  public void setOid_Duplicata(long oid_Duplicata) {
    this.oid_Duplicata = oid_Duplicata;
  }
  public void setOID_Duplicata(long OID_Duplicata) {
    this.OID_Duplicata = OID_Duplicata;
  }
  public void setOID_Cidade(long OID_Cidade) {
    this.OID_Cidade = OID_Cidade;
  }
  public long getOid_Tipo_Instrucao() {
    return oid_Tipo_Instrucao;
  }
  public Long getOid_Unidade() {
    return oid_Unidade;
  }
  public String getOid_Vendedor() {
    return oid_Vendedor;
  }
  public String getTX_Observacao() {
    return TX_Observacao;
  }
  public String getTx_Observacao() {
    return tx_Observacao;
  }
  public double getVL_Abatimento() {
    return VL_Abatimento;
  }
  public double getVL_Desconto() {
    return VL_Desconto;
  }
  public double getVl_Desconto_Faturamento() {
    return vl_Desconto_Faturamento;
  }
  public double getVL_Despesas() {
    return VL_Despesas;
  }
  public void setVL_Despesas(double VL_Despesas) {
    this.VL_Despesas = VL_Despesas;
  }
  public void setVl_Desconto_Faturamento(double vl_Desconto_Faturamento) {
    this.vl_Desconto_Faturamento = vl_Desconto_Faturamento;
  }
  public void setVL_Desconto(double VL_Desconto) {
    this.VL_Desconto = VL_Desconto;
  }
  public void setVL_Abatimento(double VL_Abatimento) {
    this.VL_Abatimento = VL_Abatimento;
  }
  public void setTx_Observacao(String tx_Observacao) {
    this.tx_Observacao = tx_Observacao;
  }
  public void setTX_Observacao(String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }
  public void setOid_Vendedor(String oid_Vendedor) {
    this.oid_Vendedor = oid_Vendedor;
  }
  public void setOid_Unidade(Long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }
  public void setOid_Tipo_Instrucao(long oid_Tipo_Instrucao) {
    this.oid_Tipo_Instrucao = oid_Tipo_Instrucao;
  }
  public double getVl_Duplicata() {
    return vl_Duplicata;
  }
  public double getVl_Instrucao() {
    return vl_Instrucao;
  }
  public double getVL_IOF() {
    return VL_IOF;
  }
  public double getVL_Juro_Mora_Dia() {
    return VL_Juro_Mora_Dia;
  }
  public double getVL_Juros() {
    return VL_Juros;
  }
  public double getVL_Multa() {
    return VL_Multa;
  }
  public double getVL_Pago() {
    return VL_Pago;
  }
  public double getVl_Saldo() {
    return vl_Saldo;
  }
  public void setVl_Saldo(double vl_Saldo) {
    this.vl_Saldo = vl_Saldo;
  }
  public void setVL_Pago(double VL_Pago) {
    this.VL_Pago = VL_Pago;
  }
  public void setVL_Multa(double VL_Multa) {
    this.VL_Multa = VL_Multa;
  }
  public void setVL_Juros(double VL_Juros) {
    this.VL_Juros = VL_Juros;
  }
  public void setVL_Juro_Mora_Dia(double VL_Juro_Mora_Dia) {
    this.VL_Juro_Mora_Dia = VL_Juro_Mora_Dia;
  }
  public void setVL_IOF(double VL_IOF) {
    this.VL_IOF = VL_IOF;
  }
  public void setVl_Instrucao(double vl_Instrucao) {
    this.vl_Instrucao = vl_Instrucao;
  }
  public void setVl_Duplicata(double vl_Duplicata) {
    this.vl_Duplicata = vl_Duplicata;
  }
  public void setVL_Tarifa(double VL_Tarifa) {
    this.VL_Tarifa = VL_Tarifa;
  }
  public void setVl_Taxa_Cobranca(double vl_Taxa_Cobranca) {
    this.vl_Taxa_Cobranca = vl_Taxa_Cobranca;
  }

  public void setVL_Desconto_Vencimento (double VL_Desconto_Vencimento) {
    this.VL_Desconto_Vencimento = VL_Desconto_Vencimento;
  }

  public void setNM_Contato_Cobranca (String NM_Contato_Cobranca) {
    this.NM_Contato_Cobranca = NM_Contato_Cobranca;
  }

  public void setNR_Autorizacao (String NR_Autorizacao) {
    this.NR_Autorizacao = NR_Autorizacao;
  }

  public void setNR_Documento (String NR_Documento) {
    this.NR_Documento = NR_Documento;
  }

  public void setNR_Duplicata_Parcela (String NR_Duplicata_Parcela) {

    this.NR_Duplicata_Parcela = NR_Duplicata_Parcela;
  }

  public void setNR_Conta_Corrente (String NR_Conta_Corrente) {
    this.NR_Conta_Corrente = NR_Conta_Corrente;
  }

  public void setNR_Agencia (String NR_Agencia) {
    this.NR_Agencia = NR_Agencia;
  }

  public double getVL_Tarifa() {
    return VL_Tarifa;
  }
  public double getVl_Taxa_Cobranca() {
    return vl_Taxa_Cobranca;
  }

  public double getVL_Desconto_Vencimento () {
    return VL_Desconto_Vencimento;
  }

  public String getNM_Contato_Cobranca () {
    return NM_Contato_Cobranca;
  }

  public String getNR_Autorizacao () {
    return NR_Autorizacao;
  }

  public String getNR_Documento () {
    return NR_Documento;
  }

  public String getNR_Duplicata_Parcela () {

    return NR_Duplicata_Parcela;
  }

  public String getNR_Conta_Corrente () {
    return NR_Conta_Corrente;
  }

  public String getNR_Agencia () {
    return NR_Agencia;
  }

  public void setNR_Titulo(String NR_Titulo) {
    this.NR_Titulo = NR_Titulo;
  }
  public String getNR_Titulo() {
    return NR_Titulo;
  }
  public void setDM_Tipo_EDI(String DM_Tipo_EDI) {
    this.DM_Tipo_EDI = DM_Tipo_EDI;
  }
  public String getDM_Tipo_EDI() {
    return DM_Tipo_EDI;
  }
  public void setVL_Cambial_Ativo(double VL_Cambial_Ativo) {
    this.VL_Cambial_Ativo = VL_Cambial_Ativo;
  }
  public double getVL_Cambial_Ativo() {
    return VL_Cambial_Ativo;
  }
  public void setVL_Cambial_Passivo(double VL_Cambial_Passivo) {
    this.VL_Cambial_Passivo = VL_Cambial_Passivo;
  }
  public double getVL_Cambial_Passivo() {
    return VL_Cambial_Passivo;
  }
  public void setVL_Reembolso(double VL_Reembolso) {
    this.VL_Reembolso = VL_Reembolso;
  }
  public double getVL_Reembolso() {
    return VL_Reembolso;
  }
  public void setDM_Tipo_Emissao(String DM_Tipo_Emissao) {
    this.DM_Tipo_Emissao = DM_Tipo_Emissao;
  }
  public String getDM_Tipo_Emissao() {
    return DM_Tipo_Emissao;
  }
  public void setDM_Remete_EDI(String DM_Remete_EDI) {
    this.DM_Remete_EDI = DM_Remete_EDI;
  }
  public String getDM_Remete_EDI() {
    return DM_Remete_EDI;
  }
  public void setDM_Carteira(String DM_Carteira) {
    this.DM_Carteira = DM_Carteira;
  }
  public String getDM_Carteira() {
    return DM_Carteira;
  }

	public String getTX_Verso() {
	    return TX_Verso;
	}
	public void setTX_Verso(String verso) {
	    TX_Verso = verso;
}
}
