package com.master.ed;

/**
 * <p>Title: Produto_Cliente_ContagemED</p>
 * <p>Description: POJO para produtos da contagem ciclica, relativa a tabela produtos_clientes_contagens</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: SPMti</p>
 * @author Teofilo Poletto Baiocco - nuovonet
 * @version 0.1.0
 */

public class Produto_Cliente_ContagemED extends Contagem_CiclicaED implements java.io.Serializable{

	private long oid_contagem_ciclica;
	private long oid_produto_cliente_contagem;
	private String oid_estoque_cliente;
	private long oid_operador;
	private String dt_contagem;
	private double nr_qt_estoque;
	private double nr_qt_contada;
	private String dt_contagem_final; // para consulta

	private String nr_cnpj_cpf;
	private String nm_razao_social;
	private String cd_tipo_estoque;
	private String nm_tipo_estoque;
	private String cd_deposito;
	private String nm_deposito;
	private String cd_operador;
	private String nm_operador;
	private String cd_estoque_cliente;
	private String nm_estoque_cliente;

	//campos txt para valores na tela
	private String nm_qt_estoque;
	private String nm_qt_contada;

	private String cd_localizacao;
	private String dm_situacao;
	private String dm_contado;
	private String nr_acuracidade;

	private int nr_divergentes;

	public String getDt_contagem() {
		return dt_contagem;
	}
	public void setDt_contagem(String dt_contagem) {
		this.dt_contagem = dt_contagem;
	}
	public double getNr_qt_contada() {
		return nr_qt_contada;
	}
	public void setNr_qt_contada(double nr_qt_contada) {
		this.nr_qt_contada = nr_qt_contada;
	}
	public double getNr_qt_estoque() {
		return nr_qt_estoque;
	}
	public void setNr_qt_estoque(double nr_qt_estoque) {
		this.nr_qt_estoque = nr_qt_estoque;
	}
	public long getOid_contagem_ciclica() {
		return oid_contagem_ciclica;
	}
	public void setOid_contagem_ciclica(long oid_contagem_ciclica) {
		this.oid_contagem_ciclica = oid_contagem_ciclica;
	}
	public String getOid_estoque_cliente() {
		return oid_estoque_cliente;
	}
	public void setOid_estoque_cliente(String oid_estoque_cliente) {
		this.oid_estoque_cliente = oid_estoque_cliente;
	}
	public long getOid_operador() {
		return oid_operador;
	}
	public void setOid_operador(long oid_operador) {
		this.oid_operador = oid_operador;
	}
	public long getOid_produto_cliente_contagem() {
		return oid_produto_cliente_contagem;
	}
	public void setOid_produto_cliente_contagem(long oid_produto_cliente_contagem) {
		this.oid_produto_cliente_contagem = oid_produto_cliente_contagem;
	}
	public String getDt_contagem_final() {
		return dt_contagem_final;
	}
	public void setDt_contagem_final(String dt_contagem_final) {
		this.dt_contagem_final = dt_contagem_final;
	}
	public String getCd_deposito() {
		return cd_deposito;
	}
	public void setCd_deposito(String cd_deposito) {
		this.cd_deposito = cd_deposito;
	}
	public String getCd_operador() {
		return cd_operador;
	}
	public void setCd_operador(String cd_operador) {
		this.cd_operador = cd_operador;
	}
	public String getCd_tipo_estoque() {
		return cd_tipo_estoque;
	}
	public void setCd_tipo_estoque(String cd_tipo_estoque) {
		this.cd_tipo_estoque = cd_tipo_estoque;
	}
	public String getNm_deposito() {
		return nm_deposito;
	}
	public void setNm_deposito(String nm_deposito) {
		this.nm_deposito = nm_deposito;
	}
	public String getNm_operador() {
		return nm_operador;
	}
	public void setNm_operador(String nm_operador) {
		this.nm_operador = nm_operador;
	}
	public String getNm_razao_social() {
		return nm_razao_social;
	}
	public void setNm_razao_social(String nm_razao_social) {
		this.nm_razao_social = nm_razao_social;
	}
	public String getNm_tipo_estoque() {
		return nm_tipo_estoque;
	}
	public void setNm_tipo_estoque(String nm_tipo_estoque) {
		this.nm_tipo_estoque = nm_tipo_estoque;
	}
	public String getNr_cnpj_cpf() {
		return nr_cnpj_cpf;
	}
	public void setNr_cnpj_cpf(String nr_cnpj_cpf) {
		this.nr_cnpj_cpf = nr_cnpj_cpf;
	}
	public String getCd_estoque_cliente() {
		return cd_estoque_cliente;
	}
	public void setCd_estoque_cliente(String cd_estoque_cliente) {
		this.cd_estoque_cliente = cd_estoque_cliente;
	}
	public String getNm_estoque_cliente() {
		return nm_estoque_cliente;
	}
	public void setNm_estoque_cliente(String nm_estoque_cliente) {
		this.nm_estoque_cliente = nm_estoque_cliente;
	}
	public String getNm_qt_contada() {
		return nm_qt_contada;
	}
	public void setNm_qt_contada(String nm_qt_contada) {
		this.nm_qt_contada = nm_qt_contada;
	}
	public String getNm_qt_estoque() {
		return nm_qt_estoque;
	}
	public void setNm_qt_estoque(String nm_qt_estoque) {
		this.nm_qt_estoque = nm_qt_estoque;
	}
	public String getCd_localizacao() {
		return cd_localizacao;
	}
	public void setCd_localizacao(String cd_localizacao) {
		this.cd_localizacao = cd_localizacao;
	}
	public String getDm_situacao() {
		return dm_situacao;
	}
	public void setDm_situacao(String dm_situacao) {
		this.dm_situacao = dm_situacao;
	}
	public String getDm_contado() {
		return dm_contado;
	}
	public void setDm_contado(String dm_contado) {
		this.dm_contado = dm_contado;
	}
	public String getNr_acuracidade() {
		return nr_acuracidade;
	}
	public void setNr_acuracidade(String nr_acuracidade) {
		this.nr_acuracidade = nr_acuracidade;
	}
	public int getNr_divergentes() {
		return nr_divergentes;
	}
	public void setNr_divergentes(int nr_divergentes) {
		this.nr_divergentes = nr_divergentes;
	}

}