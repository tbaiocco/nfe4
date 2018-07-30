package com.master.rn;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.bd.Centro_CustoBD;
import com.master.ed.Centro_CustoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Centro_CustoRN extends Transacao {

    Centro_CustoBD Centro_CustoBD = null;

    public Centro_CustoRN() {
    }

    public Centro_CustoED inclui(Centro_CustoED ed) throws Excecoes {

        Centro_CustoED centro_CustoED = new Centro_CustoED();

        this.inicioTransacao();
        Centro_CustoBD = new Centro_CustoBD(this.sql);
        centro_CustoED = Centro_CustoBD.inclui(ed);
        this.fimTransacao(true);

        return centro_CustoED;

    }

    public void altera(Centro_CustoED ed) throws Excecoes {

        this.inicioTransacao();
        Centro_CustoBD = new Centro_CustoBD(this.sql);
        Centro_CustoBD.altera(ed);
        this.fimTransacao(true);

    }

    public void deleta(Centro_CustoED ed) throws Excecoes {

        this.inicioTransacao();
        Centro_CustoBD = new Centro_CustoBD(this.sql);
        Centro_CustoBD.deleta(ed);
        this.fimTransacao(true);

    }

    public ArrayList lista(Centro_CustoED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new Centro_CustoBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public Centro_CustoED getByRecord(Centro_CustoED ed) throws Excecoes {

        this.inicioTransacao();
        Centro_CustoED edVolta = new Centro_CustoED();
        edVolta = new Centro_CustoBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);
        return edVolta;
    }

    public void getByRecord(Centro_CustoED ed, HttpServletRequest request, String nmObj) throws Excecoes{
        this.inicioTransacao();
        Centro_CustoED edVolta = new Centro_CustoED();
        edVolta = new Centro_CustoBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);
        request.setAttribute(nmObj,edVolta) ;
    }

    public void geraRelatorio(Centro_CustoED ed) throws Excecoes {

        this.inicioTransacao();
        Centro_CustoBD = new Centro_CustoBD(this.sql);
        Centro_CustoBD.geraRelatorio(ed);
        this.fimTransacao(false);

    }
    
    
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.sql != null)
            this.abortaTransacao();
    }
}