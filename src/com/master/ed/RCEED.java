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
public class RCEED extends RelatorioBaseED {
    private int oid_RCE;
    private int NR_RCE;
    private int oid_Unidade;
    private String CD_Unidade;
    private String NM_Unidade;
    private String NM_Destinatario;
    private String DT_Lancamento;
    private String DT_Referencia;
    
    public RCEED() {
        super();
    }

    public RCEED(HttpServletResponse response, String nomeRelatorio) {
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
}
