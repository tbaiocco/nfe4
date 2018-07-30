package com.master.rn;

import java.util.ArrayList;

import com.master.bd.ConhecimentoBD;
import com.master.bd.Conhecimento_InternacionalBD;
import com.master.bd.Movimento_ConhecimentoBD;
import com.master.bd.Origem_DuplicataBD;
import com.master.ed.ConhecimentoED;
import com.master.ed.Conhecimento_InternacionalED;
import com.master.ed.Movimento_ConhecimentoED;
import com.master.ed.Origem_DuplicataED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

public class Origem_DuplicataRN extends Transacao {

    Origem_DuplicataBD Origem_DuplicataBD = null;

    public Origem_DuplicataRN() {
    }

    public void inclui(Origem_DuplicataED ed) throws Excecoes {

        if (ed.getOID_Conhecimento().compareTo("") == 0) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Código do Conhecimento não foi informado !!!");
            throw exc;
        }

        try {

            this.inicioTransacao();
            Origem_DuplicataBD = new Origem_DuplicataBD(this.sql);
            Origem_DuplicataBD.inclui(ed);
            this.fimTransacao(true);

        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        }
        catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir");
            excecoes.setMetodo("inclui");
            excecoes.setExc(e);
            //faz rollback pois deu algum erro
            this.abortaTransacao();

            throw excecoes;
        }
    }

    public void incluiAvulso(Origem_DuplicataED ed) throws Excecoes {

        if (ed.getOID_Conhecimento().compareTo("") == 0) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Código do Conhecimento não foi informado !!!");
            throw exc;
        }

        try {

            this.inicioTransacao();
            Origem_DuplicataBD = new Origem_DuplicataBD(this.sql);
            Origem_DuplicataBD.incluiAvulso(ed);
            this.fimTransacao(true);

        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        }
        catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir");
            excecoes.setMetodo("inclui");
            excecoes.setExc(e);
            //faz rollback pois deu algum erro
            this.abortaTransacao();

            throw excecoes;
        }
    }


    public void altera(Origem_DuplicataED ed) throws Excecoes {

        if (String.valueOf(ed.getOID_Origem_Duplicata()).compareTo("") == 0) {
            Excecoes exc = new Excecoes();
            exc.setMensagem("Código do Origem_Duplicata não foi informado !!!");
            throw exc;
        }

        try {

            this.inicioTransacao();
            Origem_DuplicataBD = new Origem_DuplicataBD(this.sql);
            Origem_DuplicataBD.altera(ed);
            this.fimTransacao(true);

        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        }
        catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar");
            excecoes.setMetodo("alterar");
            excecoes.setExc(e);
            //faz rollback pois deu algum erro
            this.abortaTransacao();

            throw excecoes;
        }
    }

    public void deleta(Origem_DuplicataED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            Origem_DuplicataBD = new Origem_DuplicataBD(this.sql);
            Origem_DuplicataBD.deleta(ed);
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


    public void estorna(Origem_DuplicataED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            Origem_DuplicataBD = new Origem_DuplicataBD(this.sql);
            Origem_DuplicataBD.estorna(ed);
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

    public void estornaTodos(Origem_DuplicataED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            Origem_DuplicataBD = new Origem_DuplicataBD(this.sql);
            Origem_DuplicataBD.estornaTodos(ed);
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


    public ArrayList lista(Origem_DuplicataED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new Origem_DuplicataBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public Origem_DuplicataED getByRecord(Origem_DuplicataED ed) throws Excecoes {

        this.inicioTransacao();
        Origem_DuplicataED edVolta = new Origem_DuplicataED();
        edVolta = new Origem_DuplicataBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);
        return edVolta;
    }

    public Origem_DuplicataED getByParcelamento(Origem_DuplicataED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new Origem_DuplicataBD(this.sql).getByParcelamento(ed);
        this.fimTransacao(false);
        return ed;
    }

    public void geraRelatorio(Origem_DuplicataED ed) throws Excecoes {

        //this.inicioTransacao();
        //Origem_DuplicataBD = new Origem_DuplicataBD(this.sql);
        //Origem_DuplicataBD.geraRelatorio(ed);
        //this.fimTransacao(false);

    }

    public void desconto(Origem_DuplicataED ed) throws Excecoes {

        try {

            this.inicioTransacao();
            Origem_DuplicataBD = new Origem_DuplicataBD(this.sql);
            Origem_DuplicataBD.desconto(ed);

            //Movido de Origem_DulicataBD.desconto(ed)
            // System.out.println("Back to RN...");
            Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();
            edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (Parametro_FixoED.getInstancia().getOID_Tipo_Movimento_Custo_Desconto ()).longValue ());
            edMovimento_Conhecimento.setVL_Movimento (ed.getVL_Desconto ());
            edMovimento_Conhecimento.setDT_Movimento_Conhecimento (Data.getDataDMY ());
            edMovimento_Conhecimento.setHR_Movimento_Conhecimento (Data.getHoraHM ());
            edMovimento_Conhecimento.setOID_Conhecimento (ed.getOID_Conhecimento ());
            edMovimento_Conhecimento.setDM_Lancado_Gerado ("D");
            edMovimento_Conhecimento.setNM_Pessoa_Entrega ("");
            edMovimento_Conhecimento.setDM_Tipo_Movimento ("D");
            edMovimento_Conhecimento.setOID_Tabela_Frete ("");
//            Movimento_ConhecimentoRN Movimento_Conhecimentorn = new Movimento_ConhecimentoRN ();
            // System.out.println("DESCONTO 40 Antes Inclui Mov Cto ->>");
            this.inclui_Movimento_Conhecimento(edMovimento_Conhecimento);

            this.fimTransacao(true);

        } catch (Excecoes exc) {
            this.abortaTransacao();
            throw exc;
        }
        catch (Exception e) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar");
            excecoes.setMetodo("alterar");
            excecoes.setExc(e);
            //faz rollback pois deu algum erro
            this.abortaTransacao();
            throw excecoes;
        }
    }

    public Movimento_ConhecimentoED inclui_Movimento_Conhecimento (Movimento_ConhecimentoED ed) throws Excecoes {

        // System.out.println ("Movimento_ConhecimentoED inclui 1 ");
        ConhecimentoBD ConhecimentoBD = null;
        Movimento_ConhecimentoBD Movimento_ConhecimentoBD = null;

        if ("".equals (ed.getOID_Conhecimento ()) && "".equals (ed.getOID_Cotacao ())) {
          throw new Excecoes ("Código do Conhecimento/Cotacao não foi informado!");
        }

        Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();

//        this.inicioTransacao ();
        try {
          movimento_ConhecimentoED = new Movimento_ConhecimentoBD (this.sql).inclui (ed);

          // System.out.println ("Movimento_ConhecimentoED inclui 2 ");

          if (!"".equals (ed.getOID_Conhecimento ())) {
            if ("I".equals (ed.getDM_Tipo_Movimento ())) {
              if ("I".equals (ed.getDM_Nacional_Internacional ())) {
                Conhecimento_InternacionalED conhecimentoED = new Conhecimento_InternacionalED ();
                conhecimentoED.setOID_Conhecimento (ed.getOID_Conhecimento ());
                new Conhecimento_InternacionalBD (this.sql).altera_Totais (conhecimentoED);
              }
              else {
                ConhecimentoED conhecimentoED = new ConhecimentoED ();
                conhecimentoED.setOID_Conhecimento (ed.getOID_Conhecimento ());
                ConhecimentoBD = new ConhecimentoBD (this.sql);
                ConhecimentoBD.altera_Totais (conhecimentoED);
              }
            }

            if (ed.getDM_Tipo_Movimento () != null &&
                (ed.getDM_Tipo_Movimento ().equals ("D") ||
                 ed.getDM_Tipo_Movimento ().equals ("R")) &&
                !ed.getDM_Lancado_Gerado ().equals ("D") && //d=desc cto
                !ed.getDM_Lancado_Gerado ().equals ("G")) {
              // System.out.println ("RN Movimento Conhecimento inclui  calc margem ");
              Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.sql);
              movimento_ConhecimentoED = Movimento_ConhecimentoBD.calcula_Margem (ed);
            }
          }

//          this.fimTransacao (true);
          return movimento_ConhecimentoED;
        }
        catch (Excecoes exc) {
//          this.abortaTransacao ();
          throw exc;
        }
        catch (RuntimeException e) {
//          abortaTransacao ();
          throw e;
        }
      }

}
