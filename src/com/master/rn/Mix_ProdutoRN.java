/*
 * Created on 31/08/2004
 */
package com.master.rn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.bd.Mix_ProdutoBD;
import com.master.ed.Mix_ProdutoED;
import com.master.ed.Produto_VendedorED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class Mix_ProdutoRN extends Transacao {

    Mix_ProdutoBD Mix_ProdutoBD = null;

    public Mix_ProdutoRN() {

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public Mix_ProdutoED inclui(Mix_ProdutoED ed) throws Excecoes {

        this.inicioTransacao();
        Mix_ProdutoBD = new Mix_ProdutoBD(this.sql);
        ed = Mix_ProdutoBD.inclui(ed);
        this.fimTransacao(true);

        //*** INSERE da Tabela Produtos_Vendedor
        Produto_VendedorED edProduto = new Produto_VendedorED();
        edProduto.setModulo(Produto_VendedorED.MODULO_MIX_PRODUTO);
        edProduto.setOperacao(Produto_VendedorED.OP_INCLUIR);
        edProduto.setOid_Mix(ed.getOid_Mix());
        edProduto.setOid_Produto(ed.getOid_Produto());
        edProduto.setOid_Pessoa(ed.getOid_Pessoa_Distribuidor());
        new Produto_VendedorRN().GravaProdutos(edProduto);

        return ed;
    }

    /***************************************************************************
     * @throws
     * SQLException*************************************************************************
     *  
     **************************************************************************/
    public void altera(Mix_ProdutoED ed) throws Excecoes, SQLException {

        int oid_Mix_old = 0;

        this.inicioTransacao();

        //*** Busca oid antes de excluir [Produtos_Vendedores]
        String sql = " select oid_mix from mix_produtos "
                + " where oid_mix_produto = " + ed.getOid_Mix_Produto();
        ResultSet res2 = this.sql.executarConsulta(sql);
        if (res2.next()) {
            oid_Mix_old = res2.getInt("oid_mix");
        }

        Mix_ProdutoBD = new Mix_ProdutoBD(this.sql);
        Mix_ProdutoBD.altera(ed);

        this.fimTransacao(true);

        //*** INSERE da Tabela Produtos_Vendedor como [EXCLUIDO]
        if (oid_Mix_old != ed.getOid_Mix()) {
            Produto_VendedorED edProduto = new Produto_VendedorED();
            edProduto.setModulo(Produto_VendedorED.MODULO_MIX_PRODUTO);
            edProduto.setOperacao(Produto_VendedorED.OP_EXCLUIR);
            edProduto.setOid_Pessoa(ed.getOid_Pessoa_Distribuidor());
            edProduto.setOid_Mix(oid_Mix_old);
            edProduto.setOid_Produto(ed.getOid_Produto());
            new Produto_VendedorRN().GravaProdutos(edProduto);

            //*** INSERE da Tabela Produtos_Vendedor como [INCLUIDO]
            Produto_VendedorED edProduto2 = new Produto_VendedorED();
            edProduto2.setModulo(Produto_VendedorED.MODULO_MIX_PRODUTO);
            edProduto2.setOperacao(Produto_VendedorED.OP_INCLUIR);
            edProduto2.setOid_Pessoa(ed.getOid_Pessoa_Distribuidor());
            edProduto2.setOid_Mix(ed.getOid_Mix());
            edProduto2.setOid_Produto(ed.getOid_Produto());
            new Produto_VendedorRN().GravaProdutos(edProduto2);
        }

    }

    /***************************************************************************
     * @throws
     * SQLException*************************************************************************
     *  
     **************************************************************************/
    public void deleta(Mix_ProdutoED ed) throws Excecoes, SQLException {

        int oid_Mix_old = 0;

        this.inicioTransacao();

        //*** Busca oid antes de excluir [Produtos_Vendedores]
        String sql = " select oid_mix from mix_produtos "
                + " where oid_mix_produto = " + ed.getOid_Mix_Produto();
        ResultSet res2 = this.sql.executarConsulta(sql);
        if (res2.next()) {
            oid_Mix_old = res2.getInt("oid_mix");
        }

        Mix_ProdutoBD = new Mix_ProdutoBD(this.sql);
        Mix_ProdutoBD.deleta(ed);

        //*** INSERE da Tabela Produtos_Vendedor como [EXCLUIDO]
        if (oid_Mix_old > 0) {
            Produto_VendedorED edProduto = new Produto_VendedorED();
            edProduto.setModulo(Produto_VendedorED.MODULO_MIX_PRODUTO);
            edProduto.setOperacao(Produto_VendedorED.OP_EXCLUIR);
            edProduto.setOid_Pessoa(ed.getOid_Pessoa_Distribuidor());
            edProduto.setOid_Mix(oid_Mix_old);
            edProduto.setOid_Produto(ed.getOid_Produto());
            new Produto_VendedorRN().GravaProdutos(edProduto);
        }

        this.fimTransacao(true);

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public ArrayList lista(Mix_ProdutoED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new Mix_ProdutoBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public Mix_ProdutoED getByRecord(Mix_ProdutoED ed) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
        Mix_ProdutoED edVolta = new Mix_ProdutoED();
        //atribui ao ed de retorno
        edVolta = new Mix_ProdutoBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

    public Mix_ProdutoED getByOidMixProduto(int oid_Mix_Produto)
            throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
        Mix_ProdutoED edVolta = new Mix_ProdutoED();
        //atribui ao ed de retorno
        edVolta = new Mix_ProdutoBD(this.sql)
                .getByOidMixProduto(oid_Mix_Produto);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }
}