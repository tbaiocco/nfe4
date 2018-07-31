package com.master.bd;

/**
 * T�tulo: Solicitacao_CompraBD
 * Descri��o: Solicitacao de Compras - BD
 * Data da cria��o: 11/2004
 * Atualizado em: 11/2004
 * Empresa: �xitoLog�stica Mastercom
 * Autor: Teofilo Poletto Baiocco
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.Cotacao_CompraED;
import com.master.ed.Item_Nota_Fiscal_CompraED;
import com.master.ed.Modelo_Nota_FiscalED;
import com.master.ed.Nota_Fiscal_CompraED;
import com.master.ed.Solicitacao_CompraED;
import com.master.rl.Solicitacao_CompraRL;
import com.master.root.FormataDataBean;
import com.master.root.Movimento_EstoqueBean;
import com.master.root.PessoaBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Solicitacao_CompraBD {

  private ExecutaSQL executasql;

  public Solicitacao_CompraBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Solicitacao_CompraED inclui (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    int valOid = 0;
    Solicitacao_CompraED Solicitacao_CompraED = new Solicitacao_CompraED ();

    try {

      ResultSet rs = executasql.executarConsulta ("SELECT MAX(OID_Solicitacao_compra) as result FROM Solicitacoes_compras");
      while (rs.next ()) {
        valOid = rs.getInt ("result");
      }

      sql = "INSERT INTO Solicitacoes_Compras (" +
          " oid_Solicitacao_compra," +
          " oid_unidade," +
          " oid_usuario," +
          " oid_centro_custo," +
          " oid_conta," +
          " oid_conta_servico," +
          " oid_modelo_nota_fiscal," +
          " dt_stamp," +
          " usuario_stamp," +
          " dm_stamp," +
          " dm_tipo_compra," +
          " oid_autorizador," +
          " nm_entrega," +
          " dt_Solicitacao," +
          " tx_Observacao," +
          " oid_Veiculo," +
          " dm_status," +
          " dt_entrega" +
          ")";
      sql += " values ( ";
      sql += ++valOid + "," +
          ed.getOid_unidade () + "," +
          ed.getOid_usuario () + "," +
          ed.getOid_centro_custo () + "," +
          ed.getOid_Conta () + "," +
          ed.getOid_Conta_Servico () + "," +
          ed.getOid_modelo_nota_fiscal () + ",'" +
          ed.getDt_stamp () + "','' ,'S','S'," +
          ed.getOid_autorizador () + ",'" +
          ed.getNm_entrega () + "','" +
          ed.getDt_Solicitacao () + "','" +
          ed.getTX_Observacao () + "','" +
          ed.getOid_Veiculo () + "','" +
          ed.getDm_status_Solicitacao () + "'," +
          new Utilitaria().getSQLDate(ed.getDt_entrega()) + ")";

      int i = executasql.executarUpdate (sql);
      Solicitacao_CompraED.setOid_Solicitacao_compra (valOid);
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


  public void altera (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "UPDATE Solicitacoes_Compras set " +
          "  oid_unidade=" + ed.getOid_unidade () +
          ", oid_usuario=" + ed.getOid_usuario () +
          ", oid_centro_custo=" + ed.getOid_centro_custo () +
          ", oid_conta=" + ed.getOid_Conta () +
          ", oid_conta_Servico=" + ed.getOid_Conta_Servico () +
          ", oid_modelo_nota_fiscal=" + ed.getOid_modelo_nota_fiscal () +
          ", dt_stamp='" + Data.getDataDMY () + "'"+
          ", oid_autorizador=" + ed.getOid_autorizador () +
          ", oid_Veiculo='" + ed.getOid_Veiculo () + "'"+
          ", oid_Fornecedor='" + ed.getOid_fornecedor () + "'"+
          ", TX_Observacao='" + ed.getTX_Observacao () + "'"+
          ", dm_tipo_Compra='" + ed.getDm_tipo_compra () + "'"+
          ", nm_entrega='" + ed.getNm_entrega () + "'" +
          ", dt_entrega=" + new Utilitaria().getSQLDate(ed.getDt_entrega());

      sql += " WHERE oid_Solicitacao_compra = " + ed.getOid_Solicitacao_compra ();
      // System.out.println(sql);
      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar dados da Solicita��o");
      excecoes.setMetodo ("altera()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    boolean TEM_REGISTRO = false;
    try {

      ResultSet rs = null;
      rs = this.executasql.executarConsulta ("select * from itens_Solicitacoes_compras " +
                                             "where oid_Solicitacao_compra=" + ed.getOid_Solicitacao_compra ());
      if (rs.next ()) {
        TEM_REGISTRO = true;
        Excecoes ex = new Excecoes ();
        throw ex;
      }

      sql = "delete from Solicitacoes_compras WHERE Oid_Solicitacao_Compra = ";
      sql += "" + ed.getOid_Solicitacao_compra () + "";

      int i = executasql.executarUpdate (sql);
    }

    catch (Excecoes e) {
      e.setMensagem ("Esta Solicita��o possui �tens vinculados, exclua-os primeiro!");
      throw e;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao deletar Solicita��o");
      excecoes.setMetodo ("deleta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void gera_Pedido (Solicitacao_CompraED ed) throws Excecoes {


      System.out.println("autoriza compra");

    String sql = null;
    long valOid = 0;

    try {

      Solicitacao_CompraED edVolta = this.getByRecord(ed);


      sql = " SELECT autorizadores_compras.oid_autorizador, autorizadores_compras.vl_alcada, autorizadores_compras.dt_vencimento " +
      		" FROM   autorizadores_compras, usuarios  " +
      		" WHERE  usuarios.oid_usuario = autorizadores_compras.oid_usuario " +
      		" AND    usuarios.oid_usuario = '" + ed.getCd_usuario_autorizador () + "'";
      ResultSet res = executasql.executarConsulta (sql);
      while (res.next ()) {
        ed.setOid_autorizador (res.getLong (1));

        FormataDataBean DataFormatadata = new FormataDataBean ();
        DataFormatadata.setDT_FormataData (res.getString (3));
        String data1 = DataFormatadata.getDT_FormataData ();
        String data2 = Data.getDataDMY ();
        Calendar cal1 = Data.stringToCalendar (data1 , "dd/MM/yyyy");
        Calendar cal2 = Data.stringToCalendar (data2 , "dd/MM/yyyy");
        Data data = new Data ();
        if (cal2.after (cal1)) {
          throw new Excecoes ("Autoriza��o vencida!" , getClass ().getName () , "autoriza(Solicitacao_CompraED ed)");
        }
        if (!Libera_Autorizacao (res.getDouble (2) , ed.getOid_autorizador () , ed.getOid_Solicitacao_compra ())) {
          throw new Excecoes ("Valor superior � sua Al�ada!" , getClass ().getName () , "autoriza(Solicitacao_CompraED ed)");
          //throw new Excecoes("Valores j� liberados neste m�s s�o superiores � sua Al�ada!", getClass().getName(), "autoriza(Solicitacao_CompraED ed)");
        }
      }

      sql = " UPDATE Solicitacoes_compras SET dm_status='A' " +
      		" WHERE Oid_Solicitacao_Compra = " + ed.getOid_Solicitacao_compra ();
      int i = executasql.executarUpdate (sql);

      ResultSet rs = executasql.executarConsulta ("SELECT MAX(OID_autorizacao_compra) as result FROM autorizacoes_compras");
      while (rs.next ()) {
        valOid = rs.getLong ("result");
      }

      sql =   "INSERT into autorizacoes_compras (" +
	          "oid_autorizacao_compra, " +
	          "oid_Solicitacao_compra, " +
	          "oid_autorizador, " +
	          "dm_tipo_autorizacao, " +
	          "dt_stamp, " +
	          "dm_stamp, " +
	          "usuario_stamp ) values (" +
	          ++valOid + ", " +
	          ed.getOid_Solicitacao_compra () + ", " +
	          ed.getOid_autorizador () + ", " +
	          "'S', '" + Data.getDataDMY () + "', " +
	          "'S' , '')";

      i = executasql.executarUpdate (sql);

      sql =   "INSERT into orcamentos_compras (" +
		      "oid_orcamento_compra, " +
		      "oid_solicitacao_compra, " +
		      "oid_orcamento_conta, " +
		      "vl_compra  ) " +
		      "values (" +
		      "'" + String.valueOf (System.currentTimeMillis ()).toString () + "', " +
		      ed.getOid_Solicitacao_compra () + ", " +
		      "'" + ed.getOid_Orcamento_Conta() + "'," +
		      edVolta.getVl_Pedido() + ")";
        System.out.println("orcamentos_compras=" + sql);

		i = executasql.executarUpdate (sql);

      sql =" DELETE FROM Pedidos_Compras " +
      	   " WHERE oid_Solicitacao_compra=" + ed.getOid_Solicitacao_compra ();
      executasql.executarUpdate (sql);

      this.inclui_pedido (ed);

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao autorizar Solicita��o");
      excecoes.setMetodo ("gera_Pedido()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void cancela (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    Item_Solicitacao_CompraBD itemBD = new Item_Solicitacao_CompraBD (this.executasql);
    try {

      sql = " UPDATE Solicitacoes_compras set dm_status='C' " +
      		" WHERE Oid_Solicitacao_Compra = " + ed.getOid_Solicitacao_compra () + "";
      int i = executasql.executarUpdate (sql);

      sql = " SELECT oid_item_Solicitacao_compra " +
      		" FROM itens_Solicitacoes_compras " +
      		" WHERE Oid_Solicitacao_Compra = " + ed.getOid_Solicitacao_compra () + "";
      ResultSet rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        ed.setOid_item_Solicitacao_compra (rs.getLong (1));
        itemBD.cancela (ed);
      }

      i = executasql.executarUpdate ("update pedidos_compras set dm_status='C' WHERE Oid_Solicitacao_Compra = " + ed.getOid_Solicitacao_compra ());

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao cancelar Solicita��o");
      excecoes.setMetodo ("deleta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void exclui_Pedido (Solicitacao_CompraED ed) throws Excecoes {

	    String sql = null;
	    Item_Solicitacao_CompraBD itemBD = new Item_Solicitacao_CompraBD (this.executasql);
	    try {

	      sql = "UPDATE Solicitacoes_compras set dm_status='E' WHERE Oid_Solicitacao_Compra = " + ed.getOid_Solicitacao_compra ();
	      executasql.executarUpdate (sql);

	      sql = "UPDATE Itens_Cotacoes_compras set dm_status='P' WHERE oid_Cotacao_Compra in (SELECT oid_Cotacao_Compra FROM Cotacoes_Compras WHERE Oid_Solicitacao_Compra = " + ed.getOid_Solicitacao_compra () +")";
	      executasql.executarUpdate (sql);

	      sql =" DELETE FROM orcamentos_compras " +
		 	   " WHERE oid_Solicitacao_compra=" + ed.getOid_Solicitacao_compra ();
	      executasql.executarUpdate (sql);

	      sql =" DELETE FROM parcelamentos_Pedidos " +
	 	   	   " WHERE oid_pedido_compra=" + ed.getOid_Solicitacao_compra ();
	      executasql.executarUpdate (sql);


	      sql = "DELETE FROM pedidos_compras WHERE Oid_Solicitacao_Compra = " + ed.getOid_Solicitacao_compra ();
	      executasql.executarUpdate (sql);

	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao excluir Pedido");
	      excecoes.setMetodo ("exclui_Pedido()");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void finaliza (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    Cotacao_CompraBD cotacao = new Cotacao_CompraBD (this.executasql);
    Cotacao_CompraED cotED = new Cotacao_CompraED ();
    Cotacao_CompraED edVolta = new Cotacao_CompraED();
    cotED.setOid_Solicitacao_compra (ed.getOid_Solicitacao_compra ());
    try {

      sql = "update Solicitacoes_compras set dm_status='E' WHERE Oid_Solicitacao_Compra = ";
      sql += "" + ed.getOid_Solicitacao_compra () + "";
      int i = executasql.executarUpdate (sql);

      sql = "select oid_item_Solicitacao_compra from itens_Solicitacoes_compras WHERE Oid_Solicitacao_Compra = ";
      sql += "" + ed.getOid_Solicitacao_compra () + "";
      ResultSet rs = null;
      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
      // System.out.println(rs.getLong ("oid_item_Solicitacao_compra"));

        cotED.setOid_item_Solicitacao_compra (rs.getLong ("oid_item_Solicitacao_compra"));
        edVolta = cotacao.inclui_cotacao (cotED);
      }

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao cancelar Solicita��o");
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

      sql = " SELECT * FROM Solicitacoes_compras" +
      		" WHERE oid_Solicitacao_compra = " + ed.getOid_Solicitacao_compra ();

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_fornecedor(res.getString ("Oid_Fornecedor"));
        edVolta.setDm_tipo_compra(res.getString ("Dm_tipo_compra"));

        edVolta.setOid_Solicitacao_compra (res.getLong ("Oid_Solicitacao_compra"));
        edVolta.setOid_unidade (res.getLong ("Oid_unidade"));
        edVolta.setOid_usuario (res.getLong ("Oid_usuario"));
        edVolta.setOid_centro_custo (res.getLong ("Oid_centro_custo"));
        edVolta.setOid_modelo_nota_fiscal (res.getLong ("Oid_modelo_nota_fiscal"));
        edVolta.setOid_autorizador (res.getLong ("Oid_autorizador"));
        edVolta.setDt_stamp (res.getString ("Dt_stamp"));
        edVolta.setNm_entrega (res.getString ("Nm_entrega"));
        edVolta.setDt_Solicitacao (res.getString ("Dt_Solicitacao"));
        dataFormatada.setDT_FormataData (edVolta.getDt_Solicitacao ());
        edVolta.setDt_Solicitacao (dataFormatada.getDT_FormataData ());
        edVolta.setDm_status_Solicitacao (res.getString ("Dm_status"));

        edVolta.setDt_entrega(dataFormatada.getDT_FormataData(res.getString("dt_entrega")));

        edVolta.setOid_Conta (res.getLong ("Oid_Conta"));
        edVolta.setOid_Conta_Servico (res.getLong ("Oid_Conta_Servico"));
        edVolta.setOid_Veiculo (res.getString("Oid_Veiculo"));
        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));

        edVolta.setNm_status_Solicitacao (consulta_Situacao(res.getString ("Dm_status")));

        edVolta = this.total_Solicitacao_Compra(edVolta);

        edVolta.setNr_parcelas(res.getLong ("Nr_parcelas"));
        edVolta.setVl_Parcela_Entrada(res.getDouble("Vl_Parcela_Entrada"));




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
    String busca = "";
    FormataDataBean dataFormatada = new FormataDataBean ();
    ArrayList list = new ArrayList ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();

    try {

      sql = " SELECT * FROM Solicitacoes_compras" +
      	    " WHERE 1 = 1 ";
      if (String.valueOf (ed.getOid_Solicitacao_compra ()) != null &&
          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("0")) {
        sql += " and oid_Solicitacao_compra = " + ed.getOid_Solicitacao_compra ();
      }
      if (String.valueOf (ed.getOid_unidade ()) != null &&
          !String.valueOf (ed.getOid_unidade ()).equals ("null") &&
          !String.valueOf (ed.getOid_unidade ()).equals ("0")) {
        sql += " and oid_unidade = " + ed.getOid_unidade ();
      }

      if (String.valueOf (ed.getOid_Conta ()) != null &&
          !String.valueOf (ed.getOid_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOid_Conta ()).equals ("0")) {
        sql += " and (oid_Conta = " + ed.getOid_Conta () + " OR oid_Conta_servico = " + ed.getOid_Conta () +")" ;
      }
      if (String.valueOf (ed.getOid_Veiculo ()) != null &&
          !String.valueOf (ed.getOid_Veiculo ()).equals ("null") &&
          !String.valueOf (ed.getOid_Veiculo ()).equals ("")) {
        sql += " and oid_Veiculo = '" + ed.getOid_Veiculo () +"'";
      }

      if (String.valueOf (ed.getOid_usuario ()) != null &&
          !String.valueOf (ed.getOid_usuario ()).equals ("null") &&
          !String.valueOf (ed.getOid_usuario ()).equals ("0")) {
        sql += " and oid_usuario = " + ed.getOid_usuario ();
      }
      if (String.valueOf (ed.getOid_centro_custo ()) != null &&
          !String.valueOf (ed.getOid_centro_custo ()).equals ("null") &&
          !String.valueOf (ed.getOid_centro_custo ()).equals ("0")) {
        sql += " and oid_centro_custo = " + ed.getOid_centro_custo ();
      }
      if (String.valueOf (ed.getOid_autorizador ()) != null &&
          !String.valueOf (ed.getOid_autorizador ()).equals ("null") &&
          !String.valueOf (ed.getOid_autorizador ()).equals ("0")) {
        sql += " and oid_autorizador = " + ed.getOid_autorizador ();
      }
      if (String.valueOf (ed.getDt_Solicitacao ()) != null &&
          !String.valueOf (ed.getDt_Solicitacao ()).equals ("null") &&
          !String.valueOf (ed.getDt_Solicitacao ()).equals ("")) {
        sql += " and Dt_Solicitacao >= '" + ed.getDt_Solicitacao () + "'";
      }
      if (String.valueOf (ed.getDm_status_Solicitacao ()) != null &&
          !String.valueOf (ed.getDm_status_Solicitacao ()).equals ("null") &&
          !String.valueOf (ed.getDm_status_Solicitacao ()).equals ("") &&
          !String.valueOf (ed.getDm_status_Solicitacao ()).equals ("T")) {
        sql += " and Dm_status = '" + ed.getDm_status_Solicitacao () + "'";
      }
      sql += " order by oid_Solicitacao_compra ";
      ResultSet res = null;
      ResultSet rs = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_Solicitacao_compra (res.getLong ("Oid_Solicitacao_compra"));
        edVolta.setOid_unidade (res.getLong ("Oid_unidade"));
        busca = "select cd_unidade from unidades where oid_unidade = " + res.getString ("Oid_unidade");
        rs = this.executasql.executarConsulta (busca);
        while (rs.next ()) {
          edVolta.setCd_unidade (rs.getString ("cd_unidade"));
        }
        edVolta.setOid_usuario (res.getLong ("Oid_usuario"));
        busca = "select nm_usuario from usuarios where oid_usuario = " + res.getString ("Oid_usuario");
        rs = this.executasql.executarConsulta (busca);
        while (rs.next ()) {
          edVolta.setNm_usuario (rs.getString ("nm_usuario"));
        }
        edVolta.setOid_centro_custo (res.getLong ("Oid_centro_custo"));
        edVolta.setOid_modelo_nota_fiscal (res.getLong ("Oid_modelo_nota_fiscal"));
        edVolta.setOid_autorizador (res.getLong ("Oid_autorizador"));
        edVolta.setDt_stamp (res.getString ("Dt_stamp"));
        edVolta.setNm_entrega (res.getString ("Nm_entrega"));
        edVolta.setDt_Solicitacao (res.getString ("Dt_Solicitacao"));
        dataFormatada.setDT_FormataData (edVolta.getDt_Solicitacao ());
        edVolta.setDt_Solicitacao (dataFormatada.getDT_FormataData ());
        edVolta.setDm_status_Solicitacao (res.getString ("Dm_status"));

        edVolta.setDt_entrega(dataFormatada.getDT_FormataData(res.getString("dt_entrega")));

        edVolta.setOid_Conta (res.getLong ("Oid_Conta"));
        edVolta.setOid_Veiculo (res.getString("Oid_Veiculo"));
        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));

        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("P")) {
          edVolta.setDm_status_Solicitacao ("Pendente");
        }
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("E")) {
          edVolta.setDm_status_Solicitacao ("Enviada");
        }
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("C")) {
          edVolta.setDm_status_Solicitacao ("Cancelada");
        }
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("F")) {
          edVolta.setDm_status_Solicitacao ("Finalizada");
        }
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("A")) {
          edVolta.setDm_status_Solicitacao ("Autorizada");
        }
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("D")) {
          edVolta.setDm_status_Solicitacao ("Cotada");
        }

        busca = "select dm_status from pedidos_compras where oid_solicitacao_compra = " + edVolta.getOid_Solicitacao_compra ();
        rs = this.executasql.executarConsulta (busca);
        while (rs.next ()) {
          if (rs.getString (1) != null && rs.getString (1).equals ("P")) {
            edVolta.setDm_status_pedido ("Pendente");
          }
          if (rs.getString (1) != null && rs.getString (1).equals ("A")) {
            edVolta.setDm_status_pedido ("Parcial");
          }
          if (rs.getString (1) != null && rs.getString (1).equals ("F")) {
            edVolta.setDm_status_pedido ("Finalizado");
          }
          if (rs.getString (1) != null && rs.getString (1).equals ("C")) {
            edVolta.setDm_status_pedido ("Cancelado");
          }
          if (rs.getString (1) != null && rs.getString (1).equals ("N")) {
            edVolta.setDm_status_pedido ("Nota Fiscal");
          }
        }

        edVolta.setNm_status_Solicitacao (consulta_Situacao(res.getString ("Dm_status")));


        busca = "select itens_cotacoes_compras.dm_status " +
            " from itens_cotacoes_compras, cotacoes_compras, solicitacoes_compras" +
            " where itens_cotacoes_compras.oid_cotacao_compra =  cotacoes_compras.oid_cotacao_compra" +
            " and cotacoes_compras.oid_solicitacao_compra = solicitacoes_compras.oid_solicitacao_compra" +
            " and solicitacoes_compras.oid_solicitacao_compra =" + edVolta.getOid_Solicitacao_compra ();
        rs = this.executasql.executarConsulta (busca);
        int cont = 0;
        while (rs.next ()) {
          if (rs.getString (1) != null && rs.getString (1).equals ("A")) {
            cont++;
          }
        }
        edVolta.setQuantidade_cotacoes_aceitas (new Long (cont).intValue ());

        busca = "select count(oid_item_solicitacao_compra) from itens_solicitacoes_compras" +
            " where dm_status <> 'C'" +
            " and oid_solicitacao_compra = " + edVolta.getOid_Solicitacao_compra ();
        rs = this.executasql.executarConsulta (busca);
        cont = 0;
        while (rs.next ()) {
          cont = rs.getInt (1);
        }
        edVolta.setQuantidade_itens (new Long (cont).intValue ());

        list.add (edVolta);
      }

    }
    catch (SQLException e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "Lista(Solicitacao_CompraED ed)");
    }

    return list;
  }

  public ArrayList Lista_Itens (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    String produto = null;
    String busca = "";
    ArrayList list = new ArrayList ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();

    try {

      sql = " SELECT * FROM itens_Solicitacoes_compras" +
      	    " WHERE  1 = 1 ";
      if (String.valueOf (ed.getOid_Solicitacao_compra ()) != null &&
          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("0")) {
        sql += " AND oid_Solicitacao_compra = " + ed.getOid_Solicitacao_compra ();
      }
      sql += " ORDER BY  oid_item_solicitacao_compra";
      ResultSet res = null;
      ResultSet rs = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_item_Solicitacao_compra (res.getLong ("Oid_item_Solicitacao_compra"));
        edVolta.setOid_Solicitacao_compra (res.getLong ("Oid_Solicitacao_compra"));
        edVolta.setOid_estoque (res.getLong ("Oid_estoque"));
        edVolta.setVl_quantidade (res.getDouble ("Vl_quantidade"));
        edVolta.setVl_unitario (res.getDouble ("vl_unitario"));

	    sql = 	" SELECT itens_cotacoes_compras.vl_preco, " +
	        	"        itens_cotacoes_compras.nr_prazo_entrega, " +
	        	"        itens_cotacoes_compras.nm_condicao_pgto " +
	        	" FROM   itens_cotacoes_compras, " +
	        	"        cotacoes_compras  " +
	        	" WHERE  itens_cotacoes_compras.dm_status = 'A' " +
	        	" AND    itens_cotacoes_compras.oid_cotacao_compra = cotacoes_compras.oid_cotacao_compra " +
	        	" AND    cotacoes_compras.oid_item_solicitacao_compra = " +res.getString ("oid_item_solicitacao_compra");

		    rs = this.executasql.executarConsulta (sql);
		    if (rs.next ()) {
		        edVolta.setVl_unitario (res.getDouble ("vl_preco"));
		    }

	    edVolta.setCd_estoque ("");
	    edVolta.setNm_estoque ("");
        if (res.getString ("Oid_estoque") != null && !res.getString ("Oid_estoque").equals ("0") && res.getString ("Oid_estoque").length () > 0) {
          busca = " SELECT cd_estoque, nm_estoque, vl_custo " +
          		  "  FROM  estoques " +
          		  " WHERE oid_estoque = " + res.getString ("Oid_estoque");
          rs = this.executasql.executarConsulta (busca);
          if (rs.next ()) {
            edVolta.setCd_estoque (rs.getString (1));
            edVolta.setNm_estoque (rs.getString (2));
            if (edVolta.getVl_unitario()==0){
                edVolta.setVl_unitario(rs.getDouble("vl_custo"));
            }
          }
        }

        edVolta.setVl_total_item(edVolta.getVl_quantidade()*edVolta.getVl_unitario());

        produto = res.getString ("nm_produto");
        if (produto != null && !produto.equals ("null")) {
          edVolta.setNm_produto (produto);
        }
        else {
          edVolta.setNm_produto ("");
        }


        sql = " SELECT SUM (vl_quantidade_entrega) as vl_quantidade_entrega " +
		  	  " FROM   itens_solicitacoes_entregas " +
		  	  " WHERE  oid_item_solicitacao_compra = " + res.getLong ("Oid_item_Solicitacao_compra");
		rs = this.executasql.executarConsulta (sql);
		if (rs.next ()) {
			edVolta.setVl_quantidade_entrega(rs.getDouble("vl_quantidade_entrega"));
		}
		edVolta.setDm_status_item ("Pendente");
		if (edVolta.getVl_quantidade()>0 && edVolta.getVl_quantidade()==edVolta.getVl_quantidade_entrega()){
		    edVolta.setDm_status_item ("Recebido");

	        sql = " SELECT SUM (vl_quantidade_entrega) as vl_quantidade_entrega_com_nota " +
			  	  " FROM   itens_solicitacoes_entregas " +
			  	  " WHERE  oid_item_solicitacao_compra = " + res.getLong ("Oid_item_Solicitacao_compra") +
			  	  " AND    oid_nota_fiscal IS NOT NULL";
			rs = this.executasql.executarConsulta (sql);
			if (rs.next ()) {
				if (edVolta.getVl_quantidade_entrega() == rs.getDouble("vl_quantidade_entrega_com_nota")){
				    edVolta.setDm_status_item ("OK");
				}
			}
		}


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


  public ArrayList Lista_Itens_Entrega (Solicitacao_CompraED ed) throws Excecoes {

	    String sql = null;
	    String busca = "";
	    ArrayList list = new ArrayList ();
	    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
	    FormataDataBean dataFormatada = new FormataDataBean ();

	    try {

	      sql = " SELECT * FROM itens_solicitacoes_entregas" +
	      	    " WHERE oid_Solicitacao_compra = " + ed.getOid_Solicitacao_compra () +
	      		" ORDER BY  oid_item_solicitacao_entrega";
	      ResultSet res = null;
	      ResultSet rs = null;
	      res = this.executasql.executarConsulta (sql);
	      while (res.next ()) {
	        edVolta = new Solicitacao_CompraED ();
	        edVolta.setOid_item_Solicitacao_entrega(res.getLong ("oid_item_solicitacao_entrega"));
	        edVolta.setOid_Solicitacao_compra (res.getLong ("Oid_Solicitacao_compra"));
	        edVolta.setOid_movimento_ordem_servico(res.getLong ("Oid_movimento_ordem_servico"));
	        edVolta.setOid_Movimento_Estoque(res.getLong ("Oid_movimento_estoque"));
	        edVolta.setDm_situacao_estoque("Atualizar");
	        if (res.getLong ("Oid_movimento_estoque")>0){
		        edVolta.setDm_situacao_estoque("OK");
	        }

	        edVolta.setOid_Solicitacao_compra (res.getLong ("Oid_Solicitacao_compra"));
	        edVolta.setOid_estoque (res.getLong ("Oid_estoque"));
	        edVolta.setOid_nota_fiscal(res.getString("Oid_nota_fiscal"));
	        edVolta.setVl_quantidade_entrega(res.getDouble ("vl_quantidade_entrega"));
	        edVolta.setDt_entrega(res.getString ("Dt_entrega"));
	        dataFormatada.setDT_FormataData (edVolta.getDt_entrega ());
	        edVolta.setDt_entrega (dataFormatada.getDT_FormataData ());

	        edVolta.setNr_nota_fiscal("");
	        if (res.getString ("Oid_nota_fiscal") != null && !res.getString ("Oid_nota_fiscal").equals ("0") && res.getString ("Oid_nota_fiscal").length () > 0) {
		          busca = " SELECT NR_Nota_Fiscal " +
		          		  "  FROM  notas_fiscais_transacoes " +
		          		  " WHERE Oid_nota_fiscal = '" + res.getString ("Oid_nota_fiscal") + "'";
		          rs = this.executasql.executarConsulta (busca);
		          if (rs.next ()) {
		            edVolta.setNr_nota_fiscal (rs.getString ("NR_Nota_Fiscal"));
		          }
		    }

            edVolta.setOid_Veiculo("");
	        if (res.getLong ("oid_ordem_servico") > 0) {
		          busca = " SELECT NR_Ordem_Servico, oid_Veiculo " +
		          		  "  FROM  Ordens_Servicos " +
		          		  " WHERE oid_ordem_servico = '" + res.getLong ("oid_ordem_servico") + "'";
		          rs = this.executasql.executarConsulta (busca);
		          if (rs.next ()) {
		            edVolta.setOid_Veiculo(rs.getString ("oid_Veiculo"));
		          }
		    }

	        edVolta.setCd_estoque ("");
	        edVolta.setNm_estoque ("");
	        if (res.getString ("Oid_estoque") != null && !res.getString ("Oid_estoque").equals ("0") && res.getString ("Oid_estoque").length () > 0) {
	          busca = " SELECT cd_estoque, nm_estoque, vl_custo " +
	          		  "  FROM  estoques " +
	          		  " WHERE oid_estoque = " + res.getString ("Oid_estoque");
	          rs = this.executasql.executarConsulta (busca);
	          if (rs.next ()) {
	            edVolta.setCd_estoque (rs.getString (1));
	            edVolta.setNm_estoque (rs.getString (2));

	          }
	        }

	        edVolta.setVl_total_item(edVolta.getVl_quantidade()*edVolta.getVl_unitario());

			list.add (edVolta);
	      }

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao listar registros");
	      excecoes.setMetodo ("Lista_Itens_Entrega()");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }

	    return list;
  }

  public void inclui_pedido (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    int valOid = 0;
    String dt_vencimento=Data.getDataDMY();
    try {


	  sql =" SELECT Contas.dm_vencimento " +
	       " FROM   Contas, solicitacoes_compras " +
	       " WHERE 	solicitacoes_compras.oid_Conta = Contas.oid_Conta " +
		   " AND    Contas.dm_vencimento IS NOT NULL" +
		   " AND    solicitacoes_compras.oid_solicitacao_compra='" +  ed.getOid_Solicitacao_compra () + "'";

      System.out.println ("PEDIDO INCLUIDO!!! nr: " + sql);

	  ResultSet resCta = executasql.executarConsulta (sql);
	  if (resCta.next ()) {
	      System.out.println ("PEDIDO Idm_vencimento: " + resCta.getString("dm_vencimento"));
	   	  if (resCta.getString("dm_vencimento")!= null && !resCta.getString("dm_vencimento").equals("null") && !resCta.getString("dm_vencimento").equals("")){
	   		  dt_vencimento=Data.manipulaDias(Data.getDataDMY (), resCta.getInt("dm_vencimento"));
	   	  }
	  }


      sql = "INSERT INTO Pedidos_Compras (" +
          " oid_Solicitacao_compra," +
          " oid_pedido_compra," +
          " dt_stamp," +
          " usuario_stamp," +
          " dm_stamp," +
          " nm_observacoes," +
          " dt_Pedido_compra," +
          " dt_vencimento," +
          " dm_status" +
          ")";
      sql += " values ( ";
      sql += ed.getOid_Solicitacao_compra () + "," +
          ed.getOid_Solicitacao_compra () + ",'" +
          Data.getDataDMY () + "','','S'," +  "''," +
          "'" + Data.getDataDMY () + "'," +
          "'" + dt_vencimento + "'," +
          "'E')";

      //System.out.println ("PEDIDO INCLUIDO!!! nr: " + sql);

      int i = executasql.executarUpdate (sql);
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
  }

  public void atualiza_Pedido (Solicitacao_CompraED ed) throws Excecoes {

	    String sql = null;
	    ResultSet rs= null;
	    try {
	    	  Solicitacao_CompraED edVolta = this.getByRecord_pedido(ed);
	          double vl_pedido_realizado=0;
	          double vl_pedido_entregue=0;

	          sql = " SELECT * FROM itens_solicitacoes_entregas, itens_solicitacoes_compras " +
	          		" WHERE itens_solicitacoes_entregas.oid_item_solicitacao_compra = itens_solicitacoes_compras.oid_item_solicitacao_compra" +
	          		" AND   itens_solicitacoes_entregas.oid_movimento_estoque IS NOT NULL "  +
	          		" AND   itens_solicitacoes_compras.oid_Solicitacao_compra = " + edVolta.getOid_Solicitacao_compra ();

	          System.out.println(sql);

					rs = this.executasql.executarConsulta (sql);
					while (rs.next ()) {
						vl_pedido_entregue+=rs.getDouble("vl_quantidade_entrega") * rs.getDouble("vl_unitario");
						if (rs.getString("oid_nota_fiscal")!= null && rs.getString("oid_nota_fiscal").length()>4){
							vl_pedido_realizado+=rs.getDouble("vl_quantidade_entrega") * rs.getDouble("vl_unitario");
						}
					}

			  sql =" UPDATE Pedidos_Compras SET vl_pedido_realizado="  + vl_pedido_realizado +
			  "                               , vl_pedido=" + edVolta.getVl_Pedido() +
			  "                               , vl_pedido_entregue=" + vl_pedido_entregue +
			  "      WHERE oid_pedido_compra = " + edVolta.getOid_Pedido_compra();

	          System.out.println(sql);

	      int i = executasql.executarUpdate (sql);
	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      exc.printStackTrace ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao incluir!");
	      excecoes.setMetodo ("atualiza_pedido()");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }


  public Solicitacao_CompraED getByRecord_pedido (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    ResultSet rs = null;
    FormataDataBean dataFormatada = new FormataDataBean ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();

    try {

    	if (ed.getOid_Solicitacao_compra()==0){
		      sql = " SELECT Oid_Solicitacao_compra " +
		      		" FROM Pedidos_compras" +
		      		" WHERE oid_Pedido_compra = " + ed.getOid_Pedido_compra ();

		      ResultSet res = this.executasql.executarConsulta (sql);
		      if (res.next ()) {
		    	  ed.setOid_Solicitacao_compra(res.getLong("Oid_Solicitacao_compra"));
		      }
    	}
	    edVolta = this.getByRecord(ed);

        String Busca = " SELECT * " +
        			   " FROM pedidos_compras " +
        		       " WHERE Oid_Solicitacao_compra = " + ed.getOid_Solicitacao_compra();
        rs = this.executasql.executarConsulta (Busca);
        if (rs.next ()) {

          edVolta.setOid_Pedido_compra (rs.getLong("oid_pedido_compra"));
          edVolta.setOid_pedido (rs.getLong("oid_pedido_compra"));
          edVolta.setNr_pedido(rs.getLong("oid_pedido_compra"));

          edVolta.setDm_status_pedido (rs.getString ("dm_status"));
          if (edVolta.getDm_status_pedido () != null &&
              edVolta.getDm_status_pedido ().equals ("P")) {
            edVolta.setDm_status_pedido ("Pendente");
          }
          if (edVolta.getDm_status_pedido () != null &&
              edVolta.getDm_status_pedido ().equals ("C")) {
            edVolta.setDm_status_pedido ("Cancelado");
          }
          if (edVolta.getDm_status_pedido () != null &&
              edVolta.getDm_status_pedido ().equals ("E")) {
            edVolta.setDm_status_pedido ("Encerrado");
          }
          if (edVolta.getDm_status_pedido () != null &&
              edVolta.getDm_status_pedido ().equals ("A")) {
            edVolta.setDm_status_pedido ("Parcial");
          }
          if (edVolta.getDm_status_pedido () != null &&
              edVolta.getDm_status_pedido ().equals ("N")) {
            edVolta.setDm_status_pedido ("Nota Fiscal");
          }
          edVolta.setDt_pedido (rs.getString ("Dt_Pedido_compra"));
          dataFormatada.setDT_FormataData (edVolta.getDt_pedido ());
          edVolta.setDt_pedido (dataFormatada.getDT_FormataData ());

          edVolta.setDt_vencimento (rs.getString ("Dt_Vencimento"));
          dataFormatada.setDT_FormataData (edVolta.getDt_vencimento ());
          edVolta.setDt_vencimento (dataFormatada.getDT_FormataData ());

          edVolta.setVl_pedido_entregue(rs.getDouble("Vl_pedido_entregue"));
          edVolta.setVl_pedido_realizado(rs.getDouble("Vl_pedido_realizado"));

		  edVolta.setVl_total_produto_entrega(rs.getDouble("Vl_pedido_entregue"));
		  if (rs.getDouble("Vl_pedido_entregue")>0){
			  edVolta.setVl_total_produto_entrega_gerar_nota(rs.getDouble("Vl_pedido_entregue")-rs.getDouble("Vl_pedido_realizado"));
		  }

 	      edVolta.setDt_vencimento_minimo(Data.getDataDMY());
		  sql =	" SELECT Contas.dm_vencimento " +
		      	" FROM   Contas " +
		      	" WHERE	 Contas.dm_vencimento IS NOT NULL" +
		 	   	" AND    Contas.oid_Conta='" +edVolta.getOid_Conta() + "'";
		  ResultSet resCta = executasql.executarConsulta (sql);
		  if (resCta.next ()) {
	    	     if (resCta.getString("dm_vencimento")!= null && !resCta.getString("dm_vencimento").equals("null") && !resCta.getString("dm_vencimento").equals("")){
		    	     edVolta.setDt_vencimento_minimo(Data.manipulaDias(edVolta.getDt_pedido(), resCta.getInt("dm_vencimento")));
	    	     }
		  }


	      sql = " SELECT SUM (vl_parcela) AS TT_Parcela " +
		        " FROM Parcelamentos_Pedidos  " +
		        " WHERE OID_Pedido_Compra = '" + ed.getOid_Pedido_compra () + "'";

	      ResultSet resParc = this.executasql.executarConsulta (sql);
	      if (resParc.next ()) {
			  edVolta.setVl_Parcelamento(resParc.getDouble("TT_Parcela"));
	      }



		  //System.out.println("NR Parcelas=" + edVolta.getNr_parcelas());
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

    return edVolta;
  }

  public Solicitacao_CompraED total_Solicitacao_Compra (Solicitacao_CompraED ed) throws Excecoes {

	    String sql = null;
	    ResultSet res=null;
	    ResultSet rs=null;
	    double vl_pedido=0;
	    double vl_pedido_conta1=0;
	    double vl_pedido_conta2=0;
	    double vl_unitario=0;
	    double nr_quantidade=0;

	    try {

		  //itens

		  sql = " SELECT  itens_solicitacoes_compras.vl_quantidade, itens_solicitacoes_compras.vl_unitario, " +
				"         itens_solicitacoes_compras.oid_item_solicitacao_compra,   " +
				"         itens_solicitacoes_compras.oid_estoque,   " +
				"         itens_solicitacoes_compras.nm_observacoes,  " +
				"         solicitacoes_compras.Oid_Conta_Servico,  " +
				"         estoques.dm_tipo_produto,    " +
				"         estoques.vl_custo    " +
				" FROM    itens_solicitacoes_compras, estoques, solicitacoes_compras " +
				" WHERE   itens_solicitacoes_compras.oid_solicitacao_compra =  solicitacoes_compras.oid_solicitacao_compra " +
				" AND     itens_solicitacoes_compras.oid_estoque =  estoques.oid_estoque " +
				" AND     itens_solicitacoes_compras.oid_solicitacao_compra = " + ed.getOid_Solicitacao_compra ();

		   System.out.println(sql);
		   res = this.executasql.executarConsulta (sql);
		   while (res.next ()) {

		    	  vl_unitario=res.getDouble ("vl_unitario");

			      sql = " SELECT itens_cotacoes_compras.vl_preco, " +
			          	"        itens_cotacoes_compras.nr_prazo_entrega, " +
			          	"        itens_cotacoes_compras.nm_condicao_pgto " +
			          	" FROM   itens_cotacoes_compras, " +
			          	"        cotacoes_compras  " +
			          	" WHERE  itens_cotacoes_compras.dm_status = 'A' " +
			          	" AND    itens_cotacoes_compras.oid_cotacao_compra = cotacoes_compras.oid_cotacao_compra " +
			          	" AND    cotacoes_compras.oid_item_solicitacao_compra = " +res.getString ("oid_item_solicitacao_compra");

			      rs = this.executasql.executarConsulta (sql);
			      if (rs.next ()) {
			    	  vl_unitario=rs.getDouble ("vl_preco");
			      }

			      nr_quantidade++;
			      if (vl_unitario==0){
			    	  vl_unitario=res.getDouble ("vl_custo");
			      }

			      vl_pedido+=res.getDouble ("vl_quantidade") * vl_unitario;
			      if (!"S".equals(res.getString("dm_tipo_produto")) || res.getLong("Oid_Conta_Servico")==0){
				      vl_pedido_conta1+=res.getDouble ("vl_quantidade") * vl_unitario;
			      }else{
				      vl_pedido_conta2+=res.getDouble ("vl_quantidade") * vl_unitario;
			      }

		   }


		   ed.setVl_Pedido(vl_pedido);
		   ed.setVl_Pedido_Conta1(vl_pedido_conta1);
		   ed.setVl_Pedido_Conta2(vl_pedido_conta2);
		   ed.setNr_quantidade(nr_quantidade);

		   System.out.println("vl_pedido=" + vl_pedido);


	 }
	 catch (Exception exc) {
	   exc.printStackTrace ();
	   Excecoes excecoes = new Excecoes ();
	   excecoes.setClasse (this.getClass ().getName ());
	   excecoes.setMensagem ("Erro ao recuperar registro");
	   excecoes.setMetodo ("total_Solicitacao_Compra()");
	   excecoes.setExc (exc);
	   throw excecoes;
	 }

	 return ed;
}


  public void imprime_pedido (Solicitacao_CompraED ed , HttpServletResponse response) throws Excecoes {

    String sql = null;
    String busca = null;
    ArrayList list = new ArrayList ();
    ResultSet rs = null;
    ResultSet res = null;
    FormataDataBean dataFormatada = new FormataDataBean ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();

    // System.out.println("imprime_pedido 1 ");

    try {
      //cabecalho do pedido
      ed.setOid_Pedido_compra (ed.getOid_Solicitacao_compra ());

      sql = " SELECT * FROM Solicitacoes_compras " +
    	    " WHERE oid_Solicitacao_compra = " + ed.getOid_Solicitacao_compra ();

      res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
	        busca = " SELECT unidades.cd_unidade, pessoas.nm_fantasia, pessoas.nm_endereco, pessoas.nm_bairro, cidades.nm_cidade, estados.cd_estado" +
		            " FROM   unidades, pessoas, cidades, regioes_estados, estados " +
		            " WHERE  unidades.oid_pessoa = pessoas.oid_pessoa " +
		            " AND    pessoas.oid_cidade = cidades.oid_cidade " +
		            " AND    cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado " +
		            " AND    regioes_estados.oid_estado = estados.oid_estado " +
		            " AND    unidades.oid_unidade = " + res.getString ("Oid_unidade");

	       //System.out.println(busca);

	        rs = this.executasql.executarConsulta (busca);
	        if (rs.next ()) {
	          ed.setNm_unidade (rs.getString ("cd_unidade") + " - " + rs.getString ("nm_fantasia"));
	          ed.setNm_endereco_entrega (rs.getString ("nm_endereco") + ", " + rs.getString ("nm_bairro") + " - " + rs.getString ("nm_cidade") + "/" + rs.getString ("cd_estado"));
	        }

	        busca = " SELECT pessoas.oid_pessoa, pessoas.nm_razao_social, pessoas.nm_endereco, pessoas.nm_bairro, cidades.nm_cidade, estados.cd_estado" +
			        " FROM   pessoas, cidades, estados, regioes_estados" +
			        " WHERE  pessoas.oid_cidade = cidades.oid_cidade " +
			        " AND    cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado " +
			        " AND    regioes_estados.oid_estado = estados.oid_estado " +
			        " AND    pessoas.oid_pessoa = '" + res.getString ("oid_fornecedor") + "'";
			rs = this.executasql.executarConsulta (busca);
			if (rs.next ()) {
			  ed.setNm_fornecedor (rs.getString ("oid_pessoa") + " - " + rs.getString ("nm_razao_social"));
			  ed.setNm_endereco_fornecedor (rs.getString ("nm_endereco") + ", " + rs.getString ("nm_bairro") + " - " + rs.getString ("nm_cidade") + "/" + rs.getString ("cd_estado"));
			}


	        busca = "SELECT nm_usuario from usuarios where oid_usuario = " + res.getString ("Oid_usuario");
	        rs = this.executasql.executarConsulta (busca);
	        while (rs.next ()) {
	          ed.setNm_usuario (rs.getString ("nm_usuario"));
	        }

	        busca = "SELECT nm_usuario from usuarios where oid_usuario = " + res.getString ("oid_autorizador");
	        rs = this.executasql.executarConsulta (busca);
	        while (rs.next ()) {
	          ed.setNm_autorizador(rs.getString ("nm_usuario"));
	        }

	        ed.setOid_Conta (res.getLong ("Oid_Conta"));
	        if(JavaUtil.doValida(String.valueOf(ed.getOid_Conta()))){
	        	busca = "SELECT cd_conta, nm_conta from contas where oid_conta = " + ed.getOid_Conta();
	        	rs = this.executasql.executarConsulta (busca);
	            while (rs.next ()) {
	              ed.setConta(rs.getString("cd_conta") + " - " + rs.getString("nm_conta"));
	            }
	        }
	        ed.setOid_Veiculo (res.getString("Oid_Veiculo"));
	        String obs="";
	        if ( res.getString("Oid_Veiculo")!=null &&  res.getString("Oid_Veiculo").length()>4){
	          obs="VEICULO:" + res.getString("Oid_Veiculo");
	        }
	        if ( res.getString("TX_Observacao")!=null &&  res.getString("TX_Observacao").length()>4){
	          //obs+=" / " + res.getString("TX_Observacao");
	        	ed.setNm_observacoes(res.getString("TX_Observacao"));
	        }
	        ed.setOid_centro_custo(res.getLong ("Oid_Centro_Custo"));
	        if(JavaUtil.doValida(String.valueOf(ed.getOid_centro_custo()))){
	        	busca = "SELECT cd_centro_custo, nm_centro_custo from centros_custos where oid_centro_custo = " + ed.getOid_centro_custo();
	        	rs = this.executasql.executarConsulta (busca);
	            while (rs.next ()) {
	              ed.setCentro_custo(rs.getString("cd_centro_custo") + " - " + rs.getString("nm_centro_custo"));
	            }
	        }
	        if(JavaUtil.doValida(obs)){
	        	if(JavaUtil.doValida(ed.getCentro_custo()))
	        		ed.setCentro_custo(ed.getCentro_custo() + "\n" + obs);
	        	else
	        		ed.setCentro_custo(obs);
	        }


           //ed.setNm_prazo_entrega(dataFormatada.getDT_FormataData(res.getString("dt_entrega")));

	        ed.setDt_Solicitacao (res.getString ("Dt_Solicitacao"));
	        dataFormatada.setDT_FormataData (ed.getDt_Solicitacao ());
	        ed.setDt_Solicitacao (dataFormatada.getDT_FormataData ());
      }

      //itens

      sql = " SELECT  itens_solicitacoes_compras.vl_quantidade, " +
			"         itens_solicitacoes_compras.Vl_unitario,   " +
			"         itens_solicitacoes_compras.oid_item_solicitacao_compra,   " +
			"         itens_solicitacoes_compras.oid_estoque,   " +
			"         itens_solicitacoes_compras.nm_produto,  " +
			"         itens_solicitacoes_compras.nm_observacoes  " +
			" FROM    itens_solicitacoes_compras  " +
			" WHERE   itens_solicitacoes_compras.oid_solicitacao_compra = " + ed.getOid_Solicitacao_compra ();

       System.out.println(sql);
	   res = this.executasql.executarConsulta (sql);
	   while (res.next ()) {
		      edVolta = new Solicitacao_CompraED ();
		      edVolta.setNm_observacoes(JavaUtil.doValida(res.getString("nm_Observacoes"))?res.getString("nm_Observacoes"):"");
		      edVolta.setNm_produto(JavaUtil.doValida(res.getString("nm_produto"))?res.getString("nm_produto"):"");
		      edVolta.setVl_unitario(res.getDouble("Vl_unitario"));
		      edVolta.setVl_quantidade (res.getDouble ("vl_quantidade"));

	          edVolta.setCd_estoque ("");
	          edVolta.setNm_estoque ("");
	          sql = " SELECT cd_estoque, nm_estoque, vl_custo" +
		      		  " FROM   estoques " +
		      		  " WHERE  oid_estoque = " + res.getString ("Oid_estoque");
		      rs = this.executasql.executarConsulta (sql);
		      if (rs.next ()) {
		         edVolta.setCd_estoque (rs.getString (1));
		         edVolta.setNm_estoque (rs.getString (2));
		         if (edVolta.getVl_unitario()==0){
			         edVolta.setVl_unitario(rs.getDouble("vl_custo"));
		         }
		      }

		      sql = " SELECT itens_cotacoes_compras.vl_preco, " +
		          	"        itens_cotacoes_compras.nr_prazo_entrega, " +
		          	"        itens_cotacoes_compras.nm_condicao_pgto " +
		          	" FROM   itens_cotacoes_compras, " +
		          	"        cotacoes_compras  " +
		          	" WHERE  itens_cotacoes_compras.dm_status = 'A' " +
		          	" AND    itens_cotacoes_compras.oid_cotacao_compra = cotacoes_compras.oid_cotacao_compra " +
		          	" AND    cotacoes_compras.oid_item_solicitacao_compra = " +res.getString ("oid_item_solicitacao_compra");

		      rs = this.executasql.executarConsulta (sql);
		      if (rs.next ()) {
		         ed.setNm_cond_pgto (rs.getString ("nm_condicao_pgto"));
		         edVolta.setNm_observacoes(JavaUtil.doValida(rs.getString("nr_prazo_entrega"))?rs.getString("nr_prazo_entrega"):"");
		         edVolta.setVl_unitario (rs.getDouble ("vl_preco"));
		      }

	        edVolta.setVl_total_item (edVolta.getVl_quantidade () * edVolta.getVl_unitario ());
	        list.add (edVolta);
      }

      Solicitacao_CompraRL rl = new Solicitacao_CompraRL ();
      rl.relPedido (list , response , ed);

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
  }

  public void imprime_solicitacao (Solicitacao_CompraED ed , HttpServletResponse response) throws Excecoes {

	    String sql = null;
	    String busca = null;
	    ArrayList list = new ArrayList ();
	    ResultSet rs = null;
	    ResultSet rsLocal = null;
	    ResultSet res = null;
	    FormataDataBean dataFormatada = new FormataDataBean ();
	    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();

	    // System.out.println("imprime_pedido 1 ");

	    try {
	      //cabecalho do pedido

	      sql = " select * from Solicitacoes_compras";
	      sql += " where 1 = 1 ";
	      if (String.valueOf (ed.getOid_Solicitacao_compra ()) != null &&
	          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("null") &&
	          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("0")) {
	        sql += " and oid_Solicitacao_compra = " + ed.getOid_Solicitacao_compra ();
	      }
	      // System.out.println(sql);


	      res = null;
	      res = this.executasql.executarConsulta (sql);
	      while (res.next ()) {
		        busca = " SELECT unidades.cd_unidade, pessoas.nm_fantasia, pessoas.nm_endereco, pessoas.nm_bairro, cidades.nm_cidade, estados.cd_estado" +
			            " FROM   unidades, pessoas, cidades, regioes_estados, estados " +
			            " WHERE  unidades.oid_pessoa = pessoas.oid_pessoa " +
			            " AND    pessoas.oid_cidade = cidades.oid_cidade " +
			            " AND    cidades.oid_regiao_estado = regioes_estados.oid_regiao_estado " +
			            " AND    regioes_estados.oid_estado = estados.oid_estado " +
			            " AND    unidades.oid_unidade = " + res.getString ("Oid_unidade");


	        rs = this.executasql.executarConsulta (busca);
	        if (rs.next ()) {
	          ed.setNm_unidade (rs.getString ("cd_unidade") + " - " + rs.getString ("nm_fantasia"));
	          ed.setNm_endereco_entrega (rs.getString ("nm_endereco") + ", " + rs.getString ("nm_bairro") + " - " + rs.getString ("nm_cidade") + "/" + rs.getString ("cd_estado"));
	        }
	        busca = "select nm_usuario from usuarios where oid_usuario = " + res.getString ("Oid_usuario");
	        rs = this.executasql.executarConsulta (busca);
	        while (rs.next ()) {
	          ed.setNm_usuario (rs.getString ("nm_usuario"));
	        }
	        busca = "select nm_usuario from usuarios where oid_usuario = " + res.getString ("oid_autorizador");
	        rs = this.executasql.executarConsulta (busca);
	        while (rs.next ()) {
	          ed.setNm_autorizador(rs.getString ("nm_usuario"));
	        }

	    // System.out.println("imprime_pedido 2 ");

	        ed.setOid_Conta (res.getLong ("Oid_Conta"));
	        ed.setOid_Veiculo (res.getString("Oid_Veiculo"));
	        String obs="";
	        if ( res.getString("Oid_Veiculo")!=null &&  res.getString("Oid_Veiculo").length()>4){
	          obs="VEICULO:" + res.getString("Oid_Veiculo");
	        }
	        if ( res.getString("TX_Observacao")!=null &&  res.getString("TX_Observacao").length()>4){
	          obs+=" / " + res.getString("TX_Observacao");
	        }

	        ed.setCentro_custo(obs);

	        ed.setDt_Solicitacao (res.getString ("Dt_Solicitacao"));
	        dataFormatada.setDT_FormataData (ed.getDt_Solicitacao ());
	        ed.setDt_Solicitacao (dataFormatada.getDT_FormataData ());
	      }

	      //itens
	      sql = " select * " +
	          " from itens_solicitacoes_compras " +
	          " where 1=1 ";
	      if (String.valueOf (ed.getOid_Solicitacao_compra ()) != null &&
	          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("null") &&
	          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("0")) {
	        sql += "and itens_solicitacoes_compras.oid_solicitacao_compra = " + ed.getOid_Solicitacao_compra ();
	      }

	      // System.out.println(sql);

	      res = null;
	      res = this.executasql.executarConsulta (sql);
	      while (res.next ()) {
	    	  edVolta = new Solicitacao_CompraED ();
	    // System.out.println("imprime_pedido item ");

          ed.setNm_fornecedor ("");
          ed.setNm_endereco_fornecedor ("");

	        edVolta.setVl_quantidade (res.getDouble ("vl_quantidade"));
	        rs = null;
	        edVolta.setOid_estoque (res.getLong ("Oid_estoque"));
	        if (res.getString ("Oid_estoque") != null && !res.getString ("Oid_estoque").equals ("0") && res.getString ("Oid_estoque").length () > 0) {
              long oid_unidade_produto = 0;
	          busca = "select cd_estoque, nm_estoque, oid_unidade_produto from estoques where oid_estoque = " + res.getString ("Oid_estoque");
	          rs = this.executasql.executarConsulta (busca);
	          while (rs.next ()) {
	            edVolta.setCd_estoque (rs.getString (1));
	            edVolta.setNm_estoque (rs.getString (2));
	            oid_unidade_produto = (rs.getLong(3));
	            if (oid_unidade_produto > 0){
		          busca = "select cd_unidade_produto from unidades_produtos where oid_unidade_produto = " + oid_unidade_produto;
		          // System.out.println(busca);
		          rsLocal = this.executasql.executarConsulta (busca);
		          while (rsLocal.next ()) {
	                 edVolta.setCd_unidade_produto(rsLocal.getString (1));
		          }
	            }
	          }
	        }
	        else {
	          edVolta.setCd_estoque ("");
              edVolta.setCd_unidade_produto("");
	          edVolta.setNm_estoque ("");
	          String produto = res.getString ("nm_produto");
	          if (produto != null && !produto.equals ("null")) {
	            edVolta.setNm_estoque (produto);
	          }
	        }
	        list.add (edVolta);
	        ed.setNr_pedido(res.getLong("oid_solicitacao_compra"));
	        ed.setNm_prazo_entrega (res.getString ("nr_prazo_entrega"));
	      }

	    // System.out.println("imprime_pedido 99 ");

	      Solicitacao_CompraRL rl = new Solicitacao_CompraRL ();
	      rl.imprime_solicitacao (list , response , ed);

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
  }

  public ArrayList Lista_fornecedores_cotacoes (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    String produto = null;
    String busca = "";
    ArrayList list = new ArrayList ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();

    try {

      sql = " select oid_fornecedor from itens_cotacoes_compras, cotacoes_compras " +
          " where itens_cotacoes_compras.dm_status = 'A' and " +
          " itens_cotacoes_compras.oid_cotacao_compra = cotacoes_compras.oid_cotacao_compra ";
      if (String.valueOf (ed.getOid_Solicitacao_compra ()) != null &&
          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("0")) {
        sql += "and cotacoes_compras.oid_solicitacao_compra = " + ed.getOid_Solicitacao_compra ();
      }
      sql += " order by oid_fornecedor";
      ResultSet res = null;
      ResultSet rs = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_fornecedor (res.getString (1));
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

  public String Finaliza_Pedido (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    ResultSet rs = null;
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    FormataDataBean dataFormatada = new FormataDataBean ();
    double vl_Qtde_sol = 0;
    double vl_Qtde_ent = 0;
    double vl_Qtde_Tot_sol = 0;
    double vl_Qtde_Tot_ent = 0;
    String dt_entrega = null;
    String dt_pedido = null;
    int nr_prazo_ent = 0;
    double vl_Preco_item_sol = 0;
    double vl_Preco_item_ent = 0;
    String oid_Estoque = null;
    String oid_Fornecedor = null;
    double vl_Total_item_sol = 0;
    double vl_Total_item_ent = 0;
    double vl_Total_pedido = 0;
    double vl_Total_nf = 0;
    String msg = "";
    String status_Ped = "E";
    String oid_item_solicitacao = "";
    double vl_Qtde_item_entrega = 0;

    try {

      //verifica se o pedido tem NF associada.
      sql = "select * from pedidos_compras_notas_fiscais WHERE oid_Pedido_compra = " + ed.getOid_Pedido_compra ();
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      if (!res.next ()) {
        Excecoes excecao = new Excecoes ();
        excecao.setMensagem ("Pedido n�o associado a nenhuma Nota Fiscal. N�o � poss�vel finalizar.");
        throw excecao;
      }
      res.beforeFirst ();

      while (res.next ()) {
        if (!res.getString ("dm_finalizado").equals ("S")) {
          edVolta.setOid_nota_fiscal (res.getString ("oid_nota_fiscal"));

          //localiza itens do pedido/solicitacao.
          sql = "select * from itens_solicitacoes_compras, itens_cotacoes_compras, cotacoes_compras " +
              " where itens_solicitacoes_compras.oid_solicitacao_compra = solicitacoes_compras.oid_solicitacao_compra " +
              " and cotacoes_compras.oid_item_solicitacao_compra = itens_solicitacoes_compras.oid_item_solicitacao_compra " +
              " and itens_cotacoes_compras.oid_cotacao_compra = cotacoes_compras.oid_cotacao_compra " +
              " and itens_cotacoes_compras.dm_status = 'A' " +
              " and solicitacoes_compras.oid_solicitacao_compra = " + ed.getOid_Pedido_compra ();
          sql += " order by oid_estoque ";
          res = null;
          res = this.executasql.executarConsulta (sql);
          while (res.next ()) {
            if (res.getString ("oid_estoque") != null) {
              vl_Qtde_item_entrega = 0;
              vl_Qtde_sol = res.getDouble ("vl_quantidade");
              vl_Qtde_Tot_sol += res.getDouble ("vl_quantidade");
              nr_prazo_ent = res.getInt ("nr_prazo_entrega");
              vl_Preco_item_sol = res.getDouble ("vl_preco");
              oid_Estoque = res.getString ("oid_estoque");
              oid_Fornecedor = res.getString ("oid_fornecedor");
              oid_item_solicitacao = res.getString ("oid_item_solicitacao_compra");
              rs = null;
              rs = this.executasql.executarConsulta ("select dt_stamp from pedidos_compras where oid_pedido_compra = " + ed.getOid_Pedido_compra ());
              while (rs.next ()) {
                dataFormatada.setDT_FormataData (rs.getString (1));
                dt_pedido = dataFormatada.getDT_FormataData ();
              }
              vl_Total_item_sol = vl_Qtde_sol * vl_Preco_item_sol;
              vl_Total_pedido += vl_Total_item_sol;

              //localiza e compra os dados de NF com os do pedido/solicitacao
              sql = "select notas_fiscais_transacoes.vl_nota_fiscal, " +
                  " itens_notas_fiscais_transacoes.vl_liquido, itens_notas_fiscais_transacoes.nr_volumes, notas_fiscais_transacoes.oid_pessoa, " +
                  " romaneios_notas_fiscais.vl_quantidade, romaneios_notas_fiscais.dt_stamp " +
                  " from notas_fiscais_transacoes, itens_notas_fiscais_transacoes, romaneios_notas_fiscais " +
                  " where notas_fiscais_transacoes.oid_nota_fiscal = itens_notas_fiscais_transacoes.oid_nota_fiscal " +
                  " and romaneios_notas_fiscais.oid_nota_fiscal = notas_fiscais_transacoes.oid_nota_fiscal " +
                  " and notas_fiscais_transacoes.oid_nota_fiscal = '" + edVolta.getOid_nota_fiscal () + "' " +
                  " and itens_notas_fiscais_transacoes.oid_estoque = " + oid_Estoque;
              rs = null;
              rs = this.executasql.executarConsulta (sql);
              while (rs.next ()) {
                vl_Qtde_ent = rs.getDouble ("vl_quantidade");
                vl_Qtde_Tot_ent += rs.getDouble ("vl_quantidade");
                vl_Total_nf = rs.getDouble ("vl_nota_fiscal");
                vl_Preco_item_ent = rs.getDouble ("vl_liquido") / rs.getDouble ("nr_volumes");
                vl_Total_item_ent = rs.getDouble ("vl_liquido");
                dataFormatada.setDT_FormataData (rs.getString ("dt_stamp"));
                dt_entrega = dataFormatada.getDT_FormataData ();

                // atualiza qtde entrega
                sql = "select vl_quantidade_entrega from itens_solicitacoes_compras " +
                    "where oid_item_solicitacao_compra = '" + oid_item_solicitacao + "'";
                ResultSet item = null;
                item = this.executasql.executarConsulta (sql);
                while (item.next ()) {
                  vl_Qtde_item_entrega = item.getDouble ("vl_quantidade_entrega");
                }
                vl_Qtde_item_entrega += vl_Qtde_ent;
                sql = "UPDATE itens_solicitacoes_compras set " +
                    " vl_quantidade_entrega = " + vl_Qtde_item_entrega;
                sql += " WHERE oid_item_solicitacao_compra = '" + oid_item_solicitacao + "'";
                int u = executasql.executarUpdate (sql);

                //compara os valores
                //qtde
                if (vl_Qtde_sol != vl_Qtde_ent) {
                  //gera divergencia
                  // System.out.println ("//qtde");
                  // System.out.println (vl_Qtde_sol + " # " + vl_Qtde_ent);
                  // System.out.println (res.getString ("oid_estoque") + " -----------------------");
                  msg = " Quantidades diferentes! Pedido: " + vl_Qtde_sol + " # NF: " + vl_Qtde_ent + "\\n ";
                }
                //preco unitario
                if (vl_Preco_item_sol != vl_Preco_item_ent) {
                  //gera divergencia
                  // System.out.println ("//preco unitario");
                  // System.out.println (vl_Preco_item_sol + " # " + vl_Preco_item_ent);
                  // System.out.println (res.getString ("oid_estoque") + " -----------------------");
                  msg += "Pre�os unit�rios diferentes! Pedido: " + vl_Preco_item_sol + " # NF: " + vl_Preco_item_ent + "\\n ";
                }
                //vl total item
                if (vl_Total_item_sol != vl_Total_item_ent) {
                  //gera divergencia
                  // System.out.println ("//vl total item");
                  // System.out.println (vl_Total_item_sol + " # " + vl_Total_item_ent);
                  // System.out.println (res.getString ("oid_estoque") + " -----------------------");
                  msg += "Valor total diferente! Pedido: " + vl_Total_item_sol + " # NF: " + vl_Total_item_ent + "\\n ";
                }
                //total
                if (vl_Total_pedido != vl_Total_nf) {
                  //gera divergencia
                  // System.out.println ("//total");
                  // System.out.println (vl_Total_pedido + " # " + vl_Total_nf);
                  // System.out.println (res.getString ("oid_estoque") + " -----------------------");
                  msg += "Valor de pedido diferente da NF! Pedido: " + vl_Total_pedido + " # NF: " + vl_Total_nf + "\\n ";
                }
                //data pgto
                Calendar cal = Data.stringToCalendar (dt_pedido , "dd/MM/yyyy");
                int diasAtuais = cal.get (Calendar.DAY_OF_MONTH);
                cal.set (Calendar.DAY_OF_MONTH , diasAtuais + nr_prazo_ent);
                Calendar cal2 = Data.stringToCalendar (dt_entrega , "dd/MM/yyyy");
                // System.out.println (dt_pedido);
                // System.out.println ("//data pgto");
                // System.out.println (dt_entrega);
                // System.out.println (nr_prazo_ent + " " + diasAtuais + nr_prazo_ent);
                // System.out.println (cal2.get (Calendar.DAY_OF_MONTH) + "/" + cal2.get (Calendar.MONTH) + "/" + cal2.get (Calendar.YEAR) + " # " + cal.get (Calendar.DAY_OF_MONTH) + "/" + cal.get (Calendar.MONTH) + "/" + cal.get (Calendar.YEAR));
                if (cal2.after (cal)) {
                  //gera divergencia
                  // System.out.println ("//data pgto");
                  // System.out.println (cal2.get (Calendar.DAY_OF_MONTH) + "/" + cal2.get (Calendar.MONTH) + "/" + cal2.get (Calendar.YEAR) + " # " + cal.get (Calendar.DAY_OF_MONTH) + "/" + cal.get (Calendar.MONTH) + "/" + cal.get (Calendar.YEAR));
                  // System.out.println (res.getString ("oid_estoque") + " -----------------------");
                  msg += "Data de Entrega diferente! Pedido: " + cal2.get (Calendar.DAY_OF_MONTH) + "/" + cal2.get (Calendar.MONTH) + "/" + cal2.get (Calendar.YEAR) + " # NF: " + cal.get (Calendar.DAY_OF_MONTH) + "/" + cal.get (Calendar.MONTH) + "/" + cal.get (Calendar.YEAR) + "\\n ";
                }
                //fornecedor
                if (oid_Fornecedor != rs.getString ("oid_pessoa")) {
                  //gera divergencia
                  // System.out.println ("//fornecedor");
                  // System.out.println (oid_Fornecedor + " # " + rs.getString ("oid_pessoa"));
                  // System.out.println (res.getString ("oid_estoque") + " -----------------------");
                  msg += "Fornecedor diferente! Pedido: " + oid_Fornecedor + " # NF: " + rs.getString ("oid_pessoa") + "\\n ";
                }

                sql = "UPDATE pedidos_compras_notas_fiscais set " +
                    " dm_finalizado = 'S'";
                sql += " WHERE oid_nota_fiscal = '" + edVolta.getOid_nota_fiscal () + "'" +
                    " and oid_pedido_compra = " + ed.getOid_Pedido_compra ();
                // System.out.println (sql);
                u = executasql.executarUpdate (sql);

              }
            }
          }
        }

        if (msg.equals ("")) {
          msg = "Finalizado com sucesso!";
        }

        sql = "select vl_quantidade_entrega, vl_quantidade from itens_solicitacoes_compras " +
            "where oid_solicitacao_compra = " + ed.getOid_Pedido_compra ();
        ResultSet item = null;
        // System.out.println (sql);
        item = this.executasql.executarConsulta (sql);
        while (item.next ()) {
          if (item.getDouble ("vl_quantidade_entrega") < item.getDouble ("vl_quantidade")) {
            status_Ped = "A";
          }
          if (item.getDouble ("vl_quantidade_entrega") == 0 && !status_Ped.equals ("A")) {
            status_Ped = "P";
          }
        }

        //seta pedido para status apropriado
        sql = "UPDATE Pedidos_Compras set " +
            " dm_status = '" + status_Ped + "'";
        sql += " WHERE oid_Pedido_compra = " + ed.getOid_Pedido_compra ();
        // System.out.println (sql);
        int i = executasql.executarUpdate (sql);
      }

    }
    catch (Excecoes e) {
      if (e.getMensagem () != null && !e.getMensagem ().equals ("")) {
      }
      else {
        e.setMensagem (msg);
      }
      e.setClasse (this.getClass ().getName ());
      e.setMetodo ("Finaliza_Pedido");
      throw e;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar dados do Pedido. \\n" + msg);
      excecoes.setMetodo ("finaliza_pedido()");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return msg;
  }

  public Solicitacao_CompraED inclui_Nota_Fiscal_Itens_Recebidos(Solicitacao_CompraED ed)throws Excecoes{

	    try{
	      String sql="";
	      ResultSet res=null;
	      ResultSet resAux=null;
	      Solicitacao_CompraED pedidoED = this.getByRecord_pedido(ed);

	      //System.out.println("Forncedor=" + pedidoED.getOid_fornecedor());
	      //System.out.println("Pedido 2=" + pedidoED.getOid_Pedido_compra());
	      //System.out.println("Pedido 2 nr=" + pedidoED.getNr_pedido());
	      //System.out.println("getNr_parcelas=" + ed.getNr_parcelas());


	      String oid_Nota_Fiscal="";
	      double vl_nota_fiscal=0;
	      double vl_servico=0;
	      double vl_unico_mesmo_item=0;
	      long oid_Conta_Servico=0;
		  long oid_Natureza_Operacao_Servico=0;


	      Nota_Fiscal_CompraBD nota_fiscalBD =  new Nota_Fiscal_CompraBD(executasql);
	      Modelo_Nota_FiscalBD modeloNotaBD =  new Modelo_Nota_FiscalBD(executasql);
	      Item_Nota_Fiscal_CompraBD item_nota_fiscalBD =  new Item_Nota_Fiscal_CompraBD(executasql);


	      sql = " SELECT itens_solicitacoes_entregas.oid_Estoque, itens_solicitacoes_compras.vl_unitario, itens_solicitacoes_entregas.vl_quantidade_entrega " +
	    		" FROM  itens_solicitacoes_entregas, " +
	    		"       itens_solicitacoes_compras " +
				" WHERE itens_solicitacoes_entregas.oid_item_solicitacao_compra = itens_solicitacoes_compras.oid_item_solicitacao_compra" +
			    " AND   itens_solicitacoes_compras.oid_Solicitacao_compra = " + pedidoED.getOid_Solicitacao_compra () +
			    " AND   itens_solicitacoes_entregas.oid_nota_fiscal IS NULL "  +
			    " AND   itens_solicitacoes_entregas.oid_movimento_estoque IS NOT NULL "  +
			    " ORDER BY  itens_solicitacoes_entregas.oid_Estoque ";
	      System.out.println(sql);

	      String oid_estoque="";
	      int itens_diferentes=0;
	      res = this.executasql.executarConsulta (sql);
	      while(res.next () ) {
	    	if (!oid_estoque.equals(res.getString("oid_estoque")) && !oid_estoque.equals("")){
	    		itens_diferentes++;
	    	}
	    	vl_unico_mesmo_item+=res.getDouble("vl_unitario") * res.getDouble("vl_quantidade_entrega");
	    	oid_estoque.equals(res.getString("oid_estoque"));
	      }
	      if (itens_diferentes>0){
	    	  vl_unico_mesmo_item=0;
	      }
          System.out.println(" INCLUI NF vl_unico_mesmo_item=" + vl_unico_mesmo_item);


	      sql = " SELECT itens_solicitacoes_entregas.oid_Estoque, itens_solicitacoes_entregas.oid_ordem_servico, itens_solicitacoes_compras.vl_unitario " +
	      		" FROM  itens_solicitacoes_entregas, " +
	      		"       itens_solicitacoes_compras " +
				" WHERE itens_solicitacoes_entregas.oid_item_solicitacao_compra = itens_solicitacoes_compras.oid_item_solicitacao_compra" +
			    " AND   itens_solicitacoes_compras.oid_Solicitacao_compra = " + pedidoED.getOid_Solicitacao_compra () +
			    " AND   itens_solicitacoes_entregas.oid_nota_fiscal IS NULL "  +
			    " AND   itens_solicitacoes_entregas.oid_movimento_estoque IS NOT NULL "  +
			    " ORDER BY  itens_solicitacoes_entregas.oid_Estoque ";
	      System.out.println(sql);


	      res = this.executasql.executarConsulta (sql);
	      while(res.next () && pedidoED.getOid_Pedido_compra()>0 && pedidoED.getOid_fornecedor()!=null && pedidoED.getOid_fornecedor().length()>4) {


	          System.out.println(" INCLUI NF 1");


	    	  if ("".equals(oid_Nota_Fiscal)){
		          Nota_Fiscal_CompraED notaED = new Nota_Fiscal_CompraED();

		          //request em cima dos campos dos forms html
		          notaED.setOid_modelo_nota_fiscal(1);
		          Modelo_Nota_FiscalED Modelo = new Modelo_Nota_FiscalED();
		          Modelo.setOid_Modelo_Nota_Fiscal(new Long(notaED.getOid_modelo_nota_fiscal()).intValue());
		          Modelo = modeloNotaBD.getByRecord(Modelo);

		          notaED.setDM_Contabiliza(Modelo.getDM_Gera_Fiscal());

		          notaED.setOid_pessoa(pedidoED.getOid_fornecedor());

		          notaED.setNr_nota_fiscal(0);
		          if (String.valueOf(ed.getNr_nota_fiscal()) != null && !String.valueOf(ed.getNr_nota_fiscal()).equals("")
		            && !String.valueOf(ed.getNr_nota_fiscal()).equals("null")){
		                notaED.setNr_nota_fiscal(Long.parseLong(ed.getNr_nota_fiscal()));
		          }

		          notaED.setDt_emissao(ed.getDt_emissao());
		          notaED.setNm_serie(ed.getNm_serie());
		          notaED.setDt_entrada(Data.getDataDMY());
		          notaED.setHr_entrada(Data.getHoraHM());
		          notaED.setDm_forma_pagamento("1");
		          notaED.setNr_parcelas(ed.getNr_parcelas());
		          notaED.setDm_tipo_nota_fiscal("E");

		          System.out.println(" INCLUI NF 2");

		          sql = " SELECT oid_Pessoa,  oid_Unidade_Fiscal, oid_Unidade " +
		          		" FROM  Unidades " +
		    		    " WHERE Unidades.oid_Unidade = " + pedidoED.getOid_unidade();
		          resAux = this.executasql.executarConsulta (sql);
		    	  if (resAux.next ()) {
		    		      notaED.setOid_pessoa_destinatario(resAux.getString("oid_Pessoa"));
		    		      notaED.setOID_Unidade_Contabil(resAux.getLong("oid_unidade_fiscal"));
		    		      notaED.setOID_Unidade_Fiscal(resAux.getLong("oid_Unidade_Fiscal"));
		    		      notaED.setOID_Unidade_Pagadora(resAux.getLong("oid_Unidade"));
		    	  }

		    	  notaED.setOid_conta(pedidoED.getOid_Conta());
		    	  sql = " SELECT oid_Natureza_Operacao " +
		        		" FROM  Contas " +
		    		    " WHERE Contas.oid_Conta = " + pedidoED.getOid_Conta();
		    	  resAux = this.executasql.executarConsulta (sql);
		    	  if (resAux.next ()) {
		    		      notaED.setOid_natureza_operacao(resAux.getLong("oid_Natureza_Operacao"));
		    	  }

		          System.out.println(" INCLUI NF 3 === pedidoED.getOid_Conta_Servico()=" + pedidoED.getOid_Conta_Servico());



		          notaED.setOid_centro_custo(pedidoED.getOid_centro_custo());


		          notaED.setNr_volumes(1);

		          notaED.setVl_nota_fiscal(vl_nota_fiscal);
		          notaED.setVl_liquido_nota_fiscal(vl_nota_fiscal);

		          notaED.setDm_observacao("RECEBIMENTO ITEMS PEDIDO:" + pedidoED.getNr_pedido());


		          System.out.println(" INCLUI NF 6");

		          notaED = nota_fiscalBD.inclui(notaED);
		          oid_Nota_Fiscal = notaED.getOid_nota_fiscal();
	    	  }

	    	  if (oid_Nota_Fiscal !=null && oid_Nota_Fiscal.length()>4 ){


		          System.out.println(" INCLUI NF 7");

	    		  	  sql = " SELECT SUM (vl_quantidade_entrega) as vl_quantidade_entrega, Estoques.oid_unidade_produto, Estoques.NM_Estoque " +
			          		" FROM  itens_solicitacoes_entregas, Estoques  " +
			          		" WHERE itens_solicitacoes_entregas.oid_Estoque = Estoques.oid_Estoque  " +
						    " AND   itens_solicitacoes_entregas.oid_nota_fiscal IS NULL "  +
						    " AND   itens_solicitacoes_entregas.oid_movimento_estoque IS NOT NULL "  +
					    	" AND 	itens_solicitacoes_entregas.oid_Solicitacao_compra = " + pedidoED.getOid_Solicitacao_compra () +
					    	" AND   Estoques.oid_Estoque =" + res.getLong("oid_Estoque") +
				    		" GROUP BY  Estoques.oid_Estoque, Estoques.oid_unidade_produto, Estoques.NM_Estoque " ;

			          System.out.println(sql);

			          resAux = this.executasql.executarConsulta (sql);
			          if (resAux.next ()) {
			        	  vl_nota_fiscal +=resAux.getDouble("vl_quantidade_entrega") * res.getDouble("vl_unitario");

				          System.out.println("ITEM=  Qtde Ent=" + resAux.getString("NM_Estoque") + resAux.getDouble("vl_quantidade_entrega") + "  V.Unit=" +  res.getDouble("vl_unitario") +"  TotIt= "+ resAux.getDouble("vl_quantidade_entrega") * res.getDouble("vl_unitario"));


				          System.out.println(" INCLUI NF 8");

				          Item_Nota_Fiscal_CompraED itemNF = new Item_Nota_Fiscal_CompraED ();
			              itemNF.setOid_Solicitacao_compra(ed.getOid_Solicitacao_compra ());
			              itemNF.setOID_Nota_Fiscal (oid_Nota_Fiscal);
			              itemNF.setVl_quantidade (resAux.getDouble("vl_quantidade_entrega"));
			              itemNF.setVL_Liquido (resAux.getDouble("vl_quantidade_entrega") * res.getDouble("vl_unitario"));
			              itemNF.setVL_Produto (res.getDouble("vl_unitario"));
			              if (vl_unico_mesmo_item>0){
				        	  vl_nota_fiscal =vl_unico_mesmo_item;
				              itemNF.setVl_quantidade (1);
				              itemNF.setVL_Liquido (vl_unico_mesmo_item);
				              itemNF.setVL_Produto (vl_unico_mesmo_item);
			              }


			              itemNF.setVL_IPI (0);
			              itemNF.setOID_Produto (res.getString("oid_Estoque"));
			              itemNF.setCD_Imobiliz ("");
			              itemNF.setOID_Unidade_Produto (resAux.getString("oid_unidade_produto"));;
			              if (res.getLong("oid_Ordem_Servico")>0){
			            	  itemNF.setOid_Ordem_Servico(res.getLong("oid_Ordem_Servico"));
			              }
				          item_nota_fiscalBD.inclui (itemNF);

				    	  sql = " UPDATE itens_Solicitacoes_entregas SET oid_Nota_Fiscal='" + oid_Nota_Fiscal + "'" +
							    " WHERE  itens_solicitacoes_entregas.oid_nota_fiscal IS NULL "  +
							    " AND    itens_solicitacoes_entregas.oid_movimento_estoque IS NOT NULL "  +
						    	" AND 	 itens_solicitacoes_entregas.oid_Solicitacao_compra = " + ed.getOid_Solicitacao_compra () +
						    	" AND    itens_solicitacoes_entregas.oid_Estoque =" + res.getLong("oid_Estoque");

				    	  //System.out.println(sql);

				    	  executasql.executarUpdate (sql);

			          }
	          }
	      }
	      if (vl_nota_fiscal>0){

	    	  if (pedidoED.getOid_Conta_Servico()>0){
		    	  sql = " SELECT oid_Natureza_Operacao " +
		        		" FROM  Contas " +
		    		    " WHERE Contas.oid_Conta = " + pedidoED.getOid_Conta_Servico();
		    	  resAux = this.executasql.executarConsulta (sql);
		    	  if (resAux.next ()) {

			          System.out.println(" INCLUI NF 4");
		    		  oid_Natureza_Operacao_Servico=resAux.getLong("oid_Natureza_Operacao");

			          sql = " SELECT SUM (itens_solicitacoes_entregas.vl_quantidade_entrega) as vl_quantidade_entrega, Estoques.oid_unidade_produto, itens_solicitacoes_compras.vl_unitario " +
		          		" FROM  itens_solicitacoes_entregas, Estoques, itens_solicitacoes_compras  " +
		          		" WHERE itens_solicitacoes_entregas.oid_Estoque = Estoques.oid_Estoque  " +
		          		" AND   itens_solicitacoes_compras.oid_Estoque = Estoques.oid_Estoque  " +
		          		" AND 	itens_solicitacoes_entregas.oid_Solicitacao_compra =  itens_solicitacoes_compras.oid_Solicitacao_compra " +
					    " AND   itens_solicitacoes_entregas.oid_nota_fiscal='"  + oid_Nota_Fiscal + "'" +
				    	" AND 	itens_solicitacoes_entregas.oid_Solicitacao_compra = " + pedidoED.getOid_Solicitacao_compra () +
				    	" AND   Estoques.DM_Tipo_Produto='S'" +
			    		" GROUP BY  Estoques.oid_Estoque, Estoques.oid_unidade_produto, itens_solicitacoes_compras.vl_unitario" ;
			          System.out.println(sql);

			          resAux = this.executasql.executarConsulta (sql);
			          while (resAux.next ()) {

				          System.out.println(" INCLUI NF 50");
				          System.out.println(" INCLUI NF 50 resAux.getDouble(vl_quantidade_entrega==" + resAux.getDouble("vl_quantidade_entrega"));
				          System.out.println(" INCLUI NF 50 resAux.getDouble(vl_unitario==" + resAux.getDouble("vl_unitario"));

			        	  vl_servico +=resAux.getDouble("vl_quantidade_entrega") * resAux.getDouble("vl_unitario");
			        	  if (vl_servico>0 ){
					    	  oid_Conta_Servico=pedidoED.getOid_Conta_Servico();
			        	  }
				          System.out.println(" INCLUI NF 5 vl_servico=" + vl_servico);
			    	  }
		    	 }
	    	  }





	    	  System.out.println(" vl_nota_fiscal=" + vl_nota_fiscal);
	    	  System.out.println(" VL_Servico=" + vl_servico);
	    	  System.out.println(" oid_Conta_Servico=" + oid_Conta_Servico);

	    	  double vl_liquido_nota_fiscal=vl_nota_fiscal;
	    	  if (vl_servico>0 && oid_Conta_Servico>0 && vl_servico<=vl_nota_fiscal){
	    		  vl_nota_fiscal=vl_nota_fiscal-vl_servico;
	    	  }else{
	    		  vl_servico=0;
	    		  oid_Conta_Servico=0;
	    		  oid_Natureza_Operacao_Servico=0;
	    	  }

	    	  sql =	" UPDATE notas_fiscais_transacoes SET " +
	    	  		"  oid_Conta_Servico= " + oid_Conta_Servico +
	    	  		", oid_Natureza_Operacao_Servico=" + oid_Natureza_Operacao_Servico +
	    	  		", vl_servico= " + vl_servico+
	    	  		", oid_Pedido_Compra=" + pedidoED.getOid_Pedido_compra() +
	    	  		", vl_nota_fiscal=" + vl_nota_fiscal +
	    	  		", vl_liquido_nota_fiscal=" + vl_liquido_nota_fiscal +
	    	  		" WHERE oid_Nota_Fiscal='" + oid_Nota_Fiscal + "'";

	    	  System.out.println(sql);

	    	  executasql.executarUpdate (sql);

	    	  nota_fiscalBD.inclui_Parcelamento(oid_Nota_Fiscal, ed.getNr_parcelas(), vl_liquido_nota_fiscal);

	      }

	      this.atualiza_Pedido(ed);



	    }
	    catch (Excecoes exc){
	      throw exc;
	    }
	    catch(Exception exc){
	      exc.printStackTrace();
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("erro ao incluir");
	      excecoes.setMetodo("inclui");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }

	    return ed;
	  }


  public Solicitacao_CompraED inclui_Nota_Fiscal_Itens_Recebidos_OOOOOOOOOOOOOLLLLLLLLLLLLLLLDDDDDDD(Solicitacao_CompraED ed)throws Excecoes{

    try{
      String sql="";
      ResultSet res=null;
      ResultSet resAux=null;
      Solicitacao_CompraED pedidoED = this.getByRecord_pedido(ed);

      //System.out.println("Forncedor=" + pedidoED.getOid_fornecedor());
      //System.out.println("Pedido 2=" + pedidoED.getOid_Pedido_compra());
      //System.out.println("Pedido 2 nr=" + pedidoED.getNr_pedido());
      //System.out.println("getNr_parcelas=" + ed.getNr_parcelas());


      String oid_Nota_Fiscal="";
      double vl_nota_fiscal=0;

      Nota_Fiscal_CompraBD nota_fiscalBD =  new Nota_Fiscal_CompraBD(executasql);
      Modelo_Nota_FiscalBD modeloNotaBD =  new Modelo_Nota_FiscalBD(executasql);
      Item_Nota_Fiscal_CompraBD item_nota_fiscalBD =  new Item_Nota_Fiscal_CompraBD(executasql);

      sql = " SELECT itens_solicitacoes_entregas.oid_Estoque, itens_solicitacoes_entregas.oid_ordem_servico, itens_solicitacoes_compras.vl_unitario " +
      		" FROM  itens_solicitacoes_entregas, " +
      		"       itens_solicitacoes_compras " +
			" WHERE itens_solicitacoes_entregas.oid_item_solicitacao_compra = itens_solicitacoes_compras.oid_item_solicitacao_compra" +
		    " AND   itens_solicitacoes_compras.oid_Solicitacao_compra = " + pedidoED.getOid_Solicitacao_compra () +
		    " AND   itens_solicitacoes_entregas.oid_nota_fiscal IS NULL "  +
		    " AND   itens_solicitacoes_entregas.oid_movimento_estoque IS NOT NULL "  +
		    " ORDER BY  itens_solicitacoes_entregas.oid_Estoque ";
      //System.out.println(sql);


      res = this.executasql.executarConsulta (sql);
      while(res.next () && pedidoED.getOid_Pedido_compra()>0 && pedidoED.getOid_fornecedor()!=null && pedidoED.getOid_fornecedor().length()>4) {

    	  if ("".equals(oid_Nota_Fiscal)){
	          Nota_Fiscal_CompraED notaED = new Nota_Fiscal_CompraED();

	          //request em cima dos campos dos forms html
	          notaED.setOid_modelo_nota_fiscal(1);
	          Modelo_Nota_FiscalED Modelo = new Modelo_Nota_FiscalED();
	          Modelo.setOid_Modelo_Nota_Fiscal(new Long(notaED.getOid_modelo_nota_fiscal()).intValue());
	          Modelo = modeloNotaBD.getByRecord(Modelo);

	          notaED.setDM_Contabiliza(Modelo.getDM_Gera_Fiscal());

	          notaED.setOid_pessoa(pedidoED.getOid_fornecedor());

	          notaED.setNr_nota_fiscal(0);
	          if (String.valueOf(ed.getNr_nota_fiscal()) != null && !String.valueOf(ed.getNr_nota_fiscal()).equals("")
	            && !String.valueOf(ed.getNr_nota_fiscal()).equals("null")){
	                notaED.setNr_nota_fiscal(Long.parseLong(ed.getNr_nota_fiscal()));
	          }

	          notaED.setDt_emissao(ed.getDt_emissao());
	          notaED.setNm_serie(ed.getNm_serie());
	          notaED.setDt_entrada(Data.getDataDMY());
	          notaED.setHr_entrada(Data.getHoraHM());
	          notaED.setDm_forma_pagamento("1");
	          notaED.setNr_parcelas(ed.getNr_parcelas());
	          notaED.setDm_tipo_nota_fiscal("E");


	          sql = " SELECT oid_Pessoa,  oid_Unidade_Fiscal, oid_Unidade " +
	          		" FROM  Unidades " +
	    		    " WHERE Unidades.oid_Unidade = " + pedidoED.getOid_unidade();
	    		resAux = this.executasql.executarConsulta (sql);
	    		if (resAux.next ()) {
	    		      notaED.setOid_pessoa_destinatario(resAux.getString("oid_Pessoa"));
	    		      notaED.setOID_Unidade_Contabil(resAux.getLong("oid_unidade_fiscal"));
	    		      notaED.setOID_Unidade_Fiscal(resAux.getLong("oid_Unidade_Fiscal"));
	    		      notaED.setOID_Unidade_Pagadora(resAux.getLong("oid_Unidade"));
	    		}

	    	  notaED.setOid_conta(pedidoED.getOid_Conta());
	    	  sql = " SELECT oid_Natureza_Operacao " +
	        		" FROM  Contas " +
	    		    " WHERE Contas.oid_Conta = " + pedidoED.getOid_Conta();
	    		resAux = this.executasql.executarConsulta (sql);
	    		if (resAux.next ()) {
	    		      notaED.setOid_natureza_operacao(resAux.getLong("oid_Natureza_Operacao"));
	    		}

	          notaED.setOid_centro_custo(pedidoED.getOid_centro_custo());


	          notaED.setNr_volumes(1);

	          notaED.setVl_nota_fiscal(vl_nota_fiscal);
	          notaED.setVl_liquido_nota_fiscal(vl_nota_fiscal);

	          notaED.setDm_observacao("RECEBIMENTO ITEMS PEDIDO:" + pedidoED.getNr_pedido());

	          notaED = nota_fiscalBD.inclui(notaED);
	          oid_Nota_Fiscal = notaED.getOid_nota_fiscal();
    	  }

    	  if (oid_Nota_Fiscal !=null && oid_Nota_Fiscal.length()>4 ){

	          sql = " SELECT SUM (vl_quantidade_entrega) as vl_quantidade_entrega, Estoques.oid_unidade_produto, Estoques.NM_Estoque " +
	          		" FROM  itens_solicitacoes_entregas, Estoques  " +
	          		" WHERE itens_solicitacoes_entregas.oid_Estoque = Estoques.oid_Estoque  " +
				    " AND   itens_solicitacoes_entregas.oid_nota_fiscal IS NULL "  +
				    " AND   itens_solicitacoes_entregas.oid_movimento_estoque IS NOT NULL "  +
			    	" AND 	itens_solicitacoes_entregas.oid_Solicitacao_compra = " + pedidoED.getOid_Solicitacao_compra () +
			    	" AND   Estoques.oid_Estoque =" + res.getLong("oid_Estoque") +
		    		" GROUP BY  Estoques.oid_Estoque, Estoques.oid_unidade_produto, Estoques.NM_Estoque " ;

	          //System.out.println(sql);

	          resAux = this.executasql.executarConsulta (sql);
	          if (resAux.next ()) {
	        	  vl_nota_fiscal+=resAux.getDouble("vl_quantidade_entrega") * res.getDouble("vl_unitario");

		          System.out.println("ITEM=  Qtde Ent=" + resAux.getString("NM_Estoque") + resAux.getDouble("vl_quantidade_entrega") + "  V.Unit=" +  res.getDouble("vl_unitario") +"  TotIt= "+ resAux.getDouble("vl_quantidade_entrega") * res.getDouble("vl_unitario"));

		          Item_Nota_Fiscal_CompraED itemNF = new Item_Nota_Fiscal_CompraED ();
	              itemNF.setOid_Solicitacao_compra(ed.getOid_Solicitacao_compra ());
	              itemNF.setVl_quantidade (resAux.getDouble("vl_quantidade_entrega"));
	              itemNF.setVL_Produto (res.getDouble("vl_unitario"));
	              itemNF.setOID_Nota_Fiscal (oid_Nota_Fiscal);
	              itemNF.setVL_Liquido (resAux.getDouble("vl_quantidade_entrega") * res.getDouble("vl_unitario"));
	              itemNF.setVL_IPI (0);
	              itemNF.setOID_Produto (res.getString("oid_Estoque"));
	              itemNF.setCD_Imobiliz ("");
	              itemNF.setOID_Unidade_Produto (resAux.getString("oid_unidade_produto"));;
	              if (res.getLong("oid_Ordem_Servico")>0){
	            	  itemNF.setOid_Ordem_Servico(res.getLong("oid_Ordem_Servico"));
	              }
		          item_nota_fiscalBD.inclui (itemNF);

		    	  sql = " UPDATE itens_Solicitacoes_entregas SET oid_Nota_Fiscal='" + oid_Nota_Fiscal + "'" +
					    " WHERE  itens_solicitacoes_entregas.oid_nota_fiscal IS NULL "  +
					    " AND    itens_solicitacoes_entregas.oid_movimento_estoque IS NOT NULL "  +
				    	" AND 	 itens_solicitacoes_entregas.oid_Solicitacao_compra = " + ed.getOid_Solicitacao_compra () +
				    	" AND    itens_solicitacoes_entregas.oid_Estoque =" + res.getLong("oid_Estoque");

		    	  //System.out.println(sql);

		    	  executasql.executarUpdate (sql);

	          }
          }
      }
      if (vl_nota_fiscal>0){
    	  sql =" UPDATE notas_fiscais_transacoes SET oid_Pedido_Compra=" + pedidoED.getOid_Pedido_compra() + ", vl_nota_fiscal=" + vl_nota_fiscal + ", vl_liquido_nota_fiscal=" + vl_nota_fiscal +
    	       " WHERE oid_Nota_Fiscal='" + oid_Nota_Fiscal + "'";

    	  //System.out.println(sql);

    	  executasql.executarUpdate (sql);

    	  nota_fiscalBD.inclui_Parcelamento(oid_Nota_Fiscal, ed.getNr_parcelas(), vl_nota_fiscal);

      }

      this.atualiza_Pedido(ed);



    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      exc.printStackTrace();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return ed;
  }


  public Solicitacao_CompraED atualiza_Estoque_Itens_Recebidos(Solicitacao_CompraED ed)throws Excecoes{

	    try{
	      String sql="";
	      ResultSet res=null;
	      Solicitacao_CompraED pedidoED = this.getByRecord_pedido(ed);
	      Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

	      double vl_movimento=0;

	      sql = " SELECT itens_solicitacoes_entregas.oid_Estoque, " +
	      		"        itens_solicitacoes_entregas.oid_ordem_servico, " +
	      		"        itens_solicitacoes_entregas.vl_quantidade_entrega, " +
	      		"        itens_solicitacoes_compras.vl_unitario, " +
	      		"        itens_solicitacoes_entregas.oid_item_solicitacao_compra, " +
	      		"        itens_solicitacoes_entregas.oid_item_solicitacao_entrega " +
	      		" FROM  itens_solicitacoes_entregas, " +
	      		"       Estoques, " +
	      		"       itens_solicitacoes_compras " +
				" WHERE itens_solicitacoes_entregas.oid_item_solicitacao_compra = itens_solicitacoes_compras.oid_item_solicitacao_compra" +
				" AND   itens_solicitacoes_entregas.oid_Estoque = Estoques.oid_Estoque" +
			    " AND   itens_solicitacoes_compras.oid_Solicitacao_compra = " + pedidoED.getOid_Solicitacao_compra () +
			    " AND   itens_solicitacoes_entregas.oid_movimento_estoque IS NULL "  +
		  		" ORDER BY  itens_solicitacoes_entregas.oid_Estoque ";
	      System.out.println(sql);


	      res = this.executasql.executarConsulta (sql);
	      while(res.next () && pedidoED.getOid_Pedido_compra()>0 && pedidoED.getOid_fornecedor()!=null && pedidoED.getOid_fornecedor().length()>4) {

			  sql =	" SELECT Movimentos_Estoques.oid_Movimento_Estoque " +
		  			" FROM   Movimentos_Estoques " +
		  		    " WHERE  Movimentos_Estoques.oid_Estoque= " + res.getInt("oid_Estoque") +
			    	" AND    Movimentos_Estoques.oid_item_solicitacao_entrega= " + res.getLong("oid_item_solicitacao_entrega");
			  System.out.println("sql ja tem=" + sql );
			  ResultSet resMovEst = this.executasql.executarConsulta(sql);
			  if (resMovEst.next()){

				  sql=" DELETE FROM Movimentos_Estoques " +
				  	  " WHERE  Movimentos_Estoques.oid_Estoque= " + res.getInt("oid_Estoque") +
				      " AND    Movimentos_Estoques.oid_item_solicitacao_entrega= " + res.getLong("oid_item_solicitacao_entrega");
				  System.out.println("eclui o que tem=" + sql );
				  executasql.executarUpdate (sql);

				  sql=" DELETE FROM  Movimentos_Ordens_Servicos " +
	  	  		      " WHERE  Movimentos_Ordens_Servicos.oid_Estoque= " + res.getInt("oid_Estoque") +
	  	  		      " AND    Movimentos_Ordens_Servicos.oid_item_solicitacao_entrega= " + res.getLong("oid_item_solicitacao_entrega");
				  System.out.println("eclui o que tem=" + sql );
				  executasql.executarUpdate (sql);

			  }
			  if (1==1){
		        	  vl_movimento=res.getDouble("vl_quantidade_entrega") * res.getDouble("vl_unitario");

				      System.out.println("vl mov estoque=" + vl_movimento);

		    		  Movimento_EstoqueBean Movimento_Estoque = new Movimento_EstoqueBean();
		    		  Movimento_Estoque.setDM_Movimento ("E");
		    		  Movimento_Estoque.setDT_Movimento (Data.getDataDMY());
		    		  Movimento_Estoque.setNR_Quantidade (res.getDouble("vl_quantidade_entrega"));
		    		  Movimento_Estoque.setOID_Estoque (res.getInt("oid_Estoque"));
		    		  Movimento_Estoque.setVL_Unitario (res.getDouble("vl_unitario"));
		    		  Movimento_Estoque.setNM_Fornecedor("");
		    		  Movimento_Estoque.setOid_Veiculo ("");
		    		  Movimento_Estoque.setOid_Nota_Fiscal("");
		    		  Movimento_Estoque.setOid_Item_Solicitacao_Entrega(res.getLong("oid_item_solicitacao_entrega"));

        	    	  sql = " SELECT NM_Razao_Social " +
		  	        		" FROM  Pessoas " +
		  	    		    " WHERE Pessoas.oid_Pessoa = '" + pedidoED.getOid_fornecedor() + "'";
		  	    	  	ResultSet resP = this.executasql.executarConsulta (sql);
		  	    		if (resP.next ()) {
				    		Movimento_Estoque.setNM_Fornecedor(resP.getString("NM_Razao_Social"));
		  	    		}

					  System.out.println("seque 1=" );


		    		  Movimento_Estoque.insert ();

			    	  sql = " UPDATE itens_Solicitacoes_entregas SET oid_movimento_estoque='" + Movimento_Estoque.getOID() + "'" +
						    " WHERE  itens_solicitacoes_entregas.oid_item_solicitacao_entrega =" + res.getLong("oid_item_solicitacao_entrega");

			    	  System.out.println(sql);

			    	  executasql.executarUpdate (sql);


					  System.out.println("seque oid_ordem_servico=" + res.getInt("oid_ordem_servico") );


		              if (res.getInt("oid_ordem_servico")>0) {
						  sql =	" SELECT Movimentos_Ordens_Servicos.oid_Ordem_Servico " +
	            	  			" FROM   Movimentos_Ordens_Servicos " +
	            	  		    " WHERE  Movimentos_Ordens_Servicos.oid_Ordem_Servico= " + res.getInt("oid_ordem_servico") +
          	  		    		" AND    Movimentos_Ordens_Servicos.oid_Estoque= " + res.getInt("oid_Estoque") +
          	  		    		" AND    Movimentos_Ordens_Servicos.oid_item_solicitacao_entrega= " + res.getLong("oid_item_solicitacao_entrega") +
          	  		    		" AND    Movimentos_Ordens_Servicos.OID_Tipo_Servico= "  + parametro_FixoED.getOID_Tipo_Servico_Requisicao_Estoque()  +
          	  		    		" AND    Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico= '"  + Data.getDataDMY() +"'";
		            	  System.out.println("sql ja tem=" + sql );
						  ResultSet resMovOS = this.executasql.executarConsulta(sql);
		                  if (!resMovOS.next()){

			            	  System.out.println("seque 2=" );
		                  }


		              }
			  }
	      }
	      this.atualiza_Pedido(pedidoED);

	    }
	    catch (Excecoes exc){
	      throw exc;
	    }
	    catch(Exception exc){
	      exc.printStackTrace();
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("erro ao incluir");
	      excecoes.setMetodo("inclui");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }

	    return ed;
	  }




  public ArrayList Lista_Pedidos (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;

    ArrayList list = new ArrayList ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    FormataDataBean formataData = new FormataDataBean ();

    try {

      sql = " SELECT itens_solicitacoes_compras.oid_estoque, solicitacoes_compras.oid_Veiculo, itens_solicitacoes_compras.vl_quantidade,  solicitacoes_compras.oid_solicitacao_compra, itens_solicitacoes_compras.nm_produto, " +
          "          itens_cotacoes_compras.oid_fornecedor, itens_cotacoes_compras.vl_preco, solicitacoes_compras.dt_solicitacao, pedidos_compras.dm_status " +
          " FROM  itens_cotacoes_compras, solicitacoes_compras, pedidos_compras, itens_solicitacoes_compras, cotacoes_compras" +
          " WHERE pedidos_compras.oid_solicitacao_compra = solicitacoes_compras.oid_solicitacao_compra " +
          " AND solicitacoes_compras.oid_solicitacao_compra = itens_solicitacoes_compras.oid_solicitacao_compra " +
          " AND itens_solicitacoes_compras.oid_item_solicitacao_compra = cotacoes_compras.oid_item_solicitacao_compra " +
          " AND cotacoes_compras.oid_cotacao_compra = itens_cotacoes_compras.oid_cotacao_compra " +
          " AND itens_cotacoes_compras.dm_status = 'A' ";

      if (String.valueOf (ed.getOid_Pedido_compra ()) != null &&
          !String.valueOf (ed.getOid_Pedido_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_Pedido_compra ()).equals ("0")) {
        sql += " AND pedidos_compras.oid_pedido_compra = " + ed.getOid_Pedido_compra ();
      }
      if (String.valueOf (ed.getOid_estoque ()) != null &&
          !String.valueOf (ed.getOid_estoque ()).equals ("null") &&
          !String.valueOf (ed.getOid_estoque ()).equals ("0")) {
        sql += " AND itens_solicitacoes_compras.oid_estoque = " + ed.getOid_estoque ();
      }

      if (String.valueOf (ed.getOid_Veiculo ()) != null &&
          !String.valueOf (ed.getOid_Veiculo ()).equals ("null") &&
          !String.valueOf (ed.getOid_Veiculo ()).equals ("")) {
        sql += " AND solicitacoes_compras.oid_Veiculo = '" + ed.getOid_Veiculo () +"'";
      }
      if (String.valueOf (ed.getOid_Conta ()) != null &&
          !String.valueOf (ed.getOid_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOid_Conta ()).equals ("0")) {
        sql += " and solicitacoes_compras.oid_Conta = " + ed.getOid_Conta ();
      }

      if (String.valueOf (ed.getOid_fornecedor ()) != null &&
          !String.valueOf (ed.getOid_fornecedor ()).equals ("null") &&
          !String.valueOf (ed.getOid_fornecedor ()).equals ("0")) {
        sql += " AND itens_cotacoes_compras.oid_fornecedor = '" + ed.getOid_fornecedor () + "'";
      }
      if (String.valueOf (ed.getDt_Solicitacao ()) != null &&
          !String.valueOf (ed.getDt_Solicitacao ()).equals ("null") &&
          !String.valueOf (ed.getDt_Solicitacao ()).equals ("")) {
        sql += " AND solicitacoes_compras.Dt_Solicitacao >= '" + ed.getDt_Solicitacao () + "'";
      }
      if (String.valueOf (ed.getDt_pedido ()) != null &&
          !String.valueOf (ed.getDt_pedido ()).equals ("null") &&
          !String.valueOf (ed.getDt_pedido ()).equals ("")) {
        sql += " AND solicitacoes_compras.Dt_Solicitacao <= '" + ed.getDt_pedido () + "'";
      }
      if (String.valueOf (ed.getDm_status_pedido ()) != null &&
          !String.valueOf (ed.getDm_status_pedido ()).equals ("null") &&
          !String.valueOf (ed.getDm_status_pedido ()).equals ("") &&
          !String.valueOf (ed.getDm_status_pedido ()).equals ("T")) {
        sql += " AND pedidos_compras.Dm_status = '" + ed.getDm_status_pedido () + "'";
      }

      sql += "order by solicitacoes_compras.dt_solicitacao, itens_cotacoes_compras.oid_fornecedor ";
      // System.out.println (sql);
      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_estoque (res.getLong ("oid_estoque"));
        edVolta.setDm_status_pedido (res.getString ("dm_status"));
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("P")) {
          edVolta.setDm_status_pedido ("Pendente");
        }
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("C")) {
          edVolta.setDm_status_pedido ("Cancelado");
        }
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("E")) {
          edVolta.setDm_status_pedido ("Encerrado");
        }
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("A")) {
          edVolta.setDm_status_pedido ("Parcial");
        }
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("N")) {
          edVolta.setDm_status_pedido ("Nota Fiscal");
        }
        edVolta.setOid_fornecedor (res.getString ("Oid_fornecedor"));
        edVolta.setOid_Veiculo (" ");
        if (res.getString ("Oid_Veiculo") != null && res.getString ("Oid_Veiculo").length () > 4) {
          edVolta.setOid_Veiculo (res.getString ("Oid_Veiculo"));
        }
        edVolta.setOid_Solicitacao_compra (res.getLong ("Oid_solicitacao_compra"));
        formataData.setDT_FormataData (res.getString ("dt_solicitacao"));
        edVolta.setDt_Solicitacao (formataData.getDT_FormataData ());
        edVolta.setVl_unitario (res.getDouble ("vl_preco"));
        String produto = res.getString ("nm_produto");
        if (produto != null && !produto.equals ("null")) {
          edVolta.setNm_produto (produto);
        }
        else {
          edVolta.setNm_produto ("");
        }
        ResultSet resTP = null;
        resTP = this.executasql.executarConsulta ("select * from estoques where oid_estoque = " + edVolta.getOid_estoque ());
        while (resTP.next ()) {
          edVolta.setCd_estoque (resTP.getString ("Cd_estoque"));
          edVolta.setNm_estoque (resTP.getString ("Nm_estoque"));
        }
        resTP = this.executasql.executarConsulta ("select * from pessoas where oid_pessoa = '" + edVolta.getOid_fornecedor () + "'");
        while (resTP.next ()) {
          edVolta.setNm_fornecedor (resTP.getString ("NM_Razao_Social"));
        }

        edVolta.setVl_quantidade (res.getDouble ("vl_quantidade"));
        edVolta.setVl_total_item (edVolta.getVl_quantidade () * edVolta.getVl_unitario ());


        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar Pedidos");
      excecoes.setMetodo ("Lista_Pedidos()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList Lista_Pedidos_NF (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;

    ArrayList list = new ArrayList ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    FormataDataBean formataData = new FormataDataBean ();

    try {

      sql = " select solicitacoes_compras.oid_solicitacao_compra, itens_solicitacoes_compras.oid_estoque, " +
          "itens_cotacoes_compras.oid_fornecedor, itens_cotacoes_compras.vl_preco, solicitacoes_compras.dt_solicitacao, pedidos_compras.dm_status " +
          "from itens_cotacoes_compras, solicitacoes_compras, pedidos_compras, itens_solicitacoes_compras, cotacoes_compras" +
          " where pedidos_compras.oid_solicitacao_compra = solicitacoes_compras.oid_solicitacao_compra " +
          "and solicitacoes_compras.oid_solicitacao_compra = itens_solicitacoes_compras.oid_solicitacao_compra " +
          "and itens_solicitacoes_compras.oid_item_solicitacao_compra = cotacoes_compras.oid_item_solicitacao_compra " +
          "and cotacoes_compras.oid_cotacao_compra = itens_cotacoes_compras.oid_cotacao_compra " +
          "and itens_cotacoes_compras.dm_status = 'A' ";

      sql += " and (pedidos_compras.Dm_status = 'P' or pedidos_compras.Dm_status = 'A')";

      if (String.valueOf (ed.getOid_Pedido_compra ()) != null &&
          !String.valueOf (ed.getOid_Pedido_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_Pedido_compra ()).equals ("0")) {
        sql += " and pedidos_compras.oid_pedido_compra = " + ed.getOid_Pedido_compra ();
      }
      if (String.valueOf (ed.getOid_estoque ()) != null &&
          !String.valueOf (ed.getOid_estoque ()).equals ("null") &&
          !String.valueOf (ed.getOid_estoque ()).equals ("0")) {
        sql += " and itens_solicitacoes_compras.oid_estoque = " + ed.getOid_estoque ();
      }
      if (String.valueOf (ed.getOid_fornecedor ()) != null &&
          !String.valueOf (ed.getOid_fornecedor ()).equals ("null") &&
          !String.valueOf (ed.getOid_fornecedor ()).equals ("0")) {
        sql += " and itens_cotacoes_compras.oid_fornecedor = '" + ed.getOid_fornecedor () + "'";
      }
      if (String.valueOf (ed.getDt_Solicitacao ()) != null &&
          !String.valueOf (ed.getDt_Solicitacao ()).equals ("null") &&
          !String.valueOf (ed.getDt_Solicitacao ()).equals ("")) {
        sql += " and solicitacoes_compras.Dt_Solicitacao >= '" + ed.getDt_Solicitacao () + "'";
      }
      if (String.valueOf (ed.getDt_pedido ()) != null &&
          !String.valueOf (ed.getDt_pedido ()).equals ("null") &&
          !String.valueOf (ed.getDt_pedido ()).equals ("")) {
        sql += " and solicitacoes_compras.Dt_Solicitacao <= '" + ed.getDt_pedido () + "'";
      }

      sql += "order by solicitacoes_compras.dt_solicitacao, itens_cotacoes_compras.oid_fornecedor ";
      // System.out.println (sql);
      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_estoque (res.getLong ("oid_estoque"));
        edVolta.setDm_status_pedido (res.getString ("dm_status"));
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("P")) {
          edVolta.setDm_status_pedido ("Pendente");
        }
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("C")) {
          edVolta.setDm_status_pedido ("Cancelado");
        }
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("E")) {
          edVolta.setDm_status_pedido ("Encerrado");
        }
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("A")) {
          edVolta.setDm_status_pedido ("Parcial");
        }
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("N")) {
          edVolta.setDm_status_pedido ("Nota Fiscal");
        }
        edVolta.setOid_fornecedor (res.getString ("Oid_fornecedor"));
        edVolta.setOid_Solicitacao_compra (res.getLong ("Oid_solicitacao_compra"));
        edVolta.setOid_Pedido_compra (res.getLong ("Oid_solicitacao_compra"));
        formataData.setDT_FormataData (res.getString ("dt_solicitacao"));
        edVolta.setDt_Solicitacao (formataData.getDT_FormataData ());
        edVolta.setVl_unitario (res.getDouble ("vl_preco"));
        ResultSet resTP = null;
        resTP = this.executasql.executarConsulta ("select * from estoques where oid_estoque = " + edVolta.getOid_estoque ());
        while (resTP.next ()) {
          edVolta.setCd_estoque (resTP.getString ("Cd_estoque"));
          edVolta.setNm_estoque (resTP.getString ("Nm_estoque"));
        }
        resTP = this.executasql.executarConsulta ("select * from pessoas where oid_pessoa = '" + edVolta.getOid_fornecedor () + "'");
        while (resTP.next ()) {
          edVolta.setNm_fornecedor (resTP.getString ("NM_Razao_Social"));
        }
        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar Pedidos");
      excecoes.setMetodo ("Lista_Pedidos()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public boolean Libera_Autorizacao (double alcada , long oid , long sol) throws Excecoes {

    try {

      String hoje = Data.getDataDMY ();
      String stDia = "01/" + hoje.substring (3 , hoje.length ());

//Verifica valores
//          EXCLUSIVO PELLENZ, @ n�o ir� funcionar se tiver mais de um fornecedor por pedido @
      //pega fornecedor, vl pedido e cond. pgto.
      ///**
       ResultSet resPD = null;
      double preco = 0;

//Este selects pegariam todas as solicita��es autorizadas no m�s
//POREM teria-se que lembrar de somar a Solicitacao em questao, DEIXANDO O select de baixo!!!
//    		String selAux = "select oid_solicitacao_compra from autorizacoes_compras where " +
//    				        " oid_autorizador = " + oid +
//    				        " and dt_stamp >= '"+stDia+"'";
//
//    		String sql = "select * from itens_cotacoes_compras "+
//		      " where itens_solicitacoes_compras.oid_solicitacao_compra = solicitacoes_compras.oid_solicitacao_compra "+
//		      " and cotacoes_compras.oid_item_solicitacao_compra = itens_solicitacoes_compras.oid_item_solicitacao_compra "+
//		      " and itens_cotacoes_compras.oid_cotacao_compra = cotacoes_compras.oid_cotacao_compra "+
//		      " and itens_cotacoes_compras.dm_status = 'A' "+
//		      " and solicitacoes_compras.oid_solicitacao_compra in (" + selAux +")";
//    		resPD = this.executasql.executarConsulta(sql);
//    		while(resPD.next()){
//    		    preco += resPD.getDouble("vl_preco");
//    		}

      String Bsql = "select * from itens_cotacoes_compras, cotacoes_compras, solicitacoes_compras, itens_solicitacoes_compras " +
          " where itens_solicitacoes_compras.oid_solicitacao_compra = solicitacoes_compras.oid_solicitacao_compra " +
          " and cotacoes_compras.oid_item_solicitacao_compra = itens_solicitacoes_compras.oid_item_solicitacao_compra " +
          " and itens_cotacoes_compras.oid_cotacao_compra = cotacoes_compras.oid_cotacao_compra " +
          " and itens_cotacoes_compras.dm_status = 'A' " +
          " and solicitacoes_compras.oid_solicitacao_compra = " + sol;
      resPD = null;
      resPD = this.executasql.executarConsulta (Bsql);
      while (resPD.next ()) {
        preco += resPD.getDouble ("vl_preco");
      }
      //*/
      //FIM do Exclusivo PELLENZ!!! @@@ NAO USAR EM NENHUM OUTRO CLIENTE!!! @@@
      // System.out.println (preco + " alcada >" + alcada);
      if (preco > alcada) {
        return false;
      }
      else {
        return true;
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      exc.printStackTrace ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao autorizar Solicita��o");
      excecoes.setMetodo ("deleta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void relSolicitacao (Solicitacao_CompraED ed , HttpServletResponse resp) throws Excecoes {

    String sql = null;
    String busca = "";
    FormataDataBean dataFormatada = new FormataDataBean ();
    ArrayList list = new ArrayList ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();

    try {

      sql = " select Solicitacoes_compras.*, centros_custos.cd_centro_custo " +
          " from Solicitacoes_compras, centros_custos ";
      sql += " where Solicitacoes_compras.oid_centro_custo = centros_custos.oid_centro_custo ";
      if (String.valueOf (ed.getOid_Solicitacao_compra ()) != null &&
          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("0")) {
        sql += " and Solicitacoes_compras.oid_Solicitacao_compra = " + ed.getOid_Solicitacao_compra ();
      }
      if (String.valueOf (ed.getOid_unidade ()) != null &&
          !String.valueOf (ed.getOid_unidade ()).equals ("null") &&
          !String.valueOf (ed.getOid_unidade ()).equals ("0")) {
        sql += " and Solicitacoes_compras.oid_unidade = " + ed.getOid_unidade ();
      }
      if (String.valueOf (ed.getOid_usuario ()) != null &&
          !String.valueOf (ed.getOid_usuario ()).equals ("null") &&
          !String.valueOf (ed.getOid_usuario ()).equals ("0")) {
        sql += " and Solicitacoes_compras.oid_usuario = " + ed.getOid_usuario ();
      }
      if (String.valueOf (ed.getOid_centro_custo ()) != null &&
          !String.valueOf (ed.getOid_centro_custo ()).equals ("null") &&
          !String.valueOf (ed.getOid_centro_custo ()).equals ("0")) {
        sql += " and Solicitacoes_compras.oid_centro_custo = " + ed.getOid_centro_custo ();
      }
      if (String.valueOf (ed.getOid_autorizador ()) != null &&
          !String.valueOf (ed.getOid_autorizador ()).equals ("null") &&
          !String.valueOf (ed.getOid_autorizador ()).equals ("0")) {
        sql += " and Solicitacoes_compras.oid_autorizador = " + ed.getOid_autorizador ();
      }
      if (String.valueOf (ed.getDt_Solicitacao ()) != null &&
          !String.valueOf (ed.getDt_Solicitacao ()).equals ("null") &&
          !String.valueOf (ed.getDt_Solicitacao ()).equals ("")) {
        sql += " and Solicitacoes_compras.Dt_Solicitacao = '" + ed.getDt_Solicitacao () + "'";
      }
      if (String.valueOf (ed.getDm_status_Solicitacao ()) != null &&
          !String.valueOf (ed.getDm_status_Solicitacao ()).equals ("null") &&
          !String.valueOf (ed.getDm_status_Solicitacao ()).equals ("") &&
          !String.valueOf (ed.getDm_status_Solicitacao ()).equals ("T")) {
        sql += " and Solicitacoes_compras.Dm_status = '" + ed.getDm_status_Solicitacao () + "'";
      }
      sql += " order by Solicitacoes_compras.oid_unidade, Solicitacoes_compras.oid_Solicitacao_compra ";

      // System.out.println (sql);

      ResultSet res = null;
      ResultSet rs = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Solicitacao_CompraED ();
        double preco = 0;
        edVolta.setOid_Solicitacao_compra (res.getLong ("Oid_Solicitacao_compra"));
        edVolta.setOid_unidade (res.getLong ("Oid_unidade"));
        busca = "select cd_unidade from unidades where oid_unidade = " + res.getString ("Oid_unidade");
        rs = this.executasql.executarConsulta (busca);
        while (rs.next ()) {
          edVolta.setCd_unidade (rs.getString ("cd_unidade"));
        }
        edVolta.setOid_usuario (res.getLong ("Oid_usuario"));
        busca = "select nm_usuario from usuarios where oid_usuario = " + res.getString ("Oid_usuario");
        rs = this.executasql.executarConsulta (busca);
        while (rs.next ()) {
          edVolta.setNm_usuario (rs.getString ("nm_usuario"));
        }

        edVolta.setCentro_custo (res.getString ("cd_centro_custo"));

        edVolta.setOid_autorizador (res.getLong ("Oid_autorizador"));

        edVolta.setDt_Solicitacao (res.getString ("Dt_Solicitacao"));
        dataFormatada.setDT_FormataData (edVolta.getDt_Solicitacao ());
        edVolta.setDt_Solicitacao (dataFormatada.getDT_FormataData ());

        edVolta.setDm_status_Solicitacao (res.getString ("Dm_status"));
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("P")) {
          edVolta.setDm_status_Solicitacao ("Pendente");
        }
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("E")) {
          edVolta.setDm_status_Solicitacao ("Enviada");
        }
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("C")) {
          edVolta.setDm_status_Solicitacao ("Cancelada");
        }
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("F")) {
          edVolta.setDm_status_Solicitacao ("Finalizada");
        }
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("A")) {
          edVolta.setDm_status_Solicitacao ("Autorizada");
        }
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("D")) {
          edVolta.setDm_status_Solicitacao ("Cotada");
        }

        edVolta.setOid_Conta (res.getLong ("Oid_Conta"));
        edVolta.setOid_Veiculo (res.getString("Oid_Veiculo"));
        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));


        busca = "select itens_cotacoes_compras.vl_preco, itens_cotacoes_compras.Oid_fornecedor, itens_cotacoes_compras.nm_condicao_pgto " +
            " from itens_cotacoes_compras, cotacoes_compras, solicitacoes_compras" +
            " where itens_cotacoes_compras.oid_cotacao_compra =  cotacoes_compras.oid_cotacao_compra" +
            " and cotacoes_compras.oid_solicitacao_compra = solicitacoes_compras.oid_solicitacao_compra" +
            " and itens_cotacoes_compras.dm_status = 'A' " +
            " and solicitacoes_compras.oid_solicitacao_compra =" + edVolta.getOid_Solicitacao_compra ();
        rs = this.executasql.executarConsulta (busca);
        int cont = 0;
        while (rs.next ()) {
          preco += rs.getDouble ("vl_preco");
          edVolta.setNm_cond_pgto (rs.getString ("nm_condicao_pgto"));
          edVolta.setOid_fornecedor (rs.getString ("Oid_fornecedor"));
        }
        edVolta.setNm_fornecedor (PessoaBean.getByOID (edVolta.getOid_fornecedor ()).getNM_Razao_Social ());

        edVolta.setVl_total (preco);

        list.add (edVolta);
      }

      Solicitacao_CompraRL rl = new Solicitacao_CompraRL ();
      rl.relSolicitacao (list , resp , ed);

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "Lista(Solicitacao_CompraED ed)");
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "Lista(Solicitacao_CompraED ed)");
    }

  }

  public void relPedido_Compra (Solicitacao_CompraED ed , HttpServletResponse resp) throws Excecoes {

    String sql = null;
    String busca = "";
    FormataDataBean dataFormatada = new FormataDataBean ();
    ArrayList list = new ArrayList ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    boolean tem_nf = false;

    try {

      sql = " select distinct(solicitacoes_compras.*), " +
          " pedidos_compras.dt_pedido_compra, pedidos_compras.dm_status as dm_status_pedido, pedidos_compras.oid_pedido_compra, " +
          " centros_custos.cd_centro_custo, itens_cotacoes_compras.Oid_fornecedor " +
          " FROM  cotacoes_compras, itens_cotacoes_compras, solicitacoes_compras, pedidos_compras, itens_solicitacoes_compras, centros_custos " +
          " WHERE pedidos_compras.oid_solicitacao_compra = solicitacoes_compras.oid_solicitacao_compra " +
          " AND solicitacoes_compras.oid_solicitacao_compra = itens_solicitacoes_compras.oid_solicitacao_compra " +
          " AND itens_solicitacoes_compras.oid_item_solicitacao_compra = cotacoes_compras.oid_item_solicitacao_compra " +
          " AND cotacoes_compras.oid_cotacao_compra = itens_cotacoes_compras.oid_cotacao_compra " +
          " AND Solicitacoes_compras.oid_centro_custo = centros_custos.oid_centro_custo " +
          " AND itens_cotacoes_compras.dm_status = 'A' " +
          " AND pedidos_compras.dm_status != 'C' " +
          " AND solicitacoes_compras.dm_status != 'C' ";

      if (String.valueOf (ed.getOid_Pedido_compra ()) != null &&
          !String.valueOf (ed.getOid_Pedido_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_Pedido_compra ()).equals ("0")) {
        sql += " and pedidos_compras.oid_pedido_compra = " + ed.getOid_Pedido_compra ();
      }

      if (String.valueOf (ed.getDt_Solicitacao ()) != null &&
          !String.valueOf (ed.getDt_Solicitacao ()).equals ("null") &&
          !String.valueOf (ed.getDt_Solicitacao ()).equals ("")) {
        sql += " and pedidos_compras.dt_pedido_compra >= '" + ed.getDt_Solicitacao () + "'";
      }
      if (String.valueOf (ed.getDt_pedido ()) != null &&
          !String.valueOf (ed.getDt_pedido ()).equals ("null") &&
          !String.valueOf (ed.getDt_pedido ()).equals ("")) {
        sql += " and pedidos_compras.dt_pedido_compra <= '" + ed.getDt_pedido () + "'";
      }

      if (String.valueOf (ed.getDm_status_pedido ()) != null &&
          !String.valueOf (ed.getDm_status_pedido ()).equals ("null") &&
          !String.valueOf (ed.getDm_status_pedido ()).equals ("") &&
          !String.valueOf (ed.getDm_status_pedido ()).equals ("T")) {
        sql += " and pedidos_compras.Dm_status = '" + ed.getDm_status_pedido () + "'";
      }

      if (String.valueOf (ed.getOid_Veiculo ()) != null &&
          !String.valueOf (ed.getOid_Veiculo ()).equals ("null") &&
          !String.valueOf (ed.getOid_Veiculo ()).equals ("")) {
        sql += " AND solicitacoes_compras.oid_Veiculo = '" + ed.getOid_Veiculo () +"'";
      }
      if (String.valueOf (ed.getOid_Conta ()) != null &&
          !String.valueOf (ed.getOid_Conta ()).equals ("null") &&
          !String.valueOf (ed.getOid_Conta ()).equals ("0")) {
        sql += " and solicitacoes_compras.oid_Conta = " + ed.getOid_Conta ();
      }

      if (String.valueOf (ed.getOid_fornecedor ()) != null &&
          !String.valueOf (ed.getOid_fornecedor ()).equals ("null") &&
          !String.valueOf (ed.getOid_fornecedor ()).equals ("0")) {
        sql += " AND itens_cotacoes_compras.oid_fornecedor = '" + ed.getOid_fornecedor () + "'";
      }
      if (String.valueOf (ed.getOid_centro_custo()) != null &&
          !String.valueOf (ed.getOid_centro_custo()).equals ("null") &&
          !String.valueOf (ed.getOid_centro_custo()).equals ("0")) {
        sql += " and solicitacoes_compras.oid_Centro_Custo = " + ed.getOid_centro_custo();
      }

      sql += "order by  Solicitacoes_compras.oid_unidade, pedidos_compras.dt_pedido_compra ";

      // System.out.println (sql);

      ResultSet res = null;
      ResultSet rs = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        tem_nf = false;
        edVolta = new Solicitacao_CompraED ();
        double preco = 0;
        edVolta.setOid_Solicitacao_compra (res.getLong ("Oid_Solicitacao_compra"));
        edVolta.setOid_Pedido_compra (res.getLong ("Oid_pedido_compra"));
        edVolta.setOid_unidade (res.getLong ("Oid_unidade"));
        busca = "select cd_unidade from unidades where oid_unidade = " + res.getString ("Oid_unidade");
        rs = this.executasql.executarConsulta (busca);
        while (rs.next ()) {
          edVolta.setCd_unidade (rs.getString ("cd_unidade"));
        }
        edVolta.setOid_usuario (res.getLong ("Oid_usuario"));
        busca = "select nm_usuario from usuarios where oid_usuario = " + res.getString ("Oid_usuario");
        rs = this.executasql.executarConsulta (busca);
        while (rs.next ()) {
          edVolta.setNm_usuario (rs.getString ("nm_usuario"));
        }
        busca = "select nm_usuario from usuarios where oid_usuario = " + res.getString ("oid_autorizador");
        rs = this.executasql.executarConsulta (busca);
        while (rs.next ()) {
          ed.setNm_autorizador(rs.getString ("nm_usuario"));
        }

        edVolta.setOid_Veiculo(JavaUtil.doValida(res.getString("oid_Veiculo")) ? res.getString("oid_Veiculo") : "");
        edVolta.setOid_Conta(res.getLong("oid_conta"));
        busca = "select cd_conta from contas where oid_conta = " + edVolta.getOid_Conta();
        rs = this.executasql.executarConsulta (busca);
        while (rs.next ()) {
        	edVolta.setConta(rs.getString("cd_conta"));
        }
        edVolta.setOid_fornecedor (res.getString ("Oid_fornecedor"));

        edVolta.setCentro_custo (res.getString ("cd_centro_custo"));

        edVolta.setOid_autorizador (res.getLong ("Oid_autorizador"));

        edVolta.setDt_pedido (res.getString ("Dt_pedido_compra"));
        dataFormatada.setDT_FormataData (edVolta.getDt_pedido ());
        edVolta.setDt_pedido (dataFormatada.getDT_FormataData ());

        edVolta.setDm_status_pedido (res.getString ("Dm_status_pedido"));
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("P")) {
          edVolta.setDm_status_Solicitacao ("Pendente");
        }
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("E")) {
          edVolta.setDm_status_Solicitacao ("Enviada");
        }
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("C")) {
          edVolta.setDm_status_Solicitacao ("Cancelada");
        }
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("F")) {
          edVolta.setDm_status_Solicitacao ("Finalizada");
        }
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("A")) {
          edVolta.setDm_status_Solicitacao ("Autorizada");
        }
        if (edVolta.getDm_status_pedido () != null &&
            edVolta.getDm_status_pedido ().equals ("D")) {
          edVolta.setDm_status_Solicitacao ("Cotada");
        }

        busca = "select itens_cotacoes_compras.vl_preco, itens_cotacoes_compras.Oid_fornecedor, itens_cotacoes_compras.nm_condicao_pgto " +
            " from itens_cotacoes_compras, cotacoes_compras, solicitacoes_compras" +
            " where itens_cotacoes_compras.oid_cotacao_compra =  cotacoes_compras.oid_cotacao_compra" +
            " and cotacoes_compras.oid_solicitacao_compra = solicitacoes_compras.oid_solicitacao_compra" +
            " and itens_cotacoes_compras.dm_status = 'A' " +
            " and solicitacoes_compras.oid_solicitacao_compra =" + edVolta.getOid_Solicitacao_compra ();
        rs = this.executasql.executarConsulta (busca);
        int cont = 0;
        while (rs.next ()) {
          preco += rs.getDouble ("vl_preco");
          edVolta.setNm_cond_pgto (rs.getString ("nm_condicao_pgto"));
//          edVolta.setOid_fornecedor (rs.getString ("Oid_fornecedor"));
        }
        edVolta.setNm_fornecedor (PessoaBean.getByOID (edVolta.getOid_fornecedor ()).getNM_Razao_Social ());

//        edVolta.setVl_total(preco);
        edVolta.setVl_total(new Cotacao_CompraBD(this.executasql).getValorTotalItens(res.getLong("Oid_Solicitacao_compra"), 0));

        busca = "select nr_nota_fiscal from notas_fiscais_transacoes, pedidos_compras_notas_fiscais " +
            " WHERE notas_fiscais_transacoes.oid_nota_fiscal = pedidos_compras_notas_fiscais.oid_nota_fiscal " +
            " AND pedidos_compras_notas_fiscais.oid_Pedido_compra = " + edVolta.getOid_Pedido_compra ();
        rs = this.executasql.executarConsulta (busca);
        while (rs.next ()) {
          edVolta.setNr_nota_fiscal (rs.getString ("nr_nota_fiscal"));
          list.add (edVolta);
          tem_nf = true;
        }
        if (!tem_nf) {
          list.add (edVolta);
        }
      }

      Solicitacao_CompraRL rl = new Solicitacao_CompraRL ();
      rl.relPedido_Compra (list , resp , ed);

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "Lista(Solicitacao_CompraED ed)");
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "Lista(Solicitacao_CompraED ed)");
    }

  }

  public void relPrazoMedio (Solicitacao_CompraED ed , HttpServletResponse resp) throws Excecoes {

    String sql = null;
    String busca = "";
    FormataDataBean dataFormatada = new FormataDataBean ();
    ArrayList list = new ArrayList ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();
    boolean tem_nf = false;

    try {

      sql = " select solicitacoes_compras.oid_solicitacao_compra, solicitacoes_compras.dt_solicitacao, autorizacoes_compras.dt_stamp, " +
          "pedidos_compras.dt_pedido_compra, notas_fiscais_transacoes.dt_entrada, notas_fiscais_transacoes.dt_emissao, " +
          "notas_fiscais_transacoes.nr_nota_fiscal, solicitacoes_compras.oid_unidade " +
          "from solicitacoes_compras, pedidos_compras, notas_fiscais_transacoes, pedidos_compras_notas_fiscais " +
          " where pedidos_compras.oid_solicitacao_compra = solicitacoes_compras.oid_solicitacao_compra " +
          " AND pedidos_compras.oid_pedido_compra = pedidos_compras_notas_fiscais.oid_pedido_compra " +
          " AND notas_fiscais_transacoes.oid_nota_fiscal = pedidos_compras_notas_fiscais.oid_nota_fiscal " +
          " AND autorizacoes_compras.oid_solicitacao_compra = solicitacoes_compras.oid_solicitacao_compra ";

      if (String.valueOf (ed.getOid_Solicitacao_compra ()) != null &&
          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_Solicitacao_compra ()).equals ("0")) {
        sql += " and Solicitacoes_compras.oid_solicitacao_compra = " + ed.getOid_Solicitacao_compra ();
      }

      if (String.valueOf (ed.getDt_Solicitacao ()) != null &&
          !String.valueOf (ed.getDt_Solicitacao ()).equals ("null") &&
          !String.valueOf (ed.getDt_Solicitacao ()).equals ("")) {
        sql += " and Solicitacoes_compras.Dt_Solicitacao >= '" + ed.getDt_Solicitacao () + "'";
      }
      if (String.valueOf (ed.getDt_pedido ()) != null &&
          !String.valueOf (ed.getDt_pedido ()).equals ("null") &&
          !String.valueOf (ed.getDt_pedido ()).equals ("")) {
        sql += " and Solicitacoes_compras.Dt_Solicitacao <= '" + ed.getDt_pedido () + "'";
      }

      if (String.valueOf (ed.getDm_status_pedido ()) != null &&
          !String.valueOf (ed.getDm_status_pedido ()).equals ("null") &&
          !String.valueOf (ed.getDm_status_pedido ()).equals ("") &&
          !String.valueOf (ed.getDm_status_pedido ()).equals ("T")) {
        sql += " and pedidos_compras.Dm_status = '" + ed.getDm_status_pedido () + "'";
      }

      if (String.valueOf (ed.getOid_centro_custo ()) != null &&
          !String.valueOf (ed.getOid_centro_custo ()).equals ("null") &&
          !String.valueOf (ed.getOid_centro_custo ()).equals ("0")) {
        sql += " and Solicitacoes_compras.oid_centro_custo = " + ed.getOid_centro_custo ();
      }
      if (String.valueOf (ed.getOid_autorizador ()) != null &&
          !String.valueOf (ed.getOid_autorizador ()).equals ("null") &&
          !String.valueOf (ed.getOid_autorizador ()).equals ("0")) {
        sql += " and Solicitacoes_compras.oid_autorizador = " + ed.getOid_autorizador ();
      }

      if (String.valueOf (ed.getOid_unidade ()) != null &&
          !String.valueOf (ed.getOid_unidade ()).equals ("null") &&
          !String.valueOf (ed.getOid_unidade ()).equals ("0")) {
        sql += " and Solicitacoes_compras.oid_unidade = " + ed.getOid_unidade ();
      }

      sql += " order by  Solicitacoes_compras.oid_unidade, pedidos_compras.oid_solicitacao_compra ";

      // System.out.println (sql);

      ResultSet res = null;
      ResultSet rs = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        tem_nf = false;
        edVolta = new Solicitacao_CompraED ();
        double preco = 0;
        edVolta.setOid_Solicitacao_compra (res.getLong ("Oid_Solicitacao_compra"));
        edVolta.setOid_unidade (res.getLong ("Oid_unidade"));
        busca = "select cd_unidade from unidades where oid_unidade = " + res.getString ("Oid_unidade");
        rs = this.executasql.executarConsulta (busca);
        while (rs.next ()) {
          edVolta.setCd_unidade (rs.getString ("cd_unidade"));
        }

        edVolta.setDt_Solicitacao (res.getString ("Dt_Solicitacao"));
        dataFormatada.setDT_FormataData (edVolta.getDt_Solicitacao ());
        edVolta.setDt_Solicitacao (dataFormatada.getDT_FormataData ());
        Calendar cal1 = Data.stringToCalendar (edVolta.getDt_Solicitacao () , "dd/MM/yyyy");

        edVolta.setDt_autorizacao (res.getString ("Dt_stamp"));
        dataFormatada.setDT_FormataData (edVolta.getDt_autorizacao ());
        edVolta.setDt_autorizacao (dataFormatada.getDT_FormataData ());
        Calendar cal2 = Data.stringToCalendar (edVolta.getDt_autorizacao () , "dd/MM/yyyy");

        edVolta.setDt_emissao (res.getString ("Dt_emissao"));
        dataFormatada.setDT_FormataData (edVolta.getDt_emissao ());
        edVolta.setDt_emissao (dataFormatada.getDT_FormataData ());
        Calendar cal3 = Data.stringToCalendar (edVolta.getDt_emissao () , "dd/MM/yyyy");

        edVolta.setDt_entrada (res.getString ("Dt_entrada"));
        dataFormatada.setDT_FormataData (edVolta.getDt_entrada ());
        edVolta.setDt_entrada (dataFormatada.getDT_FormataData ());
        Calendar cal4 = Data.stringToCalendar (edVolta.getDt_entrada () , "dd/MM/yyyy");

        edVolta.setDiasA_B (Data.diferencaDias (cal1 , cal2));
        edVolta.setDiasA_C (Data.diferencaDias (cal1 , cal3));
        edVolta.setDiasB_C (Data.diferencaDias (cal2 , cal3));
        edVolta.setDiasB_D (Data.diferencaDias (cal2 , cal4));
        edVolta.setDiasTotal (Data.diferencaDias (cal1 , cal4));

        edVolta.setNr_nota_fiscal (res.getString ("nr_nota_fiscal"));

        list.add (edVolta);

      }
      Solicitacao_CompraRL rl = new Solicitacao_CompraRL ();
      rl.relPrazoMedio (list , resp , ed);

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "Lista(Solicitacao_CompraED ed)");
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "Lista(Solicitacao_CompraED ed)");
    }
  }

  public Solicitacao_CompraED getMov_ServicoToNF (Solicitacao_CompraED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();
    Solicitacao_CompraED edVolta = new Solicitacao_CompraED ();

    try {

      sql = " select * from movimentos_ordens_servicos ";
      sql += " where 1 = 1 ";
      if (String.valueOf (ed.getOid_fornecedor ()) != null &&
          !String.valueOf (ed.getOid_fornecedor ()).equals ("null") &&
          !String.valueOf (ed.getOid_fornecedor ()).equals ("0")) {
        sql += " and oid_pessoa = '" + ed.getOid_fornecedor () + "'";
      }
      if (String.valueOf (ed.getNr_documento ()) != null &&
          !String.valueOf (ed.getNr_documento ()).equals ("null") &&
          !String.valueOf (ed.getNr_documento ()).equals ("0")) {
        sql += " and nr_documento = " + ed.getNr_documento ();
      }

      if (String.valueOf (ed.getOid_nota_fiscal ()) != null &&
          !String.valueOf (ed.getOid_nota_fiscal ()).equals ("null") &&
          !String.valueOf (ed.getOid_nota_fiscal ()).equals ("0")) {
        sql += " and oid_nota_fiscal = '" + ed.getOid_nota_fiscal () + "'";
      }
      if (String.valueOf (ed.getOid_movimento_ordem_servico ()) != null &&
          !String.valueOf (ed.getOid_movimento_ordem_servico ()).equals ("null") &&
          !String.valueOf (ed.getOid_movimento_ordem_servico ()).equals ("0")) {
        sql += " and Oid_movimento_ordem_servico = " + ed.getOid_movimento_ordem_servico ();
      }

      ResultSet res = null;
      // System.out.println (sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Solicitacao_CompraED ();
        edVolta.setOid_movimento_ordem_servico (res.getLong ("Oid_movimento_ordem_servico"));
        edVolta.setDt_servico (res.getString ("Dt_movimento_ordem_servico"));
        dataFormatada.setDT_FormataData (edVolta.getDt_servico ());
        edVolta.setDt_servico (dataFormatada.getDT_FormataData ());
        //edVolta.setVl_servico(res.getDouble("vl_movimento"));
        edVolta.setVl_servico (res.getDouble ("vl_DOCUMENTO"));
        edVolta.setNr_quantidade (res.getDouble ("nr_quantidade"));
        edVolta.setNr_documento (res.getString ("nr_documento"));

        edVolta.setOid_fornecedor (res.getString ("oid_pessoa"));

        edVolta.setDm_status_Solicitacao (res.getString ("Dm_faturado_pago"));
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("P")) {
          edVolta.setDm_status_Solicitacao ("Pago");
        }
        if (edVolta.getDm_status_Solicitacao () != null &&
            edVolta.getDm_status_Solicitacao ().equals ("F")) {
          edVolta.setDm_status_Solicitacao ("Faturado");
        }

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

    return edVolta;
  }

  private String consulta_Situacao (String dm_Status_Solicitacao) throws Excecoes {

     String nm_Status="";
        if ("P".equals(dm_Status_Solicitacao))
          nm_Status="Pendente";
        if ("E".equals(dm_Status_Solicitacao))
          nm_Status="Solicitado Compra/Cotacao";
        if ("C".equals(dm_Status_Solicitacao))
          nm_Status="Cancelada";
        if ("F".equals(dm_Status_Solicitacao))
          nm_Status="Finalizada";
        if ("A".equals(dm_Status_Solicitacao))
          nm_Status="Autorizada";
        if ("D".equals(dm_Status_Solicitacao))
          nm_Status="Cotada";

    return nm_Status;
  }


}
