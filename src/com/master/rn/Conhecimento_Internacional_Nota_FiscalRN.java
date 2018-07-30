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
import com.master.bd.Conhecimento_Internacional_Nota_FiscalBD;
import com.master.ed.Conhecimento_Internacional_Nota_FiscalED;
import java.util.*;

public class Conhecimento_Internacional_Nota_FiscalRN extends Transacao  {
  Conhecimento_Internacional_Nota_FiscalBD Conhecimento_Internacional_Nota_FiscalBD = null;


  public Conhecimento_Internacional_Nota_FiscalRN() {
    //Conhecimento_Internacional_Nota_Fiscalbd = new Conhecimento_Internacional_Nota_FiscalBD(this.sql);
  }

  public Conhecimento_Internacional_Nota_FiscalED inclui(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

    Conhecimento_Internacional_Nota_FiscalED ocorrencia_Nota_FiscalED = new Conhecimento_Internacional_Nota_FiscalED();

    try{
      this.inicioTransacao();
      Conhecimento_Internacional_Nota_FiscalBD = new Conhecimento_Internacional_Nota_FiscalBD(this.sql);
      ocorrencia_Nota_FiscalED = Conhecimento_Internacional_Nota_FiscalBD.inclui(ed);
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

  public void altera(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Conhecimento_Internacional_Nota_Fiscal()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Conhecimento_Internacional_Nota_Fiscal não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Conhecimento_Internacional_Nota_FiscalBD = new Conhecimento_Internacional_Nota_FiscalBD(this.sql);
      Conhecimento_Internacional_Nota_FiscalBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
        e.printStackTrace();
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de alteração");

      this.abortaTransacao();

      throw exc;
    }

  }

  public void deleta(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Conhecimento_Internacional_Nota_Fiscal()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Conhecimento_Internacional_Nota_Fiscal não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Conhecimento_Internacional_Nota_FiscalBD = new Conhecimento_Internacional_Nota_FiscalBD(this.sql);
      Conhecimento_Internacional_Nota_FiscalBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch (Excecoes exc){
        this.abortaTransacao();
        throw exc;
    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de exclusão");

      this.abortaTransacao();

      throw exc;
    }

  }

  public ArrayList lista(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{
// // System.out.println("RN 1");
      //retorna um arraylist de ED´s
      this.inicioTransacao();
// // System.out.println("RN 2");
      ArrayList lista = new Conhecimento_Internacional_Nota_FiscalBD(sql).lista(ed);
// // System.out.println("RN 3");
      this.fimTransacao(false);
// // System.out.println("RN 4");
      return lista;
  }

  public Conhecimento_Internacional_Nota_FiscalED getByRecord(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Conhecimento_Internacional_Nota_FiscalED edVolta = new Conhecimento_Internacional_Nota_FiscalED();
      //atribui ao ed de retorno
      edVolta = new Conhecimento_Internacional_Nota_FiscalBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public byte[] geraRelTratoresPendentes(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Conhecimento_Internacional_Nota_FiscalBD = new Conhecimento_Internacional_Nota_FiscalBD(this.sql);
    byte[] b = Conhecimento_Internacional_Nota_FiscalBD.geraRelTratoresPendentes(ed);
    this.fimTransacao(false);
    return b;
  }

  public byte[] geraRelTratoresEmbarc(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

    this.inicioTransacao();
    Conhecimento_Internacional_Nota_FiscalBD = new Conhecimento_Internacional_Nota_FiscalBD(this.sql);
    byte[] b = Conhecimento_Internacional_Nota_FiscalBD.geraRelTratoresEmbarc(ed);
    this.fimTransacao(false);
    return b;
  }

  public byte[] geraSeguroNacional(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

   // // // System.out.println(" RN ");

    this.inicioTransacao();
    Conhecimento_Internacional_Nota_FiscalBD = new Conhecimento_Internacional_Nota_FiscalBD(this.sql);
    byte[] b = Conhecimento_Internacional_Nota_FiscalBD.geraSeguroNacional(ed);
    this.fimTransacao(false);
    return b;
  }

  public byte[] geraRelConhecTrocaNota(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

    this.inicioTransacao();
    Conhecimento_Internacional_Nota_FiscalBD = new Conhecimento_Internacional_Nota_FiscalBD(this.sql);
    byte[] b = Conhecimento_Internacional_Nota_FiscalBD.geraRelConhecTrocaNota(ed);
    this.fimTransacao(false);
    return b;
  }

  ///### Panazzolo 17062003
  public byte[] geraRelMercadoriasEmbarcadas(Conhecimento_Internacional_Nota_FiscalED ed)throws Excecoes{

    this.inicioTransacao();
    Conhecimento_Internacional_Nota_FiscalBD = new Conhecimento_Internacional_Nota_FiscalBD(this.sql);
    byte[] b = Conhecimento_Internacional_Nota_FiscalBD.geraRelMercadoriasEmbarcadas(ed);
    this.fimTransacao(false);
    return b;
  }



}