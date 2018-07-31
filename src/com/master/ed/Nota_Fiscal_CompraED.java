package com.master.ed;

public class Nota_Fiscal_CompraED extends RelatorioBaseED{

	private String oid_nota_fiscal;
  private long nr_nota_fiscal;
  private long oid_pedido_compra;
  private String dt_emissao;
  private long nr_volumes;
  private String nm_serie;
  private String dt_entrada;
  private String hr_entrada;
  private String oid_pessoa;
  private String oid_pessoa_destinatario;
  private long oid_natureza_operacao;
  private long oid_natureza_operacao_servico;
  private long oid_natureza_operacao_outros;
  private long oid_modelo_nota_fiscal;
  private String dm_tipo_nota_fiscal;
  private String dt_stamp;
  private String usuario_stamp;
  private String dm_stamp;
  private String dm_observacao;
  private double vl_descontos;
  private double vl_icms;
  private double vl_icms_substituicao;
  private double vl_inss;
  private double vl_ipi;
  private double vl_irrf;
  private double vl_isqn;
  private double vl_liquido_nota_fiscal;
  private double vl_nota_fiscal;
  private double vl_total_despesas;
  private double vl_total_frete;
  private double vl_total_seguro;
  private String dm_forma_pagamento;
  private long nr_parcelas;
  private String dm_finalizado;
  private long OID_Unidade_Fiscal;
  private long OID_Unidade_Contabil;
  private String NM_Unidade_Fiscal;
  private String NM_Unidade_Contabil;
  private String CD_Unidade_Fiscal;
  private String CD_Unidade_Contabil;
  private String DM_Contabiliza;
  private String DM_Contabilizacao;
  private String DM_Fiscal;
  private String DM_Parcelamento;
  private String DM_Estoque;
  private String DM_Financeiro;
  private double VL_Parcela_Calculo;
  private double VL_Servico;
  private double VL_Outros;
  private double VL_Pis;
  private double VL_Cofins;
  private double VL_Csll;

  private long OID_Unidade_Pagadora;
  private String NM_Unidade_Pagadora;
  private String CD_Unidade_Pagadora;

  private String oid_Pedido_Compra_Nota_Fiscal;
  private String DM_Romaneio;

  private String NM_Pessoa;
  private String nm_conta_debito;
  private String nm_centro_custo;

  private String cd_natureza_operacao;
  private String cd_natureza_operacao_servico;

  private long oid_conta;
  private long oid_conta_servico;
  private long oid_conta_outros;
  private long oid_centro_custo;

  private String dt_entrada_final;
  private String dt_emissao_final;

  private java.util.Collection NotaFiscalEntradaDetalhes;
  private String dataRel;
  private String siglaRel;

  private String NM_Extensao;

  private String NM_Chave_NFE;
  private String nm_arquivo_imp;

  public void setDm_stamp(String dm_stamp) {
    this.dm_stamp = dm_stamp;
  }
  public void setDm_tipo_nota_fiscal(String dm_tipo_nota_fiscal) {
    this.dm_tipo_nota_fiscal = dm_tipo_nota_fiscal;
  }
  public void setDt_emissao(String dt_emissao) {
    this.dt_emissao = dt_emissao;
  }
  public void setDt_entrada(String dt_entrada) {
    this.dt_entrada = dt_entrada;
  }
  public void setHr_entrada(String hr_entrada) {
    this.hr_entrada = hr_entrada;
  }
  public void setNr_nota_fiscal(long nr_nota_fiscal) {
    this.nr_nota_fiscal = nr_nota_fiscal;
  }
  public void setNm_serie(String nm_serie) {
    this.nm_serie = nm_serie;
  }
  public void setNr_volumes(long nr_volumes) {
    this.nr_volumes = nr_volumes;
  }
  public void setOid_modelo_nota_fiscal(long oid_modelo_nota_fiscal) {
    this.oid_modelo_nota_fiscal = oid_modelo_nota_fiscal;
  }
  public void setOid_natureza_operacao(long oid_natureza_operacao) {
    this.oid_natureza_operacao = oid_natureza_operacao;
  }
  public void setOid_nota_fiscal(String oid_nota_fiscal) {
    this.oid_nota_fiscal = oid_nota_fiscal;
  }
  public void setOid_pessoa(String oid_pessoa) {
    this.oid_pessoa = oid_pessoa;
  }
  public void setOid_pessoa_destinatario(String oid_pessoa_destinatario) {
    this.oid_pessoa_destinatario = oid_pessoa_destinatario;
  }
  public void setUsuario_stamp(String usuario_stamp) {
    this.usuario_stamp = usuario_stamp;
  }
  public void setVl_icms(double vl_icms) {
    this.vl_icms = vl_icms;
  }
  public void setVl_inss(double vl_inss) {
    this.vl_inss = vl_inss;
  }
  public void setVl_ipi(double vl_ipi) {
    this.vl_ipi = vl_ipi;
  }
  public void setVl_irrf(double vl_irrf) {
    this.vl_irrf = vl_irrf;
  }
  public void setVl_isqn(double vl_isqn) {
    this.vl_isqn = vl_isqn;
  }
  public void setVl_liquido_nota_fiscal(double vl_liquido_nota_fiscal) {
    this.vl_liquido_nota_fiscal = vl_liquido_nota_fiscal;
  }
  public void setVl_nota_fiscal(double vl_nota_fiscal) {
    this.vl_nota_fiscal = vl_nota_fiscal;
  }
  public void setVl_total_despesas(double vl_total_despesas) {
    this.vl_total_despesas = vl_total_despesas;
  }
  public void setVl_total_frete(double vl_total_frete) {
    this.vl_total_frete = vl_total_frete;
  }
  public String getDm_stamp() {
    return dm_stamp;
  }
  public String getDm_tipo_nota_fiscal() {
    return dm_tipo_nota_fiscal;
  }
  public String getDt_emissao() {
    return dt_emissao;
  }
  public String getDt_entrada() {
    return dt_entrada;
  }
  public String getHr_entrada() {
    return hr_entrada;
  }
  public String getNm_serie() {
    return nm_serie;
  }
  public long getNr_nota_fiscal() {
    return nr_nota_fiscal;
  }
  public long getNr_volumes() {
    return nr_volumes;
  }
  public long getOid_modelo_nota_fiscal() {
    return oid_modelo_nota_fiscal;
  }
  public long getOid_natureza_operacao() {
    return oid_natureza_operacao;
  }
  public String getOid_nota_fiscal() {
    return oid_nota_fiscal;
  }
  public String getOid_pessoa() {
    return oid_pessoa;
  }
  public String getOid_pessoa_destinatario() {
    return oid_pessoa_destinatario;
  }
  public String getUsuario_stamp() {
    return usuario_stamp;
  }
  public double getVl_icms() {
    return vl_icms;
  }
  public double getVl_ipi() {
    return vl_ipi;
  }
  public double getVl_inss() {
    return vl_inss;
  }
  public double getVl_irrf() {
    return vl_irrf;
  }
  public double getVl_isqn() {
    return vl_isqn;
  }
  public double getVl_liquido_nota_fiscal() {
    return vl_liquido_nota_fiscal;
  }
  public double getVl_nota_fiscal() {
    return vl_nota_fiscal;
  }
  public double getVl_total_despesas() {
    return vl_total_despesas;
  }
  public double getVl_total_frete() {
    return vl_total_frete;
  }
  public void setDm_observacao(String dm_observacao) {
    this.dm_observacao = dm_observacao;
  }
  public String getDm_observacao() {
    return dm_observacao;
  }
  public double getVl_descontos() {
    return vl_descontos;
  }
  public void setVl_descontos(double vl_descontos) {
    this.vl_descontos = vl_descontos;
  }
  public void setVl_total_seguro(double vl_total_seguro) {
    this.vl_total_seguro = vl_total_seguro;
  }
  public double getVl_total_seguro() {
    return vl_total_seguro;
  }
  public void setDm_forma_pagamento(String dm_forma_pagamento) {
    this.dm_forma_pagamento = dm_forma_pagamento;
  }
  public String getDm_forma_pagamento() {
    return dm_forma_pagamento;
  }
  public void setNr_parcelas(long nr_parcelas) {
    this.nr_parcelas = nr_parcelas;
  }
  public long getNr_parcelas() {
    return nr_parcelas;
  }
  public void setDm_finalizado(String dm_finalizado) {
    this.dm_finalizado = dm_finalizado;
  }
  public String getDm_finalizado() {
    return dm_finalizado;
  }
  public void setOID_Unidade_Fiscal(long OID_Unidade_Fiscal) {
    this.OID_Unidade_Fiscal = OID_Unidade_Fiscal;
  }
  public long getOID_Unidade_Fiscal() {
    return OID_Unidade_Fiscal;
  }
  public void setOID_Unidade_Contabil(long OID_Unidade_Contabil) {
    this.OID_Unidade_Contabil = OID_Unidade_Contabil;
  }
  public long getOID_Unidade_Contabil() {
    return OID_Unidade_Contabil;
  }
  public void setNM_Unidade_Fiscal(String NM_Unidade_Fiscal) {
    this.NM_Unidade_Fiscal = NM_Unidade_Fiscal;
  }
  public String getNM_Unidade_Fiscal() {
    return NM_Unidade_Fiscal;
  }
  public void setNM_Unidade_Contabil(String NM_Unidade_Contabil) {
    this.NM_Unidade_Contabil = NM_Unidade_Contabil;
  }
  public String getNM_Unidade_Contabil() {
    return NM_Unidade_Contabil;
  }
  public void setCD_Unidade_Fiscal(String CD_Unidade_Fiscal) {
    this.CD_Unidade_Fiscal = CD_Unidade_Fiscal;
  }
  public String getCD_Unidade_Fiscal() {
    return CD_Unidade_Fiscal;
  }
  public void setCD_Unidade_Contabil(String CD_Unidade_Contabil) {
    this.CD_Unidade_Contabil = CD_Unidade_Contabil;
  }
  public String getCD_Unidade_Contabil() {
    return CD_Unidade_Contabil;
  }
  public void setDM_Contabiliza(String DM_Contabiliza) {
    this.DM_Contabiliza = DM_Contabiliza;
  }
  public String getDM_Contabiliza() {
    return DM_Contabiliza;
  }
  public void setDM_Estoque(String DM_Estoque) {
    this.DM_Estoque = DM_Estoque;
  }
  public String getDM_Estoque() {
    return DM_Estoque;
  }
  public void setDM_Financeiro(String DM_Financeiro) {
    this.DM_Financeiro = DM_Financeiro;
  }
  public String getDM_Financeiro() {
    return DM_Financeiro;
  }
  public void setVL_Parcela_Calculo(double VL_Parcela_Calculo) {
    this.VL_Parcela_Calculo = VL_Parcela_Calculo;
  }
  public double getVL_Parcela_Calculo() {
    return VL_Parcela_Calculo;
  }
  public void setVL_Servico(double VL_Servico) {
    this.VL_Servico = VL_Servico;
  }
  public double getVL_Servico() {
    return VL_Servico;
  }
  public double getVL_Cofins() {
    return VL_Cofins;
  }
  public void setVL_Cofins(double VL_Cofins) {
    this.VL_Cofins = VL_Cofins;
  }
  public double getVL_Csll() {
    return VL_Csll;
  }
  public void setVL_Csll(double VL_Csll) {
    this.VL_Csll = VL_Csll;
  }
  public double getVL_Pis() {
    return VL_Pis;
  }
  public void setVL_Pis(double VL_Pis) {
    this.VL_Pis = VL_Pis;
  }
/**
 * @return Returns the cD_Unidade_Pagadora.
 */
public String getCD_Unidade_Pagadora() {
	return CD_Unidade_Pagadora;
}
/**
 * @param unidade_Pagadora The cD_Unidade_Pagadora to set.
 */
public void setCD_Unidade_Pagadora(String unidade_Pagadora) {
	CD_Unidade_Pagadora = unidade_Pagadora;
}
/**
 * @return Returns the nM_Unidade_Pagadora.
 */
public String getNM_Unidade_Pagadora() {
	return NM_Unidade_Pagadora;
}
/**
 * @param unidade_Pagadora The nM_Unidade_Pagadora to set.
 */
public void setNM_Unidade_Pagadora(String unidade_Pagadora) {
	NM_Unidade_Pagadora = unidade_Pagadora;
}
/**
 * @return Returns the oID_Unidade_Pagadora.
 */
public long getOID_Unidade_Pagadora() {
	return OID_Unidade_Pagadora;
}
/**
 * @param unidade_Pagadora The oID_Unidade_Pagadora to set.
 */
public void setOID_Unidade_Pagadora(long unidade_Pagadora) {
	OID_Unidade_Pagadora = unidade_Pagadora;
}
public String getOid_Pedido_Compra_Nota_Fiscal() {
    return oid_Pedido_Compra_Nota_Fiscal;
}
public void setOid_Pedido_Compra_Nota_Fiscal(
        String oid_Pedido_Compra_Nota_Fiscal) {
    this.oid_Pedido_Compra_Nota_Fiscal = oid_Pedido_Compra_Nota_Fiscal;
}
public String getDM_Romaneio() {
    return DM_Romaneio;
}
public void setDM_Romaneio(String romaneio) {
    DM_Romaneio = romaneio;
}
public long getOid_conta() {
    return oid_conta;
}
public void setOid_conta(long oid_conta) {
    this.oid_conta = oid_conta;
}
public long getOid_centro_custo() {
    return oid_centro_custo;
}
public void setOid_centro_custo(long oid_centro_custo) {
    this.oid_centro_custo = oid_centro_custo;
}
public String getNm_centro_custo() {
    return nm_centro_custo;
}
public void setNm_centro_custo(String nm_centro_custo) {
    this.nm_centro_custo = nm_centro_custo;
}
public String getNm_conta_debito() {
    return nm_conta_debito;
}
public void setNm_conta_debito(String nm_conta_debito) {
    this.nm_conta_debito = nm_conta_debito;
}
public String getNM_Pessoa() {
    return NM_Pessoa;
}
public void setNM_Pessoa(String pessoa) {
    NM_Pessoa = pessoa;
}
public String getDataRel() {
    return dataRel;
}
public void setDataRel(String dataRel) {
    this.dataRel = dataRel;
}
public java.util.Collection getNotaFiscalEntradaDetalhes() {
    return NotaFiscalEntradaDetalhes;
}
public void setNotaFiscalEntradaDetalhes(
        java.util.Collection notaFiscalEntradaDetalhes) {
    NotaFiscalEntradaDetalhes = notaFiscalEntradaDetalhes;
}
public String getSiglaRel() {
    return siglaRel;
}
public void setSiglaRel(String siglaRel) {
    this.siglaRel = siglaRel;
}
public String getDt_entrada_final() {
    return dt_entrada_final;
}
public void setDt_entrada_final(String dt_entrada_final) {
    this.dt_entrada_final = dt_entrada_final;
}
public String getDt_emissao_final() {
    return dt_emissao_final;
}
public void setDt_emissao_final(String dt_emissao_final) {
    this.dt_emissao_final = dt_emissao_final;
}
public String getDt_stamp() {
	return dt_stamp;
}
public void setDt_stamp(String dt_stamp) {
	this.dt_stamp = dt_stamp;
}
public long getOid_natureza_operacao_servico() {
	return oid_natureza_operacao_servico;
}
public void setOid_natureza_operacao_servico(long oid_natureza_operacao_servico) {
	this.oid_natureza_operacao_servico = oid_natureza_operacao_servico;
}
public long getOid_conta_servico() {
	return oid_conta_servico;
}
public void setOid_conta_servico(long oid_conta_servico) {
	this.oid_conta_servico = oid_conta_servico;
}
public String getNM_Extensao() {
	return NM_Extensao;
}
public void setNM_Extensao(String extensao) {
	NM_Extensao = extensao;
}
public long getOid_conta_outros() {
	return oid_conta_outros;
}
public void setOid_conta_outros(long oid_conta_outros) {
	this.oid_conta_outros = oid_conta_outros;
}
public long getOid_natureza_operacao_outros() {
	return oid_natureza_operacao_outros;
}
public void setOid_natureza_operacao_outros(long oid_natureza_operacao_outros) {
	this.oid_natureza_operacao_outros = oid_natureza_operacao_outros;
}
public double getVL_Outros() {
	return VL_Outros;
}
public void setVL_Outros(double outros) {
	VL_Outros = outros;
}
public String getDM_Fiscal() {
	return DM_Fiscal;
}
public void setDM_Fiscal(String fiscal) {
	DM_Fiscal = fiscal;
}
public String getDM_Parcelamento() {
	return DM_Parcelamento;
}
public void setDM_Parcelamento(String parcelamento) {
	DM_Parcelamento = parcelamento;
}
public String getDM_Contabilizacao() {
	return DM_Contabilizacao;
}
public void setDM_Contabilizacao(String contabilizacao) {
	DM_Contabilizacao = contabilizacao;
}
public String getCd_natureza_operacao() {
	return cd_natureza_operacao;
}
public void setCd_natureza_operacao(String cd_natureza_operacao) {
	this.cd_natureza_operacao = cd_natureza_operacao;
}
public String getCd_natureza_operacao_servico() {
	return cd_natureza_operacao_servico;
}
public void setCd_natureza_operacao_servico(String cd_natureza_operacao_servico) {
	this.cd_natureza_operacao_servico = cd_natureza_operacao_servico;
}
public long getOid_pedido_compra() {
	return oid_pedido_compra;
}
public void setOid_pedido_compra(long oid_pedido_compra) {
	this.oid_pedido_compra = oid_pedido_compra;
}
public double getVl_icms_substituicao() {
	return vl_icms_substituicao;
}
public void setVl_icms_substituicao(double vl_icms_substituicao) {
	this.vl_icms_substituicao = vl_icms_substituicao;
}
public String getNM_Chave_NFE() {
	return NM_Chave_NFE;
}
public void setNM_Chave_NFE(String chave_NFE) {
	NM_Chave_NFE = chave_NFE;
}
public String getNm_arquivo_imp() {
	return nm_arquivo_imp;
}
public void setNm_arquivo_imp(String nm_arquivo_imp) {
	this.nm_arquivo_imp = nm_arquivo_imp;
}
}
