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

import com.master.bd.FaturamentoBD;
import com.master.bd.Lote_FaturamentoBD;
import com.master.ed.FaturamentoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class FaturamentoRN
    extends Transacao {
  FaturamentoBD FaturamentoBD = null;

  public FaturamentoRN () {
  }


  public ArrayList listaFatura (long NR_Fatura , String DT_Emissao) throws Excecoes {
    ArrayList lista = new ArrayList ();
    try {
      inicioTransacao ();
      lista = new FaturamentoBD (sql).listaFatura (NR_Fatura , DT_Emissao);
      fimTransacao (true);
      return lista;
    }
    catch (Excecoes exc) {
      abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void altera (FaturamentoED ed) throws Excecoes {

    if (ed.getOID_Pessoa ().compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Faturamento não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      FaturamentoBD = new FaturamentoBD (this.sql);
      FaturamentoBD.altera (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
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

  public void deleta (FaturamentoED ed) throws Excecoes {

    if (1 == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Faturamento não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      FaturamentoBD = new FaturamentoBD (this.sql);
      FaturamentoBD.deleta (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
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

  public FaturamentoED getByRecord (FaturamentoED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    FaturamentoED edVolta = new FaturamentoED ();
    //atribui ao ed de retorno
    edVolta = new FaturamentoBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public FaturamentoED geraFatura (FaturamentoED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    FaturamentoED edVolta = new FaturamentoED ();
    //atribui ao ed de retorno
    edVolta = new FaturamentoBD (this.sql).geraFatura (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (true);

    return edVolta;
  }

  public FaturamentoED geraFaturaOrdem_Frete_Terceiro (FaturamentoED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    FaturamentoED edVolta = new FaturamentoED ();
    //atribui ao ed de retorno
    edVolta = new FaturamentoBD (this.sql).geraFaturaOrdem_Frete_Terceiro (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (true);

    return edVolta;
  }

  public byte[] geraPre_Faturamento (FaturamentoED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    FaturamentoBD = new FaturamentoBD (this.sql);
    byte[] b = FaturamentoBD.geraPre_Faturamento (ed);
    this.fimTransacao (false);
    return b;
  }

  public ArrayList listaFaturaPre_Fatura (FaturamentoED ed) throws Excecoes {

	ArrayList lista = new ArrayList ();
    try {
      inicioTransacao ();
      lista = new FaturamentoBD (sql).listaFaturaPre_Fatura (ed);
      fimTransacao (true);
      return lista;
    }
    catch (Excecoes exc) {
      abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

}