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

public class Ocorrencia_MotoristaBean {
  private String NM_Contato;
  private String DT_Ocorrencia_Motorista;
  private String HR_Ocorrencia_Motorista;
  private String TX_Descricao;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_Tipo_Ocorrencia;
  private String oid_Pessoa;
  private String NM_Razao_Social;
  private String NM_Tipo_Ocorrencia;
  private String CD_Tipo_Ocorrencia;
  private String DM_Penalizacao;

  public Ocorrencia_MotoristaBean () {}

  public String getNM_Contato () {
    return NM_Contato;
  }

  public void setNM_Contato (String NM_Contato) {
    this.NM_Contato = NM_Contato;
  }

  public String getDT_Ocorrencia_Motorista () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Ocorrencia_Motorista);
    DT_Ocorrencia_Motorista = DataFormatada.getDT_FormataData ();

    return DT_Ocorrencia_Motorista;
  }

  public void setDT_Ocorrencia_Motorista (String DT_Ocorrencia_Motorista) {
    this.DT_Ocorrencia_Motorista = DT_Ocorrencia_Motorista;
  }

  public String getHR_Ocorrencia_Motorista () {
    return HR_Ocorrencia_Motorista;
  }

  public void setHR_Ocorrencia_Motorista (String HR_Ocorrencia_Motorista) {
    this.HR_Ocorrencia_Motorista = HR_Ocorrencia_Motorista;
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

  public String getOID_Pessoa () {
    return oid_Pessoa;
  }

  public void setOID_Pessoa (String n) {
    this.oid_Pessoa = n;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public void setNM_Razao_Social (String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
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
      // passando como parâmetro o NM_Ocorrencia_Motorista do DSN
      // o NM_Ocorrencia_Motorista de usuário e a senha do banco.
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
          "SELECT MAX(OID_Ocorrencia_Motorista) FROM Ocorrencias_Motoristas");

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
    buff.append ("INSERT INTO Ocorrencias_Motoristas (OID_Ocorrencia_Motorista, OID_Tipo_Ocorrencia, OID_Pessoa,  NM_Contato, DT_Ocorrencia_Motorista, HR_Ocorrencia_Motorista, TX_Descricao, Dt_Stamp, Usuario_Stamp, Dm_Stamp, DM_Penalizacao) ");
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
      pstmt.setString (3 , getOID_Pessoa ());
      pstmt.setString (4 , getNM_Contato ());
      pstmt.setString (5 , this.DT_Ocorrencia_Motorista);
      pstmt.setString (6 , getHR_Ocorrencia_Motorista ());
      pstmt.setString (7 , getTX_Descricao ());
      pstmt.setString (8 , getDt_Stamp ());
      pstmt.setString (9 , getUsuario_Stamp ());
      pstmt.setString (10 , getDm_Stamp ());
      pstmt.setString (11 , getDM_Penalizacao ());
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
      // passando como parâmetro o NM_Ocorrencia_Motorista do DSN
      // o NM_Ocorrencia_Motorista de usuário e a senha do banco.
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
    buff.append ("UPDATE Ocorrencias_Motoristas SET OID_Tipo_Ocorrencia=?, NM_Contato=?, TX_Descricao=?, DM_Penalizacao=? ");
    buff.append ("WHERE OID_Ocorrencia_Motorista=?");
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
      pstmt.setString (4 , getDM_Penalizacao ());
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
      // passando como parâmetro o NM_Ocorrencia_Motorista do DSN
      // o NM_Ocorrencia_Motorista de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Ocorrencias_Motoristas ");
    buff.append ("WHERE OID_Ocorrencia_Motorista=?");
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

  public static final Ocorrencia_MotoristaBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Ocorrencia_Motorista do DSN
      // o NM_Ocorrencia_Motorista de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Ocorrencia_MotoristaBean p = new Ocorrencia_MotoristaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT Ocorrencias_Motoristas.OID_Ocorrencia_Motorista, Ocorrencias_Motoristas.OID_Tipo_Ocorrencia, Ocorrencias_Motoristas.OID_Pessoa, NM_Contato, DT_Ocorrencia_Motorista, HR_Ocorrencia_Motorista, TX_Descricao, Ocorrencias_Motoristas.Dt_Stamp, Ocorrencias_Motoristas.Usuario_Stamp, Ocorrencias_Motoristas.Dm_Stamp, Pessoas.NM_Razao_Social, Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Tipos_Ocorrencias.CD_Tipo_Ocorrencia, DM_Penalizacao ");
      buff.append (" FROM Ocorrencias_Motoristas, Pessoas, Tipos_Ocorrencias ");
      buff.append (" WHERE Ocorrencias_Motoristas.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ");
      buff.append (" AND Ocorrencias_Motoristas.OID_Pessoa = Pessoas.OID_Pessoa ");
      buff.append (" AND Ocorrencias_Motoristas.OID_Ocorrencia_Motorista =");
      buff.append (oid);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Tipo_Ocorrencia (cursor.getInt (2));
        p.setOID_Pessoa (cursor.getString (3));
        p.setNM_Contato (cursor.getString (4));
        p.setDT_Ocorrencia_Motorista (cursor.getString (5));
        p.setHR_Ocorrencia_Motorista (cursor.getString (6));
        p.setTX_Descricao (cursor.getString (7));
        p.setDt_Stamp (cursor.getString (8));
        p.setUsuario_Stamp (cursor.getString (9));
        p.setDm_Stamp (cursor.getString (10));
        p.setNM_Razao_Social (cursor.getString (11));
        p.setNM_Tipo_Ocorrencia (cursor.getString (12));
        p.setCD_Tipo_Ocorrencia (cursor.getString (13));
        p.setDM_Penalizacao (cursor.getString (14));
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

    List Ocorrencia_Motoristas_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT Ocorrencias_Motoristas.OID_Ocorrencia_Motorista, Ocorrencias_Motoristas.OID_Tipo_Ocorrencia, Ocorrencias_Motoristas.OID_Pessoa, NM_Contato, DT_Ocorrencia_Motorista, HR_Ocorrencia_Motorista, TX_Descricao, Ocorrencias_Motoristas.Dt_Stamp, Ocorrencias_Motoristas.Usuario_Stamp, Ocorrencias_Motoristas.Dm_Stamp, Pessoas.NM_Razao_Social, Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Tipos_Ocorrencias.CD_Tipo_Ocorrencia ");
      buff.append (" FROM Ocorrencias_Motoristas, Pessoas, Tipos_Ocorrencias ");
      buff.append (" WHERE Ocorrencias_Motoristas.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ");
      buff.append (" AND Ocorrencias_Motoristas.OID_Pessoa = Pessoas.OID_Pessoa ");
      buff.append (" ORDER BY Ocorrencias_Motoristas.DT_Ocorrencia_Motorista ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Ocorrencia_MotoristaBean p = new Ocorrencia_MotoristaBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Tipo_Ocorrencia (cursor.getInt (2));
        p.setOID_Pessoa (cursor.getString (3));
        p.setNM_Contato (cursor.getString (4));
        p.setDT_Ocorrencia_Motorista (cursor.getString (5));
        p.setHR_Ocorrencia_Motorista (cursor.getString (6));
        p.setTX_Descricao (cursor.getString (7));
        p.setDt_Stamp (cursor.getString (8));
        p.setUsuario_Stamp (cursor.getString (9));
        p.setDm_Stamp (cursor.getString (10));
        p.setNM_Razao_Social (cursor.getString (11));
        p.setNM_Tipo_Ocorrencia (cursor.getString (12));
        p.setCD_Tipo_Ocorrencia (cursor.getString (13));
        Ocorrencia_Motoristas_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Ocorrencia_Motoristas_Lista;
  }

  public static final List getByNR_CNPJ_CPF (String NR_CNPJ_CPF) throws Exception {
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

    List Ocorrencia_Motoristas_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT Ocorrencias_Motoristas.OID_Ocorrencia_Motorista, Ocorrencias_Motoristas.OID_Tipo_Ocorrencia, Ocorrencias_Motoristas.OID_Pessoa, NM_Contato, DT_Ocorrencia_Motorista, HR_Ocorrencia_Motorista, TX_Descricao, Ocorrencias_Motoristas.Dt_Stamp, Ocorrencias_Motoristas.Usuario_Stamp, Ocorrencias_Motoristas.Dm_Stamp, Pessoas.NM_Razao_Social, Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Tipos_Ocorrencias.CD_Tipo_Ocorrencia ");
      buff.append (" FROM Ocorrencias_Motoristas, Pessoas, Tipos_Ocorrencias ");
      buff.append (" WHERE Ocorrencias_Motoristas.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ");
      buff.append (" AND Ocorrencias_Motoristas.OID_Pessoa = Pessoas.OID_Pessoa ");
      buff.append (" AND Ocorrencias_Motoristas.OID_Pessoa ='");
      buff.append (NR_CNPJ_CPF);
      buff.append ("'");
      buff.append (" ORDER BY Ocorrencias_Motoristas.DT_Ocorrencia_Motorista ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Ocorrencia_MotoristaBean p = new Ocorrencia_MotoristaBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Tipo_Ocorrencia (cursor.getInt (2));
        p.setOID_Pessoa (cursor.getString (3));
        p.setNM_Contato (cursor.getString (4));
        p.setDT_Ocorrencia_Motorista (cursor.getString (5));
        p.setHR_Ocorrencia_Motorista (cursor.getString (6));
        p.setTX_Descricao (cursor.getString (7));
        p.setDt_Stamp (cursor.getString (8));
        p.setUsuario_Stamp (cursor.getString (9));
        p.setDm_Stamp (cursor.getString (10));
        p.setNM_Razao_Social (cursor.getString (11));
        p.setNM_Tipo_Ocorrencia (cursor.getString (12));
        p.setCD_Tipo_Ocorrencia (cursor.getString (13));
        Ocorrencia_Motoristas_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Ocorrencia_Motoristas_Lista;
  }

  public static void main (String args[]) throws Exception {
    Ocorrencia_MotoristaBean pp = new Ocorrencia_MotoristaBean ();
    pp.setOID (1);
    pp.setOID_Pessoa ("11111111111");
    pp.setOID_Tipo_Ocorrencia (1);
    pp.update ();

    Ocorrencia_MotoristaBean p = getByOID (1);
    // //// System.out.println(p.getOID());




  }

  public void setDM_Penalizacao (String DM_Penalizacao) {
    this.DM_Penalizacao = DM_Penalizacao;
  }

  public String getDM_Penalizacao () {
    return DM_Penalizacao;
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

    List Ocorrencia_Motoristas_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT Ocorrencias_Motoristas.OID_Ocorrencia_Motorista, Ocorrencias_Motoristas.OID_Tipo_Ocorrencia, " +
                   " Ocorrencias_Motoristas.OID_Pessoa, NM_Contato, DT_Ocorrencia_Motorista, HR_Ocorrencia_Motorista, TX_Descricao, " +
                   " Ocorrencias_Motoristas.Dt_Stamp, Ocorrencias_Motoristas.Usuario_Stamp, Ocorrencias_Motoristas.Dm_Stamp, Pessoas.NM_Razao_Social, " +
                   " Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Tipos_Ocorrencias.CD_Tipo_Ocorrencia ");
      buff.append (" FROM Ocorrencias_Motoristas, Pessoas, Tipos_Ocorrencias ");
      buff.append (" WHERE Ocorrencias_Motoristas.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ");
      buff.append (" AND Ocorrencias_Motoristas.OID_Pessoa = Pessoas.OID_Pessoa ");
      if (JavaUtil.doValida (request.getParameter ("FT_NR_CNPJ_CPF"))) {
        buff.append (" AND Ocorrencias_Motoristas.OID_Pessoa ='");
        buff.append (request.getParameter ("FT_NR_CNPJ_CPF"));
        buff.append ("'");
      }
      if (JavaUtil.doValida (request.getParameter ("oid_Tipo_Ocorrencia"))) {
        buff.append (" AND Ocorrencias_Motoristas.OID_Tipo_Ocorrencia ='");
        buff.append (request.getParameter ("oid_Tipo_Ocorrencia"));
        buff.append ("'");
      }
      if (JavaUtil.doValida (request.getParameter ("FT_DT_Ocorrencia_Inicial"))) {
        buff.append (" AND Ocorrencias_Motoristas.DT_Ocorrencia_Motorista >='");
        buff.append (request.getParameter ("FT_DT_Ocorrencia_Inicial"));
        buff.append ("'");
      }
      if (JavaUtil.doValida (request.getParameter ("FT_DT_Ocorrencia_Final"))) {
        buff.append (" AND Ocorrencias_Motoristas.DT_Ocorrencia_Motorista <='");
        buff.append (request.getParameter ("FT_DT_Ocorrencia_Final"));
        buff.append ("'");
      }
      if (JavaUtil.doValida (request.getParameter ("FT_DM_Penalizacao")) && !request.getParameter ("FT_DM_Penalizacao").equals ("T")) {
        buff.append (" AND Ocorrencias_Motoristas.DM_Penalizacao ='");
        buff.append (request.getParameter ("FT_DM_Penalizacao"));
        buff.append ("'");
      }

      buff.append (" ORDER BY Ocorrencias_Motoristas.DT_Ocorrencia_Motorista ");
      // System.out.println (buff.toString ());
      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Ocorrencia_MotoristaBean p = new Ocorrencia_MotoristaBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Tipo_Ocorrencia (cursor.getInt (2));
        p.setOID_Pessoa (cursor.getString (3));
        p.setNM_Contato (cursor.getString (4));
        p.setDT_Ocorrencia_Motorista (cursor.getString (5));
        p.setHR_Ocorrencia_Motorista (cursor.getString (6));
        p.setTX_Descricao (cursor.getString (7));
        p.setDt_Stamp (cursor.getString (8));
        p.setUsuario_Stamp (cursor.getString (9));
        p.setDm_Stamp (cursor.getString (10));
        p.setNM_Razao_Social (cursor.getString (11));
        p.setNM_Tipo_Ocorrencia (cursor.getString (12));
        p.setCD_Tipo_Ocorrencia (cursor.getString (13));
        Ocorrencia_Motoristas_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Ocorrencia_Motoristas_Lista;
  }

}
