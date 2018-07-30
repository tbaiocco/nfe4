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

import com.master.bd.Conta_CorrenteBD;
import com.master.ed.Conta_CorrenteED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Conta_CorrenteRN extends Transacao {

    public Conta_CorrenteRN() {
    }

    public Conta_CorrenteED inclui(Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Conta_CorrenteBD(this.sql).inclui(ed);
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

    public void altera(Conta_CorrenteED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new Conta_CorrenteBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void deleta(Conta_CorrenteED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new Conta_CorrenteBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista(Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Conta_CorrenteBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Conta_CorrenteED getByRecord(Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Conta_CorrenteBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}