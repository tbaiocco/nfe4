package com.master.ed;

/**
 * @author André Valadas
 * @serial Setores dos Produtos
 * @serialData 01/07/2005
 */
public class Setor_ProdutoED extends MasterED {

    public Setor_ProdutoED() {
    }
    public Setor_ProdutoED(int oid_Setor_Produto) {
        this.oid_Setor_Produto = oid_Setor_Produto;
    }
    
    private int oid_Setor_Produto;
    private String CD_Setor_Produto;
    private String NM_Setor_Produto;
    
    public String getCD_Setor_Produto() {
        return CD_Setor_Produto;
    }
    public int getOid_Setor_Produto() {
        return oid_Setor_Produto;
    }
    public void setCD_Setor_Produto(String Setor_Produto) {
        CD_Setor_Produto = Setor_Produto;
    }
    public void setOid_Setor_Produto(int oid_Setor_Produto) {
        this.oid_Setor_Produto = oid_Setor_Produto;
    }
    public String getNM_Setor_Produto() {
        return NM_Setor_Produto;
    }
    public void setNM_Setor_Produto(String Setor_Produto) {
        NM_Setor_Produto = Setor_Produto;
    }
}