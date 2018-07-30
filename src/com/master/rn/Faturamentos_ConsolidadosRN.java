package com.master.rn;

import java.util.ArrayList;

import com.master.bd.DuplicataBD;
import com.master.bd.Faturamentos_ConsolidadosBD;
import com.master.ed.Faturamentos_ConsolidadosED;
import com.master.ed.Manifesto_InternacionalED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Faturamentos_ConsolidadosRN extends Transacao  {
    Faturamentos_ConsolidadosBD fcBD = null;
 

  public Faturamentos_ConsolidadosRN() {
    //Manifesto_Internacionalbd = new Manifesto_InternacionalBD(this.sql);
  }

  public Faturamentos_ConsolidadosED inclui(Faturamentos_ConsolidadosED ed)throws Excecoes{

      Faturamentos_ConsolidadosED fcED = new Faturamentos_ConsolidadosED();


    try{

      this.inicioTransacao();

      fcED = new Faturamentos_ConsolidadosBD(this.sql).inclui(ed);

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

    return fcED;

  }

  public void altera(Faturamentos_ConsolidadosED ed)throws Excecoes{

    try{

      this.inicioTransacao();
      
      new Faturamentos_ConsolidadosBD(this.sql).altera(ed);

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

  public void deleta(Faturamentos_ConsolidadosED ed)throws Excecoes{

    try{

      this.inicioTransacao();
      
      new Faturamentos_ConsolidadosBD(this.sql).deleta(ed);

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

  public ArrayList lista(Faturamentos_ConsolidadosED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Faturamentos_ConsolidadosBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Faturamentos_ConsolidadosED getByOidFatConsolidado(Faturamentos_ConsolidadosED ed)throws Excecoes{
      this.inicioTransacao();
      try {
          return new Faturamentos_ConsolidadosBD(this.sql).getByOidFatConsolidado(ed);
      } finally{
          this.fimTransacao(false);
      }
  }

  public Faturamentos_ConsolidadosED getCRTConsolidados(Faturamentos_ConsolidadosED ed)throws Excecoes{
      this.inicioTransacao();
      try {
          return new Faturamentos_ConsolidadosBD(this.sql).getCRTConsolidados(ed);
      } finally{
          this.fimTransacao(false);
      }
  }
  
  public Faturamentos_ConsolidadosED getCRTByMIC(Faturamentos_ConsolidadosED ed, boolean setCliente, boolean consolidado)throws Excecoes{
      this.inicioTransacao();
      Faturamentos_ConsolidadosED edVolta = new Faturamentos_ConsolidadosBD(this.sql).getCRTByMIC(ed, setCliente, consolidado);
      this.fimTransacao(false);

      return edVolta;
  }
  
  public void gera_Relatorio_Consolidacao_MIC_CRT(Faturamentos_ConsolidadosED ed)throws Excecoes{

      this.inicioTransacao();
      try {
          if ("MIC".equals(ed.getDm_Tipo_Relatorio())) {
              new Faturamentos_ConsolidadosBD(this.sql).gera_Relatorio_MIC_Consolidado(ed);
          }
          else if ("MIC_CRT".equals(ed.getDm_Tipo_Relatorio())) {
              new Faturamentos_ConsolidadosBD(this.sql).gera_Relatorio_MIC_CRT_Consolidado(ed);
          }
      }
      finally {
          this.fimTransacao(false);
      }

  }
  
  
  public void imprimeFaturaProform(Faturamentos_ConsolidadosED ed)throws Excecoes{

      this.inicioTransacao();
      try{
      	new Faturamentos_ConsolidadosBD(this.sql).imprimeFaturaProform(ed);
      }
      finally {
          this.fimTransacao(false);
      }

  }
  
  public void Consolida(Faturamentos_ConsolidadosED ed)throws Excecoes{

      try{
        this.inicioTransacao();
        new Faturamentos_ConsolidadosBD(this.sql).Consolida(ed);
        this.fimTransacao(true);
      }
      catch(Excecoes exc){
          this.abortaTransacao();
          throw exc;
      }
      catch(Exception e){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao Consolidar");
        excecoes.setMetodo("Consolida()");
        excecoes.setExc(e);
        //faz rollback pois deu algum erro
        this.abortaTransacao();
        throw excecoes;
      }

    }
  

}