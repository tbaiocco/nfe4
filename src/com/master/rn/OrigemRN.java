package com.master.rn;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.bd.OrigemBD;
import com.master.ed.OrigemED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Origens
 * @serialData 14/10/2005
 */
public class OrigemRN extends Transacao {

    public OrigemRN() {
    }
   
    public OrigemED inclui(OrigemED ed ) throws Excecoes {

        try {
        	this.inicioTransacao();
            ed = new OrigemBD(this.sql).inclui(ed);
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

    public void altera(OrigemED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new OrigemBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(OrigemED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new OrigemBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(OrigemED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new OrigemBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public void lista(OrigemED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new OrigemBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public OrigemED getByRecord(OrigemED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new OrigemBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
	public void getByRecord(OrigemED ed, HttpServletRequest request, String nmObj) throws Excecoes {

		try {
			this.inicioTransacao();
			OrigemED edQBR = new OrigemBD(this.sql).getByRecord(ed);
			request.setAttribute(nmObj, edQBR.getOID_Origem()== 0 ? null : edQBR);
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