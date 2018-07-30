package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Origem_DuplicataED;
import com.master.rn.Origem_DuplicataRN;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.Data;

public class fat002Bean  {

  Utilitaria util = new Utilitaria();

    public void inclui(HttpServletRequest request) throws Excecoes {

        try {
            Origem_DuplicataRN Origem_Duplicatarn = new Origem_DuplicataRN();
            Origem_DuplicataED ed = new Origem_DuplicataED();

            //request em cima dos campos dos forms html

            ed.setDT_Origem_Duplicata(request.getParameter("FT_DT_Origem_Duplicata"));
            ed.setHR_Origem_Duplicata(request.getParameter("FT_HR_Origem_Duplicata"));
            ed.setOID_Duplicata(request.getParameter("oid_Duplicata"));
            ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));

            String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");

            if (String.valueOf(nr_Conhecimento) != null && !String.valueOf(nr_Conhecimento).equals("") && !String.valueOf(nr_Conhecimento).equals("null")) {
                ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
            }
            String TX_Observacao = request.getParameter("FT_TX_Observacao");
            if (String.valueOf(TX_Observacao) != null && !String.valueOf(TX_Observacao).equals("") && !String.valueOf(TX_Observacao).equals("null")) {
                ed.setTX_Observacao(TX_Observacao);
            }

            Origem_Duplicatarn.inclui(ed);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao incluir");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void incluiAvulso(HttpServletRequest request) throws Excecoes {

        try {
            Origem_DuplicataRN Origem_Duplicatarn = new Origem_DuplicataRN();
            Origem_DuplicataED ed = new Origem_DuplicataED();

            //request em cima dos campos dos forms html

            ed.setOID_Duplicata(request.getParameter("oid_Duplicata"));
            ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));


            ed.setDT_Origem_Duplicata(Data.getDataDMY());
            ed.setHR_Origem_Duplicata(Data.getHoraHM());
            String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");

            if (String.valueOf(nr_Conhecimento) != null && !String.valueOf(nr_Conhecimento).equals("") && !String.valueOf(nr_Conhecimento).equals("null")) {
                ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
            }
            ed.setTX_Observacao("Avulso");
            ed.setDM_Situacao("I");


            Origem_Duplicatarn.incluiAvulso(ed);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao incluir");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }


    public void altera(HttpServletRequest request) throws Excecoes {

        try {
            Origem_DuplicataRN Origem_Duplicatarn = new Origem_DuplicataRN();
            Origem_DuplicataED ed = new Origem_DuplicataED();

            ed.setOID_Origem_Duplicata(request.getParameter("oid_Origem_Duplicata"));
            ed.setOID_Duplicata(request.getParameter("oid_Duplicata"));
            ed.setDT_Origem_Duplicata(request.getParameter("FT_DT_Origem_Duplicata"));
            ed.setHR_Origem_Duplicata(request.getParameter("FT_HR_Origem_Duplicata"));
            String TX_Observacao = request.getParameter("FT_TX_Observacao");
            if (String.valueOf(TX_Observacao) != null && !String.valueOf(TX_Observacao).equals("") && !String.valueOf(TX_Observacao).equals("null")) {
                ed.setTX_Observacao(TX_Observacao);
            }

            Origem_Duplicatarn.altera(ed);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao alterar");
            excecoes.setMetodo("alterar");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void estorna(HttpServletRequest request) throws Excecoes {

        try {

        	Origem_DuplicataRN Origem_Duplicatarn = new Origem_DuplicataRN();
            Origem_DuplicataED ed = new Origem_DuplicataED();

            ed.setOID_Origem_Duplicata(request.getParameter("oid_Origem_Duplicata"));
            ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
            ed.setOID_Conhecimento_Faturamento(request.getParameter("oid_Conhecimento_Faturamento"));
            ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
            String TX_Observacao = request.getParameter("FT_TX_Observacao");
            if (String.valueOf(TX_Observacao) != null && !String.valueOf(TX_Observacao).equals("") && !String.valueOf(TX_Observacao).equals("null")) {
                ed.setTX_Observacao(TX_Observacao);
            }
            String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
            if (util.doValida(oid_Nota_Fiscal))
                ed.setOid_Nota_Fiscal(oid_Nota_Fiscal);

            Origem_Duplicatarn.estorna(ed);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao excluir");
            excecoes.setMetodo("excluir");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void estornaTodos(HttpServletRequest request) throws Excecoes {

        try {
            Origem_DuplicataRN Origem_Duplicatarn = new Origem_DuplicataRN();
            Origem_DuplicataED ed = new Origem_DuplicataED();

            ed.setOID_Duplicata(request.getParameter("oid_Duplicata"));

            Origem_Duplicatarn.estornaTodos(ed);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao excluir");
            excecoes.setMetodo("excluir");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }


    public void deleta(HttpServletRequest request) throws Excecoes {

        try {
            Origem_DuplicataRN Origem_Duplicatarn = new Origem_DuplicataRN();
            Origem_DuplicataED ed = new Origem_DuplicataED();

            ed.setOID_Duplicata(request.getParameter("oid_Duplicata"));
            ed.setOID_Origem_Duplicata(request.getParameter("oid_Origem_Duplicata"));
            ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
            ed.setOID_Conhecimento_Faturamento(request.getParameter("oid_Conhecimento_Faturamento"));
            ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
            String TX_Observacao = request.getParameter("FT_TX_Observacao");
            if (String.valueOf(TX_Observacao) != null && !String.valueOf(TX_Observacao).equals("") && !String.valueOf(TX_Observacao).equals("null")) {
                ed.setTX_Observacao(TX_Observacao);
            }

            Origem_Duplicatarn.deleta(ed);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao excluir");
            excecoes.setMetodo("excluir");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public ArrayList Origem_Duplicata_Lista(HttpServletRequest request) throws Excecoes {

        Origem_DuplicataED ed = new Origem_DuplicataED();

        String OID_Duplicata = request.getParameter("oid_Duplicata");
        if (String.valueOf(OID_Duplicata) != null && !String.valueOf(OID_Duplicata).equals("") && !String.valueOf(OID_Duplicata).equals("null")) {
            ed.setOID_Duplicata(request.getParameter("oid_Duplicata"));
        }
        ed.setDT_Origem_Duplicata(request.getParameter("FT_DT_Origem_Duplicata"));

        String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");

        if (String.valueOf(nr_Conhecimento) != null && !String.valueOf(nr_Conhecimento).equals("") && !String.valueOf(nr_Conhecimento).equals("null")) {
            ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
        }
        ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
        ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
        String oid_Unidade = request.getParameter("oid_Unidade");
        if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("") && !String.valueOf(oid_Unidade).equals("null")) {
            ed.setOID_Unidade(new Long(oid_Unidade).longValue());
        }

        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        if (util.doValida(oid_Nota_Fiscal))
            ed.setOid_Nota_Fiscal(oid_Nota_Fiscal);

        return new Origem_DuplicataRN().lista(ed);

    }

    public Origem_DuplicataED getByRecord(HttpServletRequest request) throws Excecoes {

        Origem_DuplicataED ed = new Origem_DuplicataED();

        String oid_Origem_Duplicata = request.getParameter("oid_Origem_Duplicata");
        if (String.valueOf(oid_Origem_Duplicata) != null && !String.valueOf(oid_Origem_Duplicata).equals("") && !String.valueOf(oid_Origem_Duplicata).equals("null")) {
            ed.setOID_Origem_Duplicata(request.getParameter("oid_Origem_Duplicata"));
        }

        return new Origem_DuplicataRN().getByRecord(ed);

    }

    public Origem_DuplicataED getByParcelamento(String oid_Parcelamento, String oid_Nota_Fiscal) throws Excecoes {

        if (!util.doValida(oid_Nota_Fiscal))
            throw new Excecoes("Nota Fiscal não informada!");
        if (!util.doValida(oid_Parcelamento))
            throw new Excecoes("Parcelamento não informado!");

        Origem_DuplicataED ed = new Origem_DuplicataED();
        ed.setOid_Nota_Fiscal(oid_Nota_Fiscal);
        ed.setOid_Parcelamento(Integer.parseInt(oid_Parcelamento));

        return new Origem_DuplicataRN().getByParcelamento(ed);

    }

    public void desconto(HttpServletRequest request) throws Excecoes {

        try {
            Origem_DuplicataRN Origem_Duplicatarn = new Origem_DuplicataRN();
            Origem_DuplicataED ed = new Origem_DuplicataED();

            ed.setOID_Origem_Duplicata(request.getParameter("oid_Origem_Duplicata"));
            ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));

            String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");
            if (String.valueOf(nr_Conhecimento) != null && !String.valueOf(nr_Conhecimento).equals("") && !String.valueOf(nr_Conhecimento).equals("null")) {
                ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
            }

            String oid_Tipo_Ocorrencia = request.getParameter("oid_Tipo_Ocorrencia");
            if (String.valueOf(oid_Tipo_Ocorrencia) != null && !String.valueOf(oid_Tipo_Ocorrencia).equals("") && !String.valueOf(oid_Tipo_Ocorrencia).equals("null")) {
                ed.setOID_Tipo_Ocorrencia(new Long(oid_Tipo_Ocorrencia).longValue());
            }

            String TX_Observacao = request.getParameter("FT_TX_Observacao");
            if (String.valueOf(TX_Observacao) != null && !String.valueOf(TX_Observacao).equals("") && !String.valueOf(TX_Observacao).equals("null")) {
                ed.setTX_Observacao(TX_Observacao);
            }

            String VL_Desconto = request.getParameter("FT_VL_Desconto");
            if (String.valueOf(VL_Desconto) != null && !String.valueOf(VL_Desconto).equals("") && !String.valueOf(VL_Desconto).equals("null")) {
                ed.setVL_Desconto(new Double(VL_Desconto).doubleValue());
            }
            String VL_Desconto_Concedido = request.getParameter("FT_VL_Desconto_Concedido");
            if (String.valueOf(VL_Desconto_Concedido) != null && !String.valueOf(VL_Desconto_Concedido).equals("") && !String.valueOf(VL_Desconto_Concedido).equals("null")) {
                ed.setVL_Desconto_Concedido(new Double(VL_Desconto_Concedido).doubleValue());
            }


            Origem_Duplicatarn.desconto(ed);

        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao excluir");
            excecoes.setMetodo("excluir");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    //public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    //  Origem_DuplicataED ed = new Origem_DuplicataED();

    /***************************************************************************
     * * * * * * A T E N Ç Ã O * * * * * * /* SETAR O ED PARA PESQUISA NO BD
     */

    //    Origem_DuplicataRN geRN = new Origem_DuplicataRN();
    //  geRN.geraRelatorio(ed);
    //}
}
