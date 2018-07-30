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

import com.master.bd.FaturamentoCRTBD;
import com.master.ed.FaturamentoCRTED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class FaturamentoCRTRN extends Transacao  {
  FaturamentoCRTBD FaturamentoCRTBD = null;


  public FaturamentoCRTRN() {
  }






  public ArrayList inclui(FaturamentoCRTED ed)throws Excecoes{

    ArrayList lista = new ArrayList();

    try{
      this.inicioTransacao();
      lista = new FaturamentoCRTBD(sql).inclui(ed);
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

      return lista;

  }

  public void altera(FaturamentoCRTED ed)throws Excecoes{

    if (ed.getOID_Pessoa().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do FaturamentoCRT não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      FaturamentoCRTBD = new FaturamentoCRTBD(this.sql);
      FaturamentoCRTBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){throw exc;}

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

  public void deleta(FaturamentoCRTED ed)throws Excecoes{

    if (1 == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Faturamento não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      FaturamentoCRTBD = new FaturamentoCRTBD(this.sql);
      FaturamentoCRTBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){throw exc;}

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

  public ArrayList lista(FaturamentoCRTED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new FaturamentoCRTBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public FaturamentoCRTED getByRecord(FaturamentoCRTED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      FaturamentoCRTED edVolta = new FaturamentoCRTED();
      //atribui ao ed de retorno
      edVolta = new FaturamentoCRTBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

//  public void geraRelatorio(FaturamentoED ed)throws Excecoes{
//
//    //antes de invocar chamada ao relatorio deve-se
//    //fazer validacoes de regra de negocio
//
//    this.inicioTransacao();
//    FaturamentoBD = new FaturamentoBD(this.sql);
//    FaturamentoBD.geraRelatorio(ed);
//    this.fimTransacao(false);

//  }

}
