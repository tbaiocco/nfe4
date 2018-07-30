package com.master.rn;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.bd.Relatorio_Gerencial_ContaBD;
import com.master.ed.Relatorio_Gerencial_ContaED;
import com.master.bd.Relatorio_Gerencial_MetaBD;
import com.master.ed.Relatorio_Gerencial_MetaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Regis Steigleder
 * @serial Relatorios Gerenciais
 * @serialData 08/01/2006
 */

public class Relatorio_Gerencial_ContaRN extends Transacao {

    public Relatorio_Gerencial_ContaRN() {
    }

    public Relatorio_Gerencial_ContaED inclui(Relatorio_Gerencial_ContaED ed ) throws Excecoes {

        try {
        	this.inicioTransacao();
			ed = new Relatorio_Gerencial_ContaBD(this.sql).inclui(ed);
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

    public void altera(Relatorio_Gerencial_ContaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Relatorio_Gerencial_ContaBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public boolean deleta(Relatorio_Gerencial_ContaED ed) throws Excecoes {

    	try {
            this.inicioTransacao();
            new Relatorio_Gerencial_ContaBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
        return true;
    }

    public ArrayList lista(Relatorio_Gerencial_ContaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Relatorio_Gerencial_ContaBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public void lista(Relatorio_Gerencial_ContaED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Relatorio_Gerencial_ContaBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
            request.setAttribute("disabled",lista.size() > 0 ? "disabled": " ") ;
        } finally {
            this.fimTransacao(false);
        }
    }

    public Relatorio_Gerencial_ContaED getByRecord(Relatorio_Gerencial_ContaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Relatorio_Gerencial_ContaBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
	public void getByRecord(Relatorio_Gerencial_ContaED ed, HttpServletRequest request, String nmObj) throws Excecoes {

		try {
			this.inicioTransacao();
			Relatorio_Gerencial_ContaED edQBR = new Relatorio_Gerencial_ContaBD(this.sql).getByRecord(ed);
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