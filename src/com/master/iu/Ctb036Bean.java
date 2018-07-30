package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.CategoriaED;
import com.master.rn.CategoriaRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class Ctb036Bean extends JavaUtil {

    public CategoriaED inclui(HttpServletRequest request) throws Excecoes {

        CategoriaED ed = new CategoriaED();

        ed.setNm_Categoria(request.getParameter("FT_NM_Categoria"));

        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));

        String PE_Fator_Anual = request.getParameter("FT_PE_Fator_Anual");
        if (PE_Fator_Anual != null && !PE_Fator_Anual.equals(""))
            ed.setPe_Fator_Anual(new Double(PE_Fator_Anual));

        String NR_Anos = request.getParameter("FT_NR_Anos");
        if (NR_Anos != null && !NR_Anos.equals(""))
            ed.setNr_Anos(new Long(NR_Anos).longValue());

        String oid_Conta_Credora_Venda = request.getParameter("oid_Conta_Credora_Venda");
        if (oid_Conta_Credora_Venda != null && !oid_Conta_Credora_Venda.equals(""))
            ed.setOID_Conta_Credora_Venda(new Integer(oid_Conta_Credora_Venda));
        String oid_Conta_Devedora_Venda = request.getParameter("oid_Conta_Devedora_Venda");
        if (oid_Conta_Devedora_Venda != null && !oid_Conta_Devedora_Venda.equals(""))
            ed.setOID_Conta_Devedora_Venda(new Integer(oid_Conta_Devedora_Venda));

        String oid_Conta_Credora_Baixa_Depreciacao = request.getParameter("oid_Conta_Credora_Baixa_Depreciacao");
        if (oid_Conta_Credora_Baixa_Depreciacao != null && !oid_Conta_Credora_Baixa_Depreciacao.equals(""))
            ed.setOID_Conta_Credora_Baixa_Depreciacao(new Integer(oid_Conta_Credora_Baixa_Depreciacao));
        String oid_Conta_Devedora_Baixa_Depreciacao = request.getParameter("oid_Conta_Devedora_Baixa_Depreciacao");
        if (oid_Conta_Devedora_Baixa_Depreciacao != null && !oid_Conta_Devedora_Baixa_Depreciacao.equals(""))
            ed.setOID_Conta_Devedora_Baixa_Depreciacao(new Integer(oid_Conta_Devedora_Baixa_Depreciacao));

        String oid_Conta_Credora_Ganho_Venda = request.getParameter("oid_Conta_Credora_Ganho_Venda");
        if (oid_Conta_Credora_Ganho_Venda != null && !oid_Conta_Credora_Ganho_Venda.equals(""))
            ed.setOID_Conta_Credora_Ganho_Venda(new Integer(oid_Conta_Credora_Ganho_Venda));
        String oid_Conta_Devedora_Ganho_Venda = request.getParameter("oid_Conta_Devedora_Ganho_Venda");
        if (oid_Conta_Devedora_Ganho_Venda != null && !oid_Conta_Devedora_Ganho_Venda.equals(""))
            ed.setOID_Conta_Devedora_Ganho_Venda(new Integer(oid_Conta_Devedora_Ganho_Venda));

        String oid_Conta_Credora_Perda_Venda = request.getParameter("oid_Conta_Credora_Perda_Venda");
        if (oid_Conta_Credora_Perda_Venda != null && !oid_Conta_Credora_Perda_Venda.equals(""))
            ed.setOID_Conta_Credora_Perda_Venda(new Integer(oid_Conta_Credora_Perda_Venda));
        String oid_Conta_Devedora_Perda_Venda = request.getParameter("oid_Conta_Devedora_Perda_Venda");
        if (oid_Conta_Devedora_Perda_Venda != null && !oid_Conta_Devedora_Perda_Venda.equals(""))
            ed.setOID_Conta_Devedora_Perda_Venda(new Integer(oid_Conta_Devedora_Perda_Venda));

        return new CategoriaRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        CategoriaED ed = new CategoriaED();

        ed.setOid_Categoria(request.getParameter("oid_Categoria"));
        ed.setNm_Categoria(request.getParameter("FT_NM_Categoria"));

        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));

        String PE_Fator_Anual = request.getParameter("FT_PE_Fator_Anual");
        if (PE_Fator_Anual != null && !PE_Fator_Anual.equals(""))
            ed.setPe_Fator_Anual(new Double(PE_Fator_Anual));

        String NR_Anos = request.getParameter("FT_NR_Anos");
        if (NR_Anos != null && !NR_Anos.equals(""))
            ed.setNr_Anos(new Long(NR_Anos).longValue());

        String oid_Conta_Credora_Venda = request.getParameter("oid_Conta_Credora_Venda");
        if (oid_Conta_Credora_Venda != null && !oid_Conta_Credora_Venda.equals(""))
            ed.setOID_Conta_Credora_Venda(new Integer(oid_Conta_Credora_Venda));
        String oid_Conta_Devedora_Venda = request.getParameter("oid_Conta_Devedora_Venda");
        if (oid_Conta_Devedora_Venda != null && !oid_Conta_Devedora_Venda.equals(""))
            ed.setOID_Conta_Devedora_Venda(new Integer(oid_Conta_Devedora_Venda));

        String oid_Conta_Credora_Baixa_Depreciacao = request.getParameter("oid_Conta_Credora_Baixa_Depreciacao");
        if (oid_Conta_Credora_Baixa_Depreciacao != null && !oid_Conta_Credora_Baixa_Depreciacao.equals(""))
            ed.setOID_Conta_Credora_Baixa_Depreciacao(new Integer(oid_Conta_Credora_Baixa_Depreciacao));
        String oid_Conta_Devedora_Baixa_Depreciacao = request.getParameter("oid_Conta_Devedora_Baixa_Depreciacao");
        if (oid_Conta_Devedora_Baixa_Depreciacao != null && !oid_Conta_Devedora_Baixa_Depreciacao.equals(""))
            ed.setOID_Conta_Devedora_Baixa_Depreciacao(new Integer(oid_Conta_Devedora_Baixa_Depreciacao));

        String oid_Conta_Credora_Ganho_Venda = request.getParameter("oid_Conta_Credora_Ganho_Venda");
        if (oid_Conta_Credora_Ganho_Venda != null && !oid_Conta_Credora_Ganho_Venda.equals(""))
            ed.setOID_Conta_Credora_Ganho_Venda(new Integer(oid_Conta_Credora_Ganho_Venda));
        String oid_Conta_Devedora_Ganho_Venda = request.getParameter("oid_Conta_Devedora_Ganho_Venda");
        if (oid_Conta_Devedora_Ganho_Venda != null && !oid_Conta_Devedora_Ganho_Venda.equals(""))
            ed.setOID_Conta_Devedora_Ganho_Venda(new Integer(oid_Conta_Devedora_Ganho_Venda));

        String oid_Conta_Credora_Perda_Venda = request.getParameter("oid_Conta_Credora_Perda_Venda");
        if (oid_Conta_Credora_Perda_Venda != null && !oid_Conta_Credora_Perda_Venda.equals(""))
            ed.setOID_Conta_Credora_Perda_Venda(new Integer(oid_Conta_Credora_Perda_Venda));
        String oid_Conta_Devedora_Perda_Venda = request.getParameter("oid_Conta_Devedora_Perda_Venda");
        if (oid_Conta_Devedora_Perda_Venda != null && !oid_Conta_Devedora_Perda_Venda.equals(""))
            ed.setOID_Conta_Devedora_Perda_Venda(new Integer(oid_Conta_Devedora_Perda_Venda));

        new CategoriaRN().altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        CategoriaED ed = new CategoriaED();

        ed.setOid_Categoria(request.getParameter("oid_Categoria"));

        new CategoriaRN().deleta(ed);
    }

    public ArrayList Categoria_Lista(HttpServletRequest request) throws Excecoes {

        CategoriaED ed = new CategoriaED();

        String Oid_Categoria = request.getParameter("oid_Categoria");
        if (Oid_Categoria != null && !Oid_Categoria.equals(""))
        	ed.setOid_Categoria(Oid_Categoria);

        String Nm_Categoria = request.getParameter("FT_NM_Categoria");
        if (Nm_Categoria != null && !Nm_Categoria.equals(""))
        	ed.setNm_Categoria(Nm_Categoria);

        String oid_Conta = request.getParameter("oid_Conta");
        if (oid_Conta != null && !oid_Conta.equals(""))
        	ed.setOid_Conta(new Integer(oid_Conta));

        String PE_Fator_Anual = request.getParameter("FT_PE_Fator_Anual");
        if (PE_Fator_Anual != null && !PE_Fator_Anual.equals(""))
            ed.setPe_Fator_Anual(new Double(PE_Fator_Anual));

        String NR_Anos = request.getParameter("FT_NR_Anos");
        if (NR_Anos != null && !NR_Anos.equals(""))
            ed.setNr_Anos(new Long(NR_Anos).longValue());

        return new CategoriaRN().lista(ed);

    }

    public CategoriaED getByRecord(HttpServletRequest request) throws Excecoes {

        CategoriaED ed = new CategoriaED();

        String oid_Categoria = request.getParameter("oid_Categoria");

        if (oid_Categoria != null && oid_Categoria.length() > 0) {
            ed.setOid_Categoria(oid_Categoria);
        }
        return new CategoriaRN().getByRecord(ed);
    }

    public CategoriaED getByRecord(String oid_Categoria) throws Excecoes {

        if (doValida(oid_Categoria))
            return new CategoriaRN().getByRecord(new CategoriaED(oid_Categoria));
        else return new CategoriaED();
    }
}
