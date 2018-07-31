package com.master.ed;

public class Nao_ConformidadeED extends MasterED{
	private String DT_Emissao;
	private long Oid_nao_conformidade;
	private String Oid_cliente_fornecedor;
	private String Oid_identificador_problema;
	private String NM_descricao_problema;
	private String NM_disposicao_problema;
	private String NM_acao_contencao;
	private String NM_causa;
	private String NM_acao_corretiva;
	private String NM_acao_preventiva;
	private String NM_verificacao_implemantacao;
	private String NM_verificacao_eficacia;
	private String DT_Prazo;
	private String NM_cliente_fornecedor;
	private String NM_identificador_problema;
	


	public Nao_ConformidadeED(){
	}	
	
	
	/**
	 * @return Returns the dT_Emissao.
	 */
	public String getDT_Emissao() {
		return DT_Emissao;
	}
	/**
	 * @param emissao The dT_Emissao to set.
	 */
	public void setDT_Emissao(String emissao) {
		DT_Emissao = emissao;
	}
	/**
	 * @return Returns the dT_Prazo.
	 */
	public String getDT_Prazo() {
		return DT_Prazo;
	}
	/**
	 * @param prazo The dT_Prazo to set.
	 */
	public void setDT_Prazo(String prazo) {
		DT_Prazo = prazo;
	}
	/**
	 * @return Returns the nM_acao_contencao.
	 */
	public String getNM_acao_contencao() {
		return NM_acao_contencao;
	}
	/**
	 * @param nm_acao_contencao The nM_acao_contencao to set.
	 */
	public void setNM_acao_contencao(String nm_acao_contencao) {
		NM_acao_contencao = nm_acao_contencao;
	}
	/**
	 * @return Returns the nM_acao_corretiva.
	 */
	public String getNM_acao_corretiva() {
		return NM_acao_corretiva;
	}
	/**
	 * @param nm_acao_corretiva The nM_acao_corretiva to set.
	 */
	public void setNM_acao_corretiva(String nm_acao_corretiva) {
		NM_acao_corretiva = nm_acao_corretiva;
	}
	/**
	 * @return Returns the nM_acao_preventiva.
	 */
	public String getNM_acao_preventiva() {
		return NM_acao_preventiva;
	}
	/**
	 * @param nm_acao_preventiva The nM_acao_preventiva to set.
	 */
	public void setNM_acao_preventiva(String nm_acao_preventiva) {
		NM_acao_preventiva = nm_acao_preventiva;
	}
	/**
	 * @return Returns the nM_causa.
	 */
	public String getNM_causa() {
		return NM_causa;
	}
	/**
	 * @param nm_causa The nM_causa to set.
	 */
	public void setNM_causa(String nm_causa) {
		NM_causa = nm_causa;
	}
	/**
	 * @return Returns the nM_descricao_problema.
	 */
	public String getNM_descricao_problema() {
		return NM_descricao_problema;
	}
	/**
	 * @param nm_descricao_problema The nM_descricao_problema to set.
	 */
	public void setNM_descricao_problema(String nm_descricao_problema) {
		NM_descricao_problema = nm_descricao_problema;
	}
	/**
	 * @return Returns the nM_disposicao_problema.
	 */
	public String getNM_disposicao_problema() {
		return NM_disposicao_problema;
	}
	/**
	 * @param nm_disposicao_problema The nM_disposicao_problema to set.
	 */
	public void setNM_disposicao_problema(String nm_disposicao_problema) {
		NM_disposicao_problema = nm_disposicao_problema;
	}
	/**
	 * @return Returns the nM_verificacao_eficacia.
	 */
	public String getNM_verificacao_eficacia() {
		return NM_verificacao_eficacia;
	}
	/**
	 * @param nm_verificacao_eficacia The nM_verificacao_eficacia to set.
	 */
	public void setNM_verificacao_eficacia(String nm_verificacao_eficacia) {
		NM_verificacao_eficacia = nm_verificacao_eficacia;
	}
	/**
	 * @return Returns the nM_verificacao_implemantacao.
	 */
	public String getNM_verificacao_implemantacao() {
		return NM_verificacao_implemantacao;
	}
	/**
	 * @param nm_verificacao_implemantacao The nM_verificacao_implemantacao to set.
	 */
	public void setNM_verificacao_implemantacao(
			String nm_verificacao_implemantacao) {
		NM_verificacao_implemantacao = nm_verificacao_implemantacao;
	}
	public long getOid_nao_conformidade() {
		return Oid_nao_conformidade;
	}
	/**
	 * @param oid_nao_conformidade The oid_nao_conformidade to set.
	 */
	public void setOid_nao_conformidade(long oid_nao_conformidade) {
		Oid_nao_conformidade = oid_nao_conformidade;
		
		
	}
	/**
	 * @return Returns the oid_cliente_fornecedor.
	 */
	public String getOid_cliente_fornecedor() {
		return Oid_cliente_fornecedor;
	}
	/**
	 * @param oid_cliente_fornecedor The oid_cliente_fornecedor to set.
	 */
	public void setOid_cliente_fornecedor(String oid_cliente_fornecedor) {
		this.Oid_cliente_fornecedor = oid_cliente_fornecedor;
	}
	/**
	 * @return Returns the oid_identificador_problema.
	 */
	public String getOid_identificador_problema() {
		return Oid_identificador_problema;
	}
	/**
	 * @param oid_identificador_problema The oid_identificador_problema to set.
	 */
	public void setOid_identificador_problema(String oid_identificador_problema) {
		this.Oid_identificador_problema = oid_identificador_problema;
	}
	/**
	 * @return Returns the nM_cliente_fornecedor.
	 */
	public String getNM_cliente_fornecedor() {
		return NM_cliente_fornecedor;
	}
	/**
	 * @param nm_cliente_fornecedor The nM_cliente_fornecedor to set.
	 */
	public void setNM_cliente_fornecedor(String nm_cliente_fornecedor) {
		NM_cliente_fornecedor = nm_cliente_fornecedor;
	}
	/**
	 * @return Returns the nM_identificador_problema.
	 */
	public String getNM_identificador_problema() {
		return NM_identificador_problema;
	}
	/**
	 * @param nm_identificador_problema The nM_identificador_problema to set.
	 */
	public void setNM_identificador_problema(String nm_identificador_problema) {
		NM_identificador_problema = nm_identificador_problema;
	}
}
