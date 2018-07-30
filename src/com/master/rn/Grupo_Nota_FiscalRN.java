package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Grupo_Nota_FiscalBD;
import com.master.ed.Grupo_Nota_FiscalED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Grupos Notas Fiscais
 * @serialData 17/06/2005
 */
public class Grupo_Nota_FiscalRN extends Transacao {

    public Grupo_Nota_FiscalRN() {
    }

    public Grupo_Nota_FiscalED inclui(Grupo_Nota_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Grupo_Nota_FiscalBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(Grupo_Nota_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Grupo_Nota_FiscalBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Grupo_Nota_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Grupo_Nota_FiscalBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Grupo_Nota_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Grupo_Nota_FiscalBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Grupo_Nota_FiscalED getByRecord(Grupo_Nota_FiscalED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Grupo_Nota_FiscalBD(sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
}