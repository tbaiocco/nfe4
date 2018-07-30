package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Setor_ProdutoBD;
import com.master.ed.Setor_ProdutoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Setores dos Produtos
 * @serialData 01/07/2005
 */
public class Setor_ProdutoRN extends Transacao {

    public Setor_ProdutoRN() {
    }

    public Setor_ProdutoED inclui(Setor_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Setor_ProdutoBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(Setor_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Setor_ProdutoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Setor_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Setor_ProdutoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Setor_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Setor_ProdutoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Setor_ProdutoED getByRecord(Setor_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Setor_ProdutoBD(sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.sql != null)
            this.abortaTransacao();
    }
}