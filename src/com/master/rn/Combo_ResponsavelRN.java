package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Combo_ResponsavelBD;
import com.master.ed.Combo_ResponsavelED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Cristian Vianna Garcia
 * @serial Combo_Responsável
 * @serialData 06/2007
 */
public class Combo_ResponsavelRN extends Transacao {

    public Combo_ResponsavelRN() {
    }

    public Combo_ResponsavelED inclui(Combo_ResponsavelED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Combo_ResponsavelBD(this.sql).inclui(ed);
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

    public void altera(Combo_ResponsavelED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Combo_ResponsavelBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Combo_ResponsavelED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Combo_ResponsavelBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Combo_ResponsavelED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Combo_ResponsavelBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Combo_ResponsavelED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Combo_ResponsavelBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Combo_ResponsavelED getByRecord(Combo_ResponsavelED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Combo_ResponsavelBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(Combo_ResponsavelED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		Combo_ResponsavelED edQBR = new Combo_ResponsavelBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Responsavel()== 0 ? null : edQBR);

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
    	Combo_ResponsavelED mpED = (Combo_ResponsavelED)Obj;
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
				Combo_ResponsavelED edVolta = new Combo_ResponsavelED();
				edVolta = (Combo_ResponsavelED)lst.get(i);
				saida = "<item ";
				saida += "oid_Responsavel='" + edVolta.getOid_Responsavel() + "' ";
				saida += "nm_Responsavel='" + edVolta.getNm_Responsavel() + "' ";
				saida += "/>";
				out.println(saida);
			}
		} if ("CB".equals(acao)) {
			String saida;
			ArrayList lst = new ArrayList();
			lst = this.lista(mpED);
			for (int i=0; i<lst.size(); i++){
				Combo_ResponsavelED edVolta = new Combo_ResponsavelED();
				edVolta = (Combo_ResponsavelED)lst.get(i);
				saida = "<item ";
				saida += "value='" + edVolta.getOid_Responsavel() + "'>";
				saida +=  edVolta.getNm_Responsavel();
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