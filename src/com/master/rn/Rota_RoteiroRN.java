package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Rota_RoteiroBD;
import com.master.ed.Rota_RoteiroED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Rota_RoteiroRN extends Transacao  {
  Rota_RoteiroBD Rota_RoteiroBD = null;


  public Rota_RoteiroRN() {
    //Rota_Roteirobd = new Rota_RoteiroBD(this.sql);
  }

  public Rota_RoteiroED inclui(Rota_RoteiroED ed)throws Excecoes{

    Rota_RoteiroED Rota_RoteiroED = new Rota_RoteiroED();

    if (ed.getCD_Roteiro().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Nota_Fiscal não foi informado !!!10");
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
      Rota_RoteiroBD = new Rota_RoteiroBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Rota_RoteiroED = Rota_RoteiroBD.inclui(ed);

      //faz o commit em cima do objeto transacao
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

      return Rota_RoteiroED;
  }

  public void altera(Rota_RoteiroED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Rota_Roteiro()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Rota_Roteiro não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Rota_RoteiroBD = new Rota_RoteiroBD(this.sql);
      Rota_RoteiroBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de alteração");

      this.abortaTransacao();

      throw exc;
    }

  }

  public void deleta(Rota_RoteiroED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Rota_Roteiro()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Rota_Roteiro não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Rota_RoteiroBD = new Rota_RoteiroBD(this.sql);
      Rota_RoteiroBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de exclusão");

      this.abortaTransacao();

      throw exc;
    }

  }

  public ArrayList lista(Rota_RoteiroED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Rota_RoteiroBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Rota_RoteiroED getByRecord(Rota_RoteiroED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Rota_RoteiroED edVolta = new Rota_RoteiroED();
      //atribui ao ed de retorno
      edVolta = new Rota_RoteiroBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(Rota_RoteiroED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Rota_RoteiroBD = new Rota_RoteiroBD(this.sql);
    Rota_RoteiroBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

}