/*
 * Created on 24/05/2005
 *
 */
package com.master.util;

import java.util.ArrayList;
import java.util.List;

public class TransacaoLog {
	private int pid;
	private String classe;
	List comandos = new ArrayList();

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public List getComandos() {
		return comandos;
	}
	public void setComandos(List comandos) {
		this.comandos = comandos;
	}
}
