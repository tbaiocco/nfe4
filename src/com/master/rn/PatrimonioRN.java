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

import com.master.bd.PatrimonioBD;
import com.master.ed.PatrimonioED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class PatrimonioRN extends Transacao  {
  PatrimonioBD PatrimonioBD = null;


  public PatrimonioRN() {
    //Patrimoniobd = new PatrimonioBD(this.sql);
  }

  public PatrimonioED inclui(PatrimonioED ed)throws Excecoes{

    PatrimonioED apoioED = new PatrimonioED();

    try{

      this.inicioTransacao();
      apoioED = new PatrimonioBD(this.sql).inclui(ed);
      this.fimTransacao(true);

    }

    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

      catch(Exception e){
        //faz rollback pois deu algum erro
        this.abortaTransacao();
        throw new Excecoes("Erro ao incluir", this.getClass().getName(), "inclui");
      }

      return apoioED;
  }

  public void altera(PatrimonioED ed)throws Excecoes{

    if (String.valueOf(ed.getOid_patrimonio()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Chave do Patrimonio não foi informada !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      new PatrimonioBD(this.sql).altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
        this.abortaTransacao();
        throw exc;
    }
    catch(Exception e){
    	this.abortaTransacao();
        throw new Excecoes("Erro ao alterar", this.getClass().getName(), "altera");
    }

  }

  public void deleta(PatrimonioED ed)throws Excecoes{

    if (String.valueOf(ed.getOid_patrimonio()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Chave do Patrimonio não foi informada !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      new PatrimonioBD(this.sql).deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
        this.abortaTransacao();
        throw exc;
    }
    catch(Exception e){
    	this.abortaTransacao();
        throw new Excecoes("Erro ao excluir", this.getClass().getName(), "deleta");
    }

  }

  public ArrayList lista(PatrimonioED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new PatrimonioBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public PatrimonioED getByRecord(PatrimonioED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      PatrimonioED edVolta = new PatrimonioED();
      //atribui ao ed de retorno
      edVolta = new PatrimonioBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(PatrimonioED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    PatrimonioBD = new PatrimonioBD(this.sql);
    PatrimonioBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}