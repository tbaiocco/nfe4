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

import br.cte.model.CteInutilizacao;
import br.nfe.core.base.EmpresaDb;
import br.nfe.model.Empresa;
import br.nfe.model.NfeCancelamento;
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
import com.master.root.Condicao_PagamentoBean;
import com.master.root.DateFormatter;
import com.master.root.FormataDataBean;
import com.master.root.PessoaBean;
import com.master.root.UnidadeBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.ManipulaString;
import com.master.util.Mensagens;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Nota_Fiscal_EletronicaBD extends BancoUtil {

	private ExecutaSQL executasql;

	FormataDataBean dataFormatada = new FormataDataBean();

	public Nota_Fiscal_EletronicaBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
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
// .println(sql);
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

			}
			return ed;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "getByRecord()");
		} finally {
			closeResultset(res);
		}
	}

	/** Faz a conexao com a biblioteca de NFe, que processara e depois devolvera os dados para impressao do DANFE
     *  @param ArrayList de Nota_Fiscal_TransacoesED
     *  @param Data de Saida
     *  @param Hora de Saida
     *  @return NfeNotaFiscal nota para ser impressa ou NfeNotaFiscal vazio no caso de algum erro
     *  @throws Excecoes
     * */
    public NfeNotaFiscal geraNFe(ArrayList listaNotasFiscais, String dtSaida, String hrSaida) throws Excecoes {

    	NfeNotaFiscal nfReturn = new NfeNotaFiscal();
    	NfeServicos servico = new NfeServicos();
        try {
        	 // System.out.println("geraNotaFiscal_to_NFe 1");
//        	Origem_DuplicataED DupliED = new Origem_DuplicataED();

        	// ************************************************************
            String toReturn = "";
            String sqlUpdate = "";

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
            Iterator iterator = listaNotasFiscais.iterator();
            while (iterator.hasNext())
            {
                Nota_Fiscal_EletronicaED ed = (Nota_Fiscal_EletronicaED) iterator.next();
 System.out.println("NFe OID:"+ed.getOid_nota_fiscal());

                isSaida = ("D".equals(ed.getDm_tipo_nota_fiscal()) || "R".equals(ed.getDm_tipo_nota_fiscal()) || "S".equals(ed.getDm_tipo_nota_fiscal()));//*** Tipo de NOTA

                ArrayList lista = new Item_Nota_Fiscal_TransacoesBD(executasql).lista(new Item_Nota_Fiscal_TransacoesED(ed.getOid_nota_fiscal()));
                Natureza_OperacaoED edCFOP = new Natureza_OperacaoBD(executasql).getByRecord(new Natureza_OperacaoED(new Integer((int)ed.getOid_natureza_operacao())));

                ClienteBean edCliente = ClienteBean.getByOID_Cliente(ed.getOid_pessoa_destinatario());
//                ClienteBean edCliente = ClienteBean.getByOID_Cliente_Nota(ed.getOid_pessoa_destinatario());

                PessoaED edPessoa = new PessoaBD(executasql).getByRecord(new PessoaED(ed.getOid_pessoa_destinatario()));

                int oid_uni = new Integer (String.valueOf(ed.getOID_Unidade_Fiscal())).intValue();

                UnidadeBean unidade_Remetente = new UnidadeBean().getByOID_Unidade(oid_uni);;

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
                    	new Nota_Fiscal_EletronicaRN().numeraNFe(ed, pWMS.getCd_Aidof_Nota_Fiscal_Devolucao());
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

//System.out.println("printNotaFiscalSaida ->>" +sqlUpdate);

                    executasql.executarUpdate(sqlUpdate);
                    // RALPH
                    if ("E".equals(ed.getDm_tipo_nota_fiscal())){

                    	sqlUpdate = "UPDATE Notas_Fiscais SET " +
		                    " DT_Entrega = '"+ed.getDt_emissao() + "' " +
		                    " ,HR_Entrada = '"+Data.getHoraHM() + "' " +
		                    " WHERE oid_Nota_Fiscal = '"+ed.getOid_nota_fiscal()+"' ";

	                    	executasql.executarUpdate(sqlUpdate);
                    }
//        	        //*** Gerar Livro Fiscal
//        	        if ("S".equals(ed.edModelo.getDM_Gera_Fiscal()))
//        	        {
//                        if (isSaida || JavaUtil.doValida(ed.getDM_Tipo_Devolucao()))
//                        {
//                        	new Livro_FiscalBD(executasql).geraLivro_Fiscal_Saidas(new Livro_FiscalED(ed.getOid_nota_fiscal(), "NF"), "S");
//                        }else{
//                        	new Livro_FiscalBD(executasql).geraLivro_Fiscal_Entradas(new Livro_FiscalED(ed.getOid_nota_fiscal(), "NF"), "E");
//                        }
//        	        }
                }

            	// System.out.println("printNotaFiscalSaida 10");
                PessoaED unidade = new PessoaBD(executasql).getByRecord(unidade_Remetente.getOID_Pessoa());
//              ******** IN�CIO NOTA FISCAL *************
                nrNotaAtual++;
//              Nota Fiscal
                NfeNotaFiscal notafiscal = new NfeNotaFiscal();

                //Ambiente: 1-prod, 2-homologa
                if(Parametro_FixoED.getInstancia().getNM_Base().equals("HOMOLOGA")){
                	notafiscal.setTpAmbiente(2);
                }
                else
                	notafiscal.setTpAmbiente(1);


                notafiscal.setVersaoNfe("3.10"); //10-4-14 - VER 3.10

                notafiscal.setIndFinal(1);//10-4-14 - VER 3.10
                notafiscal.setIndPres(1);//10-4-14 - VER 3.10
                notafiscal.setIndSinc(0);//10-4-14 - VER 3.10 - 0-asincrono 1-sincrono

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
//System.out.println("serie:"+ed.getNm_serie()+"|"+ser);
                notafiscal.setSerie(new Integer(ser).intValue());//s�rie do documento fiscal
                notafiscal.setNNF((int)ed.getNr_nota_fiscal());//n�mero do documento fiscal
                notafiscal.setDEmi(Data.strToDate(ed.getDt_emissao()));//data de emiss�o do documento fiscal
                notafiscal.setDSaiEnt(Data.strToDate(getValueDef(dtSaida, ed.getDt_emissao())));//data de sa�da ou entrada da mercadoria
                notafiscal.setTpNf(isSaida ? 1 : 0);//tipo de documento fiscal
                notafiscal.setTpEmissao(1);//forma de emiss�o do nfe
                notafiscal.setFinNfe(1);//Finalidade de emiss�o da NF-E >> NORMAL
                if(ed.getOid_modelo_nota_fiscal() == 13 || ed.getOid_modelo_nota_fiscal() == 15 || ed.getOid_modelo_nota_fiscal() == 22){
                	notafiscal.setFinNfe(3);//Finalidade de emiss�o da NF-E >> AJUSTE
                	String NF_Ref = getTableStringValue("nm_ch_referenciada",	"Notas_Fiscais", "oid_nota_fiscal = '"	+ ed.getOid_nota_fiscal() + "'");
                	if(ed.getOid_modelo_nota_fiscal() == 22){
                		if(!JavaUtil.doValida(NF_Ref)){
                			throw new Excecoes("Para NFe de ajuste � necess�rio informar a chave da nota de referenciada!");
                		}
	                	NfeReferenciada nfref = new NfeReferenciada();
	                	ArrayList nfsref = new ArrayList();
	                	nfref.setRefNfe(NF_Ref);
	                	nfsref.add(nfref);
	                	notafiscal.setNfeReferenciadaCollection(nfsref);
                	}
                }

                if(Parametro_FixoED.getInstancia().getNM_Empresa().equals("TSG")){
                	if(ed.getOid_modelo_nota_fiscal() == 11){
                    	notafiscal.setFinNfe(3);//Finalidade de emiss�o da NF-E >> AJUSTE
                    } else {
                    	notafiscal.setFinNfe(1);//Finalidade de emiss�o da NF-E >> NORMAL
                    }
                }

                //NOTAS DE DEVOLUCAO
                if("D".equals(ed.getDm_tipo_nota_fiscal()) || edCFOP.getDM_Tipo_Operacao().equalsIgnoreCase("S")){
                	if(edCFOP.getOid_Natureza_Operacao() == 43 || edCFOP.getOid_Natureza_Operacao() == 99 || edCFOP.getOid_Natureza_Operacao() == 6)
                		notafiscal.setFinNfe(1);//Finalidade de emiss�o da NF-E >> SAIDA/REMESSA
                	else {
                		notafiscal.setFinNfe(4);//Finalidade de emiss�o da NF-E >> DEVOLUCAO
	                	String NF_Ref = getTableStringValue("nm_ch_referenciada",	"Notas_Fiscais", "oid_nota_fiscal = '"	+ ed.getOid_nota_fiscal() + "'");
	                	if(!JavaUtil.doValida(NF_Ref)){
	                		throw new Excecoes("Para NFe de devolu��o � necess�rio informar a chave da nota de entrada referenciada!");
	                	}
	                	NfeReferenciada nfref = new NfeReferenciada();
	                	ArrayList nfsref = new ArrayList();
	                	nfref.setRefNfe(NF_Ref);
	                	nfsref.add(nfref);
	                	notafiscal.setNfeReferenciadaCollection(nfsref);
                	}
                } 
                if("E".equals(ed.getDm_tipo_nota_fiscal()) && edCFOP.getOid_Natureza_Operacao() == 116){
                	notafiscal.setFinNfe(4);//Finalidade de emiss�o da NF-E >> DEVOLUCAO
                	String NF_Ref = getTableStringValue("nm_ch_referenciada",	"Notas_Fiscais", "oid_nota_fiscal = '"	+ ed.getOid_nota_fiscal() + "'");
                	if(!JavaUtil.doValida(NF_Ref)){
                		throw new Excecoes("Para NFe de devolu��o de Venda � necess�rio informar a chave da nota de venda referenciada!");
                	}
                	NfeReferenciada nfref = new NfeReferenciada();
                	ArrayList nfsref = new ArrayList();
                	nfref.setRefNfe(NF_Ref);
                	nfsref.add(nfref);
                	notafiscal.setNfeReferenciadaCollection(nfsref);
                }

                notafiscal.setVersaoNfe("3.10");

    			CidadeBean origem = CidadeBean.getByOID((int)unidade.getOid_Cidade());

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
    	        CidadeBean orig = CidadeBean.getByOID((int)unidade.getOid_Cidade());
    			cIBGE_UF = getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+orig.getOID_Estado());
    	        emitente.setCUf(Integer.parseInt(cIBGE_UF));//C�digo do Estado
    	        emitente.setUf(unidade.getCD_Estado());//sigla UF
    	        emitente.setCep(Integer.parseInt(unidade.getNR_CEP()));//C�digo do CEP
    	        emitente.setCPais(1058);//C�digo do Pa�s
    	        emitente.setXPais("BRASIL");//Nome do Pa�s
    	        emitente.setFone(ManipulaString.limpaCampo(unidade.getNR_Telefone()));//Telefone
    	        emitente.setEmail("cristiana.fiscal@transmiro.com.br");//E-mail do emitente
    	        emitente.setSite("WWW.TRANSMIRO.COM.BR");//Site do NfeEmitente
    	        emitente.setCrt(3);//C�digo de Regime Tribut�rio
    	        notafiscal.setEmitente(emitente);

    	        if(notafiscal.getTpAmbiente()==2){
    	        	emitente.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
    	        	emitente.setXFant("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
    	        }

    	        notafiscal.setCnpj(emitente.getCnpj());//CNPJ emitente
    	        EmpresaDb eDb = new EmpresaDb();
    	        Empresa e = eDb.getEmpresa(emitente.getCnpj());
    	        notafiscal.setEmpresa(e);

//    			Destinatario
    	        CidadeBean destino = CidadeBean.getByOID((int)edPessoa.getOid_Cidade());
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
    	        destinatario.setCep(Integer.parseInt(ManipulaString.limpaCampo(JavaUtil.doValida(edPessoa.getNR_CEP())?edPessoa.getNR_CEP():"0")));//C�digo do CEP
    	        destinatario.setCPais(1058);//C�digo do Pa�s
    	        destinatario.setXPais("BRASIL");//Nome do Pa�s
    	        destinatario.setFone(ManipulaString.limpaCampo(edPessoa.getNR_Telefone()));//Telefone
    	        destinatario.setEmail(null);//E-mail do Destinat�rio

    	        if(!JavaUtil.doValida(edPessoa.getNM_Inscricao_Estadual())){
    	        	destinatario.setIndIEDest(9);//10-4-14 - VER 3.10
    	        } else if(edPessoa.getNM_Inscricao_Estadual().equalsIgnoreCase("ISENTO")){
    	        	destinatario.setIndIEDest(2);//10-4-14 - VER 3.10
    	        	destinatario.setIndIEDest(9);//10-4-14 - VER 3.10
    	        } else {
    	        	destinatario.setIndIEDest(1);//10-4-14 - VER 3.10
    	        }

    	        if(notafiscal.getTpAmbiente()==2){
    	        	destinatario.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
    	        }

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
                notafiscal.setVIpi(ed.getVl_ipi());//valor total do ipi
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
                	 System.out.println("NFe itens ...");

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
                            
//                            notaitem.setXvProd(Valores.trataDouble(edItem.getVL_Item(), 2));//valor total bruto
//                            notaitem.setVUnCom(notaitem.getXvProd() / notaitem.getQCom());//valor unit�rio de comercializa��o
System.out.println(notaitem.getCProd()+":"+notaitem.getVUnCom()+"*"+notaitem.getQCom()+"="+(notaitem.getVUnCom()*notaitem.getQCom()));
System.out.println(" ----- "+notaitem.getXvProd());
                            notaitem.setCEanTrib("");//c�digo de barras
                            notaitem.setUTrib(edProduto.getCD_Unidade_Produto());//unidade tribut�vel
                            notaitem.setQTrib(edItem.getNR_QT_Atendido());//quantidade tribut�vel
//                            notaitem.setVUnTrib(edItem.getVL_Unitario());//valor unit�rio de tributa��o
                            notaitem.setVUnTrib(notaitem.getVUnCom());

                            notaitem.setVFrete(0d);//valor total do frete
                            notaitem.setVSeg(0d);//valor total do seguro
                            notaitem.setVDesc(edItem.getVL_Desconto());//valor do desconto
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

                			notaitem.setVICMSDeson(0d);  //10-4-14 - VER 3.10
            		        notaitem.setvTotTrib(10d);  //10-4-14 - VER 3.10
            		        notaitem.setVICMSDeson(0d);//10-4-14 - VER 3.10
            		        notaitem.setMotDesICMS(0);//10-4-14 - VER 3.10
            		        notaitem.setVICMSOp(10d);//10-4-14 - VER 3.10
            		        notaitem.setPDif(50d);//10-4-14 - VER 3.10
            		        notaitem.setVICMSDif(0d);//10-4-14 - VER 3.10
            		        notaitem.setNFCI("");//10-4-14 - VER 3.10
            		        notaitem.setXPed("");//10-4-14 - VER 3.10
            		        notaitem.setNItemPed(countItens);     //10-4-14 - VER 3.10

                			 if("000".equals(edProduto.getCD_Situacao_Tributaria())){
                				 notaitem.setPRedBc(0d);//percentual da redu��o da BC

                			 } else if("090".equals(edProduto.getCD_Situacao_Tributaria())) {
//                				 notaitem.setVIcms(0D);
//                				 notaitem.setVBc(edItem.getVL_ICMS_Aprov());
                				 notaitem.setXvProd(edItem.getVL_ICMS_Aprov());//valor total bruto
                				 notaitem.setPRedBc(0.01);//percentual da redu��o da BC
                				 notafiscal.setVProd(ed.getVl_icms());//valor total dos produtos e servi�os
//                				 notaitem.setIndTot(0);
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

                            notaitem.setIpiCenq(999);//Enquadramento Legal do IPI
                            notaitem.setIpiCst(53);//c�digo sit trib ipi
                            notaitem.setIpiVBc(edItem.getVL_Item());//Valor da BC do IPI
                            notaitem.setIpiPIpi(0d);//aliquota ipi
                            notaitem.setIpiVUnid(0);//valor por unidade tributavel
                            notaitem.setIpiQUnid(0);//Qtd ? prod trib por unidade
                            notaitem.setVIpi(edItem.getVL_IPI());//valor do ipi
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

                            notaitem.setvTotTrib(notaitem.getVIcms()+notaitem.getVIcmsSt()+notaitem.getVIpi()+notaitem.getVIssqn()+notaitem.getVCofins()+notaitem.getVPIS());

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
//System.out.println("CNf:"+nr);
        	        notafiscal.setCNf(nr);

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
//System.out.println("1>"+transporte.getEsp()+"|"+transporte.getModFrete());
                    transporte.setXNome(edTransportador.getNM_Razao_Social());//Raz�o Social/Nome
                    transporte.setIe(edTransportador.getNM_Inscricao_Estadual());//Inscri��o Estadual
                    transporte.setXEnder(edTransportador.getNM_Endereco());//Endere�o Completo
                    transporte.setUf(edTransportador.getCD_Estado());//sigla UF
                    transporte.setXMun(JavaUtil.getValueDef(edTransportador.getNM_Cidade(),"").trim());//Nome do munic�pio
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
        					resLocal.close();
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
    				String infTrib = "\n\nLei da Transpar�ncia - O valor aproximado de tributos incidentes sobre o pre�o deste Documento � de R$ "+new DecimalFormat("#,##0.00").format(notafiscal.getvTotTrib());
        			notafiscal.setInfCpl(ManipulaString.corrigeString(ManipulaString.Enter2BR(inf + txt_dpl))+infTrib);
        			//...

//                  E FIM DA NOTA FISCAL
                    //AQUI VAI O LANCE DO TestaNFE!!!
//        			Chama o gerador...
System.out.println("enviando :"+ed.getNfe_chave_acesso() + "|"+notafiscal.getChaveAcesso());
                    NfeLote retorno = servico.enviaNfe(notafiscal);
                    if (retorno != null) {
                    	System.out.println("Retorno processamento de lote 3.10 ok!!!\n"
                                + " Recibo: " + retorno.getNRec() + "\n"
                                + " Data: " + retorno.getData() + "\n"
                                + " cstat: " + retorno.getCStat() + " \n"
                                + " Motivo: " + retorno.getXMotivo());
//System.out.println("enviando :"+ed.getNfe_chave_acesso() + "|"+notafiscal.getChaveAcesso());
                    	//update na NF
                        String OK = "";
                        if(JavaUtil.doValida(ed.getNfe_cstat_lote()) && "100".equals(ed.getNfe_cstat_lote())){

                        } else {
                        	OK = this.updateRetornoLote(ed, retorno);
                        }

//                        try {
//                            System.out.println("aguardando 5s para consultar recibo!!!");
//                            Thread.sleep(5000);
//                        } catch (InterruptedException ex) {
//                            ex.printStackTrace();
//                        }

                        if (retorno.getNfeNotaFiscal() != null) {
                            System.out.println("ChAcessoGerada: " + retorno.getNfeNotaFiscal().getChaveAcesso()+"\n");

//                            System.out.println("iniciando consulta de recibo... ");
                            NfeRetornoEnvioLote ret = null;

                            if (notafiscal.getIndSinc() == 0) {
                                System.out.println("iniciando consulta de recibo... ");
                                try {
                                    System.out.println("aguardando 3s para consultar recibo!!!");
                                    Thread.sleep(3000);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                                ret = servico.retornoEnvioNfe(retorno.getNRec(), retorno.getNfeNotaFiscal());
                            } else if (retorno.getNfeRetornoEnvioLote() != null) {
                                ret = retorno.getNfeRetornoEnvioLote();

                                System.out.println("iniciando consulta de recibo 2... ");
                                try {
                                    System.out.println("aguardando 3s para consultar recibo!!!");
                                    Thread.sleep(3000);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                                ret = servico.retornoEnvioNfe(retorno.getNRec(), retorno.getNfeNotaFiscal());
                            }

                            if (ret != null) {
                            	System.out.println("Retorno NFE 3.10 ok!!!\n"
                                        + " Protocolo: " + ret.getnProt() + "\n"
                                        + " Data: " + ret.getDhRecbto() + "\n"
                                        + " cstat: " + ret.getcStat() + "\n"
                                        + " Motivo: " + ret.getxMotivo());
                                nfReturn = notafiscal;
                                nfReturn.setChaveAcesso(retorno.getNfeNotaFiscal().getChaveAcesso());
                                nfReturn.setProtocolo(ret.getnProt());
                                nfReturn.setDhRecbto(Utils.getInstance().convertStringDateSefaztoData(ret.getDhRecbto()));
                                nfReturn.setcStat(Integer.parseInt(ret.getcStat()));
                                nfReturn.setxMotivo(ret.getxMotivo());
                                //update na NF
//                                if(JavaUtil.doValida(ed.getNfe_cstat()) && "100".equals(ed.getNfe_cstat())){
//
//                                } else {
                                	OK = this.updateRetornoNFE(ed, retorno, ret);
//                                }
                            } else {
                            	throw new Mensagens("Erro ao buscar NFE 3.10...\n\r"+JavaUtil.getErrors(servico.getErros()).toUpperCase());
                            }
                        } else {
                        	throw new Mensagens("Retorno do lote n�o tem a nfe...\n\r");
                        }
                    } else {
                    	throw new Mensagens("Erro ao enviar lote 3.10...\n\r"+JavaUtil.getErrors(servico.getErros()).toUpperCase());
                    }
            }

//            if (!doValida(nfReturn.getChaveAcesso()))
//                throw new Mensagens("NFe sem Chave de Acesso v�lida, consulte a NF para verificar detalhes...\n\r"+JavaUtil.getErrors(servico.getErros()).toUpperCase());

        } catch (Excecoes e) {
        	e.printStackTrace();
            throw e;
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Excecoes(e.getMessage()+ "\n\r" +JavaUtil.getErrors(servico.getErros()).toUpperCase(), this.getClass().getName(), "geraNFe()");
        }

        return nfReturn;
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
	public String updateRetornoNFE(Nota_Fiscal_EletronicaED ed, NfeLote retorno, NfeRetornoEnvioLote ret) throws SQLException, Excecoes {
		try{

			String sqlUpdate;
			sqlUpdate = "UPDATE Notas_Fiscais SET " +
			            " nfe_chave_acesso = '" + retorno.getNfeNotaFiscal().getChaveAcesso() + "' " +
						" ,nfe_protocolo = '"+ret.getnProt() + "' " +
			            " ,nfe_dt_hr_recebimento = '"+(ret.getDhRecbto()) + "' " +
			    		" ,nfe_cstat = '" + ret.getcStat() + "' " +
			    		" ,nfe_motivo = '" + ret.getxMotivo() + "' " +
						" ,nfe_digestvalue = '" + ret.getDigVal() + "' " +
			            " WHERE oid_Nota_Fiscal = '"+ed.getOid_nota_fiscal()+"' ";
//System.out.println(sqlUpdate);
			executasql.executarUpdate(sqlUpdate);
			if(JavaUtil.doValida(ret.getcStat()) && "100".equals(ret.getcStat())){
				return "OK";
            } else {
            	return "NOK";
            }
		} catch(Exception e){
			return "ERRO: "+e.getMessage();
		}
	}
	public String updateRetornoCancelamento(Nota_Fiscal_EletronicaED ed, NfeCce ret) throws SQLException, Excecoes {
		try{
			String sqlUpdate;
			sqlUpdate = "UPDATE Notas_Fiscais SET " +
			            " nfe_dt_hr_recebimento = '"+(ret.getDhRegEvento()+"                   ").substring(0,20) + "' " +
			    		" ,nfe_cstat = '" + ret.getcStat() + "' " +
			    		" ,nfe_motivo = '" + ret.getxMotivo() + "' ";
			    		if(JavaUtil.doValida(ret.getnProt()))
			    			sqlUpdate += " ,nfe_protocolo = '"+ret.getnProt() + "' ";
    		sqlUpdate += " WHERE oid_Nota_Fiscal = '"+ed.getOid_nota_fiscal()+"' ";
System.out.println(sqlUpdate);
			executasql.executarUpdate(sqlUpdate);
			if(JavaUtil.doValida(ret.getcStat()) && "101".equals(ret.getcStat())){
				return "OK";
            } else {
            	return "NOK";
            }
		} catch(Exception e){
			return "ERRO: "+e.getMessage();
		}
	}

	public void cancelaNFE(Nota_Fiscal_EletronicaED ed) throws Excecoes {

		NfeServicos servico = new NfeServicos();
		try{
			//para horario de verao
//			TimeZone tz = TimeZone.getTimeZone("GMT-02:00");
//	        TimeZone.setDefault(tz);
//	        Calendar ca = GregorianCalendar.getInstance(tz);
//	        Date dt = ca.getTime();
			Date dt = new Date();

			int oid_uni = new Integer (String.valueOf(ed.getOID_Unidade_Fiscal())).intValue();
            UnidadeBean unidade_Remetente = new UnidadeBean().getByOID_Unidade(oid_uni);
            PessoaED unidade = new PessoaBD(executasql).getByRecord(unidade_Remetente.getOID_Pessoa());
			NfeEmitente emitente = new NfeEmitente();
	        emitente.setCnpj(unidade.getNR_CNPJ_CPF());
	        CidadeBean orig = CidadeBean.getByOID((int)unidade.getOid_Cidade());
	        String cIBGE_UF = getTableStringValue("nm_codigo_ibge", "estados", "oid_estado="+orig.getOID_Estado());
System.out.println("Cid:"+orig.getOID()+"-"+orig.getNM_Cidade()+" | "+cIBGE_UF+" >"+dt.toString());
	        emitente.setCUf(Integer.parseInt(cIBGE_UF));//C�digo do Estado
	        NfeNotaFiscal nota = new NfeNotaFiscal();
	        nota.setCnpj(unidade.getNR_CNPJ_CPF());
	        nota.setEmitente(emitente);
	        //nota.setTpAmbiente(1);
	        if(Parametro_FixoED.getInstancia().getNM_Base().equals("HOMOLOGA"))
            	nota.setTpAmbiente(2);
            else
            	nota.setTpAmbiente(1);
	        nota.setJustificativaCancelamento("ERRO NA EMISSAO, DESISTENCIA DO CLIENTE.");
	        nota.setChaveAcesso(ed.getNfe_chave_acesso());
	        nota.setNNF((int)ed.getNr_nota_fiscal());
	        nota.setProtocolo(ed.getNfe_protocolo());

	        nota.setDSaiEnt(dt);

	        NfeCce cce = new NfeCce();
            cce.setcOrgao("" + nota.getEmitente().getCUf());
            cce.setCNPJ(nota.getEmitente().getCnpj());
            cce.setnProt(nota.getProtocolo());
            cce.setDescEvento("Cancelamento");
            cce.setxJus(nota.getJustificativaCancelamento());
            cce.setxMotivo(nota.getJustificativaCancelamento());
            cce.setnSeqEvento(1);
            cce.setTpEvento(110111);
            cce.setDhEvento(new Date());
            cce.setVerEvento("1.00");
            cce.setVersao("1.00");
            cce.setTpAmbiente(nota.getTpAmbiente());
            cce.setChNFe(nota.getChaveAcesso());

	        NfeCce retorno = servico.recepcaoEventoNfe(nota.getTpAmbiente(), nota.getEmitente().getCnpj(),cce);
	        if (retorno != null) {
	            System.out.println("Retorno cancelamento 2.00 ok!!!\n"
	                    + " Protocolo: " + retorno.getnProt() + "\n"
	                    + " Data: " + retorno.getDhRegEvento() + "\n"
	                    + " cstat: " + retorno.getcStat() + " \n"
	                    + " Motivo: " + retorno.getxMotivo());
	            String OK = this.updateRetornoCancelamento(ed, retorno);

	        } else {
	            System.out.println("Erro ao buscar cancelamento 2.00 ... consulte hashMap");
	            JavaUtil.showErrors(servico.getErros());
	        }
		} catch (Exception e) {
        	e.printStackTrace();
            throw new Excecoes(e.getMessage()+ "\n" +JavaUtil.getErrors(servico.getErros()), this.getClass().getName(), "geraNFe()");
        }

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