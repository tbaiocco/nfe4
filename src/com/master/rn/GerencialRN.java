package com.master.rn;

import com.master.bd.GerencialBD;
import com.master.ed.GerencialED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class GerencialRN extends Transacao  {
  GerencialBD GerencialBD = null;

  public GerencialRN() {
  }

  public GerencialED getByRecord(GerencialED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      GerencialED edVolta = new GerencialED();
      //atribui ao ed de retorno
      edVolta = new GerencialBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public byte[] geraAnalise_Gerencial_Movimento_Conhecimentos(GerencialED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    GerencialBD = new GerencialBD(this.sql);
    byte[] b = GerencialBD.geraAnalise_Gerencial_Movimento_Conhecimentos(ed);
    this.fimTransacao(false);
    return b;

  }

  public byte[] geraAnalise_Gerencial_Conhecimentos(GerencialED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    GerencialBD = new GerencialBD(this.sql);
    byte[] b = GerencialBD.geraAnalise_Gerencial_Conhecimentos(ed);
    this.fimTransacao(false);
    return b;

  }   

  public byte[] geraAnalise_Gerencial_Clientes(GerencialED ed)throws Excecoes{

    this.inicioTransacao();
    GerencialBD = new GerencialBD(this.sql);
    byte[] b = null;

    if ("RELCLI1".equals(ed.getDM_Relatorio())){
      b = GerencialBD.geraAnalise_Gerencial_Clientes (ed);
    }

    if ("CSC".equals(ed.getDM_Relatorio())){
      b = GerencialBD.geraAnalise_Gerencial_Conhecimentos(ed);
    }

    if ("RELCLI3".equals(ed.getDM_Relatorio())){
      b = GerencialBD.geraAnalise_Gerencial_Clientes (ed);
    }

    if ("RELCLI2".equals(ed.getDM_Relatorio())){
      b = GerencialBD.geraAnalise_Gerencial_Clientes_Anual(ed);
    }

    if ("RELCLIMOD".equals(ed.getDM_Relatorio())){
      b = GerencialBD.geraAnalise_Gerencial_Clientes_Modal(ed);
    }

    this.fimTransacao(false);
    return b;

  }

  public byte[] geraAnalise_Gerencial_Fornecedores(GerencialED ed)throws Excecoes{

	    this.inicioTransacao();
	    GerencialBD = new GerencialBD(this.sql);
	    byte[] b = null;

	    if ("RELFOR1".equals(ed.getDM_Relatorio())){
	      b = GerencialBD.geraAnalise_Gerencial_Fornecedores (ed);
	    }

	    this.fimTransacao(false);
	    return b;

  }

  public byte[] geraAnalise_Gerencial_Modal(GerencialED ed)throws Excecoes{

	    this.inicioTransacao();
	    GerencialBD = new GerencialBD(this.sql);
	    byte[] b = null;

	    if ("RELMODAL1".equals(ed.getDM_Relatorio())){
	      b = GerencialBD.geraAnalise_Gerencial_Modal (ed);
	    }

	    this.fimTransacao(false);
	    return b;

  }

  public byte[] geraAnalise_Gerencial_Tabelas_Fretes(GerencialED ed)throws Excecoes{

	    this.inicioTransacao();
	    GerencialBD = new GerencialBD(this.sql);
	    byte[] b = null;

	    if ("RELTAB".equals(ed.getDM_Relatorio().substring(0,6))){
	      b = GerencialBD.geraAnalise_Gerencial_Tabelas_Fretes (ed);
	    }

	    this.fimTransacao(false);
	    return b;

}
  
  public byte[] geraAnalise_Gerencial_Financeira(GerencialED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    GerencialBD = new GerencialBD(this.sql);
    byte[] b = GerencialBD.geraAnalise_Gerencial_Financeira(ed);
    this.fimTransacao(false);
    return b;

  }

  public byte[] geraAnalise_Gerencial_Receita_Despesa(GerencialED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio
    this.inicioTransacao ();
    byte[] b = null;
  
    GerencialBD = new GerencialBD (this.sql);
    b = GerencialBD.geraAnalise_Gerencial_Receita_Despesa (ed);
  
    this.fimTransacao (false);
    return b;

  }

  public byte[] geraAnalise_Gerencial_Demonstrativo_Resultado(GerencialED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    byte[] b = null;
  
    GerencialBD = new GerencialBD (this.sql);
    b = GerencialBD.geraAnalise_Gerencial_Demonstrativo_Resultado (ed);
  
    this.fimTransacao (false);
    return b;

  }

  public byte[] geraAnalise_Gerencial_Veiculos(GerencialED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    GerencialBD = new GerencialBD(this.sql);
    byte[] b = null;
    if ("R_ANUAL".equals((ed.getDM_Relatorio()+"       ").substring(0,7))){
      b = GerencialBD.geraAnalise_Gerencial_Veiculos_Anual(ed);
    }else {
      b = GerencialBD.geraAnalise_Gerencial_Veiculos(ed);
    }


    this.fimTransacao(false);
    return b;

  }

  public byte[] geraAnalise_Gerencial_Prazo_Medio(GerencialED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    GerencialBD = new GerencialBD(this.sql);
    byte[] b = GerencialBD.geraAnalise_Gerencial_Prazo_Medio(ed);
    this.fimTransacao(false);
    return b;

  }


  public void inclui(GerencialED ed)throws Excecoes{

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();
//// System.out.println("RNRNRNRNRNRN");
      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      GerencialBD = new GerencialBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      GerencialBD.inclui(ed);

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



  public void altera(GerencialED ed)throws Excecoes{

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();
//// System.out.println("RNRNRNRNRNRN");
      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      GerencialBD = new GerencialBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      GerencialBD.altera(ed);

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

  public byte[] geraAnalise_Gerencial_Contas_Receber(GerencialED ed)throws Excecoes{

    this.inicioTransacao();
    GerencialBD = new GerencialBD(this.sql);
    byte[] b = GerencialBD.geraAnalise_Gerencial_Contas_Receber(ed);
    this.fimTransacao(false);
    return b;
  }

  public byte[] geraAnalise_Gerencial_CRT(GerencialED ed)throws Excecoes{

      //antes de invocar chamada ao relatorio deve-se
      //fazer validacoes de regra de negocio

      this.inicioTransacao();
      GerencialBD = new GerencialBD(this.sql);
      byte[] b = GerencialBD.geraAnalise_Gerencial_CRT(ed);
      this.fimTransacao(false);
      return b;

    }

  public byte[] geraAnalise_Gerencial_Faturamento(GerencialED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
      byte[] b = null;      

    GerencialBD = new GerencialBD(this.sql);
    b = GerencialBD.geraAnalise_Gerencial_Faturamento (ed);
    
    this.fimTransacao(false);
    return b;

  }

  public byte[] geraAnalise_Gerencial_Faturamento_Diario(GerencialED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
      byte[] b = null;      

    GerencialBD = new GerencialBD(this.sql);
    b = GerencialBD.geraAnalise_Gerencial_Faturamento_Diario (ed);
    
    this.fimTransacao(false);
    return b;

  }


}
