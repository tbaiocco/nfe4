package com.master.rn;

/**
 * <p>Title: Modelo da Nota Fiscal de parametrização</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Delta Guia </p>
 * @author Claudia Galmarini Welter
 * @version 1.0
 */

import java.util.ArrayList;

import com.master.bd.ModeloNotaFiscalBD;
import com.master.ed.ModeloNotaFiscalED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;


public class ModeloNotaFiscalRN extends Transacao  {
  ModeloNotaFiscalBD ModeloNotaFiscalBD = null;


  public ModeloNotaFiscalRN() {
    //ModeloNotaFiscalbd = new ModeloNotaFiscalBD(this.sql);
  }

  public ModeloNotaFiscalED inclui(ModeloNotaFiscalED ed)throws Excecoes{

    ModeloNotaFiscalED manED = new ModeloNotaFiscalED();


    try{
      this.inicioTransacao();
      ModeloNotaFiscalBD = new ModeloNotaFiscalBD(this.sql);
      manED = ModeloNotaFiscalBD.inclui(ed);
      manED = ModeloNotaFiscalBD.associa(manED);
      this.fimTransacao(true);
    }

    catch(Excecoes exc){throw exc;}

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

    return manED;

  }

  public void altera(ModeloNotaFiscalED ed)throws Excecoes{
    try{
      this.inicioTransacao();

      ModeloNotaFiscalBD = new ModeloNotaFiscalBD(this.sql);
      ModeloNotaFiscalBD.altera(ed);
      ModeloNotaFiscalBD.alteraAssociacao(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){throw exc;}

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

  public void deleta(ModeloNotaFiscalED ed)throws Excecoes{
    try{

      this.inicioTransacao();

      ModeloNotaFiscalBD = new ModeloNotaFiscalBD(this.sql);
      // System.out.println("Chamando por operacao deleta");
      ModeloNotaFiscalBD.deleta(ed);
      ModeloNotaFiscalBD.excluiAssociacao(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){throw exc;}

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

  public ArrayList lista(ModeloNotaFiscalED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new ModeloNotaFiscalBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ModeloNotaFiscalED getByRecord(ModeloNotaFiscalED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      ModeloNotaFiscalED edVolta = new ModeloNotaFiscalED();
      //atribui ao ed de retorno
      edVolta = new ModeloNotaFiscalBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

    public ArrayList getByNM_Nota_Fiscal(ModeloNotaFiscalED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      ArrayList edVolta = new ArrayList();
      //atribui ao ed de retorno
      edVolta = new ModeloNotaFiscalBD(this.sql).getByNM_Nota_Fiscal(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }



}