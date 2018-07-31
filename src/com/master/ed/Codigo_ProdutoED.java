/*
 * Created on 27/08/2004
 */
package com.master.ed;

/**
 * @author Andre
 */
public class Codigo_ProdutoED extends MasterED{

	private int oid_Codigo_Produto;
    private int oid_Produto;
    private String dm_Produto;
    private String cd_Barra;
    private String dm_Situacao;
    //*** Implementada
    private String cd_Produto;
    private String nm_Produto;  
    
	/**
	 * @return Returns the cd_Barra.
	 */
	public String getCd_Barra() {
		return cd_Barra;
	}
	/**
	 * @param cd_Barra The cd_Barra to set.
	 */
	public void setCd_Barra(String cd_Barra) {
		this.cd_Barra = cd_Barra;
	}
	/**
	 * @return Returns the cd_Produto.
	 */
	public String getCd_Produto() {
		return cd_Produto;
	}
	/**
	 * @param cd_Produto The cd_Produto to set.
	 */
	public void setCd_Produto(String cd_Produto) {
		this.cd_Produto = cd_Produto;
	}
	/**
	 * @return Returns the dm_Produto.
	 */
	public String getDm_Produto() {
		return dm_Produto;
	}
	/**
	 * @param dm_Produto The dm_Produto to set.
	 */
	public void setDm_Produto(String dm_Produto) {
		this.dm_Produto = dm_Produto;
	}
	/**
	 * @return Returns the dm_Situacao.
	 */
	public String getDm_Situacao() {
		return dm_Situacao;
	}
	/**
	 * @param dm_Situacao The dm_Situacao to set.
	 */
	public void setDm_Situacao(String dm_Situacao) {
		this.dm_Situacao = dm_Situacao;
	}
	/**
	 * @return Returns the nm_Produto.
	 */
	public String getNm_Produto() {
		return nm_Produto;
	}
	/**
	 * @param nm_Produto The nm_Produto to set.
	 */
	public void setNm_Produto(String nm_Produto) {
		this.nm_Produto = nm_Produto;
	}
	/**
	 * @return Returns the oid_Codigo_Produto.
	 */
	public int getOid_Codigo_Produto() {
		return oid_Codigo_Produto;
	}
	/**
	 * @param oid_Codigo_Produto The oid_Codigo_Produto to set.
	 */
	public void setOid_Codigo_Produto(int oid_Codigo_Produto) {
		this.oid_Codigo_Produto = oid_Codigo_Produto;
	}
	/**
	 * @return Returns the oid_Produto.
	 */
	public int getOid_Produto() {
		return oid_Produto;
	}
	/**
	 * @param oid_Produto The oid_Produto to set.
	 */
	public void setOid_Produto(int oid_Produto) {
		this.oid_Produto = oid_Produto;
	}
}
