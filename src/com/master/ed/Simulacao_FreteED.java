package com.master.ed;

public class Simulacao_FreteED extends MasterED {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private long oid_Simulacao;
	private String oid_Pessoa_Cliente;
	private String oid_Pessoa_Transportador; // pessoa das tabelas de frete que servirão de base para o cálculo
	private long nr_Simulacao;
	private int oid_Origem;
	private int oid_Destino;
	private double vl_Mercadoria;
	private double vl_Peso;
	private double vl_Frete_Peso;
	private double vl_Frete_Valor;
	private double vl_Taxas;
	private double vl_Pedagio;
	private double vl_Total_Frete;
	private long nr_Quantidade_Ctrc;

	public long getNr_Quantidade_Ctrc() {
		return nr_Quantidade_Ctrc;
	}
	public void setNr_Quantidade_Ctrc(long nr_Quantidade_Ctrc) {
		this.nr_Quantidade_Ctrc = nr_Quantidade_Ctrc;
	}
	public int getOid_Destino() {
		return oid_Destino;
	}
	public void setOid_Destino(int oid_Destino) {
		this.oid_Destino = oid_Destino;
	}
	public int getOid_Origem() {
		return oid_Origem;
	}
	public void setOid_Origem(int oid_Origem) {
		this.oid_Origem = oid_Origem;
	}
	public String getOid_Pessoa_Cliente() {
		return oid_Pessoa_Cliente;
	}
	public void setOid_Pessoa_Cliente(String oid_Pessoa_Cliente) {
		this.oid_Pessoa_Cliente = oid_Pessoa_Cliente;
	}
	public String getOid_Pessoa_Transportador() {
		return oid_Pessoa_Transportador;
	}
	public void setOid_Pessoa_Transportador(String oid_Pessoa_Transportador) {
		this.oid_Pessoa_Transportador = oid_Pessoa_Transportador;
	}
	public long getOid_Simulacao() {
		return oid_Simulacao;
	}
	public void setOid_Simulacao(long oid_Simulacao) {
		this.oid_Simulacao = oid_Simulacao;
	}
	public double getVl_Frete_Peso() {
		return vl_Frete_Peso;
	}
	public void setVl_Frete_Peso(double vl_Frete_Peso) {
		this.vl_Frete_Peso = vl_Frete_Peso;
	}
	public double getVl_Frete_Valor() {
		return vl_Frete_Valor;
	}
	public void setVl_Frete_Valor(double vl_Frete_Valor) {
		this.vl_Frete_Valor = vl_Frete_Valor;
	}
	public double getVl_Mercadoria() {
		return vl_Mercadoria;
	}
	public void setVl_Mercadoria(double vl_Mercadoria) {
		this.vl_Mercadoria = vl_Mercadoria;
	}
	public double getVl_Pedagio() {
		return vl_Pedagio;
	}
	public void setVl_Pedagio(double vl_Pedagio) {
		this.vl_Pedagio = vl_Pedagio;
	}
	public double getVl_Peso() {
		return vl_Peso;
	}
	public void setVl_Peso(double vl_Peso) {
		this.vl_Peso = vl_Peso;
	}
	public double getVl_Taxas() {
		return vl_Taxas;
	}
	public void setVl_Taxas(double vl_Taxas) {
		this.vl_Taxas = vl_Taxas;
	}
	public double getVl_Total_Frete() {
		return vl_Total_Frete;
	}
	public void setVl_Total_Frete(double vl_Total_Frete) {
		this.vl_Total_Frete = vl_Total_Frete;
	}
	public long getNr_Simulacao() {
		return nr_Simulacao;
	}
	public void setNr_Simulacao(long nr_Simulacao) {
		this.nr_Simulacao = nr_Simulacao;
	}

}
