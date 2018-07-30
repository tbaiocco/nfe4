/*
 * Created on 03/09/2004
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Comissao_AgenciamentoBD;
import com.master.ed.Comissao_AgenciamentoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class Comissao_AgenciamentoRN extends Transacao {

     Comissao_AgenciamentoBD Comissao_AgenciamentoBD = null;

    public Comissao_AgenciamentoRN() {

    }

    /***************************************************************************
     *
     **************************************************************************/
    public  Comissao_AgenciamentoED inclui( Comissao_AgenciamentoED ed) throws Excecoes {

         Comissao_AgenciamentoED  Comissao_AgenciamentoED = new  Comissao_AgenciamentoED();

        try {

            //a chamada a este metodo da superclasse
            //iniciotransacao faz com que se abra uma conexao
            //e a deixe no pool
            this.inicioTransacao();

            //instancia objeto sql, que eh
            //uma referencia ao objeto ExecutaSQL, que por sua
            //vez possui a referencia a conexao ativa
             Comissao_AgenciamentoBD = new  Comissao_AgenciamentoBD(this.sql);

            //chama o inclui passando a estrutura de dados
            //como parametro
             Comissao_AgenciamentoED =  Comissao_AgenciamentoBD.inclui(ed);

            //faz o commit em cima do objeto transacao
            this.fimTransacao(true);
        } catch (Excecoes e) {
            throw e;
        }

        catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Mix");
            excecoes.setMetodo("Inclui");
            excecoes.setExc(e);
            throw excecoes;
        }

        return  Comissao_AgenciamentoED;
    }

    /***************************************************************************
     *
     **************************************************************************/
    public void altera( Comissao_AgenciamentoED ed) throws Excecoes {

        try {

            this.inicioTransacao();

             Comissao_AgenciamentoBD = new  Comissao_AgenciamentoBD(this.sql);
             Comissao_AgenciamentoBD.altera(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar Mix");
            excecoes.setMetodo("altera");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    /***************************************************************************
     *
     **************************************************************************/
    public void deleta( Comissao_AgenciamentoED ed) throws Excecoes {

        try {

            this.inicioTransacao();

             Comissao_AgenciamentoBD = new  Comissao_AgenciamentoBD(this.sql);
             Comissao_AgenciamentoBD.deleta(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir  Mix");
            excecoes.setMetodo("deleta");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    /***************************************************************************
     *
     **************************************************************************/
    public ArrayList lista( Comissao_AgenciamentoED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new  Comissao_AgenciamentoBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    /***************************************************************************
     *
     **************************************************************************/
    public  Comissao_AgenciamentoED getByRecord( Comissao_AgenciamentoED ed) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
         Comissao_AgenciamentoED edVolta = new  Comissao_AgenciamentoED();
        //atribui ao ed de retorno
        edVolta = new  Comissao_AgenciamentoBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

    public byte[] gera_Comissao_Agenciamento(Comissao_AgenciamentoED ed) throws Excecoes {

        this.inicioTransacao();
        Comissao_AgenciamentoBD = new Comissao_AgenciamentoBD(this.sql);
        byte[] b = Comissao_AgenciamentoBD.gera_Comissao_Agenciamento(ed);
        this.fimTransacao(false);
        return b;
    }

}
