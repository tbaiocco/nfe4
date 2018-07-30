/*
 * Created on 12/11/2004
 *
 */
package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.ConsertoBD;
import com.master.bd.Dimensao_PneuBD;
import com.master.bd.EstoqueBD;
import com.master.bd.Fabricante_PneuBD;
import com.master.bd.HodometroBD;
import com.master.bd.Modelo_PneuBD;
import com.master.bd.Modelo_VeiculoBD;
import com.master.bd.Motivo_SucateamentoBD;
import com.master.bd.Movimento_PneuBD;
import com.master.bd.Movimento_Pneu_EstoqueBD;
import com.master.bd.PneuBD;
import com.master.bd.RecapagemBD;
import com.master.bd.UnidadeBD;
import com.master.bd.VeiculoBD;
import com.master.bd.Vida_PneuBD;
import com.master.ed.ConsertoED;
import com.master.ed.Dimensao_PneuED;
import com.master.ed.Fabricante_PneuED;
import com.master.ed.HodometroED;
import com.master.ed.Medicao_PneuED;
import com.master.ed.Modelo_PneuED;
import com.master.ed.Modelo_VeiculoED;
import com.master.ed.Motivo_SucateamentoED;
import com.master.ed.Movimento_PneuED;
import com.master.ed.Movimento_Pneu_EstoqueED;
import com.master.ed.PneuED;
import com.master.ed.RecapagemED;
import com.master.ed.RelatorioED;
import com.master.ed.Tipo_PneuED;
import com.master.ed.UnidadeED;
import com.master.ed.UsuarioED;
import com.master.ed.VeiculoED;
import com.master.ed.Vida_PneuED;
import com.master.rl.JasperRL;
import com.master.root.VeiculoBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.OLUtil;
import com.master.util.RequestUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

/**
 * @author Tiago Sauter Lauxen
 * @author Ralph - Alterado - Rotinas Laszlo //
 * @author PRS - Alterado - Rotinas Laszlo //
 * @author Cristian - Alterado - Rotinas Laszlo //
 */
public class PneuRN extends Transacao {

	BancoUtil bu = new BancoUtil();

	public PneuED inclui(PneuED ed) throws Excecoes {
		this.inicioTransacao();
		try {
			PneuED toReturn = new PneuBD(sql).inclui(ed);
			this.fechaVida(toReturn);
			this.fimTransacao(true);
			return toReturn;
		} catch (RuntimeException e) {
			this.abortaTransacao();
			throw e;
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		}
	}

	public void incluiPneuVeiculo(PneuED ed) throws Exception {
		try {
			// Validações
			if (!JavaUtil.doValida(ed.getOid_Veiculo())) {
				throw new Mensagens("ID Veiculo não informado!");
			}
			if (!JavaUtil.doValida(ed.getDM_Posicao())) {
				throw new Mensagens("Posição do Pneu não informada!");
			}
			if (ed.getOid_Pneu() < 1) {
				throw new Mensagens("ID Pneu não informado!");
			}
			ed.setDM_Localizacao("03");

			this.inicioTransacao();
			PneuED toReturn = new PneuBD(sql).getByRecord(ed.getOid_Pneu());
			VeiculoBean edVeiculo = VeiculoBean.getByOID(ed.getOid_Veiculo());
			ed.setDM_Situacao(toReturn.getDM_Situacao());
			new PneuBD(sql).alteraSituacaoPneu(ed);
			// **** Grava Movimentação
			new Movimento_PneuBD(sql)
					.inclui(new Movimento_PneuED(ed.getDM_Localizacao(),
							toReturn.getDM_Situacao(), Data.getDataYMD(), "",
							(int) edVeiculo.getNR_Kilometragem_Atual(), 0,
							toReturn.getKM_Atual(), ed.getOid_Pneu(), ed
									.getOid_Veiculo(), "Inclusao no Veiculo"));

			// Saida do estoque
			if (JavaUtil.doValida(String.valueOf(toReturn.getOid_estoque()))) {
				new EstoqueBD(sql).baixa_Estoque(String.valueOf(toReturn
						.getOid_estoque()), 1, ed.getOid_Veiculo());
			}

			this.fimTransacao(true);
		} catch (RuntimeException e) {
			this.abortaTransacao();
			throw e;
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		} catch (Exception e) {
			this.abortaTransacao();
			throw e;
		}
	}

	public void alteraPneuVeiculo(PneuED ed) throws Excecoes {
		try {
			// Validações
			if (!JavaUtil.doValida(ed.getOid_Veiculo())) {
				throw new Mensagens("ID Veiculo não informado!");
			}
			if (!JavaUtil.doValida(ed.getDM_Posicao())) {
				throw new Mensagens("Posição do Pneu não informada!");
			}
			if (ed.getOid_Pneu() < 1) {
				throw new Mensagens("ID Pneu não informado!");
			}
			this.inicioTransacao();
			PneuED toReturn = new PneuBD(sql).getByRecord(ed.getOid_Pneu());
			ed.setDM_Localizacao(toReturn.getDM_Localizacao());
			ed.setDM_Situacao(toReturn.getDM_Situacao());
			ed.setDM_Situacao(toReturn.getDM_Situacao());
			new PneuBD(sql).alteraSituacaoPneu(ed);
			this.fimTransacao(true);
		} catch (RuntimeException e) {
			this.abortaTransacao();
			throw e;
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		}
	}

	public void removePneuVeiculo(PneuED ed) throws Exception {
		try {
			// Validações
			if (ed.getOid_Pneu() < 1) {
				throw new Mensagens("ID Pneu não informado!");
			}
			if (!JavaUtil.doValida(ed.getDM_Localizacao())) {
				throw new Mensagens("Localização do Pneu não informada!");
			}
			if (!JavaUtil.doValida(ed.getDM_Situacao())) {
				throw new Mensagens("Situação do Pneu não informada!");
			}

			this.inicioTransacao();
			// busca pneu
			PneuED toReturn = new PneuBD(sql).getByRecord(ed.getOid_Pneu());
			// busca veiculo
			VeiculoBean edVeiculo = VeiculoBean.getByOID(toReturn.getOid_Veiculo());
			// estancia movimento_pneu
			Movimento_PneuED edMov = new Movimento_PneuED();
			// traz oid_pneu, oid_veiculo de movimento_pneu
			edMov.setOid_Pneu(ed.getOid_Pneu());
			edMov.setOid_Veiculo(toReturn.getOid_Veiculo());
			// busca movimentos
			edMov = new Movimento_PneuBD(sql).getByRecord(edMov);
            // valores dos movimentos
			edMov.setDT_Remocao(Data.getDataYMD());
			edMov.setKM_Final((int) edVeiculo.getNR_Kilometragem_Atual());
			if (ed.getKM_Final() > 0);{
				edMov.setKM_Final(ed.getKM_Final());
			}
			// monta km atual
			toReturn.setKM_Atual(edMov.getKM_Pneu()	+ (edMov.getKM_Final() - edMov.getKM_Inicial()));


			//new PneuBD(sql).altera(toReturn);
			// Seta oid_Veiculo e Posição como nulas
			new PneuBD(sql).alteraSituacaoPneu(toReturn);
			// **** Altera Movimentação
			new Movimento_PneuBD(sql).altera(edMov);
			// **** Grava nova Movimentação
			new Movimento_PneuBD(sql).inclui(
					new Movimento_PneuED(
						ed.getDM_Localizacao(), ed.getDM_Situacao(),
						"", Data.getDataYMD(),0, 0,
						toReturn.getKM_Atual(), ed.getOid_Pneu(),
						"", "Remocao do Pneu")
					);

			// Entrada no estoque
			if (JavaUtil.doValida(String.valueOf(toReturn.getOid_estoque()))) {
				new EstoqueBD(sql).entrada_Estoque(String.valueOf(toReturn
						.getOid_estoque()), 1, ed.getOid_Veiculo());
			}

			this.fimTransacao(true);
		} catch (RuntimeException e) {
			this.abortaTransacao();
			throw e;
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		} catch (Exception e) {
			this.abortaTransacao();
			throw e;
		}
	}

	public void manutencaoPneuVeiculo(PneuED ed) throws Exception {
		try {
			// Validações
			if (ed.getOid_Pneu() < 1) {
				throw new Mensagens("ID Pneu não informado!");
			}
			if (!JavaUtil.doValida(ed.getDM_Localizacao())) {
				throw new Mensagens("Localização do Pneu não informada!");
			}
			if (!JavaUtil.doValida(ed.getDM_Situacao())) {
				throw new Mensagens("Situação do Pneu não informada!");
			}

			this.inicioTransacao();
			PneuED toReturn = new PneuBD(sql).getByRecord(ed.getOid_Pneu());
			new PneuBD(sql).alteraSituacaoPneu(ed);
			// **** Grava nova Movimentação caso localizações tenham sido
			// mudadas
			if (!ed.getDM_Localizacao().equals(toReturn.getDM_Localizacao())) {
				new Movimento_PneuBD(sql).inclui(new Movimento_PneuED(ed
						.getDM_Localizacao(), ed.getDM_Situacao(), Data
						.getDataYMD(), "", 0, 0, toReturn.getKM_Atual(), ed
						.getOid_Pneu(), "", "Manutencao do Pneu"));

				// Saida ou Entrada do estoque
				if (JavaUtil
						.doValida(String.valueOf(toReturn.getOid_estoque()))
						&& ed.getDM_Localizacao().equals("02")) {
					new EstoqueBD(sql).baixa_Estoque(String.valueOf(toReturn
							.getOid_estoque()), 1, ed.getOid_Veiculo());
				} else if (JavaUtil.doValida(String.valueOf(toReturn
						.getOid_estoque()))
						&& ed.getDM_Localizacao().equals("01")) {
					new EstoqueBD(sql).entrada_Estoque(String.valueOf(toReturn
							.getOid_estoque()), 1, ed.getOid_Veiculo());
				}
			}

			if ("8".equals(ed.getDM_Situacao())
					|| "9".equals(ed.getDM_Situacao())) {
				String manutencao = "---";
				if ("8".equals(ed.getDM_Situacao()))
					manutencao = "Sucata";
				if ("9".equals(ed.getDM_Situacao()))
					manutencao = "Descarte";


				new Movimento_PneuBD(sql).inclui(new Movimento_PneuED(ed
						.getDM_Localizacao(), ed.getDM_Situacao(), Data
						.getDataYMD(), "", 0, 0, toReturn.getKM_Atual(), ed
						.getOid_Pneu(), "", manutencao));

			}

			this.fimTransacao(true);
		} catch (RuntimeException e) {
			this.abortaTransacao();
			throw e;
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		} catch (Exception e) {
			this.abortaTransacao();
			throw e;
		}
	}

	public void altera(PneuED ed) throws Excecoes {
		inicioTransacao();
		try {
			new PneuBD(sql).altera(ed);
			fimTransacao(true);
		} catch (Excecoes e) {
			abortaTransacao();
			throw e;
		}
	}

	public void alteraUltima_Recapagem(PneuED ed) throws Excecoes {
		inicioTransacao();
		try {
			new PneuBD(sql).alteraUltima_Recapagem(ed);
			fimTransacao(true);
		} catch (Excecoes e) {
			abortaTransacao();
			throw e;
		}
	}

	public void alteraVenda(PneuED ed) throws Excecoes {
		inicioTransacao();
		try {
			new PneuBD(sql).alteraVenda(ed);
			fimTransacao(true);
		} catch (Excecoes e) {
			abortaTransacao();
			throw e;
		}
	}

	public void delete(PneuED ed) throws Excecoes {
		inicioTransacao();
		try {
			new PneuBD(sql).delete(ed);
			fimTransacao(true);
		} catch (Excecoes e) {
			abortaTransacao();
			throw e;
		}
	}

	public void deleteUltima_Recapagem(PneuED ed) throws Excecoes {
		inicioTransacao();
		try {
			new PneuBD(sql).deleteUltima_Recapagem(ed);
			fimTransacao(true);
		} catch (Excecoes e) {
			abortaTransacao();
			throw e;
		}
	}

	public void deleteVenda(PneuED ed) throws Excecoes {
		inicioTransacao();
		try {
			new PneuBD(sql).deleteVenda(ed);
			fimTransacao(true);
		} catch (Excecoes e) {
			abortaTransacao();
			throw e;
		}
	}


	public ArrayList lista(PneuED ed) throws Excecoes {
		try {
			inicioTransacao();
			ArrayList lista = new PneuBD(sql).listaJSTL(ed);
			return lista;
		} finally {
			fimTransacao(false);
		}

	}

	public ArrayList listaJSTL(PneuED ed) throws Excecoes {
		try {
			inicioTransacao();
			ArrayList lista = new PneuBD(sql).listaJSTL(ed);
			return lista;
		} finally {
			fimTransacao(false);
		}

	}

	public ArrayList lista_Consulta(PneuED ed) throws Excecoes {
		try {
			inicioTransacao();
			ArrayList lista = new PneuBD(sql).lista_Consulta(ed);
			return lista;
		} finally {
			fimTransacao(false);
		}

	}

	public ArrayList listaPar(PneuED ed) throws Excecoes {
		try {
			inicioTransacao();
			ArrayList listaPar = new PneuBD(sql).listaPar(ed);
			return listaPar;
		} finally {
			fimTransacao(false);
		}

	}

	public ArrayList listaUltima_Recapagem(PneuED ed) throws Excecoes {
		try {
			inicioTransacao();
			ArrayList listaUltima_Recapagem = new PneuBD(sql).listaUltima_Recapagem(ed);
			return listaUltima_Recapagem;
		} finally {
			fimTransacao(false);
		}

	}

	public PneuED getByRecord(int oid) throws Excecoes {
		inicioTransacao();
		try {
			return new PneuBD(this.sql).getByRecord(oid);
		} finally {
			fimTransacao(false);
		}
	}

	public PneuED getByRecord(PneuED ed) throws Excecoes {
		inicioTransacao();
		try {
			return new PneuBD(this.sql).getByRecord(ed);
		} finally {
			fimTransacao(false);
		}
	}

	public PneuED getByRecordOL(PneuED ed) throws Excecoes {
		inicioTransacao();
		try {
			return new PneuBD(this.sql).getByRecordOL(ed);
		} finally {
			fimTransacao(false);
		}
	}

	public PneuED getByRecordJSTL(PneuED ed) throws Excecoes {
		inicioTransacao();
		try {
			ArrayList lista = this.listaJSTL(ed);
			Iterator iterator = lista.iterator ();
			if (iterator.hasNext ()) {
				return (PneuED) iterator.next ();
			}
			else {
				return new PneuED ();
			}
		} finally {
			fimTransacao(false);
		}
	}

	public PneuED getByCodigo(String codigo) throws Excecoes {
		inicioTransacao();
		try {
			return new PneuBD(this.sql).getByCodigo(codigo);
		} finally {
			fimTransacao(false);
		}
	}

	public PneuED getByNrFabrica(String nrFabrica) throws Excecoes {
		inicioTransacao();
		try {
			return new PneuBD(this.sql).getByNrFabrica(nrFabrica);
		} finally {
			fimTransacao(false);
		}
	}

	public void geraRelPneus(PneuED ed, HttpServletResponse res)
			throws Excecoes {
		inicioTransacao();
		try {
			new PneuBD(this.sql).geraRelPneus(ed, res);
		} finally {
			fimTransacao(false);
		}
	}

	//************L A S Z L O*****************************************

    /**
     * Cria a lista de posicoes vazias . Usado para a tela pns310L e para o relatorio pns310
     * @param ed
     * @return lista de posições vazias
     * @throws Excecoes
     */
    public ArrayList listaPosicoesVazias (PneuED ed) throws Excecoes {
    	BancoUtil bu = new BancoUtil();
    	try {
    		this.inicioTransacao();
    		ArrayList lista = new ArrayList(); // Array final que será enviado para o relatório/OL
    		VeiculoRN veicRN = new VeiculoRN();
    		VeiculoED veicED = new VeiculoED();
    		veicED.setOid_Empresa(ed.getOid_Empresa());
    		ArrayList lstVeic = new VeiculoBD(this.sql).lista(veicED);
    		for (int i=0; i<lstVeic.size(); i++){  // Girando no array de veiculo
    			veicED = (VeiculoED)lstVeic.get(i);
    			PneuED pneuED = new PneuED ();
    			pneuED.setOid_Veiculo(veicED.getOid_Veiculo());
    			ArrayList lstPos = veicRN.pegaPosicoes(veicED);  			// Lista de todas as posições possíveis no veiculo
    			ArrayList lstPneus = new PneuBD(this.sql).lista(pneuED); 	// Lista de todos os pneus montados no veículo
    			for (int x=0; x<lstPneus.size(); x++){ // Girando no array de pneus para cada veiculo
    				pneuED = (PneuED)lstPneus.get(x);
    				for (int y=0; y<lstPos.size(); y++){ // Girando no array de posições para cada pneu
    					String pos = (String)lstPos.get(y);
    					if (pos.equals(pneuED.getDM_Posicao()) ) {
    						lstPos.set(y,"*");
    					}
    				}
    			}
				for (int y=0; y<lstPos.size(); y++){ // Girando no array de posições para cada pneu
					String pos = (String)lstPos.get(y);
					if ( !"*".equals(pos) ) {
						// Inicia montagem do ed para a lista
						PneuED pneuEDLst = new PneuED();
						pneuEDLst.setNR_Frota(veicED.getNr_Frota());
						pneuEDLst.setDM_Posicao(pos);
						pneuEDLst.setNm_Contato_Recapagem(veicED.getNm_Unidade());
						//  Busca a última movimentação para a posição
						Movimento_PneuED movED = new Movimento_PneuED();
						movED.setOid_Veiculo(veicED.getOid_Veiculo());
						movED.setDm_Posicao(pos);
						movED = new Movimento_PneuBD(this.sql).getByRecord1(movED);
						// Completa o ed da lista com a informações da última movimentação....
						pneuEDLst.setNr_Serie((bu.doValida(movED.getDt_Entrada()) ? movED.getDt_Entrada() : ""));
						pneuEDLst.setMM_Atual(movED.getNr_Odometro_Entrada());
						if ("STP1".equals(pneuEDLst.getDM_Posicao()) || "STP2".equals(pneuEDLst.getDM_Posicao()) ) { // se for estepe
							if ("true".equals(ed.getDm_Controle_Parcial()) ) { // pergunta se inclui
								lista.add(pneuEDLst); // inclui na lista
							}
						} else {
							lista.add(pneuEDLst); // inclui na lista
						}
					}
				}
    		}
    		return lista;
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    /**
     *  Lista de pneus geminados não conforme
     * @param ed
     * @return lista de geminações não conforme
     * @throws Excecoes
     */
    public ArrayList listaGemNaoConf (PneuED ed) throws Excecoes {
    	try {
    		this.inicioTransacao();
    		BancoUtil bu = new BancoUtil();
    		ArrayList lista = new ArrayList(); // Array final que será enviado para o relatório/OL
    		VeiculoED veicED = new VeiculoED();
    		veicED.setOid_Empresa(ed.getOid_Empresa());
    		if (bu.doValida(ed.getCd_Item_Estoque())) {
    			veicED.setOid_Tipo_Veiculo(new Integer(ed.getCd_Item_Estoque()).intValue());
    		}
    		ArrayList lstVeic = new VeiculoBD(this.sql).lista(veicED);
	    		for (int i=0; i<lstVeic.size(); i++){  // Girando no array de veiculo
	    			veicED = (VeiculoED)lstVeic.get(i);
	    			PneuED pneuED = new PneuED ();
	    			pneuED.setOid_Veiculo(veicED.getOid_Veiculo());
	    			ArrayList lstEixo  = new PneuBD(this.sql).Busca_Eixo_Veiculo(pneuED); 	// Lista de os eixos, exceto os simples
	    			for (int x=0; x<lstEixo.size(); x++){// Girando no array de eixos
	    				PneuED pneuVolta = new PneuED ();
	    				pneuVolta = (PneuED)lstEixo.get(x);
						ed.setOid_Veiculo(veicED.getOid_Veiculo());
						ed.setDm_Eixo(pneuVolta.getDm_Eixo());
		    			ArrayList lstPneu  = new PneuBD(this.sql).Busca_Pneu(ed); 	// Lista de pneus
		    			if (lstPneu.size()>1) {
		    				// Buscar a lista de pneus do eixo do veiculo
		    				PneuED pneuRel = new PneuED ();
		    				pneuRel.setOid_Veiculo(veicED.getOid_Veiculo());
		    				pneuRel.setDm_Eixo(pneuVolta.getDm_Eixo());
		    				ArrayList lstPneusNGeminados  = new PneuBD(this.sql).listaJSTL(pneuRel);	// Lista de os eixos, exceto os simples
		    				for (int y=0; y<lstPneusNGeminados.size(); y++){ // Gira no array de pneus do eixo encontrado com desigualdades
		        				PneuED pneuRetorno = new PneuED ();
		        				pneuRetorno = (PneuED)lstPneusNGeminados.get(y);
		        				lista.add(pneuRetorno);
		    				}
		    			}
	    			}
	    		}
    		return lista;
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    /**
     *  Relatório de pneus
     * @param ed
     * @return PDF
     * @throws Excecoes
     */
    public void relatorio(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {

            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new PneuBD(sql).listaJSTL(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns015"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNr_Fogo()))
				nm_Filtro+=" Pneu=" + ed.getNr_Fogo();
			if (bu.doValida(ed.getNr_Serie()))
				nm_Filtro+=" Série=" + ed.getNr_Serie();
			if (bu.doValida(ed.getNm_Fabricante_Pneu()))
				nm_Filtro+=" Fabricante=" + ed.getNm_Fabricante_Pneu();
			if (bu.doValida(ed.getNm_Dimensao_Pneu()))
				nm_Filtro+=" Dimensão=" + ed.getNm_Dimensao_Pneu();
			if (bu.doValida(ed.getNm_Tipo_Pneu()))
				nm_Filtro+=" Tipo=" + ed.getNm_Tipo_Pneu();
			if (bu.doValida(ed.getNm_Modelo_Pneu()))
				nm_Filtro+=" Modelo=" + ed.getNm_Modelo_Pneu();
			if ((ed.getNr_Nota_Fiscal())>0)
				nm_Filtro+=" Nota Fiscal=" + ed.getNr_Nota_Fiscal();
			if ((ed.getDm_CP_Selecao())=="1")
				nm_Filtro+=" Controle Parcial=Com";
			if ((ed.getDm_CP_Selecao())=="2")
				nm_Filtro+=" Controle Parcial=Sem";
			if ((ed.getDm_CP_Selecao())=="3")
				nm_Filtro+=" Controle Parcial=Todos";
			if (bu.doValida(ed.getTx_Dot()))
				nm_Filtro+=" D.O.T.=" + ed.getTx_Dot();
			if (bu.doValida(ed.getCd_Item_Estoque()))
				nm_Filtro+=" Código do Estoque=" + ed.getCd_Item_Estoque();
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }

    /**
     *  Relatório de ultima recapagem
     * @param ed
     * @return PDF
     * @throws Excecoes
     */
    public void relatorioUltima_Recapagem(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	BancoUtil bu = new BancoUtil();
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ed.setDm_Virada("true");
    		ArrayList lista = new PneuBD(sql).listaUltima_Recapagem(ed);
    		ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns017"); // Seta o nome do relatório
    		// Monta a descricao do filtro utilizado
    		if (bu.doValida(ed.getNr_Fogo()))
    			nm_Filtro+=" Pneu=" + ed.getNr_Fogo();
    		if (bu.doValida(ed.getNm_Fornecedor_Ultima_Recapagem()))
    			nm_Filtro+=" Fornecedor=" + ed.getNm_Fornecedor_Ultima_Recapagem();
    		if (bu.doValida(ed.getNm_Fabricante_Ultima_Recapagem()))
    			nm_Filtro+=" Borracha=" + ed.getNm_Fabricante_Ultima_Recapagem();
    		if (bu.doValida(ed.getNm_Modelo_Pneu_Ultima_Recapagem()))
    			nm_Filtro+=" Modelo=" + ed.getNm_Modelo_Pneu_Ultima_Recapagem();
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    /**
     *  Relatório de pneus vendidos
     * @param ed
     * @return PDF
     * @throws Excecoes
     */
    public void relatorioVenda(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	BancoUtil bu = new BancoUtil();
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ed.setDm_Virada("true");
    		ed.setDt_Sucateamento("SUCATEAMENTO");
    		ArrayList lista = new PneuBD(sql).listaJSTL(ed);
    		ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns018"); // Seta o nome do relatório
    		// Monta a descricao do filtro utilizado
    		if (bu.doValida(ed.getNr_Fogo()))
    			nm_Filtro+=" Pneu=" + ed.getNr_Fogo();
    		if (bu.doValida(ed.getDt_Venda()))
    			nm_Filtro+=" Data=" + ed.getDt_Venda();
    		if (bu.doValida(ed.getTx_Comentario_Venda()))
    			nm_Filtro+=" Comentário=" + ed.getTx_Comentario_Venda();
    		ed.setDescFiltro(nm_Filtro);

    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    /**
     *  Relatório de pneus em recapagens
     * @param ed
     * @return PDF
     * @throws Excecoes
     */
    public void relatorioConsulta_Pneus_Recapagem(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	BancoUtil bu = new BancoUtil();
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ed.setDt_Remessa_Recapagem("RECAPAGEM");
    		ArrayList lista = new PneuBD(sql).listaJSTL(ed);
    		ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns303"); // Seta o nome do relatório
    		// Monta a descricao do filtro utilizado
    		if (bu.doValida(ed.getNm_Fornecedor()))
    			nm_Filtro+=" Fornecedor=" + ed.getNm_Fornecedor();
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    /**
     *  Relatório de pneus em estoque
     * @param ed
     * @return PDF
     * @throws Excecoes
     */
    public void relatorioConsulta_Pneus_Estoque(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	BancoUtil bu = new BancoUtil();
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ed.setDt_Estoque("ESTOQUE");
    		ArrayList lista = new PneuBD(sql).listaJSTL(ed);
    		Calendar dtDia = Data.stringToCalendar(Data.getDataDMY(),"dd/MM/yyyy");
    		for (int i=0; i<lista.size(); i++){
    			PneuED edVolta = new PneuED();
    			edVolta = (PneuED)lista.get(i);
    			Calendar dtStk = Data.stringToCalendar(edVolta.getDt_Estoque(),"dd/MM/yyyy");
				edVolta.setDiasEmEstoque(Data.diferencaDias(dtStk,dtDia));
    		}
    		ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns302"); // Seta o nome do relatório
    		// Monta a descricao do filtro utilizado
    		if (bu.doValida(ed.getNm_Local_Estoque()))
    			nm_Filtro+=" Estoque=" + ed.getNm_Local_Estoque();
    		if (bu.doValida(ed.getNm_Fabricante_Pneu()))
    			nm_Filtro+=" Marca=" + ed.getNm_Fabricante_Pneu();
    		if (bu.doValida(ed.getNm_Dimensao_Pneu()))
    			nm_Filtro+=" Dimensão=" + ed.getNm_Dimensao_Pneu();
    		if (bu.doValida(ed.getNm_Modelo_Pneu()))
    			nm_Filtro+=" Modelo=" + ed.getNm_Modelo_Pneu();
    		if (ed.getNr_Vida()<20) {
    			if (ed.getNr_Vida()==0)
    				nm_Filtro+=" Vida=" + "Novo";
				else
					nm_Filtro+=" Vida=" + "R"+ed.getNr_Vida();
    		} else if (ed.getNr_Vida()==99)
				nm_Filtro+=" Vida=" + "Todos Pneus";

    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    /**
     *  Relatório de pneus em estoque
     * @param ed
     * @return PDF
     * @throws Excecoes
     */
    public void relatorioConsolidado_Pneus_Estoque(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	BancoUtil bu = new BancoUtil();
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ed.setDt_Estoque("ESTOQUE");
    		ArrayList lista = new PneuBD(sql).listaJSTL(ed);
    		Calendar dtDia = Data.stringToCalendar(Data.getDataDMY(),"dd/MM/yyyy");
    		for (int i=0; i<lista.size(); i++){
    			PneuED edVolta = new PneuED();
    			edVolta = (PneuED)lista.get(i);
    			Calendar dtStk = Data.stringToCalendar(edVolta.getDt_Estoque(),"dd/MM/yyyy");
				edVolta.setDiasEmEstoque(Data.diferencaDias(dtStk,dtDia));
    		}
    		Collections.sort(lista, new Comparator() {
                public int compare(Object o1, Object o2) {
                    return ((PneuED)o1).getNm_Local_Estoque().compareTo(((PneuED)o2).getNm_Local_Estoque());
                }
            });
    		ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns302Consolidado"); // Seta o nome do relatório
    		// Monta a descricao do filtro utilizado
    		if (bu.doValida(ed.getNm_Local_Estoque()))
    			nm_Filtro+=" Estoque=" + ed.getNm_Local_Estoque();
    		if (bu.doValida(ed.getNm_Fabricante_Pneu()))
    			nm_Filtro+=" Marca=" + ed.getNm_Fabricante_Pneu();
    		if (bu.doValida(ed.getNm_Dimensao_Pneu()))
    			nm_Filtro+=" Dimensão=" + ed.getNm_Dimensao_Pneu();
    		if (bu.doValida(ed.getNm_Modelo_Pneu()))
    			nm_Filtro+=" Modelo=" + ed.getNm_Modelo_Pneu();
    		if (ed.getNr_Vida()<20) {
    			if (ed.getNr_Vida()==0)
    				nm_Filtro+=" Vida=" + "Novo";
				else
					nm_Filtro+=" Vida=" + "R"+ed.getNr_Vida();
    		} else if (ed.getNr_Vida()==99)
				nm_Filtro+=" Vida=" + "Todos Pneus";

    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    /**
     *  Relatório de Pneus sucateados
     * @param ed
     * @return PDF
     * @throws Excecoes
     */
    public void relatorioConsulta_Pneus_Sucateamento(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	BancoUtil bu = new BancoUtil();
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ed.setDt_Sucateamento("SUCATEAMENTO");
    		ArrayList lista = new PneuBD(sql).listaJSTL(ed);
    		ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns304"); // Seta o nome do relatório
    		// Monta a descricao do filtro utilizado
    		if (ed.getOid_Motivo_Sucateamento()>0)
    			nm_Filtro+=" Motivo=" + ed.getNm_Motivo_Sucateamento();
    		if (ed.getOid_Dimensao_Pneu()>0)
    			nm_Filtro+=" Dimensão=" + ed.getNm_Dimensao_Pneu();
    		if (ed.getOid_Fabricante_Pneu()>0)
    			nm_Filtro+=" Marca=" + ed.getNm_Fabricante_Pneu();
    		if (ed.getNr_Mm_Sulco_Maior()>0)
    			nm_Filtro+=" Sulco Saída >=" + ed.getNr_Mm_Sulco_Maior();
    		if (ed.getNr_Mm_Sulco_Menor()>0)
    			nm_Filtro+=" Sulco Saída <=" + ed.getNr_Mm_Sulco_Menor();
    		if (bu.doValida(ed.getDt_Inicial_Sucateamento()))
    			nm_Filtro+=" Dt Inicial >=" + ed.getDt_Inicial_Sucateamento();
    		if (bu.doValida(ed.getDt_Final_Sucateamento()))
    			nm_Filtro+=" Dt Final <=" + ed.getDt_Final_Sucateamento();
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    /**
     *  Relatório de peneus do veiculo
     * @param ed
     * @return PDF
     * @throws Excecoes
     */
    public void relatorioConsulta_Pneus_Veiculo(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		BancoUtil bu = new BancoUtil();
    		int i = 0 ;
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ArrayList lista = new PneuBD(sql).listaJSTL(ed);
    		ArrayList lstPns = new ArrayList();
    		VeiculoED veicED = new VeiculoED();
    		veicED.setOid_Veiculo(ed.getOid_Veiculo());
    		veicED = new VeiculoBD(this.sql).getByRecord(veicED);
    		veicED.defaultPosicoes();
    		// Monta o Pneu na posição certa dentro do relatório
    		for (i=0;i<lista.size();i++) {
    			PneuED pneuED = (PneuED)lista.get(i);
    			if (bu.doValida(pneuED.getNr_Fogo())) {
    				if ("STP1".equals(pneuED.getDM_Posicao())) veicED.setStp1(pneuED.getNr_Fogo());
    				if ("STP2".equals(pneuED.getDM_Posicao()))  veicED.setStp2(pneuED.getNr_Fogo());
    				if ("DE".equals(pneuED.getDM_Posicao())) veicED.setDe(pneuED.getNr_Fogo());
    				if ("D2E".equals(pneuED.getDM_Posicao())) veicED.setD2e(pneuED.getNr_Fogo());
    				if ("TEE".equals(pneuED.getDM_Posicao())) veicED.setTee(pneuED.getNr_Fogo());
    				if ("TEI".equals(pneuED.getDM_Posicao())) veicED.setTei(pneuED.getNr_Fogo());
    				if ("TKEE".equals(pneuED.getDM_Posicao())) veicED.setTkee(pneuED.getNr_Fogo());
    				if ("TKEI".equals(pneuED.getDM_Posicao())) veicED.setTkei(pneuED.getNr_Fogo());
    				if ("L1EE".equals(pneuED.getDM_Posicao())) veicED.setL1ee(pneuED.getNr_Fogo());
    				if ("L1EI".equals(pneuED.getDM_Posicao())) veicED.setL1ei(pneuED.getNr_Fogo());
    				if ("L2EE".equals(pneuED.getDM_Posicao())) veicED.setL2ee(pneuED.getNr_Fogo());
    				if ("L2EI".equals(pneuED.getDM_Posicao())) veicED.setL2ei(pneuED.getNr_Fogo());
    				if ("L3EE".equals(pneuED.getDM_Posicao())) veicED.setL3ee(pneuED.getNr_Fogo());
    				if ("L3EI".equals(pneuED.getDM_Posicao())) veicED.setL3ei(pneuED.getNr_Fogo());
    				if ("L4EE".equals(pneuED.getDM_Posicao())) veicED.setL4ee(pneuED.getNr_Fogo());
    				if ("L4EI".equals(pneuED.getDM_Posicao())) veicED.setL4ei(pneuED.getNr_Fogo());
    				if ("L5EE".equals(pneuED.getDM_Posicao())) veicED.setL5ee(pneuED.getNr_Fogo());
    				if ("L5EI".equals(pneuED.getDM_Posicao())) veicED.setL5ei(pneuED.getNr_Fogo());
    				if ("L6EE".equals(pneuED.getDM_Posicao())) veicED.setL6ee(pneuED.getNr_Fogo());
    				if ("L6EI".equals(pneuED.getDM_Posicao())) veicED.setL6ei(pneuED.getNr_Fogo());
    				if ("L7EE".equals(pneuED.getDM_Posicao())) veicED.setL7ee(pneuED.getNr_Fogo());
    				if ("L7EI".equals(pneuED.getDM_Posicao())) veicED.setL7ei(pneuED.getNr_Fogo());
    				if ("L8EE".equals(pneuED.getDM_Posicao())) veicED.setL8ee(pneuED.getNr_Fogo());
    				if ("L8EI".equals(pneuED.getDM_Posicao())) veicED.setL8ei(pneuED.getNr_Fogo());
    				if ("DD".equals(pneuED.getDM_Posicao())) veicED.setDd(pneuED.getNr_Fogo());
    				if ("D2D".equals(pneuED.getDM_Posicao())) veicED.setD2d(pneuED.getNr_Fogo());
    				if ("TDE".equals(pneuED.getDM_Posicao())) veicED.setTde(pneuED.getNr_Fogo());
    				if ("TDI".equals(pneuED.getDM_Posicao())) veicED.setTdi(pneuED.getNr_Fogo());
    				if ("TKDE".equals(pneuED.getDM_Posicao())) veicED.setTkde(pneuED.getNr_Fogo());
    				if ("TKDI".equals(pneuED.getDM_Posicao())) veicED.setTkdi(pneuED.getNr_Fogo());
    				if ("L1DE".equals(pneuED.getDM_Posicao())) veicED.setL1de(pneuED.getNr_Fogo());
    				if ("L1DI".equals(pneuED.getDM_Posicao())) veicED.setL1di(pneuED.getNr_Fogo());
    				if ("L2DE".equals(pneuED.getDM_Posicao())) veicED.setL2de(pneuED.getNr_Fogo());
    				if ("L2DI".equals(pneuED.getDM_Posicao())) veicED.setL2di(pneuED.getNr_Fogo());
    				if ("L3DE".equals(pneuED.getDM_Posicao())) veicED.setL3de(pneuED.getNr_Fogo());
    				if ("L3DI".equals(pneuED.getDM_Posicao())) veicED.setL3di(pneuED.getNr_Fogo());
    				if ("L4DE".equals(pneuED.getDM_Posicao())) veicED.setL4de(pneuED.getNr_Fogo());
    				if ("L4DI".equals(pneuED.getDM_Posicao())) veicED.setL4di(pneuED.getNr_Fogo());
    				if ("L5DE".equals(pneuED.getDM_Posicao())) veicED.setL5de(pneuED.getNr_Fogo());
    				if ("L5DI".equals(pneuED.getDM_Posicao())) veicED.setL5di(pneuED.getNr_Fogo());
    				if ("L6DE".equals(pneuED.getDM_Posicao())) veicED.setL6de(pneuED.getNr_Fogo());
    				if ("L6DI".equals(pneuED.getDM_Posicao())) veicED.setL6di(pneuED.getNr_Fogo());
    				if ("L7DE".equals(pneuED.getDM_Posicao())) veicED.setL7de(pneuED.getNr_Fogo());
    				if ("L7DI".equals(pneuED.getDM_Posicao())) veicED.setL7di(pneuED.getNr_Fogo());
    				if ("L8DE".equals(pneuED.getDM_Posicao())) veicED.setL8de(pneuED.getNr_Fogo());
    				if ("L8DI".equals(pneuED.getDM_Posicao())) veicED.setL8di(pneuED.getNr_Fogo());
    			}
    		}
    		// Identifica qual o relatorio pelo dm_Tipo_Chassis do veicED
    		// e chama o relatorio especifico
    		Modelo_VeiculoED ModVeicED = new Modelo_VeiculoED();
    		ModVeicED.setOid_Modelo_Veiculo(veicED.getOid_Modelo_Veiculo());
    		ModVeicED = new Modelo_VeiculoBD(this.sql).getByRecord(ModVeicED);
    		if (ModVeicED.getDm_Tipo_Chassis()==1) ed.setNomeRelatorio("pns306_PNEUCA04"); else
			if (ModVeicED.getDm_Tipo_Chassis()==2) ed.setNomeRelatorio("pns306_PNEUCA08"); else
			if (ModVeicED.getDm_Tipo_Chassis()==3) ed.setNomeRelatorio("pns306_PNEUCA12"); else
			if (ModVeicED.getDm_Tipo_Chassis()==4) ed.setNomeRelatorio("pns306_PNEUCA16"); else
			if (ModVeicED.getDm_Tipo_Chassis()==5) ed.setNomeRelatorio("pns306_PNEUCA32"); else
			if (ModVeicED.getDm_Tipo_Chassis()==6) ed.setNomeRelatorio("pns306_PNEUCAS4"); else
			if (ModVeicED.getDm_Tipo_Chassis()==7) ed.setNomeRelatorio("pns306_PNEUCAS6"); else
			if (ModVeicED.getDm_Tipo_Chassis()==8) ed.setNomeRelatorio("pns306_PNEUCO04"); else
			if (ModVeicED.getDm_Tipo_Chassis()==9) ed.setNomeRelatorio("pns306_PNEUCO06"); else
			if (ModVeicED.getDm_Tipo_Chassis()==10) ed.setNomeRelatorio("pns306_PNEUCO10"); else
			if (ModVeicED.getDm_Tipo_Chassis()==11) ed.setNomeRelatorio("pns306_PNEUDO08"); else
			if (ModVeicED.getDm_Tipo_Chassis()==12) ed.setNomeRelatorio("pns306_PNEUON06"); else
			if (ModVeicED.getDm_Tipo_Chassis()==13) ed.setNomeRelatorio("pns306_PNEUON08"); else
			if (ModVeicED.getDm_Tipo_Chassis()==14) ed.setNomeRelatorio("pns306_PNEUON09"); else
			if (ModVeicED.getDm_Tipo_Chassis()==15) ed.setNomeRelatorio("pns306_PNEUON10"); else
			if (ModVeicED.getDm_Tipo_Chassis()==16) ed.setNomeRelatorio("pns306_PNEUON12"); else
			if (ModVeicED.getDm_Tipo_Chassis()==17) ed.setNomeRelatorio("pns306_PNEUON14"); else
			if (ModVeicED.getDm_Tipo_Chassis()==18) ed.setNomeRelatorio("pns306_PNEURE02"); else
			if (ModVeicED.getDm_Tipo_Chassis()==19) ed.setNomeRelatorio("pns306_PNEURE08"); else
			if (ModVeicED.getDm_Tipo_Chassis()==20) ed.setNomeRelatorio("pns306_PNEURE12");
    		veicED.setSublista(lista);// Joga a lista bdos pneus para o subrelatório
    		lstPns.add(veicED);  // Coloca o ed do veiculonuma lista para enviar ao report
    		ed.setLista(lstPns); // Joga a lista de veiculos no ed para enviar pro relatório
    		ed.setResponse(response);
    		// Monta a descricao do filtro utilizado
    		if (bu.doValida(ed.getNR_Frota()))
    			nm_Filtro+=" Veiculo=" + ed.getNR_Frota();
    		ed.setDescFiltro(nm_Filtro);
    		HashMap map = new HashMap();
    		map.put("nr_Kilometragem_Atual", String.valueOf(veicED.getNr_Kilometragem_Atual()));
    		map.put("PATH_SUBLIST", Parametro_FixoED.getInstancia().getPATH_RELATORIOS());
    		ed.setHashMap(map);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    /**
     *  Relatório de posições vazias
     * @param ed
     * @return PDF
     * @throws Excecoes
     */
    public void relatorioPosicoesVazias(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	BancoUtil bu = new BancoUtil();
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ed.setLista(listaPosicoesVazias(ed)); // Busca Joga a lista de veiculos no ed para enviar pro relatório
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns310"); // Seta o nome do relatório
    		// Monta a descricao do filtro utilizado
    		if ("false".equals(ed.getDm_Controle_Parcial()))
    			nm_Filtro+=" Considerar Estepe=Não";
    		else
    			nm_Filtro+=" Considerar Estepe=Sim";
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }


    /**
     * Relatório de pneus geminados não conforme
     * @param ed
     * @return PDF
     * @throws Excecoes
     */
    public void relatorioGeminacoesNaoConforme(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		String texto = "";
    		ed.setLista(listaGemNaoConf(ed)); // Busca Joga a lista de geminações não conforme no ed para enviar pro relatório
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns311"); // Seta o nome do relatório
    		// Monta a descricao do filtro utilizado
    		if (bu.doValida(ed.getNm_Unidade()))
    			nm_Filtro+=" Tipo de Veículo=" + ed.getNm_Unidade();

    		if ("true".equals(ed.getDm_Tipo_Pneu()))
    			texto+=" Tipo=Sim";
    		if ("true".equals(ed.getDm_Dimensao_Pneu()))
    			texto+=" Dimensao=Sim";
    		if ("true".equals(ed.getDm_Fabricante_Pneu()))
    			texto+=" Marca=Sim";
    		if ("true".equals(ed.getDm_Modelo_Pneu()))
    			texto+=" Modelo=Sim";
    		if ("true".equals(ed.getDm_Vida_Pneu()))
    			texto+=" Vida=Sim";
    		if ("true".equals(ed.getDm_MM_Atual()))
    			texto+=" Prof.Sulco=Sim";
    		if ( !"".equals(texto) )
    			nm_Filtro+=" Comparar :" + texto;

    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }


    /**
     *  Relatório de pneus por marca
     * @param ed
     * @return PDF
     * @throws Excecoes
     */
    public void relatorioPneusPorMarca(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ed.setLista(listaPneusPorMarca(ed)); // Busca Joga a lista de geminações não conforme no ed para enviar pro relatório
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns312"); // Seta o nome do relatório
    		// Monta a descricao do filtro utilizado
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorioAnaliseSucata(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	String where="";
    	BancoUtil bu = new BancoUtil();
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		// Monta o filtro e a descricao do filtro utilizado
	    	if (bu.doValida(ed.getDt_Inicial_Sucateamento()) ) {
	    		where += " and p.dt_Sucateamento >= '" +  ed.getDt_Inicial_Sucateamento() +"' ";
	    		nm_Filtro+= " De: " + ed.getDt_Inicial_Sucateamento() + " ";
	    	}
	    	if (bu.doValida(ed.getDt_Final_Sucateamento()) ) {
	    		where += " and p.dt_Sucateamento <= '" +  ed.getDt_Final_Sucateamento() +"' ";
	    		nm_Filtro+= " Até: " + ed.getDt_Final_Sucateamento() + " ";
	    	}
	    	if (bu.doValida(String.valueOf(ed.getOid_Unidade())) ) {
	    		where += " and p.oid_unidade_sucateamento = " +  ed.getOid_Unidade() +" ";
	    		UnidadeED unidED = new UnidadeED();
	    		unidED.setOid_Empresa(ed.getOid_Empresa());
	    		unidED.setOid_Unidade(ed.getOid_Unidade());
	    		unidED = new UnidadeBD(this.sql).getByRecord(unidED);
	    		nm_Filtro+=" Unidade=" + unidED.getNm_Unidade();
	    	}
    		if (bu.doValida(ed.getOid_Fornecedor()) ) {
	    		where += " and p.oid_unidade_sucateamento = " +  ed.getOid_Fornecedor() +" ";
	    		UnidadeED unidED = new UnidadeED();
	    		unidED.setOid_Empresa(ed.getOid_Empresa());
	    		unidED.setOid_Unidade(Long.valueOf(ed.getOid_Fornecedor()).longValue());
	    		unidED = new UnidadeBD(this.sql).getByRecord(unidED);
	    		nm_Filtro+=" Unidade=" + unidED.getNm_Unidade();
	    	}
	    	if (ed.getOid_Modelo_Pneu()>0 ) {
	    		where += " and p.oid_Modelo_Pneu = " +  ed.getOid_Modelo_Pneu() +" ";
	    		Modelo_PneuED modpED = new Modelo_PneuED();
	    		modpED.setOid_Empresa(ed.getOid_Empresa());
	    		modpED.setOid_Modelo_Pneu(ed.getOid_Modelo_Pneu());
	    		modpED = new Modelo_PneuBD(this.sql).getByRecord(modpED);
	    		nm_Filtro+=" Modelo=" + modpED.getNM_Modelo_Pneu();
	    	}
	    	if (ed.getOid_Dimensao_Pneu()>0 ) {
	    		where += " and p.oid_Dimensao_Pneu = " +  ed.getOid_Dimensao_Pneu() +" ";
	    		Dimensao_PneuED dimpED = new Dimensao_PneuED();
	    		dimpED.setOid_Empresa(ed.getOid_Empresa());
	    		dimpED.setOid_Dimensao_Pneu(ed.getOid_Dimensao_Pneu());
	    		dimpED = new Dimensao_PneuBD(this.sql).getByRecord(dimpED);
	    		nm_Filtro+=" Dimensão=" + dimpED.getNm_Dimensao_Pneu();
	    	}
	    	if (ed.getOid_Fabricante_Pneu()>0 ) {
	    		where += " and p.oid_Fabricante_Pneu = " +  ed.getOid_Fabricante_Pneu() +" ";
	    		Fabricante_PneuED fabpED = new Fabricante_PneuED();
	    		fabpED.setOid_Empresa((int)ed.getOid_Empresa());
	    		fabpED.setOid_Fabricante_Pneu(ed.getOid_Fabricante_Pneu());
	    		fabpED = new Fabricante_PneuBD(this.sql).getByRecord(fabpED);
	    		nm_Filtro+=" Fabricante=" + fabpED.getNM_Fabricante_Pneu();
	    	}
	    	if (ed.getOid_Motivo_Sucateamento()>0 ) {
	    		where += " and p.oid_Motivo_Sucateamento = " +  ed.getOid_Motivo_Sucateamento() +" ";
	    		Motivo_SucateamentoED motiED = new Motivo_SucateamentoED();
	    		motiED.setOid_Motivo_Sucateamento(ed.getOid_Motivo_Sucateamento());
	    		motiED = new Motivo_SucateamentoBD(this.sql).getByRecord(motiED);
	    		nm_Filtro+=" Motivo=" + motiED.getNm_Motivo_Sucateamento();
	    	}
	    	if (bu.doValida(ed.getTx_Dot()) ) {
	    		where += " and p.tx_Dot >= '" +  ed.getTx_Dot() +"' ";
	    		nm_Filtro+=" D.O.T.=" + ed.getTx_Dot();
	    	}
	    	ed.setDescFiltro(nm_Filtro);
	    	// Busca os pneus sucateados
	    	ArrayList lst = new PneuBD(this.sql).analiseSucata(ed,where); // Lista dos sucateados
    		ed.setLista(lst); // Busca Joga a lista de veiculos no ed para enviar pro relatório
    		ArrayList lst1 = new PneuBD(this.sql).analiseSucataResumo1(ed,where); // Lista do primeiro resumo
    		ArrayList lst2 = new PneuBD(this.sql).analiseSucataResumo2(ed,where); // Lista do segundo resumo
    		ArrayList lst3 = new PneuBD(this.sql).analiseSucataResumo3(ed,where); // Lista do terceiro resumo
    		ArrayList lst4 = new PneuBD(this.sql).analiseSucataResumo4(ed,where); // Lista do quarto resumo
    		ArrayList lst5 = new PneuBD(this.sql).analiseSucataResumo5(ed,where); // Lista do quinto resumo
    		if (lst.size()>0) {
				PneuED voltaED1 = (PneuED)lst.get(lst.size()-1);  // Pega o ultimo registro da lista
				PneuED voltaED2 = (PneuED)lst1.get(lst1.size()-1); // Pega o ultimo registro da lista do primeiro resumo
				PneuED voltaED3 = (PneuED)lst2.get(lst2.size()-1); // Pega o ultimo registro da lista do segundo resumo
				PneuED voltaED4 = (PneuED)lst3.get(lst3.size()-1); // Pega o ultimo registro da lista do terceiro resumo
				PneuED voltaED5 = (PneuED)lst4.get(lst4.size()-1); // Pega o ultimo registro da lista do quarto resumo
				voltaED5.setSublista(lst5);  // Coloca no último registro da lista do quarto resumo a lista com o quinto resumo
				voltaED4.setSublista1(lst4); // Coloca no último registro da lista do terceiro resumo a lista com o quarto resumo
				voltaED3.setSublista(lst3);  // Coloca no último registro da lista do segundo resumo a lista com o terceiro resumo
				voltaED2.setSublista1(lst2); // Coloca no último registro da lista do primeiro resumo a lista com o segundo resumo
				voltaED1.setSublista(lst1);  // Coloca no último registro a lista com o primeiro resumo
    		}
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns506"); // Seta o nome do relatório
    		HashMap map = new HashMap();
    		map.put("RESUMO", ed.getDm_Eixo());
            map.put("PATH_SUBLIST", Parametro_FixoED.getInstancia().getPATH_RELATORIOS());
            ed.setHashMap(map);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    /**
     *
     * @param ed
     * @return lista de Por Marca
     * @throws Excecoes
     */
    public ArrayList listaPneusPorMarca (PneuED ed) throws Excecoes {
    	try {
    		this.inicioTransacao();
    		ArrayList lista = new ArrayList(); // Array final que será enviado para o relatório/OL
				PneuED pneuED = new PneuED ();
				pneuED.setOid_Empresa(ed.getOid_Empresa());
				pneuED.setOid_Fabricante_Pneu(ed.getOid_Fabricante_Pneu());
				pneuED.setOid_Dimensao_Pneu(ed.getOid_Dimensao_Pneu());
				pneuED.setOid_Modelo_Pneu(ed.getOid_Modelo_Pneu());
				pneuED.setDm_Considera_Sucateados(ed.getDm_Considera_Sucateados());
				ArrayList lstPneusPorMarca  = new PneuBD(this.sql).listaPorMarca(pneuED);	// Lista de pneus por marca
				for (int x=0; x<lstPneusPorMarca.size(); x++){ // Gira no array de pneus por marca encontrados
					PneuED marcaRetorno = new PneuED ();
					marcaRetorno = (PneuED)lstPneusPorMarca.get(x);
					lista.add(marcaRetorno);
				}
    		return lista;
    	} finally {
    		this.fimTransacao(false);
    	}
    }
    /**
     * Relatório de pneus comprados ( consulta de pneus vendidos no periodo )
     * @param ed
     * @param request
     * @param response
     * @throws Excecoes
     */
    public void relatorioPneusComprados(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ArrayList lst = new PneuBD(this.sql).listaJSTL(ed); // Lista dos pneus Comprados
    		ArrayList lstSub = new PneuBD(this.sql).listaGroupByDFM(ed); // Lista com o resumo dos pneus vendidos agrupados poe dimensao
			PneuED voltaED = (PneuED)lst.get(lst.size()-1); // Coloca no último registro a lista com o resumo
			voltaED.setSublista(lstSub);
    		ed.setLista(lst); // Busca Joga a lista de geminações não conforme no ed para enviar pro relatório
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns315"); // Seta o nome do relatório
    		// Monta a descricao do filtro utilizado

			if (bu.doValida(ed.getNm_Dimensao_Pneu()) )
				nm_Filtro+= "Dimesão: " + ed.getNm_Dimensao_Pneu() + " ";
			if (bu.doValida(ed.getNm_Fabricante_Pneu()) )
				nm_Filtro+= "Marca: " + ed.getNm_Fabricante_Pneu() + " ";
			if (bu.doValida(ed.getOid_Fornecedor()) )
				nm_Filtro+= "Fornecedor: " + ed.getNm_Fornecedor() + " ";
			if (bu.doValida(ed.getDt_Inicial_Compra()) || bu.doValida(ed.getDt_Final_Compra())) {
    			nm_Filtro+= "Período de: " +
    						(bu.doValida(ed.getDt_Inicial_Compra())? ed.getDt_Inicial_Compra():"início" ) +
    						" até: " +
    						(bu.doValida(ed.getDt_Final_Compra())? ed.getDt_Final_Compra():"fim" );
    		}
    		ed.setDescFiltro(nm_Filtro);
    		HashMap map = new HashMap();
            map.put("PATH_SUBLIST", Parametro_FixoED.getInstancia().getPATH_RELATORIOS());
    		ed.setHashMap(map);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    /**
     * Relatório de pneus vendidos ( consulta de pneus vendidos no periodo )
     * @param ed
     * @param request
     * @param response
     * @throws Excecoes
     */
    public void relatorioPneusVendidos(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ArrayList lst = new PneuBD(this.sql).listaJSTL(ed); // Lista dos pneus vendidos
    		ArrayList lstSub = new PneuBD(this.sql).listaGroupByDFM(ed); // Lista com o resumo dos pneus vendidos agrupados poe dimensao
			PneuED voltaED = (PneuED)lst.get(lst.size()-1); // Coloca no último registro a lista com o resumo
			voltaED.setSublista(lstSub);
			ed.setLista(lst); // Joga a lista de pneus vendidos no ed capa
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns317"); // Seta o nome do relatório
    		// Monta a descricao do filtro utilizado
    		if (bu.doValida(ed.getDt_Inicial_Sucateamento()) || bu.doValida(ed.getDt_Final_Sucateamento())) {
    			nm_Filtro = "Período de: " +
    						(bu.doValida(ed.getDt_Inicial_Sucateamento())? ed.getDt_Inicial_Sucateamento():"início" ) +
    						" até: " +
    						(bu.doValida(ed.getDt_Final_Sucateamento())? ed.getDt_Final_Sucateamento():"fim" );
    		}
    		ed.setDescFiltro(nm_Filtro);
    		HashMap map = new HashMap();
            map.put("PATH_SUBLIST", Parametro_FixoED.getInstancia().getPATH_RELATORIOS());
    		ed.setHashMap(map);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed capa
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorioPrevRetiradaVeiculo(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {

    	BancoUtil bu = new BancoUtil();
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ed.setLista(calculaRetirada(ed)); // Busca Joga a lista de veiculos no ed para enviar pro relatório
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns318"); // Seta o nome do relatório
    		// Monta a descricao do filtro utilizado
    		if (bu.doValida(ed.getNR_Frota()))
    			nm_Filtro+=" Veículo=" + ed.getNR_Frota();
    		if (ed.getNr_Hodometro_Veiculo() >= 0)
    			nm_Filtro+=" Hodômetro=" + FormataValor.formataValorBT(ed.getNr_Hodometro_Veiculo(),1);
    		if (ed.getTwi() >= 0)
    			nm_Filtro+=" TWI=" + FormataValor.formataValorBT(ed.getTwi(),1);
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorioFichas(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		VeiculoED veicED =  new VeiculoED();
    		ArrayList lista = new ArrayList();
    		lista.add(veicED);
    		ed.setLista(lista);
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns507");
    		new JasperRL(ed).geraRelatorio();
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    /**
     * Destroca/Desmonta pneu no veiculo.
     * 	1 - Verifica se o pneu ainda esta no veiculo
	 *  2 - Coloca no estoque
     *  2 - Retira do veiculo
     * @param ed
     * @throws Excecoes
     */
	public PneuED destrocaDesmonta(PneuED ed,String tipo) throws Excecoes {
		try {
			this.inicioTransacao();
			Movimento_PneuED movED = new Movimento_PneuED();
			PneuBD bd = new PneuBD(this.sql);
			if ("DM".equals(tipo)) {
				// Busca os dados da movimentação do pneu que entrou e vai desmontar ( aquela, incompleta )
				movED.setOid_Pneu(ed.getOid_Pneu());
				movED.setOid_Veiculo(ed.getOid_Veiculo());
				movED.setDm_Posicao(ed.getDM_Posicao());
				movED.setNr_Odometro_Entrada(ed.getNr_Hodometro_Veiculo());
				movED = new Movimento_PneuBD(this.sql).getByRecord1(movED);

				// Coloca no estoque e retira da frota
				PneuED pneuSaiED = new PneuED();
				pneuSaiED.setOid_Pneu(ed.getOid_Pneu());
				pneuSaiED.setOid_Local_Estoque(movED.getOid_Local_Estoque()); 	// Oid do estoque para retorno
				pneuSaiED.setDt_Estoque(movED.getDt_Entrada()); 				// Retorna pro estoque na data da entrada
				bd.atualizaSituacao(pneuSaiED);
				ed = new PneuED();
			} else

			if ("DT".equals(tipo)) {
				// Busca os dados da movimentação do pneu que entrou e vai sair ( aquela, incompleta )
				Movimento_PneuED movEntED = new Movimento_PneuED();
				movEntED.setOid_Pneu(ed.getOid_Pneu());
				movEntED.setOid_Veiculo(ed.getOid_Veiculo());
				movEntED.setDm_Posicao(ed.getDM_Posicao());
				movEntED.setNr_Odometro_Entrada(ed.getNr_Hodometro_Veiculo());
				movEntED =  new Movimento_PneuBD(this.sql).getByRecord1(movEntED);

				// Coloca o pneu que lá está agora no estoque e retira da frota
				PneuED pneuSaiED = new PneuED();
				pneuSaiED.setOid_Pneu((int)movEntED.getOid_Pneu());
				pneuSaiED.setOid_Local_Estoque(movEntED.getOid_Local_Estoque()); // Oid do estoque para retorno
				pneuSaiED.setDt_Estoque(movEntED.getDt_Entrada()); // Retorna pro estoque na data do dia
				bd.atualizaSituacao(pneuSaiED);

				// Busca os dados da movimentação do pneu que saiu e deve voltar ( aquela, completa )
				movED.setOid_Pneu_Colocado(ed.getOid_Pneu());
				movED.setOid_Veiculo(ed.getOid_Veiculo());
				movED.setDm_Posicao(ed.getDM_Posicao());
				movED.setNr_Odometro_Saida(ed.getNr_Hodometro_Veiculo());
				movED =  new Movimento_PneuBD(this.sql).getByRecord1(movED);

				// Busca os dados do pneu vai voltar
				PneuED pneuED = new PneuED();
				pneuED.setOid_Pneu(movED.getOid_Pneu());
				pneuED = bd.getByRecord(pneuED);

				// Coloca o pneu que vai voltar de novo no veículo
				PneuED pneuEntED = new PneuED();
				pneuEntED.setOid_Pneu(movED.getOid_Pneu());
				pneuEntED.setOid_Veiculo(movED.getOid_Veiculo());
				pneuEntED.setDM_Posicao(movED.getDm_Posicao());
				pneuEntED.setDm_Eixo(movED.getDm_Eixo());
				pneuEntED.setNr_Hodometro_Veiculo(movED.getNr_Odometro_Entrada());
				pneuEntED.setNr_Km_Acumulada_Veiculo(movED.getNr_Km_Acumulada_Entrada());
				// Recalcula a km correta do pneu
				pneuEntED.setNr_Km_Acumulada(pneuED.getNr_Km_Acumulada() - (movED.getNr_Km_Acumulada_Saida() - movED.getNr_Km_Acumulada_Entrada()) );
				pneuEntED.setMM_Atual(movED.getNr_Mm_Entrada());
				pneuEntED.setDt_Entrada(movED.getDt_Entrada());
				bd.atualizaSituacao(pneuEntED);
				// Retorna o ed para atualizar a posição na tela
				pneuEntED.setNr_Fogo(pneuED.getNr_Fogo());
				ed = pneuEntED;
			}
			// Acerta Movimento
			new Movimento_PneuBD(this.sql).desfazMovimento(movED,tipo);
			fimTransacao(true);
		} catch (Excecoes e) {
			abortaTransacao();
			throw e;
		}
		return ed;
	}

    /**
     * Monta/troca pneu no veiculo.
     * @param ed
     * @throws Excecoes
     */
	public PneuED trocaMonta(PneuED ed, String tipo) throws Excecoes {
		try {
			this.inicioTransacao();
			PneuBD bd = new PneuBD(this.sql);
			System.out.println("TrocaMonta:"+ed.getDM_Posicao()+"/"+ed.getDm_Eixo()+"/pneu:"+ed.getOid_Pneu());
			if (ed.getOid_Pneu_Retirado()>0) { // Retira pneu
				System.out.println("KM:"+ed.getNr_Km_Acumulada_Veiculo());
				PneuED pneuSaiED = new PneuED();
				pneuSaiED.setOid_Pneu_Retirado(ed.getOid_Pneu_Retirado());
				pneuSaiED.setMM_Atual(ed.getNr_Mm_Saida());
				pneuSaiED.setNr_Km_Acumulada_Veiculo(ed.getNr_Km_Acumulada_Veiculo());
				if ("TE".equals(tipo)) {
					pneuSaiED.setOid_Local_Estoque(ed.getOid_Local_Estoque());
					pneuSaiED.setDt_Estoque(ed.getDt_Entrada());
				} else if ("TR".equals(tipo)) {
					pneuSaiED.setOid_Fornecedor_Recapagem(ed.getOid_Fornecedor_Recapagem());
					pneuSaiED.setDt_Remessa_Recapagem(ed.getDt_Remessa_Recapagem());
					pneuSaiED.setDt_Promessa_Retorno_Recapagem(ed.getDt_Promessa_Retorno_Recapagem());
					pneuSaiED.setNm_Contato_Recapagem(ed.getNm_Contato_Recapagem());
					pneuSaiED.setNr_Os_Recapagem(ed.getNr_Os_Recapagem());
					pneuSaiED.setVl_Negociado_Recapagem(ed.getVl_Negociado_Recapagem());
				} else if ("TS".equals(tipo)){
					pneuSaiED.setDt_Sucateamento(ed.getDt_Sucateamento());
					pneuSaiED.setOid_Motivo_Sucateamento(ed.getOid_Motivo_Sucateamento());
				}
				bd.atualizaSituacao(pneuSaiED);
			}
			if (ed.getOid_Pneu()>0) { // Coloca pneu
				PneuED pneuEntED = new PneuED();
				pneuEntED.setOid_Pneu(ed.getOid_Pneu());
				pneuEntED.setOid_Veiculo(ed.getOid_Veiculo());
				pneuEntED.setDM_Posicao(ed.getDM_Posicao());
				pneuEntED.setDm_Eixo(ed.getDm_Eixo());
				pneuEntED.setNr_Hodometro_Veiculo(ed.getNr_Hodometro_Veiculo());
				pneuEntED.setNr_Km_Acumulada_Veiculo(ed.getNr_Km_Acumulada_Veiculo());
				pneuEntED.setMM_Atual(ed.getMM_Atual());
				System.out.println(pneuEntED.getDm_Eixo()+"/Pneu:"+pneuEntED.getCD_Pneu());
				if ("M".equals(tipo) || "TE".equals(tipo) || "MNT".equals(tipo))
					pneuEntED.setDt_Entrada(ed.getDt_Entrada());
				else if ("TR".equals(tipo))
					pneuEntED.setDt_Entrada(ed.getDt_Remessa_Recapagem());
				else if ("TS".equals(tipo))
					pneuEntED.setDt_Entrada(ed.getDt_Sucateamento());
				bd.atualizaSituacao(pneuEntED);
			}
			// Registra o historico do pneu que saiu e que entrou
			this.registraMovimento (ed,tipo);
			// Se deve sucatear, sucateia ué!
			if ("TS".equals(tipo)){
				PneuED pnSucED = new PneuED();
				pnSucED.setOid_Pneu(ed.getOid_Pneu_Retirado());
				pnSucED.setOid_Empresa(ed.getOid_Empresa());
				pnSucED.setDt_Sucateamento(ed.getDt_Sucateamento());
				fechaVida(pnSucED);
			}
			// Atualiza a km do veiculo se não for um lançamento retroativo
			if ( !"R".equals(ed.getDm_Virada()) )
				new VeiculoBD(this.sql).atualizaKm(ed);

			this.fimTransacao(true);
		} catch (Excecoes e) {
			System.out.println("probrema...");
			e.printStackTrace();
			this.abortaTransacao();
			throw e;
		}
		return ed;
	}

    /**
     * Rodizio de pneus no veiculo.
     * 	1 - Atualiza o pneu na nova posição
	 *  2 - Faz o historico da saida e entrada....
     * @param ed
     * @throws Excecoes
     */
	public PneuED rodizio(PneuED ed) throws Excecoes {
		try {
			this.inicioTransacao();
			PneuBD bd = new PneuBD(this.sql);
			// Busca o parametro array criando um ed para cada ocorrencia
			OLUtil ol = new OLUtil();
			ArrayList lst = ol.pegaArraydaRequest(ed.getArray());
			for (int x=0;x<lst.size();x++) {
				PneuED pneuRodizioED = (PneuED)lst.get(x);
				// Busca o eixo da nova posição ...
				pneuRodizioED.setDm_Eixo(this.getEixo(pneuRodizioED.getDM_Posicao(), ed.getOid_Veiculo())) ;

				// Retira o pneu do veiculo e coloca no estoque - é a penas um registro intermediário ...
				PneuED pneuSaiED = new PneuED();
				pneuSaiED.setOid_Empresa(ed.getOid_Empresa());
				pneuSaiED.setOid_Pneu_Retirado(pneuRodizioED.getOid_Pneu());
				pneuSaiED.setMM_Atual(ed.getNr_Mm_Saida());
				pneuSaiED.setNr_Km_Acumulada_Veiculo(ed.getNr_Km_Acumulada_Veiculo());
				pneuSaiED.setOid_Local_Estoque(0);
				pneuSaiED.setDt_Estoque(ed.getDt_Entrada());
				bd.atualizaSituacao(pneuSaiED);

				// Recoloca o pneu no veiculo na outra posição...
				PneuED pneuEntED = new PneuED();
				pneuEntED.setOid_Empresa(ed.getOid_Empresa());
				pneuEntED.setOid_Pneu(pneuRodizioED.getOid_Pneu());
				pneuEntED.setOid_Veiculo(ed.getOid_Veiculo());
				pneuEntED.setDM_Posicao(pneuRodizioED.getDM_Posicao());
				pneuEntED.setDm_Eixo(pneuRodizioED.getDm_Eixo());
				pneuEntED.setNr_Hodometro_Veiculo(ed.getNr_Hodometro_Veiculo());
				pneuEntED.setNr_Km_Acumulada_Veiculo(ed.getNr_Km_Acumulada_Veiculo());
				pneuEntED.setMM_Atual(ed.getMM_Atual()); // Ver isso ainda...
				pneuEntED.setDt_Entrada(ed.getDt_Entrada());
				bd.atualizaSituacao(pneuEntED);

				// Registra o histórico do pneu que saiu e que entrou
				pneuEntED.setOid_Pneu_Retirado(pneuEntED.getOid_Pneu());
				pneuEntED.setDm_CP_Selecao(pneuRodizioED.getDm_CP_Selecao());
				pneuEntED.setTx_Motivo_Troca("RODÍZIO");
				this.registraMovimento (pneuEntED,"RZ");
			}
			fimTransacao(true);

		} catch (Excecoes e) {
			abortaTransacao();
			throw e;
		}
		return ed;
	}

    /**
     * NOVO Rodizio de pneus no veiculo. JSTL!!!
     * 	1 - Atualiza o pneu na nova posição
	 *  2 - Faz o historico da saida e entrada....
     * @param ed
     * @throws Excecoes
     */
	public PneuED rodizioJSTL(PneuED ed) throws Excecoes {
		try {
			this.inicioTransacao();
			PneuBD bd = new PneuBD(this.sql);
			// Busca o parametro array criando um ed para cada ocorrencia
			ArrayList lst = (ArrayList)ed.getLista();
			for (int x=0;x<lst.size();x++) {
				PneuED pneuRodizioED = (PneuED)lst.get(x);
				System.out.println("oid:"+pneuRodizioED.getOid_Pneu()+"|   cd:"+pneuRodizioED.getCD_Pneu());
				System.out.println("Ant:"+pneuRodizioED.getDm_CP_Selecao()+"|  NOVA:"+pneuRodizioED.getDM_Posicao());
				System.out.println("----------------------------");
				// Busca o eixo da nova posição ...
				pneuRodizioED.setDm_Eixo(this.getEixo(pneuRodizioED.getDM_Posicao(), ed.getOid_Veiculo())) ;

				// Retira o pneu do veiculo e coloca no estoque - é a penas um registro intermediário ...
				PneuED pneuSaiED = new PneuED();
				pneuSaiED.setOid_Empresa(ed.getOid_Empresa());
				pneuSaiED.setOid_Pneu_Retirado(pneuRodizioED.getOid_Pneu());
				pneuSaiED.setMM_Atual(ed.getNr_Mm_Saida());
				pneuSaiED.setNr_Km_Acumulada_Veiculo(ed.getNr_Km_Acumulada_Veiculo());
				pneuSaiED.setOid_Local_Estoque(0);
				pneuSaiED.setDt_Estoque(ed.getDt_Entrada());
				bd.atualizaSituacao(pneuSaiED);

				// Recoloca o pneu no veiculo na outra posição...
				PneuED pneuEntED = new PneuED();
				pneuEntED.setOid_Empresa(ed.getOid_Empresa());
				pneuEntED.setOid_Pneu(pneuRodizioED.getOid_Pneu());
				pneuEntED.setOid_Veiculo(ed.getOid_Veiculo());
				pneuEntED.setDM_Posicao(pneuRodizioED.getDM_Posicao());
				pneuEntED.setDm_Eixo(pneuRodizioED.getDm_Eixo());
				pneuEntED.setNr_Hodometro_Veiculo(ed.getNr_Hodometro_Veiculo());
				pneuEntED.setNr_Km_Acumulada_Veiculo(ed.getNr_Km_Acumulada_Veiculo());
				pneuEntED.setMM_Atual(ed.getMM_Atual()); // Ver isso ainda...
				pneuEntED.setDt_Entrada(ed.getDt_Entrada());
				bd.atualizaSituacao(pneuEntED);

				// Registra o histórico do pneu que saiu e que entrou
				pneuEntED.setOid_Pneu_Retirado(pneuEntED.getOid_Pneu());
				pneuEntED.setDm_CP_Selecao(pneuRodizioED.getDm_CP_Selecao());
				pneuEntED.setTx_Motivo_Troca("RODÍZIO");
				this.registraMovimento (pneuEntED,"RZ");
			}
			fimTransacao(true);

		} catch (Excecoes e) {
			abortaTransacao();
			throw e;
		}
		return ed;
	}

	/**
	 *  Desfaz rodízio de pneus
	 *  1 - Atualiza pneu para a posição anterior
	 *  2 - Apaga e corrige as movimentações
	 * @param ed
	 * @return
	 * @throws Excecoes
	 */
	public String desfazRodizio(PneuED ed) throws Excecoes {
		try {
			this.inicioTransacao();
			PneuBD bd = new PneuBD(this.sql);
			String msg = "OK";
			Movimento_PneuED movED = new Movimento_PneuED();
			movED.setOid_Veiculo(ed.getOid_Veiculo());
			movED.setDt_Saida(ed.getDt_Entrada());
			movED.setNr_Odometro_Saida(ed.getNr_Hodometro_Veiculo());
			movED.setDm_Tipo_Movimento("R");
			ArrayList lst = new Movimento_PneuBD(this.sql).lista1(movED);
			// Verifica se cada um dos pneus do rodizio se mantem na mesma posição ainda.
			for (int i=0; i<lst.size(); i++){
				movED = new Movimento_PneuED();
				movED = (Movimento_PneuED) lst.get(i);
				PneuED pneuED = new PneuED();
				pneuED.setOid_Pneu(movED.getOid_Pneu());
				pneuED = new PneuBD(this.sql).getByRecordOL(pneuED);

				// Retira o pneu do veiculo e coloca no estoque - é a penas um registro intermediário ...
				PneuED pneuSaiED = new PneuED();
				pneuSaiED.setOid_Empresa(pneuED.getOid_Empresa());
				pneuSaiED.setOid_Pneu_Retirado(pneuED.getOid_Pneu());
				pneuSaiED.setMM_Atual(pneuED.getNr_Mm_Saida());
				// Recalcula a km correta do pneu
				pneuSaiED.setNr_Km_Acumulada(pneuED.getNr_Km_Acumulada() - (movED.getNr_Km_Acumulada_Saida() - movED.getNr_Km_Acumulada_Entrada()) );
				pneuSaiED.setOid_Local_Estoque(0);
				pneuSaiED.setDt_Estoque(movED.getDt_Entrada());
				bd.atualizaSituacao(pneuSaiED);

				// Coloca o pneu de novo no veículo
				PneuED pneuEntED = new PneuED();
				pneuEntED.setOid_Pneu(pneuED.getOid_Pneu());
				pneuEntED.setOid_Veiculo(pneuED.getOid_Veiculo());
				pneuEntED.setDM_Posicao(movED.getDm_Posicao());
				pneuEntED.setDm_Eixo(movED.getDm_Eixo());
				pneuEntED.setNr_Hodometro_Veiculo(movED.getNr_Odometro_Entrada());
				pneuEntED.setNr_Km_Acumulada_Veiculo(movED.getNr_Km_Acumulada_Entrada());
				pneuEntED.setMM_Atual(movED.getNr_Mm_Entrada());
				pneuEntED.setDt_Entrada(movED.getDt_Entrada());
				bd.atualizaSituacao(pneuEntED);
				// Acerta Movimento
				movED.setDm_Posicao(movED.getDm_Posicao_Destino());
				// Desfaz a movimentação
				new Movimento_PneuBD(this.sql).desfazMovimento(movED,"DR");
			}
			fimTransacao(true);
			return msg;
		} catch (Excecoes e) {
			abortaTransacao();
			throw e;
		}
	}

	/**
	 * Registra o movimento do(s) pneu(s)
	 * @param ed - Ed montado na tela de troca do lazlo - pns102
	 * @param destino - destino do pneu que saiu ESTOQUE, RECAPAGEM, SUCATA
	 * @throws Excecoes
	 */
	public void registraMovimento (PneuED ed, String destino)throws Excecoes {

		String dtTroca = "";
		if ("M".equals(destino))	dtTroca = ed.getDt_Entrada(); 			else
		if ("MNT".equals(destino))	dtTroca = ed.getDt_Entrada(); 			else
		if ("TE".equals(destino))	dtTroca = ed.getDt_Entrada(); 			else
		if ("RZ".equals(destino))	dtTroca = ed.getDt_Entrada(); 			else
		if ("TR".equals(destino))	dtTroca = ed.getDt_Remessa_Recapagem(); else
		if ("TS".equals(destino))	dtTroca = ed.getDt_Sucateamento();

		if ( ed.getOid_Pneu_Retirado() > 0 ) {
			// Busca o oid do movimento de entrada do pneu que saiu para atualizar os dados finais
			Movimento_PneuED movSED = new Movimento_PneuED();
			movSED.setOid_Pneu(ed.getOid_Pneu_Retirado());					// Pneu que está saindo
			movSED.setOid_Veiculo(ed.getOid_Veiculo());						// Veículo
			movSED.setDm_Posicao(ed.getDM_Posicao());						// Posição
			if ("RZ".equals(destino))										// Se for um rodizio coloca a posição original
				movSED.setDm_Posicao(ed.getDm_CP_Selecao());				// que foi enviada lá da tela do laszlo no campo "mula" dm_CP_Selecao

			// Busca o movimento de entrada do pneu que está saindo agora no veículo e posição
			movSED = new Movimento_PneuBD(this.sql).getByRecord1(movSED);

			// Agora atualiza os dados de saída do movimento, fechando assim o mesmo ( entrada + saída )
			movSED.setDm_Posicao_Destino(ed.getDM_Posicao());				// Posição de destino
			movSED.setOid_Pneu_Colocado(ed.getOid_Pneu()); 					// Pneu colocado
			movSED.setDt_Saida(dtTroca);									// Data da saida
			movSED.setNr_Odometro_Saida(ed.getNr_Hodometro_Veiculo());		// Hodometro da saída
			movSED.setNr_Km_Acumulada_Saida(ed.getNr_Km_Acumulada_Veiculo());// Km da Saida
			movSED.setNr_Mm_Saida(ed.getNr_Mm_Saida());						// Mm da saída
			movSED.setTx_Motivo(ed.getTx_Motivo_Troca());					// Motivo da saida
			movSED.setDm_Tipo_Movimento("RZ".equals(destino)? "R":"T");		// Tipo de movimento Rodizio ou Troca
			new Movimento_PneuBD(this.sql).registraSaida(movSED);			// Atualiza
		}

		// Cria um novo registro para a entrada do pneu colocado se houver...
		if (ed.getOid_Pneu()> 0) {
			// Faz historico de entrada
			Movimento_PneuED movEED = new Movimento_PneuED();
			movEED.setOid_Empresa(ed.getOid_Empresa());
			movEED.setOid_Pneu(ed.getOid_Pneu()); 							// Pneu colocado
			movEED.setOid_Veiculo(ed.getOid_Veiculo());
			movEED.setDt_Entrada(dtTroca);									// Data da troca
			movEED.setNr_Odometro_Entrada(ed.getNr_Hodometro_Veiculo());
			movEED.setNr_Km_Acumulada_Entrada(ed.getNr_Km_Acumulada_Veiculo());
			movEED.setDm_Posicao(ed.getDM_Posicao());
			movEED.setDm_Eixo(ed.getDm_Eixo());
			movEED.setNr_Vida(ed.getNr_Vida());
			movEED.setNr_Mm_Entrada(ed.getMM_Atual());
			movEED.setOid_Local_Estoque(ed.getOid_Local_Estoque());
			movEED.setDm_Tipo_Movimento("N");								// Tipo de movimento "N" em aberto
			new Movimento_PneuBD(this.sql).inclui(movEED);
		}
	}

	/**
	 * Coloca pneu no Estoque
	 * 1 - Coloca do estoque
	 * @param ed
	 * @throws Excecoes
	 */
	public void entradaEstoque(PneuED ed) throws Excecoes {
		try {
			this.inicioTransacao();
			PneuBD bd = new PneuBD(this.sql);
			// Coloca no estoque e apaga a situacao anterior
			PneuED pneuStkED = new PneuED();
			pneuStkED.setOid_Pneu(ed.getOid_Pneu());
			pneuStkED.setOid_Local_Estoque(ed.getOid_Local_Estoque());
			pneuStkED.setDt_Estoque(ed.getDt_Estoque());
			bd.atualizaSituacao(pneuStkED);
			this.fimTransacao(true);
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		}
	}

	/**
	 *  Registra virada na roda - Registra somente no movimento
	 * @param ed
	 * @throws Excecoes
	 */
	public void viradaRoda(PneuED ed) throws Excecoes {
		inicioTransacao();
		try {
			Movimento_PneuED movEED = new Movimento_PneuED();
			movEED.setOid_Empresa(ed.getOid_Empresa());
			movEED.setOid_Pneu(ed.getOid_Pneu()); 								// Pneu virado
			movEED.setOid_Veiculo(ed.getOid_Veiculo());
			movEED.setDt_Entrada(ed.getDt_Entrada());							// Data da troca
			movEED.setNr_Odometro_Entrada(ed.getNr_Hodometro_Veiculo());		// Hodometro da virada
			movEED.setNr_Km_Acumulada_Entrada(ed.getNr_Km_Acumulada_Veiculo()); // Km Acumulada da virada
			movEED.setDm_Posicao(ed.getDM_Posicao());
			movEED.setDm_Posicao_Destino(ed.getDM_Posicao());
			movEED.setDm_Eixo(ed.getDm_Eixo());
			movEED.setNr_Vida(ed.getNr_Vida());
			movEED.setNr_Mm_Entrada(ed.getMM_Atual());
			movEED.setDm_Tipo_Movimento("V");
			movEED.setTx_Motivo("VIRADA NA RODA");
			new Movimento_PneuBD(sql).inclui(movEED);
			fimTransacao(true);
		} catch (Excecoes e) {
			abortaTransacao();
			throw e;
		}
	}

	/**
	 * Retorna o pneu do recape colocando em sucata
	 * 1 - Coloca na sucata
	 * @param ed
	 * @throws Excecoes
	 */
	public void retornoSucata(PneuED ed) throws Excecoes {
		try {
			this.inicioTransacao();
			// 1 - Fecha a vida do pneu.
			fechaVida(ed);
			// 2 - Coloca o pneu na sucata
			PneuBD bd = new PneuBD(this.sql);
			PneuED pneuSucED = new PneuED();
			pneuSucED.setOid_Pneu(ed.getOid_Pneu());
			pneuSucED.setOid_Motivo_Sucateamento(ed.getOid_Motivo_Sucateamento());
			pneuSucED.setDt_Sucateamento(ed.getDt_Sucateamento());
			bd.atualizaSituacao(pneuSucED);
			this.fimTransacao(true);
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		}
	}

	/**
	 * Retorna o pneu do recape colocando no estoque
	 * 1 - Fecha a vida atual
	 * 2 - Atualiza os dados da última recapagem
	 * 3 - Tira da recapagem
	 * 4 - Coloca em estoque
	 * @param ed
	 * @throws Excecoes
	 */
	public void retornoEstoqueRecapado(PneuED ed) throws Excecoes {
		try {
			PneuED pnuED = this.getByRecordJSTL(new PneuED(ed.getOid_Pneu()));

			System.out.println(ed.getDt_Estoque());

			this.inicioTransacao();
			// 1 - Fecha a vida do pneu.
			fechaVida(ed);
			// 2 - Cria a recapagem

			System.out.println(ed.getDt_Estoque());

			RecapagemED recED = new RecapagemED();
			recED.setOid_Pneu(ed.getOid_Pneu());
			recED.setOid_Empresa(ed.getOid_Empresa());
			recED.setOid_Banda(ed.getOid_Modelo_Pneu_Ultima_Recapagem());
			recED.setOid_Fabricante_Banda(ed.getOid_Fabricante_Ultima_Recapagem());
			if(JavaUtil.doValida(pnuED.getOid_Fornecedor_Recapagem())){
				recED.setOid_Fornecedor_Recapagem(pnuED.getOid_Fornecedor_Recapagem());
			} else
				recED.setOid_Fornecedor_Recapagem(ed.getOid_Fornecedor());

			recED.setDm_Garantia_Recapagem((ed.getDm_Garantia_Ultima_Recapagem()?"true":"false"));
			recED.setVl_Recapagem(ed.getVl_Ultima_Recapagem());
			recED.setDt_Recapagem(ed.getDt_Estoque());
			recED.setNr_Nota_Fiscal_Recapagem(ed.getNr_Nota_Fiscal_Ultima_Recapagem());
			recED.setNr_Os_Recapagem(ed.getNr_Os_Recapagem());
			recED.setNr_Mm_Sulco_Recapagem(ed.getNr_MM_Sulco_Ultima_Recapagem());
			recED.setNr_Perimetro(ed.getNr_Perimetro());
			new RecapagemBD(this.sql).inclui(recED);
			// 3 - Coloca no estoque e tira da recapagem
			PneuED pneuStkED = new PneuED();
			pneuStkED.setOid_Pneu(ed.getOid_Pneu());
			pneuStkED.setDt_Estoque(ed.getDt_Estoque());
			pneuStkED.setOid_Local_Estoque(ed.getOid_Local_Estoque());
			pneuStkED.setMM_Atual(ed.getNr_MM_Sulco_Ultima_Recapagem());
			pneuStkED.setNr_Vida(ed.getNr_Vida());
			pneuStkED.setCd_Item_Estoque(ed.getCd_Item_Estoque());
            // Coloca o pneu no estoque e atualiza a mm atual e a vida
			new PneuBD(this.sql).atualizaSituacao(pneuStkED);
			this.fimTransacao(true);
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		}
	}

	/**
	 * Retorna o pneu do recape colocando no estoque
	 * 1 - Registra o Conserto
	 * 2 - Tira da recapagem
	 * 3 - Coloca em estoque
	 * @param ed
	 * @throws Excecoes
	 */
	public void retornoEstoqueConsertado(PneuED ed) throws Excecoes {
		try {
			// Pega o pneu...
			PneuED pnED = this.getByRecordOL(ed);
			this.inicioTransacao();
			// 1 - Registra o conserto
			ConsertoED pnCons = new ConsertoED();
			pnCons.setOid_Pneu(ed.getOid_Pneu());
			pnCons.setOid_Fornecedor(ed.getOid_Fornecedor());
			pnCons.setDt_Conserto(ed.getDt_Estoque());
			pnCons.setVl_Conserto(ed.getVl_Preco());
			pnCons.setTx_Descricao_Conserto(ed.getTx_Lonas());
			pnCons.setNr_Documento_Conserto(String.valueOf(ed.getNr_Nota_Fiscal()));
			pnCons.setOid_Empresa(ed.getOid_Empresa());
			pnCons.setVl_Prof_Sulco_Resulcagem(0);
			pnCons.setOid_Veiculo(""); //Pega do último que rodou?
			pnCons.setDm_Vida_Conserto(pnED.getNr_Vida()); // Pega do pneu mesmo!
			ConsertoBD ConsBD = new ConsertoBD(this.sql);
			ConsBD.inclui(pnCons);
			// 2 - Coloca no estoque
			PneuED pneuStkED = new PneuED();
			pneuStkED.setOid_Pneu(ed.getOid_Pneu());
			pneuStkED.setDt_Estoque(ed.getDt_Estoque());
			pneuStkED.setOid_Local_Estoque(ed.getOid_Local_Estoque());
            // Coloca o pneu no estoque
			new PneuBD(this.sql).atualizaSituacao(pneuStkED);
			this.fimTransacao(true);
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		}
	}
	/**
	 * Retorna o pneu do recape colocando no estoque
	 * 1 - Registra a Recusa
	 * 2 - Coloca em estoque Tira da recapagem
	 * @param ed
	 * @throws Excecoes
	 */
	public void retornoEstoqueRecusado(PneuED ed) throws Excecoes {
		try {
			this.inicioTransacao();
			// 1 - Registra a Recusa
			new PneuBD(this.sql).registraRecusaRecapagem(ed);
			// 2 - Coloca no estoque
			PneuED pneuStkED = new PneuED();
			pneuStkED.setOid_Pneu(ed.getOid_Pneu());
			pneuStkED.setDt_Estoque(ed.getDt_Recusa_Recapagem());
			pneuStkED.setOid_Local_Estoque(ed.getOid_Local_Estoque());
            // Coloca o pneu no estoque
			new PneuBD(this.sql).atualizaSituacao(pneuStkED);
			this.fimTransacao(true);
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		}
	}

	/**
	 * Movimenta pneu de estoque para estoque
	 * 1 - Registra a Movimentação de estoque
	 * 2 - Coloca em estoque Tira da recapagem
	 * @param ed
	 * @throws Excecoes
	 */
	public void movimentaStkStk(PneuED ed) throws Excecoes {
		try {
			this.inicioTransacao();
			// 1 - Registra a Movimentação de estoque
			Movimento_Pneu_EstoqueED movPnED = new Movimento_Pneu_EstoqueED();
			movPnED.setOid_Pneu(ed.getOid_Pneu());
			movPnED.setOid_Empresa(ed.getOid_Empresa());
			movPnED.setDt_Movimento_Pneu_Estoque(ed.getDt_Estoque());
			movPnED.setOid_Local_Estoque_Origem(ed.getOid_Local_Estoque());
			movPnED.setOid_Local_Estoque_Destino(ed.getOid_estoque());
			new Movimento_Pneu_EstoqueBD(this.sql).inclui(movPnED);
			// 2 - Coloca no estoque
			PneuED pneuStkED = new PneuED();
			pneuStkED.setOid_Pneu(ed.getOid_Pneu());
			// Dados do estoque
			pneuStkED.setDt_Estoque(ed.getDt_Estoque());
			pneuStkED.setOid_Local_Estoque(ed.getOid_estoque());
            // Coloca o pneu no estoque
			new PneuBD(this.sql).atualizaSituacao(pneuStkED);
			this.fimTransacao(true);
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		}
	}

	/**
	 * Movimenta pneu de estoque para Recapagem
	 * 1 - Coloca na recapagem e retira do estoque
	 * @param ed
	 * @throws Excecoes
	 */
	public void movimentaStkRec(PneuED ed) throws Excecoes {
		try {
			this.inicioTransacao();
			// 1 - Coloca na recapagem e retira do estoque
			new PneuBD(this.sql).atualizaSituacao(ed);
			this.fimTransacao(true);
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		}
	}
	/**
	 * Movimenta pneu de estoque para Sucata
	 * 1 - Fecha vida
	 * 2 - Coloca na sucata e retira do estoque
	 * @param ed
	 * @throws Excecoes
	 */
	public void movimentaStkSuc(PneuED ed) throws Excecoes {
		try {
			this.inicioTransacao();
			// 1 - Fecha a vida
			fechaVida(ed);
			// 2 - Coloca na sucata e retira do estoque
			new PneuBD(this.sql).atualizaSituacao(ed);
			this.fimTransacao(true);
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		}
	}

	/** Fecha Vida
	 *  Cria um registro de vida a partir da vida atual ( em aberto )
	 * @param ed
	 * @param bu
	 * @throws Excecoes
	 */
	private void fechaVida(PneuED ed) throws Excecoes {
		BancoUtil bu = new BancoUtil();
		// Busca o pneu ...
		PneuED pneuED = new PneuED();
		pneuED.setOid_Pneu(ed.getOid_Pneu());
		pneuED = new PneuBD(this.sql).getByRecordJSTL(pneuED);
		// Busca a última vida
		Vida_PneuED ultVidaED = new Vida_PneuED();
		Vida_PneuBD vidaBD = new Vida_PneuBD(this.sql);
		ultVidaED.setOid_Pneu(ed.getOid_Pneu());
		ultVidaED = vidaBD.getUltimaVida(ultVidaED);
		// Busca a última movimentação
		Movimento_PneuED movPneuED = new Movimento_PneuED();
		movPneuED.setOid_Pneu(ed.getOid_Pneu());
		movPneuED = new Movimento_PneuBD(this.sql).getUltimoMovimento(movPneuED);
		// Cria registro da vida que esta fechando ...
		Vida_PneuED vidaED = new Vida_PneuED();
		// Monta a vida
		vidaED.setOid_Empresa(ed.getOid_Empresa());
		vidaED.setOid_Pneu(ed.getOid_Pneu());
		// Vida em que o pneu se encontra ...
		vidaED.setNr_Vida(pneuED.getNr_Vida());
		// Pega o último veiculo onde o pneu andou e o eixo ...
		vidaED.setOid_Veiculo(movPneuED.getOid_Veiculo());
		vidaED.setDm_Eixo(movPneuED.getDm_Eixo());
		// Pega a dt e km inicial da ultima vida ou do cadastramnto do pneu se não houver vida ainda
		if (ultVidaED.getOid_Vida_Pneu() > 0) {
			vidaED.setDt_Inicial(ultVidaED.getDt_Final());
			vidaED.setNr_Km_Inicial(ultVidaED.getNr_Km_Final());
		} else {
			vidaED.setDt_Inicial(pneuED.getDt_Nota_Fiscal());
			vidaED.setNr_Km_Inicial(pneuED.getNr_Km_Inicial());
		}
		// Data de finalização da vida
		if (bu.doValida(ed.getDt_Estoque())) {	// Retorno de recapagem em situação recapado
			vidaED.setDt_Final(ed.getDt_Estoque());
		} else
		if (bu.doValida(ed.getDt_Sucateamento())) { // Sucateamento direto ou retornado da recapagem para sucata.
			vidaED.setDt_Final(ed.getDt_Sucateamento());
		}
		// Dados do pneu ..
		vidaED.setNr_Km_Final(pneuED.getNr_Km_Acumulada());
		vidaED.setNr_Mm_Final(pneuED.getMM_Atual());
		// Dados da última recapagem ou do pneu ...
		RecapagemED recED = new RecapagemED();
		recED.setOid_Pneu(ed.getOid_Pneu());
		recED = new RecapagemBD(this.sql).getLastRecord(recED);
		if (bu.doValida(recED.getDt_Recapagem())) { // Encontrou um último registro de recapagem
			vidaED.setOid_Fabricante_Banda(recED.getOid_Fabricante_Banda());
			vidaED.setOid_Banda(recED.getOid_Banda());
			vidaED.setVl_Vida(recED.getVl_Recapagem());
			vidaED.setOid_Fornecedor(recED.getOid_Fornecedor_Recapagem());
			vidaED.setNr_Os(recED.getNr_Os_Recapagem());
			vidaED.setNr_Nota_Fiscal_Recape(recED.getNr_Nota_Fiscal_Recapagem());
			vidaED.setNr_Mm_Inicial(recED.getNr_Mm_Sulco_Recapagem());
		}else {
			vidaED.setOid_Fabricante_Banda(0);
			vidaED.setOid_Banda(0);
			vidaED.setVl_Vida(pneuED.getVl_Preco());
			vidaED.setOid_Fornecedor(pneuED.getOid_Fornecedor());
			vidaED.setNr_Os("");
			vidaED.setNr_Nota_Fiscal_Recape(pneuED.getNr_Nota_Fiscal());
			vidaED.setNr_Mm_Inicial(pneuED.getNr_Mm_Inicial());
		}
		//Grava a vida ...
		vidaBD.inclui(vidaED);
		// Soma um na vida para retornar a rotina chamadora ...
		ed.setNr_Vida(pneuED.getNr_Vida()+1);
	}

    public void relatorioConsulta_RecusasRecapagens(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lst = new PneuBD(sql).listaRecusasRecapagens(ed);
			PneuED voltaED = (PneuED)lst.get(lst.size()-1); // Coloca no último registro a lista para fazer o resumo
			voltaED.setSublista(lst);
			ed.setLista(lst); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns321"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
        	// Monta a descricao do filtro utilizado
			if (bu.doValida(ed.getNr_Fogo()))
				nm_Filtro+="Pneu:" + ed.getNr_Fogo();
			if (bu.doValida(ed.getNm_Fornecedor_Recapagem()) )
				nm_Filtro+= "Fornecedor: " + ed.getNm_Fornecedor_Recapagem() + " ";
			if (bu.doValida(ed.getDt_Inicial_Compra()) || bu.doValida(ed.getDt_Final_Compra())) {
    			nm_Filtro+= "Período de: " +
    						(bu.doValida(ed.getDt_Inicial_Compra())? ed.getDt_Inicial_Compra():"início" ) +
    						" até: " +
    						(bu.doValida(ed.getDt_Final_Compra())? ed.getDt_Final_Compra():"fim" );
    		}
			ed.setDescFiltro(nm_Filtro);
    		HashMap map = new HashMap();
            map.put("PATH_SUBLIST", Parametro_FixoED.getInstancia().getPATH_RELATORIOS());
    		ed.setHashMap(map);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }

    public void relatorioProcuraPar(PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lst = new PneuBD(sql).listaPar(ed);
			PneuED voltaED = (PneuED)lst.get(lst.size()-1); // Coloca no último registro a lista para fazer o resumo
			voltaED.setSublista(lst);
			ed.setLista(lst); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns320"); // Seta o nome do relatório
			PneuED pneuED = new PneuED();
			pneuED.setOid_Pneu(ed.getOid_Pneu());
			pneuED = new PneuBD(this.sql).getByRecordOL(pneuED);
			ed.setNr_Fogo(pneuED.getNr_Fogo());
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
        	// Monta a descricao do filtro utilizado
			if (bu.doValida(ed.getNr_Fogo()))
				nm_Filtro+="Pneu:" + ed.getNr_Fogo();
			ed.setDescFiltro(nm_Filtro);
    		HashMap map = new HashMap();
            map.put("PATH_SUBLIST", Parametro_FixoED.getInstancia().getPATH_RELATORIOS());
    		ed.setHashMap(map);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList listaRecusasRecapagens (PneuED ed) throws Excecoes {
    	try {
    		this.inicioTransacao();
    		ArrayList lista = new ArrayList(); // Array final que será enviado para o relatório/OL
				PneuED pneuED = new PneuED ();
				pneuED.setOid_Pneu(ed.getOid_Pneu());
				pneuED.setOid_Empresa(ed.getOid_Empresa());
				pneuED.setDt_Inicial_Compra(ed.getDt_Inicial_Compra());
				pneuED.setDt_Final_Compra(ed.getDt_Final_Compra());
				pneuED.setOid_Fornecedor(ed.getOid_Fornecedor());
				ArrayList lstRecusasRecapagens  = new PneuBD(this.sql).listaRecusasRecapagens(pneuED);
				for (int x=0; x<lstRecusasRecapagens.size(); x++){ // Gira no array de pneus por marca encontrados
					PneuED recusaRetorno = new PneuED ();
					recusaRetorno = (PneuED)lstRecusasRecapagens.get(x);
					lista.add(recusaRetorno);
				}
    		return lista;
    	} finally {
    		this.fimTransacao(false);
    	}
    }


    /**
     * processaRL
     * Processa solicitação de relatorio OL retornando sempre um PDF.
     * @param rel = Qual o relatorio a ser chamado
     * @param Obj = Um bean populado com parametros para a geracao do relatorio
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws Excecoes
     */
    public void processaRL(String rel, Object Obj, HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException, Excecoes {
    	//Extrai o bean com os campos da request colados
    	PneuED ed = (PneuED)Obj;
    	ed.setRequest(request);

		if ("1".equals(rel)) {
			this.relatorio(ed, request, response);
		} else
		if ("2".equals(rel)) {
			this.relatorioUltima_Recapagem(ed, request, response);
		} else
		if ("3".equals(rel)) {
			this.relatorioConsulta_Pneus_Recapagem(ed, request, response);
		} else
		if ("4".equals(rel)) {
			this.relatorioConsulta_Pneus_Estoque(ed, request, response);
		} else
		if ("5".equals(rel)) {
			this.relatorioConsulta_Pneus_Sucateamento(ed, request, response);
		} else
		if ("6".equals(rel)) {
			this.relatorioConsulta_Pneus_Veiculo(ed, request, response);
		} else
		if ("7".equals(rel)) {
			this.relatorioVenda(ed, request, response);
		} else
		if ("8".equals(rel)) {
			this.relatorioPosicoesVazias(ed, request, response);
    	} else
		if ("9".equals(rel)) {
			this.relatorioGeminacoesNaoConforme(ed, request, response);
		} else
		if ("10".equals(rel)) {
			this.relatorioPneusPorMarca(ed, request, response);
    	} else
		if ("11".equals(rel)) {
			this.relatorioPneusComprados(ed, request, response);
    	} else
		if ("12".equals(rel)) {
			this.relatorioPneusVendidos(ed, request, response);
    	} else
		if ("13".equals(rel)) {
			this.relatorioPrevRetiradaVeiculo(ed, request, response);
    	} else
		if ("14".equals(rel)) {
			this.relatorioConsulta_RecusasRecapagens(ed, request, response);
    	} else
		if ("15".equals(rel)) {
			this.relatorioProcuraPar(ed, request, response);
    	} else
		if ("16".equals(rel)) {
			this.relatorioAnaliseSucata(ed, request, response);
    	} else
		if ("17".equals(rel)) {
			this.relatorioFichas(ed, request, response);
    	}
    }

    /**
     * processaOL
     * Processa solicitação de tela OL retornando sempre arquivo XML com a seguinte estrutura.
     * <cad>
     * 		<item campo=valor />
     * </cad>
     * @param acao
     * @param Obj
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws Excecoes
     */
    public void processaOL(String acao, Object Obj, HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException, Excecoes {
    	//Extrai o bean com os campos da request colados
    	PneuED ed = (PneuED)Obj;
    	//Prepara a saída
    	ed.setMasterDetails(request);

    	//ArrayList lstCL = new ArrayList();
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) { // Incluir pneu
			if (checkDuploPneu(ed,acao))
				out.println("<ret><item oknok='Já existente pneu com este nr Fogo !'/></ret>");
			else {
	    		ed = this.inclui(ed);
	    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Pneu() + "'/></ret>");
			}
    	} else
		if ("A".equals(acao)) { // Alterar pneu
			if (checkDuploPneu(ed,acao))
				out.println("<ret><item oknok='Já existente pneu com este nr Fogo !'/></ret>");
			else {
				this.altera(ed);
				out.println("<ret><item oknok='AOK' /></ret>");
			}
		} else
		if ("D".equals(acao)) { // Deletar pneu
			this.delete(ed);
			out.println("<ret><item oknok='DOK' /></ret>");
		} else
		if ("C".equals(acao)) { // Consultar um pneu
			PneuED edVolta = this.getByRecordOL(ed);
			if (Utilitaria.doValida(edVolta.getNr_Fogo())) {
				out.println("<cad>");
				out.println(montaRegistro(edVolta));
				out.println("</cad>");
			} else
				out.println("<ret><item oknok='Pneu não encontrado!' /></ret>");
		} else
		if ("C102".equals(acao)) { // Consultar um pneu usado exclusivamente pelo pns102L
			PneuED edVolta = this.getByRecordOL(ed);
			if (Utilitaria.doValida(edVolta.getNr_Fogo())) {
				ArrayList lst = this.statusPneu(ed);
				if ("E".equals((String)lst.get(0))) { // Se o pneu não está no estoque dá o erro
					out.println("<cad>");
					out.println(montaRegistro(edVolta));
					out.println("</cad>");
				} else {
					out.println("<ret><item oknok='" + (String)lst.get(1) + "!'/></ret>");
				}
			} else
				out.println("<ret><item oknok='Pneu não encontrado!' /></ret>");
		} else
		if ("SVH".equals(acao)) { //Simulação de virada de hodometro
			String dm_Retorno = this.getKmAcumulada(ed); // Calcula a km acumulada do veiculo
			if (dm_Retorno!=null) {
				out.println("<ret><item oknok='" + dm_Retorno + "' /></ret>"); // Vai dar a msg de virada no OL ...
			}else {
				String saida="<ret><item oknok='OK' ";
					saida +="nr_Km_Acumulada_Veiculo='" + FormataValor.formataValorBT(ed.getNr_Km_Acumulada_Veiculo(),1) +"' ";
					saida +=" /></ret>";
				out.println(saida);
			}
		} else
		if ("U".equals(acao)) { // Registrar ultima recapagem
			this.alteraUltima_Recapagem(ed);
			out.println("<ret><item oknok='AOK' /></ret>");
		} else
		if ("V".equals(acao)) { // Registrar Venda
			this.alteraVenda(ed);
			out.println("<ret><item oknok='AOK' /></ret>");
		} else
		if ("VD".equals(acao)) { // Altera a venda
			this.alteraVenda(ed);
			out.println("<ret><item oknok='AOK' /></ret>");
		} else
		if ("VR".equals(acao)) { // Virada na roda
			ArrayList lst = this.statusPneu(ed);
			if (!"F".equals((String)lst.get(0))) { // Se o pneu não está na frota, dá o erro e não atualiza
				out.println("<ret><item oknok='Pneu não está mais no veículo ! " + (String)lst.get(1) + "!'/></ret>");
			}else {
				ed.setDm_Eixo(this.getEixo(ed.getDM_Posicao(), ed.getOid_Veiculo())) ;  // Pega o eixo
				this.getKmAcumulada(ed); // Calcula a km acumulada do veiculo
				this.viradaRoda(ed);
				out.println("<ret><item oknok='VOK' /></ret>");
			}
		} else
		if ("RZ".equals(acao)) { // Registrar o rodízio
			String dm_Retorno = this.getKmAcumulada(ed); // Calcula a km acumulada do veiculo
			if (dm_Retorno!=null) {
				out.println("<ret><item oknok='" + dm_Retorno + "' /></ret>"); // Vai dar a msg de virada no OL ...
			}else {
				// Deu tudo certo é só gravar e retornar km acumulada + odometro + se atualiza odometro no OL ...
				this.rodizio(ed);
				String saida="<ret><item oknok='OK' ";
					saida +="nr_Km_Acumulada_Veiculo='" + FormataValor.formataValorBT(ed.getNr_Km_Acumulada_Veiculo(),1) +"' ";
					saida +="nr_Hodometro_Veiculo='" + FormataValor.formataValorBT(ed.getNr_Hodometro_Veiculo(),1) +"' ";
					saida +="atualizaOdometro='"+ ( "R".equals(ed.getDm_Virada()) ? "N" :"S" ) + "' ";  // Se for retroativo não atualiza odometro
					saida +=" /></ret>";
				out.println(saida);
			}
		} else
		if ("RE".equals(acao)) { // Retorno de recapagem para estoque (Recapado)
			this.retornoEstoqueRecapado(ed);
			out.println("<ret><item oknok='REOK' /></ret>");
		} else
		if ("RC".equals(acao)) { // Retorno de recapagem para estoque (Consertado)
			this.retornoEstoqueConsertado(ed);
			out.println("<ret><item oknok='RCOK' /></ret>");
		} else
		if ("RR".equals(acao)) { // Retorno de recapagem para estoque (Recusado)
			this.retornoEstoqueRecusado(ed);
			out.println("<ret><item oknok='RROK' /></ret>");
		} else
		if ("RS".equals(acao)) { // Retorno de recapagem para sucata
			this.retornoSucata(ed);
			out.println("<ret><item oknok='RSOK' /></ret>");
		} else
		if ("MEE".equals(acao)) { // Movimenta estoque para estoque
			this.movimentaStkStk(ed);
			out.println("<ret><item oknok='MEEOK' /></ret>");
		} else
		if ("MER".equals(acao)) { // Movimenta estoque para Recapagem
			this.movimentaStkRec(ed);
			out.println("<ret><item oknok='MEROK' /></ret>");
		} else
		if ("MES".equals(acao)) { // Movimenta estoque para Sucata
			this.movimentaStkSuc(ed);
			out.println("<ret><item oknok='MESOK' /></ret>");
		} else
		if ("DU".equals(acao)) { // Apagar ultima recapagem
			this.deleteUltima_Recapagem(ed);
			out.println("<ret><item oknok='DOK' /></ret>");
		} else
		if ("DV".equals(acao)) { // Apagar Venda
			this.deleteVenda(ed);
			out.println("<ret><item oknok='DOK' /></ret>");
		} else
		if ("EE".equals(acao)) { // Envia pneu para estoque
			this.entradaEstoque(ed);
			out.println("<ret><item oknok='RSOK' /></ret>");
		}else
	    // Montagem ou troca do pneu no veículo com pneu retirado destinado a estoque, sucata, recapagem
		if ("M".equals(acao) || "TE".equals(acao) || "TR".equals(acao) || "TS".equals(acao) ) {
			//Faz a validação do pneu - Nessa altura ja se sabe o oid mas se verifica novamente se o pneu esta disponivel no estoque
			ArrayList lst = this.statusPneu(ed);
			if (!"E".equals((String)lst.get(0))) { // Se o pneu não está no estoque dá o erro e não atualiza
				out.println("<ret><item oknok='" + (String)lst.get(1) + "!'/></ret>");
			}else {
				ed.setDm_Eixo(this.getEixo(ed.getDM_Posicao(), ed.getOid_Veiculo())) ;  // Pega o eixo
				String dm_Retorno = this.getKmAcumulada(ed); // Calcula a km acumulada do veiculo
				if (dm_Retorno!=null) {
					out.println("<ret><item oknok='" + dm_Retorno + "' /></ret>"); // Vai dar a msg de virada no OL ...
				}else {
					// Deu tudo certo é só gravar e retornar km acumulada + odometro + se atualiza odometro no OL ...
					this.trocaMonta(ed,acao);
					String saida="<ret><item oknok='OK' ";
						saida +="oid_Pneu='" + ed.getOid_Pneu() +"' ";
						saida +="nr_Km_Acumulada_Veiculo='" + FormataValor.formataValorBT(ed.getNr_Km_Acumulada_Veiculo(),1) +"' ";
						saida +="nr_Hodometro_Veiculo='" + FormataValor.formataValorBT(ed.getNr_Hodometro_Veiculo(),1) +"' ";
						saida +="atualizaOdometro='"+ ( "R".equals(ed.getDm_Virada()) ? "N" :"S" ) + "' ";  // Se for retroativo não atualiza odometro
						saida +=" /></ret>";
					out.println(saida);
				}
			}
		} else
		if ("DM".equals(acao) || "DT".equals(acao)) {  // Destroca/Desmontagem do pneu
			PneuED edVolta = this.getByRecordOL(ed);
			if ( !edVolta.getOid_Veiculo().equals(ed.getOid_Veiculo()) ) { // Verifica se o pneu ainda esta no veiculo
				out.println("<ret><item oknok='Pneu não está mais neste veiculo' /></ret>"); //
			} else {
				ed = this.destrocaDesmonta(edVolta,acao);
				String saida="";
				saida="<ret><item oknok='OK' ";
				saida +="oid_Pneu='"+ed.getOid_Pneu()+"' ";
				saida +="nr_Fogo='"+ed.getNr_Fogo()+"' ";
				saida +="dt_Entrada='"+ed.getDt_Entrada()+"' ";
				saida +="nr_Km_Acumulada_Veiculo='"+FormataValor.formataValorBT(ed.getNr_Km_Acumulada_Veiculo(),1)+"' ";
				saida +="nr_Hodometro_Veiculo='"+FormataValor.formataValorBT(ed.getNr_Hodometro_Veiculo(),1)+"' ";
				//saida +="atualizaOdometro='"+ ( "R".equals(ed.getDm_Virada()) ? "N" :"S" ) + "' ";  // Se for retroativo não atualiza odometro
				saida +=" /></ret>";
				out.println(saida);
			}
		} else
		if ("DR".equals(acao)) { // Desfaz o último reodízio
			this.desfazRodizio(ed);
			out.println("<ret><item oknok='DOK' /></ret>");
		} else {
		out.println("<cad>");
		String saida=null;
		ArrayList lst = new ArrayList();
		if ("LCL".equals(acao)) { // Lista de pneus com calculo de retirada
			lst = this.calculaRetirada(ed);
		}
		if ("L".equals(acao)) { // Lista de pneus
			lst = this.lista(ed);
		}
		if ("LP".equals(acao)) { // Lista Pares Para o pneu
			lst = this.listaPar(ed);
		}
		if ("LE".equals(acao)) { // Lista de pneus em estoque
			ed.setDt_Estoque("ESTOQUE");
			lst = this.lista(ed);
		}
		if ("LR".equals(acao)) { // Lista de pneus em recapagem
			ed.setDt_Remessa_Recapagem("RECAPAGEM");
			lst = this.lista(ed);
		}
		if ("LS".equals(acao)) { // Lista de pneus em sucateados
			ed.setDt_Sucateamento("SUCATEAMENTO");
			lst = this.lista(ed);
		}
		if ("LU".equals(acao)) { // Lista de pneus com última recapagem
			lst = this.listaUltima_Recapagem(ed);
		}
		if ("LPV".equals(acao)) { // Lista posicoes vazias
			lst = this.listaPosicoesVazias(ed);
		}
		if ("LGNC".equals(acao)) { // Lista de geminação não conforme
			lst = this.listaGemNaoConf(ed);
		}
		if ("LPPM".equals(acao)) { // Lista de pneus por marca
			lst = this.listaPneusPorMarca(ed);
		}
		if ("LRR".equals(acao)) { // Lista de pneus por marca
			lst = this.listaRecusasRecapagens(ed);
		}

		for (int i=0; i<lst.size(); i++){
			PneuED edVolta = new PneuED();
			edVolta = (PneuED)lst.get(i);
			if ("L".equals(acao) || "LR".equals(acao) || "LS".equals(acao) ||
				"LE".equals(acao)|| "LPV".equals(acao) || "LGNC".equals(acao) ||
				"LPPM".equals(acao) || "LCL".equals(acao) || "LP".equals(acao) || "LRR".equals(acao)){ // Processa listas
				saida = montaRegistro(edVolta);
			}else
			if ("CB".equals(acao)) { // Lista combo
				saida = "<item ";
				saida += "value='" + edVolta.getOid_Pneu()+ "'>";
				saida +=  edVolta.getNr_Fogo();
				saida += "</item>";
			}else
			if ("LU".equals(acao)) { // Lista última recapagem
				saida =  "<item ";
				saida += "oid_Pneu='" + edVolta.getOid_Pneu() + "' ";
				saida += "nr_Fogo='" + edVolta.getNr_Fogo() + "' ";
				if (JavaUtil.doValida(edVolta.getNm_Fabricante_Pneu())) {
					saida += "nm_Fabricante_Pneu='" + edVolta.getNm_Fabricante_Pneu() + "' ";
				}else{
					saida += "nm_Fabricante_Pneu='" + "" + "' ";
				}
				if (JavaUtil.doValida(edVolta.getNm_Modelo_Pneu())) {
					saida += "nm_Modelo_Pneu='" + edVolta.getNm_Modelo_Pneu() + "' ";
				}else{
					saida += "nm_Modelo_Pneu='" + "" + "' ";
				}
				saida += "oid_Fornecedor_Ultima_Recapagem='" + edVolta.getOid_Fornecedor_Ultima_Recapagem() + "' ";
				saida += "oid_Fabricante_Ultima_Recapagem='" + edVolta.getOid_Fabricante_Ultima_Recapagem() + "' ";
				saida += "oid_Modelo_Pneu_Ultima_Recapagem='" + edVolta.getOid_Modelo_Pneu_Ultima_Recapagem() + "' ";
				if (edVolta.getVl_Ultima_Recapagem() > 0){
					saida += "vl_Ultima_Recapagem='" + FormataValor.formataValorBT(edVolta.getVl_Ultima_Recapagem(),2) + "' ";
				}else{
					saida += "vl_Ultima_Recapagem='" + "" + "' ";
				}
				if (JavaUtil.doValida(edVolta.getNr_Os_Ultima_Recapagem())){
					saida += "nr_Os_Ultima_Recapagem='" + edVolta.getNr_Os_Ultima_Recapagem() + "' ";
				}else{
					saida += "nr_Os_Ultima_Recapagem='" + "" + "' ";
				}
				if (edVolta.getNr_Nota_Fiscal_Ultima_Recapagem()  > 0){
					saida += "nr_Nota_Fiscal_Ultima_Recapagem='" + edVolta.getNr_Nota_Fiscal_Ultima_Recapagem() + "' ";
				}else{
					saida += "nr_Nota_Fiscal_Ultima_Recapagem='" + "" + "' ";
				}
				if (edVolta.getNr_MM_Sulco_Ultima_Recapagem() > 0){
					saida += "nr_MM_Sulco_Ultima_Recapagem='" + FormataValor.formataValorBT(edVolta.getNr_MM_Sulco_Ultima_Recapagem(),1) + "' ";
				}else{
					saida += "nr_MM_Sulco_Ultima_Recapagem='" + "" + "' ";
				}
				saida += "dt_Ultima_Recapagem='" + edVolta.getDt_Ultima_Recapagem() + "' ";
				saida += "dm_Garantia_Ultima_Recapagem='" + edVolta.getDm_Garantia_Ultima_Recapagem() + "' ";
				saida += "nm_Garantia_Ultima_Recapagem='" + edVolta.getNm_Garantia_Ultima_Recapagem() + "' ";
				saida += "/>";
			}
			out.println(saida);
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    public void processaRequisicao(PneuED ed, HttpServletRequest request)
			throws ServletException, IOException, Excecoes {
		Object toReturn = null;
		// PADRAO PARA TODAS...
		String acao = JavaUtil.getValueDef(request.getParameter("acao"), "M");
		UsuarioED user = (UsuarioED) RequestUtil.getSessionAttribute(request,
				"usuario");
		ed.setOid_Empresa(user.getOid_Empresa());
		ed.setUser(user.getOid_Usuario().intValue());
		ed.setUsuario_Stamp(user.getNm_Usuario());
		ed.setDm_Stamp(acao);
		ed.setTime_millis(System.currentTimeMillis());
		System.out.println(acao+"/Pneu:"+ed.getOid_Pneu());
		System.out.println(ed.getDt_Estoque());
		// FIM PADRAO!
		if ("I".equals(acao)) {
			if (checkDuploPneu(ed,acao)){
				toReturn = "Já existe pneu com este nr Fogo !";
				request.setAttribute("msg", toReturn);
			} else {
				this.inclui(ed);
			}
		} else if ("A".equals(acao)) {
			if (checkDuploPneu(ed,acao)){
				toReturn = "Já existe pneu com este nr Fogo !";
				request.setAttribute("msg", toReturn);
			} else {
				this.altera(ed);
			}
		} else if ("D".equals(acao)) {
			this.delete(ed);
		} else if ("VR".equals(acao)) { // Virada na roda
			ArrayList lst = this.statusPneu(ed);
			if (!"F".equals((String) lst.get(0))) { // Se o pneu não está na
													// frota, dá o erro e não
													// atualiza
				toReturn = (String) lst.get(1);
				request.setAttribute("msg", toReturn);
			} else {
				ed.setDm_Eixo(this.getEixo(ed.getDM_Posicao(), ed
						.getOid_Veiculo())); // Pega o eixo
				// this.getKmAcumulada(ed); // Calcula a km acumulada do veiculo
				ed.setNr_Km_Acumulada_Veiculo(ed.getNr_Hodometro_Veiculo());
				this.viradaRoda(ed);
			}
		} else if ("MNT".equals(acao) || "TE".equals(acao) || "TR".equals(acao)
				|| "TS".equals(acao)) {
			// Faz a validação do pneu - Nessa altura ja se sabe o oid mas se
			// verifica novamente se o pneu esta disponivel no estoque
			System.out.println("pos:"+ed.getDM_Posicao()+"/"+ed.getDm_Eixo()+"/pneu:"+ed.getOid_Pneu());
			ArrayList lst = this.statusPneuJSTL(ed, acao);
			if (!"E".equals((String) lst.get(0)) && !"TE".equals(acao)
					&& !"TR".equals(acao) && !"TS".equals(acao)) { // Se o pneu
																	// não está
																	// no
																	// estoque
																	// dá o erro
																	// e não
																	// atualiza
				toReturn = (String) lst.get(1);
				request.setAttribute("msg", toReturn);
				System.out.println("msg:"+toReturn);
			} else {
				if ("TE".equals(acao) || "TR".equals(acao) || "TS".equals(acao)) {
					if (ed.getOid_Pneu_Retirado() > 0) {
						ed.setOid_Pneu(0);
					} else {
						ed.setOid_Pneu_Retirado(ed.getOid_Pneu());
						ed.setOid_Pneu(0);
					}
				}
				System.out.println("seguindo > pneu:"+ed.getOid_Pneu());
				// Deu tudo certo é só gravar e retornar km acumulada + odometro
				ed.setNr_Km_Acumulada_Veiculo(ed.getNr_Hodometro_Veiculo());
				ed.setDm_Virada("R");
				System.out.println("seguindo > pneu:"+ed.getOid_Pneu());
				this.trocaMonta(ed, acao);
				// }
			}
		} else if ("DM".equals(acao) || "DT".equals(acao)) { // Destroca/Desmontagem
																// do pneu
			PneuED edVolta = this.getByRecordJSTL(ed);
			if (!edVolta.getOid_Veiculo().equals(ed.getOid_Veiculo())) { // Verifica
																			// se o
																			// pneu
																			// ainda
																			// esta
																			// no
																			// veiculo
				toReturn = "Pneu não está mais neste veiculo";
				request.setAttribute("msg", toReturn);
			} else {
				ed = this.destrocaDesmonta(edVolta, acao);

			}
		} else if ("RZ".equals(acao)) { // Registrar o rodízio
			ArrayList toRZ = new ArrayList();
//			PneuED busca = new PneuED();
//			busca.setOid_Veiculo(ed.getOid_Veiculo());
			ed.setNr_Vida(99);
			ArrayList lista = this.listaJSTL(ed);
			ArrayList ocupadas = new ArrayList();
			int n;
			if ((n = lista.size()) != 0) {
				for (int i=0; i<n; i++) {
					PneuED p = (PneuED) lista.get(i);
					p.setDm_CP_Selecao(p.getDM_Posicao());
					p.setDM_Posicao(request.getParameter("DM_Posicao"+i));
					if(ocupadas.contains(p.getDM_Posicao())){
						toReturn = "Não é possível ocupar a posição "+p.getDM_Posicao()+" duas vezes!";
						request.setAttribute("msg", toReturn);
					}
					System.out.println(">>>> DM_Posicao"+i);
					System.out.println("oid:"+p.getOid_Pneu()+"|   cd:"+p.getCD_Pneu());
					System.out.println("Ant:"+p.getDm_CP_Selecao()+"|  NOVA:"+p.getDM_Posicao());
					System.out.println("----------------------------");
					toRZ.add(p);
					ocupadas.add((String)p.getDM_Posicao());
				}
			} else {
				toReturn = "Não existem Pneus neste neste veiculo!";
				request.setAttribute("msg", toReturn);
			}
			if(!JavaUtil.doValida((String)toReturn)){
				ed.setLista(toRZ);
				this.rodizioJSTL(ed);
			}
//			String dm_Retorno = this.getKmAcumulada(ed); // Calcula a km acumulada do veiculo
//			if (dm_Retorno!=null) {
//				out.println("<ret><item oknok='" + dm_Retorno + "' /></ret>"); // Vai dar a msg de virada no OL ...
//			}else {
				// Deu tudo certo é só gravar e retornar km acumulada + odometro + se atualiza odometro no OL ...
//			}
		} else if ("LE".equals(acao)) { // Lista de pneus em estoque
			ed.setDt_Estoque("ESTOQUE");
			ArrayList lst = new ArrayList();
			lst = this.listaJSTL(ed);
			request.setAttribute("lista", lst);
		} else if ("LR".equals(acao)) { // Lista de pneus em recapagem
			ed.setDt_Remessa_Recapagem("RECAPAGEM");
			ArrayList lst = new ArrayList();
			lst = this.listaJSTL(ed);
			request.setAttribute("lista", lst);
		} else if ("LS".equals(acao)) { // Lista de pneus em sucateados
			ed.setDt_Sucateamento("SUCATEAMENTO");
			ArrayList lst = new ArrayList();
			lst = this.listaJSTL(ed);
			request.setAttribute("lista", lst);
		} else if ("LRR".equals(acao)) { // Lista de recusa de recapagem
			ArrayList lst = new ArrayList();
			lst = this.listaRecusasRecapagens(ed);
			request.setAttribute("lista", lst);
		} else if ("MEE".equals(acao)) { // Movimenta estoque para estoque
			this.movimentaStkStk(ed);
		} else if ("MER".equals(acao)) { // Movimenta estoque para Recapagem
			ed.setOid_Fornecedor_Recapagem(ed.getOid_Fornecedor());
			ed.setOid_Fornecedor(null);
			this.movimentaStkRec(ed);
		} else if ("MES".equals(acao)) { // Movimenta estoque para Sucata
			this.movimentaStkSuc(ed);
		} else if ("RE".equals(acao)) { // Retorno de recapagem para estoque
										// (Recapado)
			this.retornoEstoqueRecapado(ed);
		} else if ("RC".equals(acao)) { // Retorno de recapagem para estoque
										// (Consertado)
			this.retornoEstoqueConsertado(ed);
		} else if ("RR".equals(acao)) { // Retorno de recapagem para estoque
										// (Recusado)
			ed.setOid_Fornecedor_Recusa_Recapagem(ed.getOid_Fornecedor());
			ed.setOid_Fornecedor(null);
			ed.setDt_Recusa_Recapagem(ed.getDt_Estoque());
			ed.setDt_Estoque(null);
			this.retornoEstoqueRecusado(ed);
		} else if ("RS".equals(acao)) { // Retorno de recapagem para sucata
			this.retornoSucata(ed);
		} else if ("HIS".equals(acao)) { // Historico
			ArrayList movs = new ArrayList();
			Movimento_PneuED mov = new Movimento_PneuED();
			mov.setOid_Pneu(ed.getOid_Pneu());
			mov.setOid_Empresa(ed.getOid_Empresa());
			movs = new Movimento_PneuRN().lista1(mov);
			ArrayList cons = new ArrayList();
			ConsertoED con = new ConsertoED();
			con.setOid_Pneu(ed.getOid_Pneu());
			con.setOid_Empresa(ed.getOid_Empresa());
			cons = new ConsertoRN().lista(con);
			ArrayList recaps = new ArrayList();
			RecapagemED recap = new RecapagemED();
			recap.setOid_Pneu(ed.getOid_Pneu());
			recap.setOid_Empresa(ed.getOid_Empresa());
			recaps = new RecapagemRN().lista(recap);
			ArrayList vids = new ArrayList();
			Vida_PneuED vid = new Vida_PneuED();
			vid.setOid_Pneu(ed.getOid_Pneu());
			vid.setNr_Vida(99);
			vid.setOid_Empresa(ed.getOid_Empresa());
			vids = new Vida_PneuRN().lista(vid);
			ArrayList recs = new ArrayList();
			recs = this.listaRecusasRecapagens(ed);

			request.setAttribute("movs", movs);
			request.setAttribute("cons", cons);
			request.setAttribute("recaps", recaps);
			request.setAttribute("vids", vids);
			request.setAttribute("recs", recs);

		} else {
			ArrayList lst = new ArrayList();
			lst = this.listaJSTL(ed);
			if ("L".equals(acao)) {
				request.setAttribute("lista", lst);
			} else if ("M".equals(acao) || "S".equals(acao)) {
				request.setAttribute("pneu", (PneuED) lst.get(0));
			}
		}
	}

    public void processaRelatorios(String rel, PneuED ed, HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, Excecoes {

    	UsuarioED user = (UsuarioED) RequestUtil.getSessionAttribute(request,"usuario");
		ed.setOid_Empresa(user.getOid_Empresa());
		ed.setUser(user.getOid_Usuario().intValue());
		ed.setUsuario_Stamp(user.getNm_Usuario());
    	ed.setRequest(request);

	if ("1".equals(rel)) {
		this.relatorio(ed, request, response);
	} else
	if ("2".equals(rel)) {
		this.relatorioUltima_Recapagem(ed, request, response);
	} else
	if ("3".equals(rel)) {
		this.relatorioConsulta_Pneus_Recapagem(ed, request, response);
	} else
	if ("4".equals(rel)) {
		this.relatorioConsulta_Pneus_Estoque(ed, request, response);
	} else
	if ("5".equals(rel)) {
		this.relatorioConsulta_Pneus_Sucateamento(ed, request, response);
	} else
	if ("6".equals(rel)) {
		this.relatorioConsulta_Pneus_Veiculo(ed, request, response);
	} else
	if ("7".equals(rel)) {
		this.relatorioVenda(ed, request, response);
	} else
	if ("8".equals(rel)) {
		this.relatorioPosicoesVazias(ed, request, response);
	} else
	if ("9".equals(rel)) {
		this.relatorioGeminacoesNaoConforme(ed, request, response);
	} else
	if ("10".equals(rel)) {
		this.relatorioPneusPorMarca(ed, request, response);
	} else
	if ("11".equals(rel)) {
		this.relatorioPneusComprados(ed, request, response);
	} else
	if ("12".equals(rel)) {
		this.relatorioPneusVendidos(ed, request, response);
	} else
	if ("13".equals(rel)) {
		this.relatorioPrevRetiradaVeiculo(ed, request, response);
	} else
	if ("14".equals(rel)) {
		this.relatorioConsulta_RecusasRecapagens(ed, request, response);
	} else
	if ("15".equals(rel)) {
		this.relatorioProcuraPar(ed, request, response);
	} else
	if ("16".equals(rel)) {
		this.relatorioAnaliseSucata(ed, request, response);
	} else
	if ("17".equals(rel)) {
		this.relatorioFichas(ed, request, response);
	} else
	if ("18".equals(rel)) {
		this.relatorioConsolidado_Pneus_Estoque(ed, request, response);
	}
}

	/**
	 * @param ed
	 * @throws Excecoes
	 */
	private ArrayList calculaRetirada(PneuED ed) throws Excecoes {
		PneuED edVolta = new PneuED();
		OLUtil ol = new OLUtil();
		ArrayList lst = ol.pegaArraydaRequest(ed.getArray());
		try {
    		this.inicioTransacao();
			for (int x=0;x<lst.size();x++) {
				PneuED pneuED = (PneuED)lst.get(x);
				edVolta.setOid_Pneu(pneuED.getOid_Pneu());
				edVolta = new PneuBD(this.sql).getByRecordOL(edVolta);
				pneuED.setNr_Fogo(edVolta.getNr_Fogo());
				pneuED.setDM_Posicao(edVolta.getDM_Posicao());
				pneuED.setNr_Hodometro_Veiculo(edVolta.getNr_Hodometro_Veiculo());
				pneuED.setNr_Km_Acumulada_Veiculo(edVolta.getNr_Km_Acumulada_Veiculo());
				// ed.getNr_Km_Acumulada_Veiculo() é a km acumulada referente ao hodometro entrado na tela mesmo se for virada.
				pneuED.setNr_Km_Vida_N0	(ed.getNr_Km_Acumulada_Veiculo()-edVolta.getNr_Km_Acumulada_Veiculo());
				pneuED.setNr_Km_Acumulada(edVolta.getNr_Km_Acumulada());
				pneuED.setMM_Atual(edVolta.getMM_Atual());

				pneuED.setMm_Gastos(edVolta.getMM_Atual() - pneuED.getMM());
				pneuED.setMm_Restantes(pneuED.getMM() - ed.getTwi());

				pneuED.setKm_Mm(pneuED.getNr_Km_Vida_N0() / pneuED.getMm_Gastos());
				pneuED.setKm_Restantes(pneuED.getKm_Mm() * pneuED.getMm_Restantes());

				//pneuED.setMM(edVolta.getMM_Atual());
			}
			} catch (Excecoes e) {
				this.abortaTransacao();
				throw e;
			}
		return lst;
	}

    private String montaRegistro(PneuED edVolta) {
    	String saida;
		saida =  "<item ";
		saida += "oid_Pneu='" + edVolta.getOid_Pneu() + "' ";
		saida += "oid_Fornecedor='" + edVolta.getOid_Fornecedor() + "' ";
		saida += "oid_Fornecedor_Recapagem='" + edVolta.getOid_Fornecedor_Recapagem() + "' ";
		saida += "oid_Fabricante_Pneu='" + edVolta.getOid_Fabricante_Pneu() + "' ";
		saida += "oid_Tipo_Pneu='" + edVolta.getOid_Tipo_Pneu() + "' ";
		saida += "oid_Modelo_Pneu='" + edVolta.getOid_Modelo_Pneu() + "' ";
		saida += "oid_Dimensao_Pneu='" + edVolta.getOid_Dimensao_Pneu() + "' ";
		saida += "oid_Empresa='" + edVolta.getOid_Empresa()         + "' ";
		saida += "oid_Local_Estoque='" + edVolta.getOid_Local_Estoque() + "' ";
		saida += "oid_Veiculo='" + edVolta.getOid_Veiculo() + "' ";
		saida += "CD_Pneu='" + edVolta.getCD_Pneu() + "' ";
		saida += "CD_Modelo_Pneu='" + edVolta.getCD_Modelo_Pneu() + "' ";
		saida += "cd_Item_Estoque='" + edVolta.getCd_Item_Estoque() + "' ";
		saida += "NR_Fabrica='" + edVolta.getNR_Fabrica() + "' ";
		saida += "nr_Frota='" + edVolta.getNR_Frota() + "' ";
		saida += "nr_Km_Acumulada='" + FormataValor.formataValorBT(edVolta.getNr_Km_Acumulada(),1) + "' ";
		saida += "nr_Km_Acumulada_Veiculo='" + FormataValor.formataValorBT(edVolta.getNr_Km_Acumulada_Veiculo(),1) + "' ";
		saida += "nr_Hodometro_Veiculo='" + FormataValor.formataValorBT(edVolta.getNr_Hodometro_Veiculo(),1) + "' ";
		saida += "nr_Nota_Fiscal='" + edVolta.getNr_Nota_Fiscal() + "' ";
		saida += "nr_Serie='" + edVolta.getNr_Serie() + "' ";
		saida += "nr_Vida='" + edVolta.getNr_Vida() + "' ";
		saida += "nr_Perimetro='" + FormataValor.formataValorBT(edVolta.getNr_Perimetro(),0) + "' ";
		saida += "KM_Atual='" + edVolta.getKM_Atual() + "' ";
		saida += "MM_Atual='" + FormataValor.formataValorBT(edVolta.getMM_Atual(),1) + "' ";
		saida += "nr_Mm_Saida='" + FormataValor.formataValorBT(edVolta.getNr_Mm_Saida(),0) + "' ";
		saida += "DM_Situacao='" + edVolta.getDM_Situacao() + "' ";
		saida += "dm_Controle_Parcial ='" + edVolta.getDm_Controle_Parcial() + "' ";
		saida += "DM_Localizacao='" + edVolta.getDM_Localizacao() + "' ";
		saida += "DM_Posicao='" + edVolta.getDM_Posicao() + "' ";
		saida += "dm_Eixo='" + edVolta.getDm_Eixo() + "' ";
		saida += "nr_Fogo='" + edVolta.getNr_Fogo() + "' ";
		saida += "nm_Fabricante_Pneu='" + edVolta.getNm_Fabricante_Pneu() + "' ";
		saida += "nm_Dimensao_Pneu='" + edVolta.getNm_Dimensao_Pneu() + "' ";
		saida += "nm_Modelo_Pneu='" + edVolta.getNm_Modelo_Pneu() + "' ";
		saida += "nm_Tipo_Pneu='" + edVolta.getNm_Tipo_Pneu() + "' ";
		saida += "nm_Fornecedor_Recapagem='" + edVolta.getNm_Fornecedor_Recapagem() + "' ";
		saida += "nm_Motivo_Sucateamento='" + edVolta.getNm_Motivo_Sucateamento() + "' ";
		saida += "nm_Local_Estoque='" + edVolta.getNm_Local_Estoque() + "' ";
		saida += "nm_Contato_Recapagem='" + edVolta.getNm_Contato_Recapagem() + "' ";
		saida += "nm_Razao_Social='" + edVolta.getNm_Razao_Social() + "' ";
		saida += "tx_Lonas='" + edVolta.getTx_Lonas() + "' ";
		saida += "tx_Dot='" + edVolta.getTx_Dot() + "' ";
		saida += "Desc_Eixo='" + edVolta.getDesc_Eixo() + "' ";
		saida += "Desc_Localizacao_Pneu='" + edVolta.getDesc_Localizacao_Pneu() + "' ";
		saida += "dt_Recusa_Recapagem='" + edVolta.getDt_Recusa_Recapagem() + "' ";
		saida += "tx_Motivo_Recusa_Recapagem='" + edVolta.getTx_Motivo_Recusa_Recapagem() + "' ";
		saida += "nr_Nota_Fiscal_Recusa_Recapagem='" + edVolta.getNr_Nota_Fiscal_Recusa_Recapagem() + "' ";
		saida += "nm_Responsavel_Recusa_Recapagem='" + edVolta.getNm_Responsavel_Recusa_Recapagem() + "' ";
		if (JavaUtil.doValida(edVolta.getTx_Comentario_Venda())) {
			saida += "tx_Comentario_Venda='" + edVolta.getTx_Comentario_Venda() + "' ";
		}else{
			saida += "tx_Comentario_Venda='" + "" + "' ";
		}
		//saida += "tx_Comentario_Venda='" + edVolta.getTx_Comentario_Venda() + "' ";
		saida += "tx_Situacao='" + edVolta.getTx_Situacao() + "' ";
		saida += "dt_Nota_Fiscal='" + edVolta.getDt_Nota_Fiscal() + "' ";
		saida += "dt_Sucateamento='" + edVolta.getDt_Sucateamento() + "' ";
		if (JavaUtil.doValida(edVolta.getDt_Venda())) {
			saida += "dt_Venda='" + edVolta.getDt_Venda() + "' ";
		}else{
			saida += "dt_Venda='" + "" + "' ";
		}
		//saida += "dt_Venda='" + edVolta.getDt_Venda() + "' ";
		saida += "dt_Entrada='" + edVolta.getDt_Entrada() + "' ";
		saida += "dt_Estoque='" + edVolta.getDt_Estoque() + "' ";
		saida += "DiasEmEstoque='" + edVolta.getDiasEmEstoque() + "' ";
		saida += "dt_Remessa_Recapagem='" + edVolta.getDt_Remessa_Recapagem() + "' ";
		saida += "dt_Promessa_Retorno_Recapagem='" + edVolta.getDt_Promessa_Retorno_Recapagem() + "' ";
		saida += "vl_Preco='" + FormataValor.formataValorBT(edVolta.getVl_Preco(),2) + "' ";
		if (edVolta.getVl_Venda() > 0){
			saida += "vl_Venda='" + FormataValor.formataValorBT(edVolta.getVl_Venda(),2) + "' ";
		}else{
			saida += "vl_Venda='" + "" + "' ";
		}
		//saida += "vl_Venda='" + FormataValor.formataValorBT(edVolta.getVl_Venda(),2) + "' ";
		saida += "vl_Negociado_Recapagem='" + FormataValor.formataValorBT(edVolta.getVl_Negociado_Recapagem(), 2) + "' ";
		saida += "oid_Fornecedor_Ultima_Recapagem='" + edVolta.getOid_Fornecedor_Ultima_Recapagem() + "' ";
		saida += "oid_Fabricante_Ultima_Recapagem='" + edVolta.getOid_Fabricante_Ultima_Recapagem() + "' ";
		saida += "oid_Modelo_Pneu_Ultima_Recapagem='" + edVolta.getOid_Modelo_Pneu_Ultima_Recapagem() + "' ";
		saida += "vl_Ultima_Recapagem='" + FormataValor.formataValorBT(edVolta.getVl_Ultima_Recapagem(),2) + "' ";
		saida += "dt_Ultima_Recapagem='" + edVolta.getDt_Ultima_Recapagem() + "' ";
		saida += "nr_Os_Ultima_Recapagem='" + edVolta.getNr_Os_Ultima_Recapagem() + "' ";
		saida += "nr_Os_Recapagem='" + edVolta.getNr_Os_Recapagem() + "' ";
		saida += "nr_Nota_Fiscal_Ultima_Recapagem='" + edVolta.getNr_Nota_Fiscal_Ultima_Recapagem() + "' ";
		saida += "dm_Garantia_Ultima_Recapagem='" + edVolta.getDm_Garantia_Ultima_Recapagem() + "' ";
		saida += "nm_Garantia_Ultima_Recapagem='" + edVolta.getNm_Garantia_Ultima_Recapagem() + "' ";
		saida += "nr_MM_Sulco_Ultima_Recapagem='" + FormataValor.formataValorBT(edVolta.getNr_MM_Sulco_Ultima_Recapagem(),1) + "' ";
		saida += "nm_Fornecedor='" + edVolta.getNm_Fornecedor() + "' ";
		// Campos para a lista de calculo previsão de retirada
		if (edVolta.getMm_Gastos() >= 0)
			saida += "mm_Gastos='" + FormataValor.formataValorBT(edVolta.getMm_Gastos(), 1) + "' ";
		if (edVolta.getMm_Restantes() >= 0)
			saida += "mm_Restantes='" + FormataValor.formataValorBT(edVolta.getMm_Restantes(), 1) + "' ";
		if (edVolta.getKm_Restantes() >= 0)
			saida += "km_Restantes='" + FormataValor.formataValorBT(edVolta.getKm_Restantes(), 1) + "' ";
		if (edVolta.getKm_Mm() >= 0)
			saida += "km_Mm='" + FormataValor.formataValorBT(edVolta.getKm_Mm(), 1) + "' ";
		if (edVolta.getMM() > 0)
			saida += "MM='" + FormataValor.formataValorBT(edVolta.getMM(), 1) + "' ";
		if (edVolta.getNr_Km_Vida_N0()> 0)
			saida += "nr_Km_Vida_N0='" + FormataValor.formataValorBT(edVolta.getNr_Km_Vida_N0(), 1) + "' ";
		saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
		saida += "/>";
		return saida;
    }

    public boolean checkDuploPneu ( PneuED ed, String acao) throws Excecoes {
    	boolean ret = false;
    	PneuED edChk = new PneuED();
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setNr_Fogo(ed.getNr_Fogo());
		edChk = this.getByRecordOL(edChk);
    	if ("I".equals(acao) && edChk.getOid_Pneu()>0)
    		ret = true;
    	else
    	if ("A".equals(acao) && edChk.getOid_Pneu()>0 ) {
			if (ed.getOid_Pneu()!=edChk.getOid_Pneu())
				ret = true ;
    	}
    	return ret;
    }

    /**
     * Situacao do pneu
     * Dado um pneu no ed retorna um array com duas posicoes:
     * na posicao zero letra que determina o stauts do pneu
     * 	E = Estoque
     * 	M = Montado na frota
     *  N = Pneu inexistente
     *  R = em Recapagem
     *  S = Sucateado
     *  V = Vendido
     *  Na posição um um texto explicativo para msg de erro.
     * @param ed
     * @return
     * @throws Excecoes
     */
    public ArrayList statusPneu ( PneuED ed ) throws Excecoes {

    	BancoUtil bu = new BancoUtil();
    	String status="", texto="";
    	PneuED edChk = new PneuED();
		edChk.setOid_Empresa(ed.getOid_Empresa());

		if ( bu.doValida(ed.getNr_Fogo()) ) {// Se foi informado o pneu ( as trocas podem ser feitas sem um pneu entrar no lugar do que saiu )
			edChk.setNr_Fogo(ed.getNr_Fogo());
			edChk.setOid_Pneu(ed.getOid_Pneu()); // Se o pneu ja foi valida na consulta ( onblur do campo nr_fogo ) entao ja tem o oid resta verificar situacao...
			edChk = this.getByRecordOL(edChk);
	    	if (edChk.getOid_Pneu()==0) {
	    		status="N";
	    		texto="Não existe pneu com este número";
	    	} else {
	    		// Pega o oid do pneu e a vida
	    		ed.setOid_Pneu(edChk.getOid_Pneu());
	    		ed.setNr_Vida(edChk.getNr_Vida());
	    		// Testa outras situacoes
	    		if (bu.doValida(edChk.getDt_Estoque())) {
	    			status="E";
	    			texto="Pneu no estoque " + edChk.getNm_Local_Estoque();
	    			ed.setOid_Local_Estoque(edChk.getOid_Local_Estoque()); // Guarda o oid do estoque para colocar no movimento
	    		}
	    		if (bu.doValida(edChk.getDt_Entrada())) {
	    			status="F";
	    			texto="Pneu está no veiculo " + edChk.getNR_Frota();
	    		}
	    		if (bu.doValida(edChk.getDt_Remessa_Recapagem())) {
	    			status="R";
	    			texto="Pneu está na recapagem " + edChk.getNm_Fornecedor_Recapagem();
	    		}
	    		if (bu.doValida(edChk.getDt_Sucateamento())) {
	    			status="S";
	    			texto="Pneu sucateado em " + edChk.getDt_Sucateamento();
	    		}
	    		if (bu.doValida(edChk.getDt_Venda())) {
	    			status="V";
	    			texto="Pneu vendido em " + edChk.getDt_Venda();
	    		}
	    	}
    	} else {
			status="E";
			texto="Não foi informado pneu para a troca"; // Somente comentario
    	}
    	ArrayList ret = new ArrayList();
		ret.add(status);
		ret.add(texto);
    	return ret;
    }

    /**
     * Situacao do pneu
     * Dado um pneu no ed retorna um array com duas posicoes:
     * na posicao zero letra que determina o stauts do pneu
     * 	E = Estoque
     * 	M = Montado na frota
     *  N = Pneu inexistente
     *  R = em Recapagem
     *  S = Sucateado
     *  V = Vendido
     *  Na posição um um texto explicativo para msg de erro.
     * @param ed
     * @return
     * @throws Excecoes
     */
    public ArrayList statusPneuJSTL( PneuED ed, String acao ) throws Excecoes {

    	BancoUtil bu = new BancoUtil();
    	String status="", texto="";
    	PneuED edChk = new PneuED();
		edChk.setOid_Empresa(ed.getOid_Empresa());

		if(!JavaUtil.doValida(acao)) {
			acao = "";
		}

		if ( bu.doValida(String.valueOf(ed.getOid_Pneu())) ) {// Se foi informado o pneu ( as trocas podem ser feitas sem um pneu entrar no lugar do que saiu )
//			edChk.setNr_Fogo(ed.getNr_Fogo());
			edChk.setOid_Pneu(ed.getOid_Pneu()); // Se o pneu ja foi valida na consulta ( onblur do campo nr_fogo ) entao ja tem o oid resta verificar situacao...
			edChk = this.getByRecordJSTL(edChk);
	    	if (edChk.getOid_Pneu()==0) {
	    		status="N";
	    		texto="Não existe pneu com este número";
	    	} else {
	    		// Pega o oid do pneu e a vida
	    		ed.setOid_Pneu(edChk.getOid_Pneu());
	    		ed.setNr_Vida(edChk.getNr_Vida());
	    		// Testa outras situacoes
	    		if (bu.doValida(edChk.getDt_Estoque())) {
	    			status="E";
	    			texto="Pneu no estoque " + edChk.getNm_Local_Estoque();
	    			ed.setOid_Local_Estoque(edChk.getOid_Local_Estoque()); // Guarda o oid do estoque para colocar no movimento
	    		}
	    		if (bu.doValida(edChk.getDt_Entrada()) && !acao.equals("TE")) {
	    			status="F";
	    			texto="Pneu está no veiculo " + edChk.getNR_Placa();
	    		}
	    		if (bu.doValida(edChk.getDt_Remessa_Recapagem())) {
	    			status="R";
	    			texto="Pneu está na recapagem " + edChk.getNm_Fornecedor_Recapagem();
	    		}
	    		if (bu.doValida(edChk.getDt_Sucateamento())) {
	    			status="S";
	    			texto="Pneu sucateado em " + edChk.getDt_Sucateamento();
	    		}
	    		if (bu.doValida(edChk.getDt_Venda())) {
	    			status="V";
	    			texto="Pneu vendido em " + edChk.getDt_Venda();
	    		}

	    	}
    	} else {
			status="N";
			texto="Não foi informado pneu para a troca"; // Somente comentario
    	}
    	ArrayList ret = new ArrayList();
		ret.add(status);
		ret.add(texto);
    	return ret;
    }

    // Busca o eixo da posicao
    private String getEixo(String pos, String oid_Veiculo) throws Excecoes {
    	String eixo="";
    	try {
    		VeiculoED edQBR = new VeiculoED();
    		edQBR.setOid_Veiculo(oid_Veiculo);
    		VeiculoRN veiRN = new VeiculoRN();
    		eixo = veiRN.getEixo(pos, edQBR);
		} catch (Excecoes e) {
			abortaTransacao();
			throw e;
		}
    	return eixo;
    }
    // Busca a km acumulada do veiculo
    private String getKmAcumulada(PneuED ed) throws Excecoes {
    	HodometroED odoED = new HodometroED();
    	String dm_Retorno=null;
    	try {
    		this.inicioTransacao();
			odoED.setOid_Veiculo(ed.getOid_Veiculo());
			odoED.setOid_Empresa(ed.getOid_Empresa());
			odoED.setDt_Odometro_Troca(ed.getDt_Entrada());
			odoED.setNr_Odometro_Colocado(ed.getNr_Hodometro_Veiculo());
			odoED.setDm_Virada(ed.getDm_Virada());
			odoED = new HodometroBD(this.sql).getKmAcumulada(odoED);
			ed.setNr_Km_Acumulada_Veiculo(odoED.getNr_Km_Acum_Troca());
			ed.setDm_Virada(odoED.getDm_Virada());
			dm_Retorno=odoED.getDm_Retorno();
			this.fimTransacao(true);
		} catch (Excecoes e) {
			this.abortaTransacao();
			throw e;
		}
		return dm_Retorno;
    }
    // Busca a medicao dos mm do pneu
    private double getMedicao (PneuED ed) throws Excecoes {
    	Medicao_PneuED mdpED = new Medicao_PneuED();
    	try {
			mdpED.setOid_Pneu(ed.getOid_Pneu());
			mdpED.setNr_Vida(ed.getNr_Vida());
			Medicao_PneuRN mdpRN = new Medicao_PneuRN();
			mdpED = mdpRN.getMedicao(mdpED);
		} catch (Excecoes e) {
			abortaTransacao();
			throw e;
		}
    	return mdpED.getNr_Mm_Sulco();
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
	}

}