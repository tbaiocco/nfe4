package com.master.rn;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import com.master.bd.ContaBD;
import com.master.bd.Movimento_ContabilBD;
import com.master.bd.Protocolo_ContabilBD;
import com.master.bd.HistoricoBD;
import com.master.bd.OrigemBD;
import com.master.bd.Centro_CustoBD;
import com.master.ed.ContaED;
import com.master.ed.Movimento_ContabilED;
import com.master.ed.Protocolo_ContabilED;
import com.master.ed.OrigemED;
import com.master.ed.Centro_CustoED;
import com.master.ed.HistoricoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import com.master.root.UnidadeBean;
import com.master.util.Data;
import com.master.util.Valores;
import com.master.ed.ErrosImportacaoED;

/**
 * @author Régis
 * @serial Protocolos Contabeis
 * @serialData 03/2006
 */
public class Protocolo_ContabilRN extends Transacao {

    public Protocolo_ContabilRN() {
    }
   
    public Protocolo_ContabilED inclui(Protocolo_ContabilED ed ) throws Excecoes {

        try {
        	this.inicioTransacao();
            ed = new Protocolo_ContabilBD(this.sql).inclui(ed);
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

    public void altera(Protocolo_ContabilED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Protocolo_ContabilBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Protocolo_ContabilED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Protocolo_ContabilBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Protocolo_ContabilED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Protocolo_ContabilBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
   
    public void lista(Protocolo_ContabilED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Protocolo_ContabilBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Protocolo_ContabilED getByRecord(Protocolo_ContabilED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Protocolo_ContabilBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
	public void getByRecord(Protocolo_ContabilED ed, HttpServletRequest request, String nmObj) throws Excecoes {

		try {
			this.inicioTransacao();
			Protocolo_ContabilED edQBR = new Protocolo_ContabilBD(this.sql).getByRecord(ed);
			request.setAttribute(nmObj, edQBR.getOid_Protocolo_Contabil()== 0 ? null : edQBR);
		} finally { 
			this.fimTransacao(false);
		}
	}
	
	/* 
	 * Importa movimento contabil de arquivo.
	 * Verifica se os dados do arquivo de importação estão corretos.
	 * Somente incorpara ao banco de dados se tudo estiver ok.
	 * Caso contrário monta uma lista com os erros e chama uma tela para listar.
	 */
    public void importa(Protocolo_ContabilED ed,HttpServletRequest request,String nm_Arquivo) throws Excecoes {
        try {
        	double vlD = 0 , vlC = 0 ;
    		int ctaErrReg = 0 , ctaReg = 0 , ctaErr = 0; 
        	ArrayList listErrs = new ArrayList();
        	ArrayList list = new ArrayList();
        	String vl_Tx = new String(); 
        	String tx_Registro = new String();
        	ContaED edConta = new ContaED();
        	UnidadeBean edUnidade = new UnidadeBean();
        	HistoricoED edHistorico = new HistoricoED();
        	OrigemED edOrigem = new OrigemED();
        	
        	Centro_CustoED edCentro_Custo = new Centro_CustoED();
            LineNumberReader line = new LineNumberReader(new FileReader(nm_Arquivo));
            if (line.ready()) {
            	this.inicioTransacao();	
            	while ((tx_Registro = line.readLine()) != null){
            		ctaErrReg = 0 ; ctaReg ++;
            		// Separa os campos do registro e coloca no ed
            		Movimento_ContabilED edMovCtb = new Movimento_ContabilED();
            		if (tx_Registro.length() == 302 ) {
	            		edMovCtb.setDt_Movimento(tx_Registro.substring(0,10));
	            		edMovCtb.setCd_Unidade(tx_Registro.substring(10,13).trim());
	            		edMovCtb.setCd_Origem(tx_Registro.substring(13,16).trim());
	            		edMovCtb.setCd_Conta(tx_Registro.substring(16,22).trim());
	            		edMovCtb.setCd_Historico(tx_Registro.substring(22,25).trim());
	            		edMovCtb.setTx_Historico_Complementar(tx_Registro.substring(25,271).trim());
	            		edMovCtb.setDm_Debito_Credito(tx_Registro.substring(281,282));
	            		vl_Tx = tx_Registro.substring(282,297).trim();
	            		edMovCtb.setVl_Lancamento(new Double(tx_Registro.substring(282,297).trim()).doubleValue());
	            		edMovCtb.setCd_Centro_Custo(tx_Registro.substring(297,302).trim());
	            		edMovCtb.setDt_Digitacao(Data.getDataDMY());
	            		edMovCtb.setTx_Chave_Origem("IMPORTACAO");
	            		edMovCtb.setCd_Estrutural("");
	            		// Valida a conta
	            		edConta.setCd_Conta(edMovCtb.getCd_Conta());
	            		edConta = new ContaBD(this.sql).getByCD(edConta);
	            		if (edConta.getOid_Conta() == null){
	            			edMovCtb.setCd_Estrutural(edMovCtb.getCd_Estrutural() + " [Conta não cadastrada = " + edMovCtb.getCd_Conta() + "]");
	            			ctaErrReg ++ ;
	            		} else {
	            			edMovCtb.setOid_Conta(edConta.getOid_Conta().longValue());
	            		}	
	            		// Valida a unidade
	            		edUnidade = UnidadeBean.getByCD_Unidade(edMovCtb.getCd_Unidade());
	            		if (edUnidade.getOID_Unidade() == 0){
	            			edMovCtb.setCd_Estrutural(edMovCtb.getCd_Estrutural() + " [Unidade não cadastrada = " + edMovCtb.getCd_Unidade()+ "]");
	            			ctaErrReg ++ ;
	            		} else {
	            			edMovCtb.setOid_Unidade(edUnidade.getOID_Unidade());
	            		}	
	            		// Valida o historico
	            		edHistorico.setCd_Historico(edMovCtb.getCd_Historico());
	            		edHistorico = new HistoricoBD(this.sql).getByRecord(edHistorico);
	            		if (edHistorico.getOid_Historico() == null){
	            			edMovCtb.setCd_Estrutural(edMovCtb.getCd_Estrutural() + " [Historico não cadastrado = " + edMovCtb.getCd_Historico()+ "]");
	            			ctaErrReg ++ ;
	            		} else {
	            			edMovCtb.setOid_Historico(edHistorico.getOid_Historico().longValue());
	            			edMovCtb.setTx_Historico(edHistorico.getNm_Historico());
	            		}	
	            		// Valida a origem
	            		edOrigem.setCD_Origem(edMovCtb.getCd_Origem());
	            		edOrigem = new OrigemBD(this.sql).getByRecord(edOrigem);
	            		if (edOrigem.getOID_Origem() == 0){
	            			edMovCtb.setCd_Estrutural(edMovCtb.getCd_Estrutural() + " [Origem não cadastrada = " + edMovCtb.getCd_Origem()+ "]");
	            			ctaErrReg ++ ;
	            		} else {
	            			edMovCtb.setOid_Origem(edOrigem.getOID_Origem());
	            		}	
	            		// Valida o centro de custos
	            		edCentro_Custo.setCd_Centro_Custo(edMovCtb.getCd_Centro_Custo());
	            		edCentro_Custo = new Centro_CustoBD(this.sql).getByRecord(edCentro_Custo);
	            		if (edCentro_Custo.getOid_Centro_Custo() == null){
	            			edMovCtb.setCd_Estrutural(edMovCtb.getCd_Estrutural() + " [Centro de custo não cadastrado = " + edMovCtb.getCd_Centro_Custo()+ "]");
	            			ctaErrReg ++ ;
	            		} else {
	            			edMovCtb.setOid_Centro_Custo(edCentro_Custo.getOid_Centro_Custo().longValue());
	            		}
	            		// Valida o ponto decimal
	            		if (vl_Tx.indexOf(".") != 12 ) {
	            			edMovCtb.setCd_Estrutural(edMovCtb.getCd_Estrutural() + " [Ponto decimal não existente = " + vl_Tx + "]");
	            			ctaErrReg ++ ;
	            		} else {
	            			if ( "D".equals(edMovCtb.getDm_Debito_Credito()) ){
	            				vlD = vlD + edMovCtb.getVl_Lancamento();
	            			} else {
	            				vlC = vlC + edMovCtb.getVl_Lancamento();
	            			}
	            		}
	            		// Valida debito credito
	            		if ( !("D".equals(edMovCtb.getDm_Debito_Credito()) || "C".equals(edMovCtb.getDm_Debito_Credito())) ) {
	            			edMovCtb.setCd_Estrutural(edMovCtb.getCd_Estrutural() + " [Campo debito/credito não informado = " + edMovCtb.getDm_Debito_Credito()+ "]");
	            			ctaErrReg ++ ;
	            		}
            		} else {
            			edMovCtb.setCd_Estrutural(edMovCtb.getCd_Estrutural() + " [Registro menor que o esperado: Esperado 302 caracteres encontrado " + tx_Registro.length() + " caracteres encontrados]");
            			ctaErrReg ++ ;
            		}
            		if (ctaErrReg > 0 ) {
            			ctaErr++;
            			edMovCtb.setOid_Movimento_Contabil(ctaReg); // Contador de registros
            		}
            		// Adiciona na lista
            		list.add(edMovCtb);
            	}
            	// Verifica se débito e crédito fecham.
            	if ( vlD != vlC ) {
            		ctaErr++;
            		Movimento_ContabilED edMovCtb = new Movimento_ContabilED();
            		edMovCtb.setCd_Estrutural(" [Débito e crédito não fecham - Débito = " + Valores.formatMonetario(vlD) + " Crédito = " + Valores.formatMonetario(vlC) + "]");
            		edMovCtb.setOid_Movimento_Contabil(ctaReg); // Contador de registros
            		list.add(edMovCtb);
            	}
            	if (ctaErr == 0 ) {
            		// Aqui incorpora definitivamente os lançamentos
            		long nr_Lote = 0;
        			for (int i=0;i<list.size();i++) {
        				Movimento_ContabilED edLst = (Movimento_ContabilED)list.get(i);
        				edLst.setNr_Lote(nr_Lote); 
                    	//Grava ed definitivo
        				edLst = new Movimento_ContabilBD(this.sql).inclui(edLst);
                    	//Se for o primeiro registro, pega o numero do lote para repetir nos outros
                    	if (nr_Lote == 0) nr_Lote = edLst.getNr_Lote();
        			};	
        			ed.setDm_Situacao("O");
        			new Protocolo_ContabilBD(this.sql).altera(ed);
        			request.setAttribute("status", "OK");
            	} else {
            		// Aqui monta a lista com os erros.
        			for (int i=0;i<list.size();i++) {
        				Movimento_ContabilED movLst = (Movimento_ContabilED)list.get(i);
        				if (movLst.getOid_Movimento_Contabil() > 0) {
        					ErrosImportacaoED edErro = new ErrosImportacaoED();
        					edErro.setTx_Erro("Registro # " + movLst.getOid_Movimento_Contabil() + " | Erro : " + movLst.getCd_Estrutural());
        					listErrs.add(edErro);
        				}
        			};
        			// Coloca a lista de erros na session
        			request.getSession(true).setAttribute("listErrs", listErrs);
        			ed.setDm_Situacao("E");
        			new Protocolo_ContabilBD(this.sql).altera(ed);
        			request.setAttribute("status", "NOK");
            	}
            	this.fimTransacao(true);
            }
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        } catch (Exception e) {
        	this.abortaTransacao();
        	throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "importa(Protocolo_ContabilED ed)");
		}
        finally {
            this.fimTransacao(false);
        }
    	
    }
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}