package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Menu_PerfilBD;
import com.master.ed.Menu_PerfilED;
import com.master.ed.SistemaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Menus Perfil
 * @serialData 05/2007
 */
public class Menu_PerfilRN extends Transacao {

    public Menu_PerfilRN() {
    }

    public Menu_PerfilED inclui(Menu_PerfilED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Menu_PerfilBD(this.sql).inclui(ed);
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

    public void altera(Menu_PerfilED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Menu_PerfilBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Menu_PerfilED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Menu_PerfilBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Menu_PerfilED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Menu_PerfilBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Menu_PerfilED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Menu_PerfilBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Menu_PerfilED getByRecord(Menu_PerfilED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Menu_PerfilBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(Menu_PerfilED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		Menu_PerfilED edQBR = new Menu_PerfilBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Menu_Perfil()== 0 ? null : edQBR);

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
    	Menu_PerfilED mpED = (Menu_PerfilED)Obj;
    	//Prepara a saída
    	
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		mpED = this.inclui(mpED);
    		out.println("<ret><item oknok='IOK' oid='" + mpED.getOid_Menu_Perfil() + "'/></ret>");
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
					Menu_PerfilED edVolta = new Menu_PerfilED();
					edVolta = (Menu_PerfilED)lst.get(i);
					saida = "<item ";
					saida += "oid_Menu_Perfil='" + edVolta.getOid_Menu_Perfil() + "' ";
					saida += "oid_Sistema='" + edVolta.getOid_Sistema() + "' ";
					saida += "nm_Menu_Perfil='" + edVolta.getNm_Menu_Perfil() + "' ";
					saida += "nm_Sistema='" + edVolta.getNm_Sistema() + "' ";
					saida += ">";
					saida += "</item>";
					out.println(saida);
				}
			} else
			if ("CB".equals(acao)) {
				String saida;
				ArrayList lst = new ArrayList();
				lst = this.lista(mpED);
				for (int i=0; i<lst.size(); i++){
					Menu_PerfilED edVolta = new Menu_PerfilED();
					edVolta = (Menu_PerfilED)lst.get(i);
					saida = "<item ";
					saida += "value='" + edVolta.getOid_Menu_Perfil() + "'>";
					saida +=  edVolta.getNm_Menu_Perfil();
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