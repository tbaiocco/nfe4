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
import com.master.bd.Ocorrencia_EmbarqueBD;
import com.master.ed.Ocorrencia_EmbarqueED;
import java.util.*;
import java.text.*;


public class Ocorrencia_EmbarqueRN extends Transacao  {
  Ocorrencia_EmbarqueBD Ocorrencia_EmbarqueBD = null;


  public Ocorrencia_EmbarqueRN() {
    //Ocorrencia_Embarquebd = new Ocorrencia_EmbarqueBD(this.sql);
  }

  public Ocorrencia_EmbarqueED inclui(Ocorrencia_EmbarqueED ed)throws Excecoes{

    Ocorrencia_EmbarqueED ocorrencia_EmbarqueED = new Ocorrencia_EmbarqueED();

    if (String.valueOf(ed.getOID_Embarque()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Embarque não foi informado !!!10");
      throw exc;
    }

    try{

      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      Calendar DT_Ocorrencia = Calendar.getInstance();

      Date DT_Emissao = formatter.parse(ed.getDT_Emissao());
      Calendar DT_Emissao_Calendario = Calendar.getInstance();
      DT_Emissao_Calendario.setTime(DT_Emissao);

      Date DT_Hoje = new Date();
      Calendar DT_Hoje_Calendario = Calendar.getInstance();
      DT_Hoje_Calendario.setTime(DT_Hoje);

      if (String.valueOf(ed.getDT_Ocorrencia_Embarque()) != null &&
        !String.valueOf(ed.getDT_Ocorrencia_Embarque()).equals("") &&
        !String.valueOf(ed.getDT_Ocorrencia_Embarque()).equals("null")){
        Date Ocorrencia = formatter.parse(ed.getDT_Ocorrencia_Embarque());
        DT_Ocorrencia.setTime(Ocorrencia);
        if (DT_Ocorrencia.after(DT_Hoje_Calendario) || DT_Emissao_Calendario.after(DT_Ocorrencia)){
          Excecoes exc = new Excecoes();
          exc.setMensagem("Data da ocorrência tem de ser menor ou igual a hoje");
          exc.setClasse(this.getClass().getName());
          exc.setMetodo("Inclui(Ocorrencia_EmbarqueRN)");
          throw exc;
        }
      }
      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();
      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Ocorrencia_EmbarqueBD = new Ocorrencia_EmbarqueBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      ocorrencia_EmbarqueED = Ocorrencia_EmbarqueBD.inclui(ed);

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

      return ocorrencia_EmbarqueED;
  }

  public void altera(Ocorrencia_EmbarqueED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Ocorrencia_Embarque()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Ocorrencia_Embarque não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Ocorrencia_EmbarqueBD = new Ocorrencia_EmbarqueBD(this.sql);
      Ocorrencia_EmbarqueBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de alteração");

      this.abortaTransacao();

      throw exc;
    }

  }

  public void deleta(Ocorrencia_EmbarqueED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Ocorrencia_Embarque()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Ocorrencia_Embarque não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Ocorrencia_EmbarqueBD = new Ocorrencia_EmbarqueBD(this.sql);
      Ocorrencia_EmbarqueBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de exclusão");

      this.abortaTransacao();

      throw exc;
    }

  }

  public ArrayList lista(Ocorrencia_EmbarqueED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Ocorrencia_EmbarqueBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Ocorrencia_EmbarqueED getByRecord(Ocorrencia_EmbarqueED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Ocorrencia_EmbarqueED edVolta = new Ocorrencia_EmbarqueED();
      //atribui ao ed de retorno
      edVolta = new Ocorrencia_EmbarqueBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(Ocorrencia_EmbarqueED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Ocorrencia_EmbarqueBD = new Ocorrencia_EmbarqueBD(this.sql);
    Ocorrencia_EmbarqueBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}