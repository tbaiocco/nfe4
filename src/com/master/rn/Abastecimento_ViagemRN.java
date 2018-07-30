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

import com.master.bd.Abastecimento_ViagemBD;
import com.master.ed.Abastecimento_ViagemED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Abastecimento_ViagemRN extends Transacao  {
  Abastecimento_ViagemBD Abastecimento_ViagemBD = null;


  public Abastecimento_ViagemRN() {
    //Abastecimento_Viagembd = new Abastecimento_ViagemBD(this.sql);
  }

  public void inclui(Abastecimento_ViagemED ed)throws Excecoes{


    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Abastecimento_ViagemBD = new Abastecimento_ViagemBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Abastecimento_ViagemBD.inclui(ed);

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
  }


  public void altera(Abastecimento_ViagemED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Abastecimento_Viagem()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Abastecimento_Viagem não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Abastecimento_ViagemBD = new Abastecimento_ViagemBD(this.sql);
      Abastecimento_ViagemBD.altera(ed);

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

  public void deleta(Abastecimento_ViagemED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Abastecimento_Viagem()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Abastecimento_Viagem não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Abastecimento_ViagemBD = new Abastecimento_ViagemBD(this.sql);
      Abastecimento_ViagemBD.deleta(ed);

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

  public ArrayList lista(Abastecimento_ViagemED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Abastecimento_ViagemBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Abastecimento_ViagemED getByRecord(Abastecimento_ViagemED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Abastecimento_ViagemED edVolta = new Abastecimento_ViagemED();
      //atribui ao ed de retorno
      edVolta = new Abastecimento_ViagemBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }


  public double getByLitrosAbastecimento(String oid_Manifesto)throws Excecoes{
      //inicia conexao com bd
      
      this.inicioTransacao();
      //instancia ed de retorno
      Abastecimento_ViagemED edVolta = new Abastecimento_ViagemED();
      //atribui ao ed de retorno
      double tt_litros= new Abastecimento_ViagemBD(this.sql).getByLitrosAbastecimento(oid_Manifesto);
      //libera conexao nao "commitando"
      this.fimTransacao(false);
      return tt_litros;
  }

  public byte[] imprime_Ordem_Abastecimento(Abastecimento_ViagemED ed)
  throws Excecoes {
      inicioTransacao();
      try {
          return new Abastecimento_ViagemBD(sql).imprime_Ordem_Abastecimento(ed);
      } finally {
          fimTransacao(true);
      }
  }


}