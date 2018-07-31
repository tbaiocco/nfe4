/*
 * Created on 22/10/2004
 */
package com.master.ed;

import java.util.Date;



/**
 * @author Andre Valadas
 */
public class Contrato_VendedorRelED extends MasterED{
	
    private int oid_contrato_vendedor;
    private String oid_vendedor;
    // 1 = Incluidos, 2 = Alterados, 3 = Excluidos
    private String operacao;
    
    //*** Campos do Relatório
    private String nm_razao_social;
    private Date dt_contrato;
    private String cd_produto;
    private String nm_produto;
    private double pe_comissao;
	
	//*** Parâmetros
	private String Mix;
	private String Relatorio;

	//*** ADENDO
	private String NR_Contrato;
	private Integer NR_Folha;
	private String DT_Inicial;
	private String DT_Final;

    public String getCd_produto() {
        return cd_produto;
    }
    public void setCd_produto(String cd_produto) {
        this.cd_produto = cd_produto;
    }
    public Date getDt_contrato() {
        return dt_contrato;
    }
    public void setDt_contrato(Date dt_contrato) {
        this.dt_contrato = dt_contrato;
    }
    public String getDT_Final() {
        return DT_Final;
    }
    public void setDT_Final(String final1) {
        DT_Final = final1;
    }
    public String getDT_Inicial() {
        return DT_Inicial;
    }
    public void setDT_Inicial(String inicial) {
        DT_Inicial = inicial;
    }
    public String getMix() {
        return Mix;
    }
    public void setMix(String mix) {
        Mix = mix;
    }
    public String getNm_produto() {
        return nm_produto;
    }
    public void setNm_produto(String nm_produto) {
        this.nm_produto = nm_produto;
    }
    public String getNm_razao_social() {
        return nm_razao_social;
    }
    public void setNm_razao_social(String nm_razao_social) {
        this.nm_razao_social = nm_razao_social;
    }
    public String getNR_Contrato() {
        return NR_Contrato;
    }
    public void setNR_Contrato(String contrato) {
        NR_Contrato = contrato;
    }
    public Integer getNR_Folha() {
        return NR_Folha;
    }
    public void setNR_Folha(Integer folha) {
        NR_Folha = folha;
    }
    public double getPe_comissao() {
        return pe_comissao;
    }
    public void setPe_comissao(double pe_comissao) {
        this.pe_comissao = pe_comissao;
    }
    public int getOid_contrato_vendedor() {
        return oid_contrato_vendedor;
    }
    public void setOid_contrato_vendedor(int oid_contrato_vendedor) {
        this.oid_contrato_vendedor = oid_contrato_vendedor;
    }
    public String getOid_vendedor() {
        return oid_vendedor;
    }
    public void setOid_vendedor(String oid_vendedor) {
        this.oid_vendedor = oid_vendedor;
    }
    public String getRelatorio() {
        return Relatorio;
    }
    public void setRelatorio(String relatorio) {
        Relatorio = relatorio;
    }
    public String getOperacao() {
        return operacao;
    }
    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }
}
