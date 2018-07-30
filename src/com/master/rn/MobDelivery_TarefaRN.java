/*
 * Created on 12/11/2004
 *
 */
package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.ColetaBD;
import com.master.bd.MobDelivery_ExecutorBD;
import com.master.bd.MobDelivery_Grupo_TarefaBD;
import com.master.bd.MobDelivery_MensagemBD;
import com.master.bd.MobDelivery_SituacaoBD;
import com.master.bd.MobDelivery_TarefaBD;
import com.master.bd.MobDelivery_UnidadeBD;
import com.master.bd.Ocorrencia_ConhecimentoBD;
import com.master.ed.ColetaED;
import com.master.ed.MobDelivery_ExecutorED;
import com.master.ed.MobDelivery_Grupo_TarefaED;
import com.master.ed.MobDelivery_MensagemED;
import com.master.ed.MobDelivery_SituacaoED;
import com.master.ed.MobDelivery_TarefaED;
import com.master.ed.MobDelivery_UnidadeED;
import com.master.ed.Ocorrencia_ConhecimentoED;
import com.master.rl.JasperRL;
import com.master.root.Tipo_OcorrenciaBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Tarefas no MobDelivery
 * @serialData 06/2007
 */
public class MobDelivery_TarefaRN extends Transacao {

	public MobDelivery_TarefaED inclui (MobDelivery_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_TarefaED toReturn = new MobDelivery_TarefaBD (this.sql).inclui (ed);
			this.fimTransacao (true);
			return toReturn;
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void altera (MobDelivery_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_TarefaBD (this.sql).altera (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void delete (MobDelivery_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_TarefaBD (this.sql).delete (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void registraEnvio (MobDelivery_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_TarefaBD (this.sql).registraEnvio (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void registraRecebimento (MobDelivery_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_TarefaBD (this.sql).registraRecebimento (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void baixaColeta (MobDelivery_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_TarefaBD (this.sql).baixaTarefa (ed);
			// Pega a tarefa de novo já atualizada
			ed = new MobDelivery_TarefaBD(this.sql).getByRecord(ed);
			
			//this.baixaColetaNalthus(ed);
			
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	/** Baixa COLETA no Nalthus
	 * @param ed
	 * @throws Excecoes
	 */
	private void baixaColetaNalthus(MobDelivery_TarefaED ed) throws Excecoes {
		
		ColetaED colED = new ColetaED();
		colED.setOID_Coleta(ed.getOid_Conhecimento());
		colED.setDT_Coletado(ed.getDt_Execucao());
		colED.setHR_Coletado(ed.getHr_Execucao());
		new ColetaBD(this.sql).finaliza_coleta(colED);
		
	}
	
	public void baixaEntrega (MobDelivery_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_TarefaBD (this.sql).baixaTarefa (ed);
			// Pega a tarefa de novo já atualizada
			ed = new MobDelivery_TarefaBD(this.sql).getByRecord(ed);
			
			//this.baixaEntregaNalthus(ed);
			
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	/** Baixa ENTREGA no Nalthus
	 * @param ed
	 * @throws Excecoes
	 */
	private void baixaEntregaNalthus(MobDelivery_TarefaED ed) throws Excecoes {
		// Da baixa no nalthus
		int oid_Ocorrencia;
		// Busca a situação para o nalthus no de/para da tabela de situação
		MobDelivery_SituacaoED sitED = new MobDelivery_SituacaoED();
		sitED.setCd_Situacao(ed.getDm_Situacao());
		sitED = new MobDelivery_SituacaoBD(this.sql).getByRecord(sitED);
		BancoUtil bu = new BancoUtil();
		// Se a situação está cadastrada no de/para vai edar baixa no nalthus
		if (bu.doValida(sitED.getCd_Externo())) {
			// Busca o tipo de ocorrencia pelo codigo do de/para
			Tipo_OcorrenciaBean tpOco;
			try {tpOco = Tipo_OcorrenciaBean.getByCD_Tipo_Ocorrencia(sitED.getCd_Externo());
				 oid_Ocorrencia = tpOco.getOID();
			} catch (Exception e) {oid_Ocorrencia = 0 ;}
			
			MobDelivery_ExecutorED exeED = new MobDelivery_ExecutorED();
			exeED.setOid_Executor(ed.getOid_Executor());
			exeED = new MobDelivery_ExecutorBD(this.sql).getByRecord(exeED);
			
			//Dá baixa no nalthus
			Ocorrencia_ConhecimentoED ocoED = new Ocorrencia_ConhecimentoED();
			ocoED.setOID_Conhecimento(ed.getOid_Conhecimento());
			ocoED.setDT_Ocorrencia_Conhecimento(ed.getDt_Execucao());
			ocoED.setHR_Ocorrencia_Conhecimento(ed.getHr_Execucao());
			ocoED.setNM_Pessoa_Entrega(ed.getNm_Agente().toUpperCase() + " RG:" + ed.getTx_Rg_Agente().toUpperCase());
			ocoED.setTX_Observacao("Reg.p/celular, operador=" + exeED.getNm_Executor());
			ocoED.setOID_Tipo_Ocorrencia(oid_Ocorrencia);
			new Ocorrencia_ConhecimentoBD(this.sql).inclui(ocoED);
		}
	}
	

	public void registraInicioDia (MobDelivery_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_ExecutorED execED = new MobDelivery_ExecutorED();
			execED.setOid_Executor(ed.getOid_Executor());
			execED.setDt_Dia(ed.getDt_Dia());
			new MobDelivery_ExecutorBD(this.sql).registraInicioDia (execED);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void registraFechamentoDia (MobDelivery_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_ExecutorED execED = new MobDelivery_ExecutorED();
			execED.setOid_Executor(ed.getOid_Executor());
			new MobDelivery_ExecutorBD(this.sql).registraFechamentoDia(execED);
			new MobDelivery_TarefaBD (this.sql).registraFechamentoDia (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void limpaProtocolo (MobDelivery_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_TarefaBD (this.sql).limpaProtocolo (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public ArrayList listaTarefas (MobDelivery_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_TarefaBD (this.sql).lista (ed,"nr_ordem_alocado");
		}
		finally {
			this.fimTransacao (false);
		}
	}
	
	public ArrayList listaTarefasCel (MobDelivery_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_TarefaBD (this.sql).lista (ed,"nr_ordem_alocado");
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public ArrayList listaMensagens (MobDelivery_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_MensagemED edM = new MobDelivery_MensagemED();
			edM.setOid_Executor(ed.getOid_Executor());
			edM.setDm_Protocolo(ed.getDm_Protocolo());
			edM.setNr_Protocolo(ed.getNr_Protocolo());
			return new MobDelivery_MensagemBD (this.sql).lista (edM);
		}
		finally {
			this.fimTransacao (false);
		}
	}

	
	public ArrayList listaCompleta (MobDelivery_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_TarefaBD (this.sql).listaCompleta(ed);
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public MobDelivery_TarefaED getByRecord (MobDelivery_TarefaED ed ) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_TarefaBD (this.sql).getByRecord (ed);
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public void alocaTarefas (MobDelivery_TarefaED ed ) throws Excecoes {
		long nr_Ordem_Alocado; 
		this.inicioTransacao ();
		try {
			MobDelivery_TarefaED edQ = new MobDelivery_TarefaED();
			edQ.setOid_Grupo_Tarefa(ed.getOid_Grupo_Tarefa());
			//edQ.setOid_Tarefa(ed.getOid_Tarefa());
			MobDelivery_TarefaBD tBD = new MobDelivery_TarefaBD(this.sql);
			// Busca o 'ultimo alocado para este executor nesta data
			nr_Ordem_Alocado = tBD.getUltimoNrAlocado(ed);
			edQ.setOid_Executor(-1); // Para buscar somente tarefas não alocadas deste grupo
			ArrayList lst = tBD.lista(edQ, "nr_ordem");
			edQ=null;
			for (int i=0; i<lst.size(); i++){
				MobDelivery_TarefaED edVolta = new MobDelivery_TarefaED();
				edVolta = (MobDelivery_TarefaED)lst.get(i);
				edVolta.setOid_Executor(ed.getOid_Executor());
				nr_Ordem_Alocado=nr_Ordem_Alocado+100;
				edVolta.setNr_Ordem_Alocado(nr_Ordem_Alocado);
				// Grava a alocação no executor
				tBD.alocaTarefa(edVolta);
				edVolta=null;
			}
			tBD=null;
		}
		finally {
			this.fimTransacao (true);
		}
	}

	public void deAlocaTarefas (MobDelivery_TarefaED ed ) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_TarefaBD tBD = new MobDelivery_TarefaBD(this.sql);
			ed.setDm_Situacao("0"); // Dealoca somente as tarefas ainda pendentes
			ArrayList lst = tBD.lista(ed, "nr_ordem" );
			for (int i=0; i<lst.size(); i++){
				MobDelivery_TarefaED edVolta = new MobDelivery_TarefaED();
				edVolta = (MobDelivery_TarefaED)lst.get(i);
				// Grava a dealocação do executor
				tBD.deAlocaTarefa(edVolta);
				edVolta=null;
			}
		}
		finally {
			this.fimTransacao (true);
		}
	}
	
	public void deAlocaTarefasNovoGrupo (MobDelivery_TarefaED ed ) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_TarefaBD tBD = new MobDelivery_TarefaBD(this.sql);
			MobDelivery_Grupo_TarefaBD gtBD = new MobDelivery_Grupo_TarefaBD(this.sql);
			
			// Busca a tarefa.
			MobDelivery_TarefaED tED = new MobDelivery_TarefaED();
			tED.setOid_Tarefa(ed.getOid_Tarefa());
			tED=tBD.getByRecord(tED);
			long oidExecutor = tED.getOid_Executor();
			
			// Busca o grupo de tarefas da tarefa e cria um novo grupo a partir dele.
			MobDelivery_Grupo_TarefaED gtED = new MobDelivery_Grupo_TarefaED();
			gtED.setOid_Grupo_Tarefa(tED.getOid_Grupo_Tarefa());
			gtED=gtBD.getByRecord(gtED);
			gtED.setOid_Grupo_Tarefa(0);
			gtED.setNm_Grupo_Tarefa("* " + gtED.getNm_Grupo_Tarefa());
			gtED=gtBD.inclui(gtED);
			
			// Dealoca a tarefa do executor e troca de grupo para o grupo criado acima
			tED.setOid_Grupo_Tarefa(gtED.getOid_Grupo_Tarefa());
			tBD.altera(tED);
			tBD.deAlocaTarefa(tED);
			
			//Marca o executor como tendo tarefas dealocadas e por isso deverá ser executado um "T" no celular na proxima atualização.
			MobDelivery_ExecutorED exeED = new MobDelivery_ExecutorED();
			exeED.setOid_Executor(oidExecutor);
			exeED.setDm_Reenviar("S");
			new MobDelivery_ExecutorBD(this.sql).registraDmReenvio(exeED); 
		}
		finally {
			this.fimTransacao (true);
		}
	}

	public void reposicionaTarefa (MobDelivery_TarefaED ed ) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_TarefaBD tBD = new MobDelivery_TarefaBD(this.sql);
			// Pega o arrastado
			MobDelivery_TarefaED arrastado = new MobDelivery_TarefaED();
			arrastado.setOid_Tarefa(ed.getOid_Grupo_Tarefa());
			arrastado = tBD.getByRecord(arrastado);
			//Pega sobre qual foi arrastado e solto
			MobDelivery_TarefaED porSobre = new MobDelivery_TarefaED();
			porSobre.setOid_Tarefa(ed.getOid_Tarefa());
			porSobre = tBD.getByRecord(porSobre);
			// pega o proximo ou anterior ao por sobre o qual foi arrastando, dependendo de dm_situacao ( lado ) Esquerda ou Direita
			MobDelivery_TarefaED tAnteProx = new MobDelivery_TarefaED();
			porSobre.setDm_Situacao(ed.getDm_Situacao());
			tAnteProx = tBD.getAnteriorProximo(porSobre);
			// Calcula o novo número de ordem de alocação 
			long novo_Ordem_Alocado =0;
			if ("E".equals(ed.getDm_Situacao())) {
				novo_Ordem_Alocado = tAnteProx.getNr_Ordem_Alocado() + ((porSobre.getNr_Ordem_Alocado() - tAnteProx.getNr_Ordem_Alocado() )/2); 
			} else {
				if (tAnteProx.getNr_Ordem_Alocado()==0) tAnteProx.setNr_Ordem_Alocado(porSobre.getNr_Ordem_Alocado()+100);
				novo_Ordem_Alocado = porSobre.getNr_Ordem_Alocado() + ((tAnteProx.getNr_Ordem_Alocado() - porSobre.getNr_Ordem_Alocado() )/2);
			}
			// Grava o arrastado com o novo numero de ordem de alocação 
			if (tAnteProx.getNr_Ordem_Alocado() != arrastado.getNr_Ordem_Alocado() ) {
				arrastado.setNr_Ordem_Alocado(novo_Ordem_Alocado);
				tBD.reposicionaTarefa(arrastado);
			}
		}
		finally {
			this.fimTransacao (true);
		}
	}

	
    public String graficoDesempenho(MobDelivery_TarefaED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	String string = null;
    	string="Desempenho de Entregadores";
        try {
            this.inicioTransacao();
    		String nm_Filtro = "";
    		// Monta o filtro e a descricao do filtro utilizado
	    	if (ed.getOid_Unidade()>0 ) { 
	    		MobDelivery_UnidadeED unED = new MobDelivery_UnidadeED();
	    		unED.setOid_Unidade(ed.getOid_Unidade());
	    		unED = new MobDelivery_UnidadeBD(this.sql).getByRecord(unED);
	    		nm_Filtro+=" Unidade=" + unED.getNm_Unidade();
	    	}	
	    	// Cabeçalho do xml do gráfico 	
	    	string+="<graph " +
	    			"bgColor='EAEAEA' " +
	    			"caption='" + nm_Filtro + "' " + 
	    			"subCaption='Período "+ ed.getDt_Inicial() + " até " + ed.getDt_Final()+ "'  "+
	    			"xAxisName='Executores' " +
	    			"yAxisName='Desempenho' " +
	    			"decimalPrecision='1' " +
	    			"formatNumberScale='0'  " +
	    			"decimalSeparator=',' " +
	    			"numberSuffix='%25' " +
	    			"showNames='1' " +
	    			"showValues='0' " +
	    			"formatNumberScale='0' " +
	    			"numDivLines='9' " +
	    			"yAxisMaxValue='100'" +
	    			"rotateNames='1' >";
	    	ArrayList lista = new MobDelivery_TarefaBD(sql).desempenhoExecutores(ed,"gra");
	    	
	    	string+="<categories> " ;
            for (int i=0; i<lista.size(); i++){
            	MobDelivery_TarefaED edVolta = new MobDelivery_TarefaED();
				edVolta = (MobDelivery_TarefaED)lista.get(i);
				string+="<category name='" + edVolta.getNm_Executor() + "' /> " ;
            }	
            string+="</categories> " ;
            
            string+="<dataset seriesName='Entregues'  color='83FF83' showValues='0'> " ;
            for (int i=0; i<lista.size(); i++){
            	MobDelivery_TarefaED edVolta = new MobDelivery_TarefaED();
				edVolta = (MobDelivery_TarefaED)lista.get(i);
				string+="<set value='" + ((double)edVolta.getNr_Tarefas_Executadas()/(double)edVolta.getNr_Total_Tarefas()*100) + "' /> " ;
				//string+="<set value='" + "50.0" + "' /> " ;
            }	
            string+="</dataset> " ;

            string+="<dataset seriesName='Não Entregues' color='FF8383' showValues='0'> " ;
            for (int i=0; i<lista.size(); i++){
            	MobDelivery_TarefaED edVolta = new MobDelivery_TarefaED();
				edVolta = (MobDelivery_TarefaED)lista.get(i);
				string+="<set value='" 	+ ((double)edVolta.getNr_Tarefas_N_Executadas()/(double)edVolta.getNr_Total_Tarefas()*100) + "' /> " ;
            }	
            string+="</dataset> " ;

    		//			"color='"+new Utilitaria().getCorGrafico(i)+"'/>";
            string+="</graph>";
        } finally {
            this.fimTransacao(false);
        }

    	return string;
    }


    public void relatorioDesempenho(MobDelivery_TarefaED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new MobDelivery_TarefaBD(sql).desempenhoExecutores(ed, "rel");
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório 
			ed.setResponse(response);
			ed.setNomeRelatorio("mob501"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			nm_Filtro = "Unidade: " + ed.getNm_Unidade() + " ";
			nm_Filtro +="Período: de " + ed.getDt_Inicial() + " até " + ed.getDt_Final();
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
    public void processaRL(String rel, Object Obj, HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, Excecoes {
    	//Extrai o bean com os campos da request colados
    	MobDelivery_TarefaED ed = (MobDelivery_TarefaED)Obj;
    	ed.setRequest(request);
    	//if ("1".equals(rel)) {
    	this.relatorioDesempenho(ed, request, response);	
    	//} 
    }
	
	/**
	 * processaGR
	 * Processa solicitação de relatorio OL retornando sempre um PDF.
	 * @param rel = Qual o relatorio a ser chamado
	 * @param Obj = Um bean populado com parametros para a geracao do relatorio
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws Excecoes
	 */

	public String processaGR(String acao, Object Obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Excecoes {
		//Extrai o bean com os campos da request colados
		MobDelivery_TarefaED ed = (MobDelivery_TarefaED)Obj;
		return this.graficoDesempenho(ed, request, response);	
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
		MobDelivery_TarefaED ed = (MobDelivery_TarefaED)Obj;
		//Prepara a saída
		ed.setMasterDetails(request);
		
		PrintWriter out = response.getWriter();
		out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
		if ("I".equals(acao) ) {
			ed = this.inclui(ed);
			out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Grupo_Tarefa() + "' /></ret>");
		} else 
		if ("A".equals(acao)) {
			this.altera(ed);
			out.println("<ret><item oknok='AOK' /></ret>");
		} else 
		if ("D".equals(acao)) {
			this.delete(ed);
			out.println("<ret><item oknok='DOK' /></ret>");
		} else 
		if ("AT".equals(acao)) {
			this.alocaTarefas(ed);
			out.println("<ret><item oknok='ALOK' /></ret>");
		} else 
		if ("DAT".equals(acao)) {
			this.deAlocaTarefas(ed);
			out.println("<ret><item oknok='DATOK' /></ret>");
		} else 
		if ("DATNG".equals(acao)) {
			this.deAlocaTarefasNovoGrupo(ed);
			out.println("<ret><item oknok='DATNGOK' /></ret>");
		} else 
		if ("RT".equals(acao)) {
			this.reposicionaTarefa(ed);
			out.println("<ret><item oknok='RTOK' /></ret>");
		} else 
		if ("LTE".equals(acao)) {
			MontaXmlLTE(ed, out);
		} else {
			out.println("<cad>");
			String saida = null;
			ArrayList lst = new ArrayList();
			lst = this.listaTarefas(ed);
			for (int i=0; i<lst.size(); i++){
				MobDelivery_TarefaED edVolta = new MobDelivery_TarefaED();
				edVolta = (MobDelivery_TarefaED)lst.get(i);
				if ("L".equals(acao)) {
					saida = "<item ";
					saida += "oid_Tarefa='" + edVolta.getOid_Tarefa() + "' ";
					saida += "oid_Grupo_Tarefa='" + edVolta.getOid_Grupo_Tarefa() + "' ";
					saida += "oid_Unidade='" + edVolta.getOid_Unidade() + "' ";
					saida += "nr_Tarefa='" + edVolta.getNr_Tarefa() + "' ";
					saida += "nr_Volume='" + FormataValor.formataValorBT(edVolta.getNr_Volume(),0) + "' ";
					saida += "nm_Endereco='" + JavaUtil.preperaString(edVolta.getNm_Endereco()) + "' ";
					saida += "nm_Nome='" + JavaUtil.preperaString(edVolta.getNm_Nome()) + "' ";
					saida += "dm_Situacao='" + edVolta.getDm_Situacao() + "' ";
					saida += "nm_Situacao='" + edVolta.getNm_Situacao() + "' ";
					saida += "tx_Rg_Agente='" + edVolta.getTx_Rg_Agente() + "' ";
					saida += "nm_Agente='" + edVolta.getNm_Agente() + "' ";
					saida += "dt_Execucao='" + edVolta.getDt_Execucao() + "' ";
					saida += "hr_Execucao='" + edVolta.getHr_Execucao() + "' ";
					saida += "hr_Inicio_Execucao='" + edVolta.getHr_Inicio_Execucao() + "' ";
					saida += "hr_Fim_Execucao='" + edVolta.getHr_Fim_Execucao() + "' ";
					saida += "nr_Odometro='" + edVolta.getNr_Odometro() + "' ";
					saida += "oid_Executor='" + edVolta.getOid_Executor() + "' ";
					saida += "dm_Tipo='" + edVolta.getDm_Tipo() + "' ";
					saida += "dm_Protocolo='" + edVolta.getDm_Protocolo() + "' ";
					saida += "nr_Protocolo='" + edVolta.getNr_Protocolo() + "' ";
					saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
					saida += "/>";
				} 
				edVolta = null;
				out.println(saida);
			}
			out.println("</cad>");
		}
		out.flush();
		out.close();
	}

	/**
	 * @param ed - Ed montado para a consulta - Vem da tela laszlo
	 * @param out - String para escrita de saida
	 * @throws Excecoes
	 */
	private void MontaXmlLTE(MobDelivery_TarefaED ed, PrintWriter out) throws Excecoes {
		long cmp_Oid_Executor = 0 ; 
		String saida = null;
		ArrayList lst = new ArrayList();
		lst = this.listaCompleta(ed);
		
		out.println("<tarefas>");
		for (int i=0; i<lst.size(); i++){
			MobDelivery_TarefaED edVolta = new MobDelivery_TarefaED();
			edVolta = (MobDelivery_TarefaED)lst.get(i);
			if (cmp_Oid_Executor != edVolta.getOid_Executor()) {
				
				if (cmp_Oid_Executor!=0) {
					saida = "</executor>";
					out.println(saida);
				}	
				cmp_Oid_Executor = edVolta.getOid_Executor();
				saida = "<executor ";
				saida += "oid_Executor='" + edVolta.getOid_Executor() + "' ";
				saida += "nm_Executor='" + JavaUtil.preperaString(edVolta.getNm_Executor()) + "' ";
				saida += "id_Grupo_Tarefa='" + edVolta.getOid_Grupo_Tarefa() + "' ";
				saida += "dt_Tarefa='" + edVolta.getDt_Grupo_Tarefa() + "' ";
				saida += "nm_Grupo_Tarefa='" + JavaUtil.preperaString(edVolta.getNm_Grupo_Tarefa()) + "' ";
				saida += ">";
				out.println(saida);
			}
			if (edVolta.getOid_Tarefa() > 0 ) {
				saida = "<item ";
				saida += "oid_Tarefa='" + edVolta.getOid_Tarefa() + "' ";
				saida += "oid_Grupo_Tarefa='" + edVolta.getOid_Grupo_Tarefa() + "' ";
				saida += "nr_Tarefa='" + edVolta.getNr_Tarefa() + "' ";
				saida += "nm_Destinatario='" + JavaUtil.preperaString(edVolta.getNm_Nome()) + "' ";
				saida += "nm_Endereco='" + JavaUtil.preperaString(edVolta.getNm_Endereco()) + "' ";
				saida += "nr_Volumes='" +FormataValor.formataValorBT(edVolta.getNr_Volume(),0) + "' ";
				saida += "dm_Situacao='" + edVolta.getDm_Situacao() + "' ";
				saida += "nm_Situacao='" + edVolta.getNm_Situacao() + "' ";

				if ("C".equals(edVolta.getDm_Tipo())) {
					saida += "nr_Peso='" +FormataValor.formataValorBT(edVolta.getNr_Peso(),3) + "' ";
					saida += "nm_Solicitante='" +edVolta.getNm_Solicitante() + "' ";
					saida += "tx_Observacao='" +edVolta.getTx_Observacao() + "' ";
				}
				
				if ("0".equals(edVolta.getDm_Situacao()) ) {
					saida += "dt_Execucao=' ' ";
					saida += "hr_Execucao=' ' " ;
				} else {
					saida += "dt_Execucao='" + edVolta.getDt_Execucao() + "' ";
					saida += "hr_Execucao='" + edVolta.getHr_Execucao() + "' ";
				}
				saida += "nr_Cor='" + edVolta.getNr_Cor() + "' ";
				saida += "dm_Tipo='" + edVolta.getDm_Tipo() + "' ";
				saida += ">";
				saida += "</item>";
				out.println(saida);
			}
		}
		if (cmp_Oid_Executor!=0) {
			saida = "</executor>";
			out.println(saida);
		}	
		out.println("</tarefas>");
	}

	public void processaCEL(String acao, Object Obj, HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, Excecoes {
		//Extrai o bean com os campos da request colados
		MobDelivery_TarefaED ed = (MobDelivery_TarefaED)Obj;
		//Prepara a saída
		//ed.setMasterDetails(request);
		//ed.setNm_Unidade(edUser.getNm_Usuario());
        //ed.setDm_Stamp(JavaUtil.getValueDef(acao("acao"), "S");
	        
		PrintWriter out = response.getWriter();
		
		BancoUtil bu = new BancoUtil();
		
		if ("C".equals(acao) || "T".equals(acao) ) {
			long nr_Protocolo = Math.round((Math.random()*1000));
			String saida = null;
			ArrayList lstTar = new ArrayList();
			ArrayList lstMsg = new ArrayList();
			ed.setDm_Protocolo(acao);
			lstTar = this.listaTarefasCel(ed);
			lstMsg = this.listaMensagens(ed);
			// Coloca o protocolo como primeira coisa no array de retorno se houver dados a enviar ...
			if (lstTar.size()==0 && lstMsg.size()==0 ) {
				out.println( "NADA||" );
			} else {	
				MobDelivery_TarefaED edVT = new MobDelivery_TarefaED();
				MobDelivery_MensagemED edVM = new MobDelivery_MensagemED();
				saida = nr_Protocolo + "||" ; 
				// Descarrega cada uma das tarefas do array ....
				for (int i=0; i<lstTar.size(); i++){  
					edVT = (MobDelivery_TarefaED)lstTar.get(i);
					saida += edVT.getDm_Tipo() + "|";
					saida += edVT.getOid_Tarefa() + "|";
					saida += edVT.getNr_Ordem_Alocado() + "|";
					saida += edVT.getNm_Nome() + "|";
					saida += edVT.getNm_Endereco() + "|";
					saida += edVT.getNr_Tarefa() + "|";
					saida += FormataValor.formataValorBT(edVT.getNr_Volume(),0) + "|";
					
					if ("C".equals(edVT.getDm_Tipo())) {
						//saida += FormataValor.formataValorBT(edVT.getNr_Peso(),3) + "|";
						saida += "1|";
						saida += edVT.getNm_Solicitante() + "|";
						saida += edVT.getTx_Observacao() + "|";
					}
					
					if ( !"0".equals(edVT.getDm_Situacao())) {
						saida += edVT.getDt_Execucao() + "|";
						saida += edVT.getHr_Execucao() + "|";
						saida += edVT.getDm_Situacao() + "|";
						if ("E".equals(edVT.getDm_Tipo())) {
							saida += (bu.doValida(edVT.getNm_Agente())? edVT.getNm_Agente(): " ") + "|";
							saida += (bu.doValida(edVT.getTx_Rg_Agente())? edVT.getTx_Rg_Agente(): " ") + "|";
						}
					}
					
					//saida += "hr_Inicio_Execucao='" + edVT.getHr_Inicio_Execucao() + "' ";
					//saida += "hr_Fim_Execucao='" + edVT.getHr_Fim_Execucao() + "' ";
					//saida += "nr_Odometro='" + edVT.getNr_Odometro() + "' ";
					
					saida +=  "|";
				}
				out.println(saida);
				// Seta o número do protocolo gerado no ed para gravar ...
				ed.setNr_Protocolo(nr_Protocolo);
				// Se pediu para ler tudo, limpa o protocolo ...
				if ("T".equals(acao) )
					this.limpaProtocolo(ed);
				// Registra a envio com o número do protocolo gerado....
				this.registraEnvio(ed);
			}
		} else
		if ("COK".equals(acao)) {
			this.registraRecebimento(ed);
			out.println( "OK||" );
		} else
		if ("BE".equals(acao)) {
			this.baixaEntrega(ed);
			out.println( "OK||" );
		} else
		if ("BC".equals(acao)) {
			this.baixaColeta(ed);
			out.println( "OK||" );
		}
		if ("FD".equals(acao)) {
			this.registraFechamentoDia(ed);
			out.println( "OK||" );
		}
		if ("ID".equals(acao)) {
			this.registraInicioDia(ed);
			out.println( "OK||" );
		}
    	out.flush();
    	out.close();
	}
}
