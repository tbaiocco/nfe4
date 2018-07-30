package com.master.rn;

/**
 * @author Jeanine e Vinícius
 *
 */

import java.util.ArrayList;

import com.master.bd.Nota_ContabilBD;
import com.master.ed.Nota_ContabilED;
import com.master.ed.RelatorioBaseED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.master.rl.JasperRL;

public class Nota_ContabilRN extends Transacao {
	Nota_ContabilBD Nota_ContabilBD = null;

	public Nota_ContabilRN() {

	}

	// ========================== Inclui Usuário =============================\\
	public Nota_ContabilED inclui(Nota_ContabilED ed, HttpServletRequest request)
			throws Excecoes {

		Nota_ContabilED nota_ContabilED = new Nota_ContabilED();

		try {

			this.inicioTransacao();
			Nota_ContabilBD = new Nota_ContabilBD(this.sql);
			nota_ContabilED = Nota_ContabilBD.inclui(ed);
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

		return nota_ContabilED;
	}

	public Nota_ContabilED inclui(Nota_ContabilED ed)
			throws Excecoes {

		Nota_ContabilED nota_ContabilED = new Nota_ContabilED();

		try {

			this.inicioTransacao();
			Nota_ContabilBD = new Nota_ContabilBD(this.sql);
			nota_ContabilED = Nota_ContabilBD.inclui(ed);
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

		return nota_ContabilED;
	}

	// ========================== Altera ================================\\
	public void altera(Nota_ContabilED ed) throws Excecoes {

		try {
			this.inicioTransacao();

			new Nota_ContabilBD(this.sql).altera(ed);

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
	public ArrayList lista(Nota_ContabilED ed) throws Excecoes {

		try {
			this.inicioTransacao();
			ArrayList lista = new Nota_ContabilBD(sql).lista(ed);
			// this.fimTransacao(false);

			return lista;
		} finally {
			this.fimTransacao(false);
		}
	}

	// ======================= GetByRecord ===================================\\
	public Nota_ContabilED getByRecord(Nota_ContabilED ed) throws Excecoes {

		this.inicioTransacao();

		Nota_ContabilED edVolta = new Nota_ContabilED();

		edVolta = new Nota_ContabilBD(this.sql).getByRecord(ed);

		this.fimTransacao(false);

		return edVolta;
	}

}
