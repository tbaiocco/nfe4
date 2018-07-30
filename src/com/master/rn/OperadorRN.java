/*
 * Created on 24/08/2004
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.OperadorBD;
import com.master.ed.OperadorED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class OperadorRN extends Transacao {

    OperadorBD OperadorBD = null;

    public OperadorRN() {
        //OperadorBD = new OperadorBD(this.sql);
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public OperadorED inclui(OperadorED ed) throws Excecoes {

        OperadorED OperadorED = new OperadorED();

        try {

            //a chamada a este metodo da superclasse
            //iniciotransacao faz com que se abra uma conexao
            //e a deixe no pool
            this.inicioTransacao();

            //instancia objeto sql, que eh
            //uma referencia ao objeto ExecutaSQL, que por sua
            //vez possui a referencia a conexao ativa
            OperadorBD = new OperadorBD(this.sql);

            //chama o inclui passando a estrutura de dados
            //como parametro
            OperadorED = OperadorBD.inclui(ed);

            //faz o commit em cima do objeto transacao
            this.fimTransacao(true);
        } catch (Excecoes e) {
            throw e;
        }

        catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Operador");
            excecoes.setMetodo("Inclui");
            excecoes.setExc(e);
            throw excecoes;
        }

        return OperadorED;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void altera(OperadorED ed) throws Excecoes {

        try {

            this.inicioTransacao();

            OperadorBD = new OperadorBD(this.sql);
            OperadorBD.altera(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar Operador");
            excecoes.setMetodo("altera");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void deleta(OperadorED ed) throws Excecoes {

        try {

            this.inicioTransacao();

            OperadorBD = new OperadorBD(this.sql);
            OperadorBD.deleta(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir Operador");
            excecoes.setMetodo("deleta");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public ArrayList lista(OperadorED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new OperadorBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public OperadorED getByRecord(OperadorED ed) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
        OperadorED edVolta = new OperadorED();
        //atribui ao ed de retorno
        edVolta = new OperadorBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

}