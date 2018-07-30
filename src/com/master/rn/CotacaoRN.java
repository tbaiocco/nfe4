package com.master.rn;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.CotacaoBD;
import com.master.ed.CotacaoED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

public class CotacaoRN extends Transacao {

    CotacaoBD CotacaoBD = null;
    public CotacaoRN() {
    }

    public CotacaoED inclui(CotacaoED ed) throws Excecoes {
        CotacaoED conED = new CotacaoED();
        if (!JavaUtil.doValida(ed.getOID_Pessoa())) {
            throw new Mensagens("Código do Cotacao não foi informado!");
        }
        inicioTransacao();
        try {
            CotacaoBD CotacaoBD = new CotacaoBD(sql);
            conED = CotacaoBD.inclui(ed);
            fimTransacao(true);
            return conED;
        } catch (Excecoes exc) {
            exc.printStackTrace();
            abortaTransacao();
            throw exc;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }


    public void altera(CotacaoED ed)
    throws Excecoes {
        if ("".equals(ed.getOID_Pessoa())) {
            throw new Excecoes("Pessoa não informada!");
        }
        try {
            this.inicioTransacao();
            new CotacaoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public void confirma_Cotacao(CotacaoED ed)
    throws Excecoes {
        try {
            this.inicioTransacao();
            new CotacaoBD(this.sql).confirma_Cotacao(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public CotacaoED copia_Cotacao(CotacaoED ed) throws Excecoes {
        CotacaoED conED = new CotacaoED();
        inicioTransacao();
        try {
            CotacaoBD CotacaoBD = new CotacaoBD(sql);
            conED = CotacaoBD.copia_Cotacao(ed);
            fimTransacao(true);
            return conED;
        } catch (Excecoes exc) {
            exc.printStackTrace();
            abortaTransacao();
            throw exc;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

    public void deleta(CotacaoED ed) throws Excecoes {

        if (1 == 0) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Código do Cotacao não foi informado !!!");
            throw exc;
        }

        try {

            this.inicioTransacao();

            CotacaoBD = new CotacaoBD(this.sql);
            CotacaoBD.deleta(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        }

        catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir");
            excecoes.setMetodo("excluir");
            excecoes.setExc(e);
            //faz rollback pois deu algum erro
            this.abortaTransacao();

            throw excecoes;
        }
    }

    public void altera_Situacao(CotacaoED ed) throws Excecoes {

        if (1 == 0) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Código do Cotacao não foi informado !!!");
            throw exc;
        }

        try {

            this.inicioTransacao();

            Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

            CotacaoBD = new CotacaoBD(this.sql);

            // System.out.println("altera_Situacao rn 1 ");
            CotacaoBD.altera_Situacao(ed);
            // System.out.println("altera_Situacao rn 2 ");

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        }

        catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir");
            excecoes.setMetodo("excluir");
            excecoes.setExc(e);
            //faz rollback pois deu algum erro
            this.abortaTransacao();

            throw excecoes;
        }
    }

    public ArrayList lista(CotacaoED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new CotacaoBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public ArrayList lista_Cotacao_Salva(CotacaoED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new CotacaoBD(sql).lista_Cotacao_Salva(ed);
        this.fimTransacao(false);
        return lista;
    }

    public CotacaoED consulta_Cotacao_Salva(CotacaoED ed) throws Excecoes {
        CotacaoED edVolta = new CotacaoED();
        try {
            this.inicioTransacao();
            edVolta = new CotacaoBD(this.sql).consulta_Cotacao_Salva(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
        return edVolta;
    }

    public CotacaoED getByRecord(CotacaoED ed) throws Excecoes {
        CotacaoED edVolta = new CotacaoED();
        try {
            this.inicioTransacao();
            edVolta = new CotacaoBD(this.sql).getByRecord(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
        return edVolta;
    }

    public byte[] geraRelCotacao(CotacaoED ed) throws Excecoes {

        //antes de invocar chamada ao relatorio deve-se
        //fazer validacoes de regra de negocio

        this.inicioTransacao();
        CotacaoBD = new CotacaoBD(this.sql);
        byte[] b = CotacaoBD.geraRelCotacao(ed);
        this.fimTransacao(false);
        return b;
    }

    public CotacaoED salva_Cotacao(CotacaoED ed) throws Excecoes {
        CotacaoED edVolta = new CotacaoED();
        try {
            this.inicioTransacao();
            edVolta = new CotacaoBD(this.sql).salva_Cotacao(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
        return edVolta;
    }


    public byte[] imprime_Cotacao(CotacaoED ed) throws Excecoes {

        byte[] b = null;

        try {
            this.inicioTransacao();
            CotacaoBD = new CotacaoBD(this.sql);
            b = CotacaoBD.imprime_Cotacao(ed);
            this.fimTransacao(true);
        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        }

        catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao Imp Cotacao");
            excecoes.setMetodo("imprime_Cotacao");
            excecoes.setExc(e);
            //faz rollback pois deu algum erro
            this.abortaTransacao();

            throw excecoes;
        }

        return b;

    }


    public byte[] imprime_Cotacao2(CotacaoED ed) throws Excecoes {
        inicioTransacao();
        try {
            byte[] b = new CotacaoBD(this.sql).imprime_Cotacao(ed);
            fimTransacao(true);
            return b;
        } catch (Excecoes e) {
            abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
        }
    }

}
