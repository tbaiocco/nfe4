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

import com.master.bd.ViagemBD;
import com.master.ed.ViagemED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class ViagemRN
    extends Transacao {
  ViagemBD ViagemBD = null;

  public ViagemRN () {
    //Viagembd = new ViagemBD(this.sql);
  }

  public void inclui (ViagemED ed) throws Excecoes {

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      ViagemBD = new ViagemBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      ViagemBD.inclui (ed);

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
  }

  public void altera (ViagemED ed) throws Excecoes {

    if (String.valueOf (ed.getOID_Viagem ()).compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Viagem não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      ViagemBD = new ViagemBD (this.sql);
      ViagemBD.altera (ed);

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

  public void deleta (ViagemED ed) throws Excecoes {

    if (String.valueOf (ed.getOID_Viagem ()).compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Viagem não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      ViagemBD = new ViagemBD (this.sql);
      ViagemBD.deleta (ed);

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

  public ArrayList lista (ViagemED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new ViagemBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }

  public ViagemED getByRecord (ViagemED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ViagemED edVolta = new ViagemED ();
    //atribui ao ed de retorno
    edVolta = new ViagemBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public ViagemED consulta_Viagem (ViagemED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ViagemED edVolta = new ViagemED ();
    //atribui ao ed de retorno
    edVolta = new ViagemBD (this.sql).consulta_Viagem (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public void inclui_varios_ctos (ViagemED ed) throws Excecoes {
    //// System.out.println(" r1");

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      ViagemBD = new ViagemBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      ViagemBD.inclui_varios_ctos (ed);

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
  }

  public void inclui_nota_fiscal_filtro (ViagemED ed) throws Excecoes {
	    //// System.out.println(" r1");

	    try {

	      //a chamada a este metodo da superclasse
	      //iniciotransacao faz com que se abra uma conexao
	      //e a deixe no pool
	      this.inicioTransacao ();

	      //instancia objeto sql, que eh
	      //uma referencia ao objeto ExecutaSQL, que por sua
	      //vez possui a referencia a conexao ativa
	      ViagemBD = new ViagemBD (this.sql);

	      //chama o inclui passando a estrutura de dados
	      //como parametro
	      ViagemBD.inclui_nota_fiscal_filtro (ed);

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
	  }

  public void inclui_ctos_Rota (ViagemED ed) throws Excecoes {

    try {
      this.inicioTransacao ();
      ViagemBD = new ViagemBD (this.sql);
      ViagemBD.inclui_ctos_Rota (ed);
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
      this.abortaTransacao ();
      throw excecoes;
    }
  }

  public long viagemExiste (String oid_Manifesto , String oid_Conhecimento , String DM_Tipo_Viagem , String oid_Processo, String oid_Nota_Fiscal) throws Excecoes {
    try {
      inicioTransacao ();
      return new ViagemBD (sql).viagemExiste (oid_Manifesto , oid_Conhecimento , DM_Tipo_Viagem , oid_Processo , oid_Nota_Fiscal);
      
    }
    finally {
      fimTransacao (false);
    }
  }

  public ViagemED geraViagem_CtoAvulso (ViagemED ed) throws Excecoes {

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      ViagemBD = new ViagemBD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      ed = ViagemBD.geraViagem_CtoAvulso (ed);

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
    return ed;
  }

}
