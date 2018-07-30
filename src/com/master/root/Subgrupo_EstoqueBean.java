package com.master.root;

import java.sql.*;
import java.util.*;
import auth.*;

public class Subgrupo_EstoqueBean {
  private int CD_Subgrupo_Estoque;
  private String NM_Subgrupo_Estoque;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid_Grupo_Estoque;
  private String NM_Grupo_Estoque;
  private int oid;
  private String dia_compra;

  public Subgrupo_EstoqueBean () {}

  public int getCD_Subgrupo_Estoque () {
    return CD_Subgrupo_Estoque;
  }

  public void setCD_Subgrupo_Estoque (int subgrupo_Estoque) {
    CD_Subgrupo_Estoque = subgrupo_Estoque;
  }

  public String getNM_Subgrupo_Estoque () {
    return NM_Subgrupo_Estoque;
  }

  public void setNM_Subgrupo_Estoque (String subgrupo_Estoque) {
    NM_Subgrupo_Estoque = subgrupo_Estoque;
  }

  public int getOid_Grupo_Estoque () {
    return oid_Grupo_Estoque;
  }

  public void setOid_Grupo_Estoque (int oid_Grupo_Estoque) {
    this.oid_Grupo_Estoque = oid_Grupo_Estoque;
  }

  public String getNM_Grupo_Estoque () {
    return NM_Grupo_Estoque;
  }

  public void setNM_Grupo_Estoque (String grupo_Estoque) {
    NM_Grupo_Estoque = grupo_Estoque;
  }

  public String getDia_compra () {
    return dia_compra;
  }

  public void setDia_compra (String dia_compra) {
    this.dia_compra = dia_compra;
  }

  /*
   *---------------- Bloco Padrão para Todas Classes ------------------
   */
  public String getUsuario_Stamp () {
    return Usuario_Stamp;
  }

  public void setUsuario_Stamp (String Usuario_Stamp) {
    this.Usuario_Stamp = Usuario_Stamp;
  }

  public String getDt_Stamp () {
    return Dt_Stamp;
  }

  public void setDt_Stamp (String Dt_Stamp) {
    this.Dt_Stamp = Dt_Stamp;
  }

  public String getDm_Stamp () {
    return Dm_Stamp;
  }

  public void setDm_Stamp (String Dm_Stamp) {
    this.Dm_Stamp = Dm_Stamp;
  }

  public int getOID () {
    return oid;
  }

  public void setOID (int n) {
    this.oid = n;
  }

  public void insert () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Grupo_Estoque do DSN
      // o NM_Grupo_Estoque de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    /*
     * Define o insert.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("INSERT INTO Sub_Grupos_Estoques (OID_Sub_Grupo_Estoque, NM_Sub_Grupo_Estoque, Dt_Stamp, Usuario_Stamp, Dm_Stamp, oid_grupo_estoque, nr_proximo_item, dia_compra) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setString (2 , getNM_Subgrupo_Estoque ());
      pstmt.setString (3 , getDt_Stamp ());
      pstmt.setString (4 , getUsuario_Stamp ());
      pstmt.setString (5 , getDm_Stamp ());
      pstmt.setInt (6 , getOid_Grupo_Estoque ());
      pstmt.setInt (7 , 1);
      pstmt.setString (8 , getDia_compra ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conexão.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void update () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Grupo_Estoque do DSN
      // o NM_Grupo_Estoque de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Define o update.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Sub_Grupos_Estoques SET NM_Sub_Grupo_Estoque=?, Dt_Stamp=?, dia_compra=? ");
    buff.append ("WHERE OID_Sub_Grupo_Estoque=? and OID_Grupo_Estoque=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getNM_Subgrupo_Estoque ());
      pstmt.setString (2 , getDt_Stamp ());
      pstmt.setString (3 , getDia_compra ());
      pstmt.setInt (4 , getOID ());
      pstmt.setInt (5 , getOid_Grupo_Estoque ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conexão.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void delete () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Grupo_Estoque do DSN
      // o NM_Grupo_Estoque de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Define o DELETE.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("DELETE FROM Sub_Grupos_Estoques ");
    buff.append ("WHERE OID_Sub_Grupo_Estoque=?");
    buff.append (" AND OID_Grupo_Estoque=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setInt (2 , getOid_Grupo_Estoque ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conexão.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public static final Subgrupo_EstoqueBean getByOID (Subgrupo_EstoqueBean sgrupo) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Grupo_Estoque do DSN
      // o NM_Grupo_Estoque de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Subgrupo_EstoqueBean p = new Subgrupo_EstoqueBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Sub_Grupos_Estoques.OID_Sub_Grupo_Estoque, Sub_Grupos_Estoques.OID_Grupo_Estoque,");
      buff.append ("Sub_Grupos_Estoques.NM_Sub_Grupo_Estoque, Grupos_Estoques.NM_Grupo_Estoque, Sub_Grupos_Estoques.dia_compra ");
      buff.append ("FROM Sub_Grupos_Estoques, Grupos_Estoques WHERE Sub_Grupos_Estoques.OID_Grupo_Estoque = Grupos_Estoques.OID_Grupo_Estoque");
      buff.append (" AND Sub_Grupos_Estoques.OID_Sub_Grupo_Estoque= ");
      buff.append (sgrupo.getOID ());
      buff.append (" AND Sub_Grupos_Estoques.OID_Grupo_Estoque= ");
      buff.append (sgrupo.getOid_Grupo_Estoque ());

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOid_Grupo_Estoque (cursor.getInt (2));
        p.setNM_Subgrupo_Estoque (cursor.getString (3));
        p.setNM_Grupo_Estoque (cursor.getString (4));
        p.setDia_compra (cursor.getString (5));
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return p;
  }

  public static final List getByNM_Subgrupo_Estoque (Subgrupo_EstoqueBean sgrupo) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Grupo_Estoque do DSN
      // o NM_Grupo_Estoque de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Subgrupos_Estoques_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Sub_Grupos_Estoques.OID_Sub_Grupo_Estoque, Sub_Grupos_Estoques.OID_Grupo_Estoque,");
      buff.append ("Sub_Grupos_Estoques.NM_Sub_Grupo_Estoque, Grupos_Estoques.NM_Grupo_Estoque, Sub_Grupos_Estoques.dia_compra ");
      buff.append ("FROM Sub_Grupos_Estoques, Grupos_Estoques WHERE Sub_Grupos_Estoques.OID_Grupo_Estoque = Grupos_Estoques.OID_Grupo_Estoque");
      if (sgrupo.getNM_Subgrupo_Estoque () != null && !sgrupo.getNM_Subgrupo_Estoque ().equals ("null")
          && !sgrupo.getNM_Subgrupo_Estoque ().equals ("")) {
        buff.append (" AND NM_Sub_Grupo_Estoque LIKE'");
        buff.append (sgrupo.getNM_Subgrupo_Estoque ());
        buff.append ("%'");
      }
      if (sgrupo.getOid_Grupo_Estoque () != 0) {
        buff.append (" AND Sub_Grupos_Estoques.OID_Grupo_Estoque ='");
        buff.append (sgrupo.getOid_Grupo_Estoque ());
        buff.append ("'");
      }

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Subgrupo_EstoqueBean p = new Subgrupo_EstoqueBean ();
        p.setOID (cursor.getInt (1));
        p.setOid_Grupo_Estoque (cursor.getInt (2));
        p.setNM_Subgrupo_Estoque (cursor.getString (3));
        p.setNM_Grupo_Estoque (cursor.getString (4));
        p.setDia_compra (cursor.getString (5));
        Subgrupos_Estoques_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Subgrupos_Estoques_Lista;
  }

  public static final List getAll () throws Exception {
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o nome do DSN
      // o nome de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Subgrupos_Estoques_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Sub_Grupo_Estoque, ");
      buff.append ("	NM_Sub_Grupo_Estoque ");
      buff.append ("FROM Sub_Grupos_Estoques");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Subgrupo_EstoqueBean p = new Subgrupo_EstoqueBean ();
        p.setOID (cursor.getInt (1));
        p.setNM_Subgrupo_Estoque (cursor.getString (2));
        Subgrupos_Estoques_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Subgrupos_Estoques_Lista;
  }

  public void getMax (int grupo_estoque) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Grupo_Estoque do DSN
      // o NM_Grupo_Estoque de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Gera um novo código (OID)
     */
    try {
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (
          "SELECT MAX(OID_Sub_Grupo_Estoque) FROM Sub_Grupos_Estoques WHERE oid_grupo_estoque ='" +
          grupo_estoque + "'");

      while (cursor.next ()) {
        int oid = cursor.getInt (1);
        setOID (oid + 1);
      }
      cursor.close ();
      stmt.close ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }

    try {
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public static void main (String args[]) throws Exception {

  }
}