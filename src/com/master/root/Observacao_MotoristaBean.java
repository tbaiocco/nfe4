package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class Observacao_MotoristaBean {
  private String TX_Observacao;
  private String DM_Mostra_Coleta;
  private String DM_Mostra_Entrega;
  private String DM_Mostra_Conhecimento;
  private String DM_Mostra_Acerto_Contas;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private String oid_Pessoa;
  private String NM_Razao_Social;
  private String NR_CNPJ_CPF;
  private String DM_Mostra_Cadastro;

  public Observacao_MotoristaBean () {
  }

  public String getNR_CNPJ_CPF () {
    return NR_CNPJ_CPF;
  }

  public void setNR_CNPJ_CPF (String nr_cnpj_cpf) {
    NR_CNPJ_CPF = nr_cnpj_cpf;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public String getDM_Mostra_Coleta () {
    return DM_Mostra_Coleta;
  }

  public void setDM_Mostra_Coleta (String DM_Mostra_Coleta) {
    this.DM_Mostra_Coleta = DM_Mostra_Coleta;
  }

  public String getDM_Mostra_Entrega () {
    return DM_Mostra_Entrega;
  }

  public void setDM_Mostra_Entrega (String DM_Mostra_Entrega) {
    this.DM_Mostra_Entrega = DM_Mostra_Entrega;
  }

  public String getDM_Mostra_Conhecimento () {
    return DM_Mostra_Conhecimento;
  }

  public void setDM_Mostra_Conhecimento (String DM_Mostra_Conhecimento) {
    this.DM_Mostra_Conhecimento = DM_Mostra_Conhecimento;
  }

  public String getDM_Mostra_Acerto_Contas () {
    return DM_Mostra_Acerto_Contas;
  }

  public void setDM_Mostra_Acerto_Contas (String DM_Mostra_Acerto_Contas) {
    this.DM_Mostra_Acerto_Contas = DM_Mostra_Acerto_Contas;
  }

  public String getDM_Mostra_Cadastro () {
    return DM_Mostra_Cadastro;
  }

  public void setDM_Mostra_Cadastro (String DM_Mostra_Cadastro) {
    this.DM_Mostra_Cadastro = DM_Mostra_Cadastro;
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
          "SELECT MAX(OID_Observacao_Motorista) FROM Observacoes_Motoristas");

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
    buff.append ("INSERT INTO Observacoes_Motoristas (OID_OBSERVACAO_Motorista, OID_Motorista, TX_OBSERVACAO, DM_MOSTRA_COLETA, DM_MOSTRA_ENTREGA, DM_MOSTRA_CONHECIMENTO, DM_MOSTRA_Acerto_Contas, DM_MOSTRA_CADASTRO, DT_STAMP,  DM_STAMP, USUARIO_STAMP) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setString (2 , getOID_Pessoa ());
      pstmt.setString (3 , getTX_Observacao ());
      pstmt.setString (4 , getDM_Mostra_Coleta ());
      pstmt.setString (5 , getDM_Mostra_Entrega ());
      pstmt.setString (6 , getDM_Mostra_Conhecimento ());
      pstmt.setString (7 , getDM_Mostra_Acerto_Contas ());
      pstmt.setString (8 , getDM_Mostra_Cadastro ());
      pstmt.setString (9 , getDt_Stamp ());
      pstmt.setString (10 , getDm_Stamp ());
      pstmt.setString (11 , getUsuario_Stamp ());
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
      // passando como parâmetro o NM_Observacao_Motorista do DSN
      // o NM_Observacao_Motorista de usuário e a senha do banco.
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

    buff.append ("UPDATE Observacoes_Motoristas SET TX_OBSERVACAO=?, DM_MOSTRA_COLETA=?, DM_MOSTRA_ENTREGA=?, DM_MOSTRA_CONHECIMENTO=?, DM_MOSTRA_Acerto_Contas=?, DM_MOSTRA_CADASTRO=? ");
    buff.append ("WHERE OID_Observacao_Motorista=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getTX_Observacao ());
      pstmt.setString (2 , getDM_Mostra_Coleta ());
      pstmt.setString (3 , getDM_Mostra_Entrega ());
      pstmt.setString (4 , getDM_Mostra_Conhecimento ());
      pstmt.setString (5 , getDM_Mostra_Acerto_Contas ());
      pstmt.setString (6 , getDM_Mostra_Cadastro ());
      pstmt.setInt (7 , getOID ());
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
      // passando como parâmetro o NM_Observacao_Motorista do DSN
      // o NM_Observacao_Motorista de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Observacoes_Motoristas ");
    buff.append ("WHERE OID_Observacao_Motorista=?");
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

  public static final Observacao_MotoristaBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Observacao_Motorista do DSN
      // o NM_Observacao_Motorista de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Observacao_MotoristaBean p = new Observacao_MotoristaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT ");
      buff.append (" Observacoes_Motoristas.OID_OBSERVACAO_Motorista, ");
      buff.append (" Observacoes_Motoristas.OID_Motorista, ");
      buff.append (" Observacoes_Motoristas.TX_OBSERVACAO, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_COLETA, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_ENTREGA, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_CONHECIMENTO, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_Acerto_Contas,  ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_CADASTRO, ");
      buff.append (" Observacoes_Motoristas.DT_STAMP, ");
      buff.append (" Observacoes_Motoristas.DM_STAMP, ");
      buff.append (" Observacoes_Motoristas.USUARIO_STAMP, ");
      buff.append (" Pessoas.NM_Razao_Social, ");
      buff.append (" Pessoas.NR_CNPJ_CPF ");
      buff.append (" FROM Observacoes_Motoristas, Pessoas ");
      buff.append (" WHERE Observacoes_Motoristas.OID_Motorista = Pessoas.OID_Pessoa ");
      buff.append (" AND Observacoes_Motoristas.OID_Observacao_Motorista =");
      buff.append (oid);
      buff.append (" ORDER BY Observacoes_Motoristas.OID_Observacao_Motorista  ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Pessoa (cursor.getString (2));
        p.setTX_Observacao (cursor.getString (3));
        p.setDM_Mostra_Coleta (cursor.getString (4));
        p.setDM_Mostra_Entrega (cursor.getString (5));
        p.setDM_Mostra_Conhecimento (cursor.getString (6));
        p.setDM_Mostra_Acerto_Contas (cursor.getString (7));
        p.setDM_Mostra_Cadastro (cursor.getString (8));
        p.setDt_Stamp (cursor.getString (9));
        p.setDm_Stamp (cursor.getString (10));
        p.setUsuario_Stamp (cursor.getString (11));
        p.setNM_Razao_Social (cursor.getString (12));
        p.setNR_CNPJ_CPF (cursor.getString (13));
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

  public static final Observacao_MotoristaBean getByOID_Pessoa (String oid , String Mostra) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Observacao_Motorista do DSN
      // o NM_Observacao_Motorista de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Observacao_MotoristaBean p = new Observacao_MotoristaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT ");
      buff.append (" Observacoes_Motoristas.OID_OBSERVACAO_Motorista, ");
      buff.append (" Observacoes_Motoristas.OID_Motorista, ");
      buff.append (" Observacoes_Motoristas.TX_OBSERVACAO, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_COLETA, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_ENTREGA, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_CONHECIMENTO, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_Acerto_Contas,  ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_CADASTRO, ");
      buff.append (" Observacoes_Motoristas.DT_STAMP, ");
      buff.append (" Observacoes_Motoristas.DM_STAMP, ");
      buff.append (" Observacoes_Motoristas.USUARIO_STAMP, ");
      buff.append (" Pessoas.NM_Razao_Social, ");
      buff.append (" Pessoas.NR_CNPJ_CPF ");
      buff.append (" FROM Observacoes_Motoristas, Pessoas ");
      buff.append (" WHERE Observacoes_Motoristas.OID_Motorista = Pessoas.OID_Pessoa ");
      buff.append (" AND Observacoes_Motoristas.OID_Motorista ='" + oid + "'");
      buff.append (" ORDER BY Observacoes_Motoristas.OID_Observacao_Motorista  ");
      //// System.out.println("0");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      String Mensagem = "";

      while (cursor.next ()) {
        if (cursor.getString (4) != null && cursor.getString (4).equals ("S") && Mostra.equals ("Coleta")) {
          Mensagem = Mensagem + cursor.getString (3) + ", ";
        }
        if (cursor.getString (5) != null && cursor.getString (5).equals ("S") && Mostra.equals ("Entrega")) {
          Mensagem = Mensagem + cursor.getString (3) + ", ";
        }
        if (cursor.getString (6) != null && cursor.getString (6).equals ("S") && Mostra.equals ("Conhecimento")) {
          Mensagem = Mensagem + cursor.getString (3) + ", ";
        }
        if (cursor.getString (7) != null && cursor.getString (7).equals ("S") && Mostra.equals ("Acerto_Contas")) {
          Mensagem = Mensagem + cursor.getString (3) + ", ";
        }
        if (cursor.getString (8) != null && cursor.getString (8).equals ("S") && Mostra.equals ("Cadastro")) {
          Mensagem = Mensagem + cursor.getString (3) + ", ";
        }

      }
      p.setTX_Observacao (Mensagem);

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

    List Observacao_Motoristas_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT ");
      buff.append (" Observacoes_Motoristas.OID_OBSERVACAO_Motorista, ");
      buff.append (" Observacoes_Motoristas.OID_Motorista, ");
      buff.append (" Observacoes_Motoristas.TX_OBSERVACAO, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_COLETA, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_ENTREGA, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_CONHECIMENTO, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_Acerto_Contas,  ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_CADASTRO, ");
      buff.append (" Observacoes_Motoristas.DT_STAMP, ");
      buff.append (" Observacoes_Motoristas.DM_STAMP, ");
      buff.append (" Observacoes_Motoristas.USUARIO_STAMP, ");
      buff.append (" Pessoas.NM_Razao_Social, ");
      buff.append (" Pessoas.NR_CNPJ_CPF ");
      buff.append (" FROM Observacoes_Motoristas, Pessoas ");
      buff.append (" WHERE Observacoes_Motoristas.OID_Motorista = Pessoas.OID_Pessoa ");
      buff.append (" ORDER BY Observacoes_Motoristas.OID_Motorista  ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Observacao_MotoristaBean p = new Observacao_MotoristaBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Pessoa (cursor.getString (2));
        p.setTX_Observacao (cursor.getString (3));
        p.setDM_Mostra_Coleta (cursor.getString (4));
        p.setDM_Mostra_Entrega (cursor.getString (5));
        p.setDM_Mostra_Conhecimento (cursor.getString (6));
        p.setDM_Mostra_Acerto_Contas (cursor.getString (7));
        p.setDM_Mostra_Cadastro (cursor.getString (8));
        p.setDt_Stamp (cursor.getString (9));
        p.setDm_Stamp (cursor.getString (10));
        p.setUsuario_Stamp (cursor.getString (11));
        p.setNM_Razao_Social (cursor.getString (12));
        p.setNR_CNPJ_CPF (cursor.getString (13));
        Observacao_Motoristas_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Observacao_Motoristas_Lista;
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

    List Observacao_Motoristas_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT ");
      buff.append (" Observacoes_Motoristas.OID_OBSERVACAO_Motorista, ");
      buff.append (" Observacoes_Motoristas.OID_Motorista, ");
      buff.append (" Observacoes_Motoristas.TX_OBSERVACAO, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_COLETA, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_ENTREGA, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_CONHECIMENTO, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_Acerto_Contas,  ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_CADASTRO, ");
      buff.append (" Observacoes_Motoristas.DT_STAMP, ");
      buff.append (" Observacoes_Motoristas.DM_STAMP, ");
      buff.append (" Observacoes_Motoristas.USUARIO_STAMP, ");
      buff.append (" Pessoas.NM_Razao_Social, ");
      buff.append (" Pessoas.NR_CNPJ_CPF ");
      buff.append (" FROM Observacoes_Motoristas, Pessoas ");
      buff.append (" WHERE Observacoes_Motoristas.OID_Motorista = Pessoas.OID_Pessoa ");
      buff.append (" AND Pessoas.NR_CNPJ_CPF = '");
      buff.append (NR_CNPJ_CPF);
      buff.append ("'");
      buff.append (" ORDER BY Observacoes_Motoristas.OID_Observacao_Motorista  ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Observacao_MotoristaBean p = new Observacao_MotoristaBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Pessoa (cursor.getString (2));
        p.setTX_Observacao (cursor.getString (3));
        p.setDM_Mostra_Coleta (cursor.getString (4));
        p.setDM_Mostra_Entrega (cursor.getString (5));
        p.setDM_Mostra_Conhecimento (cursor.getString (6));
        p.setDM_Mostra_Acerto_Contas (cursor.getString (7));
        p.setDM_Mostra_Cadastro (cursor.getString (8));
        p.setDt_Stamp (cursor.getString (9));
        p.setDm_Stamp (cursor.getString (10));
        p.setUsuario_Stamp (cursor.getString (11));
        p.setNM_Razao_Social (cursor.getString (12));
        p.setNR_CNPJ_CPF (cursor.getString (13));
        Observacao_Motoristas_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Observacao_Motoristas_Lista;
  }

  public static final List getByOidPessoa (String oidPessoa) throws Exception {

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

    List Observacao_Motoristas_Lista = new ArrayList ();

    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT ");
      buff.append (" Observacoes_Motoristas.OID_OBSERVACAO_Motorista, ");
      buff.append (" Observacoes_Motoristas.OID_Motorista, ");
      buff.append (" Observacoes_Motoristas.TX_OBSERVACAO, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_COLETA, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_ENTREGA, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_CONHECIMENTO, ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_Acerto_Contas,  ");
      buff.append (" Observacoes_Motoristas.DM_MOSTRA_CADASTRO, ");
      buff.append (" Observacoes_Motoristas.DT_STAMP, ");
      buff.append (" Observacoes_Motoristas.DM_STAMP, ");
      buff.append (" Observacoes_Motoristas.USUARIO_STAMP, ");
      buff.append (" Pessoas.NM_Razao_Social, ");
      buff.append (" Pessoas.NR_CNPJ_CPF ");
      buff.append (" FROM Observacoes_Motoristas, Pessoas ");
      buff.append (" WHERE Observacoes_Motoristas.OID_Motorista = Pessoas.OID_Pessoa ");
      buff.append (" AND Pessoas.oid_pessoa = '" + oidPessoa + "'");
      buff.append (" ORDER BY Observacoes_Motoristas.OID_Observacao_Motorista  ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {

        Observacao_MotoristaBean p = new Observacao_MotoristaBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Pessoa (cursor.getString (2));
        p.setTX_Observacao (cursor.getString (3));
        p.setDM_Mostra_Coleta (cursor.getString (4));
        p.setDM_Mostra_Entrega (cursor.getString (5));
        p.setDM_Mostra_Conhecimento (cursor.getString (6));
        p.setDM_Mostra_Acerto_Contas (cursor.getString (7));
        p.setDM_Mostra_Cadastro (cursor.getString (8));
        p.setDt_Stamp (cursor.getString (9));
        p.setDm_Stamp (cursor.getString (10));
        p.setUsuario_Stamp (cursor.getString (11));
        p.setNM_Razao_Social (cursor.getString (12));
        p.setNR_CNPJ_CPF (cursor.getString (13));
        Observacao_Motoristas_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Observacao_Motoristas_Lista;
  }

  public static void main (String args[]) throws Exception {
    Observacao_MotoristaBean pp = new Observacao_MotoristaBean ();
    pp.setOID (1);
    pp.setOID_Pessoa ("11111111111");
    pp.update ();

    Observacao_MotoristaBean p = getByOID (1);
    // //// System.out.println(p.getOID());
  }
}
