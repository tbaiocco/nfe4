package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class GerencialED extends MasterED {
  private long OID_Empresa;
  private String NM_Empresa;
  private long OID_Unidade;
  private long OID_Grupo_Conta;
  private long OID_Grupo_Economico;
  private long OID_Produto;
  private long OID_Modal;
  private String OID_Cliente;   
  private String OID_Vendedor;
  private String DM_Criterio_Receita;
  private String DM_Criterio_Despesa;
  private String NM_Cliente;
  private String NM_Vendedor;
  private String NM_Grupo_Economico;
  private String NM_Modal;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private long OID_Origem;
  private long OID_Destino;

  private String DT_Movimento_Inicial;
  private String DT_Movimento_Final;

  private String DM_Tipo_Pagamento;
  private String DM_Classificar;
  private String DM_Relatorio;
  private String DM_Lista_Conhecimento;
  private String NR_Amostra;
  private String OID_Pessoa_Remetente;
  private String NM_Remetente;
  private String OID_Pessoa_Destinatario;
  private String NM_Destinatario;
  private String OID_Pessoa_Pagador;
  private String NM_Pagador;
  private String DM_Situacao_Cobranca;
  private String DM_Tipo_Embarque;
  private String DM_Frete;

  private String OID_Pessoa_Fornecedor;
  private String NM_Fornecedor;

  private double VL_Total_Frete;
  private double VL_Notas_Fiscais;
  private double NR_Peso;
  private double NR_Peso_Cubado;
  private double NR_Conhecimentos;
  private double VL_Margem;
  private double NR_Volumes;
  private double VL_Classifica;
  private double NR_Despachos;

  private double TVL_Total_Frete;
  private double TVL_Notas_Fiscais;
  private double TNR_Peso;
  private double TNR_Peso_Cubado;
  private double TNR_Conhecimentos;
  private double TVL_Margem;
  private double TNR_Volumes;
  private double TVL_Classifica;
  private double TNR_Despachos;

  private long OID_Gerencial;
  private String OID_Pessoa;
  private String NR_Sort;
  private String OID_Conhecimento;
  private long OID_Cidade_Origem;
  private long OID_Cidade_Destino;
  private long OID_Regiao_Origem;
  private long OID_Regiao_Destino;
  private long OID_Unidade_Origem;
  private long OID_Unidade_Destino;
  private String DM_Pessoa;
  private String DT_1;
  private String DT_2;
  private String DT_3;
  private String DT_4;
  private String DT_5;
  private double VL_1;
  private double VL_2;
  private double VL_3;
  private double VL_4;
  private double VL_5;
  private double VL_Vencido;
  private double VL_Vencer;
  private long OID_Conta;
  private long OID_Carteira;
  private long OID_Conta_Corrente;
  private String NM_Origem;
  private String NM_Campo;
  private String DM_Cobranca;
  private String DM_Contas_Pagar;
  private String DM_Banco;
  private String DM_Vencido;
  private String DM_Vencer;
  private String DM_Tipo;
  private String DT_Vencimento_Inicial;
  private String DT_Vencimento_Final;
  private String DM_Situacao;

  private double VL_Titulo_Inicial;
  private double VL_Titulo_Final;
  private String NM_Fantasia_Vendedor;
  private String CD_Vendedor;
  private String CD_Carteira;
  private String NM_Banco;
  private String CD_Conta_Corrente;
  private String NR_CNPJ_CPF;
  private String NM_Razao_Social;
  private String OID_Tipo_Documento;
  private String CD_Tipo_Documento;
  private String NM_Tipo_Documento;

  private String OID_Tipo_Movimento;
  private String CD_Tipo_Movimento;
  private String NM_Tipo_Movimento;
  private String NM_Campo2;
  private String OID_Veiculo;
  private String NM_Unidade;
  private double VL_6;
  private double VL_7;
  private double VL_8;
  private double VL_9;
  private double VL_10;
  private String NM_Destino;
  private String NM_Mes_Inicial;
  private String NM_Mes_Final;
  private String NM_Ano_Inicial;
  private String NM_Ano_Final;
  private String NM_Mes_1;
  private String NM_Mes_2;
  private String NM_Mes_3;
  private String NM_Ano_1;
  private String NM_Ano_2;
  private String NM_Ano_3;
  private String DM_Consolida;
  private double VL_Mes1;
  private double VL_Mes2;
  private double VL_Mes3;
  private double VL_Mes4;
  private double VL_Mes5;
  private double VL_Mes6;
  private double VL_Mes7;
  private double VL_Mes8;
  private double VL_Mes9;
  private double VL_Mes10;
  private double VL_Mes11;
  private double VL_Mes12;
  private long oid_Modelo_Veiculo;
  private long oid_Marca_Veiculo;
  private String DM_Situacao_Cliente;
  private long NR_Entrega_Prazo;
  private long NR_Entrega_Atrazada;
  private long NR_Entrega_Antecipada;
  private long NR_Entrega_Justificada;
  private String DM_Tipo_Documento;
  private String DM_Periodo;
  private double VL_Margem_Inicial;
  private double VL_Margem_Final;
  private String DM_Vendedor;
  private String DM_Origem;
  private String DM_Destino;
  private double VL_11;
  private double VL_12;
  private double VL_13;
  private double VL_14;
  private double VL_15;
  private double VL_16;
  private double VL_17;
  private double VL_18;
  private double VL_19;
  private double VL_20;
  private double VL_21;
  private double VL_22;
  private double VL_23;
  private double VL_24;
  private double VL_25;
  private double VL_26;
  private double VL_27;
  private double VL_28;
  private double VL_29;
  private double VL_30;
  private double VL_31;


  public long getOID_Empresa() {
    return OID_Empresa;
  }
  public void setOID_Empresa(long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }
  public void setNM_Empresa(String NM_Empresa) {
    this.NM_Empresa = NM_Empresa;
  }
  public String getNM_Empresa() {
    return NM_Empresa;
  }
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  public void setOID_Grupo_Economico(long OID_Grupo_Economico) {
    this.OID_Grupo_Economico = OID_Grupo_Economico;
  }
  public long getOID_Grupo_Economico() {
    return OID_Grupo_Economico;
  }
  public void setOID_Produto(long OID_Produto) {
    this.OID_Produto = OID_Produto;
  }
  public long getOID_Produto() {
    return OID_Produto;
  }
  public void setOID_Modal(long OID_Modal) {
    this.OID_Modal = OID_Modal;
  }
  public long getOID_Modal() {
    return OID_Modal;
  }
  public void setOID_Cliente(String OID_Cliente) {
    this.OID_Cliente = OID_Cliente;
  }
  public String getOID_Cliente() {
    return OID_Cliente;
  }
  public void setOID_Vendedor(String OID_Vendedor) {
    this.OID_Vendedor = OID_Vendedor;
  }
  public String getOID_Vendedor() {
    return OID_Vendedor;
  }
  public void setNM_Cliente(String NM_Cliente) {
    this.NM_Cliente = NM_Cliente;
  }
  public String getNM_Cliente() {
    return NM_Cliente;
  }
  public void setNM_Vendedor(String NM_Vendedor) {
    this.NM_Vendedor = NM_Vendedor;
  }
  public String getNM_Vendedor() {
    return NM_Vendedor;
  }
  public void setNM_Grupo_Economico(String NM_Grupo_Economico) {
    this.NM_Grupo_Economico = NM_Grupo_Economico;
  }
  public String getNM_Grupo_Economico() {
    return NM_Grupo_Economico;
  }
  public void setNM_Modal(String NM_Modal) {
    this.NM_Modal = NM_Modal;
  }
  public String getNM_Modal() {
    return NM_Modal;
  }
  public void setDT_Emissao_Inicial(String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }
  public String getDT_Emissao_Inicial() {
    return DT_Emissao_Inicial;
  }
  public void setDT_Emissao_Final(String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }
  public String getDT_Emissao_Final() {
    return DT_Emissao_Final;
  }
  public void setDM_Tipo_Pagamento(String DM_Tipo_Pagamento) {
    this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
  }
  public String getDM_Tipo_Pagamento() {
    return DM_Tipo_Pagamento;
  }
  public void setDM_Classificar(String DM_Classificar) {
    this.DM_Classificar = DM_Classificar;
  }
  public String getDM_Classificar() {
    return DM_Classificar;
  }
  public void setDM_Relatorio(String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }
  public String getDM_Relatorio() {
    return DM_Relatorio;
  }
  public void setDM_Lista_Conhecimento(String DM_Lista_Conhecimento) {
    this.DM_Lista_Conhecimento = DM_Lista_Conhecimento;
  }
  public String getDM_Lista_Conhecimento() {
    return DM_Lista_Conhecimento;
  }
  public void setNR_Amostra(String NR_Amostra) {
    this.NR_Amostra = NR_Amostra;
  }
  public String getNR_Amostra() {
    return NR_Amostra;
  }
  public void setOID_Pessoa_Remetente(String OID_Pessoa_Remetente) {
    this.OID_Pessoa_Remetente = OID_Pessoa_Remetente;
  }
  public String getOID_Pessoa_Remetente() {
    return OID_Pessoa_Remetente;
  }
  public void setNM_Remetente(String NM_Remetente) {
    this.NM_Remetente = NM_Remetente;
  }
  public String getNM_Remetente() {
    return NM_Remetente;
  }
  public void setOID_Pessoa_Destinatario(String OID_Pessoa_Destinatario) {
    this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
  }
  public String getOID_Pessoa_Destinatario() {
    return OID_Pessoa_Destinatario;
  }
  public void setNM_Destinatario(String NM_Destinatario) {
    this.NM_Destinatario = NM_Destinatario;
  }
  public String getNM_Destinatario() {
    return NM_Destinatario;
  }
  public void setOID_Pessoa_Pagador(String OID_Pessoa_Pagador) {
    this.OID_Pessoa_Pagador = OID_Pessoa_Pagador;
  }
  public String getOID_Pessoa_Pagador() {
    return OID_Pessoa_Pagador;
  }
  public void setNM_Pagador(String NM_Pagador) {
    this.NM_Pagador = NM_Pagador;
  }
  public String getNM_Pagador() {
    return NM_Pagador;
  }
  public void setDM_Situacao_Cobranca(String DM_Situacao_Cobranca) {
    this.DM_Situacao_Cobranca = DM_Situacao_Cobranca;
  }
  public String getDM_Situacao_Cobranca() {
    return DM_Situacao_Cobranca;
  }
  public void setDM_Tipo_Embarque(String DM_Tipo_Embarque) {
    this.DM_Tipo_Embarque = DM_Tipo_Embarque;
  }
  public String getDM_Tipo_Embarque() {
    return DM_Tipo_Embarque;
  }
  public void setDM_Frete(String DM_Frete) {
    this.DM_Frete = DM_Frete;
  }
  public String getDM_Frete() {
    return DM_Frete;
  }
  public void setVL_Total_Frete(double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }
  public double getVL_Total_Frete() {
    return VL_Total_Frete;
  }
  public void setVL_Notas_Fiscais(double VL_Notas_Fiscais) {
    this.VL_Notas_Fiscais = VL_Notas_Fiscais;
  }
  public double getVL_Notas_Fiscais() {
    return VL_Notas_Fiscais;
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
  public void setNR_Conhecimentos(double NR_Conhecimentos) {
    this.NR_Conhecimentos = NR_Conhecimentos;
  }
  public double getNR_Conhecimentos() {
    return NR_Conhecimentos;
  }
  public void setVL_Margem(double VL_Margem) {
    this.VL_Margem = VL_Margem;
  }
  public double getVL_Margem() {
    return VL_Margem;
  }
  public void setNR_Volumes(double NR_Volumes) {
    this.NR_Volumes = NR_Volumes;
  }
  public double getNR_Volumes() {
    return NR_Volumes;
  }
  public void setVL_Classifica(double VL_Classifica) {
    this.VL_Classifica = VL_Classifica;
  }
  public double getVL_Classifica() {
    return VL_Classifica;
  }
  public void setNR_Sort(String NR_Sort) {
    this.NR_Sort = NR_Sort;
  }
  public String getNR_Sort() {
    return NR_Sort;
  }
  public void setOID_Gerencial(long OID_Gerencial) {
    this.OID_Gerencial = OID_Gerencial;
  }
  public long getOID_Gerencial() {
    return OID_Gerencial;
  }
  public void setOID_Pessoa(String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public String getOID_Pessoa() {
    return OID_Pessoa;
  }
  public void setNR_Despachos(double NR_Despachos) {
    this.NR_Despachos = NR_Despachos;
  }
  public double getNR_Despachos() {
    return NR_Despachos;
  }
  public void setOID_Conhecimento(String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }
  public String getOID_Conhecimento() {
    return OID_Conhecimento;
  }
  public double getTNR_Conhecimentos() {
    return TNR_Conhecimentos;
  }
  public double getTNR_Peso() {
    return TNR_Peso;
  }
  public double getTNR_Peso_Cubado() {
    return TNR_Peso_Cubado;
  }
  public double getTNR_Volumes() {
    return TNR_Volumes;
  }
  public double getTVL_Classifica() {
    return TVL_Classifica;
  }
  public double getTVL_Margem() {
    return TVL_Margem;
  }
  public double getTVL_Notas_Fiscais() {
    return TVL_Notas_Fiscais;
  }
  public double getTVL_Total_Frete() {
    return TVL_Total_Frete;
  }
  public void setTVL_Total_Frete(double TVL_Total_Frete) {
    this.TVL_Total_Frete = TVL_Total_Frete;
  }
  public void setTVL_Notas_Fiscais(double TVL_Notas_Fiscais) {
    this.TVL_Notas_Fiscais = TVL_Notas_Fiscais;
  }
  public void setTVL_Margem(double TVL_Margem) {
    this.TVL_Margem = TVL_Margem;
  }
  public void setTVL_Classifica(double TVL_Classifica) {
    this.TVL_Classifica = TVL_Classifica;
  }
  public void setTNR_Volumes(double TNR_Volumes) {
    this.TNR_Volumes = TNR_Volumes;
  }
  public void setTNR_Peso_Cubado(double TNR_Peso_Cubado) {
    this.TNR_Peso_Cubado = TNR_Peso_Cubado;
  }
  public void setTNR_Peso(double TNR_Peso) {
    this.TNR_Peso = TNR_Peso;
  }
  public void setTNR_Conhecimentos(double TNR_Conhecimentos) {
    this.TNR_Conhecimentos = TNR_Conhecimentos;
  }
  public double getTNR_Despachos() {
    return TNR_Despachos;
  }
  public void setTNR_Despachos(double TNR_Despachos) {
    this.TNR_Despachos = TNR_Despachos;
  }
  public void setOID_Cidade_Origem(long OID_Cidade_Origem) {
    this.OID_Cidade_Origem = OID_Cidade_Origem;
  }
  public long getOID_Cidade_Origem() {
    return OID_Cidade_Origem;
  }
  public void setOID_Cidade_Destino(long OID_Cidade_Destino) {
    this.OID_Cidade_Destino = OID_Cidade_Destino;
  }
  public long getOID_Cidade_Destino() {
    return OID_Cidade_Destino;
  }
  public void setDM_Pessoa(String DM_Pessoa) {
    this.DM_Pessoa = DM_Pessoa;
  }
  public String getDM_Pessoa() {
    return DM_Pessoa;
  }
  public void setDT_1(String DT_1) {
    this.DT_1 = DT_1;
  }
  public String getDT_1() {
    return DT_1;
  }
  public void setDT_2(String DT_2) {
    this.DT_2 = DT_2;
  }
  public String getDT_2() {
    return DT_2;
  }
  public void setDT_3(String DT_3) {
    this.DT_3 = DT_3;
  }
  public String getDT_3() {
    return DT_3;
  }
  public void setDT_4(String DT_4) {
    this.DT_4 = DT_4;
  }
  public String getDT_4() {
    return DT_4;
  }
  public void setDT_5(String DT_5) {
    this.DT_5 = DT_5;
  }
  public String getDT_5() {
    return DT_5;
  }
  public void setVL_1(double VL_1) {
    this.VL_1 = VL_1;
  }
  public double getVL_1() {
    return VL_1;
  }
  public void setVL_2(double VL_2) {
    this.VL_2 = VL_2;
  }
  public double getVL_2() {
    return VL_2;
  }
  public void setVL_3(double VL_3) {
    this.VL_3 = VL_3;
  }
  public double getVL_3() {
    return VL_3;
  }
  public void setVL_4(double VL_4) {
    this.VL_4 = VL_4;
  }
  public double getVL_4() {
    return VL_4;
  }
  public void setVL_5(double VL_5) {
    this.VL_5 = VL_5;
  }
  public double getVL_5() {
    return VL_5;
  }
  public void setVL_Vencido(double VL_Vencido) {
    this.VL_Vencido = VL_Vencido;
  }
  public double getVL_Vencido() {
    return VL_Vencido;
  }
  public void setVL_Vencer(double VL_Vencer) {
    this.VL_Vencer = VL_Vencer;
  }
  public double getVL_Vencer() {
    return VL_Vencer;
  }
  public void setOID_Conta(long OID_Conta) {
    this.OID_Conta = OID_Conta;
  }
  public long getOID_Conta() {
    return OID_Conta;
  }
  public void setOID_Carteira(long OID_Carteira) {
    this.OID_Carteira = OID_Carteira;
  }
  public long getOID_Carteira() {
    return OID_Carteira;
  }
  public void setOID_Conta_Corrente(long OID_Conta_Corrente) {
    this.OID_Conta_Corrente = OID_Conta_Corrente;
  }
  public long getOID_Conta_Corrente() {
    return OID_Conta_Corrente;
  }
  public void setNM_Origem(String NM_Origem) {
    this.NM_Origem = NM_Origem;
  }
  public String getNM_Origem() {
    return NM_Origem;
  }
  public void setNM_Campo(String NM_Campo) {
    this.NM_Campo = NM_Campo;
  }
  public String getNM_Campo() {
    return NM_Campo;
  }
  public void setDM_Cobranca(String DM_Cobranca) {
    this.DM_Cobranca = DM_Cobranca;
  }
  public String getDM_Cobranca() {
    return DM_Cobranca;
  }
  public void setDM_Contas_Pagar(String DM_Contas_Pagar) {
    this.DM_Contas_Pagar = DM_Contas_Pagar;
  }
  public String getDM_Contas_Pagar() {
    return DM_Contas_Pagar;
  }
  public void setDM_Banco(String DM_Banco) {
    this.DM_Banco = DM_Banco;
  }
  public String getDM_Banco() {
    return DM_Banco;
  }
  public void setDM_Vencido(String DM_Vencido) {
    this.DM_Vencido = DM_Vencido;
  }
  public String getDM_Vencido() {
    return DM_Vencido;
  }
  public void setDM_Vencer(String DM_Vencer) {
    this.DM_Vencer = DM_Vencer;
  }
  public String getDM_Vencer() {
    return DM_Vencer;
  }
  public void setDM_Tipo(String DM_Tipo) {
    this.DM_Tipo = DM_Tipo;
  }
  public String getDM_Tipo() {
    return DM_Tipo;
  }
  public String getCD_Carteira() {
    return CD_Carteira;
  }
  public String getCD_Conta_Corrente() {
    return CD_Conta_Corrente;
  }
  public String getCD_Tipo_Documento() {
    return CD_Tipo_Documento;
  }
  public String getCD_Vendedor() {
    return CD_Vendedor;
  }
  public void setCD_Carteira(String CD_Carteira) {
    this.CD_Carteira = CD_Carteira;
  }
  public void setCD_Conta_Corrente(String CD_Conta_Corrente) {
    this.CD_Conta_Corrente = CD_Conta_Corrente;
  }
  public void setCD_Tipo_Documento(String CD_Tipo_Documento) {
    this.CD_Tipo_Documento = CD_Tipo_Documento;
  }
  public void setCD_Vendedor(String CD_Vendedor) {
    this.CD_Vendedor = CD_Vendedor;
  }
  public void setNM_Banco(String NM_Banco) {
    this.NM_Banco = NM_Banco;
  }
  public String getNM_Banco() {
    return NM_Banco;
  }
  public String getNM_Fantasia_Vendedor() {
    return NM_Fantasia_Vendedor;
  }
  public void setNM_Fantasia_Vendedor(String NM_Fantasia_Vendedor) {
    this.NM_Fantasia_Vendedor = NM_Fantasia_Vendedor;
  }
  public void setNM_Razao_Social(String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }
  public String getNM_Razao_Social() {
    return NM_Razao_Social;
  }
  public String getNM_Tipo_Documento() {
    return NM_Tipo_Documento;
  }
  public void setNM_Tipo_Documento(String NM_Tipo_Documento) {
    this.NM_Tipo_Documento = NM_Tipo_Documento;
  }
  public void setNR_CNPJ_CPF(String NR_CNPJ_CPF) {
    this.NR_CNPJ_CPF = NR_CNPJ_CPF;
  }
  public String getNR_CNPJ_CPF() {
    return NR_CNPJ_CPF;
  }
  public String getOID_Tipo_Documento() {
    return OID_Tipo_Documento;
  }
  public void setOID_Tipo_Documento(String OID_Tipo_Documento) {
    this.OID_Tipo_Documento = OID_Tipo_Documento;
  }
  public void setVL_Titulo_Final(double VL_Titulo_Final) {
    this.VL_Titulo_Final = VL_Titulo_Final;
  }
  public void setVL_Titulo_Inicial(double VL_Titulo_Inicial) {
    this.VL_Titulo_Inicial = VL_Titulo_Inicial;
  }
  public double getVL_Titulo_Final() {
    return VL_Titulo_Final;
  }
  public double getVL_Titulo_Inicial() {
    return VL_Titulo_Inicial;
  }
  public void setDT_Vencimento_Final(String DT_Vencimento_Final) {
    this.DT_Vencimento_Final = DT_Vencimento_Final;
  }
  public void setDT_Vencimento_Inicial(String DT_Vencimento_Inicial) {
    this.DT_Vencimento_Inicial = DT_Vencimento_Inicial;
  }
  public String getDT_Vencimento_Final() {
    return DT_Vencimento_Final;
  }
  public String getDT_Vencimento_Inicial() {
    return DT_Vencimento_Inicial;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public String getCD_Tipo_Movimento() {
    return CD_Tipo_Movimento;
  }
  public void setCD_Tipo_Movimento(String CD_Tipo_Movimento) {
    this.CD_Tipo_Movimento = CD_Tipo_Movimento;
  }
  public String getNM_Fornecedor() {
    return NM_Fornecedor;
  }
  public void setNM_Fornecedor(String NM_Fornecedor) {
    this.NM_Fornecedor = NM_Fornecedor;
  }
  public String getNM_Tipo_Movimento() {
    return NM_Tipo_Movimento;
  }
  public void setNM_Tipo_Movimento(String NM_Tipo_Movimento) {
    this.NM_Tipo_Movimento = NM_Tipo_Movimento;
  }
  public String getOID_Pessoa_Fornecedor() {
    return OID_Pessoa_Fornecedor;
  }
  public void setOID_Pessoa_Fornecedor(String OID_Pessoa_Fornecedor) {
    this.OID_Pessoa_Fornecedor = OID_Pessoa_Fornecedor;
  }
  public void setOID_Tipo_Movimento(String OID_Tipo_Movimento) {
    this.OID_Tipo_Movimento = OID_Tipo_Movimento;
  }
  public String getOID_Tipo_Movimento() {
    return OID_Tipo_Movimento;
  }
  public String getDT_Movimento_Final() {
    return DT_Movimento_Final;
  }
  public String getDT_Movimento_Inicial() {
    return DT_Movimento_Inicial;
  }
  public void setDT_Movimento_Final(String DT_Movimento_Final) {
    this.DT_Movimento_Final = DT_Movimento_Final;
  }
  public void setDT_Movimento_Inicial(String DT_Movimento_Inicial) {
    this.DT_Movimento_Inicial = DT_Movimento_Inicial;
  }
  public void setNM_Campo2(String NM_Campo2) {
    this.NM_Campo2 = NM_Campo2;
  }
  public String getNM_Campo2() {
    return NM_Campo2;
  }

  public void setOID_Veiculo (String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }

  public String getOID_Veiculo () {
    return OID_Veiculo;
  }

  public void setNM_Unidade (String NM_Unidade) {
    this.NM_Unidade = NM_Unidade;
  }

  public String getNM_Unidade () {
    return NM_Unidade;
  }

  public void setVL_6 (double VL_6) {
    this.VL_6 = VL_6;
  }

  public double getVL_6 () {
    return VL_6;
  }

  public void setVL_7 (double VL_7) {
    this.VL_7 = VL_7;
  }

  public double getVL_7 () {
    return VL_7;
  }

  public void setVL_8 (double VL_8) {
    this.VL_8 = VL_8;
  }

  public double getVL_8 () {
    return VL_8;
  }

  public void setVL_9 (double VL_9) {
    this.VL_9 = VL_9;
  }

  public double getVL_9 () {
    return VL_9;
  }

  public void setVL_10 (double VL_10) {
    this.VL_10 = VL_10;
  }

  public double getVL_10 () {
    return VL_10;
  }

  public long getOID_Unidade_Destino () {
    return OID_Unidade_Destino;
  }

  public long getOID_Unidade_Origem () {
    return OID_Unidade_Origem;
  }

  public void setOID_Unidade_Destino (long OID_Unidade_Destino) {
    this.OID_Unidade_Destino = OID_Unidade_Destino;
  }

  public void setOID_Unidade_Origem (long OID_Unidade_Origem) {
    this.OID_Unidade_Origem = OID_Unidade_Origem;
  }

  public void setNM_Destino (String NM_Destino) {
    this.NM_Destino = NM_Destino;
  }

  public String getNM_Destino () {
    return NM_Destino;
  }

  public void setNM_Mes_Inicial (String NM_Mes_Inicial) {
    this.NM_Mes_Inicial = NM_Mes_Inicial;
  }

  public String getNM_Mes_Inicial () {
    return NM_Mes_Inicial;
  }

  public void setNM_Mes_Final (String NM_Mes_Final) {
    this.NM_Mes_Final = NM_Mes_Final;
  }

  public String getNM_Mes_Final () {
    return NM_Mes_Final;
  }

  public void setNM_Ano_Inicial (String NM_Ano_Inicial) {
    this.NM_Ano_Inicial = NM_Ano_Inicial;
  }

  public String getNM_Ano_Inicial () {
    return NM_Ano_Inicial;
  }

  public void setNM_Ano_Final (String NM_Ano_Final) {
    this.NM_Ano_Final = NM_Ano_Final;
  }

  public String getNM_Ano_Final () {
    return NM_Ano_Final;
  }

  public void setNM_Mes_1 (String NM_Mes_1) {
    this.NM_Mes_1 = NM_Mes_1;
  }

  public String getNM_Mes_1 () {
    return NM_Mes_1;
  }

  public void setNM_Mes_2 (String NM_Mes_2) {
    this.NM_Mes_2 = NM_Mes_2;
  }

  public String getNM_Mes_2 () {
    return NM_Mes_2;
  }

  public void setNM_Mes_3 (String NM_Mes_3) {
    this.NM_Mes_3 = NM_Mes_3;
  }

  public String getNM_Mes_3 () {
    return NM_Mes_3;
  }

  public void setNM_Ano_1 (String NM_Ano_1) {
    this.NM_Ano_1 = NM_Ano_1;
  }

  public String getNM_Ano_1 () {
    return NM_Ano_1;
  }

  public void setNM_Ano_2 (String NM_Ano_2) {
    this.NM_Ano_2 = NM_Ano_2;
  }

  public String getNM_Ano_2 () {
    return NM_Ano_2;
  }

  public void setNM_Ano_3 (String NM_Ano_3) {
    this.NM_Ano_3 = NM_Ano_3;
  }

  public String getNM_Ano_3 () {
    return NM_Ano_3;
  }

  public void setDM_Consolida (String DM_Consolida) {
    this.DM_Consolida = DM_Consolida;
  }

  public String getDM_Consolida () {
    return DM_Consolida;
  }

  public long getOID_Grupo_Conta () {
    return OID_Grupo_Conta;
  }

  public void setOID_Grupo_Conta (long OID_Grupo_Conta) {
    this.OID_Grupo_Conta = OID_Grupo_Conta;
  }

  public void setVL_Mes1 (double VL_Mes1) {
    this.VL_Mes1 = VL_Mes1;
  }

  public double getVL_Mes1 () {
    return VL_Mes1;
  }

  public void setVL_Mes2 (double VL_Mes2) {
    this.VL_Mes2 = VL_Mes2;
  }

  public double getVL_Mes2 () {
    return VL_Mes2;
  }

  public void setVL_Mes3 (double VL_Mes3) {
    this.VL_Mes3 = VL_Mes3;
  }

  public double getVL_Mes3 () {
    return VL_Mes3;
  }

  public void setVL_Mes4 (double VL_Mes4) {
    this.VL_Mes4 = VL_Mes4;
  }

  public double getVL_Mes4 () {
    return VL_Mes4;
  }

  public void setVL_Mes5 (double VL_Mes5) {
    this.VL_Mes5 = VL_Mes5;
  }

  public double getVL_Mes5 () {
    return VL_Mes5;
  }

  public void setVL_Mes6 (double VL_Mes6) {
    this.VL_Mes6 = VL_Mes6;
  }

  public double getVL_Mes6 () {
    return VL_Mes6;
  }

  public void setVL_Mes7 (double VL_Mes7) {
    this.VL_Mes7 = VL_Mes7;
  }

  public double getVL_Mes7 () {
    return VL_Mes7;
  }

  public void setVL_Mes8 (double VL_Mes8) {
    this.VL_Mes8 = VL_Mes8;
  }

  public double getVL_Mes8 () {
    return VL_Mes8;
  }

  public void setVL_Mes9 (double VL_Mes9) {
    this.VL_Mes9 = VL_Mes9;
  }

  public double getVL_Mes9 () {
    return VL_Mes9;
  }

  public void setVL_Mes10 (double VL_Mes10) {
    this.VL_Mes10 = VL_Mes10;
  }

  public double getVL_Mes10 () {
    return VL_Mes10;
  }

  public void setVL_Mes11 (double VL_Mes11) {
    this.VL_Mes11 = VL_Mes11;
  }

  public double getVL_Mes11 () {
    return VL_Mes11;
  }

  public void setVL_Mes12 (double VL_Mes12) {
    this.VL_Mes12 = VL_Mes12;
  }

  public double getVL_Mes12 () {
    return VL_Mes12;
  }

  public void setOid_Modelo_Veiculo (long oid_Modelo_Veiculo) {
    this.oid_Modelo_Veiculo = oid_Modelo_Veiculo;
  }

  public void setOid_Marca_Veiculo (long oid_Marca_Veiculo) {
    this.oid_Marca_Veiculo = oid_Marca_Veiculo;
  }

  public void setOID_Regiao_Origem (long OID_Regiao_Origem) {
    this.OID_Regiao_Origem = OID_Regiao_Origem;
  }

  public void setOID_Regiao_Destino (long OID_Regiao_Destino) {
    this.OID_Regiao_Destino = OID_Regiao_Destino;
  }

  public void setVL_11 (double VL_11) {
    this.VL_11 = VL_11;
  }

  public void setVL_12 (double VL_12) {
    this.VL_12 = VL_12;
  }

  public void setVL_13 (double VL_13) {
    this.VL_13 = VL_13;
  }

  public void setVL_14 (double VL_14) {
    this.VL_14 = VL_14;
  }

  public void setVL_15 (double VL_15) {
    this.VL_15 = VL_15;
  }

  public void setVL_16 (double VL_16) {
    this.VL_16 = VL_16;
  }

  public void setVL_17 (double VL_17) {
    this.VL_17 = VL_17;
  }

  public void setVL_18 (double VL_18) {
    this.VL_18 = VL_18;
  }

  public void setVL_19 (double VL_19) {
    this.VL_19 = VL_19;
  }

  public void setVL_20 (double VL_20) {
    this.VL_20 = VL_20;
  }

  public void setVL_21 (double VL_21) {
    this.VL_21 = VL_21;
  }

  public void setVL_22 (double VL_22) {
    this.VL_22 = VL_22;
  }

  public void setVL_23 (double VL_23) {
    this.VL_23 = VL_23;
  }

  public void setVL_24 (double VL_24) {
    this.VL_24 = VL_24;
  }

  public void setVL_25 (double VL_25) {
    this.VL_25 = VL_25;
  }

  public void setVL_26 (double VL_26) {
    this.VL_26 = VL_26;
  }

  public void setVL_27 (double VL_27) {
    this.VL_27 = VL_27;
  }

  public void setVL_28 (double VL_28) {
    this.VL_28 = VL_28;
  }

  public void setVL_29 (double VL_29) {
    this.VL_29 = VL_29;
  }

  public void setVL_30 (double VL_30) {
    this.VL_30 = VL_30;
  }

  public void setVL_31 (double VL_31) {
    this.VL_31 = VL_31;
  }

  public void setDM_Destino (String DM_Destino) {
    this.DM_Destino = DM_Destino;
  }

  public void setDM_Origem (String DM_Origem) {
    this.DM_Origem = DM_Origem;
  }

  public void setOID_Origem (long OID_Origem) {
    this.OID_Origem = OID_Origem;
  }

  public void setOID_Destino (long OID_Destino) {
    this.OID_Destino = OID_Destino;
  }

  public void setDM_Vendedor (String DM_Vendedor) {
    this.DM_Vendedor = DM_Vendedor;
  }

  public void setVL_Margem_Final (double VL_Margem_Final) {
    this.VL_Margem_Final = VL_Margem_Final;
  }

  public void setVL_Margem_Inicial (double VL_Margem_Inicial) {
    this.VL_Margem_Inicial = VL_Margem_Inicial;
  }

  public void setDM_Periodo (String DM_Periodo) {
    this.DM_Periodo = DM_Periodo;
  }

  public void setDM_Tipo_Documento (String DM_Tipo_Documento) {
    this.DM_Tipo_Documento = DM_Tipo_Documento;
  }

  public void setNR_Entrega_Justificada (long NR_Entrega_Justificada) {
    this.NR_Entrega_Justificada = NR_Entrega_Justificada;
  }

  public void setNR_Entrega_Antecipada (long NR_Entrega_Antecipada) {
    this.NR_Entrega_Antecipada = NR_Entrega_Antecipada;
  }

  public void setNR_Entrega_Atrazada (long NR_Entrega_Atrazada) {
    this.NR_Entrega_Atrazada = NR_Entrega_Atrazada;
  }

  public void setNR_Entrega_Prazo (long NR_Entrega_Prazo) {
    this.NR_Entrega_Prazo = NR_Entrega_Prazo;
  }

  public void setDM_Situacao_Cliente (String DM_Situacao_Cliente) {
    this.DM_Situacao_Cliente = DM_Situacao_Cliente;
  }

  public long getOid_Modelo_Veiculo () {
    return oid_Modelo_Veiculo;
  }

  public long getOid_Marca_Veiculo () {
    return oid_Marca_Veiculo;
  }

  public long getOID_Regiao_Origem () {
    return OID_Regiao_Origem;
  }

  public long getOID_Regiao_Destino () {
    return OID_Regiao_Destino;
  }

  public double getVL_31 () {
    return VL_31;
  }

  public double getVL_30 () {
    return VL_30;
  }

  public double getVL_29 () {
    return VL_29;
  }

  public double getVL_28 () {
    return VL_28;
  }

  public double getVL_27 () {
    return VL_27;
  }

  public double getVL_26 () {
    return VL_26;
  }

  public double getVL_25 () {
    return VL_25;
  }

  public double getVL_24 () {
    return VL_24;
  }

  public double getVL_23 () {
    return VL_23;
  }

  public double getVL_22 () {
    return VL_22;
  }

  public double getVL_21 () {
    return VL_21;
  }

  public double getVL_20 () {
    return VL_20;
  }

  public double getVL_19 () {
    return VL_19;
  }

  public double getVL_18 () {
    return VL_18;
  }

  public double getVL_17 () {
    return VL_17;
  }

  public double getVL_16 () {
    return VL_16;
  }

  public double getVL_15 () {
    return VL_15;
  }

  public double getVL_14 () {
    return VL_14;
  }

  public double getVL_13 () {
    return VL_13;
  }

  public double getVL_12 () {
    return VL_12;
  }

  public double getVL_11 () {
    return VL_11;
  }

  public String getDM_Destino () {
    return DM_Destino;
  }

  public String getDM_Origem () {
    return DM_Origem;
  }

  public long getOID_Origem () {
    return OID_Origem;
  }

  public long getOID_Destino () {
    return OID_Destino;
  }

  public String getDM_Vendedor () {
    return DM_Vendedor;
  }

  public double getVL_Margem_Final () {
    return VL_Margem_Final;
  }

  public double getVL_Margem_Inicial () {
    return VL_Margem_Inicial;
  }

  public String getDM_Periodo () {
    return DM_Periodo;
  }

  public String getDM_Tipo_Documento () {
    return DM_Tipo_Documento;
  }

  public long getNR_Entrega_Justificada () {
    return NR_Entrega_Justificada;
  }

  public long getNR_Entrega_Antecipada () {
    return NR_Entrega_Antecipada;
  }

  public long getNR_Entrega_Atrazada () {
    return NR_Entrega_Atrazada;
  }

  public long getNR_Entrega_Prazo () {
    return NR_Entrega_Prazo;
  }

  public String getDM_Situacao_Cliente () {
    return DM_Situacao_Cliente;
  }
public String getDM_Criterio_Despesa() {
	return DM_Criterio_Despesa;
}
public void setDM_Criterio_Despesa(String criterio_Despesa) {
	DM_Criterio_Despesa = criterio_Despesa;
}
public String getDM_Criterio_Receita() {
	return DM_Criterio_Receita;
}
public void setDM_Criterio_Receita(String criterio_Receita) {
	DM_Criterio_Receita = criterio_Receita;
}

}
