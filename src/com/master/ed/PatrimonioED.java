package com.master.ed;

import com.master.util.Valores;

public class PatrimonioED extends RelatorioED {

	private long oid_patrimonio;
	//private long oid_unidade; ja existem no RelatorioED
	//private long oid_centro_custo;
	private String cd_patrimonio;
	private String tx_descricao;
	private String dt_compra;
	private double vl_original;
	private double vl_depreciacao;
	private double nr_quantidade_meses;
	private double vl_depreciado;
	private String dt_baixa;
	private String dt_venda_sucateamento;
	private double vl_venda_sucateamento;

	private String dt_compra_final;
	private String dt_baixa_final;
	private String dt_venda_sucateamento_final;

	//campos de valor
	private String tx_original;
	private String tx_depreciacao;
	private String tx_quantidade_meses;
	private String tx_depreciado;
	private String tx_venda_sucateamento;

	private String cd_centro_custo;
	private String nm_centro_custo;

	public String getCd_patrimonio() {
		return cd_patrimonio;
	}
	public void setCd_patrimonio(String cd_patrimonio) {
		this.cd_patrimonio = cd_patrimonio;
	}
	public String getDt_baixa() {
		return dt_baixa;
	}
	public void setDt_baixa(String dt_baixa) {
		this.dt_baixa = dt_baixa;
	}
	public String getDt_compra() {
		return dt_compra;
	}
	public void setDt_compra(String dt_compra) {
		this.dt_compra = dt_compra;
	}
	public String getDt_venda_sucateamento() {
		return dt_venda_sucateamento;
	}
	public void setDt_venda_sucateamento(String dt_venda_sucateamento) {
		this.dt_venda_sucateamento = dt_venda_sucateamento;
	}
	public double getNr_quantidade_meses() {
		return nr_quantidade_meses;
	}
	public void setNr_quantidade_meses(double nr_quantidade_meses) {
		this.nr_quantidade_meses = nr_quantidade_meses;
		this.setTx_quantidade_meses(Valores.converteDoubleToString(nr_quantidade_meses));
	}

	public long getOid_patrimonio() {
		return oid_patrimonio;
	}
	public void setOid_patrimonio(long oid_patrimonio) {
		this.oid_patrimonio = oid_patrimonio;
	}

	public String getTx_descricao() {
		return tx_descricao;
	}
	public void setTx_descricao(String tx_descricao) {
		this.tx_descricao = tx_descricao;
	}
	public double getVl_depreciacao() {
		return vl_depreciacao;
	}
	public void setVl_depreciacao(double vl_depreciacao) {
		this.vl_depreciacao = vl_depreciacao;
		this.setTx_depreciacao(Valores.converteDoubleToString(vl_depreciacao));
	}
	public double getVl_depreciado() {
		return vl_depreciado;
	}
	public void setVl_depreciado(double vl_depreciado) {
		this.vl_depreciado = vl_depreciado;
		this.setTx_depreciado(Valores.converteDoubleToString(vl_depreciado));
	}
	public double getVl_original() {
		return vl_original;
	}
	public void setVl_original(double vl_original) {
		this.vl_original = vl_original;
		this.setTx_original(Valores.converteDoubleToString(vl_original));
	}
	public double getVl_venda_sucateamento() {
		return vl_venda_sucateamento;
	}
	public void setVl_venda_sucateamento(double vl_venda_sucateamento) {
		this.vl_venda_sucateamento = vl_venda_sucateamento;
		this.setTx_venda_sucateamento(Valores.converteDoubleToString(vl_venda_sucateamento));
	}
	public String getDt_baixa_final() {
		return dt_baixa_final;
	}
	public void setDt_baixa_final(String dt_baixa_final) {
		this.dt_baixa_final = dt_baixa_final;
	}
	public String getDt_compra_final() {
		return dt_compra_final;
	}
	public void setDt_compra_final(String dt_compra_final) {
		this.dt_compra_final = dt_compra_final;
	}
	public String getDt_venda_sucateamento_final() {
		return dt_venda_sucateamento_final;
	}
	public void setDt_venda_sucateamento_final(String dt_venda_sucateamento_final) {
		this.dt_venda_sucateamento_final = dt_venda_sucateamento_final;
	}
	public String getTx_depreciacao() {
		return tx_depreciacao;
	}
	public void setTx_depreciacao(String tx_depreciacao) {
		this.tx_depreciacao = tx_depreciacao;
		this.vl_depreciacao=(Valores.converteStringToDouble(tx_depreciacao));
	}
	public String getTx_depreciado() {
		return tx_depreciado;
	}
	public void setTx_depreciado(String tx_depreciado) {
		this.tx_depreciado = tx_depreciado;
		this.vl_depreciado=(Valores.converteStringToDouble(tx_depreciado));
	}
	public String getTx_original() {
		return tx_original;
	}
	public void setTx_original(String tx_original) {
		this.tx_original = tx_original;
		this.vl_original=(Valores.converteStringToDouble(tx_original));
	}
	public String getTx_quantidade_meses() {
		return tx_quantidade_meses;
	}
	public void setTx_quantidade_meses(String tx_quantidade_meses) {
		this.tx_quantidade_meses = tx_quantidade_meses;
		this.nr_quantidade_meses=(Valores.converteStringToDouble(tx_quantidade_meses));
	}
	public String getTx_venda_sucateamento() {
		return tx_venda_sucateamento;
	}
	public void setTx_venda_sucateamento(String tx_venda_sucateamento) {
		this.tx_venda_sucateamento = tx_venda_sucateamento;
		this.vl_venda_sucateamento=(Valores.converteStringToDouble(tx_venda_sucateamento));
	}
	public String getCd_centro_custo() {
		return cd_centro_custo;
	}
	public void setCd_centro_custo(String cd_centro_custo) {
		this.cd_centro_custo = cd_centro_custo;
	}
	public String getNm_centro_custo() {
		return nm_centro_custo;
	}
	public void setNm_centro_custo(String nm_centro_custo) {
		this.nm_centro_custo = nm_centro_custo;
	}

}
