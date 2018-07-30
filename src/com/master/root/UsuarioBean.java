package com.master.root;

import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import auth.*;
import com.master.ed.*;
import com.master.util.*;

public class UsuarioBean {
  private String CD_Usuario;
  private String NM_Usuario;
  private String NM_Senha;
  private String DM_Perfil;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_Unidade;
  public UnidadeBean edUnidade = new UnidadeBean ();
  private String OID_Pessoa;
  private long nr_Aux;
  

  public UsuarioBean () {}

  public String getCD_Usuario () {
    return CD_Usuario;
  }

  public void setCD_Usuario (String CD_Usuario) {
    this.CD_Usuario = CD_Usuario;
  }

  public String getNM_Usuario () {
    return NM_Usuario;
  }

  public void setNM_Usuario (String NM_Usuario) {
    this.NM_Usuario = NM_Usuario;
  }

  public String getNM_Senha () {
    return NM_Senha;
  }

  public void setNM_Senha (String NM_Senha) {
    this.NM_Senha = NM_Senha;
  }

  public String getDM_Perfil () {
    return DM_Perfil;
  }

  public void setDM_Perfil (String DM_Perfil) {
    this.DM_Perfil = DM_Perfil;
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

  public int getOID_Unidade () {
    return oid_Unidade;
  }

  public void setOID_Unidade (int n) {
    this.oid_Unidade = n;
  }

  public void insert () throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    try {
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (
          "SELECT MAX(OID_Usuario) FROM Usuarios");

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
    StringBuffer buff = new StringBuffer ();
    buff.append ("INSERT INTO Usuarios (OID_Usuario, OID_Unidade, CD_Usuario, NM_Usuario, NM_Senha, DM_Perfil, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?)");
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setInt (2 , getOID_Unidade ());
      pstmt.setString (3 , getCD_Usuario ());
      pstmt.setString (4 , getNM_Usuario ());
      pstmt.setString (5 , getNM_Senha ());
      pstmt.setString (6 , getDM_Perfil ());
      pstmt.setString (7 , getDt_Stamp ());
      pstmt.setString (8 , getUsuario_Stamp ());
      pstmt.setString (9 , getDm_Stamp ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
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
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Usuarios SET NM_Usuario=?, NM_Senha=?, DM_Perfil=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
    buff.append ("WHERE OID_Usuario=?");
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getNM_Usuario ());
      pstmt.setString (2 , getNM_Senha ());
      pstmt.setString (3 , getDM_Perfil ());
      pstmt.setString (4 , getDt_Stamp ());
      pstmt.setString (5 , getUsuario_Stamp ());
      pstmt.setString (6 , getDm_Stamp ());
      pstmt.setInt (7 , getOID ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
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
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    StringBuffer buff = new StringBuffer ();
    buff.append ("DELETE FROM Usuarios ");
    buff.append ("WHERE OID_Usuario=?");
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
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public static final UsuarioBean getByOID (int oid) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    UsuarioBean p = new UsuarioBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Usuario, ");
      buff.append ("	CD_Usuario, ");
      buff.append ("	NM_Usuario, ");
      buff.append ("	DM_Perfil, ");
      buff.append ("	OID_Unidade, ");
      buff.append ("	OID_Pessoa ");
      buff.append ("FROM Usuarios ");
      buff.append ("WHERE OID_Usuario= ");
      buff.append (oid);

      // System.out.println("USUARIO=>>>>>>>>" +buff.toString());
      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {

      // System.out.println("USUARIO ok=>>>>>>>>" +buff.toString());
        p.setOID (cursor.getInt (1));
        p.setCD_Usuario (cursor.getString (2));
        p.setNM_Usuario (cursor.getString (3));
        p.setDM_Perfil (cursor.getString (4));
        p.setOID_Unidade (cursor.getInt (5));
        p.setOID_Pessoa (cursor.getString (6));
        if (p.getOID_Unidade () > 0)
          p.edUnidade = UnidadeBean.getByOID_Unidade (p.getOID_Unidade ());

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

  public static final UsuarioBean getByCD_Usuario (String CD_Usuario) throws
      Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    UsuarioBean p = new UsuarioBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Usuario, ");
      buff.append ("	CD_Usuario, ");
      buff.append ("	NM_Usuario, ");
      buff.append ("	DM_Perfil, ");
      buff.append ("	OID_Unidade, ");
      buff.append ("	OID_Pessoa ");
      buff.append ("FROM Usuarios ");
      buff.append ("WHERE CD_Usuario= '");
      buff.append (CD_Usuario);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Usuario (cursor.getString (2));
        p.setNM_Usuario (cursor.getString (3));
        p.setDM_Perfil (cursor.getString (4));
        p.setOID_Unidade (cursor.getInt (5));
        p.setOID_Pessoa (cursor.getString (6));
        if (p.getOID_Unidade () > 0)
          p.edUnidade = UnidadeBean.getByOID_Unidade (p.getOID_Unidade ());
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

  public static final List getByNM_Usuario (String NM_Usuario) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Usuarios_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Usuario, ");
      buff.append ("	CD_Usuario, ");
      buff.append ("	NM_Usuario, ");
      buff.append ("	DM_Perfil, ");
      buff.append ("	OID_Unidade, ");
      buff.append ("	OID_Pessoa ");
      buff.append ("FROM Usuarios ");
      buff.append ("WHERE NM_Usuario LIKE'");
      buff.append (NM_Usuario);
      buff.append ("%'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        UsuarioBean p = new UsuarioBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Usuario (cursor.getString (2));
        p.setNM_Usuario (cursor.getString (3));
        p.setDM_Perfil (cursor.getString (4));
        p.setOID_Unidade (cursor.getInt (5));
        p.setOID_Pessoa (cursor.getString (6));
        if (p.getOID_Unidade () > 0)
          p.edUnidade = UnidadeBean.getByOID_Unidade (p.getOID_Unidade ());
        Usuarios_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Usuarios_Lista;
  }

  public static final List getAll () throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Usuarios_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Usuario, ");
      buff.append ("	CD_Usuario, ");
      buff.append ("	NM_Usuario, ");
      buff.append ("	DM_Perfil, ");
      buff.append ("	OID_Unidade ");
      buff.append ("FROM Usuarios");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        UsuarioBean p = new UsuarioBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Usuario (cursor.getString (2));
        p.setNM_Usuario (cursor.getString (3));
        p.setDM_Perfil (cursor.getString (4));
        p.setOID_Unidade (cursor.getInt (5));
        if (p.getOID_Unidade () > 0)
          p.edUnidade = UnidadeBean.getByOID_Unidade (p.getOID_Unidade ());
        Usuarios_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Usuarios_Lista;
  }

  public static final List getAllOperacional () throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Usuarios_Lista = new ArrayList ();
    try {

     String data_inicial= Data.getSomaDiaData(Data.getDataDMY(),-7);
     String nm_usuario="";
     int oid_Usuario=0;


      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Usuarios.OID_Usuario, ");
      buff.append ("	Usuarios.NM_Usuario, ");
      buff.append ("	Usuarios.OID_Unidade, ");
      buff.append ("	NR_Conhecimento ");
      buff.append (" FROM  Usuarios, Conhecimentos ");
      buff.append (" WHERE Usuarios.OID_Usuario =  Conhecimentos.OID_Usuario ");
      buff.append (" AND   Conhecimentos.DM_Impresso ='N' ");
      buff.append (" AND   Conhecimentos.DM_Situacao <>'C' ");
      buff.append (" AND   Conhecimentos.VL_Total_Frete > 0 ");
      buff.append (" AND   Conhecimentos.DT_Emissao>'"+data_inicial+ "'");
      buff.append (" ORDER BY Usuarios.NM_Usuario ");
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      long cont=0, tt_cont=0;
      while (cursor.next ()) {
        tt_cont++;
        if (!nm_usuario.equals(cursor.getString("NM_Usuario")) && !nm_usuario.equals("")){
          UsuarioBean p = new UsuarioBean ();
          p.setOID (oid_Usuario);
          p.setNM_Usuario (nm_usuario);
          p.setNr_Aux (cont);
          Usuarios_Lista.add (p);
          cont=0;
        }
        oid_Usuario=  cursor.getInt ("OID_Usuario");
        nm_usuario = cursor.getString("NM_Usuario");
        cont++;
      }
      if (cont>0){
        UsuarioBean p = new UsuarioBean ();
        p.setOID (oid_Usuario);
        p.setNM_Usuario (nm_usuario);
        p.setNr_Aux (cont);
        Usuarios_Lista.add (p);
      }
      UsuarioBean p = new UsuarioBean ();
      p.setOID (0);
      p.setNM_Usuario ("Todos...");
      p.setNr_Aux (tt_cont);
      Usuarios_Lista.add (p);
      
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Usuarios_Lista;
  }

  public static UsuarioED getUsuarioCorrente (HttpServletRequest request) throws
      Mensagens {
    Object usuarioED = RequestUtil.getSessionAttribute (request , "usuario");
    if (usuarioED instanceof UsuarioED) {
      return (UsuarioED) usuarioED;
    }
    else throw new Mensagens ("Atributo usuario da session não existe.");
  }

  public void setOID_Pessoa (String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }

  public void setNr_Aux (long nr_Aux) {
    this.nr_Aux = nr_Aux;
  }

  public long getNr_Aux () {
    return nr_Aux;
  }
}