package com.master.rn;

/**
 * <p>Title: Contagem_CiclicaRN</p>
 * <p>Description: Regras de negocio para contagem ciclica, relativa a tabela contagens_ciclicas</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: SPMti</p>
 * @author Teofilo Poletto Baiocco - nuovonet
 * @version 0.1.0
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Contagem_CiclicaBD;
import com.master.bd.Produto_Cliente_ContagemBD;
import com.master.ed.Contagem_CiclicaED;
import com.master.ed.Produto_Cliente_ContagemED;
import com.master.ed.WMS_EstoqueED;
import com.master.rl.JasperRL;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Contagem_CiclicaRN extends Transacao  {

	Contagem_CiclicaBD Contagem_CiclicaBD = null;

	public Contagem_CiclicaRN() {

	}

	public Contagem_CiclicaED inclui(Contagem_CiclicaED ed) throws Excecoes{
		Contagem_CiclicaED Contagem_CiclicaED = new Contagem_CiclicaED();
		try{
			this.inicioTransacao();
			ed.setDm_situacao("A");
			ed.setDt_fim(Data.manipulaDias(ed.getDt_inicio(),new Long(ed.getNr_dias_contagem_ciclica()).intValue()));
			Contagem_CiclicaBD = new Contagem_CiclicaBD(this.sql);
			Contagem_CiclicaED = Contagem_CiclicaBD.inclui(ed);
			//Fazer a inclusao dos itens!
			ed = Contagem_CiclicaBD.getByRecord(Contagem_CiclicaED);
			Produto_Cliente_ContagemBD prodBD = new Produto_Cliente_ContagemBD(this.sql);
			ArrayList itens = prodBD.getItensToContagem(ed);
			for(int i=0;i<itens.size();i++){
 				Produto_Cliente_ContagemED produto = (Produto_Cliente_ContagemED)itens.get(i);
 				prodBD.inclui(produto);
			}
			ed.setNr_qt_itens(itens.size());
			Contagem_CiclicaBD.altera(ed);

			this.fimTransacao(true);
		} catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		} catch(Exception exc){
			this.abortaTransacao();
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public Contagem_CiclicaED inclui(Contagem_CiclicaED ed)");
		}
		return ed;
	}

	public void altera(Contagem_CiclicaED ed) throws Excecoes{
		if (String.valueOf(ed.getOid_contagem_ciclica()).compareTo("") == 0){
			Excecoes exc = new Excecoes();
			exc.setMensagem("Código do Contagem_Ciclica não foi informado !!!");
			throw exc;
		}
		try{
			this.inicioTransacao();
			Contagem_CiclicaBD = new Contagem_CiclicaBD(this.sql);
			Contagem_CiclicaBD.altera(ed);
			this.fimTransacao(true);
		} catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		} catch(Exception exc){
			this.abortaTransacao();
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public void altera(Contagem_CiclicaED ed)");
		}
	}

	public void deleta(Contagem_CiclicaED ed)throws Excecoes{
		if (String.valueOf(ed.getOid_contagem_ciclica()).compareTo("") == 0){
			Excecoes exc = new Excecoes();
			exc.setMensagem("Código do Contagem_Ciclica não foi informado !!!");
			throw exc;
		}
		try{
			this.inicioTransacao();
			Contagem_CiclicaBD = new Contagem_CiclicaBD(this.sql);
			Contagem_CiclicaBD.deleta(ed);
			this.fimTransacao(true);
		} catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		} catch(Exception exc){
			this.abortaTransacao();
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public void deleta(Contagem_CiclicaED ed)");
		}
	}

	public void encerra(Contagem_CiclicaED ed) throws Excecoes{
		if (String.valueOf(ed.getOid_contagem_ciclica()).compareTo("") == 0){
			Excecoes exc = new Excecoes();
			exc.setMensagem("Código do Contagem_Ciclica não foi informado !!!");
			throw exc;
		}
		try{
			this.inicioTransacao();
			Contagem_CiclicaBD = new Contagem_CiclicaBD(this.sql);
			Contagem_CiclicaBD.encerra(ed);
			this.fimTransacao(true);
		} catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		} catch(Exception exc){
			this.abortaTransacao();
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public void encerra(Contagem_CiclicaED ed)");
		}
	}

	public ArrayList lista(Contagem_CiclicaED ed)throws Excecoes{
		this.inicioTransacao();
		ArrayList lista = new Contagem_CiclicaBD(sql).lista(ed);
		this.fimTransacao(false);
		return lista;
	}

	public Contagem_CiclicaED getByRecord(Contagem_CiclicaED ed)throws Excecoes{
		this.inicioTransacao();
		Contagem_CiclicaED edVolta = new Contagem_CiclicaED();
		edVolta = new Contagem_CiclicaBD(this.sql).getByRecord(ed);
		this.fimTransacao(false);
		return edVolta;
	}

	public void relContagem(Contagem_CiclicaED ed,HttpServletRequest request, HttpServletResponse response, String relatorio) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			this.inicioTransacao();
			// Busca a lista
			list = new Contagem_CiclicaBD(this.sql).listaRelatorio(ed);
			// Se tiver alguma item na lista, monta relatório senão avisa na tela
			if (list.size() > 0 ) {
				ed.setLista(list);
				ed.setResponse(response);
				ed.setNomeRelatorio(relatorio); // Seta o nome do relatório
				new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
				request.setAttribute("retorno",new String("T"));
			} else {
				request.setAttribute("retorno",new String("F"));
			}
		}
		finally {
			this.fimTransacao(false);
		}
	}


}