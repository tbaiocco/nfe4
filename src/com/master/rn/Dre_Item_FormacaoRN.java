package com.master.rn;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.bd.DRE_ItemBD;
import com.master.bd.Dre_Item_FormacaoBD;
import com.master.ed.Dre_ItemED;
import com.master.ed.Dre_Item_FormacaoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Formações dos Itens do DRE
 * @serialData 02/2006
 */
public class Dre_Item_FormacaoRN extends Transacao {

    public Dre_Item_FormacaoRN() {
    }

    public Dre_Item_FormacaoED inclui(Dre_Item_FormacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Dre_Item_FormacaoBD(this.sql).inclui(ed);
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

    public void altera(Dre_Item_FormacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Dre_Item_FormacaoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Dre_Item_FormacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Dre_Item_FormacaoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Dre_Item_FormacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Dre_Item_FormacaoBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Dre_Item_FormacaoED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Dre_Item_FormacaoBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
            request.setAttribute("disabled",lista.size() > 0 ? "disabled": " ") ;
        } finally {
            this.fimTransacao(false);
        }
    }

    public Dre_Item_FormacaoED getByRecord(Dre_Item_FormacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Dre_Item_FormacaoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public void getByRecord(Dre_Item_FormacaoED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            Dre_Item_FormacaoED edQBR = new Dre_Item_FormacaoBD(this.sql).getByRecord(ed);
			request.setAttribute(nmObj, edQBR.getOid_Dre_Item_Formacao()== 0 ? null : edQBR);
        } finally {
            this.fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}