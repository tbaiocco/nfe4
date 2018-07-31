package com.master.ed;

public class Movimento_ConhecimentoED
    extends MasterED {

	
  private String OID_Conhecimento;
  private String DM_Tipo_Movimento;
  private String DT_Movimento_Conhecimento;
  private String HR_Movimento_Conhecimento;
  private long OID_Tipo_Movimento;
  private long OID_Usuario;
  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String OID_Pessoa_Consignatario;
  private String DT_Emissao;
  private long NR_Conhecimento;
  private long OID_Unidade;
  private String NM_Tipo_Movimento;
  private String CD_Unidade;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private String OID_Movimento_Conhecimento;
  private long NR_Movimento_Conhecimento;
  private String NM_Pessoa_Entrega;
  private double VL_Movimento;
  private String DM_Lancado_Gerado;
  private String VL_Movimento_Formatado;
  private String DM_Impresso;
  private String OID_Tabela_Frete;
  private double VL_Debito;
  private double VL_Credito;
  private double VL_Margem;
  private String PE_Margem;
  private String DT_Movimento_Inicial;
  private String DT_Movimento_Final;
  private String DM_Tipo_Rateio;
  private double VL_Rateio;
  private double VL_Despesas;
  private double VL_Previsto;
  private double VL_Realizado;
  private long OID_Lote_Fornecedor;
  private String DT_Realizado;
  private String OID_Lote;
  private String DM_Previsto_Realizado;
  private String NR_Documento;
  private String OID_Fornecedor;
  private String NM_Fornecedor;
  private String OID_Ordem_Frete;
  private String DM_Nacional_Internacional;
  private double VL_Tarifa;
  private String OID_Cotacao;
  private long NR_Cotacao;
  private String DM_Situacao;
  private String OID_Movimento_Cotacao;
  private String TX_Observacao;
  private String OID_Manifesto;
  private String NM_Tabela_Frete;

  private double VL_Total_Custo;
  private double VL_Ressarcimento;
  private double VL_Custo_Coleta;
  private double VL_Custo_Transferencia;
  private double VL_Custo_Entrega;
  private double VL_Custo_Imposto;
  private double VL_Custo_Seguro;
  private double VL_Custo_Outros;
  private double VL_Custo_Monitoramento;
  private double VL_Custo_Comunicacao;
  private double VL_Custo_Gerenciamento_Risco;
  private double VL_Custo_Comissao;
  private double VL_Custo_Financeiro;
  private double VL_Custo_Administrativo;
  private double VL_Total_Frete;
  private long NR_Postagem;
  private String DM_Origem_Lancamento;
  private String OID_Viagem;

  public String getOID_Viagem() {
	return OID_Viagem;
}

public void setOID_Viagem(String viagem) {
	OID_Viagem = viagem;
}

public void setOID_Conhecimento (String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }

  public String getOID_Conhecimento () {
    return OID_Conhecimento;
  }

  public void setDM_Tipo_Movimento (String DM_Tipo_Movimento) {
    this.DM_Tipo_Movimento = DM_Tipo_Movimento;
  }

  public String getDM_Tipo_Movimento () {
    return DM_Tipo_Movimento;
  }

  public void setOID_Movimento_Conhecimento (String OID_Movimento_Conhecimento) {
    this.OID_Movimento_Conhecimento = OID_Movimento_Conhecimento;
  }

  public String getOID_Movimento_Conhecimento () {
    return OID_Movimento_Conhecimento;
  }

  public void setDT_Movimento_Conhecimento (String DT_Movimento_Conhecimento) {
    this.DT_Movimento_Conhecimento = DT_Movimento_Conhecimento;
  }

  public String getDT_Movimento_Conhecimento () {
    return DT_Movimento_Conhecimento;
  }

  public void setHR_Movimento_Conhecimento (String HR_Movimento_Conhecimento) {
    this.HR_Movimento_Conhecimento = HR_Movimento_Conhecimento;
  }

  public String getHR_Movimento_Conhecimento () {
    return HR_Movimento_Conhecimento;
  }

  public void setOID_Tipo_Movimento (long OID_Tipo_Movimento) {
    this.OID_Tipo_Movimento = OID_Tipo_Movimento;
  }

  public long getOID_Tipo_Movimento () {
    return OID_Tipo_Movimento;
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

  public void setOID_Pessoa_Consignatario (String OID_Pessoa_Consignatario) {
    this.OID_Pessoa_Consignatario = OID_Pessoa_Consignatario;
  }

  public String getOID_Pessoa_Consignatario () {
    return OID_Pessoa_Consignatario;
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

  public void setNR_Conhecimento (long NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }

  public long getNR_Conhecimento () {
    return NR_Conhecimento;
  }

  public void setNM_Tipo_Movimento (String NM_Tipo_Movimento) {
    this.NM_Tipo_Movimento = NM_Tipo_Movimento;
  }

  public String getNM_Tipo_Movimento () {
    return NM_Tipo_Movimento;
  }

  public void setCD_Unidade (String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }

  public String getCD_Unidade () {
    return CD_Unidade;
  }

  public void setNM_Pessoa_Remetente (String NM_Pessoa_Remetente) {
    this.NM_Pessoa_Remetente = NM_Pessoa_Remetente;
  }

  public String getNM_Pessoa_Remetente () {
    return NM_Pessoa_Remetente;
  }

  public void setNM_Pessoa_Destinatario (String NM_Pessoa_Destinatario) {
    this.NM_Pessoa_Destinatario = NM_Pessoa_Destinatario;
  }

  public String getNM_Pessoa_Destinatario () {
    return NM_Pessoa_Destinatario;
  }

  public void setNR_Movimento_Conhecimento (long NR_Movimento_Conhecimento) {
    this.NR_Movimento_Conhecimento = NR_Movimento_Conhecimento;
  }

  public long getNR_Movimento_Conhecimento () {
    return NR_Movimento_Conhecimento;
  }

  public void setNM_Pessoa_Entrega (String NM_Pessoa_Entrega) {
    this.NM_Pessoa_Entrega = NM_Pessoa_Entrega;
  }

  public String getNM_Pessoa_Entrega () {
    return NM_Pessoa_Entrega;
  }

  public void setVL_Movimento (double VL_Movimento) {
    this.VL_Movimento = VL_Movimento;
  }

  public double getVL_Movimento () {
    return VL_Movimento;
  }

  public void setDM_Lancado_Gerado (String DM_Lancado_Gerado) {
    this.DM_Lancado_Gerado = DM_Lancado_Gerado;
  }

  public String getDM_Lancado_Gerado () {
    return DM_Lancado_Gerado;
  }

  public void setVL_Movimento_Formatado (String VL_Movimento_Formatado) {
    this.VL_Movimento_Formatado = VL_Movimento_Formatado;
  }

  public String getVL_Movimento_Formatado () {
    return VL_Movimento_Formatado;
  }

  public void setDM_Impresso (String DM_Impresso) {
    this.DM_Impresso = DM_Impresso;
  }

  public String getDM_Impresso () {
    return DM_Impresso;
  }

  public void setOID_Tabela_Frete (String OID_Tabela_Frete) {
    this.OID_Tabela_Frete = OID_Tabela_Frete;
  }

  public String getOID_Tabela_Frete () {
    return OID_Tabela_Frete;
  }

  public void setVL_Debito (double VL_Debito) {
    this.VL_Debito = VL_Debito;
  }

  public double getVL_Debito () {
    return VL_Debito;
  }

  public void setVL_Credito (double VL_Credito) {
    this.VL_Credito = VL_Credito;
  }

  public double getVL_Credito () {
    return VL_Credito;
  }

  public void setVL_Margem (double VL_Margem) {
    this.VL_Margem = VL_Margem;
  }

  public double getVL_Margem () {
    return VL_Margem;
  }

  public void setPE_Margem (String PE_Margem) {
    this.PE_Margem = PE_Margem;
  }

  public String getPE_Margem () {
    return PE_Margem;
  }

  public String getDM_Tipo_Rateio () {
    return DM_Tipo_Rateio;
  }

  public void setDM_Tipo_Rateio (String DM_Tipo_Rateio) {
    this.DM_Tipo_Rateio = DM_Tipo_Rateio;
  }

  public void setDT_Movimento_Final (String DT_Movimento_Final) {
    this.DT_Movimento_Final = DT_Movimento_Final;
  }

  public void setDT_Movimento_Inicial (String DT_Movimento_Inicial) {
    this.DT_Movimento_Inicial = DT_Movimento_Inicial;
  }

  public String getDT_Movimento_Final () {
    return DT_Movimento_Final;
  }

  public String getDT_Movimento_Inicial () {
    return DT_Movimento_Inicial;
  }

  public double getVL_Rateio () {
    return VL_Rateio;
  }

  public void setVL_Rateio (double VL_Rateio) {
    this.VL_Rateio = VL_Rateio;
  }

  public void setVL_Despesas (double VL_Despesas) {
    this.VL_Despesas = VL_Despesas;
  }

  public double getVL_Despesas () {
    return VL_Despesas;
  }

  public void setVL_Previsto (double VL_Previsto) {
    this.VL_Previsto = VL_Previsto;
  }

  public double getVL_Previsto () {
    return VL_Previsto;
  }

  public void setVL_Realizado (double VL_Realizado) {
    this.VL_Realizado = VL_Realizado;
  }

  public double getVL_Realizado () {
    return VL_Realizado;
  }

  public void setOID_Lote_Fornecedor (long OID_Lote_Fornecedor) {
    this.OID_Lote_Fornecedor = OID_Lote_Fornecedor;
  }

  public long getOID_Lote_Fornecedor () {
    return OID_Lote_Fornecedor;
  }

  public void setDT_Realizado (String DT_Realizado) {
    this.DT_Realizado = DT_Realizado;
  }

  public String getDT_Realizado () {
    return DT_Realizado;
  }

  public void setOID_Lote (String OID_Lote) {
    this.OID_Lote = OID_Lote;
  }

  public String getOID_Lote () {
    return OID_Lote;
  }

  public void setDM_Previsto_Realizado (String DM_Previsto_Realizado) {
    this.DM_Previsto_Realizado = DM_Previsto_Realizado;
  }

  public String getDM_Previsto_Realizado () {
    return DM_Previsto_Realizado;
  }

  public void setNR_Documento (String NR_Documento) {
    this.NR_Documento = NR_Documento;
  }

  public String getNR_Documento () {
    return NR_Documento;
  }

  public void setOID_Fornecedor (String OID_Fornecedor) {
    this.OID_Fornecedor = OID_Fornecedor;
  }

  public String getOID_Fornecedor () {
    return OID_Fornecedor;
  }

  public void setNM_Fornecedor (String NM_Fornecedor) {
    this.NM_Fornecedor = NM_Fornecedor;
  }

  public String getNM_Fornecedor () {
    return NM_Fornecedor;
  }

  public void setOID_Ordem_Frete (String OID_Ordem_Frete) {
    this.OID_Ordem_Frete = OID_Ordem_Frete;
  }

  public String getOID_Ordem_Frete () {
    return OID_Ordem_Frete;
  }

  public String getDM_Nacional_Internacional () {
    return this.DM_Nacional_Internacional;
  }

  public void setDM_Nacional_Internacional (String nacional_Internacional) {
    this.DM_Nacional_Internacional = nacional_Internacional;
  }

  public void setVL_Tarifa (double VL_Tarifa) {
    this.VL_Tarifa = VL_Tarifa;
  }

  public double getVL_Tarifa () {
    return VL_Tarifa;
  }

  public void setOID_Cotacao (String OID_Cotacao) {
    this.OID_Cotacao = OID_Cotacao;
  }

  public String getOID_Cotacao () {
    return OID_Cotacao;
  }

  public void setNR_Cotacao (long NR_Cotacao) {
    this.NR_Cotacao = NR_Cotacao;
  }

  public long getNR_Cotacao () {
    return NR_Cotacao;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setOID_Movimento_Cotacao (String OID_Movimento_Cotacao) {
    this.OID_Movimento_Cotacao = OID_Movimento_Cotacao;
  }

  public String getOID_Movimento_Cotacao () {
    return OID_Movimento_Cotacao;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setOID_Manifesto (String OID_Manifesto) {

    this.OID_Manifesto = OID_Manifesto;
  }

  public String getOID_Manifesto () {

    return OID_Manifesto;
  }

  public void setNM_Tabela_Frete (String NM_Tabela_Frete) {
    this.NM_Tabela_Frete = NM_Tabela_Frete;
  }

  public String getNM_Tabela_Frete () {
    return NM_Tabela_Frete;
  }

  public void setVL_Total_Frete (double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }

  public double getVL_Total_Frete () {
    return VL_Total_Frete;
  }

  public double getVL_Custo_Administrativo () {
    return VL_Custo_Administrativo;
  }

  public double getVL_Custo_Coleta () {
    return VL_Custo_Coleta;
  }

  public double getVL_Custo_Comissao () {
    return VL_Custo_Comissao;
  }

  public double getVL_Custo_Comunicacao () {
    return VL_Custo_Comunicacao;
  }

  public double getVL_Custo_Entrega () {
    return VL_Custo_Entrega;
  }

  public double getVL_Custo_Financeiro () {
    return VL_Custo_Financeiro;
  }

  public double getVL_Custo_Gerenciamento_Risco () {
    return VL_Custo_Gerenciamento_Risco;
  }

  public double getVL_Custo_Imposto () {
    return VL_Custo_Imposto;
  }

  public double getVL_Custo_Monitoramento () {
    return VL_Custo_Monitoramento;
  }

  public double getVL_Custo_Outros () {
    return VL_Custo_Outros;
  }

  public double getVL_Custo_Seguro () {
    return VL_Custo_Seguro;
  }

  public double getVL_Custo_Transferencia () {
    return VL_Custo_Transferencia;
  }

  public void setVL_Custo_Transferencia (double VL_Custo_Transferencia) {
    this.VL_Custo_Transferencia = VL_Custo_Transferencia;
  }

  public void setVL_Custo_Seguro (double VL_Custo_Seguro) {
    this.VL_Custo_Seguro = VL_Custo_Seguro;
  }

  public void setVL_Custo_Outros (double VL_Custo_Outros) {
    this.VL_Custo_Outros = VL_Custo_Outros;
  }

  public void setVL_Custo_Monitoramento (double VL_Custo_Monitoramento) {
    this.VL_Custo_Monitoramento = VL_Custo_Monitoramento;
  }

  public void setVL_Custo_Imposto (double VL_Custo_Imposto) {
    this.VL_Custo_Imposto = VL_Custo_Imposto;
  }

  public void setVL_Custo_Gerenciamento_Risco (double VL_Custo_Gerenciamento_Risco) {
    this.VL_Custo_Gerenciamento_Risco = VL_Custo_Gerenciamento_Risco;
  }

  public void setVL_Custo_Financeiro (double VL_Custo_Financeiro) {
    this.VL_Custo_Financeiro = VL_Custo_Financeiro;
  }

  public void setVL_Custo_Entrega (double VL_Custo_Entrega) {
    this.VL_Custo_Entrega = VL_Custo_Entrega;
  }

  public void setVL_Custo_Comunicacao (double VL_Custo_Comunicacao) {
    this.VL_Custo_Comunicacao = VL_Custo_Comunicacao;
  }

  public void setVL_Custo_Comissao (double VL_Custo_Comissao) {
    this.VL_Custo_Comissao = VL_Custo_Comissao;
  }

  public void setVL_Custo_Coleta (double VL_Custo_Coleta) {
    this.VL_Custo_Coleta = VL_Custo_Coleta;
  }

  public void setVL_Custo_Administrativo (double VL_Custo_Administrativo) {
    this.VL_Custo_Administrativo = VL_Custo_Administrativo;
  }

  public double getVL_Ressarcimento () {
    return VL_Ressarcimento;
  }

  public double getVL_Total_Custo () {
    return VL_Total_Custo;
  }

  public void setVL_Total_Custo (double VL_Total_Custo) {
    this.VL_Total_Custo = VL_Total_Custo;
  }

  public void setVL_Ressarcimento (double VL_Ressarcimento) {
    this.VL_Ressarcimento = VL_Ressarcimento;
  }

  public void setNR_Postagem (long NR_Postagem) {
    this.NR_Postagem = NR_Postagem;
  }

  public long getNR_Postagem () {
    return NR_Postagem;
  }

  public void setDM_Origem_Lancamento (String DM_Origem_Lancamento) {
    this.DM_Origem_Lancamento = DM_Origem_Lancamento;
  }

  public String getDM_Origem_Lancamento () {
    return DM_Origem_Lancamento;
  }

public long getOID_Usuario() {
	return OID_Usuario;
}

public void setOID_Usuario(long usuario) {
	OID_Usuario = usuario;
}

}
