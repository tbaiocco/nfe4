package com.master.rn;

import com.master.bd.Auxiliar1BD;
import com.master.bd.EDI_ImportacaoBD;
import com.master.ed.Auxiliar1ED;
import com.master.ed.ContaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Auxiliar1RN
    extends Transacao {
  Auxiliar1BD Auxiliar1BD = null;

  public Auxiliar1RN () {
    //Auxiliar1bd = new Auxiliar1BD(this.sql);
  }

  public void inclui (Auxiliar1ED ed) throws Excecoes {

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Auxiliar1BD = new Auxiliar1BD (this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Auxiliar1BD.inclui (ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao (true);

    }
    catch (Exception e) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Erro de inclusão");

      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw exc;
    }

  }

  public void deleta (Auxiliar1ED ed) throws Excecoes {

    if (1 == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Auxiliar1 não foi informado !!!");
      throw exc;
    }

    try {

      this.inicioTransacao ();

      Auxiliar1BD = new Auxiliar1BD (this.sql);
      Auxiliar1BD.deleta (ed);

      this.fimTransacao (true);

    }
    catch (Exception e) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Erro de exclusão");

      this.abortaTransacao ();

      throw exc;
    }

  }

  public byte[] geraRelacaoContas (ContaED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

	  try{
		  this.inicioTransacao ();
//	    ContaBD = new ContaBD(this.sql);
//	    byte[] b = ContaBD.geraRelacaoContas(ed);
	    // System.out.println ("AUXILIAR rn ->>  ");

	    return new Auxiliar1BD(this.sql).geraRelacaoContas (ed);
	  } finally {
		  this.fimTransacao (false);
	  }

  }

  public String leituraNFE (String arquivo, String tipo) throws Excecoes {

	    //tipo:
	    //1 - Compras
	    //2 - Transporte
		  try{
			  this.inicioTransacao ();
		     System.out.println ("AUXILIAR rn ->> TIPO: "+tipo);
//		    return new Auxiliar1BD(this.sql).leituraNFE(arquivo);
			  return new EDI_ImportacaoBD(this.sql).leituraNFE(arquivo);
		  } catch(Excecoes e){
			  throw e;
		  } finally {
			  this.fimTransacao (false);
		  }

	  }

  public void bacas (String executar, String arquivo) throws Excecoes {

    // System.out.println (executar);

    try {

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao ();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Auxiliar1BD = new Auxiliar1BD (this.sql);
      Auxiliar1BD.bacas (executar, arquivo);
      //chama o inclui passando a estrutura de dados
      //como parametro

      //faz o commit em cima do objeto transacao
      this.fimTransacao (true);

    }
    catch (Exception e) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Erro de inclusão");

      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw exc;
    }

  }

}