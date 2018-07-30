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
import java.util.Collections;

import com.master.bd.DepreciacaoBD;
import com.master.bd.Movimento_Ordem_ServicoBD;
import com.master.ed.DepreciacaoED;
import com.master.ed.Movimento_Ordem_ServicoED;
import com.master.rl.JasperRL;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import com.master.util.comparadores.CategoriaComparator;
import com.master.util.comparadores.UnidadeComparator;

public class DepreciacaoRN extends Transacao {

    public DepreciacaoRN() {
    }

    public DepreciacaoED inclui(DepreciacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new DepreciacaoBD(this.sql).inclui(ed);
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

    public void altera(DepreciacaoED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new DepreciacaoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }
    public void vender(DepreciacaoED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new DepreciacaoBD(this.sql).vender(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }


    public void gerarDepreciacao(DepreciacaoED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new DepreciacaoBD(this.sql).gerarDepreciacao(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }


    public void deleta(DepreciacaoED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            new DepreciacaoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException exc) {
            this.abortaTransacao();
            throw exc;
        }
    }

    public ArrayList lista(DepreciacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new DepreciacaoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList Depreciacao_Mes_Lista(DepreciacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new DepreciacaoBD(sql).Depreciacao_Mes_Lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList Depreciacao_Sintetico_Lista(DepreciacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new DepreciacaoBD(sql).Depreciacao_Sintetico_Lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public DepreciacaoED getByRecord(DepreciacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new DepreciacaoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public DepreciacaoED getByRecordVenda(DepreciacaoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new DepreciacaoBD(this.sql).getByRecordVenda(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void relDepreciacao(DepreciacaoED ed)
    throws Excecoes {
    	ArrayList toReturn = this.lista(ed);
    	//Vamos ordenar a putaria!
    	//Categoria
    	Collections.sort(toReturn, new CategoriaComparator());
    	//Unidade
    	Collections.sort(toReturn, new UnidadeComparator());
    	ed.setLista(toReturn);
//      System.out.println(ed.getDescFiltro());
      //*** Chama o Gerador de Relatórios Jasper
      new JasperRL(ed).geraRelatorio();
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}