package com.master.bd;

/**
 * Título: Requisicao_CompraBD
 * Descrição: Requisicao de Materiais - BD
 * Data da criação: 11/2004
 * Atualizado em: 11/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Teofilo Poletto Baiocco
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.rl.Requisicao_CompraRL;
import com.master.bd.EstoqueBD;
import com.master.rn.Item_Requisicao_CompraRN;
import com.master.rn.Requisicao_CompraRN;
import com.master.ed.EstoqueED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.ed.Requisicao_CompraED;
import com.master.ed.Solicitacao_CompraED;

import java.util.*;
import java.sql.*;

import javax.servlet.http.HttpServletResponse;

import com.master.root.FormataDataBean;
import com.master.root.Movimento_EstoqueBean;

public class Requisicao_CompraBD {

  private ExecutaSQL executasql;

  public Requisicao_CompraBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Requisicao_CompraED inclui (Requisicao_CompraED ed) throws Excecoes {

    String sql = null;
    int valOid = 0;
    Requisicao_CompraED Requisicao_CompraED = new Requisicao_CompraED ();

    try {

      ResultSet rs = executasql.executarConsulta ("SELECT MAX(OID_requisicao_compra) as result FROM requisicoes_compras");
      while (rs.next ()) valOid = rs.getInt ("result");

      sql = "INSERT INTO Requisicoes_Compras (" +
          " oid_requisicao_compra," +
          " oid_unidade," +
          " oid_usuario," +
          " oid_centro_custo," +
          " oid_modelo_nota_fiscal," +
          " dt_stamp," +
          " usuario_stamp," +
          " dm_stamp," +
          " oid_autorizador," +
          " nm_entrega," +
          " dt_requisicao," +
          " dm_status" +
          ")";
      sql += " values ( ";
      sql += ++valOid + "," +
          ed.getOid_unidade () + "," +
          ed.getOid_usuario () + "," +
          ed.getOid_centro_custo () + "," +
          ed.getOid_modelo_nota_fiscal () + ",'" +
          ed.getDt_stamp () + "','','S'," +
          ed.getOid_autorizador () + ",'" +
          ed.getNm_entrega () + "','" +
          ed.getDt_requisicao () + "','" +
          ed.getDm_status_requisicao () + "')";

      int i = executasql.executarUpdate (sql);
      Requisicao_CompraED.setOid_requisicao_compra (valOid);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir!");
      excecoes.setMetodo ("inclui()");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return Requisicao_CompraED;

  }

  public void altera (Requisicao_CompraED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "UPDATE Requisicoes_Compras set " +
          "  oid_unidade=" + ed.getOid_unidade () +
          ", oid_usuario=" + ed.getOid_usuario () +
          ", oid_centro_custo=" + ed.getOid_centro_custo () +
          ", oid_modelo_nota_fiscal=" + ed.getOid_modelo_nota_fiscal () +
          ", dt_stamp='" + Data.getDataDMY () +
          "', oid_autorizador=" + ed.getOid_autorizador () +
          ", nm_entrega='" + ed.getNm_entrega () + "'";
      sql += " WHERE oid_requisicao_compra = " + ed.getOid_requisicao_compra ();

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar dados da Requisição");
      excecoes.setMetodo ("altera()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (Requisicao_CompraED ed) throws Excecoes {

    String sql = null;
    boolean TEM_REGISTRO = false;
    try {

      ResultSet rs = null;
      rs = this.executasql.executarConsulta ("select * from itens_requisicoes_compras " +
                                             "where oid_requisicao_compra=" + ed.getOid_requisicao_compra ());
      if (rs.next ()) {
        TEM_REGISTRO = true;
        Excecoes ex = new Excecoes ();
        throw ex;
      }

      sql = "delete from requisicoes_compras WHERE Oid_Requisicao_Compra = ";
      sql += "" + ed.getOid_requisicao_compra () + "";

      int i = executasql.executarUpdate (sql);
    }

    catch (Excecoes e) {
      e.setMensagem ("Esta Requisição possui Ítens vinculados, exclua-os primeiro!");
      throw e;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao deletar Requisição");
      excecoes.setMetodo ("deleta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void autoriza (Requisicao_CompraED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    Item_Requisicao_CompraBD itemBD = new Item_Requisicao_CompraBD (this.executasql);
    try {

      sql = "update requisicoes_compras set dm_status='E' WHERE Oid_Requisicao_Compra = ";
      sql += "" + ed.getOid_requisicao_compra () + "";

      int i = executasql.executarUpdate (sql);

      //inclui autorização
//        ResultSet rs = executasql.executarConsulta("SELECT MAX(OID_autorizacao_compra) as result FROM autorizacoes_compras");
//        while (rs.next()) valOid = rs.getLong("result");
//        sql = "insert into autorizacoes_compras (" +
//        	  "oid_autorizacao_compra, " +
//        	  "oid_requisicao_compra, " +
//        	  "oid_autorizador, " +
//        	  "dm_tipo_autorizacao, " +
//        	  "dt_stamp, " +
//        	  "dm_stamp, " +
//        	  "usuario_stamp ) values (" +
//        	  ++valOid + ", " +
//        	  ed.getOid_requisicao_compra() + ", " +
//        	  ed.getOid_autorizador() + ", " +
//        	  "'R', '" + Data.getDataDMY() + "', " +
//        	  "'S' , '')";
//
//        i = executasql.executarUpdate(sql);


      sql = "select oid_item_requisicao_compra from itens_requisicoes_compras WHERE Oid_Requisicao_Compra = ";
      sql += "" + ed.getOid_requisicao_compra () + "";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        ed.setOid_item_requisicao_compra (res.getLong (1));
        itemBD.finaliza (ed);
      }

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao autorizar Requisição");
      excecoes.setMetodo ("deleta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void cancela (Requisicao_CompraED ed) throws Excecoes {

    String sql = null;
    try {

      sql = " UPDATE requisicoes_compras SET dm_status='C' WHERE Oid_Requisicao_Compra = " + ed.getOid_requisicao_compra() + "";
      	executasql.executarUpdate (sql);

      sql = "UPDATE itens_requisicoes_compras SET dm_status='C' WHERE Oid_Requisicao_Compra = "  + ed.getOid_requisicao_compra() + "";
      	executasql.executarUpdate(sql);

      sql = "UPDATE Movimentos_Estoques SET dm_movimento='C' WHERE Oid_Requisicao_Compra = "  + ed.getOid_requisicao_compra() + "";
      	executasql.executarUpdate(sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao cancelar Requisição");
      excecoes.setMetodo ("deleta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public Requisicao_CompraED getByRecord (Requisicao_CompraED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();
    Requisicao_CompraED edVolta = new Requisicao_CompraED ();

    try {

      sql = " SELECT * FROM requisicoes_compras";
      sql += " WHERE 1 = 1 ";
      if (String.valueOf (ed.getOid_requisicao_compra ()) != null &&
          !String.valueOf (ed.getOid_requisicao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_requisicao_compra ()).equals ("0"))
        sql += " AND oid_requisicao_compra = " + ed.getOid_requisicao_compra ();

      ResultSet res = null;
      ResultSet resItem = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Requisicao_CompraED ();
        edVolta.setOid_requisicao_compra (res.getLong ("Oid_requisicao_compra"));
        edVolta.setOid_unidade (res.getLong ("Oid_unidade"));
        edVolta.setOid_usuario (res.getLong ("Oid_usuario"));
        edVolta.setOid_centro_custo (res.getLong ("Oid_centro_custo"));
        edVolta.setOid_modelo_nota_fiscal (res.getLong ("Oid_modelo_nota_fiscal"));
        edVolta.setOid_autorizador (res.getLong ("Oid_autorizador"));
        edVolta.setDt_stamp (res.getString ("Dt_stamp"));
        edVolta.setNm_entrega (res.getString ("Nm_entrega"));
        edVolta.setDt_requisicao (res.getString ("Dt_requisicao"));
        dataFormatada.setDT_FormataData (edVolta.getDt_requisicao ());
        edVolta.setDt_requisicao (dataFormatada.getDT_FormataData ());
        edVolta.setDm_status_requisicao (res.getString ("Dm_status"));
        if (edVolta.getDm_status_requisicao () != null &&
            edVolta.getDm_status_requisicao ().equals ("P")) edVolta.setDm_status_requisicao ("Pendente");
        if (edVolta.getDm_status_requisicao () != null &&
            edVolta.getDm_status_requisicao ().equals ("C")) edVolta.setDm_status_requisicao ("Cancelada");
        if (edVolta.getDm_status_requisicao () != null &&
            edVolta.getDm_status_requisicao ().equals ("F")) edVolta.setDm_status_requisicao ("Finalizada");

    	edVolta.setDm_item_requisicao("N");
        sql ="SELECT oid_Requisicao_Compra FROM Itens_Requisicoes_Compras WHERE oid_Requisicao_Compra=" + res.getLong ("Oid_requisicao_compra");
        resItem = this.executasql.executarConsulta (sql);
        if (resItem.next ()) {
        	edVolta.setDm_item_requisicao("S");
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

  public ArrayList Lista (Requisicao_CompraED ed) throws Excecoes {

    String sql = null;
    String busca = "";
    FormataDataBean dataFormatada = new FormataDataBean ();
    ArrayList list = new ArrayList ();
    Requisicao_CompraED edVolta = new Requisicao_CompraED ();

    try {

      sql = " select * from requisicoes_compras";
      sql += " where 1 = 1 ";
      if (String.valueOf (ed.getOid_requisicao_compra ()) != null &&
          !String.valueOf (ed.getOid_requisicao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_requisicao_compra ()).equals ("0"))
        sql += " and oid_requisicao_compra = " + ed.getOid_requisicao_compra ();
      if (String.valueOf (ed.getOid_unidade ()) != null &&
          !String.valueOf (ed.getOid_unidade ()).equals ("null") &&
          !String.valueOf (ed.getOid_unidade ()).equals ("0"))
        sql += " and oid_unidade = " + ed.getOid_unidade ();
      if (String.valueOf (ed.getOid_usuario ()) != null &&
          !String.valueOf (ed.getOid_usuario ()).equals ("null") &&
          !String.valueOf (ed.getOid_usuario ()).equals ("0"))
        sql += " and oid_usuario = " + ed.getOid_usuario ();
      if (String.valueOf (ed.getOid_centro_custo ()) != null &&
          !String.valueOf (ed.getOid_centro_custo ()).equals ("null") &&
          !String.valueOf (ed.getOid_centro_custo ()).equals ("0"))
        sql += " and oid_centro_custo = " + ed.getOid_centro_custo ();
      if (String.valueOf (ed.getOid_autorizador ()) != null &&
          !String.valueOf (ed.getOid_autorizador ()).equals ("null") &&
          !String.valueOf (ed.getOid_autorizador ()).equals ("0"))
        sql += " and oid_autorizador = " + ed.getOid_autorizador ();
      if (String.valueOf (ed.getDt_requisicao ()) != null &&
          !String.valueOf (ed.getDt_requisicao ()).equals ("null") &&
          !String.valueOf (ed.getDt_requisicao ()).equals (""))
        sql += " and Dt_requisicao = '" + ed.getDt_requisicao () + "'";
      if (String.valueOf (ed.getDm_status_requisicao ()) != null &&
          !String.valueOf (ed.getDm_status_requisicao ()).equals ("null") &&
          !String.valueOf (ed.getDm_status_requisicao ()).equals ("") &&
          !String.valueOf (ed.getDm_status_requisicao ()).equals ("T"))
        sql += " and Dm_status = '" + ed.getDm_status_requisicao () + "'";
      sql += " order by oid_requisicao_compra ";
      ResultSet res = null;
      ResultSet rs = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Requisicao_CompraED ();
        edVolta.setOid_requisicao_compra (res.getLong ("Oid_requisicao_compra"));
        edVolta.setOid_unidade (res.getLong ("Oid_unidade"));
        busca = "select cd_unidade from unidades where oid_unidade = " + res.getString ("Oid_unidade");
        rs = this.executasql.executarConsulta (busca);
        while (rs.next ()) edVolta.setCd_unidade (rs.getString ("cd_unidade"));
        edVolta.setOid_usuario (res.getLong ("Oid_usuario"));
        busca = "select nm_usuario from usuarios where oid_usuario = " + res.getString ("Oid_usuario");
        rs = this.executasql.executarConsulta (busca);
        while (rs.next ()) edVolta.setNm_usuario (rs.getString ("nm_usuario"));
        edVolta.setOid_centro_custo (res.getLong ("Oid_centro_custo"));
        edVolta.setOid_modelo_nota_fiscal (res.getLong ("Oid_modelo_nota_fiscal"));
        edVolta.setOid_autorizador (res.getLong ("Oid_autorizador"));
        edVolta.setDt_stamp (res.getString ("Dt_stamp"));
        edVolta.setNm_entrega (res.getString ("Nm_entrega"));
        edVolta.setDt_requisicao (res.getString ("Dt_requisicao"));
        dataFormatada.setDT_FormataData (edVolta.getDt_requisicao ());
        edVolta.setDt_requisicao (dataFormatada.getDT_FormataData ());
        edVolta.setDm_status_requisicao (res.getString ("Dm_status"));
        if (edVolta.getDm_status_requisicao () != null &&
            edVolta.getDm_status_requisicao ().equals ("P")) edVolta.setDm_status_requisicao ("Pendente");
        if (edVolta.getDm_status_requisicao () != null &&
            edVolta.getDm_status_requisicao ().equals ("E")) edVolta.setDm_status_requisicao ("Enviada");
        if (edVolta.getDm_status_requisicao () != null &&
            edVolta.getDm_status_requisicao ().equals ("C")) edVolta.setDm_status_requisicao ("Cancelada");
        if (edVolta.getDm_status_requisicao () != null &&
            edVolta.getDm_status_requisicao ().equals ("F")) edVolta.setDm_status_requisicao ("Finalizada");
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

  public ArrayList Lista_Itens (Requisicao_CompraED ed) throws Excecoes {

    String sql = null;
    String busca = "";
    ArrayList list = new ArrayList ();
    Requisicao_CompraED edVolta = new Requisicao_CompraED ();

    try {

      sql = " select * from itens_requisicoes_compras";
      sql += " where 1 = 1 ";
      if (String.valueOf (ed.getOid_requisicao_compra ()) != null &&
          !String.valueOf (ed.getOid_requisicao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_requisicao_compra ()).equals ("0"))
        sql += " and oid_requisicao_compra = " + ed.getOid_requisicao_compra ();
      sql += " order by oid_item_requisicao_compra";
      ResultSet res = null;
      ResultSet rs = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Requisicao_CompraED ();
        edVolta.setOid_item_requisicao_compra (res.getLong ("Oid_item_requisicao_compra"));
        edVolta.setOid_estoque (res.getLong ("Oid_estoque"));
        busca = "select cd_estoque, nm_estoque from estoques where oid_estoque = " + res.getString ("Oid_estoque");
        rs = this.executasql.executarConsulta (busca);
        while (rs.next ()) {
          edVolta.setCd_estoque (rs.getString (1));
          edVolta.setNm_estoque (rs.getString (2));
        }
        edVolta.setVl_quantidade (res.getDouble ("Vl_quantidade"));
        edVolta.setVl_quantidade_entrega (res.getDouble ("Vl_quantidade_Entrega"));
        edVolta.setDm_status_item (res.getString ("Dm_status"));
        if (edVolta.getDm_status_item () != null &&
            edVolta.getDm_status_item ().equals ("P")) edVolta.setDm_status_item ("Pendente");
        if (edVolta.getDm_status_item () != null &&
            edVolta.getDm_status_item ().equals ("C")) edVolta.setDm_status_item ("Cancelado");
        if (edVolta.getDm_status_item () != null &&
            edVolta.getDm_status_item ().equals ("F")) edVolta.setDm_status_item ("Finalizado");
        if (edVolta.getDm_status_item () != null &&
            edVolta.getDm_status_item ().equals ("E")) edVolta.setDm_status_item ("Entregue");
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar registros");
      excecoes.setMetodo ("getByRecord()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Requisicao_CompraED entregar (Requisicao_CompraED ed) throws Excecoes {

    String sql = null;
    int i = 0;
    Requisicao_CompraED edVolta = new Requisicao_CompraED ();
    EstoqueBD estoqueBD = new EstoqueBD (this.executasql);

    try {

      sql = "SELECT * FROM itens_requisicoes_compras, estoques " +
      		" WHERE itens_requisicoes_compras.oid_Estoque = estoques.oid_Estoque " +
            " AND   Oid_Requisicao_Compra = " + ed.getOid_requisicao_compra () ;

      ResultSet  rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        edVolta = new Requisicao_CompraED ();
        edVolta.setOid_estoque (rs.getLong ("oid_Estoque"));
        edVolta.setVl_quantidade (rs.getDouble ("vl_quantidade"));

        EstoqueED estoqueVolta = new EstoqueED ();
        estoqueVolta.setOid_estoque(rs.getLong ("oid_Estoque"));
        estoqueVolta = estoqueBD.getByRecord(estoqueVolta);

        if (estoqueVolta.getVl_Quantidade()>= rs.getDouble ("vl_quantidade") && rs.getDouble ("vl_quantidade")>0) {
          //baixa_estoque

    		String chave = String.valueOf (System.currentTimeMillis ()).toString ().substring(7);
    		double vl_movimento= rs.getDouble ("vl_quantidade") * rs.getDouble ("vl_custo");


        	sql = " INSERT INTO Movimentos_Estoques (OID_Movimento_Estoque, OID_Estoque, DT_Movimento, NR_Quantidade, VL_Unitario, Dt_Stamp, DM_Movimento, DM_Destino, NM_Destino, OID_Veiculo, NM_Fornecedor, oid_Nota_Fiscal, NR_Nota_Fiscal, Oid_requisicao_compra, DT_Ordem) " +
        	      " VALUES (" + chave + ", " + rs.getLong ("oid_Estoque") + ", '" + Data.getDataDMY() + "', " +  rs.getDouble ("vl_quantidade") + ", " +  vl_movimento + ", '" + Data.getDataDMY() + "', 'S', 'R' " + ", 'REQ.ESTOQUE NR: " + ed.getOid_requisicao_compra () + "', '', '', '', '', " + ed.getOid_requisicao_compra () + ",'" + Data.getDataYMD()+Data.getHoraHM()+Data.getMileSegundo() +  "')";
        	// System.out.println(sql);

            executasql.executarUpdate (sql);

            sql = " UPDATE itens_requisicoes_compras set dm_status='E', dt_entrega='" + Data.getDataDMY () + "' " +
                     " WHERE  Oid_item_Requisicao_Compra = "  + rs.getString ("oid_item_requisicao_compra") ;
            executasql.executarUpdate (sql);

        }
        else {
          Excecoes excecao = new Excecoes ();
          excecao.setMensagem ("Não há estoque suficiente para o ítem cód.: " + estoqueVolta.getCd_estoque());
          throw excecao;
        }
      }
      edVolta.setOid_requisicao_compra (ed.getOid_requisicao_compra ());
      //seta status da requisicao para finalizado

      sql = "UPDATE requisicoes_compras set dm_status='F' WHERE Oid_Requisicao_Compra = "  + edVolta.getOid_requisicao_compra ();
      executasql.executarUpdate (sql);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao entregar Requisição");
      excecoes.setMetodo ("entregar()");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public void imprime_Protocolo (Requisicao_CompraED ed , HttpServletResponse response) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean DataFormatada = new FormataDataBean ();
    double quantidade = 0;

    try {
      sql = "SELECT * FROM itens_requisicoes_compras " +
          " WHERE oid_requisicao_compra = '" + ed.getOid_requisicao_compra () + "'";
      sql += " order by oid_estoque";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      //popula
      while (res.next ()) {
        quantidade = 0;
        Requisicao_CompraED edVolta = new Requisicao_CompraED ();
        edVolta.setOid_requisicao_compra (ed.getOid_requisicao_compra ());
        ResultSet rs = null;
        edVolta.setOid_estoque (res.getLong ("oid_estoque"));
        sql = "select cd_estoque, nm_estoque, dm_tipo_produto from estoques" +
            " where oid_estoque = " + edVolta.getOid_estoque ();
        rs = null;
        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          edVolta.setCd_estoque (rs.getString (1));
          edVolta.setNm_estoque (rs.getString (2));
          if (rs.getString (3) != null && rs.getString (3).equals ("E")) edVolta.setDm_status_item ("Estoque");
          else edVolta.setDm_status_item ("Diverso");
        }
        edVolta.setVl_quantidade_entrega (res.getDouble ("vl_quantidade_entrega"));
        sql = "select nr_quantidade from estoques where oid_estoque = " + edVolta.getOid_estoque ();
        rs = null;
        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) quantidade += rs.getDouble (1);
        edVolta.setVl_estoque (quantidade);
        edVolta.setDt_stamp (res.getString ("dt_stamp"));
        DataFormatada.setDT_FormataData (edVolta.getDt_stamp ());
        edVolta.setDt_stamp (DataFormatada.getDT_FormataData ());
        list.add (edVolta);
      }

      Requisicao_CompraRL rl = new Requisicao_CompraRL ();
      rl.relProtocolo (list , response , ed);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao imprimir Romaneio");
      excecoes.setMetodo ("imprime_Romaneio()");
      excecoes.setExc (exc);
      throw excecoes;
    }

  }

}
