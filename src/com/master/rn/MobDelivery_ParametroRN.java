/*
 * Created on 12/11/2004
 *
 */
package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.MobDelivery_ParametroBD;
import com.master.ed.MobDelivery_ParametroED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Executor de tarefas no MobDelivery
 * @serialData 06/2007
 */
public class MobDelivery_ParametroRN extends Transacao {


	public void altera (MobDelivery_ParametroED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_ParametroBD (this.sql).altera (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public MobDelivery_ParametroED iniciaDia (MobDelivery_ParametroED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_ParametroBD parBD = new MobDelivery_ParametroBD(this.sql);
			ed = parBD.getByRecord(ed);
			String dtHoje = Data.getDataDMY();
			if (ed.getDt_Aberto().length() == 0 ) {
				ed.setDt_Aberto("01/01/2000"); // Se não havia data ainda nos parametros ...
				
			}
			if (Data.comparaData(dtHoje, ">", ed.getDt_Aberto())) {
				ed.setDt_Aberto(dtHoje);
				parBD.altera(ed);
			} else {
				ed = null;
			}
			this.fimTransacao (true);
			return ed;
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
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
    public void relatorio(MobDelivery_ParametroED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new MobDelivery_ParametroBD(sql).lista(ed);
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
    	MobDelivery_ParametroED ed = (MobDelivery_ParametroED)Obj;
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
		MobDelivery_ParametroED ed = (MobDelivery_ParametroED)Obj;
		//Prepara a saída
		ed.setMasterDetails(request);

		PrintWriter out = response.getWriter();
		out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
		if ("A".equals(acao)) {
			this.altera(ed);
			out.println("<ret><item oknok='APOK' /></ret>");
		} else	
		if ("DTD".equals(acao)) {
			ed = this.getByRecord(ed);
			if (ed!=null) {
				out.println("<ret><item oknok='DTDOK' dt_Aberto='" + ed.getDt_Aberto() + "' /></ret>");
			} else {
				out.println("<ret><item oknok='DTDNOK' /></ret>");
			}
		} else	
		if ("ID".equals(acao)) {
			ed = this.iniciaDia(ed);
			if (ed!=null) {
				out.println("<ret><item oknok='IDOK' dt_Aberto='" + ed.getDt_Aberto() + "' /></ret>");
			} else {
				out.println("<ret><item oknok='IDNOK' /></ret>");
			}
		} else {
			if ("C".equals(acao)) {
				out.println("<cad>");
				String saida = null;
				MobDelivery_ParametroED edVolta = this.getByRecord(ed);
				saida = "<item ";
				saida += "nm_Parametro='" + edVolta.getNm_Parametro() + "' ";
				saida += "nr_Frequencia_Refresh_Tela='" + edVolta.getNr_Frequencia_Refresh_Tela() + "' ";
				saida += "nr_Frequencia_Refresh_Cel='" + edVolta.getNr_Frequencia_Refresh_Cel() + "' ";
				saida += "dt_Aberto='" + edVolta.getDt_Aberto() + "' ";
				saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
				saida += "/>";
				out.println(saida);
				out.println("</cad>");
			}
		}
		out.flush();
		out.close();
	}

}
