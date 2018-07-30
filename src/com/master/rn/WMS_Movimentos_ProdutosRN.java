package com.master.rn;

/**
 * Título: WMS_Movimentos_ProdutosRN
 * Descrição: Movimentos - RN
 * Data da criação: 12/2003
 * Atualizado em: 12/2003
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import com.master.bd.WMS_Movimentos_ProdutosBD;
import com.master.ed.WMS_Movimentos_ProdutosED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class WMS_Movimentos_ProdutosRN extends Transacao  {
  WMS_Movimentos_ProdutosBD WMS_Movimentos_ProdutosBD = null;


  public WMS_Movimentos_ProdutosRN() {
    //WMS_Movimentos_Produtosbd = new WMS_Movimentos_ProdutosBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public WMS_Movimentos_ProdutosED inclui(WMS_Movimentos_ProdutosED ed)throws Excecoes{

    WMS_Movimentos_ProdutosED WMS_Movimentos_ProdutosED = new WMS_Movimentos_ProdutosED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      WMS_Movimentos_ProdutosBD = new WMS_Movimentos_ProdutosBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      WMS_Movimentos_ProdutosED = WMS_Movimentos_ProdutosBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);
    }
    catch(Excecoes e){throw e;}

    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Movimentação");
      excecoes.setMetodo("Inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return WMS_Movimentos_ProdutosED;
  }

/********************************************************
 *
 *******************************************************/
  public void altera(WMS_Movimentos_ProdutosED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      WMS_Movimentos_ProdutosBD = new WMS_Movimentos_ProdutosBD(this.sql);
      WMS_Movimentos_ProdutosBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Movimentação");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public void deleta(WMS_Movimentos_ProdutosED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      WMS_Movimentos_ProdutosBD = new WMS_Movimentos_ProdutosBD(this.sql);
      WMS_Movimentos_ProdutosBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Movimentação");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public WMS_Movimentos_ProdutosED getByRecord(WMS_Movimentos_ProdutosED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      WMS_Movimentos_ProdutosED edVolta = new WMS_Movimentos_ProdutosED();
      //atribui ao ed de retorno
      edVolta = new WMS_Movimentos_ProdutosBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista(WMS_Movimentos_ProdutosED ed, String orderby)throws Excecoes{
      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new WMS_Movimentos_ProdutosBD(sql).lista(ed,orderby);
      this.fimTransacao(false);

      return lista;
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList getAll()throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new WMS_Movimentos_ProdutosBD(sql).getAll();
      this.fimTransacao(false);
      return lista;
  }
/********************************************************
 *
 *******************************************************/
  public void geraRelatorio(WMS_Movimentos_ProdutosED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    WMS_Movimentos_ProdutosBD = new WMS_Movimentos_ProdutosBD(this.sql);
    WMS_Movimentos_ProdutosBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}