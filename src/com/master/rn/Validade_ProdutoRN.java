package com.master.rn;

/**
 * Título: Validade_ProdutoRN
 * Descrição: Produtos - RN
 * Data da criação: 10/2003
 * Atualizado em: 02/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import com.master.bd.Validade_ProdutoBD;
import com.master.ed.Validade_ProdutoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Validade_ProdutoRN extends Transacao  {
  Validade_ProdutoBD Validade_ProdutoBD = null;


  public Validade_ProdutoRN() {
    //Validade_ProdutoBD = new Validade_ProdutoBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public Validade_ProdutoED inclui(Validade_ProdutoED ed)throws Excecoes{

    Validade_ProdutoED Validade_ProdutoED = new Validade_ProdutoED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Validade_ProdutoBD = new Validade_ProdutoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro

      Validade_ProdutoED = Validade_ProdutoBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);
    }
    catch(Excecoes e){throw e;}

    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Operador");
      excecoes.setMetodo("Inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return Validade_ProdutoED;
  }

/********************************************************
 *
 *******************************************************/
  public void altera(Validade_ProdutoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Validade_ProdutoBD = new Validade_ProdutoBD(this.sql);
      Validade_ProdutoBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Operador");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public void deleta(Validade_ProdutoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Validade_ProdutoBD = new Validade_ProdutoBD(this.sql);
      Validade_ProdutoBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Operador");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public Validade_ProdutoED getByRecord(Validade_ProdutoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Validade_ProdutoED edVolta = new Validade_ProdutoED();
      //atribui ao ed de retorno
      edVolta = new Validade_ProdutoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }



  public ArrayList lista(Validade_ProdutoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Validade_ProdutoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }


}