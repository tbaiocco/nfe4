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

import com.master.bd.Ordem_PagamentoBD;
import com.master.ed.Ordem_PagamentoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Ordem_PagamentoRN extends Transacao  {
  Ordem_PagamentoBD Ordem_PagamentoBD = null;


  public Ordem_PagamentoRN() {
    //Ordem_Pagamentobd = new Ordem_PagamentoBD(this.sql);
  }

  public void deleta_Movimento(Ordem_PagamentoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Ordem_PagamentoBD = new Ordem_PagamentoBD(this.sql);
      Ordem_PagamentoBD.deleta_Movimento(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar histórico");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }



  public byte[] Relatorio_Ordem_Pagamento(Ordem_PagamentoED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Ordem_PagamentoBD = new Ordem_PagamentoBD(this.sql);
    byte[] b = Ordem_PagamentoBD.Relatorio_Ordem_Pagamento(ed);
    this.fimTransacao(false);
    return b;
  }

  public byte[] imprime_Requisicao(Ordem_PagamentoED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Ordem_PagamentoBD = new Ordem_PagamentoBD(this.sql);
    byte[] b = Ordem_PagamentoBD.imprime_Requisicao(ed);
    this.fimTransacao(false);
    return b;
  }



}