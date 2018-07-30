/*
 * Created on 25/10/2004
 */
package com.master.rn;

import com.master.bd.Produto_VendedorBD;
import com.master.ed.Produto_VendedorED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class Produto_VendedorRN extends Transacao {

    Produto_VendedorBD Produto_VendedorBD = null;

    public Produto_VendedorRN() {
    }

    /***************************************************************************
     * 
     **************************************************************************/
    public Produto_VendedorED GravaProdutos(Produto_VendedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Produto_VendedorBD(this.sql).GravaProdutos(ed);
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    /***************************************************************************
     * 
     **************************************************************************/
    public void GravaContrato(Produto_VendedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Produto_VendedorBD(this.sql).GravaContrato(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    /***************************************************************************
     * 
     **************************************************************************/
    public void deleta(Produto_VendedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            Produto_VendedorBD = new Produto_VendedorBD(this.sql);
            Produto_VendedorBD.deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }

    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }

    // *** RELATÓRIOS
    /*
     * public void RelProduto_Vendedor(HttpServletResponse response, String
     * oid_Vendedor, String Layout, String ordenar) throws Exception { //inicia
     * conexao com bd this.inicioTransacao(); // Seleciona o relatório if
     * (Layout.equals(Produto_VendedorRelED.LAYOUT_VEND_SIMPLES)) new
     * Produto_VendedorBD(this.sql).RelProduto_Vendedor_Simples(response,
     * oid_Vendedor, ordenar); else if
     * (Layout.equals(Produto_VendedorRelED.LAYOUT_VEND_COMPLETO)) new
     * Produto_VendedorBD(this.sql).RelProduto_Vendedor_Completo(response,
     * oid_Vendedor, ordenar); else if
     * (Layout.equals(Produto_VendedorRelED.LAYOUT_ROTA)) new
     * Produto_VendedorBD(this.sql).RelProduto_Vendedor_Rota(response,
     * oid_Vendedor); else // System.err.println("ERRO -- Layout não setado!
     * [RelProduto_Vendedor]"); }
     */
}