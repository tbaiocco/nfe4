package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.ModeloNotaFiscalED;
import com.master.rn.ModeloNotaFiscalRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;

public class con012Bean {
    
    Utilitaria util = new Utilitaria();

    public ModeloNotaFiscalED inclui(HttpServletRequest request) throws Excecoes {

        try {
            ModeloNotaFiscalRN ModeloNotaFiscalrn = new ModeloNotaFiscalRN();
            ModeloNotaFiscalED ed = new ModeloNotaFiscalED();
            String sugestao = request.getParameter("FT_CD_Sugestao");
            if ((sugestao == null)) {
                sugestao = "0"; //Quer dizer que não há uma sugestao
            }
            ed.setOid_Sugestao_Contabil(Long.parseLong(sugestao));

            ed.setDM_Aceita_Contabilizacao(request.getParameter("FT_DM_Contabiliza"));
            ed.setDM_Aceita_Escrita(request.getParameter("FT_DM_Escrita"));

            String estoque = request.getParameter("FT_DM_Estoque_Oper");
            if (estoque == null) {
                estoque = "N";
            }
            ed.setDM_Aceita_Estoque(estoque);

            String estoqueimb = request.getParameter("FT_DM_Estoque_Imob");
            if (estoqueimb == null) {
                estoqueimb = "N";
            }
            ed.setDM_Aceita_Imobilizado(estoqueimb);

            String acao_est = request.getParameter("FT_Tipo_Estoque");
            if (acao_est == null) {
                acao_est = "N";
            }
            ed.setDM_Estoque_Soma(acao_est);

            ed.setDM_Aceita_Financeiro(request.getParameter("FT_DM_Financeiro"));

            String icms = request.getParameter("FT_DM_ICMS");
            if ((icms == null) || (icms.equals("")) || (icms.equals("null"))) {
                icms = "N";
            }
            ed.setDM_ICMS(icms);

            String inss = request.getParameter("FT_DM_INSS");
            if ((inss == null) || (inss.equals("")) || (inss.equals("null"))) {
                inss = "N";
            }
            ed.setDM_INSS(inss);

            String ipi = request.getParameter("FT_DM_IPI");
            if ((ipi == null) || (ipi.equals("")) || (ipi.equals("null"))) {
                ipi = "N";
            }
            ed.setDM_IPI(ipi);

            String irf = request.getParameter("FT_DM_IRRF");
            if ((irf == null) || (irf.equals("")) || (irf.equals("null"))) {
                irf = "N";
            }
            ed.setDM_IRRF(irf);

            String isqn = request.getParameter("FT_DM_ISQN");
            if ((isqn == null) || (isqn.equals("")) || (isqn.equals("null"))) {
                isqn = "N";
            }
            ed.setDM_ISQN(isqn);

            ed.setNM_Nota_Fiscal(request.getParameter("FT_NM_Modelo"));
            ed.setCD_Nota_Fiscal(request.getParameter("FT_CD_Modelo"));

            return ModeloNotaFiscalrn.inclui(ed);
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
            ModeloNotaFiscalRN ModeloNotaFiscalrn = new ModeloNotaFiscalRN();
            ModeloNotaFiscalED ed = new ModeloNotaFiscalED();

            String sugestao = request.getParameter("oid_Sugestao");
            //// System.out.println("sugestao"+sugestao);
            if ((sugestao == null)) {
                sugestao = "0"; //Quer dizer que não há uma sugestao
            }
            ed.setOid_Sugestao_Contabil(Long.parseLong(sugestao));

            ed.setDM_Aceita_Contabilizacao(request.getParameter("FT_DM_Contabiliza"));
            ed.setDM_Aceita_Escrita(request.getParameter("FT_DM_Escrita"));

            String estoque = request.getParameter("FT_DM_Estoque_Oper");
            if (estoque == null) {
                estoque = "N";
            }
            ed.setDM_Aceita_Estoque(estoque);

            String estoqueimb = request.getParameter("FT_DM_Estoque_Imob");
            if (estoqueimb == null) {
                estoqueimb = "N";
            }
            ed.setDM_Aceita_Imobilizado(estoqueimb);

            String acao_est = request.getParameter("FT_Tipo_Estoque");
            if (acao_est == null) {
                acao_est = "N";
            }
            ed.setDM_Estoque_Soma(acao_est);

            ed.setDM_Aceita_Financeiro(request.getParameter("FT_DM_Financeiro"));

            String icms = request.getParameter("FT_DM_ICMS");
            if ((icms == null) || (icms.equals("")) || (icms.equals("null"))) {
                icms = "N";
            }
            ed.setDM_ICMS(icms);

            String inss = request.getParameter("FT_DM_INSS");
            if ((inss == null) || (inss.equals("")) || (inss.equals("null"))) {
                inss = "N";
            }
            ed.setDM_INSS(inss);

            String ipi = request.getParameter("FT_DM_IPI");
            if ((ipi == null) || (ipi.equals("")) || (ipi.equals("null"))) {
                ipi = "N";
            }
            ed.setDM_IPI(ipi);

            String irf = request.getParameter("FT_DM_IRPF");
            if ((irf == null) || (irf.equals("")) || (irf.equals("null"))) {
                irf = "N";
            }
            ed.setDM_IRRF(irf);

            String isqn = request.getParameter("FT_DM_ISQN");
            if ((isqn == null) || (isqn.equals("")) || (isqn.equals("null"))) {
                isqn = "N";
            }
            ed.setDM_ISQN(isqn);

            ed.setNM_Nota_Fiscal(request.getParameter("FT_NM_Modelo"));
            ed.setCD_Nota_Fiscal(request.getParameter("FT_CD_Modelo"));

            String oid_modelo = request.getParameter("oid_modelo_Nota_Fiscal");
            //// System.out.println("oid_modelo "+oid_modelo);
            ed.setOid_Modelo_Nota_Fiscal(Long.parseLong(oid_modelo));

            ModeloNotaFiscalrn.altera(ed);
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

    public void deleta(HttpServletRequest request) throws Excecoes {

        try {
            ModeloNotaFiscalRN ModeloNotaFiscalrn = new ModeloNotaFiscalRN();
            ModeloNotaFiscalED ed = new ModeloNotaFiscalED();
            String aux = request.getParameter("oid_modelo_Nota_Fiscal");
            ed.setOid_Modelo_Nota_Fiscal(Long.parseLong(aux));

            ModeloNotaFiscalrn.deleta(ed);
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

    public ArrayList ModeloNotaFiscal_Lista(HttpServletRequest request) throws Excecoes {
        ModeloNotaFiscalED ed = new ModeloNotaFiscalED();
        return new ModeloNotaFiscalRN().lista(ed);

    }

    public ModeloNotaFiscalED getByRecord(HttpServletRequest request) throws Excecoes {

        ModeloNotaFiscalED ed = new ModeloNotaFiscalED();
        String oid_ModeloNotaFiscal = request.getParameter("FT_CD_Nota_Fiscal");
        String codigo = request.getParameter("oid_modelo_Nota_Fiscal");

        if (util.doValida(oid_ModeloNotaFiscal)) {
            ed.setOid_Modelo_Nota_Fiscal(new Long(oid_ModeloNotaFiscal).longValue());
        } else if (util.doValida(codigo)) {
            ed.setOid_Modelo_Nota_Fiscal(new Long(codigo).longValue());
        }
        return new ModeloNotaFiscalRN().getByRecord(ed);
    }

    public ModeloNotaFiscalED getByCD_Nota_Fiscal(HttpServletRequest request) throws Excecoes {

        ModeloNotaFiscalED ed = new ModeloNotaFiscalED();
        String CD_Nota_Fiscal = request.getParameter("FT_CD_Nota_Fiscal");

        if (util.doValida(CD_Nota_Fiscal)) {
            ed.setCD_Nota_Fiscal(CD_Nota_Fiscal);
            return new ModeloNotaFiscalRN().getByRecord(ed);
        }
        return ed;
    }

    public ModeloNotaFiscalED getByOid(String oid) throws Excecoes {

        ModeloNotaFiscalED ed = new ModeloNotaFiscalED();

        if (util.doValida(oid)) {
            ed.setOid_Modelo_Nota_Fiscal(Long.parseLong(oid));
        } else
            throw new Excecoes("Parâmetros incorretos!");

        return new ModeloNotaFiscalRN().getByRecord(ed);
    }

    public ArrayList getByNM_Nota_Fiscal(HttpServletRequest request) throws Excecoes {

        ModeloNotaFiscalED ed = new ModeloNotaFiscalED();
        String nm_ModeloNotaFiscal = request.getParameter("FT_NM_Nota_Fiscal");

        ed.setNM_Nota_Fiscal(nm_ModeloNotaFiscal);

        return new ModeloNotaFiscalRN().getByNM_Nota_Fiscal(ed);
    }
}