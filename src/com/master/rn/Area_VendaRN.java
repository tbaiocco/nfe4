/*
 * Created on 30/09/2004
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Area_VendaBD;
import com.master.ed.Area_VendaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class Area_VendaRN extends Transacao {

     Area_VendaBD  Area_VendaBD = null;

    public Area_VendaRN() {

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public  Area_VendaED inclui( Area_VendaED ed) throws Excecoes {

         Area_VendaED  Area_VendaED = new  Area_VendaED();

        try {

            //a chamada a este metodo da superclasse
            //iniciotransacao faz com que se abra uma conexao
            //e a deixe no pool
            this.inicioTransacao();

            //instancia objeto sql, que eh
            //uma referencia ao objeto ExecutaSQL, que por sua
            //vez possui a referencia a conexao ativa
             Area_VendaBD = new  Area_VendaBD(this.sql);

            //chama o inclui passando a estrutura de dados
            //como parametro
             Area_VendaED =  Area_VendaBD.inclui(ed);

            //faz o commit em cima do objeto transacao
            this.fimTransacao(true);
        } catch (Excecoes e) {
            throw e;
        }

        catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Area_Venda");
            excecoes.setMetodo("Inclui");
            excecoes.setExc(e);
            throw excecoes;
        }

        return  Area_VendaED;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void altera( Area_VendaED ed) throws Excecoes {

        try {

            this.inicioTransacao();

             Area_VendaBD = new  Area_VendaBD(this.sql);
             Area_VendaBD.altera(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar Area_Venda");
            excecoes.setMetodo("altera");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void deleta( Area_VendaED ed) throws Excecoes {

        try {

            this.inicioTransacao();

             Area_VendaBD = new  Area_VendaBD(this.sql);
             Area_VendaBD.deleta(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir  Area_Venda");
            excecoes.setMetodo("deleta");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public ArrayList lista( Area_VendaED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new  Area_VendaBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public  Area_VendaED getByRecord( Area_VendaED ed) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
         Area_VendaED edVolta = new  Area_VendaED();
        //atribui ao ed de retorno
        edVolta = new  Area_VendaBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }
    
    /***************************************************************************
     *  
     **************************************************************************/
    public Area_VendaED getByOidArea_Venda(int oid_Area_Venda) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
         Area_VendaED edVolta = new Area_VendaED();
        //atribui ao ed de retorno
        edVolta = new  Area_VendaBD(this.sql).getByOidArea_Venda(oid_Area_Venda);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }
    
    /***************************************************************************
     *  
     **************************************************************************/
    public Area_VendaED getByCDArea_Venda(String cd_Area_Venda) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
         Area_VendaED edVolta = new Area_VendaED();
        //atribui ao ed de retorno
        edVolta = new  Area_VendaBD(this.sql).getByCDArea_Venda(cd_Area_Venda);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }
}