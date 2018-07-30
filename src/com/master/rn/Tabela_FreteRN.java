package com.master.rn;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.Conhecimento_InternacionalBD;
import com.master.bd.Tabela_FreteBD;
import com.master.ed.Conhecimento_InternacionalED;
import com.master.ed.Tabela_FreteED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Tabela_FreteRN extends Transacao  {
  Tabela_FreteBD Tabela_FreteBD = null;

  public Tabela_FreteRN() {
  }


  public void reajustaTabelaKieling(Tabela_FreteED ed)throws Excecoes{

    try{
      this.inicioTransacao();

      Tabela_FreteBD = new Tabela_FreteBD(this.sql);

      Tabela_FreteBD.reajustaTabelaKieling(ed);

      this.fimTransacao(true);

    }

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public void relTabela_Frete(Tabela_FreteED ed)
  throws Excecoes {
      inicioTransacao();
      try {
          new Tabela_FreteBD(sql).relTabela_Frete(ed);
      } finally {
          fimTransacao(false);
      }
  }
  
  
  public void reajustaTabela(Tabela_FreteED ed)throws Excecoes{

    try{
      this.inicioTransacao();

      Tabela_FreteBD = new Tabela_FreteBD(this.sql);

      Tabela_FreteBD.reajustaTabela(ed);

      this.fimTransacao(true);

    }

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao reajustaTabela");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      this.abortaTransacao();

      throw excecoes;
    }
  }



  public byte[] imprimeTabela(Tabela_FreteED ed)throws Excecoes{
    this.inicioTransacao();
    try {
	    Tabela_FreteBD = new Tabela_FreteBD(this.sql);
	    byte[] b = Tabela_FreteBD.imprimeTabela(ed);
	    return b;
    } finally {
        this.fimTransacao(false);
    }
  }
  
  public boolean tabelaExiste(Tabela_FreteED tabela)
  throws Excecoes {
      this.inicioTransacao();
      try {
  	    return new Tabela_FreteBD(this.sql).tabelaExiste(tabela);
      } finally {
          this.fimTransacao(false);
      }      
  }
  
  public void geraRelCotacaoEmitida(Tabela_FreteED ed, HttpServletResponse response) throws Excecoes{

  	//antes de invocar chamada ao relatorio deve-se
  	//fazer validacoes de regra de negocio

  	this.inicioTransacao();
  	Tabela_FreteBD tb = new Tabela_FreteBD(this.sql);
  	tb.geraRelCotacaoEmitida(ed, response);
  	
  	this.fimTransacao(false);
}
  
}