package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Combo_PrioridadeBD;
import com.master.ed.Combo_PrioridadeED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Cristian Vianna Garcia
 * @serial Combo_Prioridade
 * @serialData 06/2007
 */
public class Combo_PrioridadeRN extends Transacao {

    public Combo_PrioridadeRN() {
    }

    public Combo_PrioridadeED inclui(Combo_PrioridadeED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Combo_PrioridadeBD(this.sql).inclui(ed);
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

    public void altera(Combo_PrioridadeED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Combo_PrioridadeBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Combo_PrioridadeED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Combo_PrioridadeBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Combo_PrioridadeED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Combo_PrioridadeBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Combo_PrioridadeED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Combo_PrioridadeBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Combo_PrioridadeED getByRecord(Combo_PrioridadeED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Combo_PrioridadeBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(Combo_PrioridadeED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		Combo_PrioridadeED edQBR = new Combo_PrioridadeBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Prioridade()== 0 ? null : edQBR);

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
    	Combo_PrioridadeED mpED = (Combo_PrioridadeED)Obj;
    	//Prepara a saída
    	
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		this.inclui(mpED);
    		out.println("<ret><item oknok='IOK' oid='1234'/></ret>");
    	} else 
		if ("A".equals(acao)) {
			this.altera(mpED);
			out.println("<ret><item oknok='AOK' /></ret>");
		} else 
		if ("D".equals(acao)) {
			this.deleta(mpED);
			out.println("<ret><item oknok='DOK' /></ret>");
		} else {
		out.println("<cad>");
		if ("L".equals(acao)) {
			String saida;
			ArrayList lst = new ArrayList();
			lst = this.lista(mpED);
			for (int i=0; i<lst.size(); i++){
				Combo_PrioridadeED edVolta = new Combo_PrioridadeED();
				edVolta = (Combo_PrioridadeED)lst.get(i);
				saida = "<item ";
				saida += "oid_Prioridade='" + edVolta.getOid_Prioridade() + "' ";
				saida += "nm_Prioridade='" + edVolta.getNm_Prioridade() + "' ";
				saida += "/>";
				out.println(saida);
			}
		} if ("CB".equals(acao)) {
			String saida;
			ArrayList lst = new ArrayList();
			lst = this.lista(mpED);
			for (int i=0; i<lst.size(); i++){
				Combo_PrioridadeED edVolta = new Combo_PrioridadeED();
				edVolta = (Combo_PrioridadeED)lst.get(i);
				saida = "<item ";
				saida += "value='" + edVolta.getOid_Prioridade() + "'>";
				saida +=  edVolta.getNm_Prioridade();
				saida += "</item>";
				out.println(saida);
			}
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}