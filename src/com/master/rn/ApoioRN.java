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

import com.master.bd.ApoioBD;
import com.master.ed.ApoioED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class ApoioRN extends Transacao  {
  ApoioBD ApoioBD = null;


  public ApoioRN() {
    //Apoiobd = new ApoioBD(this.sql);
  }

  public ApoioED inclui(ApoioED ed)throws Excecoes{

    ApoioED apoioED = new ApoioED();

    try{

      this.inicioTransacao();
      ApoioBD = new ApoioBD(this.sql);
      apoioED = ApoioBD.inclui(ed);
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

      return apoioED;
  }

  public void altera(ApoioED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Apoio()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Apoio não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      ApoioBD = new ApoioBD(this.sql);
      ApoioBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de alteração");

      this.abortaTransacao();

      throw exc;
    }

  }

  public void deleta(ApoioED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Apoio()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Apoio não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      ApoioBD = new ApoioBD(this.sql);
      ApoioBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de exclusão");

      this.abortaTransacao();

      throw exc;
    }

  }

  public ArrayList lista(ApoioED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new ApoioBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ApoioED getByRecord(ApoioED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      ApoioED edVolta = new ApoioED();
      //atribui ao ed de retorno
      edVolta = new ApoioBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(ApoioED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    ApoioBD = new ApoioBD(this.sql);
    ApoioBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}