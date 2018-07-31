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
public class Conhecimento_RCEED extends RelatorioBaseED {
    private int oid_Conhecimento_RCE;
    private String oid_Conhecimento;
    private int oid_RCE;
    private int NR_RCE;
    private long NR_Conhecimento;
    
    public Conhecimento_RCEED() {
        super();
    }

    public Conhecimento_RCEED(HttpServletResponse response, String nomeRelatorio) {
        super(response, nomeRelatorio);
    }
    
    public String getOid_Conhecimento() {
        return this.oid_Conhecimento;
    }
    public void setOid_Conhecimento(String oid_Conhecimento) {
        this.oid_Conhecimento = oid_Conhecimento;
    }
    public int getOid_Conhecimento_RCE() {
        return this.oid_Conhecimento_RCE;
    }
    public void setOid_Conhecimento_RCE(int oid_Conhecimento_RCE) {
        this.oid_Conhecimento_RCE = oid_Conhecimento_RCE;
    }
    public int getOid_RCE() {
        return this.oid_RCE;
    }
    public void setOid_RCE(int oid_RCE) {
        this.oid_RCE = oid_RCE;
    }
    public long getNR_Conhecimento() {
        return this.NR_Conhecimento;
    }
    public void setNR_Conhecimento(long conhecimento) {
        this.NR_Conhecimento = conhecimento;
    }
    public int getNR_RCE() {
        return this.NR_RCE;
    }
    public void setNR_RCE(int nr_rce) {
        this.NR_RCE = nr_rce;
    }
}
