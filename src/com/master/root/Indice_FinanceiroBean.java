package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

import com.master.util.*;

public class Indice_FinanceiroBean {
  private String DT_Cotacao;
  private double VL_Cotacao;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_Moeda;

  public Indice_FinanceiroBean () {}

  public int getOID_Moeda () {
    return oid_Moeda;
  }

  public void setOID_Moeda (int n) {
    this.oid_Moeda = n;
  }

  public String getDT_Cotacao () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Cotacao);
    DT_Cotacao = DataFormatada.getDT_FormataData ();

    return DT_Cotacao;
  }

  public void setDT_Cotacao (String DT_Cotacao) {
    this.DT_Cotacao = DT_Cotacao;
  }

  public double getVL_Cotacao () {
    return VL_Cotacao;
  }

  public void setVL_Cotacao (double VL_Cotacao) {
    this.VL_Cotacao = VL_Cotacao;
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
    //*** Valida para não deixar incluir duas cotações iguais no mesmo dia
     if (new BancoUtil ().doExiste ("Indice_Financeiro" ,
                                    "oid_Moeda = " + this.getOID_Moeda () +
                                    " AND DT_Cotacao = '" + FormataData.formataDataTB (this.DT_Cotacao) + "'")) {
       throw new Mensagens ("Indice Financeiro ja cadastrado para esse dia!");
     }

    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o VL_Cotacao do DSN
      // o VL_Cotacao de usuário e a senha do banco.
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
          "SELECT MAX(OID_Indice_Financeiro) FROM Indice_Financeiro");

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
    buff.append ("INSERT INTO Indice_Financeiro (OID_Indice_Financeiro, OID_Moeda, DT_Cotacao, VL_Cotacao, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
    buff.append ("VALUES (?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setInt (2 , getOID_Moeda ());
      pstmt.setString (3 , this.DT_Cotacao);
      pstmt.setDouble (4 , getVL_Cotacao ());
      pstmt.setString (5 , getDt_Stamp ());
      pstmt.setString (6 , getUsuario_Stamp ());
      pstmt.setString (7 , getDm_Stamp ());
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
      // passando como parâmetro o VL_Cotacao do DSN
      // o VL_Cotacao de usuário e a senha do banco.
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
    buff.append ("UPDATE Indice_Financeiro SET DT_Cotacao=?, VL_Cotacao=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
    buff.append ("WHERE OID_Indice_Financeiro=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , this.DT_Cotacao);
      pstmt.setDouble (2 , getVL_Cotacao ());
      pstmt.setString (3 , getDt_Stamp ());
      pstmt.setString (4 , getUsuario_Stamp ());
      pstmt.setString (5 , getDm_Stamp ());
      pstmt.setInt (6 , getOID ());
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
      // passando como parâmetro o VL_Cotacao do DSN
      // o VL_Cotacao de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Indice_Financeiro ");
    buff.append ("WHERE OID_Indice_Financeiro=?");
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

  public static final Indice_FinanceiroBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o VL_Cotacao do DSN
      // o VL_Cotacao de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Indice_FinanceiroBean p = new Indice_FinanceiroBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Indice_Financeiro, ");
      buff.append ("	OID_Moeda, ");
      buff.append ("	DT_Cotacao, ");
      buff.append ("	VL_Cotacao ");
      buff.append ("FROM Indice_Financeiro ");
      buff.append ("WHERE OID_Indice_Financeiro= ");
      buff.append (oid);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Moeda (cursor.getInt (2));
        p.setDT_Cotacao (cursor.getString (3));
        p.setVL_Cotacao (cursor.getDouble (4));
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

  public static final Indice_FinanceiroBean getByCotacao (int oid_Moeda , String DT_Cotacao) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o VL_Cotacao do DSN
      // o VL_Cotacao de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Indice_FinanceiroBean p = new Indice_FinanceiroBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Indice_Financeiro, ");
      buff.append ("	OID_Moeda, ");
      buff.append ("	DT_Cotacao, ");
      buff.append ("	VL_Cotacao ");
      buff.append ("FROM Indice_Financeiro ");
      buff.append ("WHERE OID_Moeda = " + oid_Moeda);

      if (JavaUtil.doValida (DT_Cotacao)) {
        buff.append (" AND DT_Cotacao = '" + DT_Cotacao + "'");
      }

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Moeda (cursor.getInt (2));
        p.setDT_Cotacao (cursor.getString (3));
        p.setVL_Cotacao (cursor.getDouble (4));
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

  public static final Indice_FinanceiroBean getByUltimaCotacao (int oid_Moeda) throws Excecoes{
	  try{
		  return getByUltimaCotacao(oid_Moeda, Data.getDataDMY());
	  } catch (Exception e){
		  throw new Excecoes();
	  }
  }

  public static final Indice_FinanceiroBean getByUltimaCotacao (int oid_Moeda , String data) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o VL_Cotacao do DSN
      // o VL_Cotacao de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Indice_FinanceiroBean p = new Indice_FinanceiroBean ();
    try {

      String sql = " SELECT Indice_Financeiro.OID_Indice_Financeiro, " +
          " Indice_Financeiro.OID_Moeda,  " +
          " Indice_Financeiro.DT_Cotacao, " +
          " Indice_Financeiro.VL_Cotacao" +
          " FROM Indice_Financeiro " +
          " WHERE Indice_Financeiro.OID_Moeda = " + oid_Moeda;
      sql += " AND Indice_Financeiro.DT_Cotacao <= '" + data + "' order by DT_Cotacao desc limit 1";

      // System.out.println (sql);

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sql);

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Moeda (cursor.getInt (2));
        p.setDT_Cotacao (cursor.getString (3));
        p.setVL_Cotacao (cursor.getDouble (4));
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

  public static final List getByOID_Moeda (int OID_Moeda) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o VL_Cotacao do DSN
      // o VL_Cotacao de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Indice_Financeiro_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Indice_Financeiro, ");
      buff.append ("	OID_Moeda, ");
      buff.append ("	DT_Cotacao, ");
      buff.append ("	VL_Cotacao ");
      buff.append ("FROM Indice_Financeiro ");
      buff.append ("WHERE OID_Moeda =");
      buff.append (OID_Moeda);
      buff.append (" ORDER BY DT_Cotacao desc");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Indice_FinanceiroBean p = new Indice_FinanceiroBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Moeda (cursor.getInt (2));
        p.setDT_Cotacao (cursor.getString (3));
        p.setVL_Cotacao (cursor.getDouble (4));
        Indice_Financeiro_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Indice_Financeiro_Lista;
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

    List Indice_Financeiro_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Indice_Financeiro, ");
      buff.append ("	OID_Moeda, ");
      buff.append ("	DT_Cotacao, ");
      buff.append ("	VL_Cotacao ");
      buff.append ("FROM Indice_Financeiro");
      buff.append (" ORDER BY DT_Cotacao desc");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Indice_FinanceiroBean p = new Indice_FinanceiroBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Moeda (cursor.getInt (2));
        p.setDT_Cotacao (cursor.getString (3));
        p.setVL_Cotacao (cursor.getDouble (4));
        Indice_Financeiro_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Indice_Financeiro_Lista;
  }

  public static void main (String args[]) throws Exception {
    Indice_FinanceiroBean pp = new Indice_FinanceiroBean ();
    pp.setOID (3);
    pp.setVL_Cotacao (9000);
    //pp.update();

    Indice_FinanceiroBean p = getByCotacao (2 , "10/10/2002");
    // //// System.out.println(p.getOID());
    // //// System.out.println(p.getOID_Moeda());
    // //// System.out.println(p.getDT_Cotacao());
    // //// System.out.println(p.getVL_Cotacao());

  }
}
