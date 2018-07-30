package com.master.rn;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;


import com.master.bd.Empresa_HelpBD;

import com.master.ed.Empresa_HelpED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Jeanine e Vinícius
 * @serial Técnico_Suporte
 * @serialData 21/05/2008
 */

public class Empresa_HelpRN extends Transacao {

    public Empresa_HelpRN() {
    } 
    
    public Empresa_HelpED inclui(Empresa_HelpED ed ) throws Excecoes {

        try {
        	this.inicioTransacao();
            ed = new Empresa_HelpBD(this.sql).inclui(ed);
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

    public void altera(Empresa_HelpED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Empresa_HelpBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Empresa_HelpED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Empresa_HelpBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Empresa_HelpED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Empresa_HelpBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public void lista(Empresa_HelpED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Empresa_HelpBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

   public Empresa_HelpED getByRecord(Empresa_HelpED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Empresa_HelpBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
   
	public boolean isCadastrado() {
		return false;
	}

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}