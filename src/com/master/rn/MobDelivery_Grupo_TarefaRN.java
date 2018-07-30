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
import com.master.bd.ColetaRCEBD;
import com.master.bd.MobDelivery_Grupo_TarefaBD;
import com.master.bd.MobDelivery_TarefaBD;
import com.master.bd.MobDelivery_UnidadeBD;
import com.master.ed.ColetaED;
import com.master.ed.ColetaRCEED;
import com.master.ed.ConhecimentoED;
import com.master.ed.ManifestoED;
import com.master.ed.MobDelivery_Grupo_TarefaED;
import com.master.ed.MobDelivery_TarefaED;
import com.master.ed.MobDelivery_UnidadeED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import com.master.util.JavaUtil;

/**
 * @author Régis Steigleder
 * @serial Grupos de tarefas no MobDelivery		
 * @serialData 06/2007
 */
public class MobDelivery_Grupo_TarefaRN extends Transacao {

	public MobDelivery_Grupo_TarefaED inclui (MobDelivery_Grupo_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_Grupo_TarefaED toReturn = new MobDelivery_Grupo_TarefaBD (this.sql).inclui (ed);
			this.fimTransacao (true);
			return toReturn;
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void altera (MobDelivery_Grupo_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_Grupo_TarefaBD (this.sql).altera (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void delete (MobDelivery_Grupo_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_Grupo_TarefaBD (this.sql).delete (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}
	
	/**
	 * Deleta grupo tarefa e todas as tarefas debaixo dela.
	 * @param ed
	 * @throws Excecoes
	 */
	public void deleteGT (MobDelivery_Grupo_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			// Busca e apaga todos os registros de tarefas para este grupo de tarefas
			MobDelivery_TarefaED tarED = new MobDelivery_TarefaED();
			tarED.setOid_Grupo_Tarefa(ed.getOid_Grupo_Tarefa());
			MobDelivery_TarefaBD bdTar = new MobDelivery_TarefaBD(this.sql);
			ArrayList lstTar = bdTar.lista(tarED, "nr_ordem");
			for (int i=0 ; i<lstTar.size() ; i++) {
				tarED = (MobDelivery_TarefaED)lstTar.get(i);
				bdTar.delete(tarED);
			}
			new MobDelivery_Grupo_TarefaBD (this.sql).delete (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}
	
	
	/**
	 * Importa dados do nalthus:
	 * 		- Le dados das tabelas Manifesto, viagens e conhecimentos.
	 * 		- Grava em MobDelivery_Grupos_Tarefas e MobDelivery_Tarefas.
	 * 		- Importa de todas as unidades que devem ter uma correspondecia por cd_Unidade no MobDelivery.
	 * 			- Se não houver correspondencia não importa.
	 * 		- Sequencia de 100 1em 100 as tarefas iniciando em 100 para o grupo.
	 * @param ed com a data do dia aberto na tela mobC001
	 * @throws Excecoes
	 */
	public void importaNalthus (MobDelivery_Grupo_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_Grupo_TarefaBD bd = new MobDelivery_Grupo_TarefaBD (this.sql);
			// Le os Manifestos do período
			ManifestoED manED = new ManifestoED();
			manED.setDT_Emissao_Inicial(ed.getDt_Inicial());
			manED.setDT_Emissao_Final(ed.getDt_Final());
			ArrayList lstM = new ArrayList();
			// Lista dos manifestos lidos e ordernados por unidade
			lstM =  bd.listaManifestos(manED);
			for (int i=0; i<lstM.size(); i++){
				ManifestoED edMv = new ManifestoED();
				edMv = (ManifestoED)lstM.get(i);
				//Instancia o grupo tarefa para gravar
				MobDelivery_Grupo_TarefaED edG = new MobDelivery_Grupo_TarefaED();
				// Vai buscar o oid da unidade
				MobDelivery_UnidadeED unED = new MobDelivery_UnidadeED();
				unED.setNr_Unidade(edMv.getCD_Unidade());
				unED = new MobDelivery_UnidadeBD(this.sql).getByRecord(unED);
				// Se encontrou a unidade pelo código dela... inclui o grupo tarefa
				if (unED.getOid_Unidade()> 0) {
					edG.setOid_Unidade(unED.getOid_Unidade());
					edG.setNr_Grupo_Tarefa(edMv.getNR_Manifesto());
					edG.setNm_Grupo_Tarefa(edMv.getNR_Manifesto() + " - " + edMv.getNM_Ajudante1() + " - " + edMv.getNR_Placa());
					edG.setDt_Grupo_Tarefa(edMv.getDT_Emissao());
					edG.setOid_Romaneio(edMv.getOID_Manifesto());
					// Valida se já foi incluído 
					MobDelivery_Grupo_TarefaED edGQ = new MobDelivery_Grupo_TarefaED();
					edGQ.setNr_Grupo_Tarefa(edG.getNr_Grupo_Tarefa());
					edGQ.setOid_Unidade(edG.getOid_Unidade());
					edGQ = bd.getByRecord(edGQ);
					if (edGQ.getOid_Grupo_Tarefa() == 0) {
						// Inclui o Grupo de Tarefas e retorna o oid - MobDelivery_Grupos_Tarefas
						edG = bd.inclui(edG);
					} else {
						edG.setOid_Grupo_Tarefa(edGQ.getOid_Grupo_Tarefa());
					}
					//
					ConhecimentoED conED = new ConhecimentoED();
					conED.setOID_Conhecimento(edMv.getOID_Manifesto());
					ArrayList lstC= new ArrayList();
					// Busca os conhecimentos da viagem - Entrega
					lstC = bd.listaConhecimento(conED);
					long nr_Tarefa = 0;
					for (int y=0; y<lstC.size(); y++){
						ConhecimentoED edCv = new ConhecimentoED();
						edCv = (ConhecimentoED)lstC.get(y);
						MobDelivery_TarefaED edT = new MobDelivery_TarefaED();
						// Monta o ed para gravar a tarefa
						edT.setOid_Grupo_Tarefa(edG.getOid_Grupo_Tarefa());
						edT.setOid_Unidade(edG.getOid_Unidade());
						edT.setDt_Tarefa(edMv.getDT_Emissao());;
						edT.setNr_Tarefa(edCv.getNR_Conhecimento());
						edT.setNr_Volume(edCv.getNR_Volumes());
						edT.setNm_Endereco(edCv.getNM_Cidade_Destinatario());
						edT.setNm_Nome(edCv.getNM_Pessoa_Destinatario());
						edT.setOid_Conhecimento(edCv.getOID_Conhecimento());
						edT.setDm_Tipo("E");
						nr_Tarefa=nr_Tarefa+100;
						edT.setNr_Ordem(nr_Tarefa);
						MobDelivery_TarefaBD bdT= new MobDelivery_TarefaBD(this.sql);
						// Verifica se já foi incluido
						MobDelivery_TarefaED edTQ = new MobDelivery_TarefaED();
						edTQ.setOid_Unidade(edT.getOid_Unidade());
						edTQ.setNr_Tarefa(edT.getNr_Tarefa());
						edTQ = bdT.getByRecord(edTQ);
						if (edTQ.getOid_Tarefa()==0) {
							// Inclui a tarefa MobDelivery_Tarefas
							bdT.inclui(edT);
						}
					}
					
					// Busca coletas
					ColetaRCEED colRCEED = new ColetaRCEED();
					colRCEED.setOID_Manifesto(edMv.getOID_Manifesto());
					lstC = new ColetaRCEBD(this.sql).lista(colRCEED);
					for (int y=0; y<lstC.size(); y++){
						ColetaRCEED edCRCEv = new ColetaRCEED();
						edCRCEv = (ColetaRCEED)lstC.get(y);
						ColetaED colCv = new ColetaED();
						colCv.setOID_Coleta(edCRCEv.getOID_Coleta());
						colCv = new ColetaBD(this.sql).getByRecord(colCv) ;
						MobDelivery_TarefaED edT = new MobDelivery_TarefaED();
						// Monta o ed para gravar a tarefa
						edT.setOid_Grupo_Tarefa(edG.getOid_Grupo_Tarefa());
						edT.setOid_Unidade(edG.getOid_Unidade());
						edT.setDt_Tarefa(edMv.getDT_Emissao());;
						edT.setNr_Tarefa(Long.parseLong(colCv.getNR_Coleta()));
						edT.setNm_Endereco(colCv.getNM_Endereco_Coleta());
						edT.setNm_Nome(colCv.getNM_Pessoa_Remetente());
						edT.setNm_Solicitante(colCv.getNM_Solicitante());
						edT.setNr_Volume(colCv.getNR_Volumes());
						edT.setNr_Peso(colCv.getNR_Peso());
						edT.setTx_Observacao("Coletar em " + colCv.getDT_Coleta() + " entre " + colCv.getHR_Coleta_Minima() + "h e " + colCv.getHR_Coleta_Maxima() + "h.");
						edT.setOid_Conhecimento(colCv.getOID_Coleta());
						edT.setDm_Tipo("C");
						nr_Tarefa=nr_Tarefa+100;
						edT.setNr_Ordem(nr_Tarefa);
						MobDelivery_TarefaBD bdT= new MobDelivery_TarefaBD(this.sql);
						// Verifica se já foi incluido
						MobDelivery_TarefaED edTQ = new MobDelivery_TarefaED();
						edTQ.setOid_Unidade(edT.getOid_Unidade());
						edTQ.setNr_Tarefa(edT.getNr_Tarefa());
						edTQ = bdT.getByRecord(edTQ);
						if (edTQ.getOid_Tarefa()==0) {
							// Inclui a tarefa MobDelivery_Tarefas
							bdT.inclui(edT);
						}
					}
				}
			}
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	
	public ArrayList lista (MobDelivery_Grupo_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_Grupo_TarefaBD (this.sql).lista (ed);
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public ArrayList listaGrupoTna (MobDelivery_Grupo_TarefaED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_Grupo_TarefaBD (this.sql).listaGrupoTna(ed);
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public MobDelivery_Grupo_TarefaED getByRecord (MobDelivery_Grupo_TarefaED ed ) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_Grupo_TarefaBD (this.sql).getByRecord (ed);
		}
		finally {
			this.fimTransacao (false);
		}
	}


	/**
    public void relatorio(MobDelivery_Grupo_TarefaED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new MobDelivery_Grupo_TarefaBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório 
			ed.setResponse(response);
			ed.setNomeRelatorio("pns021"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNm_Banda()))
				nm_Filtro+=" Desenho=" + ed.getNm_Banda();
			if (ed.getOid_Fabricante_Banda()>0) 
				nm_Filtro+=" Marca=" + ed.getNm_Fabricante_Banda();
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }
	 **/

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
	/**
    public void processaRL(String rel, Object Obj, HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, Excecoes {
    	//Extrai o bean com os campos da request colados
    	MobDelivery_Grupo_TarefaED ed = (MobDelivery_Grupo_TarefaED)Obj;
    	ed.setRequest(request);
    	//if ("1".equals(rel)) {
    	this.relatorio(ed, request, response);	
    	//} 
    }
	 **/

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
		MobDelivery_Grupo_TarefaED ed = (MobDelivery_Grupo_TarefaED)Obj;
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
		if ("IN".equals(acao)) {
			this.importaNalthus(ed);
			out.println("<ret><item oknok='INOK' /></ret>");
		} else 
		if ("DGT".equals(acao)) {
			this.deleteGT(ed);
			out.println("<ret><item oknok='DGTOK' /></ret>");
		} else 
		if ("LTNA".equals(acao)) {
			out.println("<tarefas>");
			String saida = null;
			ArrayList lst = new ArrayList();
			lst = this.listaGrupoTna(ed);
			for (int i=0; i<lst.size(); i++){
				MobDelivery_Grupo_TarefaED edVolta = new MobDelivery_Grupo_TarefaED();
				edVolta = (MobDelivery_Grupo_TarefaED)lst.get(i);
				saida = "<executor ";
				saida += "oid_Grupo_Tarefa='" + edVolta.getOid_Grupo_Tarefa() + "' ";
				saida += "oid_Unidade='" + edVolta.getOid_Unidade() + "' ";
				saida += "nm_Grupo_Tarefa='" + JavaUtil.preperaString(edVolta.getNm_Grupo_Tarefa()) + "' ";
				saida += "getNr_Grupo_Tarefa='" + edVolta.getNr_Grupo_Tarefa() + "' ";
				saida += "dt_Grupo_Tarefa='" + edVolta.getDt_Grupo_Tarefa() + "' ";
				saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
				saida += "/>";
				out.println(saida);
			}
			out.println("</tarefas>");
		} else {
			out.println("<cad>");
			String saida = null;
			ArrayList lst = new ArrayList();
			lst = this.lista(ed);
			for (int i=0; i<lst.size(); i++){
				MobDelivery_Grupo_TarefaED edVolta = new MobDelivery_Grupo_TarefaED();
				edVolta = (MobDelivery_Grupo_TarefaED)lst.get(i);
				if ("L".equals(acao)) {
					saida = "<item ";
					saida += "oid_Grupo_Tarefa='" + edVolta.getOid_Grupo_Tarefa() + "' ";
					saida += "oid_Unidade='" + edVolta.getOid_Unidade() + "' ";
					saida += "nm_Grupo_Tarefa='" + JavaUtil.preperaString(edVolta.getNm_Grupo_Tarefa()) + "' ";
					saida += "getNr_Grupo_Tarefa='" + edVolta.getNr_Grupo_Tarefa() + "' ";
					saida += "dt_Grupo_Tarefa='" + edVolta.getDt_Grupo_Tarefa() + "' ";
					saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
					saida += "/>";
				} 
				out.println(saida);
			}
			out.println("</cad>");
		}
		out.flush();
		out.close();
	}

}
