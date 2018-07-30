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

import com.master.bd.ConhecimentoBD;
import com.master.bd.Conhecimento_Nota_FiscalBD;
import com.master.bd.Programacao_CargaBD;
import com.master.ed.ConhecimentoED;
import com.master.ed.Conhecimento_Nota_FiscalED;
import com.master.ed.Programacao_CargaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Conhecimento_Nota_FiscalRN extends Transacao {

    Conhecimento_Nota_FiscalBD Conhecimento_Nota_FiscalBD = null;

    public Conhecimento_Nota_FiscalRN() {
    }

    public Conhecimento_Nota_FiscalED inclui(Conhecimento_Nota_FiscalED ed) throws Excecoes {
        if ("".equals(ed.getOID_Nota_Fiscal())) {
            throw new Excecoes("Código do Nota_Fiscal não foi informado!");
        }
        inicioTransacao();
        try {
            Conhecimento_Nota_FiscalBD = new Conhecimento_Nota_FiscalBD(this.sql);
            Conhecimento_Nota_FiscalED ocorrencia_Nota_FiscalED = Conhecimento_Nota_FiscalBD.inclui(ed);

            new Programacao_CargaBD(this.sql).setVolumes(ed.getOID_Conhecimento(), ed.getNR_Peso());

            this.fimTransacao(true);
            return ocorrencia_Nota_FiscalED;
        } catch (Excecoes e) {
            abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public void altera(Conhecimento_Nota_FiscalED ed) throws Excecoes {
        if (String.valueOf(ed.getOID_Conhecimento_Nota_Fiscal()).compareTo("") == 0) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Código do Conhecimento_Nota_Fiscal não foi informado !!!");
            throw exc;
        }
        try {
            this.inicioTransacao();
            Conhecimento_Nota_FiscalBD = new Conhecimento_Nota_FiscalBD(this.sql);
            Conhecimento_Nota_FiscalBD.altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public void deleta(Conhecimento_Nota_FiscalED ed) throws Excecoes {

        if (String.valueOf(ed.getOID_Conhecimento_Nota_Fiscal()).compareTo("") == 0) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Código do Conhecimento_Nota_Fiscal não foi informado !!!");
            throw exc;
        }

        try {
            this.inicioTransacao();
            Conhecimento_Nota_FiscalBD = new Conhecimento_Nota_FiscalBD(this.sql);
            Conhecimento_Nota_FiscalBD.deleta(ed);
            this.fimTransacao(true);
        } catch (Exception e) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Erro de exclusão");
            this.abortaTransacao();
            throw exc;
        }

    }

    public ArrayList lista(Conhecimento_Nota_FiscalED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new Conhecimento_Nota_FiscalBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public Conhecimento_Nota_FiscalED getByRecord(Conhecimento_Nota_FiscalED ed) throws Excecoes {
        inicioTransacao();
        try {
            return new Conhecimento_Nota_FiscalBD(sql).getByRecord(ed);
        } finally {
            fimTransacao(false);
        }
    }

    public int qtde_Notas_Fiscais_Conhecimento(String oid_Conhecimento) throws Excecoes {
        inicioTransacao();
        try {
            return new Conhecimento_Nota_FiscalBD(sql).qtde_Notas_Fiscais_Conhecimento(oid_Conhecimento);
        } finally {
            fimTransacao(false);
        }
    }
}