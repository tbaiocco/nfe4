package com.master.rn;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.ProdutoBD;
import com.master.ed.ProdutoED;
import com.master.ed.ProdutoRelED;
import com.master.ed.RelatorioED;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

public class ProdutoRN extends Transacao {

    public ProdutoRN() {
    }

    public ProdutoED inclui(ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new ProdutoBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes e){
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e){
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new ProdutoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e){
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e){
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(ProdutoED ed) throws Excecoes {

        try {
	        this.inicioTransacao();
	        new ProdutoBD(this.sql).deleta(ed);
	        this.fimTransacao(true);
        } catch (Excecoes e){
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e){
            this.abortaTransacao();
            throw e;
        }
    }

    public ProdutoED getByRecord(ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new ProdutoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public ProdutoED getByRecordProdCli(ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new ProdutoBD(this.sql).getByRecordProdCli(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] geraRelatorioItemXCliente(ProdutoED ed) throws Excecoes {

        this.inicioTransacao();
        byte[] b = new ProdutoBD(this.sql).geraRelatorioItemXCliente(ed);
        this.fimTransacao(false);
        return b;
    }

    public byte[] geraRelatorioItemXLocalizacao(ProdutoED ed) throws Excecoes {

        this.inicioTransacao();
        byte[] b = new ProdutoBD(this.sql).geraRelatorioItemXLocalizacao(ed);
        this.fimTransacao(false);
        return b;
    }

    public ArrayList lista(ProdutoED ed) throws Excecoes {

        try {
	        this.inicioTransacao();
	        return new ProdutoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList listaProdutoCliente(ProdutoED ed) throws Excecoes {

        try {
	        this.inicioTransacao();
	        return new ProdutoBD(sql).listaProdutoCliente(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList listaDistribuidoras(ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new ProdutoBD(sql).listaDistribuidoras(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    //*** Produtos relacionados a Tabela de Preços
    public ArrayList listaProdutosVenda(ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new ProdutoBD(sql).listaProdutosVenda(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    public ProdutoED getPrecoTabelaByProduto(ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new ProdutoBD(sql).getPrecoTabelaByProduto(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    /** ------------------------------------------------------------------------ */
  	//*** Produtos Para Tabelas de Vendas
  	//    Busca todos produtos ainda NÃO RELACIONADOS a TABELA Passada 
    public ArrayList listaProdutoSemTabela(ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new ProdutoBD(sql).listaProdutoSemTabela(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    public ProdutoED getProdutoSemTabela(ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new ProdutoBD(sql).getProdutoSemTabela(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
  	//*** Produtos Tabelas de Vendas
  	//    Busca todos produtos RELACIONADOS a TABELAS DE VENDA 
    public ArrayList listaProdutoTabela(ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new ProdutoBD(sql).listaProdutoTabela(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    public ProdutoED getProdutoTabela(ProdutoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new ProdutoBD(sql).getProdutoTabela(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    /** ------------------------------------------------------------------------ */
    
    //*** RELATÓRIOS
    public void RelProdutos(HttpServletResponse response, ProdutoED ed, String Layout, String Filter) throws Exception, Excecoes {

        try {
	        this.inicioTransacao();
	        // Seleciona o relatório
	        if (Layout.equals(ProdutoRelED.MODELO_1))
	            new ProdutoBD(this.sql).RelProdutos(response, ed, Filter);
	        else throw new Excecoes("Layout inválido!");
        } finally {
            this.fimTransacao(false);
        }
    }

    public void RelProdutosEstoque(HttpServletResponse response, RelatorioED ed) throws Exception {

        try {
	        this.inicioTransacao();
	        // Seleciona o relatório
	        if (JavaUtil.doValida(ed.getLayout()))
	            new ProdutoBD(this.sql).RelProdutosEstoque(response, ed);
	        else throw new Excecoes("Layout inválido!");
        } finally {
            this.fimTransacao(false);
        }
    }

    public void RelProdutosComprados(HttpServletResponse response, RelatorioED ed) throws Exception {

        try {
	        this.inicioTransacao();
	        // Seleciona o relatório
	        if (JavaUtil.doValida(ed.getLayout()))
	            new ProdutoBD(this.sql).RelProdutosComprados(response, ed);
	        else throw new Excecoes("Layout inválido!");
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public void relEstruturasProduto(RelatorioED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new ProdutoBD(this.sql).relEstruturasProduto(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}