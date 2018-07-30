package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Modelo_Nota_FiscalBD;
import com.master.ed.Modelo_Nota_FiscalED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * - Modelos de Notas Fiscais
 */
public class Modelo_Nota_FiscalRN extends Transacao {

    public Modelo_Nota_FiscalRN() {
    }

    public Modelo_Nota_FiscalED inclui(Modelo_Nota_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Modelo_Nota_FiscalBD(this.sql).inclui(ed);
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

    public void altera(Modelo_Nota_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Modelo_Nota_FiscalBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Modelo_Nota_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Modelo_Nota_FiscalBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Modelo_Nota_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Modelo_Nota_FiscalBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public Modelo_Nota_FiscalED getByRecord(Modelo_Nota_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Modelo_Nota_FiscalBD(this.sql).getByRecord(ed);
            return ed;
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}
