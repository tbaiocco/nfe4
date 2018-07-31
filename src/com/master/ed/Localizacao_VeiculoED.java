package com.master.ed;


public class Localizacao_VeiculoED extends MasterED {
  private String NM_Empresa;
  private long OID_Unidade;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;

  private String DT_Localizacao_Inicial;
  private String DT_Localizacao_Final;

  private String DM_Relatorio;

  private String NM_Localizacao1;
  private String NM_Localizacao2;
  private String NM_Localizacao3;

  private String NR_Sort;
  private long OID_Cidade_Origem;
  private long OID_Cidade_Destino;
  private long OID_Ordem_Servico;
  private String DT_Localizacao1;
  private String DT_Localizacao2;
  private String DT_Localizacao3;
  private String HR_Localizacao1;
  private String HR_Localizacao2;
  private String HR_Localizacao3;
  private long OID_Coleta;
  private String NM_Origem;
  private String TX_Observacao;
  private String DM_Situacao;

  private String OID_Manifesto;
  private String OID_Localizacao_Veiculo;
  private String OID_Veiculo;
  private String NM_Unidade;
  private String NM_Destino;
  private String DT_Localizacao;
  private String HR_Localizacao;
  private long OID_Empresa;
  private String OID_Motorista;
  private String DT_Previsao_Chegada;
  private String HR_Previsao_Chegada;
  private String NM_Carregamento;
  private String OID_Ordem_Frete;
  private String DM_Tipo;
  private String DM_Procedencia;
  private String NR_Frota;
  private int oid_Modelo_Veiculo;
  private int oid_Tipo_Veiculo;
  private int oid_Marca_Veiculo;
  private String oid_Motorista;
  private String oid_Proprietario;
  private String DM_Classificacao;
  private String NM_Tipo;
  private String NM_Modelo_Veiculo;
  private String NM_Motorista;
  private String NM_Proprietario;
  private String NR_Rastreador;
  private String NM_Marca;
  private String DM_Tipo_Implemento;
  private String NR_Placa;
  private String OID_Carreta;
  private String DT_Coleta;
  private String NM_Local_Coleta;
  private int QT_Entregas;
  private String DT_Embarque;
  private String NR_Manifesto;
  private String NM_Modelo_Carreta;
  private String DM_Expresso;
  private String DM_Procedencia_Carreta;
  private String DM_Tipo_Monitoramento;
  private int NR_Ordem;
  private int NR_Total;
  private String NM_Arquivo;
  private String CD_Seguradora;
  private int oid_Seguradora;
  private String NM_Situacao_Cavalo;
  private String NM_Status;
  private int NR_KIT;
  private int NR_Ajudante;
  private String NM_Ultima_Ocorrencia;
  private String OID_Conhecimento;
  private String NR_Conhecimento;

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

  public void setNM_Localizacao1(String NM_Localizacao1) {
    this.NM_Localizacao1 = NM_Localizacao1;
  }
  public String getNM_Localizacao1() {
    return NM_Localizacao1;
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

  public void setDM_Relatorio(String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }
  public String getDM_Relatorio() {
    return DM_Relatorio;
  }

  public void setNM_Localizacao2(String NM_Localizacao2) {
    this.NM_Localizacao2 = NM_Localizacao2;
  }
  public String getNM_Localizacao2() {
    return NM_Localizacao2;
  }

  public void setNM_Localizacao3(String NM_Localizacao3) {
    this.NM_Localizacao3 = NM_Localizacao3;
  }
  public String getNM_Localizacao3() {
    return NM_Localizacao3;
  }

  public void setNR_Sort(String NR_Sort) {
    this.NR_Sort = NR_Sort;
  }
  public String getNR_Sort() {
    return NR_Sort;
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

  public void setDT_Localizacao1(String DT_Localizacao1) {
    this.DT_Localizacao1 = DT_Localizacao1;
  }
  public String getDT_Localizacao1() {
    return DT_Localizacao1;
  }
  public void setDT_Localizacao2(String DT_Localizacao2) {
    this.DT_Localizacao2 = DT_Localizacao2;
  }
  public String getDT_Localizacao2() {
    return DT_Localizacao2;
  }
  public void setDT_Localizacao3(String DT_Localizacao3) {
    this.DT_Localizacao3 = DT_Localizacao3;
  }
  public String getDT_Localizacao3() {
    return DT_Localizacao3;
  }
  public void setHR_Localizacao1(String HR_Localizacao1) {
    this.HR_Localizacao1 = HR_Localizacao1;
  }
  public String getHR_Localizacao1() {
    return HR_Localizacao1;
  }
  public void setHR_Localizacao2(String HR_Localizacao2) {
    this.HR_Localizacao2 = HR_Localizacao2;
  }
  public String getHR_Localizacao2() {
    return HR_Localizacao2;
  }

  public void setOID_Coleta(long OID_Coleta) {
    this.OID_Coleta = OID_Coleta;
  }
  public long getOID_Coleta() {
    return OID_Coleta;
  }

  public void setNM_Origem(String NM_Origem) {
    this.NM_Origem = NM_Origem;
  }
  public String getNM_Origem() {
    return NM_Origem;
  }
  public void setTX_Observacao(String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }
  public String getTX_Observacao() {
    return TX_Observacao;
  }

  public String getOID_Manifesto() {
    return OID_Manifesto;
  }
  public void setOID_Manifesto(String OID_Manifesto) {
    this.OID_Manifesto = OID_Manifesto;
  }

  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }

  public String getOID_Localizacao_Veiculo() {
    return OID_Localizacao_Veiculo;
  }
  public void setOID_Localizacao_Veiculo(String OID_Localizacao_Veiculo) {
    this.OID_Localizacao_Veiculo = OID_Localizacao_Veiculo;
  }

  public String getDT_Localizacao_Final() {
    return DT_Localizacao_Final;
  }
  public String getDT_Localizacao_Inicial() {
    return DT_Localizacao_Inicial;
  }
  public void setDT_Localizacao_Final(String DT_Localizacao_Final) {
    this.DT_Localizacao_Final = DT_Localizacao_Final;
  }
  public void setDT_Localizacao_Inicial(String DT_Localizacao_Inicial) {
    this.DT_Localizacao_Inicial = DT_Localizacao_Inicial;
  }
  public void setHR_Localizacao3(String HR_Localizacao3) {
    this.HR_Localizacao3 = HR_Localizacao3;
  }
  public String getHR_Localizacao3() {
    return HR_Localizacao3;
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

  public long getOID_Ordem_Servico () {
    return OID_Ordem_Servico;
  }

  public void setOID_Ordem_Servico (long OID_Ordem_Servico) {
    this.OID_Ordem_Servico = OID_Ordem_Servico;
  }

  public void setNM_Destino (String NM_Destino) {
    this.NM_Destino = NM_Destino;
  }

  public String getNM_Destino () {
    return NM_Destino;
  }

  public void setDT_Localizacao (String DT_Localizacao) {
    this.DT_Localizacao = DT_Localizacao;
  }

  public String getDT_Localizacao () {
    return DT_Localizacao;
  }

  public void setHR_Localizacao (String HR_Localizacao) {
    this.HR_Localizacao = HR_Localizacao;
  }

  public String getHR_Localizacao () {
    return HR_Localizacao;
  }

  public void setOID_Empresa (long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }

  public long getOID_Empresa () {
    return OID_Empresa;
  }

  public void setOID_Motorista (String OID_Motorista) {
    this.OID_Motorista = OID_Motorista;
  }

  public String getOID_Motorista () {
    return OID_Motorista;
  }

  public void setDT_Previsao_Chegada (String DT_Previsao_Chegada) {
    this.DT_Previsao_Chegada = DT_Previsao_Chegada;
  }

  public String getDT_Previsao_Chegada () {
    return DT_Previsao_Chegada;
  }

  public void setHR_Previsao_Chegada (String HR_Previsao_Chegada) {
    this.HR_Previsao_Chegada = HR_Previsao_Chegada;
  }

  public String getHR_Previsao_Chegada () {
    return HR_Previsao_Chegada;
  }

  public void setNM_Carregamento (String NM_Carregamento) {
    this.NM_Carregamento = NM_Carregamento;
  }

  public String getNM_Carregamento () {
    return NM_Carregamento;
  }

  public void setOID_Ordem_Frete (String OID_Ordem_Frete) {
    this.OID_Ordem_Frete = OID_Ordem_Frete;
  }

  public String getOID_Ordem_Frete () {
    return OID_Ordem_Frete;
  }

  public void setDM_Tipo (String DM_Tipo) {
    this.DM_Tipo = DM_Tipo;
  }

  public String getDM_Tipo () {
    return DM_Tipo;
  }

  public void setDM_Procedencia (String DM_Procedencia) {
    this.DM_Procedencia = DM_Procedencia;
  }

  public String getDM_Procedencia () {
    return DM_Procedencia;
  }

  public void setNR_Frota (String NR_Frota) {
    this.NR_Frota = NR_Frota;
  }

  public String getNR_Frota () {
    return NR_Frota;
  }

  public void setOid_Modelo_Veiculo (int oid_Modelo_Veiculo) {
    this.oid_Modelo_Veiculo = oid_Modelo_Veiculo;
  }

  public void setOid_Tipo_Veiculo (int oid_Tipo_Veiculo) {
    this.oid_Tipo_Veiculo = oid_Tipo_Veiculo;
  }

  public void setOid_Marca_Veiculo (int oid_Marca_Veiculo) {
    this.oid_Marca_Veiculo = oid_Marca_Veiculo;
  }

  public void setOid_Motorista (String oid_Motorista) {
    this.oid_Motorista = oid_Motorista;
  }

  public void setOid_Proprietario (String oid_Proprietario) {
    this.oid_Proprietario = oid_Proprietario;
  }

  public void setOid_Seguradora (int oid_Seguradora) {
    this.oid_Seguradora = oid_Seguradora;
  }

  public void setNR_Conhecimento (String NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }

  public void setOID_Conhecimento (String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }

  public void setNM_Ultima_Ocorrencia (String NM_Ultima_Ocorrencia) {
    this.NM_Ultima_Ocorrencia = NM_Ultima_Ocorrencia;
  }

  public void setNR_Ajudante (int NR_Ajudante) {
    this.NR_Ajudante = NR_Ajudante;
  }

  public void setNR_KIT (int NR_KIT) {
    this.NR_KIT = NR_KIT;
  }

  public void setNM_Status (String NM_Status) {
    this.NM_Status = NM_Status;
  }

  public void setNM_Situacao_Cavalo (String NM_Situacao_Cavalo) {

    this.NM_Situacao_Cavalo = NM_Situacao_Cavalo;
  }

  public void setCD_Seguradora (String CD_Seguradora) {
    this.CD_Seguradora = CD_Seguradora;
  }

  public void setNM_Arquivo (String NM_Arquivo) {
    this.NM_Arquivo = NM_Arquivo;
  }

  public void setNR_Total (int NR_Total) {
    this.NR_Total = NR_Total;
  }

  public void setNR_Ordem (int NR_Ordem) {
    this.NR_Ordem = NR_Ordem;
  }

  public void setDM_Tipo_Monitoramento (String DM_Tipo_Monitoramento) {
    this.DM_Tipo_Monitoramento = DM_Tipo_Monitoramento;
  }

  public void setDM_Procedencia_Carreta (String DM_Procedencia_Carreta) {
    this.DM_Procedencia_Carreta = DM_Procedencia_Carreta;
  }

  public void setDM_Expresso (String DM_Expresso) {
    this.DM_Expresso = DM_Expresso;
  }

  public void setNM_Modelo_Carreta (String NM_Modelo_Carreta) {
    this.NM_Modelo_Carreta = NM_Modelo_Carreta;
  }

  public void setNR_Manifesto (String NR_Manifesto) {
    this.NR_Manifesto = NR_Manifesto;
  }

  public void setDT_Embarque (String DT_Embarque) {
    this.DT_Embarque = DT_Embarque;
  }

  public void setQT_Entregas (int QT_Entregas) {

    this.QT_Entregas = QT_Entregas;
  }

  public void setNM_Local_Coleta (String NM_Local_Coleta) {
    this.NM_Local_Coleta = NM_Local_Coleta;
  }

  public void setDT_Coleta (String DT_Coleta) {
    this.DT_Coleta = DT_Coleta;
  }

  public void setOID_Carreta (String OID_Carreta) {
    this.OID_Carreta = OID_Carreta;
  }

  public void setNR_Placa (String NR_Placa) {
    this.NR_Placa = NR_Placa;
  }

  public void setDM_Tipo_Implemento (String DM_Tipo_Implemento) {
    this.DM_Tipo_Implemento = DM_Tipo_Implemento;
  }

  public void setNM_Marca (String NM_Marca) {
    this.NM_Marca = NM_Marca;
  }

  public void setNR_Rastreador (String NR_Rastreador) {
    this.NR_Rastreador = NR_Rastreador;
  }

  public void setNM_Proprietario (String NM_Proprietario) {
    this.NM_Proprietario = NM_Proprietario;
  }

  public void setNM_Motorista (String NM_Motorista) {
    this.NM_Motorista = NM_Motorista;
  }

  public void setNM_Modelo_Veiculo (String NM_Modelo_Veiculo) {

    this.NM_Modelo_Veiculo = NM_Modelo_Veiculo;
  }

  public void setNM_Tipo (String NM_Tipo) {
    this.NM_Tipo = NM_Tipo;
  }

  public void setDM_Classificacao (String DM_Classificacao) {
    this.DM_Classificacao = DM_Classificacao;
  }

  public int getOid_Modelo_Veiculo () {
    return oid_Modelo_Veiculo;
  }

  public int getOid_Tipo_Veiculo () {
    return oid_Tipo_Veiculo;
  }

  public int getOid_Marca_Veiculo () {
    return oid_Marca_Veiculo;
  }

  public String getOid_Motorista () {
    return oid_Motorista;
  }

  public String getOid_Proprietario () {
    return oid_Proprietario;
  }

  public int getOid_Seguradora () {
    return oid_Seguradora;
  }

  public String getNR_Conhecimento () {
    return NR_Conhecimento;
  }

  public String getOID_Conhecimento () {
    return OID_Conhecimento;
  }

  public String getNM_Ultima_Ocorrencia () {
    return NM_Ultima_Ocorrencia;
  }

  public int getNR_Ajudante () {
    return NR_Ajudante;
  }

  public int getNR_KIT () {
    return NR_KIT;
  }

  public String getNM_Status () {
    return NM_Status;
  }

  public String getNM_Situacao_Cavalo () {

    return NM_Situacao_Cavalo;
  }

  public String getCD_Seguradora () {
    return CD_Seguradora;
  }

  public String getNM_Arquivo () {
    return NM_Arquivo;
  }

  public int getNR_Total () {
    return NR_Total;
  }

  public int getNR_Ordem () {
    return NR_Ordem;
  }

  public String getDM_Tipo_Monitoramento () {
    return DM_Tipo_Monitoramento;
  }

  public String getDM_Procedencia_Carreta () {
    return DM_Procedencia_Carreta;
  }

  public String getDM_Expresso () {
    return DM_Expresso;
  }

  public String getNM_Modelo_Carreta () {
    return NM_Modelo_Carreta;
  }

  public String getNR_Manifesto () {
    return NR_Manifesto;
  }

  public String getDT_Embarque () {
    return DT_Embarque;
  }

  public int getQT_Entregas () {

    return QT_Entregas;
  }

  public String getNM_Local_Coleta () {
    return NM_Local_Coleta;
  }

  public String getDT_Coleta () {
    return DT_Coleta;
  }

  public String getOID_Carreta () {
    return OID_Carreta;
  }

  public String getNR_Placa () {
    return NR_Placa;
  }

  public String getDM_Tipo_Implemento () {
    return DM_Tipo_Implemento;
  }

  public String getNM_Marca () {
    return NM_Marca;
  }

  public String getNR_Rastreador () {
    return NR_Rastreador;
  }

  public String getNM_Proprietario () {
    return NM_Proprietario;
  }

  public String getNM_Motorista () {
    return NM_Motorista;
  }

  public String getNM_Modelo_Veiculo () {

    return NM_Modelo_Veiculo;
  }

  public String getNM_Tipo () {
    return NM_Tipo;
  }

  public String getDM_Classificacao () {
    return DM_Classificacao;
  }

}
