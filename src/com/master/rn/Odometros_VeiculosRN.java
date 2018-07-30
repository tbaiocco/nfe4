package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Odometros_VeiculosBD;
import com.master.ed.Odometros_VeiculosED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Odometros_VeiculosRN extends Transacao {
	Odometros_VeiculosBD Odometros_VeiculosBD = null;

  public Odometros_VeiculosRN () {
    Odometros_VeiculosBD = new Odometros_VeiculosBD(this.sql);
  }

  public void inclui (Odometros_VeiculosED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Odometros_VeiculosBD = new Odometros_VeiculosBD (this.sql);
      Odometros_VeiculosBD.inclui(ed);

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
  }

 /* public void altera (Odometros_VeiculosED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Odometros_VeiculosBD = new Odometros_VeiculosBD (this.sql);
      Odometros_VeiculosBD.altera (ed);

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
*/
  public void deleta (Odometros_VeiculosED ed) throws Excecoes {


    try {

      this.inicioTransacao ();

      Odometros_VeiculosBD = new Odometros_VeiculosBD (this.sql);
      Odometros_VeiculosBD.deleta (ed);

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

  public ArrayList lista (Odometros_VeiculosED ed) throws Excecoes {

	  this.inicioTransacao ();
    ArrayList lista = new Odometros_VeiculosBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }

  public Odometros_VeiculosED getByRecord (Odometros_VeiculosED ed) throws Exception {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    Odometros_VeiculosED edVolta = new Odometros_VeiculosED ();
    //atribui ao ed de retorno
    edVolta = new Odometros_VeiculosBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }


}