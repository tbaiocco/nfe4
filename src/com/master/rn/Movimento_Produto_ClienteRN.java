package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Movimento_Produto_ClienteBD;
import com.master.ed.Movimento_Produto_ClienteED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * - Movimento dos Produtos Clientes
 */
public class Movimento_Produto_ClienteRN extends Transacao {

    public Movimento_Produto_ClienteRN() {
    }  

    public Movimento_Produto_ClienteED inclui(Movimento_Produto_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Movimento_Produto_ClienteBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Movimento_Produto_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Movimento_Produto_ClienteBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public Movimento_Produto_ClienteED getByRecord(Movimento_Produto_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Movimento_Produto_ClienteBD(this.sql).getByRecord(ed);
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
