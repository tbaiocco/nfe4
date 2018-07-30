package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Local_EstoqueBD;
import com.master.bd.PneuBD;
import com.master.ed.Local_EstoqueED;
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
 * @serial Dimensões de pneus
 * @serialData 06/2007
 */
public class Local_EstoqueRN extends Transacao {

    public Local_EstoqueRN() {
    }

    public Local_EstoqueED inclui(Local_EstoqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Local_EstoqueBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(Local_EstoqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Local_EstoqueBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Local_EstoqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Local_EstoqueBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Local_EstoqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Local_EstoqueBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Local_EstoqueED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Local_EstoqueBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Local_EstoqueED getByRecord(Local_EstoqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Local_EstoqueBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(Local_EstoqueED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		Local_EstoqueED edQBR = new Local_EstoqueBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Local_Estoque()== 0 ? null : edQBR);

    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorio(Local_EstoqueED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Local_EstoqueBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns012"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNm_Unidade()))
				nm_Filtro+=" Unidade=" + ed.getNm_Unidade();
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
    Local_EstoqueED ed = (Local_EstoqueED)Obj;
    ed.setRequest(request);
	//if ("1".equals(rel)) {
		this.relatorio(ed, request, response);
	//}

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
    	Local_EstoqueED ed = (Local_EstoqueED)Obj;
    	//Prepara a saída

    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		if (checkDuplo(ed,acao)) {
    			out.println("<ret><item oknok='Local de Estoque já existente com esta descricao !'/></ret>");
    		} else {
	    		ed = this.inclui(ed);
	    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Local_Estoque() + "' /></ret>");
    		}
    	} else
		if ("A".equals(acao)) {
    		if (checkDuplo(ed,acao)) {
    			out.println("<ret><item oknok='Local de Estoque já existente com esta descricao !'/></ret>");
    		} else {
				this.altera(ed);
				out.println("<ret><item oknok='AOK' /></ret>");
    		}
		} else
		if ("D".equals(acao)) {
			if (checkEmUso(ed)) {
				out.println("<ret><item oknok='Impossível excluir! Local de Estoque em uso!' /></ret>");
			} else {
				this.deleta(ed);
				out.println("<ret><item oknok='DOK' /></ret>");
			}
		} else {
		out.println("<cad>");
		String saida=null;
		ArrayList lst = new ArrayList();
		lst = this.lista(ed);
		for (int i=0; i<lst.size(); i++){
			Local_EstoqueED edVolta = new Local_EstoqueED();
			edVolta = (Local_EstoqueED)lst.get(i);
			if ("L".equals(acao)) {
				saida = "<item ";
				saida += "oid_Local_Estoque='" + edVolta.getOid_Local_Estoque() + "' ";
				saida += "nm_Local_Estoque='" + edVolta.getNm_Local_Estoque() + "' ";
				saida += "oid_Unidade='" + edVolta.getOid_Unidade() + "' ";
				saida += "oid_Empresa='" + edVolta.getOid_Empresa() + "' ";
				saida += "nm_Unidade='" + edVolta.getNm_Unidade() + "' ";
				saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
				saida += "/>";
			}else
				if ("CB".equals(acao) || "CBC".equals(acao)) {
					if ( i==0 && "CBC".equals(acao) ) {
						saida = "<item ";
						saida += "value='0'>TODOS</item>";
						out.println(saida);
					}
				saida = "<item ";
				saida += "value='" + edVolta.getOid_Local_Estoque() + "'>";
				saida +=  edVolta.getNm_Local_Estoque().trim() + " - " + edVolta.getNm_Unidade().trim();
				saida += "</item>";
			}
			out.println(saida);
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    public void processaRequisicao(Local_EstoqueED ed,
			HttpServletRequest request) throws ServletException, IOException,
			Excecoes {
		Object toReturn = new Object();
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
			this.deleta(ed);
		} else {
			ArrayList lst = new ArrayList();
			lst = this.lista(ed);
			if ("L".equals(acao)) {
				request.setAttribute("lista", lst);
			} else if ("M".equals(acao) || "S".equals(acao)) {
				request.setAttribute("local_estoque", (Local_EstoqueED) lst
						.get(0));
			}
		}
	}

    public boolean checkDuplo ( Local_EstoqueED ed, String acao) throws Excecoes {
    	boolean ret = false;
    	Local_EstoqueED edChk = new Local_EstoqueED();
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setNm_Local_Estoque(ed.getNm_Local_Estoque());
		edChk.setOid_Unidade(ed.getOid_Unidade());
		edChk = this.getByRecord(edChk);
    	if ("I".equals(acao) && edChk.getOid_Local_Estoque()>0)
    		ret = true;
    	else
        	if ("A".equals(acao) && edChk.getOid_Local_Estoque()>0 ) {
    			if (ed.getOid_Local_Estoque()!=edChk.getOid_Local_Estoque() )
    				ret = true ;
    	}
    	return ret;
    }

    public boolean checkEmUso ( Local_EstoqueED ed ) throws Excecoes {
		try {
			PneuED pneuED = new PneuED();
			pneuED.setOid_Empresa(ed.getOid_Empresa());
			pneuED.setOid_Local_Estoque(ed.getOid_Local_Estoque());
			this.inicioTransacao();
			return (new PneuBD(this.sql).lista(pneuED).size()>0 ? true : false);
        } finally {
            this.fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}