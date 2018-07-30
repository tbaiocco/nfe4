package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Controle_TarefasBD;
import com.master.ed.Controle_TarefasED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Cristian Vianna Garcia
 * @serial Controle de Tarefas
 * @serialData 05/2007
 */
public class Controle_TarefasRN extends Transacao {

    public Controle_TarefasRN() {
    }

    public Controle_TarefasED inclui(Controle_TarefasED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Controle_TarefasBD(this.sql).inclui(ed);
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

    public void altera(Controle_TarefasED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Controle_TarefasBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Controle_TarefasED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Controle_TarefasBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Controle_TarefasED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Controle_TarefasBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Controle_TarefasED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Controle_TarefasBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Controle_TarefasED getByRecord(Controle_TarefasED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Controle_TarefasBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(Controle_TarefasED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		Controle_TarefasED edQBR = new Controle_TarefasBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Tarefa()== 0 ? null : edQBR);

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
    	Controle_TarefasED mpED = (Controle_TarefasED)Obj;
    	//Prepara a saída
    	
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		Controle_TarefasED tarefaIncluido=this.inclui(mpED);
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
				Controle_TarefasED edVolta = new Controle_TarefasED();
				edVolta = (Controle_TarefasED)lst.get(i);
				saida = "<item ";
				saida += "oid_Tarefa='" + edVolta.getOid_Tarefa() + "' ";
				saida += "NM_Titulo='" + edVolta.getNM_Titulo() + "' ";
				saida += "NM_Descricao='" + edVolta.getNM_Descricao() + "' ";
				saida += "oid_Responsavel='" + edVolta.getOid_Responsavel() + "' ";
				saida += "NM_Responsavel='" + edVolta.getNM_Responsavel() + "' ";
				saida += "oid_Prioridade='" + edVolta.getOid_Prioridade() + "' ";
				saida += "NM_Prioridade='" + edVolta.getNM_Prioridade() + "' ";
				saida += "oid_Situacao='" + edVolta.getOid_Situacao() + "' ";
				saida += "NM_Situacao='" + edVolta.getNM_Situacao() + "' ";
				saida += "/>";
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