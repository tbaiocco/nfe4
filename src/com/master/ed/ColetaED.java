package com.master.ed;

public class ColetaED 
    extends RelatorioBaseED {

  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String DT_Emissao;
  private String HR_Emissao;
  private String DT_Coleta;
  private long OID_Unidade;
  private long OID_Produto;
  private String CD_Unidade;
  private String NM_Fantasia;
  private String NM_Endereco_Coleta;
  private String NM_Bairro_Coleta;
  private String TX_Observacao;
  private String NR_Telefone;
  private String NM_E_Mail;
  private String NM_Pessoa_Solicitante;
  private String NM_Pessoa_Contato;  
  private String NM_Pessoa_Remetente;
  private String NM_Motorista;
  private String NM_Pessoa_Destinatario;
  private String NM_Pessoa_Destinatario2;
  private String NM_Pessoa_Destinatario3;
  private String NM_Pessoa_Destinatario4;
  private String OID_Coleta;
  private String CD_Cidade;
  private String NM_Cidade;
  private String DM_Impresso;
  private String DM_Finalizado;  
  private String NM_Cidade_Destino;
  private double VL_Custo;
  private double NR_Cubagem;
  private double VL_Mercadoria;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;

  private String DT_Programada_Inicial;
  private String DT_Programada_Final;

  private long OID_Cidade;
  private long NR_Volumes;
  private double NR_Peso;
  private String NR_Coleta;
  private String DM_Situacao;
  private String HR_Coleta_Minima;
  private String HR_Coleta_Maxima;
  private String DT_Programada;
  private String HR_Programada;
  private String NR_Placa;
  private String DM_Procedencia;
  private String OID_Veiculo;
  private String OID_Conhecimento;
  private String DM_Relatorio;
  private long OID_Modal;
  private String NR_Pedido;

  private long oid_Programacao_Carga;

  private String OID_Veiculo_Carreta;
  private String NR_Placa_Carreta;

  private String OID_Carreta2;
  private String NR_Placa_Carreta2;

  private String NM_Solicitante;

  private long NR_Ramal_Solicitante;
  
  private long NR_Ramal_Contato;  

  private String HR_Coleta;

  private String DT_Coletado;

  private String HR_Coletado;

  private String DM_Intervalo_Almoco;

  private String DM_Tipo_Veiculo;

  private String DM_Munck;

  private String DM_KIT;

  private int QT_Volumes;

  private String Usuario_Stamp;

  private String Dt_Stamp;

  private String Dm_Stamp;

  private int oid_AIDOF;

  private String NM_Razao_Social;

  private String NM_Razao_Social_Destinatario;

  private String NM_Modal;

  private String CD_Modal;

  private String NM_Destinatario;

  private String CD_Cidade_Destinatario;
  private long OID_Cidade_Destinatario;
  private String NM_Cidade_Destinatario;

  private String NM_Documento;

  private String DM_Tipo_Conhecimento;

  private double VL_Total_Frete;

  private String DT_Previsao_Entrega;

  private String HR_Previsao_Entrega;

  private int NR_Peso2;

  private int NR_Peso3;

  private int NR_Peso4;

  private String NM_Destinatario2;

  private String NM_Destinatario3;

  private String NM_Destinatario4;

  private String NM_Cidade_Destinatario2;

  private String NM_Cidade_Destinatario3;

  private String NM_Cidade_Destinatario4;

  private String NM_Documento2;

  private String NM_Documento3;

  private String NM_Documento4;

  private String OID_Pessoa_Pagador;

  private double VL_Custo_Notas_Fiscais;

  private double VL_Total_Frete_Notas_Fiscais;

  private double VL_Mercadoria_Notas_Fiscais;

  private double NR_Peso_Notas_Fiscais;

  private double QT_Volumes_Notas_Fiscais;

  private int QT_Conhecimentos_Gerados;

  private int QT_Conhecimentos_OK;

  private int QT_Conhecimentos_Impressos;

  private int QT_Notas_Fiscais;

  private int QT_Notas_Notas_Fiscais;

  private String NM_Especie;
  private String NM_Especie2;
  private String NM_Especie3;
  private String NM_Especie4;

  private String DT_Coleta_Destino;
  private String DT_Coleta_Destino2;
  private String DT_Coleta_Destino3;
  private String DT_Coleta_Destino4;

  private long NR_Volumes2;
  private long NR_Volumes3;
  private long NR_Volumes4;

  private long NR_Conhecimento;
  private long NR_Conhecimento2;
  private long NR_Conhecimento3;
  private long NR_Conhecimento4;
  private String NR_Cartao;
  private String NR_Liberacao;
  private String NM_Rota;
  private String OID_Carreta;
  private String OID_Motorista;
  private String DM_Tipo;
  private String DM_Tipo_Coleta;

  private String NM_Endereco_Unidade;
  private String NM_Razao_Social_Unidade;
  private String NM_Cidade_Unidade;
  private String NR_CNPJ_CPF_Unidade;
  private String NR_Fone_Unidade;
  private String OID_Cotacao;
  private String NM_Tipo_Coleta;
  private String OID_Pessoa_Usuario;
  private long oid_Usuario;
  private double NR_Peso_Cubado;
  private double VL_Cotacao;
  private String DM_Situacao_Cotacao;
  private String NM_Situacao_Cotacao;
  private String NM_Situacao;
  private String TX_Motivo_Perda;
  private String DM_Motivo_Perda;
  private String acao;

  public void setOID_Coleta (String OID_Coleta) {
    this.OID_Coleta = OID_Coleta;
  }

  public String getOID_Coleta () {
    return OID_Coleta;
  }

  public void setOID_Pessoa (String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }

  public void setOID_Pessoa_Destinatario (String OID_Pessoa_Destinatario) {
    this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
  }

  public String getOID_Pessoa_Destinatario () {
    return OID_Pessoa_Destinatario;
  }

  public void setOID_Unidade (long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }

  public long getOID_Unidade () {
    return OID_Unidade;
  }

  public void setDT_Emissao (String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }

  public String getDT_Emissao () {
    return DT_Emissao;
  }

  public void setDT_Coleta (String DT_Coleta) {
    this.DT_Coleta = DT_Coleta;
  }

  public String getDT_Coleta () {
    return DT_Coleta;
  }

  public void setCD_Unidade (String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }

  public String getCD_Unidade () {
    return CD_Unidade;
  }

  public void setNM_Fantasia (String NM_Fantasia) {
    this.NM_Fantasia = NM_Fantasia;
  }

  public String getNM_Fantasia () {
    return NM_Fantasia;
  }

  public void setNM_Pessoa_Remetente (String NM_Pessoa_Remetente) {
    this.NM_Pessoa_Remetente = NM_Pessoa_Remetente;
  }

  public String getNM_Pessoa_Remetente () {
    return NM_Pessoa_Remetente;
  }

  public void setNM_Endereco_Coleta (String NM_Endereco_Coleta) {
    this.NM_Endereco_Coleta = NM_Endereco_Coleta;
  }

  public String getNM_Endereco_Coleta () {
    return NM_Endereco_Coleta;
  }

  public void setNM_Bairro_Coleta (String NM_Bairro_Coleta) {
    this.NM_Bairro_Coleta = NM_Bairro_Coleta;
  }

  public String getNM_Bairro_Coleta () {
    return NM_Bairro_Coleta;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setNR_Telefone (String NR_Telefone) {
    this.NR_Telefone = NR_Telefone;
  }

  public String getNR_Telefone () {
    return NR_Telefone;
  }

  public void setNM_E_Mail (String NM_E_Mail) {
    this.NM_E_Mail = NM_E_Mail;
  }

  public String getNM_E_Mail () {
    return NM_E_Mail;
  }

  public void setNM_Pessoa_Solicitante (String NM_Pessoa_Solicitante) {
    this.NM_Pessoa_Solicitante = NM_Pessoa_Solicitante;
  }

  public String getNM_Pessoa_Solicitante () {
    return NM_Pessoa_Solicitante;
  }

  public void setNM_Pessoa_Destinatario (String NM_Pessoa_Destinatario) {
    this.NM_Pessoa_Destinatario = NM_Pessoa_Destinatario;
  }

  public String getNM_Pessoa_Destinatario () {
    return NM_Pessoa_Destinatario;
  }

  public void setNM_Cidade_Destino (String NM_Cidade_Destino) {
    this.NM_Cidade_Destino = NM_Cidade_Destino;
  }

  public String getNM_Cidade_Destino () {
    return NM_Cidade_Destino;
  }

  public void setNM_Cidade (String NM_Cidade) {
    this.NM_Cidade = NM_Cidade;
  }

  public String getNM_Cidade () {
    return NM_Cidade;
  }

  public void setCD_Cidade (String CD_Cidade) {
    this.CD_Cidade = CD_Cidade;
  }

  public String getCD_Cidade () {
    return CD_Cidade;
  }

  public void setVL_Custo (double VL_Custo) {
    this.VL_Custo = VL_Custo;
  }

  public double getVL_Custo () {
    return VL_Custo;
  }

  public void setNR_Cubagem (double NR_Cubagem) {
    this.NR_Cubagem = NR_Cubagem;
  }

  public double getNR_Cubagem () {
    return NR_Cubagem;
  }

  public void setVL_Mercadoria (double VL_Mercadoria) {
    this.VL_Mercadoria = VL_Mercadoria;
  }

  public double getVL_Mercadoria () {
    return VL_Mercadoria;
  }

  public void setDT_Emissao_Inicial (String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }

  public String getDT_Emissao_Inicial () {
    return DT_Emissao_Inicial;
  }

  public void setDT_Emissao_Final (String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }

  public String getDT_Emissao_Final () {
    return DT_Emissao_Final;
  }

  public void setOID_Cidade (long OID_Cidade) {
    this.OID_Cidade = OID_Cidade;
  }

  public long getOID_Cidade () {
    return OID_Cidade;
  }

  public void setNR_Volumes (long NR_Volumes) {
    this.NR_Volumes = NR_Volumes;
  }

  public long getNR_Volumes () {
    return NR_Volumes;
  }

  public void setNR_Peso (double NR_Peso) {
    this.NR_Peso = NR_Peso;
  }

  public double getNR_Peso () {
    return NR_Peso;
  }

  public void setNR_Coleta (String NR_Coleta) {
    this.NR_Coleta = NR_Coleta;
  }

  public String getNR_Coleta () {
    return NR_Coleta;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setHR_Coleta_Minima (String HR_Coleta_Minima) {
    this.HR_Coleta_Minima = HR_Coleta_Minima;
  }

  public String getHR_Coleta_Minima () {
    return HR_Coleta_Minima;
  }

  public void setHR_Coleta_Maxima (String HR_Coleta_Maxima) {
    this.HR_Coleta_Maxima = HR_Coleta_Maxima;
  }

  public String getHR_Coleta_Maxima () {
    return HR_Coleta_Maxima;
  }

  public void setDT_Programada (String DT_Programada) {
    this.DT_Programada = DT_Programada;
  }

  public String getDT_Programada () {
    return DT_Programada;
  }

  public void setHR_Programada (String HR_Programada) {
    this.HR_Programada = HR_Programada;
  }

  public String getHR_Programada () {
    return HR_Programada;
  }

  public void setNR_Placa (String NR_Placa) {
    this.NR_Placa = NR_Placa;
  }

  public String getNR_Placa () {
    return NR_Placa;
  }

  public void setDM_Procedencia (String DM_Procedencia) {
    this.DM_Procedencia = DM_Procedencia;
  }

  public String getDM_Procedencia () {
    return DM_Procedencia;
  }

  public String getOID_Veiculo () {
    return OID_Veiculo;
  }

  public void setOID_Veiculo (String veiculo) {
    OID_Veiculo = veiculo;
  }

  public String getOID_Conhecimento () {
    return OID_Conhecimento;
  }

  public void setOID_Conhecimento (String conhecimento) {
    OID_Conhecimento = conhecimento;
  }

  public String getHR_Emissao () {
    return HR_Emissao;
  }

  public void setHR_Emissao (String emissao) {
    HR_Emissao = emissao;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public void setOID_Modal (long OID_Modal) {
    this.OID_Modal = OID_Modal;
  }

  public long getOID_Modal () {
    return OID_Modal;
  }

  public void setNR_Pedido (String NR_Pedido) {
    this.NR_Pedido = NR_Pedido;
  }

  public String getNR_Pedido () {
    return NR_Pedido;
  }

  public String getCD_Modal () {
    return CD_Modal;
  }

  public String getDM_Intervalo_Almoco () {
    return DM_Intervalo_Almoco;
  }

  public String getDM_KIT () {
    return DM_KIT;
  }

  public String getDM_Munck () {
    return DM_Munck;
  }

  public void setDM_Munck (String DM_Munck) {
    this.DM_Munck = DM_Munck;
  }

  public void setDM_KIT (String DM_KIT) {
    this.DM_KIT = DM_KIT;
  }

  public void setDM_Intervalo_Almoco (String DM_Intervalo_Almoco) {
    this.DM_Intervalo_Almoco = DM_Intervalo_Almoco;
  }

  public void setCD_Modal (String CD_Modal) {
    this.CD_Modal = CD_Modal;
  }

  public String getDm_Stamp () {
    return Dm_Stamp;
  }

  public String getDM_Tipo () {
    return DM_Tipo;
  }

  public String getDM_Tipo_Conhecimento () {
    return DM_Tipo_Conhecimento;
  }

  public String getDM_Tipo_Veiculo () {
    return DM_Tipo_Veiculo;
  }

  public String getDT_Coleta_Destino () {
    return DT_Coleta_Destino;
  }

  public String getDT_Coleta_Destino2 () {
    return DT_Coleta_Destino2;
  }

  public String getDT_Coleta_Destino3 () {
    return DT_Coleta_Destino3;
  }

  public String getDT_Coleta_Destino4 () {
    return DT_Coleta_Destino4;
  }

  public String getDT_Coletado () {
    return DT_Coletado;
  }

  public void setDT_Coletado (String DT_Coletado) {
    this.DT_Coletado = DT_Coletado;
  }

  public void setDT_Coleta_Destino4 (String DT_Coleta_Destino4) {
    this.DT_Coleta_Destino4 = DT_Coleta_Destino4;
  }

  public void setDT_Coleta_Destino3 (String DT_Coleta_Destino3) {
    this.DT_Coleta_Destino3 = DT_Coleta_Destino3;
  }

  public void setDT_Coleta_Destino2 (String DT_Coleta_Destino2) {
    this.DT_Coleta_Destino2 = DT_Coleta_Destino2;
  }

  public void setDT_Coleta_Destino (String DT_Coleta_Destino) {
    this.DT_Coleta_Destino = DT_Coleta_Destino;
  }

  public void setDM_Tipo_Veiculo (String DM_Tipo_Veiculo) {
    this.DM_Tipo_Veiculo = DM_Tipo_Veiculo;
  }

  public void setDM_Tipo_Conhecimento (String DM_Tipo_Conhecimento) {
    this.DM_Tipo_Conhecimento = DM_Tipo_Conhecimento;
  }

  public void setDM_Tipo (String DM_Tipo) {
    this.DM_Tipo = DM_Tipo;
  }

  public void setDm_Stamp (String Dm_Stamp) {
    this.Dm_Stamp = Dm_Stamp;
  }

  public void setDT_Previsao_Entrega (String DT_Previsao_Entrega) {
    this.DT_Previsao_Entrega = DT_Previsao_Entrega;
  }

  public String getDT_Previsao_Entrega () {
    return DT_Previsao_Entrega;
  }

  public void setDt_Stamp (String Dt_Stamp) {
    this.Dt_Stamp = Dt_Stamp;
  }

  public void setHR_Coleta (String HR_Coleta) {
    this.HR_Coleta = HR_Coleta;
  }

  public String getDt_Stamp () {
    return Dt_Stamp;
  }

  public String getHR_Coleta () {
    return HR_Coleta;
  }

  public String getHR_Coletado () {
    return HR_Coletado;
  }

  public String getHR_Previsao_Entrega () {
    return HR_Previsao_Entrega;
  }

  public void setHR_Coletado (String HR_Coletado) {
    this.HR_Coletado = HR_Coletado;
  }

  public void setHR_Previsao_Entrega (String HR_Previsao_Entrega) {
    this.HR_Previsao_Entrega = HR_Previsao_Entrega;
  }

  public void setNM_Cidade_Destinatario (String NM_Cidade_Destinatario) {
    this.NM_Cidade_Destinatario = NM_Cidade_Destinatario;
  }

  public void setNM_Cidade_Destinatario2 (String NM_Cidade_Destinatario2) {
    this.NM_Cidade_Destinatario2 = NM_Cidade_Destinatario2;
  }

  public void setNM_Cidade_Destinatario3 (String NM_Cidade_Destinatario3) {
    this.NM_Cidade_Destinatario3 = NM_Cidade_Destinatario3;
  }

  public void setNM_Cidade_Destinatario4 (String NM_Cidade_Destinatario4) {
    this.NM_Cidade_Destinatario4 = NM_Cidade_Destinatario4;
  }

  public String getNM_Cidade_Destinatario () {
    return NM_Cidade_Destinatario;
  }

  public String getNM_Cidade_Destinatario2 () {
    return NM_Cidade_Destinatario2;
  }

  public String getNM_Cidade_Destinatario3 () {
    return NM_Cidade_Destinatario3;
  }

  public String getNM_Cidade_Destinatario4 () {
    return NM_Cidade_Destinatario4;
  }

  public String getNM_Destinatario () {
    return NM_Destinatario;
  }

  public String getNM_Destinatario2 () {
    return NM_Destinatario2;
  }

  public String getNM_Destinatario3 () {
    return NM_Destinatario3;
  }

  public void setNM_Destinatario3 (String NM_Destinatario3) {
    this.NM_Destinatario3 = NM_Destinatario3;
  }

  public void setNM_Destinatario2 (String NM_Destinatario2) {
    this.NM_Destinatario2 = NM_Destinatario2;
  }

  public void setNM_Destinatario (String NM_Destinatario) {
    this.NM_Destinatario = NM_Destinatario;
  }

  public void setNM_Destinatario4 (String NM_Destinatario4) {
    this.NM_Destinatario4 = NM_Destinatario4;
  }

  public void setNM_Documento (String NM_Documento) {
    this.NM_Documento = NM_Documento;
  }

  public void setNM_Documento2 (String NM_Documento2) {
    this.NM_Documento2 = NM_Documento2;
  }

  public void setNM_Documento3 (String NM_Documento3) {
    this.NM_Documento3 = NM_Documento3;
  }

  public void setNM_Documento4 (String NM_Documento4) {
    this.NM_Documento4 = NM_Documento4;
  }

  public String getNM_Documento4 () {
    return NM_Documento4;
  }

  public String getNM_Documento3 () {
    return NM_Documento3;
  }

  public String getNM_Documento2 () {
    return NM_Documento2;
  }

  public String getNM_Documento () {
    return NM_Documento;
  }

  public String getNM_Destinatario4 () {
    return NM_Destinatario4;
  }

  public String getNM_Especie () {
    return NM_Especie;
  }

  public String getNM_Especie2 () {
    return NM_Especie2;
  }

  public String getNM_Especie3 () {
    return NM_Especie3;
  }

  public String getNM_Especie4 () {
    return NM_Especie4;
  }

  public void setNM_Especie4 (String NM_Especie4) {
    this.NM_Especie4 = NM_Especie4;
  }

  public void setNM_Especie3 (String NM_Especie3) {
    this.NM_Especie3 = NM_Especie3;
  }

  public void setNM_Especie2 (String NM_Especie2) {
    this.NM_Especie2 = NM_Especie2;
  }

  public void setNM_Especie (String NM_Especie) {
    this.NM_Especie = NM_Especie;
  }

  public String getDM_Finalizado() {
	return DM_Finalizado;
}

public void setDM_Finalizado(String finalizado) {
	DM_Finalizado = finalizado;
}

public void setNM_Modal (String NM_Modal) {
    this.NM_Modal = NM_Modal;
  }

  public String getNM_Modal () {
    return NM_Modal;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public String getNM_Razao_Social_Destinatario () {
    return NM_Razao_Social_Destinatario;
  }

  public String getNM_Rota () {
    return NM_Rota;
  }

  public String getNM_Solicitante () {
    return NM_Solicitante;
  }

  public String getNR_Cartao () {
    return NR_Cartao;
  }

  public void setNR_Cartao (String NR_Cartao) {
    this.NR_Cartao = NR_Cartao;
  }

  public void setNM_Solicitante (String NM_Solicitante) {
    this.NM_Solicitante = NM_Solicitante;
  }

  public void setNM_Rota (String NM_Rota) {
    this.NM_Rota = NM_Rota;
  }

  public void setNM_Razao_Social_Destinatario (String
                                               NM_Razao_Social_Destinatario) {
    this.NM_Razao_Social_Destinatario = NM_Razao_Social_Destinatario;
  }

  public void setNM_Razao_Social (String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }

  public void setNR_Conhecimento (long NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }

  public void setNR_Conhecimento2 (long NR_Conhecimento2) {
    this.NR_Conhecimento2 = NR_Conhecimento2;
  }

  public void setNR_Conhecimento3 (long NR_Conhecimento3) {
    this.NR_Conhecimento3 = NR_Conhecimento3;
  }

  public void setNR_Conhecimento4 (long NR_Conhecimento4) {
    this.NR_Conhecimento4 = NR_Conhecimento4;
  }

  public long getNR_Conhecimento4 () {
    return NR_Conhecimento4;
  }

  public long getNR_Conhecimento3 () {
    return NR_Conhecimento3;
  }

  public long getNR_Conhecimento2 () {
    return NR_Conhecimento2;
  }

  public long getNR_Conhecimento () {
    return NR_Conhecimento;
  }

  public String getNR_Liberacao () {
    return NR_Liberacao;
  }

  public void setNR_Liberacao (String NR_Liberacao) {
    this.NR_Liberacao = NR_Liberacao;
  }

  public int getNR_Peso2 () {
    return NR_Peso2;
  }

  public void setNR_Peso2 (int NR_Peso2) {
    this.NR_Peso2 = NR_Peso2;
  }

  public void setNR_Peso3 (int NR_Peso3) {
    this.NR_Peso3 = NR_Peso3;
  }

  public void setNR_Peso4 (int NR_Peso4) {
    this.NR_Peso4 = NR_Peso4;
  }

  public void setNR_Peso_Notas_Fiscais (double NR_Peso_Notas_Fiscais) {
    this.NR_Peso_Notas_Fiscais = NR_Peso_Notas_Fiscais;
  }

  public int getNR_Peso3 () {
    return NR_Peso3;
  }

  public int getNR_Peso4 () {
    return NR_Peso4;
  }

  public double getNR_Peso_Notas_Fiscais () {
    return NR_Peso_Notas_Fiscais;
  }

  public long getNR_Volumes2 () {
    return NR_Volumes2;
  }

  public long getNR_Volumes3 () {
    return NR_Volumes3;
  }

  public long getNR_Volumes4 () {
    return NR_Volumes4;
  }

  public int getOid_AIDOF () {
    return oid_AIDOF;
  }

  public long getOid_Usuario () {
    return oid_Usuario;
  }

  public String getAcao () {
    return acao;
  }

  public String getDM_Motivo_Perda () {
    return DM_Motivo_Perda;
  }

  public String getTX_Motivo_Perda () {
    return TX_Motivo_Perda;
  }

  public String getNM_Situacao_Cotacao () {
    return NM_Situacao_Cotacao;
  }

  public String getDM_Situacao_Cotacao () {

    return DM_Situacao_Cotacao;
  }

  public double getVL_Cotacao () {
    return VL_Cotacao;
  }

  public double getNR_Peso_Cubado () {
    return NR_Peso_Cubado;
  }

  public String getOID_Pessoa_Usuario () {
    return OID_Pessoa_Usuario;
  }

  public String getNM_Tipo_Coleta () {
    return NM_Tipo_Coleta;
  }

  public String getDT_Programada_Inicial () {
    return DT_Programada_Inicial;
  }

  public String getDT_Programada_Final () {
    return DT_Programada_Final;
  }

  public String getOID_Cotacao () {
    return OID_Cotacao;
  }

  public String getDM_Tipo_Coleta () {
    return DM_Tipo_Coleta;
  }

  public String getNM_Pessoa_Destinatario4 () {
    return NM_Pessoa_Destinatario4;
  }

  public String getNM_Pessoa_Destinatario3 () {
    return NM_Pessoa_Destinatario3;
  }

  public String getNM_Pessoa_Destinatario2 () {
    return NM_Pessoa_Destinatario2;
  }

  public double getVL_Mercadoria_Notas_Fiscais () {
    return VL_Mercadoria_Notas_Fiscais;
  }

  public double getVL_Total_Frete () {
    return VL_Total_Frete;
  }

  public double getVL_Total_Frete_Notas_Fiscais () {
    return VL_Total_Frete_Notas_Fiscais;
  }

  public double getVL_Custo_Notas_Fiscais () {
    return VL_Custo_Notas_Fiscais;
  }

  public String getUsuario_Stamp () {
    return Usuario_Stamp;
  }

  public double getQT_Volumes_Notas_Fiscais () {
    return QT_Volumes_Notas_Fiscais;
  }

  public int getQT_Volumes () {
    return QT_Volumes;
  }

  public int getQT_Notas_Notas_Fiscais () {
    return QT_Notas_Notas_Fiscais;
  }

  public int getQT_Notas_Fiscais () {
    return QT_Notas_Fiscais;
  }

  public int getQT_Conhecimentos_OK () {
    return QT_Conhecimentos_OK;
  }

  public int getQT_Conhecimentos_Impressos () {
    return QT_Conhecimentos_Impressos;
  }

  public int getQT_Conhecimentos_Gerados () {
    return QT_Conhecimentos_Gerados;
  }

  public String getOID_Pessoa_Pagador () {
    return OID_Pessoa_Pagador;
  }

  public String getOID_Motorista () {
    return OID_Motorista;
  }

  public String getOID_Carreta () {
    return OID_Carreta;
  }

  public void setOID_Carreta (String OID_Carreta) {
    this.OID_Carreta = OID_Carreta;
  }

  public void setOid_AIDOF (int oid_AIDOF) {
    this.oid_AIDOF = oid_AIDOF;
  }

  public void setOid_Usuario (long oid_Usuario) {
    this.oid_Usuario = oid_Usuario;
  }

  public void setAcao (String acao) {
    this.acao = acao;
  }

  public void setDM_Motivo_Perda (String DM_Motivo_Perda) {
    this.DM_Motivo_Perda = DM_Motivo_Perda;
  }

  public void setTX_Motivo_Perda (String TX_Motivo_Perda) {
    this.TX_Motivo_Perda = TX_Motivo_Perda;
  }

  public void setNM_Situacao_Cotacao (String NM_Situacao_Cotacao) {
    this.NM_Situacao_Cotacao = NM_Situacao_Cotacao;
  }

  public void setDM_Situacao_Cotacao (String DM_Situacao_Cotacao) {

    this.DM_Situacao_Cotacao = DM_Situacao_Cotacao;
  }

  public void setVL_Cotacao (double VL_Cotacao) {
    this.VL_Cotacao = VL_Cotacao;
  }

  public void setNR_Peso_Cubado (double NR_Peso_Cubado) {
    this.NR_Peso_Cubado = NR_Peso_Cubado;
  }

  public void setOID_Pessoa_Usuario (String OID_Pessoa_Usuario) {
    this.OID_Pessoa_Usuario = OID_Pessoa_Usuario;
  }

  public void setNM_Tipo_Coleta (String NM_Tipo_Coleta) {
    this.NM_Tipo_Coleta = NM_Tipo_Coleta;
  }

  public void setDT_Programada_Final (String DT_Programada_Final) {
    this.DT_Programada_Final = DT_Programada_Final;
  }

  public void setDT_Programada_Inicial (String DT_Programada_Inicial) {
    this.DT_Programada_Inicial = DT_Programada_Inicial;
  }

  public void setOID_Cotacao (String OID_Cotacao) {
    this.OID_Cotacao = OID_Cotacao;
  }

  public void setDM_Tipo_Coleta (String DM_Tipo_Coleta) {
    this.DM_Tipo_Coleta = DM_Tipo_Coleta;
  }

  public void setNM_Pessoa_Destinatario2 (String NM_Pessoa_Destinatario2) {
    this.NM_Pessoa_Destinatario2 = NM_Pessoa_Destinatario2;
  }

  public void setNM_Pessoa_Destinatario3 (String NM_Pessoa_Destinatario3) {
    this.NM_Pessoa_Destinatario3 = NM_Pessoa_Destinatario3;
  }

  public void setNM_Pessoa_Destinatario4 (String NM_Pessoa_Destinatario4) {
    this.NM_Pessoa_Destinatario4 = NM_Pessoa_Destinatario4;
  }

  public void setVL_Total_Frete_Notas_Fiscais (double
                                               VL_Total_Frete_Notas_Fiscais) {
    this.VL_Total_Frete_Notas_Fiscais = VL_Total_Frete_Notas_Fiscais;
  }

  public void setVL_Total_Frete (double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }

  public void setVL_Mercadoria_Notas_Fiscais (double
                                              VL_Mercadoria_Notas_Fiscais) {
    this.VL_Mercadoria_Notas_Fiscais = VL_Mercadoria_Notas_Fiscais;
  }

  public void setVL_Custo_Notas_Fiscais (double VL_Custo_Notas_Fiscais) {
    this.VL_Custo_Notas_Fiscais = VL_Custo_Notas_Fiscais;
  }

  public void setUsuario_Stamp (String Usuario_Stamp) {
    this.Usuario_Stamp = Usuario_Stamp;
  }

  public void setQT_Volumes_Notas_Fiscais (double QT_Volumes_Notas_Fiscais) {
    this.QT_Volumes_Notas_Fiscais = QT_Volumes_Notas_Fiscais;
  }

  public void setQT_Volumes (int QT_Volumes) {
    this.QT_Volumes = QT_Volumes;
  }

  public void setQT_Notas_Notas_Fiscais (int QT_Notas_Notas_Fiscais) {
    this.QT_Notas_Notas_Fiscais = QT_Notas_Notas_Fiscais;
  }

  public void setQT_Notas_Fiscais (int QT_Notas_Fiscais) {
    this.QT_Notas_Fiscais = QT_Notas_Fiscais;
  }

  public void setQT_Conhecimentos_OK (int QT_Conhecimentos_OK) {
    this.QT_Conhecimentos_OK = QT_Conhecimentos_OK;
  }

  public void setQT_Conhecimentos_Impressos (int QT_Conhecimentos_Impressos) {
    this.QT_Conhecimentos_Impressos = QT_Conhecimentos_Impressos;
  }

  public void setQT_Conhecimentos_Gerados (int QT_Conhecimentos_Gerados) {
    this.QT_Conhecimentos_Gerados = QT_Conhecimentos_Gerados;
  }

  public void setOID_Pessoa_Pagador (String OID_Pessoa_Pagador) {
    this.OID_Pessoa_Pagador = OID_Pessoa_Pagador;
  }

  public void setOID_Motorista (String OID_Motorista) {
    this.OID_Motorista = OID_Motorista;
  }

  public void setNR_Volumes2 (long NR_Volumes2) {
    this.NR_Volumes2 = NR_Volumes2;
  }

  public void setNR_Volumes3 (long NR_Volumes3) {
    this.NR_Volumes3 = NR_Volumes3;
  }

  public void setNR_Volumes4 (long NR_Volumes4) {
    this.NR_Volumes4 = NR_Volumes4;
  }

  public String getCD_Cidade_Destinatario () {
    return CD_Cidade_Destinatario;
  }

  public void setCD_Cidade_Destinatario (String cidade_Destinatario) {
    CD_Cidade_Destinatario = cidade_Destinatario;
  }

  public long getOID_Cidade_Destinatario () {
    return OID_Cidade_Destinatario;
  }

  public void setOID_Cidade_Destinatario (long cidade_Destinatario) {
    OID_Cidade_Destinatario = cidade_Destinatario;
  }

  public String getNM_Cidade_Unidade () {
    return NM_Cidade_Unidade;
  }

  public void setNM_Cidade_Unidade (String cidade_Unidade) {
    NM_Cidade_Unidade = cidade_Unidade;
  }

  public String getNM_Endereco_Unidade () {
    return NM_Endereco_Unidade;
  }

  public void setNM_Endereco_Unidade (String endereco_Unidade) {
    NM_Endereco_Unidade = endereco_Unidade;
  }

  public String getNM_Razao_Social_Unidade () {
    return NM_Razao_Social_Unidade;
  }

  public void setNM_Razao_Social_Unidade (String razao_Social_Unidade) {
    NM_Razao_Social_Unidade = razao_Social_Unidade;
  }

  public String getNR_CNPJ_CPF_Unidade () {
    return NR_CNPJ_CPF_Unidade;
  }

  public void setNR_CNPJ_CPF_Unidade (String unidade) {
    NR_CNPJ_CPF_Unidade = unidade;
  }

  public String getNR_Fone_Unidade () {
    return NR_Fone_Unidade;
  }

  public void setNR_Fone_Unidade (String fone_Unidade) {
    NR_Fone_Unidade = fone_Unidade;
  }

  public String getNR_Placa_Carreta () {
    return NR_Placa_Carreta;
  }

  public void setNR_Placa_Carreta (String placa_Carreta) {
    NR_Placa_Carreta = placa_Carreta;
  }

public String getNM_Motorista() {
	return NM_Motorista;
}

public void setNM_Motorista(String motorista) {
	NM_Motorista = motorista;
}

public String getNR_Placa_Carreta2() {
	return NR_Placa_Carreta2;
}

public void setNR_Placa_Carreta2(String placa_Carreta2) {
	NR_Placa_Carreta2 = placa_Carreta2;
}

public String getOID_Carreta2() {
	return OID_Carreta2;
}

public void setOID_Carreta2(String carreta2) {
	OID_Carreta2 = carreta2;
}

public long getOID_Produto() {
	return OID_Produto;
}

public void setOID_Produto(long produto) {
	OID_Produto = produto;
}

public String getDM_Impresso() {
	return DM_Impresso;
}

public void setDM_Impresso(String impressa) {
	DM_Impresso = impressa;
}

public String getNM_Situacao() {
	return NM_Situacao;
}

public void setNM_Situacao(String situacao) {
	NM_Situacao = situacao;
}

public String getNM_Pessoa_Contato() {
	return NM_Pessoa_Contato;
}

public void setNM_Pessoa_Contato(String pessoa_Contato) {
	NM_Pessoa_Contato = pessoa_Contato;
}

public long getOid_Programacao_Carga() {
	return oid_Programacao_Carga;
}

public void setOid_Programacao_Carga(long oid_Programacao_Carga) {
	this.oid_Programacao_Carga = oid_Programacao_Carga;
}

public long getNR_Ramal_Contato() {
	return NR_Ramal_Contato;
}

public void setNR_Ramal_Contato(long ramal_Contato) {
	NR_Ramal_Contato = ramal_Contato;
}

public long getNR_Ramal_Solicitante() {
	return NR_Ramal_Solicitante;
}

public void setNR_Ramal_Solicitante(long ramal_Solicitante) {
	NR_Ramal_Solicitante = ramal_Solicitante;
}
}
