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

import javax.servlet.http.HttpServletResponse;

import com.master.bd.Conhecimento_InternacionalBD;
import com.master.ed.ConhecimentoED;
import com.master.ed.Conhecimento_InternacionalED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Conhecimento_InternacionalRN extends Transacao  {
  Conhecimento_InternacionalBD Conhecimento_InternacionalBD = null;


  public Conhecimento_InternacionalRN() {
  }

  public Conhecimento_InternacionalED inclui(Conhecimento_InternacionalED ed)throws Excecoes{

    Conhecimento_InternacionalED conED = new Conhecimento_InternacionalED();

    if (ed.getOID_Pessoa().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Conhecimento_Internacional não foi informado !!!10");
      throw exc;
    }

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      conED = Conhecimento_InternacionalBD.inclui(ed);

      //faz o commit em cima do objeto transacao
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

    return conED;

  }

  public void altera(Conhecimento_InternacionalED ed)throws Excecoes{

    if (ed.getOID_Pessoa().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Conhecimento_Internacional não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
      Conhecimento_InternacionalBD.altera(ed);

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

  public void altera_Pagador(Conhecimento_InternacionalED ed)throws Excecoes{

    if (ed.getOID_Pessoa_Pagador().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Conhecimento_Internacional não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
      Conhecimento_InternacionalBD.altera_Pagador(ed);

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

  public void deleta(Conhecimento_InternacionalED ed)throws Excecoes{

    if (1 == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Conhecimento_Internacional não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
      Conhecimento_InternacionalBD.deleta(ed);

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

  public ArrayList lista(Conhecimento_InternacionalED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Conhecimento_InternacionalBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }
  
  public ArrayList lista_MIC(Conhecimento_InternacionalED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Conhecimento_InternacionalBD(sql).lista_MIC(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Conhecimento_InternacionalED getByRecord(Conhecimento_InternacionalED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();
      //atribui ao ed de retorno

      edVolta = new Conhecimento_InternacionalBD(this.sql).getByRecord(ed);

      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public byte[] geraRelConhecInternacionalAntigo(Conhecimento_InternacionalED ed)throws Excecoes{

      byte[] b = null;

      try{ 
        this.inicioTransacao();
        Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
        b = Conhecimento_InternacionalBD.geraRelConhecInternacionalAntigo(ed);
        this.fimTransacao(true);
      }
      catch(Excecoes exc){throw exc;}

      catch(Exception e){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar conhecimento");
        excecoes.setMetodo("geraRelConhecInternacional(Conhecimento_InternacionalED ed)");
        excecoes.setExc(e);
        //faz rollback pois deu algum erro
        this.abortaTransacao();

        throw excecoes;
      }

    return b;

    }

  
  public void geraRelConhecInternacional(Conhecimento_InternacionalED ed, HttpServletResponse response)throws Excecoes{
      this.inicioTransacao();
      try {
      	Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
      	Conhecimento_InternacionalBD.geraRelConhecInternacional(ed,response);
      	this.fimTransacao(true);
      } catch (Excecoes e) {
      	abortaTransacao();
      	throw e;
      }
  }



  public void geraRelatorio(Conhecimento_InternacionalED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
    Conhecimento_InternacionalBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

    public void alteraOriginal(String original, String oid)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      new Conhecimento_InternacionalBD(this.sql).alteraOriginal(original, oid);
      this.fimTransacao(true);

  }

    public void cancela(Conhecimento_InternacionalED ed)throws Excecoes{

    	try{

    		this.inicioTransacao();

    		Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
    		Conhecimento_InternacionalBD.cancela(ed);

    		this.fimTransacao(true);

    	}
    	catch(Excecoes exc){
    		this.abortaTransacao();
    		throw exc;}

    	catch(Exception e){
    		Excecoes excecoes = new Excecoes();
    		excecoes.setClasse(this.getClass().getName());
    		excecoes.setMensagem("Erro ao cancelar");
    		excecoes.setMetodo("cancela");
    		excecoes.setExc(e);
    		//faz rollback pois deu algum erro
    		this.abortaTransacao();

    		throw excecoes;
    	}
    }


  public byte[] geraPre_Faturamento_inter(ConhecimentoED ed)throws Excecoes{

    	//antes de invocar chamada ao relatorio deve-se
    	//fazer validacoes de regra de negocio

    	this.inicioTransacao();
    	Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
    	byte[] b = Conhecimento_InternacionalBD.geraPre_Faturamento_inter(ed);
    	this.fimTransacao(false);
    	return b;
  }

  
  public byte[] geraRelCRTEmitidoInter(ConhecimentoED ed) throws Excecoes{

  	//antes de invocar chamada ao relatorio deve-se
  	//fazer validacoes de regra de negocio

  	this.inicioTransacao();
  	Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
  	byte[] b = Conhecimento_InternacionalBD.geraRelCRTEmitidoInter(ed);
  	this.fimTransacao(false);
  	return b;
}
  
  
  public ArrayList lista_fatura(Conhecimento_InternacionalED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Conhecimento_InternacionalBD(sql).lista_fatura(ed);
      this.fimTransacao(false);
      return lista;
  }
  
  public Conhecimento_InternacionalED getByRecord_fatura(Conhecimento_InternacionalED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();
      //atribui ao ed de retorno

      edVolta = new Conhecimento_InternacionalBD(this.sql).getByRecord_fatura(ed);

      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }
  
  public Conhecimento_InternacionalED inclui_fatura(Conhecimento_InternacionalED ed)throws Excecoes{

      Conhecimento_InternacionalED conED = new Conhecimento_InternacionalED();

      try{

        this.inicioTransacao();
        Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
        conED = Conhecimento_InternacionalBD.inclui_fatura(ed);
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

      return conED;

    }
  
  public void deleta_fatura(Conhecimento_InternacionalED ed)throws Excecoes{

      try{

        this.inicioTransacao();

        Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
        // System.out.println("VAI 3");
        Conhecimento_InternacionalBD.deleta_fatura(ed);

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
  
  public void altera_fatura(Conhecimento_InternacionalED ed)throws Excecoes{

      try{

        this.inicioTransacao();

        Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
        Conhecimento_InternacionalBD.altera_fatura(ed);

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
  
  public void geraRelCRTEmitidoJasper(Conhecimento_InternacionalED ed, HttpServletResponse response) throws Excecoes{

    	//antes de invocar chamada ao relatorio deve-se
    	//fazer validacoes de regra de negocio

    	this.inicioTransacao();
    	Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
    	if (ed.getDM_SO() != null && (ed.getDM_SO().equals("NP") || ed.getDM_SO().equals("NC"))){
    	    Conhecimento_InternacionalBD.geraRelCRT_NaoFaturado(ed, response);
    	} else if (ed.getDM_SO() != null && ed.getDM_SO().equals("PF")){
    	    Conhecimento_InternacionalBD.geraRelFaturado(ed, response);
    	} else if (ed.getDM_SO() != null && ed.getDM_SO().equals("PC")){
    	    Conhecimento_InternacionalBD.geraRelCRTporCidade(ed, response);
    	} else Conhecimento_InternacionalBD.geraRelCRTEmitidoJasper(ed, response);
    	
    	this.fimTransacao(false);
  }
  
  public void geraRelCRT_Comissao(Conhecimento_InternacionalED ed, HttpServletResponse response) throws Excecoes{

  	//antes de invocar chamada ao relatorio deve-se
  	//fazer validacoes de regra de negocio

  	this.inicioTransacao();
  	Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
  	Conhecimento_InternacionalBD.geraRelCRT_Comissao(ed, response);
  	
  	this.fimTransacao(false);
}
  
  public void geraRelConhecInternacionalMultiplo(Conhecimento_InternacionalED ed, HttpServletResponse response)throws Excecoes{
      this.inicioTransacao();
      try {
      	Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
      	Conhecimento_InternacionalBD.geraRelConhecInternacionalMultiplo(ed,response);
      	this.fimTransacao(true);
      } catch (Excecoes e) {
      	abortaTransacao();
      	throw e;
      }
  }
  
  
  public void LiberaFaturamentoInternacionalMultiplo(Conhecimento_InternacionalED ed)throws Excecoes{
      this.inicioTransacao();
      try {
      	Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
      	Conhecimento_InternacionalBD.LiberaFaturamentoInternacionalMultiplo(ed);
      	this.fimTransacao(true);
      } catch (Excecoes e) {
      	abortaTransacao();
      	throw e;
      }
  }
  
  public void geraRelCusto(Conhecimento_InternacionalED ed, HttpServletResponse response) throws Excecoes{

  	//antes de invocar chamada ao relatorio deve-se
  	//fazer validacoes de regra de negocio

  	this.inicioTransacao();
  	Conhecimento_InternacionalBD = new Conhecimento_InternacionalBD(this.sql);
  	Conhecimento_InternacionalBD.geraRelCusto(ed, response);
  	
  	this.fimTransacao(false);
}
  
}
