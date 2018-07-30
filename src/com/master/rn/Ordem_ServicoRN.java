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

import com.master.bd.Ordem_ServicoBD;
import com.master.ed.Ordem_ServicoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Ordem_ServicoRN
    extends Transacao {
  Ordem_ServicoBD Ordem_ServicoBD = null;

  public Ordem_ServicoRN () {
    //Ordem_Servicobd = new Ordem_ServicoBD(this.sql);
  }

  public void deleta_Movimento (Ordem_ServicoED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Ordem_ServicoBD = new Ordem_ServicoBD (this.sql);
      Ordem_ServicoBD.deleta_Movimento (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao deletar histórico");
      excecoes.setMetodo ("deleta");
      excecoes.setExc (e);
      throw excecoes;
    }
  }

  public void inclui_Rateio (Ordem_ServicoED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Ordem_ServicoBD = new Ordem_ServicoBD (this.sql);
      Ordem_ServicoBD.inclui_Rateio (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao deletar histórico");
      excecoes.setMetodo ("deleta");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  public void inclui_Custo_Fixo (Ordem_ServicoED ed) throws Excecoes {

    try {

      this.inicioTransacao ();

      Ordem_ServicoBD = new Ordem_ServicoBD (this.sql);
      Ordem_ServicoBD.inclui_Custo_Fixo (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (Exception e) {
      this.abortaTransacao ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao deletar histórico");
      excecoes.setMetodo ("deleta");
      excecoes.setExc (e);
      throw excecoes;
    }

  }

  public ArrayList lista (Ordem_ServicoED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new Ordem_ServicoBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }

  public byte[] Relatorio_Ordem_Servico (Ordem_ServicoED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    Ordem_ServicoBD = new Ordem_ServicoBD (this.sql);
    byte[] b = Ordem_ServicoBD.Relatorio_Ordem_Servico (ed);
    this.fimTransacao (false);
    return b;
  }

  public byte[] resumo_Custo_Faturamento_Veiculo (Ordem_ServicoED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    Ordem_ServicoBD = new Ordem_ServicoBD (this.sql);
    byte[] b = Ordem_ServicoBD.resumo_Custo_Faturamento_Veiculo (ed);
    this.fimTransacao (false);
    return b;
  }

  public byte[] ficha_Manutencao (Ordem_ServicoED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    Ordem_ServicoBD = new Ordem_ServicoBD (this.sql);
    byte[] b = Ordem_ServicoBD.ficha_Manutencao (ed);
    this.fimTransacao (false);
    return b;
  }

  public byte[] imprime_Requisicao (Ordem_ServicoED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    Ordem_ServicoBD = new Ordem_ServicoBD (this.sql);
    byte[] b = Ordem_ServicoBD.imprime_Requisicao (ed);
    this.fimTransacao (false);
    return b;
  }

}
