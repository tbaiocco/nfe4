/*
 * Created on 22/10/2004
 */
package com.master.ed;

/**
 * @author Andre Valadas
 */
public class ProdutoRelED extends MasterED{
    
    //*** Layouts [Modelos]
    public static final String MODELO_1 = "PRO_MODELO_1";
    // Constantes com NOME dos Relatorios a chamar...
    //*** proF303rl
    public static final String MODELO_ESTOQUE_1 = "Produto_Estoque_01"; //Por Produtos
    public static final String MODELO_ESTOQUE_2 = "Produto_Estoque_02"; //Por Tipos de Estoque
    public static final String MODELO_ESTOQUE_3 = "Produto_Estoque_03"; //Pro Deposito(Armazens)
    
    //*** pedF300rl
    public static final String MODELO_PRODUTOS_COMPRADOS_1 = "Produtos_Comprados_01"; //Produtos Comprados(Por Nota Fiscal)
    
    private String Modelo;
    
    //*** Filtros
    public static final String FILTER_TODOS = "TODOS ";
    public static final String FILTER_PRODUTO = "PRODUTO ";
    public static final String FILTER_FORNECEDOR = "FORNECEDOR ";
    public static final String FILTER_ESTRUTURA = "ESTRUTURA ";
    public static final String FILTER_SETOR = "SETOR ";
    public static final String FILTER_MIX = "MIX ";
    public static final String FILTER_DISTRIBUIDOR = "DISTRIBUIDOR ";
    private String Filter;    
    
    //*** Dados
    private String cd_produto;
    private String cd_produto_fornecedor;
    private String nm_produto;
    private String cd_fornecedor;
    private String nm_fornecedor;    
    private String cd_estrutura_produto;
    private String nm_estrutura_produto;
    private String cd_mix;
    private String nm_mix;
    private String cd_distribuidor;
    private String nm_distribuidor;
    
    private String cd_unidade;
    private String nm_unidade;
  
    public String getCd_distribuidor() {
        return cd_distribuidor;
    }
    public void setCd_distribuidor(String cd_distribuidor) {
        this.cd_distribuidor = cd_distribuidor;
    }
    public String getCd_estrutura_produto() {
        return cd_estrutura_produto;
    }
    public void setCd_estrutura_produto(String cd_estrutura_produto) {
        this.cd_estrutura_produto = cd_estrutura_produto;
    }
    public String getCd_fornecedor() {
        return cd_fornecedor;
    }
    public void setCd_fornecedor(String cd_fornecedor) {
        this.cd_fornecedor = cd_fornecedor;
    }
    public String getCd_mix() {
        return cd_mix;
    }
    public void setCd_mix(String cd_mix) {
        this.cd_mix = cd_mix;
    }
    public String getCd_produto() {
        return cd_produto;
    }
    public void setCd_produto(String cd_produto) {
        this.cd_produto = cd_produto;
    }
    public String getCd_unidade() {
        return cd_unidade;
    }
    public void setCd_unidade(String cd_unidade) {
        this.cd_unidade = cd_unidade;
    }
    public String getNm_distribuidor() {
        return nm_distribuidor;
    }
    public void setNm_distribuidor(String nm_distribuidor) {
        this.nm_distribuidor = nm_distribuidor;
    }
    public String getNm_estrutura_produto() {
        return nm_estrutura_produto;
    }
    public void setNm_estrutura_produto(String nm_estrutura_produto) {
        this.nm_estrutura_produto = nm_estrutura_produto;
    }
    public String getNm_fornecedor() {
        return nm_fornecedor;
    }
    public void setNm_fornecedor(String nm_fornecedor) {
        this.nm_fornecedor = nm_fornecedor;
    }
    public String getNm_mix() {
        return nm_mix;
    }
    public void setNm_mix(String nm_mix) {
        this.nm_mix = nm_mix;
    }
    public String getNm_produto() {
        return nm_produto;
    }
    public void setNm_produto(String nm_produto) {
        this.nm_produto = nm_produto;
    }
    public String getNm_unidade() {
        return nm_unidade;
    }
    public void setNm_unidade(String nm_unidade) {
        this.nm_unidade = nm_unidade;
    }
    public String getFilter() {
        return Filter;
    }
    public void setFilter(String filter) {
        Filter = filter;
    }
    public String getModelo() {
        return Modelo;
    }
    public void setModelo(String modelo) {
        Modelo = modelo;
    }
    public String getCd_produto_fornecedor() {
        return cd_produto_fornecedor;
    }
    public void setCd_produto_fornecedor(String cd_produto_fornecedor) {
        this.cd_produto_fornecedor = cd_produto_fornecedor;
    }
}
