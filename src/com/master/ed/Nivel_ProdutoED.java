package com.master.ed;

public class Nivel_ProdutoED extends MasterED {

    public Nivel_ProdutoED(int oid_Nivel_Produto) {
        this.oid_Nivel_Produto = oid_Nivel_Produto;
    }
    public Nivel_ProdutoED() {
        super();
    }
    private String nm_Nivel_Produto;
    private String cd_Nivel_Produto;
    private String dm_Nivel_Produto;
    private int oid_Nivel_Produto;

    public String getCd_Nivel_Produto() {
        return cd_Nivel_Produto;
    }

    public String getNm_Nivel_Produto() {
        return nm_Nivel_Produto;
    }

    public int getOid_Nivel_Produto() {
        return oid_Nivel_Produto;
    }

    public void setOid_Nivel_Produto(int oid_Nivel_Produto) {
        this.oid_Nivel_Produto = oid_Nivel_Produto;
    }

    public void setNm_Nivel_Produto(String nm_Nivel_Produto) {
        this.nm_Nivel_Produto = nm_Nivel_Produto;
    }

    public void setCd_Nivel_Produto(String cd_Nivel_Produto) {
        this.cd_Nivel_Produto = cd_Nivel_Produto;
    }

    public void setDm_Nivel_Produto(String dm_Nivel_Produto) {
        this.dm_Nivel_Produto = dm_Nivel_Produto;
    }

    public String getDm_Nivel_Produto() {
        return dm_Nivel_Produto;
    }

}