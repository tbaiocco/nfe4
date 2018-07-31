package com.master.ed;

import java.io.Serializable;

import com.master.root.FornecedorBean;

/**
 * @author André Valadas
 * @serial Fornecedores do Produto
 * @serialData 02/09/2005
 */
public class Fornecedor_ProdutoED extends MasterED implements Serializable {

    public Fornecedor_ProdutoED() {
        super();
    }
    public Fornecedor_ProdutoED(int fornProduto) {
        oid_Fornecedor_Produto = fornProduto;
    }
    public Fornecedor_ProdutoED(int fornProduto, boolean cFornecedor, boolean cProduto) {
        carregaFornecedor = cFornecedor;
        carregaProduto = cProduto;
        oid_Fornecedor_Produto = fornProduto;
    }
    public Fornecedor_ProdutoED(boolean cFornecedor, boolean cProduto) {
        carregaFornecedor = cFornecedor;
        carregaProduto = cProduto;
    }
    public Fornecedor_ProdutoED(String pessoa, int produto) {
        oid_Pessoa = pessoa;
        oid_Produto = produto;
    }
    public Fornecedor_ProdutoED(String pessoa, int produto, boolean cFornecedor, boolean cProduto) {
        oid_Pessoa = pessoa;
        oid_Produto = produto;
        carregaFornecedor = cFornecedor;
        carregaProduto = cProduto;
    }
    
    private boolean carregaProduto = true;
    private boolean carregaFornecedor = true;
    public ProdutoED edProduto = new ProdutoED();
    public FornecedorBean edFornec = new FornecedorBean();
        
    private int oid_Fornecedor_Produto;
    private int oid_Produto;
    private String oid_Pessoa;
    
    public String getOid_Pessoa() {
        return oid_Pessoa;
    }
    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }
    public int getOid_Produto() {
        return oid_Produto;
    }
    public void setOid_Produto(int oid_Produto) {
        this.oid_Produto = oid_Produto;
    }
    public boolean isCarregaFornecedor() {
        return carregaFornecedor;
    }
    public void setCarregaFornecedor(boolean carregaFornecedor) {
        this.carregaFornecedor = carregaFornecedor;
    }
    public boolean isCarregaProduto() {
        return carregaProduto;
    }
    public void setCarregaProduto(boolean carregaProduto) {
        this.carregaProduto = carregaProduto;
    }
    public int getOid_Fornecedor_Produto() {
        return oid_Fornecedor_Produto;
    }
    public void setOid_Fornecedor_Produto(int oid_Fornecedor_Produto) {
        this.oid_Fornecedor_Produto = oid_Fornecedor_Produto;
    }
}