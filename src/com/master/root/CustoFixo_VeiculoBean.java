package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.util.JavaUtil;

import auth.OracleConnection2;

public class CustoFixo_VeiculoBean {
  private String DT_CustoFixo;
  private double VL_Custo_Fixo;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_Tipo_Servico;
  private String NM_Tipo_Servico;
  private String oid_Veiculo;

  public CustoFixo_VeiculoBean () {
    VL_Custo_Fixo = 0;
  }

  public String getDT_CustoFixo () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_CustoFixo);
    DT_CustoFixo = DataFormatada.getDT_FormataData ();

    return DT_CustoFixo;
  }

  public void setDT_CustoFixo (String DT_CustoFixo) {
    this.DT_CustoFixo = DT_CustoFixo;
  }

  public double getVL_Custo_Fixo () {
    return VL_Custo_Fixo;
  }

  public void setVL_Custo_Fixo (double VL_Custo_Fixo) {
    this.VL_Custo_Fixo = VL_Custo_Fixo;
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

  public int getOID_Tipo_Servico () {
    return oid_Tipo_Servico;
  }

  public void setOID_Tipo_Servico (int n) {
    this.oid_Tipo_Servico = n;
  }

  public String getNM_Tipo_Servico () {
    return NM_Tipo_Servico;
  }

  public void setNM_Tipo_Servico (String NM_Tipo_Servico) {
    this.NM_Tipo_Servico = NM_Tipo_Servico;
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
          "SELECT MAX(OID_Custo_Fixo_Veiculo) FROM Custos_Fixos_Veiculos");

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
    buff.append ("INSERT INTO Custos_Fixos_Veiculos (OID_Custo_Fixo_Veiculo, OID_Veiculo, OID_Tipo_Servico, VL_Custo_Fixo, DT_Stamp, Usuario_Stamp, Dm_Stamp) ");
    buff.append ("VALUES (?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setString (2 , getOID_Veiculo ());
      pstmt.setInt (3 , getOID_Tipo_Servico ());
      pstmt.setDouble (4 , getVL_Custo_Fixo ());
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
      // passando como parâmetro o VL_Custo_Fixo do DSN
      // o VL_Custo_Fixo de usuário e a senha do banco.
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
    buff.append ("UPDATE Custos_Fixos_Veiculos SET OID_Tipo_Servico=?, VL_Custo_Fixo=? ");
    buff.append ("WHERE OID_Custo_Fixo_Veiculo=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setInt (1 , getOID_Tipo_Servico ());
      pstmt.setDouble (2 , getVL_Custo_Fixo ());
      pstmt.setInt (3 , getOID ());
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
      // passando como parâmetro o VL_Custo_Fixo do DSN
      // o VL_Custo_Fixo de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Custos_Fixos_Veiculos ");
    buff.append ("WHERE OID_Custo_Fixo_Veiculo=?");
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

  public static final CustoFixo_VeiculoBean getByOID (String oid)

      throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o VL_Custo_Fixo do DSN
      // o VL_Custo_Fixo de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    CustoFixo_VeiculoBean p = new CustoFixo_VeiculoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Custos_Fixos_Veiculos.OID_Custo_Fixo_Veiculo, ");
      buff.append ("	Custos_Fixos_Veiculos.OID_Tipo_Servico, ");
      buff.append ("	Custos_Fixos_Veiculos.OID_Veiculo, ");
      buff.append ("	Custos_Fixos_Veiculos.VL_Custo_Fixo, ");
      buff.append ("	Tipos_Servicos.NM_Tipo_Servico  ");
      buff.append (" FROM Custos_Fixos_Veiculos, ");
      buff.append ("      Tipos_Servicos ");
      buff.append (" WHERE (Custos_Fixos_Veiculos.oid_Tipo_Servico 		     = Tipos_Servicos.oid_Tipo_Servico )");
      buff.append ("AND OID_Custo_Fixo_Veiculo= '");
      buff.append (oid);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Tipo_Servico (cursor.getInt (2));
        p.setOID_Veiculo (cursor.getString (3));
        p.setVL_Custo_Fixo (cursor.getDouble (4));
        p.setNM_Tipo_Servico (cursor.getString (5));
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

  public static final CustoFixo_VeiculoBean getByCustoFixo (String oid_Veiculo, String data_CustoFixo)

      throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o VL_Custo_Fixo do DSN
      // o VL_Custo_Fixo de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    CustoFixo_VeiculoBean p = new CustoFixo_VeiculoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Custos_Fixos_Veiculos.OID_Custo_Fixo_Veiculo, ");
      buff.append ("	Custos_Fixos_Veiculos.OID_Tipo_Servico, ");
      buff.append ("	Custos_Fixos_Veiculos.OID_Veiculo, ");
      buff.append ("	Custos_Fixos_Veiculos.VL_Custo_Fixo, ");
      buff.append ("	Tipos_Servicos.NM_Tipo_Servico  ");
      buff.append (" FROM Custos_Fixos_Veiculos, ");
      buff.append ("      Tipos_Servicos ");
      buff.append (" WHERE (Custos_Fixos_Veiculos.oid_Tipo_Servico 		     = Tipos_Servicos.oid_Tipo_Servico )");
      buff.append ("AND OID_Veiculo= '");
      buff.append (oid_Veiculo);
      buff.append ("'");
      buff.append ("AND DT_CustoFixo= '");
      buff.append (data_CustoFixo);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Tipo_Servico (cursor.getInt (2));
        p.setOID_Veiculo (cursor.getString (3));
        p.setVL_Custo_Fixo (cursor.getDouble (4));
        p.setNM_Tipo_Servico (cursor.getString (5));
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

    List CustoFixo_Veiculos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Custos_Fixos_Veiculos.OID_Custo_Fixo_Veiculo, ");
      buff.append ("	Custos_Fixos_Veiculos.OID_Tipo_Servico, ");
      buff.append ("	Custos_Fixos_Veiculos.OID_Veiculo, ");
      buff.append ("	Custos_Fixos_Veiculos.VL_Custo_Fixo, ");
      buff.append ("	Tipos_Servicos.NM_Tipo_Servico  ");
      buff.append (" FROM Custos_Fixos_Veiculos, ");
      buff.append ("      Tipos_Servicos ");
      buff.append (" WHERE Custos_Fixos_Veiculos.oid_Tipo_Servico = Tipos_Servicos.oid_Tipo_Servico ");
      if (oid_Veiculo != null && oid_Veiculo.length()>5) {
        buff.append (" AND   Custos_Fixos_Veiculos.oid_Veiculo ='" + oid_Veiculo + "'");
      }
      // System.out.println(buff);
      
      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        CustoFixo_VeiculoBean p = new CustoFixo_VeiculoBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Tipo_Servico (cursor.getInt (2));
        p.setOID_Veiculo (cursor.getString (3));
        p.setVL_Custo_Fixo (cursor.getDouble (4));
        p.setNM_Tipo_Servico (cursor.getString (5));
        CustoFixo_Veiculos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return CustoFixo_Veiculos_Lista;
  }

  public static void main (String args[]) throws Exception {

  }


}
