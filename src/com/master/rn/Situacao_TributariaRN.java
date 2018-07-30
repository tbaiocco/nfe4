/*
 * Created on 10/09/2004
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Situacao_TributariaBD;
import com.master.ed.Situacao_TributariaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class Situacao_TributariaRN extends Transacao {

    Situacao_TributariaBD Situacao_TributariaBD = null;

    public Situacao_TributariaRN() {

    }

    public Situacao_TributariaED inclui(Situacao_TributariaED ed) throws Excecoes {

        Situacao_TributariaED Situacao_TributariaED = new Situacao_TributariaED();
        this.inicioTransacao();
        Situacao_TributariaBD = new Situacao_TributariaBD(this.sql);
        Situacao_TributariaED = Situacao_TributariaBD.inclui(ed);
        this.fimTransacao(true);
        return Situacao_TributariaED;
    }

    public void altera(Situacao_TributariaED ed) throws Excecoes {

        this.inicioTransacao();
        Situacao_TributariaBD = new Situacao_TributariaBD(this.sql);
        Situacao_TributariaBD.altera(ed);
        this.fimTransacao(true);
    }

    public void deleta(Situacao_TributariaED ed) throws Excecoes {

        this.inicioTransacao();
        Situacao_TributariaBD = new Situacao_TributariaBD(this.sql);
        Situacao_TributariaBD.deleta(ed);
        this.fimTransacao(true);
    }

    public ArrayList lista(Situacao_TributariaED ed) throws Excecoes {

        // retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new Situacao_TributariaBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public Situacao_TributariaED getByRecord(Situacao_TributariaED ed) throws Excecoes {

        this.inicioTransacao();
        Situacao_TributariaED edVolta = new Situacao_TributariaED();
        edVolta = new Situacao_TributariaBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);
        return edVolta;
    }

    public Situacao_TributariaED getByOidSituacao_Tributaria(int oid_Situacao_Tributaria) throws Excecoes {

        this.inicioTransacao();
        Situacao_TributariaED edVolta = new Situacao_TributariaED();
        edVolta = new Situacao_TributariaBD(this.sql).getByOidSituacao_Tributaria(oid_Situacao_Tributaria);
        this.fimTransacao(false);
        return edVolta;
    }

    public Situacao_TributariaED getByCD_Situacao_Tributaria(String cd_Situacao_Tributaria) throws Excecoes {

        this.inicioTransacao();
        Situacao_TributariaED edVolta = new Situacao_TributariaED();
        edVolta = new Situacao_TributariaBD(this.sql).getByCD_Situacao_Tributaria(cd_Situacao_Tributaria);
        this.fimTransacao(false);
        return edVolta;
    }

    // Verifica se registro já existe!
    public boolean doExiste(Situacao_TributariaED ed) throws Excecoes {

        this.inicioTransacao();
        boolean existe;
        existe = new Situacao_TributariaBD(this.sql).doExiste(ed);
        this.fimTransacao(false);
        return existe;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.sql != null)
            this.abortaTransacao();
    }

}