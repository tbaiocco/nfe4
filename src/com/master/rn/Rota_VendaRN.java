/*
 * Created on 01/10/2004
 */
package com.master.rn;

import java.util.ArrayList;
import com.master.bd.Cliente_VendedorBD;
import com.master.bd.Rota_VendaBD;
import com.master.ed.Cliente_VendedorED;
import com.master.ed.Rota_VendaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class Rota_VendaRN extends Transacao {

    public Rota_VendaRN() {
    }

    public Rota_VendaED inclui(Rota_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Rota_VendaBD(this.sql).GravaDados(ed, "I");
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public Rota_VendaED altera(Rota_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Rota_VendaBD(this.sql).GravaDados(ed, "A");
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Rota_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Rota_VendaBD(this.sql).GravaDados(ed, "D");
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deletaByDia(Rota_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Rota_VendaBD(this.sql).deletaByDia(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void tranfereRotasVendas(ArrayList rotas, String oid_Vendedor2) throws Exception {

        if (rotas.size() < 1 || !JavaUtil.doValida(oid_Vendedor2))
            return;
        try {            
            this.inicioTransacao();
            BancoUtil banco = new BancoUtil(this.sql, false);
            Cliente_VendedorBD cliVenBD = new Cliente_VendedorBD(this.sql);
            Rota_VendaBD rotaVenBD = new Rota_VendaBD(this.sql);
            
            for (int i=0; i<rotas.size(); i++)
            {
                Rota_VendaED edRota = (Rota_VendaED) rotas.get(i);
                Cliente_VendedorED edCliVen = new Cliente_VendedorED();
                
                //*** Verifica se ja existe cliente p/ incluir
                String oidClienteVendedor = edRota.getOid_Cliente()+edRota.getOid_Vendedor();
                String oidClienteVendedor2 = edRota.getOid_Cliente()+oid_Vendedor2;
                if (!banco.doExiste("Clientes_Vendedores","oid_Cliente_Vendedor="+JavaUtil.getSQLString(oidClienteVendedor2)))
                {
                    //*** CLIENTE VENDEDOR Original
                    edCliVen.setOid_Cliente_Vendedor(oidClienteVendedor);
                    edCliVen = cliVenBD.getByRecord(edCliVen);
                    //*** INCLUI DESTINO 
                    edCliVen.setOid_Vendedor(oid_Vendedor2);
                    cliVenBD.inclui(edCliVen);//Cliente Vendedor
                }
                
                //*** EXCLUI ORIGENS
                rotaVenBD.GravaDados(edRota, "D");//rota
                edCliVen.setOid_Cliente_Vendedor(oidClienteVendedor);
                cliVenBD.deleta(edCliVen);//Cliente Vendedor
                
                //*** Verifica se existe Rota p/ incluir
                edRota.setOid_Vendedor(oid_Vendedor2);
                //*** INCLUI DESTINO
                if (rotaVenBD.getByRecord(edRota).getOid_Rota_Venda() < 1)
                    rotaVenBD.GravaDados(edRota, "I");//rota
            }
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public ArrayList lista(Rota_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Rota_VendaBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    //Rotas por Cliente
    public ArrayList listaByCliente(Rota_VendaED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            return new Rota_VendaBD(sql).listaByCliente(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public Rota_VendaED getByRecord(Rota_VendaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Rota_VendaBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
}