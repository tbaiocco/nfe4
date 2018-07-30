package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Cheque_ClienteBD;
import com.master.bd.Lote_ChequeBD;
import com.master.bd.Lote_RecebimentoBD;
import com.master.bd.Movimento_Conta_CorrenteBD;
import com.master.ed.Cheque_ClienteED;
import com.master.ed.Lote_ChequeED;
import com.master.ed.Lote_RecebimentoED;
import com.master.ed.Movimento_Conta_CorrenteED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

/**
 * @author André Valadas
 * @serial Lotes Cheques
 * @serialData 16/08/2005
 */
public class Lote_ChequeRN extends Transacao {

    public Lote_ChequeRN() {
    }

    public Lote_ChequeED inclui(Lote_ChequeED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed =  new Lote_ChequeBD(this.sql).inclui(ed);
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

    public void altera(Lote_ChequeED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Lote_ChequeBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Lote_ChequeED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Lote_ChequeBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void devolucaoCheque(Lote_ChequeED ed) throws Exception {

        try {
            this.inicioTransacao();
            //*** Dados do Lote_Cheque
            Lote_ChequeED edVolta = new Lote_ChequeBD(this.sql).getByRecord(new Lote_ChequeED(null, ed.getOid_Cheque_Cliente(), ed.getOid_Lote_Recebimento()));
            //*** Dados do Cheque a ser Devolvido
            Cheque_ClienteED edCheque = new Cheque_ClienteBD(this.sql).getByRecord(new Cheque_ClienteED(edVolta.getOid_Cheque_Cliente(), false, true, false, false, false, false));
            //*** Dados do Lote Recebimento
            Lote_RecebimentoED edLote = new Lote_RecebimentoBD(this.sql).getByRecord(new Lote_RecebimentoED(edVolta.getOid_Lote_Recebimento(), false, true, false));
            edVolta.setDM_Situacao("D");
            edVolta.setOid_Motivo_Devolucao(ed.edCheque.getOid_Motivo_Devolucao());
            new Lote_ChequeBD(this.sql).altera(edVolta);
            
            //*** Devolve Cheque
            this.sql.executarUpdate(" UPDATE Cheques_Clientes SET" +
                                    "    oid_Conta_Corrente = NULL" +
                                    "   ,DT_Compensacao = NULL" +
                                    "   ,DM_Situacao = 'D'" +
                                    "   ,DM_Apresentacao = '"+ed.edCheque.getDM_Apresentacao()+"'" +
                                    "   ,oid_Motivo_Devolucao = "+ed.edCheque.getOid_Motivo_Devolucao()+
                                    " WHERE Cheques_Clientes.oid_Cheque_Cliente = "+ed.getOid_Cheque_Cliente());
           
            //*** Inclui no Conta Corrente
            Movimento_Conta_CorrenteED edMovConta = new Movimento_Conta_CorrenteED();
            edMovConta.setDT_Movimento_Conta_Corrente(Data.getDataDMY());
            edMovConta.setOid_Lote_Recebimento(edVolta.getOid_Lote_Recebimento());
            edMovConta.setOid_Conta_Corrente(edCheque.edConta_Corrente.getOid_Conta_Corrente());
            edMovConta.setOid_Conta(edCheque.edConta_Corrente.getOid_Conta().intValue());
            edMovConta.setNR_Documento(edLote.edTipoDoc.getCD_Tipo_Documento()+String.valueOf(edLote.getNR_Lote()));
            edMovConta.setNM_Complemento_Historico("Cheque Devolvido: " + edCheque.getNR_Cheque());
            edMovConta.setDM_Debito_Credito("D");
            edMovConta.setDM_Tipo_Lancamento("G");
            edMovConta.setOID_Tipo_Documento(new Integer(edLote.getOid_Tipo_Documento()));
            edMovConta.setOid_Historico(new Integer(Parametro_FixoED.getInstancia().getOID_Historico_Devolucao_Cheque()));
            edMovConta.setVL_Lancamento(new Double(edCheque.getVL_Cheque()));
            new Movimento_Conta_CorrenteBD(this.sql).inclui(edMovConta);
            
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void addChequesEntregas(Lote_ChequeED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Lote_ChequeBD(this.sql).addChequesEntregas(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    public void addChequesByData(Lote_ChequeED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Lote_ChequeBD(this.sql).addChequesByData(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public ArrayList lista(Lote_ChequeED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Lote_ChequeBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public Lote_ChequeED getByRecord(Lote_ChequeED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Lote_ChequeBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}