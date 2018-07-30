package com.master.rn;

/**
 * Título: WMS_Nota_FiscalRN
 * Descrição: Notas Fiscais - RN
 * Data da criação: 11/2003
 * Atualizado em: 03/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import com.master.bd.WMS_Nota_FiscalBD;
import com.master.ed.WMS_Item_Produto_ClienteED;
import com.master.ed.WMS_Nota_FiscalED;
import com.master.ed.WMS_Nota_Fiscal_DevolucaoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class WMS_Nota_FiscalRN extends Transacao  {
  WMS_Nota_FiscalBD WMS_Nota_FiscalBD = null;


  public WMS_Nota_FiscalRN() {
  }

  public WMS_Nota_FiscalED inclui(WMS_Nota_FiscalED ed)throws Excecoes{
    WMS_Nota_FiscalED conED = new WMS_Nota_FiscalED();
    try
    {
	  this.inicioTransacao();
	  WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
	  conED = WMS_Nota_FiscalBD.inclui(ed);
	  ed.setOid_nota_fiscal(conED.getOid_nota_fiscal());
	  this.fimTransacao(true);
    }
    catch(Excecoes exc){
    	this.abortaTransacao();
    	throw exc;
	}
    catch(Exception e){
		Excecoes excecoes = new Excecoes();
		excecoes.setClasse(this.getClass().getName());
		excecoes.setMetodo("inclui");
		excecoes.setExc(e);
		//faz rollback pois deu algum erro
		this.abortaTransacao();
		throw excecoes;
    }

    return conED;

  }


  public void altera(WMS_Nota_FiscalED ed)throws Excecoes{
    try{

      this.inicioTransacao();

      WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
      WMS_Nota_FiscalBD.altera(ed);

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

  public void deleta(WMS_Nota_FiscalED ed)throws Excecoes{

    if (ed.getOid_nota_fiscal().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Nota Fiscal não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
      WMS_Nota_FiscalBD.deleta(ed);

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

  public ArrayList lista(WMS_Nota_FiscalED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new WMS_Nota_FiscalBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public WMS_Nota_FiscalED getByRecord(WMS_Nota_FiscalED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      WMS_Nota_FiscalED edVolta = new WMS_Nota_FiscalED();
      //atribui ao ed de retorno
      edVolta = new WMS_Nota_FiscalBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public ArrayList lista(WMS_Nota_FiscalED ed, String orderby)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new WMS_Nota_FiscalBD(sql).lista(ed, orderby);
      this.fimTransacao(false);
      return lista;
  }

/********************************************************
 *
 *******************************************************/
  public byte[] geraRelatorioNotaFiscal(WMS_Nota_FiscalED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
    byte[] b = WMS_Nota_FiscalBD.geraRelatorioNotaFiscal(ed);
    this.fimTransacao(true);
    return b;
  }
  
/********************************************************
 *
 *******************************************************/
  public long calcula_Quantidade_Itens(WMS_Nota_FiscalED ed) throws Excecoes{
    this.inicioTransacao();
    WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
    long b = WMS_Nota_FiscalBD.calcula_Quantidade_Itens(ed);
    this.fimTransacao(false);
    return b;
  }
  
/********************************************************
 *
 *******************************************************/
  public void atualiza_Quantidade_Itens(WMS_Nota_FiscalED ed) throws Excecoes{
      this.inicioTransacao();

      WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
      WMS_Nota_FiscalBD.atualiza_Quantidade_Itens(ed);

      this.fimTransacao(true);
  }
    
/********************************************************
 *
 *******************************************************/  
  public double calcula_Total_Produtos(WMS_Nota_FiscalED ed) throws Excecoes{
    this.inicioTransacao();
    WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
    double b = WMS_Nota_FiscalBD.calcula_Total_Produtos(ed);
    this.fimTransacao(false);
    return b;
  }
  
/********************************************************
 *
 *******************************************************/  
  public double calcula_Total_Nota(WMS_Nota_FiscalED ed) throws Excecoes{
    this.inicioTransacao();
    WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
    double b = WMS_Nota_FiscalBD.calcula_Total_Nota(ed);
    this.fimTransacao(false);
    return b;
  }
  
/********************************************************
 *
 *******************************************************/  
  public boolean isImpresso(WMS_Nota_FiscalED ed) throws Excecoes{
    this.inicioTransacao();
    WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
    boolean b = WMS_Nota_FiscalBD.isImpresso(ed);
    this.fimTransacao(false);
    return b;
  }
  
/********************************************************
 *
 *******************************************************/  
  public boolean isCancelada(WMS_Nota_FiscalED ed) throws Excecoes{
    this.inicioTransacao();
    WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
    boolean b = WMS_Nota_FiscalBD.isCancelada(ed);
    this.fimTransacao(false);
    return b;
  }
  
/********************************************************
 *
 *******************************************************/  
  public void atualiza_Total_Produtos(WMS_Nota_FiscalED ed) throws Excecoes{
      this.inicioTransacao();

      WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
      WMS_Nota_FiscalBD.atualiza_Total_Produtos(ed);

      this.fimTransacao(true);  
  }
  
/********************************************************
 *
 *******************************************************/  
  public void atualiza_Total_Nota(WMS_Nota_FiscalED ed) throws Excecoes{
      this.inicioTransacao();

      WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
      WMS_Nota_FiscalBD.atualiza_Total_Nota(ed);

      this.fimTransacao(true);  
  }
  
/********************************************************
 *
 *******************************************************/    
  public void atualiza_DM_Gerado(WMS_Nota_FiscalED ed) throws Excecoes{
      this.inicioTransacao();

      WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
      WMS_Nota_FiscalBD.atualiza_DM_Gerado(ed);

      this.fimTransacao(true);  
  }
  
/********************************************************
 *
 *******************************************************/    
  public void atualiza_Notas(WMS_Nota_FiscalED ed) throws Excecoes{
      this.inicioTransacao();

      WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
      WMS_Nota_FiscalBD.atualiza_Notas(ed);

      this.fimTransacao(true);  
  }
  
/********************************************************
 *
 *******************************************************/   
  public void concatena_Observacao(String novoOid, String str) throws Excecoes{
      this.inicioTransacao();

      WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
      WMS_Nota_FiscalBD.concatena_Observacao(novoOid, str);

      this.fimTransacao(true);     
  }
  
/********************************************************
 *
 *******************************************************/  
  public void insere_Nota_Devolucao( WMS_Nota_Fiscal_DevolucaoED ed ) throws Excecoes{
      this.inicioTransacao();

      WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
      WMS_Nota_FiscalBD.insere_Nota_Devolucao(ed);

      this.fimTransacao(true); 
  }
  
/********************************************************
 *
 *******************************************************/    
  public void verificaItens(WMS_Nota_FiscalED ed) throws Excecoes{
      this.inicioTransacao();

      WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
      WMS_Nota_FiscalBD.verificaItens(ed);

      this.fimTransacao(true);        
  }
  
/********************************************************
 *
 *******************************************************/    
  public void itens_Nao_Encontrados(WMS_Nota_FiscalED ed) throws Excecoes{
      this.inicioTransacao();

      WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
      WMS_Nota_FiscalBD.itens_Nao_Encontrados(ed);

      this.fimTransacao(true);        
  }
  
/********************************************************
 *
 *******************************************************/    
  public String isInclui(WMS_Nota_FiscalED edV) throws Excecoes{
    this.inicioTransacao();
    WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
    String b = WMS_Nota_FiscalBD.isInclui(edV);
    this.fimTransacao(false); 
    return b;
  }
  
/********************************************************
 *
 *******************************************************/  
  public void atualiza_Quantidade_Devolucao(WMS_Item_Produto_ClienteED ed) throws Excecoes{
      this.inicioTransacao();

      WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
      WMS_Nota_FiscalBD.atualiza_Quantidade_Devolucao(ed);

      this.fimTransacao(true);    
  }
  
/********************************************************
 *
 *******************************************************/  
  public void cancela_Nota(WMS_Nota_FiscalED ed) throws Excecoes{
      this.inicioTransacao();

      WMS_Nota_FiscalBD = new WMS_Nota_FiscalBD(this.sql);
      WMS_Nota_FiscalBD.cancela_Nota(ed);

      this.fimTransacao(true);    
  }
  
}
