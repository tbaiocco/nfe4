package com.master.ed;

/**
 * @author André Valadas
 * @serial Max. Descontos dos Produtos Referente a Tabelas de Preços
 * @serialData 28/02/2006
 */
public class Desconto_ProdutoED extends MasterED {

    public Desconto_ProdutoED() {
    }
    public Desconto_ProdutoED(int produto) {
        super();
        oid_Desconto_Produto = produto;
    }
    
    private int oid_Desconto_Produto;
    private int oid_Tabela_Venda;
    private String oid_Produto_Cliente;
    private double NR_Quantidade;
    private double PE_Desconto;
    
    public double getNR_Quantidade() {
        return NR_Quantidade;
    }
    
    public void setNR_Quantidade(double quantidade) {
        NR_Quantidade = quantidade;
    }
    
    public int getOid_Desconto_Produto() {
        return oid_Desconto_Produto;
    }
    
    public void setOid_Desconto_Produto(int oid_Desconto_Produto) {
        this.oid_Desconto_Produto = oid_Desconto_Produto;
    }
    
    public String getOid_Produto_Cliente() {
        return oid_Produto_Cliente;
    }
    
    public void setOid_Produto_Cliente(String oid_Produto_Cliente) {
        this.oid_Produto_Cliente = oid_Produto_Cliente;
    }
    
    public int getOid_Tabela_Venda() {
        return oid_Tabela_Venda;
    }
    
    public void setOid_Tabela_Venda(int oid_Tabela_Venda) {
        this.oid_Tabela_Venda = oid_Tabela_Venda;
    }
    
    public double getPE_Desconto() {
        return PE_Desconto;
    }
    
    public void setPE_Desconto(double desconto) {
        PE_Desconto = desconto;
    }
}