package com.master.rn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.master.bd.CompromissoBD;
import com.master.bd.Movimento_CompromissoBD;
import com.master.ed.CompromissoED;
import com.master.ed.Movimento_CompromissoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Movimento_CompromissoRN extends Transacao {

    Movimento_CompromissoBD movimento_CompromissoBD = null;

    public Movimento_CompromissoRN() {
    }

    public Movimento_CompromissoED inclui(Movimento_CompromissoED ed) throws Excecoes {

        Movimento_CompromissoED movimento_CompromissoED = new Movimento_CompromissoED();

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
                exc.setMetodo("Inclui(Movimento_CompromissoED)");
                throw exc;
            }

            // data de pagamento tem de ser maior ou igual data de emissao
            Date dataEmissao = formatter.parse(ed.getDt_Emissao());
            Calendar calDtEmissao = Calendar.getInstance();
            calDtEmissao.setTime(dataEmissao);

            if (calDtEmissao.after(calDtPgto)) {
                Excecoes exc = new Excecoes();
                exc.setMensagem("Data do Pagamento tem de ser maior ou igual a data de emissao");
                exc.setClasse(this.getClass().getName());
                exc.setMetodo("Inclui(Movimento_CompromissoED)");
                throw exc;
            }

            double valPgto = ed.getVl_Pagamento().doubleValue();
            double valComp = ed.getVl_Compromisso().doubleValue();

            if (valPgto > valComp) {
                Excecoes exc = new Excecoes();
                exc.setMensagem("Valor do pagamento não pode ser maior que o valor devido");
                exc.setClasse(this.getClass().getName());
                exc.setMetodo("Inclui(Movimento_CompromissoED)");
                throw exc;
            }

            if ((ed.getVl_Pagamento()).doubleValue() <= 0.0) {
                Excecoes exc = new Excecoes();
                exc.setMensagem("Valor do pagamento menor ou igual a zero.");
                exc.setClasse(this.getClass().getName());
                exc.setMetodo("Inclui(Movimento_CompromissoED)");
                throw exc;
            }

            if ((ed.getVl_Desconto()).doubleValue() < 0.0) {
                Excecoes exc = new Excecoes();
                exc.setMensagem("Valor do desconto menor ou igual a zero.");
                exc.setClasse(this.getClass().getName());
                exc.setMetodo("Inclui(Movimento_CompromissoED)");
                throw exc;
            }

            if ((ed.getVl_Multa_Pagamento()).doubleValue() < 0.0) {
                Excecoes exc = new Excecoes();
                exc.setMensagem("Valor da multa menor ou igual a zero.");
                exc.setClasse(this.getClass().getName());
                exc.setMetodo("Inclui(Movimento_CompromissoED)");
                throw exc;
            }

            if ((ed.getVl_Outras_Despesas()).doubleValue() < 0.0) {
                Excecoes exc = new Excecoes();
                exc.setMensagem("Valor de outras despesas menor ou igual a zero.");
                exc.setClasse(this.getClass().getName());
                exc.setMetodo("Inclui(Movimento_CompromissoED)");
                throw exc;
            }

            // a chamada a este metodo da superclasse
            // iniciotransacao faz com que se abra uma conexao
            // e a deixe no pool
            this.inicioTransacao();

            // instancia objeto sql, que eh
            // uma referencia ao objeto ExecutaSQL, que por sua
            // vez possui a referencia a conexao ativa
            movimento_CompromissoBD = new Movimento_CompromissoBD(this.sql);

            // chama o inclui passando a estrutura de dados
            // como parametro
            movimento_CompromissoED = movimento_CompromissoBD.inclui(ed);

            // altera o valor do saldo em compromissos

            double dValSub = ed.getVl_Pagamento().doubleValue();
            double dValComp = ed.getVl_Compromisso().doubleValue();
            CompromissoED edComp = new CompromissoED();
            edComp.setVl_Saldo(new Double(dValComp - dValSub));
            edComp.setOid_Compromisso(ed.getOid_Compromisso());
            this.subtraiSaldo(edComp);

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
            excecoes.setMetodo("inclui(Movimento_CompromissoED)");
            excecoes.setExc(e);
            this.abortaTransacao();
            throw excecoes;

        }

        return movimento_CompromissoED;

    }

    public void altera(Movimento_CompromissoED ed) throws Excecoes {

        try {

            this.inicioTransacao();

            movimento_CompromissoBD = new Movimento_CompromissoBD(this.sql);
            movimento_CompromissoBD.altera(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar");
            excecoes.setMetodo("inclui(Movimento_CompromissoED)");
            excecoes.setExc(e);
            this.abortaTransacao();
            throw excecoes;
        }

    }

    public void deleta(Movimento_CompromissoED ed) throws Excecoes {

        try {

            this.inicioTransacao();

            movimento_CompromissoBD = new Movimento_CompromissoBD(this.sql);
            movimento_CompromissoBD.deleta(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir");
            excecoes.setMetodo("inclui(Movimento_CompromissoED)");
            excecoes.setExc(e);
            this.abortaTransacao();
            throw excecoes;
        }

    }

    public ArrayList lista(Movimento_CompromissoED ed) throws Excecoes {

        // retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new Movimento_CompromissoBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public Movimento_CompromissoED getByRecord(Movimento_CompromissoED ed) throws Excecoes {
        // inicia conexao com bd
        this.inicioTransacao();
        // instancia ed de retorno
        Movimento_CompromissoED edVolta = new Movimento_CompromissoED();
        // atribui ao ed de retorno
        edVolta = new Movimento_CompromissoBD(this.sql).getByRecord(ed);
        // libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

    public void subtraiSaldo(CompromissoED ed) throws Excecoes {

        CompromissoBD compromissoBD = new CompromissoBD(this.sql);
        compromissoBD.subtraiSaldo(ed);

    }

    public Movimento_CompromissoED liquidaCompromissos(Movimento_CompromissoED ed) throws Excecoes {

        Movimento_CompromissoED movimento_CompromissoED = new Movimento_CompromissoED();

        try {
            this.inicioTransacao();
            movimento_CompromissoBD = new Movimento_CompromissoBD(this.sql);
            movimento_CompromissoED = movimento_CompromissoBD.liquidaCompromissos(ed);
            this.fimTransacao(true);

        }
        catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao liquidaCompromissos");
            excecoes.setMetodo("inclui(liquidaCompromissos)");
            excecoes.setExc(e);
            this.abortaTransacao();
            throw excecoes;
        }
        return movimento_CompromissoED;
    }
}