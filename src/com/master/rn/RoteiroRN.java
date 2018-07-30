package com.master.rn;

import java.util.ArrayList;

import com.master.bd.RoteiroBD;
import com.master.ed.RoteiroED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;

public class RoteiroRN extends Transacao  {
  RoteiroBD RoteiroBD = null;
  private ExecutaSQL executasql;


  public RoteiroRN() {
  }

  public RoteiroED inclui(RoteiroED ed)throws Excecoes{

    RoteiroED conED = new RoteiroED();

    try{

      this.inicioTransacao();
      RoteiroBD = new RoteiroBD(this.sql);
      conED = RoteiroBD.inclui(ed);
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

  public void altera(RoteiroED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      RoteiroBD = new RoteiroBD(this.sql);
      RoteiroBD.altera(ed);

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

  public void deleta(RoteiroED ed)throws Excecoes{

    if (1 == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Roteiro não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      RoteiroBD = new RoteiroBD(this.sql);
      RoteiroBD.deleta(ed);

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

  public ArrayList lista(RoteiroED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new RoteiroBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ArrayList lista_Dinamica(RoteiroED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new RoteiroBD(sql).lista_Dinamica(ed);
      this.fimTransacao(false);
      return lista;
  }

  public RoteiroED getByRecord(RoteiroED ed)throws Excecoes{

    RoteiroED edVolta = new RoteiroED();

    try{

      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      //atribui ao ed de retorno
      edVolta = new RoteiroBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Roteiro");
      excecoes.setMetodo("getByRecord(RoteiroED ed)");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

      return edVolta;
  }


  public byte[] geraRelRotas(RoteiroED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    RoteiroBD = new RoteiroBD(this.sql);
    byte[] b = RoteiroBD.geraRelRotas(ed);
    this.fimTransacao(false);
    return b;
  }

}