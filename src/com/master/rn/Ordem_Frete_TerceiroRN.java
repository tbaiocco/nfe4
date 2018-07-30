package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.master.bd.Ordem_Frete_TerceiroBD;
import com.master.ed.Ordem_Frete_TerceiroED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Ordem_Frete_TerceiroRN extends Transacao  {
  Ordem_Frete_TerceiroBD Ordem_Frete_TerceiroBD = null;


  public Ordem_Frete_TerceiroRN() {
    //Ordem_Frete_Terceirobd = new Ordem_Frete_TerceiroBD(this.sql);
  }

  public Ordem_Frete_TerceiroED inclui(Ordem_Frete_TerceiroED ed)throws Excecoes{

    Ordem_Frete_TerceiroED ordem_FreteED = new Ordem_Frete_TerceiroED();

    if (String.valueOf(ed.getOID_Unidade()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código da Unidade não foi informado !!!");
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

      if (DT_Emissao_Calendario.after(DT_Hoje_Calendario)){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Data emissão tem de ser menor ou igual a data de hoje.");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("inclui(Ordem_Frete_TerceiroED ed");
        throw exc;
      }

      if (ed.getVL_Adiantamento() < 0){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Valor do adiantamento menor ou igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("inclui(Ordem_Frete_TerceiroED ed");
        throw exc;
      }

      if (ed.getVL_Ordem_Frete_Terceiro() < 0){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Valor da ordem de frete menor ou igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("inclui(Ordem_Frete_TerceiroED ed");
        throw exc;
      }

      if (ed.getVL_Ordem_Frete_Terceiro() < ed.getVL_Adiantamento()){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Valor da ordem de frete menor que o adiantamento");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("inclui(Ordem_Frete_TerceiroED ed");
        throw exc;
      }

      this.inicioTransacao();

      Ordem_Frete_TerceiroBD = new Ordem_Frete_TerceiroBD(this.sql);

      ordem_FreteED = Ordem_Frete_TerceiroBD.inclui(ed);

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

    return ordem_FreteED;

  }

  public void altera(Ordem_Frete_TerceiroED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Ordem_Frete_Terceiro()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Ordem_Frete_Terceiro não foi informado !!!");
      throw exc;
    }

    try{

      if (ed.getVL_Adiantamento() < 0){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Valor do adiantamento menor ou igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("altera(Ordem_Frete_TerceiroED ed");
        throw exc;
      }

      if (ed.getVL_Ordem_Frete_Terceiro() < 0){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Valor da ordem de frete menor ou igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("altera(Ordem_Frete_TerceiroED ed");
        throw exc;
      }

      if (ed.getVL_Ordem_Frete_Terceiro() < ed.getVL_Adiantamento()){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Valor da ordem de frete menor que o adiantamento");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("altera(Ordem_Frete_TerceiroED ed");
        throw exc;
      }

      this.inicioTransacao();

      Ordem_Frete_TerceiroBD = new Ordem_Frete_TerceiroBD(this.sql);
      Ordem_Frete_TerceiroBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de alteração");

      this.abortaTransacao();

      throw exc;
    }

  }

  public void deleta(Ordem_Frete_TerceiroED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Ordem_Frete_Terceiro()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Ordem_Frete_Terceiro não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Ordem_Frete_TerceiroBD = new Ordem_Frete_TerceiroBD(this.sql);
      Ordem_Frete_TerceiroBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de exclusão");

      this.abortaTransacao();

      throw exc;
    }

  }

  public void cancela(Ordem_Frete_TerceiroED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Ordem_Frete_Terceiro()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Ordem_Frete_Terceiro não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Ordem_Frete_TerceiroBD = new Ordem_Frete_TerceiroBD(this.sql);
      Ordem_Frete_TerceiroBD.cancela(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de cancelamento");

      this.abortaTransacao();

      throw exc;
    }

  }

  public ArrayList lista(Ordem_Frete_TerceiroED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Ordem_Frete_TerceiroBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Ordem_Frete_TerceiroED getByRecord(Ordem_Frete_TerceiroED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Ordem_Frete_TerceiroED edVolta = new Ordem_Frete_TerceiroED();
      //atribui ao ed de retorno
      edVolta = new Ordem_Frete_TerceiroBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public byte[] imprime_Ordem_Frete_Terceiro(Ordem_Frete_TerceiroED ed)throws Excecoes{

    byte[] b = null;

    try{
      this.inicioTransacao();
      Ordem_Frete_TerceiroBD = new Ordem_Frete_TerceiroBD(this.sql);
      b = Ordem_Frete_TerceiroBD.imprime_Ordem_Frete_Terceiro(ed);
      this.fimTransacao(true);
    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar ordem de frete");
      excecoes.setMetodo("gera_Relatorio_Ordem_Frete(Ordem_FreteED ed)");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  return b;
  }




  public byte[] geraRelOrdemFreteTerceiro(Ordem_Frete_TerceiroED ed)throws Excecoes{

    byte[] b = null;

    try{
      this.inicioTransacao();
      Ordem_Frete_TerceiroBD = new Ordem_Frete_TerceiroBD(this.sql);
      b = Ordem_Frete_TerceiroBD.geraRelOrdemFreteTerceiro(ed);
      this.fimTransacao(true);
    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar ordem de frete");
      excecoes.setMetodo("geraRelOrdemFreteTerceiro(Ordem_FreteED ed)");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  return b;
  }



}
