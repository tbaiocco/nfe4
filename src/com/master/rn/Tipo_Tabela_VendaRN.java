package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Tipo_Tabela_VendaBD;
import com.master.ed.Tipo_Tabela_VendaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Tipos de Tabelas de Venda
 * @serialData 11/04/2005
 */
public class Tipo_Tabela_VendaRN extends Transacao {

    public Tipo_Tabela_VendaRN() {
    }

    public Tipo_Tabela_VendaED inclui(Tipo_Tabela_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Tipo_Tabela_VendaBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(Tipo_Tabela_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Tipo_Tabela_VendaBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Tipo_Tabela_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Tipo_Tabela_VendaBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Tipo_Tabela_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Tipo_Tabela_VendaBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Tipo_Tabela_VendaED getByRecord(Tipo_Tabela_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Tipo_Tabela_VendaBD(sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
}