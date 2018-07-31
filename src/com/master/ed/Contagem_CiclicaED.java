package com.master.ed;

/**
 * <p>Title: Contagem_CiclicaED</p>
 * <p>Description: POJO para contagem ciclica, relativa a tabela contagens_ciclicas</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: SPMti</p>
 * @author Teofilo Poletto Baiocco - nuovonet
 * @version 0.1.0
 */

public class Contagem_CiclicaED extends RelatorioBaseED implements java.io.Serializable{

	private long oid_contagem_ciclica;
	private String oid_pessoa;
	private long oid_tipo_estoque;
	private long oid_armazem;
	private long oid_operador;
	private String dt_inicio;
	private String dt_fim;
	private String dm_situacao;
	private long nr_qt_itens;
	private long nr_qt_itens_contados;
	private String tx_observacao;
	private long nr_dias_contagem_ciclica;

	private String dt_inicio_final; //para consulta >= dt_inicio / <= dt_inicio_final
	private String dt_fim_final; //para consulta >= dt_fim / <= dt_fim_final

	//campos das FKs para telas
	private String nr_cnpj_cpf;
	private String nm_razao_social;
	private String cd_tipo_estoque;
	private String nm_tipo_estoque;
	private String cd_deposito;
	private String nm_deposito;
	private String cd_operador;
	private String nm_operador;

	private double nr_acuracidade_contagem;

	public String getDescDm_situacao() {
		return dm_situacao.equals("E") ? "ENCERRADA" : "EM ABERTO";
	}
	public String getDm_situacao() {
		return dm_situacao;
	}
	public void setDm_situacao(String dm_situacao) {
		this.dm_situacao = dm_situacao;
	}
	public String getDt_fim() {
		return dt_fim;
	}
	public void setDt_fim(String dt_fim) {
		this.dt_fim = dt_fim;
	}
	public String getDt_inicio() {
		return dt_inicio;
	}
	public void setDt_inicio(String dt_inicio) {
		this.dt_inicio = dt_inicio;
	}
	public long getNr_dias_contagem_ciclica() {
		return nr_dias_contagem_ciclica;
	}
	public void setNr_dias_contagem_ciclica(long nr_dias_contagem_ciclica) {
		this.nr_dias_contagem_ciclica = nr_dias_contagem_ciclica;
	}
	public long getNr_qt_itens() {
		return nr_qt_itens;
	}
	public void setNr_qt_itens(long nr_qt_itens) {
		this.nr_qt_itens = nr_qt_itens;
	}
	public long getNr_qt_itens_contados() {
		return nr_qt_itens_contados;
	}
	public void setNr_qt_itens_contados(long nr_qt_itens_contados) {
		this.nr_qt_itens_contados = nr_qt_itens_contados;
	}
	public long getOid_armazem() {
		return oid_armazem;
	}
	public void setOid_armazem(long oid_armazem) {
		this.oid_armazem = oid_armazem;
	}
	public long getOid_contagem_ciclica() {
		return oid_contagem_ciclica;
	}
	public void setOid_contagem_ciclica(long oid_contagem_ciclica) {
		this.oid_contagem_ciclica = oid_contagem_ciclica;
	}
	public long getOid_operador() {
		return oid_operador;
	}
	public void setOid_operador(long oid_operador) {
		this.oid_operador = oid_operador;
	}
	public String getOid_pessoa() {
		return oid_pessoa;
	}
	public void setOid_pessoa(String oid_pessoa) {
		this.oid_pessoa = oid_pessoa;
	}
	public long getOid_tipo_estoque() {
		return oid_tipo_estoque;
	}
	public void setOid_tipo_estoque(long oid_tipo_estoque) {
		this.oid_tipo_estoque = oid_tipo_estoque;
	}
	public String getTx_observacao() {
		return tx_observacao;
	}
	public void setTx_observacao(String tx_observacao) {
		this.tx_observacao = tx_observacao;
	}
	public String getDt_fim_final() {
		return dt_fim_final;
	}
	public void setDt_fim_final(String dt_fim_final) {
		this.dt_fim_final = dt_fim_final;
	}
	public String getDt_inicio_final() {
		return dt_inicio_final;
	}
	public void setDt_inicio_final(String dt_inicio_final) {
		this.dt_inicio_final = dt_inicio_final;
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
	public double getNr_acuracidade_contagem() {
		return nr_acuracidade_contagem;
	}
	public void setNr_acuracidade_contagem(double nr_acuracidade_contagem) {
		this.nr_acuracidade_contagem = nr_acuracidade_contagem;
	}

}