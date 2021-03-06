package com.master.rn;

import java.util.ArrayList;
import com.master.bd.Item_Nota_Fiscal_TransacoesBD;
import com.master.bd.Nota_Fiscal_EletronicaBD;
import com.master.ed.Item_Nota_Fiscal_TransacoesED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Item_Nota_Fiscal_TransacoesRN extends Transacao {

    public Item_Nota_Fiscal_TransacoesRN() {
    }

    public Item_Nota_Fiscal_TransacoesED inclui(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Item_Nota_Fiscal_TransacoesBD(this.sql).inclui(ed);
            if (!ed.isVendaDireta())
                new Nota_Fiscal_EletronicaBD(this.sql).atualizaValorICMS(ed.getOID_Nota_Fiscal());
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

    public void altera(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Item_Nota_Fiscal_TransacoesBD(this.sql).altera(ed);
            //if (!ed.isVendaDireta())
                new Nota_Fiscal_EletronicaBD(this.sql).atualizaValorICMS(ed.getOID_Nota_Fiscal());
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Item_Nota_Fiscal_TransacoesBD(this.sql).deleta(ed);
            if (!ed.isVendaDireta())
                new Nota_Fiscal_EletronicaBD(this.sql).atualizaValorICMS(ed.getOID_Nota_Fiscal());
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public Item_Nota_Fiscal_TransacoesED getByRecord(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Item_Nota_Fiscal_TransacoesBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Item_Nota_Fiscal_TransacoesBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList listaItemPedido(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Item_Nota_Fiscal_TransacoesBD(sql).listaItemPedido(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList devolveItem(Item_Nota_Fiscal_TransacoesED edNFi, String pOid_Produto_Cliente, double pNr_Quantidade_Saida) throws Excecoes{

        try {
            this.inicioTransacao();
            return new Item_Nota_Fiscal_TransacoesBD(sql).devolveItem( edNFi, pOid_Produto_Cliente,  pNr_Quantidade_Saida);
        } finally {
            this.fimTransacao(true);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}