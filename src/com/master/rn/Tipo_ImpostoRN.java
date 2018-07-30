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

import com.master.bd.Tipo_ImpostoBD;
import com.master.ed.Tipo_ImpostoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Tipo_ImpostoRN extends Transacao  {
  Tipo_ImpostoBD Tipo_ImpostoBD = null;


  public Tipo_ImpostoRN() {
    //Tipo_Impostobd = new Tipo_ImpostoBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public Tipo_ImpostoED inclui(Tipo_ImpostoED ed)throws Excecoes{

    Tipo_ImpostoED Tipo_ImpostoED = new Tipo_ImpostoED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Tipo_ImpostoBD = new Tipo_ImpostoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Tipo_ImpostoED = Tipo_ImpostoBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);
    }
    catch(Excecoes e){throw e;}

    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Tipo_Imposto");
      excecoes.setMetodo("Inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return Tipo_ImpostoED;
  }

/********************************************************
 *
 *******************************************************/
  public void altera(Tipo_ImpostoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Tipo_ImpostoBD = new Tipo_ImpostoBD(this.sql);
      Tipo_ImpostoBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Tipo_Imposto");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public void deleta(Tipo_ImpostoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Tipo_ImpostoBD = new Tipo_ImpostoBD(this.sql);
      Tipo_ImpostoBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Tipo_Imposto");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista(Tipo_ImpostoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Tipo_ImpostoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

/********************************************************
 *
 *******************************************************/
  public Tipo_ImpostoED getByRecord(Tipo_ImpostoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Tipo_ImpostoED edVolta = new Tipo_ImpostoED();
      //atribui ao ed de retorno
      edVolta = new Tipo_ImpostoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

/********************************************************
 *
 *******************************************************/
  public void geraRelatorio(Tipo_ImpostoED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Tipo_ImpostoBD = new Tipo_ImpostoBD(this.sql);
    Tipo_ImpostoBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}