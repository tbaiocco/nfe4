package com.master.rn;



import java.util.ArrayList;

import com.master.bd.ConhecimentoBD;
import com.master.bd.Conhecimento_InternacionalBD;
import com.master.bd.Movimento_ConhecimentoBD;
import com.master.ed.ConhecimentoED;
import com.master.ed.Conhecimento_InternacionalED;
import com.master.ed.Movimento_ConhecimentoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Movimento_ConhecimentoRN
    extends Transacao {
  Movimento_ConhecimentoBD Movimento_ConhecimentoBD = null;
  ConhecimentoBD ConhecimentoBD = null;

  public Movimento_ConhecimentoRN () {
    //Movimento_Conhecimentobd = new Movimento_ConhecimentoBD(this.sql);
  }

  public Movimento_ConhecimentoED inclui (Movimento_ConhecimentoED ed) throws Excecoes {

    // System.out.println ("Movimento_ConhecimentoED inclui 1 ");

    if ("".equals (ed.getOID_Conhecimento ()) && "".equals (ed.getOID_Cotacao ())) {
      throw new Excecoes ("Código do Conhecimento/Cotacao não foi informado!");
    }

    Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();

    this.inicioTransacao ();
    try {
      movimento_ConhecimentoED = new Movimento_ConhecimentoBD (this.sql).inclui (ed);

      // System.out.println ("Movimento_ConhecimentoED inclui 2 ");

      if (!"".equals (ed.getOID_Conhecimento ())) {
        if ("I".equals (ed.getDM_Tipo_Movimento ())) {
          if ("I".equals (ed.getDM_Nacional_Internacional ())) {
            Conhecimento_InternacionalED conhecimentoED = new Conhecimento_InternacionalED ();
            conhecimentoED.setOID_Conhecimento (ed.getOID_Conhecimento ());
            new Conhecimento_InternacionalBD (this.sql).altera_Totais (conhecimentoED);
          }
          else {
            ConhecimentoED conhecimentoED = new ConhecimentoED ();
            conhecimentoED.setOID_Conhecimento (ed.getOID_Conhecimento ());
            ConhecimentoBD = new ConhecimentoBD (this.sql);
            ConhecimentoBD.altera_Totais (conhecimentoED);
          }
        }

        if (ed.getDM_Tipo_Movimento () != null &&
            (ed.getDM_Tipo_Movimento ().equals ("D") ||
             ed.getDM_Tipo_Movimento ().equals ("R")) &&
            !ed.getDM_Lancado_Gerado ().equals ("D") && //d=desc cto
            !ed.getDM_Lancado_Gerado ().equals ("G")) {
          // System.out.println ("RN Movimento Conhecimento inclui  calc margem ");
          Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.sql);
          movimento_ConhecimentoED = Movimento_ConhecimentoBD.calcula_Margem (ed);
        }
      }

      this.fimTransacao (true);
      return movimento_ConhecimentoED;
	  } catch(Excecoes e) {
	      this.abortaTransacao();
	      throw e;
	  } catch(RuntimeException e) {
	      this.abortaTransacao();
	      throw e;
	  }
  }

  public void altera (Movimento_ConhecimentoED ed) throws Excecoes {

    if (String.valueOf (ed.getOID_Movimento_Conhecimento ()).compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Movimento_Conhecimento não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.sql);
      Movimento_ConhecimentoBD.altera (ed);

      this.fimTransacao (true);
      this.inicioTransacao ();

      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.sql);
      Movimento_ConhecimentoBD.calcula_Margem (ed);
      
      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
        this.abortaTransacao ();
        throw exc;
      }
      catch (RuntimeException e) {
        abortaTransacao ();
        throw e;
      }

  }

  public void atualiza_Custo_Realizado (Movimento_ConhecimentoED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.sql);
      Movimento_ConhecimentoBD.atualiza_Custo_Realizado (ed);

      this.fimTransacao (true);
      this.inicioTransacao ();

      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.sql);
      Movimento_ConhecimentoBD.calcula_Margem (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
        this.abortaTransacao ();
        throw exc;
      }
      catch (RuntimeException e) {
        abortaTransacao ();
        throw e;
      }

  }

  public void deleta (Movimento_ConhecimentoED ed) throws Excecoes {

    if (String.valueOf (ed.getOID_Movimento_Conhecimento ()).compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Movimento_Conhecimento não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.sql);
      Movimento_ConhecimentoBD.deleta (ed);

      this.fimTransacao (true);
      this.inicioTransacao ();
      
      if (ed.getOID_Conhecimento () != null && ed.getOID_Conhecimento ().length () > 4) {
        Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.sql);
        Movimento_ConhecimentoBD.calcula_Margem (ed);
      }

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
        this.abortaTransacao ();
        throw exc;
      }
      catch (RuntimeException e) {
        abortaTransacao ();
        throw e;
      }

  }

  public ArrayList lista (Movimento_ConhecimentoED ed) throws Excecoes {
    ArrayList lista = null;

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    try {
      if (ed.getOID_Cotacao () != null && ed.getOID_Cotacao ().length () > 4) {
        lista = new Movimento_ConhecimentoBD (sql).listaCotacao (ed);
      }
      else {
        lista = new Movimento_ConhecimentoBD (sql).lista (ed);
      }
      return lista;
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public Movimento_ConhecimentoED getByRecord (Movimento_ConhecimentoED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Movimento_ConhecimentoED edVolta = new Movimento_ConhecimentoED ();
    //atribui ao ed de retorno
    edVolta = new Movimento_ConhecimentoBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public Movimento_ConhecimentoED consulta_Cotacao (Movimento_ConhecimentoED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Movimento_ConhecimentoED edVolta = new Movimento_ConhecimentoED ();
    //atribui ao ed de retorno
    edVolta = new Movimento_ConhecimentoBD (this.sql).consulta_Cotacao (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public void geraRelatorio (Movimento_ConhecimentoED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.sql);
    Movimento_ConhecimentoBD.geraRelatorio (ed);
    this.fimTransacao (false);

  }

  public Movimento_ConhecimentoED acerta_Margem (Movimento_ConhecimentoED ed) throws Excecoes {

    Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();

    if (ed.getOID_Conhecimento ().compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Conhecimento não foi informado !!!10");
      throw exc;
    }

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      movimento_ConhecimentoED = Movimento_ConhecimentoBD.acerta_Margem (ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao (true);

    }

    catch (Excecoes exc) {
        this.abortaTransacao ();
        throw exc;
      }
      catch (RuntimeException e) {
        abortaTransacao ();
        throw e;
      }

    return movimento_ConhecimentoED;
  }

  public Movimento_ConhecimentoED recalculaMargem (Movimento_ConhecimentoED ed) throws Excecoes {

    Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      movimento_ConhecimentoED = Movimento_ConhecimentoBD.recalculaMargem (ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao (true);

    }

    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return movimento_ConhecimentoED;
  }

  public Movimento_ConhecimentoED inclui_Rateio (Movimento_ConhecimentoED ed) throws Excecoes {

    Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();

    try {
      this.inicioTransacao ();
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.sql);
      movimento_ConhecimentoED = Movimento_ConhecimentoBD.inclui_Rateio (ed);
      this.fimTransacao (true);
    }

    catch (Excecoes exc) {
        this.abortaTransacao ();
        throw exc;
      }
      catch (RuntimeException e) {
        abortaTransacao ();
        throw e;
      }

    return movimento_ConhecimentoED;
  }

  public Movimento_ConhecimentoED deleta_Rateio (Movimento_ConhecimentoED ed) throws Excecoes {

    Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();

    try {
      this.inicioTransacao ();
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.sql);
      movimento_ConhecimentoED = Movimento_ConhecimentoBD.deleta_Rateio (ed);
      this.fimTransacao (true);
    }

    catch (Excecoes exc) {
        this.abortaTransacao ();
        throw exc;
      }
      catch (RuntimeException e) {
        abortaTransacao ();
        throw e;
      }

    return movimento_ConhecimentoED;
  }

  public Movimento_ConhecimentoED calcula_Margem (Movimento_ConhecimentoED ed) throws Excecoes {

    Movimento_ConhecimentoED movimento_ConhecimentoED = new Movimento_ConhecimentoED ();

    if (ed.getOID_Conhecimento ().compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Conhecimento não foi informado !!!10");
      throw exc;
    }

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      movimento_ConhecimentoED = Movimento_ConhecimentoBD.calcula_Margem (ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao (true);

    }

    catch (Excecoes exc) {
        this.abortaTransacao ();
        throw exc;
      }
      catch (RuntimeException e) {
        abortaTransacao ();
        throw e;
      }
    return movimento_ConhecimentoED;
  }

}