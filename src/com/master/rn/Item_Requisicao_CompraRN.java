package com.master.rn;

/**
 * Título: Item_Requisicao_CompraRN
 * Descrição: Itens da Requisicao de Materiais - RN
 * Data da criação: 11/2004
 * Atualizado em: 11/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Teofilo Poletto Baiocco
*/

import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Item_Requisicao_CompraBD;
import com.master.ed.Requisicao_CompraED;
import java.util.*;

public class Item_Requisicao_CompraRN extends Transacao  {
    Item_Requisicao_CompraBD Item_Requisicao_CompraBD = null;

  public Item_Requisicao_CompraRN() {
    //Requisicao_CompraBD = new Requisicao_CompraBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public Requisicao_CompraED inclui(Requisicao_CompraED ed)throws Excecoes{

      Requisicao_CompraED Requisicao_CompraED = new Requisicao_CompraED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Item_Requisicao_CompraBD = new Item_Requisicao_CompraBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro

      Requisicao_CompraED = Item_Requisicao_CompraBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);
    }
    catch(Excecoes e){throw e;}

    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Requisição");
      excecoes.setMetodo("Inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return Requisicao_CompraED;
  }

/********************************************************
 *
 *******************************************************/
  public void altera(Requisicao_CompraED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Item_Requisicao_CompraBD = new Item_Requisicao_CompraBD(this.sql);
      Item_Requisicao_CompraBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Requisição");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public void deleta(Requisicao_CompraED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Item_Requisicao_CompraBD = new Item_Requisicao_CompraBD(this.sql);
      Item_Requisicao_CompraBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Requisição");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

  /********************************************************
  *
  *******************************************************/
   public void cancela(Requisicao_CompraED ed)throws Excecoes{

     try{

       this.inicioTransacao();

       Item_Requisicao_CompraBD = new Item_Requisicao_CompraBD(this.sql);
       Item_Requisicao_CompraBD.deleta(ed);

       this.fimTransacao(true);

     }
     catch(Excecoes exc){
       throw exc;
     }
     catch(Exception e){
       this.abortaTransacao();
       Excecoes excecoes = new Excecoes();
       excecoes.setClasse(this.getClass().getName());
       excecoes.setMensagem("Erro ao excluir Requisição");
       excecoes.setMetodo("deleta");
       excecoes.setExc(e);
       throw excecoes;
     }

   }

   /********************************************************
   *
   *******************************************************/
    public void finaliza(Requisicao_CompraED ed)throws Excecoes{

      try{

        this.inicioTransacao();

        Item_Requisicao_CompraBD = new Item_Requisicao_CompraBD(this.sql);
        Item_Requisicao_CompraBD.finaliza(ed);

        this.fimTransacao(true);

      }
      catch(Excecoes exc){
        throw exc;
      }
      catch(Exception e){
        this.abortaTransacao();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao Finalizar Ítem");
        excecoes.setMetodo("finaliza");
        excecoes.setExc(e);
        throw excecoes;
      }

    }

    /********************************************************
    *
    *******************************************************/
     public void entrega(Requisicao_CompraED ed)throws Excecoes{

       try{

         this.inicioTransacao();

         Item_Requisicao_CompraBD = new Item_Requisicao_CompraBD(this.sql);
         Item_Requisicao_CompraBD.entrega(ed);

         this.fimTransacao(true);

       }
       catch(Excecoes exc){
         throw exc;
       }
       catch(Exception e){
         this.abortaTransacao();
         Excecoes excecoes = new Excecoes();
         excecoes.setClasse(this.getClass().getName());
         excecoes.setMensagem("Erro ao Entregar Ítem");
         excecoes.setMetodo("entrega");
         excecoes.setExc(e);
         throw excecoes;
       }

     }

/********************************************************
 *
 *******************************************************/
  public Requisicao_CompraED getByRecord(Requisicao_CompraED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Requisicao_CompraED edVolta = new Requisicao_CompraED();
      //atribui ao ed de retorno
      edVolta = new Item_Requisicao_CompraBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList Lista(Requisicao_CompraED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      ArrayList lista = new ArrayList();
      //atribui ao ed de retorno
      lista = new Item_Requisicao_CompraBD(this.sql).Lista(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return lista;
  }

  /********************************************************
  *
  *******************************************************/
   public void altera_Qtde_Entrega(Requisicao_CompraED ed)throws Excecoes{

     try{

       this.inicioTransacao();
       Item_Requisicao_CompraBD = new Item_Requisicao_CompraBD(this.sql);
       Item_Requisicao_CompraBD.altera_Qtde_Entrega(ed);
       this.fimTransacao(true);

     }
     catch(Excecoes exc){
       throw exc;
     }
     catch(Exception e){
       this.abortaTransacao();
       Excecoes excecoes = new Excecoes();
       excecoes.setClasse(this.getClass().getName());
       excecoes.setMensagem("Erro ao alterar Quantidade de Entrega");
       excecoes.setMetodo("altera_Qtde_Entrega()");
       excecoes.setExc(e);
       throw excecoes;
     }

   }

}