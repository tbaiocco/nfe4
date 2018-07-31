package com.master.ed;

import java.io.Serializable;

/**
 * @author Cristian Vianna Garcia
 *
 */
public class Controle_TarefasED extends MasterED implements Serializable {

	private static final long serialVersionUID = 7565709400038136233L;

	public Controle_TarefasED() {
	}
	
	private int oid_Tarefa;
	private String NM_Titulo;
	private String NM_Descricao;
	private int oid_Responsavel;
	private String NM_Responsavel;
	private int oid_Prioridade;
	private String NM_Prioridade;
	private int oid_Situacao;
	private String NM_Situacao;
	
	public void setOid_Tarefa(int oid_Tarefa) {
		this.oid_Tarefa = oid_Tarefa;
	}
	public int getOid_Tarefa() {
		return oid_Tarefa;
	}
	public void setNM_Titulo(String NM_Titulo) {
		this.NM_Titulo = NM_Titulo;
	}
	public String getNM_Titulo() {
		return NM_Titulo;
	}
	public void setNM_Descricao(String NM_Descricao) {
		this.NM_Descricao = NM_Descricao;
	}
	public String getNM_Descricao() {
		return NM_Descricao;
	}
	public void setOid_Responsavel(int oid_Responsavel) {
		this.oid_Responsavel = oid_Responsavel;
	}
	public int getOid_Responsavel() {
		return oid_Responsavel;
	}
	public void setNM_Responsavel(String NM_Responsavel) {
		this.NM_Responsavel = NM_Responsavel;
	}
	public String getNM_Responsavel() {
		return NM_Responsavel;
	}
	public void setOid_Prioridade(int oid_Prioridade) {
		this.oid_Prioridade = oid_Prioridade;
	}
	public int getOid_Prioridade() {
		return oid_Prioridade;
	}
	public void setNM_Prioridade(String NM_Prioridade) {
		this.NM_Prioridade = NM_Prioridade;
	}
	public String getNM_Prioridade() {
		return NM_Prioridade;
	}
	public void setNM_Situacao(String NM_Situacao) {
		this.NM_Situacao = NM_Situacao;
	}
	public String getNM_Situacao() {
		return NM_Situacao;
	}
	public void setOid_Situacao(int oid_Situacao) {
		this.oid_Situacao = oid_Situacao;
	}
	public int getOid_Situacao() {
		return oid_Situacao;
	}
}
