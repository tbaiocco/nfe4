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

public class Cia_AereaBean {

	
  /**
   * @author Ralph Renato da Silva
   * @serial Companhias Aéreas
   * @serialData 02/2008
   */
	
  private int oid;
  private String CD_Cia_Aerea;
  private String DM_Formulario_Minuta_Despacho;
  private String DM_Formulario_Awb;
  private long NR_Apolice_Seguro;
  private String NM_Seguradora;	
  private long NR_Conta_Corrente;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;



  
  public Cia_AereaBean () {}


  	    public int getOID () {
	        return oid;
	    }
	    public void setOID (int n) {
	        this.oid = n;
	    }

		public String getCD_Cia_Aerea() {
			return CD_Cia_Aerea;
		}
		public void setCD_Cia_Aerea(String cia_Aerea) {
			CD_Cia_Aerea = cia_Aerea;
		}
		
		public long getNR_Apolice_Seguro() {
			return NR_Apolice_Seguro;
		}
		public void setNR_Apolice_Seguro(long apolice_Seguro) {
			NR_Apolice_Seguro = apolice_Seguro;
		}
		
		public String getDM_Formulario_Awb() {
			return DM_Formulario_Awb;
		}
		public void setDM_Formulario_Awb(String formulario_Awb) {
			DM_Formulario_Awb = formulario_Awb;
		}
		
		public String getDM_Formulario_Minuta_Despacho() {
			return DM_Formulario_Minuta_Despacho;
		}
		public void setDM_Formulario_Minuta_Despacho(String formulario_Minuta_Despacho) {
			DM_Formulario_Minuta_Despacho = formulario_Minuta_Despacho;
		}
		
		public String getNM_Seguradora() {
			return NM_Seguradora;
		}
		public void setNM_Seguradora(String seguradora) {
			NM_Seguradora = seguradora;
		}
		
				
		public long getNR_Conta_Corrente() {
			return NR_Conta_Corrente;
		}
		public void setNR_Conta_Corrente(long conta_Corrente) {
			NR_Conta_Corrente = conta_Corrente;
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
      ResultSet cursor = stmt.executeQuery (
          "SELECT MAX(oid_cia_aerea) FROM cia_aereas");

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
    buff.append ("INSERT INTO cia_aereas (oid_cia_aerea, cd_cia_aerea, dm_formulario_minuta_despacho, dm_formulario_awb, nr_apolice_seguro, nm_seguradora, nr_conta_corrente, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setString (2 , getCD_Cia_Aerea());
      pstmt.setString (3 , getDM_Formulario_Minuta_Despacho());
      pstmt.setString (4 , getDM_Formulario_Awb());
      pstmt.setLong   (5 , getNR_Apolice_Seguro());
      pstmt.setString (6 , getNM_Seguradora());
      pstmt.setLong   (7 , getNR_Conta_Corrente());
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
    buff.append ("UPDATE " +
    			 "	cia_aereas " +
    			 "SET " +
    			 " cd_cia_aerea=?," +
    			 " dm_formulario_minuta_despacho=?," +
    			 " dm_formulario_awb=?," +
    			 " nr_apolice_seguro=?," +
    			 " nm_seguradora=?," +
    			 " nr_conta_corrente=?," +
    			 " Dt_Stamp=?," +
    			 " Usuario_Stamp=?," +
    			 " Dm_Stamp=? "
    			 );
    buff.append ("WHERE oid_cia_aerea=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getCD_Cia_Aerea());
      pstmt.setString (2 , getDM_Formulario_Minuta_Despacho());
      pstmt.setString (3 , getDM_Formulario_Awb());
      pstmt.setLong   (4 , getNR_Apolice_Seguro());
      pstmt.setString (5 , getNM_Seguradora());
      pstmt.setLong   (6 , getNR_Conta_Corrente());
      pstmt.setString (7 , getDt_Stamp ());
      pstmt.setString (8 , getUsuario_Stamp ());
      pstmt.setString (9 , getDm_Stamp ());
      pstmt.setInt    (10 , getOID ());
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
    buff.append ("DELETE FROM cia_aereas ");
    buff.append ("WHERE oid_cia_aerea=?");
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

  public static final Cia_AereaBean getByOID (int oid , boolean autoNumera) throws Exception {
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

	    Cia_AereaBean p = new Cia_AereaBean ();
	    try {
	      StringBuffer buff = new StringBuffer ();
	      buff.append ("SELECT oid_cia_aerea, ");
	      buff.append ("cd_cia_aerea,");
	      buff.append ("dm_formulario_minuta_despacho,");
	      buff.append ("dm_formulario_awb,");
	      buff.append ("nr_apolice_seguro,");
	      buff.append ("nm_seguradora,");
	      buff.append ("nr_conta_corrente ");
	      buff.append ("FROM cia_aereas ");
	      buff.append ("WHERE oid_cia_aerea= ");
	      buff.append (oid);

	      Statement stmt = conn.createStatement ();
	      ResultSet cursor =
	          stmt.executeQuery (buff.toString ());

	      if (cursor.next ()) {
	        p.setOID  (cursor.getInt (1));
	        p.setCD_Cia_Aerea(cursor.getString (2));
	        p.setDM_Formulario_Minuta_Despacho(cursor.getString (3));
	        p.setDM_Formulario_Awb(cursor.getString (4));
	        p.setNR_Apolice_Seguro(cursor.getLong (5));
	        p.setNM_Seguradora(cursor.getString (6));
	        p.setNR_Conta_Corrente(cursor.getLong (7));
	      }
	      cursor.close ();
	      stmt.close ();
	      conn.close ();
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , Cia_AereaBean.class.getName () , "getByOID(int oid)");
	    }
	    return p;
	  }

  public static final Cia_AereaBean getByOID (int oid) throws Exception {
	    return getByOID (oid , false);
	  }


	  
  public static final List getByCD_Cia_Aerea (String CD_Cia) throws Exception {
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

	    List Cia_Aerea_Lista = new ArrayList ();
	    try {
	      StringBuffer buff = new StringBuffer ();
	      buff.append ("SELECT oid_cia_aerea, ");
	      buff.append ("	cd_cia_aerea, ");
	      buff.append ("	dm_formulario_minuta_despacho, ");
	      buff.append ("	dm_formulario_awb, ");
	      buff.append ("	nr_apolice_seguro, ");
	      buff.append ("	nm_seguradora, ");
	      buff.append ("	nr_conta_corrente ");
	      buff.append (" FROM cia_aereas ");
	      if (CD_Cia == null){
	    	  // System.out.println(CD_Cia +"   passei" );
	    	  buff.append (" WHERE cd_cia_aerea LIKE'"+CD_Cia+"'");
	      }
	      buff.append (" ORDER BY cd_cia_aerea ");

	      Statement stmt = conn.createStatement ();
	      ResultSet cursor =
	          stmt.executeQuery (buff.toString ());

	      while (cursor.next ()) {
	        Cia_AereaBean p = new Cia_AereaBean ();
	        p.setOID  (cursor.getInt (1));
	        p.setCD_Cia_Aerea(cursor.getString (2));
	        p.setDM_Formulario_Minuta_Despacho(cursor.getString (3));
	        p.setDM_Formulario_Awb(cursor.getString (4));
	        p.setNR_Apolice_Seguro(cursor.getLong (5));
	        p.setNM_Seguradora(cursor.getString (6));
	        p.setNR_Conta_Corrente(cursor.getLong (7));
	        Cia_Aerea_Lista.add (p);
	      }
	      cursor.close ();
	      stmt.close ();
	      conn.close ();
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	    }
	    return Cia_Aerea_Lista;
	  }

  
  
  public static final Cia_AereaBean getByCD_Cia (String f_CD_Cia_Aerea) throws Exception {
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

	    Cia_AereaBean p = new Cia_AereaBean ();
	    try {
	      StringBuffer buff = new StringBuffer ();
	      buff.append ("SELECT oid_cia_aerea, ");
	      buff.append ("cd_cia_aerea, ");
	      buff.append ("dm_formulario_minuta_despacho, ");
	      buff.append ("dm_formulario_awb, ");
	      buff.append ("nr_apolice_seguro, ");
	      buff.append ("nm_seguradora, ");
	      buff.append ("nr_conta_corrente ");
	      buff.append ("FROM cia_aereas ");
	      buff.append ("WHERE cd_cia_aerea LIKE'"+f_CD_Cia_Aerea+"%' ");
	      buff.append ("ORDER BY cd_cia_aerea ");

	      Statement stmt = conn.createStatement ();
	      ResultSet cursor =
	          stmt.executeQuery (buff.toString ());

	      while (cursor.next ()) {
		        p.setOID  (cursor.getInt (1));
		        p.setCD_Cia_Aerea(cursor.getString (2));
		        p.setDM_Formulario_Minuta_Despacho(cursor.getString (3));
		        p.setDM_Formulario_Awb(cursor.getString (4));
		        p.setNR_Apolice_Seguro(cursor.getLong (5));
		        p.setNM_Seguradora(cursor.getString (6));
		        p.setNR_Conta_Corrente(cursor.getLong (7));
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

    List Cias_Aereas_Lista = new ArrayList ();
    try {
      Cia_AereaBean x = new Cia_AereaBean ();	
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT oid_cia_aerea, ");
      buff.append ("cd_cia_aerea, ");
      buff.append ("dm_formulario_minuta_despacho, ");
      buff.append ("dm_formulario_awb, ");
      buff.append ("nr_apolice_seguro, ");
      buff.append ("nm_seguradora, ");
      buff.append ("nr_conta_corrente ");
      buff.append (" FROM cia_aereas ");
      buff.append (" WHERE 1=1 ");

      if (x.CD_Cia_Aerea != null){
    	  buff.append (" AND cd_cia_aerea='"+x.CD_Cia_Aerea+"'");
      }

      buff.append (" ORDER BY cd_cia_aerea ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Cia_AereaBean p = new Cia_AereaBean ();
        p.setOID  (cursor.getInt (1));
        p.setCD_Cia_Aerea(cursor.getString (2));
        p.setDM_Formulario_Minuta_Despacho(cursor.getString (3));
        p.setDM_Formulario_Awb(cursor.getString (4));
        p.setNR_Apolice_Seguro(cursor.getLong (5));
        p.setNM_Seguradora(cursor.getString (6));
        p.setNR_Conta_Corrente(cursor.getLong (7));
        Cias_Aereas_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Cias_Aereas_Lista;
  }

}
