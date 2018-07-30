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
import com.master.ed.RelatorioED;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

/**
 * @author André Valadas
 * @serial Lotes Recebimentos
 * @serialData 15/08/2005
 */
public class Lote_RecebimentoRN extends Transacao {

    public Lote_RecebimentoRN() {
    }

    public Lote_RecebimentoED inclui(Lote_RecebimentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed =  new Lote_RecebimentoBD(this.sql).inclui(ed);
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

    public void altera(Lote_RecebimentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Lote_RecebimentoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void cancela(Lote_RecebimentoED ed) throws Exception {

        try {
            this.inicioTransacao();
            
            //*** Cancela Lote
            new Lote_RecebimentoBD(this.sql).altera(ed);
            
            //*** Cancela Lotes Cheques
            Lote_ChequeED edLoteCheq = new Lote_ChequeED("C", 0, ed.getOid_Lote_Recebimento());
            new Lote_ChequeBD(this.sql).altera(edLoteCheq);
            
            //*** Concela Cheques do Lote
            this.sql.executarUpdate(" UPDATE Cheques_Clientes SET" +
                                    "    oid_Conta_Corrente = NULL " +
                                    "   ,DT_Compensacao = NULL " +
                                    "   ,DM_Situacao = 'N'" +
                                    " WHERE Cheques_Clientes.oid_Cheque_Cliente = Lotes_Cheques.oid_Cheque_Cliente" +
                                    "   AND Lotes_Cheques.oid_Lote_Recebimento = Lotes_Recebimentos.oid_Lote_Recebimento" +
                                    "   AND Lotes_Cheques.DM_Situacao <> 'F'" +
                                    "   AND Lotes_Recebimentos.oid_Lote_Recebimento = "+ed.getOid_Lote_Recebimento());
            
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void compensacao(Lote_RecebimentoED ed) throws Exception {

        String sql = "";
        try {
            this.inicioTransacao();
            //*** Compensa Lote
            new Lote_RecebimentoBD(this.sql).altera(ed);
            
            //*** Compensa Lotes Cheques
            Lote_ChequeED edLoteCheq = new Lote_ChequeED("F", 0, ed.getOid_Lote_Recebimento());
            new Lote_ChequeBD(this.sql).altera(edLoteCheq);
            
            //*** Busca Cheques
            ArrayList lista = new Lote_ChequeBD(this.sql).lista(edLoteCheq);
            for (int i=0; i<lista.size(); i++)
            {
                edLoteCheq = (Lote_ChequeED) lista.get(i); 
                Cheque_ClienteED edCheque = new Cheque_ClienteBD(this.sql).getByRecord(new Cheque_ClienteED(edLoteCheq.getOid_Cheque_Cliente(), false, false, false, false, false, false));
                if (!"S".equals(edCheque.getDM_Apresentacao()))
                    throw new Mensagens("Cheque bloqueado para Reapresentação!");
                
                //*** Compensa Cheques do Lote
                sql = " UPDATE Cheques_Clientes SET" +
                      "    DT_Compensacao = '"+ed.getDT_Compensacao()+"'" +
                      "   ,DM_Situacao = 'F'";
                
                //*** Verifica Datas do Cheque
                if (!JavaUtil.doValida(edCheque.getDT_Apresentacao1()))
                    sql+= "     ,DT_Apresentacao1 = '"+ed.getDT_Compensacao()+"'";
                else if (!JavaUtil.doValida(edCheque.getDT_Apresentacao2()))
                    sql+= "     ,DT_Apresentacao2 = '"+ed.getDT_Compensacao()+"'";
                else if (!JavaUtil.doValida(edCheque.getDT_Apresentacao3()))
                    sql+= "     ,DT_Apresentacao3 = '"+ed.getDT_Compensacao()+"'";
                else throw new Mensagens("Esse Cheque já foi Reapresentado pela Terceira vez!");
                
                sql +=" WHERE Cheques_Clientes.DT_Compensacao IS NULL" +
                      "   AND Cheques_Clientes.oid_Cheque_Cliente = Lotes_Cheques.oid_Cheque_Cliente" +
                      "   AND Lotes_Cheques.oid_Lote_Recebimento = Lotes_Recebimentos.oid_Lote_Recebimento" +
                      "   AND Lotes_Recebimentos.oid_Lote_Recebimento = "+ed.getOid_Lote_Recebimento();
                this.sql.executarUpdate(sql);
            }
            
            //*** Inclui no Conta Corrente
            Movimento_Conta_CorrenteED edMovConta = new Movimento_Conta_CorrenteED();
            edMovConta.setDT_Movimento_Conta_Corrente(ed.getDT_Compensacao());
            ed = new Lote_RecebimentoBD(this.sql).getByRecord(new Lote_RecebimentoED(ed.getOid_Lote_Recebimento(), true, true, false));

            edMovConta.setOid_Lote_Recebimento(ed.getOid_Lote_Recebimento());
            edMovConta.setOid_Conta_Corrente(ed.getOid_Conta_Corrente());
            edMovConta.setOid_Conta(ed.edConta_Corrente.getOid_Conta().intValue());
            edMovConta.setNR_Documento(ed.edTipoDoc.getCD_Tipo_Documento()+String.valueOf(ed.getNR_Lote()));
            edMovConta.setNM_Complemento_Historico("Lote Receb: " + ed.getNR_Lote());
            edMovConta.setDM_Debito_Credito("C");
            edMovConta.setDM_Tipo_Lancamento("G");
            edMovConta.setOID_Tipo_Documento(new Integer(ed.getOid_Tipo_Documento()));
            edMovConta.setOid_Historico(new Integer(Parametro_FixoED.getInstancia().getOID_Historico_Compensacao()));
            edMovConta.setVL_Lancamento(new Double(ed.getVL_Lote()));
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
    
    public void deleta(Lote_RecebimentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Lote_RecebimentoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Lote_RecebimentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Lote_RecebimentoBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public Lote_RecebimentoED getByRecord(Lote_RecebimentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Lote_RecebimentoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    /** ------------ RELATÓRIOS ---------------- */ 
    //*** Lotes Recebimentos
    public void relLoteRecebimento(RelatorioED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Lote_RecebimentoBD(this.sql).relLoteRecebimento(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}