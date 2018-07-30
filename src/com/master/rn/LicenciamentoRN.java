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

import com.master.bd.LicenciamentoBD;
import com.master.ed.LicenciamentoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class LicenciamentoRN extends Transacao {

    public LicenciamentoRN() {
    }

    public LicenciamentoED inclui(LicenciamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new LicenciamentoBD(this.sql).inclui(ed);
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

    public void altera(LicenciamentoED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new LicenciamentoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void deleta(LicenciamentoED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new LicenciamentoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista(LicenciamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new LicenciamentoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public LicenciamentoED getByRecord(LicenciamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new LicenciamentoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public boolean getLiberacao_Licenciamento(String oid_Veiculo) throws Excecoes {

        try {
            this.inicioTransacao();
            return new LicenciamentoBD(this.sql).getLiberacao_Licenciamento(oid_Veiculo);
        } finally {
            this.fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}