package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Marca_VeiculoBD;
import com.master.bd.Modelo_VeiculoBD;
import com.master.bd.Tipo_PneuBD;
import com.master.bd.PneuBD;
import com.master.ed.Dimensao_PneuED;
import com.master.ed.Marca_VeiculoED;
import com.master.ed.Modelo_VeiculoED;
import com.master.ed.Tipo_PneuED;
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
public class Tipo_PneuRN extends Transacao {

    public Tipo_PneuRN() {
    }

    public Tipo_PneuED inclui(Tipo_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Tipo_PneuBD(this.sql).inclui(ed);
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

    public void altera(Tipo_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Tipo_PneuBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Tipo_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Tipo_PneuBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Tipo_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Tipo_PneuBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Tipo_PneuED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Tipo_PneuBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Tipo_PneuED getByRecord(Tipo_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Tipo_PneuBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(Tipo_PneuED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		Tipo_PneuED edQBR = new Tipo_PneuBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Tipo_Pneu()== 0 ? null : edQBR);

    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorio(Tipo_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Tipo_PneuBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns005"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNm_Tipo_Pneu()))
				nm_Filtro+=" Tipo Pneu=" + ed.getNm_Tipo_Pneu();
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
	    Tipo_PneuED ed = (Tipo_PneuED)Obj;
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
    	Tipo_PneuED ed = (Tipo_PneuED)Obj;
    	//Prepara a saída
    	ed.setMasterDetails(request);

    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		if (checkDuplo(ed,acao)) {
    			out.println("<ret><item oknok='Tipo já existente com esta descricao !'/></ret>");
    		} else {
	    		ed = this.inclui(ed);
	    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Tipo_Pneu() + "' /></ret>");
    		}
    	} else
		if ("A".equals(acao)) {
    		if (checkDuplo(ed,acao)) {
    			out.println("<ret><item oknok='Tipo já existente com esta descricao !'/></ret>");
    		} else {
				this.altera(ed);
				out.println("<ret><item oknok='AOK' /></ret>");
    		}
		} else
		if ("D".equals(acao)) {
			if (checkEmUso(ed)) {
				out.println("<ret><item oknok='Impossível excluir! Tipo em uso!' /></ret>");
			} else {
				this.deleta(ed);
				out.println("<ret><item oknok='DOK' /></ret>");

			}
		} else {
		out.println("<cad>");
		String saida;
		ArrayList lst = new ArrayList();
		lst = this.lista(ed);
		for (int i=0; i<lst.size(); i++){
			Tipo_PneuED edVolta = new Tipo_PneuED();
			edVolta = (Tipo_PneuED)lst.get(i);
			if ("L".equals(acao)) {
				saida = "<item ";
				saida += "oid_Tipo_Pneu='" + edVolta.getOid_Tipo_Pneu() + "' ";
				saida += "nm_Tipo_Pneu='" + edVolta.getNm_Tipo_Pneu() + "' ";
				saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
				saida += "/>";
				out.println(saida);
			}else
			if ("CB".equals(acao)) {
				saida = "<item ";
				saida += "value='" + edVolta.getOid_Tipo_Pneu() + "'>";
				saida +=  edVolta.getNm_Tipo_Pneu();
				saida += "</item>";
				out.println(saida);
			}
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    public void processaRequisicao(Tipo_PneuED ed,
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
				request.setAttribute("tipo_pneu", (Tipo_PneuED) lst
						.get(0));
			}
		}
	}

    public boolean checkDuplo ( Tipo_PneuED ed, String acao) throws Excecoes {
    	boolean ret = false;
    	Tipo_PneuED edChk = new Tipo_PneuED();
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setNm_Tipo_Pneu(ed.getNm_Tipo_Pneu());
		edChk = this.getByRecord(edChk);
    	if ("I".equals(acao) && edChk.getOid_Tipo_Pneu()>0)
    		ret = true;
    	else
        	if ("A".equals(acao) && edChk.getOid_Tipo_Pneu()>0 ) {
    			if (ed.getOid_Tipo_Pneu()!=edChk.getOid_Tipo_Pneu() )
    				ret = true ;
    	}
    	return ret;
    }

    public boolean checkEmUso ( Tipo_PneuED ed ) throws Excecoes {
		try {
			PneuED pneuED = new PneuED();
			pneuED.setOid_Empresa(ed.getOid_Empresa());
			pneuED.setOid_Tipo_Pneu(ed.getOid_Tipo_Pneu());
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