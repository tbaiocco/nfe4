package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Movimento_Conta_CorrenteBD;
import com.master.ed.Movimento_Conta_CorrenteED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Movimento_Conta_CorrenteRN extends Transacao {

    public Movimento_Conta_CorrenteRN() {
    }

    public Movimento_Conta_CorrenteED inclui(Movimento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            Movimento_Conta_CorrenteED toReturn = new Movimento_Conta_CorrenteBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return toReturn;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public Movimento_Conta_CorrenteED transfere(Movimento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Movimento_Conta_CorrenteBD(this.sql).transfere(ed);
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

    public Movimento_Conta_CorrenteED geraSaldoInicial(Movimento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Movimento_Conta_CorrenteBD(this.sql).geraSaldoInicial(ed);
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

    public Movimento_Conta_CorrenteED geraLancamentoMotoristas(Movimento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Movimento_Conta_CorrenteBD(this.sql).geraLancamentoMotoristas(ed);
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

    public Movimento_Conta_CorrenteED finalizaMovimento(Movimento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Movimento_Conta_CorrenteBD(this.sql).finalizaMovimento(ed);
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

    public Movimento_Conta_CorrenteED acertaSaldo(Movimento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Movimento_Conta_CorrenteBD(this.sql).acertaSaldo(ed);
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

    public Movimento_Conta_CorrenteED consultaSaldo(Movimento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Movimento_Conta_CorrenteBD(this.sql).consultaSaldo(ed);
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

    public void altera(Movimento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Movimento_Conta_CorrenteBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    public void alteraConta(Movimento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Movimento_Conta_CorrenteBD(this.sql).alteraConta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Movimento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Movimento_Conta_CorrenteBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Movimento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Movimento_Conta_CorrenteBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList listaSaldo(Movimento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Movimento_Conta_CorrenteBD(sql).listaSaldo(ed);
        } finally {
            this.fimTransacao(false);
        }
    }


    public Movimento_Conta_CorrenteED getByRecord(Movimento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Movimento_Conta_CorrenteBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] imprime_Movimento_Conta_Corrente(Movimento_Conta_CorrenteED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            return new Movimento_Conta_CorrenteBD(this.sql).imprime_Movimento_Conta_Corrente(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] gera_Rel_Movimento_Conta_Corrente(Movimento_Conta_CorrenteED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            return new Movimento_Conta_CorrenteBD(this.sql).gera_Rel_Movimento_Conta_Corrente(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}
