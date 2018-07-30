package com.master.rn;

import com.master.bd.ComissaoBD;
import com.master.ed.ComissaoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class ComissaoRN extends Transacao  {
  ComissaoBD ComissaoBD = null;


  public ComissaoRN() {
  }


  public byte[] geraComissao_Vendas(ComissaoED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    ComissaoBD = new ComissaoBD(this.sql);
    byte[] b = ComissaoBD.geraComissao_Vendas(ed);
    this.fimTransacao(false);
    return b;

  }


  public void inclui(ComissaoED ed)throws Excecoes{

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();
//// System.out.println("RNRNRNRNRNRN");
      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      ComissaoBD = new ComissaoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      ComissaoBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de inclusão");

      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw exc;
    }

  }


}