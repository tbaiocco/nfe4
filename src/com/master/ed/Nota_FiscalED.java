package com.master.ed;

public class Nota_FiscalED extends MasterED{

  private String OID_Nota_Fiscal;
  private long NR_Nota_Fiscal;
  private String NM_Serie;
  private double VL_Nota_Fiscal;
  private double VL_Seguro;
  private String DM_Situacao;
  private String NR_Pedido;
  private String DT_Emissao;
  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String OID_Pessoa_Entregadora;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private double NR_Peso;
  private double NR_Volumes;
  private String DT_Entrada;
  private String HR_Entrada;
  private String DM_Transferencia;
  private String OID_Pessoa_Consignatario;
  private long OID_Natureza_Operacao;
  private String NM_Natureza_Operacao;
  private String CD_Natureza_Operacao;
  private String CD_Chassis_Serie;
  private String DM_Exportacao;
  private double VL_Tabela;
  private String DM_Tabela;
  private double VL_Total_Frete;
  // Atributos para o WMS
  private long oid_Modelo_Nota_Fiscal;

  private String CD_Referencia;
  private String NM_Motorista;
  private String NR_Placa;
  private String NR_Celular;
  private String DT_Previsao_Chegada;
  private String HR_Previsao_Chegada;
  private String NM_Produto;
  private String DT_Ocorrencia_Nota_Fiscal;
  private String HR_Ocorrencia_Nota_Fiscal;
  private String NM_Ocorrencia;
  private String DM_Situacao_Embarque;
  private long oid_Referencia;
  private long NR_Volumes_Itens;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String DT_Entrega_Inicial;
  private String DT_Entrega_Final;
  private String DT_Saida_Inicial;
  private String DT_Saida_Final;
  private long NR_Despacho;

  ///### 21072003
  private long OID_Produto;
  private long OID_Modal;
  private String TX_Observacao;
  private String DM_Tipo_Pagamento;
  private String CD_Acesso;
  private long OID_Coleta;
  private String NR_Conhecimento;
  private String OID_Conhecimento;
  private String OID_Pessoa_Redespacho;
  private double NR_Peso_Cubado;
  private String NM_Calculo_Frete;
  private String DM_Tipo_Conhecimento;
  private double NR_Itens;
  private long OID_Lote;
  private int QT_Conhecimentos_OK;
  private int QT_Conhecimentos_Impressos;
  private int QT_Conhecimentos_Gerados;
  private String NR_Lote;
  private String NM_Erro_Calculo;
  private double NR_Cubagem;
  private double NR_Cubagem_Total;
  private String NM_Especie;
  private String DM_Tipo_Consulta;
  private long oid_Deposito;
  private String NM_Deposito;
  private String DM_Relatorio;
  private long OID_Unidade;
  private String OID_Veiculo;
  private String OID_Motorista;
  private double VL_Custo_Descarga;
  private String DT_Descarga_Deposito;
  private String DT_Carga_Deposito;
  private String DT_Entrega;
  private String NR_AWB;
  private String NR_Minuta;
  private String NR_NF;
  private String DT_Previsao_Entrega;
  private String DM_Regiao;
  private String NR_Dias_Entrega;
  private String NR_Dias_Previsto_Entrega;
  private String NR_Dias_Realizado_Entrega;
  private String HR_Entrega;
  private String NM_Pessoa_Entrega;
  private String HR_Previsao_Entrega;
  private String DM_Origem;
  private String DM_Destino;
  private long OID_Origem;
  private long OID_Destino;
  private String DM_Ordem;
  private String NM_Origem;
  private String NM_Destino;
  private long OID_Ordem_Carga;
  private long OID_Ordem_Descarga;
  private String DM_Carga_Descarga;
  private long NR_Postagem;
  private double NR0;
  private double NR1;
  private double NR2;
  private double NR3;
  private double NR4;
  private double NR5;
  private double NR6;
  private double NR_Itens2;
  private String DM_Tipo_Postagem;
  private long NR_Conhecimento_Inicial;
  private long NR_Conhecimento_Final;
  private String DM_Tipo_Documento;
  private String NM_Centro_Custo;
  private String UF_Origem;
  private String UF_Destino;
  private String CD_Rota_Entrega;
  private String NR_CX1;
  private String NR_CX2;
  private String NR_CX3;
  private String NR_CX4;
  private String NR_CX5;
  private String NR_CX6;
  private String NR_CX7;
  private String NR_CX8;
  private String NR_CX9;
  private String NR_CX10;
  private String NR_CX11;
  private String NR_CX12;

private String nm_arquivo_imp;
private String nm_chave_nfe;

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
  private String NM_Agencia;
  private String NM_Conta_Corrente;
  private String NM_Filial_Conhecimento;
  private String NR_Serie_Conhecimento;
  private String NR_Serie_Nota_Fiscal;
  private String DT_Emissao_Nota_Fiscal;
  private String DM_Serie_Duplicata;
  private double VL_Desconto;
  private double VL_Documento;
  private double VL_ICMS;
  private double VL_Juro_Mora_Dia;
  private double VL_Duplicata;
  private double NR_Peso_Nota_Fiscal;
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
  private String DM_Substituicao_Tributaria;
  private String oid_Pessoa_Unidade;
  private String CD_Ocorrencia;
  private long oid_Padrao;
  private String DT_Ocorrencia;
  private String HR_Ocorrencia;
  private String oid_Pessoa_Destinatario;
  private String oid_Pessoa_Consignatario;
  private String oid_Pessoa_Entregadora;
  private String oid_Pessoa_Redespacho;
  private String NM_Natureza;
  private String HR_Emissao;
  private String NR_Fatura;
  private String DT_Coleta;
  private String HR_Coleta;
  private String DT_Embarque;
  private String HR_Embarque;
  private String NR_Manifesto;
  private double VL_Outros1;
  private String NM_Unidade_Entregadora;
  private double NR_Volume_Nota_Fiscal;
  private double NR_Volumes_Conhecimento;
  private String oid_Pessoa_Pagador;
  private String DM_Tipo_Ocorrencia;
  private long oid_Seguradora;
  private String CD_Unidade_Entregadora;
  private String DT_Ocorrencia_Lancada;
  private String HR_Ocorrencia_Lancada;
  private long oid_Unidade;
  private String NM_Cidade_Origem;
  private String NM_Cidade_Destino;
  private String OID_Carreta;
  private String NM_Razao_Social;
  private String DM_Isencao_Seguro;
  private double NR_Peso_Cubado_Conhecimento;
  private double VL_ICMS_Conhecimento;
  private String DM_Tipo_Transporte;
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
  private String CD_Modal;
  private double VL_Entrega;
  private double VL_Coleta;
  private String NM_Cia_Aerea;
  private String NM_Inscricao_Estadual_Destinatario;
  private String NR_CNPJ_CPF_Remetente;
  private String NR_CNPJ_CPF_Destinatario;
  private String NR_CNPJ_CPF_Pagador;
  private String acao;
  private String NM_Mes_Inicial;
  private String NM_Ano_Inicial;
  private String DM_Tracking;
  private int NR_Dias_Entrega_Realizado;
  private long NR_Dias_Entrega_Previsto;
  private String NR_Codigo_Cliente;

  public void setOID_Nota_Fiscal(String OID_Nota_Fiscal) {
    this.OID_Nota_Fiscal = OID_Nota_Fiscal;
  }
  public String getOID_Nota_Fiscal() {
    return OID_Nota_Fiscal;
  }
  public void setNR_Nota_Fiscal(long NR_Nota_Fiscal) {
    this.NR_Nota_Fiscal = NR_Nota_Fiscal;
  }
  public long getNR_Nota_Fiscal() {
    return NR_Nota_Fiscal;
  }
  public void setNM_Serie(String NM_Serie) {
    this.NM_Serie = NM_Serie;
  }
  public String getNM_Serie() {
    return NM_Serie;
  }
  public void setVL_Nota_Fiscal(double VL_Nota_Fiscal) {
    this.VL_Nota_Fiscal = VL_Nota_Fiscal;
  }
  public double getVL_Nota_Fiscal() {
    return VL_Nota_Fiscal;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public void setNR_Pedido(String NR_Pedido) {
    this.NR_Pedido = NR_Pedido;
  }
  public String getNR_Pedido() {
    return NR_Pedido;
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
  public void setNR_Peso(double NR_Peso) {
    this.NR_Peso = NR_Peso;
  }
  public double getNR_Peso() {
    return NR_Peso;
  }
  public void setNR_Volumes(double NR_Volumes) {
    this.NR_Volumes = NR_Volumes;
  }
  public double getNR_Volumes() {
    return NR_Volumes;
  }
  public void setDT_Entrada(String DT_Entrada) {
    this.DT_Entrada = DT_Entrada;
  }
  public String getDT_Entrada() {
    return DT_Entrada;
  }
  public void setHR_Entrada(String HR_Entrada) {
    this.HR_Entrada = HR_Entrada;
  }
  public String getHR_Entrada() {
    return HR_Entrada;
  }
  public void setDM_Transferencia(String DM_Transferencia) {
    this.DM_Transferencia = DM_Transferencia;
  }
  public String getDM_Transferencia() {
    return DM_Transferencia;
  }
  public void setOID_Pessoa_Consignatario(String OID_Pessoa_Consignatario) {
    this.OID_Pessoa_Consignatario = OID_Pessoa_Consignatario;
  }
  public String getOID_Pessoa_Consignatario() {
    return OID_Pessoa_Consignatario;
  }
  public void setOID_Natureza_Operacao(long OID_Natureza_Operacao) {
    this.OID_Natureza_Operacao = OID_Natureza_Operacao;
  }
  public long getOID_Natureza_Operacao() {
    return OID_Natureza_Operacao;
  }
  public void setNM_Natureza_Operacao(String NM_Natureza_Operacao) {
    this.NM_Natureza_Operacao = NM_Natureza_Operacao;
  }
  public String getNM_Natureza_Operacao() {
    return NM_Natureza_Operacao;
  }
  public void setCD_Natureza_Operacao(String CD_Natureza_Operacao) {
    this.CD_Natureza_Operacao = CD_Natureza_Operacao;
  }
  public String getCD_Natureza_Operacao() {
    return CD_Natureza_Operacao;
  }
  public void setCD_Chassis_Serie(String CD_Chassis_Serie) {
    this.CD_Chassis_Serie = CD_Chassis_Serie;
  }
  public String getCD_Chassis_Serie() {
    return CD_Chassis_Serie;
  }
  public void setDM_Exportacao(String DM_Exportacao) {
    this.DM_Exportacao = DM_Exportacao;
  }
  public String getDM_Exportacao() {
    return DM_Exportacao;
  }
  public String getCD_Referencia() {
    return CD_Referencia;
  }
  public void setCD_Referencia(String CD_Referencia) {
    this.CD_Referencia = CD_Referencia;
  }
  public void setDM_Situacao_Embarque(String DM_Situacao_Embarque) {
    this.DM_Situacao_Embarque = DM_Situacao_Embarque;
  }
  public String getDM_Situacao_Embarque() {
    return DM_Situacao_Embarque;
  }
  public String getDT_Emissao_Final() {
    return DT_Emissao_Final;
  }
  public String getDT_Emissao_Inicial() {
    return DT_Emissao_Inicial;
  }
  public void setDT_Emissao_Final(String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }
  public void setDT_Emissao_Inicial(String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }
  public void setDT_Ocorrencia_Nota_Fiscal(String DT_Ocorrencia_Nota_Fiscal) {
    this.DT_Ocorrencia_Nota_Fiscal = DT_Ocorrencia_Nota_Fiscal;
  }
  public void setDT_Previsao_Chegada(String DT_Previsao_Chegada) {
    this.DT_Previsao_Chegada = DT_Previsao_Chegada;
  }
  public String getDT_Ocorrencia_Nota_Fiscal() {
    return DT_Ocorrencia_Nota_Fiscal;
  }
  public String getDT_Previsao_Chegada() {
    return DT_Previsao_Chegada;
  }
  public String getHR_Ocorrencia_Nota_Fiscal() {
    return HR_Ocorrencia_Nota_Fiscal;
  }
  public String getHR_Previsao_Chegada() {
    return HR_Previsao_Chegada;
  }
  public String getNM_Motorista() {
    return NM_Motorista;
  }
  public void setHR_Ocorrencia_Nota_Fiscal(String HR_Ocorrencia_Nota_Fiscal) {
    this.HR_Ocorrencia_Nota_Fiscal = HR_Ocorrencia_Nota_Fiscal;
  }
  public void setHR_Previsao_Chegada(String HR_Previsao_Chegada) {
    this.HR_Previsao_Chegada = HR_Previsao_Chegada;
  }
  public void setNM_Motorista(String NM_Motorista) {
    this.NM_Motorista = NM_Motorista;
  }
  public void setNM_Ocorrencia(String NM_Ocorrencia) {
    this.NM_Ocorrencia = NM_Ocorrencia;
  }
  public String getNM_Ocorrencia() {
    return NM_Ocorrencia;
  }
  public String getNM_Produto() {
    return NM_Produto;
  }
  public String getNR_Celular() {
    return NR_Celular;
  }
  public void setNM_Produto(String NM_Produto) {
    this.NM_Produto = NM_Produto;
  }
  public void setNR_Celular(String NR_Celular) {
    this.NR_Celular = NR_Celular;
  }
  public String getNR_Placa() {
    return NR_Placa;
  }
  public long getNR_Volumes_Itens() {
    return NR_Volumes_Itens;
  }
  public void setNR_Placa(String NR_Placa) {
    this.NR_Placa = NR_Placa;
  }
  public void setNR_Volumes_Itens(long NR_Volumes_Itens) {
    this.NR_Volumes_Itens = NR_Volumes_Itens;
  }
  public void setOid_Referencia(long oid_Referencia) {
    this.oid_Referencia = oid_Referencia;
  }

  public void setOid_Deposito (long oid_Deposito) {
    this.oid_Deposito = oid_Deposito;
  }

  public void setOID_Ordem_Descarga (long OID_Ordem_Descarga) {
    this.OID_Ordem_Descarga = OID_Ordem_Descarga;
  }

  public void setDM_Carga_Descarga (String DM_Carga_Descarga) {
    this.DM_Carga_Descarga = DM_Carga_Descarga;
  }

  public void setOID_Ordem_Carga (long OID_Ordem_Carga) {
    this.OID_Ordem_Carga = OID_Ordem_Carga;
  }

  public void setNM_Destino (String NM_Destino) {
    this.NM_Destino = NM_Destino;
  }

  public void setNM_Origem (String NM_Origem) {
    this.NM_Origem = NM_Origem;
  }

  public void setDM_Ordem (String DM_Ordem) {
    this.DM_Ordem = DM_Ordem;
  }

  public void setOID_Destino (long OID_Destino) {
    this.OID_Destino = OID_Destino;
  }

  public void setOID_Origem (long OID_Origem) {
    this.OID_Origem = OID_Origem;
  }

  public void setDM_Destino (String DM_Destino) {
    this.DM_Destino = DM_Destino;
  }

  public void setDM_Origem (String DM_Origem) {
    this.DM_Origem = DM_Origem;
  }

  public void setHR_Previsao_Entrega (String HR_Previsao_Entrega) {
    this.HR_Previsao_Entrega = HR_Previsao_Entrega;
  }

  public void setNM_Pessoa_Entrega (String NM_Pessoa_Entrega) {
    this.NM_Pessoa_Entrega = NM_Pessoa_Entrega;
  }

  public void setHR_Entrega (String HR_Entrega) {
    this.HR_Entrega = HR_Entrega;
  }

  public void setNR_Dias_Realizado_Entrega (String NR_Dias_Realizado_Entrega) {
    this.NR_Dias_Realizado_Entrega = NR_Dias_Realizado_Entrega;
  }

  public void setNR_Dias_Previsto_Entrega (String NR_Dias_Previsto_Entrega) {
    this.NR_Dias_Previsto_Entrega = NR_Dias_Previsto_Entrega;
  }

  public void setNR_Dias_Entrega (String NR_Dias_Entrega) {
    this.NR_Dias_Entrega = NR_Dias_Entrega;
  }

  public void setDM_Regiao (String DM_Regiao) {
    this.DM_Regiao = DM_Regiao;
  }

  public void setDT_Previsao_Entrega (String DT_Previsao_Entrega) {
    this.DT_Previsao_Entrega = DT_Previsao_Entrega;
  }

  public void setNR_NF (String NR_NF) {
    this.NR_NF = NR_NF;
  }

  public void setNR_Minuta (String NR_Minuta) {
    this.NR_Minuta = NR_Minuta;
  }

  public void setNR_AWB (String NR_AWB) {
    this.NR_AWB = NR_AWB;
  }

  public void setDT_Entrega (String DT_Entrega) {
    this.DT_Entrega = DT_Entrega;
  }

  public void setDT_Entrega_Inicial (String DT_Entrega_Inicial) {
    this.DT_Entrega_Inicial = DT_Entrega_Inicial;
  }

  public void setDT_Entrega_Final (String DT_Entrega_Final) {
    this.DT_Entrega_Final = DT_Entrega_Final;
  }

  public void setDT_Carga_Deposito (String DT_Carga_Deposito) {
    this.DT_Carga_Deposito = DT_Carga_Deposito;
  }

  public void setDT_Descarga_Deposito (String DT_Descarga_Deposito) {
    this.DT_Descarga_Deposito = DT_Descarga_Deposito;
  }

  public void setVL_Custo_Descarga (double VL_Custo_Descarga) {
    this.VL_Custo_Descarga = VL_Custo_Descarga;
  }

  public void setOID_Motorista (String OID_Motorista) {
    this.OID_Motorista = OID_Motorista;
  }

  public void setOID_Veiculo (String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }

  public void setOID_Unidade (long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public void setNM_Deposito (String NM_Deposito) {
    this.NM_Deposito = NM_Deposito;
  }

  public void setDM_Tipo_Consulta (String DM_Tipo_Consulta) {
    this.DM_Tipo_Consulta = DM_Tipo_Consulta;
  }

  public long getOid_Referencia() {
    return oid_Referencia;
  }

  public long getOid_Deposito () {
    return oid_Deposito;
  }

  public long getOID_Ordem_Descarga () {
    return OID_Ordem_Descarga;
  }

  public String getDM_Carga_Descarga () {
    return DM_Carga_Descarga;
  }

  public long getOID_Ordem_Carga () {
    return OID_Ordem_Carga;
  }

  public String getNM_Destino () {
    return NM_Destino;
  }

  public String getNM_Origem () {
    return NM_Origem;
  }

  public String getDM_Ordem () {
    return DM_Ordem;
  }

  public long getOID_Destino () {
    return OID_Destino;
  }

  public long getOID_Origem () {
    return OID_Origem;
  }

  public String getDM_Destino () {
    return DM_Destino;
  }

  public String getDM_Origem () {
    return DM_Origem;
  }

  public String getHR_Previsao_Entrega () {
    return HR_Previsao_Entrega;
  }

  public String getNM_Pessoa_Entrega () {
    return NM_Pessoa_Entrega;
  }

  public String getHR_Entrega () {
    return HR_Entrega;
  }

  public String getNR_Dias_Realizado_Entrega () {
    return NR_Dias_Realizado_Entrega;
  }

  public String getNR_Dias_Previsto_Entrega () {
    return NR_Dias_Previsto_Entrega;
  }

  public String getNR_Dias_Entrega () {
    return NR_Dias_Entrega;
  }

  public String getDM_Regiao () {
    return DM_Regiao;
  }

  public String getDT_Previsao_Entrega () {
    return DT_Previsao_Entrega;
  }

  public String getNR_NF () {
    return NR_NF;
  }

  public String getNR_Minuta () {
    return NR_Minuta;
  }

  public String getNR_AWB () {
    return NR_AWB;
  }

  public String getDT_Entrega () {
    return DT_Entrega;
  }

  public String getDT_Entrega_Inicial () {
    return DT_Entrega_Inicial;
  }

  public String getDT_Entrega_Final () {
    return DT_Entrega_Final;
  }

  public String getDT_Carga_Deposito () {
    return DT_Carga_Deposito;
  }

  public String getDT_Descarga_Deposito () {
    return DT_Descarga_Deposito;
  }

  public double getVL_Custo_Descarga () {
    return VL_Custo_Descarga;
  }

  public String getOID_Motorista () {
    return OID_Motorista;
  }

  public String getOID_Veiculo () {
    return OID_Veiculo;
  }

  public long getOID_Unidade () {
    return OID_Unidade;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public String getNM_Deposito () {
    return NM_Deposito;
  }

  public String getDM_Tipo_Consulta () {
    return DM_Tipo_Consulta;
  }

  public void setNR_Despacho(long NR_Despacho) {
    this.NR_Despacho = NR_Despacho;
  }
  public long getNR_Despacho() {
    return NR_Despacho;
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
  public void setTX_Observacao(String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }
  public String getTX_Observacao() {
    return TX_Observacao;
  }
  public void setDM_Tipo_Pagamento(String DM_Tipo_Pagamento) {
    this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
  }
  public String getDM_Tipo_Pagamento() {
    return DM_Tipo_Pagamento;
  }
  public void setCD_Acesso(String CD_Acesso) {
    this.CD_Acesso = CD_Acesso;
  }
  public String getCD_Acesso() {
    return CD_Acesso;
  }
  public void setOID_Coleta(long OID_Coleta) {
    this.OID_Coleta = OID_Coleta;
  }
  public long getOID_Coleta() {
    return OID_Coleta;
  }
  public void setNR_Conhecimento(String NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }
  public String getNR_Conhecimento() {
    return NR_Conhecimento;
  }
  public void setOID_Conhecimento(String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }
  public String getOID_Conhecimento() {
    return OID_Conhecimento;
  }
  public void setOID_Pessoa_Redespacho(String OID_Pessoa_Redespacho) {
    this.OID_Pessoa_Redespacho = OID_Pessoa_Redespacho;
  }
  public String getOID_Pessoa_Redespacho() {
    return OID_Pessoa_Redespacho;
  }
  public void setNR_Peso_Cubado(double NR_Peso_Cubado) {
    this.NR_Peso_Cubado = NR_Peso_Cubado;
  }
  public double getNR_Peso_Cubado() {
    return NR_Peso_Cubado;
  }
  public void setVL_Total_Frete(double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }
  public double getVL_Total_Frete() {
    return VL_Total_Frete;
  }
  public void setNM_Calculo_Frete(String NM_Calculo_Frete) {
    this.NM_Calculo_Frete = NM_Calculo_Frete;
  }
  public String getNM_Calculo_Frete() {
    return NM_Calculo_Frete;
  }
  public void setDM_Tipo_Conhecimento(String DM_Tipo_Conhecimento) {
    this.DM_Tipo_Conhecimento = DM_Tipo_Conhecimento;
  }
  public String getDM_Tipo_Conhecimento() {
    return DM_Tipo_Conhecimento;
  }
  public void setNR_Itens(double NR_Itens) {
    this.NR_Itens = NR_Itens;
  }
  public double getNR_Itens() {
    return NR_Itens;
  }
  public void setOID_Lote(long OID_Lote) {
    this.OID_Lote = OID_Lote;
  }
  public long getOID_Lote() {
    return OID_Lote;
  }
  public void setQT_Conhecimentos_Gerados(int QT_Conhecimentos_Gerados) {
    this.QT_Conhecimentos_Gerados = QT_Conhecimentos_Gerados;
  }
  public int getQT_Conhecimentos_Gerados() {
    return QT_Conhecimentos_Gerados;
  }
  public void setQT_Conhecimentos_OK(int QT_Conhecimentos_OK) {
    this.QT_Conhecimentos_OK = QT_Conhecimentos_OK;
  }
  public int getQT_Conhecimentos_OK() {
    return QT_Conhecimentos_OK;
  }
  public void setQT_Conhecimentos_Impressos(int QT_Conhecimentos_Impressos) {
    this.QT_Conhecimentos_Impressos = QT_Conhecimentos_Impressos;
  }
  public int getQT_Conhecimentos_Impressos() {
    return QT_Conhecimentos_Impressos;
  }
  public void setNR_Lote(String NR_Lote) {
    this.NR_Lote = NR_Lote;
  }
  public String getNR_Lote() {
    return NR_Lote;
  }
  public void setNM_Erro_Calculo(String NM_Erro_Calculo) {
    this.NM_Erro_Calculo = NM_Erro_Calculo;
  }
  public String getNM_Erro_Calculo() {
    return NM_Erro_Calculo;
  }
  public void setNR_Cubagem(double NR_Cubagem) {
    this.NR_Cubagem = NR_Cubagem;
  }
  public double getNR_Cubagem() {
    return NR_Cubagem;
  }
  public void setNR_Cubagem_Total(double NR_Cubagem_Total) {
    this.NR_Cubagem_Total = NR_Cubagem_Total;
  }
  public double getNR_Cubagem_Total() {
    return NR_Cubagem_Total;
  }
  public String getDM_Tabela() {
    return DM_Tabela;
  }
  public void setDM_Tabela(String DM_Tabela) {
    this.DM_Tabela = DM_Tabela;
  }
  public double getVL_Tabela() {
    return VL_Tabela;
  }
  public void setVL_Tabela(double VL_Tabela) {
    this.VL_Tabela = VL_Tabela;
  }
  public String getNM_Especie() {
      return this.NM_Especie;
  }
  public void setNM_Especie(String especie) {
      this.NM_Especie = especie;
  }
  public String getDT_Saida_Final() {
  	return DT_Saida_Final;
  }
  public void setDT_Saida_Final(String saida_Final) {
  	DT_Saida_Final = saida_Final;
  }
  public String getDT_Saida_Inicial() {
  	return DT_Saida_Inicial;
  }
  public void setDT_Saida_Inicial(String saida_Inicial) {
  	DT_Saida_Inicial = saida_Inicial;
  }
	public long getOid_Modelo_Nota_Fiscal() {
		return oid_Modelo_Nota_Fiscal;
	}

  public long getOid_Empresa () {
    return oid_Empresa;
  }

  public long getOid_Padrao () {
    return oid_Padrao;
  }

  public String getOid_Pessoa () {
    return oid_Pessoa;
  }

  public String getOid_Pessoa_Consignatario () {
    return oid_Pessoa_Consignatario;
  }

  public String getOid_Pessoa_Destinatario () {
    return oid_Pessoa_Destinatario;
  }

  public String getOid_Pessoa_Entregadora () {
    return oid_Pessoa_Entregadora;
  }

  public String getOid_Pessoa_Pagador () {
    return oid_Pessoa_Pagador;
  }

  public String getOid_Pessoa_Redespacho () {
    return oid_Pessoa_Redespacho;
  }

  public String getOid_Pessoa_Unidade () {
    return oid_Pessoa_Unidade;
  }

  public long getOid_Seguradora () {
    return oid_Seguradora;
  }

  public long getOid_Unidade () {
    return oid_Unidade;
  }

  public String getAcao () {
    return acao;
  }

  public String getNR_Codigo_Cliente () {
    return NR_Codigo_Cliente;
  }

  public long getNR_Dias_Entrega_Previsto () {
    return NR_Dias_Entrega_Previsto;
  }

  public int getNR_Dias_Entrega_Realizado () {
    return NR_Dias_Entrega_Realizado;
  }

  public String getDM_Tracking () {
    return DM_Tracking;
  }

  public double getNR_Itens2 () {
    return NR_Itens2;
  }

  public String getNM_Ano_Inicial () {
    return NM_Ano_Inicial;
  }

  public String getNM_Mes_Inicial () {
    return NM_Mes_Inicial;
  }

  public double getVL_TX_Coleta_Entrega () {
    return VL_TX_Coleta_Entrega;
  }

  public double getVL_Sec_Cat () {
    return VL_Sec_Cat;
  }

  public double getVL_SEC_CAT () {
    return VL_SEC_CAT;
  }

  public double getVL_Pedagio () {
    return VL_Pedagio;
  }

  public double getVL_Outros1 () {
    return VL_Outros1;
  }

  public double getVL_Juro_Mora_Dia () {
    return VL_Juro_Mora_Dia;
  }

  public double getVL_ITR () {
    return VL_ITR;
  }

  public double getVL_ICMS_Conhecimento () {
    return VL_ICMS_Conhecimento;
  }

  public double getVL_ICMS () {
    return VL_ICMS;
  }

  public double getVL_Frete_Valor () {
    return VL_Frete_Valor;
  }

  public double getVL_Frete_Peso () {
    return VL_Frete_Peso;
  }

  public double getVL_Frete () {
    return VL_Frete;
  }

  public double getVL_Entrega () {
    return VL_Entrega;
  }

  public double getVL_Duplicata () {
    return VL_Duplicata;
  }

  public double getVL_Documento () {
    return VL_Documento;
  }

  public double getVL_Despacho () {
    return VL_Despacho;
  }

  public double getVL_Desconto () {
    return VL_Desconto;
  }

  public double getVL_Coleta () {
    return VL_Coleta;
  }

  public double getVL_Base_Calculo_ICMS () {
    return VL_Base_Calculo_ICMS;
  }

  public double getVL_Ademe () {
    return VL_Ademe;
  }

  public double getPE_Aliquota_ICMS () {
    return PE_Aliquota_ICMS;
  }

  public int getOID_Cidade_Origem () {
    return OID_Cidade_Origem;
  }

  public int getOID_Cidade_Destino () {
    return OID_Cidade_Destino;
  }

  public String getOID_Carreta () {
    return OID_Carreta;
  }

  public double getNR_Volumes_Conhecimento () {
    return NR_Volumes_Conhecimento;
  }

  public double getNR_Volume_Nota_Fiscal () {
    return NR_Volume_Nota_Fiscal;
  }

  public String getNR_Telefone () {
    return NR_Telefone;
  }

  public String getNR_Serie_Nota_Fiscal () {
    return NR_Serie_Nota_Fiscal;
  }

  public String getNR_Serie_Duplicata () {
    return NR_Serie_Duplicata;
  }

  public String getNR_Serie_Conhecimento () {
    return NR_Serie_Conhecimento;
  }

  public double getNR_Peso_Nota_Fiscal () {
    return NR_Peso_Nota_Fiscal;
  }

  public double getNR_Peso_Cubado_Conhecimento () {
    return NR_Peso_Cubado_Conhecimento;
  }

  public double getNR_Peso_Conhecimento () {
    return NR_Peso_Conhecimento;
  }

  public String getNR_Manifesto () {
    return NR_Manifesto;
  }

  public String getNR_Fatura () {
    return NR_Fatura;
  }

  public String getNR_Duplicata () {
    return NR_Duplicata;
  }

  public String getNR_CNPJ_CPF_Remetente () {
    return NR_CNPJ_CPF_Remetente;
  }

  public String getNR_CNPJ_CPF_Pagador () {
    return NR_CNPJ_CPF_Pagador;
  }

  public String getNR_CNPJ_CPF_Destinatario () {
    return NR_CNPJ_CPF_Destinatario;
  }

  public String getNR_CEP () {
    return NR_CEP;
  }

  public String getNM_Unidade_Entregadora () {
    return NM_Unidade_Entregadora;
  }

  public String getNM_Tipo_Veiculo () {
    return NM_Tipo_Veiculo;
  }

  public String getNM_Tipo_Documento () {
    return NM_Tipo_Documento;
  }

  public String getNM_Tipo_Cobranca () {
    return NM_Tipo_Cobranca;
  }

  public String getNM_Remetente () {
    return NM_Remetente;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public String getNM_Natureza () {
    return NM_Natureza;
  }

  public String getNM_Inscricao_Estadual_Remetente () {
    return NM_Inscricao_Estadual_Remetente;
  }

  public String getNM_Inscricao_Estadual_Destinatario () {
    return NM_Inscricao_Estadual_Destinatario;
  }

  public String getNM_Identificador_Registro () {
    return NM_Identificador_Registro;
  }

  public String getNM_Filial_Duplicata () {
    return NM_Filial_Duplicata;
  }

  public String getNM_Filial_Conhecimento () {
    return NM_Filial_Conhecimento;
  }

  public String getNM_Estado_Origem () {
    return NM_Estado_Origem;
  }

  public String getNM_Estado_Destino () {
    return NM_Estado_Destino;
  }

  public String getNM_Estado () {
    return NM_Estado;
  }

  public String getNM_Endereco () {
    return NM_Endereco;
  }

  public String getNM_Destinatario () {
    return NM_Destinatario;
  }

  public String getNM_Conta_Corrente () {
    return NM_Conta_Corrente;
  }

  public String getNM_Cidade_Origem () {
    return NM_Cidade_Origem;
  }

  public String getNM_Cidade_Destino () {
    return NM_Cidade_Destino;
  }

  public String getNM_Cidade () {
    return NM_Cidade;
  }

  public String getNM_Cia_Aerea () {
    return NM_Cia_Aerea;
  }

  public String getNM_Bairro () {
    return NM_Bairro;
  }

  public String getNM_Agente_Cobranca () {
    return NM_Agente_Cobranca;
  }

  public String getNM_Agencia () {
    return NM_Agencia;
  }

  public String getHR_Ocorrencia_Lancada () {
    return HR_Ocorrencia_Lancada;
  }

  public String getHR_Ocorrencia () {
    return HR_Ocorrencia;
  }

  public String getHR_Emissao () {
    return HR_Emissao;
  }

  public String getHR_Embarque () {
    return HR_Embarque;
  }

  public String getHR_Coleta () {
    return HR_Coleta;
  }

  public String getDT_Vencimento_Duplicata () {
    return DT_Vencimento_Duplicata;
  }

  public String getDT_Ocorrencia_Lancada () {
    return DT_Ocorrencia_Lancada;
  }

  public String getDT_Ocorrencia () {
    return DT_Ocorrencia;
  }

  public String getDT_Inicial () {
    return DT_Inicial;
  }

  public String getDT_Final () {
    return DT_Final;
  }

  public String getDT_Emissao_Nota_Fiscal () {
    return DT_Emissao_Nota_Fiscal;
  }

  public String getDT_Emissao_Duplicata () {
    return DT_Emissao_Duplicata;
  }

  public String getDT_Emissao_Conhecimento () {
    return DT_Emissao_Conhecimento;
  }

  public String getDT_Embarque () {
    return DT_Embarque;
  }

  public String getDT_Coleta () {
    return DT_Coleta;
  }

  public String getDM_Tipo_Transporte () {
    return DM_Tipo_Transporte;
  }

  public String getDM_Tipo_Ocorrencia () {
    return DM_Tipo_Ocorrencia;
  }

  public String getDM_Substituicao_Tributaria () {
    return DM_Substituicao_Tributaria;
  }

  public String getDM_Situacao_Cliente () {
    return DM_Situacao_Cliente;
  }

  public String getDM_Serie_Duplicata () {
    return DM_Serie_Duplicata;
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

  public String getDM_Isencao_Seguro () {
    return DM_Isencao_Seguro;
  }

  public String getCD_Unidade_Entregadora () {
    return CD_Unidade_Entregadora;
  }

  public String getCD_Unidade () {
    return CD_Unidade;
  }

  public String getCD_Ocorrencia () {
    return CD_Ocorrencia;
  }

  public String getCD_Modal () {
    return CD_Modal;
  }

  public String getCD_CFO_Conhecimento () {
    return CD_CFO_Conhecimento;
  }

  public String getNR_CX12 () {
    return NR_CX12;
  }

  public String getNR_CX11 () {
    return NR_CX11;
  }

  public String getNR_CX10 () {
    return NR_CX10;
  }

  public String getNR_CX9 () {
    return NR_CX9;
  }

  public String getNR_CX8 () {
    return NR_CX8;
  }

  public String getNR_CX7 () {
    return NR_CX7;
  }

  public String getNR_CX6 () {
    return NR_CX6;
  }

  public String getNR_CX5 () {
    return NR_CX5;
  }

  public String getNR_CX4 () {
    return NR_CX4;
  }

  public String getNR_CX3 () {
    return NR_CX3;
  }

  public String getNR_CX2 () {
    return NR_CX2;
  }

  public String getNR_CX1 () {
    return NR_CX1;
  }

  public String getUF_Destino () {
    return UF_Destino;
  }

  public String getUF_Origem () {
    return UF_Origem;
  }

  public String getNM_Centro_Custo () {
    return NM_Centro_Custo;
  }

  public String getDM_Tipo_Documento () {
    return DM_Tipo_Documento;
  }

  public long getNR_Conhecimento_Final () {
    return NR_Conhecimento_Final;
  }

  public long getNR_Conhecimento_Inicial () {
    return NR_Conhecimento_Inicial;
  }

  public String getDM_Tipo_Postagem () {
    return DM_Tipo_Postagem;
  }

  public double getNR6 () {
    return NR6;
  }

  public double getNR5 () {
    return NR5;
  }

  public double getNR4 () {
    return NR4;
  }

  public double getNR3 () {
    return NR3;
  }

  public double getNR2 () {
    return NR2;
  }

  public double getNR1 () {
    return NR1;
  }

  public double getNR0 () {
    return NR0;
  }

  public long getNR_Postagem () {
    return NR_Postagem;
  }

  public void setOid_Modelo_Nota_Fiscal(long oid_Modelo_Nota_Fiscal) {
		this.oid_Modelo_Nota_Fiscal = oid_Modelo_Nota_Fiscal;
	}

  public void setOid_Empresa (long oid_Empresa) {
    this.oid_Empresa = oid_Empresa;
  }

  public void setOid_Padrao (long oid_Padrao) {
    this.oid_Padrao = oid_Padrao;
  }

  public void setOid_Pessoa (String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }

  public void setOid_Pessoa_Consignatario (String oid_Pessoa_Consignatario) {
    this.oid_Pessoa_Consignatario = oid_Pessoa_Consignatario;
  }

  public void setOid_Pessoa_Destinatario (String oid_Pessoa_Destinatario) {
    this.oid_Pessoa_Destinatario = oid_Pessoa_Destinatario;
  }

  public void setOid_Pessoa_Entregadora (String oid_Pessoa_Entregadora) {
    this.oid_Pessoa_Entregadora = oid_Pessoa_Entregadora;
  }

  public void setOid_Pessoa_Pagador (String oid_Pessoa_Pagador) {
    this.oid_Pessoa_Pagador = oid_Pessoa_Pagador;
  }

  public void setOid_Pessoa_Redespacho (String oid_Pessoa_Redespacho) {
    this.oid_Pessoa_Redespacho = oid_Pessoa_Redespacho;
  }

  public void setOid_Pessoa_Unidade (String oid_Pessoa_Unidade) {
    this.oid_Pessoa_Unidade = oid_Pessoa_Unidade;
  }

  public void setOid_Seguradora (long oid_Seguradora) {
    this.oid_Seguradora = oid_Seguradora;
  }

  public void setOid_Unidade (long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }

  public void setAcao (String acao) {
    this.acao = acao;
  }

  public void setNR_Codigo_Cliente (String NR_Codigo_Cliente) {
    this.NR_Codigo_Cliente = NR_Codigo_Cliente;
  }

  public void setNR_Dias_Entrega_Previsto (long NR_Dias_Entrega_Previsto) {
    this.NR_Dias_Entrega_Previsto = NR_Dias_Entrega_Previsto;
  }

  public void setNR_Dias_Entrega_Realizado (int NR_Dias_Entrega_Realizado) {
    this.NR_Dias_Entrega_Realizado = NR_Dias_Entrega_Realizado;
  }

  public void setDM_Tracking (String DM_Tracking) {
    this.DM_Tracking = DM_Tracking;
  }

  public void setNR_Itens2 (double NR_Itens2) {
    this.NR_Itens2 = NR_Itens2;
  }

  public void setNM_Ano_Inicial (String NM_Ano_Inicial) {
    this.NM_Ano_Inicial = NM_Ano_Inicial;
  }

  public void setNM_Mes_Inicial (String NM_Mes_Inicial) {
    this.NM_Mes_Inicial = NM_Mes_Inicial;
  }

  public void setVL_TX_Coleta_Entrega (double VL_TX_Coleta_Entrega) {
    this.VL_TX_Coleta_Entrega = VL_TX_Coleta_Entrega;
  }

  public void setVL_Desconto (double VL_Desconto) {
    this.VL_Desconto = VL_Desconto;
  }

  public void setVL_Despacho (double VL_Despacho) {
    this.VL_Despacho = VL_Despacho;
  }

  public void setVL_Documento (double VL_Documento) {
    this.VL_Documento = VL_Documento;
  }

  public void setVL_Duplicata (double VL_Duplicata) {
    this.VL_Duplicata = VL_Duplicata;
  }

  public void setVL_Entrega (double VL_Entrega) {
    this.VL_Entrega = VL_Entrega;
  }

  public void setVL_Frete (double VL_Frete) {
    this.VL_Frete = VL_Frete;
  }

  public void setVL_Frete_Peso (double VL_Frete_Peso) {
    this.VL_Frete_Peso = VL_Frete_Peso;
  }

  public void setVL_Frete_Valor (double VL_Frete_Valor) {
    this.VL_Frete_Valor = VL_Frete_Valor;
  }

  public void setVL_ICMS (double VL_ICMS) {
    this.VL_ICMS = VL_ICMS;
  }

  public void setVL_ICMS_Conhecimento (double VL_ICMS_Conhecimento) {
    this.VL_ICMS_Conhecimento = VL_ICMS_Conhecimento;
  }

  public void setVL_ITR (double VL_ITR) {
    this.VL_ITR = VL_ITR;
  }

  public void setVL_Juro_Mora_Dia (double VL_Juro_Mora_Dia) {
    this.VL_Juro_Mora_Dia = VL_Juro_Mora_Dia;
  }

  public void setVL_Outros1 (double VL_Outros1) {
    this.VL_Outros1 = VL_Outros1;
  }

  public void setVL_Pedagio (double VL_Pedagio) {
    this.VL_Pedagio = VL_Pedagio;
  }

  public void setVL_SEC_CAT (double VL_SEC_CAT) {
    this.VL_SEC_CAT = VL_SEC_CAT;
  }

  public void setVL_Sec_Cat (double VL_Sec_Cat) {
    this.VL_Sec_Cat = VL_Sec_Cat;
  }

  public void setVL_Ademe (double VL_Ademe) {
    this.VL_Ademe = VL_Ademe;
  }

  public void setVL_Base_Calculo_ICMS (double VL_Base_Calculo_ICMS) {
    this.VL_Base_Calculo_ICMS = VL_Base_Calculo_ICMS;
  }

  public void setVL_Coleta (double VL_Coleta) {
    this.VL_Coleta = VL_Coleta;
  }

  public void setPE_Aliquota_ICMS (double PE_Aliquota_ICMS) {
    this.PE_Aliquota_ICMS = PE_Aliquota_ICMS;
  }

  public void setNR_Volumes_Conhecimento (double NR_Volumes_Conhecimento) {
    this.NR_Volumes_Conhecimento = NR_Volumes_Conhecimento;
  }

  public void setOID_Carreta (String OID_Carreta) {
    this.OID_Carreta = OID_Carreta;
  }

  public void setOID_Cidade_Destino (int OID_Cidade_Destino) {
    this.OID_Cidade_Destino = OID_Cidade_Destino;
  }

  public void setOID_Cidade_Origem (int OID_Cidade_Origem) {
    this.OID_Cidade_Origem = OID_Cidade_Origem;
  }

  public void setNR_Serie_Conhecimento (String NR_Serie_Conhecimento) {
    this.NR_Serie_Conhecimento = NR_Serie_Conhecimento;
  }

  public void setNR_Serie_Duplicata (String NR_Serie_Duplicata) {
    this.NR_Serie_Duplicata = NR_Serie_Duplicata;
  }

  public void setNR_Serie_Nota_Fiscal (String NR_Serie_Nota_Fiscal) {
    this.NR_Serie_Nota_Fiscal = NR_Serie_Nota_Fiscal;
  }

  public void setNR_Telefone (String NR_Telefone) {
    this.NR_Telefone = NR_Telefone;
  }

  public void setNR_Volume_Nota_Fiscal (double NR_Volume_Nota_Fiscal) {
    this.NR_Volume_Nota_Fiscal = NR_Volume_Nota_Fiscal;
  }

  public void setNR_Peso_Nota_Fiscal (double NR_Peso_Nota_Fiscal) {
    this.NR_Peso_Nota_Fiscal = NR_Peso_Nota_Fiscal;
  }

  public void setNR_Peso_Cubado_Conhecimento (double NR_Peso_Cubado_Conhecimento) {
    this.NR_Peso_Cubado_Conhecimento = NR_Peso_Cubado_Conhecimento;
  }

  public void setNR_Peso_Conhecimento (double NR_Peso_Conhecimento) {
    this.NR_Peso_Conhecimento = NR_Peso_Conhecimento;
  }

  public void setNR_Manifesto (String NR_Manifesto) {
    this.NR_Manifesto = NR_Manifesto;
  }

  public void setNR_Fatura (String NR_Fatura) {
    this.NR_Fatura = NR_Fatura;
  }

  public void setNR_Duplicata (String NR_Duplicata) {
    this.NR_Duplicata = NR_Duplicata;
  }

  public void setNR_CNPJ_CPF_Remetente (String NR_CNPJ_CPF_Remetente) {
    this.NR_CNPJ_CPF_Remetente = NR_CNPJ_CPF_Remetente;
  }

  public void setNR_CNPJ_CPF_Pagador (String NR_CNPJ_CPF_Pagador) {
    this.NR_CNPJ_CPF_Pagador = NR_CNPJ_CPF_Pagador;
  }

  public void setNR_CNPJ_CPF_Destinatario (String NR_CNPJ_CPF_Destinatario) {
    this.NR_CNPJ_CPF_Destinatario = NR_CNPJ_CPF_Destinatario;
  }

  public void setNR_CEP (String NR_CEP) {
    this.NR_CEP = NR_CEP;
  }

  public void setNM_Unidade_Entregadora (String NM_Unidade_Entregadora) {
    this.NM_Unidade_Entregadora = NM_Unidade_Entregadora;
  }

  public void setNM_Tipo_Veiculo (String NM_Tipo_Veiculo) {
    this.NM_Tipo_Veiculo = NM_Tipo_Veiculo;
  }

  public void setNM_Tipo_Documento (String NM_Tipo_Documento) {
    this.NM_Tipo_Documento = NM_Tipo_Documento;
  }

  public void setNM_Tipo_Cobranca (String NM_Tipo_Cobranca) {
    this.NM_Tipo_Cobranca = NM_Tipo_Cobranca;
  }

  public void setNM_Remetente (String NM_Remetente) {
    this.NM_Remetente = NM_Remetente;
  }

  public void setNM_Razao_Social (String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }

  public void setNM_Estado (String NM_Estado) {
    this.NM_Estado = NM_Estado;
  }

  public void setNM_Estado_Destino (String NM_Estado_Destino) {
    this.NM_Estado_Destino = NM_Estado_Destino;
  }

  public void setNM_Estado_Origem (String NM_Estado_Origem) {
    this.NM_Estado_Origem = NM_Estado_Origem;
  }

  public void setNM_Filial_Conhecimento (String NM_Filial_Conhecimento) {
    this.NM_Filial_Conhecimento = NM_Filial_Conhecimento;
  }

  public void setNM_Filial_Duplicata (String NM_Filial_Duplicata) {
    this.NM_Filial_Duplicata = NM_Filial_Duplicata;
  }

  public void setNM_Identificador_Registro (String NM_Identificador_Registro) {
    this.NM_Identificador_Registro = NM_Identificador_Registro;
  }

  public void setNM_Inscricao_Estadual_Destinatario (String NM_Inscricao_Estadual_Destinatario) {
    this.NM_Inscricao_Estadual_Destinatario = NM_Inscricao_Estadual_Destinatario;
  }

  public void setNM_Inscricao_Estadual_Remetente (String NM_Inscricao_Estadual_Remetente) {
    this.NM_Inscricao_Estadual_Remetente = NM_Inscricao_Estadual_Remetente;
  }

  public void setNM_Natureza (String NM_Natureza) {
    this.NM_Natureza = NM_Natureza;
  }

  public void setNM_Endereco (String NM_Endereco) {
    this.NM_Endereco = NM_Endereco;
  }

  public void setNM_Destinatario (String NM_Destinatario) {
    this.NM_Destinatario = NM_Destinatario;
  }

  public void setNM_Conta_Corrente (String NM_Conta_Corrente) {
    this.NM_Conta_Corrente = NM_Conta_Corrente;
  }

  public void setNM_Cidade_Origem (String NM_Cidade_Origem) {
    this.NM_Cidade_Origem = NM_Cidade_Origem;
  }

  public void setNM_Cidade_Destino (String NM_Cidade_Destino) {
    this.NM_Cidade_Destino = NM_Cidade_Destino;
  }

  public void setNM_Cidade (String NM_Cidade) {
    this.NM_Cidade = NM_Cidade;
  }

  public void setNM_Cia_Aerea (String NM_Cia_Aerea) {
    this.NM_Cia_Aerea = NM_Cia_Aerea;
  }

  public void setNM_Bairro (String NM_Bairro) {
    this.NM_Bairro = NM_Bairro;
  }

  public void setNM_Agente_Cobranca (String NM_Agente_Cobranca) {
    this.NM_Agente_Cobranca = NM_Agente_Cobranca;
  }

  public void setNM_Agencia (String NM_Agencia) {
    this.NM_Agencia = NM_Agencia;
  }

  public void setHR_Ocorrencia_Lancada (String HR_Ocorrencia_Lancada) {
    this.HR_Ocorrencia_Lancada = HR_Ocorrencia_Lancada;
  }

  public void setHR_Ocorrencia (String HR_Ocorrencia) {
    this.HR_Ocorrencia = HR_Ocorrencia;
  }

  public void setHR_Coleta (String HR_Coleta) {
    this.HR_Coleta = HR_Coleta;
  }

  public void setHR_Embarque (String HR_Embarque) {
    this.HR_Embarque = HR_Embarque;
  }

  public void setHR_Emissao (String HR_Emissao) {
    this.HR_Emissao = HR_Emissao;
  }

  public void setDT_Vencimento_Duplicata (String DT_Vencimento_Duplicata) {
    this.DT_Vencimento_Duplicata = DT_Vencimento_Duplicata;
  }

  public void setDT_Ocorrencia_Lancada (String DT_Ocorrencia_Lancada) {
    this.DT_Ocorrencia_Lancada = DT_Ocorrencia_Lancada;
  }

  public void setDT_Ocorrencia (String DT_Ocorrencia) {
    this.DT_Ocorrencia = DT_Ocorrencia;
  }

  public void setDT_Inicial (String DT_Inicial) {
    this.DT_Inicial = DT_Inicial;
  }

  public void setDT_Final (String DT_Final) {
    this.DT_Final = DT_Final;
  }

  public void setDT_Emissao_Nota_Fiscal (String DT_Emissao_Nota_Fiscal) {
    this.DT_Emissao_Nota_Fiscal = DT_Emissao_Nota_Fiscal;
  }

  public void setDT_Emissao_Duplicata (String DT_Emissao_Duplicata) {
    this.DT_Emissao_Duplicata = DT_Emissao_Duplicata;
  }

  public void setDT_Emissao_Conhecimento (String DT_Emissao_Conhecimento) {
    this.DT_Emissao_Conhecimento = DT_Emissao_Conhecimento;
  }

  public void setDT_Embarque (String DT_Embarque) {
    this.DT_Embarque = DT_Embarque;
  }

  public void setDT_Coleta (String DT_Coleta) {
    this.DT_Coleta = DT_Coleta;
  }

  public void setDM_Tipo_Transporte (String DM_Tipo_Transporte) {
    this.DM_Tipo_Transporte = DM_Tipo_Transporte;
  }

  public void setDM_Tipo_Ocorrencia (String DM_Tipo_Ocorrencia) {
    this.DM_Tipo_Ocorrencia = DM_Tipo_Ocorrencia;
  }

  public void setDM_Substituicao_Tributaria (String DM_Substituicao_Tributaria) {
    this.DM_Substituicao_Tributaria = DM_Substituicao_Tributaria;
  }

  public void setDM_Situacao_Cliente (String DM_Situacao_Cliente) {
    this.DM_Situacao_Cliente = DM_Situacao_Cliente;
  }

  public void setDM_Serie_Duplicata (String DM_Serie_Duplicata) {
    this.DM_Serie_Duplicata = DM_Serie_Duplicata;
  }

  public void setDM_RCTRC (String DM_RCTRC) {
    this.DM_RCTRC = DM_RCTRC;
  }

  public void setDM_RCTR_VI (String DM_RCTR_VI) {
    this.DM_RCTR_VI = DM_RCTR_VI;
  }

  public void setDM_RCTR_DC (String DM_RCTR_DC) {
    this.DM_RCTR_DC = DM_RCTR_DC;
  }

  public void setDM_RCTA (String DM_RCTA) {
    this.DM_RCTA = DM_RCTA;
  }

  public void setDM_Isencao_Seguro (String DM_Isencao_Seguro) {
    this.DM_Isencao_Seguro = DM_Isencao_Seguro;
  }

  public void setCD_Unidade_Entregadora (String CD_Unidade_Entregadora) {
    this.CD_Unidade_Entregadora = CD_Unidade_Entregadora;
  }

  public void setCD_Unidade (String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }

  public void setCD_Ocorrencia (String CD_Ocorrencia) {
    this.CD_Ocorrencia = CD_Ocorrencia;
  }

  public void setCD_Modal (String CD_Modal) {
    this.CD_Modal = CD_Modal;
  }

  public void setCD_CFO_Conhecimento (String CD_CFO_Conhecimento) {
    this.CD_CFO_Conhecimento = CD_CFO_Conhecimento;
  }

  public void setNR_CX12 (String NR_CX12) {
    this.NR_CX12 = NR_CX12;
  }

  public void setNR_CX11 (String NR_CX11) {
    this.NR_CX11 = NR_CX11;
  }

  public void setNR_CX10 (String NR_CX10) {
    this.NR_CX10 = NR_CX10;
  }

  public void setNR_CX9 (String NR_CX9) {
    this.NR_CX9 = NR_CX9;
  }

  public void setNR_CX8 (String NR_CX8) {
    this.NR_CX8 = NR_CX8;
  }

  public void setNR_CX7 (String NR_CX7) {
    this.NR_CX7 = NR_CX7;
  }

  public void setNR_CX6 (String NR_CX6) {
    this.NR_CX6 = NR_CX6;
  }

  public void setNR_CX5 (String NR_CX5) {
    this.NR_CX5 = NR_CX5;
  }

  public void setNR_CX4 (String NR_CX4) {
    this.NR_CX4 = NR_CX4;
  }

  public void setNR_CX3 (String NR_CX3) {
    this.NR_CX3 = NR_CX3;
  }

  public void setNR_CX2 (String NR_CX2) {
    this.NR_CX2 = NR_CX2;
  }

  public void setNR_CX1 (String NR_CX1) {
    this.NR_CX1 = NR_CX1;
  }

  public void setUF_Destino (String UF_Destino) {
    this.UF_Destino = UF_Destino;
  }

  public void setUF_Origem (String UF_Origem) {
    this.UF_Origem = UF_Origem;
  }

  public void setNM_Centro_Custo (String NM_Centro_Custo) {
    this.NM_Centro_Custo = NM_Centro_Custo;
  }

  public void setDM_Tipo_Documento (String DM_Tipo_Documento) {
    this.DM_Tipo_Documento = DM_Tipo_Documento;
  }

  public void setNR_Conhecimento_Final (long NR_Conhecimento_Final) {
    this.NR_Conhecimento_Final = NR_Conhecimento_Final;
  }

  public void setNR_Conhecimento_Inicial (long NR_Conhecimento_Inicial) {
    this.NR_Conhecimento_Inicial = NR_Conhecimento_Inicial;
  }

  public void setDM_Tipo_Postagem (String DM_Tipo_Postagem) {
    this.DM_Tipo_Postagem = DM_Tipo_Postagem;
  }

  public void setNR6 (double NR6) {
    this.NR6 = NR6;
  }

  public void setNR5 (double NR5) {
    this.NR5 = NR5;
  }

  public void setNR4 (double NR4) {
    this.NR4 = NR4;
  }

  public void setNR3 (double NR3) {
    this.NR3 = NR3;
  }

  public void setNR2 (double NR2) {
    this.NR2 = NR2;
  }

  public void setNR1 (double NR1) {
    this.NR1 = NR1;
  }

  public void setNR0 (double NR0) {
    this.NR0 = NR0;
  }

  public void setNR_Postagem (long NR_Postagem) {
    this.NR_Postagem = NR_Postagem;
  }
public String getCD_Rota_Entrega() {
	return CD_Rota_Entrega;
}
public void setCD_Rota_Entrega(String rota_Entrega) {
	CD_Rota_Entrega = rota_Entrega;
}
public String getOID_Pessoa_Entregadora() {
	return OID_Pessoa_Entregadora;
}
public void setOID_Pessoa_Entregadora(String pessoa_Entregadora) {
	OID_Pessoa_Entregadora = pessoa_Entregadora;
}
public double getVL_Seguro() {
	return VL_Seguro;
}
public void setVL_Seguro(double seguro) {
	VL_Seguro = seguro;
}
public String getNm_arquivo_imp() {
	return nm_arquivo_imp;
}
public void setNm_arquivo_imp(String nm_arquivo_imp) {
	this.nm_arquivo_imp = nm_arquivo_imp;
}
public String getNm_chave_nfe() {
	return nm_chave_nfe;
}
public void setNm_chave_nfe(String nm_chave_nfe) {
	this.nm_chave_nfe = nm_chave_nfe;
}
}
