package com.master.ed;

import javax.servlet.http.*;

public class SeguroED extends RelatorioBaseED {

    private String OID_Conhecimento;

    private String DT_Inicial;
    private String DT_Final;
    private String oid_Pessoa;
    private long oid_Empresa;
    private long oid_Unidade;
    private long oid_Seguradora;


    private long NR_Conhecimento;
    private String NM_Serie;
    private String DM_Responsavel_Cobranca;
    private String DM_Tipo_Pagamento;
    private double PE_Aliquota_ICMS;
    private String TX_Observacao;
    private String DM_Isento_Seguro;
    private String NM_Atendente;
    private String NM_Natureza;
    private String DT_Previsao_Entrega;
    private String HR_Previsao_Entrega;
    private String NM_Pessoa_Entrega;
    private String DT_Emissao;
    private String OID_Pessoa;
    private String OID_Pessoa_Destinatario;
    private String OID_Pessoa_Consignatario;
    private long OID_Modal;
    private long OID_Unidade;
    private String OID_Vendedor;
    private long OID_Cidade;
    private long OID_Cidade_Destino;
    private String OID_Tabela_Frete;
    private String OID_Pessoa_Pagador;
    private long OID_Produto;
    private long OID_Coleta;
    private String NM_Pessoa_Remetente;
    private String NM_Pessoa_Destinatario;
    private String NM_Pessoa_Consignatario;
    private String NM_Pessoa_Pagador;
    private String CD_Unidade;
    private double VL_Peso;
    private double VL_Peso_Cubado;
    private double VL_Nota_Fiscal;
  private double VL_Nota_Fiscal_Inicial;
  private double VL_Nota_Fiscal_Final;
    private double VL_Frete;
    private double VL_ICMS;
    private long VL_Volumes;
    private String DM_Tipo_Conhecimento;
    private String DM_Impresso;
    private long OID_Estado_Origem;
    private long OID_Estado_Destino;
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
    private String NM_Endereco_Remetente;
    private String NM_Cidade_Remetente;
    private String CD_Estado_Remetente;
    private String NM_INSCRICAO_Remetente;
    private String NM_Endereco_Destinatario;
    private String NM_Cidade_Destinatario;
    private String CD_Estado_Destinatario;
    private String NM_INSCRICAO_Destinatario;
    private String NM_Endereco_Consignatario;
    private String NM_Cidade_Consignatario;
    private String CD_Estado_Consignatario;
    private String NM_INSCRICAO_Consignatario;
    private String NM_Cidade_CTRC_Origem;
    private String NM_Cidade_CTRC_Destino;
    private String CD_Estado_CTRC_Origem;
    private String CD_Estado_CTRC_Destino;
    private double PE_ALIQUOTA_ICMS;
    private String NM_Razao_Social_Pagador;
    private String NR_VOLUMES_Itens;
    private String NM_Produto;
    private String CD_Referencia;
    private String NR_NOTA_FISCAL;
    private String dt_emissao_nota;
    private String nr_peso;
    private String nr_peso_cubado;
    private String VL_Nota_Fiscal_CTRC;
    private String dt_Emissao_Inicial;
    private String dt_Emissao_Final;
    private String dm_Situacao_Cobranca;
    private String NM_Fantasia_Unidade;
    private String OID_Nota_Fiscal;
    private String DM_Tipo_Embarque;
    private String DM_Situacao;
    private String OID_Pessoa_Redespacho;
    private String NM_Cidade_Redespacho;
    private String NM_Endereco_Redespacho;
    private String NM_INSCRICAO_Redespacho;
    private String NM_Pessoa_Redespacho;
    private String NR_CNPJ_CPF_Redespacho;
    private long NR_Duplicata;
    private long NR_Conhecimento_Final;
    private long NR_Conhecimento_Inicial;
    private String DM_Tipo_Produto;
    private String DM_Tipo_Tabela_Frete;
    private String DM_Tipo_Coleta;
    private String DM_Tipo_Entrega;
    private long OID_Empresa;
    private String OID_Pessoa_Entregadora;
    private long OID_Lote;
    private long OID_Acerto;
    private long OID_Usuario;
    private String DM_Situacao_Entrega;
    private String DM_Frete_Recebido;
    private String DM_Frete_Emitido;
    private String DM_Tipo_Remessa;
    private String DM_Origem;
    private String DM_Destino;
    private long OID_Origem;
    private long OID_Destino;
    private double VL_Total_Custo;
    private String DM_Tipo_Transporte;
    private String OID_Veiculo;
    private String DM_Tipo_Comissao;
    private String DM_Relatorio;
    private String DM_Conhecimento_Varias_Notas_Fiscais;
    private String DM_Tipo_Impressao;
    //Atributo inclu�do para a impress�o do relat�rio de ICMS
    private String DM_Imprime_Cancelados;
    private long NR_Pagina_Inicial;
    private double VL_Custo_Entrega;
    private String OID_Carreta;
    private String OID_Carreta2;
    private String DT_Entrega;
    private double NR_Peso;
    private double NR_Peso_Cubado;
    private double NR_Volumes;
    private double PE_Carga_Expressa;
    private String DM_Layout;
    private String NM_Lote_Faturamento;
    private String NR_Transacao_Pedagio;
    private String DM_Tipo_Documento;
    private String DM_Tipo_Cobranca;
    private String DM_Tipo_Desconto_Pedagio;
    private double PE_Comissao_Acerto;
    private String DM_Situacao_Cliente;


    private double vl_aliquota_rctrc = 0;
    private double vl_aliquota_rctr_dc = 0;
    private double vl_aliquota_rctr_vi = 0;
    private double vl_aliquota_rcta = 0;

    private double pe_aliquota_rctrc = 0;
    private double pe_aliquota_rctr_dc = 0;
    private double pe_aliquota_rctr_vi = 0;
    private double pe_aliquota_rcta = 0;

    private double VL_Seguro1_Total = 0;
    private double VL_Seguro2_Total = 0;
    private double VL_Nota_Fiscal_Total = 0;


public SeguroED() {
        super();
    }

    public SeguroED(HttpServletResponse response, String nomeRelatorio) {
        super(response, nomeRelatorio);
    }


    public void setOID_Conhecimento(String OID_Conhecimento) {
        this.OID_Conhecimento = OID_Conhecimento;
    }
    public String getOID_Conhecimento() {
        return OID_Conhecimento;
    }
    public void setNR_Conhecimento(long NR_Conhecimento) {
        this.NR_Conhecimento = NR_Conhecimento;
    }
    public long getNR_Conhecimento() {
        return NR_Conhecimento;
    }
    public void setNM_Serie(String NM_Serie) {
        this.NM_Serie = NM_Serie;
    }
    public String getNM_Serie() {
        return NM_Serie;
    }
    public void setDM_Responsavel_Cobranca(String DM_Responsavel_Cobranca) {
        this.DM_Responsavel_Cobranca = DM_Responsavel_Cobranca;
    }
    public String getDM_Responsavel_Cobranca() {
        return DM_Responsavel_Cobranca;
    }
    public void setDM_Tipo_Pagamento(String DM_Tipo_Pagamento) {
        this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
    }
    public String getDM_Tipo_Pagamento() {
        return DM_Tipo_Pagamento;
    }
    public void setPE_Aliquota_ICMS(double PE_Aliquota_ICMS) {
        this.PE_Aliquota_ICMS = PE_Aliquota_ICMS;
    }
    public double getPE_Aliquota_ICMS() {
        return PE_Aliquota_ICMS;
    }
    public void setTX_Observacao(String TX_Observacao) {
        this.TX_Observacao = TX_Observacao;
    }
    public String getTX_Observacao() {
        return TX_Observacao;
    }
    public void setDM_Isento_Seguro(String DM_Isento_Seguro) {
        this.DM_Isento_Seguro = DM_Isento_Seguro;
    }
    public String getDM_Isento_Seguro() {
        return DM_Isento_Seguro;
    }
    public void setNM_Atendente(String NM_Atendente) {
        this.NM_Atendente = NM_Atendente;
    }
    public String getNM_Atendente() {
        return NM_Atendente;
    }
    public void setNM_Natureza(String NM_Natureza) {
        this.NM_Natureza = NM_Natureza;
    }
    public String getNM_Natureza() {
        return NM_Natureza;
    }
    public void setDT_Previsao_Entrega(String DT_Previsao_Entrega) {
        this.DT_Previsao_Entrega = DT_Previsao_Entrega;
    }
    public String getDT_Previsao_Entrega() {
        return DT_Previsao_Entrega;
    }
    public void setHR_Previsao_Entrega(String HR_Previsao_Entrega) {
        this.HR_Previsao_Entrega = HR_Previsao_Entrega;
    }
    public String getHR_Previsao_Entrega() {
        return HR_Previsao_Entrega;
    }
    public void setNM_Pessoa_Entrega(String NM_Pessoa_Entrega) {
        this.NM_Pessoa_Entrega = NM_Pessoa_Entrega;
    }
    public String getNM_Pessoa_Entrega() {
        return NM_Pessoa_Entrega;
    }
    public void setDT_Emissao(String DT_Emissao) {
        this.DT_Emissao = DT_Emissao;
    }
    public String getDT_Emissao() {
        return DT_Emissao;
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
    public void setOID_Pessoa_Consignatario(String OID_Pessoa_Consignatario) {
        this.OID_Pessoa_Consignatario = OID_Pessoa_Consignatario;
    }
    public String getOID_Pessoa_Consignatario() {
        return OID_Pessoa_Consignatario;
    }
    public void setOID_Modal(long OID_Modal) {
        this.OID_Modal = OID_Modal;
    }
    public long getOID_Modal() {
        return OID_Modal;
    }
    public void setOID_Unidade(long OID_Unidade) {
        this.OID_Unidade = OID_Unidade;
    }
    public long getOID_Unidade() {
        return OID_Unidade;
    }
    public void setOID_Vendedor(String OID_Vendedor) {
        this.OID_Vendedor = OID_Vendedor;
    }
    public String getOID_Vendedor() {
        return OID_Vendedor;
    }
    public void setOID_Cidade(long OID_Cidade) {
        this.OID_Cidade = OID_Cidade;
    }
    public long getOID_Cidade() {
        return OID_Cidade;
    }
    public void setOID_Cidade_Destino(long OID_Cidade_Destino) {
        this.OID_Cidade_Destino = OID_Cidade_Destino;
    }
    public long getOID_Cidade_Destino() {
        return OID_Cidade_Destino;
    }
    public void setOID_Tabela_Frete(String OID_Tabela_Frete) {
        this.OID_Tabela_Frete = OID_Tabela_Frete;
    }
    public String getOID_Tabela_Frete() {
        return OID_Tabela_Frete;
    }
    public void setOID_Pessoa_Pagador(String OID_Pessoa_Pagador) {
        this.OID_Pessoa_Pagador = OID_Pessoa_Pagador;
    }
    public String getOID_Pessoa_Pagador() {
        return OID_Pessoa_Pagador;
    }
    public void setOID_Produto(long OID_Produto) {
        this.OID_Produto = OID_Produto;
    }
    public long getOID_Produto() {
        return OID_Produto;
    }
    public void setOID_Coleta(long OID_Coleta) {
        this.OID_Coleta = OID_Coleta;
    }
    public long getOID_Coleta() {
        return OID_Coleta;
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
    public void setNM_Pessoa_Consignatario(String NM_Pessoa_Consignatario) {
        this.NM_Pessoa_Consignatario = NM_Pessoa_Consignatario;
    }
    public String getNM_Pessoa_Consignatario() {
        return NM_Pessoa_Consignatario;
    }
    public void setCD_Unidade(String CD_Unidade) {
        this.CD_Unidade = CD_Unidade;
    }
    public String getCD_Unidade() {
        return CD_Unidade;
    }
    public void setVL_Peso(double VL_Peso) {
        this.VL_Peso = VL_Peso;
    }
    public double getVL_Peso() {
        return VL_Peso;
    }
    public void setVL_Peso_Cubado(double VL_Peso_Cubado) {
        this.VL_Peso_Cubado = VL_Peso_Cubado;
    }
    public double getVL_Peso_Cubado() {
        return VL_Peso_Cubado;
    }
    public void setVL_Nota_Fiscal(double VL_Nota_Fiscal) {
        this.VL_Nota_Fiscal = VL_Nota_Fiscal;
    }
    public double getVL_Nota_Fiscal() {
        return VL_Nota_Fiscal;
    }
    public void setVL_Frete(double VL_Frete) {
        this.VL_Frete = VL_Frete;
    }
    public double getVL_Frete() {
        return VL_Frete;
    }
    public void setVL_ICMS(double VL_ICMS) {
        this.VL_ICMS = VL_ICMS;
    }
    public double getVL_ICMS() {
        return VL_ICMS;
    }
    public void setVL_Volumes(long VL_Volumes) {
        this.VL_Volumes = VL_Volumes;
    }
    public long getVL_Volumes() {
        return VL_Volumes;
    }
    public void setDM_Tipo_Conhecimento(String DM_Tipo_Conhecimento) {
        this.DM_Tipo_Conhecimento = DM_Tipo_Conhecimento;
    }
    public String getDM_Tipo_Conhecimento() {
        return DM_Tipo_Conhecimento;
    }
    public void setDM_Impresso(String DM_Impresso) {
        this.DM_Impresso = DM_Impresso;
    }
    public String getDM_Impresso() {
        return DM_Impresso;
    }
    public void setOID_Estado_Origem(long OID_Estado_Origem) {
        this.OID_Estado_Origem = OID_Estado_Origem;
    }
    public long getOID_Estado_Origem() {
        return OID_Estado_Origem;
    }
    public void setOID_Estado_Destino(long OID_Estado_Destino) {
        this.OID_Estado_Destino = OID_Estado_Destino;
    }
    public long getOID_Estado_Destino() {
        return OID_Estado_Destino;
    }
    public void setCD_CFO(String CD_CFO) {
        this.CD_CFO = CD_CFO;
    }
    public String getCD_CFO() {
        return CD_CFO;
    }
    public void setOID_Taxa(long OID_Taxa) {
        this.OID_Taxa = OID_Taxa;
    }
    public long getOID_Taxa() {
        return OID_Taxa;
    }
    public void setVL_FRETE_PESO(double VL_FRETE_PESO) {
        this.VL_FRETE_PESO = VL_FRETE_PESO;
    }
    public double getVL_FRETE_PESO() {
        return VL_FRETE_PESO;
    }
    public void setVL_FRETE_VALOR(double VL_FRETE_VALOR) {
        this.VL_FRETE_VALOR = VL_FRETE_VALOR;
    }
    public double getVL_FRETE_VALOR() {
        return VL_FRETE_VALOR;
    }
    public void setVL_SEC_CAT(double VL_SEC_CAT) {
        this.VL_SEC_CAT = VL_SEC_CAT;
    }
    public double getVL_SEC_CAT() {
        return VL_SEC_CAT;
    }
    public void setVL_PEDAGIO(double VL_PEDAGIO) {
        this.VL_PEDAGIO = VL_PEDAGIO;
    }
    public double getVL_PEDAGIO() {
        return VL_PEDAGIO;
    }
    public void setVL_DESPACHO(double VL_DESPACHO) {
        this.VL_DESPACHO = VL_DESPACHO;
    }
    public double getVL_DESPACHO() {
        return VL_DESPACHO;
    }
    public void setVL_OUTROS1(double VL_OUTROS1) {
        this.VL_OUTROS1 = VL_OUTROS1;
    }
    public double getVL_OUTROS1() {
        return VL_OUTROS1;
    }
    public void setVL_OUTROS2(double VL_OUTROS2) {
        this.VL_OUTROS2 = VL_OUTROS2;
    }
    public double getVL_OUTROS2() {
        return VL_OUTROS2;
    }
    public void setVL_TOTAL_FRETE(double VL_TOTAL_FRETE) {
        this.VL_TOTAL_FRETE = VL_TOTAL_FRETE;
    }
    public double getVL_TOTAL_FRETE() {
        return VL_TOTAL_FRETE;
    }
    public void setVL_BASE_CALCULO_ICMS(double VL_BASE_CALCULO_ICMS) {
        this.VL_BASE_CALCULO_ICMS = VL_BASE_CALCULO_ICMS;
    }
    public double getVL_BASE_CALCULO_ICMS() {
        return VL_BASE_CALCULO_ICMS;
    }
    public void setNM_Endereco_Remetente(String NM_Endereco_Remetente) {
        this.NM_Endereco_Remetente = NM_Endereco_Remetente;
    }
    public String getNM_Endereco_Remetente() {
        return NM_Endereco_Remetente;
    }
    public void setNM_Cidade_Remetente(String NM_Cidade_Remetente) {
        this.NM_Cidade_Remetente = NM_Cidade_Remetente;
    }
    public String getNM_Cidade_Remetente() {
        return NM_Cidade_Remetente;
    }
    public void setCD_Estado_Remetente(String CD_Estado_Remetente) {
        this.CD_Estado_Remetente = CD_Estado_Remetente;
    }
    public String getCD_Estado_Remetente() {
        return CD_Estado_Remetente;
    }
    public void setNM_INSCRICAO_Remetente(String NM_INSCRICAO_Remetente) {
        this.NM_INSCRICAO_Remetente = NM_INSCRICAO_Remetente;
    }
    public String getNM_INSCRICAO_Remetente() {
        return NM_INSCRICAO_Remetente;
    }
    public void setNM_Endereco_Destinatario(String NM_Endereco_Destinatario) {
        this.NM_Endereco_Destinatario = NM_Endereco_Destinatario;
    }
    public String getNM_Endereco_Destinatario() {
        return NM_Endereco_Destinatario;
    }
    public void setNM_Cidade_Destinatario(String NM_Cidade_Destinatario) {
        this.NM_Cidade_Destinatario = NM_Cidade_Destinatario;
    }
    public String getNM_Cidade_Destinatario() {
        return NM_Cidade_Destinatario;
    }
    public void setCD_Estado_Destinatario(String CD_Estado_Destinatario) {
        this.CD_Estado_Destinatario = CD_Estado_Destinatario;
    }
    public String getCD_Estado_Destinatario() {
        return CD_Estado_Destinatario;
    }
    public void setNM_INSCRICAO_Destinatario(String NM_INSCRICAO_Destinatario) {
        this.NM_INSCRICAO_Destinatario = NM_INSCRICAO_Destinatario;
    }
    public String getNM_INSCRICAO_Destinatario() {
        return NM_INSCRICAO_Destinatario;
    }
    public void setNM_Endereco_Consignatario(String NM_Endereco_Consignatario) {
        this.NM_Endereco_Consignatario = NM_Endereco_Consignatario;
    }
    public String getNM_Endereco_Consignatario() {
        return NM_Endereco_Consignatario;
    }
    public void setNM_Cidade_Consignatario(String NM_Cidade_Consignatario) {
        this.NM_Cidade_Consignatario = NM_Cidade_Consignatario;
    }
    public String getNM_Cidade_Consignatario() {
        return NM_Cidade_Consignatario;
    }
    public void setCD_Estado_Consignatario(String CD_Estado_Consignatario) {
        this.CD_Estado_Consignatario = CD_Estado_Consignatario;
    }
    public String getCD_Estado_Consignatario() {
        return CD_Estado_Consignatario;
    }
    public void setNM_INSCRICAO_Consignatario(String NM_INSCRICAO_Consignatario) {
        this.NM_INSCRICAO_Consignatario = NM_INSCRICAO_Consignatario;
    }
    public String getNM_INSCRICAO_Consignatario() {
        return NM_INSCRICAO_Consignatario;
    }
    public void setNM_Cidade_CTRC_Origem(String NM_Cidade_CTRC_Origem) {
        this.NM_Cidade_CTRC_Origem = NM_Cidade_CTRC_Origem;
    }
    public String getNM_Cidade_CTRC_Origem() {
        return NM_Cidade_CTRC_Origem;
    }
    public void setNM_Cidade_CTRC_Destino(String NM_Cidade_CTRC_Destino) {
        this.NM_Cidade_CTRC_Destino = NM_Cidade_CTRC_Destino;
    }
    public String getNM_Cidade_CTRC_Destino() {
        return NM_Cidade_CTRC_Destino;
    }
    public void setCD_Estado_CTRC_Origem(String CD_Estado_CTRC_Origem) {
        this.CD_Estado_CTRC_Origem = CD_Estado_CTRC_Origem;
    }
    public String getCD_Estado_CTRC_Origem() {
        return CD_Estado_CTRC_Origem;
    }
    public void setCD_Estado_CTRC_Destino(String CD_Estado_CTRC_Destino) {
        this.CD_Estado_CTRC_Destino = CD_Estado_CTRC_Destino;
    }
    public String getCD_Estado_CTRC_Destino() {
        return CD_Estado_CTRC_Destino;
    }
    public void setPE_ALIQUOTA_ICMS(double PE_ALIQUOTA_ICMS) {
        this.PE_ALIQUOTA_ICMS = PE_ALIQUOTA_ICMS;
    }
    public double getPE_ALIQUOTA_ICMS() {
        return PE_ALIQUOTA_ICMS;
    }
    public void setNM_Razao_Social_Pagador(String NM_Razao_Social_Pagador) {
        this.NM_Razao_Social_Pagador = NM_Razao_Social_Pagador;
    }
    public String getNM_Razao_Social_Pagador() {
        return NM_Razao_Social_Pagador;
    }
    public void setNR_VOLUMES_Itens(String NR_VOLUMES_Itens) {
        this.NR_VOLUMES_Itens = NR_VOLUMES_Itens;
    }
    public String getNR_VOLUMES_Itens() {
        return NR_VOLUMES_Itens;
    }
    public void setNM_Produto(String NM_Produto) {
        this.NM_Produto = NM_Produto;
    }
    public String getNM_Produto() {
        return NM_Produto;
    }
    public void setCD_Referencia(String CD_Referencia) {
        this.CD_Referencia = CD_Referencia;
    }
    public String getCD_Referencia() {
        return CD_Referencia;
    }
    public void setNR_NOTA_FISCAL(String NR_NOTA_FISCAL) {
        this.NR_NOTA_FISCAL = NR_NOTA_FISCAL;
    }
    public String getNR_NOTA_FISCAL() {
        return NR_NOTA_FISCAL;
    }
    public void setDt_emissao_nota(String dt_emissao_nota) {
        this.dt_emissao_nota = dt_emissao_nota;
    }
    public String getDt_emissao_nota() {
        return dt_emissao_nota;
    }
    public void setNr_peso(String nr_peso) {
        this.nr_peso = nr_peso;
    }
    public String getNr_peso() {
        return nr_peso;
    }
    public void setNr_peso_cubado(String nr_peso_cubado) {
        this.nr_peso_cubado = nr_peso_cubado;
    }
    public String getNr_peso_cubado() {
        return nr_peso_cubado;
    }
    public void setVL_Nota_Fiscal_CTRC(String VL_Nota_Fiscal_CTRC) {
        this.VL_Nota_Fiscal_CTRC = VL_Nota_Fiscal_CTRC;
    }
    public String getVL_Nota_Fiscal_CTRC() {
        return VL_Nota_Fiscal_CTRC;
    }
    public void setDt_Emissao_Inicial(String dt_Emissao_Inicial) {
        this.dt_Emissao_Inicial = dt_Emissao_Inicial;
    }
    public String getDt_Emissao_Inicial() {
        return dt_Emissao_Inicial;
    }
    public void setDt_Emissao_Final(String dt_Emissao_Final) {
        this.dt_Emissao_Final = dt_Emissao_Final;
    }
    public String getDt_Emissao_Final() {
        return dt_Emissao_Final;
    }
    public void setDm_Situacao_Cobranca(String dm_Situacao_Cobranca) {
        this.dm_Situacao_Cobranca = dm_Situacao_Cobranca;
    }

  public void setOid_Empresa (long oid_Empresa) {
    this.oid_Empresa = oid_Empresa;
  }

  public void setOid_Pessoa (String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }

  public void setOid_Unidade (long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }

  public void setOid_Seguradora (long oid_Seguradora) {
    this.oid_Seguradora = oid_Seguradora;
  }

  public void setDT_Final (String DT_Final) {
    this.DT_Final = DT_Final;
  }

  public void setDT_Inicial (String DT_Inicial) {
    this.DT_Inicial = DT_Inicial;
  }

  public void setDM_Situacao_Cliente (String DM_Situacao_Cliente) {
    this.DM_Situacao_Cliente = DM_Situacao_Cliente;
  }

  public void setVL_Nota_Fiscal_Inicial (double VL_Nota_Fiscal_Inicial) {
    this.VL_Nota_Fiscal_Inicial = VL_Nota_Fiscal_Inicial;
  }

  public void setVL_Nota_Fiscal_Final (double VL_Nota_Fiscal_Final) {
    this.VL_Nota_Fiscal_Final = VL_Nota_Fiscal_Final;
  }

    public void setDM_Tipo_Cobranca (String DM_Tipo_Cobranca) {
        this.DM_Tipo_Cobranca = DM_Tipo_Cobranca;
    }

    public void setDM_Tipo_Documento (String DM_Tipo_Documento) {
        this.DM_Tipo_Documento = DM_Tipo_Documento;
    }

    public String getDm_Situacao_Cobranca() {
        return dm_Situacao_Cobranca;
    }

  public long getOid_Empresa () {
    return oid_Empresa;
  }

  public String getOid_Pessoa () {
    return oid_Pessoa;
  }

  public long getOid_Unidade () {
    return oid_Unidade;
  }

  public long getOid_Seguradora () {
    return oid_Seguradora;
  }

  public String getDT_Inicial () {
    return DT_Inicial;
  }

  public String getDT_Final () {
    return DT_Final;
  }

  public String getDM_Situacao_Cliente () {
    return DM_Situacao_Cliente;
  }

  public double getVL_Nota_Fiscal_Inicial () {
    return VL_Nota_Fiscal_Inicial;
  }

  public double getVL_Nota_Fiscal_Final () {
    return VL_Nota_Fiscal_Final;
  }

    public String getDM_Tipo_Cobranca () {
        return DM_Tipo_Cobranca;
    }

    public String getDM_Tipo_Documento () {
        return DM_Tipo_Documento;
    }

    public void setNM_Fantasia_Unidade(String NM_Fantasia_Unidade) {
        this.NM_Fantasia_Unidade = NM_Fantasia_Unidade;
    }
    public String getNM_Fantasia_Unidade() {
        return NM_Fantasia_Unidade;
    }
    public void setOID_Nota_Fiscal(String OID_Nota_Fiscal) {
        this.OID_Nota_Fiscal = OID_Nota_Fiscal;
    }
    public String getOID_Nota_Fiscal() {
        return OID_Nota_Fiscal;
    }
    public void setDM_Tipo_Embarque(String DM_Tipo_Embarque) {
        this.DM_Tipo_Embarque = DM_Tipo_Embarque;
    }
    public String getDM_Tipo_Embarque() {
        return DM_Tipo_Embarque;
    }
    public void setDM_Situacao(String DM_Situacao) {
        this.DM_Situacao = DM_Situacao;
    }
    public String getDM_Situacao() {
        return DM_Situacao;
    }
    public void setOID_Pessoa_Redespacho(String OID_Pessoa_Redespacho) {
        this.OID_Pessoa_Redespacho = OID_Pessoa_Redespacho;
    }
    public String getOID_Pessoa_Redespacho() {
        return OID_Pessoa_Redespacho;
    }
    public void setNM_Cidade_Redespacho(String NM_Cidade_Redespacho) {
        this.NM_Cidade_Redespacho = NM_Cidade_Redespacho;
    }
    public String getNM_Cidade_Redespacho() {
        return NM_Cidade_Redespacho;
    }
    public void setNM_Endereco_Redespacho(String NM_Endereco_Redespacho) {
        this.NM_Endereco_Redespacho = NM_Endereco_Redespacho;
    }
    public String getNM_Endereco_Redespacho() {
        return NM_Endereco_Redespacho;
    }
    public void setNM_INSCRICAO_Redespacho(String NM_INSCRICAO_Redespacho) {
        this.NM_INSCRICAO_Redespacho = NM_INSCRICAO_Redespacho;
    }
    public String getNM_INSCRICAO_Redespacho() {
        return NM_INSCRICAO_Redespacho;
    }
    public void setNM_Pessoa_Redespacho(String NM_Pessoa_Redespacho) {
        this.NM_Pessoa_Redespacho = NM_Pessoa_Redespacho;
    }
    public String getNM_Pessoa_Redespacho() {
        return NM_Pessoa_Redespacho;
    }
    public void setNR_CNPJ_CPF_Redespacho(String NR_CNPJ_CPF_Redespacho) {
        this.NR_CNPJ_CPF_Redespacho = NR_CNPJ_CPF_Redespacho;
    }
    public String getNR_CNPJ_CPF_Redespacho() {
        return NR_CNPJ_CPF_Redespacho;
    }
    public void setNR_Duplicata(long NR_Duplicata) {
        this.NR_Duplicata = NR_Duplicata;
    }
    public long getNR_Duplicata() {
        return NR_Duplicata;
    }
    public void setNR_Conhecimento_Inicial(long NR_Conhecimento_Inicial) {
        this.NR_Conhecimento_Inicial = NR_Conhecimento_Inicial;
    }
    public long getNR_Conhecimento_Inicial() {
        return NR_Conhecimento_Inicial;
    }
    public void setNR_Conhecimento_Final(long NR_Conhecimento_Final) {
        this.NR_Conhecimento_Final = NR_Conhecimento_Final;
    }
    public long getNR_Conhecimento_Final() {
        return NR_Conhecimento_Final;
    }
    public void setDM_Tipo_Produto(String DM_Tipo_Produto) {
        this.DM_Tipo_Produto = DM_Tipo_Produto;
    }
    public String getDM_Tipo_Produto() {
        return DM_Tipo_Produto;
    }
    public String getDM_Tipo_Coleta() {
        return DM_Tipo_Coleta;
    }
    public String getDM_Tipo_Entrega() {
        return DM_Tipo_Entrega;
    }
    public String getDM_Tipo_Tabela_Frete() {
        return DM_Tipo_Tabela_Frete;
    }
    public void setDM_Tipo_Coleta(String DM_Tipo_Coleta) {
        this.DM_Tipo_Coleta = DM_Tipo_Coleta;
    }
    public void setDM_Tipo_Entrega(String DM_Tipo_Entrega) {
        this.DM_Tipo_Entrega = DM_Tipo_Entrega;
    }
    public void setDM_Tipo_Tabela_Frete(String DM_Tipo_Tabela_Frete) {
        this.DM_Tipo_Tabela_Frete = DM_Tipo_Tabela_Frete;
    }
    public long getOID_Empresa() {
        return OID_Empresa;
    }
    public void setOID_Empresa(long OID_Empresa) {
        this.OID_Empresa = OID_Empresa;
    }
    public void setOID_Pessoa_Entregadora(String OID_Pessoa_Entregadora) {
        this.OID_Pessoa_Entregadora = OID_Pessoa_Entregadora;
    }
    public String getOID_Pessoa_Entregadora() {
        return OID_Pessoa_Entregadora;
    }
    public void setOID_Lote(long OID_Lote) {
        this.OID_Lote = OID_Lote;
    }
    public long getOID_Lote() {
        return OID_Lote;
    }
    public void setOID_Acerto(long OID_Acerto) {
        this.OID_Acerto = OID_Acerto;
    }
    public long getOID_Acerto() {
        return OID_Acerto;
    }
    public void setOID_Usuario(long OID_Usuario) {
        this.OID_Usuario = OID_Usuario;
    }
    public long getOID_Usuario() {
        return OID_Usuario;
    }
    public void setDM_Situacao_Entrega(String DM_Situacao_Entrega) {
        this.DM_Situacao_Entrega = DM_Situacao_Entrega;
    }
    public String getDM_Situacao_Entrega() {
        return DM_Situacao_Entrega;
    }
    public void setDM_Frete_Recebido(String DM_Frete_Recebido) {
        this.DM_Frete_Recebido = DM_Frete_Recebido;
    }
    public String getDM_Frete_Recebido() {
        return DM_Frete_Recebido;
    }
    public void setDM_Frete_Emitido(String DM_Frete_Emitido) {
        this.DM_Frete_Emitido = DM_Frete_Emitido;
    }
    public String getDM_Frete_Emitido() {
        return DM_Frete_Emitido;
    }
    public void setDM_Tipo_Remessa(String DM_Tipo_Remessa) {
        this.DM_Tipo_Remessa = DM_Tipo_Remessa;
    }
    public String getDM_Tipo_Remessa() {
        return DM_Tipo_Remessa;
    }
    public void setDM_Origem(String DM_Origem) {
        this.DM_Origem = DM_Origem;
    }
    public String getDM_Origem() {
        return DM_Origem;
    }
    public void setDM_Destino(String DM_Destino) {
        this.DM_Destino = DM_Destino;
    }
    public String getDM_Destino() {
        return DM_Destino;
    }
    public void setOID_Origem(long OID_Origem) {
        this.OID_Origem = OID_Origem;
    }
    public long getOID_Origem() {
        return OID_Origem;
    }
    public void setOID_Destino(long OID_Destino) {
        this.OID_Destino = OID_Destino;
    }
    public long getOID_Destino() {
        return OID_Destino;
    }
    public void setVL_Total_Custo(double VL_Total_Custo) {
        this.VL_Total_Custo = VL_Total_Custo;
    }
    public double getVL_Total_Custo() {
        return VL_Total_Custo;
    }
    public void setDM_Tipo_Transporte(String DM_Tipo_Transporte) {
        this.DM_Tipo_Transporte = DM_Tipo_Transporte;
    }

    public String getDM_Tipo_Transporte() {
        return DM_Tipo_Transporte;
    }
    public void setOID_Veiculo(String OID_Veiculo) {
        this.OID_Veiculo = OID_Veiculo;
    }
    public String getOID_Veiculo() {
        return OID_Veiculo;
    }
    public void setDM_Tipo_Comissao(String DM_Tipo_Comissao) {
        this.DM_Tipo_Comissao = DM_Tipo_Comissao;
    }
    public String getDM_Tipo_Comissao() {
        return DM_Tipo_Comissao;
    }
    public void setDM_Relatorio(String DM_Relatorio) {
        this.DM_Relatorio = DM_Relatorio;
    }
    public String getDM_Relatorio() {
        return DM_Relatorio;
    }
    public void setDM_Conhecimento_Varias_Notas_Fiscais(String DM_Conhecimento_Varias_Notas_Fiscais) {
        this.DM_Conhecimento_Varias_Notas_Fiscais = DM_Conhecimento_Varias_Notas_Fiscais;
    }
    public String getDM_Conhecimento_Varias_Notas_Fiscais() {
        return DM_Conhecimento_Varias_Notas_Fiscais;
    }
    public void setDM_Tipo_Impressao(String DM_Tipo_Impressao) {
        this.DM_Tipo_Impressao = DM_Tipo_Impressao;
    }
    public String getDM_Tipo_Impressao() {
        return DM_Tipo_Impressao;
    }
    public void setNR_Pagina_Inicial(long NR_Pagina_Inicial) {
        this.NR_Pagina_Inicial = NR_Pagina_Inicial;
    }
    public long getNR_Pagina_Inicial() {
        return NR_Pagina_Inicial;
    }
    public void setVL_Custo_Entrega(double VL_Custo_Entrega) {
        this.VL_Custo_Entrega = VL_Custo_Entrega;
    }
    public double getVL_Custo_Entrega() {
        return VL_Custo_Entrega;
    }
    public void setOID_Carreta(String OID_Carreta) {
        this.OID_Carreta = OID_Carreta;
    }
    public String getOID_Carreta() {
        return OID_Carreta;
    }
    public void setDT_Entrega(String DT_Entrega) {
        this.DT_Entrega = DT_Entrega;
    }
    public String getDT_Entrega() {
        return DT_Entrega;
    }
    public void setNR_Peso(double NR_Peso) {
        this.NR_Peso = NR_Peso;
    }
    public double getNR_Peso() {
        return NR_Peso;
    }
    public void setNR_Peso_Cubado(double NR_Peso_Cubado) {
        this.NR_Peso_Cubado = NR_Peso_Cubado;
    }
    public double getNR_Peso_Cubado() {
        return NR_Peso_Cubado;
    }
    public void setNR_Volumes(double NR_Volumes) {
        this.NR_Volumes = NR_Volumes;
    }
    public double getNR_Volumes() {
        return NR_Volumes;
    }
    public void setPE_Carga_Expressa(double PE_Carga_Expressa) {
        this.PE_Carga_Expressa = PE_Carga_Expressa;
    }
    public double getPE_Carga_Expressa() {
        return PE_Carga_Expressa;
    }
    public void setDM_Imprime_Cancelados(String DM_Imprime_Cancelados) {
        this.DM_Imprime_Cancelados = DM_Imprime_Cancelados;
    }
    public String getDM_Imprime_Cancelados() {
        return DM_Imprime_Cancelados;
    }
    public void setDM_Layout(String DM_Layout) {
        this.DM_Layout = DM_Layout;
    }
    public String getDM_Layout() {
        return DM_Layout;
    }
    public String getOID_Carreta2() {
        return OID_Carreta2;
    }
    public void setOID_Carreta2(String carreta2) {
        OID_Carreta2 = carreta2;
    }
    public String getNM_Lote_Faturamento() {
        return NM_Lote_Faturamento;
    }
    public void setNM_Lote_Faturamento(String NM_Lote_Faturamento) {
        this.NM_Lote_Faturamento = NM_Lote_Faturamento;
    }
    public String getNR_Transacao_Pedagio() {
        return NR_Transacao_Pedagio;
    }
    public void setNR_Transacao_Pedagio(String NR_Transacao_Pedagio) {
        this.NR_Transacao_Pedagio = NR_Transacao_Pedagio;
    }
    public String getDM_Tipo_Desconto_Pedagio() {
        return this.DM_Tipo_Desconto_Pedagio;
    }
    public void setDM_Tipo_Desconto_Pedagio(String tipo_Desconto_Pedagio) {
        this.DM_Tipo_Desconto_Pedagio = tipo_Desconto_Pedagio;
    }
    public double getPE_Comissao_Acerto() {
        return this.PE_Comissao_Acerto;
    }
    public void setPE_Comissao_Acerto(double comissao_Acerto) {
        this.PE_Comissao_Acerto = comissao_Acerto;
    }
    public String getNM_Pessoa_Pagador() {
        return this.NM_Pessoa_Pagador;
    }
    public void setNM_Pessoa_Pagador(String pessoa_Pagador) {
        this.NM_Pessoa_Pagador = pessoa_Pagador;
    }

	public double getPe_aliquota_rcta() {
		return pe_aliquota_rcta;
	}

	public void setPe_aliquota_rcta(double pe_aliquota_rcta) {
		this.pe_aliquota_rcta = pe_aliquota_rcta;
	}

	public double getPe_aliquota_rctr_dc() {
		return pe_aliquota_rctr_dc;
	}

	public void setPe_aliquota_rctr_dc(double pe_aliquota_rctr_dc) {
		this.pe_aliquota_rctr_dc = pe_aliquota_rctr_dc;
	}

	public double getPe_aliquota_rctr_vi() {
		return pe_aliquota_rctr_vi;
	}

	public void setPe_aliquota_rctr_vi(double pe_aliquota_rctr_vi) {
		this.pe_aliquota_rctr_vi = pe_aliquota_rctr_vi;
	}

	public double getPe_aliquota_rctrc() {
		return pe_aliquota_rctrc;
	}

	public void setPe_aliquota_rctrc(double pe_aliquota_rctrc) {
		this.pe_aliquota_rctrc = pe_aliquota_rctrc;
	}

	public double getVl_aliquota_rcta() {
		return vl_aliquota_rcta;
	}

	public void setVl_aliquota_rcta(double vl_aliquota_rcta) {
		this.vl_aliquota_rcta = vl_aliquota_rcta;
	}

	public double getVl_aliquota_rctr_dc() {
		return vl_aliquota_rctr_dc;
	}

	public void setVl_aliquota_rctr_dc(double vl_aliquota_rctr_dc) {
		this.vl_aliquota_rctr_dc = vl_aliquota_rctr_dc;
	}

	public double getVl_aliquota_rctr_vi() {
		return vl_aliquota_rctr_vi;
	}

	public void setVl_aliquota_rctr_vi(double vl_aliquota_rctr_vi) {
		this.vl_aliquota_rctr_vi = vl_aliquota_rctr_vi;
	}

	public double getVl_aliquota_rctrc() {
		return vl_aliquota_rctrc;
	}

	public void setVl_aliquota_rctrc(double vl_aliquota_rctrc) {
		this.vl_aliquota_rctrc = vl_aliquota_rctrc;
	}

	public double getVL_Nota_Fiscal_Total() {
		return VL_Nota_Fiscal_Total;
	}

	public void setVL_Nota_Fiscal_Total(double nota_Fiscal_Total) {
		VL_Nota_Fiscal_Total = nota_Fiscal_Total;
	}

	public double getVL_Seguro1_Total() {
		return VL_Seguro1_Total;
	}

	public void setVL_Seguro1_Total(double seguro1_Total) {
		VL_Seguro1_Total = seguro1_Total;
	}

	public double getVL_Seguro2_Total() {
		return VL_Seguro2_Total;
	}

	public void setVL_Seguro2_Total(double seguro2_Total) {
		VL_Seguro2_Total = seguro2_Total;
	}

}
