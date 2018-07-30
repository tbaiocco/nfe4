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

import com.master.bd.Grupo_EstoqueBD;
import com.master.ed.Grupo_EstoqueED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Grupo_EstoqueRN extends Transacao  {
  Grupo_EstoqueBD grupo_EstoqueBD = null;


  public Grupo_EstoqueRN() {
    //Grupo_Estoquebd = new Grupo_EstoqueBD(this.sql);
  }

  public void inclui(Grupo_EstoqueED ed)throws Excecoes{

    if (ed.getNM_Grupo_Estoque().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Nome do Grupo Estoque não foi informado");
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
      grupo_EstoqueBD = new Grupo_EstoqueBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      grupo_EstoqueBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de inclusão");

      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw exc;
    }

  }

  public void altera(Grupo_EstoqueED ed)throws Excecoes{

    if (ed.getNM_Grupo_Estoque().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Nome do Grupo Estoque não foi informado");
      throw exc;
    }

    try{

      this.inicioTransacao();

      grupo_EstoqueBD = new Grupo_EstoqueBD(this.sql);
      grupo_EstoqueBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de alteração");

      this.abortaTransacao();

      throw exc;
    }

  }

  public void deleta(Grupo_EstoqueED ed)throws Excecoes{

    if (1 == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Grupo Estoque não foi informado");
      throw exc;
    }

    try{

      this.inicioTransacao();

      grupo_EstoqueBD = new Grupo_EstoqueBD(this.sql);
      grupo_EstoqueBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de exclusão");

      this.abortaTransacao();

      throw exc;
    }

  }

  public ArrayList lista(Grupo_EstoqueED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Grupo_EstoqueBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Grupo_EstoqueED getByRecord(Grupo_EstoqueED ed)throws Excecoes{

      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Grupo_EstoqueED edVolta = new Grupo_EstoqueED();
      //atribui ao ed de retorno
      edVolta = new Grupo_EstoqueBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public byte[] geraRelatorio(Grupo_EstoqueED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    grupo_EstoqueBD = new Grupo_EstoqueBD(this.sql);
    byte[] b  = grupo_EstoqueBD.geraRelatorio(ed);
    this.fimTransacao(false);
    return b;

  }

}