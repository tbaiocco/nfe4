package com.master.bd;

import java.io.LineNumberReader;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.EstoqueED;
import com.master.ed.Item_Nota_Fiscal_CompraED;
import com.master.rn.EstoqueRN;
import com.master.root.FormataDataBean;
import com.master.root.Movimento_EstoqueBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.ManipulaArquivo;
import com.master.util.ManipulaString;
import com.master.util.bd.ExecutaSQL;

public class EstoqueBD {

  private ExecutaSQL executasql;

  public EstoqueBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public EstoqueED inclui (EstoqueED ed) throws Excecoes {

    String sql = null;
    int valOid = 0;
    EstoqueED EstoqueED = new EstoqueED ();
    EstoqueRN EstoqueRN = new EstoqueRN ();

    try {

      ResultSet rs = executasql.executarConsulta ("SELECT MAX(OID_estoque) as result FROM estoques");
      while (rs.next ()) {
        valOid = rs.getInt ("result");
      }

      // System.out.println ("INCLUIR EST 1");

      EstoqueED = this.getCodigo_Item (ed);

      if (EstoqueED.getCd_estoque () != ed.getCd_estoque ()) {
        ed.setCd_estoque (EstoqueED.getCd_estoque ());
      }

      EstoqueED.setNr_proximo_item (EstoqueED.getNr_proximo_item ());
      EstoqueRN.update_Proximo_Item (EstoqueED.getNr_proximo_item () , ed.getOid_grupo_estoque () , ed.getOid_sub_grupo_estoque ());

      sql = "INSERT INTO Estoques (" +
          " oid_estoque," +
          " nm_estoque," +
          " nm_referencia_fabrica," +
          " nm_referencia_fabrica2," +
          " nm_referencia_fabrica3," +
          " nm_referencia_fabrica4," +
          " nm_referencia_fabrica5," +
          " nm_referencia_fabrica6," +
          " nm_aplicacao," +
          " vl_custo," +
          " dt_stamp," +
          " usuario_stamp," +
          " dm_stamp," +
          " oid_unidade_produto," +
          " oid_sub_grupo_estoque," +
          " oid_grupo_estoque," +
          " vl_custo_minimo," +
          " vl_custo_maximo," +
          " vl_estoque_minimo," +
          " vl_estoque_maximo," +
          " dm_status," +
          " dm_tipo_produto," +
          " nm_classificacao_fiscal," +
          " dm_negativo," +
          " nr_leadtime," +
          " nr_leadtime_maximo," +
          " cd_estoque," +
          " CD_Deposito," +
          " NM_Rua," +
          " NR_Endereco," +
          " NR_Apartamento," +
          " oid_produto" +
          ")";
      sql += " values ( ";
      sql += ++valOid + ",'" +
          ed.getNm_estoque () + "','" +
          ed.getNm_referencia () + "','" +
          ed.getNm_referencia2 () + "','" +
          ed.getNm_referencia3 () + "','" +
          ed.getNm_referencia4 () + "','" +
          ed.getNm_referencia5 () + "','" +
          ed.getNm_referencia6 () + "','" +
          ed.getNm_aplicacao () + "'," +
          ed.getVl_custo () + ",'" +
          ed.getDt_stamp () + "','','S'," +
          ed.getOid_unidade_produto () + "," +
          ed.getOid_sub_grupo_estoque () + "," +
          ed.getOid_grupo_estoque () + "," +
          ed.getVl_custo_minimo () + "," +
          ed.getVl_custo_maximo () + "," +
          ed.getVl_estoque_minimo () + "," +
          ed.getVl_estoque_maximo () + ",'" +
          ed.getDm_status () + "','" +
          ed.getDm_tipo_produto () + "','" +
          ed.getNm_classificacao_fiscal () + "','" +
          ed.getDm_negativo () + "'," +
          ed.getNr_leadtime () + "," +
          ed.getNr_leadtime_maximo () + ",'" +
          ed.getCd_estoque () + "','" +
          ed.getCD_Deposito () + "','" +
          ed.getNM_Rua () + "','" +
          ed.getNR_Endereco () + "','" +
          ed.getNR_Apartamento () + "'," +
          ed.getOid_produto () + ")";

      System.out.println (sql);

      int i = executasql.executarUpdate (sql);
      EstoqueED.setOid_estoque (valOid);
    }

    catch (Exception exc) {
    	exc.printStackTrace();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Produto j� registrado!");
      excecoes.setMetodo ("inclui()");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return EstoqueED;

  }

  public EstoqueED incluiContagem (EstoqueED ed) throws Excecoes {

	    String sql = null;
	    String valOid = String.valueOf (System.currentTimeMillis ()).toString ();
	    EstoqueED EstoqueED = new EstoqueED ();

	    try {

	      EstoqueED = this.getByRecord(ed);

	      sql = "INSERT INTO Contagens_Estoques (" +
	      	  " OID_Contagem_estoque," +
	          " oid_estoque," +
	          " DT_Contagem," +
	          " HR_Contagem," +
	          " oid_Usuario," +
	          " nr_quantidade_estoque," +
	          " nr_quantidade_contagem " +
	          ")";
	      sql += " values ( ";
	      sql +=  valOid + ",'" +
          	  ed.getOid_estoque() + "','" +
	          Data.getDataDMY() + "','" +
	          Data.getHoraHM() + "','" +
	          ed.getOid_Usuario() + "'," +
	          EstoqueED.getVl_estoque() + "," +

	          ed.getNR_Quantidade_Contagem() + ")";

	       System.out.println (sql);

	      int i = executasql.executarUpdate (sql);

	      sql =" UPDATE Estoques SET " +
	      				" OID_Contagem_estoque='" + valOid  + "'" +
	      				" ,DT_Ultima_Contagem='" + Data.getDataDMY() + "'" +
	      				" ,cd_deposito='" + ed.getCD_Deposito () +  "'" +
	      				" ,nm_rua='" + ed.getNM_Rua () + "'" +
	      				" ,nr_endereco='" + ed.getNR_Endereco () + "'" +
	      				" ,nr_apartamento='" + ed.getNR_Apartamento () + "'" +
	      				" ,NR_Divergencia_Ultima_Contagem=" + (EstoqueED.getVl_estoque()-ed.getNR_Quantidade_Contagem()) +
	      				" WHERE oid_Estoque='" + ed.getOid_estoque() + "'";

	       System.out.println (sql);

	      executasql.executarUpdate (sql);

	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao Alterar!");
	      excecoes.setMetodo ("incluiContagem()");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	    return EstoqueED;

	  }

  public void altera (EstoqueED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " UPDATE Estoques set " +
          " oid_produto=" + ed.getOid_produto () +
          ", nm_estoque='" + ed.getNm_estoque () +
          "', nm_referencia_fabrica='" + ed.getNm_referencia () +
          "', nm_referencia_fabrica2='" + ed.getNm_referencia2 () +
          "', nm_referencia_fabrica3='" + ed.getNm_referencia3 () +
          "', nm_referencia_fabrica4='" + ed.getNm_referencia4 () +
          "', nm_referencia_fabrica5='" + ed.getNm_referencia5 () +
          "', nm_referencia_fabrica6='" + ed.getNm_referencia6 () +
          "', nm_aplicacao='" + ed.getNm_aplicacao () +
          "', vl_custo=" + ed.getVl_custo () +
          ", dt_stamp='" + ed.getDt_stamp () +
          "', oid_unidade_produto=" + ed.getOid_unidade_produto () +
          ", oid_sub_grupo_estoque=" + ed.getOid_sub_grupo_estoque () +
          ", oid_grupo_estoque=" + ed.getOid_grupo_estoque () +
          ", vl_custo_minimo=" + ed.getVl_custo_minimo () +
          ", vl_custo_maximo=" + ed.getVl_custo_maximo () +
          ", vl_estoque_minimo=" + ed.getVl_estoque_minimo () +
          ", nr_quantidade=" + ed.getVl_estoque () +
          ", vl_estoque_maximo=" + ed.getVl_estoque_maximo () +
          ", dm_status='" + ed.getDm_status () +
          "', dm_tipo_produto='" + ed.getDm_tipo_produto () +
          "', nm_classificacao_fiscal='" + ed.getNm_classificacao_fiscal () +
          "', dm_negativo='" + ed.getDm_negativo () +
          "', cd_deposito='" + ed.getCD_Deposito () +
          "', nm_rua='" + ed.getNM_Rua () +
          "', nr_endereco='" + ed.getNR_Endereco () +
          "', nr_apartamento='" + ed.getNR_Apartamento () +
          "', nr_leadtime=" + ed.getNr_leadtime () +
          ", nr_leadtime_maximo=" + ed.getNr_leadtime_maximo () +
          ", cd_estoque='" + ed.getCd_estoque () + "'";
      sql += " WHERE oid_estoque = " + ed.getOid_estoque ();

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar dados do Produto");
      excecoes.setMetodo ("altera()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void ativa_Desativa (EstoqueED ed) throws Excecoes {

	    String sql = null;

	    try {


	      EstoqueED edVolta = new EstoqueED ();
	      edVolta = this.getByRecord(ed);

	      if ("A".equals(edVolta.getDm_status())) {
		      sql = " UPDATE Estoques SET " +
		          "   dm_status='I' " +
		          " WHERE oid_estoque = " + ed.getOid_estoque ();
	      }
	      else {
		      sql = " UPDATE Estoques SET " +
	          "   dm_status='A' " +
	          " WHERE oid_estoque = " + ed.getOid_estoque ();
	      }
	      int i = executasql.executarUpdate (sql);
	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao alterar dados do Produto");
	      excecoes.setMetodo ("ativa_Desativa()");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void deleta (EstoqueED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "delete from estoques WHERE Oid_Estoque = ";
      sql += "" + ed.getOid_estoque () + "";

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao deletar Produto");
      excecoes.setMetodo ("deleta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public EstoqueED getByRecord (EstoqueED ed) throws Excecoes {

    String sql = null;
    EstoqueED edVolta = new EstoqueED ();

    try {

      sql = " SELECT * FROM estoques";
      sql += " WHERE 1 = 1 ";
      if (String.valueOf (ed.getOid_estoque ()) != null && !String.valueOf (ed.getOid_estoque ()).equals ("null") && !String.valueOf (ed.getOid_estoque ()).equals ("0")) {
        sql += " AND oid_estoque = " + ed.getOid_estoque ();
      }else {
	      if (ed.getCd_estoque () != null &&
	    	  !ed.getCd_estoque ().equals ("null") &&
	    	  !ed.getCd_estoque ().equals ("")) {
	        sql += " AND cd_estoque = '" + ed.getCd_estoque () + "'";
	      }
	      if (ed.getNm_estoque () != null &&
	    	  !ed.getNm_estoque ().equals ("null") &&
	    	  !ed.getNm_estoque ().equals ("")) {
		    sql += " AND Nm_estoque = '" + ed.getNm_estoque () + "'";
		  }
      }
      // System.out.println(sql);

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new EstoqueED ();
        edVolta.setOid_estoque (res.getLong ("oid_estoque"));
        edVolta.setCd_estoque (res.getString ("Cd_estoque"));
        edVolta.setNm_estoque (res.getString ("Nm_estoque"));
    	edVolta.setNm_referencia (" ");
        if (res.getString ("Nm_referencia_fabrica") != null && !res.getString ("Nm_referencia_fabrica").equals("null")) {
        	edVolta.setNm_referencia (res.getString ("Nm_referencia_fabrica"));
        }
        edVolta.setNm_referencia2 (JavaUtil.getValueDef(res.getString ("Nm_referencia_fabrica2"),""));
        edVolta.setNm_referencia3 (JavaUtil.getValueDef(res.getString ("Nm_referencia_fabrica3"),""));
        edVolta.setNm_referencia4 (JavaUtil.getValueDef(res.getString ("Nm_referencia_fabrica4"),""));
        edVolta.setNm_referencia5 (JavaUtil.getValueDef(res.getString ("Nm_referencia_fabrica5"),""));
        edVolta.setNm_referencia6 (JavaUtil.getValueDef(res.getString ("Nm_referencia_fabrica6"),""));
        
        edVolta.setNm_aplicacao (res.getString ("Nm_aplicacao"));
        edVolta.setVl_custo (res.getDouble ("Vl_custo"));
        edVolta.setDt_stamp (res.getString ("Dt_stamp"));
        edVolta.setOid_unidade_produto (res.getLong ("Oid_unidade_produto"));
        edVolta.setOid_sub_grupo_estoque (res.getInt ("Oid_sub_grupo_estoque"));
        edVolta.setOid_grupo_estoque (res.getInt ("Oid_grupo_estoque"));
        edVolta.setVl_custo_minimo (res.getDouble ("Vl_custo_minimo"));
        edVolta.setVl_custo_maximo (res.getDouble ("Vl_custo_maximo"));
        edVolta.setVl_estoque_minimo (res.getDouble ("Vl_estoque_minimo"));
        edVolta.setVl_estoque_maximo (res.getDouble ("Vl_estoque_maximo"));
        edVolta.setVl_medio (res.getDouble ("Vl_medio"));
        edVolta.setDm_status (res.getString ("Dm_status"));
        edVolta.setDm_tipo_produto (res.getString ("Dm_tipo_produto"));
        edVolta.setNm_classificacao_fiscal (res.getString ("Nm_classificacao_fiscal"));
        edVolta.setDm_negativo (res.getString ("Dm_negativo"));
        edVolta.setNr_leadtime (res.getLong ("Nr_leadtime"));
        edVolta.setNr_leadtime_maximo (res.getLong ("Nr_leadtime_maximo"));
        edVolta.setOid_produto (res.getLong ("Oid_produto"));

        edVolta.setNR_Quantidade_Tolerancia (res.getDouble ("NR_Quantidade_Tolerancia"));
        edVolta.setNR_Dias_Contagem (res.getInt ("NR_Dias_Contagem"));

        edVolta.setCD_Deposito (res.getString ("CD_Deposito"));
        edVolta.setNM_Rua (res.getString ("NM_Rua"));
        edVolta.setNR_Endereco (res.getString ("NR_Endereco"));
        edVolta.setNR_Apartamento (res.getString ("NR_Apartamento"));

        edVolta = this.contaEstoque(edVolta);


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

  public EstoqueED contaEstoque (EstoqueED ed) throws Excecoes {

	    String sql = null;
	    try {

	        ed.setDM_Movimento("N");

	        String dt_Movimento="01/01/2000", dt_Ordem="01/01/2000";
	        double qt_estoque=0;
	        sql =" SELECT DT_Movimento, DT_Ordem, NR_Quantidade, oid_movimento_estoque, VL_Unitario  FROM Movimentos_Estoques WHERE OID_Estoque = " + ed.getOid_estoque() + 	" AND DM_movimento='X' AND DT_Ordem IS NOT NULL ORDER BY DT_Ordem DESC LIMIT 1  ";
	        //sql =" SELECT DT_Movimento, DT_Ordem, NR_Quantidade, oid_movimento_estoque, VL_Unitario  FROM Movimentos_Estoques WHERE OID_Estoque = " + ed.getOid_estoque() + 	" AND DM_movimento='X'  ORDER BY dt_movimento DESC LIMIT 1  ";

	        System.out.println(sql);

	        ResultSet resAj = this.executasql.executarConsulta (sql);
	        if (resAj.next ()) {
	      	  dt_Movimento=resAj.getString("DT_Movimento");
	      	  dt_Ordem=resAj.getString("DT_Ordem");
	      	  qt_estoque=resAj.getDouble("NR_Quantidade");

		      System.out.println("Ajuste dt_Movimento=" + dt_Movimento +" dt_Ordem=" + dt_Ordem + " qt_estoque=" + qt_estoque);

	        }

	        sql =   " SELECT dm_movimento, nr_quantidade, DT_movimento " +
		            " FROM Movimentos_Estoques     " +
		            " WHERE DM_Movimento <>'X' " +
		            //" AND   DT_Movimento >= '" + dt_Movimento + "'" +
		            " AND   dt_Ordem 	  >= '" + dt_Ordem + "'" +
		            " AND   OID_Estoque = " +  ed.getOid_estoque() +
		        	" ORDER BY DT_movimento, DM_Movimento ";

	        ResultSet res2 = this.executasql.executarConsulta (sql);
	        while (res2.next ()) {


	          ed.setDM_Movimento("S");
	          if ("E".equals (res2.getString ("dm_movimento"))) {
	        	  qt_estoque += res2.getDouble ("nr_quantidade");
	          }
	          if ("S".equals (res2.getString ("dm_movimento"))) {
	        	  qt_estoque -= res2.getDouble ("nr_quantidade");
	          }
			  System.out.println("Lendo Movimento=" + res2.getString ("DT_movimento") +" dm_movimento=" + res2.getString ("dm_movimento") + " qt_estoque=" + qt_estoque);
	        }
	        ed.setVl_Quantidade (qt_estoque);
	        ed.setVl_estoque (qt_estoque);

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao recuperar registro");
	      excecoes.setMetodo ("getByRecord()");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }

	    return ed;
	  }


  public ArrayList Lista (EstoqueED ed) throws Excecoes {

    String sql = null;
    String sqlBusca = null;
    ArrayList list = new ArrayList ();
    EstoqueED edVolta = new EstoqueED ();

    try {

      sql = " select * from estoques";
      sql += " where 1 =1 ";
      if (ed.getCd_estoque () != null && !ed.getCd_estoque ().equals ("null") && !ed.getCd_estoque ().equals ("")) {
        sql += " and cd_estoque LIKE '" + ed.getCd_estoque () + "%'";
      }
      if (ed.getDm_status () != null && !ed.getDm_status ().equals ("null")  && !ed.getDm_status ().equals ("")) {
          sql += " and Dm_status = '" + ed.getDm_status() + "'";
      }

      if (ed.getNm_estoque () != null && !ed.getNm_estoque ().equals ("null") && !ed.getNm_estoque ().equals ("")) {
        sql += "and nm_estoque LIKE '" + ed.getNm_estoque () + "%'";
      }
      if (ed.getNm_referencia () != null && !ed.getNm_referencia ().equals ("null") && !ed.getNm_referencia ().equals ("")) {
        sql += "and nm_referencia_fabrica LIKE '" + ed.getNm_referencia () + "%'";
      }
      if (String.valueOf (ed.getOid_sub_grupo_estoque ()) != null && !String.valueOf (ed.getOid_sub_grupo_estoque ()).equals ("null")
          && !String.valueOf (ed.getOid_sub_grupo_estoque ()).equals ("") && !String.valueOf (ed.getOid_sub_grupo_estoque ()).equals ("0")) {
        sql += "and Oid_sub_grupo_estoque = " + ed.getOid_sub_grupo_estoque () + "";
      }
      if (String.valueOf (ed.getOid_grupo_estoque ()) != null && !String.valueOf (ed.getOid_grupo_estoque ()).equals ("null")
          && !String.valueOf (ed.getOid_grupo_estoque ()).equals ("") && !String.valueOf (ed.getOid_grupo_estoque ()).equals ("0")) {
        sql += "and Oid_grupo_estoque = " + ed.getOid_grupo_estoque () + "";
      }

      if (ed.getCD_Deposito () != null && !ed.getCD_Deposito ().equals ("null")  && !ed.getCD_Deposito ().equals ("")) {
          sql += " and CD_Deposito = '" + ed.getCD_Deposito() + "'";
      }
      if (ed.getNM_Rua () != null && !ed.getNM_Rua ().equals ("null")  && !ed.getNM_Rua ().equals ("")) {
          sql += " and NM_Rua = '" + ed.getNM_Rua() + "'";
      }
      if (ed.getNR_Endereco () != null && !ed.getNR_Endereco ().equals ("null")  && !ed.getNR_Endereco ().equals ("")) {
          sql += " and NR_Endereco = '" + ed.getNR_Endereco() + "'";
      }
      if (ed.getNR_Apartamento () != null && !ed.getNR_Apartamento ().equals ("null")  && !ed.getNR_Apartamento ().equals ("")) {
          sql += " and NR_Apartamento = '" + ed.getNR_Apartamento() + "'";
      }


      sql += "order by nm_estoque ";

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        double conta = 0;
        edVolta = new EstoqueED ();
        edVolta.setOid_estoque (res.getLong ("oid_estoque"));
        edVolta.setCd_estoque (res.getString ("Cd_estoque"));
        edVolta.setNm_estoque (res.getString ("Nm_estoque"));
    	edVolta.setNm_referencia (" ");
        if (res.getString ("Nm_referencia_fabrica") != null && !res.getString ("Nm_referencia_fabrica").equals("null")) {
        	edVolta.setNm_referencia (res.getString ("Nm_referencia_fabrica"));
        }
        edVolta.setVl_custo (res.getDouble ("Vl_custo"));
        edVolta.setDt_stamp (res.getString ("Dt_stamp"));
        edVolta.setOid_unidade_produto (res.getLong ("Oid_unidade_produto"));
        edVolta.setOid_sub_grupo_estoque (res.getInt ("Oid_sub_grupo_estoque"));
        edVolta.setOid_grupo_estoque (res.getInt ("Oid_grupo_estoque"));
        edVolta.setVl_custo_minimo (res.getDouble ("Vl_custo_minimo"));
        edVolta.setVl_custo_maximo (res.getDouble ("Vl_custo_maximo"));
        edVolta.setVl_estoque_minimo (res.getDouble ("Vl_estoque_minimo"));
        edVolta.setVl_estoque_maximo (res.getDouble ("Vl_estoque_maximo"));
        if (res.getString ("Dm_status") != null && res.getString ("Dm_status").equals ("I")) {
          edVolta.setDm_status ("Inativo");
        }
        else {
          edVolta.setDm_status ("Ativo");
        }
        if (res.getString ("Dm_tipo_produto") != null && res.getString ("Dm_tipo_produto").equals ("D")) {
          edVolta.setDm_tipo_produto ("Diverso");
        }
        else if (res.getString ("Dm_tipo_produto") != null && res.getString ("Dm_tipo_produto").equals ("R")) {
          edVolta.setDm_tipo_produto ("Remanufaturado");
        }
        else if (res.getString ("Dm_tipo_produto") != null && res.getString ("Dm_tipo_produto").equals ("P")) {
          edVolta.setDm_tipo_produto ("Pneu Usado/Recap.");
        }
        else {
          edVolta.setDm_tipo_produto ("Estoque");
        }
        edVolta.setNm_classificacao_fiscal (res.getString ("Nm_classificacao_fiscal"));
        edVolta.setDm_negativo (res.getString ("Dm_negativo"));
        edVolta.setNr_leadtime (res.getLong ("Nr_leadtime"));
        edVolta.setNr_leadtime_maximo (res.getLong ("Nr_leadtime_maximo"));
        edVolta.setOid_produto (res.getLong ("Oid_produto"));

        edVolta = this.contaEstoque(edVolta);


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



  public ArrayList ListaContagem (EstoqueED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    EstoqueED edVolta = new EstoqueED ();
    FormataDataBean formataData = new FormataDataBean ();

    try {

      sql = " SELECT Estoques.NM_Estoque,  Estoques.oid_Estoque, Estoques.CD_Estoque, Contagens_Estoques.* " +
      		" FROM   Estoques, Contagens_Estoques  " +
      		" WHERE  Estoques.oid_Estoque = Contagens_Estoques.oid_Estoque " +
      		" AND    Estoques.Oid_Estoque = '" + ed.getOid_estoque () + "'";
      sql += "order by Contagens_Estoques.oid_Contagem_Estoque desc ";
      System.out.println (sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new EstoqueED ();
        edVolta.setOid_estoque (res.getLong ("oid_estoque"));
        edVolta.setCd_estoque (res.getString ("Cd_estoque"));
        edVolta.setNm_estoque (res.getString ("Nm_estoque"));

        formataData.setDT_FormataData (res.getString ("dt_contagem"));
        edVolta.setDT_Contagem (formataData.getDT_FormataData ());
        edVolta.setHR_Contagem(res.getString ("hr_contagem"));

        edVolta.setNR_Quantidade_Contagem (res.getDouble ("NR_Quantidade_Contagem"));
        edVolta.setVl_estoque (res.getDouble ("NR_Quantidade_Estoque"));
        edVolta.setOid_Usuario (res.getInt ("Oid_Usuario"));
        edVolta.setNM_Usuario(" ");
        if (res.getInt ("Oid_Usuario")>0) {
        	sql ="SELECT NM_Usuario FROM Usuarios WHERE oid_Usuario=" +  res.getInt ("Oid_Usuario");
            ResultSet res2 = this.executasql.executarConsulta (sql);
            if (res2.next ()) {
                edVolta.setNM_Usuario(res2.getString("NM_Usuario"));
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
      excecoes.setMetodo ("ListaContagem()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public EstoqueED getCodigo_Item (EstoqueED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String codigo = "";
    EstoqueED edVolta = new EstoqueED ();

    try {

      // System.out.println ("INCLUIR getCodigo_Item 1");

      sql = " select nr_proximo_item as result from grupos_estoques " +
          " where oid_grupo_estoque = " + ed.getOid_grupo_estoque ();
      //sql += " and oid_sub_grupo_estoque = " + ed.getOid_sub_grupo_estoque ();

      // System.out.println ("INCLUIR getCodigo_Item >>" + sql);

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        // System.out.println ("INCLUIR getCodigo_Item W1");

        valOid = res.getLong ("result") + 1;
        edVolta = new EstoqueED ();
        edVolta.setNr_proximo_item (valOid);
        if (ed.getOid_grupo_estoque () <= 9) {
          codigo = "0" + String.valueOf (ed.getOid_grupo_estoque ());
        }
        else {
          codigo = String.valueOf (ed.getOid_grupo_estoque ());
        }
        if (ed.getOid_sub_grupo_estoque () <= 9) {
          codigo += "0" + String.valueOf (ed.getOid_sub_grupo_estoque ());
        }
        else {
          codigo += String.valueOf (ed.getOid_sub_grupo_estoque ());
        }
        if (valOid <= 99 && valOid > 9) {
          codigo += "0" + String.valueOf (valOid);
        }
        else if (valOid <= 9) {
          codigo += "00" + String.valueOf (valOid);
        }
        else {
          codigo += String.valueOf (valOid);
        }

        // System.out.println ("INCLUIR getCodigo_Item W99->" + codigo);

        edVolta.setCd_estoque (codigo);
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

  public void update_Proximo_nro (long proximo , int grupo , int subgrupo) throws Excecoes {

    String sql = null;

    try {

      sql = " UPDATE sub_grupos_estoques set " +
          " nr_proximo_item=" + proximo;
      sql += " WHERE oid_sub_grupo_estoque = " + subgrupo;
      sql += " and oid_grupo_estoque = " + grupo;
      //int i = executasql.executarUpdate (sql);

      sql = " UPDATE grupos_estoques set " +
          " nr_proximo_item=" + proximo +
          " WHERE oid_grupo_estoque = " + grupo;
      executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar dados do Produto");
      excecoes.setMetodo ("altera()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public EstoqueED inclui_itemXfornecedor (EstoqueED ed) throws Excecoes {

    String sql = null;
    int valOid = 0;
    EstoqueED EstoqueED = new EstoqueED ();

    try {

      ResultSet rs = executasql.executarConsulta ("SELECT count(OID_item_fornecedor) as result FROM itens_fornecedores");
      while (rs.next ()) {
        valOid = rs.getInt ("result");
      }

      sql = "INSERT INTO itens_fornecedores (" +
          " OID_item_fornecedor," +
          " oid_fornecedor," +
          " dt_stamp," +
          " usuario_stamp," +
          " dm_stamp," +
          " vl_lista_preco, " +
          " dm_preferencial," +
          " oid_estoque" +
          ")";
      sql += " values ( ";
      sql += ++valOid + ",'" +
          ed.getOid_fornecedor () + "','" +
          ed.getDt_stamp () + "','','S'," +
          ed.getVl_lista_preco () + ",'" +
          ed.getDm_preferencial () + "'," +
          ed.getOid_estoque () + ")";
//// System.out.println(sql);
      int i = executasql.executarUpdate (sql);
      EstoqueED.setOid_estoque (valOid);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Produto j� registrado!");
      excecoes.setMetodo ("inclui()");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return EstoqueED;

  }

  public void altera_itemXfornecedor (EstoqueED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " UPDATE itens_fornecedores set " +
          " oid_fornecedor='" + ed.getOid_fornecedor () +
          "', dm_preferencial='" + ed.getDm_preferencial () +
          "', dt_stamp='" + ed.getDt_stamp () +
          "', vl_lista_preco=" + ed.getVl_lista_preco () +
          " WHERE oid_item_fornecedor = " + ed.getOid_item_fornecedor ();

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar dados do Produto");
      excecoes.setMetodo ("altera()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta_itemXfornecedor (EstoqueED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "delete from itens_fornecedores WHERE oid_item_fornecedor = ";
      sql += "" + ed.getOid_item_fornecedor () + "";

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao deletar Produto");
      excecoes.setMetodo ("deleta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public EstoqueED getByRecord_itemXfornecedor (EstoqueED ed) throws Excecoes {

    String sql = null;
    FormataDataBean formataData = new FormataDataBean ();
    EstoqueED edVolta = new EstoqueED ();

    try {

      sql = " select * from itens_fornecedores";
      sql += " where 1 = 1 ";
      if (String.valueOf (ed.getOid_estoque ()) != null && !String.valueOf (ed.getOid_estoque ()).equals ("null") && !String.valueOf (ed.getOid_estoque ()).equals ("0")) {
        sql += " and oid_estoque = " + ed.getOid_estoque ();
      }
      if (ed.getOid_fornecedor () != null && !ed.getOid_fornecedor ().equals ("null") && !ed.getOid_fornecedor ().equals ("")) {
        sql += " and Oid_fornecedor = '" + ed.getOid_fornecedor () + "'";
      }
      if (String.valueOf (ed.getOid_item_fornecedor ()) != null && !String.valueOf (ed.getOid_item_fornecedor ()).equals ("null") && !String.valueOf (ed.getOid_item_fornecedor ()).equals ("0")) {
        sql += " and Oid_item_fornecedor = '" + ed.getOid_item_fornecedor () + "'";
      }

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new EstoqueED ();
        edVolta.setOid_estoque (res.getLong ("oid_estoque"));
        edVolta.setOid_item_fornecedor (res.getLong ("Oid_item_fornecedor"));
        edVolta.setOid_fornecedor (res.getString ("Oid_fornecedor"));

        edVolta.setVl_lista_preco (res.getDouble ("vl_lista_preco"));
        edVolta.setVl_ultima_compra (res.getDouble ("vl_ultima_compra"));
//        formatar data
        formataData.setDT_FormataData (res.getString ("dt_ultima_compra"));
        edVolta.setDt_ultima_compra (formataData.getDT_FormataData ());

        if (res.getString ("dm_preferencial") != null && !res.getString ("dm_preferencial").equals ("null") &&
            !res.getString ("dm_preferencial").equals ("")) {
          edVolta.setDm_preferencial (res.getString ("dm_preferencial"));
        }
        else {
          edVolta.setDm_preferencial ("N");
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

  public ArrayList Lista_itemXfornecedor (EstoqueED ed) throws Excecoes {

    String sql = null;

    ArrayList list = new ArrayList ();
    EstoqueED edVolta = new EstoqueED ();
    FormataDataBean formataData = new FormataDataBean ();

    try {

      sql = " select *, pessoas.nm_razao_social from itens_fornecedores, estoques, pessoas ";
      sql += " where itens_fornecedores.oid_Estoque = estoques.oid_estoque and  itens_fornecedores.Oid_fornecedor = Pessoas.oid_Pessoa ";
      if (String.valueOf (ed.getOid_estoque ()) != null && !String.valueOf (ed.getOid_estoque ()).equals ("null") && !String.valueOf (ed.getOid_estoque ()).equals ("0")) {
        sql += " and itens_fornecedores.oid_estoque = " + ed.getOid_estoque ();
      }
      if (ed.getOid_fornecedor () != null && !ed.getOid_fornecedor ().equals ("null") && !ed.getOid_fornecedor ().equals ("")) {
        sql += " and itens_fornecedores.Oid_fornecedor = '" + ed.getOid_fornecedor () + "'";
      }
      if (String.valueOf (ed.getOid_item_fornecedor ()) != null && !String.valueOf (ed.getOid_item_fornecedor ()).equals ("null") && !String.valueOf (ed.getOid_item_fornecedor ()).equals ("0")) {
        sql += " and itens_fornecedores.Oid_item_fornecedor = '" + ed.getOid_item_fornecedor () + "'";
      }
      if (ed.getDm_preferencial () != null && !ed.getDm_preferencial ().equals ("null") && !ed.getDm_preferencial ().equals ("")
          && !ed.getDm_preferencial ().equals ("T")) {
        sql += " and Dm_preferencial = '" + ed.getDm_preferencial () + "'";
      }
      sql += "order by estoques.nm_estoque ";

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new EstoqueED ();
        edVolta.setOid_estoque (res.getLong ("oid_estoque"));
        edVolta.setOid_item_fornecedor (res.getLong ("Oid_item_fornecedor"));

        edVolta.setVl_lista_preco (res.getDouble ("vl_lista_preco"));
        edVolta.setVl_ultima_compra (res.getDouble ("vl_ultima_compra"));
        // formatar data
        formataData.setDT_FormataData (res.getString ("dt_ultima_compra"));
        edVolta.setDt_ultima_compra (formataData.getDT_FormataData ());

        edVolta.setDm_preferencial ("Sim");
        if ("N".equals (res.getString ("dm_preferencial"))) {
          edVolta.setDm_preferencial ("Nao");
        }
        if ("L".equals (res.getString ("dm_preferencial"))) {
          edVolta.setDm_preferencial ("Lista");
        }

          edVolta.setCd_estoque (res.getString ("Cd_estoque"));
          edVolta.setNm_estoque (res.getString ("Nm_estoque"));
	      edVolta.setNm_referencia (" ");
	      if (res.getString ("Nm_referencia_fabrica") != null && !res.getString ("Nm_referencia_fabrica").equals("null")) {
	    	  edVolta.setNm_referencia (res.getString ("Nm_referencia_fabrica"));
	      }
          edVolta.setVl_custo (res.getDouble ("Vl_custo"));
          if ("I".equals (res.getString ("Dm_status"))) {
            edVolta.setDm_status ("Inativo");
          }
          else {
            edVolta.setDm_status ("Ativo");
          }
          if ("D".equals (res.getString ("Dm_tipo_produto"))) {
            edVolta.setDm_tipo_produto ("Diverso");
          }
          else {
            edVolta.setDm_tipo_produto ("Estoque");
          }
          edVolta.setOid_fornecedor (res.getString ("Oid_fornecedor"));
          edVolta.setNm_fornecedor (res.getString ("NM_Razao_Social"));
          list.add (edVolta);
        }


    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem (exc.getMessage ());
      excecoes.setMetodo ("Lista()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  /*public void inclui_produto_cliente() throws Excecoes{
      Produto_ClienteED produto = new Produto_ClienteED();
      Produto_ClienteRN rn = new Produto_ClienteRN();

      String sql = null;

      try{

        sql = " select * from estoques";
        sql += " where 1 = 1 order by oid_estoque";

        ResultSet res = null;
        res = this.executasql.executarConsulta(sql);
        while (res.next()){
            produto.setOID_Produto(res.getInt("oid_produto"));
            produto.setOID_Pessoa("92758457000188");
            produto.setOid_Estoque(res.getInt("oid_estoque"));
            produto.setOID_Produto_Cliente(res.getString("oid_estoque")+"92758457000188");
            produto.setOID_Embalagem(1);
            produto.setNR_Peso(0);
            produto.setDM_Rotatividade("LIFO");
            produto.setNM_Referencia("PRODUTO ALMOXARIFADO");
            produto.setDM_Serie("N");
            produto.setNr_Altura(0);
            produto.setNr_Largura(0);
            produto.setNr_Profundidade(0);
            produto.setNr_Empilhagem(0);
            produto.setOid_Tipo_Pallet(1);

            rn.inclui_produto_compra(produto);
        }
      }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao incluir registro");
        excecoes.setMetodo("inclui_produto_cliente()");
        excecoes.setExc(exc);
        throw excecoes;
      }
         }*/

  public ArrayList ListaWMS (EstoqueED ed) throws Excecoes {

    String sql = null;
    String sqlBusca = null;
    ArrayList list = new ArrayList ();
    EstoqueED edVolta = new EstoqueED ();

    try {

      sql = " select estoques.cd_estoque, estoques.nm_estoque, produtos_clientes.*  " +
          " from produtos_clientes, estoques";
      sql += " where estoques.oid_estoque = produtos_clientes.oid_estoque ";
      if (ed.getCd_estoque () != null && !ed.getCd_estoque ().equals ("null") && !ed.getCd_estoque ().equals ("")) {
        sql += " and cd_estoque LIKE '" + ed.getCd_estoque () + "%'";
      }
      if (ed.getNm_estoque () != null && !ed.getNm_estoque ().equals ("null") && !ed.getNm_estoque ().equals ("")) {
        sql += "and nm_estoque LIKE '" + ed.getNm_estoque () + "%'";
      }

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new EstoqueED ();
        edVolta.setOid_estoque (res.getLong ("oid_estoque"));
        edVolta.setCd_estoque (res.getString ("Cd_estoque"));
        edVolta.setNm_estoque (res.getString ("Nm_estoque"));
        edVolta.setOid_fornecedor (res.getString ("oid_pessoa"));
        edVolta.setOid_produto (res.getLong ("Oid_produto"));
        edVolta.setProduto_cliente (res.getString ("oid_produto_cliente"));

        sqlBusca = "select * from pessoas where oid_pessoa = '" + res.getString ("oid_pessoa") + "'";
        ResultSet rs = null;
        rs = this.executasql.executarConsulta (sqlBusca);
        while (rs.next ()) {
          edVolta.setNm_fornecedor (rs.getString ("nm_razao_social"));
        }

        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar registro");
      excecoes.setMetodo ("listaWMS()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public EstoqueED getEstoque (EstoqueED ed) throws Excecoes {

    String sql = null;
    String sqlBusca = null;
    EstoqueED edVolta = new EstoqueED ();

    try {

      double conta = 0;

      sqlBusca = "Select nr_quantidade from estoques where 1 = 1 " +
          " and oid_estoque = " + ed.getOid_estoque ();
      ResultSet rs = null;
      rs = this.executasql.executarConsulta (sqlBusca);
      while (rs.next ()) {
        conta += rs.getDouble ("nr_quantidade");
      }
      edVolta.setVl_estoque (conta);

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

  public void baixa_EstoqueWMS (String oid_Estoque , double Quantidade) throws Excecoes {

    String sqlBusca = null;
    EstoqueED edVolta = new EstoqueED ();

    try {

      double contado = 0;
      double baixado = 0;
      double resto = Quantidade;
      sqlBusca = "Select * from estoques_clientes where 1 = 1 ";
      if (oid_Estoque != null && !oid_Estoque.equals ("") && !oid_Estoque.equals ("0")) {
        sqlBusca += " and oid_estoque = " + oid_Estoque;
      }

      ResultSet rs = null;
      rs = this.executasql.executarConsulta (sqlBusca);
      while (rs.next () && resto != 0) {

        if (rs.getDouble ("nr_quantidade") >= resto) {
          contado = rs.getDouble ("nr_quantidade") - resto;
          baixado = resto;
          int u = this.executasql.executarUpdate ("update estoques_clientes set nr_quantidade = " + contado +
                                                  " where oid_estoque_cliente = '" + rs.getString ("oid_estoque_cliente") + "'");
        }
        else {
          contado = 0;
          baixado = rs.getDouble ("nr_quantidade");
          int u = this.executasql.executarUpdate ("update estoques_clientes set nr_quantidade = " + contado +
                                                  " where oid_estoque_cliente = '" + rs.getString ("oid_estoque_cliente") + "'");
        }
        resto = resto - baixado;
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao atualizar Estoque");
      excecoes.setMetodo ("baixa_Estoque()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void baixa_Estoque (String oid_Estoque , double qt_tirar , String oid_Veiculo) throws Excecoes {

    String sqlBusca = null;
    EstoqueED edVolta = new EstoqueED ();

    double qt_atual = 0;

    // System.out.println ("baixa_Estoque  ITEM ->> " + oid_Estoque + " qt_tirar " + qt_tirar);

    try {

      edVolta.setOid_estoque (new Long (oid_Estoque).longValue ());
      edVolta = this.getByRecord (edVolta);
      qt_atual = edVolta.getVl_Quantidade ();

      if (qt_atual >= qt_tirar) {

        Movimento_EstoqueBean movimento_Est = new Movimento_EstoqueBean ();
        movimento_Est.setDM_Movimento ("S");
        movimento_Est.setDT_Movimento (Data.getDataDMY ());
        movimento_Est.setNR_Quantidade (qt_tirar);
        movimento_Est.setOID_Estoque (edVolta.getOid_estoque ());
        movimento_Est.setVL_Unitario (edVolta.getVl_custo ());
        movimento_Est.setOid_Veiculo (oid_Veiculo);
        movimento_Est.insert ();

      }
      else {
        Excecoes e = new Excecoes ();
        e.setMensagem ("Estoque insuficiente para este �tem.");
        throw e;
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Estoque insuficiente !");
      excecoes.setMetodo ("baixa_Estoque()");
      excecoes.setExc (exc);
      throw excecoes;
    }

  }

  public void entrada_Estoque (String oid_Estoque , double qt_incluir , String oid_Veiculo) throws Excecoes {

    String sqlBusca = null;
    EstoqueED edVolta = new EstoqueED ();

    double qt_atual = 0;

    // System.out.println ("entrada_Estoque  ITEM ->> " + oid_Estoque + " qt_incluir " + qt_incluir);

    try {

      edVolta.setOid_estoque (new Long (oid_Estoque).longValue ());
      edVolta = this.getByRecord (edVolta);
      Movimento_EstoqueBean movimento_Est = new Movimento_EstoqueBean ();
      movimento_Est.setDM_Movimento ("E");
      movimento_Est.setDT_Movimento (Data.getDataDMY ());
      movimento_Est.setNR_Quantidade (qt_incluir);
      movimento_Est.setOID_Estoque (edVolta.getOid_estoque ());
      movimento_Est.setVL_Unitario (edVolta.getVl_custo ());
      movimento_Est.setOid_Veiculo (oid_Veiculo);
      movimento_Est.insert ();

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Estoque insuficiente !");
      excecoes.setMetodo ("baixa_Estoque()");
      excecoes.setExc (exc);
      throw excecoes;
    }

  }

  public ArrayList Lista_Movimentacao_item (EstoqueED ed , String tipo) throws Excecoes {

    String sql = null;

    ArrayList list = new ArrayList ();
    EstoqueED edVolta = new EstoqueED ();
    FormataDataBean formataData = new FormataDataBean ();

    try {
      if (tipo.equals ("E")) {

        sql = " select estoques.oid_estoque, solicitacoes_compras.oid_solicitacao_compra, notas_fiscais_transacoes.oid_nota_fiscal, notas_fiscais_transacoes.nr_nota_fiscal, notas_fiscais_transacoes.dt_emissao,  itens_cotacoes_compras.oid_fornecedor, itens_cotacoes_compras.vl_preco, romaneios_notas_fiscais.vl_quantidade, romaneios_notas_fiscais.dt_stamp " +
            "from itens_cotacoes_compras, solicitacoes_compras, notas_fiscais_transacoes, romaneios_notas_fiscais " +
            "where estoques.oid_estoque = itens_solicitacoes_compras.oid_estoque " +
            "and itens_solicitacoes_compras.oid_item_solicitacao_compra = cotacoes_compras.oid_item_solicitacao_compra " +
            "and cotacoes_compras.oid_cotacao_compra = itens_cotacoes_compras.oid_cotacao_compra " +
            "and itens_cotacoes_compras.dm_status = 'A' " +
            "and solicitacoes_compras.oid_solicitacao_compra = itens_solicitacoes_compras.oid_solicitacao_compra " +
            "and solicitacoes_compras.oid_solicitacao_compra = pedidos_compras.oid_solicitacao_compra " +
            "and pedidos_compras.dm_status != 'E' " +
            "and estoques.dm_tipo_produto = 'E' " +
            "and solicitacoes_compras.dm_status != 'F' " +
            "and pedidos_compras.oid_pedido_compra = pedidos_compras_notas_fiscais.oid_pedido_compra " +
            "and pedidos_compras_notas_fiscais.oid_nota_fiscal = notas_fiscais_transacoes.oid_nota_fiscal " +
            "and notas_fiscais_transacoes.oid_nota_fiscal = romaneios_notas_fiscais.oid_nota_fiscal " +
            "and romaneios_notas_fiscais.oid_estoque = estoques.oid_estoque ";

        if (String.valueOf (ed.getOid_estoque ()) != null && !String.valueOf (ed.getOid_estoque ()).equals ("null") && !String.valueOf (ed.getOid_estoque ()).equals ("0")) {
          sql += " and estoques.oid_estoque = " + ed.getOid_estoque ();
        }

        sql += "order by solicitacoes_compras.dt_solicitacao, itens_cotacoes_compras.oid_fornecedor ";
        // System.out.println (sql);
        ResultSet res = null;

        res = this.executasql.executarConsulta (sql);
        while (res.next ()) {
          edVolta = new EstoqueED ();
          edVolta.setOid_estoque (res.getLong ("oid_estoque"));
          edVolta.setOid_fornecedor (res.getString ("Oid_fornecedor"));
          edVolta.setOid_Solicitacao_Compra (res.getString ("Oid_solicitacao_compra"));
          edVolta.setNr_Nota_Fiscal (res.getString ("nr_nota_fiscal"));
          edVolta.setOid_Nota_Fiscal (res.getString ("oid_nota_fiscal"));
          formataData.setDT_FormataData (res.getString ("dt_emissao"));
          edVolta.setDt_Nota_Fiscal (formataData.getDT_FormataData ());
          formataData.setDT_FormataData (res.getString ("dt_stamp"));
          edVolta.setDt_Entrada (formataData.getDT_FormataData ());
          edVolta.setVl_Unitario (res.getDouble ("vl_preco"));
          edVolta.setVl_Quantidade (res.getDouble ("vl_quantidade"));
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
      else {
        sql = " select estoques.oid_estoque, itens_requisicoes_compras.vl_quantidade, itens_requisicoes_compras.vl_quantidade_entrega, " +
            " itens_requisicoes_compras.dt_entrega, requisicoes_compras.oid_requisicao_compra, requisicoes_compras.dt_requisicao, requisicoes_compras.oid_centro_custo " +
            " from itens_requisicoes_compras, requisicoes_compras, estoques " +
            " where estoques.oid_estoque = itens_requisicoes_compras.oid_estoque " +
            " and estoques.dm_tipo_produto = 'E' " +
            " and itens_requisicoes_compras.dm_status = 'E' " +
            " and requisicoes_compras.oid_requisicao_compra = itens_requisicoes_compras.oid_requisicao_compra ";

        if (String.valueOf (ed.getOid_estoque ()) != null && !String.valueOf (ed.getOid_estoque ()).equals ("null") && !String.valueOf (ed.getOid_estoque ()).equals ("0")) {
          sql += " and estoques.oid_estoque = " + ed.getOid_estoque ();
        }
        sql += "order by requisicoes_compras.dt_requisicao ";
        // System.out.println (sql);
        ResultSet res = null;
        res = this.executasql.executarConsulta (sql);
        while (res.next ()) {
          edVolta = new EstoqueED ();
          edVolta.setOid_estoque (res.getLong ("oid_estoque"));
          edVolta.setOid_Requisicao_Compra (res.getString ("Oid_requisicao_compra"));
          formataData.setDT_FormataData (res.getString ("dt_requisicao"));
          edVolta.setDt_Requisicao (formataData.getDT_FormataData ());
          formataData.setDT_FormataData (res.getString ("dt_entrega"));
          edVolta.setDt_Entrada (formataData.getDT_FormataData ());
          edVolta.setVl_Quantidade (res.getDouble ("vl_quantidade"));
          edVolta.setVl_Quantidade_Entrega (res.getDouble ("vl_quantidade_entrega"));
          ResultSet resTP = null;
          // System.out.println ("bd2 " + "select * from estoques where oid_estoque = " + edVolta.getOid_estoque ());
          resTP = this.executasql.executarConsulta ("select * from estoques where oid_estoque = " + edVolta.getOid_estoque ());
          while (resTP.next ()) {
            edVolta.setCd_estoque (resTP.getString ("Cd_estoque"));
            edVolta.setNm_estoque (resTP.getString ("Nm_estoque"));
          }
          resTP = null;
          // System.out.println ("bd3 " + "select * from estoques where oid_estoque = " + res.getLong ("oid_centro_custo"));
          resTP = this.executasql.executarConsulta ("select * from centros_custos where oid_centro_custo = " + res.getLong ("oid_centro_custo"));
          while (resTP.next ()) {
            edVolta.setCentro_custo (resTP.getString ("Cd_centro_custo"));
          }
          list.add (edVolta);
        }
      }

    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar Movimenta��o");
      excecoes.setMetodo ("Lista()");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public void Movimento_Entrada_Estoque (String oid_Estoque , String oid_NF , double Quantidade) throws Excecoes {

    String sqlBusca = null;
    String sql = null;
    EstoqueED edVolta = new EstoqueED ();
    double vl_preco = 0;
    double custo = 0;
    double custoMax = 0;
    double custoMin = 0;
    double medio = 0;
    double total = 0;
    double totalAtual = 0;
    double qtdeAtual = 0;
    String dt_nf = "";
    String fornecedor = "";

    try {

      sqlBusca = "Select vl_liquido, nr_volumes from itens_notas_fiscais_transacoes where 1 = 1 ";
      if (oid_Estoque != null && !oid_Estoque.equals ("") && !oid_Estoque.equals ("0") &&
          oid_NF != null && !oid_NF.equals ("") && !oid_NF.equals ("0")) {
        sqlBusca += " and oid_estoque = " + oid_Estoque;
        sqlBusca += " and oid_nota_fiscal = '" + oid_NF + "'";
      }
      ResultSet res = null;
      res = this.executasql.executarConsulta (sqlBusca);
      while (res.next ()) {
        vl_preco = res.getDouble ("vl_liquido") / res.getDouble ("nr_volumes");
      }
      // System.out.println ("vl_preco" + vl_preco);
      custo = vl_preco;
      // busca estoque atual COM WMS
      // sqlBusca = "Select * from estoques_clientes where 1 = 1 ";
      // busca estoque atual SEM WMS
      sqlBusca = "Select nr_quantidade from estoques where 1 = 1 ";
      if (oid_Estoque != null && !oid_Estoque.equals ("") && !oid_Estoque.equals ("0")) {
        sqlBusca += " and oid_estoque = " + oid_Estoque;
      }
      ResultSet rs = null;
      rs = this.executasql.executarConsulta (sqlBusca);
      while (rs.next ()) {
        qtdeAtual += rs.getDouble ("nr_quantidade");
      }
      // busca dados do item de estoque
      sqlBusca = "Select vl_custo, vl_custo_minimo, vl_custo_maximo, vl_medio from estoques where 1 = 1 ";
      if (oid_Estoque != null && !oid_Estoque.equals ("") && !oid_Estoque.equals ("0")) {
        sqlBusca += " and oid_estoque = " + oid_Estoque;
      }
      rs = null;
      rs = this.executasql.executarConsulta (sqlBusca);
      while (rs.next ()) {
        if (rs.getString ("vl_medio") != null && !rs.getString ("vl_medio").equals ("null")
            && !rs.getString ("vl_medio").equals ("0") && !rs.getString ("vl_medio").equals ("0.0")) {
          medio = rs.getDouble ("vl_medio");
        }
        else {
          medio = rs.getDouble ("vl_custo");
        }

        custoMax = rs.getDouble ("vl_custo_maximo");
        custoMin = rs.getDouble ("vl_custo_minimo");
      }

      // se o atual for 0
      if (medio == 0.0) {
        medio = vl_preco;
      }
      // calcula o novo preco medio
      totalAtual = qtdeAtual * medio;
      total = totalAtual + (vl_preco * Quantidade);
      medio = total / (qtdeAtual + Quantidade);

      // verifica maximo e minimo
      if (custoMax < vl_preco) {
        custoMax = vl_preco;
      }
      if (custoMin > vl_preco) {
        custoMin = vl_preco;
      }

      // inclui alteracoes no item de estoque
      sql = " UPDATE Estoques set " +
          "vl_custo=" + custo +
          ", dt_stamp='" + Data.getDataDMY () +
          "', vl_custo_minimo=" + custoMin +
          ", vl_custo_maximo=" + custoMax +
          ", vl_medio=" + medio;
      sql += " WHERE oid_estoque = '" + oid_Estoque + "'";
      int i = executasql.executarUpdate (sql);

      // inclui alteracoes em itens X fornecedores
      // localiza dados da NF
      sqlBusca = "Select notas_fiscais_transacoes.dt_entrada, notas_fiscais_transacoes.oid_pessoa from notas_fiscais_transacoes" +
          " where oid_nota_fiscal = '" + oid_NF + "'";
      res = null;
      res = this.executasql.executarConsulta (sqlBusca);
      while (res.next ()) {
        dt_nf = res.getString ("dt_entrada");
        fornecedor = res.getString ("oid_pessoa");
      }
      // localiza dados do itemXfornecedor
      sqlBusca = "Select * from itens_fornecedores" +
          " where oid_estoque = '" + oid_Estoque + "'" +
          " and oid_fornecedor = '" + fornecedor + "'";
      res = null;
      res = this.executasql.executarConsulta (sqlBusca);
      if (res.next ()) {
        sql = " UPDATE itens_fornecedores set " +
            " vl_ultima_compra=" + vl_preco +
            ", dt_ultima_compra='" + dt_nf + "'";
        sql += " WHERE oid_estoque = '" + oid_Estoque + "'";
        // System.out.println ("updt " + sql);
        int u = executasql.executarUpdate (sql);
      }
      else {
        int valOid = 0;
        rs = executasql.executarConsulta ("SELECT count(OID_item_fornecedor) as result FROM itens_fornecedores");
        while (rs.next ()) {
          valOid = rs.getInt ("result");
        }

        valOid++;

        sql = "INSERT INTO itens_fornecedores (" +
            " OID_item_fornecedor," +
            " oid_fornecedor," +
            " dt_stamp," +
            " usuario_stamp," +
            " dm_stamp," +
            " dm_preferencial," +
            " oid_estoque, " +
            " dt_ultima_compra," +
            " vl_ultima_compra" +
            ")";
        sql += " values ( ";
        sql += valOid + ",'" +
            fornecedor + "','" +
            Data.getDataDMY () + "','','S','" +
            "N" + "'," +
            oid_Estoque + ",'" +
            dt_nf + "'," +
            vl_preco + ")";
        // System.out.println ("ins " + sql);
        int u = executasql.executarUpdate (sql);
      }

//			dt entrega dos itens da solicitacao
      sql = "select oid_pedido_compra from pedidos_compras_notas_fiscais " +
          "where oid_nota_fiscal = '" + oid_NF + "'";
      res = null;
      // System.out.println ("it1 " + sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        sql = "update itens_solicitacoes_compras set dt_entrega = '" + dt_nf + "' " +
            "where oid_solicitacao_compra = '" + res.getString ("oid_pedido_compra") + "'" +
            " and oid_estoque = '" + oid_Estoque + "'";
        // System.out.println ("itens " + sql);
        i = executasql.executarUpdate (sql);
      }

    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao atualizar Estoque");
      excecoes.setMetodo ("movimento_Estoque()");
      excecoes.setExc (exc);
      throw excecoes;
    }

  }

  public ArrayList Analise_de_Compras (EstoqueED ed) throws Excecoes {

    String sql = null;
    String sqlBusca = null;
    ArrayList list = new ArrayList ();
    EstoqueED edVolta = new EstoqueED ();

    try {

      sql = " select * from estoques";
      sql += " where 1 =1 and estoques.dm_tipo_produto = 'E' and estoques.dm_status = 'A' ";
      if (ed.getCd_estoque () != null && !ed.getCd_estoque ().equals ("null") && !ed.getCd_estoque ().equals ("")) {
        sql += " and cd_estoque LIKE '" + ed.getCd_estoque () + "%'";
      }
      if (ed.getNm_estoque () != null && !ed.getNm_estoque ().equals ("null") && !ed.getNm_estoque ().equals ("")) {
        sql += "and nm_estoque LIKE '" + ed.getNm_estoque () + "%'";
      }
      if (ed.getNm_referencia () != null && !ed.getNm_referencia ().equals ("null") && !ed.getNm_referencia ().equals ("")) {
        sql += "and nm_referencia_fabrica LIKE '" + ed.getNm_referencia () + "%'";
      }
      if (String.valueOf (ed.getOid_sub_grupo_estoque ()) != null && !String.valueOf (ed.getOid_sub_grupo_estoque ()).equals ("null")
          && !String.valueOf (ed.getOid_sub_grupo_estoque ()).equals ("") && !String.valueOf (ed.getOid_sub_grupo_estoque ()).equals ("0")) {
        sql += "and Oid_sub_grupo_estoque = " + ed.getOid_sub_grupo_estoque () + "";
      }
      if (String.valueOf (ed.getOid_grupo_estoque ()) != null && !String.valueOf (ed.getOid_grupo_estoque ()).equals ("null")
          && !String.valueOf (ed.getOid_grupo_estoque ()).equals ("") && !String.valueOf (ed.getOid_grupo_estoque ()).equals ("0")) {
        sql += "and Oid_grupo_estoque = " + ed.getOid_grupo_estoque () + "";
      }
      if (String.valueOf (ed.getOid_produto ()) != null && !String.valueOf (ed.getOid_produto ()).equals ("null")
          && !String.valueOf (ed.getOid_produto ()).equals ("") && !String.valueOf (ed.getOid_produto ()).equals ("0")) {
        sql += "and Oid_produto = " + ed.getOid_produto () + "";
      }

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        double conta = 0;
        edVolta = new EstoqueED ();
        edVolta.setOid_estoque (res.getLong ("oid_estoque"));
        edVolta.setCd_estoque (res.getString ("Cd_estoque"));
        edVolta.setNm_estoque (res.getString ("Nm_estoque"));
        edVolta.setNm_referencia (res.getString ("Nm_referencia_fabrica"));
        edVolta.setVl_custo (res.getDouble ("Vl_custo"));
        edVolta.setDt_stamp (res.getString ("Dt_stamp"));
        edVolta.setOid_unidade_produto (res.getLong ("Oid_unidade_produto"));
        edVolta.setOid_sub_grupo_estoque (res.getInt ("Oid_sub_grupo_estoque"));
        edVolta.setOid_grupo_estoque (res.getInt ("Oid_grupo_estoque"));
        edVolta.setVl_custo_minimo (res.getDouble ("Vl_custo_minimo"));
        edVolta.setVl_custo_maximo (res.getDouble ("Vl_custo_maximo"));
        edVolta.setVl_estoque_minimo (res.getDouble ("Vl_estoque_minimo"));
        edVolta.setVl_estoque_maximo (res.getDouble ("Vl_estoque_maximo"));

        edVolta.setNm_classificacao_fiscal (res.getString ("Nm_classificacao_fiscal"));
        edVolta.setDm_negativo (res.getString ("Dm_negativo"));
        edVolta.setNr_leadtime (res.getLong ("Nr_leadtime"));
        edVolta.setNr_leadtime_maximo (res.getLong ("Nr_leadtime_maximo"));
        edVolta.setOid_produto (res.getLong ("Oid_produto"));

//	    		estoque com WMS
//	    		sqlBusca = "Select nr_quantidade from estoques_clientes where 1 = 1 " +
//	    				   " and oid_estoque = " + edVolta.getOid_estoque();
//	    		ResultSet rs = null;
//	        	rs = this.executasql.executarConsulta(sqlBusca);
//	        	while (rs.next()){
//	            	conta += rs.getDouble("nr_quantidade");
//	        	}
//	    		edVolta.setVl_estoque(conta);

//	    		estoque sem WMS
        edVolta.setVl_estoque (res.getDouble ("nr_quantidade"));
        if (res.getDouble ("nr_quantidade") <= edVolta.getVl_estoque_minimo ()) {
          list.add (edVolta);
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

    return list;
  }

  public void importa_BACA () throws Exception {

    String caminho = "/home/baca/";
    ManipulaArquivo man = new ManipulaArquivo ("");
    LineNumberReader line = man.leLinha (caminho + "p6.txt");
    String NM_Registro = null;
    int linha = 0;
    long oid_estoque = 0;
    int oid_g = 2;
    int oid_sg = 9;
    String sql = null;
    String cod = "";
    int valOid = 1;

    try {
      if (line.ready ()) {

        // System.out.println ("ready");

        ResultSet rs = executasql.executarConsulta ("SELECT MAX(OID_estoque) as result FROM estoques");
        while (rs.next ()) {
          oid_estoque = rs.getLong ("result");
        }

        while ( (NM_Registro = line.readLine ()) != null) {

          oid_estoque++;

          if (oid_g <= 9) {
            cod = "0" + String.valueOf (oid_g);
          }
          else {
            cod = String.valueOf (oid_g);
          }
          if (oid_sg <= 9) {
            cod += "0" + String.valueOf (oid_sg);
          }
          else {
            cod += String.valueOf (oid_sg);
          }
          if (valOid <= 99 && valOid > 9) {
            cod += "0" + String.valueOf (valOid);
          }
          else if (valOid <= 9) {
            cod += "00" + String.valueOf (valOid);
          }
          else {
            cod += String.valueOf (valOid);
          }
          // System.out.println ("nm 1: " + NM_Registro);
          NM_Registro = ManipulaString.tiraAspas (NM_Registro);
          // System.out.println (NM_Registro.length () + " nm 1: " + NM_Registro);
          if (NM_Registro.length () >= 40) {
            NM_Registro = NM_Registro.substring (0 , 39);
          }
          // System.out.println ("nm 1: " + NM_Registro);
          sql = "INSERT INTO Estoques (" +
              " oid_estoque," +
              " nm_estoque," +
              " dt_stamp," +
              " usuario_stamp," +
              " dm_stamp, dm_status, dm_tipo_produto," +
              " oid_unidade_produto," +
              " oid_sub_grupo_estoque," +
              " oid_grupo_estoque," +
              " cd_estoque " + ")";
          sql += " values ( ";
          sql += oid_estoque + ", '" + NM_Registro + "'," +
              "'" + Data.getDataDMY () + "','IMP','N', 'A', 'E',1," + oid_sg + "," + oid_g + ",'" + cod + "')";
          // System.out.println (sql);
          int u = executasql.executarUpdate (sql);

          linha++;

          valOid++;
          line.setLineNumber (linha);
        }
      }
      line.close ();

    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

  }

  public byte[] gera_Rel_Movimento_Estoque (EstoqueED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;

    sql = null;
    byte[] b = null;

    if (ed.getDM_Relatorio ().equals ("1")) {
      sql = " SELECT  movimentos_estoques.*, " +
          "         Ordens_Servicos.oid_Veiculo as NR_Placa,  " +
          "         Estoques.*   " +
          " FROM    movimentos_estoques, Estoques, Ordens_Servicos " +
          " WHERE   movimentos_estoques.oid_Estoque = Estoques.oid_Estoque " +
          " AND     Ordens_Servicos.oid_Ordem_Servico = movimentos_estoques.oid_Ordem_Servico ";
    }

    if (ed.getOid_estoque () > 0) {
      sql += " and movimentos_estoques.OID_Estoque = " +
          ed.getOid_estoque ();
    }
    if (String.valueOf (ed.getOid_fornecedor ()) != null &&
        !String.valueOf (ed.getOid_fornecedor ()).equals ("") &&
        !String.valueOf (ed.getOid_fornecedor ()).equals ("null")) {
      sql += " and movimentos_estoques.OID_Fornecedor = '" + ed.getOid_fornecedor () + "'";
    }
    if (String.valueOf (ed.getDT_Inicial ()) != null &&
        !String.valueOf (ed.getDT_Inicial ()).equals ("") &&
        !String.valueOf (ed.getDT_Inicial ()).equals ("null")) {
      sql += " and movimentos_estoques.dt_movimento >= '" + ed.getDT_Inicial () + "'";
    }
    if (String.valueOf (ed.getDT_Final ()) != null &&
        !String.valueOf (ed.getDT_Final ()).equals ("") &&
        !String.valueOf (ed.getDT_Final ()).equals ("null")) {
      sql += " and movimentos_estoques.dt_movimento <= '" + ed.getDT_Final () + "'";
    }
    if (String.valueOf (ed.getNR_Placa ()) != null &&
        !String.valueOf (ed.getNR_Placa ()).equals ("null") &&
        !String.valueOf (ed.getNR_Placa ()).equals ("A") &&
        !String.valueOf (ed.getNR_Placa ()).equals ("")) {
      sql += " and Ordens_Servicos.OID_Veiculo = '" + ed.getNR_Placa () + "'";
    }

    if ("E".equals (ed.getDM_Tipo_Movimento ())) {
      sql += " and movimentos_estoques.dm_movimento = 'E'";
    }

    if ("S".equals (ed.getDM_Tipo_Movimento ())) {
      sql += " and movimentos_estoques.dm_movimento = 'S'" +
          " and Ordens_Servicos.oid_Veiculo is not null ";
    }

    if (ed.getDM_Relatorio ().equals ("1")) {
      sql += "  ORDER BY  Ordens_Servicos.OID_Veiculo, movimentos_estoques.dt_movimento";
    }

    // System.out.println ("sql " + sql);

    return b;
  }

  public void entrada_Estoque (Item_Nota_Fiscal_CompraED ed, String fornecedor) throws Excecoes {

	    String sqlBusca = null;
	    EstoqueED edVolta = new EstoqueED ();

	    try {

	      edVolta.setOid_estoque (new Long(ed.getOID_Produto()).longValue ());
//	      edVolta = this.getByRecord (edVolta);
	      Movimento_EstoqueBean movimento_Est = new Movimento_EstoqueBean ();
	      movimento_Est.setDM_Movimento ("E");
	      movimento_Est.setDT_Movimento (Data.getDataDMY());
	      movimento_Est.setNR_Quantidade (ed.getVl_quantidade());
	      movimento_Est.setOID_Estoque (edVolta.getOid_estoque ());
	      movimento_Est.setVL_Unitario (ed.getVL_Produto());
	      movimento_Est.setNM_Fornecedor(fornecedor);
	      movimento_Est.setOid_Veiculo ("");
	      movimento_Est.insert ();

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro incluindo estoque !");
	      excecoes.setMetodo ("entrada_Estoque()");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }

	  }

  public ArrayList Relatorio_Itens_Estoque(EstoqueED ed) throws Excecoes {

		String sql = null;
		ArrayList list = new ArrayList();
	    FormataDataBean formataData = new FormataDataBean ();
		String tipo_rel="NORMAL";

		if ("ESTCON".equals((ed.getDM_Relatorio()+"             ").substring(0,6))){
			tipo_rel="CONTAGEM";
		}

		try {

			sql = " SELECT " +
				  " Estoques.*," +
				  " Grupos_Estoques.oid_grupo_estoque," +
				  " Grupos_Estoques.nm_grupo_estoque" +
				  " FROM  Estoques, Grupos_Estoques "+
				  " WHERE Estoques.oid_grupo_estoque = Grupos_Estoques.oid_grupo_estoque ";


		      if ("A".equals(ed.getDm_status()) || "AE".equals(ed.getDm_status()) || "AS".equals(ed.getDm_status())){
		          sql += " and Estoques.Dm_status = 'A'";
		      }
		      if ("I".equals(ed.getDm_status()) || "IE".equals(ed.getDm_status()) || "IS".equals(ed.getDm_status())){
		          sql += " and Estoques.Dm_status = 'I'";
		      }

		      if (ed.getDm_tipo_produto () != null && !ed.getDm_tipo_produto ().equals ("null")  && !ed.getDm_tipo_produto ().equals ("")) {
		          sql += " and Estoques.Dm_Tipo_Produto = '" + ed.getDm_tipo_produto() + "'";
		      }

		      if (ed.getNm_estoque () != null && !ed.getNm_estoque ().equals ("null") && !ed.getNm_estoque ().equals ("")) {
		        sql += "and Estoques.nm_estoque LIKE '" + ed.getNm_estoque () + "%'";
		      }

		      if (ed.getNm_referencia () != null && !ed.getNm_referencia ().equals ("null") && !ed.getNm_referencia ().equals ("")) {
		        sql += "and Estoques.nm_referencia_fabrica LIKE '" + ed.getNm_referencia () + "%'";
		      }

		      if (String.valueOf (ed.getOid_grupo_estoque ()) != null && !String.valueOf (ed.getOid_grupo_estoque ()).equals ("null")
		          && !String.valueOf (ed.getOid_grupo_estoque ()).equals ("") && !String.valueOf (ed.getOid_grupo_estoque ()).equals ("0")) {
		        sql += "and Estoques.Oid_grupo_estoque = " + ed.getOid_grupo_estoque () + "";
		      }

		      if (ed.getCD_Deposito () != null && !ed.getCD_Deposito ().equals ("null")  && !ed.getCD_Deposito ().equals ("")) {
		          sql += " and Estoques.CD_Deposito = '" + ed.getCD_Deposito() + "'";
		      }
		      if (ed.getNM_Rua () != null && !ed.getNM_Rua ().equals ("null")  && !ed.getNM_Rua ().equals ("")) {
		          sql += " and Estoques.NM_Rua = '" + ed.getNM_Rua() + "'";
		      }
		      if (ed.getNR_Endereco () != null && !ed.getNR_Endereco ().equals ("null")  && !ed.getNR_Endereco ().equals ("")) {
		          sql += " and Estoques.NR_Endereco = '" + ed.getNR_Endereco() + "'";
		      }
		      if (ed.getNR_Apartamento () != null && !ed.getNR_Apartamento ().equals ("null")  && !ed.getNR_Apartamento ().equals ("")) {
		          sql += " and Estoques.NR_Apartamento = '" + ed.getNR_Apartamento() + "'";
		      }

		      if ("CONTAGEM".equals(tipo_rel)) {
		    	  String DT_Contagem="";
		    	  if ("C".equals(ed.getDM_Divergencia())) {
		    		  sql += " and Estoques.nr_divergencia_ultima_contagem <>0 AND DT_Ultima_Contagem IS NOT NULL";
		    	  }
		    	  if ("S".equals(ed.getDM_Divergencia())) {
		    		  sql += " and Estoques.nr_divergencia_ultima_contagem =0 AND DT_Ultima_Contagem IS NOT NULL";
		    	  }
		    	  if (ed.getNR_Dias_Contagem() ==0) { //nao contados
		    		  sql += " and  Estoques.DT_Ultima_Contagem IS NULL";
		    	  }

		    	  if (ed.getNR_Dias_Contagem() ==1) { //
		    		  DT_Contagem=Data.getDataDMY();
		    	  }

		    	  if (ed.getNR_Dias_Contagem() >1 && ed.getNR_Dias_Contagem()<=60) { //at� 5 dias a 60 dias
		    		  DT_Contagem=Data.getSomaDiaData(Data.getDataDMY(), -ed.getNR_Dias_Contagem());
		    	  }
					System.out.println("DT_Contagem="+DT_Contagem);

		    	  if (ed.getNR_Dias_Contagem() ==61) { //
		    		  DT_Contagem=Data.getSomaDiaData(Data.getDataDMY(), -100);
		    	  }

		    	  if (ed.getNR_Dias_Contagem() ==9999) { //contados
		    		  sql += " and  Estoques.DT_Ultima_Contagem IS NOT NULL";
		    	  }

		    	  if (!"".equals(DT_Contagem)) {
		    		  sql += " and Estoques.DT_Ultima_Contagem >='" + DT_Contagem +"'";
		    	  }
		      }


		    if ("EST1".equals(ed.getDM_Relatorio())) {
				sql += " ORDER BY " +
					   " Grupos_Estoques.nm_grupo_estoque, " +
					   " Estoques.nm_estoque";
		    }
		    if ("ESTCON1".equals(ed.getDM_Relatorio())) {
				sql += " ORDER BY " +
					   " Grupos_Estoques.nm_grupo_estoque, " +
					   " Estoques.nm_estoque";
		    }
		    if ("ESTCON2".equals(ed.getDM_Relatorio())) {
				sql += " ORDER BY " +
					   " Estoques.CD_Deposito, " +
					   " Estoques.NM_Rua, " +
					   " Estoques.NR_Endereco, " +
					   " Estoques.NR_Apartamento, " +
					   " Estoques.nm_estoque";
		    }

			System.out.println(sql);

			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				EstoqueED edVolta = new EstoqueED();

				edVolta.setNM_Grupo_Estoque(res.getString("nm_grupo_estoque"));
				edVolta.setOid_estoque(new Long(res.getString("oid_estoque")).longValue());
				edVolta.setCd_estoque(res.getString("cd_estoque"));
				edVolta.setNm_estoque(res.getString("nm_estoque"));

		        formataData.setDT_FormataData (res.getString ("DT_Ultima_Contagem"));
		        edVolta.setDT_Ultima_Contagem(formataData.getDT_FormataData ());

				edVolta.setNR_Divergencia_Ultima_Contagem(res.getDouble("NR_Divergencia_Ultima_Contagem")*-1);

				edVolta.setNm_referencia(res.getString("nm_referencia_fabrica"));
				if("null".equals(edVolta.getNm_referencia()))
					edVolta.setNm_referencia(" ");

				edVolta.setCD_Deposito(res.getString("cd_deposito"));
				if("null".equals(edVolta.getCD_Deposito()))
					edVolta.setCD_Deposito(" ");

				edVolta.setNM_Rua(res.getString("nm_rua"));
				if("null".equals(edVolta.getNM_Rua()))
					edVolta.setNM_Rua(" ");

				edVolta.setNR_Endereco(res.getString("nr_endereco"));
				if("null".equals(edVolta.getNR_Endereco()))
					edVolta.setNR_Endereco(" ");

				edVolta.setNR_Apartamento(res.getString("nr_apartamento"));
				if("null".equals(edVolta.getNR_Apartamento()))
					edVolta.setNR_Apartamento(" ");


				edVolta.setVl_Unitario(res.getDouble("vl_custo"));

			    edVolta = this.contaEstoque(edVolta);

			    if ("CONTAGEM".equals(tipo_rel)) {
			    	sql =" SELECT oid_Contagem_Estoque, nr_quantidade_estoque, nr_quantidade_contagem FROM Contagens_Estoques WHERE oid_Contagem_Estoque='" + res.getString ("oid_Contagem_Estoque")+"'";
					ResultSet resCont = this.executasql.executarConsulta(sql);
					if (resCont.next()) {
						edVolta.setVl_estoque(resCont.getDouble("nr_quantidade_estoque"));
				        ed.setVl_Quantidade (resCont.getDouble("nr_quantidade_estoque"));
						edVolta.setNR_Quantidade_Contagem(resCont.getDouble("nr_quantidade_contagem"));

						//sql =" UPDATE Estoques SET oid_Contagem_Estoque='" + resCont.getString("oid_Contagem_Estoque")+ "' WHERE oid_Estoque='" + res.getString ("oid_Estoque")+"'";
				        //int u = executasql.executarUpdate (sql);

					}
			    }


			    String Cabecalho_Titulo="...";
				if ("T".equals(ed.getDM_Divergencia())) {
					Cabecalho_Titulo="Todos";
				}
				if ("S".equals(ed.getDM_Divergencia())) {
					Cabecalho_Titulo="Sem Divergencia";
				}
				if ("C".equals(ed.getDM_Divergencia())) {
					Cabecalho_Titulo="Com Divergencia";
				}
				if (ed.getNR_Dias_Contagem()==0) {
					Cabecalho_Titulo+="/N�o Contados";
				}
				if (ed.getNR_Dias_Contagem()==1) {
					Cabecalho_Titulo+="/Contados Hoje";
				}
				if (ed.getNR_Dias_Contagem()==5) {
					Cabecalho_Titulo+="/Contados h� 5 dias";
				}
				if (ed.getNR_Dias_Contagem()==10) {
					Cabecalho_Titulo+="/Contados h� 10 dias";
				}
				if (ed.getNR_Dias_Contagem()==20) {
					Cabecalho_Titulo+="/Contados h� 20 dias";
				}
				if (ed.getNR_Dias_Contagem()==30) {
					Cabecalho_Titulo+="/Contados h� 30 dias";
				}
				if (ed.getNR_Dias_Contagem()==45) {
					Cabecalho_Titulo+="/Contados h� 45 dias";
				}
				if (ed.getNR_Dias_Contagem()==60) {
					Cabecalho_Titulo+="/Contados h� 60 dias";
				}
				if (ed.getNR_Dias_Contagem()==601) {
					Cabecalho_Titulo+="/Contados h� mais que 60 dias";
				}
				if (ed.getNR_Dias_Contagem()==9999) {
					Cabecalho_Titulo+="/Todos Contados";
				}
				edVolta.setCabecalho_Titulo(Cabecalho_Titulo);


				boolean lista=true;
				if ("CONTAGEM".equals(tipo_rel) && ed.getNR_Dias_Contagem() ==0 && edVolta.getVl_Quantidade()==0) { //nao contados
					lista=false;  //nao precisa listar contagem =0 e sem estoque
				}
			    if ("AE".equals(ed.getDm_status()) && edVolta.getVl_Quantidade()<=0){
					lista=false;  //nao precisa listar contagem =0 e sem estoque
			    }
			    if ("AS".equals(ed.getDm_status()) && edVolta.getVl_Quantidade()>0){
					lista=false;  //nao precisa listar contagem =0 e sem estoque
			    }
			    if ("IE".equals(ed.getDm_status()) && edVolta.getVl_Quantidade()<=0){
					lista=false;  //nao precisa listar contagem =0 e sem estoque
			    }
			    if ("IS".equals(ed.getDm_status()) && edVolta.getVl_Quantidade()>0){
					lista=false;  //nao precisa listar contagem =0 e sem estoque
			    }


				if (lista) list.add(edVolta);
			}

			return list;

		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "Relatorio_Itens_Estoque(EstoqueED ed)");
		}
	}


  public EstoqueED buscaProdutoImportacao(EstoqueED ed) throws Excecoes {

	    String sql = null;
	    String sqlBusca = null;
	    ArrayList list = new ArrayList ();
	    EstoqueED edVolta = new EstoqueED ();

	    try {

	      sql = " select * from estoques";
	      sql += " where 1 =1 ";
	      sql += " and nm_estoque LIKE '" + ed.getNm_estoque() + "%'";
	      sql += " or nm_referencia_fabrica LIKE '" + ed.getNm_referencia () + "%'";
	      sql += " or nm_referencia_fabrica2 LIKE '" + ed.getNm_referencia () + "%'";
	      sql += " or nm_referencia_fabrica3 LIKE '" + ed.getNm_referencia () + "%'";
	      sql += " or nm_referencia_fabrica4 LIKE '" + ed.getNm_referencia () + "%'";
	      sql += " or nm_referencia_fabrica5 LIKE '" + ed.getNm_referencia () + "%'";
	      sql += " or nm_referencia_fabrica6 LIKE '" + ed.getNm_referencia () + "%'";

	      sql += "order by nm_estoque, cd_estoque limit 1 ";

	      ResultSet res = null;

	      res = this.executasql.executarConsulta (sql);
	      while (res.next ()) {
	        double conta = 0;
	        edVolta = new EstoqueED ();
	        edVolta.setOid_estoque (res.getLong ("oid_estoque"));
	        edVolta.setCd_estoque (res.getString ("Cd_estoque"));
	        edVolta.setNm_estoque (res.getString ("Nm_estoque"));
	    	edVolta.setNm_referencia (" ");
	        if (res.getString ("Nm_referencia_fabrica") != null && !res.getString ("Nm_referencia_fabrica").equals("null")) {
	        	edVolta.setNm_referencia (res.getString ("Nm_referencia_fabrica"));
	        }
	        edVolta.setVl_custo (res.getDouble ("Vl_custo"));
	        edVolta.setDt_stamp (res.getString ("Dt_stamp"));
	        edVolta.setOid_unidade_produto (res.getLong ("Oid_unidade_produto"));
	        edVolta.setOid_sub_grupo_estoque (res.getInt ("Oid_sub_grupo_estoque"));
	        edVolta.setOid_grupo_estoque (res.getInt ("Oid_grupo_estoque"));
	        edVolta.setVl_custo_minimo (res.getDouble ("Vl_custo_minimo"));
	        edVolta.setVl_custo_maximo (res.getDouble ("Vl_custo_maximo"));
	        edVolta.setVl_estoque_minimo (res.getDouble ("Vl_estoque_minimo"));
	        edVolta.setVl_estoque_maximo (res.getDouble ("Vl_estoque_maximo"));
	        if (res.getString ("Dm_status") != null && res.getString ("Dm_status").equals ("I")) {
	          edVolta.setDm_status ("Inativo");
	        }
	        else {
	          edVolta.setDm_status ("Ativo");
	        }
	        if (res.getString ("Dm_tipo_produto") != null && res.getString ("Dm_tipo_produto").equals ("D")) {
	          edVolta.setDm_tipo_produto ("Diverso");
	        }
	        else if (res.getString ("Dm_tipo_produto") != null && res.getString ("Dm_tipo_produto").equals ("R")) {
	          edVolta.setDm_tipo_produto ("Remanufaturado");
	        }
	        else if (res.getString ("Dm_tipo_produto") != null && res.getString ("Dm_tipo_produto").equals ("P")) {
	          edVolta.setDm_tipo_produto ("Pneu Usado/Recap.");
	        }
	        else {
	          edVolta.setDm_tipo_produto ("Estoque");
	        }
	        edVolta.setNm_classificacao_fiscal (res.getString ("Nm_classificacao_fiscal"));
	        edVolta.setDm_negativo (res.getString ("Dm_negativo"));
	        edVolta.setNr_leadtime (res.getLong ("Nr_leadtime"));
	        edVolta.setNr_leadtime_maximo (res.getLong ("Nr_leadtime_maximo"));
	        edVolta.setOid_produto (res.getLong ("Oid_produto"));

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


}
