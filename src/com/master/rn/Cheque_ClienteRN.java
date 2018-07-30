package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Cheque_ClienteBD;
import com.master.bd.EDI_Malote_EletronicoBD;
import com.master.ed.Cheque_ClienteED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Cheques Clientes
 * @serialData 04/04/2005
 */
public class Cheque_ClienteRN extends Transacao {

    public Cheque_ClienteRN() {
    }

    public Cheque_ClienteED inclui(Cheque_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Cheque_ClienteBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void altera(Cheque_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Cheque_ClienteBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void deleta(Cheque_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Cheque_ClienteBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista(Cheque_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Cheque_ClienteBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public ArrayList listaSemLote(Cheque_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Cheque_ClienteBD(sql).listaSemLote(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public Cheque_ClienteED getByRecord(Cheque_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Cheque_ClienteBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public Cheque_ClienteED getByRecordSemLote(Cheque_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Cheque_ClienteBD(this.sql).getByRecordSemLote(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public String geraMaloteEletronico(Cheque_ClienteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new EDI_Malote_EletronicoBD(this.sql).geraMaloteBanrisul(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.sql != null)
            this.abortaTransacao();
    }
}