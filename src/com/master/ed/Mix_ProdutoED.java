/*
 * Created on 31/08/2004
 */
package com.master.ed;

/**
 * @author Andre
 */
public class Mix_ProdutoED extends MasterED{
	
	private int oid_Mix_Produto;
	private int oid_Produto;
	private int oid_Mix;
	private String oid_Pessoa_Distribuidor;
	
	/**
	 * @return Returns the oid_Mix.
	 */
	public int getOid_Mix() {
		return oid_Mix;
	}
	/**
	 * @param oid_Mix The oid_Mix to set.
	 */
	public void setOid_Mix(int oid_Mix) {
		this.oid_Mix = oid_Mix;
	}
	/**
	 * @return Returns the oid_Mix_Produto.
	 */
	public int getOid_Mix_Produto() {
		return oid_Mix_Produto;
	}
	/**
	 * @param oid_Mix_Produto The oid_Mix_Produto to set.
	 */
	public void setOid_Mix_Produto(int oid_Mix_Produto) {
		this.oid_Mix_Produto = oid_Mix_Produto;
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
    public String getOid_Pessoa_Distribuidor() {
        return oid_Pessoa_Distribuidor;
    }
    public void setOid_Pessoa_Distribuidor(String oid_Pessoa_Distribuidor) {
        this.oid_Pessoa_Distribuidor = oid_Pessoa_Distribuidor;
    }
}
