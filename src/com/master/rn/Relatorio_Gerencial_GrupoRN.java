package com.master.rn;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.bd.Relatorio_Gerencial_GrupoBD;
import com.master.ed.Relatorio_Gerencial_GrupoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Regis Steigleder
 * @serial Relatorios Gerenciais
 * @serialData 08/01/2006
 */

public class Relatorio_Gerencial_GrupoRN extends Transacao {

    public Relatorio_Gerencial_GrupoRN() {
    }
   
    public Relatorio_Gerencial_GrupoED inclui(Relatorio_Gerencial_GrupoED ed ) throws Excecoes {

        try {
        	this.inicioTransacao();
			ed = new Relatorio_Gerencial_GrupoBD(this.sql).inclui(ed);
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

    public void altera(Relatorio_Gerencial_GrupoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Relatorio_Gerencial_GrupoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Relatorio_Gerencial_GrupoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Relatorio_Gerencial_GrupoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Relatorio_Gerencial_GrupoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Relatorio_Gerencial_GrupoBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public void lista(Relatorio_Gerencial_GrupoED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Relatorio_Gerencial_GrupoBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
            request.setAttribute("disabled",lista.size() > 0 ? "disabled": " ") ;
        } finally {
            this.fimTransacao(false);
        }
    }

    public Relatorio_Gerencial_GrupoED getByRecord(Relatorio_Gerencial_GrupoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Relatorio_Gerencial_GrupoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
	public void getByRecord(Relatorio_Gerencial_GrupoED ed, HttpServletRequest request, String nmObj) throws Excecoes {

		try {
			this.inicioTransacao();
			Relatorio_Gerencial_GrupoED edQBR = new Relatorio_Gerencial_GrupoBD(this.sql).getByRecord(ed);
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