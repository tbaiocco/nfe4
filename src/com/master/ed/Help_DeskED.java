
package com.master.ed;

import javax.servlet.http.HttpServletRequest;

import com.master.rn.DreRN;
import com.master.rn.Help_DeskRN;
import com.master.util.Excecoes;


/**
 * @author Jeanine e Vinícius
	Date: 21/05/2008
 */

public class Help_DeskED extends RelatorioBaseED{

	  private static final long serialVersionUID = -7821905059747768563L;
	  
	  private long oid_Help_Desk;
	  private long nr_Help_Desk;
	  private long oid_Empresa;
	  private long oid_Usuario;
	  private String vl_Investimento;
	  private String vl_Aceite;
	  private String vl_Custo;
	  private String nm_Descricao_Servico1;
	  private String nm_Descricao_Servico2;
	  private String nm_Descricao_Servico3;
	  private String nm_Descricao_Servico4;
	  private String nm_Descricao_Servico5;
	  private String nm_Visto_Eletronico;
	  private String nm_Responsavel_Aceite;	  
	  private String dm_Classificacao;
	  private String dm_Adicional;
	  private String dm_Situacao_Ordem_Servico;
	  private String dm_Situacao_Orcamento;
	  private String dm_Valor_Hora;
	  private String dm_Prioridade;
	  private String dm_Origem;
	  private String dt_Orcamento;
	  private String dt_Necessidade_Entrega;
	  private String dt_Solicitacao;
	  private String dt_Aceite;
	  private String dt_Conclusao;
	  private String dt_Inicio;
	  private String dt_Homologacao;
	  private long hr_Orcadas;
	  private String hr_Solicitacao;
	  private String hr_Orcamento;
	  private String hr_Aceite;
	  private String hr_Conclusao;
	  private String hr_Inicio;
	  private String hr_Homologacao;
	  private String tx_Desenvolvimento1;
	  private String tx_Desenvolvimento2;
	  private String tx_Homologacao1;
	  private String tx_Homologacao2;
	  
	  private long oid_Tecnico;
	  private long oid_Tecnico_Orcamento;
	  private long oid_Tecnico_Homologacao;
	  private long oid_Tecnico_Desenvolvedor;
	  private String cd_Tecnico;
	  private String cd_Tecnico_Orcamento;
	  private String cd_Tecnico_Desenvolvedor;
	  private String cd_Tecnico_Homologacao;
	  private String nm_Usuario;
	  private String nm_Empresa;
	  private String nm_Tecnico;
	  private String nm_Tecnico_Orcamento;
	  private String nm_Tecnico_Desenvolvedor;
	  private String nm_Tecnico_Homologacao;
	  private String nm_Solicitante;
	  
	  private String dt_Solicitacao_Inicial;
	  private String dt_Solicitacao_Final;
	  private String dm_Ordenacao;
	  private HttpServletRequest request;
	  
	  //======  Fucoes   =======\\
	  private boolean codigoExistente;
	  private boolean incluidoOK;
	
	  
	public String getDm_Ordenacao() {
		return dm_Ordenacao;
	}
	public void setDm_Ordenacao(String dm_Ordenacao) {
		this.dm_Ordenacao = dm_Ordenacao;
	}
	public String getDm_Adicional() {
		return dm_Adicional;
	}
	public void setDm_Adicional(String dm_Adicional) {
		this.dm_Adicional = dm_Adicional;
	}
	public String getDm_Classificacao() {
		return dm_Classificacao;
	}
	public void setDm_Classificacao(String dm_Classificacao) {
		this.dm_Classificacao = dm_Classificacao;
	}
	public String getDm_Prioridade() {
		return dm_Prioridade;
	}
	public void setDm_Prioridade(String dm_Prioridade) {
		this.dm_Prioridade = dm_Prioridade;
	}
	public String getDm_Situacao_Orcamento() {
		return dm_Situacao_Orcamento;
	}
	public void setDm_Situacao_Orcamento(String dm_Situacao_Orcamento) {
		this.dm_Situacao_Orcamento = dm_Situacao_Orcamento;
	}
	public String getDm_Situacao_Ordem_Servico() {
		return dm_Situacao_Ordem_Servico;
	}
	public void setDm_Situacao_Ordem_Servico(String dm_Situacao_Ordem_Servico) {
		this.dm_Situacao_Ordem_Servico = dm_Situacao_Ordem_Servico;
	}
	public String getDm_Valor_Hora() {
		return dm_Valor_Hora;
	}
	public void setDm_Valor_Hora(String dm_Valor_Hora) {
		this.dm_Valor_Hora = dm_Valor_Hora;
	}
	public String getDt_Aceite() {
		return dt_Aceite;
	}
	public void setDt_Aceite(String dt_Aceite) {
		this.dt_Aceite = dt_Aceite;
	}
	public String getDt_Conclusao() {
		return dt_Conclusao;
	}
	public void setDt_Conclusao(String dt_Conclusao) {
		this.dt_Conclusao = dt_Conclusao;
	}
	public String getDt_Inicio() {
		return dt_Inicio;
	}
	public void setDt_Inicio(String dt_inicio) {
		this.dt_Inicio = dt_inicio;
	}
	public String getDt_Homologacao() {
		return dt_Homologacao;
	}
	public void setDt_Homologacao(String dt_Homologacao) {
		this.dt_Homologacao = dt_Homologacao;
	}
	public String getDt_Necessidade_Entrega() {
		return dt_Necessidade_Entrega;
	}
	public void setDt_Necessidade_Entrega(String dt_Necessidade_Entrega) {
		this.dt_Necessidade_Entrega = dt_Necessidade_Entrega;
	}
	public String getDt_Orcamento() {
		return dt_Orcamento;
	}
	public void setDt_Orcamento(String dt_Orcamento) {
		this.dt_Orcamento = dt_Orcamento;
	}
	public String getDt_Solicitacao() {
		return dt_Solicitacao;
	}
	public void setDt_Solicitacao(String dt_Solicitacao) {
		this.dt_Solicitacao = dt_Solicitacao;
	}
	public String getHr_Aceite() {
		return hr_Aceite;
	}
	public void setHr_Aceite(String hr_Aceite) {
		this.hr_Aceite = hr_Aceite;
	}
	public String getHr_Conclusao() {
		return hr_Conclusao;
	}
	public void setHr_Conclusao(String hr_Conclusao) {
		this.hr_Conclusao = hr_Conclusao;
	}
	public String getHr_Inicio() {
		return hr_Inicio;
	}
	public void setHr_Inicio(String hr_Inicio) {
		this.hr_Inicio = hr_Inicio;
	}
	public String getHr_Homologacao() {
		return hr_Homologacao;
	}
	public void setHr_Homologacao(String hr_Homologacao) {
		this.hr_Homologacao = hr_Homologacao;
	}
	public long getHr_Orcadas() {
		return hr_Orcadas;
	}
	public void setHr_Orcadas(long hr_Orcadas) {
		this.hr_Orcadas = hr_Orcadas;
	}
	public String getHr_Orcamento() {
		return hr_Orcamento;
	}
	public void setHr_Orcamento(String hr_Orcamento) {
		this.hr_Orcamento = hr_Orcamento;
	}
	public String getHr_Solicitacao() {
		return hr_Solicitacao;
	}
	public void setHr_Solicitacao(String hr_Solicitacao) {
		this.hr_Solicitacao = hr_Solicitacao;
	}
	public String getDt_Solicitacao_Final() {
		return dt_Solicitacao_Final;
	}
	public void setDt_Solicitacao_Final(String dt_Solicitacao_Final) {
		this.dt_Solicitacao_Final = dt_Solicitacao_Final;
	}
	public String getDt_Solicitacao_Inicial() {
		return dt_Solicitacao_Inicial;
	}
	public void setDt_Solicitacao_Inicial(String dt_Solicitacao_Inicial) {
		this.dt_Solicitacao_Inicial = dt_Solicitacao_Inicial;
	}
	public String getNm_Empresa() {
		return nm_Empresa;
	}
	public void setNm_Empresa(String nm_Empresa) {
		this.nm_Empresa = nm_Empresa;
	}
	public String getNm_Descricao_Servico1() {
		return nm_Descricao_Servico1;
	}
	public void setNm_Descricao_Servico1(String nm_Descricao_Servico1) {
		this.nm_Descricao_Servico1 = nm_Descricao_Servico1;
	}
	public String getNm_Descricao_Servico2() {
		return nm_Descricao_Servico2;
	}
	public void setNm_Descricao_Servico2(String nm_Descricao_Servico2) {
		this.nm_Descricao_Servico2 = nm_Descricao_Servico2;
	}
	public String getNm_Descricao_Servico3() {
		return nm_Descricao_Servico3;
	}
	public void setNm_Descricao_Servico3(String nm_Descricao_Servico3) {
		this.nm_Descricao_Servico3 = nm_Descricao_Servico3;
	}
	public String getNm_Descricao_Servico4() {
		return nm_Descricao_Servico4;
	}
	public void setNm_Descricao_Servico4(String nm_Descricao_Servico4) {
		this.nm_Descricao_Servico4 = nm_Descricao_Servico4;
	}
	public String getNm_Descricao_Servico5() {
		return nm_Descricao_Servico5;
	}
	public void setNm_Descricao_Servico5(String nm_Descricao_Servico5) {
		this.nm_Descricao_Servico5 = nm_Descricao_Servico5;
	}
	public String getNm_Responsavel_Aceite() {
		return nm_Responsavel_Aceite;
	}
	public void setNm_Responsavel_Aceite(String nm_Responsavel_Aceite) {
		this.nm_Responsavel_Aceite = nm_Responsavel_Aceite;
	}
	public String getNm_Usuario() {
		return nm_Usuario;
	}
	public void setNm_Usuario(String nm_Usuario) {
		this.nm_Usuario = nm_Usuario;
	}
	public String getNm_Visto_Eletronico() {
		return nm_Visto_Eletronico;
	}
	public void setNm_Visto_Eletronico(String nm_Visto_Eletronico) {
		this.nm_Visto_Eletronico = nm_Visto_Eletronico;
	}
	public String getNm_Tecnico() {
		return nm_Tecnico;
	}
	public void setNm_Tecnico(String nm_Tecnico) {
		this.nm_Tecnico = nm_Tecnico;
	}
	public String getNm_Tecnico_Orcamento() {
		return nm_Tecnico_Orcamento;
	}
	public void setNm_Tecnico_Orcamento(String nm_Tecnico_Orcamento) {
		this.nm_Tecnico_Orcamento = nm_Tecnico_Orcamento;
	}
	public long getNr_Help_Desk() {
		return nr_Help_Desk;
	}
	public void setNr_Help_Desk(long nr_Help_Desk) {
		this.nr_Help_Desk = nr_Help_Desk;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public long getOid_Help_Desk() {
		return oid_Help_Desk;
	}
	public void setOid_Help_Desk(long oid_Help_Desk) {
		this.oid_Help_Desk = oid_Help_Desk;
	}
	public long getOid_Tecnico_Desenvolvedor() {
		return oid_Tecnico_Desenvolvedor;
	}
	public void setOid_Tecnico_Desenvolvedor(long oid_Tecnico_Desenvolvedor) {
		this.oid_Tecnico_Desenvolvedor = oid_Tecnico_Desenvolvedor;
	}
	public long getOid_Tecnico_Homologacao() {
		return oid_Tecnico_Homologacao;
	}
	public void setOid_Tecnico_Homologacao(long oid_Tecnico_Homologacao) {
		this.oid_Tecnico_Homologacao = oid_Tecnico_Homologacao;
	}
	public long getOid_Tecnico_Orcamento() {
		return oid_Tecnico_Orcamento;
	}
	public void setOid_Tecnico_Orcamento(long oid_Tecnico_Orcamento) {
		this.oid_Tecnico_Orcamento = oid_Tecnico_Orcamento;
	}
	public long getOid_Usuario() {
		return oid_Usuario;
	}
	public void setOid_Usuario(long oid_Usuario) {
		this.oid_Usuario = oid_Usuario;
	}
	public String getTx_Desenvolvimento1() {
		return tx_Desenvolvimento1;
	}
	public void setTx_Desenvolvimento1(String tx_Desenvolvimento1) {
		this.tx_Desenvolvimento1 = tx_Desenvolvimento1;
	}
	public String getTx_Desenvolvimento2() {
		return tx_Desenvolvimento2;
	}
	public void setTx_Desenvolvimento2(String tx_Desenvolvimento2) {
		this.tx_Desenvolvimento2 = tx_Desenvolvimento2;
	}
	public String getTx_Homologacao1() {
		return tx_Homologacao1;
	}
	public void setTx_Homologacao1(String tx_Homologacao1) {
		this.tx_Homologacao1 = tx_Homologacao1;
	}
	public String getTx_Homologacao2() {
		return tx_Homologacao2;
	}
	public void setTx_Homologacao2(String tx_Homologacao2) {
		this.tx_Homologacao2 = tx_Homologacao2;
	}
	public String getVl_Aceite() {
		return vl_Aceite;
	}
	public void setVl_Aceite(String vl_Aceite) {
		this.vl_Aceite = vl_Aceite;
	}
	public String getVl_Custo() {
		return vl_Custo;
	}
	public void setVl_Custo(String vl_Custo) {
		this.vl_Custo = vl_Custo;
	}
	public String getVl_Investimento() {
		return vl_Investimento;
	}
	public void setVl_Investimento(String vl_Investimento) {
		this.vl_Investimento = vl_Investimento;
	}
	public String getNm_Tecnico_Desenvolvedor() {
		return nm_Tecnico_Desenvolvedor;
	}
	public void setNm_Tecnico_Desenvolvedor(String nm_Tecnico_Desenvolvedor) {
		this.nm_Tecnico_Desenvolvedor = nm_Tecnico_Desenvolvedor;
	}
	public String getNm_Tecnico_Homologacao() {
		return nm_Tecnico_Homologacao;
	}
	public void setNm_Tecnico_Homologacao(String nm_Tecnico_Homologacao) {
		this.nm_Tecnico_Homologacao = nm_Tecnico_Homologacao;
	}
	public String getNm_Solicitante() {
		return nm_Solicitante;
	}
	public void setNm_Solicitante(String nm_Solicitante) {
		this.nm_Solicitante = nm_Solicitante;
	}
	public String getCd_Tecnico() {
		return cd_Tecnico;
	}
	public void setCd_Tecnico(String cd_Tecnico) {
		this.cd_Tecnico = cd_Tecnico;
	}
	public String getCd_Tecnico_Desenvolvedor() {
		return cd_Tecnico_Desenvolvedor;
	}
	public void setCd_Tecnico_Desenvolvedor(String cd_Tecnico_Desenvolvedor) {
		this.cd_Tecnico_Desenvolvedor = cd_Tecnico_Desenvolvedor;
	}
	public String getCd_Tecnico_Homologacao() {
		return cd_Tecnico_Homologacao;
	}
	public void setCd_Tecnico_Homologacao(String cd_Tecnico_Homologacao) {
		this.cd_Tecnico_Homologacao = cd_Tecnico_Homologacao;
	}
	public String getCd_Tecnico_Orcamento() {
		return cd_Tecnico_Orcamento;
	}
	public void setCd_Tecnico_Orcamento(String cd_Tecnico_Orcamento) {
		this.cd_Tecnico_Orcamento = cd_Tecnico_Orcamento;
	}
	public String getDm_Origem() {
		return dm_Origem;
	}
	public void setDm_Origem(String dm_Origem) {
		this.dm_Origem = dm_Origem;
	}
	public long getOid_Tecnico() {
		return oid_Tecnico;
	}
	public void setOid_Tecnico(long oid_Tecnico) {
		this.oid_Tecnico = oid_Tecnico;
	}
	
	//===================================================================================================================\\
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public boolean isIncluidoOK() throws Excecoes {
		if (this.isCodigoExistente() == false){
			Help_DeskRN rn = new Help_DeskRN();
			rn.inclui_Suporte(this);
			return true;
		}else{
			return false;
		}
	}
	public void setIncluidoOK(boolean incluidoOK) {
		this.incluidoOK = incluidoOK;
	}
	/***
	 * isCodigoExistente()
	 * Verifica se o codigo deste ed já existe cadastrado 
	 * @return boolean
	 * @throws Excecoes
	 */
	public boolean isCodigoExistente()  throws Excecoes {
		boolean dmVolta = true;
		
			Help_DeskED ed = new Help_DeskED();						        // Instancia ed para a busca
			ed.setNr_Help_Desk(this.getNr_Help_Desk()); 					// Busca o registro
			if(nr_Help_Desk !=0){
			ed = new Help_DeskRN().getByRecord(ed);                         // Retorna o registro no mesmo ed
			if (ed.getNr_Help_Desk() == 0){                                 // Se registro não existir retorna false
				dmVolta = false;
			}else{
				if (ed.oid_Help_Desk == this.getOid_Help_Desk()){ // Se registro é o mesmo oid retorna false	
					dmVolta =  false;
				}	
			}
			return dmVolta;
		}
		dmVolta = false;
		return dmVolta;
	}
	public void setCodigoExistente(boolean codigoExistente) {
		this.codigoExistente = codigoExistente;
	}
	

	
	  
}
