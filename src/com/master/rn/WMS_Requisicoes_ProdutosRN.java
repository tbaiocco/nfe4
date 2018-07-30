package com.master.rn;

/**
 * Título: WMS_Requisicoes_ProdutosRN
 * Descrição: Requisições - RN
 * Data da criação: 12/2003
 * Atualizado em: 12/2003
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import com.master.bd.WMS_Requisicoes_ProdutosBD;
import com.master.ed.WMS_Requisicoes_ProdutosED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class WMS_Requisicoes_ProdutosRN extends Transacao  {
  WMS_Requisicoes_ProdutosBD WMS_Requisicoes_ProdutosBD = null;


  public WMS_Requisicoes_ProdutosRN() {
    //WMS_Requisicoes_Produtosbd = new WMS_Requisicoes_ProdutosBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public WMS_Requisicoes_ProdutosED isExisteRequisicao( String oid_nota_fiscal_transacao ) throws Excecoes{

    WMS_Requisicoes_ProdutosED ed = new WMS_Requisicoes_ProdutosED();
    try{
      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      WMS_Requisicoes_ProdutosBD = new WMS_Requisicoes_ProdutosBD(this.sql);

      ed = WMS_Requisicoes_ProdutosBD.isExisteRequisicao( oid_nota_fiscal_transacao );

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("isExisteRequisicao()");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return ed;
  }

/********************************************************
 *
 *******************************************************/
  public WMS_Requisicoes_ProdutosED isExisteRequisicaoAberta( String oid_nota_fiscal_transacao ) throws Excecoes{

    WMS_Requisicoes_ProdutosED ed = new WMS_Requisicoes_ProdutosED();
    try{
      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      WMS_Requisicoes_ProdutosBD = new WMS_Requisicoes_ProdutosBD(this.sql);

      ed = WMS_Requisicoes_ProdutosBD.isExisteRequisicaoAberta( oid_nota_fiscal_transacao );

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("isExisteRequisicaoAberta()");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return ed;
  }
  
/********************************************************
 *
 *******************************************************/
  public WMS_Requisicoes_ProdutosED inclui(WMS_Requisicoes_ProdutosED ed)throws Excecoes{

    WMS_Requisicoes_ProdutosED WMS_Requisicoes_ProdutosED = new WMS_Requisicoes_ProdutosED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      WMS_Requisicoes_ProdutosBD = new WMS_Requisicoes_ProdutosBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      WMS_Requisicoes_ProdutosED = WMS_Requisicoes_ProdutosBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);
    }
    catch(Excecoes e){throw e;}

    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Requisição");
      excecoes.setMetodo("Inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return WMS_Requisicoes_ProdutosED;
  }

/********************************************************
 *
 *******************************************************/
  public void altera(WMS_Requisicoes_ProdutosED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      WMS_Requisicoes_ProdutosBD = new WMS_Requisicoes_ProdutosBD(this.sql);
      WMS_Requisicoes_ProdutosBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Requisição");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public void deleta(WMS_Requisicoes_ProdutosED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      WMS_Requisicoes_ProdutosBD = new WMS_Requisicoes_ProdutosBD(this.sql);
      WMS_Requisicoes_ProdutosBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Requisição");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public WMS_Requisicoes_ProdutosED getByRecord(WMS_Requisicoes_ProdutosED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      WMS_Requisicoes_ProdutosED edVolta = new WMS_Requisicoes_ProdutosED();
      //atribui ao ed de retorno
      edVolta = new WMS_Requisicoes_ProdutosBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista(WMS_Requisicoes_ProdutosED ed, String orderby)throws Excecoes{
      //retorna um arraylist de ED´s
      this.inicioTransacao();

      ArrayList lista = new WMS_Requisicoes_ProdutosBD(sql).lista(ed,orderby);

      this.fimTransacao(false);

      return lista;
  }

/********************************************************
 *
 *******************************************************/
  public void geraRelatorio(WMS_Requisicoes_ProdutosED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    WMS_Requisicoes_ProdutosBD = new WMS_Requisicoes_ProdutosBD(this.sql);
    WMS_Requisicoes_ProdutosBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

/********************************************************
 *
 *******************************************************/
  public boolean isFinalizada( int oid_requisicao_produto )throws Excecoes{
    this.inicioTransacao();
    WMS_Requisicoes_ProdutosBD = new WMS_Requisicoes_ProdutosBD(this.sql);
    boolean bol = true;
    bol = WMS_Requisicoes_ProdutosBD.isFinalizada( oid_requisicao_produto );
    this.fimTransacao(false);
    return bol;
  }

/********************************************************
 *
 *******************************************************/
  public void alteraSituacao( int oid_requisicao_produto, String dm_situacao ) throws Excecoes{
    this.inicioTransacao();
    WMS_Requisicoes_ProdutosBD = new WMS_Requisicoes_ProdutosBD(this.sql);
    WMS_Requisicoes_ProdutosBD.alteraSituacao( oid_requisicao_produto, dm_situacao );
    this.fimTransacao(true);
  }

}