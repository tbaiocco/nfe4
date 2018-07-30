package com.master.rn;

/**
 * <p>Title: Produto_Cliente_ContagemRN</p>
 * <p>Description: Regras de negocio para produtos da contagem ciclica, relativa a tabela produtos_clientes_contagens</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: SPMti</p>
 * @author Teofilo Poletto Baiocco - nuovonet
 * @version 0.1.0
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.ContaBD;
import com.master.bd.Movimento_ContabilBD;
import com.master.bd.Produto_Cliente_ContagemBD;
import com.master.ed.ContaED;
import com.master.ed.Contagem_CiclicaED;
import com.master.ed.Movimento_ContabilED;
import com.master.ed.Produto_Cliente_ContagemED;
import com.master.rl.JasperRL;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Valor;
import com.master.util.bd.Transacao;

public class Produto_Cliente_ContagemRN extends Transacao  {

	Produto_Cliente_ContagemBD Produto_Cliente_ContagemBD = null;

	public Produto_Cliente_ContagemRN() {

	}

	public Produto_Cliente_ContagemED inclui(Produto_Cliente_ContagemED ed) throws Excecoes{
		Produto_Cliente_ContagemED Produto_Cliente_ContagemED = new Produto_Cliente_ContagemED();
		try{
			this.inicioTransacao();
			Produto_Cliente_ContagemBD = new Produto_Cliente_ContagemBD(this.sql);
			Produto_Cliente_ContagemED = Produto_Cliente_ContagemBD.inclui(ed);
			this.fimTransacao(true);
		} catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		} catch(Exception exc){
			this.abortaTransacao();
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public Produto_Cliente_ContagemED inclui(Produto_Cliente_ContagemED ed)");
		}
		return Produto_Cliente_ContagemED;
	}

	public void altera(Produto_Cliente_ContagemED ed) throws Excecoes{
		if (String.valueOf(ed.getOid_contagem_ciclica()).compareTo("") == 0){
			Excecoes exc = new Excecoes();
			exc.setMensagem("Código do Produto_Cliente_Contagem não foi informado !!!");
			throw exc;
		}
		try{
			if(JavaUtil.doValida(ed.getNm_qt_contada()))
				ed.setNr_qt_contada(new Double(ed.getNm_qt_contada()).doubleValue());
			this.inicioTransacao();
			Produto_Cliente_ContagemBD = new Produto_Cliente_ContagemBD(this.sql);
			Produto_Cliente_ContagemBD.altera(ed);
			this.fimTransacao(true);
		} catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		} catch(Exception exc){
			this.abortaTransacao();
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public void altera(Produto_Cliente_ContagemED ed)");
		}
	}

	public void deleta(Produto_Cliente_ContagemED ed)throws Excecoes{
		if (String.valueOf(ed.getOid_contagem_ciclica()).compareTo("") == 0){
			Excecoes exc = new Excecoes();
			exc.setMensagem("Código do Produto_Cliente_Contagem não foi informado !!!");
			throw exc;
		}
		try{
			this.inicioTransacao();
			Produto_Cliente_ContagemBD = new Produto_Cliente_ContagemBD(this.sql);
			Produto_Cliente_ContagemBD.deleta(ed);
			this.fimTransacao(true);
		} catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		} catch(Exception exc){
			this.abortaTransacao();
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "public void deleta(Produto_Cliente_ContagemED ed)");
		}
	}

	public ArrayList lista(Produto_Cliente_ContagemED ed)throws Excecoes{
		this.inicioTransacao();
		ArrayList lista = new Produto_Cliente_ContagemBD(sql).lista(ed);
		this.fimTransacao(false);
		return lista;
	}

	public Produto_Cliente_ContagemED getByRecord(Produto_Cliente_ContagemED ed)throws Excecoes{
		this.inicioTransacao();
		Produto_Cliente_ContagemED edVolta = new Produto_Cliente_ContagemED();
		edVolta = new Produto_Cliente_ContagemBD(this.sql).getByRecord(ed);
		this.fimTransacao(false);
		return edVolta;
	}

	public ArrayList getItensToContagem(Contagem_CiclicaED ed)throws Excecoes{
		this.inicioTransacao();
		ArrayList lista = new Produto_Cliente_ContagemBD(sql).getItensToContagem(ed);
		this.fimTransacao(false);
		return lista;
	}

	public ArrayList listaDatas(Contagem_CiclicaED ed)throws Excecoes{
		this.inicioTransacao();
		ArrayList lista = new Produto_Cliente_ContagemBD(sql).listaDatas(ed);
		this.fimTransacao(false);
		return lista;
	}

	public void relContagemProdutos(Produto_Cliente_ContagemED ed,HttpServletRequest request, HttpServletResponse response, String relatorio) throws Excecoes {
		ArrayList list = new ArrayList();
		try {
			this.inicioTransacao();
			// Busca a lista
			list = new Produto_Cliente_ContagemBD(this.sql).lista(ed);
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