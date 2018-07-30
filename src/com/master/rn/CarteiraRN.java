package com.master.rn;

import java.util.ArrayList;

import com.master.bd.CarteiraBD;
import com.master.ed.CarteiraED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class CarteiraRN extends Transacao {

    public CarteiraRN() {
    }

    public CarteiraED inclui(CarteiraED ed) throws Excecoes {
        inicioTransacao();
        try {
            CarteiraED toReturn = new CarteiraBD(sql).inclui(ed);
            fimTransacao(true);
            return toReturn;
        } catch (Excecoes e) {
            abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public void altera(CarteiraED ed) throws Excecoes {
        inicioTransacao();
        try {
            new CarteiraBD(sql).altera(ed);
            fimTransacao(true);
        } catch (Excecoes e) {
            abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public void deleta(CarteiraED ed) throws Excecoes {
        inicioTransacao();
        try {
            new CarteiraBD(sql).deleta(ed);
            fimTransacao(true);
        } catch (Excecoes e) {
            abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(CarteiraED ed) throws Excecoes {
        inicioTransacao();
        try {
            return new CarteiraBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList listaByMoeda(CarteiraED ed) throws Excecoes {
        inicioTransacao();
        try {
            return new CarteiraBD(sql).listaByMoeda(ed);
        } finally {
            fimTransacao(false);
        }
    }

    public CarteiraED getByRecord(CarteiraED ed) throws Excecoes {
        inicioTransacao();
        try {
            return new CarteiraBD(this.sql).getByRecord(ed);
        } finally {
            fimTransacao(false);
        }
    }

    public CarteiraED getByRecordByMoeda(CarteiraED ed) throws Excecoes {
        inicioTransacao();
        try {
            return new CarteiraBD(this.sql).getByRecordByMoeda(ed);
        } finally {
            fimTransacao(false);
        }
    }
}