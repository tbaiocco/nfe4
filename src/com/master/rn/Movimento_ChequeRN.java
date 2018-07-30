package com.master.rn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.master.bd.Movimento_ChequeBD;
import com.master.ed.Movimento_ChequeED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Movimento_ChequeRN extends Transacao {

    Movimento_ChequeBD movimento_ChequeBD = null;

    public Movimento_ChequeRN() {
    }

    public Movimento_ChequeED inclui(Movimento_ChequeED ed) throws Excecoes {

        Movimento_ChequeED movimento_ChequeED = new Movimento_ChequeED();

        try {

            // Faz validações de regra de negócio
            // Data pagamento tem de ser menor ou igual a hoje
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPagamento = formatter.parse(ed.getDt_Pagamento());
            Calendar calDtPgto = Calendar.getInstance();
            calDtPgto.setTime(dataPagamento);

            Date dataHoje = new Date();
            Calendar calDataHoje = Calendar.getInstance();
            calDataHoje.setTime(dataHoje);

            if (calDtPgto.after(calDataHoje)) {
                Excecoes exc = new Excecoes();
                exc.setMensagem("Data do Pagamento tem de ser menor ou igual a hoje");
                exc.setClasse(this.getClass().getName());
                exc.setMetodo("Inclui(Movimento_ChequeED)");
                throw exc;
            }


            double valPgto = ed.getVl_Pagamento().doubleValue();
            double valComp = ed.getVl_Saldo();

            if (valPgto > valComp) {
                Excecoes exc = new Excecoes();
                exc.setMensagem("Valor do pagamento não pode ser maior que o valor devido");
                exc.setClasse(this.getClass().getName());
                exc.setMetodo("Inclui(Movimento_ChequeED)");
                throw exc;
            }

            if ((ed.getVl_Pagamento()).doubleValue() <= 0.0) {
                Excecoes exc = new Excecoes();
                exc.setMensagem("Valor do pagamento menor ou igual a zero.");
                exc.setClasse(this.getClass().getName());
                exc.setMetodo("Inclui(Movimento_ChequeED)");
                throw exc;
            }

            if ((ed.getVl_Desconto()).doubleValue() < 0.0) {
                Excecoes exc = new Excecoes();
                exc.setMensagem("Valor do desconto menor ou igual a zero.");
                exc.setClasse(this.getClass().getName());
                exc.setMetodo("Inclui(Movimento_ChequeED)");
                throw exc;
            }

            if ((ed.getVl_Multa_Pagamento()).doubleValue() < 0.0) {
                Excecoes exc = new Excecoes();
                exc.setMensagem("Valor da multa menor ou igual a zero.");
                exc.setClasse(this.getClass().getName());
                exc.setMetodo("Inclui(Movimento_ChequeED)");
                throw exc;
            }

            if ((ed.getVl_Outras_Despesas()).doubleValue() < 0.0) {
                Excecoes exc = new Excecoes();
                exc.setMensagem("Valor de outras despesas menor ou igual a zero.");
                exc.setClasse(this.getClass().getName());
                exc.setMetodo("Inclui(Movimento_ChequeED)");
                throw exc;
            }

            // a chamada a este metodo da superclasse
            // iniciotransacao faz com que se abra uma conexao
            // e a deixe no pool
            this.inicioTransacao();

            // instancia objeto sql, que eh
            // uma referencia ao objeto ExecutaSQL, que por sua
            // vez possui a referencia a conexao ativa
            movimento_ChequeBD = new Movimento_ChequeBD(this.sql);

            // chama o inclui passando a estrutura de dados
            // como parametro
            movimento_ChequeED = movimento_ChequeBD.inclui(ed);
            // faz o commit em cima do objeto transacao
            this.fimTransacao(true);

        }

        catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir");
            excecoes.setMetodo("inclui(Movimento_ChequeED)");
            excecoes.setExc(e);
            this.abortaTransacao();
            throw excecoes;

        }

        return movimento_ChequeED;

    }

    public void altera(Movimento_ChequeED ed) throws Excecoes {

        try {

            this.inicioTransacao();

            movimento_ChequeBD = new Movimento_ChequeBD(this.sql);
            movimento_ChequeBD.altera(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar");
            excecoes.setMetodo("inclui(Movimento_ChequeED)");
            excecoes.setExc(e);
            this.abortaTransacao();
            throw excecoes;
        }

    }

    public void deleta(Movimento_ChequeED ed) throws Excecoes {

        try {

            this.inicioTransacao();

            movimento_ChequeBD = new Movimento_ChequeBD(this.sql);
            movimento_ChequeBD.deleta(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir");
            excecoes.setMetodo("inclui(Movimento_ChequeED)");
            excecoes.setExc(e);
            this.abortaTransacao();
            throw excecoes;
        }

    }

    public ArrayList lista(Movimento_ChequeED ed) throws Excecoes {

        // retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new Movimento_ChequeBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public Movimento_ChequeED getByRecord(Movimento_ChequeED ed) throws Excecoes {
        // inicia conexao com bd
        this.inicioTransacao();
        // instancia ed de retorno
        Movimento_ChequeED edVolta = new Movimento_ChequeED();
        // atribui ao ed de retorno
        edVolta = new Movimento_ChequeBD(this.sql).getByRecord(ed);
        // libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }


}