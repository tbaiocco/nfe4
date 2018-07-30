package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.rl.Liberacao_VeiculoRL;
import com.master.util.JavaUtil;

import auth.OracleConnection2;

public class Liberacao_VeiculoBean {
  private String DT_Liberacao;
  private String NM_Liberacao;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_Seguradora;
  private String NM_Seguradora;
  private String oid_Veiculo;

  public Liberacao_VeiculoBean () {
    NM_Liberacao = " ";
  }

  public String getDT_Liberacao () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Liberacao);
    DT_Liberacao = DataFormatada.getDT_FormataData ();

    return DT_Liberacao;
  }

  public void setDT_Liberacao (String DT_Liberacao) {
    this.DT_Liberacao = DT_Liberacao;
  }

  public String getNM_Liberacao () {
    return NM_Liberacao;
  }

  public void setNM_Liberacao (String NM_Liberacao) {
    this.NM_Liberacao = NM_Liberacao;
  }

  public String getOID_Veiculo () {
    return oid_Veiculo;
  }

  public void setOID_Veiculo (String oid_Veiculo) {
    this.oid_Veiculo = oid_Veiculo;
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

  public int getOID_Seguradora () {
    return oid_Seguradora;
  }

  public void setOID_Seguradora (int n) {
    this.oid_Seguradora = n;
  }

  public String getNM_Seguradora () {
    return NM_Seguradora;
  }

  public void setNM_Seguradora (String NM_Seguradora) {
    this.NM_Seguradora = NM_Seguradora;
  }

  public void insert () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Usuario do DSN
      // o NM_Usuario de usuário e a URL do banco.
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
          "SELECT MAX(OID_Liberacao_Veiculo) FROM liberacoes_veiculos");

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
    buff.append ("INSERT INTO liberacoes_veiculos (OID_Liberacao_Veiculo, OID_Veiculo, OID_Seguradora, DT_Liberacao, NM_Liberacao, DT_Stamp, Usuario_Stamp, Dm_Stamp) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setString (2 , getOID_Veiculo ());
      pstmt.setInt (3 , getOID_Seguradora ());
      pstmt.setString (4 , this.DT_Liberacao);
      pstmt.setString (5 , getNM_Liberacao ());
      pstmt.setString (6 , getDt_Stamp ());
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

  public void update () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Liberacao do DSN
      // o NM_Liberacao de usuário e a senha do banco.
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
    buff.append ("UPDATE liberacoes_veiculos SET OID_Seguradora=?, NM_Liberacao=?,  DT_Liberacao=? ");
    buff.append ("WHERE OID_Liberacao_Veiculo=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setInt (1 , getOID_Seguradora ());
      pstmt.setString (2 , getNM_Liberacao ());
      pstmt.setString (3 , this.DT_Liberacao);
      pstmt.setInt (4 , getOID ());
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
      // passando como parâmetro o NM_Liberacao do DSN
      // o NM_Liberacao de usuário e a senha do banco.
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
    buff.append ("DELETE FROM liberacoes_veiculos ");
    buff.append ("WHERE OID_Liberacao_Veiculo=?");
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

  public static final Liberacao_VeiculoBean getByOID (String oid)

      throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Liberacao do DSN
      // o NM_Liberacao de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Liberacao_VeiculoBean p = new Liberacao_VeiculoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT liberacoes_veiculos.OID_Liberacao_Veiculo, ");
      buff.append ("	liberacoes_veiculos.OID_Seguradora, ");
      buff.append ("	liberacoes_veiculos.OID_Veiculo, ");
      buff.append ("	liberacoes_veiculos.DT_Liberacao, ");
      buff.append ("	liberacoes_veiculos.NM_Liberacao, ");
      buff.append ("	Seguradoras.NM_Seguradora  ");
      buff.append (" FROM liberacoes_veiculos, ");
      buff.append ("      Seguradoras ");
      buff.append (" WHERE (liberacoes_veiculos.oid_Seguradora 		     = Seguradoras.oid_Seguradora )");
      buff.append ("AND OID_Liberacao_Veiculo= '");
      buff.append (oid);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Seguradora (cursor.getInt (2));
        p.setOID_Veiculo (cursor.getString (3));
        p.setDT_Liberacao (cursor.getString (4));
        p.setNM_Liberacao (cursor.getString (5));
        p.setNM_Seguradora (cursor.getString (6));
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

  public static final Liberacao_VeiculoBean getByLiberacao (String oid_Veiculo, String data_Liberacao)

      throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Liberacao do DSN
      // o NM_Liberacao de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Liberacao_VeiculoBean p = new Liberacao_VeiculoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT liberacoes_veiculos.OID_Liberacao_Veiculo, ");
      buff.append ("	liberacoes_veiculos.OID_Seguradora, ");
      buff.append ("	liberacoes_veiculos.OID_Veiculo, ");
      buff.append ("	liberacoes_veiculos.DT_Liberacao, ");
      buff.append ("	liberacoes_veiculos.NM_Liberacao, ");
      buff.append ("	Seguradoras.NM_Seguradora  ");
      buff.append (" FROM liberacoes_veiculos, ");
      buff.append ("      Seguradoras ");
      buff.append (" WHERE (liberacoes_veiculos.oid_Seguradora 		     = Seguradoras.oid_Seguradora )");
      buff.append ("AND OID_Veiculo= '");
      buff.append (oid_Veiculo);
      buff.append ("'");
      buff.append ("AND DT_Liberacao= '");
      buff.append (data_Liberacao);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Seguradora (cursor.getInt (2));
        p.setOID_Veiculo (cursor.getString (3));
        p.setDT_Liberacao (cursor.getString (4));
        p.setNM_Liberacao (cursor.getString (5));
        p.setNM_Seguradora (cursor.getString (6));
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


  public static final List getAll (String oid_Veiculo) throws Exception {
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

    List Liberacao_Veiculos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT liberacoes_veiculos.OID_Liberacao_Veiculo, ");
      buff.append ("	liberacoes_veiculos.OID_Seguradora, ");
      buff.append ("	liberacoes_veiculos.OID_Veiculo, ");
      buff.append ("	liberacoes_veiculos.DT_Liberacao, ");
      buff.append ("	liberacoes_veiculos.NM_Liberacao, ");
      buff.append ("	Seguradoras.NM_Seguradora  ");
      buff.append (" FROM liberacoes_veiculos, ");
      buff.append ("      Seguradoras ");
      buff.append (" WHERE liberacoes_veiculos.oid_Seguradora = Seguradoras.oid_Seguradora ");
      if (oid_Veiculo != null && oid_Veiculo.length()>5) {
        buff.append (" AND   liberacoes_veiculos.oid_Veiculo ='" + oid_Veiculo + "'");
      }
      // System.out.println(buff);
      
      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Liberacao_VeiculoBean p = new Liberacao_VeiculoBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Seguradora (cursor.getInt (2));
        p.setOID_Veiculo (cursor.getString (3));
        p.setDT_Liberacao (cursor.getString (4));
        p.setNM_Liberacao (cursor.getString (5));
        p.setNM_Seguradora (cursor.getString (6));
        Liberacao_Veiculos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Liberacao_Veiculos_Lista;
  }

  public static void main (String args[]) throws Exception {

  }

  public void geraRelVCTO (HttpServletRequest request , HttpServletResponse response) throws Exception {
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

    ArrayList Liberacao_Veiculos_Lista = new ArrayList ();
    try {
      String NR_Placa = request.getParameter ("oid_Veiculo");
      String DT_Inicio = request.getParameter ("FT_DT_Liberacao");
      String DT_Fim = request.getParameter ("FT_DT_Liberacao");

      Liberacao_VeiculoBean ed = new Liberacao_VeiculoBean ();

      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT liberacoes_veiculos.OID_Liberacao_Veiculo, ");
      buff.append ("	liberacoes_veiculos.OID_Seguradora, ");
      buff.append ("	liberacoes_veiculos.OID_Veiculo, ");
      buff.append ("	liberacoes_veiculos.DT_Liberacao, ");
      buff.append ("	liberacoes_veiculos.NM_Liberacao, ");
      buff.append ("	Seguradoras.NM_Seguradora,  ");
      buff.append (" FROM liberacoes_veiculos, ");
      buff.append ("      Seguradoras ");
      buff.append (" WHERE liberacoes_veiculos.oid_Seguradora = Seguradoras.oid_Seguradora ");

      if (JavaUtil.doValida (NR_Placa)) {
        buff.append ("  AND  liberacoes_veiculos.OID_Veiculo = '");
        buff.append (NR_Placa);
        buff.append ("'");
        ed.setOID_Veiculo (NR_Placa);
      }
      if (JavaUtil.doValida (DT_Inicio)) {
        buff.append ("  AND  liberacoes_veiculos.DT_Liberacao >= '");
        buff.append (DT_Inicio);
        buff.append ("'");
      }
      if (JavaUtil.doValida (DT_Fim)) {
        buff.append ("  AND  liberacoes_veiculos.DT_Liberacao <= '");
        buff.append (DT_Fim);
        buff.append ("'");
      }

      buff.append (" ORDER BY liberacoes_veiculos.OID_Veiculo, liberacoes_veiculos.DT_Liberacao ");
      // System.out.println ("BUFF >" + buff.toString ());
      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Liberacao_VeiculoBean p = new Liberacao_VeiculoBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Seguradora (cursor.getInt (2));
        p.setOID_Veiculo (cursor.getString (3));
        p.setDT_Liberacao (cursor.getString (4));
        p.setNM_Liberacao (cursor.getString (5));
        p.setNM_Seguradora (cursor.getString (6));
        Liberacao_Veiculos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();

      new Liberacao_VeiculoRL ().geraRelVCTO (Liberacao_Veiculos_Lista , response , ed);

    }
    catch (Exception e) {
      e.printStackTrace ();
    }

  }

}
