/*
 * Created on 10/09/2004
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Classificacao_FiscalBD;
import com.master.ed.Classificacao_FiscalED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class Classificacao_FiscalRN extends Transacao {

    Classificacao_FiscalBD  Classificacao_FiscalBD = null;

    public Classificacao_FiscalRN() {

    }

    public  Classificacao_FiscalED inclui( Classificacao_FiscalED ed) throws Excecoes {

         Classificacao_FiscalED  Classificacao_FiscalED = new  Classificacao_FiscalED();

        try {

            //a chamada a este metodo da superclasse
            //iniciotransacao faz com que se abra uma conexao
            //e a deixe no pool
            this.inicioTransacao();

            //instancia objeto sql, que eh
            //uma referencia ao objeto ExecutaSQL, que por sua
            //vez possui a referencia a conexao ativa
             Classificacao_FiscalBD = new  Classificacao_FiscalBD(this.sql);

            //chama o inclui passando a estrutura de dados
            //como parametro
             Classificacao_FiscalED =  Classificacao_FiscalBD.inclui(ed);

            //faz o commit em cima do objeto transacao
            this.fimTransacao(true);
        } catch (Excecoes e) {
            throw e;
        }

        catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Classificacao_Fiscal");
            excecoes.setMetodo("Inclui");
            excecoes.setExc(e);
            throw excecoes;
        }

        return  Classificacao_FiscalED;
    }

    public void altera( Classificacao_FiscalED ed) throws Excecoes {

        try {

            this.inicioTransacao();

             Classificacao_FiscalBD = new  Classificacao_FiscalBD(this.sql);
             Classificacao_FiscalBD.altera(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar Classificacao_Fiscal");
            excecoes.setMetodo("altera");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    public void deleta( Classificacao_FiscalED ed) throws Excecoes {

        try {

            this.inicioTransacao();

             Classificacao_FiscalBD = new  Classificacao_FiscalBD(this.sql);
             Classificacao_FiscalBD.deleta(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir Classificacao_Fiscal");
            excecoes.setMetodo("deleta");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    public ArrayList lista( Classificacao_FiscalED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new  Classificacao_FiscalBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public  Classificacao_FiscalED getByRecord( Classificacao_FiscalED ed) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
         Classificacao_FiscalED edVolta = new  Classificacao_FiscalED();
        //atribui ao ed de retorno
        edVolta = new  Classificacao_FiscalBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }
    
    public Classificacao_FiscalED getByOidClassificacao_Fiscal(int oid_Classificacao_Fiscal) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
         Classificacao_FiscalED edVolta = new Classificacao_FiscalED();
        //atribui ao ed de retorno
        edVolta = new  Classificacao_FiscalBD(this.sql).getByOidClassificacao_Fiscal(oid_Classificacao_Fiscal);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }
    
    public Classificacao_FiscalED getByCdReduzido(String cd_Reduzido) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
         Classificacao_FiscalED edVolta = new Classificacao_FiscalED();
        //atribui ao ed de retorno
        edVolta = new  Classificacao_FiscalBD(this.sql).getByCdReduzido(cd_Reduzido);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

}