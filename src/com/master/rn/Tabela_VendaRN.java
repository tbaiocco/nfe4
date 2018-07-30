package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Tabela_VendaBD;
import com.master.ed.RelatorioED;
import com.master.ed.Tabela_VendaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Tabelas de Venda
 * @serialData 11/04/2005
 */
public class Tabela_VendaRN extends Transacao {

    public Tabela_VendaRN() {
    }

    public Tabela_VendaED inclui(Tabela_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Tabela_VendaBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public void altera(Tabela_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Tabela_VendaBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public void deleta(Tabela_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Tabela_VendaBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Tabela_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Tabela_VendaBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Tabela_VendaED getByRecord(Tabela_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Tabela_VendaBD(sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public Tabela_VendaED getUltimaTabelaByTipo(Tabela_VendaED ed, boolean tabAtiva) throws Excecoes {
        
        try {
            this.inicioTransacao();
            return new Tabela_VendaBD(sql).getUltimaTabelaByTipo(ed, tabAtiva);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public Tabela_VendaED duplicarTabela(Tabela_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Tabela_VendaBD(this.sql).duplicarTabela(ed);
            this.fimTransacao(true);
            return ed;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public Tabela_VendaED reajusteTabela(Tabela_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Tabela_VendaBD(this.sql).reajusteTabela(ed);
            this.fimTransacao(true);
            return ed;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void relTabelasPrecos(RelatorioED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Tabela_VendaBD(sql).relTabelasPrecos(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
}