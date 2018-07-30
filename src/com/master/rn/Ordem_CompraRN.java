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

import com.master.bd.Ordem_CompraBD;
import com.master.ed.Ordem_CompraED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Ordem_CompraRN extends Transacao  {
  Ordem_CompraBD Ordem_CompraBD = null;


  public Ordem_CompraRN() {
    //Ordem_Comprabd = new Ordem_CompraBD(this.sql);
  }

  public void deleta_Movimento(Ordem_CompraED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Ordem_CompraBD = new Ordem_CompraBD(this.sql);
      Ordem_CompraBD.deleta_Movimento(ed);

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



  public ArrayList lista(Ordem_CompraED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Ordem_CompraBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }


  public byte[] Relatorio_Ordem_Compra(Ordem_CompraED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Ordem_CompraBD = new Ordem_CompraBD(this.sql);
    byte[] b = Ordem_CompraBD.Relatorio_Ordem_Compra(ed);
    this.fimTransacao(false);
    return b;
  }

  public byte[] imprime_Requisicao(Ordem_CompraED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Ordem_CompraBD = new Ordem_CompraBD(this.sql);
    byte[] b = Ordem_CompraBD.imprime_Requisicao(ed);
    this.fimTransacao(false);
    return b;
  }



}