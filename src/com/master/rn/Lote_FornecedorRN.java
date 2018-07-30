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

import com.master.bd.Lote_FornecedorBD;
import com.master.ed.Lote_FornecedorED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import com.master.bd.Movimento_ConhecimentoBD;

public class Lote_FornecedorRN extends Transacao  {
  Lote_FornecedorBD Lote_FornecedorBD = null;


  public Lote_FornecedorRN() {
    //Lote_Fornecedorbd = new Lote_FornecedorBD(this.sql);
  }

  public Lote_FornecedorED inclui(Lote_FornecedorED ed)throws Excecoes{

    Lote_FornecedorED manED = new Lote_FornecedorED();

    try{

      this.inicioTransacao();

      Lote_FornecedorBD = new Lote_FornecedorBD(this.sql);

      manED = Lote_FornecedorBD.inclui(ed);

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

    return manED;

  }

  public void altera(Lote_FornecedorED ed)throws Excecoes{


    try{


      this.inicioTransacao();

      Lote_FornecedorBD = new Lote_FornecedorBD(this.sql);
      Lote_FornecedorBD.altera(ed);

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


  public void confirma_Compromisso(Lote_FornecedorED ed)throws Excecoes{


    try{


      this.inicioTransacao();

      Lote_FornecedorBD = new Lote_FornecedorBD(this.sql);
      Lote_FornecedorBD.confirma_Compromisso(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao confirmar Compromisso");
      excecoes.setMetodo("confirma_Compromisso");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

  }


  public void deleta(Lote_FornecedorED ed)throws Excecoes{


    try{

      this.inicioTransacao();

      Lote_FornecedorBD = new Lote_FornecedorBD(this.sql);
      Lote_FornecedorBD.deleta(ed);

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

  public ArrayList lista(Lote_FornecedorED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Lote_FornecedorBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }


  public Lote_FornecedorED getByRecord(Lote_FornecedorED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Lote_FornecedorED edVolta = new Lote_FornecedorED();
      //atribui ao ed de retorno
      edVolta = new Lote_FornecedorBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public byte[] imprime_Lote_Fornecedor(Lote_FornecedorED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Lote_FornecedorBD = new Lote_FornecedorBD(this.sql);
    byte[] b = Lote_FornecedorBD.imprime_Lote_Fornecedor(ed);
    this.fimTransacao(false);
    return b;
  }

  public Lote_FornecedorED atualiza(Lote_FornecedorED ed)throws Excecoes{

    Lote_FornecedorED manED = new Lote_FornecedorED();

    try{

        // System.out.println ("atualiza atualiza_Lote_Fornecedor pelo MOV CTO");
      Movimento_ConhecimentoBD Movimento_ConhecimentoBD = null;

      this.inicioTransacao();

      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);
      manED = Movimento_ConhecimentoBD.atualiza_Lote_Fornecedor(ed);

      this.fimTransacao(true);

      this.inicioTransacao();
      
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);
      Movimento_ConhecimentoBD.recalcula_Margem_Lote_Fornecedor(ed.getOID_Lote_Fornecedor());

      // System.out.println (" recalculo OK ");
      
      Lote_FornecedorBD = new Lote_FornecedorBD(this.sql);
      Lote_FornecedorBD.seta_Situacao(ed.getOID_Lote_Fornecedor(), "F");
      
      this.fimTransacao(true);

    }
    catch (Excecoes exc) {
        this.abortaTransacao ();
        throw exc;
      }
      catch (RuntimeException e) {
        abortaTransacao ();
        throw e;
      }

    return manED;

  }

  public Lote_FornecedorED reabre(Lote_FornecedorED ed)throws Excecoes{

    Lote_FornecedorED manED = new Lote_FornecedorED();

    try{

    	
      this.inicioTransacao();
      Movimento_ConhecimentoBD Movimento_ConhecimentoBD = null;
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);
      manED = Movimento_ConhecimentoBD.reabre_Lote_Fornecedor(ed);

      this.fimTransacao(true);

      this.inicioTransacao();

      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);
      Movimento_ConhecimentoBD.recalcula_Margem_Lote_Fornecedor(ed.getOID_Lote_Fornecedor());

      Lote_FornecedorBD = new Lote_FornecedorBD(this.sql);
      Lote_FornecedorBD.seta_Situacao(ed.getOID_Lote_Fornecedor(), "A");

      this.fimTransacao(true);
      
      
    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("atualiza");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

    return manED;

  }


}