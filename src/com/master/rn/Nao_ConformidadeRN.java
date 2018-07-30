/*
 * Created on 30/09/2004
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Nao_ConformidadeBD;
import com.master.ed.Nao_ConformidadeED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class Nao_ConformidadeRN extends Transacao {

	Nao_ConformidadeBD  Nao_ConformidadeBD = null;

    public Nao_ConformidadeRN() {

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public  Nao_ConformidadeED inclui( Nao_ConformidadeED ed) throws Excecoes {

    	Nao_ConformidadeED  Nao_ConformidadeED = new  Nao_ConformidadeED();

        try {

            //a chamada a este metodo da superclasse
            //iniciotransacao faz com que se abra uma conexao
            //e a deixe no pool
            this.inicioTransacao();

            //instancia objeto sql, que eh
            //uma referencia ao objeto ExecutaSQL, que por sua
            //vez possui a referencia a conexao ativa
            Nao_ConformidadeBD = new  Nao_ConformidadeBD(this.sql);

            //chama o inclui passando a estrutura de dados
            //como parametro
            Nao_ConformidadeED =  Nao_ConformidadeBD.inclui(ed);

            //faz o commit em cima do objeto transacao
            this.fimTransacao(true);
        } catch (Excecoes e) {
            throw e;
        }

        catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Nao_Conformidade");
            excecoes.setMetodo("Inclui");
            excecoes.setExc(e);
            throw excecoes;
        }

        return  Nao_ConformidadeED;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void altera( Nao_ConformidadeED ed) throws Excecoes {

        try {

            this.inicioTransacao();

            Nao_ConformidadeBD = new  Nao_ConformidadeBD(this.sql);
            Nao_ConformidadeBD.altera(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar Nao_Conformidade");
            excecoes.setMetodo("altera");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void deleta( Nao_ConformidadeED ed) throws Excecoes {

        try {

            this.inicioTransacao();

            Nao_ConformidadeBD = new  Nao_ConformidadeBD(this.sql);
            Nao_ConformidadeBD.deleta(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir Nao_Conformidade");
            excecoes.setMetodo("deleta");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public ArrayList lista( Nao_ConformidadeED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new  Nao_ConformidadeBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }
    
    /***************************************************************************
     *  
     **************************************************************************/
    public  Nao_ConformidadeED getByOidNao_Conformidade(String oid_Nao_Conformidade) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
        Nao_ConformidadeED edVolta = new  Nao_ConformidadeED();
        //atribui ao ed de retorno
        edVolta = new  Nao_ConformidadeBD(this.sql).getByOid_Nao_Conformidade(oid_Nao_Conformidade);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

}