/*
 * Created on 28/06/2005
 *
 */
package com.master.ed;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Tiago
 *
 */
public class RCERelED extends RelatorioBaseED {
    private int oid_RCE;
    private int NR_RCE;
    private int oid_Unidade;
    private String CD_Unidade;
    private String NM_Unidade;
    private String NM_Destinatario;
    private String DT_Lancamento;
    private String DT_Referencia;
    private String oid_Conhecimento;
    private long NR_Conhecimento;
    private double VL_Total_Frete_Conhecimento;
    private double VL_ICMS_Conhecimento;
    private double VL_Total_Frete_Sem_ICMS_Conhecimento;
    private String TX_Notas_Fiscais;
    
    public RCERelED() {
        super();
    }

    public RCERelED(HttpServletResponse response, String nomeRelatorio) {
        super(response, nomeRelatorio);
    }
    
    public String getDT_Lancamento() {
        return this.DT_Lancamento;
    }
    public void setDT_Lancamento(String lancamento) {
        this.DT_Lancamento = lancamento;
    }
    public String getDT_Referencia() {
        return this.DT_Referencia;
    }
    public void setDT_Referencia(String referencia) {
        this.DT_Referencia = referencia;
    }
    public String getNM_Destinatario() {
        return this.NM_Destinatario;
    }
    public void setNM_Destinatario(String destinatario) {
        this.NM_Destinatario = destinatario;
    }
    public int getNR_RCE() {
        return this.NR_RCE;
    }
    public void setNR_RCE(int nr_rce) {
        this.NR_RCE = nr_rce;
    }
    public int getOid_RCE() {
        return this.oid_RCE;
    }
    public void setOid_RCE(int oid_RCE) {
        this.oid_RCE = oid_RCE;
    }
    public int getOid_Unidade() {
        return this.oid_Unidade;
    }
    public void setOid_Unidade(int oid_Unidade) {
        this.oid_Unidade = oid_Unidade;
    }
    public String getCD_Unidade() {
        return this.CD_Unidade;
    }
    public void setCD_Unidade(String unidade) {
        this.CD_Unidade = unidade;
    }
    public String getNM_Unidade() {
        return this.NM_Unidade;
    }
    public void setNM_Unidade(String unidade) {
        this.NM_Unidade = unidade;
    }
    public long getNR_Conhecimento() {
        return this.NR_Conhecimento;
    }
    public void setNR_Conhecimento(long conhecimento) {
        this.NR_Conhecimento = conhecimento;
    }
    public String getOid_Conhecimento() {
        return this.oid_Conhecimento;
    }
    public void setOid_Conhecimento(String oid_Conhecimento) {
        this.oid_Conhecimento = oid_Conhecimento;
    }
    public double getVL_Total_Frete_Conhecimento() {
        return this.VL_Total_Frete_Conhecimento;
    }
    public void setVL_Total_Frete_Conhecimento(double total_Frete_Conhecimento) {
        this.VL_Total_Frete_Conhecimento = total_Frete_Conhecimento;
    }
    public double getVL_Total_Frete_Sem_ICMS_Conhecimento() {
        return this.VL_Total_Frete_Sem_ICMS_Conhecimento;
    }
    public void setVL_Total_Frete_Sem_ICMS_Conhecimento(
            double total_Frete_Sem_ICMS_Conhecimento) {
        this.VL_Total_Frete_Sem_ICMS_Conhecimento = total_Frete_Sem_ICMS_Conhecimento;
    }
    public double getVL_ICMS_Conhecimento() {
        return this.VL_ICMS_Conhecimento;
    }
    public void setVL_ICMS_Conhecimento(double conhecimento) {
        this.VL_ICMS_Conhecimento = conhecimento;
    }
    public String getTX_Notas_Fiscais() {
        return this.TX_Notas_Fiscais;
    }
    public void setTX_Notas_Fiscais(String notas_Fiscais) {
        this.TX_Notas_Fiscais = notas_Fiscais;
    }
}
