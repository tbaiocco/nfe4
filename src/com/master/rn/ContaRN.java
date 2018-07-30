package com.master.rn;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.bd.ContaBD;
import com.master.ed.ContaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class ContaRN
    extends Transacao {
  ContaBD ContaBD = null;

  public ContaRN () {

  }

  /********************************************************
   *
   *******************************************************/
  public ContaED inclui (ContaED ed) throws Excecoes {

    ContaED contaED = new ContaED ();

    this.inicioTransacao ();
    ContaBD = new ContaBD (this.sql);
    contaED = ContaBD.inclui (ed);
    this.fimTransacao (true);

    return contaED;
  }

  public ContaED inclui_Orcamento (ContaED ed) throws Excecoes {

    ContaED contaED = new ContaED ();

    this.inicioTransacao ();
    ContaBD = new ContaBD (this.sql);
    contaED = ContaBD.inclui_Orcamento (ed);
    this.fimTransacao (true);

    return contaED;
  }

  public ContaED copia_Orcamento (ContaED ed) throws Excecoes {

    ContaED contaED = new ContaED ();

    this.inicioTransacao ();
    ContaBD = new ContaBD (this.sql);
    contaED = ContaBD.copia_Orcamento (ed);
    this.fimTransacao (true);

    return contaED;
  }

  /********************************************************
   *
   *******************************************************/
  public void altera (ContaED ed) throws Excecoes {

    this.inicioTransacao ();

    ContaBD = new ContaBD (this.sql);
    ContaBD.altera (ed);

    this.fimTransacao (true);

  }

  public void altera_fluxo (ContaED ed) throws Excecoes {

	    this.inicioTransacao ();

	    ContaBD = new ContaBD (this.sql);
	    ContaBD.altera_fluxo (ed);

	    this.fimTransacao (true);

	  }

  public void altera_Grupo (ContaED ed) throws Excecoes {

	    this.inicioTransacao ();

	    ContaBD = new ContaBD (this.sql);
	    ContaBD.altera_Grupo (ed);

	    this.fimTransacao (true);

	  }

  public void altera_Orcamento (ContaED ed) throws Excecoes {

    this.inicioTransacao ();

    ContaBD = new ContaBD (this.sql);
    ContaBD.altera_Orcamento (ed);

    this.fimTransacao (true);

  }

  /********************************************************
   *
   *******************************************************/
  public void deleta (ContaED ed) throws Excecoes {

    this.inicioTransacao ();

    ContaBD = new ContaBD (this.sql);
    ContaBD.deleta (ed);

    this.fimTransacao (true);

  }

  public void deleta_Orcamento (ContaED ed) throws Excecoes {

    this.inicioTransacao ();

    ContaBD = new ContaBD (this.sql);
    ContaBD.deleta_Orcamento (ed);

    this.fimTransacao (true);

  }

  /********************************************************
   *
   *******************************************************/
  public ArrayList lista (ContaED ed) throws Excecoes {

    this.inicioTransacao ();
    ArrayList lista = new ContaBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }

  public ArrayList listaToSaldo(ContaED ed) throws Excecoes {

	    this.inicioTransacao ();
	    ArrayList lista = new ContaBD (sql).listaToSaldo(ed);
	    this.fimTransacao (false);
	    return lista;
	  }

  public ArrayList lista_Orcamento (ContaED ed) throws Excecoes {

    this.inicioTransacao ();
    ArrayList lista = new ContaBD (sql).lista_Orcamento (ed);
    this.fimTransacao (false);
    return lista;
  }

  /********************************************************
   *
   *******************************************************/
  public ContaED getByRecord (ContaED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ContaED edVolta = new ContaED ();
    //atribui ao ed de retorno
    edVolta = new ContaBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public void getByRecord (ContaED ed , HttpServletRequest request , String nmObj) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ContaED edVolta = new ContaED ();
    //atribui ao ed de retorno
    edVolta = new ContaBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    request.setAttribute (nmObj , edVolta);
  }

  public ContaED getByOrcamento (ContaED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ContaED edVolta = new ContaED ();
    //atribui ao ed de retorno
    edVolta = new ContaBD (this.sql).getByOrcamento (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  /********************************************************
   *
   *******************************************************/
  public void getByEstrutural (ContaED ed , HttpServletRequest request , String nmObj) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ContaED edVolta = new ContaED ();
    //atribui ao ed de retorno
    edVolta = new ContaBD (this.sql).getByEstrutural (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    request.setAttribute (nmObj , edVolta);
  }

  /********************************************************
   *
   *******************************************************/
  public ContaED getByEstrutural (ContaED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ContaED edVolta = new ContaED ();
    //atribui ao ed de retorno
    edVolta = new ContaBD (this.sql).getByEstrutural (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  /********************************************************
   *
   *******************************************************/
  public ContaED getByCD (ContaED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ContaED edVolta = new ContaED ();
    //atribui ao ed de retorno
    edVolta = new ContaBD (this.sql).getByCD (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public byte[] geraRelacaoContas (ContaED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    ContaBD = new ContaBD (this.sql);
    byte[] b = ContaBD.geraRelacaoContas (ed);
    this.fimTransacao (false);
    return b;

  }

  public byte[] geraRelOrcamento (ContaED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    ContaBD = new ContaBD (this.sql);
    byte[] b = ContaBD.geraRelOrcamento (ed);
    this.fimTransacao (false);
    return b;

  }

}
