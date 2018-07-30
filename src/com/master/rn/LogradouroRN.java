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

import com.master.bd.LogradouroBD;
import com.master.ed.LogradouroED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class LogradouroRN extends Transacao  {
  LogradouroBD LogradouroBD = null;


  public LogradouroRN() {
    //Logradourobd = new LogradouroBD(this.sql);
  }

  public LogradouroED inclui(LogradouroED ed)throws Excecoes{

    LogradouroED LogradouroED = new LogradouroED();

    try{

      this.inicioTransacao();
      LogradouroBD = new LogradouroBD(this.sql);
      LogradouroED = LogradouroBD.inclui(ed);
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

      return LogradouroED;
  }

  public void altera(LogradouroED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Logradouro()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Logradouro não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      LogradouroBD = new LogradouroBD(this.sql);
      LogradouroBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de alteração");

      this.abortaTransacao();

      throw exc;
    }

  }

  public void deleta(LogradouroED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Logradouro()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Logradouro não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      LogradouroBD = new LogradouroBD(this.sql);
      LogradouroBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de exclusão");

      this.abortaTransacao();

      throw exc;
    }

  }

  public ArrayList lista(LogradouroED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new LogradouroBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public LogradouroED getByRecord(LogradouroED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      LogradouroED edVolta = new LogradouroED();
      //atribui ao ed de retorno
      edVolta = new LogradouroBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(LogradouroED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    LogradouroBD = new LogradouroBD(this.sql);
    LogradouroBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}