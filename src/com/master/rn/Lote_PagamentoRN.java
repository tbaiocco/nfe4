package com.master.rn;
 
import java.util.ArrayList;

import com.master.bd.Lancamento_ContabilBD;
import com.master.ed.Lancamento_ContabilED;

import com.master.bd.Lote_PagamentoBD;
import com.master.ed.Lote_PagamentoED;
import com.master.ed.RelatorioED;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

public class Lote_PagamentoRN extends Transacao {

	Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

    public Lote_PagamentoRN() {
    }
 
    public Lote_PagamentoED inclui(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Lote_PagamentoBD(this.sql).inclui(ed);
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

    public Lote_PagamentoED substitui_Lote_Pagamento(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Lote_PagamentoBD(this.sql).substitui_Lote_Pagamento(ed);
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

    public void altera(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Lote_PagamentoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public int altera_Compensacao(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            int i = new Lote_PagamentoBD(this.sql).altera_Compensacao(ed);
            if ("S__".equals(parametro_FixoED.getDM_Gera_Lancamento_Contabil()))
            { 
            	new Lancamento_ContabilBD(this.sql).deleta_CTB_Lote_Pagamento(ed);
            	new Lancamento_ContabilBD(this.sql).inclui_CTB_Lote_Pagamento(ed);
            }
            this.fimTransacao(true);
            return i;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public int devolucao(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            int i = new Lote_PagamentoBD(this.sql).devolucao(ed);
            this.fimTransacao(true);
            return i;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public int estorna_Compensacao(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            int i = new Lote_PagamentoBD(this.sql).estorna_Compensacao(ed);
            this.fimTransacao(true);
            return i;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }


    public void deleta(Lote_PagamentoED ed) throws Excecoes {

        try {
            if ("L".equals(ed.getDM_Situacao()))
                throw new Mensagens("Lote de Pagamento Já Liberado!");
            this.inicioTransacao();
            new Lote_PagamentoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void cancela(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Lote_PagamentoBD(this.sql).cancela(ed);
            if ("S__".equals(parametro_FixoED.getDM_Gera_Lancamento_Contabil()))
            {
            	new Lancamento_ContabilBD(this.sql).deleta_CTB_Lote_Pagamento(ed);
            }
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Lote_PagamentoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Lote_PagamentoED getByRecord(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Lote_PagamentoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] geraLote_Pagamento_Emissao(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Lote_PagamentoBD(this.sql).geraLote_Pagamento_Emissao(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] geraLote_Pagamento_Unidade(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Lote_PagamentoBD(this.sql).geraLote_Pagamento_Unidade(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] geraLote_Pagamento_Nao_Compensados(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Lote_PagamentoBD(this.sql).geraLote_Pagamento_Nao_Compensados(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] geraLote_Pagamento_Programacao(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Lote_PagamentoBD(this.sql).geraLote_Pagamento_Programacao(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] geraLote_Pagamento_Compensacao(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Lote_PagamentoBD(this.sql).geraLote_Pagamento_Compensacao(ed);
        } finally {
            this.fimTransacao(false);
        }
    }    

    public byte[] imprime_Documento_Pagamento(Lote_PagamentoED ed) throws Excecoes {
    	byte[] b = null;
        try { 
            this.inicioTransacao();
            b = new Lote_PagamentoBD(this.sql).imprime_Documento_Pagamento(ed);
            if ("S__".equals(parametro_FixoED.getDM_Gera_Lancamento_Contabil()))
            {
            	new Lancamento_ContabilBD(this.sql).inclui_CTB_Lote_Pagamento(ed);
            }
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
        return b;
    }

    public String imprime_Documento_Pagamento_Matricial(Lote_PagamentoED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            String toReturn = new Lote_PagamentoBD(sql).imprime_Documento_Pagamento_Matricial(ed);
            fimTransacao(true);
            return toReturn;
        } catch (Excecoes e) {
            abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public byte[] imprime_Lote_Pagamento (Lote_PagamentoED ed) throws Excecoes {
  
      try {
        this.inicioTransacao ();
        byte[] b = new Lote_PagamentoBD (this.sql).imprime_Lote_Pagamento (ed);
        this.fimTransacao (true);
        return b;
      }
      catch (Excecoes e) {
        this.abortaTransacao ();
        throw e;
      }
      catch (RuntimeException e) {
        this.abortaTransacao ();
        throw e;
      }
    }

    public byte[] geraLote_Pagamento_Fornecedor(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Lote_PagamentoBD(this.sql).geraLote_Pagamento_Fornecedor(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public byte[] geraLote_Pagamento_Devolvido(Lote_PagamentoED ed) throws Excecoes {

        try { 
            this.inicioTransacao();
            byte[] b = new Lote_PagamentoBD(this.sql).geraLote_Pagamento_Devolvido(ed);
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
    public int altera_Programacao(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            int i = new Lote_PagamentoBD(this.sql).altera_Programacao(ed);
            this.fimTransacao(true);
            return i;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    /** ------------ RELATÓRIOS ---------------- */ 
    //*** Lotes Pagamentos
    public void relLote_Pagamento(RelatorioED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Lote_PagamentoBD(this.sql).relLote_Pagamento(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void troca_Compromisso_Lote_Pagamento(Lote_PagamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Lote_PagamentoBD(this.sql).troca_Compromisso_Lote_Pagamento(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}