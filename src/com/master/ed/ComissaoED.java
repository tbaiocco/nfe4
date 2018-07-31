package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ComissaoED extends MasterED {
  private long OID_Empresa;
  private String NM_Empresa;
  private long OID_Unidade;
  private long OID_Grupo_Economico;
  private long OID_Produto;
  private long OID_Modal;
  private String OID_Cliente;
  private String OID_Vendedor;
  private String NM_Cliente;
  private String NM_Vendedor;
  private String NM_Grupo_Economico;
  private String NM_Modal;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String DM_Tipo_Pagamento;
  private String DM_Classificar;
  private String DM_Relatorio;
  private String DM_Lista_Conhecimento;
  private String NR_Amostra;
  private String OID_Pessoa_Remetente;
  private String NM_Remetente;
  private String OID_Pessoa_Destinatario;
  private String NM_Destinatario;
  private String OID_Pessoa_Pagador;
  private String NM_Pagador;
  private String DM_Situacao_Cobranca;
  private String DM_Tipo_Embarque;
  private String DM_Frete;

  private double VL_Total_Frete;
  private double VL_Notas_Fiscais;
  private double NR_Peso;
  private double NR_Peso_Cubado;
  private double NR_Conhecimentos;
  private double VL_Margem;
  private double NR_Volumes;
  private double VL_Classifica;
  private double NR_Despachos;

  private double TVL_Total_Frete;
  private double TVL_Notas_Fiscais;
  private double TNR_Peso;
  private double TNR_Peso_Cubado;
  private double TNR_Conhecimentos;
  private double TVL_Margem;
  private double TNR_Volumes;
  private double TVL_Classifica;
  private double TNR_Despachos;

  private long OID_Comissao;
  private String OID_Pessoa;
  private String NR_Sort;
  private String OID_Conhecimento;
  private long OID_Cidade_Origem;
  private long OID_Cidade_Destino;
  private String DM_Pessoa;
  private double VL_1;
  private double VL_2;
  private double VL_3;
  private double VL_4;
  private double VL_5;
  private double VL_Vencido;
  private double VL_Vencer;
  private long OID_Conta;
  private long OID_Carteira;
  private long OID_Conta_Corrente;
  private String NM_Origem;
  public long getOID_Empresa() {
    return OID_Empresa;
  }
  public void setOID_Empresa(long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }
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
  public void setOID_Grupo_Economico(long OID_Grupo_Economico) {
    this.OID_Grupo_Economico = OID_Grupo_Economico;
  }
  public long getOID_Grupo_Economico() {
    return OID_Grupo_Economico;
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
  public void setOID_Cliente(String OID_Cliente) {
    this.OID_Cliente = OID_Cliente;
  }
  public String getOID_Cliente() {
    return OID_Cliente;
  }
  public void setOID_Vendedor(String OID_Vendedor) {
    this.OID_Vendedor = OID_Vendedor;
  }
  public String getOID_Vendedor() {
    return OID_Vendedor;
  }
  public void setNM_Cliente(String NM_Cliente) {
    this.NM_Cliente = NM_Cliente;
  }
  public String getNM_Cliente() {
    return NM_Cliente;
  }
  public void setNM_Vendedor(String NM_Vendedor) {
    this.NM_Vendedor = NM_Vendedor;
  }
  public String getNM_Vendedor() {
    return NM_Vendedor;
  }
  public void setNM_Grupo_Economico(String NM_Grupo_Economico) {
    this.NM_Grupo_Economico = NM_Grupo_Economico;
  }
  public String getNM_Grupo_Economico() {
    return NM_Grupo_Economico;
  }
  public void setNM_Modal(String NM_Modal) {
    this.NM_Modal = NM_Modal;
  }
  public String getNM_Modal() {
    return NM_Modal;
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
  public void setDM_Tipo_Pagamento(String DM_Tipo_Pagamento) {
    this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
  }
  public String getDM_Tipo_Pagamento() {
    return DM_Tipo_Pagamento;
  }
  public void setDM_Classificar(String DM_Classificar) {
    this.DM_Classificar = DM_Classificar;
  }
  public String getDM_Classificar() {
    return DM_Classificar;
  }
  public void setDM_Relatorio(String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }
  public String getDM_Relatorio() {
    return DM_Relatorio;
  }
  public void setDM_Lista_Conhecimento(String DM_Lista_Conhecimento) {
    this.DM_Lista_Conhecimento = DM_Lista_Conhecimento;
  }
  public String getDM_Lista_Conhecimento() {
    return DM_Lista_Conhecimento;
  }
  public void setNR_Amostra(String NR_Amostra) {
    this.NR_Amostra = NR_Amostra;
  }
  public String getNR_Amostra() {
    return NR_Amostra;
  }
  public void setOID_Pessoa_Remetente(String OID_Pessoa_Remetente) {
    this.OID_Pessoa_Remetente = OID_Pessoa_Remetente;
  }
  public String getOID_Pessoa_Remetente() {
    return OID_Pessoa_Remetente;
  }
  public void setNM_Remetente(String NM_Remetente) {
    this.NM_Remetente = NM_Remetente;
  }
  public String getNM_Remetente() {
    return NM_Remetente;
  }
  public void setOID_Pessoa_Destinatario(String OID_Pessoa_Destinatario) {
    this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
  }
  public String getOID_Pessoa_Destinatario() {
    return OID_Pessoa_Destinatario;
  }
  public void setNM_Destinatario(String NM_Destinatario) {
    this.NM_Destinatario = NM_Destinatario;
  }
  public String getNM_Destinatario() {
    return NM_Destinatario;
  }
  public void setOID_Pessoa_Pagador(String OID_Pessoa_Pagador) {
    this.OID_Pessoa_Pagador = OID_Pessoa_Pagador;
  }
  public String getOID_Pessoa_Pagador() {
    return OID_Pessoa_Pagador;
  }
  public void setNM_Pagador(String NM_Pagador) {
    this.NM_Pagador = NM_Pagador;
  }
  public String getNM_Pagador() {
    return NM_Pagador;
  }
  public void setDM_Situacao_Cobranca(String DM_Situacao_Cobranca) {
    this.DM_Situacao_Cobranca = DM_Situacao_Cobranca;
  }
  public String getDM_Situacao_Cobranca() {
    return DM_Situacao_Cobranca;
  }
  public void setDM_Tipo_Embarque(String DM_Tipo_Embarque) {
    this.DM_Tipo_Embarque = DM_Tipo_Embarque;
  }
  public String getDM_Tipo_Embarque() {
    return DM_Tipo_Embarque;
  }
  public void setDM_Frete(String DM_Frete) {
    this.DM_Frete = DM_Frete;
  }
  public String getDM_Frete() {
    return DM_Frete;
  }
  public void setVL_Total_Frete(double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }
  public double getVL_Total_Frete() {
    return VL_Total_Frete;
  }
  public void setVL_Notas_Fiscais(double VL_Notas_Fiscais) {
    this.VL_Notas_Fiscais = VL_Notas_Fiscais;
  }
  public double getVL_Notas_Fiscais() {
    return VL_Notas_Fiscais;
  }
  public void setNR_Peso(double NR_Peso) {
    this.NR_Peso = NR_Peso;
  }
  public double getNR_Peso() {
    return NR_Peso;
  }
  public void setNR_Peso_Cubado(double NR_Peso_Cubado) {
    this.NR_Peso_Cubado = NR_Peso_Cubado;
  }
  public double getNR_Peso_Cubado() {
    return NR_Peso_Cubado;
  }
  public void setNR_Conhecimentos(double NR_Conhecimentos) {
    this.NR_Conhecimentos = NR_Conhecimentos;
  }
  public double getNR_Conhecimentos() {
    return NR_Conhecimentos;
  }
  public void setVL_Margem(double VL_Margem) {
    this.VL_Margem = VL_Margem;
  }
  public double getVL_Margem() {
    return VL_Margem;
  }
  public void setNR_Volumes(double NR_Volumes) {
    this.NR_Volumes = NR_Volumes;
  }
  public double getNR_Volumes() {
    return NR_Volumes;
  }
  public void setVL_Classifica(double VL_Classifica) {
    this.VL_Classifica = VL_Classifica;
  }
  public double getVL_Classifica() {
    return VL_Classifica;
  }
  public void setNR_Sort(String NR_Sort) {
    this.NR_Sort = NR_Sort;
  }
  public String getNR_Sort() {
    return NR_Sort;
  }
  public void setOID_Comissao(long OID_Comissao) {
    this.OID_Comissao = OID_Comissao;
  }
  public long getOID_Comissao() {
    return OID_Comissao;
  }
  public void setOID_Pessoa(String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public String getOID_Pessoa() {
    return OID_Pessoa;
  }
  public void setNR_Despachos(double NR_Despachos) {
    this.NR_Despachos = NR_Despachos;
  }
  public double getNR_Despachos() {
    return NR_Despachos;
  }
  public void setOID_Conhecimento(String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }
  public String getOID_Conhecimento() {
    return OID_Conhecimento;
  }
  public double getTNR_Conhecimentos() {
    return TNR_Conhecimentos;
  }
  public double getTNR_Peso() {
    return TNR_Peso;
  }
  public double getTNR_Peso_Cubado() {
    return TNR_Peso_Cubado;
  }
  public double getTNR_Volumes() {
    return TNR_Volumes;
  }
  public double getTVL_Classifica() {
    return TVL_Classifica;
  }
  public double getTVL_Margem() {
    return TVL_Margem;
  }
  public double getTVL_Notas_Fiscais() {
    return TVL_Notas_Fiscais;
  }
  public double getTVL_Total_Frete() {
    return TVL_Total_Frete;
  }
  public void setTVL_Total_Frete(double TVL_Total_Frete) {
    this.TVL_Total_Frete = TVL_Total_Frete;
  }
  public void setTVL_Notas_Fiscais(double TVL_Notas_Fiscais) {
    this.TVL_Notas_Fiscais = TVL_Notas_Fiscais;
  }
  public void setTVL_Margem(double TVL_Margem) {
    this.TVL_Margem = TVL_Margem;
  }
  public void setTVL_Classifica(double TVL_Classifica) {
    this.TVL_Classifica = TVL_Classifica;
  }
  public void setTNR_Volumes(double TNR_Volumes) {
    this.TNR_Volumes = TNR_Volumes;
  }
  public void setTNR_Peso_Cubado(double TNR_Peso_Cubado) {
    this.TNR_Peso_Cubado = TNR_Peso_Cubado;
  }
  public void setTNR_Peso(double TNR_Peso) {
    this.TNR_Peso = TNR_Peso;
  }
  public void setTNR_Conhecimentos(double TNR_Conhecimentos) {
    this.TNR_Conhecimentos = TNR_Conhecimentos;
  }
  public double getTNR_Despachos() {
    return TNR_Despachos;
  }
  public void setTNR_Despachos(double TNR_Despachos) {
    this.TNR_Despachos = TNR_Despachos;
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
  public void setDM_Pessoa(String DM_Pessoa) {
    this.DM_Pessoa = DM_Pessoa;
  }
  public String getDM_Pessoa() {
    return DM_Pessoa;
  }
  public void setVL_1(double VL_1) {
    this.VL_1 = VL_1;
  }
  public double getVL_1() {
    return VL_1;
  }
  public void setVL_2(double VL_2) {
    this.VL_2 = VL_2;
  }
  public double getVL_2() {
    return VL_2;
  }
  public void setVL_3(double VL_3) {
    this.VL_3 = VL_3;
  }
  public double getVL_3() {
    return VL_3;
  }
  public void setVL_4(double VL_4) {
    this.VL_4 = VL_4;
  }
  public double getVL_4() {
    return VL_4;
  }
  public void setVL_5(double VL_5) {
    this.VL_5 = VL_5;
  }
  public double getVL_5() {
    return VL_5;
  }
  public void setVL_Vencido(double VL_Vencido) {
    this.VL_Vencido = VL_Vencido;
  }
  public double getVL_Vencido() {
    return VL_Vencido;
  }
  public void setVL_Vencer(double VL_Vencer) {
    this.VL_Vencer = VL_Vencer;
  }
  public double getVL_Vencer() {
    return VL_Vencer;
  }
  public void setOID_Conta(long OID_Conta) {
    this.OID_Conta = OID_Conta;
  }
  public long getOID_Conta() {
    return OID_Conta;
  }
  public void setOID_Carteira(long OID_Carteira) {
    this.OID_Carteira = OID_Carteira;
  }
  public long getOID_Carteira() {
    return OID_Carteira;
  }
  public void setOID_Conta_Corrente(long OID_Conta_Corrente) {
    this.OID_Conta_Corrente = OID_Conta_Corrente;
  }
  public long getOID_Conta_Corrente() {
    return OID_Conta_Corrente;
  }
  public void setNM_Origem(String NM_Origem) {
    this.NM_Origem = NM_Origem;
  }
  public String getNM_Origem() {
    return NM_Origem;
  }



}