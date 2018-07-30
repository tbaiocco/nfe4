package com.master.rn;

import java.util.ArrayList;
import com.master.bd.Ordem_EmbarqueBD;
import com.master.ed.Ordem_EmbarqueED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 */
public class Ordem_EmbarqueRN extends Transacao {

    public Ordem_EmbarqueRN() {
    }

    public Ordem_EmbarqueED inclui(Ordem_EmbarqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Ordem_EmbarqueBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Ordem_EmbarqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Ordem_EmbarqueBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista(Ordem_EmbarqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Ordem_EmbarqueBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public Ordem_EmbarqueED getByRecord(Ordem_EmbarqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Ordem_EmbarqueBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}
