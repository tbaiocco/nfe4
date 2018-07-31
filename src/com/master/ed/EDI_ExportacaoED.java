package com.master.ed;

import java.util.Collection;

public class EDI_ExportacaoED extends MasterED{

// Dados remetente
  private String DT_Lancamento_Inicial;
  private String DT_Lancamento_Final;
  private String DT_Inicial;
  private String DT_Final;
  private String oid_Pessoa;
  private String oid_Pessoa_edi;
  private long oid_Empresa;
  private String NM_Identificador_Registro;
  private String NM_Filial_Duplicata;
  private String NM_Tipo_Documento;
  private String NR_Serie_Duplicata;
  private String NR_Duplicata;
  private String DT_Emissao_Duplicata;
  private String DT_Vencimento_Duplicata;
  private String NM_Tipo_Cobranca;
  private String NM_Agente_Cobranca;
  private String NM_Agencia;
  private String NM_Conta_Corrente;
  private String NM_Filial_Conhecimento;
  private String NR_Serie_Conhecimento;
  private String NR_Conhecimento;
  private String NR_Nota_Fiscal;
  private String NR_Serie_Nota_Fiscal;
  private String DT_Emissao_Nota_Fiscal;
  private String DM_Serie_Duplicata;
  private double VL_Desconto;
  private double VL_Documento;
  private double VL_ICMS;
  private double VL_Juro_Mora_Dia;
  private double VL_Duplicata;
  private double VL_Nota_Fiscal;
  private double NR_Peso_Nota_Fiscal;
  private double NR_Peso_Cubado_Nota_Fiscal;
  private double VL_Total_Frete;
  private double VL_Base_Calculo_ICMS;
  private double PE_Aliquota_ICMS;
  private double VL_Frete_Peso;
  private double VL_Frete_Valor;
  private double VL_SEC_CAT;
  private double VL_ITR;
  private double VL_Saldo;
  private double VL_Despacho;
  private double VL_Pedagio;
  private double VL_Ademe;
  private String CD_CFO_Conhecimento;
  private String DT_Emissao_Conhecimento;
  private double NR_Peso_Conhecimento;
  private String DM_Tipo_Pagamento;
  private String DM_Substituicao_Tributaria;
  private String oid_Pessoa_Unidade;
  private String CD_Ocorrencia;
  private long oid_Padrao;
  private String DT_Ocorrencia;
  private String HR_Ocorrencia;
  private String TX_Observacao;
  private String oid_Pessoa_Destinatario;
  private String oid_Pessoa_Consignatario;
  private String oid_Pessoa_Entregadora;
  private String oid_Pessoa_Redespacho;
  private String DT_Previsao_Entrega;
  private String HR_Previsao_Entrega;
  private String NM_Natureza;
  private String HR_Emissao;
  private String NM_Produto;
  private String NR_Fatura;
  private String DT_Coleta;
  private String HR_Coleta;
  private String DT_Embarque;
  private String HR_Embarque;
  private String NR_Manifesto;
  private String DM_Tipo_Conhecimento;
  private double VL_Outros1;
  private String NM_Unidade_Entregadora;
  private String NR_Pedido;
  private double NR_Volume_Nota_Fiscal;
  private String oid_Pessoa_Pagador;
  private String DT_Entrega;
  private String HR_Entrega;
  private String DM_Tipo_Ocorrencia;
  private long oid_Seguradora;
  private String NR_Placa;
  private String UF_Origem;
  private String UF_Destino;
  private String CD_Unidade_Entregadora;
  private String DT_Ocorrencia_Lancada;
  private String HR_Ocorrencia_Lancada;
  private long oid_Unidade;
  private String NM_Cidade_Origem;
  private String NM_Cidade_Destino;
  private String OID_Veiculo;
  private String OID_Carreta;
  private String NM_Razao_Social;
  private String DM_Isencao_Seguro;
  private double NR_Peso_Cubado_Conhecimento;
  private double VL_ICMS_Conhecimento;
  private String NR_Lote;
  private String DM_Tipo_Transporte;
  private String DM_Situacao;
  private int OID_Cidade_Destino;
  private int OID_Cidade_Origem;
  private String NM_Destinatario;
  private String NM_Remetente;
  private String OID_Carreta2;
  private String DM_Grupo_Economico;
  private String DM_Tipo_Frete;

  private String CD_Estado_Unidade;
  private String CD_Estado_Origem;
  private String CD_Estado_Destino;

  private String OID_Manifesto;

  private String DM_Tipo_Padrao;
  private double NR_Quantidade;
  private double VL_Produto;
  private String CD_Unidade_Medida;
  private Collection Produtos;

  public String getDT_Inicial() {
    return DT_Inicial;
  }
  public void setDT_Inicial(String DT_Inicial) {
    this.DT_Inicial = DT_Inicial;
  }
  public void setDT_Final(String DT_Final) {
    this.DT_Final = DT_Final;
  }
  public String getDT_Final() {
    return DT_Final;
  }
  public void setOid_Pessoa(String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }
  public String getOid_Pessoa() {
    return oid_Pessoa;
  }
  public void setOid_Empresa(long oid_Empresa) {
    this.oid_Empresa = oid_Empresa;
  }
  public long getOid_Empresa() {
    return oid_Empresa;
  }
  public void setNM_Identificador_Registro(String NM_Identificador_Registro) {
    this.NM_Identificador_Registro = NM_Identificador_Registro;
  }
  public String getNM_Identificador_Registro() {
    return NM_Identificador_Registro;
  }
  public void setNM_Filial_Duplicata(String NM_Filial_Duplicata) {
    this.NM_Filial_Duplicata = NM_Filial_Duplicata;
  }
  public String getNM_Filial_Duplicata() {
    return NM_Filial_Duplicata;
  }
  public void setNM_Tipo_Documento(String NM_Tipo_Documento) {
    this.NM_Tipo_Documento = NM_Tipo_Documento;
  }
  public String getNM_Tipo_Documento() {
    return NM_Tipo_Documento;
  }
  public void setNR_Serie_Duplicata(String NR_Serie_Duplicata) {
    this.NR_Serie_Duplicata = NR_Serie_Duplicata;
  }
  public String getNR_Serie_Duplicata() {
    return NR_Serie_Duplicata;
  }
  public void setNR_Duplicata(String NR_Duplicata) {
    this.NR_Duplicata = NR_Duplicata;
  }
  public String getNR_Duplicata() {
    return NR_Duplicata;
  }
  public void setDT_Emissao_Duplicata(String DT_Emissao_Duplicata) {
    this.DT_Emissao_Duplicata = DT_Emissao_Duplicata;
  }
  public String getDT_Emissao_Duplicata() {
    return DT_Emissao_Duplicata;
  }
  public void setDT_Vencimento_Duplicata(String DT_Vencimento_Duplicata) {
    this.DT_Vencimento_Duplicata = DT_Vencimento_Duplicata;
  }
  public String getDT_Vencimento_Duplicata() {
    return DT_Vencimento_Duplicata;
  }
  public void setVL_Documento(double VL_Documento) {
    this.VL_Documento = VL_Documento;
  }
  public double getVL_Documento() {
    return VL_Documento;
  }
  public void setNM_Tipo_Cobranca(String NM_Tipo_Cobranca) {
    this.NM_Tipo_Cobranca = NM_Tipo_Cobranca;
  }
  public String getNM_Tipo_Cobranca() {
    return NM_Tipo_Cobranca;
  }
  public void setVL_ICMS(double VL_ICMS) {
    this.VL_ICMS = VL_ICMS;
  }
  public double getVL_ICMS() {
    return VL_ICMS;
  }
  public void setVL_Juro_Mora_Dia(double VL_Juro_Mora_Dia) {
    this.VL_Juro_Mora_Dia = VL_Juro_Mora_Dia;
  }
  public double getVL_Juro_Mora_Dia() {
    return VL_Juro_Mora_Dia;
  }
  public void setVL_Desconto(double VL_Desconto) {
    this.VL_Desconto = VL_Desconto;
  }
  public double getVL_Desconto() {
    return VL_Desconto;
  }
  public void setNM_Agente_Cobranca(String NM_Agente_Cobranca) {
    this.NM_Agente_Cobranca = NM_Agente_Cobranca;
  }
  public String getNM_Agente_Cobranca() {
    return NM_Agente_Cobranca;
  }
  public void setNM_Agencia(String NM_Agencia) {
    this.NM_Agencia = NM_Agencia;
  }
  public String getNM_Agencia() {
    return NM_Agencia;
  }
  public void setNM_Conta_Corrente(String NM_Conta_Corrente) {
    this.NM_Conta_Corrente = NM_Conta_Corrente;
  }
  public String getNM_Conta_Corrente() {
    return NM_Conta_Corrente;
  }
  public void setNM_Filial_Conhecimento(String NM_Filial_Conhecimento) {
    this.NM_Filial_Conhecimento = NM_Filial_Conhecimento;
  }
  public String getNM_Filial_Conhecimento() {
    return NM_Filial_Conhecimento;
  }
  public void setNR_Serie_Conhecimento(String NR_Serie_Conhecimento) {
    this.NR_Serie_Conhecimento = NR_Serie_Conhecimento;
  }
  public String getNR_Serie_Conhecimento() {
    return NR_Serie_Conhecimento;
  }
  public void setNR_Conhecimento(String NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }
  public String getNR_Conhecimento() {
    return NR_Conhecimento;
  }
  public void setNR_Nota_Fiscal(String NR_Nota_Fiscal) {
    this.NR_Nota_Fiscal = NR_Nota_Fiscal;
  }
  public String getNR_Nota_Fiscal() {
    return NR_Nota_Fiscal;
  }
  public void setNR_Serie_Nota_Fiscal(String NR_Serie_Nota_Fiscal) {
    this.NR_Serie_Nota_Fiscal = NR_Serie_Nota_Fiscal;
  }
  public String getNR_Serie_Nota_Fiscal() {
    return NR_Serie_Nota_Fiscal;
  }
  public void setDT_Emissao_Nota_Fiscal(String DT_Emissao_Nota_Fiscal) {
    this.DT_Emissao_Nota_Fiscal = DT_Emissao_Nota_Fiscal;
  }
  public String getDT_Emissao_Nota_Fiscal() {
    return DT_Emissao_Nota_Fiscal;
  }
  public void setNR_Peso_Nota_Fiscal(double NR_Peso_Nota_Fiscal) {
    this.NR_Peso_Nota_Fiscal = NR_Peso_Nota_Fiscal;
  }
  public double getNR_Peso_Nota_Fiscal() {
    return NR_Peso_Nota_Fiscal;
  }
  public void setVL_Nota_Fiscal(double VL_Nota_Fiscal) {
    this.VL_Nota_Fiscal = VL_Nota_Fiscal;
  }
  public double getVL_Nota_Fiscal() {
    return VL_Nota_Fiscal;
  }
  public void setDM_Serie_Duplicata(String DM_Serie_Duplicata) {
    this.DM_Serie_Duplicata = DM_Serie_Duplicata;
  }
  public String getDM_Serie_Duplicata() {
    return DM_Serie_Duplicata;
  }
  public void setVL_Duplicata(double VL_Duplicata) {
    this.VL_Duplicata = VL_Duplicata;
  }
  public double getVL_Duplicata() {
    return VL_Duplicata;
  }
  public void setVL_Total_Frete(double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }
  public double getVL_Total_Frete() {
    return VL_Total_Frete;
  }
  public void setVL_Base_Calculo_ICMS(double VL_Base_Calculo_ICMS) {
    this.VL_Base_Calculo_ICMS = VL_Base_Calculo_ICMS;
  }
  public double getVL_Base_Calculo_ICMS() {
    return VL_Base_Calculo_ICMS;
  }
  public void setPE_Aliquota_ICMS(double PE_Aliquota_ICMS) {
    this.PE_Aliquota_ICMS = PE_Aliquota_ICMS;
  }
  public double getPE_Aliquota_ICMS() {
    return PE_Aliquota_ICMS;
  }
  public void setVL_Frete_Peso(double VL_Frete_Peso) {
    this.VL_Frete_Peso = VL_Frete_Peso;
  }
  public double getVL_Frete_Peso() {
    return VL_Frete_Peso;
  }
  public void setVL_Frete_Valor(double VL_Frete_Valor) {
    this.VL_Frete_Valor = VL_Frete_Valor;
  }
  public double getVL_Frete_Valor() {
    return VL_Frete_Valor;
  }
  public void setVL_SEC_CAT(double VL_SEC_CAT) {
    this.VL_SEC_CAT = VL_SEC_CAT;
  }
  public double getVL_SEC_CAT() {
    return VL_SEC_CAT;
  }
  public void setVL_ITR(double VL_ITR) {
    this.VL_ITR = VL_ITR;
  }
  public double getVL_ITR() {
    return VL_ITR;
  }
  public void setVL_Despacho(double VL_Despacho) {
    this.VL_Despacho = VL_Despacho;
  }
  public double getVL_Despacho() {
    return VL_Despacho;
  }
  public void setVL_Pedagio(double VL_Pedagio) {
    this.VL_Pedagio = VL_Pedagio;
  }
  public double getVL_Pedagio() {
    return VL_Pedagio;
  }
  public void setVL_Ademe(double VL_Ademe) {
    this.VL_Ademe = VL_Ademe;
  }
  public double getVL_Ademe() {
    return VL_Ademe;
  }
  public void setCD_CFO_Conhecimento(String CD_CFO_Conhecimento) {
    this.CD_CFO_Conhecimento = CD_CFO_Conhecimento;
  }
  public String getCD_CFO_Conhecimento() {
    return CD_CFO_Conhecimento;
  }
  public void setDT_Emissao_Conhecimento(String DT_Emissao_Conhecimento) {
    this.DT_Emissao_Conhecimento = DT_Emissao_Conhecimento;
  }
  public String getDT_Emissao_Conhecimento() {
    return DT_Emissao_Conhecimento;
  }
  public void setNR_Peso_Conhecimento(double NR_Peso_Conhecimento) {
    this.NR_Peso_Conhecimento = NR_Peso_Conhecimento;
  }
  public double getNR_Peso_Conhecimento() {
    return NR_Peso_Conhecimento;
  }
  public void setDM_Tipo_Pagamento(String DM_Tipo_Pagamento) {
    this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
  }
  public String getDM_Tipo_Pagamento() {
    return DM_Tipo_Pagamento;
  }
  public void setDM_Substituicao_Tributaria(String DM_Substituicao_Tributaria) {
    this.DM_Substituicao_Tributaria = DM_Substituicao_Tributaria;
  }
  public String getDM_Substituicao_Tributaria() {
    return DM_Substituicao_Tributaria;
  }
  public void setOid_Pessoa_Unidade(String oid_Pessoa_Unidade) {
    this.oid_Pessoa_Unidade = oid_Pessoa_Unidade;
  }
  public String getOid_Pessoa_Unidade() {
    return oid_Pessoa_Unidade;
  }
  public void setCD_Ocorrencia(String CD_Ocorrencia) {
    this.CD_Ocorrencia = CD_Ocorrencia;
  }
  public String getCD_Ocorrencia() {
    return CD_Ocorrencia;
  }
  public void setOid_Padrao(long oid_Padrao) {
    this.oid_Padrao = oid_Padrao;
  }
  public long getOid_Padrao() {
    return oid_Padrao;
  }
  public void setDT_Ocorrencia(String DT_Ocorrencia) {
    this.DT_Ocorrencia = DT_Ocorrencia;
  }
  public String getDT_Ocorrencia() {
    return DT_Ocorrencia;
  }
  public void setHR_Ocorrencia(String HR_Ocorrencia) {
    this.HR_Ocorrencia = HR_Ocorrencia;
  }
  public String getHR_Ocorrencia() {
    return HR_Ocorrencia;
  }
  public void setTX_Observacao(String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }
  public String getTX_Observacao() {
    return TX_Observacao;
  }
  public void setOid_Pessoa_Destinatario(String oid_Pessoa_Destinatario) {
    this.oid_Pessoa_Destinatario = oid_Pessoa_Destinatario;
  }
  public String getOid_Pessoa_Destinatario() {
    return oid_Pessoa_Destinatario;
  }
  public void setOid_Pessoa_Consignatario(String oid_Pessoa_Consignatario) {
    this.oid_Pessoa_Consignatario = oid_Pessoa_Consignatario;
  }
  public String getOid_Pessoa_Consignatario() {
    return oid_Pessoa_Consignatario;
  }
  public void setOid_Pessoa_Entregadora(String oid_Pessoa_Entregadora) {
    this.oid_Pessoa_Entregadora = oid_Pessoa_Entregadora;
  }
  public String getOid_Pessoa_Entregadora() {
    return oid_Pessoa_Entregadora;
  }
  public void setOid_Pessoa_Redespacho(String oid_Pessoa_Redespacho) {
    this.oid_Pessoa_Redespacho = oid_Pessoa_Redespacho;
  }
  public String getOid_Pessoa_Redespacho() {
    return oid_Pessoa_Redespacho;
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
  public void setNM_Natureza(String NM_Natureza) {
    this.NM_Natureza = NM_Natureza;
  }
  public String getNM_Natureza() {
    return NM_Natureza;
  }
  public void setHR_Emissao(String HR_Emissao) {
    this.HR_Emissao = HR_Emissao;
  }
  public String getHR_Emissao() {
    return HR_Emissao;
  }
  public void setNM_Produto(String NM_Produto) {
    this.NM_Produto = NM_Produto;
  }
  public String getNM_Produto() {
    return NM_Produto;
  }
  public void setNR_Fatura(String NR_Fatura) {
    this.NR_Fatura = NR_Fatura;
  }
  public String getNR_Fatura() {
    return NR_Fatura;
  }
  public void setDT_Coleta(String DT_Coleta) {
    this.DT_Coleta = DT_Coleta;
  }
  public String getDT_Coleta() {
    return DT_Coleta;
  }
  public void setHR_Coleta(String HR_Coleta) {
    this.HR_Coleta = HR_Coleta;
  }
  public String getHR_Coleta() {
    return HR_Coleta;
  }
  public void setDT_Embarque(String DT_Embarque) {
    this.DT_Embarque = DT_Embarque;
  }
  public String getDT_Embarque() {
    return DT_Embarque;
  }
  public void setHR_Embarque(String HR_Embarque) {
    this.HR_Embarque = HR_Embarque;
  }
  public String getHR_Embarque() {
    return HR_Embarque;
  }
  public void setNR_Manifesto(String NR_Manifesto) {
    this.NR_Manifesto = NR_Manifesto;
  }
  public String getNR_Manifesto() {
    return NR_Manifesto;
  }
  public void setDM_Tipo_Conhecimento(String DM_Tipo_Conhecimento) {
    this.DM_Tipo_Conhecimento = DM_Tipo_Conhecimento;
  }
  public String getDM_Tipo_Conhecimento() {
    return DM_Tipo_Conhecimento;
  }
  public void setVL_Outros1(double VL_Outros1) {
    this.VL_Outros1 = VL_Outros1;
  }
  public double getVL_Outros1() {
    return VL_Outros1;
  }
  public void setNM_Unidade_Entregadora(String NM_Unidade_Entregadora) {
    this.NM_Unidade_Entregadora = NM_Unidade_Entregadora;
  }
  public String getNM_Unidade_Entregadora() {
    return NM_Unidade_Entregadora;
  }
  public void setNR_Pedido(String NR_Pedido) {
    this.NR_Pedido = NR_Pedido;
  }
  public String getNR_Pedido() {
    return NR_Pedido;
  }
  public void setNR_Volume_Nota_Fiscal(double NR_Volume_Nota_Fiscal) {
    this.NR_Volume_Nota_Fiscal = NR_Volume_Nota_Fiscal;
  }
  public double getNR_Volume_Nota_Fiscal() {
    return NR_Volume_Nota_Fiscal;
  }
  public void setOid_Pessoa_Pagador(String oid_Pessoa_Pagador) {
    this.oid_Pessoa_Pagador = oid_Pessoa_Pagador;
  }
  public String getOid_Pessoa_Pagador() {
    return oid_Pessoa_Pagador;
  }
  public void setDT_Entrega(String DT_Entrega) {
    this.DT_Entrega = DT_Entrega;
  }
  public String getDT_Entrega() {
    return DT_Entrega;
  }
  public void setHR_Entrega(String HR_Entrega) {
    this.HR_Entrega = HR_Entrega;
  }
  public String getHR_Entrega() {
    return HR_Entrega;
  }
  public void setDM_Tipo_Ocorrencia(String DM_Tipo_Ocorrencia) {
    this.DM_Tipo_Ocorrencia = DM_Tipo_Ocorrencia;
  }
  public String getDM_Tipo_Ocorrencia() {
    return DM_Tipo_Ocorrencia;
  }
  public void setOid_Seguradora(long oid_Seguradora) {
    this.oid_Seguradora = oid_Seguradora;
  }
  public long getOid_Seguradora() {
    return oid_Seguradora;
  }
  public void setNR_Placa(String NR_Placa) {
    this.NR_Placa = NR_Placa;
  }
  public String getNR_Placa() {
    return NR_Placa;
  }
  public void setUF_Origem(String UF_Origem) {
    this.UF_Origem = UF_Origem;
  }
  public String getUF_Origem() {
    return UF_Origem;
  }
  public void setUF_Destino(String UF_Destino) {
    this.UF_Destino = UF_Destino;
  }
  public String getUF_Destino() {
    return UF_Destino;
  }
  public void setCD_Unidade_Entregadora(String CD_Unidade_Entregadora) {
    this.CD_Unidade_Entregadora = CD_Unidade_Entregadora;
  }
  public String getCD_Unidade_Entregadora() {
    return CD_Unidade_Entregadora;
  }
  public void setDT_Ocorrencia_Lancada(String DT_Ocorrencia_Lancada) {
    this.DT_Ocorrencia_Lancada = DT_Ocorrencia_Lancada;
  }
  public String getDT_Ocorrencia_Lancada() {
    return DT_Ocorrencia_Lancada;
  }
  public void setHR_Ocorrencia_Lancada(String HR_Ocorrencia_Lancada) {
    this.HR_Ocorrencia_Lancada = HR_Ocorrencia_Lancada;
  }
  public String getHR_Ocorrencia_Lancada() {
    return HR_Ocorrencia_Lancada;
  }
  public void setOid_Unidade(long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }

  public void setDM_Tipo_Frete (String DM_Tipo_Frete) {
    this.DM_Tipo_Frete = DM_Tipo_Frete;
  }

  public void setDM_Grupo_Economico (String DM_Grupo_Economico) {
    this.DM_Grupo_Economico = DM_Grupo_Economico;
  }

  public void setOID_Carreta2(String OID_Carreta2) {
    this.OID_Carreta2 = OID_Carreta2;
  }

  public void setNM_Remetente(String NM_Remetente) {

    this.NM_Remetente = NM_Remetente;
  }

  public void setNM_Destinatario(String NM_Destinatario) {

    this.NM_Destinatario = NM_Destinatario;
  }

  public void setOID_Cidade_Origem (int OID_Cidade_Origem) {
    this.OID_Cidade_Origem = OID_Cidade_Origem;
  }

  public void setOID_Cidade_Destino (int OID_Cidade_Destino) {
    this.OID_Cidade_Destino = OID_Cidade_Destino;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public void setDM_Tipo_Transporte (String DM_Tipo_Transporte) {
    this.DM_Tipo_Transporte = DM_Tipo_Transporte;
  }

  public long getOid_Unidade() {
    return oid_Unidade;
  }

  public String getDM_Tipo_Frete () {
    return DM_Tipo_Frete;
  }

  public String getDM_Grupo_Economico () {
    return DM_Grupo_Economico;
  }

  public String getOID_Carreta2() {
    return OID_Carreta2;
  }

  public String getNM_Remetente() {

    return NM_Remetente;
  }

  public String getNM_Destinatario() {

    return NM_Destinatario;
  }

  public int getOID_Cidade_Origem () {
    return OID_Cidade_Origem;
  }

  public int getOID_Cidade_Destino () {
    return OID_Cidade_Destino;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public String getDM_Tipo_Transporte () {
    return DM_Tipo_Transporte;
  }

  public void setNM_Cidade_Origem(String NM_Cidade_Origem) {
    this.NM_Cidade_Origem = NM_Cidade_Origem;
  }
  public String getNM_Cidade_Origem() {
    return NM_Cidade_Origem;
  }
  public void setNM_Cidade_Destino(String NM_Cidade_Destino) {
    this.NM_Cidade_Destino = NM_Cidade_Destino;
  }
  public String getNM_Cidade_Destino() {
    return NM_Cidade_Destino;
  }
  public void setOID_Veiculo(String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }
  public String getOID_Veiculo() {
    return OID_Veiculo;
  }
  public void setOID_Carreta(String OID_Carreta) {
    this.OID_Carreta = OID_Carreta;
  }
  public String getOID_Carreta() {
    return OID_Carreta;
  }
  public void setNM_Razao_Social(String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }
  public String getNM_Razao_Social() {
    return NM_Razao_Social;
  }
  public void setDM_Isencao_Seguro(String DM_Isencao_Seguro) {
    this.DM_Isencao_Seguro = DM_Isencao_Seguro;
  }
  public String getDM_Isencao_Seguro() {
    return DM_Isencao_Seguro;
  }
  public void setNR_Peso_Cubado_Conhecimento(double NR_Peso_Cubado_Conhecimento) {
    this.NR_Peso_Cubado_Conhecimento = NR_Peso_Cubado_Conhecimento;
  }
  public double getNR_Peso_Cubado_Conhecimento() {
    return NR_Peso_Cubado_Conhecimento;
  }
  public void setVL_ICMS_Conhecimento(double VL_ICMS_Conhecimento) {
    this.VL_ICMS_Conhecimento = VL_ICMS_Conhecimento;
  }
  public double getVL_ICMS_Conhecimento() {
    return VL_ICMS_Conhecimento;
  }
  public void setNR_Lote(String NR_Lote) {
    this.NR_Lote = NR_Lote;
  }
  public String getNR_Lote() {
    return NR_Lote;
  }
public String getCD_Estado_Destino() {
	return CD_Estado_Destino;
}
public void setCD_Estado_Destino(String estado_Destino) {
	CD_Estado_Destino = estado_Destino;
}
public String getCD_Estado_Origem() {
	return CD_Estado_Origem;
}
public void setCD_Estado_Origem(String estado_Origem) {
	CD_Estado_Origem = estado_Origem;
}
public String getCD_Estado_Unidade() {
	return CD_Estado_Unidade;
}
public void setCD_Estado_Unidade(String estado_Unidade) {
	CD_Estado_Unidade = estado_Unidade;
}
public double getVL_Saldo() {
	return VL_Saldo;
}
public void setVL_Saldo(double saldo) {
	VL_Saldo = saldo;
}
public double getNR_Peso_Cubado_Nota_Fiscal() {
	return NR_Peso_Cubado_Nota_Fiscal;
}
public void setNR_Peso_Cubado_Nota_Fiscal(double peso_Cubado_Nota_Fiscal) {
	NR_Peso_Cubado_Nota_Fiscal = peso_Cubado_Nota_Fiscal;
}
public String getOID_Manifesto() {
	return OID_Manifesto;
}
public void setOID_Manifesto(String manifesto) {
	OID_Manifesto = manifesto;
}
public String getDM_Tipo_Padrao() {
	return DM_Tipo_Padrao;
}
public void setDM_Tipo_Padrao(String tipo_Padrao) {
	DM_Tipo_Padrao = tipo_Padrao;
}
public double getNR_Quantidade() {
	return NR_Quantidade;
}
public void setNR_Quantidade(double quantidade) {
	NR_Quantidade = quantidade;
}
public String getCD_Unidade_Medida() {
	return CD_Unidade_Medida;
}
public void setCD_Unidade_Medida(String unidade_Medida) {
	CD_Unidade_Medida = unidade_Medida;
}
public double getVL_Produto() {
	return VL_Produto;
}
public void setVL_Produto(double produto) {
	VL_Produto = produto;
}
public Collection getProdutos() {
	return Produtos;
}
public void setProdutos(Collection produtos) {
	Produtos = produtos;
}
public String getOid_Pessoa_edi() {
	return oid_Pessoa_edi;
}
public void setOid_Pessoa_edi(String oid_Pessoa_edi) {
	this.oid_Pessoa_edi = oid_Pessoa_edi;
}
public String getDT_Lancamento_Final() {
	return DT_Lancamento_Final;
}
public void setDT_Lancamento_Final(String lancamento_Final) {
	DT_Lancamento_Final = lancamento_Final;
}
public String getDT_Lancamento_Inicial() {
	return DT_Lancamento_Inicial;
}
public void setDT_Lancamento_Inicial(String lancamento_Inicial) {
	DT_Lancamento_Inicial = lancamento_Inicial;
}


}
