package com.master.rn;

import java.util.ArrayList;

import com.master.bd.DRGBD;
import com.master.ed.DRGED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Demonstrativos Resumos Gerenciais
 * @serialData 14/10/2005
 */
public class DRGRN extends Transacao {

    public DRGRN() {
    }

    public DRGED inclui(DRGED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new DRGBD(this.sql).inclui(ed);
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

    public void altera(DRGED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new DRGBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(DRGED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new DRGBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(DRGED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new DRGBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public DRGED getByRecord(DRGED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new DRGBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}