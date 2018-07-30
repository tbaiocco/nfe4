package com.master.rn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.CompromissoBD;
import com.master.ed.CompromissoED;
import com.master.ed.RelatorioED;
import com.master.ed.Tipo_EventoED;
import com.master.util.Utilitaria;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Valor;
import com.master.util.bd.Transacao;
public class CompromissoRN extends Transacao {

    public CompromissoRN() {
    }

    public CompromissoED inclui(CompromissoED ed) throws Excecoes {

        CompromissoED compromissoED = new CompromissoED();
        try {
            this.inicioTransacao();
            compromissoED = new CompromissoBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return compromissoED;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public CompromissoED inclui_parcela(CompromissoED ed) throws Excecoes, ParseException {

        CompromissoED compromissoED = new CompromissoED();
        try {
            this.inicioTransacao();
            compromissoED = new CompromissoBD(this.sql).inclui_parcela(ed);
            this.fimTransacao(true);
            return compromissoED;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public CompromissoED inclui_Cartorio(CompromissoED ed) throws Excecoes, ParseException {

        CompromissoED compromissoED = new CompromissoED();
        try {

            this.inicioTransacao();
            compromissoED = new CompromissoBD(this.sql).inclui_Cartorio(ed);
            this.fimTransacao(true);
            return compromissoED;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public CompromissoED exclui_Cartorio(CompromissoED ed) throws Excecoes, ParseException {

        CompromissoED compromissoED = new CompromissoED();
        try {

            this.inicioTransacao();
            compromissoED = new CompromissoBD(this.sql).exclui_Cartorio(ed);
            this.fimTransacao(true);
            return compromissoED;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(CompromissoED ed) throws Exception {

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Calendar DT_Vencimento = Calendar.getInstance();

            Date DT_Emissao = formatter.parse(ed.getDt_Emissao());
            Calendar DT_Emissao_Calendario = Calendar.getInstance();
            DT_Emissao_Calendario.setTime(DT_Emissao);

            Date DT_Hoje = new Date();
            Calendar DT_Hoje_Calendario = Calendar.getInstance();
            DT_Hoje_Calendario.setTime(DT_Hoje);

            if (!JavaUtil.doValida(ed.getDt_Vencimento()))
                throw new Mensagens("Data de vencimento não informada!");
            Date Vencimento = formatter.parse(ed.getDt_Vencimento());
            DT_Vencimento.setTime(Vencimento);
            if (DT_Emissao_Calendario.after(DT_Vencimento))
                throw new Mensagens("Data de vencimento tem de ser menor ou igual a hoje!");

            if (DT_Emissao_Calendario.after(DT_Hoje_Calendario))
                throw new Mensagens("Data emissão tem de ser menor ou igual a data de hoje!");
            if ((ed.getVl_Desconto_Ate_Vencimento()).doubleValue() < 0.0)
                throw new Mensagens("Valor do desconto até vencimento menor que zero!");
            if ((ed.getVl_Juro_Mora_dia()).doubleValue() < 0.0)
                throw new Mensagens("Valor do juro mora dia menor que zero!");
            if ((ed.getVl_Multa_Apos_Vencimento()).doubleValue() < 0.0)
                throw new Mensagens("Valor do multa após vencimento menor que zero!");
            if (new Utilitaria(this.sql).doExiste("Movimentos_Compromissos, Compromissos",
                                                        "Movimentos_Compromissos.oid_Compromisso = Compromissos.oid_Compromisso" +
                                                        " AND Movimentos_Compromissos.VL_Pagamento > 0" +
                                                        " AND Movimentos_Compromissos.oid_Compromisso = "+ed.getOid_Compromisso()))
            {
                throw new Mensagens("Esse compromisso ja possui Pagamentos! Não pode ser alterado!");
            }

            double vlSaldo = ed.getVl_Saldo().doubleValue();
            double vlDiferenca = 0;
            if (ed.getVl_Compromisso().doubleValue() > ed.getVL_Compromisso_Original().doubleValue())
            {
                vlDiferenca = (ed.getVl_Compromisso().doubleValue() - ed.getVL_Compromisso_Original().doubleValue());
                vlSaldo = vlSaldo + vlDiferenca;
            } else
            if (ed.getVl_Compromisso().doubleValue() < ed.getVL_Compromisso_Original().doubleValue())
            {
                vlDiferenca = (ed.getVL_Compromisso_Original().doubleValue() - ed.getVl_Compromisso().doubleValue());
                vlSaldo = vlSaldo - vlDiferenca;
            }
            ed.setVl_Saldo(new Double(Valor.round(vlSaldo,2)));
            if (ed.getVl_Saldo().doubleValue() < 0.0)
                throw new Mensagens("Valor do Saldo Menor que ZERO!");

            this.inicioTransacao();
            new CompromissoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera_pago(CompromissoED ed) throws Exception {

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Calendar DT_Vencimento = Calendar.getInstance();

            Date DT_Emissao = formatter.parse(ed.getDt_Emissao());
            Calendar DT_Emissao_Calendario = Calendar.getInstance();
            DT_Emissao_Calendario.setTime(DT_Emissao);

            Date DT_Hoje = new Date();
            Calendar DT_Hoje_Calendario = Calendar.getInstance();
            DT_Hoje_Calendario.setTime(DT_Hoje);

            if (!JavaUtil.doValida(ed.getDt_Vencimento()))
                throw new Mensagens("Data de vencimento não informada!");
            Date Vencimento = formatter.parse(ed.getDt_Vencimento());
            DT_Vencimento.setTime(Vencimento);
            if (DT_Emissao_Calendario.after(DT_Vencimento))
                throw new Mensagens("Data de vencimento tem de ser menor ou igual a hoje!");

            if (DT_Emissao_Calendario.after(DT_Hoje_Calendario))
                throw new Mensagens("Data emissão tem de ser menor ou igual a data de hoje!");
            if ((ed.getVl_Desconto_Ate_Vencimento()).doubleValue() < 0.0)
                throw new Mensagens("Valor do desconto até vencimento menor que zero!");
            if ((ed.getVl_Juro_Mora_dia()).doubleValue() < 0.0)
                throw new Mensagens("Valor do juro mora dia menor que zero!");
            if ((ed.getVl_Multa_Apos_Vencimento()).doubleValue() < 0.0)
                throw new Mensagens("Valor do multa após vencimento menor que zero!");

//            double vlSaldo = ed.getVl_Saldo().doubleValue();
//            double vlDiferenca = 0;
//            if (ed.getVl_Compromisso().doubleValue() > ed.getVL_Compromisso_Original().doubleValue())
//            {
//                vlDiferenca = (ed.getVl_Compromisso().doubleValue() - ed.getVL_Compromisso_Original().doubleValue());
//                vlSaldo = vlSaldo + vlDiferenca;
//            } else
//            if (ed.getVl_Compromisso().doubleValue() < ed.getVL_Compromisso_Original().doubleValue())
//            {
//                vlDiferenca = (ed.getVL_Compromisso_Original().doubleValue() - ed.getVl_Compromisso().doubleValue());
//                vlSaldo = vlSaldo - vlDiferenca;
//            }
//            ed.setVl_Saldo(new Double(Valor.round(vlSaldo,2)));
//            if (ed.getVl_Saldo().doubleValue() < 0.0)
//                throw new Mensagens("Valor do Saldo Menor que ZERO!");

            this.inicioTransacao();
            new CompromissoBD(this.sql).altera_pago(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void vincula(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new CompromissoBD(this.sql).vincula(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void libera_Aprova_Compromisso(CompromissoED ed, String dm_Libera_Aprova, String acao) throws Excecoes {

        try {
            this.inicioTransacao();
            new CompromissoBD(this.sql).libera_Aprova_Compromisso(ed, dm_Libera_Aprova, acao);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void troca_Conta(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new CompromissoBD(this.sql).troca_Conta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void libera_Compromisso(CompromissoED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new CompromissoBD(this.sql).libera_Compromisso(ed);
            this.fimTransacao(true);

        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void aprova_Compromisso(CompromissoED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new CompromissoBD(this.sql).aprova_Compromisso(ed);
            this.fimTransacao(true);

        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void agenda_Pagamento(CompromissoED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new CompromissoBD(this.sql).agenda_Pagamento(ed);
            this.fimTransacao(true);

        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }


    public void deleta(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new CompromissoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta_Parcelamento(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new CompromissoBD(this.sql).deleta_Parcelamento(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }


    public void deleta_Selecao_Compromissos(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new CompromissoBD(this.sql).deleta_Selecao_Compromissos(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta_Lista(ArrayList lista) throws Excecoes {

        try {
            this.inicioTransacao();
            new CompromissoBD(this.sql).deleta_Lista(lista);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta_Vinculo(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new CompromissoBD(this.sql).deleta_Vinculo(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista_Compromisso_Ordem_Servico(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(sql).lista_Compromisso_Ordem_Servico(ed);
        } finally {
            this.fimTransacao(false);
        }
    }


    public ArrayList compromisso_Parcela_Lista(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(sql).compromisso_Parcela_Lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista_Vinculado(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(sql).lista_Vinculado(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista_Parcela(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(sql).lista_Parcela(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista_Servico(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(sql).lista_Servico(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList lista_Ordem(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(sql).lista_Ordem(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public CompromissoED getByRecord(CompromissoED ed) throws Exception {

        try {
            this.inicioTransacao();
            return new CompromissoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public CompromissoED getByRecord_Com_Lote(CompromissoED ed) throws Exception {

        try {
            this.inicioTransacao();
            return new CompromissoBD(this.sql).getByRecord_Com_Lote(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public CompromissoED getByRecord_Vinculado(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(this.sql).getByRecord_Vinculado(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public CompromissoED getByRecord_Compromisso_Vinculado(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(this.sql).getByRecord_Compromisso_Vinculado(ed);
        } finally {
            this.fimTransacao(false);
        }
    }


    public byte[] geraCompromisso_Unidade(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(this.sql).geraCompromisso_Unidade(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] imprime_Agenda_Pagamento(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(this.sql).imprime_Agenda_Pagamento(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void geraCompromisso_Fornecedor(CompromissoED ed, HttpServletResponse response) throws Excecoes {


        try {
            this.inicioTransacao();

            new CompromissoBD(this.sql).geraCompromisso_Fornecedor(ed, response);

        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] geraCompromisso_Vencimento(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(this.sql).geraCompromisso_Vencimento(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] geraDiario_Auxiliar_Fornecedor(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            byte[] b = new CompromissoBD(this.sql).geraDiario_Auxiliar_Fornecedor(ed);
            this.fimTransacao(true);
            return b;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public byte[] geraCompromisso_Emissao(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(this.sql).geraCompromisso_Emissao(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] geraCompromisso_Ordem_Servico(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(this.sql).geraCompromisso_Ordem_Servico(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] geraCompromisso_Pagamento(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            if ("RJuros".equals(ed.getDM_Relatorio())){
              return new CompromissoBD(this.sql).geraCompromisso_Pagamento_Juros_Multa (ed);
            } else return new CompromissoBD(this.sql).geraCompromisso_Pagamento (ed);
        } finally {
            this.fimTransacao(false);
        }
    }


    public ArrayList GeraEDI_Movimentos_Compromissos(Tipo_EventoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(sql).GeraEDI_Movimentos_Compromissos(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList GeraEDI_Compromissos(Tipo_EventoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new CompromissoBD(sql).GeraEDI_Compromissos(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public CompromissoED inclui_provisao(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new CompromissoBD(this.sql).inclui_provisao(ed);
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

    public CompromissoED gera_parcela(CompromissoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new CompromissoBD(this.sql).gera_parcela(ed);
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

    /** ------------ RELATÓRIOS ---------------- */
    //*** Compromissos a Pagar
    public void relCompromisso(RelatorioED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new CompromissoBD(this.sql).relCompromisso(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }

    public void geraDiario_Razao_Fornecedores(CompromissoED ed, HttpServletResponse res) throws Excecoes {

        try {
            this.inicioTransacao();
            new CompromissoBD(this.sql).geraDiario_Razao_Fornecedores(ed, res);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

}
