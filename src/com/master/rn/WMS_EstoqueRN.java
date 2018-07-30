package com.master.rn;

/**
 * André Valadas
 */
import java.util.ArrayList;

import com.master.bd.WMS_EstoqueBD;
import com.master.ed.Item_Nota_Fiscal_TransacoesED;
import com.master.ed.WMS_EstoqueED;
import com.master.ed.WMS_Nota_FiscalED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class WMS_EstoqueRN extends Transacao {

    public WMS_EstoqueRN() {
    }

    public void tranfereEstoque(WMS_EstoqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new WMS_EstoqueBD(this.sql).tranfereEstoque(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void reajusteEstoque(WMS_EstoqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new WMS_EstoqueBD(this.sql).reajusteEstoque(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public WMS_EstoqueED inclui(WMS_EstoqueED ed, boolean addMovimento) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new WMS_EstoqueBD(this.sql).inclui(ed, addMovimento);
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(WMS_EstoqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new WMS_EstoqueBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void subtrai(String oid_estoque_cliente, double quantidade, boolean addMovimento) throws Excecoes {

        try {
            this.inicioTransacao();
            new WMS_EstoqueBD(this.sql).subtrai(oid_estoque_cliente, quantidade, addMovimento);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void entradaEstoqueByNota(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            new WMS_EstoqueBD(this.sql).entradaEstoqueByNota(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(WMS_EstoqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new WMS_EstoqueBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public WMS_EstoqueED getByRecord(WMS_EstoqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new WMS_EstoqueBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public boolean haNoEstoque(String parametro) throws Excecoes {

        try {
            this.inicioTransacao();
            return new WMS_EstoqueBD(this.sql).haNoEstoque(parametro);
        } finally {
            this.fimTransacao(false);
        }
    }

    public boolean haNoEstoque(String parametro, int oid_tipo_estoque) throws Excecoes {

        try {
            this.inicioTransacao();
            return new WMS_EstoqueBD(this.sql).haNoEstoque(parametro, oid_tipo_estoque);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista(WMS_EstoqueED ed, String orderby) throws Excecoes {

        try {
            this.inicioTransacao();
            return new WMS_EstoqueBD(sql).lista(ed, orderby);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista_Lote(WMS_EstoqueED ed, String orderby) throws Excecoes {

        try {
            this.inicioTransacao();
            return new WMS_EstoqueBD(sql).lista_Lote(ed, orderby);
        } finally {
            this.fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.fimTransacao(false);
    }
}