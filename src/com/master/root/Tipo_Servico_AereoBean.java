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
import com.master.util.JavaUtil;
 

/**
 * @author Ralph Renato da Silva
 * @serial Tipos de serviços Aéreos
 * @serialData 02/2008
 */


public class Tipo_Servico_AereoBean {
  private int oid;
  private String CD_Tipo_Servico_Aereo;
  private String NM_Tipo_Servico_Aereo;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;


   
  
  public Tipo_Servico_AereoBean () {}

  public int getOID () {
	  return oid;
  }
  public void setOID (int n) {
	  this.oid = n;
  }
  
  public String getCD_Tipo_Servico_Aereo () {
	  return CD_Tipo_Servico_Aereo;
  }
  public void setCD_Tipo_Servico_Aereo (String CD_Tipo_Servico_Aereo) {
	  this.CD_Tipo_Servico_Aereo = CD_Tipo_Servico_Aereo;
  }

  public String getNM_Tipo_Servico_Aereo () {
	  return NM_Tipo_Servico_Aereo;
  }
  public void setNM_Tipo_Servico_Aereo (String NM_Tipo_Servico_Aereo) {
    this.NM_Tipo_Servico_Aereo = NM_Tipo_Servico_Aereo;
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


  public void insert () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Servico_Aereo do DSN
      // o NM_Tipo_Servico_Aereo de usuário e a senha do banco.
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
      ResultSet cursor = stmt.executeQuery ("SELECT MAX(OID_Tipo_Servico_Aereo) FROM Tipos_Servicos_Aereos");

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
    buff.append ("INSERT INTO Tipos_Servicos_Aereos (OID_Tipo_Servico_Aereo, CD_Tipo_Servico_Aereo, NM_Tipo_Servico_Aereo, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
    buff.append ("VALUES (?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setString (2 , getCD_Tipo_Servico_Aereo ());
      pstmt.setString (3 , getNM_Tipo_Servico_Aereo ());
      pstmt.setString (4 , getDt_Stamp ());
      pstmt.setString (5 , getUsuario_Stamp ());
      pstmt.setString (6 , getDm_Stamp ());
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
      // passando como parâmetro o NM_Tipo_Servico_Aereo do DSN
      // o NM_Tipo_Servico_Aereo de usuário e a senha do banco.
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
    buff.append ("UPDATE Tipos_Servicos_Aereos SET NM_Tipo_Servico_Aereo=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
    buff.append ("WHERE OID_Tipo_Servico_Aereo=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getNM_Tipo_Servico_Aereo ());
      pstmt.setString (2 , getDt_Stamp ());
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
      // passando como parâmetro o NM_Tipo_Servico_Aereo do DSN
      // o NM_Tipo_Servico_Aereo de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Tipos_Servicos_Aereos ");
    buff.append ("WHERE OID_Tipo_Servico_Aereo=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
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

  public static final Tipo_Servico_AereoBean getByOID (int oid , boolean autoNumera) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Servico_Aereo do DSN
      // o NM_Tipo_Servico_Aereo de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Tipo_Servico_AereoBean p = new Tipo_Servico_AereoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Servico_Aereo, ");
      buff.append ("	CD_Tipo_Servico_Aereo, ");
      buff.append ("	NM_Tipo_Servico_Aereo ");
      buff.append ("FROM Tipos_Servicos_Aereos ");
      buff.append ("WHERE OID_Tipo_Servico_Aereo= ");
      buff.append (oid);
      buff.append (" Order by CD_Tipo_Servico_Aereo");
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      if (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Servico_Aereo (cursor.getString (2));
        p.setNM_Tipo_Servico_Aereo (cursor.getString (3));
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , Tipo_Servico_AereoBean.class.getName () , "getByOID(int oid)");
    }
    return p;
  }

  public static final Tipo_Servico_AereoBean getByOID (int oid) throws Exception {
    return getByOID (oid , false);
  }

  public static final Tipo_Servico_AereoBean getByCD_Tipo_Servico_Aereo (String CD_Tipo_Servico_Aereo , boolean autoNumera) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Servico_Aereo do DSN
      // o NM_Tipo_Servico_Aereo de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Tipo_Servico_AereoBean p = new Tipo_Servico_AereoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Servico_Aereo, ");
      buff.append ("	CD_Tipo_Servico_Aereo, ");
      buff.append ("	NM_Tipo_Servico_Aereo ");
      buff.append ("FROM Tipos_Servicos_Aereos ");
      buff.append ("WHERE CD_Tipo_Servico_Aereo= '");
      buff.append (CD_Tipo_Servico_Aereo);
      buff.append ("'");
      buff.append (" Order by CD_Tipo_Servico_Aereo");
      
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Servico_Aereo (cursor.getString (2));
        p.setNM_Tipo_Servico_Aereo (cursor.getString (3));
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

  public static final Tipo_Servico_AereoBean getByCD_Tipo_Servico_Aereo (String CD_Tipo_Servico_Aereo) throws Exception {
    return getByCD_Tipo_Servico_Aereo (CD_Tipo_Servico_Aereo , false);
  }

  public static final List getByNM_Tipo_Servico_Aereo (String NM_Tipo_Servico_Aereo) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Servico_Aereo do DSN
      // o NM_Tipo_Servico_Aereo de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Tipos_Servicos_Aereos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Servico_Aereo, ");
      buff.append ("	CD_Tipo_Servico_Aereo, ");
      buff.append ("	NM_Tipo_Servico_Aereo ");
      buff.append ("FROM Tipos_Servicos_Aereos ");
      buff.append ("WHERE NM_Tipo_Servico_Aereo LIKE'");
      buff.append (NM_Tipo_Servico_Aereo + "%'");
      buff.append (" ORDER BY CD_Tipo_Servico_Aereo");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Tipo_Servico_AereoBean p = new Tipo_Servico_AereoBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Servico_Aereo (cursor.getString (2));
        p.setNM_Tipo_Servico_Aereo (cursor.getString (3));
        Tipos_Servicos_Aereos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Tipos_Servicos_Aereos_Lista;
  }

  public static final List getByCD_Tipo_Aereo (String CD_Tipo_Aereo) throws Exception {
	    /*
	     * Abre a conexão com o banco
	     */
	    Connection conn = null;
	    try {
	      // Pede uma conexão ao gerenciador do driver
	      // passando como parâmetro o NM_Tipo_Servico_Aereo do DSN
	      // o NM_Tipo_Servico_Aereo de usuário e a senha do banco.
	      conn = OracleConnection2.getWEB ();
	      conn.setAutoCommit (false);
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw e;
	    }

	    List Tipos_Servicos_Aereos_Lista = new ArrayList ();
	    try {
	      Tipo_Servico_AereoBean x = new Tipo_Servico_AereoBean ();
	      StringBuffer buff = new StringBuffer ();
	      buff.append ("SELECT OID_Tipo_Servico_Aereo, ");
	      buff.append ("	CD_Tipo_Servico_Aereo, ");
	      buff.append ("	NM_Tipo_Servico_Aereo ");
	      buff.append ("FROM Tipos_Servicos_Aereos ");
	      buff.append ("WHERE 1=1");
		  if (JavaUtil.doValida(CD_Tipo_Aereo)){
			  buff.append(" AND cd_tipo_servico_aereo='"+CD_Tipo_Aereo+"'");
		  }
	      buff.append (" ORDER BY CD_Tipo_Servico_Aereo");
	      
	      Statement stmt = conn.createStatement ();
	      ResultSet cursor = stmt.executeQuery (buff.toString ());

	      while (cursor.next ()) {
	          Tipo_Servico_AereoBean p = new Tipo_Servico_AereoBean ();
	          p.setOID (cursor.getInt (1));
	          p.setCD_Tipo_Servico_Aereo (cursor.getString (2));
	          p.setNM_Tipo_Servico_Aereo (cursor.getString (3));
	          Tipos_Servicos_Aereos_Lista.add (p);
	      }
	      cursor.close ();
	      stmt.close ();
	      conn.close ();
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	    }
	    return Tipos_Servicos_Aereos_Lista;
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

    List Tipos_Servicos_Aereos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Tipo_Servico_Aereo, ");
      buff.append ("	CD_Tipo_Servico_Aereo, ");
      buff.append ("	NM_Tipo_Servico_Aereo, ");
      buff.append ("FROM Tipos_Servicos_Aereos ORDER BY NM_Tipo_Servico_Aereo ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Tipo_Servico_AereoBean p = new Tipo_Servico_AereoBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Tipo_Servico_Aereo (cursor.getString (2));
        p.setNM_Tipo_Servico_Aereo (cursor.getString (3));
        Tipos_Servicos_Aereos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Tipos_Servicos_Aereos_Lista;
  }

}
