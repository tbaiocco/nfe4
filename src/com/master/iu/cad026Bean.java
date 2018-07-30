package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Natureza_OperacaoED;
import com.master.rn.Natureza_OperacaoRN;
import com.master.root.CidadeBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class cad026Bean extends JavaUtil {
    
    public Natureza_OperacaoED inclui(HttpServletRequest request) throws Excecoes {

        Natureza_OperacaoED ed = new Natureza_OperacaoED();

        ed.setCd_Natureza_Operacao(request.getParameter("FT_CD_Natureza_Operacao"));
        ed.setCd_CFO_Conhecimento(request.getParameter("FT_CD_CFO_Conhecimento"));
        ed.setNm_Natureza_Operacao(request.getParameter("FT_NM_Natureza_Operacao"));
        ed.setDM_Tipo_Operacao(request.getParameter("FT_DM_Tipo_Operacao"));
        ed.setDm_Tipo_Imposto(request.getParameter("FT_DM_Tipo_Imposto"));

        return new Natureza_OperacaoRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        Natureza_OperacaoED ed = new Natureza_OperacaoED();

        ed.setOid_Natureza_Operacao(new Integer(request.getParameter("oid_Natureza_Operacao")));
        ed.setCd_Natureza_Operacao(request.getParameter("FT_CD_Natureza_Operacao"));
        ed.setNm_Natureza_Operacao(request.getParameter("FT_NM_Natureza_Operacao"));
        ed.setDM_Tipo_Operacao(request.getParameter("FT_DM_Tipo_Operacao"));
        ed.setDm_Tipo_Imposto(request.getParameter("FT_DM_Tipo_Imposto"));
        ed.setCd_CFO_Conhecimento(request.getParameter("FT_CD_CFO_Conhecimento"));

        new Natureza_OperacaoRN().altera(ed);

    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        Natureza_OperacaoED ed = new Natureza_OperacaoED();

        ed.setOid_Natureza_Operacao(new Integer(request.getParameter("oid_Natureza_Operacao")));
        new Natureza_OperacaoRN().deleta(ed);
    }

    public ArrayList Natureza_Operacao_Lista(HttpServletRequest request) throws Excecoes {

        Natureza_OperacaoED ed = new Natureza_OperacaoED();

        String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");
        String cd_Natureza_Operacao = request.getParameter("FT_CD_Natureza_Operacao");
        String nm_Natureza_Operacao = request.getParameter("FT_NM_Natureza_Operacao");
        String DM_Tipo_Operacao = request.getParameter("FT_DM_Tipo_Operacao");

        if (doValida(oid_Natureza_Operacao))
            ed.setOid_Natureza_Operacao(new Integer(oid_Natureza_Operacao));
        if (doValida(cd_Natureza_Operacao))
            ed.setCd_Natureza_Operacao(cd_Natureza_Operacao);
        if (doValida(nm_Natureza_Operacao))
            ed.setNm_Natureza_Operacao(nm_Natureza_Operacao);
        if (doValida(DM_Tipo_Operacao))
            ed.setDM_Tipo_Operacao(DM_Tipo_Operacao);

        return new Natureza_OperacaoRN().lista(ed);

    }   

    public Natureza_OperacaoED getByRecord(HttpServletRequest request) throws Excecoes {

        Natureza_OperacaoED ed = new Natureza_OperacaoED();

        String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");
        String cd_Natureza_Operacao = request.getParameter("FT_CD_Natureza_Operacao");

        if (doValida(oid_Natureza_Operacao)) {
            ed.setOid_Natureza_Operacao(new Integer(oid_Natureza_Operacao));
        }
        if (doValida(cd_Natureza_Operacao)) {
            ed.setCd_Natureza_Operacao(cd_Natureza_Operacao);
        }

        return new Natureza_OperacaoRN().getByRecord(ed);
    }

    public Natureza_OperacaoED getByRecord_Servico(HttpServletRequest request) throws Excecoes {

        Natureza_OperacaoED ed = new Natureza_OperacaoED();

        String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao_Servico");
        String cd_Natureza_Operacao = request.getParameter("FT_CD_Natureza_Operacao_Servico");

        if (doValida(oid_Natureza_Operacao)) {
            ed.setOid_Natureza_Operacao(new Integer(oid_Natureza_Operacao));
        }
        if (doValida(cd_Natureza_Operacao)) {
            ed.setCd_Natureza_Operacao(cd_Natureza_Operacao);
        }

        return new Natureza_OperacaoRN().getByRecord(ed);
    }
    
    public Natureza_OperacaoED getByRecord_Outros(HttpServletRequest request) throws Excecoes {

        Natureza_OperacaoED ed = new Natureza_OperacaoED();

        String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao_Outros");
        String cd_Natureza_Operacao = request.getParameter("FT_CD_Natureza_Operacao_Outros");

        if (doValida(oid_Natureza_Operacao)) {
            ed.setOid_Natureza_Operacao(new Integer(oid_Natureza_Operacao));
        }
        if (doValida(cd_Natureza_Operacao)) {
            ed.setCd_Natureza_Operacao(cd_Natureza_Operacao);
        }

        return new Natureza_OperacaoRN().getByRecord(ed);
    }
    
    public Natureza_OperacaoED getByCD_Natureza_Operacao(HttpServletRequest request) throws Excecoes {

        Natureza_OperacaoED ed = new Natureza_OperacaoED();

        String cd_Natureza_Operacao = request.getParameter("FT_CD_Natureza_Operacao");

        if (doValida(cd_Natureza_Operacao)) {
            ed.setCd_Natureza_Operacao(cd_Natureza_Operacao);
            return new Natureza_OperacaoRN().getByRecord(ed);
        }
        return ed;
    }
    
    public Natureza_OperacaoED getByCD_Natureza_Operacao(String codigo, String dmTipo_Operacao) throws Excecoes {

        if (!doValida(codigo) || !doValida(dmTipo_Operacao))
            return new Natureza_OperacaoED();

        Natureza_OperacaoED ed = new Natureza_OperacaoED();
        ed.setCd_Natureza_Operacao(codigo);
        ed.setDM_Tipo_Operacao(dmTipo_Operacao);
        return new Natureza_OperacaoRN().getByRecord(ed);
    }

    public Natureza_OperacaoED getByCD(String Codigo) throws Excecoes {

        Natureza_OperacaoED ed = new Natureza_OperacaoED();

        if (doValida(Codigo)) {
            ed.setCd_Natureza_Operacao(Codigo);
            return new Natureza_OperacaoRN().getByRecord(ed);
        }
        return ed;
    }

    //*** Busca Código de Classificação 5 ou 6
    public String getCodigoClassificacao(String oid_Unidade, String oid_Cliente) throws Exception {

        if (!doValida(oid_Unidade))
            throw new Excecoes("Unidade não informada!");
        if (!doValida(oid_Cliente))
            throw new Excecoes("Cliente não informada!");

        //*** Estado ORIGEM
        CidadeBean edCidade_Orig = CidadeBean.getByOID(new BancoUtil().getTableIntValue("oid_Cidade", "Pessoas", "oid_Pessoa = '" + oid_Unidade + "'"));
        //*** Estado DESTINO
        CidadeBean edCidade_Dest = CidadeBean.getByOID(new BancoUtil().getTableIntValue("oid_Cidade", "Pessoas", "oid_Pessoa = '" + oid_Cliente + "'"));
        if (!doValida(edCidade_Orig.getCD_Estado()))
            throw new Excecoes("Código do Estado da Unidade não localizado!");
        if (!doValida(edCidade_Dest.getCD_Estado()))
            throw new Excecoes("Código do Estado do Cliente não localizado!");
        //*** Taxa
        return (edCidade_Orig.getCD_Estado().equals(edCidade_Dest.getCD_Estado()) ? "5" : "6");
    }

    public void geraRelatorio(HttpServletRequest request) throws Excecoes {

        new Natureza_OperacaoRN().geraRelatorio(new Natureza_OperacaoED());
    }

    public Natureza_OperacaoED getByOid(String Oid) throws Excecoes {

        if (!doValida(Oid))
            throw new Excecoes("ID Natureza de Operação não informado!");

        return new Natureza_OperacaoRN().getByRecord(new Natureza_OperacaoED(new Integer(Oid)));
    }
}