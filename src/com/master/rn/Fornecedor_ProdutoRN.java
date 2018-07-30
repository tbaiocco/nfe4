package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Fornecedor_ProdutoBD;
import com.master.ed.Fornecedor_ProdutoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Fornecedores do Produto
 * @serialData 02/09/2005
 */
public class Fornecedor_ProdutoRN extends Transacao {

    public Fornecedor_ProdutoRN() {
    }

    public Fornecedor_ProdutoED inclui(Fornecedor_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed =  new Fornecedor_ProdutoBD(this.sql).inclui(ed);
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

    public void altera(Fornecedor_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Fornecedor_ProdutoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Fornecedor_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Fornecedor_ProdutoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public ArrayList lista(Fornecedor_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Fornecedor_ProdutoBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public Fornecedor_ProdutoED getByRecord(Fornecedor_ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Fornecedor_ProdutoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}