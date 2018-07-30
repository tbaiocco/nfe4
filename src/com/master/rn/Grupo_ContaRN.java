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

import com.master.bd.Grupo_ContaBD;
import com.master.ed.Grupo_ContaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Grupo_ContaRN extends Transacao  {
  Grupo_ContaBD Grupo_ContaBD = null;


  public Grupo_ContaRN() {
    //Grupo_Contabd = new Grupo_ContaBD(this.sql);
  }

  public Grupo_ContaED inclui(Grupo_ContaED ed)throws Excecoes{

    Grupo_ContaED grupo_ContaED = new Grupo_ContaED();

    try{

      this.inicioTransacao();

      Grupo_ContaBD = new Grupo_ContaBD(this.sql);

      grupo_ContaED = Grupo_ContaBD.inclui(ed);

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

    return grupo_ContaED;

  }

  public void altera(Grupo_ContaED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Grupo_ContaBD = new Grupo_ContaBD(this.sql);
      Grupo_ContaBD.altera(ed);

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

  public void deleta(Grupo_ContaED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Grupo_ContaBD = new Grupo_ContaBD(this.sql);
      Grupo_ContaBD.deleta(ed);

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

  public ArrayList lista(Grupo_ContaED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Grupo_ContaBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Grupo_ContaED getByRecord(Grupo_ContaED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Grupo_ContaED edVolta = new Grupo_ContaED();
      //atribui ao ed de retorno
      edVolta = new Grupo_ContaBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(Grupo_ContaED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Grupo_ContaBD = new Grupo_ContaBD(this.sql);
    Grupo_ContaBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}