package com.master.ed;

public class Movimento_DuplicataED extends DuplicataED {


    private String DT_Movimento;
    private Double VL_Credito;
    private Double VL_Debito;
    private String DM_Gera_Movimento;
    private String CD_Tipo_Instrucao;
    private String NM_Tipo_Instrucao;
    private Integer OID_Tipo_Instrucao;
    private long NR_Sequencia_Duplicata;
    private Integer OID_Carteira;
    private String OID_Duplicata;
    private Integer oid_Bordero;
    private double VL_Imposto_Retido1;
    private double VL_Imposto_Retido2;
    private double VL_Tarifa;
    private double VL_Desconto;
    private double VL_Pago;
    private double VL_Juros;
    private double VL_Liquido;
    private String DM_Tipo_Pagamento;
    private String oid_Movimento_Duplicata;
    private double VL_Saldo;
    private double VL_Juros_Reembolso;
    private double VL_Reembolso;
    private Integer OID_Carteira_Troca;
    private double VL_Cambial_Passivo;
    private double VL_Cambial_Ativo;
    private double VL_Devolucao_Nota_Fiscal;
    private String oid_Conhecimento;
    private String oid_Conta_Corrente;

    private String NR_Conhecimento;
    private String NR_Documento;
    private String DT_Movimento_Conta_Corrente;
    private String NM_Complemento_Historico;
    private String DM_Debito_Credito;
    private String DM_Tipo_Lancamento;
    private Integer oid_Conta;
    private Integer oid_Historico;
    private Integer oid_Tipo_Documento;
    private Double VL_Lancamento;

    private int oid_Entrega;
  private double VL_Variacao_Cambial;
  private String DM_Principal;
  private double VL_Cotacao_Emissao;
  private double VL_Cotacao_Pagamento;
  private double VL_Taxa;
  private String DM_Estornado;

  public String getDM_Debito_Credito() {
        return DM_Debito_Credito;
    }
    public void setDM_Debito_Credito(String debito_Credito) {
        DM_Debito_Credito = debito_Credito;
    }
    public String getDM_Tipo_Lancamento() {
        return DM_Tipo_Lancamento;
    }
    public void setDM_Tipo_Lancamento(String tipo_Lancamento) {
        DM_Tipo_Lancamento = tipo_Lancamento;
    }
    public String getDT_Movimento_Conta_Corrente() {
        return DT_Movimento_Conta_Corrente;
    }
    public void setDT_Movimento_Conta_Corrente(String movimento_Conta_Corrente) {
        DT_Movimento_Conta_Corrente = movimento_Conta_Corrente;
    }
    public String getNM_Complemento_Historico() {
        return NM_Complemento_Historico;
    }

    public void setNM_Complemento_Historico(String complemento_Historico) {
        NM_Complemento_Historico = complemento_Historico;
    }

    public String getNR_Documento() {
        return NR_Documento;
    }

    public void setNR_Documento(String documento) {
        NR_Documento = documento;
    }

    public String getOid_Conta_Corrente() {
        return oid_Conta_Corrente;
    }

    public void setOid_Conta_Corrente(String oid_Conta_Corrente) {
        this.oid_Conta_Corrente = oid_Conta_Corrente;
    }

    public Integer getOid_Historico() {
        return oid_Historico;
    }

    public void setOid_Historico(Integer oid_Historico) {
        this.oid_Historico = oid_Historico;
    }

    public Integer getOid_Tipo_Documento() {
        return oid_Tipo_Documento;
    }

    public void setOid_Tipo_Documento(Integer oid_Tipo_Documento) {
        this.oid_Tipo_Documento = oid_Tipo_Documento;
    }

    public Double getVL_Lancamento() {
        return VL_Lancamento;
    }

    public void setVL_Lancamento(Double lancamento) {
        VL_Lancamento = lancamento;
    }

    public Movimento_DuplicataED() {
    }

    public String getDT_Movimento() {
        return DT_Movimento;
    }

    public String getOid_Movimento_Duplicata() {
        return oid_Movimento_Duplicata;
    }

    public Double getVL_Debito() {
        return VL_Debito;
    }

    public Double getVL_Credito() {
        return VL_Credito;
    }

    public void setVL_Credito(Double VL_Credito) {
        this.VL_Credito = VL_Credito;
    }

    public void setVL_Debito(Double VL_Debito) {
        this.VL_Debito = VL_Debito;
    }

    public void setOid_Movimento_Duplicata(String oid_Movimento_Duplicata) {
        this.oid_Movimento_Duplicata = oid_Movimento_Duplicata;
    }

    public void setDT_Movimento(String DT_Movimento) {
        this.DT_Movimento = DT_Movimento;
    }

    public void setOID_Tipo_Instrucao(Integer OID_Tipo_Instrucao) {
        this.OID_Tipo_Instrucao = OID_Tipo_Instrucao;
    }

    public Integer getOID_Tipo_Instrucao() {
        return OID_Tipo_Instrucao;
    }

    public void setDM_Gera_Movimento(String DM_Gera_Movimento) {
        this.DM_Gera_Movimento = DM_Gera_Movimento;
    }

    public String getDM_Gera_Movimento() {
        return DM_Gera_Movimento;
    }

    public void setCD_Tipo_Instrucao(String CD_Tipo_Instrucao) {
        this.CD_Tipo_Instrucao = CD_Tipo_Instrucao;
    }

    public String getCD_Tipo_Instrucao() {
        return CD_Tipo_Instrucao;
    }

    public void setNM_Tipo_Instrucao(String NM_Tipo_Instrucao) {
        this.NM_Tipo_Instrucao = NM_Tipo_Instrucao;
    }

    public String getNM_Tipo_Instrucao() {
        return NM_Tipo_Instrucao;
    }

    public void setNR_Sequencia_Duplicata(long NR_Sequencia_Duplicata) {
        this.NR_Sequencia_Duplicata = NR_Sequencia_Duplicata;
    }

    public long getNR_Sequencia_Duplicata() {
        return NR_Sequencia_Duplicata;
    }

    public void setOID_Carteira(Integer OID_Carteira) {
        this.OID_Carteira = OID_Carteira;
    }

    public Integer getOID_Carteira() {
        return OID_Carteira;
    }

    public void setOID_Duplicata(String OID_Duplicata) {
        this.OID_Duplicata = OID_Duplicata;
    }

    public String getOID_Duplicata() {
        return OID_Duplicata;
    }

    public void setOid_Bordero(Integer oid_Bordero) {
        this.oid_Bordero = oid_Bordero;
    }

    public void setVL_Devolucao_Nota_Fiscal(double VL_Devolucao_Nota_Fiscal) {
        this.VL_Devolucao_Nota_Fiscal = VL_Devolucao_Nota_Fiscal;
    }

    public Integer getOid_Bordero() {
        return oid_Bordero;
    }

    public double getVL_Devolucao_Nota_Fiscal() {
        return VL_Devolucao_Nota_Fiscal;
    }

    public void setVL_Tarifa(double VL_Tarifa) {
        this.VL_Tarifa = VL_Tarifa;
    }

    public double getVL_Tarifa() {
        return VL_Tarifa;
    }

    public void setVL_Desconto(double VL_Desconto) {
        this.VL_Desconto = VL_Desconto;
    }

    public double getVL_Desconto() {
        return VL_Desconto;
    }

    public void setVL_Pago(double VL_Pago) {
        this.VL_Pago = VL_Pago;
    }

    public double getVL_Pago() {
        return VL_Pago;
    }

    public void setVL_Juros(double VL_Juros) {
        this.VL_Juros = VL_Juros;
    }

    public double getVL_Juros() {
        return VL_Juros;
    }

    public void setVL_Liquido(double VL_Liquido) {
        this.VL_Liquido = VL_Liquido;
    }

    public double getVL_Liquido() {
        return VL_Liquido;
    }

    public void setDM_Tipo_Pagamento(String DM_Tipo_Pagamento) {
        this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
    }

    public String getDM_Tipo_Pagamento() {
        return DM_Tipo_Pagamento;
    }

    public void setVL_Saldo(double VL_Saldo) {
        this.VL_Saldo = VL_Saldo;
    }

    public double getVL_Saldo() {
        return VL_Saldo;
    }

    public void setVL_Juros_Reembolso(double VL_Juros_Reembolso) {
        this.VL_Juros_Reembolso = VL_Juros_Reembolso;
    }

    public double getVL_Juros_Reembolso() {
        return VL_Juros_Reembolso;
    }

    public void setVL_Reembolso(double VL_Reembolso) {
        this.VL_Reembolso = VL_Reembolso;
    }

    public double getVL_Reembolso() {
        return VL_Reembolso;
    }

    public void setOID_Carteira_Troca(Integer OID_Carteira_Troca) {
        this.OID_Carteira_Troca = OID_Carteira_Troca;
    }

    public Integer getOID_Carteira_Troca() {
        return OID_Carteira_Troca;
    }

    public double getVL_Cambial_Ativo() {
        return VL_Cambial_Ativo;
    }

    public void setVL_Cambial_Ativo(double cambial_Ativo) {
        VL_Cambial_Ativo = cambial_Ativo;
    }

    public double getVL_Cambial_Passivo() {
        return VL_Cambial_Passivo;
    }

    public void setVL_Cambial_Passivo(double cambial_Passivo) {
        VL_Cambial_Passivo = cambial_Passivo;
    }

    public int getOid_Entrega() {
        return oid_Entrega;
    }

  public Integer getOid_Conta () {
    return oid_Conta;
  }

  public String getOid_Conhecimento () {
    return oid_Conhecimento;
  }

  public String getDM_Estornado () {
    return DM_Estornado;
  }

  public String getNR_Conhecimento () {
    return NR_Conhecimento;
  }

  public double getVL_Imposto_Retido2 () {
    return VL_Imposto_Retido2;
  }

  public double getVL_Imposto_Retido1 () {
    return VL_Imposto_Retido1;
  }

  public double getVL_Taxa () {
    return VL_Taxa;
  }

  public double getVL_Cotacao_Pagamento () {
    return VL_Cotacao_Pagamento;
  }

  public double getVL_Cotacao_Emissao () {
    return VL_Cotacao_Emissao;
  }

  public String getDM_Principal () {
    return DM_Principal;
  }

  public double getVL_Variacao_Cambial () {
    return VL_Variacao_Cambial;
  }

  public void setOid_Entrega(int oid_Entrega) {
        this.oid_Entrega = oid_Entrega;
    }

  public void setOid_Conta (Integer oid_Conta) {
    this.oid_Conta = oid_Conta;
  }

  public void setOid_Conhecimento (String oid_Conhecimento) {
    this.oid_Conhecimento = oid_Conhecimento;
  }

  public void setDM_Estornado (String DM_Estornado) {
    this.DM_Estornado = DM_Estornado;
  }

  public void setNR_Conhecimento (String NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }

  public void setVL_Imposto_Retido1 (double VL_Imposto_Retido1) {
    this.VL_Imposto_Retido1 = VL_Imposto_Retido1;
  }

  public void setVL_Imposto_Retido2 (double VL_Imposto_Retido2) {
    this.VL_Imposto_Retido2 = VL_Imposto_Retido2;
  }

  public void setVL_Taxa (double VL_Taxa) {
    this.VL_Taxa = VL_Taxa;
  }

  public void setVL_Cotacao_Pagamento (double VL_Cotacao_Pagamento) {
    this.VL_Cotacao_Pagamento = VL_Cotacao_Pagamento;
  }

  public void setVL_Cotacao_Emissao (double VL_Cotacao_Emissao) {
    this.VL_Cotacao_Emissao = VL_Cotacao_Emissao;
  }

  public void setDM_Principal (String DM_Principal) {
    this.DM_Principal = DM_Principal;
  }

  public void setVL_Variacao_Cambial (double VL_Variacao_Cambial) {
    this.VL_Variacao_Cambial = VL_Variacao_Cambial;
  }

}
