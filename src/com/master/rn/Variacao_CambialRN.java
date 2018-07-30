package com.master.rn;

import java.util.ArrayList;
import com.master.bd.Variacao_CambialBD;
import com.master.ed.Variacao_CambialED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Variacao_CambialRN extends Transacao {

    Variacao_CambialBD Variacao_CambialBD = null;

    public Variacao_CambialRN() {
    }

    public Variacao_CambialED geraVariacao_Cambial(Variacao_CambialED ed) throws Excecoes {
        inicioTransacao();
        try {
            Variacao_CambialBD Variacao_CambialBD = new Variacao_CambialBD(sql);
            ed = Variacao_CambialBD.geraVariacao_Cambial(ed);
            fimTransacao(true);
            return ed;
        } catch (Excecoes exc) {
            abortaTransacao();
            throw exc;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }


    public ArrayList lista(Variacao_CambialED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new Variacao_CambialBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }


    public Variacao_CambialED getByRecord(Variacao_CambialED ed) throws Excecoes {
        Variacao_CambialED edVolta = new Variacao_CambialED();
        try {
            this.inicioTransacao();
            edVolta = new Variacao_CambialBD(this.sql).getByRecord(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
        return edVolta;
    }


/*
    public byte[] geraRelatorioConheEmbarque(Variacao_CambialED ed) throws Excecoes {

        //antes de invocar chamada ao relatorio deve-se
        //fazer validacoes de regra de negocio

        this.inicioTransacao();
        Variacao_CambialBD = new Variacao_CambialBD(this.sql);
        byte[] b = Variacao_CambialBD.geraRelVariacao_CambialEmbarque(ed);
        this.fimTransacao(false);
        return b;
    }
*/

}
