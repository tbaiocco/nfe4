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

import com.master.bd.MobDelivery_MensagemBD;
import com.master.ed.MobDelivery_MensagemED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Mensagens no MobDelivery
 * @serialData 06/2007
 */
public class MobDelivery_MensagemRN extends Transacao {

	public MobDelivery_MensagemED inclui (MobDelivery_MensagemED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			MobDelivery_MensagemED toReturn = new MobDelivery_MensagemBD (this.sql).inclui (ed);
			this.fimTransacao (true);
			return toReturn;
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void altera (MobDelivery_MensagemED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_MensagemBD (this.sql).altera (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void delete (MobDelivery_MensagemED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_MensagemBD (this.sql).delete (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void registraEnvio (MobDelivery_MensagemED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_MensagemBD (this.sql).registraEnvio (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public void registraRecebimento (MobDelivery_MensagemED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_MensagemBD (this.sql).registraRecebimento (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}


	public void limpaProtocolo (MobDelivery_MensagemED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			new MobDelivery_MensagemBD (this.sql).limpaProtocolo (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			this.abortaTransacao ();
			throw e;
		}
	}

	public ArrayList lista (MobDelivery_MensagemED ed) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_MensagemBD (this.sql).lista (ed);
		}
		finally {
			this.fimTransacao (false);
		}
	}

	public MobDelivery_MensagemED getByRecord (MobDelivery_MensagemED ed ) throws Excecoes {
		this.inicioTransacao ();
		try {
			return new MobDelivery_MensagemBD (this.sql).getByRecord (ed);
		}
		finally {
			this.fimTransacao (false);
		}
	}


	/**
    public void relatorio(MobDelivery_MensagemED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new MobDelivery_MensagemBD(sql).lista(ed);
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
    	MobDelivery_MensagemED ed = (MobDelivery_MensagemED)Obj;
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
		MobDelivery_MensagemED ed = (MobDelivery_MensagemED)Obj;
		//Prepara a saída
		ed.setMasterDetails(request);

		PrintWriter out = response.getWriter();
		out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
		if ("I".equals(acao) ) {
			ed = this.inclui(ed);
			out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Mensagem() + "' /></ret>");
		} else 
		if ("A".equals(acao)) {
			this.altera(ed);
			out.println("<ret><item oknok='AOK' /></ret>");
		} else 
		if ("D".equals(acao)) {
			this.delete(ed);
			out.println("<ret><item oknok='DOK' /></ret>");
		} else {
			out.println("<cad>");
			String saida = null;
			ArrayList lst = new ArrayList();
			lst = this.lista(ed);
			for (int i=0; i<lst.size(); i++){
				MobDelivery_MensagemED edVolta = new MobDelivery_MensagemED();
				edVolta = (MobDelivery_MensagemED)lst.get(i);
				if ("L".equals(acao)) {
					saida = "<item ";
					saida += "oid_Mensagem='" + edVolta.getOid_Mensagem() + "' ";
					saida += "oid_Unidade='" + edVolta.getOid_Unidade() + "' ";
					saida += "nr_Mensagem='" + edVolta.getNr_Mensagem() + "' ";
					saida += "tx_Mensagem='" + edVolta.getTx_Mensagem() + "' ";
					saida += "dt_Mensagem='" + edVolta.getDt_Mensagem() + "' ";
					saida += "oid_Executor='" + edVolta.getOid_Executor() + "' ";
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

}
