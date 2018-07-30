/*
 * Created on 25/10/2004
 */
package com.master.rn;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.Contrato_VendedorBD;
import com.master.ed.Contrato_VendedorED;
import com.master.ed.Contrato_VendedorRelED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class Contrato_VendedorRN extends Transacao {

    Contrato_VendedorBD Contrato_VendedorBD = null;

    public Contrato_VendedorRN() {

    }

    public Contrato_VendedorED inclui(Contrato_VendedorED ed) throws Excecoes {

        Contrato_VendedorED Contrato_VendedorED = new Contrato_VendedorED();

        this.inicioTransacao();
        Contrato_VendedorBD = new Contrato_VendedorBD(this.sql);
        Contrato_VendedorED = Contrato_VendedorBD.inclui(ed);
        this.fimTransacao(true);
        return Contrato_VendedorED;
    }

    public void deleta(Contrato_VendedorED ed) throws Excecoes {

        this.inicioTransacao();
        Contrato_VendedorBD = new Contrato_VendedorBD(this.sql);
        //*** Se ANEXO, exclui todos os ADENDOS referentes
        if (ed.getDM_Tipo_Contrato().equals("ANEXO")){
            ArrayList lista = Contrato_VendedorBD.lista(new Contrato_VendedorED(ed.getOid_Vendedor()));
            for (int i=0; i<lista.size(); i++){
                Contrato_VendedorBD.deleta(new Contrato_VendedorED(((Contrato_VendedorED) lista.get(i)).getOid_Contrato_Vendedor()));
            }
        } else Contrato_VendedorBD.deleta(ed);
        
        this.fimTransacao(true);

    }

    public ArrayList lista(Contrato_VendedorED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new Contrato_VendedorBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public Contrato_VendedorED getByRecord(Contrato_VendedorED ed) throws Excecoes {

        this.inicioTransacao();
        Contrato_VendedorED edVolta = new Contrato_VendedorED();
        edVolta = new Contrato_VendedorBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);
        return edVolta;
    }

    public ArrayList lista_Contrato(Contrato_VendedorED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new Contrato_VendedorBD(this.sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    //*** RELATÓRIOS
    public void RelContrato_Vendedor_ANEXO(HttpServletResponse response, Contrato_VendedorRelED ed) throws Exception {
        //inicia conexao com bd
        this.inicioTransacao();
        // Seleciona o relatório
        new Contrato_VendedorBD(this.sql).RelContrato_Vendedor_ANEXO(response, ed);
    }

    public void RelContrato_Vendedor_ADENDO(HttpServletResponse response, Contrato_VendedorRelED ed) throws Exception {
        //inicia conexao com bd
        this.inicioTransacao();
        // Seleciona o relatório
        new Contrato_VendedorBD(this.sql).RelContrato_Vendedor_ADENDO(response, ed);
    }
}