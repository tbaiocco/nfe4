package com.master.rn;

/**
 * <p>Title:  </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.ArrayList;

import com.master.bd.Ocorrencia_Tabela_FreteBD;
import com.master.ed.Ocorrencia_Tabela_FreteED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Ocorrencia_Tabela_FreteRN extends Transacao  {
  Ocorrencia_Tabela_FreteBD Ocorrencia_Tabela_FreteBD = null;


  public Ocorrencia_Tabela_FreteRN() {
    //Ocorrencia_Tabela_Fretebd = new Ocorrencia_Tabela_FreteBD(this.sql);
  }

  public Ocorrencia_Tabela_FreteED inclui(Ocorrencia_Tabela_FreteED ed)throws Excecoes{

    Ocorrencia_Tabela_FreteED ocorrencia_Tabela_FreteED = new Ocorrencia_Tabela_FreteED();

    if (ed.getOID_Tabela_Frete() == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Tabela_Frete não foi informado !!!");
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
      Ocorrencia_Tabela_FreteBD = new Ocorrencia_Tabela_FreteBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      ocorrencia_Tabela_FreteED = Ocorrencia_Tabela_FreteBD.inclui(ed);

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

      return ocorrencia_Tabela_FreteED;
  }

  public void altera(Ocorrencia_Tabela_FreteED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Ocorrencia_Tabela_Frete()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Ocorrencia_Tabela_Frete não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Ocorrencia_Tabela_FreteBD = new Ocorrencia_Tabela_FreteBD(this.sql);
      Ocorrencia_Tabela_FreteBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de alteração");

      this.abortaTransacao();

      throw exc;
    }

  }

  public void deleta(Ocorrencia_Tabela_FreteED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Ocorrencia_Tabela_Frete()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Ocorrencia_Tabela_Frete não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Ocorrencia_Tabela_FreteBD = new Ocorrencia_Tabela_FreteBD(this.sql);
      Ocorrencia_Tabela_FreteBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de exclusão");

      this.abortaTransacao();

      throw exc;
    }

  }

  public ArrayList lista(Ocorrencia_Tabela_FreteED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Ocorrencia_Tabela_FreteBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Ocorrencia_Tabela_FreteED getByRecord(Ocorrencia_Tabela_FreteED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Ocorrencia_Tabela_FreteED edVolta = new Ocorrencia_Tabela_FreteED();
      //atribui ao ed de retorno
      edVolta = new Ocorrencia_Tabela_FreteBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

}