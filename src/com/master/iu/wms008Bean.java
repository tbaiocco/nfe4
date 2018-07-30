package com.master.iu;

/**
 * Título: wms008Bean Descrição: Notas Fiscais - Bean Data da criação: 11/2003
 * Atualizado em: 03/2004 Empresa: ÊxitoLogística Mastercom Autor: Carlos
 * Eduardo de Holleben
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Modelo_Nota_FiscalED;
import com.master.rn.Modelo_Nota_FiscalRN;

import com.master.ed.WMS_Nota_FiscalED;
import com.master.rn.WMS_Nota_FiscalRN;

import com.master.root.ClienteBean;
import com.master.util.Excecoes;

public class wms008Bean {

    private ArrayList lista = null;

    public WMS_Nota_FiscalED inclui(HttpServletRequest request) throws Excecoes {
    	
        WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
        Modelo_Nota_FiscalRN ModeloNotaFiscalRN = new Modelo_Nota_FiscalRN();
        WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

        ed.setOid_modelo_nota_fiscal(Long.parseLong(request.getParameter("oid_Modelo")));
        Modelo_Nota_FiscalED Modelo = new Modelo_Nota_FiscalED();
        Modelo.setOid_Modelo_Nota_Fiscal(Integer.parseInt(request.getParameter("oid_Modelo")));
        Modelo = ModeloNotaFiscalRN.getByRecord(Modelo);
        ed.setOid_natureza_operacao(Long.parseLong(request.getParameter("oid_Natureza_Operacao")));
        ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
        ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
        ed.setOid_pessoa_transportador(request.getParameter("oid_Pessoa_Transportador"));
        ed.setDt_entrada(request.getParameter("FT_DT_Entrada"));
        ed.setHr_entrada(request.getParameter("FT_HR_Entrada"));

        ed.setOID_Unidade_Contabil(Long.parseLong(request.getParameter("oid_Unidade_Contabil")));
        ed.setOID_Unidade_Fiscal(Long.parseLong(request.getParameter("oid_Unidade_Fiscal")));
        ed.setOID_Unidade(Long.parseLong(request.getParameter("oid_Unidade")));
        ed.setOID_Empresa(Integer.valueOf(request.getParameter("oid_Empresa")).intValue());
        ed.setCD_Empresa(request.getParameter("FT_CD_Empresa"));
        ed.setNM_Empresa(request.getParameter("FT_NM_Empresa"));
        ed.setDM_Gerado("N");

        //Valor do ICMS
        double Cvalor = 0;
        ed.setVl_icms(Cvalor);

        //Valor do Total_Produtos
        ed.setVL_Total_Produtos(0);

        //Valor do ICMS_Substituicao
        ed.setVL_ICMS_Substituicao(Cvalor);

        //Valor do Base_Calculo_ICMS_Substituicao
        ed.setVL_Base_Calculo_ICMS_Substituicao(Cvalor);

        //Valor do Base_Calculo_ICMS
        ed.setVL_Base_Calculo_ICMS(Cvalor);

        //Valor do INSS
        ed.setVl_inss(Cvalor);

        //Valor do PIS
        ed.setVL_PIS(Cvalor);

        //Valor do CSLL
        ed.setVL_CSLL(Cvalor);

        //Valor do Cofins
        ed.setVL_Cofins(Cvalor);

        //Valor do IPI
        ed.setVl_ipi(Cvalor);

        //Valor do IRRF
        ed.setVl_irrf(Cvalor);

        //Valor do ISQN
        ed.setVl_isqn(Cvalor);

        //Valor do Frete
        ed.setVl_total_frete(Cvalor);

        //Valor do Seguro
        ed.setVl_total_seguro(Cvalor);

        //Valor do Despesas
        ed.setVl_total_despesas(Cvalor);

        //Valor do Liquido da Nota Fiscal
        ed.setVl_liquido_nota_fiscal(Cvalor);

        //Valor do Descontos
        ed.setVl_descontos(Cvalor);

        //Valor do Servico
        ed.setVL_Servico(Cvalor);

        ed.setDm_observacao("");
        ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_TipoNota"));
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        if (String.valueOf(NR_Nota_Fiscal) != null && !String.valueOf(NR_Nota_Fiscal).equals("") && !String.valueOf(NR_Nota_Fiscal).equals("null")) {
            ed.setNr_nota_fiscal(Long.parseLong(request.getParameter("FT_NR_Nota_Fiscal")));
        } else {
            ed.setNr_nota_fiscal(0);
        }

        ed.setVl_nota_fiscal(Cvalor);

        return WMS_Nota_FiscalRN.inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
        WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

        ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
        ed.setOid_modelo_nota_fiscal(Long.parseLong(request.getParameter("oid_Modelo")));
        ed.setOid_natureza_operacao(Long.parseLong(request.getParameter("oid_Natureza_Operacao")));
        ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
        ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
        ed.setOid_pessoa_transportador(request.getParameter("oid_Pessoa_Transportador"));
        ed.setVL_Servico(Double.parseDouble(request.getParameter("FT_VL_Servico")));
        ed.setDt_entrada(request.getParameter("FT_DT_Entrada"));
        ed.setHr_entrada(request.getParameter("FT_HR_Entrada"));
        ed.setOID_Unidade_Contabil(Long.parseLong(request.getParameter("oid_Unidade_Contabil")));
        ed.setOID_Unidade_Fiscal(Long.parseLong(request.getParameter("oid_Unidade_Fiscal")));
        ed.setOID_Unidade(Long.parseLong(request.getParameter("oid_Unidade")));
        ed.setOID_Empresa(Integer.valueOf(request.getParameter("oid_Empresa")).intValue());
        ed.setCD_Empresa(request.getParameter("FT_CD_Empresa"));
        ed.setNM_Empresa(request.getParameter("FT_NM_Empresa"));
        if (request.getParameter("FT_NR_Quantidade") != null && !request.getParameter("FT_NR_Quantidade").equals("") && !request.getParameter("FT_NR_Quantidade").equals("null"))
            ed.setNR_Quantidade(Integer.valueOf(request.getParameter("FT_NR_Quantidade")).intValue());
        else
            ed.setNR_Quantidade(0);
        ed.setDM_Frete(request.getParameter("FT_DM_Frete"));
        ed.setNM_Especie(request.getParameter("FT_NM_Especie"));
        ed.setNM_Marca(request.getParameter("FT_NM_Marca"));
        ed.setNR_Placa(request.getParameter("FT_NR_Placa"));
        ed.setNR_Numero(request.getParameter("FT_NR_Numero"));

        String valor = request.getParameter("FT_VL_ICMS");
        double Cvalor = 0;

        if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
            Cvalor = 0;
        else {
            Cvalor = new Double(valor).doubleValue();
        }

        ed.setVl_icms(Cvalor);

        //Valor do Total_Produtos
        valor = request.getParameter("FT_VL_Total_Produtos");

        if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
            Cvalor = 0;
        else {
            Cvalor = new Double(valor).doubleValue();
        }

        ed.setVL_Total_Produtos(Cvalor);

        //Valor do ICMS_Substituicao
        valor = request.getParameter("FT_VL_ICMS_Substituicao");

        if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
            Cvalor = 0;
        else {
            Cvalor = new Double(valor).doubleValue();
        }

        ed.setVL_ICMS_Substituicao(Cvalor);

        //Valor do Base_Calculo_ICMS_Substituicao
        valor = request.getParameter("FT_VL_Base_Calculo_ICMS_Substituicao");

        if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
            Cvalor = 0;
        else {
            Cvalor = new Double(valor).doubleValue();
        }

        ed.setVL_Base_Calculo_ICMS_Substituicao(Cvalor);

        //Valor do Base_Calculo_ICMS
        valor = request.getParameter("FT_VL_Base_Calculo_ICMS");

        if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
            Cvalor = 0;
        else {
            Cvalor = new Double(valor).doubleValue();
        }

        ed.setVL_Base_Calculo_ICMS(Cvalor);

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

        //Valor do PIS
        valor = request.getParameter("FT_VL_PIS");

        if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
            Cvalor = 0;
        else {
            Cvalor = new Double(valor).doubleValue();
        }
        ed.setVL_PIS(Cvalor);

        //Valor do CSLL
        valor = request.getParameter("FT_VL_CSLL");

        if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
            Cvalor = 0;
        else {
            Cvalor = new Double(valor).doubleValue();
        }
        ed.setVL_CSLL(Cvalor);

        //Valor do Cofins
        valor = request.getParameter("FT_VL_Cofins");

        if ((valor.equals("null")) || (valor.equals("")) || (valor == null))
            Cvalor = 0;
        else {
            Cvalor = new Double(valor).doubleValue();
        }
        ed.setVL_Cofins(Cvalor);

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

        ed.setDm_observacao(request.getParameter("ft_mm_obs"));
        ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_TipoNota"));
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

        WMS_Nota_FiscalRN.altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        try {
            WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
            WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

            ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));

            WMS_Nota_FiscalRN.deleta(ed);
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

    public ArrayList lista(HttpServletRequest request, String orderby) throws Excecoes {

        WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();
        if (request.getParameter("FT_NR_Nota_Fiscal") != null && !request.getParameter("FT_NR_Nota_Fiscal").equals(""))
            ed.setNr_nota_fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());

        if (request.getParameter("FT_NM_Serie") != null && !request.getParameter("FT_NM_Serie").equals(""))
            ed.setNm_serie(request.getParameter("FT_NM_Serie"));

        if (request.getParameter("FT_DT_Emissao") != null && !request.getParameter("FT_DT_Emissao").equals(""))
            ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));

        if (request.getParameter("oid_Pessoa_Destinatario") != null && !request.getParameter("oid_Pessoa_Destinatario").equals(""))
            ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));

        if (request.getParameter("oid_Pessoa_Transportador") != null && !request.getParameter("oid_Pessoa_Transportador").equals(""))
            ed.setOid_pessoa_transportador(request.getParameter("oid_Pessoa_Transportador"));

        if (request.getParameter("FT_CD_Situacao") != null && !request.getParameter("FT_CD_Situacao").equals(""))
            ed.setDm_finalizado(request.getParameter("FT_CD_Situacao"));

        if (request.getParameter("oid_Pessoa_Remetente") != null && !request.getParameter("oid_Pessoa_Remetente").equals(""))
            ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));

        if (request.getParameter("oid_Unidade") != null && !request.getParameter("oid_Unidade").equals(""))
            ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
        else ed.setOID_Unidade(0);

        this.setLista(new WMS_Nota_FiscalRN().lista(ed, orderby));

        return this.getLista();
    }

    public void setLista(ArrayList array) {
        this.lista = array;
    }

    public ArrayList getLista() {
        return this.lista;
    }

    public WMS_Nota_FiscalED getByRecord(HttpServletRequest request) throws Excecoes {

        WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

        if (request.getParameter("FT_NR_Nota_Fiscal") != null && request.getParameter("FT_NR_Nota_Fiscal").length() > 0) {
            ed.setNr_nota_fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
        }

        if (request.getParameter("oid_Unidade") != null && request.getParameter("oid_Unidade").length() > 0) {
            ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
        }

        String NM_Serie = request.getParameter("FT_NM_Serie");
        if (NM_Serie != null && !NM_Serie.equals("") && !NM_Serie.equals("null")) {
            ed.setNm_serie(request.getParameter("FT_NM_Serie"));
        }

        if (request.getParameter("oid_Pessoa_Remetente") != null && !request.getParameter("oid_Pessoa_Remetente").equals("") && !request.getParameter("oid_Pessoa_Remetente").equals("null")) {
            ed.setOid_pessoa(request.getParameter("oid_Pessoa_Remetente"));
        }

        if (request.getParameter("oid_Pessoa_Destinatario") != null && !request.getParameter("oid_Pessoa_Destinatario").equals("") && !request.getParameter("oid_Pessoa_Destinatario").equals("null")) {
            ed.setOid_pessoa_destinatario(request.getParameter("oid_Pessoa_Destinatario"));
        }

        if (request.getParameter("oid_Pessoa_Transportador") != null && !request.getParameter("oid_Pessoa_Transportador").equals("")
                && !request.getParameter("oid_Pessoa_Transportador").equals("null")) {
            ed.setOid_pessoa_transportador(request.getParameter("oid_Pessoa_Transportador"));
        }
        if (request.getParameter("FT_DT_Emissao") != null && !request.getParameter("FT_DT_Emissao").equals("") && !request.getParameter("FT_DT_Emissao").equals("null")) {
            ed.setDt_emissao(request.getParameter("FT_DT_Emissao"));
        }

        if (request.getParameter("oid_Modelo") != null && !request.getParameter("oid_Modelo").equals("") && !request.getParameter("oid_Modelo").equals("null")) {
            ed.setOid_modelo_nota_fiscal(Long.parseLong(request.getParameter("oid_Modelo")));
        }

        return new WMS_Nota_FiscalRN().getByRecord(ed);

    }

    public WMS_Nota_FiscalED getByOid(String oid_Nota) throws Excecoes {

        WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

        ed.setOid_nota_fiscal(oid_Nota);

        return new WMS_Nota_FiscalRN().getByRecord(ed);

    }

    public byte[] geraRelatorioNotaFiscal(HttpServletRequest req, HttpServletResponse Response) throws Excecoes {
        WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

        ed.setOid_nota_fiscal(req.getParameter("oid_Nota_Fiscal"));

        // ed.setNr_nota_fiscal( Long.valueOf( req.getParameter(
        // "FT_NR_Nota_Fiscal" ) ).longValue() );

        WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
        byte[] b = WMS_Nota_FiscalRN.geraRelatorioNotaFiscal(ed);

        return b;
    }

    public long calcula_Quantidade_Itens(WMS_Nota_FiscalED ed) throws Excecoes {
        WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
        long b = WMS_Nota_FiscalRN.calcula_Quantidade_Itens(ed);

        return b;
    }

    public void atualiza_Quantidade_Itens(WMS_Nota_FiscalED ed) throws Excecoes {
        try {
            WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
            WMS_Nota_FiscalRN.atualiza_Quantidade_Itens(ed);
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao alterar");
            excecoes.setMetodo("atualiza_Quantidade_Itens(WMS_Nota_FiscalED ed)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public double calcula_Total_Produtos(WMS_Nota_FiscalED ed) throws Excecoes {
        WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
        double b = WMS_Nota_FiscalRN.calcula_Total_Produtos(ed);

        return b;
    }

    public double calcula_Total_Nota(WMS_Nota_FiscalED ed) throws Excecoes {
        WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
        double b = WMS_Nota_FiscalRN.calcula_Total_Nota(ed);

        return b;
    }

    public void atualiza_Total_Produtos(WMS_Nota_FiscalED ed) throws Excecoes {
        try {
            WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
            WMS_Nota_FiscalRN.atualiza_Total_Produtos(ed);
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao alterar");
            excecoes.setMetodo("atualiza_Total_Produtos(WMS_Nota_FiscalED ed)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void atualiza_Total_Nota(WMS_Nota_FiscalED ed) throws Excecoes {
        try {
            WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
            WMS_Nota_FiscalRN.atualiza_Total_Nota(ed);
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao alterar");
            excecoes.setMetodo("atualiza_Total_Nota(WMS_Nota_FiscalED ed)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void cancela_Nota(WMS_Nota_FiscalED ed) throws Excecoes {
        try {
            WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
            WMS_Nota_FiscalRN.cancela_Nota(ed);
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao Cancelar Nota!");
            excecoes.setMetodo("cancela_Nota(WMS_Nota_FiscalED ed)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void atualiza_Notas(HttpServletRequest req) throws Excecoes {
        try {
            WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();
            ed.setOid_nota_fiscal(req.getParameter("oid_Nota_Fiscal"));
            WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
            WMS_Nota_FiscalRN.atualiza_Notas(ed);
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao alterar");
            excecoes.setMetodo("atualiza_Notas(HttpServletRequest req)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void atualiza_DM_Gerado(HttpServletRequest req) throws Excecoes {
        try {
            WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

            ed.setOid_nota_fiscal(req.getParameter("oid_Nota_Fiscal"));

            WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
            WMS_Nota_FiscalRN.atualiza_DM_Gerado(ed);
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao alterar");
            excecoes.setMetodo("atualiza_DM_Gerado(HttpServletRequest req)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public boolean isImpresso(HttpServletRequest req) throws Excecoes {
        WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

        ed.setOid_nota_fiscal(req.getParameter("oid_Nota_Fiscal"));

        WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
        boolean b = WMS_Nota_FiscalRN.isImpresso(ed);

        return b;
    }

    public boolean isCancelada(HttpServletRequest req) throws Excecoes {
        WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

        ed.setOid_nota_fiscal(req.getParameter("oid_Nota_Fiscal"));

        WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
        boolean b = WMS_Nota_FiscalRN.isCancelada(ed);

        return b;
    }

    public void verificaItens(HttpServletRequest req) throws Excecoes {
        try {
            WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
            WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

            ed.setOID_Unidade(new Long(req.getParameter("oid_Unidade")).longValue());
            ed.setOid_pessoa(req.getParameter("oid_Pessoa_Remetente"));

            if (req.getParameter("FT_DT_Emissao_Inicial") != null && !req.getParameter("FT_DT_Emissao_Inicial").equals(""))
                ed.setDt_emissao(req.getParameter("FT_DT_Emissao_Inicial"));

            if (req.getParameter("FT_DT_Emissao_Final") != null && !req.getParameter("FT_DT_Emissao_Final").equals(""))
                ed.setDt_emissao_final(req.getParameter("FT_DT_Emissao_Final"));

            ed.setDM_Gerado("S");

            WMS_Nota_FiscalRN.verificaItens(ed);
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao alterar");
            excecoes.setMetodo("verificaItens()");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void itens_Nao_Encontrados(HttpServletRequest req) throws Excecoes {
        try {
            WMS_Nota_FiscalRN WMS_Nota_FiscalRN = new WMS_Nota_FiscalRN();
            WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();

            ed.setOID_Unidade(new Long(req.getParameter("oid_Unidade")).longValue());
            ed.setOid_pessoa(req.getParameter("oid_Pessoa_Remetente"));

            if (req.getParameter("FT_DT_Emissao_Inicial") != null && !req.getParameter("FT_DT_Emissao_Inicial").equals(""))
                ed.setDt_emissao(req.getParameter("FT_DT_Emissao_Inicial"));

            if (req.getParameter("FT_DT_Emissao_Final") != null && !req.getParameter("FT_DT_Emissao_Final").equals(""))
                ed.setDt_emissao_final(req.getParameter("FT_DT_Emissao_Final"));

            ed.setDM_Gerado("N");

            WMS_Nota_FiscalRN.itens_Nao_Encontrados(ed);
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao alterar");
            excecoes.setMetodo("itens_Nao_Encontrados()");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

}