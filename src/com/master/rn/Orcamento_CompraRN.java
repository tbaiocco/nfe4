package com.master.rn;


import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Orcamento_CompraBD;
import com.master.ed.Orcamento_CompraED;
import java.util.*;

public class Orcamento_CompraRN
    extends Transacao {
  Orcamento_CompraBD Orcamento_Compra = null;

  public Orcamento_CompraRN () {
    //Solicitacao_CompraBD = new Solicitacao_CompraBD(this.sql);
  }

  /********************************************************
   *
   *******************************************************/
  public Orcamento_CompraED inclui (Orcamento_CompraED ed) throws Excecoes {

    Orcamento_CompraED Orcamento_CompraED = new Orcamento_CompraED ();

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Orcamento_Compra = new Orcamento_CompraBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro

      Orcamento_CompraED = Orcamento_Compra.inclui (ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      throw e;
    }

    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir Orcamento_Compra");
      excecoes.setMetodo ("Inclui");
      excecoes.setExc (e);
      throw excecoes;
    }

    return Orcamento_CompraED;
  }


  /********************************************************
   *
   *******************************************************/
  public void deleta (Orcamento_CompraED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Orcamento_Compra = new Orcamento_CompraBD (this.sql);
      Orcamento_Compra.deleta (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir Orcamento_Compra");
      excecoes.setMetodo ("deleta");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  /********************************************************
   *
   *******************************************************/
  public Orcamento_CompraED getByRecord (Orcamento_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Orcamento_CompraED edVolta = new Orcamento_CompraED ();
    //atribui ao ed de retorno
    edVolta = new Orcamento_CompraBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList Lista (Orcamento_CompraED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ArrayList lista = new ArrayList ();
    //atribui ao ed de retorno
    lista = new Orcamento_CompraBD (this.sql).Lista (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return lista;
  }

}