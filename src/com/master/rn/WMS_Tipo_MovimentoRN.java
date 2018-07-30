package com.master.rn;

import java.util.ArrayList;

import com.master.bd.WMS_Tipo_MovimentoBD;
import com.master.ed.WMS_Tipo_MovimentoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class WMS_Tipo_MovimentoRN extends Transacao {

    WMS_Tipo_MovimentoBD WMS_Tipo_MovimentoBD = null;

    public WMS_Tipo_MovimentoRN() {
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public WMS_Tipo_MovimentoED inclui(WMS_Tipo_MovimentoED ed) throws Excecoes {

        WMS_Tipo_MovimentoED WMS_Tipo_MovimentoED = new WMS_Tipo_MovimentoED();

        this.inicioTransacao();
        WMS_Tipo_MovimentoBD = new WMS_Tipo_MovimentoBD(this.sql);
        WMS_Tipo_MovimentoED = WMS_Tipo_MovimentoBD.inclui(ed);
        this.fimTransacao(true);

        return WMS_Tipo_MovimentoED;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void altera(WMS_Tipo_MovimentoED ed) throws Excecoes {

        this.inicioTransacao();
        WMS_Tipo_MovimentoBD = new WMS_Tipo_MovimentoBD(this.sql);
        WMS_Tipo_MovimentoBD.altera(ed);
        this.fimTransacao(true);

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void deleta(WMS_Tipo_MovimentoED ed) throws Excecoes {

        this.inicioTransacao();
        WMS_Tipo_MovimentoBD = new WMS_Tipo_MovimentoBD(this.sql);
        WMS_Tipo_MovimentoBD.deleta(ed);
        this.fimTransacao(true);
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public WMS_Tipo_MovimentoED getByRecord(WMS_Tipo_MovimentoED ed) throws Excecoes {

        this.inicioTransacao();
        WMS_Tipo_MovimentoED edVolta = new WMS_Tipo_MovimentoED();
        edVolta = new WMS_Tipo_MovimentoBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);
        return edVolta;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public WMS_Tipo_MovimentoED getByOid(int Oid) throws Excecoes {

        this.inicioTransacao();
        WMS_Tipo_MovimentoED edVolta = new WMS_Tipo_MovimentoED();
        edVolta = new WMS_Tipo_MovimentoBD(this.sql).getByOid(Oid);
        this.fimTransacao(false);
        return edVolta;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public ArrayList getAll() throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new WMS_Tipo_MovimentoBD(sql).getAll();
        this.fimTransacao(false);
        return lista;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public ArrayList lista(WMS_Tipo_MovimentoED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList listaVolta = new WMS_Tipo_MovimentoBD(sql).lista(ed);
        this.fimTransacao(false);
        return listaVolta;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void geraRelatorio(WMS_Tipo_MovimentoED ed) throws Excecoes {

        this.inicioTransacao();
        WMS_Tipo_MovimentoBD = new WMS_Tipo_MovimentoBD(this.sql);
        WMS_Tipo_MovimentoBD.geraRelatorio(ed);
        this.fimTransacao(false);
    }
    
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}