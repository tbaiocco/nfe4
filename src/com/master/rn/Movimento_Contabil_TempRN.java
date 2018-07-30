package com.master.rn;

import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import com.master.bd.Movimento_Contabil_TempBD;
import com.master.ed.Movimento_Contabil_TempED;
import com.master.bd.Movimento_ContabilBD;
import com.master.ed.Movimento_ContabilED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import com.master.ed.UsuarioED;

/**
 * @author Régis Steigleder
 * @serial Movimentos Contabeis Temporarios
 * @serialData 14/10/2005
 */
public class Movimento_Contabil_TempRN extends Transacao {

    public Movimento_Contabil_TempRN() {
    }
   
    public Movimento_Contabil_TempED inclui(Movimento_Contabil_TempED ed)  throws Excecoes {
        try {
        	this.inicioTransacao();
            ed = new Movimento_Contabil_TempBD(this.sql).inclui(ed);
            this.fimTransacao(true);
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
    
    public String gravaLancamentos(Movimento_Contabil_TempED ed)  throws Excecoes {
    	String msg = new String();
    	long nr_Lote = 0;
    	long nr_Lote_Manutencao = 0;
     	Movimento_ContabilED edDef = new Movimento_ContabilED();
    	msg = null;
        try {
            this.inicioTransacao();
            // Apaga os itens do definitivos deste lote se for regravação
            nr_Lote_Manutencao = ed.getNr_Lote_Originario();
            if ( nr_Lote_Manutencao > 0 ) {
            	edDef.setNr_Lote(nr_Lote_Manutencao);
            	new Movimento_ContabilBD(this.sql).deletaCP(edDef);
            }
            //Pega a lista completa dos lançamentos temporarios do usuario
             ArrayList lista = new Movimento_Contabil_TempBD(sql).lista(ed,"gravaLancamentos");
            //Faz a iteração na lista 
            for (int i=0; i<lista.size(); i++)              
            {
            	Movimento_Contabil_TempED edTemp = new Movimento_Contabil_TempED();
            	edTemp = (Movimento_Contabil_TempED)lista.get(i);         
            	//Monta o ed definitivo
            	edDef.setDt_Movimento(edTemp.getDt_Movimento());
            	edDef.setOid_Unidade(edTemp.getOid_Unidade());
            	edDef.setOid_Origem(edTemp.getOid_Origem());
            	edDef.setOid_Conta(edTemp.getOid_Conta());
            	edDef.setTx_Historico(edTemp.getTx_Historico());
            	edDef.setTx_Historico_Complementar(edTemp.getTx_Historico_Complementar());
            	edDef.setDm_Debito_Credito(edTemp.getDm_Debito_Credito());
            	edDef.setVl_Lancamento(edTemp.getVl_Lancamento());
            	edDef.setDt_Digitacao(edTemp.getDt_Digitacao());
            	edDef.setTx_Chave_Origem(edTemp.getTx_Chave_Origem());
            	edDef.setNr_Lote(nr_Lote); 
            	edDef.setCd_Lote(edTemp.getCd_Lote());
            	edDef.setOid_Historico(edTemp.getOid_Historico());
            	edDef.setOid_Centro_Custo(edTemp.getOid_Centro_Custo());
            	//Grava ed definitivo
            	edDef = new Movimento_ContabilBD(this.sql).inclui(edDef);
            	//Se for o primeiro registro, pega o numero do lote para repetir nos outros
            	if (nr_Lote == 0) nr_Lote = edDef.getNr_Lote();
            }
            new Movimento_Contabil_TempBD(this.sql).deletaCP(ed);
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
            return msg;
    }
    
    public void gravaTemporario(Movimento_Contabil_TempED ed, HttpServletRequest req, String nmObj)  throws Excecoes {
    	long nr_Lote = 0 ;
    	String tela, DC;
    	ArrayList lista;
    	UsuarioED UsuarioLogado = (UsuarioED)req.getSession().getAttribute("usuario");
    	long oid_Usuario = UsuarioLogado.getOid_Usuario().longValue();
    	Movimento_ContabilED edDef = new Movimento_ContabilED();
    	edDef.setOid_Movimento_Contabil(ed.getOid_Movimento_Contabil());
    	try {
            this.inicioTransacao();
            Movimento_ContabilED edDefnrLote = new Movimento_ContabilED();
            //Pega a lista dos lançamentos definitivos 
            edDefnrLote.setNr_Lote(ed.getNr_Lote());
            tela = new Movimento_ContabilBD(this.sql).getQualTela(edDefnrLote,"QT");
            if (tela == "ctbM004.jsp") {
            	lista = new Movimento_ContabilBD(this.sql).lista(edDefnrLote);
            	//Faz a iteração na lista para gravar o temporário tal qual esta no definitivo.
                for (int i=0; i<lista.size(); i++) {
                	edDef = (Movimento_ContabilED)lista.get(i);
                	Movimento_Contabil_TempED edTemp = new Movimento_Contabil_TempED();
                	//Monta o ed pra gravar o temporario
                	edTemp.setDt_Movimento(edDef.getDt_Movimento());
                	edTemp.setOid_Unidade(edDef.getOid_Unidade());
                	edTemp.setOid_Origem(edDef.getOid_Origem());
                	edTemp.setOid_Conta(edDef.getOid_Conta());
                	edTemp.setTx_Historico(edDef.getTx_Historico());
                	edTemp.setTx_Historico_Complementar(edDef.getTx_Historico_Complementar());
                	edTemp.setDm_Debito_Credito(edDef.getDm_Debito_Credito());
                	edTemp.setVl_Lancamento(edDef.getVl_Lancamento());
                	edTemp.setDt_Digitacao(edDef.getDt_Digitacao());
                	edTemp.setTx_Chave_Origem(edDef.getTx_Chave_Origem());
                	edTemp.setNr_Lote(nr_Lote);
                	edTemp.setCd_Lote(edDef.getCd_Lote());
                	edTemp.setOid_Historico(edDef.getOid_Historico());
                	edTemp.setOid_Centro_Custo(edDef.getOid_Centro_Custo());
                	edTemp.setOid_Usuario(oid_Usuario);
                	edTemp.setNr_Lote_Originario(edDef.getNr_Lote());
                	//Grava ed temporario
                	edTemp = new Movimento_Contabil_TempBD(this.sql).inclui(edTemp);
                	//Se for o primeiro registro, pega o numero do lote para repetir nos outros
                	if (nr_Lote == 0) nr_Lote = edTemp.getNr_Lote();
                }
            } else {
            	DC = new Movimento_ContabilBD(this.sql).getQualTela(edDefnrLote,"DC");
        		//grava o único com dm_Debito_Credito == DC
            	edDefnrLote.setNr_Lote(ed.getNr_Lote());
            	edDefnrLote.setTipo(DC);
            	lista = new Movimento_ContabilBD(this.sql).lista(edDefnrLote);            		
	            //Faz a iteração na lista para gravar o temporário.
	            for (int i=0; i<lista.size(); i++) {
	            	edDef = (Movimento_ContabilED)lista.get(i);
	            	Movimento_Contabil_TempED edTemp = new Movimento_Contabil_TempED();
	            	//Monta o ed pra gravar o temporario
	            	edTemp.setDt_Movimento(edDef.getDt_Movimento());
	            	edTemp.setOid_Unidade(edDef.getOid_Unidade());
	            	edTemp.setOid_Origem(edDef.getOid_Origem());
	            	edTemp.setOid_Conta(edDef.getOid_Conta());
	            	edTemp.setTx_Historico(edDef.getTx_Historico());
	            	edTemp.setTx_Historico_Complementar(edDef.getTx_Historico_Complementar());
	            	edTemp.setDm_Debito_Credito(edDef.getDm_Debito_Credito());
	            	edTemp.setVl_Lancamento(edDef.getVl_Lancamento());
	            	edTemp.setDt_Digitacao(edDef.getDt_Digitacao());
	            	edTemp.setTx_Chave_Origem(edDef.getTx_Chave_Origem());
	            	edTemp.setNr_Lote(nr_Lote);
	            	edTemp.setCd_Lote(edDef.getCd_Lote());
	            	edTemp.setOid_Historico(edDef.getOid_Historico());
	            	edTemp.setOid_Centro_Custo(edDef.getOid_Centro_Custo());
	            	edTemp.setOid_Usuario(oid_Usuario);
	            	edTemp.setNr_Lote_Originario(edDef.getNr_Lote());
	            	//Grava ed temporario
	            	edTemp = new Movimento_Contabil_TempBD(this.sql).inclui(edTemp);
	            	//Se for o primeiro registro, pega o numero do lote para repetir nos outros
	            	if (nr_Lote == 0) nr_Lote = edTemp.getNr_Lote();
	            }
	            //grava os outros comdm_Debito_Credito != DC
            	edDefnrLote.setTipo( DC == "C" ? "D" : "C" );
            	lista = new Movimento_ContabilBD(this.sql).lista(edDefnrLote);            		
	            //Faz a iteração na lista para gravar o temporário.
	            for (int i=0; i<lista.size(); i++) {
	            	edDef = (Movimento_ContabilED)lista.get(i);
	            	Movimento_Contabil_TempED edTemp = new Movimento_Contabil_TempED();
	            	//Monta o ed pra gravar o temporario
	            	edTemp.setDt_Movimento(edDef.getDt_Movimento());
	            	edTemp.setOid_Unidade(edDef.getOid_Unidade());
	            	edTemp.setOid_Origem(edDef.getOid_Origem());
	            	edTemp.setOid_Conta(edDef.getOid_Conta());
	            	edTemp.setTx_Historico(edDef.getTx_Historico());
	            	edTemp.setTx_Historico_Complementar(edDef.getTx_Historico_Complementar());
	            	edTemp.setDm_Debito_Credito(edDef.getDm_Debito_Credito());
	            	edTemp.setVl_Lancamento(edDef.getVl_Lancamento());
	            	edTemp.setDt_Digitacao(edDef.getDt_Digitacao());
	            	edTemp.setTx_Chave_Origem(edDef.getTx_Chave_Origem());
	            	edTemp.setNr_Lote(nr_Lote);
	            	edTemp.setCd_Lote(edDef.getCd_Lote());
	            	edTemp.setOid_Historico(edDef.getOid_Historico());
	            	edTemp.setOid_Centro_Custo(edDef.getOid_Centro_Custo());
	            	edTemp.setOid_Usuario(oid_Usuario);
	            	edTemp.setNr_Lote_Originario(edDef.getNr_Lote());
	            	//Grava ed temporario
	            	edTemp = new Movimento_Contabil_TempBD(this.sql).inclui(edTemp);
	            	//Se for o primeiro registro, pega o numero do lote para repetir nos outros
	            	if (nr_Lote == 0) nr_Lote = edTemp.getNr_Lote();
	            }
            }
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
    	Movimento_Contabil_TempED edTemp = new Movimento_Contabil_TempED();
    	edTemp.setOid_Usuario(oid_Usuario);
    	req.setAttribute(nmObj,tela) ;
    }

    public void altera(Movimento_Contabil_TempED ed, String Tela) throws Excecoes {
        try {
            this.inicioTransacao();

            if ("ctbM004".equals(Tela)) {
            	//Se a solicitacão vier da tela ctbM004 então está alterando 0 lote e 
	        	//devemos acessar todos os lancamentos do lote e alterar os seguintes campos
	        	//oid_Unidade, oid_Origem, dt_Movimento
	        	ArrayList lstFilhos = new Movimento_Contabil_TempBD(sql).lista(ed);
				Iterator i = lstFilhos.iterator();
				while (i.hasNext()) {
					Movimento_Contabil_TempED movFilho = (Movimento_Contabil_TempED)i.next();
					movFilho.setOid_Unidade(ed.getOid_Unidade());
					movFilho.setOid_Origem(ed.getOid_Origem());
					movFilho.setDt_Movimento(ed.getDt_Movimento());
					new Movimento_Contabil_TempBD(this.sql).altera(movFilho);
				}
            } else {
            	new Movimento_Contabil_TempBD(this.sql).altera(ed);
            }
            if ("ctbM002".equals(Tela)) {
            	//Se a solicitacão vier da tela ctbM002 então está alterando o lancamento pai e 
	        	//devemos acessar todos os lancamentos que estão relacionados e alterar os seguintes campos
	        	//oid_Unidade, oid_Origem, dt_Movimento, dm_Debito_Credito invertido
	        	ArrayList lstFilhos = new Movimento_Contabil_TempBD(sql).lista(ed);
				Iterator i = lstFilhos.iterator();
				while (i.hasNext()) {
					Movimento_Contabil_TempED movFilho = (Movimento_Contabil_TempED)i.next();
					movFilho.setOid_Unidade(ed.getOid_Unidade());
					movFilho.setOid_Origem(ed.getOid_Origem());
					movFilho.setDt_Movimento(ed.getDt_Movimento());
					movFilho.setDm_Debito_Credito("D".equals(ed.getDm_Debito_Credito()) ? "C" : "D");
					new Movimento_Contabil_TempBD(this.sql).altera(movFilho);
				}
            }
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void converte(Movimento_Contabil_TempED ed)  throws Excecoes {
        try {
            this.inicioTransacao();
            //Pega a lista completa dos lançamentos temporarios do usuario
             ArrayList lista = new Movimento_Contabil_TempBD(sql).lista(ed,"gravaLancamentos");
            //Faz a iteração na lista 
            for (int i=0; i<lista.size(); i++) {
            	Movimento_Contabil_TempED edTemp = new Movimento_Contabil_TempED();
            	edTemp = (Movimento_Contabil_TempED)lista.get(i);         
            	//Altera dm_tela para 4 - Indica que devera usar a tela de lote (4)
            	edTemp.setDm_Tela(4);
            	//Grava ed temporário
            	new Movimento_Contabil_TempBD(this.sql).altera(edTemp);
            }
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

    
    public void deletaCP(Movimento_Contabil_TempED ed,String tipo) throws Excecoes {
        long nrLote_Manutencao = 0;
        Movimento_ContabilED edDef = new Movimento_ContabilED();
    	try {
            this.inicioTransacao();

            if ("T".equals(tipo)) { // se tipo = 'T' então apaga o registro definitivo (originario)	
	            nrLote_Manutencao = ed.getNr_Lote_Originario();
	            if ( nrLote_Manutencao > 0 ) {
	            	edDef.setNr_Lote(nrLote_Manutencao);
	            	new Movimento_ContabilBD(this.sql).deletaCP(edDef);
	            }
            }    
            new Movimento_Contabil_TempBD(this.sql).deletaCP(ed);
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
    
    public void deleta(Movimento_Contabil_TempED ed) throws Excecoes {
        try {
            this.inicioTransacao();	
            new Movimento_Contabil_TempBD(this.sql).deleta(ed);
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

    public ArrayList lista(Movimento_Contabil_TempED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            ArrayList lista = new Movimento_Contabil_TempBD(sql).lista(ed);
            return lista;
        }
        finally {
            this.fimTransacao(false);
        }
    }
   
    public void lista(Movimento_Contabil_TempED ed, HttpServletRequest request, String nmObj) throws Excecoes {
        try {
            this.inicioTransacao();
            ArrayList lista = new Movimento_Contabil_TempBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        }
        finally {
            this.fimTransacao(false);
        }
    }

    public Movimento_Contabil_TempED getByRecord(Movimento_Contabil_TempED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            return new Movimento_Contabil_TempBD(this.sql).getByRecord(ed);
        } 
        finally {
            this.fimTransacao(false);
        }
    }
    
	public void getByRecord(Movimento_Contabil_TempED ed, HttpServletRequest request, String nmObj) throws Excecoes {
		try {
			this.inicioTransacao();
			Movimento_Contabil_TempED edQBR = new Movimento_Contabil_TempBD(this.sql).getByRecord(ed);
			request.setAttribute(nmObj, edQBR.getOid_Movimento_Contabil()== 0 ? null : edQBR);
		} 
		finally { 
			this.fimTransacao(false);
		}
	}

	public void getFirstRecord(Movimento_Contabil_TempED ed, HttpServletRequest request, String nmObj) throws Excecoes {
		try {
			this.inicioTransacao();
			Movimento_Contabil_TempED edQBR = new Movimento_Contabil_TempBD(this.sql).getFirstRecord(ed);
			request.setAttribute(nmObj, edQBR.getOid_Movimento_Contabil()== 0 ? null : edQBR);
		} 
		finally { 
			this.fimTransacao(false);
		}
	}

    public void getLista (Movimento_Contabil_TempED ed, HttpServletRequest request, String nmObj) throws Excecoes {
        try {
            this.inicioTransacao();
            ArrayList lista = new Movimento_Contabil_TempBD(sql).getLista(ed);
            request.setAttribute(nmObj, lista);
        }
        finally {
            this.fimTransacao(false);
        }
    }

    public void getQualTela (Movimento_Contabil_TempED ed, HttpServletRequest request, String nmObj) throws Excecoes {
        try {
            this.inicioTransacao();
            request.setAttribute(nmObj, new Movimento_Contabil_TempBD(this.sql).getQualTela(ed));
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