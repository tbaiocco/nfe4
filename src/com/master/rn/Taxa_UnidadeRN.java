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
import com.master.bd.Taxa_UnidadeBD;
import com.master.ed.Taxa_UnidadeED;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

public class Taxa_UnidadeRN extends Transacao  {
	Taxa_UnidadeBD Taxa_UnidadeBD = null;

	public Taxa_UnidadeRN() {
		//Taxa_Unidadebd = new Taxa_UnidadeBD(this.sql);
	}

  	public Taxa_UnidadeED inclui(Taxa_UnidadeED ed)throws Excecoes{
  		Taxa_UnidadeED Taxa_UnidadeED = new Taxa_UnidadeED();
  		try{
  			this.inicioTransacao();
  			Taxa_UnidadeED = new Taxa_UnidadeBD(this.sql).inclui(ed);
  			this.fimTransacao(true);
  		}
  		catch(Excecoes exc){
  			this.abortaTransacao();
  			throw exc;
  		}
  		catch(Exception e){
  			e.printStackTrace();
  			this.abortaTransacao();
     		throw new Excecoes("Erro ao Incluir",this.getClass().getName(),"inclui");
     	}
  		return Taxa_UnidadeED;
  	}

  	public void altera(Taxa_UnidadeED ed)throws Excecoes{

    	if (!JavaUtil.doValida(String.valueOf(ed.getOid_Taxa_Unidade()))){
    		Excecoes exc = new Excecoes();
    		exc.setMensagem("Código da Taxa Unidade não foi informado !!!");
    		throw exc;
    	}

    	try{
    		this.inicioTransacao();
    		new Taxa_UnidadeBD(this.sql).altera(ed);
    		this.fimTransacao(true);
    	}
    	catch(Excecoes exc){
  			this.abortaTransacao();
  			throw exc;
  		}
    	catch(Exception e){
    		e.printStackTrace();
  			this.abortaTransacao();
     		throw new Excecoes("Erro ao Alterar",this.getClass().getName(),"altera");
     	}
  	}

  	public void deleta(Taxa_UnidadeED ed)throws Excecoes{

  		if (!JavaUtil.doValida(String.valueOf(ed.getOid_Taxa_Unidade()))){
    		Excecoes exc = new Excecoes();
    		exc.setMensagem("Código da Taxa Unidade não foi informado !!!");
    		throw exc;
    	}

    	try{
    		this.inicioTransacao();
    		new Taxa_UnidadeBD(this.sql).deleta(ed);
    		this.fimTransacao(true);
    	}
    	catch(Excecoes exc){
  			this.abortaTransacao();
  			throw exc;
  		}
    	catch(Exception e){
    		e.printStackTrace();
  			this.abortaTransacao();
     		throw new Excecoes("Erro ao Excluir",this.getClass().getName(),"deleta");
     	}
  	}

  	public ArrayList lista(Taxa_UnidadeED ed)throws Excecoes{
  		//retorna um arraylist de ED´s
  		this.inicioTransacao();
  		ArrayList lista = new Taxa_UnidadeBD(sql).lista(ed);
  		this.fimTransacao(false);
      	return lista;
  	}

  	public Taxa_UnidadeED getByRecord(Taxa_UnidadeED ed)throws Excecoes{
  		//inicia conexao com bd
  		this.inicioTransacao();
      	//instancia ed de retorno
      	Taxa_UnidadeED edVolta = new Taxa_UnidadeED();
      	//atribui ao ed de retorno
      	edVolta = new Taxa_UnidadeBD(this.sql).getByRecord(ed);
      	//libera conexao nao "commitando"
      	this.fimTransacao(false);
      	return edVolta;
  	}

}