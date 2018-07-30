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

import com.master.bd.Documento_Lote_FornecedorBD;
import com.master.bd.Movimento_ConhecimentoBD;
import com.master.ed.Documento_Lote_FornecedorED;
import com.master.ed.Movimento_ConhecimentoED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Documento_Lote_FornecedorRN extends Transacao  {
  Documento_Lote_FornecedorBD Documento_Lote_FornecedorBD = null;
  Movimento_ConhecimentoBD Movimento_ConhecimentoBD = null;


  public Documento_Lote_FornecedorRN() {
    //Documento_Lote_Fornecedorbd = new Documento_Lote_FornecedorBD(this.sql);
  }

  public Documento_Lote_FornecedorED inclui(Documento_Lote_FornecedorED ed)throws Excecoes{

    Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED();
    Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED();

    try{

      this.inicioTransacao();

      Documento_Lote_FornecedorBD = new Documento_Lote_FornecedorBD(this.sql);
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);

      edVolta = Documento_Lote_FornecedorBD.inclui(ed);

/*
      movimento_ConhecimentoED.setOID_Fornecedor(ed.getOID_Pessoa());
      movimento_ConhecimentoED.setDT_Movimento_Conhecimento(Data.getDataDMY());
      movimento_ConhecimentoED.setHR_Movimento_Conhecimento(Data.getHoraHM());
      movimento_ConhecimentoED.setOID_Tipo_Movimento(ed.getOID_Tipo_Movimento());
      movimento_ConhecimentoED.setOID_Conhecimento(ed.getOID_Conhecimento());
      movimento_ConhecimentoED.setDM_Tipo_Movimento("D");
      movimento_ConhecimentoED.setDM_Lancado_Gerado("L");
      movimento_ConhecimentoED.setNM_Pessoa_Entrega("");
      movimento_ConhecimentoED.setVL_Realizado(ed.getVL_Cobrado());
      movimento_ConhecimentoED.setOID_Lote_Fornecedor(ed.getOID_Lote_Fornecedor());
      movimento_ConhecimentoED.setNR_Documento(ed.getNR_Master());
      // System.out.println("APROV NR Doc === " + movimento_ConhecimentoED.getNR_Documento());
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);
      Movimento_ConhecimentoBD.atualiza_Custo_Realizado(movimento_ConhecimentoED);

      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);
      Movimento_ConhecimentoBD.calcula_Margem(movimento_ConhecimentoED);
*/

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

    return edVolta;

  }

  public Documento_Lote_FornecedorED inclui_Cto_Filtro(Documento_Lote_FornecedorED ed)throws Excecoes{

    Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED();
    try{
      this.inicioTransacao();
      Documento_Lote_FornecedorBD = new Documento_Lote_FornecedorBD(this.sql);
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);

      edVolta = Documento_Lote_FornecedorBD.inclui_Cto_Filtro(ed);
      this.fimTransacao(true);
    }

    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("inclui_Cto_Filtro");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();
      throw excecoes;
    }
    return edVolta;
  }

  public Documento_Lote_FornecedorED inclui_Fatura_Postagem(Documento_Lote_FornecedorED ed)throws Excecoes{

    Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED();
    try{
      this.inicioTransacao();
      Documento_Lote_FornecedorBD = new Documento_Lote_FornecedorBD(this.sql);
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);

      edVolta = Documento_Lote_FornecedorBD.inclui_Fatura_Postagem(ed);
      this.fimTransacao(true);
    }

    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("inclui_Cto_Filtro");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();
      throw excecoes;
    }
    return edVolta;
  }

  public Documento_Lote_FornecedorED inclui_Fatura_Master(Documento_Lote_FornecedorED ed)throws Excecoes{

    Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED();
    try{
      this.inicioTransacao();
      Documento_Lote_FornecedorBD = new Documento_Lote_FornecedorBD(this.sql);
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);

      edVolta = Documento_Lote_FornecedorBD.inclui_Fatura_Master(ed);
      this.fimTransacao(true);
    }

    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("inclui_Cto_Filtro");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();
      throw excecoes;
    }
    return edVolta;
  }

  public Documento_Lote_FornecedorED deleta_Cto_Filtro(Documento_Lote_FornecedorED ed)throws Excecoes{

    Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED();
    try{
      this.inicioTransacao();
      Documento_Lote_FornecedorBD = new Documento_Lote_FornecedorBD(this.sql);
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);

      edVolta = Documento_Lote_FornecedorBD.deleta_Cto_Filtro(ed);
      this.fimTransacao(true);
    }

    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("inclui_Cto_Filtro");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();
      throw excecoes;
    }
    return edVolta;
  }

  public Documento_Lote_FornecedorED rateio_Cto_Filtro(Documento_Lote_FornecedorED ed)throws Excecoes{

    Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED();
    try{
      this.inicioTransacao();
      Documento_Lote_FornecedorBD = new Documento_Lote_FornecedorBD(this.sql);
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);

      edVolta = Documento_Lote_FornecedorBD.rateio_Cto_Filtro(ed);
      this.fimTransacao(true);
    }

    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("inclui_Cto_Filtro");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();
      throw excecoes;
    }
    return edVolta;
  }

  public Documento_Lote_FornecedorED inclui_Ordem_Frete(Documento_Lote_FornecedorED ed)throws Excecoes{

    Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED();
    Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED();

    try{

      this.inicioTransacao();

      Documento_Lote_FornecedorBD = new Documento_Lote_FornecedorBD(this.sql);

      edVolta = Documento_Lote_FornecedorBD.inclui_Ordem_Frete(ed);

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

    return edVolta;

  }

  public Documento_Lote_FornecedorED inclui_Master(Documento_Lote_FornecedorED ed)throws Excecoes{

    Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED();

    try{

      this.inicioTransacao();

      Documento_Lote_FornecedorBD = new Documento_Lote_FornecedorBD(this.sql);

      edVolta = Documento_Lote_FornecedorBD.inclui_Master(ed);

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

    return edVolta;

  }

  public Documento_Lote_FornecedorED inclui_Manifesto(Documento_Lote_FornecedorED ed)throws Excecoes{

    Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED();

    try{

      this.inicioTransacao();

      Documento_Lote_FornecedorBD = new Documento_Lote_FornecedorBD(this.sql);

      edVolta = Documento_Lote_FornecedorBD.inclui_Manifesto(ed);

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

    return edVolta;

  }


  public void altera(Documento_Lote_FornecedorED ed)throws Excecoes{

    Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED();
    Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED();

    try{

      this.inicioTransacao();

      Documento_Lote_FornecedorBD = new Documento_Lote_FornecedorBD(this.sql);
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);

      Documento_Lote_FornecedorBD.altera(ed);

      movimento_ConhecimentoED.setOID_Fornecedor(ed.getOID_Pessoa());
      movimento_ConhecimentoED.setDT_Movimento_Conhecimento(Data.getDataDMY());
      movimento_ConhecimentoED.setHR_Movimento_Conhecimento(Data.getHoraHM());
      movimento_ConhecimentoED.setOID_Tipo_Movimento(ed.getOID_Tipo_Movimento());
      movimento_ConhecimentoED.setOID_Conhecimento(ed.getOID_Conhecimento());
      movimento_ConhecimentoED.setDM_Tipo_Movimento("D");
      movimento_ConhecimentoED.setDM_Lancado_Gerado("L");
      movimento_ConhecimentoED.setNM_Pessoa_Entrega("");
      movimento_ConhecimentoED.setVL_Realizado(ed.getVL_Cobrado());
      movimento_ConhecimentoED.setOID_Lote_Fornecedor(ed.getOID_Lote_Fornecedor());

      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);
      Movimento_ConhecimentoBD.atualiza_Custo_Realizado(movimento_ConhecimentoED);

      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD(this.sql);
      Movimento_ConhecimentoBD.calcula_Margem(movimento_ConhecimentoED);

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

  }




  public void deleta(Documento_Lote_FornecedorED ed)throws Excecoes{


    try{

      this.inicioTransacao();

      Documento_Lote_FornecedorBD = new Documento_Lote_FornecedorBD(this.sql);
      Documento_Lote_FornecedorBD.deleta(ed);

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

  }

  public ArrayList lista(Documento_Lote_FornecedorED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Documento_Lote_FornecedorBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }


  public Documento_Lote_FornecedorED getByRecord(Documento_Lote_FornecedorED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED();
      //atribui ao ed de retorno
      edVolta = new Documento_Lote_FornecedorBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }


}
