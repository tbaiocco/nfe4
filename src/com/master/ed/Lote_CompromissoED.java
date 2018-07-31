package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Lote_CompromissoED  {

  private String dt_Pagamento;
  private Double vl_Pagamento;
  private Double vl_Multa_Pagamento;
  private Double vl_Juros_Pagamento;
  private Double vl_Desconto;
  private Double vl_Outras_Despesas;
  private String tx_Observacao;
  private String dm_Tipo_Pagamento;
  private Integer nr_Lote_Pagamento;
  private String oid_Lote_Compromisso;
  private Double vl_Saldo_Lote_Compromisso;
  private String DM_Situacao;
  private Double vl_Lote_Pagamento;
  private long OID_Modelo;
  private String DM_Contabiliza;
  private Double VL_Pedagio_Receber;
  private Double VL_Pedagio_Pagar;
  private Double VL_Juro_Pagamento;
  private long OID_Unidade;
  private boolean DM_Tem_Lancamento_Contabil;

  private Integer oid_Compromisso;
  private String dt_Emissao;
  private Double vl_Compromisso = new Double (0);
  private String nr_Documento;
  private String dt_Stamp;
  private Integer nr_Parcela;
  private Integer nr_Compromisso;
  private Double vl_Saldo = new Double (0);
  private String nm_Razao_Social;
  private String nm_Tipo_Documento;
  private String Dt_Pgto_Final;
  private String Dt_Pgto_Inicial;
  private String Oid_Pessoa;
  private long Oid_Lote_Pagamento;  

  public long getOid_Lote_Pagamento() {
	return Oid_Lote_Pagamento;
}
public void setOid_Lote_Pagamento(long oid_Lote_Pagamento) {
	Oid_Lote_Pagamento = oid_Lote_Pagamento;
}
public String getOid_Pessoa() {
	return Oid_Pessoa;
}
public void setOid_Pessoa(String oid_Pessoa) {
	Oid_Pessoa = oid_Pessoa;
}
public String getDt_Pgto_Inicial() {
	return Dt_Pgto_Inicial;
}
public void setDt_Pgto_Inicial(String dt_Pgto_Inicial) {
	Dt_Pgto_Inicial = dt_Pgto_Inicial;
}
public String getNm_Razao_Social() {
	return nm_Razao_Social;
}
public void setNm_Razao_Social(String nm_Razao_Social) {
	this.nm_Razao_Social = nm_Razao_Social;
}
public String getNm_Tipo_Documento() {
	return nm_Tipo_Documento;
}
public void setNm_Tipo_Documento(String nm_Tipo_Documento) {
	this.nm_Tipo_Documento = nm_Tipo_Documento;
}
public Integer getNr_Compromisso() {
	return nr_Compromisso;
}
public void setNr_Compromisso(Integer nr_Compromisso) {
	this.nr_Compromisso = nr_Compromisso;
}
public Integer getNr_Parcela() {
	return nr_Parcela;
}
public void setNr_Parcela(Integer nr_Parcela) {
	this.nr_Parcela = nr_Parcela;
}
public Double getVl_Saldo() {
	return vl_Saldo;
}
public void setVl_Saldo(Double vl_Saldo) {
	this.vl_Saldo = vl_Saldo;
}
public String getNr_Documento() {
	return nr_Documento;
}
public void setNr_Documento(String nr_Documento) {
	this.nr_Documento = nr_Documento;
}
public String getDt_Emissao() {
	return dt_Emissao;
}
public void setDt_Emissao(String dt_Emissao) {
	this.dt_Emissao = dt_Emissao;
}
public Integer getOid_Compromisso() {
	return oid_Compromisso;
}
public void setOid_Compromisso(Integer oid_Compromisso) {
	this.oid_Compromisso = oid_Compromisso;
}
public Double getVl_Compromisso() {
	return vl_Compromisso;
}
public void setVl_Compromisso(Double vl_Compromisso) {
	this.vl_Compromisso = vl_Compromisso;
}
public Lote_CompromissoED() {
  }
  public String getDm_Tipo_Pagamento() {
    return dm_Tipo_Pagamento;
  }
  public String getDt_Pagamento() {
    return dt_Pagamento;
  }
  public Integer getNr_Lote_Pagamento() {
    return nr_Lote_Pagamento;
  }
  public String getTx_Observacao() {
    return tx_Observacao;
  }
  public Double getVl_Desconto() {
    return vl_Desconto;
  }
  public Double getVl_Juros_Pagamento() {
    return vl_Juros_Pagamento;
  }
  public Double getVl_Multa_Pagamento() {
    return vl_Multa_Pagamento;
  }
  public Double getVl_Outras_Despesas() {
    return vl_Outras_Despesas;
  }
  public Double getVl_Pagamento() {
    return vl_Pagamento;
  }
  public void setVl_Pagamento(Double vl_Pagamento) {
    this.vl_Pagamento = vl_Pagamento;
  }
  public void setVl_Outras_Despesas(Double vl_Outras_Despesas) {
    this.vl_Outras_Despesas = vl_Outras_Despesas;
  }
  public void setVl_Multa_Pagamento(Double vl_Multa_Pagamento) {
    this.vl_Multa_Pagamento = vl_Multa_Pagamento;
  }
  public void setVl_Juros_Pagamento(Double vl_Juros_Pagamento) {
    this.vl_Juros_Pagamento = vl_Juros_Pagamento;
  }
  public void setVl_Desconto(Double vl_Desconto) {
    this.vl_Desconto = vl_Desconto;
  }
  public void setTx_Observacao(String tx_Observacao) {
    this.tx_Observacao = tx_Observacao;
  }
  public void setNr_Lote_Pagamento(Integer nr_Lote_Pagamento) {
    this.nr_Lote_Pagamento = nr_Lote_Pagamento;
  }
  public void setDt_Pagamento(String dt_Pagamento) {
    this.dt_Pagamento = dt_Pagamento;
  }
  public void setDm_Tipo_Pagamento(String dm_Tipo_Pagamento) {
    this.dm_Tipo_Pagamento = dm_Tipo_Pagamento;
  }

  public void setOid_Lote_Compromisso(String oid_Lote_Compromisso) {
    this.oid_Lote_Compromisso = oid_Lote_Compromisso;
  }
  public String getOid_Lote_Compromisso() {
    return oid_Lote_Compromisso;
  }
  public void setVl_Saldo_Lote_Compromisso(Double vl_Saldo_Lote_Compromisso) {
    this.vl_Saldo_Lote_Compromisso = vl_Saldo_Lote_Compromisso;
  }
  public Double getVl_Saldo_Lote_Compromisso() {
    return vl_Saldo_Lote_Compromisso;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public void setVl_Lote_Pagamento(Double vl_Lote_Pagamento) {
    this.vl_Lote_Pagamento = vl_Lote_Pagamento;
  }
  public Double getVl_Lote_Pagamento() {
    return vl_Lote_Pagamento;
  }
  public String getDM_Contabiliza() {
    return DM_Contabiliza;
  }
  public void setDM_Contabiliza(String DM_Contabiliza) {
    this.DM_Contabiliza = DM_Contabiliza;
  }
  public boolean isDM_Tem_Lancamento_Contabil() {
    return DM_Tem_Lancamento_Contabil;
  }
  public void setDM_Tem_Lancamento_Contabil(boolean DM_Tem_Lancamento_Contabil) {
    this.DM_Tem_Lancamento_Contabil = DM_Tem_Lancamento_Contabil;
  }
  public long getOID_Modelo() {
    return OID_Modelo;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  public void setOID_Modelo(long OID_Modelo) {
    this.OID_Modelo = OID_Modelo;
  }
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public void setVL_Juro_Pagamento(Double VL_Juro_Pagamento) {
    this.VL_Juro_Pagamento = VL_Juro_Pagamento;
  }
  public Double getVL_Juro_Pagamento() {
    return VL_Juro_Pagamento;
  }
  public void setVL_Pedagio_Pagar(Double VL_Pedagio_Pagar) {
    this.VL_Pedagio_Pagar = VL_Pedagio_Pagar;
  }
  public void setVL_Pedagio_Receber(Double VL_Pedagio_Receber) {
    this.VL_Pedagio_Receber = VL_Pedagio_Receber;
  }
  public Double getVL_Pedagio_Pagar() {
    return VL_Pedagio_Pagar;
  }
  public Double getVL_Pedagio_Receber() {
    return VL_Pedagio_Receber;
  }
public String getDt_Stamp() {
	return dt_Stamp;
}
public void setDt_Stamp(String dt_Stamp) {
	this.dt_Stamp = dt_Stamp;
}
public String getDt_Pgto_Final() {
	return Dt_Pgto_Final;
}
public void setDt_Pgto_Final(String dt_Pgto_Final) {
	Dt_Pgto_Final = dt_Pgto_Final;
}



}