package com.master.ed;

public class Parametro_WmsED extends MasterED {

	private long oid_Parametro_Wms ;
	private String cd_Modelo_Nota_Fiscal;
	private String cd_Cfop_Retorno_Simbolico;
	private String cd_Cfop_Retorno_Simbolico_Interestadual;
	private String nm_Observacao_Devolucao;  
	private String cd_Tipo_Produto;
	private String cd_Setor_Produto;
	private String cd_Reduzido_Classificacao_Fiscal;
	private String cd_Situacao_Tributaria;
	private String cd_Localizacao;
	private String cd_Cfop_Saida;
	private String cd_Modelo_Nota_Fiscal_Saida;
	private String cd_Cfop_Entrada;
	private String cd_Modelo_Nota_Fiscal_Entrada;
	private long oid_Embalagem;
	private long oid_Tipo_Pallet;
	private String oid_Embalagem_TX;
	private String oid_Tipo_Pallet_TX;
	private String dm_Rotatividade;
	private String cd_Aidof_Nota_Fiscal_Devolucao;
	private int oid_Unidade;


	public String getCd_Cfop_Retorno_Simbolico() {
		return cd_Cfop_Retorno_Simbolico;
	}
	public void setCd_Cfop_Retorno_Simbolico(String cd_Cfop_Retorno_Simbolico) {
		this.cd_Cfop_Retorno_Simbolico = cd_Cfop_Retorno_Simbolico;
	}
	public String getCd_Localizacao() {
		return cd_Localizacao;
	}
	public void setCd_Localizacao(String cd_Localizacao) {
		this.cd_Localizacao = cd_Localizacao;
	}
	public String getCd_Modelo_Nota_Fiscal() {
		return cd_Modelo_Nota_Fiscal;
	}
	public void setCd_Modelo_Nota_Fiscal(String cd_Modelo_Nota_Fiscal) {
		this.cd_Modelo_Nota_Fiscal = cd_Modelo_Nota_Fiscal;
	}
	public String getCd_Reduzido_Classificacao_Fiscal() {
		return cd_Reduzido_Classificacao_Fiscal;
	}
	public void setCd_Reduzido_Classificacao_Fiscal(
			String cd_Reduzido_Classificacao_Fiscal) {
		this.cd_Reduzido_Classificacao_Fiscal = cd_Reduzido_Classificacao_Fiscal;
	}
	public String getCd_Setor_Produto() {
		return cd_Setor_Produto;
	}
	public void setCd_Setor_Produto(String cd_Setor_Produto) {
		this.cd_Setor_Produto = cd_Setor_Produto;
	}
	public String getCd_Situacao_Tributaria() {
		return cd_Situacao_Tributaria;
	}
	public void setCd_Situacao_Tributaria(String cd_Situacao_Tributaria) {
		this.cd_Situacao_Tributaria = cd_Situacao_Tributaria;
	}
	public String getCd_Tipo_Produto() {
		return cd_Tipo_Produto;
	}
	public void setCd_Tipo_Produto(String cd_Tipo_Produto) {
		this.cd_Tipo_Produto = cd_Tipo_Produto;
	}
	public String getNm_Observacao_Devolucao() {
		return nm_Observacao_Devolucao;
	}
	public void setNm_Observacao_Devolucao(String nm_Observacao_Devolucao) {
		this.nm_Observacao_Devolucao = nm_Observacao_Devolucao;
	}
	public long getOid_Embalagem() {
		return oid_Embalagem;
	}
	public void setOid_Embalagem(long oid_Embalagem) {
		this.oid_Embalagem = oid_Embalagem;
	}
	public long getOid_Parametro_Wms() {
		return oid_Parametro_Wms;
	}
	public void setOid_Parametro_Wms(long oid_Parametro_Wms) {
		this.oid_Parametro_Wms = oid_Parametro_Wms;
	}
	public long getOid_Tipo_Pallet() {
		return oid_Tipo_Pallet;
	}
	public void setOid_Tipo_Pallet(long oid_Tipo_Pallet) {
		this.oid_Tipo_Pallet = oid_Tipo_Pallet;
	}
	public String getOid_Embalagem_TX() {
		return oid_Embalagem_TX;
	}
	public void setOid_Embalagem_TX(String oid_Embalagem_TX) {
		this.oid_Embalagem_TX = oid_Embalagem_TX;
		this.setOid_Embalagem(Long.parseLong(oid_Embalagem_TX));
	}
	public String getOid_Tipo_Pallet_TX() {
		return oid_Tipo_Pallet_TX;
	}
	public void setOid_Tipo_Pallet_TX(String oid_Tipo_Pallet_TX) {
		this.oid_Tipo_Pallet_TX = oid_Tipo_Pallet_TX;
		this.setOid_Tipo_Pallet(Long.parseLong(oid_Tipo_Pallet_TX));
	}
	public String getDm_Rotatividade() {
		return dm_Rotatividade;
	}
	public void setDm_Rotatividade(String dm_Rotatividade) {
		this.dm_Rotatividade = dm_Rotatividade;
	}
	public String getCd_Modelo_Nota_Fiscal_Saida() {
		return cd_Modelo_Nota_Fiscal_Saida;
	}
	public void setCd_Modelo_Nota_Fiscal_Saida(String cd_Modelo_Nota_Fiscal_Saida) {
		this.cd_Modelo_Nota_Fiscal_Saida = cd_Modelo_Nota_Fiscal_Saida;
	}
	public String getCd_Cfop_Saida() {
		return cd_Cfop_Saida;
	}
	public void setCd_Cfop_Saida(String cd_Cfop_Saida) {
		this.cd_Cfop_Saida = cd_Cfop_Saida;
	}
	public String getCd_Aidof_Nota_Fiscal_Devolucao() {
		return cd_Aidof_Nota_Fiscal_Devolucao;
	}
	public void setCd_Aidof_Nota_Fiscal_Devolucao(
			String cd_Aidof_Nota_Fiscal_Devolucao) {
		this.cd_Aidof_Nota_Fiscal_Devolucao = cd_Aidof_Nota_Fiscal_Devolucao;
	}
	public String getCd_Cfop_Entrada() {
		return cd_Cfop_Entrada;
	}
	public void setCd_Cfop_Entrada(String cd_Cfop_Entrada) {
		this.cd_Cfop_Entrada = cd_Cfop_Entrada;
	}
	public String getCd_Modelo_Nota_Fiscal_Entrada() {
		return cd_Modelo_Nota_Fiscal_Entrada;
	}
	public void setCd_Modelo_Nota_Fiscal_Entrada(
			String cd_Modelo_Nota_Fiscal_Entrada) {
		this.cd_Modelo_Nota_Fiscal_Entrada = cd_Modelo_Nota_Fiscal_Entrada;
	}
	public int getOid_Unidade() {
		return oid_Unidade;
	}
	public void setOid_Unidade(int oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}
	public String getCd_Cfop_Retorno_Simbolico_Interestadual() {
		return cd_Cfop_Retorno_Simbolico_Interestadual;
	}
	public void setCd_Cfop_Retorno_Simbolico_Interestadual(
			String cd_Cfop_Retorno_Simbolico_Interestadual) {
		this.cd_Cfop_Retorno_Simbolico_Interestadual = cd_Cfop_Retorno_Simbolico_Interestadual;
	}

}
