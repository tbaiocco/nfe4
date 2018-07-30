package com.master.rn;

import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Viagem_InternacionalBD;
import com.master.ed.Viagem_InternacionalED;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

public class Viagem_InternacionalRN extends Transacao  {
  Viagem_InternacionalBD Viagem_InternacionalBD = null;


  public Viagem_InternacionalRN() {
    //Viagem_Internacionalbd = new Viagem_InternacionalBD(this.sql);
  }

  public void inclui(Viagem_InternacionalED ed)throws Excecoes{

    if (ed.getOID_Conhecimento_Internacional().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Conhecimento_Internacional não foi informado !!!");
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
      Viagem_InternacionalBD = new Viagem_InternacionalBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Viagem_InternacionalBD.inclui(ed);

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


  public byte[] geraViagem_Internacional(Viagem_InternacionalED ed, HttpServletResponse response)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Viagem_InternacionalBD = new Viagem_InternacionalBD(this.sql);
    byte[] b = Viagem_InternacionalBD.geraViagem_Internacional(ed, response);
    this.fimTransacao(false);
    return b;
  }

  public byte[] geraViagem_Internacional_Antigo(Viagem_InternacionalED ed)throws Excecoes{

      //antes de invocar chamada ao relatorio deve-se
      //fazer validacoes de regra de negocio

      this.inicioTransacao();
      Viagem_InternacionalBD = new Viagem_InternacionalBD(this.sql);
      byte[] b = Viagem_InternacionalBD.geraViagem_Internacional_Antigo(ed);
      this.fimTransacao(false);
      return b;
    }


  public void altera(Viagem_InternacionalED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Viagem_Internacional()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Viagem_Internacional não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Viagem_InternacionalBD = new Viagem_InternacionalBD(this.sql);
      Viagem_InternacionalBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
e.printStackTrace();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public void deleta(Viagem_InternacionalED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Viagem_Internacional()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Viagem_Internacional não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Viagem_InternacionalBD = new Viagem_InternacionalBD(this.sql);
      Viagem_InternacionalBD.deleta(ed);

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

  public ArrayList lista(Viagem_InternacionalED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Viagem_InternacionalBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Viagem_InternacionalED getByRecord(Viagem_InternacionalED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Viagem_InternacionalED edVolta = new Viagem_InternacionalED();
      //atribui ao ed de retorno
      edVolta = new Viagem_InternacionalBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }
  
  public Viagem_InternacionalED getQtdeEmbarcada(Viagem_InternacionalED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Viagem_InternacionalED edVolta = new Viagem_InternacionalED();
      //atribui ao ed de retorno
      edVolta = new Viagem_InternacionalBD(this.sql).getQtdeEmbarcada(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }
  
  public byte[] geraViagem_Nacional(Viagem_InternacionalED ed, HttpServletResponse response)throws Excecoes{

      //antes de invocar chamada ao relatorio deve-se
      //fazer validacoes de regra de negocio

      this.inicioTransacao();
      Viagem_InternacionalBD = new Viagem_InternacionalBD(this.sql);
      byte[] b = Viagem_InternacionalBD.geraViagem_Nacional(ed, response);
      this.fimTransacao(false);
      return b;
    }
  
  public void geraViagem_Internacional_Multiplo(Viagem_InternacionalED ed, HttpServletResponse response)throws Excecoes{

      //antes de invocar chamada ao relatorio deve-se
      //fazer validacoes de regra de negocio

      this.inicioTransacao();
      Viagem_InternacionalBD = new Viagem_InternacionalBD(this.sql);
      Viagem_InternacionalBD.geraViagem_Internacional_Multiplo(ed, response);
      this.fimTransacao(false);
    }

}
