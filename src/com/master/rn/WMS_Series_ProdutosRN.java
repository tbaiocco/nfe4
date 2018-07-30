package com.master.rn;

/**
 * Título: WMS_Series_ProdutosRN
 * Descrição: Series - RN
 * Data da criação: 12/2003
 * Atualizado em: 12/2003
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import com.master.bd.WMS_Series_ProdutosBD;
import com.master.ed.WMS_Series_ProdutosED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class WMS_Series_ProdutosRN extends Transacao  {
  WMS_Series_ProdutosBD WMS_Series_ProdutosBD = null;


  public WMS_Series_ProdutosRN() {
    //WMS_Series_Produtosbd = new WMS_Series_ProdutosBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public WMS_Series_ProdutosED inclui(WMS_Series_ProdutosED ed)throws Excecoes{

    WMS_Series_ProdutosED WMS_Series_ProdutosED = new WMS_Series_ProdutosED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      WMS_Series_ProdutosBD = new WMS_Series_ProdutosBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      WMS_Series_ProdutosED = WMS_Series_ProdutosBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);
    }
    catch(Excecoes e){throw e;}

    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Nº de Série");
      excecoes.setMetodo("Inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return WMS_Series_ProdutosED;
  }

/********************************************************
 *
 *******************************************************/
  public void altera(WMS_Series_ProdutosED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      WMS_Series_ProdutosBD = new WMS_Series_ProdutosBD(this.sql);
      WMS_Series_ProdutosBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Nº de Série");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public void deleta(WMS_Series_ProdutosED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      WMS_Series_ProdutosBD = new WMS_Series_ProdutosBD(this.sql);
      WMS_Series_ProdutosBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Nº de Série");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public WMS_Series_ProdutosED getByRecord(WMS_Series_ProdutosED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      WMS_Series_ProdutosED edVolta = new WMS_Series_ProdutosED();
      //atribui ao ed de retorno
      edVolta = new WMS_Series_ProdutosBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

/********************************************************
 *
 *******************************************************/
  public int contByOid_Movimento_Produto(int oid_movimento_produto)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      int cont;
      cont = new WMS_Series_ProdutosBD(this.sql).contByOid_Movimento_Produto( oid_movimento_produto );
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return cont;
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista( WMS_Series_ProdutosED ed )throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new WMS_Series_ProdutosBD(sql).lista( ed );
      this.fimTransacao(false);
      return lista;
  }

}