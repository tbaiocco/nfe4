package com.master.ed;

public class AcertoRelED
{
	private String nr_acerto;
	private String nr_frota;
	private String nr_frota_inicio;
	private String nr_frota_fim;
	private String tx_observacao;
	private String cd_servico;
	private String nm_servico;
	private String vl_movimento;
	private String dt_final;
	private String dt_inicial;
	private double vl_servico;
	private String dataRel;
	private String siglaRel;
	private String dm_faturado_pago;
	private long km_inicial;
	private long km_final;
	private java.util.Collection acertosDetalhes;
	private String vl_faturado;
	private double vl_faturado_total;
	private String vl_pago;
	private double vl_pago_total;
	private String vl_total_faturado;
	private String vl_total_pago;
	private String vl_total;
	private double vl_total_double;
	private String vl_total_geral;
	private String vl_saldo;
	private String vl_adiantamento;
	private String dt_saida;
	private String dt_chegada;
	private String litros;
    private String km_total;
	private String km_litros;
	private String km_reais;
	private double km_reais_double;
	private String km_reais_total;
	
	private String nm_motorista;
	private String nm_filial;
	private String nr_ordem_frete;
	private String dt_emissao;
	private String pl_veiculo;
	private double vl_ordem_frete;

	public AcertoRelED(){
	}
  public String getNr_acerto() {
    return nr_acerto;
  }
  public void setNr_acerto(String nr_acerto) {
    this.nr_acerto = nr_acerto;
  }
  public java.util.Collection getAcertosDetalhes() {
    return acertosDetalhes;
  }
  public void setAcertosDetalhes(java.util.Collection acertosDetalhes) {
    this.acertosDetalhes = acertosDetalhes;
  }
  public String getTx_observacao() {
    return tx_observacao;
  }
  public void setTx_observacao(String tx_observacao) {
    this.tx_observacao = tx_observacao;
  }
  public String getCd_servico() {
    return cd_servico;
  }
  public String getNm_servico() {
    return nm_servico;
  }
  public void setCd_servico(String cd_servico) {
    this.cd_servico = cd_servico;
  }
  public void setNm_servico(String nm_servico) {
    this.nm_servico = nm_servico;
  }
  public double getVl_servico() {
    return vl_servico;
  }
  public void setVl_servico(double vl_servico) {
    this.vl_servico = vl_servico;
  }
  public String getDataRel() {
    return dataRel;
  }
  public String getSiglaRel() {
    return siglaRel;
  }
  public void setDataRel(String dataRel) {
    this.dataRel = dataRel;
  }
  public void setSiglaRel(String siglaRel) {
    this.siglaRel = siglaRel;
  }
  public long getKm_final() {
    return km_final;
  }
  public long getKm_inicial() {
    return km_inicial;
  }
  public void setKm_final(long km_final) {
    this.km_final = km_final;
  }
  public void setKm_inicial(long km_inicial) {
    this.km_inicial = km_inicial;
  }
  public String getVl_movimento() {
    return vl_movimento;
  }
  public void setVl_movimento(String vl_movimento) {
    this.vl_movimento = vl_movimento;
  }
  public String getDm_faturado_pago() {
    return dm_faturado_pago;
  }
  public void setDm_faturado_pago(String dm_faturado_pago) {
    this.dm_faturado_pago = dm_faturado_pago;
  }
  public void setVl_faturado(String vl_faturado) {
    this.vl_faturado = vl_faturado;
  }
  public String getVl_faturado() {
    return vl_faturado;
  }
  public void setVl_pago(String vl_pago) {
    this.vl_pago = vl_pago;
  }
  public String getVl_pago() {
    return vl_pago;
  }
  public String getVl_total_faturado() {
    return vl_total_faturado;
  }
  public String getVl_total_pago() {
    return vl_total_pago;
  }
  public void setVl_total_faturado(String vl_total_faturado) {
    this.vl_total_faturado = vl_total_faturado;
  }
  public void setVl_total_pago(String vl_total_pago) {
    this.vl_total_pago = vl_total_pago;
  }
  public String getVl_total() {
    return vl_total;
  }
  public void setVl_total(String vl_total) {
    this.vl_total = vl_total;
  }
  public String getVl_total_geral() {
    return vl_total_geral;
  }
  public void setVl_total_geral(String vl_total_geral) {
    this.vl_total_geral = vl_total_geral;
  }
  public String getDt_chegada() {
    return dt_chegada;
  }
  public String getDt_saida() {
    return dt_saida;
  }
  public void setDt_chegada(String dt_chegada) {
    this.dt_chegada = dt_chegada;
  }
  public void setDt_saida(String dt_saida) {
    this.dt_saida = dt_saida;
  }
  public void setKm_total(String km_total) {
    this.km_total = km_total;
  }
  public String getKm_total() {
    return km_total;
  }
  public String getLitros() {
    return litros;
  }
  public void setLitros(String litros) {
    this.litros = litros;
  }
  public String getKm_litros() {
    return km_litros;
  }
  public void setKm_litros(String km_litros) {
    this.km_litros = km_litros;
  }
  public String getKm_reais() {
    return km_reais;
  }
  public void setKm_reais(String km_reais) {
    this.km_reais = km_reais;
  }
  public String getKm_reais_total() {
    return km_reais_total;
  }
  public void setKm_reais_total(String km_reais_total) {
    this.km_reais_total = km_reais_total;
  }
  public String getVl_saldo() {
    return vl_saldo;
  }
  public void setVl_saldo(String vl_saldo) {
    this.vl_saldo = vl_saldo;
  }
  public String getVl_adiantamento() {
    return vl_adiantamento;
  }
  public void setVl_adiantamento(String vl_adiantamento) {
    this.vl_adiantamento = vl_adiantamento;
  }
  public String getNr_frota() {
    return nr_frota;
  }
  public void setNr_frota(String nr_frota) {
    this.nr_frota = nr_frota;
  }
  public String getDt_final() {
    return dt_final;
  }
  public String getDt_inicial() {
    return dt_inicial;
  }
  public void setDt_final(String dt_final) {
    this.dt_final = dt_final;
  }
  public void setDt_inicial(String dt_inicial) {
    this.dt_inicial = dt_inicial;
  }
  public String getNr_frota_fim() {
    return nr_frota_fim;
  }
  public String getNr_frota_inicio() {
    return nr_frota_inicio;
  }
  public void setNr_frota_fim(String nr_frota_fim) {
    this.nr_frota_fim = nr_frota_fim;
  }
  public void setNr_frota_inicio(String nr_frota_inicio) {
    this.nr_frota_inicio = nr_frota_inicio;
  }
  public double getVl_faturado_total() {
    return vl_faturado_total;
  }
  public void setVl_faturado_total(double vl_faturado_total) {
    this.vl_faturado_total = vl_faturado_total;
  }
  public double getVl_pago_total() {
    return vl_pago_total;
  }
  public void setVl_pago_total(double vl_pago_total) {
    this.vl_pago_total = vl_pago_total;
  }
  public double getKm_reais_double() {
    return km_reais_double;
  }
  public void setKm_reais_double(double km_reais_double) {
    this.km_reais_double = km_reais_double;
  }
  public double getVl_total_double() {
    return vl_total_double;
  }
  public void setVl_total_double(double vl_total_double) {
    this.vl_total_double = vl_total_double;
  }
  public String getDt_emissao() {
	return dt_emissao;
  }
  public void setDt_emissao(String dt_emissao) {
	this.dt_emissao = dt_emissao;
  }
  public String getNm_filial() {
 	return nm_filial;
  }
  public void setNm_filial(String nm_filial) {
	this.nm_filial = nm_filial;
  }
  public String getNm_motorista() {
	return nm_motorista;
  }
  public void setNm_motorista(String nm_motorista) {
	this.nm_motorista = nm_motorista;
  }
  public String getNr_ordem_frete() {
	return nr_ordem_frete;
  }
  public void setNr_ordem_frete(String nr_ordem_frete) {
	this.nr_ordem_frete = nr_ordem_frete;
  }
  public String getPl_veiculo() {
	return pl_veiculo;
  }
  public void setPl_veiculo(String pl_veiculo) {
	this.pl_veiculo = pl_veiculo;
  }
  public double getVl_ordem_frete() {
	return vl_ordem_frete;
  }
  public void setVl_ordem_frete(double vl_ordem_frete) {
	this.vl_ordem_frete = vl_ordem_frete;
  }

}
