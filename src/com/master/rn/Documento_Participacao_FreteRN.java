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

import com.master.bd.Documento_Participacao_FreteBD;
import com.master.ed.Documento_Participacao_FreteED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Documento_Participacao_FreteRN extends Transacao  {
  Documento_Participacao_FreteBD Documento_Participacao_FreteBD = null;


  public Documento_Participacao_FreteRN() {
    //Documento_Participacao_Fretebd = new Documento_Participacao_FreteBD(this.sql);
  }

  public Documento_Participacao_FreteED inclui(Documento_Participacao_FreteED ed)throws Excecoes{

    Documento_Participacao_FreteED edVolta = new Documento_Participacao_FreteED();

    try{

      this.inicioTransacao();

      Documento_Participacao_FreteBD = new Documento_Participacao_FreteBD(this.sql);

      edVolta = Documento_Participacao_FreteBD.inclui(ed);

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

  public Documento_Participacao_FreteED inclui_Cto_Filtro(Documento_Participacao_FreteED ed)throws Excecoes{

    Documento_Participacao_FreteED edVolta = new Documento_Participacao_FreteED();

    try{

      this.inicioTransacao();

      Documento_Participacao_FreteBD = new Documento_Participacao_FreteBD(this.sql);

      edVolta = Documento_Participacao_FreteBD.inclui_Cto_Filtro(ed);

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


  public void altera(Documento_Participacao_FreteED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Documento_Participacao_FreteBD = new Documento_Participacao_FreteBD(this.sql);

      Documento_Participacao_FreteBD.altera(ed);

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




  public void deleta(Documento_Participacao_FreteED ed)throws Excecoes{


    try{

      this.inicioTransacao();

      Documento_Participacao_FreteBD = new Documento_Participacao_FreteBD(this.sql);
      Documento_Participacao_FreteBD.deleta(ed);

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

  public ArrayList lista(Documento_Participacao_FreteED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Documento_Participacao_FreteBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }


  public Documento_Participacao_FreteED getByRecord(Documento_Participacao_FreteED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Documento_Participacao_FreteED edVolta = new Documento_Participacao_FreteED();
      //atribui ao ed de retorno
      edVolta = new Documento_Participacao_FreteBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }


}
