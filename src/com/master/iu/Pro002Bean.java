package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Nivel_ProdutoED;
import com.master.rn.Nivel_ProdutoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

public class Pro002Bean extends JavaUtil {

    public Nivel_ProdutoED inclui(HttpServletRequest request) throws Excecoes {

        Nivel_ProdutoRN Nivel_ProdutoRN = new Nivel_ProdutoRN();
        Nivel_ProdutoED ed = new Nivel_ProdutoED();

        ed.setCd_Nivel_Produto(request.getParameter("FT_CD_Nivel_Produto"));
        ed.setNm_Nivel_Produto(request.getParameter("FT_NM_Nivel_Produto"));
        ed.setDm_Nivel_Produto(request.getParameter("FT_DM_Nivel_Produto"));

        //*** Valida Inclusão!
        if (new BancoUtil().doExiste("Niveis_Produtos", "(CD_Nivel_Produto = '" + ed.getCd_Nivel_Produto() +"' OR " +
        							 " NM_Nivel_Produto = '" + ed.getNm_Nivel_Produto() + "') " +
        							 " AND DM_Nivel_Produto = '" + ed.getDm_Nivel_Produto() + "'"))
            throw new Mensagens("Ja existe um Nível com o Código ou Nome informado!");

        return Nivel_ProdutoRN.inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        Nivel_ProdutoRN Nivel_ProdutoRN = new Nivel_ProdutoRN();
        Nivel_ProdutoED ed = new Nivel_ProdutoED();

        ed.setOid_Nivel_Produto(new Integer(request.getParameter("oid_Nivel_Produto")).intValue());
        ed.setCd_Nivel_Produto(request.getParameter("FT_CD_Nivel_Produto"));
        ed.setNm_Nivel_Produto(request.getParameter("FT_NM_Nivel_Produto"));
        ed.setDm_Nivel_Produto(request.getParameter("FT_DM_Nivel_Produto"));

        //*** Valida Alteração!
        if (new BancoUtil().doExiste("Niveis_Produtos", "(CD_Nivel_Produto = '" + ed.getCd_Nivel_Produto() +"' OR " +
										" NM_Nivel_Produto = '" + ed.getNm_Nivel_Produto() + "') " +
										" AND DM_Nivel_Produto = '" + ed.getDm_Nivel_Produto() + "'" + 
										" AND oid_Nivel_Produto <> "+ ed.getOid_Nivel_Produto()))
            throw new Mensagens("Ja existe outro Nível com o Código informado!");

        Nivel_ProdutoRN.altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        Nivel_ProdutoRN Nivel_Produtorn = new Nivel_ProdutoRN();
        Nivel_ProdutoED ed = new Nivel_ProdutoED();

        ed.setOid_Nivel_Produto(new Integer(request.getParameter("oid_Nivel_Produto")).intValue());

        Nivel_Produtorn.deleta(ed);
    }

    public ArrayList Nivel_Produto_Lista(HttpServletRequest request) throws Excecoes {

        Nivel_ProdutoED ed = new Nivel_ProdutoED();

        String oid_Nivel_Produto = request.getParameter("oid_Nivel_Produto");
        String cd_Nivel_Produto = request.getParameter("FT_CD_Nivel_Produto");
        String nm_Nivel_Produto = request.getParameter("FT_NM_Nivel_Produto");

        ed.setDm_Nivel_Produto(request.getParameter("FT_DM_Nivel_Produto"));

        if (doValida(oid_Nivel_Produto))
            ed.setOid_Nivel_Produto(Integer.parseInt(oid_Nivel_Produto));
        else {
            if (doValida(cd_Nivel_Produto))
                ed.setCd_Nivel_Produto(cd_Nivel_Produto);
            if (doValida(nm_Nivel_Produto))
                ed.setNm_Nivel_Produto(nm_Nivel_Produto);
        }

        return new Nivel_ProdutoRN().lista(ed);
    }

    public Nivel_ProdutoED getByRecord(HttpServletRequest request) throws Excecoes {

        Nivel_ProdutoED ed = new Nivel_ProdutoED();

        String oid_Nivel_Produto = request.getParameter("oid_Nivel_Produto");
        String cd_Nivel_Produto = request.getParameter("FT_CD_Nivel_Produto");
        String DM_Nivel_Produto = request.getParameter("FT_DM_Nivel_Produto");

        if (doValida(oid_Nivel_Produto))
            ed.setOid_Nivel_Produto(new Integer(oid_Nivel_Produto).intValue());
        if (doValida(cd_Nivel_Produto))
            ed.setCd_Nivel_Produto(cd_Nivel_Produto);
        if (doValida(DM_Nivel_Produto) && !DM_Nivel_Produto.equals("T"))
            ed.setDm_Nivel_Produto(DM_Nivel_Produto);

        return new Nivel_ProdutoRN().getByRecord(ed);

    }

    public void geraRelatorio(HttpServletRequest req) throws Excecoes {
        Nivel_ProdutoED ed = new Nivel_ProdutoED();

        //    ed.setCD_Nivel_Produto(req.getParameter("codigo"));
        //    ed.setCD_Remessa(req.getParameter("nome"));

        Nivel_ProdutoRN geRN = new Nivel_ProdutoRN();
        geRN.geraRelatorio(ed);
    }

}