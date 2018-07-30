package com.master.rn;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.EDI_SincoBD;
import com.master.ed.EDI_SincoED;
import com.master.ed.Movimento_ContabilED;
import com.master.util.Excecoes;
import com.master.util.ManipulaArquivo2;
import com.master.util.ManipulaString;
import com.master.util.bd.Transacao;
import com.master.util.SeparaEndereco;

public class EDI_SincoRN extends Transacao {

	public EDI_SincoRN() {
	}

	public ArrayList gera_Arquivo_EDI_Sinco_Movimento(EDI_SincoED sincoED, HttpServletRequest request, HttpServletResponse response)throws Excecoes{
	      
		  ArrayList lista = null;
	
	      this.inicioTransacao();
	      lista = new EDI_SincoBD(sql).gera_Arquivo_EDI_Sinco_Movimento(sincoED);
	      this.fimTransacao(false);
	            
	      ManipulaArquivo2 man = new ManipulaArquivo2("","Arquivo_EDI_Sinco_Movimento");
	      
	      String linha = "";
	      
	      int n, i;
	      if ((n = lista.size()) != 0) {
	    	  for (i = 0; i < n; i++) {
		    	  EDI_SincoED edVolta = new EDI_SincoED();
		          edVolta = (EDI_SincoED) lista.get(i);
		    	
			      linha = String.valueOf(edVolta.getDt_Lancamento().substring(0,2)); //dia mes ano
				  linha+= String.valueOf(edVolta.getDt_Lancamento().substring(3,5));
				  linha+= String.valueOf(edVolta.getDt_Lancamento().substring(6,10));
			      linha+= String.valueOf(ManipulaString.formataTamanho(edVolta.getCd_Conta(),28));
			      linha+= String.valueOf(ManipulaString.formataTamanho(edVolta.getCd_Centro_Custo(),28));
			      linha+= String.valueOf(ManipulaString.formataTamanho(edVolta.getCd_Contra_Partida(),28));        
			      edVolta.setVl_Lancamento(String.valueOf(ManipulaString.alinhaNumeroDireita(edVolta.getVl_Lancamento(),18)));
				  linha+= String.valueOf(edVolta.getVl_Lancamento().substring(0,15));
				  linha+= String.valueOf(edVolta.getVl_Lancamento().substring(16,18));
			      linha+= String.valueOf(ManipulaString.formataTamanho(edVolta.getDm_Tipo_Debito_Credito(),1));
				  linha+= String.valueOf(ManipulaString.formataTamanho(null,12));
			      linha+= String.valueOf(ManipulaString.formataTamanho(null,12));
				  linha+= String.valueOf(ManipulaString.formataTamanho(edVolta.getNm_Historico_Lancamento(),150));
			      man.insereValor(linha);
			      man.insereQuebra();
	    	  } //do for
	      man.enviaBytes(request, response);
	      }
	      
	      return lista;
	}
  
	public ArrayList gera_Arquivo_EDI_Sinco_Saldos(EDI_SincoED sincoED, HttpServletRequest request, HttpServletResponse response)throws Excecoes{
	      
		  ArrayList lista = new ArrayList ();
		  ArrayList lista2 = new ArrayList ();
		  
		  
		  
		  Movimento_ContabilED mcED = new Movimento_ContabilED(); 
		  Movimento_ContabilRN mcRN = new Movimento_ContabilRN();
		 
		  mcED.setDt_Movimento_Inicial(sincoED.getDT_Inicial());
		  mcED.setDt_Movimento_Final(sincoED.getDT_Final());
		  mcED.setOid_Unidade(sincoED.getOID_Unidade());
		  
		  mcRN.balanceteVerificacao(mcED, request, response, "A");
		  
		  lista = (ArrayList) request.getAttribute("listaBalancete") ;
		  
	      this.inicioTransacao();
	      
	      for (int i=0;i<lista.size();i++){
	    	Movimento_ContabilED movLst = (Movimento_ContabilED)lista.get(i);
	      	EDI_SincoED sEDc = new EDI_SincoED();
	      	
	      	sEDc.setDT_Inicial(sincoED.getDT_Inicial());
	      	sEDc.setDT_Final(sincoED.getDT_Final());
	      	sEDc.setOID_Unidade(sincoED.getOID_Unidade());
	      	sEDc.setDM_Operacao(sincoED.getDM_Operacao());
	      	sEDc.setCd_Conta(movLst.getCd_Estrutural()+(" ")+movLst.getCd_Conta());
	      	sEDc.setVl_Saldo_Inicial(SeparaEndereco.formataNumeroParaTxt(movLst.getVl_Saldo(),18,2)); //----- FORMATA SALDO INICIAL -----\\

	      	if (movLst.getVl_Saldo()<0) 
	      		sEDc.setDm_Tipo_Saldo_Inicial("C");  
	      	else 
	      		sEDc.setDm_Tipo_Saldo_Inicial("D");  
	      		      	
	      	sEDc.setVl_Total_Debito(SeparaEndereco.formataNumeroParaTxt(movLst.getVl_Debito(),18,2)); //----- FORMATA TOTAL DEBITO -----\\
	      	sEDc.setVl_Total_Credito(SeparaEndereco.formataNumeroParaTxt(movLst.getVl_Credito(),18,2)); //----- FORMATA TOTAL CREDITO -----\\
	      	
	      	double saldo_Final = movLst.getVl_Saldo() + movLst.getVl_Debito() - movLst.getVl_Credito();
	      	sEDc.setVl_Saldo_Final(SeparaEndereco.formataNumeroParaTxt(saldo_Final,18,2)); //----- FORMATA SALDO FINAL -----\\
	      	
	      	if (saldo_Final<0)
	      		sEDc.setDm_Tipo_Saldo_Final("C");
	      	else
	      		sEDc.setDm_Tipo_Saldo_Final("D");
	      	
	      	
	      	lista2.add(sEDc);
	      }
	      this.fimTransacao(false);
	            
	      ManipulaArquivo2 man = new ManipulaArquivo2("","Arquivo_EDI_Sinco_Lancamento.txt");
	      
	      String linha = "";
	      
	      int n, i;
	      if ((n = lista2.size()) != 0) {
		      for (i = 0; i < n; i++) {
		        	EDI_SincoED edVolta = new EDI_SincoED();
		            edVolta = (EDI_SincoED) lista2.get(i);
		            
		            linha = String.valueOf(edVolta.getDT_Inicial().substring(0,2)); //dia mes ano
					linha+= String.valueOf(edVolta.getDT_Inicial().substring(3,5));
					linha+= String.valueOf(edVolta.getDT_Inicial().substring(6,10));
				    
				    linha+= String.valueOf(ManipulaString.formataTamanho(edVolta.getCd_Conta(),28));
				   
				    linha+= String.valueOf(edVolta.getVl_Saldo_Inicial());
					
					linha+= String.valueOf(ManipulaString.formataTamanho(edVolta.getDm_Tipo_Saldo_Inicial(),1));
					
					linha+= String.valueOf(edVolta.getVl_Total_Debito());
					
					linha+= String.valueOf(edVolta.getVl_Total_Credito());
					
					linha+= String.valueOf(edVolta.getVl_Saldo_Final());
					
					linha+= String.valueOf(ManipulaString.formataTamanho(edVolta.getDm_Tipo_Saldo_Final(),1));
					
		            man.insereValor(linha);
		            man.insereQuebra();
		      } //do for
	      man.enviaBytes(request, response);
	      }
	      return lista2;
	}

	public ArrayList gera_Arquivo_EDI_Sinco_Conta(EDI_SincoED sincoED, HttpServletRequest request, HttpServletResponse response)throws Excecoes{
	    
		  ArrayList lista = null;
	
		  this.inicioTransacao();
		  lista = new EDI_SincoBD(sql).gera_Arquivo_EDI_Sinco_Conta(sincoED);
		  this.fimTransacao(false);
		          
		  ManipulaArquivo2 man = new ManipulaArquivo2("","Arquivo_EDI_Sinco_Conta");
		   
		  String linha = "";
		    
		  int n, i;
		  if ((n = lista.size()) != 0) {
			  for (i = 0; i < n; i++) {
			      EDI_SincoED edVolta = new EDI_SincoED();
			      edVolta = (EDI_SincoED) lista.get(i);
			
			      linha = String.valueOf(edVolta.getDT_Inicial().substring(0,2)); //dia mes ano
			      linha+= String.valueOf(edVolta.getDT_Inicial().substring(3,5));
			      linha+= String.valueOf(edVolta.getDT_Inicial().substring(6,10));
			      linha+= String.valueOf(ManipulaString.formataTamanho(edVolta.getCd_Conta(),28));
			      linha+= String.valueOf(ManipulaString.formataTamanho(edVolta.getDm_Tipo_Conta(),1));
			      linha+= String.valueOf(ManipulaString.formataTamanho(edVolta.getCd_Contabil(),28));
			      linha+= String.valueOf(ManipulaString.formataTamanho(edVolta.getNm_Conta(),45));
			      man.insereValor(linha);
			      man.insereQuebra();
		      } //do for
		  man.enviaBytes(request, response);
		  }
		  return lista;
	}
		
}

