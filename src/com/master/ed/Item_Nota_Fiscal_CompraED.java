package com.master.ed;

public class Item_Nota_Fiscal_CompraED
    extends MasterED {

  private String OID_Item_Nota_Fiscal;
  private long oid_Ordem_Servico;
  private long oid_Solicitacao_compra;
  private String DM_Tipo_Produto;
  private String NR_Ordem_Servico;
  private String NR_Placa;
  private long NR_Item_Nota_Fiscal;
  private String OID_Nota_Fiscal;
  private String CD_Referencia;
  private String CD_Imobiliz;
  private long NR_Volumes;
  private double VL_Desconto;
  private double VL_Produto;
  private double VL_Valor_Tabela;
  private String DT_Stamp;
  private String Usuario_Stamp;
  private String DM_Stamp;
  private String NM_Produto;
  private String CD_Produto;
  private long NR_Nota_Fiscal;
  private String NM_Serie;
  private String OID_Produto;
  private String OID_Unidade_Produto;
  private String NM_Unidade;
  private double VL_IPI;

  private double vl_quantidade;

  private double VL_Liquido;
  public void setCD_Imobiliz (String CD_Imobiliz) {
    this.CD_Imobiliz = CD_Imobiliz;
  }

  public void setCD_Referencia (String CD_Referencia) {
    this.CD_Referencia = CD_Referencia;
  }

  public void setDM_Stamp (String DM_Stamp) {
    this.DM_Stamp = DM_Stamp;
  }

  public void setDT_Stamp (String DT_Stamp) {
    this.DT_Stamp = DT_Stamp;
  }

  public void setNR_Item_Nota_Fiscal (long NR_Item_Nota_Fiscal) {
    this.NR_Item_Nota_Fiscal = NR_Item_Nota_Fiscal;
  }

  public void setNR_Volumes (long NR_Volumes) {
    this.NR_Volumes = NR_Volumes;
  }

  public void setOID_Item_Nota_Fiscal (String OID_Item_Nota_Fiscal) {
    this.OID_Item_Nota_Fiscal = OID_Item_Nota_Fiscal;
  }

  public void setVL_Desconto (double VL_Desconto) {
    this.VL_Desconto = VL_Desconto;
  }

  public void setVL_Produto (double VL_Produto) {
    this.VL_Produto = VL_Produto;
  }

  public void setVL_Valor_Tabela (double VL_Valor_Tabela) {
    this.VL_Valor_Tabela = VL_Valor_Tabela;
  }

  public String getCD_Imobiliz () {
    return CD_Imobiliz;
  }

  public String getCD_Referencia () {
    return CD_Referencia;
  }

  public String getDM_Stamp () {
    return DM_Stamp;
  }

  public String getDT_Stamp () {
    return DT_Stamp;
  }

  public long getNR_Item_Nota_Fiscal () {
    return NR_Item_Nota_Fiscal;
  }

  public long getNR_Volumes () {
    return NR_Volumes;
  }

  public String getOID_Item_Nota_Fiscal () {
    return OID_Item_Nota_Fiscal;
  }

  public double getVL_Desconto () {
    return VL_Desconto;
  }

  public double getVL_Produto () {
    return VL_Produto;
  }

  public double getVL_Valor_Tabela () {
    return VL_Valor_Tabela;
  }

  public void setOID_Nota_Fiscal (String OID_Nota_Fiscal) {
    this.OID_Nota_Fiscal = OID_Nota_Fiscal;
  }

  public String getOID_Nota_Fiscal () {
    return OID_Nota_Fiscal;
  }

  public void setNM_Produto (String NM_Produto) {
    this.NM_Produto = NM_Produto;
  }

  public String getNM_Produto () {
    return NM_Produto;
  }

  public void setCD_Produto (String CD_Produto) {
    this.CD_Produto = CD_Produto;
  }

  public String getCD_Produto () {
    return CD_Produto;
  }

  public void setNR_Nota_Fiscal (long NR_Nota_Fiscal) {
    this.NR_Nota_Fiscal = NR_Nota_Fiscal;
  }

  public long getNR_Nota_Fiscal () {
    return NR_Nota_Fiscal;
  }

  public String getUsuario_Stamp () {
    return Usuario_Stamp;
  }

  public void setUsuario_Stamp (String Usuario_Stamp) {
    this.Usuario_Stamp = Usuario_Stamp;
  }

  public void setNM_Serie (String NM_Serie) {
    this.NM_Serie = NM_Serie;
  }

  public String getNM_Serie () {
    return NM_Serie;
  }

  public void setOID_Produto (String OID_Produto) {
    this.OID_Produto = OID_Produto;
  }

  public String getOID_Produto () {
    return OID_Produto;
  }

  public void setOID_Unidade_Produto (String OID_Unidade_Produto) {
    this.OID_Unidade_Produto = OID_Unidade_Produto;
  }

  public String getOID_Unidade_Produto () {
    return OID_Unidade_Produto;
  }

  public void setNM_Unidade (String NM_Unidade) {
    this.NM_Unidade = NM_Unidade;
  }

  public String getNM_Unidade () {
    return NM_Unidade;
  }

  public void setVL_IPI (double VL_IPI) {
    this.VL_IPI = VL_IPI;
  }

  public double getVL_IPI () {
    return VL_IPI;
  }

  public void setVL_Liquido (double VL_Liquido) {
    this.VL_Liquido = VL_Liquido;
  }

  public double getVL_Liquido () {
    return VL_Liquido;
  }

  public double getVl_quantidade () {
    return vl_quantidade;
  }

  public void setVl_quantidade (double vl_quantidade) {
    this.vl_quantidade = vl_quantidade;
  }

public long getOid_Ordem_Servico() {
	return oid_Ordem_Servico;
}

public void setOid_Ordem_Servico(long oid_Ordem_Servico) {
	this.oid_Ordem_Servico = oid_Ordem_Servico;
}

public String getNR_Ordem_Servico() {
	return NR_Ordem_Servico;
}

public void setNR_Ordem_Servico(String ordem_Servico) {
	NR_Ordem_Servico = ordem_Servico;
}

public String getNR_Placa() {
	return NR_Placa;
}

public void setNR_Placa(String placa) {
	NR_Placa = placa;
}

public long getOid_Solicitacao_compra() {
	return oid_Solicitacao_compra;
}

public void setOid_Solicitacao_compra(long oid_Solicitacao_compra) {
	this.oid_Solicitacao_compra = oid_Solicitacao_compra;
}

public String getDM_Tipo_Produto() {
	return DM_Tipo_Produto;
}

public void setDM_Tipo_Produto(String tipo_Produto) {
	DM_Tipo_Produto = tipo_Produto;
}
}