package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class Tipo_VeiculoBean {
  private String CD_Tipo_Veiculo;
  private String NM_Tipo_Veiculo;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private String DM_Tipo_Implemento;

  // Primeiro indice no array é zero, portanto, não deve ser cadastrado o zero, por isso foi informado vazio
  private static final String[] Tipos_Implementos = {
      "" , "CAVALO" , "CARRETA" , "DOLLY" , "BITREM" , "TOCO" , "TRUCK" , "LEVE" , "PASSEIO" , "ONIBUS", "MOTO"};

  public Tipo_VeiculoBean () {}

  public String getCD_Tipo_Veiculo () {
    return CD_Tipo_Veiculo;
  }

  public void setCD_Tipo_Veiculo (String CD_Tipo_Veiculo) {
    this.CD_Tipo_Veiculo = CD_Tipo_Veiculo;
  }

  public String getNM_Tipo_Veiculo () {
    return NM_Tipo_Veiculo;
  }

  public void setNM_Tipo_Veiculo (String NM_Tipo_Veiculo) {
    this.NM_Tipo_Veiculo = NM_Tipo_Veiculo;
  }

  public String getDM_Tipo_Implemento () {
    return DM_Tipo_Implemento;
  }

  public void setDM_Tipo_Implemento (String tipo_Implemento) {
    DM_Tipo_Implemento = tipo_Implemento;
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
      // passando como parâmetro o NM_Tipo_Veiculo do DSN
      // o NM_Tipo_Veiculo de usuário e a senha do banco.
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
          "SELECT MAX(OID_Tipo_Veiculo) FROM Tipos_Veiculos");

      while (cursor.next ()) {
        int oid = cursor.getInt (1);
        setOID (oid + 1);
      }
      cursor.close ();
      stmt.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Define o insert.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("INSERT INTO Tipos_Veiculos (OID_Tipo_Veiculo, CD_Tipo_Veiculo, NM_Tipo_Veiculo, Dt_Stamp, Usuario_Stamp, Dm_Stamp, DM_Tipo_Implemento) ");
    buff.append ("VALUES (?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setString (2 , getCD_Tipo_Veiculo ());
      pstmt.setString (3 , getNM_Tipo_Veiculo ());
      pstmt.setString (4 , getDt_Stamp ());
      pstmt.setString (5 , getUsuario_Stamp ());
      pstmt.setString (6 , getDm_Stamp ());
      pstmt.setString (7 , getDM_Tipo_Implemento ());
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
      // passando como parâmetro o NM_Tipo_Veiculo do DSN
      // o NM_Tipo_Veiculo de usuário e a senha do banco.
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
    buff.append ("UPDATE Tipos_Veiculos SET NM_Tipo_Veiculo=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, ");
    buff.append ("DM_Tipo_Implemento=?, cd_tipo_veiculo=? ");
    buff.append ("WHERE OID_Tipo_Veiculo=?");

    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getNM_Tipo_Veiculo ());
      pstmt.setString (2 , getDt_Stamp ());
      pstmt.setString (3 , getUsuario_Stamp ());
      pstmt.setString (4 , getDm_Stamp ());
      pstmt.setString (5 , getDM_Tipo_Implemento ());
      pstmt.setString (6 , getCD_Tipo_Veiculo ());
      pstmt.setInt (7 , getOID ());
//		    // System.out.println("Tipo_VeiculoBean.update() sql - "+pstmt.toString());
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
      // passando como parâmetro o NM_Tipo_Veiculo do DSN
      // o NM_Tipo_Veiculo de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Tipos_Veiculos ");
    buff.append ("WHERE OID_Tipo_Veiculo=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
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

  public static final Tipo_VeiculoBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Veiculo do DSN
      // o NM_Tipo_Veiculo de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Tipo_VeiculoBean p = new Tipo_VeiculoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Veiculo, ");
      buff.append ("	CD_Tipo_Veiculo, ");
      buff.append ("	NM_Tipo_Veiculo, ");
      buff.append ("	DM_Tipo_Implemento ");
      buff.append ("FROM Tipos_Veiculos ");
      buff.append ("WHERE OID_Tipo_Veiculo= ");
      buff.append (oid);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Veiculo (cursor.getString (2));
        p.setNM_Tipo_Veiculo (cursor.getString (3));
        p.setDM_Tipo_Implemento (cursor.getString ("DM_Tipo_Implemento"));
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

  public static final Tipo_VeiculoBean getByCD_Tipo_Veiculo (String CD_Tipo_Veiculo) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Veiculo do DSN
      // o NM_Tipo_Veiculo de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Tipo_VeiculoBean p = new Tipo_VeiculoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Veiculo, ");
      buff.append ("	CD_Tipo_Veiculo, ");
      buff.append ("	NM_Tipo_Veiculo, ");
      buff.append ("	DM_Tipo_Implemento ");
      buff.append ("FROM Tipos_Veiculos ");
      buff.append ("WHERE CD_Tipo_Veiculo= '");
      buff.append (CD_Tipo_Veiculo);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Veiculo (cursor.getString (2));
        p.setNM_Tipo_Veiculo (cursor.getString (3));
        p.setDM_Tipo_Implemento (cursor.getString ("DM_Tipo_Implemento"));
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

  public static final List getByNM_Tipo_Veiculo (String NM_Tipo_Veiculo) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Veiculo do DSN
      // o NM_Tipo_Veiculo de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Tipos_Veiculos_Lista = new ArrayList ();
    StringBuffer buff = new StringBuffer ();
    buff.append ("SELECT OID_Tipo_Veiculo, ");
    buff.append ("	CD_Tipo_Veiculo, ");
    buff.append ("	NM_Tipo_Veiculo, ");
    buff.append ("	DM_Tipo_Implemento ");
    buff.append ("FROM Tipos_Veiculos ");
    buff.append ("WHERE NM_Tipo_Veiculo LIKE'");
    buff.append (NM_Tipo_Veiculo);
    buff.append ("%'");
    buff.append ("order by NM_Tipo_Veiculo");

    Statement stmt = conn.createStatement ();
    ResultSet cursor =
        stmt.executeQuery (buff.toString ());

    while (cursor.next ()) {
      Tipo_VeiculoBean p = new Tipo_VeiculoBean ();
      p.setOID (cursor.getInt (1));
      p.setCD_Tipo_Veiculo (cursor.getString (2));
      p.setNM_Tipo_Veiculo (cursor.getString (3));
      p.setDM_Tipo_Implemento (cursor.getString ("DM_Tipo_Implemento"));
      Tipos_Veiculos_Lista.add (p);
    }
    cursor.close ();
    stmt.close ();
    conn.close ();
    return Tipos_Veiculos_Lista;
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

    List Tipos_Veiculos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Veiculo, ");
      buff.append ("	CD_Tipo_Veiculo, ");
      buff.append ("	NM_Tipo_Veiculo, ");
      buff.append ("	DM_Tipo_Implemento ");
      buff.append ("FROM Tipos_Veiculos");
      buff.append ("orde by NM_Tipo_Veiculo");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Tipo_VeiculoBean p = new Tipo_VeiculoBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Veiculo (cursor.getString (2));
        p.setNM_Tipo_Veiculo (cursor.getString (3));
        p.setDM_Tipo_Implemento (cursor.getString ("DM_Tipo_Implemento"));
        Tipos_Veiculos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Tipos_Veiculos_Lista;
  }

  public static void main (String args[]) throws Exception {
    Tipo_VeiculoBean pp = new Tipo_VeiculoBean ();
    pp.setOID (2);
    pp.setCD_Tipo_Veiculo ("LLL");
    pp.setNM_Tipo_Veiculo ("Litro");
    pp.insert ();

    Tipo_VeiculoBean p = getByOID (2);
    // //// System.out.println(p.getOID());
    // //// System.out.println(p.getCD_Tipo_Veiculo());
    // //// System.out.println(p.getNM_Tipo_Veiculo());
  }

  public static String getDescTipoImplemento (int DM_Tipo_Implemento) {
    return Tipos_Implementos[DM_Tipo_Implemento];
  }

  public static int getNR_Tipos_Implementos () {
    return Tipos_Implementos.length;
  }
}