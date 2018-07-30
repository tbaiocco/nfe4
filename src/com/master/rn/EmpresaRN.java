package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.EmpresaBD;
import com.master.ed.EmpresaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Empresas
 * @serialData 06/2007
 */
public class EmpresaRN extends Transacao {

    public EmpresaRN() {
    }

    public EmpresaED inclui(EmpresaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new EmpresaBD(this.sql).inclui(ed);
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

    public void altera(EmpresaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new EmpresaBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(EmpresaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new EmpresaBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(EmpresaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new EmpresaBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(EmpresaED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new EmpresaBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public EmpresaED getByRecord(EmpresaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new EmpresaBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(EmpresaED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		EmpresaED edQBR = new EmpresaBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Empresa()== 0 ? null : edQBR);
    	} finally {
    		this.fimTransacao(false);
    	}
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
    	EmpresaED ed = (EmpresaED)Obj;
    	//Prepara a saída
    	
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		ed = this.inclui(ed);
    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Empresa() + "' /></ret>");
    	} else 
		if ("A".equals(acao)) {
			this.altera(ed);
			out.println("<ret><item oknok='AOK' /></ret>");
		} else 
		if ("D".equals(acao)) {
			/**
			//Descobrir se tem algum registro com esta esta empresa - Integridade referencial com ...
			ArrayList lst = new ArrayList();
			try {
				PneuED pneuED = new PneuED();
				pneuED.setOid_Empresa(ed.getOid_Empresa());
				pneuED.setOid_Empresa(ed.getOid_Empresa());
				this.inicioTransacao();
				lst = new PneuBD(sql).lista(pneuED);
	    	} finally {
	    		this.fimTransacao(false);
	    	}			
			if (lst.size()>0) {
				out.println("<ret><item oknok='Impossível excluir! Empresa em uso!' /></ret>");
			} else {
			**/
				this.deleta(ed);
				out.println("<ret><item oknok='DOK' /></ret>");
			//}			
		} else {
		out.println("<cad>");
		String saida=null;
		ArrayList lst = new ArrayList();
		lst = this.lista(ed);
		for (int i=0; i<lst.size(); i++){
			EmpresaED edVolta = new EmpresaED();
			edVolta = (EmpresaED)lst.get(i);
			if ("L".equals(acao)) {
				saida = "<item ";
				saida += "oid_Empresa='" + edVolta.getOid_Empresa() + "' ";
				saida += "nm_Empresa='" + edVolta.getNm_Empresa() + "' ";
				saida += "nr_Cnpj_Cpf='" + edVolta.getNr_Cnpj_Cpf() + "' ";
				saida += "dm_Situacao='" + edVolta.getDm_Situacao() + "' ";
				saida += "/>";
			}else
			if ("CB".equals(acao)) {
				saida = "<item ";
				saida += "value='" + edVolta.getOid_Empresa() + "'>";
				saida +=  edVolta.getNm_Empresa();
				saida += "</item>";
			}
			out.println(saida);
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