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

import com.master.bd.MobDelivery_ParametroBD;
import com.master.bd.MobDelivery_SituacaoBD;
import com.master.bd.MobDelivery_TarefaBD;
import com.master.ed.MobDelivery_ParametroED;
import com.master.ed.MobDelivery_SituacaoED;
import com.master.ed.MobDelivery_TarefaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Situacao no MobDelivery
 * @serialData 06/2008
 */
public class MobDelivery_SituacaoRN extends Transacao {

	public MobDelivery_SituacaoED inclui (MobDelivery_SituacaoED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_SituacaoED toReturn = new MobDelivery_SituacaoBD (this.sql).inclui (ed);
			this.fimTransacao (true);
			return toReturn;
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void altera (MobDelivery_SituacaoED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_SituacaoBD (this.sql).altera (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void delete (MobDelivery_SituacaoED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_SituacaoBD (this.sql).delete (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public ArrayList lista (MobDelivery_SituacaoED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_SituacaoBD (this.sql).lista (ed);
		}
		finally {
			this.fimTransacao (false);
		}

	}

	public MobDelivery_SituacaoED getByRecord (MobDelivery_SituacaoED ed ) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_SituacaoBD (this.sql).getByRecord (ed);
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
	

	/**
    public void relatorio(MobDelivery_SituacaoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new MobDelivery_SituacaoBD(sql).lista(ed);
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
    	MobDelivery_SituacaoED ed = (MobDelivery_SituacaoED)Obj;
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
		MobDelivery_SituacaoED ed = (MobDelivery_SituacaoED)Obj;
		//Prepara a saída
		ed.setMasterDetails(request);
		
		BancoUtil bu = new BancoUtil();
		
		PrintWriter out = response.getWriter();
		out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
		if ("I".equals(acao) ) {
			if (checkDuplo(ed,acao)) {  
				out.println("<ret><item oknok='Situacao já existente com este código !'/></ret>");
			} else {
				ed = this.inclui(ed);
				out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Situacao() + "' /></ret>");
			}    		
		} else 
		if ("A".equals(acao)) {
			if (checkDuplo(ed,acao)) {  
				out.println("<ret><item oknok='Executor já existente com este código !'/></ret>");
			} else {
				this.altera(ed);
				out.println("<ret><item oknok='AOK' /></ret>");
			}
		} else 
		if ("D".equals(acao)) {
			if (checkEmUso(ed)) {
				out.println("<ret><item oknok='Impossível excluir! Situação em uso!' /></ret>");
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
				MobDelivery_SituacaoED edVolta = new MobDelivery_SituacaoED();
				edVolta = (MobDelivery_SituacaoED)lst.get(i);
				if ("L".equals(acao)) {
					saida = "<item ";
					saida += "oid_Situacao='" + edVolta.getOid_Situacao() + "' ";
					saida += "cd_Situacao='" + edVolta.getCd_Situacao() + "' ";
					saida += "nm_Situacao='" + edVolta.getNm_Situacao() + "' ";
					saida += "nr_Cor='" + edVolta.getNr_Cor() + "' ";
					saida += "dm_Exige_Recebedor='" + edVolta.getDm_Exige_Recebedor() + "' ";
					saida += "cd_Externo='" + (bu.doValida(edVolta.getCd_Externo()) ? edVolta.getCd_Externo() : "" ) + "' ";
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
						saida += "value='" + edVolta.getOid_Situacao() +  "'>";
						saida +=  edVolta.getCd_Situacao().trim() + " - " + edVolta.getNm_Situacao().trim() ;
						saida += "</item>";
					}
				out.println(saida);
			}
			out.println("</cad>");
		}
		out.flush();
		out.close();
	}

	public boolean checkDuplo ( MobDelivery_SituacaoED ed, String acao) throws Excecoes {
		boolean ret = false;
		MobDelivery_SituacaoED edChk = new MobDelivery_SituacaoED();
		edChk.setCd_Situacao(ed.getCd_Situacao());
		edChk = this.getByRecord(edChk);
		if ("I".equals(acao) && edChk.getOid_Situacao()>0)
			ret = true;
		else
			if ("A".equals(acao) && edChk.getOid_Situacao()>0 ) {
				if (ed.getOid_Situacao()!=edChk.getOid_Situacao() )
					ret = true ;
			}
		return ret;
	}

	
    public boolean checkEmUso ( MobDelivery_SituacaoED ed ) throws Excecoes {
    	try {
    		boolean achei=false;
    		// Procura na MobDelivery - Tarefas e Historico de Tarefas
    		this.inicioTransacao();
    		MobDelivery_TarefaED edChk = new MobDelivery_TarefaED();
    		edChk.setDm_Situacao(ed.getCd_Situacao());
    		achei=(new MobDelivery_TarefaBD(this.sql).lista(edChk,"nr_ordem").size()>0 ? true : false);
    		return (achei); 
    	} finally {
    		this.fimTransacao(false);
    	}
    }
	

}
