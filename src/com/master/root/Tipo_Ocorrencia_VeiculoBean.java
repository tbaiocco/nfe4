package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class Tipo_Ocorrencia_VeiculoBean {
  private String CD_Tipo_Ocorrencia;
  private String NM_Tipo_Ocorrencia;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private String DM_Tipo;
  private String DM_Acesso;
  private String DM_Avaria;
  private String DM_Status;
  private String DM_Aplicacao;

  public Tipo_Ocorrencia_VeiculoBean () {}

  public String getCD_Tipo_Ocorrencia () {
    return CD_Tipo_Ocorrencia;
  }

  public void setCD_Tipo_Ocorrencia (String CD_Tipo_Ocorrencia) {
    this.CD_Tipo_Ocorrencia = CD_Tipo_Ocorrencia;
  }

  public String getNM_Tipo_Ocorrencia () {
    return NM_Tipo_Ocorrencia;
  }

  public void setNM_Tipo_Ocorrencia (String NM_Tipo_Ocorrencia) {
    this.NM_Tipo_Ocorrencia = NM_Tipo_Ocorrencia;
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

  public int insert () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    int auxOid = 0;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Ocorrencia do DSN
      // o NM_Tipo_Ocorrencia de usuário e a senha do banco.
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
          "SELECT MAX(OID_Tipo_Ocorrencia) FROM Tipos_Ocorrencias_Veiculos");

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
    buff.append ("INSERT INTO Tipos_Ocorrencias_Veiculos (OID_Tipo_Ocorrencia, CD_Tipo_Ocorrencia, NM_Tipo_Ocorrencia, Dt_Stamp, Usuario_Stamp, Dm_Stamp, DM_Tipo, DM_Acesso, DM_Avaria, DM_Status, DM_Aplicacao) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setString (2 , getCD_Tipo_Ocorrencia ());
      pstmt.setString (3 , getNM_Tipo_Ocorrencia ());
      pstmt.setString (4 , getDt_Stamp ());
      pstmt.setString (5 , getUsuario_Stamp ());
      pstmt.setString (6 , getDm_Stamp ());
      pstmt.setString (7 , getDM_Tipo ());
      pstmt.setString (8 , getDM_Acesso ());
      pstmt.setString (9 , getDM_Avaria ());
      pstmt.setString (10 , getDM_Status ());
      pstmt.setString (11 , getDM_Aplicacao ());

      pstmt.executeUpdate ();

      auxOid = getOID ();
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
    return auxOid;
  }

  public void update () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Ocorrencia do DSN
      // o NM_Tipo_Ocorrencia de usuário e a senha do banco.
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
    buff.append ("UPDATE Tipos_Ocorrencias_Veiculos SET NM_Tipo_Ocorrencia=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, DM_Tipo=?, DM_Acesso=?, DM_Avaria=?, DM_Status=?, DM_Aplicacao=?  ");
    buff.append ("WHERE OID_Tipo_Ocorrencia=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getNM_Tipo_Ocorrencia ());
      pstmt.setString (2 , getDt_Stamp ());
      pstmt.setString (3 , getUsuario_Stamp ());
      pstmt.setString (4 , getDm_Stamp ());
      pstmt.setString (5 , getDM_Tipo ());
      pstmt.setString (6 , getDM_Acesso ());
      pstmt.setString (7 , getDM_Avaria ());
      pstmt.setString (8 , getDM_Status ());
      pstmt.setString (9 , getDM_Aplicacao ());
      pstmt.setInt (10 , getOID ());
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
      // passando como parâmetro o NM_Tipo_Ocorrencia do DSN
      // o NM_Tipo_Ocorrencia de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Tipos_Ocorrencias_Veiculos ");
    buff.append ("WHERE OID_Tipo_Ocorrencia=?");
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

  public static final Tipo_Ocorrencia_VeiculoBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Ocorrencia do DSN
      // o NM_Tipo_Ocorrencia de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Tipo_Ocorrencia_VeiculoBean p = new Tipo_Ocorrencia_VeiculoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Ocorrencia, ");
      buff.append ("	CD_Tipo_Ocorrencia, ");
      buff.append ("	NM_Tipo_Ocorrencia, ");
      buff.append ("	DM_Tipo, ");
      buff.append ("	DM_Acesso, ");
      buff.append ("	DM_Avaria, ");
      buff.append ("	DM_Status, ");
      buff.append ("	DM_Aplicacao ");
      buff.append ("FROM Tipos_Ocorrencias_Veiculos ");
      buff.append ("WHERE OID_Tipo_Ocorrencia= ");
      buff.append (oid);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Ocorrencia (cursor.getString (2));
        p.setNM_Tipo_Ocorrencia (cursor.getString (3));
        p.setDM_Tipo (cursor.getString (4));
        p.setDM_Acesso (cursor.getString (5));
        p.setDM_Avaria (cursor.getString (6));
        p.setDM_Status (cursor.getString (7));
        p.setDM_Aplicacao (cursor.getString (8));
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

  public static final Tipo_Ocorrencia_VeiculoBean getByCD_Tipo_Ocorrencia (String CD_Tipo_Ocorrencia) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Ocorrencia do DSN
      // o NM_Tipo_Ocorrencia de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Tipo_Ocorrencia_VeiculoBean p = new Tipo_Ocorrencia_VeiculoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Ocorrencia, ");
      buff.append ("	CD_Tipo_Ocorrencia, ");
      buff.append ("	NM_Tipo_Ocorrencia, ");
      buff.append ("	DM_Tipo, ");
      buff.append ("	DM_Acesso, ");
      buff.append ("	DM_Avaria, ");
      buff.append ("	DM_Status, ");
      buff.append ("	DM_Aplicacao ");
      buff.append ("FROM Tipos_Ocorrencias_Veiculos ");
      buff.append ("WHERE CD_Tipo_Ocorrencia= '");
      buff.append (CD_Tipo_Ocorrencia);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Ocorrencia (cursor.getString (2));
        p.setNM_Tipo_Ocorrencia (cursor.getString (3));
        p.setDM_Tipo (cursor.getString (4));
        p.setDM_Acesso (cursor.getString (5));
        p.setDM_Avaria (cursor.getString (6));
        p.setDM_Status (cursor.getString (7));
        p.setDM_Aplicacao (cursor.getString (8));
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

  public static final List getByNM_Tipo_Ocorrencia (String NM_Tipo_Ocorrencia) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Ocorrencia do DSN
      // o NM_Tipo_Ocorrencia de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Tipos_Ocorrencias_Veiculos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Ocorrencia, ");
      buff.append ("	CD_Tipo_Ocorrencia, ");
      buff.append ("	NM_Tipo_Ocorrencia ");
      buff.append ("FROM Tipos_Ocorrencias_Veiculos ");
      buff.append ("WHERE NM_Tipo_Ocorrencia LIKE'");
      buff.append (NM_Tipo_Ocorrencia);
      buff.append ("%'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Tipo_Ocorrencia_VeiculoBean p = new Tipo_Ocorrencia_VeiculoBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Ocorrencia (cursor.getString (2));
        p.setNM_Tipo_Ocorrencia (cursor.getString (3));
        Tipos_Ocorrencias_Veiculos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Tipos_Ocorrencias_Veiculos_Lista;
  }

  public static final List getByDM_Aplicacao (String DM_Aplicacao) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Ocorrencia do DSN
      // o NM_Tipo_Ocorrencia de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Tipos_Ocorrencias_Veiculos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Ocorrencia, ");
      buff.append ("	CD_Tipo_Ocorrencia, ");
      buff.append ("	NM_Tipo_Ocorrencia ");
      buff.append ("FROM Tipos_Ocorrencias_Veiculos ");
      buff.append ("WHERE 1=1 ");
      if (DM_Aplicacao != null && !DM_Aplicacao.equals ("null") && !DM_Aplicacao.equals ("   ") && !DM_Aplicacao.equals ("")) {
        buff.append ("AND DM_Aplicacao ='");
        buff.append (DM_Aplicacao);
        buff.append ("'");
      }
      buff.append (" ORDER BY  NM_Tipo_Ocorrencia  ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Tipo_Ocorrencia_VeiculoBean p = new Tipo_Ocorrencia_VeiculoBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Ocorrencia (cursor.getString (2));
        p.setNM_Tipo_Ocorrencia (cursor.getString (3));
        Tipos_Ocorrencias_Veiculos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Tipos_Ocorrencias_Veiculos_Lista;
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

    List Tipos_Ocorrencias_Veiculos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Ocorrencia, ");
      buff.append ("	CD_Tipo_Ocorrencia, ");
      buff.append ("	NM_Tipo_Ocorrencia ");
      buff.append ("FROM Tipos_Ocorrencias_Veiculos");
      buff.append (" ORDER BY  NM_Tipo_Ocorrencia  ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Tipo_Ocorrencia_VeiculoBean p = new Tipo_Ocorrencia_VeiculoBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Ocorrencia (cursor.getString (2));
        p.setNM_Tipo_Ocorrencia (cursor.getString (3));
        Tipos_Ocorrencias_Veiculos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Tipos_Ocorrencias_Veiculos_Lista;
  }

  public void setDM_Tipo (String DM_Tipo) {
    this.DM_Tipo = DM_Tipo;
  }

  public String getDM_Tipo () {
    return DM_Tipo;
  }

  public void setDM_Acesso (String DM_Acesso) {
    this.DM_Acesso = DM_Acesso;
  }

  public String getDM_Acesso () {
    return DM_Acesso;
  }

  public void setDM_Avaria (String DM_Avaria) {
    this.DM_Avaria = DM_Avaria;
  }

  public String getDM_Avaria () {
    return DM_Avaria;
  }

  public void setDM_Status (String DM_Status) {
    this.DM_Status = DM_Status;
  }

  public String getDM_Status () {
    return DM_Status;
  }

  public void setDM_Aplicacao (String DM_Aplicacao) {
    this.DM_Aplicacao = DM_Aplicacao;
  }

  public String getDM_Aplicacao () {
    return DM_Aplicacao;
  }
}