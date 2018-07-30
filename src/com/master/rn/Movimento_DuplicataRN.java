package com.master.rn;

import java.util.ArrayList;

import com.master.bd.DuplicataBD;
import com.master.bd.Movimento_DuplicataBD;
import com.master.ed.DuplicataED;
import com.master.ed.Movimento_DuplicataED;
import com.master.ed.Movimento_DuplicataPesquisaED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

public class Movimento_DuplicataRN extends Transacao {

    Movimento_DuplicataBD movimento_DuplicataBD = null;
    Parametro_FixoED parametro_FixoED = Parametro_FixoED.getInstancia();

    public Movimento_DuplicataRN() { }

    public Movimento_DuplicataRN(ExecutaSQL sql) {
        this.sql = sql;
    }

    public Movimento_DuplicataED inclui(Movimento_DuplicataED ed, ExecutaSQL executasql) throws Excecoes {

        ed = this.incluiMovimento(ed, executasql);
        return ed;
    }

    public Movimento_DuplicataED inclui(Movimento_DuplicataED ed) throws Excecoes {


        try {
            this.inicioTransacao();
            ed = this.incluiMovimento(ed, sql);
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes exc) {
            exc.printStackTrace();
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            exc.printStackTrace();
            this.abortaTransacao();
            throw exc;
        }
    }

    public Movimento_DuplicataED incluiMovimento(Movimento_DuplicataED ed, ExecutaSQL executasql) throws Excecoes {

        Movimento_DuplicataED movimento_DuplicataED = new Movimento_DuplicataED();

        try {
            movimento_DuplicataBD = new Movimento_DuplicataBD(executasql);

        // System.out.println("Cto p/Liquidar NR->> " + ed.getOid_Conhecimento());

            ed.setNR_Sequencia_Duplicata(1);
            if (ed.getVL_Tarifa() > 0) {
                if (ed.getVL_Pago() > 0 || ed.getOID_Tipo_Instrucao() == null)
                    ed.setOID_Tipo_Instrucao(new Integer(parametro_FixoED.getOID_Tipo_Instrucao_Tarifa()));
                ed.setVL_Credito(new Double(ed.getVL_Tarifa()));
                ed.setVL_Debito(new Double(0.0));
                ed.setNR_Sequencia_Duplicata(1);
                ed.setDM_Principal("N");
                movimento_DuplicataED = movimento_DuplicataBD.inclui(ed, executasql);
            }
            if (ed.getVL_Juros() > 0) {
                ed.setOID_Tipo_Instrucao(new Integer(parametro_FixoED.getOID_Tipo_Instrucao_Juros()));
                ed.setVL_Credito(new Double(ed.getVL_Juros()));
                ed.setVL_Debito(new Double(0.0));
                ed.setNR_Sequencia_Duplicata(2);
                ed.setDM_Principal("N");
                movimento_DuplicataED = movimento_DuplicataBD.inclui(ed, executasql);
            }
            if (ed.getVL_Desconto() > 0) {
                ed.setOID_Tipo_Instrucao(new Integer(parametro_FixoED.getOID_Tipo_Instrucao_Desconto()));
                ed.setVL_Credito(new Double(0.0));
                ed.setVL_Debito(new Double(ed.getVL_Desconto()));
                ed.setNR_Sequencia_Duplicata(3);
                ed.setDM_Principal("N");
                movimento_DuplicataED = movimento_DuplicataBD.inclui(ed, executasql);
            }

            if (ed.getVL_Reembolso() > 0) {
                ed.setOID_Tipo_Instrucao(new Integer(parametro_FixoED.getOID_Tipo_Instrucao_Valor_Reembolso()));
                ed.setVL_Credito(new Double(0.0));
                ed.setVL_Debito(new Double(ed.getVL_Reembolso()));
                ed.setNR_Sequencia_Duplicata(4);
                ed.setDM_Principal("N");
                movimento_DuplicataED = movimento_DuplicataBD.inclui(ed, executasql);
            }

            if (ed.getVL_Juros_Reembolso() > 0) {
                ed.setOID_Tipo_Instrucao(new Integer(parametro_FixoED.getOID_Tipo_Instrucao_Juros_Reembolso()));
                ed.setVL_Credito(new Double(0.0));
                ed.setVL_Debito(new Double(ed.getVL_Juros_Reembolso()));
                ed.setNR_Sequencia_Duplicata(5);
                ed.setDM_Principal("N");
                movimento_DuplicataED = movimento_DuplicataBD.inclui(ed, executasql);
            }
            /*
             * if (ed.getVL_Cambial_Ativo() > 0) { movimento_DuplicataBD = new
             * Movimento_DuplicataBD(this.sql);
             *
             * if (ed.getVL_Cambial_Ativo() > 0) ed.setOID_Tipo_Instrucao(new
             * Integer(parametro_FixoED.getOID_Tipo_Instrucao_Variacao_Cambial_Ativa()));
             * ed.setVL_Credito(new Double(ed.getVL_Cambial_Ativo()));
             * ed.setVL_Debito(new Double(0.0));
             * ed.setNR_Sequencia_Duplicata(6); movimento_DuplicataED =
             * movimento_DuplicataBD.inclui(ed); } if
             * (ed.getVL_Cambial_Passivo() > 0) { movimento_DuplicataBD = new
             * Movimento_DuplicataBD(this.sql);
             *
             * if (ed.getVL_Cambial_Passivo() > 0) ed.setOID_Tipo_Instrucao(new
             * Integer(parametro_FixoED.getOID_Tipo_Instrucao_Variacao_Cambial_Passiva()));
             * ed.setVL_Credito(new Double(0.0)); ed.setVL_Debito(new
             * Double(ed.getVL_Cambial_Passivo()));
             * ed.setNR_Sequencia_Duplicata(7); movimento_DuplicataED =
             * movimento_DuplicataBD.inclui(ed); }
             */

            if (ed.getVL_Taxa() > 0) {
                if (ed.getVL_Pago() > 0 || ed.getOID_Tipo_Instrucao() == null)
                    ed.setOID_Tipo_Instrucao(new Integer(parametro_FixoED.getOID_Tipo_Instrucao_Taxa_Cobranca()));
                ed.setVL_Credito(new Double(ed.getVL_Taxa()));
                ed.setVL_Debito(new Double(0.0));
                ed.setNR_Sequencia_Duplicata(8);
                ed.setDM_Principal("N");
                movimento_DuplicataED = movimento_DuplicataBD.inclui(ed, executasql);
            }

            if (ed.getVL_Pago() > 0) {

        // System.out.println("Cto p/Liquidar iu->> " + ed.getOid_Conhecimento());

                ed.setOID_Tipo_Instrucao(new Integer(parametro_FixoED.getOID_Tipo_Instrucao_Pago_Total()));
                if ("C".equals(ed.getDM_Tipo_Pagamento()))
                    ed.setOID_Tipo_Instrucao(new Integer(parametro_FixoED.getOID_Tipo_Instrucao_Pago_Cartorio()));
                if (ed.getVL_Saldo() > 0)
                    ed.setOID_Tipo_Instrucao(new Integer(parametro_FixoED.getOID_Tipo_Instrucao_Pago_Parcial()));

                ed.setVL_Credito(new Double(ed.getVL_Pago()));
                ed.setVL_Debito(new Double(0.0));
                ed.setNR_Sequencia_Duplicata(9);
                ed.setDM_Principal("S");
                movimento_DuplicataED = movimento_DuplicataBD.inclui(ed, executasql);

                DuplicataED edComp = new DuplicataED();
                edComp.setVl_Saldo(new Double(ed.getVL_Saldo()));

                edComp.setOid_Duplicata(ed.getOid_Duplicata());
                this.subtraiSaldo(edComp, executasql);
            }

            if (ed.getVL_Lancamento() != null && ed.getVL_Lancamento().doubleValue() > 0 && ed.getVL_Pago() == 0) {

                if ("D".equals(ed.getDM_Gera_Movimento())) {
                    ed.setVL_Debito(ed.getVL_Lancamento());
                    ed.setVL_Credito(new Double(0.0));
                    ed.setVL_Saldo(ed.getVL_Saldo() - ed.getVL_Debito().doubleValue());
                }
                if ("C".equals(ed.getDM_Gera_Movimento())) {
                    ed.setVL_Credito(ed.getVL_Lancamento());
                    ed.setVL_Debito(new Double(0.0));
                    ed.setVL_Saldo(ed.getVL_Saldo() + ed.getVL_Credito().doubleValue());

                }
                ed.setNR_Sequencia_Duplicata(2);
                ed.setDM_Principal("N");
                movimento_DuplicataED = movimento_DuplicataBD.inclui(ed, executasql);

                DuplicataED edComp = new DuplicataED();
                edComp.setVl_Saldo(new Double(ed.getVL_Saldo()));

                edComp.setOid_Duplicata(ed.getOid_Duplicata());
                this.subtraiSaldo(edComp, executasql);
            }

            // Devolucao de notas fiscais de venda
            if (ed.getVL_Devolucao_Nota_Fiscal() > 0) {
                ed.setOID_Tipo_Instrucao(new Integer(parametro_FixoED.getOID_Tipo_Instrucao_Devolucao_Nota_Fiscal()));
                ed.setVL_Credito(new Double(ed.getVL_Devolucao_Nota_Fiscal()));
                ed.setVL_Debito(new Double(0.0));
                ed.setNR_Sequencia_Duplicata(10);
                ed.setDM_Principal("N");
                movimento_DuplicataED = movimento_DuplicataBD.inclui(ed, executasql);

                DuplicataED edComp = new DuplicataED();
                edComp.setVl_Saldo(new Double(0));
                edComp.setOid_Duplicata(ed.getOid_Duplicata());
                this.subtraiSaldo(edComp, executasql);
            }

            if (ed.getVL_Imposto_Retido1() > 0) {
                ed.setOID_Tipo_Instrucao(new Integer(parametro_FixoED.getOID_Tipo_Instrucao_Imposto_Retido1()));
                ed.setVL_Debito(new Double(0.0));
                ed.setVL_Credito(new Double(ed.getVL_Imposto_Retido1()));
                ed.setNR_Sequencia_Duplicata(11);
                ed.setDM_Principal("N");
                movimento_DuplicataED = movimento_DuplicataBD.inclui(ed, executasql);
            }
            if (ed.getVL_Imposto_Retido2() > 0) {
                ed.setOID_Tipo_Instrucao(new Integer(parametro_FixoED.getOID_Tipo_Instrucao_Imposto_Retido2()));
                ed.setVL_Debito(new Double(0.0));
                ed.setVL_Credito(new Double(ed.getVL_Imposto_Retido2()));
                ed.setNR_Sequencia_Duplicata(12);
                ed.setDM_Principal("N");
                movimento_DuplicataED = movimento_DuplicataBD.inclui(ed, executasql);
            }


            if (ed.getOID_Carteira_Troca() != null && ed.getOID_Carteira_Troca().intValue() > 0) {

                DuplicataED edComp = new DuplicataED();
                edComp.setOid_Carteira(ed.getOID_Carteira_Troca());
                edComp.setOid_Duplicata(ed.getOid_Duplicata());
                this.alteraCarteira(edComp);
            }

        } catch (Excecoes exc) {
            exc.printStackTrace();
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            exc.printStackTrace();
            this.abortaTransacao();
            throw exc;
        } catch (Exception exc) {
            exc.printStackTrace();
            this.abortaTransacao();
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
        return movimento_DuplicataED;
    }

    public void confere(Movimento_DuplicataPesquisaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            movimento_DuplicataBD = new Movimento_DuplicataBD(this.sql);
            movimento_DuplicataBD.confere(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void altera(Movimento_DuplicataED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            movimento_DuplicataBD = new Movimento_DuplicataBD(this.sql);
            movimento_DuplicataBD.altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void estorna_Movimento(Movimento_DuplicataED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            movimento_DuplicataBD = new Movimento_DuplicataBD(this.sql);
            movimento_DuplicataBD.estorna_Movimento(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }


    public void deleta(Movimento_DuplicataED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            movimento_DuplicataBD = new Movimento_DuplicataBD(this.sql);
            movimento_DuplicataBD.deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista(Movimento_DuplicataED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Movimento_DuplicataBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Movimento_DuplicataED getByRecord(Movimento_DuplicataED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Movimento_DuplicataBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Movimento_DuplicataED consultaMovimento(long oid_Duplicata) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Movimento_DuplicataBD(this.sql).consultaMovimento(oid_Duplicata);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void geraRelatorio(Movimento_DuplicataED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            movimento_DuplicataBD = new Movimento_DuplicataBD(this.sql);
            movimento_DuplicataBD.geraRelatorio(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void subtraiSaldo(DuplicataED ed, ExecutaSQL executasql) throws Excecoes {

        DuplicataBD duplicataBD = new DuplicataBD(executasql);
        duplicataBD.subtraiSaldo(ed, executasql);
    }

    public void alteraCarteira(DuplicataED ed) throws Excecoes {

        DuplicataBD duplicataBD = new DuplicataBD(this.sql);
        //duplicataBD.alteraCarteira(ed);
    }
}