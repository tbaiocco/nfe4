package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class EmpresaBean {
  private String CD_Empresa;
  private String NM_Empresa;
  private String DM_Tipo_Empresa;
  private String TX_Mensagem;
  private String DM_Acesso;
  private String DM_Situacao;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private String NR_CNPJ_CPF;

  public EmpresaBean () {}

  public String getCD_Empresa () {
    return CD_Empresa;
  }

  public void setCD_Empresa (String CD_Empresa) {
    this.CD_Empresa = CD_Empresa;
  }

  public String getNM_Empresa () {
    return NM_Empresa;
  }

  public void setNM_Empresa (String NM_Empresa) {
    this.NM_Empresa = NM_Empresa;
  }

  public String getDM_Tipo_Empresa () {
    return DM_Tipo_Empresa;
  }

  public void setDM_Tipo_Empresa (String DM_Tipo_Empresa) {
    this.DM_Tipo_Empresa = DM_Tipo_Empresa;
  }

  public String getTX_Mensagem () {
    return TX_Mensagem;
  }

  public void setTX_Mensagem (String TX_Mensagem) {
    this.TX_Mensagem = TX_Mensagem;
  }

  public String getDM_Acesso () {
    return DM_Acesso;
  }

  public void setDM_Acesso (String DM_Acesso) {
    this.DM_Acesso = DM_Acesso;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
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
      // passando como parâmetro o NM_Empresa do DSN
      // o NM_Empresa de usuário e a senha do banco.
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
          "SELECT MAX(OID_Empresa) FROM Empresas");

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
    buff.append ("INSERT INTO Empresas (OID_Empresa, CD_Empresa, NM_Empresa, DM_Tipo_Empresa, TX_Mensagem, DM_Acesso, DM_Situacao, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setString (2 , getCD_Empresa ());
      pstmt.setString (3 , getNM_Empresa ());
      pstmt.setString (4 , getDM_Tipo_Empresa ());
      pstmt.setString (5 , getTX_Mensagem ());
      pstmt.setString (6 , getDM_Acesso ());
      pstmt.setString (7 , getDM_Situacao ());
      pstmt.setString (8 , getDt_Stamp ());
      pstmt.setString (9 , getUsuario_Stamp ());
      pstmt.setString (10 , getDm_Stamp ());
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
      // passando como parâmetro o NM_Empresa do DSN
      // o NM_Empresa de usuário e a senha do banco.
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
    buff.append ("UPDATE Empresas SET NM_Empresa=?, DM_Tipo_Empresa=?, TX_Mensagem=?, DM_Acesso=?, DM_Situacao=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
    buff.append ("WHERE OID_Empresa=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getNM_Empresa ());
      pstmt.setString (2 , getDM_Tipo_Empresa ());
      pstmt.setString (3 , getTX_Mensagem ());
      pstmt.setString (4 , getDM_Acesso ());
      pstmt.setString (5 , getDM_Situacao ());
      pstmt.setString (6 , getDt_Stamp ());
      pstmt.setString (7 , getUsuario_Stamp ());
      pstmt.setString (8 , getDm_Stamp ());
      pstmt.setInt (9 , getOID ());
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
      // passando como parâmetro o NM_Empresa do DSN
      // o NM_Empresa de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Empresas ");
    buff.append ("WHERE OID_Empresa=?");
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

  public static final EmpresaBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Empresa do DSN
      // o NM_Empresa de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    EmpresaBean p = new EmpresaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Empresa, ");
      buff.append ("	CD_Empresa, ");
      buff.append ("	NM_Empresa, ");
      buff.append ("	DM_Tipo_Empresa, ");
      buff.append ("	TX_Mensagem, ");
      buff.append ("	DM_Acesso, ");
      buff.append ("	DM_Situacao, ");
      buff.append ("	NR_CNPJ_CPF ");
      buff.append ("FROM Empresas ");
      buff.append ("WHERE OID_Empresa= ");
      buff.append (oid);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Empresa (cursor.getString (2));
        p.setNM_Empresa (cursor.getString (3));
        p.setDM_Tipo_Empresa (cursor.getString (4));
        p.setTX_Mensagem (cursor.getString (5));
        p.setDM_Acesso (cursor.getString (6));
        p.setDM_Situacao (cursor.getString (7));
        p.setNR_CNPJ_CPF (cursor.getString (8));
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

  public static final EmpresaBean getByCD_Empresa (String CD_Empresa) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Empresa do DSN
      // o NM_Empresa de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    EmpresaBean p = new EmpresaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Empresa, ");
      buff.append ("	CD_Empresa, ");
      buff.append ("	NM_Empresa, ");
      buff.append ("	DM_Tipo_Empresa, ");
      buff.append ("	TX_Mensagem, ");
      buff.append ("	DM_Acesso, ");
      buff.append ("	DM_Situacao, ");
      buff.append ("	NR_CNPJ_CPF ");
      buff.append ("FROM Empresas ");
      buff.append ("WHERE CD_Empresa= '");
      buff.append (CD_Empresa);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Empresa (cursor.getString (2));
        p.setNM_Empresa (cursor.getString (3));
        p.setDM_Tipo_Empresa (cursor.getString (4));
        p.setTX_Mensagem (cursor.getString (5));
        p.setDM_Acesso (cursor.getString (6));
        p.setDM_Situacao (cursor.getString (7));
        p.setNR_CNPJ_CPF (cursor.getString (8));

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

  public static final List getByNM_Empresa (String NM_Empresa) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Empresa do DSN
      // o NM_Empresa de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Empresas_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Empresa, ");
      buff.append ("	CD_Empresa, ");
      buff.append ("	NM_Empresa, ");
      buff.append ("	DM_Tipo_Empresa, ");
      buff.append ("	TX_Mensagem, ");
      buff.append ("	DM_Acesso, ");
      buff.append ("	DM_Situacao ");
      buff.append ("FROM Empresas ");
      buff.append ("WHERE NM_Empresa LIKE'");
      buff.append (NM_Empresa);
      buff.append ("%'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        EmpresaBean p = new EmpresaBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Empresa (cursor.getString (2));
        p.setNM_Empresa (cursor.getString (3));
        p.setDM_Tipo_Empresa (cursor.getString (4));
        p.setTX_Mensagem (cursor.getString (5));
        p.setDM_Acesso (cursor.getString (6));
        p.setDM_Situacao (cursor.getString (7));
        Empresas_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Empresas_Lista;
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

    List Empresas_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Empresa, ");
      buff.append ("	CD_Empresa, ");
      buff.append ("	NM_Empresa, ");
      buff.append ("	DM_Tipo_Empresa, ");
      buff.append ("	TX_Mensagem, ");
      buff.append ("	DM_Acesso, ");
      buff.append ("	DM_Situacao ");
      buff.append ("FROM Empresas");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        EmpresaBean p = new EmpresaBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Empresa (cursor.getString (2));
        p.setNM_Empresa (cursor.getString (3));
        p.setDM_Tipo_Empresa (cursor.getString (4));
        p.setTX_Mensagem (cursor.getString (5));
        p.setDM_Acesso (cursor.getString (6));
        p.setDM_Situacao (cursor.getString (7));
        Empresas_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Empresas_Lista;
  }

  public static void main (String args[]) throws Exception {
    EmpresaBean p = new EmpresaBean ();
    p.setOID (1);
    p.setCD_Empresa ("001");
    p.setNM_Empresa ("Panazzolo - Matriz");
    p.setDM_Tipo_Empresa ("M");
    p.setTX_Mensagem ("Empresa Matriz");
    p.setDM_Acesso ("L");
    p.setDM_Situacao ("G");
    p.insert ();

    EmpresaBean pp = getByOID (1);
    // //// System.out.println(pp.getOID());
    // //// System.out.println(pp.getCD_Empresa());
    // //// System.out.println(pp.getNM_Empresa());



  }

  public void setNR_CNPJ_CPF (String NR_CNPJ_CPF) {
    this.NR_CNPJ_CPF = NR_CNPJ_CPF;
  }

  public String getNR_CNPJ_CPF () {
    return NR_CNPJ_CPF;
  }
}