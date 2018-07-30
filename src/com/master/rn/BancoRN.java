package com.master.rn;

import java.util.ArrayList;

import com.master.bd.BancoBD;
import com.master.ed.BancoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class BancoRN extends Transacao {

    BancoBD BancoBD = null;

    public BancoRN() {
    }

    public void inclui(BancoED ed) throws Excecoes {

        if (ed.getCD_Banco().compareTo("") == 0) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Código do Banco não foi informado!");
            throw exc;
        }

        try {

            this.inicioTransacao();
            BancoBD = new BancoBD(this.sql);
            BancoBD.inclui(ed);
            this.fimTransacao(true);

        } catch (Exception e) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Erro de inclusão");
            this.abortaTransacao();
            throw exc;
        }

    }

    public void altera(BancoED ed) throws Excecoes {

        if (ed.getCD_Banco().compareTo("") == 0) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Código do Banco não foi informado!");
            throw exc;
        }

        try {

            this.inicioTransacao();
            BancoBD = new BancoBD(this.sql);
            BancoBD.altera(ed);
            this.fimTransacao(true);

        } catch (Exception e) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Erro de alteração");
            this.abortaTransacao();
            throw exc;
        }
    }

    public void deleta(BancoED ed) throws Excecoes {

        this.inicioTransacao();
        BancoBD = new BancoBD(this.sql);
        BancoBD.deleta(ed);
        this.fimTransacao(true);
    }

    public ArrayList lista(BancoED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new BancoBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public BancoED getByRecord(BancoED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new BancoBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);
        return ed;
    }

    public void geraRelatorio(BancoED ed) throws Excecoes {

        this.inicioTransacao();
        BancoBD = new BancoBD(this.sql);
        BancoBD.geraRelatorio(ed);
        this.fimTransacao(false);
    }
}