/*
 * Created on 18/09/2004
 */
package com.master.ed;

/**
 * @author Andre Valadas
 */
public class Produto_ClienteRelED extends MasterED {
    
    public static final String LAYOUT_ANEXO = "ANEXO";
    public static final String LAYOUT_ADENDO = "ADENDO";
    public static final String ORDENAR_NOME = "NOME";
    public static final String ORDENAR_CODIGO = "CODIGO";      
    
    private String oid_vendedor;
    private String oid_pessoa;
    private String nr_pagina;
    private String mix;
    private String Layout;
    private String OrderBy;
    
    private String cd_produto;
    private String nm_produto;
    private double pe_comissao;
    
    public String getCd_produto() {
        return cd_produto;
    }
    public void setCd_produto(String cd_produto) {
        this.cd_produto = cd_produto;
    }
    public String getMix() {
        return mix;
    }
    public void setMix(String mix) {
        this.mix = mix;
    }
    public String getNm_produto() {
        return nm_produto;
    }
    public void setNm_produto(String nm_produto) {
        this.nm_produto = nm_produto;
    }
    public String getNr_pagina() {
        return nr_pagina;
    }
    public void setNr_pagina(String nr_pagina) {
        this.nr_pagina = nr_pagina;
    }
    public String getOid_pessoa() {
        return oid_pessoa;
    }
    public void setOid_pessoa(String oid_pessoa) {
        this.oid_pessoa = oid_pessoa;
    }
    public String getOid_vendedor() {
        return oid_vendedor;
    }
    public void setOid_vendedor(String oid_vendedor) {
        this.oid_vendedor = oid_vendedor;
    }
    public double getPe_comissao() {
        return pe_comissao;
    }
    public void setPe_comissao(double pe_comissao) {
        this.pe_comissao = pe_comissao;
    }
    public String getLayout() {
        return Layout;
    }
    public void setLayout(String layout) {
        Layout = layout;
    }
    public String getOrderBy() {
        return OrderBy;
    }
    public void setOrderBy(String orderBy) {
        OrderBy = orderBy;
    }
}
