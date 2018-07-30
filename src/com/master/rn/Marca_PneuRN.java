package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Marca_PneuBD;
import com.master.bd.PneuBD;
import com.master.ed.Marca_PneuED;
import com.master.ed.PneuED;
import com.master.ed.UsuarioED;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.RequestUtil;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Marcas de pneus
 * @serialData 05/2007
 */
public class Marca_PneuRN extends Transacao {

    public Marca_PneuRN() {
    }

    public Marca_PneuED inclui(Marca_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Marca_PneuBD(this.sql).inclui(ed);
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

    public void altera(Marca_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Marca_PneuBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Marca_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Marca_PneuBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Marca_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Marca_PneuBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Marca_PneuED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Marca_PneuBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Marca_PneuED getByRecord(Marca_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Marca_PneuBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(Marca_PneuED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		Marca_PneuED edQBR = new Marca_PneuBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Marca_Pneu()== 0 ? null : edQBR);

    	} finally {
    		this.fimTransacao(false);
    	}
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
    	Marca_PneuED ed = (Marca_PneuED)Obj;
    	//Prepara a saída

    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		this.inclui(ed);
    		out.println("<ret><item oknok='IOK' oid='1234'/></ret>");
    	} else
		if ("A".equals(acao)) {
			this.altera(ed);
			out.println("<ret><item oknok='AOK' /></ret>");
		} else
		if ("D".equals(acao)) {
			this.deleta(ed);
			out.println("<ret><item oknok='DOK' /></ret>");
		} else {
		out.println("<cad>");
		String saida = null;
		ArrayList lst = new ArrayList();
		lst = this.lista(ed);
		for (int i=0; i<lst.size(); i++){
			Marca_PneuED edVolta = new Marca_PneuED();
			edVolta = (Marca_PneuED)lst.get(i);
			if ("L".equals(acao)) {
				saida = "<item ";
				saida += "oid_Marca_Pneu='" + edVolta.getOid_Marca_Pneu() + "' ";
				saida += "nm_Marca_Pneu='" + edVolta.getNm_Marca_Pneu() + "' ";
				saida += "/>";
			} else
			if ("CB".equals(acao)) {
				saida = "<item ";
				saida += "value='" + edVolta.getOid_Marca_Pneu() + "'> ";
				saida += edVolta.getNm_Marca_Pneu();
				saida += "</item>";
			}
			out.println(saida);
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    public void processaRequisicao(Marca_PneuED ed, HttpServletRequest request)
			throws ServletException, IOException, Excecoes {
    	Object toReturn = new Object();
    	//PADRAO PARA TODAS...
    	String acao = JavaUtil.getValueDef(request.getParameter("acao"), "M");
    	UsuarioED user = (UsuarioED)RequestUtil.getSessionAttribute(request, "usuario");
    	ed.setOid_Empresa(user.getOid_Empresa());
    	ed.setUser(user.getOid_Usuario().intValue());
    	ed.setUsuario_Stamp(user.getNm_Usuario());
    	ed.setDm_Stamp(acao);
    	ed.setTime_millis(System.currentTimeMillis());
    	System.out.println(acao);
    	//FIM PADRAO!
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
				request.setAttribute("marcaLista", lst);
				request.setAttribute("lista", lst);
			} else if ("M".equals(acao) || "S".equals(acao)) {
				request.setAttribute("marca", (Marca_PneuED)lst.get(0));
				request.setAttribute("marca_pneu", (Marca_PneuED)lst.get(0));
			}
		}
	}

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}