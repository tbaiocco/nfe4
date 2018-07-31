package com.master.ed;

public class EDI_ContabilED extends MasterED{
  public EDI_ContabilED () {
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

  // Dados remetente
  private String DT_Inicial;
  private String DT_Final;
  private String oid_Pessoa;
  private long oid_Empresa;
  private long oid_Unidade;
  private long oid_Origem;
  private String DM_Modelo_Relatorio;
  private String NM_Identificador_Registro;
  private String NM_Bairro;
  private String NM_Filial_Duplicata;
  private String NM_Tipo_Documento;
  private String NR_Serie_Duplicata;
  private String Dm_Lista_Encerramento;
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
  private double VL_Total_Frete;
  private double VL_Base_Calculo_ICMS;
  private double PE_Aliquota_ICMS;
  private double VL_Frete_Peso;
  private double VL_Frete_Valor;
  private double VL_SEC_CAT;
  private double VL_ITR;
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
  private String NM_Especie;
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
  private String DT_Movimento;
  private double VL_Movimento;
  private String NM_Historico;
  private String NM_Conta_Debito;
  private String NM_Conta_Credito;
  private String NR_Bordero;
  private String NR_Documento;
  private double VL_Lancamento;
  private String DM_Tipo_Lancamento;
  private String DM_Modo_Lancamento;
  private long NR_QT_Contas;
  private long NR_Sequencia2;
  private long NR_Sequencia1;
  private String DM_Debito_Credito;
  private String CD_Acesso;
  private String NM_Codigo_IBGE;
  private String CD_Conta;
  private String CD_Conta_Estrutural;
  private String CD_Historico;
  private long NR_Ordem_Frete;
  private double VL_IRRF;
  private double VL_Taxa_Expediente;
  private double VL_Multa_Atrazo;
  private String DM_Frete;
  private long OID_Acerto;
  private double VL_Set_Senat;
  private double VL_Ordem_Frete;
  private String DT_Emissao;
  private String OID_Ordem_Frete;
  private String OID_Ordem_Principal;
  private String NM_Serie;
  private String OID_Veiculo;
  private double VL_Adiantamento1;
  private double VL_Adiantamento2;
  private double VL_Saldo;
  private String DT_Adiantamento1;
  private String DT_Adiantamento2;
  private String DT_Saldo;
  private double VL_Compromisso;
  private Integer NR_Parcela;
  private String DT_Vencimento;
  private String OID_Pessoa;
  private String DM_Impresso;
  private String DM_Tipo_Pedagio;
  private String Dt_Emissao_Inicial;
  private String Dt_Emissao_Final;
  private String DM_Lista_Conhecimento;
  private long NR_Acerto;
  private double VL_Descontos;
  private String NM_Pagamento;
  private long NR_Qtde_Coleta;
  private long NR_Qtde_Entrega;
  private double VL_Liquido_Ordem_Frete;
  private double VL_INSS_Prestador;
  private double VL_INSS_Empresa;
  private String OID_Manifesto;
  private double PE_ICMS;
  private String NM_Razao_Social;
  private double VL_Total_Frete_CTRC;
  private double NR_Total_Peso_CTRC;
  private double VL_Coleta;
  private double VL_Premio;
  private double VL_Outros;
  private double VL_Vale_Pedagio;
  private double VL_Total;
  private String DM_Adiantamento;
  private double VL_INSS_Pago;
  private String DM_Relatorio;
  private long OID_Empresa;
  private String NM_Empresa;
  private String DT_Master_Recuperado;
  private String DM_Situacao;
  private String OID_Motorista;
  private String OID_Fornecedor;
  private double NR_Peso_Master;
  private double VL_Multa;
  private double VL_Reembolso;
  private double VL_Estadia;
  private int NR_Adiantamento;
  private double VL_Cotacao;
  private double VL_Ordem_Frete_Convertida;
  private double VL_Cotacao_Padrao;
  private String DM_Acerto;
  private String DM_Tipo_Adiantamento;
  private String DM_Tipo_Pagamento_Adiantamento1;
  private String DM_Tipo_Pagamento_Adiantamento2;
  private String DM_Tipo_Pagamento_Saldo;
  private String DM_Tipo_Adiantamento_Desc;
  private String NR_CNPJ_CPF_Empresa;
  private String NM_Razao_Social_Empresa;
  private String NM_Fantasia;
  private String NM_Endereco;
  private String NR_CEP;
  private String NM_Cidade;
  private String CD_Estado;
  private String NM_Inscricao_Estadual;
  private String NR_Telefone;
  private String NM_Fax;
  private String NR_CNPJ_CPF;
  private String DM_Tipo_Ordem_Frete;
  private String NM_Motorista;
  private String DM_Tipo_Pessoa;
  private String CD_Unidade_Contabil;
  private String CD_Conta_Corrente;
  private String CD_Conta_Debito;
  private String CD_Conta_Credito;

  private String DT_Cadastro;
  private String NM_Email;
  private String NM_Site;

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
  public void setNM_Especie(String NM_Especie) {
    this.NM_Especie = NM_Especie;
  }
  public String getNM_Especie() {
    return NM_Especie;
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
  public void setDT_Movimento(String DT_Movimento) {
    this.DT_Movimento = DT_Movimento;
  }
  public String getDT_Movimento() {
    return DT_Movimento;
  }
  public void setVL_Movimento(double VL_Movimento) {
    this.VL_Movimento = VL_Movimento;
  }
  public double getVL_Movimento() {
    return VL_Movimento;
  }
  public void setNM_Historico(String NM_Historico) {
    this.NM_Historico = NM_Historico;
  }
  public String getNM_Historico() {
    return NM_Historico;
  }
  public void setNM_Conta_Debito(String NM_Conta_Debito) {
    this.NM_Conta_Debito = NM_Conta_Debito;
  }
  public String getNM_Conta_Debito() {
    return NM_Conta_Debito;
  }
  public void setNM_Conta_Credito(String NM_Conta_Credito) {
    this.NM_Conta_Credito = NM_Conta_Credito;
  }
  public String getNM_Conta_Credito() {
    return NM_Conta_Credito;
  }
  public void setNR_Bordero(String NR_Bordero) {
    this.NR_Bordero = NR_Bordero;
  }
  public String getNR_Bordero() {
    return NR_Bordero;
  }
  public void setNR_Documento(String NR_Documento) {
    this.NR_Documento = NR_Documento;
  }
  public String getNR_Documento() {
    return NR_Documento;
  }
  public void setVL_Lancamento(double VL_Lancamento) {
    this.VL_Lancamento = VL_Lancamento;
  }
  public double getVL_Lancamento() {
    return VL_Lancamento;
  }
  public void setDM_Tipo_Lancamento(String DM_Tipo_Lancamento) {
    this.DM_Tipo_Lancamento = DM_Tipo_Lancamento;
  }
  public String getDM_Tipo_Lancamento() {
    return DM_Tipo_Lancamento;
  }
  public void setDM_Modo_Lancamento(String DM_Modo_Lancamento) {
    this.DM_Modo_Lancamento = DM_Modo_Lancamento;
  }
  public String getDM_Modo_Lancamento() {
    return DM_Modo_Lancamento;
  }
  public void setNR_QT_Contas(long NR_QT_Contas) {
    this.NR_QT_Contas = NR_QT_Contas;
  }
  public long getNR_QT_Contas() {
    return NR_QT_Contas;
  }
  public void setNR_Sequencia2(long NR_Sequencia2) {
    this.NR_Sequencia2 = NR_Sequencia2;
  }
  public long getNR_Sequencia2() {
    return NR_Sequencia2;
  }
  public void setNR_Sequencia1(long NR_Sequencia1) {
    this.NR_Sequencia1 = NR_Sequencia1;
  }
  public long getNR_Sequencia1() {
    return NR_Sequencia1;
  }
  public void setDM_Debito_Credito(String DM_Debito_Credito) {
    this.DM_Debito_Credito = DM_Debito_Credito;
  }
  public String getDM_Debito_Credito() {
    return DM_Debito_Credito;
  }
  public void setCD_Acesso(String CD_Acesso) {
    this.CD_Acesso = CD_Acesso;
  }
  public String getCD_Acesso() {
    return CD_Acesso;
  }
  public long getOid_Unidade() {
    return oid_Unidade;
  }

  public String getCD_Conta_Credito () {
    return CD_Conta_Credito;
  }

  public String getCD_Conta_Debito () {
    return CD_Conta_Debito;
  }

  public String getCD_Conta_Corrente () {
    return CD_Conta_Corrente;
  }

  public String getCD_Unidade_Contabil () {
    return CD_Unidade_Contabil;
  }

  public String getDM_Tipo_Pessoa () {
    return DM_Tipo_Pessoa;
  }

  public String getNM_Motorista () {
    return NM_Motorista;
  }

  public String getDM_Tipo_Ordem_Frete () {
    return DM_Tipo_Ordem_Frete;
  }

  public String getNR_CNPJ_CPF () {
    return NR_CNPJ_CPF;
  }

  public String getNM_Fax () {
    return NM_Fax;
  }

  public String getNR_Telefone () {

    return NR_Telefone;
  }

  public String getNM_Inscricao_Estadual () {
    return NM_Inscricao_Estadual;
  }

  public String getCD_Estado () {
    return CD_Estado;
  }

  public String getNM_Cidade () {
    return NM_Cidade;
  }

  public String getNR_CEP () {

    return NR_CEP;
  }

  public String getNM_Endereco () {
    return NM_Endereco;
  }

  public String getNM_Fantasia () {
    return NM_Fantasia;
  }

  public String getNM_Razao_Social_Empresa () {
    return NM_Razao_Social_Empresa;
  }

  public String getNR_CNPJ_CPF_Empresa () {

    return NR_CNPJ_CPF_Empresa;
  }

  public double getVL_Vale_Pedagio () {
    return VL_Vale_Pedagio;
  }

  public double getVL_Total_Frete_CTRC () {
    return VL_Total_Frete_CTRC;
  }

  public double getVL_Total () {
    return VL_Total;
  }

  public double getVL_Taxa_Expediente () {
    return VL_Taxa_Expediente;
  }

  public double getVL_Set_Senat () {
    return VL_Set_Senat;
  }

  public double getVL_Saldo () {
    return VL_Saldo;
  }

  public double getVL_Reembolso () {
    return VL_Reembolso;
  }

  public double getVL_Premio () {
    return VL_Premio;
  }

  public double getVL_Liquido_Ordem_Frete () {
    return VL_Liquido_Ordem_Frete;
  }

  public double getVL_Multa () {
    return VL_Multa;
  }

  public double getVL_Multa_Atrazo () {
    return VL_Multa_Atrazo;
  }

  public double getVL_Ordem_Frete () {
    return VL_Ordem_Frete;
  }

  public double getVL_Ordem_Frete_Convertida () {
    return VL_Ordem_Frete_Convertida;
  }

  public double getVL_Outros () {
    return VL_Outros;
  }

  public double getVL_IRRF () {
    return VL_IRRF;
  }

  public double getVL_INSS_Prestador () {
    return VL_INSS_Prestador;
  }

  public double getVL_INSS_Pago () {
    return VL_INSS_Pago;
  }

  public double getVL_INSS_Empresa () {
    return VL_INSS_Empresa;
  }

  public double getVL_Estadia () {
    return VL_Estadia;
  }

  public double getVL_Descontos () {
    return VL_Descontos;
  }

  public double getVL_Cotacao_Padrao () {
    return VL_Cotacao_Padrao;
  }

  public double getVL_Cotacao () {
    return VL_Cotacao;
  }

  public double getVL_Compromisso () {
    return VL_Compromisso;
  }

  public double getVL_Coleta () {
    return VL_Coleta;
  }

  public double getVL_Adiantamento2 () {
    return VL_Adiantamento2;
  }

  public double getVL_Adiantamento1 () {
    return VL_Adiantamento1;
  }

  public double getPE_ICMS () {
    return PE_ICMS;
  }

  public String getOID_Veiculo () {
    return OID_Veiculo;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }

  public String getOID_Ordem_Principal () {
    return OID_Ordem_Principal;
  }

  public String getOID_Ordem_Frete () {
    return OID_Ordem_Frete;
  }

  public String getOID_Motorista () {
    return OID_Motorista;
  }

  public long getOID_Empresa () {
    return OID_Empresa;
  }

  public String getOID_Fornecedor () {
    return OID_Fornecedor;
  }

  public String getOID_Manifesto () {
    return OID_Manifesto;
  }

  public long getOID_Acerto () {
    return OID_Acerto;
  }

  public double getNR_Total_Peso_CTRC () {
    return NR_Total_Peso_CTRC;
  }

  public long getNR_Qtde_Entrega () {
    return NR_Qtde_Entrega;
  }

  public long getNR_Qtde_Coleta () {
    return NR_Qtde_Coleta;
  }

  public double getNR_Peso_Master () {
    return NR_Peso_Master;
  }

  public Integer getNR_Parcela () {
    return NR_Parcela;
  }

  public long getNR_Ordem_Frete () {
    return NR_Ordem_Frete;
  }

  public int getNR_Adiantamento () {
    return NR_Adiantamento;
  }

  public long getNR_Acerto () {
    return NR_Acerto;
  }

  public String getNM_Serie () {
    return NM_Serie;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public String getNM_Pagamento () {
    return NM_Pagamento;
  }

  public String getNM_Empresa () {
    return NM_Empresa;
  }

  public String getDT_Master_Recuperado () {
    return DT_Master_Recuperado;
  }

  public String getDT_Saldo () {
    return DT_Saldo;
  }

  public String getDT_Vencimento () {
    return DT_Vencimento;
  }

  public String getDt_Emissao_Inicial () {
    return Dt_Emissao_Inicial;
  }

  public String getDt_Emissao_Final () {
    return Dt_Emissao_Final;
  }

  public String getDT_Emissao () {
    return DT_Emissao;
  }

  public String getDM_Tipo_Pagamento_Adiantamento1 () {
    return DM_Tipo_Pagamento_Adiantamento1;
  }

  public String getDM_Tipo_Pagamento_Adiantamento2 () {
    return DM_Tipo_Pagamento_Adiantamento2;
  }

  public String getDM_Tipo_Pagamento_Saldo () {
    return DM_Tipo_Pagamento_Saldo;
  }

  public String getDM_Tipo_Pedagio () {
    return DM_Tipo_Pedagio;
  }

  public String getDT_Adiantamento1 () {
    return DT_Adiantamento1;
  }

  public String getDT_Adiantamento2 () {
    return DT_Adiantamento2;
  }

  public String getDM_Tipo_Adiantamento_Desc () {
    return DM_Tipo_Adiantamento_Desc;
  }

  public String getDM_Tipo_Adiantamento () {
    return DM_Tipo_Adiantamento;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public String getDM_Lista_Conhecimento () {
    return DM_Lista_Conhecimento;
  }

  public String getDM_Impresso () {
    return DM_Impresso;
  }

  public String getDM_Frete () {
    return DM_Frete;
  }

  public String getDM_Adiantamento () {
    return DM_Adiantamento;
  }

  public String getDM_Acerto () {
    return DM_Acerto;
  }

  public void setOid_Unidade(long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }

  public void setCD_Conta_Credito (String CD_Conta_Credito) {
    this.CD_Conta_Credito = CD_Conta_Credito;
  }

  public void setCD_Conta_Debito (String CD_Conta_Debito) {
    this.CD_Conta_Debito = CD_Conta_Debito;
  }

  public void setCD_Conta_Corrente (String CD_Conta_Corrente) {
    this.CD_Conta_Corrente = CD_Conta_Corrente;
  }

  public void setCD_Unidade_Contabil (String CD_Unidade_Contabil) {
    this.CD_Unidade_Contabil = CD_Unidade_Contabil;
  }

  public void setDM_Tipo_Pessoa (String DM_Tipo_Pessoa) {
    this.DM_Tipo_Pessoa = DM_Tipo_Pessoa;
  }

  public void setNM_Motorista (String NM_Motorista) {
    this.NM_Motorista = NM_Motorista;
  }

  public void setDM_Tipo_Ordem_Frete (String DM_Tipo_Ordem_Frete) {
    this.DM_Tipo_Ordem_Frete = DM_Tipo_Ordem_Frete;
  }

  public void setNR_CNPJ_CPF (String NR_CNPJ_CPF) {
    this.NR_CNPJ_CPF = NR_CNPJ_CPF;
  }

  public void setNM_Fax (String NM_Fax) {
    this.NM_Fax = NM_Fax;
  }

  public void setNR_Telefone (String NR_Telefone) {

    this.NR_Telefone = NR_Telefone;
  }

  public void setNM_Inscricao_Estadual (String NM_Inscricao_Estadual) {
    this.NM_Inscricao_Estadual = NM_Inscricao_Estadual;
  }

  public void setCD_Estado (String CD_Estado) {
    this.CD_Estado = CD_Estado;
  }

  public void setNM_Cidade (String NM_Cidade) {
    this.NM_Cidade = NM_Cidade;
  }

  public void setNR_CEP (String NR_CEP) {

    this.NR_CEP = NR_CEP;
  }

  public void setNM_Endereco (String NM_Endereco) {
    this.NM_Endereco = NM_Endereco;
  }

  public void setNM_Fantasia (String NM_Fantasia) {
    this.NM_Fantasia = NM_Fantasia;
  }

  public void setNM_Razao_Social_Empresa (String NM_Razao_Social_Empresa) {
    this.NM_Razao_Social_Empresa = NM_Razao_Social_Empresa;
  }

  public void setNR_CNPJ_CPF_Empresa (String NR_CNPJ_CPF_Empresa) {

    this.NR_CNPJ_CPF_Empresa = NR_CNPJ_CPF_Empresa;
  }

  public void setVL_Vale_Pedagio (double VL_Vale_Pedagio) {
    this.VL_Vale_Pedagio = VL_Vale_Pedagio;
  }

  public void setVL_Total_Frete_CTRC (double VL_Total_Frete_CTRC) {
    this.VL_Total_Frete_CTRC = VL_Total_Frete_CTRC;
  }

  public void setVL_Set_Senat (double VL_Set_Senat) {
    this.VL_Set_Senat = VL_Set_Senat;
  }

  public void setVL_Taxa_Expediente (double VL_Taxa_Expediente) {
    this.VL_Taxa_Expediente = VL_Taxa_Expediente;
  }

  public void setVL_Total (double VL_Total) {
    this.VL_Total = VL_Total;
  }

  public void setVL_Saldo (double VL_Saldo) {
    this.VL_Saldo = VL_Saldo;
  }

  public void setVL_Reembolso (double VL_Reembolso) {
    this.VL_Reembolso = VL_Reembolso;
  }

  public void setVL_Premio (double VL_Premio) {
    this.VL_Premio = VL_Premio;
  }

  public void setVL_Outros (double VL_Outros) {
    this.VL_Outros = VL_Outros;
  }

  public void setVL_Ordem_Frete_Convertida (double VL_Ordem_Frete_Convertida) {
    this.VL_Ordem_Frete_Convertida = VL_Ordem_Frete_Convertida;
  }

  public void setVL_Ordem_Frete (double VL_Ordem_Frete) {
    this.VL_Ordem_Frete = VL_Ordem_Frete;
  }

  public void setVL_Multa_Atrazo (double VL_Multa_Atrazo) {
    this.VL_Multa_Atrazo = VL_Multa_Atrazo;
  }

  public void setVL_Multa (double VL_Multa) {
    this.VL_Multa = VL_Multa;
  }

  public void setVL_Liquido_Ordem_Frete (double VL_Liquido_Ordem_Frete) {
    this.VL_Liquido_Ordem_Frete = VL_Liquido_Ordem_Frete;
  }

  public void setVL_INSS_Empresa (double VL_INSS_Empresa) {
    this.VL_INSS_Empresa = VL_INSS_Empresa;
  }

  public void setVL_INSS_Pago (double VL_INSS_Pago) {
    this.VL_INSS_Pago = VL_INSS_Pago;
  }

  public void setVL_INSS_Prestador (double VL_INSS_Prestador) {
    this.VL_INSS_Prestador = VL_INSS_Prestador;
  }

  public void setVL_IRRF (double VL_IRRF) {
    this.VL_IRRF = VL_IRRF;
  }

  public void setVL_Estadia (double VL_Estadia) {
    this.VL_Estadia = VL_Estadia;
  }

  public void setVL_Descontos (double VL_Descontos) {
    this.VL_Descontos = VL_Descontos;
  }

  public void setVL_Cotacao_Padrao (double VL_Cotacao_Padrao) {
    this.VL_Cotacao_Padrao = VL_Cotacao_Padrao;
  }

  public void setVL_Cotacao (double VL_Cotacao) {
    this.VL_Cotacao = VL_Cotacao;
  }

  public void setVL_Coleta (double VL_Coleta) {
    this.VL_Coleta = VL_Coleta;
  }

  public void setVL_Compromisso (double VL_Compromisso) {
    this.VL_Compromisso = VL_Compromisso;
  }

  public void setVL_Adiantamento2 (double VL_Adiantamento2) {
    this.VL_Adiantamento2 = VL_Adiantamento2;
  }

  public void setVL_Adiantamento1 (double VL_Adiantamento1) {
    this.VL_Adiantamento1 = VL_Adiantamento1;
  }

  public void setPE_ICMS (double PE_ICMS) {
    this.PE_ICMS = PE_ICMS;
  }

  public void setOID_Veiculo (String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }

  public void setOID_Pessoa (String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }

  public void setOID_Ordem_Principal (String OID_Ordem_Principal) {
    this.OID_Ordem_Principal = OID_Ordem_Principal;
  }

  public void setOID_Ordem_Frete (String OID_Ordem_Frete) {
    this.OID_Ordem_Frete = OID_Ordem_Frete;
  }

  public void setOID_Motorista (String OID_Motorista) {
    this.OID_Motorista = OID_Motorista;
  }

  public void setOID_Manifesto (String OID_Manifesto) {
    this.OID_Manifesto = OID_Manifesto;
  }

  public void setOID_Fornecedor (String OID_Fornecedor) {
    this.OID_Fornecedor = OID_Fornecedor;
  }

  public void setOID_Empresa (long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }

  public void setOID_Acerto (long OID_Acerto) {
    this.OID_Acerto = OID_Acerto;
  }

  public void setNR_Total_Peso_CTRC (double NR_Total_Peso_CTRC) {
    this.NR_Total_Peso_CTRC = NR_Total_Peso_CTRC;
  }

  public void setNR_Qtde_Entrega (long NR_Qtde_Entrega) {
    this.NR_Qtde_Entrega = NR_Qtde_Entrega;
  }

  public void setNR_Qtde_Coleta (long NR_Qtde_Coleta) {
    this.NR_Qtde_Coleta = NR_Qtde_Coleta;
  }

  public void setNR_Peso_Master (double NR_Peso_Master) {
    this.NR_Peso_Master = NR_Peso_Master;
  }

  public void setNR_Parcela (Integer NR_Parcela) {
    this.NR_Parcela = NR_Parcela;
  }

  public void setNR_Ordem_Frete (long NR_Ordem_Frete) {
    this.NR_Ordem_Frete = NR_Ordem_Frete;
  }

  public void setNR_Adiantamento (int NR_Adiantamento) {
    this.NR_Adiantamento = NR_Adiantamento;
  }

  public void setNR_Acerto (long NR_Acerto) {
    this.NR_Acerto = NR_Acerto;
  }

  public void setNM_Serie (String NM_Serie) {
    this.NM_Serie = NM_Serie;
  }

  public void setNM_Razao_Social (String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }

  public void setNM_Pagamento (String NM_Pagamento) {
    this.NM_Pagamento = NM_Pagamento;
  }

  public void setNM_Empresa (String NM_Empresa) {
    this.NM_Empresa = NM_Empresa;
  }

  public void setDT_Vencimento (String DT_Vencimento) {
    this.DT_Vencimento = DT_Vencimento;
  }

  public void setDT_Saldo (String DT_Saldo) {
    this.DT_Saldo = DT_Saldo;
  }

  public void setDT_Master_Recuperado (String DT_Master_Recuperado) {
    this.DT_Master_Recuperado = DT_Master_Recuperado;
  }

  public void setDt_Emissao_Inicial (String Dt_Emissao_Inicial) {
    this.Dt_Emissao_Inicial = Dt_Emissao_Inicial;
  }

  public void setDt_Emissao_Final (String Dt_Emissao_Final) {
    this.Dt_Emissao_Final = Dt_Emissao_Final;
  }

  public void setDT_Emissao (String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }

  public void setDT_Adiantamento2 (String DT_Adiantamento2) {
    this.DT_Adiantamento2 = DT_Adiantamento2;
  }

  public void setDT_Adiantamento1 (String DT_Adiantamento1) {
    this.DT_Adiantamento1 = DT_Adiantamento1;
  }

  public void setDM_Tipo_Pedagio (String DM_Tipo_Pedagio) {
    this.DM_Tipo_Pedagio = DM_Tipo_Pedagio;
  }

  public void setDM_Tipo_Pagamento_Saldo (String DM_Tipo_Pagamento_Saldo) {
    this.DM_Tipo_Pagamento_Saldo = DM_Tipo_Pagamento_Saldo;
  }

  public void setDM_Tipo_Pagamento_Adiantamento2 (String
      DM_Tipo_Pagamento_Adiantamento2) {
    this.DM_Tipo_Pagamento_Adiantamento2 = DM_Tipo_Pagamento_Adiantamento2;
  }

  public void setDM_Tipo_Pagamento_Adiantamento1 (String
      DM_Tipo_Pagamento_Adiantamento1) {
    this.DM_Tipo_Pagamento_Adiantamento1 = DM_Tipo_Pagamento_Adiantamento1;
  }

  public void setDM_Acerto (String DM_Acerto) {
    this.DM_Acerto = DM_Acerto;
  }

  public void setDM_Adiantamento (String DM_Adiantamento) {
    this.DM_Adiantamento = DM_Adiantamento;
  }

  public void setDM_Frete (String DM_Frete) {
    this.DM_Frete = DM_Frete;
  }

  public void setDM_Impresso (String DM_Impresso) {
    this.DM_Impresso = DM_Impresso;
  }

  public void setDM_Lista_Conhecimento (String DM_Lista_Conhecimento) {
    this.DM_Lista_Conhecimento = DM_Lista_Conhecimento;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public void setDM_Tipo_Adiantamento (String DM_Tipo_Adiantamento) {
    this.DM_Tipo_Adiantamento = DM_Tipo_Adiantamento;
  }

  public void setDM_Tipo_Adiantamento_Desc (String DM_Tipo_Adiantamento_Desc) {
    this.DM_Tipo_Adiantamento_Desc = DM_Tipo_Adiantamento_Desc;
  }

  private void jbInit () throws Exception {
  }
public String getDT_Cadastro() {
	return DT_Cadastro;
}
public void setDT_Cadastro(String cadastro) {
	DT_Cadastro = cadastro;
}
public String getNM_Email() {
	return NM_Email;
}
public void setNM_Email(String email) {
	NM_Email = email;
}
public String getNM_Site() {
	return NM_Site;
}
public void setNM_Site(String site) {
	NM_Site = site;
}
public String getNM_Bairro() {
	return NM_Bairro;
}
public void setNM_Bairro(String bairro) {
	NM_Bairro = bairro;
}
public String getDM_Modelo_Relatorio() {
	return DM_Modelo_Relatorio;
}
public void setDM_Modelo_Relatorio(String modelo_Relatorio) {
	DM_Modelo_Relatorio = modelo_Relatorio;
}
public String getNM_Codigo_IBGE() {
	return NM_Codigo_IBGE;
}
public void setNM_Codigo_IBGE(String codigo_IBGE) {
	NM_Codigo_IBGE = codigo_IBGE;
}
public String getCD_Conta() {
	return CD_Conta;
}
public void setCD_Conta(String conta) {
	CD_Conta = conta;
}
public String getCD_Historico() {
	return CD_Historico;
}
public void setCD_Historico(String historico) {
	CD_Historico = historico;
}
public long getOid_Origem() {
	return oid_Origem;
}
public void setOid_Origem(long oid_Origem) {
	this.oid_Origem = oid_Origem;
}
public String getCD_Conta_Estrutural() {
	return CD_Conta_Estrutural;
}
public void setCD_Conta_Estrutural(String conta_Estrutural) {
	CD_Conta_Estrutural = conta_Estrutural;
}
public String getDm_Lista_Encerramento() {
	return Dm_Lista_Encerramento;
}
public void setDm_Lista_Encerramento(String dm_Lista_Encerramento) {
	Dm_Lista_Encerramento = dm_Lista_Encerramento;
}

}
