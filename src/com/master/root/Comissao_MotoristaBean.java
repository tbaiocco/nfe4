package com.master.root;

import java.sql.*;
import java.util.*;

import auth.*;
import com.master.util.*;

public class Comissao_MotoristaBean {
  private String CD_Modelo_Veiculo;
  private String NM_Modelo_Veiculo;
  private String CD_Estado;
  private String NM_Estado;
  private String CD_Estado_Destino;
  private String NM_Estado_Destino;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_Estado;
  private int oid_Estado_Destino;
  private double PE_Comissao_Motorista;
  private int OID_Modelo_Veiculo;  

  
  public Comissao_MotoristaBean () {}

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

  public String getCD_Modelo_Veiculo() {
	return CD_Modelo_Veiculo;
}

public void setCD_Modelo_Veiculo(String modelo_Veiculo) {
	CD_Modelo_Veiculo = modelo_Veiculo;
}

public String getNM_Modelo_Veiculo() {
	return NM_Modelo_Veiculo;
}

public void setNM_Modelo_Veiculo(String modelo_Veiculo) {
	NM_Modelo_Veiculo = modelo_Veiculo;
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
	      ResultSet cursor = stmt.executeQuery ("SELECT MAX(OID_Comissao_Motorista) FROM Comissoes_Motoristas");

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
	    buff.append ("INSERT INTO Comissoes_Motoristas (OID_Comissao_Motorista, OID_Estado, OID_Estado_Destino, OID_Modelo_Veiculo, PE_Comissao_Motorista, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
	    buff.append ("VALUES (?,?,?,?,?,?,?,?)");
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
	      pstmt.setInt (4 , getOID_Modelo_Veiculo ());
	      pstmt.setDouble (5 , getPE_Comissao_Motorista ());
	      pstmt.setDate (6 , FormataData.formataDataTB (getDt_Stamp ()));
	      pstmt.setString (7 , getUsuario_Stamp ());
	      pstmt.setString (8 , getDm_Stamp ());
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
  
  public void update() throws Exception {
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
	    buff.append ("UPDATE Comissoes_Motoristas SET OID_Estado=?, OID_Estado_Destino=?, OID_Modelo_Veiculo=?, PE_Comissao_Motorista=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?");
	    buff.append (" WHERE OID_Comissao_Motorista=?");
	    /*
	     * Define os dados do SQL
	     * e executa o insert no banco.
	     */
	    try {
	      PreparedStatement pstmt =
	          conn.prepareStatement (buff.toString ());
	      pstmt.setInt (1 , getOID_Estado ());
	      pstmt.setInt (2 , getOID_Estado_Destino ());
	      pstmt.setInt (3 , getOID_Modelo_Veiculo ());
	      pstmt.setDouble (4 , getPE_Comissao_Motorista ());
	      pstmt.setDate (5 , FormataData.formataDataTB (getDt_Stamp ()));
	      pstmt.setString (6 , getUsuario_Stamp ());
	      pstmt.setString (7 , getDm_Stamp ());
	      pstmt.setInt (8 , getOID ());
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
    buff.append ("DELETE FROM Comissoes_Motoristas ");
    buff.append ("WHERE OID_Comissao_Motorista=?");
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

  public static final Comissao_MotoristaBean getByOID (int oid) throws Exception {
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

    Comissao_MotoristaBean p = new Comissao_MotoristaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Comissao_Motorista, ");
      buff.append ("	Comissoes_Motoristas.OID_Estado, ");
      buff.append ("	Comissoes_Motoristas.OID_Estado_Destino, ");
      buff.append ("	Comissoes_Motoristas.PE_Comissao_Motorista, ");
      buff.append ("	Estado_Origem.CD_Estado, ");
      buff.append ("	Estado_Origem.NM_Estado, ");
      buff.append ("	Estado_Destino.CD_Estado, ");
      buff.append ("	Estado_Destino.NM_Estado ");
      buff.append ("FROM Comissoes_Motoristas, Estados Estado_Origem, Estados Estado_Destino ");
      buff.append ("WHERE Comissoes_Motoristas.OID_Estado = Estado_Origem.OID_Estado ");
      buff.append ("AND Comissoes_Motoristas.OID_Estado_Destino = Estado_Destino.OID_Estado ");
      buff.append ("AND Comissoes_Motoristas.OID_Comissao_Motorista = ");
      buff.append (oid);

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Estado (cursor.getInt (2));
        p.setOID_Estado_Destino (cursor.getInt (3));
        p.setPE_Comissao_Motorista (cursor.getDouble (4));
        p.setCD_Estado (cursor.getString (5).trim ());
        p.setNM_Estado (cursor.getString (6).trim ());
        p.setCD_Estado_Destino (cursor.getString (7).trim ());
        p.setNM_Estado_Destino (cursor.getString (8).trim ());
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

  public static final Comissao_MotoristaBean getByComissao_Motorista (int oid) throws Exception {
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

	    Comissao_MotoristaBean p = new Comissao_MotoristaBean ();
	    try {
	      StringBuffer buff = new StringBuffer ();
	      buff.append ("SELECT OID_Comissao_Motorista, ");
	      buff.append ("	Comissoes_Motoristas.OID_Estado, ");
	      buff.append ("	Comissoes_Motoristas.OID_Estado_Destino, ");
	      buff.append ("	Comissoes_Motoristas.PE_Comissao_Motorista, ");
	      buff.append ("	Estado_Origem.CD_Estado, ");
	      buff.append ("	Estado_Origem.NM_Estado, ");
	      buff.append ("	Estado_Destino.CD_Estado, ");
	      buff.append ("	Estado_Destino.NM_Estado, ");
	      buff.append ("	Modelos_Veiculos.CD_Modelo_Veiculo, ");      
	      buff.append ("	Modelos_Veiculos.NM_Modelo_Veiculo ");      
	      buff.append ("FROM Modelos_Veiculos,Comissoes_Motoristas, Estados Estado_Origem, Estados Estado_Destino ");
	      buff.append ("WHERE Comissoes_Motoristas.OID_Estado = Estado_Origem.OID_Estado ");
	      buff.append ("AND Comissoes_Motoristas.OID_Modelo_Veiculo = Modelos_Veiculos.OID_Modelo_Veiculo ");	      
	      buff.append ("AND Comissoes_Motoristas.OID_Estado_Destino = Estado_Destino.OID_Estado ");
	      buff.append ("AND Comissoes_Motoristas.OID_Comissao_Motorista = ");
	      buff.append (oid);

	      Statement stmt = conn.createStatement ();
	      ResultSet cursor = stmt.executeQuery (buff.toString ());

	      while (cursor.next ()) {
	        p.setOID (cursor.getInt (1));
	        p.setOID_Estado (cursor.getInt (2));
	        p.setOID_Estado_Destino (cursor.getInt (3));
	        p.setPE_Comissao_Motorista (cursor.getDouble (4));
	        p.setCD_Estado (cursor.getString (5).trim ());
	        p.setNM_Estado (cursor.getString (6).trim ());
	        p.setCD_Estado_Destino (cursor.getString (7).trim ());
	        p.setNM_Estado_Destino (cursor.getString (8).trim ());
	        p.setCD_Modelo_Veiculo(cursor.getString (9));
	        p.setNM_Modelo_Veiculo(cursor.getString (10));
	        
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
  
  public static final Comissao_MotoristaBean getByOID (int oid_Estado_Origem , int oid_Estado_Destino) throws Exception {
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

    Comissao_MotoristaBean p = new Comissao_MotoristaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Comissao_Motorista, ");
      buff.append ("	Comissoes_Motoristas.OID_Estado, ");
      buff.append ("	Comissoes_Motoristas.OID_Estado_Destino, ");
      buff.append ("	Comissoes_Motoristas.OID_Modelo_Veiculo, ");      
      buff.append ("	Comissoes_Motoristas.PE_Comissao_Motorista, ");
      buff.append ("	Estado_Origem.CD_Estado, ");
      buff.append ("	Estado_Origem.NM_Estado, ");
      buff.append ("	Estado_Destino.CD_Estado, ");
      buff.append ("	Estado_Destino.NM_Estado ");
      buff.append ("	Modelos_Veiculos.CD_Modelo_Veiculo, ");      
      buff.append ("	Modelos_Veiculos.NM_Modelo_Veiculo ");      
      buff.append ("FROM Modelos_Veiculos,Comissoes_Motoristas, Estados Estado_Origem, Estados Estado_Destino ");
      buff.append ("WHERE Comissoes_Motoristas.OID_Estado = Estado_Origem.OID_Estado ");
      buff.append ("AND Comissoes_Motoristas.OID_Modelo_Veiculo = Modelos_Veiculos.OID_Modelo_Veiculo ");
      buff.append ("AND Comissoes_Motoristas.OID_Estado_Destino = Estado_Destino.OID_Estado ");
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
        p.setPE_Comissao_Motorista (cursor.getDouble (4));
        p.setCD_Estado (cursor.getString (5).trim ());
        p.setNM_Estado (cursor.getString (6).trim ());
        p.setCD_Estado_Destino (cursor.getString (7).trim ());
        p.setNM_Estado_Destino (cursor.getString (8).trim ());
        p.setCD_Modelo_Veiculo(cursor.getString (9));
        p.setNM_Modelo_Veiculo(cursor.getString (10));

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

    List Comissoes_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Comissao_Motorista, ");
      buff.append ("	Comissoes_Motoristas.OID_Estado, ");
      buff.append ("	Comissoes_Motoristas.OID_Estado_Destino, ");
      buff.append ("	Comissoes_Motoristas.OID_Modelo_Veiculo, ");      
      buff.append ("	Comissoes_Motoristas.PE_Comissao_Motorista, ");
      buff.append ("	Estado_Origem.CD_Estado, ");
      buff.append ("	Estado_Origem.NM_Estado, ");
      buff.append ("	Estado_Destino.CD_Estado, ");
      buff.append ("	Estado_Destino.NM_Estado, ");
      buff.append ("	Modelos_Veiculos.CD_Modelo_Veiculo, ");      
      buff.append ("	Modelos_Veiculos.NM_Modelo_Veiculo ");      
      buff.append ("FROM Comissoes_Motoristas,Modelos_Veiculos, Estados Estado_Origem, Estados Estado_Destino ");
      buff.append ("WHERE Comissoes_Motoristas.OID_Estado = Estado_Origem.OID_Estado ");
      buff.append ("AND Comissoes_Motoristas.OID_Estado_Destino = Estado_Destino.OID_Estado ");
      buff.append ("AND Comissoes_Motoristas.OID_Modelo_Veiculo = Modelos_Veiculos.OID_Modelo_Veiculo ");      
      buff.append ("AND Estado_Origem.OID_Estado =");
      buff.append (OID_Estado);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Comissao_MotoristaBean p = new Comissao_MotoristaBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Estado (cursor.getInt (2));
        p.setOID_Estado_Destino (cursor.getInt (3));
        p.setPE_Comissao_Motorista (cursor.getDouble (4));
        p.setCD_Estado (cursor.getString (5));
        p.setNM_Estado (cursor.getString (6));
        p.setCD_Estado_Destino (cursor.getString (7));
        p.setNM_Estado_Destino (cursor.getString (8));
        p.setCD_Modelo_Veiculo(cursor.getString (9));
        p.setNM_Modelo_Veiculo(cursor.getString (10));

        Comissoes_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Comissoes_Lista;
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

    List Comissoes_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Comissao_Motorista, ");
      buff.append ("	Comissoes_Motoristas.OID_Estado, ");
      buff.append ("	Comissoes_Motoristas.OID_Estado_Destino, ");
      buff.append ("	Comissoes_Motoristas.PE_Comissao_Motorista, ");
      buff.append ("	Estado_Origem.CD_Estado, ");
      buff.append ("	Estado_Origem.NM_Estado, ");
      buff.append ("	Estado_Destino.CD_Estado, ");
      buff.append ("	Estado_Destino.NM_Estado ");
      buff.append ("FROM Comissoes_Motoristas, Estados Estado_Origem, Estados Estado_Destino ");
      buff.append ("WHERE Comissoes_Motoristas.OID_Estado = Estado_Origem.OID_Estado ");
      buff.append ("AND Comissoes_Motoristas.OID_Estado_Destino = Estado_Destino.OID_Estado ");
      buff.append ("AND Estado_Origem.OID_Estado =");
      buff.append (OID_Estado_Origem);
      buff.append ("AND Estado_Destino.OID_Estado =");
      buff.append (OID_Estado_Destino);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Comissao_MotoristaBean p = new Comissao_MotoristaBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Estado (cursor.getInt (2));
        p.setOID_Estado_Destino (cursor.getInt (3));
        p.setPE_Comissao_Motorista (cursor.getDouble (4));
        p.setCD_Estado (cursor.getString (5));
        p.setNM_Estado (cursor.getString (6));
        p.setCD_Estado_Destino (cursor.getString (7));
        p.setNM_Estado_Destino (cursor.getString (8));

        Comissoes_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Comissoes_Lista;
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

	    List Comissoes_Lista = new ArrayList ();
	    try {
	      StringBuffer buff = new StringBuffer ();
	      buff.append ("SELECT Comissoes_Motoristas.OID_Comissao_Motorista, ");
	      buff.append ("	Comissoes_Motoristas.OID_Estado, ");
	      buff.append ("	Comissoes_Motoristas.OID_Estado_Destino, ");
	      buff.append ("	Comissoes_Motoristas.PE_Comissao_Motorista, ");
	      buff.append ("	Estado_Origem.CD_Estado, ");
	      buff.append ("	Estado_Origem.NM_Estado, ");
	      buff.append ("	Estado_Destino.CD_Estado, ");
	      buff.append ("	Estado_Destino.NM_Estado ");
	      buff.append ("FROM Comissoes_Motoristas, Estados Estado_Origem, Estados Estado_Destino ");
	      buff.append ("WHERE Comissoes_Motoristas.OID_Estado = Estado_Origem.OID_Estado ");
	      buff.append ("AND Comissoes_Motoristas.OID_Estado_Destino = Estado_Destino.OID_Estado ");

	      Statement stmt = conn.createStatement ();
	      ResultSet cursor =
	          stmt.executeQuery (buff.toString ());

	      while (cursor.next ()) {
	        Comissao_MotoristaBean p = new Comissao_MotoristaBean ();
	        p.setOID (cursor.getInt (1));
	        p.setOID_Estado (cursor.getInt (2));
	        p.setOID_Estado_Destino (cursor.getInt (3));
	        p.setPE_Comissao_Motorista(cursor.getDouble (4));
	        p.setCD_Estado (cursor.getString (5));
	        p.setNM_Estado (cursor.getString (6));
	        p.setCD_Estado_Destino (cursor.getString (7));
	        p.setNM_Estado_Destino (cursor.getString (8));

	        Comissoes_Lista.add (p);
	      }
	      cursor.close ();
	      stmt.close ();
	      conn.close ();
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw e;
	    }
	    return Comissoes_Lista;
	  }
  

  public int getOID_Modelo_Veiculo() {
	return OID_Modelo_Veiculo;
}

public void setOID_Modelo_Veiculo(int OID_Modelo_Veiculo) {
	this.OID_Modelo_Veiculo = OID_Modelo_Veiculo;
}

public double getPE_Comissao_Motorista() {
	return PE_Comissao_Motorista;
}

public void setPE_Comissao_Motorista(double comissao_Motorista) {
	PE_Comissao_Motorista = comissao_Motorista;
}

}
