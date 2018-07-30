package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Lancamento_Centro_CustoBD;
import com.master.bd.Nota_Fiscal_EletronicaBD;
import com.master.ed.Lancamento_Centro_CustoED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;

public class Lancamento_Centro_CustoRN extends Transacao {

    public Lancamento_Centro_CustoRN() {
    }

    public Lancamento_Centro_CustoED inclui(Lancamento_Centro_CustoED ed) throws Excecoes {

        try {
            //*** Valida valor Lançado
            if (ed.getVL_Lancamento() <= 0)
                throw new Mensagens("Valor de Lançamento deve ser maior que ZERO!");
            
            this.inicioTransacao();
            //*** Varifica se existe Rateio de Centro de Custos, caso não exista, inclui Tipo "N"
            if (!new BancoUtil(this.sql).doExiste("Lancamentos_Centros_Custos", "oid_Nota_Fiscal = '"+ed.getOid_Nota_Fiscal()+"' AND DM_Tipo_Lancamento = 'N'"))
            {
                Nota_Fiscal_EletronicaED edNota = new Nota_Fiscal_EletronicaBD(this.sql).getByRecord(new Nota_Fiscal_EletronicaED(ed.getOid_Nota_Fiscal()));
                Lancamento_Centro_CustoED edCustoNota = new Lancamento_Centro_CustoED();
                edCustoNota.setOid_Nota_Fiscal(ed.getOid_Nota_Fiscal());
                edCustoNota.setOid_Centro_Custo(edNota.getOid_Centro_Custo().intValue());
                edCustoNota.setVL_Lancamento(edNota.getVl_liquido_nota_fiscal());
                edCustoNota.setDM_Tipo_Lancamento("N"); //*** Seta como Tipo Principal
                new Lancamento_Centro_CustoBD(this.sql).inclui(edCustoNota);
            } else {
                if (new BancoUtil(this.sql).doExiste("Lancamentos_Centros_Custos", "oid_Nota_Fiscal = '"+ed.getOid_Nota_Fiscal()+"' AND oid_Centro_Custo = "+ed.getOid_Centro_Custo()))
                    throw new Mensagens("Já existe um Lançamento para Essa Nota Fiscal com o Centro de Custo Informado!");
            }
            
            ed = new Lancamento_Centro_CustoBD(this.sql).inclui(ed);
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

    public void altera(Lancamento_Centro_CustoED ed) throws Excecoes {

        try {
            //*** Valida valor Lançado
            if (ed.getVL_Lancamento() <= 0)
                throw new Mensagens("Valor de Lançamento deve ser maior que ZERO!");
            if (new BancoUtil(this.sql).doExiste("Lancamentos_Centros_Custos", 
                                                 "oid_Nota_Fiscal = '"+ed.getOid_Nota_Fiscal()+"'" +
                                                 " AND oid_Centro_Custo = "+ed.getOid_Centro_Custo()+
                                                 " AND oid_Lancamento_Centro_Custo <> "+ed.getOid_Lancamento_Centro_Custo()))
                throw new Mensagens("Já existe um Lançamento para Essa Nota Fiscal com o Centro de Custo Informado!");
            this.inicioTransacao();
            new Lancamento_Centro_CustoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public void deleta(Lancamento_Centro_CustoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Lancamento_Centro_CustoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista(Lancamento_Centro_CustoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Lancamento_Centro_CustoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Lancamento_Centro_CustoED getByRecord(Lancamento_Centro_CustoED ed) throws Excecoes {
        
        try {
            this.inicioTransacao();
            return new Lancamento_Centro_CustoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}