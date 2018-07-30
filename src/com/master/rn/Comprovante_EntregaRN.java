package com.master.rn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.master.bd.Comprovante_EntregaBD;
import com.master.ed.Comprovante_EntregaED;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

public class Comprovante_EntregaRN extends Transacao  {
  Comprovante_EntregaBD Comprovante_EntregaBD = null;


  public Comprovante_EntregaRN() {
    //Comprovante_Entregabd = new Comprovante_EntregaBD(this.sql);
  }

  public Comprovante_EntregaED inclui(Comprovante_EntregaED ed)
  throws Excecoes {
    Comprovante_EntregaED manED = new Comprovante_EntregaED();
    try{
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

      Calendar calDtPgto = Calendar.getInstance();

      Date dataEmissao = formatter.parse(ed.getDT_Emissao());
      Calendar calDtEmissao = Calendar.getInstance();
      calDtEmissao.setTime(dataEmissao);

      Date dataHoje = new Date();
      Calendar calDataHoje = Calendar.getInstance();
      calDataHoje.setTime(dataHoje);

      this.inicioTransacao();

      Comprovante_EntregaBD = new Comprovante_EntregaBD(this.sql);

      manED = Comprovante_EntregaBD.inclui(ed);

      this.fimTransacao(true);

    }

    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

    return manED;

  }

  public void altera(Comprovante_EntregaED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Comprovante_EntregaBD = new Comprovante_EntregaBD(this.sql);
      Comprovante_EntregaBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

  }


  public void deleta(Comprovante_EntregaED ed) throws Excecoes{
  	if (!JavaUtil.doValida(ed.getOID_Comprovante_Entrega())){
  		Excecoes exc = new Excecoes("Código do Comprovante_Entrega não foi informado!");
  	}
  	try{
  		this.inicioTransacao();
  		new Comprovante_EntregaBD(this.sql).deleta(ed);
  		this.fimTransacao(true);
  	} catch(Excecoes exc) {
  		this.abortaTransacao();
  		throw exc;
  	} catch (RuntimeException e) {
  	    abortaTransacao();
  	    throw e;
  	}
  }

  public ArrayList lista(Comprovante_EntregaED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Comprovante_EntregaBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }


  public Comprovante_EntregaED getByRecord(Comprovante_EntregaED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Comprovante_EntregaED edVolta = new Comprovante_EntregaED();
      //atribui ao ed de retorno
      edVolta = new Comprovante_EntregaBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }


  public byte[] imprime_Comprovante_Entrega(Comprovante_EntregaED filtro)
  throws Excecoes {
      inicioTransacao();
      try {
          return new Comprovante_EntregaBD(sql).imprime_Comprovante_Entrega(filtro);
      } finally {
          fimTransacao(false);
      }
  }


  public Comprovante_EntregaED inclui_Exclui_Documento(Comprovante_EntregaED ed)
  throws Excecoes {
    Comprovante_EntregaED manED = new Comprovante_EntregaED();
    try{

      this.inicioTransacao();

      Comprovante_EntregaBD = new Comprovante_EntregaBD(this.sql);

      manED = Comprovante_EntregaBD.inclui_Exclui_Documento(ed);

      this.fimTransacao(true);

    }

    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("inclui_Exclui_Documento");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

    return manED;

  }

  public ArrayList lista_Conhecimento_Entregue(Comprovante_EntregaED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Comprovante_EntregaBD(sql).lista_Conhecimento_Entregue(ed);
      this.fimTransacao(false);
      return lista;
  }


}
