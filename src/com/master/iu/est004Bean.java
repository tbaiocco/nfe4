package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Lote_CompromissoED;
import com.master.ed.ModeloNotaFiscalED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.ed.Posto_CompromissoED;
import com.master.rn.ModeloNotaFiscalRN;
import com.master.rn.Nota_Fiscal_EletronicaRN;
import com.master.util.Excecoes;

public class est004Bean {

    ///### kieling 08072003 NR_despacho
    public Nota_Fiscal_EletronicaED inclui(HttpServletRequest request) throws Excecoes {

        try {
            Nota_Fiscal_EletronicaRN Nota_Fiscal_TransacoesRN = new Nota_Fiscal_EletronicaRN();
            Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();

            //request em cima dos campos dos forms html
            ed.setOid_modelo_nota_fiscal(Long.parseLong(request.getParameter("oid_Modelo")));
            ed.setDM_Contabiliza("N");
            ed.setOid_natureza_operacao(Long.parseLong(request.getParameter("oid_Natureza_Operacao")));
            ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
            ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
            ed.setNm_serie(request.getParameter("FT_NM_Serie"));
            ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));
            ed.setDt_entrada(request.getParameter("FT_DT_Entrada"));
            ed.setHr_entrada(request.getParameter("FT_HR_Entrada"));
            ed.setDm_forma_pagamento(request.getParameter("FT_FRM_PGTO"));
            if (request.getParameter("FT_NR_PARCELAS") != null && !request.getParameter("FT_NR_PARCELAS").equals("")) {
                ed.setNr_parcelas(Long.parseLong(request.getParameter("FT_NR_PARCELAS")));
            }
            ed.setOID_Unidade_Contabil(Long.parseLong(request.getParameter("oid_Unidade_Contabil")));
            ed.setOID_Unidade_Fiscal(Long.parseLong(request.getParameter("oid_Unidade_Fiscal")));

            //Valor do ICMS
            String valor = request.getParameter("FT_VL_ICMS");
            double Cvalor = 0;

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_icms(Cvalor);

            //Valor do INSS
            valor = request.getParameter("FT_VL_INSS");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_inss(Cvalor);

            //Valor do IPI
            valor = request.getParameter("FT_VL_IPI");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_ipi(Cvalor);

            //Valor do IRRF
            valor = request.getParameter("FT_VL_IRRF");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_irrf(Cvalor);

            //Valor do ISQN
            valor = request.getParameter("FT_VL_ISQN");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_isqn(Cvalor);

            //Valor do Frete
            valor = request.getParameter("FT_VL_Frete");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_total_frete(Cvalor);

            //Valor do Seguro
            valor = request.getParameter("FT_VL_Seguro");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_total_seguro(Cvalor);

            //Valor do Despesas
            valor = request.getParameter("FT_VL_Despesas");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_total_despesas(Cvalor);

            //Valor do Despesas
            valor = request.getParameter("FT_VL_LIQUIDO");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_liquido_nota_fiscal(Cvalor);

            //Valor do Descontos
            valor = request.getParameter("FT_VL_Desconto");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_descontos(Cvalor);

            //Valor do Servico
            valor = request.getParameter("FT_VL_Servico");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVL_Servico(Cvalor);

            //Valor do PIS
            valor = request.getParameter("FT_VL_PIS");
            Cvalor = 0;

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVL_Pis(Cvalor);
            //Valor do Cofins
            valor = request.getParameter("FT_VL_Cofins");
            Cvalor = 0;

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVL_Cofins(Cvalor);
            //Valor do CSLL
            valor = request.getParameter("FT_VL_CSLL");
            Cvalor = 0;

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVL_Csll(Cvalor);

            ed.setDm_observacao(request.getParameter("ft_mm_obs"));
            ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_TipoNota"));
            String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");

            if (String.valueOf(NR_Nota_Fiscal) != null && !String.valueOf(NR_Nota_Fiscal).equals("") && !String.valueOf(NR_Nota_Fiscal).equals("null")) {
                ed.setNr_nota_fiscal(Long.parseLong(request.getParameter("FT_NR_Nota_Fiscal")));
            } else {
                ed.setNr_nota_fiscal(0);
            }
            String NR_Volumes = request.getParameter("FT_NR_Volumes");
            if (String.valueOf(NR_Volumes) != null && !String.valueOf(NR_Volumes).equals("") && !String.valueOf(NR_Volumes).equals("null")) {
                ed.setNr_volumes(Long.parseLong(request.getParameter("FT_NR_Volumes")));
            }

            String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
            if (String.valueOf(VL_Nota_Fiscal) != null && !String.valueOf(VL_Nota_Fiscal).equals("") && !String.valueOf(VL_Nota_Fiscal).equals("null")) {
                ed.setVl_nota_fiscal(Double.parseDouble(request.getParameter("FT_VL_Nota_Fiscal")));
            } else
                ed.setVl_nota_fiscal(0);

            if (ed.getDm_tipo_nota_fiscal().equals("0") || ed.getDm_tipo_nota_fiscal().equals("1") || ed.getDm_tipo_nota_fiscal().equals("P"))
                ed.setDm_finalizado("S");

            return Nota_Fiscal_TransacoesRN.inclui(ed);
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
            Nota_Fiscal_EletronicaRN Nota_Fiscal_TransacoesRN = new Nota_Fiscal_EletronicaRN();
            Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();

            ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
            ed.setOid_modelo_nota_fiscal(Long.parseLong(request.getParameter("oid_Modelo")));
            ed.setOid_natureza_operacao(Long.parseLong(request.getParameter("oid_Natureza_Operacao")));
            ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
            ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
            ed.setNm_serie(request.getParameter("FT_NM_Serie"));
            ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));
            ed.setVL_Servico(Double.parseDouble(request.getParameter("FT_VL_Servico")));
            ed.setDt_entrada(request.getParameter("FT_DT_Entrada"));
            ed.setHr_entrada(request.getParameter("FT_HR_Entrada"));
            ed.setDm_forma_pagamento(request.getParameter("FT_FRM_PGTO"));
            ed.setNr_parcelas(Long.parseLong(request.getParameter("FT_NR_PARCELAS")));
            ed.setOID_Unidade_Contabil(Long.parseLong(request.getParameter("oid_Unidade_Contabil")));
            ed.setOID_Unidade_Fiscal(Long.parseLong(request.getParameter("oid_Unidade_Fiscal")));
            String valor = request.getParameter("FT_VL_ICMS");
            // System.out.println("----------------->>>>>>> " + request.getParameter("FT_DM_TipoNota"));
            double Cvalor = 0;

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_icms(Cvalor);

            //Valor do INSS
            valor = request.getParameter("FT_VL_INSS");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_inss(Cvalor);

            //Valor do IPI
            valor = request.getParameter("FT_VL_IPI");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_ipi(Cvalor);

            //Valor do IRRF
            valor = request.getParameter("FT_VL_IRRF");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_irrf(Cvalor);

            //Valor do ISQN
            valor = request.getParameter("FT_VL_ISQN");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_isqn(Cvalor);

            //Valor do Frete
            valor = request.getParameter("FT_VL_Frete");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_total_frete(Cvalor);

            //Valor do Seguro
            valor = request.getParameter("FT_VL_Seguro");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_total_seguro(Cvalor);

            //Valor do Despesas
            valor = request.getParameter("FT_VL_Despesas");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_total_despesas(Cvalor);

            //Valor do Despesas
            valor = request.getParameter("FT_VL_LIQUIDO");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_liquido_nota_fiscal(Cvalor);

            //Valor do Descontos
            valor = request.getParameter("FT_VL_Desconto");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_descontos(Cvalor);

            //Valor do PIS
            valor = request.getParameter("FT_VL_PIS");
            Cvalor = 0;

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVL_Pis(Cvalor);
            //Valor do Cofins
            valor = request.getParameter("FT_VL_Cofins");
            Cvalor = 0;

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVL_Cofins(Cvalor);
            //Valor do CSLL
            valor = request.getParameter("FT_VL_CSLL");
            Cvalor = 0;

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVL_Csll(Cvalor);

            ed.setDm_observacao(request.getParameter("ft_mm_obs"));
            ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_TipoNota"));
            String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
            if (String.valueOf(NR_Nota_Fiscal) != null && !String.valueOf(NR_Nota_Fiscal).equals("") && !String.valueOf(NR_Nota_Fiscal).equals("null")) {
                ed.setNr_nota_fiscal(Long.parseLong(request.getParameter("FT_NR_Nota_Fiscal")));
            } else {
                ed.setNr_nota_fiscal(0);
            }

            String NR_Volumes = request.getParameter("FT_NR_Volumes");
            if (String.valueOf(NR_Volumes) != null && !String.valueOf(NR_Volumes).equals("") && !String.valueOf(NR_Volumes).equals("null")) {
                ed.setNr_volumes(Long.parseLong(request.getParameter("FT_NR_Volumes")));
            }

            String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
            if (String.valueOf(VL_Nota_Fiscal) != null && !String.valueOf(VL_Nota_Fiscal).equals("") && !String.valueOf(VL_Nota_Fiscal).equals("null")) {
                ed.setVl_nota_fiscal(Double.parseDouble(request.getParameter("FT_VL_Nota_Fiscal")));
            } else
                ed.setVl_nota_fiscal(0);

            // System.out.println("ed.getDm_tipo_nota_fiscal() = " + ed.getDm_tipo_nota_fiscal());
            if (ed.getDm_tipo_nota_fiscal().equals("0") || ed.getDm_tipo_nota_fiscal().equals("1") || ed.getDm_tipo_nota_fiscal().equals("P"))
                ed.setDm_finalizado("S");

            // System.out.println("Vai chamar Nota_Fiscal_TransacoesRN.altera");
            Nota_Fiscal_TransacoesRN.altera(ed);
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao alterar");
            excecoes.setMetodo("altera");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        try {
            Nota_Fiscal_EletronicaRN Nota_Fiscal_TransacoesRN = new Nota_Fiscal_EletronicaRN();
            Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();

            ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
            ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_TipoNota"));

            Nota_Fiscal_TransacoesRN.deleta(ed, false);
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

    public void finaliza(HttpServletRequest request) throws Exception {

        double Cvalor = 0;
        String valor = null;
        try {
            Nota_Fiscal_EletronicaRN Nota_Fiscal_TransacoesRN = new Nota_Fiscal_EletronicaRN();
            Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();

            ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
            ed.setOid_natureza_operacao(Long.parseLong(request.getParameter("oid_Natureza_Operacao")));
            ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
            ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
            ed.setNm_serie(request.getParameter("FT_NM_Serie"));
            ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));
            ed.setDt_entrada(request.getParameter("FT_DT_Entrada"));
            ed.setHr_entrada(request.getParameter("FT_HR_Entrada"));
            ed.setNr_parcelas(Long.parseLong(request.getParameter("FT_NR_PARCELAS")));
            ed.setDm_forma_pagamento(request.getParameter("FT_FRM_PGTO"));
            ed.setOID_Unidade_Contabil(Long.parseLong(request.getParameter("oid_Unidade_Contabil")));
            ed.setOID_Unidade_Fiscal(Long.parseLong(request.getParameter("oid_Unidade_Fiscal")));

            ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_TipoNota"));

            valor = request.getParameter("FT_VL_LIQUIDO");

            if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
                Cvalor = 0;
            else {
                Cvalor = new Double(valor).doubleValue();
            }

            ed.setVl_descontos(Double.parseDouble(request.getParameter("FT_VL_Desconto")));
            ed.setVl_liquido_nota_fiscal(Cvalor);

            String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
            if (String.valueOf(NR_Nota_Fiscal) != null && !String.valueOf(NR_Nota_Fiscal).equals("") && !String.valueOf(NR_Nota_Fiscal).equals("null")) {
                ed.setNr_nota_fiscal(Long.parseLong(request.getParameter("FT_NR_Nota_Fiscal")));
            } else {
                ed.setNr_nota_fiscal(0);
            }

            String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");

            if (String.valueOf(VL_Nota_Fiscal) != null && !String.valueOf(VL_Nota_Fiscal).equals("") && !String.valueOf(VL_Nota_Fiscal).equals("null")) {
                ed.setVl_nota_fiscal(Double.parseDouble(request.getParameter("FT_VL_Nota_Fiscal")));
            } else
                ed.setVl_nota_fiscal(0);
            Nota_Fiscal_TransacoesRN.finalizaNF_Entrada(ed);
        } catch (Excecoes exc) {
            throw exc;
        }
    }

    ///### kieling 08072003 NR_despacho
    public ArrayList Nota_Fiscal_Lista(HttpServletRequest request) throws Excecoes {

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();
        //// System.out.println("Passo 1");
        String OID_Unidade = request.getParameter("oid_Unidade");
        if (String.valueOf(OID_Unidade) != null && !String.valueOf(OID_Unidade).equals("") && !String.valueOf(OID_Unidade).equals("null")) {
            ed.setOID_Unidade_Contabil(new Long(OID_Unidade).longValue());
        }
        if (!request.getParameter("FT_NR_Nota_Fiscal").equals(""))
            ed.setNr_nota_fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
        //// System.out.println("Passo 2");
        if (!request.getParameter("FT_NM_Serie").equals(""))
            ed.setNm_serie(request.getParameter("FT_NM_Serie"));
        //// System.out.println("Passo 3");
        if (!request.getParameter("FT_DT_Emissao").equals(""))
            ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));
        //// System.out.println("Passo 4"+request.getParameter("oid_Modelo"));
        if ((!request.getParameter("oid_Modelo").equals("")) && (!request.getParameter("oid_Modelo").equals("undefined")))
            ed.setOid_modelo_nota_fiscal(Long.parseLong(request.getParameter("oid_Modelo")));
        //// System.out.println("Passo 5");
        ed.setDm_finalizado(request.getParameter("FT_CD_Situacao"));
        //// System.out.println("Passo 6");
        if (!request.getParameter("oid_Pessoa_Remetente").equals(""))
            ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));

        return new Nota_Fiscal_EletronicaRN().lista(ed);
    }

    public Nota_Fiscal_EletronicaED getByRecord(HttpServletRequest request) throws Excecoes {

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();

        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        if (NR_Nota_Fiscal != null && NR_Nota_Fiscal.length() > 0) {
            ed.setNr_nota_fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
        }
        String NM_Serie = request.getParameter("FT_NM_Serie");
        if (NM_Serie != null && !NM_Serie.equals("") && !NM_Serie.equals("null")) {
            ed.setNm_serie(request.getParameter("FT_NM_Serie"));
        }

        ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
        ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));

        ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));

        if (request.getParameter("oid_Modelo") != null && !request.getParameter("oid_Modelo").equals("")) {
            ed.setOid_modelo_nota_fiscal(Long.parseLong(request.getParameter("oid_Modelo")));
        }

        return new Nota_Fiscal_EletronicaRN().getByRecord(ed);

    }

    public Nota_Fiscal_EletronicaED getByOid(String oid_Nota) throws Excecoes {

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();

        ed.setOid_nota_fiscal(oid_Nota);

        return new Nota_Fiscal_EletronicaRN().getByRecord(ed);

    }

    public boolean inclui_Lancamentos(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        Nota_Fiscal_EletronicaED auxiliar = (Nota_Fiscal_EletronicaED) ed;
        ModeloNotaFiscalED modelo = new ModeloNotaFiscalED();
        ModeloNotaFiscalRN modelorn = new ModeloNotaFiscalRN();
        modelo.setOid_Modelo_Nota_Fiscal(ed.getOid_modelo_nota_fiscal());

        modelo = modelorn.getByRecord(modelo);

        auxiliar.setDM_Contabiliza(modelo.getDM_Aceita_Contabilizacao());

        return new Nota_Fiscal_EletronicaRN().inclui_Lancamento(auxiliar);
    }

    public boolean inclui_Parcelamentos(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        Nota_Fiscal_EletronicaED auxiliar = (Nota_Fiscal_EletronicaED) ed;
        ModeloNotaFiscalED modelo = new ModeloNotaFiscalED();
        ModeloNotaFiscalRN modelorn = new ModeloNotaFiscalRN();
        modelo.setOid_Modelo_Nota_Fiscal(ed.getOid_modelo_nota_fiscal());
        modelo = modelorn.getByRecord(modelo);

        auxiliar.setDM_Financeiro(modelo.getDM_Aceita_Financeiro());
        return new Nota_Fiscal_EletronicaRN().inclui_Parcelamento(auxiliar);
    }

    public void reabre(HttpServletRequest request) throws Excecoes {

        try {
            Nota_Fiscal_EletronicaRN Nota_Fiscal_TransacoesRN = new Nota_Fiscal_EletronicaRN();
            Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();

            ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
            ed.setNr_nota_fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());

            Nota_Fiscal_TransacoesRN.reabre(ed);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao reabri");
            excecoes.setMetodo("reabre");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public boolean inclui_Lancamentos_Pagamento_Compromisso(Lote_CompromissoED ed) throws Excecoes {

        Lote_CompromissoED auxiliar = (Lote_CompromissoED) ed;
        ModeloNotaFiscalED modelo = new ModeloNotaFiscalED();
        ModeloNotaFiscalRN modelorn = new ModeloNotaFiscalRN();
        modelo.setOid_Modelo_Nota_Fiscal(ed.getOID_Modelo());
        modelo = modelorn.getByRecord(modelo);
        auxiliar.setDM_Contabiliza("S");
        return new Nota_Fiscal_EletronicaRN().inclui_Lancamentos_Pagamento_Compromisso(auxiliar);

    }

    public void estapaFinaliza(Nota_Fiscal_EletronicaED ed) throws Exception {

        try {
            Nota_Fiscal_EletronicaRN Nota_Fiscal_TransacoesRN = new Nota_Fiscal_EletronicaRN();
            Nota_Fiscal_TransacoesRN.finalizaNF_Entrada(ed);
        } catch (Excecoes exc) {
            throw exc;
        }
    }

    public boolean inclui_Lancamentos_Lote_Posto(Posto_CompromissoED ed) throws Excecoes {

        Posto_CompromissoED auxiliar = (Posto_CompromissoED) ed;
        ModeloNotaFiscalED modelo = new ModeloNotaFiscalED();
        ModeloNotaFiscalRN modelorn = new ModeloNotaFiscalRN();
        modelo.setOid_Modelo_Nota_Fiscal(ed.getOID_Modelo());
        modelo = modelorn.getByRecord(modelo);
        auxiliar.setDM_Contabiliza("S");
        return new Nota_Fiscal_EletronicaRN().inclui_Lancamentos_Lote_Posto(auxiliar);

    }

}