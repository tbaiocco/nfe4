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

import com.master.bd.EDI_AuxiliarBD;
import com.master.bd.RotaBD;
import com.master.ed.RotaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class RotaRN extends Transacao  {
  RotaBD RotaBD = null;


  public RotaRN() {
  }

  public RotaED inclui(RotaED ed)throws Excecoes{

    RotaED conED = new RotaED();

    try{

      this.inicioTransacao();
      RotaBD = new RotaBD(this.sql);
      conED = RotaBD.inclui(ed);
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

    return conED;

  }

  public void altera(RotaED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      RotaBD = new RotaBD(this.sql);
      RotaBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public void deleta(RotaED ed)throws Excecoes{

    if (1 == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Rota não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      RotaBD = new RotaBD(this.sql);
      RotaBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir");
      excecoes.setMetodo("excluir");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public ArrayList lista(RotaED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new RotaBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public RotaED getByRecord(RotaED ed)throws Excecoes{

    RotaED edVolta = new RotaED();

    try{

      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      //atribui ao ed de retorno
      edVolta = new RotaBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Rota");
      excecoes.setMetodo("getByRecord(RotaED ed)");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

      return edVolta;
  }

//  public byte[] geraRelatorioConheEmbarque(RotaED ed)throws Excecoes{
//
//    this.inicioTransacao();
//    RotaBD = new RotaBD(this.sql);
//    byte[] b = RotaBD.geraRelRotaEmbarque(ed);
//    this.fimTransacao(false);
//    return b;
//  }

}