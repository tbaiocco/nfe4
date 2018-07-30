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

import com.master.bd.EDI_ContabilBD;
import com.master.ed.EDI_ContabilED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import com.master.ed.Tipo_EventoED;
import com.master.bd.ConhecimentoBD;

public class EDI_ContabilRN extends Transacao  {
  EDI_ContabilBD EDI_ContabilBD = null;


  public EDI_ContabilRN() {
  }

  public ArrayList gera_EDI_Faturamento(EDI_ContabilED ed)throws Excecoes{

      //retorna um arraylist de ED큦
      this.inicioTransacao();
      ArrayList lista = new EDI_ContabilBD(sql).gera_EDI_Faturamento(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ArrayList gera_EDI_Liquidacoes(EDI_ContabilED ed)throws Excecoes{

      //retorna um arraylist de ED큦
      this.inicioTransacao();
      ArrayList lista = new EDI_ContabilBD(sql).gera_EDI_Liquidacoes(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ArrayList gera_EDI_Compromissos(EDI_ContabilED ed)throws Excecoes{

      //retorna um arraylist de ED큦
      this.inicioTransacao();
      ArrayList lista = new EDI_ContabilBD(sql).gera_EDI_Compromissos(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ArrayList gera_EDI_Clientes(EDI_ContabilED ed)throws Excecoes{

      //retorna um arraylist de ED큦
      this.inicioTransacao();
      ArrayList lista = new EDI_ContabilBD(sql).gera_EDI_Clientes(ed);
      this.fimTransacao(false);
      return lista;
  }


  public ArrayList gera_EDI_Pessoas_Embarques(EDI_ContabilED ed)throws Excecoes{

      //retorna um arraylist de ED큦
      this.inicioTransacao();
      ArrayList lista = new EDI_ContabilBD(sql).gera_EDI_Pessoas_Embarques(ed);
      this.fimTransacao(false);
      return lista;
  }

    public ArrayList gera_EDI_Conhecimento(EDI_ContabilED ed) throws Excecoes {

        //retorna um arraylist de ED큦
        this.inicioTransacao();

        ArrayList lista = new EDI_ContabilBD(sql).gera_EDI_Conhecimento(ed);
        this.fimTransacao(false);
        return lista;
    }

    public ArrayList gera_EDI_Ordem_Frete(EDI_ContabilED ed) throws Excecoes {

        //retorna um arraylist de ED큦
        this.inicioTransacao();

        ArrayList lista = new EDI_ContabilBD(sql).gera_EDI_Ordem_Frete(ed);
        this.fimTransacao(false);
        return lista;
    }

    public ArrayList gera_EDI_Ordem_Frete_Motorista(EDI_ContabilED ed) throws Excecoes {

        //retorna um arraylist de ED큦
        this.inicioTransacao();

        ArrayList lista = new EDI_ContabilBD(sql).gera_EDI_Ordem_Frete_Motorista(ed);
        this.fimTransacao(false);
        return lista;
    }

  public ArrayList gera_EDI_Pagamentos_Compromissos(EDI_ContabilED ed)throws Excecoes{

      //retorna um arraylist de ED큦
      this.inicioTransacao();
      ArrayList lista = new EDI_ContabilBD(sql).gera_EDI_Pagamentos_Compromissos(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ArrayList gera_EDI_Movimentos_Contas_Correntes(EDI_ContabilED ed)throws Excecoes{

      //retorna um arraylist de ED큦
      this.inicioTransacao();
      ArrayList lista = new EDI_ContabilBD(sql).gera_EDI_Movimentos_Contas_Correntes(ed);
      this.fimTransacao(false);
      return lista;
  }
  public ArrayList gera_EDI_Lancamentos_Contabeis(EDI_ContabilED ed) throws Excecoes {

      //retorna um arraylist de ED큦
      this.inicioTransacao();

      ArrayList lista = new EDI_ContabilBD(sql).gera_EDI_Lancamentos_Contabeis(ed);
      this.fimTransacao(false);
      return lista;
  }


}
