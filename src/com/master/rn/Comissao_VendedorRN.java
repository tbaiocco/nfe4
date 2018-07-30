/*
 * Created on 03/09/2004
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Comissao_VendedorBD;
import com.master.ed.Comissao_VendedorED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class Comissao_VendedorRN extends Transacao {

     Comissao_VendedorBD Comissao_VendedorBD = null;

    public Comissao_VendedorRN() {

    }

    /***************************************************************************
     *
     **************************************************************************/
    public  Comissao_VendedorED inclui( Comissao_VendedorED ed) throws Excecoes {

         Comissao_VendedorED  Comissao_VendedorED = new  Comissao_VendedorED();

        try {

            //a chamada a este metodo da superclasse
            //iniciotransacao faz com que se abra uma conexao
            //e a deixe no pool
            this.inicioTransacao();

            //instancia objeto sql, que eh
            //uma referencia ao objeto ExecutaSQL, que por sua
            //vez possui a referencia a conexao ativa
             Comissao_VendedorBD = new  Comissao_VendedorBD(this.sql);

            //chama o inclui passando a estrutura de dados
            //como parametro
             Comissao_VendedorED =  Comissao_VendedorBD.inclui(ed);

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

        return  Comissao_VendedorED;
    }

    /***************************************************************************
     *
     **************************************************************************/
    public void altera( Comissao_VendedorED ed) throws Excecoes {

        try {

            this.inicioTransacao();

             Comissao_VendedorBD = new  Comissao_VendedorBD(this.sql);
             Comissao_VendedorBD.altera(ed);

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
    public void deleta( Comissao_VendedorED ed) throws Excecoes {

        try {

            this.inicioTransacao();

             Comissao_VendedorBD = new  Comissao_VendedorBD(this.sql);
             Comissao_VendedorBD.deleta(ed);

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
    public ArrayList lista( Comissao_VendedorED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new  Comissao_VendedorBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    /***************************************************************************
     *
     **************************************************************************/
    public  Comissao_VendedorED getByRecord( Comissao_VendedorED ed) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
         Comissao_VendedorED edVolta = new  Comissao_VendedorED();
        //atribui ao ed de retorno
        edVolta = new  Comissao_VendedorBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

    public byte[] geraComissaoConhecimentos(Comissao_VendedorED ed) throws Excecoes {

        this.inicioTransacao();
        Comissao_VendedorBD = new Comissao_VendedorBD(this.sql);
        byte[] b = Comissao_VendedorBD.geraComissaoConhecimentos(ed);
        this.fimTransacao(false);
        return b;
    }

}
