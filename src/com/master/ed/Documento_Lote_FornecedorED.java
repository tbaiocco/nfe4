package com.master.ed;

public class Documento_Lote_FornecedorED extends MasterED{

  private String OID_Conhecimento;
  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String DT_Emissao;
  private long NR_Conhecimento;
  private long OID_Unidade;
  private String CD_Unidade;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private long NR_Lote_Fornecedor;
  private String OID_Documento_Lote_Fornecedor;
  private double VL_Total_Frete;
  private String NM_Cidade_Destino;
  private String DT_Previsao_Chegada;
  private String HR_Previsao_Chegada;
  private long NR_Conhecimento_Inicial;
  private long NR_Conhecimento_Final;
  private long NR_Postagem_Inicial;
  private long NR_Postagem_Final;
  private int OID_Subregiao;
  private long OID_Unidade_Entregadora;
  private String OID_Pessoa_Entregadora;
  private String DT_Vencimento;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private long OID_Cidade;
  private long OID_Tipo_Movimento;
  private String NM_Tipo_Movimento;
  private String CD_Tipo_Movimento;
  private long OID_Lote_Fornecedor;
  private String TX_Observacao;
  private double VL_Cobrado;
  private String NR_Documento;
  private String DM_Tipo_Documento;
  private double VL_Previsto;
  private double VL_Tabela;
  private String OID_Ordem_Frete;
  private String NR_Master;
  private String NR_Ordem_Frete;
  private String DM_Situacao;
  private long OID_Modal;
  private long OID_Cidade_Destino;
  private String NR_Fatura_Postagem;
  private long NR_Postagem;
  private String DM_Atualiza1;
  private String DM_Atualiza2;
  private String oid_Movimento_Conhecimento;
  private double VL_Movimento;
  private String OID_Manifesto;
  private String DM_Rateio;
  private String DM_Criterio;
  private String NR_Fatura_Master;
  private String OID_Tabela_Frete;

  public void setOID_Conhecimento(String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }
  public String getOID_Conhecimento() {
    return OID_Conhecimento;
  }
  public void setOID_Documento_Lote_Fornecedor(String OID_Documento_Lote_Fornecedor) {
    this.OID_Documento_Lote_Fornecedor = OID_Documento_Lote_Fornecedor;
  }
  public String getOID_Documento_Lote_Fornecedor() {
    return OID_Documento_Lote_Fornecedor;
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
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  public void setDT_Emissao(String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }
  public String getDT_Emissao() {
    return DT_Emissao;
  }
  public void setNR_Conhecimento(long NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }
  public long getNR_Conhecimento() {
    return NR_Conhecimento;
  }
  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
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
  public void setOID_Lote_Fornecedor(long OID_Lote_Fornecedor) {
    this.OID_Lote_Fornecedor = OID_Lote_Fornecedor;
  }
  public long getOID_Lote_Fornecedor() {
    return OID_Lote_Fornecedor;
  }
  public void setNR_Lote_Fornecedor(long NR_Lote_Fornecedor) {
    this.NR_Lote_Fornecedor = NR_Lote_Fornecedor;
  }
  public long getNR_Lote_Fornecedor() {
    return NR_Lote_Fornecedor;
  }
  public void setVL_Total_Frete(double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }
  public double getVL_Total_Frete() {
    return VL_Total_Frete;
  }
  public void setNR_Conhecimento_Inicial(long NR_Conhecimento_Inicial) {
    this.NR_Conhecimento_Inicial = NR_Conhecimento_Inicial;
  }
  public long getNR_Conhecimento_Inicial() {
    return NR_Conhecimento_Inicial;
  }
  public void setNR_Conhecimento_Final(long NR_Conhecimento_Final) {
    this.NR_Conhecimento_Final = NR_Conhecimento_Final;
  }
  public long getNR_Conhecimento_Final() {
    return NR_Conhecimento_Final;
  }
  public void setOID_Subregiao(int OID_Subregiao) {
    this.OID_Subregiao = OID_Subregiao;
  }
  public int getOID_Subregiao() {
    return OID_Subregiao;
  }
  public void setOID_Unidade_Entregadora(long OID_Unidade_Entregadora) {
    this.OID_Unidade_Entregadora = OID_Unidade_Entregadora;
  }
  public long getOID_Unidade_Entregadora() {
    return OID_Unidade_Entregadora;
  }
  public void setOID_Pessoa_Entregadora(String OID_Pessoa_Entregadora) {
    this.OID_Pessoa_Entregadora = OID_Pessoa_Entregadora;
  }
  public String getOID_Pessoa_Entregadora() {
    return OID_Pessoa_Entregadora;
  }
  public void setDT_Vencimento(String DT_Vencimento) {
    this.DT_Vencimento = DT_Vencimento;
  }
  public String getDT_Vencimento() {
    return DT_Vencimento;
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
  public void setOID_Tipo_Movimento(long OID_Tipo_Movimento) {
    this.OID_Tipo_Movimento = OID_Tipo_Movimento;
  }
  public long getOID_Tipo_Movimento() {
    return OID_Tipo_Movimento;
  }
  public void setNM_Tipo_Movimento(String NM_Tipo_Movimento) {
    this.NM_Tipo_Movimento = NM_Tipo_Movimento;
  }
  public String getNM_Tipo_Movimento() {
    return NM_Tipo_Movimento;
  }
  public void setCD_Tipo_Movimento(String CD_Tipo_Movimento) {
    this.CD_Tipo_Movimento = CD_Tipo_Movimento;
  }
  public String getCD_Tipo_Movimento() {
    return CD_Tipo_Movimento;
  }
  public void setTX_Observacao(String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }
  public String getTX_Observacao() {
    return TX_Observacao;
  }
  public void setVL_Cobrado(double VL_Cobrado) {
    this.VL_Cobrado = VL_Cobrado;
  }
  public double getVL_Cobrado() {
    return VL_Cobrado;
  }
  public void setNR_Documento(String NR_Documento) {
    this.NR_Documento = NR_Documento;
  }
  public String getNR_Documento() {
    return NR_Documento;
  }
  public void setDM_Tipo_Documento(String DM_Tipo_Documento) {
    this.DM_Tipo_Documento = DM_Tipo_Documento;
  }
  public String getDM_Tipo_Documento() {
    return DM_Tipo_Documento;
  }
  public void setVL_Previsto(double VL_Previsto) {
    this.VL_Previsto = VL_Previsto;
  }
  public double getVL_Previsto() {
    return VL_Previsto;
  }
  public void setOID_Ordem_Frete(String OID_Ordem_Frete) {
    this.OID_Ordem_Frete = OID_Ordem_Frete;
  }
  public String getOID_Ordem_Frete() {
    return OID_Ordem_Frete;
  }
  public void setNR_Master(String NR_Master) {
    this.NR_Master = NR_Master;
  }
  public String getNR_Master() {
    return NR_Master;
  }

  public void setNR_Ordem_Frete (String NR_Ordem_Frete) {
    this.NR_Ordem_Frete = NR_Ordem_Frete;
  }

  public String getNR_Ordem_Frete () {
    return NR_Ordem_Frete;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public long getOID_Modal () {
    return OID_Modal;
  }

  public void setOID_Modal (long OID_Modal) {
    this.OID_Modal = OID_Modal;
  }

  public void setOID_Cidade_Destino (long OID_Cidade_Destino) {
    this.OID_Cidade_Destino = OID_Cidade_Destino;
  }

  public long getOID_Cidade_Destino () {
    return OID_Cidade_Destino;
  }

  public long getNR_Postagem_Final () {
    return NR_Postagem_Final;
  }

  public long getNR_Postagem_Inicial () {
    return NR_Postagem_Inicial;
  }

  public void setNR_Postagem_Final (long NR_Postagem_Final) {
    this.NR_Postagem_Final = NR_Postagem_Final;
  }

  public void setNR_Postagem_Inicial (long NR_Postagem_Inicial) {
    this.NR_Postagem_Inicial = NR_Postagem_Inicial;
  }

  public void setNR_Fatura_Postagem (String NR_Fatura_Postagem) {

    this.NR_Fatura_Postagem = NR_Fatura_Postagem;
  }

  public String getNR_Fatura_Postagem () {

    return NR_Fatura_Postagem;
  }

  public void setNR_Postagem (long NR_Postagem) {
    this.NR_Postagem = NR_Postagem;
  }

  public long getNR_Postagem () {
    return NR_Postagem;
  }

  public void setDM_Atualiza1 (String DM_Atualiza1) {

    this.DM_Atualiza1 = DM_Atualiza1;
  }

  public String getDM_Atualiza1 () {

    return DM_Atualiza1;
  }

  public void setDM_Atualiza2 (String DM_Atualiza2) {
    this.DM_Atualiza2 = DM_Atualiza2;
  }

  public String getDM_Atualiza2 () {
    return DM_Atualiza2;
  }

  public void setOid_Movimento_Conhecimento (String oid_Movimento_Conhecimento) {
    this.oid_Movimento_Conhecimento = oid_Movimento_Conhecimento;
  }

  public void setNR_Fatura_Master (String NR_Fatura_Master) {
    this.NR_Fatura_Master = NR_Fatura_Master;
  }

  public void setDT_Previsao_Chegada (String DT_Previsao_Chegada) {
    this.DT_Previsao_Chegada = DT_Previsao_Chegada;
  }

  public void setHR_Previsao_Chegada (String HR_Previsao_Chegada) {
    this.HR_Previsao_Chegada = HR_Previsao_Chegada;
  }

  public void setNM_Cidade_Destino (String NM_Cidade_Destino) {
    this.NM_Cidade_Destino = NM_Cidade_Destino;
  }

  public void setDM_Criterio (String DM_Criterio) {
    this.DM_Criterio = DM_Criterio;
  }

  public void setDM_Rateio (String DM_Rateio) {
    this.DM_Rateio = DM_Rateio;
  }

  public void setOID_Manifesto (String OID_Manifesto) {
    this.OID_Manifesto = OID_Manifesto;
  }

  public void setVL_Movimento (double VL_Movimento) {
    this.VL_Movimento = VL_Movimento;
  }

  public String getOid_Movimento_Conhecimento () {
    return oid_Movimento_Conhecimento;
  }

  public String getNR_Fatura_Master () {
    return NR_Fatura_Master;
  }

  public String getNM_Cidade_Destino () {
    return NM_Cidade_Destino;
  }

  public String getHR_Previsao_Chegada () {
    return HR_Previsao_Chegada;
  }

  public String getDT_Previsao_Chegada () {
    return DT_Previsao_Chegada;
  }

  public String getDM_Criterio () {
    return DM_Criterio;
  }

  public String getDM_Rateio () {
    return DM_Rateio;
  }

  public String getOID_Manifesto () {
    return OID_Manifesto;
  }

  public double getVL_Movimento () {
    return VL_Movimento;
  }
public double getVL_Tabela() {
	return VL_Tabela;
}
public void setVL_Tabela(double tabela) {
	VL_Tabela = tabela;
}
public String getOID_Tabela_Frete() {
	return OID_Tabela_Frete;
}
public void setOID_Tabela_Frete(String tabela_Frete) {
	OID_Tabela_Frete = tabela_Frete;
}

}
