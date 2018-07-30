package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.ArrayList;

import com.master.bd.CategoriaBD;
import com.master.ed.CategoriaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class CategoriaRN extends Transacao {

    public CategoriaRN() {
    }

    public CategoriaED inclui(CategoriaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new CategoriaBD(this.sql).inclui(ed);
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

    public void altera(CategoriaED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new CategoriaBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void deleta(CategoriaED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new CategoriaBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista(CategoriaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CategoriaBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public CategoriaED getByRecord(CategoriaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CategoriaBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}