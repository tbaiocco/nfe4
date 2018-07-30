package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.SistemaBD;
import com.master.ed.SistemaED;
import com.master.bd.Menu_SistemaBD;
import com.master.ed.Menu_SistemaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Sistemas
 * @serialData 05/2007
 */
public class SistemaRN extends Transacao {

    public SistemaRN() {
    }

    public SistemaED inclui(SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new SistemaBD(this.sql).inclui(ed);
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

    public void altera(SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new SistemaBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new SistemaBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new SistemaBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(SistemaED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new SistemaBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public SistemaED getByRecord(SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new SistemaBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(SistemaED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		SistemaED edQBR = new SistemaBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Sistema()== 0 ? null : edQBR);

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
    	SistemaED mpED = (SistemaED)Obj;
    	//Prepara a saída
    	
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		mpED = this.inclui(mpED);
    		out.println("<ret><item oknok='IOK' oid='" + mpED.getOid_Sistema() + "' /></ret>");
    	} else 
		if ("A".equals(acao)) {
			this.altera(mpED);
			out.println("<ret><item oknok='AOK' /></ret>");
		} else 
		if ("D".equals(acao)) {
			ArrayList lst = new ArrayList();
	    	try {
				Menu_SistemaED ed = new Menu_SistemaED();
				ed.setOid_Sistema(new Long(mpED.getOid_Sistema()).intValue());
				this.inicioTransacao();
				lst = new Menu_SistemaBD(sql).lista(ed);
	    	} finally {
	    		this.fimTransacao(false);
	    	}
			if (lst.size()>0) {	
				out.println("<ret><item oknok='Impossível excluir! Sistema tem menus!' /></ret>");
			} else {
				this.deleta(mpED);
				out.println("<ret><item oknok='DOK' /></ret>");
			}
		} else {
			out.println("<cad>");
			if ("L".equals(acao)) {
				String saida;
				ArrayList lst = new ArrayList();
				lst = this.lista(mpED);
				for (int i=0; i<lst.size(); i++){
					SistemaED edVolta = new SistemaED();
					edVolta = (SistemaED)lst.get(i);
					saida = "<item ";
					saida += "oid_Sistema='" + edVolta.getOid_Sistema() + "' ";
					saida += "nm_Sistema='" + edVolta.getNm_Sistema() + "' ";
					saida += "/>";
					out.println(saida);
				}
			} else
			if ("CB".equals(acao)) {
				String saida;
				ArrayList lst = new ArrayList();
				lst = this.lista(mpED);
				for (int i=0; i<lst.size(); i++){
					SistemaED edVolta = new SistemaED();
					edVolta = (SistemaED)lst.get(i);
					saida = "<item ";
					saida += "value='" + edVolta.getOid_Sistema() + "'>";
					saida +=  edVolta.getNm_Sistema();
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