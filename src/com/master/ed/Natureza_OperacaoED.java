package com.master.ed;

public class Natureza_OperacaoED extends MasterED {

    public Natureza_OperacaoED() {
        super();
    }
    public Natureza_OperacaoED(Integer oid_Natureza_Operacao) {
        super();
        this.oid_Natureza_Operacao = oid_Natureza_Operacao;
    }
    public Natureza_OperacaoED(String cd_Natureza_Operacao) {
        super();
        this.cd_Natureza_Operacao = cd_Natureza_Operacao;
    }

    private Integer oid_Natureza_Operacao;
    private String nm_Natureza_Operacao;
    private String cd_Natureza_Operacao;
    private String Dm_Tipo_Imposto;
    private String DM_Tipo_Operacao;
    private String Cd_CFO_Conhecimento;
    private String DM_Manter_para_NF;

    public String getDescDM_Tipo_Operacao() {
        if ("D".equals(DM_Tipo_Operacao))
            return "Diversas";
        else if ("C".equals(DM_Tipo_Operacao))
            return "Entrada";
        else if ("V".equals(DM_Tipo_Operacao))
            return "Saída";
        else if ("S".equals(DM_Tipo_Operacao))
            return "Devolução";
        else
            return "Não Encontrada!";
    }
    public String getCd_Natureza_Operacao() {
        return cd_Natureza_Operacao;
    }
    public String getDm_Tipo_Imposto() {
        return Dm_Tipo_Imposto;
    }
    public String getNm_Natureza_Operacao() {
        return nm_Natureza_Operacao;
    }
    public Integer getOid_Natureza_Operacao() {
        return oid_Natureza_Operacao;
    }
    public void setOid_Natureza_Operacao(Integer oid_Natureza_Operacao) {
        this.oid_Natureza_Operacao = oid_Natureza_Operacao;
    }
    public void setNm_Natureza_Operacao(String nm_Natureza_Operacao) {
        this.nm_Natureza_Operacao = nm_Natureza_Operacao;
    }
    public void setDm_Tipo_Imposto(String Dm_Tipo_Imposto) {
        this.Dm_Tipo_Imposto = Dm_Tipo_Imposto;
    }
    public void setCd_Natureza_Operacao(String cd_Natureza_Operacao) {
        this.cd_Natureza_Operacao = cd_Natureza_Operacao;
    }
    public void setCd_CFO_Conhecimento(String Cd_CFO_Conhecimento) {
        this.Cd_CFO_Conhecimento = Cd_CFO_Conhecimento;
    }
    public String getCd_CFO_Conhecimento() {
        return Cd_CFO_Conhecimento;
    }
    public String getDM_Tipo_Operacao() {
        return DM_Tipo_Operacao;
    }
    public void setDM_Tipo_Operacao(String tipo_Operacao) {
        DM_Tipo_Operacao = tipo_Operacao;
    }
	public String getDM_Manter_para_NF() {
		return DM_Manter_para_NF;
	}
	public void setDM_Manter_para_NF(String manter_para_NF) {
		DM_Manter_para_NF = manter_para_NF;
	}
}