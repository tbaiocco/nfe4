package com.master.rn;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.bd.Relatorio_GerencialBD;
import com.master.ed.Relatorio_GerencialED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Origens
 * @serialData 14/10/2005
 */

public class Relatorio_GerencialRN extends Transacao {

    public Relatorio_GerencialRN() {
    }
   
    public Relatorio_GerencialED inclui(Relatorio_GerencialED ed,HttpServletRequest request ) throws Excecoes {

        try {
        	this.inicioTransacao();
        	Relatorio_GerencialED edQBR = new Relatorio_GerencialBD(this.sql).getByRecord(ed);
        	if (edQBR.getOid_Relatorio_Gerencial()== 0) 
        	{
        		request.setAttribute("erro", "F");
                ed = new Relatorio_GerencialBD(this.sql).inclui(ed);
                this.fimTransacao(true);
        	} else {
        		request.setAttribute("erro", "T");
        	}
            return ed;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public Relatorio_GerencialED inclui(Relatorio_GerencialED ed ) throws Excecoes {

        try {
        	this.inicioTransacao();
			ed = new Relatorio_GerencialBD(this.sql).inclui(ed);
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

    public void altera(Relatorio_GerencialED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Relatorio_GerencialBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Relatorio_GerencialED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Relatorio_GerencialBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Relatorio_GerencialED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Relatorio_GerencialBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public void lista(Relatorio_GerencialED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Relatorio_GerencialBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Relatorio_GerencialED getByRecord(Relatorio_GerencialED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Relatorio_GerencialBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
	public void getByRecord(Relatorio_GerencialED ed, HttpServletRequest request, String nmObj) throws Excecoes {

		try {
			this.inicioTransacao();
			Relatorio_GerencialED edQBR = new Relatorio_GerencialBD(this.sql).getByRecord(ed);
			request.setAttribute(nmObj, edQBR.getOid_Relatorio_Gerencial()== 0 ? null : edQBR);
		} finally { 
			this.fimTransacao(false);
		}
	}

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}