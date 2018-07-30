package com.master.rn;

/**
 * @author Tiago Sauter Lauxen
 */

import java.util.List;

import com.master.bd.CidadeBD;
import com.master.ed.CidadeED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class CidadeRN extends Transacao {
    public CidadeRN() {
    }


    public List lista(CidadeED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            List lista = new CidadeBD(sql).lista(ed);
            return lista;
        } finally {
            fimTransacao(false);
        }
    }

    public void relCidades(CidadeED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            new CidadeBD(sql).relCidades(ed);
        } finally {
            fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.fimTransacao(false);
    }
}
