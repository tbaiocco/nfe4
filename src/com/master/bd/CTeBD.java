package com.master.bd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.master.ed.ConhecimentoED;
import com.master.ed.Origem_DuplicataED;
import com.master.ed.UsuarioED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.ManipulaString;
import com.master.util.StringUtil;
import com.master.util.Utilitaria;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

import br.cte.base.EmpresaDb;
import br.cte.model.Cte;
import br.cte.model.CteAereo;
import br.cte.model.CteCompPrestacaoServico;
import br.cte.model.CteCompl;
import br.cte.model.CteContQt;
import br.cte.model.CteDestinatario;
import br.cte.model.CteDocAnt;
import br.cte.model.CteEmitente;
import br.cte.model.CteEvento;
import br.cte.model.CteExpedidor;
import br.cte.model.CteIdDocAnt;
import br.cte.model.CteImposto;
import br.cte.model.CteInfAnu;
import br.cte.model.CteInfCTeNorm;
import br.cte.model.CteInfCteSub;
import br.cte.model.CteInfModal;
import br.cte.model.CteInfNF;
import br.cte.model.CteInfNFe;
import br.cte.model.CteInfOutros;
import br.cte.model.CteInfQCarga;
import br.cte.model.CteInutilizacao;
import br.cte.model.CteLacRodo;
import br.cte.model.CteLote;
import br.cte.model.CteMoto;
import br.cte.model.CteObs;
import br.cte.model.CteOcc;
import br.cte.model.CtePass;
import br.cte.model.CtePeri;
import br.cte.model.CteProp;
import br.cte.model.CteRecebedor;
import br.cte.model.CteRemetente;
import br.cte.model.CteRetornoEnvioLote;
import br.cte.model.CteRodo;
import br.cte.model.CteSeg;
import br.cte.model.CteTomador;
import br.cte.model.CteValePed;
import br.cte.model.CteVeic;
import br.cte.model.CteVeicNovos;
import br.cte.model.Empresa;

public class CTeBD {

	private ExecutaSQL executasql;

	Utilitaria util = null;

	public CTeBD(ExecutaSQL sql) {
		util = new Utilitaria(executasql);
		this.executasql = sql;
	}
	
	//UNILEVER - 21/08/2017
	public ArrayList<String> getCNPJUnilever() {
		ArrayList<String> toReturn = new ArrayList<String>();
		try {
			String query = "SELECT oid_pessoa FROM CLIENTES where oid_grupo_economico = 9";
			ResultSet res = this.executasql.executarConsulta(query);
			while(res.next()) {
				toReturn.add(res.getString("oid_pessoa"));
			}			
		} catch (Exception exc) {
//			throw new Excecoes("Erro ao buscar cnpj unilever", exc, this
//					.getClass().getName(), "getCNPJUnilever()");
			System.out.println("Erro ao buscar cnpj unilever...");
			exc.printStackTrace();
			return new ArrayList<String>();
		}
		return toReturn;
	}

	public void alteraEnvio(ConhecimentoED ed, CteLote retorno) throws Excecoes {

		String sql = null;

		try {

			sql = " update Conhecimentos set ";
			sql += " NM_CH_CTe = '" + retorno.getCte().getChaveAcesso() + "' ";
			sql += " ,NM_Protocolo_Envio_CTe = '" + retorno.getNRec() + "' ";
			sql += " ,DT_Envio_CTe = '"
					+ new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss")
							.format(retorno.getData()) + "' ";
			sql += " ,DM_Status_CTe = '" + "E" + "' ";
			sql += " ,NM_Status_CTe = '" + "ENVIADO PARA SEFAZ" + "' ";
			sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento()
					+ "'";
			System.out.println(sql);
			int i = executasql.executarUpdate(sql);

			if (JavaUtil.doValida(ed.getDM_Tipo_Conhecimento())
					&& "A".equals(ed.getDM_Tipo_Conhecimento())) {
				// //agora os dados de anulacao
				sql = " update Conhecimentos set ";
				sql += " dt_envio_anulacao_cte = '"
						+ new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss")
								.format(retorno.getData()) + "' ";
				sql += " ,dm_status_anulacao_cte = '" + "E" + "' ";
				sql += " ,nm_status_anulacao_cte = '" + "ENVIADO PARA SEFAZ"
						+ "' ";
				sql += " where oid_conhecimento_substituto = '"
						+ ed.getOID_Conhecimento() + "' and dm_situacao='A' ";
				System.out.println(sql);
				i = executasql.executarUpdate(sql);
			}

		}

		catch (Exception exc) {
			throw new Excecoes("Erro ao alterar envio CTe", exc, this
					.getClass().getName(), "alteraEnvio()");
		}
	}

	public void alteraRetornoCTe(ConhecimentoED ed, CteRetornoEnvioLote ret)
			throws Excecoes {

		String sql = null;

		try {

			sql = " update Conhecimentos set ";
			sql += " NM_Protocolo_Retorno_CTe = '" + ret.getNProt() + "' ";
			sql += " ,DT_Recebimento_CTe = '" + (ret.getDhRecbto()) + "' ";
			sql += " ,DM_Status_CTe = '" + ret.getCStat() + "' ";
			sql += " ,NM_Status_CTe = '"
					+ ManipulaString.tiraAspas(ret.getXMotivo()) + "' ";
			// sql += " ,nfe_digestvalue = '" + ret.getDigVal() + "' ";
			if ("100".equals(ret.getCStat())) {
				sql += ", DM_Impresso='S' ";
			}
			sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento()
					+ "'";
			System.out.println(sql);
			int i = executasql.executarUpdate(sql);

			// dados da anulacao
			// if ("100".equals(ret.getCStat())) {
			if (JavaUtil.doValida(ed.getDM_Tipo_Conhecimento())
					&& "A".equals(ed.getDM_Tipo_Conhecimento())) {
				sql = " update Conhecimentos set ";
				sql += " nm_protocolo_anulacao_cte = '" + ret.getNProt() + "' ";
				sql += " ,dt_recebimento_anulacao_cte = '"
						+ (ret.getDhRecbto()) + "' ";
				sql += " ,dm_status_anulacao_cte = '" + ret.getCStat() + "' ";
				sql += " ,nm_status_anulacao_cte = '"
						+ ManipulaString.tiraAspas(ret.getXMotivo()) + "' ";

				sql += " where oid_conhecimento_substituto = '"
						+ ed.getOID_Conhecimento() + "' and dm_situacao='A' ";
				System.out.println(sql);
				i = executasql.executarUpdate(sql);
			}
		}

		catch (Exception exc) {
			throw new Excecoes("Erro ao alterar retorno CTe", exc, this
					.getClass().getName(), "alteraEnvio()");
		}
	}

	public void alteraRetornoAnulacaoCTe(ConhecimentoED ed,
			CteRetornoEnvioLote ret) throws Excecoes {

		String sql = null;

		try {

			sql = " update Conhecimentos set ";
			sql += " NM_Protocolo_Retorno_CTe = '" + ret.getNProt() + "' ";
			sql += " ,DT_Recebimento_CTe = '" + (ret.getDhRecbto()) + "' ";
			sql += " ,DM_Status_CTe = '" + ret.getCStat() + "' ";
			sql += " ,NM_Status_CTe = '"
					+ ManipulaString.tiraAspas(ret.getXMotivo()) + "' ";
			// sql += " ,nfe_digestvalue = '" + ret.getDigVal() + "' ";
			if ("100".equals(ret.getCStat())) {
				sql += ", DM_Impresso='S' ";
			}
			sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento()
					+ "'";
			System.out.println(sql);
			int i = executasql.executarUpdate(sql);
		}

		catch (Exception exc) {
			throw new Excecoes("Erro ao alterar retorno CTe", exc, this
					.getClass().getName(), "alteraEnvio()");
		}
	}

	public void alteraCancelamentoCTe(ConhecimentoED ed, CteEvento ret)
			throws Excecoes {

		String sql = null;

		try {

			sql = " update Conhecimentos set DM_Situacao = 'C', ";
			sql += " NM_Protocolo_Cancelamento_CTe = '"
					+ ret.getNProtRegEvento() + "' ";
			sql += " ,DT_Envio_Cancelamento_CTe = '" + (ret.getDhRegEvento())
					+ "' ";
			sql += " ,DT_Recebimento_Cancelamento_CTe = '"
					+ (ret.getDhRegEvento()) + "' ";
			sql += " ,DM_Status_Cancelamento_CTe = '" + ret.getCStat() + "' ";
			sql += " ,NM_Status_Cancelamento_CTe = '" + ret.getXMotivo() + "' ";
			sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento()
					+ "'";

			int i = executasql.executarUpdate(sql);
		}

		catch (Exception exc) {
			throw new Excecoes("Erro ao alterar cancelamento CTe", exc, this
					.getClass().getName(), "alteraEnvio()");
		}
	}

	public void incluiInutilizacaoNumero(String emissor, String xJust,
			UsuarioED user, CteInutilizacao ret) throws Excecoes {

		String sql = null;

		String oidUsuario = "0";
		if (user != null && user.getOid_Usuario().intValue() > 0)
			oidUsuario = String.valueOf(user.getOid_Usuario().intValue());

		try {

			sql = " insert into inutilizacao_cte (tpAmb,verAplic,dhRecbto,nProt,digVal,cStat,xMotivo,cUF,nCTIni,nCTFin,mod,serie,ano,emissor,xJust,oid_usuario) "
					+ " values (";
			sql += JavaUtil.getSQLString(ret.getTpAmb()) + ",";
			sql += JavaUtil.getSQLString(ret.getVerAplic()) + ",";
			sql += JavaUtil.getSQLString(ret.getDhRecbto()) + ",";
			sql += JavaUtil.getSQLString(ret.getNProt()) + ",";
			sql += JavaUtil.getSQLString(ret.getDigVal()) + ",";
			sql += JavaUtil.getSQLString(ret.getCStat()) + ",";
			sql += JavaUtil.getSQLString(ret.getXMotivo()) + ",";
			sql += JavaUtil.getSQLString(ret.getCUF()) + ",";
			sql += JavaUtil.getSQLString(ret.getNCTIni()) + ",";
			sql += JavaUtil.getSQLString(ret.getNCTFin()) + ",";
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
			throw new Excecoes("Erro ao incluir inutilizacao CTe", exc, this
					.getClass().getName(), "incluiInutilizacaoNumero()");
		}
	}

	public Cte getDadosDacte(ConhecimentoED ed) throws Excecoes {
		Cte cte = new Cte();
		String nr_regime_icms_especial_unidade = "";
		String RNTRC = "";
		try {
			String query = "Select Conhecimentos.* ,"
					+ " Conhecimentos.oid_Produto as oid_Produto_Conhecimento, "
					+ " Conhecimentos.VL_Nota_Fiscal as VL_Nota_Fiscal_CTRC, "
					+ " Unidades_Fiscal.CD_Unidade, Unidades_Fiscal.oid_pessoa as oid_Pessoa_Unidade, Unidades.oid_Unidade_Fiscal, "
					+ " Unidades_Fiscal.nr_regime_icms_especial, Unidades.RNTRC, Unidades_Fiscal.dm_tipo_cte, "
					+ " Cidade_Unidade.NM_Cidade as NM_Cidade_Unidade, "
					+ " Cidade_CTRC_Origem.NM_Cidade as NM_Cidade_CTRC_Origem, "
					+ " Cidade_CTRC_Origem.NM_Codigo_Ibge as NM_Codigo_Ibge_Origem, "
					+ " Cidade_CTRC_Origem.CD_Cidade as CD_Cidade_CTRC_Origem, "
					+ " Estado_CTRC_Origem.CD_Estado as CD_Estado_CTRC_Origem, "
					+ " Estado_CTRC_Origem.NM_Codigo_IBGE as NM_Codigo_IBGE_CTRC_Origem, "
					+ " Cidade_CTRC_Destino.NM_Cidade as NM_Cidade_CTRC_Destino, "
					+ " Cidade_CTRC_Destino.NM_Codigo_Ibge as NM_Codigo_Ibge_Destino, "
					+ " Cidade_CTRC_Destino.CD_Cidade as CD_Cidade_CTRC_Destino, "
					+ " Estado_CTRC_Destino.CD_Estado as CD_Estado_CTRC_Destino, "
					+ " Estado_CTRC_Destino.NM_Codigo_IBGE as NM_Codigo_IBGE_CTRC_Destino, "
					+ " Produtos.NM_Produto, "
					+ " Modal.CD_Modal, "
					+ " Modal.NM_Modal, "
					+ " Modal.DM_Tipo_Tabela_Frete, "
					+ " Modal.DM_Tipo_Transporte, "
					+ " Taxas.TX_Mensagem_Fiscal, "
					+ " Taxas.TX_Mensagem_Fiscal_ICMS, "
					+ " Taxas.TX_Mensagem_Fiscal_Aereo, "
					+ " Taxas.TX_Mensagem_Fiscal_ICMS_Aereo, "
					+ " Taxas.NM_Campo_ICMS, usuarios.nm_usuario "
					+ " FROM "
					+ " Conhecimentos left join usuarios on conhecimentos.oid_usuario = usuarios.oid_usuario, Unidades, Unidades Unidades_Fiscal, Modal, "
					+ " Taxas, Pessoas, "
					+ " Produtos, "
					+ " cidades Cidade_Unidade, "
					+ " cidades Cidade_CTRC_Origem, "
					+ " estados Estado_CTRC_Origem, "
					+ " regioes_estados Regiao_Estado_CTRC_Origem, "
					+ " cidades Cidade_CTRC_Destino, "
					+ " estados Estado_CTRC_Destino, "
					+ " regioes_estados Regiao_Estado_CTRC_Destino "
					+ " WHERE "
					+ " Conhecimentos.oid_Taxa = Taxas.oid_Taxa and"
					+ " Conhecimentos.oid_modal   = Modal.oid_modal and"
					+ " Conhecimentos.oid_produto = Produtos.oid_produto and"
					+ " conhecimentos.oid_cidade = Cidade_CTRC_Origem.oid_cidade and"
					+ " Conhecimentos.oid_unidade = Unidades.oid_unidade and"
					+ " Unidades_Fiscal.oid_unidade = Unidades.oid_unidade_fiscal and"
					+ " Unidades.oid_pessoa = Pessoas.oid_Pessoa and "
					+ " Pessoas.oid_cidade = Cidade_Unidade.oid_Cidade and "
					+ " Cidade_CTRC_Origem.oid_regiao_Estado = Regiao_Estado_CTRC_Origem.oid_regiao_estado and"
					+ " Regiao_Estado_CTRC_Origem.oid_Estado = Estado_CTRC_Origem.oid_Estado and"
					+ " conhecimentos.oid_cidade_Destino = Cidade_CTRC_Destino.oid_cidade and"
					+ " Cidade_CTRC_Destino.oid_regiao_Estado = Regiao_Estado_CTRC_Destino.oid_regiao_estado and"
					+ " Regiao_Estado_CTRC_Destino.oid_Estado = Estado_CTRC_Destino.oid_Estado ";

			// if(JavaUtil.doValida(ed.getOID_Conhecimento()))
			query += " AND conhecimentos.oid_conhecimento = '"
					+ ed.getOID_Conhecimento() + "'";
			// else {
			// query += " AND conhecimentos.nr_conhecimento>=" +
			// ed.getNR_Conhecimento_Inicial();
			// query += " AND conhecimentos.nr_conhecimento<=" +
			// ed.getNR_Conhecimento_Final();
			// query += " AND Unidades_Fiscal.oid_pessoa ='" +
			// ed.getOID_Pessoa() + "'";
			// }
			query += " ORDER BY conhecimentos.nr_conhecimento";

			String queryAux = "";
			// System.out.println(query);
			ResultSet res = this.executasql.executarConsulta(query);
			if (res != null) {
				while (res.next()) {
					String tpSeguro = "4";
					Empresa empresa = new EmpresaDb().getEmpresa(res
							.getString("oid_pessoa_unidade"));
					System.out.println("AMBIENTE EMPRESA"
							+ empresa.getRazaosocial() + ">>>>>>>>> "
							+ empresa.getAmbiente());
					cte.setChaveAcesso(res.getString("NM_CH_CTe"));
					cte.setProtocolo(res.getString("NM_Protocolo_Retorno_CTe"));

					double vTotTrib = 0;
					queryAux = "SELECT sum(vl_movimento) "
							+ " from movimentos_conhecimentos "
							+ " where oid_tipo_movimento in (8,9,10,36,56,62) "
							+ " and oid_conhecimento = '"
							+ res.getString("oid_Conhecimento") + "'";
					ResultSet resTrib = this.executasql
							.executarConsulta(queryAux);
					while (resTrib.next()) {
						vTotTrib = resTrib.getDouble(1);
					}

					RNTRC = res.getString("RNTRC");
					if (res.getString("nr_regime_icms_especial") != null
							&& res.getString("nr_regime_icms_especial")
									.length() > 4) {
						nr_regime_icms_especial_unidade = res
								.getString("nr_regime_icms_especial");
					}

					queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade "
							+ " FROM pessoas, cidades, regioes_estados, estados "
							+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   pessoas.oid_pessoa = '"
							+ res.getString("oid_pessoa_unidade") + "'";
					ResultSet resAux = this.executasql
							.executarConsulta(queryAux);
					String cuf = "43";
					String cmun = "0";
					String uf = "RS";
					CteEmitente emitente = new CteEmitente();
					while (resAux.next()) {
						cuf = resAux.getString("nm_codigo_ibge");
						cmun = resAux.getString("cd_cid");
						uf = resAux.getString("cd_estado");

						emitente.setXNome(resAux.getString("nm_razao_social")
								.trim());
						emitente.setXFant(resAux.getString("nm_fantasia")
								.trim());
						emitente.setCNPJ(resAux.getString("nr_cnpj_cpf"));
						emitente.setFone(StringUtil.retornaNumeroValido(resAux
								.getString("nr_telefone")));
						emitente.setIE(resAux
								.getString("nm_inscricao_estadual"));
						String endereco = resAux.getString("NM_Endereco");
						String numero = "0";
						String cpl = "";
						if (endereco.lastIndexOf(",") > 0) {
							numero = endereco.substring(endereco
									.lastIndexOf(",") + 1);
							if (numero.lastIndexOf(' ') > 0) {
								cpl = numero
										.substring(numero.lastIndexOf(" ") + 1);
							}
							endereco = endereco.substring(0, endereco
									.lastIndexOf(","));
							numero = StringUtil.retornaNumeroValido(numero);
						}
						if (!JavaUtil.doValida(numero))
							numero = JavaUtil.getValueDef(resAux
									.getString("NR_Endereco"), "0");
						if (!JavaUtil.doValida(cpl))
							cpl = JavaUtil.getValueDef(resAux
									.getString("NM_Complemento"), "");

						emitente.setXLgr(endereco.trim());
						emitente.setNro(StringUtil.retornaNumeroValido(numero));
						emitente.setXBairro(resAux.getString("nm_bairro")
								.trim());
						emitente.setXCpl(cpl);
						emitente.setCEP(resAux.getString("nr_cep"));
						emitente.setCMun(Integer.parseInt(ManipulaString
								.limpaCampo((JavaUtil.doValida(cuf) ? cuf : "")
										+ cmun)));

						emitente.setXMun(resAux.getString("nm_cidade").trim());
						emitente.setUF(resAux.getString("cd_estado"));
						emitente.setCPais(1058);
						emitente.setXPais("BRASIL");
					}
					resAux.close();

					cte.setCUF(new Integer(cuf));
					String oid_separado = StringUtil.retornaNumeroValido(res
							.getString("oid_Conhecimento"));
					cte.setCCT(new Integer(StringUtil.alinhaNumeroDireita(
							(oid_separado + "00000000").substring(0, 8), 8)));// CODIGO
					// NUMERICO
					// PARA
					// CHAVE DE ACESSO
					cte.setCFOP(new Integer(res
							.getString("cd_cfo_conhecimento")));
					cte.setNatOp("PRESTACAO DE SERVICO DE TRANSPORTE"); // ver
					// isso
					if (!res.getString("oid_Pessoa").equals(
							res.getString("oid_Pessoa_Pagador"))) {
						cte.setForPag(1);
					} else {
						cte.setForPag(0); // CIF/FOP
					}
					cte.setMod("57"); // fixo CTe
					cte.setSerie(new Integer(res.getString("nm_serie")));
					cte.setNCT(new Integer(res.getString("nr_conhecimento")));// NUMERO
					// DA
					// CTE
					String data = res.getString("dt_emissao");
					if (StringUtil.doValida(res.getString("hr_emissao")))
						data += " " + res.getString("hr_emissao") + ":"
								+ Data.getMileSegundo();
					else
						data += Data.getHoraHM() + ":" + Data.getMileSegundo();
					// data+=" GMT-0300";
					// System.out.println(">>>>"+data);
					cte.setDhEmi(StringUtil.stringToCalendar(data,
							"yyyy-MM-dd' 'hh:mm:ss", new Locale("pt", "BR"))
							.getTime());

					// System.out.println(">>>>"+FormataData.formataDataHoraBT(cte.getDhEmi()));
					cte.setTpImp(1);
					cte.setTpEmis(1);
					cte.setTpAmb(new Integer(JavaUtil.getValueDef(empresa
							.getAmbiente(), "2"))); // 1 - prod , 2 - homo

					cte.setRefCTE(null);

					cte.setTpCTe(0); // normal
					// aqui tp pelo tipo do conhecimento
					// if(JavaUtil.doValida(res.getString("dm_tipo_conhecimento"))
					// && !res.getString("dm_tipo_conhecimento").equals("1"))
					// cte.setTpCTe(1);

					String ch_original = "";
					queryAux = "select nm_ch_cte,dt_emissao from conhecimentos where oid_conhecimento='"
							+ res.getString("oid_conhecimento_original") + "'";
					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						ch_original = resAux.getString("nm_ch_cte");
					}
					resAux.close();
					if (res.getString("dm_tipo_conhecimento").equals("A")) {
						cte.setForPag(2);
						// cte.setCFOP();
						cte
								.setNatOp("ANULACAO DE VALOR RELATIVO A PREST. DE SERVICO DE TRANSPORTE");
						cte.setTpCTe(2); // anulacao
						if (JavaUtil.doValida(ch_original)) {
							// cte.setRefCTE(ch_original);

							CteInfAnu infAnu = new CteInfAnu();
							infAnu.setChCte(ch_original);
							Date dEmi = new Date();

							// Temos que ver a declaracao do tomador, tem um
							// grupo especifico

							// queryAux = " SELECT dt_emissao FROM
							// Conhecimentos_Notas_Fiscais, Notas_Fiscais "
							// + " WHERE
							// Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal =
							// Notas_Fiscais.oid_Nota_Fiscal "
							// + " AND
							// Conhecimentos_Notas_Fiscais.oid_Conhecimento = '"
							// + res.getString("oid_conhecimento") + "'";
							// resAux =
							// this.executasql.executarConsulta(queryAux);
							// while(resAux.next()){
							// dEmi = resAux.getDate("dt_emissao");
							// }
							infAnu.setDEmi(dEmi);
							cte.setInfAnu(infAnu);

						}

						// ainda faltam os dados da nota
						// ...
					}

					cte.setProcEmi(0);
					cte.setVerProc("2.00");

					cte.setCMunEnv(new Integer(cmun));// **
					cte.setXMunEnv(res.getString("nm_cidade_unidade"));// **
					cte.setUFEnv(uf);// **
					String dm_tipo_cte = JavaUtil.getValueDef(res
							.getString("DM_Tipo_CTe"), "0");
					if (JavaUtil.doValida(dm_tipo_cte)) {
						if ("2".equals(dm_tipo_cte)) {
							cte.setModal(2);// 01-Rodovi�rio; 02-A�reo;
							// 03-Aquavi�rio; 04-Ferrovi�rio;
							// 05-Dutovi�rio
						} else {
							cte.setModal(1);
						}
					} else {
						if ("A".equals(res.getString("DM_Tipo_Transporte"))) {
							cte.setModal(2);// 01-Rodovi�rio; 02-A�reo;
							// 03-Aquavi�rio; 04-Ferrovi�rio;
							// 05-Dutovi�rio
						} else {
							cte.setModal(1);
						}
					}

					if(res.getString("dm_tipo_conhecimento").equals("P")) {
						cte.setTpServ(2); // 2 - redespacho
					} else if(res.getString("dm_tipo_conhecimento").equals("H")) {
						cte.setTpServ(1); // 1 - subcontratacao
					} else {
						cte.setTpServ(0); // 0 - normal
					}

					String cufAx = res.getString("NM_Codigo_IBGE_CTRC_Origem");
					String cmunAx = res.getString("NM_Codigo_Ibge_Origem");
					cte.setCMunIni(Integer.parseInt(ManipulaString
							.limpaCampo((JavaUtil.doValida(cufAx) ? cufAx : "")
									+ cmunAx)));
					cte.setXMunIni(res.getString("NM_Cidade_CTRC_Origem"));
					cte.setUFIni(res.getString("CD_Estado_CTRC_Origem"));
					cufAx = res.getString("NM_Codigo_IBGE_CTRC_Destino");
					cmunAx = res.getString("NM_Codigo_Ibge_Destino");
					cte.setCMunFim(Integer.parseInt(ManipulaString
							.limpaCampo((JavaUtil.doValida(cufAx) ? cufAx : "")
									+ cmunAx)));
					cte.setXMunFim(res.getString("NM_Cidade_CTRC_Destino"));
					cte.setUFFim(res.getString("CD_Estado_CTRC_Destino"));
					cte.setRetira(0);
					cte.setXDetRetira("DETALHES DE RETIRADA");

					String pagador = res.getString("oid_Pessoa_Pagador");
					String oid_Grupo_Economico = "";

					queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, " +
							" cidades.nm_cidade, clientes.dm_rctr_dc, clientes.dm_rctrc, clientes.dm_emite_cte, clientes.oid_Grupo_Economico "
							+ " FROM pessoas, cidades, regioes_estados, estados, clientes "
							+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   pessoas.oid_pessoa = clientes.oid_pessoa "
							+ " AND   pessoas.oid_pessoa = '" + pagador + "'";
					resAux = this.executasql.executarConsulta(queryAux);

					CteTomador toma = new CteTomador();
					toma.setToma(0);
					if (res.getString("oid_Pessoa_Destinatario")
							.equals(pagador)) {
						toma.setToma(3);
					}
					if (res.getString("oid_Pessoa_Consignatario").equals(
							pagador)) {
						toma.setToma(4);
					}
					while (resAux.next()) {
						if(JavaUtil.doValida(resAux.getString("dm_emite_cte"))
								&& "S".equals(resAux.getString("dm_emite_cte"))
								&& cte.getTpServ() == 0
								&& toma.getToma() == 4)
							cte.setTpServ(2); // redespacho

						toma.setXNome(resAux.getString("nm_razao_social")
								.trim());
						toma.setCNPJ(resAux.getString("nr_cnpj_cpf"));
						toma.setFone(StringUtil.retornaNumeroValido(resAux
								.getString("nr_telefone")));
						toma.setIE(resAux.getString("nm_inscricao_estadual"));
						String endereco = resAux.getString("NM_Endereco");
						String numero = "0";
						String cpl = "";
						if (endereco.lastIndexOf(",") > 0) {
							numero = endereco.substring(endereco
									.lastIndexOf(",") + 1);
							if (numero.lastIndexOf(' ') > 0) {
								cpl = numero
										.substring(numero.lastIndexOf(" ") + 1);
							}
							endereco = endereco.substring(0, endereco
									.lastIndexOf(","));
							numero = StringUtil.retornaNumeroValido(numero);
						}
						if (!JavaUtil.doValida(numero))
							numero = JavaUtil.getValueDef(resAux
									.getString("NR_Endereco"), "0");
						if (!JavaUtil.doValida(cpl))
							cpl = JavaUtil.getValueDef(resAux
									.getString("NM_Complemento"), "");
						toma.setXLgr(endereco.trim());
						toma.setNro(StringUtil.retornaNumeroValido(numero));
						toma.setXBairro(resAux.getString("nm_bairro").trim());
						toma.setXCpl(cpl);
						toma.setCEP(resAux.getString("nr_cep"));

						cufAx = resAux.getString("NM_Codigo_IBGE");
						cmunAx = resAux.getString("cd_cid");
						toma.setCMun(Integer.parseInt(ManipulaString
								.limpaCampo((JavaUtil.doValida(cufAx) ? cufAx
										: "")
										+ cmunAx)));

						toma.setXMun(resAux.getString("nm_cidade").trim());
						toma.setUF(resAux.getString("cd_estado"));
						toma.setCPais(1058);
						toma.setXPais("BRASIL");

						if (JavaUtil.getValueDef(resAux.getString("dm_rctrc"),
								"N").equals("S")
								|| JavaUtil.getValueDef(
										resAux.getString("dm_rctr_dc"), "N")
										.equals("S")) {
							tpSeguro = "4";
						} else {
							if (toma.getToma().intValue() == 0)
								tpSeguro = "0";
							if (toma.getToma().intValue() == 3)
								tpSeguro = "3";
							if (toma.getToma().intValue() == 4)
								tpSeguro = "5";
						}
						
						oid_Grupo_Economico = JavaUtil.getValueDef(resAux.getString("oid_Grupo_Economico"),"0");
					}
					resAux.close();
					cte.setToma(toma);

					CteCompl compl = new CteCompl();

					String DM_Tipo_Conhecimento = res.getString("dm_tipo_conhecimento");
					String NM_xCaracAd = "TRANSPORTE";
					if ("8".equals(oid_Grupo_Economico)) { // Sara Lee
					   if ("1".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "1";
					   }else if ("F".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "5";
					   }else if ("B".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "6";
					   }else if ("Y".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "7";
					   }else if ("4".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "8";
					   }else if ("W".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "9";
					   }else if ("D".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "10";
					   }else if ("M".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "11";
					   }else if ("N".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "13";
					   }else if ("O".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "14";
					   }else if ("U".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "15";
					   }
					}
					compl.setXCaracAd(NM_xCaracAd);
//					compl.setXCaracAd("TRANSPORTE");

					compl.setXCaracSer(res.getString("CD_Modal") + "-"
							+ res.getString("NM_Modal"));
					compl.setXEmi(res.getString("nm_usuario"));
					compl.setXOrig(res.getString("CD_Estado_CTRC_Origem") + "/"
							+ res.getString("NM_Cidade_CTRC_Origem"));

					ArrayList<CtePass> listPass = new ArrayList<CtePass>();
					CtePass passagem = new CtePass();
					passagem.setXPass("PASSAG 1");
					listPass.add(passagem);
					//
					// CtePass passagem2 = new CtePass();
					// passagem2.setXPass("PASSAG 2");
					// listPass.add(passagem2);

					compl.setCtePass(listPass);

					compl.setXDest(res.getString("CD_Estado_CTRC_Destino")
							+ "/" + res.getString("NM_Cidade_CTRC_Destino"));
					compl.setXRota("ROTA");
					compl.setTpPer(0);
					compl.setTpHor(0);

					// obs contribuinte
					ArrayList<CteObs> listObsContrib = new ArrayList<CteObs>();
					String tx_observacao = "";

					if (StringUtil.doValida(res.getString("TX_Despacho"))) {
						tx_observacao += res.getString("TX_Despacho") + " ";
					}
					if (StringUtil.doValida(res.getString("TX_Observacao"))) {
						tx_observacao += res.getString("TX_Observacao") + " ";
					}
					if (StringUtil.doValida(res.getString("TX_Observacao2"))) {
						tx_observacao += res.getString("TX_Observacao2") + " ";
					}
					if (StringUtil.doValida(res.getString("TX_Observacao3"))) {
						tx_observacao += res.getString("TX_Observacao3") + " ";
					}
					// if (StringUtil
					// .doValida(res.getString("TX_Mensagem_Fiscal"))) {
					// tx_observacao += res.getString("TX_Mensagem_Fiscal")
					// + " ";
					// }

					if (cte.getModal().intValue() == 2) {
						if (res.getDouble("VL_ICMS") > 0) {
							if (StringUtil
									.doValida(res
											.getString("TX_Mensagem_Fiscal_ICMS_Aereo"))) {
								tx_observacao += res
										.getString("TX_Mensagem_Fiscal_ICMS_Aereo")
										+ " ";
							}
						} else {
							if (StringUtil.doValida(res
									.getString("TX_Mensagem_Fiscal_Aereo"))) {
								tx_observacao += res
										.getString("TX_Mensagem_Fiscal_Aereo")
										+ " ";
							}
						}

					} else {
						if (res.getDouble("VL_ICMS") > 0) {
							if (StringUtil.doValida(res
									.getString("TX_Mensagem_Fiscal_ICMS"))) {
								tx_observacao += res
										.getString("TX_Mensagem_Fiscal_ICMS")
										+ " ";
							}
						} else {
							if (StringUtil.doValida(res
									.getString("TX_Mensagem_Fiscal"))) {
								tx_observacao += res
										.getString("TX_Mensagem_Fiscal")
										+ " ";
							}
						}
					}

					tx_observacao = tx_observacao.toUpperCase();
					if (vTotTrib > 0) {
						tx_observacao += ("\n\nLei da Transpar�ncia - ");
						tx_observacao += ("O valor aproximado de tributos incidentes sobre o pre�o deste servi�o � de R$ " + new DecimalFormat(
								"#,##0.00").format(vTotTrib));
					}
					CteObs obsContrib = new CteObs();
					obsContrib.setXCampo("1");
					obsContrib.setXTexto(StringUtil.trunc(tx_observacao.trim(),
							3000));
					listObsContrib.add(obsContrib);
					compl.setObsCont(listObsContrib);

					// obs fisco
					ArrayList<CteObs> listObsFisco = new ArrayList<CteObs>();
					String obs_icms = "";
					CteObs obsFisco = new CteObs();
					obsFisco.setXCampo("2");
					obsFisco.setXTexto(obs_icms);
					// listObsFisco.add(obsContrib);
					// CteObs obsFisco2 = new CteObs();
					// obsFisco2.setXCampo("2");
					// obsFisco2.setXTexto("TEXT DA OBS 2");
					// listObsFisco.add(obsFisco2);
					compl.setObsFisco(listObsFisco);
					cte.setCompl(compl);

					cte.setEmitente(emitente);

					// remetente
					queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade "
							+ " FROM pessoas, cidades, regioes_estados, estados "
							+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   pessoas.oid_pessoa = '"
							+ res.getString("oid_pessoa") + "'";
					resAux = this.executasql.executarConsulta(queryAux);
					CteRemetente remetente = new CteRemetente();
					remetente.setCNPJ("");
					remetente.setCPF("");
					while (resAux.next()) {
						if (cte.getTpAmb() == 2) {
							remetente
									.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
							remetente
									.setXFant("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
						} else {
							remetente.setXNome(resAux
									.getString("nm_razao_social"));
							remetente.setXFant(resAux.getString("nm_fantasia"));
						}
						remetente.setCNPJ(resAux.getString("nr_cnpj_cpf"));
						remetente.setIE(resAux
								.getString("nm_inscricao_estadual"));
						String endereco = resAux.getString("NM_Endereco");
						String numero = "0";
						String cpl = "";
						if (endereco.lastIndexOf(",") > 0) {
							numero = endereco.substring(endereco
									.lastIndexOf(",") + 1);
							if (numero.lastIndexOf(' ') > 0) {
								cpl = numero
										.substring(numero.lastIndexOf(" ") + 1);
							}
							endereco = endereco.substring(0, endereco
									.lastIndexOf(","));
							numero = StringUtil.retornaNumeroValido(numero);
						}
						if (!JavaUtil.doValida(numero))
							numero = JavaUtil.getValueDef(resAux
									.getString("NR_Endereco"), "0");
						if (!JavaUtil.doValida(cpl))
							cpl = JavaUtil.getValueDef(resAux
									.getString("NM_Complemento"), "");
						remetente.setXLgr(endereco.trim());
						remetente
								.setNro(StringUtil.retornaNumeroValido(numero));
						remetente.setXBairro(resAux.getString("nm_bairro")
								.trim());
						remetente.setXCpl(cpl);
						remetente.setCEP(resAux.getString("nr_cep"));

						cufAx = resAux.getString("NM_Codigo_IBGE");
						cmunAx = resAux.getString("cd_cid");
						remetente.setCMun(Integer.parseInt(ManipulaString
								.limpaCampo((JavaUtil.doValida(cufAx) ? cufAx
										: "")
										+ cmunAx)));

						remetente.setXMun(resAux.getString("nm_cidade").trim());
						remetente.setUF(resAux.getString("cd_estado"));
						remetente.setCPais(1058);
						remetente.setXPais("BRASIL");
					}
					resAux.close();

					// INFORMACOES DE NF
					ArrayList<CteInfNF> listNf = new ArrayList<CteInfNF>();
					ArrayList<CteInfNFe> listNfe = new ArrayList<CteInfNFe>();
					ArrayList<CteInfOutros> listOutros = new ArrayList<CteInfOutros>();
					int qt_notas = 0;
					double NR_Peso = 0;
					double VL_Nota_Fiscal = 0;
					double VL_Mercadoria = 0;
					String NR_Nota_Fiscal = "";

					queryAux = " SELECT "
							+ " Notas_Fiscais.NM_Serie, "
							+ " Notas_Fiscais.DM_Transferencia, "
							+ " Notas_Fiscais.NR_Nota_Fiscal, "
							+ " Notas_Fiscais.NR_Despacho, "
							+ " Notas_Fiscais.NR_Lote, "
							+ " Notas_Fiscais.DT_Emissao, "
							+ " Notas_Fiscais.NR_Pedido, "
							+ " Notas_Fiscais.NR_Volumes, "
							+ " Notas_Fiscais.NR_Peso, "
							+ " Notas_Fiscais.VL_Nota_Fiscal, "
							+ " Notas_Fiscais.NM_Especie "
							// + ",Notas_Fiscais.nm_ch_nfe "
							+ ",'' as nm_ch_nfe "
							+ " FROM  Conhecimentos_Notas_Fiscais,  Notas_Fiscais "
							+ " WHERE Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal "
							+ " AND   Conhecimentos_Notas_Fiscais.oid_Conhecimento = '"
							+ res.getString("oid_conhecimento") + "'";
					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						qt_notas++;
						NR_Nota_Fiscal = resAux.getString("NR_Nota_Fiscal");
						NR_Peso = resAux.getDouble("NR_Peso");
						if (NR_Peso <= 0) {
							NR_Peso = 0.001;
						}
						VL_Nota_Fiscal = resAux.getDouble("VL_Nota_Fiscal");
						if (JavaUtil.doValida(resAux.getString("nm_ch_nfe"))) {
							CteInfNFe nfe = new CteInfNFe();
							nfe.setChave(resAux.getString("nm_ch_nfe"));
							listNfe.add(nfe);
						} else {
							if ("D"
									.equals(resAux
											.getString("DM_Transferencia"))) { // tipo
								// DOC
								VL_Nota_Fiscal = 0.01;
								// DOCUMENTOS SEM NOTA FISCAL
								CteInfNF nf = new CteInfNF();
								nf.setNRoma("0");
								nf.setNPed("0");
								nf.setSerie("0");
								nf.setNDoc("123");
								nf.setDEmi(resAux.getDate("DT_Emissao"));
								nf.setVBC(0d);
								nf.setVICMS(0d);
								nf.setVBCST(0d);
								nf.setVST(0d);
								nf.setVProd(0d);
								nf.setVNF(VL_Nota_Fiscal);
								nf.setNCFOP(0);
								nf.setNPeso(0.01d);
								nf.setPIN(null);
								listNf.add(nf);
							} else {
								CteInfNF nf = new CteInfNF();
								nf.setNRoma("0");
								nf.setNPed(JavaUtil.getValueDef(resAux
										.getString("NR_Pedido"), ""));
								nf.setSerie("1");
								nf.setNDoc(NR_Nota_Fiscal);
								nf.setDEmi(resAux.getDate("DT_Emissao"));
								nf.setVBC(0d);
								nf.setVICMS(0d);
								nf.setVBCST(0d);
								nf.setVST(0d);
								nf.setVProd(VL_Nota_Fiscal);
								nf.setVNF(VL_Nota_Fiscal);
								nf.setNCFOP(1101);
								nf.setNPeso(NR_Peso);
								nf.setPIN(null);
								listNf.add(nf);
							}
						}

					}
					resAux.close();
					remetente.setInfNF(listNf);
					remetente.setInfNFe(listNfe);
					remetente.setInfOutros(listOutros);
					cte.setRemetente(remetente);

					queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade "
							+ " FROM pessoas, cidades, regioes_estados, estados "
							+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   pessoas.oid_pessoa = '"
							+ res.getString("oid_Pessoa") + "'";
					// + res.getString("oid_Pessoa_Expedidor") + "'";
					resAux = this.executasql.executarConsulta(queryAux);
					CteExpedidor expedidor = new CteExpedidor();
					expedidor.setCNPJ("");
					expedidor.setCPF("");
					while (resAux.next()) {
						expedidor.setXNome(resAux.getString("nm_razao_social")
								.trim());
						expedidor.setFone(resAux.getString("nr_telefone"));
						expedidor.setCNPJ(resAux.getString("nr_cnpj_cpf"));
						expedidor.setIE(resAux
								.getString("nm_inscricao_estadual"));
						String endereco = resAux.getString("NM_Endereco");
						String numero = "0";
						String cpl = "";
						if (endereco.lastIndexOf(",") > 0) {
							numero = endereco.substring(endereco
									.lastIndexOf(",") + 1);
							if (numero.lastIndexOf(' ') > 0) {
								cpl = numero
										.substring(numero.lastIndexOf(" ") + 1);
							}
							endereco = endereco.substring(0, endereco
									.lastIndexOf(","));
							numero = StringUtil.retornaNumeroValido(numero);
						}
						if (!JavaUtil.doValida(numero))
							numero = JavaUtil.getValueDef(resAux
									.getString("NR_Endereco"), "0");
						if (!JavaUtil.doValida(cpl))
							cpl = JavaUtil.getValueDef(resAux
									.getString("NM_Complemento"), "");
						expedidor.setXLgr(endereco.trim());
						expedidor
								.setNro(StringUtil.retornaNumeroValido(numero));
						expedidor.setXBairro(resAux.getString("nm_bairro")
								.trim());
						expedidor.setXCpl(cpl);
						expedidor.setCEP(resAux.getString("nr_cep"));

						cufAx = resAux.getString("NM_Codigo_IBGE");
						cmunAx = resAux.getString("cd_cid");
						expedidor.setCMun(Integer.parseInt(ManipulaString
								.limpaCampo((JavaUtil.doValida(cufAx) ? cufAx
										: "")
										+ cmunAx)));

						expedidor.setXMun(resAux.getString("nm_cidade").trim());
						expedidor.setUF(resAux.getString("cd_estado"));
						expedidor.setCPais(1058);
						expedidor.setXPais("BRASIL");
					}
					resAux.close();
					if (JavaUtil.doValida(expedidor.getCNPJ()))
						cte.setExpedidor(expedidor);

					String pesRec = res.getString("oid_Pessoa_Destinatario");
					if (JavaUtil.doValida(res
							.getString("oid_Pessoa_Consignatario")))
						pesRec = res.getString("oid_Pessoa_Consignatario");

					queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade "
							+ " FROM pessoas, cidades, regioes_estados, estados "
							+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   pessoas.oid_pessoa = '"
							// + res.getString("oid_Pessoa_Destinatario")
							+ pesRec + "'";
					// + res.getString("oid_Pessoa_Redespacho") + "'";
					resAux = this.executasql.executarConsulta(queryAux);
					CteRecebedor recebedor = new CteRecebedor();
					recebedor.setCNPJ("");
					recebedor.setCPF("");
					while (resAux.next()) {
						recebedor.setXNome(resAux.getString("nm_razao_social")
								.trim());
						recebedor.setFone(resAux.getString("nr_telefone"));
						recebedor.setCNPJ(resAux.getString("nr_cnpj_cpf"));
						recebedor.setIE(resAux
								.getString("nm_inscricao_estadual"));
						String endereco = resAux.getString("NM_Endereco");
						String numero = "0";
						String cpl = "";
						if (endereco.lastIndexOf(",") > 0) {
							numero = endereco.substring(endereco
									.lastIndexOf(",") + 1);
							if (numero.lastIndexOf(' ') > 0) {
								cpl = numero
										.substring(numero.lastIndexOf(" ") + 1);
							}
							endereco = endereco.substring(0, endereco
									.lastIndexOf(","));
							numero = StringUtil.retornaNumeroValido(numero);
						}
						if (!JavaUtil.doValida(numero))
							numero = JavaUtil.getValueDef(resAux
									.getString("NR_Endereco"), "0");
						if (!JavaUtil.doValida(cpl))
							cpl = JavaUtil.getValueDef(resAux
									.getString("NM_Complemento"), "");
						recebedor.setXLgr(endereco.trim());
						recebedor
								.setNro(StringUtil.retornaNumeroValido(numero));
						recebedor.setXBairro(resAux.getString("nm_bairro")
								.trim());
						recebedor.setXCpl(cpl);
						recebedor.setCEP(resAux.getString("nr_cep"));

						cufAx = resAux.getString("NM_Codigo_IBGE");
						cmunAx = resAux.getString("cd_cid");
						recebedor.setCMun(Integer.parseInt(ManipulaString
								.limpaCampo((JavaUtil.doValida(cufAx) ? cufAx
										: "")
										+ cmunAx)));

						recebedor.setXMun(resAux.getString("nm_cidade").trim());
						recebedor.setUF(resAux.getString("cd_estado"));
						recebedor.setCPais(1058);
						recebedor.setXPais("BRASIL");
					}
					resAux.close();
					if (JavaUtil.doValida(recebedor.getCNPJ()))
						cte.setRecebedor(recebedor);

					queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade "
							+ " FROM pessoas, cidades, regioes_estados, estados "
							+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   pessoas.oid_pessoa = '"
							+ res.getString("oid_Pessoa_Destinatario") + "'";
					resAux = this.executasql.executarConsulta(queryAux);
					CteDestinatario destinatario = new CteDestinatario();
					destinatario.setCNPJ("");
					destinatario.setCPF("");
					while (resAux.next()) {
						destinatario.setXNome(resAux.getString(
								"nm_razao_social").trim());
						destinatario.setFone(resAux.getString("nr_telefone"));
						destinatario.setCNPJ(resAux.getString("nr_cnpj_cpf"));
						destinatario.setIE(resAux
								.getString("nm_inscricao_estadual"));
						String endereco = resAux.getString("NM_Endereco");
						String numero = "0";
						String cpl = "";
						if (endereco.lastIndexOf(",") > 0) {
							numero = endereco.substring(endereco
									.lastIndexOf(",") + 1);
							if (numero.lastIndexOf(' ') > 0) {
								cpl = numero
										.substring(numero.lastIndexOf(" ") + 1);
							}
							endereco = endereco.substring(0, endereco
									.lastIndexOf(","));
							numero = StringUtil.retornaNumeroValido(numero);
						}
						if (!JavaUtil.doValida(numero))
							numero = JavaUtil.getValueDef(resAux
									.getString("NR_Endereco"), "0");
						if (!JavaUtil.doValida(cpl))
							cpl = JavaUtil.getValueDef(resAux
									.getString("NM_Complemento"), "");
						destinatario.setXLgr(endereco.trim());
						destinatario.setNro(StringUtil
								.retornaNumeroValido(numero));
						destinatario.setXBairro(resAux.getString("nm_bairro")
								.trim());
						destinatario.setXCpl(cpl);
						destinatario.setCEP(resAux.getString("nr_cep"));

						cufAx = resAux.getString("NM_Codigo_IBGE");
						cmunAx = resAux.getString("cd_cid");
						destinatario.setCMun(Integer.parseInt(ManipulaString
								.limpaCampo((JavaUtil.doValida(cufAx) ? cufAx
										: "")
										+ cmunAx)));

						destinatario.setXMun(resAux.getString("nm_cidade")
								.trim());
						destinatario.setUF(resAux.getString("cd_estado"));
						destinatario.setCPais(1058);
						destinatario.setXPais("BRASIL");
					}
					resAux.close();
					cte.setDestinatario(destinatario);

					cte.setVTPrest(res.getDouble("VL_TOTAL_FRETE"));
					cte.setVRec(res.getDouble("VL_TOTAL_FRETE"));
					ArrayList<CteCompPrestacaoServico> listCompPrestacaoServico = new ArrayList<CteCompPrestacaoServico>();
					queryAux = " SELECT "
							+ " Tipos_Movimentos.NM_Tipo_Movimento, "
							+ " Movimentos_Conhecimentos.VL_Movimento "
							+ " FROM  Tipos_Movimentos,  Movimentos_Conhecimentos "
							+ " WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento "
							+ " AND   Tipos_Movimentos.DM_Tipo_Movimento <> 'D' "
							+ " AND   Tipos_Movimentos.DM_Calcula_Frete = 'S' "
							+ " AND   Movimentos_Conhecimentos.oid_Conhecimento = '"
							+ res.getString("oid_conhecimento") + "'";

					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						CteCompPrestacaoServico comp = new CteCompPrestacaoServico();
						comp.setXNome(resAux.getString("NM_Tipo_Movimento"));
						comp.setVComp(resAux.getDouble("VL_Movimento"));
						listCompPrestacaoServico.add(comp);
					}
					cte.setCompPrestacaoServico(listCompPrestacaoServico);

					// CteImposto imp = new CteImposto();
					// String cst = res.getString("DM_CST");
					// if(StringUtil.doValida(cst)){
					// try{
					// imp.setCST(new Integer(cst));
					// } catch (Exception e){
					//
					// }
					// } else {
					// imp.setCST(0);
					// }
					// if (1==1 || res.getDouble("VL_ICMS")>0){
					// // imp.setCST(0);
					// imp.setPICMS(res.getDouble("PE_ALIQUOTA_ICMS"));
					// imp.setPRedBC(0d);
					// imp.setVBC(res.getDouble("VL_TOTAL_FRETE"));
					// imp.setVICMS(res.getDouble("VL_ICMS"));
					// imp.setVCred(0d);
					// }

					CteImposto imp = new CteImposto();
					if (1 == 1 || res.getDouble("VL_ICMS") > 0) {
						imp.setCST(0);

						if (res.getDouble("VL_ICMS") > 0)
							imp.setPICMS(res.getDouble("PE_ALIQUOTA_ICMS"));
						else
							imp.setPICMS(0d);

						imp.setPRedBC(0d);
						imp.setVBC(res.getDouble("VL_TOTAL_FRETE"));
						imp.setVICMS(res.getDouble("VL_ICMS"));
						imp.setVCred(0d);
					}

//					if (res.getDouble("VL_ICMS") <= 0) {
//						imp.setCST(40);
//						imp.setVBC(0d);
//					}

					cte.setImp(imp);
					resAux.close();

					CteInfCTeNorm cteInfNor = new CteInfCTeNorm();
					cteInfNor.setProPred(res.getString("NM_Produto"));
					cteInfNor.setXOutCat("M.T.R.: "
							+ res.getString("NM_Liberacao_veiculo"));
					cteInfNor.setVCarga(res.getDouble("vl_nota_fiscal"));// **

					ArrayList<CteInfQCarga> listInfQ = new ArrayList<CteInfQCarga>();
					CteInfQCarga infq = new CteInfQCarga();
					infq.setCUnid(0);
					infq.setTpMed("VOLUMES (UN)");
					infq.setQCarga(res.getDouble("NR_VOLUMES"));
					listInfQ.add(infq);
					infq = new CteInfQCarga();
					infq.setCUnid(0);
					infq.setTpMed("PESO (KG)");
					infq.setQCarga(res.getDouble("NR_PESO"));
					listInfQ.add(infq);
					infq = new CteInfQCarga();
					infq.setCUnid(0);
					infq.setTpMed("PESO CUBADO (KG)");
					infq.setQCarga(res.getDouble("NR_PESO_CUBADO"));
					listInfQ.add(infq);
					infq = new CteInfQCarga();
					infq.setCUnid(0);
					infq.setTpMed("CUBAGEM (M3)");
					infq.setQCarga(res.getDouble("NR_CUBAGEM"));
					listInfQ.add(infq);
					cteInfNor.setInfQ(listInfQ);

					ArrayList<CteContQt> listContainer = new ArrayList<CteContQt>();
					CteContQt container = new CteContQt();
					// container.setNCont(1234);
					// container.setDPrev(new Date());
					listContainer.add(container);
					cteInfNor.setContQt(listContainer);

					ArrayList<CteDocAnt> listDocAnt = new ArrayList<CteDocAnt>();
					CteDocAnt docAnt = new CteDocAnt();
					if(cte.getTpServ() > 0) {
						ArrayList<CteIdDocAnt> idsAnt = new ArrayList<CteIdDocAnt>();
						//Cte relacionado
						CteIdDocAnt idAnt = new CteIdDocAnt();
						queryAux = "Select PESSOAS.*, estados.cd_estado "
								+ " FROM pessoas, cidades, regioes_estados, estados "
								+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
								+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
								+ " AND   regioes_estados.oid_estado = estados.oid_estado "
								+ " AND   pessoas.oid_pessoa = '"
								+ pagador + "'";
						resAux = this.executasql.executarConsulta(queryAux);
						if(resAux.next()) {
							if (resAux.getString("nr_cnpj_cpf").length() < 14) {
								docAnt
										.setCPF(resAux.getString("nr_cnpj_cpf"));
							} else {
								docAnt.setCNPJ(resAux
										.getString("nr_cnpj_cpf"));
							}
							if ("0".equals(resAux
									.getString("nm_inscricao_estadual")))
								docAnt.setIE("ISENTO");
							else
								docAnt.setIE(resAux.getString(
										"nm_inscricao_estadual").trim());
							docAnt.setUF(JavaUtil.getValueDef(resAux.getString("cd_estado"), ""));
						}
						//chave
						idAnt.setChave(res.getString("nm_ch_cte_anterior"));
						idsAnt.add(idAnt);
						docAnt.setIdDocAnt(idsAnt);

						listDocAnt.add(docAnt);
						cteInfNor.setDocAnt(listDocAnt);
					} else {
						listDocAnt.add(docAnt);
//						cteInfNor.setDocAnt(listDocAnt);
					}
					// docAnt.setCNPJ("91665554000163");
					// docAnt.setIE("4000000763");
					// docAnt.setXNome("NOME EMITENTE DOC ANTERIOR");
					// docAnt.setUF("RS");
					//
					// ArrayList<CteIdDocAnt> listIdDocAnt = new
					// ArrayList<CteIdDocAnt>();
					// CteIdDocAnt idDocAnt = new CteIdDocAnt();
					// idDocAnt.setChave("43100708693058000170570010000000020001011065");
					// listIdDocAnt.add(idDocAnt);
					//
					// CteIdDocAnt idDocAnt2 = new CteIdDocAnt();
					// idDocAnt2.setNDoc(1);
					// idDocAnt2.setSerie("0");
					// idDocAnt2.setTpDoc(4);
					// idDocAnt2.setDEmi(new Date());
					// listIdDocAnt.add(idDocAnt2);
					//
					// docAnt.setIdDocAnt(listIdDocAnt);
					// listDocAnt.add(docAnt);
					// cteInfNor.setDocAnt(listDocAnt);

					ArrayList<CteSeg> listSeg = new ArrayList<CteSeg>();
					queryAux = " SELECT Clientes.NM_Seguradora, Clientes.NR_Apolice "
							+ " FROM   Clientes "
							+ " WHERE  Clientes.NM_Seguradora IS NOT NULL "
							+ " AND    Clientes.NR_Apolice IS NOT NULL "
							+ " AND    Clientes.oid_Pessoa = '"
							+ res.getString("oid_pessoa_unidade") + "'"; // seguro
																			// no
																			// cad
																			// cliente
																			// da
																			// unidade
					// + " AND Clientes.oid_Pessoa = '" +
					// res.getString("oid_Pessoa") + "'";

					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						CteSeg seg = new CteSeg();
						seg.setNApol(resAux.getString("NR_Apolice"));
						seg.setXSeg(resAux.getString("NM_Seguradora"));
						seg.setRespSeg(new Integer(tpSeguro));
						seg.setVCarga(res.getDouble("vl_nota_fiscal"));// **
						listSeg.add(seg);
					}
					if (listSeg.size() == 0) {
						CteSeg seg = new CteSeg();
						// seg.setNApol(resAux.getString("NR_Apolice"));
						// seg.setXSeg(resAux.getString("NM_Seguradora"));
						seg.setRespSeg(new Integer(tpSeguro));
						// seg.setVCarga(res.getDouble("vl_nota_fiscal"));//**
						listSeg.add(seg);
					}
					cteInfNor.setSeg(listSeg);
					resAux.close();

					// RODOVIARIO

					CteRodo rodo = new CteRodo();
					rodo.setLota(0);

					String data2 = res.getString("DT_Previsao_Entrega");
					if (StringUtil.doValida(res
							.getString("HR_Previsao_Entrega")))
						data2 += " " + res.getString("HR_Previsao_Entrega")
								+ ":00";
					else
						data2 += " 00:00:00";
					// data+=" GMT-0300";
					System.out.println(">>>>" + data2);
					rodo.setdPrev(StringUtil.stringToCalendar(data2,
							"yyyy-MM-dd' 'hh:mm:ss", new Locale("pt", "BR"))
							.getTime());
					// rodo.setdPrev(res.getDate("DT_Previsao_Entrega"));
					rodo.setRNTRC(RNTRC);// RNTRC 8 digitos

					// ArrayList<CteOcc> listOcc = new ArrayList<CteOcc>();
					// CteOcc occ = new CteOcc();
					// occ.setSerie("123");
					// occ.setNOcc(128);//N�mero da Ordem de coleta
					// occ.setDEmi(new Date());//Data de emiss�o da ordem de
					// coleta
					//
					// occ.setCInt("145875");
					// occ.setCNPJ("91.665.554/0001-63");
					// occ.setIE("4000000763");
					// occ.setUF("RS");
					// occ.setFone("5137141234");//fone
					// listOcc.add(occ);
					// rodo.setOcc(listOcc);

					// ArrayList<CteValePed> listValePed = new
					// ArrayList<CteValePed>();
					// CteValePed valePed = new CteValePed();
					// valePed.setCNPJForn("91.665.554/0001-63");
					// valePed.setnCompra(1234);//N�mero do comprovante de
					// compra
					// valePed.setCNPJPg("91.665.554/0001-63");
					// listValePed.add(valePed);
					// valePed.setNroRE("12345");
					// valePed.setRespPg(0);
					// valePed.setVTValePed(100d);
					// ArrayList<CteDisp> listDisp = new ArrayList<CteDisp>();
					// CteDisp disp = new CteDisp();
					// disp.setXEmp("NOEM DA EMPRESA DISP");
					// disp.setTpDisp(1);
					// disp.setDVig(new Date());
					// listDisp.add(disp);
					// valePed.setDisp(listDisp);
					// rodo.setValePed(listValePed);

					ArrayList<CteVeic> listVeic = new ArrayList<CteVeic>();

					queryAux = " SELECT Veiculos.nr_placa "
							+ " FROM   Veiculos "
							+ " WHERE  Veiculos.oid_Veiculo = '"
							+ res.getString("oid_Veiculo") + "'";

					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						CteVeic veic = new CteVeic();
						veic.setUF("");
						veic.setCapKG(1000);
						veic.setCapM3(100);
						veic.setRENAVAM("");
						veic.setPlaca(resAux.getString("nr_placa"));
						veic.setTara(0);
						veic.setTpCar(0);
						veic.setTpProp("T");
						veic.setTpVeic(0); // 0 - cavalo
						veic.setTpRod(0);
						// prop
						CteProp prop = new CteProp();
						prop.setCNPJ("");
						prop.setXNome("NOME TESTE PROP");
						prop.setUF("");
						prop.setRNTRC("");
						prop.setIE("");
						prop.setTpProp(1);
						veic.setProp(prop);
						listVeic.add(veic);
					}
					resAux.close();
					queryAux = " SELECT Veiculos.nr_placa "
							+ " FROM   Veiculos "
							+ " WHERE  Veiculos.oid_Veiculo = '"
							+ res.getString("oid_Carreta") + "'";

					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						CteVeic veic = new CteVeic();
						veic.setUF("");
						veic.setCapKG(1000);
						veic.setCapM3(100);
						veic.setRENAVAM("");
						veic.setPlaca(resAux.getString("nr_placa"));
						veic.setTara(0);
						veic.setTpCar(0);
						veic.setTpProp("T");
						veic.setTpVeic(2); // 1 - carreta
						veic.setTpRod(0);
						// prop
						CteProp prop = new CteProp();
						prop.setCNPJ("");
						prop.setXNome("NOME TESTE PROP");
						prop.setUF("");
						prop.setRNTRC("");
						prop.setIE("");
						prop.setTpProp(1);
						veic.setProp(prop);
						listVeic.add(veic);
					}
					resAux.close();

					rodo.setVeic(listVeic);

					// ArrayList<CteLacRodo> listLacRodo = new
					// ArrayList<CteLacRodo>();
					// CteLacRodo lacRodo = new CteLacRodo();
					// lacRodo.setNLacre("1");
					// listLacRodo.add(lacRodo);
					// CteLacRodo lacRodo2 = new CteLacRodo();
					// lacRodo2.setNLacre("2");
					// listLacRodo.add(lacRodo2);
					// rodo.setLacRodo(listLacRodo);

					ArrayList<CteMoto> listMoto = new ArrayList<CteMoto>();
					queryAux = " SELECT Pessoas.* " + " FROM   Pessoas "
							+ " WHERE  Pessoas.oid_Pessoa = '"
							+ res.getString("oid_motorista") + "'";

					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						CteMoto moto = new CteMoto();
						// moto.setCPF(resAux.getString("nr_cnpj_cpf"));
						// moto.setxNome(resAux.getString("nm_razao_social")
						// .trim());
						listMoto.add(moto);
					}
					resAux.close();
					rodo.setMoto(listMoto);
					cteInfNor.setRodo(rodo);

					// AEREO

					// CteAereo aereo = new CteAereo();
					// aereo.setCIATA("00323324000160");
					// aereo.setNMinu(new
					// Integer(res.getString("nr_conhecimento")));
					// aereo.setXLAgEmi("AZUL LINHAS AEREAS B");
					// aereo.setDPrev(res.getDate("DT_Previsao_Entrega"));
					// aereo.setCL("M");
					// aereo.setCTar("001");
					// aereo.setVTar(0d);
					// aereo.setNOCA("00056996311055");
					// aereo.setcInfManu("99");
					// aereo.setxDime("1X1X1");
					// cteInfNor.setAereo(aereo);

					// CteAquav aquav = new CteAquav();
					// aquav.setDirec("O");
					// aquav.setIrin("1");
					// aquav.setNBooking("NB222");
					// aquav.setNCtrl("NCon");
					// aquav.setNViag("1");
					// aquav.setPrtDest("PORT DEST");
					// aquav.setPrtEmb("POR EMB");
					// aquav.setPrtTrans("PRT TRA");
					// aquav.setTpNav(1);
					// aquav.setVAFRMM(100.55);
					// aquav.setXNavio("NOME NAV");
					// aquav.setVPrest(100d);
					// cteInfNor.setAquav(aquav);

					// ** INF MODAL
					CteInfModal infmodal = new CteInfModal();// **
					infmodal.setVersaoModal("2.00");// **
					infmodal.setXsany("CTeModalRodoviario_v2.00");// **
					cte.setInfModal(infmodal);

					ArrayList<CtePeri> listPeri = new ArrayList<CtePeri>();
					// CtePeri peri = new CtePeri();
					// peri.setNONU("1");
					// peri.setPontoFulgor("1");
					// peri.setQTotProd("100");
					// peri.setXClaRisco("1");
					// peri.setXNomeAE("Nome AE");
					// peri.setqVolTipo("100");
					// listPeri.add(peri);
					cteInfNor.setPeri(listPeri);

					// ArrayList<CteVeicNovos> listVeicNovos = new
					// ArrayList<CteVeicNovos>();
					// CteVeicNovos veicNovos = new CteVeicNovos();
					// veicNovos.setChassi("22222222222222222");
					// veicNovos.setCCor("1");
					// veicNovos.setXCor("COR");
					// veicNovos.setVFrete(1000d);
					// veicNovos.setVUnit(1000d);
					// veicNovos.setCMod("1");
					// listVeicNovos.add(veicNovos);
					// cteInfNor.setVeicNovos(listVeicNovos);

					cte.setInfCTeNorm(cteInfNor);

					// CteInfCteComp infCteComp = new CteInfCteComp();
					// infCteComp.setChave("43100708693058000170570010000000020001011065");
					// infCteComp.setVTPrest(150.33);
					//
					// ArrayList<CteCompComp> listCompComp = new
					// ArrayList<CteCompComp>();
					// CteCompComp compComp = new CteCompComp();
					// compComp.setVComp(333d);
					// compComp.setXNome("NOME COMP");
					// listCompComp.add(compComp);
					// infCteComp.setCompComp(listCompComp);
					//
					// CteImposto impComp = new CteImposto();
					// impComp.setCST(0);
					// impComp.setPICMS(17d);
					// impComp.setPRedBC(5d);
					// impComp.setVBC(100d);
					// impComp.setVICMS(17d);
					// impComp.setVCred(5d);
					// infCteComp.setImpComp(impComp);
					// Enviacte.setInfCTeComp(infCteComp);
				}
			}
			res.close();

		} catch (Exception exc) {
			exc.printStackTrace();
			throw new Excecoes("Erro ao buscar dados CTe", exc, this.getClass()
					.getName(), "getDadosDacte()");
		}
		return cte;
	}

	public Cte getDadosEnvio(ConhecimentoED ed) throws Excecoes {
		Cte cte = new Cte();
		String nr_regime_icms_especial_unidade = "";
		String RNTRC = "";
		boolean substituicao = false;
		try {
			String query = "Select Conhecimentos.* ,"
					+ " Conhecimentos.oid_Produto as oid_Produto_Conhecimento, "
					+ " Conhecimentos.VL_Nota_Fiscal as VL_Nota_Fiscal_CTRC, "
					+ " Unidades_Fiscal.CD_Unidade, Unidades_Fiscal.oid_pessoa as oid_Pessoa_Unidade, Unidades.oid_Unidade_Fiscal, "
					+ " Unidades_Fiscal.nr_regime_icms_especial, Unidades.RNTRC, Unidades_Fiscal.dm_tipo_cte, "
					+ " Cidade_Unidade.NM_Cidade as NM_Cidade_Unidade, "
					+ " Cidade_CTRC_Origem.NM_Cidade as NM_Cidade_CTRC_Origem, "
					+ " Cidade_CTRC_Origem.NM_Codigo_Ibge as NM_Codigo_Ibge_Origem, "
					+ " Cidade_CTRC_Origem.CD_Cidade as CD_Cidade_CTRC_Origem, "
					+ " Estado_CTRC_Origem.CD_Estado as CD_Estado_CTRC_Origem, "
					+ " Estado_CTRC_Origem.NM_Codigo_IBGE as NM_Codigo_IBGE_CTRC_Origem, "
					+ " Cidade_CTRC_Destino.NM_Cidade as NM_Cidade_CTRC_Destino, "
					+ " Cidade_CTRC_Destino.NM_Codigo_Ibge as NM_Codigo_Ibge_Destino, "
					+ " Cidade_CTRC_Destino.CD_Cidade as CD_Cidade_CTRC_Destino, "
					+ " Estado_CTRC_Destino.CD_Estado as CD_Estado_CTRC_Destino, "
					+ " Estado_CTRC_Destino.NM_Codigo_IBGE as NM_Codigo_IBGE_CTRC_Destino, "
					+ " Produtos.NM_Produto, "
					+ " Modal.CD_Modal, "
					+ " Modal.NM_Modal, "
					+ " Modal.DM_Tipo_Tabela_Frete, "
					+ " Modal.DM_Tipo_Transporte, "
					+ " Taxas.TX_Mensagem_Fiscal, "
					+ " Taxas.TX_Mensagem_Fiscal_ICMS, "
					+ " Taxas.TX_Mensagem_Fiscal_Aereo, "
					+ " Taxas.TX_Mensagem_Fiscal_ICMS_Aereo, "
					+ " Taxas.NM_Campo_ICMS, usuarios.nm_usuario "
					+ " FROM "
					+ " Conhecimentos left join usuarios on conhecimentos.oid_usuario = usuarios.oid_usuario, Unidades, Unidades Unidades_Fiscal, Modal, "
					+ " Taxas, Pessoas, "
					+ " Produtos, "
					+ " cidades Cidade_Unidade, "
					+ " cidades Cidade_CTRC_Origem, "
					+ " estados Estado_CTRC_Origem, "
					+ " regioes_estados Regiao_Estado_CTRC_Origem, "
					+ " cidades Cidade_CTRC_Destino, "
					+ " estados Estado_CTRC_Destino, "
					+ " regioes_estados Regiao_Estado_CTRC_Destino "
					+ " WHERE "
					+ " Conhecimentos.oid_Taxa = Taxas.oid_Taxa and"
					+ " Conhecimentos.oid_modal   = Modal.oid_modal and"
					+ " Conhecimentos.oid_produto = Produtos.oid_produto and"
					+ " conhecimentos.oid_cidade = Cidade_CTRC_Origem.oid_cidade and"
					+ " Conhecimentos.oid_unidade = Unidades.oid_unidade and"
					+ " Unidades_Fiscal.oid_unidade = Unidades.oid_unidade_fiscal and"
					+ " Unidades.oid_pessoa = Pessoas.oid_Pessoa and "
					+ " Pessoas.oid_cidade = Cidade_Unidade.oid_Cidade and "
					+ " Cidade_CTRC_Origem.oid_regiao_Estado = Regiao_Estado_CTRC_Origem.oid_regiao_estado and"
					+ " Regiao_Estado_CTRC_Origem.oid_Estado = Estado_CTRC_Origem.oid_Estado and"
					+ " conhecimentos.oid_cidade_Destino = Cidade_CTRC_Destino.oid_cidade and"
					+ " Cidade_CTRC_Destino.oid_regiao_Estado = Regiao_Estado_CTRC_Destino.oid_regiao_estado and"
					+ " Regiao_Estado_CTRC_Destino.oid_Estado = Estado_CTRC_Destino.oid_Estado "
					+ " AND (DM_Impresso != 'S' OR (DM_Impresso = 'S' AND DM_Status_CTe='204'))";
			query += " AND conhecimentos.oid_conhecimento = '"
					+ ed.getOID_Conhecimento() + "'";
			query += " ORDER BY conhecimentos.nr_conhecimento";

			String queryAux = "";
			System.out.println(query);
			ResultSet res = this.executasql.executarConsulta(query);
			if (res != null) {
				while (res.next()) {

					String tpSeguro = "4";
					Empresa empresa = new EmpresaDb().getEmpresa(res
							.getString("oid_pessoa_unidade"));
					System.out.println("AMBIENTE EMPRESA "
							+ empresa.getRazaosocial() + " >>>>>>>>> "
							+ empresa.getAmbiente());
					cte.setChaveAcesso(res.getString("NM_CH_CTe"));
					cte.setProtocolo(res.getString("NM_Protocolo_Retorno_CTe"));

					if ("204".equals(res.getString("DM_Status_CTe"))) {
						if (res.getString("NM_Status_CTe").indexOf("nRec:") > 0) {
							String protEnvio = res.getString("NM_Status_CTe")
									.substring(
											res.getString("NM_Status_CTe")
													.indexOf("nRec:") + 5,
											(res.getString("NM_Status_CTe")
													.indexOf("nRec:") + 20));
							System.out.println("prot NOVO:" + protEnvio);
							cte.setCancelNProt(protEnvio);
						} else {
							cte.setCancelNProt(res
									.getString("NM_Protocolo_Envio_CTe"));
						}
					} else {
						cte.setCancelNProt(res
								.getString("NM_Protocolo_Envio_CTe"));
					}

					double vTotTrib = 0;
					queryAux = "SELECT sum(vl_movimento) "
							+ " from movimentos_conhecimentos "
							+ " where oid_tipo_movimento in (8,9,10,36,56,62) "
							+ " and oid_conhecimento = '"
							+ res.getString("oid_Conhecimento") + "'";
					ResultSet resTrib = this.executasql
							.executarConsulta(queryAux);
					while (resTrib.next()) {
						vTotTrib = resTrib.getDouble(1);
					}

					RNTRC = res.getString("RNTRC");
					if (!JavaUtil.doValida(RNTRC)) {
						RNTRC = "12345678";
					}
					if (res.getString("nr_regime_icms_especial") != null
							&& res.getString("nr_regime_icms_especial")
									.length() > 4) {
						nr_regime_icms_especial_unidade = res
								.getString("nr_regime_icms_especial");
					}

					queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade "
							+ " FROM pessoas, cidades, regioes_estados, estados "
							+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   pessoas.oid_pessoa = '"
							+ res.getString("oid_pessoa_unidade") + "'";
					ResultSet resAux = this.executasql
							.executarConsulta(queryAux);
					String cuf = "43";
					String cmun = "0";
					String uf = "RS";
					CteEmitente emitente = new CteEmitente();
					while (resAux.next()) {
						cuf = resAux.getString("nm_codigo_ibge");
						cmun = resAux.getString("cd_cid");
						uf = resAux.getString("cd_estado");

						emitente.setXNome(resAux.getString("nm_razao_social")
								.trim());
						emitente.setXFant(resAux.getString("nm_razao_social")
								.trim());
						// emitente.setXFant(resAux.getString("nm_fantasia")
						// .trim());
						emitente.setCNPJ(resAux.getString("nr_cnpj_cpf"));
						emitente.setFone(StringUtil.retornaNumeroValido(resAux
								.getString("nr_telefone"), 12));
						emitente.setIE(resAux
								.getString("nm_inscricao_estadual"));
						if (!JavaUtil.doValida(resAux
								.getString("nm_inscricao_estadual"))
								&& !"0".equals(resAux
										.getString("nm_inscricao_estadual")))
							throw new Excecoes("IE do Emissor � inv�lida!");
						String endereco = resAux.getString("NM_Endereco");
						String numero = "0";
						String cpl = "";
						if (endereco.lastIndexOf(",") > 0) {
							numero = endereco.substring(endereco
									.lastIndexOf(",") + 1);
							if (numero.lastIndexOf(' ') > 0) {
								cpl = numero
										.substring(numero.lastIndexOf(" ") + 1);
							}
							endereco = endereco.substring(0, endereco
									.lastIndexOf(","));
							numero = StringUtil.retornaNumeroValido(numero);
						}
						if (!JavaUtil.doValida(numero))
							numero = JavaUtil.getValueDef(resAux
									.getString("NR_Endereco"), "0");
						if (!JavaUtil.doValida(cpl))
							cpl = JavaUtil.getValueDef(resAux
									.getString("NM_Complemento"), "");

						emitente.setXLgr(endereco.trim());
						emitente.setNro(StringUtil.retornaNumeroValido(numero));
						emitente.setXBairro(ManipulaString.corrigeString(
								resAux.getString("nm_bairro")).trim());
						emitente.setXCpl(cpl);
						emitente.setCEP(StringUtil.retornaNumeroValido(resAux
								.getString("nr_cep")));

						if (!JavaUtil.doValida(cmun))
							throw new Excecoes(
									"C�D. IBGE Munic�pio do Emissor inv�lido!");

						emitente.setCMun(Integer.parseInt(ManipulaString
								.limpaCampo((JavaUtil.doValida(cuf) ? cuf : "")
										+ cmun)));

						if (StringUtil.retornaNumeroValido(
								resAux.getString("nr_cep")).length() != 8)
							throw new Excecoes("CEP do Emissor inv�lido!");
						if (String.valueOf(emitente.getCMun().intValue())
								.length() != 7)
							throw new Excecoes(
									"C�D. IBGE Munic�pio do Emissor inv�lido!");

						emitente.setXMun(resAux.getString("nm_cidade").trim());
						emitente.setUF(resAux.getString("cd_estado"));
						emitente.setCPais(1058);
						emitente.setXPais("BRASIL");
					}
					resAux.close();

					cte.setCUF(new Integer(cuf));
					String oid_separado = StringUtil.retornaNumeroValido(res
							.getString("oid_Conhecimento"));
					cte.setCCT(new Integer(StringUtil.alinhaNumeroDireita(
							(oid_separado + "00000000").substring(0, 8), 8)));// CODIGO
					// NUMERICO
					// PARA
					// CHAVE DE ACESSO
					cte.setCFOP(new Integer(res
							.getString("cd_cfo_conhecimento")));
					cte.setNatOp("PRESTACAO DE SERVICO DE TRANSPORTE"); // ver
					// isso
					if (!res.getString("oid_Pessoa").equals(
							res.getString("oid_Pessoa_Pagador"))) {
						cte.setForPag(1);
					} else {
						cte.setForPag(0); // CIF/FOP
					}
					cte.setMod("57"); // fixo CTe
					cte.setSerie(new Integer(res.getString("nm_serie")));
					cte.setNCT(new Integer(res.getString("nr_conhecimento")));// NUMERO
					// DA
					// CTE
					String data = res.getString("dt_emissao");
					if (StringUtil.doValida(res.getString("hr_emissao")))
						data += " " + res.getString("hr_emissao") + ":"
								+ Data.getMileSegundo();
					else
						data += Data.getHoraHM() + ":" + Data.getMileSegundo();
					cte.setDhEmi(StringUtil.stringToCalendar(data,
							"yyyy-MM-dd hh:mm:ss").getTime());
					cte.setTpImp(1);
					cte.setTpEmis(1);

					cte.setTpAmb(new Integer(JavaUtil.getValueDef(empresa
							.getAmbiente(), "2"))); // 1 - prod , 2 - homo

					cte.setTpCTe(0); // normal
					// aqui muda pelo tipo de conhecimento
					// if(JavaUtil.doValida(res.getString("dm_tipo_conhecimento"))
					// && !res.getString("dm_tipo_conhecimento").equals("1"))
					// cte.setTpCTe(1);
					

					cte.setProcEmi(0);
					cte.setVerProc("2.00");
					cte.setRefCTE(null);
					cte.setCMunEnv(Integer.parseInt(ManipulaString
							.limpaCampo((JavaUtil.doValida(cuf) ? cuf : "")
									+ cmun)));// **
					if (String.valueOf(cte.getCMunEnv().intValue()).length() != 7)
						throw new Excecoes(
								"C�D. IBGE Munic�pio de Envio inv�lido!");

					cte.setXMunEnv(res.getString("nm_cidade_unidade").trim());// **
					cte.setUFEnv(uf);// **

					String dm_tipo_cte = JavaUtil.getValueDef(res
							.getString("DM_Tipo_CTe"), "0");
					if (JavaUtil.doValida(dm_tipo_cte)) {
						if ("2".equals(dm_tipo_cte)) {
							cte.setModal(2);// 01-Rodovi�rio; 02-A�reo;
							// 03-Aquavi�rio; 04-Ferrovi�rio;
							// 05-Dutovi�rio
						} else {
							cte.setModal(1);
						}
					} else {
						if ("A".equals(res.getString("DM_Tipo_Transporte"))) {
							cte.setModal(2);// 01-Rodovi�rio; 02-A�reo;
							// 03-Aquavi�rio; 04-Ferrovi�rio;
							// 05-Dutovi�rio
						} else {
							cte.setModal(1);
						}
					}

					if(res.getString("dm_tipo_conhecimento").equals("P")) {
						cte.setTpServ(2); // 2 - redespacho
					} else if(res.getString("dm_tipo_conhecimento").equals("H")) {
						cte.setTpServ(1); // 1 - subcontratacao
					} else {
						cte.setTpServ(0); // 0 - normal
					}

					String ch_original = "";
					queryAux = "select nm_ch_cte,dt_emissao from conhecimentos where oid_conhecimento='"
							+ res.getString("oid_conhecimento_original") + "'";
					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						ch_original = resAux.getString("nm_ch_cte");
					}
					resAux.close();
					
					if (res.getString("dm_tipo_conhecimento").equals("A")) {
						cte.setForPag(2);
						// cte.setCFOP();
						cte
								.setNatOp("ANULACAO DE VALOR RELATIVO A PREST. DE SERVICO DE TRANSPORTE");
						cte.setTpCTe(2); // anulacao
						if (JavaUtil.doValida(ch_original)) {
							// cte.setRefCTE(ch_original);

							CteInfAnu infAnu = new CteInfAnu();
							infAnu.setChCte(ch_original);
							Date dEmi = new Date();

							// Temos que ver a declaracao do tomador, tem um
							// grupo especifico

							// queryAux = " SELECT dt_emissao FROM
							// Conhecimentos_Notas_Fiscais, Notas_Fiscais "
							// + " WHERE
							// Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal =
							// Notas_Fiscais.oid_Nota_Fiscal "
							// + " AND
							// Conhecimentos_Notas_Fiscais.oid_Conhecimento = '"
							// + res.getString("oid_conhecimento") + "'";
							// resAux =
							// this.executasql.executarConsulta(queryAux);
							// while(resAux.next()){
							// dEmi = resAux.getDate("dt_emissao");
							// }
							infAnu.setDEmi(dEmi);
							cte.setInfAnu(infAnu);

						}

						// ainda faltam os dados da nota
						// ...
					}

					// Substituto
//					if (res.getString("dm_tipo_conhecimento").equals("B")) {
//					}
					if(res.getString("dm_tipo_conhecimento").equals("J")){
						substituicao = true;
						cte.setTpCTe(3); //substituicao
						
//						..
					}

					String cufAx = JavaUtil.getValueDef(res
							.getString("NM_Codigo_IBGE_CTRC_Origem"), "0");
					String cmunAx = JavaUtil.getValueDef(res
							.getString("NM_Codigo_Ibge_Origem"), "0");

					if (String
							.valueOf(
									ManipulaString.limpaCampo((JavaUtil
											.doValida(cufAx) ? cufAx : "")
											+ cmunAx)).length() != 7)
						throw new Excecoes(
								"C�D. IBGE Munic�pio de In�cio inv�lido: "
										+ cte.getCMunIni());

					cte.setCMunIni(Integer.parseInt(ManipulaString
							.limpaCampo((JavaUtil.doValida(cufAx) ? cufAx : "")
									+ cmunAx)));
					cte.setXMunIni(res.getString("NM_Cidade_CTRC_Origem")
							.trim());
					cte.setUFIni(res.getString("CD_Estado_CTRC_Origem"));
					cufAx = JavaUtil.getValueDef(res
							.getString("NM_Codigo_IBGE_CTRC_Destino"), "0");
					cmunAx = JavaUtil.getValueDef(res
							.getString("NM_Codigo_Ibge_Destino"), "0");
					cte.setCMunFim(Integer.parseInt(ManipulaString
							.limpaCampo((JavaUtil.doValida(cufAx) ? cufAx : "")
									+ cmunAx)));
					if (String.valueOf(cte.getCMunFim().intValue()).length() != 7)
						throw new Excecoes(
								"C�D. IBGE Munic�pio de Entrega inv�lido: "
										+ cte.getCMunFim());

					cte.setXMunFim(res.getString("NM_Cidade_CTRC_Destino")
							.trim());
					cte.setUFFim(res.getString("CD_Estado_CTRC_Destino"));
					cte.setRetira(0);
					cte.setXDetRetira("DETALHES DE RETIRADA");

					String pagador = res.getString("oid_Pessoa_Pagador");
					String oid_Grupo_Economico = "";

					queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, " +
							" cidades.nm_cidade, clientes.dm_rctr_dc, clientes.dm_rctrc, clientes.dm_emite_cte, clientes.oid_Grupo_Economico "
							+ " FROM pessoas, cidades, regioes_estados, estados, clientes "
							+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   pessoas.oid_pessoa = clientes.oid_pessoa "
							+ " AND   pessoas.oid_pessoa = '" + pagador + "'";
					resAux = this.executasql.executarConsulta(queryAux);

					CteTomador toma = new CteTomador();
					toma.setToma(0);
					if (res.getString("oid_Pessoa_Destinatario")
							.equals(pagador)) {
						toma.setToma(3);
					}
					if (res.getString("oid_Pessoa_Consignatario").equals(
							pagador)) {
						toma.setToma(2);
					}
					cte.setIndIEToma("1");
					while (resAux.next()) {
						
						cte.setIndIEToma("1");
						if (!JavaUtil.doValida(resAux
								.getString("nm_inscricao_estadual"))) {
							cte.setIndIEToma("9");
							
						} else if ("0".equals(resAux
								.getString("nm_inscricao_estadual"))) {
							cte.setIndIEToma("2");
						}
						
						toma.setXNome(JavaUtil.trunc(resAux
								.getString("nm_razao_social"), 60));
						toma.setCNPJ(resAux.getString("nr_cnpj_cpf"));
						toma.setFone(StringUtil.retornaNumeroValido(resAux
								.getString("nr_telefone"), 12));
						if(JavaUtil.doValida(resAux.getString("dm_emite_cte"))
								&& "S".equals(resAux.getString("dm_emite_cte"))
								&& cte.getTpServ() == 0
								&& toma.getToma() == 2) {
//							cte.setTpServ(2); // redespacho
							cte.setIndIEToma("9");
							toma.setToma(4);
							if (JavaUtil.doValida(resAux
									.getString("nm_inscricao_estadual"))) {
								toma.setIE(resAux.getString("nm_inscricao_estadual"));
							}
						} else {
							if (JavaUtil.doValida(resAux
									.getString("nm_inscricao_estadual"))) {
								toma.setIE(resAux.getString("nm_inscricao_estadual"));
							}
						}
						
						String endereco = resAux.getString("NM_Endereco");
						String numero = "0";
						String cpl = "";
						if (endereco.lastIndexOf(",") > 0) {
							numero = endereco.substring(endereco
									.lastIndexOf(",") + 1);
							if (numero.lastIndexOf(' ') > 0) {
								cpl = numero
										.substring(numero.lastIndexOf(" ") + 1);
							}
							endereco = endereco.substring(0, endereco
									.lastIndexOf(","));
							numero = StringUtil.retornaNumeroValido(numero);
						}
						if (!JavaUtil.doValida(numero))
							numero = JavaUtil.getValueDef(resAux
									.getString("NR_Endereco"), "0");
						if (!JavaUtil.doValida(cpl))
							cpl = JavaUtil.getValueDef(resAux
									.getString("NM_Complemento"), "");
						toma.setXLgr(endereco.trim());
						toma.setNro(StringUtil.retornaNumeroValido(numero));
						toma.setXBairro(ManipulaString.corrigeString(
								resAux.getString("nm_bairro")).trim());
						toma.setXCpl(cpl);
						toma.setCEP(StringUtil.retornaNumeroValido(resAux
								.getString("nr_cep")));

						cufAx = resAux.getString("NM_Codigo_IBGE");
						cmunAx = resAux.getString("cd_cid");

						if (!JavaUtil.doValida(cmunAx))
							throw new Excecoes(
									"C�D. IBGE Munic�pio do Pagador inv�lido!");

						toma.setCMun(Integer.parseInt(ManipulaString
								.limpaCampo((JavaUtil.doValida(cufAx) ? cufAx
										: "")
										+ cmunAx)));

						toma.setXMun(resAux.getString("nm_cidade").trim());
						toma.setUF(resAux.getString("cd_estado"));
						toma.setCPais(1058);
						toma.setXPais("BRASIL");

						if (JavaUtil.getValueDef(resAux.getString("dm_rctrc"),
								"N").equals("S")
								|| JavaUtil.getValueDef(
										resAux.getString("dm_rctr_dc"), "N")
										.equals("S")) {
							tpSeguro = "4";
						} else {
							if (toma.getToma().intValue() == 0)
								tpSeguro = "0";
							if (toma.getToma().intValue() == 3)
								tpSeguro = "3";
							if (toma.getToma().intValue() == 4)
								tpSeguro = "5";
						}
						
						oid_Grupo_Economico = JavaUtil.getValueDef(resAux.getString("oid_Grupo_Economico"),"0");
					}

					resAux.close();
					cte.setToma(toma);

					CteCompl compl = new CteCompl();
					
					String DM_Tipo_Conhecimento = res.getString("dm_tipo_conhecimento");
					String NM_xCaracAd = "TRANSPORTE";
					if ("8".equals(oid_Grupo_Economico)) { // Sara Lee
					   if ("1".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "1";
					   }else if ("F".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "5";
					   }else if ("B".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "6";
					   }else if ("Y".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "7";
					   }else if ("4".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "8";
					   }else if ("W".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "9";
					   }else if ("D".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "10";
					   }else if ("M".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "11";
					   }else if ("N".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "13";
					   }else if ("O".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "14";
					   }else if ("U".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "15";
					   } else if ("K".equals(DM_Tipo_Conhecimento)){
					      NM_xCaracAd = "7";
					   } else if ("X".equals(DM_Tipo_Conhecimento)){
						  NM_xCaracAd = "16";
					   } else if ("V".equals(DM_Tipo_Conhecimento)){
						  NM_xCaracAd = "17";
					   }
					}
					compl.setXCaracAd(NM_xCaracAd);
//					compl.setXCaracAd("TRANSPORTE");
					
					compl.setXCaracSer(StringUtil.trunc(
							res.getString("CD_Modal") + "-"
									+ res.getString("NM_Modal"), 30).trim());
					compl.setXEmi(StringUtil.trunc(res.getString("nm_usuario"),
							20));
					compl.setXOrig(StringUtil.trunc(
							res.getString("CD_Estado_CTRC_Origem") + "/"
									+ res.getString("NM_Cidade_CTRC_Origem"),
							15).trim());

					ArrayList<CtePass> listPass = new ArrayList<CtePass>();
					CtePass passagem = new CtePass();
					// passagem.setXPass("PASSAG 1");
					listPass.add(passagem);
					//
					// CtePass passagem2 = new CtePass();
					// passagem2.setXPass("PASSAG 2");
					// listPass.add(passagem2);

					compl.setCtePass(listPass);

					compl.setXDest(StringUtil.trunc(
							res.getString("CD_Estado_CTRC_Destino") + "/"
									+ res.getString("NM_Cidade_CTRC_Destino"),
							15).trim());
					compl.setXRota("ROTA");
					compl.setTpPer(0);
					compl.setTpHor(0);

					// obs contribuinte
					ArrayList<CteObs> listObsContrib = new ArrayList<CteObs>();
					String tx_observacao = "";

					if (StringUtil.doValida(res.getString("TX_Observacao"))) {
						tx_observacao += ManipulaString.corrigeString(
								res.getString("TX_Observacao") + " ").trim();
					}
					if (StringUtil.doValida(res.getString("TX_Observacao2"))) {
						tx_observacao += ManipulaString.corrigeString(
								res.getString("TX_Observacao2") + " ").trim();
					}
					if (StringUtil.doValida(res.getString("TX_Observacao3"))) {
						tx_observacao += ManipulaString.corrigeString(
								res.getString("TX_Observacao3") + " ").trim();
					}

					tx_observacao = (tx_observacao.trim()).toUpperCase();
					CteObs obsContrib = new CteObs();
					int indice = 1;
					if (JavaUtil.doValida(tx_observacao)) {
						if (tx_observacao.length() <= 60) {
							obsContrib = new CteObs();
							obsContrib.setXCampo("1");
							obsContrib.setXTexto(StringUtil.trunc(tx_observacao
									.trim(), 60));
							listObsContrib.add(obsContrib);
							indice++;
						} else {

							int qtdeObs = (int) (tx_observacao.length() / 60);
							System.out.println(tx_observacao.length() + "|"
									+ qtdeObs);
							for (int i = 0; i <= qtdeObs; i++) {
								indice++;
								obsContrib = new CteObs();
								obsContrib.setXCampo(String.valueOf(i + 1));
								if (tx_observacao.length() >= (i + 1) * 60)
									obsContrib.setXTexto(tx_observacao
											.substring(i * 60, (i + 1) * 60)
											.trim());
								else
									obsContrib.setXTexto(tx_observacao
											.substring(i * 60).trim());

								if (JavaUtil.doValida(obsContrib.getXTexto()))
									listObsContrib.add(obsContrib);
							}
						}
					}

					if (StringUtil.doValida(res.getString("TX_Despacho"))) {
						obsContrib = new CteObs();
						obsContrib.setXCampo(String.valueOf(indice));
						obsContrib.setXTexto(StringUtil.trunc(res.getString(
								"TX_Despacho").trim(), 60));
						listObsContrib.add(obsContrib);
					}
					// if(vTotTrib>0){
					// obsContrib = new CteObs();
					// obsContrib.setXCampo("Lei da Transpar�ncia");
					// obsContrib.setXTexto("O valor aproximado de tributos
					// incidentes sobre o pre�o deste servi�o � de R$ "+new
					// DecimalFormat("#,##0.00").format(vTotTrib));
					// listObsContrib.add(obsContrib);
					// }

					compl.setObsCont(listObsContrib);

					// obs fisco
					ArrayList<CteObs> listObsFisco = new ArrayList<CteObs>();
					String obs_icms = "";
					if (cte.getModal().intValue() == 2) {
						if (res.getDouble("VL_ICMS") > 0) {
							if (StringUtil
									.doValida(res
											.getString("TX_Mensagem_Fiscal_ICMS_Aereo"))) {
								obs_icms += res
										.getString("TX_Mensagem_Fiscal_ICMS_Aereo")
										+ " ";
							}
						} else {
							if (StringUtil.doValida(res
									.getString("TX_Mensagem_Fiscal_Aereo"))) {
								obs_icms += res
										.getString("TX_Mensagem_Fiscal_Aereo")
										+ " ";
							}
						}

					} else {
						if (res.getDouble("VL_ICMS") > 0) {
							if (StringUtil.doValida(res
									.getString("TX_Mensagem_Fiscal_ICMS"))) {
								obs_icms += res
										.getString("TX_Mensagem_Fiscal_ICMS")
										+ " ";
							}
						} else {
							if (StringUtil.doValida(res
									.getString("TX_Mensagem_Fiscal"))) {
								obs_icms += res.getString("TX_Mensagem_Fiscal")
										+ " ";
							}
						}
					}

					CteObs obsFisco = new CteObs();
					obsFisco.setXCampo("1");
					obsFisco.setXTexto(obs_icms);
					// listObsFisco.add(obsContrib);
					// CteObs obsFisco2 = new CteObs();
					// obsFisco2.setXCampo("2");
					// obsFisco2.setXTexto("TEXT DA OBS 2");
					// listObsFisco.add(obsFisco2);
					compl.setObsFisco(listObsFisco);
					cte.setCompl(compl);

					cte.setEmitente(emitente);

					// remetente
					queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade "
							+ " FROM pessoas, cidades, regioes_estados, estados "
							+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   pessoas.oid_pessoa = '"
							+ res.getString("oid_pessoa") + "'";
					resAux = this.executasql.executarConsulta(queryAux);
					CteRemetente remetente = new CteRemetente();
					remetente.setCNPJ("");
					remetente.setCPF("");
					while (resAux.next()) {
						// System.out.println(cte.getTpAmb()+"<<<<<<<");
						if (cte.getTpAmb() == 2) {
							remetente
									.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
							remetente
									.setXFant("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
						} else {
							remetente.setXNome(JavaUtil.trunc(
									ManipulaString.corrigeString(resAux
											.getString("nm_razao_social")), 60)
									.trim());
							remetente.setXFant(JavaUtil.trunc(
									ManipulaString.corrigeString(resAux
											.getString("nm_fantasia")), 60)
									.trim());
						}
						if (resAux.getString("nr_cnpj_cpf").length() < 14) {
							remetente.setCPF(resAux.getString("nr_cnpj_cpf"));
						} else {
							remetente.setCNPJ(resAux.getString("nr_cnpj_cpf"));
						}
						if (!JavaUtil.doValida(resAux
								.getString("nm_inscricao_estadual"))
								&& !"0".equals(resAux
										.getString("nm_inscricao_estadual")))
							throw new Excecoes("IE do Remetente � inv�lida!");
						if ("0".equals(resAux
								.getString("nm_inscricao_estadual")))
							remetente.setIE("ISENTO");
						else
							remetente.setIE(resAux.getString(
									"nm_inscricao_estadual").trim());
						String endereco = resAux.getString("NM_Endereco");
						String numero = "0";
						String cpl = "";
						if (endereco.lastIndexOf(",") > 0) {
							numero = endereco.substring(endereco
									.lastIndexOf(",") + 1);
							if (numero.lastIndexOf(' ') > 0) {
								cpl = numero
										.substring(numero.lastIndexOf(" ") + 1);
							}
							endereco = endereco.substring(0, endereco
									.lastIndexOf(","));
							numero = StringUtil.retornaNumeroValido(numero);
						}
						if (!JavaUtil.doValida(numero))
							numero = JavaUtil.getValueDef(resAux
									.getString("NR_Endereco"), "0");
						if (!JavaUtil.doValida(cpl))
							cpl = JavaUtil.getValueDef(resAux
									.getString("NM_Complemento"), "");
						remetente.setXLgr(ManipulaString
								.corrigeString(endereco).trim());
						remetente
								.setNro(StringUtil.retornaNumeroValido(numero));
						remetente.setXBairro(ManipulaString.corrigeString(
								resAux.getString("nm_bairro")).trim());
						remetente.setXCpl(cpl);
						remetente.setCEP(StringUtil.retornaNumeroValido(resAux
								.getString("nr_cep")));

						cufAx = resAux.getString("NM_Codigo_IBGE");
						cmunAx = resAux.getString("cd_cid");

						if (!JavaUtil.doValida(cmunAx))
							throw new Excecoes(
									"C�D. IBGE Munic�pio do Remetente inv�lido!");

						remetente.setCMun(Integer.parseInt(ManipulaString
								.limpaCampo((JavaUtil.doValida(cufAx) ? cufAx
										: "")
										+ cmunAx)));

						if (StringUtil.retornaNumeroValido(
								resAux.getString("nr_cep")).length() != 8)
							throw new Excecoes("CEP do Remetente inv�lido!");
						if (String.valueOf(remetente.getCMun().intValue())
								.length() != 7)
							throw new Excecoes(
									"C�D. IBGE Munic�pio do Remetente inv�lido!");

						remetente.setXMun(resAux.getString("nm_cidade").trim());
						remetente.setUF(resAux.getString("cd_estado"));
						remetente.setCPais(1058);
						remetente.setXPais("BRASIL");
					}
					resAux.close();

					// INFORMACOES DE NF
					ArrayList<CteInfNF> listNf = new ArrayList<CteInfNF>();
					ArrayList<CteInfNFe> listNfe = new ArrayList<CteInfNFe>();
					ArrayList<CteInfOutros> listOutros = new ArrayList<CteInfOutros>();
					int qt_notas = 0;
					double NR_Peso = 0;
					double VL_Nota_Fiscal = 0;
					double VL_Mercadoria = 0;
					String NR_Nota_Fiscal = "";

					queryAux = " SELECT "
							+ " Notas_Fiscais.NM_Serie, "
							+ " Notas_Fiscais.DM_Transferencia, "
							+ " Notas_Fiscais.NR_Nota_Fiscal, "
							+ " Notas_Fiscais.NR_Despacho, "
							+ " Notas_Fiscais.NR_Lote, "
							+ " Notas_Fiscais.DT_Emissao, "
							+ " Notas_Fiscais.NR_Pedido, "
							+ " Notas_Fiscais.NR_Volumes, "
							+ " Notas_Fiscais.NR_Peso, "
							+ " Notas_Fiscais.VL_Nota_Fiscal, "
							+ " Notas_Fiscais.NM_Especie "
							+ ",Notas_Fiscais.nm_ch_nfe "
							// + ",'' as nm_ch_nfe "
							+ " FROM  Conhecimentos_Notas_Fiscais,  Notas_Fiscais "
							+ " WHERE Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal "
							+ " AND   Conhecimentos_Notas_Fiscais.oid_Conhecimento = '"
							+ res.getString("oid_conhecimento") + "'";
					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						qt_notas++;
						NR_Nota_Fiscal = resAux.getString("NR_Nota_Fiscal");
						NR_Peso = resAux.getDouble("NR_Peso");
						if (NR_Peso <= 0) {
							NR_Peso = 0.001;
						}
						VL_Nota_Fiscal = resAux.getDouble("VL_Nota_Fiscal");
						if (JavaUtil.doValida(resAux.getString("nm_ch_nfe"))) {
							CteInfNFe nfe = new CteInfNFe();
							nfe.setChave(resAux.getString("nm_ch_nfe"));
							listNfe.add(nfe);
						} else {
							if ("D"
									.equals(resAux
											.getString("DM_Transferencia"))) { // tipo
								// DOC
								VL_Nota_Fiscal = 0.01;
								// DOCUMENTOS SEM NOTA FISCAL
								CteInfNF nf = new CteInfNF();
								nf.setNRoma("0");
								nf.setNPed("0");
								nf.setSerie("0");
								nf.setNDoc("123");
								nf.setDEmi(resAux.getDate("DT_Emissao"));
								nf.setVBC(0d);
								nf.setVICMS(0d);
								nf.setVBCST(0d);
								nf.setVST(0d);
								nf.setVProd(0d);
								nf.setVNF(VL_Nota_Fiscal);
								nf.setNCFOP(0);
								nf.setNPeso(0.01d);
								nf.setPIN(null);
								listNf.add(nf);
							} else {
								CteInfNF nf = new CteInfNF();
								nf.setNRoma("0");
								nf.setNPed(JavaUtil.getValueDef(
										resAux.getString("NR_Pedido"), "")
										.trim());
								nf.setSerie("1");
								nf.setNDoc(NR_Nota_Fiscal.trim());
								nf.setDEmi(resAux.getDate("DT_Emissao"));
								nf.setVBC(0d);
								nf.setVICMS(0d);
								nf.setVBCST(0d);
								nf.setVST(0d);
								nf.setVProd(VL_Nota_Fiscal);
								nf.setVNF(VL_Nota_Fiscal);
								nf.setNCFOP(1101);
								nf.setNPeso(NR_Peso);
								nf.setPIN(null);
								listNf.add(nf);
							}
						}

					}
					resAux.close();
					remetente.setInfNF(listNf);
					remetente.setInfNFe(listNfe);
					remetente.setInfOutros(listOutros);
					cte.setRemetente(remetente);

					queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade "
							+ " FROM pessoas, cidades, regioes_estados, estados "
							+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   pessoas.oid_pessoa = '"
							+ res.getString("oid_Pessoa") + "'";
					// + res.getString("oid_Pessoa_Expedidor") + "'";
					resAux = this.executasql.executarConsulta(queryAux);
					CteExpedidor expedidor = new CteExpedidor();
					expedidor.setCNPJ("");
					expedidor.setCPF("");
					while (resAux.next()) {
						if (cte.getTpAmb() == 2) {
							expedidor
									.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
						} else {
							expedidor.setXNome(JavaUtil.trunc(
									ManipulaString.corrigeString(resAux
											.getString("nm_razao_social")), 60)
									.trim());
						}

						expedidor.setFone(StringUtil.retornaNumeroValido(resAux
								.getString("nr_telefone"), 12));
						if (resAux.getString("nr_cnpj_cpf").length() < 14) {
							expedidor.setCPF(resAux.getString("nr_cnpj_cpf"));
						} else {
							expedidor.setCNPJ(resAux.getString("nr_cnpj_cpf"));
						}
						if ("0".equals(resAux
								.getString("nm_inscricao_estadual")))
							expedidor.setIE("ISENTO");
						else
							expedidor.setIE(resAux.getString(
									"nm_inscricao_estadual").trim());
						if (!JavaUtil.doValida(resAux
								.getString("nm_inscricao_estadual"))
								&& !"0".equals(resAux
										.getString("nm_inscricao_estadual")))
							throw new Excecoes("IE do Expedidor � inv�lida!");
						String endereco = resAux.getString("NM_Endereco");
						String numero = "0";
						String cpl = "";
						if (endereco.lastIndexOf(",") > 0) {
							numero = endereco.substring(endereco
									.lastIndexOf(",") + 1);
							if (numero.lastIndexOf(' ') > 0) {
								cpl = numero
										.substring(numero.lastIndexOf(" ") + 1);
							}
							endereco = endereco.substring(0, endereco
									.lastIndexOf(","));
							numero = StringUtil.retornaNumeroValido(numero);
						}
						if (!JavaUtil.doValida(numero))
							numero = JavaUtil.getValueDef(resAux
									.getString("NR_Endereco"), "0");
						if (!JavaUtil.doValida(cpl))
							cpl = JavaUtil.getValueDef(resAux
									.getString("NM_Complemento"), "");
						expedidor.setXLgr(ManipulaString.corrigeString(
								endereco.trim()).trim());
						expedidor
								.setNro(StringUtil.retornaNumeroValido(numero));
						expedidor.setXBairro(ManipulaString.corrigeString(
								resAux.getString("nm_bairro")).trim());
						expedidor.setXCpl(cpl);
						expedidor.setCEP(StringUtil.retornaNumeroValido(resAux
								.getString("nr_cep")));

						cufAx = resAux.getString("NM_Codigo_IBGE");
						cmunAx = resAux.getString("cd_cid");
						expedidor.setCMun(Integer.parseInt(ManipulaString
								.limpaCampo((JavaUtil.doValida(cufAx) ? cufAx
										: "")
										+ cmunAx)));
						if (StringUtil.retornaNumeroValido(
								resAux.getString("nr_cep")).length() != 8)
							throw new Excecoes("CEP do Expedidor inv�lido!");
						if (String.valueOf(expedidor.getCMun().intValue())
								.length() != 7)
							throw new Excecoes(
									"C�D. IBGE Munic�pio do Expedidor inv�lido!");

						expedidor.setXMun(resAux.getString("nm_cidade").trim());
						expedidor.setUF(resAux.getString("cd_estado"));
						expedidor.setCPais(1058);
						expedidor.setXPais("BRASIL");
					}
					resAux.close();
					if (JavaUtil.doValida(expedidor.getCNPJ()))
						cte.setExpedidor(expedidor);

					// se tomador eh consignat�rio e emitente de CTe (cte.getIndIEToma = 9) n�o ser� colocado aqui o consignat�rio...
					String pesRec = res.getString("oid_Pessoa_Destinatario");
					if (JavaUtil.doValida(res
							.getString("oid_Pessoa_Consignatario")) && !cte.getIndIEToma().equals("9"))
						pesRec = res.getString("oid_Pessoa_Consignatario");

					queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade "
							+ " FROM pessoas, cidades, regioes_estados, estados "
							+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   pessoas.oid_pessoa = '"
							// + res.getString("oid_Pessoa_Destinatario")
							+ pesRec + "'";

					// + res.getString("oid_Pessoa_Redespacho") + "'";
					resAux = this.executasql.executarConsulta(queryAux);
					CteRecebedor recebedor = new CteRecebedor();
					recebedor.setCNPJ("");
					recebedor.setCPF("");
					while (resAux.next()) {
						if (cte.getTpAmb() == 2) {
							recebedor
									.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
						} else {
							recebedor.setXNome(JavaUtil.trunc(
									ManipulaString.corrigeString(resAux
											.getString("nm_razao_social")), 60)
									.trim());
						}
						recebedor.setFone(StringUtil.retornaNumeroValido(resAux
								.getString("nr_telefone"), 12));
						if (resAux.getString("nr_cnpj_cpf").length() < 14) {
							recebedor.setCPF(resAux.getString("nr_cnpj_cpf"));
						} else {
							recebedor.setCNPJ(resAux.getString("nr_cnpj_cpf"));
						}

						if (!JavaUtil.doValida(resAux
								.getString("nm_inscricao_estadual"))
								&& !"0".equals(resAux
										.getString("nm_inscricao_estadual")))
							throw new Excecoes("IE do Recebedor � inv�lida!");

						if ("0".equals(resAux
								.getString("nm_inscricao_estadual")))
							recebedor.setIE("ISENTO");
						else
							recebedor.setIE(resAux.getString(
									"nm_inscricao_estadual").trim());
						String endereco = resAux.getString("NM_Endereco");
						String numero = "0";
						String cpl = "";
						if (endereco.lastIndexOf(",") > 0) {
							numero = endereco.substring(endereco
									.lastIndexOf(",") + 1);
							if (numero.lastIndexOf(' ') > 0) {
								cpl = numero
										.substring(numero.lastIndexOf(" ") + 1);
							}
							endereco = endereco.substring(0, endereco
									.lastIndexOf(","));
							numero = StringUtil.retornaNumeroValido(numero);
						}
						if (!JavaUtil.doValida(numero))
							numero = JavaUtil.getValueDef(resAux
									.getString("NR_Endereco"), "0");
						if (!JavaUtil.doValida(cpl))
							cpl = JavaUtil.getValueDef(resAux
									.getString("NM_Complemento"), "");
						recebedor.setXLgr(ManipulaString.corrigeString(
								endereco.trim()).trim());
						recebedor
								.setNro(StringUtil.retornaNumeroValido(numero));
						recebedor.setXBairro(ManipulaString.corrigeString(
								resAux.getString("nm_bairro")).trim());
						recebedor.setXCpl(cpl);
						recebedor.setCEP(StringUtil.retornaNumeroValido(resAux
								.getString("nr_cep")));

						cufAx = resAux.getString("NM_Codigo_IBGE");
						cmunAx = resAux.getString("cd_cid");

						if (!JavaUtil.doValida(cmunAx))
							throw new Excecoes(
									"C�D. IBGE Munic�pio do Recebedor inv�lido!");

						recebedor.setCMun(Integer.parseInt(ManipulaString
								.limpaCampo((JavaUtil.doValida(cufAx) ? cufAx
										: "")
										+ cmunAx)));

						if (StringUtil.retornaNumeroValido(
								resAux.getString("nr_cep")).length() != 8)
							throw new Excecoes("CEP do Recebedor inv�lido!");
						if (String.valueOf(recebedor.getCMun().intValue())
								.length() != 7)
							throw new Excecoes(
									"C�D. IBGE Munic�pio do Recebedor inv�lido!");

						recebedor.setXMun(resAux.getString("nm_cidade").trim());
						recebedor.setUF(resAux.getString("cd_estado"));
						recebedor.setCPais(1058);
						recebedor.setXPais("BRASIL");
					}
					resAux.close();
					if (JavaUtil.doValida(recebedor.getCNPJ()))
						cte.setRecebedor(recebedor);

					queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade "
							+ " FROM pessoas, cidades, regioes_estados, estados "
							+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   pessoas.oid_pessoa = '"
							+ res.getString("oid_Pessoa_Destinatario") + "'";
					resAux = this.executasql.executarConsulta(queryAux);
					CteDestinatario destinatario = new CteDestinatario();
					destinatario.setCNPJ("");
					destinatario.setCPF("");
					while (resAux.next()) {
						if (cte.getTpAmb() == 2) {
							destinatario
									.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
						} else {
							destinatario.setXNome(JavaUtil.trunc(
									ManipulaString.corrigeString(resAux
											.getString("nm_razao_social")), 60)
									.trim());
						}
						destinatario.setFone(StringUtil.retornaNumeroValido(
								resAux.getString("nr_telefone"), 12));
						if (resAux.getString("nr_cnpj_cpf").length() < 14) {
							destinatario
									.setCPF(resAux.getString("nr_cnpj_cpf"));
						} else {
							destinatario.setCNPJ(resAux
									.getString("nr_cnpj_cpf"));
						}
						if (!JavaUtil.doValida(resAux
								.getString("nm_inscricao_estadual"))
								&& !"0".equals(resAux
										.getString("nm_inscricao_estadual")))
							throw new Excecoes("IE do Destinat�rio � inv�lida!");

						if ("0".equals(resAux
								.getString("nm_inscricao_estadual")))
							destinatario.setIE("ISENTO");
						else
							destinatario.setIE(resAux.getString(
									"nm_inscricao_estadual").trim());

						String endereco = resAux.getString("NM_Endereco");
						String numero = "0";
						String cpl = "";
						if (endereco.lastIndexOf(",") > 0) {
							numero = endereco.substring(endereco
									.lastIndexOf(",") + 1);
							if (numero.lastIndexOf(' ') > 0) {
								cpl = numero
										.substring(numero.lastIndexOf(" ") + 1);
							}
							endereco = endereco.substring(0, endereco
									.lastIndexOf(","));
							numero = StringUtil.retornaNumeroValido(numero);
						}
						if (!JavaUtil.doValida(numero))
							numero = JavaUtil.getValueDef(resAux
									.getString("NR_Endereco"), "0");
						if (!JavaUtil.doValida(cpl))
							cpl = JavaUtil.getValueDef(resAux
									.getString("NM_Complemento"), "");
						destinatario.setXLgr(ManipulaString.corrigeString(
								endereco.trim()).trim());
						destinatario.setNro(StringUtil
								.retornaNumeroValido(numero));
						destinatario.setXBairro(ManipulaString.corrigeString(
								resAux.getString("nm_bairro")).trim());
						destinatario.setXCpl(cpl);
						destinatario
								.setCEP(StringUtil.retornaNumeroValido(resAux
										.getString("nr_cep")));

						cufAx = resAux.getString("NM_Codigo_IBGE");
						cmunAx = resAux.getString("cd_cid");

						if (!JavaUtil.doValida(cmunAx))
							throw new Excecoes(
									"C�D. IBGE Munic�pio do Destinat�rio inv�lido!");

						destinatario.setCMun(Integer.parseInt(ManipulaString
								.limpaCampo((JavaUtil.doValida(cufAx) ? cufAx
										: "")
										+ cmunAx)));
						if (StringUtil.retornaNumeroValido(
								resAux.getString("nr_cep")).length() != 8)
							throw new Excecoes("CEP do Destinat�rio inv�lido!");
						if (String.valueOf(destinatario.getCMun().intValue())
								.length() != 7)
							throw new Excecoes(
									"C�D. IBGE Munic�pio do Destinat�rio inv�lido!");
						destinatario.setXMun(resAux.getString("nm_cidade")
								.trim());
						destinatario.setUF(resAux.getString("cd_estado"));
						destinatario.setCPais(1058);
						destinatario.setXPais("BRASIL");
					}
					resAux.close();
					cte.setDestinatario(destinatario);

					cte.setVTPrest(res.getDouble("VL_TOTAL_FRETE"));
					cte.setVRec(res.getDouble("VL_TOTAL_FRETE"));
					ArrayList<CteCompPrestacaoServico> listCompPrestacaoServico = new ArrayList<CteCompPrestacaoServico>();
					queryAux = " SELECT "
							+ " Tipos_Movimentos.NM_Tipo_Movimento, "
							+ " Movimentos_Conhecimentos.VL_Movimento "
							+ " FROM  Tipos_Movimentos,  Movimentos_Conhecimentos "
							+ " WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento "
							+ " AND   Tipos_Movimentos.DM_Tipo_Movimento <> 'D' "
							+ " AND   Tipos_Movimentos.DM_Calcula_Frete = 'S' "
							+ " AND   Movimentos_Conhecimentos.oid_Conhecimento = '"
							+ res.getString("oid_conhecimento") + "'";

					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						CteCompPrestacaoServico comp = new CteCompPrestacaoServico();
						comp.setXNome(StringUtil.trunc(
								resAux.getString("NM_Tipo_Movimento"), 15)
								.trim());
						comp.setVComp(resAux.getDouble("VL_Movimento"));
						listCompPrestacaoServico.add(comp);
					}
					cte.setCompPrestacaoServico(listCompPrestacaoServico);

					// CteImposto imp = new CteImposto();
					// String cst = res.getString("DM_CST");
					// if(StringUtil.doValida(cst)){
					// try{
					// imp.setCST(new Integer(cst));
					// } catch (Exception e){
					//
					// }
					// } else {
					// imp.setCST(0);
					// }
					// if (1==1 || res.getDouble("VL_ICMS")>0){
					// // imp.setCST(0);
					// imp.setPICMS(res.getDouble("PE_ALIQUOTA_ICMS"));
					// imp.setPRedBC(0d);
					// imp.setVBC(res.getDouble("VL_TOTAL_FRETE"));
					// imp.setVICMS(res.getDouble("VL_ICMS"));
					// imp.setVCred(0d);
					// }

					CteImposto imp = new CteImposto();
					if (1 == 1 || res.getDouble("VL_ICMS") > 0) {
						imp.setCST(0);

						if (res.getDouble("VL_ICMS") > 0)
							imp.setPICMS(res.getDouble("PE_ALIQUOTA_ICMS"));
						else
							imp.setPICMS(0d);

						imp.setPRedBC(0d);
						imp.setVBC(res.getDouble("VL_TOTAL_FRETE"));
						imp.setVICMS(res.getDouble("VL_ICMS"));
						imp.setVCred(0d);

						// total de tributos
						imp.setvTotTrib(vTotTrib);
					}

//					if (res.getDouble("VL_ICMS") <= 0) {
//						imp.setCST(40);
//						imp.setVBC(0d);
//					}
//					total de tributos
					imp.setvTotTrib(vTotTrib);
					
//					// CTe Subst Trib
//					if (1 == 2) {
//						imp.setCST(60);
//					}

					cte.setImp(imp);
					resAux.close();

					CteInfCTeNorm cteInfNor = new CteInfCTeNorm();
					cteInfNor.setProPred(res.getString("NM_Produto"));
					cteInfNor.setXOutCat(res.getString("NM_Liberacao_veiculo")
							.trim());
					cteInfNor.setVCarga(res.getDouble("vl_nota_fiscal"));// **
					
					// DADOS CTE SUBSTITUICAO
					if(substituicao){
						CteInfCteSub subs = new CteInfCteSub();
						if(JavaUtil.doValida(res.getString("NM_CH_CTe_Original")))
							subs.setChCte(res.getString("NM_CH_CTe_Original"));
						else
							throw new Excecoes("Chave do CTe Original � obrigat�ria para CTe de Substitui��o!");
						if(JavaUtil.doValida(res.getString("NM_CH_NFe_Anulacao")))
							subs.setChNFe(res.getString("NM_CH_NFe_Anulacao"));
						else
							throw new Excecoes("Chave da NFe de Anula��o � obrigat�ria para CTe de Substitui��o!");
						if(toma.getIE().equals("ISENTO")) {
							subs.setIcms(false);
							if(JavaUtil.doValida(res.getString("NM_CH_NFe_Anulacao")))
								subs.setRefCteAnu(res.getString("NM_CH_NFe_Anulacao"));
							else
								throw new Excecoes("Chave da NFe de Anula��o � obrigat�ria para CTe de Substitui��o!");
						} else {
							subs.setIcms(true);
						}
						cteInfNor.setInfCteSub(subs);
					}

					ArrayList<CteInfQCarga> listInfQ = new ArrayList<CteInfQCarga>();
					CteInfQCarga infq = new CteInfQCarga();
					infq.setCUnid(0);
					infq.setTpMed("VOLUMES (UN)");
					infq.setQCarga(res.getDouble("NR_VOLUMES"));
					listInfQ.add(infq);
					infq = new CteInfQCarga();
					infq.setCUnid(0);
					infq.setTpMed("PESO (KG)");
					infq.setQCarga(res.getDouble("NR_PESO"));
					listInfQ.add(infq);
					infq = new CteInfQCarga();
					infq.setCUnid(0);
					infq.setTpMed("PESO CUBADO (KG)");
					infq.setQCarga(res.getDouble("NR_PESO_CUBADO"));
					listInfQ.add(infq);
					infq = new CteInfQCarga();
					infq.setCUnid(0);
					infq.setTpMed("CUBAGEM (M3)");
					infq.setQCarga(res.getDouble("NR_CUBAGEM"));
					listInfQ.add(infq);
					cteInfNor.setInfQ(listInfQ);

					ArrayList<CteContQt> listContainer = new ArrayList<CteContQt>();
					CteContQt container = new CteContQt();
					// container.setNCont(1234);
					// container.setDPrev(new Date());
					listContainer.add(container);
					// cteInfNor.setContQt(listContainer);

					ArrayList<CteDocAnt> listDocAnt = new ArrayList<CteDocAnt>();
					CteDocAnt docAnt = new CteDocAnt();
					// docAnt.setCNPJ("91665554000163");
					// docAnt.setIE("4000000763");
					// docAnt.setXNome("NOME EMITENTE DOC ANTERIOR");
					// docAnt.setUF("RS");
					//
					// ArrayList<CteIdDocAnt> listIdDocAnt = new
					// ArrayList<CteIdDocAnt>();
					// CteIdDocAnt idDocAnt = new CteIdDocAnt();
					// idDocAnt.setChave("43100708693058000170570010000000020001011065");
					// listIdDocAnt.add(idDocAnt);
					//
					// CteIdDocAnt idDocAnt2 = new CteIdDocAnt();
					// idDocAnt2.setNDoc(1);
					// idDocAnt2.setSerie("0");
					// idDocAnt2.setTpDoc(4);
					// idDocAnt2.setDEmi(new Date());
					// listIdDocAnt.add(idDocAnt2);
					//
					// docAnt.setIdDocAnt(listIdDocAnt);
					if(cte.getTpServ() > 0) {
						ArrayList<CteIdDocAnt> idsAnt = new ArrayList<CteIdDocAnt>();
						//Cte relacionado
						queryAux = "Select PESSOAS.*, estados.cd_estado "
								+ " FROM pessoas, cidades, regioes_estados, estados "
								+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
								+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
								+ " AND   regioes_estados.oid_estado = estados.oid_estado "
								+ " AND   pessoas.oid_pessoa = '"
								+ pagador + "'";
						resAux = this.executasql.executarConsulta(queryAux);
						if(resAux.next()) {
							if (resAux.getString("nr_cnpj_cpf").length() < 14) {
								docAnt
										.setCPF(resAux.getString("nr_cnpj_cpf"));
							} else {
								docAnt.setCNPJ(resAux
										.getString("nr_cnpj_cpf"));
							}
							if ("0".equals(resAux
									.getString("nm_inscricao_estadual")))
								docAnt.setIE("ISENTO");
							else
								docAnt.setIE(resAux.getString(
										"nm_inscricao_estadual").trim());
							docAnt.setUF(JavaUtil.getValueDef(resAux.getString("cd_estado"), ""));
						}
						//chave/chaves
						CteIdDocAnt idAnt = new CteIdDocAnt();
						if(JavaUtil.doValida(res.getString("nm_ch_cte_anterior"))){
							idAnt.setChave(res.getString("nm_ch_cte_anterior"));
							idsAnt.add(idAnt);
						}
						if(idsAnt.size() > 0){
							docAnt.setIdDocAnt(idsAnt);
							listDocAnt.add(docAnt);
							cteInfNor.setDocAnt(listDocAnt);
						}
					} else {
						listDocAnt.add(docAnt);
//						cteInfNor.setDocAnt(listDocAnt);
					}

					ArrayList<CteSeg> listSeg = new ArrayList<CteSeg>();
					queryAux = " SELECT Clientes.NM_Seguradora, Clientes.NR_Apolice "
							+ " FROM   Clientes "
							+ " WHERE  Clientes.NM_Seguradora IS NOT NULL "
							+ " AND    Clientes.NR_Apolice IS NOT NULL "
							+ " AND    Clientes.oid_Pessoa = '"
							+ res.getString("oid_pessoa_unidade") + "'"; // seguro
																			// no
																			// cad
																			// cliente
																			// da
																			// unidade
					// + " AND Clientes.oid_Pessoa = '" + pagador + "'";

					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						CteSeg seg = new CteSeg();
						seg.setNApol(resAux.getString("NR_Apolice"));
						seg.setXSeg(resAux.getString("NM_Seguradora"));
						seg.setRespSeg(new Integer(tpSeguro));
						seg.setVCarga(res.getDouble("vl_nota_fiscal"));// **
						listSeg.add(seg);
					}
					if (listSeg.size() == 0) {
						CteSeg seg = new CteSeg();
						// seg.setNApol(resAux.getString("NR_Apolice"));
						// seg.setXSeg(resAux.getString("NM_Seguradora"));
						seg.setRespSeg(new Integer(tpSeguro));
						// seg.setVCarga(res.getDouble("vl_nota_fiscal"));//**
						listSeg.add(seg);
					}
					cteInfNor.setSeg(listSeg);
					resAux.close();

					// RODOVIARIO

					CteRodo rodo = new CteRodo();
					rodo.setLota(0);
					if (!JavaUtil
							.doValida(res.getString("DT_Previsao_Entrega"))) {
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.DAY_OF_YEAR, cal
								.get(Calendar.DAY_OF_YEAR) + 1);
						Date prev = cal.getTime();
						rodo.setdPrev(prev);
					} else {
						rodo.setdPrev(res.getDate("DT_Previsao_Entrega"));
					}

					rodo.setRNTRC(RNTRC);// RNTRC 8 digitos

					ArrayList<CteOcc> listOcc = new ArrayList<CteOcc>();
					CteOcc occ = new CteOcc();
					// occ.setSerie("123");
					// occ.setNOcc(128);//N�mero da Ordem de coleta
					// occ.setDEmi(new Date());//Data de emiss�o da ordem de
					// coleta
					//
					// occ.setCInt("145875");
					// occ.setCNPJ("91.665.554/0001-63");
					// occ.setIE("4000000763");
					// occ.setUF("RS");
					// occ.setFone("5137141234");//fone
					listOcc.add(occ);
					// rodo.setOcc(listOcc);

					ArrayList<CteValePed> listValePed = new ArrayList<CteValePed>();
					CteValePed valePed = new CteValePed();
					// valePed.setCNPJForn("91.665.554/0001-63");
					// valePed.setnCompra(1234);//N�mero do comprovante de
					// compra
					// valePed.setCNPJPg("91.665.554/0001-63");
					listValePed.add(valePed);
					// valePed.setNroRE("12345");
					// valePed.setRespPg(0);
					// valePed.setVTValePed(100d);
					// ArrayList<CteDisp> listDisp = new ArrayList<CteDisp>();
					// CteDisp disp = new CteDisp();
					// disp.setXEmp("NOEM DA EMPRESA DISP");
					// disp.setTpDisp(1);
					// disp.setDVig(new Date());
					// listDisp.add(disp);
					// valePed.setDisp(listDisp);
					// rodo.setValePed(listValePed);

					ArrayList<CteVeic> listVeic = new ArrayList<CteVeic>();

					queryAux = " SELECT Veiculos.nr_placa "
							+ " FROM   Veiculos "
							+ " WHERE  Veiculos.oid_Veiculo = '"
							+ res.getString("oid_Veiculo") + "'";

					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						CteVeic veic = new CteVeic();
						veic.setUF("");
						veic.setCapKG(1000);
						veic.setCapM3(100);
						veic.setRENAVAM("");
						veic.setPlaca(resAux.getString("nr_placa"));
						veic.setTara(0);
						veic.setTpCar(0);
						veic.setTpProp("T");
						veic.setTpVeic(0); // 0 - cavalo
						veic.setTpRod(0);
						// prop
						CteProp prop = new CteProp();
						prop.setCNPJ("");
						prop.setXNome("NOME TESTE PROP");
						prop.setUF("");
						prop.setRNTRC("");
						prop.setIE("");
						prop.setTpProp(1);
						veic.setProp(prop);
						listVeic.add(veic);
					}
					resAux.close();
					queryAux = " SELECT Veiculos.nr_placa "
							+ " FROM   Veiculos "
							+ " WHERE  Veiculos.oid_Veiculo = '"
							+ res.getString("oid_Carreta") + "'";

					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						CteVeic veic = new CteVeic();
						veic.setUF("");
						veic.setCapKG(1000);
						veic.setCapM3(100);
						veic.setRENAVAM("");
						veic.setPlaca(resAux.getString("nr_placa"));
						veic.setTara(0);
						veic.setTpCar(0);
						veic.setTpProp("T");
						veic.setTpVeic(2); // 1 - carreta
						veic.setTpRod(0);
						// prop
						CteProp prop = new CteProp();
						prop.setCNPJ("");
						prop.setXNome("NOME TESTE PROP");
						prop.setUF("");
						prop.setRNTRC("");
						prop.setIE("");
						prop.setTpProp(1);
						veic.setProp(prop);
						listVeic.add(veic);
					}
					resAux.close();

					// rodo.setVeic(listVeic);

					ArrayList<CteLacRodo> listLacRodo = new ArrayList<CteLacRodo>();
					CteLacRodo lacRodo = new CteLacRodo();
					// lacRodo.setNLacre("1");
					listLacRodo.add(lacRodo);
					// rodo.setLacRodo(listLacRodo);

					ArrayList<CteMoto> listMoto = new ArrayList<CteMoto>();
					queryAux = " SELECT Pessoas.* " + " FROM   Pessoas "
							+ " WHERE  Pessoas.oid_Pessoa = '"
							+ res.getString("oid_motorista") + "'";

					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						CteMoto moto = new CteMoto();
						moto.setCPF(resAux.getString("nr_cnpj_cpf"));
						moto.setxNome(resAux.getString("nm_razao_social")
								.trim());
						listMoto.add(moto);
					}
					resAux.close();
					// rodo.setMoto(listMoto);
					// System.out.println("MODAL:"+cte.getModal());
					if (cte.getModal() == 1) {
						cteInfNor.setRodo(rodo);
					}

					// AEREO
					CteAereo aereo = new CteAereo();
					// aereo.setIdT("032332");
					aereo
							.setNMinu(new Integer(res
									.getString("nr_conhecimento")));
					// aereo.setXLAgEmi("AZUL LINHAS AEREAS B");
					aereo.setDPrev(res.getDate("DT_Previsao_Entrega"));
					aereo.setCL("M");
					aereo.setCTar("001");
					aereo.setVTar(0d);
					// aereo.setNOCA("00056996311055");
					aereo.setcInfManu("");
					aereo.setxDime("");

					if (cte.getModal() == 2) {
						cteInfNor.setAereo(aereo);
					}

					// CteAquav aquav = new CteAquav();
					// aquav.setDirec("O");
					// aquav.setIrin("1");
					// aquav.setNBooking("NB222");
					// aquav.setNCtrl("NCon");
					// aquav.setNViag("1");
					// aquav.setPrtDest("PORT DEST");
					// aquav.setPrtEmb("POR EMB");
					// aquav.setPrtTrans("PRT TRA");
					// aquav.setTpNav(1);
					// aquav.setVAFRMM(100.55);
					// aquav.setXNavio("NOME NAV");
					// aquav.setVPrest(100d);
					// cteInfNor.setAquav(aquav);

					// ** INF MODAL
					CteInfModal infmodal = new CteInfModal();// **
					infmodal.setVersaoModal("3.00");// **
					if (cte.getModal() == 1) {
						infmodal.setXsany("CTeModalRodoviario_v3.00");// **
					}
					if (cte.getModal() == 2) {
						infmodal.setXsany("cteModalAereo_v3.00");// **
					}
					cte.setInfModal(infmodal);

					ArrayList<CtePeri> listPeri = new ArrayList<CtePeri>();
					CtePeri peri = new CtePeri();
					// peri.setNONU("1");
					// peri.setPontoFulgor("1");
					// peri.setQTotProd("100");
					// peri.setXClaRisco("1");
					// peri.setXNomeAE("Nome AE");
					// peri.setqVolTipo("100");
					listPeri.add(peri);
					// cteInfNor.setPeri(listPeri);

					ArrayList<CteVeicNovos> listVeicNovos = new ArrayList<CteVeicNovos>();
					CteVeicNovos veicNovos = new CteVeicNovos();
					// veicNovos.setChassi("22222222222222222");
					// veicNovos.setCCor("1");
					// veicNovos.setXCor("COR");
					// veicNovos.setVFrete(1000d);
					// veicNovos.setVUnit(1000d);
					// veicNovos.setCMod("1");
					listVeicNovos.add(veicNovos);

					// cteInfNor.setVeicNovos(listVeicNovos);

					if (res.getString("dm_tipo_conhecimento").equals("A")) {
						// Anulacao nao deve ir o infCteNorm ?????
						cteInfNor = null;
					}

					cte.setInfCTeNorm(cteInfNor);

					// CteInfCteComp infCteComp = new CteInfCteComp();
					// infCteComp.setChave("43100708693058000170570010000000020001011065");
					// infCteComp.setVTPrest(150.33);
					//
					// ArrayList<CteCompComp> listCompComp = new
					// ArrayList<CteCompComp>();
					// CteCompComp compComp = new CteCompComp();
					// compComp.setVComp(333d);
					// compComp.setXNome("NOME COMP");
					// listCompComp.add(compComp);
					// infCteComp.setCompComp(listCompComp);
					//
					// CteImposto impComp = new CteImposto();
					// impComp.setCST(0);
					// impComp.setPICMS(17d);
					// impComp.setPRedBC(5d);
					// impComp.setVBC(100d);
					// impComp.setVICMS(17d);
					// impComp.setVCred(5d);
					// infCteComp.setImpComp(impComp);
					// Enviacte.setInfCTeComp(infCteComp);
				}
			}
			res.close();

		} catch (Excecoes exc) {
			exc.printStackTrace();
			throw exc;
		} catch (Exception exc) {
			exc.printStackTrace();
			throw new Excecoes("Erro ao buscar dados CTe", exc, this.getClass()
					.getName(), "getDadosEnvio()");
		}
		return cte;
	}

	public ArrayList<ConhecimentoED> listaNaoRetornados(ConhecimentoED ed)
			throws Excecoes {

		String sql = null;
		ArrayList<ConhecimentoED> list = new ArrayList<ConhecimentoED>();
		ResultSet res = null;
		try {
			sql = " SELECT oid_conhecimento, NM_Protocolo_Envio_CTe FROM Conhecimentos  "
					+ " WHERE DM_Status_CTe = 'E'"
					+ " AND   NM_CH_CTe is not null"
					+ " AND   (NM_Protocolo_Retorno_CTe is null OR NM_Protocolo_Retorno_CTe = '' OR NM_Protocolo_Retorno_CTe = 'null')";

			res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				ConhecimentoED edVolta = new ConhecimentoED();
				edVolta.setOID_Conhecimento(res.getString("oid_conhecimento"));
				edVolta.setNM_Protocolo_Envio_CTe(res
						.getString("NM_Protocolo_Envio_CTe"));
				list.add(edVolta);
			}

		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(),
					"lista(ConhecimentoED ed)");
		}

		return list;
	}

	public ArrayList<ConhecimentoED> listaParaImpressao(ConhecimentoED ed)
			throws Excecoes {

		String sql = null;
		ArrayList<ConhecimentoED> list = new ArrayList<ConhecimentoED>();
		ResultSet res = null;
		try {
			sql = " SELECT oid_conhecimento, NM_Protocolo_Envio_CTe FROM Conhecimentos  "
					+ " WHERE DM_Status_CTe = '100'"
					+ " AND   NM_CH_CTe is not null"
					+ " AND   (NM_Protocolo_Retorno_CTe is not null)";
			sql += " AND conhecimentos.nr_conhecimento>="
					+ ed.getNR_Conhecimento_Inicial();
			sql += " AND conhecimentos.nr_conhecimento<="
					+ ed.getNR_Conhecimento_Final();
			sql += " AND Unidades_Fiscal.oid_pessoa ='" + ed.getOID_Pessoa()
					+ "'";

			res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				ConhecimentoED edVolta = new ConhecimentoED();
				edVolta.setOID_Conhecimento(res.getString("oid_conhecimento"));
				list.add(edVolta);
			}

		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(),
					"lista(ConhecimentoED ed)");
		}

		return list;
	}

	public ArrayList<ConhecimentoED> listaOrigemImpressao(Origem_DuplicataED ed)
			throws Excecoes {

		String sql = null;
		ArrayList<ConhecimentoED> list = new ArrayList<ConhecimentoED>();
		ResultSet res = null;
		try {
			sql = " SELECT origens_duplicatas.oid_conhecimento, origens_duplicatas.dm_situacao FROM origens_duplicatas, conhecimentos  "
					+ " WHERE origens_duplicatas.oid_conhecimento = conhecimentos.oid_conhecimento "
					+ " AND conhecimentos.DM_Status_CTe = '100' "
					+ " AND origens_duplicatas.dm_situacao != 'E' "
					+ " AND   conhecimentos.NM_CH_CTe is not null "
					+ " AND   conhecimentos.NM_Protocolo_Retorno_CTe is not null ";
			sql += " AND origens_duplicatas.oid_duplicata ='"
					+ ed.getOID_Duplicata() + "'"
					+ " ORDER BY conhecimentos.nr_conhecimento ";

			res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				ConhecimentoED edVolta = new ConhecimentoED();
				edVolta.setOID_Conhecimento(res.getString("oid_conhecimento"));
				list.add(edVolta);
			}

		} catch (SQLException e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(),
					"listaOrigemImpressao(ConhecimentoED ed)");
		}

		return list;
	}

	public ConhecimentoED getConhecimentoED(ConhecimentoED ed) throws Excecoes {
		boolean tem_item_consulta = false;
		String sql = " select Conhecimentos.* "
				+ "       ,Conhecimentos.DM_Tipo_Documento "
				+ "       ,Conhecimentos.DM_Retira_Aeroporto "
				+ "       ,Conhecimentos.DM_Tipo_Desconto_Pedagio "
				+ "       ,Conhecimentos.TX_Local_Retirada "
				+ "       ,Conhecimentos.DM_Tipo_Tarifa "
				+ "       ,Conhecimentos.NR_Master "
				+ "       ,Conhecimentos.oid_Unidade_Agente "
				+ "       ,Conhecimentos.DM_Tipo_Tarifa "
				+ "       ,Conhecimentos.NR_Pedido as NR_Pedido_Cto "
				+ "       ,Conhecimentos.CD_CFO_Conhecimento_Editado "
				+ "       ,Conhecimentos.VL_Nota_Fiscal as VL_Nota_Fiscal_CTRC "
				+ "       ,Unidades.DM_Calcula_Frete "
				+ "       ,Unidades.CD_Unidade " + "       ,Unidades.DM_CTe "
				+ "       ,pessoas.oid_pessoa as oid_pessoa_unidade "
				+ "       ,Modal.DM_Tipo_Tabela_Frete as Tipo_Tabela_Frete"
				+ "       ,Modal.DM_Tipo_Transporte "
				+ "       ,Modal.DM_Tipo_Coleta "
				+ "       ,Modal.DM_Tipo_Entrega "
				+ "       ,Unidades.oid_Empresa " + " from  Conhecimentos "
				+ "      ,Modal" + "      ,Unidades, Unidades Uorig, pessoas "
				+ " where Conhecimentos.oid_unidade = Uorig.oid_unidade "
				+ "   and Uorig.oid_unidade_fiscal = Unidades.oid_unidade "
				+ "   and pessoas.oid_pessoa = unidades.oid_pessoa "
				+ "   and Conhecimentos.oid_Modal   = Modal.oid_Modal ";

		if (JavaUtil.doValida(ed.getOID_Conhecimento())) {
			sql += " and OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'";
			tem_item_consulta = true;
		} else {
			if (ed.getOID_Unidade() > 0) {
				sql += " and Conhecimentos.OID_Unidade = "
						+ ed.getOID_Unidade();
			}
			if (ed.getNR_Conhecimento() > 0) {
				sql += " and Conhecimentos.NR_Conhecimento = "
						+ ed.getNR_Conhecimento();
				tem_item_consulta = true;
			}
			if (ed.getOID_Pessoa_Pagador() != null
					&& ed.getOID_Pessoa_Pagador().length() > 4) {
				sql += " and Conhecimentos.oid_Pessoa_Pagador = '"
						+ ed.getOID_Pessoa_Pagador() + "'";
				tem_item_consulta = true;
			}

		}
		if (!tem_item_consulta) {
			sql += " and Conhecimentos.NR_Conhecimento = 99999999"; // p/nao
			// fazer uma
			// consulta
			// em todo
			// banco
		}

		// System.out.println (" Sql=>>>>" + sql);

		ConhecimentoED edVolta = new ConhecimentoED();

		try {
			ResultSet res = this.executasql.executarConsulta(sql);
			String DM_Calcula_Frete = "S";
			while (res.next()) {

				// System.out.println (" CTO getByRecord NOVINHO !!!");

				DM_Calcula_Frete = res.getString("DM_Calcula_Frete");
				edVolta.setDM_Impresso(res.getString("DM_Impresso"));
				edVolta.setDM_Isento_Seguro(res.getString("DM_Isento_Seguro"));
				edVolta.setDM_Tipo_Desconto_Pedagio(res
						.getString("DM_Tipo_Desconto_Pedagio"));

				edVolta.setCD_Rota_Entrega(res.getString("CD_Rota_Entrega"));

				// /CTE
				edVolta.setDM_CTe(res.getString("DM_CTe"));
				edVolta.setNM_DACTE(res.getString("NM_DACTE"));

				edVolta.setNM_CH_CTe(res.getString("NM_CH_CTe"));
				edVolta.setDM_Status_CTe(res.getString("DM_Status_CTe"));
				edVolta.setNM_Status_CTe(res.getString("NM_Status_CTe"));
				edVolta.setDT_Envio_CTe(res.getString("DT_Envio_CTe"));
				edVolta.setDT_Recebimento_CTe(res
						.getString("DT_Recebimento_CTe"));
				edVolta.setNM_Protocolo_Envio_CTe(res
						.getString("NM_Protocolo_Envio_CTe"));
				edVolta.setNM_Protocolo_Retorno_CTe(res
						.getString("NM_Protocolo_Retorno_CTe"));

				edVolta.setDM_Status_Cancelamento_CTe(res
						.getString("DM_Status_Cancelamento_CTe"));
				edVolta.setNM_Status_Cancelamento_CTe(res
						.getString("NM_Status_Cancelamento_CTe"));
				edVolta.setDT_Envio_Cancelamento_CTe(res
						.getString("DT_Envio_Cancelamento_CTe"));
				edVolta.setDT_Recebimento_Cancelamento_CTe(res
						.getString("DT_Recebimento_Cancelamento_CTe"));
				edVolta.setNM_Protocolo_Cancelamento_CTe(res
						.getString("NM_Protocolo_Cancelamento_CTe"));

				edVolta.setDM_Status_Anulacao_CTe(res
						.getString("DM_Status_Anulacao_CTe"));
				edVolta.setNM_Status_Anulacao_CTe(res
						.getString("NM_Status_Anulacao_CTe"));
				edVolta.setDT_Envio_Anulacao_CTe(res
						.getString("DT_Envio_Anulacao_CTe"));
				edVolta.setDT_Recebimento_Anulacao_CTe(res
						.getString("DT_Recebimento_Anulacao_CTe"));
				edVolta.setNM_Protocolo_Anulacao_CTe(res
						.getString("NM_Protocolo_Anulacao_CTe"));

				if ("204".equals(res.getString("DM_Status_CTe"))) {
					if (res.getString("NM_Status_CTe").indexOf("nRec:") > 0) {
						String protEnvio = res.getString("NM_Status_CTe")
								.substring(
										res.getString("NM_Status_CTe").indexOf(
												"nRec:") + 5,
										(res.getString("NM_Status_CTe")
												.indexOf("nRec:") + 20));
						System.out.println("prot NOVO:" + protEnvio);
						edVolta.setNM_Protocolo_Envio_CTe(protEnvio);
					}
				}

				edVolta.setDM_Responsavel_Cobranca(res
						.getString("DM_Responsavel_Cobranca"));
				edVolta
						.setDM_Tipo_Pagamento(res
								.getString("DM_Tipo_Pagamento"));

				edVolta.setDM_Tipo_Conhecimento(res
						.getString("DM_Tipo_Conhecimento"));
				edVolta.setNM_Tipo_Documento("Despacho");
				if ("S".equals(res.getString("DM_Impresso"))) {
					if ("C".equals(res.getString("DM_Tipo_Documento"))) {
						edVolta.setNM_Tipo_Documento("Conhecimento");
					}
					if ("M".equals(res.getString("DM_Tipo_Documento"))) {
						edVolta.setNM_Tipo_Documento("Minuta");
					}
					if ("A".equals(res.getString("DM_Tipo_Documento"))) {
						edVolta.setNM_Tipo_Documento("AWB");
					}
					if ("N".equals(res.getString("DM_Tipo_Documento"))) {
						edVolta.setNM_Tipo_Documento("NF Servico");
					}
					if ("F".equals(res.getString("DM_Tipo_Documento"))) {
						edVolta.setNM_Tipo_Documento("Frete Terceiro");
					}
				}
				edVolta.setDM_Tipo_Postagem(res.getString("DM_Tipo_Postagem"));

				edVolta
						.setDM_Tipo_Documento(res
								.getString("DM_Tipo_Documento"));
				edVolta.setCD_Roteiro(res.getString("CD_Roteiro"));
				edVolta.setDM_Situacao(res.getString("DM_Situacao"));
				edVolta.setDT_Emissao(FormataData.formataDataBT(res
						.getDate("DT_Emissao")));
				edVolta.setDT_Coleta(FormataData.formataDataBT(res
						.getDate("DT_Coleta")));
				edVolta.setHR_Coleta(res.getString("HR_Coleta"));

				edVolta.setDT_Solicitado_Coleta(FormataData.formataDataBT(res
						.getDate("DT_Solicitado_Coleta")));
				edVolta.setHR_Solicitado_Coleta(res
						.getString("HR_Solicitado_Coleta"));
				edVolta.setDT_Confirmacao_Coleta(FormataData.formataDataBT(res
						.getDate("DT_Confirmacao_Coleta")));
				edVolta.setHR_Confirmacao_Coleta(res
						.getString("HR_Confirmacao_Coleta"));
				edVolta.setDT_Realizacao_Coleta(FormataData.formataDataBT(res
						.getDate("DT_Realizacao_Coleta")));
				edVolta.setHR_Realizacao_Coleta(res
						.getString("HR_Realizacao_Coleta"));

				edVolta.setDT_Programada(FormataData.formataDataBT(res
						.getDate("DT_Programada")));

				edVolta.setDT_Entrega("");
				if (res.getString("DT_Entrega") != null
						&& res.getString("DT_Entrega").length() >= 4) {
					edVolta.setDT_Entrega(FormataData.formataDataBT(res
							.getDate("DT_Entrega")));
				}

				edVolta.setHR_Entrega(res.getString("HR_Entrega"));
				edVolta.setDT_Previsao_Entrega(FormataData.formataDataBT(res
						.getDate("DT_Previsao_Entrega")));
				edVolta.setHR_Previsao_Entrega(res
						.getString("HR_Previsao_Entrega"));
				edVolta
						.setNM_Pessoa_Entrega(res
								.getString("NM_Pessoa_Entrega"));
				edVolta.setDM_Tipo_Entrega(res.getString("DM_Tipo_Entrega"));
				edVolta.setOID_Comprovante_Entrega(res
						.getString("oid_Comprovante_Entrega"));
				edVolta.setOID_Lote_Faturamento(res
						.getLong("oid_Lote_Faturamento"));
				edVolta.setOID_Participacao_Frete(res
						.getLong("oid_Participacao_Frete"));

				edVolta.setOID_Tipo_Ocorrencia(res
						.getLong("oid_Tipo_Ocorrencia"));
				edVolta.setOid_Ocorrencia_Conhecimento(res
						.getLong("oid_Ocorrencia_Conhecimento"));
				edVolta.setNR_Dias_Entrega_Realizado(res
						.getLong("NR_Dias_Entrega_Realizado"));

				edVolta.setOid_Programacao_Carga(res
						.getLong("Oid_Programacao_Carga"));

				edVolta.setOID_Motorista(res.getString("OID_Motorista"));

				edVolta.setNM_Atendente(res.getString("NM_Atendente"));
				edVolta.setNM_Natureza(res.getString("NM_Natureza"));
				edVolta.setNR_Lote(res.getString("NR_Lote"));
				edVolta.setNM_Serie(res.getString("NM_Serie"));
				edVolta.setNM_Liberacao_Veiculo(res
						.getString("NM_Liberacao_Veiculo"));

				edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));

				edVolta.setNR_Minuta(res.getLong("NR_Minuta"));
				edVolta.setNR_ACT(res.getLong("NR_ACT"));
				edVolta.setNR_Postagem(res.getLong("NR_Postagem"));
				edVolta.setNR_AWB(res.getLong("NR_AWB"));
				edVolta.setNR_Nota_Fiscal_Servico(res
						.getLong("NR_Nota_Fiscal_Servico"));

				edVolta.setOID_Cidade(res.getLong("OID_Cidade"));

				edVolta.setOID_Cidade_Aereo_Origem(res
						.getLong("OID_Cidade_Aereo_Origem"));
				edVolta.setOID_Cidade_Aereo_Destino(res
						.getLong("OID_Cidade_Aereo_Destino"));

				edVolta.setOID_Taxa(res.getLong("OID_Taxa"));
				edVolta.setOID_Acerto(res.getLong("OID_Acerto"));
				edVolta.setOID_Usuario(res.getLong("OID_Usuario"));

				edVolta
						.setOID_Cidade_Destino(res
								.getLong("OID_Cidade_Destino"));
				edVolta.setOID_Coleta(res.getLong("OID_Coleta"));
				edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));

				/*
				 * edVolta.setOID_Nota_Fiscal (util.getTableStringValue
				 * ("oid_Nota_Fiscal" , "Conhecimentos_Notas_Fiscais" ,
				 * "Conhecimentos_Notas_Fiscais.oid_Conhecimento = '" +
				 * edVolta.getOID_Conhecimento () + "'" + " AND
				 * Conhecimentos_Notas_Fiscais.oid_Conhecimento =
				 * Conhecimentos.oid_Conhecimento"));
				 */
				edVolta.setOID_Modal(res.getLong("OID_Modal"));
				edVolta.setOID_Centro_Custo(res.getLong("OID_Centro_Custo"));

				edVolta.setDM_Tipo_Tabela_Frete(res
						.getString("Tipo_Tabela_Frete"));

				edVolta.setDM_Tipo_Transporte(res
						.getString("DM_Tipo_Transporte"));
				edVolta.setDM_Tipo_Coleta(res.getString("DM_Tipo_Coleta"));
				edVolta.setDM_Tipo_Entrega(res.getString("DM_Tipo_Entrega"));
				edVolta
						.setDM_Tipo_Embalagem(res
								.getString("DM_Tipo_Embalagem"));
				edVolta.setOID_Motorista(res.getString("OID_Motorista"));

				edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
				edVolta.setOID_Pessoa_Consignatario(res
						.getString("OID_Pessoa_Consignatario"));
				edVolta.setOID_Pessoa_Redespacho(res
						.getString("OID_Pessoa_Redespacho"));

				edVolta.setOID_Pessoa_Pagador(res
						.getString("OID_Pessoa_Pagador"));
				edVolta.setOID_Pessoa_Destinatario(res
						.getString("OID_Pessoa_Destinatario"));
				edVolta.setOID_Produto(res.getLong("OID_Produto"));
				edVolta.setOID_Tabela_Frete(res.getString("OID_Tabela_Frete"));
				edVolta.setOID_Unidade(res.getLong("OID_Unidade"));
				edVolta.setOid_pessoa_unidade(res
						.getString("OID_Pessoa_unidade"));
				edVolta.setCD_Unidade(res.getString("CD_Unidade"));
				edVolta.setOID_Empresa(res.getLong("OID_Empresa"));
				edVolta.setOID_Vendedor(res.getString("OID_Vendedor"));
				edVolta.setOID_Veiculo(res.getString("OID_Veiculo"));
				edVolta.setOID_Carreta(res.getString("OID_Carreta"));
				edVolta.setOID_Carreta2(res.getString("OID_Carreta2"));
				edVolta.setPE_Aliquota_ICMS(res.getDouble("PE_Aliquota_ICMS"));

				edVolta
						.setPE_Carga_Expressa(res
								.getDouble("PE_Carga_Expressa"));

				edVolta.setTX_Observacao(res.getString("TX_Observacao"));
				edVolta.setTX_Roteiro(res.getString("TX_Roteiro"));
				edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal_CTRC"));

				edVolta.setVL_Nota_Fiscal_Seguro(res
						.getDouble("VL_Nota_Fiscal_Seguro"));

				edVolta.setNR_Volumes(Valor.truncLong(res
						.getDouble("NR_Volumes")));
				edVolta.setNR_Peso(res.getDouble("NR_Peso"));
				edVolta.setNR_Cubagem(res.getDouble("NR_Cubagem"));
				edVolta.setNR_Peso_Cubado(res.getDouble("NR_PESO_CUBADO"));

				edVolta.setVL_TOTAL_FRETE(res.getDouble("VL_Total_Frete"));
				edVolta.setVL_BASE_CALCULO_ICMS(res
						.getDouble("vl_base_calculo_icms"));
				edVolta.setPE_ALIQUOTA_ICMS(res.getDouble("pe_aliquota_icms"));
				edVolta.setVL_FRETE_PESO(res.getDouble("VL_Frete_Peso"));
				edVolta.setVL_PEDAGIO(res.getDouble("VL_Pedagio"));
				edVolta.setVL_Frete(res.getDouble("VL_Total_Frete"));
				edVolta.setVL_ICMS(res.getDouble("VL_Icms"));
				edVolta.setNM_Lote_Faturamento(res
						.getString("NM_Lote_Faturamento"));
				edVolta.setNR_Transacao_Pedagio(res
						.getString("NR_Transacao_Pedagio"));
				edVolta.setDM_Tipo_Cobranca(res.getString("DM_Tipo_Cobranca"));
				edVolta.setDM_Tipo_Desconto_Pedagio(res
						.getString("DM_Tipo_Desconto_Pedagio"));
				edVolta.setPE_Comissao_Acerto(res
						.getDouble("PE_Comissao_Acerto"));
				edVolta.setVL_Base_Comissao_Acerto(res
						.getDouble("VL_Base_Comissao_Acerto"));
				edVolta.setDM_Retira_Aeroporto(res
						.getString("DM_Retira_Aeroporto"));
				edVolta
						.setTX_Local_Retirada(res
								.getString("TX_Local_Retirada"));

				edVolta.setDM_Tipo_Tarifa(res.getString("DM_Tipo_Tarifa"));
				edVolta.setDM_Cia_Aerea(res.getString("DM_Cia_Aerea"));
				edVolta.setNR_Master(res.getString("NR_Master"));
				edVolta.setOid_Unidade_Agente(res.getInt("oid_Unidade_Agente"));
				edVolta.setVL_Frete_Aereo(res.getDouble("VL_Frete_Aereo"));
				edVolta.setVL_Taxa_Terrestre_Origem(res
						.getDouble("VL_Taxa_Terrestre_Origem"));
				edVolta.setVL_Nota_Fiscal_Declarado(res
						.getDouble("VL_Nota_Fiscal_Declarado"));
				edVolta.setVL_Taxa_Terrestre_Destino(res
						.getDouble("VL_Taxa_Terrestre_Destino"));
				edVolta.setVL_Taxa_Transportador(res
						.getDouble("VL_Taxa_Transportador"));
				edVolta.setVL_Master(res.getDouble("VL_Master"));
				edVolta.setDM_Tipo_Tarifa(res.getString("DM_Tipo_Tarifa"));
				edVolta.setCD_CFO_Conhecimento_Editado(res
						.getString("CD_CFO_Conhecimento_Editado"));
				// System.out.println (" CTO getByRecord 1");

				edVolta.setCD_CFO(res.getString("CD_CFO_Conhecimento"));
				edVolta.setPE_Aliquota_ICMS(res.getDouble("PE_Aliquota_ICMS"));

				// System.out.println (" CTO getByRecord NOVO( calcula_frete)");

				if ("S".equals(DM_Calcula_Frete)
						&& "N".equals(edVolta.getDM_Impresso())) {
					// System.out.println (" CTO getByRecord vai
					// calcula_frete=>> " + edVolta.getDM_Tipo_Documento ());

				}

				edVolta.setNM_Especie(res.getString("NM_Especie"));
				edVolta.setNR_Pedido(res.getString("NR_Pedido_Cto"));

				edVolta.setNM_Natureza_AWB(res.getString("NM_Natureza_AWB"));
				edVolta.setNM_Especie_AWB(res.getString("NM_Especie_AWB"));
				edVolta.setNR_Volumes_AWB(Valor.truncLong(res
						.getDouble("NR_Volumes_AWB")));
				edVolta.setNR_Peso_AWB(res.getDouble("NR_Peso_AWB"));
				edVolta.setVL_Tarifa_AWB(res.getDouble("VL_Tarifa_AWB"));
				edVolta
						.setTX_Observacao_AWB(res
								.getString("TX_Observacao_AWB"));
				edVolta.setTX_Observacao_AWB2(res
						.getString("TX_Observacao_AWB2"));
				edVolta.setTX_Observacao_AWB3(res
						.getString("TX_Observacao_AWB3"));
				edVolta.setTX_Observacao_AWB4(res
						.getString("TX_Observacao_AWB4"));
				edVolta.setTX_Observacao_AWB5(res
						.getString("TX_Observacao_AWB5"));
				edVolta.setTX_Observacao_AWB6(res
						.getString("TX_Observacao_AWB6"));
				edVolta.setVL_Frete_Valor_AWB(res
						.getDouble("VL_Frete_Valor_AWB"));
				// System.out.println (" CTO getByRecord 10");

				if (res.getLong("oid_Usuario") > 0) {
					sql = " SELECT NM_Usuario FROM Usuarios WHERE oid_Usuario='"
							+ res.getLong("oid_Usuario") + "'";
					ResultSet res2 = this.executasql.executarConsulta(sql);
					while (res2.next()) {
						edVolta.setNM_Usuario(res2.getString("NM_Usuario"));
					}
				}

				// if (res.getString("oid_Pessoa_Entregadora") != null
				// && res.getString("oid_Pessoa_Entregadora").length() > 4) {
				// sql = " SELECT NM_Razao_Social FROM Pessoas WHERE
				// oid_Pessoa='"
				// + res.getString("oid_Pessoa_Entregadora") + "'";
				// ResultSet res2 = this.executasql.executarConsulta(sql);
				// if (res2.next()) {
				// edVolta.setOID_Pessoa_Entregadora(res
				// .getString("OID_Pessoa_Entregadora"));
				// edVolta.setNM_Pessoa_Entregadora(res2
				// .getString("NM_Razao_Social"));
				// edVolta.setNR_Conhecimento_Entregadora(res
				// .getString("NR_Conhecimento_Entregadora"));
				// edVolta.setDT_Previsao_Entregadora(FormataData
				// .formataDataBT(res
				// .getDate("DT_Previsao_Entregadora")));
				// edVolta.setVL_Frete_Entregadora(res
				// .getDouble("VL_Frete_Entregadora"));
				// }
				// }

			}
		} catch (SQLException exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "getByRecord()");
		}
		return edVolta;
	}

	public void numeraCTe(ConhecimentoED ed) throws Excecoes {
		String condicao_pesquisa = "";
		long Oid_AIDOF = 0;
		long NR_Proximo = 0;
		long NR_Final = 0;
		long NR_Conhecimento = 0;
		long NR_Minuta = 0;
		long NR_Nota_Fiscal_Servico = 0;
		long NR_ACT = 0;
		long NR_AWB = 0;
		long Nr_Sort_Impressao = (new Long(String.valueOf(
				System.currentTimeMillis()).toString()).longValue());
		String tipo_impressao = "I";
		String DM_Tipo_Conhecimento = "";
		String DM_Tipo_Documento = ed.getDM_Tipo_Documento();
		String DT_Emissao_Conhecimento = Data.getDataDMY();
		boolean gera_NR_Documento_pela_AIDOF = true;
		String sql = "";
		String NM_Sigla = "";
		String DM_Cte = "N";

		sql = " SELECT Conhecimentos.oid_conhecimento "
				+ "      ,Conhecimentos.nr_conhecimento "
				+ "      ,Conhecimentos.nr_minuta "
				+ "      ,Conhecimentos.nr_ACT "
				+ "      ,Conhecimentos.nr_AWB "
				+ "      ,Conhecimentos.nm_natureza "
				+ "      ,Conhecimentos.nr_Nota_Fiscal_Servico "
				+ "      ,Conhecimentos.oid_unidade "
				+ "      ,Conhecimentos.DM_Tipo_Documento "
				+ "      ,Conhecimentos.DM_Cia_Aerea "
				+ "      ,Conhecimentos.DM_Tipo_Conhecimento "
				+ "      ,Conhecimentos.NM_Serie "
				+ "      ,Modal.DM_Tipo_Tabela_Frete "
				+ "      ,Unidades.NM_Sigla " + "      ,Unidades.CD_Unidade "
				+ "      ,Unidades.DM_Cte "
				+ "      ,conhecimentos.oid_Pessoa "
				+ "      ,conhecimentos.oid_pessoa_destinatario "
				+ "      ,conhecimentos.oid_pessoa_pagador "
				+ " from Unidades, Conhecimentos " + "      left join Modal "
				+ "        on Conhecimentos.oid_Modal = Modal.oid_Modal "
				+ " where Conhecimentos.oid_Unidade = Unidades.oid_Unidade ";

		sql += " AND Conhecimentos.DM_Impresso = 'N' ";

		// sempre pelo OID
		condicao_pesquisa += " and Conhecimentos.OID_Conhecimento = '"
				+ ed.getOID_Conhecimento() + "'";

		condicao_pesquisa += " ORDER by conhecimentos.oid_Usuario ";

		condicao_pesquisa += ", conhecimentos.uni_key ASC ";

		condicao_pesquisa += " LIMIT 50 ";
		sql += condicao_pesquisa;

		System.out.println(sql);
		ResultSet resCTRC = this.executasql.executarConsulta(sql);
		try {
			try {
				while (resCTRC.next()) {

					DM_Cte = resCTRC.getString("DM_Cte");
					System.out.println("IMPRESS CTO W1 DM_Cte>> " + DM_Cte);

					if ("S".equals(DM_Cte)) { // soh eletronicos

						ed.setOID_Unidade(resCTRC.getLong("OID_Unidade"));
						ed.setOID_Conhecimento(resCTRC
								.getString("oid_Conhecimento"));
						NR_Conhecimento = resCTRC.getLong("NR_Conhecimento");
						NR_Minuta = resCTRC.getLong("nr_minuta");
						NR_ACT = resCTRC.getLong("nr_ACT");
						NR_AWB = resCTRC.getLong("nr_AWB");
						NR_Nota_Fiscal_Servico = resCTRC
								.getLong("nr_Nota_Fiscal_Servico");
						DM_Tipo_Conhecimento = resCTRC
								.getString("DM_Tipo_Conhecimento");
						NM_Sigla = resCTRC.getString("NM_Sigla");

						DM_Tipo_Documento = resCTRC
								.getString("DM_Tipo_Documento");

						sql = " SELECT Unidades.DT_Emissao_Conhecimento, Unidades.NM_Sigla "
								+ " FROM Unidades "
								+ " WHERE  Unidades.DT_Emissao_Conhecimento is not null "
								+ " AND    Unidades.oid_Unidade     = "
								+ ed.getOID_Unidade();
						ResultSet resTP = this.executasql.executarConsulta(sql);
						if (resTP.next()) {
							DT_Emissao_Conhecimento = resTP
									.getString("DT_Emissao_Conhecimento");

						}

						if ("A".equals(DM_Tipo_Documento) && NR_AWB > 0) {
							gera_NR_Documento_pela_AIDOF = false;
						}

						if (!"B".equals(DM_Tipo_Documento)) {
							if (gera_NR_Documento_pela_AIDOF) {

								if ("A".equals(DM_Tipo_Documento)
										&& "AWB_CIA_AEREA"
												.equals(Parametro_FixoED
														.getInstancia()
														.getDM_Formulario_AWB())) {
									sql = " SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie, AIDOF.DM_Formulario "
											+ " WHERE AIDOF.CD_AIDOF = '"
											+ resCTRC.getString("DM_Cia_Aerea")
											+ "'"
											+ " AND   AIDOF.NM_Serie = "
											+ resCTRC.getLong("OID_Unidade");
								} else {
									sql = " SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie, AIDOF.DM_Formulario ";
									sql += " FROM Parametros_Filiais, AIDOF ";
									if ("M".equals(DM_Tipo_Documento)) { // minuta
										sql += " WHERE Parametros_Filiais.OID_AIDOF_MINUTA = AIDOF.OID_AIDOF ";
									}
									if ("F".equals(DM_Tipo_Documento)) { // frete
										// terc
										sql += " WHERE Parametros_Filiais.OID_AIDOF_FRETE_TERCEIRO = AIDOF.OID_AIDOF ";
									}
									if ("MD".equals(DM_Tipo_Documento)) { // minuta
										sql += " WHERE Parametros_Filiais.OID_AIDOF_MINUTA = AIDOF.OID_AIDOF ";
									}
									if ("N".equals(DM_Tipo_Documento)) { // nf
										// serv
										sql += " WHERE Parametros_Filiais.OID_AIDOF_Nota_Fiscal_Servico = AIDOF.OID_AIDOF ";
									}
									if ("Z".equals(DM_Tipo_Documento)) { // nf
										// armazem
										// devol
										sql += " WHERE Parametros_Filiais.oid_aidof_nota_fiscal = AIDOF.OID_AIDOF ";
									}
									if ("T".equals(DM_Tipo_Documento)) { // act
										sql += " WHERE Parametros_Filiais.OID_AIDOF_ACT = AIDOF.OID_AIDOF ";
									}
									if ("C".equals(DM_Tipo_Documento)) { // conhecimento
										if ("S".equals(DM_Cte)) {
											sql += " WHERE Parametros_Filiais.OID_AIDOF_ACT = AIDOF.OID_AIDOF "; // CTE
										} else {
											sql += " WHERE Parametros_Filiais.OID_AIDOF = AIDOF.OID_AIDOF "; // CTRC
										}
									}
									sql += " AND Parametros_Filiais.OID_Unidade = "
											+ ed.getOID_Unidade();
								}
								System.out.println(sql);

								resTP = this.executasql.executarConsulta(sql
										.toString());
								try {
									while (resTP.next()) {

										// System.out.println ("ACHOU AIDOF ->>"
										// + resTP.getString ("NR_Proximo"));

										ed.setNM_Serie(resTP
												.getString("NM_Serie"));
										NR_Conhecimento = resTP
												.getLong("NR_Proximo");

										if ("M".equals(DM_Tipo_Documento)) {
											NR_Minuta = resTP
													.getLong("NR_Proximo");
										}
										if ("N".equals(DM_Tipo_Documento)) {
											NR_Nota_Fiscal_Servico = resTP
													.getLong("NR_Proximo");
										}
										if ("T".equals(DM_Tipo_Documento)) {
											NR_ACT = resTP
													.getLong("NR_Proximo");
										}
										if ("A".equals(DM_Tipo_Documento)) {
											NR_AWB = resTP
													.getLong("NR_Proximo");
										}

										Oid_AIDOF = resTP.getLong("OID_AIDOF");
										NR_Proximo = resTP
												.getLong("NR_Proximo") + 1;
										NR_Final = resTP.getLong("NR_FINAL");

										// /ROTINA PARA TESTAR NR IGUAIS
										System.out.println("testar->> "
												+ NR_Conhecimento);

										if (1 == 1) { // ed.getOID_Unidade ()
											// == 9999) { //nao esta
											// ativa
											int busca = 1;
											while (busca < 2) {
												sql = " SELECT oid_Conhecimento FROM Conhecimentos "
														+ " WHERE  Conhecimentos.oid_Unidade     = "
														+ ed.getOID_Unidade()
														+ " AND    Conhecimentos.NM_CH_CTe is not null "
														+ " AND    Conhecimentos.nm_serie = '"
														+ ed.getNM_Serie()
														+ "' "
														+ " AND    Conhecimentos.NR_Conhecimento = "
														+ NR_Conhecimento;
												resTP = this.executasql
														.executarConsulta(sql
																.toString());
												if (resTP.next()) {
													// NR_Conhecimento++;
													busca++;
												} else {
													busca = 999;
												}
											}

											if (busca == 2) {
												throw new Excecoes(
														"Verificar AIDOF CTE mesmo numero, para mesma filial!");
											}

										}
									}
								} finally {
									util.closeResultset(resTP);
								}

								if (NR_Proximo > NR_Final) {
									throw new Excecoes(
											"Nr pr�xima AIDOF maior que o n�mero final!");
								}

								sql = " UPDATE AIDOF SET NR_Proximo= "
										+ (NR_Conhecimento + 1);
								sql += " WHERE OID_AIDOF = " + Oid_AIDOF;

								executasql.executarUpdate(sql);
							}
						} else {
							NR_Conhecimento = ed.getNR_Conhecimento();
							ed.setNM_Serie(resCTRC.getString("NM_Serie"));
						}

						String Uni_Key = String.valueOf(1000 + Oid_AIDOF)
								.substring(1, 4)
								+ "-"
								+ String.valueOf(9000000 + NR_Conhecimento)
										.substring(1, 7)
								+ "-"
								+ ed.getNM_Serie();

						sql = " SELECT oid_Conhecimento FROM Conhecimentos "
								+ " WHERE  Conhecimentos.Uni_Key  = '"
								+ Uni_Key + "'";
						resTP = this.executasql
								.executarConsulta(sql.toString());
						if (resTP.next()) {
							throw new Excecoes("Chave prim�ria - " + Uni_Key
									+ " ja inserida !");
						}

						String DM_Impresso = "S";
						if ("S".equals(DM_Cte)) {
							DM_Impresso = "E";
						}

						if (!"MD".equals(DM_Tipo_Documento)
								&& !"T".equals(DM_Tipo_Documento)
								&& !"A".equals(DM_Tipo_Documento)) { // T=ACT
							sql = " update Conhecimentos set "
									+ " NR_Conhecimento = "
									+ NR_Conhecimento
									+ ", "
									+
									// " NR_DV = '" + calculaDV (String.valueOf
									// (NR_Conhecimento)) + "', " +
									" NR_Minuta = " + NR_Minuta + ", "
									+ " NR_Nota_Fiscal_Servico = "
									+ NR_Nota_Fiscal_Servico + ", "
									+ " NR_ACT = " + NR_ACT + ", "
									+ " NR_Sort_Impressao = "
									+ Nr_Sort_Impressao + ", "
									+ " NM_Serie = '" + ed.getNM_Serie()
									+ "', " + " DT_Stamp  = '"
									+ Data.getDataDMY()
									+ "', HR_Stamp='"
									+ Data.getHoraHM()
									+ ":"
									+ Data.getMileSegundo()
									+ "', "
									+
									// " File1 = '" + file1 + "', " +
									// " File2 = '" + file2 + "', " +
									// " File3 = '" + file3 + "', " +
									// " File4 = '" + file4 + "', " +
									" Uni_key = '" + Uni_Key + "', "
									+ " DM_Impresso = '" + DM_Impresso + "'";
						}

						if ("MD".equals(DM_Tipo_Documento)) { // minuta
							// despacho
							sql = " update Conhecimentos set "
									+ " DT_Stamp = '" + Data.getDataDMY()
									+ "' ";
						}

						if ("A".equals(DM_Tipo_Documento)) { // A=AWB
							sql = " update Conhecimentos set " + " NR_AWB = "
									+ NR_AWB;
						}

						if ("T".equals(DM_Tipo_Documento)) { // T=ACT
							sql = " update Conhecimentos set " + " NR_ACT = "
									+ NR_ACT + ", "
									+ " DM_Tipo_Documento ='C' ";
						}

						if (!"A".equals(DM_Tipo_Documento)) {
							sql += " ,DT_Emissao = '" + DT_Emissao_Conhecimento
									+ "' ";
						}

						sql += " ,HR_Emissao = '" + Data.getHoraHM() + "' ";

						if ("Danfe ---"
								.equals(resCTRC.getString("nm_natureza"))) {
							sql += " ,DT_Coleta = '" + DT_Emissao_Conhecimento
									+ "' ";
						}

						// observacao2
						String TX_Despacho = "CTe:"
								+ resCTRC.getString("CD_Unidade") + "-"
								+ NR_Conhecimento;
						sql += " ,TX_Despacho = '" + TX_Despacho + "' ";

						sql += " WHERE Conhecimentos.oid_Conhecimento = '"
								+ ed.getOID_Conhecimento() + "'";
						executasql.executarUpdate(sql);

						ed.setDT_Emissao(Data.getDataDMY());

						if (String.valueOf(ed.getNR_Conhecimento()) != null
								&& !String.valueOf(ed.getNR_Conhecimento())
										.equals("0")) {
							ed.setNR_Conhecimento(NR_Conhecimento);
						}

						if ("S".equals(DM_Tipo_Conhecimento)) { // COLETA
							// ELETRONICA
							// this.solicita_Coleta_Eletronica(ed);
						}
						// Livro Fiscal
						if ("C".equals(DM_Tipo_Documento)) {
							// C=Comum
							if ("S".equals(Parametro_FixoED.getInstancia()
									.getDM_Gera_Livro_Fiscal())) {
								// Livro_FiscalED livro = new Livro_FiscalED();
								// livro.setOID_Conhecimento(ed
								// .getOID_Conhecimento());
								// new Livro_FiscalBD(this.executasql)
								// .geraLivro_Fiscal_CTRC(livro);
							}
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Excecoes(e.getMessage(), e, getClass().getName(),
						"numeraCTe(ConhecimentoED ed)");
			} catch (Exception e) {
				e.printStackTrace();
				throw new Excecoes(e.getMessage(), e, getClass().getName(),
						"numeraCTe(ConhecimentoED ed)");
			}
		} finally {
			util.closeResultset(resCTRC);
		}

		System.out.println("numera CTe >> " + NR_Conhecimento);

	}

}