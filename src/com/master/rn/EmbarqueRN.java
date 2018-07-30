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

import com.master.bd.EmbarqueBD;
import com.master.ed.EmbarqueED;
import com.master.ed.EmbarqueRelED;
import com.master.ed.ReceitaDespesaED;

import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import javax.servlet.http.HttpServletResponse;


public class EmbarqueRN extends Transacao  {
  EmbarqueBD EmbarqueBD = null;


  public EmbarqueRN() {
    //Embarquebd = new EmbarqueBD(this.sql);
  }

  public EmbarqueED inclui(EmbarqueED ed)throws Excecoes{
  
    EmbarqueED manED = new EmbarqueED();


    if (ed.getNR_Placa().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Placa não foi informada !!!");
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
      EmbarqueBD = new EmbarqueBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      manED = EmbarqueBD.inclui(ed);

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

    return manED;

  }

  public void altera(EmbarqueED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Embarque()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Embarque não foi informado !!!");
      throw exc;
    }

    try{

      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

      Calendar calDtPgto = Calendar.getInstance();

      Date dataEmissao = formatter.parse(ed.getDT_Emissao());
      Calendar calDtEmissao = Calendar.getInstance();
      calDtEmissao.setTime(dataEmissao);

      Date dataHoje = new Date();
      Calendar calDataHoje = Calendar.getInstance();
      calDataHoje.setTime(dataHoje);

      if (String.valueOf(ed.getDT_Saida()) != null &&
        !String.valueOf(ed.getDT_Saida()).equals("") &&
        !String.valueOf(ed.getDT_Saida()).equals("null")){
        Date saida = formatter.parse(ed.getDT_Saida());
        calDtPgto.setTime(saida);
        if (calDtPgto.after(calDataHoje) || calDtEmissao.after(calDtPgto)){
          Excecoes exc = new Excecoes();
          exc.setMensagem("Data da saída tem de ser menor ou igual a hoje");
          exc.setClasse(this.getClass().getName());
          exc.setMetodo("Altera(EmbarqueED)");
          throw exc;
        }
      }

      if (String.valueOf(ed.getDT_Nova_Previsao_Chegada()) != null &&
        !String.valueOf(ed.getDT_Nova_Previsao_Chegada()).equals("") &&
        !String.valueOf(ed.getDT_Nova_Previsao_Chegada()).equals("null")){
        Date Nova_Previsao_Chegada = formatter.parse(ed.getDT_Nova_Previsao_Chegada());
        calDtPgto.setTime(Nova_Previsao_Chegada);
        if (calDtEmissao.after(calDtPgto)){
          Excecoes exc = new Excecoes();
          exc.setMensagem("Data da nova previsao de chegada tem de ser menor ou igual a hoje");
          exc.setClasse(this.getClass().getName());
          exc.setMetodo("Altera(EmbarqueED)");
          throw exc;
        }
      }

      if (String.valueOf(ed.getDT_Entrega()) != null &&
        !String.valueOf(ed.getDT_Entrega()).equals("") &&
        !String.valueOf(ed.getDT_Entrega()).equals("null")){
        Date Entrega = formatter.parse(ed.getDT_Entrega());
        calDtPgto.setTime(Entrega);
        if (calDtPgto.after(calDataHoje) || calDtEmissao.after(calDtPgto)){
          Excecoes exc = new Excecoes();
          exc.setMensagem("Data da entrega tem de ser menor ou igual a hoje");
          exc.setClasse(this.getClass().getName());
          exc.setMetodo("Altera(EmbarqueED)");
          throw exc;
        }
      }

      if (String.valueOf(ed.getDT_Chegada()) != null &&
        !String.valueOf(ed.getDT_Chegada()).equals("") &&
        !String.valueOf(ed.getDT_Chegada()).equals("null")){
        Date Chegada = formatter.parse(ed.getDT_Chegada());
        calDtPgto.setTime(Chegada);
        if (calDtPgto.after(calDataHoje) || calDtEmissao.after(calDtPgto)){
          Excecoes exc = new Excecoes();
          exc.setMensagem("Data da chegada tem de ser menor ou igual a hoje");
          exc.setClasse(this.getClass().getName());
          exc.setMetodo("Altera(EmbarqueED)");
          throw exc;
        }
      }

      if (String.valueOf(ed.getDT_Previsao_Chegada()) != null &&
        !String.valueOf(ed.getDT_Previsao_Chegada()).equals("") &&
        !String.valueOf(ed.getDT_Previsao_Chegada()).equals("null")){
        Date dataPagamento = formatter.parse(ed.getDT_Previsao_Chegada());
        calDtPgto.setTime(dataPagamento);
        if (calDtEmissao.after(calDtPgto)){
          Excecoes exc = new Excecoes();
          exc.setMensagem("Data previsão de chegada tem de ser maior ou igual a data de hoje.");
          exc.setClasse(this.getClass().getName());
          exc.setMetodo("Altera(EmbarqueED)");
          throw exc;
        }
      }

      this.inicioTransacao();

      EmbarqueBD = new EmbarqueBD(this.sql);
      EmbarqueBD.altera(ed);

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

  }

  public void deleta(EmbarqueED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Embarque()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Embarque não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      EmbarqueBD = new EmbarqueBD(this.sql);
      EmbarqueBD.deleta(ed);

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

  }

  public ArrayList lista(EmbarqueED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new EmbarqueBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ArrayList lista_Traking(EmbarqueED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new EmbarqueBD(sql).lista_Traking(ed);
      this.fimTransacao(false);
      return lista;
  }

  public EmbarqueED getByRecord(EmbarqueED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      EmbarqueED edVolta = new EmbarqueED();
      //atribui ao ed de retorno
      edVolta = new EmbarqueBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(EmbarqueED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    EmbarqueBD = new EmbarqueBD(this.sql);
    EmbarqueBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista_Localizacao_Veiculos(String orderby, String dm_procedencia, String placa)throws Excecoes{

    // System.out.println(" NR chegou!!!!!!!!!!!!!!");


    //retorna um arraylist de ED´s
    this.inicioTransacao();
    ArrayList lista = new EmbarqueBD(sql).lista_Localizacao_Veiculos( orderby, dm_procedencia, placa);
    this.fimTransacao(false);
    return lista;
}

  public ArrayList lista_Localizacao_Veiculos_Placar(String orderby, String dm_procedencia, String placa)throws Excecoes{

    //retorna um arraylist de ED´s
    this.inicioTransacao();
    ArrayList lista = new EmbarqueBD(sql).lista_Localizacao_Veiculos_Placar( orderby, dm_procedencia, placa);
    this.fimTransacao(true);
    return lista;
}
  
  public void geraInformeEmbarque(EmbarqueED ed, HttpServletResponse response)throws Excecoes{

    	//antes de invocar chamada ao relatorio deve-se
    	//fazer validacoes de regra de negocio

    	this.inicioTransacao();
    	EmbarqueBD = new EmbarqueBD(this.sql);
    	EmbarqueBD.geraInformeEmbarque(ed, response);
    	this.fimTransacao(false);

    }
  
  public void relEmbarque(EmbarqueED ed, HttpServletResponse response)throws Excecoes{

  	//antes de invocar chamada ao relatorio deve-se
  	//fazer validacoes de regra de negocio

  	this.inicioTransacao();
  	EmbarqueBD = new EmbarqueBD(this.sql);
  	EmbarqueBD.relEmbarque(ed, response);
  	this.fimTransacao(false);

  }
  
  public void relChegadaEntrega(EmbarqueED ed, HttpServletResponse response)throws Excecoes{

    	//antes de invocar chamada ao relatorio deve-se
    	//fazer validacoes de regra de negocio

    	this.inicioTransacao();
    	EmbarqueBD = new EmbarqueBD(this.sql);
    	EmbarqueBD.relChegadaEntrega(ed, response);
    	this.fimTransacao(false);

    }
  
  public void relTransitTime(EmbarqueED ed, HttpServletResponse response)throws Excecoes{

  	//antes de invocar chamada ao relatorio deve-se
  	//fazer validacoes de regra de negocio

  	this.inicioTransacao();
  	EmbarqueBD = new EmbarqueBD(this.sql);
  	EmbarqueBD.relTransitTime(ed, response);
  	this.fimTransacao(false);

  }
  
  
  public void relCRT_Armazem(EmbarqueED ed, HttpServletResponse response)throws Excecoes{

    	//antes de invocar chamada ao relatorio deve-se
    	//fazer validacoes de regra de negocio

    	this.inicioTransacao();
    	EmbarqueBD = new EmbarqueBD(this.sql);
    	if(ed.getDM_Procedencia().equals("A"))
    	    EmbarqueBD.relArmazenagem(ed, response);
    	else EmbarqueBD.relCRT_Armazem(ed, response);
    	this.fimTransacao(false);

    }
  
  public void geraControleViagem(EmbarqueED ed, HttpServletResponse response)throws Excecoes{

  	//antes de invocar chamada ao relatorio deve-se
  	//fazer validacoes de regra de negocio

  	this.inicioTransacao();
  	EmbarqueBD = new EmbarqueBD(this.sql);
  	EmbarqueBD.geraControleViagem(ed, response);
  	this.fimTransacao(false);

  }

}
