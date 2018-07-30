package com.master.rn;

import java.util.ArrayList;

import com.master.bd.EntregaBD;
import com.master.ed.EntregaED;
import com.master.ed.RelatorioED;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 */
public class EntregaRN extends Transacao {

    public EntregaRN() {
    }

    public EntregaED inclui(EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new EntregaBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            //*** Gera Documentos Entregas
            new Documento_EntregaRN().geraDocumentosEntrega(ed);
            return ed;
        } catch(RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new EntregaBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void deleta(EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new EntregaBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista(EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new EntregaBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public EntregaED getByRecord(EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new EntregaBD(this.sql).getByRecord(ed);
            return ed;
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public void finalizaAcerto(EntregaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            //*** Valida se Salto == 0
            if (((EntregaED)new EntregaBD(this.sql).getByRecord(ed)).getVL_Saldo() != 0)
                throw new Mensagens("Valores não conferem! Saldo Incorreto!");
            
            new EntregaBD(this.sql).finalizaAcerto(ed);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch(RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }
    
    /** ------------ RELATÓRIOS ---------------- */	
    //*** Acerto de Entregas
    public void relAcertoEntrega(RelatorioED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new EntregaBD(this.sql).relAcertoEntrega(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}
