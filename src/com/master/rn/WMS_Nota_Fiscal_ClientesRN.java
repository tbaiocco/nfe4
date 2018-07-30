package com.master.rn;

/**
 * Título: WMS_Nota_Fiscal_ClientesRN
 * Descrição: Notas Fiscais Clientes - RN
 * Data da criação: 03/2004
 * Atualizado em: 03/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import com.master.bd.WMS_Nota_Fiscal_ClientesBD;
import com.master.ed.WMS_Nota_FiscalED;
import com.master.ed.WMS_Requisicoes_ProdutosED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class WMS_Nota_Fiscal_ClientesRN extends Transacao  {
  WMS_Nota_Fiscal_ClientesBD WMS_Nota_Fiscal_ClientesBD = null;


  public WMS_Nota_Fiscal_ClientesRN() {
  }

  public WMS_Nota_FiscalED inclui(WMS_Nota_FiscalED ed)throws Excecoes{
    WMS_Nota_FiscalED conED = new WMS_Nota_FiscalED();
    try{
      this.inicioTransacao();
      WMS_Nota_Fiscal_ClientesBD = new WMS_Nota_Fiscal_ClientesBD(this.sql);
      conED = WMS_Nota_Fiscal_ClientesBD.inclui(ed);
      ed.setOid_nota_fiscal(conED.getOid_nota_fiscal());

     this.fimTransacao(true);
    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

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

      WMS_Nota_Fiscal_ClientesBD = new WMS_Nota_Fiscal_ClientesBD(this.sql);
      WMS_Nota_Fiscal_ClientesBD.altera(ed);

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

  public void finaliza(WMS_Nota_FiscalED ed)throws Excecoes{
	  try{
		  this.inicioTransacao();
	      new WMS_Nota_Fiscal_ClientesBD(this.sql).finaliza(ed);
		  this.fimTransacao(true);
	  }
	  catch(Excecoes exc){
		  this.abortaTransacao();
		  throw exc;}
	  
	  catch(Exception e){
		  Excecoes excecoes = new Excecoes();
		  excecoes.setClasse(this.getClass().getName());
		  excecoes.setMensagem("Erro ao finalizar");
		  excecoes.setMetodo("finaliza");
		  excecoes.setExc(e);
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

      WMS_Nota_Fiscal_ClientesBD = new WMS_Nota_Fiscal_ClientesBD(this.sql);
      WMS_Nota_Fiscal_ClientesBD.deleta(ed);

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
      ArrayList lista = new WMS_Nota_Fiscal_ClientesBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public WMS_Nota_FiscalED getByRecord(WMS_Nota_FiscalED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      WMS_Nota_FiscalED edVolta = new WMS_Nota_FiscalED();
      //atribui ao ed de retorno
      edVolta = new WMS_Nota_Fiscal_ClientesBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public ArrayList lista(WMS_Nota_FiscalED ed, String orderby)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new WMS_Nota_Fiscal_ClientesBD(sql).lista(ed, orderby);
      this.fimTransacao(false);
      return lista;
  }

  public WMS_Requisicoes_ProdutosED gerarRequisicao( com.master.ed.WMS_Requisicoes_ProdutosED ed )throws Excecoes{
      //retorna um arraylist de ED´s
      this.inicioTransacao();
      WMS_Nota_Fiscal_ClientesBD = new WMS_Nota_Fiscal_ClientesBD(sql);
      WMS_Requisicoes_ProdutosED edVolta = new WMS_Requisicoes_ProdutosED();
      edVolta = WMS_Nota_Fiscal_ClientesBD.gerarRequisicao( ed );
      this.fimTransacao(false);
      return edVolta;
  }

  public void gerarMovimentos( javax.servlet.http.HttpServletRequest request, int OID_Requisicao_Produto )throws Excecoes{
      //retorna um arraylist de ED´s
      this.inicioTransacao();
      WMS_Nota_Fiscal_ClientesBD = new WMS_Nota_Fiscal_ClientesBD(sql);
      WMS_Nota_Fiscal_ClientesBD.gerarMovimentos( request, OID_Requisicao_Produto );
      this.fimTransacao(false);
  }

/********************************************************
 *
 *******************************************************/
  public byte[] geraRelatorioNotaFiscal(WMS_Nota_FiscalED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    WMS_Nota_Fiscal_ClientesBD = new WMS_Nota_Fiscal_ClientesBD(this.sql);
    byte[] b = WMS_Nota_Fiscal_ClientesBD.geraRelatorioNotaFiscal(ed);
    this.fimTransacao(true);
    return b;
  }
  
/********************************************************
 *
 *******************************************************/
  public long calcula_Quantidade_Itens(WMS_Nota_FiscalED ed) throws Excecoes{
    this.inicioTransacao();
    WMS_Nota_Fiscal_ClientesBD = new WMS_Nota_Fiscal_ClientesBD(this.sql);
    long b = WMS_Nota_Fiscal_ClientesBD.calcula_Quantidade_Itens(ed);
    this.fimTransacao(false);
    return b;
  }
  
/********************************************************
 *
 *******************************************************/
  public void atualiza_Quantidade_Itens(WMS_Nota_FiscalED ed) throws Excecoes{
      this.inicioTransacao();

      WMS_Nota_Fiscal_ClientesBD = new WMS_Nota_Fiscal_ClientesBD(this.sql);
      WMS_Nota_Fiscal_ClientesBD.atualiza_Quantidade_Itens(ed);

      this.fimTransacao(true);
  }
    
/********************************************************
 *
 *******************************************************/  
  public double calcula_Total_Produtos(WMS_Nota_FiscalED ed) throws Excecoes{
    this.inicioTransacao();
    WMS_Nota_Fiscal_ClientesBD = new WMS_Nota_Fiscal_ClientesBD(this.sql);
    double b = WMS_Nota_Fiscal_ClientesBD.calcula_Total_Produtos(ed);
    this.fimTransacao(false);
    return b;
  }
  
/********************************************************
 *
 *******************************************************/  
  public double calcula_Total_Nota(WMS_Nota_FiscalED ed) throws Excecoes{
    this.inicioTransacao();
    WMS_Nota_Fiscal_ClientesBD = new WMS_Nota_Fiscal_ClientesBD(this.sql);
    double b = WMS_Nota_Fiscal_ClientesBD.calcula_Total_Nota(ed);
    this.fimTransacao(false);
    return b;
  }
  
/********************************************************
 *
 *******************************************************/  
  public boolean isImpresso(WMS_Nota_FiscalED ed) throws Excecoes{
    this.inicioTransacao();
    WMS_Nota_Fiscal_ClientesBD = new WMS_Nota_Fiscal_ClientesBD(this.sql);
    boolean b = WMS_Nota_Fiscal_ClientesBD.isImpresso(ed);
    this.fimTransacao(false);
    return b;
  }
  
/********************************************************
 *
 *******************************************************/  
  public void atualiza_Total_Produtos(WMS_Nota_FiscalED ed) throws Excecoes{
      this.inicioTransacao();

      WMS_Nota_Fiscal_ClientesBD = new WMS_Nota_Fiscal_ClientesBD(this.sql);
      WMS_Nota_Fiscal_ClientesBD.atualiza_Total_Produtos(ed);

      this.fimTransacao(true);  
  }
  
/********************************************************
 *
 *******************************************************/  
  public void atualiza_Total_Nota(WMS_Nota_FiscalED ed) throws Excecoes{
      this.inicioTransacao();

      WMS_Nota_Fiscal_ClientesBD = new WMS_Nota_Fiscal_ClientesBD(this.sql);
      WMS_Nota_Fiscal_ClientesBD.atualiza_Total_Nota(ed);

      this.fimTransacao(true);  
  }  

}
