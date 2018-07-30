/*
 * Created on 19/01/2005
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Cliente_DespachanteBD;
import com.master.ed.Cliente_DespachanteED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 */
public class Cliente_DespachanteRN extends Transacao {

    Cliente_DespachanteBD Cliente_DespachanteBD = null;

    public Cliente_DespachanteRN() {

    }

    public Cliente_DespachanteED inclui(Cliente_DespachanteED ed)
            throws Excecoes {

        this.inicioTransacao();
        ed = new Cliente_DespachanteBD(this.sql).inclui(ed);
        this.fimTransacao(true);
        
        return ed;
    }

    public void altera(Cliente_DespachanteED ed) throws Excecoes {

        this.inicioTransacao();
        Cliente_DespachanteBD = new Cliente_DespachanteBD(this.sql);
        Cliente_DespachanteBD.altera(ed);
        this.fimTransacao(true);
    }

    public void deleta(Cliente_DespachanteED ed) throws Excecoes {

        this.inicioTransacao();
        Cliente_DespachanteBD = new Cliente_DespachanteBD(this.sql);
        Cliente_DespachanteBD.deleta(ed);
        //Cliente_DespachanteBD.gamb_Seguro();
        this.fimTransacao(true);
    }

    public ArrayList lista(Cliente_DespachanteED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new Cliente_DespachanteBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public Cliente_DespachanteED getByRecord(Cliente_DespachanteED ed)
            throws Excecoes {

        this.inicioTransacao();
        Cliente_DespachanteED edVolta = new Cliente_DespachanteED();
        edVolta = new Cliente_DespachanteBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);

        return edVolta;
    }
    
    public ArrayList lista_MIC(Cliente_DespachanteED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new Cliente_DespachanteBD(sql).lista_MIC(ed);
        this.fimTransacao(false);
        return lista;
    }
}