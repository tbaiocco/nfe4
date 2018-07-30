package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Ocorrencia_DuplicataBD;
import com.master.ed.Ocorrencia_DuplicataED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Ocorrencia_DuplicataRN extends Transacao  {
	Ocorrencia_DuplicataBD Ocorrencia_DuplicataBD = null;
	
	
	public Ocorrencia_DuplicataRN() {
		//Ocorrencia_Duplicatabd = new Ocorrencia_DuplicataBD(this.sql);
	}
	
	public Ocorrencia_DuplicataED inclui(Ocorrencia_DuplicataED ed)
	throws Excecoes {
		Ocorrencia_DuplicataED ocorrencia_DuplicataED = new Ocorrencia_DuplicataED();
		if (String.valueOf(ed.getOID_Duplicata()).compareTo("") == 0){
			Excecoes exc = new Excecoes();
			exc.setMensagem("Código do Duplicata não foi informado !!!10");
			throw exc;
		}
		try {
			this.inicioTransacao();
			ocorrencia_DuplicataED = new Ocorrencia_DuplicataBD(this.sql).inclui(ed);
			this.fimTransacao(true);
			return ocorrencia_DuplicataED;
		} catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		}
	}
	
	public void altera(Ocorrencia_DuplicataED ed)
	throws Excecoes {
		if (String.valueOf(ed.getOID_Ocorrencia_Duplicata()).compareTo("") == 0){
			Excecoes exc = new Excecoes();
			exc.setMensagem("Código do Ocorrencia_Duplicata não foi informado !!!");
			throw exc;
		}
		try{
			this.inicioTransacao();
			new Ocorrencia_DuplicataBD(this.sql).altera(ed);
			this.fimTransacao(true);
		} catch(Excecoes e){
			this.abortaTransacao();
			throw e;
		}
	}
	
	public void deleta(Ocorrencia_DuplicataED ed)
	throws Excecoes {
		if (String.valueOf(ed.getOID_Ocorrencia_Duplicata()).compareTo("") == 0){
			Excecoes exc = new Excecoes();
			exc.setMensagem("Código do Ocorrencia_Duplicata não foi informado !!!");
			throw exc;
		}
		try {
			this.inicioTransacao();
			new Ocorrencia_DuplicataBD(this.sql).deleta(ed);
			this.fimTransacao(true);
		} catch(Excecoes e){
			this.abortaTransacao();
			throw e;
		}
	}
	
	public ArrayList lista(Ocorrencia_DuplicataED ed)throws Excecoes{
		//retorna um arraylist de ED´s
		this.inicioTransacao();
		try {
			ArrayList lista = new Ocorrencia_DuplicataBD(sql).lista(ed);
			return lista;
		} finally {
			this.fimTransacao(false);
		}
	}
	
	public Ocorrencia_DuplicataED getByRecord(Ocorrencia_DuplicataED ed)throws Excecoes{
		this.inicioTransacao();
		try {
			return new Ocorrencia_DuplicataBD(this.sql).getByRecord(ed);
		} finally {
			this.fimTransacao(false);
		}
	}
	
	public void geraRelatorio(Ocorrencia_DuplicataED ed)throws Excecoes{
		this.inicioTransacao();
		try {
			new Ocorrencia_DuplicataBD(this.sql).geraRelatorio(ed);
		} finally {
			this.fimTransacao(false);
		}
	}
}