package com.master.ed;

import com.master.util.JavaUtil;

public class DerrubadaED extends RelatorioED {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private long oid_derrubada;
	private String dt_derrubada;
	private String hr_derrubada;
	private String oid_veiculo;
	private String dm_situacao;
	private String dt_finalizado;
	//private long oid_unidade;

	//Consulta
	private String dt_derrubada_final;
	private String dt_finalizado_final;
	private String nm_situacao;

	//Descritivos de FKs
	private String nr_placa;
	private String cd_unidade;
	private String nm_fantasia;
	private String oid_pessoa;
	private String nm_razao_social;
	private String nr_cnpj_cpf;

	//Descritivo de Situacao
	public String getDescSituacao(){
		if(JavaUtil.doValida(dm_situacao)){
			if("A".equalsIgnoreCase(dm_situacao)){
				nm_situacao = "Aberta";
			}
			else if("F".equalsIgnoreCase(dm_situacao)){
				nm_situacao = "Finalizada";
			}
			else if("E".equalsIgnoreCase(dm_situacao)){
				nm_situacao = "Encerrada";
			}
			else if("C".equalsIgnoreCase(dm_situacao)){
				nm_situacao = "Cancelada";
			}
			else {
				nm_situacao = "N/A";
			}
		} else {
			nm_situacao = "N/A";
		}
		return nm_situacao;
	}

	public String getDm_situacao() {
		return dm_situacao;
	}

	public void setDm_situacao(String dm_situacao) {
		this.dm_situacao = dm_situacao;
	}

	public String getDt_derrubada() {
		return dt_derrubada;
	}

	public void setDt_derrubada(String dt_derrubada) {
		this.dt_derrubada = dt_derrubada;
	}

	public String getDt_derrubada_final() {
		return dt_derrubada_final;
	}

	public void setDt_derrubada_final(String dt_derrubada_final) {
		this.dt_derrubada_final = dt_derrubada_final;
	}

	public String getDt_finalizado() {
		return dt_finalizado;
	}

	public void setDt_finalizado(String dt_finalizado) {
		this.dt_finalizado = dt_finalizado;
	}

	public String getHr_derrubada() {
		return hr_derrubada;
	}

	public void setHr_derrubada(String hr_derrubada) {
		this.hr_derrubada = hr_derrubada;
	}

	public String getNr_placa() {
		return nr_placa;
	}

	public void setNr_placa(String nr_placa) {
		this.nr_placa = nr_placa;
	}

	public long getOid_derrubada() {
		return oid_derrubada;
	}

	public void setOid_derrubada(long oid_derrubada) {
		this.oid_derrubada = oid_derrubada;
	}

	public String getOid_veiculo() {
		return oid_veiculo;
	}

	public void setOid_veiculo(String oid_veiculo) {
		this.oid_veiculo = oid_veiculo;
	}

	public String getDt_finalizado_final() {
		return dt_finalizado_final;
	}

	public void setDt_finalizado_final(String dt_finalizado_final) {
		this.dt_finalizado_final = dt_finalizado_final;
	}

//	public long getOid_unidade() {
//		return oid_unidade;
//	}
//
//	public void setOid_unidade(long oid_unidade) {
//		this.oid_unidade = oid_unidade;
//	}

	public String getNm_razao_social() {
		return nm_razao_social;
	}

	public void setNm_razao_social(String nm_razao_social) {
		this.nm_razao_social = nm_razao_social;
	}

	public String getNr_cnpj_cpf() {
		return nr_cnpj_cpf;
	}

	public void setNr_cnpj_cpf(String nr_cnpj_cpf) {
		this.nr_cnpj_cpf = nr_cnpj_cpf;
	}

	public String getOid_pessoa() {
		return oid_pessoa;
	}

	public void setOid_pessoa(String oid_pessoa) {
		this.oid_pessoa = oid_pessoa;
	}

	public String getCd_unidade() {
		return cd_unidade;
	}

	public void setCd_unidade(String cd_unidade) {
		this.cd_unidade = cd_unidade;
	}

	public String getNm_fantasia() {
		return nm_fantasia;
	}

	public void setNm_fantasia(String nm_fantasia) {
		this.nm_fantasia = nm_fantasia;
	}

	public String getNm_situacao() {
		if(JavaUtil.doValida(dm_situacao)){
			if("A".equalsIgnoreCase(dm_situacao)){
				nm_situacao = "Aberta";
			}
			else if("F".equalsIgnoreCase(dm_situacao)){
				nm_situacao = "Finalizada";
			}
			else if("E".equalsIgnoreCase(dm_situacao)){
				nm_situacao = "Encerrada";
			}
			else if("C".equalsIgnoreCase(dm_situacao)){
				nm_situacao = "Cancelada";
			}
			else {
				nm_situacao = "N/A";
			}
		} else {
			nm_situacao = "N/A";
		}
		return nm_situacao;
	}

	public void setNm_situacao(String nm_situacao) {
		this.nm_situacao = nm_situacao;
	}

}
