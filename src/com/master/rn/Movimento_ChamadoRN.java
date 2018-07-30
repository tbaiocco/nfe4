/*
 * Created on 05/07/2006
 */
package com.master.rn;

/**
 * by Jonas
 **/

import java.util.ArrayList;
import com.master.bd.Movimento_ChamadoBD;
import com.master.ed.Movimento_ChamadoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Movimento_ChamadoRN extends Transacao{

	Movimento_ChamadoBD movimento_ChamadoBD = null;

	public Movimento_ChamadoRN(){

	}

	public Movimento_ChamadoED inclui(Movimento_ChamadoED ed)throws Excecoes{

		Movimento_ChamadoED movimento_ChamadoED = new Movimento_ChamadoED();

	    try{

	      this.inicioTransacao();
	      movimento_ChamadoBD = new Movimento_ChamadoBD(this.sql);
	      movimento_ChamadoED = movimento_ChamadoBD.inclui(ed);
	      this.fimTransacao(true);

	    }

	    catch(Excecoes exc){
	    this.abortaTransacao();
	    throw exc;}

	      catch(Exception e){
	        Excecoes excecoes = new Excecoes();
	        excecoes.setClasse(this.getClass().getName());
	        excecoes.setMensagem("Erro ao incluir Movimento_Chamado");
	        excecoes.setMetodo("inclui");
	        excecoes.setExc(e);
	        this.abortaTransacao();

	        throw excecoes;
	      }

	      return movimento_ChamadoED;
	  }

	public void altera(Movimento_ChamadoED ed)throws Excecoes{

	    if (String.valueOf(ed.getOID_Movimento_Chamado()).compareTo("") == 0){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Código do Movimento_Chamado não informado !!!");
	      throw exc;
	    }

	    try{

	      this.inicioTransacao();

	      movimento_ChamadoBD = new Movimento_ChamadoBD(this.sql);
	      movimento_ChamadoBD.altera(ed);

	      this.fimTransacao(true);

	    }
	    catch(Exception e){
	    	e.printStackTrace();
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Erro de alteração");

	      this.abortaTransacao();

	      throw exc;
	    }

	  }

	public void deleta(Movimento_ChamadoED ed)throws Excecoes{

	    if (String.valueOf(ed.getOID_Movimento_Chamado()).compareTo("") == 0){
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Código do Movimento_Chamado não informado !!!");
	      throw exc;


	    }

	    try{

	      this.inicioTransacao();


	      movimento_ChamadoBD = new Movimento_ChamadoBD(this.sql);
	      movimento_ChamadoBD.deleta(ed);

	      this.fimTransacao(true);

	    }
	    catch(Exception e){
	    	e.printStackTrace();
	      Excecoes exc = new Excecoes();
	      exc.setMensagem("Erro de exclusão");

	      this.abortaTransacao();

	      throw exc;
	    }

	  }

	public ArrayList lista(Movimento_ChamadoED ed)throws Excecoes{

	      this.inicioTransacao();
	      ArrayList lista = new Movimento_ChamadoBD(sql).lista(ed);
	      this.fimTransacao(false);
	      return lista;
	  }

	  public Movimento_ChamadoED getByRecord(Movimento_ChamadoED ed)throws Excecoes{

	  	this.inicioTransacao();

	  	Movimento_ChamadoED edVolta = new Movimento_ChamadoED();

	      edVolta = new Movimento_ChamadoBD(this.sql).getByRecord(ed);

	      this.fimTransacao(false);

	      return edVolta;
	  }

}
