package com.master.rn;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;


import com.master.bd.Tecnico_SuporteBD;

import com.master.ed.Tecnico_SuporteED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Jeanine e Vinícius
 * @serial Técnico_Suporte
 * @serialData 21/05/2008
 */

public class Tecnico_SuporteRN extends Transacao {

    public Tecnico_SuporteRN() {
    }
   
    public Tecnico_SuporteED inclui(Tecnico_SuporteED ed ) throws Excecoes {

        try {
        	this.inicioTransacao();
            ed = new Tecnico_SuporteBD(this.sql).inclui(ed);
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

    public void altera(Tecnico_SuporteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Tecnico_SuporteBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Tecnico_SuporteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Tecnico_SuporteBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Tecnico_SuporteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Tecnico_SuporteBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public void lista(Tecnico_SuporteED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Tecnico_SuporteBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

   public Tecnico_SuporteED getByRecord(Tecnico_SuporteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Tecnico_SuporteBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    /*public Tecnico_SuporteED getByRecord(Tecnico_SuporteED ed, HttpServletRequest request, String nmObj) throws Excecoes {

		try {
			this.inicioTransacao();
			Tecnico_SuporteED edQBR = new Tecnico_SuporteBD(this.sql).getByRecord(ed);
			request.setAttribute(nmObj, edQBR.getOid_Tecnico()== 0 ? null : edQBR);
			 return new Tecnico_SuporteBD(this.sql).getByRecord(ed);
		} finally { 
			this.fimTransacao(false);
		}
	}
    */
    
    
	public boolean isCadastrado() {
		return false;
	}

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}