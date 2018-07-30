package com.master.rn;

import java.io.LineNumberReader;
import java.util.ArrayList;

import com.master.bd.BandaBD;
import com.master.bd.ConsertoBD;
import com.master.bd.Dimensao_PneuBD;
import com.master.bd.Fabricante_BandaBD;
import com.master.bd.Fabricante_PneuBD;
import com.master.bd.FornecedorBD;
import com.master.bd.HodometroBD;
import com.master.bd.Local_EstoqueBD;
import com.master.bd.Marca_VeiculoBD;
import com.master.bd.Medicao_PneuBD;
import com.master.bd.Modelo_PneuBD;
import com.master.bd.Modelo_VeiculoBD;
import com.master.bd.Motivo_SucateamentoBD;
import com.master.bd.Movimento_PneuBD;
import com.master.bd.PneuBD;
import com.master.bd.RecapagemBD;
import com.master.bd.Tipo_PneuBD;
import com.master.bd.Tipo_VeiculoBD;
import com.master.bd.UnidadeBD;
import com.master.bd.VeiculoBD;
import com.master.bd.Vida_PneuBD;
import com.master.ed.BandaED;
import com.master.ed.ConsertoED;
import com.master.ed.Dimensao_PneuED;
import com.master.ed.Fabricante_BandaED;
import com.master.ed.Fabricante_PneuED;
import com.master.ed.FornecedorED;
import com.master.ed.HodometroED;
import com.master.ed.Local_EstoqueED;
import com.master.ed.Marca_VeiculoED;
import com.master.ed.Medicao_PneuED;
import com.master.ed.Modelo_PneuED;
import com.master.ed.Modelo_VeiculoED;
import com.master.ed.Motivo_SucateamentoED;
import com.master.ed.Movimento_PneuED;
import com.master.ed.PneuED;
import com.master.ed.RecapagemED;
import com.master.ed.Tipo_PneuED;
import com.master.ed.Tipo_VeiculoED;
import com.master.ed.UnidadeED;
import com.master.ed.VeiculoED;
import com.master.ed.Vida_PneuED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.ManipulaArquivo;
import com.master.util.bd.Transacao;

public class EDI_StgpRN  extends Transacao {

	BancoUtil bu = new BancoUtil();
	
	int linha = 0;
	long oidGenerico = 0 ; 
	long oidEmpresa = 5;
	String nmRegistro = null;
	String wArquivo = "C:\\Desenv\\Cpoint\\Bacas\\Genericos\\ExpSTGP\\EXPORTA.EXP";
	
	// Arrays para manter cadastros básicos com seus oids
	ArrayList aUnidade = new ArrayList();
	ArrayList aFornecedor = new ArrayList();
	ArrayList aLocalEstoque = new ArrayList();
	ArrayList aMarcaVeic = new ArrayList();
	ArrayList aTipoVeic = new ArrayList();
	ArrayList aModeloVeic = new ArrayList();
	ArrayList aVeiculo = new ArrayList();
	ArrayList aMarcaPneu = new ArrayList();
	ArrayList aTipoPneu = new ArrayList();
	ArrayList aDimensaoPneu = new ArrayList();
	ArrayList aModeloPneu = new ArrayList();
	ArrayList aMotivoSucata = new ArrayList();
	ArrayList aMarcaBanda = new ArrayList();
	ArrayList aBanda = new ArrayList();
	
	public EDI_StgpRN () {
	}

	public void importaArquivo(String arquivo) throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início ");
  			this.importaMotivo();
			this.importaLocacao();
			this.importaFornecedor();
			this.importaLocaisEstoque();
			this.importaMarcaVeic();
			this.importaTipoVeic();
			this.importaModeloVeic();
			this.importaVeiculo();
			this.importaOdometro();
			this.importaMarcaPneu();
			this.importaTipoPneu();
			this.importaDimensaoPneu();
			this.importaModeloPneu();
			this.importaPneu();
			this.importaMedicao();
			this.importaConserto();
			this.registraHistoricoMontagem();
			this.importaMovimento();
			this.importaMarcaBanda();
			this.importaBanda();
			this.importaVida();
			this.importaRecape();
			this.importaUltimoRecape();
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim ");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaArquivo");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	
	// Motivo
	private void importaMotivo() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #Motivo");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#Motivo".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							Motivo_SucateamentoED ed = new Motivo_SucateamentoED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							String[] cpo = nmRegistro.split("£");
							if (!"NULL".equals(cpo[2])) {
								ed.setOid_Empresa(oidEmpresa);
								ed.setOid_Fabricante_Pneu(Long.parseLong(cpo[0]));
								ed.setNm_Motivo_Sucateamento(cpo[2]);
								ed.setDm_Stamp("I");
								ed.setUsuario_Stamp("Implantação");
								ed.setDt_stamp(Data.getDataDMY());
								ed = new Motivo_SucateamentoBD(this.sql).inclui(ed);
								aMotivoSucata.add(ed);
							}
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #Motivo");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaMotivo");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Fornecedor
	private void importaFornecedor() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #Fornecedor");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#Fornecedor".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							FornecedorED ed = new FornecedorED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							String[] cpo = nmRegistro.split("£");
							ed.setOid_Empresa(oidEmpresa);
							ed.setDescFiltro(cpo[0]);
							ed.setNm_Razao_Social(cpo[1]);
							ed.setNm_Endereco(cpo[2]);
							ed.setNm_Cidade(cpo[3]);
							ed.setNr_Cep(cpo[4]);
							if (!"NULL".equals(cpo[5]))
								ed.setNr_Telefone(cpo[5]);
							if (!"NULL".equals(cpo[6]))
								ed.setNr_Fax(cpo[6]);
							if (!"NULL".equals(cpo[7]))
								ed.setNm_eMail(cpo[7]);
							if (!"NULL".equals(cpo[8]))
								ed.setOid_Pessoa(this.desformataCnpj(cpo[8]));
							if (!"NULL".equals(cpo[9]))
								ed.setNm_Inscricao_Estadual(cpo[9]);
							ed.setCd_Estado(cpo[10]);
							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							ed = new FornecedorBD(this.sql).inclui(ed);
							aFornecedor.add(ed);
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #Fornecedor");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaFornecedor");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Locacao/Unidades
	private void importaLocacao() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #Locacao");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#Locacao".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							UnidadeED ed = new UnidadeED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							String[] cpo = nmRegistro.split("£");
							if (!"NULL".equals(cpo[0])){
								ed.setOid_Empresa(oidEmpresa);
								ed.setNm_Unidade(cpo[0]);
								ed.setDm_Stamp("I");
								ed.setUsuario_Stamp("Implantação");
								ed.setDt_stamp(Data.getDataDMY());
								ed = new UnidadeBD(this.sql).incluiStgp(ed);
								aUnidade.add(ed);
							}
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #Locacao");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaMarcaVeic");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Locais de estoque
	private void importaLocaisEstoque() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #LocalEstoque");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#LocalEstoque".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							Local_EstoqueED ed = new Local_EstoqueED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							String[] cpo = nmRegistro.split("£");
							ed.setOid_Empresa(oidEmpresa);
							ed.setDescFiltro(cpo[0]);
							ed.setNm_Local_Estoque(cpo[1]);
							ed.setOid_Unidade(this.buscaOidUnidadeDefault("MATRIZ"));
							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							ed = new Local_EstoqueBD(this.sql).inclui(ed);
							aLocalEstoque.add(ed);
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #LocalEstoque");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaLocaisEstoque");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Marcas de veiculos
	private void importaMarcaVeic() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #MarcaVeic");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#MarcaVeic".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							Marca_VeiculoED ed = new Marca_VeiculoED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							ed.setOid_Empresa(oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							ed.setNm_Marca_Veiculo(cpo[0]);
							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							ed = new Marca_VeiculoBD(this.sql).inclui(ed);
							aMarcaVeic.add(ed);
						}
						
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #MarcaVeic");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaMarcaVeic");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Tipos de veiculos
	private void importaTipoVeic() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #TipoVeic");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#TipoVeic".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							Tipo_VeiculoED ed = new Tipo_VeiculoED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							ed.setOid_Empresa(oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							ed.setNm_Tipo_Veiculo(cpo[0]);
							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							ed = new Tipo_VeiculoBD(this.sql).inclui(ed);
							aTipoVeic.add(ed);
						}
						
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #TipoVeic");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaTipoVeic");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Modelos de veiculos
	private void importaModeloVeic() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #ModeloVeic");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#ModeloVeic".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							Modelo_VeiculoED ed = new Modelo_VeiculoED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							String[] cpo = nmRegistro.split("£");
							ed.setOid_Empresa(oidEmpresa);
							// oid da Marca
							ed.setNm_Marca_Veiculo(cpo[0]);
							ed.setOid_Marca_Veiculo(this.buscaOidMarcaVeic(ed.getNm_Marca_Veiculo()));
							//oid do Tipo
							ed.setNm_Tipo_Veiculo(cpo[1]);
							ed.setOid_Tipo_Veiculo(this.buscaOidTipoVeic(ed.getNm_Tipo_Veiculo()));
							//tipo chassis
							ed.setDm_Tipo_Chassis(this.buscaDmChassis(cpo[2]));
							//Modelo
							ed.setNm_Modelo_Veiculo(cpo[3]);

							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							ed = new Modelo_VeiculoBD(this.sql).inclui(ed);
							aModeloVeic.add(ed);
						}
						
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #ModeloVeic");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaModeloVeic");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Veiculos
	private void importaVeiculo() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #Veiculos");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#Veículo".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							VeiculoED ed = new VeiculoED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							String[] cpo = nmRegistro.split("£");
							ed.setOid_Empresa(oidEmpresa);
							ed.setNr_Frota(cpo[0]);
							ed.setNr_Placa(cpo[1]);
							ed.setOid_Modelo_Veiculo(this.buscaOidModeloVeic(cpo[2],cpo[3], cpo[4]));
							ed.setNr_Odometro(Long.parseLong(cpo[6]));
							ed.setDm_Odometro_Maximo(Long.parseLong(cpo[7]));
							ed.setNr_Kilometragem_Atual(Long.parseLong(cpo[8]));
							if (!"NULL".equals(cpo[17])) ed.setNm_Tipo_Roda(cpo[17]);
							else ed.setNm_Tipo_Roda("");
							if (!"NULL".equals(cpo[18])) ed.setOid_Unidade(this.buscaOidUnidade(cpo[18]));
							else ed.setOid_Unidade(this.buscaOidUnidadeDefault("MATRIZ"));
							ed.setDm_Qtd_Estepes(Long.parseLong(cpo[20]));
							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							ed = new VeiculoBD(this.sql).inclui(ed);
							aVeiculo.add(ed);
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #Veiculos");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaVeiculo");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Marcas de pneus
	private void importaMarcaPneu() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #MarcaPneu");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#MarcaPneu".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							Fabricante_PneuED ed = new Fabricante_PneuED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							ed.setOid_Empresa((int) oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							ed.setNM_Fabricante_Pneu(cpo[0]);
							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							ed = new Fabricante_PneuBD(this.sql).inclui(ed);
							aMarcaPneu.add(ed);
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #MarcaPneu");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaMarcaPneu");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Tipos de pneus
	private void importaTipoPneu() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #TipoPneu");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#TipoPneu".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							Tipo_PneuED ed = new Tipo_PneuED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							ed.setOid_Empresa((int) oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							ed.setNm_Tipo_Pneu(cpo[0]);
							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							ed = new Tipo_PneuBD(this.sql).inclui(ed);
							aTipoPneu.add(ed);
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #TipoPneu");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaTipoPneu");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Dimensoes de pneus
	private void importaDimensaoPneu() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #DimensaoPneu");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#DimensaoPneu".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							Dimensao_PneuED ed = new Dimensao_PneuED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							ed.setOid_Empresa((int) oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							ed.setNm_Dimensao_Pneu(cpo[0]);
							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							ed = new Dimensao_PneuBD(this.sql).inclui(ed);
							aDimensaoPneu.add(ed);
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #DimensaoPneu");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaDimensaoPneu");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Dimensoes de pneus
	private void importaModeloPneu() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #ModeloPneu");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#ModeloPneu".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							Modelo_PneuED ed = new Modelo_PneuED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							ed.setOid_Empresa((int) oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							ed.setNM_Modelo_Pneu(cpo[1]);
							ed.setOid_Fabricante_Pneu(this.buscaOidMarcaPneu(cpo[0]));
							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							ed = new Modelo_PneuBD(this.sql).inclui(ed);
							aModeloPneu.add(ed);
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #ModeloPneu");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaModeloPneu");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	//Pneu
	private void importaPneu() throws Excecoes {
		try {
			String txt=null;
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #Pneu");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#Pneu".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							PneuED ed = new PneuED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							ed.setOid_Empresa((int) oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							ed.setNr_Fogo(cpo[0]);
							ed.setOid_Fabricante_Pneu((int)this.buscaOidMarcaPneu(cpo[1]));
							ed.setOid_Tipo_Pneu(buscaOidTipoPneu(cpo[2]));
							ed.setOid_Dimensao_Pneu(buscaOidDimensaoPneu(cpo[3]));
							ed.setNr_Km_Acumulada(Long.parseLong(cpo[4]));
							ed.setOid_Fornecedor(this.buscaOidFornecedor(cpo[5]));
							ed.setOid_Modelo_Pneu(this.buscaOidModeloPneu(cpo[1],cpo[24]));
							ed.setNr_Vida(Long.parseLong(cpo[54]));
							if (!"NULL".equals(cpo[46])) 
								ed.setCd_Item_Estoque(cpo[46]);
							else
								ed.setCd_Item_Estoque("");
							
							if (!"NULL".equals(cpo[6])) {
								txt=this.tiraPonto(cpo[6]);
								if (this.isnumeric(txt)) 
									ed.setNr_Nota_Fiscal(Long.parseLong(txt));
								else
									ed.setNr_Nota_Fiscal(0);
							}	
							
							ed.setDt_Nota_Fiscal(cpo[7]);
							ed.setVl_Preco(Double.parseDouble(cpo[8]));
							if (!"NULL".equals(cpo[33]))
								ed.setNr_Serie(cpo[33]);
							if ("T".equals(cpo[36])) 
								ed.setDm_Controle_Parcial("true");
							else
								ed.setDm_Controle_Parcial("false");
							// No veiculo
							if (!"NULL".equals(cpo[10]))  {
								ed.setOid_Veiculo(this.buscaOidVeiculo(cpo[9]));
								ed.setDt_Entrada(cpo[10]);
								ed.setNr_Km_Acumulada_Veiculo(Double.parseDouble(cpo[11]));
								ed.setNr_Hodometro_Veiculo(Double.parseDouble(cpo[11]));
								ed.setDM_Posicao(cpo[12]);
								ed.setDm_Eixo(cpo[13]);
							}	
							// Na sucata
							if (!"NULL".equals(cpo[14])) {
								ed.setDt_Sucateamento(cpo[14]);
								ed.setOid_Motivo_Sucateamento(this.buscaOidMotivo(cpo[47]));
							}
							// No estoque
							if (!"NULL".equals(cpo[16])) {
								ed.setDt_Estoque(cpo[16]);
								ed.setOid_Local_Estoque(this.buscaOidLocal(cpo[17]));
							}
							// No Recape
							if (!"NULL".equals(cpo[19])) {
								ed.setOid_Fornecedor_Recapagem(this.buscaOidFornecedor(cpo[18]));
								ed.setDt_Remessa_Recapagem(cpo[19]);
								ed.setDt_Promessa_Retorno_Recapagem(cpo[20]);
								ed.setNm_Contato_Recapagem(cpo[21]);
								if (!"NULL".equals(cpo[39])) 
									ed.setNr_Os_Recapagem(cpo[39]);
								else
									ed.setNr_Os_Recapagem("");
								ed.setVl_Negociado_Recapagem(Double.parseDouble(cpo[22]));
							}
							// Venda
							if (!"NULL".equals(cpo[41])) {
								ed.setDt_Venda(cpo[41]);
								ed.setVl_Venda(Double.parseDouble(cpo[42]));
								ed.setTx_Comentario_Venda(cpo[43]);
							}
							// Recusa de recapagem
							if (!"NULL".equals(cpo[49])) {
								ed.setDt_Recusa_Recapagem(cpo[49]);
								ed.setTx_Motivo_Recusa_Recapagem(cpo[50]);
								ed.setNm_Responsavel_Recusa_Recapagem(cpo[51]);
								if (this.isnumeric(cpo[52])) 
									ed.setNr_Nota_Fiscal_Recusa_Recapagem(Long.parseLong(this.tiraPonto(cpo[52])));
								else
									ed.setNr_Nota_Fiscal_Recusa_Recapagem(0);
							    ed.setOid_Fornecedor_Recusa_Recapagem(this.buscaOidFornecedor(cpo[53]));
							}
							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							ed = new PneuBD(this.sql).cargaStgp(ed);
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #Pneu");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaPneu");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	//Odometro
	private void importaOdometro() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #Odometro");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#Odometro".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							HodometroED ed = new HodometroED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							ed.setOid_Empresa((int) oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							ed.setOid_Veiculo(this.buscaOidVeiculo(cpo[0]));
							ed.setDt_Odometro_Troca(cpo[1]);
							ed.setNr_Odometro_Retirado(Long.parseLong(cpo[2]));
							ed.setNr_Odometro_Colocado(Long.parseLong(cpo[3]));
							ed.setNr_Km_Acum_Troca(Long.parseLong(cpo[4]));
							ed.setNr_Odometro_Maximo(Long.parseLong(cpo[7]));
							ed.setNr_Odometro_Anterior(Long.parseLong(cpo[5]));
							ed.setNr_Km_Acum_Anterior(Long.parseLong(cpo[6]));
							ed.setNr_Odometro_Maximo_Anterior(Long.parseLong(cpo[7]));
							ed.setDm_Tipo_Troca("D");
							
							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							ed = new HodometroBD(this.sql).inclui(ed);
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #Odometro");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaPneu");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Medicoes
	private void importaMedicao() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #Medicao");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#Medicao".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							Medicao_PneuED ed = new Medicao_PneuED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							ed.setOid_Empresa((int) oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							ed.setOid_Pneu(this.buscaOidPneu(cpo[0]));
							ed.setNr_Vida(Long.parseLong(cpo[1])-1);
							ed.setDt_Medicao_Pneu(cpo[2]);
							ed.setNr_Mm_Sulco(Double.parseDouble(cpo[3]));
							ed.setNr_Km_Acumulada_Pneu(Double.parseDouble(cpo[4]));
							
							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							ed = new Medicao_PneuBD(this.sql).inclui(ed);
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #Medicao");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaMedicao");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Consertos
	private void importaConserto() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #Conserto");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#Conserto".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							ConsertoED ed = new ConsertoED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							ed.setOid_Empresa((int) oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							ed.setOid_Pneu_Conserto(this.buscaOidPneu(cpo[0]));
							ed.setOid_Veiculo(this.buscaOidVeiculo(cpo[1]));
							ed.setDt_Conserto(cpo[2]);
							ed.setVl_Conserto(Double.parseDouble(cpo[3]));
							ed.setOid_Fornecedor_Conserto(this.buscaOidFornecedor(cpo[4]));
							ed.setTx_Descricao_Conserto(cpo[5]);
							ed.setDm_Vida_Conserto(Long.parseLong(cpo[6])-1);
							ed.setNr_Documento_Conserto(cpo[7]);
							ed.setVl_Prof_Sulco_Resulcagem(0);
							
							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							ed = new ConsertoBD(this.sql).inclui(ed);
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #Conserto");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaMedicao");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Movimentos
	private void importaMovimento() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #Historico");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#Historico".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							Movimento_PneuED ed = new Movimento_PneuED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							ed.setOid_Empresa(oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							ed.setOid_Veiculo(this.buscaOidVeiculo(cpo[0]));
							ed.setOid_Pneu((int)this.buscaOidPneu(cpo[1]));
							ed.setOid_Local_Estoque(buscaOidLocalDefault("DEFAULT"));
							ed.setDt_Entrada(cpo[2]);
							ed.setNr_Odometro_Entrada(Double.parseDouble(cpo[3]));
							ed.setNr_Km_Acumulada_Entrada(Double.parseDouble(cpo[4]));
							ed.setDt_Saida(cpo[5]);
							ed.setNr_Odometro_Saida(Double.parseDouble(cpo[6]));
							ed.setNr_Km_Acumulada_Saida(Double.parseDouble(cpo[7]));
							ed.setDm_Posicao(cpo[8]);
							ed.setDm_Eixo(cpo[9]);
							ed.setTx_Motivo(cpo[10]);
							if (!"NULL".equals(cpo[12])) 
								ed.setOid_Pneu_Colocado((int)this.buscaOidPneu(cpo[12]));
							ed.setNr_Vida(Long.parseLong(cpo[13])-1);
							if ("F".equals(cpo[16]))
								ed.setDm_Rodou_Pouco("N");
							else
								ed.setDm_Rodou_Pouco("S");
							if ("NULL".equals(cpo[17]))
								ed.setNr_Media_Comparacao(0);
							else	
								ed.setNr_Media_Comparacao(Double.parseDouble(cpo[17]));
							
							Medicao_PneuED medED = new Medicao_PneuED();
							medED.setOid_Pneu(ed.getOid_Pneu());
							medED.setNr_Vida(ed.getNr_Vida());
							medED = new Medicao_PneuBD(this.sql).getMedicao(medED);
							ed.setNr_Mm_Entrada(medED.getNr_Mm_Sulco());	
							
							ed.setDm_Tipo_Movimento("T");
							
							ed.setDm_Stamp("I");
							ed.setUsuario_Stamp("Implantação");
							ed.setDt_stamp(Data.getDataDMY());
							new Movimento_PneuBD(this.sql).inclui(ed);
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #Historico");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaMedicao");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Historico Montagem
	private void registraHistoricoMontagem() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #Montagem");
			this.inicioTransacao();
			PneuED ed = new PneuED();
			ed.setOid_Empresa((int) oidEmpresa);
			ed.setDt_Entrada("busca");
			ArrayList lst = new PneuBD(this.sql).lista(ed);
			for (int i=0;i<lst.size();i++) {
				PneuED edV = new PneuED();
				edV = (PneuED) lst.get(i);
				Movimento_PneuED movED = new Movimento_PneuED();
				movED.setOid_Empresa(oidEmpresa);
				movED.setOid_Pneu(edV.getOid_Pneu());
				movED.setOid_Veiculo(edV.getOid_Veiculo());
				movED.setOid_Local_Estoque(buscaOidLocalDefault("DEFAULT"));
				movED.setDt_Entrada(edV.getDt_Entrada());
				movED.setDm_Posicao(edV.getDM_Posicao());
				
				movED.setNr_Odometro_Entrada(edV.getNr_Hodometro_Veiculo());
				movED.setNr_Km_Acumulada_Entrada(edV.getNr_Km_Acumulada_Veiculo());
				
				movED.setDm_Eixo(edV.getDm_Eixo());
				movED.setNr_Vida(ed.getNr_Vida());
				
				Medicao_PneuED medED = new Medicao_PneuED();
				medED.setOid_Pneu(movED.getOid_Pneu());
				medED.setNr_Vida(movED.getNr_Vida());
				medED = new Medicao_PneuBD(this.sql).getMedicao(medED);
				movED.setNr_Mm_Entrada(medED.getNr_Mm_Sulco());	
				
				movED.setDm_Tipo_Movimento("N");
				
				movED.setDm_Stamp("I");
				movED.setUsuario_Stamp("Implantação");
				movED.setDt_stamp(Data.getDataDMY());
				new Movimento_PneuBD(this.sql).inclui(movED);
 			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #Montagem");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaMontagem");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Marcas de bandas
	private void importaMarcaBanda() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #MarcaBanda");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#MarcaBanda".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							Fabricante_BandaED ed = new Fabricante_BandaED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							ed.setOid_Empresa((int) oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							if (!"NULL".equals(cpo[0]) || !"ORIGINAL".equals(cpo[0])) {
								ed.setNm_Fabricante_Banda(cpo[0]);
								ed.setDm_Stamp("I");
								ed.setUsuario_Stamp("Implantação");
								ed.setDt_stamp(Data.getDataDMY());
								ed = new Fabricante_BandaBD(this.sql).inclui(ed);
								aMarcaBanda.add(ed);
							}
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #MarcaBanda");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaMarcaBanda");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Bandas
	private void importaBanda() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #Banda");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#Banda".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							BandaED ed = new BandaED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							ed.setOid_Empresa((int) oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							if (!"ORIGINAL".equals(cpo[1])) {
								ed.setNm_Banda(cpo[1]);
								ed.setOid_Fabricante_Banda(this.buscaOidMarcaBanda(cpo[0]));
								ed.setDm_Stamp("I");
								ed.setUsuario_Stamp("Implantação");
								ed.setDt_stamp(Data.getDataDMY());
								ed = new BandaBD(this.sql).inclui(ed);
								aBanda.add(ed);
							}
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #Banda");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaBanda");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Vidas
	private void importaVida() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #Vida");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#Vida".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							Vida_PneuED ed = new Vida_PneuED();
							if ("#".equals (nmRegistro.substring (0 , 1))) {
								break;
							}
							ed.setOid_Empresa(oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							if (!"NULL".equals(cpo[0])) {
								ed.setOid_Pneu((int)this.buscaOidPneu(cpo[0]));
								ed.setNr_Vida(Long.parseLong(cpo[1])-1);
								ed.setNr_Km_Inicial(Double.parseDouble(cpo[2]));
								ed.setNr_Km_Final(Double.parseDouble(cpo[3]));
								ed.setOid_Fornecedor(this.buscaOidFornecedor(cpo[4]));
								ed.setOid_Fabricante_Banda(this.buscaOidMarcaBanda(cpo[6]));
								ed.setOid_Banda(this.buscaOidBanda(cpo[7]));
								ed.setVl_Vida(Double.parseDouble(cpo[8]));
								ed.setDt_Inicial(cpo[9]);
								ed.setOid_Veiculo(this.buscaOidVeiculo(cpo[11]));
								ed.setDm_Eixo(cpo[12]);
								if (!"NULL".equals(cpo[13]))
									ed.setNr_Os(cpo[13]);
								else
									ed.setNr_Os("");
								if (!"NULL".equals(cpo[14]))
									ed.setNr_Nota_Fiscal_Recape(Long.parseLong(this.tiraPonto(cpo[14])));
								if (!"NULL".equals(cpo[15]))
									ed.setNr_Mm_Inicial(Double.parseDouble(cpo[15]));
								if (!"NULL".equals(cpo[16]))
									ed.setNr_Mm_Final(Double.parseDouble(cpo[16]));
								if (!"NULL".equals(cpo[17]))
									ed.setDt_Final(cpo[17]);
								else
									ed.setDt_Final(ed.getDt_Inicial());
								
								ed.setDm_Stamp("I");
								ed.setUsuario_Stamp("Implantação");
								ed.setDt_stamp(Data.getDataDMY());
								new Vida_PneuBD(this.sql).inclui(ed);
							}
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #Vida");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaVida");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	// Vidas
	private void importaRecape() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #Recape");
			this.inicioTransacao();
			Vida_PneuED ed = new Vida_PneuED();
			ed.setOid_Empresa(oidEmpresa);
			ed.setNr_Vida(9999); // Pega as vidas > 0
			ArrayList aVida = new ArrayList();
			aVida = new Vida_PneuBD(this.sql).lista(ed);
			for (int i=0;i<aVida.size();i++){
				Vida_PneuED edV = new Vida_PneuED();
				edV = (Vida_PneuED) aVida.get(i);
				RecapagemED edR = new RecapagemED();
				edR.setOid_Empresa(oidEmpresa);
				edR.setOid_Pneu(edV.getOid_Pneu());
				edR.setOid_Fornecedor_Recapagem(edV.getOid_Fornecedor());
				edR.setOid_Fabricante_Banda(edV.getOid_Fabricante_Banda());
				edR.setOid_Banda(edV.getOid_Banda());
				edR.setVl_Recapagem(edV.getVl_Vida());
				edR.setDt_Recapagem(edV.getDt_Inicial());
				edR.setNr_Nota_Fiscal_Recapagem(edV.getNr_Nota_Fiscal_Recape());
				if (!"null".equals(edV.getNr_Os()))
					edR.setNr_Os_Recapagem(edV.getNr_Os());
				else
					edR.setNr_Os_Recapagem("");	
				edR.setNr_Mm_Sulco_Recapagem(edV.getNr_Mm_Inicial());
				edR.setDm_Garantia_Recapagem("false");
				edR.setNr_Perimetro(0);
				
				edR.setDm_Stamp("I");
				edR.setUsuario_Stamp("Implantação");
				edR.setDt_stamp(Data.getDataDMY());
				new RecapagemBD(this.sql).inclui(edR);
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #Recape");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("importaRecape");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	//Ultimo Recape
	private void importaUltimoRecape() throws Excecoes {
		try {
			// System.out.println("IMPORTAÇÃO - STGPOL - Início #ultimoRecape");
			this.inicioTransacao();
			ManipulaArquivo man = new ManipulaArquivo();
			LineNumberReader line = man.leLinha(wArquivo);
			if (line.ready ()) {
				while ( (nmRegistro = line.readLine ()) != null) {
					if ("#Pneu".equals (nmRegistro.trim ())) {
						while ( (nmRegistro = line.readLine ()) != null) {
							RecapagemED ed = new RecapagemED();
							if ("#".equals (nmRegistro.substring (0 , 1))) { 
								break;	
							}
							ed.setOid_Empresa((int) oidEmpresa);
							String[] cpo = nmRegistro.split("£");
							if (!"NULL".equals(cpo[25])) {
								ed.setOid_Pneu((int)this.buscaOidPneu(cpo[0]));
								ed.setOid_Fornecedor_Recapagem(this.buscaOidFornecedor(cpo[25]));
								ed.setOid_Fabricante_Banda(this.buscaOidMarcaBanda(cpo[26]));
								ed.setOid_Banda(this.buscaOidBanda(cpo[27]));
								ed.setVl_Recapagem(Double.parseDouble(cpo[28]));
								ed.setDt_Recapagem(cpo[29]);
								if (!"NULL".equals(cpo[38]))
									ed.setNr_Nota_Fiscal_Recapagem(Long.parseLong(this.tiraPonto(cpo[38])));
								else
									ed.setNr_Nota_Fiscal_Recapagem(0);
								if (!"NULL".equals(cpo[37])) 
									ed.setNr_Os_Recapagem(this.tiraPonto(cpo[37]));
								else 
									ed.setNr_Os_Recapagem("");
								
								Medicao_PneuED medED = new Medicao_PneuED();
								medED.setOid_Pneu(ed.getOid_Pneu());
								medED.setNr_Vida(this.buscaNrVida((int)ed.getOid_Pneu()));
								medED = new Medicao_PneuBD(this.sql).getMedicao(medED);
								
								ed.setNr_Mm_Sulco_Recapagem(medED.getNr_Mm_Sulco());	
								if ("T".equals(cpo[48]))
									ed.setDm_Garantia_Recapagem("true");
								else
									ed.setDm_Garantia_Recapagem("false");	
								ed.setNr_Perimetro(0);

								ed.setDm_Stamp("I");
								ed.setUsuario_Stamp("Implantação");
								ed.setDt_stamp(Data.getDataDMY());
								ed = new RecapagemBD(this.sql).inclui(ed);
							}
						}
					}
				}
			}
			this.fimTransacao(true);
			// System.out.println("IMPORTAÇÃO - STGPOL - Fim #ultimoRecape");
		}catch (Exception exc) {
			this.abortaTransacao();
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("ultimoRecape");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	
	// Busca oid no array partindo da descrição
	private long buscaOidMotivo(String p) {
		oidGenerico = 0;
		for (int i=0;i<aMotivoSucata.size();i++) {
			Motivo_SucateamentoED ed = new Motivo_SucateamentoED();
			ed = (Motivo_SucateamentoED) aMotivoSucata.get(i);
			if (ed.getOid_Fabricante_Pneu() == Long.parseLong(p)) {
				oidGenerico = ed.getOid_Motivo_Sucateamento();
				break;
			}
		}
		return oidGenerico;
	}
	private long buscaOidUnidade(String p) {
		oidGenerico = 0;
		for (int i=0;i<aUnidade.size();i++) {
			UnidadeED ed = new UnidadeED();
			ed = (UnidadeED) aUnidade.get(i);
			if (p.equals(ed.getNm_Unidade())) {
				oidGenerico = ed.getOid_Unidade();
				break;
			}
		}
		return oidGenerico;
	}
	private long buscaOidUnidadeDefault(String p) throws Excecoes {
		try {
			oidGenerico = 0;
			UnidadeED ed = new UnidadeED();
			ed.setOid_Empresa(oidEmpresa);
			ed.setNm_Unidade(p);
			ed = new UnidadeBD(this.sql).getByRecordStgp(ed);
			oidGenerico = ed.getOid_Unidade();
			return oidGenerico;
		}catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("buscaOidUnidadeDefault");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	private long buscaOidLocal(String p) {
		oidGenerico = 0;
		for (int i=0;i<aUnidade.size();i++) {
			Local_EstoqueED ed = new Local_EstoqueED();
			ed = (Local_EstoqueED) aLocalEstoque.get(i);
			if (p.equals(ed.getDescFiltro())) {
				oidGenerico = ed.getOid_Local_Estoque();
				break;
			}
		}
		return oidGenerico;
	}
	private long buscaOidLocalDefault(String p) throws Excecoes {
		try {
			oidGenerico = 0;
			Local_EstoqueED ed = new Local_EstoqueED();
			ed.setOid_Empresa(oidEmpresa);
			ed.setOid_Unidade(this.buscaOidUnidadeDefault("MATRIZ"));
			ed = new Local_EstoqueBD(this.sql).getByRecord(ed);
			oidGenerico = ed.getOid_Local_Estoque();
			return oidGenerico;
		}catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("buscaOidUnidadeDefault");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	private long buscaOidMarcaVeic(String p) {
		oidGenerico = 0;
		for (int i=0;i<aMarcaVeic.size();i++) {
			Marca_VeiculoED ed = new Marca_VeiculoED();
			ed = (Marca_VeiculoED) aMarcaVeic.get(i);
			if (p.equals(ed.getNm_Marca_Veiculo())) {
				oidGenerico = ed.getOid_Marca_Veiculo();
				break;
			}
		}
		return oidGenerico;
	}
	private long buscaOidTipoVeic(String p) {
		oidGenerico = 0;
		for (int i=0;i<aTipoVeic.size();i++) {
			Tipo_VeiculoED ed = new Tipo_VeiculoED();
			ed = (Tipo_VeiculoED) aTipoVeic.get(i);
			if (p.equals(ed.getNm_Tipo_Veiculo())) {
				oidGenerico = ed.getOid_Tipo_Veiculo();
				break;
			}
		}
		return oidGenerico;
	}
	private long buscaOidModeloVeic(String p, String p1, String p2) {
		long oidTipo = this.buscaOidTipoVeic(p2);
		long oidMarca = this.buscaOidMarcaVeic(p);
		oidGenerico = 0;
		for (int i=0;i<aModeloVeic.size();i++) {
			Modelo_VeiculoED ed = new Modelo_VeiculoED();
			ed = (Modelo_VeiculoED) aModeloVeic.get(i);
			if (p1.equals(ed.getNm_Modelo_Veiculo()) && oidTipo == ed.getOid_Tipo_Veiculo() && oidMarca == ed.getOid_Marca_Veiculo() ) {
				oidGenerico = ed.getOid_Modelo_Veiculo();
				break;
			}
		}
		return oidGenerico;
	}
	private long buscaOidMarcaPneu(String p) {
		oidGenerico = 0;
		for (int i=0;i<aMarcaPneu.size();i++) {
			Fabricante_PneuED ed = new Fabricante_PneuED();
			ed = (Fabricante_PneuED) aMarcaPneu.get(i);
			if (p.equals(ed.getNM_Fabricante_Pneu())) {
				oidGenerico = ed.getOid_Fabricante_Pneu();
				break;
			}
		}
		return oidGenerico;
	}
	private long buscaOidTipoPneu(String p) {
		oidGenerico = 0;
		for (int i=0;i<aTipoPneu.size();i++) {
			Tipo_PneuED ed = new Tipo_PneuED();
			ed = (Tipo_PneuED) aTipoPneu.get(i);
			if (p.equals(ed.getNm_Tipo_Pneu())) {
				oidGenerico = ed.getOid_Tipo_Pneu();
				break;
			}
		}
		return oidGenerico;
	}
	private long buscaOidDimensaoPneu(String p) {
		oidGenerico = 0;
		for (int i=0;i<aDimensaoPneu.size();i++) {
			Dimensao_PneuED ed = new Dimensao_PneuED();
			ed = (Dimensao_PneuED) aDimensaoPneu.get(i);
			if (p.equals(ed.getNm_Dimensao_Pneu())) {
				oidGenerico = ed.getOid_Dimensao_Pneu();
				break;
			}
		}
		return oidGenerico;
	}
	private int buscaOidModeloPneu(String p, String p1) {
		long oidFabricante = this.buscaOidMarcaPneu(p);
		int oidGenerico = 0;
		for (int i=0;i<aModeloPneu.size();i++) {
			Modelo_PneuED ed = new Modelo_PneuED();
			ed = (Modelo_PneuED) aModeloPneu.get(i);
			if (p1.equals(ed.getNM_Modelo_Pneu()) && oidFabricante == ed.getOid_Fabricante_Pneu() ) {
				oidGenerico = ed.getOid_Modelo_Pneu();
				break;
			}
		}
		return oidGenerico;
	}
	private String buscaOidFornecedor(String p) {
		String oidString = null;
		for (int i=0;i<aFornecedor.size();i++) {
			FornecedorED ed = new FornecedorED();
			ed = (FornecedorED) aFornecedor.get(i);
			if (p.equals(ed.getDescFiltro())) {
				oidString = ed.getOid_Fornecedor();
				break;
			}
		}
		if (oidString==null) {
			FornecedorED ed = (FornecedorED) aFornecedor.get(0);
			oidString = ed.getOid_Fornecedor();
		}	
		return oidString;
	}
	private long buscaOidMarcaBanda(String p) {
		oidGenerico = 0;
		for (int i=0;i<aMarcaBanda.size();i++) {
			Fabricante_BandaED ed = new Fabricante_BandaED();
			ed = (Fabricante_BandaED) aMarcaBanda.get(i);
			if (p.equals(ed.getNm_Fabricante_Banda())) {
				oidGenerico = ed.getOid_Fabricante_Banda();
				break;
			}
		}
		return oidGenerico;
	}
	private long buscaOidBanda(String p) {
		oidGenerico = 0;
		for (int i=0;i<aBanda.size();i++) {
			BandaED ed = new BandaED();
			ed = (BandaED) aBanda.get(i);
			if (p.equals(ed.getNm_Banda())) {
				oidGenerico = ed.getOid_Banda();
				break;
			}
		}
		return oidGenerico;
	}
	private String buscaOidVeiculo(String p) {
		String oidString = null;
		for (int i=0;i<aVeiculo.size();i++) {
			VeiculoED ed = new VeiculoED();
			ed = (VeiculoED) aVeiculo.get(i);
			if (p.equals(ed.getNr_Frota())) {
				oidString = ed.getOid_Veiculo();
				break;
			}
		}
		return oidString;
	}	
	private long buscaOidPneu(String p) throws Excecoes {
		try {
			oidGenerico = 0;
			PneuED ed = new PneuED();
			ed.setOid_Empresa(oidEmpresa);
			ed.setNr_Fogo(p);
			ed = new PneuBD(this.sql).getByRecord(ed);
			oidGenerico = ed.getOid_Pneu();
			return oidGenerico;
		}catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("buscaOidPneu");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}
	private int buscaNrVida(int p) throws Excecoes {
		try {
			int nrVida = 0;
			PneuED ed = new PneuED();
			ed.setOid_Empresa(oidEmpresa);
			ed.setOid_Pneu(p);
			ed = new PneuBD(this.sql).getByRecord(ed);
			nrVida = (int)ed.getNr_Vida();
			return nrVida;
		}catch (Exception exc) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMetodo ("buscaNrVida");
			excecoes.setExc (exc);
			throw excecoes;
		}
	}

	// Converte o tipo de chassis
	private long buscaDmChassis(String p) {
		long dmChassis = 0;
		if ("0".equals(p) ) dmChassis = 1; else
		if ("1".equals(p) ) dmChassis = 2; else
		if ("2".equals(p) ) dmChassis = 3; else	
		if ("12".equals(p) ) dmChassis = 4; else
		if ("13".equals(p) ) dmChassis = 5; else
		if ("18".equals(p) ) dmChassis = 6; else	
		if ("15".equals(p) ) dmChassis = 7; else
		if ("3".equals(p) ) dmChassis = 8; else
		if ("4".equals(p) ) dmChassis = 9; else	
		if ("6".equals(p) ) dmChassis = 10; else
		if ("17".equals(p) ) dmChassis = 11; else
		if ("7".equals(p) ) dmChassis = 12; else
		if ("5".equals(p) ) dmChassis = 13; else
		if ("8".equals(p) ) dmChassis = 14; else
		if ("9".equals(p) ) dmChassis = 15; else	
		if ("19".equals(p) ) dmChassis = 16; else
		if ("14".equals(p) ) dmChassis = 17; else
		if ("10".equals(p) ) dmChassis = 18; else	
		if ("11".equals(p) ) dmChassis = 19; else
		if ("16".equals(p) ) dmChassis = 20;	
		return dmChassis;
		
		/**
 		<frame  src="../images/PNEUCA04.PNG" /> <!--1--> 0
 		<frame  src="../images/PNEUCA08.PNG" /> <!--2--> 1
 		<frame  src="../images/PNEUCA12.PNG" /> <!--3--> 2
 		<frame  src="../images/PNEUCA16.PNG" /> <!--4--> 12
 		<frame  src="../images/PNEUCA32.PNG" /> <!--5--> 13
 		<frame  src="../images/PNEUCAS4.PNG" /> <!--6--> 18
 		<frame  src="../images/PNEUCAS6.PNG" /> <!--7--> 15
 		<frame  src="../images/PNEUCO04.PNG" /> <!--8--> 3
 		<frame  src="../images/PNEUCO06.PNG" /> <!--9--> 4
 		<frame  src="../images/PNEUCO10.PNG" /> <!--10--> 6
 		<frame  src="../images/PNEUDO08.PNG" /> <!--11--> 17
 		<frame  src="../images/PNEUON06.PNG" /> <!--12--> 7
 		<frame  src="../images/PNEUON08.PNG" /> <!--13--> 5
 		<frame  src="../images/PNEUON09.PNG" /> <!--14--> 8
 		<frame  src="../images/PNEUON10.PNG" /> <!--15--> 9
 		<frame  src="../images/PNEUON12.PNG" /> <!--16--> 19
 		<frame  src="../images/PNEUON14.PNG" /> <!--17--> 14
 		<frame  src="../images/PNEURE02.PNG" /> <!--18--> 10
 		<frame  src="../images/PNEURE08.PNG" /> <!--19--> 11
 		<frame  src="../images/PNEURE12.PNG" />	<!--20--> 16
 		**/
	}
	// Desformata CNPJ
	private String desformataCnpj(String p) {
		String txt = p;
		int pos = 0;
		while (txt.indexOf(".") > 0){
			pos = txt.indexOf(".");
			txt = txt.substring(0,pos) + txt.substring(pos+1,txt.length()) ;
		}
		while (txt.indexOf("/") > 0){
			pos = txt.indexOf("/");
			txt = txt.substring(0,pos) + txt.substring(pos+1,txt.length()) ;
		}
		while (txt.indexOf("-") > 0){
			pos = txt.indexOf("-");
			txt = txt.substring(0,pos) + txt.substring(pos+1,txt.length()) ;
		}
		return txt;
	}
	// Verifica se a string é numérica
	private boolean isnumeric(String p) {
		 try {  
		     long numero = Long.parseLong(p);  
		     return true;  
		 }  
		 catch (NumberFormatException nfe) {  
		    return false;  
		 }  
	}	
	private String tiraPonto(String p){
		String txt = p;
		int pos = 0;
		while (txt.indexOf(".") > 0){
			pos = txt.indexOf(".");
			txt = txt.substring(0,pos) + txt.substring(pos+1,txt.length()) ;
		}
		return txt;
	}
}