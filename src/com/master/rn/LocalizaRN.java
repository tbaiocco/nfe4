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

import com.master.bd.LocalizaBD;
import com.master.ed.LocalizaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class LocalizaRN extends Transacao  {
  LocalizaBD LocalizaBD = null;


  public LocalizaRN() {
  }

  public LocalizaED inclui(LocalizaED ed)throws Excecoes{

    LocalizaED localizaED = new LocalizaED();

    if (ed.getOID_Pessoa().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Nota_Fiscal não foi informado !!!10");
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
      LocalizaBD = new LocalizaBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      localizaED = LocalizaBD.inclui(ed);

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

    return localizaED;

  }

  public void altera(LocalizaED ed)throws Excecoes{

    if (ed.getOID_Pessoa().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Nota_Fiscal não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      LocalizaBD = new LocalizaBD(this.sql);
      LocalizaBD.altera(ed);

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

  public void deleta(LocalizaED ed)throws Excecoes{

    if (ed.getOID_Nota_Fiscal().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Nota_Fiscal não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      LocalizaBD = new LocalizaBD(this.sql);
      LocalizaBD.deleta(ed);

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

  public ArrayList lista(LocalizaED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new LocalizaBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public LocalizaED getByRecord(LocalizaED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      LocalizaED edVolta = new LocalizaED();
      //atribui ao ed de retorno
      edVolta = new LocalizaBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(LocalizaED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    LocalizaBD = new LocalizaBD(this.sql);
    LocalizaBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}