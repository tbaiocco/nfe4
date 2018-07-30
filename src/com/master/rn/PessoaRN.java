package com.master.rn;

/**
 * <p>Title: Cadastro de Pessoas </p>
 * <p>Description: Cadastro completo de todos os tipos de pessoas físicas e
 * jurídicas, além da manutençao do cadastro</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Delta Guia Logistica</p>
 * @author Claudia Galmarini Welter
 * @version 1.0
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


import com.master.bd.OrigemBD;
import com.master.bd.PessoaBD;
import com.master.ed.OrigemED;
import com.master.ed.PessoaED;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

public class PessoaRN extends Transacao  {
  PessoaBD PessoaBD = null;

  public PessoaRN() {
  }

  public boolean inclui(PessoaED ed)throws Excecoes{

     // //// System.out.println("Pessoa 4 ");

    PessoaED PessoaED = new PessoaED();
    Excecoes excecoes = new Excecoes();
    boolean fim = false;

   try{
      this.inicioTransacao();
      PessoaBD = new PessoaBD(this.sql);

       if(PessoaBD.inclui(ed)){

          this.fimTransacao(true);
          fim = true;
       }
       else throw excecoes;
    }
    catch(Exception e){
      this.abortaTransacao();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Pessoa");
      excecoes.setMetodo("Inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return fim;
  }

  public PessoaED getByRecord(PessoaED ed) throws Excecoes{

      this.inicioTransacao();
      PessoaED edPessoa = new PessoaED();
      edPessoa = new PessoaBD(this.sql).getByRecord(ed);
      this.fimTransacao(false);

      return edPessoa;
  }

  public void getByRecord(PessoaED ed, HttpServletRequest request, String nmObj) throws Excecoes {

		try {
			this.inicioTransacao();
			PessoaED edPessoa = new PessoaBD(this.sql).getByRecord(ed);
			request.setAttribute(nmObj, !JavaUtil.doValida(edPessoa.getOid_Pessoa()) ? null : edPessoa);
		} finally {
			this.fimTransacao(false);
		}
	}

  public boolean altera(PessoaED ed)throws Excecoes{

    PessoaED PessoaED = new PessoaED();
    Excecoes excecoes = new Excecoes();
    boolean fim = false;

   try{
      this.inicioTransacao();
      PessoaBD = new PessoaBD(this.sql);
       if(PessoaBD.altera(ed)){
          this.fimTransacao(true);
          fim = true;
       }
       else throw excecoes;
    }
    catch(Exception e){
      this.abortaTransacao();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Pessoa");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }
   return fim;
  }

  public void deleta(PessoaED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      PessoaBD = new PessoaBD(this.sql);
      PessoaBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Pessoa");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

  public ArrayList lista(PessoaED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new PessoaBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

/********************************************************
 *
 *******************************************************
  public PessoaED getByRecord(PessoaED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      PessoaED edVolta = new PessoaED();
      //atribui ao ed de retorno
      edVolta = new PessoaBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }*/

}