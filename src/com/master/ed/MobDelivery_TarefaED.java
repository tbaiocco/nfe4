package com.master.ed;

import java.io.Serializable;

/**
 * @author Régis Steigleder
 * @serial Tarefa no ModDelivery 
 * @serialData 19/05/2008
 */

public class MobDelivery_TarefaED extends RelatorioBaseED implements Serializable  {

	private static final long serialVersionUID = -9115228162876983776L;
	
	public MobDelivery_TarefaED() {
		super();
	}
	
	private long oid_Tarefa ;
	private long oid_Grupo_Tarefa ;
	private long oid_Unidade ;
	private long nr_Tarefa ;
	private double nr_Volume ;
	private String nm_Endereco ;
	private String nm_Nome ;
	private double nr_Peso; 		// Campo exclusivo para Coleta
	private String nm_Solicitante;	// Campo exclusivo para Coleta
	private String tx_Observacao;	// Campo exclusivo para Coleta
	private String dm_Situacao ;
	private String tx_Rg_Agente ;	// Campo exclusivo para Entrega - retorno
	private String nm_Agente ;		// Campo exclusivo para Entrega - retorno
	private String dt_Execucao ;
	private String hr_Execucao ;
	private String hr_Inicio_Execucao ;
	private String hr_Fim_Execucao ;
	private String nr_Odometro ;
	private long oid_Executor ;
	private String dm_Tipo;
	private String dm_Protocolo;
	private long nr_Protocolo;
	private String dt_Tarefa;
	private long nr_Ordem;
	private String oid_Conhecimento;
	private long nr_Ordem_Alocado;
	private String dt_Registro_Execucao ;
	private String hr_Registro_Execucao ;
	//----------------------//
	private long nr_Grupo_Tarefa;
	private String nm_Grupo_Tarefa;
	private String dt_Grupo_Tarefa;
	private String nm_Unidade;
	private String nm_Executor;
	private String nm_Situacao;
	private long nr_Cor;
	private String dt_Dia;
	private String dt_Inicial;
	private String dt_Final;
	//----------------------//
	private int nr_Total_Tarefas;
	private int nr_Tarefas_Executadas;
	private int nr_Tarefas_N_Executadas;
	
	public String getDm_Protocolo() {
		return dm_Protocolo;
	}
	public void setDm_Protocolo(String dm_Protocolo) {
		this.dm_Protocolo = dm_Protocolo;
	}
	public String getDm_Situacao() {
		return dm_Situacao;
	}
	public void setDm_Situacao(String dm_Situacao) {
		this.dm_Situacao = dm_Situacao;
	}
	public String getDm_Tipo() {
		return dm_Tipo;
	}
	public void setDm_Tipo(String dm_Tipo) {
		this.dm_Tipo = dm_Tipo;
	}
	public String getDt_Execucao() {
		return dt_Execucao;
	}
	public void setDt_Execucao(String dt_Execucao) {
		this.dt_Execucao = dt_Execucao;
	}
	public String getDt_Grupo_Tarefa() {
		return dt_Grupo_Tarefa;
	}
	public void setDt_Grupo_Tarefa(String dt_Grupo_Tarefa) {
		this.dt_Grupo_Tarefa = dt_Grupo_Tarefa;
	}
	public String getHr_Execucao() {
		return hr_Execucao;
	}
	public void setHr_Execucao(String hr_Execucao) {
		this.hr_Execucao = hr_Execucao;
	}
	public String getHr_Fim_Execucao() {
		return hr_Fim_Execucao;
	}
	public void setHr_Fim_Execucao(String hr_Fim_Execucao) {
		this.hr_Fim_Execucao = hr_Fim_Execucao;
	}
	public String getHr_Inicio_Execucao() {
		return hr_Inicio_Execucao;
	}
	public void setHr_Inicio_Execucao(String hr_Inicio_Execucao) {
		this.hr_Inicio_Execucao = hr_Inicio_Execucao;
	}
	public String getNm_Agente() {
		return nm_Agente;
	}
	public void setNm_Agente(String nm_Agente) {
		this.nm_Agente = nm_Agente;
	}
	public String getNm_Endereco() {
		return nm_Endereco;
	}
	public void setNm_Endereco(String nm_Endereco) {
		this.nm_Endereco = nm_Endereco;
	}
	public String getNm_Executor() {
		return nm_Executor;
	}
	public void setNm_Executor(String nm_Executor) {
		this.nm_Executor = nm_Executor;
	}
	public String getNm_Grupo_Tarefa() {
		return nm_Grupo_Tarefa;
	}
	public void setNm_Grupo_Tarefa(String nm_Grupo_Tarefa) {
		this.nm_Grupo_Tarefa = nm_Grupo_Tarefa;
	}
	public String getNm_Nome() {
		return nm_Nome;
	}
	public void setNm_Nome(String nm_Nome) {
		this.nm_Nome = nm_Nome;
	}
	public String getNm_Unidade() {
		return nm_Unidade;
	}
	public void setNm_Unidade(String nm_Unidade) {
		this.nm_Unidade = nm_Unidade;
	}
	public long getNr_Grupo_Tarefa() {
		return nr_Grupo_Tarefa;
	}
	public void setNr_Grupo_Tarefa(long nr_Grupo_Tarefa) {
		this.nr_Grupo_Tarefa = nr_Grupo_Tarefa;
	}
	public String getNr_Odometro() {
		return nr_Odometro;
	}
	public void setNr_Odometro(String nr_Odometro) {
		this.nr_Odometro = nr_Odometro;
	}
	public long getNr_Protocolo() {
		return nr_Protocolo;
	}
	public void setNr_Protocolo(long nr_Protocolo) {
		this.nr_Protocolo = nr_Protocolo;
	}
	public long getNr_Tarefa() {
		return nr_Tarefa;
	}
	public void setNr_Tarefa(long nr_Tarefa) {
		this.nr_Tarefa = nr_Tarefa;
	}
	public double getNr_Volume() {
		return nr_Volume;
	}
	public void setNr_Volume(double nr_Volume) {
		this.nr_Volume = nr_Volume;
	}
	public long getOid_Executor() {
		return oid_Executor;
	}
	public void setOid_Executor(long oid_Executor) {
		this.oid_Executor = oid_Executor;
	}
	public long getOid_Grupo_Tarefa() {
		return oid_Grupo_Tarefa;
	}
	public void setOid_Grupo_Tarefa(long oid_Grupo_Tarefa) {
		this.oid_Grupo_Tarefa = oid_Grupo_Tarefa;
	}
	public long getOid_Tarefa() {
		return oid_Tarefa;
	}
	public void setOid_Tarefa(long oid_Tarefa) {
		this.oid_Tarefa = oid_Tarefa;
	}
	public long getOid_Unidade() {
		return oid_Unidade;
	}
	public void setOid_Unidade(long oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}
	public String getTx_Rg_Agente() {
		return tx_Rg_Agente;
	}
	public void setTx_Rg_Agente(String tx_Rg_Agente) {
		this.tx_Rg_Agente = tx_Rg_Agente;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getDt_Tarefa() {
		return dt_Tarefa;
	}
	public void setDt_Tarefa(String dt_Tarefa) {
		this.dt_Tarefa = dt_Tarefa;
	}
	public String getNm_Situacao() {
		return nm_Situacao;
	}
	public void setNm_Situacao(String nm_Situacao) {
		this.nm_Situacao = nm_Situacao;
	}
	public long getNr_Cor() {
		return nr_Cor;
	}
	public void setNr_Cor(long nr_Cor) {
		this.nr_Cor = nr_Cor;
	}
	public String getDt_Dia() {
		return dt_Dia;
	}
	public void setDt_Dia(String dt_Dia) {
		this.dt_Dia = dt_Dia;
	}
	public String getOid_Conhecimento() {
		return oid_Conhecimento;
	}
	public void setOid_Conhecimento(String oid_Conhecimento) {
		this.oid_Conhecimento = oid_Conhecimento;
	}
	public long getNr_Ordem() {
		return nr_Ordem;
	}
	public void setNr_Ordem(long nr_Ordem) {
		this.nr_Ordem = nr_Ordem;
	}
	public long getNr_Ordem_Alocado() {
		return nr_Ordem_Alocado;
	}
	public void setNr_Ordem_Alocado(long nr_Ordem_Alocado) {
		this.nr_Ordem_Alocado = nr_Ordem_Alocado;
	}
	public String getDt_Registro_Execucao() {
		return dt_Registro_Execucao;
	}
	public void setDt_Registro_Execucao(String dt_Registro_Execucao) {
		this.dt_Registro_Execucao = dt_Registro_Execucao;
	}
	public String getHr_Registro_Execucao() {
		return hr_Registro_Execucao;
	}
	public void setHr_Registro_Execucao(String hr_Registro_Execucao) {
		this.hr_Registro_Execucao = hr_Registro_Execucao;
	}
	public String getDt_Final() {
		return dt_Final;
	}
	public void setDt_Final(String dt_Final) {
		this.dt_Final = dt_Final;
	}
	public String getDt_Inicial() {
		return dt_Inicial;
	}
	public void setDt_Inicial(String dt_Inicial) {
		this.dt_Inicial = dt_Inicial;
	}
	public int getNr_Tarefas_Executadas() {
		return nr_Tarefas_Executadas;
	}
	public void setNr_Tarefas_Executadas(int nr_Tarefas_Executadas) {
		this.nr_Tarefas_Executadas = nr_Tarefas_Executadas;
	}
	public int getNr_Tarefas_N_Executadas() {
		return nr_Tarefas_N_Executadas;
	}
	public void setNr_Tarefas_N_Executadas(int nr_Tarefas_N_Executadas) {
		this.nr_Tarefas_N_Executadas = nr_Tarefas_N_Executadas;
	}
	public int getNr_Total_Tarefas() {
		return nr_Total_Tarefas;
	}
	public void setNr_Total_Tarefas(int nr_Total_Tarefas) {
		this.nr_Total_Tarefas = nr_Total_Tarefas;
	}
	public String getNm_Solicitante() {
		return nm_Solicitante;
	}
	public void setNm_Solicitante(String nm_Solicitante) {
		this.nm_Solicitante = nm_Solicitante;
	}
	public double getNr_Peso() {
		return nr_Peso;
	}
	public void setNr_Peso(double nr_Peso) {
		this.nr_Peso = nr_Peso;
	}
	public String getTx_Observacao() {
		return tx_Observacao;
	}
	public void setTx_Observacao(String tx_Observacao) {
		this.tx_Observacao = tx_Observacao;
	}

	
}	
	