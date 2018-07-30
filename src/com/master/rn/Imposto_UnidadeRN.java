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

import com.master.bd.Imposto_UnidadeBD;
import com.master.ed.Imposto_UnidadeED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Imposto_UnidadeRN extends Transacao  {
  Imposto_UnidadeBD Imposto_UnidadeBD = null;


  public Imposto_UnidadeRN() {
    //Imposto_Unidadebd = new Imposto_UnidadeBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public Imposto_UnidadeED inclui(Imposto_UnidadeED ed)throws Excecoes{

    Imposto_UnidadeED Imposto_UnidadeED = new Imposto_UnidadeED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Imposto_UnidadeBD = new Imposto_UnidadeBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Imposto_UnidadeED = Imposto_UnidadeBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);
    }
    catch(Excecoes e){throw e;}

    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Imposto_Unidade");
      excecoes.setMetodo("Inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return Imposto_UnidadeED;
  }

/********************************************************
 *
 *******************************************************/
  public void altera(Imposto_UnidadeED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Imposto_UnidadeBD = new Imposto_UnidadeBD(this.sql);
      Imposto_UnidadeBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Imposto_Unidade");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public void deleta(Imposto_UnidadeED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Imposto_UnidadeBD = new Imposto_UnidadeBD(this.sql);
      Imposto_UnidadeBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Imposto_Unidade");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista(Imposto_UnidadeED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Imposto_UnidadeBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

/********************************************************
 *
 *******************************************************/
  public Imposto_UnidadeED getByRecord(Imposto_UnidadeED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Imposto_UnidadeED edVolta = new Imposto_UnidadeED();
      //atribui ao ed de retorno
      edVolta = new Imposto_UnidadeBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

/********************************************************
 *
 *******************************************************/
  public void geraRelatorio(Imposto_UnidadeED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Imposto_UnidadeBD = new Imposto_UnidadeBD(this.sql);
    Imposto_UnidadeBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}