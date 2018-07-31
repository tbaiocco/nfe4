package com.master.ed;

public class Calcula_FreteCRTED extends MasterED{

  private String OID_Conhecimento;
  private long NR_Conhecimento;
  private String NM_Serie;
  private String DM_Responsavel_Cobranca;
  private String DM_Tipo_Pagamento;
  private double PE_Aliquota_ICMS;
  private String TX_Observacao;
  private String DM_Isento_Seguro;
  private String NM_Atendente;
  private String NM_Natureza;
  private String DT_Previsao_Entrega;
  private String HR_Previsao_Entrega;
  private String NM_Pessoa_Entrega;
  private String DT_Emissao;
  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String OID_Pessoa_Consignatario;
  private long OID_Modal;
  private long OID_Unidade;
  private String OID_Vendedor;
  private long OID_Cidade_Origem;
  private long OID_Cidade_Destino;
  private String OID_Tabela_Frete;
  private String OID_Pessoa_Pagador;
  private long OID_Produto;
  private long OID_Coleta;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private String NM_Pessoa_Consignatario;
  private String CD_Unidade;
  private long OID_Cidade_Remetente;
  private long OID_Cidade_Destinatario;
  private long OID_Cidade_Consignatario;
  private double VL_Frete;
  private double VL_ICMS;
  private double VL_PIS;
  private double VL_COFINS;
  private double VL_Seguro;
  private double VL_Valor_Tabela;
  private double VL_Peso;
  private double VL_Nota_Fiscal;
  private long VL_Volumes;
  private String DT_Vigencia_Inicial;
  private String DT_Vigencia_Final;
  private long OID_Estado_Origem;
  private long OID_Estado_Destino;
  private long OID_Regiao_Origem;
  private long OID_Regiao_Destino;
  private String DM_Localizado;
  private double VL_Peso_Cubado;
  private double VL_Frete_KG;
  private double VL_Taxas;
  private double PE_AD_Valorem;
  private double VL_Outros1;
  private double VL_Outros2;
  private double VL_Outros3;
  private double VL_Outros4;
  private double VL_Outros5;
  private double VL_Outros6;
  private double VL_Outros7;
  private double VL_Outros8;
  private double VL_Outros9;
  private String OID_Pessoa_Pagador_Tabela;
  private double VL_Frete_Fracionado;
  private String DM_Tipo_Conhecimento;
  private String OID_Pessoa_Redespacho;
  private double VL_Faixa1;
  private double VL_Faixa2;
  private double VL_Faixa3;
  private double VL_Faixa4;
  private double VL_Faixa5;
  private double VL_Pedagio;
  private double VL_Maximo_Nota_Fiscal;
  private double VL_Frete_Minimo;
  private String DM_Tipo_Peso;
  private double VL_Ademe_Minimo;
  private double PE_Ademe;
  private double VL_AD_Valorem_Minimo;
  private double VL_Frete_Valor_Minimo;
  private double VL_Despacho;
  private double PE_Frete_Entrega;
  private String DM_Tipo_Tabela;
  private String OID_Pessoa_Entregador;
  private String DM_Tipo_Tabela_Frete;
  private String CD_CFO_Conhecimento;
  private double VL_R_Ate10;
  private double VL_R_Ate20;
  private double VL_R_Ate30;
  private double VL_R_Ate50;
  private double VL_R_Ate70;
  private double VL_R_Ate100;
  private double VL_R_Ate150;
  private double VL_R_Ate200;
  private double VL_R_Acima200;
  private double VL_Suframa_Minimo;
  private double PE_Suframa;
  private double PE_Fluvial;
  private double VL_Fluvial_Minimo;
  private double VL_TX_KM_Rodado;
  private double VL_TX_Coleta;
  private double VL_TX_Entrega;
  private double VL_TX_Col_Urg_200;
  private double VL_TX_Col_Urg_1000;
  private double VL_TX_Col_Urg_Ton;
  private double VL_TX_Ent_Urg_200;
  private double VL_TX_Ent_Urg_1000;
  private double VL_TX_Ent_Urg_Ton;
  private double VL_TX_Exc_Coleta;
  private double VL_TX_Exc_Entrega;
  private double VL_C_Ate25;
  private double VL_C_Ate50;
  private double VL_C_Ate300;
  private double VL_C_Ate500;
  private double VL_C_Ate1000;
  private double VL_C_Acima1000;
  private double VL_C_Taxa_Minima;
  private double VL_D_Ate1C;
  private double VL_D_Ate2C;
  private double VL_D_Ate3C;
  private double VL_D_Ate4C;
  private double VL_D_Ate5C;
  private double VL_D_Ate6C;
  private double VL_D_Ate7C;
  private double VL_D_Ate8C;
  private double VL_D_Ate9C;
  private double VL_D_Ate10C;
  private double VL_D_Ate1D;
  private double VL_D_Ate2D;
  private double VL_D_Ate3D;
  private double VL_D_Ate4D;
  private double VL_D_Ate5D;
  private double VL_D_Ate6D;
  private double VL_D_Ate7D;
  private double VL_D_Ate8D;
  private double VL_D_Ate9D;
  private double VL_D_Ate10D;
  private double VL_D_ExcedenteC;
  private double VL_D_ExcedenteD;
  private double PE_D_Ad_Valorem;
  private double PE_Desc_FP;
  private double PE_Desc_FV;
  private double VL_E_Ate1;
  private double VL_E_Excedente;
  private double VL_E_Ad_Valorem;
  private double PE_E_Ad_Valorem;
  private double PE_Reentrega;
  private double PE_Devolucao;
  private double PE_C_Ad_Valorem;
  private double VL_C_Pedagio;
  private long OID_Subregiao_Destino;
  private long OID_Subregiao_Origem;
  private long OID_Empresa;
  private long NR_Prazo_Faturamento;
  private String DM_Tipo_Pedagio;
  private String DM_Tipo_Coleta;
  private String DM_Tipo_Entrega;
  private double VL_Total_Frete;
  private double NR_Peso;
  private double NR_Peso_Cubado;
  private double NR_Volumes;
  private String DM_ICMS;
  private double VL_R_Ate7500;
  private double VL_R_Ate14500;
  private double VL_R_Acima14500;
  private double PE_AD_Valorem2;
  private double NR_Peso_Minimo;
  private double PE_Refaturamento;
  private double VL_Minimo_Nota_Fiscal;
  private double PE_Carga_Expressa;
  private double VL_E_1Kg;

  public void setOID_Conhecimento(String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }
  public String getOID_Conhecimento() {
    return OID_Conhecimento;
  }
  public void setNR_Conhecimento(long NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }
  public long getNR_Conhecimento() {
    return NR_Conhecimento;
  }
  public void setNM_Serie(String NM_Serie) {
    this.NM_Serie = NM_Serie;
  }
  public String getNM_Serie() {
    return NM_Serie;
  }
  public void setDM_Responsavel_Cobranca(String DM_Responsavel_Cobranca) {
    this.DM_Responsavel_Cobranca = DM_Responsavel_Cobranca;
  }
  public String getDM_Responsavel_Cobranca() {
    return DM_Responsavel_Cobranca;
  }
  public void setDM_Tipo_Pagamento(String DM_Tipo_Pagamento) {
    this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
  }
  public String getDM_Tipo_Pagamento() {
    return DM_Tipo_Pagamento;
  }
  public void setPE_Aliquota_ICMS(double PE_Aliquota_ICMS) {
    this.PE_Aliquota_ICMS = PE_Aliquota_ICMS;
  }
  public double getPE_Aliquota_ICMS() {
    return PE_Aliquota_ICMS;
  }
  public void setTX_Observacao(String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }
  public String getTX_Observacao() {
    return TX_Observacao;
  }
  public void setDM_Isento_Seguro(String DM_Isento_Seguro) {
    this.DM_Isento_Seguro = DM_Isento_Seguro;
  }
  public String getDM_Isento_Seguro() {
    return DM_Isento_Seguro;
  }
  public void setNM_Atendente(String NM_Atendente) {
    this.NM_Atendente = NM_Atendente;
  }
  public String getNM_Atendente() {
    return NM_Atendente;
  }
  public void setNM_Natureza(String NM_Natureza) {
    this.NM_Natureza = NM_Natureza;
  }
  public String getNM_Natureza() {
    return NM_Natureza;
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
  public void setNM_Pessoa_Entrega(String NM_Pessoa_Entrega) {
    this.NM_Pessoa_Entrega = NM_Pessoa_Entrega;
  }
  public String getNM_Pessoa_Entrega() {
    return NM_Pessoa_Entrega;
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
  public void setOID_Pessoa_Consignatario(String OID_Pessoa_Consignatario) {
    this.OID_Pessoa_Consignatario = OID_Pessoa_Consignatario;
  }
  public String getOID_Pessoa_Consignatario() {
    return OID_Pessoa_Consignatario;
  }
  public void setOID_Modal(long OID_Modal) {
    this.OID_Modal = OID_Modal;
  }
  public long getOID_Modal() {
    return OID_Modal;
  }
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  public void setOID_Vendedor(String OID_Vendedor) {
    this.OID_Vendedor = OID_Vendedor;
  }
  public String getOID_Vendedor() {
    return OID_Vendedor;
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
  public void setOID_Tabela_Frete(String OID_Tabela_Frete) {
    this.OID_Tabela_Frete = OID_Tabela_Frete;
  }
  public String getOID_Tabela_Frete() {
    return OID_Tabela_Frete;
  }
  public void setOID_Pessoa_Pagador(String OID_Pessoa_Pagador) {
    this.OID_Pessoa_Pagador = OID_Pessoa_Pagador;
  }
  public String getOID_Pessoa_Pagador() {
    return OID_Pessoa_Pagador;
  }
  public void setOID_Produto(long OID_Produto) {
    this.OID_Produto = OID_Produto;
  }
  public long getOID_Produto() {
    return OID_Produto;
  }
  public void setOID_Coleta(long OID_Coleta) {
    this.OID_Coleta = OID_Coleta;
  }
  public long getOID_Coleta() {
    return OID_Coleta;
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
  public void setNM_Pessoa_Consignatario(String NM_Pessoa_Consignatario) {
    this.NM_Pessoa_Consignatario = NM_Pessoa_Consignatario;
  }
  public String getNM_Pessoa_Consignatario() {
    return NM_Pessoa_Consignatario;
  }
  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
  }
  public void setOID_Cidade_Remetente(long OID_Cidade_Remetente) {
    this.OID_Cidade_Remetente = OID_Cidade_Remetente;
  }
  public long getOID_Cidade_Remetente() {
    return OID_Cidade_Remetente;
  }
  public void setOID_Cidade_Destinatario(long OID_Cidade_Destinatario) {
    this.OID_Cidade_Destinatario = OID_Cidade_Destinatario;
  }
  public long getOID_Cidade_Destinatario() {
    return OID_Cidade_Destinatario;
  }
  public void setOID_Cidade_Consignatario(long OID_Cidade_Consignatario) {
    this.OID_Cidade_Consignatario = OID_Cidade_Consignatario;
  }
  public long getOID_Cidade_Consignatario() {
    return OID_Cidade_Consignatario;
  }
  public void setVL_Frete(double VL_Frete) {
    this.VL_Frete = VL_Frete;
  }
  public double getVL_Frete() {
    return VL_Frete;
  }
  public void setVL_ICMS(double VL_ICMS) {
    this.VL_ICMS = VL_ICMS;
  }
  public double getVL_ICMS() {
    return VL_ICMS;
  }
  public void setVL_PIS(double VL_PIS) {
    this.VL_PIS = VL_PIS;
  }
  public double getVL_PIS() {
    return VL_PIS;
  }
  public void setVL_COFINS(double VL_COFINS) {
    this.VL_COFINS = VL_COFINS;
  }
  public double getVL_COFINS() {
    return VL_COFINS;
  }
  public void setVL_Seguro(double VL_Seguro) {
    this.VL_Seguro = VL_Seguro;
  }
  public double getVL_Seguro() {
    return VL_Seguro;
  }
  public void setVL_Valor_Tabela(double VL_Valor_Tabela) {
    this.VL_Valor_Tabela = VL_Valor_Tabela;
  }
  public double getVL_Valor_Tabela() {
    return VL_Valor_Tabela;
  }
  public void setVL_Peso(double VL_Peso) {
    this.VL_Peso = VL_Peso;
  }
  public double getVL_Peso() {
    return VL_Peso;
  }
  public void setVL_Nota_Fiscal(double VL_Nota_Fiscal) {
    this.VL_Nota_Fiscal = VL_Nota_Fiscal;
  }
  public double getVL_Nota_Fiscal() {
    return VL_Nota_Fiscal;
  }
  public void setVL_Volumes(long VL_Volumes) {
    this.VL_Volumes = VL_Volumes;
  }
  public long getVL_Volumes() {
    return VL_Volumes;
  }
  public void setDT_Vigencia_Inicial(String DT_Vigencia_Inicial) {
    this.DT_Vigencia_Inicial = DT_Vigencia_Inicial;
  }
  public String getDT_Vigencia_Inicial() {
    return DT_Vigencia_Inicial;
  }
  public void setDT_Vigencia_Final(String DT_Vigencia_Final) {
    this.DT_Vigencia_Final = DT_Vigencia_Final;
  }
  public String getDT_Vigencia_Final() {
    return DT_Vigencia_Final;
  }
  public void setOID_Estado_Origem(long OID_Estado_Origem) {
    this.OID_Estado_Origem = OID_Estado_Origem;
  }
  public long getOID_Estado_Origem() {
    return OID_Estado_Origem;
  }
  public void setOID_Estado_Destino(long OID_Estado_Destino) {
    this.OID_Estado_Destino = OID_Estado_Destino;
  }
  public long getOID_Estado_Destino() {
    return OID_Estado_Destino;
  }
  public void setOID_Regiao_Origem(long OID_Regiao_Origem) {
    this.OID_Regiao_Origem = OID_Regiao_Origem;
  }
  public long getOID_Regiao_Origem() {
    return OID_Regiao_Origem;
  }
  public void setOID_Regiao_Destino(long OID_Regiao_Destino) {
    this.OID_Regiao_Destino = OID_Regiao_Destino;
  }
  public long getOID_Regiao_Destino() {
    return OID_Regiao_Destino;
  }
  public void setDM_Localizado(String DM_Localizado) {
    this.DM_Localizado = DM_Localizado;
  }
  public String getDM_Localizado() {
    return DM_Localizado;
  }
  public void setVL_Peso_Cubado(double VL_Peso_Cubado) {
    this.VL_Peso_Cubado = VL_Peso_Cubado;
  }
  public double getVL_Peso_Cubado() {
    return VL_Peso_Cubado;
  }
  public void setVL_Frete_KG(double VL_Frete_KG) {
    this.VL_Frete_KG = VL_Frete_KG;
  }
  public double getVL_Frete_KG() {
    return VL_Frete_KG;
  }
  public void setVL_Taxas(double VL_Taxas) {
    this.VL_Taxas = VL_Taxas;
  }
  public double getVL_Taxas() {
    return VL_Taxas;
  }
  public void setPE_AD_Valorem(double PE_AD_Valorem) {
    this.PE_AD_Valorem = PE_AD_Valorem;
  }
  public double getPE_AD_Valorem() {
    return PE_AD_Valorem;
  }
  public void setVL_Outros1(double VL_Outros1) {
    this.VL_Outros1 = VL_Outros1;
  }
  public double getVL_Outros1() {
    return VL_Outros1;
  }
  public void setVL_Outros2(double VL_Outros2) {
    this.VL_Outros2 = VL_Outros2;
  }
  public double getVL_Outros2() {
    return VL_Outros2;
  }
  public void setVL_Outros3(double VL_Outros3) {
    this.VL_Outros3 = VL_Outros3;
  }
  public double getVL_Outros3() {
    return VL_Outros3;
  }
  public void setVL_Outros4(double VL_Outros4) {
    this.VL_Outros4 = VL_Outros4;
  }
  public double getVL_Outros4() {
    return VL_Outros4;
  }
  public void setVL_Outros5(double VL_Outros5) {
    this.VL_Outros5 = VL_Outros5;
  }
  public double getVL_Outros5() {
    return VL_Outros5;
  }
  public void setVL_Outros6(double VL_Outros6) {
    this.VL_Outros6 = VL_Outros6;
  }
  public double getVL_Outros6() {
    return VL_Outros6;
  }
  public void setVL_Outros7(double VL_Outros7) {
    this.VL_Outros7 = VL_Outros7;
  }
  public double getVL_Outros7() {
    return VL_Outros7;
  }
  public void setVL_Outros8(double VL_Outros8) {
    this.VL_Outros8 = VL_Outros8;
  }
  public double getVL_Outros8() {
    return VL_Outros8;
  }
  public void setVL_Outros9(double VL_Outros9) {
    this.VL_Outros9 = VL_Outros9;
  }
  public double getVL_Outros9() {
    return VL_Outros9;
  }
  public void setOID_Pessoa_Pagador_Tabela(String OID_Pessoa_Pagador_Tabela) {
    this.OID_Pessoa_Pagador_Tabela = OID_Pessoa_Pagador_Tabela;
  }
  public String getOID_Pessoa_Pagador_Tabela() {
    return OID_Pessoa_Pagador_Tabela;
  }
  public void setVL_Frete_Fracionado(double VL_Frete_Fracionado) {
    this.VL_Frete_Fracionado = VL_Frete_Fracionado;
  }
  public double getVL_Frete_Fracionado() {
    return VL_Frete_Fracionado;
  }
  public void setDM_Tipo_Conhecimento(String DM_Tipo_Conhecimento) {
    this.DM_Tipo_Conhecimento = DM_Tipo_Conhecimento;
  }
  public String getDM_Tipo_Conhecimento() {
    return DM_Tipo_Conhecimento;
  }
  public void setOID_Pessoa_Redespacho(String OID_Pessoa_Redespacho) {
    this.OID_Pessoa_Redespacho = OID_Pessoa_Redespacho;
  }
  public String getOID_Pessoa_Redespacho() {
    return OID_Pessoa_Redespacho;
  }
  public void setVL_Faixa1(double VL_Faixa1) {
    this.VL_Faixa1 = VL_Faixa1;
  }
  public double getVL_Faixa1() {
    return VL_Faixa1;
  }
  public void setVL_Faixa2(double VL_Faixa2) {
    this.VL_Faixa2 = VL_Faixa2;
  }
  public double getVL_Faixa2() {
    return VL_Faixa2;
  }
  public void setVL_Faixa3(double VL_Faixa3) {
    this.VL_Faixa3 = VL_Faixa3;
  }
  public double getVL_Faixa3() {
    return VL_Faixa3;
  }
  public void setVL_Faixa4(double VL_Faixa4) {
    this.VL_Faixa4 = VL_Faixa4;
  }
  public double getVL_Faixa4() {
    return VL_Faixa4;
  }
  public void setVL_Faixa5(double VL_Faixa5) {
    this.VL_Faixa5 = VL_Faixa5;
  }
  public double getVL_Faixa5() {
    return VL_Faixa5;
  }
  public void setVL_Pedagio(double VL_Pedagio) {
    this.VL_Pedagio = VL_Pedagio;
  }
  public double getVL_Pedagio() {
    return VL_Pedagio;
  }
  public void setVL_Maximo_Nota_Fiscal(double VL_Maximo_Nota_Fiscal) {
    this.VL_Maximo_Nota_Fiscal = VL_Maximo_Nota_Fiscal;
  }
  public double getVL_Maximo_Nota_Fiscal() {
    return VL_Maximo_Nota_Fiscal;
  }
  public void setVL_Frete_Minimo(double VL_Frete_Minimo) {
    this.VL_Frete_Minimo = VL_Frete_Minimo;
  }
  public double getVL_Frete_Minimo() {
    return VL_Frete_Minimo;
  }
  public void setDM_Tipo_Peso(String DM_Tipo_Peso) {
    this.DM_Tipo_Peso = DM_Tipo_Peso;
  }
  public String getDM_Tipo_Peso() {
    return DM_Tipo_Peso;
  }
  public void setVL_Ademe_Minimo(double VL_Ademe_Minimo) {
    this.VL_Ademe_Minimo = VL_Ademe_Minimo;
  }
  public double getVL_Ademe_Minimo() {
    return VL_Ademe_Minimo;
  }
  public void setPE_Ademe(double PE_Ademe) {
    this.PE_Ademe = PE_Ademe;
  }
  public double getPE_Ademe() {
    return PE_Ademe;
  }
  public void setVL_AD_Valorem_Minimo(double VL_AD_Valorem_Minimo) {
    this.VL_AD_Valorem_Minimo = VL_AD_Valorem_Minimo;
  }
  public double getVL_AD_Valorem_Minimo() {
    return VL_AD_Valorem_Minimo;
  }
  public void setVL_Frete_Valor_Minimo(double VL_Frete_Valor_Minimo) {
    this.VL_Frete_Valor_Minimo = VL_Frete_Valor_Minimo;
  }
  public double getVL_Frete_Valor_Minimo() {
    return VL_Frete_Valor_Minimo;
  }
  public void setVL_Despacho(double VL_Despacho) {
    this.VL_Despacho = VL_Despacho;
  }
  public double getVL_Despacho() {
    return VL_Despacho;
  }
  public void setPE_Frete_Entrega(double PE_Frete_Entrega) {
    this.PE_Frete_Entrega = PE_Frete_Entrega;
  }
  public double getPE_Frete_Entrega() {
    return PE_Frete_Entrega;
  }
  public void setDM_Tipo_Tabela(String DM_Tipo_Tabela) {
    this.DM_Tipo_Tabela = DM_Tipo_Tabela;
  }
  public String getDM_Tipo_Tabela() {
    return DM_Tipo_Tabela;
  }
  public void setOID_Pessoa_Entregador(String OID_Pessoa_Entregador) {
    this.OID_Pessoa_Entregador = OID_Pessoa_Entregador;
  }
  public String getOID_Pessoa_Entregador() {
    return OID_Pessoa_Entregador;
  }
  public void setDM_Tipo_Tabela_Frete(String DM_Tipo_Tabela_Frete) {
    this.DM_Tipo_Tabela_Frete = DM_Tipo_Tabela_Frete;
  }
  public String getDM_Tipo_Tabela_Frete() {
    return DM_Tipo_Tabela_Frete;
  }
  public void setCD_CFO_Conhecimento(String CD_CFO_Conhecimento) {
    this.CD_CFO_Conhecimento = CD_CFO_Conhecimento;
  }
  public String getCD_CFO_Conhecimento() {
    return CD_CFO_Conhecimento;
  }
  public void setVL_R_Ate10(double VL_R_Ate10) {
    this.VL_R_Ate10 = VL_R_Ate10;
  }
  public double getVL_R_Ate10() {
    return VL_R_Ate10;
  }
  public void setVL_R_Ate20(double VL_R_Ate20) {
    this.VL_R_Ate20 = VL_R_Ate20;
  }
  public double getVL_R_Ate20() {
    return VL_R_Ate20;
  }
  public void setVL_R_Ate30(double VL_R_Ate30) {
    this.VL_R_Ate30 = VL_R_Ate30;
  }
  public double getVL_R_Ate30() {
    return VL_R_Ate30;
  }
  public void setVL_R_Ate50(double VL_R_Ate50) {
    this.VL_R_Ate50 = VL_R_Ate50;
  }
  public double getVL_R_Ate50() {
    return VL_R_Ate50;
  }
  public void setVL_R_Ate70(double VL_R_Ate70) {
    this.VL_R_Ate70 = VL_R_Ate70;
  }
  public double getVL_R_Ate70() {
    return VL_R_Ate70;
  }
  public void setVL_R_Ate100(double VL_R_Ate100) {
    this.VL_R_Ate100 = VL_R_Ate100;
  }
  public double getVL_R_Ate100() {
    return VL_R_Ate100;
  }
  public void setVL_R_Ate150(double VL_R_Ate150) {
    this.VL_R_Ate150 = VL_R_Ate150;
  }
  public double getVL_R_Ate150() {
    return VL_R_Ate150;
  }
  public void setVL_R_Ate200(double VL_R_Ate200) {
    this.VL_R_Ate200 = VL_R_Ate200;
  }
  public double getVL_R_Ate200() {
    return VL_R_Ate200;
  }
  public void setVL_R_Acima200(double VL_R_Acima200) {
    this.VL_R_Acima200 = VL_R_Acima200;
  }
  public double getVL_R_Acima200() {
    return VL_R_Acima200;
  }
  public void setVL_Suframa_Minimo(double VL_Suframa_Minimo) {
    this.VL_Suframa_Minimo = VL_Suframa_Minimo;
  }
  public double getVL_Suframa_Minimo() {
    return VL_Suframa_Minimo;
  }
  public void setPE_Suframa(double PE_Suframa) {
    this.PE_Suframa = PE_Suframa;
  }
  public double getPE_Suframa() {
    return PE_Suframa;
  }
  public void setPE_Fluvial(double PE_Fluvial) {
    this.PE_Fluvial = PE_Fluvial;
  }
  public double getPE_Fluvial() {
    return PE_Fluvial;
  }
  public void setVL_Fluvial_Minimo(double VL_Fluvial_Minimo) {
    this.VL_Fluvial_Minimo = VL_Fluvial_Minimo;
  }
  public double getVL_Fluvial_Minimo() {
    return VL_Fluvial_Minimo;
  }
  public void setVL_TX_KM_Rodado(double VL_TX_KM_Rodado) {
    this.VL_TX_KM_Rodado = VL_TX_KM_Rodado;
  }
  public double getVL_TX_KM_Rodado() {
    return VL_TX_KM_Rodado;
  }
  public void setVL_TX_Coleta(double VL_TX_Coleta) {
    this.VL_TX_Coleta = VL_TX_Coleta;
  }
  public double getVL_TX_Coleta() {
    return VL_TX_Coleta;
  }
  public void setVL_TX_Entrega(double VL_TX_Entrega) {
    this.VL_TX_Entrega = VL_TX_Entrega;
  }
  public double getVL_TX_Entrega() {
    return VL_TX_Entrega;
  }
  public void setVL_TX_Col_Urg_200(double VL_TX_Col_Urg_200) {
    this.VL_TX_Col_Urg_200 = VL_TX_Col_Urg_200;
  }
  public double getVL_TX_Col_Urg_200() {
    return VL_TX_Col_Urg_200;
  }
  public void setVL_TX_Col_Urg_1000(double VL_TX_Col_Urg_1000) {
    this.VL_TX_Col_Urg_1000 = VL_TX_Col_Urg_1000;
  }
  public double getVL_TX_Col_Urg_1000() {
    return VL_TX_Col_Urg_1000;
  }
  public void setVL_TX_Col_Urg_Ton(double VL_TX_Col_Urg_Ton) {
    this.VL_TX_Col_Urg_Ton = VL_TX_Col_Urg_Ton;
  }
  public double getVL_TX_Col_Urg_Ton() {
    return VL_TX_Col_Urg_Ton;
  }
  public void setVL_TX_Ent_Urg_200(double VL_TX_Ent_Urg_200) {
    this.VL_TX_Ent_Urg_200 = VL_TX_Ent_Urg_200;
  }
  public double getVL_TX_Ent_Urg_200() {
    return VL_TX_Ent_Urg_200;
  }
  public void setVL_TX_Ent_Urg_1000(double VL_TX_Ent_Urg_1000) {
    this.VL_TX_Ent_Urg_1000 = VL_TX_Ent_Urg_1000;
  }
  public double getVL_TX_Ent_Urg_1000() {
    return VL_TX_Ent_Urg_1000;
  }
  public void setVL_TX_Ent_Urg_Ton(double VL_TX_Ent_Urg_Ton) {
    this.VL_TX_Ent_Urg_Ton = VL_TX_Ent_Urg_Ton;
  }
  public double getVL_TX_Ent_Urg_Ton() {
    return VL_TX_Ent_Urg_Ton;
  }
  public void setVL_TX_Exc_Coleta(double VL_TX_Exc_Coleta) {
    this.VL_TX_Exc_Coleta = VL_TX_Exc_Coleta;
  }
  public double getVL_TX_Exc_Coleta() {
    return VL_TX_Exc_Coleta;
  }
  public void setVL_TX_Exc_Entrega(double VL_TX_Exc_Entrega) {
    this.VL_TX_Exc_Entrega = VL_TX_Exc_Entrega;
  }
  public double getVL_TX_Exc_Entrega() {
    return VL_TX_Exc_Entrega;
  }
  public void setVL_C_Ate25(double VL_C_Ate25) {
    this.VL_C_Ate25 = VL_C_Ate25;
  }
  public double getVL_C_Ate25() {
    return VL_C_Ate25;
  }
  public void setVL_C_Ate50(double VL_C_Ate50) {
    this.VL_C_Ate50 = VL_C_Ate50;
  }
  public double getVL_C_Ate50() {
    return VL_C_Ate50;
  }
  public void setVL_C_Ate300(double VL_C_Ate300) {
    this.VL_C_Ate300 = VL_C_Ate300;
  }
  public double getVL_C_Ate300() {
    return VL_C_Ate300;
  }
  public void setVL_C_Ate500(double VL_C_Ate500) {
    this.VL_C_Ate500 = VL_C_Ate500;
  }
  public double getVL_C_Ate500() {
    return VL_C_Ate500;
  }
  public void setVL_C_Ate1000(double VL_C_Ate1000) {
    this.VL_C_Ate1000 = VL_C_Ate1000;
  }
  public double getVL_C_Ate1000() {
    return VL_C_Ate1000;
  }
  public void setVL_C_Acima1000(double VL_C_Acima1000) {
    this.VL_C_Acima1000 = VL_C_Acima1000;
  }
  public double getVL_C_Acima1000() {
    return VL_C_Acima1000;
  }
  public void setVL_C_Taxa_Minima(double VL_C_Taxa_Minima) {
    this.VL_C_Taxa_Minima = VL_C_Taxa_Minima;
  }
  public double getVL_C_Taxa_Minima() {
    return VL_C_Taxa_Minima;
  }
  public void setVL_D_Ate1C(double VL_D_Ate1C) {
    this.VL_D_Ate1C = VL_D_Ate1C;
  }
  public double getVL_D_Ate1C() {
    return VL_D_Ate1C;
  }
  public void setVL_D_Ate2C(double VL_D_Ate2C) {
    this.VL_D_Ate2C = VL_D_Ate2C;
  }
  public double getVL_D_Ate2C() {
    return VL_D_Ate2C;
  }
  public void setVL_D_Ate3C(double VL_D_Ate3C) {
    this.VL_D_Ate3C = VL_D_Ate3C;
  }
  public double getVL_D_Ate3C() {
    return VL_D_Ate3C;
  }
  public void setVL_D_Ate4C(double VL_D_Ate4C) {
    this.VL_D_Ate4C = VL_D_Ate4C;
  }
  public double getVL_D_Ate4C() {
    return VL_D_Ate4C;
  }
  public void setVL_D_Ate5C(double VL_D_Ate5C) {
    this.VL_D_Ate5C = VL_D_Ate5C;
  }
  public double getVL_D_Ate5C() {
    return VL_D_Ate5C;
  }
  public void setVL_D_Ate6C(double VL_D_Ate6C) {
    this.VL_D_Ate6C = VL_D_Ate6C;
  }
  public double getVL_D_Ate6C() {
    return VL_D_Ate6C;
  }
  public void setVL_D_Ate7C(double VL_D_Ate7C) {
    this.VL_D_Ate7C = VL_D_Ate7C;
  }
  public double getVL_D_Ate7C() {
    return VL_D_Ate7C;
  }
  public void setVL_D_Ate8C(double VL_D_Ate8C) {
    this.VL_D_Ate8C = VL_D_Ate8C;
  }
  public double getVL_D_Ate8C() {
    return VL_D_Ate8C;
  }
  public void setVL_D_Ate9C(double VL_D_Ate9C) {
    this.VL_D_Ate9C = VL_D_Ate9C;
  }
  public double getVL_D_Ate9C() {
    return VL_D_Ate9C;
  }
  public void setVL_D_Ate10C(double VL_D_Ate10C) {
    this.VL_D_Ate10C = VL_D_Ate10C;
  }
  public double getVL_D_Ate10C() {
    return VL_D_Ate10C;
  }
  public void setVL_D_Ate1D(double VL_D_Ate1D) {
    this.VL_D_Ate1D = VL_D_Ate1D;
  }
  public double getVL_D_Ate1D() {
    return VL_D_Ate1D;
  }
  public void setVL_D_Ate2D(double VL_D_Ate2D) {
    this.VL_D_Ate2D = VL_D_Ate2D;
  }
  public double getVL_D_Ate2D() {
    return VL_D_Ate2D;
  }
  public void setVL_D_Ate3D(double VL_D_Ate3D) {
    this.VL_D_Ate3D = VL_D_Ate3D;
  }
  public double getVL_D_Ate3D() {
    return VL_D_Ate3D;
  }
  public void setVL_D_Ate4D(double VL_D_Ate4D) {
    this.VL_D_Ate4D = VL_D_Ate4D;
  }
  public double getVL_D_Ate4D() {
    return VL_D_Ate4D;
  }
  public void setVL_D_Ate5D(double VL_D_Ate5D) {
    this.VL_D_Ate5D = VL_D_Ate5D;
  }
  public double getVL_D_Ate5D() {
    return VL_D_Ate5D;
  }
  public void setVL_D_Ate6D(double VL_D_Ate6D) {
    this.VL_D_Ate6D = VL_D_Ate6D;
  }
  public double getVL_D_Ate6D() {
    return VL_D_Ate6D;
  }
  public void setVL_D_Ate7D(double VL_D_Ate7D) {
    this.VL_D_Ate7D = VL_D_Ate7D;
  }
  public double getVL_D_Ate7D() {
    return VL_D_Ate7D;
  }
  public void setVL_D_Ate8D(double VL_D_Ate8D) {
    this.VL_D_Ate8D = VL_D_Ate8D;
  }
  public double getVL_D_Ate8D() {
    return VL_D_Ate8D;
  }
  public void setVL_D_Ate9D(double VL_D_Ate9D) {
    this.VL_D_Ate9D = VL_D_Ate9D;
  }
  public double getVL_D_Ate9D() {
    return VL_D_Ate9D;
  }
  public void setVL_D_Ate10D(double VL_D_Ate10D) {
    this.VL_D_Ate10D = VL_D_Ate10D;
  }
  public double getVL_D_Ate10D() {
    return VL_D_Ate10D;
  }
  public void setVL_D_ExcedenteC(double VL_D_ExcedenteC) {
    this.VL_D_ExcedenteC = VL_D_ExcedenteC;
  }
  public double getVL_D_ExcedenteC() {
    return VL_D_ExcedenteC;
  }
  public void setVL_D_ExcedenteD(double VL_D_ExcedenteD) {
    this.VL_D_ExcedenteD = VL_D_ExcedenteD;
  }
  public double getVL_D_ExcedenteD() {
    return VL_D_ExcedenteD;
  }
  public void setPE_D_Ad_Valorem(double PE_D_Ad_Valorem) {
    this.PE_D_Ad_Valorem = PE_D_Ad_Valorem;
  }
  public double getPE_D_Ad_Valorem() {
    return PE_D_Ad_Valorem;
  }
  public void setPE_Desc_FP(double PE_Desc_FP) {
    this.PE_Desc_FP = PE_Desc_FP;
  }
  public double getPE_Desc_FP() {
    return PE_Desc_FP;
  }
  public void setPE_Desc_FV(double PE_Desc_FV) {
    this.PE_Desc_FV = PE_Desc_FV;
  }
  public double getPE_Desc_FV() {
    return PE_Desc_FV;
  }
  public void setVL_E_Ate1(double VL_E_Ate1) {
    this.VL_E_Ate1 = VL_E_Ate1;
  }
  public double getVL_E_Ate1() {
    return VL_E_Ate1;
  }
  public void setVL_E_Excedente(double VL_E_Excedente) {
    this.VL_E_Excedente = VL_E_Excedente;
  }
  public double getVL_E_Excedente() {
    return VL_E_Excedente;
  }
  public void setVL_E_Ad_Valorem(double VL_E_Ad_Valorem) {
    this.VL_E_Ad_Valorem = VL_E_Ad_Valorem;
  }
  public double getVL_E_Ad_Valorem() {
    return VL_E_Ad_Valorem;
  }
  public void setPE_E_Ad_Valorem(double PE_E_Ad_Valorem) {
    this.PE_E_Ad_Valorem = PE_E_Ad_Valorem;
  }
  public double getPE_E_Ad_Valorem() {
    return PE_E_Ad_Valorem;
  }
  public void setPE_Reentrega(double PE_Reentrega) {
    this.PE_Reentrega = PE_Reentrega;
  }
  public double getPE_Reentrega() {
    return PE_Reentrega;
  }
  public void setPE_Devolucao(double PE_Devolucao) {
    this.PE_Devolucao = PE_Devolucao;
  }
  public double getPE_Devolucao() {
    return PE_Devolucao;
  }
  public void setPE_C_Ad_Valorem(double PE_C_Ad_Valorem) {
    this.PE_C_Ad_Valorem = PE_C_Ad_Valorem;
  }
  public double getPE_C_Ad_Valorem() {
    return PE_C_Ad_Valorem;
  }
  public void setVL_C_Pedagio(double VL_C_Pedagio) {
    this.VL_C_Pedagio = VL_C_Pedagio;
  }
  public double getVL_C_Pedagio() {
    return VL_C_Pedagio;
  }
  public void setOID_Subregiao_Origem(long OID_Subregiao_Origem) {
    this.OID_Subregiao_Origem = OID_Subregiao_Origem;
  }
  public long getOID_Subregiao_Origem() {
    return OID_Subregiao_Origem;
  }
  public void setOID_Subregiao_Destino(long OID_Subregiao_Destino) {
    this.OID_Subregiao_Destino = OID_Subregiao_Destino;
  }
  public long getOID_Subregiao_Destino() {
    return OID_Subregiao_Destino;
  }
  public void setOID_Empresa(long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }
  public long getOID_Empresa() {
    return OID_Empresa;
  }
  public void setNR_Prazo_Faturamento(long NR_Prazo_Faturamento) {
    this.NR_Prazo_Faturamento = NR_Prazo_Faturamento;
  }
  public long getNR_Prazo_Faturamento() {
    return NR_Prazo_Faturamento;
  }
  public void setDM_Tipo_Pedagio(String DM_Tipo_Pedagio) {
    this.DM_Tipo_Pedagio = DM_Tipo_Pedagio;
  }
  public String getDM_Tipo_Pedagio() {
    return DM_Tipo_Pedagio;
  }
  public void setDM_Tipo_Coleta(String DM_Tipo_Coleta) {
    this.DM_Tipo_Coleta = DM_Tipo_Coleta;
  }
  public String getDM_Tipo_Coleta() {
    return DM_Tipo_Coleta;
  }
  public void setDM_Tipo_Entrega(String DM_Tipo_Entrega) {
    this.DM_Tipo_Entrega = DM_Tipo_Entrega;
  }
  public String getDM_Tipo_Entrega() {
    return DM_Tipo_Entrega;
  }
  public void setVL_Total_Frete(double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }
  public double getVL_Total_Frete() {
    return VL_Total_Frete;
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
  public void setNR_Volumes(double NR_Volumes) {
    this.NR_Volumes = NR_Volumes;
  }
  public double getNR_Volumes() {
    return NR_Volumes;
  }
  public void setDM_ICMS(String DM_ICMS) {
    this.DM_ICMS = DM_ICMS;
  }
  public String getDM_ICMS() {
    return DM_ICMS;
  }
  public void setVL_R_Ate7500(double VL_R_Ate7500) {
    this.VL_R_Ate7500 = VL_R_Ate7500;
  }
  public double getVL_R_Ate7500() {
    return VL_R_Ate7500;
  }
  public void setVL_R_Ate14500(double VL_R_Ate14500) {
    this.VL_R_Ate14500 = VL_R_Ate14500;
  }
  public double getVL_R_Ate14500() {
    return VL_R_Ate14500;
  }
  public void setVL_R_Acima14500(double VL_R_Acima14500) {
    this.VL_R_Acima14500 = VL_R_Acima14500;
  }
  public double getVL_R_Acima14500() {
    return VL_R_Acima14500;
  }
  public void setPE_AD_Valorem2(double PE_AD_Valorem2) {
    this.PE_AD_Valorem2 = PE_AD_Valorem2;
  }
  public double getPE_AD_Valorem2() {
    return PE_AD_Valorem2;
  }
  public double getNR_Peso_Minimo() {
    return NR_Peso_Minimo;
  }
  public void setNR_Peso_Minimo(double NR_Peso_Minimo) {
    this.NR_Peso_Minimo = NR_Peso_Minimo;
  }
  public double getPE_Refaturamento() {
    return PE_Refaturamento;
  }
  public void setPE_Refaturamento(double PE_Refaturamento) {
    this.PE_Refaturamento = PE_Refaturamento;
  }
  public double getVL_Minimo_Nota_Fiscal() {
    return VL_Minimo_Nota_Fiscal;
  }
  public void setVL_Minimo_Nota_Fiscal(double VL_Minimo_Nota_Fiscal) {
    this.VL_Minimo_Nota_Fiscal = VL_Minimo_Nota_Fiscal;
  }
  public void setPE_Carga_Expressa(double PE_Carga_Expressa) {
    this.PE_Carga_Expressa = PE_Carga_Expressa;
  }
  public double getPE_Carga_Expressa() {
    return PE_Carga_Expressa;
  }

  public void setVL_E_1Kg (double VL_E_1Kg) {
    this.VL_E_1Kg = VL_E_1Kg;
  }

  public double getVL_E_1Kg () {
    return VL_E_1Kg;
  }

}
