package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Ocorrencia_ProdutoBD;
import com.master.ed.Ocorrencia_ProdutoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Ocorrencia_ProdutoRN extends Transacao  {
  Ocorrencia_ProdutoBD Ocorrencia_ProdutoBD = null;


  public Ocorrencia_ProdutoRN() {
    //Ocorrencia_Produtobd = new Ocorrencia_ProdutoBD(this.sql);
  }

  public Ocorrencia_ProdutoED inclui(Ocorrencia_ProdutoED ed)throws Excecoes{

    Ocorrencia_ProdutoED ocorrencia_DuplicataED = new Ocorrencia_ProdutoED();

    if (String.valueOf(ed.getOid_Produto()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Produto não foi informado!!!");
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
      Ocorrencia_ProdutoBD = new Ocorrencia_ProdutoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      ocorrencia_DuplicataED = Ocorrencia_ProdutoBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    }

    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;
    }
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

      return ocorrencia_DuplicataED;
  }

  public void altera(Ocorrencia_ProdutoED ed)throws Excecoes{

    if (String.valueOf(ed.getOid_Ocorrencia_Produto()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código da Ocorrencia_Produto não foi informado!!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      new Ocorrencia_ProdutoBD(this.sql).altera(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de alteração");

      this.abortaTransacao();

      throw exc;
    }

  }

  public void deleta(Ocorrencia_ProdutoED ed)throws Excecoes{

    if (String.valueOf(ed.getOid_Ocorrencia_Produto()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código da Ocorrencia_Produto não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Ocorrencia_ProdutoBD = new Ocorrencia_ProdutoBD(this.sql);
      Ocorrencia_ProdutoBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de exclusão");

      this.abortaTransacao();

      throw exc;
    }

  }

  public ArrayList lista(Ocorrencia_ProdutoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Ocorrencia_ProdutoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Ocorrencia_ProdutoED getByRecord(Ocorrencia_ProdutoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Ocorrencia_ProdutoED edVolta = new Ocorrencia_ProdutoED();
      //atribui ao ed de retorno
      edVolta = new Ocorrencia_ProdutoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }
}