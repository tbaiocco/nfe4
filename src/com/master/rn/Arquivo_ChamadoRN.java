/*
 * Created on 05/07/2006
 */
package com.master.rn;

/**
 * by Jonas
 **/

import java.util.ArrayList;
import com.master.bd.Arquivo_ChamadoBD;
import com.master.ed.Arquivo_ChamadoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Arquivo_ChamadoRN extends Transacao{

	Arquivo_ChamadoBD arquivo_ChamadoBD = null;
	
	public Arquivo_ChamadoRN(){
		
	}
	
	public Arquivo_ChamadoED inclui(Arquivo_ChamadoED ed)throws Excecoes{

		Arquivo_ChamadoED arquivo_ChamadoED = new Arquivo_ChamadoED();

	    try{

	      this.inicioTransacao();
	      arquivo_ChamadoBD = new Arquivo_ChamadoBD(this.sql);
	      arquivo_ChamadoED = arquivo_ChamadoBD.inclui(ed);
	      this.fimTransacao(true);

	    }

	    catch(Excecoes exc){
	    this.abortaTransacao();
	    throw exc;}

	      catch(Exception e){
	        Excecoes excecoes = new Excecoes();
	        excecoes.setClasse(this.getClass().getName());
	        excecoes.setMensagem("Erro ao incluir Arquivo_Chamado");
	        excecoes.setMetodo("inclui");
	        excecoes.setExc(e);
	        this.abortaTransacao();

	        throw excecoes;
	      }

	      return arquivo_ChamadoED;
	  }
	
	public void altera(Arquivo_ChamadoED ed)throws Excecoes{

	    if (String.valueOf(ed.getOID_Arquivo_Chamado()).compareTo("") == 0){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Código do Arquivo_Chamado não informado !!!");
	      throw exc;
	    }

	    try{

	      this.inicioTransacao();

	      arquivo_ChamadoBD = new Arquivo_ChamadoBD(this.sql);
	      arquivo_ChamadoBD.altera(ed);

	      this.fimTransacao(true);

	    }
	    catch(Exception e){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Erro de alteração");

	      this.abortaTransacao();

	      throw exc;
	    }

	  }
	
	public void deleta(Arquivo_ChamadoED ed)throws Excecoes{

	    if (String.valueOf(ed.getOID_Arquivo_Chamado()).compareTo("") == 0){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Código do Arquivo_Chamado não informado !!!");
	      throw exc;
	    }

	    try{
	    	
	      this.inicioTransacao();

	      Arquivo_ChamadoBD arquivo_ChamadoBD = new Arquivo_ChamadoBD(this.sql);	    	
	      arquivo_ChamadoBD.deleta(ed);

	      this.fimTransacao(true);

	    }
	    catch(Exception e){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Erro de exclusão");

	      this.abortaTransacao();

	      throw exc;
	    }

	  }
	
	public ArrayList lista(Arquivo_ChamadoED ed)throws Excecoes{
	      
	      this.inicioTransacao();
	      ArrayList lista = new Arquivo_ChamadoBD(sql).lista(ed);
	      this.fimTransacao(false);
	      return lista;
	  }

	  public Arquivo_ChamadoED getByRecord(Arquivo_ChamadoED ed)throws Excecoes{

	  	this.inicioTransacao();

	  	Arquivo_ChamadoED edVolta = new Arquivo_ChamadoED();
	    
	      edVolta = new Arquivo_ChamadoBD(this.sql).getByRecord(ed);
	    
	      this.fimTransacao(false);

	      return edVolta;
	  }
	
	
	
}
