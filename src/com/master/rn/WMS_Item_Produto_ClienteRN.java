package com.master.rn;

/**
 * Título: WMS_Item_Produto_ClienteRN
 * Descrição: Itens da Nota Fiscal - RN
 * Data da criação: 11/2003
 * Atualizado em: 03/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import com.master.bd.WMS_Item_Produto_ClienteBD;
import com.master.ed.WMS_Item_Produto_ClienteED;
import com.master.ed.WMS_Nota_FiscalED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class WMS_Item_Produto_ClienteRN extends Transacao  {
  WMS_Item_Produto_ClienteBD WMS_Item_Produto_ClienteBD = null;


  public WMS_Item_Produto_ClienteRN() {
    //WMS_Item_Produto_ClienteBD = new WMS_Item_Produto_ClienteBD(this.sql);
  }

  public void inclui(WMS_Item_Produto_ClienteED ed)throws Excecoes{

    if (ed.getOid_Nota_Fiscal().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("OID da NF não foi informado !!!");
      throw exc;
    }

    try{


      if ( ed.getVl_Produto() ==0.00 ){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Valor do produto menor ou igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("inclui(WMS_Item_Produto_ClienteED ed)");
        throw exc;
      }

      if (ed.getNr_Quantidade_Movimento() <= 0){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Quantidade de do item nota fiscal menor ou igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("inclui(WMS_Item_Produto_ClienteED ed)");
        throw exc;
      }

      this.inicioTransacao();

      new WMS_Item_Produto_ClienteBD(this.sql).inclui(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public void altera(WMS_Item_Produto_ClienteED ed)throws Excecoes{

    if (String.valueOf(ed.getOid_Item_Produto_Cliente()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Item_Produto_Cliente_Transacoes não foi informado !!!");
      throw exc;
    }

    try{


      if ( ed.getVl_Produto() == 0.00 ){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Valor do produto igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("inclui(WMS_Item_Produto_ClienteED ed)");
        throw exc;
      }

      if (ed.getNr_Quantidade_Movimento() <= 0){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Quantidade do item nota fiscal menor ou igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("inclui(WMS_Item_Produto_ClienteED ed)");
        throw exc;
      }

      this.inicioTransacao();

      WMS_Item_Produto_ClienteBD = new WMS_Item_Produto_ClienteBD(this.sql);
      WMS_Item_Produto_ClienteBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public void alteraPelaMovimentacao(WMS_Item_Produto_ClienteED ed) throws Excecoes{

    if (String.valueOf(ed.getOid_Item_Produto_Cliente()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Item_Produto_Cliente_Transacoes não foi informado !!!");
      throw exc;
    }

    try{
      if (ed.getNr_Quantidade_Devolucao() <= 0){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Quantidade de volumes do item nota fiscal menor ou igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("inclui(WMS_Item_Produto_ClienteED ed)");
        throw exc;
      }

      this.inicioTransacao();

      WMS_Item_Produto_ClienteBD = new WMS_Item_Produto_ClienteBD(this.sql);
      WMS_Item_Produto_ClienteBD.alteraPelaMovimentacao(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alteraPelaMovimentacao");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public void deleta(WMS_Item_Produto_ClienteED ed)throws Excecoes{

    if (String.valueOf(ed.getOid_Item_Produto_Cliente()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Item_Produto_Cliente_Transacoes não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      new WMS_Item_Produto_ClienteBD(this.sql).deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir");
      excecoes.setMetodo("excluir");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  }
  
  public ArrayList lista(WMS_Item_Produto_ClienteED ed, String orderby)throws Excecoes{
        
      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new WMS_Item_Produto_ClienteBD(sql).lista(ed, orderby);
      this.fimTransacao(false);
      return lista;
  }

  public WMS_Item_Produto_ClienteED getByRecord(WMS_Item_Produto_ClienteED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      WMS_Item_Produto_ClienteED edVolta = new WMS_Item_Produto_ClienteED();
      //atribui ao ed de retorno
      edVolta = new WMS_Item_Produto_ClienteBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public WMS_Item_Produto_ClienteED Reverter( String oid )throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      WMS_Item_Produto_ClienteED edVolta = new WMS_Item_Produto_ClienteED();
      //atribui ao ed de retorno
      edVolta = new WMS_Item_Produto_ClienteBD(this.sql).Reverter( oid );
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public boolean itemJaIncluso( String oid_produto_cliente, String oid_nota_fiscal )throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      boolean bol = false;
      //atribui ao ed de retorno
      bol = new WMS_Item_Produto_ClienteBD(this.sql).itemJaIncluso( oid_produto_cliente, oid_nota_fiscal );
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return bol;
  }

/********************************************************
 *
 *******************************************************/    
  public String verificaItens(WMS_Item_Produto_ClienteED edI, WMS_Nota_FiscalED edN) throws Excecoes{
      this.inicioTransacao();

      WMS_Item_Produto_ClienteBD = new WMS_Item_Produto_ClienteBD(this.sql);
      String retorno = WMS_Item_Produto_ClienteBD.verificaItens(edI,edN);

      this.fimTransacao(true);  
      
      return retorno;
  }
  
  public String isInclui(String oid) throws Excecoes{
      this.inicioTransacao();

      WMS_Item_Produto_ClienteBD = new WMS_Item_Produto_ClienteBD(this.sql);
      String retorno = WMS_Item_Produto_ClienteBD.isInclui(oid);

      this.fimTransacao(false);  
      
      return retorno;
  }
  
/********************************************************
 *
 *******************************************************/    
  public void atualiza_Notas(WMS_Item_Produto_ClienteED ed) throws Excecoes{
      this.inicioTransacao();

      WMS_Item_Produto_ClienteBD = new WMS_Item_Produto_ClienteBD(this.sql);
      WMS_Item_Produto_ClienteBD.atualiza_Notas(ed);

      this.fimTransacao(true);  
  }

  public ArrayList listaItemPedido(WMS_Item_Produto_ClienteED ed)throws Excecoes{
	  ArrayList list = new ArrayList();
	  try{
		  this.inicioTransacao();
		  WMS_Item_Produto_ClienteBD = new WMS_Item_Produto_ClienteBD(this.sql);
		  list = WMS_Item_Produto_ClienteBD.listaItemPedido(ed);
		  this.fimTransacao(true);
		  return list;
	  }
	  catch(Excecoes exc){
		  this.abortaTransacao();
		  throw exc;}
	  catch(Exception e){
		  Excecoes excecoes = new Excecoes();
		  excecoes.setClasse(this.getClass().getName());
		  excecoes.setMensagem("Erro ao excluir");
		  excecoes.setMetodo("excluir");
		  excecoes.setExc(e);
		  //faz rollback pois deu algum erro
		  this.abortaTransacao();
		  throw excecoes;
	  }
  }

  public String devolveItem(String pOid_Produto_Cliente, double pNr_Quantidade_Saida) throws Excecoes{
	  String volta =""; 
	  try{
		  this.inicioTransacao();
		  volta = new WMS_Item_Produto_ClienteBD(this.sql).devolveItem( pOid_Produto_Cliente,  pNr_Quantidade_Saida);
		  this.fimTransacao(true);
		  return volta;
	  }
	  catch(Excecoes exc){
		  this.abortaTransacao();
		  throw exc;}
	  catch(Exception e){
		  Excecoes excecoes = new Excecoes();
		  excecoes.setClasse(this.getClass().getName());
		  excecoes.setMensagem("Problemas com devolveItem()");
		  excecoes.setMetodo("devolveItem");
		  excecoes.setExc(e);
		  this.abortaTransacao();
		  throw excecoes;
	  }
  }

}



