package com.master.ed;

public class Parcela_CompromissoED extends MasterED {

    private String DT_Parcela_Compromisso;
    private String HR_Parcela_Compromisso;
    private String DT_Emissao;
    private long NR_Compromisso;
    private long OID_Unidade;
    private String CD_Unidade;
    private long NR_Parcelamento;
    private long OID_Cidade_Destino;
    private String OID_Parcelamento;
    private String OID_Parcela_Compromisso;
    private String OID_Veiculo;
    private Integer OID_Compromisso;

    public void setOID_Compromisso(Integer OID_Compromisso) {
        this.OID_Compromisso = OID_Compromisso;
    }

    public Integer getOID_Compromisso() {
        return OID_Compromisso;
    }

    public void setOID_Parcela_Compromisso(String OID_Parcela_Compromisso) {
        this.OID_Parcela_Compromisso = OID_Parcela_Compromisso;
    }

    public String getOID_Parcela_Compromisso() {
        return OID_Parcela_Compromisso;
    }

    public void setDT_Parcela_Compromisso(String DT_Parcela_Compromisso) {
        this.DT_Parcela_Compromisso = DT_Parcela_Compromisso;
    }

    public String getDT_Parcela_Compromisso() {
        return DT_Parcela_Compromisso;
    }

    public void setHR_Parcela_Compromisso(String HR_Parcela_Compromisso) {
        this.HR_Parcela_Compromisso = HR_Parcela_Compromisso;
    }

    public String getHR_Parcela_Compromisso() {
        return HR_Parcela_Compromisso;
    }

    public void setOID_Unidade(long OID_Unidade) {
        this.OID_Unidade = OID_Unidade;
    }

    public long getOID_Unidade() {
        return OID_Unidade;
    }

    public void setDT_Emissao(String DT_Emissao) {
        this.DT_Emissao = DT_Emissao;
    }

    public String getDT_Emissao() {
        return DT_Emissao;
    }

    public void setNR_Compromisso(long NR_Compromisso) {
        this.NR_Compromisso = NR_Compromisso;
    }

    public long getNR_Compromisso() {
        return NR_Compromisso;
    }

    public void setCD_Unidade(String CD_Unidade) {
        this.CD_Unidade = CD_Unidade;
    }

    public String getCD_Unidade() {
        return CD_Unidade;
    }

    public void setOID_Parcelamento(String OID_Parcelamento) {
        this.OID_Parcelamento = OID_Parcelamento;
    }

    public String getOID_Parcelamento() {
        return OID_Parcelamento;
    }

    public void setNR_Parcelamento(long NR_Parcelamento) {
        this.NR_Parcelamento = NR_Parcelamento;
    }

    public long getNR_Parcelamento() {
        return NR_Parcelamento;
    }

    public void setOID_Cidade_Destino(long OID_Cidade_Destino) {
        this.OID_Cidade_Destino = OID_Cidade_Destino;
    }

    public long getOID_Cidade_Destino() {
        return OID_Cidade_Destino;
    }

    public void setOID_Veiculo(String OID_Veiculo) {
        this.OID_Veiculo = OID_Veiculo;
    }

    public String getOID_Veiculo() {
        return OID_Veiculo;
    }
}