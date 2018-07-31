package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.DuplicataBD;
import com.master.ed.RelatorioED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.ManipulaArquivo;
import com.master.util.Mensagens;
import com.master.util.ModuloUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

import auth.OracleConnection2;

public class ClienteBean {
  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);
  private String DM_Tipo_Cobranca;
  private String DM_Tipo_Faturamento;
  private String DM_Condicao_Vencimento;
  private String DM_Fatura_Segunda;
  private String DM_Fatura_Terca;
  private String DM_Fatura_Quarta;
  private String DM_Fatura_Quinta;
  private String DM_Fatura_Sexta;
  private String NR_CNPJ_CPF;
  private String NM_Razao_Social;
  private String NM_Fantasia;
  private String NM_Endereco = "";
  private String NM_Inscricao_Estadual = "";
  private String OID_Cidade;
  private String NM_Cidade_Estado_Pais;
  private String DM_Vencimento_Segunda;
  private String DM_Vencimento_Terca;
  private String DM_Vencimento_Quarta;
  private String DM_Vencimento_Quinta;
  private String DM_Vencimento_Sexta;
  private String DM_FOB_Dirigido;
  private String DM_Isencao_ICMS;
  private String DM_Isencao_Seguro;
  private String DM_Credito;
  private String DM_Situacao;
  private String DM_Compr_Entrega_Fatura;
  private String DM_Tipo_Emissao;
  private String NR_Conta_Contabil;
  private int NR_Dias_Vencimento;
  private int NR_Dias_Protesto;
  private int NR_Minimo_CTO_Fatura;
  private int NR_Maximo_CTO_Fatura;
  private int NR_Peso_Cubado;
  private double VL_Minimo_Fatura;
  private double VL_Maximo_Fatura;
  private double VL_Limite_Credito;
  private double PE_Juros_Cobranca;
  private double PE_Base_Comissao_Motorista;
  private double VL_Taxa_Cobranca;
  private double VL_Taxa_Despacho;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private String oid_Cliente;
  private String oid_Pessoa;
  private int oid_Ramo_Atividade;
  private String NM_Ramo_Atividade;
  private String CD_Ramo_Atividade;
  private int oid_Grupo_Economico;
  private int oid_Carteira;
  private String CD_Grupo_Economico;
  private String NM_Grupo_Economico;
  private String oid_Cliente_Pagador;
  private String NR_CNPJ_CPF_Pagador;
  private String NM_Razao_Social_Pagador;
  private String NM_Fantasia_Pagador;
  private String oid_Vendedor;
  private int OID_Modal;
  private String CD_Conta_Corrente;
  private String cd_Carteira;
  private String NM_Banco;
  private String CD_Cidade;
  private String NM_Cidade = "";
  private double PE_Comissao;
  private String NM_Razao_Social_Vendedor;
  private String DT_Cadastro;
  private String DT_Alteracao;
  private String DT_Desativado;
  private String DM_PIS_COFINS;
  private double PE_PIS_COFINS;
  private int NR_QT_Notas_Fiscais;
  private int OID_Segmento;
  private int OID_Unidade_Operacional;
  private String TX_Instrucao_Cobranca;
  private String DM_Tracking;
  private String oid_Pessoa_Parceiro;
  private double VL_Limite_Credito_Adicional;
  private double VL_Minimo_Compra;
  private int NR_Duplicatas_Vencidas;
  private String DT_Validade_Cadastro;
  private String CD_Estado = "";
  private String NM_Estado;
  private String CD_Pais;
  private String NM_Pais;
  private int oid_Produto;
  private String DM_RCTRC;
  private String DM_RCTR_VI;
  private String DM_RCTR_DC;
  private String DM_RCTA;
  private String CD_Cliente_Palm;
  private int oid_Condicao_Pagamento;
  private String DM_Forma_Pagamento;
  private String DM_Resp_Vendedor;
  private String NM_Bairro;
  private String NR_CEP;
  private int oid_Conta;
  private int oid_Conta_Debito;
  private String DM_Monitoramento;
  private String DM_Carregamento;
  private String DM_ICMS_WMS;
  private double VL_Taxa_Coleta;
  private double VL_Taxa_Entrega;
  private String DM_ISSQN;
  private double PE_ISSQN;
  private String DM_Wms;
  private double VL_Custo_Carga;
  private double VL_Custo_Descarga;
  private int NR_Dias_Contagem_Ciclica;
  private String DM_Inclui_ICMS_Parcela_CTRC;
  private String NM_Contato_Cobranca;
  private String CD_Acesso;
  private double PE_Desconto_Vencimento;

  private int NR_Dias_Bloqueio;
  private String DT_Ultimo_Movimento;
  private String DT_Bloqueio;

  private String DM_Faturamento;

  public static String getColorSituacao (String dmCredito , String dtDesativacao) {
    if (JavaUtil.doValida (dtDesativacao)) {
      return "#ff6600";
    }
    else {
      return!"N".equals (dmCredito) ? "#fff8f0" : "#f7f7f7";
    }
  }

  public static String formataCodigoCliente (String codigo) {
    return JavaUtil.formatar (codigo , "#####-#");
  }

  public ClienteBean () {
    DM_Tipo_Cobranca = "";
    DM_Tipo_Faturamento = "";
    DM_Condicao_Vencimento = "";
    DM_Fatura_Segunda = "";
    DM_Fatura_Terca = "";
    DM_Fatura_Quarta = "";
    DM_Fatura_Quinta = "";
    DM_Fatura_Sexta = "";
    NR_CNPJ_CPF = "";
    NM_Razao_Social = "";
    NM_Fantasia = "";
    DM_Vencimento_Segunda = "";
    DM_Vencimento_Terca = "";
    DM_Vencimento_Quarta = "";
    DM_Vencimento_Quinta = "";
    DM_Vencimento_Sexta = "";
    DM_FOB_Dirigido = "";
    DM_Isencao_ICMS = "";
    DM_Isencao_Seguro = "";
    DM_Credito = "";
    DM_Compr_Entrega_Fatura = "";
    DM_Tipo_Emissao = "";
    NR_Conta_Contabil = "";
    NR_Dias_Vencimento = 0;
    NR_Dias_Protesto = 0;
    VL_Minimo_Fatura = 0;
    VL_Maximo_Fatura = 0;
    VL_Limite_Credito = 0;
    NR_Minimo_CTO_Fatura = 0;
    NR_Maximo_CTO_Fatura = 0;
    PE_Juros_Cobranca = 0;
    VL_Taxa_Cobranca = 0;
    NR_Peso_Cubado = 0;
    Usuario_Stamp = "";
    Dm_Stamp = "";
    oid_Cliente = "";
    oid_Pessoa = "";
    oid_Ramo_Atividade = 0;
    OID_Modal = 0;
    NM_Ramo_Atividade = "";
    CD_Ramo_Atividade = "";
    oid_Grupo_Economico = 0;
    CD_Grupo_Economico = "";
    NM_Grupo_Economico = "";
    oid_Cliente_Pagador = "";
    NR_CNPJ_CPF_Pagador = "";
    NM_Razao_Social_Pagador = "";
    NM_Fantasia_Pagador = "";
    oid_Vendedor = "";
    OID_Unidade_Operacional = 0;
    DM_Situacao = "";
    TX_Instrucao_Cobranca = "";
    DM_Tracking = "";
    oid_Pessoa_Parceiro = "";
    VL_Limite_Credito_Adicional = 0;
    VL_Minimo_Compra = 0;
    NR_Duplicatas_Vencidas = 0;
    DT_Validade_Cadastro = "";
    DT_Cadastro = "";
    DT_Alteracao = "";
    DT_Desativado = "";
    CD_Cliente_Palm = "";
  }

  // *** CR�DITO
  public String getDescDM_Credito () {
    if ("N".equals (DM_Credito)) {
      return "Ativo";
    }
    else if ("S".equals (DM_Credito)) {
      return "Cr�dito Bloqueado";
    }
    else if ("B".equals (DM_Credito)) {
      return "Suspender Vendas";
    }
    else if ("D".equals (DM_Credito)) {
      return "Desabilitado Sintegra";
    }
    else {
      return "N�o informado";
    }
  }

  // *** SITUA��O
  public String getDescDM_Situacao () {
    if ("P".equals (DM_Situacao)) {
      return "Prospect";
    }
    else if ("I".equals (DM_Situacao)) {
      return "Inativo";
    }
    else if ("M".equals (DM_Situacao)) {
      return "Manuten��o";
    }
    else if ("R".equals (DM_Situacao)) {
      return "Reconquista";
    }
    else {
      return "N�o informada";
    }
  }

  public String getCD_Cidade () {
    return CD_Cidade;
  }

  public void setCD_Cidade (String cidade) {
    CD_Cidade = cidade;
  }

  public String getOID_Cidade () {
    return OID_Cidade;
  }

  public void setOID_Cidade (String cidade) {
    OID_Cidade = cidade;
  }

  public String getCD_Estado () {
    return CD_Estado;
  }

  public void setCD_Estado (String estado) {
    CD_Estado = estado;
  }

  public String getCD_Pais () {
    return CD_Pais;
  }

  public void setCD_Pais (String pais) {
    CD_Pais = pais;
  }

  public String getNM_Bairro () {
    return NM_Bairro;
  }

  public void setNM_Bairro (String bairro) {
    NM_Bairro = bairro;
  }

  public String getNR_CEP () {
    return NR_CEP;
  }

  public void setNR_CEP (String nr_cep) {
    NR_CEP = nr_cep;
  }

  public String getNM_Estado () {
    return NM_Estado;
  }

  public void setNM_Estado (String estado) {
    NM_Estado = estado;
  }

  public String getNM_Pais () {
    return NM_Pais;
  }

  public void setNM_Pais (String pais) {
    NM_Pais = pais;
  }

  public String getNM_Cidade_Estado_Pais () {
    return NM_Cidade_Estado_Pais;
  }

  public void setNM_Cidade_Estado_Pais (String cidade_Estado_Pais) {
    NM_Cidade_Estado_Pais = cidade_Estado_Pais;
  }

  public String getNM_Endereco () {
    return NM_Endereco;
  }

  public void setNM_Endereco (String endereco) {
    NM_Endereco = endereco;
  }

  public String getDM_Tipo_Faturamento () {
    return DM_Tipo_Faturamento;
  }

  public void setDM_Tipo_Faturamento (String DM_Tipo_Faturamento) {
    this.DM_Tipo_Faturamento = DM_Tipo_Faturamento;
  }

  public String getDM_Tipo_Cobranca () {
    return DM_Tipo_Cobranca;
  }

  public void setDM_Tipo_Cobranca (String DM_Tipo_Cobranca) {
    this.DM_Tipo_Cobranca = DM_Tipo_Cobranca;
  }

  public String getNR_Conta_Contabil () {
    return NR_Conta_Contabil;
  }

  public void setNR_Conta_Contabil (String NR_Conta_Contabil) {
    this.NR_Conta_Contabil = NR_Conta_Contabil;
  }

  public String getCD_Grupo_Economico () {
    return CD_Grupo_Economico;
  }

  public void setCD_Grupo_Economico (String CD_Grupo_Economico) {
    this.CD_Grupo_Economico = CD_Grupo_Economico;
  }

  public String getDM_Fatura_Terca () {
    return DM_Fatura_Terca;
  }

  public void setDM_Fatura_Terca (String DM_Fatura_Terca) {
    this.DM_Fatura_Terca = DM_Fatura_Terca;
  }

  public String getDM_Condicao_Vencimento () {
    return DM_Condicao_Vencimento;
  }

  public void setDM_Condicao_Vencimento (String DM_Condicao_Vencimento) {
    this.DM_Condicao_Vencimento = DM_Condicao_Vencimento;
  }

  public String getDM_Fatura_Sexta () {
    return DM_Fatura_Sexta;
  }

  public void setDM_Fatura_Sexta (String DM_Fatura_Sexta) {
    this.DM_Fatura_Sexta = DM_Fatura_Sexta;
  }

  public String getDM_Fatura_Segunda () {
    return DM_Fatura_Segunda;
  }

  public void setDM_Fatura_Segunda (String DM_Fatura_Segunda) {
    this.DM_Fatura_Segunda = DM_Fatura_Segunda;
  }

  public String getDM_Fatura_Quarta () {
    return DM_Fatura_Quarta;
  }

  public void setDM_Fatura_Quarta (String DM_Fatura_Quarta) {
    this.DM_Fatura_Quarta = DM_Fatura_Quarta;
  }

  public String getDM_Fatura_Quinta () {
    return DM_Fatura_Quinta;
  }

  public void setDM_Fatura_Quinta (String DM_Fatura_Quinta) {
    this.DM_Fatura_Quinta = DM_Fatura_Quinta;
  }

  public String getNR_CNPJ_CPF () {
    return NR_CNPJ_CPF;
  }

  public void setNR_CNPJ_CPF (String NR_CNPJ_CPF) {
    this.NR_CNPJ_CPF = NR_CNPJ_CPF;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public void setNM_Razao_Social (String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }

  public String getNM_Fantasia () {
    return NM_Fantasia;
  }

  public void setNM_Fantasia (String NM_Fantasia) {
    this.NM_Fantasia = NM_Fantasia;
  }

  public String getNR_CNPJ_CPF_Pagador () {
    return NR_CNPJ_CPF_Pagador;
  }

  public void setNR_CNPJ_CPF_Pagador (String NR_CNPJ_CPF_Pagador) {
    this.NR_CNPJ_CPF_Pagador = NR_CNPJ_CPF_Pagador;
  }

  public String getNM_Razao_Social_Pagador () {
    return NM_Razao_Social_Pagador;
  }

  public void setNM_Razao_Social_Pagador (String NM_Razao_Social_Pagador) {
    this.NM_Razao_Social_Pagador = NM_Razao_Social_Pagador;
  }

  public String getNM_Fantasia_Pagador () {
    return NM_Fantasia_Pagador;
  }

  public void setNM_Fantasia_Pagador (String NM_Fantasia_Pagador) {
    this.NM_Fantasia_Pagador = NM_Fantasia_Pagador;
  }

  public String getNM_Grupo_Economico () {
    return NM_Grupo_Economico;
  }

  public void setNM_Grupo_Economico (String NM_Grupo_Economico) {
    this.NM_Grupo_Economico = NM_Grupo_Economico;
  }

  public String getNM_Ramo_Atividade () {
    return NM_Ramo_Atividade;
  }

  public void setNM_Ramo_Atividade (String NM_Ramo_Atividade) {
    this.NM_Ramo_Atividade = NM_Ramo_Atividade;
  }

  public String getCD_Ramo_Atividade () {
    return CD_Ramo_Atividade;
  }

  public void setCD_Ramo_Atividade (String CD_Ramo_Atividade) {
    this.CD_Ramo_Atividade = CD_Ramo_Atividade;
  }

  public String getDM_Vencimento_Segunda () {
    return DM_Vencimento_Segunda;
  }

  public void setDM_Vencimento_Segunda (String DM_Vencimento_Segunda) {
    this.DM_Vencimento_Segunda = DM_Vencimento_Segunda;
  }

  public String getDM_Vencimento_Terca () {
    return DM_Vencimento_Terca;
  }

  public void setDM_Vencimento_Terca (String DM_Vencimento_Terca) {
    this.DM_Vencimento_Terca = DM_Vencimento_Terca;
  }

  public String getDM_Vencimento_Quarta () {
    return DM_Vencimento_Quarta;
  }

  public void setDM_Vencimento_Quarta (String DM_Vencimento_Quarta) {
    this.DM_Vencimento_Quarta = DM_Vencimento_Quarta;
  }

  public String getDM_Vencimento_Quinta () {
    return DM_Vencimento_Quinta;
  }

  public void setDM_Vencimento_Quinta (String DM_Vencimento_Quinta) {
    this.DM_Vencimento_Quinta = DM_Vencimento_Quinta;
  }

  public String getDM_Vencimento_Sexta () {
    return DM_Vencimento_Sexta;
  }

  public void setDM_Vencimento_Sexta (String DM_Vencimento_Sexta) {
    this.DM_Vencimento_Sexta = DM_Vencimento_Sexta;
  }

  public String getDM_FOB_Dirigido () {
    return DM_FOB_Dirigido;
  }

  public void setDM_FOB_Dirigido (String DM_FOB_Dirigido) {
    this.DM_FOB_Dirigido = DM_FOB_Dirigido;
  }

  public String getDM_Isencao_ICMS () {
    return DM_Isencao_ICMS;
  }

  public void setDM_Isencao_ICMS (String DM_Isencao_ICMS) {
    this.DM_Isencao_ICMS = DM_Isencao_ICMS;
  }

  public String getDM_Isencao_Seguro () {
    return DM_Isencao_Seguro;
  }

  public void setDM_Isencao_Seguro (String DM_Isencao_Seguro) {
    this.DM_Isencao_Seguro = DM_Isencao_Seguro;
  }

  public String getDM_Credito () {
    return DM_Credito;
  }

  public void setDM_Credito (String DM_Credito) {
    this.DM_Credito = DM_Credito;
  }

  public String getDM_Compr_Entrega_Fatura () {
    return DM_Compr_Entrega_Fatura;
  }

  public void setDM_Compr_Entrega_Fatura (String DM_Compr_Entrega_Fatura) {
    this.DM_Compr_Entrega_Fatura = DM_Compr_Entrega_Fatura;
  }

  public String getDM_Tipo_Emissao () {
    return DM_Tipo_Emissao;
  }

  public void setDM_Tipo_Emissao (String DM_Tipo_Emissao) {
    this.DM_Tipo_Emissao = DM_Tipo_Emissao;
  }

  public double getVL_Minimo_Fatura () {
    return VL_Minimo_Fatura;
  }

  public void setVL_Minimo_Fatura (double VL_Minimo_Fatura) {
    this.VL_Minimo_Fatura = VL_Minimo_Fatura;
  }

  public double getVL_Maximo_Fatura () {
    return VL_Maximo_Fatura;
  }

  public void setVL_Maximo_Fatura (double VL_Maximo_Fatura) {
    this.VL_Maximo_Fatura = VL_Maximo_Fatura;
  }

  public double getVL_Limite_Credito () {
    return VL_Limite_Credito;
  }

  public void setVL_Limite_Credito (double VL_Limite_Credito) {
    this.VL_Limite_Credito = VL_Limite_Credito;
  }

  public int getNR_Minimo_CTO_Fatura () {
    return NR_Minimo_CTO_Fatura;
  }

  public void setNR_Minimo_CTO_Fatura (int NR_Minimo_CTO_Fatura) {
    this.NR_Minimo_CTO_Fatura = NR_Minimo_CTO_Fatura;
  }

  public int getNR_Maximo_CTO_Fatura () {
    return NR_Maximo_CTO_Fatura;
  }

  public void setNR_Maximo_CTO_Fatura (int NR_Maximo_CTO_Fatura) {
    this.NR_Maximo_CTO_Fatura = NR_Maximo_CTO_Fatura;
  }

  public double getPE_Juros_Cobranca () {
    return PE_Juros_Cobranca;
  }

  public void setPE_Juros_Cobranca (double PE_Juros_Cobranca) {
    this.PE_Juros_Cobranca = PE_Juros_Cobranca;
  }

  public double getVL_Taxa_Cobranca () {
    return VL_Taxa_Cobranca;
  }

  public void setVL_Taxa_Cobranca (double VL_Taxa_Cobranca) {
    this.VL_Taxa_Cobranca = VL_Taxa_Cobranca;
  }

  public int getNR_Peso_Cubado () {
    return NR_Peso_Cubado;
  }

  public void setNR_Peso_Cubado (int NR_Peso_Cubado) {
    this.NR_Peso_Cubado = NR_Peso_Cubado;
  }

  public int getNR_Dias_Protesto () {
    return NR_Dias_Protesto;
  }

  public void setNR_Dias_Protesto (int NR_Dias_Protesto) {
    this.NR_Dias_Protesto = NR_Dias_Protesto;
  }

  public int getNR_Dias_Vencimento () {
    return NR_Dias_Vencimento;
  }

  public void setNR_Dias_Vencimento (int NR_Dias_Vencimento) {
    this.NR_Dias_Vencimento = NR_Dias_Vencimento;
  }

  public void setCD_Carteira (String cd_Carteira) {
    this.cd_Carteira = cd_Carteira;
  }

  public String getCD_Carteira () {
    return cd_Carteira;
  }

  public void setCD_Conta_Corrente (String CD_Conta_Corrente) {
    this.CD_Conta_Corrente = CD_Conta_Corrente;
  }

  public String getCD_Conta_Corrente () {
    return CD_Conta_Corrente;
  }

  public void setNM_Banco (String NM_Banco) {
    this.NM_Banco = NM_Banco;
  }

  public String getNM_Banco () {
    return NM_Banco;
  }

  public void setNM_Cidade (String NM_Cidade) {
    this.NM_Cidade = NM_Cidade;
  }

  public String getNM_Cidade () {
    return NM_Cidade;
  }

  /*
   * ---------------- Bloco Padr�o para Todas Classes ------------------
   */
  public String getUsuario_Stamp () {
    return Usuario_Stamp;
  }

  public void setUsuario_Stamp (String Usuario_Stamp) {
    this.Usuario_Stamp = Usuario_Stamp;
  }

  public String getDt_Stamp () {
    return Dt_Stamp;
  }

  public void setDt_Stamp (String Dt_Stamp) {
    this.Dt_Stamp = Dt_Stamp;
  }

  public String getDm_Stamp () {
    return Dm_Stamp;
  }

  public void setDm_Stamp (String Dm_Stamp) {
    this.Dm_Stamp = Dm_Stamp;
  }

  public String getOID_Cliente () {
    return oid_Cliente;
  }

  public void setOID_Cliente (String OID_Cliente) {
    this.oid_Cliente = OID_Cliente;
  }

  public String getOID_Pessoa () {
    return oid_Pessoa;
  }

  public void setOID_Pessoa (String OID_Pessoa) {
    this.oid_Pessoa = OID_Pessoa;
  }

  public int getOID_Grupo_Economico () {
    return oid_Grupo_Economico;
  }

  public void setOID_Grupo_Economico (int OID_Grupo_Economico) {
    this.oid_Grupo_Economico = OID_Grupo_Economico;
  }

  public int getOID_Carteira () {
    return oid_Carteira;
  }

  public void setOID_Carteira (int OID_Carteira) {
    this.oid_Carteira = OID_Carteira;
  }

  public int getOID_Ramo_Atividade () {
    return oid_Ramo_Atividade;
  }

  public void setOID_Ramo_Atividade (int OID_Ramo_Atividade) {
    this.oid_Ramo_Atividade = OID_Ramo_Atividade;
  }

  public String getOID_Cliente_Pagador () {
    return oid_Cliente_Pagador;
  }

  public void setOID_Cliente_Pagador (String oid_Cliente_Pagador) {
    this.oid_Cliente_Pagador = oid_Cliente_Pagador;
  }

  public String getOID_Vendedor () {
    return oid_Vendedor;
  }

  public void setOID_Vendedor (String oid_Vendedor) {
    this.oid_Vendedor = oid_Vendedor;
  }

  public void insertByPessoa (String oid_Pessoa) throws Excecoes {
    try {

      ClienteBean Cliente = new ClienteBean ();
      Cliente = ClienteBean.getByOID_Cliente (Parametro_FixoED.getInstancia ().getOID_Cliente_Complemento_Padrao ());
      Cliente.setOID_Pessoa (oid_Pessoa);
      Cliente.setOID_Cliente (oid_Pessoa);
      Cliente.setOID_Cliente_Pagador(oid_Pessoa);
      Cliente.insert ();

    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "insertByPessoa(String oid_Pessoa)");
    }
  }

  // *** Valida C�digo para o Cliente Baseado no M�dulo 10
  public void validaCodigoCliente (String codigo , String delimitador , String oidCliente) throws Exception {
    // *** Verifica se Gera Codigo p/ Cliente
    if (!JavaUtil.doValida (codigo) || !Parametro_FixoED.getInstancia ().isGeraCodigoCliente ()) {
      return;
    }
    if (JavaUtil.doValida (delimitador)) {
      codigo = codigo.replaceAll (delimitador , "");
    }
    String where = "CD_Cliente_Palm = '" + codigo + "'";
    if (JavaUtil.doValida (oidCliente)) {
      where += " AND oid_Cliente <> '" + oidCliente + "'";
    }
    if (util.doExiste ("Clientes" , where)) {
      throw new Mensagens ("C�digo informado ja esxiste!");
    }
  }

  // *** Busca proximo C�digo p/ o Cliente
  public static String getNextCodigoCliente (int oid_Unidade) throws Exception {
    // *** Verifica se Gera Codigo p/ Cliente
    if (!Parametro_FixoED.getInstancia ().isGeraCodigoCliente ()) {
      return "";
    }
    if (oid_Unidade < 1) {
      oid_Unidade = Parametro_FixoED.getInstancia ().getOID_Unidade_Padrao ();
      if (oid_Unidade < 1) {
        throw new Mensagens ("Unidade n�o informada!");
      }
    }
    String codigo = JavaUtil.LFill (Integer.parseInt (JavaUtil.getValueDef (new BancoUtil ().getTableStringValue ("NR_Proximo_Codigo_Cliente" , "Parametros_Filiais" , "oid_Unidade = " + oid_Unidade) ,
                                                                            "0")) + 1 , 5 , true);
    return codigo + "-" + ModuloUtil.getDigitoVerificador (codigo);
  }

  // *** Atualiza C�digo
  public void updateNextCodigoCliente (int oid_Unidade , String codigo , String delimitador) throws Exception {
    // *** Verifica se Gera Codigo p/ Cliente
    if (!JavaUtil.doValida (codigo) || !Parametro_FixoED.getInstancia ().isGeraCodigoCliente ()) {
      return;
    }
    if (oid_Unidade < 1) {
      oid_Unidade = Parametro_FixoED.getInstancia ().getOID_Unidade_Padrao ();
      if (oid_Unidade < 1) {
        throw new Mensagens ("Unidade n�o informada!");
      }
    }
    if (JavaUtil.doValida (delimitador)) {
      codigo = codigo.replaceAll (delimitador , "");
    }
    int nextCode = Integer.parseInt (JavaUtil.LFill (codigo , 5 , true));
    int lastCode = Integer.parseInt (JavaUtil.LFill (JavaUtil.getValueDef (util.getTableStringValue ("NR_Proximo_Codigo_Cliente" , "Parametros_Filiais" , "oid_Unidade = " + oid_Unidade) , 0) , 5 , true));
    if (nextCode > lastCode) {
      new BancoUtil ().executarUpdate (" UPDATE Parametros_Filiais SET" + "      NR_Proximo_Codigo_Cliente = " + JavaUtil.getSQLString (JavaUtil.LFill (codigo , 5 , true)) + " WHERE oid_Unidade = "
                                       + oid_Unidade);
    }
  }

  public void insert () throws Exception {
    // *** Valida Codigo p/ Cliente
    this.validaCodigoCliente (getCD_Cliente_Palm () , "-" , null);
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append (" INSERT INTO Clientes (  ");
    buff.append (" OID_Cliente,            ");
    buff.append (" OID_Cliente_Pagador,    ");
    buff.append (" OID_Pessoa,             ");
    buff.append (" OID_Ramo_Atividade,     ");
    buff.append (" OID_Grupo_Economico,    ");
    buff.append (" OID_Segmento,           ");
    buff.append (" DM_Tipo_Cobranca,       ");
    buff.append (" DM_Condicao_Vencimento, ");
    buff.append (" DM_Fatura_Segunda,      ");
    buff.append (" DM_Fatura_Terca,        ");
    buff.append (" DM_Fatura_Quarta,       ");
    buff.append (" DM_Fatura_Quinta,       ");
    buff.append (" DM_Fatura_Sexta,        ");
    buff.append (" DM_Vencimento_Segunda,  ");
    buff.append (" DM_Vencimento_Terca,    ");
    buff.append (" DM_Vencimento_Quarta,   ");
    buff.append (" DM_Vencimento_Quinta,   ");
    buff.append (" DM_Vencimento_Sexta,    ");
    buff.append (" DM_FOB_Dirigido,        ");
    buff.append (" DM_Isencao_ICMS,        ");
    buff.append (" DM_Isencao_Seguro,      ");
    buff.append (" DM_Credito,             ");
    buff.append (" NR_Conta_Contabil,      ");
    buff.append (" DM_Compr_Entrega_Fatura,");
    buff.append (" DM_Tipo_Emissao,        ");
    buff.append (" VL_Minimo_Fatura,       ");
    buff.append (" VL_Maximo_Fatura,       ");
    buff.append (" VL_Limite_Credito,      ");
    buff.append (" NR_Minimo_CTO_Fatura,   ");
    buff.append (" NR_Maximo_CTO_Fatura,   ");
    buff.append (" PE_Juros_Cobranca,      ");
    buff.append (" VL_Taxa_Cobranca,       ");
    buff.append (" NR_Peso_Cubado,         ");
    buff.append (" NR_Dias_Vencimento,     ");
    buff.append (" NR_Dias_Protesto,       ");
    buff.append (" DT_Stamp,               ");
    buff.append (" DM_Stamp,               ");
    buff.append (" Usuario_Stamp,          ");
    buff.append (" OID_Vendedor,           ");
    buff.append (" OID_Carteira,           ");
    buff.append (" PE_Comissao ,           ");
    buff.append (" PE_PIS_COFINS,          ");
    buff.append (" DM_PIS_COFINS,          ");
    buff.append (" DM_Tipo_Faturamento,    ");
    buff.append (" NR_QT_Notas_Fiscais,    ");
    buff.append (" VL_Limite_Credito_Adicional, ");
    buff.append (" VL_Minimo_Compra,    	  ");
    buff.append (" NR_Duplicatas_Vencidas, ");
    buff.append (" DT_Cadastro,    		  ");
    buff.append (" DT_Validade_Cadastro,   ");
    buff.append (" VL_Taxa_Despacho,       ");
    buff.append (" DM_RCTRC,               ");
    buff.append (" DM_RCTR_VI,             ");
    buff.append (" DM_RCTA,                ");
    buff.append (" DM_RCTR_DC,             ");
    buff.append (" CD_Cliente_Palm,        ");
    buff.append (" oid_Condicao_Pagamento, ");
    buff.append (" DM_Forma_Pagamento,     ");
    buff.append (" OID_Conta,              ");
    buff.append (" OID_Conta_Debito,       ");
    buff.append (" PE_Base_Comissao_Motorista,");
    buff.append (" oid_Produto,            ");
    buff.append (" DT_Alteracao,           ");
    buff.append (" DM_Monitoramento,       ");
    buff.append (" DM_Resp_Vendedor,       ");
    buff.append (" OID_Modal,             ");
    buff.append (" DM_Wms,              ");
    buff.append (" DM_Inclui_ICMS_Parcela_CTRC,              ");
    buff.append (" DM_Carregamento,              ");
    buff.append (" DM_ICMS_Wms,              ");
    buff.append (" NR_Dias_Bloqueio,       ");
    buff.append (" NR_Dias_Contagem_Ciclica)       ");
    buff.append (" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getOID_Cliente ());
      pstmt.setString (2 , getOID_Cliente_Pagador ());
      pstmt.setString (3 , getOID_Pessoa ());
      pstmt.setInt (4 , getOID_Ramo_Atividade ());
      pstmt.setInt (5 , getOID_Grupo_Economico ());
      pstmt.setInt (6 , getOID_Segmento ());
      // *** Se Cliente for Pessoa F�sica, Cobran�a somente A VISTA('V')
      if (getOID_Cliente ().length () < 14) {
        pstmt.setString (7 , "V");
      }
      else {
        pstmt.setString (7 , getDM_Tipo_Cobranca ());
      }
      pstmt.setString (8 , getDM_Condicao_Vencimento ());
      pstmt.setString (9 , getDM_Fatura_Segunda ());
      pstmt.setString (10 , getDM_Fatura_Terca ());
      pstmt.setString (11 , getDM_Fatura_Quarta ());
      pstmt.setString (12 , getDM_Fatura_Quinta ());
      pstmt.setString (13 , getDM_Fatura_Sexta ());
      pstmt.setString (14 , getDM_Vencimento_Segunda ());
      pstmt.setString (15 , getDM_Vencimento_Terca ());
      pstmt.setString (16 , getDM_Vencimento_Quarta ());
      pstmt.setString (17 , getDM_Vencimento_Quinta ());
      pstmt.setString (18 , getDM_Vencimento_Sexta ());
      pstmt.setString (19 , getDM_FOB_Dirigido ());
      pstmt.setString (20 , getDM_Isencao_ICMS ());
      pstmt.setString (21 , getDM_Isencao_Seguro ());
      pstmt.setString (22 , getDM_Credito ());
      pstmt.setString (23 , getNR_Conta_Contabil ());
      pstmt.setString (24 , getDM_Compr_Entrega_Fatura ());
      pstmt.setString (25 , getDM_Tipo_Emissao ());
      pstmt.setDouble (26 , getVL_Minimo_Fatura ());
      pstmt.setDouble (27 , getVL_Maximo_Fatura ());
      pstmt.setDouble (28 , getVL_Limite_Credito ());
      pstmt.setInt (29 , getNR_Minimo_CTO_Fatura ());
      pstmt.setInt (30 , getNR_Maximo_CTO_Fatura ());
      pstmt.setDouble (31 , getPE_Juros_Cobranca ());
      pstmt.setDouble (32 , getVL_Taxa_Cobranca ());
      pstmt.setInt (33 , getNR_Peso_Cubado ());
      pstmt.setInt (34 , getNR_Dias_Vencimento ());
      pstmt.setInt (35 , getNR_Dias_Protesto ());
      pstmt.setDate (36 , FormataData.formataDataTB (getDt_Stamp ()));
      pstmt.setString (37 , getUsuario_Stamp ());
      pstmt.setString (38 , Data.getDataDMY ());
      pstmt.setString (39 , getOID_Vendedor ());
      pstmt.setInt (40 , getOID_Carteira ());
      pstmt.setDouble (41 , getPE_Comissao ());
      pstmt.setDouble (42 , getPE_PIS_COFINS ());
      pstmt.setString (43 , getDM_PIS_COFINS ());
      pstmt.setString (44 , getDM_Tipo_Faturamento ());
      pstmt.setInt (45 , getNR_QT_Notas_Fiscais ());
      pstmt.setDouble (46 , getVL_Limite_Credito_Adicional ());
      pstmt.setDouble (47 , getVL_Minimo_Compra ());
      pstmt.setInt (48 , getNR_Duplicatas_Vencidas ());
      pstmt.setString (49 , JavaUtil.getValueDef (getDT_Cadastro () , Data.getDataDMY ()));
      pstmt.setString (50 , JavaUtil.getValueDef (getDT_Validade_Cadastro () , null));
      pstmt.setDouble (51 , getVL_Taxa_Despacho ());
      pstmt.setString (52 , JavaUtil.getValueDef (getDM_RCTRC () , "N"));
      pstmt.setString (53 , JavaUtil.getValueDef (getDM_RCTR_VI () , "N"));
      pstmt.setString (54 , JavaUtil.getValueDef (getDM_RCTA () , "N"));
      pstmt.setString (55 , JavaUtil.getValueDef (getDM_RCTR_DC () , "N"));
      pstmt.setString (56 , getCD_Cliente_Palm ().replaceAll ("-" , ""));
      pstmt.setInt (57 , getOid_Condicao_Pagamento ());
      pstmt.setString (58 , JavaUtil.getValueDef (getDM_Forma_Pagamento () , ""));
      pstmt.setInt (59 , getOid_Conta ());
      pstmt.setInt (60 , getOid_Conta_Debito ());
      pstmt.setDouble (61 , getPE_Base_Comissao_Motorista ());
      pstmt.setInt (62 , getOid_Produto ());
      pstmt.setString (63 , JavaUtil.getValueDef (getDT_Cadastro () , Data.getDataDMY ()));
      pstmt.setString (64 , JavaUtil.getValueDef (getDM_Monitoramento () , "N"));
      pstmt.setString (65 , JavaUtil.getValueDef (getDM_Resp_Vendedor () , "N"));
      pstmt.setInt (66 , getOID_Modal ());
      pstmt.setString (67 ,"N");
      pstmt.setString (68 , getDM_Inclui_ICMS_Parcela_CTRC ());
      pstmt.setString (69 , "N");
      pstmt.setString (70 , "N");
      pstmt.setInt (71 , 30);
      pstmt.setInt (72 , 0);

      pstmt.executeUpdate ();
      // *** Atualiza Codigo nos parametros
      this.updateNextCodigoCliente (getOID_Unidade_Operacional () , getCD_Cliente_Palm () , "-");
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void update () throws Exception {
    // *** Valida Codigo p/ Cliente
    this.validaCodigoCliente (getCD_Cliente_Palm () , "-" , getOID_Cliente ());
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Clientes SET ");
    buff.append (" OID_Cliente_Pagador=?,    ");
    buff.append (" OID_Ramo_Atividade=?,     ");
    buff.append (" OID_Grupo_Economico=?,    ");
    buff.append (" OID_Segmento=?,           ");
    buff.append (" DM_Fatura_Segunda=?,      ");
    buff.append (" DM_Fatura_Terca=?,        ");
    buff.append (" DM_Fatura_Quarta=?,       ");
    buff.append (" DM_Fatura_Quinta=?,       ");
    buff.append (" DM_Fatura_Sexta=?,        ");
    buff.append (" DM_Vencimento_Segunda=?,  ");
    buff.append (" DM_Vencimento_Terca=?,    ");
    buff.append (" DM_Vencimento_Quarta=?,   ");
    buff.append (" DM_Vencimento_Quinta=?,   ");
    buff.append (" DM_Vencimento_Sexta=?,    ");
    buff.append (" DM_FOB_Dirigido=?,        ");
    buff.append (" DM_Isencao_ICMS=?,        ");
    buff.append (" DM_Isencao_Seguro=?,      ");
    buff.append (" NR_Conta_Contabil=?,      ");
    buff.append (" DM_Compr_Entrega_Fatura=?,");
    buff.append (" DM_Tipo_Emissao=?,        ");
    buff.append (" VL_Minimo_Fatura=?,       ");
    buff.append (" VL_Maximo_Fatura=?,       ");
    buff.append (" NR_Minimo_CTO_Fatura=?,   ");
    buff.append (" NR_Maximo_CTO_Fatura=?,   ");
    buff.append (" PE_Juros_Cobranca=?,      ");
    buff.append (" VL_Taxa_Cobranca=?,       ");
    buff.append (" NR_Peso_Cubado=?,         ");
    buff.append (" DT_Stamp=?,               ");
    buff.append (" DM_Stamp=?,               ");
    buff.append (" Usuario_Stamp=?,          ");
    buff.append (" PE_PIS_COFINS=?,          ");
    buff.append (" DM_PIS_COFINS=?,          ");
    buff.append (" PE_Comissao=?,            ");
    buff.append (" OID_Vendedor=?,           ");
    buff.append (" NR_QT_Notas_Fiscais=?,    ");
    buff.append (" VL_Taxa_Despacho=?,    	");
    buff.append (" oid_unidade_operacional=?,");
    buff.append (" dm_situacao=?,    		");
    buff.append (" tx_instrucao_cobranca=?,  ");
    buff.append (" dm_tracking=?,    		");
    buff.append (" oid_pessoa_parceiro=?,  	");
    buff.append (" DT_validade_cadastro=?,   ");
    buff.append (" oid_Produto=?,            ");
    buff.append (" DM_Tipo_Cobranca=?,       ");
    buff.append (" DM_Tipo_Faturamento=?,    ");
    buff.append (" DM_RCTRC=?,               ");
    buff.append (" DM_RCTR_VI=?,             ");
    buff.append (" DM_RCTA=?,                ");
    buff.append (" DM_RCTR_DC=?,             ");
    buff.append (" CD_Cliente_Palm=?,        ");
    buff.append (" oid_Condicao_Pagamento=?, ");
    buff.append (" oid_Conta=?,              ");
    buff.append (" oid_Conta_Debito=?,       ");
    buff.append (" DM_Forma_Pagamento=?,      ");
    buff.append (" DM_Resp_Vendedor=?,      ");
    buff.append (" OID_Modal=?     			");
    buff.append (" WHERE OID_Cliente=?		");

    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getOID_Cliente_Pagador ());
      pstmt.setInt (2 , getOID_Ramo_Atividade ());
      pstmt.setInt (3 , getOID_Grupo_Economico ());
      pstmt.setInt (4 , getOID_Segmento ());
      pstmt.setString (5 , getDM_Fatura_Segunda ());
      pstmt.setString (6 , getDM_Fatura_Terca ());
      pstmt.setString (7 , getDM_Fatura_Quarta ());
      pstmt.setString (8 , getDM_Fatura_Quinta ());
      pstmt.setString (9 , getDM_Fatura_Sexta ());
      pstmt.setString (10 , getDM_Vencimento_Segunda ());
      pstmt.setString (11 , getDM_Vencimento_Terca ());
      pstmt.setString (12 , getDM_Vencimento_Quarta ());
      pstmt.setString (13 , getDM_Vencimento_Quinta ());
      pstmt.setString (14 , getDM_Vencimento_Sexta ());
      pstmt.setString (15 , getDM_FOB_Dirigido ());
      pstmt.setString (16 , getDM_Isencao_ICMS ());
      pstmt.setString (17 , getDM_Isencao_Seguro ());
      pstmt.setString (18 , getNR_Conta_Contabil ());
      pstmt.setString (19 , getDM_Compr_Entrega_Fatura ());
      pstmt.setString (20 , getDM_Tipo_Emissao ());
      pstmt.setDouble (21 , getVL_Minimo_Fatura ());
      pstmt.setDouble (22 , getVL_Maximo_Fatura ());
      pstmt.setInt (23 , getNR_Minimo_CTO_Fatura ());
      pstmt.setInt (24 , getNR_Maximo_CTO_Fatura ());
      pstmt.setDouble (25 , getPE_Juros_Cobranca ());
      pstmt.setDouble (26 , getVL_Taxa_Cobranca ());
      pstmt.setInt (27 , getNR_Peso_Cubado ());
      pstmt.setString (28 , getDt_Stamp ());
      pstmt.setString (29 , getUsuario_Stamp ());
      pstmt.setString (30 , getDm_Stamp ());
      pstmt.setDouble (31 , getPE_PIS_COFINS ());
      pstmt.setString (32 , getDM_PIS_COFINS ());
      pstmt.setDouble (33 , getPE_Comissao ());
      pstmt.setString (34 , getOID_Vendedor ());
      pstmt.setInt (35 , getNR_QT_Notas_Fiscais ());
      pstmt.setDouble (36 , getVL_Taxa_Despacho ());
      pstmt.setInt (37 , getOID_Unidade_Operacional ());
      pstmt.setString (38 , getDM_Situacao ());
      pstmt.setString (39 , getTX_Instrucao_Cobranca ());
      pstmt.setString (40 , getDM_Tracking ());
      pstmt.setString (41 , getOid_Pessoa_Parceiro ());
      if (!"".equals (getDT_Validade_Cadastro ())) {
        pstmt.setString (42 , getDT_Validade_Cadastro ());
      }
      else {
        pstmt.setString (42 , null);
      }
      pstmt.setInt (43 , getOid_Produto ());
      pstmt.setString (44 , getDM_Tipo_Cobranca ());
      pstmt.setString (45 , getDM_Tipo_Faturamento ());
      pstmt.setString (46 , getDM_RCTRC ());
      pstmt.setString (47 , getDM_RCTR_VI ());
      pstmt.setString (48 , getDM_RCTA ());
      pstmt.setString (49 , getDM_RCTR_DC ());
      pstmt.setString (50 , getCD_Cliente_Palm ().replaceAll ("-" , ""));
      pstmt.setInt (51 , getOid_Condicao_Pagamento ());
      pstmt.setInt (52 , getOid_Conta ());
      pstmt.setInt (53 , getOid_Conta_Debito ());
      pstmt.setString (54 , JavaUtil.getValueDef (getDM_Forma_Pagamento () , ""));
      pstmt.setString (55 , JavaUtil.getValueDef (getDM_Resp_Vendedor () , "N"));
      pstmt.setInt (56 , getOID_Modal ());
      pstmt.setString (57 , getOID_Cliente ());
      pstmt.executeUpdate ();
      // *** Atualiza Codigo nos parametros
      this.updateNextCodigoCliente (getOID_Unidade_Operacional () , getCD_Cliente_Palm () , "-");
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void update_Resumido () throws Exception {
    // *** Valida Codigo p/ Cliente
    this.validaCodigoCliente (getCD_Cliente_Palm () , "-" , getOID_Cliente ());
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Clientes SET 		");
    buff.append (" OID_Cliente_Pagador=?,    ");
    buff.append (" OID_Ramo_Atividade=?,     ");
    buff.append (" OID_Grupo_Economico=?,    ");
    buff.append (" OID_Segmento=?,           ");
    buff.append (" NR_Conta_Contabil=?,      ");
    buff.append (" DT_Stamp=?,               ");
    buff.append (" PE_Comissao=?,            ");
    buff.append (" OID_Vendedor=?,           ");
    buff.append (" oid_unidade_operacional=?,");
    buff.append (" oid_pessoa_parceiro=?,    ");
    buff.append (" DT_Validade_Cadastro=?,   ");
    buff.append (" CD_Cliente_Palm=?,        ");
    buff.append (" oid_Conta=?,              ");
    buff.append (" oid_Conta_Debito=?,       ");
    buff.append (" oid_Produto=?,            ");
    buff.append (" OID_Modal=?,              ");
    buff.append (" CD_Acesso=?,            	 ");
    buff.append (" NR_Dias_Bloqueio=?        ");
    buff.append (" WHERE OID_Cliente=?       ");
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getOID_Cliente_Pagador ());
      pstmt.setInt (2 , getOID_Ramo_Atividade ());
      pstmt.setInt (3 , getOID_Grupo_Economico ());
      pstmt.setInt (4 , getOID_Segmento ());
      pstmt.setString (5 , getNR_Conta_Contabil ());
      pstmt.setString (6 , getDt_Stamp ());
      pstmt.setDouble (7 , getPE_Comissao ());
      pstmt.setString (8 , getOID_Vendedor ());
      pstmt.setInt (9 , getOID_Unidade_Operacional ());
      pstmt.setString (10 , getOid_Pessoa_Parceiro ());
      if (!"".equals (getDT_Validade_Cadastro ())) {
        pstmt.setString (11 , getDT_Validade_Cadastro ());
      }
      else {
        pstmt.setString (11 , null);
      }
      pstmt.setString (12 , getCD_Cliente_Palm ().replaceAll ("-" , ""));

      pstmt.setInt (13 , getOid_Conta ());
      pstmt.setInt (14 , getOid_Conta_Debito ());
      pstmt.setInt (15 , getOid_Produto ());
      pstmt.setInt (16 , getOID_Modal ());
      pstmt.setString (17 , getCD_Acesso ());
      pstmt.setInt (18 , this.getNR_Dias_Bloqueio());
      pstmt.setString (19 , getOID_Cliente ());
      pstmt.executeUpdate ();
      // *** Atualiza Codigo nos parametros
      this.updateNextCodigoCliente (getOID_Unidade_Operacional () , getCD_Cliente_Palm () , "-");
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void update_Credito () throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Clientes SET ");
    buff.append (" NR_Dias_Protesto=?,       ");
    buff.append (" NR_Duplicatas_Vencidas=?, ");
    buff.append (" VL_Minimo_Fatura=?,       ");
    buff.append (" VL_Maximo_Fatura=?,       ");
    buff.append (" VL_Limite_Credito=?,      ");
    buff.append (" VL_Limite_Credito_Adicional=?, ");
    buff.append (" VL_Minimo_Compra=?,       ");
    buff.append (" PE_Juros_Cobranca=?,      ");
    buff.append (" VL_Taxa_Cobranca=?,       ");
    buff.append (" TX_Instrucao_Cobranca=?,  ");
    buff.append (" DM_Credito=?,             ");
    buff.append (" oid_Condicao_Pagamento=?, ");
    buff.append (" DM_Forma_Pagamento=?,     ");
    buff.append (" DM_Tipo_Faturamento=?,    ");
    buff.append (" DT_Alteracao=?,           ");
    buff.append (" DT_Desativado=?,          ");
    buff.append (" DM_Resp_Vendedor=?        ");
    buff.append (" WHERE OID_Cliente=?       ");
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getNR_Dias_Protesto ());
      pstmt.setInt (2 , getNR_Duplicatas_Vencidas ());
      pstmt.setDouble (3 , getVL_Minimo_Fatura ());
      pstmt.setDouble (4 , getVL_Maximo_Fatura ());
      pstmt.setDouble (5 , getVL_Limite_Credito ());
      pstmt.setDouble (6 , getVL_Limite_Credito_Adicional ());
      pstmt.setDouble (7 , getVL_Minimo_Compra ());
      pstmt.setDouble (8 , getPE_Juros_Cobranca ());
      pstmt.setDouble (9 , getVL_Taxa_Cobranca ());
      pstmt.setString (10 , getTX_Instrucao_Cobranca ());
      pstmt.setString (11 , getDM_Credito ());
      pstmt.setInt (12 , getOid_Condicao_Pagamento ());
      pstmt.setString (13 , JavaUtil.getValueDef (getDM_Forma_Pagamento () , ""));
      pstmt.setString (14 , JavaUtil.getValueDef (getDM_Tipo_Faturamento () , "1"));
      pstmt.setString (15 , JavaUtil.getValueDef (getDT_Alteracao () , Data.getDataDMY ()));
      pstmt.setString (16 , JavaUtil.getValueDef (getDT_Desativado () , null));
      pstmt.setString (17 , JavaUtil.getValueDef (getDM_Resp_Vendedor () , "N"));
      pstmt.setString (18 , getOID_Cliente ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void update_Frete () throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Clientes SET ");
    buff.append (" DM_FOB_Dirigido=?,        ");
    buff.append (" DM_Isencao_ICMS=?,        ");
    buff.append (" DM_Isencao_Seguro=?,      ");
    buff.append (" NR_QT_Notas_Fiscais=?,    ");
    buff.append (" NR_Peso_Cubado=?,         ");
    buff.append (" PE_PIS_COFINS=?,          ");
    buff.append (" DM_PIS_COFINS=?,          ");
    buff.append (" VL_Taxa_Despacho=?,       ");
    buff.append (" DM_Situacao=?,            ");
    buff.append (" DM_Tracking=?,            ");
    buff.append (" DM_RCTRC=?,               ");
    buff.append (" DM_RCTR_VI=?,             ");
    buff.append (" DM_RCTA=?,                ");
    buff.append (" DM_RCTR_DC=?,             ");
    buff.append (" DM_Monitoramento=?,       ");
    buff.append (" VL_Taxa_Coleta=?,       ");
    buff.append (" VL_Taxa_Entrega=?,       ");
    buff.append (" VL_Custo_Carga=?,       ");
    buff.append (" VL_Custo_Descarga=?,       ");
    buff.append (" PE_Base_Comissao_Motorista=?, ");
    buff.append (" DM_Inclui_ICMS_Parcela_CTRC=? ");
    buff.append (" WHERE OID_Cliente=?       ");
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getDM_FOB_Dirigido ());
      pstmt.setString (2 , getDM_Isencao_ICMS ());
      pstmt.setString (3 , getDM_Isencao_Seguro ());
      pstmt.setInt (4 , getNR_QT_Notas_Fiscais ());
      pstmt.setInt (5 , getNR_Peso_Cubado ());
      pstmt.setDouble (6 , getPE_PIS_COFINS ());
      pstmt.setString (7 , getDM_PIS_COFINS ());
      pstmt.setDouble (8 , getVL_Taxa_Despacho ());
      pstmt.setString (9 , getDM_Situacao ());
      pstmt.setString (10 , getDM_Tracking ());
      pstmt.setString (11 , getDM_RCTRC ());
      pstmt.setString (12 , getDM_RCTR_VI ());
      pstmt.setString (13 , getDM_RCTA ());
      pstmt.setString (14 , getDM_RCTR_DC ());
      pstmt.setString (15 , getDM_Monitoramento ());
      pstmt.setDouble (16 , getVL_Taxa_Coleta ());
      pstmt.setDouble (17 , getVL_Taxa_Entrega ());
      pstmt.setDouble (18 , getVL_Custo_Carga ());
      pstmt.setDouble (19 , getVL_Custo_Descarga ());
      pstmt.setDouble (20 , getPE_Base_Comissao_Motorista ());
      pstmt.setString (21 , getDM_Inclui_ICMS_Parcela_CTRC ());
      pstmt.setString (22 , getOID_Cliente ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void update_WMS () throws Exception {
	    Connection conn = null;
	    try {
	      conn = OracleConnection2.getWEB ();
	      conn.setAutoCommit (false);
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw e;
	    }
	    StringBuffer buff = new StringBuffer ();
	    buff.append ("UPDATE Clientes SET ");
	    buff.append (" DM_Carregamento=?,        ");
	    buff.append (" DM_ICMS_WMS=?,        ");
	    buff.append (" DM_WMS=?,      ");
	    buff.append (" VL_Custo_Carga=?,       ");
	    buff.append (" VL_Custo_Descarga=?,        ");
	    buff.append (" NR_Dias_Contagem_Ciclica=?        ");
	    buff.append (" WHERE OID_Cliente=?       ");
	    try {
	      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
	      pstmt.setString (1 , getDM_Carregamento ());
	      pstmt.setString (2 , getDM_ICMS_WMS ());
	      pstmt.setString (3 , getDM_Wms ());
	      pstmt.setDouble (4 , getVL_Custo_Carga ());
	      pstmt.setDouble (5 , getVL_Custo_Descarga ());
	      pstmt.setInt (6 , getNR_Dias_Contagem_Ciclica ());
	      pstmt.setString (7 , getOID_Cliente ());
	      pstmt.executeUpdate ();
	    }
	    catch (Exception e) {
	      conn.rollback ();
	      e.printStackTrace ();
	      throw e;
	    }
	    try {
	      conn.commit ();
	      conn.close ();
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw e;
	    }
	  }

  public void update_Servico () throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Clientes SET ");
    buff.append (" PE_ISSQN=?,          ");
    buff.append (" DM_ISSQN=?           ");
    buff.append (" WHERE OID_Cliente=?       ");
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setDouble (1 , getPE_ISSQN ());
      pstmt.setString (2 , getDM_ISSQN ());
      pstmt.setString (3 , getOID_Cliente ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void update_Faturamento_Frete () throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Clientes SET ");
    buff.append (" DM_Fatura_Segunda=?,      ");
    buff.append (" DM_Fatura_Terca=?,        ");
    buff.append (" DM_Fatura_Quarta=?,       ");
    buff.append (" DM_Fatura_Quinta=?,       ");
    buff.append (" DM_Fatura_Sexta=?,        ");
    buff.append (" DM_Vencimento_Segunda=?,  ");
    buff.append (" DM_Vencimento_Terca=?,    ");
    buff.append (" DM_Vencimento_Quarta=?,   ");
    buff.append (" DM_Vencimento_Quinta=?,   ");
    buff.append (" DM_Vencimento_Sexta=?,    ");
    buff.append (" DM_Compr_Entrega_Fatura=?,");
    buff.append (" DM_Tipo_Emissao=?,        ");
    buff.append (" VL_Minimo_Fatura=?,       ");
    buff.append (" VL_Maximo_Fatura=?,       ");
    buff.append (" NR_Minimo_CTO_Fatura=?,   ");
    buff.append (" NR_Maximo_CTO_Fatura=?,   ");
    buff.append (" DM_Condicao_Vencimento=?, ");
    buff.append (" NR_Dias_Vencimento=?,     ");
    buff.append (" DM_Tipo_Cobranca=?,       ");
    buff.append (" PE_Desconto_Vencimento=?,      ");
    buff.append (" DM_Tipo_Faturamento=?,     ");
    buff.append (" DM_Faturamento=?     ");
    buff.append (" WHERE OID_Cliente=?       ");
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getDM_Fatura_Segunda ());
      pstmt.setString (2 , getDM_Fatura_Terca ());
      pstmt.setString (3 , getDM_Fatura_Quarta ());
      pstmt.setString (4 , getDM_Fatura_Quinta ());
      pstmt.setString (5 , getDM_Fatura_Sexta ());
      pstmt.setString (6 , getDM_Vencimento_Segunda ());
      pstmt.setString (7 , getDM_Vencimento_Terca ());
      pstmt.setString (8 , getDM_Vencimento_Quarta ());
      pstmt.setString (9 , getDM_Vencimento_Quinta ());
      pstmt.setString (10 , getDM_Vencimento_Sexta ());
      pstmt.setString (11 , getDM_Compr_Entrega_Fatura ());
      pstmt.setString (12 , getDM_Tipo_Emissao ());
      pstmt.setDouble (13 , getVL_Minimo_Fatura ());
      pstmt.setDouble (14 , getVL_Maximo_Fatura ());
      pstmt.setInt (15 , getNR_Minimo_CTO_Fatura ());
      pstmt.setInt (16 , getNR_Maximo_CTO_Fatura ());
      pstmt.setString (17 , getDM_Condicao_Vencimento ());
      pstmt.setInt (18 , getNR_Dias_Vencimento ());
      pstmt.setString (19 , getDM_Tipo_Cobranca ());
      pstmt.setDouble (20 , getPE_Desconto_Vencimento ());
      pstmt.setString (21 , getDM_Tipo_Faturamento ());
      pstmt.setString (22 , getDM_Faturamento ());
      pstmt.setString (23 , getOID_Cliente ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  // /### Transito
  public void update_cobranca () throws Exception {
    /*
     * Abre a conex�o com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conex�o ao gerenciador do driver
      // passando como par�metro o NM_Tipo_Documento do DSN
      // o NM_Tipo_Documento de usu�rio e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Define o update.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Clientes SET ");
    buff.append (" DM_Tipo_Cobranca=?,       ");
    buff.append (" DM_Condicao_Vencimento=?, ");
    buff.append (" DM_Credito=?,             ");
    buff.append (" VL_Limite_Credito=?,      ");
    buff.append (" NR_Dias_Vencimento=?,     ");
    buff.append (" NR_Dias_Protesto=?,       ");
    buff.append (" DT_Stamp=?,               ");
    buff.append (" DM_Stamp=?,               ");
    buff.append (" Usuario_Stamp=?,          ");
    buff.append (" OID_Carteira=?,           ");
    buff.append (" DM_Tipo_Faturamento=?,    ");
    buff.append (" NR_Minimo_CTO_Fatura=?,   ");
    buff.append (" NR_Maximo_CTO_Fatura=?,   ");
    buff.append (" VL_Minimo_Fatura=?,       ");
    buff.append (" VL_Maximo_Fatura=?,       ");
    buff.append (" VL_Taxa_Cobranca=?,       ");
    buff.append (" DM_Vencimento_Segunda=?,  ");
    buff.append (" DM_Vencimento_Terca=?,    ");
    buff.append (" DM_Vencimento_Quarta=?,   ");
    buff.append (" DM_Vencimento_Quinta=?,   ");
    buff.append (" DM_Vencimento_Sexta=?,    ");
    buff.append (" DM_Fatura_Segunda=?,      ");
    buff.append (" DM_Fatura_Terca=?,        ");
    buff.append (" DM_Fatura_Quarta=?,       ");
    buff.append (" DM_Fatura_Quinta=?,       ");
    buff.append (" DM_Fatura_Sexta=?,        ");
    buff.append (" VL_Limite_Credito_Adicional=?, ");
    buff.append (" VL_Minimo_Compra=?,    	 ");
    buff.append (" NR_Duplicatas_Vencidas=?,  ");
    buff.append (" OID_Cliente_Pagador=?,      ");
    buff.append (" NM_Contato_Cobranca=?,        ");
    buff.append (" DM_Tipo_Emissao=?        ");
    buff.append (" WHERE OID_Cliente=?");
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getDM_Tipo_Cobranca ());
      pstmt.setString (2 , getDM_Condicao_Vencimento ());
      pstmt.setString (3 , getDM_Credito ());
      pstmt.setDouble (4 , getVL_Limite_Credito ());
      pstmt.setInt (5 , getNR_Dias_Vencimento ());
      pstmt.setInt (6 , getNR_Dias_Protesto ());
      pstmt.setString (7 , getDt_Stamp ());
      pstmt.setString (8 , getUsuario_Stamp ());
      pstmt.setString (9 , getDm_Stamp ());
      pstmt.setInt (10 , getOID_Carteira ());
      pstmt.setString (11 , getDM_Tipo_Faturamento ());
      pstmt.setInt (12 , getNR_Minimo_CTO_Fatura ());
      pstmt.setInt (13 , getNR_Maximo_CTO_Fatura ());
      pstmt.setDouble (14 , getVL_Minimo_Fatura ());
      pstmt.setInt (13 , getNR_Maximo_CTO_Fatura ());
      pstmt.setDouble (15 , getVL_Maximo_Fatura ());
      pstmt.setDouble (16 , getVL_Taxa_Cobranca ());
      pstmt.setString (17 , getDM_Vencimento_Segunda ());
      pstmt.setString (18 , getDM_Vencimento_Terca ());
      pstmt.setString (19 , getDM_Vencimento_Quarta ());
      pstmt.setString (20 , getDM_Vencimento_Quinta ());
      pstmt.setString (21 , getDM_Vencimento_Sexta ());
      pstmt.setString (22 , getDM_Fatura_Segunda ());
      pstmt.setString (23 , getDM_Fatura_Terca ());
      pstmt.setString (24 , getDM_Fatura_Quarta ());
      pstmt.setString (25 , getDM_Fatura_Quinta ());
      pstmt.setString (26 , getDM_Fatura_Sexta ());
      pstmt.setDouble (27 , getVL_Limite_Credito_Adicional ());
      pstmt.setDouble (28 , getVL_Minimo_Compra ());
      pstmt.setInt (29 , getNR_Duplicatas_Vencidas ());
      pstmt.setString (30 , getOID_Cliente_Pagador ());
      pstmt.setString (31 , getNM_Contato_Cobranca ());
      pstmt.setString (32 , getDM_Tipo_Emissao ());
      pstmt.setString (33 , getOID_Cliente ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conex�o.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public static String update_Movimentacao(String oid_cliente) throws Exception {
	    Connection conn = null;
	    try {
	      conn = OracleConnection2.getWEB ();
	      conn.setAutoCommit (false);
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw e;
	    }

	    String data = "01/01/2001";
	    try{
	    	String consulta = "SELECT conhecimentos.dt_emissao " +
	    					  " from conhecimentos, movimentos_conhecimentos, tabelas_fretes " +
	    					  " where conhecimentos.oid_Conhecimento = movimentos_conhecimentos.oid_Conhecimento " +
	    					  " and movimentos_conhecimentos.oid_tabela_frete = tabelas_fretes.oid_tabela_frete " +
	    					  " and conhecimentos.oid_pessoa_pagador = '"+oid_cliente+"' " +
	    					  " and tabelas_Fretes.oid_pessoa = '"+oid_cliente+"' " +
					  		  " and conhecimentos.dm_impresso = 'S' " +
					  		  " and conhecimentos.vl_total_frete > 0 " +
					  		  " order by conhecimentos.dt_emissao desc limit 1";
	        ResultSet rs = new BancoUtil().executarConsulta(consulta);

	        while(rs.next()){
	        	data = FormataData.formataDataBT(rs.getString(1));
	        }

	    } catch (Exception e){
	    	e.printStackTrace();
	    	throw e;
	    }

	    StringBuffer buff = new StringBuffer ();
	    buff.append ("UPDATE Clientes SET ");
	    buff.append (" DT_Ultimo_Movimento=?           ");
	    buff.append (" WHERE OID_Cliente=?       ");
	    try {
	      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
	      pstmt.setString(1 , data);
	      pstmt.setString(2 , oid_cliente);
	      pstmt.executeUpdate ();
	    }
	    catch (Exception e) {
	      conn.rollback ();
	      e.printStackTrace ();
	      throw e;
	    }
	    try {
	      conn.commit ();
	      conn.close ();
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw e;
	    }

	    return data;
	  }

  public static void update_Movimentacao_total() throws Exception {
	    Connection conn = null;
	    try {
	      conn = OracleConnection2.getWEB ();
	      conn.setAutoCommit (false);
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw e;
	    }
	    Iterator it = getAll().iterator();
	    while(it.hasNext())
	    {
	    	ClienteBean cliente = (ClienteBean)it.next();
		    String data = "01/01/2001";
		    try{
		    	String consulta = "SELECT conhecimentos.dt_emissao " +
		    					  " from conhecimentos, movimentos_conhecimentos, tabelas_fretes " +
		    					  " where conhecimentos.oid_Conhecimento = movimentos_conhecimentos.oid_Conhecimento " +
		    					  " and movimentos_conhecimentos.oid_tabela_frete = tabelas_fretes.oid_tabela_frete " +
		    					  " and conhecimentos.oid_pessoa_pagador = '"+cliente.getOID_Cliente()+"' " +
		    					  " and tabelas_Fretes.oid_pessoa = '"+cliente.getOID_Cliente()+"' " +
		    					  " and conhecimentos.dm_impresso = 'S' " +
						  		  " and conhecimentos.vl_total_frete > 0 " +
						  		  " order by conhecimentos.dt_emissao desc limit 1";
		        ResultSet rs = new BancoUtil().executarConsulta(consulta);

		        while(rs.next()){
		        	data = FormataData.formataDataBT(rs.getString(1));
		        }

		    } catch (Exception e){
		    	e.printStackTrace();
		    	throw e;
		    }

		    StringBuffer buff = new StringBuffer ();
		    buff.append ("UPDATE Clientes SET ");
		    buff.append (" DT_Ultimo_Movimento=?           ");
		    buff.append (" WHERE OID_Cliente=?       ");
		    try {
		      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
		      pstmt.setString(1 , data);
		      pstmt.setString(2 , cliente.getOID_Cliente());
		      pstmt.executeUpdate ();
		    }
		    catch (Exception e) {
		      conn.rollback ();
		      e.printStackTrace ();
		      throw e;
		    }

        } //iterator

	    try {
	      conn.commit ();
	      conn.close ();
	    }
	    catch (Exception e) {
	    	conn.rollback ();
	      e.printStackTrace ();
	      throw e;
	    }

	  }

  public void delete () throws Exception {
    /*
     * Abre a conex�o com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conex�o ao gerenciador do driver
      // passando como par�metro o NM_Tipo_Documento do DSN
      // o NM_Tipo_Documento de usu�rio e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Define o DELETE.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append (" DELETE FROM Clientes ");
    buff.append (" WHERE OID_Cliente =?");
    /*
     * Define os dados do SQL e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getOID_Cliente ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    try {
      StringBuffer buff_Pessoa = new StringBuffer ();
      buff_Pessoa.append ("DELETE FROM Grupos_Pessoas_Cargas ");
      buff_Pessoa.append ("WHERE OID_Pessoa =?");
      PreparedStatement pstmt = conn.prepareStatement (buff_Pessoa.toString ());
      pstmt.setString (1 , getOID_Pessoa ());
      pstmt.executeUpdate ();
      buff_Pessoa.delete (0 , buff_Pessoa.length ());
      buff_Pessoa.append ("DELETE FROM Pessoas ");
      buff_Pessoa.append ("WHERE OID_Pessoa =?");
      pstmt = conn.prepareStatement (buff_Pessoa.toString ());
      pstmt.setString (1 , getOID_Pessoa ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conex�o.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public static final ClienteBean getByOID_Cliente (String oid) throws Exception {
    Connection conn = null;
    ExecutaSQL executasql = new ExecutaSQL ();
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
      executasql.setConnection (conn);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    ClienteBean p = new ClienteBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                                   ");
      buff.append ("   Clientes.OID_Cliente,                 ");
      buff.append ("   Clientes.OID_Cliente_Pagador,         ");
      buff.append ("	Clientes.OID_Pessoa,                  ");
      buff.append ("	Clientes.OID_Vendedor,                ");
      buff.append ("	Clientes.OID_Carteira,                ");
      buff.append ("	Clientes.OID_Grupo_Economico,         ");
      buff.append ("	Clientes.OID_Ramo_Atividade,          ");
      buff.append ("	Clientes.OID_Segmento,                ");
      buff.append ("	Clientes.DM_Tipo_Cobranca,            ");
      buff.append ("	Clientes.DM_Condicao_Vencimento,      ");
      buff.append ("	Clientes.DM_Fatura_Segunda,           ");
      buff.append ("	Clientes.DM_Fatura_Terca,             ");
      buff.append ("	Clientes.DM_Fatura_Quarta,            ");
      buff.append ("	Clientes.DM_Fatura_Quinta,            ");
      buff.append ("	Clientes.DM_Fatura_Sexta,             ");
      buff.append ("	Clientes.DM_Vencimento_Segunda,       ");
      buff.append ("	Clientes.DM_Vencimento_Terca,         ");
      buff.append ("	Clientes.DM_Vencimento_Quarta,        ");
      buff.append ("	Clientes.DM_Vencimento_Quinta,        ");
      buff.append ("	Clientes.DM_Vencimento_Sexta,         ");
      buff.append ("	Clientes.DM_FOB_Dirigido,             ");
      buff.append ("	Clientes.DM_Isencao_ICMS,             ");
      buff.append ("	Clientes.DM_Isencao_Seguro,           ");
      buff.append ("	Clientes.DM_Credito,                  ");
      buff.append ("	Clientes.NR_Conta_Contabil,           ");
      buff.append ("	Clientes.DM_Compr_Entrega_Fatura,     ");
      buff.append ("	Clientes.DM_Tipo_Emissao,             ");
      buff.append ("	Clientes.VL_Minimo_Fatura,            ");
      buff.append ("	Clientes.VL_Maximo_Fatura,            ");
      buff.append ("	Clientes.VL_Limite_Credito,           ");
      buff.append ("	Clientes.NR_Minimo_CTO_Fatura,        ");
      buff.append ("	Clientes.NR_Maximo_CTO_Fatura,        ");
      buff.append ("	Clientes.PE_Juros_Cobranca,           ");
      buff.append ("	Clientes.VL_Taxa_Cobranca,            ");
      buff.append ("	Clientes.NR_Peso_Cubado,              ");
      buff.append ("	Clientes.NR_Dias_Vencimento,          ");
      buff.append ("	Clientes.NR_Dias_Protesto,            ");
      buff.append ("	Pessoas.NR_CNPJ_CPF,                  ");
      buff.append ("	Pessoas.NM_Razao_Social,              ");
      buff.append ("	Pessoas.NM_Fantasia,                  ");
      buff.append ("	Grupos_Economicos.CD_Grupo_Economico, ");
      buff.append ("	Grupos_Economicos.NM_Grupo_Economico, ");
      buff.append ("	Ramos_Atividades.CD_Ramo_Atividade,   ");
      buff.append ("	Ramos_Atividades.NM_Ramo_Atividade,    ");
      buff.append ("	Pessoas_Bancos.nm_razao_Social as NM_Banco , ");
      buff.append ("	Carteiras.CD_Carteira, ");
      buff.append ("	Contas_Correntes.CD_Conta_Corrente,  ");
      buff.append ("	Clientes.PE_PIS_COFINS,         ");
      buff.append ("	Clientes.DM_PIS_COFINS,         ");
      buff.append ("	Clientes.PE_Comissao,           ");
      buff.append ("	Clientes.DM_Tipo_Faturamento,   ");
      buff.append ("	Clientes.NR_QT_Notas_Fiscais,   ");
      buff.append ("	Clientes.VL_Taxa_Despacho,      ");
      buff.append ("	Clientes.oid_unidade_operacional,");
      buff.append ("	Clientes.dm_situacao,           ");
      buff.append ("	Clientes.tx_instrucao_cobranca, ");
      buff.append ("    Clientes.VL_Limite_Credito_Adicional,");
      buff.append (" 	Clientes.VL_Minimo_Compra,     ");
      buff.append (" 	Clientes.NR_Duplicatas_Vencidas, ");
      buff.append (" 	Clientes.DT_Cadastro,    		 ");
      buff.append (" 	Clientes.DT_Validade_Cadastro, ");
      buff.append ("	Clientes.dm_tracking, ");
      buff.append ("	Clientes.oid_pessoa_parceiro, ");
      buff.append ("	Pessoas.NM_Endereco, ");
      buff.append ("	Pessoas.oid_cidade, ");
      buff.append ("	Clientes.oid_Produto, ");
      buff.append ("	Clientes.DM_RCTRC,   ");
      buff.append ("	Clientes.DM_RCTR_VI,   ");
      buff.append ("	Clientes.DM_RCTA,   ");
      buff.append ("	Clientes.DM_RCTR_DC,    ");
      buff.append ("    Clientes.CD_Cliente_Palm, ");
      buff.append ("    Clientes.oid_Condicao_Pagamento, ");
      buff.append ("    Clientes.DM_Forma_Pagamento, ");
      buff.append ("	Clientes.oid_Conta, ");
      buff.append ("	Clientes.oid_Conta_Debito, ");
      buff.append ("	Clientes.PE_Base_Comissao_Motorista, ");
      buff.append ("       Clientes.DT_Alteracao,               ");
      buff.append ("       Clientes.DT_Desativado,              ");
      buff.append ("       Clientes.DM_Monitoramento, ");
      buff.append ("       Clientes.VL_Taxa_Coleta,     ");
      buff.append ("       Clientes.VL_Taxa_Entrega,     ");
      buff.append ("       Clientes.DM_Resp_Vendedor,            ");
      buff.append ("       Clientes.OID_Modal,            ");
      buff.append ("       Clientes.VL_Custo_Carga,     ");
      buff.append ("       Clientes.VL_Custo_Descarga,     ");
      buff.append ("       Clientes.NM_Contato_Cobranca,            ");
      buff.append ("       Clientes.DM_Wms,            ");
      buff.append ("       Clientes.CD_Acesso,            ");
      buff.append ("       Clientes.DM_Inclui_ICMS_Parcela_CTRC,            ");
      buff.append ("       Clientes.PE_Desconto_Vencimento,     ");
      buff.append ("       Clientes.DM_Carregamento,            ");
      buff.append ("       Clientes.DM_ICMS_Wms,            ");
      buff.append ("	Clientes.NR_Dias_Contagem_Ciclica,            ");
      buff.append ("	Clientes.NR_Dias_Bloqueio,            ");
      buff.append ("	Clientes.DT_Ultimo_Movimento,            ");
      buff.append ("	Clientes.DM_Faturamento   ");
      buff.append ("   FROM Clientes, Pessoas, Grupos_Economicos, Ramos_Atividades, Pessoas Pessoas_Bancos , Carteiras, Contas_Correntes ");
      buff.append ("   WHERE Clientes.OID_Pessoa          = Pessoas.OID_Pessoa                    ");
      buff.append ("   AND   Clientes.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");
      buff.append ("   AND   Clientes.OID_Grupo_Economico = Grupos_Economicos.OID_Grupo_Economico  ");
      buff.append ("   AND   Clientes.OID_Carteira        = Carteiras.oid_Carteira  ");
      buff.append ("   AND   Carteiras.oid_conta_corrente = Contas_Correntes.oid_conta_corrente  ");
      buff.append ("   AND   Contas_Correntes.oid_pessoa =  Pessoas_Bancos.oid_pessoa  ");
      buff.append ("   AND   Clientes.OID_Cliente = '");
      buff.append (oid);
      buff.append ("'");
      buff.append ("   ORDER BY Pessoas.NM_RAZAO_SOCIAL ");

     // System.out.println(buff.toString());

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      while (cursor.next ()) {
        p.setOID_Cliente (cursor.getString (1));
        p.setOID_Cliente_Pagador (cursor.getString (2));
        p.setOID_Pessoa (cursor.getString (3));
        p.setOID_Vendedor (cursor.getString (4));
        p.setOID_Carteira (cursor.getInt (5));
        p.setOID_Grupo_Economico (cursor.getInt (6));
        p.setOID_Ramo_Atividade (cursor.getInt (7));
        p.setOID_Segmento (cursor.getInt (8));
        p.setDM_Tipo_Cobranca (cursor.getString (9));
        p.setDM_Condicao_Vencimento (cursor.getString (10));
        p.setDM_Fatura_Segunda (cursor.getString (11));
        p.setDM_Fatura_Terca (cursor.getString (12));
        p.setDM_Fatura_Quarta (cursor.getString (13));
        p.setDM_Fatura_Quinta (cursor.getString (14));
        p.setDM_Fatura_Sexta (cursor.getString (15));
        p.setDM_Vencimento_Segunda (cursor.getString (16));
        p.setDM_Vencimento_Terca (cursor.getString (17));
        p.setDM_Vencimento_Quarta (cursor.getString (18));
        p.setDM_Vencimento_Quinta (cursor.getString (19));
        p.setDM_Vencimento_Sexta (cursor.getString (20));
        p.setDM_FOB_Dirigido (cursor.getString (21));
        p.setDM_Isencao_ICMS (cursor.getString (22));
        p.setDM_Isencao_Seguro (cursor.getString (23));
        p.setDM_Credito (cursor.getString (24));
        p.setNR_Conta_Contabil (cursor.getString (25));
        p.setDM_Compr_Entrega_Fatura (cursor.getString (26));
        p.setDM_Tipo_Emissao (cursor.getString (27));
        p.setVL_Minimo_Fatura (cursor.getDouble (28));
        p.setVL_Maximo_Fatura (cursor.getDouble (29));
        p.setVL_Limite_Credito (new DuplicataBD (executasql).getLimiteCredito (p.getOID_Pessoa () , Data.manipulaDias (Data.getDataDMY () , -30) , Data.getDataDMY ()));
        p.setNR_Minimo_CTO_Fatura (cursor.getInt (31));
        p.setNR_Maximo_CTO_Fatura (cursor.getInt (32));
        p.setPE_Juros_Cobranca (cursor.getDouble (33));
        p.setVL_Taxa_Cobranca (cursor.getDouble (34));
        p.setNR_Peso_Cubado (cursor.getInt (35));
        p.setNR_Dias_Vencimento (cursor.getInt (36));
        p.setNR_Dias_Protesto (cursor.getInt (37));
        p.setNR_CNPJ_CPF (cursor.getString (38));
        p.setNM_Razao_Social (cursor.getString (39));
        p.setNM_Fantasia (cursor.getString (40));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia (p.getNM_Razao_Social ());
        }
        p.setCD_Grupo_Economico (cursor.getString (41));
        p.setNM_Grupo_Economico (cursor.getString (42));
        p.setCD_Ramo_Atividade (cursor.getString (43));
        p.setNM_Ramo_Atividade (cursor.getString (44));
        p.setNM_Banco (cursor.getString (45));
        p.setCD_Carteira (cursor.getString (46));
        p.setCD_Conta_Corrente (cursor.getString (47));
        p.setPE_PIS_COFINS (cursor.getDouble (48));
        p.setDM_PIS_COFINS (cursor.getString (49));
        p.setPE_Comissao (cursor.getDouble (50));
        p.setDM_Tipo_Faturamento (cursor.getString (51));
        p.setNR_QT_Notas_Fiscais (cursor.getInt (52));
        p.setVL_Taxa_Despacho (cursor.getDouble (53));
        p.setOID_Unidade_Operacional (cursor.getInt (54));
        p.setDM_Situacao (cursor.getString (55));
        p.setTX_Instrucao_Cobranca (cursor.getString (56));
        p.setVL_Limite_Credito_Adicional (cursor.getDouble (57));
        p.setVL_Minimo_Compra (cursor.getDouble (58));
        p.setNR_Duplicatas_Vencidas (cursor.getInt (59));
        p.setDT_Cadastro (FormataData.formataDataBT (cursor.getDate (60)));
        p.setDT_Alteracao (FormataData.formataDataBT (cursor.getDate ("DT_Alteracao")));
        p.setDT_Desativado (FormataData.formataDataBT (cursor.getDate ("DT_Desativado")));
        p.setDT_Validade_Cadastro (FormataData.formataDataBT (cursor.getDate (61)));
        p.setDM_Tracking (cursor.getString (62));
        p.setOid_Pessoa_Parceiro (cursor.getString (63));
        p.setNM_Endereco (cursor.getString (64));
        p.setOID_Cidade (String.valueOf (cursor.getInt (65)));
        CidadeBean cidade = CidadeBean.getByOID (cursor.getInt (65));
        p.setNM_Cidade (cidade.getNM_Cidade ());
        p.setCD_Estado (cidade.getCD_Estado ());
        p.setOid_Produto (cursor.getInt ("oid_Produto"));
        p.setDM_RCTRC (cursor.getString ("DM_RCTRC"));
        p.setDM_RCTR_VI (cursor.getString ("DM_RCTR_VI"));
        p.setDM_RCTA (cursor.getString ("DM_RCTA"));
        p.setDM_RCTR_DC (cursor.getString ("DM_RCTR_DC"));
        p.setCD_Cliente_Palm (formataCodigoCliente (cursor.getString ("CD_Cliente_Palm")));
        p.setOid_Condicao_Pagamento (cursor.getInt ("oid_Condicao_Pagamento"));
        p.setDM_Forma_Pagamento (cursor.getString ("DM_Forma_Pagamento"));
        p.setOid_Conta (cursor.getInt ("oid_Conta"));
        p.setOid_Conta_Debito (cursor.getInt ("oid_Conta_Debito"));
        p.setDM_Monitoramento (cursor.getString ("DM_Monitoramento"));
        p.setPE_Base_Comissao_Motorista (cursor.getDouble ("PE_Base_Comissao_Motorista"));
        p.setDM_Resp_Vendedor (cursor.getString ("DM_Resp_Vendedor"));
        p.setVL_Taxa_Coleta (cursor.getDouble ("VL_Taxa_Coleta"));
        p.setVL_Taxa_Entrega (cursor.getDouble ("VL_Taxa_Entrega"));
        p.setVL_Custo_Carga (cursor.getDouble ("VL_Custo_Carga"));
        p.setVL_Custo_Descarga (cursor.getDouble ("VL_Custo_Descarga"));
        p.setOID_Modal (cursor.getInt ("OID_Modal"));
        p.setDM_Wms (cursor.getString ("DM_Wms"));
        p.setDM_ICMS_WMS (cursor.getString ("DM_ICMS_Wms"));
        p.setDM_Carregamento (cursor.getString ("DM_Carregamento"));
        p.setCD_Acesso (cursor.getString ("CD_Acesso"));
        p.setNR_Dias_Contagem_Ciclica (cursor.getInt ("NR_Dias_Contagem_Ciclica"));
        p.setDM_Inclui_ICMS_Parcela_CTRC (cursor.getString ("DM_Inclui_ICMS_Parcela_CTRC"));
        p.setNM_Contato_Cobranca (cursor.getString ("NM_Contato_Cobranca"));
        if (p.getNM_Contato_Cobranca () == null || p.getNM_Contato_Cobranca ().equals ("null") || p.getNM_Contato_Cobranca ().equals ("")) {
          p.setNM_Contato_Cobranca (" ");
        }
        p.setPE_Desconto_Vencimento (cursor.getDouble ("PE_Desconto_Vencimento"));

        p.setDM_Faturamento (cursor.getString ("DM_Faturamento"));

        p.setNR_Dias_Bloqueio(cursor.getInt ("NR_Dias_Bloqueio"));
        p.setDT_Ultimo_Movimento(FormataData.formataDataBT(cursor.getString("dt_ultimo_movimento")));

        if(p.getNR_Dias_Bloqueio()>0 && Utilitaria.doValida(p.getDT_Ultimo_Movimento())){
        	  p.setDT_Bloqueio(Data.getSomaDiaData(p.getDT_Ultimo_Movimento(), p.getNR_Dias_Bloqueio()));
          } else if(p.getNR_Dias_Bloqueio()>0){
        	  p.setDT_Bloqueio(Data.getSomaDiaData(Data.getDataDMY(), p.getNR_Dias_Bloqueio()));
          } else {
        	  p.setDT_Bloqueio(Data.getSomaDiaData(Data.getDataDMY(), 30));
          }
//        if(!Utilitaria.doValida(cursor.getString("dt_ultimo_movimento"))){
//        	p.setDT_Ultimo_Movimento(update_Movimentacao(p.getOID_Cliente()));
//        }

      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final ClienteBean getByOID_Cliente_WMS (String oid) throws Exception {
    Connection conn = null;
    ExecutaSQL executasql = new ExecutaSQL ();
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
      executasql.setConnection (conn);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    ClienteBean p = new ClienteBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                                   ");
      buff.append ("   Clientes.OID_Cliente,                 ");
      buff.append ("   Clientes.OID_Cliente_Pagador,         ");
      buff.append ("	Clientes.OID_Pessoa,                  ");
      buff.append ("	Clientes.OID_Vendedor,                ");
      buff.append ("	Clientes.OID_Carteira,                ");
      buff.append ("	Clientes.OID_Grupo_Economico,         ");
      buff.append ("	Clientes.OID_Ramo_Atividade,          ");
      buff.append ("	Clientes.OID_Segmento,                ");
      buff.append ("	Clientes.DM_Tipo_Cobranca,            ");
      buff.append ("	Clientes.DM_Condicao_Vencimento,      ");
      buff.append ("	Clientes.DM_Fatura_Segunda,           ");
      buff.append ("	Clientes.DM_Fatura_Terca,             ");
      buff.append ("	Clientes.DM_Fatura_Quarta,            ");
      buff.append ("	Clientes.DM_Fatura_Quinta,            ");
      buff.append ("	Clientes.DM_Fatura_Sexta,             ");
      buff.append ("	Clientes.DM_Vencimento_Segunda,       ");
      buff.append ("	Clientes.DM_Vencimento_Terca,         ");
      buff.append ("	Clientes.DM_Vencimento_Quarta,        ");
      buff.append ("	Clientes.DM_Vencimento_Quinta,        ");
      buff.append ("	Clientes.DM_Vencimento_Sexta,         ");
      buff.append ("	Clientes.DM_FOB_Dirigido,             ");
      buff.append ("	Clientes.DM_Isencao_ICMS,             ");
      buff.append ("	Clientes.DM_Isencao_Seguro,           ");
      buff.append ("	Clientes.DM_Credito,                  ");
      buff.append ("	Clientes.NR_Conta_Contabil,           ");
      buff.append ("	Clientes.DM_Compr_Entrega_Fatura,     ");
      buff.append ("	Clientes.DM_Tipo_Emissao,             ");
      buff.append ("	Clientes.VL_Minimo_Fatura,            ");
      buff.append ("	Clientes.VL_Maximo_Fatura,            ");
      buff.append ("	Clientes.VL_Limite_Credito,           ");
      buff.append ("	Clientes.NR_Minimo_CTO_Fatura,        ");
      buff.append ("	Clientes.NR_Maximo_CTO_Fatura,        ");
      buff.append ("	Clientes.PE_Juros_Cobranca,           ");
      buff.append ("	Clientes.VL_Taxa_Cobranca,            ");
      buff.append ("	Clientes.NR_Peso_Cubado,              ");
      buff.append ("	Clientes.NR_Dias_Vencimento,          ");
      buff.append ("	Clientes.NR_Dias_Protesto,            ");
      buff.append ("	Pessoas.NR_CNPJ_CPF,                  ");
      buff.append ("	Pessoas.NM_Razao_Social,              ");
      buff.append ("	Pessoas.NM_Fantasia,                  ");
      buff.append ("	Grupos_Economicos.CD_Grupo_Economico, ");
      buff.append ("	Grupos_Economicos.NM_Grupo_Economico, ");
      buff.append ("	Ramos_Atividades.CD_Ramo_Atividade,   ");
      buff.append ("	Ramos_Atividades.NM_Ramo_Atividade,    ");
      buff.append ("	Pessoas_Bancos.nm_razao_Social as NM_Banco , ");
      buff.append ("	Carteiras.CD_Carteira, ");
      buff.append ("	Contas_Correntes.CD_Conta_Corrente,  ");
      buff.append ("	Clientes.PE_PIS_COFINS,         ");
      buff.append ("	Clientes.DM_PIS_COFINS,         ");
      buff.append ("	Clientes.PE_Comissao,           ");
      buff.append ("	Clientes.DM_Tipo_Faturamento,   ");
      buff.append ("	Clientes.NR_QT_Notas_Fiscais,   ");
      buff.append ("	Clientes.VL_Taxa_Despacho,      ");
      buff.append ("	Clientes.oid_unidade_operacional,");
      buff.append ("	Clientes.dm_situacao,           ");
      buff.append ("	Clientes.tx_instrucao_cobranca, ");
      buff.append ("   Clientes.VL_Limite_Credito_Adicional,");
      buff.append (" 	Clientes.VL_Minimo_Compra,     ");
      buff.append (" 	Clientes.NR_Duplicatas_Vencidas, ");
      buff.append (" 	Clientes.DT_Cadastro,    		 ");
      buff.append (" 	Clientes.DT_Validade_Cadastro, ");
      buff.append ("	Clientes.dm_tracking, ");
      buff.append ("	Clientes.oid_pessoa_parceiro, ");
      buff.append ("	Pessoas.NM_Endereco, ");
      buff.append ("	Pessoas.oid_cidade, ");
      buff.append ("	Clientes.oid_Produto, ");
      buff.append ("	Clientes.DM_RCTRC,   ");
      buff.append ("	Clientes.DM_RCTR_VI,   ");
      buff.append ("	Clientes.DM_RCTA,   ");
      buff.append ("	Clientes.DM_RCTR_DC,    ");
      buff.append ("   Clientes.CD_Cliente_Palm, ");
      buff.append ("   Clientes.oid_Condicao_Pagamento, ");
      buff.append ("   Clientes.DM_Forma_Pagamento, ");
      buff.append ("	Clientes.oid_Conta, ");
      buff.append ("	Clientes.oid_Conta_Debito, ");
      buff.append ("	Clientes.PE_Base_Comissao_Motorista, ");
      buff.append ("   Clientes.DT_Alteracao,               ");
      buff.append ("   Clientes.DT_Desativado,              ");
      buff.append ("   Clientes.DM_Monitoramento, ");
      buff.append (" 	Clientes.VL_Taxa_Coleta,     ");
      buff.append (" 	Clientes.VL_Taxa_Entrega,     ");
      buff.append ("   Clientes.DM_Resp_Vendedor,            ");
      buff.append ("   Clientes.OID_Modal,            ");
      buff.append ("   Clientes.DM_Wms            ");
      buff.append ("   FROM Clientes, Pessoas, Grupos_Economicos, Ramos_Atividades, Pessoas Pessoas_Bancos , Carteiras, Contas_Correntes ");
      buff.append ("   WHERE Clientes.OID_Pessoa          = Pessoas.OID_Pessoa                    ");
      buff.append ("   AND   Clientes.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");
      buff.append ("   AND   Clientes.OID_Grupo_Economico = Grupos_Economicos.OID_Grupo_Economico  ");
      buff.append ("   AND   Clientes.OID_Carteira        = Carteiras.oid_Carteira  ");
      buff.append ("   AND   Carteiras.oid_conta_corrente = Contas_Correntes.oid_conta_corrente  ");
      buff.append ("   AND   Contas_Correntes.oid_pessoa =  Pessoas_Bancos.oid_pessoa  ");
      buff.append ("   AND   Clientes.OID_Cliente = '");
      buff.append (oid);
      buff.append ("' and DM_WMS = 'S' ");
      buff.append ("   ORDER BY Pessoas.NM_RAZAO_SOCIAL ");
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      while (cursor.next ()) {
        p.setOID_Cliente (cursor.getString (1));
        p.setOID_Cliente_Pagador (cursor.getString (2));
        p.setOID_Pessoa (cursor.getString (3));
        p.setOID_Vendedor (cursor.getString (4));
        p.setOID_Carteira (cursor.getInt (5));
        p.setOID_Grupo_Economico (cursor.getInt (6));
        p.setOID_Ramo_Atividade (cursor.getInt (7));
        p.setOID_Segmento (cursor.getInt (8));
        p.setDM_Tipo_Cobranca (cursor.getString (9));
        p.setDM_Condicao_Vencimento (cursor.getString (10));
        p.setDM_Fatura_Segunda (cursor.getString (11));
        p.setDM_Fatura_Terca (cursor.getString (12));
        p.setDM_Fatura_Quarta (cursor.getString (13));
        p.setDM_Fatura_Quinta (cursor.getString (14));
        p.setDM_Fatura_Sexta (cursor.getString (15));
        p.setDM_Vencimento_Segunda (cursor.getString (16));
        p.setDM_Vencimento_Terca (cursor.getString (17));
        p.setDM_Vencimento_Quarta (cursor.getString (18));
        p.setDM_Vencimento_Quinta (cursor.getString (19));
        p.setDM_Vencimento_Sexta (cursor.getString (20));
        p.setDM_FOB_Dirigido (cursor.getString (21));
        p.setDM_Isencao_ICMS (cursor.getString (22));
        p.setDM_Isencao_Seguro (cursor.getString (23));
        p.setDM_Credito (cursor.getString (24));
        p.setNR_Conta_Contabil (cursor.getString (25));
        p.setDM_Compr_Entrega_Fatura (cursor.getString (26));
        p.setDM_Tipo_Emissao (cursor.getString (27));
        p.setVL_Minimo_Fatura (cursor.getDouble (28));
        p.setVL_Maximo_Fatura (cursor.getDouble (29));
        p.setVL_Limite_Credito (new DuplicataBD (executasql).getLimiteCredito (p.getOID_Pessoa () , Data.manipulaDias (Data.getDataDMY () , -30) , Data.getDataDMY ()));

        p.setNR_Minimo_CTO_Fatura (cursor.getInt (31));
        p.setNR_Maximo_CTO_Fatura (cursor.getInt (32));
        p.setPE_Juros_Cobranca (cursor.getDouble (33));
        p.setVL_Taxa_Cobranca (cursor.getDouble (34));
        p.setNR_Peso_Cubado (cursor.getInt (35));
        p.setNR_Dias_Vencimento (cursor.getInt (36));
        p.setNR_Dias_Protesto (cursor.getInt (37));
        p.setNR_CNPJ_CPF (cursor.getString (38));
        p.setNM_Razao_Social (cursor.getString (39));
        p.setNM_Fantasia (cursor.getString (40));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia (p.getNM_Razao_Social ());
        }
        p.setCD_Grupo_Economico (cursor.getString (41));
        p.setNM_Grupo_Economico (cursor.getString (42));
        p.setCD_Ramo_Atividade (cursor.getString (43));
        p.setNM_Ramo_Atividade (cursor.getString (44));
        p.setNM_Banco (cursor.getString (45));
        p.setCD_Carteira (cursor.getString (46));
        p.setCD_Conta_Corrente (cursor.getString (47));
        p.setPE_PIS_COFINS (cursor.getDouble (48));
        p.setDM_PIS_COFINS (cursor.getString (49));
        p.setPE_Comissao (cursor.getDouble (50));
        p.setDM_Tipo_Faturamento (cursor.getString (51));
        p.setNR_QT_Notas_Fiscais (cursor.getInt (52));
        p.setVL_Taxa_Despacho (cursor.getDouble (53));
        p.setOID_Unidade_Operacional (cursor.getInt (54));
        p.setDM_Situacao (cursor.getString (55));
        p.setTX_Instrucao_Cobranca (cursor.getString (56));
        p.setVL_Limite_Credito_Adicional (cursor.getDouble (57));
        p.setVL_Minimo_Compra (cursor.getDouble (58));
        p.setNR_Duplicatas_Vencidas (cursor.getInt (59));
        p.setDT_Cadastro (FormataData.formataDataBT (cursor.getDate (60)));
        p.setDT_Alteracao (FormataData.formataDataBT (cursor.getDate ("DT_Alteracao")));
        p.setDT_Desativado (FormataData.formataDataBT (cursor.getDate ("DT_Desativado")));
        p.setDT_Validade_Cadastro (FormataData.formataDataBT (cursor.getDate (61)));
        p.setDM_Tracking (cursor.getString (62));
        p.setOid_Pessoa_Parceiro (cursor.getString (63));
        p.setNM_Endereco (cursor.getString (64));
        p.setOID_Cidade (String.valueOf (cursor.getInt (65)));
        CidadeBean cidade = CidadeBean.getByOID (cursor.getInt (65));
        p.setNM_Cidade (cidade.getNM_Cidade ());
        p.setCD_Estado (cidade.getCD_Estado ());
        p.setOid_Produto (cursor.getInt ("oid_Produto"));
        p.setDM_RCTRC (cursor.getString ("DM_RCTRC"));
        p.setDM_RCTR_VI (cursor.getString ("DM_RCTR_VI"));
        p.setDM_RCTA (cursor.getString ("DM_RCTA"));
        p.setDM_RCTR_DC (cursor.getString ("DM_RCTR_DC"));
        p.setCD_Cliente_Palm (formataCodigoCliente (cursor.getString ("CD_Cliente_Palm")));
        p.setOid_Condicao_Pagamento (cursor.getInt ("oid_Condicao_Pagamento"));
        p.setDM_Forma_Pagamento (cursor.getString ("DM_Forma_Pagamento"));
        p.setOid_Conta (cursor.getInt ("oid_Conta"));
        p.setOid_Conta_Debito (cursor.getInt ("oid_Conta_Debito"));
        p.setDM_Monitoramento (cursor.getString ("DM_Monitoramento"));
        p.setPE_Base_Comissao_Motorista (cursor.getDouble ("PE_Base_Comissao_Motorista"));
        p.setDM_Resp_Vendedor (cursor.getString ("DM_Resp_Vendedor"));
        p.setVL_Taxa_Coleta (cursor.getDouble ("VL_Taxa_Coleta"));
        p.setVL_Taxa_Entrega (cursor.getDouble ("VL_Taxa_Entrega"));
        p.setOID_Modal (cursor.getInt ("OID_Modal"));
        p.setDM_Wms (cursor.getString ("DM_Wms"));
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final ClienteBean getByCD_Cliente (String codigo) throws Exception {
    Connection conn = null;
    ExecutaSQL executasql = new ExecutaSQL ();
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
      executasql.setConnection (conn);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    ClienteBean p = new ClienteBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                                   ");
      buff.append ("   Clientes.OID_Cliente,                 ");
      buff.append ("   Clientes.OID_Cliente_Pagador,         ");
      buff.append ("   Clientes.OID_Pessoa,                  ");
      buff.append ("   Clientes.OID_Vendedor,                ");
      buff.append ("   Clientes.OID_Carteira,                ");
      buff.append ("   Clientes.OID_Grupo_Economico,         ");
      buff.append ("   Clientes.OID_Ramo_Atividade,          ");
      buff.append ("   Clientes.OID_Segmento,                ");
      buff.append ("   Clientes.DM_Tipo_Cobranca,            ");
      buff.append ("   Clientes.DM_Condicao_Vencimento,      ");
      buff.append ("   Clientes.DM_Fatura_Segunda,           ");
      buff.append ("   Clientes.DM_Fatura_Terca,             ");
      buff.append ("   Clientes.DM_Fatura_Quarta,            ");
      buff.append ("   Clientes.DM_Fatura_Quinta,            ");
      buff.append ("   Clientes.DM_Fatura_Sexta,             ");
      buff.append ("   Clientes.DM_Vencimento_Segunda,       ");
      buff.append ("   Clientes.DM_Vencimento_Terca,         ");
      buff.append ("   Clientes.DM_Vencimento_Quarta,        ");
      buff.append ("   Clientes.DM_Vencimento_Quinta,        ");
      buff.append ("   Clientes.DM_Vencimento_Sexta,         ");
      buff.append ("   Clientes.DM_FOB_Dirigido,             ");
      buff.append ("   Clientes.DM_Isencao_ICMS,             ");
      buff.append ("   Clientes.DM_Isencao_Seguro,           ");
      buff.append ("   Clientes.DM_Credito,                  ");
      buff.append ("   Clientes.NR_Conta_Contabil,           ");
      buff.append ("   Clientes.DM_Compr_Entrega_Fatura,     ");
      buff.append ("   Clientes.DM_Tipo_Emissao,             ");
      buff.append ("   Clientes.VL_Minimo_Fatura,            ");
      buff.append ("   Clientes.VL_Maximo_Fatura,            ");
      buff.append ("   Clientes.VL_Limite_Credito,           ");
      buff.append ("   Clientes.NR_Minimo_CTO_Fatura,        ");
      buff.append ("   Clientes.NR_Maximo_CTO_Fatura,        ");
      buff.append ("   Clientes.PE_Juros_Cobranca,           ");
      buff.append ("   Clientes.VL_Taxa_Cobranca,            ");
      buff.append ("   Clientes.NR_Peso_Cubado,              ");
      buff.append ("   Clientes.NR_Dias_Vencimento,          ");
      buff.append ("   Clientes.NR_Dias_Protesto,            ");
      buff.append ("   Pessoas.NR_CNPJ_CPF,                  ");
      buff.append ("   Pessoas.NM_Razao_Social,              ");
      buff.append ("   Pessoas.NM_Fantasia,                  ");
      buff.append ("   Grupos_Economicos.CD_Grupo_Economico, ");
      buff.append ("   Grupos_Economicos.NM_Grupo_Economico, ");
      buff.append ("   Ramos_Atividades.CD_Ramo_Atividade,   ");
      buff.append ("   Ramos_Atividades.NM_Ramo_Atividade,    ");
      buff.append ("   Pessoas_Bancos.nm_razao_Social as NM_Banco , ");
      buff.append ("   Carteiras.CD_Carteira, ");
      buff.append ("   Contas_Correntes.CD_Conta_Corrente,  ");
      buff.append ("   Clientes.PE_PIS_COFINS,         ");
      buff.append ("   Clientes.DM_PIS_COFINS,         ");
      buff.append ("   Clientes.PE_Comissao,           ");
      buff.append ("   Clientes.DM_Tipo_Faturamento,   ");
      buff.append ("   Clientes.NR_QT_Notas_Fiscais,   ");
      buff.append ("   Clientes.VL_Taxa_Despacho,      ");
      buff.append ("   Clientes.oid_unidade_operacional, ");
      buff.append ("   Clientes.dm_situacao, ");
      buff.append ("   Clientes.tx_instrucao_cobranca, ");
      buff.append ("   Clientes.VL_Limite_Credito_Adicional, ");
      buff.append ("   Clientes.VL_Minimo_Compra,     ");
      buff.append ("   Clientes.NR_Duplicatas_Vencidas, ");
      buff.append ("   Clientes.DT_Cadastro,            ");
      buff.append ("   Clientes.DT_Validade_Cadastro, ");
      buff.append ("   Clientes.dm_tracking, ");
      buff.append ("   Clientes.oid_pessoa_parceiro, ");
      buff.append ("   Pessoas.NM_Endereco, ");
      buff.append ("   Pessoas.oid_cidade, ");
      buff.append ("   Clientes.oid_Produto, ");
      buff.append ("   Clientes.DM_RCTRC,   ");
      buff.append ("   Clientes.DM_RCTR_VI,   ");
      buff.append ("   Clientes.DM_RCTA,   ");
      buff.append ("   Clientes.DM_RCTR_DC,    ");
      buff.append ("   Clientes.CD_Cliente_Palm, ");
      buff.append ("   Clientes.oid_Condicao_Pagamento, ");
      buff.append ("   Clientes.DM_Forma_Pagamento,     ");
      buff.append ("   Clientes.DT_Alteracao,           ");
      buff.append ("   Clientes.DT_Desativado,          ");
      buff.append ("   Clientes.DM_Monitoramento, ");
      buff.append ("   Clientes.DM_Resp_Vendedor,        ");
      buff.append ("   Clientes.OID_Modal,        ");
      buff.append ("   Clientes.DM_Wms,        ");
      buff.append ("	Clientes.NR_Dias_Bloqueio,            ");
      buff.append ("	Clientes.DT_Ultimo_Movimento            ");
      buff.append (" FROM Clientes, Pessoas, Grupos_Economicos, Ramos_Atividades, Pessoas Pessoas_Bancos , Carteiras, Contas_Correntes ");
      buff.append (" WHERE Clientes.OID_Pessoa          = Pessoas.OID_Pessoa                    ");
      buff.append (" AND   Clientes.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");
      buff.append (" AND   Clientes.OID_Grupo_Economico = Grupos_Economicos.OID_Grupo_Economico  ");
      buff.append (" AND   Clientes.OID_Carteira        = Carteiras.oid_Carteira  ");
      buff.append (" AND   Carteiras.oid_conta_corrente = Contas_Correntes.oid_conta_corrente  ");
      buff.append (" AND   Contas_Correntes.oid_pessoa =  Pessoas_Bancos.oid_pessoa  ");
      buff.append (" AND   Clientes.CD_Cliente_Palm = '");
      buff.append (codigo.replaceAll ("-" , ""));
      buff.append ("'");
      buff.append (" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      while (cursor.next ()) {
        p.setOID_Cliente (cursor.getString (1));
        p.setOID_Cliente_Pagador (cursor.getString (2));
        p.setOID_Pessoa (cursor.getString (3));
        p.setOID_Vendedor (cursor.getString (4));
        p.setOID_Carteira (cursor.getInt (5));
        p.setOID_Grupo_Economico (cursor.getInt (6));
        p.setOID_Ramo_Atividade (cursor.getInt (7));
        p.setOID_Segmento (cursor.getInt (8));
        p.setDM_Tipo_Cobranca (cursor.getString (9));
        p.setDM_Condicao_Vencimento (cursor.getString (10));
        p.setDM_Fatura_Segunda (cursor.getString (11));
        p.setDM_Fatura_Terca (cursor.getString (12));
        p.setDM_Fatura_Quarta (cursor.getString (13));
        p.setDM_Fatura_Quinta (cursor.getString (14));
        p.setDM_Fatura_Sexta (cursor.getString (15));
        p.setDM_Vencimento_Segunda (cursor.getString (16));
        p.setDM_Vencimento_Terca (cursor.getString (17));
        p.setDM_Vencimento_Quarta (cursor.getString (18));
        p.setDM_Vencimento_Quinta (cursor.getString (19));
        p.setDM_Vencimento_Sexta (cursor.getString (20));
        p.setDM_FOB_Dirigido (cursor.getString (21));
        p.setDM_Isencao_ICMS (cursor.getString (22));
        p.setDM_Isencao_Seguro (cursor.getString (23));
        p.setDM_Credito (cursor.getString (24));
        p.setNR_Conta_Contabil (cursor.getString (25));
        p.setDM_Compr_Entrega_Fatura (cursor.getString (26));
        p.setDM_Tipo_Emissao (cursor.getString (27));
        p.setVL_Minimo_Fatura (cursor.getDouble (28));
        p.setVL_Maximo_Fatura (cursor.getDouble (29));
        p.setVL_Limite_Credito (new DuplicataBD (executasql).getLimiteCredito (p.getOID_Pessoa () , Data.manipulaDias (Data.getDataDMY () , -30) , Data.getDataDMY ()));
        p.setNR_Minimo_CTO_Fatura (cursor.getInt (31));
        p.setNR_Maximo_CTO_Fatura (cursor.getInt (32));
        p.setPE_Juros_Cobranca (cursor.getDouble (33));
        p.setVL_Taxa_Cobranca (cursor.getDouble (34));
        p.setNR_Peso_Cubado (cursor.getInt (35));
        p.setNR_Dias_Vencimento (cursor.getInt (36));
        p.setNR_Dias_Protesto (cursor.getInt (37));
        p.setNR_CNPJ_CPF (cursor.getString (38));
        p.setNM_Razao_Social (cursor.getString (39));
        p.setNM_Fantasia (cursor.getString (40));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia (p.getNM_Razao_Social ());
        }
        p.setCD_Grupo_Economico (cursor.getString (41));
        p.setNM_Grupo_Economico (cursor.getString (42));
        p.setCD_Ramo_Atividade (cursor.getString (43));
        p.setNM_Ramo_Atividade (cursor.getString (44));
        p.setNM_Banco (cursor.getString (45));
        p.setCD_Carteira (cursor.getString (46));
        p.setCD_Conta_Corrente (cursor.getString (47));
        p.setPE_PIS_COFINS (cursor.getDouble (48));
        p.setDM_PIS_COFINS (cursor.getString (49));
        p.setPE_Comissao (cursor.getDouble (50));
        p.setDM_Tipo_Faturamento (cursor.getString (51));
        p.setNR_QT_Notas_Fiscais (cursor.getInt (52));
        p.setVL_Taxa_Despacho (cursor.getDouble (53));
        p.setOID_Unidade_Operacional (cursor.getInt (54));
        p.setDM_Situacao (cursor.getString (55));
        p.setTX_Instrucao_Cobranca (cursor.getString (56));
        p.setVL_Limite_Credito_Adicional (cursor.getDouble (57));
        p.setVL_Minimo_Compra (cursor.getDouble (58));
        p.setNR_Duplicatas_Vencidas (cursor.getInt (59));
        p.setDT_Cadastro (FormataData.formataDataBT (cursor.getDate (60)));
        p.setDT_Alteracao (FormataData.formataDataBT (cursor.getDate ("DT_Alteracao")));
        p.setDT_Desativado (FormataData.formataDataBT (cursor.getDate ("DT_Desativado")));
        p.setDT_Validade_Cadastro (FormataData.formataDataBT (cursor.getDate (61)));
        p.setDM_Tracking (cursor.getString (62));
        p.setOid_Pessoa_Parceiro (cursor.getString (63));
        p.setNM_Endereco (cursor.getString (64));
        p.setOID_Cidade (String.valueOf (cursor.getInt (65)));
        CidadeBean cidade = CidadeBean.getByOID (cursor.getInt (65));
        p.setNM_Cidade (cidade.getNM_Cidade ());
        p.setCD_Estado (cidade.getCD_Estado ());
        p.setOid_Produto (cursor.getInt ("oid_Produto"));
        p.setDM_RCTRC (cursor.getString ("DM_RCTRC"));
        p.setDM_RCTR_VI (cursor.getString ("DM_RCTR_VI"));
        p.setDM_RCTA (cursor.getString ("DM_RCTA"));
        p.setDM_RCTR_DC (cursor.getString ("DM_RCTR_DC"));
        p.setCD_Cliente_Palm (formataCodigoCliente (cursor.getString ("CD_Cliente_Palm")));
        p.setOid_Condicao_Pagamento (cursor.getInt ("oid_Condicao_Pagamento"));
        p.setDM_Forma_Pagamento (cursor.getString ("DM_Forma_Pagamento"));
        p.setDM_Monitoramento (cursor.getString ("DM_Monitoramento"));
        p.setDM_Resp_Vendedor (cursor.getString ("DM_Resp_Vendedor"));
        p.setOID_Modal (cursor.getInt ("OID_Modal"));
        p.setDM_Wms (cursor.getString ("DM_Dms"));

        p.setNR_Dias_Bloqueio(cursor.getInt ("NR_Dias_Bloqueio"));
        p.setDT_Ultimo_Movimento(FormataData.formataDataBT(cursor.getString("dt_ultimo_movimento")));
        if(!Utilitaria.doValida(cursor.getString("dt_ultimo_movimento"))){
        	p.setDT_Ultimo_Movimento(update_Movimentacao(p.getOID_Cliente()));
        }

      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final List getByNM_Razao_Social (String NM_Razao_Social) throws Exception {
    Connection conn = null;
    ExecutaSQL executasql = new ExecutaSQL ();
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
      executasql.setConnection (conn);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    List Cliente_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                                   ");
      buff.append ("   Clientes.OID_Cliente,                 ");
      buff.append ("   Clientes.OID_Cliente_Pagador,         ");
      buff.append ("	Clientes.OID_Pessoa,                  ");
      buff.append ("	Clientes.OID_Vendedor,                ");
      buff.append ("	Clientes.OID_Carteira,                ");
      buff.append ("	Clientes.OID_Grupo_Economico,         ");
      buff.append ("	Clientes.OID_Ramo_Atividade,          ");
      buff.append ("	Clientes.DM_Tipo_Cobranca,            ");
      buff.append ("	Clientes.DM_Condicao_Vencimento,      ");
      buff.append ("	Clientes.DM_Fatura_Segunda,           ");
      buff.append ("	Clientes.DM_Fatura_Terca,             ");
      buff.append ("	Clientes.DM_Fatura_Quarta,            ");
      buff.append ("	Clientes.DM_Fatura_Quinta,            ");
      buff.append ("	Clientes.DM_Fatura_Sexta,             ");
      buff.append ("	Clientes.DM_Vencimento_Segunda,       ");
      buff.append ("	Clientes.DM_Vencimento_Terca,         ");
      buff.append ("	Clientes.DM_Vencimento_Quarta,        ");
      buff.append ("	Clientes.DM_Vencimento_Quinta,        ");
      buff.append ("	Clientes.DM_Vencimento_Sexta,         ");
      buff.append ("	Clientes.DM_FOB_Dirigido,             ");
      buff.append ("	Clientes.DM_Isencao_ICMS,             ");
      buff.append ("	Clientes.DM_Isencao_Seguro,           ");
      buff.append ("	Clientes.DM_Credito,                  ");
      buff.append ("	Clientes.NR_Conta_Contabil,           ");
      buff.append ("	Clientes.DM_Compr_Entrega_Fatura,     ");
      buff.append ("	Clientes.DM_Tipo_Emissao,             ");
      buff.append ("	Clientes.VL_Minimo_Fatura,            ");
      buff.append ("	Clientes.VL_Maximo_Fatura,            ");
      buff.append ("	Clientes.VL_Limite_Credito,           ");
      buff.append ("	Clientes.NR_Minimo_CTO_Fatura,        ");
      buff.append ("	Clientes.NR_Maximo_CTO_Fatura,        ");
      buff.append ("	Clientes.PE_Juros_Cobranca,           ");
      buff.append ("	Clientes.VL_Taxa_Cobranca,            ");
      buff.append ("	Clientes.NR_Peso_Cubado,              ");
      buff.append ("	Clientes.NR_Dias_Vencimento,          ");
      buff.append ("	Clientes.NR_Dias_Protesto,            ");
      buff.append ("	Pessoas.NR_CNPJ_CPF,                  ");
      buff.append ("	Pessoas.NM_Razao_Social,              ");
      buff.append ("	Pessoas.NM_Fantasia,                  ");
      buff.append ("	Grupos_Economicos.CD_Grupo_Economico, ");
      buff.append ("	Grupos_Economicos.NM_Grupo_Economico, ");
      buff.append ("	Ramos_Atividades.CD_Ramo_Atividade,   ");
      buff.append ("	Ramos_Atividades.NM_Ramo_Atividade,   ");
      buff.append ("	Cidades.NM_Cidade,                    ");
      buff.append ("   Clientes.VL_Limite_Credito_Adicional, ");
      buff.append (" 	Clientes.VL_Minimo_Compra,     ");
      buff.append (" 	Clientes.NR_Duplicatas_Vencidas, ");
      buff.append (" 	Clientes.DT_Cadastro,    		 ");
      buff.append (" 	Clientes.DT_Validade_Cadastro, ");
      buff.append ("	Pessoa_Vendedor.NM_Razao_Social as NM_Vendedor,");
      buff.append ("	Clientes.OID_Segmento,                 ");
      buff.append ("	Pessoas.NM_Endereco, ");
      buff.append ("	Estados.CD_Estado, ");
      buff.append ("	Estados.NM_Estado, ");
      buff.append ("	Paises.NM_Pais, ");
      buff.append ("	Paises.CD_Pais, ");
      buff.append ("	Cidades.CD_Cidade, ");
      buff.append ("	Cidades.oid_cidade, ");
      buff.append ("   Clientes.CD_Cliente_Palm, ");
      buff.append ("   Clientes.oid_Condicao_Pagamento, ");
      buff.append ("   Clientes.DM_Forma_Pagamento,     ");
      buff.append ("   Clientes.DT_Alteracao,           ");
      buff.append ("   Clientes.DT_Desativado,          ");
      buff.append ("   Clientes.DM_Resp_Vendedor,        ");
      buff.append ("   Clientes.OID_Modal,        ");
      buff.append ("   Clientes.DM_Wms        ");
      buff.append (" FROM Clientes, Pessoas, Pessoas Pessoa_Vendedor, Cidades, Ramos_Atividades, " +
                   " Grupos_Economicos, Regioes_Estados, Estados, Regioes_Paises, Paises ");
      buff.append (" WHERE Clientes.OID_Pessoa = Pessoas.OID_Pessoa             ");
      buff.append (" AND   Clientes.OID_Vendedor = Pessoa_Vendedor.OID_Pessoa   ");
      buff.append (" AND   Pessoas.OID_Cidade = Cidades.OID_Cidade ");
      buff.append (" AND   Clientes.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");
      buff.append (" AND   Clientes.OID_Grupo_Economico = Grupos_Economicos.OID_Grupo_Economico  ");
      buff.append (" AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
      buff.append (" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
      buff.append (" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
      buff.append (" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
      buff.append (" AND Cidades.OID_Cidade = Pessoas.oid_Cidade");
      buff.append (" AND Pessoas.NM_Razao_Social LIKE'");
      buff.append (NM_Razao_Social);
      buff.append ("%'");
      buff.append (" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      while (cursor.next ()) {
        ClienteBean p = new ClienteBean ();
        p.setOID_Cliente (cursor.getString (1));
        p.setOID_Cliente_Pagador (cursor.getString (2));
        p.setOID_Pessoa (cursor.getString (3));
        p.setOID_Vendedor (cursor.getString (4));
        p.setOID_Carteira (cursor.getInt (5));
        p.setOID_Grupo_Economico (cursor.getInt (6));
        p.setOID_Ramo_Atividade (cursor.getInt (7));
        p.setDM_Tipo_Cobranca (cursor.getString (8));
        p.setDM_Condicao_Vencimento (cursor.getString (9));
        p.setDM_Fatura_Segunda (cursor.getString (10));
        p.setDM_Fatura_Terca (cursor.getString (11));
        p.setDM_Fatura_Quarta (cursor.getString (12));
        p.setDM_Fatura_Quinta (cursor.getString (13));
        p.setDM_Fatura_Sexta (cursor.getString (14));
        p.setDM_Vencimento_Segunda (cursor.getString (15));
        p.setDM_Vencimento_Terca (cursor.getString (16));
        p.setDM_Vencimento_Quarta (cursor.getString (17));
        p.setDM_Vencimento_Quinta (cursor.getString (18));
        p.setDM_Vencimento_Sexta (cursor.getString (19));
        p.setDM_FOB_Dirigido (cursor.getString (20));
        p.setDM_Isencao_ICMS (cursor.getString (21));
        p.setDM_Isencao_Seguro (cursor.getString (22));
        p.setDM_Credito (cursor.getString (23));
        p.setNR_Conta_Contabil (cursor.getString (24));
        p.setDM_Compr_Entrega_Fatura (cursor.getString (25));
        p.setDM_Tipo_Emissao (cursor.getString (26));
        p.setVL_Minimo_Fatura (cursor.getDouble (27));
        p.setVL_Maximo_Fatura (cursor.getDouble (28));
        p.setVL_Limite_Credito (new DuplicataBD (executasql).getLimiteCredito (p.getOID_Pessoa () , Data.manipulaDias (Data.getDataDMY () , -30) , Data.getDataDMY ()));
        p.setNR_Minimo_CTO_Fatura (cursor.getInt (30));
        p.setNR_Maximo_CTO_Fatura (cursor.getInt (31));
        p.setPE_Juros_Cobranca (cursor.getDouble (32));
        p.setVL_Taxa_Cobranca (cursor.getDouble (33));
        p.setNR_Peso_Cubado (cursor.getInt (34));
        p.setNR_Dias_Vencimento (cursor.getInt (35));
        p.setNR_Dias_Protesto (cursor.getInt (36));
        p.setNR_CNPJ_CPF (cursor.getString (37));
        p.setNM_Razao_Social (cursor.getString (38));
        p.setNM_Fantasia (cursor.getString (39));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia (p.getNM_Razao_Social ());
        }
        p.setCD_Grupo_Economico (cursor.getString (40));
        p.setNM_Grupo_Economico (cursor.getString (41));
        p.setCD_Ramo_Atividade (cursor.getString (42));
        p.setNM_Ramo_Atividade (cursor.getString (43));
        p.setNM_Cidade (cursor.getString (44));
        p.setVL_Limite_Credito_Adicional (cursor.getDouble (45));
        p.setVL_Minimo_Compra (cursor.getDouble (46));
        p.setNR_Duplicatas_Vencidas (cursor.getInt (47));
        p.setDT_Cadastro (FormataData.formataDataBT (cursor.getDate (48)));
        p.setDT_Alteracao (FormataData.formataDataBT (cursor.getDate ("DT_Alteracao")));
        p.setDT_Desativado (FormataData.formataDataBT (cursor.getDate ("DT_Desativado")));
        p.setDT_Validade_Cadastro (FormataData.formataDataBT (cursor.getDate (49)));
        p.setNM_Razao_Social_Vendedor (cursor.getString (50));
        p.setOID_Segmento (cursor.getInt (51));
        p.setNM_Endereco (cursor.getString (52));
        p.setCD_Estado (cursor.getString (53));
        p.setNM_Estado (cursor.getString (54));
        p.setCD_Pais (cursor.getString (55));
        p.setNM_Pais (cursor.getString (56));
        p.setNM_Cidade_Estado_Pais (p.getNM_Cidade ().trim () + " - " + p.CD_Estado.trim () + " - " + p.NM_Pais.trim ());
        p.setCD_Cidade (cursor.getString (57));
        p.setOID_Cidade (cursor.getString (58));
        p.setCD_Cliente_Palm (formataCodigoCliente (cursor.getString ("CD_Cliente_Palm")));
        p.setOid_Condicao_Pagamento (cursor.getInt ("oid_Condicao_Pagamento"));
        p.setDM_Forma_Pagamento (cursor.getString ("DM_Forma_Pagamento"));
        p.setDM_Resp_Vendedor (cursor.getString ("DM_Resp_Vendedor"));
        p.setOID_Modal (cursor.getInt ("OID_Modal"));
        p.setDM_Wms (cursor.getString ("DM_Wms"));
        Cliente_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Cliente_Lista;
  }

  public static final List getByNM_Razao_Social_WMS (String NM_Razao_Social) throws Exception {
    Connection conn = null;
    ExecutaSQL executasql = new ExecutaSQL ();
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
      executasql.setConnection (conn);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    List Cliente_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                                   ");
      buff.append ("   Clientes.OID_Cliente,                 ");
      buff.append ("   Clientes.OID_Cliente_Pagador,         ");
      buff.append ("	Clientes.OID_Pessoa,                  ");
      buff.append ("	Clientes.OID_Vendedor,                ");
      buff.append ("	Clientes.OID_Carteira,                ");
      buff.append ("	Clientes.OID_Grupo_Economico,         ");
      buff.append ("	Clientes.OID_Ramo_Atividade,          ");
      buff.append ("	Clientes.DM_Tipo_Cobranca,            ");
      buff.append ("	Clientes.DM_Condicao_Vencimento,      ");
      buff.append ("	Clientes.DM_Fatura_Segunda,           ");
      buff.append ("	Clientes.DM_Fatura_Terca,             ");
      buff.append ("	Clientes.DM_Fatura_Quarta,            ");
      buff.append ("	Clientes.DM_Fatura_Quinta,            ");
      buff.append ("	Clientes.DM_Fatura_Sexta,             ");
      buff.append ("	Clientes.DM_Vencimento_Segunda,       ");
      buff.append ("	Clientes.DM_Vencimento_Terca,         ");
      buff.append ("	Clientes.DM_Vencimento_Quarta,        ");
      buff.append ("	Clientes.DM_Vencimento_Quinta,        ");
      buff.append ("	Clientes.DM_Vencimento_Sexta,         ");
      buff.append ("	Clientes.DM_FOB_Dirigido,             ");
      buff.append ("	Clientes.DM_Isencao_ICMS,             ");
      buff.append ("	Clientes.DM_Isencao_Seguro,           ");
      buff.append ("	Clientes.DM_Credito,                  ");
      buff.append ("	Clientes.NR_Conta_Contabil,           ");
      buff.append ("	Clientes.DM_Compr_Entrega_Fatura,     ");
      buff.append ("	Clientes.DM_Tipo_Emissao,             ");
      buff.append ("	Clientes.VL_Minimo_Fatura,            ");
      buff.append ("	Clientes.VL_Maximo_Fatura,            ");
      buff.append ("	Clientes.VL_Limite_Credito,           ");
      buff.append ("	Clientes.NR_Minimo_CTO_Fatura,        ");
      buff.append ("	Clientes.NR_Maximo_CTO_Fatura,        ");
      buff.append ("	Clientes.PE_Juros_Cobranca,           ");
      buff.append ("	Clientes.VL_Taxa_Cobranca,            ");
      buff.append ("	Clientes.NR_Peso_Cubado,              ");
      buff.append ("	Clientes.NR_Dias_Vencimento,          ");
      buff.append ("	Clientes.NR_Dias_Protesto,            ");
      buff.append ("	Pessoas.NR_CNPJ_CPF,                  ");
      buff.append ("	Pessoas.NM_Razao_Social,              ");
      buff.append ("	Pessoas.NM_Fantasia,                  ");
      buff.append ("	Grupos_Economicos.CD_Grupo_Economico, ");
      buff.append ("	Grupos_Economicos.NM_Grupo_Economico, ");
      buff.append ("	Ramos_Atividades.CD_Ramo_Atividade,   ");
      buff.append ("	Ramos_Atividades.NM_Ramo_Atividade,   ");
      buff.append ("	Cidades.NM_Cidade,                    ");
      buff.append ("   Clientes.VL_Limite_Credito_Adicional, ");
      buff.append (" 	Clientes.VL_Minimo_Compra,     ");
      buff.append (" 	Clientes.NR_Duplicatas_Vencidas, ");
      buff.append (" 	Clientes.DT_Cadastro,    		 ");
      buff.append (" 	Clientes.DT_Validade_Cadastro, ");
      buff.append ("	Pessoa_Vendedor.NM_Razao_Social as NM_Vendedor,");
      buff.append ("	Clientes.OID_Segmento,                 ");
      buff.append ("	Pessoas.NM_Endereco, ");
      buff.append ("	Estados.CD_Estado, ");
      buff.append ("	Estados.NM_Estado, ");
      buff.append ("	Paises.NM_Pais, ");
      buff.append ("	Paises.CD_Pais, ");
      buff.append ("	Cidades.CD_Cidade, ");
      buff.append ("	Cidades.oid_cidade, ");
      buff.append ("   Clientes.CD_Cliente_Palm, ");
      buff.append ("   Clientes.oid_Condicao_Pagamento, ");
      buff.append ("   Clientes.DM_Forma_Pagamento,     ");
      buff.append ("   Clientes.DT_Alteracao,           ");
      buff.append ("   Clientes.DT_Desativado,          ");
      buff.append ("   Clientes.DM_Resp_Vendedor,        ");
      buff.append ("   Clientes.OID_Modal,        ");
      buff.append ("   Clientes.DM_Wms,        ");
      buff.append ("	Pessoas.NM_Inscricao_Estadual, ");
      buff.append ("	Pessoas.NM_Bairro ");
      buff.append (" FROM Clientes, Pessoas, Cidades, Estados, Regioes_Estados, Paises, Regioes_Paises, Grupos_Economicos, Ramos_Atividades, Pessoas Pessoa_Vendedor  ");
      buff.append (" WHERE Clientes.OID_Pessoa = Pessoas.OID_Pessoa             ");
      buff.append (" AND   Clientes.OID_Vendedor = Pessoa_Vendedor.OID_Pessoa   ");
      buff.append (" AND   Pessoas.OID_Cidade = Cidades.OID_Cidade ");
      buff.append (" AND   Clientes.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");
      buff.append (" AND   Clientes.OID_Grupo_Economico = Grupos_Economicos.OID_Grupo_Economico  ");
      buff.append (" AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
      buff.append (" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
      buff.append (" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
      buff.append (" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
      buff.append (" AND Cidades.OID_Cidade = Pessoas.oid_Cidade");
      buff.append (" AND Pessoas.NM_Razao_Social LIKE'");
      buff.append (NM_Razao_Social);
      buff.append ("%' and dm_wms = 'S' ");
      buff.append (" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      while (cursor.next ()) {
        ClienteBean p = new ClienteBean ();
        p.setOID_Cliente (cursor.getString (1));
        p.setOID_Cliente_Pagador (cursor.getString (2));
        p.setOID_Pessoa (cursor.getString (3));
        p.setOID_Vendedor (cursor.getString (4));
        p.setOID_Carteira (cursor.getInt (5));
        p.setOID_Grupo_Economico (cursor.getInt (6));
        p.setOID_Ramo_Atividade (cursor.getInt (7));
        p.setDM_Tipo_Cobranca (cursor.getString (8));
        p.setDM_Condicao_Vencimento (cursor.getString (9));
        p.setDM_Fatura_Segunda (cursor.getString (10));
        p.setDM_Fatura_Terca (cursor.getString (11));
        p.setDM_Fatura_Quarta (cursor.getString (12));
        p.setDM_Fatura_Quinta (cursor.getString (13));
        p.setDM_Fatura_Sexta (cursor.getString (14));
        p.setDM_Vencimento_Segunda (cursor.getString (15));
        p.setDM_Vencimento_Terca (cursor.getString (16));
        p.setDM_Vencimento_Quarta (cursor.getString (17));
        p.setDM_Vencimento_Quinta (cursor.getString (18));
        p.setDM_Vencimento_Sexta (cursor.getString (19));
        p.setDM_FOB_Dirigido (cursor.getString (20));
        p.setDM_Isencao_ICMS (cursor.getString (21));
        p.setDM_Isencao_Seguro (cursor.getString (22));
        p.setDM_Credito (cursor.getString (23));
        p.setNR_Conta_Contabil (cursor.getString (24));
        p.setDM_Compr_Entrega_Fatura (cursor.getString (25));
        p.setDM_Tipo_Emissao (cursor.getString (26));
        p.setVL_Minimo_Fatura (cursor.getDouble (27));
        p.setVL_Maximo_Fatura (cursor.getDouble (28));
        p.setVL_Limite_Credito (new DuplicataBD (executasql).getLimiteCredito (p.getOID_Pessoa () , Data.manipulaDias (Data.getDataDMY () , -30) , Data.getDataDMY ()));
        p.setNR_Minimo_CTO_Fatura (cursor.getInt (30));
        p.setNR_Maximo_CTO_Fatura (cursor.getInt (31));
        p.setPE_Juros_Cobranca (cursor.getDouble (32));
        p.setVL_Taxa_Cobranca (cursor.getDouble (33));
        p.setNR_Peso_Cubado (cursor.getInt (34));
        p.setNR_Dias_Vencimento (cursor.getInt (35));
        p.setNR_Dias_Protesto (cursor.getInt (36));
        p.setNR_CNPJ_CPF (cursor.getString (37));
        p.setNM_Razao_Social (cursor.getString (38));
        p.setNM_Fantasia (cursor.getString (39));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia (p.getNM_Razao_Social ());
        }
        p.setCD_Grupo_Economico (cursor.getString (40));
        p.setNM_Grupo_Economico (cursor.getString (41));
        p.setCD_Ramo_Atividade (cursor.getString (42));
        p.setNM_Ramo_Atividade (cursor.getString (43));
        p.setNM_Cidade (cursor.getString (44));
        p.setVL_Limite_Credito_Adicional (cursor.getDouble (45));
        p.setVL_Minimo_Compra (cursor.getDouble (46));
        p.setNR_Duplicatas_Vencidas (cursor.getInt (47));
        p.setDT_Cadastro (FormataData.formataDataBT (cursor.getDate (48)));
        p.setDT_Alteracao (FormataData.formataDataBT (cursor.getDate ("DT_Alteracao")));
        p.setDT_Desativado (FormataData.formataDataBT (cursor.getDate ("DT_Desativado")));
        p.setDT_Validade_Cadastro (FormataData.formataDataBT (cursor.getDate (49)));
        p.setNM_Razao_Social_Vendedor (cursor.getString (50));
        p.setOID_Segmento (cursor.getInt (51));
        p.setNM_Endereco (cursor.getString (52));
        p.setCD_Estado (cursor.getString (53));
        p.setNM_Estado (cursor.getString (54));
        p.setCD_Pais (cursor.getString (55));
        p.setNM_Pais (cursor.getString (56));
        p.setNM_Cidade_Estado_Pais (p.getNM_Cidade ().trim () + " - " + p.CD_Estado.trim () + " - " + p.NM_Pais.trim ());
        p.setCD_Cidade (cursor.getString (57));
        p.setOID_Cidade (cursor.getString (58));
        p.setCD_Cliente_Palm (formataCodigoCliente (cursor.getString ("CD_Cliente_Palm")));
        p.setOid_Condicao_Pagamento (cursor.getInt ("oid_Condicao_Pagamento"));
        p.setDM_Forma_Pagamento (cursor.getString ("DM_Forma_Pagamento"));
        p.setDM_Resp_Vendedor (cursor.getString ("DM_Resp_Vendedor"));
        p.setOID_Modal (cursor.getInt ("OID_Modal"));
        p.setDM_Wms (cursor.getString ("DM_Wms"));
        p.setNM_Inscricao_Estadual (cursor.getString ("NM_Inscricao_Estadual"));
        p.setNM_Bairro (cursor.getString ("NM_Bairro"));
        Cliente_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Cliente_Lista;
  }

  public final List getListByRecord (HttpServletRequest request) throws Exception {
    Connection conn = null;
    ExecutaSQL executasql = new ExecutaSQL ();
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
      executasql.setConnection (conn);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    String oid_Cliente = request.getParameter ("oid_Cliente");
    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Vendedor = request.getParameter ("oid_Vendedor");
    String cdClientePalm = request.getParameter ("FT_CD_Cliente_Palm");
    String nrCnpjCpf = request.getParameter ("FT_NR_CNPJ_CPF");
    String nmRazaoSocial = request.getParameter ("FT_NM_Razao_Social");
    String nmFantasia = request.getParameter ("FT_NM_Fantasia");
    String nrCEP = request.getParameter ("FT_NR_CEP");
    String nmEndereco = request.getParameter ("FT_NM_Endereco");
    String nmBairro = request.getParameter ("FT_NM_Bairro");
    String nmCidade = request.getParameter ("FT_NM_Cidade");
    String dmCredito = request.getParameter ("FT_DM_Credito");
    String orderByField = request.getParameter ("orderByField");
    List Cliente_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                                   ");
      buff.append ("   Clientes.OID_Cliente,                 ");
      buff.append ("   Clientes.OID_Cliente_Pagador,         ");
      buff.append ("   Clientes.OID_Pessoa,                  ");
      buff.append ("   Clientes.OID_Vendedor,                ");
      buff.append ("   Clientes.OID_Carteira,                ");
      buff.append ("   Clientes.OID_Grupo_Economico,         ");
      buff.append ("   Clientes.OID_Ramo_Atividade,          ");
      buff.append ("   Clientes.DM_Tipo_Cobranca,            ");
      buff.append ("   Clientes.DM_Condicao_Vencimento,      ");
      buff.append ("   Clientes.DM_Fatura_Segunda,           ");
      buff.append ("   Clientes.DM_Fatura_Terca,             ");
      buff.append ("   Clientes.DM_Fatura_Quarta,            ");
      buff.append ("   Clientes.DM_Fatura_Quinta,            ");
      buff.append ("   Clientes.DM_Fatura_Sexta,             ");
      buff.append ("   Clientes.DM_Vencimento_Segunda,       ");
      buff.append ("   Clientes.DM_Vencimento_Terca,         ");
      buff.append ("   Clientes.DM_Vencimento_Quarta,        ");
      buff.append ("   Clientes.DM_Vencimento_Quinta,        ");
      buff.append ("   Clientes.DM_Vencimento_Sexta,         ");
      buff.append ("   Clientes.DM_FOB_Dirigido,             ");
      buff.append ("   Clientes.DM_Isencao_ICMS,             ");
      buff.append ("   Clientes.DM_Isencao_Seguro,           ");
      buff.append ("   Clientes.DM_Credito,                  ");
      buff.append ("   Clientes.NR_Conta_Contabil,           ");
      buff.append ("   Clientes.DM_Compr_Entrega_Fatura,     ");
      buff.append ("   Clientes.DM_Tipo_Emissao,             ");
      buff.append ("   Clientes.VL_Minimo_Fatura,            ");
      buff.append ("   Clientes.VL_Maximo_Fatura,            ");
      buff.append ("   Clientes.VL_Limite_Credito,           ");
      buff.append ("   Clientes.NR_Minimo_CTO_Fatura,        ");
      buff.append ("   Clientes.NR_Maximo_CTO_Fatura,        ");
      buff.append ("   Clientes.PE_Juros_Cobranca,           ");
      buff.append ("   Clientes.VL_Taxa_Cobranca,            ");
      buff.append ("   Clientes.NR_Peso_Cubado,              ");
      buff.append ("   Clientes.NR_Dias_Vencimento,          ");
      buff.append ("   Clientes.NR_Dias_Protesto,            ");
      buff.append ("   Pessoas.NR_CNPJ_CPF,                  ");
      buff.append ("   Pessoas.NM_Razao_Social,              ");
      buff.append ("   Pessoas.NM_Fantasia,                  ");
      buff.append ("   Grupos_Economicos.CD_Grupo_Economico, ");
      buff.append ("   Grupos_Economicos.NM_Grupo_Economico, ");
      buff.append ("   Ramos_Atividades.CD_Ramo_Atividade,   ");
      buff.append ("   Ramos_Atividades.NM_Ramo_Atividade,   ");
      buff.append ("   Cidades.NM_Cidade,                    ");
      buff.append ("   Clientes.VL_Limite_Credito_Adicional, ");
      buff.append ("   Clientes.VL_Minimo_Compra,       ");
      buff.append ("   Clientes.NR_Duplicatas_Vencidas, ");
      buff.append ("   Clientes.DT_Cadastro,            ");
      buff.append ("   Clientes.DT_Validade_Cadastro,   ");
      buff.append ("   Pessoa_Vendedor.NM_Razao_Social as NM_Vendedor,");
      buff.append ("   Clientes.OID_Segmento, ");
      buff.append ("   Pessoas.NM_Endereco, ");
      buff.append ("   Estados.CD_Estado, ");
      buff.append ("   Estados.NM_Estado, ");
      buff.append ("   Paises.NM_Pais, ");
      buff.append ("   Paises.CD_Pais, ");
      buff.append ("   Cidades.CD_Cidade, ");
      buff.append ("   Cidades.oid_cidade, ");
      buff.append ("   Clientes.CD_Cliente_Palm, ");
      buff.append ("   Clientes.oid_Condicao_Pagamento, ");
      buff.append ("   Clientes.DM_Forma_Pagamento, ");
      buff.append ("   Pessoas.NM_Bairro, ");
      buff.append ("   Pessoas.NR_CEP, ");
      buff.append ("   Cidades.NM_Cidade, ");
      buff.append ("   Clientes.DT_Alteracao,  ");
      buff.append ("   Clientes.DT_Desativado, ");
      buff.append ("   Clientes.DM_Resp_Vendedor, ");
      buff.append ("   Clientes.OID_Modal,        ");
      buff.append ("   Clientes.DM_Wms,            ");
      buff.append ("	Clientes.NR_Dias_Bloqueio,            ");
      buff.append ("	Clientes.DT_Ultimo_Movimento            ");
      buff.append (" FROM Clientes, Pessoas, Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises, Grupos_Economicos, Ramos_Atividades, Pessoas Pessoa_Vendedor  ");
      buff.append (" WHERE Clientes.OID_Pessoa = Pessoas.OID_Pessoa ");
      buff.append ("   AND Clientes.OID_Vendedor = Pessoa_Vendedor.OID_Pessoa ");
      buff.append ("   AND Pessoas.OID_Cidade = Cidades.OID_Cidade ");
      buff.append ("   AND Clientes.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade ");
      buff.append ("   AND Clientes.OID_Grupo_Economico = Grupos_Economicos.OID_Grupo_Economico ");
      buff.append ("   AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
      buff.append ("   AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
      buff.append ("   AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
      buff.append ("   AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
      buff.append ("   AND Cidades.OID_Cidade = Pessoas.oid_Cidade");
      if (JavaUtil.doValida (oid_Pessoa) || JavaUtil.doValida (oid_Cliente) || JavaUtil.doValida (oid_Vendedor) || JavaUtil.doValida (nrCnpjCpf) || JavaUtil.doValida (cdClientePalm)) {
        if (JavaUtil.doValida (oid_Cliente)) {
          buff.append (" AND Clientes.oid_Cliente = '" + oid_Cliente + "'");
        }
        else if (JavaUtil.doValida (oid_Pessoa)) {
          buff.append (" AND Clientes.oid_Pessoa = '" + oid_Pessoa + "'");
        }
        else if (JavaUtil.doValida (nrCnpjCpf)) {
          buff.append (" AND Pessoas.NR_CNPJ_CPF = '" + nrCnpjCpf + "'");
        }
        else if (JavaUtil.doValida (cdClientePalm)) {
          buff.append (" AND Clientes.CD_Cliente_Palm = '" + cdClientePalm.replaceAll ("-" , "") + "'");
        }
        else if (JavaUtil.doValida (oid_Vendedor)) {
            buff.append (" AND Clientes.oid_Vendedor = '" + oid_Vendedor + "'");
          }
      }
      else {
        if (JavaUtil.doValida (nmRazaoSocial)) {
          buff.append (" AND Pessoas.NM_Razao_Social LIKE '" + nmRazaoSocial + "%'");
        }
        if (JavaUtil.doValida (nmFantasia)) {
          buff.append (" AND Pessoas.NM_Fantasia LIKE '" + nmFantasia + "%'");
        }
        if (JavaUtil.doValida (nrCEP)) {
          buff.append (" AND Pessoas.NR_CEP LIKE '%" + nrCEP + "%'");
        }
        if (JavaUtil.doValida (nmEndereco)) {
          buff.append (" AND Pessoas.NM_Endereco LIKE '" + nmEndereco + "%'");
        }
        if (JavaUtil.doValida (nmBairro)) {
          buff.append (" AND Pessoas.NM_Bairro LIKE '" + nmBairro + "%'");
        }
        if (JavaUtil.doValida (nmCidade)) {
          buff.append (" AND Cidades.NM_Cidade LIKE '" + nmCidade + "%'");
        }
        if (JavaUtil.doValida (dmCredito)) {
          buff.append (" AND Clientes.DM_Credito = '" + dmCredito + "'");
        }

      }
      if ("NR_CNPJ_CPF".equals (orderByField)) {
        buff.append (" ORDER BY Pessoas.NR_CNPJ_CPF ");
      }
      else if ("CD_Cliente_Palm".equals (orderByField)) {
        buff.append (" ORDER BY Clientes.CD_Cliente_Palm ");
      }
      else if ("NM_Razao_Social".equals (orderByField)) {
        buff.append (" ORDER BY Pessoas.NM_Razao_Social ");
      }
      else if ("NM_Fantasia".equals (orderByField)) {
        buff.append (" ORDER BY Pessoas.NM_Fantasia ");
      }
      else if ("NR_CEP".equals (orderByField)) {
        buff.append (" ORDER BY Pessoas.NR_CEP ");
      }
      else if ("NM_Endereco".equals (orderByField)) {
        buff.append (" ORDER BY Pessoas.NM_Endereco ");
      }
      else if ("NM_Bairro".equals (orderByField)) {
        buff.append (" ORDER BY Pessoas.NM_Bairro ");
      }
      else if ("DM_Wms".equals (orderByField)) {
        buff.append ("Pessoas.DM_Wms ");
      }
      else if ("NM_Cidade".equals (orderByField)) {
        buff.append (" ORDER BY Cidades.NM_Cidade ");
      }
      else {
        buff.append (" ORDER BY Pessoas.NM_Razao_Social ");
      }
      //System.out.println(buff);
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      while (cursor.next ()) {
        ClienteBean p = new ClienteBean ();
        p.setOID_Cliente (cursor.getString (1));
        p.setOID_Cliente_Pagador (cursor.getString (2));
        p.setOID_Pessoa (cursor.getString (3));
        p.setOID_Vendedor (cursor.getString (4));
        p.setOID_Carteira (cursor.getInt (5));
        p.setOID_Grupo_Economico (cursor.getInt (6));
        p.setOID_Ramo_Atividade (cursor.getInt (7));
        p.setDM_Tipo_Cobranca (cursor.getString (8));
        p.setDM_Condicao_Vencimento (cursor.getString (9));
        p.setDM_Fatura_Segunda (cursor.getString (10));
        p.setDM_Fatura_Terca (cursor.getString (11));
        p.setDM_Fatura_Quarta (cursor.getString (12));
        p.setDM_Fatura_Quinta (cursor.getString (13));
        p.setDM_Fatura_Sexta (cursor.getString (14));
        p.setDM_Vencimento_Segunda (cursor.getString (15));
        p.setDM_Vencimento_Terca (cursor.getString (16));
        p.setDM_Vencimento_Quarta (cursor.getString (17));
        p.setDM_Vencimento_Quinta (cursor.getString (18));
        p.setDM_Vencimento_Sexta (cursor.getString (19));
        p.setDM_FOB_Dirigido (cursor.getString (20));
        p.setDM_Isencao_ICMS (cursor.getString (21));
        p.setDM_Isencao_Seguro (cursor.getString (22));
        p.setDM_Credito (cursor.getString (23));
        p.setNR_Conta_Contabil (cursor.getString (24));
        p.setDM_Compr_Entrega_Fatura (cursor.getString (25));
        p.setDM_Tipo_Emissao (cursor.getString (26));
        p.setVL_Minimo_Fatura (cursor.getDouble (27));
        p.setVL_Maximo_Fatura (cursor.getDouble (28));
        p.setVL_Limite_Credito (new DuplicataBD (executasql).getLimiteCredito (p.getOID_Pessoa () , Data.manipulaDias (Data.getDataDMY () , -30) , Data.getDataDMY ()));
        p.setNR_Minimo_CTO_Fatura (cursor.getInt (30));
        p.setNR_Maximo_CTO_Fatura (cursor.getInt (31));
        p.setPE_Juros_Cobranca (cursor.getDouble (32));
        p.setVL_Taxa_Cobranca (cursor.getDouble (33));
        p.setNR_Peso_Cubado (cursor.getInt (34));
        p.setNR_Dias_Vencimento (cursor.getInt (35));
        p.setNR_Dias_Protesto (cursor.getInt (36));
        p.setNR_CNPJ_CPF (cursor.getString (37));
        p.setNM_Razao_Social (cursor.getString (38));
        p.setNM_Fantasia (JavaUtil.getValueDef (cursor.getString (39) , JavaUtil.trunc (p.getNM_Razao_Social () , 30)));
        p.setCD_Grupo_Economico (cursor.getString (40));
        p.setNM_Grupo_Economico (cursor.getString (41));
        p.setCD_Ramo_Atividade (cursor.getString (42));
        p.setNM_Ramo_Atividade (cursor.getString (43));
        p.setNM_Cidade (cursor.getString (44));
        p.setVL_Limite_Credito_Adicional (cursor.getDouble (45));
        p.setVL_Minimo_Compra (cursor.getDouble (46));
        p.setNR_Duplicatas_Vencidas (cursor.getInt (47));
        p.setDT_Cadastro (FormataData.formataDataBT (cursor.getDate (48)));
        p.setDT_Alteracao (FormataData.formataDataBT (cursor.getDate ("DT_Alteracao")));
        p.setDT_Desativado (FormataData.formataDataBT (cursor.getDate ("DT_Desativado")));
        p.setDT_Validade_Cadastro (FormataData.formataDataBT (cursor.getDate (49)));
        p.setNM_Razao_Social_Vendedor (cursor.getString (50));
        p.setOID_Segmento (cursor.getInt (51));
        p.setNM_Endereco (cursor.getString (52));
        p.setCD_Estado (cursor.getString (53));
        p.setNM_Estado (cursor.getString (54));
        p.setCD_Pais (cursor.getString (55));
        p.setNM_Pais (cursor.getString (56));
        p.setNM_Cidade_Estado_Pais (p.getNM_Cidade ().trim () + " - " + p.CD_Estado.trim () + " - " + p.NM_Pais.trim ());
        p.setCD_Cidade (cursor.getString (57));
        p.setOID_Cidade (cursor.getString (58));
        p.setCD_Cliente_Palm (formataCodigoCliente (cursor.getString ("CD_Cliente_Palm")));
        p.setOid_Condicao_Pagamento (cursor.getInt ("oid_Condicao_Pagamento"));
        p.setDM_Forma_Pagamento (cursor.getString ("DM_Forma_Pagamento"));
        p.setNM_Bairro (cursor.getString ("NM_Bairro"));
        p.setNR_CEP (cursor.getString ("NR_CEP"));
        p.setNM_Cidade (cursor.getString ("NM_Cidade"));
        p.setDM_Resp_Vendedor (cursor.getString ("DM_Resp_Vendedor"));
        p.setOID_Modal (cursor.getInt ("OID_Modal"));
        p.setDM_Wms (cursor.getString ("DM_Wms"));

        p.setNR_Dias_Bloqueio(cursor.getInt ("NR_Dias_Bloqueio"));
        p.setDT_Ultimo_Movimento(FormataData.formataDataBT(cursor.getString("dt_ultimo_movimento")));
        if(p.getNR_Dias_Bloqueio()>0 && JavaUtil.doValida(p.getDT_Ultimo_Movimento())){
      	  p.setDT_Bloqueio(Data.getSomaDiaData(p.getDT_Ultimo_Movimento(), p.getNR_Dias_Bloqueio()));
        } else if(p.getNR_Dias_Bloqueio()>0){
      	  p.setDT_Bloqueio(Data.getSomaDiaData(Data.getDataDMY(), p.getNR_Dias_Bloqueio()));
        } else {
      	  p.setDT_Bloqueio(Data.getSomaDiaData(Data.getDataDMY(), 30));
        }

        Cliente_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Cliente_Lista;
  }

  public static final List getByNR_CNPJ_CPF (String NR_CNPJ_CPF) throws Exception {
    Connection conn = null;
    ExecutaSQL executasql = new ExecutaSQL ();
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
      executasql.setConnection (conn);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    List Cliente_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                                   ");
      buff.append ("   Clientes.OID_Cliente,                 ");
      buff.append ("   Clientes.OID_Cliente_Pagador,         ");
      buff.append ("	Clientes.OID_Pessoa,                  ");
      buff.append ("	Clientes.OID_Vendedor,                ");
      buff.append ("	Clientes.OID_Carteira,                ");
      buff.append ("	Clientes.OID_Grupo_Economico,         ");
      buff.append ("	Clientes.OID_Ramo_Atividade,          ");
      buff.append ("	Clientes.DM_Tipo_Cobranca,            ");
      buff.append ("	Clientes.DM_Condicao_Vencimento,      ");
      buff.append ("	Clientes.DM_Fatura_Segunda,           ");
      buff.append ("	Clientes.DM_Fatura_Terca,             ");
      buff.append ("	Clientes.DM_Fatura_Quarta,            ");
      buff.append ("	Clientes.DM_Fatura_Quinta,            ");
      buff.append ("	Clientes.DM_Fatura_Sexta,             ");
      buff.append ("	Clientes.DM_Vencimento_Segunda,       ");
      buff.append ("	Clientes.DM_Vencimento_Terca,         ");
      buff.append ("	Clientes.DM_Vencimento_Quarta,        ");
      buff.append ("	Clientes.DM_Vencimento_Quinta,        ");
      buff.append ("	Clientes.DM_Vencimento_Sexta,         ");
      buff.append ("	Clientes.DM_FOB_Dirigido,             ");
      buff.append ("	Clientes.DM_Isencao_ICMS,             ");
      buff.append ("	Clientes.DM_Isencao_Seguro,           ");
      buff.append ("	Clientes.DM_Credito,                  ");
      buff.append ("	Clientes.NR_Conta_Contabil,           ");
      buff.append ("	Clientes.DM_Compr_Entrega_Fatura,     ");
      buff.append ("	Clientes.DM_Tipo_Emissao,             ");
      buff.append ("	Clientes.VL_Minimo_Fatura,            ");
      buff.append ("	Clientes.VL_Maximo_Fatura,            ");
      buff.append ("	Clientes.VL_Limite_Credito,           ");
      buff.append ("	Clientes.NR_Minimo_CTO_Fatura,        ");
      buff.append ("	Clientes.NR_Maximo_CTO_Fatura,        ");
      buff.append ("	Clientes.PE_Juros_Cobranca,           ");
      buff.append ("	Clientes.VL_Taxa_Cobranca,            ");
      buff.append ("	Clientes.NR_Peso_Cubado,              ");
      buff.append ("	Clientes.NR_Dias_Vencimento,          ");
      buff.append ("	Clientes.NR_Dias_Protesto,            ");
      buff.append ("	Pessoas.NR_CNPJ_CPF,                  ");
      buff.append ("	Pessoas.NM_Razao_Social,              ");
      buff.append ("	Pessoas.NM_Fantasia,                  ");
      buff.append ("	Grupos_Economicos.CD_Grupo_Economico, ");
      buff.append ("	Grupos_Economicos.NM_Grupo_Economico, ");
      buff.append ("	Ramos_Atividades.CD_Ramo_Atividade,   ");
      buff.append ("	Ramos_Atividades.NM_Ramo_Atividade,   ");
      buff.append ("   Clientes.VL_Limite_Credito_Adicional, ");
      buff.append (" 	Clientes.VL_Minimo_Compra,     		  ");
      buff.append (" 	Clientes.NR_Duplicatas_Vencidas, 	  ");
      buff.append (" 	Clientes.DT_Cadastro,    		 	  ");
      buff.append (" 	Clientes.DT_Validade_Cadastro, 		  ");
      buff.append ("	Clientes.OID_Segmento,                ");
      buff.append ("	Pessoas.NM_Endereco,                  ");
      buff.append ("	Cidades.NM_Cidade,                    ");
      buff.append ("   Clientes.CD_Cliente_Palm,             ");
      buff.append ("   Clientes.oid_Condicao_Pagamento,      ");
      buff.append ("   Clientes.DM_Forma_Pagamento,          ");
      buff.append ("   Clientes.DT_Alteracao,                ");
      buff.append ("   Clientes.DT_Desativado,               ");
      buff.append ("   Clientes.DM_Resp_Vendedor,             ");
      buff.append ("   Clientes.OID_Modal,             ");
      buff.append ("   Clientes.DM_Wms             ");
      buff.append (" FROM Clientes, Pessoas, Grupos_Economicos, Ramos_Atividades, Cidades ");
      buff.append (" WHERE Clientes.OID_Pessoa          = Pessoas.OID_Pessoa                    ");
      buff.append (" AND   Clientes.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");
      buff.append (" AND   Clientes.OID_Grupo_Economico = Grupos_Economicos.OID_Grupo_Economico  ");
      buff.append (" AND   Pessoas.OID_Cidade = Cidades.OID_Cidade  ");
      buff.append (" AND Pessoas.NR_CNPJ_CPF = '");
      buff.append (NR_CNPJ_CPF);
      buff.append ("'");
      buff.append (" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      while (cursor.next ()) {
        ClienteBean p = new ClienteBean ();
        p.setOID_Cliente (cursor.getString (1));
        p.setOID_Cliente_Pagador (cursor.getString (2));
        p.setOID_Pessoa (cursor.getString (3));
        p.setOID_Vendedor (cursor.getString (4));
        p.setOID_Carteira (cursor.getInt (5));
        p.setOID_Grupo_Economico (cursor.getInt (6));
        p.setOID_Ramo_Atividade (cursor.getInt (7));
        p.setDM_Tipo_Cobranca (cursor.getString (8));
        p.setDM_Condicao_Vencimento (cursor.getString (9));
        p.setDM_Fatura_Segunda (cursor.getString (10));
        p.setDM_Fatura_Terca (cursor.getString (11));
        p.setDM_Fatura_Quarta (cursor.getString (12));
        p.setDM_Fatura_Quinta (cursor.getString (13));
        p.setDM_Fatura_Sexta (cursor.getString (14));
        p.setDM_Vencimento_Segunda (cursor.getString (15));
        p.setDM_Vencimento_Terca (cursor.getString (16));
        p.setDM_Vencimento_Quarta (cursor.getString (17));
        p.setDM_Vencimento_Quinta (cursor.getString (18));
        p.setDM_Vencimento_Sexta (cursor.getString (19));
        p.setDM_FOB_Dirigido (cursor.getString (20));
        p.setDM_Isencao_ICMS (cursor.getString (21));
        p.setDM_Isencao_Seguro (cursor.getString (22));
        p.setDM_Credito (cursor.getString (23));
        p.setNR_Conta_Contabil (cursor.getString (24));
        p.setDM_Compr_Entrega_Fatura (cursor.getString (25));
        p.setDM_Tipo_Emissao (cursor.getString (26));
        p.setVL_Minimo_Fatura (cursor.getDouble (27));
        p.setVL_Maximo_Fatura (cursor.getDouble (28));
        p.setVL_Limite_Credito (new DuplicataBD (executasql).getLimiteCredito (p.getOID_Pessoa () , Data.manipulaDias (Data.getDataDMY () , -30) , Data.getDataDMY ()));
        p.setNR_Minimo_CTO_Fatura (cursor.getInt (30));
        p.setNR_Maximo_CTO_Fatura (cursor.getInt (31));
        p.setPE_Juros_Cobranca (cursor.getDouble (32));
        p.setVL_Taxa_Cobranca (cursor.getDouble (33));
        p.setNR_Peso_Cubado (cursor.getInt (34));
        p.setNR_Dias_Vencimento (cursor.getInt (35));
        p.setNR_Dias_Protesto (cursor.getInt (36));
        p.setNR_CNPJ_CPF (cursor.getString (37));
        p.setNM_Razao_Social (cursor.getString (38));
        p.setNM_Fantasia (cursor.getString (39));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia (p.getNM_Razao_Social ());
        }
        p.setCD_Grupo_Economico (cursor.getString (40));
        p.setNM_Grupo_Economico (cursor.getString (41));
        p.setCD_Ramo_Atividade (cursor.getString (42));
        p.setNM_Ramo_Atividade (cursor.getString (43));
        p.setVL_Limite_Credito_Adicional (cursor.getDouble (44));
        p.setVL_Minimo_Compra (cursor.getDouble (45));
        p.setNR_Duplicatas_Vencidas (cursor.getInt (46));
        p.setDT_Cadastro (FormataData.formataDataBT (cursor.getDate (47)));
        p.setDT_Alteracao (FormataData.formataDataBT (cursor.getDate ("DT_Alteracao")));
        p.setDT_Desativado (FormataData.formataDataBT (cursor.getDate ("DT_Desativado")));
        p.setDT_Validade_Cadastro (FormataData.formataDataBT (cursor.getDate (48)));
        p.setOID_Segmento (cursor.getInt (49));
        p.setNM_Endereco (cursor.getString (50));
        p.setNM_Cidade (cursor.getString (51));
        p.setCD_Cliente_Palm (formataCodigoCliente (cursor.getString ("CD_Cliente_Palm")));
        p.setOid_Condicao_Pagamento (cursor.getInt ("oid_Condicao_Pagamento"));
        p.setDM_Forma_Pagamento (cursor.getString ("DM_Forma_Pagamento"));
        p.setDM_Resp_Vendedor (cursor.getString ("DM_Resp_Vendedor"));
        p.setOID_Modal (cursor.getInt ("OID_Modal"));
        p.setDM_Wms (cursor.getString ("DM_Wms"));
        Cliente_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Cliente_Lista;
  }

  public static final List getListaByNR_CNPJ_CPF (String NR_CNPJ_CPF) throws Exception {
    Connection conn = null;
    ExecutaSQL executasql = new ExecutaSQL ();
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
      executasql.setConnection (conn);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    List Cliente_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT ");
      buff.append (" Clientes.OID_Cliente, ");
      buff.append (" Clientes.OID_Cliente_Pagador, ");
      buff.append (" Clientes.OID_Pessoa, ");
      buff.append (" Clientes.OID_Vendedor, ");
      buff.append (" Clientes.OID_Carteira, ");
      buff.append (" Clientes.OID_Grupo_Economico, ");
      buff.append (" Clientes.OID_Ramo_Atividade, ");
      buff.append (" Clientes.DM_Tipo_Cobranca, ");
      buff.append (" Clientes.DM_Condicao_Vencimento, ");
      buff.append (" Clientes.DM_Fatura_Segunda, ");
      buff.append (" Clientes.DM_Fatura_Terca, ");
      buff.append (" Clientes.DM_Fatura_Quarta, ");
      buff.append (" Clientes.DM_Fatura_Quinta, ");
      buff.append (" Clientes.DM_Fatura_Sexta, ");
      buff.append (" Clientes.DM_Vencimento_Segunda, ");
      buff.append (" Clientes.DM_Vencimento_Terca, ");
      buff.append (" Clientes.DM_Vencimento_Quarta, ");
      buff.append (" Clientes.DM_Vencimento_Quinta, ");
      buff.append (" Clientes.DM_Vencimento_Sexta, ");
      buff.append (" Clientes.DM_FOB_Dirigido, ");
      buff.append (" Clientes.DM_Isencao_ICMS, ");
      buff.append (" Clientes.DM_Isencao_Seguro, ");
      buff.append (" Clientes.DM_Credito, ");
      buff.append (" Clientes.NR_Conta_Contabil, ");
      buff.append (" Clientes.DM_Compr_Entrega_Fatura, ");
      buff.append (" Clientes.DM_Tipo_Emissao, ");
      buff.append (" Clientes.VL_Minimo_Fatura, ");
      buff.append (" Clientes.VL_Maximo_Fatura, ");
      buff.append (" Clientes.VL_Limite_Credito, ");
      buff.append (" Clientes.NR_Minimo_CTO_Fatura, ");
      buff.append (" Clientes.NR_Maximo_CTO_Fatura, ");
      buff.append (" Clientes.PE_Juros_Cobranca, ");
      buff.append (" Clientes.VL_Taxa_Cobranca, ");
      buff.append (" Clientes.NR_Peso_Cubado, ");
      buff.append (" Clientes.NR_Dias_Vencimento, ");
      buff.append (" Clientes.NR_Dias_Protesto, ");
      buff.append (" Pessoas.NR_CNPJ_CPF, ");
      buff.append (" Pessoas.NM_Razao_Social, ");
      buff.append (" Pessoas.NM_Fantasia, ");
      buff.append (" Grupos_Economicos.CD_Grupo_Economico, ");
      buff.append (" Grupos_Economicos.NM_Grupo_Economico, ");
      buff.append (" Ramos_Atividades.CD_Ramo_Atividade, ");
      buff.append (" Ramos_Atividades.NM_Ramo_Atividade, ");
      buff.append (" Clientes.VL_Limite_Credito_Adicional, ");
      buff.append (" Clientes.VL_Minimo_Compra, ");
      buff.append (" Clientes.NR_Duplicatas_Vencidas, ");
      buff.append (" Clientes.DT_Cadastro, ");
      buff.append (" Clientes.DT_Validade_Cadastro, ");
      buff.append (" Clientes.OID_Segmento, ");
      buff.append (" Clientes.CD_Cliente_Palm, ");
      buff.append (" Clientes.oid_Condicao_Pagamento, ");
      buff.append (" Clientes.DM_Forma_Pagamento, ");
      buff.append (" Clientes.DT_Alteracao,  ");
      buff.append (" Clientes.DT_Desativado, ");
      buff.append (" Clientes.DM_Resp_Vendedor, ");
      buff.append (" Clientes.OID_Modal, ");
      buff.append (" Clientes.DM_Wms ");
      buff.append (" FROM Clientes, Pessoas, Grupos_Economicos, Ramos_Atividades ");
      buff.append (" WHERE Clientes.OID_Pessoa = Pessoas.OID_Pessoa ");
      buff.append (" AND Clientes.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade ");
      buff.append (" AND Clientes.OID_Grupo_Economico = Grupos_Economicos.OID_Grupo_Economico ");
      buff.append (" AND Pessoas.NR_CNPJ_CPF = '%" + NR_CNPJ_CPF + "%'");
      buff.append (" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      while (cursor.next ()) {
        ClienteBean p = new ClienteBean ();
        p.setOID_Cliente (cursor.getString (1));
        p.setOID_Cliente_Pagador (cursor.getString (2));
        p.setOID_Pessoa (cursor.getString (3));
        p.setOID_Vendedor (cursor.getString (4));
        p.setOID_Carteira (cursor.getInt (5));
        p.setOID_Grupo_Economico (cursor.getInt (6));
        p.setOID_Ramo_Atividade (cursor.getInt (7));
        p.setDM_Tipo_Cobranca (cursor.getString (8));
        p.setDM_Condicao_Vencimento (cursor.getString (9));
        p.setDM_Fatura_Segunda (cursor.getString (10));
        p.setDM_Fatura_Terca (cursor.getString (11));
        p.setDM_Fatura_Quarta (cursor.getString (12));
        p.setDM_Fatura_Quinta (cursor.getString (13));
        p.setDM_Fatura_Sexta (cursor.getString (14));
        p.setDM_Vencimento_Segunda (cursor.getString (15));
        p.setDM_Vencimento_Terca (cursor.getString (16));
        p.setDM_Vencimento_Quarta (cursor.getString (17));
        p.setDM_Vencimento_Quinta (cursor.getString (18));
        p.setDM_Vencimento_Sexta (cursor.getString (19));
        p.setDM_FOB_Dirigido (cursor.getString (20));
        p.setDM_Isencao_ICMS (cursor.getString (21));
        p.setDM_Isencao_Seguro (cursor.getString (22));
        p.setDM_Credito (cursor.getString (23));
        p.setNR_Conta_Contabil (cursor.getString (24));
        p.setDM_Compr_Entrega_Fatura (cursor.getString (25));
        p.setDM_Tipo_Emissao (cursor.getString (26));
        p.setVL_Minimo_Fatura (cursor.getDouble (27));
        p.setVL_Maximo_Fatura (cursor.getDouble (28));
        p.setVL_Limite_Credito (new DuplicataBD (executasql).getLimiteCredito (p.getOID_Pessoa () , Data.manipulaDias (Data.getDataDMY () , -30) , Data.getDataDMY ()));
        p.setNR_Minimo_CTO_Fatura (cursor.getInt (30));
        p.setNR_Maximo_CTO_Fatura (cursor.getInt (31));
        p.setPE_Juros_Cobranca (cursor.getDouble (32));
        p.setVL_Taxa_Cobranca (cursor.getDouble (33));
        p.setNR_Peso_Cubado (cursor.getInt (34));
        p.setNR_Dias_Vencimento (cursor.getInt (35));
        p.setNR_Dias_Protesto (cursor.getInt (36));
        p.setNR_CNPJ_CPF (cursor.getString (37));
        p.setNM_Razao_Social (cursor.getString (38));
        p.setNM_Fantasia (cursor.getString (39));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia (p.getNM_Razao_Social ());
        }
        p.setCD_Grupo_Economico (cursor.getString (40));
        p.setNM_Grupo_Economico (cursor.getString (41));
        p.setCD_Ramo_Atividade (cursor.getString (42));
        p.setNM_Ramo_Atividade (cursor.getString (43));
        p.setVL_Limite_Credito_Adicional (cursor.getDouble (44));
        p.setVL_Minimo_Compra (cursor.getDouble (45));
        p.setNR_Duplicatas_Vencidas (cursor.getInt (46));
        p.setDT_Cadastro (FormataData.formataDataBT (cursor.getDate (47)));
        p.setDT_Alteracao (FormataData.formataDataBT (cursor.getDate ("DT_Alteracao")));
        p.setDT_Desativado (FormataData.formataDataBT (cursor.getDate ("DT_Desativado")));
        p.setDT_Validade_Cadastro (FormataData.formataDataBT (cursor.getDate (48)));
        p.setOID_Segmento (cursor.getInt (49));
        p.setCD_Cliente_Palm (formataCodigoCliente (cursor.getString ("CD_Cliente_Palm")));
        p.setOid_Condicao_Pagamento (cursor.getInt ("oid_Condicao_Pagamento"));
        p.setDM_Forma_Pagamento (cursor.getString ("DM_Forma_Pagamento"));
        p.setDM_Resp_Vendedor (cursor.getString ("DM_Resp_Vendedor"));
        p.setOID_Modal (cursor.getInt ("OID_Modal"));
        p.setDM_Wms (cursor.getString ("DM_Wms"));
        Cliente_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Cliente_Lista;
  }

  public static final List getAll () throws Exception {
    Connection conn = null;
    ExecutaSQL executasql = new ExecutaSQL ();
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
      executasql.setConnection (conn);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    List Cliente_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                                   ");
      buff.append ("   Clientes.OID_Cliente,                 ");
      buff.append ("   Clientes.OID_Cliente_Pagador,         ");
      buff.append ("	Clientes.OID_Pessoa,                  ");
      buff.append ("	Clientes.OID_Vendedor,                ");
      buff.append ("	Clientes.OID_Carteira,                ");
      buff.append ("	Clientes.OID_Grupo_Economico,         ");
      buff.append ("	Clientes.OID_Ramo_Atividade,          ");
      buff.append ("	Clientes.DM_Tipo_Cobranca,            ");
      buff.append ("	Clientes.DM_Condicao_Vencimento,      ");
      buff.append ("	Clientes.DM_Fatura_Segunda,           ");
      buff.append ("	Clientes.DM_Fatura_Terca,             ");
      buff.append ("	Clientes.DM_Fatura_Quarta,            ");
      buff.append ("	Clientes.DM_Fatura_Quinta,            ");
      buff.append ("	Clientes.DM_Fatura_Sexta,             ");
      buff.append ("	Clientes.DM_Vencimento_Segunda,       ");
      buff.append ("	Clientes.DM_Vencimento_Terca,         ");
      buff.append ("	Clientes.DM_Vencimento_Quarta,        ");
      buff.append ("	Clientes.DM_Vencimento_Quinta,        ");
      buff.append ("	Clientes.DM_Vencimento_Sexta,         ");
      buff.append ("	Clientes.DM_FOB_Dirigido,             ");
      buff.append ("	Clientes.DM_Isencao_ICMS,             ");
      buff.append ("	Clientes.DM_Isencao_Seguro,           ");
      buff.append ("	Clientes.DM_Credito,                  ");
      buff.append ("	Clientes.NR_Conta_Contabil,           ");
      buff.append ("	Clientes.DM_Compr_Entrega_Fatura,     ");
      buff.append ("	Clientes.DM_Tipo_Emissao,             ");
      buff.append ("	Clientes.VL_Minimo_Fatura,            ");
      buff.append ("	Clientes.VL_Maximo_Fatura,            ");
      buff.append ("	Clientes.VL_Limite_Credito,           ");
      buff.append ("	Clientes.NR_Minimo_CTO_Fatura,        ");
      buff.append ("	Clientes.NR_Maximo_CTO_Fatura,        ");
      buff.append ("	Clientes.PE_Juros_Cobranca,           ");
      buff.append ("	Clientes.VL_Taxa_Cobranca,            ");
      buff.append ("	Clientes.NR_Peso_Cubado,              ");
      buff.append ("	Clientes.NR_Dias_Vencimento,          ");
      buff.append ("	Clientes.NR_Dias_Protesto,            ");
      buff.append ("	Pessoas.NR_CNPJ_CPF,                  ");
      buff.append ("	Pessoas.NM_Razao_Social,              ");
      buff.append ("	Pessoas.NM_Fantasia,                  ");
      buff.append ("	Grupos_Economicos.CD_Grupo_Economico, ");
      buff.append ("	Grupos_Economicos.NM_Grupo_Economico, ");
      buff.append ("	Ramos_Atividades.CD_Ramo_Atividade,   ");
      buff.append ("	Ramos_Atividades.NM_Ramo_Atividade,   ");
      buff.append ("   Clientes.VL_Limite_Credito_Adicional, ");
      buff.append (" 	Clientes.VL_Minimo_Compra,     		  ");
      buff.append (" 	Clientes.NR_Duplicatas_Vencidas, 	  ");
      buff.append (" 	Clientes.DT_Cadastro,    		 	  ");
      buff.append (" 	Clientes.DT_Validade_Cadastro, 		  ");
      buff.append ("	Clientes.OID_Segmento,                ");
      buff.append ("   Clientes.CD_Cliente_Palm,             ");
      buff.append ("   Clientes.oid_Condicao_Pagamento,      ");
      buff.append ("   Clientes.DM_Forma_Pagamento,          ");
      buff.append ("   Clientes.DT_Alteracao,                ");
      buff.append ("   Clientes.DT_Desativado,               ");
      buff.append ("   Clientes.DM_Resp_Vendedor,             ");
      buff.append ("	Clientes.PE_Comissao,           ");
      buff.append ("	Carteiras.CD_Carteira, ");
      buff.append ("   Clientes.OID_Modal,               ");
      buff.append ("   Clientes.DM_Wms,            ");
      buff.append ("	Clientes.NR_Dias_Bloqueio,            ");
      buff.append ("	Clientes.DT_Ultimo_Movimento            ");
      buff.append (" FROM Clientes, Pessoas, Grupos_Economicos, Ramos_Atividades, Carteiras ");
      buff.append (" WHERE Clientes.OID_Pessoa          = Pessoas.OID_Pessoa                    ");
      buff.append (" AND   Clientes.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");
      buff.append (" AND   Clientes.OID_Grupo_Economico = Grupos_Economicos.OID_Grupo_Economico  ");
      buff.append ("   AND   Clientes.OID_Carteira        = Carteiras.oid_Carteira  ");
      buff.append (" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");
      //System.out.println(buff);
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      while (cursor.next ()) {
        ClienteBean p = new ClienteBean ();
        p.setOID_Cliente (cursor.getString (1));
        p.setOID_Cliente_Pagador (cursor.getString (2));
        p.setOID_Pessoa (cursor.getString (3));
        p.setOID_Vendedor (cursor.getString (4));
        p.setOID_Carteira (cursor.getInt (5));
        p.setOID_Grupo_Economico (cursor.getInt (6));
        p.setOID_Ramo_Atividade (cursor.getInt (7));
        p.setDM_Tipo_Cobranca (cursor.getString (8));
        p.setDM_Condicao_Vencimento (cursor.getString (9));
        p.setDM_Fatura_Segunda (cursor.getString (10));
        p.setDM_Fatura_Terca (cursor.getString (11));
        p.setDM_Fatura_Quarta (cursor.getString (12));
        p.setDM_Fatura_Quinta (cursor.getString (13));
        p.setDM_Fatura_Sexta (cursor.getString (14));
        p.setDM_Vencimento_Segunda (cursor.getString (15));
        p.setDM_Vencimento_Terca (cursor.getString (16));
        p.setDM_Vencimento_Quarta (cursor.getString (17));
        p.setDM_Vencimento_Quinta (cursor.getString (18));
        p.setDM_Vencimento_Sexta (cursor.getString (19));
        p.setDM_FOB_Dirigido (cursor.getString (20));
        p.setDM_Isencao_ICMS (cursor.getString (21));
        p.setDM_Isencao_Seguro (cursor.getString (22));
        p.setDM_Credito (cursor.getString (23));
        p.setNR_Conta_Contabil (cursor.getString (24));
        p.setDM_Compr_Entrega_Fatura (cursor.getString (25));
        p.setDM_Tipo_Emissao (cursor.getString (26));
        p.setVL_Minimo_Fatura (cursor.getDouble (27));
        p.setVL_Maximo_Fatura (cursor.getDouble (28));
        p.setVL_Limite_Credito (new DuplicataBD (executasql).getLimiteCredito (p.getOID_Pessoa () , Data.manipulaDias (Data.getDataDMY () , -30) , Data.getDataDMY ()));
        p.setNR_Minimo_CTO_Fatura (cursor.getInt (30));
        p.setNR_Maximo_CTO_Fatura (cursor.getInt (31));
        p.setPE_Juros_Cobranca (cursor.getDouble (32));
        p.setVL_Taxa_Cobranca (cursor.getDouble (33));
        p.setNR_Peso_Cubado (cursor.getInt (34));
        p.setNR_Dias_Vencimento (cursor.getInt (35));
        p.setNR_Dias_Protesto (cursor.getInt (36));
        p.setNR_CNPJ_CPF (cursor.getString (37));
        p.setNM_Razao_Social (cursor.getString (38));
        p.setNM_Fantasia (cursor.getString (39));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia (p.getNM_Razao_Social ());
        }
        p.setCD_Grupo_Economico (cursor.getString (40));
        p.setNM_Grupo_Economico (cursor.getString (41));
        p.setCD_Ramo_Atividade (cursor.getString (42));
        p.setNM_Ramo_Atividade (cursor.getString (43));
        p.setVL_Limite_Credito_Adicional (cursor.getDouble (44));
        p.setVL_Minimo_Compra (cursor.getDouble (45));
        p.setNR_Duplicatas_Vencidas (cursor.getInt (46));
        p.setDT_Cadastro (FormataData.formataDataBT (cursor.getDate (47)));
        p.setDT_Alteracao (FormataData.formataDataBT (cursor.getDate ("DT_Alteracao")));
        p.setDT_Desativado (FormataData.formataDataBT (cursor.getDate ("DT_Desativado")));
        p.setDT_Validade_Cadastro (FormataData.formataDataBT (cursor.getDate (48)));
        p.setOID_Segmento (cursor.getInt (49));
        p.setCD_Cliente_Palm (formataCodigoCliente (cursor.getString ("CD_Cliente_Palm")));
        p.setOid_Condicao_Pagamento (cursor.getInt ("oid_Condicao_Pagamento"));
        p.setDM_Forma_Pagamento (cursor.getString ("DM_Forma_Pagamento"));
        p.setDM_Resp_Vendedor (cursor.getString ("DM_Resp_Vendedor"));
        p.setPE_Comissao (cursor.getDouble ("PE_Comissao"));
        p.setCD_Carteira (cursor.getString ("CD_Carteira"));
        p.setOID_Modal (cursor.getInt ("OID_Modal"));
        p.setDM_Wms (cursor.getString ("DM_Wms"));

        p.setNR_Dias_Bloqueio(cursor.getInt ("NR_Dias_Bloqueio"));
        p.setDT_Ultimo_Movimento(FormataData.formataDataBT(cursor.getString("dt_ultimo_movimento")));
        if(p.getNR_Dias_Bloqueio()>0 && JavaUtil.doValida(p.getDT_Ultimo_Movimento())){
        	  p.setDT_Bloqueio(Data.getSomaDiaData(p.getDT_Ultimo_Movimento(), p.getNR_Dias_Bloqueio()));
          } else if(p.getNR_Dias_Bloqueio()>0){
        	  p.setDT_Bloqueio(Data.getSomaDiaData(Data.getDataDMY(), p.getNR_Dias_Bloqueio()));
          } else {
        	  p.setDT_Bloqueio(Data.getSomaDiaData(Data.getDataDMY(), 30));
          }

        Cliente_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Cliente_Lista;
  }

  public void geraRelatorio (javax.servlet.http.HttpServletRequest req , javax.servlet.http.HttpServletResponse res) throws Exception {
    String sql = null;
    Statement stmt = null;
    ResultSet cursor = null;

    sql = "Select * from Pessoas, Clientes, Cidades, Estados, Regioes_Estados " + "where Pessoas.OID_Pessoa = Clientes.OID_Pessoa " + "and Pessoas.OID_Cidade = Cidades.OID_Cidade "
        + "and Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " + "and Regioes_Estados.OID_Estado = Estados.OID_Estado " + "Order by NM_Razao_Social";
    Connection conn = null;
    try {
      // Pede uma conex�o ao gerenciador do driver
      // passando como par�metro o NM_Tipo_Documento do DSN
      // o NM_Tipo_Documento de usu�rio e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    finally{
      cursor.close ();
      stmt.close ();
      conn.close ();
    }

  }

  public void importa_Cliente () throws Exception {
    String sql = null;
    sql = "Select * from Pessoas, Duplicatas, Clientes, Cidades, Estados, Regioes_Estados " + "where Pessoas.OID_Pessoa = Clientes.OID_Pessoa " + "and Pessoas.OID_Pessoa = Duplicatas.OID_Pessoa "
        + "and Pessoas.OID_Cidade = Cidades.OID_Cidade " + "and Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " + "and Regioes_Estados.OID_Estado = Estados.OID_Estado "
        + "Order by NM_Razao_Social";
    Connection conn = null;
    try {
      // Pede uma conex�o ao gerenciador do driver
      // passando como par�metro o NM_Tipo_Documento do DSN
      // o NM_Tipo_Documento de usu�rio e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    Statement stmt = conn.createStatement ();
    ResultSet res = stmt.executeQuery (sql);
    try {
      ManipulaArquivo man = new ManipulaArquivo (";");
      FormataDataBean dataFormatada = new FormataDataBean ();
      while (res.next ()) {
        man.insereValor (String.valueOf (res.getString ("NR_CNPJ_CPF")));
        man.insereValor (String.valueOf (res.getString ("NM_Razao_Social")));
        man.insereValor (String.valueOf (res.getString ("NM_Endereco")));
        man.insereValor (String.valueOf (res.getString ("NR_CEP")));
        man.insereValor (String.valueOf (res.getString ("NM_Bairro")));
        man.insereValor (String.valueOf (res.getString ("NM_Cidade")));
        man.insereValor (String.valueOf (res.getString ("CD_Estado")));
        man.insereValor (String.valueOf (res.getString ("NM_INSCRICAO_ESTADUAL")));
        man.insereValor (String.valueOf (res.getString ("NR_DUPLICATA")));
        dataFormatada.setDT_FormataData (res.getString ("DT_EMISSAO"));
        man.insereValor (String.valueOf (dataFormatada.getDT_FormataData ()));
        dataFormatada.setDT_FormataData (res.getString ("DT_VENCIMENTO"));
        man.insereValor (String.valueOf (dataFormatada.getDT_FormataData ()));
        man.insereValor (String.valueOf (res.getString ("VL_SALDO")));
        man.insereValor (String.valueOf (res.getString ("VL_JURO_MORA_DIA")));
        man.insereQuebra ();
      }
      man.escreveArquivo ("C:\\temp\\Cliente.txt");
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  // /### Transito 28062003
  public void geraEtiqueta (javax.servlet.http.HttpServletRequest req , javax.servlet.http.HttpServletResponse res) throws Exception {
    String sql = null;
    sql = "Select * from Pessoas, Cidades, Estados, Regioes_Estados " + "where Pessoas.OID_Cidade = Cidades.OID_Cidade " + "and Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado "
        + "and Regioes_Estados.OID_Estado = Estados.OID_Estado " + "Order by NM_Razao_Social";
    Connection conn = null;
    try {
      // Pede uma conex�o ao gerenciador do driver
      // passando como par�metro o NM_Tipo_Documento do DSN
      // o NM_Tipo_Documento de usu�rio e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    Statement stmt = conn.createStatement ();
    ResultSet cursor = stmt.executeQuery (sql);
  }

  // *** RELAT�RIOS
  // Cliente
  public void Rel_Cliente (HttpServletRequest request , HttpServletResponse response) throws Exception {
    ArrayList lista = new ArrayList ();
    String oid_Cliente = request.getParameter ("oid_Cliente");
    if (JavaUtil.doValida (oid_Cliente)) {
      RelatorioED ed = new RelatorioED ();
      //ClienteBean edVolta = getByOID_Cliente(oid_Vendedor);
      ClienteBean edVolta = getByOID_Cliente (oid_Cliente);
      ed.setNr_cnpj_cpf (edVolta.getNR_CNPJ_CPF ());
      ed.setNm_razao_social (edVolta.getNM_Razao_Social ());
      PessoaBean edPessoa = PessoaBean.getByEndereco_Completo (ed.getNr_cnpj_cpf ());
      ed.setNm_cidade (edPessoa.getNM_Cidade ());
      ed.setNm_estado (edPessoa.getNM_Estado ());
      ed.setNm_endereco (edPessoa.getNM_Endereco ());
      ed.setNm_bairro (edPessoa.getNM_Bairro ());
      ed.setNr_cep (edPessoa.getNR_CEP ());
      ed.setNr_telefone (edPessoa.getNR_Telefone ());
      ed.setNm_inscricao_estadual (edPessoa.getNM_Inscricao_Estadual ());

      ed.setNR_Dias_Bloqueio(edVolta.getNR_Dias_Bloqueio());
      ed.setDT_Ultimo_Movimento(edVolta.getDT_Ultimo_Movimento());
      if(ed.getNR_Dias_Bloqueio()>0 && JavaUtil.doValida(edVolta.getDT_Ultimo_Movimento())){
    	  ed.setDT_Bloqueio(Data.getSomaDiaData(edVolta.getDT_Ultimo_Movimento(), edVolta.getNR_Dias_Bloqueio()));
      } else if(ed.getNR_Dias_Bloqueio()>0){
    	  ed.setDT_Bloqueio(Data.getSomaDiaData(Data.getDataDMY(), edVolta.getNR_Dias_Bloqueio()));
      } else {
    	  ed.setDT_Bloqueio(Data.getSomaDiaData(Data.getDataDMY(), 30));
      }

      ed.setNm_vendedor (VendedorBean.getByOID_Vendedor (edVolta.getOID_Vendedor ()).getNM_Razao_Social ());
      ed.setPE_Comissao (edVolta.getPE_Comissao ());
      ed.setNM_Grupo_Economico (edVolta.getNM_Grupo_Economico ());
      ed.setCD_Carteira (edVolta.getCD_Carteira ());
      if (JavaUtil.doValida (edVolta.getDM_Tipo_Cobranca ())) {
        if (edVolta.getDM_Tipo_Cobranca ().equals ("B")) {
          ed.setDM_Tipo_Cobranca ("BANCO");
        }
        else if (edVolta.getDM_Tipo_Cobranca ().equals ("C")) {
          ed.setDM_Tipo_Cobranca ("CARTEIRA");
        }
        else {
          ed.setDM_Tipo_Cobranca ("A VISTA");
        }
      }
      else {
        ed.setDM_Tipo_Cobranca ("A VISTA");
      }
      ed.setNR_Dias_Vencimento (edVolta.getNR_Dias_Vencimento ());

      lista.add (ed);
    }
    else {
      ArrayList listaVolta = (ArrayList) getAll ();
      // *** Converte para fields do relat�rio
      for (int i = 0; i < listaVolta.size (); i++) {
        RelatorioED ed = new RelatorioED ();
        ClienteBean edVolta = (ClienteBean) listaVolta.get (i);
        ed.setNr_cnpj_cpf (edVolta.getNR_CNPJ_CPF ());
        ed.setNm_razao_social (edVolta.getNM_Razao_Social ());
        PessoaBean edPessoa = PessoaBean.getByEndereco_Completo (ed.getNr_cnpj_cpf ());
        ed.setNm_cidade (edPessoa.getNM_Cidade ());
        ed.setNm_estado (edPessoa.getNM_Estado ());
        ed.setNm_endereco (edPessoa.getNM_Endereco ());
        ed.setNm_bairro (edPessoa.getNM_Bairro ());
        ed.setNr_cep (edPessoa.getNR_CEP ());
        ed.setNr_telefone (edPessoa.getNR_Telefone ());
        ed.setNm_inscricao_estadual (edPessoa.getNM_Inscricao_Estadual ());

        ed.setNR_Dias_Bloqueio(edVolta.getNR_Dias_Bloqueio());
        ed.setDT_Ultimo_Movimento(edVolta.getDT_Ultimo_Movimento());
        if(ed.getNR_Dias_Bloqueio()>0 && JavaUtil.doValida(edVolta.getDT_Ultimo_Movimento())){
      	  ed.setDT_Bloqueio(Data.getSomaDiaData(edVolta.getDT_Ultimo_Movimento(), edVolta.getNR_Dias_Bloqueio()));
        } else if(ed.getNR_Dias_Bloqueio()>0){
      	  ed.setDT_Bloqueio(Data.getSomaDiaData(Data.getDataDMY(), edVolta.getNR_Dias_Bloqueio()));
        } else {
      	  ed.setDT_Bloqueio(Data.getSomaDiaData(Data.getDataDMY(), 30));
        }

        ed.setNm_vendedor (VendedorBean.getByOID_Vendedor (edVolta.getOID_Vendedor ()).getNM_Razao_Social ());
        ed.setPE_Comissao (edVolta.getPE_Comissao ());
        ed.setNM_Grupo_Economico (edVolta.getNM_Grupo_Economico ());
        ed.setCD_Carteira (edVolta.getCD_Carteira ());
        if (JavaUtil.doValida (edVolta.getDM_Tipo_Cobranca ())) {
          if (edVolta.getDM_Tipo_Cobranca ().equals ("B")) {
            ed.setDM_Tipo_Cobranca ("BANCO");
          }
          else if (edVolta.getDM_Tipo_Cobranca ().equals ("C")) {
            ed.setDM_Tipo_Cobranca ("CARTEIRA");
          }
          else {
            ed.setDM_Tipo_Cobranca ("A VISTA");
          }
        }
        else {
          ed.setDM_Tipo_Cobranca ("A VISTA");
        }
        ed.setNR_Dias_Vencimento (edVolta.getNR_Dias_Vencimento ());

        lista.add (ed);
      }
    }
  }

//Cliente Bloqueado
  public void Rel_Cliente_Bloqueado(HttpServletRequest request , HttpServletResponse response) throws Exception {
    ArrayList lista = new ArrayList ();
    String oid_Cliente = request.getParameter ("oid_Cliente");

    String data_parametro = Data.getDataDMY();
    if(JavaUtil.doValida(request.getParameter("FT_DT_Parametro"))){
    	data_parametro = request.getParameter("FT_DT_Parametro");
    }

    if (JavaUtil.doValida (oid_Cliente)) {
    	update_Movimentacao(oid_Cliente);
      RelatorioED ed = new RelatorioED ();
      //ClienteBean edVolta = getByOID_Cliente(oid_Vendedor);
      ClienteBean edVolta = getByOID_Cliente (oid_Cliente);
      ed.setNr_cnpj_cpf (edVolta.getNR_CNPJ_CPF ());
      ed.setNm_razao_social (edVolta.getNM_Razao_Social ());
      PessoaBean edPessoa = PessoaBean.getByEndereco_Completo (ed.getNr_cnpj_cpf ());
      ed.setNm_cidade (edPessoa.getNM_Cidade ());
      ed.setNm_estado (edPessoa.getNM_Estado ());
      ed.setNm_endereco (edPessoa.getNM_Endereco ());
      ed.setNm_bairro (edPessoa.getNM_Bairro ());
      ed.setNr_cep (edPessoa.getNR_CEP ());
      ed.setNr_telefone (edPessoa.getNR_Telefone ());
      ed.setNm_inscricao_estadual (edPessoa.getNM_Inscricao_Estadual ());

      ed.setNR_Dias_Bloqueio(edVolta.getNR_Dias_Bloqueio());
      ed.setDT_Ultimo_Movimento(edVolta.getDT_Ultimo_Movimento());
      if(ed.getNR_Dias_Bloqueio()>0 && JavaUtil.doValida(edVolta.getDT_Ultimo_Movimento())){
    	  ed.setDT_Bloqueio(Data.getSomaDiaData(edVolta.getDT_Ultimo_Movimento(), edVolta.getNR_Dias_Bloqueio()));
      } else if(ed.getNR_Dias_Bloqueio()>0){
    	  ed.setDT_Bloqueio(Data.getSomaDiaData(Data.getDataDMY(), edVolta.getNR_Dias_Bloqueio()));
      } else {
    	  ed.setDT_Bloqueio(Data.getSomaDiaData(Data.getDataDMY(), 30));
      }

      ed.setNm_vendedor (VendedorBean.getByOID_Vendedor (edVolta.getOID_Vendedor ()).getNM_Razao_Social ());
      ed.setPE_Comissao (edVolta.getPE_Comissao ());
      ed.setNM_Grupo_Economico (edVolta.getNM_Grupo_Economico ());
      ed.setCD_Carteira (edVolta.getCD_Carteira ());
      if (JavaUtil.doValida (edVolta.getDM_Tipo_Cobranca ())) {
        if (edVolta.getDM_Tipo_Cobranca ().equals ("B")) {
          ed.setDM_Tipo_Cobranca ("BANCO");
        }
        else if (edVolta.getDM_Tipo_Cobranca ().equals ("C")) {
          ed.setDM_Tipo_Cobranca ("CARTEIRA");
        }
        else {
          ed.setDM_Tipo_Cobranca ("A VISTA");
        }
      }
      else {
        ed.setDM_Tipo_Cobranca ("A VISTA");
      }
      ed.setNR_Dias_Vencimento (edVolta.getNR_Dias_Vencimento ());

      if(Data.comparaData(data_parametro,">", ed.getDT_Bloqueio())){
      	lista.add (ed);
      }
    }
    else {
    	//update_Movimentacao_total();

      ArrayList listaVolta = (ArrayList) getListByRecord(request);
      // *** Converte para fields do relat�rio
      for (int i = 0; i < listaVolta.size (); i++) {
        RelatorioED ed = new RelatorioED ();
        ClienteBean edVolta = (ClienteBean) listaVolta.get (i);
        ed.setNr_cnpj_cpf (edVolta.getNR_CNPJ_CPF ());
        ed.setNm_razao_social (edVolta.getNM_Razao_Social ());
        PessoaBean edPessoa = PessoaBean.getByEndereco_Completo (ed.getNr_cnpj_cpf ());
        ed.setNm_cidade (edPessoa.getNM_Cidade ());
        ed.setNm_estado (edPessoa.getNM_Estado ());
        ed.setNm_endereco (edPessoa.getNM_Endereco ());
        ed.setNm_bairro (edPessoa.getNM_Bairro ());
        ed.setNr_cep (edPessoa.getNR_CEP ());
        ed.setNr_telefone (edPessoa.getNR_Telefone ());
        ed.setNm_inscricao_estadual (edPessoa.getNM_Inscricao_Estadual ());

        ed.setNR_Dias_Bloqueio(edVolta.getNR_Dias_Bloqueio());
        ed.setDT_Ultimo_Movimento(edVolta.getDT_Ultimo_Movimento());
        ed.setDT_Bloqueio(edVolta.getDT_Bloqueio());

        ed.setNm_vendedor (VendedorBean.getByOID_Vendedor (edVolta.getOID_Vendedor ()).getNM_Razao_Social ());
        ed.setPE_Comissao (edVolta.getPE_Comissao ());
        ed.setNM_Grupo_Economico (edVolta.getNM_Grupo_Economico ());
        ed.setCD_Carteira (edVolta.getCD_Carteira ());
        if (JavaUtil.doValida (edVolta.getDM_Tipo_Cobranca ())) {
          if (edVolta.getDM_Tipo_Cobranca ().equals ("B")) {
            ed.setDM_Tipo_Cobranca ("BANCO");
          }
          else if (edVolta.getDM_Tipo_Cobranca ().equals ("C")) {
            ed.setDM_Tipo_Cobranca ("CARTEIRA");
          }
          else {
            ed.setDM_Tipo_Cobranca ("A VISTA");
          }
        }
        else {
          ed.setDM_Tipo_Cobranca ("A VISTA");
        }
        ed.setNR_Dias_Vencimento (edVolta.getNR_Dias_Vencimento ());

        if(Data.comparaData(data_parametro,">", ed.getDT_Bloqueio())){
        	lista.add (ed);
        }
      }
    }
  }

  public static final List getByNM_Razao_Social_Endereco (String NM_Razao_Social) throws Exception {
    /*
     * Abre a conex�o com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conex�o ao gerenciador do driver
      // passando como par�metro o NM_Razao_Social do DSN
      // o NM_Razao_Social de usu�rio e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    List Pessoa_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Pessoas.OID_Pessoa, ");
      buff.append ("	Pessoas.OID_Cidade, ");
      buff.append ("	Pessoas.NR_CNPJ_CPF, ");
      buff.append ("	Pessoas.NM_Razao_Social, ");
      buff.append ("	Pessoas.NM_Endereco, ");
      buff.append ("	Pessoas.NM_Bairro, ");
      buff.append ("	Pessoas.NR_CEP, ");
      buff.append ("	Pessoas.NM_Fantasia, ");
      buff.append ("	Pessoas.NM_Inscricao_Estadual, ");
      buff.append ("	Cidades.NM_Cidade, ");
      buff.append ("	Cidades.CD_Cidade, ");
      buff.append ("	Cidades.NM_Codigo_Aereo, ");
      buff.append ("	Cidades.DM_Tipo_Localizacao, ");
      buff.append ("	Cidades.DM_Suframa, ");
      buff.append ("	Regioes_Estados.OID_Regiao_Estado, ");
      buff.append ("	Regioes_Estados.NM_Regiao_Estado, ");
      buff.append ("	Regioes_Estados.CD_Regiao_Estado, ");
      buff.append ("	Estados.OID_Estado, ");
      buff.append ("	Estados.NM_Estado, ");
      buff.append ("	Estados.CD_Estado, ");
      buff.append ("	Regioes_Paises.OID_Regiao_Pais, ");
      buff.append ("	Regioes_Paises.NM_Regiao_Pais, ");
      buff.append ("	Regioes_Paises.CD_Regiao_Pais, ");
      buff.append ("	Paises.OID_Pais, ");
      buff.append ("	Paises.NM_Pais, ");
      buff.append ("	Paises.CD_Pais, ");
      buff.append ("	Cidades.oid_cidade, Clientes.OID_Cliente ");
      buff.append (" FROM clientes, Pessoas, Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
      buff.append (" WHERE Clientes.OID_Pessoa = Pessoas.OID_Pessoa and Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
      buff.append (" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
      buff.append (" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
      buff.append (" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
      buff.append (" AND Cidades.OID_Cidade = Pessoas.oid_Cidade");
      buff.append (" AND Pessoas.NM_Razao_Social LIKE '");
      buff.append (NM_Razao_Social);
      buff.append ("%'");
      buff.append (" ORDER BY Pessoas.NM_Razao_Social ");
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      while (cursor.next ()) {
        PessoaBean p = new PessoaBean ();
        p.setOID (cursor.getString (1));
        p.setOID_Cidade (cursor.getInt (2));
        p.setNR_CNPJ_CPF (cursor.getString (3));
        p.setNM_Razao_Social (cursor.getString (4));
        p.setNM_Endereco (cursor.getString (5));
        p.setNM_Bairro (cursor.getString (6));
        p.setNR_CEP (cursor.getString (7));
        p.setNM_Fantasia (cursor.getString (8));
        if (p.getNM_Fantasia () == null || p.getNM_Fantasia ().equals ("null") || p.getNM_Fantasia ().equals ("")) {
          p.setNM_Fantasia ( (p.getNM_Razao_Social () + "                                                  ").substring (0 , 30).trim ());
        }
        p.setNM_Inscricao_Estadual (cursor.getString (9));
        p.setNM_Cidade (cursor.getString (10));
        p.setCD_Cidade (cursor.getString (11));
        p.setNM_Codigo_Aereo (cursor.getString (12));
        p.setDM_Tipo_Localizacao (cursor.getString (13));
        p.setDM_Suframa (cursor.getString (14));
        p.setOid_Regiao_Estado (cursor.getInt (15));
        p.setNM_Regiao_Estado (cursor.getString (16));
        p.setCD_Regiao_Estado (cursor.getString (17));
        p.setOid_Estado (cursor.getInt (18));
        p.setNM_Estado (cursor.getString (19));
        p.setCD_Estado (cursor.getString (20));
        p.setOid_Regiao_Pais (cursor.getInt (21));
        p.setNM_Regiao_Pais (cursor.getString (22));
        p.setCD_Regiao_Pais (cursor.getString (23));
        p.setOid_Pais (cursor.getInt (24));
        p.setNM_Pais (cursor.getString (25));
        p.setCD_Pais (cursor.getString (26));
        p.setNM_Cidade_Estado_Pais (p.getNM_Cidade ().trim () + " - " + cursor.getString (20).trim () + " - " + cursor.getString (25).trim ());
        p.setOid_Cidade (cursor.getInt (27));
        Pessoa_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Pessoa_Lista;
  }

  public void setPE_Comissao (double PE_Comissao) {
    this.PE_Comissao = PE_Comissao;
  }

  public double getPE_Comissao () {
    return PE_Comissao;
  }

  public void setNM_Razao_Social_Vendedor (String NM_Razao_Social_Vendedor) {
    this.NM_Razao_Social_Vendedor = NM_Razao_Social_Vendedor;
  }

  public String getNM_Razao_Social_Vendedor () {
    return NM_Razao_Social_Vendedor;
  }

  public void setDT_Cadastro (String DT_Cadastro) {
    this.DT_Cadastro = DT_Cadastro;
  }

  public String getDT_Cadastro () {
    return DT_Cadastro;
  }

  public void setDM_PIS_COFINS (String DM_PIS_COFINS) {
    this.DM_PIS_COFINS = DM_PIS_COFINS;
  }

  public String getDM_PIS_COFINS () {
    return DM_PIS_COFINS;
  }

  public void setPE_PIS_COFINS (double PE_PIS_COFINS) {
    this.PE_PIS_COFINS = PE_PIS_COFINS;
  }

  public double getPE_PIS_COFINS () {
    return PE_PIS_COFINS;
  }

  public void setNR_QT_Notas_Fiscais (int NR_QT_Notas_Fiscais) {
    this.NR_QT_Notas_Fiscais = NR_QT_Notas_Fiscais;
  }

  public int getNR_QT_Notas_Fiscais () {
    return NR_QT_Notas_Fiscais;
  }

  public int getOID_Segmento () {
    return OID_Segmento;
  }

  public void setOID_Segmento (int segmentacao) {
    OID_Segmento = segmentacao;
  }

  public double getVL_Taxa_Despacho () {
    return VL_Taxa_Despacho;
  }

  public void setVL_Taxa_Despacho (double VL_Taxa_Despacho) {
    this.VL_Taxa_Despacho = VL_Taxa_Despacho;
  }

  public int getOID_Unidade_Operacional () {
    return OID_Unidade_Operacional;
  }

  public void setOID_Unidade_Operacional (int unidade_Operacional) {
    OID_Unidade_Operacional = unidade_Operacional;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setDM_Situacao (String situacao) {
    DM_Situacao = situacao;
  }

  public String getTX_Instrucao_Cobranca () {
    return TX_Instrucao_Cobranca;
  }

  public void setTX_Instrucao_Cobranca (String instrucao) {
    TX_Instrucao_Cobranca = instrucao;
  }

  public String getDM_Tracking () {
    return DM_Tracking;
  }

  public void setDM_Tracking (String tracking) {
    DM_Tracking = tracking;
  }

  public String getOid_Pessoa_Parceiro () {
    return oid_Pessoa_Parceiro;
  }

  public void setOid_Pessoa_Parceiro (String oid_Pessoa_Parceiro) {
    this.oid_Pessoa_Parceiro = oid_Pessoa_Parceiro;
  }

  public String getDT_Validade_Cadastro () {
    return DT_Validade_Cadastro;
  }

  public int getNR_Duplicatas_Vencidas () {
    return NR_Duplicatas_Vencidas;
  }

  public double getVL_Limite_Credito_Adicional () {
    return VL_Limite_Credito_Adicional;
  }

  public double getVL_Minimo_Compra () {
    return VL_Minimo_Compra;
  }

  public void setDT_Validade_Cadastro (String validade_Cadastro) {
    DT_Validade_Cadastro = validade_Cadastro;
  }

  public void setNR_Duplicatas_Vencidas (int duplicatas_Vencidas) {
    NR_Duplicatas_Vencidas = duplicatas_Vencidas;
  }

  public void setVL_Limite_Credito_Adicional (double limite_Credito_Adicional) {
    VL_Limite_Credito_Adicional = limite_Credito_Adicional;
  }

  public void setVL_Minimo_Compra (double minimo_Compra) {
    VL_Minimo_Compra = minimo_Compra;
  }

  public int getOid_Produto () {
    return this.oid_Produto;
  }

  public String getDM_RCTA () {
    return DM_RCTA;
  }

  public void setOid_Produto (int oid_Produto) {
    this.oid_Produto = oid_Produto;
  }

  public void setDM_RCTA (String DM_RCTA) {
    this.DM_RCTA = DM_RCTA;
  }

  public String getDM_RCTR_DC () {
    return DM_RCTR_DC;
  }

  public void setDM_RCTR_DC (String dm_rctr_dc) {
    DM_RCTR_DC = dm_rctr_dc;
  }

  public String getDM_RCTR_VI () {
    return DM_RCTR_VI;
  }

  public void setDM_RCTR_VI (String dm_rctr_vi) {
    DM_RCTR_VI = dm_rctr_vi;
  }

  public String getDM_RCTRC () {
    return DM_RCTRC;
  }

  public void setDM_RCTRC (String dm_rctrc) {
    DM_RCTRC = dm_rctrc;
  }

  public String getCD_Cliente_Palm () {
    return CD_Cliente_Palm;
  }

  public void setCD_Cliente_Palm (String cliente_Palm) {
    CD_Cliente_Palm = cliente_Palm;
  }

  public int getOid_Condicao_Pagamento () {
    return oid_Condicao_Pagamento;
  }

  public void setOid_Condicao_Pagamento (int oid_Condicao_Pagamento) {
    this.oid_Condicao_Pagamento = oid_Condicao_Pagamento;
  }

  public String getDM_Forma_Pagamento () {
    return DM_Forma_Pagamento;
  }

  public void setDM_Forma_Pagamento (String forma_Pagamento) {
    DM_Forma_Pagamento = forma_Pagamento;
  }

  public int getOid_Conta () {
    return oid_Conta;
  }

  public void setOid_Conta (int oid_Conta) {
    this.oid_Conta = oid_Conta;
  }

  public int getOid_Conta_Debito () {
    return oid_Conta_Debito;
  }

  public double getPE_Desconto_Vencimento () {
    return PE_Desconto_Vencimento;
  }

  public String getCD_Acesso () {
    return CD_Acesso;
  }

  public String getNM_Contato_Cobranca () {
    return NM_Contato_Cobranca;
  }

  public String getDM_Inclui_ICMS_Parcela_CTRC () {
    return DM_Inclui_ICMS_Parcela_CTRC;
  }

  public double getVL_Custo_Descarga () {
    return VL_Custo_Descarga;
  }

  public double getVL_Custo_Carga () {
    return VL_Custo_Carga;
  }

  public double getPE_ISSQN () {
    return PE_ISSQN;
  }

  public String getDM_ISSQN () {
    return DM_ISSQN;
  }

  public double getVL_Taxa_Entrega () {
    return VL_Taxa_Entrega;
  }

  public double getVL_Taxa_Coleta () {
    return VL_Taxa_Coleta;
  }

  public String getDM_Monitoramento () {
    return DM_Monitoramento;
  }

  public double getPE_Base_Comissao_Motorista () {
    return PE_Base_Comissao_Motorista;
  }

  public void setOid_Conta_Debito (int oid_Conta_Debito) {
    this.oid_Conta_Debito = oid_Conta_Debito;
  }

  public void setPE_Desconto_Vencimento (double PE_Desconto_Vencimento) {
    this.PE_Desconto_Vencimento = PE_Desconto_Vencimento;
  }

  public void setCD_Acesso (String CD_Acesso) {
    this.CD_Acesso = CD_Acesso;
  }

  public void setNM_Contato_Cobranca (String NM_Contato_Cobranca) {
    this.NM_Contato_Cobranca = NM_Contato_Cobranca;
  }

  public void setDM_Inclui_ICMS_Parcela_CTRC (String DM_Inclui_ICMS_Parcela_CTRC) {
    this.DM_Inclui_ICMS_Parcela_CTRC = DM_Inclui_ICMS_Parcela_CTRC;
  }

  public void setVL_Custo_Descarga (double VL_Custo_Descarga) {
    this.VL_Custo_Descarga = VL_Custo_Descarga;
  }

  public void setVL_Custo_Carga (double VL_Custo_Carga) {
    this.VL_Custo_Carga = VL_Custo_Carga;
  }

  public void setPE_ISSQN (double PE_ISSQN) {
    this.PE_ISSQN = PE_ISSQN;
  }

  public void setDM_ISSQN (String DM_ISSQN) {
    this.DM_ISSQN = DM_ISSQN;
  }

  public void setVL_Taxa_Entrega (double VL_Taxa_Entrega) {
    this.VL_Taxa_Entrega = VL_Taxa_Entrega;
  }

  public void setVL_Taxa_Coleta (double VL_Taxa_Coleta) {
    this.VL_Taxa_Coleta = VL_Taxa_Coleta;
  }

  public void setDM_Monitoramento (String DM_Monitoramento) {
    this.DM_Monitoramento = DM_Monitoramento;
  }

  public void setPE_Base_Comissao_Motorista (double PE_Base_Comissao_Motorista) {
    this.PE_Base_Comissao_Motorista = PE_Base_Comissao_Motorista;
  }

  public String getDT_Alteracao () {
    return DT_Alteracao;
  }

  public void setDT_Alteracao (String alteracao) {
    DT_Alteracao = alteracao;
  }

  public String getDT_Desativado () {
    return DT_Desativado;
  }

  public void setDT_Desativado (String desativado) {
    DT_Desativado = desativado;
  }

  public String getDM_Resp_Vendedor () {
    return DM_Resp_Vendedor;
  }

  public void setDM_Resp_Vendedor (String resp_Vendedor) {
    DM_Resp_Vendedor = resp_Vendedor;
  }

  public String getDM_Wms () {
    return DM_Wms;
  }

  public void setDM_Wms (String wms) {
    DM_Wms = wms;
  }

  public int getOID_Modal () {
    return OID_Modal;
  }

  public void setOID_Modal (int modal) {
    OID_Modal = modal;
  }

  public int getNR_Dias_Contagem_Ciclica () {
    return NR_Dias_Contagem_Ciclica;
  }

  public void setNR_Dias_Contagem_Ciclica (int dias_Contagem_Ciclica) {
    NR_Dias_Contagem_Ciclica = dias_Contagem_Ciclica;
  }

public String getCd_Carteira() {
	return cd_Carteira;
}

public void setCd_Carteira(String cd_Carteira) {
	this.cd_Carteira = cd_Carteira;
}

public String getDM_Carregamento() {
	return DM_Carregamento;
}

public void setDM_Carregamento(String carregamento) {
	DM_Carregamento = carregamento;
}

public String getDM_ICMS_WMS() {
	return DM_ICMS_WMS;
}

public void setDM_ICMS_WMS(String dm_icms_wms) {
	DM_ICMS_WMS = dm_icms_wms;
}

public int getOid_Carteira() {
	return oid_Carteira;
}

public void setOid_Carteira(int oid_Carteira) {
	this.oid_Carteira = oid_Carteira;
}

public String getOid_Cliente() {
	return oid_Cliente;
}

public void setOid_Cliente(String oid_Cliente) {
	this.oid_Cliente = oid_Cliente;
}

public String getOid_Cliente_Pagador() {
	return oid_Cliente_Pagador;
}

public void setOid_Cliente_Pagador(String oid_Cliente_Pagador) {
	this.oid_Cliente_Pagador = oid_Cliente_Pagador;
}

public int getOid_Grupo_Economico() {
	return oid_Grupo_Economico;
}

public void setOid_Grupo_Economico(int oid_Grupo_Economico) {
	this.oid_Grupo_Economico = oid_Grupo_Economico;
}

public String getOid_Pessoa() {
	return oid_Pessoa;
}

public void setOid_Pessoa(String oid_Pessoa) {
	this.oid_Pessoa = oid_Pessoa;
}

public int getOid_Ramo_Atividade() {
	return oid_Ramo_Atividade;
}

public void setOid_Ramo_Atividade(int oid_Ramo_Atividade) {
	this.oid_Ramo_Atividade = oid_Ramo_Atividade;
}

public String getOid_Vendedor() {
	return oid_Vendedor;
}

public void setOid_Vendedor(String oid_Vendedor) {
	this.oid_Vendedor = oid_Vendedor;
}

public String getNM_Inscricao_Estadual() {
	return NM_Inscricao_Estadual;
}

public void setNM_Inscricao_Estadual(String inscricao_Estadual) {
	NM_Inscricao_Estadual = inscricao_Estadual;
}

public String getDT_Ultimo_Movimento() {
	return DT_Ultimo_Movimento;
}

public void setDT_Ultimo_Movimento(String ultimo_Movimento) {
	DT_Ultimo_Movimento = ultimo_Movimento;
}

public int getNR_Dias_Bloqueio() {
	return NR_Dias_Bloqueio;
}

public void setNR_Dias_Bloqueio(int dias_Bloqueio) {
	NR_Dias_Bloqueio = dias_Bloqueio;
}

public String getDT_Bloqueio() {
	return DT_Bloqueio;
}

public void setDT_Bloqueio(String bloqueio) {
	DT_Bloqueio = bloqueio;
}

public String getDM_Faturamento() {
	return DM_Faturamento;
}

public void setDM_Faturamento(String faturamento) {
	DM_Faturamento = faturamento;
}
}