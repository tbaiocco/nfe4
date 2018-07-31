package com.master.ed;

public class FaturamentoED extends MasterED{


  private String NM_Serie;
  private String DM_Tipo_Pagador;
  private String DT_Emissao;
  private String DT_Emissao_Ctrc;
  private String OID_Pessoa;
  private long OID_Unidade;
  private long OID_Grupo_Economico;
  private String OID_Vendedor;
  private String OID_Tabela_Frete;
  private String OID_Pessoa_Pagador;
  private long OID_Produto;
  private String CD_Unidade;
  private double VL_Peso;
  private double VL_Peso_Cubado;
  private double VL_Total_Frete;
  private double VL_ICMS;
  private long NR_Fatura;
  private long NR_Parcela;
  private String OID_Fatura;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String OID_Conhecimento;
  private long NR_Conhecimento;
  private double VL_Duplicata;
  private String DT_Vencimento;
  private String DT_Vencimento_Minimo;
  private String DT_Vencimento_Informado;
  private double VL_Taxa_Cobranca;
  private double VL_Desconto_Faturamento;
  private double VL_Taxa_Refaturamento;
  private double VL_Saldo;
  private long oid_Tipo_Documento;
  private long oid_Carteira;
  private int NR_Dias_Vencimento;
  private double VL_Juro_Mora_Dia;
  private double VL_Multa;
  private long NR_Conhecimento_Inicial;
  private long NR_Conhecimento_Final;
  private long OID_Carteira_Informada;
  private double VL_Fatura;
  private long OID_Unidade_Faturamento;
  private String DM_Tipo_Faturamento;
  private String DM_Tipo_Conhecimento;
  private long OID_Unidade_CTRC;
  private String NM_Lote_Faturamento;
  private String DM_Tipo_Documento;
  private String DM_Tipo_Cobrancao;
  private long oid_Lote_Faturamento;
  private String DM_Tipo_Cobranca;
  private String DM_Tipo_Transporte;
  private long OID_Empresa;

  private String DM_Baixado;
  private String NM_Praca_Pagamento;
  private long OID_Centro_Custo;
  private String OID_Pessoa_Destinatario;
  private String OID_Pessoa_Remetente;

  private String CD_Rota_Entrega;
  private String DM_Lista;
  private String NR_Conhecimento_Sequencia;
  private String DM_Compr_Entrega_Fatura;
  private String DM_Relatorio;


  public String getDM_Relatorio() {
	return DM_Relatorio;
}
public void setDM_Relatorio(String relatorio) {
	DM_Relatorio = relatorio;
}
public String getDM_Compr_Entrega_Fatura() {
	return DM_Compr_Entrega_Fatura;
}
public void setDM_Compr_Entrega_Fatura(String compr_Entrega_Fatura) {
	DM_Compr_Entrega_Fatura = compr_Entrega_Fatura;
}
  public String getDM_Tipo_Pagador () {
    return DM_Tipo_Pagador;
  }

  public void setDM_Tipo_Pagador (String tipo_Pagador) {
    DM_Tipo_Pagador = tipo_Pagador;
  }

  public void setNM_Serie (String NM_Serie) {
    this.NM_Serie = NM_Serie;
  }

  public String getNM_Serie () {
    return NM_Serie;
  }

  public void setDT_Emissao (String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }

  public String getDT_Emissao () {
    return DT_Emissao;
  }

  public void setOID_Pessoa (String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }

  public void setOID_Unidade (long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }

  public long getOID_Unidade () {
    return OID_Unidade;
  }

  public void setOID_Vendedor (String OID_Vendedor) {
    this.OID_Vendedor = OID_Vendedor;
  }

  public String getOID_Vendedor () {
    return OID_Vendedor;
  }

  public void setOID_Tabela_Frete (String OID_Tabela_Frete) {
    this.OID_Tabela_Frete = OID_Tabela_Frete;
  }

  public String getOID_Tabela_Frete () {
    return OID_Tabela_Frete;
  }

  public void setOID_Pessoa_Pagador (String OID_Pessoa_Pagador) {
    this.OID_Pessoa_Pagador = OID_Pessoa_Pagador;
  }

  public String getOID_Pessoa_Pagador () {
    return OID_Pessoa_Pagador;
  }

  public void setOID_Produto (long OID_Produto) {
    this.OID_Produto = OID_Produto;
  }

  public long getOID_Produto () {
    return OID_Produto;
  }

  public void setCD_Unidade (String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }

  public String getCD_Unidade () {
    return CD_Unidade;
  }

  public void setVL_Peso (double VL_Peso) {
    this.VL_Peso = VL_Peso;
  }

  public double getVL_Peso () {
    return VL_Peso;
  }

  public void setVL_Peso_Cubado (double VL_Peso_Cubado) {
    this.VL_Peso_Cubado = VL_Peso_Cubado;
  }

  public double getVL_Peso_Cubado () {
    return VL_Peso_Cubado;
  }

  public void setVL_Total_Frete (double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }

  public double getVL_Total_Frete () {
    return VL_Total_Frete;
  }

  public void setVL_ICMS (double VL_ICMS) {
    this.VL_ICMS = VL_ICMS;
  }

  public double getVL_ICMS () {
    return VL_ICMS;
  }

  public void setNR_Fatura (long NR_Fatura) {
    this.NR_Fatura = NR_Fatura;
  }

  public long getNR_Fatura () {
    return NR_Fatura;
  }

  public void setOID_Fatura (String OID_Fatura) {
    this.OID_Fatura = OID_Fatura;
  }

  public String getOID_Fatura () {
    return OID_Fatura;
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

  public void setOID_Conhecimento (String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }

  public String getOID_Conhecimento () {
    return OID_Conhecimento;
  }

  public void setNR_Conhecimento (long NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }

  public long getNR_Conhecimento () {
    return NR_Conhecimento;
  }

  public void setVL_Duplicata (double VL_Duplicata) {
    this.VL_Duplicata = VL_Duplicata;
  }

  public double getVL_Duplicata () {
    return VL_Duplicata;
  }

  public void setDT_Vencimento (String DT_Vencimento) {
    this.DT_Vencimento = DT_Vencimento;
  }

  public String getDT_Vencimento () {
    return DT_Vencimento;
  }

  public void setVL_Taxa_Cobranca (double VL_Taxa_Cobranca) {
    this.VL_Taxa_Cobranca = VL_Taxa_Cobranca;
  }

  public double getVL_Taxa_Cobranca () {
    return VL_Taxa_Cobranca;
  }

  public void setVL_Desconto_Faturamento (double VL_Desconto_Faturamento) {
    this.VL_Desconto_Faturamento = VL_Desconto_Faturamento;
  }

  public double getVL_Desconto_Faturamento () {
    return VL_Desconto_Faturamento;
  }

  public void setVL_Taxa_Refaturamento (double VL_Taxa_Refaturamento) {
    this.VL_Taxa_Refaturamento = VL_Taxa_Refaturamento;
  }

  public double getVL_Taxa_Refaturamento () {
    return VL_Taxa_Refaturamento;
  }

  public void setVL_Saldo (double VL_Saldo) {
    this.VL_Saldo = VL_Saldo;
  }

  public double getVL_Saldo () {
    return VL_Saldo;
  }

  public void setOid_Tipo_Documento (long oid_Tipo_Documento) {
    this.oid_Tipo_Documento = oid_Tipo_Documento;
  }

  public long getOid_Tipo_Documento () {
    return oid_Tipo_Documento;
  }

  public void setOid_Carteira (long oid_Carteira) {
    this.oid_Carteira = oid_Carteira;
  }

  public void setOid_Lote_Faturamento (long oid_Lote_Faturamento) {
    this.oid_Lote_Faturamento = oid_Lote_Faturamento;
  }

  public void setOID_Grupo_Economico (long OID_Grupo_Economico) {
    this.OID_Grupo_Economico = OID_Grupo_Economico;
  }

  public void setNR_Conhecimento_Sequencia (String NR_Conhecimento_Sequencia) {
    this.NR_Conhecimento_Sequencia = NR_Conhecimento_Sequencia;
  }

  public void setDM_Lista (String DM_Lista) {
    this.DM_Lista = DM_Lista;
  }

  public void setOID_Pessoa_Destinatario (String OID_Pessoa_Destinatario) {
    this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
  }

  public void setOID_Centro_Custo (long OID_Centro_Custo) {
    this.OID_Centro_Custo = OID_Centro_Custo;
  }

  public void setOID_Empresa (long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }

  public void setDM_Tipo_Transporte (String DM_Tipo_Transporte) {
    this.DM_Tipo_Transporte = DM_Tipo_Transporte;
  }

  public void setDM_Tipo_Cobranca (String DM_Tipo_Cobranca) {
    this.DM_Tipo_Cobranca = DM_Tipo_Cobranca;
  }

  public void setDM_Tipo_Documento (String DM_Tipo_Documento) {
    this.DM_Tipo_Documento = DM_Tipo_Documento;
  }

  public long getOid_Carteira () {
    return oid_Carteira;
  }

  public long getOid_Lote_Faturamento () {
    return oid_Lote_Faturamento;
  }

  public long getOID_Grupo_Economico () {
    return OID_Grupo_Economico;
  }

  public String getNR_Conhecimento_Sequencia () {
    return NR_Conhecimento_Sequencia;
  }

  public String getDM_Lista () {
    return DM_Lista;
  }

  public String getOID_Pessoa_Destinatario () {
    return OID_Pessoa_Destinatario;
  }

  public long getOID_Centro_Custo () {
    return OID_Centro_Custo;
  }

  public long getOID_Empresa () {
    return OID_Empresa;
  }

  public String getDM_Tipo_Transporte () {
    return DM_Tipo_Transporte;
  }

  public String getDM_Tipo_Cobranca () {
    return DM_Tipo_Cobranca;
  }

  public String getDM_Tipo_Documento () {
    return DM_Tipo_Documento;
  }

  public void setNR_Dias_Vencimento (int NR_Dias_Vencimento) {
    this.NR_Dias_Vencimento = NR_Dias_Vencimento;
  }

  public int getNR_Dias_Vencimento () {
    return NR_Dias_Vencimento;
  }

  public void setVL_Juro_Mora_Dia (double VL_Juro_Mora_Dia) {
    this.VL_Juro_Mora_Dia = VL_Juro_Mora_Dia;
  }

  public double getVL_Juro_Mora_Dia () {
    return VL_Juro_Mora_Dia;
  }

  public void setVL_Multa (double VL_Multa) {
    this.VL_Multa = VL_Multa;
  }

  public double getVL_Multa () {
    return VL_Multa;
  }

  public void setNR_Conhecimento_Inicial (long NR_Conhecimento_Inicial) {
    this.NR_Conhecimento_Inicial = NR_Conhecimento_Inicial;
  }

  public long getNR_Conhecimento_Inicial () {
    return NR_Conhecimento_Inicial;
  }

  public void setNR_Conhecimento_Final (long NR_Conhecimento_Final) {
    this.NR_Conhecimento_Final = NR_Conhecimento_Final;
  }

  public long getNR_Conhecimento_Final () {
    return NR_Conhecimento_Final;
  }

  public void setOID_Carteira_Informada (long OID_Carteira_Informada) {
    this.OID_Carteira_Informada = OID_Carteira_Informada;
  }

  public long getOID_Carteira_Informada () {
    return OID_Carteira_Informada;
  }

  public void setVL_Fatura (double VL_Fatura) {
    this.VL_Fatura = VL_Fatura;
  }

  public double getVL_Fatura () {
    return VL_Fatura;
  }

  public void setOID_Unidade_Faturamento (long OID_Unidade_Faturamento) {
    this.OID_Unidade_Faturamento = OID_Unidade_Faturamento;
  }

  public long getOID_Unidade_Faturamento () {
    return OID_Unidade_Faturamento;
  }

  public void setDM_Tipo_Faturamento (String DM_Tipo_Faturamento) {
    this.DM_Tipo_Faturamento = DM_Tipo_Faturamento;
  }

  public String getDM_Tipo_Faturamento () {
    return DM_Tipo_Faturamento;
  }

  public void setDM_Tipo_Conhecimento (String DM_Tipo_Conhecimento) {
    this.DM_Tipo_Conhecimento = DM_Tipo_Conhecimento;
  }

  public String getDM_Tipo_Conhecimento () {
    return DM_Tipo_Conhecimento;
  }

  public void setOID_Unidade_CTRC (long OID_Unidade_CTRC) {
    this.OID_Unidade_CTRC = OID_Unidade_CTRC;
  }

  public long getOID_Unidade_CTRC () {
    return OID_Unidade_CTRC;
  }

  public String getDT_Vencimento_Minimo () {
    return DT_Vencimento_Minimo;
  }

  public void setDT_Vencimento_Minimo (String DT_Vencimento_Minimo) {
    this.DT_Vencimento_Minimo = DT_Vencimento_Minimo;
  }

  public String getNM_Lote_Faturamento () {
    return NM_Lote_Faturamento;
  }

  public void setNM_Lote_Faturamento (String NM_Lote_Faturamento) {
    this.NM_Lote_Faturamento = NM_Lote_Faturamento;
  }

  public String getDM_Tipo_Cobrancao () {
    return DM_Tipo_Cobrancao;
  }

  public void setDM_Tipo_Cobrancao (String tipo_Cobrancao) {
    DM_Tipo_Cobrancao = tipo_Cobrancao;
  }

  public String getNM_Praca_Pagamento () {
    return NM_Praca_Pagamento;
  }

  public void setNM_Praca_Pagamento (String praca_Pagamento) {
    NM_Praca_Pagamento = praca_Pagamento;
  }

  public String getCD_Rota_Entrega () {
    return CD_Rota_Entrega;
  }

  public void setCD_Rota_Entrega (String rota_Entrega) {
    CD_Rota_Entrega = rota_Entrega;
  }
public String getOID_Pessoa_Remetente() {
	return OID_Pessoa_Remetente;
}
public void setOID_Pessoa_Remetente(String pessoa_Remetente) {
	OID_Pessoa_Remetente = pessoa_Remetente;
}
public long getNR_Parcela() {
	return NR_Parcela;
}
public void setNR_Parcela(long parcela) {
	NR_Parcela = parcela;
}
public String getDT_Emissao_Ctrc() {
	return DT_Emissao_Ctrc;
}
public void setDT_Emissao_Ctrc(String emissao_Ctrc) {
	DT_Emissao_Ctrc = emissao_Ctrc;
}
public String getDT_Vencimento_Informado() {
	return DT_Vencimento_Informado;
}
public void setDT_Vencimento_Informado(String vencimento_Informado) {
	DT_Vencimento_Informado = vencimento_Informado;
}
public String getDM_Baixado() {
	return DM_Baixado;
}
public void setDM_Baixado(String baixado) {
	DM_Baixado = baixado;
}
}
