/*
 * Created on 24/08/2004
 */
package com.master.rn;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.Cliente_VendedorBD;
import com.master.ed.Cliente_VendedorED;
import com.master.ed.Cliente_VendedorRelED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class Cliente_VendedorRN extends Transacao {

    public Cliente_VendedorRN() {
    }

    public Cliente_VendedorED inclui(Cliente_VendedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Cliente_VendedorBD(this.sql).inclui(ed);
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

    public void altera(Cliente_VendedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Cliente_VendedorBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Cliente_VendedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Cliente_VendedorBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Cliente_VendedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Cliente_VendedorBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Cliente_VendedorED getByRecord(Cliente_VendedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Cliente_VendedorBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    //*** RELATÓRIOS
    public void RelCliente_Vendedor(HttpServletResponse response, String oid_Vendedor, String Layout, String ordenar) throws Exception {

        try {
            this.inicioTransacao();
            // Seleciona o relatório
            if (Layout.equals(Cliente_VendedorRelED.LAYOUT_VEND_SIMPLES))
                new Cliente_VendedorBD(this.sql).RelCliente_Vendedor_Simples(response, oid_Vendedor, ordenar);
            else if (Layout.equals(Cliente_VendedorRelED.LAYOUT_VEND_COMPLETO))
                new Cliente_VendedorBD(this.sql).RelCliente_Vendedor_Completo(response, oid_Vendedor, ordenar);
            else if (Layout.equals(Cliente_VendedorRelED.LAYOUT_ROTA))
                new Cliente_VendedorBD(this.sql).RelCliente_Vendedor_Rota(response, oid_Vendedor);
            else throw new Excecoes("ERRO -- Layout não setado! [RelCliente_Vendedor]");
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}