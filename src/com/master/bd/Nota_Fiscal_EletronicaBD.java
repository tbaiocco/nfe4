package com.master.bd;

/**
 * Andr� Valadas
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.master.ed.CompromissoED;
import com.master.ed.ConhecimentoED;
import com.master.ed.DuplicataED;
import com.master.ed.Item_Nota_Fiscal_TransacoesED;
import com.master.ed.Item_PedidoED;
import com.master.ed.Lancamento_ContabilED;
import com.master.ed.Livro_FiscalED;
import com.master.ed.Lote_CompromissoED;
import com.master.ed.Modelo_Nota_FiscalED;
import com.master.ed.Natureza_OperacaoED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.ed.Ocorrencia_Nota_FiscalED;
import com.master.ed.Parametro_WmsED;
import com.master.ed.Parcela_CompromissoED;
import com.master.ed.Parcelamento_FinanceiroED;
import com.master.ed.Pedido_Nota_FiscalED;
import com.master.ed.PessoaED;
import com.master.ed.Posto_CompromissoED;
import com.master.ed.ProdutoED;
import com.master.ed.Situacao_TributariaED;
import com.master.ed.UsuarioED;
import com.master.iu.NFEBean;
import com.master.rn.Nota_Fiscal_EletronicaRN;
import com.master.root.CidadeBean;
import com.master.root.ClienteBean;
import com.master.root.DateFormatter;
import com.master.root.FormataDataBean;
import com.master.root.PessoaBean;
import com.master.root.UnidadeBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.ManipulaString;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

import br.nfe.core.base.EmpresaDb;
import br.cte.model.Empresa;
import br.nfe.model.NfeCce;
import br.nfe.model.NfeDestinatario;
import br.nfe.model.NfeEmitente;
import br.nfe.model.NfeFaturamento;
import br.nfe.model.NfeInutilizacao;
import br.nfe.model.NfeLote;
import br.nfe.model.NfeNotaFiscal;
import br.nfe.model.NfeNotaItem;
import br.nfe.model.NfeReferenciada;
import br.nfe.model.NfeRetornoEnvioLote;
import br.nfe.model.NfeTransporte;
import br.servicos.NfeServicos;
import br.utils.Utils;

import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS40;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TProcEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.inf.portalfiscal.nfe.schema.retEnvEventoCancNFe.TRetEvento;

import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS00;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS10;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS90;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.*;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS60;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp.Transporta;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

public class Nota_Fiscal_EletronicaBD extends BancoUtil {

	private ExecutaSQL executasql;

	FormataDataBean dataFormatada = new FormataDataBean();
	Utilitaria util = new Utilitaria (executasql);

	public Nota_Fiscal_EletronicaBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
	}

	public Nota_Fiscal_EletronicaED inclui(Nota_Fiscal_EletronicaED ed)
			throws Excecoes {

		String sql = null;

		try {

			if (!doValida(ed.getDm_tipo_nota_fiscal()))
				throw new Excecoes("Tipo de Nota n�o informada!");

			//SITUACAO NOVA
			String DM_Nota_Fiscal = getTableStringValue("DM_Nota_Fiscal",	"Modelos_Notas_Fiscais", "oid_modelo_nota_fiscal = '"	+ ed.getOid_modelo_nota_fiscal() + "'");
			if (ed.getNr_nota_fiscal()==0 && "C".equals(DM_Nota_Fiscal)) {
				throw new Excecoes("Modelo de Nota Fiscal exige que seja informado o NR da Nota Fiscal do Cliente!");
			}

			if (ed.getOID_Unidade_Fiscal() == 4){
				ed.setNm_serie("NF7");
			}

			if (ed.getOID_Unidade_Fiscal() == 1 && (ed.getOid_modelo_nota_fiscal() == 13 || ed.getOid_modelo_nota_fiscal() == 15)){
				ed.setNm_serie("NF6");
			}

			if (ed.getNr_nota_fiscal()==0 && "E".equals(DM_Nota_Fiscal)) { //gera numero na INCLUSAO
				if (("D".equals(ed.getDm_tipo_nota_fiscal()) || "S".equals(ed.getDm_tipo_nota_fiscal()) || "E".equals(ed.getDm_tipo_nota_fiscal()))) {
					setNrSerieNotaFromAIDOF(ed, getValueDef(ed.getNm_serie(), "NF1"));
				}
			}
            // Se exige n�mero da nota do cliente a data da emissao fica igual a entrada
            if ("C".equals(DM_Nota_Fiscal)){
            	if (ed.getOid_modelo_nota_fiscal() == 21){
                	ed.setDT_Entrega(ed.getDt_entrada());
            	}else{
                	ed.setDt_emissao(ed.getDt_entrada());
                	ed.setDT_Entrega(ed.getDt_entrada());
            	}
            }else{
            	if (ed.getOid_modelo_nota_fiscal() == 13 || ed.getOid_modelo_nota_fiscal() == 14 || ed.getOid_modelo_nota_fiscal() == 15){
                	ed.setDt_emissao(ed.getDt_entrada());
                	ed.setDT_Entrega(ed.getDt_entrada());
            	}else{
                	ed.setDt_entrada(Data.getDataDMY());
                	ed.setDt_emissao(Data.getDataDMY());
                	ed.setDT_Entrega(Data.getDataDMY());
            	}
            }
			//*** Busca o OID para Nota Fiscal
			ed.setOid_nota_fiscal((ed.getOid_pessoa()+"999999999").substring(0,8) + System.currentTimeMillis());

			//*** Finalizado...
			ed.setDm_finalizado(getValueDef(ed.getDm_finalizado(), "N"));
			sql = " INSERT INTO notas_fiscais ("
					+ "		oid_nota_fiscal"
					+ "		,nr_nota_fiscal"
					+ "		,dt_emissao"
					+ "     ,DT_Entrega"
					+ "		,nr_Itens"
					+ "		,nr_volumes"
					+ "		,nr_peso"
					+ "     ,nr_peso_cubado"
					+ "		,nm_serie"
					+ "		,dt_entrada"
					+ "		,hr_entrada"
					+ "		,oid_pessoa"
					+ "		,oid_pessoa_destinatario"
					+ "		,oid_Pessoa_Consignatario"
					+ "		,oid_Pessoa_Redespacho"
					+ "		,oid_Pessoa_Fornecedor"
					+ "		,oid_natureza_operacao"
					+ "     ,oid_Natureza_Operacao_Servico"
					+ "		,oid_modelo_nota_fiscal"
					+ "		,Oid_Entregador"
					+ "		,vl_total_frete"
					+ "		,vl_icms"
					+ "		,PE_Aliquota_ICMS"
					+ "		,vl_servico"
					+ "		,vl_inss"
					+ "		,vl_irrf"
					+ "		,vl_ipi"
					+ "		,vl_isqn"
					+ "		,vl_total_seguro"
					+ "		,vl_total_despesas"
					+ "		,vl_nota_fiscal"
					+ "		,vl_liquido_nota_fiscal"
					+ "		,dm_tipo_nota_fiscal"
					+ "		,dt_stamp"
					+ "		,dm_observacao"
					+ "		,nm_complemento"
					+ "		,dm_forma_pagamento"
					+ "		,nr_parcelas"
					+ "		,vl_descontos"
					+ "		,VL_Desconto_Itens"
					+ "     ,VL_Adicional"
					+ "     ,VL_Diferenca_Aliq_ICMS"
					+ "		,dm_finalizado"
					+ "		,oid_unidade_fiscal"
					+ "		,oid_unidade"
					+ "		,oid_unidade_contabil"
					+ "		,oid_centro_Custo"
					+ "		,vl_pis"
					+ "		,VL_ICMS_Substituicao"
					+ "		,vl_csll"
					+ "		,DM_Pendencia"
					+ "		,DM_Situacao"
					+ "		,DM_Impresso"
					+ "		,DM_Tipo_Devolucao"
					+ "     ,DM_Estoque"
					+ "     ,oid_Tabela_Venda"
					+ "		,oid_Condicao_Pagamento"
					+ "		,oid_Conta"
					+ "     ,oid_Veiculo"
					+ "     ,oid_Carga_Entrega"
					+ "		,Oid_Nota_Fiscal_Original"
					+ "     ,dm_Devolvido"
					+ "     ,dm_Frete"
					+ "     ,oid_produto )"
					+ "     VALUES ";
			sql += "('"
					+ ed.getOid_nota_fiscal()
					+ "',"
					+ ed.getNr_nota_fiscal()
					+ ", '"
					+ ed.getDt_emissao()
					+ "', '"
					+ ed.getDT_Entrega()
					+ "', "
					+ ed.getNr_Itens()
					+ ", "
					+ ed.getNr_volumes()
					+ ", "
					+ ed.getNR_Peso()
					+ ", "
					+ ed.getNR_Peso_Cubado()
					+ ", '"
					+ ed.getNm_serie()
					+ "', '"
					+ ed.getDt_entrada()
					+ "','"
					+ ed.getHr_entrada()
					+ "', '"
					+ ed.getOid_pessoa()
					+ "', '"
					+ getValueDef(ed.getOid_pessoa_destinatario(), ed.getOid_pessoa()) + "', '"
					+ ed.getOid_Pessoa_Consignatario() + "','"
					+ ed.getOid_Pessoa_Redespacho() + "','"
					+ ed.getOid_pessoa_fornecedor() + "', "
					+ ed.getOid_natureza_operacao() + ", "
					+ ed.getOid_Natureza_Operacao_Servico() + ", "
					+ ed.getOid_modelo_nota_fiscal() + ", "
					+ ed.getOid_Entregador() + ", " + ed.getVl_total_frete()
					+ ", " + ed.getVl_icms() + ", " + ed.getPE_Aliquota_ICMS()
					+ ", " + ed.getVL_Servico() + "," + ed.getVl_inss() + ", "
					+ ed.getVl_irrf() + ", " + ed.getVl_ipi() + ", "
					+ ed.getVl_isqn() + "," + ed.getVl_total_seguro() + ", "
					+ ed.getVl_total_despesas() + ", " + ed.getVl_nota_fiscal()
					+ ", " + ed.getVl_liquido_nota_fiscal() + ", '"
					+ ed.getDm_tipo_nota_fiscal() + "','" + ed.getDt_stamp()
					+ "',";

			if (ed.getDm_observacao() != null
					&& !ed.getDm_observacao().equals("null")
					&& !ed.getDm_observacao().equals("")) {
				sql += "'" + ed.getDm_observacao() + "',";
			} else {
				sql += "null,";
			}
			if (ed.getNm_complemento() != null
					&& !ed.getNm_complemento().equals("null")
					&& !ed.getNm_complemento().equals("")) {
				sql += "'" + ed.getNm_complemento() + "',";
			} else {
				sql += "null,";
			}

			sql += "'" + getValueDef(ed.getDm_forma_pagamento(), "X") + "',"
					+ ed.getNr_parcelas() + "," + ed.getVl_descontos() + ","
					+ ed.getVL_Desconto_Itens() + "," + ed.getVL_Adicional()
					+ "," + ed.getVL_Diferenca_Aliq_ICMS() + ",'"
					+ ed.getDM_Situacao() + "'," // campo dm_finalizado
					+ ed.getOID_Unidade_Fiscal()
					+ "," + ed.getOID_Unidade() + ","
					+ ed.getOID_Unidade_Contabil() + ","
					+ ed.getOid_Centro_Custo() + "," + ed.getVL_Pis() + ","
					+ ed.getVL_ICMS_Subst() + "," + ed.getVL_Csll() + ",'"
					+ ed.getDM_Pendencia() + "'" + ",'" + ed.getDM_Situacao() + "'" + ",'" // campo dm_situacao
					+ getValueDef(ed.getDM_Impresso(), "N") + "'"
					+ ",'" + getValueDef(ed.getDM_Tipo_Devolucao(), "") + "'"
					+ ",'" + getValueDef(ed.getDM_Estoque(), "N") + "'" + ","
					+ ed.getOid_Tabela_Venda() + ","
					+ ed.getOid_Condicao_Pagamento() + "," + ed.getOid_Conta()
					+ ",'" + ed.getOid_Veiculo() + "'" + ","
					+ ed.getOid_Carga_Entrega() + ",'"
					+ ed.getOid_Nota_Fiscal_Original() + "',"
					+ "'N'"
					+ ",'" + getValueDef(ed.getDM_Frete(), "1") + "'" + ","
					+ ed.getOid_produto() + ")";
// .println(sql);
			executasql.executarUpdate(sql);


			//*** Verifica se deve Movimentar Financeiro
			if ("S".equals(getTableStringValue("DM_Movimenta_Financeiro", "Modelos_Notas_Fiscais", "oid_Modelo_Nota_Fiscal = "	+ ed.getOid_modelo_nota_fiscal()))) {
				//*** Grava Parcelamento se for NF de Compra
				if ("E".equals(ed.getDm_tipo_nota_fiscal()))
					this.inclui_Parcelamento(ed, ed.getOid_Condicao_Pagamento(), ed.getVl_liquido_nota_fiscal());
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "inclui()");
		}
		return ed;
	}

	public void altera(Nota_Fiscal_EletronicaED ed) throws Excecoes {

		String sql = null;
		int oidCondicaoPagamento = 0;
		double vlLiquido = 0;

		//*** Valida se existe Itens para essa nota
		if (doExiste("Itens_Notas_Fiscais", "oid_Nota_Fiscal = '"
				+ ed.getOid_nota_fiscal() + "'")) {
			if (getTableDoubleValue("VL_Desconto_Itens", "Notas_Fiscais", "oid_Nota_Fiscal = '" + ed.getOid_nota_fiscal() + "'") != ed.getVL_Desconto_Itens()) {
				throw new Mensagens("Essa Nota Fiscal j� possui Itens Relacionados e calculados referente ao RATEIO do Desconto dos Itens! "
			      					+ "Voc� n�o pode Alterar esse Desconto!");
			}
			if (getTableDoubleValue("VL_Adicional", "Notas_Fiscais", "oid_Nota_Fiscal = '" + ed.getOid_nota_fiscal() + "'") != ed.getVL_Adicional()) {
				throw new Mensagens("Essa Nota Fiscal j� possui Itens Relacionados e calculados referente ao Valor ADICIONAL! "
					     			+ "Voc� n�o pode Alterar esse Valor!");
			}
			//*** N�o permite alterar o valor da NF caso exista itens
			// calculados pelos RATEIOS
			if (ed.getVL_Desconto_Itens() > 0 || ed.getVL_Adicional() > 0) {
				if (getTableDoubleValue("VL_Nota_Fiscal", "Notas_Fiscais",	"oid_Nota_Fiscal = '" + ed.getOid_nota_fiscal() + "'") != ed.getVl_nota_fiscal()) {
					throw new Mensagens(" Essa Nota Fiscal j� possui Itens Relacionados e calculados referente ao RATEIO do Valor dos Itens! "
								       + "Voc� n�o pode Alterar esse Valor!");
				}
			}
		}

    	if (ed.getOid_modelo_nota_fiscal() == 13 || ed.getOid_modelo_nota_fiscal() == 15){
        	ed.setDt_emissao(ed.getDt_entrada());
        	ed.setDT_Entrega(ed.getDt_entrada());
    	}

		try {

			sql = " UPDATE Notas_Fiscais SET ";
			sql += "      NR_Nota_Fiscal =" + ed.getNr_nota_fiscal();
			sql += "     ,DT_Emissao = " + getSQLDate(ed.getDt_emissao());
			sql += "     ,DT_Entrega = " + getSQLDate(ed.getDT_Entrega());
			sql += "     ,NR_Itens =" + ed.getNr_Itens();
			sql += "     ,nr_Volumes =" + ed.getNr_volumes();
			sql += "     ,NR_Peso =" + ed.getNR_Peso();
			sql += "     ,NR_Peso_Cubado =" + ed.getNR_Peso_Cubado();
			sql += "     ,nm_serie ='" + ed.getNm_serie() + "'";
			sql += "     ,dt_entrada =" + getSQLDate(ed.getDt_entrada());
			sql += "     ,hr_entrada ='" + ed.getHr_entrada() + "'";
			sql += "     ,oid_pessoa ='" + ed.getOid_pessoa() + "'";
			sql += "     ,oid_pessoa_Fornecedor ='" + ed.getOid_pessoa_fornecedor() + "'";
			sql += "     ,oid_pessoa_destinatario ='" + ed.getOid_pessoa_destinatario() + "'";
			sql += "     ,oid_Pessoa_Consignatario ='" + ed.getOid_Pessoa_Consignatario() + "'";
			sql += "     ,oid_Pessoa_Redespacho ='" + ed.getOid_Pessoa_Redespacho() + "'";
			sql += "     ,oid_natureza_operacao =" 	+ ed.getOid_natureza_operacao();
			sql += "     ,oid_Natureza_Operacao_Servico =" 	+ ed.getOid_Natureza_Operacao_Servico();
			sql += "     ,oid_modelo_nota_fiscal =" + ed.getOid_modelo_nota_fiscal();
			sql += "     ,Oid_Entregador =" + ed.getOid_Entregador();
			sql += "     ,vl_total_frete =" + ed.getVl_total_frete();
			sql += "     ,vl_icms =" + ed.getVl_icms();
			sql += "     ,PE_Aliquota_ICMS =" + ed.getPE_Aliquota_ICMS();
			sql += "     ,vl_inss =" + ed.getVl_inss();
			sql += "     ,vl_irrf =" + ed.getVl_irrf();
			sql += "     ,vl_ipi =" + ed.getVl_ipi();
			sql += "     ,vl_isqn =" + ed.getVl_isqn();
			sql += "     ,vl_servico =" + ed.getVL_Servico();
			sql += "     ,vl_total_seguro =" + ed.getVl_total_seguro();
			sql += "     ,vl_total_despesas =" + ed.getVl_total_despesas();
			sql += "     ,vl_nota_fiscal =" + ed.getVl_nota_fiscal();
			sql += "     ,vl_liquido_nota_fiscal =" + ed.getVl_liquido_nota_fiscal();
			sql += "     ,dm_tipo_nota_fiscal ='" + ed.getDm_tipo_nota_fiscal() + "'";
			sql += "     ,dt_stamp ='" + ed.getDt_stamp() + "'";

			if (ed.getDm_observacao() == null || ed.getDm_observacao().equals("null")|| ed.getDm_observacao().equals("")) {
				sql += ",dm_observacao = null";
			} else {
				sql += ",dm_observacao = '" + ed.getDm_observacao() + "'";
			}
			if (ed.getNm_complemento() == null 	|| ed.getNm_complemento().equals("null") || ed.getNm_complemento().equals("")) {
				sql += ",nm_complemento = null";
			} else {
				sql += ",nm_complemento = '" + ed.getNm_complemento() + "'";
			}
			sql += "     ,dm_forma_pagamento ='" + ed.getDm_forma_pagamento() + "'";
			sql += "     ,nr_parcelas =" + ed.getNr_parcelas();
			sql += "     ,vl_descontos =" + ed.getVl_descontos();
			sql += "     ,VL_Desconto_Itens =" + ed.getVL_Desconto_Itens();
			sql += "     ,VL_Adicional =" + ed.getVL_Adicional();
			sql += "     ,VL_Diferenca_Aliq_ICMS =" + ed.getVL_Diferenca_Aliq_ICMS();
			sql += "     ,oid_unidade_fiscal =" + ed.getOID_Unidade_Fiscal();
			sql += "     ,oid_unidade =" + ed.getOID_Unidade();
			sql += "     ,oid_centro_Custo =" + ed.getOid_Centro_Custo();
			sql += "     ,vl_pis=" + ed.getVL_Pis();
			sql += "     ,VL_ICMS_Substituicao=" + ed.getVL_ICMS_Subst();
			sql += "     ,vl_csll=" + ed.getVL_Csll();
			sql += "     ,oid_unidade_contabil =" + ed.getOID_Unidade_Contabil();
			sql += "     ,DM_Pendencia = '" + getValueDef(ed.getDM_Pendencia(), "N") + "'";
			sql += "     ,DM_Frete = '" + getValueDef(ed.getDM_Frete(), "1") + "'";
			sql += "     ,oid_Tabela_Venda =" + ed.getOid_Tabela_Venda();
			sql += "     ,oid_Condicao_Pagamento =" + ed.getOid_Condicao_Pagamento();
			sql += "     ,oid_Conta =" + ed.getOid_Conta();
			sql += "     ,oid_Veiculo ='" + ed.getOid_Veiculo() + "'";
			sql += "     ,oid_Nota_Fiscal_Devolucao ='" + ed.getOid_Nota_Fiscal_Devolucao() + "'";

			if (ed.getDm_Devolvido() == null 	|| ed.getDm_Devolvido().equals("null") || ed.getDm_Devolvido().equals("")) {
				sql += "     ,dm_Devolvido = null ";
			} else {
				sql += "     ,dm_Devolvido ='" + ed.getDm_Devolvido() + "'";
			}

			sql += " WHERE oid_nota_fiscal ='" + ed.getOid_nota_fiscal() + "'";

			// .println("UPDATE NOTA FISCAL TRANS 1=" + sql);


			//*** Busca Oid Antigo para comparar em parcelamento
			oidCondicaoPagamento = getTableIntValue("oid_Condicao_pagamento",	"notas_fiscais", "oid_nota_fiscal = '"	+ ed.getOid_nota_fiscal() + "' ");


			// .println("UPDATE NOTA FISCAL TRANS 2=" + sql);

			executasql.executarUpdate(sql);

			//*** Verifica se deve Movimentar Financeiro
			if (!"F".equals(ed.getDM_Situacao())
					&& "S".equals(getTableStringValue("DM_Movimenta_Financeiro", "Modelos_Notas_Fiscais", "oid_Modelo_Nota_Fiscal = " + ed.getOid_modelo_nota_fiscal()))) {
				//*** Verifica para Reparcelamento

				if ("E".equals(ed.getDm_tipo_nota_fiscal())) {
					//*** Busca VL_Liquido Antigo para comparar em parcelamento
					vlLiquido = getTableDoubleValue("VL_Liquido_Nota_Fiscal",	"notas_fiscais", "oid_nota_fiscal = '"	+ ed.getOid_nota_fiscal() + "' ");

					inclui_Parcelamento(ed, oidCondicaoPagamento,	vlLiquido);
				}
			}

			//*** Recalcula pre�o dos itens
			//    Por Condi��o de Pagamento, ou por Tabela de Pre�os
			if (ed.isUpdatePrecosByTabela()
					|| ed.isUpdatePrecosByCond()
					&& new NFEBean().doExisteItensNF(String.valueOf(ed.getOid_nota_fiscal())))
				new Item_Nota_Fiscal_TransacoesBD(executasql).recalculaPrecoItensNota(ed, ed.isUpdatePrecosByTabela(), ed.isUpdatePrecosByCond());

			//*** ICMS
			if ("S".equals(ed.getDm_tipo_nota_fiscal())) {
				this.atualizaValorICMS(ed.getOid_nota_fiscal());
			}

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,	this.getClass().getName(), "altera()");
		}
	}

	public void altera_CFOP(Nota_Fiscal_EletronicaED ed) throws Excecoes {

		String sql = null;

		try {

			sql = " UPDATE Notas_Fiscais SET ";
			sql += "      oid_natureza_operacao ="
					+ ed.getOid_natureza_operacao();
			sql += "     ,oid_Natureza_Operacao_Servico ="
					+ ed.getOid_Natureza_Operacao_Servico();

			if (ed.getDm_observacao() == null
					|| ed.getDm_observacao().equals("null")
					|| ed.getDm_observacao().equals("")) {
				sql += ",dm_observacao = null";
			} else {
				sql += ",dm_observacao = '" + ed.getDm_observacao() + "'";
			}

			sql += " WHERE oid_nota_fiscal ='" + ed.getOid_nota_fiscal() + "'";

			executasql.executarUpdate(sql);

			if (ed.getDm_observacao() != null
					|| !ed.getDm_observacao().equals("null")
					|| !ed.getDm_observacao().equals("")) {
				sql = " UPDATE Livros_Fiscais SET ";
				sql += " tx_observacao = ''" ;
				sql += " ,tx_mensagem = '" + ed.getDm_observacao() + "'";
				sql += " WHERE oid_nota_fiscal ='" + ed.getOid_nota_fiscal() + "'";

				executasql.executarUpdate(sql);
			}

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "altera_CFOP()");
		}
	}

	/**
	 * M�todo para atulizar campos especificos diretos na Nota
	 */
	public void correcao(Nota_Fiscal_EletronicaED ed) throws Excecoes {

		String sql = null;
		try {

			sql = " UPDATE Notas_Fiscais SET "
					+ "      oid_Nota_Fiscal = oid_Nota_Fiscal";
			if (ed.getOid_Entregador() > 0)
				sql += "     ,oid_Entregador = " + ed.getOid_Entregador();
			if (doValida(ed.getOid_Vendedor()))
				sql += "     ,oid_Pessoa_Fornecedor = "
						+ getSQLString(ed.getOid_Vendedor());
			if (doValida(ed.getOid_Veiculo()))
				sql += "     ,oid_Veiculo = "
						+ getSQLString(ed.getOid_Veiculo());

			sql += ed.getSQLSetMasterDetails();
			sql += " WHERE oid_Nota_Fiscal = "
					+ getSQLString(ed.getOid_nota_fiscal());
			executasql.executarUpdate(sql);

			//*** Altera Entregador e Placas tbm nos Pedidos
			if (ed.getOid_Entregador() > 0 || doValida(ed.getOid_Veiculo())) {
				ed.setOid_Pedido(new NFEBean().getOid_Pedido(ed
						.getOid_nota_fiscal()));
				if (doValida(ed.getOid_Pedido())) {
					sql = " UPDATE Pedidos SET "
							+ "     oid_Pedido = oid_Pedido";
					if (ed.getOid_Entregador() > 0)
						sql += "     ,oid_Entregador = "
								+ ed.getOid_Entregador();
					if (doValida(ed.getOid_Vendedor()))
						sql += "     ,oid_Vendedor = "
								+ getSQLString(ed.getOid_Vendedor());
					sql += ed.getSQLSetMasterDetails();
					sql += " WHERE oid_Pedido = "
							+ getSQLString(ed.getOid_Pedido());
					executasql.executarUpdate(sql);
				}
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "correcao()");
		}
	}

	public void deleta(Nota_Fiscal_EletronicaED ed, boolean excluirParcelas)
			throws Excecoes {

        ResultSet res = null;
        String sql = null;

		try {
			//*** Verifica rela��es antes de excluir
			if (!excluirParcelas
					&& doExiste("Parcelamentos_financeiros",
							"oid_Nota_Fiscal = '" + ed.getOid_nota_fiscal()
									+ "'"))
				throw new Mensagens("Exclua todos os parcelamentos primeiro!");

			if ("E".equals(ed.getDm_tipo_nota_fiscal()) || "F".equals(ed.getDm_tipo_nota_fiscal())) {
				//*** Compromissos
				executasql
						.executarUpdate(" DELETE FROM Compromissos as Com"
								+ " WHERE Com.VL_Saldo = Com.VL_Compromisso"
								+ "   AND Com.oid_Compromisso IN (SELECT ParCom.oid_Compromisso"
								+ "                               FROM Parcelas_Compromissos as ParCom"
								+ "                                   ,Parcelamentos_Financeiros as ParFin"
								+ "                               WHERE ParCom.oid_Parcelamento = ParFin.oid_Parcelamento"
								+ "                                 AND ParFin.oid_Nota_Fiscal = '"
								+ ed.getOid_nota_fiscal() + "')");
				executasql
						.executarUpdate(" DELETE FROM Parcelamentos_financeiros"
								+ " WHERE oid_Nota_Fiscal = '"
								+ ed.getOid_nota_fiscal() + "'");

			} else if ("S".equals(ed.getDm_tipo_nota_fiscal())) {

				//*** Exclui Duplicatas
				this.deletaDuplicatas(ed.getOid_nota_fiscal());

				//*** Libera Pedido para gerar nova NF, ou Cancela o mesmo
				if (JavaUtil.doValida(ed.getOid_Pedido())) {
					PedidoBD PedidoBD = new PedidoBD(executasql);
					if ("R".equals(ed.getDM_Tipo_Devolucao())) {
						PedidoBD.atualizaSituacaoPedido(Long.parseLong(ed
								.getOid_Pedido()), "N");
						PedidoBD.atualizaCriticaEstoque(Long.parseLong(ed
								.getOid_Pedido()), "N");
					} else {
						PedidoBD.atualizaSituacaoPedido(Long.parseLong(ed
								.getOid_Pedido()), "C");
					}
				}

			} else if ("D".equals(ed.getDm_tipo_nota_fiscal())) {


	  			  // Insere v�nculos entre as notas fiscais de entrada, sa�da e devolvida.

				  sql = " Select Itens_Notas_Fiscais_Vinculadas.OID_Item_Nota_Fiscal_Entrada, " +
				  		"        Itens_Notas_Fiscais_Vinculadas.NR_QT_Devolucao," +
				  		"        Nota_Entrada.oid_Nota_Fiscal " +
				  		" from " +
				  		" Notas_Fiscais as Nota_Entrada, Itens_Notas_Fiscais as Itens_Entrada, Itens_Notas_Fiscais, Itens_Notas_Fiscais_Vinculadas " +
				  		" WHERE Nota_Entrada.oid_Nota_Fiscal = Itens_Entrada.OID_Nota_Fiscal " +
				  		" AND Itens_Entrada.oid_Item_Nota_Fiscal = Itens_Notas_Fiscais_Vinculadas.OID_Item_Nota_Fiscal_Entrada " +
				  		" AND Itens_Notas_Fiscais.oid_Item_Nota_Fiscal = Itens_Notas_Fiscais_Vinculadas.OID_Item_Nota_Fiscal_Devolvida " +
				  		" AND Itens_Notas_Fiscais.oid_Nota_Fiscal = '" + ed.getOid_nota_fiscal() + "'";

				  res = executasql.executarConsulta(sql);
				  while (res.next()) {

		  			  //Atualiza o item da nfe de entrada
		  			  sql="UPDATE "+
		  			  	  "Itens_Notas_Fiscais "+
		  			  	  "SET "+
		  			  	  "nr_Qt_Devolucao = nr_Qt_Devolucao - "+ res.getDouble("NR_QT_Devolucao") + ", " +
		  			  	  "dm_Devolvido = 'N' "+
		  			  	  "WHERE "+
		  			  	  "oid_Item_Nota_Fiscal = '"+ res.getString("OID_Item_Nota_Fiscal_Entrada") + "'" ;
		  			  executasql.executarUpdate(sql);

	  				  sql="UPDATE "+
  			  	  	  	  "Notas_Fiscais "+
  			  	          "SET "+
  			  	          "dm_Devolvido = 'N' "+
  			  	          "WHERE "+
  			  	          "oid_Nota_Fiscal = '"+ res.getString("oid_Nota_Fiscal") + "'" ;
	  				  executasql.executarUpdate(sql);

	  				  sql="UPDATE "+
			  	  	  	  "Notas_Fiscais "+
			  	          "SET "+
			  	          "dm_Devolvido = 'N' "+
			  	          "WHERE "+
			  	          "oid_nota_fiscal_devolucao = '" + ed.getOid_nota_fiscal() + "'";
     				  executasql.executarUpdate(sql);

				  }

				  // Apagam os itens da Nota Fiscal de Devolu��o

	        	  Item_Nota_Fiscal_TransacoesED itnNF = new Item_Nota_Fiscal_TransacoesED();
	        	  itnNF.setOID_Nota_Fiscal(ed.getOid_nota_fiscal());
	        	  new Item_Nota_Fiscal_TransacoesBD(this.sql).deleta(itnNF);


				//*** Verifica se existe Conhecimento
				if (doExiste("Conhecimentos_Notas_Fiscais",
						"oid_Nota_Fiscal = '" + ed.getOid_nota_fiscal() + "'")) {
					String oid_Conhecimento = getTableStringValue(
							"Conhecimentos_Notas_Fiscais.oid_Conhecimento",
							"Conhecimentos_Notas_Fiscais, Conhecimentos",
							"Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = '"
									+ ed.getOid_nota_fiscal()
									+ "'"
									+ " AND Conhecimentos_Notas_Fiscais.oid_Conhecimento = Conhecimentos.oid_Conhecimento");
					//*** Verifica se existe conhecimento!
					if (doValida(oid_Conhecimento)) {
						String DM_Impresso = getTableStringValue("DM_Impresso",	"Conhecimentos", "oid_Conhecimento = '"	+ oid_Conhecimento + "'");
						//*** Verifica se existe conhecimento ja impresso!
						if ("S".equals(DM_Impresso))
							throw new Mensagens(
									"J� existe um Conhecimento Impresso para essa Nota! Voc� n�o pode exclu�-l�!");
						else
							executasql
									.executarUpdate("DELETE FROM Conhecimentos WHERE oid_Conhecimento = '"
											+ oid_Conhecimento + "'");
					}
				}
			}

			if (doExiste("Lancamentos_Contabeis", "oid_Nota_Fiscal = '"
					+ ed.getOid_nota_fiscal() + "'"))
				throw new Mensagens("Exclua todos os lan�amentos primeiro!");

			//*** Exclui Registros dos Livros Fiscais
			executasql.executarUpdate(" DELETE FROM Livros_Fiscais "
					+ " WHERE oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal()
					+ "'");

			executasql.executarUpdate(" DELETE FROM Notas_Fiscais "
					+ " WHERE oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal()
					+ "'");
			//*** Carga Entrega
			if (ed.getOid_Carga_Entrega() > 0)
				new Carga_EntregaBD(executasql).updateTotais(ed
						.getOid_Carga_Entrega());
		} catch (Mensagens e) {
			throw e;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "deleta()");
		}
	}

	public void deletaDuplicatas(String oid_Nota_Fiscal) throws Excecoes {

		try {
			if (!doValida(oid_Nota_Fiscal))
				throw new Mensagens("Nota Fiscal n�o informada!");

			executasql.executarUpdate(" DELETE FROM Parcelamentos_financeiros"
					+ " WHERE oid_Nota_Fiscal = "
					+ getSQLString(oid_Nota_Fiscal));
			//*** Duplicatas
			executasql
					.executarUpdate(" DELETE FROM Duplicatas "
							+ " WHERE Duplicatas.VL_Saldo = Duplicatas.VL_Duplicata"
							+ "   AND Duplicatas.oid_Duplicata IN (select Origens_Duplicatas.oid_duplicata from Origens_Duplicatas "
							+ "                                     where Origens_Duplicatas.oid_Nota_Fiscal = "
							+ getSQLString(oid_Nota_Fiscal) + ")");
			//*** Origens Duplicatas - [PARCELAS]
			//*** Exclui somente rela��es da Nota q n�o possuam Duplicatas
			executasql
					.executarUpdate(" DELETE FROM Origens_Duplicatas"
							+ " WHERE (Origens_Duplicatas.oid_Nota_Fiscal = "
							+ getSQLString(oid_Nota_Fiscal)
							+ "   AND (select count(1) from duplicatas, Origens_Duplicatas where duplicatas.oid_duplicata = Origens_Duplicatas.oid_duplicata) = 0)");
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "deleta()");
		}
	}

	public void estornaDuplicatas(String oid_Nota_Fiscal) throws Excecoes {

		try {
			if (!doValida(oid_Nota_Fiscal))
				throw new Mensagens("Nota Fiscal n�o informada!");

			//*** Movimentos das Duplicatas
			executasql
					.executarUpdate(" delete from Movimentos_Contas_Correntes "
							+ " WHERE Movimentos_Contas_Correntes.oid_Movimento_Duplicata IN "
							+ "             (select Movimentos_Duplicatas.oid_Movimento_duplicata from Origens_Duplicatas, Duplicatas, Movimentos_Duplicatas "
							+ "                   where Origens_Duplicatas.oid_Nota_Fiscal = "
							+ getSQLString(oid_Nota_Fiscal)
							+ ""
							+ "                   AND Duplicatas.oid_Duplicata = Movimentos_Duplicatas.oid_Duplicata"
							+ "                   AND Duplicatas.oid_Duplicata = Origens_Duplicatas.oid_Duplicata )");

			//*** Movimentos das Duplicatas
			executasql
					.executarUpdate(" delete from Movimentos_Duplicatas "
							+ " WHERE Movimentos_Duplicatas.oid_Duplicata IN (select Origens_Duplicatas.oid_duplicata from Origens_Duplicatas "
							+ "                                     where Origens_Duplicatas.oid_Nota_Fiscal = "
							+ getSQLString(oid_Nota_Fiscal) + ")");

			//*** Duplicatas
			executasql
					.executarUpdate(" update Duplicatas "
							+ " set VL_Saldo = VL_Duplicata"
							+ " WHERE oid_Duplicata IN (select Origens_Duplicatas.oid_duplicata from Origens_Duplicatas "
							+ "                                     where Origens_Duplicatas.oid_Nota_Fiscal = "
							+ getSQLString(oid_Nota_Fiscal) + ")");

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(),
					"estornaDuplicatas(String oid_Nota_Fiscal)");
		}
	}

	public boolean verificaProdutos(Nota_Fiscal_EletronicaED ed)
			throws Excecoes {

		String sql = null;
		ResultSet res = null;
		double VL_Item = 0;
		double VL_IPI = 0;
		double VL_ICMS_Aprov = 0;
		double VL_Desconto = 0;
		double VL_Desconto_Itens = 0;
		double NR_Quantidade = 0;
		try {
			//*** Caso n�o possua itens na NF e N� de itens informados na NF ==
			// 0 n�o verifica produtos
			if (!doExiste("Itens_Notas_Fiscais", "oid_Nota_Fiscal ='"
					+ ed.getOid_nota_fiscal() + "'")
					&& ed.getNr_Itens() <= 0)
				return true;

			//*** Se existe produtos deve atualizar o Estoque!
			ed.setAtualizarEstoque("S".equals(ed.edModelo.getDM_Movimenta_Estoque()));
			//*** Busca Soma dos Valores dos Itens
			sql = " SELECT sum(VL_Item) as VL_Item"
					+ " 	  ,sum(VL_IPI) as VL_IPI"
					+ "       ,sum(VL_ICMS_Aprov) as VL_ICMS_Aprov"
					+ "	      ,sum(VL_Desconto) as VL_Desconto"
					+ "	      ,sum(VL_Desconto_NF) as VL_Desconto_Itens"
					+ "		  ,sum(NR_Quantidade) as NR_Quantidade "
					+ " FROM Itens_notas_fiscais "
					+ " WHERE oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal()
					+ "'";

			// .println(sql);
			res = executasql.executarConsulta(sql);
			while (res.next()) {
				VL_Item = res.getDouble("VL_Item");
				VL_IPI = res.getDouble("VL_IPI");
				VL_ICMS_Aprov = res.getDouble("VL_ICMS_Aprov");
				VL_Desconto = res.getDouble("VL_Desconto");
				VL_Desconto_Itens = res.getDouble("VL_Desconto_Itens");
				NR_Quantidade = res.getDouble("NR_Quantidade");
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "verificaProdutos()");
		} finally {
			super.closeResultset(res);
		}
		//*** Confere se existe produto de Pesagem com Peso Real n�o informado
		if (doExiste(
				"Itens_Notas_Fiscais as IteNot" + " ,Produtos as Pro"
						+ " ,Unidades_Produtos as UniPro",
				"IteNot.oid_Produto = Pro.oid_Produto"
						+ " AND UniPro.oid_Unidade_Produto = Pro.oid_Unidade_Produto"
						+ " AND UniPro.DM_Pesagem = 'S'"
						+ " AND IteNot.NR_Peso_Real <= 0"
						+ " AND IteNot.oid_Nota_Fiscal = "
						+ getSQLString(ed.getOid_nota_fiscal()))) {
			throw new Mensagens(
					"H� Itens com Peso Real n�o informado para a Nota Fiscal N�: "
							+ ed.getNr_nota_fiscal());
		}

		/*** Confere Quantidade
		if (ed.getNr_Itens() != NR_Quantidade)
			throw new Mensagens(
					"A Quantidade dos itens n�o confere com a Nota Fiscal N�: "
							+ ed.getNr_nota_fiscal());
		**/

		//*** Confere IPI
		if (ed.getVl_ipi() > 0 && ed.getVl_ipi() != Valor.round(VL_IPI, 2))
			throw new Mensagens(
					"Soma dos valores do IPI n�o conferem com a Nota Fiscal N�: "
							+ ed.getNr_nota_fiscal());

		//*** Confere ICMS(Toler�ncia de 1)

		// .println(" ");
		// .println(" ");
		// .println(" ");
		// .println(" ");
		// .println(" ");
		// .println(" ");
		// .println(" ");
		// .println("VL_ICMS_Aprov =  "+VL_ICMS_Aprov);
		// .println(" ed.getVl_icms()= "+ed.getVl_icms());
		// .println(" ");
		// .println(" ");
		// .println(" ");
		// .println(" ");
		// .println(" ");
		// .println(" ");
		// .println(" ");
		if (!Valor.validaTolerancia(ed.getVl_icms(), VL_ICMS_Aprov, 1))
			throw new Mensagens(
					"Soma dos valores do ICMS n�o conferem com a Nota Fiscal N�: "
							+ ed.getNr_nota_fiscal());

		//*** Confere Valor(Toler�ncia de 2)

		if (!Valor.validaTolerancia(ed.getVl_liquido_nota_fiscal()
				- ed.getVL_ICMS_Subst() - ed.getVL_Servico()
				- ed.getVl_total_frete() - ed.getVl_total_despesas()
				- ed.getVl_total_seguro() + ed.getVL_Desconto_Itens(), VL_Item
				+ VL_IPI, 2))
			throw new Mensagens(
					"A Soma dos valores dos itens n�o conferem com a Nota Fiscal N�: "
							+ ed.getNr_nota_fiscal());

		//*** Confere Descontos da Nota(Toler�ncia de 0.5)
		if (!Valor.validaTolerancia(ed.getVl_descontos(), VL_Desconto, 0.5))
			throw new Mensagens(
					"Soma dos Descontos n�o conferem com a Nota Fiscal N�: "
							+ ed.getNr_nota_fiscal());

		//*** Confere Descontos dos Itens[RATEIO](Toler�ncia de 1)
		if (!Valor.validaTolerancia(ed.getVL_Desconto_Itens(), VL_Desconto_Itens, 1))
			throw new Mensagens(
					"Soma dos Descontos do RATEIO n�o conferem com o Desconto Itens da Nota Fiscal N�: "
							+ ed.getNr_nota_fiscal());

		return true;
	}

	/*
	 * Passo 2: OS d�bitos e cr�ditos devem gerar numero 0. E o particionamento
	 * do centros de custo para cada lancto deve somar 100% na conta Debito
	 */
	public boolean verificaLancamentos(Nota_Fiscal_EletronicaED ed)
			throws Excecoes {
		String sql = null;
		ResultSet res = null;
		ResultSet r1 = null;
		ResultSet rs = null;
		double Debito = 0, Credito = 0;
		boolean passo = false;
		boolean existe_lacto = false;

		try {
			//Verifica todos os lancamentos daquela nota fiscal
			sql = "Select oid_lancamento, dm_acao, vl_lancamento "
					+ " from Lancamentos_Contabeis "
					+ " Where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal()
					+ "'";
			res = executasql.executarConsulta(sql);

			while (res.next()) {
				sql = "Select sum(vl_lancamento) as Debito from Lancamentos_Contabeis  Where oid_Nota_Fiscal ='"
						+ ed.getOid_nota_fiscal() + "' and dm_acao='D'";
				r1 = executasql.executarConsulta(sql);
				while (r1.next()) {
					Debito = r1.getDouble("Debito");
				}
				r1 = null;
				sql = "Select sum(vl_lancamento) as Credito from Lancamentos_Contabeis  Where oid_Nota_Fiscal ='"
						+ ed.getOid_nota_fiscal() + "' and dm_acao='C'";
				r1 = executasql.executarConsulta(sql);
				while (r1.next()) {
					Credito = r1.getDouble("Credito");
				}
				existe_lacto = true;
			}

			//Primeiro verifica se houveram lan�entos
			if (existe_lacto) {
				passo = true;
			} else {
				passo = false;
				throw new Mensagens(
						"A nota fiscal nao pode ser finalizada sem lancamentos");
			}
			//Depois se os lan�entos fecham a regra de partidas dobradas
			if (((Debito - Credito) == 0) && (passo)) {
				passo = true;
			} else {
				passo = false;
				throw new Mensagens(
						"O somatorio dos lancamentos esta incorreto. Valor da diferen�a "
								+ (Debito - Credito));
			}

			//Para ent�verificar se as aloca�s entre contas cont�is fecham o
			// esperado
			if (passo) {
				//Verifica o pior caso!!
				sql = "select sum(lcc.vl_lancamento) as VL_Lancamento ";
				sql += "from lancamentos_centros_custos lcc, lancamentos_contabeis lc ";
				sql += "where lc.oid_nota_fiscal = '" + ed.getOid_nota_fiscal()
						+ "' ";
				sql += "and lcc.oid_lancamento = lc.oid_lancamento ";
				sql += "and dm_acao='C' ";
				rs = executasql.executarConsulta(sql);
				while (rs.next()) {
					if (rs.getDouble("VL_Lancamento") > 0) {
						passo = false;
						throw new Mensagens(
								"As alocacoes em centros de custo devem ser gravadas na conta de debito");
					}
				}
				rs = null;
				sql = "select oid_lancamento "
						+ "from  lancamentos_contabeis lc,  contas ct "
						+ "where lc.oid_nota_fiscal = '"
						+ ed.getOid_nota_fiscal() + "' "
						+ "and ct.oid_conta = lc.oid_conta "
						+ "and dm_acao='D' and ct.dm_despesa = 'S'";

				res = executasql.executarConsulta(sql);

				while (res.next()) {
					//J�ei que existem lancamentos de despesa
					sql = "Select sum(vl_lancamento) as VL_Lancamento  from lancamentos_centros_custos where oid_lancamento in(";
					sql += "select oid_lancamento "
							+ "from  lancamentos_contabeis lc,  contas ct "
							+ "where lc.oid_nota_fiscal = '"
							+ ed.getOid_nota_fiscal() + "' "
							+ "and ct.oid_conta = lc.oid_conta "
							+ "and dm_acao='D' and ct.dm_despesa = 'S')";

					rs = executasql.executarConsulta(sql);

					while (rs.next()) {
						if (rs.getDouble("VL_Lancamento") > 0) {
							if (!((rs.getDouble("VL_Lancamento") == ed
									.getVl_nota_fiscal()) && (rs
									.getDouble("VL_Lancamento") == Debito))) {
								passo = false;
								throw new Mensagens(
										"A alocacao entre centros de custos deve estar na conta de debito que contenha o valor bruto");
							}
						} else {
							passo = false;
							throw new Mensagens(
									"A alocacao entre centros de custos na conta de debito deve ser maior que zero");
						}
					}
				}
			}
		} catch (Mensagens e) {
			throw e;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "verificaLancamentos()");
		}
		return passo;
	}

	/* Passo 3: As parcelas de pagamento devem somar 100% */
	public boolean verificaParcelamentos(Nota_Fiscal_EletronicaED ed)
			throws Excecoes {

		String sql = null;
		ResultSet res = null;
		boolean bValida = false;
		try {
			//*** Verifica se Modelo n�o Movimenta Financeiro ou
			//    Caso n�o possua Parcelamento da NF n�o verifica Valores
			if ("N".equals(ed.edModelo.getDM_Movimenta_Financeiro())
					|| !doExiste("Parcelamentos_Financeiros",
							"oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal()
									+ "'"))
				return true;

			sql = " SELECT sum(vl_parcela) as parcelas"
					+ " FROM Parcelamentos_Financeiros "
					+ " WHERE oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal()
					+ "'";

			res = executasql.executarConsulta(sql);
			while (res.next()) {
				//*** Verifica o Valor das Parcelas com a]o Valor Liquido da NF
				if (res.getDouble("parcelas") != ed.getVl_liquido_nota_fiscal()) {
					throw new Mensagens(
							"Valor liquido n�o condiz com o valor total das parcelas! Nota N�: "
									+ ed.getNr_nota_fiscal());
				} else
					bValida = true;
			}
		} catch (Mensagens e) {
			throw e;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "verificaParcelamentos()");
		} finally {
			closeResultset(res);
		}
		return bValida;
	}

	/* Passo 4: O valor cont�bil deve coincidir com o Valor L�quido da NF */
	public boolean verificaFiscal(Nota_Fiscal_EletronicaED ed) throws Excecoes {

		try {
			//*** Verifica se Modelo n�o gera Fiscal ou
			if ("N".equals(ed.edModelo.getDM_Gera_Fiscal()) || "18".equals(String.valueOf(ed.edModelo.getOid_Modelo_Nota_Fiscal())))
				return true;
			//*** Verifica se existe Livro Fiscal
			if (!doExiste("Livros_Fiscais", " oid_Nota_Fiscal ='"
					+ ed.getOid_nota_fiscal() + "'"))
				new Livro_FiscalBD(executasql).geraLivro_Fiscal_Entradas(
						new Livro_FiscalED(ed.getOid_nota_fiscal(), "NF"), "E");
			//throw new Mensagens("Voc� precisa gerar o Livro Fiscal primeiro!
			// Nota N�: "+ed.getNr_nota_fiscal());
			//*** Verifica o Valor Cont�bil com o Valor da NF
			double vlContabil = getTableDoubleValue("sum(VL_Contabil)",
					"Livros_Fiscais", "oid_Nota_Fiscal ='"
							+ ed.getOid_nota_fiscal() + "'"
							+ " AND oid_Pessoa_Emitente ='"
							+ ed.getOid_pessoa() + "'");
			if (vlContabil != Valor.round(ed.getVl_liquido_nota_fiscal(), 2))
				throw new Mensagens(
						"Valor da NF n�o condiz com o valor cont�bil do Livro Fiscal! Nota N�: "
								+ ed.getNr_nota_fiscal());
			return true;

		} catch (Mensagens e) {
			throw e;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "verificaFiscal()");
		}
	}

	//*** Finaliza Nota Fiscal de Entrada
	public void finalizaNF_Entrada(Nota_Fiscal_EletronicaED ed) throws Excecoes {

		String sql = null;
		ResultSet res = null;
		ResultSet res2 = null;
		int oid_Pedido_Nota_Fiscal = 0;
		try {

			//*** Verifica se deve Gerar COMPROMISSOS - [Contas a Pagar]
			if ("S".equals(ed.edModelo.getDM_Movimenta_Financeiro()))
				this.geraCompromisso(ed);

			//*** Verifica se Existe ItemNF vinculado a Item do Pedido
			if (new NFEBean().doExisteItensPedido(ed.getOid_nota_fiscal())) {
				//*** Busca todos Pedidos relacionados a essa NF
				sql = " SELECT DISTINCT oid_Pedido "
						+ " FROM Itens_Notas_Fiscais "
						+ "	WHERE oid_Pedido > 0"
						+ "   AND oid_Nota_Fiscal = '"
						+ ed.getOid_nota_fiscal() + "'";

				res = executasql.executarConsulta(sql);
				oid_Pedido_Nota_Fiscal = getMaximo("oid_Pedido_Nota_Fiscal",
						"Pedidos_Notas_Fiscais", "");
				while (res.next()) {
					//*** Busca Itens do Pedido Relacionado a NF
					sql = " SELECT DISTINCT Itens_Pedidos.oid_Item_Pedido"
							+ " FROM Itens_Pedidos, Itens_Notas_Fiscais, Pedidos, Notas_Fiscais"
							+ " WHERE Itens_Pedidos.oid_Pedido = Itens_Notas_Fiscais.oid_Pedido"
							+ "   AND Itens_Pedidos.oid_Item_Pedido = Itens_Notas_Fiscais.oid_Item_Pedido"
							+ "   AND Itens_Notas_Fiscais.oid_Pedido = Pedidos.oid_Pedido"
							+ "   AND Itens_Notas_Fiscais.oid_Nota_fiscal = Notas_Fiscais.oid_Nota_fiscal"
							+ "   AND Notas_Fiscais.oid_Nota_fiscal = '"
							+ ed.getOid_nota_fiscal() + "'"
							+ "   AND Itens_Notas_Fiscais.oid_Pedido = "
							+ res.getLong("oid_Pedido");
					res2 = executasql.executarConsulta(sql);
					while (res2.next()) {
						//*** Compara quantidade do QTD_PEDIDO com QTD_Atendida
						// para ver de deve Finalizar o ITEM
						double QT_Pedido = getTableDoubleValue(
								"sum(NR_QT_Pedido)", "Itens_Pedidos",
								"oid_Item_Pedido = "
										+ res2.getInt("oid_Item_Pedido"));
						double QT_Atendido = getTableDoubleValue(
								"sum(NR_QT_Atendido)", "Itens_Pedidos",
								"oid_Item_Pedido = "
										+ res2.getInt("oid_Item_Pedido"));
						if (QT_Pedido == QT_Atendido) {
							//*** Chama m�todo para FINALIZAR o ITEM DO PEDIDO
							new Item_PedidoBD(executasql)
									.atualizaSituacaoItemPedido(new Item_PedidoED(
											res2.getLong("oid_Item_Pedido"),
											res.getLong("oid_Pedido"), "F"));
							//*** ATUALIZA Valores no Pedido
							if ("C".equals(ed.getDM_Situacao()))
								new PedidoBD(executasql).total_Pedido(res
										.getLong("oid_Pedido"));
						}
					}
					//*** Verifica se todos Itens_do_Pedido ent�o como
					// Finalizados para FINALIZAR O PEDIDO
					if (!doExiste("Itens_Pedidos", "oid_Pedido = "
							+ res.getLong("oid_Pedido")
							+ " AND DM_Situacao != 'F'")) {
						new PedidoBD(executasql).atualizaSituacaoPedido(res
								.getLong("oid_Pedido"), "F");
					}
					//*** Insere registro na Tabela Pedidos_Notas_Fiscais
					new Pedido_Nota_FiscalBD(executasql)
							.inclui(new Pedido_Nota_FiscalED(
									++oid_Pedido_Nota_Fiscal, res
											.getInt("oid_Pedido"), ed
											.getOid_nota_fiscal(), "E", "F"));
				}
			}
			sql = " UPDATE " +
				  " notas_fiscais SET " +
				  "	DM_Finalizado = 'F'" +
				  "	,DT_Finalizado = '" + Data.getDataDMY() + "'" +
				  "	,HR_Finalizado = '" + Data.getHoraHM() + "'" +
				  " WHERE oid_Nota_Fiscal = '" 	+ ed.getOid_nota_fiscal() + "'";

			executasql.executarUpdate(sql);

			/*** CALCULA O CUSTO DOS PRODUTOS SOMENTE SE FOR NOTA DE COMPRA.
			 *** PARA NF DE DEVOLUCAO O CUSTO FICA O DO PRODUTOS_CLIENTES ***/
			if (!doValida(ed.getOid_Nota_Fiscal_Original()) && !"15".equals(String.valueOf(ed.edModelo.getOid_Modelo_Nota_Fiscal()))) {
				new Item_Nota_Fiscal_TransacoesBD(executasql).calcularCustoDaCompra(ed);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "finaliza()");
		} finally {
			closeResultset(res);
			closeResultset(res2);
		}
	}

	//*** Finaliza NF de Saida
	public void finalizaNF_Saida(Nota_Fiscal_EletronicaED ed) throws Excecoes {

		String sql = null;
		double NR_Quantidade = ed.getNr_volumes();

		try {

			sql = " UPDATE Notas_Fiscais SET"
				+ " DM_Finalizado = 'F'"
				+ ",DM_Impresso = '" + getValueDef(ed.getDM_Impresso(), "N") + "'"
				+ ",DM_Estoque = '" + getValueDef(ed.getDM_Estoque(), "N") + "'"
				+ ",DM_Pendencia = '" + ed.getDM_Pendencia() + "'"
				+ ",DT_Entrega = " + getSQLDate(ed.getDT_Entrega())
				+ ",HR_Entrada = " + getSQLString(ed.getHr_entrada())
				+ ",DT_Finalizado = '" + Data.getDataDMY() + "'"
				+ ",HR_Finalizado = '" + Data.getHoraHM() + "'"
				+ ",oid_Carga_Entrega = " + ed.getOid_Carga_Entrega()
				+ " WHERE oid_Nota_Fiscal = '" + ed.getOid_nota_fiscal() + "'";
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "finalizaNF_Saida()");
		}
	}

	/** D� a baixa da devolucao simbolica na nota de entrada
	 *  Coloca na observacao da nf de devolucao os numeros da nfs de entrada correspondente
	 *  Caso n�o consiga achar uma nf de entrada com o item a ser devolvido cria uma
	 *  ocorrencia para a nf de devolu�ao e retorna false para a rotina chamadora.
	 **/
	public boolean  baixaDevolucaoSimbolica(Nota_Fiscal_EletronicaED ed) throws Excecoes {

		String sql = null;
		String NR_Lote_Produto = "";
		ResultSet res = null;
		int Itens_Nao_Devolvidos = 0;
		try {

			// D� baixa no item da nf de entrada
	        ArrayList lstNFi = new ArrayList(); // Cont�m os itens da nota de devolu��o
	        ArrayList lstNFe = new ArrayList(); // Cont�m os n�meros das notas de entrada que tiveram itens devolvidos
	        ArrayList lstNF = new ArrayList();  // Cont�m os n�meros das notas j� retonados
	        String NF_nr ="";					// String com os n�meros das notas separados por virgula
	        String NF_Lote ="";					// String com os n�meros das lotes separados por virgula
	        String Observacao ="";
	        String NM_Complemento = "";         // Complemento da observacao, com: Nome, Endere�o, IE e CNPJ do destinat�rio.
        	// Busca todos os itens da nota fiscal de devolucao
        	Item_Nota_Fiscal_TransacoesED edNFi = new Item_Nota_Fiscal_TransacoesED();
        	edNFi.setOID_Nota_Fiscal(ed.getOid_nota_fiscal());
        	lstNFi = new Item_Nota_Fiscal_TransacoesBD(this.sql).lista(edNFi);
        	//Itera os itens da nota de devolucao para buscar nas notas de entrada
        	for (int x = 0; x <lstNFi.size(); x++){
        		edNFi = (Item_Nota_Fiscal_TransacoesED)lstNFi.get(x);
        		// D� baixa da quantidade devolvida da nota fiscal original de entrada, retorna array c/nrs das notas originais
        		lstNFe =  new Item_Nota_Fiscal_TransacoesBD(this.sql).devolveItem(edNFi, edNFi.getOid_Produto_Cliente(),edNFi.getNR_Quantidade());
        		for (int y = 0; y < lstNFe.size(); y++) {
        			String nr_Nota = (String)lstNFe.get(y);
        			if (!lstNF.contains(nr_Nota)) { // Verifica se nr nf n�o est� no array
    					lstNF.add(nr_Nota);
        			}
        		}
        	}
        	// Altera a nota fiscal de devolu��o colocando os nrs das notas originais na observacao.
    		for (int y = 0; y < lstNF.size(); y++) { // Itera o array de nrs nf para formar um string �nico para a observa��o
    			if ( String.valueOf(lstNF.get(y)) != null && String.valueOf(lstNF.get(y)) != "null"){
    				NF_nr = NF_nr +", " + lstNF.get(y);
    			}
    		}
    		//Procura se h� algum item que n�o pode ser devolvido
    		sql = "Select "+
    			  "cd_produto "+
  			  	  "FROM "+
  				  "Itens_Notas_Fiscais, "+
  				  "Produtos "+
  				  "WHERE "+
  				  "Oid_Nota_Fiscal = '" + ed.getOid_nota_fiscal() + "' and "+
  				  "dm_Devolvido = 'X' and " +
  				  "produtos.oid_produto = itens_notas_fiscais.oid_produto ";
    		res = executasql.executarConsulta(sql);
    		while(res.next()){
            	Ocorrencia_Nota_FiscalED ocoNF = new Ocorrencia_Nota_FiscalED();
            	ocoNF.setOID_Nota_Fiscal(ed.getOid_nota_fiscal());
            	ocoNF.setTX_Observacao("N�o existe quantidade do item em NF de Entrada para devolu��o ! Produto = " + res.getString("cd_Produto"));
            	ocoNF.setDM_Pendencia("S");
            	ocoNF.setOID_Tipo_Ocorrencia(116);
            	ocoNF.setDT_Ocorrencia_Nota_Fiscal(Data.getDataDMY());
            	ocoNF.setHR_Ocorrencia_Nota_Fiscal(Data.getHoraHM());
            	ocoNF.setDM_Origem_Ocorrencia("I");
            	new Ocorrencia_Nota_FiscalBD(executasql).inclui(ocoNF);
            	Itens_Nao_Devolvidos++;
    		}
    		if (Itens_Nao_Devolvidos <= 0){
	    		// Anota as nfs retornadas nas observacoes da nota de devolucao
	            Parametro_WmsED edPWms = new Parametro_WmsED();
	            edPWms = new Parametro_WmsBD(this.sql).getByRecord(edPWms);
	    		if (doValida(ed.getDm_observacao())) Observacao = ed.getDm_observacao();
	    		Observacao = edPWms.getNm_Observacao_Devolucao() + " "  + Observacao ;
	    		sql = "Select "+
  			  		  "distinct(Itens_Notas_Fiscais.NR_Lote_Produto) as NR_Lote_Produto "+
			  	      "FROM "+
				      "Notas_Fiscais, "+
	  				  "Itens_Notas_Fiscais "+
				      "WHERE "+
	  				  "Notas_Fiscais.Oid_Nota_Fiscal = Itens_Notas_Fiscais.Oid_Nota_Fiscal and "+
				      "Notas_Fiscais.Oid_Nota_Fiscal = '" + ed.getOid_nota_fiscal() + "' and " +
				      "Itens_Notas_Fiscais.NR_Lote_Produto is not null " +
	    			  " Order by Itens_Notas_Fiscais.NR_Lote_Produto";
	    		res = executasql.executarConsulta(sql);
	    		NR_Lote_Produto = " Lote Produto: ";
	    		boolean dm_tem_produto_com_lote = false;
	    		while(res.next()){
	    			lstNFe.add(res.getString("NR_Lote_Produto"));
	    			dm_tem_produto_com_lote = true;
	    		}
	    		if (dm_tem_produto_com_lote){

	    			for (int y = 0; y < lstNFe.size(); y++) {
	        			NR_Lote_Produto = (String)lstNFe.get(y);
	        			if (!lstNF.contains(NR_Lote_Produto)) { // Verifica se nr nf n�o est� no array
	    					lstNF.add(NR_Lote_Produto);
	        			}
	        		}
	        		// Altera a nota fiscal de devolu��o colocando os nrs das notas originais na observacao.
	        		for (int y = 0; y < lstNF.size(); y++) { // Itera o array de nrs nf para formar um string �nico para a observa��o
	        			if ( String.valueOf(lstNF.get(y)) != null && String.valueOf(lstNF.get(y)) != "null"){
	        				if(!JavaUtil.doValida(NF_Lote)){
	        					NF_Lote = String.valueOf(lstNF.get(y));
	        				} else {
	        				NF_Lote = NF_Lote +", " + lstNF.get(y);
	        			}
	        		}
	        		}
//	        		NR_Lote_Produto = NR_Lote_Produto + NF_Lote;
	        		NR_Lote_Produto = NF_Lote;

	    		}else {
	    			NR_Lote_Produto = "";
	    		}

	    		if ("S".equals(ed.getDm_tipo_nota_fiscal())){

	//	    		 dados do destinatario (da NF de SAIDA) no complemento da observacao
		    		String oid_destinatario = "";
		    		sql = "Select "+
			  			  "oid_pessoa_destinatario "+
			  			  "FROM "+
			  			  "Notas_Fiscais "+
			  			  "WHERE "+
			  			  "Oid_Nota_Fiscal = '" + ed.getOid_Nota_Fiscal_Original() + "' ";

		    		// .println(sql);
			  		res = executasql.executarConsulta(sql);
			  		while(res.next()){
			  			oid_destinatario = res.getString(1);
			  		}
		    		PessoaBean destinatario = PessoaBean.getByOID(oid_destinatario);
		    		if(JavaUtil.doValida(destinatario.getNM_Razao_Social())){
		    			NM_Complemento = "DEST.: " + destinatario.getNM_Razao_Social();
		    			NM_Complemento += " | CNPJ: " + destinatario.getNR_CNPJ_CPF();
		    			if(JavaUtil.doValida(destinatario.getNM_Inscricao_Estadual())){
			    			NM_Complemento += " | I.E.: " + destinatario.getNM_Inscricao_Estadual();
			    		}
		    			NM_Complemento += ";";
		    			NM_Complemento += "END.: " + destinatario.getNM_Endereco();
		    			NM_Complemento += " - " + destinatario.getNM_Cidade();
		    			NM_Complemento += " / " + destinatario.getCD_Estado();
		    			NM_Complemento += ";";
		    		} else {
		    			throw new Exception("Problemas no destinatario da mercadoria.");
		    		}
		    		// fim dados destinatario
	    		}
	    		NR_Lote_Produto = NR_Lote_Produto.replaceAll("null", "");
	    		String NM_Observacao_Nota = "";
	    		if (NR_Lote_Produto != null && NR_Lote_Produto.length()>1){
	    			NM_Observacao_Nota = "CONFORME NF: "+NR_Lote_Produto;
	    		}else{
	    			NM_Observacao_Nota = "";
	    		}

	    		sql = "UPDATE Notas_Fiscais SET "
//					+ "DM_observacao = '" + Observacao + "- NFs: " +  NF_nr + NR_Lote_Produto + "' "
	    			+ "DM_observacao = '" + Observacao + "- " + ed.edModelo.getTX_Observacao() +  NM_Observacao_Nota + "' "
	    			+ ",NM_Complemento = '" + NM_Complemento + "' "
				    + "WHERE oid_Nota_Fiscal = '" + ed.getOid_nota_fiscal() + "'";
				executasql.executarUpdate(sql);
				return true;
    		} else {
    			return false;
    		}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "devolucaoSimbolica()");
		}
	}

	//*** ATUALIZA Valor ICMS, e Base de Calculo na Nota Fiscal
	public void atualizaValorICMS(String oid_Nota_Fiscal) throws Excecoes {

		ResultSet rs = null;
		double valorIPI = 0, valorICMS = 0, valorICMS_Subst = 0, valorBaseICMS = 0, valorBaseICMS_Subst = 0, valorProdutosNF = 0, valorItensNF = 0;
		try {
			if (!doValida(oid_Nota_Fiscal))
				throw new Excecoes(	"Nota Fiscal n�o informada para calculo de Impostos!");

			String query = "";
			boolean existeItens = doExiste("Itens_Notas_Fiscais",	"oid_Nota_Fiscal = '" + oid_Nota_Fiscal + "'");
			if (existeItens) {

				query = " SELECT Itens_Notas_Fiscais.PE_Aliquota_ICMS"
						+ "     ,Itens_Notas_Fiscais.oid_Produto"
						+ "		,sum(Itens_Notas_Fiscais.VL_Produto) as VL_Produto"
						+ "     ,sum(Itens_Notas_Fiscais.VL_Item) as VL_Item"
						+ "		,sum(Itens_Notas_Fiscais.vl_ipi) as VL_Ipi"
						+ "		,sum(Itens_Notas_Fiscais.VL_ICMS_Aprov) as VL_Icms"
						+ "		,sum(Itens_Notas_Fiscais.vl_base_calculo_icms) as VL_Base_Calculo_Icms"
						+ " FROM Itens_Notas_Fiscais, Notas_Fiscais"
						+ " WHERE Itens_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal "
						+ "   AND Itens_Notas_Fiscais.oid_Nota_Fiscal = '"
						+ oid_Nota_Fiscal
						+ "'"
						+ " GROUP BY Itens_Notas_Fiscais.PE_Aliquota_ICMS, Itens_Notas_Fiscais.oid_Produto";
			} else {
				query = " SELECT PE_Aliquota_ICMS"
						+ "		,VL_Nota_Fiscal as VL_Produto"
						+ "		,VL_IPI as VL_Ipi" + "		,VL_ICMS as VL_Icms"
						+ "		,VL_Base_Calculo_ICMS as VL_Base_Calculo_Icms"
						+ " FROM Notas_Fiscais " + " WHERE oid_Nota_Fiscal = '"
						+ oid_Nota_Fiscal + "'";
			}
			boolean isIsento = false;
			String cdSubstTrib = "";
			String dmTipoNF = getTableStringValue("DM_Tipo_Nota_Fiscal", "Notas_Fiscais", "oid_Nota_Fiscal = '" + oid_Nota_Fiscal+ "'");
			String tipoICMS = getTableStringValue("DM_ICMS_WMS", "Clientes, Notas_Fiscais",	"Clientes.oid_Pessoa = Notas_Fiscais.oid_Pessoa" + " AND Notas_Fiscais.oid_Nota_Fiscal = '" + oid_Nota_Fiscal + "'");
			//*** Substitui��o Tribut�ria **************
			if ("S".equals(tipoICMS) || "D".equals(dmTipoNF))
				isIsento = true;
			else if ("R".equals(tipoICMS))
				isIsento = false;
			else {
				isIsento = doValida(getTableStringValue("NM_Inscricao_Estadual", "Pessoas, Notas_Fiscais","Pessoas.oid_Pessoa = Notas_Fiscais.oid_Pessoa" + " AND Notas_Fiscais.oid_Nota_Fiscal = '" + oid_Nota_Fiscal + "'"));
			}
			rs = this.executasql.executarConsulta(query);
			while (rs.next()) {
				valorIPI += rs.getDouble("VL_Ipi");
				valorICMS += rs.getDouble("VL_Icms");
				valorBaseICMS += rs.getDouble("VL_Base_Calculo_Icms");
				//*** S� busca Referencia caso Exista Itens
				if (existeItens) {
					valorItensNF += rs.getDouble("VL_Item");
					cdSubstTrib = getValueDef(getTableStringValue("CD_Situacao_Tributaria",	"Situacoes_Tributarias, Produtos", 	"Situacoes_Tributarias.oid_Situacao_Tributaria = Produtos.oid_Situacao_Tributaria"	+ " AND Produtos.oid_Produto = '" + rs.getString("oid_Produto") + "'"),	"");
				}
				//*** Substitui��o Tribut�ria Cobrada de Pessoas Fisicas e
				// Produtos com S.T. [10,30,70]
				if (!isIsento	|| (cdSubstTrib.endsWith("10")	|| cdSubstTrib.endsWith("30") || cdSubstTrib.endsWith("70"))) {
					double baseSubst = Valor.round(Valor.calcPercentual(rs.getDouble("VL_Base_Calculo_Icms"), 40));
					valorICMS_Subst += Valor.round(Valor.calcPercentual(baseSubst, rs.getDouble("PE_Aliquota_ICMS")));
					valorBaseICMS_Subst += baseSubst;
				}
				valorProdutosNF += rs.getDouble("VL_Produto");
			}

			// .println("valorProdutosNF=>"+valorProdutosNF);
			// .println("valorItensNF=>"+valorItensNF);
			// .println("dmTipoNF=>"+dmTipoNF);
			// .println("existeItens=>"+existeItens);


			//*** Atualiza Valores na Nota Fiscal
			query = " UPDATE Notas_Fiscais SET dm_Stamp='U' ";

			//*** Verifica se Calcula Automaticamente os Valores
			if ("S".equals(dmTipoNF) || "D".equals(dmTipoNF) ) {
				if (existeItens && valorItensNF > 0)
					query += "  ,VL_Liquido_Nota_Fiscal = (VL_Total_Frete - VL_Desconto_Itens + VL_Total_Seguro + VL_Servico + VL_Total_Despesas + CAST(" + Valor.round(valorItensNF + valorICMS_Subst)	+ " AS numeric))";
				else if (valorProdutosNF > 0)
					query += "	,VL_Liquido_Nota_Fiscal = (VL_Total_Frete - VL_Desconto_Itens - VL_Descontos + VL_Total_Seguro + VL_Servico + VL_Total_Despesas + CAST(" + Valor.round(valorProdutosNF + valorICMS_Subst)+ " AS numeric))";
				else
					//query += "	,VL_Liquido_Nota_Fiscal = 0";
				    query += " ,VL_IPI = " + Valor.round(valorIPI)
						+ "   ,VL_ICMS = " + Valor.round(valorICMS)
						+ "   ,VL_ICMS_Substituicao = "	+ Valor.round(valorICMS_Subst)
						+ "   ,VL_Base_Calculo_ICMS = "	+ Valor.round(valorBaseICMS)
						+ "   ,VL_Base_Calculo_ICMS_Subst = " + Valor.round(valorBaseICMS_Subst);
			} else {
				query += "    ,VL_Base_Calculo_ICMS = " 	+ Valor.round(valorBaseICMS)
						+ "   ,VL_Base_Calculo_ICMS_Subst = "+ Valor.round(valorBaseICMS_Subst);
			}
			query += " WHERE oid_Nota_Fiscal = '" + oid_Nota_Fiscal + "'";
			// .println(query);

			executasql.executarUpdate(query);

		} catch (Exception e) {
			throw new Excecoes(e.getMessage(), e, this.getClass().getName(),
					"atualizaValorICMS");
		} finally {
			closeResultset(rs);
		}
	}

	//*** ATUALIZA Valor ICMS, e Base de Calculo na Nota Fiscal pelos
	// Conhecimentos
	public ConhecimentoED atualizaValorICMSByConhecimentos(
			String oid_Nota_Fiscal, String oid_Nota_Fiscal_Temporaria)
			throws Excecoes {

		ResultSet rs = null;
		double valorICMS = 0, valorICMS_Subst = 0, valorBaseICMS = 0, valorBaseICMS_Subst = 0, valorTotalFrete = 0, peAliquota = 0;
		String NR_Conhecimento;

		try {
			ConhecimentoED conhecimentoED = new ConhecimentoED();

			if (!doValida(oid_Nota_Fiscal))
				throw new Excecoes(
						"Nota Fiscal n�o informada para calculo de Impostos!");

			boolean existeConhecimentos = doExiste(	"Conhecimentos_Notas_Fiscais", "oid_Nota_Fiscal = '"+ oid_Nota_Fiscal + "'");
			if (!existeConhecimentos)
				return conhecimentoED;

			String query = "";
			query = " SELECT Conhecimentos.VL_Total_Frete"
					+ "       ,Conhecimentos.PE_Aliquota_ICMS"
					+ "       ,Conhecimentos.NR_Conhecimento"
					+ "       ,Conhecimentos.NM_Serie"
					+ "       ,Conhecimentos.DT_Emissao"
					+ "       ,Conhecimentos.OID_Conhecimento"
					+ "       ,sum(Conhecimentos.vl_icms) as VL_Icms"
					+ "       ,sum(Conhecimentos.vl_base_calculo_icms) as VL_Base_Calculo_Icms"
					+ " FROM Conhecimentos, Conhecimentos_Notas_Fiscais, Notas_Fiscais "
					+ " WHERE Conhecimentos.oid_Conhecimento = Conhecimentos_Notas_Fiscais.oid_Conhecimento"
					+ "   AND Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal "
					+ "   AND Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = '"
					+ oid_Nota_Fiscal
					+ "'"
					+ " GROUP BY Conhecimentos.VL_Total_Frete, Conhecimentos.PE_Aliquota_ICMS, "
					+ " Conhecimentos.NR_Conhecimento, Conhecimentos.NM_Serie, Conhecimentos.DT_Emissao, Conhecimentos.OID_Conhecimento";

			boolean isIsento = false;
			String tipoICMS = getTableStringValue("Clientes.DM_ICMS_WMS",
					"Clientes, Notas_Fiscais",
					"Clientes.oid_Pessoa = Notas_Fiscais.oid_Pessoa"
							+ " AND Notas_Fiscais.oid_Nota_Fiscal = '"
							+ oid_Nota_Fiscal + "'");
			//*** Substitui��o Tribut�ria **************
			if ("S".equals(tipoICMS))
				isIsento = true;
			else if ("R".equals(tipoICMS))
				isIsento = false;
			else {
				isIsento = doValida(getTableStringValue(
						"Pessoas.NM_Inscricao_Estadual",
						"Pessoas, Notas_Fiscais",
						"Pessoas.oid_Pessoa = Notas_Fiscais.oid_Pessoa"
								+ " AND Notas_Fiscais.oid_Nota_Fiscal = '"
								+ oid_Nota_Fiscal + "'"));
			}
			rs = this.executasql.executarConsulta(query);

			while (rs.next()) {
				peAliquota = rs.getDouble("PE_Aliquota_ICMS");
				valorTotalFrete += rs.getDouble("VL_Total_Frete");
				valorICMS += rs.getDouble("VL_Icms");
				valorBaseICMS += rs.getDouble("VL_Base_Calculo_Icms");
				//*** Substitui��o Tribut�ria Cobrada de Pessoas Fisicas e
				// Produtos com S.T. [10,30,70]
				if (!isIsento) {
					valorBaseICMS_Subst += Valor.calcPercentual(valorBaseICMS, 40);
					valorICMS_Subst += Valor.calcPercentual( valorBaseICMS_Subst, rs.getDouble("PE_Aliquota_ICMS"));
				}
				conhecimentoED.setOID_Conhecimento(rs.getString("OID_Conhecimento"));
				conhecimentoED.setNR_Conhecimento(rs.getLong("NR_Conhecimento"));
				conhecimentoED.setNM_Serie(rs.getString("NM_Serie"));
				conhecimentoED.setDT_Emissao(rs.getString("DT_Emissao"));
			}

			//*** Atualiza Valores na Nota Fiscal
			query = " UPDATE Notas_Fiscais SET ";
			query += "    VL_Liquido_Nota_Fiscal = "+ Valor.round(valorTotalFrete)
					+ "   ,PE_Aliquota_ICMS = "	+ Valor.round(peAliquota)
					+ "   ,VL_ICMS = "	+ Valor.round(valorICMS)
					+ "   ,VL_ICMS_Substituicao = "	+ Valor.round(valorICMS_Subst)
					+ "   ,VL_Base_Calculo_ICMS = "	+ Valor.round(valorBaseICMS)
					+ "   ,VL_Base_Calculo_ICMS_Subst = "+ Valor.round(valorBaseICMS_Subst)
					+ " WHERE oid_Nota_Fiscal = '" + oid_Nota_Fiscal_Temporaria	+ "'";

			executasql.executarUpdate(query);

			return conhecimentoED;

		} catch (Exception e) {
			throw new Excecoes(e.getMessage(), e, this.getClass().getName(),
					"atualizaValorICMSByConhecimentos");
		} finally {
			closeResultset(rs);
		}
	}

	//*** Gera COMPROMISSOS - [Contas a Pagar]
	public void geraCompromisso(Nota_Fiscal_EletronicaED ed) throws Excecoes {

		String sql = null;
		ResultSet res = null;
		Parcela_CompromissoED edParcela = new Parcela_CompromissoED();
		CompromissoED edCompromisso = new CompromissoED();
		try {

			sql = " SELECT  pr.oid_parcelamento, "
					+ "		    pr.nr_parcelamento, " + "			pr.vl_parcela, "
					+ "			pr.dt_pagamento , " + "			pr.DM_TIPO_PAGAMENTO,"
					+ "			nf.oid_Natureza_Operacao," + "			nf.oid_Conta "
					+ " FROM parcelamentos_financeiros pr, notas_fiscais nf "
					+ " WHERE pr.OID_Nota_Fiscal = nf.OID_Nota_Fiscal "
                    + " AND nf.DM_Tipo_Nota_Fiscal in ('E','F')" // E = Entrada e F = Nota Diversa Entrada
					+ " AND nf.oid_nota_fiscal_original in ('null',null,'') "
					+ " AND pr.oid_nota_fiscal = '" + ed.getOid_nota_fiscal() + "'";

			res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				edCompromisso.setDt_Vencimento(dataFormatada.getDT_FormataData(res.getString("dt_pagamento")));

				edCompromisso.setNr_Parcela(new Integer(res.getInt("nr_parcelamento")));
				edCompromisso.setVl_Compromisso(new Double(res.getDouble("vl_parcela")));
				edCompromisso.setVl_Saldo(new Double(res.getDouble("vl_parcela")));

				edCompromisso.setOid_Pessoa(ed.getOid_pessoa_fornecedor());
				edCompromisso.setDt_Entrada(ed.getDt_entrada());
				edCompromisso.setDt_Emissao(ed.getDt_emissao());
				edCompromisso.setNr_Documento(String.valueOf(ed.getNr_nota_fiscal()));
				edCompromisso.setOid_Unidade(new Long(ed.getOID_Unidade_Fiscal()));
				edCompromisso.setDM_Tipo_Pagamento(res.getString("DM_TIPO_PAGAMENTO"));
				edCompromisso.setOid_Centro_Custo(ed.getOid_Centro_Custo());

				edCompromisso.setOid_Conta(new Integer(res.getInt("oid_Conta")));
				edCompromisso.setOid_Conta_Credito(edCompromisso.getOid_Conta());
				edCompromisso.setOid_Natureza_Operacao(new Integer(res.getInt("oid_Natureza_Operacao")));
				edCompromisso.setOid_Tipo_Documento(new Integer(Parametro_FixoED.getInstancia().getOid_Tipo_Documento_Nota_Fiscal_Entrada()));

				//*** Inclui Compromisso
				edCompromisso = new CompromissoBD(this.executasql).inclui(edCompromisso);
				edCompromisso.setOid_Compromisso(edCompromisso.getOid_Compromisso());

				// Gera OID
				long valOid = System.currentTimeMillis();
				edParcela.setOID_Compromisso(edCompromisso.getOid_Compromisso());
				edParcela.setOID_Parcelamento(res.getString("oid_parcelamento"));

				sql = " INSERT INTO Parcelas_Compromissos ("
						+ "		 OID_Parcela_Compromisso" + "		,OID_Compromisso"
						+ "		,OID_Parcelamento) " + " VALUES ";
				sql += "(" + valOid + "," + edParcela.getOID_Compromisso()
						+ "," + edParcela.getOID_Parcelamento() + ")";

				executasql.executarUpdate(sql);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "geraCompromisso()");
		} finally {
			closeResultset(res);
		}
	}

	//*** Gera Duplicatas - [Contas a Receber]
	public void geraDuplicatas(Nota_Fiscal_EletronicaED ed) throws Excecoes {

		String sql = null;
		ResultSet res = null;
		String nr_Parcela = null;

		try {
			//*** Se j� Existe Duplicatas n�o incluir
			if (doExiste("Origens_Duplicatas", "oid_Nota_Fiscal = '"
					+ ed.getOid_nota_fiscal() + "'"))
				return;

			sql = " SELECT  pr.oid_parcelamento"
					+ "		   ,pr.nr_parcelamento"
					+ "		   ,pr.vl_parcela"
					+ "		   ,pr.dt_pagamento "
					+ "        ,nf.vl_comissao / nf.vl_nota_fiscal * pr.vl_parcela as vl_comissao_parcela "
					+ // calcula comissao para cada parcela
					" FROM parcelamentos_financeiros pr, notas_fiscais nf "
					+ " WHERE pr.OID_Nota_Fiscal = nf.OID_Nota_Fiscal "
                    + " AND nf.DM_Tipo_Nota_Fiscal in ('S','R')" // S = Saida e R = Nota Diversa Saida
					+ " AND pr.oid_nota_fiscal = '" + ed.getOid_nota_fiscal()
					+ "'";

			res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				DuplicataED edDuplicata = new DuplicataED();

				edDuplicata.setDt_Vencimento(dataFormatada
						.getDT_FormataData(res.getString("dt_pagamento")));
				nr_Parcela = res.getString("nr_parcelamento");
				if (nr_Parcela != null)
					edDuplicata.setNr_Parcela(new Integer(nr_Parcela));

				edDuplicata.setVl_Duplicata(new Double(res.getDouble("vl_parcela")));
				edDuplicata.setVl_Saldo(new Double(res.getDouble("vl_parcela")));
				edDuplicata.setVl_Comissao(res.getDouble("vl_comissao_parcela"));

				edDuplicata.setOid_Pessoa(ed.getOid_pessoa_destinatario());
				//*** Busca Cliente Pagador caso Possua
				if ("S".equals(ed.getDm_tipo_nota_fiscal()) || "R".equals(ed.getDm_tipo_nota_fiscal()))
					edDuplicata.setOid_Pessoa(getValueDef(getTableStringValue(
							"oid_Cliente_Pagador", "Clientes", "oid_Cliente='"
									+ ed.getOid_pessoa_destinatario() + "'"), ed
							.getOid_pessoa_destinatario()));
				edDuplicata.setOid_Vendedor(ed.getOid_Vendedor());

				edDuplicata.setOid_Unidade(new Long(ed.getOID_Unidade()));

				//*** Busca CARTEIRA no CLIENTE , caso contrario busca da
				// Unidade
				edDuplicata
						.setOid_Carteira(new Integer(getValueDef(
								getTableIntValue("oid_Carteira", "Clientes",
										"oid_Cliente = '" + ed.getOid_pessoa_destinatario()
												+ "'"), getTableIntValue(
										"oid_Carteira", "Unidades",
										"oid_Unidade = '" + ed.getOID_Unidade()
												+ "'"))));

				//*** Busca na CARTEIRA os valores de JUROS e MULTA, e os
				// calcula para a duplicata
				sql = " SELECT pe_juros, pe_multa " + " FROM carteiras "
						+ " WHERE oid_carteira = "
						+ edDuplicata.getOid_Carteira();
				ResultSet rsLocal = this.executasql.executarConsulta(sql);
				while (rsLocal.next()) {
					edDuplicata.setVL_Juro_Mora_Dia(new Double((rsLocal
							.getDouble("pe_juros")
							/ 30 * res.getDouble("vl_parcela") / 100)));
					edDuplicata.setVL_Multa(new Double((rsLocal
							.getDouble("pe_multa")
							/ 30 * res.getDouble("vl_parcela") / 100)));
				}

				edDuplicata.setDt_Emissao(ed.getDt_emissao());
				edDuplicata.setNr_Documento(String.valueOf(ed.getNr_nota_fiscal()));
				edDuplicata.setOid_Unidade(new Long(ed.getOID_Unidade_Fiscal()));
				edDuplicata.setOid_Tipo_Documento(new Integer(Parametro_FixoED
						.getInstancia()
						.getOid_Tipo_Documento_Nota_Fiscal_Saida()));
				if ("2".equals(ed.getDm_forma_pagamento())
						|| "5".equals(ed.getDm_forma_pagamento()))
					edDuplicata.setDM_Tipo_Cobranca("V");
				else
					edDuplicata.setDM_Tipo_Cobranca("F");

				/** INCLUI DUPLICATA * */
				edDuplicata = new DuplicataBD(executasql).inclui(edDuplicata);

				//*** Gera Origens Duplicatas
				sql = " INSERT INTO Origens_Duplicatas ("
						+ "		 oid_Origem_Duplicata"
						+ "		,oid_Duplicata"
						+ "		,oid_Conhecimento"
						+ "		,oid_Parcelamento"
						+ "		,oid_Nota_Fiscal"
						+ "		,DT_Origem_Duplicata"
						+ "		,HR_Origem_Duplicata"
						+ "		,DM_Situacao) "
						+ "	VALUES ("
						+ "'"
						+ (edDuplicata.getOid_Duplicata()
						+ ed.getOid_nota_fiscal()) + "'" + ","
						+ edDuplicata.getOid_Duplicata() + ",'"
						+ ed.getOid_Conhecimento() + "'" + ","
						+ res.getInt("oid_Parcelamento") + ",'"
						+ ed.getOid_nota_fiscal() + "'" + ",'"
						+ Data.getDataDMY() + "'" + ",'" + Data.getHoraHM()
						+ "'" + ",'A')";

				executasql.executarUpdate(sql);
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "geraDuplicatas()");
		} finally {
			closeResultset(res);
		}
	}

	//*** Busca Oid, Numero, S�rie da Nota Fiscal
	public void setNrSerieNotaFromAIDOF(Nota_Fiscal_EletronicaED ed,String nmSerie) throws Exception {

    	// .println("setNrSerieNotaFromAIDOF");

		ResultSet rs = null;
		long nrFinal = 0, oid_AIDOF = 0;
		try {
			if (!doValida(nmSerie))
				throw new Excecoes("S�rie n�o informada!");

			String sql = "SELECT " +
						 "NR_Proximo, " +
						 "NR_Final, " +
						 "OID_Aidof, " +
						 "NM_Serie " +
						 "FROM AIDOF " +
						 "WHERE " +
						 "CD_Aidof = '" + nmSerie + "'";

			//*** Seta NR e S�rie da Nota Fiscal
			rs = this.executasql.executarConsulta(sql);
			while (rs.next()) {
				ed.setNm_serie(rs.getString("NM_Serie"));
				ed.setNr_nota_fiscal(rs.getLong("NR_Proximo"));
				nrFinal = rs.getLong("NR_Final");
				oid_AIDOF = rs.getLong("OID_Aidof");
			}
			if (oid_AIDOF < 1)
				throw new Excecoes("AIDOF n�o encontrado : "
						+ nmSerie);

			//*** Verifica por seguran�a se NR ja existe
			while (doExiste("Notas_Fiscais", "NR_Nota_Fiscal = "
					+ ed.getNr_nota_fiscal() +
					" and NM_Serie = '" + ed.getNm_serie() + "'" +
					" and oid_Pessoa = '" + ed.getOid_pessoa() + "'"
					))
				ed.setNr_nota_fiscal(ed.getNr_nota_fiscal() + 1);

			//*** Se Fora do "Range" da NF informa erro
			if ((ed.getNr_nota_fiscal() + 1) > nrFinal)
				throw new Excecoes(
						"N�mero sequencial da Nota Fiscal esgotado! Atualize no AIDOF!");

	    	// .println("setNrSerieNotaFromAIDOF ed.getNr_nota_fiscal="+ed.getNr_nota_fiscal());

			sql = "UPDATE AIDOF SET " +
				  "NR_Proximo= " + (ed.getNr_nota_fiscal() + 1) + " " +
			      "WHERE OID_Aidof = " + oid_AIDOF;
			executasql.executarUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "getNrSerieNotaFromAIDOF()");
		} finally {
			closeResultset(rs);
		}
	}

//	*** Busca Oid, Numero, S�rie da Nota Fiscal
	public void setNrSerieNotaFromAIDOF(Nota_Fiscal_EletronicaED ed, int oid_AIDOF) throws Exception {

    	// .println("setNrSerieNotaFromAIDOF");

		ResultSet rs = null;
		long nrFinal = 0;
		try {
			if (!doValida(String.valueOf(oid_AIDOF)))
				throw new Excecoes("AIDOF n�o informada!");

			String sql = "SELECT " +
						 "NR_Proximo, " +
						 "NR_Final, " +
						 "OID_Aidof, " +
						 "NM_Serie " +
						 "FROM AIDOF " +
						 "WHERE " +
						 "oid_Aidof = '" + oid_AIDOF + "'";
//.println(sql);
			//*** Seta NR e S�rie da Nota Fiscal
			rs = this.executasql.executarConsulta(sql);
			while (rs.next()) {
				ed.setNm_serie(rs.getString("NM_Serie"));
				ed.setNr_nota_fiscal(rs.getLong("NR_Proximo"));
				nrFinal = rs.getLong("NR_Final");
				oid_AIDOF = rs.getInt("OID_Aidof");
			}
			if (oid_AIDOF < 1)
				throw new Excecoes("AIDOF n�o encontrado : "
						+ oid_AIDOF);

			//*** Verifica por seguran�a se NR ja existe
			while (doExiste("Notas_Fiscais", "NR_Nota_Fiscal = "
					+ ed.getNr_nota_fiscal() + " and NM_Serie = '"
					+ ed.getNm_serie() + "'"))
				ed.setNr_nota_fiscal(ed.getNr_nota_fiscal() + 1);

			//*** Se Fora do "Range" da NF informa erro
			if ((ed.getNr_nota_fiscal() + 1) > nrFinal)
				throw new Excecoes(
						"N�mero sequencial da Nota Fiscal esgotado! Atualize no AIDOF!");

	    	// .println("setNrSerieNotaFromAIDOF ed.getNr_nota_fiscal="+ed.getNr_nota_fiscal());

			sql = "UPDATE AIDOF SET " +
				  "NR_Proximo= " + (ed.getNr_nota_fiscal() + 1) + " " +
			      "WHERE OID_Aidof = " + oid_AIDOF;
			executasql.executarUpdate(sql);

		} catch (Exception e) {
			throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "getNrSerieNotaFromAIDOF()");
		} finally {
			closeResultset(rs);
		}
	}


	public ArrayList lista(Nota_Fiscal_EletronicaED edV) throws Excecoes {

		String sql = null;
		ResultSet res = null;
		ArrayList lista = new ArrayList();
		try {
			sql = " SELECT * "
					+ " FROM Notas_Fiscais "
					+ " WHERE Notas_Fiscais.oid_Modelo_Nota_Fiscal > 0 ";

			if (doValida(edV.getOid_nota_fiscal()))
				sql += " AND oid_Nota_Fiscal="
						+ getSQLString(edV.getOid_nota_fiscal());
			else {
				//*** Busca Pelo RANGE
				if (edV.getNr_nota_fiscal() > 0
						&& edV.getNr_nota_fiscal_final() > 0)
					sql += " AND NR_Nota_Fiscal between "
							+ edV.getNr_nota_fiscal() + " AND "
							+ edV.getNr_nota_fiscal_final();
				else if (edV.getNr_nota_fiscal() > 0)
					sql += " AND NR_Nota_Fiscal=" + edV.getNr_nota_fiscal();
				else if (edV.getNr_nota_fiscal_final() > 0)
					sql += " AND NR_Nota_Fiscal="
							+ edV.getNr_nota_fiscal_final();

				if (doValida(edV.getNm_serie()))
					sql += " AND NM_Serie ='" + edV.getNm_serie() + "' ";
				if (edV.getOid_modelo_nota_fiscal() > 0)
					sql += " AND Oid_Modelo_Nota_Fiscal ="
							+ edV.getOid_modelo_nota_fiscal();
				if (doValida(edV.getOid_pessoa()))
					sql += " AND oid_Pessoa ='" + edV.getOid_pessoa() + "' ";
				if (doValida(edV.getOid_pessoa_destinatario()))
					sql += " AND oid_Pessoa_destinatario ='"
							+ edV.getOid_pessoa_destinatario() + "' ";
				if (doValida(edV.getOid_Pessoa_Consignatario()))
					sql += " AND oid_Pessoa_Consignatario ='"
							+ edV.getOid_Pessoa_Consignatario() + "' ";
				if (doValida(edV.getOid_Pessoa_Redespacho()))
					sql += " AND oid_Pessoa_Redespacho ='"
							+ edV.getOid_Pessoa_Redespacho() + "' ";
				if (doValida(edV.getOid_pessoa_fornecedor()))
					sql += " AND oid_Pessoa_fornecedor ='"
							+ edV.getOid_pessoa_fornecedor() + "' ";
				if (doValida(edV.getOid_Vendedor()))
					sql += " AND oid_Pessoa_Fornecedor = '"
							+ edV.getOid_Vendedor() + "'";
				if (doValida(edV.getOid_Veiculo()))
					sql += " AND oid_Veiculo = '" + edV.getOid_Veiculo() + "'";
				//*** ENTREGADOR x CARGA
				if (edV.getOid_Carga_Entrega() > 0
						&& edV.getOid_Entregador() > 0)
					sql += " AND (oid_Carga_Entrega = "
							+ edV.getOid_Carga_Entrega()
							+ " AND oid_Entregador = "
							+ edV.getOid_Entregador() + ")";
				else if (edV.getOid_Entregador() > 0)
					sql += " AND oid_Entregador = " + edV.getOid_Entregador();
				else if (edV.getOid_Carga_Entrega() > 0)
					sql += " AND oid_Carga_Entrega = "
							+ edV.getOid_Carga_Entrega();

				//*** Data da Entrega
				if (doValida(edV.getDT_Entrega())
						&& doValida(edV.getDT_Entrega_Final())) {
					sql += " AND Notas_Fiscais.DT_Entrega BETWEEN '"
							+ edV.getDT_Entrega() + "' AND '"
							+ edV.getDT_Entrega_Final() + "'";
				} else if (doValida(edV.getDT_Entrega())
						|| doValida(edV.getDT_Entrega_Final())) {
					sql += " AND Notas_Fiscais.DT_Entrega = '"
							+ (doValida(edV.getDT_Entrega()) ? edV
									.getDT_Entrega() : edV
									.getDT_Entrega_Final()) + "'";
				}
				//*** Data da Entrada
				if (doValida(edV.getDt_entrada())
						&& doValida(edV.getDt_entrada_final())) {
					sql += " AND Notas_Fiscais.DT_Entrada BETWEEN '"
							+ edV.getDt_entrada() + "' AND '"
							+ edV.getDt_entrada_final() + "'";
				} else if (doValida(edV.getDt_entrada())
						|| doValida(edV.getDt_entrada_final())) {
					sql += " AND Notas_Fiscais.DT_Entrada = '"
							+ (doValida(edV.getDt_entrada()) ? edV
									.getDt_entrada() : edV
									.getDt_entrada_final()) + "'";
				}
				if (doValida(edV.getDt_emissao()))
					sql += " AND Notas_Fiscais.DT_Emissao = '"
							+ edV.getDt_emissao() + "'";
				if (doValida(edV.getDT_Inicial()))
					sql += " AND Notas_Fiscais.DT_Emissao >= '"
							+ edV.getDT_Inicial() + "'";
				if (doValida(edV.getDT_Final()))
					sql += " AND Notas_Fiscais.DT_Emissao <= '"
							+ edV.getDT_Final() + "'";

				if (doValida(edV.getDM_Situacao())) {
					if (!edV.getDM_Situacao().equals("T"))
						if (edV.getDM_Situacao().equals("1"))
							sql += " AND Notas_Fiscais.DM_Finalizado in ('F','C','D') ";
						else
							sql += " AND Notas_Fiscais.DM_Finalizado ='" + edV.getDM_Situacao() + "' ";
				}
				if (doValida(edV.getDM_Estoque()))
					sql += " AND Notas_Fiscais.DM_Estoque ='"
							+ edV.getDM_Estoque() + "' ";
				if (doValida(edV.getDM_Impresso())
						&& !edV.getDM_Impresso().equals("T"))
					sql += " AND Notas_Fiscais.DM_Impresso ='"
							+ edV.getDM_Impresso() + "' ";
				if (doValida(edV.getDM_Pendencia())
						&& !edV.getDM_Pendencia().equals("T"))
					sql += " AND Notas_Fiscais.DM_Pendencia ='"
							+ edV.getDM_Pendencia() + "' ";
				if (edV.getOID_Unidade_Contabil() > 0)
					sql += " AND Notas_Fiscais.oid_unidade_contabil = "
							+ edV.getOID_Unidade_Contabil();
				if (doValida(edV.getDm_tipo_nota_fiscal()))
					sql += " AND Notas_Fiscais.DM_Tipo_Nota_Fiscal = '"
							+ edV.getDm_tipo_nota_fiscal() + "'";
				if (doValida(edV.getDm_Devolvido()))
					sql += " AND Notas_Fiscais.DM_Devolvido = '"
							+ edV.getDm_Devolvido() + "'";


				sql += " ORDER BY Notas_Fiscais.NR_Nota_Fiscal";
				//*** Pagina��o
				sql += edV.getSQLPaginacao();
			}
			res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();

				ed.setOid_nota_fiscal(res.getString("oid_nota_fiscal"));
				ed.setNr_nota_fiscal(res.getLong("nr_nota_fiscal"));
				ed.setDt_emissao(res.getString("dt_emissao"));
				dataFormatada.setDT_FormataData(ed.getDt_emissao());
				ed.setDt_emissao(dataFormatada.getDT_FormataData());
				ed.setDM_Frete(res.getString("DM_Frete"));
				ed.setDT_Entrega(dataFormatada.getDT_FormataData(res.getString("DT_Entrega")));
				ed.setDt_entrada(res.getString("dt_entrada"));
				dataFormatada.setDT_FormataData(ed.getDt_entrada());
				ed.setDt_entrada(dataFormatada.getDT_FormataData());
				ed.setNr_Itens(res.getDouble("nr_Itens"));
				ed.setNm_serie(res.getString("nm_serie"));
				ed.setDM_Pendencia(res.getString("DM_Pendencia"));
				ed.setNM_Pendencia(doValida(ed.getDM_Pendencia()) && (ed.getDM_Pendencia().equals("P") || ed.getDM_Pendencia().equals("S")) ? "SIM" : "N�O");

				ed.setHr_entrada(res.getString("hr_entrada"));
				ed.setOid_pessoa(res.getString("oid_pessoa"));
				ed.setOid_pessoa_destinatario(res.getString("oid_pessoa_destinatario"));
				ed.setOid_Pessoa_Consignatario(res.getString("oid_Pessoa_Consignatario"));
				ed.setOid_Pessoa_Redespacho(res.getString("oid_Pessoa_Redespacho"));
				ed.setOid_pessoa_fornecedor(res.getString("oid_pessoa_fornecedor"));
				ed.setOid_pessoa_transportador(res.getString("oid_pessoa_transportador"));
				ed.setOid_Vendedor(ed.getOid_pessoa_fornecedor());
				ed.setOid_natureza_operacao(res.getLong("oid_natureza_operacao"));
				ed.setOid_Natureza_Operacao_Servico(res.getInt("oid_Natureza_Operacao_Servico"));
				ed.setOid_modelo_nota_fiscal(res.getInt("oid_modelo_nota_fiscal"));
				ed.setOid_Condicao_Pagamento(res.getInt("oid_condicao_pagamento"));
				ed.setOid_Tabela_Venda(res.getInt("oid_Tabela_Venda"));
				ed.setOid_Conta(res.getInt("oid_Conta"));
				ed.setVl_total_frete(res.getDouble("vl_total_frete"));
				ed.setVl_icms(res.getDouble("vl_icms"));
				ed.setVL_Base_Calculo_ICMS(res.getDouble("VL_Base_Calculo_ICMS"));
				ed.setPE_Aliquota_ICMS(res.getDouble("PE_Aliquota_ICMS"));
				ed.setVl_inss(res.getDouble("vl_inss"));
				ed.setVl_irrf(res.getDouble("vl_irrf"));
				ed.setVl_ipi(res.getDouble("vl_ipi"));
				ed.setVL_Servico(res.getDouble("vl_servico"));
				ed.setVl_isqn(res.getDouble("vl_isqn"));
				ed.setVl_total_seguro(res.getDouble("vl_total_seguro"));
				ed.setVl_total_despesas(res.getDouble("vl_total_despesas"));
				ed.setVl_nota_fiscal(res.getDouble("vl_nota_fiscal"));
				ed.setVl_liquido_nota_fiscal(res.getDouble("vl_liquido_nota_fiscal"));
				ed.setVL_Pis(res.getDouble("VL_Pis"));
				ed.setVL_Base_Calculo_ICMS_Subst(res.getDouble("VL_Base_Calculo_ICMS_Subst"));
				ed.setVL_ICMS_Subst(res.getDouble("VL_ICMS_Substituicao"));
				ed.setVL_Csll(res.getDouble("VL_Csll"));
				ed.setVL_Cofins(res.getDouble("VL_Cofins"));
				ed.setNr_volumes(res.getDouble("nr_Volumes"));
				ed.setNr_volumes_nota(res.getLong("nr_Volumes"));
				ed.setNR_Peso(res.getDouble("NR_Peso"));
				ed.setNR_Peso_Cubado(res.getDouble("NR_Peso_Cubado"));

				ed.setDm_tipo_nota_fiscal(res.getString("dm_tipo_nota_fiscal"));
				ed.setDt_stamp(res.getString("dt_stamp"));
				ed.setDm_observacao(res.getString("dm_observacao"));
				ed.setNm_complemento(res.getString("Nm_complemento"));
				ed.setDm_forma_pagamento(res.getString("dm_forma_pagamento"));
				ed.setNr_parcelas(res.getLong("nr_parcelas"));
				ed.setVl_descontos(res.getDouble("vl_descontos"));
				ed.setVL_Desconto_Itens(res.getDouble("VL_Desconto_Itens"));
				ed.setVL_Adicional(res.getDouble("VL_Adicional"));
				ed.setVL_Diferenca_Aliq_ICMS(res.getDouble("VL_Diferenca_Aliq_ICMS"));
				ed.setOID_Unidade_Contabil(res.getLong("oid_unidade_contabil"));
				ed.setOID_Unidade_Fiscal(res.getLong("oid_unidade_fiscal"));
				ed.setOID_Unidade(res.getLong("oid_unidade"));
				ed.setOid_Centro_Custo(new Integer(res.getInt("Oid_Centro_Custo")));
				ed.setDm_finalizado(res.getString("dm_finalizado"));
				ed.setDM_Situacao(res.getString("dm_finalizado"));
				ed.setDM_Impresso(res.getString("DM_Impresso"));
				ed.setDM_Tipo_Devolucao(res.getString("DM_Tipo_Devolucao"));
				ed.setDM_Estoque(res.getString("DM_Estoque"));
				ed.setVl_Comissao(res.getDouble("vl_comissao"));
				ed.setOid_Entregador(res.getInt("oid_Entregador"));
				ed.setOid_Veiculo(res.getString("oid_Veiculo"));
				ed.setOid_Carga_Entrega(res.getInt("oid_Carga_Entrega"));
				ed.setDm_Devolvido(res.getString("dm_Devolvido"));

				ed.setNfe_chave_acesso(res.getString("nfe_chave_acesso"));
				ed.setNfe_protocolo(res.getString("nfe_protocolo"));
				ed.setNfe_digestvalue(res.getString("nfe_digestvalue"));
				ed.setNfe_cstat(res.getString("nfe_cstat"));
				ed.setNfe_motivo(res.getString("nfe_motivo"));
				ed.setNfe_dt_hr_recebimento(res.getString("nfe_dt_hr_recebimento"));

				//*** Carrega Dados
				if (ed.getOid_modelo_nota_fiscal() > 0)
					ed.edModelo = new Modelo_Nota_FiscalBD(executasql).getByRecord(new Modelo_Nota_FiscalED((int) ed.getOid_modelo_nota_fiscal()));

				lista.add(ed);
			}
			return lista;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "lista()");
		} finally {
			closeResultset(res);
		}
	}

	public ArrayList lista_em_Viagem(Nota_Fiscal_EletronicaED edV) throws Excecoes {

		String sql = null;
		ResultSet res = null;
		ArrayList lista = new ArrayList();
		try {
			sql = " SELECT notas_fiscais.*, conhecimentos.nr_conhecimento, conhecimentos.oid_conhecimento, manifestos.nr_manifesto, manifestos.oid_manifesto "
					+ " FROM Notas_Fiscais, conhecimentos_notas_fiscais, conhecimentos, viagens, manifestos "
					+ " WHERE notas_fiscais.oid_nota_fiscal = conhecimentos_notas_fiscais.oid_nota_fiscal "
					+ " AND conhecimentos.oid_conhecimento = viagens.oid_conhecimento "
					+ " AND viagens.oid_manifesto = manifestos.oid_manifesto "
					+ " AND Notas_Fiscais.oid_Modelo_Nota_Fiscal > 0 ";

			sql += " AND manifestos.oid_manifesto=" + getSQLString(edV.getOid_Manifesto());

			sql += " ORDER BY Notas_Fiscais.NR_Nota_Fiscal";

			res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();

				ed.setOid_Manifesto(res.getString("oid_manifesto"));
				ed.setOid_Conhecimento(res.getString("oid_conhecimento"));
				ed.setNr_Manifesto(res.getString("nr_manifesto"));
				ed.setNr_Conhecimento(res.getString("nr_conhecimento"));

				ed.setOid_nota_fiscal(res.getString("oid_nota_fiscal"));
				ed.setNr_nota_fiscal(res.getLong("nr_nota_fiscal"));
				ed.setDt_emissao(res.getString("dt_emissao"));
				dataFormatada.setDT_FormataData(ed.getDt_emissao());
				ed.setDt_emissao(dataFormatada.getDT_FormataData());
				ed.setDT_Entrega(dataFormatada.getDT_FormataData(res.getString("DT_Entrega")));
				ed.setDt_entrada(res.getString("dt_entrada"));
				dataFormatada.setDT_FormataData(ed.getDt_entrada());
				ed.setDt_entrada(dataFormatada.getDT_FormataData());
				ed.setNr_Itens(res.getDouble("nr_Itens"));
				ed.setNm_serie(res.getString("nm_serie"));
				ed.setDM_Pendencia(res.getString("DM_Pendencia"));
				ed.setNM_Pendencia(doValida(ed.getDM_Pendencia()) && (ed.getDM_Pendencia().equals("P") || ed.getDM_Pendencia().equals("S")) ? "SIM" : "N�O");

				ed.setHr_entrada(res.getString("hr_entrada"));
				ed.setOid_pessoa(res.getString("oid_pessoa"));
				ed.setOid_pessoa_destinatario(res.getString("oid_pessoa_destinatario"));
				ed.setOid_Pessoa_Consignatario(res.getString("oid_Pessoa_Consignatario"));
				ed.setOid_Pessoa_Redespacho(res.getString("oid_Pessoa_Redespacho"));
				ed.setOid_pessoa_fornecedor(res.getString("oid_pessoa_fornecedor"));
				ed.setOid_Vendedor(ed.getOid_pessoa_fornecedor());
				ed.setOid_natureza_operacao(res.getLong("oid_natureza_operacao"));
				ed.setOid_Natureza_Operacao_Servico(res.getInt("oid_Natureza_Operacao_Servico"));
				ed.setOid_modelo_nota_fiscal(res.getInt("oid_modelo_nota_fiscal"));
				ed.setOid_Condicao_Pagamento(res.getInt("oid_condicao_pagamento"));
				ed.setOid_Tabela_Venda(res.getInt("oid_Tabela_Venda"));
				ed.setOid_Conta(res.getInt("oid_Conta"));
				ed.setVl_total_frete(res.getDouble("vl_total_frete"));
				ed.setVl_icms(res.getDouble("vl_icms"));
				ed.setVL_Base_Calculo_ICMS(res.getDouble("VL_Base_Calculo_ICMS"));
				ed.setPE_Aliquota_ICMS(res.getDouble("PE_Aliquota_ICMS"));
				ed.setVl_inss(res.getDouble("vl_inss"));
				ed.setVl_irrf(res.getDouble("vl_irrf"));
				ed.setVl_ipi(res.getDouble("vl_ipi"));
				ed.setVL_Servico(res.getDouble("vl_servico"));
				ed.setVl_isqn(res.getDouble("vl_isqn"));
				ed.setVl_total_seguro(res.getDouble("vl_total_seguro"));
				ed.setVl_total_despesas(res.getDouble("vl_total_despesas"));
				ed.setVl_nota_fiscal(res.getDouble("vl_nota_fiscal"));
				ed.setVl_liquido_nota_fiscal(res.getDouble("vl_liquido_nota_fiscal"));
				ed.setVL_Pis(res.getDouble("VL_Pis"));
				ed.setVL_Base_Calculo_ICMS_Subst(res.getDouble("VL_Base_Calculo_ICMS_Subst"));
				ed.setVL_ICMS_Subst(res.getDouble("VL_ICMS_Substituicao"));
				ed.setVL_Csll(res.getDouble("VL_Csll"));
				ed.setVL_Cofins(res.getDouble("VL_Cofins"));
				ed.setNr_volumes(res.getDouble("nr_Volumes"));
				ed.setNr_volumes_nota(res.getLong("nr_Volumes"));
				ed.setNR_Peso(res.getDouble("NR_Peso"));
				ed.setNR_Peso_Cubado(res.getDouble("NR_Peso_Cubado"));

				ed.setDm_tipo_nota_fiscal(res.getString("dm_tipo_nota_fiscal"));
				ed.setDt_stamp(res.getString("dt_stamp"));
				ed.setDm_observacao(res.getString("dm_observacao"));
				ed.setNm_complemento(res.getString("Nm_complemento"));
				ed.setDm_forma_pagamento(res.getString("dm_forma_pagamento"));
				ed.setNr_parcelas(res.getLong("nr_parcelas"));
				ed.setVl_descontos(res.getDouble("vl_descontos"));
				ed.setVL_Desconto_Itens(res.getDouble("VL_Desconto_Itens"));
				ed.setVL_Adicional(res.getDouble("VL_Adicional"));
				ed.setVL_Diferenca_Aliq_ICMS(res.getDouble("VL_Diferenca_Aliq_ICMS"));
				ed.setOID_Unidade_Contabil(res.getLong("oid_unidade_contabil"));
				ed.setOID_Unidade_Fiscal(res.getLong("oid_unidade_fiscal"));
				ed.setOID_Unidade(res.getLong("oid_unidade"));
				ed.setOid_Centro_Custo(new Integer(res.getInt("Oid_Centro_Custo")));
				ed.setDm_finalizado(res.getString("dm_finalizado"));
				ed.setDM_Situacao(res.getString("dm_finalizado"));
				ed.setDM_Impresso(res.getString("DM_Impresso"));
				ed.setDM_Tipo_Devolucao(res.getString("DM_Tipo_Devolucao"));
				ed.setDM_Estoque(res.getString("DM_Estoque"));
				ed.setVl_Comissao(res.getDouble("vl_comissao"));
				ed.setOid_Entregador(res.getInt("oid_Entregador"));
				ed.setOid_Veiculo(res.getString("oid_Veiculo"));
				ed.setOid_Carga_Entrega(res.getInt("oid_Carga_Entrega"));
				ed.setDm_Devolvido(res.getString("dm_Devolvido"));

				//Ocorrencia
				Ocorrencia_Nota_FiscalED ocorrencia = new Ocorrencia_Nota_FiscalED();
				ocorrencia.setOID_Nota_Fiscal(res.getString("oid_nota_fiscal"));
				Iterator iterador = new Ocorrencia_Nota_FiscalBD(this.executasql).lista(ocorrencia).iterator();
				while(iterador.hasNext()){
					ocorrencia = (Ocorrencia_Nota_FiscalED)iterador.next();
					ed.setNM_Pendencia(ocorrencia.getTX_Observacao());
				}


				//*** Carrega Dados
				if (ed.getOid_modelo_nota_fiscal() > 0)
					ed.edModelo = new Modelo_Nota_FiscalBD(executasql).getByRecord(new Modelo_Nota_FiscalED((int) ed.getOid_modelo_nota_fiscal()));

				lista.add(ed);
			}
			return lista;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "lista_em_Viagem()");
		} finally {
			closeResultset(res);
		}
	}

	public Nota_Fiscal_EletronicaED getByRecord(Nota_Fiscal_EletronicaED edV)
			throws Excecoes {

		String sql = null;
		ResultSet res = null;
		Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();

		try {
			sql = " SELECT Notas_Fiscais.* "
					+ "       ,Conhecimentos_Notas_Fiscais.oid_Conhecimento as oid_Conhecimento_Nota_Fiscal "
					+ " FROM Notas_Fiscais "
					+ "      left join (Conhecimentos_Notas_Fiscais "
					+ "                 left join Conhecimentos "
					+ "                   on Conhecimentos_Notas_Fiscais.oid_Conhecimento = Conhecimentos.oid_Conhecimento) "
					+ "        on notas_fiscais.oid_Nota_Fiscal = Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal  "
					+ " WHERE (Conhecimentos.DM_Situacao is null or Conhecimentos.DM_Situacao <> 'C') ";
			if (doValida(edV.getOid_nota_fiscal()))
				sql += "   AND Notas_Fiscais.oid_nota_fiscal = "
						+ getSQLString(edV.getOid_nota_fiscal());
			if (edV.getNr_nota_fiscal() > 0)
				sql += "   AND Notas_Fiscais.NR_Nota_Fiscal = "
						+ edV.getNr_nota_fiscal();
			if (edV.getOID_Unidade() > 0)
				sql += "   AND Notas_Fiscais.oid_Unidade = "
						+ edV.getOID_Unidade();
			if (doValida(edV.getDm_tipo_nota_fiscal()))
				sql += "   AND Notas_Fiscais.DM_Tipo_Nota_Fiscal = "
						+ getSQLString(edV.getDm_tipo_nota_fiscal());
System.out.println(sql);
			res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				ed.setOid_nota_fiscal(res.getString("oid_nota_fiscal"));
				ed.setOid_Nota_Fiscal_Original(res.getString("oid_Nota_Fiscal_Original"));
				ed.setOid_Nota_Fiscal_Devolucao(res.getString("oid_Nota_Fiscal_Devolucao"));
				ed.setNr_nota_fiscal(res.getLong("nr_nota_fiscal"));
				ed.setNr_Itens(res.getDouble("nr_Itens"));
				ed.setNr_volumes(res.getDouble("nr_Volumes"));
				ed.setNr_volumes_nota(res.getLong("nr_Volumes"));
				ed.setNR_Peso(res.getDouble("NR_Peso"));
				ed.setNR_Peso_Cubado(res.getDouble("NR_Peso_Cubado"));
				ed.setNR_Proximo_Lote(res.getLong("NR_Proximo_Lote"));
				ed.setNm_serie(res.getString("nm_serie"));
				ed.setDT_Entrega(dataFormatada.getDT_FormataData(res.getString("DT_Entrega")));
				ed.setDt_emissao(res.getString("dt_emissao"));
				ed.setDM_Frete(res.getString("DM_Frete"));
				dataFormatada.setDT_FormataData(ed.getDt_emissao());
				ed.setDt_emissao(dataFormatada.getDT_FormataData());
				ed.setDt_entrada(res.getString("dt_entrada"));
				dataFormatada.setDT_FormataData(ed.getDt_entrada());
				ed.setDt_entrada(dataFormatada.getDT_FormataData());

				ed.setHr_entrada(res.getString("hr_entrada"));
				ed.setOid_pessoa(res.getString("oid_pessoa"));
				ed.setOid_pessoa_destinatario(res.getString("oid_pessoa_destinatario"));
				ed.setOid_Pessoa_Consignatario(res.getString("oid_Pessoa_Consignatario"));
				ed.setOid_Pessoa_Redespacho(res.getString("oid_Pessoa_Redespacho"));
				ed.setOid_pessoa_fornecedor(res.getString("oid_pessoa_fornecedor"));
				ed.setOid_pessoa_transportador(res.getString("oid_pessoa_transportador"));
				//ed.setOid_Vendedor(ed.getOid_pessoa_fornecedor());
				// ed.setOid_Vendedor(ed.getOid_pessoa());

				ed.setOid_Vendedor(getTableStringValue("oid_Vendedor", "Clientes","oid_Cliente = '" + ed.getOid_pessoa()	+ "'"));

				ed.setOid_natureza_operacao(res.getLong("oid_natureza_operacao"));
				ed.setOid_Natureza_Operacao_Servico(res.getInt("oid_Natureza_Operacao_Servico"));
				ed.setOid_modelo_nota_fiscal(res.getInt("oid_modelo_nota_fiscal"));
				ed.setOid_Condicao_Pagamento(res.getInt("oid_condicao_pagamento"));
				ed.setCD_Condicao_Pagamento(getTableStringValue(
						"CD_Condicao_Pagamento", "Condicoes_Pagamentos",
						"oid_Condicao_Pagamento="
								+ ed.getOid_Condicao_Pagamento()));
				ed.setNM_Condicao_Pagamento(getTableStringValue(
						"NM_Condicao_Pagamento", "Condicoes_Pagamentos",
						"oid_Condicao_Pagamento="
								+ ed.getOid_Condicao_Pagamento()));

				ed.setOid_Tabela_Venda(res.getInt("oid_Tabela_Venda"));
				ed.setOid_Conta(res.getInt("oid_Conta"));
				ed.setVl_total_frete(res.getDouble("vl_total_frete"));
				ed.setVl_icms(res.getDouble("vl_icms"));
				ed.setVL_Base_Calculo_ICMS(res.getDouble("VL_Base_Calculo_ICMS"));
				ed.setPE_Aliquota_ICMS(res.getDouble("PE_Aliquota_ICMS"));
				ed.setVl_inss(res.getDouble("vl_inss"));
				ed.setVl_irrf(res.getDouble("vl_irrf"));
				ed.setVl_ipi(res.getDouble("vl_ipi"));
				ed.setVL_Servico(res.getDouble("vl_servico"));
				ed.setVl_isqn(res.getDouble("vl_isqn"));
				ed.setVl_total_seguro(res.getDouble("vl_total_seguro"));
				ed.setVl_total_despesas(res.getDouble("vl_total_despesas"));
				ed.setVl_nota_fiscal(res.getDouble("vl_nota_fiscal"));
				ed.setVl_liquido_nota_fiscal(res.getDouble("vl_liquido_nota_fiscal"));
				ed.setVL_Pis(res.getDouble("VL_Pis"));
				ed.setVL_Base_Calculo_ICMS_Subst(res.getDouble("VL_Base_Calculo_ICMS_Subst"));
				ed.setVL_ICMS_Subst(res.getDouble("VL_ICMS_Substituicao"));
				ed.setVL_Csll(res.getDouble("VL_Csll"));
				ed.setVL_Cofins(res.getDouble("VL_Cofins"));

				ed.setDm_tipo_nota_fiscal(res.getString("dm_tipo_nota_fiscal"));
				ed.setDt_stamp(res.getString("dt_stamp"));
				ed.setDm_observacao(res.getString("dm_observacao"));
				ed.setNm_complemento(res.getString("Nm_complemento"));
				ed.setDm_forma_pagamento(res.getString("dm_forma_pagamento"));
				ed.setNr_parcelas(res.getLong("nr_parcelas"));
				ed.setVl_descontos(res.getDouble("vl_descontos"));
				ed.setVL_Desconto_Itens(res.getDouble("VL_Desconto_Itens"));
				ed.setVL_Adicional(res.getDouble("VL_Adicional"));
				ed.setVL_Diferenca_Aliq_ICMS(res.getDouble("VL_Diferenca_Aliq_ICMS"));
				ed.setOID_Unidade_Contabil(res.getLong("oid_unidade_contabil"));
				ed.setOID_Unidade_Fiscal(res.getLong("oid_unidade_fiscal"));
				ed.setOID_Unidade(res.getLong("oid_unidade"));
				ed.setOid_Centro_Custo(new Integer(res.getInt("Oid_Centro_Custo")));
				ed.setDm_finalizado(res.getString("dm_finalizado"));
				ed.setDM_Situacao(res.getString("dm_finalizado"));

				if("C".equals(res.getString("dm_situacao"))){ //cancelada
					ed.setDm_finalizado("C");
					ed.setDM_Situacao("C");
				}
				ed.setDM_Pendencia(res.getString("DM_Pendencia"));
				ed.setDM_Impresso(res.getString("DM_Impresso"));
				ed.setDM_Tipo_Devolucao(res.getString("DM_Tipo_Devolucao"));
				ed.setDM_Estoque(res.getString("DM_Estoque"));

				ed.setOid_Entregador(res.getInt("oid_Entregador"));
				ed.setOid_Veiculo(res.getString("oid_Veiculo"));
				ed.setOid_Carga_Entrega(res.getInt("oid_Carga_Entrega"));
				ed.setOid_Conhecimento(getValueDef(res.getString("oid_Conhecimento_Nota_Fiscal"), ""));
				ed.setDm_Devolvido(getValueDef(res.getString("dm_Devolvido"), "N"));

//				DADOS NFE
				ed.setNfe_recibo(res.getString("nfe_recibo"));
				ed.setNfe_data_lote(res.getString("nfe_data_lote"));
				ed.setNfe_cstat_lote(res.getString("nfe_cstat_lote"));
				ed.setNfe_motivo_lote(res.getString("nfe_motivo_lote"));

				ed.setNfe_chave_acesso(res.getString("nfe_chave_acesso"));
				ed.setNfe_protocolo(res.getString("nfe_protocolo"));
				ed.setNfe_digestvalue(res.getString("nfe_digestvalue"));
				ed.setNfe_cstat(res.getString("nfe_cstat"));
				ed.setNfe_motivo(res.getString("nfe_motivo"));
				ed.setNfe_dt_hr_recebimento(res.getString("nfe_dt_hr_recebimento"));

				String detalhe = "<TABLE border=\"0\">";
				if(JavaUtil.doValida(ed.getNfe_recibo())){
					detalhe += "<TR><TD>Recibo/Status:          </TD><TD><FONT color=\"#9999FF\">"+ed.getNfe_recibo()+" / "+ed.getNfe_motivo_lote()+"</FONT></TD></TR>";
					detalhe += "<TR><TD>Data Lote: </TD><TD><FONT color=\"#9999FF\">"+ed.getNfe_data_lote()+"</FONT></TD></TR>";
				}

				if(JavaUtil.doValida(ed.getNfe_chave_acesso())){
					detalhe += "<TR><TD>Chave de Acesso:  </TD><TD><FONT color=\"#9999FF\">"+ed.getNfe_chave_acesso()+"</FONT></TD></TR>";
				}
				if(JavaUtil.doValida(ed.getNfe_protocolo()))
					detalhe += "<TR><TD>Protocolo/Status:  </TD><TD><FONT color=\"#9999FF\">"+ed.getNfe_protocolo()+" / "+ed.getNfe_motivo()+"</FONT></TD></TR>";
				if(JavaUtil.doValida(ed.getNfe_dt_hr_recebimento()))
					detalhe += "<TR><TD>Data/Hora Recto: </TD><TD><FONT color=\"#9999FF\">"+new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss").format(Utils.getInstance().convertStringDateSefaztoData(ed.getNfe_dt_hr_recebimento()))+"</FONT></TD></TR>";
				detalhe += "</TABLE>";
				ed.setSO(detalhe);

				//*** Carrega Dados
				if (ed.getOid_modelo_nota_fiscal() > 0)
					ed.edModelo = new Modelo_Nota_FiscalBD(executasql).getByRecord(new Modelo_Nota_FiscalED((int) ed.getOid_modelo_nota_fiscal()));

			}
			return ed;
		} catch (Exception exc) {
			exc.printStackTrace();
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "getByRecord()");
		} finally {
			closeResultset(res);
		}
	}

	public void inclui_Parcelamento(Nota_Fiscal_EletronicaED ed,
			int oid_Condicao_Pagamento_OLD, double VL_Liquido_OLD)
			throws Excecoes {

		String sql = null;
		ResultSet res = null;
		String dtParcela = "";
		double resto = 0, valor = 0, VL_Parcela1 = 0, VL_Parcela = 0;
		long next_oid = 0, arred = 0;
		long[] condicao = { 0, 0, 0, 0, 0 }; //*** N� M�ximo atual de Parcelas
											 // pelas condi��es de pagamento== 5
		int n_Parcelas = 1, count = 1;
		boolean canInsert = false;

		try {
			if (!doValida(ed.getDt_emissao()))
				throw new Mensagens(
						"Data para calculo do PARCELAMENTO n�o informada!");

			//*** Condi��o de Pagamento, n�o gera Parcelamento
			int oid_Condicao_Isento = Parametro_FixoED.getInstancia()
					.getOID_Condicao_Pagamento_Isento();
			if (oid_Condicao_Isento > 0
					&& oid_Condicao_Isento == ed.getOid_Condicao_Pagamento())
				return;

			//*** Se Existe Parcelas
			if (doExiste("parcelamentos_financeiros", "oid_nota_fiscal = '"
					+ ed.getOid_nota_fiscal() + "'")) {
				//*** Se n�o foi passado ele busca oid
				if (oid_Condicao_Pagamento_OLD <= 0 || VL_Liquido_OLD <= 0) {
					//*** Busca Condi��o antiga para verificar se foi altera
					sql = " SELECT oid_condicao_pagamento, VL_Liquido_Nota_Fiscal"
							+ " FROM Notas_Fiscais"
							+ " WHERE oid_Nota_Fiscal = '"
							+ ed.getOid_nota_fiscal() + "'";
					res = executasql.executarConsulta(sql);
					if (res.next()) {
						oid_Condicao_Pagamento_OLD = res
								.getInt("oid_condicao_pagamento");
						VL_Liquido_OLD = res
								.getDouble("VL_Liquido_Nota_Fiscal");
					}
				}
				//*** Se foi alterada ent�o deleta todas parcelas para o
				// reparcelamento
				if (oid_Condicao_Pagamento_OLD > 0
						&& (oid_Condicao_Pagamento_OLD != ed
								.getOid_Condicao_Pagamento())
						|| VL_Liquido_OLD > 0
						&& (VL_Liquido_OLD != ed.getVl_liquido_nota_fiscal())) {
					sql = " DELETE FROM parcelamentos_financeiros "
							+ " WHERE oid_nota_fiscal = '"
							+ ed.getOid_nota_fiscal() + "'";
					executasql.executarUpdate(sql);
					canInsert = true;
				}
			} else
				canInsert = true;

			//*** Verifica sepode Inserir
			if (canInsert) {
					ed.setNr_parcelas(n_Parcelas);

				//*** Faz divis�o dos valores para cada parcela
				//Desconta a retencao de INSS
				valor = (ed.getVl_liquido_nota_fiscal()-ed.getVl_inss()) / ed.getNr_parcelas();
//				valor = ed.getVl_liquido_nota_fiscal() / ed.getNr_parcelas();
				if (ed.getNr_parcelas() <= 1) {
					VL_Parcela1 = valor;
					VL_Parcela = valor;
				} else {
					arred = Math.round(valor);
					if ((valor - arred) == 0) {
						VL_Parcela1 = valor;
						VL_Parcela = valor;
					} else {

						//*** Arredonda Valor
						valor = Valor.round(valor);
						//*** Resto
						resto = Valor.round(ed.getVl_liquido_nota_fiscal()
								- (valor * ed.getNr_parcelas()));

						VL_Parcela1 = (valor + resto);
						VL_Parcela = valor;
					}
				}

				//*** Grava Parcelas
				while (count <= ed.getNr_parcelas()) {
					//*** Incrementa oid
					if (next_oid > 0)
						++next_oid;
					else
						next_oid = getAutoIncremento("oid_parcelamento",
								"parcelamentos_financeiros");

					//*** Armazena Data na variavel para n�o mudar seu valor a
					// cada intera��o
					dtParcela = ed.getDt_emissao();

					//*** Incrementa parcela para o mes seguinte
					Calendar caledario = Data.stringToCalendar(dtParcela,
							Data.SHORT_DATE);
					int diasAtuais = caledario.get(Calendar.DAY_OF_MONTH);
					caledario.set(Calendar.DAY_OF_MONTH, diasAtuais
							+ (int) (condicao[count - 1]));
					dtParcela = new DateFormatter().calendarToString(caledario,
							Data.SHORT_DATE);

					sql = " INSERT INTO Parcelamentos_Financeiros ("
							+ " 	oid_parcelamento, " + "		oid_nota_fiscal, "
							+ "		vl_parcela, " + "		dt_pagamento, "
							+ "		dt_stamp, " + "		dm_tipo_pagamento, "
							+ "		nr_parcelamento) " + " VALUES ";
					sql += "(" + next_oid + ",'" + ed.getOid_nota_fiscal()
							+ "',";
					sql += (count == 1 ? VL_Parcela1 : VL_Parcela);
					sql += ",'" + dtParcela + "','" + Data.getDataDMY() + "','"
							+ ed.getDm_forma_pagamento() + "'," + count + ")";

					executasql.executarUpdate(sql);
					count++;
				}
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "inclui_Parcelamento()");
		} finally {
			closeResultset(res);
		}
	}

	public void inclui_Lancamento(Nota_Fiscal_EletronicaED ed) throws Excecoes {

		String sql = null;
		ResultSet res = null;
		Lancamento_ContabilED lan = new Lancamento_ContabilED();
		Lancamento_ContabilBD geralan = new Lancamento_ContabilBD(
				this.executasql);
		int valor = 0;
		double quantia = 0;

		try {
			sql = " Select * from Sugestoes_Modelos sm, eventos_contabeis ev ";
			sql += " where sm.oid_sugestao_contabil = ev.oid_sugestao_contabil "
					+ "   and sm.oid_modelo_nota_fiscal = "
					+ ed.getOid_modelo_nota_fiscal();
			res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				lan.setOID_Conta(res.getLong("oid_conta"));
				lan.setDM_Acao(res.getString("Dm_tipo_contabilizacao"));
				lan.setCD_Historico(res.getLong("Oid_Historico"));
				valor = res.getInt("cd_valor");
				switch (valor) {
				case 1:
					quantia = ed.getVl_nota_fiscal();
					break;
				case 2:
					quantia = ed.getVl_liquido_nota_fiscal();
					break;
				case 3:
					quantia = ed.getVl_icms();
					break;
				case 4:
					quantia = ed.getVl_irrf();
					break;
				case 5:
					quantia = ed.getVl_inss();
					break;
				case 6:
					quantia = ed.getVl_isqn();
					break;
				case 7:
					quantia = ed.getVl_descontos();
					break;
				case 8:
					quantia = ed.getVl_ipi();
					break;
				case 9:
					quantia = ed.getVl_total_frete();
					break;
				case 10:
					quantia = ed.getVl_total_seguro();
					break;
				case 11:
					quantia = ed.getVl_total_despesas();
					break;
				case 12:
					quantia = ed.getVL_Servico();
					break;
				case 20:
					quantia = ed.getVL_Pis();
					break;
				case 21:
					quantia = ed.getVL_ICMS_Subst();
					break;
				case 22:
					quantia = ed.getVL_Csll();
					break;

				}
				lan.setOID_Unidade_Contabil(ed.getOID_Unidade_Contabil());
				lan.setVL_Lancamento(quantia);

				lan.setOID_Nota_Fiscal(ed.getOid_nota_fiscal());

				lan.setNM_Complementar("");
				lan.setDM_Stamp(" ");
				lan.setDT_Stamp(Data.getDataDMY());
				lan.setDT_Lancamento(ed.getDt_entrada());
				if (quantia > 0) {
					geralan.inclui(lan);
				}
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "inclui_Lancamento()");
		} finally {
			closeResultset(res);
		}
	}

	/* Atualiza a tabela para dm_finaliza = "R" */
	public void reabre(Nota_Fiscal_EletronicaED ed) throws Excecoes {
		String sql = null;
		ResultSet res = null;
		Parcelamento_FinanceiroED Parcelamento = new Parcelamento_FinanceiroED();

		try {
			//exclui os compromissos e depois reabre
			sql = "Select * from Parcelamentos_financeiros where oid_nota_fiscal = '"
					+ ed.getOid_nota_fiscal() + "'";
			res = executasql.executarConsulta(sql);

			while (res.next()) {
				Parcelamento.setOID_Nota_Fiscal(ed.getOid_nota_fiscal());
				Parcelamento.setOID_Parcelamento(res
						.getLong("oid_parcelamento"));
				Parcelamento.setNR_Nota_Fiscal(String.valueOf(ed
						.getNr_nota_fiscal()));
				new Parcelamento_FinanceiroBD(this.executasql)
						.deleta(Parcelamento);
			}
			//se a exclus�o de todos os compromisso n�o for problam�tica ent�o
			// reabre a nota
			sql = "update notas_fiscais Set dm_finalizado = 'R' "
					+ "Where oid_Nota_Fiscal ='" + ed.getOid_nota_fiscal()
					+ "'";
			executasql.executarUpdate(sql);
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "reabre()");
		}
	}

	public void inclui_Lancamentos_Pagamento_Compromisso(Lote_CompromissoED ed)
			throws Excecoes {

		String sql = null;
		Lancamento_ContabilED lan = new Lancamento_ContabilED();
		Lancamento_ContabilBD geralan = new Lancamento_ContabilBD(
				this.executasql);
		int valor = 0;
		Double quantia = null;

		try {
			sql = "Select * from Sugestoes_Modelos sm, eventos_contabeis ev ";
			sql += "where sm.oid_sugestao_contabil=ev.oid_sugestao_contabil and sm.oid_modelo_nota_fiscal="
					+ ed.getOID_Modelo();
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				lan.setOID_Conta(res.getLong("oid_conta"));
				lan.setDM_Acao(res.getString("Dm_tipo_contabilizacao"));
				lan.setCD_Historico(res.getLong("Oid_Historico"));
				valor = res.getInt("cd_valor");

				switch (valor) {

				case 1:
					quantia = ed.getVl_Compromisso();
					break;
				case 2:
					quantia = ed.getVL_Pedagio_Pagar();
					break;
				case 3:
					quantia = ed.getVL_Pedagio_Receber();
					break;
				case 4:
					quantia = ed.getVl_Lote_Pagamento();
					break;
				case 5:
					quantia = ed.getVl_Multa_Pagamento();
					break;
				case 6:
					quantia = ed.getVl_Outras_Despesas();
					break;
				case 7:
					quantia = ed.getVl_Desconto();
					break;
				case 11:
					quantia = ed.getVl_Outras_Despesas();
					break;
				case 13:
				case 14:
				case 15:
					quantia = ed.getVl_Pagamento();
					break;
				case 16:
					quantia = ed.getVL_Juro_Pagamento();
					break;
				case 17:
					quantia = ed.getVl_Multa_Pagamento();
					break;
				case 18:
					quantia = ed.getVL_Pedagio_Pagar();
					break;
				case 19:
					quantia = ed.getVL_Pedagio_Receber();
					break;

				}

				double VL_Lancto = new Double(String.valueOf(quantia))
						.doubleValue();
				lan.setVL_Lancamento(VL_Lancto);
				lan.setOID_Nota_Fiscal("");
				lan.setNM_Complementar("");
				lan.setDM_Stamp(" ");
				lan.setDT_Stamp(Data.getDataDMY());
				lan.setDT_Lancamento(ed.getDt_Pagamento());
				lan.setOID_Unidade_Contabil(ed.getOID_Unidade());
				lan.setOID_Compromisso(new Long(String.valueOf(ed
						.getOid_Compromisso())).longValue());
				lan.setOID_Lote_Pagamento(new Long(String.valueOf(ed
						.getOid_Lote_Pagamento())).longValue());

				if (VL_Lancto > 0) {
					geralan.inclui_Pagamento(lan);
				}
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(),
					"inclui_Lancamentos_Pagamento_Compromisso()");
		}
	}

	public void inclui_Lancamentos_Lote_Posto(Posto_CompromissoED ed)
			throws Excecoes {

		String sql = null;
		Lancamento_ContabilED lan = new Lancamento_ContabilED();
		Lancamento_ContabilBD geralan = new Lancamento_ContabilBD(
				this.executasql);

		int valor = 0;
		Double quantia = null;

		try {
			sql = "Select * from Sugestoes_Modelos sm, eventos_contabeis ev ";
			sql += "where sm.oid_sugestao_contabil=ev.oid_sugestao_contabil and sm.oid_modelo_nota_fiscal="
					+ ed.getOID_Modelo();

			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				lan.setOID_Conta(res.getLong("oid_conta"));
				lan.setDM_Acao(res.getString("Dm_tipo_contabilizacao"));
				lan.setCD_Historico(res.getLong("Oid_Historico"));
				valor = res.getInt("cd_valor");

				switch (valor) {

				case 1:
					quantia = ed.getVl_Compromisso();
					break;
				case 2:
					quantia = ed.getVL_Pedagio_Pagar();
					break;
				case 3:
					quantia = ed.getVL_Pedagio_Receber();
					break;
				case 4:
					quantia = ed.getVl_Lote_Posto();
					break;
				case 5:
					quantia = ed.getVl_Multa_Pagamento();
					break;
				case 6:
					quantia = ed.getVl_Outras_Despesas();
					break;
				case 7:
					quantia = ed.getVl_Desconto();
					break;
				case 11:
					quantia = ed.getVl_Outras_Despesas();
					break;
				case 13:
				case 14:
				case 15:
					quantia = ed.getVl_Pagamento();
					break;
				case 16:
					quantia = ed.getVL_Juro_Pagamento();
					break;
				case 17:
					quantia = ed.getVl_Multa_Pagamento();
					break;
				case 18:
					quantia = ed.getVL_Pedagio_Pagar();
					break;
				case 19:
					quantia = ed.getVL_Pedagio_Receber();
					break;

				}

				double VL_Lancto = new Double(String.valueOf(quantia))
						.doubleValue();
				lan.setVL_Lancamento(VL_Lancto);
				lan.setOID_Nota_Fiscal("");
				lan.setNM_Complementar("");
				lan.setDM_Stamp(" ");
				lan.setDT_Stamp(Data.getDataDMY());
				lan.setDT_Lancamento(ed.getDt_Pagamento());
				lan.setOID_Unidade_Contabil(ed.getOID_Unidade());
				lan.setOID_Compromisso(new Long(String.valueOf(ed
						.getOid_Compromisso())).longValue());
				lan.setOID_Lote_Posto(new Long(String.valueOf(ed
						.getOid_Lote_Posto())).longValue());

				if (VL_Lancto > 0) {
					geralan.inclui_Pagamento(lan);
				}
			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(),
					"inclui_Lancamentos_Lote_Posto()");
		}
	}

	//*** Baca para corre�ao
	public void corrige(Nota_Fiscal_EletronicaED ed) throws Excecoes {
		ResultSet res = null;
		String sql = null;
		int i =0;
		ArrayList list = new ArrayList();
		try {
			// Atualiza as notas de saida ...
			sql = "select "+
			      "oid_nota_fiscal "+
			      "from "+
			      "notas_fiscais "+
			      "where "+
			      "dm_tipo_nota_fiscal = 'S' and "+
			      "dt_entrada between '" + ed.getDT_Inicial() + "' and '" + ed.getDT_Final() + "' and "+
			      "vl_desconto_itens > 0 ";
			res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				Nota_Fiscal_EletronicaED edNF = new Nota_Fiscal_EletronicaED();
				edNF.setOid_nota_fiscal(res.getString("oid_nota_fiscal"));
				new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Entradas(new Livro_FiscalED(edNF.getOid_nota_fiscal(), "NF"), "S");
			}
			//Atualiza as notas de entrada....
			sql = "select "+
				  "oid_nota_fiscal_devolucao "+
				  "from "+
				  "notas_fiscais "+
				  "where "+
				  "dm_tipo_nota_fiscal = 'S' and "+
				  "dt_entrada between '" + ed.getDT_Inicial() + "' and '" + ed.getDT_Final() + "' and "+
				  "vl_desconto_itens > 0 and "+
				  "oid_nota_fiscal_devolucao is not null";
			res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				Item_Nota_Fiscal_TransacoesED edItemNF = new Item_Nota_Fiscal_TransacoesED();
				edItemNF.setOID_Nota_Fiscal(res.getString("oid_nota_fiscal_devolucao"));
				list = new Item_Nota_Fiscal_TransacoesBD(this.sql).lista(edItemNF);
				for (i=0;i<list.size();i++) {
					Item_Nota_Fiscal_TransacoesED edItemVolta = new Item_Nota_Fiscal_TransacoesED();
					edItemVolta = (Item_Nota_Fiscal_TransacoesED)list.get(i);
					edItemVolta = new Item_Nota_Fiscal_TransacoesBD(this.sql).getByRecord(edItemVolta);
					new Item_Nota_Fiscal_TransacoesBD(this.sql).altera(edItemVolta);
				}
	            new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Entradas(new Livro_FiscalED(edItemNF.getOID_Nota_Fiscal(), "NF"), "E");

			}
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "finalizaNF_Saida()");
		}
	}

//	*** Faturamento - [Contas a Receber]
	public String FaturamentoNotaFiscal(ArrayList notas) throws Excecoes {

		String sql = null;
//		ResultSet res = null;
//		String nr_Parcela = null;
		String aux = "";
		double vl_duplicata = 0;
		String duplicatas_geradas = "";

		try {
			Iterator it = notas.iterator();
			DuplicataED edDuplicata = new DuplicataED();
			while(it.hasNext()) {
				Nota_Fiscal_EletronicaED nota = (Nota_Fiscal_EletronicaED)it.next();
				String chave = String.valueOf(nota.getOid_pagador()+nota.getDm_grupo_faturamento()/**+nota.getDt_emissao()*/);
				if(!chave.equals(aux)){
					//nova duplicata
					vl_duplicata = nota.getVl_liquido_nota_fiscal();
					edDuplicata = new DuplicataED();

					edDuplicata.setOid_Pessoa(nota.getOid_pagador());
					int dias_vcto = getValueDef(getTableIntValue("nr_dias_vencimento", "clientes", "oid_cliente='"+edDuplicata.getOid_Pessoa()+"'"),30);
//					edDuplicata.setDt_Emissao(nota.getDt_emissao());
					edDuplicata.setDt_Emissao(Data.getDataDMY());
					edDuplicata.setDt_Vencimento(Data.getSomaDiaData(edDuplicata.getDt_Emissao(), dias_vcto));
					edDuplicata.setNr_Parcela(new Integer(1));
					edDuplicata.setVl_Duplicata(new Double(vl_duplicata));
					edDuplicata.setVl_Saldo(new Double(vl_duplicata));
					edDuplicata.setVl_Comissao(0);
					edDuplicata.setOid_Vendedor(nota.getOid_Vendedor());
					edDuplicata.setOid_Unidade(new Long(nota.getOID_Unidade()));
					edDuplicata.setOid_Carteira(
							new Integer(
								getValueDef(
									getTableIntValue("oid_Carteira", "Clientes","oid_Cliente = '"+edDuplicata.getOid_Pessoa()+ "'"),
									getTableIntValue("oid_Carteira", "Unidades","oid_Unidade = '"+edDuplicata.getOid_Unidade()+ "'")
								)
							)
					);
//					*** Busca na CARTEIRA os valores de JUROS e MULTA, e os
					// calcula para a duplicata
					sql = " SELECT pe_juros, pe_multa " + " FROM carteiras "
							+ " WHERE oid_carteira = "
							+ edDuplicata.getOid_Carteira();
					ResultSet rsLocal = this.executasql.executarConsulta(sql);
					while (rsLocal.next()) {
						edDuplicata.setVL_Juro_Mora_Dia(new Double((rsLocal.getDouble("pe_juros")/ 30 * vl_duplicata / 100)));
						edDuplicata.setVL_Multa(new Double((rsLocal.getDouble("pe_multa") * vl_duplicata / 100)));
					}
					edDuplicata.setOid_Tipo_Documento(new Integer(Parametro_FixoED.getInstancia().getOid_Tipo_Documento_Nota_Fiscal_Saida()));
					edDuplicata.setDM_Tipo_Cobranca("F");
					/** INCLUI DUPLICATA * */
					edDuplicata = new DuplicataBD(executasql).inclui(edDuplicata);
					duplicatas_geradas += edDuplicata.getNr_Duplicata()+", ";
				} else {
					//soma na duplicata ja inclusa
					vl_duplicata = edDuplicata.getVl_Saldo().doubleValue() + nota.getVl_liquido_nota_fiscal();
					edDuplicata.setVl_Duplicata(new Double(vl_duplicata));
					edDuplicata.setVl_Saldo(new Double(vl_duplicata));

					String sqlUpdate = "UPDATE duplicatas " +
									   " set vl_duplicata = (vl_duplicata+"+nota.getVl_liquido_nota_fiscal()+")" +
									   "    ,vl_saldo = (vl_saldo+"+nota.getVl_liquido_nota_fiscal()+")" +
								   	   " WHERE oid_duplicata = " + edDuplicata.getOid_Duplicata();
					executasql.executarUpdate(sqlUpdate);
				}

//				*** Gera Origens Duplicatas
				sql = " INSERT INTO Origens_Duplicatas ("
						+ "		 oid_Origem_Duplicata"
						+ "		,oid_Duplicata"
						+ "		,oid_Conhecimento"
						+ "		,oid_Parcelamento"
						+ "		,oid_Nota_Fiscal"
						+ "		,DT_Origem_Duplicata"
						+ "		,HR_Origem_Duplicata"
						+ "		,DM_Situacao) "
						+ "	VALUES ("
						+ "'"
						+ (edDuplicata.getOid_Duplicata()
						+ nota.getOid_nota_fiscal()) + "'" + ","
						+ edDuplicata.getOid_Duplicata() + ",'"
						+ nota.getOid_Conhecimento() + "'" + ","
						+ "0,'"
						+ nota.getOid_nota_fiscal() + "'" + ",'"
						+ Data.getDataDMY() + "'" + ",'" + Data.getHoraHM()
						+ "'" + ",'A')";
				executasql.executarUpdate(sql);

				sql = " UPDATE Notas_Fiscais SET"
					+ " DM_Faturado = 'S'"
					+ " WHERE oid_nota_fiscal = '" + nota.getOid_nota_fiscal() + "'";
				executasql.executarUpdate(sql);

				aux = String.valueOf(nota.getOid_pagador()+nota.getDm_grupo_faturamento()/**+nota.getDt_emissao()*/);
			}

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "geraDuplicatas()");
		}

		return duplicatas_geradas;
	}

	public ArrayList listaNFparaFaturamento(Nota_Fiscal_EletronicaED edV) throws Excecoes {

		String sql = null;
		ResultSet res = null;
		ArrayList lista = new ArrayList();
		try {
			sql = " SELECT Notas_Fiscais.*, clientes.oid_cliente_pagador, clientes.oid_vendedor "
					+ " FROM Notas_Fiscais, modelos_notas_fiscais, clientes "
					+ " WHERE Notas_Fiscais.oid_Modelo_Nota_Fiscal = modelos_notas_fiscais.oid_Modelo_Nota_Fiscal "
					+ " AND   Notas_Fiscais.oid_pessoa_destinatario = clientes.oid_pessoa "
					+ " AND   modelos_notas_fiscais.dm_movimenta_financeiro = 'S' "
					+ " AND   clientes.dm_faturamento = 'F' "
					+ " AND   (Notas_Fiscais.dm_situacao = 'F' or Notas_Fiscais.dm_situacao = 'A') "
					+ " AND   Notas_Fiscais.dm_faturado = 'N' "
					+ " AND   Notas_Fiscais.dm_finalizado = 'F' "
					+ " AND   Notas_Fiscais.DM_Tipo_Nota_Fiscal = 'S' ";

			if (doValida(edV.getOid_nota_fiscal()))
				sql += " AND oid_Nota_Fiscal="
						+ getSQLString(edV.getOid_nota_fiscal());
			else {
				//*** Busca Pelo RANGE
				if (edV.getNr_nota_fiscal() > 0
						&& edV.getNr_nota_fiscal_final() > 0)
					sql += " AND NR_Nota_Fiscal between "
							+ edV.getNr_nota_fiscal() + " AND "
							+ edV.getNr_nota_fiscal_final();
				else if (edV.getNr_nota_fiscal() > 0)
					sql += " AND NR_Nota_Fiscal=" + edV.getNr_nota_fiscal();
				else if (edV.getNr_nota_fiscal_final() > 0)
					sql += " AND NR_Nota_Fiscal="
							+ edV.getNr_nota_fiscal_final();
//				if (doValida(edV.getOid_nota_fiscal_manifesto()))
//					sql += " AND oid_Nota_Fiscal_manifesto="
//							+ getSQLString(edV.getOid_nota_fiscal_manifesto());
//				if (doValida(edV.getOid_nota_fiscal_retorno()))
//					sql += " AND oid_Nota_Fiscal_retorno="
//							+ getSQLString(edV.getOid_nota_fiscal_retorno());
				if (doValida(edV.getNm_serie()))
					sql += " AND NM_Serie ='" + edV.getNm_serie() + "' ";
				if (edV.getOid_modelo_nota_fiscal() > 0)
					sql += " AND Oid_Modelo_Nota_Fiscal ="
							+ edV.getOid_modelo_nota_fiscal();
				if (doValida(edV.getOid_pessoa()))
					sql += " AND Notas_Fiscais.oid_Pessoa ='" + edV.getOid_pessoa() + "' ";
				if (doValida(edV.getOid_pessoa_destinatario()))
					sql += " AND Notas_Fiscais.oid_Pessoa_destinatario ='"
							+ edV.getOid_pessoa_destinatario() + "' ";
				if (doValida(edV.getOid_Pessoa_Consignatario()))
					sql += " AND Notas_Fiscais.oid_Pessoa_Consignatario ='"
							+ edV.getOid_Pessoa_Consignatario() + "' ";
				if (doValida(edV.getOid_Pessoa_Redespacho()))
					sql += " AND Notas_Fiscais.oid_Pessoa_Redespacho ='"
							+ edV.getOid_Pessoa_Redespacho() + "' ";
				if (doValida(edV.getOid_pessoa_fornecedor()))
					sql += " AND Notas_Fiscais.oid_Pessoa_fornecedor ='"
							+ edV.getOid_pessoa_fornecedor() + "' ";
				if (doValida(edV.getOid_Vendedor()))
					sql += " AND Notas_Fiscais.oid_Pessoa_Fornecedor = '"
							+ edV.getOid_Vendedor() + "'";
				if (doValida(edV.getOid_Veiculo()))
					sql += " AND oid_Veiculo = '" + edV.getOid_Veiculo() + "'";
				//*** ENTREGADOR x CARGA
				if (edV.getOid_Carga_Entrega() > 0
						&& edV.getOid_Entregador() > 0)
					sql += " AND (oid_Carga_Entrega = "
							+ edV.getOid_Carga_Entrega()
							+ " AND oid_Entregador = "
							+ edV.getOid_Entregador() + ")";
				else if (edV.getOid_Entregador() > 0)
					sql += " AND oid_Entregador = " + edV.getOid_Entregador();
				else if (edV.getOid_Carga_Entrega() > 0)
					sql += " AND oid_Carga_Entrega = "
							+ edV.getOid_Carga_Entrega();

				//*** Data da Entrega
				if (doValida(edV.getDT_Entrega())
						&& doValida(edV.getDT_Entrega_Final())) {
					sql += " AND Notas_Fiscais.DT_Entrega BETWEEN '"
							+ edV.getDT_Entrega() + "' AND '"
							+ edV.getDT_Entrega_Final() + "'";
				} else if (doValida(edV.getDT_Entrega())
						|| doValida(edV.getDT_Entrega_Final())) {
					sql += " AND Notas_Fiscais.DT_Entrega = '"
							+ (doValida(edV.getDT_Entrega()) ? edV
									.getDT_Entrega() : edV
									.getDT_Entrega_Final()) + "'";
				}
				//*** Data da Entrada
				if (doValida(edV.getDt_entrada())
						&& doValida(edV.getDt_entrada_final())) {
					sql += " AND Notas_Fiscais.DT_Entrada BETWEEN '"
							+ edV.getDt_entrada() + "' AND '"
							+ edV.getDt_entrada_final() + "'";
				} else if (doValida(edV.getDt_entrada())
						|| doValida(edV.getDt_entrada_final())) {
					sql += " AND Notas_Fiscais.DT_Entrada = '"
							+ (doValida(edV.getDt_entrada()) ? edV
									.getDt_entrada() : edV
									.getDt_entrada_final()) + "'";
				}
				if (doValida(edV.getDt_emissao()))
					sql += " AND Notas_Fiscais.DT_Emissao = '"
							+ edV.getDt_emissao() + "'";
				if (doValida(edV.getDT_Inicial()))
					sql += " AND Notas_Fiscais.DT_Emissao >= '"
							+ edV.getDT_Inicial() + "'";
				if (doValida(edV.getDT_Final()))
					sql += " AND Notas_Fiscais.DT_Emissao <= '"
							+ edV.getDT_Final() + "'";


				if (doValida(edV.getDM_Estoque()))
					sql += " AND Notas_Fiscais.DM_Estoque ='"
							+ edV.getDM_Estoque() + "' ";
				if (doValida(edV.getDM_Impresso())
						&& !edV.getDM_Impresso().equals("T"))
					sql += " AND Notas_Fiscais.DM_Impresso ='"
							+ edV.getDM_Impresso() + "' ";
				if (doValida(edV.getDM_Pendencia())
						&& !edV.getDM_Pendencia().equals("T"))
					sql += " AND Notas_Fiscais.DM_Pendencia ='"
							+ edV.getDM_Pendencia() + "' ";
				if (edV.getOID_Unidade_Contabil() > 0)
					sql += " AND Notas_Fiscais.oid_unidade_contabil = "
							+ edV.getOID_Unidade_Contabil();

				if (doValida(edV.getDm_Devolvido()))
					sql += " AND Notas_Fiscais.DM_Devolvido = '"
							+ edV.getDm_Devolvido() + "'";


				sql += " ORDER BY clientes.oid_cliente_pagador, Notas_Fiscais.NR_Nota_Fiscal";
				//*** Pagina��o
				sql += edV.getSQLPaginacao();
			}
			res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();

				//PAGADOR
				ed.setOid_pagador(res.getString("oid_cliente_pagador"));

				//Agrupador de faturamento por tipo de produto
				String aux = "Select produtos.oid_tipo_produto " +
							 " from produtos, itens_notas_fiscais " +
							 " where itens_notas_fiscais.oid_produto = produtos.oid_produto " +
							 " and   itens_notas_fiscais.oid_nota_fiscal = '" + res.getString("oid_nota_fiscal") + "'" +
							 " limit 1 ";
				ResultSet rsAux = this.executarConsulta(aux);
				ed.setDm_grupo_faturamento("");
				if(rsAux.next()){
					ed.setDm_grupo_faturamento(rsAux.getString(1));
				}

				ed.setOid_nota_fiscal(res.getString("oid_nota_fiscal"));
//				ed.setOid_nota_fiscal_manifesto(res.getString("oid_Nota_Fiscal_Manifesto"));
//				ed.setOid_nota_fiscal_retorno(res.getString("oid_Nota_Fiscal_retorno"));
				ed.setNr_nota_fiscal(res.getLong("nr_nota_fiscal"));
				ed.setDt_emissao(res.getString("dt_emissao"));
				dataFormatada.setDT_FormataData(ed.getDt_emissao());
				ed.setDt_emissao(dataFormatada.getDT_FormataData());
				ed.setDT_Entrega(dataFormatada.getDT_FormataData(res.getString("DT_Entrega")));
				ed.setDt_entrada(res.getString("dt_entrada"));
				dataFormatada.setDT_FormataData(ed.getDt_entrada());
				ed.setDt_entrada(dataFormatada.getDT_FormataData());
				ed.setNr_Itens(res.getDouble("nr_Itens"));
				ed.setNm_serie(res.getString("nm_serie"));
				ed.setDM_Pendencia(res.getString("DM_Pendencia"));
				ed.setNM_Pendencia(doValida(ed.getDM_Pendencia()) && (ed.getDM_Pendencia().equals("P") || ed.getDM_Pendencia().equals("S")) ? "SIM" : "N�O");

				ed.setHr_entrada(res.getString("hr_entrada"));

				ed.setOid_Vendedor(res.getString("oid_vendedor"));

				ed.setOid_pessoa(res.getString("oid_pessoa"));
				ed.setOid_pessoa_destinatario(res.getString("oid_pessoa_destinatario"));
				ed.setOid_Pessoa_Consignatario(res.getString("oid_Pessoa_Consignatario"));
				ed.setOid_Pessoa_Redespacho(res.getString("oid_Pessoa_Redespacho"));
				ed.setOid_pessoa_fornecedor(res.getString("oid_pessoa_fornecedor"));

				ed.setOid_natureza_operacao(res.getLong("oid_natureza_operacao"));
				ed.setOid_Natureza_Operacao_Servico(res.getInt("oid_Natureza_Operacao_Servico"));
				ed.setOid_modelo_nota_fiscal(res.getInt("oid_modelo_nota_fiscal"));
				ed.setOid_Condicao_Pagamento(res.getInt("oid_condicao_pagamento"));
				ed.setOid_Tabela_Venda(res.getInt("oid_Tabela_Venda"));
				ed.setOid_Conta(res.getInt("oid_Conta"));
				ed.setVl_total_frete(res.getDouble("vl_total_frete"));
				ed.setVl_icms(res.getDouble("vl_icms"));
				ed.setVL_Base_Calculo_ICMS(res.getDouble("VL_Base_Calculo_ICMS"));
				ed.setPE_Aliquota_ICMS(res.getDouble("PE_Aliquota_ICMS"));
				ed.setVl_inss(res.getDouble("vl_inss"));
				ed.setVl_irrf(res.getDouble("vl_irrf"));
				ed.setVl_ipi(res.getDouble("vl_ipi"));
				ed.setVL_Servico(res.getDouble("vl_servico"));
				ed.setVl_isqn(res.getDouble("vl_isqn"));
				ed.setVl_total_seguro(res.getDouble("vl_total_seguro"));
				ed.setVl_total_despesas(res.getDouble("vl_total_despesas"));
				ed.setVl_nota_fiscal(res.getDouble("vl_nota_fiscal"));
				ed.setVl_liquido_nota_fiscal(res.getDouble("vl_liquido_nota_fiscal"));
				ed.setVL_Pis(res.getDouble("VL_Pis"));
				ed.setVL_Base_Calculo_ICMS_Subst(res.getDouble("VL_Base_Calculo_ICMS_Subst"));
				ed.setVL_ICMS_Subst(res.getDouble("VL_ICMS_Substituicao"));
				ed.setVL_Csll(res.getDouble("VL_Csll"));
				ed.setVL_Cofins(res.getDouble("VL_Cofins"));
				ed.setNr_volumes(res.getDouble("nr_Volumes"));
				ed.setNr_volumes_nota(res.getLong("nr_Volumes"));
				ed.setNR_Peso(res.getDouble("NR_Peso"));
				ed.setNR_Peso_Cubado(res.getDouble("NR_Peso_Cubado"));

				ed.setDm_tipo_nota_fiscal(res.getString("dm_tipo_nota_fiscal"));
				ed.setDt_stamp(res.getString("dt_stamp"));
				ed.setDm_observacao(res.getString("dm_observacao"));
				ed.setNm_complemento(res.getString("Nm_complemento"));
				ed.setDm_forma_pagamento(res.getString("dm_forma_pagamento"));
				ed.setNr_parcelas(res.getLong("nr_parcelas"));
				ed.setVl_descontos(res.getDouble("vl_descontos"));
				ed.setVL_Desconto_Itens(res.getDouble("VL_Desconto_Itens"));
				ed.setVL_Adicional(res.getDouble("VL_Adicional"));
				ed.setVL_Diferenca_Aliq_ICMS(res.getDouble("VL_Diferenca_Aliq_ICMS"));
				ed.setOID_Unidade_Contabil(res.getLong("oid_unidade_contabil"));
				ed.setOID_Unidade_Fiscal(res.getLong("oid_unidade_fiscal"));
				ed.setOID_Unidade(res.getLong("oid_unidade"));
				ed.setOid_Centro_Custo(new Integer(res.getInt("Oid_Centro_Custo")));
				ed.setDm_finalizado(res.getString("dm_finalizado"));
				ed.setDM_Situacao(res.getString("dm_finalizado"));
				ed.setDM_Impresso(res.getString("DM_Impresso"));
				ed.setDM_Tipo_Devolucao(res.getString("DM_Tipo_Devolucao"));
				ed.setDM_Estoque(res.getString("DM_Estoque"));
				ed.setVl_Comissao(res.getDouble("vl_comissao"));
				ed.setOid_Entregador(res.getInt("oid_Entregador"));
//				ed.setOid_pessoa_transportador(res.getString("oid_Pessoa_Transportador"));
				ed.setOid_Veiculo(res.getString("oid_Veiculo"));
				ed.setOid_Carga_Entrega(res.getInt("oid_Carga_Entrega"));
				ed.setDm_Devolvido(res.getString("dm_Devolvido"));

				lista.add(ed);
			}

//			ordena por tipo de produto e depois por pagador...
            Collections.sort (lista , new Comparator () {
  	          public int compare (Object o1 , Object o2) {
  	        	Nota_Fiscal_EletronicaED ed1 = (Nota_Fiscal_EletronicaED) o1;
  	        	Nota_Fiscal_EletronicaED ed2 = (Nota_Fiscal_EletronicaED) o2;
  	            return ed1.getDm_grupo_faturamento().compareTo(ed2.getDm_grupo_faturamento());
  	          }
  	        });
            Collections.sort (lista , new Comparator () {
	          public int compare (Object o1 , Object o2) {
	        	Nota_Fiscal_EletronicaED ed1 = (Nota_Fiscal_EletronicaED) o1;
	        	Nota_Fiscal_EletronicaED ed2 = (Nota_Fiscal_EletronicaED) o2;
	            return ed1.getOid_pagador().compareTo(ed2.getOid_pagador());
	          }
	        });

			return lista;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "listaNFparaFaturamento()");
		} finally {
			closeResultset(res);
		}
	}


	public Nota_Fiscal_EletronicaED inclui_Nota_Entrada(Nota_Fiscal_EletronicaED ed)
	throws Excecoes {

	Nota_Fiscal_EletronicaED notaEntradaED = new Nota_Fiscal_EletronicaED();

	try {

	notaEntradaED = this.getByRecord(ed);

	notaEntradaED.setDt_entrada(Data.getDataDMY());
	notaEntradaED.setDt_emissao(Data.getDataDMY());
	notaEntradaED.setDT_Entrega(Data.getDataDMY());
	notaEntradaED.setOid_nota_fiscal((ed.getOid_pessoa_destinatario()+"999999999").substring(0,8) + System.currentTimeMillis());

	String oid_Pessoa = notaEntradaED.getOid_pessoa();
	String oid_Pessoa_Destinatario = notaEntradaED.getOid_pessoa_destinatario();
	String NR_Nota_fiscal = String.valueOf(notaEntradaED.getNr_nota_fiscal());

	notaEntradaED.setOid_pessoa(oid_Pessoa);

    // String oidPessoaDestino = getTableStringValue("oid_Pessoa",
	// 		   									  "unidades",
	// 		   									  "oid_unidade = "+notaEntradaED.getOID_Unidade_Fiscal());

    notaEntradaED.setOid_pessoa_destinatario(oid_Pessoa_Destinatario);


	notaEntradaED.setOid_pessoa_fornecedor(oid_Pessoa_Destinatario);

	notaEntradaED.setDM_Pendencia("N");
	notaEntradaED.setDM_Situacao("A");
	notaEntradaED.setDm_finalizado("N");
	notaEntradaED.setDM_Impresso("N");
	notaEntradaED.setDM_Estoque("N");
	notaEntradaED.setNr_nota_fiscal(0);
	notaEntradaED.setNm_serie("1");
	notaEntradaED.setDm_tipo_nota_fiscal("E");
	notaEntradaED.setOid_modelo_nota_fiscal(18);
	notaEntradaED.setOid_natureza_operacao(7);

	notaEntradaED.setDm_observacao(" DEVOLUCAO NOTA FISCAL NR.: " + NR_Nota_fiscal );
	notaEntradaED = this.inclui(notaEntradaED);

	ArrayList list = new ArrayList();
	int i = 0;

	Item_Nota_Fiscal_TransacoesED edItemNF = new Item_Nota_Fiscal_TransacoesED();

	edItemNF.setOID_Nota_Fiscal(ed.getOid_nota_fiscal());

	list = new Item_Nota_Fiscal_TransacoesBD(this.sql).lista(edItemNF);

	for (i=0; i<list.size(); i++) {

		Item_Nota_Fiscal_TransacoesED edItemVolta = new Item_Nota_Fiscal_TransacoesED();

		edItemVolta = (Item_Nota_Fiscal_TransacoesED)list.get(i);

		edItemVolta = new Item_Nota_Fiscal_TransacoesBD(this.sql).getByRecord(edItemVolta);

		edItemVolta.setOID_Nota_Fiscal(notaEntradaED.getOid_nota_fiscal());

		edItemVolta.setOid_natureza_operacao(7);

		edItemVolta.setOID_Item_Nota_Fiscal(0);

		new Item_Nota_Fiscal_TransacoesBD(this.sql).inclui(edItemVolta);

	}

	new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Entradas(new Livro_FiscalED(notaEntradaED.getOid_nota_fiscal(), "NF"), "E");


	} catch (Exception exc) {
	throw new Excecoes(exc.getMessage(), exc,
			this.getClass().getName(), "inclui()");
	}
	return notaEntradaED;
}

	/** Faz a conexao com a biblioteca de NFe, que processara e depois devolvera os dados para impressao do DANFE
     *  @param ArrayList de Nota_Fiscal_TransacoesED
     *  @param Data de Saida
     *  @param Hora de Saida
     *  @return NfeNotaFiscal nota para ser impressa ou NfeNotaFiscal vazio no caso de algum erro
     *  @throws Excecoes
     * */
    public TNFe geraNFe(Empresa empresa, Nota_Fiscal_EletronicaED nota, String dtSaida, String hrSaida) throws Excecoes {

    	TNFe nfeReturn = new TNFe();
        try {
        	 // System.out.println("geraNotaFiscal_to_NFe 1");
//        	Origem_DuplicataED DupliED = new Origem_DuplicataED();

        	// ************************************************************
            String toReturn = "";
            String sqlUpdate = "";

            DecimalFormat dec = new DecimalFormat("#0.00");
            //*** Variveis
            ResultSet resLocal = null;
            String sqlBusca = null;
        	 // System.out.println("printNotaFiscalSaida 2");

            int i = 0,
                countItens = 0,
                nrNotas = 0,
                nrNotaAtual = 0;

            boolean canList = true,
                    isSaida = false,
                    isIsentoSubst = false;
            long oidPedido = 0;
            double baseCalculoICMS = 0
                  ,valorICMS = 0
                  ,peAliquota = 0
                  ,sitTributaria = 0
                  ,subTotalItens = 0
                  ,totalItens = 0
                  ,totalPesoLiquido = 0
                  ,totalPIS = 0
                  ,totalCOFINS = 0;

            
            
//            Iterator iterator = this.lista(edV).iterator();
//            while (iterator.hasNext())
            {
                Nota_Fiscal_EletronicaED ed = this.getByRecord(nota);
 System.out.println("NFe OID:"+ed.getOid_nota_fiscal());

                isSaida = ("D".equals(ed.getDm_tipo_nota_fiscal()) || "R".equals(ed.getDm_tipo_nota_fiscal()) || "S".equals(ed.getDm_tipo_nota_fiscal()));//*** Tipo de NOTA

                ArrayList lista = new Item_Nota_Fiscal_TransacoesBD(executasql).lista(new Item_Nota_Fiscal_TransacoesED(ed.getOid_nota_fiscal()));
                Natureza_OperacaoED edCFOP = new Natureza_OperacaoBD(executasql).getByRecord(new Natureza_OperacaoED(new Integer((int)ed.getOid_natureza_operacao())));

                ClienteBean edCliente = ClienteBean.getByOID_Cliente(empresa, ed.getOid_pessoa_destinatario());
//                ClienteBean edCliente = ClienteBean.getByOID_Cliente_Nota(ed.getOid_pessoa_destinatario());

                PessoaED edPessoa = new PessoaBD(executasql).getByRecord(new PessoaED(ed.getOid_pessoa_destinatario()));

                int oid_uni = new Integer (String.valueOf(ed.getOID_Unidade_Fiscal())).intValue();

                UnidadeBean unidade_Remetente = new UnidadeBean().getByOID_Unidade(empresa, oid_uni);

//                PessoaED edTransportador = new PessoaBD(executasql).getByRecord(new PessoaED(unidade_Remetente.getOID_Pessoa()));
                PessoaED edTransportador = new PessoaED();
                if (doValida(ed.getOid_pessoa_transportador())){
                    edTransportador = new PessoaBD(executasql).getByRecord(new PessoaED(ed.getOid_pessoa_transportador()));
                }else{
                    edTransportador = new PessoaBD(executasql).getByRecord(new PessoaED(unidade_Remetente.getOID_Pessoa()));
                }

                /** SUBSTITUICAO TRIBUTARIA **/
                isIsentoSubst = false;

                if (edCliente.getNR_CNPJ_CPF().length() > 11 &&	"S".equals(edCliente.getDM_Isencao_ICMS())) {
                    isIsentoSubst = true;
                }
            	// System.out.println("printNotaFiscalSaida 4");

            	dtSaida = getValueDef(dtSaida, ed.getDT_Entrega());
            	hrSaida = getValueDef(hrSaida, ed.getHr_entrada());

                //*** Atualiza Status na Nota Fiscal - DESABILITADO!!! Teo: 23/03/2010
                if ("N".equals(ed.getDM_Impresso()))
                {
                	String DM_Nota_Fiscal = getTableStringValue("DM_Nota_Fiscal",	"Modelos_Notas_Fiscais", "oid_modelo_nota_fiscal = '"	+ ed.getOid_modelo_nota_fiscal() + "'");

                	// Se exige n�mero da nota do cliente a data da emissao fica igual a entrada
                	if ("C".equals(DM_Nota_Fiscal)){
                    	ed.setDt_emissao(ed.getDt_entrada());
                    }else{
                    	if (ed.getOid_modelo_nota_fiscal() == 13 || ed.getOid_modelo_nota_fiscal() == 15){
                        	ed.setDt_emissao(ed.getDt_emissao());
                    	}else{
                        	ed.setDt_emissao(Data.getDataDMY());
                    	}
                    }

                	if(Data.comparaData(dtSaida, "<", ed.getDt_emissao()))
                		dtSaida = ed.getDt_emissao();

                	// System.out.println("printNotaFiscalSaida 5");
        			//SITUACAO NOVA
                	boolean geraNR_Nota_Fiscal=false;
                	// System.out.println("printNotaFiscalSaida DM_Nota_Fiscal="+DM_Nota_Fiscal);

                    if (!"T".equals(ed.getNm_serie())){ ///? JA TINHA
        				geraNR_Nota_Fiscal=true;
                    }
                	if (ed.getNr_nota_fiscal()==0 && "I".equals(DM_Nota_Fiscal)) { //gera numero na IMPRESSAO
        				geraNR_Nota_Fiscal=true;
        			}
                	if (ed.getNr_nota_fiscal()>0 && "C".equals(DM_Nota_Fiscal)) { //NR do CLIENTE informado
        				geraNR_Nota_Fiscal=false;
        			}
                	if (ed.getNr_nota_fiscal()>0 && "E".equals(DM_Nota_Fiscal)) { //Numera na INCLUSAO
        				geraNR_Nota_Fiscal=false;
        			}



                    if (geraNR_Nota_Fiscal){
                    	// System.out.println("printNotaFiscalSaida geraNR_Nota_Fiscal");

                    	Parametro_WmsED pWMS= new Parametro_WmsED();
                    	//Linha para pegar a AIDOF da unidade...
                    	pWMS.setOid_Unidade(new Long(ed.getOID_Unidade_Fiscal()).intValue());
                    	pWMS = new Parametro_WmsBD (executasql).getByRecord(pWMS);
                        //old
//                    	this.setNrSerieNotaFromAIDOF(ed, pWMS.getCd_Aidof_Nota_Fiscal_Devolucao());
                    	//nova numeracao
                    	new Nota_Fiscal_EletronicaRN(empresa).numeraNFe(ed, pWMS.getCd_Aidof_Nota_Fiscal_Devolucao());
                    }
                    sqlUpdate = "UPDATE Notas_Fiscais SET " +
                                "DM_Impresso = 'S' " +
                                ",DM_Situacao = 'F' " +
                                ",DM_Finalizado = 'F' " +
                            ((isSaida) ?
                                ",DT_Emissao = '"+ed.getDt_emissao()+"' " +
                                ",DT_Entrada = '"+ed.getDt_emissao()+"' " +
                                ",DT_Entrega = "+getSQLDate(dtSaida) + " " +
                                ",HR_Entrada = "+getSQLString(hrSaida) + " " +
                                ",NM_Serie = '"+ed.getNm_serie()+"' "
                            :
                            	" "
                            ) +
//                               ",NR_Nota_Fiscal = '"+ed.getNr_nota_fiscal()+"' " +
//                    		   ",unikey = '" + ed.getOid_pessoa() + String.valueOf(ed.getNr_nota_fiscal()) + ed.getNm_serie() + "' " +
                               "WHERE oid_Nota_Fiscal = '"+ed.getOid_nota_fiscal()+"' ";

System.out.println("printNotaFiscalSaida ->>" +sqlUpdate);

                    executasql.executarUpdate(sqlUpdate);
                    // RALPH
                    if ("E".equals(ed.getDm_tipo_nota_fiscal())){

                    	sqlUpdate = "UPDATE Notas_Fiscais SET " +
		                    " DT_Entrega = '"+ed.getDt_emissao() + "' " +
		                    " ,HR_Entrada = '"+Data.getHoraHM() + "' " +
		                    " WHERE oid_Nota_Fiscal = '"+ed.getOid_nota_fiscal()+"' ";

	                    	executasql.executarUpdate(sqlUpdate);
                    }
                }

            	// System.out.println("printNotaFiscalSaida 10");
                PessoaED unidade = new PessoaBD(executasql).getByRecord(unidade_Remetente.getOID_Pessoa());
//              ******** IN�CIO NOTA FISCAL *************
                nrNotaAtual++;
//              Nota Fiscal
                TNFe.InfNFe infNFe = new InfNFe();
                CidadeBean orig = CidadeBean.getByOID(empresa, (int)unidade.getOid_Cidade());
    			String uIBGE_UF = getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+orig.getOID_Estado());
                
                String rnd = String.valueOf(JavaUtil.truncInverse(System.currentTimeMillis(), 8));
                CidadeBean origem = CidadeBean.getByOID(empresa, (int)unidade.getOid_Cidade());
    			String cIBGE_UF = getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+origem.getOID_Estado());
    			CidadeBean destino = CidadeBean.getByOID(empresa, (int)edPessoa.getOid_Cidade());
    			String dIBGE_UF = getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+destino.getOID_Estado());
    	        if(!JavaUtil.doValida(edCliente.getOid_Pessoa())){
    				throw new Excecoes("Problemas com o destinatário: "+edPessoa.getNM_Razao_Social()+".\nDeve ser cadastrado como cliente...");
    			}

//                infNFe.setId("xxx");
                infNFe.setVersao("4.00");
                
                String ser = doValida(ed.getNm_serie())&&"NF1".equals(ed.getNm_serie())?"1" :
                	doValida(ed.getNm_serie())&&"NF7".equals(ed.getNm_serie())?"2" :
                		doValida(ed.getNm_serie())&&"NF6".equals(ed.getNm_serie())?"2" :
                			doValida(ed.getNm_serie())&&"NF12".equals(ed.getNm_serie())?"2" :
                				doValida(ed.getNm_serie())&&"NF02".equals(ed.getNm_serie())?"2" :
                					doValida(ed.getNm_serie())&&"NF08".equals(ed.getNm_serie())?"2" :
                						doValida(ed.getNm_serie())&&"NF10".equals(ed.getNm_serie())?"2" :
                				ed.getNm_serie();
                
                SimpleDateFormat dtUTC = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                
                if(rnd.length()<8) {
                	rnd += String.valueOf(JavaUtil.truncInverse(System.currentTimeMillis(), 8));
                	rnd = rnd.substring(rnd.length()-8);
                }
                
             // Dados Nfe
                Ide ide = new Ide();
                ide.setCUF(uIBGE_UF);
                ide.setCNF(rnd);
                ide.setNatOp(edCFOP.getNm_Natureza_Operacao());
                ide.setMod("55");
                ide.setSerie(ser);
                ide.setNNF(String.valueOf(ed.getNr_nota_fiscal()));
                ide.setDhEmi(dtUTC.format(FormataData.formataDataTB(ed.getDt_emissao()))+"-03:00");
                ide.setDhSaiEnt(dtUTC.format(FormataData.formataDataTB(getValueDef(dtSaida, ed.getDt_emissao())))+"-03:00");
                ide.setTpNF(isSaida ? "1" : "0");
                if(origem.getOID_Estado() != destino.getOID_Estado()){
                	ide.setIdDest("2");//fora do estado... //10-4-14 - VER 3.10
    	        } else {
    	        	ide.setIdDest("1");//dentro do estado... //10-4-14 - VER 3.10
    	        }
                ide.setCMunFG(ManipulaString.limpaCampo((doValida(cIBGE_UF)?cIBGE_UF:"") + origem.getNM_Codigo_IBGE())); //mun fato gerador
                ide.setTpImp("1");
                ide.setTpEmis("1");
//                ide.setCDV("xxx"); dig chave acesso
                ide.setTpAmb("2");
                ide.setFinNFe("1");
                ide.setIndFinal("0");
                ide.setIndPres("2");
                ide.setProcEmi("0");
                ide.setVerProc("8.00");
                infNFe.setIde(ide);

//                notafiscal.setIndPag(0);//indicador da forma de pagamento
//                if(ed.edModelo.getOid_Modelo_Nota_Fiscal()==8){
//                	notafiscal.setIndPag(1);
//                }

                //NOTAS DE DEVOLUCAO
//                if("D".equals(ed.getDm_tipo_nota_fiscal()) || edCFOP.getDM_Tipo_Operacao().equalsIgnoreCase("S")){
//                	if(edCFOP.getOid_Natureza_Operacao() == 43 || edCFOP.getOid_Natureza_Operacao() == 99 || edCFOP.getOid_Natureza_Operacao() == 6)
//                		ide.setFinNFe("1");//Finalidade de emiss�o da NF-E >> SAIDA/REMESSA
//                	else {
//                		ide.setFinNFe("4");//Finalidade de emiss�o da NF-E >> DEVOLUCAO
//	                	String NF_Ref = getTableStringValue("nm_ch_referenciada",	"Notas_Fiscais", "oid_nota_fiscal = '"	+ ed.getOid_nota_fiscal() + "'");
//	                	if(!JavaUtil.doValida(NF_Ref)){
//	                		throw new Excecoes("Para NFe de devolução é necessário informar a chave da nota de entrada referenciada!");
//	                	}
//	                	NfeReferenciada nfref = new NfeReferenciada();
//	                	ArrayList nfsref = new ArrayList();
//	                	nfref.setRefNfe(NF_Ref);
//	                	nfsref.add(nfref);
//	                	notafiscal.setNfeReferenciadaCollection(nfsref);
//                	}
//                } 
//                if("E".equals(ed.getDm_tipo_nota_fiscal()) && edCFOP.getOid_Natureza_Operacao() == 116){
//                	ide.setFinNFe("4");//Finalidade de emiss�o da NF-E >> DEVOLUCAO
//                	String NF_Ref = getTableStringValue("nm_ch_referenciada",	"Notas_Fiscais", "oid_nota_fiscal = '"	+ ed.getOid_nota_fiscal() + "'");
//                	if(!JavaUtil.doValida(NF_Ref)){
//                		throw new Excecoes("Para NFe de devolução de Venda é necessário informar a chave da nota de venda referenciada!");
//                	}
//                	NfeReferenciada nfref = new NfeReferenciada();
//                	ArrayList nfsref = new ArrayList();
//                	nfref.setRefNfe(NF_Ref);
//                	nfsref.add(nfref);
//                	notafiscal.setNfeReferenciadaCollection(nfsref);
//                }

              //Emitente
                Emit emit = new Emit();
                emit.setCNPJ((unidade.getNR_CNPJ_CPF()));
                emit.setXNome(unidade.getNM_Razao_Social());
                emit.setXFant(unidade.getNM_Fantasia());
                
                String endereco = unidade.getNM_Endereco();
    			String numero = endereco.substring (endereco.lastIndexOf (",") + 1);
    			endereco = endereco.substring (0 , endereco.lastIndexOf (","));
                TEnderEmi enderEmit = new TEnderEmi();
                enderEmit.setXLgr(endereco.trim());
                enderEmit.setNro(ManipulaString.limpaCampo(numero));
                enderEmit.setXCpl(null);
                enderEmit.setXBairro(unidade.getNM_Bairro());
                enderEmit.setCMun(ManipulaString.limpaCampo((doValida(cIBGE_UF)?cIBGE_UF:"") + origem.getNM_Codigo_IBGE()));
                enderEmit.setXMun(unidade.getNM_Cidade().trim());
                enderEmit.setUF(TUfEmi.valueOf(unidade.getCD_Estado()));
                enderEmit.setCEP(unidade.getNR_CEP());
                enderEmit.setCPais("1058");
                enderEmit.setXPais("BRASIL");
                enderEmit.setFone(ManipulaString.limpaCampo(unidade.getNR_Telefone()));
                emit.setEnderEmit(enderEmit);
                emit.setIE(unidade.getNM_Inscricao_Estadual());
                emit.setCRT("3");
                infNFe.setEmit(emit);

              //Destinatario
                Dest dest = new Dest();
                if(edCliente.getNR_CNPJ_CPF().length()<14)
                	dest.setCPF((edCliente.getNR_CNPJ_CPF()));
                else
                	dest.setCNPJ((edCliente.getNR_CNPJ_CPF()));
                
                dest.setXNome(edCliente.getNM_Razao_Social());
                endereco = edPessoa.getNM_Endereco();
    			String cpl = null;
    			if(endereco.lastIndexOf(',')>0){
    				numero = endereco.substring (endereco.lastIndexOf (",") + 1);
        			endereco = endereco.substring (0 , endereco.lastIndexOf (","));
        			if(numero.lastIndexOf(' ')>0){
        				cpl = numero.substring (numero.lastIndexOf (" ") + 1);
        			}
    			} else {
    				throw new Excecoes("problemas com endereco do cliente: "+edCliente.getNM_Razao_Social()+".\nDeve ter o numero separado por virgula...");
    			}
                TEndereco enderDest = new TEndereco();
                enderDest.setXLgr(endereco.trim());
                enderDest.setNro(ManipulaString.limpaCampo(numero));
                if(!JavaUtil.doValida(edPessoa.getNM_Bairro())){
    				throw new Excecoes("Problemas com o destinatario: "+edPessoa.getNM_Razao_Social()+".\nO campo BAIRRO eh obrigat�oio...");
    			}
                enderDest.setXBairro(edPessoa.getNM_Bairro().trim());
                enderDest.setXCpl(cpl);
                enderDest.setCMun(ManipulaString.limpaCampo((doValida(dIBGE_UF)?dIBGE_UF:"") + destino.getNM_Codigo_IBGE()));
                enderDest.setXMun(edPessoa.getNM_Cidade().trim());
                enderDest.setUF(TUf.valueOf(edPessoa.getCD_Estado()));
                enderDest.setCEP(ManipulaString.limpaCampo(JavaUtil.doValida(edPessoa.getNR_CEP())?edPessoa.getNR_CEP():"0"));
                enderDest.setCPais("1058");
                enderDest.setXPais("BRASIL");
                enderDest.setFone(ManipulaString.limpaCampo(edPessoa.getNR_Telefone()));
                dest.setEnderDest(enderDest);
                dest.setEmail(null);
                dest.setIE(JavaUtil.getValueDif(edPessoa.getNM_Inscricao_Estadual(),"ISENTO"));
                infNFe.setDest(dest);
    	        
    	        if(!JavaUtil.doValida(edPessoa.getNM_Inscricao_Estadual())){
    	        	dest.setIndIEDest("9");//10-4-14 - VER 3.10
    	        } else if(edPessoa.getNM_Inscricao_Estadual().equalsIgnoreCase("ISENTO")){
    	        	dest.setIndIEDest("2");//10-4-14 - VER 3.10
    	        } else {
    	        	dest.setIndIEDest("1");//10-4-14 - VER 3.10
    	        }

                /** ---- Maximo de Itens ---- **/
                peAliquota = 0;
                int nrItens = lista.size();
                boolean isCFOPNormal = true;
                for (int n=0; n < lista.size(); n++)
                {
                    Item_Nota_Fiscal_TransacoesED edItem = (Item_Nota_Fiscal_TransacoesED) lista.get(n);
                    if (peAliquota != edItem.getPE_Aliquota_ICMS())
                        nrItens++;
                    //*** Descri��o CFOP Diferenciado
                	// System.out.println("printNotaFiscalSaida 10 B");
                    if (isCFOPNormal  && edCFOP.getOid_Natureza_Operacao()!=null && edItem.getOid_natureza_operacao() != edCFOP.getOid_Natureza_Operacao().longValue())
                    {
                        nrItens += 2;
                        isCFOPNormal = false;
                    }
                    peAliquota = edItem.getPE_Aliquota_ICMS();
                }

                //*** FISCAL

                ArrayList msgFiscal = new ArrayList();

                nrNotaAtual = 0;
                baseCalculoICMS = 0;
                valorICMS = 0;
                peAliquota = 0;
                sitTributaria = 0;
                subTotalItens = 0;
                totalItens = 0;
                totalPesoLiquido = 0;
                totalPIS = 0;
                totalCOFINS = 0;
                i = 0;
                oidPedido = 0;
                canList = true;
                isCFOPNormal = true;
//                while (canList)
//                {
                	 System.out.println("NFe itens ...");

                    countItens = 0;

                    if (lista.size() > 0)
                    {
                    	long volumes = 0;
                    	while (i < lista.size())
                        {
                    		countItens++;
                        	Item_Nota_Fiscal_TransacoesED edItem = (Item_Nota_Fiscal_TransacoesED) lista.get(i);
                        	ProdutoED edProduto = new ProdutoBD(executasql).getByRecord(new ProdutoED(edItem.getOID_Produto()));
                        	Det det = new Det();
                            det.setNItem(String.valueOf(countItens));
                            
                            //Produto
                            Prod prod = new Prod();
                            prod.setCProd(JavaUtil.doValida(edProduto.getCD_Fornecedor()) ? edProduto.getCD_Fornecedor() : edProduto.getCD_Produto());
                            prod.setCEAN("SEM GTIN");
                            prod.setXProd(edProduto.getNM_Produto());
                            prod.setNCM(ManipulaString.limpaCampo(edProduto.getCD_Fiscal()));
                            prod.setCEST(null);
                            prod.setIndEscala(null);
                            Natureza_OperacaoED edCFOPDif = new Natureza_OperacaoBD(executasql).getByRecord(new Natureza_OperacaoED(new Integer((int)edItem.getOid_natureza_operacao())));
                            prod.setCFOP(edCFOPDif.getCd_Natureza_Operacao());
                            prod.setUCom(edProduto.getCD_Unidade_Produto());
                            prod.setQCom(""+dec.format(edItem.getNR_QT_Atendido()).replace(",", "."));
                            prod.setVUnCom(""+dec.format(edItem.getVL_Unitario()).replace(",", "."));
                            prod.setVProd(""+dec.format(edItem.getVL_Item()).replace(",", "."));
                            prod.setCEANTrib("SEM GTIN");
                            prod.setUTrib(edProduto.getCD_Unidade_Produto());
                            prod.setQTrib(""+dec.format(edItem.getNR_QT_Atendido()).replace(",", "."));
                            prod.setVUnTrib(prod.getVUnCom());
                            if(edItem.getVL_Desconto() > 0)
                            	prod.setVDesc(""+dec.format(edItem.getVL_Desconto()).replace(",", "."));
                            prod.setIndTot("1");
                            det.setProd(prod);
                        	
                          //Impostos
                            Imposto imposto = new Imposto();
                            ICMS icms = new ICMS();
                            if("000".equals(edProduto.getCD_Situacao_Tributaria())){
                            	ICMS00 icms00 = new ICMS00();
                            	icms00.setOrig("0");
                            	icms00.setCST("00");
                            	icms00.setModBC("3");
                            	icms00.setVBC(""+dec.format(edItem.getVL_Base_Calculo_ICMS()).replace(",", "."));
                            	icms00.setPICMS(""+dec.format(edItem.getPE_Aliquota_ICMS()).replace(",", "."));
                            	icms00.setVICMS(""+dec.format(edItem.getVL_ICMS_Aprov()).replace(",", "."));
                            	icms.setICMS00(icms00);
                            } else if("090".equals(edProduto.getCD_Situacao_Tributaria())) {
                            	ICMS90 icms90 = new ICMS90();
                            	icms90.setOrig("0");
                            	icms90.setCST("90");
                            	icms90.setModBC("3");
                            	icms90.setVBC("0");
                            	icms90.setPICMS(""+dec.format(edItem.getPE_Aliquota_ICMS()).replace(",", "."));
                            	icms90.setVICMS(""+dec.format(edItem.getVL_ICMS_Aprov()).replace(",", "."));
                            	icms90.setPRedBC("0.00");//percentual da redu��o da BC
                            	icms.setICMS90(icms90);
                            	
                            	prod.setVProd(""+dec.format(edItem.getVL_ICMS_Aprov()).replace(",", "."));
//                				 notafiscal.setVProd(ed.getVl_icms());//valor total dos produtos e servi�os
                            } else if("040".equals(edProduto.getCD_Situacao_Tributaria())) {
                            	ICMS40 icms40 = new ICMS40();
                            	icms40.setOrig("0");
                            	icms40.setCST("40");
                            	icms.setICMS40(icms40);
                            	
                            } else {
                            	ICMS10 icms10 = new ICMS10();
                            	icms10.setOrig("0");
                            	icms10.setCST("10");
//                            	icms10.setModBC("3");
//                            	icms10.setVBC(""+edItem.getVL_Base_Calculo_ICMS());
//                            	icms10.setPICMS(""+edItem.getPE_Aliquota_ICMS());
//                            	icms10.setVICMS(""+edItem.getVL_ICMS_Aprov());
                            	icms10.setModBCST("4");
//                            	icms10.setVBCST(""+edItem.getVL_Base_Calculo_ICMS());
                            	icms10.setPICMSST(""+dec.format(edItem.getPE_Aliquota_ICMS()).replace(",", "."));
//                            	icms10.setVICMSST((""+edItem.getVL_ICMS_Aprov());
                            	icms10.setPRedBCST("0.00");
                            	icms.setICMS10(icms10);
                				
                            }
                          
                            PIS pis = new PIS();
                            PIS.PISOutr pisOutro = new PIS.PISOutr();
//                            PISAliq pisAliq = new PISAliq();
                            pisOutro.setCST("99");
                            pisOutro.setVBC("0.00");
                            pisOutro.setPPIS("0.00");
                            pisOutro.setVPIS("0.00");
                            pis.setPISOutr(pisOutro);

                            COFINS cofins = new COFINS();
//                            COFINSAliq cofinsAliq = new COFINSAliq();
                            COFINS.COFINSOutr cOutro = new COFINS.COFINSOutr();
                            cOutro.setCST("99");
                            cOutro.setVBC("0.00");
                            cOutro.setPCOFINS("0.00");
                            cOutro.setVCOFINS("0.00");
                            cofins.setCOFINSOutr(cOutro);

                            JAXBElement<ICMS> icmsElement = new JAXBElement<ICMS>(new QName("ICMS"), ICMS.class, icms);
                            imposto.getContent().add(icmsElement);

                            JAXBElement<PIS> pisElement = new JAXBElement<PIS>(new QName("PIS"), PIS.class, pis);
                            imposto.getContent().add(pisElement);

                            JAXBElement<COFINS> cofinsElement = new JAXBElement<COFINS>(new QName("COFINS"), COFINS.class, cofins);
                            imposto.getContent().add(cofinsElement);

                            det.setImposto(imposto);
                            infNFe.getDet().add(det);

                            i++;
                        }

                    } else {
                    	canList = false;
                    }
                    
                 // TOTAIS
                    Total total = new Total();

                    ICMSTot icmstot = new ICMSTot();
                    icmstot.setVBC(""+dec.format(ed.getVL_Base_Calculo_ICMS()).replace(",", "."));
                    icmstot.setVICMS(""+dec.format(ed.getVl_icms()).replace(",", "."));
//                    if(ed.getVL_Base_Calculo_ICMS_Subst() > 0)
                    	icmstot.setVBCST(""+dec.format(ed.getVL_Base_Calculo_ICMS_Subst()).replace(",", "."));
//                    if(ed.getVL_ICMS_Subst() > 0) {
                    	icmstot.setVST(""+dec.format(ed.getVL_ICMS_Subst()).replace(",", "."));
//                    }
                    	
                    icmstot.setVICMSDeson("0.00");
                    icmstot.setVProd(""+dec.format(ed.getVl_nota_fiscal()).replace(",", "."));
                    
                    icmstot.setVFCPUFDest("0.00"); 
                    icmstot.setVICMSUFDest("0.00");
                    icmstot.setVICMSUFRemet("0.00");
                    icmstot.setVFCP("0.00");
                    
                    icmstot.setVFCPST("0.00");
                    icmstot.setVFCPSTRet("0.00");
                    
                    icmstot.setVFrete("0");
                    icmstot.setVSeg("0");
                    icmstot.setVDesc(""+dec.format(ed.getVL_Desconto_Itens()).replace(",", "."));
                    icmstot.setVII("0");
                    icmstot.setVIPI(""+dec.format(ed.getVl_ipi()).replace(",", "."));
                    icmstot.setVIPIDevol("0");
                    icmstot.setVPIS("0");
                    icmstot.setVCOFINS("0");
                    icmstot.setVOutro("0");
                    icmstot.setVNF(""+dec.format(ed.getVl_liquido_nota_fiscal()).replace(",", "."));
                    total.setICMSTot(icmstot);
                    infNFe.setTotal(total);

//        			Transporte
                    Transp transp = new Transp();
                    transp.setModFrete("0");//Modalidade do frete
                    if (ed.getDM_Frete().equals("1")){
                    	transp.setModFrete("0");
                    } else if (ed.getDM_Frete().equals("2")){
                    	transp.setModFrete("1");
                    } else if (ed.getDM_Frete().equals("3")){
                    	transp.setModFrete("2");
                    } else if (ed.getDM_Frete().equals("9")){
                    	transp.setModFrete("9");
                    }

                    Transporta trasnporta = new Transporta();
                    trasnporta.setXNome(edTransportador.getNM_Razao_Social());//Raz�o Social/Nome
                    trasnporta.setIE(edTransportador.getNM_Inscricao_Estadual());//Inscri��o Estadual
                    trasnporta.setXEnder(edTransportador.getNM_Endereco());//Endere�o Completo
                    trasnporta.setUF(TUf.valueOf(edTransportador.getCD_Estado()));//sigla UF
                    trasnporta.setXMun(JavaUtil.getValueDef(edTransportador.getNM_Cidade(),"").trim());//Nome do munic�pio
                    if(edTransportador.getNR_CNPJ_CPF().length()<14){
                    	trasnporta.setCPF((edTransportador.getNR_CNPJ_CPF()));//CNPJ ou CPF
                    } else 
                    	trasnporta.setCNPJ((edTransportador.getNR_CNPJ_CPF()));//CNPJ ou CPF
                    transp.setTransporta(trasnporta);
                    
                    br.inf.portalfiscal.nfe.schema_4.enviNFe.TVeiculo veicTransp = new TVeiculo();
                    veicTransp.setPlaca(getTableStringValue("NR_Placa", "Veiculos","oid_Veiculo = '"+ed.getOid_Veiculo()+"'"));//Placa do ve�culo
                    
                    if(JavaUtil.doValida(veicTransp.getPlaca())){
                    	veicTransp.setUF(TUf.valueOf("RS"));
                    	transp.setVeicTransp(veicTransp);
                    }
                    

//                    transp.setRntc("");//ANTT
//                    transp.setRPlaca("");//Placa do reboque
//                    transp.setRUf("");//UF do reboque
//                    transp.setRRntc("");//ANTT do reboque
//                    transp.setQVol((double)ed.getNr_volumes_nota());//Quantidade de volumes transportados
//                    transp.setEsp("EMB.GERAL");//especie dos volumes transportados
//                    transp.setMarca("MARCA");//marca dos volumes transportados
//                    transp.setNVol("");//numera��o dos volumes transportados
//                    transp.setPesoL(ed.getNR_Peso());//peso liquido em kg
//                    transp.setPesoB(ed.getNR_Peso());//peso bruto em kg
//                    transp.setNLacre("");

                    infNFe.setTransp(transp);

        			//Cobranca
        			String txt_dpl = "";
        			if (doValida(ed.getOid_nota_fiscal())){
        				
        				Pag pag = new Pag();
        				InfNFe.Cobr cob = new InfNFe.Cobr();
        				try {
        					sqlBusca =" SELECT Duplicatas.NR_Duplicata,Duplicatas.DT_Vencimento,Duplicatas.vl_Duplicata " +
        							  " FROM Origens_Duplicatas, Notas_Fiscais, Unidades, " +
        							  " Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Duplicatas ";
        					sqlBusca += " WHERE Unidades.oid_Unidade = Notas_Fiscais.oid_Unidade ";
        					sqlBusca += " AND Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
        					sqlBusca += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
        					sqlBusca += " AND Origens_Duplicatas.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";
        					sqlBusca += " AND Origens_Duplicatas.OID_Duplicata = Duplicatas.OID_Duplicata ";
        					if (String.valueOf (ed.getOid_nota_fiscal()) != null && !String.valueOf (ed.getOid_nota_fiscal()).equals ("") && !String.valueOf (ed.getOid_nota_fiscal()).equals ("null")) {
        						sqlBusca += " and Origens_Duplicatas.Oid_Nota_Fiscal = '" + ed.getOid_nota_fiscal() + "'";
        					}
        					resLocal = sql.executarConsulta (sqlBusca);
        					if(resLocal.next ()) {
        						Pag.DetPag detPag = new Pag.DetPag();
                	            detPag.setTPag("90");
                	            detPag.setVPag(""+dec.format(resLocal.getDouble(3)).replace(",", "."));
                	            pag.getDetPag().add(detPag);
                	            InfNFe.Cobr.Dup dup = new InfNFe.Cobr.Dup();
        						dup.setNDup(resLocal.getString(1));
        						dup.setDVenc(resLocal.getString(2));
        						dup.setVDup(resLocal.getString(3));
        						cob.getDup().add(dup);
        						txt_dpl = "- Fatura: " + resLocal.getString(1) + " - Venc: " + FormataDataBean.getFormatDate(resLocal.getString(2));
        					} else {
        						Pag.DetPag detPag = new Pag.DetPag();
                	            detPag.setTPag("90");
                	            detPag.setVPag("0.00"); // icmstot.getVNF());
                	            pag.getDetPag().add(detPag);
                	            InfNFe.Cobr.Dup dup = new InfNFe.Cobr.Dup();
        						dup.setNDup(ide.getNNF());
        						dup.setDVenc(Data.getDataYMD());
        						dup.setVDup(icmstot.getVNF());
        						cob.getDup().add(dup);
        						
        					}
        					InfNFe.Cobr.Fat ft = new InfNFe.Cobr.Fat();
    						ft.setNFat(ide.getNNF());
//    						ft.setVDesc("0.00");
    						ft.setVOrig(icmstot.getVNF());
    						ft.setVLiq(icmstot.getVNF());
    						cob.setFat(ft);
        				}
        				finally {
        					util.closeResultset (resLocal);
        				}
//        				infNFe.setCobr(cob);
        				infNFe.setPag(pag);
        			}

//        			INF ADICIONAL
        			String inf = null;
        			if (msgFiscal.size() > 0)
                    {
                        for (int m=0; m < msgFiscal.size(); m++)
                        {
                            if ((String)msgFiscal.get(m) != null){
                            	inf += (String)msgFiscal.get(m);
                            }
                        }
                    }
        			InfAdic infAdic = new InfAdic();
                    infAdic.setInfAdFisco(inf);
                    
        			inf = "";
        			if("D".equals(ed.getDm_tipo_nota_fiscal())){
                    	if (doValida(ed.getNm_complemento()))
                        {
                    		inf=ed.getNm_complemento() ;
                        }
        			}
    				if (doValida(ed.getDm_observacao()))
                    {
                		inf+=ed.getDm_observacao();
                    }

//        			inf += " ****** SR. CLIENTE: PARA ACESSAR OS ARQUIVOS ELETRONICOS (XML) DE SUAS NF-E ACESSE http://201.66.254.114:8180/DisponibilizadorNFe/login.jsp,";
//        			inf += " utilizando o login CLIENTE com a senha DEST e informe corretamente APENAS OS NUMEROS de seu CNPJ/CPF. ******  ";
//    				String infTrib = "\n\nLei da Transparencia - O valor aproximado de tributos incidentes sobre o preco deste Documento e de R$ "+new DecimalFormat("#,##0.00").format(notafiscal.getvTotTrib());
//        			notafiscal.setInfCpl(ManipulaString.corrigeString(ManipulaString.Enter2BR(inf + txt_dpl))+infTrib);
        			infAdic.setInfCpl(ManipulaString.corrigeString(ManipulaString.Enter2BR(inf + txt_dpl)));
        			//...
//        			infNFe.setInfAdic(infAdic);
            
        			nfeReturn.setInfNFe(infNFe);
            }
            
        } catch (Excecoes e) {
        	e.printStackTrace();
            throw e;
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "geraNFe()");
        }

        return nfeReturn;
    }

	public String updateRetornoLote(Nota_Fiscal_EletronicaED ed, NfeLote retorno) throws SQLException, Excecoes {
		try{
			String sqlUpdate;
			sqlUpdate = "UPDATE Notas_Fiscais SET " +
			            " nfe_recibo = '"+retorno.getNRec() + "' " +
			            " ,nfe_data_lote = '"+new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss").format(retorno.getData()) + "' " +
			    		" ,nfe_cstat_lote = '" + retorno.getCStat() + "' " +
			    		" ,nfe_motivo_lote = '" + retorno.getXMotivo() + "' " +
			            " WHERE oid_Nota_Fiscal = '"+ed.getOid_nota_fiscal()+"' ";
System.out.println(sqlUpdate);
			executasql.executarUpdate(sqlUpdate);
			return "OK";
		} catch(Exception e){
			return "ERRO: "+e.getMessage();
		}
	}
	public String updateRetornoNFE(Nota_Fiscal_EletronicaED ed, TRetEnviNFe retorno, TEnviNFe enviNFe) throws SQLException, Excecoes {
		try{
			String sqlUpdate;
			sqlUpdate = "UPDATE Notas_Fiscais SET " +
			            " nfe_chave_acesso = '" + retorno.getProtNFe().getInfProt().getChNFe() + "', " +
						" nfe_protocolo = '"+retorno.getProtNFe() + "' " +
			            " ,nfe_dt_hr_recebimento = '"+(retorno.getDhRecbto()) + "' " +
			    		" ,nfe_cstat = '" + retorno.getCStat() + "' " +
			    		" ,nfe_motivo = '" + retorno.getXMotivo() + "' " +
						" ,nfe_digestvalue = '" + retorno.getProtNFe().getInfProt().getDigVal() + "' " +
						" ,xml_autorizacao = '" + XmlUtil.criaNfeProc(enviNFe, retorno.getProtNFe()) + "' " +
			            " WHERE oid_Nota_Fiscal = '"+ed.getOid_nota_fiscal()+"' ";
System.out.println(sqlUpdate);
			executasql.executarUpdate(sqlUpdate);
			if(JavaUtil.doValida(retorno.getCStat()) && "100".equals(retorno.getCStat())){
				
				if ("S".equals(ed.edModelo.getDM_Gera_Fiscal()))
		        {
					boolean isSaida = ("D".equals(ed.getDm_tipo_nota_fiscal()) || "R".equals(ed.getDm_tipo_nota_fiscal()) || "S".equals(ed.getDm_tipo_nota_fiscal()));//*** Tipo de NOTA
	                if (isSaida || JavaUtil.doValida(ed.getDM_Tipo_Devolucao()))
	                {
	                	new Livro_FiscalBD(executasql).geraLivro_Fiscal_Saidas(new Livro_FiscalED(ed.getOid_nota_fiscal(), "NF"), "S");
	                }else{
	                	new Livro_FiscalBD(executasql).geraLivro_Fiscal_Entradas(new Livro_FiscalED(ed.getOid_nota_fiscal(), "NF"), "E");
	                }
		        }
				
				return "OK";
            } else {
            	return "NOK";
            }
		} catch(Exception e){
			return "ERRO: "+e.getMessage();
		}
	}
	public String updateRetornoCancelamento(Nota_Fiscal_EletronicaED ed, br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEvento.InfEvento ret) throws SQLException, Excecoes {
		try{
			String sqlUpdate;
			sqlUpdate = "UPDATE Notas_Fiscais SET " +
			            " nfe_dt_hr_recebimento = '"+(ret.getDhRegEvento()+"                   ").substring(0,20) + "' " +
			    		" ,nfe_cstat = '" + ret.getCStat() + "' " +
			    		" ,nfe_motivo = '" + ret.getXMotivo() + "' ";
			    		if(JavaUtil.doValida(ret.getNProt()))
			    			sqlUpdate += " ,nfe_protocolo = '"+ret.getNProt() + "' ";
    		sqlUpdate += " WHERE oid_Nota_Fiscal = '"+ed.getOid_nota_fiscal()+"' ";
System.out.println(sqlUpdate);
			executasql.executarUpdate(sqlUpdate);
			if(JavaUtil.doValida(ret.getCStat()) && "101".equals(ret.getCStat())){
				return "OK";
            } else {
            	return "NOK";
            }
		} catch(Exception e){
			return "ERRO: "+e.getMessage();
		}
	}

	public void cancelaNFE(Empresa empresa, Nota_Fiscal_EletronicaED ed, ConfiguracoesWebNfe config) throws Excecoes {

		NfeServicos servico = new NfeServicos();
		try{
			

			int oid_uni = new Integer (String.valueOf(ed.getOID_Unidade_Fiscal())).intValue();
            UnidadeBean unidade_Remetente = new UnidadeBean().getByOID_Unidade(empresa, oid_uni);
            PessoaED unidade = new PessoaBD(executasql).getByRecord(unidade_Remetente.getOID_Pessoa());
	        CidadeBean orig = CidadeBean.getByOID(empresa, (int)unidade.getOid_Cidade());
	        String cIBGE_UF = getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+orig.getOID_Estado());
System.out.println("Cid:"+orig.getOID()+"-"+orig.getNM_Cidade()+" | "+cIBGE_UF+" >"+XmlUtil.dataNfe());
			
			String chave = ed.getNfe_chave_acesso();
            String protocolo = ed.getNfe_protocolo();
            String cnpj = unidade.getNR_CNPJ_CPF();
            String motivo = "ERRO NA EMISSAO, DESISTENCIA DO CLIENTE.";

            String id = "ID" + ConstantesUtil.EVENTO.CANCELAR + chave + "01";

            TEnvEvento enviEvento = new TEnvEvento();
            enviEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
            enviEvento.setIdLote("1");

            TEvento eventoCancela = new TEvento();
            eventoCancela.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);

            TEvento.InfEvento infoEvento = new TEvento.InfEvento();
            infoEvento.setId(id);
            infoEvento.setChNFe(chave);
//            infoEvento.setCOrgao(cIBGE_UF);
            infoEvento.setCOrgao(String.valueOf(config.getEstado().getCodigoIbge()));
            infoEvento.setTpAmb(config.getAmbiente());
            infoEvento.setCNPJ(cnpj);

            infoEvento.setDhEvento(XmlUtil.dataNfe());
            infoEvento.setTpEvento(ConstantesUtil.EVENTO.CANCELAR);
            infoEvento.setNSeqEvento("1");
            infoEvento.setVerEvento(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);

            TEvento.InfEvento.DetEvento detEvento = new TEvento.InfEvento.DetEvento();
            detEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
            detEvento.setDescEvento("Cancelamento");
            detEvento.setNProt(protocolo);
            detEvento.setXJust(motivo);
            infoEvento.setDetEvento(detEvento);
            eventoCancela.setInfEvento(infoEvento);
            enviEvento.getEvento().add(eventoCancela);
            

            TRetEnvEvento retorno = Nfe.cancelarNfe(enviEvento, false, ConstantesUtil.NFE);
            
            if (retorno != null) {
            	if (!StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo().equals(retorno.getCStat())) {
                    throw new NfeException("Status:" + retorno.getCStat() + " - Motivo:" + retorno.getXMotivo());
                }

                if (!StatusEnum.EVENTO_VINCULADO.getCodigo().equals(retorno.getRetEvento().get(0).getInfEvento().getCStat())) {
                    throw new NfeException("Status:" + retorno.getRetEvento().get(0).getInfEvento().getCStat() + " - Motivo:" + retorno.getRetEvento().get(0).getInfEvento().getXMotivo());
                }

                br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEvento retEnv = retorno.getRetEvento().get(0);
                
                System.out.println("Status:" + retEnv.getInfEvento().getCStat());
                System.out.println("Motivo:" + retEnv.getInfEvento().getXMotivo());
                System.out.println("Data:" + retEnv.getInfEvento().getDhRegEvento());
                
	            System.out.println("Retorno cancelamento 4.00 ok!!!\n"
	                    + " Protocolo: " + retEnv.getInfEvento().getNProt() + "\n"
	                    + " Data: " + retEnv.getInfEvento().getDhRegEvento() + "\n"
	                    + " cstat: " + retEnv.getInfEvento().getCStat() + " \n"
	                    + " Motivo: " + retEnv.getInfEvento().getXMotivo());
	            String OK = this.updateRetornoCancelamento(ed, retEnv.getInfEvento());

	        } else {
	            System.out.println("Erro ao buscar cancelamento 4.00 ... consulte hashMap");
	            JavaUtil.showErrors(servico.getErros());
	        }

            

			
	        
		} catch (Exception e) {
        	e.printStackTrace();
            throw new Excecoes(e.getMessage()+ "\n" +JavaUtil.getErrors(servico.getErros()), this.getClass().getName(), "geraNFe()");
        }

	}

    public NfeNotaFiscal getNFeDANFE(Nota_Fiscal_EletronicaED ed) throws Excecoes {

    	NfeNotaFiscal nfReturn = new NfeNotaFiscal();
        try {
        	 // System.out.println("geraNotaFiscal_to_NFe 1");
//        	Origem_DuplicataED DupliED = new Origem_DuplicataED();


            //*** Vari�veis
            ResultSet resLocal = null;
            String sqlBusca = null;
        	 // System.out.println("printNotaFiscalSaida 2");

            int i = 0,
                countItens = 0,
                nrNotas = 0,
                nrNotaAtual = 0;

            boolean canList = true,
                    isSaida = false,
                    isIsentoSubst = false;
            long oidPedido = 0;
            double baseCalculoICMS = 0
                  ,valorICMS = 0
                  ,peAliquota = 0
                  ,sitTributaria = 0
                  ,subTotalItens = 0
                  ,totalItens = 0
                  ,totalPesoLiquido = 0
                  ,totalPIS = 0
                  ,totalCOFINS = 0;

            ArrayList infNFes = new ArrayList();

            isSaida = ("D".equals(ed.getDm_tipo_nota_fiscal()) || "R".equals(ed.getDm_tipo_nota_fiscal()) || "S".equals(ed.getDm_tipo_nota_fiscal()));//*** Tipo de NOTA

            ArrayList lista = new Item_Nota_Fiscal_TransacoesBD(executasql).lista(new Item_Nota_Fiscal_TransacoesED(ed.getOid_nota_fiscal()));
            Natureza_OperacaoED edCFOP = new Natureza_OperacaoBD(executasql).getByRecord(new Natureza_OperacaoED(new Integer((int)ed.getOid_natureza_operacao())));

            ClienteBean edCliente = ClienteBean.getByOID_Cliente(null, ed.getOid_pessoa_destinatario());
//            ClienteBean edCliente = ClienteBean.getByOID_Cliente_Nota(ed.getOid_pessoa_destinatario());

            PessoaED edPessoa = new PessoaBD(executasql).getByRecord(new PessoaED(ed.getOid_pessoa_destinatario()));

            int oid_uni = new Integer (String.valueOf(ed.getOID_Unidade_Fiscal())).intValue();

            UnidadeBean unidade_Remetente = new UnidadeBean().getByOID_Unidade(null, oid_uni);

//            PessoaED edTransportador = new PessoaBD(executasql).getByRecord(new PessoaED(unidade_Remetente.getOID_Pessoa()));
            PessoaED edTransportador = new PessoaED();
            if (doValida(ed.getOid_pessoa_transportador())){
                edTransportador = new PessoaBD(executasql).getByRecord(new PessoaED(ed.getOid_pessoa_transportador()));
            }else{
                edTransportador = new PessoaBD(executasql).getByRecord(new PessoaED(unidade_Remetente.getOID_Pessoa()));
            }

            	// System.out.println("printNotaFiscalSaida 10");
                PessoaED unidade = new PessoaBD(executasql).getByRecord(unidade_Remetente.getOID_Pessoa());
//              ******** IN�CIO NOTA FISCAL *************
                nrNotaAtual++;
//              Nota Fiscal
                NfeNotaFiscal notafiscal = new NfeNotaFiscal();

                //Ambiente: 1-prod, 2-homologa
//                notafiscal.setTpAmbiente(1);
                if(Parametro_FixoED.getInstancia().getNM_Base().equals("HOMOLOGA"))
                	notafiscal.setTpAmbiente(2);
                else
                	notafiscal.setTpAmbiente(1);

                notafiscal.setNatOp(edCFOP.getNm_Natureza_Operacao());//Descri��o da natureza da opera��o
                notafiscal.setIndPag(0);//indicador da forma de pagamento
                if(ed.edModelo.getOid_Modelo_Nota_Fiscal()==8){
                	notafiscal.setIndPag(1);
                }
                notafiscal.setModF("55");//c�digo do modelo do documento fiscal
                String ser = doValida(ed.getNm_serie())&&"NF1".equals(ed.getNm_serie())?"1" :
                	doValida(ed.getNm_serie())&&"NF7".equals(ed.getNm_serie())?"2" :
                		doValida(ed.getNm_serie())&&"NF6".equals(ed.getNm_serie())?"2" :
                			doValida(ed.getNm_serie())&&"NF12".equals(ed.getNm_serie())?"2" :
                				doValida(ed.getNm_serie())&&"NF02".equals(ed.getNm_serie())?"2" :
                					doValida(ed.getNm_serie())&&"NF08".equals(ed.getNm_serie())?"2" :
                						doValida(ed.getNm_serie())&&"NF10".equals(ed.getNm_serie())?"2" :
                				ed.getNm_serie();
                notafiscal.setSerie(new Integer(ser).intValue());//s�rie do documento fiscal
                notafiscal.setNNF((int)ed.getNr_nota_fiscal());//n�mero do documento fiscal
                notafiscal.setDEmi(Data.strToDate(ed.getDt_emissao()));//data de emiss�o do documento fiscal
                notafiscal.setDSaiEnt(Data.strToDate(ed.getDt_entrada()));//data de sa�da ou entrada da mercadoria
                notafiscal.setTpNf(isSaida ? 1 : 0);//tipo de documento fiscal
                notafiscal.setTpEmissao(1);//forma de emiss�o do nfe
                notafiscal.setFinNfe(1);//Finalidade de emiss�o da NF-E

                if(ed.getOid_modelo_nota_fiscal() == 13 || ed.getOid_modelo_nota_fiscal() == 15){
                	notafiscal.setFinNfe(3);//Finalidade de emiss�o da NF-E >> AJUSTE
                }

//              NOTAS DE DEVOLUCAO
                if("D".equals(ed.getDm_tipo_nota_fiscal()) || edCFOP.getDM_Tipo_Operacao().equalsIgnoreCase("S")){
                	notafiscal.setFinNfe(4);//Finalidade de emiss�o da NF-E >> DEVOLUCAO
                	String NF_Ref = getTableStringValue("nm_ch_referenciada",	"Notas_Fiscais", "oid_nota_fiscal = '"	+ ed.getOid_nota_fiscal() + "'");

                	NfeReferenciada nfref = new NfeReferenciada();
                	ArrayList nfsref = new ArrayList();
                	nfref.setRefNfe(NF_Ref);
                	nfsref.add(nfref);
                	notafiscal.setNfeReferenciadaCollection(nfsref);
                }

    			CidadeBean origem = CidadeBean.getByOID(null, (int)unidade.getOid_Cidade());

    			String cIBGE_UF = getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+origem.getOID_Estado());
//    			System.out.println("estado ORIG:"+cIBGE_UF);

//    			Emitente
    			NfeEmitente emitente = new NfeEmitente();
    	        emitente.setXNome(unidade.getNM_Razao_Social());//Raz�o Social/Nome
    	        emitente.setXFant(unidade.getNM_Fantasia());//Nome fantasia
    	        emitente.setIe(unidade.getNM_Inscricao_Estadual());//Inscri��o Estadual
    	        emitente.setIest(null);//IE do Substituto Tribut�rio
    	        emitente.setIm(null);//inscri��o municipal
    	        emitente.setCnae(null);//CMAE fiscal
    	        emitente.setCnpj(JavaUtil.formataCNPJ_CPF(unidade.getNR_CNPJ_CPF()));//CNPJ emitente
    	        String endereco = unidade.getNM_Endereco();
    			String numero = endereco.substring (endereco.lastIndexOf (",") + 1);
    			endereco = endereco.substring (0 , endereco.lastIndexOf (","));
    	        emitente.setXLgr(endereco);//Logradouro
    	        emitente.setNro(ManipulaString.limpaCampo(numero));//N�mero do endere�o
    	        emitente.setXCpl("");//complemento do endere�o
    	        emitente.setXBairro(unidade.getNM_Bairro());//Bairro
    	        emitente.setCMun(Integer.parseInt(ManipulaString.limpaCampo((doValida(cIBGE_UF)?cIBGE_UF:"") + origem.getNM_Codigo_IBGE())));//C�digo do munic�pio no IBGE
    	        emitente.setXMun(unidade.getNM_Cidade());
    	        CidadeBean orig = CidadeBean.getByOID(null, (int)unidade.getOid_Cidade());
    			cIBGE_UF = getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+orig.getOID_Estado());
    	        emitente.setCUf(Integer.parseInt(cIBGE_UF));//C�digo do Estado
    	        emitente.setUf(unidade.getCD_Estado());//sigla UF
    	        emitente.setCep(Integer.parseInt(unidade.getNR_CEP()));//C�digo do CEP
    	        emitente.setCPais(1058);//C�digo do Pa�s
    	        emitente.setXPais("BRASIL");//Nome do Pa�s
    	        emitente.setFone(ManipulaString.limpaCampo(unidade.getNR_Telefone()));//Telefone
    	        emitente.setEmail("DERLI@ANDREZASA.COM.BR");//E-mail do emitente
    	        emitente.setSite("WWW.WOLP.COM.BR");//Site do NfeEmitente
    	        emitente.setCrt(3);//C�digo de Regime Tribut�rio
    	        notafiscal.setEmitente(emitente);

//    	        notafiscal.setCnpj(emitente.getCnpj());//CNPJ emitente
//    	        EmpresaDb eDb = new EmpresaDb();
//    	        Empresa e = eDb.getEmpresa(emitente.getCnpj());
//    	        notafiscal.setEmpresa(e);

//    			Destinatario
    	        CidadeBean destino = CidadeBean.getByOID(null, (int)edPessoa.getOid_Cidade());
    			cIBGE_UF = getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+destino.getOID_Estado());
    	        if(!JavaUtil.doValida(edCliente.getOid_Pessoa())){
    				throw new Excecoes("Problemas com o destinat�rio: "+edPessoa.getNM_Razao_Social()+".\nDeve ser cadastrado como cliente...");
    			}
    	        NfeDestinatario destinatario = new NfeDestinatario();
    	        destinatario.setXNome(edCliente.getNM_Razao_Social());//Raz�o Social/Nome
    	        destinatario.setCnpj(JavaUtil.formataCNPJ_CPF(edCliente.getNR_CNPJ_CPF()));//CNPJ destinat�rio
    	        destinatario.setIe(JavaUtil.getValueDif(edPessoa.getNM_Inscricao_Estadual(),"ISENTO"));//Inscri��o Estadual
    	        destinatario.setISuf(null);//Inscri��o na SUFRAMA
    	        if(edCliente.getNR_CNPJ_CPF().length()<14)
    	        	destinatario.setTipoPessoa("F");//
    	        else
    	        	destinatario.setTipoPessoa("J");//
    	        endereco = edPessoa.getNM_Endereco();
    			String cpl = "";
    			if(endereco.lastIndexOf(',')>0){
    				numero = endereco.substring (endereco.lastIndexOf (",") + 1);
        			endereco = endereco.substring (0 , endereco.lastIndexOf (","));
        			if(numero.lastIndexOf(' ')>0){
        				cpl = numero.substring (numero.lastIndexOf (" ") + 1);
        			}
    			} else {
    				throw new Excecoes("problemas com endere�o do cliente: "+edCliente.getNM_Razao_Social()+".\nDeve ter o n�mero separado por v�rgula...");
    			}
    	        destinatario.setXLgr(endereco);//Logradouro
    	        destinatario.setNro(ManipulaString.limpaCampo(numero));//N�mero do endere�o
    	        destinatario.setXCpl(cpl);//complemento do endere�o
    	        if(!JavaUtil.doValida(edPessoa.getNM_Bairro())){
    				throw new Excecoes("Problemas com o destinat�rio: "+edPessoa.getNM_Razao_Social()+".\nO campo BAIRRO � obrigat�rio...");
    			}
    	        destinatario.setXBairro(edPessoa.getNM_Bairro());//Bairro
    	        destinatario.setCMun(Integer.parseInt(ManipulaString.limpaCampo((doValida(cIBGE_UF)?cIBGE_UF:"") + destino.getNM_Codigo_IBGE())));//C�digo do munic�pio no IBGE
    	        destinatario.setXMun(edPessoa.getNM_Cidade());//Nome do munic�pio
    	        destinatario.setCUf(Integer.parseInt(cIBGE_UF));//C�digo do Estado
    	        destinatario.setUf(edPessoa.getCD_Estado());//sigla UF
    	        destinatario.setCep(Integer.parseInt(ManipulaString.limpaCampo(edPessoa.getNR_CEP())));//C�digo do CEP
    	        destinatario.setCPais(1058);//C�digo do Pa�s
    	        destinatario.setXPais("BRASIL");//Nome do Pa�s
    	        destinatario.setFone(ManipulaString.limpaCampo(edPessoa.getNR_Telefone()));//Telefone
    	        destinatario.setEmail(null);//E-mail do Destinat�rio
    	        notafiscal.setDestinatario(destinatario);
    			//...

    	        if(origem.getOID_Estado() != destino.getOID_Estado()){
    	        	notafiscal.setIdDest(2);//fora do estado... //10-4-14 - VER 3.10
    	        } else {
    	        	notafiscal.setIdDest(1);//dentro do estado... //10-4-14 - VER 3.10
    	        }

                /** ---- M�ximo de Itens ---- **/
                peAliquota = 0;
                int nrItens = lista.size();
                boolean isCFOPNormal = true;
                for (int n=0; n < lista.size(); n++)
                {
                    Item_Nota_Fiscal_TransacoesED edItem = (Item_Nota_Fiscal_TransacoesED) lista.get(n);
                    if (peAliquota != edItem.getPE_Aliquota_ICMS())
                        nrItens++;
                    //*** Descri��o CFOP Diferenciado
                	// System.out.println("printNotaFiscalSaida 10 B");
                    if (isCFOPNormal  && edCFOP.getOid_Natureza_Operacao()!=null && edItem.getOid_natureza_operacao() != edCFOP.getOid_Natureza_Operacao().longValue())
                    {
                        nrItens += 2;
                        isCFOPNormal = false;
                    }
                    peAliquota = edItem.getPE_Aliquota_ICMS();
                }
            	// System.out.println("printNotaFiscalSaida 11");

                //*** FISCAL

                notafiscal.setVBc(ed.getVL_Base_Calculo_ICMS());//Valor total do ICMS
                notafiscal.setVIcms(ed.getVl_icms());//Valor total do ICMS
                notafiscal.setVBcst(ed.getVL_Base_Calculo_ICMS_Subst());//base de calculo do ICMS st
                notafiscal.setVSt(ed.getVL_ICMS_Subst());//valor total do ICMS st
                notafiscal.setVProd(ed.getVl_nota_fiscal());//valor total dos produtos e servi�os
                notafiscal.setVFrete(0);//valor total do frete
                notafiscal.setVSeg(0);//valor total do seguro
                notafiscal.setVDesc(ed.getVL_Desconto_Itens());//valor total do desconto
                notafiscal.setVIi(0);//valor total do II
                notafiscal.setVIpi(0d);//valor total do ipi
                notafiscal.setVPis(0d);//valor do pis
                notafiscal.setVCofins(0d);//valor do confins
                notafiscal.setVOutro(0d);//outras despesas acess�rias
                notafiscal.setVNf(ed.getVl_liquido_nota_fiscal());//valor total da nf-e

                notafiscal.setvTotTrib(notafiscal.getVIcms()+notafiscal.getVSt()+notafiscal.getVIpi()+notafiscal.getVCofins()+notafiscal.getVPis());

                ArrayList cdsFiscais = new ArrayList();
                ArrayList msgFiscal = new ArrayList();

                nrNotaAtual = 0;
                baseCalculoICMS = 0;
                valorICMS = 0;
                peAliquota = 0;
                sitTributaria = 0;
                subTotalItens = 0;
                totalItens = 0;
                totalPesoLiquido = 0;
                totalPIS = 0;
                totalCOFINS = 0;
                i = 0;
                oidPedido = 0;
                canList = true;
                isCFOPNormal = true;
//                while (canList)
//                {
                	// System.out.println("printNotaFiscalSaida 20");

                    countItens = 0;

                    //DADOS DO PRODUTO ** LINHA 8 **
                    ArrayList<NfeNotaItem> listaItens = new ArrayList<NfeNotaItem>();
        			ArrayList produtos = new ArrayList();
                    if (lista.size() > 0)
                    {
                    	long volumes = 0;
                    	while (i < lista.size())
                        {
                    		countItens++;
                        	Item_Nota_Fiscal_TransacoesED edItem = (Item_Nota_Fiscal_TransacoesED) lista.get(i);
                        	ProdutoED edProduto = new ProdutoBD(executasql).getByRecord(new ProdutoED(edItem.getOID_Produto()));
//                        	Produtos
                            NfeNotaItem notaitem = new NfeNotaItem();
                            notaitem.setNota(notafiscal);
                            notaitem.setCProd(JavaUtil.doValida(edProduto.getCD_Fornecedor()) ? edProduto.getCD_Fornecedor() : edProduto.getCD_Produto());//C�digo do produto ou servi�o
                            notaitem.setCEan("");//c�digo de barras
                            notaitem.setXProd(edProduto.getNM_Produto());//descri��o do produto ou servi�o
                            notaitem.setNcm(ManipulaString.limpaCampo(edProduto.getCD_Fiscal()));//c�digo NCM
                            notaitem.setExtipi("");//EX_TIPI
                            notaitem.setGenero(null);//G�nero do produto ou servi�o
                            Natureza_OperacaoED edCFOPDif = new Natureza_OperacaoBD(executasql).getByRecord(new Natureza_OperacaoED(new Integer((int)edItem.getOid_natureza_operacao())));
                            notaitem.setCfop(new Integer(edCFOPDif.getCd_Natureza_Operacao()).intValue());//c�digo fiscal de opera��es e presta��es

                            notaitem.setUCom(edProduto.getCD_Unidade_Produto());
                            notaitem.setQCom(edItem.getNR_QT_Atendido());//quantidade comercial
                            notaitem.setVUnCom(edItem.getVL_Unitario());//valor unit�rio de comercializa��o
                            notaitem.setXvProd(edItem.getVL_Item());//valor total bruto
                            notaitem.setCEanTrib("");//c�digo de barras
                            notaitem.setUTrib(edProduto.getCD_Unidade_Produto());//unidade tribut�vel
                            notaitem.setQTrib(edItem.getNR_QT_Atendido());//quantidade tribut�vel
                            notaitem.setVUnTrib(edItem.getVL_Unitario());//valor unit�rio de tributa��o

                            notaitem.setVFrete(0d);//valor total do frete
                            notaitem.setVSeg(0d);//valor total do seguro
                            notaitem.setVDesc(0d);//valor do desconto
                            notaitem.setVOutro(0d);//Outras despesas acess�rias
                            notaitem.setIndTot(1);//Indica se valor do Item (vProd) entra no valor total da NF-e
                            notaitem.setOrig(0);//Origem da mercadoria

                            Situacao_TributariaED edSituacao = new Situacao_TributariaBD(executasql).getByOidSituacao_Tributaria(edProduto.getOid_Situacao_Tributaria());
                            notaitem.setCst(Integer.parseInt(edProduto.getCD_Situacao_Tributaria()));//tributa��o ICMS

                            notaitem.setModBc(3);//modalidade de determina��o da BC do icms
//                			icms00.setModBC(edSituacao.getDM_Modalidade_BC());
                			 notaitem.setVBc(edItem.getVL_Base_Calculo_ICMS());
                			 notaitem.setPIcms(edItem.getPE_Aliquota_ICMS());
                			 notaitem.setVIcms(edItem.getVL_ICMS_Aprov());

                			 if("000".equals(edProduto.getCD_Situacao_Tributaria())){
                				 notaitem.setPRedBc(0d);//percentual da redu��o da BC

                			 } else if("090".equals(edProduto.getCD_Situacao_Tributaria())) {
                				 notaitem.setVIcms(0D);
//                				 notaitem.setVBc(edItem.getVL_ICMS_Aprov());
                				 notaitem.setXvProd(edItem.getVL_ICMS_Aprov());//valor total bruto
                				 notaitem.setPRedBc(0.01);//percentual da redu��o da BC
                				 notafiscal.setVProd(ed.getVl_icms());//valor total dos produtos e servi�os
                			 } else {

                			 //CST 010 ou TODOS menos 000...
//                			 if("010".equals(edProduto.getCD_Situacao_Tributaria())){
//                				icms10.setOrig(edSituacao.getDM_Procedencia());
                				notaitem.setModBcSt(4);
//                				notaitem.setVBcSt(edItem.getVL_Base_Calculo_ICMS());
//                				notaitem.setVBcSt(edItem.getVL_Base_Calculo_ICMS());
                				notaitem.setPlcmsSt(edItem.getPE_Aliquota_ICMS());
//                				notaitem.setVIcmsSt(edItem.getVL_ICMS_Subs());

                				//NOVIDADES de 01/10/09
//                				double pe_magem_valor_agregado = getTableDoubleValue("classificacoes_fiscais.pe_magem_valor_agregado","classificacoes_fiscais, Produtos","classificacoes_fiscais.oid_classificacao_fiscal = Produtos.oid_classificacao_fiscal AND Produtos.oid_Produto="+getSQLString(edItem.getOID_Produto()));
//                				notaitem.setPMvaSt(pe_magem_valor_agregado);
//                				double pe_reducao_base_calculo = getTableDoubleValue("classificacoes_fiscais.pe_reducao_base_calculo","classificacoes_fiscais, Produtos","classificacoes_fiscais.oid_classificacao_fiscal = Produtos.oid_classificacao_fiscal AND Produtos.oid_Produto="+getSQLString(edItem.getOID_Produto()));
//                				notaitem.setPRedBcSt(pe_reducao_base_calculo);

                				notaitem.setPRedBc(0d);//percentual da redu��o da BC
                			}

//                			 if("010".equals(edProduto.getCD_Situacao_Tributaria())){
////                				icms10.setOrig(edSituacao.getDM_Procedencia());
//                				notaitem.setModBcSt(4);
//                				notaitem.setVBcSt(edItem.getVL_Base_Calculo_ICMS());
//                				notaitem.setPlcmsSt(edItem.getPE_Aliquota_ICMS());
////                				notaitem.setVIcmsSt(edItem.getVL_ICMS_Subs());
//
//                				//NOVIDADES de 01/10/09
//                				double pe_magem_valor_agregado = getTableDoubleValue("classificacoes_fiscais.pe_magem_valor_agregado","classificacoes_fiscais, Produtos","classificacoes_fiscais.oid_classificacao_fiscal = Produtos.oid_classificacao_fiscal AND Produtos.oid_Produto="+getSQLString(edItem.getOID_Produto()));
//                				notaitem.setPMvaSt(pe_magem_valor_agregado);
//                				double pe_reducao_base_calculo = getTableDoubleValue("classificacoes_fiscais.pe_reducao_base_calculo","classificacoes_fiscais, Produtos","classificacoes_fiscais.oid_classificacao_fiscal = Produtos.oid_classificacao_fiscal AND Produtos.oid_Produto="+getSQLString(edItem.getOID_Produto()));
//                				notaitem.setPRedBcSt(pe_reducao_base_calculo);
//                			}

                            notaitem.setIpiCenq(999);//Enquadramento Legal do IPI
                            notaitem.setIpiCst(53);//c�digo sit trib ipi
                            notaitem.setIpiVBc(0);//Valor da BC do IPI
                            notaitem.setIpiPIpi(0);//aliquota ipi
                            notaitem.setIpiVUnid(0);//valor por unidade tributavel
                            notaitem.setIpiQUnid(0);//Qtd ? prod trib por unidade
                            notaitem.setVIpi(0);//valor do ipi
                            notaitem.setIiVBc(0);//valor da bc do imposto de importa��o
                            notaitem.setIiDespAdu(0);//vlr despesas aduaneiras
                            notaitem.setIiVii(0);//valor imposto importa��o
                            notaitem.setPisCst(7);//situa��o trib pis
                            notaitem.setPisVBc(0);//Valor da Base de C�lculo pis
                            notaitem.setPPIS(0);//Al�quota do PIS
                            notaitem.setVPIS(0);//Valor do PIS
                            notaitem.setPisQBCProd(0);//Pis Quantidade Vendida
                            notaitem.setPisVAliqProd(0);//PIS Al�quota do PIS (em reais)
                            notaitem.setCofinsCst(7);//situa��o trib cofins
                            notaitem.setConfinsVbc(0);//valor da BC do cofins
                            notaitem.setPCofins(0);//aliq.cofins
                            notaitem.setVCofins(0);//valor cofins
                            notaitem.setCofinsQBCProd(0);//Cofins Quantidade Vendida
                            notaitem.setCofinsVAliqProd(0);//Cofins Al�quota do PIS (em reais)
                            notaitem.setDDI(null);//Data de Registro
                            notaitem.setDDesemb(null);//Data do Desembara�o
                            notaitem.setIssqnVbc(0);//valor da Bc do issqn
                            notaitem.setIssqnVAliq(0);//aliq. Do issqn
                            notaitem.setVIssqn(0);//valor do issqn
                            notaitem.setCMunFg(0);//cod. Mun ocorrencia do fato gerador
                            notaitem.setCListServ(0);//cod lista de servicos
                            notaitem.setCSitTrib("I");//codigo da tributacao do issqn
                            listaItens.add(notaitem);

                            i++;
                        }
                    	notafiscal.setNfeNotaItemCollection(listaItens);

                    } else {
                    	canList = false;
                    	notafiscal.setNfeNotaItemCollection(listaItens);
                    }



//                  aleatorio
        	        String rnd = String.valueOf(System.currentTimeMillis());
        	        Integer nr = new Integer(rnd.substring(rnd.length()-6));
//        	        notafiscal.setCNf(nr);

//        			Transporte
                    NfeTransporte transporte = new NfeTransporte();
                    transporte.setModFrete(0);//Modalidade do frete
                    if (ed.getDM_Frete().equals("1")){
                    	transporte.setModFrete(0);
                    } else if (ed.getDM_Frete().equals("2")){
                    	transporte.setModFrete(1);
                    } else if (ed.getDM_Frete().equals("3")){
                    	transporte.setModFrete(2);
                    } else if (ed.getDM_Frete().equals("9")){
                    	transporte.setModFrete(9);
                    }

                    transporte.setXNome(edTransportador.getNM_Razao_Social());//Raz�o Social/Nome
                    transporte.setIe(edTransportador.getNM_Inscricao_Estadual());//Inscri��o Estadual
                    transporte.setXEnder(edTransportador.getNM_Endereco());//Endere�o Completo
                    transporte.setUf(edTransportador.getCD_Estado());//sigla UF
                    transporte.setXMun(edTransportador.getNM_Cidade());//Nome do munic�pio
                    transporte.setTipoPessoa("J");//
                    if(edTransportador.getNR_CNPJ_CPF().length()<14){
                    	transporte.setTipoPessoa("F");
                    }
                    transporte.setCnpj(JavaUtil.formataCNPJ_CPF(edTransportador.getNR_CNPJ_CPF()));//CNPJ ou CPF
                    transporte.setPlaca(getTableStringValue("NR_Placa", "Veiculos","oid_Veiculo = '"+ed.getOid_Veiculo()+"'"));//Placa do ve�culo
                    if(JavaUtil.doValida(transporte.getPlaca())){
                    	transporte.setUfVeiculo("RS");
        			} else {
        				transporte.setUfVeiculo("");//sigla UF
        			}

                    transporte.setRntc("");//ANTT
                    transporte.setRPlaca("");//Placa do reboque
                    transporte.setRUf("");//UF do reboque
                    transporte.setRRntc("");//ANTT do reboque
                    transporte.setQVol((double)ed.getNr_volumes_nota());//Quantidade de volumes transportados
                    transporte.setEsp("EMB.GERAL");//especie dos volumes transportados
                    transporte.setMarca("MARCA");//marca dos volumes transportados
                    transporte.setNVol("");//numera��o dos volumes transportados
                    transporte.setPesoL(ed.getNR_Peso());//peso liquido em kg
                    transporte.setPesoB(ed.getNR_Peso());//peso bruto em kg
                    transporte.setNLacre("");

                    notafiscal.setTransporte(transporte);

        			//Cobranca
        			String txt_dpl = "";
        			if (doValida(ed.getOid_nota_fiscal())){
        				NfeFaturamento cob = new NfeFaturamento();
        				try {
        					sqlBusca =" SELECT Duplicatas.NR_Duplicata,Duplicatas.DT_Vencimento,Duplicatas.vl_Duplicata " +
        							  " FROM Origens_Duplicatas, Notas_Fiscais, Unidades, " +
        							  " Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Duplicatas ";
        					sqlBusca += " WHERE Unidades.oid_Unidade = Notas_Fiscais.oid_Unidade ";
        					sqlBusca += " AND Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
        					sqlBusca += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
        					sqlBusca += " AND Origens_Duplicatas.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";
        					sqlBusca += " AND Origens_Duplicatas.OID_Duplicata = Duplicatas.OID_Duplicata ";
        					if (String.valueOf (ed.getOid_nota_fiscal()) != null && !String.valueOf (ed.getOid_nota_fiscal()).equals ("") && !String.valueOf (ed.getOid_nota_fiscal()).equals ("null")) {
        						sqlBusca += " and Origens_Duplicatas.Oid_Nota_Fiscal = '" + ed.getOid_nota_fiscal() + "'";
        					}
        					resLocal = sql.executarConsulta (sqlBusca);
        					if(resLocal.next ()) {
        						cob.setNDup(resLocal.getString(1));
//        						cob.setDVenc(FormataDataBean.getFormatDate(resLocal.getString(2)));
//        						cob.setVDup1(resLocal.getDouble(3));
        						cob.setVDup(resLocal.getDouble(3));
        						txt_dpl = "- Fatura: " + resLocal.getString(1) + " - Venc: " + FormataDataBean.getFormatDate(resLocal.getString(2));
        					}
        				}
        				finally {
        					util.closeResultset (resLocal);
        				}
        				notafiscal.setFaturamento(cob);
        			}

        	        notafiscal.setUFEmbarq("");//UF de Embarque dos produtos
        	        notafiscal.setXLocEmbarq("");//Local do embarque


//        			INF ADICIONAL
        			String inf = "";
        			if (msgFiscal.size() > 0)
                    {
                        for (int m=0; m < msgFiscal.size(); m++)
                        {
                            if ((String)msgFiscal.get(m) != null){
                            	inf += (String)msgFiscal.get(m);
                            }
                        }
                    }
        			notafiscal.setInfAdFisco(inf);
        			inf = "";
        			if("D".equals(ed.getDm_tipo_nota_fiscal())){
        				if (doValida(ed.getDm_observacao()))
                        {
                    		inf=ed.getDm_observacao();
                        } else if (doValida(ed.getNm_complemento()))
                        {
                    		inf=ed.getNm_complemento() ;
//                            String[] mensagemSplit = ed.getNm_complemento().split(";");
//                            for (int x = 0; x < mensagemSplit.length; x++)
//                            {
//                                inf += mensagemSplit[x];
//                            }
                        }
        			} else {
        				if (doValida(ed.getDm_observacao()))
                        {
                    		inf=ed.getDm_observacao();
                        }
        			}
//        			inf += " ****** SR. CLIENTE: PARA ACESSAR OS ARQUIVOS ELETRONICOS (XML) DE SUAS NF-E ACESSE http://201.66.254.114:8180/DisponibilizadorNFe/login.jsp,";
//        			inf += " utilizando o login CLIENTE com a senha DEST e informe corretamente APENAS OS NUMEROS de seu CNPJ/CPF. ******  ";
        			String infTrib = "\n\nLei da Transpar�ncia - O valor aproximado de tributos incidentes sobre o pre�o deste Documento � de R$ "+new DecimalFormat("#,##0.00").format(notafiscal.getvTotTrib());
        			notafiscal.setInfCpl(ManipulaString.corrigeString(ManipulaString.Enter2BR(inf + txt_dpl))+infTrib);

        			//...

        			nfReturn = notafiscal;
                    nfReturn.setChaveAcesso(ed.getNfe_chave_acesso());
                    nfReturn.setProtocolo(ed.getNfe_protocolo());
                    nfReturn.setDhRecbto(Utils.getInstance().convertStringDateSefaztoData(ed.getNfe_dt_hr_recebimento()));
//                  E FIM DA NOTA FISCAL

//            }

            if (!doValida(nfReturn.getChaveAcesso()))
                throw new Mensagens("NFe sem Chave de Acesso v�lida, consulte a NF para verificar detalhes...");

        } catch (Excecoes e) {
            throw e;
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "getNFe()");
        }

        return nfReturn;
    }

    public Nota_Fiscal_EletronicaED numeraNFe(Nota_Fiscal_EletronicaED ed,String nmSerie) throws Exception {

    	// .println("setNrSerieNotaFromAIDOF");

		ResultSet rs = null;
		long nrFinal = 0, oid_AIDOF = 0;
		try {
			if (!doValida(nmSerie))
				throw new Excecoes("S�rie n�o informada!");

			String sql = "SELECT " +
						 "NR_Proximo, " +
						 "NR_Final, " +
						 "OID_Aidof, " +
						 "NM_Serie " +
						 "FROM AIDOF " +
						 "WHERE " +
						 "CD_Aidof = '" + nmSerie + "'";

			//*** Seta NR e S�rie da Nota Fiscal
			rs = this.executasql.executarConsulta(sql);
			while (rs.next()) {
				ed.setNm_serie(rs.getString("NM_Serie"));
				ed.setNr_nota_fiscal(rs.getLong("NR_Proximo"));
				nrFinal = rs.getLong("NR_Final");
				oid_AIDOF = rs.getLong("OID_Aidof");
			}
			if (oid_AIDOF < 1)
				throw new Excecoes("AIDOF n�o encontrado : "
						+ nmSerie);

			//*** Verifica por seguran�a se NR ja existe
			while (doExiste("Notas_Fiscais", "NR_Nota_Fiscal = "
					+ ed.getNr_nota_fiscal() +
					" and NM_Serie = '" + ed.getNm_serie() + "'" +
					" and oid_Pessoa = '" + ed.getOid_pessoa() + "'"
					))
				ed.setNr_nota_fiscal(ed.getNr_nota_fiscal() + 1);

			//*** Se Fora do "Range" da NF informa erro
			if ((ed.getNr_nota_fiscal() + 1) > nrFinal)
				throw new Excecoes(
						"N�mero sequencial da Nota Fiscal esgotado! Atualize no AIDOF!");

	    	// .println("setNrSerieNotaFromAIDOF ed.getNr_nota_fiscal="+ed.getNr_nota_fiscal());

			sql = "UPDATE AIDOF SET " +
				  "NR_Proximo= " + (ed.getNr_nota_fiscal() + 1) + " " +
			      "WHERE OID_Aidof = " + oid_AIDOF;
			this.executasql.executarUpdate(sql);

			sql = "UPDATE Notas_Fiscais SET " +
				  "DM_Impresso = 'S' " +
				  ",DM_Situacao = 'F' " +
				  ",DM_Finalizado = 'F' " +
            	  ",NR_Nota_Fiscal = '"+ed.getNr_nota_fiscal()+"' " +
            	  "WHERE oid_Nota_Fiscal = '"+ed.getOid_nota_fiscal()+"' ";

System.out.println("numeraNFe ->>" +sql);

			this.executasql.executarUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "numeraNFe()");
		} finally {
			closeResultset(rs);
		}

		return ed;
	}
    
    public void incluiInutilizacaoNumero(String emissor, String xJust, UsuarioED user, NfeInutilizacao ret)
			throws Excecoes {

		String sql = null;

		String oidUsuario = "0";
		if(user != null && user.getOid_Usuario().intValue()>0)
			oidUsuario = String.valueOf(user.getOid_Usuario().intValue());

		try {

			sql = " insert into inutilizacao_cte (tpAmb,verAplic,dhRecbto,nProt,digVal,cStat,xMotivo,cUF,nCTIni,nCTFin,mod,serie,ano,emissor,xJust,oid_usuario) "
					+ " values (";
			sql += JavaUtil.getSQLString(ret.getTpAmb()) + ",";
			sql += JavaUtil.getSQLString(ret.getVerAplic()) + ",";
			sql += JavaUtil.getSQLString(ret.getDhRecbto()) + ",";
			sql += JavaUtil.getSQLString(ret.getnProt()) + ",";
			sql += JavaUtil.getSQLString(ret.getDigVal()) + ",";
			sql += JavaUtil.getSQLString(ret.getcStat()) + ",";
			sql += JavaUtil.getSQLString(ret.getxMotivo()) + ",";
			sql += JavaUtil.getSQLString(ret.getcUF()) + ",";
			sql += JavaUtil.getSQLString(ret.getnNFIni()) + ",";
			sql += JavaUtil.getSQLString(ret.getnNFFin()) + ",";
			sql += JavaUtil.getSQLString(ret.getMod()) + ",";
			sql += JavaUtil.getSQLString(ret.getSerie()) + ",";
			sql += JavaUtil.getSQLString(ret.getAno()) + ",";
			sql += JavaUtil.getSQLString(emissor) + ",";
			sql += JavaUtil.getSQLString(xJust) + ",";
			sql += JavaUtil.getSQLString(oidUsuario) + "";
			sql += ")";
System.out.println(sql);
			int i = executasql.executarUpdate(sql);
		}

		catch (Exception exc) {
			throw new Excecoes("Erro ao incluir inutilizacao NFe", exc, this
					.getClass().getName(), "incluiInutilizacaoNumero()");
		}
	}

}