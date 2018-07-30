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

import com.master.bd.Movimento_OrdemBD;
import com.master.ed.Movimento_OrdemED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Movimento_OrdemRN extends Transacao  {
  Movimento_OrdemBD Movimento_OrdemBD = null;


  public Movimento_OrdemRN() {
    //Movimento_Ordembd = new Movimento_OrdemBD(this.sql);
  }

  public void inclui(Movimento_OrdemED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Compromisso()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Compromisso não foi informado !!!");
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
      Movimento_OrdemBD = new Movimento_OrdemBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Movimento_OrdemBD.inclui(ed);

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

  public void altera(Movimento_OrdemED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Movimento_Ordem()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Movimento_Ordem não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Movimento_OrdemBD = new Movimento_OrdemBD(this.sql);
      Movimento_OrdemBD.altera(ed);

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

  public void deleta(Movimento_OrdemED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Movimento_Ordem()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Movimento_Ordem não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Movimento_OrdemBD = new Movimento_OrdemBD(this.sql);
      Movimento_OrdemBD.deleta(ed);

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

  public ArrayList lista(Movimento_OrdemED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Movimento_OrdemBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Movimento_OrdemED getByRecord(Movimento_OrdemED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Movimento_OrdemED edVolta = new Movimento_OrdemED();
      //atribui ao ed de retorno
      edVolta = new Movimento_OrdemBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

}