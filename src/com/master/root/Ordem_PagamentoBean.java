package com.master.root;

import java.sql.*;
import java.util.*;

import auth.*;

public class Ordem_PagamentoBean {
  private int NR_Ordem_Pagamento;
  private String NM_Solicitante;
  private String DT_Ordem_Pagamento;
  private String DT_Encerramento;
  private double VL_Previsto;
  private String TX_Observacao;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_Unidade;
  private int oid_Tipo_Pagamento;
  private int oid_AIDOF;
  private String oid_Pessoa;
  private String NM_Fantasia;
  private String NM_Razao_Social;
  private String NM_Tipo_Pagamento;
  private String CD_Tipo_Pagamento;
  private String DM_Tipo_Despesa;
  private String CD_Unidade;
  private String OID_Pessoa_Fornecedor;
  private String NM_Fornecedor;
  private String NR_CNPJ_CPF_Fornecedor;
  private String NM_Pagamento1;
  private String NM_Pagamento2;
  private String NM_Pagamento3;
  private String NM_Pagamento4;
  private String NM_Pagamento5;
  private String NM_Pagamento6;
  private String NM_Pagamento7;
  private String NM_Pagamento8;
  private String NM_Pagamento9;
  private String NM_Pagamento10;
  private String NM_Condicao_Pagamento;
  private String OID_Pessoa_Faturado;

  public Ordem_PagamentoBean () {
    VL_Previsto = 0;
    TX_Observacao = " ";
    Usuario_Stamp = "";
    Dm_Stamp = "";
    oid = 0;
    oid_Unidade = 0;
    oid_Tipo_Pagamento = 0;
    oid_AIDOF = 0;
    oid_Pessoa = "";
    NM_Fantasia = "";
    NM_Razao_Social = "";
    NM_Tipo_Pagamento = "";
    CD_Tipo_Pagamento = "";
    CD_Unidade = "";
  }

  public int getNR_Ordem_Pagamento () {
    return NR_Ordem_Pagamento;
  }

  public void setNR_Ordem_Pagamento (int NR_Ordem_Pagamento) {
    this.NR_Ordem_Pagamento = NR_Ordem_Pagamento;
  }

  public String getDT_Ordem_Pagamento () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Ordem_Pagamento);
    DT_Ordem_Pagamento = DataFormatada.getDT_FormataData ();

    return DT_Ordem_Pagamento;
  }

  public void setDT_Ordem_Pagamento (String DT_Ordem_Pagamento) {
    this.DT_Ordem_Pagamento = DT_Ordem_Pagamento;
  }

  public String getDT_Encerramento () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Encerramento);
    DT_Encerramento = DataFormatada.getDT_FormataData ();

    return DT_Encerramento;
  }

  public void setDT_Encerramento (String DT_Encerramento) {
    this.DT_Encerramento = DT_Encerramento;
  }

  public double getVL_Previsto () {
    return VL_Previsto;
  }

  public void setVL_Previsto (double VL_Previsto) {
    this.VL_Previsto = VL_Previsto;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public int getOID_Unidade () {
    return oid_Unidade;
  }

  public void setOID_Unidade (int n) {
    this.oid_Unidade = n;
  }

  public String getNM_Fantasia () {
    return NM_Fantasia;
  }

  public void setNM_Fantasia (String NM_Fantasia) {
    this.NM_Fantasia = NM_Fantasia;
  }

  public String getCD_Unidade () {
    return CD_Unidade;
  }

  public void setCD_Unidade (String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
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

  public int getOID_Tipo_Pagamento () {
    return oid_Tipo_Pagamento;
  }

  public void setOID_Tipo_Pagamento (int n) {
    this.oid_Tipo_Pagamento = n;
  }

  public String getNM_Tipo_Pagamento () {
    return NM_Tipo_Pagamento;
  }

  public void setNM_Tipo_Pagamento (String NM_Tipo_Pagamento) {
    this.NM_Tipo_Pagamento = NM_Tipo_Pagamento;
  }

  public String getCD_Tipo_Pagamento () {
    return CD_Tipo_Pagamento;
  }

  public void setCD_Tipo_Pagamento (String CD_Tipo_Pagamento) {
    this.CD_Tipo_Pagamento = CD_Tipo_Pagamento;
  }

  public String getDM_Tipo_Despesa () {
    return DM_Tipo_Despesa;
  }

  public void setDM_Tipo_Despesa (String DM_Tipo_Despesa) {
    this.DM_Tipo_Despesa = DM_Tipo_Despesa;
  }

  public int getOID_AIDOF () {
    return oid_AIDOF;
  }

  public void setOID_AIDOF (int n) {
    this.oid_AIDOF = n;
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
      // passando como parâmetro o NM_Ordem_Pagamento do DSN
      // o NM_Ordem_Pagamento de usuário e a senha do banco.
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

      // System.out.println ("OC INclui");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (
          "SELECT MAX(OID_Ordem_Pagamento) FROM Ordens_Pagamentos");

      while (cursor.next ()) {
        int oid = cursor.getInt (1);
        setOID (oid + 1);
      }

      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT NR_Proxima_Ordem_Pagamento ");
      buff.append (" FROM Parametros_Filiais ");
      buff.append (" WHERE OID_Unidade = ");
      buff.append (getOID_Unidade ());

      ResultSet cursor2 =
          stmt.executeQuery (buff.toString ());

      while (cursor2.next ()) {
        // System.out.println ("Acho para fil");

        setNR_Ordem_Pagamento (cursor2.getInt (1));
      }

      cursor.close ();
      cursor2.close ();
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

    buff.append ("INSERT INTO Ordens_Pagamentos (OID_Ordem_Pagamento, OID_Unidade, OID_Tipo_Pagamento, NR_Ordem_Pagamento, DT_Ordem_Pagamento, DT_Encerramento, VL_Previsto, TX_Observacao, Dt_Stamp, Usuario_Stamp, Dm_Stamp, OID_Pessoa_Fornecedor, NM_Pagamento1, NM_Pagamento2, NM_Pagamento3, NM_Pagamento4, NM_Pagamento5, NM_Pagamento6, NM_Pagamento7, NM_Pagamento8, NM_Pagamento9, NM_Pagamento10, NM_Condicao_Pagamento) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setInt (2 , getOID_Unidade ());
      pstmt.setInt (3 , getOID_Tipo_Pagamento ());
      pstmt.setInt (4 , getNR_Ordem_Pagamento ());
      pstmt.setString (5 , this.DT_Ordem_Pagamento);
      pstmt.setString (6 , this.DT_Encerramento);
      pstmt.setDouble (7 , getVL_Previsto ());
      pstmt.setString (8 , getTX_Observacao ());
      pstmt.setString (9 , getDt_Stamp ());
      pstmt.setString (10 , getUsuario_Stamp ());
      pstmt.setString (11 , getDm_Stamp ());
      pstmt.setString (12 , getOID_Pessoa_Fornecedor ());
      pstmt.setString (13 , getNM_Pagamento1 ());
      pstmt.setString (14 , getNM_Pagamento2 ());
      pstmt.setString (15 , getNM_Pagamento3 ());
      pstmt.setString (16 , getNM_Pagamento4 ());
      pstmt.setString (17 , getNM_Pagamento5 ());
      pstmt.setString (18 , getNM_Pagamento6 ());
      pstmt.setString (19 , getNM_Pagamento7 ());
      pstmt.setString (20 , getNM_Pagamento8 ());
      pstmt.setString (21 , getNM_Pagamento9 ());
      pstmt.setString (22 , getNM_Pagamento10 ());
      pstmt.setString (23 , getNM_Condicao_Pagamento ());

      // System.out.println (pstmt.toString ());

      pstmt.executeUpdate ();

      /*
       * Define o update.
       */

      setNR_Ordem_Pagamento (getNR_Ordem_Pagamento () + 1);

      buff.delete (0 , buff.length ());

      buff.append ("UPDATE Parametros_Filiais SET NR_Proxima_Ordem_Pagamento=? ");
      buff.append ("WHERE OID_Unidade =?");

      PreparedStatement pstmt1 =
          conn.prepareStatement (buff.toString ());

      pstmt1.setInt (1 , getNR_Ordem_Pagamento ());
      pstmt1.setInt (2 , getOID_Unidade ());
      pstmt1.executeUpdate ();

      setNR_Ordem_Pagamento (getNR_Ordem_Pagamento () - 1);

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
      // passando como parâmetro o NM_Ordem_Pagamento do DSN
      // o NM_Ordem_Pagamento de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    StringBuffer buff = new StringBuffer ();
    buff.append ("SELECT Ordens_Pagamentos.DT_Encerramento ");
    buff.append ("FROM Ordens_Pagamentos ");
    buff.append (" WHERE Ordens_Pagamentos.oid_Ordem_Pagamento = ");
    buff.append (getOID ());

    Statement stmt = conn.createStatement ();
    ResultSet cursor =
        stmt.executeQuery (buff.toString ());

    while (cursor.next ()) {
      setDT_Encerramento (cursor.getString (1));
    }

    if (this.DT_Encerramento != null) {
      return;
    }

    /*
     * Define o update.
     */
    buff.delete (0 , buff.length ());

    buff.append ("UPDATE Ordens_Pagamentos SET OID_Tipo_Pagamento=?, VL_Previsto=?, TX_Observacao=?, DT_Ordem_Pagamento=?, DT_Encerramento=?, NM_Pagamento1=?, NM_Pagamento2=?, NM_Pagamento3=?, NM_Pagamento4=?, NM_Pagamento5=?, NM_Pagamento6=?, NM_Pagamento7=?, NM_Pagamento8=?, NM_Pagamento9=?, NM_Pagamento10=?, NM_Condicao_Pagamento=? ");
    buff.append ("WHERE OID_Ordem_Pagamento=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setInt (1 , getOID_Tipo_Pagamento ());
      pstmt.setDouble (2 , getVL_Previsto ());
      pstmt.setString (3 , getTX_Observacao ());
      pstmt.setString (4 , this.DT_Ordem_Pagamento);
      pstmt.setString (5 , this.DT_Encerramento);
      pstmt.setString (6 , getNM_Pagamento1 ());
      pstmt.setString (7 , getNM_Pagamento2 ());
      pstmt.setString (8 , getNM_Pagamento3 ());
      pstmt.setString (9 , getNM_Pagamento4 ());
      pstmt.setString (10 , getNM_Pagamento5 ());
      pstmt.setString (11 , getNM_Pagamento6 ());
      pstmt.setString (12 , getNM_Pagamento7 ());
      pstmt.setString (13 , getNM_Pagamento8 ());
      pstmt.setString (14 , getNM_Pagamento9 ());
      pstmt.setString (15 , getNM_Pagamento10 ());
      pstmt.setString (16 , getNM_Condicao_Pagamento ());

      pstmt.setInt (17 , getOID ());
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
      // passando como parâmetro o NM_Ordem_Pagamento do DSN
      // o NM_Ordem_Pagamento de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Ordens_Pagamentos ");
    buff.append ("WHERE OID_Ordem_Pagamento=?");
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

  public static final Ordem_PagamentoBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Ordem_Pagamento do DSN
      // o NM_Ordem_Pagamento de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Ordem_PagamentoBean p = new Ordem_PagamentoBean ();
    try {
      String sql =
          " SELECT Ordens_Pagamentos.OID_Ordem_Pagamento, " +
          "        Ordens_Pagamentos.OID_Unidade, " +
          "        Ordens_Pagamentos.OID_Tipo_Pagamento, " +
          "        Ordens_Pagamentos.NR_Ordem_Pagamento, " +
          "        Ordens_Pagamentos.DT_Ordem_Pagamento, " +
          "        Ordens_Pagamentos.DT_Encerramento, " +
          "        Ordens_Pagamentos.VL_Previsto, " +
          "        Ordens_Pagamentos.TX_Observacao, " +
          "        Ordens_Pagamentos.Dt_Stamp, " +
          "        Ordens_Pagamentos.Usuario_Stamp, " +
          "        Ordens_Pagamentos.Dm_Stamp, " +
          "        Tipos_Servicos.NM_Tipo_Servico, " +
          "        Tipos_Servicos.CD_Tipo_Servico, " +
          "        Unidades.CD_Unidade, " +
          "        Pessoas.NM_Fantasia, " +
          "        Tipos_Servicos.DM_Tipo_Despesa, " +
          "        Ordens_Pagamentos.oid_pessoa_fornecedor, " +
          "        Ordens_Pagamentos.NM_Pagamento1, " +
          "        Ordens_Pagamentos.NM_Pagamento2, " +
          "        Ordens_Pagamentos.NM_Pagamento3, " +
          "        Ordens_Pagamentos.NM_Pagamento4, " +
          "        Ordens_Pagamentos.NM_Pagamento5, " +
          "        Ordens_Pagamentos.NM_Pagamento6, " +
          "        Ordens_Pagamentos.NM_Pagamento7, " +
          "        Ordens_Pagamentos.NM_Pagamento8, " +
          "        Ordens_Pagamentos.NM_Pagamento9, " +
          "        Ordens_Pagamentos.NM_Pagamento10, " +
          "        Ordens_Pagamentos.NM_Condicao_Pagamento " +
          " FROM Ordens_Pagamentos, Unidades, Tipos_Servicos, Pessoas , Pessoas Pessoa_Fornecedor " +
          " WHERE Ordens_Pagamentos.OID_Unidade = Unidades.OID_Unidade  " +
          " AND Ordens_Pagamentos.OID_Tipo_Pagamento = Tipos_Servicos.OID_Tipo_Servico  " +
          " AND Ordens_Pagamentos.OID_Pessoa_Fornecedor = Pessoa_Fornecedor.OID_Pessoa  " +
          " AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  " +
          " AND Ordens_Pagamentos.OID_Ordem_Pagamento = " + oid;
      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (sql);

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Unidade (cursor.getInt (2));
        p.setOID_Tipo_Pagamento (cursor.getInt (3));
        p.setNR_Ordem_Pagamento (cursor.getInt (4));
        p.setDT_Ordem_Pagamento (cursor.getString (5));
        p.setDT_Encerramento (cursor.getString (6));
        p.setVL_Previsto (cursor.getDouble (7));
        p.setTX_Observacao (cursor.getString (8));
        p.setDt_Stamp (cursor.getString (9));
        p.setUsuario_Stamp (cursor.getString (10));
        p.setDm_Stamp (cursor.getString (11));
        p.setNM_Tipo_Pagamento (cursor.getString (12));
        p.setCD_Tipo_Pagamento (cursor.getString (13));
        p.setCD_Unidade (cursor.getString (14));
        p.setNM_Fantasia (cursor.getString (15));
        p.setDM_Tipo_Despesa (cursor.getString (16));
        p.setOID_Pessoa_Fornecedor (cursor.getString (17));
        p.setNM_Pagamento1 (cursor.getString (18));
        p.setNM_Pagamento2 (cursor.getString (19));
        p.setNM_Pagamento3 (cursor.getString (20));
        p.setNM_Pagamento4 (cursor.getString (21));
        p.setNM_Pagamento5 (cursor.getString (22));
        p.setNM_Pagamento6 (cursor.getString (23));
        p.setNM_Pagamento7 (cursor.getString (24));
        p.setNM_Pagamento8 (cursor.getString (25));
        p.setNM_Pagamento9 (cursor.getString (26));
        p.setNM_Pagamento10 (cursor.getString (27));
        p.setNM_Condicao_Pagamento (cursor.getString (28));

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

  public static final List getAll (String NR_Ordem_Pagamento, String oid_Tipo_Pagamento , String oid_Unidade , String oid_Pessoa , String DT_Inicial , String DT_Final) throws Exception {
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

    List Ordens_Pagamentos_Lista = new ArrayList ();
    try {
      String sql =
          " SELECT Ordens_Pagamentos.OID_Ordem_Pagamento, " +
          "        Ordens_Pagamentos.OID_Unidade, " +
          "        Ordens_Pagamentos.OID_Tipo_Pagamento, " +
          "        Ordens_Pagamentos.NR_Ordem_Pagamento, " +
          "        Ordens_Pagamentos.DT_Ordem_Pagamento, " +
          "        Ordens_Pagamentos.DT_Encerramento, " +
          "        Ordens_Pagamentos.VL_Previsto, " +
          "        Ordens_Pagamentos.TX_Observacao, " +
          "        Ordens_Pagamentos.Dt_Stamp, " +
          "        Ordens_Pagamentos.Usuario_Stamp, " +
          "        Ordens_Pagamentos.Dm_Stamp, " +
          "        Tipos_Servicos.NM_Tipo_Servico, " +
          "        Tipos_Servicos.CD_Tipo_Servico, " +
          "        Unidades.CD_Unidade, " +
          "        Pessoas.NM_Fantasia, " +
          "        Tipos_Servicos.DM_Tipo_Despesa, " +
          "        Ordens_Pagamentos.oid_pessoa_fornecedor, " +
          "        Ordens_Pagamentos.NM_Pagamento1, " +
          "        Ordens_Pagamentos.NM_Pagamento2, " +
          "        Ordens_Pagamentos.NM_Pagamento3, " +
          "        Ordens_Pagamentos.NM_Pagamento4, " +
          "        Ordens_Pagamentos.NM_Pagamento5, " +
          "        Ordens_Pagamentos.NM_Pagamento6, " +
          "        Ordens_Pagamentos.NM_Pagamento7, " +
          "        Ordens_Pagamentos.NM_Pagamento8, " +
          "        Ordens_Pagamentos.NM_Pagamento9, " +
          "        Ordens_Pagamentos.NM_Pagamento10, " +
          "        Pessoa_Fornecedor.NM_Razao_Social, " +
          "        Ordens_Pagamentos.NM_Condicao_Pagamento " +
          " FROM Ordens_Pagamentos, Unidades, Tipos_Servicos, Pessoas , Pessoas Pessoa_Fornecedor " +
          " WHERE Ordens_Pagamentos.OID_Unidade = Unidades.OID_Unidade " +
          "   AND Ordens_Pagamentos.OID_Tipo_Pagamento = Tipos_Servicos.OID_Tipo_Servico  " +
          "   AND Ordens_Pagamentos.OID_Pessoa_Fornecedor = Pessoa_Fornecedor.OID_Pessoa  " +
          "   AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa ";

      if (oid_Pessoa != null && !oid_Pessoa.equals ("") && !oid_Pessoa.equals ("null")) {
        sql += " AND Ordens_Pagamentos.oid_Pessoa_Fornecedor ='" + oid_Pessoa + "'";
      }
      if (oid_Tipo_Pagamento != null && !oid_Tipo_Pagamento.equals ("") && !oid_Tipo_Pagamento.equals ("null")) {
        sql += " AND Ordens_Pagamentos.oid_Tipo_Pagamento ='" + oid_Tipo_Pagamento + "'";
      }

      if (oid_Unidade != null && !oid_Unidade.equals ("") && !oid_Unidade.equals ("null")) {
        sql += " AND Ordens_Pagamentos.oid_Unidade ='" + oid_Unidade + "'";
      }

      if (DT_Inicial != null && !DT_Inicial.equals ("") && !DT_Inicial.equals ("null")) {
        sql += " AND Ordens_Pagamentos.DT_ORDEM_Pagamento >='" + DT_Inicial + "'";
      }
      if (DT_Final != null && !DT_Final.equals ("") && !DT_Final.equals ("null")) {
        sql += " AND Ordens_Pagamentos.DT_ORDEM_Pagamento <='" + DT_Final + "'";
      }

      if (NR_Ordem_Pagamento != null && !NR_Ordem_Pagamento.equals ("") && !NR_Ordem_Pagamento.equals ("null")) {
        sql += " AND Ordens_Pagamentos.NR_Ordem_Pagamento =" + NR_Ordem_Pagamento;
      }
      sql += " ORDER BY Ordens_Pagamentos.NR_Ordem_Pagamento DESC";

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (sql);

      while (cursor.next ()) {
        Ordem_PagamentoBean p = new Ordem_PagamentoBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Unidade (cursor.getInt (2));
        p.setOID_Tipo_Pagamento (cursor.getInt (3));
        p.setNR_Ordem_Pagamento (cursor.getInt (4));
        p.setDT_Ordem_Pagamento (cursor.getString (5));
        p.setDT_Encerramento (cursor.getString (6));
        p.setVL_Previsto (cursor.getDouble (7));
        p.setTX_Observacao (cursor.getString (8));
        p.setDt_Stamp (cursor.getString (9));
        p.setUsuario_Stamp (cursor.getString (10));
        p.setDm_Stamp (cursor.getString (11));
        p.setNM_Tipo_Pagamento (cursor.getString (12));
        p.setCD_Tipo_Pagamento (cursor.getString (13));
        p.setCD_Unidade (cursor.getString (14));
        p.setNM_Fantasia (cursor.getString (15));
        p.setDM_Tipo_Despesa (cursor.getString (16));
        p.setOID_Pessoa_Fornecedor (cursor.getString (17));
        p.setNM_Pagamento1 (cursor.getString (18));
        p.setNM_Pagamento2 (cursor.getString (19));
        p.setNM_Pagamento3 (cursor.getString (20));
        p.setNM_Pagamento4 (cursor.getString (21));
        p.setNM_Pagamento5 (cursor.getString (22));
        p.setNM_Pagamento6 (cursor.getString (23));
        p.setNM_Pagamento7 (cursor.getString (24));
        p.setNM_Pagamento8 (cursor.getString (25));
        p.setNM_Pagamento9 (cursor.getString (26));
        p.setNM_Pagamento10 (cursor.getString (27));
        p.setNM_Fornecedor (cursor.getString (28));
        p.setNM_Condicao_Pagamento (cursor.getString (29));
        Ordens_Pagamentos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Ordens_Pagamentos_Lista;
  }

  public static void main (String args[]) throws Exception {
    Ordem_PagamentoBean pp = new Ordem_PagamentoBean ();
    pp.setOID (1);
    pp.setVL_Previsto (99999999.88);
    pp.insert ();

    Ordem_PagamentoBean p = getByOID (11);
    // //// System.out.println(p.getOID());
    // //// System.out.println(p.getVL_Previsto());


  }

  public void setOID_Pessoa_Fornecedor (String OID_Pessoa_Fornecedor) {
    this.OID_Pessoa_Fornecedor = OID_Pessoa_Fornecedor;
  }

  public String getOID_Pessoa_Fornecedor () {
    return OID_Pessoa_Fornecedor;
  }

  public void setNM_Fornecedor (String NM_Fornecedor) {
    this.NM_Fornecedor = NM_Fornecedor;
  }

  public String getNM_Fornecedor () {
    return NM_Fornecedor;
  }

  public void setNR_CNPJ_CPF_Fornecedor (String NR_CNPJ_CPF_Fornecedor) {
    this.NR_CNPJ_CPF_Fornecedor = NR_CNPJ_CPF_Fornecedor;
  }

  public String getNR_CNPJ_CPF_Fornecedor () {
    return NR_CNPJ_CPF_Fornecedor;
  }

  public void setNM_Pagamento1 (String NM_Pagamento1) {
    this.NM_Pagamento1 = NM_Pagamento1;
  }

  public String getNM_Pagamento1 () {
    return NM_Pagamento1;
  }

  public void setNM_Pagamento2 (String NM_Pagamento2) {
    this.NM_Pagamento2 = NM_Pagamento2;
  }

  public String getNM_Pagamento2 () {
    return NM_Pagamento2;
  }

  public void setNM_Pagamento3 (String NM_Pagamento3) {
    this.NM_Pagamento3 = NM_Pagamento3;
  }

  public String getNM_Pagamento3 () {
    return NM_Pagamento3;
  }

  public void setNM_Pagamento4 (String NM_Pagamento4) {
    this.NM_Pagamento4 = NM_Pagamento4;
  }

  public String getNM_Pagamento4 () {
    return NM_Pagamento4;
  }

  public void setNM_Pagamento5 (String NM_Pagamento5) {
    this.NM_Pagamento5 = NM_Pagamento5;
  }

  public String getNM_Pagamento5 () {
    return NM_Pagamento5;
  }

  public void setNM_Pagamento6 (String NM_Pagamento6) {
    this.NM_Pagamento6 = NM_Pagamento6;
  }

  public String getNM_Pagamento6 () {
    return NM_Pagamento6;
  }

  public void setNM_Pagamento7 (String NM_Pagamento7) {
    this.NM_Pagamento7 = NM_Pagamento7;
  }

  public String getNM_Pagamento7 () {
    return NM_Pagamento7;
  }

  public void setNM_Pagamento8 (String NM_Pagamento8) {
    this.NM_Pagamento8 = NM_Pagamento8;
  }

  public String getNM_Pagamento8 () {
    return NM_Pagamento8;
  }

  public void setNM_Pagamento9 (String NM_Pagamento9) {
    this.NM_Pagamento9 = NM_Pagamento9;
  }

  public String getNM_Pagamento9 () {
    return NM_Pagamento9;
  }

  public void setNM_Pagamento10 (String NM_Pagamento10) {
    this.NM_Pagamento10 = NM_Pagamento10;
  }

  public String getNM_Pagamento10 () {
    return NM_Pagamento10;
  }

  public void setNM_Condicao_Pagamento (String NM_Condicao_Pagamento) {
    this.NM_Condicao_Pagamento = NM_Condicao_Pagamento;
  }

  public String getNM_Condicao_Pagamento () {
    return NM_Condicao_Pagamento;
  }

  public void setOID_Pessoa_Faturado (String OID_Pessoa_Faturado) {
    this.OID_Pessoa_Faturado = OID_Pessoa_Faturado;
  }

  public String getOID_Pessoa_Faturado () {
    return OID_Pessoa_Faturado;
  }


}
