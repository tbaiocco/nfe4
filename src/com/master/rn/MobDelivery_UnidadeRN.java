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
import com.master.bd.MobDelivery_UnidadeBD;
import com.master.ed.MobDelivery_ExecutorED;
import com.master.ed.MobDelivery_UnidadeED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Unidade de Negócio no MobDelivery
 * @serialData 19/05/2008
 */
public class MobDelivery_UnidadeRN extends Transacao {

	public MobDelivery_UnidadeED inclui (MobDelivery_UnidadeED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_UnidadeED toReturn = new MobDelivery_UnidadeBD (this.sql).inclui (ed);
			this.fimTransacao (true);
			return toReturn;
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void altera (MobDelivery_UnidadeED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_UnidadeBD (this.sql).altera (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void delete (MobDelivery_UnidadeED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_UnidadeBD (this.sql).delete (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public ArrayList lista (MobDelivery_UnidadeED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_UnidadeBD (this.sql).lista (ed);
		}
		finally {
			this.fimTransacao (false);
		}

	}

	public MobDelivery_UnidadeED getByRecord (MobDelivery_UnidadeED ed ) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_UnidadeBD (this.sql).getByRecord (ed);
		}
		finally {
			this.fimTransacao (false);
		}
	}

	/**
    public void relatorio(MobDelivery_UnidadeED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new MobDelivery_UnidadeBD(sql).lista(ed);
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
    	MobDelivery_UnidadeED ed = (MobDelivery_UnidadeED)Obj;
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
	public void processaOL(String acao, Object Obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Excecoes {
		//Extrai o bean com os campos da request colados
		MobDelivery_UnidadeED ed = (MobDelivery_UnidadeED)Obj;
		//Prepara a saída
		ed.setMasterDetails(request);

		PrintWriter out = response.getWriter();
		out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
		if ("I".equals(acao) ) {
			if (checkDuplo(ed,acao)) {  
				out.println("<ret><item oknok='Unidade já existente com este código !'/></ret>");
			} else {
				ed = this.inclui(ed);
				out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Unidade() + "' /></ret>");
			}    		
		} else 
		if ("A".equals(acao)) {
			if (checkDuplo(ed,acao)) {  
				out.println("<ret><item oknok='Unidade já existente com este código !'/></ret>");
			} else {
				this.altera(ed);
				out.println("<ret><item oknok='AOK' /></ret>");
			}
		} else 
		if ("D".equals(acao)) {
			if (checkEmUso(ed)) {
				out.println("<ret><item oknok='Impossível excluir! Unidade em uso!' /></ret>");
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
				MobDelivery_UnidadeED edVolta = new MobDelivery_UnidadeED();
				edVolta = (MobDelivery_UnidadeED)lst.get(i);
				if ("L".equals(acao)) {
					saida = "<item ";
					saida += "oid_Unidade='" + edVolta.getOid_Unidade() + "' ";
					saida += "nr_Unidade='" + edVolta.getNr_Unidade() + "' ";
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
						saida += "value='" + edVolta.getOid_Unidade() +  "'>";
						saida +=  edVolta.getNm_Unidade().trim() ;
						saida += "</item>";
					}
				out.println(saida);
			}
			out.println("</cad>");
		}
		out.flush();
		out.close();
	}

	public boolean checkDuplo ( MobDelivery_UnidadeED ed, String acao) throws Excecoes {
		boolean ret = false;
		MobDelivery_UnidadeED edChk = new MobDelivery_UnidadeED();
		edChk.setNr_Unidade(ed.getNr_Unidade());
		edChk = this.getByRecord(edChk);
		if ("I".equals(acao) && edChk.getOid_Unidade()>0)
			ret = true;
		else
			if ("A".equals(acao) && edChk.getOid_Unidade()>0 ) {
				if (ed.getOid_Unidade()!=edChk.getOid_Unidade() )
					ret = true ;
			}
		return ret;
	}

	
    public boolean checkEmUso ( MobDelivery_UnidadeED ed ) throws Excecoes {
		try {
			boolean achei=false;
			// Procura na MobDelivery - Tarefas e Historico de Tarefas
			this.inicioTransacao();
			MobDelivery_ExecutorED exeED = new MobDelivery_ExecutorED();
			exeED.setOid_Unidade(ed.getOid_Unidade());
			achei=(new MobDelivery_ExecutorBD(this.sql).lista(exeED).size()>0 ? true : false);
			return (achei); 
		} finally {
			this.fimTransacao(false);
		}
    }
	

}
