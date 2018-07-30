/*
 * Created on 27/05/2005
 *
 */
package com.master.iu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Tabela_FreteED;
import com.master.rn.Tabela_FreteRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author java2
 *
 */
public class Tabela_FreteBean extends BancoUtil {
	public Tabela_FreteED inclui(HttpServletRequest request)
	throws Excecoes {
		String oid_Pessoa = request.getParameter("oid_Pessoa");
		String oid_Empresa = request.getParameter("OID_Empresa");
		String oid_Pessoa_Redespacho = request.getParameter("oid_Pessoa_Redespacho");
		String oid_Produto = request.getParameter("oid_Produto");
		String oid_Modal = request.getParameter("oid_Modal");
		String DT_Vigencia = request.getParameter("FT_DT_Vigencia");
		String DT_Validade = request.getParameter("FT_DT_Validade");
		String NR_Prazo_Entrega = request.getParameter("FT_NR_Prazo_Entrega");
		String NR_Prazo_Faturamento = request.getParameter("FT_NR_Prazo_Faturamento");
		String NR_Peso_Inicial = request.getParameter("FT_NR_Peso_Inicial");
		String NR_Peso_Final = request.getParameter("FT_NR_Peso_Final");
		String NR_Peso_Minimo = request.getParameter("FT_NR_Peso_Minimo");
		String DM_Origem = request.getParameter("FT_DM_Origem");
		String DM_Tipo_Pedagio = request.getParameter("FT_DM_Tipo_Pedagio");
		String VL_Pedagio = request.getParameter("FT_VL_Pedagio");
		String VL_Frete_Minimo = request.getParameter("FT_VL_Frete_Minimo");
		String DM_ICMS = request.getParameter("FT_DM_ICMS");
		String DM_Tipo_Tabela = request.getParameter("FT_DM_Tipo_Tabela");
		String DM_Tipo_Peso = request.getParameter("FT_DM_Tipo_Peso");
		String oid_Origem = request.getParameter("oid_Origem");
		String NM_Origem = request.getParameter("FT_NM_Origem");
		String DM_Destino = request.getParameter("FT_DM_Destino");
		String oid_Destino = request.getParameter("oid_Destino");
		String NM_Destino = request.getParameter("FT_NM_Destino");
		String VL_Frete = request.getParameter("FT_VL_Frete");
		String VL_Ademe_Minimo = request.getParameter("FT_VL_Ademe_Minimo");
		String VL_Ad_Valorem_Minimo = request.getParameter("FT_VL_Ad_Valorem_Minimo");
		String VL_Maximo_Nota_Fiscal = request.getParameter("FT_VL_Maximo_Nota_Fiscal");
		String VL_Minimo_Nota_Fiscal = request.getParameter("FT_VL_Minimo_Nota_Fiscal");
		String VL_Despacho = request.getParameter("FT_VL_Despacho");
		String VL_Frete_Valor_Minimo = request.getParameter("FT_VL_Frete_Valor_Minimo");
		String VL_Taxas = request.getParameter("FT_VL_Taxas");
		String PE_Ad_Valorem = request.getParameter("FT_PE_Ad_Valorem");
		String PE_Reembolso = request.getParameter("FT_PE_Reembolso");
		String PE_Reembolso_Minimo = request.getParameter("FT_PE_Reembolso_Minimo");
		String VL_Reembolso = request.getParameter("FT_VL_Reembolso");
		String PE_Devolucao = request.getParameter("FT_PE_Devolucao");
		String PE_Refaturamento = request.getParameter("FT_PE_Refaturamento");
		String PE_Reentrega = request.getParameter("FT_PE_Reentrega");
		String PE_Ademe = request.getParameter("FT_PE_Ademe");
		String VL_Outros1 = request.getParameter("FT_VL_Outros1");
		String VL_Outros2 = request.getParameter("FT_VL_Outros2");

		Tabela_FreteED Tabela_Frete = new Tabela_FreteED();

		if (oid_Pessoa != null && oid_Pessoa.length() > 0) {
			Tabela_Frete.setOID_Pessoa(oid_Pessoa);
		}
		if (oid_Empresa != null && oid_Empresa.length() > 0) {
			Tabela_Frete.setOID_Empresa(new Long(oid_Empresa).longValue());
		}
		if (oid_Pessoa_Redespacho != null && oid_Pessoa_Redespacho.length() > 0) {
			Tabela_Frete.setOID_Pessoa_Redespacho(oid_Pessoa_Redespacho);
		}
		if (oid_Produto != null && oid_Produto.length() > 0) {
			int b = (new Integer(oid_Produto)).intValue();
			Tabela_Frete.setOid_Produto(b);
		}
		if (oid_Modal != null && oid_Modal.length() > 0) {
			int b = (new Integer(oid_Modal)).intValue();
			Tabela_Frete.setOID_Modal(b);
		}
		if (DT_Vigencia != null && DT_Vigencia.length() > 0) {
			Tabela_Frete.setDT_Vigencia(DT_Vigencia);
		}
		if (DT_Validade != null && DT_Validade.length() > 0) {
			Tabela_Frete.setDT_Validade(DT_Validade);
		}
		if (NR_Prazo_Entrega != null && NR_Prazo_Entrega.length() > 0) {
			Tabela_Frete.setNR_Prazo_Entrega(new Long(NR_Prazo_Entrega).longValue());
		}
		if (NR_Prazo_Faturamento != null && NR_Prazo_Faturamento.length() > 0) {
			Tabela_Frete.setNR_Prazo_Faturamento(new Long(NR_Prazo_Faturamento).longValue());
		}
		if (NR_Peso_Inicial != null && NR_Peso_Inicial.length() > 0) {
			Tabela_Frete.setNR_Peso_Inicial(Integer.parseInt(NR_Peso_Inicial));
		}
		if (NR_Peso_Final != null && NR_Peso_Final.length() > 0) {
			Tabela_Frete.setNR_Peso_Final(Integer.parseInt(NR_Peso_Final));
		}
		if (NR_Peso_Minimo != null && NR_Peso_Minimo.length() > 0) {
			Tabela_Frete.setNR_Peso_Minimo(new Long(NR_Peso_Minimo).longValue());
			if (Tabela_Frete.getNR_Peso_Minimo() > Tabela_Frete.getNR_Peso_Final()) {
				Tabela_Frete.setNR_Peso_Minimo(0);
			}
		}
		if (DM_Origem != null && DM_Origem.length() > 0) {
			Tabela_Frete.setDM_Origem(DM_Origem);
		}
		if (DM_Tipo_Pedagio != null && DM_Tipo_Pedagio.length() > 0) {
			Tabela_Frete.setDM_Tipo_Pedagio(DM_Tipo_Pedagio);
		}
		if (VL_Pedagio != null && VL_Pedagio.length() > 0) {
			Tabela_Frete.setVL_Pedagio(new Double(VL_Pedagio).doubleValue());
		}
		if (VL_Frete_Minimo != null && VL_Frete_Minimo.length() > 0) {
			Tabela_Frete.setVL_Frete_Minimo(new Double(VL_Frete_Minimo).doubleValue());
		}
		if (DM_ICMS != null && DM_ICMS.length() > 0) {
			Tabela_Frete.setDM_ICMS(DM_ICMS);
		}
		if (DM_Tipo_Tabela != null && DM_Tipo_Tabela.length() > 0) {
			Tabela_Frete.setDM_Tipo_Tabela(DM_Tipo_Tabela);
		}
		if (DM_Tipo_Peso != null && DM_Tipo_Peso.length() > 0) {
			Tabela_Frete.setDM_Tipo_Peso(DM_Tipo_Peso);
		}
		if (oid_Origem != null && oid_Origem.length() > 0) {
			Tabela_Frete.setOid_Origem((new Integer(oid_Origem)).intValue());
		}
		if (NM_Origem != null && NM_Origem.length() > 0) {
			Tabela_Frete.setNM_Origem(NM_Origem);
		}
		if (DM_Destino != null && DM_Destino.length() > 0) {
			Tabela_Frete.setDM_Destino(DM_Destino);
		}
		if (oid_Destino != null && oid_Destino.length() > 0) {
			Tabela_Frete.setOid_Destino((new Integer(oid_Destino)).intValue());
		}
		if (NM_Destino != null && NM_Destino.length() > 0) {
			Tabela_Frete.setNM_Destino(NM_Destino);
		}
		if (VL_Frete != null && VL_Frete.length() > 0) {
			Tabela_Frete.setVL_Frete(new Double(VL_Frete).doubleValue());
		}
		if (VL_Ademe_Minimo != null && VL_Ademe_Minimo.length() > 0) {
			Tabela_Frete.setVL_Ademe_Minimo(new Double(VL_Ademe_Minimo).doubleValue());
		}
		if (VL_Ad_Valorem_Minimo != null && VL_Ad_Valorem_Minimo.length() > 0) {
			Tabela_Frete.setVL_Ad_Valorem_Minimo(new Double(VL_Ad_Valorem_Minimo).doubleValue());
		}
		if (VL_Maximo_Nota_Fiscal != null && VL_Maximo_Nota_Fiscal.length() > 0) {
			Tabela_Frete.setVL_Maximo_Nota_Fiscal(new Double(VL_Maximo_Nota_Fiscal).doubleValue());
		}
		if (VL_Minimo_Nota_Fiscal != null && VL_Minimo_Nota_Fiscal.length() > 0) {
			Tabela_Frete.setVL_Minimo_Nota_Fiscal(new Double(VL_Minimo_Nota_Fiscal).doubleValue());
		}
		if (VL_Despacho != null && VL_Despacho.length() > 0) {
			Tabela_Frete.setVL_Despacho(new Double(VL_Despacho).doubleValue());
		}
		if (VL_Frete_Valor_Minimo != null && VL_Frete_Valor_Minimo.length() > 0) {
			Tabela_Frete.setVL_Frete_Valor_Minimo(new Double(VL_Frete_Valor_Minimo).doubleValue());
		}
		if (VL_Taxas != null && VL_Taxas.length() > 0) {
			Tabela_Frete.setVL_Taxas(new Double(VL_Taxas).doubleValue());
		}
		if (PE_Ad_Valorem != null && PE_Ad_Valorem.length() > 0) {
			Tabela_Frete.setPE_Ad_Valorem(new Double(PE_Ad_Valorem).doubleValue());
		}
		if (PE_Reembolso != null && PE_Reembolso.length() > 0) {
			Tabela_Frete.setPE_Reembolso(new Double(PE_Reembolso).doubleValue());
		}
		if (PE_Reembolso_Minimo != null && PE_Reembolso_Minimo.length() > 0) {
			Tabela_Frete.setPE_Reembolso_Minimo(new Double(PE_Reembolso_Minimo).doubleValue());
		}
		if (VL_Reembolso != null && VL_Reembolso.length() > 0) {
			Tabela_Frete.setVL_Reembolso(new Double(VL_Reembolso).doubleValue());
		}
		if (PE_Devolucao != null && PE_Devolucao.length() > 0) {
			Tabela_Frete.setPE_Devolucao(new Double(PE_Devolucao).doubleValue());
		}
		if (PE_Refaturamento != null && PE_Refaturamento.length() > 0) {
			Tabela_Frete.setPE_Refaturamento(new Double(PE_Refaturamento).doubleValue());
		}
		if (PE_Reentrega != null && PE_Reentrega.length() > 0) {
			Tabela_Frete.setPE_Reentrega(new Double(PE_Reentrega).doubleValue());
		}
		if (PE_Ademe != null && PE_Ademe.length() > 0) {
			Tabela_Frete.setPE_Ademe(new Double(PE_Ademe).doubleValue());
		}
		if (JavaUtil.doValida(VL_Outros1)) {
			Tabela_Frete.setVL_Outros1(Double.parseDouble(VL_Outros1));
		}
		if (JavaUtil.doValida(VL_Outros2)) {
			Tabela_Frete.setVL_Outros2(Double.parseDouble(VL_Outros2));
		}
		/* Valida se já existe uma tabela com os mesmos dados */
		Tabela_FreteED tabela = new Tabela_FreteED();
		if (new Tabela_FreteRN().tabelaExiste(tabela)) {
		    throw new Mensagens("Já existe uma tabela com estes dados");
		}
		String sql =
		    " INSERT INTO Tabelas_Fretes" +
		    "   (OID_Tabela_Frete, " +
		    "    OID_Produto, " +
		    "    OID_Modal, " +
		    "    OID_Pessoa, " +
		    "    OID_Origem, " +
		    "    OID_Destino, " +
		    "    DM_Origem, " +
		    "    NM_Origem, " +
		    "    DM_Destino, " +
		    "    NM_Destino, " +
		    "    NR_Peso_Inicial, " +
		    "    NR_Peso_Final, " +
		    "    DT_Vigencia, " +
		    "    DT_Validade, " +
		    "    VL_Frete, " +
		    "    VL_Frete_Kg, " +
		    "    VL_Taxas, " +
		    "    PE_Ad_Valorem, " +
		    "    VL_Outros1, " +
		    "    VL_Outros2, " +
		    "    VL_Outros3, " +
		    "    VL_Outros4, " +
		    "    VL_Outros5, " +
		    "    VL_Outros6, " +
		    "    VL_Outros7, " +
		    "    VL_Outros8, " +
		    "    VL_Outros9, " +
		    "    Dt_Stamp, " +
		    "    Usuario_Stamp, " +
		    "    Dm_Stamp, " +
		    "    DM_Tipo_Pedagio, " +
		    "    VL_Pedagio, " +
		    "    VL_Frete_Minimo, " +
		    "    NR_Cotacao, " +
		    "    Oid_Unidade, " +
		    "    OID_Empresa, " +
		    "    OID_Pessoa_Redespacho, " +
		    "    NR_Prazo_Entrega, " +
			"    NR_Prazo_Faturamento, " +
			"    NR_Peso_Minimo, " +
			"    DM_ICMS, " +
			"    DM_Tipo_Tabela, " +
			"    DM_Tipo_Peso, " +
			"    VL_Ademe_Minimo, " +
			"    VL_Ad_Valorem_Minimo, " +
			"    VL_Maximo_Nota_Fiscal, " +
			"    VL_Minimo_Nota_Fiscal, " +
			"    VL_Despacho, " +
			"    PE_Devolucao, " +
			"    PE_Refaturamento, " +
			"    PE_Reentrega, " +
			"    PE_Ademe)" +
		    " VALUES (" + Tabela_Frete.getOid() +
		    "        ," + Tabela_Frete.getOid_Produto() +
		    "        ," + Tabela_Frete.getOID_Modal() +
		    "        ,'" + Tabela_Frete.getOID_Pessoa() + "'" +
		    "        ," + Tabela_Frete.getOid_Origem() +
		    "        ," + Tabela_Frete.getOid_Destino() +
		    "        ,'" + Tabela_Frete.getDM_Origem() + "'" +
		    "        ,'" + Tabela_Frete.getNM_Origem() + "'" +
		    "        ,'" + Tabela_Frete.getDM_Destino() + "'" +
		    "        ,'" + Tabela_Frete.getNM_Destino() + "'" +
		    "        ," + Tabela_Frete.getNR_Peso_Inicial() +
		    "        ," + Tabela_Frete.getNR_Peso_Final() +
		    "        ," + JavaUtil.getSQLDate(Tabela_Frete.getDT_Vigencia()) +
		    "        ," + JavaUtil.getSQLDate(Tabela_Frete.getDT_Validade()) +
		    "        ," + Tabela_Frete.getVL_Frete() +
		    "        ," + Tabela_Frete.getVL_Frete_Kg() +
		    "        ," + Tabela_Frete.getVL_Taxas() +
		    "        ," + Tabela_Frete.getPE_Ad_Valorem() +
		    "        ," + Tabela_Frete.getVL_Outros1() +
		    "        ," + Tabela_Frete.getVL_Outros2() +
		    "        ," + Tabela_Frete.getVL_Outros3() +
		    "        ," + Tabela_Frete.getVL_Outros4() +
		    "        ," + Tabela_Frete.getVL_Outros5() +
		    "        ," + Tabela_Frete.getVL_Outros6() +
		    "        ," + Tabela_Frete.getVL_Outros7() +
		    "        ," + Tabela_Frete.getVL_Outros8() +
		    "        ," + Tabela_Frete.getVL_Outros9() +
		    "        ," + JavaUtil.getSQLDate(Tabela_Frete.getDt_Stamp()) +
		    "        ," + Tabela_Frete.getUsuario_Stamp() +
		    "        ," + JavaUtil.getSQLStringDef(Tabela_Frete.getDm_Stamp(), "") +
		    "        ," + JavaUtil.getSQLStringDef(Tabela_Frete.getDM_Tipo_Pedagio(), "") +
		    "        ," + Tabela_Frete.getVL_Pedagio() +
		    "        ," + Tabela_Frete.getVL_Frete_Minimo() +
		    "        ," + Tabela_Frete.getNr_Cotacao() +
		    "        ," + Tabela_Frete.getOid_Unidade() +
		    "        ," + Tabela_Frete.getOID_Empresa() +
		    "        ,'" + Tabela_Frete.getOID_Pessoa_Redespacho() + "'" +
		    "        ," + Tabela_Frete.getNR_Prazo_Entrega() +
		    "        ," + Tabela_Frete.getNR_Prazo_Faturamento() +
		    "        ," + Tabela_Frete.getNR_Peso_Minimo() +
		    "        ,'" + JavaUtil.getValueDef(Tabela_Frete.getDM_ICMS(), "N") + "'" +
		    "        ," + JavaUtil.getSQLStringDef(Tabela_Frete.getDM_Tipo_Tabela(), "") +
		    "        ," + JavaUtil.getSQLStringDef(Tabela_Frete.getDM_Tipo_Peso(), "") +
		    "        ," + Tabela_Frete.getVL_Ademe_Minimo() +
		    "        ," + Tabela_Frete.getVL_Ad_Valorem_Minimo() +
		    "        ," + Tabela_Frete.getVL_Maximo_Nota_Fiscal() +
		    "        ," + Tabela_Frete.getVL_Minimo_Nota_Fiscal() +
		    "        ," + Tabela_Frete.getVL_Despacho() +
		    "        ," + Tabela_Frete.getPE_Devolucao() +
		    "        ," + Tabela_Frete.getPE_Refaturamento() +
		    "        ," + Tabela_Frete.getPE_Reentrega() +
		    "        ," + Tabela_Frete.getPE_Ademe() + ")";
		inicioTransacao();
		try {
			this.sql.executarUpdate(sql);
			fimTransacao(true);
			return Tabela_Frete;
		} catch (SQLException e) {
			abortaTransacao();
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui(HttpServletRequest request)");
		}
	}
	
	public void altera(HttpServletRequest request)
	throws Excecoes {
		String oid_Tabela_Frete = request.getParameter("oid_Tabela_Frete");
		String oid_Pessoa = request.getParameter("oid_Pessoa");
		String oid_Pessoa_Redespacho = request.getParameter("oid_Pessoa_Redespacho");
		String oid_Produto = request.getParameter("oid_Produto");
		String oid_Modal = request.getParameter("oid_Modal");
		String DT_Vigencia = request.getParameter("FT_DT_Vigencia");
		String DT_Validade = request.getParameter("FT_DT_Validade");
		String oid_Empresa = request.getParameter("OID_Empresa");
		String NR_Prazo_Entrega = request.getParameter("FT_NR_Prazo_Entrega");
		String PE_Reembolso = request.getParameter("FT_PE_Reembolso");
		String PE_Reembolso_Minimo = request.getParameter("FT_PE_Reembolso_Minimo");
		String VL_Reembolso = request.getParameter("FT_VL_Reembolso");
		String NR_Prazo_Faturamento = request.getParameter("FT_NR_Prazo_Faturamento");
		String NR_Peso_Inicial = request.getParameter("FT_NR_Peso_Inicial");
		String NR_Peso_Final = request.getParameter("FT_NR_Peso_Final");
		String NR_Peso_Minimo = request.getParameter("FT_NR_Peso_Minimo");
		String DM_Origem = request.getParameter("FT_DM_Origem");
		String DM_ICMS = request.getParameter("FT_DM_ICMS");
		String DM_Tipo_Tabela = request.getParameter("FT_DM_Tipo_Tabela");
		String DM_Tipo_Peso = request.getParameter("FT_DM_Tipo_Peso");
		String oid_Origem = request.getParameter("oid_Origem");
		String NM_Origem = request.getParameter("FT_NM_Origem");
		String DM_Destino = request.getParameter("FT_DM_Destino");
		String oid_Destino = request.getParameter("oid_Destino");
		String NM_Destino = request.getParameter("FT_NM_Destino");
		String DM_Tipo_Pedagio = request.getParameter("FT_DM_Tipo_Pedagio");
		String VL_Frete = request.getParameter("FT_VL_Frete");
		String VL_Taxas = request.getParameter("FT_VL_Taxas");
		String PE_Ad_Valorem = request.getParameter("FT_PE_Ad_Valorem");
		String PE_Devolucao = request.getParameter("FT_PE_Devolucao");
		String PE_Refaturamento = request.getParameter("FT_PE_Refaturamento");
		String PE_Reentrega = request.getParameter("FT_PE_Reentrega");
		String VL_Frete_Minimo = request.getParameter("FT_VL_Frete_Minimo");
		String VL_Pedagio = request.getParameter("FT_VL_Pedagio");
		String VL_Frete_Valor_Minimo = request.getParameter("FT_VL_Frete_Valor_Minimo");
		String VL_Ad_Valorem_Minimo = request.getParameter("FT_VL_Ad_Valorem_Minimo");
		String PE_Ademe = request.getParameter("FT_PE_Ademe");
		String VL_Ademe_Minimo = request.getParameter("FT_VL_Ademe_Minimo");
		String VL_Maximo_Nota_Fiscal = request.getParameter("FT_VL_Maximo_Nota_Fiscal");
		String VL_Minimo_Nota_Fiscal = request.getParameter("FT_VL_Minimo_Nota_Fiscal");
		String VL_Despacho = request.getParameter("FT_VL_Despacho");
		String VL_Outros1 = request.getParameter("FT_VL_Outros1");
		String VL_Outros2 = request.getParameter("FT_VL_Outros2");

		Tabela_FreteED Tabela_Frete = new Tabela_FreteED();
		if (oid_Tabela_Frete != null && oid_Tabela_Frete.length() > 0) {
			int b = (new Integer(oid_Tabela_Frete)).intValue();
			Tabela_Frete.setOid(b);
		}
		if (oid_Pessoa != null && oid_Pessoa.length() > 0) {
			Tabela_Frete.setOID_Pessoa(oid_Pessoa);
		}
		if (oid_Pessoa_Redespacho != null && oid_Pessoa_Redespacho.length() > 0) {
			Tabela_Frete.setOID_Pessoa_Redespacho(oid_Pessoa_Redespacho);
		}
		if (oid_Produto != null && oid_Produto.length() > 0) {
			int b = (new Integer(oid_Produto)).intValue();
			Tabela_Frete.setOid_Produto(b);
		}
		if (oid_Modal != null && oid_Modal.length() > 0) {
			int b = (new Integer(oid_Modal)).intValue();
			Tabela_Frete.setOID_Modal(b);
		}
		if (DT_Vigencia != null && DT_Vigencia.length() > 0) {
			Tabela_Frete.setDT_Vigencia(DT_Vigencia);
		}
		if (DT_Validade != null && DT_Validade.length() > 0) {
			Tabela_Frete.setDT_Validade(DT_Validade);
		}
		if (oid_Empresa != null && oid_Empresa.length() > 0) {
			Tabela_Frete.setOID_Empresa(new Long(oid_Empresa).longValue());
		}
		if (NR_Prazo_Entrega != null && NR_Prazo_Entrega.length() > 0) {
			Tabela_Frete.setNR_Prazo_Entrega(new Long(NR_Prazo_Entrega).longValue());
		}
		if (PE_Reembolso != null && PE_Reembolso.length() > 0) {
			Tabela_Frete.setPE_Reembolso(new Double(PE_Reembolso).doubleValue());
		}
		if (PE_Reembolso_Minimo != null && PE_Reembolso_Minimo.length() > 0) {
			Tabela_Frete.setPE_Reembolso_Minimo(new Double(PE_Reembolso_Minimo).doubleValue());
		}
		if (VL_Reembolso != null && VL_Reembolso.length() > 0) {
			Tabela_Frete.setVL_Reembolso(new Double(VL_Reembolso).doubleValue());
		}
		if (NR_Prazo_Faturamento != null && NR_Prazo_Faturamento.length() > 0) {
			Tabela_Frete.setNR_Prazo_Faturamento(new Long(NR_Prazo_Faturamento).longValue());
		}
		if (NR_Peso_Inicial != null && NR_Peso_Inicial.length() > 0) {
			Tabela_Frete.setNR_Peso_Inicial(Integer.parseInt(NR_Peso_Inicial));
		}
		if (NR_Peso_Final != null && NR_Peso_Final.length() > 0) {
			Tabela_Frete.setNR_Peso_Final(Integer.parseInt(NR_Peso_Final));
		}
		if (NR_Peso_Minimo != null && NR_Peso_Minimo.length() > 0) {
			Tabela_Frete.setNR_Peso_Minimo(new Long(NR_Peso_Minimo).longValue());
			if (Tabela_Frete.getNR_Peso_Minimo() > Tabela_Frete.getNR_Peso_Final()) {
				Tabela_Frete.setNR_Peso_Minimo(0);
			}
		}
		if (DM_Origem != null && DM_Origem.length() > 0) {
			Tabela_Frete.setDM_Origem(DM_Origem);
		}
		if (DM_ICMS != null && DM_ICMS.length() > 0) {
			Tabela_Frete.setDM_ICMS(DM_ICMS);
			Tabela_Frete.setDM_ICMS("S");
		}
		if (DM_Tipo_Tabela != null && DM_Tipo_Tabela.length() > 0) {
			Tabela_Frete.setDM_Tipo_Tabela(DM_Tipo_Tabela);
		}
		if (DM_Tipo_Peso != null && DM_Tipo_Peso.length() > 0) {
			Tabela_Frete.setDM_Tipo_Peso(DM_Tipo_Peso);
		}
		if (oid_Origem != null && oid_Origem.length() > 0) {
			Tabela_Frete.setOid_Origem((new Integer(oid_Origem)).intValue());
		}
		if (NM_Origem != null && NM_Origem.length() > 0) {
			Tabela_Frete.setNM_Origem(NM_Origem);
		}
		if (DM_Destino != null && DM_Destino.length() > 0) {
			Tabela_Frete.setDM_Destino(DM_Destino);
		}
		if (oid_Destino != null && oid_Destino.length() > 0) {
			Tabela_Frete.setOid_Destino((new Integer(oid_Destino)).intValue());
		}
		if (NM_Destino != null && NM_Destino.length() > 0) {
			Tabela_Frete.setNM_Destino(NM_Destino);
		}
		if (DM_Tipo_Pedagio != null && DM_Tipo_Pedagio.length() > 0) {
			Tabela_Frete.setDM_Tipo_Pedagio(DM_Tipo_Pedagio);
		}
		if (VL_Frete != null && VL_Frete.length() > 0) {
			Tabela_Frete.setVL_Frete(new Double(VL_Frete).doubleValue());
		}
		if (VL_Taxas != null && VL_Taxas.length() > 0) {
			Tabela_Frete.setVL_Taxas(new Double(VL_Taxas).doubleValue());
		}
		if (PE_Ad_Valorem != null && PE_Ad_Valorem.length() > 0) {
			Tabela_Frete.setPE_Ad_Valorem(new Double(PE_Ad_Valorem).doubleValue());
		}
		if (PE_Devolucao != null && PE_Devolucao.length() > 0) {
			Tabela_Frete.setPE_Devolucao(new Double(PE_Devolucao).doubleValue());
		}
		if (PE_Refaturamento != null && PE_Refaturamento.length() > 0) {
			Tabela_Frete.setPE_Refaturamento(new Double(PE_Refaturamento).doubleValue());
		}
		if (PE_Reentrega != null && PE_Reentrega.length() > 0) {
			Tabela_Frete.setPE_Reentrega(new Double(PE_Reentrega).doubleValue());
		}
		if (VL_Frete_Minimo != null && VL_Frete_Minimo.length() > 0) {
			Tabela_Frete.setVL_Frete_Minimo(new Double(VL_Frete_Minimo).doubleValue());
		}
		if (VL_Pedagio != null && VL_Pedagio.length() > 0) {
			Tabela_Frete.setVL_Pedagio(new Double(VL_Pedagio).doubleValue());
		}
		if (VL_Frete_Valor_Minimo != null && VL_Frete_Valor_Minimo.length() > 0) {
			Tabela_Frete.setVL_Frete_Valor_Minimo(new Double(VL_Frete_Valor_Minimo).doubleValue());
		}
		if (VL_Ad_Valorem_Minimo != null && VL_Ad_Valorem_Minimo.length() > 0) {
			Tabela_Frete.setVL_Ad_Valorem_Minimo(new Double(VL_Ad_Valorem_Minimo).doubleValue());
		}
		if (PE_Ademe != null && PE_Ademe.length() > 0) {
			Tabela_Frete.setPE_Ademe(new Double(PE_Ademe).doubleValue());
		}
		if (VL_Ademe_Minimo != null && VL_Ademe_Minimo.length() > 0) {
			Tabela_Frete.setVL_Ademe_Minimo(new Double(VL_Ademe_Minimo).doubleValue());
		}
		if (VL_Maximo_Nota_Fiscal != null && VL_Maximo_Nota_Fiscal.length() > 0) {
			Tabela_Frete.setVL_Maximo_Nota_Fiscal(new Double(VL_Maximo_Nota_Fiscal).doubleValue());
		}
		if (VL_Minimo_Nota_Fiscal != null && VL_Minimo_Nota_Fiscal.length() > 0) {
			Tabela_Frete.setVL_Minimo_Nota_Fiscal(new Double(VL_Minimo_Nota_Fiscal).doubleValue());
		}
		if (VL_Despacho != null && VL_Despacho.length() > 0) {
			Tabela_Frete.setVL_Despacho(new Double(VL_Despacho).doubleValue());
		}
		if (JavaUtil.doValida(VL_Outros1)) {
			Tabela_Frete.setVL_Outros1(Double.parseDouble(VL_Outros1));
		}
		if (JavaUtil.doValida(VL_Outros2)) {
			Tabela_Frete.setVL_Outros2(Double.parseDouble(VL_Outros2));
		}

		String sql =
			" UPDATE Tabelas_Fretes" +
			" SET OID_Produto=" + Tabela_Frete.getOid_Produto() +
			"    ,OID_Modal=" + Tabela_Frete.getOID_Modal() +
			"    ,OID_Origem=" + Tabela_Frete.getOid_Origem() +
			"    ,OID_Destino=" + Tabela_Frete.getOid_Destino() +
			"    ,DM_Origem='" + Tabela_Frete.getDM_Origem() + "'" +
			"    ,NM_Origem='" + Tabela_Frete.getNM_Origem() + "'" +
			"    ,DM_Destino='" + Tabela_Frete.getDM_Destino() + "'" +
			"    ,NM_Destino='" + Tabela_Frete.getNM_Destino() + "'" +
			"    ,NR_Peso_Inicial=" + Tabela_Frete.getNR_Peso_Inicial() +
			"    ,NR_Peso_Final=" + Tabela_Frete.getNR_Peso_Final() +
			"    ,DT_Vigencia=" + JavaUtil.getSQLDate(Tabela_Frete.getDT_Vigencia()) +
			"    ,DT_Validade=" + JavaUtil.getSQLDate(Tabela_Frete.getDT_Validade()) +
			"    ,VL_Frete=" + Tabela_Frete.getVL_Frete() +
			"    ,VL_Frete_Kg=" + Tabela_Frete.getVL_Frete_Kg() +
			"    ,VL_Taxas=" + Tabela_Frete.getVL_Taxas() +
			"    ,PE_Ad_Valorem=" + Tabela_Frete.getPE_Ad_Valorem() +
			"    ,VL_Outros1=" + Tabela_Frete.getVL_Outros1() +
			"    ,VL_Outros2=" + Tabela_Frete.getVL_Outros2() +
			"    ,VL_Outros3=" + Tabela_Frete.getVL_Outros3() +
			"    ,VL_Outros4=" + Tabela_Frete.getVL_Outros4() +
			"    ,VL_Outros5=" + Tabela_Frete.getVL_Outros5() +
			"    ,VL_Outros6=" + Tabela_Frete.getVL_Outros6() +
			"    ,VL_Outros7=" + Tabela_Frete.getVL_Outros7() +
			"    ,VL_Outros8=" + Tabela_Frete.getVL_Outros8() +
			"    ,VL_Outros9=" + Tabela_Frete.getVL_Outros9() +
			"    ,DM_Tipo_Pedagio=" + JavaUtil.getSQLStringDef(Tabela_Frete.getDM_Tipo_Pedagio(), "") +
			"    ,VL_Pedagio=" + Tabela_Frete.getVL_Pedagio() +
			"    ,VL_Frete_Minimo=" + Tabela_Frete.getVL_Frete_Minimo() +
			"    ,OID_Empresa=" + Tabela_Frete.getOID_Empresa() +
			"    ,OID_Pessoa_Redespacho='" + Tabela_Frete.getOID_Pessoa_Redespacho() + "'" +
			"    ,NR_Prazo_Entrega=" + Tabela_Frete.getNR_Prazo_Entrega() +
			"    ,NR_Prazo_Faturamento=" + Tabela_Frete.getNR_Prazo_Faturamento() +
			"    ,NR_Peso_Minimo=" + Tabela_Frete.getNR_Peso_Minimo() +
			"    ,DM_ICMS=" + JavaUtil.getSQLStringDef(Tabela_Frete.getDM_ICMS(), "") +
			"    ,DM_Tipo_Tabela=" + JavaUtil.getSQLStringDef(Tabela_Frete.getDM_Tipo_Tabela(), "") +
			"    ,DM_Tipo_Peso=" + JavaUtil.getSQLStringDef(Tabela_Frete.getDM_Tipo_Peso(), "") +
			"    ,VL_Ademe_Minimo=" + Tabela_Frete.getVL_Ademe_Minimo() +
			"    ,VL_Ad_Valorem_Minimo=" + Tabela_Frete.getVL_Ad_Valorem_Minimo() +
			"    ,VL_Maximo_Nota_Fiscal=" + Tabela_Frete.getVL_Maximo_Nota_Fiscal() +
			"    ,VL_Minimo_Nota_Fiscal=" + Tabela_Frete.getVL_Minimo_Nota_Fiscal() +
			"    ,VL_Despacho=" + Tabela_Frete.getVL_Despacho() +
			"    ,PE_Devolucao=" + Tabela_Frete.getPE_Devolucao() +
			"    ,PE_Refaturamento=" + Tabela_Frete.getPE_Refaturamento() +
			"    ,PE_Reentrega=" + Tabela_Frete.getPE_Reentrega() +
			"    ,PE_Ademe=" + Tabela_Frete.getPE_Ademe() +
			" WHERE OID_Tabela_Frete=" + Tabela_Frete.getOid();
		inicioTransacao();
		try {
			this.sql.executarUpdate(sql);
			fimTransacao(true);
		} catch (SQLException e) {
			abortaTransacao();
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "altera(HttpServletRequest request)");
		}
	}
	
	public void delete(HttpServletRequest request)
	throws Excecoes {
		String oid_Tabela_Frete = request.getParameter("oid_Tabela_Frete");

		Tabela_FreteED Tabela_Frete = new Tabela_FreteED();
		
		if (oid_Tabela_Frete != null && oid_Tabela_Frete.length() > 0) {
			int b = (new Integer(oid_Tabela_Frete)).intValue();
			Tabela_Frete.setOid(b);
		}
		
		String sql =
			"DELETE FROM Tabelas_Fretes WHERE OID_Tabela_Frete = " + Tabela_Frete.getOid();
		inicioTransacao();
		try {
			this.sql.executarUpdate(sql);
			fimTransacao(true);
		} catch (SQLException e) {
			abortaTransacao();
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "delete(HttpServletRequest request)");
		}
	}
	
	public List lista(Tabela_FreteED filtro)
	throws Excecoes {
		List toReturn = new ArrayList();
		String sql =
			" SELECT Tabelas_Fretes.OID_Tabela_Frete " +
			"       ,Tabelas_Fretes.OID_Produto " +
			"       ,Tabelas_Fretes.OID_Modal " +
			"       ,Tabelas_Fretes.OID_Pessoa " +
			"       ,Tabelas_Fretes.NR_Peso_Inicial " +
			"       ,Tabelas_Fretes.NR_Peso_Final " +
			"       ,Tabelas_Fretes.VL_Frete " +
			"       ,Tabelas_Fretes.VL_Frete_Kg " +
			"       ,Tabelas_Fretes.VL_Taxas " +
			"       ,Tabelas_Fretes.PE_Ad_Valorem " +
			"       ,Tabelas_Fretes.VL_Outros1 " +
			"       ,Tabelas_Fretes.VL_Outros2 " +
			"       ,Tabelas_Fretes.VL_Outros3 " +
			"       ,Tabelas_Fretes.VL_Outros4 " +
			"       ,Tabelas_Fretes.VL_Outros5 " +
			"       ,Tabelas_Fretes.VL_Outros6 " +
			"       ,Tabelas_Fretes.VL_Outros7 " +
			"       ,Tabelas_Fretes.VL_Outros8 " +
			"       ,Tabelas_Fretes.VL_Outros9 " +
			"       ,Tabelas_Fretes.Dt_Stamp " +
			"       ,Tabelas_Fretes.Usuario_Stamp " +
			"       ,Tabelas_Fretes.Dm_Stamp " +
			"       ,Pessoas.NM_Razao_Social" +
			"       ,Produtos.CD_Produto " +
			"       ,Produtos.NM_Produto " +
			"       ,Modal.CD_Modal " +
			"       ,Modal.NM_Modal " +
			"       ,Tabelas_Fretes.OID_Origem " +
			"       ,Tabelas_Fretes.OID_Destino " +
			"       ,Tabelas_Fretes.NM_Origem " +
			"       ,Tabelas_Fretes.NM_Destino " +
			"       ,Tabelas_Fretes.DM_Origem " +
			"       ,Tabelas_Fretes.DM_Destino " +
			"       ,Tabelas_Fretes.DT_Vigencia " +
			"       ,Tabelas_Fretes.DT_Validade " +
			"       ,Tabelas_Fretes.DM_Tipo_Pedagio " +
			"       ,Tabelas_Fretes.VL_Pedagio " +
			"       ,Tabelas_Fretes.VL_Frete_Minimo " +
			"       ,Tabelas_Fretes.OID_Empresa " +
			"       ,Tabelas_Fretes.OID_Pessoa_Redespacho " +
			"       ,Tabelas_Fretes.NR_Prazo_Entrega " +
			"       ,Tabelas_Fretes.NR_Prazo_Faturamento " +
			"       ,Tabelas_Fretes.NR_Peso_Minimo " +
			"       ,Tabelas_Fretes.DM_ICMS " +
			"       ,Tabelas_Fretes.DM_Tipo_Tabela " +
			"       ,Tabelas_Fretes.DM_Tipo_Peso " +
			"       ,Tabelas_Fretes.VL_Ademe_Minimo " +
			"       ,Tabelas_Fretes.VL_Ad_Valorem_Minimo " +
			"       ,Tabelas_Fretes.VL_Maximo_Nota_Fiscal " +
			"       ,Tabelas_Fretes.VL_Minimo_Nota_Fiscal " +
			"       ,Tabelas_Fretes.VL_Despacho " +
			"       ,Tabelas_Fretes.PE_Devolucao " +
			"       ,Tabelas_Fretes.PE_Refaturamento " +
			"       ,Tabelas_Fretes.PE_Reentrega " +
			"       ,Tabelas_Fretes.PE_Ademe " +
			"       ,Produtos.DM_Unidade " +
			" FROM Tabelas_Fretes, Pessoas, Modal, Produtos " +
			" WHERE Tabelas_Fretes.OID_Produto = Produtos.OID_Produto AND Tabelas_Fretes.OID_Modal = Modal.OID_Modal AND Tabelas_Fretes.OID_Pessoa = Pessoas.OID_Pessoa  ";
		if (filtro.getOid() > 0) {
		    sql += " AND Tabelas_Fretes.OID_Tabela_Frete = " + filtro.getOid();
		}
		if (JavaUtil.doValida(filtro.getOID_Pessoa())) {
		    sql += " AND Tabelas_Fretes.OID_Pessoa = '" + filtro.getOID_Pessoa() + "'";
		}
		sql += " ORDER BY NM_ORIGEM, NM_DESTINO";
		
		inicioTransacao();
		try {
			ResultSet res = this.sql.executarConsulta(sql);
			try {
				try {
					while (res.next()) {
					    Tabela_FreteED ed = new Tabela_FreteED();
						ed.setOid(res.getLong(1));
						ed.setOid_Produto(res.getLong(2));
						ed.setOID_Modal(res.getInt(3));
						ed.setOID_Pessoa(res.getString(4));
						ed.setNR_Peso_Inicial(res.getInt(5));
						ed.setNR_Peso_Final(res.getInt(6));
						ed.setVL_Frete(res.getDouble(7));
						ed.setVL_Frete_Kg(res.getDouble(8));
						ed.setVL_Taxas(res.getDouble(9));
						ed.setPE_Ad_Valorem(res.getDouble(10));
						ed.setVL_Outros1(res.getDouble(11));
						ed.setVL_Outros2(res.getDouble(12));
						ed.setVL_Outros3(res.getDouble(13));
						ed.setVL_Outros4(res.getDouble(14));
						ed.setVL_Outros5(res.getDouble(15));
						ed.setVL_Outros8(res.getDouble(16));
						ed.setVL_Outros7(res.getDouble(17));
						ed.setVL_Outros6(res.getDouble(18));
						ed.setVL_Outros9(res.getDouble(19));
						ed.setDt_Stamp(res.getString(20));
						ed.setUsuario_Stamp(res.getString(21));
						ed.setDm_Stamp(res.getString(22));
						ed.setNM_Razao_Social(res.getString(23));
						ed.setCD_Produto(res.getString(24));
						ed.setNM_Produto(res.getString(25));
						ed.setCD_Modal(res.getString(26));
						ed.setNM_Modal(res.getString(27));
						ed.setOid_Origem(res.getLong(28));
						ed.setOid_Destino(res.getLong(29));
						ed.setNM_Origem(res.getString(30));
						ed.setNM_Destino(res.getString(31));
						ed.setDM_Origem(res.getString(32));
						ed.setDM_Destino(res.getString(33));
						ed.setDT_Vigencia(res.getString(34));
						ed.setDT_Validade(res.getString(35));
						ed.setDM_Tipo_Pedagio(res.getString(36));
						ed.setVL_Pedagio(res.getDouble(37));
						ed.setVL_Frete_Minimo(res.getDouble(38));
						ed.setOID_Empresa(res.getLong("OID_Empresa"));
						ed.setOID_Pessoa_Redespacho(res.getString("OID_Pessoa_Redespacho"));
						ed.setNR_Prazo_Entrega(res.getLong("NR_Prazo_Entrega"));
						ed.setNR_Prazo_Faturamento(res.getLong("NR_Prazo_Faturamento"));
						ed.setNR_Peso_Minimo(res.getLong("NR_Peso_Minimo"));
						ed.setDM_ICMS(res.getString("DM_ICMS"));
						ed.setDM_Tipo_Tabela(res.getString("DM_Tipo_Tabela"));
						// System.out.println("DM_Tipo_Tabela Banco: " + res.getString("DM_Tipo_Tabela"));
						// System.out.println("DM_Tipo_Tabela objeto: " + ed.getDM_Tipo_Tabela());
						ed.setDM_Tipo_Peso(res.getString("DM_Tipo_Peso"));
						ed.setVL_Ademe_Minimo(res.getDouble("VL_Ademe_Minimo"));
						ed.setVL_Ad_Valorem_Minimo(res.getDouble("VL_Ad_Valorem_Minimo"));
						ed.setVL_Maximo_Nota_Fiscal(res.getDouble("VL_Maximo_Nota_Fiscal"));
						ed.setVL_Minimo_Nota_Fiscal(res.getDouble("VL_Minimo_Nota_Fiscal"));
						ed.setVL_Despacho(res.getDouble("VL_Despacho"));
						ed.setPE_Devolucao(res.getDouble("PE_Devolucao"));
						ed.setPE_Refaturamento(res.getDouble("PE_Refaturamento"));
						ed.setPE_Reentrega(res.getDouble("PE_Reentrega"));
						ed.setPE_Ademe(res.getDouble("PE_Ademe"));
						ed.setDM_Unidade(res.getString("DM_Unidade"));
						toReturn.add(ed);
					}
					return toReturn;
				} catch (SQLException e) {
					throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(Tabela_FreteED filtro)");
				}
			} finally{
				closeResultset(res);
			}
		} finally {
			fimTransacao(false);
		}
	}
	
	public final List getByNR_CNPJ_CPF_Lista(String NR_CNPJ_CPF)
	throws Exception {
	    if (JavaUtil.doValida(NR_CNPJ_CPF)) {
	        Tabela_FreteED filtro = new Tabela_FreteED();
	        filtro.setOID_Pessoa(NR_CNPJ_CPF);
	        return lista(filtro);
	    } else return new ArrayList();
	}

	public final Tabela_FreteBean getByOID(long oid)
	throws Exception {
	    if (oid > 0) {
	        Tabela_FreteED filtro = new Tabela_FreteED();
	        filtro.setOid(oid);
	        List lista = lista(filtro);
	        Iterator iterator = lista.iterator();
	        if (iterator.hasNext()) {
	            return (Tabela_FreteBean)iterator.next();
	        } else return new Tabela_FreteBean();
	    } else return new Tabela_FreteBean();
	}
	public final List getAll()
	throws Exception {
        Tabela_FreteED filtro = new Tabela_FreteED();
        return lista(filtro);
	}
}
