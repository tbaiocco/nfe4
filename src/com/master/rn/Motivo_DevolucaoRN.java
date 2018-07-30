package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Motivo_DevolucaoBD;
import com.master.ed.Motivo_DevolucaoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Motivos de Devolução dos Cheques
 * @serialData 24/08/2005
 */
public class Motivo_DevolucaoRN extends Transacao {

    public Motivo_DevolucaoRN() {
    }

    public Motivo_DevolucaoED inclui(Motivo_DevolucaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Motivo_DevolucaoBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(Motivo_DevolucaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Motivo_DevolucaoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Motivo_DevolucaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Motivo_DevolucaoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Motivo_DevolucaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Motivo_DevolucaoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Motivo_DevolucaoED getByRecord(Motivo_DevolucaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Motivo_DevolucaoBD(sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
}