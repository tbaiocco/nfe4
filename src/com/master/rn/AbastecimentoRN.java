package com.master.rn;

/**
 * @author Régis
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.AbastecimentoBD;
import com.master.bd.HodometroBD;
import com.master.bd.Movimento_Ordem_ServicoBD;
import com.master.ed.AbastecimentoED;
import com.master.ed.HodometroED;
import com.master.ed.Movimento_Ordem_ServicoED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.bd.Transacao;
import com.master.util.Data;


public class AbastecimentoRN extends Transacao {
	
    public AbastecimentoRN() {
    }
    /**
     * Rotina para inclusão de abastecimento...
     * 1 - Busca a km do abastecimento anterior ...
     * 2 - Calcula o percurso ( km do abastecimento - km do abastecimento anterior )
     * 3 - Se for abastecimento completo busca o somatório dos abastecimentos parciais
     * 4 - Inclui registro
     * 5 - Verifica se este abastecimento está entre dois outros
     * 6 - Se 5 for verdadeiro busca o proximo abastecimento
     * 7 - Recalcula o percurso deste proximo abastecimento
     * 8 - Se este for parcial acerta o percurso para a media
     * 9 - Se for Completo busca o somatorio e ajusta o percurso, a quantidade e o valor para a media
     * 10 - Se este ajustado for um parcial procura um completo a frente e se encontrar atualiza o somatório para a media
     * 11 - Atualiza este registro completo com o somatorio 
     * 
     * Objetos:
     *  ed 			- Ed com registro a ser gravado no banco de dados - passo 4
     *  somaED		- Ed para receber os somatorios dos parciais
     *  ajustaED	- Ed o próximo registro	 a ser ajustado - passo 6
     *  atuPercED	- Ed com oid do abastecimento a corrigir e os dados corrigidos - passos 8 ou 9  	
     *  completoED	- Ed com os dados do abastecimento completo mais a frente - passo 10
     *  atuSomaED	- Ed com os dados ( último completo ) a atualizar - passo 11
     *  
     * @param ed
     * @return
     * @throws Excecoes
     */
    public AbastecimentoED inclui(AbastecimentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            AbastecimentoED somaED = null;
            // Calcula o percurso deste abastecimento ...
            ed.setNr_Kilometragem_Percurso(ed.getNr_Kilometragem()-new AbastecimentoBD(sql).getKmAbastecimentoAnterior(ed));
            // Se for um completo  ...
            if ("C".equals(ed.getDm_Completo())){ // Busca o somatórios dos tipos P e soma com o do registro
            	somaED = new AbastecimentoBD(this.sql).somaParcial(ed);
            	ed.setNr_Kilometragem_Percurso_Media(ed.getNr_Kilometragem_Percurso()+somaED.getNr_Kilometragem_Percurso());
            	ed.setNr_Quantidade_Media(ed.getNr_Quantidade()+somaED.getNr_Quantidade());
            	ed.setVl_Abastecimento_Media(ed.getVl_Abastecimento()+somaED.getVl_Abastecimento());
            } else { // Se tipo Parcial, coloca o mesmo valor do roegistro de entrada ... 
            	ed.setNr_Kilometragem_Percurso_Media(ed.getNr_Kilometragem_Percurso());  
            	ed.setNr_Quantidade_Media(ed.getNr_Quantidade());
            	ed.setVl_Abastecimento_Media(ed.getVl_Abastecimento());
            }
            ed = new AbastecimentoBD(this.sql).inclui(ed);
            // Caso seja um abastecimento que se encaixe entre outros dois, ajusta o próximo registro...  
            AbastecimentoED ajustaED = new AbastecimentoBD(this.sql).getProximoAbastecimento(ed); // Busca o próximo
            if (ajustaED.getOid_Abastecimento()>0) { // Se encontrou, ajusta km do percusro
            	ajustaED.setNr_Kilometragem_Percurso(ajustaED.getNr_Kilometragem()-ed.getNr_Kilometragem());
            	if ("P".equals(ajustaED.getDm_Completo())) {
            		ajustaED.setNr_Kilometragem_Percurso_Media(ajustaED.getNr_Kilometragem_Percurso());
            	} else {// Busca o somatório dos parciais para o abastecimento completo 
            		somaED = new AbastecimentoBD(this.sql).somaParcial(ajustaED);
                	ajustaED.setNr_Kilometragem_Percurso_Media(ajustaED.getNr_Kilometragem_Percurso()+somaED.getNr_Kilometragem_Percurso());
                	ajustaED.setNr_Quantidade_Media(ajustaED.getNr_Quantidade()+somaED.getNr_Quantidade());
                	ajustaED.setVl_Abastecimento_Media(ajustaED.getVl_Abastecimento()+somaED.getVl_Abastecimento());
            	}
            	// Monta o ed para atualizar
            	AbastecimentoED atuPercED = new AbastecimentoED();
            	atuPercED.setOid_Abastecimento(ajustaED.getOid_Abastecimento());
            	atuPercED.setNr_Kilometragem_Percurso(ajustaED.getNr_Kilometragem_Percurso());
            	atuPercED.setNr_Kilometragem_Percurso_Media(ajustaED.getNr_Kilometragem_Percurso_Media());
            	atuPercED.setNr_Quantidade_Media(ajustaED.getNr_Quantidade_Media());
            	atuPercED.setVl_Abastecimento_Media(ajustaED.getVl_Abastecimento_Media());
            	
            	// Atualiza no BD ...
            	new AbastecimentoBD(this.sql).ajustaKilometragemPercurso(atuPercED);
            	//Se este ajustado for um parcial procura por um completo a frente para refazer o somatório ...
            	if ("P".equals(ajustaED.getDm_Completo())) {
            		AbastecimentoED completoED = new AbastecimentoBD(this.sql).getProximoAbastecimentoCompleto(ajustaED);
            		if (completoED != null) {
            			// Busca a soma dos parciais....
            			somaED = new AbastecimentoBD(this.sql).somaParcial(completoED);
                    	// Monta o ed para atualizar
            			AbastecimentoED atuSomaED = new AbastecimentoED();
                    	atuSomaED.setOid_Abastecimento(completoED.getOid_Abastecimento());
            			atuSomaED.setNr_Kilometragem_Percurso_Media(completoED.getNr_Kilometragem_Percurso()+somaED.getNr_Kilometragem_Percurso());
            			atuSomaED.setNr_Quantidade_Media(completoED.getNr_Quantidade()+somaED.getNr_Quantidade());
            			atuSomaED.setVl_Abastecimento_Media(completoED.getVl_Abastecimento()+somaED.getVl_Abastecimento());
                    	// Atualiza no BD ...
            			new AbastecimentoBD(this.sql).ajustaKilometragemPercurso(atuSomaED);
            		}
            	}
            }
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Movimento_Ordem_ServicoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Movimento_Ordem_ServicoBD(this.sql).delete(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public List lista(Movimento_Ordem_ServicoED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            List lista = new Movimento_Ordem_ServicoBD(sql).lista(ed);
            return lista;
        } finally {
            fimTransacao(false);
        }
    }

    public ArrayList listaAbastecimentosComMedia(AbastecimentoED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            ArrayList lista = new AbastecimentoBD(sql).listaAbastecimentosComMedia(ed);
            return lista;
        } finally {
            fimTransacao(false);
        }
    }

    public ArrayList mediaMensal(AbastecimentoED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            ArrayList lista = new AbastecimentoBD(sql).mediaMensal(ed);
            return lista;
        } finally {
            fimTransacao(false);
        }
    }
    
   
    public ArrayList listaMovimento_Ordem_Servico(Movimento_Ordem_ServicoED ed) throws Excecoes {

        try {
            inicioTransacao();
            Movimento_Ordem_ServicoBD moBD = new Movimento_Ordem_ServicoBD(sql);
            ArrayList lista = moBD.listaMovimento_Ordem_Servico(ed);
            return lista;
        } finally {
            fimTransacao(false);
        }
    }
    
    public ArrayList lista_Abastec_Externo(Movimento_Ordem_ServicoED ed) throws Excecoes {

        try {
            inicioTransacao();
            Movimento_Ordem_ServicoBD moBD = new Movimento_Ordem_ServicoBD(sql);
            ArrayList lista = moBD.lista_Abastec_Externo(ed);
            return lista;
        } finally {
            fimTransacao(false);
        }
    }
    
    public ArrayList lista_Abastec_Int(Movimento_Ordem_ServicoED ed) throws Excecoes {

        try {
            inicioTransacao();
            Movimento_Ordem_ServicoBD moBD = new Movimento_Ordem_ServicoBD(sql);
            ArrayList lista = moBD.lista_Abastec_Int(ed);
            return lista;
        } finally {
            fimTransacao(false);
        }
    }
    
    public ArrayList lista_Excessoes_Medias(Movimento_Ordem_ServicoED ed) throws Excecoes {

        try {
            inicioTransacao();
            Movimento_Ordem_ServicoBD moBD = new Movimento_Ordem_ServicoBD(sql);
            ArrayList lista = moBD.lista_Excessoes_Medias(ed);
            return lista;
        } finally {
            fimTransacao(false);
        }
    }
    
    public ArrayList lista_Abastec_Doc_Mot(Movimento_Ordem_ServicoED ed) throws Excecoes {

        try {
            inicioTransacao();
            Movimento_Ordem_ServicoBD moBD = new Movimento_Ordem_ServicoBD(sql);
            ArrayList lista = moBD.lista_Abastec_Doc_Mot(ed);
            return lista;
        } finally {
            fimTransacao(false);
        }
    }
    
    public ArrayList lista_Medias_Cons(Movimento_Ordem_ServicoED ed) throws Excecoes {

        try {
            inicioTransacao();
            Movimento_Ordem_ServicoBD moBD = new Movimento_Ordem_ServicoBD(sql);
            ArrayList lista = moBD.list_Medias_Cons(ed);
            //ArrayList lista = new Movimento_Ordem_ServicoBD(sql).lista_Abastec_Externo(ed);
            return lista;
        } finally {
            fimTransacao(false);
        }
    }
    
    public ArrayList list_Totais_Abastec(Movimento_Ordem_ServicoED ed) throws Excecoes {

        try {
            inicioTransacao();
            Movimento_Ordem_ServicoBD moBD = new Movimento_Ordem_ServicoBD(sql);
            ArrayList lista = moBD.list_Totais_Abastec(ed);
            //ArrayList lista = new Movimento_Ordem_ServicoBD(sql).lista_Abastec_Externo(ed);
            return lista;
        } finally {
            fimTransacao(false);
        }
    }
    
    public void relMovimentosOrdemServico(Movimento_Ordem_ServicoED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            new Movimento_Ordem_ServicoBD(sql).relMovimentosOrdemServico(ed);
        } finally {
            fimTransacao(false);
        }
    }

    public void relAbastecimento(Movimento_Ordem_ServicoED ed)
    throws Excecoes {
        inicioTransacao();
        try {
            new Movimento_Ordem_ServicoBD(sql).relAbastecimento(ed);
        } finally {
            fimTransacao(false);
        }
    }
    
    public void relKMsRodados(Movimento_Ordem_ServicoED ed)
    throws Excecoes {
    	inicioTransacao();
    	try {
    		new Movimento_Ordem_ServicoBD(sql).relKMsRodados(ed);
    	} finally {
    		fimTransacao(false);
    	}
    }
    
    public void relatorio_Medias_Cons(Movimento_Ordem_ServicoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		BancoUtil bu = new BancoUtil();
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ArrayList lista = new Movimento_Ordem_ServicoBD(sql).list_Medias_Cons(ed);
    		ed.setLista(lista); 
    		ed.setResponse(response);
    		ed.setNomeRelatorio("aba304");
    		if (bu.doValida(ed.getDt_Inicial()))
    			nm_Filtro+=" De = " + ed.getDt_Inicial();
    		if (bu.doValida(ed.getDt_Final()))
    			nm_Filtro+=" Até = " + ed.getDt_Final();
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }
    
    public void relatorio_Abastec_Doc_Mot(Movimento_Ordem_ServicoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		BancoUtil bu = new BancoUtil();
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ArrayList lista = new Movimento_Ordem_ServicoBD(sql).lista_Abastec_Doc_Mot(ed);
    		ed.setLista(lista); 
    		ed.setResponse(response);
    		ed.setNomeRelatorio("aba308");
    		if(ed.getNr_Documento() > 0)
    			nm_Filtro+=" Documento = " + ed.getNr_Documento();
    		if (bu.doValida(ed.getDt_Inicial()))
    			nm_Filtro+=" De = " + ed.getDt_Inicial();
    		if (bu.doValida(ed.getDt_Final()))
    			nm_Filtro+=" Até = " + ed.getDt_Final();
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorio(Movimento_Ordem_ServicoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Movimento_Ordem_ServicoBD(sql).listaMovimento_Ordem_Servico(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório 
			ed.setResponse(response);
			ed.setNomeRelatorio("aba301"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNr_Frota()))
				nm_Filtro+=" Frota=" + ed.getNr_Frota();
			if (bu.doValida(ed.getDt_Inicial()))
				nm_Filtro+=" Data Incial=" + ed.getDt_Inicial();
			if (bu.doValida(ed.getDt_Final()))
				nm_Filtro+=" Dt_Final=" + ed.getDt_Final();			
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public void relatorioAbastecExt(Movimento_Ordem_ServicoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		BancoUtil bu = new BancoUtil();
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ArrayList lista = new Movimento_Ordem_ServicoBD(sql).lista_Abastec_Externo(ed);
    		ed.setLista(lista); 
    		ed.setResponse(response);
    		ed.setNomeRelatorio("aba302");
    		if (ed.getOid_Pessoa() > 0)
    			nm_Filtro+=" Fornecedor = " + ed.getOid_Pessoa();
    		if (bu.doValida(ed.getDt_Inicial()))
    			nm_Filtro+=" De = " + ed.getDt_Inicial();
    		if (bu.doValida(ed.getDt_Final()))
    			nm_Filtro+=" Até = " + ed.getDt_Final();
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }
    
    public void relatorio_Abastec_Int(Movimento_Ordem_ServicoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		BancoUtil bu = new BancoUtil();
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ArrayList lista = new Movimento_Ordem_ServicoBD(sql).lista_Abastec_Int(ed);
    		ed.setLista(lista); 
    		ed.setResponse(response);
    		ed.setNomeRelatorio("aba307");
    		if (bu.doValida(ed.getDt_Inicial()))
    			nm_Filtro+=" De = " + ed.getDt_Inicial();
    		if (bu.doValida(ed.getDt_Final()))
    			nm_Filtro+=" Até = " + ed.getDt_Final();
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }
    
    public void relatorio_Excessoes_Medias(Movimento_Ordem_ServicoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		BancoUtil bu = new BancoUtil();
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ArrayList lista = new Movimento_Ordem_ServicoBD(sql).lista_Excessoes_Medias(ed);
    		ed.setLista(lista); 
    		ed.setResponse(response);
    		ed.setNomeRelatorio("aba303");
    		if (ed.getOid_Veiculo() > 0)
    			nm_Filtro+=" Veículo = " + ed.getOid_Veiculo();
    		if (bu.doValida(ed.getDt_Inicial()))
    			nm_Filtro+=" De = " + ed.getDt_Inicial();
    		if (bu.doValida(ed.getDt_Final()))
    			nm_Filtro+=" Até = " + ed.getDt_Final();
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
    	} finally {
    		this.fimTransacao(false);
    	}
    }
    
    public void relatorioTotais_Abastec(Movimento_Ordem_ServicoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	try {
    		BancoUtil bu = new BancoUtil();
    		this.inicioTransacao();
    		String nm_Filtro = "";
    		ArrayList lista = new Movimento_Ordem_ServicoBD(sql).list_Totais_Abastec(ed);
    		ed.setLista(lista); 
    		ed.setResponse(response);
    		ed.setNomeRelatorio("aba305");
    		if (bu.doValida(ed.getDt_Inicial()))
    			nm_Filtro+=" De = " + ed.getDt_Inicial();
    		if (bu.doValida(ed.getDt_Final()))
    			nm_Filtro+=" Até = " + ed.getDt_Final();
    		ed.setDescFiltro(nm_Filtro);
    		new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
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
    	Movimento_Ordem_ServicoED ed = (Movimento_Ordem_ServicoED)Obj;
    	ed.setRequest(request);
    	
		if ("1".equals(rel)) {
			this.relatorio(ed, request, response);	
		} 
		if ("2".equals(rel)) {
			this.relatorioAbastecExt(ed, request, response);	
		} 
		if ("3".equals(rel)) {
			this.relatorioTotais_Abastec(ed, request, response);	
		} 
		if ("4".equals(rel)) {
			this.relatorio_Medias_Cons(ed, request, response);	
		}
		if ("5".equals(rel)) {
			this.relatorio_Abastec_Int(ed, request, response);	
		}
		if ("6".equals(rel)) {
			this.relatorio_Excessoes_Medias(ed, request, response);	
		} 
		if ("7".equals(rel)) {
			this.relatorio_Abastec_Doc_Mot(ed, request, response);	
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
    	AbastecimentoED ed = (AbastecimentoED)Obj;
    	
    	//Prepara a saída
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		String dm_Retorno = this.getKmAcumulada(ed); // Calcula a km acumulada do veiculo
			if (dm_Retorno!=null) {
				out.println("<ret><item oknok='" + dm_Retorno + "' /></ret>"); // Vai dar a msg de virada no OL ...
			} else {
				// Verifica se o abastecimento pode ser registrado...
				String val = this.validaAbastecimento(ed);
				if (val!=null) {
					out.println("<ret><item oknok='" + val + "' /></ret>");
				} else {
				    ed = this.inclui(ed);
				    out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Abastecimento() + "' /></ret>");
				}
			}
    	} else 
		if ("D".equals(acao)) {
			//this.deleta(ed);
			//out.println("<ret><item oknok='DOK' /></ret>");
		} else {
		out.println("<cad>");
		String saida=null;
		ArrayList lst = new ArrayList();
		if ("L".equals(acao)) {
			lst = this.listaAbastecimentosComMedia(ed);
		}
//		if ("LT".equals(acao)) {
//			lst = this.lista_Abastec_Externo(ed);
//		}
//		if ("LM".equals(acao)) {
//			lst = this.lista_Medias_Cons(ed);
//		}
//		if ("LTA".equals(acao)) {
//			lst = this.list_Totais_Abastec(ed);
//		}
//		if ("LAI".equals(acao)) {
//			lst = this.lista_Abastec_Int(ed);
//		}
//		if ("LEM".equals(acao)) {
//			lst = this.lista_Excessoes_Medias(ed);
//		}
//		if ("AD".equals(acao)) {
//			lst = this.lista_Abastec_Doc_Mot(ed);
//		}
		for (int i=0; i<lst.size(); i++){
			AbastecimentoED edVolta = new AbastecimentoED();
			edVolta = (AbastecimentoED)lst.get(i);
			if ("L".equals(acao) || "LT".equals(acao) || "LM".equals(acao) || "LTA".equals(acao) || "LAI".equals(acao)  || "LEM".equals(acao) ||  "AD".equals(acao)) {
				saida = montaRegistro(edVolta);
			}	
			out.println(saida);
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }
    
	private String montaRegistro( AbastecimentoED edVolta ){
    	String saida;
		saida = "<item "; 
		saida += "nm_Razao_Social='" + edVolta.getNm_Razao_Social() + "' ";
		saida += "oid_Veiculo='" + edVolta.getOid_Veiculo() + "' ";
		saida += "nm_Tipo_Veiculo='" + edVolta.getNm_Tipo_Veiculo() + "' ";
		saida += "nm_Marca_Veiculo='" + edVolta.getNm_Marca_Veiculo() + "' ";
		saida += "nr_Frota='" + edVolta.getNr_Frota() + "' ";
		saida += "dt_Abastecimento='" + edVolta.getDt_Abastecimento() + "' ";
		saida += "nr_Odometro='" + FormataValor.formataValorBT(edVolta.getNr_Odometro(),1) + "' ";
		saida += "nr_Kilometragem='" + FormataValor.formataValorBT(edVolta.getNr_Kilometragem(),1) + "' ";
		saida += "nr_Quantidade='" + FormataValor.formataValorBT(edVolta.getNr_Quantidade(),2) + "' ";
		saida += "nr_Media_Inferior='" + FormataValor.formataValorBT(edVolta.getNr_Media_Inferior(),1) + "' ";
		saida += "nr_Media_Superior='" + FormataValor.formataValorBT(edVolta.getNr_Media_Superior(),1) + "' ";
		saida += "nr_Documento='" + edVolta.getNr_Documento() + "' ";
		saida += "dm_Tipo_Abastec='" + edVolta.getDm_Tipo_Abastec() + "' ";
		saida += "vl_Abastecimento='" + FormataValor.formataValorBT(edVolta.getVl_Abastecimento(),2) + "' ";
		saida += "dm_Completo='" + edVolta.getDm_Completo() + "' ";
		saida += "vl_Combinado='" + FormataValor.formataValorBT(edVolta.getVl_Combinado(),2) + "' ";
		saida += "nr_Kilometragem_Percurso='" + FormataValor.formataValorBT(edVolta.getNr_Kilometragem(),1) + "' ";
		saida += "vl_Media_Abastecimento='" + FormataValor.formataValorBT(edVolta.getVl_Media_Abastecimento(),2) + "' ";
		saida += "vl_Km='" + FormataValor.formataValorBT(edVolta.getVl_Km(), 2)+ "' ";
		saida += "/>";
		return saida;
    }
	
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.fimTransacao(false);
    }
    
    
    // Busca a km acumulada do veiculo
    private String getKmAcumulada(AbastecimentoED ed) throws Excecoes {
    	HodometroED odoED = new HodometroED();
    	String dm_Retorno=null;
    	try {
    		this.inicioTransacao();
			odoED.setOid_Veiculo(ed.getOid_Veiculo());
			odoED.setOid_Empresa(ed.getOid_Empresa());
			odoED.setDt_Odometro_Troca(ed.getDt_Abastecimento());
			odoED.setNr_Odometro_Colocado(ed.getNr_Odometro());
			odoED.setDm_Virada(ed.getDm_Virada());
			odoED = new HodometroBD(this.sql).getKmAcumulada(odoED);
			ed.setNr_Kilometragem(odoED.getNr_Km_Acum_Troca());
			ed.setDm_Virada(odoED.getDm_Virada());
			dm_Retorno=odoED.getDm_Retorno();
			this.fimTransacao(true);
    	} finally {
    		this.fimTransacao(false);
    	}
		return dm_Retorno;
    }
    
    private String validaAbastecimento ( AbastecimentoED ed) throws Excecoes {
    	String Retorno = null;
    	AbastecimentoED retED = new AbastecimentoED();
		AbastecimentoED abaED = new AbastecimentoED();
		abaED.setOid_Empresa(ed.getOid_Empresa());
		abaED.setOid_Veiculo(ed.getOid_Veiculo());
		abaED.setNr_Kilometragem(ed.getNr_Kilometragem());
    	try {
    		this.inicioTransacao();
    		retED = new AbastecimentoBD(this.sql).getProximoAbastecimento(abaED);
    		if (retED.getOid_Abastecimento()>0) {
	    		if (Data.comparaData(ed.getDt_Abastecimento(), ">" , retED.getDt_Abastecimento()) && ed.getNr_Kilometragem() < retED.getNr_Kilometragem()) {
	    			Retorno = "Existe abastecimento com km superior ("+FormataValor.formataValorBT(retED.getNr_Kilometragem(),1)+") e data inferior ("+retED.getDt_Abastecimento()+") !" ;
	    		} else {
	    			retED = new AbastecimentoBD(this.sql).getAbastecimentoAnterior(abaED);
	    			if (retED.getOid_Abastecimento()>0) {
		        		if (Data.comparaData(ed.getDt_Abastecimento(), "<" , retED.getDt_Abastecimento()) && ed.getNr_Kilometragem() > retED.getNr_Kilometragem()) {
		        			Retorno = "Existe abastecimento com km inferior ("+FormataValor.formataValorBT(retED.getNr_Kilometragem(),1)+") e data superior ("+retED.getDt_Abastecimento()+") !" ;
		        		}
	    			}
	    		}
    		}
    	} finally {
    		this.fimTransacao(false);
    	}
    	return Retorno;
    }

   
}