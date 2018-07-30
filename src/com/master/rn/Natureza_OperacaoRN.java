package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Natureza_OperacaoBD;
import com.master.ed.Natureza_OperacaoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Natureza_OperacaoRN extends Transacao {

    Natureza_OperacaoBD Natureza_OperacaoBD = null;

    public Natureza_OperacaoRN() {
    }

    public Natureza_OperacaoED inclui(Natureza_OperacaoED ed) throws Excecoes {

        this.inicioTransacao();
        Natureza_OperacaoBD = new Natureza_OperacaoBD(this.sql);
        ed = Natureza_OperacaoBD.inclui(ed);
        this.fimTransacao(true);
        return ed;
    }

    public void altera(Natureza_OperacaoED ed) throws Excecoes {

        this.inicioTransacao();
        Natureza_OperacaoBD = new Natureza_OperacaoBD(this.sql);
        Natureza_OperacaoBD.altera(ed);
        this.fimTransacao(true);
    }

    public void deleta(Natureza_OperacaoED ed) throws Excecoes {

        this.inicioTransacao();
        Natureza_OperacaoBD = new Natureza_OperacaoBD(this.sql);
        Natureza_OperacaoBD.deleta(ed);
        this.fimTransacao(true);

    }

    public ArrayList lista(Natureza_OperacaoED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new Natureza_OperacaoBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public Natureza_OperacaoED getByRecord(Natureza_OperacaoED ed) throws Excecoes {

        this.inicioTransacao();
        Natureza_OperacaoED edVolta = new Natureza_OperacaoED();
        edVolta = new Natureza_OperacaoBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);
        return edVolta;
    }

    public void geraRelatorio(Natureza_OperacaoED ed) throws Excecoes {

        this.inicioTransacao();
        Natureza_OperacaoBD = new Natureza_OperacaoBD(this.sql);
        Natureza_OperacaoBD.geraRelatorio(ed);
        this.fimTransacao(false);

    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.sql !=  null)
            this.abortaTransacao();
    }
}