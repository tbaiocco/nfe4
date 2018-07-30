package com.master.rn;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.bd.DreBD;
import com.master.bd.Relatorio_GerencialBD;
import com.master.ed.DreED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Demonstrativo de Resultados
 * @serialData 02/2006
 */
public class DreRN extends Transacao {

    public DreRN() {
    }

    public DreED inclui(DreED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new DreBD(this.sql).inclui(ed);
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

    public void altera(DreED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new DreBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(DreED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new DreBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(DreED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new DreBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(DreED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new DreBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public DreED getByRecord(DreED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new DreBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(DreED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            DreED edQBR = new DreBD(this.sql).getByRecord(ed);
			request.setAttribute(nmObj, edQBR.getOid_Dre()== 0 ? null : edQBR);

        } finally {
            this.fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}