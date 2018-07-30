package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class UnidadeED  {

	private static final long serialVersionUID = 7107333736311762419L;

	public UnidadeED(){

	}

	public UnidadeED(long oid_unidade){
		oid_Unidade = oid_unidade;
	}

	private long oid_Unidade;
	private String nm_Sigla;
	private String nm_Unidade;
	private String cd_Unidade;
	private String cd_Unidade_Empresa;
	private long oid_Empresa;
	private long oid_Unidade_Cobranca;
	private String oid_Pessoa;
	private long oid_Moeda;
	private String cd_Unidade_Contabil;
	private long oid_Subregiao;
	private String dm_Situacao;
	private String dm_Calcula_Frete;
	private String dm_Tipo_Seguro;
	private String nr_Poliza_Seguro;
	private String nr_Permiso_Complementar;
	private String nr_Permiso_Originario;
	private String oid_Conta_Corrente;
	private long oid_Carteira;
	private long oid_Unidade_Fiscal;
	private String nr_Regime_ICMS_Especial;
	private String oid_Emissor_Padrao;
	private long oid_Deposito;
	private String dm_Tipo_Documento_Padrao;
	private String msg_Stamp;

	private String nm_Sigla_Unidade_Cobranca;
	private String cd_Unidade_Cobranca;
	private String nm_Sigla_Unidade_Fiscal;
	private String cd_Unidade_Fiscal;
	private String cd_Empresa;
	private String nm_Empresa;
	private String cd_Moeda;
	private String nm_Moeda;
	private String nm_Fantasia;
	private String nm_Razao_Social;
	private String nm_Fantasia_Unidade_Fiscal;
	private String nm_Fantasia_Unidade_Cobranca;
	private String dt_Vcto;
	private String nm_Endereco;
	private String nm_Bairro;
	private String nm_Cidade;
	private String nm_Estado;
	private String nm_Regiao_Estado;
	private String nm_Regiao_Pais;
	private String nm_Pais;
	private String nm_Telefone;
	private long oid_Cidade;
	private String nr_Cep;
	private String nr_Cnpj_Cpf;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getCd_Empresa() {
		return cd_Empresa;
	}
	public void setCd_Empresa(String cd_Empresa) {
		this.cd_Empresa = cd_Empresa;
	}
	public String getCd_Moeda() {
		return cd_Moeda;
	}
	public void setCd_Moeda(String cd_Moeda) {
		this.cd_Moeda = cd_Moeda;
	}
	public String getCd_Unidade() {
		return cd_Unidade;
	}
	public void setCd_Unidade(String cd_Unidade) {
		this.cd_Unidade = cd_Unidade;
	}
	public String getCd_Unidade_Cobranca() {
		return cd_Unidade_Cobranca;
	}
	public void setCd_Unidade_Cobranca(String cd_Unidade_Cobranca) {
		this.cd_Unidade_Cobranca = cd_Unidade_Cobranca;
	}
	public String getCd_Unidade_Contabil() {
		return cd_Unidade_Contabil;
	}
	public void setCd_Unidade_Contabil(String cd_Unidade_Contabil) {
		this.cd_Unidade_Contabil = cd_Unidade_Contabil;
	}
	public String getCd_Unidade_Empresa() {
		return cd_Unidade_Empresa;
	}
	public void setCd_Unidade_Empresa(String cd_Unidade_Empresa) {
		this.cd_Unidade_Empresa = cd_Unidade_Empresa;
	}
	public String getCd_Unidade_Fiscal() {
		return cd_Unidade_Fiscal;
	}
	public void setCd_Unidade_Fiscal(String cd_Unidade_Fiscal) {
		this.cd_Unidade_Fiscal = cd_Unidade_Fiscal;
	}
	public String getDm_Calcula_Frete() {
		return dm_Calcula_Frete;
	}
	public void setDm_Calcula_Frete(String dm_Calcula_Frete) {
		this.dm_Calcula_Frete = dm_Calcula_Frete;
	}
	public String getDm_Situacao() {
		return dm_Situacao;
	}
	public void setDm_Situacao(String dm_Situacao) {
		this.dm_Situacao = dm_Situacao;
	}
	public String getDm_Tipo_Documento_Padrao() {
		return dm_Tipo_Documento_Padrao;
	}
	public void setDm_Tipo_Documento_Padrao(String dm_Tipo_Documento_Padrao) {
		this.dm_Tipo_Documento_Padrao = dm_Tipo_Documento_Padrao;
	}
	public String getDm_Tipo_Seguro() {
		return dm_Tipo_Seguro;
	}
	public void setDm_Tipo_Seguro(String dm_Tipo_Seguro) {
		this.dm_Tipo_Seguro = dm_Tipo_Seguro;
	}
	public String getDt_Vcto() {
		return dt_Vcto;
	}
	public void setDt_Vcto(String dt_Vcto) {
		this.dt_Vcto = dt_Vcto;
	}
	public String getNm_Cidade() {
		return nm_Cidade;
	}
	public void setNm_Cidade(String nm_Cidade) {
		this.nm_Cidade = nm_Cidade;
	}
	public String getNm_Empresa() {
		return nm_Empresa;
	}
	public void setNm_Empresa(String nm_Empresa) {
		this.nm_Empresa = nm_Empresa;
	}
	public String getNm_Estado() {
		return nm_Estado;
	}
	public void setNm_Estado(String nm_Estado) {
		this.nm_Estado = nm_Estado;
	}
	public String getNm_Fantasia() {
		return nm_Fantasia;
	}
	public void setNm_Fantasia(String nm_Fantasia) {
		this.nm_Fantasia = nm_Fantasia;
	}
	public String getNm_Fantasia_Unidade_Cobranca() {
		return nm_Fantasia_Unidade_Cobranca;
	}
	public void setNm_Fantasia_Unidade_Cobranca(String nm_Fantasia_Unidade_Cobranca) {
		this.nm_Fantasia_Unidade_Cobranca = nm_Fantasia_Unidade_Cobranca;
	}
	public String getNm_Fantasia_Unidade_Fiscal() {
		return nm_Fantasia_Unidade_Fiscal;
	}
	public void setNm_Fantasia_Unidade_Fiscal(String nm_Fantasia_Unidade_Fiscal) {
		this.nm_Fantasia_Unidade_Fiscal = nm_Fantasia_Unidade_Fiscal;
	}
	public String getNm_Moeda() {
		return nm_Moeda;
	}
	public void setNm_Moeda(String nm_Moeda) {
		this.nm_Moeda = nm_Moeda;
	}
	public String getNm_Pais() {
		return nm_Pais;
	}
	public void setNm_Pais(String nm_Pais) {
		this.nm_Pais = nm_Pais;
	}
	public String getNm_Razao_Social() {
		return nm_Razao_Social;
	}
	public void setNm_Razao_Social(String nm_Razao_Social) {
		this.nm_Razao_Social = nm_Razao_Social;
	}
	public String getNm_Regiao_Estado() {
		return nm_Regiao_Estado;
	}
	public void setNm_Regiao_Estado(String nm_Regiao_Estado) {
		this.nm_Regiao_Estado = nm_Regiao_Estado;
	}
	public String getNm_Regiao_Pais() {
		return nm_Regiao_Pais;
	}
	public void setNm_Regiao_Pais(String nm_Regiao_Pais) {
		this.nm_Regiao_Pais = nm_Regiao_Pais;
	}
	public String getNm_Sigla() {
		return nm_Sigla;
	}
	public void setNm_Sigla(String nm_Sigla) {
		this.nm_Sigla = nm_Sigla;
	}
	public String getNm_Sigla_Unidade_Cobranca() {
		return nm_Sigla_Unidade_Cobranca;
	}
	public void setNm_Sigla_Unidade_Cobranca(String nm_Sigla_Unidade_Cobranca) {
		this.nm_Sigla_Unidade_Cobranca = nm_Sigla_Unidade_Cobranca;
	}
	public String getNm_Sigla_Unidade_Fiscal() {
		return nm_Sigla_Unidade_Fiscal;
	}
	public void setNm_Sigla_Unidade_Fiscal(String nm_Sigla_Unidade_Fiscal) {
		this.nm_Sigla_Unidade_Fiscal = nm_Sigla_Unidade_Fiscal;
	}
	public String getNr_Permiso_Complementar() {
		return nr_Permiso_Complementar;
	}
	public void setNr_Permiso_Complementar(String nr_Permiso_Complementar) {
		this.nr_Permiso_Complementar = nr_Permiso_Complementar;
	}
	public String getNr_Permiso_Originario() {
		return nr_Permiso_Originario;
	}
	public void setNr_Permiso_Originario(String nr_Permiso_Originario) {
		this.nr_Permiso_Originario = nr_Permiso_Originario;
	}
	public String getNr_Poliza_Seguro() {
		return nr_Poliza_Seguro;
	}
	public void setNr_Poliza_Seguro(String nr_Poliza_Seguro) {
		this.nr_Poliza_Seguro = nr_Poliza_Seguro;
	}
	public String getNr_Regime_ICMS_Especial() {
		return nr_Regime_ICMS_Especial;
	}
	public void setNr_Regime_ICMS_Especial(String nr_Regime_ICMS_Especial) {
		this.nr_Regime_ICMS_Especial = nr_Regime_ICMS_Especial;
	}
	public long getOid_Carteira() {
		return oid_Carteira;
	}
	public void setOid_Carteira(long oid_Carteira) {
		this.oid_Carteira = oid_Carteira;
	}
	public String getOid_Conta_Corrente() {
		return oid_Conta_Corrente;
	}
	public void setOid_Conta_Corrente(String oid_Conta_Corrente) {
		this.oid_Conta_Corrente = oid_Conta_Corrente;
	}
	public long getOid_Deposito() {
		return oid_Deposito;
	}
	public void setOid_Deposito(long oid_Deposito) {
		this.oid_Deposito = oid_Deposito;
	}
	public String getOid_Emissor_Padrao() {
		return oid_Emissor_Padrao;
	}
	public void setOid_Emissor_Padrao(String oid_Emissor_Padrao) {
		this.oid_Emissor_Padrao = oid_Emissor_Padrao;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public long getOid_Moeda() {
		return oid_Moeda;
	}
	public void setOid_Moeda(long oid_Moeda) {
		this.oid_Moeda = oid_Moeda;
	}
	public String getOid_Pessoa() {
		return oid_Pessoa;
	}
	public void setOid_Pessoa(String oid_Pessoa) {
		this.oid_Pessoa = oid_Pessoa;
	}
	public long getOid_Subregiao() {
		return oid_Subregiao;
	}
	public void setOid_Subregiao(long oid_Subregiao) {
		this.oid_Subregiao = oid_Subregiao;
	}
	public long getOid_Unidade() {
		return oid_Unidade;
	}
	public void setOid_Unidade(long oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}
	public long getOid_Unidade_Cobranca() {
		return oid_Unidade_Cobranca;
	}
	public void setOid_Unidade_Cobranca(long oid_Unidade_Cobranca) {
		this.oid_Unidade_Cobranca = oid_Unidade_Cobranca;
	}
	public long getOid_Unidade_Fiscal() {
		return oid_Unidade_Fiscal;
	}
	public void setOid_Unidade_Fiscal(long oid_Unidade_Fiscal) {
		this.oid_Unidade_Fiscal = oid_Unidade_Fiscal;
	}
	public String getNm_Endereco() {
		return nm_Endereco;
	}
	public void setNm_Endereco(String nm_Endereco) {
		this.nm_Endereco = nm_Endereco;
	}
	public String getNm_Bairro() {
		return nm_Bairro;
	}
	public void setNm_Bairro(String nm_Bairro) {
		this.nm_Bairro = nm_Bairro;
	}
	public String getNm_Telefone() {
		return nm_Telefone;
	}
	public void setNm_Telefone(String nm_Telefone) {
		this.nm_Telefone = nm_Telefone;
	}
	public long getOid_Cidade() {
		return oid_Cidade;
	}
	public void setOid_Cidade(long oid_Cidade) {
		this.oid_Cidade = oid_Cidade;
	}
	public String getNr_Cep() {
		return nr_Cep;
	}
	public void setNr_Cep(String nr_Cep) {
		this.nr_Cep = nr_Cep;
	}
	public String getNr_Cnpj_Cpf() {
		return nr_Cnpj_Cpf;
	}
	public void setNr_Cnpj_Cpf(String nr_Cnpj_Cpf) {
		this.nr_Cnpj_Cpf = nr_Cnpj_Cpf;
	}
	public String getNm_Unidade() {
		return nm_Unidade;
	}
	public void setNm_Unidade(String nm_Unidade) {
		this.nm_Unidade = nm_Unidade;
	}
	public String getMsg_Stamp() {
		return msg_Stamp;
	}
	public void setMsg_Stamp(String msg_Stamp) {
		this.msg_Stamp = msg_Stamp;
	}





}
