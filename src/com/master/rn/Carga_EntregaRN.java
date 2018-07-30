package com.master.rn;

import java.util.ArrayList;
import com.master.bd.Carga_EntregaBD;
import com.master.ed.Carga_EntregaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 */
public class Carga_EntregaRN extends Transacao {

    public Carga_EntregaRN() {
    }

    public Carga_EntregaED inclui(Carga_EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Carga_EntregaBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Carga_EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Carga_EntregaBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista(Carga_EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Carga_EntregaBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public Carga_EntregaED getByRecord(Carga_EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Carga_EntregaBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}
