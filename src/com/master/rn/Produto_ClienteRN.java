package com.master.rn;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.master.bd.Produto_ClienteBD;
import com.master.bd.Produto_ClientePZBD;
import com.master.ed.Produto_ClienteED;
import com.master.ed.Produto_ClienteRelED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

public class Produto_ClienteRN extends Transacao {   

    public Produto_ClienteRN() {
    }

    public Produto_ClienteED inclui(Produto_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            if (Parametro_FixoED.getInstancia().getNM_Empresa().equals("PELLENZ"))
            {
    	        ed = new Produto_ClientePZBD(this.sql).inclui(ed);
            } else ed = new Produto_ClienteBD(this.sql).inclui(ed);
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

    public void altera(Produto_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            if(Parametro_FixoED.getInstancia().getNM_Empresa().equals("PELLENZ"))
                new Produto_ClientePZBD(this.sql).altera(ed);
            else new Produto_ClienteBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void atualizaCusto(Produto_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Produto_ClienteBD(this.sql).atualizaCustoBySaldoFinanceiro(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Produto_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            if (Parametro_FixoED.getInstancia().getNM_Empresa().equals("PELLENZ"))
                new Produto_ClientePZBD(this.sql).deleta(ed);
            else new Produto_ClienteBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public Produto_ClienteED getByRecord(Produto_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            if (Parametro_FixoED.getInstancia().getNM_Empresa().equals("PELLENZ"))
                return new Produto_ClientePZBD(this.sql).getByRecord(ed);
            else return new Produto_ClienteBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList listaRastreamento(String oid_produto_cliente, String orderby) throws Excecoes {

        try {
            this.inicioTransacao();
            if (Parametro_FixoED.getInstancia().getNM_Empresa().equals("PELLENZ"))
                return new Produto_ClientePZBD(sql).listaRastreamento(oid_produto_cliente, orderby);
            else return new Produto_ClienteBD(sql).listaRastreamento(oid_produto_cliente, orderby);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista(HttpServletRequest request, String orderby) throws Excecoes {

        try {
            this.inicioTransacao();
            if (Parametro_FixoED.getInstancia().getNM_Empresa().equals("PELLENZ"))
                return new Produto_ClientePZBD(sql).lista(request, orderby);
            else return new Produto_ClienteBD(sql).lista(request, orderby);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList listaProdutoClienteEstoque(Produto_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Produto_ClienteBD(sql).listaProdutoClienteEstoque(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList listaProdutoClienteLocal(Produto_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Produto_ClienteBD(sql).listaProdutoClienteLocal(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    //*** RELATÓRIOS
    public void RelProdutos_Cliente(HttpServletResponse response, Produto_ClienteRelED ed) throws Exception {
        
        try {
            this.inicioTransacao();
            new Produto_ClienteBD(this.sql).RelProdutos_Cliente(response, ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (sql !=  null)
            this.abortaTransacao();
    }
}