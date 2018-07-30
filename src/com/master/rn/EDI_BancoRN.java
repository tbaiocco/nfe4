package com.master.rn;

import java.util.ArrayList;

import com.master.bd.EDI_BancoBD;
import com.master.ed.EDI_BancoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class EDI_BancoRN extends Transacao {

    EDI_BancoBD EDI_BancoBD = null;

    public EDI_BancoRN() {
    }

    public ArrayList listaRetornoCobranca(EDI_BancoED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new EDI_BancoBD(sql).listaRetornoCobranca(ed);
        this.fimTransacao(false);
        return lista;
    }

    public EDI_BancoED atualizaRetornoCobranca(EDI_BancoED ed) throws Excecoes {

        this.inicioTransacao();
        EDI_BancoBD = new EDI_BancoBD(this.sql);
        ed = EDI_BancoBD.atualizaRetornoCobranca(ed);
        this.fimTransacao(true);

        return ed;
    }

    public EDI_BancoED importaRetornoCobranca(EDI_BancoED ed) throws Excecoes {

        this.inicioTransacao();
        EDI_BancoBD = new EDI_BancoBD(this.sql);
        ed = EDI_BancoBD.importaRetornoCobranca(ed);
        this.fimTransacao(true);

        return ed;
    }

    public ArrayList geraRemessaCobranca(EDI_BancoED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new EDI_BancoBD(sql).geraRemessaCobranca(ed);
        EDI_BancoBD = new EDI_BancoBD(this.sql);
        this.fimTransacao(false);
        return lista;
    }

    public ArrayList geraPreviaRemessa(EDI_BancoED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new EDI_BancoBD(sql).geraPreviaRemessa(ed);
        EDI_BancoBD = new EDI_BancoBD(this.sql);
        this.fimTransacao(false);
        return lista;
    }
    
    
    /***************************************************************************
     *
     **************************************************************************/
    public void atualizaRemessaCobranca(EDI_BancoED ed) throws Excecoes {

        this.inicioTransacao();
        EDI_BancoBD = new EDI_BancoBD(this.sql);
        EDI_BancoBD.atualizaRemessaCobranca(ed);
        this.fimTransacao(true);

    }
}
