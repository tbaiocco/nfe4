package com.master.rn;

import java.util.ArrayList;
import java.util.List;
import com.master.bd.Mensagem_VendedorBD;
import com.master.ed.Mensagem_VendedorED;
import com.master.root.VendedorBean;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Mensagens Vendedores
 * @serialData 27/02/2006
 */
public class Mensagem_VendedorRN extends Transacao {

    public Mensagem_VendedorRN() {
    }

    public Mensagem_VendedorED inclui(Mensagem_VendedorED ed) throws Exception {

        try {
            this.inicioTransacao();
            
            if (JavaUtil.doValida(ed.getOid_Vendedor()))
                new Mensagem_VendedorBD(this.sql).inclui(ed);
            else {
                List lista = VendedorBean.getAll("V','R");
                for (int i = 0; i < lista.size(); i++)
                {
                    VendedorBean edVen = (VendedorBean)lista.get(i);
                    //*** Somente Vendedores/Supervisores ATIVOS
                    if ("A".equals(edVen.getDM_Situacao()))
                    {
                        ed.setOid_Vendedor(edVen.getOID_Vendedor());
                        new Mensagem_VendedorBD(this.sql).inclui(ed);
                    }
                }
            }
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
    
    public void altera(ArrayList mensagens) throws Exception {

        if (mensagens.size() < 1)
            return;
        try {
            this.inicioTransacao();
            for (int i=0; i<mensagens.size(); i++)
            {
                Mensagem_VendedorED edMsg = (Mensagem_VendedorED) mensagens.get(i);
                this.sql.executarUpdate(" UPDATE Mensagens_Vendedores SET " +
                                        "    TX_Mensagem = "+JavaUtil.getSQLString(edMsg.getTX_Mensagem())+
                                        " WHERE oid_Mensagem_Vendedor = "+edMsg.getOid_Mensagem_Vendedor());
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

    public void deleta(Mensagem_VendedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Mensagem_VendedorBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Mensagem_VendedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Mensagem_VendedorBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Mensagem_VendedorED getByRecord(Mensagem_VendedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Mensagem_VendedorBD(sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
}