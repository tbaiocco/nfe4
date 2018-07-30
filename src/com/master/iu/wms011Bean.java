package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.WMS_Tipo_EstoqueED;
import com.master.rn.WMS_Tipo_EstoqueRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class wms011Bean  {

	private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

    public WMS_Tipo_EstoqueED inclui(HttpServletRequest request) throws Excecoes {

        WMS_Tipo_EstoqueRN WMS_Tipo_EstoqueRN = new WMS_Tipo_EstoqueRN();
        WMS_Tipo_EstoqueED ed = new WMS_Tipo_EstoqueED();

        ed.setNm_Tipo_Estoque(request.getParameter("FT_NM_Tipo_Estoque"));
        ed.setCd_Tipo_Estoque(request.getParameter("FT_CD_Tipo_Estoque"));
        ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

        if (util.doExiste("Tipos_Estoques", "CD_Tipo_Estoque = '"+ed.getCd_Tipo_Estoque()+"'"))
            throw new Mensagens("Já Existe um Tipo de Estoque com o Código informado!");
        
        return WMS_Tipo_EstoqueRN.inclui(ed);

    }

    public void altera(HttpServletRequest request) throws Excecoes {

        WMS_Tipo_EstoqueRN WMS_Tipo_EstoqueRN = new WMS_Tipo_EstoqueRN();
        WMS_Tipo_EstoqueED ed = new WMS_Tipo_EstoqueED();
        ed.setOid_Tipo_Estoque(new Integer(request.getParameter("FT_OID_Tipo_Estoque")).intValue());
        ed.setNm_Tipo_Estoque(request.getParameter("FT_NM_Tipo_Estoque"));
        ed.setCd_Tipo_Estoque(request.getParameter("FT_CD_Tipo_Estoque"));
        ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

        util.doValidaUpdate(ed.getOid_Tipo_Estoque(),ed.getCd_Tipo_Estoque(),"Tipos_Estoques", "oid_Tipo_Estoque", "CD_Tipo_Estoque");

        WMS_Tipo_EstoqueRN.altera(ed);

    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        WMS_Tipo_EstoqueRN WMS_Tipo_Estoquern = new WMS_Tipo_EstoqueRN();
        WMS_Tipo_EstoqueED ed = new WMS_Tipo_EstoqueED();

        ed.setOid_Tipo_Estoque(new Integer(request.getParameter("FT_OID_Tipo_Estoque")).intValue());

        WMS_Tipo_Estoquern.deleta(ed);
    }

    public WMS_Tipo_EstoqueED getByRecord(HttpServletRequest request) throws Excecoes {

        WMS_Tipo_EstoqueED ed = new WMS_Tipo_EstoqueED();

        String oid_Tipo_Estoque = request.getParameter("FT_OID_Tipo_Estoque");
        String NM_Tipo_Estoque = request.getParameter("FT_NM_Tipo_Estoque");
        String CD_Tipo_Estoque = request.getParameter("FT_CD_Tipo_Estoque");

        if (CD_Tipo_Estoque != null && CD_Tipo_Estoque.length() > 0)
            ed.setCd_Tipo_Estoque(CD_Tipo_Estoque);

        if (NM_Tipo_Estoque != null && NM_Tipo_Estoque.length() > 0)
            ed.setNm_Tipo_Estoque(NM_Tipo_Estoque);

        if (oid_Tipo_Estoque != null && oid_Tipo_Estoque.length() > 0)
            ed.setOid_Tipo_Estoque(Integer.valueOf(oid_Tipo_Estoque).intValue());

        return new WMS_Tipo_EstoqueRN().getByRecord(ed);
    }

    public WMS_Tipo_EstoqueED getByOid_Tipo_Estoque(String oid_Tipo_Estoque) throws Excecoes {

        WMS_Tipo_EstoqueED ed = new WMS_Tipo_EstoqueED();

        if (util.doValida(oid_Tipo_Estoque))
            ed.setOid_Tipo_Estoque(Integer.parseInt(oid_Tipo_Estoque));
        else throw new Excecoes("Parâmetros incorretos!");

        return new WMS_Tipo_EstoqueRN().getByRecord(ed);
    }

    public WMS_Tipo_EstoqueED getByCD_Tipo_Estoque(HttpServletRequest request) throws Excecoes {

        WMS_Tipo_EstoqueED ed = new WMS_Tipo_EstoqueED();

        String CD_Tipo_Estoque = request.getParameter("FT_CD_Tipo_Estoque");

        if (util.doValida(CD_Tipo_Estoque)) {
            ed.setCd_Tipo_Estoque(CD_Tipo_Estoque);
            return new WMS_Tipo_EstoqueRN().getByCD_Tipo_Estoque(ed);
        }
        return ed;
    }

    public void geraRelatorio(HttpServletRequest req) throws Excecoes {
        WMS_Tipo_EstoqueED ed = new WMS_Tipo_EstoqueED();
        WMS_Tipo_EstoqueRN geRN = new WMS_Tipo_EstoqueRN();
        geRN.geraRelatorio(ed);
    }

    public ArrayList lista(HttpServletRequest request) throws Excecoes {

        WMS_Tipo_EstoqueED ed = new WMS_Tipo_EstoqueED();

        String NM_Tipo_Estoque = request.getParameter("FT_NM_Tipo_Estoque");
        String CD_Tipo_Estoque = request.getParameter("FT_CD_Tipo_Estoque");

        if (util.doValida(NM_Tipo_Estoque))
            ed.setNm_Tipo_Estoque(NM_Tipo_Estoque);
        if (util.doValida(CD_Tipo_Estoque))
            ed.setCd_Tipo_Estoque(CD_Tipo_Estoque);

        return new WMS_Tipo_EstoqueRN().lista(ed);
    }
}