package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Estrutura_ProdutoBD;
import com.master.ed.Estrutura_ProdutoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Estrutura_ProdutoRN extends Transacao {

    Estrutura_ProdutoBD Estrutura_ProdutoBD = null;

    public Estrutura_ProdutoRN() {
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public Estrutura_ProdutoED inclui(Estrutura_ProdutoED ed) throws Excecoes {

        Estrutura_ProdutoED Estrutura_ProdutoED = new Estrutura_ProdutoED();

        this.inicioTransacao();
        Estrutura_ProdutoBD = new Estrutura_ProdutoBD(this.sql);
        Estrutura_ProdutoED = Estrutura_ProdutoBD.inclui(ed);
        this.fimTransacao(true);

        return Estrutura_ProdutoED;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void altera(Estrutura_ProdutoED ed) throws Excecoes {

        this.inicioTransacao();
        Estrutura_ProdutoBD = new Estrutura_ProdutoBD(this.sql);
        Estrutura_ProdutoBD.altera(ed);
        this.fimTransacao(true);

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void deleta(Estrutura_ProdutoED ed) throws Excecoes {

        this.inicioTransacao();
        Estrutura_ProdutoBD = new Estrutura_ProdutoBD(this.sql);
        Estrutura_ProdutoBD.deleta(ed);
        this.fimTransacao(true);

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public ArrayList lista(Estrutura_ProdutoED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new Estrutura_ProdutoBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public Estrutura_ProdutoED getByRecord(Estrutura_ProdutoED ed) throws Excecoes {

        this.inicioTransacao();
        Estrutura_ProdutoED edVolta = new Estrutura_ProdutoED();
        edVolta = new Estrutura_ProdutoBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);
        return edVolta;
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}