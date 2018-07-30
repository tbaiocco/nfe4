package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Nivel_ProdutoBD;
import com.master.ed.Nivel_ProdutoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Nivel_ProdutoRN extends Transacao {

    Nivel_ProdutoBD Nivel_ProdutoBD = null;

    public Nivel_ProdutoRN() {
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public Nivel_ProdutoED inclui(Nivel_ProdutoED ed) throws Excecoes {

        Nivel_ProdutoED Nivel_ProdutoED = new Nivel_ProdutoED();

        this.inicioTransacao();
        Nivel_ProdutoBD = new Nivel_ProdutoBD(this.sql);
        Nivel_ProdutoED = Nivel_ProdutoBD.inclui(ed);
        this.fimTransacao(true);
        return Nivel_ProdutoED;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void altera(Nivel_ProdutoED ed) throws Excecoes {

        this.inicioTransacao();
        Nivel_ProdutoBD = new Nivel_ProdutoBD(this.sql);
        Nivel_ProdutoBD.altera(ed);
        this.fimTransacao(true);
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void deleta(Nivel_ProdutoED ed) throws Excecoes {

        this.inicioTransacao();
        Nivel_ProdutoBD = new Nivel_ProdutoBD(this.sql);
        Nivel_ProdutoBD.deleta(ed);
        this.fimTransacao(true);
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public ArrayList lista(Nivel_ProdutoED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new Nivel_ProdutoBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public Nivel_ProdutoED getByRecord(Nivel_ProdutoED ed) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
        Nivel_ProdutoED edVolta = new Nivel_ProdutoED();
        //atribui ao ed de retorno
        edVolta = new Nivel_ProdutoBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void geraRelatorio(Nivel_ProdutoED ed) throws Excecoes {

        this.inicioTransacao();
        Nivel_ProdutoBD = new Nivel_ProdutoBD(this.sql);
        Nivel_ProdutoBD.geraRelatorio(ed);
        this.fimTransacao(false);

    }
    
    protected void finalize() throws Throwable {
        if (this.sql !=  null)
            this.abortaTransacao();
    }

}