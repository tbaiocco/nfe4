package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.ColetaBD;
import com.master.bd.ConhecimentoBD;
import com.master.bd.ContaBD;
import com.master.bd.ManifestoBD;
import com.master.bd.Movimento_ContabilBD;
import com.master.bd.Movimento_PneuBD;
import com.master.bd.Programacao_CargaBD;
import com.master.bd.Solicitacao_CompraBD;
import com.master.ed.ColetaED;
import com.master.ed.ConhecimentoED;
import com.master.ed.ContaED;
import com.master.ed.ManifestoED;
import com.master.ed.Movimento_ContabilED;
import com.master.ed.Movimento_PneuED;
import com.master.ed.Programacao_CargaED;
import com.master.ed.Solicitacao_CompraED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

public class ColetaRN extends Transacao  {
	ColetaBD ColetaBD = null;


	public ColetaRN() {
		//Coletabd = new ColetaBD(this.sql);
	}


	public ColetaED inclui(ColetaED ed) throws Excecoes {

		ColetaED conED = new ColetaED();

		try {
			this.inicioTransacao();
			ColetaBD = new ColetaBD(this.sql);
			conED = ColetaBD.inclui(ed);
			this.fimTransacao(true);

		} catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		}

		catch (Exception e) {
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao incluir");
			excecoes.setMetodo("inclui");
			excecoes.setExc(e);
			//faz rollback pois deu algum erro
			this.abortaTransacao();

			throw excecoes;
		}

		return conED;

	}
	
	  public void finaliza_coleta (ColetaED ed) throws Excecoes {

		    if (String.valueOf (ed.getOID_Coleta ()).compareTo ("") == 0) {
		      Excecoes exc = new Excecoes ();
		      exc.setMensagem ("Código do Coleta não foi informado !!!");
		      throw exc;
		    }

		    try {
		      
		      this.inicioTransacao ();

		      ColetaBD = new ColetaBD (this.sql);
		      ColetaBD.finaliza_coleta (ed);

		      this.fimTransacao (true);

		    }
		    catch (Excecoes exc) {
		      this.abortaTransacao ();
		      throw exc;
		    }

		    catch (Exception e) {
		      Excecoes excecoes = new Excecoes ();
		      excecoes.setClasse (this.getClass ().getName ());
		      excecoes.setMensagem ("Erro ao Finalizar");
		      excecoes.setMetodo ("Finaliza");
		      excecoes.setExc (e);
		      //faz rollback pois deu algum erro
		      this.abortaTransacao ();

		      throw excecoes;
		    }

		  }
	

	public ColetaED getByRecord(ColetaED ed) throws Excecoes {
		ColetaED edVolta = new ColetaED();
		try {
			this.inicioTransacao();
			edVolta = new ColetaBD(this.sql).getByRecord(ed);
			this.fimTransacao(true);
		} catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		} catch (RuntimeException e) {
			abortaTransacao();
			throw e;
		}
		return edVolta;
	}

	public ColetaED copia_Coleta(ColetaED ed) throws Excecoes {
		ColetaED edVolta = new ColetaED();
		try {
			this.inicioTransacao();
			edVolta = new ColetaBD(this.sql).copia_Coleta(ed);
			this.fimTransacao(true);
		} catch (Excecoes exc) {
			this.abortaTransacao();
			throw exc;
		} catch (RuntimeException e) {
			abortaTransacao();
			throw e;
		}
		return edVolta;
	}

	public ArrayList Lista_Coleta(ColetaED ed)throws Excecoes{

		//retorna um arraylist de ED´s
		this.inicioTransacao();
		ArrayList lista = new ColetaBD(sql).Lista_Coleta(ed);
		this.fimTransacao(false);
		return lista;
	}


	public byte[] Imprime_Coleta(ColetaED ed)throws Excecoes{

		//antes de invocar chamada ao relatorio deve-se
		//fazer validacoes de regra de negocio

		this.inicioTransacao();
		ColetaBD = new ColetaBD(this.sql);
		byte[] b = ColetaBD.Imprime_Coleta(ed);
		this.fimTransacao(true);
		return b;
	}


	public String Imprime_Coleta_Matricial (ColetaED ed) throws Excecoes {
		inicioTransacao ();
		try {
			String toReturn = new ColetaBD (sql).Imprime_Coleta_Matricial (ed);
			fimTransacao (true);
			return toReturn;
		}
		catch (Excecoes e) {
			abortaTransacao ();
			throw e;
		}
		catch (RuntimeException e) {
			abortaTransacao ();
			throw e;
		}
	}

	public byte[] Relatorio_Coleta_Notas_Fiscais(ColetaED ed)throws Excecoes{

		//antes de invocar chamada ao relatorio deve-se
		//fazer validacoes de regra de negocio

		this.inicioTransacao();
		ColetaBD = new ColetaBD(this.sql);
		byte[] b = ColetaBD.Relatorio_Coleta_Notas_Fiscais(ed);
		this.fimTransacao(false);
		return b;
	}

	public byte[] Relatorio_Coleta(ColetaED ed)throws Excecoes{

		//antes de invocar chamada ao relatorio deve-se
		//fazer validacoes de regra de negocio

		this.inicioTransacao();
		ColetaBD = new ColetaBD(this.sql);
		byte[] b = ColetaBD.Relatorio_Coleta(ed);
		this.fimTransacao(false);
		return b;
	}

	public void altera_Veiculo(ColetaED ed)throws Excecoes{

		try{
			this.inicioTransacao();
			ColetaBD = new ColetaBD(this.sql);
			ColetaBD.altera_Veiculo(ed);
			this.fimTransacao(true);
		}
		catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		}catch(Exception e){
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao alterar");
			excecoes.setMetodo("altera_Veiculo");
			excecoes.setExc(e);
			//faz rollback pois deu algum erro
			this.abortaTransacao();
			throw excecoes;
		}
	}

	public ColetaED confirma_Reprova_Cotacao (ColetaED ed) throws Excecoes {

		ColetaED conED = new ColetaED();

		try {
			this.inicioTransacao ();
			ColetaBD = new ColetaBD (this.sql);
			conED = ColetaBD.confirma_Reprova_Cotacao (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes exc) {
			this.abortaTransacao ();
			throw exc;
		}
		catch (Exception e) {
			Excecoes excecoes = new Excecoes ();
			excecoes.setClasse (this.getClass ().getName ());
			excecoes.setMensagem ("Erro ao alterar");
			excecoes.setMetodo ("confirma_Cotacao");
			excecoes.setExc (e);
			//faz rollback pois deu algum erro
			this.abortaTransacao ();
			throw excecoes;
		}
		return conED;
	}

	public void remarca(ColetaED ed)throws Excecoes{

		try{
			this.inicioTransacao();
			ColetaBD = new ColetaBD(this.sql);
			ColetaBD.remarca(ed);
			this.fimTransacao(true);
		}
		catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		}catch(Exception e){
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao alterar");
			excecoes.setMetodo("remarca");
			excecoes.setExc(e);
			//faz rollback pois deu algum erro
			this.abortaTransacao();
			throw excecoes;
		}
	}

	public void altera(ColetaED ed)throws Excecoes{

		try{
			this.inicioTransacao();
			ColetaBD = new ColetaBD(this.sql);
			ColetaBD.altera(ed);
			this.fimTransacao(true);
		}
		catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		}catch(Exception e){
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao alterar");
			excecoes.setMetodo("altera_Veiculo");
			excecoes.setExc(e);
			//faz rollback pois deu algum erro
			this.abortaTransacao();
			throw excecoes;
		}
	}

	public void cancela(ColetaED ed)throws Excecoes{

		try{
			this.inicioTransacao();
			ColetaBD = new ColetaBD(this.sql);
			ColetaBD.cancela(ed);
			this.fimTransacao(true);
		}
		catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		}catch(Exception e){
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao alterar");
			excecoes.setMetodo("altera_Veiculo");
			excecoes.setExc(e);
			//faz rollback pois deu algum erro
			this.abortaTransacao();
			throw excecoes;
		}
	}
	
	public void vincula_CTRC(ColetaED ed)throws Excecoes{

		try{
			this.inicioTransacao();
			ColetaBD = new ColetaBD(this.sql);
			ColetaBD.vincula_CTRC(ed);
			this.fimTransacao(true);
		}
		catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		}catch(Exception e){
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao alterar");
			excecoes.setMetodo("altera_Veiculo");
			excecoes.setExc(e);
			//faz rollback pois deu algum erro
			this.abortaTransacao();
			throw excecoes;
		}
	}

	public void Imprime_ColetaJasper(ColetaED ed, HttpServletResponse resp)throws Excecoes{

		//antes de invocar chamada ao relatorio deve-se
		//fazer validacoes de regra de negocio

		this.inicioTransacao();
		ColetaBD = new ColetaBD(this.sql);
		ColetaBD.Imprime_ColetaJasper(ed, resp);
		this.fimTransacao(false);
	}

	public void Relatorio_Coleta_Filtro(ColetaED ed,HttpServletRequest request, HttpServletResponse response, String Rel) throws Excecoes {
		ArrayList listColetaRel = new ArrayList(); // Array para enviar para o relatório
		try {
			this.inicioTransacao();
			// Busca o movimento de lancamentos para o periodo informado com o ed da tela de filtro ctbF011.jsp
			ArrayList listColeta = new ArrayList();
			ColetaED EDFiltro = new ColetaED();
			String OID_Unidade = request.getParameter ("oid_Unidade");
			String OID_Pessoa_Remetente = request.getParameter ("FT_NR_CNPJ_CPF_Remetente");
			String OID_Pessoa_Destinatario = request.getParameter ("oid_Pessoa_Destinatario");			
			String NR_Coleta = request.getParameter ("FT_NR_Coleta");			
			String DM_Tipo_Coleta = request.getParameter ("FT_DM_Tipo_Coleta");			
			String DT_Emissao = request.getParameter ("FT_DT_Emissao");			
			String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
			String OID_Veiculo = request.getParameter ("oid_Veiculo");
			String NR_Placa_Carreta = request.getParameter ("FT_NR_Placa_Carreta");
			String DM_Situacao = request.getParameter ("FT_DM_Situacao");			
			// Monta a parte fixa que veio do bean da tela de filtro

			if (JavaUtil.doValida (OID_Unidade)){				
				EDFiltro.setOID_Unidade(new Long(OID_Unidade).longValue());
			}
			if (JavaUtil.doValida (OID_Pessoa_Remetente)){				
				EDFiltro.setOID_Pessoa(OID_Pessoa_Remetente);
			}
			if (JavaUtil.doValida (OID_Pessoa_Destinatario)){				
				EDFiltro.setOID_Pessoa_Destinatario(OID_Pessoa_Destinatario);
			}
			if (JavaUtil.doValida (NR_Coleta)){				
				EDFiltro.setNR_Coleta(NR_Coleta);
			}
			if (JavaUtil.doValida (DM_Tipo_Coleta)){				
				EDFiltro.setDM_Tipo_Coleta(DM_Tipo_Coleta);
			}
			if (JavaUtil.doValida (DT_Emissao)){				
				EDFiltro.setDT_Emissao(DT_Emissao);
			}
			if (JavaUtil.doValida (DT_Emissao_Final)){				
				EDFiltro.setDT_Emissao_Final(DT_Emissao_Final);
			}
			if (JavaUtil.doValida (OID_Veiculo)){				
				EDFiltro.setOID_Veiculo(OID_Veiculo);
			}
			if (JavaUtil.doValida (NR_Placa_Carreta)){				
				EDFiltro.setOID_Carreta(NR_Placa_Carreta);
			}
			if (JavaUtil.doValida (DM_Situacao)){				
				EDFiltro.setDM_Situacao(DM_Situacao);
			}
			
			
			listColeta = new ColetaBD(this.sql).Relatorio_Lista_Filtro(EDFiltro);
			int QTD = 0;
			
			for (int i=0;i<listColeta.size();i++) { 
				ColetaED ColetaLst = (ColetaED)listColeta.get(i); 
				ColetaED Relatorio = new ColetaED(); 
				QTD++;

				//****** MONTA OS CAMPOS QUE VÃO PARA O RELATÓRIO ****** 
				Relatorio.setDT_Emissao(FormataData.formataDataBT(ColetaLst.getDT_Emissao()));
				Relatorio.setNR_Peso(ColetaLst.getNR_Peso());				
				Relatorio.setNM_Razao_Social(ColetaLst.getNM_Razao_Social());				
				Relatorio.setNM_Razao_Social_Unidade(ColetaLst.getNM_Razao_Social_Unidade());
				Relatorio.setCD_Unidade(ColetaLst.getCD_Unidade());
				Relatorio.setOID_Coleta(ColetaLst.getOID_Coleta());				
				Relatorio.setNR_Conhecimento(QTD);

				// TESTE PARA TRATAR NULL MOTORISTA				
				if (JavaUtil.doValida (ColetaLst.getNM_Razao_Social_Destinatario())){
					Relatorio.setNM_Razao_Social_Destinatario(ColetaLst.getNM_Razao_Social_Destinatario());
				}else{
					Relatorio.setNM_Razao_Social_Destinatario("    ( SEM REGISTRO )");
				}
				// TESTE PARA TRATAR NULL MOTORISTA				
				if (JavaUtil.doValida (ColetaLst.getNM_Motorista())){
					Relatorio.setNM_Motorista(ColetaLst.getNM_Motorista());
				}else{
					Relatorio.setNM_Motorista("    ( SEM REGISTRO )");
				}
				// TESTE PARA TRATAR NULL NÚMERO DA COLETA				
				if (JavaUtil.doValida (ColetaLst.getNR_Coleta())){
					Relatorio.setNR_Coleta(ColetaLst.getNR_Coleta());
				}else{
					Relatorio.setNR_Coleta("( -------- )");
				}
				// TESTE PARA TRATAR NULL PLACA DA VEÍCULO				
				if (JavaUtil.doValida (ColetaLst.getOID_Veiculo())){
					Relatorio.setOID_Veiculo(ColetaLst.getOID_Veiculo());
				}else{
					Relatorio.setOID_Veiculo(" -------- ");
				}
				// TESTE PARA TRATAR NULL PLACA DA CARRETA				
				if (JavaUtil.doValida (ColetaLst.getOID_Veiculo())){
					Relatorio.setOID_Carreta(ColetaLst.getOID_Carreta());
				}else{
					Relatorio.setOID_Carreta(" -------- ");
				}
				// SITUAÇÃO DA COLETA
			    if ("C".equals(ColetaLst.getDM_Situacao())) {
			    	Relatorio.setNM_Situacao ("**CANCELADA**");
			    }else {
		            if ("1".equals(ColetaLst.getDM_Situacao())) {
		            	Relatorio.setNM_Situacao("À REALIZAR");
		            }
		            if ("2".equals(ColetaLst.getDM_Situacao())) {
		        	    Relatorio.setNM_Situacao("ALOCADA");
		            }
		            if ("3".equals(ColetaLst.getDM_Situacao())) {
		        	    Relatorio.setNM_Situacao("REALIZADA");
		            }
		            if ("4".equals(ColetaLst.getDM_Situacao())) {
		        	    Relatorio.setNM_Situacao("REMARCADA");
		            }
	            }
				
				listColetaRel.add(Relatorio); 
			}
			//if (listColetaRel.size() > 0 ) {
				ed.setLista(listColetaRel); // Joga a lista de movimentos no ed para enviar pro relatório 
				ed.setResponse(response);
				ed.setNomeRelatorio("Relatorio_Coleta001"); // Seta o nome do relatório
				new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
			//}
		} 
		finally { 
			this.fimTransacao(false);
		}
	}
	
	
	
	public void deleta(ColetaED ed) throws Excecoes{

		try{
			this.inicioTransacao();
			ColetaBD = new ColetaBD(this.sql);
			ColetaBD.deleta(ed);
			this.fimTransacao(true);
		}
		catch(Excecoes exc){
			this.abortaTransacao();
			throw exc;
		}catch(Exception e){
			Excecoes excecoes = new Excecoes();
			excecoes.setClasse(this.getClass().getName());
			excecoes.setMensagem("Erro ao excluir");
			excecoes.setMetodo("deleta(ColetaED ed)");
			excecoes.setExc(e);
			//faz rollback pois deu algum erro
			this.abortaTransacao();
			throw excecoes;
		}
	}

}
