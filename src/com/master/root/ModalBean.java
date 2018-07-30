package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.master.util.FormataData;

import auth.OracleConnection2;

public class ModalBean {
  private String CD_Modal;
  private String NM_Modal;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private String DM_Tipo_Tabela_Frete;
  private String DM_Tipo_Coleta;
  private String DM_Tipo_Entrega;
  private String DM_Tipo_Transporte;
  private String DM_Grupo_Servico;

  public ModalBean () {}

  public String getCD_Modal () {
    return CD_Modal;
  }

  public void setCD_Modal (String CD_Modal) {
    this.CD_Modal = CD_Modal;
  }

  public String getNM_Modal () {
    return NM_Modal;
  }

  public void setNM_Modal (String NM_Modal) {
    this.NM_Modal = NM_Modal;
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
      // passando como parâmetro o NM_Modal do DSN
      // o NM_Modal de usuário e a senha do banco.
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
          "SELECT MAX(OID_Modal) FROM Modal");

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
    buff.append ("INSERT INTO Modal (OID_Modal, CD_Modal, NM_Modal, Dt_Stamp, Usuario_Stamp, Dm_Stamp, DM_Tipo_Tabela_Frete, DM_Tipo_Coleta, DM_Tipo_Entrega, DM_Tipo_Transporte, DM_Grupo_Servico) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?)");


    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setString (2 , getCD_Modal ());
      pstmt.setString (3 , getNM_Modal ());
      pstmt.setDate (4 , FormataData.formataDataTB (getDt_Stamp ()));
      pstmt.setString (5 , getUsuario_Stamp ());
      pstmt.setString (6 , getDm_Stamp ());
      pstmt.setString (7 , getDM_Tipo_Tabela_Frete ());
      pstmt.setString (8 , getDM_Tipo_Coleta ());
      pstmt.setString (9 , getDM_Tipo_Entrega ());
      pstmt.setString (10 , getDM_Tipo_Transporte ());
      pstmt.setString (11 , getDM_Grupo_Servico ());
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
      // passando como parâmetro o NM_Modal do DSN
      // o NM_Modal de usuário e a senha do banco.
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
    buff.append ("UPDATE Modal SET NM_Modal=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, DM_Tipo_Tabela_Frete=?, DM_Tipo_Coleta=?, DM_Tipo_Entrega=?, DM_Tipo_Transporte=?, DM_Grupo_Servico=? ");
    buff.append ("WHERE OID_Modal=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getNM_Modal ());
      pstmt.setDate (2 , FormataData.formataDataTB (getDt_Stamp ()));
      pstmt.setString (3 , getUsuario_Stamp ());
      pstmt.setString (4 , getDm_Stamp ());
      pstmt.setString (5 , getDM_Tipo_Tabela_Frete ());
      pstmt.setString (6 , getDM_Tipo_Coleta ());
      pstmt.setString (7 , getDM_Tipo_Entrega ());
      pstmt.setString (8 , getDM_Tipo_Transporte ());
      pstmt.setString (9 , getDM_Grupo_Servico ());
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
      // passando como parâmetro o NM_Modal do DSN
      // o NM_Modal de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Modal ");
    buff.append ("WHERE OID_Modal=?");
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

  public static final ModalBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Modal do DSN
      // o NM_Modal de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    ModalBean p = new ModalBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Modal, ");
      buff.append ("	CD_Modal, ");
      buff.append ("	NM_Modal, ");
      buff.append ("	DM_Tipo_Tabela_Frete, ");
      buff.append ("	DM_Tipo_Coleta,       ");
      buff.append ("	DM_Tipo_Entrega,      ");
      buff.append ("	DM_Grupo_Servico,      ");
      buff.append ("	DM_Tipo_Transporte    ");
      buff.append ("FROM Modal ");
      buff.append ("WHERE OID_Modal= ");
      buff.append (oid);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Modal (cursor.getString (2));
        p.setNM_Modal (cursor.getString (3));
        p.setDM_Tipo_Tabela_Frete (cursor.getString (4));
        p.setDM_Tipo_Coleta (cursor.getString (5));
        p.setDM_Tipo_Entrega (cursor.getString (6));
        p.setDM_Grupo_Servico (cursor.getString (7));
        p.setDM_Tipo_Transporte (cursor.getString (8));
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

  public static final ModalBean getByCD_Modal (String CD_Modal) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Modal do DSN
      // o NM_Modal de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    ModalBean p = new ModalBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Modal, ");
      buff.append ("	CD_Modal, ");
      buff.append ("	NM_Modal, ");
      buff.append ("	DM_Tipo_Tabela_Frete, ");
      buff.append ("	DM_Tipo_Coleta,       ");
      buff.append ("	DM_Tipo_Entrega,      ");
      buff.append ("	DM_Grupo_Servico,      ");
      buff.append ("	DM_Tipo_Transporte    ");
      buff.append ("FROM Modal ");
      buff.append ("WHERE CD_Modal= '");
      buff.append (CD_Modal);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Modal (cursor.getString (2));
        p.setNM_Modal (cursor.getString (3));
        p.setDM_Tipo_Tabela_Frete (cursor.getString (4));
        p.setDM_Tipo_Coleta (cursor.getString (5));
        p.setDM_Tipo_Entrega (cursor.getString (6));
        p.setDM_Grupo_Servico (cursor.getString (7));
        p.setDM_Tipo_Transporte (cursor.getString (8));

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

  public static final List getByNM_Modal (String NM_Modal) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Modal do DSN
      // o NM_Modal de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Modal_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Modal, ");
      buff.append ("	CD_Modal, ");
      buff.append ("	NM_Modal, ");
      buff.append ("	DM_Tipo_Tabela_Frete, ");
      buff.append ("	DM_Tipo_Coleta,       ");
      buff.append ("	DM_Tipo_Entrega,      ");
      buff.append ("	DM_Grupo_Servico,      ");
      buff.append ("	DM_Tipo_Transporte    ");
      buff.append ("FROM Modal ");
      buff.append ("WHERE NM_Modal LIKE'");
      buff.append (NM_Modal);
      buff.append ("%'");
      buff.append (" order BY DM_Grupo_Servico, CD_Modal ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        ModalBean p = new ModalBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Modal (cursor.getString (2));
        p.setNM_Modal (cursor.getString (3));
        p.setDM_Tipo_Tabela_Frete (cursor.getString (4));
        p.setDM_Tipo_Coleta (cursor.getString (5));
        p.setDM_Tipo_Entrega (cursor.getString (6));
        p.setDM_Grupo_Servico (cursor.getString (7));
        p.setDM_Tipo_Transporte (cursor.getString (8));

        Modal_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Modal_Lista;
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

    List Modal_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Modal, ");
      buff.append ("	CD_Modal, ");
      buff.append ("	NM_Modal, ");
      buff.append ("	DM_Tipo_Tabela_Frete, ");
      buff.append ("	DM_Tipo_Coleta,       ");
      buff.append ("	DM_Tipo_Entrega,      ");
      buff.append ("	DM_Grupo_Servico,      ");
      buff.append ("	DM_Tipo_Transporte    ");
      buff.append ("FROM Modal order BY  DM_Grupo_Servico,  CD_Modal");

      // System.out.println ("Modal " + buff.toString ());

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        ModalBean p = new ModalBean ();
        // System.out.println ("Modal 2");

        p.setOID (cursor.getInt (1));
        p.setCD_Modal (cursor.getString (2));
        p.setNM_Modal (cursor.getString (3));
        // System.out.println ("Modal 3");
        p.setDM_Tipo_Tabela_Frete (cursor.getString (4));
        p.setDM_Tipo_Coleta (cursor.getString (5));
        p.setDM_Tipo_Entrega (cursor.getString (6));
        p.setDM_Grupo_Servico (cursor.getString (7));
        p.setDM_Tipo_Transporte (cursor.getString (8));
        // System.out.println ("Modal 4");

        Modal_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Modal_Lista;
  }

  public static void main (String args[]) throws Exception {
    ModalBean pp = new ModalBean ();
    pp.setOID (1);
    pp.setCD_Modal ("LLL");
    pp.setNM_Modal ("Litro");
    pp.insert ();

    ModalBean p = getByOID (1);
    // //// System.out.println(p.getOID());
    // //// System.out.println(p.getCD_Modal());
    // //// System.out.println(p.getNM_Modal());



  }

  public void setDM_Tipo_Tabela_Frete (String DM_Tipo_Tabela_Frete) {
    this.DM_Tipo_Tabela_Frete = DM_Tipo_Tabela_Frete;
  }

  public String getDM_Tipo_Tabela_Frete () {
    return DM_Tipo_Tabela_Frete;
  }

  public void setDM_Tipo_Coleta (String DM_Tipo_Coleta) {
    this.DM_Tipo_Coleta = DM_Tipo_Coleta;
  }

  public String getDM_Tipo_Coleta () {
    return DM_Tipo_Coleta;
  }

  public void setDM_Tipo_Entrega (String DM_Tipo_Entrega) {
    this.DM_Tipo_Entrega = DM_Tipo_Entrega;
  }

  public String getDM_Tipo_Entrega () {
    return DM_Tipo_Entrega;
  }

  public void setDM_Tipo_Transporte (String DM_Tipo_Transporte) {
    this.DM_Tipo_Transporte = DM_Tipo_Transporte;
  }

  public String getDM_Tipo_Transporte () {
    return DM_Tipo_Transporte;
  }

public String getDM_Grupo_Servico() {
	return DM_Grupo_Servico;
}

public void setDM_Grupo_Servico(String grupo_Servico) {
	DM_Grupo_Servico = grupo_Servico;
}
}