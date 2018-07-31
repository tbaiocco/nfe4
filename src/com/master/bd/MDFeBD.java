package com.master.bd;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.master.ed.ManifestoED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.ManipulaString;
import com.master.util.StringUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

import br.mdfe.core.base.EmpresaDb;
import br.mdfe.model.Empresa;
import br.mdfe.model.Mdfe;
import br.mdfe.model.MdfeAutXML;
import br.mdfe.model.MdfeCondutor;
import br.mdfe.model.MdfeConsulta;
import br.mdfe.model.MdfeEmit;
import br.mdfe.model.MdfeEvento;
import br.mdfe.model.MdfeInfANTT;
import br.mdfe.model.MdfeInfCT;
import br.mdfe.model.MdfeInfCTe;
import br.mdfe.model.MdfeInfContratante;
import br.mdfe.model.MdfeInfMunCarrega;
import br.mdfe.model.MdfeInfMunDescarga;
import br.mdfe.model.MdfeInfNF;
import br.mdfe.model.MdfeInfNFe;
import br.mdfe.model.MdfeInfPercurso;
import br.mdfe.model.MdfeLote;
import br.mdfe.model.MdfePeri;
import br.mdfe.model.MdfeProp;
import br.mdfe.model.MdfeRetornoEnvioLote;
import br.mdfe.model.MdfeRodo;
import br.mdfe.model.MdfeSeg;
import br.mdfe.model.MdfeValePed;
import br.mdfe.model.MdfeVeicReboque;
import br.mdfe.model.MdfeVeicTracao;

public class MDFeBD {

	private ExecutaSQL executasql;

	Utilitaria util = null;

	public MDFeBD(ExecutaSQL sql) {
		util = new Utilitaria(executasql);
		this.executasql = sql;
	}
	
	//UNILEVER - 21/08/2017
	public boolean isUnilever(ManifestoED ed) {
		boolean toReturn = false;
		try {
			ArrayList<String> cnpjs = new ArrayList<String>();
			String query = "SELECT oid_pessoa FROM CLIENTES where oid_grupo_economico = 9";
			ResultSet res = this.executasql.executarConsulta(query);
			while(res.next()) {
				cnpjs.add(res.getString("oid_pessoa"));
			}
			res.close();
			//CTes
			query = "SELECT Conhecimentos.NR_Conhecimento, Conhecimentos.oid_pessoa_pagador, Conhecimentos.oid_pessoa_destinatario, Conhecimentos.oid_pessoa"
					+ " FROM Conhecimentos, Viagens "
					+ " WHERE Conhecimentos.OID_Conhecimento = Viagens.OID_Conhecimento " +
							" AND Conhecimentos.DM_Tipo_Documento = 'C' "
					+ " AND   Viagens.OID_Manifesto ='"
					+ ed.getOID_Manifesto()
					+ "'"
					+ " Order by Viagens.NR_Sequencia, Conhecimentos.NR_Conhecimento ";
			res = this.executasql.executarConsulta(query);
			while(res.next()) {
				toReturn = ((res.getString("oid_pessoa_pagador") != null && cnpjs.contains(res.getString("oid_pessoa_pagador"))) 
    					|| (res.getString("oid_pessoa_destinatario") != null && cnpjs.contains(res.getString("oid_pessoa_destinatario"))) 
    					|| (res.getString("oid_pessoa") != null && cnpjs.contains(res.getString("oid_pessoa"))));
				System.out.println("CTe:" + res.getString("NR_Conhecimento") + "isUnilever?" + toReturn);
				
				if(toReturn)
					return toReturn;
			}
			
			//NFes
			query = "SELECT notas_fiscais.nr_nota_fiscal, notas_fiscais.oid_pessoa, notas_fiscais.oid_pessoa_destinatario "
					+ " FROM notas_fiscais, Viagens "
					+ " WHERE notas_fiscais.OID_nota_fiscal = Viagens.OID_nota_fiscal "
					+ " AND   Viagens.OID_Manifesto ='"
					+ ed.getOID_Manifesto()
					+ "'"
					+ " Order by Viagens.NR_Sequencia, notas_fiscais.nr_nota_fiscal ";
			while(res.next()) {
				toReturn = ((res.getString("oid_pessoa_destinatario") != null && cnpjs.contains(res.getString("oid_pessoa_destinatario"))) 
    					|| (res.getString("oid_pessoa") != null && cnpjs.contains(res.getString("oid_pessoa"))));
				System.out.println("NFe:" + res.getString("nr_nota_fiscal") + "isUnilever?" + toReturn);
				
				return toReturn;
			}
			
		} catch (Exception exc) {
//			throw new Excecoes("Erro ao buscar cnpj unilever", exc, this
//					.getClass().getName(), "getCNPJUnilever()");
			System.out.println("Erro ao buscar cnpj unilever...");
			exc.printStackTrace();
			return false;
		}
		return toReturn;
	}

	public void alteraEnvio(ManifestoED ed, MdfeLote retorno) throws Excecoes {

		String sql = null;

		try {

			sql = " update Manifestos set ";
			sql += " NM_CH_MDFe = '" + retorno.getMdfe().getChAcesso() + "' ";
			sql += " ,NM_Protocolo_Envio_MDFe = '" + retorno.getNRec() + "' ";
			sql += " ,DT_Envio_MDFe = '"
					+ new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss")
							.format(retorno.getData()) + "' ";
			sql += " ,DM_Status_MDFe = '" + "E" + "' ";
			sql += " ,NM_Status_MDFe = '" + "ENVIADO PARA SEFAZ" + "' ";
			sql += " where oid_Manifesto = '" + ed.getOID_Manifesto() + "'";
			System.out.println(sql);
			int i = executasql.executarUpdate(sql);

		}

		catch (Exception exc) {
			throw new Excecoes("Erro ao alterar envio MDFe", exc, this
					.getClass().getName(), "alteraEnvio()");
		}
	}

	public void alteraRetorno(ManifestoED ed, MdfeRetornoEnvioLote ret)
			throws Excecoes {

		String sql = null;

		try {

			sql = " update Manifestos set ";
			sql += " NM_Protocolo_Retorno_MDFe = '" + ret.getNProt() + "' ";
			sql += " ,DT_Recebimento_MDFe = '" + (ret.getDhRecbto()) + "' ";
			sql += " ,DM_Status_MDFe = '" + ret.getCStat() + "' ";
			sql += " ,NM_Status_MDFe = '"
					+ ManipulaString.tiraAspas(ret.getXMotivo()) + "' ";
			// sql += " ,nfe_digestvalue = '" + ret.getDigVal() + "' ";
			if ("100".equals(ret.getCStat())) {
				// sql += ", DM_Impresso='S' ";
			}
			sql += " where oid_Manifesto = '" + ed.getOID_Manifesto() + "'";
			System.out.println(sql);
			int i = executasql.executarUpdate(sql);

		}

		catch (Exception exc) {
			throw new Excecoes("Erro ao alterar retorno MDFe", exc, this
					.getClass().getName(), "alteraRetorno()");
		}
	}

	public void alteraCancelamento(ManifestoED ed, MdfeEvento ret)
			throws Excecoes {

		String sql = null;

		try {

			sql = " update Manifestos set ";
			sql += " NM_Protocolo_Cancelamento_MDFe = '"
					+ ret.getNProtRegEvento() + "' ";
			sql += " ,DT_Envio_Cancelamento_MDFe = '" + (ret.getDhRegEvento())
					+ "' ";
			sql += " ,DT_Recebimento_Cancelamento_MDFe = '"
					+ (ret.getDhRegEvento()) + "' ";
			sql += " ,DM_Status_Cancelamento_MDFe = '" + ret.getCStat() + "' ";
			sql += " ,NM_Status_Cancelamento_MDFe = '" + ret.getXMotivo()
					+ "' ";
			sql += " where oid_Manifesto = '" + ed.getOID_Manifesto() + "'";

			int i = executasql.executarUpdate(sql);

			sql = " delete from viagens where oid_Manifesto = '" + ed.getOID_Manifesto() + "'";

			executasql.executarUpdate(sql);

		}

		catch (Exception exc) {
			throw new Excecoes("Erro ao alterar cancelamento MDFe", exc, this
					.getClass().getName(), "alteraCancelamento()");
		}
	}

	public void alteraEncerramento(ManifestoED ed, MdfeEvento ret)
			throws Excecoes {

		String sql = null;

		try {

			sql = " update Manifestos set ";
			sql += " NM_Protocolo_Encerramento_MDFe = '"
					+ ret.getNProtRegEvento() + "' ";
			sql += " ,DT_Envio_Encerramento_MDFe = '" + (ret.getDhRegEvento())
					+ "' ";
			sql += " ,DT_Recebimento_Encerramento_MDFe = '"
					+ (ret.getDhRegEvento()) + "' ";
			sql += " ,DM_Status_Encerramento_MDFe = '" + ret.getCStat() + "' ";
			sql += " ,NM_Status_Encerramento_MDFe = '" + ret.getXMotivo()
					+ "' ";
			sql += " where oid_Manifesto = '" + ed.getOID_Manifesto() + "'";

			int i = executasql.executarUpdate(sql);
		}

		catch (Exception exc) {
			throw new Excecoes("Erro ao alterar encerramento MDFe", exc, this
					.getClass().getName(), "alteraEncerramento()");
		}
	}

	public void atualizaMDFe(ManifestoED ed, MdfeConsulta ret) throws Excecoes {

		String sql = null;

		try {

			sql = " update Manifestos set ";
			sql += " NM_Protocolo_Retorno_MDFe = '" + ret.getNProt() + "' ";
			sql += " ,DT_Recebimento_MDFe = '" + (ret.getDhRecbto()) + "' ";
			sql += " ,DM_Status_MDFe = '" + ret.getCStat() + "' ";
			sql += " ,NM_Status_MDFe = '"
					+ ManipulaString.tiraAspas(ret.getXMotivo()) + "' ";
			// sql += " ,nfe_digestvalue = '" + ret.getDigVal() + "' ";
			if ("100".equals(ret.getCStat())) {
				// sql += ", DM_Impresso='S' ";
			}
			sql += " where oid_Manifesto = '" + ed.getOID_Manifesto() + "'";
			System.out.println(sql);
			int i = executasql.executarUpdate(sql);

		}

		catch (Exception exc) {
			throw new Excecoes("Erro ao alterar retorno MDFe", exc, this
					.getClass().getName(), "alteraRetorno()");
		}
	}

	public void numeraMDFe(ManifestoED ed) throws Excecoes {

		// numero eh na inclusao

	}

	public Mdfe getDados(ManifestoED ed, boolean envio) throws Excecoes {

		Mdfe mdfe = new Mdfe();
		String RNTRC = "";
		try {
			String query = "SELECT manifestos.*, "
					+ " Manifestos.oid_Pessoa as oid_Pessoa_Motorista, "
					+ " Pessoa_Proprietario.oid_Pessoa as oid_Pessoa_Proprietario, "
					+ " Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario, "
					+ " Pessoa_Motorista.NM_Razao_Social as NM_Motorista, "
					+ " Veiculos.NR_Placa, unidades.rntrc, "
					+ " Cidade_Manifesto.NM_Cidade as NM_Cidade_Manifesto, "
					+ " pesUni.nr_cnpj_cpf as unidade"
					+ " FROM Manifestos, unidades, Veiculos, Cidades Cidade_Manifesto, Pessoas Pessoa_Proprietario, Pessoas Pessoa_Motorista, pessoas pesuni "
					+ " WHERE  Manifestos.OID_Veiculo = Veiculos.OID_Veiculo "
					+ " AND Manifestos.OID_Cidade = Cidade_Manifesto.OID_Cidade "
					+ " AND Manifestos.OID_Pessoa = Pessoa_Motorista.Oid_Pessoa "
					+ " AND Veiculos.OID_Pessoa_Proprietario = Pessoa_Proprietario.oid_Pessoa "
					+ " AND Manifestos.oid_unidade = Unidades.oid_unidade "
					+ "AND Unidades.oid_pessoa = pesuni.oid_pessoa "
					+ " AND Manifestos.oid_manifesto = '"
					+ ed.getOID_Manifesto() + "'";
			if (envio) {
				query += " AND (Manifestos.DM_Status_MDFe is null or Manifestos.DM_Status_MDFe != '100') ";
			}
			System.out.println(query);
			ResultSet res = this.executasql.executarConsulta(query);
			if (res != null) {
				while (res.next()) {

					String ufIni = "", ufFim = "";

					Empresa empresa = new EmpresaDb().getEmpresa(res
							.getString("unidade"));
					Integer amb = new Integer(JavaUtil.getValueDef(empresa
							.getAmbiente(), "2")); // 1 - prod , 2 - homo
					// PROVISORIO homologa
//					amb = 2;

					RNTRC = res.getString("RNTRC");
					if (!JavaUtil.doValida(RNTRC)) {
						RNTRC = "12345678";
					}

//					mdfe.setVersao("1.00");
//					mdfe.setVersaoModal("1.00");
//					//versao 3.00
					mdfe.setVersao("3.00");
					mdfe.setVersaoModal("3.00");
					
					mdfe.setChAcesso(res.getString("NM_CH_MDFe"));
					mdfe.setNProt(res.getString("NM_Protocolo_Retorno_MDFe"));
					mdfe.setDhEmi(res.getDate("DT_Envio_MDFe"));
					if(JavaUtil.doValida(res.getString("DT_Recebimento_MDFe")))
						mdfe.setDhRecbto(res.getDate("DT_Recebimento_MDFe"));

					/**
					 * DADOS DA MDFE
					 */
					mdfe.setCUF(new Integer(JavaUtil.getValueDef(String
							.valueOf(empresa.getcUf()), "43")));
					mdfe.setTpAmb(amb);
					if(JavaUtil.doValida(res.getString("dm_tipo_manifesto")) &&
							(res.getString("dm_tipo_manifesto").equals("RI") || res.getString("dm_tipo_manifesto").equals("RN")))
						mdfe.setTpEmit(2);
					else
						mdfe.setTpEmit(1); // 1 - transporte de carga de terceiros
					// | 2 - carga pr�pria >>>
					// OBS: quando tpemit for 2 � para preencher as nfes e
					// quando for 1 � o CTe
					mdfe.setMod(58);
					String oid_separado = StringUtil.retornaNumeroValido(res
							.getString("oid_Manifesto"));
					mdfe.setCMDF(new Integer(StringUtil.alinhaNumeroDireita(
							(oid_separado + "00000000").substring(0, 8), 8)));
					mdfe.setSerie(res.getString("nm_serie"));
					mdfe.setNMDF(new Integer(res.getString("NR_Manifesto")));// nro
					// mdfe
					mdfe.setModal(1);
					String data = res.getString("dt_emissao");
					// if (StringUtil.doValida(res.getString("hr_emissao")))
					// data += " " + res.getString("hr_emissao") + ":"
					// + Data.getMileSegundo();
					// else
					data += " " + Data.getHoraHM() + ":"
							+ Data.getMileSegundo();
					mdfe.setDhEmi(StringUtil.stringToCalendar(data,
							"yyyy-MM-dd hh:mm:ss").getTime());
					mdfe.setTpEmis(1);
					mdfe.setProcEmi(0);
					mdfe.setVerProc("0.001");

					/**
					 * EMITENTE 
					 */
					MdfeEmit emitente = new MdfeEmit();
					String queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade "
							+ " FROM pessoas, cidades, regioes_estados, estados "
							+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   pessoas.oid_pessoa = '"
							+ res.getString("unidade") + "'";
					System.out.println(queryAux);
					ResultSet resAux = this.executasql
							.executarConsulta(queryAux);

					String uf = "RS";
					while (resAux.next()) {
						String cmun = "0";
						String cuf = "43";
						uf = resAux.getString("cd_estado");
						cuf = resAux.getString("nm_codigo_ibge");
						cmun = resAux.getString("cd_cid");

						emitente.setCNPJ(resAux.getString("oid_pessoa"));// CNPJ
						// emitente

						if (amb != 1)
							emitente
									.setXNome("MDF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");// Raz�o
						else
							emitente.setXNome(resAux.getString(
									"nm_razao_social").trim());
						// Social/Nome
						emitente.setXFant(resAux.getString("nm_fantasia")
								.trim());// Nome fantasia
						if (!JavaUtil.doValida(resAux
								.getString("nm_inscricao_estadual"))
								&& !"0".equals(resAux
										.getString("nm_inscricao_estadual")))
							throw new Excecoes("IE do Emissor � inv�lida!");
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
						emitente.setFone(StringUtil.retornaNumeroValido(resAux
								.getString("nr_telefone"), 12));
						emitente.setEmail(resAux.getString("email"));
					}

					/**
					 * MODAL RODOVIARIO
					 */
					MdfeRodo rodo = new MdfeRodo();
					rodo.setRNTRC(RNTRC.trim());
//					rodo.setCIOT("111222333444");

					// TRACAO
					queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade "
							+ " , veiculos.nr_rntrc, complementos_veiculos.vl_tara, complementos_veiculos.nr_peso_limite, veiculos.oid_cidade, "
							+ " tipos_veiculos.dm_tipo_implemento, veiculos.oid_veiculo, veiculos.nr_placa, e2.cd_estado as ufVeiculo "
							+ "FROM veiculos left join complementos_veiculos"
							+ " on veiculos.oid_veiculo = complementos_veiculos.oid_veiculo, modelos_veiculos, tipos_veiculos, pessoas, cidades, regioes_estados, estados"
							+ " ,cidades c2, regioes_estados r2, estados e2 "
							+ " WHERE veiculos.oid_pessoa_proprietario = pessoas.oid_pessoa "
							+ " AND veiculos.oid_modelo_veiculo = modelos_veiculos.oid_modelo_veiculo "
							+ " AND modelos_veiculos.oid_tipo_veiculo = tipos_veiculos.oid_tipo_veiculo "
							+ " AND pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND veiculos.oid_cidade = c2.oid_cidade"
							+ " AND   c2.oid_regiao_estado = r2.oid_regiao_estado "
							+ " AND   r2.oid_estado = e2.oid_estado "
							+ " AND veiculos.oid_veiculo = '"
							+ res.getString("oid_veiculo") + "'";
					System.out.println(queryAux);
					resAux = this.executasql.executarConsulta(queryAux);
					MdfeVeicTracao tracao = new MdfeVeicTracao();
					while (resAux.next()) {
						String aux = StringUtil.retornaNumeroValido(resAux
								.getString("oid_veiculo"));
						tracao.setCInt(new Integer(StringUtil
								.alinhaNumeroDireita((aux + "0000").substring(
										0, 4), 4)));
						tracao.setPlaca(resAux.getString("NR_Placa"));
						tracao.setTara(resAux.getDouble("vl_tara"));
						if (tracao.getTara() <= 0) {
							// throw new Excecoes("tara obrigatoria para o
							// veiculo);
							tracao.setTara(5000d);
						}
						tracao.setCapKG(resAux.getDouble("nr_peso_limite"));
						tracao.setCapM3(30d);

						// Propriet�rios do Ve�culo. S� preenchido quando o
						// ve�culo
						// n�o pertencer � empresa emitente do MDF-e
						MdfeProp prop = null;
						if (JavaUtil.doValida(resAux.getString("oid_Pessoa"))
								&& !emitente.getCNPJ().equals(
										resAux.getString("oid_Pessoa"))) {
							prop = new MdfeProp();
							if (resAux.getString("oid_Pessoa").length() > 11)
								prop.setCNPJ(resAux.getString("oid_Pessoa"));
							else
								prop.setCPF(resAux.getString("oid_Pessoa"));
							prop.setXNome(resAux.getString("nm_razao_social").trim());
							prop.setUF(resAux.getString("cd_estado"));
							prop.setIE(JavaUtil.getValueDef(resAux
									.getString("nm_inscricao_estadual"), "0"));

							if (JavaUtil.doValida(resAux.getString("nr_rntrc"))) {
								prop.setRNTRC(JavaUtil.getValueDef(ManipulaString.retornaNumeroValido(resAux.getString("nr_rntrc")).trim(),
										"12345678"));
								if(ManipulaString.retornaNumeroValido(resAux.getString("nr_rntrc")).trim().length()<8){
									throw new Excecoes(" RNTRC do Cavalo inv�lido!");
								}
							} else {
//								prop.setRNTRC("12345678");
								throw new Excecoes(" RNTRC do Cavalo inv�lido!");
							}

							prop.setTpProp(1);// Tipo Propriet�rio ESSE EH O
							// PADRAO
						}
						tracao.setProp(prop);

						ArrayList<MdfeCondutor> lCond = new ArrayList<MdfeCondutor>();
						MdfeCondutor cond = new MdfeCondutor();
						cond.setCPF(res.getString("oid_Pessoa_Motorista"));
						cond.setXNome(res.getString("nm_motorista").trim());
						lCond.add(cond);

						// MdfeCondutor cond2 = new MdfeCondutor();
						// cond2.setCPF("59437282870");
						// cond2.setXNome("TEO BAIOCCO");
						// lCond.add(cond2);

						tracao.setCondutor(lCond);
						String tp = "03";
						if (JavaUtil.doValida(resAux
								.getString("dm_tipo_Implemento"))) {
							if (resAux.getString("dm_tipo_Implemento").equals(
									"6"))
								tp = "01";
							if (resAux.getString("dm_tipo_Implemento").equals(
									"5"))
								tp = "02";
							if (resAux.getString("dm_tipo_Implemento").equals(
									"7"))
								tp = "04";
							if (resAux.getString("dm_tipo_Implemento").equals(
									"8"))
								tp = "05";
							if (resAux.getString("dm_tipo_Implemento").equals(
									"9")
									|| resAux.getString("dm_tipo_Implemento")
											.equals("10"))
								tp = "06";
						}
						tracao.setTpRod(new Integer(tp));// 01 - Truck; 02 -
						// Toco; 03 -
						// Cavalo; 04 - Van;
						// 05 - Utilitario;
						// 06 - Outros
						tracao.setTpCar(0);// 00 - n�o aplic�vel;
						tracao.setUF(resAux.getString("ufVeiculo"));
					}
					rodo.setVeicTracao(tracao);

					// REBOQUE
					ArrayList<MdfeVeicReboque> lReb = new ArrayList<MdfeVeicReboque>();
					if (JavaUtil.doValida(res.getString("oid_Veiculo_Carreta"))) {
						queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade "
								+ " , veiculos.nr_rntrc, complementos_veiculos.vl_tara, complementos_veiculos.nr_peso_limite, veiculos.oid_cidade, "
								+ " tipos_veiculos.dm_tipo_implemento, veiculos.oid_veiculo, veiculos.nr_placa, e2.cd_estado as ufVeiculo "
								+ "FROM veiculos left join complementos_veiculos "
								+ " on veiculos.oid_veiculo = complementos_veiculos.oid_veiculo, modelos_veiculos, tipos_veiculos, pessoas, cidades, regioes_estados, estados"
								+ " ,cidades c2, regioes_estados r2, estados e2 "
								+ " WHERE veiculos.oid_pessoa_proprietario = pessoas.oid_pessoa "
								+ " AND veiculos.oid_modelo_veiculo = modelos_veiculos.oid_modelo_veiculo "
								+ " AND modelos_veiculos.oid_tipo_veiculo = tipos_veiculos.oid_tipo_veiculo "
								+ " AND pessoas.oid_cidade = cidades.oid_cidade"
								+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
								+ " AND   regioes_estados.oid_estado = estados.oid_estado "
								+ " AND veiculos.oid_cidade = c2.oid_cidade"
								+ " AND   c2.oid_regiao_estado = r2.oid_regiao_estado "
								+ " AND   r2.oid_estado = e2.oid_estado "
								+ " AND   veiculos.oid_veiculo = '"
								+ res.getString("oid_Veiculo_Carreta") + "'";
						resAux = this.executasql.executarConsulta(queryAux);
						MdfeVeicReboque reboque = new MdfeVeicReboque();
						while (resAux.next()) {
							String aux = StringUtil.retornaNumeroValido(resAux
									.getString("oid_veiculo"));
							reboque.setCInt(new Integer(StringUtil
									.alinhaNumeroDireita((aux + "0000")
											.substring(0, 4), 4)));
							reboque.setPlaca(resAux.getString("NR_Placa"));
							reboque.setTara(resAux.getDouble("vl_tara"));
							reboque
									.setCapKG(resAux
											.getDouble("nr_peso_limite"));
							reboque.setCapM3(30d);

							// Propriet�rios do Ve�culo. S� preenchido quando o
							// ve�culo
							// n�o pertencer � empresa emitente do MDF-e
							MdfeProp propreb = null;
							if (JavaUtil.doValida(resAux
									.getString("oid_Pessoa"))
									&& !emitente.getCNPJ().equals(
											res.getString("oid_Pessoa"))) {

								propreb = new MdfeProp();
								if (resAux.getString("oid_Pessoa").length() > 11)
									propreb.setCNPJ(resAux
											.getString("oid_Pessoa"));
								else
									propreb.setCPF(resAux
											.getString("oid_Pessoa"));
								propreb.setXNome(resAux
										.getString("nm_razao_social").trim());
								propreb.setUF(resAux.getString("cd_estado"));
								propreb.setIE(JavaUtil.getValueDef(resAux
										.getString("nm_inscricao_estadual"), "0"));
								if (JavaUtil.doValida(resAux.getString("nr_rntrc"))) {
									propreb.setRNTRC(JavaUtil.getValueDef(ManipulaString.retornaNumeroValido(resAux.getString("nr_rntrc")).trim(),
											"12345678"));
									if(ManipulaString.retornaNumeroValido(resAux.getString("nr_rntrc")).trim().length()<8){
										throw new Excecoes(" RNTRC da CARRETA inv�lido!");
									}
								} else {
//									propreb.setRNTRC("12345678");
									throw new Excecoes(" RNTRC da CARRETA inv�lido!");
								}
								propreb.setTpProp(1);// Tipo Propriet�rio
								// ESSE EH O

							}
							reboque.setProp(propreb);
							reboque.setTpCar(02);// 01 - aberta; 02-fechada;
							// 03-graneleira; 04-porta
							// conteiner; 05-sider
							reboque.setUF(resAux.getString("ufVeiculo"));
							lReb.add(reboque);
						}

						rodo.setVeicReboque(lReb);
					}

					// VALE pedagio, nao tem...
					ArrayList<MdfeValePed> lVped = null; //new ArrayList<MdfeValePed>();
//					MdfeValePed vped = new MdfeValePed();
//					vped.setCNPJForn("91665554000163");
//					vped.setCNPJPg("91665554000163");
//					vped.setNCompra("123423434");
//					lVped.add(vped);
					//
					// vped = new MdfeValePed();
					// vped.setCNPJForn("08593038000127");
					// vped.setCNPJPg("08593038000127");
					// vped.setNCompra("123423435");
					// lVped.add(vped);
					
//					rodo.setValePed(lVped);
					
					MdfeInfANTT antt = new MdfeInfANTT();
//					antt.setInfCIOT(new ArrayList<MdfeInfCIOT>());
					antt.setInfContratantes(new ArrayList<MdfeInfContratante>());
					antt.setRNTRC(RNTRC);
//					MdfeInfCIOT ciot = new MdfeInfCIOT();
//					ciot.setCIOT("123456");
//					ciot.setCNPJ(emitente.getCNPJ());
//					antt.getInfCIOT().add(ciot);
					
					MdfeInfContratante contrat = new MdfeInfContratante();
					//tomadores de servi�o, obrigat�rio
					queryAux = "SELECT distinct Conhecimentos.OID_Pessoa_Pagador "
							+ " FROM Conhecimentos, Viagens "
							+ " WHERE Conhecimentos.OID_Conhecimento = Viagens.OID_Conhecimento " +
									" AND Conhecimentos.DM_Tipo_Documento = 'C' "
							+ " AND   Viagens.OID_Manifesto ='"
							+ res.getString("OID_Manifesto")
							+ "'"
							+ " limit 1 ";
					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						contrat = new MdfeInfContratante();
						contrat.setCNPJ(resAux.getString("OID_Pessoa_Pagador"));
						antt.getInfContratantes().add(contrat);
					}
//					queryAux = "SELECT distinct notas_fiscais.oid_pessoa "
//							+ " FROM notas_fiscais, Viagens "
//							+ " WHERE notas_fiscais.OID_nota_fiscal = Viagens.OID_nota_fiscal "
//							+ " AND   Viagens.OID_Manifesto ='"
//							+ res.getString("OID_Manifesto")
//							+ "'"
//							+ " limit 1 ";
//						resAux = this.executasql.executarConsulta(queryAux);
//						while (resAux.next()) {
//							contrat = new MdfeInfContratante();
//							contrat.setCNPJ(resAux.getString("OID_Pessoa"));
//							antt.getInfContratantes().add(contrat);
//						}
//					contrat.setCNPJ("64904295003471");
//					antt.getInfContratantes().add(contrat);
					
					rodo.setInfANTT(antt);

					mdfe.setRodo(rodo);
					
					//seguradora
//					queryAux = "Select * "
//							+ " FROM seguradoras "
//							+ " WHERE oid_seguradora = "
//							+ res.getString("oid_seguradora");
//					resAux = this.executasql.executarConsulta(queryAux);
//					if (resAux.next()) {
//						
//					}
					ArrayList<MdfeSeg> segs = new ArrayList<MdfeSeg>();
					MdfeSeg seg = new MdfeSeg();
					seg.setnApol("9999");
					seg.setnAver("8888");
					seg.setxSeg("APISUL");
					seg.setCnpjSeg("00000000000000");
					seg.setRespSeg(1);
					seg.setCNPJ("00000000000000");
					segs.add(seg);
					mdfe.setSeg(segs);

					double vcarga = 0;
					double qcarga = 0;
					double cunid = 0;
					ArrayList<MdfeInfMunDescarga> lMunDesc = new ArrayList<MdfeInfMunDescarga>();
					// DESCARGA
					queryAux = "Select ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade, estados.oid_estado "
							+ " FROM cidades, regioes_estados, estados "
							+ " WHERE cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   cidades.oid_cidade = "
							+ res.getString("oid_cidade");
					resAux = this.executasql.executarConsulta(queryAux);
					if (resAux.next()) {
						String cmun = "0";
						String cuf = "43";
						cuf = resAux.getString("nm_codigo_ibge");
						cmun = resAux.getString("cd_cid");

						mdfe.setUFFim(resAux.getString("cd_estado"));
						ufFim = resAux.getString("oid_estado");

						if (!JavaUtil.doValida(cmun))
							throw new Excecoes(
									"C�D. IBGE Munic�pio do CARGA inv�lido!");

						MdfeInfMunDescarga desc = new MdfeInfMunDescarga();
						desc.setCMunDescarga(Integer.parseInt(ManipulaString
								.limpaCampo((JavaUtil.doValida(cuf) ? cuf : "")
										+ cmun)));
						desc.setXMunDescarga(resAux.getString("nm_cidade")
								.trim());
						if (String.valueOf(desc.getCMunDescarga().intValue())
								.length() != 7)
							throw new Excecoes(
									"C�D. IBGE Munic�pio do Emissor inv�lido!");

						/**
						 * INFORMACAO DE DOCUMENTOS
						 */
						ArrayList<MdfeInfNFe> lNfe = new ArrayList<MdfeInfNFe>();
						ArrayList<MdfeInfNF> lNf = new ArrayList<MdfeInfNF>();
						ArrayList<MdfeInfCT> lCt = new ArrayList<MdfeInfCT>();
						ArrayList<MdfeInfCTe> lCte = new ArrayList<MdfeInfCTe>();
						if (mdfe.getTpEmit() == 2) {
							// NFE
							int qnf = 0;
							int qnfe = 0;
							queryAux = "SELECT notas_fiscais.nr_nota_fiscal, notas_fiscais.nfe_chave_acesso,"
								+ " notas_fiscais.nm_serie, notas_fiscais.vl_nota_fiscal, notas_fiscais.dt_emissao"
								+ " ,notas_fiscais.nr_peso, '0' as nr_volumes, notas_fiscais.oid_pessoa, estados.cd_estado "
								+ " FROM notas_fiscais, Viagens, pessoas, cidades, regioes_estados, estados "
								+ " WHERE notas_fiscais.OID_nota_fiscal = Viagens.OID_nota_fiscal "
								+ " AND   notas_fiscais.OID_pessoa = pessoas.oid_pessoa "
								+ " AND   pessoas.OID_cidade = cidades.OID_cidade "
								+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
								+ " AND   regioes_estados.oid_estado = estados.oid_estado "
								+ " AND   Viagens.OID_Manifesto ='"
								+ res.getString("OID_Manifesto")
								+ "'"
								+ " Order by Viagens.NR_Sequencia, notas_fiscais.nr_nota_fiscal ";
							resAux = this.executasql.executarConsulta(queryAux);
							while (resAux.next()) {
								vcarga += resAux.getDouble("vl_nota_fiscal");
								qcarga += resAux.getDouble("nr_peso");
								cunid += resAux.getDouble("nr_volumes");
								 if (!JavaUtil.doValida(resAux
										.getString("nfe_chave_acesso"))) {
									MdfeInfNF nf = new MdfeInfNF();
									nf.setCNPJ(resAux.getString("oid_pessoa"));
									nf.setNNF(resAux
											.getString("nr_nota_fiscal"));
									nf.setSerie(resAux
											.getString("nm_serie"));
									nf.setUF(resAux.getString("cd_estado"));
									nf.setVNF(resAux.getDouble("vl_nota_fiscal"));
									String emi = resAux.getString("dt_emissao");
									emi += " " + Data.getHoraHM() + ":"
											+ Data.getMileSegundo();
									nf.setdEmi(StringUtil.stringToCalendar(
											data, "yyyy-MM-dd hh:mm:ss")
											.getTime());
									lNf.add(nf);
									desc.setInfNF(lNf);
								} else {
									MdfeInfNFe nfe = new MdfeInfNFe();
									nfe
											.setChNFe(resAux
													.getString("nfe_chave_acesso"));
									lNfe.add(nfe);
									desc.setInfNFe(lNfe);
								}
							}
							mdfe.setQNF(qnf);
							mdfe.setQNFe(qnfe);
						} else {
							// CTE e CTRC
							int qct = 0;
							int qcte = 0;
							int qnf = 0;
							int qnfe = 0;
							queryAux = "SELECT Conhecimentos.NR_Conhecimento, Conhecimentos.NM_CH_CTe,"
									+ " Conhecimentos.nm_serie, Conhecimentos.vl_nota_fiscal, conhecimentos.dt_emissao"
									+ " ,conhecimentos.nr_peso, conhecimentos.nr_volumes, Conhecimentos.OID_Conhecimento "
									+ " FROM Conhecimentos, Viagens "
									+ " WHERE Conhecimentos.OID_Conhecimento = Viagens.OID_Conhecimento " +
											" AND Conhecimentos.DM_Tipo_Documento = 'C' "
									+ " AND   Viagens.OID_Manifesto ='"
									+ res.getString("OID_Manifesto")
									+ "'"
									+ " Order by Viagens.NR_Sequencia, Conhecimentos.NR_Conhecimento ";
							resAux = this.executasql.executarConsulta(queryAux);
							while (resAux.next()) {
								vcarga += resAux.getDouble("vl_nota_fiscal");
								qcarga += resAux.getDouble("nr_peso");
								cunid += resAux.getDouble("nr_volumes");
								if (!JavaUtil.doValida(resAux
										.getString("nm_ch_cte"))) {
									MdfeInfCT ct = new MdfeInfCT();
									String emi = resAux.getString("dt_emissao");
									emi += " " + Data.getHoraHM() + ":"
											+ Data.getMileSegundo();
									ct.setdEmi(StringUtil.stringToCalendar(
											data, "yyyy-MM-dd hh:mm:ss")
											.getTime());
									ct.setNCT(resAux
											.getString("nr_conhecimento"));
									ct.setSerie(resAux.getString("nm_serie"));
									ct.setVCarga(resAux
											.getDouble("vl_nota_fiscal"));
									lCt.add(ct);
									qct++;
								} else {
									MdfeInfCTe cte = new MdfeInfCTe();
									cte.setChCTe(resAux.getString("nm_ch_cte"));
									lCte.add(cte);
									qcte++;
									
									cte.setPeri(new ArrayList<MdfePeri>());
								}

								if (!envio) {
									//NFEs
									String queryAuxNF = "SELECT notas_fiscais.nr_nota_fiscal, notas_fiscais.nfe_chave_acesso,"
										+ " notas_fiscais.nm_serie, notas_fiscais.vl_nota_fiscal, notas_fiscais.dt_emissao"
										+ " ,notas_fiscais.nr_peso, '0' as nr_volumes, notas_fiscais.oid_pessoa, estados.cd_estado "
										+ " FROM notas_fiscais, conhecimentos_notas_fiscais, pessoas, cidades, regioes_estados, estados "
										+ " WHERE notas_fiscais.OID_nota_fiscal = conhecimentos_notas_fiscais.OID_nota_fiscal "
										+ " AND   notas_fiscais.OID_pessoa = pessoas.oid_pessoa "
										+ " AND   pessoas.OID_cidade = cidades.OID_cidade "
										+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
										+ " AND   regioes_estados.oid_estado = estados.oid_estado "
										+ " AND   conhecimentos_notas_fiscais.OID_conhecimento ='"
										+ resAux.getString("OID_Conhecimento")
										+ "'"
										+ " Order by notas_fiscais.nr_nota_fiscal ";
									ResultSet resAuxNF = this.executasql.executarConsulta(queryAuxNF);
									while (resAuxNF.next()) {
//										vcarga += resAuxNF.getDouble("vl_nota_fiscal");
//										qcarga += resAuxNF.getDouble("nr_peso");
//										cunid += resAuxNF.getDouble("nr_volumes");
										 if (!JavaUtil.doValida(resAuxNF
												.getString("nfe_chave_acesso"))) {
											MdfeInfNF nf = new MdfeInfNF();
											nf.setCNPJ(resAuxNF.getString("oid_pessoa"));
											nf.setNNF(resAuxNF
													.getString("nr_nota_fiscal"));
											nf.setSerie(resAuxNF
													.getString("nm_serie"));
											nf.setUF(resAuxNF.getString("cd_estado"));
											nf.setVNF(resAuxNF.getDouble("vl_nota_fiscal"));
											String emi = resAuxNF.getString("dt_emissao");
											emi += " " + Data.getHoraHM() + ":"
													+ Data.getMileSegundo();
											nf.setdEmi(StringUtil.stringToCalendar(
													data, "yyyy-MM-dd hh:mm:ss")
													.getTime());
											lNf.add(nf);
											qnf++;
										} else {
											MdfeInfNFe nfe = new MdfeInfNFe();
											nfe
													.setChNFe(resAuxNF
															.getString("nfe_chave_acesso"));
											lNfe.add(nfe);
											qnfe++;
										}
									}
								}
								
							}
							mdfe.setQCT(qct);
							mdfe.setQCTe(qcte);
							mdfe.setQNF(qnf);
							mdfe.setQNFe(qnfe);
						}
						desc.setInfCT(lCt);
						desc.setInfCTe(lCte);
						
						desc.setInfNF(lNf);
						desc.setInfNFe(lNfe);

						lMunDesc.add(desc);
					}
					mdfe.setInfMunDescarga(lMunDesc);

					ArrayList<MdfeInfMunCarrega> lMunCarga = new ArrayList<MdfeInfMunCarrega>();
					// CARGA
					queryAux = "Select ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade, estados.oid_estado "
							+ " FROM cidades, regioes_estados, estados "
							+ " WHERE cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   cidades.oid_cidade = "
							+ res.getString("oid_cidade_origem");
					resAux = this.executasql.executarConsulta(queryAux);
					while (resAux.next()) {
						String cmun = "0";
						String cuf = "43";
						cuf = resAux.getString("nm_codigo_ibge");
						cmun = resAux.getString("cd_cid");

						mdfe.setUFIni(resAux.getString("cd_estado"));
						ufIni = resAux.getString("oid_estado");

						if (!JavaUtil.doValida(cmun))
							throw new Excecoes(
									"C�D. IBGE Munic�pio do CARGA inv�lido!");

						MdfeInfMunCarrega carga = new MdfeInfMunCarrega();
						carga.setCMunCarrega(Integer.parseInt(ManipulaString
								.limpaCampo((JavaUtil.doValida(cuf) ? cuf : "")
										+ cmun)));
						carga.setXMunCarrega(resAux.getString("nm_cidade")
								.trim());
						if (String.valueOf(carga.getCMunCarrega().intValue())
								.length() != 7)
							throw new Excecoes(
									"C�D. IBGE Munic�pio do Emissor inv�lido!");

						lMunCarga.add(carga);
					}
					mdfe.setInfMunCarrega(lMunCarga);
					// CARGA so num municipio
					// MdfeInfMunCarrega cmun2 = new MdfeInfMunCarrega();
					// cmun2.setCMunCarrega(4311403);
					// cmun2.setXMunCarrega("LAJEADO");
					// lMunCarga.add(cmun2);

					ArrayList<String> lacres = new ArrayList<String>();
					// lacres.add("AKUX10021KX");
//					lacres.add("AKUX10020KX");
//					mdfe.setNLacre(lacres);

					mdfe.setInfCpl("");
					mdfe.setInfAdFisco("I");

					ArrayList<MdfeAutXML> lAutXml = new ArrayList<MdfeAutXML>();
					MdfeAutXML a = new MdfeAutXML();
					a.setCNPJ("");
					lAutXml.add(a);
//					mdfe.setAutXML(lAutXml);

					mdfe.setEmit(emitente);
					System.out.println("FIM...");
					// totais
					mdfe.setVCarga(vcarga);
					mdfe.setQCarga(qcarga);
					mdfe.setCUnid(01); // 01-KG; 02-TON

					// percurso
					ArrayList<MdfeInfPercurso> rota = new ArrayList<MdfeInfPercurso>();
					String percurso = "";
					queryAux = " select * from percursos where oid_estado_origem = "
							+ ufIni + " AND oid_estado_destino = " + ufFim;
					resAux = this.executasql.executarConsulta(queryAux);
					if (resAux.next()) {
						percurso = resAux.getString("nm_percurso");
					}
					if (JavaUtil.doValida(percurso)) {
						String[] it = percurso.split(",");
						for (String auxR : it) {
							MdfeInfPercurso perc = new MdfeInfPercurso();
							perc.setUFPer(auxR);
							rota.add(perc);
						}
					}
					mdfe.setInfPercurso(rota);

				}
			}
			res.close();

		} catch (Excecoes exc) {
			exc.printStackTrace();
			throw exc;
		} catch (Exception exc) {
			exc.printStackTrace();
			throw new Excecoes("Erro ao buscar dados MDFe", exc, this
					.getClass().getName(), "getDadosEnvio()");
		}
		return mdfe;

	}

	public ManifestoED getEDByChave(String chave) throws Excecoes {
		ManifestoED ed = new ManifestoED();
		try {
			String query = "SELECT manifestos.*, unidades.cd_unidade "
					+ " FROM Manifestos, unidades "
					+ " WHERE  Manifestos.oid_unidade = Unidades.oid_unidade" +
							" AND Manifestos.nm_ch_mdfe ='" + chave + "'";
			ResultSet res = this.executasql.executarConsulta(query);
			if (res != null) {
				while (res.next()) {
					ed.setOID_Manifesto(res.getString("oid_manifesto"));
					ed.setNR_Manifesto(res.getLong("oid_manifesto"));
					ed.setCD_Unidade(res.getString("cd_unidade"));
				}
			}
		} catch(Exception e){
			System.out.println("problema...");
		}
		return ed;
	}

	public List<Mdfe> getDados(ManifestoED ed) throws Excecoes {

		String RNTRC = "";
		ArrayList<Mdfe> toReturn = new ArrayList<Mdfe>();
		try {
			String query = "SELECT manifestos.*, "
					+ " Manifestos.oid_Pessoa as oid_Pessoa_Motorista, "
					+ " Pessoa_Proprietario.oid_Pessoa as oid_Pessoa_Proprietario, "
					+ " Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario, "
					+ " Pessoa_Motorista.NM_Razao_Social as NM_Motorista, "
					+ " Veiculos.NR_Placa, unidades.rntrc, "
					+ " Cidade_Manifesto.NM_Cidade as NM_Cidade_Manifesto, "
					+ " pesUni.nr_cnpj_cpf as unidade"
					+ " FROM Manifestos, unidades, Veiculos, Cidades Cidade_Manifesto, Pessoas Pessoa_Proprietario, Pessoas Pessoa_Motorista, pessoas pesuni "
					+ " WHERE  Manifestos.OID_Veiculo = Veiculos.OID_Veiculo "
					+ " AND Manifestos.OID_Cidade = Cidade_Manifesto.OID_Cidade "
					+ " AND Manifestos.OID_Pessoa = Pessoa_Motorista.Oid_Pessoa "
					+ " AND Veiculos.OID_Pessoa_Proprietario = Pessoa_Proprietario.oid_Pessoa "
					+ " AND Manifestos.oid_unidade = Unidades.oid_unidade "
					+ " AND Unidades.oid_pessoa = pesuni.oid_pessoa ";

			query += " AND Manifestos.DT_Emissao >= '01/07/2014'";

			if (JavaUtil.doValida(ed.getDT_Emissao_Final())) {
				query += " AND Manifestos.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
			}
			if (JavaUtil.doValida(ed.getOID_Veiculo())) {
				query += " AND Manifestos.oid_Veiculo = '" + ed.getOID_Veiculo() + "'";
			}
			if (JavaUtil.doValida(ed.getOID_Pessoa())) {
				query += " AND Manifestos.oid_Pessoa = '" + ed.getOID_Pessoa() + "'";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOID_Unidade()))) {
				query += " AND Manifestos.oid_Unidade = " + ed.getOID_Unidade() + "";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOID_Cidade()))) {
				query += " AND Manifestos.oid_Cidade = " + ed.getOID_Cidade() + "";
			}
			if (JavaUtil.doValida(String.valueOf(ed.getOID_Cidade_Origem()))) {
				query += " AND Manifestos.oid_Cidade_origem = " + ed.getOID_Cidade_Origem() + "";
			}
			if (JavaUtil.doValida(ed.getDm_Tipo_Consulta()) && "E".equals(ed.getDm_Tipo_Consulta())) {
				query += " AND Manifestos.DM_Status_MDFe = '100' " +
						 " AND (Manifestos.dm_status_encerramento_mdfe != '135' or Manifestos.dm_status_encerramento_mdfe is null) " +
						 " AND (Manifestos.dm_status_cancelamento_mdfe != '135' or Manifestos.dm_status_cancelamento_mdfe is null)";
			}
//			query += " limit 10 ";
			System.out.println(query);
			ResultSet res = this.executasql.executarConsulta(query);
			if (res != null) {
				while (res.next()) {

					Mdfe mdfe = new Mdfe();

					String ufIni = "", ufFim = "";

					Empresa empresa = new EmpresaDb().getEmpresa(res
							.getString("unidade"));
					Integer amb = new Integer(JavaUtil.getValueDef(empresa
							.getAmbiente(), "2")); // 1 - prod , 2 - homo
					// PROVISORIO homologa
//					amb = 2;

					RNTRC = res.getString("RNTRC");
					if (!JavaUtil.doValida(RNTRC)) {
						RNTRC = "12345678";
					}

					mdfe.setChAcesso(res.getString("NM_CH_MDFe"));
					mdfe.setNProt(res.getString("NM_Protocolo_Retorno_MDFe"));
					mdfe.setDhEmi(res.getDate("DT_Envio_MDFe"));
					if(JavaUtil.doValida(res.getString("DT_Recebimento_MDFe")))
						mdfe.setDhRecbto(res.getDate("DT_Recebimento_MDFe"));

					/**
					 * DADOS DA MDFE
					 */
					mdfe.setCUF(new Integer(JavaUtil.getValueDef(String
							.valueOf(empresa.getcUf()), "43")));
					mdfe.setTpAmb(amb);
					if(JavaUtil.doValida(res.getString("dm_tipo_manifesto")) &&
							(res.getString("dm_tipo_manifesto").equals("RI") || res.getString("dm_tipo_manifesto").equals("RN")))
						mdfe.setTpEmit(2);
					else
						mdfe.setTpEmit(1); // 1 - transporte de carga de terceiros
					// | 2 - carga pr�pria >>>
					// OBS: quando tpemit for 2 � para preencher as nfes e
					// quando for 1 � o CTe
					mdfe.setMod(58);
					String oid_separado = StringUtil.retornaNumeroValido(res
							.getString("oid_Manifesto"));
					mdfe.setCMDF(new Integer(StringUtil.alinhaNumeroDireita(
							(oid_separado + "00000000").substring(0, 8), 8)));
					mdfe.setSerie(res.getString("nm_serie"));
					mdfe.setNMDF(new Integer(res.getString("NR_Manifesto")));// nro
					// mdfe
					mdfe.setModal(1);
					String data = res.getString("dt_emissao");
					// if (StringUtil.doValida(res.getString("hr_emissao")))
					// data += " " + res.getString("hr_emissao") + ":"
					// + Data.getMileSegundo();
					// else
					data += " " + Data.getHoraHM() + ":"
							+ Data.getMileSegundo();
					mdfe.setDhEmi(StringUtil.stringToCalendar(data,
							"yyyy-MM-dd hh:mm:ss").getTime());
					mdfe.setTpEmis(1);
					mdfe.setProcEmi(0);
					mdfe.setVerProc("0.001");

					/**
					 * EMITENTE
					 */
					MdfeEmit emitente = new MdfeEmit();
					String queryAux = "Select PESSOAS.*, ESTADOS.NM_Codigo_IBGE, estados.cd_estado, cidades.NM_Codigo_IBGE as cd_cid, cidades.nm_cidade "
							+ " FROM pessoas, cidades, regioes_estados, estados "
							+ " WHERE pessoas.oid_cidade = cidades.oid_cidade"
							+ " AND   cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado "
							+ " AND   regioes_estados.oid_estado = estados.oid_estado "
							+ " AND   pessoas.oid_pessoa = '"
							+ res.getString("unidade") + "'";
					ResultSet resAux = this.executasql
							.executarConsulta(queryAux);

					String uf = "RS";
					while (resAux.next()) {
						String cmun = "0";
						String cuf = "43";
						uf = resAux.getString("cd_estado");
						cuf = resAux.getString("nm_codigo_ibge");
						cmun = resAux.getString("cd_cid");

						emitente.setCNPJ(resAux.getString("oid_pessoa"));// CNPJ
						// emitente

						if (amb != 1)
							emitente
									.setXNome("MDF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");// Raz�o
						else
							emitente.setXNome(resAux.getString(
									"nm_razao_social").trim());
						// Social/Nome
						emitente.setXFant(resAux.getString("nm_fantasia")
								.trim());// Nome fantasia
						if (!JavaUtil.doValida(resAux
								.getString("nm_inscricao_estadual"))
								&& !"0".equals(resAux
										.getString("nm_inscricao_estadual")))
							throw new Excecoes("IE do Emissor � inv�lida!");
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
						emitente.setFone(StringUtil.retornaNumeroValido(resAux
								.getString("nr_telefone"), 12));
						emitente.setEmail(resAux.getString("email"));
					}

					mdfe.setEmit(emitente);

					toReturn.add(mdfe);

				}
			}
			res.close();

		} catch (Excecoes exc) {
			exc.printStackTrace();
			throw exc;
		} catch (Exception exc) {
			exc.printStackTrace();
			throw new Excecoes("Erro ao buscar dados MDFe", exc, this
					.getClass().getName(), "getDadosEnvio()");
		}
		return toReturn;

	}

}
