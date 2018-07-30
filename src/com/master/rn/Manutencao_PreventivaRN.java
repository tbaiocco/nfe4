package com.master.rn;

/**
 * @author Tiago Sauter Lauxen
 */

import java.util.List;

import com.master.bd.Manutencao_PreventivaBD;
import com.master.ed.Manutencao_PreventivaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Manutencao_PreventivaRN extends Transacao {
    public Manutencao_PreventivaRN() {
    }

    public List lista(Manutencao_PreventivaED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            List lista = new Manutencao_PreventivaBD(sql).lista(ed);
            return lista;
        } finally {
            fimTransacao(false);
        }
    }

    public void relManutencaoPreventivaVeiculo(Manutencao_PreventivaED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            new Manutencao_PreventivaBD(sql).relManutencaoPreventivaVeiculo(ed);
        } finally {
            fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.fimTransacao(false);
    }
}