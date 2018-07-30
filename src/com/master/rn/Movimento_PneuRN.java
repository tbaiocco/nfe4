package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.ConsertoBD;
import com.master.bd.Movimento_PneuBD;
import com.master.bd.PneuBD;
import com.master.bd.Vida_PneuBD;
import com.master.ed.ConsertoED;
import com.master.ed.Local_EstoqueED;
import com.master.ed.Movimento_PneuED;
import com.master.ed.PneuED;
import com.master.ed.UsuarioED;
import com.master.ed.Vida_PneuED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.RequestUtil;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

/**
 * @author André Valadas , Cristian Vianna Garcia
 * - Movimento Pneus
 */
public class Movimento_PneuRN
extends Transacao {

	public Movimento_PneuRN () {

	}

	public Movimento_PneuED inclui (Movimento_PneuED ed) throws Excecoes {

		try {
			this.inicioTransacao ();
			ed = new Movimento_PneuBD (this.sql).inclui (ed);
			this.fimTransacao (true);
			return ed;
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public ArrayList lista (Movimento_PneuED ed) throws Excecoes {

		try {
			this.inicioTransacao ();
			ArrayList lista = new Movimento_PneuBD (sql).lista (ed);
			return lista;
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public ArrayList lista_nalthus (Movimento_PneuED ed) throws Excecoes {

		try {
			this.inicioTransacao ();
				ArrayList lista_nalthus = new Movimento_PneuBD (sql).lista_nalthus (ed);

				return lista_nalthus;
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public Movimento_PneuED getByRecord (Movimento_PneuED ed) throws Excecoes {

		try {
			this.inicioTransacao ();
			ed = new Movimento_PneuBD (this.sql).getByRecord (ed);
			return ed;
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public Movimento_PneuED verificaDestroca(Movimento_PneuED ed) throws Excecoes {

		try {
			this.inicioTransacao();
			Movimento_PneuED movED = new Movimento_PneuED();
			movED.setOid_Pneu_Colocado(ed.getOid_Pneu());
			movED.setOid_Veiculo(ed.getOid_Veiculo());
			movED.setDm_Posicao(ed.getDm_Posicao());
			movED.setNr_Odometro_Saida(ed.getNr_Odometro_Saida());
			movED =  new Movimento_PneuBD(this.sql).getByRecord1(movED);
			return movED;
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public Movimento_PneuED verificaEntrada(Movimento_PneuED ed) throws Excecoes {

		try {
			this.inicioTransacao();
			Movimento_PneuED movED = new Movimento_PneuED();
			movED.setOid_Pneu(ed.getOid_Pneu());
			movED.setOid_Veiculo(ed.getOid_Veiculo());
			movED.setDm_Posicao(ed.getDm_Posicao());
			movED.setNr_Odometro_Entrada(ed.getNr_Odometro_Entrada());
			movED =  new Movimento_PneuBD(this.sql).getByRecord1(movED);
			return movED;
		}
		finally {
			this.fimTransacao (false);
		}
	}


	public Movimento_PneuED verificaDesfazRodizio(Movimento_PneuED ed) throws Excecoes {

		try {
			this.inicioTransacao();
			Movimento_PneuED movED = new Movimento_PneuED();
			movED.setOid_Veiculo(ed.getOid_Veiculo());
			movED.setDm_Tipo_Movimento("R");
			movED = new Movimento_PneuBD(this.sql).getByRecord1(movED);
			return movED;
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public String verificaPneusDoRodizio(Movimento_PneuED ed) throws Excecoes {

		try {
			BancoUtil bu = new BancoUtil();
			String msg = "OK";
			this.inicioTransacao();
			Movimento_PneuED movED = new Movimento_PneuED();
			movED.setOid_Veiculo(ed.getOid_Veiculo());
			movED.setDt_Saida(ed.getDt_Saida());
			movED.setNr_Odometro_Saida(ed.getNr_Odometro_Saida());
			movED.setDm_Tipo_Movimento("R");
			ArrayList lst = new Movimento_PneuBD(this.sql).lista1(movED);
			// Verifica se cada um dos pneus do rodizio se mantem na mesma posição ainda.
			for (int i=0; i<lst.size(); i++){
				Movimento_PneuED edVolta = new Movimento_PneuED();
				edVolta = (Movimento_PneuED) lst.get(i);
				PneuED pneuED = new PneuED();
				pneuED.setOid_Pneu(edVolta.getOid_Pneu());
				pneuED = new PneuBD(this.sql).getByRecordOL(pneuED);
				if (!bu.doValida(pneuED.getDM_Posicao())) {
					msg="Rodízio impossível! Pneu " +edVolta.getNr_Fogo_Entrada()+" não está mais na posição "+edVolta.getDm_Posicao_Destino()+"!";
				} else
				if (!(pneuED.getDM_Posicao().equals(edVolta.getDm_Posicao_Destino()) && pneuED.getOid_Veiculo().equals(edVolta.getOid_Veiculo())) ) {
					msg="Rodízio impossível! Pneu " +edVolta.getNr_Fogo_Entrada()+" não está mais na posição "+edVolta.getDm_Posicao_Destino()+"!";
				}
			}
			return msg;
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public ArrayList lista1 (Movimento_PneuED ed) throws Excecoes {

		try {
			this.inicioTransacao ();
			ArrayList lista = new Movimento_PneuBD (sql).lista1 (ed);
			return lista;
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public ArrayList lista2 (Movimento_PneuED ed) throws Excecoes {

		try {
			this.inicioTransacao ();
			ArrayList lista = new Movimento_PneuBD (sql).lista2 (ed);
			return lista;
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public ArrayList lista3 (Movimento_PneuED ed) throws Excecoes {

		try {
			this.inicioTransacao ();
			ArrayList lista = new Movimento_PneuBD (sql).lista3 (ed);
			return lista;
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public ArrayList listaSaidaBaixa (Movimento_PneuED ed) throws Excecoes {

		try {
			this.inicioTransacao ();
			ArrayList lista = new Movimento_PneuBD (sql).listaSaidaBaixa (ed);
			return lista;
		}
		finally {
			this.fimTransacao (false);
		}
	}
	public void relatorio(Movimento_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		BancoUtil bu = new BancoUtil();
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ArrayList lstEdVazio = new ArrayList();

    		// Lista de hitorico de movimentação
    		ArrayList lista = new Movimento_PneuBD(sql).lista1(ed);
    		// Coloca a primeira lista
    		PneuED pneuLinha1 = new PneuED();
    		pneuLinha1.setSublista(lista); // MOVIMENTOS

    		//Lista de consertos
    		ConsertoED ConsED = new ConsertoED();
    		ConsED.setOid_Pneu_Conserto(ed.getOid_Pneu());
    		ConsED.setOid_Empresa(ed.getOid_Empresa());
    		ArrayList lstCons = new ConsertoBD(sql).lista(ConsED);
    		// Coloca a segunda linha
    		pneuLinha1.setSublista1(lstCons); // CONSERTO

    		//Lista de Vidas
    		Vida_PneuED VidED = new Vida_PneuED();
    		VidED.setOid_Pneu(ed.getOid_Pneu());
    		VidED.setOid_Empresa(ed.getOid_Empresa());
    		ArrayList lstVid = new Vida_PneuBD(sql).lista(VidED);
    		// Coloca a terceira lista
    		pneuLinha1.setSublista2(lstVid);

    		lstEdVazio.add(pneuLinha1);

    		// Montagem do ed "capa"
    		ed.setLista(lstEdVazio);
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns309");

    		if (bu.doValida(ed.getNr_Fogo()))
    			nm_Filtro+=" Pneu=" + ed.getNr_Fogo();
    		ed.setDescFiltro(nm_Filtro);

    		HashMap map = new HashMap();
    		PneuED PneuED = new PneuED();
    		PneuED.setOid_Pneu(ed.getOid_Pneu());
    		PneuED = new PneuBD(sql).getByRecordOL(PneuED);

    		map.put("nm_Fabricante_Pneu", String.valueOf(PneuED.getNm_Fabricante_Pneu()));
    		map.put("nm_Tipo_Pneu", String.valueOf(PneuED.getNm_Tipo_Pneu()));
    		map.put("nm_Dimensao_Pneu", String.valueOf(PneuED.getNm_Dimensao_Pneu()));
    		map.put("nm_Modelo_Pneu", String.valueOf(PneuED.getNm_Modelo_Pneu()));
    		map.put("nr_Km_Acumulada", String.valueOf(PneuED.getNr_Km_Acumulada()));
    		map.put("nr_Placa", String.valueOf(PneuED.getNr_Fogo()));
    		if (bu.doValida(PneuED.getDt_Entrada())){
    			map.put("msg", "O Pneu está no Veículo: " + PneuED.getNR_Frota() + " - Posição: " + PneuED.getDM_Posicao() + " - Entrada: " + PneuED.getDt_Entrada() + " - Hodômetro: " + FormataValor.formataValorBT(PneuED.getNr_Hodometro_Veiculo(),1) + " - KM Acumulada: " + FormataValor.formataValorBT(PneuED.getNr_Km_Acumulada(),1));
    		}else
    		if (bu.doValida(PneuED.getDt_Estoque())){
        		map.put("msg", "O Pneu está em Estoque - Data Entrada: " + PneuED.getDt_Estoque() + " - Local Estoque: " + PneuED.getNm_Local_Estoque());
        	}else
        	if (bu.doValida(PneuED.getDt_Entrada())){
            	map.put("msg", "O Pneu está no Recapador: " + PneuED.getNm_Fornecedor_Recapagem() + " - Data de Remessa: " + PneuED.getDt_Remessa_Recapagem() + " - Prometido para: " + PneuED.getDt_Promessa_Retorno_Recapagem());
            }else
            if (bu.doValida(PneuED.getDt_Sucateamento())){
            	map.put("msg", "O Pneu está Sucateado - Data Sucateamento: " + PneuED.getDt_Sucateamento() + " - Motivo: " + PneuED.getNm_Motivo_Sucateamento());
            }
            map.put("PATH_SUBLIST", Parametro_FixoED.getInstancia().getPATH_RELATORIOS());
    		ed.setHashMap(map);

    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorio2(Movimento_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		BancoUtil bu = new BancoUtil();
    		int i = 0 ;
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ArrayList lista = new Movimento_PneuBD(sql).lista1(ed);
    		ed.setLista(lista);
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns309_Veic");
    		if (bu.doValida(ed.getNr_Frota()))
    			nm_Filtro+=" Veículo=" + ed.getNr_Frota();
    		if (bu.doValida(ed.getDt_Inicial()))
    			nm_Filtro+=" Data Inicial=" + ed.getDt_Inicial();
    		if (bu.doValida(ed.getDt_Final()))
    			nm_Filtro+=" Data Final=" + ed.getDt_Final();
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorio3(Movimento_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		BancoUtil bu = new BancoUtil();
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ArrayList lista = new Movimento_PneuBD(sql).lista2(ed);
    		ed.setLista(lista);
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns313");
    		if (bu.doValida(ed.getDt_Inicio()))
    			nm_Filtro+=" De = " + ed.getDt_Inicio();
    		if (bu.doValida(ed.getDt_Fim()))
    			nm_Filtro+=" Até = " + ed.getDt_Fim();
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorio4(Movimento_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		BancoUtil bu = new BancoUtil();
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ArrayList lista = new Movimento_PneuBD(sql).lista3(ed);
    		ed.setLista(lista);
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns316");
    		if (bu.doValida(ed.getDt_Inicial_Saida()))
    			nm_Filtro+=" De = " + ed.getDt_Inicial_Saida();
    		if (bu.doValida(ed.getDt_Final_Saida()))
    			nm_Filtro+=" Até = " + ed.getDt_Final_Saida();
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorioSaidaBaixa(Movimento_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		BancoUtil bu = new BancoUtil();
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ArrayList lista = new Movimento_PneuBD(sql).listaSaidaBaixa(ed);
    		ed.setLista(lista);
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns324"); // Seta o nome do relatório
    		// Monta a descricao do filtro utilizado
    		if (bu.doValida(ed.getDt_Inicial()))
    			nm_Filtro+=" De = " + ed.getDt_Inicial();
    		if (bu.doValida(ed.getDt_Final()))
    			nm_Filtro+=" Até = " + ed.getDt_Final();
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }


    /**
     * processaRL
     * Processa solicitação de relatorio OL retornando sempre um PDF.
     * @param rel = Qual o relatorio a ser chamado
     * @param Obj = Um bean populado com parametros para a geracao do relatorio
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws Excecoes
     */
    public void processaRL(String rel, Object Obj, HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException, Excecoes {
    	//Extrai o bean com os campos da request colados
    	Movimento_PneuED ed = (Movimento_PneuED)Obj;
    	ed.setRequest(request);
		if ("1".equals(rel)) {
			this.relatorio(ed, request, response);
		}
		if ("2".equals(rel)) {
			this.relatorio2(ed, request, response);
		}
		if ("3".equals(rel)) {
			this.relatorio3(ed, request, response);
		}
		if ("4".equals(rel)) {
			this.relatorio4(ed, request, response);
		}
		if ("5".equals(rel)) {
			this.relatorioSaidaBaixa(ed, request, response);
		}
    }

    /**
     * processaOL
     * Processa solicitação de tela OL retornando sempre arquivo XML com a seguinte estrutura.
     * <cad>
     * 		<item campo=valor />
     * </cad>
     * @param acao
     * @param Obj
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws Excecoes
     */
    public void processaOL(String acao, Object Obj, HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException, Excecoes {
    	//Extrai o bean com os campos da request colados
    	Movimento_PneuED ed = (Movimento_PneuED)Obj;
    	//Prepara a saída
    	ed.setMasterDetails(request);

    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");

		if ("VT".equals(acao)) { // Verifica se o pneu foi colocado no lugar de outro numa troca
			Movimento_PneuED edVolta = new Movimento_PneuED();
			edVolta = this.verificaDestroca(ed);
			if (edVolta.getOid_Pneu()>0) {
				if ("R".equals(edVolta.getDm_Tipo_Movimento())) {
					edVolta.setTX_Observacao("Troca em rodízio! Desfaça o último rodízio do veículo!");
				} else {
					// Busca a situacao do pneu
					PneuED pnED = new PneuED();
					pnED.setOid_Pneu(edVolta.getOid_Pneu());
					pnED.setNr_Fogo(edVolta.getNr_Fogo_Saida());
					PneuRN pnRN = new PneuRN();
					ArrayList lst = pnRN.statusPneu(pnED);
					if (!"E".equals((String)lst.get(0))) // Se NÃO estiver no estoque anota na observação para dar msg lá na tela
						edVolta.setTX_Observacao((String)lst.get(1) + " !");

				}
				out.println("<cad>");
				out.println(montaRegistro(edVolta));
				out.println("</cad>");
			} else {
				// Vai buscar os dados da montagem do pneu para pegar o estoque de onde foi retirado e mostrar na tela
				ed.setNr_Odometro_Entrada(ed.getNr_Odometro_Saida());
				edVolta = this.verificaEntrada(ed);
				Local_EstoqueED leED = new Local_EstoqueED();
				leED.setOid_Local_Estoque(edVolta.getOid_Local_Estoque());
				Local_EstoqueRN leRN = new Local_EstoqueRN();
				leED = leRN.getByRecord(leED);
				BancoUtil bu = new BancoUtil();
				if (bu.doValida(leED.getNm_Local_Estoque()))
					out.println("<ret><item oknok='" + leED.getNm_Local_Estoque().trim() + " - " +leED.getNm_Unidade().trim() + "' /></ret>");
				else
					out.println("<ret><item oknok='Estoque' /></ret>");
			}
		} else
		if ("VR".equals(acao)) { // Verifica a possibilidade de desfazer o rodízio
			Movimento_PneuED edVolta = new Movimento_PneuED();
			edVolta = this.verificaDesfazRodizio(ed);
			if (edVolta.getOid_Pneu()==0)
				out.println("<ret><item oknok='Não há rodízio para este veículo! '/></ret>");
			else {
				String saida="<ret>" +
							 "<item " +
							 "oknok='"+this.verificaPneusDoRodizio(edVolta)+"' "+
							 "dt_Saida='"+edVolta.getDt_Saida()+"' "+
							 "nr_Odometro_Saida='"+FormataValor.formataValorBT(edVolta.getNr_Odometro_Saida(),1)+"' />" +
							 "</ret>";
				out.println(saida);
			}
		} else {
			out.println("<cad>");
			String saida=null;
			ArrayList lst = new ArrayList();
			if ("L".equals(acao)) {
				lst = this.lista1(ed);
			}
			if ("BK".equals(acao)) {
				lst = this.listaSaidaBaixa(ed);
			}
			if ("LPC".equals(acao)) {
				lst = this.lista2(ed);
			}
			if ("LPR".equals(acao)) {
				lst = this.lista3(ed);
			}
			for (int i=0; i<lst.size(); i++){
				Movimento_PneuED edVolta = new Movimento_PneuED();
				edVolta = (Movimento_PneuED)lst.get(i);
				if ("BK".equals(acao) ||"L".equals(acao) || "LPC".equals(acao) || "LPR".equals(acao)) {
					out.println(montaRegistro(edVolta));
				}
				out.println(saida);
			}
			out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    public void processaRelatorio(String rel, Movimento_PneuED ed,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Excecoes {
		// Extrai o bean com os campos da request colados
		ed.setRequest(request);
		UsuarioED user = (UsuarioED) RequestUtil.getSessionAttribute(request,
				"usuario");
		ed.setOid_Empresa(user.getOid_Empresa());
		ed.setUser(user.getOid_Usuario().intValue());
		ed.setUsuario_Stamp(user.getNm_Usuario());
		ed.setTime_millis(System.currentTimeMillis());

		if ("1".equals(rel)) {
			this.relatorio(ed, request, response);
		}
		if ("2".equals(rel)) {
			this.relatorio2(ed, request, response);
		}
		if ("3".equals(rel)) {
			ed.setDt_Inicio(ed.getDt_Inicial());
			ed.setDt_Fim(ed.getDt_Final());
			this.relatorio3(ed, request, response);
		}
		if ("4".equals(rel)) {
			ed.setDt_Inicial_Saida(ed.getDt_Inicial());
			ed.setDt_Final_Saida(ed.getDt_Final());
			this.relatorio4(ed, request, response);
		}
		if ("5".equals(rel)) {
			this.relatorioSaidaBaixa(ed, request, response);
		}
	}

    public void processaRequisicao(Movimento_PneuED ed, HttpServletRequest request) throws ServletException, IOException,
			Excecoes {

    	Object toReturn = null;
		// PADRAO PARA TODAS...
		String acao = JavaUtil.getValueDef(request.getParameter("acao"), "M");
		UsuarioED user = (UsuarioED) RequestUtil.getSessionAttribute(request,
				"usuario");
		ed.setOid_Empresa(user.getOid_Empresa());
		ed.setUser(user.getOid_Usuario().intValue());
		ed.setUsuario_Stamp(user.getNm_Usuario());
		ed.setDm_Stamp(acao);
		ed.setTime_millis(System.currentTimeMillis());
		System.out.println(acao);

		if ("VT".equals(acao)) { // Verifica se o pneu foi colocado no lugar
									// de outro numa troca
			Movimento_PneuED edVolta = new Movimento_PneuED();
			edVolta = this.verificaDestroca(ed);
			if (edVolta.getOid_Pneu() > 0) {
				if ("R".equals(edVolta.getDm_Tipo_Movimento())) {
					edVolta.setTX_Observacao("Troca em rodízio! Desfaça o último rodízio do veículo!");
				} else {
					// Busca a situacao do pneu
					PneuED pnED = new PneuED();
					pnED.setOid_Pneu(edVolta.getOid_Pneu());
					//pnED.setNr_Fogo(edVolta.getNr_Fogo_Saida());
					PneuRN pnRN = new PneuRN();
					ArrayList lst = pnRN.statusPneuJSTL(pnED, acao);
					if (!"E".equals((String) lst.get(0))) {
						edVolta.setTX_Observacao((String) lst.get(1) + " !");
						toReturn = (String) lst.get(1) + " !";
					}
				}

			} else {
				// Vai buscar os dados da montagem do pneu para pegar o estoque
				// de onde foi retirado e mostrar na tela
				ed.setNr_Odometro_Entrada(ed.getNr_Odometro_Saida());
				edVolta = this.verificaEntrada(ed);
				Local_EstoqueED leED = new Local_EstoqueED();
				leED.setOid_Local_Estoque(edVolta.getOid_Local_Estoque());
				Local_EstoqueRN leRN = new Local_EstoqueRN();
				leED = leRN.getByRecord(leED);
				String estoque = "";
				if(JavaUtil.doValida(leED.getNm_Local_Estoque())){
					estoque = leED.getNm_Local_Estoque();
					if(JavaUtil.doValida(leED.getNm_Unidade()))
						estoque += " / " + leED.getNm_Unidade();
				} else {
					estoque = "Estoque";
				}
				request.setAttribute("estoque", estoque);
//				BancoUtil bu = new BancoUtil();

			}
			request.setAttribute("msg", toReturn);
			request.setAttribute("movimento_pneu", edVolta);
		} else if ("VR".equals(acao)) { // Verifica a possibilidade de desfazer
										// o rodízio
			Movimento_PneuED edVolta = new Movimento_PneuED();
			edVolta = this.verificaDesfazRodizio(ed);
			if (edVolta.getOid_Pneu() == 0)
				toReturn = "Não há rodízio para este veículo!";
			else {
				toReturn = this.verificaPneusDoRodizio(edVolta);
			}
			request.setAttribute("msg", toReturn);
			request.setAttribute("movimento_pneu", edVolta);
		} else {
			String saida = null;
			ArrayList lst = new ArrayList();
			if ("L".equals(acao)) {
				lst = this.lista1(ed);
			}
			if ("BK".equals(acao)) {
				lst = this.listaSaidaBaixa(ed);
			}
			if ("LPC".equals(acao)) {
				lst = this.lista2(ed);
			}
			if ("LPR".equals(acao)) {
				lst = this.lista3(ed);
			}
			request.setAttribute("lista", lst);
		}
	}

    private String montaRegistro(Movimento_PneuED edVolta) {
    	String saida;
		saida =  "<item ";
		saida += "oid_Movimento_Pneu ='" + edVolta.getOid_Movimento_Pneu() + "' ";
		saida += "oid_Pneu='" + edVolta.getOid_Pneu() + "' ";
		saida += "oid_Veiculo='" + edVolta.getOid_Veiculo() + "' ";
		saida += "nr_Odometro_Entrada='" + FormataValor.formataValorBT(edVolta.getNr_Odometro_Entrada(),1) + "' ";
		saida += "nr_Km_Acumulada_Entrada='" + FormataValor.formataValorBT(edVolta.getNr_Km_Acumulada_Entrada(),1)  + "' ";
		saida += "nr_Km_Acumulada_Veiculo='" + FormataValor.formataValorBT(edVolta.getNr_Km_Acumulada_Veiculo(),1)  + "' ";
		saida += "dt_Saida='" + edVolta.getDt_Saida() + "' ";
		saida += "dt_Entrada='" + edVolta.getDt_Entrada() + "' ";
		saida += "nr_Odometro_Saida='" + FormataValor.formataValorBT(edVolta.getNr_Odometro_Saida(),1) + "' ";
		saida += "nr_Km_Acumulada_Saida='" + FormataValor.formataValorBT(edVolta.getNr_Km_Acumulada_Saida(),1)  + "' ";
		saida += "dm_Posicao='" + edVolta.getDm_Posicao() + "' ";
		saida += "dm_Eixo='" + edVolta.getDm_Eixo() + "' ";
		saida += "dm_Posicao_Destino='" + edVolta.getDm_Posicao_Destino() + "' ";
		saida += "dm_Rodou_Pouco='" + edVolta.getDm_Rodou_Pouco() + "' ";
		saida += "nr_Fogo='" + edVolta.getNr_Fogo() + "' ";
		saida += "nm_Vida='" + edVolta.getNm_Vida() + "' ";
		saida += "vl_Pneu='" + FormataValor.formataValorBT(edVolta.getVl_Pneu(),2) + "' ";
		saida += "dt_Nota_Fiscal='" + edVolta.getDt_Nota_Fiscal() + "' ";
		saida += "nm_Razao_Social='" + edVolta.getNm_Razao_Social() + "' ";
		saida += "nm_Dimensao_Pneu='" + edVolta.getNm_Dimensao_Pneu() + "' ";
		saida += "nm_Fabricante_Pneu='" + edVolta.getNm_Fabricante_Pneu() + "' ";
		saida += "nm_Modelo_Pneu='" + edVolta.getNm_Modelo_Pneu() + "' ";
		if (JavaUtil.doValida(edVolta.getTx_Motivo())) {
			saida += "tx_Motivo='" + edVolta.getTx_Motivo() + "' ";
		}else{
			saida += "tx_Motivo='" + "" + "' ";
		}
		saida += "tx_Observacao='" + edVolta.getTX_Observacao() + "' ";
		saida += "oid_Pneu_Colocado='" + edVolta.getOid_Pneu_Colocado() + "' ";
		saida += "nr_Vida='" + edVolta.getNr_Vida() + "' ";
		saida += "nr_Fogo_Entrada='" + edVolta.getNr_Fogo_Entrada() + "' ";
		saida += "nr_Fogo_Saida='" + edVolta.getNr_Fogo_Saida() + "' ";
		saida += "nr_Frota='" + edVolta.getNr_Frota() + "' ";
		saida += "nr_Km_Veiculo='" + FormataValor.formataValorBT(edVolta.getNr_Km_Veiculo(),1)  + "' ";
		saida += "/>";
		return saida;
    }

	protected void finalize () throws Throwable {
		if (this.sql != null) {
			this.abortaTransacao ();
		}
	}
}