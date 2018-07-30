/*
 * Created on 19/01/2005
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.DespachanteBD;
import com.master.ed.DespachanteED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 */
public class DespachanteRN extends Transacao {

    DespachanteBD DespachanteBD = null;

    public DespachanteRN() {

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public DespachanteED inclui( DespachanteED ed) throws Excecoes {

         DespachanteED  DespachanteED = new  DespachanteED();

         this.inicioTransacao();
         DespachanteBD = new  DespachanteBD(this.sql);
         DespachanteED =  DespachanteBD.inclui(ed);
         this.fimTransacao(true);
         
         return  DespachanteED;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void altera( DespachanteED ed) throws Excecoes {

        this.inicioTransacao();
        DespachanteBD = new  DespachanteBD(this.sql);
        DespachanteBD.altera(ed);
        this.fimTransacao(true);
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void deleta( DespachanteED ed) throws Excecoes {

        this.inicioTransacao();
        DespachanteBD = new  DespachanteBD(this.sql);
        DespachanteBD.deleta(ed);
        this.fimTransacao(true);
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public ArrayList lista(DespachanteED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new  DespachanteBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }
   
    /***************************************************************************
     *  
     **************************************************************************/
    public DespachanteED getByRecord(DespachanteED ed) throws Excecoes {

        this.inicioTransacao();
        DespachanteED edVolta = new DespachanteED();
        edVolta = new  DespachanteBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);

        return edVolta;
    }
    
    /***************************************************************************
     *  
     **************************************************************************/
    public DespachanteED getByCdDespachante(String cd_Despachante) throws Excecoes {

        this.inicioTransacao();
        DespachanteED edVolta = new DespachanteED();
        edVolta = new  DespachanteBD(this.sql).getByCdDespachante(cd_Despachante);
        this.fimTransacao(false);

        return edVolta;
    }
}