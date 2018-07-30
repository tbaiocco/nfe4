package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Pedido_Nota_FiscalBD;
import com.master.ed.Pedido_Nota_FiscalED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serialData 03/02/2005
 * @JavaBean.class Pedido_Nota_FiscalRN
 */
public class Pedido_Nota_FiscalRN extends Transacao {

    Pedido_Nota_FiscalBD Pedido_Nota_FiscalBD = null;
    
    public Pedido_Nota_FiscalRN() {
    }

    public Pedido_Nota_FiscalED inclui(Pedido_Nota_FiscalED ed) throws Excecoes {

         this.inicioTransacao();
         ed =  new Pedido_Nota_FiscalBD(this.sql).inclui(ed);
         this.fimTransacao(true);
         
         return ed;
    }

    public void altera(Pedido_Nota_FiscalED ed) throws Excecoes {

        this.inicioTransacao();
        new Pedido_Nota_FiscalBD(this.sql).altera(ed);
        this.fimTransacao(true);
    }

    public void deleta(Pedido_Nota_FiscalED ed) throws Excecoes {

        this.inicioTransacao();
        new Pedido_Nota_FiscalBD(this.sql).deleta(ed);
        this.fimTransacao(true);
    }

    public ArrayList lista(Pedido_Nota_FiscalED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new Pedido_Nota_FiscalBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }
   
    public Pedido_Nota_FiscalED getByRecord(Pedido_Nota_FiscalED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new Pedido_Nota_FiscalBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);

        return ed;
    }
}