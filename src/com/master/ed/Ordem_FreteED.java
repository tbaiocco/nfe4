package com.master.ed;

public class Ordem_FreteED extends MasterED{

  private long OID_Unidade;
  private long OID_Unidade_Adiantamento1;
  private long OID_Unidade_Adiantamento2;
  private long OID_Unidade_Saldo;
  private long NR_Posicao;
  private long NR_Chave;
  private int OID_Unidade_Pagamento;
  private String CD_Unidade;
  private String NM_Unidade;
  private long NR_Ordem_Frete;
  private double VL_IRRF;
  private double VL_Pedagio;
  private double VL_Taxa_Expediente;
  private double VL_Multa_Atrazo;
  private String TX_Observacao;
  private String DM_Frete;
  private long OID_Acerto;
  private double VL_Set_Senat;
  private double VL_Ordem_Frete;
  private String DT_Emissao;
  private String OID_Ordem_Frete;
  private String OID_Ordem_Principal;
  private String NM_Serie;
  private String NR_Placa;
  private String OID_Veiculo;
  private double VL_Adiantamento1;
  private double VL_Adiantamento2;
  private double VL_Saldo;
  private String DT_Adiantamento1;
  private String DT_Adiantamento2;
  private String DT_Saldo;
  private String HR_Emissao;
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
  private String DM_Tipo_Pagamento;
  private double VL_Liquido_Ordem_Frete;
  private double VL_INSS_Prestador;
  private double VL_INSS_Empresa;
  private String OID_Manifesto;
  private String OID_MIC;
  private String NR_Master;
  private String NR_Voo;
  private String HR_Voo;
  private String DT_Voo;
  private double VL_ICMS;
  private double PE_ICMS;
  private String NM_Razao_Social;
  private double VL_Total_Frete_CTRC;
  private double NR_Total_Peso_CTRC;
  private double VL_Coleta;
  private double VL_Carga;
  private double VL_Descarga;
  private double VL_Base_Adto;
  private double VL_Despesa;
  private double VL_Premio;
  private double VL_Outros;
  private double VL_Vale_Pedagio;
  private double VL_Vale_Pedagio_Empresa;
  private double VL_Total;
  private String DM_Adiantamento;
  private double VL_INSS_Pago;
  private String DM_Relatorio;
  private long OID_Empresa;
  private String NM_Empresa;
  private String DT_Master_Recuperado;
  private String DM_Situacao;
  private String HR_Master_Recuperado;
  private String NM_Roteiro1;
  private String NM_Roteiro2;
  private String NM_Roteiro3;
  private String NM_Roteiro4;
  private String NM_Roteiro5;
  private String NM_Roteiro6;
  private String NM_Roteiro7;
  private String NM_Roteiro8;
  private String NM_Roteiro9;
  private String NM_Roteiro10;
  private String DM_Check1;
  private String DM_Check2;
  private String DM_Check3;
  private String DM_Check4;
  private String DM_Check5;
  private String DM_Check6;
  private String DM_Check7;
  private String DM_Check8;
  private String DM_Check9;
  private String DM_Check10;
  private String DM_Check11;
  private String DM_Check12;
  private String DT_Ponto1;
  private String DT_Ponto2;
  private String DT_Ponto3;
  private String DT_Ponto4;
  private String DT_Ponto5;
  private String DT_Ponto6;
  private String DT_Ponto7;
  private String DT_Ponto8;
  private String HR_Ponto1;
  private String HR_Ponto2;
  private String HR_Ponto3;
  private String HR_Ponto4;
  private String HR_Ponto5;
  private String HR_Ponto6;
  private String HR_Ponto7;
  private String HR_Ponto8;
  private String NM_Ponto1;
  private String NM_Ponto2;
  private String NM_Ponto3;
  private String NM_Ponto4;
  private String NM_Ponto5;
  private String NM_Ponto6;
  private String NM_Ponto7;
  private String NM_Ponto8;
  private String TX_Observacao_Plano;
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
  private String DM_Tipo_Pessoa;
  private double VL_Total_Liquido;
  private double VL_Base_Calculo;
  private String NR_Transacao_Pedagio;
  private String NM_Situacao;
  private long NR_Ordem_Frete_Inicial;
  private long NR_Ordem_Frete_Final;
  private long OID_Usuario;
  private String DM_Saldo_Liberado;
  private String DM_Compromisso;
  private String OID_Pessoa_Pagador;
  private long OID_Lote_Fornecedor;
  private String OID_Local_Pagamento;
  private String NR_Cheque_Adto;
  private String DT_Pagamento;

  private String NM_Local_Troca;

  private long oid_Programacao_Carga;

  private String CIOT;
  private String VIAGEMID;
  private String dt_envio_cfe;
  private String dt_encerramento_cfe;
  private String dt_cancelamento_cfe;


  private String vlTarifaPamcary;
  private String qtTarifaPamcary;
  private String tpTarifaPamcary;
  private String vlTarifaPamcary_Transferencia;
  private String qtTarifaPamcary_Transferencia;
  private String tpTarifaPamcary_Transferencia;


  public void setOID_Ordem_Frete(String OID_Ordem_Frete) {
    this.OID_Ordem_Frete = OID_Ordem_Frete;
  }
  public String getOID_Ordem_Frete() {
    return OID_Ordem_Frete;
  }
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
  }
  public void setNM_Unidade(String NM_Unidade) {
    this.NM_Unidade = NM_Unidade;
  }
  public String getNM_Unidade() {
    return NM_Unidade;
  }
  public void setNR_Ordem_Frete(long NR_Ordem_Frete) {
    this.NR_Ordem_Frete = NR_Ordem_Frete;
  }
  public long getNR_Ordem_Frete() {
    return NR_Ordem_Frete;
  }
  public void setVL_Ordem_Frete(double VL_Ordem_Frete) {
    this.VL_Ordem_Frete = VL_Ordem_Frete;
  }
  public double getVL_Ordem_Frete() {
    return VL_Ordem_Frete;
  }
  public void setVL_Set_Senat(double VL_Set_Senat) {
    this.VL_Set_Senat = VL_Set_Senat;
  }
  public double getVL_Set_Senat() {
    return VL_Set_Senat;
  }
  public void setVL_IRRF(double VL_IRRF) {
    this.VL_IRRF = VL_IRRF;
  }
  public double getVL_IRRF() {
    return VL_IRRF;
  }
  public void setVL_Pedagio(double VL_Pedagio) {
    this.VL_Pedagio = VL_Pedagio;
  }
  public double getVL_Pedagio() {
    return VL_Pedagio;
  }
  public void setVL_Taxa_Expediente(double VL_Taxa_Expediente) {
    this.VL_Taxa_Expediente = VL_Taxa_Expediente;
  }
  public double getVL_Taxa_Expediente() {
    return VL_Taxa_Expediente;
  }
  public void setVL_Multa_Atrazo(double VL_Multa_Atrazo) {
    this.VL_Multa_Atrazo = VL_Multa_Atrazo;
  }
  public double getVL_Multa_Atrazo() {
    return VL_Multa_Atrazo;
  }
  public void setTX_Observacao(String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }
  public String getTX_Observacao() {
    return TX_Observacao;
  }
  public void setDM_Frete(String DM_Frete) {
    this.DM_Frete = DM_Frete;
  }
  public String getDM_Frete() {
    return DM_Frete;
  }
  public void setOID_Acerto(long OID_Acerto) {
    this.OID_Acerto = OID_Acerto;
  }
  public long getOID_Acerto() {
    return OID_Acerto;
  }
  public void setDT_Emissao(String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }
  public String getDT_Emissao() {
    return DT_Emissao;
  }
  public void setNM_Serie(String NM_Serie) {
    this.NM_Serie = NM_Serie;
  }
  public String getNM_Serie() {
    return NM_Serie;
  }
  public void setNR_Placa(String NR_Placa) {
    this.NR_Placa = NR_Placa;
  }
  public String getNR_Placa() {
    return NR_Placa;
  }
  public void setOID_Veiculo(String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }
  public String getOID_Veiculo() {
    return OID_Veiculo;
  }
  public void setVL_Adiantamento1(double VL_Adiantamento1) {
    this.VL_Adiantamento1 = VL_Adiantamento1;
  }
  public double getVL_Adiantamento1() {
    return VL_Adiantamento1;
  }
  public void setVL_Adiantamento2(double VL_Adiantamento2) {
    this.VL_Adiantamento2 = VL_Adiantamento2;
  }
  public double getVL_Adiantamento2() {
    return VL_Adiantamento2;
  }
  public void setVL_Saldo(double VL_Saldo) {
    this.VL_Saldo = VL_Saldo;
  }
  public double getVL_Saldo() {
    return VL_Saldo;
  }
  public void setDT_Adiantamento1(String DT_Adiantamento1) {
    this.DT_Adiantamento1 = DT_Adiantamento1;
  }
  public String getDT_Adiantamento1() {
    return DT_Adiantamento1;
  }
  public void setDT_Adiantamento2(String DT_Adiantamento2) {
    this.DT_Adiantamento2 = DT_Adiantamento2;
  }
  public String getDT_Adiantamento2() {
    return DT_Adiantamento2;
  }
  public void setDT_Saldo(String DT_Saldo) {
    this.DT_Saldo = DT_Saldo;
  }
  public String getDT_Saldo() {
    return DT_Saldo;
  }
  public void setHR_Emissao(String HR_Emissao) {
    this.HR_Emissao = HR_Emissao;
  }
  public String getHR_Emissao() {
    return HR_Emissao;
  }
  public void setVL_Compromisso(double VL_Compromisso) {
    this.VL_Compromisso = VL_Compromisso;
  }
  public double getVL_Compromisso() {
    return VL_Compromisso;
  }
  public void setNR_Parcela(Integer NR_Parcela) {
    this.NR_Parcela = NR_Parcela;
  }
  public Integer getNR_Parcela() {
    return NR_Parcela;
  }
  public void setDT_Vencimento(String DT_Vencimento) {
    this.DT_Vencimento = DT_Vencimento;
  }
  public String getDT_Vencimento() {
    return DT_Vencimento;
  }
  public void setOID_Pessoa(String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public String getOID_Pessoa() {
    return OID_Pessoa;
  }
  public void setDM_Impresso(String DM_Impresso) {
    this.DM_Impresso = DM_Impresso;
  }
  public String getDM_Impresso() {
    return DM_Impresso;
  }
  public void setDM_Tipo_Pedagio(String DM_Tipo_Pedagio) {
    this.DM_Tipo_Pedagio = DM_Tipo_Pedagio;
  }
  public String getDM_Tipo_Pedagio() {
    return DM_Tipo_Pedagio;
  }
  public void setDt_Emissao_Inicial(String Dt_Emissao_Inicial) {
    this.Dt_Emissao_Inicial = Dt_Emissao_Inicial;
  }
  public String getDt_Emissao_Inicial() {
    return Dt_Emissao_Inicial;
  }
  public void setDt_Emissao_Final(String Dt_Emissao_Final) {
    this.Dt_Emissao_Final = Dt_Emissao_Final;
  }
  public String getDt_Emissao_Final() {
    return Dt_Emissao_Final;
  }
  public void setDM_Lista_Conhecimento(String DM_Lista_Conhecimento) {
    this.DM_Lista_Conhecimento = DM_Lista_Conhecimento;
  }
  public String getDM_Lista_Conhecimento() {
    return DM_Lista_Conhecimento;
  }
  public void setNR_Acerto(long NR_Acerto) {
    this.NR_Acerto = NR_Acerto;
  }
  public long getNR_Acerto() {
    return NR_Acerto;
  }
  public void setVL_Descontos(double VL_Descontos) {
    this.VL_Descontos = VL_Descontos;
  }
  public double getVL_Descontos() {
    return VL_Descontos;
  }
  public void setNM_Pagamento(String NM_Pagamento) {
    this.NM_Pagamento = NM_Pagamento;
  }
  public String getNM_Pagamento() {
    return NM_Pagamento;
  }
  public void setNR_Qtde_Coleta(long NR_Qtde_Coleta) {
    this.NR_Qtde_Coleta = NR_Qtde_Coleta;
  }
  public long getNR_Qtde_Coleta() {
    return NR_Qtde_Coleta;
  }
  public void setNR_Qtde_Entrega(long NR_Qtde_Entrega) {
    this.NR_Qtde_Entrega = NR_Qtde_Entrega;
  }
  public long getNR_Qtde_Entrega() {
    return NR_Qtde_Entrega;
  }
  public void setDM_Tipo_Pagamento(String DM_Tipo_Pagamento) {
    this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
  }
  public String getDM_Tipo_Pagamento() {
    return DM_Tipo_Pagamento;
  }
  public void setVL_Liquido_Ordem_Frete(double VL_Liquido_Ordem_Frete) {
    this.VL_Liquido_Ordem_Frete = VL_Liquido_Ordem_Frete;
  }
  public double getVL_Liquido_Ordem_Frete() {
    return VL_Liquido_Ordem_Frete;
  }
  public void setVL_INSS_Prestador(double VL_INSS_Prestador) {
    this.VL_INSS_Prestador = VL_INSS_Prestador;
  }
  public double getVL_INSS_Prestador() {
    return VL_INSS_Prestador;
  }
  public void setVL_INSS_Empresa(double VL_INSS_Empresa) {
    this.VL_INSS_Empresa = VL_INSS_Empresa;
  }
  public double getVL_INSS_Empresa() {
    return VL_INSS_Empresa;
  }
  public String getOID_Manifesto() {
    return OID_Manifesto;
  }
  public void setOID_Manifesto(String OID_Manifesto) {
    this.OID_Manifesto = OID_Manifesto;
  }
  public void setNR_Master(String NR_Master) {
    this.NR_Master = NR_Master;
  }
  public String getNR_Master() {
    return NR_Master;
  }
  public void setNR_Voo(String NR_Voo) {
    this.NR_Voo = NR_Voo;
  }
  public String getNR_Voo() {
    return NR_Voo;
  }
  public void setHR_Voo(String HR_Voo) {
    this.HR_Voo = HR_Voo;
  }
  public String getHR_Voo() {
    return HR_Voo;
  }
  public void setDT_Voo(String DT_Voo) {
    this.DT_Voo = DT_Voo;
  }
  public String getDT_Voo() {
    return DT_Voo;
  }
  public void setVL_ICMS(double VL_ICMS) {
    this.VL_ICMS = VL_ICMS;
  }
  public double getVL_ICMS() {
    return VL_ICMS;
  }
  public void setPE_ICMS(double PE_ICMS) {
    this.PE_ICMS = PE_ICMS;
  }
  public double getPE_ICMS() {
    return PE_ICMS;
  }
  public void setNM_Razao_Social(String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }
  public String getNM_Razao_Social() {
    return NM_Razao_Social;
  }
  public void setVL_Total_Frete_CTRC(double VL_Total_Frete_CTRC) {
    this.VL_Total_Frete_CTRC = VL_Total_Frete_CTRC;
  }
  public double getVL_Total_Frete_CTRC() {
    return VL_Total_Frete_CTRC;
  }
  public void setNR_Total_Peso_CTRC(double NR_Total_Peso_CTRC) {
    this.NR_Total_Peso_CTRC = NR_Total_Peso_CTRC;
  }
  public double getNR_Total_Peso_CTRC() {
    return NR_Total_Peso_CTRC;
  }
  public void setVL_Coleta(double VL_Coleta) {
    this.VL_Coleta = VL_Coleta;
  }
  public double getVL_Coleta() {
    return VL_Coleta;
  }
  public void setVL_Premio(double VL_Premio) {
    this.VL_Premio = VL_Premio;
  }
  public double getVL_Premio() {
    return VL_Premio;
  }
  public void setVL_Outros(double VL_Outros) {
    this.VL_Outros = VL_Outros;
  }
  public double getVL_Outros() {
    return VL_Outros;
  }
  public void setVL_Vale_Pedagio(double VL_Vale_Pedagio) {
    this.VL_Vale_Pedagio = VL_Vale_Pedagio;
  }
  public double getVL_Vale_Pedagio() {
    return VL_Vale_Pedagio;
  }
  public void setVL_Total(double VL_Total) {
    this.VL_Total = VL_Total;
  }
  public double getVL_Total() {
    return VL_Total;
  }
  public void setDM_Adiantamento(String DM_Adiantamento) {
    this.DM_Adiantamento = DM_Adiantamento;
  }
  public String getDM_Adiantamento() {
    return DM_Adiantamento;
  }
  public void setVL_INSS_Pago(double VL_INSS_Pago) {
    this.VL_INSS_Pago = VL_INSS_Pago;
  }
  public double getVL_INSS_Pago() {
    return VL_INSS_Pago;
  }
  public void setDM_Relatorio(String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }
  public String getDM_Relatorio() {
    return DM_Relatorio;
  }
  public void setOID_Empresa(long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }
  public long getOID_Empresa() {
    return OID_Empresa;
  }
  public void setNM_Empresa(String NM_Empresa) {
    this.NM_Empresa = NM_Empresa;
  }
  public String getNM_Empresa() {
    return NM_Empresa;
  }
  public void setDT_Master_Recuperado(String DT_Master_Recuperado) {
    this.DT_Master_Recuperado = DT_Master_Recuperado;
  }
  public String getDT_Master_Recuperado() {
    return DT_Master_Recuperado;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public void setHR_Master_Recuperado(String HR_Master_Recuperado) {
    this.HR_Master_Recuperado = HR_Master_Recuperado;
  }
  public String getHR_Master_Recuperado() {
    return HR_Master_Recuperado;
  }
  public void setNM_Roteiro1(String NM_Roteiro1) {
    this.NM_Roteiro1 = NM_Roteiro1;
  }
  public String getNM_Roteiro1() {
    return NM_Roteiro1;
  }
  public void setNM_Roteiro2(String NM_Roteiro2) {
    this.NM_Roteiro2 = NM_Roteiro2;
  }
  public String getNM_Roteiro2() {
    return NM_Roteiro2;
  }
  public void setNM_Roteiro3(String NM_Roteiro3) {
    this.NM_Roteiro3 = NM_Roteiro3;
  }
  public String getNM_Roteiro3() {
    return NM_Roteiro3;
  }
  public void setNM_Roteiro4(String NM_Roteiro4) {
    this.NM_Roteiro4 = NM_Roteiro4;
  }
  public String getNM_Roteiro4() {
    return NM_Roteiro4;
  }
  public void setNM_Roteiro5(String NM_Roteiro5) {
    this.NM_Roteiro5 = NM_Roteiro5;
  }
  public String getNM_Roteiro5() {
    return NM_Roteiro5;
  }
  public void setNM_Roteiro6(String NM_Roteiro6) {
    this.NM_Roteiro6 = NM_Roteiro6;
  }
  public String getNM_Roteiro6() {
    return NM_Roteiro6;
  }
  public void setNM_Roteiro7(String NM_Roteiro7) {
    this.NM_Roteiro7 = NM_Roteiro7;
  }
  public String getNM_Roteiro7() {
    return NM_Roteiro7;
  }
  public void setNM_Roteiro8(String NM_Roteiro8) {
    this.NM_Roteiro8 = NM_Roteiro8;
  }
  public String getNM_Roteiro8() {
    return NM_Roteiro8;
  }
  public void setNM_Roteiro9(String NM_Roteiro9) {
    this.NM_Roteiro9 = NM_Roteiro9;
  }
  public String getNM_Roteiro9() {
    return NM_Roteiro9;
  }
  public void setNM_Roteiro10(String NM_Roteiro10) {
    this.NM_Roteiro10 = NM_Roteiro10;
  }
  public String getNM_Roteiro10() {
    return NM_Roteiro10;
  }
  public void setDM_Check1(String DM_Check1) {
    this.DM_Check1 = DM_Check1;
  }
  public String getDM_Check1() {
    return DM_Check1;
  }
  public void setDM_Check2(String DM_Check2) {
    this.DM_Check2 = DM_Check2;
  }
  public String getDM_Check2() {
    return DM_Check2;
  }
  public void setDM_Check3(String DM_Check3) {
    this.DM_Check3 = DM_Check3;
  }
  public String getDM_Check3() {
    return DM_Check3;
  }
  public void setDM_Check4(String DM_Check4) {
    this.DM_Check4 = DM_Check4;
  }
  public String getDM_Check4() {
    return DM_Check4;
  }
  public void setDM_Check5(String DM_Check5) {
    this.DM_Check5 = DM_Check5;
  }
  public String getDM_Check5() {
    return DM_Check5;
  }
  public void setDM_Check6(String DM_Check6) {
    this.DM_Check6 = DM_Check6;
  }
  public String getDM_Check6() {
    return DM_Check6;
  }
  public void setDM_Check7(String DM_Check7) {
    this.DM_Check7 = DM_Check7;
  }
  public String getDM_Check7() {
    return DM_Check7;
  }
  public void setDM_Check8(String DM_Check8) {
    this.DM_Check8 = DM_Check8;
  }
  public String getDM_Check8() {
    return DM_Check8;
  }
  public void setDM_Check9(String DM_Check9) {
    this.DM_Check9 = DM_Check9;
  }
  public String getDM_Check9() {
    return DM_Check9;
  }
  public void setDM_Check10(String DM_Check10) {
    this.DM_Check10 = DM_Check10;
  }
  public String getDM_Check10() {
    return DM_Check10;
  }
  public void setDM_Check11(String DM_Check11) {
    this.DM_Check11 = DM_Check11;
  }
  public String getDM_Check11() {
    return DM_Check11;
  }
  public void setDM_Check12(String DM_Check12) {
    this.DM_Check12 = DM_Check12;
  }
  public String getDM_Check12() {
    return DM_Check12;
  }
  public void setDT_Ponto1(String DT_Ponto1) {
    this.DT_Ponto1 = DT_Ponto1;
  }
  public String getDT_Ponto1() {
    return DT_Ponto1;
  }
  public void setDT_Ponto2(String DT_Ponto2) {
    this.DT_Ponto2 = DT_Ponto2;
  }
  public String getDT_Ponto2() {
    return DT_Ponto2;
  }
  public void setDT_Ponto3(String DT_Ponto3) {
    this.DT_Ponto3 = DT_Ponto3;
  }
  public String getDT_Ponto3() {
    return DT_Ponto3;
  }
  public void setDT_Ponto4(String DT_Ponto4) {
    this.DT_Ponto4 = DT_Ponto4;
  }
  public String getDT_Ponto4() {
    return DT_Ponto4;
  }
  public void setDT_Ponto5(String DT_Ponto5) {
    this.DT_Ponto5 = DT_Ponto5;
  }
  public String getDT_Ponto5() {
    return DT_Ponto5;
  }
  public void setDT_Ponto6(String DT_Ponto6) {
    this.DT_Ponto6 = DT_Ponto6;
  }
  public String getDT_Ponto6() {
    return DT_Ponto6;
  }
  public void setDT_Ponto7(String DT_Ponto7) {
    this.DT_Ponto7 = DT_Ponto7;
  }
  public String getDT_Ponto7() {
    return DT_Ponto7;
  }
  public void setDT_Ponto8(String DT_Ponto8) {
    this.DT_Ponto8 = DT_Ponto8;
  }
  public String getDT_Ponto8() {
    return DT_Ponto8;
  }
  public void setHR_Ponto1(String HR_Ponto1) {
    this.HR_Ponto1 = HR_Ponto1;
  }
  public String getHR_Ponto1() {
    return HR_Ponto1;
  }
  public void setHR_Ponto2(String HR_Ponto2) {
    this.HR_Ponto2 = HR_Ponto2;
  }
  public String getHR_Ponto2() {
    return HR_Ponto2;
  }
  public void setHR_Ponto3(String HR_Ponto3) {
    this.HR_Ponto3 = HR_Ponto3;
  }
  public String getHR_Ponto3() {
    return HR_Ponto3;
  }
  public void setHR_Ponto4(String HR_Ponto4) {
    this.HR_Ponto4 = HR_Ponto4;
  }
  public String getHR_Ponto4() {
    return HR_Ponto4;
  }
  public void setHR_Ponto5(String HR_Ponto5) {
    this.HR_Ponto5 = HR_Ponto5;
  }
  public String getHR_Ponto5() {
    return HR_Ponto5;
  }
  public void setHR_Ponto6(String HR_Ponto6) {
    this.HR_Ponto6 = HR_Ponto6;
  }
  public String getHR_Ponto6() {
    return HR_Ponto6;
  }
  public void setHR_Ponto7(String HR_Ponto7) {
    this.HR_Ponto7 = HR_Ponto7;
  }
  public String getHR_Ponto7() {
    return HR_Ponto7;
  }
  public void setHR_Ponto8(String HR_Ponto8) {
    this.HR_Ponto8 = HR_Ponto8;
  }
  public String getHR_Ponto8() {
    return HR_Ponto8;
  }
  public void setNM_Ponto1(String NM_Ponto1) {
    this.NM_Ponto1 = NM_Ponto1;
  }
  public String getNM_Ponto1() {
    return NM_Ponto1;
  }
  public void setNM_Ponto2(String NM_Ponto2) {
    this.NM_Ponto2 = NM_Ponto2;
  }
  public String getNM_Ponto2() {
    return NM_Ponto2;
  }
  public void setNM_Ponto3(String NM_Ponto3) {
    this.NM_Ponto3 = NM_Ponto3;
  }
  public String getNM_Ponto3() {
    return NM_Ponto3;
  }
  public void setNM_Ponto4(String NM_Ponto4) {
    this.NM_Ponto4 = NM_Ponto4;
  }
  public String getNM_Ponto4() {
    return NM_Ponto4;
  }
  public void setNM_Ponto5(String NM_Ponto5) {
    this.NM_Ponto5 = NM_Ponto5;
  }
  public String getNM_Ponto5() {
    return NM_Ponto5;
  }
  public void setNM_Ponto6(String NM_Ponto6) {
    this.NM_Ponto6 = NM_Ponto6;
  }
  public String getNM_Ponto6() {
    return NM_Ponto6;
  }
  public void setNM_Ponto7(String NM_Ponto7) {
    this.NM_Ponto7 = NM_Ponto7;
  }
  public String getNM_Ponto7() {
    return NM_Ponto7;
  }
  public void setNM_Ponto8(String NM_Ponto8) {
    this.NM_Ponto8 = NM_Ponto8;
  }
  public String getNM_Ponto8() {
    return NM_Ponto8;
  }
  public void setTX_Observacao_Plano(String TX_Observacao_Plano) {
    this.TX_Observacao_Plano = TX_Observacao_Plano;
  }
  public String getTX_Observacao_Plano() {
    return TX_Observacao_Plano;
  }
  public void setOID_Motorista(String OID_Motorista) {
    this.OID_Motorista = OID_Motorista;
  }
  public String getOID_Motorista() {
    return OID_Motorista;
  }
  public void setNR_Peso_Master(double NR_Peso_Master) {
    this.NR_Peso_Master = NR_Peso_Master;
  }
  public double getNR_Peso_Master() {
    return NR_Peso_Master;
  }
  public void setVL_Multa(double VL_Multa) {
    this.VL_Multa = VL_Multa;
  }
  public double getVL_Multa() {
    return VL_Multa;
  }
  public void setVL_Reembolso(double VL_Reembolso) {
    this.VL_Reembolso = VL_Reembolso;
  }
  public double getVL_Reembolso() {
    return VL_Reembolso;
  }
  public void setVL_Estadia(double VL_Estadia) {
    this.VL_Estadia = VL_Estadia;
  }
  public double getVL_Estadia() {
    return VL_Estadia;
  }
  public String getOID_Ordem_Principal() {
    return OID_Ordem_Principal;
  }
  public void setOID_Ordem_Principal(String OID_Ordem_Principal) {
    this.OID_Ordem_Principal = OID_Ordem_Principal;
  }
  public void setNR_Adiantamento(int NR_Adiantamento) {
    this.NR_Adiantamento = NR_Adiantamento;
  }
  public int getNR_Adiantamento() {
    return NR_Adiantamento;
  }

  public void setVL_Cotacao (double VL_Cotacao) {
    this.VL_Cotacao = VL_Cotacao;
  }

  public double getVL_Cotacao () {
    return VL_Cotacao;
  }

  public void setVL_Ordem_Frete_Convertida (double
                                               VL_Ordem_Frete_Convertida) {
    this.VL_Ordem_Frete_Convertida = VL_Ordem_Frete_Convertida;
  }

  public double getVL_Ordem_Frete_Convertida () {
    return VL_Ordem_Frete_Convertida;
  }

  public void setVL_Cotacao_Padrao (double VL_Cotacao_Padrao) {
    this.VL_Cotacao_Padrao = VL_Cotacao_Padrao;
  }

  public double getVL_Cotacao_Padrao () {
    return VL_Cotacao_Padrao;
  }

  public void setDM_Acerto (String DM_Acerto) {
    this.DM_Acerto = DM_Acerto;
  }

  public String getDM_Acerto () {
    return DM_Acerto;
  }
  public String getDM_Tipo_Adiantamento() {
      return this.DM_Tipo_Adiantamento;
  }
  public String getDM_Tipo_Adiantamento_Desc() {
    if ("A".equals (DM_Tipo_Adiantamento)) {
      return "Adto";
    }
    else if ("V".equals (DM_Tipo_Adiantamento)) {
      return "Vale";
    }
    else if ("I".equals (DM_Tipo_Adiantamento)) {
      return "INSS";
    }
    else if ("P".equals (DM_Tipo_Adiantamento)) {
      return "Posto";
    }
    else if ("O".equals (DM_Tipo_Adiantamento)) {
      return "Ordem Pagamento";
    }
    else if ("B".equals (DM_Tipo_Adiantamento)) {
        return "Depósito C/C";
      }
    else return "---";
  }
  public void setDM_Tipo_Adiantamento(String tipo_Adiantamento) {
      this.DM_Tipo_Adiantamento = tipo_Adiantamento;
  }

  public String getDM_Tipo_Pagamento_Adiantamento2 () {
    return DM_Tipo_Pagamento_Adiantamento2;
  }

  public String getDM_Tipo_Pagamento_Saldo () {
    return DM_Tipo_Pagamento_Saldo;
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

  public void setDM_Tipo_Adiantamento_Desc (String DM_Tipo_Adiantamento_Desc) {
    this.DM_Tipo_Adiantamento_Desc = DM_Tipo_Adiantamento_Desc;
  }

  public long getOID_Unidade_Adiantamento1 () {
    return OID_Unidade_Adiantamento1;
  }

  public long getOID_Unidade_Adiantamento2 () {
    return OID_Unidade_Adiantamento2;
  }

  public long getOID_Unidade_Saldo () {
    return OID_Unidade_Saldo;
  }

  public void setOID_Unidade_Saldo (long OID_Unidade_Saldo) {
    this.OID_Unidade_Saldo = OID_Unidade_Saldo;
  }

  public void setOID_Unidade_Adiantamento2 (long OID_Unidade_Adiantamento2) {
    this.OID_Unidade_Adiantamento2 = OID_Unidade_Adiantamento2;
  }

  public void setOID_Unidade_Adiantamento1 (long OID_Unidade_Adiantamento1) {
    this.OID_Unidade_Adiantamento1 = OID_Unidade_Adiantamento1;
  }

  public String getDM_Tipo_Pagamento_Adiantamento1 () {
    return DM_Tipo_Pagamento_Adiantamento1;
  }

  public void setOID_Fornecedor (String OID_Fornecedor) {
    this.OID_Fornecedor = OID_Fornecedor;
  }

  public String getOID_Fornecedor () {
    return OID_Fornecedor;
  }

  public int getOID_Unidade_Pagamento () {
    return OID_Unidade_Pagamento;
  }

  public void setOID_Unidade_Pagamento (int OID_Unidade_Pagamento) {
    this.OID_Unidade_Pagamento = OID_Unidade_Pagamento;
  }
	public String getOID_MIC() {
	    return OID_MIC;
	}
	public void setOID_MIC(String oid_mic) {
	    OID_MIC = oid_mic;
	}

  public void setVL_Total_Liquido (double VL_Total_Liquido) {
    this.VL_Total_Liquido = VL_Total_Liquido;
  }

  public double getVL_Total_Liquido () {
    return VL_Total_Liquido;
  }

  public void setVL_Base_Calculo (double VL_Base_Calculo) {
    this.VL_Base_Calculo = VL_Base_Calculo;
  }

  public double getVL_Base_Calculo () {
    return VL_Base_Calculo;
  }

  public void setNR_Transacao_Pedagio (String NR_Transacao_Pedagio) {
    this.NR_Transacao_Pedagio = NR_Transacao_Pedagio;
  }

  public String getNR_Transacao_Pedagio () {
    return NR_Transacao_Pedagio;
  }

  public void setNM_Situacao (String NM_Situacao) {
    this.NM_Situacao = NM_Situacao;
  }

  public String getNM_Situacao () {
    return NM_Situacao;
  }

  public void setNR_Ordem_Frete_Inicial (long NR_Ordem_Frete_Inicial) {
    this.NR_Ordem_Frete_Inicial = NR_Ordem_Frete_Inicial;
  }

  public long getNR_Ordem_Frete_Inicial () {
    return NR_Ordem_Frete_Inicial;
  }

  public void setNR_Ordem_Frete_Final (long NR_Ordem_Frete_Final) {
    this.NR_Ordem_Frete_Final = NR_Ordem_Frete_Final;
  }

  public long getNR_Ordem_Frete_Final () {
    return NR_Ordem_Frete_Final;
  }
public String getDM_Tipo_Pessoa() {
	return DM_Tipo_Pessoa;
}
public void setDM_Tipo_Pessoa(String tipo_Pessoa) {
	DM_Tipo_Pessoa = tipo_Pessoa;
}

  public void setOID_Usuario (long OID_Usuario) {
    this.OID_Usuario = OID_Usuario;
  }

  public long getOID_Usuario () {
    return OID_Usuario;
  }
public String getDM_Saldo_Liberado() {
	return DM_Saldo_Liberado;
}
public void setDM_Saldo_Liberado(String saldo_Liberado) {
	DM_Saldo_Liberado = saldo_Liberado;
}

  public void setDM_Compromisso (String DM_Compromisso) {
    this.DM_Compromisso = DM_Compromisso;
  }

  public String getDM_Compromisso () {
    return DM_Compromisso;
  }

  public void setOID_Pessoa_Pagador (String OID_Pessoa_Pagador) {
    this.OID_Pessoa_Pagador = OID_Pessoa_Pagador;
  }

  public String getOID_Pessoa_Pagador () {
    return OID_Pessoa_Pagador;
  }

  public void setOID_Lote_Fornecedor (long OID_Lote_Fornecedor) {
    this.OID_Lote_Fornecedor = OID_Lote_Fornecedor;
  }

  public long getOID_Lote_Fornecedor () {
    return OID_Lote_Fornecedor;
  }

  public void setOID_Local_Pagamento (String OID_Local_Pagamento) {

    this.OID_Local_Pagamento = OID_Local_Pagamento;
  }

  public String getOID_Local_Pagamento () {

    return OID_Local_Pagamento;
  }
public double getVL_Carga() {
	return VL_Carga;
}
public void setVL_Carga(double carga) {
	VL_Carga = carga;
}
public double getVL_Descarga() {
	return VL_Descarga;
}
public void setVL_Descarga(double descarga) {
	VL_Descarga = descarga;
}
public String getDT_Pagamento() {
	return DT_Pagamento;
}
public void setDT_Pagamento(String pagamento) {
	DT_Pagamento = pagamento;
}
public String getNR_Cheque_Adto() {
	return NR_Cheque_Adto;
}
public void setNR_Cheque_Adto(String cheque_Adto) {
	NR_Cheque_Adto = cheque_Adto;
}
public long getOid_Programacao_Carga() {
	return oid_Programacao_Carga;
}
public void setOid_Programacao_Carga(long oid_Programacao_Carga) {
	this.oid_Programacao_Carga = oid_Programacao_Carga;
}
public double getVL_Vale_Pedagio_Empresa() {
	return VL_Vale_Pedagio_Empresa;
}
public void setVL_Vale_Pedagio_Empresa(double vale_Pedagio_Empresa) {
	VL_Vale_Pedagio_Empresa = vale_Pedagio_Empresa;
}
public long getNR_Chave() {
	return NR_Chave;
}
public void setNR_Chave(long chave) {
	NR_Chave = chave;
}
public long getNR_Posicao() {
	return NR_Posicao;
}
public void setNR_Posicao(long posicao) {
	NR_Posicao = posicao;
}
public String getNM_Local_Troca() {
	return NM_Local_Troca;
}
public void setNM_Local_Troca(String local_Troca) {
	NM_Local_Troca = local_Troca;
}
public double getVL_Despesa() {
	return VL_Despesa;
}
public void setVL_Despesa(double despesa) {
	VL_Despesa = despesa;
}
public double getVL_Base_Adto() {
	return VL_Base_Adto;
}
public void setVL_Base_Adto(double base_Adto) {
	VL_Base_Adto = base_Adto;
}
public String getCIOT() {
	return CIOT;
}
public void setCIOT(String ciot) {
	CIOT = ciot;
}
public String getDt_cancelamento_cfe() {
	return dt_cancelamento_cfe;
}
public void setDt_cancelamento_cfe(String dt_cancelamento_cfe) {
	this.dt_cancelamento_cfe = dt_cancelamento_cfe;
}
public String getDt_encerramento_cfe() {
	return dt_encerramento_cfe;
}
public void setDt_encerramento_cfe(String dt_encerramento_cfe) {
	this.dt_encerramento_cfe = dt_encerramento_cfe;
}
public String getDt_envio_cfe() {
	return dt_envio_cfe;
}
public void setDt_envio_cfe(String dt_envio_cfe) {
	this.dt_envio_cfe = dt_envio_cfe;
}
public String getVIAGEMID() {
	return VIAGEMID;
}
public void setVIAGEMID(String viagemid) {
	VIAGEMID = viagemid;
}
public String getVlTarifaPamcary() {
	return vlTarifaPamcary;
}
public void setVlTarifaPamcary(String vlTarifaPamcary) {
	this.vlTarifaPamcary = vlTarifaPamcary;
}
public String getQtTarifaPamcary() {
	return qtTarifaPamcary;
}
public void setQtTarifaPamcary(String qtTarifaPamcary) {
	this.qtTarifaPamcary = qtTarifaPamcary;
}
public String getTpTarifaPamcary() {
	return tpTarifaPamcary;
}
public void setTpTarifaPamcary(String tpTarifaPamcary) {
	this.tpTarifaPamcary = tpTarifaPamcary;
}
public String getQtTarifaPamcary_Transferencia() {
	return qtTarifaPamcary_Transferencia;
}
public void setQtTarifaPamcary_Transferencia(
		String qtTarifaPamcary_Transferencia) {
	this.qtTarifaPamcary_Transferencia = qtTarifaPamcary_Transferencia;
}
public String getTpTarifaPamcary_Transferencia() {
	return tpTarifaPamcary_Transferencia;
}
public void setTpTarifaPamcary_Transferencia(
		String tpTarifaPamcary_Transferencia) {
	this.tpTarifaPamcary_Transferencia = tpTarifaPamcary_Transferencia;
}
public String getVlTarifaPamcary_Transferencia() {
	return vlTarifaPamcary_Transferencia;
}
public void setVlTarifaPamcary_Transferencia(
		String vlTarifaPamcary_Transferencia) {
	this.vlTarifaPamcary_Transferencia = vlTarifaPamcary_Transferencia;
}
}
