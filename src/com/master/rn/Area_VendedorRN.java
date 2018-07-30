/*
 * Created on 30/09/2004
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Area_VendedorBD;
import com.master.ed.Area_VendedorED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class Area_VendedorRN extends Transacao {

    Area_VendedorBD  Area_VendedorBD = null;

    public Area_VendedorRN() {

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public  Area_VendedorED inclui( Area_VendedorED ed) throws Excecoes {

         Area_VendedorED  Area_VendedorED = new  Area_VendedorED();

        try {

            //a chamada a este metodo da superclasse
            //iniciotransacao faz com que se abra uma conexao
            //e a deixe no pool
            this.inicioTransacao();

            //instancia objeto sql, que eh
            //uma referencia ao objeto ExecutaSQL, que por sua
            //vez possui a referencia a conexao ativa
             Area_VendedorBD = new  Area_VendedorBD(this.sql);

            //chama o inclui passando a estrutura de dados
            //como parametro
             Area_VendedorED =  Area_VendedorBD.inclui(ed);

            //faz o commit em cima do objeto transacao
            this.fimTransacao(true);
        } catch (Excecoes e) {
            throw e;
        }

        catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Area");
            excecoes.setMetodo("Inclui");
            excecoes.setExc(e);
            throw excecoes;
        }

        return  Area_VendedorED;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void altera( Area_VendedorED ed) throws Excecoes {

        try {

            this.inicioTransacao();

             Area_VendedorBD = new  Area_VendedorBD(this.sql);
             Area_VendedorBD.altera(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar Area");
            excecoes.setMetodo("altera");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void deleta( Area_VendedorED ed) throws Excecoes {

        try {

            this.inicioTransacao();

             Area_VendedorBD = new  Area_VendedorBD(this.sql);
             Area_VendedorBD.deleta(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir Area");
            excecoes.setMetodo("deleta");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public ArrayList lista( Area_VendedorED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new  Area_VendedorBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }
    
    /***************************************************************************
     *  
     **************************************************************************/
    public  Area_VendedorED getByOidArea_Vendedor(int oid_Area_Vendedor) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
         Area_VendedorED edVolta = new  Area_VendedorED();
        //atribui ao ed de retorno
        edVolta = new  Area_VendedorBD(this.sql).getByOidArea_Vendedor(oid_Area_Vendedor);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

}