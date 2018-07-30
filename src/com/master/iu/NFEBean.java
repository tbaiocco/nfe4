package com.master.iu;

import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.master.ed.Lote_CompromissoED;
import com.master.ed.ModeloNotaFiscalED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.ed.Ocorrencia_Nota_FiscalED;
import com.master.ed.Posto_CompromissoED;
import com.master.ed.RelatorioED;
import com.master.rn.ModeloNotaFiscalRN;
import com.master.rn.Nota_Fiscal_EletronicaRN;
import com.master.root.UsuarioBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FileUtil;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.ed.Parametro_FixoED;

public class NFEBean extends BancoUtil {

    public Nota_Fiscal_EletronicaED inclui(HttpServletRequest request) throws Exception {

        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");
        String oid_Natureza_Operacao_Servico = request.getParameter("oid_Natureza_Operacao_Servico");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Pessoa_Remetente = request.getParameter("oid_Pessoa_Remetente");
        String oid_Produto = request.getParameter("oid_Produto");

        String oid_Pessoa_Destinatario = request.getParameter("oid_Pessoa_Destinatario");
        String oid_Pessoa_Consignatario = request.getParameter("oid_Pessoa_Consignatario");
        String oid_Pessoa_Redespacho = request.getParameter("oid_Pessoa_Redespacho");

        String oid_Centro_Custo = request.getParameter("oid_Centro_Custo");
        String oid_Unidade_Contabil = request.getParameter("oid_Unidade_Contabil");
        String oid_Unidade_Fiscal = request.getParameter("oid_Unidade_Fiscal");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String oid_Condicao_Pagamento = request.getParameter("oid_Condicao_Pagamento");
        String oid_Conta = request.getParameter("oid_Conta");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String oid_Veiculo = request.getParameter("oid_Veiculo");
        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");

        String NM_Serie = request.getParameter("FT_NM_Serie");
        String DT_Emissao = request.getParameter("FT_DT_Emissao");
        String DM_Frete = request.getParameter("FT_DM_Frete");
        String DT_Entrega = request.getParameter("FT_DT_Entrega");
        String DT_Entrada = request.getParameter("FT_DT_Entrada");
        String HR_Entrada = request.getParameter("FT_HR_Entrada");
        String FRM_PGTO = request.getParameter("FT_FRM_PGTO");
        String NR_PARCELAS = request.getParameter("FT_NR_PARCELAS");

        String VL_ICMS = request.getParameter("FT_VL_ICMS");
        String PE_Aliquota_ICMS = request.getParameter("FT_PE_Aliquota_ICMS");
        String VL_INSS = request.getParameter("FT_VL_INSS");
        String VL_IPI = request.getParameter("FT_VL_IPI");
        String VL_IRRF = request.getParameter("FT_VL_IRRF");
        String VL_ISQN = request.getParameter("FT_VL_ISQN");
        String VL_Frete = request.getParameter("FT_VL_Frete");
        String VL_Seguro = request.getParameter("FT_VL_Seguro");
        String VL_Despesas = request.getParameter("FT_VL_Despesas");
        String VL_LIQUIDO = request.getParameter("FT_VL_LIQUIDO");
        String VL_Desconto = request.getParameter("FT_VL_Desconto");
        String VL_Desconto_Itens = request.getParameter("FT_VL_Desconto_Itens");
        String VL_Adicional = request.getParameter("FT_VL_Adicional");
        String VL_Diferenca_Aliq_ICMS = request.getParameter("FT_VL_Diferenca_Aliq_ICMS");
        String VL_Servico = request.getParameter("FT_VL_Servico");
        String VL_PIS = request.getParameter("FT_VL_PIS");
        String VL_ICMS_Subst = request.getParameter("FT_VL_ICMS_Subst");
        String VL_CSLL = request.getParameter("FT_VL_CSLL");
        String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
        String DM_Observacao = request.getParameter("FT_DM_Observacao");
        String NM_Complemento = request.getParameter("FT_NM_Complemento");
        String DM_Tipo_Nota_Fiscal = request.getParameter("FT_DM_Tipo_Nota_Fiscal");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        String NR_Itens = request.getParameter("FT_NR_Itens");
        String NR_Peso = request.getParameter("FT_NR_Peso");
        String NR_Peso_Cubado = request.getParameter("FT_NR_Peso_Cubado");
        String NR_Volumes = request.getParameter("FT_NR_Volumes");

        //Pessoa Fornecedor é o destinatário ou o remetente dependendo do tipo de nf
        String oid_Pessoa_Fornecedor="";
        if (doValida(oid_Pessoa_Remetente) && "E".equals(DM_Tipo_Nota_Fiscal)) //Preenchido quando nf Entrada
        	oid_Pessoa_Fornecedor = oid_Pessoa_Remetente;
        if (doValida(oid_Pessoa_Destinatario) && !"E".equals(DM_Tipo_Nota_Fiscal)) //Preenchido quando nf Saída/Devolucao
        	oid_Pessoa_Fornecedor = oid_Pessoa_Destinatario;

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo_nota_fiscal(Long.parseLong(oid_Modelo_Nota_Fiscal));

        if (doValida(oid_Natureza_Operacao))
            ed.setOid_natureza_operacao(Long.parseLong(oid_Natureza_Operacao));
        if (doValida(oid_Produto))
            ed.setOid_produto(Long.parseLong(oid_Produto));
        if (doValida(oid_Natureza_Operacao_Servico))
            ed.setOid_Natureza_Operacao_Servico(Integer.parseInt(oid_Natureza_Operacao_Servico));
        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);

        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (doValida(oid_Pessoa_Destinatario))
            ed.setOid_pessoa_destinatario(oid_Pessoa_Destinatario);
        else ed.setOid_pessoa_destinatario(oid_Pessoa);
        if (doValida(oid_Pessoa_Consignatario))
            ed.setOid_Pessoa_Consignatario(oid_Pessoa_Consignatario);
        if (doValida(oid_Pessoa_Redespacho))
            ed.setOid_Pessoa_Redespacho(oid_Pessoa_Redespacho);
        if (doValida(oid_Pessoa_Fornecedor))
            ed.setOid_pessoa_fornecedor(oid_Pessoa_Fornecedor);
        if (doValida(oid_Centro_Custo))
            ed.setOid_Centro_Custo(new Integer(oid_Centro_Custo));
        if (doValida(oid_Condicao_Pagamento))
            ed.setOid_Condicao_Pagamento(Integer.parseInt(oid_Condicao_Pagamento));
        if (doValida(oid_Conta))
            ed.setOid_Conta(Integer.parseInt(oid_Conta));
        if (doValida(oid_Entregador))
            ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
        if (doValida(oid_Veiculo))
            ed.setOid_Veiculo(oid_Veiculo);
        if (doValida(oid_Tabela_Venda))
            ed.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));

        if (doValida(NM_Serie))
            ed.setNm_serie(NM_Serie);
        else ed.setNm_serie("");
        if (doValida(DT_Emissao))
            ed.setDt_emissao(DT_Emissao);
        if (doValida(DM_Frete))
            ed.setDM_Frete(DM_Frete);
        if (doValida(DT_Entrega))
            ed.setDT_Entrega(DT_Entrega);
        if (doValida(DT_Entrada))
            ed.setDt_entrada(DT_Entrada);
        if (doValida(HR_Entrada))
            ed.setHr_entrada(HR_Entrada);
        if (doValida(FRM_PGTO))
            ed.setDm_forma_pagamento(FRM_PGTO);
        if (doValida(NR_PARCELAS))
            ed.setNr_parcelas(Long.parseLong(NR_PARCELAS));
        if (doValida(oid_Unidade_Contabil))
            ed.setOID_Unidade_Contabil(Long.parseLong(oid_Unidade_Contabil));
        if (doValida(oid_Unidade_Fiscal))
            ed.setOID_Unidade_Fiscal(Long.parseLong(oid_Unidade_Fiscal));
        if (doValida(oid_Unidade))
            ed.setOID_Unidade(Long.parseLong(oid_Unidade));
        //*** Valor do ICMS
        if (doValida(VL_ICMS))
            ed.setVl_icms(Double.parseDouble(VL_ICMS));
        if (doValida(PE_Aliquota_ICMS))
            ed.setPE_Aliquota_ICMS(Double.parseDouble(PE_Aliquota_ICMS));
        //*** Valor do INSS
        if (doValida(VL_INSS))
            ed.setVl_inss(Double.parseDouble(VL_INSS));
        //*** Valor do IPI
        if (doValida(VL_IPI))
            ed.setVl_ipi(Double.parseDouble(VL_IPI));
        //*** Valor do IRRF
        if (doValida(VL_IRRF))
            ed.setVl_irrf(Double.parseDouble(VL_IRRF));
        //*** Valor do ISQN
        if (doValida(VL_ISQN))
            ed.setVl_isqn(Double.parseDouble(VL_ISQN));
        //*** Valor do Frete
        if (doValida(VL_Frete))
            ed.setVl_total_frete(Double.parseDouble(VL_Frete));
        //*** Valor do Seguro
        if (doValida(VL_Seguro))
            ed.setVl_total_seguro(Double.parseDouble(VL_Seguro));
        //*** Valor do Despesas
        if (doValida(VL_Despesas))
            ed.setVl_total_despesas(Double.parseDouble(VL_Despesas));
        //*** Valor Liquido
        if (doValida(VL_LIQUIDO))
            ed.setVl_liquido_nota_fiscal(Double.parseDouble(VL_LIQUIDO));
        //*** Valor do Descontos
        if (doValida(VL_Desconto))
            ed.setVl_descontos(Double.parseDouble(VL_Desconto));
        //*** Valor do Descontos Itens
        if (doValida(VL_Desconto_Itens))
            ed.setVL_Desconto_Itens(Double.parseDouble(VL_Desconto_Itens));
        //*** Valor Adicional
        if (doValida(VL_Adicional))
            ed.setVL_Adicional(Double.parseDouble(VL_Adicional));
        //*** Valor Diferença Aliquota ICMS
        if (doValida(VL_Diferenca_Aliq_ICMS))
            ed.setVL_Diferenca_Aliq_ICMS(Double.parseDouble(VL_Diferenca_Aliq_ICMS));
        //*** Valor do Servico
        if (doValida(VL_Servico))
            ed.setVL_Servico(Double.parseDouble(VL_Servico));
        //*** Valor do PIS
        if (doValida(VL_PIS))
            ed.setVL_Pis(Double.parseDouble(VL_PIS));
        //*** Valor do Cofins
        if (doValida(VL_ICMS_Subst))
            ed.setVL_ICMS_Subst(Double.parseDouble(VL_ICMS_Subst));
        //*** Valor do CSLL
        if (doValida(VL_CSLL))
            ed.setVL_Csll(Double.parseDouble(VL_CSLL));
        //*** Valor Nota Fiscal
        if (doValida(VL_Nota_Fiscal))
            ed.setVl_nota_fiscal(Double.parseDouble(VL_Nota_Fiscal));

        if (doValida(DM_Observacao))
            ed.setDm_observacao(DM_Observacao);
        else ed.setDm_observacao("");
        if (doValida(NM_Complemento))
            ed.setNm_complemento(NM_Complemento);
        else ed.setNm_complemento("");
        if (doValida(DM_Tipo_Nota_Fiscal))
            ed.setDm_tipo_nota_fiscal(DM_Tipo_Nota_Fiscal);
        if (doValida(NR_Nota_Fiscal))
            ed.setNr_nota_fiscal(Long.parseLong(NR_Nota_Fiscal));
        if (doValida(NR_Itens))
            ed.setNr_Itens(Double.parseDouble(NR_Itens));
        if (doValida(NR_Volumes))
            ed.setNr_volumes(Double.parseDouble(NR_Volumes));
        if (doValida(NR_Peso))
            ed.setNR_Peso(Double.parseDouble(NR_Peso));
        if (doValida(NR_Peso_Cubado))
            ed.setNR_Peso_Cubado(Double.parseDouble(NR_Peso_Cubado));
        ed.setDM_Pendencia("N");
        ed.setDM_Situacao("A");
        return new Nota_Fiscal_EletronicaRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");
        String oid_Natureza_Operacao_Servico = request.getParameter("oid_Natureza_Operacao_Servico");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Pessoa_Remetente = request.getParameter("oid_Pessoa_Remetente");
        String oid_Pessoa_Destinatario = request.getParameter("oid_Pessoa_Destinatario");
        String oid_Pessoa_Fornecedor = request.getParameter("oid_Pessoa_Fornecedor");
        String oid_Pessoa_Consignatario = request.getParameter("oid_Pessoa_Consignatario");
        String oid_Pessoa_Redespacho = request.getParameter("oid_Pessoa_Redespacho");
        String oid_Centro_Custo = request.getParameter("oid_Centro_Custo");
        String oid_Unidade_Contabil = request.getParameter("oid_Unidade_Contabil");
        String oid_Unidade_Fiscal = request.getParameter("oid_Unidade_Fiscal");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String oid_Condicao_Pagamento = request.getParameter("oid_Condicao_Pagamento");
        String oid_Conta = request.getParameter("oid_Conta");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String oid_Veiculo = request.getParameter("oid_Veiculo");
        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");

        String NM_Serie = request.getParameter("FT_NM_Serie");
        String DT_Emissao = request.getParameter("FT_DT_Emissao");
        String DM_Frete = request.getParameter("FT_DM_Frete");
        String DT_Entrega = request.getParameter("FT_DT_Entrega");
        String DT_Entrada = request.getParameter("FT_DT_Entrada");
        String HR_Entrada = request.getParameter("FT_HR_Entrada");
        String FRM_PGTO = request.getParameter("FT_FRM_PGTO");
        String NR_PARCELAS = request.getParameter("FT_NR_PARCELAS");

        String VL_ICMS = request.getParameter("FT_VL_ICMS");
        String PE_Aliquota_ICMS = request.getParameter("FT_PE_Aliquota_ICMS");
        String VL_INSS = request.getParameter("FT_VL_INSS");
        String VL_IPI = request.getParameter("FT_VL_IPI");
        String VL_IRRF = request.getParameter("FT_VL_IRRF");
        String VL_ISQN = request.getParameter("FT_VL_ISQN");
        String VL_Frete = request.getParameter("FT_VL_Frete");
        String VL_Seguro = request.getParameter("FT_VL_Seguro");
        String VL_Despesas = request.getParameter("FT_VL_Despesas");
        String VL_LIQUIDO = request.getParameter("FT_VL_LIQUIDO");
        String VL_Desconto = request.getParameter("FT_VL_Desconto");
        String VL_Desconto_Itens = request.getParameter("FT_VL_Desconto_Itens");
        String VL_Adicional = request.getParameter("FT_VL_Adicional");
        String VL_Diferenca_Aliq_ICMS = request.getParameter("FT_");
        String VL_Servico = request.getParameter("FT_VL_Servico");
        String VL_PIS = request.getParameter("FT_VL_PIS");
        String VL_ICMS_Subst = request.getParameter("FT_VL_ICMS_Subst");
        String VL_CSLL = request.getParameter("FT_VL_CSLL");
        String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");

        String DM_Observacao = request.getParameter("FT_DM_Observacao");
        String NM_Complemento = request.getParameter("FT_NM_Complemento");
        String DM_Tipo_Nota_Fiscal = request.getParameter("FT_DM_Tipo_Nota_Fiscal");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        String NR_Itens = request.getParameter("FT_NR_Itens");
        String NR_Volumes = request.getParameter("FT_NR_Volumes");
        String NR_Peso = request.getParameter("FT_NR_Peso");
        String NR_Peso_Cubado = request.getParameter("FT_NR_Peso_Cubado");
        String DM_Pendencia = request.getParameter("FT_DM_Pendencia");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String dm_Devolvido = request.getParameter("dm_Devolvido");

        if (!doValida(oid_Nota_Fiscal))
            throw new Excecoes("Nota Fiscal não informada!");
        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED(oid_Nota_Fiscal);
        ed.setUpdatePrecosByCond("true".equals(request.getParameter("updatePrecosByCond")));
        ed.setUpdatePrecosByTabela("true".equals(request.getParameter("updatePrecosByTabela")));
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo_nota_fiscal(Long.parseLong(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Natureza_Operacao))
            ed.setOid_natureza_operacao(Long.parseLong(oid_Natureza_Operacao));
        if (doValida(oid_Natureza_Operacao_Servico))
            ed.setOid_Natureza_Operacao_Servico(Integer.parseInt(oid_Natureza_Operacao_Servico));
        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (doValida(oid_Pessoa_Remetente))
            ed.setOid_pessoa(oid_Pessoa_Remetente);
        if (doValida(oid_Pessoa_Destinatario))
            ed.setOid_pessoa_destinatario(oid_Pessoa_Destinatario);
        else ed.setOid_pessoa_destinatario(oid_Pessoa);
        if (doValida(oid_Pessoa_Consignatario))
            ed.setOid_Pessoa_Consignatario(oid_Pessoa_Consignatario);
        if (doValida(oid_Pessoa_Redespacho))
            ed.setOid_Pessoa_Redespacho(oid_Pessoa_Redespacho);
        if (doValida(oid_Pessoa_Fornecedor))
            ed.setOid_pessoa_fornecedor(oid_Pessoa_Fornecedor);
        if (doValida(oid_Centro_Custo))
            ed.setOid_Centro_Custo(new Integer(oid_Centro_Custo));
        if (doValida(oid_Condicao_Pagamento))
            ed.setOid_Condicao_Pagamento(Integer.parseInt(oid_Condicao_Pagamento));
        if (doValida(oid_Conta))
            ed.setOid_Conta(Integer.parseInt(oid_Conta));
        if (doValida(oid_Entregador))
            ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
        if (doValida(oid_Veiculo))
            ed.setOid_Veiculo(oid_Veiculo);
        if (doValida(oid_Tabela_Venda))
            ed.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));

        ed.setNm_serie(getValueDef(NM_Serie, ""));
        if (doValida(DT_Emissao))
            ed.setDt_emissao(DT_Emissao);
        if (doValida(DM_Frete))
            ed.setDM_Frete(DM_Frete);
        if (doValida(DT_Entrega))
            ed.setDT_Entrega(DT_Entrega);
        if (doValida(DT_Entrada))
            ed.setDt_entrada(DT_Entrada);
        if (doValida(HR_Entrada))
            ed.setHr_entrada(HR_Entrada);
        if (doValida(FRM_PGTO))
            ed.setDm_forma_pagamento(FRM_PGTO);
        if (doValida(NR_PARCELAS))
            ed.setNr_parcelas(Long.parseLong(NR_PARCELAS));
        if (doValida(oid_Unidade_Contabil))
            ed.setOID_Unidade_Contabil(Long.parseLong(oid_Unidade_Contabil));
        if (doValida(oid_Unidade_Fiscal))
            ed.setOID_Unidade_Fiscal(Long.parseLong(oid_Unidade_Fiscal));
        if (doValida(oid_Unidade))
            ed.setOID_Unidade(Long.parseLong(oid_Unidade));
        //*** Valor do ICMS
        if (doValida(VL_ICMS))
            ed.setVl_icms(Double.parseDouble(VL_ICMS));
        if (doValida(PE_Aliquota_ICMS))
            ed.setPE_Aliquota_ICMS(Double.parseDouble(PE_Aliquota_ICMS));
        //*** Valor do INSS
        if (doValida(VL_INSS))
            ed.setVl_inss(Double.parseDouble(VL_INSS));
        //*** Valor do IPI
        if (doValida(VL_IPI))
            ed.setVl_ipi(Double.parseDouble(VL_IPI));
        //*** Valor do IRRF
        if (doValida(VL_IRRF))
            ed.setVl_irrf(Double.parseDouble(VL_IRRF));
        //*** Valor do ISQN
        if (doValida(VL_ISQN))
            ed.setVl_isqn(Double.parseDouble(VL_ISQN));
        //*** Valor do Frete
        if (doValida(VL_Frete))
            ed.setVl_total_frete(Double.parseDouble(VL_Frete));
        //*** Valor do Seguro
        if (doValida(VL_Seguro))
            ed.setVl_total_seguro(Double.parseDouble(VL_Seguro));
        //*** Valor do Despesas
        if (doValida(VL_Despesas))
            ed.setVl_total_despesas(Double.parseDouble(VL_Despesas));
        //*** Valor Liquido
        if (doValida(VL_LIQUIDO))
            ed.setVl_liquido_nota_fiscal(Double.parseDouble(VL_LIQUIDO));
        //*** Valor do Descontos
        if (doValida(VL_Desconto))
            ed.setVl_descontos(Double.parseDouble(VL_Desconto));
        //*** Valor do Descontos Itens
        if (doValida(VL_Desconto_Itens))
            ed.setVL_Desconto_Itens(Double.parseDouble(VL_Desconto_Itens));
        //*** Valor Adicional
        if (doValida(VL_Adicional))
            ed.setVL_Adicional(Double.parseDouble(VL_Adicional));
        //*** Valor Diferença Aliquota ICMS
        if (doValida(VL_Diferenca_Aliq_ICMS))
            ed.setVL_Diferenca_Aliq_ICMS(Double.parseDouble(VL_Diferenca_Aliq_ICMS));
        //*** Valor do Servico
        if (doValida(VL_Servico))
            ed.setVL_Servico(Double.parseDouble(VL_Servico));
        //*** Valor do PIS
        if (doValida(VL_PIS))
            ed.setVL_Pis(Double.parseDouble(VL_PIS));
        //*** Valor do Cofins
        if (doValida(VL_ICMS_Subst))
            ed.setVL_ICMS_Subst(Double.parseDouble(VL_ICMS_Subst));
        //*** Valor do CSLL
        if (doValida(VL_CSLL))
            ed.setVL_Csll(Double.parseDouble(VL_CSLL));
        //*** Valor Nota Fiscal
        if (doValida(VL_Nota_Fiscal))
            ed.setVl_nota_fiscal(Double.parseDouble(VL_Nota_Fiscal));

        if (doValida(DM_Observacao))
            ed.setDm_observacao(DM_Observacao);
        else ed.setDm_observacao("");
        if (doValida(NM_Complemento))
            ed.setNm_complemento(NM_Complemento);
        else ed.setNm_complemento("");

        if (doValida(DM_Tipo_Nota_Fiscal))
            ed.setDm_tipo_nota_fiscal(DM_Tipo_Nota_Fiscal);
        if (doValida(NR_Nota_Fiscal))
            ed.setNr_nota_fiscal(Long.parseLong(NR_Nota_Fiscal));
        if (doValida(NR_Itens))
            ed.setNr_Itens(Double.parseDouble(NR_Itens));
        if (doValida(NR_Volumes))
            ed.setNr_volumes(Double.parseDouble(NR_Volumes));
        if (doValida(NR_Peso))
            ed.setNR_Peso(Double.parseDouble(NR_Peso));
        if (doValida(NR_Peso_Cubado))
            ed.setNR_Peso_Cubado(Double.parseDouble(NR_Peso_Cubado));

        if (doValida(DM_Pendencia))
            ed.setDM_Pendencia(DM_Pendencia);
        if (doValida(DM_Situacao))
            ed.setDM_Situacao(DM_Situacao);
        if (doValida(dm_Devolvido))
            ed.setDm_Devolvido(dm_Devolvido);

        new Nota_Fiscal_EletronicaRN().altera(ed);
    }

    public void altera_CFOP(HttpServletRequest request) throws Excecoes {

        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");
        String oid_Natureza_Operacao_Servico = request.getParameter("oid_Natureza_Operacao_Servico");
        String DM_Observacao = request.getParameter("FT_DM_Observacao");

        if (!doValida(oid_Nota_Fiscal))
            throw new Excecoes("Nota Fiscal não informada!");
        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED(oid_Nota_Fiscal);
        if (doValida(oid_Natureza_Operacao))
            ed.setOid_natureza_operacao(Long.parseLong(oid_Natureza_Operacao));
        if (doValida(oid_Natureza_Operacao_Servico))
            ed.setOid_Natureza_Operacao_Servico(Integer.parseInt(oid_Natureza_Operacao_Servico));
        if (doValida(DM_Observacao))
            ed.setDm_observacao(DM_Observacao);
        else ed.setDm_observacao("");

        new Nota_Fiscal_EletronicaRN().altera_CFOP(ed);
    }


    public void deleta(HttpServletRequest request) throws Excecoes {

        String IS_OK = request.getParameter("IS_OK");
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String oid_Carga_Entrega = request.getParameter("oid_Carga_Entrega");
        String DM_Tipo_Nota_Fiscal = request.getParameter("FT_DM_Tipo_Nota_Fiscal");

        if (!doValida(oid_Nota_Fiscal))
            throw new Excecoes("ID Nota Fiscal não informado!");
        if (!doValida(DM_Tipo_Nota_Fiscal))
            throw new Excecoes("Tipo de Nota Fiscal não informado!");

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED(oid_Nota_Fiscal);
        ed.setDm_tipo_nota_fiscal(DM_Tipo_Nota_Fiscal);
        if (doValida(oid_Carga_Entrega))
            ed.setOid_Carga_Entrega(Integer.parseInt(oid_Carga_Entrega));
        if ("S".equals(DM_Tipo_Nota_Fiscal) && doValida(request.getParameter("oid_Pedido"))) {
            ed.setOid_Pedido(request.getParameter("oid_Pedido"));
            ed.setDM_Tipo_Devolucao("true".equals(request.getParameter("reabrePedido")) ? "R" : "C"); //*** Tipo "R" Reabre Pedido de Venda
        }
        new Nota_Fiscal_EletronicaRN().deleta(ed,"SIM".equals(IS_OK));
    }

    public ArrayList Nota_Fiscal_Lista(HttpServletRequest request) throws Excecoes {

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED(request.getParameter("oid_Nota_Fiscal"));
        ed.setPaginacao(request);

        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String oid_Pessoa_Remetente = request.getParameter("oid_Pessoa_Remetente");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Vendedor = request.getParameter("oid_Vendedor");
    	String oid_Pessoa_Destinatario = request.getParameter("oid_Pessoa_Destinatario");
    	String oid_Pessoa_Consignatario = request.getParameter("oid_Pessoa_Consignatario");
    	String oid_Pessoa_Redespacho = request.getParameter("oid_Pessoa_Redespacho");
    	String oid_Pessoa_Fornecedor = request.getParameter("oid_Pessoa_Fornecedor");
    	String DT_Emissao = request.getParameter("FT_DT_Emissao");
        String DT_Entrega = request.getParameter("FT_DT_Entrega");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
    	String DT_Entrada = request.getParameter("FT_DT_Entrada");
    	String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        String NM_Serie = request.getParameter("FT_NM_Serie");
        String Dt_Inicial = request.getParameter("FT_DT_Inicial");
        String Dt_Final = request.getParameter("FT_DT_Final");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Estoque = request.getParameter("FT_DM_Estoque");
        String DM_Pendencia = request.getParameter("FT_DM_Pendencia");
        String DM_Impresso = request.getParameter("FT_DM_Impresso");
        String DM_Tipo_Nota_Fiscal = request.getParameter("FT_DM_Tipo_Nota_Fiscal");

        if (doValida(oid_Unidade))
            ed.setOID_Unidade_Contabil(Long.parseLong(oid_Unidade));
        if (doValida(oid_Entregador))
            ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (doValida(oid_Vendedor))
            ed.setOid_Vendedor(oid_Vendedor);

        if (doValida(oid_Pessoa_Fornecedor))
            ed.setOid_pessoa_fornecedor(oid_Pessoa_Fornecedor);
        if (doValida(oid_Pessoa_Remetente))
            ed.setOid_pessoa(oid_Pessoa_Remetente);
        if (doValida(oid_Pessoa_Destinatario))
            ed.setOid_pessoa_destinatario(oid_Pessoa_Destinatario);
        if (doValida(oid_Pessoa_Consignatario))
            ed.setOid_Pessoa_Consignatario(oid_Pessoa_Consignatario);
        if (doValida(oid_Pessoa_Redespacho))
            ed.setOid_Pessoa_Redespacho(oid_Pessoa_Redespacho);

        if (doValida(NR_Nota_Fiscal))
            ed.setNr_nota_fiscal(Long.parseLong(NR_Nota_Fiscal));
        if (doValida(NM_Serie))
            ed.setNm_serie(NM_Serie);
        if (doValida(Dt_Inicial))
            ed.setDT_Inicial(Dt_Inicial);
        if (doValida(Dt_Final))
            ed.setDT_Final(Dt_Final);
        if (doValida(DT_Emissao))
            ed.setDt_emissao(DT_Emissao);
        if (doValida(DT_Entrega))
            ed.setDT_Entrega(DT_Entrega);
        if (doValida(DT_Entrega_Final))
            ed.setDT_Entrega_Final(DT_Entrega_Final);
        if (doValida(DT_Entrada))
            ed.setDt_entrada(DT_Entrada);
        if (doValida(DT_Entrada_Final))
            ed.setDt_entrada_final(DT_Entrada_Final);
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo_nota_fiscal(Long.parseLong(oid_Modelo_Nota_Fiscal));
        if (doValida(DM_Situacao))
            ed.setDM_Situacao(DM_Situacao);
        if (doValida(DM_Estoque))
            ed.setDM_Estoque(DM_Estoque);
        if (doValida(DM_Pendencia))
            ed.setDM_Pendencia(DM_Pendencia);
        if (doValida(DM_Impresso))
            ed.setDM_Impresso(DM_Impresso);
        if (doValida(DM_Tipo_Nota_Fiscal))
            ed.setDm_tipo_nota_fiscal(DM_Tipo_Nota_Fiscal);

        return new Nota_Fiscal_EletronicaRN().lista(ed);
    }

    public ArrayList Nota_Fiscal_Lista_em_Viagem(HttpServletRequest request) throws Excecoes {

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();
        String oid_Manifesto = request.getParameter("oid_Manifesto");

        if (doValida(oid_Manifesto))
            ed.setOid_Manifesto(oid_Manifesto);

        return new Nota_Fiscal_EletronicaRN().lista_em_Viagem(ed);
    }

    public Nota_Fiscal_EletronicaED getByRecord(HttpServletRequest request) throws Excecoes {

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");

        if (doValida(NR_Nota_Fiscal))
            ed.setNr_nota_fiscal(Long.parseLong(NR_Nota_Fiscal));
        if (doValida(oid_Nota_Fiscal))
            ed.setOid_nota_fiscal(oid_Nota_Fiscal);

        return new Nota_Fiscal_EletronicaRN().getByRecord(ed);
    }

    public Nota_Fiscal_EletronicaED getByNR_Nota_Fiscal(HttpServletRequest request) throws Excecoes {

        String oid_Unidade = request.getParameter("oid_Unidade");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        if (!doValida(NR_Nota_Fiscal))
            throw new Mensagens("Informe o Nº da Nota Fiscal!");

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();
        if (doValida(oid_Unidade))
            ed.setOID_Unidade(Long.parseLong(oid_Unidade));
        ed.setNr_nota_fiscal(Long.parseLong(NR_Nota_Fiscal));
        ed.setDm_tipo_nota_fiscal(request.getParameter("FT_DM_Tipo_Nota_Fiscal"));
        return new Nota_Fiscal_EletronicaRN().getByRecord(ed);
    }

    //*** Não pode existir duas Notas de ENTRADA Com mesmo Numero para o mesmo Fornecedor
    public boolean existeNR_Nota_Fiscal(HttpServletRequest request) throws Excecoes {

    	String DM_Tipo_Nota_Fiscal = request.getParameter("FT_DM_Tipo_Nota_Fiscal");
    	String oid_Pessoa_Fornecedor = request.getParameter("oid_Pessoa_Fornecedor");
    	String oid_Pessoa_Remetente = request.getParameter("oid_Pessoa_Remetente");
    	String oid_Pessoa_Destinatario = request.getParameter("oid_Pessoa_Destinatario");
    	if (doValida(oid_Pessoa_Remetente) && "E".equals(DM_Tipo_Nota_Fiscal))
    		oid_Pessoa_Fornecedor = oid_Pessoa_Remetente;
    	if (doValida(oid_Pessoa_Destinatario) && !"E".equals(DM_Tipo_Nota_Fiscal))
    		oid_Pessoa_Fornecedor = oid_Pessoa_Destinatario;
        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String NM_Serie = request.getParameter("FT_NM_Serie");

        oid_Pessoa_Fornecedor = oid_Pessoa_Remetente;

        if (!doValida(oid_Pessoa_Fornecedor))
            throw new Mensagens("ID Fornecedor não informado!");
        if (!doValida(NR_Nota_Fiscal))
            throw new Mensagens("Numero da Nota Fiscal não informado!");
        if (!doValida(oid_Modelo_Nota_Fiscal))
            throw new Mensagens("Modelo da Nota Fiscal não informado!");
        String query = "oid_Pessoa = '"+oid_Pessoa_Fornecedor+"'" +
				       " AND NR_Nota_Fiscal = "+NR_Nota_Fiscal+
				       " AND oid_Modelo_Nota_Fiscal = "+oid_Modelo_Nota_Fiscal+
                       " AND DM_Tipo_Nota_Fiscal = '" + DM_Tipo_Nota_Fiscal + "'";
        if (doValida(NM_Serie))
            query += " AND NM_Serie = '"+NM_Serie+"'";

        if (doValida(oid_Nota_Fiscal))
            query += " AND oid_Nota_Fiscal != '"+oid_Nota_Fiscal+"'";

        return doExiste("Notas_Fiscais", query);
    }

    public Nota_Fiscal_EletronicaED getByOid(String oid_Nota_Fiscal) throws Excecoes {

        if (!doValida(oid_Nota_Fiscal))
            throw new Mensagens("Informe o ID da Nota Fiscal!");
        return new Nota_Fiscal_EletronicaRN().getByRecord(new Nota_Fiscal_EletronicaED(oid_Nota_Fiscal));
    }

    public String getOid_Pedido(String oid_Nota_Fiscal) throws Excecoes {

        if (!doValida(oid_Nota_Fiscal))
            throw new Excecoes("ID Nota Fiscal não informado!");

        return super.getTableStringValue("DISTINCT Pedidos.oid_Pedido",
                						 "Pedidos, Notas_Fiscais, Itens_Notas_Fiscais, Itens_Pedidos",
                						 " Itens_Notas_Fiscais.oid_Pedido = Pedidos.oid_Pedido " +
                						 " AND Itens_Notas_Fiscais.oid_Pedido = Itens_Pedidos.oid_Pedido " +
                						 " AND Itens_Notas_Fiscais.oid_Item_Pedido = Itens_Pedidos.oid_Item_Pedido " +
                						 " AND Itens_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal " +
                						 " AND Itens_Notas_Fiscais.oid_Nota_Fiscal = '"+oid_Nota_Fiscal+"'");
    }

    //*** Não deixa ALTERAR ou EXCLUIR caso exista
    //    algum item relacionado a Item do Pedido
    public boolean doExisteItensPedido(String oid_Nota_Fiscal) throws Excecoes {

        if (!doValida(oid_Nota_Fiscal))
            throw new Mensagens("Nota Fiscal não informada!");

        return super.doExiste("Itens_Notas_Fiscais, Notas_Fiscais",
                			  "(Itens_Notas_Fiscais.oid_Item_Pedido > 0 OR Itens_Notas_Fiscais.oid_Item_Pedido IS NULL) " +
                			  " AND Itens_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal " +
                			  " AND Itens_Notas_Fiscais.oid_Nota_Fiscal = '"+oid_Nota_Fiscal+"'");
    }
    public boolean doExisteItensNF(String oid_Nota_Fiscal) throws Excecoes {

        if (!doValida(oid_Nota_Fiscal))
            throw new Mensagens("Nota Fiscal não informada!");
        return super.doExiste("Itens_Notas_Fiscais", "oid_Nota_Fiscal = '"+oid_Nota_Fiscal+"'");
    }

    //*** Finaliza Nota Fiscal de Venda Direta
    public void finalizaNF_VendaDireta(String oid_Nota_Fiscal, String oid_Conhecimento) throws Exception {

        if (!doValida(oid_Nota_Fiscal))
            throw new Mensagens("ID Nota Fiscal não informada!");
        if (!doValida(oid_Conhecimento))
            throw new Mensagens("ID Conhecimento não informado!");

        new Nota_Fiscal_EletronicaRN().finalizaNF_VendaDireta(oid_Nota_Fiscal, oid_Conhecimento);
    }

    //*** Finaliza Nota Fiscal de Entrada
    public Nota_Fiscal_EletronicaED finalizaNF_Entrada(HttpServletRequest request) throws Exception{

        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        if (!doValida(oid_Nota_Fiscal))
            throw new Excecoes("Nota Fiscal não informada!");

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaRN().getByRecord(new Nota_Fiscal_EletronicaED(oid_Nota_Fiscal));
        return new Nota_Fiscal_EletronicaRN().finalizaNF_Entrada(ed);
    }

    //*** Finaliza Nota Fiscal de Saida (+ impressão)
    public void finalizaNF_Saida(HttpServletRequest request) throws Excecoes {

        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        if (!doValida(oid_Nota_Fiscal))
            throw new Mensagens("Nota Fiscal não informada!");

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();
        //.getByRecord(new Nota_Fiscal_TransacoesED(oid_Nota_Fiscal));
        ed.setOid_nota_fiscal(oid_Nota_Fiscal);
        ed.setDT_Entrega(request.getParameter("FT_DT_Saida"));
        ed.setHr_entrada(request.getParameter("FT_HR_Saida"));
        boolean stkOK = false;
        // System.out.println("Sai Da nOOOOOOO IUUUUUUUUUU 1");
        stkOK = new Nota_Fiscal_EletronicaRN().finalizaNF_Saida(ed);
        // System.out.println("Sai Da nOOOOOOO IUUUUUUUUUU 2");
        if (!stkOK) throw new Mensagens("Nota Fiscal não Finalizada ! Consulte as Ocorrencias!	");
        // System.out.println("Sai Da nOOOOOOO IUUUUUUUUUU 3");
    }

//  *** Finaliza Lote de Nota Fiscal de Saida (pelo Manifesto)
    public void finalizaLote_NF_Saida(HttpServletRequest request) throws Excecoes {

    	String arquivo = "finalizaLote_NF.log"; //arquivo que vai conter os erros na finalização
    	FileUtil.renameLogFile(arquivo); //renomeia para 'finalizaLote_NF.log_DATA_DE_HOJE(AAAAMMDD)'

        String oid_Manifesto = request.getParameter("oid_Manifesto");
        if (!doValida(oid_Manifesto))
            throw new Mensagens("Manifesto não informado!");

        Iterator iterador = this.Nota_Fiscal_Lista_em_Viagem(request).iterator();

        while(iterador.hasNext()){
        	boolean stkOK = false;
        	Nota_Fiscal_EletronicaED ed = (Nota_Fiscal_EletronicaED)iterador.next();
        	try{
        		ed = new Nota_Fiscal_EletronicaRN().getByRecord(new Nota_Fiscal_EletronicaED(ed.getOid_nota_fiscal()));
                stkOK = new Nota_Fiscal_EletronicaRN().finalizaNF_Saida(ed);
        	} catch(Excecoes e){
        		String msg = "NF: "+ed.getNr_nota_fiscal() + ". Erro: " + e.getMensagem() + "/" + e.getMessage() + "/" + e.getLocalizedMessage() + "|FIM";
        		FileUtil.saveStrToFile(arquivo, msg);
        	} catch(Exception e){
        		String msg = "NF: "+ed.getNr_nota_fiscal() + ". Erro: " + e.getMessage() + "/" + e.getLocalizedMessage() + "|FIM";
        		FileUtil.saveStrToFile(arquivo, msg);
        	}
            if (!stkOK){
            	String msg = "NF: "+ed.getNr_nota_fiscal() + ". Erro: Nota Fiscal não Finalizada ! Consulte as Ocorrencias!/";
        		FileUtil.saveStrToFile(arquivo, msg);
            }
        }

        //fecha manifesto! dm_carga_fechada = 'S'...

    }

    //*** Finaliza Nota Fiscal de Saida (+ impressão)
    public void finalizaNF_Devolucao(HttpServletRequest request) throws Excecoes {

        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        if (!doValida(oid_Nota_Fiscal))
            throw new Mensagens("Nota Fiscal não informada!");

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaRN().getByRecord(new Nota_Fiscal_EletronicaED(oid_Nota_Fiscal));
        boolean finaliza = new Nota_Fiscal_EletronicaRN().finalizaNF_Devolucao(ed,true);
        if (!finaliza) throw new Mensagens("Nota Fiscal não pode ser Finalizada! Veja ocorrencias da NF!");
    }

    //*** Busca lista de Notas Fiscais para Finalização
    public ArrayList getListNFFinalizar(HttpServletRequest request) throws Excecoes{

        String oid_Carga_Entrega = request.getParameter("oid_Carga_Entrega");

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Pessoa_Fornecedor = request.getParameter("oid_Pessoa_Fornecedor");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String oid_Vendedor = request.getParameter("oid_Vendedor");
        String DM_Tipo_Nota_Fiscal = request.getParameter("FT_DM_Tipo_Nota_Fiscal");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        String NR_Nota_Fiscal_Final = request.getParameter("FT_NR_Nota_Fiscal_Final");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String DT_Entrega = request.getParameter("FT_DT_Entrega");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Impresso = request.getParameter("FT_DM_Impresso");

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();
        if (!doValida(DM_Tipo_Nota_Fiscal))
            throw new Excecoes("Tipo de NF não informado!", this.getClass().getName(), "getListNFFinalizar()");
        ed.setDm_tipo_nota_fiscal(DM_Tipo_Nota_Fiscal);

        if (doValida(oid_Carga_Entrega))
            ed.setOid_Carga_Entrega(Integer.parseInt(oid_Carga_Entrega));
        if (doValida(NR_Nota_Fiscal))
            ed.setNr_nota_fiscal(Long.parseLong(NR_Nota_Fiscal));
        if (doValida(NR_Nota_Fiscal_Final))
            ed.setNr_nota_fiscal_final(Long.parseLong(NR_Nota_Fiscal_Final));
        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (doValida(oid_Pessoa_Fornecedor))
            ed.setOid_pessoa_fornecedor(oid_Pessoa_Fornecedor);
        if (doValida(oid_Entregador))
            ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
        if (doValida(oid_Vendedor))
            ed.setOid_Vendedor(oid_Vendedor);
        if (doValida(DT_Entrega))
            ed.setDT_Entrega(DT_Entrega);
        if (doValida(DT_Entrega_Final))
            ed.setDT_Entrega_Final(DT_Entrega_Final);
        if (doValida(DT_Emissao_Inicial))
            ed.setDT_Inicial(DT_Emissao_Inicial);
        if (doValida(DT_Emissao_Final))
            ed.setDT_Final(DT_Emissao_Final);
        if (doValida(DM_Impresso))
            ed.setDM_Impresso(DM_Impresso);

        //*** Somente Notas em Aberto
        ed.setDM_Situacao(getValueDef(DM_Situacao,"N"));
        return new Nota_Fiscal_EletronicaRN().lista(ed);
    }
    //*** Finaliza uma lista de Notas Fiscais
    public void finalizaListaNF(HttpServletRequest request) throws Exception {

        ArrayList lista = (ArrayList) request.getSession(true).getAttribute("listaNotas");
        String dmTipoNF = request.getParameter("FT_DM_Tipo_Nota_Fiscal");

        if (lista.size() < 1)
            throw new Mensagens("Lista de Notas vazia! Execute novamente a consulta!");
        if (!doValida(dmTipoNF))
            throw new Mensagens("Tipo de Nota não informada!");

        new Nota_Fiscal_EletronicaRN().finalizaListaNF(lista
                                                      ,dmTipoNF
                                                      ,getValueDef(request.getParameter("oid_Carga_Entrega"),0)
                                                      ,request.getParameter("FT_DT_Saida")
                                                      ,request.getParameter("FT_HR_Saida"));
    }

    //*** EXCLUI Nota Fiscal de COMPRA
    public void excluiNFCompra(String oid_Nota_Fiscal, String dmTipo_Nota_Fiscal) throws Exception {

        if (!JavaUtil.doValida(oid_Nota_Fiscal))
            throw new Mensagens("Nota Fiscal não informada para Exclusão!");
        if (!JavaUtil.doValida(dmTipo_Nota_Fiscal))
            throw new Mensagens("Tipo de Nota não informado!");

        Nota_Fiscal_EletronicaED edNota = new Nota_Fiscal_EletronicaED(oid_Nota_Fiscal);
        edNota.setDm_tipo_nota_fiscal(dmTipo_Nota_Fiscal);
        new Nota_Fiscal_EletronicaRN().excluiNFCompra(edNota);
    }

    public void cancelaNFCompra(String oid_Nota_Fiscal, String dmTipo_Nota_Fiscal) throws Exception {

        if (!JavaUtil.doValida(oid_Nota_Fiscal))
            throw new Mensagens("Nota Fiscal não informada para Cancelamento!");
        if (!JavaUtil.doValida(dmTipo_Nota_Fiscal))
            throw new Mensagens("Tipo de Nota não informado!");

        Nota_Fiscal_EletronicaED edNota = new Nota_Fiscal_EletronicaED(oid_Nota_Fiscal);
        edNota.setDm_tipo_nota_fiscal(dmTipo_Nota_Fiscal);
        new Nota_Fiscal_EletronicaRN().cancelaNFCompra(edNota);
    }

    //*** CANCELA Nota Fiscal de Venda
    public void cancelaNFVenda(String oid_Nota_Fiscal, String reabrePedido) throws Exception {

        if (!doValida(oid_Nota_Fiscal))
            throw new Mensagens("ID Nota Fiscal não informada para Cancelamento!");
        Nota_Fiscal_EletronicaED edNota = new Nota_Fiscal_EletronicaED(oid_Nota_Fiscal);
        edNota.setDM_Tipo_Devolucao("true".equals(reabrePedido) ? "R" : "C"); //*** Tipo "R" Reabre Pedido de Venda
        new Nota_Fiscal_EletronicaRN().cancelaNFVenda(edNota);
    }
    public void cancelaNFVenda(ArrayList listaNotas, String reabrePedido) throws Exception {

        if (listaNotas.size() < 1)
            throw new Mensagens("Não foram informadas Notas Fiscais para serem Canceladas! Lista Vazia!");
        for (int i=0; i<listaNotas.size(); i++)
        {
            Nota_Fiscal_EletronicaED edNota = (Nota_Fiscal_EletronicaED) listaNotas.get(i);
            edNota.setDM_Tipo_Devolucao("true".equals(reabrePedido) ? "R" : "C"); //*** Tipo "R" Reabre Pedido de Venda
            new Nota_Fiscal_EletronicaRN().cancelaNFVenda(edNota);
        }
    }

    //*** Devolução Nota Fiscal de Venda
    public Nota_Fiscal_EletronicaED geraNFDevolucaoVenda(HttpServletRequest request) throws Exception {

        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");
        String oid_Pessoa_Fornecedor = request.getParameter("oid_Pessoa_Fornecedor");
        String oid_Tipo_Ocorrencia = request.getParameter("oid_Tipo_Ocorrencia");
        String DM_Tipo_Devolucao = request.getParameter("FT_DM_Tipo_Devolucao");
        String TX_Observacao = request.getParameter("FT_TX_Observacao");

        if (!doValida(oid_Nota_Fiscal))
            throw new Mensagens("Nota Fiscal não informada!");
        if (!doValida(oid_Modelo_Nota_Fiscal))
            throw new Mensagens("Modelo da Nota Fiscal não informado!");
        if (!doValida(oid_Natureza_Operacao))
            throw new Mensagens("Natureza de Operação não informada!");
        if (!doValida(oid_Pessoa_Fornecedor))
            throw new Mensagens("Fornecedor não informado!");
        if (!doValida(DM_Tipo_Devolucao))
            throw new Mensagens("Tipo de Devolução não informada!");
        if (!doValida(oid_Tipo_Ocorrencia))
            throw new Mensagens("Tipo de Ocorrência não informada!");

        //*** Ocorrência para NF de Origem
        Ocorrencia_Nota_FiscalED edOcorrencia = new Ocorrencia_Nota_FiscalED();
        edOcorrencia.setOID_Nota_Fiscal(oid_Nota_Fiscal);
        edOcorrencia.setOID_Tipo_Ocorrencia(Long.parseLong(oid_Tipo_Ocorrencia));
        edOcorrencia.setDT_Ocorrencia_Nota_Fiscal(Data.getDataDMY());
        edOcorrencia.setHR_Ocorrencia_Nota_Fiscal(Data.getHoraHM());
        edOcorrencia.setTX_Observacao(TX_Observacao);
        edOcorrencia.setDM_Origem_Ocorrencia("I");
        edOcorrencia.setDM_Pendencia("N");
        edOcorrencia.setNM_Pessoa(UsuarioBean.getUsuarioCorrente(request).getNm_Usuario());
        edOcorrencia.setMasterDetails(request);

        Nota_Fiscal_EletronicaED edDevolucao = new Nota_Fiscal_EletronicaRN().getByRecord(new Nota_Fiscal_EletronicaED(oid_Nota_Fiscal));
        edDevolucao.setDt_entrada(Data.getDataDMY());
        edDevolucao.setDt_emissao(Data.getDataDMY());
        edDevolucao.setDT_Entrega(Data.getDataDMY());
        edDevolucao.setOid_natureza_operacao(Long.parseLong(oid_Natureza_Operacao));
        edDevolucao.setOid_modelo_nota_fiscal(Long.parseLong(oid_Modelo_Nota_Fiscal));
        edDevolucao.setOid_Pessoa_Consignatario(edDevolucao.getOid_pessoa_fornecedor());//Será o Vendedor p/ Saida
        edDevolucao.setOid_pessoa_fornecedor(oid_Pessoa_Fornecedor);
        edDevolucao.setDM_Tipo_Devolucao(DM_Tipo_Devolucao);
        edDevolucao.setDm_observacao(TX_Observacao);
        edDevolucao.setOid_Centro_Custo(new Integer(Parametro_FixoED.getInstancia().getOID_Centro_Custo_Entrega()));
        edDevolucao.setMasterDetails(request);

        return new Nota_Fiscal_EletronicaRN().geraNFDevolucaoVenda(edDevolucao, edOcorrencia);
    }

    /**
     * Atualiza(Corrige) campos nas Notas Fiscais de Venda
     * @return ArrayList<String> com numeros das Notas Atualizadas
     */
    public ArrayList correcaoNotasVendas(HttpServletRequest request) throws Excecoes {

        //*** Campos p/ Filtro
        String dmOperacao = request.getParameter("dmOperacao");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        String NR_Nota_Fiscal_Final = request.getParameter("FT_NR_Nota_Fiscal_Final");
        String NM_Serie = request.getParameter("FT_NM_Serie");
        String dmTipoNF = request.getParameter("FT_DM_Tipo_Nota_Fiscal");
        //*** Campos p/ alterações
        String oid_Entregador = request.getParameter("oid_Entregador");
        String oid_Entregador2 = request.getParameter("oid_Entregador2");
        String oid_Vendedor = request.getParameter("oid_Vendedor");
        String oid_Vendedor2 = request.getParameter("oid_Vendedor2");
        String oid_Veiculo = request.getParameter("oid_Veiculo");
        String oid_Veiculo2 = request.getParameter("oid_Veiculo2");
        //*** Validações
        if (!doValida(oid_Unidade))
            throw new Mensagens("Unidade não informada!");
        if (!doValida(NR_Nota_Fiscal) && !doValida(NR_Nota_Fiscal_Final))
            throw new Mensagens("Notas Fiscais não informadas!");
        if (!doValida(dmTipoNF))
            throw new Mensagens("Tipo de Notas Fiscal não informado!");
        if (!doValida(dmOperacao))
            throw new Mensagens("Tipo de Operação não informada!");
        if ("V".equals(dmOperacao) && (!doValida(oid_Vendedor) || !doValida(oid_Vendedor2)))
            throw new Mensagens("Vendedores não informados para Alterações!");
        else if ("E".equals(dmOperacao) && (!doValida(oid_Entregador) || !doValida(oid_Entregador2)))
            throw new Mensagens("Entregadores não informados para Alterações!");
        else if ("P".equals(dmOperacao) && (!doValida(oid_Veiculo) || !doValida(oid_Veiculo2)))
            throw new Mensagens("Veículos não informados para Alterações!");

        //*** Filtro
        Nota_Fiscal_EletronicaED filter = new Nota_Fiscal_EletronicaED();
        filter.setOID_Unidade(Long.parseLong(oid_Unidade));
        filter.setNr_nota_fiscal(getValueDef(NR_Nota_Fiscal, 0));
        filter.setNr_nota_fiscal_final(getValueDef(NR_Nota_Fiscal_Final, 0));
        filter.setDm_tipo_nota_fiscal(dmTipoNF);
        if (doValida(NM_Serie))
            filter.setNm_serie(NM_Serie);
        if ("V".equals(dmOperacao))
        {
            filter.setOid_Vendedor(oid_Vendedor);
            filter.setDM_Impresso("N");
        } else if ("E".equals(dmOperacao))
            filter.setOid_Entregador(Integer.parseInt(oid_Entregador));
        else if ("P".equals(dmOperacao))
            filter.setOid_Veiculo(oid_Veiculo);

        ArrayList lista = new Nota_Fiscal_EletronicaRN().lista(filter);
        if (lista.isEmpty())
            throw new Mensagens("Não foram encontradas Notas Fiscais para Alteração!");

        //*** Alterações
        ArrayList returnList = new ArrayList();
        Iterator iterator = lista.iterator();
        while (iterator.hasNext())
        {
            Nota_Fiscal_EletronicaED ed = (Nota_Fiscal_EletronicaED) iterator.next();
            ed.setMasterDetails(request);
            if ("V".equals(dmOperacao))
                ed.setOid_Vendedor(oid_Vendedor2);
            else if ("E".equals(dmOperacao))
                ed.setOid_Entregador(Integer.parseInt(oid_Entregador2));
            else if ("P".equals(dmOperacao))
                ed.setOid_Veiculo(oid_Veiculo2);
            new Nota_Fiscal_EletronicaRN().correcao(ed);
            returnList.add(String.valueOf(ed.getNr_nota_fiscal()));
        }
        return returnList;
    }

    public boolean inclui_Lancamentos(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        /*ModeloNotaFiscalED edModelo = new ModeloNotaFiscalED();
        edModelo.setOid_Modelo_Nota_Fiscal(ed.getOid_modelo_nota_fiscal());
        edModelo = new ModeloNotaFiscalRN().getByRecord(edModelo);*/
        ed.setDM_Contabiliza("N");
        return new Nota_Fiscal_EletronicaRN().inclui_Lancamento(ed);
    }

    public boolean inclui_Parcelamentos(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        /*ModeloNotaFiscalED edModelo = new ModeloNotaFiscalED();
        edModelo.setOid_Modelo_Nota_Fiscal(ed.getOid_modelo_nota_fiscal());
        edModelo = new ModeloNotaFiscalRN().getByRecord(edModelo);*/
        ed.setDM_Financeiro(ed.edModelo.getDM_Movimenta_Financeiro());
        return new Nota_Fiscal_EletronicaRN().inclui_Parcelamento(ed);
    }

    public void reabre(HttpServletRequest request) throws Excecoes {

        Nota_Fiscal_EletronicaRN Nota_Fiscal_TransacoesRN = new Nota_Fiscal_EletronicaRN();
        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();

        ed.setOid_nota_fiscal(request.getParameter("oid_Nota_Fiscal"));
        ed.setNr_nota_fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());

        Nota_Fiscal_TransacoesRN.reabre(ed);
    }

    public boolean inclui_Lancamentos_Pagamento_Compromisso(Lote_CompromissoED ed) throws Excecoes {

        Lote_CompromissoED auxiliar = (Lote_CompromissoED) ed;
        ModeloNotaFiscalED modelo = new ModeloNotaFiscalED();
        ModeloNotaFiscalRN modelorn = new ModeloNotaFiscalRN();
        modelo.setOid_Modelo_Nota_Fiscal(ed.getOID_Modelo());
        modelo = modelorn.getByRecord(modelo);
        auxiliar.setDM_Contabiliza("N");
        return new Nota_Fiscal_EletronicaRN().inclui_Lancamentos_Pagamento_Compromisso(auxiliar);

    }

    public boolean inclui_Lancamentos_Lote_Posto(Posto_CompromissoED ed) throws Excecoes {

        Posto_CompromissoED auxiliar = (Posto_CompromissoED) ed;
        ModeloNotaFiscalED modelo = new ModeloNotaFiscalED();
        ModeloNotaFiscalRN modelorn = new ModeloNotaFiscalRN();
        modelo.setOid_Modelo_Nota_Fiscal(ed.getOID_Modelo());
        modelo = modelorn.getByRecord(modelo);
        auxiliar.setDM_Contabiliza("N");
        return new Nota_Fiscal_EletronicaRN().inclui_Lancamentos_Lote_Posto(auxiliar);

    }

    /** ------------ RELATÓRIOS ---------------- */
    public String imprime_NotasFiscaisMatricial(HttpServletRequest request) throws Excecoes {

        ArrayList lista = new ArrayList();

        if (request.getSession(true).getAttribute("listaNotas") == null)
            lista = this.Nota_Fiscal_Lista(request);
        else lista = (ArrayList) request.getSession(true).getAttribute("listaNotas");

        try{
        	//Melhorar isso... ver questão de edModelo.getDM_Permite_Servico()...
	        if(request.getParameter("oid_Modelo_Nota_Fiscal").equals("14") || request.getParameter("oid_Modelo_Nota_Fiscal").equals("17")){
	        	return new Nota_Fiscal_EletronicaRN().imprime_NotasFiscaisServicoMatricial(lista
                        ,request.getParameter("FT_DT_Saida")
                        ,request.getParameter("FT_HR_Saida"));
	        } else {
	        	return new Nota_Fiscal_EletronicaRN().imprime_NotasFiscaisMatricial(lista
                        ,request.getParameter("FT_DT_Saida")
                        ,request.getParameter("FT_HR_Saida"));
	        }
        } catch(Exception e){
        	e.printStackTrace();
        	throw new Excecoes();
        }
    }

    public String imprime_NotaAlteracaoPlaca(HttpServletRequest request) throws Excecoes {

        ArrayList lista = new ArrayList();
        if (request.getSession(true).getAttribute("listaNotas") == null)
            lista = this.Nota_Fiscal_Lista(request);
        else lista = (ArrayList) request.getSession(true).getAttribute("listaNotas");
        return new Nota_Fiscal_EletronicaRN().imprime_NotaAlteracaoPlaca(lista
                                                                        ,request.getParameter("FT_DT_Saida")
                                                                        ,request.getParameter("FT_HR_Saida"));
    }

    //*** Notas Fiscais de Venda
    public void relNF_Venda(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String Relatorio = request.getParameter("Relatorio");
        String DM_Tipo_Nota_Fiscal = request.getParameter("FT_DM_Tipo_Nota_Fiscal");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        String NR_Nota_Fiscal_Final = request.getParameter("FT_NR_Nota_Fiscal_Final");
        String CD_Vendedor = request.getParameter("FT_CD_Vendedor");
        String CD_Vendedor_Final = request.getParameter("FT_CD_Vendedor_Final");
        String oid_Carga_Entrega = request.getParameter("oid_Carga_Entrega");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String oid_Pessoa_Remetente = request.getParameter("oid_Pessoa_Remetente");
        String oid_Pessoa_Fornecedor = request.getParameter("oid_Pessoa_Fornecedor");
        String oid_Cidade = request.getParameter("oid_Cidade");
        String oid_Segmentacao = request.getParameter("oid_Segmentacao");

        String oid_Estrutura_Produto = request.getParameter("oid_Estrutura_Produto");
        String CD_Estrutura_Produto = request.getParameter("FT_CD_Estrutura_Produto");
        String CD_Estrutura_Produto_Final = request.getParameter("FT_CD_Estrutura_Produto_Final");

        String oid_Estrutura_Fornecedor = request.getParameter("oid_Estrutura_Fornecedor");

        String oid_Pessoa = request.getParameter("oid_Pessoa"); // Cliente
        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Pendencia = request.getParameter("FT_DM_Pendencia");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String DT_Entrega_Inicial = request.getParameter("FT_DT_Entrega_Inicial");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
        String oid_Supervisor = request.getParameter("oid_Supervisor");

        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        if (doValida(DM_Tipo_Nota_Fiscal))
            ed.setDm_tipo_nota_fiscal(DM_Tipo_Nota_Fiscal);
        if (doValida(oid_Carga_Entrega))
            ed.setOid_carga_entrega(Integer.parseInt(oid_Carga_Entrega));
        if (doValida(oid_Entregador))
            ed.setOid_entregador(Integer.parseInt(oid_Entregador));
        if (doValida(NR_Nota_Fiscal))
            ed.setNr_nota_fiscal(Integer.parseInt(NR_Nota_Fiscal));
        if (doValida(NR_Nota_Fiscal_Final))
            ed.setNr_nota_fiscal_final(Integer.parseInt(NR_Nota_Fiscal_Final));
        if (doValida(CD_Vendedor))
            ed.setCd_vendedor(CD_Vendedor);
        if (doValida(CD_Vendedor_Final))
            ed.setCd_vendedor_final(CD_Vendedor_Final);
        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (doValida(oid_Pessoa_Remetente))
            ed.setOid_pessoa_remetente(oid_Pessoa_Remetente);
        if (doValida(oid_Pessoa_Fornecedor))
            ed.setOid_pessoa_fornecedor(oid_Pessoa_Fornecedor);
        if (doValida(DT_Emissao_Inicial))
            ed.setDt_emissao_inicial(DT_Emissao_Inicial);
        if (doValida(DT_Emissao_Final))
            ed.setDt_emissao_final(DT_Emissao_Final);
        if (doValida(DT_Entrega_Inicial))
            ed.setDt_entrega(DT_Entrega_Inicial);
        if (doValida(DT_Entrega_Final))
            ed.setDt_entrega_final(DT_Entrega_Final);
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Grupo_Nota_Fiscal))
            ed.setOid_grupo_nota_fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));
        if (doValida(oid_Cidade))
            ed.setOid_cidade(Integer.parseInt(oid_Cidade));
        if (doValida(oid_Segmentacao))
            ed.setOid_segmentacao(Integer.parseInt(oid_Segmentacao));
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (doValida(DM_Pendencia))
            ed.setDm_pendencia(DM_Pendencia);
        if (doValida(oid_Supervisor))
            ed.setOid_Supervisor(oid_Supervisor);

        if (doValida(oid_Estrutura_Produto))
            ed.setOid_estrutura_produto(Integer.parseInt(oid_Estrutura_Produto));

        if (doValida(oid_Estrutura_Fornecedor))
            ed.setOid_estrutura_fornecedor(Integer.parseInt(oid_Estrutura_Fornecedor));

        if (doValida(CD_Estrutura_Produto))
            ed.setCd_estrutura_produto(CD_Estrutura_Produto);
        if (doValida(CD_Estrutura_Produto_Final))
            ed.setCd_estrutura_produto_final(CD_Estrutura_Produto_Final);

        new Nota_Fiscal_EletronicaRN().relNF_Venda(ed);
    }

    //*** Notas Fiscais de ENTRADA Conferência
    public void relNFConferencia(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String Relatorio = request.getParameter("Relatorio");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        String oid_Pessoa = request.getParameter("oid_Pessoa");//*** Fornecedor
        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Pendencia = request.getParameter("FT_DM_Pendencia");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Final");
        String DT_Entrada = request.getParameter("FT_DT_Entrada");
        String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");

        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!doValida(DT_Emissao_Inicial) && !doValida(DT_Emissao_Final) && !doValida(DT_Entrada) && !doValida(DT_Entrada_Final))
            throw new Mensagens("Informe a Data de Emissão para Consulta!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        if (doValida(NR_Nota_Fiscal))
            ed.setNr_nota_fiscal(Integer.parseInt(NR_Nota_Fiscal));
        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (doValida(DT_Emissao_Inicial))
            ed.setDt_emissao_inicial(DT_Emissao_Inicial);
        if (doValida(DT_Emissao_Final))
            ed.setDt_emissao_final(DT_Emissao_Final);
        if (doValida(DT_Entrada))
            ed.setDt_entrada(DT_Entrada);
        if (doValida(DT_Entrada_Final))
            ed.setDt_entrada_final(DT_Entrada_Final);
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Grupo_Nota_Fiscal))
            ed.setOid_grupo_nota_fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (doValida(DM_Pendencia))
            ed.setDm_pendencia(DM_Pendencia);

        new Nota_Fiscal_EletronicaRN().relNFConferencia(ed);
    }
    //*** Notas Fiscais de Entrada volume de compras
    public void relNFVolume_de_Compras(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String Relatorio = request.getParameter("Relatorio");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        String oid_Pessoa = request.getParameter("oid_Pessoa");//*** Fornecedor
        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Pendencia = request.getParameter("FT_DM_Pendencia");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Final");
        String DT_Entrada = request.getParameter("FT_DT_Entrada");
        String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");

        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!doValida(DT_Emissao_Inicial) && !doValida(DT_Emissao_Final) && !doValida(DT_Entrada) && !doValida(DT_Entrada_Final))
            throw new Mensagens("Informe a Data de Emissão para Consulta!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        if (doValida(NR_Nota_Fiscal))
            ed.setNr_nota_fiscal(Integer.parseInt(NR_Nota_Fiscal));
        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (doValida(DT_Emissao_Inicial))
            ed.setDt_emissao_inicial(DT_Emissao_Inicial);
        if (doValida(DT_Emissao_Final))
            ed.setDt_emissao_final(DT_Emissao_Final);
        if (doValida(DT_Entrada))
            ed.setDt_entrada(DT_Entrada);
        if (doValida(DT_Entrada_Final))
            ed.setDt_entrada_final(DT_Entrada_Final);
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Grupo_Nota_Fiscal))
            ed.setOid_grupo_nota_fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (doValida(DM_Pendencia))
            ed.setDm_pendencia(DM_Pendencia);

        new Nota_Fiscal_EletronicaRN().relNFVolume_de_Compras(ed);
    }
    //*** Notas Fiscais de Entrada Emitidas pela Johann
    public void relNFEntrada_Emitida(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String Relatorio = request.getParameter("Relatorio");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        String oid_Pessoa = request.getParameter("oid_Pessoa");//*** Fornecedor
        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Pendencia = request.getParameter("FT_DM_Pendencia");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Final");
        String DT_Entrada = request.getParameter("FT_DT_Entrada");
        String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");
        String NR_Cnpj_Cpf = request.getParameter("FT_NR_CNPJ_CPF");

        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!doValida(DT_Emissao_Inicial) && !doValida(DT_Emissao_Final) && !doValida(DT_Entrada) && !doValida(DT_Entrada_Final))
            throw new Mensagens("Informe a Data de Emissão para Consulta!");
        if (!doValida(NR_Cnpj_Cpf))
            throw new Mensagens("Informe o Fornecedor!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        if (doValida(NR_Nota_Fiscal))
            ed.setNr_nota_fiscal(Integer.parseInt(NR_Nota_Fiscal));
        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (doValida(DT_Emissao_Inicial))
            ed.setDt_emissao_inicial(DT_Emissao_Inicial);
        if (doValida(DT_Emissao_Final))
            ed.setDt_emissao_final(DT_Emissao_Final);
        if (doValida(DT_Entrada))
            ed.setDt_entrada(DT_Entrada);
        if (doValida(DT_Entrada_Final))
            ed.setDt_entrada_final(DT_Entrada_Final);
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Grupo_Nota_Fiscal))
            ed.setOid_grupo_nota_fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (doValida(DM_Pendencia))
            ed.setDm_pendencia(DM_Pendencia);
        if (doValida(NR_Cnpj_Cpf))
            ed.setNr_cnpj_cpf(NR_Cnpj_Cpf);

        new Nota_Fiscal_EletronicaRN().relNFEntrada_Emitida(ed);
    }

    //*** Notas Fiscais de Entrada/Natureza/Tipo
    public void relNFEntradaNT(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String Relatorio = request.getParameter("Relatorio");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        String oid_Pessoa = request.getParameter("oid_Pessoa");//*** Fornecedor
        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Pendencia = request.getParameter("FT_DM_Pendencia");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Final");
        String DT_Entrada = request.getParameter("FT_DT_Entrada");
        String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");
        String NR_Cnpj_Cpf = request.getParameter("FT_NR_CNPJ_CPF");

        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!doValida(DT_Entrada) && !doValida(DT_Entrada_Final))
            throw new Mensagens("Informe a Data de Entrada para Consulta!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        if (doValida(NR_Nota_Fiscal))
            ed.setNr_nota_fiscal(Integer.parseInt(NR_Nota_Fiscal));
        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (doValida(DT_Emissao_Inicial))
            ed.setDt_emissao_inicial(DT_Emissao_Inicial);
        if (doValida(DT_Emissao_Final))
            ed.setDt_emissao_final(DT_Emissao_Final);
        if (doValida(DT_Entrada))
            ed.setDt_entrada(DT_Entrada);
        if (doValida(DT_Entrada_Final))
            ed.setDt_entrada_final(DT_Entrada_Final);
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Grupo_Nota_Fiscal))
            ed.setOid_grupo_nota_fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (doValida(DM_Pendencia))
            ed.setDm_pendencia(DM_Pendencia);
        if (doValida(NR_Cnpj_Cpf))
            ed.setNr_cnpj_cpf(NR_Cnpj_Cpf);

        new Nota_Fiscal_EletronicaRN().relNFEntradaNT(ed);
    }
    //*** Relatório da Evolução dos Vendedores em     Valores /(vendas - Devoluções)
    public void relNFEvolucaoVendedor(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String Relatorio = request.getParameter("Relatorio");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        String oid_Pessoa = request.getParameter("oid_Pessoa");//*** Fornecedor
        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Pendencia = request.getParameter("FT_DM_Pendencia");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Final");
        String DT_Entrada = request.getParameter("FT_DT_Entrada");
        String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");

        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!doValida(DT_Emissao_Inicial) && !doValida(DT_Emissao_Final) && !doValida(DT_Entrada) && !doValida(DT_Entrada_Final))
            throw new Mensagens("Informe a Data de Emissão para Consulta!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        if (doValida(NR_Nota_Fiscal))
            ed.setNr_nota_fiscal(Integer.parseInt(NR_Nota_Fiscal));
        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (doValida(DT_Emissao_Inicial))
            ed.setDt_emissao_inicial(DT_Emissao_Inicial);
        if (doValida(DT_Emissao_Final))
            ed.setDt_emissao_final(DT_Emissao_Final);
        if (doValida(DT_Entrada))
            ed.setDt_entrada(DT_Entrada);
        if (doValida(DT_Entrada_Final))
            ed.setDt_entrada_final(DT_Entrada_Final);
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Grupo_Nota_Fiscal))
            ed.setOid_grupo_nota_fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (doValida(DM_Pendencia))
            ed.setDm_pendencia(DM_Pendencia);

        new Nota_Fiscal_EletronicaRN().relNFEvolucaoVendedor(ed);
    }

    /**
     * Devolução Simbólica Nota Fiscal de Venda
     */
    public void geraNFDevolucaoSimbolica(HttpServletRequest request) throws Exception {

    	String oid_Pessoa = request.getParameter("oid_cliente");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String dt_Emissao_Inicial = request.getParameter("dt_Emissao_Inicial");
        String dt_Emissao_Final = request.getParameter("dt_Emissao_Final");
        String nr_Nota_Fiscal = request.getParameter("nr_Nota_Fiscal");

        if (!doValida(oid_Pessoa))
            throw new Mensagens("Cliente não informado!");
        if (!doValida(oid_Unidade))
            throw new Mensagens("Unidade não informada!");
        if (!doValida(nr_Nota_Fiscal)) {
        	if (!doValida(dt_Emissao_Inicial))
        		throw new Mensagens("Data inicial não informada!");
        	if (!doValida(dt_Emissao_Final))
        		throw new Mensagens("Data final não informada!");
        }

        Nota_Fiscal_EletronicaED edDevolucao = new Nota_Fiscal_EletronicaED();
        if (doValida(nr_Nota_Fiscal))
        	edDevolucao.setNr_nota_fiscal(Integer.parseInt(nr_Nota_Fiscal));
        else {
            edDevolucao.setDt_entrada(dt_Emissao_Inicial);
            edDevolucao.setDt_entrada_final(dt_Emissao_Final);
        }
        edDevolucao.setOid_pessoa(oid_Pessoa);
        edDevolucao.setOID_Unidade_Contabil(Long.parseLong(oid_Unidade));
        boolean finalizado = new Nota_Fiscal_EletronicaRN().geraNFDevolucaoSimbolica(edDevolucao);
        if (!finalizado) throw new Mensagens("Nota Geradas com erros! Veja notas de Devolução Pendentes !");
    }

    public ArrayList listaNFparaFaturamento(HttpServletRequest request) throws Excecoes {

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED(request.getParameter("oid_Nota_Fiscal"));
        ed.setPaginacao(request);

        String oid_Unidade = request.getParameter("oid_Unidade");
        String oid_Pessoa_Remetente = request.getParameter("oid_Pessoa_Remetente");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Vendedor = request.getParameter("oid_Vendedor");
    	String oid_Pessoa_Destinatario = request.getParameter("oid_Cliente");
    	String DT_Emissao = request.getParameter("FT_DT_Emissao");
        String DT_Entrega = request.getParameter("FT_DT_Entrega");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
    	String DT_Entrada = request.getParameter("FT_DT_Entrada");
    	String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        String NM_Serie = request.getParameter("FT_NM_Serie");
        String Dt_Inicial = request.getParameter("FT_DT_Inicial");
        String Dt_Final = request.getParameter("FT_DT_Final");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Estoque = request.getParameter("FT_DM_Estoque");
        String DM_Pendencia = request.getParameter("FT_DM_Pendencia");
        String DM_Impresso = request.getParameter("FT_DM_Impresso");
        String DM_Tipo_Nota_Fiscal = request.getParameter("FT_DM_Tipo_Nota_Fiscal");

        if (doValida(oid_Unidade))
            ed.setOID_Unidade_Contabil(Long.parseLong(oid_Unidade));
        if (doValida(oid_Entregador))
            ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (doValida(oid_Vendedor))
            ed.setOid_Vendedor(oid_Vendedor);

        if (doValida(oid_Pessoa_Remetente))
            ed.setOid_pessoa(oid_Pessoa_Remetente);
        if (doValida(oid_Pessoa_Destinatario))
            ed.setOid_pessoa_destinatario(oid_Pessoa_Destinatario);

        if (doValida(NR_Nota_Fiscal))
            ed.setNr_nota_fiscal(Long.parseLong(NR_Nota_Fiscal));
        if (doValida(NM_Serie))
            ed.setNm_serie(NM_Serie);
        if (doValida(Dt_Inicial))
            ed.setDT_Inicial(Dt_Inicial);
        if (doValida(Dt_Final))
            ed.setDT_Final(Dt_Final);
        if (doValida(DT_Emissao))
            ed.setDt_emissao(DT_Emissao);
        if (doValida(DT_Entrega))
            ed.setDT_Entrega(DT_Entrega);
        if (doValida(DT_Entrega_Final))
            ed.setDT_Entrega_Final(DT_Entrega_Final);
        if (doValida(DT_Entrada))
            ed.setDt_entrada(DT_Entrada);
        if (doValida(DT_Entrada_Final))
            ed.setDt_entrada_final(DT_Entrada_Final);
        if (doValida(DM_Situacao))
            ed.setDM_Situacao(DM_Situacao);
        if (doValida(DM_Estoque))
            ed.setDM_Estoque(DM_Estoque);
        if (doValida(DM_Pendencia))
            ed.setDM_Pendencia(DM_Pendencia);
        if (doValida(DM_Impresso))
            ed.setDM_Impresso(DM_Impresso);
        if (doValida(DM_Tipo_Nota_Fiscal))
            ed.setDm_tipo_nota_fiscal(DM_Tipo_Nota_Fiscal);

        return new Nota_Fiscal_EletronicaRN().listaNFparaFaturamento(ed);

    }

    public String FaturamentoNota_Fiscal(HttpServletRequest request) throws Excecoes {

        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED(request.getParameter("oid_Nota_Fiscal"));
        ed.setPaginacao(request);

        String oid_Unidade = request.getParameter("oid_Unidade");
        String oid_Pessoa_Remetente = request.getParameter("oid_Pessoa_Remetente");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Vendedor = request.getParameter("oid_Vendedor");
    	String oid_Pessoa_Destinatario = request.getParameter("oid_Cliente");
    	String DT_Emissao = request.getParameter("FT_DT_Emissao");
        String DT_Entrega = request.getParameter("FT_DT_Entrega");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
    	String DT_Entrada = request.getParameter("FT_DT_Entrada");
    	String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        String NM_Serie = request.getParameter("FT_NM_Serie");
        String Dt_Inicial = request.getParameter("FT_DT_Inicial");
        String Dt_Final = request.getParameter("FT_DT_Final");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Estoque = request.getParameter("FT_DM_Estoque");
        String DM_Pendencia = request.getParameter("FT_DM_Pendencia");
        String DM_Impresso = request.getParameter("FT_DM_Impresso");
        String DM_Tipo_Nota_Fiscal = request.getParameter("FT_DM_Tipo_Nota_Fiscal");

        if (doValida(oid_Unidade))
            ed.setOID_Unidade_Contabil(Long.parseLong(oid_Unidade));
        if (doValida(oid_Entregador))
            ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (doValida(oid_Vendedor))
            ed.setOid_Vendedor(oid_Vendedor);

        if (doValida(oid_Pessoa_Remetente))
            ed.setOid_pessoa(oid_Pessoa_Remetente);
        if (doValida(oid_Pessoa_Destinatario))
            ed.setOid_pessoa_destinatario(oid_Pessoa_Destinatario);

        if (doValida(NR_Nota_Fiscal))
            ed.setNr_nota_fiscal(Long.parseLong(NR_Nota_Fiscal));
        if (doValida(NM_Serie))
            ed.setNm_serie(NM_Serie);
        if (doValida(Dt_Inicial))
            ed.setDT_Inicial(Dt_Inicial);
        if (doValida(Dt_Final))
            ed.setDT_Final(Dt_Final);
        if (doValida(DT_Emissao))
            ed.setDt_emissao(DT_Emissao);
        if (doValida(DT_Entrega))
            ed.setDT_Entrega(DT_Entrega);
        if (doValida(DT_Entrega_Final))
            ed.setDT_Entrega_Final(DT_Entrega_Final);
        if (doValida(DT_Entrada))
            ed.setDt_entrada(DT_Entrada);
        if (doValida(DT_Entrada_Final))
            ed.setDt_entrada_final(DT_Entrada_Final);
        if (doValida(DM_Situacao))
            ed.setDM_Situacao(DM_Situacao);
        if (doValida(DM_Estoque))
            ed.setDM_Estoque(DM_Estoque);
        if (doValida(DM_Pendencia))
            ed.setDM_Pendencia(DM_Pendencia);
        if (doValida(DM_Impresso))
            ed.setDM_Impresso(DM_Impresso);
        if (doValida(DM_Tipo_Nota_Fiscal))
            ed.setDm_tipo_nota_fiscal(DM_Tipo_Nota_Fiscal);

        return new Nota_Fiscal_EletronicaRN().FaturamentoNotaFiscal(ed);

    }

    public Nota_Fiscal_EletronicaED inclui_Nota_Entrada(HttpServletRequest request) throws Exception {

        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");

        if (!doValida(oid_Nota_Fiscal))
            throw new Excecoes("Nota Fiscal não informada!");
        Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED(oid_Nota_Fiscal);
    	ed.setDM_Pendencia("N");
        ed.setDM_Situacao("A");

        return new Nota_Fiscal_EletronicaRN().inclui_Nota_Entrada(ed);
    }


    public void geraNFe(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        ArrayList lista = new ArrayList();
        if (request.getSession(true).getAttribute("listaNotas") == null)
            lista = this.Nota_Fiscal_Lista(request);
        else lista = (ArrayList) request.getSession(true).getAttribute("listaNotas");
        try{
        	Iterator iterator = lista.iterator();
            while (iterator.hasNext())
            {
            	Nota_Fiscal_EletronicaED ed = (Nota_Fiscal_EletronicaED) iterator.next();
            	if(JavaUtil.doValida(ed.getNfe_chave_acesso()) && JavaUtil.doValida(ed.getNfe_cstat()) && ed.getNfe_cstat().equals("100"))
            		throw new Excecoes("NFe já enviada, consulte novamente para atualizar os dados...");
            }
        	new Nota_Fiscal_EletronicaRN().geraNFe(lista
//        	String arquivo = new Nota_Fiscal_TransacoesRN().geraNotaFiscal_to_NFe(lista
                        ,request.getParameter("FT_DT_Saida")
                        ,request.getParameter("FT_HR_Saida"), response);
//        	new EnviaPDF().enviaArquivoAnexo(response, arquivo, "text/plain");
        } catch (Excecoes e) {
            e.printStackTrace();
            throw e;
        } catch(Exception e){
        	e.printStackTrace();
        	throw new Excecoes();
        }
    }

    public void imprimeDANFE(HttpServletRequest request, HttpServletResponse response) throws Excecoes {


        try{
        	Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED(request.getParameter("oid_Nota_Fiscal"));
        	new Nota_Fiscal_EletronicaRN().imprimeDANFE(ed, response);
        } catch (Excecoes e) {
            e.printStackTrace();
            throw e;
        } catch(Exception e){
        	e.printStackTrace();
        	throw new Excecoes();
        }
    }

    public void enviaNFE_cancelada(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

    	Nota_Fiscal_EletronicaED ed = this.getByRecord(request);
        try{
        	new Nota_Fiscal_EletronicaRN().enviaNFE_cancelada(ed);
        } catch (Excecoes e) {
            e.printStackTrace();
            throw e;
        } catch(Exception e){
        	e.printStackTrace();
        	throw new Excecoes();
        }
    }

}