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

import com.master.bd.Produto_PatrimonioBD;
import com.master.ed.Produto_PatrimonioED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Produto_PatrimonioRN extends Transacao {

    public Produto_PatrimonioRN() {
    }

    public Produto_PatrimonioED inclui(Produto_PatrimonioED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Produto_PatrimonioBD(this.sql).inclui(ed);
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

    public void altera(Produto_PatrimonioED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new Produto_PatrimonioBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void deleta(Produto_PatrimonioED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new Produto_PatrimonioBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista(Produto_PatrimonioED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Produto_PatrimonioBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Produto_PatrimonioED getByRecord(Produto_PatrimonioED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Produto_PatrimonioBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}