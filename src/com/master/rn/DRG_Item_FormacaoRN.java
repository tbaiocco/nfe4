package com.master.rn;

import java.util.ArrayList;

import com.master.bd.DRG_Item_FormacaoBD;
import com.master.ed.DRG_Item_FormacaoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Formações dos Itens do D.R.G.
 * @serialData 14/10/2005
 */
public class DRG_Item_FormacaoRN extends Transacao {

    public DRG_Item_FormacaoRN() {
    }

    public DRG_Item_FormacaoED inclui(DRG_Item_FormacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new DRG_Item_FormacaoBD(this.sql).inclui(ed);
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

    public void altera(DRG_Item_FormacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new DRG_Item_FormacaoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(DRG_Item_FormacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new DRG_Item_FormacaoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(DRG_Item_FormacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new DRG_Item_FormacaoBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public DRG_Item_FormacaoED getByRecord(DRG_Item_FormacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new DRG_Item_FormacaoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}