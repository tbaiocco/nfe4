/*
 * Created on 04/07/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.master.rn;

/**
 * @author Jonas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.util.ArrayList;

import com.master.bd.ChamadoBD;
import com.master.ed.ChamadoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class ChamadoRN extends Transacao{
	ChamadoBD chamadoBD = null;
	
	
	public ChamadoRN(){
		
	}
	
	public ChamadoED inclui(ChamadoED ed)throws Excecoes{

	    ChamadoED chamadoED = new ChamadoED();

	    try{

	      this.inicioTransacao();
	      chamadoBD = new ChamadoBD(this.sql);
	      chamadoED = chamadoBD.inclui(ed);
	      this.fimTransacao(true);

	    }

	    catch(Excecoes exc){
	    this.abortaTransacao();
	    throw exc;}

	      catch(Exception e){
	        Excecoes excecoes = new Excecoes();
	        excecoes.setClasse(this.getClass().getName());
	        excecoes.setMensagem("Erro ao incluir Chamado");
	        excecoes.setMetodo("inclui");
	        excecoes.setExc(e);
	        this.abortaTransacao();

	        throw excecoes;
	      }

	      return chamadoED;
	  }
	
	
	public ChamadoED altera(ChamadoED ed)throws Excecoes{
		
		// System.out.println("#################chegou rn#####################");
		
	    ChamadoED chamadoED = new ChamadoED();

	    try{

	      this.inicioTransacao();
	      chamadoBD = new ChamadoBD(this.sql);
	      chamadoED = chamadoBD.altera(ed);
	      this.fimTransacao(true);

	    }

	    catch(Excecoes exc){
	    this.abortaTransacao();
	    throw exc;}

	      catch(Exception e){
	        Excecoes excecoes = new Excecoes();
	        excecoes.setClasse(this.getClass().getName());
	        excecoes.setMensagem("Erro ao incluir Chamado");
	        excecoes.setMetodo("inclui");
	        excecoes.setExc(e);
	        this.abortaTransacao();

	        throw excecoes;
	      }

	      return chamadoED;
	  }

	  public void deleta(ChamadoED ed)throws Excecoes{

	    if (String.valueOf(ed.getOID_Chamado()).compareTo("") == 0){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Código do Chamado não informado !!!");
	      throw exc;
	    }

	    try{

	      this.inicioTransacao();

	      ChamadoBD chamadoBD = new ChamadoBD(this.sql);
	      chamadoBD.deleta(ed);

	      this.fimTransacao(true);

	    }
	    catch(Exception e){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Erro de exclusão");

	      this.abortaTransacao();

	      throw exc;
	    }

	  }

	  public ArrayList lista(ChamadoED ed)throws Excecoes{

	      this.inicioTransacao();
	      ArrayList lista = new ChamadoBD(sql).lista(ed);
	      this.fimTransacao(false);
	      return lista;
	  }

	  public ChamadoED getByRecord(ChamadoED ed)throws Excecoes{

	  	this.inicioTransacao();

	      ChamadoED edVolta = new ChamadoED();
	    
	      edVolta = new ChamadoBD(this.sql).getByRecord(ed);
	    
	      this.fimTransacao(false);

	      return edVolta;
	  }

}
