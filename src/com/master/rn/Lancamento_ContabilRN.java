package com.master.rn;

/**
 * <p>Title: Lancamento Contabil </p>
 * <p>Description: Ao entrar com a nota fiscal de compra de produto/serviços,
 * o sistema gerará automaticamente os lancamentos contábeis através do modelo contábil escolhido pelo usuário.</p>
 * <p>Copyright: Delta Guia Copyright (c) 2002</p>
 * <p>Company: Exito/Delta Guia</p>
 * @author Claudia Galmarini Welter
 * @version 1.0
 */


import java.util.ArrayList;

import com.master.bd.Lancamento_ContabilBD;
import com.master.ed.Lancamento_ContabilED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Lancamento_ContabilRN extends Transacao  {
  Lancamento_ContabilBD Lancamento_ContabilBD = null;


  public Lancamento_ContabilRN() {
    //Item_Nota_Fiscal_Transacoesbd = new Item_Nota_Fiscal_TransacoesBD(this.sql);
  } 

  public void inclui_Lancamento_Contabil(Lancamento_ContabilED ed)throws Excecoes{

    try{

// System.out.println("Cheguei no rn");
      if (ed.getVL_Lancamento() <= 0){
// System.out.println("Cheguei no rn 2");
        Excecoes exc = new Excecoes();
        exc.setMensagem("Valor do Lancamento menor ou igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("inclui(Lancamento_ContabilED ed)");
        throw exc;
      }
      this.inicioTransacao();

      Lancamento_ContabilBD = new Lancamento_ContabilBD(this.sql);
      Lancamento_ContabilBD.inclui_Lancamento_Contabil(ed);
// System.out.println("voltei do bd");
      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir um lançamento contábil");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public void inclui_CTB_Acerto(Lancamento_ContabilED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Lancamento_ContabilBD = new Lancamento_ContabilBD(this.sql);
      Lancamento_ContabilBD.inclui_CTB_Acerto(ed);
      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir um lançamento contábil");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public void inclui(Lancamento_ContabilED ed)throws Excecoes{

    try{

// System.out.println("Cheguei no rn");
      if (ed.getVL_Lancamento() <= 0){
// System.out.println("Cheguei no rn 2");
        Excecoes exc = new Excecoes();
        exc.setMensagem("Valor do Lancamento menor ou igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("inclui(Lancamento_ContabilED ed)");
        throw exc;
      }
      this.inicioTransacao();

      Lancamento_ContabilBD = new Lancamento_ContabilBD(this.sql);
      Lancamento_ContabilBD.inclui(ed);
// System.out.println("voltei do bd");
      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir um lançamento contábil");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public void altera(Lancamento_ContabilED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Lancamento()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Parcelamento_Financeiro não foi informado !!!");
      throw exc;
    }

    try{

      if (ed.getVL_Lancamento() <= 0){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Valor do Lancamentomenor ou igual a zero");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("inclui(Lancamento_ContabilED ed)");
        throw exc;
      }

      this.inicioTransacao();

      Lancamento_ContabilBD = new Lancamento_ContabilBD(this.sql);
      Lancamento_ContabilBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public void deleta(Lancamento_ContabilED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Lancamento()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Parcelamento_Financeiro não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Lancamento_ContabilBD = new Lancamento_ContabilBD(this.sql);
      Lancamento_ContabilBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir");
      excecoes.setMetodo("excluir");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }
  }

  public ArrayList lista(Lancamento_ContabilED ed)throws Excecoes{

    //retorna um arraylist de ED´s
    this.inicioTransacao();
    ArrayList lista = new Lancamento_ContabilBD(sql).lista(ed);
    this.fimTransacao(false);
    return lista;
}
  public ArrayList Lista_Contabil_Nota_Fiscal(Lancamento_ContabilED ed)throws Excecoes{

    //retorna um arraylist de ED´s
    this.inicioTransacao();
    ArrayList lista = new Lancamento_ContabilBD(sql).Lista_Contabil_Nota_Fiscal(ed);
    this.fimTransacao(false);
    return lista;
}
  
    public ArrayList GeraEDI_Notas(long oid_unidade, String dt_inicio, String dt_fim)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Lancamento_ContabilBD(sql).GeraEDI_Notas(oid_unidade, dt_inicio, dt_fim);
      this.fimTransacao(true);

      return lista;
  }

    public ArrayList GeraEDI_Acertos(long oid_unidade, String dt_inicio, String dt_fim)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Lancamento_ContabilBD(sql).GeraEDI_Acertos(oid_unidade, dt_inicio, dt_fim);
      this.fimTransacao(true);

      return lista;
  }




    public Lancamento_ContabilED getByRecord(Lancamento_ContabilED ed)throws Excecoes{
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED();
        //atribui ao ed de retorno
        edVolta = new Lancamento_ContabilBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

    public Lancamento_ContabilED getByRecord_Nota_Fiscal(Lancamento_ContabilED ed)throws Excecoes{
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
        Lancamento_ContabilED edVolta = new Lancamento_ContabilED();
        //atribui ao ed de retorno
        edVolta = new Lancamento_ContabilBD(this.sql).getByRecord_Nota_Fiscal(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }
  
  
  public ArrayList lista_Contabil_Compromisso(Lancamento_ContabilED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Lancamento_ContabilBD(sql).lista_Contabil_Compromisso(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ArrayList lista_Contabil_Solicitacao(Lancamento_ContabilED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Lancamento_ContabilBD(sql).lista_Contabil_Solicitacao(ed);
      this.fimTransacao(false);
      return lista;
  }


  public ArrayList lista_Acerto_Completo(Lancamento_ContabilED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Lancamento_ContabilBD(sql).lista_Acerto_Completo(ed);
      this.fimTransacao(false);
      return lista;
  }

   public ArrayList lista_Contabil_Acerto(Lancamento_ContabilED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Lancamento_ContabilBD(sql).lista_Contabil_Acerto(ed);
      this.fimTransacao(false);
      return lista;
  }

     public ArrayList lista_Contabil_Ordem_Frete(Lancamento_ContabilED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Lancamento_ContabilBD(sql).lista_Contabil_Ordem_Frete(ed);      this.fimTransacao(false);
      return lista;
  }

  public Lancamento_ContabilED getByRecord_Solicitacao(Lancamento_ContabilED ed)throws Excecoes{
      this.inicioTransacao();
      Lancamento_ContabilED edVolta = new Lancamento_ContabilED();
      edVolta = new Lancamento_ContabilBD(this.sql).getByRecord_Solicitacao(ed);
      this.fimTransacao(false);
      return edVolta;
  }


    public Lancamento_ContabilED getByRecord_Acerto(Lancamento_ContabilED ed)throws Excecoes{
      this.inicioTransacao();
      Lancamento_ContabilED edVolta = new Lancamento_ContabilED();
      edVolta = new Lancamento_ContabilBD(this.sql).getByRecord_Acerto(ed);
      this.fimTransacao(false);
      return edVolta;
  }

    public Lancamento_ContabilED getByRecord_Movimento_Conta_Corrente(Lancamento_ContabilED ed)throws Excecoes{
      this.inicioTransacao();
      Lancamento_ContabilED edVolta = new Lancamento_ContabilED();
      edVolta = new Lancamento_ContabilBD(this.sql).getByRecord_Movimento_Conta_Corrente(ed);
      this.fimTransacao(false);
      return edVolta;
  }

      public Lancamento_ContabilED getByRecord_Ajuste(Lancamento_ContabilED ed)throws Excecoes{
      this.inicioTransacao();
      Lancamento_ContabilED edVolta = new Lancamento_ContabilED();

      ed.setOID_Movimento_Servico(ed.getOID_Movimento_Servico());

      edVolta = new Lancamento_ContabilBD(this.sql).getByRecord_Ajuste(ed);
      this.fimTransacao(false);
      return edVolta;
  }


     public Lancamento_ContabilED getByRecord_Ordem_Frete(Lancamento_ContabilED ed)throws Excecoes{
      this.inicioTransacao();
      Lancamento_ContabilED edVolta = new Lancamento_ContabilED();
      edVolta = new Lancamento_ContabilBD(this.sql).getByRecord_Ordem_Frete(ed);
      this.fimTransacao(false);
      return edVolta;
  }

  public Lancamento_ContabilED getByRecord_Compromisso(Lancamento_ContabilED ed)throws Excecoes{
      this.inicioTransacao();
      Lancamento_ContabilED edVolta = new Lancamento_ContabilED();
      edVolta = new Lancamento_ContabilBD(this.sql).getByRecord_Compromisso(ed);
      this.fimTransacao(false);
      return edVolta;
  }
  
  public Lancamento_ContabilED getByRecord_Duplicata(Lancamento_ContabilED ed)throws Excecoes{
  	this.inicioTransacao();
  	Lancamento_ContabilED edVolta = new Lancamento_ContabilED();
  	edVolta = new Lancamento_ContabilBD(this.sql).getByRecord_Duplicata(ed);
  	this.fimTransacao(false);
  	return edVolta;
  }

  public Lancamento_ContabilED getByRecord_Conhecimento(Lancamento_ContabilED ed)throws Excecoes{
  	this.inicioTransacao();
  	Lancamento_ContabilED edVolta = new Lancamento_ContabilED();
  	edVolta = new Lancamento_ContabilBD(this.sql).getByRecord_Conhecimento(ed);
  	this.fimTransacao(false);
  	return edVolta;
  }

  public ArrayList lista_Contabil_Caixa(Lancamento_ContabilED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Lancamento_ContabilBD(sql).lista_Contabil_Caixa(ed);

      this.fimTransacao(false);
      return lista;
  }

  public Lancamento_ContabilED getByRecord_Caixa(Lancamento_ContabilED ed)throws Excecoes{
      this.inicioTransacao();
      Lancamento_ContabilED edVolta = new Lancamento_ContabilED();
      edVolta = new Lancamento_ContabilBD(this.sql).getByRecord_Caixa(ed);
      this.fimTransacao(false);
      return edVolta;
  }
      public ArrayList GeraEDI_Solicitacao(long oid_unidade, String dt_inicio, String dt_fim)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Lancamento_ContabilBD(sql).GeraEDI_Solicitacao(oid_unidade, dt_inicio, dt_fim);
      this.fimTransacao(true);

      return lista;
  }

    public ArrayList GeraEDI_Caixinha(long oid_unidade, String dt_inicio, String dt_fim)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Lancamento_ContabilBD(sql).GeraEDI_Caixinha(oid_unidade, dt_inicio, dt_fim);
      this.fimTransacao(true);

      return lista; 
  }

  public ArrayList lista_Contabil_Lote_Pagamento(Lancamento_ContabilED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Lancamento_ContabilBD(sql).lista_Contabil_Lote_Pagamento(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Lancamento_ContabilED getByRecord_Lote_Pagamento(Lancamento_ContabilED ed)throws Excecoes{
      this.inicioTransacao();
      Lancamento_ContabilED edVolta = new Lancamento_ContabilED();
      edVolta = new Lancamento_ContabilBD(this.sql).getByRecord_Lote_Pagamento(ed);
      this.fimTransacao(false);
      return edVolta;
  }
  
  public ArrayList lista_Contabil_Duplicata(Lancamento_ContabilED ed)throws Excecoes{

  	//retorna um arraylist de ED´s
  	this.inicioTransacao();
  	ArrayList lista = new Lancamento_ContabilBD(sql).lista_Contabil_Duplicata(ed);
  	this.fimTransacao(false);
  	return lista;
  }

  public ArrayList Lista_Contabil_Conhecimento(Lancamento_ContabilED ed)throws Excecoes{

  	//retorna um arraylist de ED´s
  	this.inicioTransacao();
  	ArrayList lista = new Lancamento_ContabilBD(sql).Lista_Contabil_Conhecimento(ed);
  	this.fimTransacao(false);
  	return lista;
  } 

  public ArrayList lista_Contabil_Movimento_Conta_Corrente(Lancamento_ContabilED ed)throws Excecoes{

  	//retorna um arraylist de ED´s
  	this.inicioTransacao();
  	ArrayList lista = new Lancamento_ContabilBD(sql).lista_Contabil_Movimento_Conta_Corrente(ed);
  	this.fimTransacao(false);
  	return lista;
  } 

  public ArrayList GeraEDI_Cobranca(long oid_unidade, String dt_inicio, String dt_fim)throws Excecoes{

  	//retorna um arraylist de ED´s
  	this.inicioTransacao();
  	ArrayList lista = new Lancamento_ContabilBD(sql).GeraEDI_Cobranca(oid_unidade, dt_inicio, dt_fim);
  	this.fimTransacao(true);

  	return lista;
  }

  public ArrayList lista_Contabil_Lote_Posto(Lancamento_ContabilED ed)throws Excecoes{

      this.inicioTransacao();
      ArrayList lista = new Lancamento_ContabilBD(sql).lista_Contabil_Lote_Posto(ed);
      this.fimTransacao(false);
      return lista;
  }
  public Lancamento_ContabilED getByRecord_Lote_Posto(Lancamento_ContabilED ed)throws Excecoes{
      this.inicioTransacao();
      Lancamento_ContabilED edVolta = new Lancamento_ContabilED();
      edVolta = new Lancamento_ContabilBD(this.sql).getByRecord_Lote_Posto(ed);
      this.fimTransacao(false);
      return edVolta;
  }

}
