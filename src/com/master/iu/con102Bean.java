package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Conhecimento_Internacional_Nota_FiscalED;
import com.master.rn.Conhecimento_Internacional_Nota_FiscalRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class con102Bean {

    public Conhecimento_Internacional_Nota_FiscalED inclui(HttpServletRequest request, String OID_Nota_Fiscal) throws Excecoes {
        String DM_Tipo_Conhecimento = request.getParameter("FT_DM_Tipo_Conhecimento");

        Conhecimento_Internacional_Nota_FiscalED ed = new Conhecimento_Internacional_Nota_FiscalED();

        // request em cima dos campos dos forms html

        ed.setDT_Conhecimento_Internacional_Nota_Fiscal(request.getParameter("FT_DT_Conhecimento_Nota_Fiscal"));
        ed.setHR_Conhecimento_Internacional_Nota_Fiscal(request.getParameter("FT_HR_Conhecimento_Nota_Fiscal"));

        ed.setOID_Nota_Fiscal(OID_Nota_Fiscal);

        ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
        ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());

        if (JavaUtil.doValida(DM_Tipo_Conhecimento)) {
            ed.setDM_Tipo_Conhecimento(DM_Tipo_Conhecimento);
        }
        return new Conhecimento_Internacional_Nota_FiscalRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {
        Conhecimento_Internacional_Nota_FiscalRN Conhecimento_Internacional_Nota_Fiscalrn = new Conhecimento_Internacional_Nota_FiscalRN();
        Conhecimento_Internacional_Nota_FiscalED ed = new Conhecimento_Internacional_Nota_FiscalED();

        ed.setOID_Conhecimento_Internacional_Nota_Fiscal(request.getParameter("oid_Conhecimento_Nota_Fiscal"));
        ed.setDT_Conhecimento_Internacional_Nota_Fiscal(request.getParameter("FT_DT_Conhecimento_Nota_Fiscal"));
        ed.setHR_Conhecimento_Internacional_Nota_Fiscal(request.getParameter("FT_HR_Conhecimento_Nota_Fiscal"));

        Conhecimento_Internacional_Nota_Fiscalrn.altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        try {
            Conhecimento_Internacional_Nota_FiscalRN Conhecimento_Internacional_Nota_Fiscalrn = new Conhecimento_Internacional_Nota_FiscalRN();
            Conhecimento_Internacional_Nota_FiscalED ed = new Conhecimento_Internacional_Nota_FiscalED();

            ed.setOID_Conhecimento_Internacional_Nota_Fiscal(request.getParameter("oid_Conhecimento_Nota_Fiscal"));
            ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));

            // System.out.println("request > " + request.getParameter("oid_Conhecimento"));
            // System.out.println("ed > " + ed.getOID_Conhecimento_Internacional_Nota_Fiscal());

            Conhecimento_Internacional_Nota_Fiscalrn.deleta(ed);
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

    public ArrayList Conhecimento_Nota_Fiscal_Lista(HttpServletRequest request) throws Excecoes {

        Conhecimento_Internacional_Nota_FiscalED ed = new Conhecimento_Internacional_Nota_FiscalED();
        ed.setOID_Nota_Fiscal(request.getParameter("oid_Nota_Fiscal"));
        ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));

        return new Conhecimento_Internacional_Nota_FiscalRN().lista(ed);

    }

    public Conhecimento_Internacional_Nota_FiscalED getByRecord(HttpServletRequest request) throws Excecoes {

        Conhecimento_Internacional_Nota_FiscalED ed = new Conhecimento_Internacional_Nota_FiscalED();

        String oid_Conhecimento_Nota_Fiscal = request.getParameter("oid_Conhecimento_Nota_Fiscal");
        if (String.valueOf(oid_Conhecimento_Nota_Fiscal) != null && !String.valueOf(oid_Conhecimento_Nota_Fiscal).equals("") && !String.valueOf(oid_Conhecimento_Nota_Fiscal).equals("null")) {
            ed.setOID_Conhecimento_Internacional_Nota_Fiscal(oid_Conhecimento_Nota_Fiscal);
        }

        return new Conhecimento_Internacional_Nota_FiscalRN().getByRecord(ed);

    }

    public void geraRelTratoresPendentes(HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        Conhecimento_Internacional_Nota_FiscalED ed = new Conhecimento_Internacional_Nota_FiscalED();

        String Dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (String.valueOf(Dt_Emissao_Inicial) != null && !String.valueOf(Dt_Emissao_Inicial).equals("")) {
            ed.setDt_Emissao_Inicial(Dt_Emissao_Inicial);
        }

        String Dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        if (String.valueOf(Dt_Emissao_Final) != null && !String.valueOf(Dt_Emissao_Final).equals("")) {
            ed.setDt_Emissao_Final(Dt_Emissao_Final);
        }

        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        if (String.valueOf(DM_Situacao) != null && !String.valueOf(DM_Situacao).equals("")) {
            ed.setDM_Situacao(DM_Situacao);
        }

        ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
        ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
        ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
        String oid_Unidade = request.getParameter("oid_Unidade");
        if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")) {
            ed.setOID_Unidade(new Long(oid_Unidade).longValue());
        }

        Conhecimento_Internacional_Nota_FiscalRN geRN = new Conhecimento_Internacional_Nota_FiscalRN();
        byte[] b = geRN.geraRelTratoresPendentes(ed);
        new EnviaPDF().enviaBytes(request, response, b);

    }

    public void geraRelTratoresEmbarc(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
        Conhecimento_Internacional_Nota_FiscalED ed = new Conhecimento_Internacional_Nota_FiscalED();

        String Dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (String.valueOf(Dt_Emissao_Inicial) != null && !String.valueOf(Dt_Emissao_Inicial).equals("")) {
            ed.setDt_Emissao_Inicial(Dt_Emissao_Inicial);
        }

        String Dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        if (String.valueOf(Dt_Emissao_Final) != null && !String.valueOf(Dt_Emissao_Final).equals("")) {
            ed.setDt_Emissao_Final(Dt_Emissao_Final);
        }

        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        if (String.valueOf(DM_Situacao) != null && !String.valueOf(DM_Situacao).equals("")) {
            ed.setDM_Situacao(DM_Situacao);
        }

        ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
        ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
        ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
        String oid_Unidade = request.getParameter("oid_Unidade");
        if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")) {
            ed.setOID_Unidade(new Long(oid_Unidade).longValue());
        }

        Conhecimento_Internacional_Nota_FiscalRN geRN = new Conhecimento_Internacional_Nota_FiscalRN();
        byte[] b = geRN.geraRelTratoresEmbarc(ed);
        new EnviaPDF().enviaBytes(request, Response, b);

    }

    public void geraSeguroNacional(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
        Conhecimento_Internacional_Nota_FiscalED ed = new Conhecimento_Internacional_Nota_FiscalED();

        String Dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (String.valueOf(Dt_Emissao_Inicial) != null && !String.valueOf(Dt_Emissao_Inicial).equals("")) {
            ed.setDt_Emissao_Inicial(Dt_Emissao_Inicial);
        }

        String Dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        if (String.valueOf(Dt_Emissao_Final) != null && !String.valueOf(Dt_Emissao_Final).equals("")) {
            ed.setDt_Emissao_Final(Dt_Emissao_Final);
        }

        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        if (String.valueOf(DM_Situacao) != null && !String.valueOf(DM_Situacao).equals("")) {
            ed.setDM_Situacao(DM_Situacao);
        }

        ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));

        ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));

        String oid_Unidade = request.getParameter("oid_Unidade");
        if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")) {
            ed.setOID_Unidade(new Long(oid_Unidade).longValue());
        }

        // // //// System.out.println(" bean ");

        Conhecimento_Internacional_Nota_FiscalRN geRN = new Conhecimento_Internacional_Nota_FiscalRN();
        byte[] b = geRN.geraSeguroNacional(ed);
        new EnviaPDF().enviaBytes(request, Response, b);

    }

    public void geraRelConhecTrocaNota(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
        Conhecimento_Internacional_Nota_FiscalED ed = new Conhecimento_Internacional_Nota_FiscalED();

        ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
        String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");
        if (String.valueOf(nr_Conhecimento) != null && !String.valueOf(nr_Conhecimento).equals("")) {
            ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
        }
        ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
        ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
        ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
        String oid_Unidade = request.getParameter("oid_Unidade");
        if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")) {
            ed.setOID_Unidade(new Long(oid_Unidade).longValue());
        }

        Conhecimento_Internacional_Nota_FiscalRN geRN = new Conhecimento_Internacional_Nota_FiscalRN();
        byte[] b = geRN.geraRelConhecTrocaNota(ed);
        new EnviaPDF().enviaBytes(request, Response, b);

    }

    // //### Panazzolo 17062003
    public void geraRelMercadoriasEmbarcadas(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
        Conhecimento_Internacional_Nota_FiscalED ed = new Conhecimento_Internacional_Nota_FiscalED();

        String Dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (String.valueOf(Dt_Emissao_Inicial) != null && !String.valueOf(Dt_Emissao_Inicial).equals("")) {
            ed.setDt_Emissao_Inicial(Dt_Emissao_Inicial);
        }

        String Dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        if (String.valueOf(Dt_Emissao_Final) != null && !String.valueOf(Dt_Emissao_Final).equals("")) {
            ed.setDt_Emissao_Final(Dt_Emissao_Final);
        }

        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        if (String.valueOf(DM_Situacao) != null && !String.valueOf(DM_Situacao).equals("")) {
            ed.setDM_Situacao(DM_Situacao);
        }

        ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
        ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
        ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
        String oid_Unidade = request.getParameter("oid_Unidade");
        if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")) {
            ed.setOID_Unidade(new Long(oid_Unidade).longValue());
        }

        Conhecimento_Internacional_Nota_FiscalRN geRN = new Conhecimento_Internacional_Nota_FiscalRN();
        byte[] b = geRN.geraRelMercadoriasEmbarcadas(ed);
        new EnviaPDF().enviaBytes(request, Response, b);

    }
}