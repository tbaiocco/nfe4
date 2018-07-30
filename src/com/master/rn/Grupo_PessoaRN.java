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

import com.master.bd.Grupo_PessoaBD;
import com.master.ed.Grupo_PessoaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Grupo_PessoaRN extends Transacao  {
  Grupo_PessoaBD Grupo_PessoaBD = null;


  public Grupo_PessoaRN() {
    //Grupo_Pessoabd = new Grupo_PessoaBD(this.sql);
  }

  public Grupo_PessoaED inclui(Grupo_PessoaED ed)throws Excecoes{

    Grupo_PessoaED Grupo_PessoaED = new Grupo_PessoaED();

    try{

      this.inicioTransacao();

      Grupo_PessoaBD = new Grupo_PessoaBD(this.sql);

      Grupo_PessoaED = Grupo_PessoaBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    }

    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir grupo de conta");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return Grupo_PessoaED;

  }

  public void altera(Grupo_PessoaED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Grupo_PessoaBD = new Grupo_PessoaBD(this.sql);
      Grupo_PessoaBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar grupo de conta");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

  public void deleta(Grupo_PessoaED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Grupo_PessoaBD = new Grupo_PessoaBD(this.sql);
      Grupo_PessoaBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
   }

  }

  public ArrayList lista(Grupo_PessoaED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Grupo_PessoaBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Grupo_PessoaED getByRecord(Grupo_PessoaED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Grupo_PessoaED edVolta = new Grupo_PessoaED();
      //atribui ao ed de retorno
      edVolta = new Grupo_PessoaBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(Grupo_PessoaED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Grupo_PessoaBD = new Grupo_PessoaBD(this.sql);
    Grupo_PessoaBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}