package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Ocorrencia_Nota_FiscalBD;
import com.master.ed.Ocorrencia_Nota_FiscalED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Ocorrencia_Nota_FiscalRN extends Transacao {

    Ocorrencia_Nota_FiscalBD Ocorrencia_Nota_FiscalBD = null;

    public Ocorrencia_Nota_FiscalRN() {
    }

    public Ocorrencia_Nota_FiscalED inclui(Ocorrencia_Nota_FiscalED ed) throws Excecoes {

        Ocorrencia_Nota_FiscalED ocorrencia_Nota_FiscalED = new Ocorrencia_Nota_FiscalED();

        if (ed.getOID_Nota_Fiscal().compareTo("") == 0) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Código do Nota_Fiscal não foi informado !!!10");
            throw exc;
        }

        try {

            //a chamada a este metodo da superclasse
            //iniciotransacao faz com que se abra uma conexao
            //e a deixe no pool
            this.inicioTransacao();

            //instancia objeto sql, que eh
            //uma referencia ao objeto ExecutaSQL, que por sua
            //vez possui a referencia a conexao ativa
            Ocorrencia_Nota_FiscalBD = new Ocorrencia_Nota_FiscalBD(this.sql);

            //chama o inclui passando a estrutura de dados
            //como parametro
            ocorrencia_Nota_FiscalED = Ocorrencia_Nota_FiscalBD.inclui(ed);

            //faz o commit em cima do objeto transacao
            this.fimTransacao(true);

        }

        catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        }
        catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir");
            excecoes.setMetodo("inclui");
            excecoes.setExc(e);
            //faz rollback pois deu algum erro
            this.abortaTransacao();

            throw excecoes;
        }

        return ocorrencia_Nota_FiscalED;
    }

    public void altera(Ocorrencia_Nota_FiscalED ed) throws Exception {

        if (String.valueOf(ed.getOID_Ocorrencia_Nota_Fiscal()).compareTo("") == 0) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Código do Ocorrencia_Nota_Fiscal não foi informado !!!");
            throw exc;
        }

        try {

            this.inicioTransacao();
            Ocorrencia_Nota_FiscalBD = new Ocorrencia_Nota_FiscalBD(this.sql);
            Ocorrencia_Nota_FiscalBD.altera(ed);
            this.fimTransacao(true);

        } catch (Exception e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Ocorrencia_Nota_FiscalED ed) throws Excecoes {

        if (String.valueOf(ed.getOID_Ocorrencia_Nota_Fiscal()).compareTo("") == 0) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Código do Ocorrencia_Nota_Fiscal não foi informado !!!");
            throw exc;
        }

        try {

            this.inicioTransacao();
            Ocorrencia_Nota_FiscalBD = new Ocorrencia_Nota_FiscalBD(this.sql);
            Ocorrencia_Nota_FiscalBD.deleta(ed);
            this.fimTransacao(true);

        } catch (Exception e) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Erro de exclusão");
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista(Ocorrencia_Nota_FiscalED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new Ocorrencia_Nota_FiscalBD(this.sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public Ocorrencia_Nota_FiscalED getByRecord(Ocorrencia_Nota_FiscalED ed) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
        Ocorrencia_Nota_FiscalED edVolta = new Ocorrencia_Nota_FiscalED();
        //atribui ao ed de retorno
        edVolta = new Ocorrencia_Nota_FiscalBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

    public void geraRelatorio(Ocorrencia_Nota_FiscalED ed) throws Excecoes {

        this.inicioTransacao();
        Ocorrencia_Nota_FiscalBD = new Ocorrencia_Nota_FiscalBD(this.sql);
        Ocorrencia_Nota_FiscalBD.geraRelatorio(ed);
        this.fimTransacao(false);

    }
}