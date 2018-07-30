package com.master.rn;

import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Produto_CustoBD;
import com.master.ed.Produto_CustoED;
import java.util.*;

public class Produto_CustoRN extends Transacao  {
  Produto_CustoBD Produto_CustoBD = null;


  public Produto_CustoRN() {
    //Produto_Custobd = new Produto_CustoBD(this.sql);
  }

  public Produto_CustoED inclui(Produto_CustoED ed)throws Excecoes{

    Produto_CustoED Produto_CustoED = new Produto_CustoED();

    try{

      this.inicioTransacao();
      Produto_CustoBD = new Produto_CustoBD(this.sql);
      Produto_CustoED = Produto_CustoBD.inclui(ed);
      this.fimTransacao(true);
    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}

    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Produto_Custo");
      excecoes.setMetodo("Inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return Produto_CustoED;
  }

  public void altera(Produto_CustoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Produto_CustoBD = new Produto_CustoBD(this.sql);
      Produto_CustoBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Produto_Custo");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

  public void deleta(Produto_CustoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Produto_CustoBD = new Produto_CustoBD(this.sql);
      Produto_CustoBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Produto_Custo");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

  public ArrayList lista(Produto_CustoED ed)throws Excecoes{

      this.inicioTransacao();
      ArrayList lista = new Produto_CustoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Produto_CustoED getByRecord(Produto_CustoED ed)throws Excecoes{
      this.inicioTransacao();
      Produto_CustoED edVolta = new Produto_CustoED();
      edVolta = new Produto_CustoBD(this.sql).getByRecord(ed);
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(Produto_CustoED ed)throws Excecoes{
    this.inicioTransacao();
    Produto_CustoBD = new Produto_CustoBD(this.sql);
    Produto_CustoBD.geraRelatorio(ed);
    this.fimTransacao(false);
  }

}