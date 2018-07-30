package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.WMS_Tipo_MovimentoED;
import com.master.rn.WMS_Tipo_MovimentoRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class wms010Bean extends JavaUtil implements Serializable {

    public WMS_Tipo_MovimentoED inclui(HttpServletRequest request) throws Excecoes {

        WMS_Tipo_MovimentoRN WMS_Tipo_MovimentoRN = new WMS_Tipo_MovimentoRN();
        WMS_Tipo_MovimentoED ed = new WMS_Tipo_MovimentoED();

        ed.setNm_Descricao(request.getParameter("FT_NM_Descricao"));
        ed.setDm_Origem(request.getParameter("FT_DM_Origem"));
        ed.setDm_Destino(request.getParameter("FT_DM_Destino"));
        ed.setDm_Situacao(request.getParameter("FT_DM_Situacao"));
        ed.setDm_NF(request.getParameter("FT_DM_NF"));
        ed.setDm_tipo_movimento(request.getParameter("FT_DM_Tipo_Movimento"));

        return WMS_Tipo_MovimentoRN.inclui(ed);

    }

    public void altera(HttpServletRequest request) throws Excecoes {

        WMS_Tipo_MovimentoRN WMS_Tipo_MovimentoRN = new WMS_Tipo_MovimentoRN();
        WMS_Tipo_MovimentoED ed = new WMS_Tipo_MovimentoED();
        ed.setOid_Tipo_Movimento_Produto(new Integer(request.getParameter("FT_Oid_Tipo_Movimento_Produto")).intValue());
        ed.setNm_Descricao(request.getParameter("FT_NM_Descricao"));
        ed.setDm_Origem(request.getParameter("FT_DM_Origem"));
        ed.setDm_Destino(request.getParameter("FT_DM_Destino"));
        ed.setDm_Situacao(request.getParameter("FT_DM_Situacao"));
        ed.setDm_NF(request.getParameter("FT_DM_NF"));
        ed.setDm_tipo_movimento(request.getParameter("FT_DM_Tipo_Movimento"));

        WMS_Tipo_MovimentoRN.altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        WMS_Tipo_MovimentoRN WMS_Tipo_Movimentorn = new WMS_Tipo_MovimentoRN();
        WMS_Tipo_MovimentoED ed = new WMS_Tipo_MovimentoED();

        ed.setOid_Tipo_Movimento_Produto(new Integer(request.getParameter("FT_Oid_Tipo_Movimento_Produto")).intValue());

        WMS_Tipo_Movimentorn.deleta(ed);
    }

    public WMS_Tipo_MovimentoED getByRecord(HttpServletRequest request) throws Excecoes {

        WMS_Tipo_MovimentoED ed = new WMS_Tipo_MovimentoED();

        String Oid_Tipo_Movimento_Produto = request.getParameter("FT_Oid_Tipo_Movimento_Produto");

        if (Oid_Tipo_Movimento_Produto != null && !Oid_Tipo_Movimento_Produto.equals("0")) {
            ed.setOid_Tipo_Movimento_Produto(new Integer(Oid_Tipo_Movimento_Produto).intValue());
        }

        return new WMS_Tipo_MovimentoRN().getByRecord(ed);

    }

    public WMS_Tipo_MovimentoED getByOid(int Oid) throws Excecoes {

        return new WMS_Tipo_MovimentoRN().getByOid(Oid);

    }

    public ArrayList getAll() throws Excecoes {
        return new WMS_Tipo_MovimentoRN().getAll();
    }

    public ArrayList lista(HttpServletRequest request) throws Excecoes {

        WMS_Tipo_MovimentoED ed = new WMS_Tipo_MovimentoED();

        String Oid_Tipo_Movimento_Produto = request.getParameter("FT_Oid_Tipo_Movimento_Produto");
        String NM_Descricao = request.getParameter("FT_NM_Descricao");
        String DM_Tipo_Movimento = request.getParameter("FT_DM_Tipo_Movimento");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Destino = request.getParameter("FT_DM_Destino");
        String DM_NF = request.getParameter("FT_DM_NF");
        String DM_Origem = request.getParameter("FT_DM_Origem");

        if (doValida(Oid_Tipo_Movimento_Produto))
            ed.setOid_Tipo_Movimento_Produto(new Integer(Oid_Tipo_Movimento_Produto).intValue());
        if (doValida(NM_Descricao))
            ed.setNm_Descricao(NM_Descricao);
        if (doValida(DM_Tipo_Movimento))
            ed.setDm_tipo_movimento(DM_Tipo_Movimento);
        if (doValida(DM_Situacao))
            ed.setDm_Situacao(DM_Situacao);
        if (doValida(DM_Destino))
            ed.setDm_Destino(DM_Destino);
        if (doValida(DM_NF))
            ed.setDm_NF(DM_NF);
        if (doValida(DM_Origem))
            ed.setDm_Origem(DM_Origem);

        return new WMS_Tipo_MovimentoRN().lista(ed);
    }

    public void geraRelatorio(HttpServletRequest req) throws Excecoes {
        WMS_Tipo_MovimentoED ed = new WMS_Tipo_MovimentoED();

        //    ed.setCd_Tipo_Movimento(req.getParameter("codigo"));
        //    ed.setCD_Remessa(req.getParameter("nome"));

        WMS_Tipo_MovimentoRN geRN = new WMS_Tipo_MovimentoRN();
        geRN.geraRelatorio(ed);
    }
}