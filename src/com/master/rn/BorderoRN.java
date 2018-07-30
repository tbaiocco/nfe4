package com.master.rn;

import java.util.ArrayList;

import com.master.bd.BorderoBD;
import com.master.ed.BorderoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class BorderoRN extends Transacao {

    public BorderoRN() {
    }

    public BorderoED inclui(BorderoED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            ed = new BorderoBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(BorderoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new BorderoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(BorderoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new BorderoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(BorderoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new BorderoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public BorderoED getByRecord(BorderoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new BorderoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] geraRelMovimentoBordero(BorderoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new BorderoBD(this.sql).geraRelMovimentoBordero(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}