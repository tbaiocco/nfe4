/*
 * Created on 09/09/2004
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Mix_VendedorBD;
import com.master.ed.Mix_VendedorED;
import com.master.ed.Produto_VendedorED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class Mix_VendedorRN extends Transacao {

    Mix_VendedorBD Mix_VendedorBD = null;

    public Mix_VendedorRN() {
    }

    public Mix_VendedorED inclui(Mix_VendedorED ed) throws Excecoes {

        Mix_VendedorED Mix_VendedorED = new Mix_VendedorED();

        this.inicioTransacao();
        Mix_VendedorBD = new Mix_VendedorBD(this.sql);
        Mix_VendedorED = Mix_VendedorBD.inclui(ed);
        this.fimTransacao(true);

        //*** INSERE da Tabela Produtos_Vendedor
        Produto_VendedorED edProduto = new Produto_VendedorED();
        edProduto.setModulo(Produto_VendedorED.MODULO_MIX_VENDEDOR);
        edProduto.setOperacao(Produto_VendedorED.OP_INCLUIR);
        //edProduto.setOid_Pessoa(OID_DA_DISTRIBUIDORA);
        edProduto.setOid_Mix(ed.getOid_Mix());
        edProduto.setOid_Vendedor(ed.getOid_Vendedor());
        new Produto_VendedorRN().GravaProdutos(edProduto);
        return Mix_VendedorED;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void altera(Mix_VendedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();

            //*** Busca oid antes de alterar [Produtos_Vendedores]
            int oid_Mix_old = new BancoUtil(this.sql).getTableIntValue("oid_mix", "mix_vendedores","oid_mix_Vendedor = " + ed.getOid_Mix_Vendedor());
            Mix_VendedorBD = new Mix_VendedorBD(this.sql);
            Mix_VendedorBD.altera(ed);

            this.fimTransacao(true);

            //*** INSERE da Tabela Produtos_Vendedor como [EXCLUIDO]
            if (oid_Mix_old != ed.getOid_Mix())
            {
                Produto_VendedorED edProduto = new Produto_VendedorED();
                edProduto.setModulo(Produto_VendedorED.MODULO_MIX_VENDEDOR);
                edProduto.setOperacao(Produto_VendedorED.OP_EXCLUIR);
                //edProduto.setOid_Pessoa(OID_DA_DISTRIBUIDORA);
                edProduto.setOid_Mix(oid_Mix_old);
                edProduto.setOid_Vendedor(ed.getOid_Vendedor());
                new Produto_VendedorRN().GravaProdutos(edProduto);

                //*** INSERE da Tabela Produtos_Vendedor como [INCLUIDO]
                Produto_VendedorED edProduto2 = new Produto_VendedorED();
                edProduto2.setModulo(Produto_VendedorED.MODULO_MIX_VENDEDOR);
                edProduto2.setOperacao(Produto_VendedorED.OP_EXCLUIR);
                //edProduto2.setOid_Pessoa(OID_DA_DISTRIBUIDORA);
                edProduto2.setOid_Mix(ed.getOid_Mix());
                edProduto2.setOid_Vendedor(ed.getOid_Vendedor());
                new Produto_VendedorRN().GravaProdutos(edProduto2);
            }
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
    public void deleta(Mix_VendedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();

            //*** Busca oid antes de excluir [Produtos_Vendedores]
            int oid_Mix_old = new BancoUtil(this.sql).getTableIntValue("oid_mix", "mix_vendedores","oid_mix_Vendedor = " + ed.getOid_Mix_Vendedor());
            Mix_VendedorBD = new Mix_VendedorBD(this.sql);
            Mix_VendedorBD.deleta(ed);

            //*** INSERE da Tabela Produtos_Vendedor como [EXCLUIDO]
            if (oid_Mix_old > 0)
            {
                Produto_VendedorED edProduto = new Produto_VendedorED();
                edProduto.setModulo(Produto_VendedorED.MODULO_MIX_VENDEDOR);
                edProduto.setOperacao(Produto_VendedorED.OP_EXCLUIR);
                //edProduto.setOid_Pessoa(OID_DA_DISTRIBUIDORA);
                edProduto.setOid_Mix(oid_Mix_old);
                edProduto.setOid_Vendedor(ed.getOid_Vendedor());
                new Produto_VendedorRN().GravaProdutos(edProduto);
            }
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
    public ArrayList lista(Mix_VendedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Mix_VendedorBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public Mix_VendedorED getByRecord(Mix_VendedorED ed) throws Excecoes {
        
        try {
            this.inicioTransacao();
            return new Mix_VendedorBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Mix_VendedorED getByOidMixProduto(int oid_Mix_Vendedor) throws Excecoes {
        
        try {
            this.inicioTransacao();
            return new Mix_VendedorBD(this.sql).getByOidMixVendedor(oid_Mix_Vendedor);
        } finally {
            this.fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}