package com.master.rn;

/**
 * <p>Title:  </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.ArrayList;

import com.master.bd.Ocorrencia_Conhecimento_InternacionalBD;
import com.master.ed.Ocorrencia_ConhecimentoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Ocorrencia_Conhecimento_InternacionalRN extends Transacao  {
  Ocorrencia_Conhecimento_InternacionalBD Ocorrencia_Conhecimento_InternacionalBD = null;


  public Ocorrencia_Conhecimento_InternacionalRN() {
    //Ocorrencia_Conhecimentobd = new Ocorrencia_ConhecimentoBD(this.sql);
  }

  public Ocorrencia_ConhecimentoED inclui(Ocorrencia_ConhecimentoED ed)throws Excecoes{

    Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED();

    if (ed.getOID_Conhecimento().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Conhecimento não foi informado !!!10");
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
      Ocorrencia_Conhecimento_InternacionalBD = new Ocorrencia_Conhecimento_InternacionalBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      ocorrencia_ConhecimentoED = Ocorrencia_Conhecimento_InternacionalBD.inclui(ed);

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

      return ocorrencia_ConhecimentoED;
  }

  public void altera(Ocorrencia_ConhecimentoED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Ocorrencia_Conhecimento()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Ocorrencia_Conhecimento não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Ocorrencia_Conhecimento_InternacionalBD = new Ocorrencia_Conhecimento_InternacionalBD(this.sql);
      Ocorrencia_Conhecimento_InternacionalBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de alteração");

      this.abortaTransacao();

      throw exc;
    }

  }

  public void deleta(Ocorrencia_ConhecimentoED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Ocorrencia_Conhecimento()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Ocorrencia_Conhecimento não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Ocorrencia_Conhecimento_InternacionalBD = new Ocorrencia_Conhecimento_InternacionalBD(this.sql);
      Ocorrencia_Conhecimento_InternacionalBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de exclusão");

      this.abortaTransacao();

      throw exc;
    }

  }

  public ArrayList lista(Ocorrencia_ConhecimentoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Ocorrencia_Conhecimento_InternacionalBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Ocorrencia_ConhecimentoED getByRecord(Ocorrencia_ConhecimentoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Ocorrencia_ConhecimentoED edVolta = new Ocorrencia_ConhecimentoED();
      //atribui ao ed de retorno
      edVolta = new Ocorrencia_Conhecimento_InternacionalBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

/**  public void geraRelatorio(Ocorrencia_ConhecimentoED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Ocorrencia_ConhecimentoBD = new Ocorrencia_ConhecimentoBD(this.sql);
    Ocorrencia_ConhecimentoBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }*/

}