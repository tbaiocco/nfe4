/*
 * Created on 04/07/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.master.ed;

/**
 * @author Jonas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class ChamadoED extends MasterED{

	private long OID_Chamado;
	private String OID_Cliente;
	private String OID_Responsavel;
	private String DT_Entrada;
	private String DT_Entrada_Final;
	private String HR_Entrada;
	private String DM_Prioridade;
	private String DM_Classificacao;
	private String NM_Solicitante;
	private String TX_Descricao;
	private String NR_Previsao;
	private String DT_Inicio;
	private String DT_Inicio_Final;
	private String HR_Inicio;
	private String DT_Conclusao;
	private String HR_Conclusao;
	private String DM_Situacao;
	private String usuario_stamp;
	
	
	
	/**
	 * @return Returns the dM_Classificacao.
	 */
	public String getDM_Classificacao() {
		return DM_Classificacao;
	}
	/**
	 * @param classificacao The dM_Classificacao to set.
	 */
	public void setDM_Classificacao(String classificacao) {
		DM_Classificacao = classificacao;
	}
	/**
	 * @return Returns the dM_Prioridade.
	 */
	public String getDM_Prioridade() {
		return DM_Prioridade;
	}
	/**
	 * @param prioridade The dM_Prioridade to set.
	 */
	public void setDM_Prioridade(String prioridade) {
		DM_Prioridade = prioridade;
	}
	/**
	 * @return Returns the dM_Situacao.
	 */
	public String getDM_Situacao() {
		return DM_Situacao;
	}
	/**
	 * @param situacao The dM_Situacao to set.
	 */
	public void setDM_Situacao(String situacao) {
		DM_Situacao = situacao;
	}
	/**
	 * @return Returns the dT_Conclusao.
	 */
	public String getDT_Conclusao() {
		return DT_Conclusao;
	}
	/**
	 * @param conclusao The dT_Conclusao to set.
	 */
	public void setDT_Conclusao(String conclusao) {
		DT_Conclusao = conclusao;
	}
	/**
	 * @return Returns the dT_Entrada.
	 */
	public String getDT_Entrada() {
		return DT_Entrada;
	}
	/**
	 * @param entrada The dT_Entrada to set.
	 */
	public void setDT_Entrada(String entrada) {
		DT_Entrada = entrada;
	}
	/**
	 * @return Returns the dT_Inicio.
	 */
	public String getDT_Inicio() {
		return DT_Inicio;
	}
	/**
	 * @param inicio The dT_Inicio to set.
	 */
	public void setDT_Inicio(String inicio) {
		DT_Inicio = inicio;
	}
	/**
	 * @return Returns the hR_Conclusao.
	 */
	public String getHR_Conclusao() {
		return HR_Conclusao;
	}
	/**
	 * @param conclusao The hR_Conclusao to set.
	 */
	public void setHR_Conclusao(String conclusao) {
		HR_Conclusao = conclusao;
	}
	/**
	 * @return Returns the hR_Entrada.
	 */
	public String getHR_Entrada() {
		return HR_Entrada;
	}
	/**
	 * @param entrada The hR_Entrada to set.
	 */
	public void setHR_Entrada(String entrada) {
		HR_Entrada = entrada;
	}
	/**
	 * @return Returns the hR_Inicio.
	 */
	public String getHR_Inicio() {
		return HR_Inicio;
	}
	/**
	 * @param inicio The hR_Inicio to set.
	 */
	public void setHR_Inicio(String inicio) {
		HR_Inicio = inicio;
	}
	/**
	 * @return Returns the nM_Solicitante.
	 */
	public String getNM_Solicitante() {
		return NM_Solicitante;
	}
	/**
	 * @param solicitante The nM_Solicitante to set.
	 */
	public void setNM_Solicitante(String solicitante) {
		NM_Solicitante = solicitante;
	}
	/**
	 * @return Returns the nR_Previsao.
	 */
	public String getNR_Previsao() {
		return NR_Previsao;
	}
	/**
	 * @param previsao The nR_Previsao to set.
	 */
	public void setNR_Previsao(String previsao) {
		NR_Previsao = previsao;
	}
	/**
	 * @return Returns the oID_Chamado.
	 */
	public long getOID_Chamado() {
		return OID_Chamado;
	}
	/**
	 * @param chamado The oID_Chamado to set.
	 */
	public void setOID_Chamado(long chamado) {
		OID_Chamado = chamado;
	}
	/**
	 * @return Returns the oID_Cliente.
	 */
	public String getOID_Cliente() {
		return OID_Cliente;
	}
	/**
	 * @param cliente The oID_Cliente to set.
	 */
	public void setOID_Cliente(String cliente) {
		OID_Cliente = cliente;
	}
	/**
	 * @return Returns the oID_Responsavel.
	 */
	public String getOID_Responsavel() {
		return OID_Responsavel;
	}
	/**
	 * @param responsavel The oID_Responsavel to set.
	 */
	public void setOID_Responsavel(String responsavel) {
		OID_Responsavel = responsavel;
	}
	/**
	 * @return Returns the tX_Descricao.
	 */
	public String getTX_Descricao() {
		return TX_Descricao;
	}
	/**
	 * @param descricao The tX_Descricao to set.
	 */
	public void setTX_Descricao(String descricao) {
		TX_Descricao = descricao;
	}
	/**
	 * @return Returns the usuario_stamp.
	 */
	public String getUsuario_stamp() {
		return usuario_stamp;
	}
	/**
	 * @param usuario_stamp The usuario_stamp to set.
	 */
	public void setUsuario_stamp(String usuario_stamp) {
		this.usuario_stamp = usuario_stamp;
	}
	/**
	 * @return Returns the dT_Entrada_Final.
	 */
	public String getDT_Entrada_Final() {
		return DT_Entrada_Final;
	}
	/**
	 * @param entrada_Final The dT_Entrada_Final to set.
	 */
	public void setDT_Entrada_Final(String entrada_Final) {
		DT_Entrada_Final = entrada_Final;
	}
	/**
	 * @return Returns the dT_Inicio_Final.
	 */
	public String getDT_Inicio_Final() {
		return DT_Inicio_Final;
	}
	/**
	 * @param inicio_Final The dT_Inicio_Final to set.
	 */
	public void setDT_Inicio_Final(String inicio_Final) {
		DT_Inicio_Final = inicio_Final;
	}
}
