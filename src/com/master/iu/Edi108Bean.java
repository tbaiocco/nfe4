package com.master.iu;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.EDI_AuxiliarED;
import com.master.rn.EDI_AuxiliarRN;
import com.master.util.Excecoes;

public class Edi108Bean {

    public void importaPessoa(HttpServletRequest request) throws Excecoes {

        EDI_AuxiliarED ed = new EDI_AuxiliarED();
        ed.setNM_Arquivo(request.getParameter("FT_NM_Arquivo"));
        new EDI_AuxiliarRN().importaPessoa(ed);
    }

    public void importaTabelaVenda(HttpServletRequest request) throws Excecoes {

        EDI_AuxiliarED ed = new EDI_AuxiliarED();
        ed.setNM_Arquivo(request.getParameter("FT_NM_Arquivo"));
        new EDI_AuxiliarRN().importaTabelaVenda(ed);
    }
    
    public void importaPercProdutos(HttpServletRequest request) throws Excecoes {

        EDI_AuxiliarED ed = new EDI_AuxiliarED();
        ed.setNM_Arquivo(request.getParameter("FT_NM_Arquivo"));
        new EDI_AuxiliarRN().importaPercProdutos(ed);
    }
    
    public void importaEstoque(HttpServletRequest request) throws Excecoes {

        EDI_AuxiliarED ed = new EDI_AuxiliarED();
        ed.setNM_Arquivo(request.getParameter("FT_NM_Arquivo"));
        new EDI_AuxiliarRN().importaEstoque(ed);
    }
    
    public void importaDuplicatas(HttpServletRequest request) throws Excecoes {

        EDI_AuxiliarED ed = new EDI_AuxiliarED();
        ed.setNM_Arquivo(request.getParameter("FT_NM_Arquivo"));
        new EDI_AuxiliarRN().importaDuplicatas(ed);
    }
    
    public void importaRotas(HttpServletRequest request) throws Excecoes {

        EDI_AuxiliarED ed = new EDI_AuxiliarED();
        ed.setNM_Arquivo(request.getParameter("FT_NM_Arquivo"));
        new EDI_AuxiliarRN().importaRotas(ed);
    }
}