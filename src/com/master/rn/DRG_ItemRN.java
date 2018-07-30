package com.master.rn;

import java.util.ArrayList;

import com.master.bd.DRG_ItemBD;
import com.master.ed.DRG_ItemED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Itens do D.R.G.
 * @serialData 14/10/2005
 */
public class DRG_ItemRN extends Transacao {

    public DRG_ItemRN() {
    }

    public DRG_ItemED inclui(DRG_ItemED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new DRG_ItemBD(this.sql).inclui(ed);
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

    public void altera(DRG_ItemED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new DRG_ItemBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(DRG_ItemED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new DRG_ItemBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(DRG_ItemED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new DRG_ItemBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public DRG_ItemED getByRecord(DRG_ItemED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new DRG_ItemBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}