/*
 * Created on 16/11/2004
 */
package com.master.ed;

/**
 * @author Andre Valadas
 */
public class PessoaRelED {

    private String cd_vendedor;
    private String nr_cnpj_cpf;
    private String nm_razao_social;
    private String nm_cidade;
    private String nm_estado;
    private String nm_endereco;
    private String nm_bairro;
    private String nr_cep;
    private String nr_telefone;
    private String nm_inscricao_estadual;
    
    public String getCd_vendedor() {
        return cd_vendedor;
    }
    public void setCd_vendedor(String cd_vendedor) {
        this.cd_vendedor = cd_vendedor;
    }
    public String getNm_bairro() {
        return nm_bairro;
    }
    public void setNm_bairro(String nm_bairro) {
        this.nm_bairro = nm_bairro;
    }
    public String getNm_cidade() {
        return nm_cidade;
    }
    public void setNm_cidade(String nm_cidade) {
        this.nm_cidade = nm_cidade;
    }
    public String getNm_endereco() {
        return nm_endereco;
    }
    public void setNm_endereco(String nm_endereco) {
        this.nm_endereco = nm_endereco;
    }
    public String getNm_estado() {
        return nm_estado;
    }
    public void setNm_estado(String nm_estado) {
        this.nm_estado = nm_estado;
    }
    public String getNm_inscricao_estadual() {
        return nm_inscricao_estadual;
    }
    public void setNm_inscricao_estadual(String nm_inscricao_estadual) {
        this.nm_inscricao_estadual = nm_inscricao_estadual;
    }
    public String getNm_razao_social() {
        return nm_razao_social;
    }
    public void setNm_razao_social(String nm_razao_social) {
        this.nm_razao_social = nm_razao_social;
    }
    public String getNr_cep() {
        return nr_cep;
    }
    public void setNr_cep(String nr_cep) {
        this.nr_cep = nr_cep;
    }
    public String getNr_cnpj_cpf() {
        return nr_cnpj_cpf;
    }
    public void setNr_cnpj_cpf(String nr_cnpj_cpf) {
        this.nr_cnpj_cpf = nr_cnpj_cpf;
    }
    public String getNr_telefone() {
        return nr_telefone;
    }
    public void setNr_telefone(String nr_telefone) {
        this.nr_telefone = nr_telefone;
    }
}