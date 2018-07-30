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

import com.master.bd.Romaneio_EntregaBD;
import com.master.ed.Romaneio_EntregaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Romaneio_EntregaRN extends Transacao  {
  Romaneio_EntregaBD Romaneio_EntregaBD = null;


  public Romaneio_EntregaRN() {
    //Romaneio_Entregabd = new Romaneio_EntregaBD(this.sql);
  }

  public Romaneio_EntregaED inclui(Romaneio_EntregaED ed)throws Excecoes{

    Romaneio_EntregaED romaneio_EntregaED = new Romaneio_EntregaED();

    if (ed.getOID_Pessoa().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Conhecimento não foi informado !!!");
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
      Romaneio_EntregaBD = new Romaneio_EntregaBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      romaneio_EntregaED = Romaneio_EntregaBD.inclui(ed);

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

      return romaneio_EntregaED;

  }

  public void altera(Romaneio_EntregaED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Romaneio_Entrega()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Romaneio_Entrega não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Romaneio_EntregaBD = new Romaneio_EntregaBD(this.sql);
      Romaneio_EntregaBD.altera(ed);

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

  public void deleta(Romaneio_EntregaED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Romaneio_Entrega()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Romaneio_Entrega não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Romaneio_EntregaBD = new Romaneio_EntregaBD(this.sql);
      Romaneio_EntregaBD.deleta(ed);

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

  public ArrayList lista(Romaneio_EntregaED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Romaneio_EntregaBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Romaneio_EntregaED getByRecord(Romaneio_EntregaED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Romaneio_EntregaED edVolta = new Romaneio_EntregaED();
      //atribui ao ed de retorno
      edVolta = new Romaneio_EntregaBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(Romaneio_EntregaED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Romaneio_EntregaBD = new Romaneio_EntregaBD(this.sql);
    Romaneio_EntregaBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}