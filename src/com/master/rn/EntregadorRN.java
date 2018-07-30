/*
 * Created on 03/01/2005
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.EntregadorBD;
import com.master.ed.EntregadorED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class EntregadorRN extends Transacao {

    public EntregadorRN() {
    }

    public EntregadorED inclui( EntregadorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new EntregadorBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void altera( EntregadorED ed) throws Excecoes {

        try{
            this.inicioTransacao();
            new EntregadorBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void deleta( EntregadorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new EntregadorBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista(EntregadorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new EntregadorBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public EntregadorED getByRecord(EntregadorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new EntregadorBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}