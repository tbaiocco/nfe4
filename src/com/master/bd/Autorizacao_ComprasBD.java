package com.master.bd;

/**
 * <p>Title: Autorizacao_ComprasBD </p>
 * <p>Description: Cadastro, exclusão e alteração de Autorizadores. Gerenciamento de autorizações
 * de Solicitações e requisições.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2004</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.ed.Autorizacao_ComprasED;
import java.util.*;
import java.sql.*;

import com.master.root.FormataDataBean;

public class Autorizacao_ComprasBD {

  private ExecutaSQL executasql;

  public Autorizacao_ComprasBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Autorizacao_ComprasED inclui_autorizador (Autorizacao_ComprasED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = null;
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      ResultSet rs = executasql.executarConsulta ("select max(oid_autorizador) as result from autorizadores_compras ");

      //pega proximo valor da chave
      while (rs.next ()) {
        valOid = rs.getLong ("result");
        ed.setOid_Autorizador (++valOid);
      }

      sql = " insert into autorizadores_compras (oid_autorizador, " +
          "oid_perfil_compra, oid_usuario, vl_alcada, " +
          "dt_stamp, usuario_stamp, dm_stamp, dt_vencimento) values ";
      sql += "(" + ed.getOid_Autorizador () + "," + ed.getOid_Perfil_Compra () + "," +
          ed.getOid_Usuario () + "," + ed.getVL_Alcada () + ",'" + ed.getDt_stamp () + "','" + ed.getUsuario_Stamp () + "','" + ed.getDm_Stamp () + "','" +
          ed.getDT_Vencimento () + "')";
//////// // System.out.println(sql);
      int i = executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui autorizador");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }

  public void altera_autorizador (Autorizacao_ComprasED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "update autorizadores_compras " +
          "set vl_alcada =" + ed.getVL_Alcada () +
          ", dt_vencimento = '" + ed.getDT_Vencimento () +
          "', oid_perfil_compra = " + ed.getOid_Perfil_Compra () +
          ", dt_stamp ='" + Data.getDataDMY () + "'" +
          ", usuario_stamp ='" + ed.getUsuario_Stamp () + "'" +
          ", dm_stamp ='S' " +
          "Where OID_autorizador =" + ed.getOid_Autorizador ();

      //////// // System.out.println(sql);
      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar autorizador");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta_autorizador (Autorizacao_ComprasED ed) throws Excecoes {

    String sql = null;
    String DM_Impresso = null;

    try {
      sql = "delete from autorizadores_compras Where oid_autorizador =" + ed.getOid_Autorizador ();
//////// // System.out.println(sql);
      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("excluir autorizador");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista_autorizadores (Autorizacao_ComprasED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select * " +
          "From  autorizadores_compras " +
          "Where 1=1 ";

      if (String.valueOf (ed.getOid_Autorizador ()) != null &&
          !String.valueOf (ed.getOid_Autorizador ()).equals ("") &&
          !String.valueOf (ed.getOid_Autorizador ()).equals ("null") &&
          !String.valueOf (ed.getOid_Autorizador ()).equals ("0")) {
        sql += " AND oid_autorizador = '" + ed.getOid_Autorizador () + "'";
      }
      if (String.valueOf (ed.getOid_Usuario ()) != null &&
          !String.valueOf (ed.getOid_Usuario ()).equals ("") &&
          !String.valueOf (ed.getOid_Usuario ()).equals ("null") &&
          !String.valueOf (ed.getOid_Usuario ()).equals ("0")) {
        sql += " AND oid_usuario = '" + ed.getOid_Usuario () + "'";
      }
      if (String.valueOf (ed.getOid_Perfil_Compra ()) != null &&
          !String.valueOf (ed.getOid_Perfil_Compra ()).equals ("") &&
          !String.valueOf (ed.getOid_Perfil_Compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_Perfil_Compra ()).equals ("0")) {
        sql += " AND oid_perfil_compra = '" + ed.getOid_Perfil_Compra () + "'";
      }
      sql += " ORDER BY OID_autorizador";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;

      FormataDataBean DataFormatada = new FormataDataBean ();

      //popula
      while (res.next ()) {
        Autorizacao_ComprasED edVolta = new Autorizacao_ComprasED ();
        edVolta.setOid_Autorizador (res.getLong ("oid_autorizador"));
        edVolta.setOid_Perfil_Compra (res.getLong ("oid_perfil_compra"));
        edVolta.setVL_Alcada (res.getDouble ("vl_alcada"));
        edVolta.setDT_Vencimento (res.getString ("dt_vencimento"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Vencimento ());
        edVolta.setDT_Vencimento (DataFormatada.getDT_FormataData ());

        edVolta.setOid_Usuario (res.getLong ("OID_usuario"));

        sql = "Select * from perfis_compras WHERE OID_perfil_compra = " + edVolta.getOid_Perfil_Compra ();
//////// // System.out.println(sql);
        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);
        while (resLocal.next ()) {
          edVolta.setNM_Perfil_Compra (resLocal.getString ("NM_Perfil_Compra"));
        }
        sql = "Select * from usuarios WHERE OID_usuario = " + edVolta.getOid_Usuario ();
////////// System.out.println(sql);
        resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);
        while (resLocal.next ()) {
          edVolta.setNM_Usuario (resLocal.getString ("NM_Usuario"));
        }
        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar autorizador");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Autorizacao_ComprasED getByRecord_autorizador (Autorizacao_ComprasED ed) throws Excecoes {

    String sql = null;
    Autorizacao_ComprasED edVolta = new Autorizacao_ComprasED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select * " +
          "From  autorizadores_compras " +
          "Where 1=1 ";

      if (String.valueOf (ed.getOid_Autorizador ()) != null &&
          !String.valueOf (ed.getOid_Autorizador ()).equals ("") &&
          !String.valueOf (ed.getOid_Autorizador ()).equals ("null") &&
          !String.valueOf (ed.getOid_Autorizador ()).equals ("0")) {
        sql += " AND oid_autorizador = '" + ed.getOid_Autorizador () + "'";
      }
      if (String.valueOf (ed.getOid_Usuario ()) != null &&
          !String.valueOf (ed.getOid_Usuario ()).equals ("") &&
          !String.valueOf (ed.getOid_Usuario ()).equals ("null") &&
          !String.valueOf (ed.getOid_Usuario ()).equals ("0")) {
        sql += " AND oid_usuario = '" + ed.getOid_Usuario () + "'";
      }
      if (String.valueOf (ed.getOid_Perfil_Compra ()) != null &&
          !String.valueOf (ed.getOid_Perfil_Compra ()).equals ("") &&
          !String.valueOf (ed.getOid_Perfil_Compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_Perfil_Compra ()).equals ("0")) {
        sql += " AND oid_perfil_compra = '" + ed.getOid_Perfil_Compra () + "'";
      }
      sql += " ORDER BY OID_autorizador";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();
      //popula
      while (res.next ()) {
        edVolta.setOid_Autorizador (res.getLong ("oid_autorizador"));
        edVolta.setOid_Perfil_Compra (res.getLong ("oid_perfil_compra"));
        edVolta.setVL_Alcada (res.getDouble ("vl_alcada"));
        edVolta.setDT_Vencimento (res.getString ("dt_vencimento"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Vencimento ());
        edVolta.setDT_Vencimento (DataFormatada.getDT_FormataData ());
        edVolta.setOid_Usuario (res.getLong ("OID_usuario"));

        sql = "Select * from perfis_compras WHERE OID_perfil_compra = " + edVolta.getOid_Perfil_Compra ();
        ////////// System.out.println(sql);
        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);
        while (resLocal.next ()) {
          edVolta.setNM_Perfil_Compra (resLocal.getString ("NM_Perfil_Compra"));
        }
        sql = "Select * from usuarios WHERE OID_usuario = " + edVolta.getOid_Usuario ();
        ////////// System.out.println(sql);
        resLocal = null;
        resLocal = this.executasql.executarConsulta (sql);
        while (resLocal.next ()) {
          edVolta.setNM_Usuario (resLocal.getString ("NM_Usuario"));
        }
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar autorizador");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void inclui_perfil (Autorizacao_ComprasED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = null;
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      ResultSet rs = executasql.executarConsulta ("select max(oid_perfil_compra) as result from perfis_compras ");

      //pega proximo valor da chave
      while (rs.next ()) {
        valOid = rs.getLong ("result");
        ed.setOid_Perfil_Compra (++valOid);
      }

      sql = " insert into perfis_compras (oid_perfil_compra, nm_perfil_compra, " +
          "dt_stamp, usuario_stamp, dm_stamp) values ";
      sql += "(" + ed.getOid_Perfil_Compra () + ",'" + ed.getNM_Perfil_Compra () + "','" +
          ed.getDt_stamp () + "','" + ed.getUsuario_Stamp () + "','" + ed.getDm_Stamp () + "')";
////////   // System.out.println(sql);
      int i = executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui perfil");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void altera_perfil (Autorizacao_ComprasED ed) throws Excecoes {

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "update perfis_compras " +
          "set nm_perfil_compra = '" + ed.getNM_Perfil_Compra () +
          "', dt_stamp ='" + Data.getDataDMY () + "'" +
          ", usuario_stamp ='" + ed.getUsuario_Stamp () + "'" +
          ", dm_stamp ='S' " +
          "Where OID_perfil_compra =" + ed.getOid_Perfil_Compra ();

      //////// // System.out.println(sql);
      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar perfil");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta_perfil (Autorizacao_ComprasED ed) throws Excecoes {

    String sql = null;
    String DM_Impresso = null;

    try {
      sql = "delete from perfis_compras Where oid_perfil_compra =" + ed.getOid_Perfil_Compra ();
////////   // System.out.println(sql);
      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("excluir perfil");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista_perfis (Autorizacao_ComprasED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select * " +
          "From  perfis_compras " +
          "Where 1=1 ";
      if (String.valueOf (ed.getOid_Perfil_Compra ()) != null &&
          !String.valueOf (ed.getOid_Perfil_Compra ()).equals ("") &&
          !String.valueOf (ed.getOid_Perfil_Compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_Perfil_Compra ()).equals ("0")) {
        sql += " AND oid_perfil_compra = '" + ed.getOid_Perfil_Compra () + "'";
      }
      if (ed.getNM_Perfil_Compra () != null &&
          !ed.getNM_Perfil_Compra ().equals ("") &&
          !ed.getNM_Perfil_Compra ().equals ("null")) {
        sql += " AND nm_perfil_compra LIKE '" + ed.getNM_Perfil_Compra () + "%'";
      }
      sql += " ORDER BY OID_perfil_compra";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      //popula
      while (res.next ()) {
        Autorizacao_ComprasED edVolta = new Autorizacao_ComprasED ();
        edVolta.setOid_Perfil_Compra (res.getLong ("oid_perfil_compra"));
        edVolta.setNM_Perfil_Compra (res.getString ("NM_Perfil_Compra"));
        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar perfil");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Autorizacao_ComprasED getByRecord_perfil (Autorizacao_ComprasED ed) throws Excecoes {

    String sql = null;
    Autorizacao_ComprasED edVolta = new Autorizacao_ComprasED ();
    FormataDataBean dataFormatada = new FormataDataBean ();

    try {
      sql = "Select * " +
          "From  perfis_compras " +
          "Where 1=1 ";
      if (String.valueOf (ed.getOid_Perfil_Compra ()) != null &&
          !String.valueOf (ed.getOid_Perfil_Compra ()).equals ("") &&
          !String.valueOf (ed.getOid_Perfil_Compra ()).equals ("null") &&
          !String.valueOf (ed.getOid_Perfil_Compra ()).equals ("0")) {
        sql += " AND oid_perfil_compra = '" + ed.getOid_Perfil_Compra () + "'";
      }
      sql += " ORDER BY OID_perfil_compra";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      double valor = 0;
      //popula
      while (res.next ()) {
        edVolta.setOid_Perfil_Compra (res.getLong ("oid_perfil_compra"));
        edVolta.setNM_Perfil_Compra (res.getString ("NM_Perfil_Compra"));

      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar perfil");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public boolean getByEncrypt (String chave , String usuario , long oid) throws Excecoes {
    String sql = null;
    String chave_privada = "";
    boolean ok = false;
    String us_sol = null;

    try {
      //////// // System.out.println("entrou no bd");
      int tam = chave.length ();
      int tam0 = ( (tam - 28) / 2);
      int tam1 = 14 + ( (tam - 28) / 2);
      int tam2 = 14 + ( (tam - 28) / 3);

      String senhaR = chave.substring (0 , tam0);
      String senhaL = chave.substring (tam1 , tam2 + tam0);
      String senhaM = chave.substring ( (tam2 + 14) + tam0 , tam);

      chave_privada = senhaL + senhaM + senhaR;

      sql = " select * from usuarios " +
          " WHERE  UPPER(nm_senha) = UPPER('" + chave_privada + "') and UPPER(cd_usuario) = UPPER('" + usuario + "')";
      ResultSet res = null;
////////     // System.out.println(sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        us_sol = res.getString ("oid_usuario");
        sql = "select oid_usuario from solicitacoes_compras where oid_solicitacao_compra = " + oid;
        ResultSet rs = null;
        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          if (us_sol.equals (rs.getString (1))) {
            ok = true;
          }
          else {
            ok = false;
          }
        }
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recuperar usuario");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ok;
  }

}