package com.master.ed;

public class Edi_Nota_Fiscal_CTRCED {

  private String DT_Entrada;
  private String NM_Remetente;
  private String NM_Estado_Origem;
  private String NM_Destinatario;
  private String NM_Estado_Destino;
  private double VL_Frete;
  private String DT_Inicial;
  private String DT_Final;
  private String oid_Pessoa;
  private String NM_Endereco;
  private String NM_Bairro;
  private String NR_CEP;
  private String NR_Telefone;
  private String NM_Inscricao_Estadual_Remetente;
  private String NM_Cidade;
  private String NM_Estado;
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
  private String NM_Situacao_Entrega;
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
  private double VL_Total_Frete;
  private double VL_Base_Calculo_ICMS;
  private double PE_Aliquota_ICMS;
  private double VL_Frete_Peso;
  private double VL_Frete_Valor;
  private double VL_SEC_CAT;
  private double VL_ITR;
  private double VL_Despacho;
  private double VL_Pedagio;
  private long NR_Prazo_Entrega;
  private long NR_Dias_Entrega_Realizado;
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
  private double NR_Volumes_Conhecimento;
  private String oid_Pessoa_Pagador;
  private String DT_Entrega;
  private String HR_Entrega;
  private String NM_Localizacao_Entrega;
  private String DM_Tipo_Ocorrencia;
  private long oid_Seguradora;
  private String NR_Placa;
  private String NR_Placa_Carreta;
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
  private String NM_Motorista;
  private String DM_Isencao_Seguro;
  private double NR_Peso_Cubado_Conhecimento;
  private double VL_ICMS_Conhecimento;
  private String NR_Lote;
  private String DM_Tipo_Transporte;
  private String DM_Situacao;
  private int OID_Cidade_Destino;
  private int OID_Cidade_Origem;
  private String DM_Situacao_Cliente;
  private String DM_RCTRC;
  private String DM_RCTR_VI;
  private String DM_RCTR_DC;
  private String DM_RCTA;
  private double VL_Sec_Cat;
  private double VL_TX_Coleta_Entrega;
  private String NM_Tipo_Veiculo;
  private String CD_Unidade;
  private double NR_Peso_Cubado;
  private String CD_Modal;
  private double VL_Entrega;
  private double VL_Coleta;
  private String DM_Relatorio;
  private String NM_Cia_Aerea;
  private String NM_Centro_Custo;
  private String NM_Inscricao_Estadual_Destinatario;
  private String NR_CNPJ_CPF_Remetente;
  private String NR_CNPJ_CPF_Destinatario;
  private String NR_CNPJ_CPF_Pagador;

  public String getNM_Bairro () {
    return NM_Bairro;
  }

  public void setNM_Bairro (String bairro) {
    NM_Bairro = bairro;
  }

  public String getNM_Cidade () {
    return NM_Cidade;
  }

  public void setNM_Cidade (String cidade) {
    NM_Cidade = cidade;
  }

  public String getNM_Endereco () {
    return NM_Endereco;
  }

  public void setNM_Endereco (String endereco) {
    NM_Endereco = endereco;
  }

  public String getNM_Estado () {
    return NM_Estado;
  }

  public void setNM_Estado (String estado) {
    NM_Estado = estado;
  }

  public String getNM_Inscricao_Estadual_Remetente () {

    return NM_Inscricao_Estadual_Remetente;
  }

  public void setNM_Inscricao_Estadual_Remetente (String NM_Inscricao_Estadual_Remetente) {
    NM_Inscricao_Estadual_Remetente = NM_Inscricao_Estadual_Remetente;
    this.NM_Inscricao_Estadual_Remetente = NM_Inscricao_Estadual_Remetente;
  }

  public String getNR_CEP () {
    return NR_CEP;
  }

  public void setNR_CEP (String nr_cep) {
    NR_CEP = nr_cep;
  }

  public String getNR_Telefone () {
    return NR_Telefone;
  }

  public void setNR_Telefone (String telefone) {
    NR_Telefone = telefone;
  }

  public String getDT_Inicial () {
    return DT_Inicial;
  }

  public void setDT_Inicial (String DT_Inicial) {
    this.DT_Inicial = DT_Inicial;
  }

  public void setDT_Final (String DT_Final) {
    this.DT_Final = DT_Final;
  }

  public String getDT_Final () {
    return DT_Final;
  }

  public void setOid_Pessoa (String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }

  public String getOid_Pessoa () {
    return oid_Pessoa;
  }

  public void setOid_Empresa (long oid_Empresa) {
    this.oid_Empresa = oid_Empresa;
  }

  public long getOid_Empresa () {
    return oid_Empresa;
  }

  public void setNM_Identificador_Registro (String NM_Identificador_Registro) {
    this.NM_Identificador_Registro = NM_Identificador_Registro;
  }

  public String getNM_Identificador_Registro () {
    return NM_Identificador_Registro;
  }

  public void setNM_Filial_Duplicata (String NM_Filial_Duplicata) {
    this.NM_Filial_Duplicata = NM_Filial_Duplicata;
  }

  public String getNM_Filial_Duplicata () {
    return NM_Filial_Duplicata;
  }

  public void setNM_Tipo_Documento (String NM_Tipo_Documento) {
    this.NM_Tipo_Documento = NM_Tipo_Documento;
  }

  public String getNM_Tipo_Documento () {
    return NM_Tipo_Documento;
  }

  public void setNR_Serie_Duplicata (String NR_Serie_Duplicata) {
    this.NR_Serie_Duplicata = NR_Serie_Duplicata;
  }

  public String getNR_Serie_Duplicata () {
    return NR_Serie_Duplicata;
  }

  public void setNR_Duplicata (String NR_Duplicata) {
    this.NR_Duplicata = NR_Duplicata;
  }

  public String getNR_Duplicata () {
    return NR_Duplicata;
  }

  public void setDT_Emissao_Duplicata (String DT_Emissao_Duplicata) {
    this.DT_Emissao_Duplicata = DT_Emissao_Duplicata;
  }

  public String getDT_Emissao_Duplicata () {
    return DT_Emissao_Duplicata;
  }

  public void setDT_Vencimento_Duplicata (String DT_Vencimento_Duplicata) {
    this.DT_Vencimento_Duplicata = DT_Vencimento_Duplicata;
  }

  public String getDT_Vencimento_Duplicata () {
    return DT_Vencimento_Duplicata;
  }

  public void setVL_Documento (double VL_Documento) {
    this.VL_Documento = VL_Documento;
  }

  public double getVL_Documento () {
    return VL_Documento;
  }

  public void setNM_Tipo_Cobranca (String NM_Tipo_Cobranca) {
    this.NM_Tipo_Cobranca = NM_Tipo_Cobranca;
  }

  public String getNM_Tipo_Cobranca () {
    return NM_Tipo_Cobranca;
  }

  public void setVL_ICMS (double VL_ICMS) {
    this.VL_ICMS = VL_ICMS;
  }

  public double getVL_ICMS () {
    return VL_ICMS;
  }

  public void setVL_Juro_Mora_Dia (double VL_Juro_Mora_Dia) {
    this.VL_Juro_Mora_Dia = VL_Juro_Mora_Dia;
  }

  public double getVL_Juro_Mora_Dia () {
    return VL_Juro_Mora_Dia;
  }

  public void setVL_Desconto (double VL_Desconto) {
    this.VL_Desconto = VL_Desconto;
  }

  public double getVL_Desconto () {
    return VL_Desconto;
  }

  public void setNM_Agente_Cobranca (String NM_Agente_Cobranca) {
    this.NM_Agente_Cobranca = NM_Agente_Cobranca;
  }

  public String getNM_Agente_Cobranca () {
    return NM_Agente_Cobranca;
  }

  public void setNM_Agencia (String NM_Agencia) {
    this.NM_Agencia = NM_Agencia;
  }

  public String getNM_Agencia () {
    return NM_Agencia;
  }

  public void setNM_Conta_Corrente (String NM_Conta_Corrente) {
    this.NM_Conta_Corrente = NM_Conta_Corrente;
  }

  public String getNM_Conta_Corrente () {
    return NM_Conta_Corrente;
  }

  public void setNM_Filial_Conhecimento (String NM_Filial_Conhecimento) {
    this.NM_Filial_Conhecimento = NM_Filial_Conhecimento;
  }

  public String getNM_Filial_Conhecimento () {
    return NM_Filial_Conhecimento;
  }

  public void setNR_Serie_Conhecimento (String NR_Serie_Conhecimento) {
    this.NR_Serie_Conhecimento = NR_Serie_Conhecimento;
  }

  public String getNR_Serie_Conhecimento () {
    return NR_Serie_Conhecimento;
  }

  public void setNR_Conhecimento (String NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }

  public String getNR_Conhecimento () {
    return NR_Conhecimento;
  }

  public void setNR_Nota_Fiscal (String NR_Nota_Fiscal) {
    this.NR_Nota_Fiscal = NR_Nota_Fiscal;
  }


  public void setNR_Serie_Nota_Fiscal (String NR_Serie_Nota_Fiscal) {
    this.NR_Serie_Nota_Fiscal = NR_Serie_Nota_Fiscal;
  }

  public String getNR_Serie_Nota_Fiscal () {
    return NR_Serie_Nota_Fiscal;
  }

  public void setDT_Emissao_Nota_Fiscal (String DT_Emissao_Nota_Fiscal) {
    this.DT_Emissao_Nota_Fiscal = DT_Emissao_Nota_Fiscal;
  }

  public String getDT_Emissao_Nota_Fiscal () {
    return DT_Emissao_Nota_Fiscal;
  }

  public void setNR_Peso_Nota_Fiscal (double NR_Peso_Nota_Fiscal) {
    this.NR_Peso_Nota_Fiscal = NR_Peso_Nota_Fiscal;
  }

  public double getNR_Peso_Nota_Fiscal () {
    return NR_Peso_Nota_Fiscal;
  }

  public void setVL_Nota_Fiscal (double VL_Nota_Fiscal) {
    this.VL_Nota_Fiscal = VL_Nota_Fiscal;
  }

  public double getVL_Nota_Fiscal () {
    return VL_Nota_Fiscal;
  }

  public void setDM_Serie_Duplicata (String DM_Serie_Duplicata) {
    this.DM_Serie_Duplicata = DM_Serie_Duplicata;
  }

  public String getDM_Serie_Duplicata () {
    return DM_Serie_Duplicata;
  }

  public void setVL_Duplicata (double VL_Duplicata) {
    this.VL_Duplicata = VL_Duplicata;
  }

  public double getVL_Duplicata () {
    return VL_Duplicata;
  }

  public void setVL_Total_Frete (double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }

  public double getVL_Total_Frete () {
    return VL_Total_Frete;
  }

  public void setVL_Base_Calculo_ICMS (double VL_Base_Calculo_ICMS) {
    this.VL_Base_Calculo_ICMS = VL_Base_Calculo_ICMS;
  }

  public double getVL_Base_Calculo_ICMS () {
    return VL_Base_Calculo_ICMS;
  }

  public void setPE_Aliquota_ICMS (double PE_Aliquota_ICMS) {
    this.PE_Aliquota_ICMS = PE_Aliquota_ICMS;
  }

  public double getPE_Aliquota_ICMS () {
    return PE_Aliquota_ICMS;
  }

  public void setVL_Frete_Peso (double VL_Frete_Peso) {
    this.VL_Frete_Peso = VL_Frete_Peso;
  }

  public double getVL_Frete_Peso () {
    return VL_Frete_Peso;
  }

  public void setVL_Frete_Valor (double VL_Frete_Valor) {
    this.VL_Frete_Valor = VL_Frete_Valor;
  }

  public double getVL_Frete_Valor () {
    return VL_Frete_Valor;
  }

  public void setVL_SEC_CAT (double VL_SEC_CAT) {
    this.VL_SEC_CAT = VL_SEC_CAT;
  }

  public double getVL_SEC_CAT () {
    return VL_SEC_CAT;
  }

  public void setVL_ITR (double VL_ITR) {
    this.VL_ITR = VL_ITR;
  }

  public double getVL_ITR () {
    return VL_ITR;
  }

  public void setVL_Despacho (double VL_Despacho) {
    this.VL_Despacho = VL_Despacho;
  }

  public double getVL_Despacho () {
    return VL_Despacho;
  }

  public void setVL_Pedagio (double VL_Pedagio) {
    this.VL_Pedagio = VL_Pedagio;
  }

  public double getVL_Pedagio () {
    return VL_Pedagio;
  }

  public void setVL_Ademe (double VL_Ademe) {
    this.VL_Ademe = VL_Ademe;
  }

  public double getVL_Ademe () {
    return VL_Ademe;
  }

  public void setCD_CFO_Conhecimento (String CD_CFO_Conhecimento) {
    this.CD_CFO_Conhecimento = CD_CFO_Conhecimento;
  }

  public String getCD_CFO_Conhecimento () {
    return CD_CFO_Conhecimento;
  }

  public void setDT_Emissao_Conhecimento (String DT_Emissao_Conhecimento) {
    this.DT_Emissao_Conhecimento = DT_Emissao_Conhecimento;
  }

  public String getDT_Emissao_Conhecimento () {
    return DT_Emissao_Conhecimento;
  }

  public void setNR_Peso_Conhecimento (double NR_Peso_Conhecimento) {
    this.NR_Peso_Conhecimento = NR_Peso_Conhecimento;
  }

  public double getNR_Peso_Conhecimento () {
    return NR_Peso_Conhecimento;
  }

  public void setDM_Tipo_Pagamento (String DM_Tipo_Pagamento) {
    this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
  }

  public String getDM_Tipo_Pagamento () {
    return DM_Tipo_Pagamento;
  }

  public void setDM_Substituicao_Tributaria (String DM_Substituicao_Tributaria) {
    this.DM_Substituicao_Tributaria = DM_Substituicao_Tributaria;
  }

  public String getDM_Substituicao_Tributaria () {
    return DM_Substituicao_Tributaria;
  }

  public void setOid_Pessoa_Unidade (String oid_Pessoa_Unidade) {
    this.oid_Pessoa_Unidade = oid_Pessoa_Unidade;
  }

  public String getOid_Pessoa_Unidade () {
    return oid_Pessoa_Unidade;
  }

  public void setCD_Ocorrencia (String CD_Ocorrencia) {
    this.CD_Ocorrencia = CD_Ocorrencia;
  }

  public String getCD_Ocorrencia () {
    return CD_Ocorrencia;
  }

  public void setOid_Padrao (long oid_Padrao) {
    this.oid_Padrao = oid_Padrao;
  }

  public long getOid_Padrao () {
    return oid_Padrao;
  }

  public void setDT_Ocorrencia (String DT_Ocorrencia) {
    this.DT_Ocorrencia = DT_Ocorrencia;
  }

  public String getDT_Ocorrencia () {
    return DT_Ocorrencia;
  }

  public void setHR_Ocorrencia (String HR_Ocorrencia) {
    this.HR_Ocorrencia = HR_Ocorrencia;
  }

  public String getHR_Ocorrencia () {
    return HR_Ocorrencia;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setOid_Pessoa_Destinatario (String oid_Pessoa_Destinatario) {
    this.oid_Pessoa_Destinatario = oid_Pessoa_Destinatario;
  }

  public String getOid_Pessoa_Destinatario () {
    return oid_Pessoa_Destinatario;
  }

  public void setOid_Pessoa_Consignatario (String oid_Pessoa_Consignatario) {
    this.oid_Pessoa_Consignatario = oid_Pessoa_Consignatario;
  }

  public String getOid_Pessoa_Consignatario () {
    return oid_Pessoa_Consignatario;
  }

  public void setOid_Pessoa_Entregadora (String oid_Pessoa_Entregadora) {
    this.oid_Pessoa_Entregadora = oid_Pessoa_Entregadora;
  }

  public String getOid_Pessoa_Entregadora () {
    return oid_Pessoa_Entregadora;
  }

  public void setOid_Pessoa_Redespacho (String oid_Pessoa_Redespacho) {
    this.oid_Pessoa_Redespacho = oid_Pessoa_Redespacho;
  }

  public String getOid_Pessoa_Redespacho () {
    return oid_Pessoa_Redespacho;
  }

  public void setDT_Previsao_Entrega (String DT_Previsao_Entrega) {
    this.DT_Previsao_Entrega = DT_Previsao_Entrega;
  }

  public String getDT_Previsao_Entrega () {
    return DT_Previsao_Entrega;
  }

  public void setHR_Previsao_Entrega (String HR_Previsao_Entrega) {
    this.HR_Previsao_Entrega = HR_Previsao_Entrega;
  }

  public String getHR_Previsao_Entrega () {
    return HR_Previsao_Entrega;
  }

  public void setNM_Natureza (String NM_Natureza) {
    this.NM_Natureza = NM_Natureza;
  }

  public String getNM_Natureza () {
    return NM_Natureza;
  }

  public void setHR_Emissao (String HR_Emissao) {
    this.HR_Emissao = HR_Emissao;
  }

  public String getHR_Emissao () {
    return HR_Emissao;
  }

  public void setNM_Produto (String NM_Produto) {
    this.NM_Produto = NM_Produto;
  }

  public String getNM_Produto () {
    return NM_Produto;
  }

  public void setNR_Fatura (String NR_Fatura) {
    this.NR_Fatura = NR_Fatura;
  }

  public String getNR_Fatura () {
    return NR_Fatura;
  }

  public void setDT_Coleta (String DT_Coleta) {
    this.DT_Coleta = DT_Coleta;
  }

  public String getDT_Coleta () {
    return DT_Coleta;
  }

  public void setHR_Coleta (String HR_Coleta) {
    this.HR_Coleta = HR_Coleta;
  }

  public String getHR_Coleta () {
    return HR_Coleta;
  }

  public void setDT_Embarque (String DT_Embarque) {
    this.DT_Embarque = DT_Embarque;
  }

  public String getDT_Embarque () {
    return DT_Embarque;
  }

  public void setHR_Embarque (String HR_Embarque) {
    this.HR_Embarque = HR_Embarque;
  }

  public String getHR_Embarque () {
    return HR_Embarque;
  }

  public void setNR_Manifesto (String NR_Manifesto) {
    this.NR_Manifesto = NR_Manifesto;
  }

  public String getNR_Manifesto () {
    return NR_Manifesto;
  }

  public void setDM_Tipo_Conhecimento (String DM_Tipo_Conhecimento) {
    this.DM_Tipo_Conhecimento = DM_Tipo_Conhecimento;
  }

  public String getDM_Tipo_Conhecimento () {
    return DM_Tipo_Conhecimento;
  }

  public void setVL_Outros1 (double VL_Outros1) {
    this.VL_Outros1 = VL_Outros1;
  }

  public double getVL_Outros1 () {
    return VL_Outros1;
  }

  public void setNM_Unidade_Entregadora (String NM_Unidade_Entregadora) {
    this.NM_Unidade_Entregadora = NM_Unidade_Entregadora;
  }

  public String getNM_Unidade_Entregadora () {
    return NM_Unidade_Entregadora;
  }

  public void setNR_Pedido (String NR_Pedido) {
    this.NR_Pedido = NR_Pedido;
  }

  public String getNR_Pedido () {
    return NR_Pedido;
  }

  public void setNR_Volume_Nota_Fiscal (double NR_Volume_Nota_Fiscal) {
    this.NR_Volume_Nota_Fiscal = NR_Volume_Nota_Fiscal;
  }

  public double getNR_Volume_Nota_Fiscal () {
    return NR_Volume_Nota_Fiscal;
  }

  public void setOid_Pessoa_Pagador (String oid_Pessoa_Pagador) {
    this.oid_Pessoa_Pagador = oid_Pessoa_Pagador;
  }

  public String getOid_Pessoa_Pagador () {
    return oid_Pessoa_Pagador;
  }

  public void setDT_Entrega (String DT_Entrega) {
    this.DT_Entrega = DT_Entrega;
  }

  public String getDT_Entrega () {
    return DT_Entrega;
  }

  public void setHR_Entrega (String HR_Entrega) {
    this.HR_Entrega = HR_Entrega;
  }

  public String getHR_Entrega () {
    return HR_Entrega;
  }

  public void setDM_Tipo_Ocorrencia (String DM_Tipo_Ocorrencia) {
    this.DM_Tipo_Ocorrencia = DM_Tipo_Ocorrencia;
  }

  public String getDM_Tipo_Ocorrencia () {
    return DM_Tipo_Ocorrencia;
  }

  public void setOid_Seguradora (long oid_Seguradora) {
    this.oid_Seguradora = oid_Seguradora;
  }

  public long getOid_Seguradora () {
    return oid_Seguradora;
  }

  public void setNR_Placa (String NR_Placa) {
    this.NR_Placa = NR_Placa;
  }

  public String getNR_Placa () {
    return NR_Placa;
  }

  public void setUF_Origem (String UF_Origem) {
    this.UF_Origem = UF_Origem;
  }

  public String getUF_Origem () {
    return UF_Origem;
  }

  public void setUF_Destino (String UF_Destino) {
    this.UF_Destino = UF_Destino;
  }

  public String getUF_Destino () {
    return UF_Destino;
  }

  public void setCD_Unidade_Entregadora (String CD_Unidade_Entregadora) {
    this.CD_Unidade_Entregadora = CD_Unidade_Entregadora;
  }

  public String getCD_Unidade_Entregadora () {
    return CD_Unidade_Entregadora;
  }

  public void setDT_Ocorrencia_Lancada (String DT_Ocorrencia_Lancada) {
    this.DT_Ocorrencia_Lancada = DT_Ocorrencia_Lancada;
  }

  public String getDT_Ocorrencia_Lancada () {
    return DT_Ocorrencia_Lancada;
  }

  public void setHR_Ocorrencia_Lancada (String HR_Ocorrencia_Lancada) {
    this.HR_Ocorrencia_Lancada = HR_Ocorrencia_Lancada;
  }

  public String getHR_Ocorrencia_Lancada () {
    return HR_Ocorrencia_Lancada;
  }

  public void setOid_Unidade (long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }

  public void setNR_CNPJ_CPF_Pagador (String NR_CNPJ_CPF_Pagador) {
    this.NR_CNPJ_CPF_Pagador = NR_CNPJ_CPF_Pagador;
  }

  public void setNR_CNPJ_CPF_Destinatario (String NR_CNPJ_CPF_Destinatario) {
    this.NR_CNPJ_CPF_Destinatario = NR_CNPJ_CPF_Destinatario;
  }

  public void setNR_CNPJ_CPF_Remetente (String NR_CNPJ_CPF_Remetente) {
    this.NR_CNPJ_CPF_Remetente = NR_CNPJ_CPF_Remetente;
  }

  public void setNM_Inscricao_Estadual_Destinatario (String NM_Inscricao_Estadual_Destinatario) {
    this.NM_Inscricao_Estadual_Destinatario = NM_Inscricao_Estadual_Destinatario;
  }

  public void setNM_Centro_Custo (String NM_Centro_Custo) {
    this.NM_Centro_Custo = NM_Centro_Custo;
  }

  public void setNM_Cia_Aerea (String NM_Cia_Aerea) {
    this.NM_Cia_Aerea = NM_Cia_Aerea;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public void setVL_Coleta (double VL_Coleta) {
    this.VL_Coleta = VL_Coleta;
  }

  public void setVL_Entrega (double VL_Entrega) {
    this.VL_Entrega = VL_Entrega;
  }

  public void setCD_Modal (String CD_Modal) {
    this.CD_Modal = CD_Modal;
  }

  public void setNR_Peso_Cubado (double NR_Peso_Cubado) {
    this.NR_Peso_Cubado = NR_Peso_Cubado;
  }

  public void setNR_Volumes_Conhecimento (double NR_Volumes_Conhecimento) {

    this.NR_Volumes_Conhecimento = NR_Volumes_Conhecimento;
  }

  public void setCD_Unidade (String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }

  public void setNM_Tipo_Veiculo (String NM_Tipo_Veiculo) {
    this.NM_Tipo_Veiculo = NM_Tipo_Veiculo;
  }

  public void setVL_TX_Coleta_Entrega (double VL_TX_Coleta_Entrega) {
    this.VL_TX_Coleta_Entrega = VL_TX_Coleta_Entrega;
  }

  public void setVL_Sec_Cat (double VL_Sec_Cat) {
    this.VL_Sec_Cat = VL_Sec_Cat;
  }

  public void setDM_RCTA (String DM_RCTA) {
    this.DM_RCTA = DM_RCTA;
  }

  public void setDM_RCTR_DC (String DM_RCTR_DC) {
    this.DM_RCTR_DC = DM_RCTR_DC;
  }

  public void setDM_RCTR_VI (String DM_RCTR_VI) {
    this.DM_RCTR_VI = DM_RCTR_VI;
  }

  public void setDM_RCTRC (String DM_RCTRC) {
    this.DM_RCTRC = DM_RCTRC;
  }

  public void setDM_Situacao_Cliente (String DM_Situacao_Cliente) {
    this.DM_Situacao_Cliente = DM_Situacao_Cliente;
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

  public long getOid_Unidade () {
    return oid_Unidade;
  }

  public String getNR_CNPJ_CPF_Pagador () {
    return NR_CNPJ_CPF_Pagador;
  }

  public String getNR_CNPJ_CPF_Destinatario () {
    return NR_CNPJ_CPF_Destinatario;
  }

  public String getNR_CNPJ_CPF_Remetente () {
    return NR_CNPJ_CPF_Remetente;
  }

  public String getNM_Inscricao_Estadual_Destinatario () {
    return NM_Inscricao_Estadual_Destinatario;
  }

  public String getNM_Centro_Custo () {
    return NM_Centro_Custo;
  }

  public String getNM_Cia_Aerea () {
    return NM_Cia_Aerea;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public double getVL_Coleta () {
    return VL_Coleta;
  }

  public double getVL_Entrega () {
    return VL_Entrega;
  }

  public String getCD_Modal () {
    return CD_Modal;
  }

  public double getNR_Peso_Cubado () {
    return NR_Peso_Cubado;
  }

  public double getNR_Volumes_Conhecimento () {

    return NR_Volumes_Conhecimento;
  }

  public String getCD_Unidade () {
    return CD_Unidade;
  }

  public String getNM_Tipo_Veiculo () {
    return NM_Tipo_Veiculo;
  }

  public double getVL_TX_Coleta_Entrega () {
    return VL_TX_Coleta_Entrega;
  }

  public double getVL_Sec_Cat () {
    return VL_Sec_Cat;
  }

  public String getDM_RCTRC () {
    return DM_RCTRC;
  }

  public String getDM_RCTR_VI () {
    return DM_RCTR_VI;
  }

  public String getDM_RCTR_DC () {
    return DM_RCTR_DC;
  }

  public String getDM_RCTA () {
    return DM_RCTA;
  }

  public String getDM_Situacao_Cliente () {
    return DM_Situacao_Cliente;
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

  public void setNM_Cidade_Origem (String NM_Cidade_Origem) {
    this.NM_Cidade_Origem = NM_Cidade_Origem;
  }

  public String getNM_Cidade_Origem () {
    return NM_Cidade_Origem;
  }

  public void setNM_Cidade_Destino (String NM_Cidade_Destino) {
    this.NM_Cidade_Destino = NM_Cidade_Destino;
  }

  public String getNM_Cidade_Destino () {
    return NM_Cidade_Destino;
  }

  public void setOID_Veiculo (String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }

  public String getOID_Veiculo () {
    return OID_Veiculo;
  }

  public void setOID_Carreta (String OID_Carreta) {
    this.OID_Carreta = OID_Carreta;
  }

  public String getOID_Carreta () {
    return OID_Carreta;
  }

  public void setNM_Razao_Social (String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public void setDM_Isencao_Seguro (String DM_Isencao_Seguro) {
    this.DM_Isencao_Seguro = DM_Isencao_Seguro;
  }

  public String getDM_Isencao_Seguro () {
    return DM_Isencao_Seguro;
  }

  public void setNR_Peso_Cubado_Conhecimento (double NR_Peso_Cubado_Conhecimento) {
    this.NR_Peso_Cubado_Conhecimento = NR_Peso_Cubado_Conhecimento;
  }

  public double getNR_Peso_Cubado_Conhecimento () {
    return NR_Peso_Cubado_Conhecimento;
  }

  public void setVL_ICMS_Conhecimento (double VL_ICMS_Conhecimento) {
    this.VL_ICMS_Conhecimento = VL_ICMS_Conhecimento;
  }

  public double getVL_ICMS_Conhecimento () {
    return VL_ICMS_Conhecimento;
  }

  public void setNR_Lote (String NR_Lote) {
    this.NR_Lote = NR_Lote;
  }

  public String getNR_Lote () {
    return NR_Lote;
  }

  public String getDT_Entrada () {
    return DT_Entrada;
  }

  public void setDT_Entrada (String entrada) {
    DT_Entrada = entrada;
  }

  public String getNM_Destinatario () {
    return NM_Destinatario;
  }

  public void setNM_Destinatario (String destinatario) {
    NM_Destinatario = destinatario;
  }

  public String getNM_Estado_Destino () {
    return NM_Estado_Destino;
  }

  public void setNM_Estado_Destino (String estado_Destino) {
    NM_Estado_Destino = estado_Destino;
  }

  public String getNM_Estado_Origem () {
    return NM_Estado_Origem;
  }

  public void setNM_Estado_Origem (String estado_Origem) {
    NM_Estado_Origem = estado_Origem;
  }

  public String getNM_Remetente () {
    return NM_Remetente;
  }

  public void setNM_Remetente (String remetente) {
    NM_Remetente = remetente;
  }

  public double getVL_Frete () {
    return VL_Frete;
  }

  public void setVL_Frete (double frete) {
    VL_Frete = frete;
  }

public String getNM_Motorista() {
	return NM_Motorista;
}

public void setNM_Motorista(String motorista) {
	NM_Motorista = motorista;
}

public String getNR_Placa_Carreta() {
	return NR_Placa_Carreta;
}

public void setNR_Placa_Carreta(String carreta) {
	NR_Placa_Carreta = carreta;
}

public String getNM_Localizacao_Entrega() {
	return NM_Localizacao_Entrega;
}

public void setNM_Localizacao_Entrega(String localizacao_Entrega) {
	NM_Localizacao_Entrega = localizacao_Entrega;
}

public long getNR_Dias_Entrega_Realizado() {
	return NR_Dias_Entrega_Realizado;
}


public void setNR_Dias_Entrega_Realizado(long dias_Entrega_Realizado) {
	NR_Dias_Entrega_Realizado = dias_Entrega_Realizado;
}

public long getNR_Prazo_Entrega() {
	return NR_Prazo_Entrega;
}

public void setNR_Prazo_Entrega(long prazo_Entrega) {
	NR_Prazo_Entrega = prazo_Entrega;
}

public String getNR_Nota_Fiscal() {
	return NR_Nota_Fiscal;
}

public String getNM_Situacao_Entrega() {
	return NM_Situacao_Entrega;
}

public void setNM_Situacao_Entrega(String situacao_Entrega) {
	NM_Situacao_Entrega = situacao_Entrega;
}
}
