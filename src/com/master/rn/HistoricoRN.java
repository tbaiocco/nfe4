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

import javax.servlet.http.HttpServletRequest;

import com.master.bd.HistoricoBD;
import com.master.ed.HistoricoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class HistoricoRN extends Transacao  {
  HistoricoBD HistoricoBD = null;


  public HistoricoRN() {
    //Historicobd = new HistoricoBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public HistoricoED inclui(HistoricoED ed)throws Excecoes{

    HistoricoED historicoED = new HistoricoED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      HistoricoBD = new HistoricoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      historicoED = HistoricoBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir histórico");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return historicoED;

  }

/********************************************************
 *
 *******************************************************/
  public void altera(HistoricoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      HistoricoBD = new HistoricoBD(this.sql);
      HistoricoBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar histórico");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public void deleta(HistoricoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      HistoricoBD = new HistoricoBD(this.sql);
      HistoricoBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar histórico");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista(HistoricoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new HistoricoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;

  }

  public HistoricoED getByRecord(HistoricoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      HistoricoED edVolta = new HistoricoED();
      //atribui ao ed de retorno
      edVolta = new HistoricoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }
  
  public void getByRecord(HistoricoED ed, HttpServletRequest request, String nmObj)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      HistoricoED edVolta = new HistoricoED();
      //atribui ao ed de retorno
      edVolta = new HistoricoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      request.setAttribute(nmObj,edVolta) ;
  }

/********************************************************
 *
 *******************************************************/
  public void geraRelatorio(HistoricoED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    HistoricoBD = new HistoricoBD(this.sql);
    HistoricoBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}