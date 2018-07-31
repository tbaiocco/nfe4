package com.master.ed;

public class EDI_AuxiliarED extends MasterED {

    // Dados remetente
    private String NR_CNPJ_CPF;
    private String NM_INSCRICAO;
    private String NM_Endereco;
    private String NM_Bairro;
    private String NM_Cidade;
    private String NR_CEP;
    private String CD_Estado;
    private String DT_Embarque;
    private String NM_Razao_Social;
    private long OID_Cidade;
    private String NR_Telefone;

    // Dados Destinatario
    private String NM_Razao_Social_Destinatario;
    private String NR_CNPJ_CPF_Destinatario;
    private String NM_INSCRICAO_Destinatario;
    private String NM_Endereco_Destinatario;
    private String NM_Bairro_Destinatario;
    private String NM_Cidade_Destinatario;
    private String NR_CEP_Destinatario;
    private String CD_Estado_Destinatario;
    private String CD_Area_Frete_Destinatario;
    private long OID_Cidade_Destinatario;

    // Dados Nota Fiscal
    private String CD_Condicao_Frete;
    private String NR_NOTA_FISCAL;
    private String DT_Emissao_Nota_Fiscal;
    private String NM_Natureza_Mercadoria;
    private double NR_Volumes;
    private double VL_Nota_Fiscal;
    private double VL_Peso;

    // Dados Consignatario
    private String NM_Razao_Social_Consignatario;
    private String NR_CNPJ_CPF_Consignatario;
    private String NM_INSCRICAO_Consignatario;
    private String NM_Endereco_Consignatario;
    private String NM_Bairro_Consignatario;
    private String NM_Cidade_Consignatario;
    private String NR_CEP_Consignatario;
    private String CD_Estado_Consignatario;

    // Dados Redespacho
    private String NM_Razao_Social_Redespacho;
    private String NR_CNPJ_CPF_Redespacho;
    private String NM_INSCRICAO_Redespacho;
    private String NM_Endereco_Redespacho;
    private String NM_Bairro_Redespacho;
    private String NM_Cidade_Redespacho;
    private String NR_CEP_Redespacho;
    private String CD_Estado_Redespacho;
    private String CD_Area_Frete_Redespacho;

    // Dados Pagador
    private String NM_Razao_Social_Pagador;
    private String NR_CNPJ_CPF_Pagador;
    private String NM_INSCRICAO_Pagador;
    private String NM_Endereco_Pagador;
    private String NM_Bairro_Pagador;
    private String NM_Cidade_Pagador;
    private String NR_CEP_Pagador;
    private String CD_Estado_Pagador;

    // outros
    private String NM_Arquivo;
    private String CD_Tipo;
    private String NM_Produto;
    private long OID_Produto;
    private String CD_Produto;
    private String NR_Pedido;
    private String NM_Natureza_Operacao;
    private String CD_Companhia;

    private String OID_EDI_Conhecimento;
    private String NR_EDI_Conhecimento;
    private String DM_Responsavel_Cobranca;
    private String DM_Tipo_Pagamento;
    private double PE_Aliquota_ICMS;
    private String TX_Observacao;
    private String DM_Isento_Seguro;
    private String NM_Atendente;
    private String NM_Pessoa_Entrega;
    private long OID_Unidade;
    private String OID_Vendedor;
    private String OID_Tabela_Frete;
    private String CD_Unidade;
    private double VL_Frete;
    private double VL_ICMS;
    private String DM_Tipo_EDI_Conhecimento;
    private String DM_Impresso;
    private String CD_CFO;
    private long OID_Taxa;
    private double VL_FRETE_PESO;
    private double VL_FRETE_VALOR;
    private double VL_SEC_CAT;
    private double VL_PEDAGIO;
    private double VL_DESPACHO;
    private double VL_OUTROS1;
    private double VL_OUTROS2;
    private double VL_TOTAL_FRETE;
    private double VL_BASE_CALCULO_ICMS;
    private String NM_Cidade_CTRC_Origem;
    private String NM_Cidade_CTRC_Destino;
    private String CD_Estado_CTRC_Origem;
    private String CD_Estado_CTRC_Destino;
    private double PE_ALIQUOTA_ICMS;
    private String CD_Referencia;
    private String VL_Nota_Fiscal_formatada;
    private String NM_Fantasia_Unidade;
    private String OID_Nota_Fiscal;
    private String DM_Situacao;
    private long NR_Duplicata;
    private String NM_Serie;
    private long OID_Estado_Destino;
    private long OID_Estado_Origem;
    private long OID_Pessoa;
    private long OID_Pessoa_Destinatario;
    private long OID_Pessoa_Consignatario;
    private long OID_Pessoa_Redespacho;
    private long OID_Pessoa_Pagador;
    private long OID_Modal;
    private long OID_Lote;
    private String NM_Natureza;
    private String DT_Emissao;

    //Duplicata
    private Long nr_Documento;
    private Integer nr_Parcela;
    private String dt_Emissao;
    private Integer nr_Duplicata;
    private Integer oid_Tipo_Documento;
    private Long oid_Unidade;
    private String nr_CNPJ_CPF;
    private String nm_Razao_Social;
    private String cd_Tipo_Documento;
    private String nm_Tipo_Documento;
    private String cd_Vendedor;
    private String nm_Vendedor;
    private String nm_Carteira;
    private String cd_Unidade;
    private String nm_Fantasia;
    private String dm_Numeracao_Automatica;
    private String nr_Proximo_Numero;
    private String oid_Vendedor;
    private String CD_Conta_Corrente;
    private String NM_Banco;
    private String NR_Bancario;
    private String DT_Emissao_Inicial;
    private String DT_Emissao_Final;
    private String NM_Inscricao_Estadual;
    private Integer nr_Duplicata_Final;
    private String CD_Moeda;
    private Integer OID_Moeda;
    private String CD_Banco;
    private String NR_Convenio;
    private String NM_Razao_Social_Empresa;
    private String CD_Empresa_Banco;
    private String CD_Ocorrencia;
    private double vl_Duplicata;
    private double VL_Juro_Mora_Dia;
    private double vl_Desconto_Faturamento;
    private double VL_Multa;
    private double vl_Saldo;
    private double vl_Taxa_Cobranca;

    ///###15052003 Transito
    private long NR_EDI_Conhecimento_Final;
    private long NR_EDI_Conhecimento_Inicial;
    private long OID_Coleta;
    private String DT_Inicial;
    private String DT_Final;
    private String dt_Vencimento;
    private long oid_Carteira;
    private String oid_Pessoa;
    private String OID_Pessoa_Remetente_CTRC;
    private String NR_Serie_CTRC;
    private String NR_Conhecimento;
    private String DT_Emissao_CTRC;
    private double VL_Total_Frete;
    private String DM_Tipo_Conhecimento;
    private String OID_Pessoa_Remetente_NF;
    private String NR_Serie_NF;
    private String NR_Nota_Fiscal;
    private String DT_Emissao_NF;
    private int NR_Linha;

    public String getCD_Area_Frete_Destinatario() {
        return CD_Area_Frete_Destinatario;
    }

    public String getCD_Area_Frete_Redespacho() {
        return CD_Area_Frete_Redespacho;
    }

    public String getCD_CFO() {
        return CD_CFO;
    }

    public String getCD_Condicao_Frete() {
        return CD_Condicao_Frete;
    }

    public String getCD_Estado_Consignatario() {
        return CD_Estado_Consignatario;
    }

    public String getCD_Estado_CTRC_Destino() {
        return CD_Estado_CTRC_Destino;
    }

    public String getCD_Estado_CTRC_Origem() {
        return CD_Estado_CTRC_Origem;
    }

    public String getCD_Estado_Destinatario() {
        return CD_Estado_Destinatario;
    }

    public String getCD_Estado_Pagador() {
        return CD_Estado_Pagador;
    }

    public String getCD_Estado_Redespacho() {
        return CD_Estado_Redespacho;
    }

    public String getCD_Estado() {
        return CD_Estado;
    }

    public String getCD_Referencia() {
        return CD_Referencia;
    }

    public String getCD_Tipo() {
        return CD_Tipo;
    }

    public String getCD_Unidade() {
        return CD_Unidade;
    }

    public String getDM_Impresso() {
        return DM_Impresso;
    }

    public String getDM_Isento_Seguro() {
        return DM_Isento_Seguro;
    }

    public String getDM_Responsavel_Cobranca() {
        return DM_Responsavel_Cobranca;
    }

    public String getDM_Situacao() {
        return DM_Situacao;
    }

    public String getDM_Tipo_EDI_Conhecimento() {
        return DM_Tipo_EDI_Conhecimento;
    }

    public String getDM_Tipo_Pagamento() {
        return DM_Tipo_Pagamento;
    }

    public String getDT_Embarque() {
        return DT_Embarque;
    }

    public String getDT_Emissao() {
        return DT_Emissao;
    }

    public String getDT_Emissao_Nota_Fiscal() {
        return DT_Emissao_Nota_Fiscal;
    }

    public String getNM_Arquivo() {
        return NM_Arquivo;
    }

    public String getNM_Atendente() {
        return NM_Atendente;
    }

    public String getNM_Bairro_Consignatario() {
        return NM_Bairro_Consignatario;
    }

    public String getNM_Bairro_Destinatario() {
        return NM_Bairro_Destinatario;
    }

    public String getNM_Bairro_Pagador() {
        return NM_Bairro_Pagador;
    }

    public String getNM_Bairro_Redespacho() {
        return NM_Bairro_Redespacho;
    }

    public String getNM_Cidade_Consignatario() {
        return NM_Cidade_Consignatario;
    }

    public String getNM_Cidade_CTRC_Destino() {
        return NM_Cidade_CTRC_Destino;
    }

    public String getNM_Cidade_CTRC_Origem() {
        return NM_Cidade_CTRC_Origem;
    }

    public String getNM_Cidade_Destinatario() {
        return NM_Cidade_Destinatario;
    }

    public String getNM_Cidade_Pagador() {
        return NM_Cidade_Pagador;
    }

    public String getNM_Cidade_Redespacho() {
        return NM_Cidade_Redespacho;
    }

    public String getNM_Cidade() {
        return NM_Cidade;
    }

    public String getNM_Endereco_Consignatario() {
        return NM_Endereco_Consignatario;
    }

    public String getNM_Endereco_Destinatario() {
        return NM_Endereco_Destinatario;
    }

    public String getNM_Endereco_Pagador() {
        return NM_Endereco_Pagador;
    }

    public String getNM_Endereco_Redespacho() {
        return NM_Endereco_Redespacho;
    }

    public String getNM_Endereco() {
        return NM_Endereco;
    }

    public String getNM_Fantasia_Unidade() {
        return NM_Fantasia_Unidade;
    }

    public String getNM_INSCRICAO_Consignatario() {
        return NM_INSCRICAO_Consignatario;
    }

    public String getNM_INSCRICAO_Destinatario() {
        return NM_INSCRICAO_Destinatario;
    }

    public String getNM_INSCRICAO_Pagador() {
        return NM_INSCRICAO_Pagador;
    }

    public String getNM_INSCRICAO_Redespacho() {
        return NM_INSCRICAO_Redespacho;
    }

    public String getNM_INSCRICAO() {
        return NM_INSCRICAO;
    }

    public String getNM_Natureza() {
        return NM_Natureza;
    }

    public String getNM_Natureza_Mercadoria() {
        return NM_Natureza_Mercadoria;
    }

    public String getNM_Pessoa_Entrega() {
        return NM_Pessoa_Entrega;
    }

    public String getNM_Razao_Social_Consignatario() {
        return NM_Razao_Social_Consignatario;
    }

    public String getNM_Razao_Social_Destinatario() {
        return NM_Razao_Social_Destinatario;
    }

    public String getNM_Razao_Social_Pagador() {
        return NM_Razao_Social_Pagador;
    }

    public String getNM_Razao_Social_Redespacho() {
        return NM_Razao_Social_Redespacho;
    }

    public String getNM_Razao_Social() {
        return NM_Razao_Social;
    }

    public String getNM_Serie() {
        return NM_Serie;
    }

    public String getNR_CEP_Consignatario() {
        return NR_CEP_Consignatario;
    }

    public String getNR_CEP_Destinatario() {
        return NR_CEP_Destinatario;
    }

    public String getNR_CEP_Pagador() {
        return NR_CEP_Pagador;
    }

    public String getNR_CEP_Redespacho() {
        return NR_CEP_Redespacho;
    }

    public String getNR_CEP() {
        return NR_CEP;
    }

    public String getNR_CNPJ_CPF_Consignatario() {
        return NR_CNPJ_CPF_Consignatario;
    }

    public String getNR_CNPJ_CPF_Destinatario() {
        return NR_CNPJ_CPF_Destinatario;
    }

    public String getNR_CNPJ_CPF_Pagador() {
        return NR_CNPJ_CPF_Pagador;
    }

    public String getNR_CNPJ_CPF_Redespacho() {
        return NR_CNPJ_CPF_Redespacho;
    }

    public String getNR_CNPJ_CPF() {
        return NR_CNPJ_CPF;
    }

    public long getNR_Duplicata() {
        return NR_Duplicata;
    }

    public long getNR_EDI_Conhecimento_Final() {
        return NR_EDI_Conhecimento_Final;
    }

    public long getNR_EDI_Conhecimento_Inicial() {
        return NR_EDI_Conhecimento_Inicial;
    }

    public String getNR_NOTA_FISCAL() {
        return NR_NOTA_FISCAL;
    }

    public double getNR_Volumes() {
        return NR_Volumes;
    }

    public long getOID_Cidade() {
        return OID_Cidade;
    }

    public String getOID_EDI_Conhecimento() {
        return OID_EDI_Conhecimento;
    }

    public long getOID_Estado_Destino() {
        return OID_Estado_Destino;
    }

    public long getOID_Estado_Origem() {
        return OID_Estado_Origem;
    }

    public long getOID_Modal() {
        return OID_Modal;
    }

    public String getOID_Nota_Fiscal() {
        return OID_Nota_Fiscal;
    }

    public long getOID_Pessoa() {
        return OID_Pessoa;
    }

    public long getOID_Pessoa_Consignatario() {
        return OID_Pessoa_Consignatario;
    }

    public long getOID_Pessoa_Destinatario() {
        return OID_Pessoa_Destinatario;
    }

    public long getOID_Pessoa_Pagador() {
        return OID_Pessoa_Pagador;
    }

    public long getOID_Pessoa_Redespacho() {
        return OID_Pessoa_Redespacho;
    }

    public long getOID_Produto() {
        return OID_Produto;
    }

    public String getOID_Tabela_Frete() {
        return OID_Tabela_Frete;
    }

    public long getOID_Taxa() {
        return OID_Taxa;
    }

    public long getOID_Unidade() {
        return OID_Unidade;
    }

    public String getOID_Vendedor() {
        return OID_Vendedor;
    }

    public double getPE_Aliquota_ICMS() {
        return PE_Aliquota_ICMS;
    }

    public double getPE_ALIQUOTA_ICMS() {
        return PE_ALIQUOTA_ICMS;
    }

    public String getTX_Observacao() {
        return TX_Observacao;
    }

    public double getVL_BASE_CALCULO_ICMS() {
        return VL_BASE_CALCULO_ICMS;
    }

    public double getVL_DESPACHO() {
        return VL_DESPACHO;
    }

    public double getVL_Frete() {
        return VL_Frete;
    }

    public double getVL_FRETE_PESO() {
        return VL_FRETE_PESO;
    }

    public double getVL_FRETE_VALOR() {
        return VL_FRETE_VALOR;
    }

    public double getVL_ICMS() {
        return VL_ICMS;
    }

    public double getVL_Nota_Fiscal() {
        return VL_Nota_Fiscal;
    }

    public double getVL_OUTROS1() {
        return VL_OUTROS1;
    }

    public double getVL_OUTROS2() {
        return VL_OUTROS2;
    }

    public double getVL_PEDAGIO() {
        return VL_PEDAGIO;
    }

    public double getVL_Peso() {
        return VL_Peso;
    }

    public double getVL_SEC_CAT() {
        return VL_SEC_CAT;
    }

    public double getVL_TOTAL_FRETE() {
        return VL_TOTAL_FRETE;
    }

    public void setCD_Area_Frete_Destinatario(String CD_Area_Frete_Destinatario) {
        this.CD_Area_Frete_Destinatario = CD_Area_Frete_Destinatario;
    }

    public void setCD_Area_Frete_Redespacho(String CD_Area_Frete_Redespacho) {
        this.CD_Area_Frete_Redespacho = CD_Area_Frete_Redespacho;
    }

    public void setCD_CFO(String CD_CFO) {
        this.CD_CFO = CD_CFO;
    }

    public void setCD_Condicao_Frete(String CD_Condicao_Frete) {
        this.CD_Condicao_Frete = CD_Condicao_Frete;
    }

    public void setCD_Estado_Consignatario(String CD_Estado_Consignatario) {
        this.CD_Estado_Consignatario = CD_Estado_Consignatario;
    }

    public void setCD_Estado_CTRC_Destino(String CD_Estado_CTRC_Destino) {
        this.CD_Estado_CTRC_Destino = CD_Estado_CTRC_Destino;
    }

    public void setCD_Estado_CTRC_Origem(String CD_Estado_CTRC_Origem) {
        this.CD_Estado_CTRC_Origem = CD_Estado_CTRC_Origem;
    }

    public void setCD_Estado_Destinatario(String CD_Estado_Destinatario) {
        this.CD_Estado_Destinatario = CD_Estado_Destinatario;
    }

    public void setCD_Estado_Pagador(String CD_Estado_Pagador) {
        this.CD_Estado_Pagador = CD_Estado_Pagador;
    }

    public void setCD_Estado_Redespacho(String CD_Estado_Redespacho) {
        this.CD_Estado_Redespacho = CD_Estado_Redespacho;
    }

    public void setCD_Estado(String CD_Estado) {
        this.CD_Estado = CD_Estado;
    }

    public void setCD_Referencia(String CD_Referencia) {
        this.CD_Referencia = CD_Referencia;
    }

    public void setCD_Tipo(String CD_Tipo) {
        this.CD_Tipo = CD_Tipo;
    }

    public void setCD_Unidade(String CD_Unidade) {
        this.CD_Unidade = CD_Unidade;
    }

    public void setDM_Impresso(String DM_Impresso) {
        this.DM_Impresso = DM_Impresso;
    }

    public void setDM_Isento_Seguro(String DM_Isento_Seguro) {
        this.DM_Isento_Seguro = DM_Isento_Seguro;
    }

    public void setDM_Responsavel_Cobranca(String DM_Responsavel_Cobranca) {
        this.DM_Responsavel_Cobranca = DM_Responsavel_Cobranca;
    }

    public void setDM_Situacao(String DM_Situacao) {
        this.DM_Situacao = DM_Situacao;
    }

    public void setDM_Tipo_EDI_Conhecimento(String DM_Tipo_EDI_Conhecimento) {
        this.DM_Tipo_EDI_Conhecimento = DM_Tipo_EDI_Conhecimento;
    }

    public void setDM_Tipo_Pagamento(String DM_Tipo_Pagamento) {
        this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
    }

    public void setDT_Embarque(String DT_Embarque) {
        this.DT_Embarque = DT_Embarque;
    }

    public void setDT_Emissao(String DT_Emissao) {
        this.DT_Emissao = DT_Emissao;
    }

    public void setDT_Emissao_Nota_Fiscal(String DT_Emissao_Nota_Fiscal) {
        this.DT_Emissao_Nota_Fiscal = DT_Emissao_Nota_Fiscal;
    }

    public void setNM_Arquivo(String NM_Arquivo) {
        this.NM_Arquivo = NM_Arquivo;
    }

    public void setNM_Atendente(String NM_Atendente) {
        this.NM_Atendente = NM_Atendente;
    }

    public void setNM_Bairro_Consignatario(String NM_Bairro_Consignatario) {
        this.NM_Bairro_Consignatario = NM_Bairro_Consignatario;
    }

    public void setNM_Bairro_Destinatario(String NM_Bairro_Destinatario) {
        this.NM_Bairro_Destinatario = NM_Bairro_Destinatario;
    }

    public void setNM_Bairro_Pagador(String NM_Bairro_Pagador) {
        this.NM_Bairro_Pagador = NM_Bairro_Pagador;
    }

    public void setNM_Bairro_Redespacho(String NM_Bairro_Redespacho) {
        this.NM_Bairro_Redespacho = NM_Bairro_Redespacho;
    }

    public void setNM_Cidade_Consignatario(String NM_Cidade_Consignatario) {
        this.NM_Cidade_Consignatario = NM_Cidade_Consignatario;
    }

    public void setNM_Cidade_CTRC_Destino(String NM_Cidade_CTRC_Destino) {
        this.NM_Cidade_CTRC_Destino = NM_Cidade_CTRC_Destino;
    }

    public void setNM_Cidade_CTRC_Origem(String NM_Cidade_CTRC_Origem) {
        this.NM_Cidade_CTRC_Origem = NM_Cidade_CTRC_Origem;
    }

    public void setNM_Cidade_Destinatario(String NM_Cidade_Destinatario) {
        this.NM_Cidade_Destinatario = NM_Cidade_Destinatario;
    }

    public void setNM_Cidade_Pagador(String NM_Cidade_Pagador) {
        this.NM_Cidade_Pagador = NM_Cidade_Pagador;
    }

    public void setNM_Cidade_Redespacho(String NM_Cidade_Redespacho) {
        this.NM_Cidade_Redespacho = NM_Cidade_Redespacho;
    }

    public void setNM_Cidade(String NM_Cidade) {
        this.NM_Cidade = NM_Cidade;
    }

    public void setNM_Endereco_Consignatario(String NM_Endereco_Consignatario) {
        this.NM_Endereco_Consignatario = NM_Endereco_Consignatario;
    }

    public void setNM_Endereco_Destinatario(String NM_Endereco_Destinatario) {
        this.NM_Endereco_Destinatario = NM_Endereco_Destinatario;
    }

    public void setNM_Endereco_Pagador(String NM_Endereco_Pagador) {
        this.NM_Endereco_Pagador = NM_Endereco_Pagador;
    }

    public void setNM_Endereco_Redespacho(String NM_Endereco_Redespacho) {
        this.NM_Endereco_Redespacho = NM_Endereco_Redespacho;
    }

    public void setNM_Endereco(String NM_Endereco) {
        this.NM_Endereco = NM_Endereco;
    }

    public void setNM_Fantasia_Unidade(String NM_Fantasia_Unidade) {
        this.NM_Fantasia_Unidade = NM_Fantasia_Unidade;
    }

    public void setNM_INSCRICAO_Consignatario(String NM_INSCRICAO_Consignatario) {
        this.NM_INSCRICAO_Consignatario = NM_INSCRICAO_Consignatario;
    }

    public void setNM_INSCRICAO_Destinatario(String NM_INSCRICAO_Destinatario) {
        this.NM_INSCRICAO_Destinatario = NM_INSCRICAO_Destinatario;
    }

    public void setNM_INSCRICAO_Pagador(String NM_INSCRICAO_Pagador) {
        this.NM_INSCRICAO_Pagador = NM_INSCRICAO_Pagador;
    }

    public void setNM_INSCRICAO_Redespacho(String NM_INSCRICAO_Redespacho) {
        this.NM_INSCRICAO_Redespacho = NM_INSCRICAO_Redespacho;
    }

    public void setNM_INSCRICAO(String NM_INSCRICAO) {
        this.NM_INSCRICAO = NM_INSCRICAO;
    }

    public void setNM_Natureza(String NM_Natureza) {
        this.NM_Natureza = NM_Natureza;
    }

    public void setNM_Natureza_Mercadoria(String NM_Natureza_Mercadoria) {
        this.NM_Natureza_Mercadoria = NM_Natureza_Mercadoria;
    }

    public void setNM_Pessoa_Entrega(String NM_Pessoa_Entrega) {
        this.NM_Pessoa_Entrega = NM_Pessoa_Entrega;
    }

    public void setNM_Razao_Social_Consignatario(String NM_Razao_Social_Consignatario) {
        this.NM_Razao_Social_Consignatario = NM_Razao_Social_Consignatario;
    }

    public void setNM_Razao_Social_Destinatario(String NM_Razao_Social_Destinatario) {
        this.NM_Razao_Social_Destinatario = NM_Razao_Social_Destinatario;
    }

    public void setNM_Razao_Social_Pagador(String NM_Razao_Social_Pagador) {
        this.NM_Razao_Social_Pagador = NM_Razao_Social_Pagador;
    }

    public void setNM_Razao_Social_Redespacho(String NM_Razao_Social_Redespacho) {
        this.NM_Razao_Social_Redespacho = NM_Razao_Social_Redespacho;
    }

    public void setNM_Razao_Social(String NM_Razao_Social) {
        this.NM_Razao_Social = NM_Razao_Social;
    }

    public void setNM_Serie(String NM_Serie) {
        this.NM_Serie = NM_Serie;
    }

    public void setNR_CEP_Consignatario(String NR_CEP_Consignatario) {
        this.NR_CEP_Consignatario = NR_CEP_Consignatario;
    }

    public void setNR_CEP_Destinatario(String NR_CEP_Destinatario) {
        this.NR_CEP_Destinatario = NR_CEP_Destinatario;
    }

    public void setNR_CEP_Pagador(String NR_CEP_Pagador) {
        this.NR_CEP_Pagador = NR_CEP_Pagador;
    }

    public void setNR_CEP_Redespacho(String NR_CEP_Redespacho) {
        this.NR_CEP_Redespacho = NR_CEP_Redespacho;
    }

    public void setNR_CEP(String NR_CEP) {
        this.NR_CEP = NR_CEP;
    }

    public void setNR_CNPJ_CPF_Consignatario(String NR_CNPJ_CPF_Consignatario) {
        this.NR_CNPJ_CPF_Consignatario = NR_CNPJ_CPF_Consignatario;
    }

    public void setNR_CNPJ_CPF_Destinatario(String NR_CNPJ_CPF_Destinatario) {
        this.NR_CNPJ_CPF_Destinatario = NR_CNPJ_CPF_Destinatario;
    }

    public void setNR_CNPJ_CPF_Pagador(String NR_CNPJ_CPF_Pagador) {
        this.NR_CNPJ_CPF_Pagador = NR_CNPJ_CPF_Pagador;
    }

    public void setNR_CNPJ_CPF_Redespacho(String NR_CNPJ_CPF_Redespacho) {
        this.NR_CNPJ_CPF_Redespacho = NR_CNPJ_CPF_Redespacho;
    }

    public void setNR_CNPJ_CPF(String NR_CNPJ_CPF) {
        this.NR_CNPJ_CPF = NR_CNPJ_CPF;
    }

    public void setNR_Duplicata(long NR_Duplicata) {
        this.NR_Duplicata = NR_Duplicata;
    }

    public void setNR_EDI_Conhecimento_Final(long NR_EDI_Conhecimento_Final) {
        this.NR_EDI_Conhecimento_Final = NR_EDI_Conhecimento_Final;
    }

    public void setNR_EDI_Conhecimento_Inicial(long NR_EDI_Conhecimento_Inicial) {
        this.NR_EDI_Conhecimento_Inicial = NR_EDI_Conhecimento_Inicial;
    }

    public void setNR_NOTA_FISCAL(String NR_NOTA_FISCAL) {
        this.NR_NOTA_FISCAL = NR_NOTA_FISCAL;
    }

    public void setNR_Volumes(double NR_Volumes) {
        this.NR_Volumes = NR_Volumes;
    }

    public void setOID_Cidade(long OID_Cidade) {
        this.OID_Cidade = OID_Cidade;
    }

    public void setOID_Estado_Destino(long OID_Estado_Destino) {
        this.OID_Estado_Destino = OID_Estado_Destino;
    }

    public void setOID_Estado_Origem(long OID_Estado_Origem) {
        this.OID_Estado_Origem = OID_Estado_Origem;
    }

    public void setOID_Modal(long OID_Modal) {
        this.OID_Modal = OID_Modal;
    }

    public void setOID_Nota_Fiscal(String OID_Nota_Fiscal) {
        this.OID_Nota_Fiscal = OID_Nota_Fiscal;
    }

    public void setOID_Pessoa(long OID_Pessoa) {
        this.OID_Pessoa = OID_Pessoa;
    }

    public void setOID_Pessoa_Consignatario(long OID_Pessoa_Consignatario) {
        this.OID_Pessoa_Consignatario = OID_Pessoa_Consignatario;
    }

    public void setOID_Pessoa_Destinatario(long OID_Pessoa_Destinatario) {
        this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
    }

    public void setOID_Pessoa_Pagador(long OID_Pessoa_Pagador) {
        this.OID_Pessoa_Pagador = OID_Pessoa_Pagador;
    }

    public void setOID_Pessoa_Redespacho(long OID_Pessoa_Redespacho) {
        this.OID_Pessoa_Redespacho = OID_Pessoa_Redespacho;
    }

    public void setOID_Produto(long OID_Produto) {
        this.OID_Produto = OID_Produto;
    }

    public void setOID_Tabela_Frete(String OID_Tabela_Frete) {
        this.OID_Tabela_Frete = OID_Tabela_Frete;
    }

    public void setOID_Taxa(long OID_Taxa) {
        this.OID_Taxa = OID_Taxa;
    }

    public void setOID_Unidade(long OID_Unidade) {
        this.OID_Unidade = OID_Unidade;
    }

    public void setOID_Vendedor(String OID_Vendedor) {
        this.OID_Vendedor = OID_Vendedor;
    }

    public void setPE_Aliquota_ICMS(double PE_Aliquota_ICMS) {
        this.PE_Aliquota_ICMS = PE_Aliquota_ICMS;
    }

    public void setPE_ALIQUOTA_ICMS(double PE_ALIQUOTA_ICMS) {
        this.PE_ALIQUOTA_ICMS = PE_ALIQUOTA_ICMS;
    }

    public void setTX_Observacao(String TX_Observacao) {
        this.TX_Observacao = TX_Observacao;
    }

    public void setVL_BASE_CALCULO_ICMS(double VL_BASE_CALCULO_ICMS) {
        this.VL_BASE_CALCULO_ICMS = VL_BASE_CALCULO_ICMS;
    }

    public void setVL_DESPACHO(double VL_DESPACHO) {
        this.VL_DESPACHO = VL_DESPACHO;
    }

    public void setVL_Frete(double VL_Frete) {
        this.VL_Frete = VL_Frete;
    }

    public void setVL_FRETE_PESO(double VL_FRETE_PESO) {
        this.VL_FRETE_PESO = VL_FRETE_PESO;
    }

    public void setVL_FRETE_VALOR(double VL_FRETE_VALOR) {
        this.VL_FRETE_VALOR = VL_FRETE_VALOR;
    }

    public void setVL_ICMS(double VL_ICMS) {
        this.VL_ICMS = VL_ICMS;
    }

    public void setVL_Nota_Fiscal(double VL_Nota_Fiscal) {
        this.VL_Nota_Fiscal = VL_Nota_Fiscal;
    }

    public void setVL_OUTROS1(double VL_OUTROS1) {
        this.VL_OUTROS1 = VL_OUTROS1;
    }

    public void setVL_OUTROS2(double VL_OUTROS2) {
        this.VL_OUTROS2 = VL_OUTROS2;
    }

    public void setVL_PEDAGIO(double VL_PEDAGIO) {
        this.VL_PEDAGIO = VL_PEDAGIO;
    }

    public void setVL_Peso(double VL_Peso) {
        this.VL_Peso = VL_Peso;
    }

    public void setVL_SEC_CAT(double VL_SEC_CAT) {
        this.VL_SEC_CAT = VL_SEC_CAT;
    }

    public void setVL_TOTAL_FRETE(double VL_TOTAL_FRETE) {
        this.VL_TOTAL_FRETE = VL_TOTAL_FRETE;
    }

    public String getVL_Nota_Fiscal_formatada() {
        return VL_Nota_Fiscal_formatada;
    }

    public void setVL_Nota_Fiscal_formatada(String VL_Nota_Fiscal_formatada) {
        this.VL_Nota_Fiscal_formatada = VL_Nota_Fiscal_formatada;
    }

    public String getNM_Produto() {
        return NM_Produto;
    }

    public void setNM_Produto(String NM_Produto) {
        this.NM_Produto = NM_Produto;
    }

    public String getCD_Produto() {
        return CD_Produto;
    }

    public void setCD_Produto(String CD_Produto) {
        this.CD_Produto = CD_Produto;
    }

    public long getOID_Lote() {
        return OID_Lote;
    }

    public void setOID_Lote(long OID_Lote) {
        this.OID_Lote = OID_Lote;
    }

    public void setOID_EDI_Conhecimento(String OID_EDI_Conhecimento) {
        this.OID_EDI_Conhecimento = OID_EDI_Conhecimento;
    }

    public String getNR_EDI_Conhecimento() {
        return NR_EDI_Conhecimento;
    }

    public void setNR_EDI_Conhecimento(String NR_EDI_Conhecimento) {
        this.NR_EDI_Conhecimento = NR_EDI_Conhecimento;
    }

    public String getNM_Bairro() {
        return NM_Bairro;
    }

    public void setNM_Bairro(String NM_Bairro) {
        this.NM_Bairro = NM_Bairro;
    }

    public String getNM_Natureza_Operacao() {
        return NM_Natureza_Operacao;
    }

    public void setNM_Natureza_Operacao(String NM_Natureza_Operacao) {
        this.NM_Natureza_Operacao = NM_Natureza_Operacao;
    }

    public String getNR_Pedido() {
        return NR_Pedido;
    }

    public void setNR_Pedido(String NR_Pedido) {
        this.NR_Pedido = NR_Pedido;
    }

    public String getCD_Companhia() {
        return CD_Companhia;
    }

    public void setCD_Companhia(String CD_Companhia) {
        this.CD_Companhia = CD_Companhia;
    }

    public long getOID_Cidade_Destinatario() {
        return OID_Cidade_Destinatario;
    }

    public void setOID_Cidade_Destinatario(long OID_Cidade_Destinatario) {
        this.OID_Cidade_Destinatario = OID_Cidade_Destinatario;
    }

    public void setOID_Coleta(long OID_Coleta) {
        this.OID_Coleta = OID_Coleta;
    }

    public long getOID_Coleta() {
        return OID_Coleta;
    }

    public void setDT_Inicial(String DT_Inicial) {
        this.DT_Inicial = DT_Inicial;
    }

    public String getDT_Inicial() {
        return DT_Inicial;
    }

    public void setDT_Final(String DT_Final) {
        this.DT_Final = DT_Final;
    }

    public String getDT_Final() {
        return DT_Final;
    }

    public String getCD_Banco() {
        return CD_Banco;
    }

    public String getCD_Conta_Corrente() {
        return CD_Conta_Corrente;
    }

    public String getCD_Empresa_Banco() {
        return CD_Empresa_Banco;
    }

    public void setCD_Empresa_Banco(String CD_Empresa_Banco) {
        this.CD_Empresa_Banco = CD_Empresa_Banco;
    }

    public void setCD_Conta_Corrente(String CD_Conta_Corrente) {
        this.CD_Conta_Corrente = CD_Conta_Corrente;
    }

    public void setCD_Banco(String CD_Banco) {
        this.CD_Banco = CD_Banco;
    }

    public String getCD_Moeda() {
        return CD_Moeda;
    }

    public void setCD_Moeda(String CD_Moeda) {
        this.CD_Moeda = CD_Moeda;
    }

    public void setCD_Ocorrencia(String CD_Ocorrencia) {
        this.CD_Ocorrencia = CD_Ocorrencia;
    }

    public String getCD_Ocorrencia() {
        return CD_Ocorrencia;
    }

    public String getCd_Tipo_Documento() {
        return cd_Tipo_Documento;
    }

    public void setCd_Tipo_Documento(String cd_Tipo_Documento) {
        this.cd_Tipo_Documento = cd_Tipo_Documento;
    }

    public void setCd_Unidade(String cd_Unidade) {
        this.cd_Unidade = cd_Unidade;
    }

    public void setCd_Vendedor(String cd_Vendedor) {
        this.cd_Vendedor = cd_Vendedor;
    }

    public String getCd_Unidade() {
        return cd_Unidade;
    }

    public String getCd_Vendedor() {
        return cd_Vendedor;
    }

    public void setDm_Numeracao_Automatica(String dm_Numeracao_Automatica) {
        this.dm_Numeracao_Automatica = dm_Numeracao_Automatica;
    }

    public String getDm_Numeracao_Automatica() {
        return dm_Numeracao_Automatica;
    }

    public void setDt_Emissao(String dt_Emissao) {
        this.dt_Emissao = dt_Emissao;
    }

    public void setDT_Emissao_Final(String DT_Emissao_Final) {
        this.DT_Emissao_Final = DT_Emissao_Final;
    }

    public void setDT_Emissao_Inicial(String DT_Emissao_Inicial) {
        this.DT_Emissao_Inicial = DT_Emissao_Inicial;
    }

    public String getDt_Emissao() {
        return dt_Emissao;
    }

    public String getDT_Emissao_Final() {
        return DT_Emissao_Final;
    }

    public String getDT_Emissao_Inicial() {
        return DT_Emissao_Inicial;
    }

    public void setNM_Banco(String NM_Banco) {
        this.NM_Banco = NM_Banco;
    }

    public void setNm_Carteira(String nm_Carteira) {
        this.nm_Carteira = nm_Carteira;
    }

    public String getNM_Banco() {
        return NM_Banco;
    }

    public String getNm_Carteira() {
        return nm_Carteira;
    }

    public void setNm_Fantasia(String nm_Fantasia) {
        this.nm_Fantasia = nm_Fantasia;
    }

    public String getNm_Fantasia() {
        return nm_Fantasia;
    }

    public String getNM_Inscricao_Estadual() {
        return NM_Inscricao_Estadual;
    }

    public void setNM_Inscricao_Estadual(String NM_Inscricao_Estadual) {
        this.NM_Inscricao_Estadual = NM_Inscricao_Estadual;
    }

    public void setNm_Razao_Social(String nm_Razao_Social) {
        this.nm_Razao_Social = nm_Razao_Social;
    }

    public String getNm_Razao_Social() {
        return nm_Razao_Social;
    }

    public String getNM_Razao_Social_Empresa() {
        return NM_Razao_Social_Empresa;
    }

    public void setNM_Razao_Social_Empresa(String NM_Razao_Social_Empresa) {
        this.NM_Razao_Social_Empresa = NM_Razao_Social_Empresa;
    }

    public void setNm_Tipo_Documento(String nm_Tipo_Documento) {
        this.nm_Tipo_Documento = nm_Tipo_Documento;
    }

    public void setNm_Vendedor(String nm_Vendedor) {
        this.nm_Vendedor = nm_Vendedor;
    }

    public void setNR_Bancario(String NR_Bancario) {
        this.NR_Bancario = NR_Bancario;
    }

    public String getNm_Tipo_Documento() {
        return nm_Tipo_Documento;
    }

    public String getNm_Vendedor() {
        return nm_Vendedor;
    }

    public String getNR_Bancario() {
        return NR_Bancario;
    }

    public void setNr_CNPJ_CPF(String nr_CNPJ_CPF) {
        this.nr_CNPJ_CPF = nr_CNPJ_CPF;
    }

    public String getNr_CNPJ_CPF() {
        return nr_CNPJ_CPF;
    }

    public String getNR_Convenio() {
        return NR_Convenio;
    }

    public Long getNr_Documento() {
        return nr_Documento;
    }

    public void setNR_Convenio(String NR_Convenio) {
        this.NR_Convenio = NR_Convenio;
    }

    public void setNr_Documento(Long nr_Documento) {
        this.nr_Documento = nr_Documento;
    }

    public void setNr_Duplicata(Integer nr_Duplicata) {
        this.nr_Duplicata = nr_Duplicata;
    }

    public void setNr_Duplicata_Final(Integer nr_Duplicata_Final) {
        this.nr_Duplicata_Final = nr_Duplicata_Final;
    }

    public Integer getNr_Duplicata() {
        return nr_Duplicata;
    }

    public Integer getNr_Duplicata_Final() {
        return nr_Duplicata_Final;
    }

    public void setNr_Parcela(Integer nr_Parcela) {
        this.nr_Parcela = nr_Parcela;
    }

    public void setNr_Proximo_Numero(String nr_Proximo_Numero) {
        this.nr_Proximo_Numero = nr_Proximo_Numero;
    }

    public Integer getNr_Parcela() {
        return nr_Parcela;
    }

    public String getNr_Proximo_Numero() {
        return nr_Proximo_Numero;
    }

    public Integer getOID_Moeda() {
        return OID_Moeda;
    }

    public void setOID_Moeda(Integer OID_Moeda) {
        this.OID_Moeda = OID_Moeda;
    }

    public void setOid_Tipo_Documento(Integer oid_Tipo_Documento) {
        this.oid_Tipo_Documento = oid_Tipo_Documento;
    }

    public Integer getOid_Tipo_Documento() {
        return oid_Tipo_Documento;
    }

    public Long getOid_Unidade() {
        return oid_Unidade;
    }

    public String getOid_Vendedor() {
        return oid_Vendedor;
    }

    public void setOid_Unidade(Long oid_Unidade) {
        this.oid_Unidade = oid_Unidade;
    }

    public void setOid_Vendedor(String oid_Vendedor) {
        this.oid_Vendedor = oid_Vendedor;
    }

    public double getVl_Desconto_Faturamento() {
        return vl_Desconto_Faturamento;
    }

    public double getVl_Duplicata() {
        return vl_Duplicata;
    }

    public void setVl_Desconto_Faturamento(double vl_Desconto_Faturamento) {
        this.vl_Desconto_Faturamento = vl_Desconto_Faturamento;
    }

    public void setVl_Duplicata(double vl_Duplicata) {
        this.vl_Duplicata = vl_Duplicata;
    }

    public void setVL_Juro_Mora_Dia(double VL_Juro_Mora_Dia) {
        this.VL_Juro_Mora_Dia = VL_Juro_Mora_Dia;
    }

    public void setVL_Multa(double VL_Multa) {
        this.VL_Multa = VL_Multa;
    }

    public double getVL_Juro_Mora_Dia() {
        return VL_Juro_Mora_Dia;
    }

    public double getVL_Multa() {
        return VL_Multa;
    }

    public void setVl_Saldo(double vl_Saldo) {
        this.vl_Saldo = vl_Saldo;
    }

    public double getVl_Saldo() {
        return vl_Saldo;
    }

    public double getVl_Taxa_Cobranca() {
        return vl_Taxa_Cobranca;
    }

    public void setVl_Taxa_Cobranca(double vl_Taxa_Cobranca) {
        this.vl_Taxa_Cobranca = vl_Taxa_Cobranca;
    }

    public void setDt_Vencimento(String dt_Vencimento) {
        this.dt_Vencimento = dt_Vencimento;
    }

    public String getDt_Vencimento() {
        return dt_Vencimento;
    }

    public void setOid_Carteira(long oid_Carteira) {
        this.oid_Carteira = oid_Carteira;
    }

    public long getOid_Carteira() {
        return oid_Carteira;
    }

    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }

    public String getOid_Pessoa() {
        return oid_Pessoa;
    }

    public void setOID_Pessoa_Remetente_CTRC(String OID_Pessoa_Remetente_CTRC) {
        this.OID_Pessoa_Remetente_CTRC = OID_Pessoa_Remetente_CTRC;
    }

    public String getOID_Pessoa_Remetente_CTRC() {
        return OID_Pessoa_Remetente_CTRC;
    }

    public void setNR_Serie_CTRC(String NR_Serie_CTRC) {
        this.NR_Serie_CTRC = NR_Serie_CTRC;
    }

    public String getNR_Serie_CTRC() {
        return NR_Serie_CTRC;
    }

    public void setNR_Conhecimento(String NR_Conhecimento) {
        this.NR_Conhecimento = NR_Conhecimento;
    }

    public String getNR_Conhecimento() {
        return NR_Conhecimento;
    }

    public void setDT_Emissao_CTRC(String DT_Emissao_CTRC) {
        this.DT_Emissao_CTRC = DT_Emissao_CTRC;
    }

    public String getDT_Emissao_CTRC() {
        return DT_Emissao_CTRC;
    }

    public void setVL_Total_Frete(double VL_Total_Frete) {
        this.VL_Total_Frete = VL_Total_Frete;
    }

    public double getVL_Total_Frete() {
        return VL_Total_Frete;
    }

    public void setDM_Tipo_Conhecimento(String DM_Tipo_Conhecimento) {
        this.DM_Tipo_Conhecimento = DM_Tipo_Conhecimento;
    }

    public String getDM_Tipo_Conhecimento() {
        return DM_Tipo_Conhecimento;
    }

    public void setOID_Pessoa_Remetente_NF(String OID_Pessoa_Remetente_NF) {
        this.OID_Pessoa_Remetente_NF = OID_Pessoa_Remetente_NF;
    }

    public String getOID_Pessoa_Remetente_NF() {
        return OID_Pessoa_Remetente_NF;
    }

    public void setNR_Serie_NF(String NR_Serie_NF) {
        this.NR_Serie_NF = NR_Serie_NF;
    }

    public String getNR_Serie_NF() {
        return NR_Serie_NF;
    }

    public void setNR_Nota_Fiscal(String NR_Nota_Fiscal) {
        this.NR_Nota_Fiscal = NR_Nota_Fiscal;
    }

    public String getNR_Nota_Fiscal() {
        return NR_Nota_Fiscal;
    }

    public void setDT_Emissao_NF(String DT_Emissao_NF) {
        this.DT_Emissao_NF = DT_Emissao_NF;
    }

    public String getDT_Emissao_NF() {
        return DT_Emissao_NF;
    }

    public void setNR_Linha(int NR_Linha) {
        this.NR_Linha = NR_Linha;
    }

    public int getNR_Linha() {
        return NR_Linha;
    }

    public String getNR_Telefone() {
        return NR_Telefone;
    }

    public void setNR_Telefone(String telefone) {
        NR_Telefone = telefone;
    }
}