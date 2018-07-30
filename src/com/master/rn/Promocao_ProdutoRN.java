package com.master.rn;

/**
 * Título: Promocao_ProdutoRN
 * Descrição: Produtos - RN
 * Data da criação: 10/2003
 * Atualizado em: 02/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import com.master.bd.Promocao_ProdutoBD;
import com.master.ed.Promocao_ProdutoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Promocao_ProdutoRN extends Transacao  {
  Promocao_ProdutoBD Promocao_ProdutoBD = null;


  public Promocao_ProdutoRN() {
    //Promocao_ProdutoBD = new Promocao_ProdutoBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public Promocao_ProdutoED inclui(Promocao_ProdutoED ed)throws Excecoes{

  	 Promocao_ProdutoED Promocao_ProdutoED = new Promocao_ProdutoED();

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Promocao_ProdutoBD = new Promocao_ProdutoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro

      Promocao_ProdutoED = Promocao_ProdutoBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

      return Promocao_ProdutoED;
  }

/********************************************************
 *
 *******************************************************/
  public void altera(Promocao_ProdutoED ed)throws Excecoes{

      this.inicioTransacao();

      Promocao_ProdutoBD = new Promocao_ProdutoBD(this.sql);
      Promocao_ProdutoBD.altera(ed);

      this.fimTransacao(true);
  }

/********************************************************
 *
 *******************************************************/
  public void deleta(Promocao_ProdutoED ed)throws Excecoes{

      this.inicioTransacao();

      Promocao_ProdutoBD = new Promocao_ProdutoBD(this.sql);
      Promocao_ProdutoBD.deleta(ed);

      this.fimTransacao(true);
  }

/********************************************************
 *
 *******************************************************/
  public Promocao_ProdutoED getByRecord(Promocao_ProdutoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Promocao_ProdutoED edVolta = new Promocao_ProdutoED();
      //atribui ao ed de retorno
      edVolta = new Promocao_ProdutoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }
  
  public Promocao_ProdutoED getByUltDataVigencia(Promocao_ProdutoED ed)throws Excecoes{

      this.inicioTransacao();
      ed = new Promocao_ProdutoBD(this.sql).getByUltDataVigencia(ed);
      this.fimTransacao(false);
      return ed;
  }

  public ArrayList lista(Promocao_ProdutoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Promocao_ProdutoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }
}