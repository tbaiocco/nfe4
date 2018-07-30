package com.master.rn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.master.bd.DespesaBD;
import com.master.ed.DespesaED;
import com.master.ed.Tipo_EventoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class DespesaRN extends Transacao {

    DespesaBD DespesaBD = null;

    public DespesaRN() {
        //Despesabd = new DespesaBD(this.sql);
    }

    //  public DespesaRN(ExecutaSQL sqlTrans) {
    //    this.sql = sql;
    //    new Transacao(sqlTrans);
    //    new DespesaBD(this.sql);
    //  }

    public DespesaED inclui(DespesaED ed) throws Excecoes {

        DespesaED DespesaED = new DespesaED();
        try {

            this.inicioTransacao();
            DespesaBD = new DespesaBD(this.sql);
            DespesaED = DespesaBD.inclui(ed);
        } finally {
            //faz o commit em cima do objeto transacao
            this.fimTransacao(true);
        }
        return DespesaED;
    }


    public void altera(DespesaED ed) throws Excecoes {

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Calendar DT_Vencimento = Calendar.getInstance();

            Date DT_Emissao = formatter.parse(ed.getDt_Emissao());
            Calendar DT_Emissao_Calendario = Calendar.getInstance();
            DT_Emissao_Calendario.setTime(DT_Emissao);

            Date DT_Hoje = new Date();
            Calendar DT_Hoje_Calendario = Calendar.getInstance();
            DT_Hoje_Calendario.setTime(DT_Hoje);

            if (String.valueOf(ed.getDt_Vencimento()) != null && !String.valueOf(ed.getDt_Vencimento()).equals("") && !String.valueOf(ed.getDt_Vencimento()).equals("null")) {
                Date Vencimento = formatter.parse(ed.getDt_Vencimento());
                DT_Vencimento.setTime(Vencimento);
                if (DT_Emissao_Calendario.after(DT_Vencimento)) {
                    Excecoes exc = new Excecoes();
                    exc.setMensagem("Data de vencimento tem de ser menor ou igual a hoje");
                    exc.setClasse(this.getClass().getName());
                    exc.setMetodo("Altera(DespesaRN)");
                    throw exc;
                }
            }

            if (DT_Emissao_Calendario.after(DT_Hoje_Calendario)) {
                Excecoes exc = new Excecoes();
                exc.setMensagem("Data emissão tem de ser menor ou igual a data de hoje.");
                exc.setClasse(this.getClass().getName());
                exc.setMetodo("Altera(DespesaRN)");
                throw exc;
            }


            //// System.out.println(" novo saldo " + VL_Saldo);

            this.inicioTransacao();

            DespesaBD = new DespesaBD(this.sql);
            DespesaBD.altera(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar");
            excecoes.setMetodo("inclui(DespesaED)");
            excecoes.setExc(e);
            this.abortaTransacao();
            throw excecoes;
        }

    }


    public void deleta(DespesaED ed) throws Excecoes {

        try {

            this.inicioTransacao();

            DespesaBD = new DespesaBD(this.sql);
            DespesaBD.deleta(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir");
            excecoes.setMetodo("inclui(DespesaED)");
            excecoes.setExc(e);
            this.abortaTransacao();
            throw excecoes;
        }

    }


    public ArrayList lista(DespesaED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new DespesaBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }



    public DespesaED getByRecord(DespesaED ed) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
        DespesaED edVolta = new DespesaED();
        //atribui ao ed de retorno
        edVolta = new DespesaBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }


    public byte[] geraDespesa_Emissao(DespesaED ed) throws Excecoes {

        //antes de invocar chamada ao relatorio deve-se
        //fazer validacoes de regra de negocio

        this.inicioTransacao();
        DespesaBD = new DespesaBD(this.sql);
        byte[] b = DespesaBD.geraDespesa_Emissao(ed);
        this.fimTransacao(false);
        return b;
    }


}
