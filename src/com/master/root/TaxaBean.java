package com.master.root;

import java.sql.*;
import java.util.*;

import auth.*;
import com.master.util.*;

public class TaxaBean {
  private double PE_Aliquota_ICMS;
  private double PE_Abatimento_ICMS;
  private double PE_Aliquota_ICMS_Aereo;
  private double PE_Aliquota_Seguro;
  private double PE_Aliquota_COFINS;
  private double PE_Aliquota_PIS;
  private String CD_CFO;
  private String DM_ICMS_CIF;
  private String DM_ICMS_FOB;
  private String TX_Mensagem_Fiscal;
  private String TX_Mensagem_Fiscal_ICMS;
  private String CD_Estado;
  private String NM_Estado;
  private String NM_Campo_ICMS;
  private String CD_Estado_Destino;
  private String NM_Estado_Destino;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_Estado;
  private int oid_Estado_Destino;

  private double PE_Aliquota_RCTRC;
  private double PE_Aliquota_RCTA;
  private double PE_Aliquota_RCTR_DC;
  private double PE_Aliquota_RCTR_VI;

  private double PE_ICMS_Nao_Contribuinte;
  private double VL_Cobertura_Rodoviario;
  private double VL_Cobertura_Aereo;
  private double PE_Aliquota_Metropolitana;

  public TaxaBean () {}

  public String getCD_Estado () {
    return CD_Estado;
  }

  public void setCD_Estado (String CD_Estado) {
    this.CD_Estado = CD_Estado;
  }

  public String getNM_Estado () {
    return NM_Estado;
  }

  public void setNM_Estado (String NM_Estado) {
    this.NM_Estado = NM_Estado;
  }

  public String getNM_Estado_Destino () {
    return NM_Estado_Destino;
  }

  public void setNM_Estado_Destino (String NM_Estado_Destino) {
    this.NM_Estado_Destino = NM_Estado_Destino;
  }

  public String getCD_Estado_Destino () {
    return CD_Estado_Destino;
  }

  public void setCD_Estado_Destino (String CD_Estado_Destino) {
    this.CD_Estado_Destino = CD_Estado_Destino;
  }

  public double getPE_Aliquota_ICMS () {
    return PE_Aliquota_ICMS;
  }

  public void setPE_Aliquota_ICMS (double PE_Aliquota_ICMS) {
    this.PE_Aliquota_ICMS = PE_Aliquota_ICMS;
  }

  public double getPE_Aliquota_Seguro () {
    return PE_Aliquota_Seguro;
  }

  public void setPE_Aliquota_Seguro (double PE_Aliquota_Seguro) {
    this.PE_Aliquota_Seguro = PE_Aliquota_Seguro;
  }

  public double getPE_Aliquota_COFINS () {
    return PE_Aliquota_COFINS;
  }

  public void setPE_Aliquota_COFINS (double PE_Aliquota_COFINS) {
    this.PE_Aliquota_COFINS = PE_Aliquota_COFINS;
  }

  public double getPE_Aliquota_PIS () {
    return PE_Aliquota_PIS;
  }

  public void setPE_Aliquota_PIS (double PE_Aliquota_PIS) {
    this.PE_Aliquota_PIS = PE_Aliquota_PIS;
  }

  public String getCD_CFO () {
    return CD_CFO;
  }

  public void setCD_CFO (String CD_CFO) {
    this.CD_CFO = CD_CFO;
  }

  public String getDM_ICMS_CIF () {
    return DM_ICMS_CIF;
  }

  public void setDM_ICMS_CIF (String DM_ICMS_CIF) {
    this.DM_ICMS_CIF = DM_ICMS_CIF;
  }

  public String getDM_ICMS_FOB () {
    return DM_ICMS_FOB;
  }

  public void setDM_ICMS_FOB (String DM_ICMS_FOB) {
    this.DM_ICMS_FOB = DM_ICMS_FOB;
  }

  public String getTX_Mensagem_Fiscal () {
    return TX_Mensagem_Fiscal;
  }

  public void setTX_Mensagem_Fiscal (String TX_Mensagem_Fiscal) {
    this.TX_Mensagem_Fiscal = TX_Mensagem_Fiscal;
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

  public int getOID_Estado () {
    return oid_Estado;
  }

  public void setOID_Estado (int n) {
    this.oid_Estado = n;
  }

  public int getOID_Estado_Destino () {
    return oid_Estado_Destino;
  }

  public void setOID_Estado_Destino (int n) {
    this.oid_Estado_Destino = n;
  }

  public void setPE_ICMS_Nao_Contribuinte (double PE_ICMS_Nao_Contribuinte) {
    this.PE_ICMS_Nao_Contribuinte = PE_ICMS_Nao_Contribuinte;
  }

  public double getPE_ICMS_Nao_Contribuinte () {
    return PE_ICMS_Nao_Contribuinte;
  }

  public String getTX_Mensagem_Fiscal_ICMS () {
    return this.TX_Mensagem_Fiscal_ICMS;
  }

  public void setTX_Mensagem_Fiscal_ICMS (String mensagem_Fiscal_ICMS) {
    this.TX_Mensagem_Fiscal_ICMS = mensagem_Fiscal_ICMS;
  }

  public double getPE_Aliquota_ICMS_Aereo () {
    return this.PE_Aliquota_ICMS_Aereo;
  }

  public void setPE_Aliquota_ICMS_Aereo (double aliquota_ICMS_Aereo) {
    this.PE_Aliquota_ICMS_Aereo = aliquota_ICMS_Aereo;
  }

  public void insert () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o PE_Aliquota_PIS do DSN
      // o PE_Aliquota_PIS de usuário e a senha do banco.
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
          "SELECT MAX(OID_Taxa) FROM Taxas");

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
    buff.append ("INSERT INTO Taxas (OID_Taxa, OID_Estado, OID_Estado_Destino, PE_Aliquota_ICMS, PE_Aliquota_PIS, CD_CFO, DM_ICMS_CIF, DM_ICMS_FOB, PE_Aliquota_Seguro, PE_Aliquota_COFINS, TX_Mensagem_Fiscal, Dt_Stamp, Usuario_Stamp, Dm_Stamp, PE_ICMS_Nao_Contribuinte, TX_Mensagem_Fiscal_ICMS, PE_Aliquota_ICMS_Aereo, PE_Aliquota_RCTRC, PE_Aliquota_RCTR_DC, PE_Aliquota_RCTR_VI, PE_Aliquota_RCTA, NM_Campo_ICMS, PE_Aliquota_Metropolitana, VL_Cobertura_Rodoviario, VL_Cobertura_Aereo ) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setInt (2 , getOID_Estado ());
      pstmt.setInt (3 , getOID_Estado_Destino ());
      pstmt.setDouble (4 , getPE_Aliquota_ICMS ());
      pstmt.setDouble (5 , getPE_Aliquota_PIS ());
      pstmt.setString (6 , getCD_CFO ());
      pstmt.setString (7 , getDM_ICMS_CIF ());
      pstmt.setString (8 , getDM_ICMS_FOB ());
      pstmt.setDouble (9 , getPE_Aliquota_Seguro ());
      pstmt.setDouble (10 , getPE_Aliquota_COFINS ());
      pstmt.setString (11 , getTX_Mensagem_Fiscal ());
      pstmt.setDate (12 , FormataData.formataDataTB (getDt_Stamp ()));
      pstmt.setString (13 , getUsuario_Stamp ());
      pstmt.setString (14 , getDm_Stamp ());
      pstmt.setDouble (15 , getPE_ICMS_Nao_Contribuinte ());
      pstmt.setString (16 , getTX_Mensagem_Fiscal_ICMS ());
      pstmt.setDouble (17 , getPE_Aliquota_ICMS_Aereo ());
      pstmt.setDouble (18 , getPE_Aliquota_RCTRC ());
      pstmt.setDouble (19 , getPE_Aliquota_RCTR_DC ());
      pstmt.setDouble (20 , getPE_Aliquota_RCTR_VI ());
      pstmt.setDouble (21 , getPE_Aliquota_RCTA ());
      pstmt.setString (22 , getNM_Campo_ICMS ());
      pstmt.setDouble (23 , getPE_Aliquota_Metropolitana ());
      pstmt.setDouble (24 , getVL_Cobertura_Rodoviario ());
      pstmt.setDouble (25 , getVL_Cobertura_Aereo ());
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
      // passando como parâmetro o PE_Aliquota_PIS do DSN
      // o PE_Aliquota_PIS de usuário e a senha do banco.
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
    buff.append ("UPDATE Taxas SET PE_Aliquota_ICMS=?, PE_Aliquota_PIS=?, CD_CFO=?, DM_ICMS_CIF=?, DM_ICMS_FOB=?, PE_Aliquota_Seguro=?, PE_Aliquota_COFINS=?, TX_Mensagem_Fiscal=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, PE_ICMS_Nao_Contribuinte=?, TX_Mensagem_Fiscal_ICMS=?, PE_Aliquota_ICMS_Aereo=?, PE_Aliquota_RCTA=?, PE_Aliquota_RCTRC=?, PE_Aliquota_RCTR_DC=?, PE_Aliquota_RCTR_VI=?, NM_Campo_ICMS=?, PE_Aliquota_Metropolitana=?, VL_Cobertura_Rodoviario=?, VL_Cobertura_Aereo=?, PE_Abatimento_ICMS=? ");
    buff.append ("WHERE OID_Taxa=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setDouble (1 , getPE_Aliquota_ICMS ());
      pstmt.setDouble (2 , getPE_Aliquota_PIS ());
      pstmt.setString (3 , getCD_CFO ());
      pstmt.setString (4 , getDM_ICMS_CIF ());
      pstmt.setString (5 , getDM_ICMS_FOB ());
      pstmt.setDouble (6 , getPE_Aliquota_Seguro ());
      pstmt.setDouble (7 , getPE_Aliquota_COFINS ());
      pstmt.setString (8 , getTX_Mensagem_Fiscal ());
      pstmt.setDate (9 , FormataData.formataDataTB (getDt_Stamp ()));
      pstmt.setString (10 , getUsuario_Stamp ());
      pstmt.setString (11 , getDm_Stamp ());
      pstmt.setDouble (12 , getPE_ICMS_Nao_Contribuinte ());
      pstmt.setString (13 , getTX_Mensagem_Fiscal_ICMS ());
      pstmt.setDouble (14 , getPE_Aliquota_ICMS_Aereo ());
      pstmt.setDouble (15 , getPE_Aliquota_RCTA ());
      pstmt.setDouble (16 , getPE_Aliquota_RCTRC ());
      pstmt.setDouble (17 , getPE_Aliquota_RCTR_DC ());
      pstmt.setDouble (18 , getPE_Aliquota_RCTR_VI ());
      pstmt.setString (19 , getNM_Campo_ICMS ());

      pstmt.setDouble (20 , getPE_Aliquota_Metropolitana ());
      pstmt.setDouble (21 , getVL_Cobertura_Rodoviario ());
      pstmt.setDouble (22 , getVL_Cobertura_Aereo ());
      pstmt.setDouble (23 , getPE_Abatimento_ICMS ());

      pstmt.setInt (24 , getOID ());
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
      // passando como parâmetro o PE_Aliquota_PIS do DSN
      // o PE_Aliquota_PIS de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Taxas ");
    buff.append ("WHERE OID_Taxa=?");
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

  public static final TaxaBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */

    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o PE_Aliquota_PIS do DSN
      // o PE_Aliquota_PIS de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    TaxaBean p = new TaxaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Taxa, ");
      buff.append ("	Taxas.OID_Estado, ");
      buff.append ("	Taxas.OID_Estado_Destino, ");
      buff.append ("	Taxas.PE_Aliquota_ICMS, ");
      buff.append ("	Taxas.PE_Aliquota_Seguro, ");
      buff.append ("	Taxas.PE_Aliquota_PIS, ");
      buff.append ("	Taxas.CD_CFO, ");
      buff.append ("	Taxas.DM_ICMS_CIF, ");
      buff.append ("	Taxas.DM_ICMS_FOB, ");
      buff.append ("	Taxas.PE_Aliquota_COFINS, ");
      buff.append ("	Taxas.TX_Mensagem_Fiscal, ");
      buff.append ("	Estado_Origem.CD_Estado, ");
      buff.append ("	Estado_Origem.NM_Estado, ");
      buff.append ("	Estado_Destino.CD_Estado, ");
      buff.append ("	Estado_Destino.NM_Estado, ");
      buff.append ("	Taxas.PE_ICMS_Nao_Contribuinte, ");
      buff.append ("	Taxas.NM_Campo_ICMS, ");
      buff.append ("	Taxas.TX_Mensagem_Fiscal_ICMS, ");
      buff.append ("	Taxas.PE_Aliquota_ICMS_Aereo, ");
      buff.append ("	Taxas.PE_Aliquota_RCTRC, ");
      buff.append ("	Taxas.PE_Aliquota_RCTR_DC, ");
      buff.append ("	Taxas.PE_Aliquota_RCTR_VI, ");
      buff.append ("	Taxas.VL_Cobertura_Rodoviario, ");
      buff.append ("	Taxas.VL_Cobertura_Aereo, ");
      buff.append ("	Taxas.PE_Aliquota_Metropolitana, ");
      buff.append ("	Taxas.PE_Aliquota_RCTA,  ");
      buff.append ("	Taxas.PE_Abatimento_ICMS ");
      buff.append ("FROM Taxas, Estados Estado_Origem, Estados Estado_Destino ");
      buff.append ("WHERE Taxas.OID_Estado = Estado_Origem.OID_Estado ");
      buff.append ("AND Taxas.OID_Estado_Destino = Estado_Destino.OID_Estado ");
      buff.append ("AND Taxas.OID_Taxa = ");
      buff.append (oid);

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Estado (cursor.getInt (2));
        p.setOID_Estado_Destino (cursor.getInt (3));
        p.setPE_Aliquota_ICMS (cursor.getDouble (4));
        p.setPE_Aliquota_Seguro (cursor.getDouble (5));
        p.setPE_Aliquota_PIS (cursor.getDouble (6));
        p.setCD_CFO (cursor.getString (7));
        p.setDM_ICMS_CIF (cursor.getString (8));
        p.setDM_ICMS_FOB (cursor.getString (9));
        p.setPE_Aliquota_COFINS (cursor.getDouble (10));
        p.setTX_Mensagem_Fiscal (JavaUtil.getValueDef (cursor.getString (11) , "").trim ());
        p.setCD_Estado (cursor.getString (12).trim ());
        p.setNM_Estado (cursor.getString (13).trim ());
        p.setCD_Estado_Destino (cursor.getString (14).trim ());
        p.setNM_Estado_Destino (cursor.getString (15).trim ());
        p.setPE_ICMS_Nao_Contribuinte (cursor.getDouble (16));
        p.setTX_Mensagem_Fiscal_ICMS (cursor.getString ("TX_Mensagem_Fiscal_ICMS"));
        p.setPE_Aliquota_ICMS_Aereo (cursor.getDouble ("PE_Aliquota_ICMS_Aereo"));
        p.setPE_Aliquota_RCTRC (cursor.getDouble ("PE_Aliquota_RCTRC"));
        p.setPE_Aliquota_RCTR_DC (cursor.getDouble ("PE_Aliquota_RCTR_DC"));
        p.setPE_Aliquota_RCTR_VI(cursor.getDouble ("PE_Aliquota_RCTR_VI"));
        p.setPE_Aliquota_RCTA (cursor.getDouble ("PE_Aliquota_RCTA"));
        p.setNM_Campo_ICMS (cursor.getString ("NM_Campo_ICMS"));
        p.setPE_Aliquota_Metropolitana (cursor.getDouble ("PE_Aliquota_Metropolitana"));
        p.setVL_Cobertura_Rodoviario (cursor.getDouble ("VL_Cobertura_Rodoviario"));
        p.setVL_Cobertura_Aereo (cursor.getDouble ("VL_Cobertura_Aereo"));
        p.setPE_Abatimento_ICMS (cursor.getDouble ("PE_Abatimento_ICMS"));
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final TaxaBean getByOID (int oid_Estado_Origem , int oid_Estado_Destino) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o PE_Aliquota_PIS do DSN
      // o PE_Aliquota_PIS de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    TaxaBean p = new TaxaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Taxa, ");
      buff.append ("	Taxas.OID_Estado, ");
      buff.append ("	Taxas.OID_Estado_Destino, ");
      buff.append ("	Taxas.PE_Aliquota_ICMS, ");
      buff.append ("	Taxas.PE_Aliquota_Seguro, ");
      buff.append ("	Taxas.PE_Aliquota_PIS, ");
      buff.append ("	Taxas.CD_CFO, ");
      buff.append ("	Taxas.DM_ICMS_CIF, ");
      buff.append ("	Taxas.DM_ICMS_FOB, ");
      buff.append ("	Taxas.PE_Aliquota_COFINS, ");
      buff.append ("	Taxas.TX_Mensagem_Fiscal, ");
      buff.append ("	Estado_Origem.CD_Estado, ");
      buff.append ("	Estado_Origem.NM_Estado, ");
      buff.append ("	Estado_Destino.CD_Estado, ");
      buff.append ("	Estado_Destino.NM_Estado, ");
      buff.append ("	Taxas.PE_ICMS_Nao_Contribuinte, ");
      buff.append ("	Taxas.TX_Mensagem_Fiscal_ICMS, ");
      buff.append ("	Taxas.PE_Aliquota_ICMS_Aereo, ");
      buff.append ("	Taxas.PE_Aliquota_RCTRC, ");
      buff.append ("	Taxas.PE_Aliquota_RCTR_DC, ");
      buff.append ("	Taxas.PE_Aliquota_RCTR_VI, ");
      buff.append ("	Taxas.VL_Cobertura_Rodoviario, ");
      buff.append ("	Taxas.VL_Cobertura_Aereo, ");
      buff.append ("	Taxas.PE_Aliquota_Metropolitana, ");
      buff.append ("	Taxas.PE_Aliquota_RCTA  ");

      buff.append ("FROM Taxas, Estados Estado_Origem, Estados Estado_Destino ");
      buff.append ("WHERE Taxas.OID_Estado = Estado_Origem.OID_Estado ");
      buff.append ("AND Taxas.OID_Estado_Destino = Estado_Destino.OID_Estado ");
      buff.append ("AND Estado_Origem.OID_Estado =");
      buff.append (oid_Estado_Origem);
      buff.append ("AND Estado_Destino.OID_Estado =");
      buff.append (oid_Estado_Destino);

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Estado (cursor.getInt (2));
        p.setOID_Estado_Destino (cursor.getInt (3));
        p.setPE_Aliquota_ICMS (cursor.getDouble (4));
        p.setPE_Aliquota_Seguro (cursor.getDouble (5));
        p.setPE_Aliquota_PIS (cursor.getDouble (6));
        p.setCD_CFO (cursor.getString (7));
        p.setDM_ICMS_CIF (cursor.getString (8));
        p.setDM_ICMS_FOB (cursor.getString (9));
        p.setPE_Aliquota_COFINS (cursor.getDouble (10));
        p.setTX_Mensagem_Fiscal (JavaUtil.getValueDef (cursor.getString (11) , "").trim ());
        p.setCD_Estado (cursor.getString (12).trim ());
        p.setNM_Estado (cursor.getString (13).trim ());
        p.setCD_Estado_Destino (cursor.getString (14).trim ());
        p.setNM_Estado_Destino (cursor.getString (15).trim ());
        p.setPE_ICMS_Nao_Contribuinte (cursor.getDouble (16));
        p.setTX_Mensagem_Fiscal_ICMS (cursor.getString ("TX_Mensagem_Fiscal_ICMS"));
        p.setPE_Aliquota_ICMS_Aereo (cursor.getDouble ("PE_Aliquota_ICMS_Aereo"));
        p.setPE_Aliquota_RCTRC (cursor.getDouble ("PE_Aliquota_RCTRC"));
        p.setPE_Aliquota_RCTR_DC (cursor.getDouble ("PE_Aliquota_RCTR_DC"));
        p.setPE_Aliquota_RCTR_VI(cursor.getDouble ("PE_Aliquota_RCTR_VI"));
        p.setPE_Aliquota_RCTA (cursor.getDouble ("PE_Aliquota_RCTA"));
        p.setPE_Aliquota_Metropolitana (cursor.getDouble ("PE_Aliquota_Metropolitana"));
        p.setVL_Cobertura_Rodoviario (cursor.getDouble ("VL_Cobertura_Rodoviario"));
        p.setVL_Cobertura_Aereo (cursor.getDouble ("VL_Cobertura_Aereo"));

      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final List getByOID_Estado (int OID_Estado) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o PE_Aliquota_PIS do DSN
      // o PE_Aliquota_PIS de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Taxa_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Taxa, ");
      buff.append ("	Taxas.OID_Estado, ");
      buff.append ("	Taxas.OID_Estado_Destino, ");
      buff.append ("	Taxas.PE_Aliquota_ICMS, ");
      buff.append ("	Taxas.PE_Aliquota_Seguro, ");
      buff.append ("	Taxas.PE_Aliquota_PIS, ");
      buff.append ("	Taxas.CD_CFO, ");
      buff.append ("	Taxas.DM_ICMS_CIF, ");
      buff.append ("	Taxas.DM_ICMS_FOB, ");
      buff.append ("	Taxas.PE_Aliquota_COFINS, ");
      buff.append ("	Taxas.TX_Mensagem_Fiscal, ");
      buff.append ("	Estado_Origem.CD_Estado, ");
      buff.append ("	Estado_Origem.NM_Estado, ");
      buff.append ("	Estado_Destino.CD_Estado, ");
      buff.append ("	Estado_Destino.NM_Estado, ");
      buff.append ("	Taxas.PE_ICMS_Nao_Contribuinte, ");
      buff.append ("	Taxas.TX_Mensagem_Fiscal_ICMS, ");
      buff.append ("	Taxas.PE_Aliquota_ICMS_Aereo ");
      buff.append ("FROM Taxas, Estados Estado_Origem, Estados Estado_Destino ");
      buff.append ("WHERE Taxas.OID_Estado = Estado_Origem.OID_Estado ");
      buff.append ("AND Taxas.OID_Estado_Destino = Estado_Destino.OID_Estado ");
      buff.append ("AND Estado_Origem.OID_Estado =");
      buff.append (OID_Estado);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        TaxaBean p = new TaxaBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Estado (cursor.getInt (2));
        p.setOID_Estado_Destino (cursor.getInt (3));
        p.setPE_Aliquota_ICMS (cursor.getDouble (4));
        p.setPE_Aliquota_Seguro (cursor.getDouble (5));
        p.setPE_Aliquota_PIS (cursor.getDouble (6));
        p.setCD_CFO (cursor.getString (7));
        p.setDM_ICMS_CIF (cursor.getString (8));
        p.setDM_ICMS_FOB (cursor.getString (9));
        p.setPE_Aliquota_COFINS (cursor.getDouble (10));
        p.setTX_Mensagem_Fiscal (JavaUtil.getValueDef (cursor.getString (11) , "").trim ());
        p.setCD_Estado (cursor.getString (12));
        p.setNM_Estado (cursor.getString (13));
        p.setCD_Estado_Destino (cursor.getString (14));
        p.setNM_Estado_Destino (cursor.getString (15));
        p.setPE_ICMS_Nao_Contribuinte (cursor.getDouble (16));
        p.setTX_Mensagem_Fiscal_ICMS (cursor.getString ("TX_Mensagem_Fiscal_ICMS"));
        p.setPE_Aliquota_ICMS_Aereo (cursor.getDouble ("PE_Aliquota_ICMS_Aereo"));

        Taxa_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Taxa_Lista;
  }

  public static final List getByOID_Estado_Origem_Destino (int OID_Estado_Origem , int OID_Estado_Destino) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o PE_Aliquota_PIS do DSN
      // o PE_Aliquota_PIS de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Taxa_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Taxa, ");
      buff.append ("	Taxas.OID_Estado, ");
      buff.append ("	Taxas.OID_Estado_Destino, ");
      buff.append ("	Taxas.PE_Aliquota_ICMS, ");
      buff.append ("	Taxas.PE_Aliquota_Seguro, ");
      buff.append ("	Taxas.PE_Aliquota_PIS, ");
      buff.append ("	Taxas.CD_CFO, ");
      buff.append ("	Taxas.DM_ICMS_CIF, ");
      buff.append ("	Taxas.DM_ICMS_FOB, ");
      buff.append ("	Taxas.PE_Aliquota_COFINS, ");
      buff.append ("	Taxas.TX_Mensagem_Fiscal, ");
      buff.append ("	Estado_Origem.CD_Estado, ");
      buff.append ("	Estado_Origem.NM_Estado, ");
      buff.append ("	Estado_Destino.CD_Estado, ");
      buff.append ("	Estado_Destino.NM_Estado, ");
      buff.append ("	Taxas.PE_ICMS_Nao_Contribuinte, ");
      buff.append ("	Taxas.TX_Mensagem_Fiscal_ICMS, ");
      buff.append ("	Taxas.VL_Cobertura_Rodoviario, ");
      buff.append ("	Taxas.VL_Cobertura_Aereo, ");
      buff.append ("	Taxas.PE_Aliquota_Metropolitana, ");
      buff.append ("	Taxas.PE_Aliquota_ICMS_Aereo ");
      buff.append ("FROM Taxas, Estados Estado_Origem, Estados Estado_Destino ");
      buff.append ("WHERE Taxas.OID_Estado = Estado_Origem.OID_Estado ");
      buff.append ("AND Taxas.OID_Estado_Destino = Estado_Destino.OID_Estado ");
      buff.append ("AND Estado_Origem.OID_Estado =");
      buff.append (OID_Estado_Origem);
      buff.append ("AND Estado_Destino.OID_Estado =");
      buff.append (OID_Estado_Destino);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        TaxaBean p = new TaxaBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Estado (cursor.getInt (2));
        p.setOID_Estado_Destino (cursor.getInt (3));
        p.setPE_Aliquota_ICMS (cursor.getDouble (4));
        p.setPE_Aliquota_Seguro (cursor.getDouble (5));
        p.setPE_Aliquota_PIS (cursor.getDouble (6));
        p.setCD_CFO (cursor.getString (7));
        p.setDM_ICMS_CIF (cursor.getString (8));
        p.setDM_ICMS_FOB (cursor.getString (9));
        p.setPE_Aliquota_COFINS (cursor.getDouble (10));
        p.setTX_Mensagem_Fiscal (JavaUtil.getValueDef (cursor.getString (11) , "").trim ());
        p.setCD_Estado (cursor.getString (12));
        p.setNM_Estado (cursor.getString (13));
        p.setCD_Estado_Destino (cursor.getString (14));
        p.setNM_Estado_Destino (cursor.getString (15));
        p.setPE_ICMS_Nao_Contribuinte (cursor.getDouble (16));
        p.setTX_Mensagem_Fiscal_ICMS (cursor.getString ("TX_Mensagem_Fiscal_ICMS"));
        p.setPE_Aliquota_ICMS_Aereo (cursor.getDouble ("PE_Aliquota_ICMS_Aereo"));
        p.setPE_Aliquota_Metropolitana (cursor.getDouble ("PE_Aliquota_Metropolitana"));
        p.setVL_Cobertura_Rodoviario (cursor.getDouble ("VL_Cobertura_Rodoviario"));
        p.setVL_Cobertura_Aereo (cursor.getDouble ("VL_Cobertura_Aereo"));

        Taxa_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Taxa_Lista;
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

    List Taxa_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Taxas.OID_Taxa, ");
      buff.append ("	Taxas.OID_Estado, ");
      buff.append ("	Taxas.OID_Estado_Destino, ");
      buff.append ("	Taxas.PE_Aliquota_ICMS, ");
      buff.append ("	Taxas.PE_Aliquota_Seguro, ");
      buff.append ("	Taxas.PE_Aliquota_PIS, ");
      buff.append ("	Taxas.CD_CFO, ");
      buff.append ("	Taxas.DM_ICMS_CIF, ");
      buff.append ("	Taxas.DM_ICMS_FOB, ");
      buff.append ("	Taxas.PE_Aliquota_COFINS, ");
      buff.append ("	Taxas.TX_Mensagem_Fiscal, ");
      buff.append ("	Estado_Origem.CD_Estado, ");
      buff.append ("	Estado_Origem.NM_Estado, ");
      buff.append ("	Estado_Destino.CD_Estado, ");
      buff.append ("	Estado_Destino.NM_Estado, ");
      buff.append ("	Taxas.PE_ICMS_Nao_Contribuinte, ");
      buff.append ("	Taxas.TX_Mensagem_Fiscal_ICMS, ");
      buff.append ("	Taxas.PE_Aliquota_ICMS_Aereo ");
      buff.append ("FROM Taxas, Estados Estado_Origem, Estados Estado_Destino ");
      buff.append ("WHERE Taxas.OID_Estado = Estado_Origem.OID_Estado ");
      buff.append ("AND Taxas.OID_Estado_Destino = Estado_Destino.OID_Estado ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        TaxaBean p = new TaxaBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Estado (cursor.getInt (2));
        p.setOID_Estado_Destino (cursor.getInt (3));
        p.setPE_Aliquota_ICMS (cursor.getDouble (4));
        p.setPE_Aliquota_Seguro (cursor.getDouble (5));
        p.setPE_Aliquota_PIS (cursor.getDouble (6));
        p.setCD_CFO (cursor.getString (7));
        p.setDM_ICMS_CIF (cursor.getString (8));
        p.setDM_ICMS_FOB (cursor.getString (9));
        p.setPE_Aliquota_COFINS (cursor.getDouble (10));
        p.setTX_Mensagem_Fiscal (JavaUtil.getValueDef (cursor.getString (11) , "").trim ());
        p.setCD_Estado (cursor.getString (12));
        p.setNM_Estado (cursor.getString (13));
        p.setCD_Estado_Destino (cursor.getString (14));
        p.setNM_Estado_Destino (cursor.getString (15));
        p.setPE_ICMS_Nao_Contribuinte (cursor.getDouble (16));
        p.setTX_Mensagem_Fiscal_ICMS (cursor.getString ("TX_Mensagem_Fiscal_ICMS"));
        p.setPE_Aliquota_ICMS_Aereo (cursor.getDouble ("PE_Aliquota_ICMS_Aereo"));

        Taxa_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Taxa_Lista;
  }

  public double getPE_Aliquota_RCTR_DC () {
    return PE_Aliquota_RCTR_DC;
  }

  public double getPE_Aliquota_RCTA () {
    return PE_Aliquota_RCTA;
  }

  public double getPE_Aliquota_RCTR_VI () {
    return PE_Aliquota_RCTR_VI;
  }

  public double getPE_Aliquota_RCTRC () {
    return PE_Aliquota_RCTRC;
  }

  public void setPE_Aliquota_RCTRC (double PE_Aliquota_RCTRC) {
    this.PE_Aliquota_RCTRC = PE_Aliquota_RCTRC;
  }

  public void setPE_Aliquota_RCTR_VI (double PE_Aliquota_RCTR_VI) {
    this.PE_Aliquota_RCTR_VI = PE_Aliquota_RCTR_VI;
  }

  public void setPE_Aliquota_RCTA (double PE_Aliquota_RCTA) {
    this.PE_Aliquota_RCTA = PE_Aliquota_RCTA;
  }

  public void setPE_Aliquota_RCTR_DC (double PE_Aliquota_RCTR_DC) {
    this.PE_Aliquota_RCTR_DC = PE_Aliquota_RCTR_DC;
  }

  public String getNM_Campo_ICMS () {
    return NM_Campo_ICMS;
  }

  public void setNM_Campo_ICMS (String NM_Campo_ICMS) {
    this.NM_Campo_ICMS = NM_Campo_ICMS;
  }

  public void setVL_Cobertura_Rodoviario (double VL_Cobertura_Rodoviario) {
    this.VL_Cobertura_Rodoviario = VL_Cobertura_Rodoviario;
  }

  public double getVL_Cobertura_Rodoviario () {
    return VL_Cobertura_Rodoviario;
  }

  public void setVL_Cobertura_Aereo (double VL_Cobertura_Aereo) {
    this.VL_Cobertura_Aereo = VL_Cobertura_Aereo;
  }

  public double getVL_Cobertura_Aereo () {
    return VL_Cobertura_Aereo;
  }

  public void setPE_Aliquota_Metropolitana (double PE_Aliquota_Metropolitana) {
    this.PE_Aliquota_Metropolitana = PE_Aliquota_Metropolitana;
  }

  public double getPE_Aliquota_Metropolitana () {
    return PE_Aliquota_Metropolitana;
  }

public double getPE_Abatimento_ICMS() {
	return PE_Abatimento_ICMS;
}

public void setPE_Abatimento_ICMS(double abatimento_ICMS) {
	PE_Abatimento_ICMS = abatimento_ICMS;
}
}
