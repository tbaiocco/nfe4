/*
 * Created on 12/11/2004
 */
package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.BandaBD;
import com.master.bd.Fabricante_BandaBD;
import com.master.bd.PneuBD;
import com.master.ed.BandaED;
import com.master.ed.Fabricante_BandaED;
import com.master.ed.Marca_PneuED;
import com.master.ed.PneuED;
import com.master.ed.UsuarioED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.RequestUtil;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Fabricantes de pneus
 * @serialData 06/2007
 */
public class Fabricante_BandaRN extends Transacao {

	public Fabricante_BandaED inclui (Fabricante_BandaED ed) throws Excecoes {
		inicioTransacao ();
		try {
			Fabricante_BandaED toReturn = new Fabricante_BandaBD (sql).inclui (ed);
			fimTransacao (true);
			return toReturn;
		}
		catch (Excecoes e) {
			abortaTransacao ();
			throw e;
		}
	}

	public void altera (Fabricante_BandaED ed) throws Excecoes {
		inicioTransacao ();
		try {
			new Fabricante_BandaBD (sql).altera (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			abortaTransacao ();
			throw e;
		}
	}

	public void delete (Fabricante_BandaED ed) throws Excecoes {
		inicioTransacao ();
		try {
			new Fabricante_BandaBD (sql).delete (ed);
			fimTransacao (true);
		}
		catch (Excecoes e) {
			abortaTransacao ();
			throw e;
		}
	}

	public ArrayList lista (Fabricante_BandaED ed) throws Excecoes {
		try {
			this.inicioTransacao ();
			return new Fabricante_BandaBD (sql).lista (ed);
		}
		finally {
			fimTransacao (false);
		}
	}

	public Fabricante_BandaED getByRecord (Fabricante_BandaED ed) throws Excecoes {
		try {
			this.inicioTransacao ();
			return new Fabricante_BandaBD (sql).getByRecord (ed);
		}
		finally {
			fimTransacao (false);
		}
	}


    public void relatorio(Fabricante_BandaED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Fabricante_BandaBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns020"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNm_Fabricante_Banda()))
				nm_Filtro+=" Descrição=" + ed.getNm_Fabricante_Banda();
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
    	Fabricante_BandaED ed = (Fabricante_BandaED)Obj;
    	ed.setRequest(request);
	//if ("1".equals(rel)) {
		this.relatorio(ed, request, response);
	//}

}

	/**
     * processaOL
     * Processa colicitação de tela OL retornando sempre arquivo XML com a seguinte estrutura.
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
    	Fabricante_BandaED ed = (Fabricante_BandaED)Obj;
    	//Prepara a saída
    	ed.setMasterDetails(request);

    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		if (checkDuplo(ed,acao)) {
    			out.println("<ret><item oknok='Marca já existente com esta descrição !'/></ret>");
    		} else {
	    		ed = this.inclui(ed);
	    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Fabricante_Banda() + "' /></ret>");
    		}
    	} else
		if ("A".equals(acao)) {
    		if (checkDuplo(ed,acao)) {
    			out.println("<ret><item oknok='Marca já existente com esta descrição !'/></ret>");
    		} else {
				this.altera(ed);
				out.println("<ret><item oknok='AOK' /></ret>");
    		}
		} else
		if ("D".equals(acao)) {
			if (checkEmUso(ed)) {
				out.println("<ret><item oknok='Impossível excluir! Marca em uso!' /></ret>");
			} else {
				this.delete(ed);
				out.println("<ret><item oknok='DOK' /></ret>");
			}
		} else {
		out.println("<cad>");
		if ("CB".equals(acao)) { ed.setCd_Fabricante_Banda("999999"); } // Colocado para buscar tambem o registro "ORIGINAL"
		String saida = null;
		ArrayList lst = new ArrayList();
		lst = this.lista(ed);
		for (int i=0; i<lst.size(); i++){
			Fabricante_BandaED edVolta = new Fabricante_BandaED();
			edVolta = (Fabricante_BandaED)lst.get(i);
			if ("L".equals(acao)) {
				saida = "<item ";
				saida += "oid_Fabricante_Banda='" + edVolta.getOid_Fabricante_Banda() + "' ";
				saida += "oid_Empresa='" + edVolta.getOid_Empresa() + "' ";
				saida += "nm_Fabricante_Banda='" + edVolta.getNm_Fabricante_Banda() + "' ";
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
				saida += "value='" + edVolta.getOid_Fabricante_Banda() + "'>";
				saida +=  edVolta.getNm_Fabricante_Banda();
				saida += "</item>";
			}
			out.println(saida);
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    public boolean checkDuplo ( Fabricante_BandaED ed, String acao) throws Excecoes {
    	boolean ret = false;
    	Fabricante_BandaED edChk = new Fabricante_BandaED();
    	edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setNm_Fabricante_Banda(ed.getNm_Fabricante_Banda());
		edChk = this.getByRecord(edChk);
    	if ("I".equals(acao) && edChk.getOid_Fabricante_Banda()>0)
    		ret = true;
    	else
        	if ("A".equals(acao) && edChk.getOid_Fabricante_Banda()>0 ) {
    			if (ed.getOid_Fabricante_Banda()!=edChk.getOid_Fabricante_Banda() )
    				ret = true ;
    	}
    	return ret;
    }

    public boolean checkEmUso ( Fabricante_BandaED ed ) throws Excecoes {
		try {
			BandaED bandaED = new BandaED();
			bandaED.setOid_Empresa(ed.getOid_Empresa());
			bandaED.setOid_Fabricante_Banda(ed.getOid_Fabricante_Banda());
			this.inicioTransacao();
			return (new BandaBD(this.sql).lista(bandaED).size()>0 ? true : false);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void processaRequisicao(Fabricante_BandaED ed,
			HttpServletRequest request) throws ServletException, IOException,
			Excecoes {
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
		// FIM PADRAO!
		if ("I".equals(acao)) {
			this.inclui(ed);
		} else if ("A".equals(acao)) {
			this.altera(ed);
		} else if ("D".equals(acao)) {
			this.delete(ed);
		} else {
			ArrayList lst = new ArrayList();
			lst = this.lista(ed);
			if ("L".equals(acao)) {
				request.setAttribute("lista", lst);
			} else if ("M".equals(acao) || "S".equals(acao)) {
				request.setAttribute("fabricante_banda", (Fabricante_BandaED) lst.get(0));
			}
		}
	}

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}