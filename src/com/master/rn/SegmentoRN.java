/*
 * Created on 15/10/2004
 */
package com.master.rn;

import java.util.ArrayList;

import com.master.bd.SegmentoBD;
import com.master.ed.SegmentoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Andre Valadas
 */
public class SegmentoRN extends Transacao {

     SegmentoBD SegmentoBD = null;

    public SegmentoRN() {

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public  SegmentoED inclui( SegmentoED ed) throws Excecoes {

         SegmentoED  SegmentoED = new  SegmentoED();

        try {

            //a chamada a este metodo da superclasse
            //iniciotransacao faz com que se abra uma conexao
            //e a deixe no pool
            this.inicioTransacao();

            //instancia objeto sql, que eh
            //uma referencia ao objeto ExecutaSQL, que por sua
            //vez possui a referencia a conexao ativa
             SegmentoBD = new  SegmentoBD(this.sql);

            //chama o inclui passando a estrutura de dados
            //como parametro
             SegmentoED =  SegmentoBD.inclui(ed);

            //faz o commit em cima do objeto transacao
            this.fimTransacao(true);
        } catch (Excecoes e) {
            throw e;
        }

        catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Segmento");
            excecoes.setMetodo("Inclui");
            excecoes.setExc(e);
            throw excecoes;
        }

        return  SegmentoED;
    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void altera( SegmentoED ed) throws Excecoes {

        try {

            this.inicioTransacao();

             SegmentoBD = new  SegmentoBD(this.sql);
             SegmentoBD.altera(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar Segmento");
            excecoes.setMetodo("altera");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public void deleta( SegmentoED ed) throws Excecoes {

        try {

            this.inicioTransacao();

             SegmentoBD = new  SegmentoBD(this.sql);
             SegmentoBD.deleta(ed);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception e) {
            this.abortaTransacao();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir  Segmento");
            excecoes.setMetodo("deleta");
            excecoes.setExc(e);
            throw excecoes;
        }

    }

    /***************************************************************************
     *  
     **************************************************************************/
    public ArrayList lista( SegmentoED ed) throws Excecoes {

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new  SegmentoBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }
   
    /***************************************************************************
     *  
     **************************************************************************/
    public SegmentoED getByOidSegmento(int oid_Segmento) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
         SegmentoED edVolta = new SegmentoED();
        //atribui ao ed de retorno
        edVolta = new  SegmentoBD(this.sql).getByOidSegmento(oid_Segmento);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }
    
    /***************************************************************************
     *  
     **************************************************************************/
    public SegmentoED getByCdSegmento(String cd_Segmento) throws Excecoes {
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
         SegmentoED edVolta = new SegmentoED();
        //atribui ao ed de retorno
        edVolta = new  SegmentoBD(this.sql).getByCdSegmento(cd_Segmento);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

    //*** RELATÓRIOS
    /*public void RelSegmento(HttpServletResponse response) throws Exception {
        //inicia conexao com bd
        this.inicioTransacao();
        //atribui ao ed de retorno
        new SegmentoBD(this.sql).RelSegmento(response);
    }*/
}