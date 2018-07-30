package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Desconto_ProdutoBD;
import com.master.ed.Desconto_ProdutoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Max. Descontos dos Produtos Referente a Tabelas de Preços
 * @serialData 28/02/2006
 */
public class Desconto_ProdutoRN extends Transacao {

    public Desconto_ProdutoRN() {
    }

    public Desconto_ProdutoED inclui(Desconto_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Desconto_ProdutoBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Desconto_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Desconto_ProdutoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Desconto_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Desconto_ProdutoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Desconto_ProdutoED getByRecord(Desconto_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Desconto_ProdutoBD(sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
}