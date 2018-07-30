package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.ArrayList;

import com.master.bd.Documento_Conta_CorrenteBD;
import com.master.ed.Documento_Conta_CorrenteED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Documento_Conta_CorrenteRN extends Transacao {

    Documento_Conta_CorrenteBD Documento_Conta_CorrenteBD = null;

    public Documento_Conta_CorrenteRN() {
    }

    public Documento_Conta_CorrenteED inclui(Documento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            Documento_Conta_CorrenteBD = new Documento_Conta_CorrenteBD(this.sql);
            ed = Documento_Conta_CorrenteBD.inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void altera(Documento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            Documento_Conta_CorrenteBD = new Documento_Conta_CorrenteBD(this.sql);
            Documento_Conta_CorrenteBD.altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {  
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void deleta(Documento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            Documento_Conta_CorrenteBD = new Documento_Conta_CorrenteBD(this.sql);
            Documento_Conta_CorrenteBD.deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista(Documento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Documento_Conta_CorrenteBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Documento_Conta_CorrenteED getByRecord(Documento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Documento_Conta_CorrenteBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public String consulta_Ultimo_Numero(Documento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Documento_Conta_CorrenteBD(this.sql).consulta_Ultimo_Numero(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void geraRelatorio(Documento_Conta_CorrenteED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            Documento_Conta_CorrenteBD = new Documento_Conta_CorrenteBD(this.sql);
            Documento_Conta_CorrenteBD.geraRelatorio(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}