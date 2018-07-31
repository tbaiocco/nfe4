package com.master.ed;

import javax.servlet.http.HttpServletResponse;

public class CidadeED extends RelatorioBaseED {
    private long oid_Cidade;
    private String cd_cidade;
    private String NM_Cidade;
    private int oid_regiao_estado;
    private String cd_regiao_estado;
    private String nm_regiao_estado;
    private int oid_estado;
    private String CD_Estado;
    private String NM_Estado;

    public CidadeED() {
    }

    public CidadeED(long oid_cidade) {
    	oid_Cidade = oid_cidade;
    }

    public CidadeED(HttpServletResponse response, String nomeRelatorio) {
        super(response, nomeRelatorio);
    }

    public CidadeED(String cidade, String estado) {
        NM_Cidade = cidade;
        CD_Estado = estado;
    }


    public long getOID_Cidade() {
        return oid_Cidade;
    }

    public void setOID_Cidade(long OID_Cidade) {
        this.oid_Cidade = OID_Cidade;
    }

    public void setNM_Cidade(String NM_Cidade) {
        this.NM_Cidade = NM_Cidade;
    }

    public String getNM_Cidade() {
        return NM_Cidade;
    }

    public void setCD_Estado(String CD_Estado) {
        this.CD_Estado = CD_Estado;
    }

    public String getCD_Estado() {
        return CD_Estado;
    }

    public void setNM_Estado(String NM_Estado) {
        this.NM_Estado = NM_Estado;
    }

    public String getNM_Estado() {
        return NM_Estado;
    }
    public int getOid_estado() {
        return this.oid_estado;
    }
    public void setOid_estado(int oid_estado) {
        this.oid_estado = oid_estado;
    }
    public String getCd_regiao_estado() {
        return this.cd_regiao_estado;
    }
    public void setCd_regiao_estado(String cd_regiao_estado) {
        this.cd_regiao_estado = cd_regiao_estado;
    }
    public String getNm_regiao_estado() {
        return this.nm_regiao_estado;
    }
    public void setNm_regiao_estado(String nm_regiao_estado) {
        this.nm_regiao_estado = nm_regiao_estado;
    }
    public int getOid_regiao_estado() {
        return this.oid_regiao_estado;
    }
    public void setOid_regiao_estado(int oid_regiao_estado) {
        this.oid_regiao_estado = oid_regiao_estado;
    }
    public String getCd_cidade() {
        return this.cd_cidade;
    }
    public void setCd_cidade(String cd_cidade) {
        this.cd_cidade = cd_cidade;
    }
}