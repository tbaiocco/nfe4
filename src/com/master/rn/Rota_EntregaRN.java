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

import com.master.bd.Rota_EntregaBD;
import com.master.ed.Rota_EntregaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Rota_EntregaRN
    extends Transacao {
  Rota_EntregaBD Rota_EntregaBD = null;

  public Rota_EntregaRN () {
  }

  public Rota_EntregaED inclui (Rota_EntregaED ed) throws Excecoes {

    Rota_EntregaED conED = new Rota_EntregaED ();

    try {

      this.inicioTransacao ();
      Rota_EntregaBD = new Rota_EntregaBD (this.sql);
      conED = Rota_EntregaBD.inclui (ed);
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

    return conED;

  }

  public void altera (Rota_EntregaED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Rota_EntregaBD = new Rota_EntregaBD (this.sql);
      Rota_EntregaBD.altera (ed);

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

  public void deleta (Rota_EntregaED ed) throws Excecoes {

    if (1 == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Rota_Entrega não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      Rota_EntregaBD = new Rota_EntregaBD (this.sql);
      Rota_EntregaBD.deleta (ed);

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

  public ArrayList lista (Rota_EntregaED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new Rota_EntregaBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }

  public Rota_EntregaED getByRecord (Rota_EntregaED ed) throws Excecoes {

    Rota_EntregaED edVolta = new Rota_EntregaED ();

    try {

      //inicia conexao com bd
      this.inicioTransacao ();
      //instancia ed de retorno
      //atribui ao ed de retorno
      edVolta = new Rota_EntregaBD (this.sql).getByRecord (ed);
      //libera conexao nao "commitando"
      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar Rota_Entrega");
      excecoes.setMetodo ("getByRecord(Rota_EntregaED ed)");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return edVolta;
  }

  public ArrayList getRotas () throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new Rota_EntregaBD (sql).getRotas ();
    this.fimTransacao (false);
    return lista;
  }

  public ArrayList listaCDByPessoa (Rota_EntregaED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new Rota_EntregaBD (sql).listaCDByPessoa (ed);
    this.fimTransacao (false);
    return lista;
  }

  public Rota_EntregaED getCDByPessoa (Rota_EntregaED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    Rota_EntregaED rota = new Rota_EntregaBD (sql).getCDByPessoa (ed);
    this.fimTransacao (false);
    return rota;
  }

//  public byte[] geraRelatorioConheEmbarque(Rota_EntregaED ed)throws Excecoes{
//
//    this.inicioTransacao();
//    Rota_EntregaBD = new Rota_EntregaBD(this.sql);
//    byte[] b = Rota_EntregaBD.geraRelRota_EntregaEmbarque(ed);
//    this.fimTransacao(false);
//    return b;
//  }

}