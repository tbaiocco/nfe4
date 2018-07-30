package com.master.rn;

import com.master.bd.Parametro_ContaBD;
import com.master.ed.Parametro_ContaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Parametro_ContaRN extends Transacao {

    public Parametro_ContaRN() {
    }
    public void altera(Parametro_ContaED ed) throws Excecoes {
        inicioTransacao();
        try {
            new Parametro_ContaBD(sql).altera(ed);
            fimTransacao(true);
        } catch (Excecoes e) {
            abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public Parametro_ContaED getByRecord(Parametro_ContaED ed) throws Excecoes {
        inicioTransacao();
        try {
            return new Parametro_ContaBD(this.sql).getByRecord(ed);
        } finally {
            fimTransacao(false);
        }
    }
}