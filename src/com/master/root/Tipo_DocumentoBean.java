package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

import com.master.util.Excecoes;
import com.master.util.FormataData;

public class Tipo_DocumentoBean {
  private String CD_Tipo_Documento;
  private String NM_Tipo_Documento;
  private String DM_Numeracao_Automatica;
  private String DM_Compensacao;
  private String DM_Debito_Credito;
  private String DM_Aplicacao;
  private long NR_Proximo_Numero;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;

  public Tipo_DocumentoBean () {}

  public String getCD_Tipo_Documento () {
    return CD_Tipo_Documento;
  }

  public void setCD_Tipo_Documento (String CD_Tipo_Documento) {
    this.CD_Tipo_Documento = CD_Tipo_Documento;
  }

  public String getNM_Tipo_Documento () {
    return NM_Tipo_Documento;
  }

  public void setNM_Tipo_Documento (String NM_Tipo_Documento) {
    this.NM_Tipo_Documento = NM_Tipo_Documento;
  }

  public String getDM_Numeracao_Automatica () {
    return DM_Numeracao_Automatica;
  }

  public void setDM_Numeracao_Automatica (String DM_Numeracao_Automatica) {
    this.DM_Numeracao_Automatica = DM_Numeracao_Automatica;
  }

  public String getDM_Compensacao () {
    return DM_Compensacao;
  }

  public void setDM_Compensacao (String DM_Compensacao) {
    this.DM_Compensacao = DM_Compensacao;
  }

  public String getDM_Debito_Credito () {
    return DM_Debito_Credito;
  }

  public void setDM_Debito_Credito (String DM_Debito_Credito) {
    this.DM_Debito_Credito = DM_Debito_Credito;
  }

  public String getDM_Aplicacao () {
    return DM_Aplicacao;
  }

  public void setDM_Aplicacao (String DM_Aplicacao) {
    this.DM_Aplicacao = DM_Aplicacao;
  }

  public long getNR_Proximo_Numero () {
    return NR_Proximo_Numero;
  }

  public void setNR_Proximo_Numero (long NR_Proximo_Numero) {
    this.NR_Proximo_Numero = NR_Proximo_Numero;
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
      // passando como parâmetro o NM_Tipo_Documento do DSN
      // o NM_Tipo_Documento de usuário e a senha do banco.
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
          "SELECT MAX(OID_Tipo_Documento) FROM Tipos_Documentos");

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
    buff.append ("INSERT INTO Tipos_Documentos (OID_Tipo_Documento, CD_Tipo_Documento, NM_Tipo_Documento, DM_Numeracao_Automatica, DM_Compensacao, NR_Proximo_Numero, DM_Debito_Credito, DM_Aplicacao, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setString (2 , getCD_Tipo_Documento ());
      pstmt.setString (3 , getNM_Tipo_Documento ());
      pstmt.setString (4 , getDM_Numeracao_Automatica ());
      pstmt.setString (5 , getDM_Compensacao ());
      pstmt.setLong (6 , getNR_Proximo_Numero ());
      pstmt.setString (7 , getDM_Debito_Credito ());
      pstmt.setString (8 , getDM_Aplicacao ());
      pstmt.setString (9 , getDt_Stamp ());
      pstmt.setString (10 , getUsuario_Stamp ());
      pstmt.setString (11 , getDm_Stamp ());
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
      // passando como parâmetro o NM_Tipo_Documento do DSN
      // o NM_Tipo_Documento de usuário e a senha do banco.
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
    buff.append ("UPDATE Tipos_Documentos SET NM_Tipo_Documento=?, DM_Numeracao_Automatica=?, DM_Compensacao=?, NR_Proximo_Numero=?, DM_Debito_Credito=?, DM_Aplicacao=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
    buff.append ("WHERE OID_Tipo_Documento=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getNM_Tipo_Documento ());
      pstmt.setString (2 , getDM_Numeracao_Automatica ());
      pstmt.setString (3 , getDM_Compensacao ());
      pstmt.setLong (4 , getNR_Proximo_Numero ());
      pstmt.setString (5 , getDM_Debito_Credito ());
      pstmt.setString (6 , getDM_Aplicacao ());
      pstmt.setString (7 , getDt_Stamp ());
      pstmt.setString (8 , getUsuario_Stamp ());
      pstmt.setString (9 , getDm_Stamp ());
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

  public void update_NR_Proximo_Numero () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Documento do DSN
      // o NM_Tipo_Documento de usuário e a senha do banco.
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
    buff.append ("UPDATE Tipos_Documentos SET NR_Proximo_Numero=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
    buff.append ("WHERE OID_Tipo_Documento=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setLong (1 , getNR_Proximo_Numero ());
      pstmt.setDate (2 , FormataData.formataDataTB (getDt_Stamp ()));
      pstmt.setString (3 , getUsuario_Stamp ());
      pstmt.setString (4 , getDm_Stamp ());
      pstmt.setInt (5 , getOID ());
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
      // passando como parâmetro o NM_Tipo_Documento do DSN
      // o NM_Tipo_Documento de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Tipos_Documentos ");
    buff.append ("WHERE OID_Tipo_Documento=?");
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

  public static final Tipo_DocumentoBean getByOID (int oid , boolean autoNumera) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Documento do DSN
      // o NM_Tipo_Documento de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Tipo_DocumentoBean p = new Tipo_DocumentoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Documento, ");
      buff.append ("	CD_Tipo_Documento, ");
      buff.append ("	NM_Tipo_Documento, ");
      buff.append ("	DM_Numeracao_Automatica, ");
      buff.append ("	DM_Compensacao, ");
      buff.append ("	NR_Proximo_Numero, ");
      buff.append ("	DM_Debito_Credito, ");
      buff.append ("	DM_Aplicacao ");
      buff.append ("FROM Tipos_Documentos ");
      buff.append ("WHERE OID_Tipo_Documento= ");
      buff.append (oid);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      if (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Documento (cursor.getString (2));
        p.setNM_Tipo_Documento (cursor.getString (3));
        p.setDM_Numeracao_Automatica (cursor.getString (4));
        p.setDM_Compensacao (cursor.getString (5));
        if (autoNumera && "S".equals (p.getDM_Numeracao_Automatica ())) {
          p.setNR_Proximo_Numero (cursor.getLong (6));
          Tipo_DocumentoBean tipoDocumento = new Tipo_DocumentoBean ();
          tipoDocumento.setOID (p.getOID ());
          tipoDocumento.setNR_Proximo_Numero (p.getNR_Proximo_Numero () + 1);
          tipoDocumento.update_NR_Proximo_Numero ();
        }
        else if (autoNumera) {
          p.setNR_Proximo_Numero (0);
        }
        else {
          p.setNR_Proximo_Numero (cursor.getLong (6));
        }
        p.setDM_Debito_Credito (cursor.getString (7));
        p.setDM_Aplicacao (cursor.getString (8));
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , Tipo_DocumentoBean.class.getName () , "getByOID(int oid)");
    }
    return p;
  }

  public static final Tipo_DocumentoBean getByOID (int oid) throws Exception {
    return getByOID (oid , false);
  }

  public static final Tipo_DocumentoBean getByCD_Tipo_Documento (String CD_Tipo_Documento , boolean autoNumera) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Documento do DSN
      // o NM_Tipo_Documento de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Tipo_DocumentoBean p = new Tipo_DocumentoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Documento, ");
      buff.append ("	CD_Tipo_Documento, ");
      buff.append ("	NM_Tipo_Documento, ");
      buff.append ("	DM_Numeracao_Automatica, ");
      buff.append ("	DM_Compensacao, ");
      buff.append ("	NR_Proximo_Numero, ");
      buff.append ("	DM_Debito_Credito, ");
      buff.append ("	DM_Aplicacao ");
      buff.append ("FROM Tipos_Documentos ");
      buff.append ("WHERE CD_Tipo_Documento= '");
      buff.append (CD_Tipo_Documento);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Documento (cursor.getString (2));
        p.setNM_Tipo_Documento (cursor.getString (3));
        p.setDM_Numeracao_Automatica (cursor.getString (4));
        p.setDM_Compensacao (cursor.getString (5));
        if (autoNumera && "S".equals (p.getDM_Numeracao_Automatica ())) {
          p.setNR_Proximo_Numero (cursor.getLong (6));
          Tipo_DocumentoBean tipoDocumento = new Tipo_DocumentoBean ();
          tipoDocumento.setOID (p.getOID ());
          tipoDocumento.setNR_Proximo_Numero (p.getNR_Proximo_Numero () + 1);
          tipoDocumento.update_NR_Proximo_Numero ();
        }
        else if (autoNumera) {
          p.setNR_Proximo_Numero (0);
        }
        else {
          p.setNR_Proximo_Numero (cursor.getLong (6));
        }
        p.setDM_Debito_Credito (cursor.getString (7));
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

  public static final Tipo_DocumentoBean getByCD_Tipo_Documento (String CD_Tipo_Documento) throws Exception {
    return getByCD_Tipo_Documento (CD_Tipo_Documento , false);
  }

  public static final List getByNM_Tipo_Documento (String NM_Tipo_Documento) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Documento do DSN
      // o NM_Tipo_Documento de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Tipos_Documentos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Documento, ");
      buff.append ("	CD_Tipo_Documento, ");
      buff.append ("	NM_Tipo_Documento, ");
      buff.append ("	DM_Numeracao_Automatica, ");
      buff.append ("	DM_Compensacao, ");
      buff.append ("	NR_Proximo_Numero, ");
      buff.append ("	DM_Debito_Credito, ");
      buff.append ("	DM_Aplicacao ");
      buff.append ("FROM Tipos_Documentos ");
      buff.append ("WHERE NM_Tipo_Documento LIKE'");
      buff.append (NM_Tipo_Documento);
      buff.append ("%' ORDER BY NM_Tipo_Documento");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Tipo_DocumentoBean p = new Tipo_DocumentoBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Documento (cursor.getString (2));
        p.setNM_Tipo_Documento (cursor.getString (3));
        p.setDM_Numeracao_Automatica (cursor.getString (4));
        p.setDM_Compensacao (cursor.getString (5));
        p.setNR_Proximo_Numero (cursor.getLong (6));
        p.setDM_Debito_Credito (cursor.getString (7));
        p.setDM_Aplicacao (cursor.getString (8));
        Tipos_Documentos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Tipos_Documentos_Lista;
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

    List Tipos_Documentos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Documento, ");
      buff.append ("	CD_Tipo_Documento, ");
      buff.append ("	NM_Tipo_Documento, ");
      buff.append ("	DM_Numeracao_Automatica, ");
      buff.append ("	DM_Compensacao, ");
      buff.append ("	NR_Proximo_Numero, ");
      buff.append ("	DM_Debito_Credito, ");
      buff.append ("	DM_Aplicacao ");
      buff.append ("FROM Tipos_Documentos ORDER BY NM_Tipo_Documento ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Tipo_DocumentoBean p = new Tipo_DocumentoBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Documento (cursor.getString (2));
        p.setNM_Tipo_Documento (cursor.getString (3));
        p.setDM_Numeracao_Automatica (cursor.getString (4));
        p.setDM_Compensacao (cursor.getString (5));
        p.setNR_Proximo_Numero (cursor.getLong (6));
        p.setDM_Debito_Credito (cursor.getString (7));
        p.setDM_Aplicacao (cursor.getString (8));
        Tipos_Documentos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Tipos_Documentos_Lista;
  }

  public static void main (String args[]) throws Exception {
    Tipo_DocumentoBean pp = new Tipo_DocumentoBean ();
    pp.setOID (1);
    pp.setCD_Tipo_Documento ("ADV");
    pp.setNM_Tipo_Documento ("Ad Valorem");
    pp.setDM_Numeracao_Automatica ("C");
    pp.setDM_Compensacao ("P");
    pp.setNR_Proximo_Numero (10);
    pp.insert ();

    Tipo_DocumentoBean p = getByOID (1);
    // //// System.out.println(p.getOID());
    // //// System.out.println(p.getCD_Tipo_Documento());
    // //// System.out.println(p.getNM_Tipo_Documento());



  }
}
