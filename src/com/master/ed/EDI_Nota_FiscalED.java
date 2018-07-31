package com.master.ed;

import java.util.ArrayList;
import java.util.Collection;

public class EDI_Nota_FiscalED extends MasterED{

	private String cd_empresa;
	private String cd_unidade;
	private String nr_nota_fiscal;
	private String nm_serie;
	private String dt_emissao;
	private String dt_recebimento;
	private String dt_base;
	private String dt_saida;
	private double vl_produtos;
	private double vl_nota_fiscal;

	//TODO @Revisar: registro 5
	private String nr_titulo;
	private double vl_titulo;
	private String dt_vencimento;
	private String dt_aceite;

	private String nr_ordem_compra;

	private Collection itens = new ArrayList();

	public String getCd_empresa() {
		return cd_empresa;
	}

	public void setCd_empresa(String cd_empresa) {
		this.cd_empresa = cd_empresa;
	}

	public String getCd_unidade() {
		return cd_unidade;
	}

	public void setCd_unidade(String cd_unidade) {
		this.cd_unidade = cd_unidade;
	}

	public String getDt_aceite() {
		return dt_aceite;
	}

	public void setDt_aceite(String dt_aceite) {
		this.dt_aceite = dt_aceite;
	}

	public String getDt_base() {
		return dt_base;
	}

	public void setDt_base(String dt_base) {
		this.dt_base = dt_base;
	}

	public String getDt_emissao() {
		return dt_emissao;
	}

	public void setDt_emissao(String dt_emissao) {
		this.dt_emissao = dt_emissao;
	}

	public String getDt_recebimento() {
		return dt_recebimento;
	}

	public void setDt_recebimento(String dt_recebimento) {
		this.dt_recebimento = dt_recebimento;
	}

	public String getDt_saida() {
		return dt_saida;
	}

	public void setDt_saida(String dt_saida) {
		this.dt_saida = dt_saida;
	}

	public String getDt_vencimento() {
		return dt_vencimento;
	}

	public void setDt_vencimento(String dt_vencimento) {
		this.dt_vencimento = dt_vencimento;
	}

	public Collection getItens() {
		return itens;
	}

	public void setItens(Collection itens) {
		this.itens = itens;
	}

	public String getNm_serie() {
		return nm_serie;
	}

	public void setNm_serie(String nm_serie) {
		this.nm_serie = nm_serie;
	}

	public String getNr_nota_fiscal() {
		return nr_nota_fiscal;
	}

	public void setNr_nota_fiscal(String nr_nota_fiscal) {
		this.nr_nota_fiscal = nr_nota_fiscal;
	}

	public String getNr_titulo() {
		return nr_titulo;
	}

	public void setNr_titulo(String nr_titulo) {
		this.nr_titulo = nr_titulo;
	}

	public double getVl_nota_fiscal() {
		return vl_nota_fiscal;
	}

	public void setVl_nota_fiscal(double vl_nota_fiscal) {
		this.vl_nota_fiscal = vl_nota_fiscal;
	}

	public double getVl_produtos() {
		return vl_produtos;
	}

	public void setVl_produtos(double vl_produtos) {
		this.vl_produtos = vl_produtos;
	}

	public double getVl_titulo() {
		return vl_titulo;
	}

	public void setVl_titulo(double vl_titulo) {
		this.vl_titulo = vl_titulo;
	}

	public String getNr_ordem_compra() {
		return nr_ordem_compra;
	}

	public void setNr_ordem_compra(String nr_ordem_compra) {
		this.nr_ordem_compra = nr_ordem_compra;
	}



}
