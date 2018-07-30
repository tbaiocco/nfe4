package com.master.rn;

/**
 * @author Tiago Sauter Lauxen
 */

import java.util.List;

import com.master.bd.Movimento_Ordem_ServicoBD;
import com.master.ed.Movimento_Ordem_ServicoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;


public class Movimento_Ordem_ServicoRN extends Transacao {
    public Movimento_Ordem_ServicoRN() {
    }

    public List lista(Movimento_Ordem_ServicoED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            List lista = new Movimento_Ordem_ServicoBD(sql).lista(ed);
            return lista;
        } finally {
            fimTransacao(false);
        }
    }


    public void relMovimentosOrdemServico(Movimento_Ordem_ServicoED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            new Movimento_Ordem_ServicoBD(sql).relMovimentosOrdemServico(ed);
        } finally {
            fimTransacao(false);
        }
    }

    public void relAbastecimento(Movimento_Ordem_ServicoED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            new Movimento_Ordem_ServicoBD(sql).relAbastecimento(ed);
        } finally {
            fimTransacao(false);
        }
    }
    
    public void relKMsRodados(Movimento_Ordem_ServicoED ed)
    throws Excecoes {
    	inicioTransacao();
    	try {
    		new Movimento_Ordem_ServicoBD(sql).relKMsRodados(ed);
    	} finally {
    		fimTransacao(false);
    	}
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.fimTransacao(false);
    }
}