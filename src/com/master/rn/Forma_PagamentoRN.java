package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Forma_PagamentoBD;
import com.master.ed.Forma_PagamentoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * - Formas de Pagamentos
 */
public class Forma_PagamentoRN extends Transacao {

    public Forma_PagamentoRN() {
    }

    public Forma_PagamentoED inclui(Forma_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Forma_PagamentoBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(Forma_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Forma_PagamentoBD(this.sql).altera(ed);
        } finally {
            this.fimTransacao(true);
        }
    }

    public void deleta(Forma_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Forma_PagamentoBD(this.sql).deleta(ed);
        } finally {
            this.fimTransacao(true);
        }
    }

    public ArrayList lista(Forma_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Forma_PagamentoBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public Forma_PagamentoED getByRecord(Forma_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Forma_PagamentoBD(this.sql).getByRecord(ed);
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
