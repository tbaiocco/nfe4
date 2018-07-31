package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CarteiraED extends MasterED implements java.io.Serializable{
  private Integer oid_Carteira;
  private String dm_Carteira;
  private Double pe_Juros;
  private Double pe_Multa;
  private Integer nr_Remessa;
  private String cd_Carteira;
  private String dm_Remete_EDI;
  private String cd_Empresa_Banco;
  private String dt_abertura_carteira;
  private String dt_Encerramento_Carteira;
  private String dm_Tipo_Emissao;
  private String nr_Convenio;
  private String dm_Alterar_Vencimento;
  private String dm_Conceder_Desconto;
  private String dm_Pedido_Baixa;
  private String dm_Protestar;
  private String dm_Sustar_Protesto;
  private Double nr_Taxa_Desconto;
  private Double nr_Limite_Credito;
  private String oid_Conta_Corrente;
  private String nm_Razao_Social;
  private String oid_Pessoa;
  private String nr_CNPJ_CPF;
  private String cd_Conta_Corrente;
  private String nr_Conta_Corrente;
  private String cd_Tipo_Carteira;
  private String cd_banco;
  private String DT_Inicio_EDI;
  private String oid_Moeda;
  private String DM_Tipo_Impressao_Bloqueto;
  private long NR_Bloqueto_Inicial;
  private long NR_Bloqueto_Atual;
  private long NR_Bloqueto_Final;
  private String NR_Dias_Liberacao_Cobranca;
  private String TX_Bloqueto;
  private String TX_Fatura;

  public String getOid_Moeda() {
      return oid_Moeda;
  }
  public void setOid_Moeda(String oid_Moeda) {
      this.oid_Moeda = oid_Moeda;
  }
  public Integer getOid_Carteira() {
    return oid_Carteira;
  }
  public void setOid_Carteira(Integer oid_Carteira) {
    this.oid_Carteira = oid_Carteira;
  }
  public void setDm_Carteira(String dm_Carteira) {
    this.dm_Carteira = dm_Carteira;
  }
  public String getDm_Carteira() {
    return dm_Carteira;
  }
  public void setPe_Juros(Double pe_Juros) {
    this.pe_Juros = pe_Juros;
  }
  public Double getPe_Juros() {
    return pe_Juros;
  }
  public void setPe_Multa(Double pe_Multa) {
    this.pe_Multa = pe_Multa;
  }
  public Double getPe_Multa() {
    return pe_Multa;
  }
  public void setCd_Tipo_Carteira(String cd_Tipo_Carteira) {
    this.cd_Tipo_Carteira = cd_Tipo_Carteira;
  }
  public String getCd_Tipo_Carteira() {
    return cd_Tipo_Carteira;
  }
  public void setNr_Remessa(Integer nr_Remessa) {
    this.nr_Remessa = nr_Remessa;
  }
  public Integer getNr_Remessa() {
    return nr_Remessa;
  }
  public void setCd_Carteira(String cd_Carteira) {
    this.cd_Carteira = cd_Carteira;
  }
  public String getCd_Carteira() {
    return cd_Carteira;
  }
  public void setDm_Remete_EDI(String dm_Remete_EDI) {
    this.dm_Remete_EDI = dm_Remete_EDI;
  }
  public String getDm_Remete_EDI() {
    return dm_Remete_EDI;
  }
  public void setCd_Empresa_Banco(String cd_Empresa_Banco) {
    this.cd_Empresa_Banco = cd_Empresa_Banco;
  }
  public String getCd_Empresa_Banco() {
    return cd_Empresa_Banco;
  }
  public void setDt_abertura_carteira(String dt_abertura_carteira) {
    this.dt_abertura_carteira = dt_abertura_carteira;
  }
  public String getDt_abertura_carteira() {
    return dt_abertura_carteira;
  }
  public void setDt_Encerramento_Carteira(String dt_Encerramento_Carteira) {
    this.dt_Encerramento_Carteira = dt_Encerramento_Carteira;
  }
  public String getDt_Encerramento_Carteira() {
    return dt_Encerramento_Carteira;
  }
  public void setDm_Tipo_Emissao(String dm_Tipo_Emissao) {
    this.dm_Tipo_Emissao = dm_Tipo_Emissao;
  }
  public String getDm_Tipo_Emissao() {
    return dm_Tipo_Emissao;
  }
  public void setNr_Convenio(String nr_Convenio) {
    this.nr_Convenio = nr_Convenio;
  }
  public String getNr_Convenio() {
    return nr_Convenio;
  }
  public void setDm_Alterar_Vencimento(String dm_Alterar_Vencimento) {
    this.dm_Alterar_Vencimento = dm_Alterar_Vencimento;
  }
  public String getDm_Alterar_Vencimento() {
    return dm_Alterar_Vencimento;
  }
  public void setDm_Conceder_Desconto(String dm_Conceder_Desconto) {
    this.dm_Conceder_Desconto = dm_Conceder_Desconto;
  }
  public String getDm_Conceder_Desconto() {
    return dm_Conceder_Desconto;
  }
  public void setDm_Pedido_Baixa(String dm_Pedido_Baixa) {
    this.dm_Pedido_Baixa = dm_Pedido_Baixa;
  }
  public String getDm_Pedido_Baixa() {
    return dm_Pedido_Baixa;
  }
  public void setDm_Protestar(String dm_Protestar) {
    this.dm_Protestar = dm_Protestar;
  }
  public String getDm_Protestar() {
    return dm_Protestar;
  }
  public void setDm_Sustar_Protesto(String dm_Sustar_Protesto) {
    this.dm_Sustar_Protesto = dm_Sustar_Protesto;
  }
  public String getDm_Sustar_Protesto() {
    return dm_Sustar_Protesto;
  }
  public void setNr_Taxa_Desconto(Double nr_Taxa_Desconto) {
    this.nr_Taxa_Desconto = nr_Taxa_Desconto;
  }
  public Double getNr_Taxa_Desconto() {
    return nr_Taxa_Desconto;
  }
  public void setNr_Limite_Credito(Double nr_Limite_Credito) {
    this.nr_Limite_Credito = nr_Limite_Credito;
  }
  public Double getNr_Limite_Credito() {
    return nr_Limite_Credito;
  }
  public void setOid_Conta_Corrente(String oid_Conta_Corrente) {
    this.oid_Conta_Corrente = oid_Conta_Corrente;
  }
  public String getOid_Conta_Corrente() {
    return oid_Conta_Corrente;
  }
  public void setNm_Razao_Social(String nm_Razao_Social) {
    this.nm_Razao_Social = nm_Razao_Social;
  }
  public String getNm_Razao_Social() {
    return nm_Razao_Social;
  }
  public void setOid_Pessoa(String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }
  public String getOid_Pessoa() {
    return oid_Pessoa;
  }
  public void setNr_CNPJ_CPF(String nr_CNPJ_CPF) {
    this.nr_CNPJ_CPF = nr_CNPJ_CPF;
  }
  public String getNr_CNPJ_CPF() {
    return nr_CNPJ_CPF;
  }
  public void setCd_Conta_Corrente(String cd_Conta_Corrente) {
    this.cd_Conta_Corrente = cd_Conta_Corrente;
  }
  public String getCd_Conta_Corrente() {
    return cd_Conta_Corrente;
  }
  public void setNr_Conta_Corrente(String nr_Conta_Corrente) {
    this.nr_Conta_Corrente = nr_Conta_Corrente;
  }
  public String getNr_Conta_Corrente() {
    return nr_Conta_Corrente;
  }
  public void setCd_banco(String cd_banco) {
    this.cd_banco = cd_banco;
  }

  public void setTX_Fatura (String TX_Fatura) {
    this.TX_Fatura = TX_Fatura;
  }

  public void setTX_Bloqueto (String TX_Bloqueto) {
    this.TX_Bloqueto = TX_Bloqueto;
  }

  public void setNR_Dias_Liberacao_Cobranca (String NR_Dias_Liberacao_Cobranca) {
    this.NR_Dias_Liberacao_Cobranca = NR_Dias_Liberacao_Cobranca;
  }

  public String getCd_banco() {
    return cd_banco;
  }

  public String getTX_Fatura () {
    return TX_Fatura;
  }

  public String getTX_Bloqueto () {
    return TX_Bloqueto;
  }

  public String getNR_Dias_Liberacao_Cobranca () {
    return NR_Dias_Liberacao_Cobranca;
  }

  public void setDT_Inicio_EDI(String DT_Inicio_EDI) {
    this.DT_Inicio_EDI = DT_Inicio_EDI;
  }
  public String getDT_Inicio_EDI() {
    return DT_Inicio_EDI;
  }
  public String getDM_Tipo_Impressao_Bloqueto() {
      return this.DM_Tipo_Impressao_Bloqueto;
  }
  public void setDM_Tipo_Impressao_Bloqueto(String tipo_Impressao_Bloqueto) {
      this.DM_Tipo_Impressao_Bloqueto = tipo_Impressao_Bloqueto;
  }
  public long getNR_Bloqueto_Final() {
      return this.NR_Bloqueto_Final;
  }
  public void setNR_Bloqueto_Final(long bloqueto_Final) {
      this.NR_Bloqueto_Final = bloqueto_Final;
  }
  public long getNR_Bloqueto_Inicial() {
      return this.NR_Bloqueto_Inicial;
  }
  public void setNR_Bloqueto_Inicial(long bloqueto_Inicial) {
      this.NR_Bloqueto_Inicial = bloqueto_Inicial;
  }
  public long getNR_Bloqueto_Atual() {
      return NR_Bloqueto_Atual;
}
  public void setNR_Bloqueto_Atual(long bloqueto_Atual) {
      NR_Bloqueto_Atual = bloqueto_Atual;
  }
}