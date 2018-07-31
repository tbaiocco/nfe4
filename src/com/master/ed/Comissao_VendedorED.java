/*
 * Created on 03/09/2004
 */
package com.master.ed;

/**
 * @author Andre Valadas
 */
public class Comissao_VendedorED
extends MasterED {

	private int oid_Comissao_Vendedor;
	private String oid_Vendedor;
	private double PE_Comissao;
	private int oid_Mix;
	private int oid_Produto;
	private int oid_Tipo_Produto;
	private int oid_Estrutura_Produto;
	private String DM_Situacao;
	//*** Implemetados
	private String CD_Mix;
	private String NM_Mix;
	private String CD_Produto;
	private String NM_Produto;
	private String CD_Tipo_Produto;
	private String NM_Tipo_Produto;
	private String CD_Estrutura_Produto;
	private String NM_Estrutura_Produto;
	private String DT_Emissao_Inicial;
	private String DT_Emissao_Final;
	private String DM_Relatorio;
	private String DM_Tipo_Documento; /**
	 * @return Returns the cD_Estrutura_Produto.
	 */
	public String getCD_Estrutura_Produto () {
		return CD_Estrutura_Produto;
	}

	/**
	 * @param estrutura_Produto The cD_Estrutura_Produto to set.
	 */
	public void setCD_Estrutura_Produto (String estrutura_Produto) {
		CD_Estrutura_Produto = estrutura_Produto;
	}

	/**
	 * @return Returns the cD_Mix.
	 */
	public String getCD_Mix () {
		return CD_Mix;
	}

	/**
	 * @param mix The cD_Mix to set.
	 */
	public void setCD_Mix (String mix) {
		CD_Mix = mix;
	}

	/**
	 * @return Returns the cD_Produto.
	 */
	public String getCD_Produto () {
		return CD_Produto;
	}

	/**
	 * @param produto The cD_Produto to set.
	 */
	public void setCD_Produto (String produto) {
		CD_Produto = produto;
	}

	/**
	 * @return Returns the cD_Tipo_Produto.
	 */
	public String getCD_Tipo_Produto () {
		return CD_Tipo_Produto;
	}

	/**
	 * @param tipo_Produto The cD_Tipo_Produto to set.
	 */
	public void setCD_Tipo_Produto (String tipo_Produto) {
		CD_Tipo_Produto = tipo_Produto;
	}

	/**
	 * @return Returns the dM_Situacao.
	 */
	public String getDM_Situacao () {
		return DM_Situacao;
	}

	/**
	 * @param situacao The dM_Situacao to set.
	 */
	public void setDM_Situacao (String situacao) {
		DM_Situacao = situacao;
	}

	/**
	 * @return Returns the nM_Estrutura_Produto.
	 */
	public String getNM_Estrutura_Produto () {
		return NM_Estrutura_Produto;
	}

	/**
	 * @param estrutura_Produto The nM_Estrutura_Produto to set.
	 */
	public void setNM_Estrutura_Produto (String estrutura_Produto) {
		NM_Estrutura_Produto = estrutura_Produto;
	}

	/**
	 * @return Returns the nM_Mix.
	 */
	public String getNM_Mix () {
		return NM_Mix;
	}

	/**
	 * @param mix The nM_Mix to set.
	 */
	public void setNM_Mix (String mix) {
		NM_Mix = mix;
	}

	/**
	 * @return Returns the nM_Produto.
	 */
	public String getNM_Produto () {
		return NM_Produto;
	}

	/**
	 * @param produto The nM_Produto to set.
	 */
	public void setNM_Produto (String produto) {
		NM_Produto = produto;
	}

	/**
	 * @return Returns the nM_Tipo_Produto.
	 */
	public String getNM_Tipo_Produto () {
		return NM_Tipo_Produto;
	}

	/**
	 * @param tipo_Produto The nM_Tipo_Produto to set.
	 */
	public void setNM_Tipo_Produto (String tipo_Produto) {
		NM_Tipo_Produto = tipo_Produto;
	}

	/**
	 * @return Returns the oid_Comissao_Vendedor.
	 */
	public int getOid_Comissao_Vendedor () {
		return oid_Comissao_Vendedor;
	}

	/**
	 * @param oid_Comissao_Vendedor The oid_Comissao_Vendedor to set.
	 */
	public void setOid_Comissao_Vendedor (int oid_Comissao_Vendedor) {
		this.oid_Comissao_Vendedor = oid_Comissao_Vendedor;
	}

	/**
	 * @return Returns the oid_Estrutura_Produto.
	 */
	public int getOid_Estrutura_Produto () {
		return oid_Estrutura_Produto;
	}

	/**
	 * @param oid_Estrutura_Produto The oid_Estrutura_Produto to set.
	 */
	public void setOid_Estrutura_Produto (int oid_Estrutura_Produto) {
		this.oid_Estrutura_Produto = oid_Estrutura_Produto;
	}

	/**
	 * @return Returns the oid_Mix.
	 */
	public int getOid_Mix () {
		return oid_Mix;
	}

	/**
	 * @param oid_Mix The oid_Mix to set.
	 */
	public void setOid_Mix (int oid_Mix) {
		this.oid_Mix = oid_Mix;
	}

	/**
	 * @return Returns the oid_Produto.
	 */
	public int getOid_Produto () {
		return oid_Produto;
	}

	/**
	 * @param oid_Produto The oid_Produto to set.
	 */
	public void setOid_Produto (int oid_Produto) {
		this.oid_Produto = oid_Produto;
	}

	/**
	 * @return Returns the oid_Tipo_Produto.
	 */
	public int getOid_Tipo_Produto () {
		return oid_Tipo_Produto;
	}

	/**
	 * @param oid_Tipo_Produto The oid_Tipo_Produto to set.
	 */
	public void setOid_Tipo_Produto (int oid_Tipo_Produto) {
		this.oid_Tipo_Produto = oid_Tipo_Produto;
	}

	/**
	 * @return Returns the oid_Vendedor.
	 */
	public String getOid_Vendedor () {
		return oid_Vendedor;
	}

	public String getDM_Tipo_Documento () {
		return DM_Tipo_Documento;
	}

	public String getDM_Relatorio () {
		return DM_Relatorio;
	}

	public String getDT_Emissao_Final () {
		return DT_Emissao_Final;
	}

	public String getDT_Emissao_Inicial () {
		return DT_Emissao_Inicial;
	}

	/**
	 * @param oid_Vendedor The oid_Vendedor to set.
	 */
	public void setOid_Vendedor (String oid_Vendedor) {
		this.oid_Vendedor = oid_Vendedor;
	}

	public void setDM_Tipo_Documento (String DM_Tipo_Documento) {
		this.DM_Tipo_Documento = DM_Tipo_Documento;
	}

	public void setDM_Relatorio (String DM_Relatorio) {
		this.DM_Relatorio = DM_Relatorio;
	}

	public void setDT_Emissao_Final (String DT_Emissao_Final) {
		this.DT_Emissao_Final = DT_Emissao_Final;
	}

	public void setDT_Emissao_Inicial (String DT_Emissao_Inicial) {
		this.DT_Emissao_Inicial = DT_Emissao_Inicial;
	}
	public double getPE_Comissao () {
		return PE_Comissao;
	}

	/**
	 * @param comissao The pE_Comissao to set.
	 */
	public void setPE_Comissao (double comissao) {
		PE_Comissao = comissao;
	}
}
