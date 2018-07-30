package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Estrutura_ProdutoED;
import com.master.rn.Estrutura_ProdutoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

public class Pro003Bean extends BancoUtil {

    public Estrutura_ProdutoED inclui(HttpServletRequest request) throws Excecoes {

        String NM_Estrututura = "";
        Estrutura_ProdutoED ed = new Estrutura_ProdutoED();

        ed.setCd_Estrutura_Produto(request.getParameter("FT_CD_Estrutura_Produto"));
        ed.setDm_Estrutura_Produto(request.getParameter("FT_DM_Estrutura_Produto"));
        ed.setDm_Estrutura_Produto("A");

        if (doValida(request.getParameter("oid_Nivel_Produto1"))) {
            ed.setOid_Nivel_Produto1(Integer.parseInt(request.getParameter("oid_Nivel_Produto1")));
            NM_Estrututura += getTableStringValue("NM_Nivel_Produto", "Niveis_Produtos", "oid_nivel_produto = " + ed.getOid_Nivel_Produto1());
        }
        if (doValida(request.getParameter("oid_Nivel_Produto2"))) {
            ed.setOid_Nivel_Produto2(Integer.parseInt(request.getParameter("oid_Nivel_Produto2")));
            NM_Estrututura += (NM_Estrututura.length() > 0 ? "/" : "") + getTableStringValue("NM_Nivel_Produto", "Niveis_Produtos", "oid_nivel_produto = " + ed.getOid_Nivel_Produto2());
        }
        if (doValida(request.getParameter("oid_Nivel_Produto3"))) {
            ed.setOid_Nivel_Produto3(Integer.parseInt(request.getParameter("oid_Nivel_Produto3")));
            NM_Estrututura += (NM_Estrututura.length() > 0 ? "/" : "") + getTableStringValue("NM_Nivel_Produto", "Niveis_Produtos", "oid_nivel_produto = " + ed.getOid_Nivel_Produto3());
        }
        if (doValida(request.getParameter("oid_Nivel_Produto4"))) {
            ed.setOid_Nivel_Produto4(Integer.parseInt(request.getParameter("oid_Nivel_Produto4")));
            NM_Estrututura += (NM_Estrututura.length() > 0 ? "/" : "") + getTableStringValue("NM_Nivel_Produto", "Niveis_Produtos", "oid_nivel_produto = " + ed.getOid_Nivel_Produto4());
        }
        if (doValida(request.getParameter("oid_Nivel_Produto5"))) {
            ed.setOid_Nivel_Produto5(Integer.parseInt(request.getParameter("oid_Nivel_Produto5")));
            NM_Estrututura += (NM_Estrututura.length() > 0 ? "/" : "") + getTableStringValue("NM_Nivel_Produto", "Niveis_Produtos", "oid_nivel_produto = " + ed.getOid_Nivel_Produto5());
        }
        ed.setNm_Estrutura_Produto(NM_Estrututura);

        //*** Valida Inclusão!
        if (doValida(ed.getCd_Estrutura_Produto())) {
           	ed.setCd_Estrutura_Produto(JavaUtil.LFill(ed.getCd_Estrutura_Produto(), 3, true));
            if (doExiste("Estruturas_Produtos","CD_Estrutura_Produto = '"+ed.getCd_Estrutura_Produto()+"'"))
                throw new Mensagens("Ja existe uma Estrutura com o Código informado!");
        }
        
        if (doExiste("Estruturas_Produtos","oid_Nivel_Produto1 = "+ed.getOid_Nivel_Produto1()+
                						   " AND oid_Nivel_Produto2 = "+ed.getOid_Nivel_Produto2()+
                						   " AND oid_Nivel_Produto3 = "+ed.getOid_Nivel_Produto3()+
                						   " AND oid_Nivel_Produto4 = "+ed.getOid_Nivel_Produto4()+
                						   " AND oid_Nivel_Produto5 = "+ed.getOid_Nivel_Produto5()))
            throw new Mensagens("Essa Estrutura já existe!");
        
        return new Estrutura_ProdutoRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        Estrutura_ProdutoRN Estrutura_ProdutoRN = new Estrutura_ProdutoRN();
        Estrutura_ProdutoED ed = new Estrutura_ProdutoED();

        ed.setOid_Estrutura_Produto(new Integer(request.getParameter("oid_Estrutura_Produto")).intValue());
        ed.setCd_Estrutura_Produto(request.getParameter("FT_CD_Estrutura_Produto"));
        ed.setDm_Estrutura_Produto("A");

        if (doValida(request.getParameter("oid_Nivel_Produto1")))
            ed.setOid_Nivel_Produto1(new Integer(request.getParameter("oid_Nivel_Produto1")).intValue());
        if (doValida(request.getParameter("oid_Nivel_Produto2")))
            ed.setOid_Nivel_Produto2(new Integer(request.getParameter("oid_Nivel_Produto2")).intValue());
        if (doValida(request.getParameter("oid_Nivel_Produto3")))
            ed.setOid_Nivel_Produto3(new Integer(request.getParameter("oid_Nivel_Produto3")).intValue());
        if (doValida(request.getParameter("oid_Nivel_Produto4")))
            ed.setOid_Nivel_Produto4(new Integer(request.getParameter("oid_Nivel_Produto4")).intValue());
        if (doValida(request.getParameter("oid_Nivel_Produto5")))
            ed.setOid_Nivel_Produto5(new Integer(request.getParameter("oid_Nivel_Produto5")).intValue());

        String NM_Estrututura = "";
        if (ed.getOid_Nivel_Produto1() > 0)
            NM_Estrututura += getTableStringValue("NM_Nivel_Produto", "Niveis_Produtos", "oid_nivel_produto = " + ed.getOid_Nivel_Produto1());
        if (ed.getOid_Nivel_Produto2() > 0)
            NM_Estrututura += (NM_Estrututura.length() > 0 ? "/" : "") + getTableStringValue("NM_Nivel_Produto", "Niveis_Produtos", "oid_nivel_produto = " + ed.getOid_Nivel_Produto2());
        if (ed.getOid_Nivel_Produto3() > 0)
            NM_Estrututura += (NM_Estrututura.length() > 0 ? "/" : "") + getTableStringValue("NM_Nivel_Produto", "Niveis_Produtos", "oid_nivel_produto = " + ed.getOid_Nivel_Produto3());
        if (ed.getOid_Nivel_Produto4() > 0)
            NM_Estrututura += (NM_Estrututura.length() > 0 ? "/" : "") + getTableStringValue("NM_Nivel_Produto", "Niveis_Produtos", "oid_nivel_produto = " + ed.getOid_Nivel_Produto4());
        if (ed.getOid_Nivel_Produto5() > 0)
            NM_Estrututura += (NM_Estrututura.length() > 0 ? "/" : "") + getTableStringValue("NM_Nivel_Produto", "Niveis_Produtos", "oid_nivel_produto = " + ed.getOid_Nivel_Produto5());
        ed.setNm_Estrutura_Produto(NM_Estrututura);

        //*** Valida Alteração!
        ed.setCd_Estrutura_Produto(JavaUtil.LFill(ed.getCd_Estrutura_Produto(), 3, true));
        doValidaUpdate(ed.getOid_Estrutura_Produto(),ed.getCd_Estrutura_Produto(),"Estruturas_Produtos","oid_Estrutura_Produto", "CD_Estrutura_Produto");
        if (doExiste("Estruturas_Produtos","oid_Nivel_Produto1 = "+ed.getOid_Nivel_Produto1()+
				   " AND oid_Nivel_Produto2 = "+ed.getOid_Nivel_Produto2()+
				   " AND oid_Nivel_Produto3 = "+ed.getOid_Nivel_Produto3()+
				   " AND oid_Nivel_Produto4 = "+ed.getOid_Nivel_Produto4()+
				   " AND oid_Nivel_Produto5 = "+ed.getOid_Nivel_Produto5()+
				   " AND oid_Estrutura_Produto <> "+ed.getOid_Estrutura_Produto()))
            throw new Mensagens("Essa Estrutura já existe!");
        
        Estrutura_ProdutoRN.altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        String oid_Estrutura_Produto = request.getParameter("oid_Estrutura_Produto");
        if (!doValida(oid_Estrutura_Produto))
            throw new Excecoes("ID Estrutura não informada!");
        
        new Estrutura_ProdutoRN().deleta(new Estrutura_ProdutoED(Integer.parseInt(oid_Estrutura_Produto)));
    }

    public ArrayList Estrutura_Produto_Lista(HttpServletRequest request) throws Excecoes {

        Estrutura_ProdutoED ed = new Estrutura_ProdutoED();

        String oid_Estrutura_Produto = request.getParameter("oid_Estrutura_Produto");
        String cd_Estrutura_Produto = request.getParameter("FT_CD_Estrutura_Produto");
        String nm_Estrutura_Produto = request.getParameter("FT_NM_Estrutura_Produto");
        ed.setDm_Estrutura_Produto(request.getParameter("FT_DM_Estrutura_Produto"));
        
        String oid_Nivel_Produto1 = request.getParameter("oid_Nivel_Produto1");
	    String oid_Nivel_Produto2 = request.getParameter("oid_Nivel_Produto2");
	    String oid_Nivel_Produto3 = request.getParameter("oid_Nivel_Produto3");
	    String oid_Nivel_Produto4 = request.getParameter("oid_Nivel_Produto4");

        if (doValida(oid_Estrutura_Produto))
            ed.setOid_Estrutura_Produto(Integer.parseInt(oid_Estrutura_Produto));
        if (doValida(cd_Estrutura_Produto))
            ed.setCd_Estrutura_Produto(cd_Estrutura_Produto);
        if (doValida(nm_Estrutura_Produto))
            ed.setNm_Estrutura_Produto(nm_Estrutura_Produto);
        if (doValida(oid_Nivel_Produto1))
            ed.setOid_Nivel_Produto1(Integer.parseInt(oid_Nivel_Produto1));
        if (doValida(oid_Nivel_Produto2))
            ed.setOid_Nivel_Produto2(Integer.parseInt(oid_Nivel_Produto2));
        if (doValida(oid_Nivel_Produto3))
            ed.setOid_Nivel_Produto3(Integer.parseInt(oid_Nivel_Produto3));
        if (doValida(oid_Nivel_Produto4))
            ed.setOid_Nivel_Produto4(Integer.parseInt(oid_Nivel_Produto4));

        return new Estrutura_ProdutoRN().lista(ed);

    }

    public Estrutura_ProdutoED getByRecord(HttpServletRequest request) throws Excecoes {

        Estrutura_ProdutoED ed = new Estrutura_ProdutoED();

        String oid_Estrutura_Produto = request.getParameter("oid_Estrutura_Produto");
        String cd_Estrutura_Produto = request.getParameter("FT_CD_Estrutura_Produto");

        if (doValida(oid_Estrutura_Produto))
            ed.setOid_Estrutura_Produto(new Integer(oid_Estrutura_Produto).intValue());
        if (doValida(cd_Estrutura_Produto))
            ed.setCd_Estrutura_Produto(cd_Estrutura_Produto);

        return new Estrutura_ProdutoRN().getByRecord(ed);

    }

    public Estrutura_ProdutoED getByOID_Estrutura_Produto(String oid_Estrutura_Produto) throws Excecoes {

        if (!doValida(oid_Estrutura_Produto))
            throw new Mensagens("ID Estrutura Produto não informada!");
        return new Estrutura_ProdutoRN().getByRecord(new Estrutura_ProdutoED(Integer.parseInt(oid_Estrutura_Produto)));
    }

    public Estrutura_ProdutoED getByCD_Estrutura_Produto(String cd_Estrutura_Produto) throws Excecoes {

        Estrutura_ProdutoED ed = new Estrutura_ProdutoED();

        if (doValida(cd_Estrutura_Produto))
            ed.setCd_Estrutura_Produto(cd_Estrutura_Produto);
        return new Estrutura_ProdutoRN().getByRecord(ed);
    }
}