package com.master.rn;

import java.util.ArrayList;

import com.master.bd.ReciboBD;
import com.master.ed.ReciboED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @JavaBean.class Recibos
 * @serialData 13/10/2005
 */
public class ReciboRN extends Transacao {

    public ReciboRN() {
    }

    public ReciboED inclui(ReciboED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new ReciboBD(this.sql).inclui(ed);
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

    public void altera(ReciboED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new ReciboBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(ReciboED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new ReciboBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(ReciboED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new ReciboBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public ReciboED getByRecord(ReciboED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new ReciboBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    /** ------------ RELATÓRIOS ---------------- */ 
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}