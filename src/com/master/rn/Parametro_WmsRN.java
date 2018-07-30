package com.master.rn;

import com.master.bd.Parametro_WmsBD;
import com.master.ed.Parametro_WmsED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Parametro_WmsRN extends Transacao {

    public Parametro_WmsRN() {
    }

    public void altera(Parametro_WmsED ed) throws Excecoes {
        inicioTransacao();
        try {
            new Parametro_WmsBD(sql).altera(ed);
            fimTransacao(true);
        } catch (Excecoes e) {
            abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public Parametro_WmsED getByRecord(Parametro_WmsED ed) throws Excecoes {
    	// System.out.println("PAR WMS - RN...");
        inicioTransacao();
        try {
            return new Parametro_WmsBD(this.sql).getByRecord(ed);
        } finally {
            fimTransacao(false);
        }
    }
}