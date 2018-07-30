package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Documento_EntregaBD;
import com.master.ed.Documento_EntregaED;
import com.master.ed.EntregaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Documentos Entregas
 * @serialData 31/03/2005
 */
public class Documento_EntregaRN extends Transacao {

    public Documento_EntregaRN() {
    }

    public Documento_EntregaED inclui(Documento_EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Documento_EntregaBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(Documento_EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Documento_EntregaBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Documento_EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Documento_EntregaBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Documento_EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Documento_EntregaBD(this.sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public Documento_EntregaED getByRecord(Documento_EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Documento_EntregaBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public void geraDocumentosEntrega(EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Documento_EntregaBD(this.sql).geraDocumentosEntrega(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
}
