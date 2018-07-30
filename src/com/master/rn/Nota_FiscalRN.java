package com.master.rn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.master.bd.Movimento_LogisticoBD;
import com.master.bd.Nota_FiscalBD;
import com.master.ed.Movimento_LogisticoED;
import com.master.ed.Nota_FiscalED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Nota_FiscalRN
    extends Transacao {
  Nota_FiscalBD Nota_FiscalBD = null;

  public Nota_FiscalRN () {

  }

  public Nota_FiscalED inclui (Nota_FiscalED ed) throws Excecoes {

    Nota_FiscalED conED = new Nota_FiscalED ();
    try {

      //// System.out.println("nf rn 1");

      if (ed.getOID_Pessoa ().compareTo ("") == 0) {
        Excecoes exc = new Excecoes ();
        exc.setMensagem ("Código do Nota_Fiscal não foi informado !!!");
        throw exc;
      }

//// System.out.println("nf rn 2");


      SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
      Calendar DT_Ocorrencia = Calendar.getInstance ();

      Date DT_Emissao = formatter.parse (ed.getDT_Emissao ());
      Calendar DT_Emissao_Calendario = Calendar.getInstance ();
      DT_Emissao_Calendario.setTime (DT_Emissao);

      Date DT_Hoje = new Date ();
      Calendar DT_Hoje_Calendario = Calendar.getInstance ();
      DT_Hoje_Calendario.setTime (DT_Hoje);

      if (String.valueOf (ed.getDT_Entrada ()) != null &&
          !String.valueOf (ed.getDT_Entrada ()).equals ("") &&
          !String.valueOf (ed.getDT_Entrada ()).equals ("null")) {
        Date Ocorrencia = formatter.parse (ed.getDT_Entrada ());
        DT_Ocorrencia.setTime (Ocorrencia);
        if (DT_Ocorrencia.after (DT_Hoje_Calendario) || DT_Emissao_Calendario.after (DT_Ocorrencia)) {
          Excecoes exc = new Excecoes ();
          exc.setMensagem ("Data da entrada tem de ser menor ou igual a hoje");
          exc.setClasse (this.getClass ().getName ());
          exc.setMetodo ("inclui(Nota_FiscalED ed)");
          throw exc;
        }
      }

//// System.out.println("nf rn 3");

      if (DT_Emissao_Calendario.after (DT_Hoje_Calendario)) {
        Excecoes exc = new Excecoes ();
        exc.setMensagem ("Data emissão tem de ser menor ou igual a data de hoje.");
        exc.setClasse (this.getClass ().getName ());
        exc.setMetodo ("inclui(Nota_FiscalED ed)");
        throw exc;
      }

//// System.out.println("nf rn 4");

      this.inicioTransacao ();

//// System.out.println("nf rn 8");

      Nota_FiscalBD = new Nota_FiscalBD (this.sql);
      conED = Nota_FiscalBD.inclui (ed);

      //Transit-Time
      // System.out.println("T Time");
      // comentado RONALDO
      // new Movimento_LogisticoBD(this.sql).gera_Movimento(new Movimento_LogisticoED(ed.getOID_Nota_Fiscal()));

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      exc.printStackTrace ();
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      e.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return conED;

  }

  public void altera (Nota_FiscalED ed) throws Excecoes {

    if (ed.getOID_Pessoa ().compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Nota_Fiscal não foi informado !!!");
      throw exc;
    }

    try {

      SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
      Calendar DT_Ocorrencia = Calendar.getInstance ();

      Date DT_Emissao = formatter.parse (ed.getDT_Emissao ());
      Calendar DT_Emissao_Calendario = Calendar.getInstance ();
      DT_Emissao_Calendario.setTime (DT_Emissao);

      Date DT_Hoje = new Date ();
      Calendar DT_Hoje_Calendario = Calendar.getInstance ();
      DT_Hoje_Calendario.setTime (DT_Hoje);

      if (String.valueOf (ed.getDT_Entrada ()) != null &&
          !String.valueOf (ed.getDT_Entrada ()).equals ("") &&
          !String.valueOf (ed.getDT_Entrada ()).equals ("null")) {
        Date Ocorrencia = formatter.parse (ed.getDT_Entrada ());
        DT_Ocorrencia.setTime (Ocorrencia);
        if (DT_Ocorrencia.after (DT_Hoje_Calendario) || DT_Emissao_Calendario.after (DT_Ocorrencia)) {
          Excecoes exc = new Excecoes ();
          exc.setMensagem ("Data da entrada tem de ser menor ou igual a hoje");
          exc.setClasse (this.getClass ().getName ());
          exc.setMetodo ("altera(Nota_FiscalED ed)");
          throw exc;
        }
      }

      if (DT_Emissao_Calendario.after (DT_Hoje_Calendario)) {
        Excecoes exc = new Excecoes ();
        exc.setMensagem ("Data emissão tem de ser menor ou igual a data de hoje.");
        exc.setClasse (this.getClass ().getName ());
        exc.setMetodo ("altera(Nota_FiscalED ed)");
        throw exc;
      }

      if (ed.getVL_Nota_Fiscal () <= 0) {
        Excecoes exc = new Excecoes ();
        exc.setMensagem ("Valor da nota fiscal menor ou igual a zero");
        exc.setClasse (this.getClass ().getName ());
        exc.setMetodo ("altera(Nota_FiscalED ed))");
        throw exc;
      }

      // Permitir nota fiscal com zero volumes
      /*if (ed.getNR_Volumes() <= 0) {
        Excecoes exc = new Excecoes();
        exc.setMensagem("Volumes da nota fiscal menor ou igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("altera(Nota_FiscalED ed))");
        throw exc;
             }*/

      if (ed.getNR_Peso () <= 0) {
        Excecoes exc = new Excecoes ();
        exc.setMensagem ("Peso da nota fiscal menor ou igual a zero");
        exc.setClasse (this.getClass ().getName ());
        exc.setMetodo ("altera(Nota_FiscalED ed))");
        throw exc;
      }

      this.inicioTransacao ();

      Nota_FiscalBD = new Nota_FiscalBD (this.sql);
      Nota_FiscalBD.altera (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void confirma_Descarga_Deposito (Nota_FiscalED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Nota_FiscalBD = new Nota_FiscalBD (this.sql);
      Nota_FiscalBD.confirma_Descarga_Deposito (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("confirma_Descarga_Armazem");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void confirma_Carga_Descarga_Deposito (Nota_FiscalED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Nota_FiscalBD = new Nota_FiscalBD (this.sql);
      Nota_FiscalBD.confirma_Carga_Descarga_Deposito (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("confirma_Carga_Descarga_Deposito");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }


  public void erroCalculo (Nota_FiscalED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Nota_FiscalBD = new Nota_FiscalBD (this.sql);
      Nota_FiscalBD.erroCalculo (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void deleta (Nota_FiscalED ed) throws Excecoes {

    if (ed.getOID_Nota_Fiscal ().compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Nota_Fiscal não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      Nota_FiscalBD = new Nota_FiscalBD (this.sql);
      Nota_FiscalBD.deleta (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir");
      excecoes.setMetodo ("excluir");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public ArrayList lista (Nota_FiscalED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new Nota_FiscalBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }


  public ArrayList listaConsultaCliente (Nota_FiscalED ed) throws Excecoes {
    this.inicioTransacao ();
    ArrayList lista = null;
    try {
      if ("COM_ITENS_DA_NOTA".equals (ed.getDM_Tipo_Consulta ())) {
        lista = new Nota_FiscalBD (sql).listaConsultaCliente (ed);
      }
      else {
        lista = new Nota_FiscalBD (sql).listaConsultaClienteSimples (ed);
      }

      return lista;
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public Nota_FiscalED getByRecord (Nota_FiscalED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Nota_FiscalED edVolta = new Nota_FiscalED ();
    //atribui ao ed de retorno
    edVolta = new Nota_FiscalBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public int notasMesmoNumero (Nota_FiscalED ed) throws Excecoes {
    int toReturn;
    this.inicioTransacao ();
    toReturn = new Nota_FiscalBD (this.sql).notasMesmoNumero (ed);
    this.fimTransacao (false);
    return toReturn;
  }

  public Nota_FiscalED getByRecord_Conhecimento (Nota_FiscalED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Nota_FiscalED edVolta = new Nota_FiscalED ();
    //atribui ao ed de retorno
    edVolta = new Nota_FiscalBD (this.sql).getByRecord_Conhecimento (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public byte[] geraRelNota_Fiscal (Nota_FiscalED ed) throws Excecoes {

    this.inicioTransacao ();
    Nota_FiscalBD = new Nota_FiscalBD (this.sql);
    byte[] b = Nota_FiscalBD.geraRelNota_Fiscal (ed);
    this.fimTransacao (false);
    return b;
  }

  public byte[] imprime_Etiqueta (Nota_FiscalED ed) throws Excecoes {

    this.inicioTransacao ();
    Nota_FiscalBD = new Nota_FiscalBD (this.sql);
    byte[] b = Nota_FiscalBD.imprime_Etiqueta (ed);
    this.fimTransacao (false);
    return b;
  }


  public byte[] geraRelRemessas (Nota_FiscalED ed) throws Excecoes {

    this.inicioTransacao ();
    Nota_FiscalBD = new Nota_FiscalBD (this.sql);
    byte[] b = Nota_FiscalBD.geraRelRemessas (ed);
    this.fimTransacao (false);
    return b;
  }



  public ArrayList lista_Traking (Nota_FiscalED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new Nota_FiscalBD (sql).lista_Traking (ed);
    this.fimTransacao (false);
    return lista;
  }

  public ArrayList Nota_Fiscal_Rastreamento_Lista (Nota_FiscalED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new Nota_FiscalBD (sql).Nota_Fiscal_Rastreamento_Lista (ed);
    this.fimTransacao (false);
    return lista;
  }

  public ArrayList gera_Arquivo_Nota_Fiscal_Traking (Nota_FiscalED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new Nota_FiscalBD (sql).gera_Arquivo_Nota_Fiscal_Traking (ed);
    this.fimTransacao (false);
    return lista;
  }

}
