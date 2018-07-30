package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Ocorrencia_PedidoBD;
import com.master.ed.Ocorrencia_PedidoED;
import com.master.ed.PedidoED;
import com.master.ed.RelatorioED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serialData 27/10/2004
 * @JavaBean.class Ocorrencia_PedidoRN
 */
public class Ocorrencia_PedidoRN extends Transacao {

    public Ocorrencia_PedidoRN() {
    }

    public Ocorrencia_PedidoED inclui(Ocorrencia_PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Ocorrencia_PedidoBD(this.sql).inclui(ed);
            this.fimTransacao(true);
         	return ed;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public void altera(Ocorrencia_PedidoED ed) throws Excecoes {

        try {
	        this.inicioTransacao();
	        new Ocorrencia_PedidoBD(this.sql).altera(ed);
	        this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public void deleta(Ocorrencia_PedidoED ed) throws Excecoes {

        try {
	        this.inicioTransacao();
	        new Ocorrencia_PedidoBD(this.sql).deleta(ed);
	        this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Ocorrencia_PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Ocorrencia_PedidoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public Ocorrencia_PedidoED getByRecord(Ocorrencia_PedidoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Ocorrencia_PedidoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    //*** Por Cliente(OBRIGATÓRIO SEMPRE)/Pedido/Vendedor
    public int verificaFinanceiro(PedidoED ed) throws Excecoes {
        
        //*** Nº de Ocorrencias Geradas
        int nrOcorrencias = 0;
        try {
	        this.inicioTransacao();
	        nrOcorrencias = new Ocorrencia_PedidoBD(this.sql).verificaFinanceiro(ed);
	        this.fimTransacao(true);
	        return nrOcorrencias;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }
    //*** Verifica Estoque
    public int verificaEstoque(PedidoED ed) throws Excecoes {
        //*** Nº de Ocorrencias Geradas
        int nrOcorrencias = 0;
        try {
	        this.inicioTransacao();
	        nrOcorrencias = new Ocorrencia_PedidoBD(this.sql).verificaEstoque(ed);
	        this.fimTransacao(true);
	        return nrOcorrencias;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }
    
    //*** Verifica Crítica Pedidos
    public ArrayList listaCriticaPedidos(Ocorrencia_PedidoED ed) throws Excecoes {
        
        try {
            this.inicioTransacao();
            return new Ocorrencia_PedidoBD(this.sql).listaCriticaPedidos(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    /** ------------ RELATÓRIOS ---------------- */
	//*** Ocorrências Pedido
    public void relOcorrenciasPedido(RelatorioED ed) throws Excecoes {
        
        try {
            this.inicioTransacao();
            new Ocorrencia_PedidoBD(this.sql).relOcorrenciasPedido(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}