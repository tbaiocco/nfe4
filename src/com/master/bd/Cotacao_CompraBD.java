package com.master.bd;

/**
 * Título: Cotacao_CompraBD
 * Descrição: Cotacao de Compras - BD
 * Data da criação: 11/2004
 * Atualizado em: 11/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Teofilo Poletto Baiocco
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.ed.Cotacao_CompraED;
import java.util.*;
import java.sql.*;

import com.master.root.FornecedorBean;

import com.master.root.FormataDataBean;

public class Cotacao_CompraBD {

  private ExecutaSQL executasql;

  public Cotacao_CompraBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Cotacao_CompraED inclui_cotacao (Cotacao_CompraED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    Cotacao_CompraED edVolta = new Cotacao_CompraED ();

    try {

      ResultSet rs = executasql.executarConsulta ("SELECT MAX(OID_cotacao_compra) as result FROM cotacoes_compras");
      while (rs.next ()) {
        valOid = rs.getInt ("result");
      }

      sql = "INSERT INTO cotacoes_Compras (" +
          " oid_cotacao_compra," +
          " oid_item_Solicitacao_compra," +
          " oid_Solicitacao_compra," +
          " dt_stamp," +
          " usuario_stamp," +
          " dm_stamp" +
          ")";
      sql += " values ( ";
      sql += ++valOid + "," +
          ed.getOid_item_Solicitacao_compra () + "," +
          ed.getOid_Solicitacao_compra () + ", '" +
          Data.getDataDMY () + "','','S')";

      // System.out.println(sql);

      int i = executasql.executarUpdate (sql);
      edVolta.setOid_cotacao_compra (valOid);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao Criar Cotação");
      excecoes.setMetodo ("inclui_cotacao()");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public Cotacao_CompraED getByRecord_cotacao (Cotacao_CompraED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();
    Cotacao_CompraED edVolta = new Cotacao_CompraED ();
    String Busca = null;

    try {

      sql = " select * from cotacoes_compras";
      sql += " where 1 = 1 ";
      if (String.valueOf (ed.getOid_cotacao_compra ()) != null &&
          !String.valueOf (ed.getOid_cotacao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_cotacao_compra ()).equals ("0")) {
        sql += " and oid_cotacao_compra = " + ed.getOid_cotacao_compra ();
      }
      if (String.valueOf (ed.getOid_Solicitacao_compra ()) != null &&
          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("0")) {
        sql += " and oid_Solicitacao_compra = " + ed.getOid_Solicitacao_compra ();
      }
      if (String.valueOf (ed.getOid_item_Solicitacao_compra ()) != null &&
          !String.valueOf (ed.getOid_item_Solicitacao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_item_Solicitacao_compra ()).equals ("0")) {
        sql += " and oid_item_Solicitacao_compra = " + ed.getOid_item_Solicitacao_compra ();
      }

// System.out.print(sql);

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Cotacao_CompraED ();
        edVolta.setOid_item_Solicitacao_compra (res.getLong ("Oid_item_solicitacao_compra"));
        edVolta.setOid_Solicitacao_compra (res.getLong ("Oid_solicitacao_compra"));
        edVolta.setOid_cotacao_compra (res.getLong ("Oid_cotacao_compra"));
        edVolta.setNm_observacoes (res.getString ("nm_observacoes"));
        edVolta.setDt_stamp (res.getString ("Dt_stamp"));

        ResultSet rs = null;
        Busca = " SELECT itens_solicitacoes_compras.oid_estoque, " +
                "        itens_solicitacoes_compras.nm_produto, " +
                "        estoques.cd_estoque " +
                " FROM   itens_solicitacoes_compras, estoques   " +
                " WHERE  itens_solicitacoes_compras.oid_estoque = estoques.oid_estoque  " +
                " AND    oid_item_solicitacao_compra =" + edVolta.getOid_item_Solicitacao_compra ();
            // System.out.println(Busca);

        rs = this.executasql.executarConsulta (Busca);
        while (rs.next ()) {
          edVolta.setOid_estoque (rs.getLong ("oid_estoque"));
          edVolta.setCd_estoque(rs.getString ("cd_estoque"));
          edVolta.setNm_produto (rs.getString ("nm_produto"));
        }

      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getByRecord()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public ArrayList Lista_cotacoes (Cotacao_CompraED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();
    ArrayList list = new ArrayList ();
    String produto = null;
    String busca = "";
    Cotacao_CompraED edVolta = new Cotacao_CompraED ();

    try {

      sql = " SELECT cotacoes_compras.*, itens_solicitacoes_compras.oid_estoque, " +
            "        itens_solicitacoes_compras.vl_quantidade, itens_solicitacoes_compras.nm_produto, itens_solicitacoes_compras.dm_status " +
            " FROM   cotacoes_compras, itens_solicitacoes_compras" +
      		" WHERE itens_solicitacoes_compras.Oid_item_solicitacao_compra = cotacoes_compras.Oid_item_solicitacao_compra ";
      if (String.valueOf (ed.getOid_cotacao_compra ()) != null &&
          !String.valueOf (ed.getOid_cotacao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_cotacao_compra ()).equals ("0")) {
        sql += " and cotacoes_Compras.oid_cotacao_compra = " + ed.getOid_cotacao_compra ();
      }
      if (String.valueOf (ed.getOid_Solicitacao_compra ()) != null &&
          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("0")) {
        sql += " and cotacoes_Compras.oid_solicitacao_compra = " + ed.getOid_Solicitacao_compra ();
      }
      if (String.valueOf (ed.getOid_estoque ()) != null &&
          !String.valueOf (ed.getOid_estoque ()).equals ("null") &&
          !String.valueOf (ed.getOid_estoque ()).equals ("0")) {
        sql += " and itens_solicitacoes_compras.oid_estoque = " + ed.getOid_estoque ();
      }
      if (String.valueOf (ed.getNm_produto ()) != null &&
          !String.valueOf (ed.getNm_produto ()).equals ("null") &&
          !String.valueOf (ed.getNm_produto ()).equals ("0")) {
        sql += " and itens_solicitacoes_compras.nm_produto like '" + ed.getNm_produto () + "%'";
      }

      ResultSet res = null;
      ResultSet rs = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Cotacao_CompraED ();
        edVolta.setOid_item_Solicitacao_compra (res.getLong ("Oid_item_solicitacao_compra"));
        edVolta.setOid_Solicitacao_compra (res.getLong ("Oid_solicitacao_compra"));
        edVolta.setOid_cotacao_compra (res.getLong ("Oid_cotacao_compra"));
        edVolta.setNm_observacoes (res.getString ("nm_observacoes"));
        edVolta.setDt_stamp (res.getString ("Dt_stamp"));
        edVolta.setOid_estoque (res.getLong ("Oid_estoque"));
        edVolta.setDt_cotacao (res.getString ("Dt_Stamp"));
        dataFormatada.setDT_FormataData (edVolta.getDt_cotacao ());
        edVolta.setDt_cotacao (dataFormatada.getDT_FormataData ());
        if (res.getString ("Oid_estoque") != null && !res.getString ("Oid_estoque").equals ("0") && res.getString ("Oid_estoque").length () > 0) {
          busca = "select cd_estoque, nm_estoque from estoques where oid_estoque = " + res.getString ("Oid_estoque");
          rs = this.executasql.executarConsulta (busca);
          while (rs.next ()) {
            edVolta.setCd_estoque (rs.getString (1));
            edVolta.setNm_estoque (rs.getString (2));
          }
        }
        else {
          edVolta.setCd_estoque ("");
          edVolta.setNm_estoque ("");
        }
        produto = res.getString ("nm_produto");
        if (produto != null && !produto.equals ("null")) {
          edVolta.setNm_produto (produto);
        }
        else {
          edVolta.setNm_produto ("");
        }
        edVolta.setVl_quantidade (res.getDouble ("Vl_quantidade"));

        edVolta.setDm_status_item (res.getString ("Dm_status"));
        if (edVolta.getDm_status_item () != null &&
            edVolta.getDm_status_item ().equals ("P")) {
          edVolta.setDm_status_item ("Pendente");
        }
        if (edVolta.getDm_status_item () != null &&
            edVolta.getDm_status_item ().equals ("C")) {
          edVolta.setDm_status_item ("Cancelado");
        }
        if (edVolta.getDm_status_item () != null &&
            edVolta.getDm_status_item ().equals ("F")) {
          edVolta.setDm_status_item ("Finalizado");
        }
        if (edVolta.getDm_status_item () != null &&
            edVolta.getDm_status_item ().equals ("D")) {
          edVolta.setDm_status_item ("Cotado");
        }
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getByRecord()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Cotacao_CompraED inclui_item (Cotacao_CompraED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    Cotacao_CompraED edVolta = new Cotacao_CompraED ();

    try {

      String oid = String.valueOf (System.currentTimeMillis ()).toString () + String.valueOf (ed.getOid_cotacao_compra ());

      valOid = new Long (oid.substring (oid.length () - 9 , oid.length ())).longValue ();

      sql = "INSERT INTO itens_cotacoes_Compras (" +
          " oid_item_cotacao_compra," +
          " oid_cotacao_compra," +
          " oid_fornecedor," +
          " dt_stamp," +
          " usuario_stamp," +
          " dm_stamp," +
          " vl_preco," +
          " nm_observacoes," +
          " nm_condicao_pgto," +
          " nr_prazo_entrega," +
          " dm_frete," +
          " dt_cotacao," +
          " dm_status" +
          ")";
      sql += " values ( ";
      sql += valOid + "," +
          ed.getOid_cotacao_compra () + ",'" +
          ed.getOid_fornecedor () + "', '" +
          Data.getDataDMY () + "','','S'," +
          ed.getVl_preco () + ", '" +
          ed.getNm_observacoes () + "', '" +
          ed.getCondicao_pgto () + "', " +
          ed.getNr_prazo_entrega () + ", '" +
          ed.getDm_frete () + "', '" +
          ed.getDt_cotacao () + "', '" +
          ed.getDm_status_item () + "')";

      int i = executasql.executarUpdate (sql);
      edVolta.setOid_item_cotacao_compra (valOid);
    }

    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao inserir Ítem");
      excecoes.setMetodo ("inclui_item()");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public void altera_item (Cotacao_CompraED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "UPDATE itens_cotacoes_Compras set " +
          " oid_fornecedor='" + ed.getOid_fornecedor () +
          "', vl_preco=" + ed.getVl_preco () +
          ", nm_observacoes='" + ed.getNm_observacoes () +
          "', nm_condicao_pgto='" + ed.getCondicao_pgto () +
          "', dt_stamp='" + Data.getDataDMY () +
          "', nr_prazo_entrega=" + ed.getNr_prazo_entrega () +
          ", dm_frete='" + ed.getDm_frete () + "'";
      sql += " WHERE oid_item_cotacao_compra = " + ed.getOid_item_cotacao_compra ();

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar dados do Ítem");
      excecoes.setMetodo ("altera()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (Cotacao_CompraED ed) throws Excecoes {
  }

  public void rejeita_item (Cotacao_CompraED ed) throws Excecoes {

    String sql = null;
    try {

      sql = "update itens_cotacoes_compras set dm_status='R' WHERE Oid_item_cotacao_Compra = ";
      sql += "" + ed.getOid_item_cotacao_compra () + "";

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao cancelar Ítem");
      excecoes.setMetodo ("rejeita_item()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void aceita_item (Cotacao_CompraED ed) throws Excecoes {

    String sql = null;
    try {

      sql = "update itens_cotacoes_compras set dm_status='A' WHERE Oid_item_cotacao_Compra = ";
      sql += "" + ed.getOid_item_cotacao_compra () + "";

      int i = executasql.executarUpdate (sql);

      sql = " select * from itens_cotacoes_compras";
      sql += " where dm_status='P' " +
          "and oid_cotacao_compra = " + ed.getOid_cotacao_compra ();
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        i = executasql.executarUpdate ("update itens_cotacoes_compras set dm_status='R' WHERE Oid_item_cotacao_Compra = " +
                                       res.getString ("oid_item_cotacao_compra"));
      }
      sql = " select oid_item_solicitacao_compra, oid_solicitacao_compra from cotacoes_compras";
      sql += " where oid_cotacao_compra = " + ed.getOid_cotacao_compra ();
      res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        i = executasql.executarUpdate ("update itens_solicitacoes_compras set dm_status='D' WHERE Oid_item_solicitacao_Compra = " +
                                       res.getString ("oid_item_solicitacao_compra"));
        ed.setOid_Solicitacao_compra (res.getLong ("oid_solicitacao_compra"));
      }
      i = executasql.executarUpdate ("update solicitacoes_compras set dm_status='D' WHERE Oid_solicitacao_Compra = " +
                                     ed.getOid_Solicitacao_compra ());
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao efetivar Ítem");
      excecoes.setMetodo ("aceita_item()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public Cotacao_CompraED getByRecord_item (Cotacao_CompraED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();
    Cotacao_CompraED edVolta = new Cotacao_CompraED ();

    try {

      sql = " select * from itens_cotacoes_compras";
      sql += " where 1 = 1 ";
      if (String.valueOf (ed.getOid_item_cotacao_compra ()) != null &&
          !String.valueOf (ed.getOid_item_cotacao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_item_cotacao_compra ()).equals ("0")) {
        sql += " and oid_item_cotacao_compra = " + ed.getOid_item_cotacao_compra ();
      }

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Cotacao_CompraED ();
        edVolta.setOid_item_cotacao_compra (res.getLong ("Oid_item_cotacao_compra"));
        edVolta.setOid_fornecedor (res.getString ("Oid_fornecedor"));
        edVolta.setOid_cotacao_compra (res.getLong ("Oid_cotacao_compra"));
        edVolta.setVl_preco (res.getDouble ("vl_preco"));
        edVolta.setNm_observacoes (res.getString ("nm_observacoes"));
        edVolta.setCondicao_pgto (res.getString ("nm_condicao_pgto"));
        edVolta.setDt_stamp (res.getString ("Dt_stamp"));
        edVolta.setNr_prazo_entrega (res.getInt ("Nr_prazo_entrega"));
        edVolta.setDm_frete (res.getString ("dm_frete"));

        edVolta.setDm_status_item (res.getString ("Dm_status"));
        if (edVolta.getDm_status_item () != null &&
            edVolta.getDm_status_item ().equals ("R")) {
          edVolta.setDm_status_item ("Rejeitado");
        }
        if (edVolta.getDm_status_item () != null &&
            edVolta.getDm_status_item ().equals ("A")) {
          edVolta.setDm_status_item ("Aceito");
        }
        if (edVolta.getDm_status_item () != null &&
            edVolta.getDm_status_item ().equals ("P")) {
          edVolta.setDm_status_item ("Pendente");
        }
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getByRecord()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public ArrayList Lista_itens (Cotacao_CompraED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();
    ArrayList list = new ArrayList ();
    Cotacao_CompraED edVolta = new Cotacao_CompraED ();
    String Busca = null;
    ResultSet rs = null;
    ResultSet rsE = null;

    try {

      sql = " select * from itens_cotacoes_compras";
      sql += " where 1 = 1 ";
      if (String.valueOf (ed.getOid_item_cotacao_compra ()) != null &&
          !String.valueOf (ed.getOid_item_cotacao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_item_cotacao_compra ()).equals ("0")) {
        sql += " and oid_item_cotacao_compra = " + ed.getOid_item_cotacao_compra ();
      }
      if (String.valueOf (ed.getOid_cotacao_compra ()) != null &&
          !String.valueOf (ed.getOid_cotacao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_cotacao_compra ()).equals ("0")) {
        sql += " and oid_cotacao_compra = " + ed.getOid_cotacao_compra ();
      }
      if (String.valueOf (ed.getOid_fornecedor ()) != null &&
          !String.valueOf (ed.getOid_fornecedor ()).equals ("null") &&
          !String.valueOf (ed.getOid_fornecedor ()).equals ("0")) {
        sql += " and oid_fornecedor = '" + ed.getOid_fornecedor () + "'";
      }

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Cotacao_CompraED ();
        edVolta.setOid_item_cotacao_compra (res.getLong ("Oid_item_cotacao_compra"));
        edVolta.setOid_fornecedor (res.getString ("Oid_fornecedor"));
        FornecedorBean forn = new FornecedorBean ();
        forn = FornecedorBean.getByOID_Fornecedor (edVolta.getOid_fornecedor ());
        edVolta.setNm_fornecedor (forn.getNM_Razao_Social ());
        edVolta.setOid_cotacao_compra (res.getLong ("Oid_cotacao_Compra"));
        edVolta.setVl_preco (res.getDouble ("vl_preco"));
        edVolta.setNm_observacoes (res.getString ("nm_observacoes"));
        edVolta.setCondicao_pgto (res.getString ("nm_condicao_pgto"));
        edVolta.setDt_stamp (res.getString ("Dt_stamp"));
        edVolta.setNr_prazo_entrega (res.getInt ("Nr_prazo_entrega"));
        edVolta.setDm_frete (res.getString ("dm_frete"));

        edVolta.setDm_status_item (res.getString ("Dm_status"));
        if (edVolta.getDm_status_item () != null &&
            edVolta.getDm_status_item ().equals ("R")) {
          edVolta.setDm_status_item ("Rejeitado");
        }
        if (edVolta.getDm_status_item () != null &&
            edVolta.getDm_status_item ().equals ("A")) {
          edVolta.setDm_status_item ("Aceito");
        }
        if (edVolta.getDm_status_item () != null &&
            edVolta.getDm_status_item ().equals ("P")) {
          edVolta.setDm_status_item ("Pendente");
        }

        Busca = "select * " +
            " from itens_solicitacoes_compras, cotacoes_compras";
        Busca += " where itens_solicitacoes_compras.oid_item_solicitacao_compra = cotacoes_compras.oid_item_solicitacao_compra " +
            " and cotacoes_compras.oid_cotacao_compra = " + edVolta.getOid_cotacao_compra ();

        rs = this.executasql.executarConsulta (Busca);
        while (rs.next ()) {
          if (rs.getString ("nm_produto") != null &&
              !rs.getString ("nm_produto").equals ("null")) {
            edVolta.setNm_produto (rs.getString ("nm_produto"));
          }
          else {
            edVolta.setNm_produto ("");
          }
          edVolta.setVl_quantidade (rs.getDouble ("vl_quantidade"));

          if (rs.getString ("oid_estoque") != null && !rs.getString ("oid_estoque").equals ("null") && !rs.getString ("oid_estoque").equals ("0")) {
            Busca = "select * " +
                " from estoques";
            Busca += " where oid_estoque = " + rs.getString ("oid_estoque");

            rsE = this.executasql.executarConsulta (Busca);
            while (rsE.next ()) {
              if (rsE.getString ("nm_estoque") != null &&
                  !rsE.getString ("nm_estoque").equals ("null")) {
                edVolta.setNm_estoque (rsE.getString ("nm_estoque"));
              }
              else {
                edVolta.setNm_estoque ("");
              }
              if (rsE.getString ("cd_estoque") != null &&
                  !rsE.getString ("cd_estoque").equals ("null")) {
                edVolta.setCd_estoque (rsE.getString ("cd_estoque"));
              }
              else {
                edVolta.setCd_estoque ("");
              }
            }
          }
        }
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("getByRecord()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public double getValorTotalItens(long oid_Solicitacao, long oid_item_Solicitacao) throws Excecoes {

	    String sql = null;
	    FormataDataBean dataFormatada = new FormataDataBean ();
	    double acumulador = 0;
	    Cotacao_CompraED toReturn = new Cotacao_CompraED();

	    try {

	      sql = "select itens_cotacoes_compras.vl_preco, itens_solicitacoes_compras.vl_quantidade " +
          		" from itens_cotacoes_compras, cotacoes_compras, solicitacoes_compras, itens_solicitacoes_compras " +
          		" where itens_cotacoes_compras.oid_cotacao_compra =  cotacoes_compras.oid_cotacao_compra" +
          		" and cotacoes_compras.oid_solicitacao_compra = solicitacoes_compras.oid_solicitacao_compra" +
          		" AND itens_solicitacoes_compras.oid_item_solicitacao_compra = cotacoes_compras.oid_item_solicitacao_compra " +
          		" and itens_cotacoes_compras.dm_status = 'A' ";

	      if (JavaUtil.doValida(String.valueOf(oid_Solicitacao))) {
	        sql += " and solicitacoes_compras.oid_solicitacao_compra = " + oid_Solicitacao;
	      }
	      if (JavaUtil.doValida(String.valueOf(oid_item_Solicitacao))) {
	        sql += " and itens_solicitacoes_compras.oid_item_solicitacao_compra = " + oid_item_Solicitacao;
	      }
// System.out.println(sql);
	      ResultSet res = null;

	      res = this.executasql.executarConsulta (sql);
	      while (res.next ()) {
	    	  acumulador += res.getDouble(1) * res.getDouble(2);
	      }

	    }
	    catch (Exception exc) {
	      exc.printStackTrace ();
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao recuperar registro");
	      excecoes.setMetodo ("getByRecord()");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }

	    return acumulador;
	  }

}
