package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.BorderoED;
import com.master.rn.BorderoRN;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;


public class Cob103Bean  {

  Utilitaria util = new Utilitaria();

    public BorderoED inclui(HttpServletRequest request)throws Excecoes{

        BorderoED ed = new BorderoED();

        ed.setOid_Carteira(new Integer(request.getParameter("oid_Carteira")));
        ed.setDM_Bordero(request.getParameter("FT_DM_Bordero"));
        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setDt_Operacao(request.getParameter("FT_DT_Operacao"));

        ed.setVl_Bordero(new Double (0.0));
        String VL_Bordero = request.getParameter("FT_VL_Bordero");
        if (util.doValida(VL_Bordero))
            ed.setVl_Bordero(new Double(request.getParameter("FT_VL_Bordero")));

        return new BorderoRN().inclui(ed);
    }

    public void altera(HttpServletRequest request)throws Excecoes{

        BorderoED ed = new BorderoED();
        ed.setOid_Bordero(new Integer(request.getParameter("oid_Bordero")));
        ed.setVl_Bordero(new Double(request.getParameter("FT_VL_Bordero")));

        new BorderoRN().altera(ed);
    }

    public void deleta(HttpServletRequest request)throws Excecoes{

        BorderoED ed = new BorderoED();
        ed.setOid_Bordero(new Integer(request.getParameter("oid_Bordero")));
        new BorderoRN().deleta(ed);
    }

    public ArrayList lista(HttpServletRequest request)throws Excecoes{

        BorderoED ed = new BorderoED();
        String oid_Carteira = request.getParameter("oid_Carteira");
        if (util.doValida(oid_Carteira))
            ed.setOid_Carteira(new Integer(oid_Carteira));

        String dt_Emissao = request.getParameter("FT_DT_Emissao_Inicial");
        if (util.doValida(dt_Emissao))
            ed.setDT_Emissao_Inicial(dt_Emissao);

        dt_Emissao = request.getParameter("FT_DT_Emissao_Final");
        if (util.doValida(dt_Emissao))
           ed.setDT_Emissao_Final(dt_Emissao);

        String NR_Bordero = request.getParameter("FT_NR_Bordero");
        if (util.doValida(NR_Bordero))
            ed.setNr_Bordero(new Integer(NR_Bordero));

        return new BorderoRN().lista(ed);
    }

    public BorderoED getByRecord(HttpServletRequest request)throws Excecoes{

        BorderoED ed = new BorderoED();
        String oid_Bordero = request.getParameter("oid_Bordero");
        if (util.doValida(oid_Bordero))
            ed.setOid_Bordero(new Integer(oid_Bordero));

        return new BorderoRN().getByRecord(ed);
    }

    public byte[] geraRelMovimentoBordero(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{

        BorderoED ed = new BorderoED();
        String oid_Bordero = request.getParameter("oid_Bordero");
        if (util.doValida(oid_Bordero))
            ed.setOid_Bordero(new Integer(oid_Bordero));

        ed.setDM_Relatorio(request.getParameter("FT_DM_Relatorio"));
        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));

        String dt_Operacao = request.getParameter("FT_DT_Operacao");
        if (util.doValida(dt_Operacao))
           ed.setDt_Operacao(dt_Operacao);


        String NR_Bordero = request.getParameter("FT_NR_Bordero");
        if (util.doValida(NR_Bordero))
            ed.setNr_Bordero(new Integer(NR_Bordero));

        return new BorderoRN().geraRelMovimentoBordero(ed);
    }
}
