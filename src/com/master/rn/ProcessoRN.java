package com.master.rn;

import java.util.ArrayList;

import com.master.bd.ProcessoBD;
import com.master.ed.ProcessoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class ProcessoRN extends Transacao {

    public ProcessoRN() {
    }

    public ProcessoED inclui(ProcessoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new ProcessoBD(this.sql).inclui(ed);
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

    public void altera(ProcessoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new ProcessoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void alteraSituacao(ProcessoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new ProcessoBD(this.sql).alteraSituacao(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void deleta(ProcessoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new ProcessoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista_Fatura(ProcessoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new ProcessoBD(sql).lista_Fatura(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista(ProcessoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new ProcessoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ProcessoED getByRecord(ProcessoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new ProcessoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.sql !=  null)
            this.abortaTransacao();
    }
}
