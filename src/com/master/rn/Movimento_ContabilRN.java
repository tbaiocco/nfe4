package com.master.rn;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.ContaBD;
import com.master.bd.EmpresaBD;
import com.master.bd.Movimento_ContabilBD;
import com.master.bd.Nota_ContabilBD;
import com.master.bd.Parametro_ContaBD;
import com.master.bd.PessoaBD;
import com.master.bd.Saldo_ContaBD;
import com.master.bd.UnidadeBD;
import com.master.ed.Centro_CustoED;
import com.master.ed.ContaED;
import com.master.ed.EmpresaED;
import com.master.ed.HistoricoED;
import com.master.ed.Movimento_ContabilED;
import com.master.ed.Nota_ContabilED;
import com.master.ed.Parametro_ContaED;
import com.master.ed.PessoaED;
import com.master.ed.Saldo_ContaED;
import com.master.ed.UnidadeED;
import com.master.rl.JasperRL;
import com.master.root.CidadeBean;
import com.master.root.PessoaBean;
import com.master.root.UnidadeBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FileUtil;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.ManipulaArquivo;
import com.master.util.ManipulaString;
import com.master.util.Utilitaria;
import com.master.util.Valor;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;


/**
 * @author Régis Steigleder
 * @serial Movimentos Contabeis
 * @serialData 14/10/2005
 */
public class Movimento_ContabilRN extends Transacao {

    public Movimento_ContabilRN() {
    }

    public Movimento_ContabilED inclui(Movimento_ContabilED ed)  throws Excecoes {
        try {
        	this.inicioTransacao();
            ed = new Movimento_ContabilBD(this.sql).inclui(ed);
            return ed;
        }
        catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
        catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(Movimento_ContabilED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Movimento_ContabilBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Movimento_ContabilED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            new Movimento_ContabilBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        }
        catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
        catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Movimento_ContabilED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            ArrayList lista = new Movimento_ContabilBD(sql).lista(ed);
            return lista;
        }
        finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList listaSPED(Movimento_ContabilED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            ArrayList lista = new Movimento_ContabilBD(sql).listaSPED(ed);
            return lista;
        }
        finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Movimento_ContabilED ed, HttpServletRequest request, String nmObj) throws Excecoes {
        try {
            this.inicioTransacao();
            ArrayList lista = new Movimento_ContabilBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        }
        finally {
            this.fimTransacao(false);
        }
    }

    public void listaInconsistentes(Movimento_ContabilED ed, HttpServletRequest request, String nmObj) throws Excecoes {
        try {
            this.inicioTransacao();
            ArrayList lista = new Movimento_ContabilBD(sql).listaInconsistentes(ed);
            request.setAttribute(nmObj, lista);
        }
        finally {
            this.fimTransacao(false);
        }
    }

    public Movimento_ContabilED getByRecord(Movimento_ContabilED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            return new Movimento_ContabilBD(this.sql).getByRecord(ed);
        }
        finally {
            this.fimTransacao(false);
        }
    }

	public void getByRecord(Movimento_ContabilED ed, HttpServletRequest request, String nmObj) throws Excecoes {
		try {
			this.inicioTransacao();
			Movimento_ContabilED edQBR = new Movimento_ContabilBD(this.sql).getByRecord(ed);
			request.setAttribute(nmObj, edQBR.getOid_Movimento_Contabil()== 0 ? null : edQBR);
		}
		finally {
			this.fimTransacao(false);
		}
	}

	public Movimento_ContabilED getSaldoConta(Movimento_ContabilED ed) throws Excecoes {
		try {
			this.inicioTransacao();
			return new Movimento_ContabilBD(this.sql).getSaldoConta(ed);
			}
		finally {
			this.fimTransacao(false);
		}
	}
	public Movimento_ContabilED getSomaConta(Movimento_ContabilED ed) throws Excecoes {
		try {
			this.inicioTransacao();
			return new Movimento_ContabilBD(this.sql).getSomaConta(ed);
			}
		finally {
			this.fimTransacao(false);
		}
	}
	public Movimento_ContabilED getDadosSaldoSPEDold(Movimento_ContabilED ed) throws Excecoes {
		try {
			this.inicioTransacao();
			return new Movimento_ContabilBD(this.sql).getDadosSaldoSPED(ed);
			}
		finally {
			this.fimTransacao(false);
		}
	}
	public Saldo_ContaED getDadosSaldoSPED(Movimento_ContabilED ed) throws Excecoes {
		try {
			this.inicioTransacao();
			return new Saldo_ContaBD(this.sql).getDadosSaldoSPED(ed);
			}
		finally {
			this.fimTransacao(false);
		}
	}
	/**- Razão analítico
	 *   Busca o razão analítico para as contas parametrisadas no ed.
	 *   Pode ter saida em relatório Jasper ou na tela JSP.
	 * @param ed
	 * @param request
	 * @param response
	 * @param tipo
	 * 			T = Tela -> grava na request a lista
	 * 			R = Relatorio Jasper -> chama relatório jasper
	 * @throws Excecoes
	 * Dá o retorno do sucesso da consulta na variável de request retorno.
	 *
	 */
	public void razaoAnalitico(Movimento_ContabilED ed,HttpServletRequest request, HttpServletResponse response, String tipo) throws Excecoes {
		ArrayList list = new ArrayList(); // Array para listar as analiticas contas do intervalo
		ArrayList listMovctbRel = new ArrayList(); // Array para enviar para o relatório
		boolean ctaContaTemMovimento;
		double saldoAtual = 0;
		try {
			this.inicioTransacao();
			// Busca a lista das contas do intervalo informado  (somente analíticas)
			ContaED edListaConta = new ContaED();
				edListaConta.setCd_Estrutural_Inicial(ed.getCd_Estrutural_Inicial());
				edListaConta.setCd_Estrutural_Final(ed.getCd_Estrutural_Final());
				edListaConta.setCd_Conta(ed.getCd_Conta());
				edListaConta.setDm_Tipo_Conta("A"); // Só contas analíticas
				edListaConta.setDM_Relatorio("3"); // Ordenado por codigo estrutural
			list = new ContaBD(this.sql).lista(edListaConta); // Chama o método que retorna a lista
			// Itera a lista das contas do intervalo
			for (int i=0;i<list.size();i++) {
				ContaED contaLst = (ContaED)list.get(i); // Pega a conta da lista
				// Monta parte do ed para o relatório com as informações básicas da conta
				Movimento_ContabilED edMovCtbRel = new Movimento_ContabilED();
				edMovCtbRel.setOid_Conta(contaLst.getOid_Conta().longValue());
				edMovCtbRel.setCd_Estrutural(contaLst.getCd_Estrutural());
				edMovCtbRel.setCd_Conta(contaLst.getCd_Conta());
				edMovCtbRel.setNm_Conta(contaLst.getNm_Conta());
				edMovCtbRel.setNr_Livro(ed.getNr_Livro());
				edMovCtbRel.setNr_Pagina(ed.getNr_Pagina());
				edMovCtbRel.setDt_Movimento_Inicial(ed.getDt_Movimento_Inicial());
				edMovCtbRel.setDt_Movimento_Final(ed.getDt_Movimento_Final());
				edMovCtbRel.setDt_Emissao(ed.getDt_Emissao());
				edMovCtbRel.setNm_Fantasia(ed.getNm_Fantasia());
				// Busca o saldo da conta
				Movimento_ContabilED edBuscaSaldo = new Movimento_ContabilED();
					edBuscaSaldo.setOid_Unidade(ed.getOid_Unidade()); // monta o oid da unidade no ed
					edBuscaSaldo.setOid_Conta(contaLst.getOid_Conta().longValue()); // Monta o oid da conta no ed
					edBuscaSaldo.setDt_Movimento_Inicial(ed.getDt_Movimento_Inicial()); // Monta a data inicial de busca no ed
				Movimento_ContabilED  edSaldo =  new Movimento_ContabilBD(this.sql).getSaldoConta(edBuscaSaldo); // Chama o método que retorna o saldo
				// Monta o saldo inicial do razao no ed

				if (edSaldo.getVl_Saldo() < 0){
					edMovCtbRel.setVl_Saldo(edSaldo.getVl_Saldo() * -1);
				}else{
					edMovCtbRel.setVl_Saldo(edSaldo.getVl_Saldo());
				}

				saldoAtual = edSaldo.getVl_Saldo() ;
				// Busca o movimento de lancamentos para o periodo informado com o ed da tela de filtro ctbF011.jsp
				ArrayList listMov = new ArrayList();
				ed.setOid_Conta(contaLst.getOid_Conta().longValue()); // Monta o oid da conta no ed do filtro
				listMov = new Movimento_ContabilBD(this.sql).lista(ed);
				ctaContaTemMovimento = false;
				for (int ii=0;ii<listMov.size();ii++) {
					ctaContaTemMovimento = true;
					Movimento_ContabilED movctbLst = (Movimento_ContabilED)listMov.get(ii); // Pega a conta da lista
					Movimento_ContabilED edMovCtbReli = new Movimento_ContabilED(); // Instancia um bean pra montagem da linha de detalhe
					// Repete aparte lá de cima neste bean
					edMovCtbReli.setOid_Conta(edMovCtbRel.getOid_Conta());
					edMovCtbReli.setCd_Estrutural(edMovCtbRel.getCd_Estrutural());
					edMovCtbReli.setCd_Conta(edMovCtbRel.getCd_Conta());
					edMovCtbReli.setNm_Conta(edMovCtbRel.getNm_Conta());
					edMovCtbReli.setVl_Saldo(edMovCtbRel.getVl_Saldo());
					edMovCtbReli.setNr_Livro(ed.getNr_Livro());
					edMovCtbReli.setNr_Pagina(ed.getNr_Pagina());
					edMovCtbReli.setDt_Movimento_Inicial(ed.getDt_Movimento_Inicial());
					edMovCtbReli.setDt_Movimento_Final(ed.getDt_Movimento_Final());
					edMovCtbReli.setDt_Emissao(ed.getDt_Emissao());
					edMovCtbReli.setNm_Fantasia(ed.getNm_Fantasia());
					// até aqui...
					edMovCtbReli.setDt_Movimento(movctbLst.getDt_Movimento());
					edMovCtbReli.setCd_Conta_Contrapartida(movctbLst.getCd_Conta());
					edMovCtbReli.setNm_Centro_Custo(movctbLst.getNm_Centro_Custo());
					edMovCtbReli.setTx_Historico(movctbLst.getTx_Historico());
					edMovCtbReli.setTx_Historico_Complementar(movctbLst.getTx_Historico_Complementar());
					edMovCtbReli.setVl_Debito("D".equals(movctbLst.getDm_Debito_Credito())  ? movctbLst.getVl_Lancamento():0);
					edMovCtbReli.setVl_Credito("C".equals(movctbLst.getDm_Debito_Credito()) ? movctbLst.getVl_Lancamento():0);
					saldoAtual = saldoAtual + ("C".equals(movctbLst.getDm_Debito_Credito()) ? -1 * movctbLst.getVl_Lancamento(): movctbLst.getVl_Lancamento()) ;
					edMovCtbReli.setVl_Saldo_Atual(saldoAtual);
					listMovctbRel.add(edMovCtbReli); // Dá o add na lista de eds dos detalhes
				}
				if ( ed.getOid_Centro_Custo() == 0 && ed.getOid_Conta() > 0){
				// Se a conta não teve movimento então coloca na lista o bean que foi instanciado lá encima
				if ( !ctaContaTemMovimento  ) {
					if ( edMovCtbRel.getVl_Saldo() != 0 ) { // Se tem saldo maior que 0 entra na lista
						listMovctbRel.add(edMovCtbRel);
					}
				}
				}
			}
			if (listMovctbRel.size() > 0 ) {
				if ( tipo == "R") {
					ed.setLista(listMovctbRel); // Joga alista de contas ( grupo ) na ed para enviar pro relatório
					ed.setResponse(response);
					ed.setNomeRelatorio("Razao_Analitico"); // Seta o nome do relatório
					new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
				}else{
					request.setAttribute("lista",listMovctbRel);
				}
				request.setAttribute("retorno",new String("T"));
			} else {
				request.setAttribute("retorno",new String("F"));
			}
		}
		finally {
			this.fimTransacao(false);
		}
	}
	//-------- Diário Geral
	public void diarioGeral(Movimento_ContabilED ed,HttpServletRequest request, HttpServletResponse response) throws Excecoes {
		ArrayList listMovctbRel = new ArrayList(); // Array para enviar para o relatório
		try {
			this.inicioTransacao();
			// Busca o movimento de lancamentos para o periodo informado com o ed da tela de filtro ctbF011.jsp
			ArrayList listMov = new ArrayList();
			listMov = new Movimento_ContabilBD(this.sql).lista(ed);
			for (int ii=0;ii<listMov.size();ii++) { //Itera a lista para montar nova lista com o bean do relatório
				Movimento_ContabilED movctbLst = (Movimento_ContabilED)listMov.get(ii); // Pega o movimento da lista
				Movimento_ContabilED edMovCtbReli = new Movimento_ContabilED(); // Instancia um bean pra montagem da linha de detalhe
				// Monta a parte fixa que veio do bean da tela de filtro
				edMovCtbReli.setNr_Livro(ed.getNr_Livro());
				edMovCtbReli.setNr_Pagina(ed.getNr_Pagina());
				edMovCtbReli.setDt_Movimento_Inicial(ed.getDt_Movimento_Inicial());
				edMovCtbReli.setDt_Movimento_Final(ed.getDt_Movimento_Final());
				edMovCtbReli.setDt_Emissao(ed.getDt_Emissao());
				edMovCtbReli.setNm_Fantasia(ed.getNm_Fantasia());
				// até aqui... Monta a parte variável vinda do movimento.
				edMovCtbReli.setCd_Conta(movctbLst.getCd_Conta());
				edMovCtbReli.setNm_Conta(movctbLst.getNm_Conta());
				edMovCtbReli.setDt_Movimento(movctbLst.getDt_Movimento());
				edMovCtbReli.setTx_Historico(movctbLst.getTx_Historico());
				edMovCtbReli.setTx_Historico_Complementar(movctbLst.getTx_Historico_Complementar());
				edMovCtbReli.setVl_Debito("D".equals(movctbLst.getDm_Debito_Credito())  ? movctbLst.getVl_Lancamento():0);
				edMovCtbReli.setVl_Credito("C".equals(movctbLst.getDm_Debito_Credito()) ? movctbLst.getVl_Lancamento():0);
				listMovctbRel.add(edMovCtbReli); // Dá o add na lista de eds dos detalhes
			}
			if (listMovctbRel.size() > 0 ) {
				ed.setLista(listMovctbRel); // Joga alista de movimentos no ed para enviar pro relatório
				ed.setResponse(response);
				ed.setNomeRelatorio("Diario_Geral"); // Seta o nome do relatório
				new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
				request.setAttribute("retorno",new String("T"));
			} else {
				request.setAttribute("retorno",new String("F"));
			}
		}
		finally {
			this.fimTransacao(false);
		}
	}

	//-------- Balancete de verificacao
	public void balanceteVerificacao(Movimento_ContabilED ed,HttpServletRequest request, HttpServletResponse response, String destino) throws Excecoes {
		ArrayList list = new ArrayList(); // Array para listar as contas
		ArrayList listMovctbRel = new ArrayList(); // Array para enviar para o relatório
		try {
			this.inicioTransacao();
			// Busca a lista das contas
			ContaED edListaConta = new ContaED();
				edListaConta.setDM_Relatorio("3"); // Ordenado por codigo estrutural
			list = new ContaBD(this.sql).lista(edListaConta); // Chama o método que retorna a lista
			// Itera a lista das contas ordenadas pelo código estrutural.
			for (int i=0;i<list.size();i++) {
				ContaED contaLst = (ContaED)list.get(i); // Pega a conta da lista
				// Monta parte do ed para o relatório com as informações básicas da conta
				Movimento_ContabilED edMovCtbRel = new Movimento_ContabilED();
				edMovCtbRel.setOid_Conta(contaLst.getOid_Conta().longValue());
				edMovCtbRel.setCd_Estrutural(contaLst.getCd_Estrutural());
				edMovCtbRel.setCd_Conta(contaLst.getCd_Conta());
				edMovCtbRel.setNm_Conta(JavaUtil.LFill(contaLst.getNm_Conta(), contaLst.getNm_Conta().length() + (contaLst.getNr_Grau().intValue() * 2), ' ', false));
				edMovCtbRel.setNr_Livro(ed.getNr_Livro());
				edMovCtbRel.setNr_Pagina(ed.getNr_Pagina());
				edMovCtbRel.setDt_Movimento_Inicial(ed.getDt_Movimento_Inicial());
				edMovCtbRel.setDt_Movimento_Final(ed.getDt_Movimento_Final());
				edMovCtbRel.setDt_Emissao(ed.getDt_Emissao());
				edMovCtbRel.setNm_Fantasia(ed.getNm_Fantasia());
				edMovCtbRel.setDm_Tipo_Conta(contaLst.getDm_Tipo_Conta());
				edMovCtbRel.setNr_Grau(contaLst.getNr_Grau());
				if ("S".equals(contaLst.getDm_Tipo_Conta())) {
					//Conta sintetica inicia com saldo e debito e credito zerados
					edMovCtbRel.setVl_Saldo(0);
					edMovCtbRel.setVl_Debito(0);
					edMovCtbRel.setVl_Credito(0);
					edMovCtbRel.setVl_Saldo_Atual(0);
				}else{
					// Busca o saldo da conta e a soma do débito e crédito no período
					Movimento_ContabilED edBuscaSaldo = new Movimento_ContabilED();
						edBuscaSaldo.setOid_Unidade(ed.getOid_Unidade()); // monta o oid da unidade no ed
						edBuscaSaldo.setOid_Conta(contaLst.getOid_Conta().longValue()); // Monta o oid da conta no ed
						edBuscaSaldo.setDt_Movimento_Inicial(ed.getDt_Movimento_Inicial()); // Monta a data inicial de busca no ed
						edBuscaSaldo.setDt_Movimento_Final(ed.getDt_Movimento_Final()); // Monta a data final de busca no ed
						edBuscaSaldo.setDm_Lista_Encerramento(ed.getDm_Lista_Encerramento());
					Movimento_ContabilED  edSaldo =  new Movimento_ContabilBD(this.sql).getSaldoConta(edBuscaSaldo); // Chama o método que retorna o saldo
					// Monta o saldo inicial do balancete no ed
					edMovCtbRel.setVl_Saldo(edSaldo.getVl_Saldo());
					// Busca a soma dos débitos e créditos dos lancamentos para o periodo informado com o ed da tela de filtro ctbF011.jsp
					Movimento_ContabilED  edSoma =  new Movimento_ContabilBD(this.sql).getSomaConta(edBuscaSaldo); // Chama o método que retorna o saldo
					edMovCtbRel.setVl_Debito(edSoma.getVl_Debito());
					edMovCtbRel.setVl_Credito(edSoma.getVl_Credito());
					edMovCtbRel.setVl_Saldo_Atual(0);
				}
				listMovctbRel.add(edMovCtbRel); // Dá o add na lista de eds dos detalhes
			}
			// Varre o array para somar nas contas mãe
			for (int i=0;i<listMovctbRel.size();i++) {
				Movimento_ContabilED movLst = (Movimento_ContabilED)listMovctbRel.get(i);
				if ("A".equals(movLst.getDm_Tipo_Conta())){
					somaParaCima(listMovctbRel, movLst.getCd_Estrutural(), movLst.getVl_Saldo(), movLst.getVl_Debito(), movLst.getVl_Credito());
				}
			}
			// Cria as linhas do resumo de sinteticas
			int listMovctbRelSize = listMovctbRel.size();
			for (int i=0;i<listMovctbRelSize;i++) {
				Movimento_ContabilED movLst = (Movimento_ContabilED)listMovctbRel.get(i);
				if ("S".equals(movLst.getDm_Tipo_Conta())) {
					Movimento_ContabilED edMovCtbRel = new Movimento_ContabilED();
					edMovCtbRel.setOid_Conta(movLst.getOid_Conta());
					edMovCtbRel.setCd_Estrutural("X");
					edMovCtbRel.setCd_Conta(movLst.getCd_Estrutural());
					edMovCtbRel.setNm_Conta(movLst.getNm_Conta());
					edMovCtbRel.setNr_Livro(ed.getNr_Livro());
					edMovCtbRel.setNr_Pagina(ed.getNr_Pagina());
					edMovCtbRel.setDt_Movimento_Inicial(ed.getDt_Movimento_Inicial());
					edMovCtbRel.setDt_Movimento_Final(ed.getDt_Movimento_Final());
					edMovCtbRel.setDt_Emissao(ed.getDt_Emissao());
					edMovCtbRel.setNm_Fantasia(ed.getNm_Fantasia());
					edMovCtbRel.setDm_Tipo_Conta(movLst.getDm_Tipo_Conta());
					edMovCtbRel.setNr_Grau(movLst.getNr_Grau());
					edMovCtbRel.setVl_Saldo(movLst.getVl_Saldo());
					edMovCtbRel.setVl_Debito(movLst.getVl_Debito());
					edMovCtbRel.setVl_Credito(movLst.getVl_Credito());
					listMovctbRel.add(edMovCtbRel);
				}
			}
			// Cria as linhas do resumo final
			double vl[] = new double[3];
			listMovctbRelSize = listMovctbRel.size();
			for (int i=0;i<listMovctbRelSize;i++) {
				Movimento_ContabilED movLst = (Movimento_ContabilED)listMovctbRel.get(i);
				if (movLst.getNr_Grau().intValue() == 0 && (!"X".equals(movLst.getCd_Estrutural())) ) {
					Movimento_ContabilED edMovCtbRel = new Movimento_ContabilED();
					edMovCtbRel.setOid_Conta(movLst.getOid_Conta());
					edMovCtbRel.setCd_Estrutural(" ");
					edMovCtbRel.setCd_Conta(movLst.getCd_Conta());
					edMovCtbRel.setNm_Conta(movLst.getNm_Conta());
					edMovCtbRel.setNr_Livro(ed.getNr_Livro());
					edMovCtbRel.setNr_Pagina(ed.getNr_Pagina());
					edMovCtbRel.setDt_Movimento_Inicial(ed.getDt_Movimento_Inicial());
					edMovCtbRel.setDt_Movimento_Final(ed.getDt_Movimento_Final());
					edMovCtbRel.setDt_Emissao(ed.getDt_Emissao());
					edMovCtbRel.setNm_Fantasia(ed.getNm_Fantasia());
					edMovCtbRel.setDm_Tipo_Conta(movLst.getDm_Tipo_Conta());
					edMovCtbRel.setNr_Grau(movLst.getNr_Grau());
					edMovCtbRel.setVl_Saldo(movLst.getVl_Saldo());
					edMovCtbRel.setVl_Debito(movLst.getVl_Debito());
					edMovCtbRel.setVl_Credito(movLst.getVl_Credito());
					listMovctbRel.add(edMovCtbRel);
					// Soma para o total geral
					vl[0] = Valor.round(vl[0] + movLst.getVl_Saldo(),2);
					vl[1] = Valor.round(vl[1] + movLst.getVl_Debito(),2);
					vl[2] = Valor.round(vl[2] + movLst.getVl_Credito(),2);
				}
			}
			//Cria a linha de totais
			Movimento_ContabilED edMovCtbRel = new Movimento_ContabilED();
			edMovCtbRel.setOid_Conta(0);
			edMovCtbRel.setCd_Estrutural(" ");
			edMovCtbRel.setCd_Conta(" ");
			edMovCtbRel.setNm_Conta("Total Geral:");
			edMovCtbRel.setNr_Livro(ed.getNr_Livro());
			edMovCtbRel.setNr_Pagina(ed.getNr_Pagina());
			edMovCtbRel.setDt_Movimento_Inicial(ed.getDt_Movimento_Inicial());
			edMovCtbRel.setDt_Movimento_Final(ed.getDt_Movimento_Final());
			edMovCtbRel.setDt_Emissao(ed.getDt_Emissao());
			edMovCtbRel.setNm_Fantasia(ed.getNm_Fantasia());
			edMovCtbRel.setDm_Tipo_Conta("S");
			edMovCtbRel.setNr_Grau(new Integer(0));
			edMovCtbRel.setVl_Saldo(vl[0]);
			edMovCtbRel.setVl_Debito(vl[1]);
			edMovCtbRel.setVl_Credito(vl[2]);
			listMovctbRel.add(edMovCtbRel);
			// Se tiver alguma item na lista, monta relatório senão avisa na tela
			if (listMovctbRel.size() > 0 ) {
				if ("A".equals(destino) ) {
					request.setAttribute("listaBalancete",listMovctbRel);
				} else {

		    		// Se for balanço geral busca as notas explicativas para gerar um sub-relatório no jasper.
					if(Utilitaria.doValida(ed.getNm_Cabecalho())){
		    			Nota_ContabilED edNota = new Nota_ContabilED();
		    			edNota.setDt_Inicial(ed.getDt_Movimento_Inicial());
		    			edNota.setDt_Final(ed.getDt_Movimento_Final());
		    			ArrayList lstNotas = new Nota_ContabilBD(sql).lista(edNota);
		    			edMovCtbRel = (Movimento_ContabilED)listMovctbRel.get(listMovctbRel.size()-1);
		    			edMovCtbRel.setSublista(lstNotas);
		    		}

					ed.setLista(listMovctbRel); // Joga alista de contas ( grupo ) na ed para enviar pro relatório
					ed.setResponse(response);
					if (Utilitaria.doValida(ed.getNm_Cabecalho())) {
						ed.setNomeRelatorio("Balanco"); // Seta o nome do relatório
					} else {
						ed.setNomeRelatorio("Balancete"); // Seta o nome do relatório}
					}
					HashMap map = new HashMap();
					Parametro_ContaED parED = new Parametro_ContaED();
					parED = new Parametro_ContaBD(this.sql).getByRecord(parED);
					// Busca a cidade da unidade - pega a unidade e depois a pessoa
					UnidadeED unED = new UnidadeED();
					unED.setOid_Unidade(ed.getOid_Unidade());
					unED = new UnidadeBD(this.sql).getByRecord(unED);
					PessoaED pesED = new PessoaED();
					pesED.setOid_Pessoa(unED.getOid_Pessoa());
					pesED = new PessoaBD(this.sql).getByRecord(pesED);

					map.put("PATH_SUBLIST", Parametro_FixoED.getInstancia().getPATH_RELATORIOS());
					map.put("local", pesED.getNM_Cidade().trim() +" - "+ pesED.getCD_Estado()+", " + ed.getDt_Emissao().substring(0, 2) + " de "+ Data.getMesCorrente(ed.getDt_Emissao())+ " de " + ed.getDt_Emissao().substring(6,10) + ".");
		    		map.put("assinatura1", Utilitaria.doValida(parED.getTx_Assinatura1())? parED.getTx_Assinatura1() : "");
		    		map.put("cargo1", Utilitaria.doValida(parED.getTx_Cargo1())? parED.getTx_Cargo1() : "");
		    		map.put("cpf1", Utilitaria.doValida(parED.getTx_Cic1())? parED.getTx_Cic1() : "");
		    		map.put("fone1",Utilitaria.doValida(parED.getTx_Fone1())? parED.getTx_Fone1() : "");
		    		map.put("assinatura2", Utilitaria.doValida(parED.getTx_Assinatura2())? parED.getTx_Assinatura2() : "");
		    		map.put("cargo2", Utilitaria.doValida(parED.getTx_Cargo2())? parED.getTx_Cargo2() : "");
		    		map.put("cpf2", Utilitaria.doValida(parED.getTx_Cic2())? parED.getTx_Cic2() : "");
		    		map.put("fone2", Utilitaria.doValida(parED.getTx_Fone2())? parED.getTx_Fone2() : "");
		    		map.put("cabecalho", Utilitaria.doValida(ed.getNm_Cabecalho())? "Balanço Geral" : "Balancete de Verificação");
		    		ed.setHashMap(map);

					new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
				}
				request.setAttribute("retorno",new String("T"));
			} else {
				request.setAttribute("retorno",new String("F"));
			}

		}
		finally {
			this.fimTransacao(false);
		}
	}

	public void balanceteVerificacao_old(Movimento_ContabilED ed,HttpServletRequest request, HttpServletResponse response, String destino) throws Excecoes {
		ArrayList list = new ArrayList(); // Array para listar as contas
		ArrayList listMovctbRel = new ArrayList(); // Array para enviar para o relatório
		try {
			this.inicioTransacao();
			// Busca a lista das contas
			ContaED edListaConta = new ContaED();
				edListaConta.setDM_Relatorio("3"); // Ordenado por codigo estrutural
			list = new ContaBD(this.sql).lista(edListaConta); // Chama o método que retorna a lista
			// Itera a lista das contas ordenadas pelo código estrutural.
			for (int i=0;i<list.size();i++) {
				ContaED contaLst = (ContaED)list.get(i); // Pega a conta da lista
				// Monta parte do ed para o relatório com as informações básicas da conta
				Movimento_ContabilED edMovCtbRel = new Movimento_ContabilED();
				edMovCtbRel.setOid_Conta(contaLst.getOid_Conta().longValue());
				edMovCtbRel.setCd_Estrutural(contaLst.getCd_Estrutural());
				edMovCtbRel.setCd_Conta(contaLst.getCd_Conta());
				edMovCtbRel.setNm_Conta(JavaUtil.LFill(contaLst.getNm_Conta(), contaLst.getNm_Conta().length() + (contaLst.getNr_Grau().intValue() * 2), ' ', false));
				edMovCtbRel.setNr_Livro(ed.getNr_Livro());
				edMovCtbRel.setNr_Pagina(ed.getNr_Pagina());
				edMovCtbRel.setDt_Movimento_Inicial(ed.getDt_Movimento_Inicial());
				edMovCtbRel.setDt_Movimento_Final(ed.getDt_Movimento_Final());
				edMovCtbRel.setDt_Emissao(ed.getDt_Emissao());
				edMovCtbRel.setNm_Fantasia(ed.getNm_Fantasia());
				edMovCtbRel.setDm_Tipo_Conta(contaLst.getDm_Tipo_Conta());
				edMovCtbRel.setNr_Grau(contaLst.getNr_Grau());
				if ("S".equals(contaLst.getDm_Tipo_Conta())) {
					//Conta sintetica inicia com saldo e debito e credito zerados
					edMovCtbRel.setVl_Saldo(0);
					edMovCtbRel.setVl_Debito(0);
					edMovCtbRel.setVl_Credito(0);
					edMovCtbRel.setVl_Saldo_Atual(0);
				}else{
					// Busca o saldo da conta e a soma do débito e crédito no período
					Movimento_ContabilED edBuscaSaldo = new Movimento_ContabilED();
						edBuscaSaldo.setOid_Unidade(ed.getOid_Unidade()); // monta o oid da unidade no ed
						edBuscaSaldo.setOid_Conta(contaLst.getOid_Conta().longValue()); // Monta o oid da conta no ed
						edBuscaSaldo.setDt_Movimento_Inicial(ed.getDt_Movimento_Inicial()); // Monta a data inicial de busca no ed
						edBuscaSaldo.setDt_Movimento_Final(ed.getDt_Movimento_Final()); // Monta a data final de busca no ed
					Movimento_ContabilED  edSaldo =  new Movimento_ContabilBD(this.sql).getSaldoConta(edBuscaSaldo); // Chama o método que retorna o saldo
					// Monta o saldo inicial do balancete no ed
					edMovCtbRel.setVl_Saldo(edSaldo.getVl_Saldo());
					// Busca a soma dos débitos e créditos dos lancamentos para o periodo informado com o ed da tela de filtro ctbF011.jsp
					Movimento_ContabilED  edSoma =  new Movimento_ContabilBD(this.sql).getSomaConta(edBuscaSaldo); // Chama o método que retorna o saldo
					edMovCtbRel.setVl_Debito(edSoma.getVl_Debito());
					edMovCtbRel.setVl_Credito(edSoma.getVl_Credito());
					edMovCtbRel.setVl_Saldo_Atual(0);
				}
				listMovctbRel.add(edMovCtbRel); // Dá o add na lista de eds dos detalhes
			}
			// Varre o array para somar nas contas mãe
			for (int i=0;i<listMovctbRel.size();i++) {
				Movimento_ContabilED movLst = (Movimento_ContabilED)listMovctbRel.get(i);
				if ("A".equals(movLst.getDm_Tipo_Conta())){
					somaParaCima(listMovctbRel, movLst.getCd_Estrutural(), movLst.getVl_Saldo(), movLst.getVl_Debito(), movLst.getVl_Credito());
				}
			}
			// Cria as linhas do resumo de sinteticas
			int listMovctbRelSize = listMovctbRel.size();
			for (int i=0;i<listMovctbRelSize;i++) {
				Movimento_ContabilED movLst = (Movimento_ContabilED)listMovctbRel.get(i);
				if ("S".equals(movLst.getDm_Tipo_Conta())) {
					Movimento_ContabilED edMovCtbRel = new Movimento_ContabilED();
					edMovCtbRel.setOid_Conta(movLst.getOid_Conta());
					edMovCtbRel.setCd_Estrutural("X");
					edMovCtbRel.setCd_Conta(movLst.getCd_Estrutural());
					edMovCtbRel.setNm_Conta(movLst.getNm_Conta());
					edMovCtbRel.setNr_Livro(ed.getNr_Livro());
					edMovCtbRel.setNr_Pagina(ed.getNr_Pagina());
					edMovCtbRel.setDt_Movimento_Inicial(ed.getDt_Movimento_Inicial());
					edMovCtbRel.setDt_Movimento_Final(ed.getDt_Movimento_Final());
					edMovCtbRel.setDt_Emissao(ed.getDt_Emissao());
					edMovCtbRel.setNm_Fantasia(ed.getNm_Fantasia());
					edMovCtbRel.setDm_Tipo_Conta(movLst.getDm_Tipo_Conta());
					edMovCtbRel.setNr_Grau(movLst.getNr_Grau());
					edMovCtbRel.setVl_Saldo(movLst.getVl_Saldo());
					edMovCtbRel.setVl_Debito(movLst.getVl_Debito());
					edMovCtbRel.setVl_Credito(movLst.getVl_Credito());
					listMovctbRel.add(edMovCtbRel);
				}
			}
			// Cria as linhas do resumo final
			double vl[] = new double[3];
			listMovctbRelSize = listMovctbRel.size();
			for (int i=0;i<listMovctbRelSize;i++) {
				Movimento_ContabilED movLst = (Movimento_ContabilED)listMovctbRel.get(i);
				if (movLst.getNr_Grau().intValue() == 0 && (!"X".equals(movLst.getCd_Estrutural())) ) {
					Movimento_ContabilED edMovCtbRel = new Movimento_ContabilED();
					edMovCtbRel.setOid_Conta(movLst.getOid_Conta());
					edMovCtbRel.setCd_Estrutural(" ");
					edMovCtbRel.setCd_Conta(movLst.getCd_Conta());
					edMovCtbRel.setNm_Conta(movLst.getNm_Conta());
					edMovCtbRel.setNr_Livro(ed.getNr_Livro());
					edMovCtbRel.setNr_Pagina(ed.getNr_Pagina());
					edMovCtbRel.setDt_Movimento_Inicial(ed.getDt_Movimento_Inicial());
					edMovCtbRel.setDt_Movimento_Final(ed.getDt_Movimento_Final());
					edMovCtbRel.setDt_Emissao(ed.getDt_Emissao());
					edMovCtbRel.setNm_Fantasia(ed.getNm_Fantasia());
					edMovCtbRel.setDm_Tipo_Conta(movLst.getDm_Tipo_Conta());
					edMovCtbRel.setNr_Grau(movLst.getNr_Grau());
					edMovCtbRel.setVl_Saldo(movLst.getVl_Saldo());
					edMovCtbRel.setVl_Debito(movLst.getVl_Debito());
					edMovCtbRel.setVl_Credito(movLst.getVl_Credito());
					listMovctbRel.add(edMovCtbRel);
					// Soma para o total geral
					vl[0] = Valor.round(vl[0] + movLst.getVl_Saldo(),2);
					vl[1] = Valor.round(vl[1] + movLst.getVl_Debito(),2);
					vl[2] = Valor.round(vl[2] + movLst.getVl_Credito(),2);
				}
			}
			//Cria a linha de totais
			Movimento_ContabilED edMovCtbRel = new Movimento_ContabilED();
			edMovCtbRel.setOid_Conta(0);
			edMovCtbRel.setCd_Estrutural(" ");
			edMovCtbRel.setCd_Conta(" ");
			edMovCtbRel.setNm_Conta("Total Geral:");
			edMovCtbRel.setNr_Livro(ed.getNr_Livro());
			edMovCtbRel.setNr_Pagina(ed.getNr_Pagina());
			edMovCtbRel.setDt_Movimento_Inicial(ed.getDt_Movimento_Inicial());
			edMovCtbRel.setDt_Movimento_Final(ed.getDt_Movimento_Final());
			edMovCtbRel.setDt_Emissao(ed.getDt_Emissao());
			edMovCtbRel.setNm_Fantasia(ed.getNm_Fantasia());
			edMovCtbRel.setDm_Tipo_Conta("S");
			edMovCtbRel.setNr_Grau(new Integer(0));
			edMovCtbRel.setVl_Saldo(vl[0]);
			edMovCtbRel.setVl_Debito(vl[1]);
			edMovCtbRel.setVl_Credito(vl[2]);
			listMovctbRel.add(edMovCtbRel);
			// Se tiver alguma item na lista, monta relatório senão avisa na tela
			if (listMovctbRel.size() > 0 ) {
				if ("A".equals(destino) ) {
					request.setAttribute("listaBalancete",listMovctbRel);
				} else {
					ed.setLista(listMovctbRel); // Joga alista de contas ( grupo ) na ed para enviar pro relatório
					ed.setResponse(response);
					ed.setNomeRelatorio("Balancete"); // Seta o nome do relatório
					new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
				}
				request.setAttribute("retorno",new String("T"));
			} else {
				request.setAttribute("retorno",new String("F"));
			}
		}
		finally {
			this.fimTransacao(false);
		}
	}

	private void somaParaCima(ArrayList l,String ce, double vls, double vld, double vlc){
		ContaED edConta = new ContaED();
		edConta.setCd_Estrutural(ce);
		String cdPai =  edConta.pegaGrauInferior();
		if (! "*".equals(cdPai) ) {
			for ( int i=0;i<l.size();i++){
				Movimento_ContabilED movLst = (Movimento_ContabilED)l.get(i);
				if (cdPai.equals(movLst.getCd_Estrutural())) {
					movLst.setVl_Saldo(Valor.round(movLst.getVl_Saldo() + vls,2));
					movLst.setVl_Debito(Valor.round(movLst.getVl_Debito() + vld,2));
					movLst.setVl_Credito(Valor.round(movLst.getVl_Credito() + vlc,2));
					l.set(i,movLst);
					break;
				}
			}
			this.somaParaCima( l, cdPai,  vls, vld, vlc);
		}
	}
	// -------- Fim do balancete de verificacao

	public void geraSaldo(long oid_uni, String dt_ini, String dt_fin) throws Excecoes {
		try {
			ContaED busca = new ContaED();
			busca.setDM_Relatorio("3");
			ArrayList listaCtas = new ContaRN().listaToSaldo(busca);
			Iterator ctas = listaCtas.iterator();
//			System.out.println("gerando saldos..."+dt_ini);
			while(ctas.hasNext()){
				ContaED cta = (ContaED)ctas.next();
				if(JavaUtil.getValueDef(cta.getDm_Tipo_Conta(),"S").equals("A")){
					Movimento_ContabilED buscaMov = new Movimento_ContabilED();
					buscaMov.setOid_Unidade(oid_uni);
					buscaMov.setOid_Conta(cta.getOid_Conta().longValue());
					buscaMov.setDt_Movimento_Inicial(dt_ini);
					buscaMov.setDt_Movimento_Final(dt_fin);
					double sld_ini = this.getSaldoConta(buscaMov).getVl_Saldo();
					Movimento_ContabilED valores = this.getDadosSaldoSPEDold(buscaMov);
					double sld_fin = sld_ini + valores.getVl_Saldo();

					Saldo_ContaED sld = new Saldo_ContaED();
					sld.setOid_conta(cta.getOid_Conta().longValue());
					sld.setDt_referencia(dt_ini);
					sld.setVl_saldo_inicial(sld_ini);
					sld.setVl_debito(valores.getVl_Debito());
					sld.setVl_credito(valores.getVl_Credito());
					sld.setVl_saldo_final(sld_fin);
					if(sld_ini!=0 || sld_fin!=0 || valores.getVl_Debito()!=0 || valores.getVl_Credito()!=0){
						this.inicioTransacao();
			            new Saldo_ContaBD(this.sql).inclui(sld);
			            this.fimTransacao(true);
					}
//					break;
				}
			}

		} catch (Exception e){
			e.printStackTrace();
			this.abortaTransacao();
            throw new Excecoes(e.getMessage());
		}

	}

	public void geraSaldoAno(long oid_uni) throws Excecoes {
		try {
			for(int i=12; i<=12;i++){
				String dti = "01/"+ManipulaString.alinhaNumeroDireita(String.valueOf(i), 2)+"/2009";
//				String aux = "01/"+ManipulaString.alinhaNumeroDireita(String.valueOf(i+1), 2)+"/2009";
//				if(i==12)
//					aux = "01/01/2010";
				String dtf = Data.getUltimoDiaDoMes(dti);
//				System.out.println(dti+"|"+dtf);
				this.geraSaldo(oid_uni, dti, dtf);
			}
		} catch(Exception e){
			e.printStackTrace();
            throw new Excecoes(e.getMessage());
		}

	}

	public void geraSaldoContaAno(long oid_uni, long oid_conta) throws Excecoes {
		try {
			for(int i=6; i<=12;i++){
				String dti = "01/"+ManipulaString.alinhaNumeroDireita(String.valueOf(i), 2)+"/2009";
//				String aux = "01/"+ManipulaString.alinhaNumeroDireita(String.valueOf(i+1), 2)+"/2009";
//				if(i==12)
//					aux = "01/01/2010";
				String dtf = Data.getUltimoDiaDoMes(dti);

				oid_conta = 412;

				ContaED busca = new ContaED();
				busca.setOid_Conta(new Integer(String.valueOf(oid_conta)));
				ArrayList listaCtas = new ContaRN().listaToSaldo(busca);
				Iterator ctas = listaCtas.iterator();
//				System.out.println("gerando saldos..."+oid_conta+"|"+dti);
				while(ctas.hasNext()){
					ContaED cta = (ContaED)ctas.next();
					if(JavaUtil.getValueDef(cta.getDm_Tipo_Conta(),"S").equals("A")){
						Movimento_ContabilED buscaMov = new Movimento_ContabilED();
						buscaMov.setOid_Unidade(oid_uni);
						buscaMov.setOid_Conta(cta.getOid_Conta().longValue());
						buscaMov.setDt_Movimento_Inicial(dti);
						buscaMov.setDt_Movimento_Final(dtf);
						double sld_ini = this.getSaldoConta(buscaMov).getVl_Saldo();
						Movimento_ContabilED valores = this.getDadosSaldoSPEDold(buscaMov);
						double sld_fin = sld_ini + valores.getVl_Saldo();

						Saldo_ContaED sld = new Saldo_ContaED();
						sld.setOid_conta(cta.getOid_Conta().longValue());
						sld.setDt_referencia(dti);
						sld.setVl_saldo_inicial(sld_ini);
						sld.setVl_debito(valores.getVl_Debito());
						sld.setVl_credito(valores.getVl_Credito());
						sld.setVl_saldo_final(sld_fin);
						if(sld_ini!=0 || sld_fin!=0 || valores.getVl_Debito()!=0 || valores.getVl_Credito()!=0){
							this.inicioTransacao();
				            new Saldo_ContaBD(this.sql).inclui(sld);
				            this.fimTransacao(true);
						}
//						break;
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
            throw new Excecoes(e.getMessage());
		}

	}

	/**
    public void deletaDuplo() throws Excecoes {
        try {
            this.inicioTransacao();
            new Movimento_ContabilBD(this.sql).deletaDuplo();
            this.fimTransacao(true);
        }
        catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        }
        catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
	 **/


	protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}