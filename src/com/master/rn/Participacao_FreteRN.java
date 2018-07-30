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

import com.master.bd.Participacao_FreteBD;
import com.master.ed.Participacao_FreteED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Participacao_FreteRN extends Transacao  {
  Participacao_FreteBD Participacao_FreteBD = null;


  public Participacao_FreteRN() {
    //Participacao_Fretebd = new Participacao_FreteBD(this.sql);
  }

  public Participacao_FreteED inclui(Participacao_FreteED ed)throws Excecoes{

    Participacao_FreteED manED = new Participacao_FreteED();

    try{

      this.inicioTransacao();
      Participacao_FreteBD = new Participacao_FreteBD(this.sql);

      manED = Participacao_FreteBD.inclui(ed);

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

  public void altera(Participacao_FreteED ed)throws Excecoes{


    try{


      this.inicioTransacao();

      Participacao_FreteBD = new Participacao_FreteBD(this.sql);
      Participacao_FreteBD.altera(ed);

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




  public void deleta(Participacao_FreteED ed)throws Excecoes{


    try{

      this.inicioTransacao();

      Participacao_FreteBD = new Participacao_FreteBD(this.sql);
      Participacao_FreteBD.deleta(ed);

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

  public ArrayList lista(Participacao_FreteED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Participacao_FreteBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }


  public Participacao_FreteED getByRecord(Participacao_FreteED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Participacao_FreteED edVolta = new Participacao_FreteED();
      //atribui ao ed de retorno
      edVolta = new Participacao_FreteBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }


  public byte[] imprime_Participacao_Frete(Participacao_FreteED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Participacao_FreteBD = new Participacao_FreteBD(this.sql);
    byte[] b = Participacao_FreteBD.imprime_Participacao_Frete(ed);
    this.fimTransacao(false);
    return b;
  }


  public byte[] rel_Ctos_Participacao_Frete(Participacao_FreteED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Participacao_FreteBD = new Participacao_FreteBD(this.sql);
    byte[] b = Participacao_FreteBD.rel_Ctos_Participacao_Frete(ed);
    this.fimTransacao(false);
    return b;
  }
  
}
