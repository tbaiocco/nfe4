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

import com.master.bd.Movimento_ProcessoBD;
import com.master.ed.Movimento_ProcessoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Movimento_ProcessoRN extends Transacao  {
  Movimento_ProcessoBD Movimento_ProcessoBD = null;


  public Movimento_ProcessoRN() {
    //Movimento_Processobd = new Movimento_ProcessoBD(this.sql);
  }

//  public Movimento_ProcessoRN(ExecutaSQL sqlTrans) {
//    this.sql = sql;
//    new Transacao(sqlTrans);
//    new Movimento_ProcessoBD(this.sql);
//  }

  public Movimento_ProcessoED inclui(Movimento_ProcessoED ed)throws Excecoes{

    Movimento_ProcessoED Movimento_ProcessoED = new Movimento_ProcessoED();

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Movimento_ProcessoBD = new Movimento_ProcessoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Movimento_ProcessoED = Movimento_ProcessoBD.inclui(ed);


      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    }

    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui(Movimento_ProcessoED)");
      excecoes.setExc(e);
      this.abortaTransacao();
      throw excecoes;

    }

    return Movimento_ProcessoED;

  }


  public Movimento_ProcessoED geraSaldoInicial(Movimento_ProcessoED ed)throws Excecoes{

    Movimento_ProcessoED Movimento_ProcessoED = new Movimento_ProcessoED();

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Movimento_ProcessoBD = new Movimento_ProcessoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Movimento_ProcessoED = Movimento_ProcessoBD.geraSaldoInicial(ed);


      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    }

    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar o valor do Movimento_Processo");
      excecoes.setMetodo("inclui(Movimento_ProcessoED)");
      excecoes.setExc(e);
      this.abortaTransacao();
      throw excecoes;

    }

    return Movimento_ProcessoED;

  }


  public Movimento_ProcessoED consultaSaldo(Movimento_ProcessoED ed)throws Excecoes{

    Movimento_ProcessoED Movimento_ProcessoED = new Movimento_ProcessoED();

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Movimento_ProcessoBD = new Movimento_ProcessoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Movimento_ProcessoED = Movimento_ProcessoBD.consultaSaldo(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    }

    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao consultar saldo");
      excecoes.setMetodo("inclui(Movimento_ProcessoED)");
      excecoes.setExc(e);
      this.abortaTransacao();
      throw excecoes;

    }

    return Movimento_ProcessoED;

  }



  public void altera(Movimento_ProcessoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Movimento_ProcessoBD = new Movimento_ProcessoBD(this.sql);
      Movimento_ProcessoBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("inclui(Movimento_ProcessoED)");
      excecoes.setExc(e);
      this.abortaTransacao();
      throw excecoes;
    }

  }



  public void deleta(Movimento_ProcessoED ed)throws Excecoes{

    try{


      this.inicioTransacao();

      Movimento_ProcessoBD = new Movimento_ProcessoBD(this.sql);
      Movimento_ProcessoBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir");
      excecoes.setMetodo("inclui(Movimento_ProcessoED)");
      excecoes.setExc(e);
      this.abortaTransacao();
      throw excecoes;
    }

  }

  public ArrayList lista(Movimento_ProcessoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
             // System.out.println("rn");

      ArrayList lista = new Movimento_ProcessoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Movimento_ProcessoED getByRecord(Movimento_ProcessoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Movimento_ProcessoED edVolta = new Movimento_ProcessoED();
      //atribui ao ed de retorno
      edVolta = new Movimento_ProcessoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public byte[] imprime_Movimento_Processo(Movimento_ProcessoED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Movimento_ProcessoBD = new Movimento_ProcessoBD(this.sql);
    byte[] b = Movimento_ProcessoBD.imprime_Movimento_Processo(ed);
    this.fimTransacao(false);
    return b;
  }



}
