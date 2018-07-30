package com.master.rn;

/**
 * @author André Valadas
 * Módulo: Preços dos Produtos
*/
import java.util.ArrayList;

import com.master.bd.Preco_ProdutoBD;
import com.master.ed.Preco_ProdutoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Preco_ProdutoRN extends Transacao {

    public Preco_ProdutoRN() {
    }

    public Preco_ProdutoED inclui(Preco_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Preco_ProdutoBD(this.sql).inclui(ed);
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

    public void altera(Preco_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Preco_ProdutoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Preco_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Preco_ProdutoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public Preco_ProdutoED getByRecord(Preco_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Preco_ProdutoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista(Preco_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Preco_ProdutoBD(this.sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}