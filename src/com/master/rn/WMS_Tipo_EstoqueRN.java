package com.master.rn;

import java.util.ArrayList;

import com.master.bd.WMS_Tipo_EstoqueBD;
import com.master.ed.WMS_Tipo_EstoqueED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class WMS_Tipo_EstoqueRN extends Transacao {

    WMS_Tipo_EstoqueBD WMS_Tipo_EstoqueBD = null;
    public WMS_Tipo_EstoqueRN() {
    }

    public WMS_Tipo_EstoqueED inclui(WMS_Tipo_EstoqueED ed) throws Excecoes {

        this.inicioTransacao();
        WMS_Tipo_EstoqueBD = new WMS_Tipo_EstoqueBD(this.sql);
        ed = WMS_Tipo_EstoqueBD.inclui(ed);
        this.fimTransacao(true);

        return ed;
    }

    public void altera(WMS_Tipo_EstoqueED ed) throws Excecoes {

        this.inicioTransacao();
        WMS_Tipo_EstoqueBD = new WMS_Tipo_EstoqueBD(this.sql);
        WMS_Tipo_EstoqueBD.altera(ed);
        this.fimTransacao(true);
    }

    public void deleta(WMS_Tipo_EstoqueED ed) throws Excecoes {

        this.inicioTransacao();
        WMS_Tipo_EstoqueBD = new WMS_Tipo_EstoqueBD(this.sql);
        WMS_Tipo_EstoqueBD.deleta(ed);
        this.fimTransacao(true);
    }

    public WMS_Tipo_EstoqueED getByRecord(WMS_Tipo_EstoqueED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new WMS_Tipo_EstoqueBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);
        return ed;
    }

    public WMS_Tipo_EstoqueED getByCD_Tipo_Estoque(WMS_Tipo_EstoqueED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new WMS_Tipo_EstoqueBD(this.sql).getByCD_Tipo_Estoque(ed);
        this.fimTransacao(false);
        return ed;
    }

    public void geraRelatorio(WMS_Tipo_EstoqueED ed) throws Excecoes {

        this.inicioTransacao();
        WMS_Tipo_EstoqueBD = new WMS_Tipo_EstoqueBD(this.sql);
        WMS_Tipo_EstoqueBD.geraRelatorio(ed);
        this.fimTransacao(false);
    }

    public ArrayList lista(WMS_Tipo_EstoqueED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList listaVolta = new WMS_Tipo_EstoqueBD(sql).lista(ed);
        this.fimTransacao(false);
        return listaVolta;
    }
}