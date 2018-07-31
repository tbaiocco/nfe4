package com.master.ed;

public class Ocorrencia_Nota_FiscalED extends MasterED {

    private String OID_Nota_Fiscal;
    private String TX_Observacao;
    private String DT_Ocorrencia_Nota_Fiscal;
    private String HR_Ocorrencia_Nota_Fiscal;
    private long OID_Ocorrencia_Nota_Fiscal;
    private long OID_Tipo_Ocorrencia;
    private String OID_Pessoa;
    private String OID_Pessoa_Destinatario;
    private String DT_Emissao;
    private long NR_Nota_Fiscal;
    private String NM_Tipo_Ocorrencia;
    private String NM_Pessoa_Remetente;
    private String NM_Pessoa_Destinatario;
    private String DM_Origem_Ocorrencia;
    private String TX_Observacao_Cliente;
    private String DM_Acesso;
    private String DM_Pendencia;
    private String NM_Pessoa;

    private String DM_Tipo_Nota_Fiscal;

    public String getDesc_DM_Pendencia() {
        if (this.DM_Pendencia.toUpperCase().trim().equals("S"))
            return "Sim";
        else if (this.DM_Pendencia.toUpperCase().trim().equals("N"))
            return "Não";
        else return "Não Informada!";
    }

    public void setOID_Nota_Fiscal(String OID_Nota_Fiscal) {
        this.OID_Nota_Fiscal = OID_Nota_Fiscal;
    }

    public String getOID_Nota_Fiscal() {
        return OID_Nota_Fiscal;
    }

    public void setTX_Observacao(String TX_Observacao) {
        this.TX_Observacao = TX_Observacao;
    }

    public String getTX_Observacao() {
        return TX_Observacao;
    }

    public void setOID_Ocorrencia_Nota_Fiscal(long OID_Ocorrencia_Nota_Fiscal) {
        this.OID_Ocorrencia_Nota_Fiscal = OID_Ocorrencia_Nota_Fiscal;
    }

    public long getOID_Ocorrencia_Nota_Fiscal() {
        return OID_Ocorrencia_Nota_Fiscal;
    }

    public void setDT_Ocorrencia_Nota_Fiscal(String DT_Ocorrencia_Nota_Fiscal) {
        this.DT_Ocorrencia_Nota_Fiscal = DT_Ocorrencia_Nota_Fiscal;
    }

    public String getDT_Ocorrencia_Nota_Fiscal() {
        return DT_Ocorrencia_Nota_Fiscal;
    }

    public void setHR_Ocorrencia_Nota_Fiscal(String HR_Ocorrencia_Nota_Fiscal) {
        this.HR_Ocorrencia_Nota_Fiscal = HR_Ocorrencia_Nota_Fiscal;
    }

    public String getHR_Ocorrencia_Nota_Fiscal() {
        return HR_Ocorrencia_Nota_Fiscal;
    }

    public void setOID_Tipo_Ocorrencia(long OID_Tipo_Ocorrencia) {
        this.OID_Tipo_Ocorrencia = OID_Tipo_Ocorrencia;
    }

    public long getOID_Tipo_Ocorrencia() {
        return OID_Tipo_Ocorrencia;
    }

    public void setOID_Pessoa(String OID_Pessoa) {
        this.OID_Pessoa = OID_Pessoa;
    }

    public String getOID_Pessoa() {
        return OID_Pessoa;
    }

    public void setOID_Pessoa_Destinatario(String OID_Pessoa_Destinatario) {
        this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
    }

    public String getOID_Pessoa_Destinatario() {
        return OID_Pessoa_Destinatario;
    }

    public void setDT_Emissao(String DT_Emissao) {
        this.DT_Emissao = DT_Emissao;
    }

    public String getDT_Emissao() {
        return DT_Emissao;
    }

    public void setNR_Nota_Fiscal(long NR_Nota_Fiscal) {
        this.NR_Nota_Fiscal = NR_Nota_Fiscal;
    }

    public long getNR_Nota_Fiscal() {
        return NR_Nota_Fiscal;
    }

    public void setNM_Tipo_Ocorrencia(String NM_Tipo_Ocorrencia) {
        this.NM_Tipo_Ocorrencia = NM_Tipo_Ocorrencia;
    }

    public String getNM_Tipo_Ocorrencia() {
        return NM_Tipo_Ocorrencia;
    }

    public void setNM_Pessoa_Remetente(String NM_Pessoa_Remetente) {
        this.NM_Pessoa_Remetente = NM_Pessoa_Remetente;
    }

    public String getNM_Pessoa_Remetente() {
        return NM_Pessoa_Remetente;
    }

    public void setNM_Pessoa_Destinatario(String NM_Pessoa_Destinatario) {
        this.NM_Pessoa_Destinatario = NM_Pessoa_Destinatario;
    }

    public String getNM_Pessoa_Destinatario() {
        return NM_Pessoa_Destinatario;
    }

    public void setDM_Origem_Ocorrencia(String DM_Origem_Ocorrencia) {
        this.DM_Origem_Ocorrencia = DM_Origem_Ocorrencia;
    }

    public String getDM_Origem_Ocorrencia() {
        return DM_Origem_Ocorrencia;
    }

    public void setTX_Observacao_Cliente(String TX_Observacao_Cliente) {
        this.TX_Observacao_Cliente = TX_Observacao_Cliente;
    }

    public String getTX_Observacao_Cliente() {
        return TX_Observacao_Cliente;
    }

    public void setDM_Acesso(String DM_Acesso) {
        this.DM_Acesso = DM_Acesso;
    }

    public String getDM_Acesso() {
        return DM_Acesso;
    }

    public void setDM_Pendencia(String DM_Pendencia) {
        this.DM_Pendencia = DM_Pendencia;
    }

    public String getDM_Pendencia() {
        return DM_Pendencia;
    }

    public void setNM_Pessoa(String NM_Pessoa) {
        this.NM_Pessoa = NM_Pessoa;
    }

    public String getNM_Pessoa() {
        return NM_Pessoa;
    }

    public String getDM_Tipo_Nota_Fiscal() {
        return DM_Tipo_Nota_Fiscal;
    }

    public void setDM_Tipo_Nota_Fiscal(String tipo_Nota_Fiscal) {
        DM_Tipo_Nota_Fiscal = tipo_Nota_Fiscal;
    }
}