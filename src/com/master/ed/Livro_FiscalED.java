package com.master.ed;

public class Livro_FiscalED extends RelatorioBaseED {

    public Livro_FiscalED() {
        super();
    }

    public Livro_FiscalED(String nota_Fiscal, String especie) {
        super();
        OID_Nota_Fiscal = nota_Fiscal;
        CD_Especie = especie;
    }

    private String NM_Razao_Social;
    private String NM_Unidade;
    private String NR_CNPJ_CPF;
    private String dt_Stamp;
    private long OID_Unidade;
    private long OID_Natureza_Operacao;
    private String DM_Situacao;
    private String DT_Emissao;
    private String HR_Pedido;
    private String DT_Entrada;
    private double VL_Base_Calculo;
    private double PE_Aliquota;
    private long OID_Tipo_Documento;
    private double VL_Contabil;
    private String DT_Inicial;
    private String DT_Final;
    private String TX_Observacao;
    private double VL_Imposto;
    private double VL_Imposto_Creditado;
    private double VL_Outro;
    private String NR_Documento;
    private String OID_Nota_Fiscal;
    private String DM_Tipo_Livro;
    private String CD_Natureza_Operacao;
    private long OID_Livro_Fiscal;
    private String OID_Conhecimento;
    private String DM_Tipo_Documento;
    private String CD_Estado;
    private String DM_Tipo_Operacao;
    private String CD_Unidade;
    private String NM_Natureza_Operacao;
    private String DM_Relatorio;
    private String NM_Serie_Documento;
    private String NM_Modelo_Documento;
    private String DM_Tipo_Imposto;
    private String OID_Pessoa_Emitente;
    private String NM_Tipo_Operacao;
    private long NR_Pagina_Inicial;
    private String CD_Especie;
    private String TX_Mensagem;

    public static String getNM_Tipo_Imposto(String dmTipo_Imposto)
    {
        if ("I".equals(dmTipo_Imposto) || "A".equals(dmTipo_Imposto) || "O".equals(dmTipo_Imposto))
            return "ICMS";
        else if ("Q".equals(dmTipo_Imposto))
            return "ISSQN";
        else if ("P".equals(dmTipo_Imposto))
            return "IPI";
        else return "Não encontrado";
    }
    public String getNM_Tipo_Imposto()
    {
        return getNM_Tipo_Imposto(this.DM_Tipo_Imposto);
    }
    public String getNM_Razao_Social() {
        return NM_Razao_Social;
    }
    public void setNM_Razao_Social(String NM_Razao_Social) {
        this.NM_Razao_Social = NM_Razao_Social;
    }
    public void setNM_Unidade(String NM_Unidade) {
        this.NM_Unidade = NM_Unidade;
    }
    public String getNM_Unidade() {
        return NM_Unidade;
    }
    public void setNR_CNPJ_CPF(String NR_CNPJ_CPF) {
        this.NR_CNPJ_CPF = NR_CNPJ_CPF;
    }
    public String getNR_CNPJ_CPF() {
        return NR_CNPJ_CPF;
    }
    public void setOID_Unidade(long OID_Unidade) {
        this.OID_Unidade = OID_Unidade;
    }
    public long getOID_Unidade() {
        return OID_Unidade;
    }
    public void setOID_Natureza_Operacao(long OID_Natureza_Operacao) {
        this.OID_Natureza_Operacao = OID_Natureza_Operacao;
    }
    public long getOID_Natureza_Operacao() {
        return OID_Natureza_Operacao;
    }
    public void setPE_Aliquota(double PE_Aliquota) {
        this.PE_Aliquota = PE_Aliquota;
    }
    public double getPE_Aliquota() {
        return PE_Aliquota;
    }
    public void setDM_Situacao(String DM_Situacao) {
        this.DM_Situacao = DM_Situacao;
    }
    public String getDM_Situacao() {
        return DM_Situacao;
    }
    public void setDT_Emissao(String DT_Emissao) {
        this.DT_Emissao = DT_Emissao;
    }
    public String getDT_Emissao() {
        return DT_Emissao;
    }
    public void setHR_Pedido(String HR_Pedido) {
        this.HR_Pedido = HR_Pedido;
    }
    public String getHR_Pedido() {
        return HR_Pedido;
    }
    public void setDT_Entrada(String DT_Entrada) {
        this.DT_Entrada = DT_Entrada;
    }
    public String getDT_Entrada() {
        return DT_Entrada;
    }
    public void setVL_Base_Calculo(double VL_Base_Calculo) {
        this.VL_Base_Calculo = VL_Base_Calculo;
    }
    public double getVL_Base_Calculo() {
        return VL_Base_Calculo;
    }
    public void setOID_Tipo_Documento(long OID_Tipo_Documento) {
        this.OID_Tipo_Documento = OID_Tipo_Documento;
    }
    public long getOID_Tipo_Documento() {
        return OID_Tipo_Documento;
    }
    public void setOID_Nota_Fiscal(String OID_Nota_Fiscal) {
        this.OID_Nota_Fiscal = OID_Nota_Fiscal;
    }
    public String getOID_Nota_Fiscal() {
        return OID_Nota_Fiscal;
    }
    public void setNR_Documento(String NR_Documento) {
        this.NR_Documento = NR_Documento;
    }
    public String getNR_Documento() {
        return NR_Documento;
    }
    public void setVL_Contabil(double VL_Contabil) {
        this.VL_Contabil = VL_Contabil;
    }
    public double getVL_Contabil() {
        return VL_Contabil;
    }
    public String getDT_Final() {
        return DT_Final;
    }
    public String getDT_Inicial() {
        return DT_Inicial;
    }
    public void setDT_Final(String DT_Final) {
        this.DT_Final = DT_Final;
    }
    public void setDT_Inicial(String DT_Inicial) {
        this.DT_Inicial = DT_Inicial;
    }
    public void setDt_Stamp(String dt_Stamp) {
        this.dt_Stamp = dt_Stamp;
    }
    public String getDt_Stamp() {
        return dt_Stamp;
    }
    public void setTX_Observacao(String TX_Observacao) {
        this.TX_Observacao = TX_Observacao;
    }
    public String getTX_Observacao() {
        return TX_Observacao;
    }
    public void setVL_Imposto(double VL_Imposto) {
        this.VL_Imposto = VL_Imposto;
    }
    public double getVL_Imposto() {
        return VL_Imposto;
    }
    public void setDM_Tipo_Livro(String DM_Tipo_Livro) {
        this.DM_Tipo_Livro = DM_Tipo_Livro;
    }
    public String getDM_Tipo_Livro() {
        return DM_Tipo_Livro;
    }
    public void setCD_Natureza_Operacao(String CD_Natureza_Operacao) {
        this.CD_Natureza_Operacao = CD_Natureza_Operacao;
    }
    public String getCD_Natureza_Operacao() {
        return CD_Natureza_Operacao;
    }
    public void setOID_Livro_Fiscal(long OID_Livro_Fiscal) {
        this.OID_Livro_Fiscal = OID_Livro_Fiscal;
    }
    public long getOID_Livro_Fiscal() {
        return OID_Livro_Fiscal;
    }
    public void setOID_Conhecimento(String OID_Conhecimento) {
        this.OID_Conhecimento = OID_Conhecimento;
    }
    public String getOID_Conhecimento() {
        return OID_Conhecimento;
    }
    public void setDM_Tipo_Documento(String DM_Tipo_Documento) {
        this.DM_Tipo_Documento = DM_Tipo_Documento;
    }
    public String getDM_Tipo_Documento() {
        return DM_Tipo_Documento;
    }
    public void setCD_Estado(String CD_Estado) {
        this.CD_Estado = CD_Estado;
    }
    public String getCD_Estado() {
        return CD_Estado;
    }
    public void setDM_Tipo_Operacao(String DM_Tipo_Operacao) {
        this.DM_Tipo_Operacao = DM_Tipo_Operacao;
    }
    public String getDM_Tipo_Operacao() {
        return DM_Tipo_Operacao;
    }
    public void setCD_Unidade(String CD_Unidade) {
        this.CD_Unidade = CD_Unidade;
    }
    public String getCD_Unidade() {
        return CD_Unidade;
    }
    public void setNM_Natureza_Operacao(String NM_Natureza_Operacao) {
        this.NM_Natureza_Operacao = NM_Natureza_Operacao;
    }
    public String getNM_Natureza_Operacao() {
        return NM_Natureza_Operacao;
    }
    public void setDM_Relatorio(String DM_Relatorio) {
        this.DM_Relatorio = DM_Relatorio;
    }
    public String getDM_Relatorio() {
        return DM_Relatorio;
    }
    public double getVL_Imposto_Creditado() {
        return VL_Imposto_Creditado;
    }
    public void setVL_Imposto_Creditado(double VL_Imposto_Creditado) {
        this.VL_Imposto_Creditado = VL_Imposto_Creditado;
    }
    public void setNM_Serie_Documento(String NM_Serie_Documento) {
        this.NM_Serie_Documento = NM_Serie_Documento;
    }
    public String getNM_Serie_Documento() {
        return NM_Serie_Documento;
    }
    public void setNM_Modelo_Documento(String NM_Modelo_Documento) {
        this.NM_Modelo_Documento = NM_Modelo_Documento;
    }
    public String getNM_Modelo_Documento() {
        return NM_Modelo_Documento;
    }
    public void setDM_Tipo_Imposto(String DM_Tipo_Imposto) {
        this.DM_Tipo_Imposto = DM_Tipo_Imposto;
    }
    public String getDM_Tipo_Imposto() {
        return DM_Tipo_Imposto;
    }
    public void setOID_Pessoa_Emitente(String OID_Pessoa_Emitente) {
        this.OID_Pessoa_Emitente = OID_Pessoa_Emitente;
    }
    public String getOID_Pessoa_Emitente() {
        return OID_Pessoa_Emitente;
    }
    public void setNM_Tipo_Operacao(String NM_Tipo_Operacao) {
        this.NM_Tipo_Operacao = NM_Tipo_Operacao;
    }
    public String getNM_Tipo_Operacao() {
        return NM_Tipo_Operacao;
    }
    public void setNR_Pagina_Inicial(long NR_Pagina_Inicial) {
        this.NR_Pagina_Inicial = NR_Pagina_Inicial;
    }
    public long getNR_Pagina_Inicial() {
        return NR_Pagina_Inicial;
    }
    public void setCD_Especie(String CD_Especie) {
        this.CD_Especie = CD_Especie;
    }
    public String getCD_Especie() {
        return CD_Especie;
    }
    public String getTX_Mensagem() {
        return TX_Mensagem;
    }
    public void setTX_Mensagem(String mensagem) {
        TX_Mensagem = mensagem;
    }
    public double getVL_Outro() {
        return VL_Outro;
    }
    public void setVL_Outro(double outro) {
        VL_Outro = outro;
    }
}