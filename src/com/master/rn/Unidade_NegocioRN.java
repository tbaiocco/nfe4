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
import com.master.bd.Unidade_NegocioBD;
import com.master.ed.Unidade_NegocioED;
import java.util.*;

public class Unidade_NegocioRN extends Transacao  {
  Unidade_NegocioBD Unidade_NegocioBD = null;


  public Unidade_NegocioRN() {
    //Unidade_Negociobd = new Unidade_NegocioBD(this.sql);
  }

  public Unidade_NegocioED inclui(Unidade_NegocioED ed)throws Excecoes{

    Unidade_NegocioED Unidade_NegocioED = new Unidade_NegocioED();

    try{

      this.inicioTransacao();

      Unidade_NegocioBD = new Unidade_NegocioBD(this.sql);

      Unidade_NegocioED = Unidade_NegocioBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    }

    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir grupo de conta");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return Unidade_NegocioED;

  }

  public void altera(Unidade_NegocioED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Unidade_NegocioBD = new Unidade_NegocioBD(this.sql);
      Unidade_NegocioBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar grupo de conta");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

  public void deleta(Unidade_NegocioED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Unidade_NegocioBD = new Unidade_NegocioBD(this.sql);
      Unidade_NegocioBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
   }

  }

  public ArrayList lista(Unidade_NegocioED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Unidade_NegocioBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Unidade_NegocioED getByRecord(Unidade_NegocioED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Unidade_NegocioED edVolta = new Unidade_NegocioED();
      //atribui ao ed de retorno
      edVolta = new Unidade_NegocioBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(Unidade_NegocioED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Unidade_NegocioBD = new Unidade_NegocioBD(this.sql);
    Unidade_NegocioBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }
  public Unidade_NegocioED getByRecordUnidade(Unidade_NegocioED ed)throws Excecoes{
      this.inicioTransacao();
      Unidade_NegocioED edVolta = new Unidade_NegocioED();
      edVolta = new Unidade_NegocioBD(this.sql).getByRecordUnidade(ed);
      this.fimTransacao(false);
      return edVolta;
  }


  public ArrayList lista_Unidade(Unidade_NegocioED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Unidade_NegocioBD(sql).lista_Unidade(ed);
      this.fimTransacao(false);
      return lista;
  }

}
