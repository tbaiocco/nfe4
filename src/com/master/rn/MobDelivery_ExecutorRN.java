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

import com.master.bd.MobDelivery_ExecutorBD;
import com.master.bd.MobDelivery_ParametroBD;
import com.master.bd.MobDelivery_SituacaoBD;
import com.master.bd.MobDelivery_TarefaBD;
import com.master.ed.MobDelivery_ExecutorED;
import com.master.ed.MobDelivery_ParametroED;
import com.master.ed.MobDelivery_SituacaoED;
import com.master.ed.MobDelivery_TarefaED;
import com.master.ed.UsuarioED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Executor de tarefas no MobDelivery
 * @serialData 06/2007
 */
public class MobDelivery_ExecutorRN extends Transacao {

	public MobDelivery_ExecutorED inclui (MobDelivery_ExecutorED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_ExecutorED toReturn = new MobDelivery_ExecutorBD (this.sql).inclui (ed);
			this.fimTransacao (true);
			return toReturn;
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void altera (MobDelivery_ExecutorED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_ExecutorBD (this.sql).altera (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void delete (MobDelivery_ExecutorED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_ExecutorBD (this.sql).delete (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public ArrayList lista (MobDelivery_ExecutorED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_ExecutorBD (this.sql).lista (ed);
		}
		finally {
			this.fimTransacao (false);
		}

	}

	public MobDelivery_ExecutorED getByRecord (MobDelivery_ExecutorED ed ) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_ExecutorBD (this.sql).getByRecord (ed);
		}
		finally {
			this.fimTransacao (false);
		}
	}
	
	public MobDelivery_ParametroED getByRecord (MobDelivery_ParametroED ed ) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_ParametroBD (this.sql).getByRecord (ed);
		}
		finally {
			this.fimTransacao (false);
		}
	}
	
	public String listaSits (MobDelivery_SituacaoED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_SituacaoED sitED = new MobDelivery_SituacaoED();
			ArrayList lst = new MobDelivery_SituacaoBD(this.sql).lista(sitED);
			String strSits = "";
			for (int i=0; i<lst.size(); i++){
				sitED = (MobDelivery_SituacaoED)lst.get(i);
				strSits+=sitED.getCd_Situacao()+","+sitED.getDm_Exige_Recebedor()+"|";
			}
			return strSits;
		}
		finally {
			this.fimTransacao (false);
		}

	}

	/**
    public void relatorio(MobDelivery_ExecutorED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new MobDelivery_ExecutorBD(sql).lista(ed);
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
    	MobDelivery_ExecutorED ed = (MobDelivery_ExecutorED)Obj;
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
		MobDelivery_ExecutorED ed = (MobDelivery_ExecutorED)Obj;
		//Prepara a saída
		ed.setMasterDetails(request);

		PrintWriter out = response.getWriter();
		out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
		if ("I".equals(acao) ) {
			if (checkDuplo(ed,acao)) {  
				out.println("<ret><item oknok='Executor já existente com este nome !'/></ret>");
			} else {
				ed = this.inclui(ed);
				out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Executor() + "' /></ret>");
			}    		
		} else 
		if ("A".equals(acao)) {
			if (checkDuplo(ed,acao)) {  
				out.println("<ret><item oknok='Executor já existente com este nome !'/></ret>");
			} else {
				this.altera(ed);
				out.println("<ret><item oknok='AOK' /></ret>");
			}
		} else 
		if ("D".equals(acao)) {
			if (checkEmUso(ed)) {
				out.println("<ret><item oknok='Impossível excluir! Executor em uso!' /></ret>");
			} else {
				this.delete(ed);
				out.println("<ret><item oknok='DOK' /></ret>");
			}			
		} else {
			out.println("<cad>");
			String saida = null;
			ArrayList lst = new ArrayList();
			lst = this.lista(ed);
			for (int i=0; i<lst.size(); i++){
				MobDelivery_ExecutorED edVolta = new MobDelivery_ExecutorED();
				edVolta = (MobDelivery_ExecutorED)lst.get(i);
				if ("L".equals(acao)) {
					saida = "<item ";
					saida += "oid_Executor='" + edVolta.getOid_Executor() + "' ";
					saida += "oid_Unidade='" + edVolta.getOid_Unidade() + "' ";
					saida += "nm_Executor='" + edVolta.getNm_Executor() + "' ";
					saida += "nr_Celular='" + edVolta.getNr_Celular() + "' ";
					saida += "tx_Senha='" + edVolta.getTx_Senha() + "' ";
					saida += "nm_Unidade='" + edVolta.getNm_Unidade() + "' ";
					saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
					saida += "/>";
				} else 			
					if ("CB".equals(acao) || "CBC".equals(acao)) {
						if ( i==0 && "CBC".equals(acao) ) {
							saida = "<item ";
							saida += "value='0'>TODOS</item>";
							out.println(saida);
						}
						saida = "<item ";
						saida += "value='" + edVolta.getOid_Executor() +  "'>";
						saida +=  edVolta.getNm_Executor().trim() ;
						saida += "</item>";
					}
				out.println(saida);
			}
			out.println("</cad>");
		}
		out.flush();
		out.close();
	}

	/**
	 * processaCEL
	 * Processa solicitação de celular retornando sempre uma string com a seguinte estrutura.
	 * campo|campo||
	 * @param acao
	 * @param Obj
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws Excecoes
	 */
	public void processaCEL(String acao, Object Obj, HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, Excecoes {
		//Extrai o bean com os campos da request colados
		MobDelivery_ExecutorED ed = (MobDelivery_ExecutorED)Obj;
		//Prepara a saída

		PrintWriter out = response.getWriter();
		if ("LOGON".equals(acao) ) {
			MobDelivery_ExecutorED edGbr = this.getByRecord(ed);
			if (edGbr.getOid_Executor()>0){ 
				// Verifica se a senha está ok
				if (edGbr.getTx_Senha().equals(ed.getTx_Senha())) {
					// Coloca o usuario MobDelivery na session
					request.getSession().setAttribute("usuarioCEL", edGbr);
					// Coloca o usuario Nalthus na session ( para que a rotina OLUtil.requestToBean funcione adequadamente )
					UsuarioED edUsuNalthus = new UsuarioED();
					edUsuNalthus.setNm_Usuario(edGbr.getNm_Executor());
					request.getSession().setAttribute("usuario", edUsuNalthus);
					// Pega a parametrização do celular
					MobDelivery_ParametroED parED = new MobDelivery_ParametroED ();
					parED = this.getByRecord(parED);
					// Pega a lista de situações
					String strSits = this.listaSits(new MobDelivery_SituacaoED());
					// Devolve o oid + paraaemtros + lista de situacoes 
					out.println("LOGOK|"+edGbr.getOid_Executor()+"|"+ parED.getNr_Frequencia_Refresh_Cel()+"||"+strSits+"|");
				}else{
					request.getSession().setAttribute("usuarioCEL", null);
					out.println("LOGNOK||");
				}
			} else {
				request.getSession().setAttribute("usuarioCEL", null);
				out.println("LOGNOK||");
			}
		}
		out.flush();
		out.close();
	}

	public boolean checkDuplo ( MobDelivery_ExecutorED ed, String acao) throws Excecoes {
		boolean ret = false;
		MobDelivery_ExecutorED edChk = new MobDelivery_ExecutorED();
		edChk.setNm_Executor(ed.getNm_Executor());
		edChk = this.getByRecord(edChk);
		if ("I".equals(acao) && edChk.getOid_Executor()>0)
			ret = true;
		else
			if ("A".equals(acao) && edChk.getOid_Executor()>0 ) {
				if (ed.getOid_Executor()!=edChk.getOid_Executor() )
					ret = true ;
			}
		return ret;
	}
	
    public boolean checkEmUso ( MobDelivery_ExecutorED ed ) throws Excecoes {
    	try {
    		boolean achei=false;
    		// Procura na MobDelivery - Tarefas e Historico de Tarefas
    		this.inicioTransacao();
    		MobDelivery_TarefaED tarED = new MobDelivery_TarefaED();
    		tarED.setOid_Executor(ed.getOid_Executor());
    		achei=(new MobDelivery_TarefaBD(this.sql).lista(tarED,"nr_ordem").size()>0 ? true : false);
    		return (achei); 
    	} finally {
    		this.fimTransacao(false);
    	}
    }
	

}
