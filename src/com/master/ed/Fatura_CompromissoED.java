package com.master.ed;

public class Fatura_CompromissoED
    extends MasterED {

  private Integer oid_Fatura_Compromisso;
  private String dt_Emissao;
  private Double vl_Fatura_Compromisso;
  private String tx_Observacao;
  private Integer nr_Fatura_Compromisso;
  private String oid_Pessoa;
  private Long oid_Unidade;
  private String nr_CNPJ_CPF;
  private String nm_Razao_Social;
  private String cd_Unidade;
  private String nm_Fantasia;
  private String nr_Proximo_Numero;
  private String DM_Imprimir;
  private String nr_Documento;
  private Integer NR_Compromisso;
  private String DM_Situacao;
  private String dt_Inicial;
  private Integer NR_Fatura_Compromisso_Final;
  private long OID_Compromisso;
  private String DM_Relatorio;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private double VL_Compromisso;
  private double VL_Saldo;


  public Fatura_CompromissoED () {
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

  public String getDescDM_Situacao () {
    if ("L".equals (DM_Situacao)) {
      return "Impresso";
    }
    else if ("I".equals (DM_Situacao)) {
      return "Não Impresso";
    }
    else if ("F".equals (DM_Situacao)) {
      return "Finalizado C/C";
    }
    else if ("C".equals (DM_Situacao)) {
      return "Cancelado";
    }
    else {
      return "Não Informada!";
    }
  }

  public String getDt_Emissao () {
    return dt_Emissao;
  }

  public Integer getNr_Fatura_Compromisso () {
    return nr_Fatura_Compromisso;
  }

  public String getNr_Documento () {
    return nr_Documento;
  }

  public Integer getOid_Fatura_Compromisso () {
    return oid_Fatura_Compromisso;
  }

  public String getOid_Pessoa () {
    return oid_Pessoa;
  }

  public Long getOid_Unidade () {
    return oid_Unidade;
  }

  public String getTx_Observacao () {
    return tx_Observacao;
  }

  public Double getVl_Fatura_Compromisso () {
    return vl_Fatura_Compromisso;
  }

  public void setVl_Fatura_Compromisso (Double vl_Fatura_Compromisso) {
    this.vl_Fatura_Compromisso = vl_Fatura_Compromisso;
  }

  public void setTx_Observacao (String tx_Observacao) {
    this.tx_Observacao = tx_Observacao;
  }

  public void setOid_Unidade (Long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }

  public void setOid_Pessoa (String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }

  public void setOid_Fatura_Compromisso (Integer oid_Fatura_Compromisso) {
    this.oid_Fatura_Compromisso = oid_Fatura_Compromisso;
  }

  public void setNr_Documento (String nr_Documento) {
    this.nr_Documento = nr_Documento;
  }

  public void setNr_Fatura_Compromisso (Integer nr_Fatura_Compromisso) {
    this.nr_Fatura_Compromisso = nr_Fatura_Compromisso;
  }

  public void setDt_Emissao (String dt_Emissao) {
    this.dt_Emissao = dt_Emissao;
  }

  public void setNr_CNPJ_CPF (String nr_CNPJ_CPF) {
    this.nr_CNPJ_CPF = nr_CNPJ_CPF;
  }

  public String getNr_CNPJ_CPF () {
    return nr_CNPJ_CPF;
  }

  public void setNm_Razao_Social (String nm_Razao_Social) {
    this.nm_Razao_Social = nm_Razao_Social;
  }

  public String getNm_Razao_Social () {
    return nm_Razao_Social;
  }

  public void setCd_Unidade (String cd_Unidade) {
    this.cd_Unidade = cd_Unidade;
  }

  public String getCd_Unidade () {
    return cd_Unidade;
  }

  public void setNm_Fantasia (String nm_Fantasia) {
    this.nm_Fantasia = nm_Fantasia;
  }

  public String getNm_Fantasia () {
    return nm_Fantasia;
  }

  public void setNr_Proximo_Numero (String nr_Proximo_Numero) {
    this.nr_Proximo_Numero = nr_Proximo_Numero;
  }

  public String getNr_Proximo_Numero () {
    return nr_Proximo_Numero;
  }

  public void setDM_Imprimir (String DM_Imprimir) {
    this.DM_Imprimir = DM_Imprimir;
  }

  public String getDM_Imprimir () {
    return DM_Imprimir;
  }

  public void setNR_Compromisso (Integer NR_Compromisso) {
    this.NR_Compromisso = NR_Compromisso;
  }

  public Integer getNR_Compromisso () {
    return NR_Compromisso;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setDt_Inicial (String dt_Inicial) {
    this.dt_Inicial = dt_Inicial;
  }

  public void setVL_Saldo (double VL_Saldo) {
    this.VL_Saldo = VL_Saldo;
  }

  public void setVL_Compromisso (double VL_Compromisso) {
    this.VL_Compromisso = VL_Compromisso;
  }

  public void setDT_Emissao_Final (String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }

  public void setDT_Emissao_Inicial (String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }

  public String getDt_Inicial () {
    return dt_Inicial;
  }

  public double getVL_Saldo () {
    return VL_Saldo;
  }

  public double getVL_Compromisso () {
    return VL_Compromisso;
  }

  public String getDT_Emissao_Final () {
    return DT_Emissao_Final;
  }

  public String getDT_Emissao_Inicial () {
    return DT_Emissao_Inicial;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public void setNR_Fatura_Compromisso_Final (Integer NR_Fatura_Compromisso_Final) {
    this.NR_Fatura_Compromisso_Final = NR_Fatura_Compromisso_Final;
  }

  public Integer getNR_Fatura_Compromisso_Final () {
    return NR_Fatura_Compromisso_Final;
  }

  public void setOID_Compromisso (long OID_Compromisso) {
    this.OID_Compromisso = OID_Compromisso;
  }

  public long getOID_Compromisso () {
    return OID_Compromisso;
  }

  private void jbInit () throws Exception {
  }
}
