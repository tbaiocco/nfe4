/*
 * Created on 24/08/2004
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Codigo_ProdutoBD;
import com.master.ed.Codigo_ProdutoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class Codigo_ProdutoRN extends Transacao {

     Codigo_ProdutoBD  Codigo_ProdutoBD = null;

    public Codigo_ProdutoRN() {

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public  Codigo_ProdutoED inclui( Codigo_ProdutoED ed) throws Excecoes {

         Codigo_ProdutoED  Codigo_ProdutoED = new  Codigo_ProdutoED();

        try {

            //a chamada a este metodo da superclasse
            //iniciotransacao faz com que se abra uma conexao
            //e a deixe no pool
            this.inicioTransacao();

            //instancia objeto sql, que eh
            //uma referencia ao objeto ExecutaSQL, que por sua
            //vez possui a referencia a conexao ativa
             Codigo_ProdutoBD = new  Codigo_ProdutoBD(this.sql);

            //chama o inclui passando a estrutura de dados
            //como parametro
             Codigo_ProdutoED =  Codigo_ProdutoBD.inclui(ed);

            //faz o commit em cima do objeto transacao
            this.fimTransacao(true);
        } catch (Excecoes e) {
            throw e;
        }

        catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Cliente_Vendedor");
            excecoes.setMetodo("Inclui");
            excecoes.setExc(e);
            throw excecoes;
        }

        return  Codigo_ProdutoED;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void altera( Codigo_ProdutoED ed) throws Excecoes {

        try {

            this.inicioTransacao();

             Codigo_ProdutoBD = new  Codigo_ProdutoBD(this.sql);
             Codigo_ProdutoBD.altera(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar Cliente_Vendedor");
            excecoes.setMetodo("altera");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void deleta( Codigo_ProdutoED ed) throws Excecoes {

        try {

            this.inicioTransacao();

             Codigo_ProdutoBD = new  Codigo_ProdutoBD(this.sql);
             Codigo_ProdutoBD.deleta(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir  Cliente_Vendedor");
            excecoes.setMetodo("deleta");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public ArrayList lista( Codigo_ProdutoED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new  Codigo_ProdutoBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public  Codigo_ProdutoED getByRecord( Codigo_ProdutoED ed) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
         Codigo_ProdutoED edVolta = new  Codigo_ProdutoED();
        //atribui ao ed de retorno
        edVolta = new  Codigo_ProdutoBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

}