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

import com.master.bd.Ocorrencia_BancariaBD;
import com.master.ed.Ocorrencia_BancariaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Ocorrencia_BancariaRN extends Transacao  {
  Ocorrencia_BancariaBD Ocorrencia_BancariaBD = null;


  public Ocorrencia_BancariaRN() {
    //Ocorrencia_Bancariabd = new Ocorrencia_BancariaBD(this.sql);
  }

  public Ocorrencia_BancariaED inclui(Ocorrencia_BancariaED ed)throws Excecoes{

    Ocorrencia_BancariaED ocorrenciaED = new Ocorrencia_BancariaED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Ocorrencia_BancariaBD = new Ocorrencia_BancariaBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      ocorrenciaED = Ocorrencia_BancariaBD.inclui(ed);

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
      excecoes.setMensagem("Erro ao incluir ocorrência bancária");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return ocorrenciaED;

  }

  public void altera(Ocorrencia_BancariaED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Ocorrencia_BancariaBD = new Ocorrencia_BancariaBD(this.sql);
      Ocorrencia_BancariaBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar ocorrência bancária");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

  public void deleta(Ocorrencia_BancariaED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Ocorrencia_BancariaBD = new Ocorrencia_BancariaBD(this.sql);
      Ocorrencia_BancariaBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir ocorrência bancária");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

  public ArrayList lista(Ocorrencia_BancariaED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Ocorrencia_BancariaBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Ocorrencia_BancariaED getByRecord(Ocorrencia_BancariaED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Ocorrencia_BancariaED edVolta = new Ocorrencia_BancariaED();
      //atribui ao ed de retorno
      edVolta = new Ocorrencia_BancariaBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(Ocorrencia_BancariaED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Ocorrencia_BancariaBD = new Ocorrencia_BancariaBD(this.sql);
    Ocorrencia_BancariaBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}