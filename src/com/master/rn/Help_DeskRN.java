package com.master.rn;

/**
 * @author Jeanine e Vinícius
 *
 */

import java.util.ArrayList;

import com.master.bd.Help_DeskBD;
import com.master.ed.Help_DeskED;
import com.master.ed.RelatorioBaseED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.master.rl.JasperRL;
import com.master.ed.UsuarioED;

public class Help_DeskRN extends Transacao {
	Help_DeskBD Help_DeskBD = null;

	public Help_DeskRN() {
      
	}

	// ========================== Inclui Usuário =============================\\
	public Help_DeskED inclui_Usuario(Help_DeskED ed, HttpServletRequest request)
			throws Excecoes {

		Help_DeskED help_DeskED = new Help_DeskED();

		try {

			this.inicioTransacao();
			Help_DeskBD = new Help_DeskBD(this.sql);
			// ed.setDt_Solicitacao.FormataData.formataDataBT();
			ed.setDt_Solicitacao(Data.getDataDMY()); // FormataData.formataDataBT
			ed.setHr_Solicitacao(Data.getHoraHM());
			ed.setDm_Situacao_Ordem_Servico("Aberta");

			UsuarioED usu = new UsuarioED();
			usu = (UsuarioED) request.getSession().getAttribute("usuario");
			ed.setNm_Usuario(usu.getNm_Usuario());
			ed.setOid_Usuario(usu.getOid_Usuario().longValue());
			
			ed.setNm_Empresa(usu.getNm_Razao_Social());
			ed.setOid_Empresa(usu.getOid_Empresa());
			// usu.getNm_Usuario();

			help_DeskED = Help_DeskBD.inclui_Usuario(ed);
			this.fimTransacao(true);

		}

		catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}

		catch (Exception e) {
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao incluir");
			excecoes.setMetodo("inclui");
			excecoes.setExc(e);
			this.abortaTransacao();

			throw excecoes;
		}

		return help_DeskED;
	}

	// ========================== Inclui Usuário =============================\\
	public Help_DeskED inclui_Suporte(Help_DeskED ed)
			throws Excecoes {

		Help_DeskED help_DeskED = new Help_DeskED();

		try {

			this.inicioTransacao();
			Help_DeskBD = new Help_DeskBD(this.sql);
			
			ed.setDm_Situacao_Ordem_Servico("Aberta");
			
			//UsuarioED usu = new UsuarioED();
			//usu = (UsuarioED) request.getSession().getAttribute("usuario");
			//ed.setNm_Usuario(usu.getNm_Usuario());
			//ed.setOid_Usuario(usu.getOid_Usuario().longValue());
			
			help_DeskED = Help_DeskBD.inclui_Suporte(ed);
			this.fimTransacao(true);

		}

		catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}

		catch (Exception e) {
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao incluir");
			excecoes.setMetodo("inclui");
			excecoes.setExc(e);
			this.abortaTransacao();

			throw excecoes;
		}

		return help_DeskED;
	}
	
	
	// ========================== Altera ================================\\
	public void altera(Help_DeskED ed, String metodo) throws Excecoes {

		try {
			this.inicioTransacao();
			if ("altera_Suporte_Spm".equals(metodo))
				new Help_DeskBD(this.sql).altera_Suporte_Spm(ed);
			if ("altera_Orcamento".equals(metodo))
				new Help_DeskBD(this.sql).altera_Orcamento(ed);
			if ("altera_Desenvolvimento".equals(metodo))
				new Help_DeskBD(this.sql).altera_Desenvolvimento(ed);
			if ("altera_Homologacao".equals(metodo))
				new Help_DeskBD(this.sql).altera_Homologacao(ed);
			if ("altera_Usuario".equals(metodo))
				new Help_DeskBD(this.sql).altera_Usuario(ed);
			if ("altera_Aceite".equals(metodo))
				new Help_DeskBD(this.sql).altera_Aceite(ed);

			this.fimTransacao(true);
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		} catch (RuntimeException e) {
			this.abortaTransacao();
			throw e;
		}
	}

	// ======================= Lista Usuário ===========================\\
	public ArrayList lista(Help_DeskED ed) throws Excecoes {

		try {
			this.inicioTransacao();
			ArrayList lista = new Help_DeskBD(sql).lista(ed);
			// this.fimTransacao(false);

			return lista;
		} finally {
			this.fimTransacao(false);
		}
	}

	// ======================= Lista Usuário ===========================\\
	public ArrayList lista(Help_DeskED ed, HttpServletRequest request) throws Excecoes {

		try {
			this.inicioTransacao();
			UsuarioED usu = new UsuarioED();
			usu = (UsuarioED) request.getSession().getAttribute("usuario");
			ed.setOid_Empresa(usu.getOid_Empresa());
			ArrayList lista = new Help_DeskBD(sql).lista(ed);
			// this.fimTransacao(false);

			return lista;
		} finally {
			this.fimTransacao(false);
		}
	}
	
	//session.setAttribute("usuario", edUsuario);
	

	// ===================== Lista Suporte ================================\\
	public ArrayList lista2(Help_DeskED ed) throws Excecoes {

		try {
			this.inicioTransacao();
			ArrayList lista2 = new Help_DeskBD(sql).lista2(ed);
			// this.fimTransacao(false);
			return lista2;
		} finally {
			this.fimTransacao(false);
		}
	}
	
	public void geraRelatorio(Help_DeskED ed,HttpServletRequest request, HttpServletResponse response) throws Excecoes {
		try {
			this.inicioTransacao();
			
			ArrayList listMov = new ArrayList();
			listMov = new Help_DeskBD(this.sql).lista2(ed);
			
			ed.setLista(listMov); 
			ed.setResponse(response);
			ed.setNomeRelatorio("helpListaOS"); // Seta o nome do relatório
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
		} 
		finally { 
			this.fimTransacao(false);
		}
	}
	
	public void geraFicha(Help_DeskED ed,HttpServletRequest request, HttpServletResponse response) throws Excecoes {
		try {
			this.inicioTransacao();
			
			ArrayList listMov = new ArrayList();
			Help_DeskED edVolta = new Help_DeskED();
			edVolta = new Help_DeskBD(this.sql).getByRecord(ed);
			listMov.add(edVolta);
			
			ed.setLista(listMov); 
			ed.setResponse(response);
			ed.setNomeRelatorio("OSficha"); // Seta o nome do relatório
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
		} 
		finally { 
			this.fimTransacao(false);
		}
	}

	// =======================================================================\\

	
	public Help_DeskED getByRecord(Help_DeskED ed) throws Excecoes {

		this.inicioTransacao();

		Help_DeskED edVolta = new Help_DeskED();

		edVolta = new Help_DeskBD(this.sql).getByRecord(ed);

		this.fimTransacao(false);

		return edVolta;
	}
	
	
	public void getByRecord(Help_DeskED ed, HttpServletRequest request, String nmObj) throws Excecoes {

		try {
			this.inicioTransacao();
			Help_DeskED edQBR = new Help_DeskBD(this.sql).getByRecord(ed);
			request.setAttribute(nmObj, edQBR.getOid_Help_Desk()== 0 ? null : edQBR);
		} finally { 
			this.fimTransacao(false);
		}
	}

}
