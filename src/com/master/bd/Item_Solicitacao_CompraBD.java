package com.master.bd;

/**
 * Título: Item_Solicitacao_CompraBD
 * Descrição: Itens da Solicitacao de Compras - BD
 * Data da criação: 11/2004
 * Atualizado em: 11/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Teofilo Poletto Baiocco
 */

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Solicitacao_CompraED;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class Item_Solicitacao_CompraBD {

  private ExecutaSQL executasql;

  public Item_Solicitacao_CompraBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Solicitacao_CompraED inclui (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    Solicitacao_CompraED Solicitacao_CompraED = new Solicitacao_CompraED ();

    try {

      String oid = String.valueOf (System.currentTimeMillis ()).toString () + String.valueOf (ed.getOid_Solicitacao_compra ());

      valOid = new Long (oid.substring (oid.length () - 11 , oid.length ())).longValue ();

      sql = "INSERT INTO itens_Solicitacoes_Compras (" +
          " oid_item_Solicitacao_compra," +
          " oid_Solicitacao_compra," +
          " oid_estoque," +
          " vl_quantidade," +
          " vl_unitario," +
          " nm_observacoes," +
          " nm_produto," +
          " dt_stamp," +
          " usuario_stamp," +
          " dm_stamp," +
          " nr_prazo_entrega," +
          " nr_contrato," +
          " dm_status" +
          ")";
      sql += " values ( ";
      sql += valOid + "," +
          ed.getOid_Solicitacao_compra () + "," +
          ed.getOid_estoque () + "," +
          ed.getVl_quantidade () + "," +
          ed.getVl_unitario() + ",'" +
          ed.getNm_observacoes () + "','" +
          ed.getNm_produto () + "','" +
          ed.getDt_stamp () + "','','S'," +
          ed.getNr_prazo_entrega () + ",'" +
          ed.getNr_contrato () + "','" +
          ed.getDm_status_item () + "')";

// System.out.println(sql);

      int i = executasql.executarUpdate (sql);


      new Solicitacao_CompraBD (this.executasql).atualiza_Pedido(ed);


      Solicitacao_CompraED.setOid_item_Solicitacao_compra (valOid);
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
    return Solicitacao_CompraED;

  }


  public Solicitacao_CompraED recebe_Itens (Solicitacao_CompraED ed) throws Excecoes {

	    String sql = null;
	    long valOid = 0;
	    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();


	    try {


		   sql =" SELECT oid_movimento_estoque, oid_item_solicitacao_entrega " +
	    		"    	 FROM  itens_Solicitacoes_Entregas " +
	      		"        WHERE oid_estoque= " + ed.getOid_estoque() +
	      		"        AND   oid_solicitacao_compra = " + ed.getOid_Solicitacao_compra() +
	      		"        AND   dt_entrega = '" + ed.getDt_entrega() + "'" +
	      		"        AND   oid_item_solicitacao_compra = " + ed.getOid_item_Solicitacao_compra() +
	      		"        AND   oid_Nota_Fiscal IS NULL ";
		   System.out.println(sql);

		   ResultSet res = this.executasql.executarConsulta (sql);
		   if (!res.next ()) {
			      String oid = String.valueOf (System.currentTimeMillis ()).toString () + String.valueOf (ed.getOid_Solicitacao_compra ());

			      valOid = new Long (oid.substring (oid.length () - 11 , oid.length ())).longValue ();

			      sql = "INSERT INTO itens_Solicitacoes_Entregas (" +
			      	  " oid_item_Solicitacao_Entrega," +
			          " oid_item_Solicitacao_compra," +
			          " oid_Solicitacao_compra," +
			          " oid_estoque," +
			          " oid_Ordem_Servico," +
			          " vl_quantidade_entrega," +
			          " dt_entrega" +
			          ")";
			      sql += " values ( ";
			      sql += valOid + "," +
			      	  ed.getOid_item_Solicitacao_compra() + "," +
			          ed.getOid_Solicitacao_compra () + "," +
			          ed.getOid_estoque () + "," +
			          ed.getOid_Ordem_Servico () + "," +
			          ed.getVl_quantidade_entrega () + ",'" +
			          ed.getDt_entrega() + "')";

			      System.out.println(sql);

			      int i = executasql.executarUpdate (sql);



			      ///SERVICOS - RECEBE NO PEDIDO
			      sql = " SELECT DM_Tipo_Produto " +
			      		" FROM   Estoques " +
					    " WHERE  DM_Tipo_Produto='S' " +
				    	" AND    oid_estoque = " + ed.getOid_estoque ();

			      res = this.executasql.executarConsulta (sql);
			      if (res.next ()) {
						sql =" UPDATE itens_Solicitacoes_Entregas " +
							 " SET oid_movimento_estoque=999999 " +
							 " WHERE oid_item_Solicitacao_Entrega =" + valOid;
					     executasql.executarUpdate (sql);

						new Solicitacao_CompraBD (this.executasql).atualiza_Pedido(ed);
			      }


			      edVolta.setOid_item_Solicitacao_compra (valOid);

		   }


	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      exc.printStackTrace ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao recebe_Itens!");
	      excecoes.setMetodo ("recebe_Itens()");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	    return edVolta;

	  }

  public Solicitacao_CompraED exclui_Recebimento (Solicitacao_CompraED ed) throws Excecoes {

	    String sql = null;
	    try {

		   sql =" SELECT oid_movimento_estoque, oid_item_solicitacao_entrega " +
	    		"        FROM  itens_solicitacoes_entregas " +
	      		"        WHERE oid_estoque= " + ed.getOid_estoque() +
	      		"        AND   oid_solicitacao_compra = " + ed.getOid_Solicitacao_compra() +
	      		"        AND   dt_entrega = '" + ed.getDt_entrega() + "'" +
	      		"        AND   oid_item_solicitacao_compra = " + ed.getOid_item_Solicitacao_compra() +
	      		"        AND   oid_Nota_Fiscal IS NULL ";
		   System.out.println(sql);

		   ResultSet res = this.executasql.executarConsulta (sql);
		   while (res.next ()) {
			   sql =" DELETE FROM movimentos_estoques " +
	    			" WHERE  oid_item_solicitacao_entrega = " + res.getLong("oid_item_solicitacao_entrega");

			   System.out.println(sql);
			   executasql.executarUpdate (sql);

			   sql =" DELETE FROM movimentos_ordens_servicos " +
		    		" WHERE  oid_item_solicitacao_entrega = " + res.getLong("oid_item_solicitacao_entrega");

			   System.out.println(sql);
			   executasql.executarUpdate (sql);

			   sql =" DELETE FROM itens_solicitacoes_entregas " +
	    			" WHERE  oid_item_solicitacao_entrega = " + res.getLong("oid_item_solicitacao_entrega");

			   System.out.println(sql);
			   executasql.executarUpdate (sql);
		   }


	      new Solicitacao_CompraBD (this.executasql).atualiza_Pedido(ed);

	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      exc.printStackTrace ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao recebe_Itens!");
	      excecoes.setMetodo ("recebe_Itens()");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	    return ed;

	  }

  public void altera (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "UPDATE itens_Solicitacoes_Compras set " +
          " oid_estoque=" + ed.getOid_estoque () +
          ", vl_quantidade=" + ed.getVl_quantidade () +
          ", vl_unitario=" + ed.getVl_unitario() +
          ", nm_observacoes='" + ed.getNm_observacoes () +
          "', nm_produto='" + ed.getNm_produto () +
          "', dt_stamp='" + Data.getDataDMY () +
          "', nr_prazo_entrega=" + ed.getNr_prazo_entrega () +
          ", nr_contrato='" + JavaUtil.doValida (ed.getNr_contrato ()) + "'" +
          " WHERE oid_item_Solicitacao_compra = " + ed.getOid_item_Solicitacao_compra ();
      // System.out.println (sql);
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

  public void deleta (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "DELETE FROM  itens_Solicitacoes_compras WHERE Oid_item_Solicitacao_Compra = ";
      sql += "" + ed.getOid_item_Solicitacao_compra () + "";
      executasql.executarUpdate (sql);

      sql = "DELETE FROM  itens_Solicitacoes_Entregas WHERE Oid_item_Solicitacao_Compra = ";
      sql += "" + ed.getOid_item_Solicitacao_compra () + "";

      executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao deletar Ítem");
      excecoes.setMetodo ("deleta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void cancela (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    try {

      sql = "update itens_Solicitacoes_compras set dm_status='C' WHERE Oid_item_Solicitacao_Compra = ";
      sql += "" + ed.getOid_item_Solicitacao_compra () + "";

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao cancelar Solicitação");
      excecoes.setMetodo ("deleta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public Solicitacao_CompraED getByRecord (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();

    try {

      sql = " SELECT * FROM itens_Solicitacoes_compras" +
      		" WHERE  oid_item_Solicitacao_compra = " + ed.getOid_item_Solicitacao_compra ();

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_Solicitacao_compra (res.getLong ("Oid_Solicitacao_compra"));
        edVolta.setOid_item_Solicitacao_compra (res.getLong ("Oid_item_Solicitacao_compra"));
        edVolta.setOid_estoque (res.getLong ("Oid_estoque"));
        edVolta.setVl_quantidade (res.getDouble ("vl_quantidade"));
        edVolta.setVl_unitario (res.getDouble ("vl_Unitario"));
        edVolta.setNm_observacoes (res.getString ("nm_observacoes"));
        edVolta.setNm_produto (res.getString ("nm_produto"));
        edVolta.setDt_stamp (res.getString ("Dt_stamp"));
        edVolta.setNr_prazo_entrega (res.getLong ("Nr_prazo_entrega"));
        edVolta.setNr_contrato (res.getString ("nr_contrato"));
        edVolta.setDt_entrega (res.getString ("Dt_entrega"));
        dataFormatada.setDT_FormataData (edVolta.getDt_entrega ());
        edVolta.setDt_entrega (dataFormatada.getDT_FormataData ());
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

        sql = " SELECT SUM (vl_quantidade_entrega) as vl_quantidade_entrega " +
		  	  " FROM   itens_solicitacoes_entregas " +
		  	  " WHERE  oid_item_solicitacao_compra = " + res.getLong ("oid_item_solicitacao_compra");
		ResultSet rs = this.executasql.executarConsulta (sql);
		if (rs.next ()) {
			  edVolta.setVl_quantidade_entrega(rs.getDouble("vl_quantidade_entrega"));

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

  public ArrayList Lista (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();
    ArrayList list = new ArrayList ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();

    try {

      sql = " SELECT * from itens_Solicitacoes_compras";
      sql += " WHERE 1 = 1 ";
      if (String.valueOf (ed.getOid_item_Solicitacao_compra ()) != null &&
          !String.valueOf (ed.getOid_item_Solicitacao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_item_Solicitacao_compra ()).equals ("0")) {
        sql += " and oid_item_Solicitacao_compra = " + ed.getOid_item_Solicitacao_compra ();
      }

      if (String.valueOf (ed.getOid_unidade ()) != null &&
          !String.valueOf (ed.getOid_unidade ()).equals ("null") &&
          !String.valueOf (ed.getOid_unidade ()).equals ("0")) {
        sql += " and oid_unidade = " + ed.getOid_unidade ();
      }
      if (String.valueOf (ed.getOid_usuario ()) != null &&
          !String.valueOf (ed.getOid_usuario ()).equals ("null") &&
          !String.valueOf (ed.getOid_usuario ()).equals ("0")) {
        sql += " and oid_usuario = " + ed.getOid_usuario ();
      }
      if (String.valueOf (ed.getOid_autorizador ()) != null &&
          !String.valueOf (ed.getOid_autorizador ()).equals ("null") &&
          !String.valueOf (ed.getOid_autorizador ()).equals ("0")) {
        sql += " and oid_autorizador = " + ed.getOid_autorizador ();
      }
      if (String.valueOf (ed.getDt_Solicitacao ()) != null &&
          !String.valueOf (ed.getDt_Solicitacao ()).equals ("null") &&
          !String.valueOf (ed.getDt_Solicitacao ()).equals ("")) {
        sql += " and Dt_Solicitacao = " + ed.getDt_Solicitacao ();
      }
      if (String.valueOf (ed.getDm_status_Solicitacao ()) != null &&
          !String.valueOf (ed.getDm_status_Solicitacao ()).equals ("null") &&
          !String.valueOf (ed.getDm_status_Solicitacao ()).equals ("")) {
        sql += " and Dm_status_Solicitacao = " + ed.getDm_status_Solicitacao ();
      }

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_Solicitacao_compra (res.getLong ("Oid_Solicitacao_compra"));
        edVolta.setOid_item_Solicitacao_compra (res.getLong ("Oid_item_Solicitacao_compra"));
        edVolta.setOid_estoque (res.getLong ("Oid_estoque"));
        edVolta.setVl_quantidade (res.getDouble ("vl_quantidade"));

        edVolta.setVl_unitario (res.getDouble ("vl_Unitario"));

        edVolta.setNm_observacoes (res.getString ("nm_observacoes"));
        edVolta.setDt_stamp (res.getString ("Dt_stamp"));
        edVolta.setNr_prazo_entrega (res.getLong ("Nr_prazo_entrega"));
        edVolta.setNr_contrato (res.getString ("nr_contrato"));
        edVolta.setDt_entrega (res.getString ("Dt_entrega"));
        dataFormatada.setDT_FormataData (edVolta.getDt_entrega ());
        edVolta.setDt_entrega (dataFormatada.getDT_FormataData ());
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

}
