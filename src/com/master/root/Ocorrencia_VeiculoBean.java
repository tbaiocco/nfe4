package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.master.util.JavaUtil;

import auth.OracleConnection2;

public class Ocorrencia_VeiculoBean {
  private String NM_Contato;
  private String DT_Ocorrencia_Veiculo;
  private String HR_Ocorrencia_Veiculo;
  private String TX_Descricao;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_Tipo_Ocorrencia;
  private String oid_Veiculo;
  private String NR_Placa;
  private String NM_Tipo_Ocorrencia;
  private String CD_Tipo_Ocorrencia;
  private String DM_Situacao;

  public Ocorrencia_VeiculoBean () {}

  public String getNM_Contato () {
    return NM_Contato;
  }

  public void setNM_Contato (String NM_Contato) {
    this.NM_Contato = NM_Contato;
  }

  public String getDT_Ocorrencia_Veiculo () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Ocorrencia_Veiculo);
    DT_Ocorrencia_Veiculo = DataFormatada.getDT_FormataData ();

    return DT_Ocorrencia_Veiculo;
  }

  public void setDT_Ocorrencia_Veiculo (String DT_Ocorrencia_Veiculo) {
    this.DT_Ocorrencia_Veiculo = DT_Ocorrencia_Veiculo;
  }

  public String getHR_Ocorrencia_Veiculo () {
    return HR_Ocorrencia_Veiculo;
  }

  public void setHR_Ocorrencia_Veiculo (String HR_Ocorrencia_Veiculo) {
    this.HR_Ocorrencia_Veiculo = HR_Ocorrencia_Veiculo;
  }

  public String getTX_Descricao () {
    return TX_Descricao;
  }

  public void setTX_Descricao (String TX_Descricao) {
    this.TX_Descricao = TX_Descricao;
  }

  public int getOID_Tipo_Ocorrencia () {
    return oid_Tipo_Ocorrencia;
  }

  public void setOID_Tipo_Ocorrencia (int n) {
    this.oid_Tipo_Ocorrencia = n;
  }

  public String getNM_Tipo_Ocorrencia () {
    return NM_Tipo_Ocorrencia;
  }

  public void setNM_Tipo_Ocorrencia (String NM_Tipo_Ocorrencia) {
    this.NM_Tipo_Ocorrencia = NM_Tipo_Ocorrencia;
  }

  public String getCD_Tipo_Ocorrencia () {
    return CD_Tipo_Ocorrencia;
  }

  public void setCD_Tipo_Ocorrencia (String CD_Tipo_Ocorrencia) {
    this.CD_Tipo_Ocorrencia = CD_Tipo_Ocorrencia;
  }

  public String getOID_Veiculo () {
    return oid_Veiculo;
  }

  public void setOID_Veiculo (String n) {
    this.oid_Veiculo = n;
  }

  public String getNR_Placa () {
    return NR_Placa;
  }

  public void setNR_Placa (String NR_Placa) {
    this.NR_Placa = NR_Placa;
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
      // passando como parâmetro o NM_Ocorrencia_Veiculo do DSN
      // o NM_Ocorrencia_Veiculo de usuário e a senha do banco.
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
          "SELECT MAX(OID_Ocorrencia_Veiculo) FROM Ocorrencias_Veiculos");

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
    buff.append ("INSERT INTO Ocorrencias_Veiculos (OID_Ocorrencia_Veiculo, OID_Tipo_Ocorrencia, OID_veiculo,  NM_Contato, DT_Ocorrencia_Veiculo, HR_Ocorrencia_Veiculo, TX_Descricao, Dt_Stamp, Usuario_Stamp, Dm_Stamp, DM_Situacao) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setInt (2 , getOID_Tipo_Ocorrencia ());
      pstmt.setString (3 , getOID_Veiculo ());
      pstmt.setString (4 , getNM_Contato ());
      pstmt.setString (5 , this.DT_Ocorrencia_Veiculo);
      pstmt.setString (6 , getHR_Ocorrencia_Veiculo ());
      pstmt.setString (7 , getTX_Descricao ());
      pstmt.setString (8 , getDt_Stamp ());
      pstmt.setString (9 , getUsuario_Stamp ());
      pstmt.setString (10 , getDm_Stamp ());
      pstmt.setString (11 , getDM_Situacao ());
      pstmt.executeUpdate ();
      
      String sql = " UPDATE Veiculos SET oid_Ultima_Ocorrencia='" +getOID () + "', DM_Situacao='" + getDM_Situacao ()  + "' WHERE oid_Veiculo='" +  getOID_Veiculo () + "'"; 
      // System.out.println(sql);

      pstmt = conn.prepareStatement (sql);
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
      // passando como parâmetro o NM_Ocorrencia_Veiculo do DSN
      // o NM_Ocorrencia_Veiculo de usuário e a senha do banco.
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
    buff.append ("UPDATE Ocorrencias_Veiculos SET OID_Tipo_Ocorrencia=?, NM_Contato=?, TX_Descricao=?, DM_Situacao=? ");
    buff.append ("WHERE OID_Ocorrencia_Veiculo=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setInt (1 , getOID_Tipo_Ocorrencia ());
      pstmt.setString (2 , getNM_Contato ());
      pstmt.setString (3 , getTX_Descricao ());
      pstmt.setString (4 , getDM_Situacao ());
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
      // passando como parâmetro o NM_Ocorrencia_Veiculo do DSN
      // o NM_Ocorrencia_Veiculo de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Ocorrencias_Veiculos ");
    buff.append ("WHERE OID_Ocorrencia_Veiculo=?");
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

  public static final Ocorrencia_VeiculoBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Ocorrencia_Veiculo do DSN
      // o NM_Ocorrencia_Veiculo de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Ocorrencia_VeiculoBean p = new Ocorrencia_VeiculoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT Ocorrencias_Veiculos.OID_Ocorrencia_Veiculo, Ocorrencias_Veiculos.OID_Tipo_Ocorrencia, Ocorrencias_Veiculos.OID_veiculo, NM_Contato, DT_Ocorrencia_Veiculo, HR_Ocorrencia_Veiculo, TX_Descricao, Ocorrencias_Veiculos.Dt_Stamp, Ocorrencias_Veiculos.Usuario_Stamp, Ocorrencias_Veiculos.Dm_Stamp, veiculos.NR_Placa, Tipos_Ocorrencias_Veiculos.NM_Tipo_Ocorrencia, Tipos_Ocorrencias_Veiculos.CD_Tipo_Ocorrencia, Ocorrencias_Veiculos.DM_Situacao ");
      buff.append (" FROM Ocorrencias_Veiculos, veiculos, Tipos_Ocorrencias_Veiculos ");
      buff.append (" WHERE Ocorrencias_Veiculos.OID_Tipo_Ocorrencia = Tipos_Ocorrencias_Veiculos.OID_Tipo_Ocorrencia ");
      buff.append (" AND Ocorrencias_Veiculos.OID_veiculo = veiculos.OID_veiculo ");
      buff.append (" AND Ocorrencias_Veiculos.OID_Ocorrencia_Veiculo =");
      buff.append (oid);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Tipo_Ocorrencia (cursor.getInt (2));
        p.setOID_Veiculo (cursor.getString (3));
        p.setNM_Contato (cursor.getString (4));
        p.setDT_Ocorrencia_Veiculo (cursor.getString (5));
        p.setHR_Ocorrencia_Veiculo (cursor.getString (6));
        p.setTX_Descricao (cursor.getString (7));
        p.setDt_Stamp (cursor.getString (8));
        p.setUsuario_Stamp (cursor.getString (9));
        p.setDm_Stamp (cursor.getString (10));
        p.setNR_Placa (cursor.getString (11));
        p.setNM_Tipo_Ocorrencia (cursor.getString (12));
        p.setCD_Tipo_Ocorrencia (cursor.getString (13));
        p.setDM_Situacao (cursor.getString (14));
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

    List Ocorrencia_Veiculos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT Ocorrencias_Veiculos.OID_Ocorrencia_Veiculo, Ocorrencias_Veiculos.OID_Tipo_Ocorrencia, Ocorrencias_Veiculos.OID_veiculo, NM_Contato, DT_Ocorrencia_Veiculo, HR_Ocorrencia_Veiculo, TX_Descricao, Ocorrencias_Veiculos.Dt_Stamp, Ocorrencias_Veiculos.Usuario_Stamp, Ocorrencias_Veiculos.Dm_Stamp, veiculos.NR_Placa, Tipos_Ocorrencias_Veiculos.NM_Tipo_Ocorrencia, Tipos_Ocorrencias_Veiculos.CD_Tipo_Ocorrencia ");
      buff.append (" FROM Ocorrencias_Veiculos, veiculos, Tipos_Ocorrencias_Veiculos ");
      buff.append (" WHERE Ocorrencias_Veiculos.OID_Tipo_Ocorrencia = Tipos_Ocorrencias_Veiculos.OID_Tipo_Ocorrencia ");
      buff.append (" AND Ocorrencias_Veiculos.OID_veiculo = veiculos.OID_veiculo ");
      buff.append (" ORDER BY Ocorrencias_Veiculos.DT_Ocorrencia_Veiculo ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Ocorrencia_VeiculoBean p = new Ocorrencia_VeiculoBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Tipo_Ocorrencia (cursor.getInt (2));
        p.setOID_Veiculo (cursor.getString (3));
        p.setNM_Contato (cursor.getString (4));
        p.setDT_Ocorrencia_Veiculo (cursor.getString (5));
        p.setHR_Ocorrencia_Veiculo (cursor.getString (6));
        p.setTX_Descricao (cursor.getString (7));
        p.setDt_Stamp (cursor.getString (8));
        p.setUsuario_Stamp (cursor.getString (9));
        p.setDm_Stamp (cursor.getString (10));
        p.setNR_Placa (cursor.getString (11));
        p.setNM_Tipo_Ocorrencia (cursor.getString (12));
        p.setCD_Tipo_Ocorrencia (cursor.getString (13));
        Ocorrencia_Veiculos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Ocorrencia_Veiculos_Lista;
  }

  public static final List getByNR_Placa (String NR_Placa) throws Exception {
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

    List Ocorrencia_Veiculos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT Ocorrencias_Veiculos.OID_Ocorrencia_Veiculo, Ocorrencias_Veiculos.OID_Tipo_Ocorrencia, Ocorrencias_Veiculos.OID_veiculo, NM_Contato, DT_Ocorrencia_Veiculo, HR_Ocorrencia_Veiculo, TX_Descricao, Ocorrencias_Veiculos.Dt_Stamp, Ocorrencias_Veiculos.Usuario_Stamp, Ocorrencias_Veiculos.Dm_Stamp, veiculos.NR_Placa, Tipos_Ocorrencias_Veiculos.NM_Tipo_Ocorrencia, Tipos_Ocorrencias_Veiculos.CD_Tipo_Ocorrencia ");
      buff.append (" FROM Ocorrencias_Veiculos, veiculos, Tipos_Ocorrencias_Veiculos ");
      buff.append (" WHERE Ocorrencias_Veiculos.OID_Tipo_Ocorrencia = Tipos_Ocorrencias_Veiculos.OID_Tipo_Ocorrencia ");
      buff.append (" AND Ocorrencias_Veiculos.OID_veiculo = veiculos.OID_veiculo ");
      buff.append (" AND Ocorrencias_Veiculos.OID_veiculo ='");
      buff.append (NR_Placa);
      buff.append ("'");
      buff.append (" ORDER BY Ocorrencias_Veiculos.DT_Ocorrencia_Veiculo ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Ocorrencia_VeiculoBean p = new Ocorrencia_VeiculoBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Tipo_Ocorrencia (cursor.getInt (2));
        p.setOID_Veiculo (cursor.getString (3));
        p.setNM_Contato (cursor.getString (4));
        p.setDT_Ocorrencia_Veiculo (cursor.getString (5));
        p.setHR_Ocorrencia_Veiculo (cursor.getString (6));
        p.setTX_Descricao (cursor.getString (7));
        p.setDt_Stamp (cursor.getString (8));
        p.setUsuario_Stamp (cursor.getString (9));
        p.setDm_Stamp (cursor.getString (10));
        p.setNR_Placa (cursor.getString (11));
        p.setNM_Tipo_Ocorrencia (cursor.getString (12));
        p.setCD_Tipo_Ocorrencia (cursor.getString (13));
        Ocorrencia_Veiculos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Ocorrencia_Veiculos_Lista;
  }

  public static void main (String args[]) throws Exception {
    Ocorrencia_VeiculoBean pp = new Ocorrencia_VeiculoBean ();
    pp.setOID (1);
    pp.setOID_Veiculo ("");
    pp.setOID_Tipo_Ocorrencia (1);
    pp.update ();

    Ocorrencia_VeiculoBean p = getByOID (1);
    // //// System.out.println(p.getOID());




  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public List getByRequest (HttpServletRequest request) throws Exception {
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

    List Ocorrencia_Veiculos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT Ocorrencias_Veiculos.OID_Ocorrencia_Veiculo, Ocorrencias_Veiculos.OID_Tipo_Ocorrencia, " +
                   " Ocorrencias_Veiculos.OID_veiculo, NM_Contato, DT_Ocorrencia_Veiculo, HR_Ocorrencia_Veiculo, TX_Descricao, " +
                   " Ocorrencias_Veiculos.Dt_Stamp, Ocorrencias_Veiculos.Usuario_Stamp, Ocorrencias_Veiculos.Dm_Stamp, veiculos.NR_Placa, " +
                   " Tipos_Ocorrencias_Veiculos.NM_Tipo_Ocorrencia, Tipos_Ocorrencias_Veiculos.CD_Tipo_Ocorrencia ");
      buff.append (" FROM Ocorrencias_Veiculos, veiculos, Tipos_Ocorrencias_Veiculos ");
      buff.append (" WHERE Ocorrencias_Veiculos.OID_Tipo_Ocorrencia = Tipos_Ocorrencias_Veiculos.OID_Tipo_Ocorrencia ");
      buff.append (" AND Ocorrencias_Veiculos.OID_veiculo = veiculos.OID_veiculo ");
      if (JavaUtil.doValida (request.getParameter ("oid_Veiculo"))) {
        buff.append (" AND Ocorrencias_Veiculos.OID_veiculo ='");
        buff.append (request.getParameter ("oid_Veiculo"));
        buff.append ("'");
      }
      if (JavaUtil.doValida (request.getParameter ("oid_Tipo_Ocorrencia"))) {
        buff.append (" AND Ocorrencias_Veiculos.OID_Tipo_Ocorrencia ='");
        buff.append (request.getParameter ("oid_Tipo_Ocorrencia"));
        buff.append ("'");
      }
      if (JavaUtil.doValida (request.getParameter ("FT_DT_Ocorrencia_Inicial"))) {
        buff.append (" AND Ocorrencias_Veiculos.DT_Ocorrencia_Veiculo >='");
        buff.append (request.getParameter ("FT_DT_Ocorrencia_Inicial"));
        buff.append ("'");
      }
      if (JavaUtil.doValida (request.getParameter ("FT_DT_Ocorrencia_Final"))) {
        buff.append (" AND Ocorrencias_Veiculos.DT_Ocorrencia_Veiculo <='");
        buff.append (request.getParameter ("FT_DT_Ocorrencia_Final"));
        buff.append ("'");
      }
      if (JavaUtil.doValida (request.getParameter ("FT_DM_Situacao")) && !request.getParameter ("FT_DM_Situacao").equals ("T")) {
        buff.append (" AND Ocorrencias_Veiculos.DM_Situacao ='");
        buff.append (request.getParameter ("FT_DM_Situacao"));
        buff.append ("'");
      }

      buff.append (" ORDER BY Ocorrencias_Veiculos.DT_Ocorrencia_Veiculo ");
      // System.out.println (buff.toString ());
      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Ocorrencia_VeiculoBean p = new Ocorrencia_VeiculoBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Tipo_Ocorrencia (cursor.getInt (2));
        p.setOID_Veiculo (cursor.getString (3));
        p.setNM_Contato (cursor.getString (4));
        p.setDT_Ocorrencia_Veiculo (cursor.getString (5));
        p.setHR_Ocorrencia_Veiculo (cursor.getString (6));
        p.setTX_Descricao (cursor.getString (7));
        p.setDt_Stamp (cursor.getString (8));
        p.setUsuario_Stamp (cursor.getString (9));
        p.setDm_Stamp (cursor.getString (10));
        p.setNR_Placa (cursor.getString (11));
        p.setNM_Tipo_Ocorrencia (cursor.getString (12));
        p.setCD_Tipo_Ocorrencia (cursor.getString (13));
        Ocorrencia_Veiculos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Ocorrencia_Veiculos_Lista;
  }

}
