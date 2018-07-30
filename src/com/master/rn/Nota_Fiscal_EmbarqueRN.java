package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Nota_Fiscal_EmbarqueBD;
import com.master.ed.Nota_Fiscal_EmbarqueED;
import java.util.*;

public class Nota_Fiscal_EmbarqueRN extends Transacao  {
  Nota_Fiscal_EmbarqueBD Nota_Fiscal_EmbarqueBD = null;


  public Nota_Fiscal_EmbarqueRN() {
    //Nota_Fiscal_Embarquebd = new Nota_Fiscal_EmbarqueBD(this.sql);
  }

  public Nota_Fiscal_EmbarqueED inclui(Nota_Fiscal_EmbarqueED ed)throws Excecoes{

    Nota_Fiscal_EmbarqueED ocorrencia_Nota_FiscalED = new Nota_Fiscal_EmbarqueED();

    if (ed.getOID_Nota_Fiscal().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Nota_Fiscal não foi informado !!!");
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
      Nota_Fiscal_EmbarqueBD = new Nota_Fiscal_EmbarqueBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      ocorrencia_Nota_FiscalED = Nota_Fiscal_EmbarqueBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    }

      catch(Excecoes exc){throw exc;}

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

      return ocorrencia_Nota_FiscalED;
  }

  public void altera(Nota_Fiscal_EmbarqueED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Nota_Fiscal_Embarque()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Nota_Fiscal_Embarque não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Nota_Fiscal_EmbarqueBD = new Nota_Fiscal_EmbarqueBD(this.sql);
      Nota_Fiscal_EmbarqueBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de alteração");

      this.abortaTransacao();

      throw exc;
    }

  }

  public void deleta(Nota_Fiscal_EmbarqueED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Nota_Fiscal_Embarque()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Nota_Fiscal_Embarque não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Nota_Fiscal_EmbarqueBD = new Nota_Fiscal_EmbarqueBD(this.sql);
      Nota_Fiscal_EmbarqueBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de exclusão");

      this.abortaTransacao();

      throw exc;
    }

  }

  public ArrayList lista(Nota_Fiscal_EmbarqueED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Nota_Fiscal_EmbarqueBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Nota_Fiscal_EmbarqueED getByRecord(Nota_Fiscal_EmbarqueED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Nota_Fiscal_EmbarqueED edVolta = new Nota_Fiscal_EmbarqueED();
      //atribui ao ed de retorno
      edVolta = new Nota_Fiscal_EmbarqueBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(Nota_Fiscal_EmbarqueED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Nota_Fiscal_EmbarqueBD = new Nota_Fiscal_EmbarqueBD(this.sql);
    Nota_Fiscal_EmbarqueBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}