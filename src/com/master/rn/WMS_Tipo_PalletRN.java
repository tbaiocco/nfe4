package com.master.rn;

/**
 * Título: WMS_Tipo_PalletRN
 * Descrição: Tipos de Pallet - RN
 * Data da criação: 02/2004
 * Atualizado em: 02/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.util.ArrayList;

import com.master.bd.WMS_Tipo_PalletBD;
import com.master.ed.WMS_Tipo_PalletED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class WMS_Tipo_PalletRN extends Transacao  {
  WMS_Tipo_PalletBD WMS_Tipo_PalletBD = null;


  public WMS_Tipo_PalletRN() {
    //WMS_Tipo_Palletbd = new WMS_Tipo_PalletBD(this.sql);
  }

/********************************************************
 *
 *******************************************************/
  public WMS_Tipo_PalletED inclui(WMS_Tipo_PalletED ed)throws Excecoes{

    WMS_Tipo_PalletED WMS_Tipo_PalletED = new WMS_Tipo_PalletED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      WMS_Tipo_PalletBD = new WMS_Tipo_PalletBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      WMS_Tipo_PalletED = WMS_Tipo_PalletBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);
    }
    catch(Excecoes e){throw e;}

    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Tipo de Pallet");
      excecoes.setMetodo("Inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return WMS_Tipo_PalletED;
  }

/********************************************************
 *
 *******************************************************/
  public void altera(WMS_Tipo_PalletED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      WMS_Tipo_PalletBD = new WMS_Tipo_PalletBD(this.sql);
      WMS_Tipo_PalletBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Tipo de Pallet");
      excecoes.setMetodo("altera");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public void deleta(WMS_Tipo_PalletED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      WMS_Tipo_PalletBD = new WMS_Tipo_PalletBD(this.sql);
      WMS_Tipo_PalletBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Tipo de Pallet");
      excecoes.setMetodo("deleta");
      excecoes.setExc(e);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public WMS_Tipo_PalletED getByRecord(WMS_Tipo_PalletED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      WMS_Tipo_PalletED edVolta = new WMS_Tipo_PalletED();
      //atribui ao ed de retorno
      edVolta = new WMS_Tipo_PalletBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

/********************************************************
 *
 *******************************************************/
  public WMS_Tipo_PalletED getByOid(String oid_embalagem)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      WMS_Tipo_PalletED edVolta = new WMS_Tipo_PalletED();
      //atribui ao ed de retorno
      edVolta = new WMS_Tipo_PalletBD(this.sql).getByOid(oid_embalagem);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

/********************************************************
 *
 *******************************************************/
  public ArrayList getAll()throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();

      ArrayList array = new WMS_Tipo_PalletBD(this.sql).getAll();
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return array;
  }

/********************************************************
 *
 *******************************************************/
  public void geraRelatorio(WMS_Tipo_PalletED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    WMS_Tipo_PalletBD = new WMS_Tipo_PalletBD(this.sql);
    WMS_Tipo_PalletBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }
/********************************************************
 *
 *******************************************************/
  public ArrayList lista(WMS_Tipo_PalletED ed, String orderby)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new WMS_Tipo_PalletBD(sql).lista(ed, orderby);
      this.fimTransacao(false);
      return lista;
  }

}