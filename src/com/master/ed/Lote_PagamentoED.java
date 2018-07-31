package com.master.ed;
 
public class Lote_PagamentoED extends MasterED {

    private Integer oid_Lote_Pagamento;
    private String dt_Emissao;   
    private String DT_Programada;
    private Double vl_Lote_Pagamento;
    private String tx_Observacao;
    private Integer nr_Lote_Pagamento;
    private String oid_Pessoa;
    private Long oid_Unidade;
    private String nr_CNPJ_CPF;
    private String nm_Razao_Social;
    private String nm_Razao_Social_Destino;
    private String cd_Unidade;
    private String nm_Fantasia;
    private String DM_Copia_Cheque;
    private String nr_Proximo_Numero;
    private String OID_Conta_Corrente;
    private String OID_Conta_Corrente_Destino;
    private String NR_Conta_Corrente;
    private String NM_Favorecido;
    private String DM_Imprimir;
    private String nr_Documento;
    private String CD_Tipo_Documento;
    private String NM_Tipo_Documento;
    private Integer OID_Tipo_Documento;
    private String CD_Conta_Corrente;
    private String CD_Conta_Corrente_Destino;
    private String NM_Razao_Social_Banco;
    private Integer NR_Compromisso;
    private String DM_Situacao;
    private String DT_Compensacao;
    private String dt_Inicial;
    private String dt_Final;
    private String DM_Compensado;
    private long NR_Cheque;
    private Integer NR_Lote_Pagamento_Final;
    private long OID_Compromisso;
    private long OID_Compromisso_Troca;
    private long NR_Cheque_Inicial;
    private long NR_Cheque_Final;
    private Integer oid_Documento_Conta_Corrente;
  private String OID_Fornecedor;
  private String DM_Relatorio;
  private String DT_Apresentacao1;
  private String DT_Apresentacao2;
  private String DT_Apresentacao3;
  private String DM_Apresentacao;
  private int oid_Motivo_Retorno;
  private String DT_Apresentacao;
  private String CD_Motivo_Retorno;
  private String NM_Motivo_Retorno;
  private double VL_Compromisso;

  public Lote_PagamentoED() {
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

    public String getDescDM_Situacao(){
        if ("L".equals(DM_Situacao))
           return "Impresso";
        else if ("I".equals(DM_Situacao))
           return "Não Impresso";
        else if ("F".equals(DM_Situacao))
           return "Finalizado C/C";
        else if ("C".equals(DM_Situacao))
           return "Cancelado";
        else return "Não Informada!";
    }

    public String getDt_Emissao() {
        return dt_Emissao;
    }

    public String getDT_Programada() {
        return DT_Programada;
    }

    public Integer getNr_Lote_Pagamento() {
        return nr_Lote_Pagamento;
    }

    public String getNr_Documento() {
        return nr_Documento;
    }

    public Integer getOid_Lote_Pagamento() {
        return oid_Lote_Pagamento;
    }

    public String getOid_Pessoa() {
        return oid_Pessoa;
    }

    public Long getOid_Unidade() {
        return oid_Unidade;
    }

    public String getTx_Observacao() {
        return tx_Observacao;
    }

    public Double getVl_Lote_Pagamento() {
        return vl_Lote_Pagamento;
    }

    public void setVl_Lote_Pagamento(Double vl_Lote_Pagamento) {
        this.vl_Lote_Pagamento = vl_Lote_Pagamento;
    }

    public void setTx_Observacao(String tx_Observacao) {
        this.tx_Observacao = tx_Observacao;
    }

    public void setOid_Unidade(Long oid_Unidade) {
        this.oid_Unidade = oid_Unidade;
    }

    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }

    public void setOid_Lote_Pagamento(Integer oid_Lote_Pagamento) {
        this.oid_Lote_Pagamento = oid_Lote_Pagamento;
    }

    public void setNr_Documento(String nr_Documento) {
        this.nr_Documento = nr_Documento;
    }

    public void setNr_Lote_Pagamento(Integer nr_Lote_Pagamento) {
        this.nr_Lote_Pagamento = nr_Lote_Pagamento;
    }

    public void setDT_Programada(String DT_Programada) {
        this.DT_Programada = DT_Programada;
    }

    public void setDt_Emissao(String dt_Emissao) {
        this.dt_Emissao = dt_Emissao;
    }

    public void setNr_CNPJ_CPF(String nr_CNPJ_CPF) {
        this.nr_CNPJ_CPF = nr_CNPJ_CPF;
    }

    public String getNr_CNPJ_CPF() {
        return nr_CNPJ_CPF;
    }

    public void setNm_Razao_Social(String nm_Razao_Social) {
        this.nm_Razao_Social = nm_Razao_Social;
    }

    public String getNm_Razao_Social() {
        return nm_Razao_Social;
    }

    public void setCd_Unidade(String cd_Unidade) {
        this.cd_Unidade = cd_Unidade;
    }

    public String getCd_Unidade() {
        return cd_Unidade;
    }

    public void setNm_Fantasia(String nm_Fantasia) {
        this.nm_Fantasia = nm_Fantasia;
    }

    public String getNm_Fantasia() {
        return nm_Fantasia;
    }

    public void setDM_Copia_Cheque(String DM_Copia_Cheque) {
        this.DM_Copia_Cheque = DM_Copia_Cheque;
    }

    public String getDM_Copia_Cheque() {
        return DM_Copia_Cheque;
    }

    public void setNr_Proximo_Numero(String nr_Proximo_Numero) {
        this.nr_Proximo_Numero = nr_Proximo_Numero;
    }

    public String getNr_Proximo_Numero() {
        return nr_Proximo_Numero;
    }

    public void setOID_Conta_Corrente(String OID_Conta_Corrente) {
        this.OID_Conta_Corrente = OID_Conta_Corrente;
    }

    public String getOID_Conta_Corrente() {
        return OID_Conta_Corrente;
    }

    public void setNR_Conta_Corrente(String NR_Conta_Corrente) {
        this.NR_Conta_Corrente = NR_Conta_Corrente;
    }

    public String getNR_Conta_Corrente() {
        return NR_Conta_Corrente;
    }

    public void setNM_Favorecido(String NM_Favorecido) {
        this.NM_Favorecido = NM_Favorecido;
    }

    public String getNM_Favorecido() {
        return NM_Favorecido;
    }

    public void setDM_Imprimir(String DM_Imprimir) {
        this.DM_Imprimir = DM_Imprimir;
    }

    public String getDM_Imprimir() {
        return DM_Imprimir;
    }

    public void setCD_Tipo_Documento(String CD_Tipo_Documento) {
        this.CD_Tipo_Documento = CD_Tipo_Documento;
    }

    public String getCD_Tipo_Documento() {
        return CD_Tipo_Documento;
    }

    public void setNM_Tipo_Documento(String NM_Tipo_Documento) {
        this.NM_Tipo_Documento = NM_Tipo_Documento;
    }

    public String getNM_Tipo_Documento() {
        return NM_Tipo_Documento;
    }

    public void setOID_Tipo_Documento(Integer OID_Tipo_Documento) {
        this.OID_Tipo_Documento = OID_Tipo_Documento;
    }

    public Integer getOID_Tipo_Documento() {
        return OID_Tipo_Documento;
    }

    public void setCD_Conta_Corrente(String CD_Conta_Corrente) {
        this.CD_Conta_Corrente = CD_Conta_Corrente;
    }

    public String getCD_Conta_Corrente() {
        return CD_Conta_Corrente;
    }

    public void setNM_Razao_Social_Banco(String NM_Razao_Social_Banco) {
        this.NM_Razao_Social_Banco = NM_Razao_Social_Banco;
    }

    public String getNM_Razao_Social_Banco() {
        return NM_Razao_Social_Banco;
    }

    public void setNR_Compromisso(Integer NR_Compromisso) {
        this.NR_Compromisso = NR_Compromisso;
    }

    public Integer getNR_Compromisso() {
        return NR_Compromisso;
    }

    public void setDM_Situacao(String DM_Situacao) {
        this.DM_Situacao = DM_Situacao;
    }

    public String getDM_Situacao() {
        return DM_Situacao;
    }

    public void setDT_Compensacao(String DT_Compensacao) {
        this.DT_Compensacao = DT_Compensacao;
    }

    public String getDT_Compensacao() {
        return DT_Compensacao;
    }

    public void setDt_Inicial(String dt_Inicial) {
        this.dt_Inicial = dt_Inicial;
    }

    public String getDt_Inicial() {
        return dt_Inicial;
    }

    public void setDt_Final(String dt_Final) {
        this.dt_Final = dt_Final;
    }

    public void setOid_Documento_Conta_Corrente(Integer oid_Documento_Conta_Corrente) {
        this.oid_Documento_Conta_Corrente = oid_Documento_Conta_Corrente;
    }

  public void setOid_Motivo_Retorno (int oid_Motivo_Retorno) {
    this.oid_Motivo_Retorno = oid_Motivo_Retorno;
  }

  public void setNm_Razao_Social_Destino (String nm_Razao_Social_Destino) {
    this.nm_Razao_Social_Destino = nm_Razao_Social_Destino;
  }

  public void setCD_Conta_Corrente_Destino (String CD_Conta_Corrente_Destino) {
    this.CD_Conta_Corrente_Destino = CD_Conta_Corrente_Destino;
  }

  public void setOID_Conta_Corrente_Destino (String OID_Conta_Corrente_Destino) {
    this.OID_Conta_Corrente_Destino = OID_Conta_Corrente_Destino;
  }

  public void setVL_Compromisso (double VL_Compromisso) {
    this.VL_Compromisso = VL_Compromisso;
  }

  public void setNM_Motivo_Retorno (String NM_Motivo_Retorno) {
    this.NM_Motivo_Retorno = NM_Motivo_Retorno;
  }

  public void setCD_Motivo_Retorno (String CD_Motivo_Retorno) {
    this.CD_Motivo_Retorno = CD_Motivo_Retorno;
  }

  public void setDT_Apresentacao (String DT_Apresentacao) {
    this.DT_Apresentacao = DT_Apresentacao;
  }

  public void setDM_Apresentacao (String DM_Apresentacao) {
    this.DM_Apresentacao = DM_Apresentacao;
  }

  public void setDT_Apresentacao3 (String DT_Apresentacao3) {
    this.DT_Apresentacao3 = DT_Apresentacao3;
  }

  public void setDT_Apresentacao2 (String DT_Apresentacao2) {
    this.DT_Apresentacao2 = DT_Apresentacao2;
  }

  public void setDT_Apresentacao1 (String DT_Apresentacao1) {
    this.DT_Apresentacao1 = DT_Apresentacao1;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public void setOID_Fornecedor (String OID_Fornecedor) {
    this.OID_Fornecedor = OID_Fornecedor;
  }

  public String getDt_Final() {
        return dt_Final;
    }

    public Integer getOid_Documento_Conta_Corrente() {
        return oid_Documento_Conta_Corrente;
    }

  public int getOid_Motivo_Retorno () {
    return oid_Motivo_Retorno;
  }

  public String getNm_Razao_Social_Destino () {
    return nm_Razao_Social_Destino;
  }

  public String getCD_Conta_Corrente_Destino () {
    return CD_Conta_Corrente_Destino;
  }

  public String getOID_Conta_Corrente_Destino () {
    return OID_Conta_Corrente_Destino;
  }

  public double getVL_Compromisso () {
    return VL_Compromisso;
  }

  public String getNM_Motivo_Retorno () {
    return NM_Motivo_Retorno;
  }

  public String getCD_Motivo_Retorno () {
    return CD_Motivo_Retorno;
  }

  public String getDT_Apresentacao () {
    return DT_Apresentacao;
  }

  public String getDM_Apresentacao () {
    return DM_Apresentacao;
  }

  public String getDT_Apresentacao3 () {
    return DT_Apresentacao3;
  }

  public String getDT_Apresentacao2 () {
    return DT_Apresentacao2;
  }

  public String getDT_Apresentacao1 () {
    return DT_Apresentacao1;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public String getOID_Fornecedor () {
    return OID_Fornecedor;
  }

  public void setDM_Compensado(String DM_Compensado) {
        this.DM_Compensado = DM_Compensado;
    }

    public String getDM_Compensado() {
        return DM_Compensado;
    }

    public void setNR_Cheque(long NR_Cheque) {
        this.NR_Cheque = NR_Cheque;
    }

    public long getNR_Cheque() {
        return NR_Cheque;
    }

    public void setNR_Lote_Pagamento_Final(Integer NR_Lote_Pagamento_Final) {
        this.NR_Lote_Pagamento_Final = NR_Lote_Pagamento_Final;
    }

    public Integer getNR_Lote_Pagamento_Final() {
        return NR_Lote_Pagamento_Final;
    }

    public void setOID_Compromisso(long OID_Compromisso) {
        this.OID_Compromisso = OID_Compromisso;
    }

    public long getOID_Compromisso() {
        return OID_Compromisso;
    }

    public void setNR_Cheque_Inicial(long NR_Cheque_Inicial) {
        this.NR_Cheque_Inicial = NR_Cheque_Inicial;
    }

    public long getNR_Cheque_Inicial() {
        return NR_Cheque_Inicial;
    }

    public void setNR_Cheque_Final(long NR_Cheque_Final) {
        this.NR_Cheque_Final = NR_Cheque_Final;
    }

    public long getNR_Cheque_Final() {
        return NR_Cheque_Final;
    }

  private void jbInit () throws Exception {
  }

public long getOID_Compromisso_Troca() {
	return OID_Compromisso_Troca;
}

public void setOID_Compromisso_Troca(long compromisso_Troca) {
	OID_Compromisso_Troca = compromisso_Troca;
}
}
