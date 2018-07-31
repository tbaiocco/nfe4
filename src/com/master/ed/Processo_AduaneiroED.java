package com.master.ed;

public class Processo_AduaneiroED
    extends MasterED {

    private String oid_Processo_Aduaneiro;
    private String oid_Conhecimento;
    private String oid_Pessoa_Remetente;
    private String oid_Pessoa_Destinatario;
    private String DT_Emissao;
    private String HR_Emissao;
    private String NR_Fatura;
    private String NR_Nota_Fiscal;
    private String NR_SD;
    private String NR_RE;
    private String DT_Validade;
    private int oid_Despachante;
    private int oid_Corretora;
    private String DT_Chegada;
    private String DT_Solicitacao_Cruze;
    private String DT_Liberacao;
    private String DT_Lancamento;
    private String DT_Cruze;
    private String DT_Encerramento;
    private String TX_Observacao;
    
    private long NR_Volumes_Observacao;
    private double VL_Nota_Fiscal;
    private double VL_Peso;
    private double VL_Peso_Cubado;
    
    private long oid_Unidade;
    private String CD_Unidade;
    private String NM_Fantasia;
    
    private int oid_Aduana;
    private String CD_Aduana;
	private String NM_Aduana;
    private String NR_Aduana;
    
    private String NM_Pessoa_Remetente;
    private String NM_Pessoa_Destinatario;

    private long NR_Conhecimento;
    private String NM_Conhecimento;
    
    private String NM_Despachante;
    private String NR_CNPJ_CPF_Despachante;
    private String NR_Telefone_Despachante;
    private String NR_Fax_Despachante;
    private String NM_Corretora;
    
    private String DM_Tipo;
    private String NM_Volumes_Observacao;
  
	
    public String getCD_Aduana() {
        return CD_Aduana;
    }
    public void setCD_Aduana(String aduana) {
        CD_Aduana = aduana;
    }
    public String getCD_Unidade() {
        return CD_Unidade;
    }
    public void setCD_Unidade(String unidade) {
        CD_Unidade = unidade;
    }
    public String getDT_Chegada() {
        return DT_Chegada;
    }
    public void setDT_Chegada(String chegada) {
        DT_Chegada = chegada;
    }
    public String getDT_Cruze() {
        return DT_Cruze;
    }
    public void setDT_Cruze(String cruze) {
        DT_Cruze = cruze;
    }
    public String getDT_Emissao() {
        return DT_Emissao;
    }
    public void setDT_Emissao(String emissao) {
        DT_Emissao = emissao;
    }
    public String getDT_Encerramento() {
        return DT_Encerramento;
    }
    public void setDT_Encerramento(String encerramento) {
        DT_Encerramento = encerramento;
    }
    public String getDT_Lancamento() {
        return DT_Lancamento;
    }
    public void setDT_Lancamento(String lancamento) {
        DT_Lancamento = lancamento;
    }
    public String getDT_Liberacao() {
        return DT_Liberacao;
    }
    public void setDT_Liberacao(String liberacao) {
        DT_Liberacao = liberacao;
    }
    public String getDT_Solicitacao_Cruze() {
        return DT_Solicitacao_Cruze;
    }
    public void setDT_Solicitacao_Cruze(String solicitacao_Cruze) {
        DT_Solicitacao_Cruze = solicitacao_Cruze;
    }
    public String getDT_Validade() {
        return DT_Validade;
    }
    public void setDT_Validade(String validade) {
        DT_Validade = validade;
    }
    public String getHR_Emissao() {
        return HR_Emissao;
    }
    public void setHR_Emissao(String emissao) {
        HR_Emissao = emissao;
    }
    public String getNM_Aduana() {
        return NM_Aduana;
    }
    public void setNM_Aduana(String aduana) {
        NM_Aduana = aduana;
    }
    public String getNM_Fantasia() {
        return NM_Fantasia;
    }
    public void setNM_Fantasia(String fantasia) {
        NM_Fantasia = fantasia;
    }
    public String getNM_Pessoa_Destinatario() {
        return NM_Pessoa_Destinatario;
    }
    public void setNM_Pessoa_Destinatario(String pessoa_Destinatario) {
        NM_Pessoa_Destinatario = pessoa_Destinatario;
    }
    public String getNM_Pessoa_Remetente() {
        return NM_Pessoa_Remetente;
    }
    public void setNM_Pessoa_Remetente(String pessoa_Remetente) {
        NM_Pessoa_Remetente = pessoa_Remetente;
    }
    public String getNR_Aduana() {
        return NR_Aduana;
    }
    public void setNR_Aduana(String aduana) {
        NR_Aduana = aduana;
    }
    public long getNR_Conhecimento() {
        return NR_Conhecimento;
    }
    public void setNR_Conhecimento(long conhecimento) {
        NR_Conhecimento = conhecimento;
    }
    public String getNR_Fatura() {
        return NR_Fatura;
    }
    public void setNR_Fatura(String fatura) {
        NR_Fatura = fatura;
    }
    public String getNR_Nota_Fiscal() {
        return NR_Nota_Fiscal;
    }
    public void setNR_Nota_Fiscal(String nota_Fiscal) {
        NR_Nota_Fiscal = nota_Fiscal;
    }
    public String getNR_RE() {
        return NR_RE;
    }
    public void setNR_RE(String nr_re) {
        NR_RE = nr_re;
    }
    public String getNR_SD() {
        return NR_SD;
    }
    public void setNR_SD(String nr_sd) {
        NR_SD = nr_sd;
    }
    public long getNR_Volumes_Observacao() {
        return NR_Volumes_Observacao;
    }
    public void setNR_Volumes_Observacao(long volumes_Observacao) {
        NR_Volumes_Observacao = volumes_Observacao;
    }
    public int getOid_Aduana() {
        return oid_Aduana;
    }
    public void setOid_Aduana(int oid_Aduana) {
        this.oid_Aduana = oid_Aduana;
    }
    public String getOid_Conhecimento() {
        return oid_Conhecimento;
    }
    public void setOid_Conhecimento(String oid_Conhecimento) {
        this.oid_Conhecimento = oid_Conhecimento;
    }
    public int getOid_Corretora() {
        return oid_Corretora;
    }
    public void setOid_Corretora(int oid_Corretora) {
        this.oid_Corretora = oid_Corretora;
    }
    public int getOid_Despachante() {
        return oid_Despachante;
    }
    public void setOid_Despachante(int oid_Despachante) {
        this.oid_Despachante = oid_Despachante;
    }
    public String getOid_Pessoa_Destinatario() {
        return oid_Pessoa_Destinatario;
    }
    public void setOid_Pessoa_Destinatario(String oid_Pessoa_Destinatario) {
        this.oid_Pessoa_Destinatario = oid_Pessoa_Destinatario;
    }
    public String getOid_Pessoa_Remetente() {
        return oid_Pessoa_Remetente;
    }
    public void setOid_Pessoa_Remetente(String oid_Pessoa_Remetente) {
        this.oid_Pessoa_Remetente = oid_Pessoa_Remetente;
    }
    public String getOid_Processo_Aduaneiro() {
        return oid_Processo_Aduaneiro;
    }
    public void setOid_Processo_Aduaneiro(String oid_Processo_Aduaneiro) {
        this.oid_Processo_Aduaneiro = oid_Processo_Aduaneiro;
    }
    public long getOid_Unidade() {
        return oid_Unidade;
    }
    public void setOid_Unidade(long oid_Unidade) {
        this.oid_Unidade = oid_Unidade;
    }
    public String getTX_Observacao() {
        return TX_Observacao;
    }
    public void setTX_Observacao(String observacao) {
        TX_Observacao = observacao;
    }
    public double getVL_Nota_Fiscal() {
        return VL_Nota_Fiscal;
    }
    public void setVL_Nota_Fiscal(double nota_Fiscal) {
        VL_Nota_Fiscal = nota_Fiscal;
    }
    public double getVL_Peso() {
        return VL_Peso;
    }
    public void setVL_Peso(double peso) {
        VL_Peso = peso;
    }
    public double getVL_Peso_Cubado() {
        return VL_Peso_Cubado;
    }
    public void setVL_Peso_Cubado(double peso_Cubado) {
        VL_Peso_Cubado = peso_Cubado;
    }
    public String getNM_Corretora() {
        return NM_Corretora;
    }
    public void setNM_Corretora(String corretora) {
        NM_Corretora = corretora;
    }
    public String getNM_Despachante() {
        return NM_Despachante;
    }
    public void setNM_Despachante(String despachante) {
        NM_Despachante = despachante;
    }
    public String getNR_CNPJ_CPF_Despachante() {
        return NR_CNPJ_CPF_Despachante;
    }
    public void setNR_CNPJ_CPF_Despachante(String despachante) {
        NR_CNPJ_CPF_Despachante = despachante;
    }
    public String getNR_Fax_Despachante() {
        return NR_Fax_Despachante;
    }
    public void setNR_Fax_Despachante(String fax_Despachante) {
        NR_Fax_Despachante = fax_Despachante;
    }
    public String getNR_Telefone_Despachante() {
        return NR_Telefone_Despachante;
    }
    public void setNR_Telefone_Despachante(String telefone_Despachante) {
        NR_Telefone_Despachante = telefone_Despachante;
    }
    public String getNM_Conhecimento() {
        return NM_Conhecimento;
    }
    public void setNM_Conhecimento(String conhecimento) {
        NM_Conhecimento = conhecimento;
    }
    public String getDM_Tipo() {
        return DM_Tipo;
    }
    public void setDM_Tipo(String tipo) {
        DM_Tipo = tipo;
    }
    public String getNM_Volumes_Observacao() {
        return NM_Volumes_Observacao;
    }
    public void setNM_Volumes_Observacao(String volumes_Observacao) {
        NM_Volumes_Observacao = volumes_Observacao;
    }
}
