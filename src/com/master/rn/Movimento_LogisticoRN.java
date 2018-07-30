package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.Movimento_LogisticoBD;
import com.master.ed.Movimento_LogisticoED;
import com.master.rl.Movimento_LogisticoRL;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

public class Movimento_LogisticoRN extends Transacao  {
	Movimento_LogisticoBD Movimento_LogisticoBD = null;

	public Movimento_LogisticoRN() {
		//Movimento_Logisticobd = new Movimento_LogisticoBD(this.sql);
	}

/********************************************************
 *
 *******************************************************/
	public Movimento_LogisticoED inclui(Movimento_LogisticoED ed)throws Excecoes{
		Movimento_LogisticoED Movimento_LogisticoED = new Movimento_LogisticoED();

		try{
			this.inicioTransacao();
			Movimento_LogisticoBD = new Movimento_LogisticoBD(this.sql);
			Movimento_LogisticoED = Movimento_LogisticoBD.inclui(ed);
			this.fimTransacao(true);
		}
		catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		}
		catch(Exception e){
			this.abortaTransacao();
			e.printStackTrace();
			throw new Excecoes("Erro de inclusao.", e, this.getClass().getName(), "inclui()");
		}
		return Movimento_LogisticoED;
	}

/********************************************************
 *
 *******************************************************/
	public void altera(Movimento_LogisticoED ed)throws Excecoes{

		try{
			this.inicioTransacao();
			Movimento_LogisticoBD = new Movimento_LogisticoBD(this.sql);
			Movimento_LogisticoBD.altera(ed);
			this.fimTransacao(true);
		}
		catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		}
		catch(Exception e){
			this.abortaTransacao();
			e.printStackTrace();
			throw new Excecoes("Erro de alteracao.", e, this.getClass().getName(), "altera()");
		}
	}

/********************************************************
 *
 *******************************************************/
	public void deleta(Movimento_LogisticoED ed)throws Excecoes{

		try{
			this.inicioTransacao();
			Movimento_LogisticoBD = new Movimento_LogisticoBD(this.sql);
			Movimento_LogisticoBD.deleta(ed);
			this.fimTransacao(true);
		}
		catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		}
		catch(Exception e){
			this.abortaTransacao();
			e.printStackTrace();
			throw new Excecoes("Erro de exclusao.", e, this.getClass().getName(), "deleta()");
		}
	}

/********************************************************
 *
 *******************************************************/
	public ArrayList lista(Movimento_LogisticoED ed)throws Excecoes{
		//retorna um arraylist de ED´s
		this.inicioTransacao();
		ArrayList lista = new Movimento_LogisticoBD(sql).lista(ed);
		this.fimTransacao(false);
		return lista;
	}

/********************************************************
 *
 *******************************************************/
	public Movimento_LogisticoED getByRecord(Movimento_LogisticoED ed)throws Excecoes{
		//inicia conexao com bd
		this.inicioTransacao();
		//instancia ed de retorno
		Movimento_LogisticoED edVolta = new Movimento_LogisticoED();
		//atribui ao ed de retorno
		edVolta = new Movimento_LogisticoBD(this.sql).getByRecord(ed);
		//libera conexao nao "commitando"
		this.fimTransacao(false);
		return edVolta;
	}

	public void geraRelatorio(Movimento_LogisticoED ed, HttpServletResponse response)throws Excecoes{

		//antes de invocar chamada ao relatorio deve-se
		//fazer validacoes de regra de negocio
		String nf = ed.getOid_nota_fiscal();
//		System.out.println(nf);
		Movimento_LogisticoED toSearch = ed;
		ArrayList dados = new ArrayList();
		this.gera_Movimento_via_Relatorio(toSearch);
		ed.setOid_nota_fiscal(nf);

		this.inicioTransacao();

		Movimento_LogisticoBD = new Movimento_LogisticoBD(this.sql);

		dados = Movimento_LogisticoBD.dados_relatorio(ed);

		if(ed.getRelatorio().equals("Transit_Time")){
			new Movimento_LogisticoRL().relTransitTime(dados, ed, response);
		} else if(ed.getRelatorio().equals("Movimentacao")){
			new Movimento_LogisticoRL().relMovimentacao(dados, ed, response);
		} else if(ed.getRelatorio().equals("Armazenagem")){
			new Movimento_LogisticoRL().relArmazenagem(dados, ed, response);
		} else {
			new Movimento_LogisticoRL().relMovimentacao(dados, ed, response);
		}

		this.fimTransacao(true);
	}

	public void gera_Movimento(Movimento_LogisticoED ed)throws Excecoes{

		try{
			this.inicioTransacao();
			Movimento_LogisticoBD = new Movimento_LogisticoBD(this.sql);

			if(!JavaUtil.doValida(ed.getOid_nota_fiscal())){
				Iterator it = Movimento_LogisticoBD.getRegistros_para_geracao().iterator();
				while(it.hasNext()){
					ed.setOid_nota_fiscal(it.next().toString());
					Movimento_LogisticoBD.gera_Movimento(ed);
				}
			} else {
				Movimento_LogisticoBD.gera_Movimento(ed);
			}
			this.fimTransacao(true);
		}
		catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		}
		catch(Exception e){
			this.abortaTransacao();
			e.printStackTrace();
			throw new Excecoes("Erro de geracao.", e, this.getClass().getName(), "gera_Movimento()");
		}
	}

	public void gera_Movimento_via_Relatorio(Movimento_LogisticoED ed)throws Excecoes{
		Movimento_LogisticoED edInterno = (Movimento_LogisticoED)ed;
		try{
			this.inicioTransacao();
			Movimento_LogisticoBD = new Movimento_LogisticoBD(this.sql);
			Iterator it = Movimento_LogisticoBD.getRegistros_para_geracao_via_Relatorio(edInterno).iterator();
			while(it.hasNext()){
				edInterno.setOid_nota_fiscal(it.next().toString());
				Movimento_LogisticoBD.gera_Movimento(edInterno);
			}
			this.fimTransacao(true);
		}
		catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		}
		catch(Exception e){
			this.abortaTransacao();
			e.printStackTrace();
			throw new Excecoes("Erro de geracao.", e, this.getClass().getName(), "gera_Movimento()");
		}
	}

}