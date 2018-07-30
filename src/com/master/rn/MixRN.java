/*
 * Created on 31/08/2004
 */
package com.master.rn;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.MixBD;
import com.master.ed.MixED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class MixRN extends Transacao {

    MixBD MixBD = null;

    public MixRN() {

    }

    public MixED inclui(MixED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new MixBD(this.sql).inclui(ed);
        } finally {
            this.fimTransacao(true);
        }
        return ed;
    }

    public void altera(MixED ed) throws Excecoes {

        this.inicioTransacao();
        MixBD = new MixBD(this.sql);
        MixBD.altera(ed);

        this.fimTransacao(true);
    }

    public void deleta(MixED ed) throws Excecoes {

        this.inicioTransacao();
        MixBD = new MixBD(this.sql);
        MixBD.deleta(ed);
        this.fimTransacao(true);
    }

    public ArrayList lista(MixED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new MixBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public MixED getByRecord(MixED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new MixBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);
        return ed;
    }

    public MixED getByCdMix(String cd_Mix) throws Excecoes {

        this.inicioTransacao();
        MixED edVolta = new MixED();
        edVolta = new MixBD(this.sql).getByCdMix(cd_Mix);
        this.fimTransacao(false);
        return edVolta;
    }

    //*** RELATÓRIOS
    public void RelMix(HttpServletResponse response) throws Exception {

        this.inicioTransacao();
        new MixBD(this.sql).RelMix(response);
        this.fimTransacao(false);
    }
}