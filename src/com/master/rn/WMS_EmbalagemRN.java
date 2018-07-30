package com.master.rn;

/**
 * Título: WMS_EmbalagemRN
 * Descrição: Embalagens - RN
 * Data da criação: 10/2003
 * Atualizado em: 02/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import com.master.bd.WMS_EmbalagemBD;
import com.master.ed.WMS_EmbalagemED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class WMS_EmbalagemRN extends Transacao  {
  WMS_EmbalagemBD WMS_EmbalagemBD = null;


  public WMS_EmbalagemRN() {
    //WMS_Embalagembd = new WMS_EmbalagemBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public WMS_EmbalagemED inclui(WMS_EmbalagemED ed)throws Excecoes{

    WMS_EmbalagemED WMS_EmbalagemED = new WMS_EmbalagemED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      WMS_EmbalagemBD = new WMS_EmbalagemBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      WMS_EmbalagemED = WMS_EmbalagemBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);
    }
    catch(Excecoes e){throw e;}

    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Embalagem");
      excecoes.setMetodo("Inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return WMS_EmbalagemED;
  }

/********************************************************
 *
 *******************************************************/
  public void altera(WMS_EmbalagemED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      WMS_EmbalagemBD = new WMS_EmbalagemBD(this.sql);
      WMS_EmbalagemBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Embalagem");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public void deleta(WMS_EmbalagemED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      WMS_EmbalagemBD = new WMS_EmbalagemBD(this.sql);
      WMS_EmbalagemBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Embalagem");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public WMS_EmbalagemED getByRecord(WMS_EmbalagemED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      WMS_EmbalagemED edVolta = new WMS_EmbalagemED();
      //atribui ao ed de retorno
      edVolta = new WMS_EmbalagemBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList getAll()throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //atribui ao ed de retorno
      ArrayList lista = new WMS_EmbalagemBD(this.sql).getAll();
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return lista;
  }

/********************************************************
 *
 *******************************************************/
  public WMS_EmbalagemED getByOid(String oid_embalagem)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      WMS_EmbalagemED edVolta = new WMS_EmbalagemED();
      //atribui ao ed de retorno
      edVolta = new WMS_EmbalagemBD(this.sql).getByOid(oid_embalagem);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista(WMS_EmbalagemED ed, String orderby)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new WMS_EmbalagemBD(sql).lista(ed, orderby);
      this.fimTransacao(false);
      return lista;
  }
}