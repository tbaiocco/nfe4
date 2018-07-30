package com.master.root;

import java.sql.*;
import java.util.*;

import auth.*;

public class Ordem_CompraBean {
  private int NR_Ordem_Compra;
  private String NM_Solicitante;
  private String DT_Ordem_Compra;
  private String DT_Encerramento;
  private double VL_Previsto;
  private String TX_Observacao;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_Unidade;
  private int oid_Tipo_Compra;
  private int oid_AIDOF;
  private String oid_Pessoa;
  private String NM_Fantasia;
  private String NM_Razao_Social;
  private String NM_Tipo_Compra;
  private String CD_Tipo_Compra;
  private String DM_Tipo_Despesa;
  private String CD_Unidade;
  private String OID_Pessoa_Fornecedor;
  private String NM_Fornecedor;
  private String NR_CNPJ_CPF_Fornecedor;
  private String NM_Compra1;
  private String NM_Compra2;
  private String NM_Compra3;
  private String NM_Compra4;
  private String NM_Compra5;
  private String NM_Compra6;
  private String NM_Compra7;
  private String NM_Compra8;
  private String NM_Compra9;
  private String NM_Compra10;
  private String NM_Condicao_Pagamento;
  private String OID_Pessoa_Faturado;
  private String OID_Veiculo;
  private String OID_Pessoa_Funcionario;
  private String NM_Pessoa_Funcionario;
  private String NR_CNPJ_CPF_Funcionario;

  public Ordem_CompraBean () {
    VL_Previsto = 0;
    TX_Observacao = " ";
    Usuario_Stamp = "";
    Dm_Stamp = "";
    oid = 0;
    oid_Unidade = 0;
    oid_Tipo_Compra = 0;
    oid_AIDOF = 0;
    oid_Pessoa = "";
    NM_Fantasia = "";
    NM_Razao_Social = "";
    NM_Tipo_Compra = "";
    CD_Tipo_Compra = "";
    CD_Unidade = "";
  }

  public int getNR_Ordem_Compra () {
    return NR_Ordem_Compra;
  }

  public void setNR_Ordem_Compra (int NR_Ordem_Compra) {
    this.NR_Ordem_Compra = NR_Ordem_Compra;
  }

  public String getDT_Ordem_Compra () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Ordem_Compra);
    DT_Ordem_Compra = DataFormatada.getDT_FormataData ();

    return DT_Ordem_Compra;
  }

  public void setDT_Ordem_Compra (String DT_Ordem_Compra) {
    this.DT_Ordem_Compra = DT_Ordem_Compra;
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

  public int getOID_Tipo_Compra () {
    return oid_Tipo_Compra;
  }

  public void setOID_Tipo_Compra (int n) {
    this.oid_Tipo_Compra = n;
  }

  public String getNM_Tipo_Compra () {
    return NM_Tipo_Compra;
  }

  public void setNM_Tipo_Compra (String NM_Tipo_Compra) {
    this.NM_Tipo_Compra = NM_Tipo_Compra;
  }

  public String getCD_Tipo_Compra () {
    return CD_Tipo_Compra;
  }

  public void setCD_Tipo_Compra (String CD_Tipo_Compra) {
    this.CD_Tipo_Compra = CD_Tipo_Compra;
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
      // passando como parâmetro o NM_Ordem_Compra do DSN
      // o NM_Ordem_Compra de usuário e a senha do banco.
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
          "SELECT MAX(OID_Ordem_Compra) FROM Ordens_Compras");

      while (cursor.next ()) {
        int oid = cursor.getInt (1);
        setOID (oid + 1);
      }

      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT NR_Proxima_Ordem_Compra ");
      buff.append (" FROM Parametros_Filiais ");
      buff.append (" WHERE OID_Unidade = ");
      buff.append (getOID_Unidade ());

      ResultSet cursor2 =
          stmt.executeQuery (buff.toString ());

      while (cursor2.next ()) {
        // System.out.println ("Acho para fil");

        setNR_Ordem_Compra (cursor2.getInt (1));
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

    buff.append ("INSERT INTO Ordens_Compras (OID_Ordem_Compra, OID_Unidade, OID_Tipo_Compra, NR_Ordem_Compra, DT_Ordem_Compra, DT_Encerramento, VL_Previsto, TX_Observacao, Dt_Stamp, Usuario_Stamp, Dm_Stamp, OID_Pessoa_Fornecedor, NM_Compra1, NM_Compra2, NM_Compra3, NM_Compra4, NM_Compra5, NM_Compra6, NM_Compra7, NM_Compra8, NM_Compra9, NM_Compra10, NM_Condicao_Pagamento, OID_Veiculo, OID_Pessoa_Funcionario) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setInt (2 , getOID_Unidade ());
      pstmt.setInt (3 , getOID_Tipo_Compra ());
      pstmt.setInt (4 , getNR_Ordem_Compra ());
      pstmt.setString (5 , this.DT_Ordem_Compra);
      pstmt.setString (6 , this.DT_Encerramento);
      pstmt.setDouble (7 , getVL_Previsto ());
      pstmt.setString (8 , getTX_Observacao ());
      pstmt.setString (9 , getDt_Stamp ());
      pstmt.setString (10 , getUsuario_Stamp ());
      pstmt.setString (11 , getDm_Stamp ());
      pstmt.setString (12 , getOID_Pessoa_Fornecedor ());
      pstmt.setString (13 , getNM_Compra1 ());
      pstmt.setString (14 , getNM_Compra2 ());
      pstmt.setString (15 , getNM_Compra3 ());
      pstmt.setString (16 , getNM_Compra4 ());
      pstmt.setString (17 , getNM_Compra5 ());
      pstmt.setString (18 , getNM_Compra6 ());
      pstmt.setString (19 , getNM_Compra7 ());
      pstmt.setString (20 , getNM_Compra8 ());
      pstmt.setString (21 , getNM_Compra9 ());
      pstmt.setString (22 , getNM_Compra10 ());
      pstmt.setString (23 , getNM_Condicao_Pagamento ());
      pstmt.setString (24 , getOID_Veiculo ());
      pstmt.setString (25 , getOID_Pessoa_Funcionario ());

      // System.out.println (pstmt.toString ());

      pstmt.executeUpdate ();

      /*
       * Define o update.
       */

      setNR_Ordem_Compra (getNR_Ordem_Compra () + 1);

      buff.delete (0 , buff.length ());

      buff.append ("UPDATE Parametros_Filiais SET NR_Proxima_Ordem_Compra=? ");
      buff.append ("WHERE OID_Unidade =?");

      PreparedStatement pstmt1 =
          conn.prepareStatement (buff.toString ());

      pstmt1.setInt (1 , getNR_Ordem_Compra ());
      pstmt1.setInt (2 , getOID_Unidade ());
      pstmt1.executeUpdate ();

      setNR_Ordem_Compra (getNR_Ordem_Compra () - 1);

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
      // passando como parâmetro o NM_Ordem_Compra do DSN
      // o NM_Ordem_Compra de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    StringBuffer buff = new StringBuffer ();
    buff.append ("SELECT Ordens_Compras.DT_Encerramento ");
    buff.append ("FROM Ordens_Compras ");
    buff.append (" WHERE Ordens_Compras.oid_Ordem_Compra = ");
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

    buff.append ("UPDATE Ordens_Compras SET OID_Tipo_Compra=?, VL_Previsto=?, TX_Observacao=?, DT_Ordem_Compra=?, DT_Encerramento=?, NM_Compra1=?, NM_Compra2=?, NM_Compra3=?, NM_Compra4=?, NM_Compra5=?, NM_Compra6=?, NM_Compra7=?, NM_Compra8=?, NM_Compra9=?, NM_Compra10=?, NM_Condicao_Pagamento=? ");
    buff.append ("WHERE OID_Ordem_Compra=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setInt (1 , getOID_Tipo_Compra ());
      pstmt.setDouble (2 , getVL_Previsto ());
      pstmt.setString (3 , getTX_Observacao ());
      pstmt.setString (4 , this.DT_Ordem_Compra);
      pstmt.setString (5 , this.DT_Encerramento);
      pstmt.setString (6 , getNM_Compra1 ());
      pstmt.setString (7 , getNM_Compra2 ());
      pstmt.setString (8 , getNM_Compra3 ());
      pstmt.setString (9 , getNM_Compra4 ());
      pstmt.setString (10 , getNM_Compra5 ());
      pstmt.setString (11 , getNM_Compra6 ());
      pstmt.setString (12 , getNM_Compra7 ());
      pstmt.setString (13 , getNM_Compra8 ());
      pstmt.setString (14 , getNM_Compra9 ());
      pstmt.setString (15 , getNM_Compra10 ());
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
      // passando como parâmetro o NM_Ordem_Compra do DSN
      // o NM_Ordem_Compra de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Ordens_Compras ");
    buff.append ("WHERE OID_Ordem_Compra=?");
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

  public static final Ordem_CompraBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Ordem_Compra do DSN
      // o NM_Ordem_Compra de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Ordem_CompraBean p = new Ordem_CompraBean ();
    try {
      String sql =
          " SELECT Ordens_Compras.OID_Ordem_Compra, " +
          "        Ordens_Compras.OID_Unidade, " +
          "        Ordens_Compras.OID_Tipo_Compra, " +
          "        Ordens_Compras.NR_Ordem_Compra, " +
          "        Ordens_Compras.DT_Ordem_Compra, " +
          "        Ordens_Compras.DT_Encerramento, " +
          "        Ordens_Compras.VL_Previsto, " +
          "        Ordens_Compras.TX_Observacao, " +
          "        Ordens_Compras.Dt_Stamp, " +
          "        Ordens_Compras.Usuario_Stamp, " +
          "        Ordens_Compras.Dm_Stamp, " +
          "        Tipos_Servicos.NM_Tipo_Servico, " +
          "        Tipos_Servicos.CD_Tipo_Servico, " +
          "        Unidades.CD_Unidade, " +
          "        Pessoas.NM_Fantasia, " +
          "        Tipos_Servicos.DM_Tipo_Despesa, " +
          "        Ordens_Compras.oid_pessoa_fornecedor, " +
          "        Ordens_Compras.NM_Compra1, " +
          "        Ordens_Compras.NM_Compra2, " +
          "        Ordens_Compras.NM_Compra3, " +
          "        Ordens_Compras.NM_Compra4, " +
          "        Ordens_Compras.NM_Compra5, " +
          "        Ordens_Compras.NM_Compra6, " +
          "        Ordens_Compras.NM_Compra7, " +
          "        Ordens_Compras.NM_Compra8, " +
          "        Ordens_Compras.NM_Compra9, " +
          "        Ordens_Compras.NM_Compra10, " +
          "        Ordens_Compras.OID_Veiculo, " +
          "        Ordens_Compras.OID_Pessoa_Funcionario, " +
          "        Ordens_Compras.NM_Condicao_Pagamento " +
          " FROM Ordens_Compras, Unidades, Tipos_Servicos, Pessoas , Pessoas Pessoa_Fornecedor " +
          " WHERE Ordens_Compras.OID_Unidade = Unidades.OID_Unidade  " +
          " AND Ordens_Compras.OID_Tipo_Compra = Tipos_Servicos.OID_Tipo_Servico  " +
          " AND Ordens_Compras.OID_Pessoa_Fornecedor = Pessoa_Fornecedor.OID_Pessoa  " +
          " AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  " +
          " AND Ordens_Compras.OID_Ordem_Compra = " + oid;
      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (sql);

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Unidade (cursor.getInt (2));
        p.setOID_Tipo_Compra (cursor.getInt (3));
        p.setNR_Ordem_Compra (cursor.getInt (4));
        p.setDT_Ordem_Compra (cursor.getString (5));
        p.setDT_Encerramento (cursor.getString (6));
        p.setVL_Previsto (cursor.getDouble (7));
        p.setTX_Observacao (cursor.getString (8));
        p.setDt_Stamp (cursor.getString (9));
        p.setUsuario_Stamp (cursor.getString (10));
        p.setDm_Stamp (cursor.getString (11));
        p.setNM_Tipo_Compra (cursor.getString (12));
        p.setCD_Tipo_Compra (cursor.getString (13));
        p.setCD_Unidade (cursor.getString (14));
        p.setNM_Fantasia (cursor.getString (15));
        p.setDM_Tipo_Despesa (cursor.getString (16));
        p.setOID_Pessoa_Fornecedor (cursor.getString (17));
        p.setNM_Compra1 (cursor.getString (18));
        p.setNM_Compra2 (cursor.getString (19));
        p.setNM_Compra3 (cursor.getString (20));
        p.setNM_Compra4 (cursor.getString (21));
        p.setNM_Compra5 (cursor.getString (22));
        p.setNM_Compra6 (cursor.getString (23));
        p.setNM_Compra7 (cursor.getString (24));
        p.setNM_Compra8 (cursor.getString (25));
        p.setNM_Compra9 (cursor.getString (26));
        p.setNM_Compra10 (cursor.getString (27));
        if (cursor.getString (28) != null && cursor.getString (28).length()>4){  
          p.setOID_Veiculo (cursor.getString (28));
        }
        if (cursor.getString (29) != null && cursor.getString (29).length()>4){  
          p.setOID_Pessoa_Funcionario (cursor.getString (29));
        }
        p.setNM_Condicao_Pagamento (cursor.getString (30));

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

  public static final List getAll (String NR_Ordem_Compra , String NR_Placa , String oid_Tipo_Compra , String oid_Unidade , String oid_Pessoa , String DT_Inicial , String DT_Final, String oid_Veiculo, String oid_Pessoa_Funcionario) throws Exception {
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

    List Ordens_Compras_Lista = new ArrayList ();
    try {
      String sql =
          " SELECT Ordens_Compras.OID_Ordem_Compra, " +
          "        Ordens_Compras.OID_Unidade, " +
          "        Ordens_Compras.OID_Tipo_Compra, " +
          "        Ordens_Compras.NR_Ordem_Compra, " +
          "        Ordens_Compras.DT_Ordem_Compra, " +
          "        Ordens_Compras.DT_Encerramento, " +
          "        Ordens_Compras.VL_Previsto, " +
          "        Ordens_Compras.TX_Observacao, " +
          "        Ordens_Compras.Dt_Stamp, " +
          "        Ordens_Compras.Usuario_Stamp, " +
          "        Ordens_Compras.Dm_Stamp, " +
          "        Tipos_Servicos.NM_Tipo_Servico, " +
          "        Tipos_Servicos.CD_Tipo_Servico, " +
          "        Unidades.CD_Unidade, " +
          "        Pessoas.NM_Fantasia, " +
          "        Tipos_Servicos.DM_Tipo_Despesa, " +
          "        Ordens_Compras.oid_pessoa_fornecedor, " +
          "        Ordens_Compras.NM_Compra1, " +
          "        Ordens_Compras.NM_Compra2, " +
          "        Ordens_Compras.NM_Compra3, " +
          "        Ordens_Compras.NM_Compra4, " +
          "        Ordens_Compras.NM_Compra5, " +
          "        Ordens_Compras.NM_Compra6, " +
          "        Ordens_Compras.NM_Compra7, " +
          "        Ordens_Compras.NM_Compra8, " +
          "        Ordens_Compras.NM_Compra9, " +
          "        Ordens_Compras.NM_Compra10, " +
          "        Ordens_Compras.OID_Veiculo, " +
          "        Ordens_Compras.OID_Pessoa_Funcionario, " +
          "        Ordens_Compras.NM_Condicao_Pagamento, " +
          "        Pessoa_Fornecedor.NM_Razao_Social as NM_Fornecedor " +
          " FROM Ordens_Compras, Unidades, Tipos_Servicos, Pessoas , Pessoas Pessoa_Fornecedor " +
          " WHERE Ordens_Compras.OID_Unidade = Unidades.OID_Unidade " +
          "   AND Ordens_Compras.OID_Tipo_Compra = Tipos_Servicos.OID_Tipo_Servico  " +
          "   AND Ordens_Compras.OID_Pessoa_Fornecedor = Pessoa_Fornecedor.OID_Pessoa  " +
          "   AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa ";

      if (oid_Pessoa != null && !oid_Pessoa.equals ("") && !oid_Pessoa.equals ("null")) {
        sql += " AND Ordens_Compras.oid_Pessoa_Fornecedor ='" + oid_Pessoa + "'";
      }
      if (oid_Tipo_Compra != null && !oid_Tipo_Compra.equals ("") && !oid_Tipo_Compra.equals ("null")) {
        sql += " AND Ordens_Compras.oid_Tipo_Compra ='" + oid_Tipo_Compra + "'";
      }

      if (oid_Veiculo != null && !oid_Veiculo.equals ("") && !oid_Veiculo.equals ("null")) {
        sql += " AND Ordens_Compras.oid_Veiculo ='" + oid_Veiculo + "'";
      }

      if (oid_Pessoa_Funcionario != null && !oid_Pessoa_Funcionario.equals ("") && !oid_Pessoa_Funcionario.equals ("null")) {
        sql += " AND Ordens_Compras.oid_Pessoa_Funcionario ='" + oid_Pessoa_Funcionario + "'";
      }

      if (oid_Unidade != null && !oid_Unidade.equals ("") && !oid_Unidade.equals ("null")) {
        sql += " AND Ordens_Compras.oid_Unidade ='" + oid_Unidade + "'";
      }

      if (DT_Inicial != null && !DT_Inicial.equals ("") && !DT_Inicial.equals ("null")) {
        sql += " AND Ordens_Compras.DT_ORDEM_Compra >='" + DT_Inicial + "'";
      }
      if (DT_Final != null && !DT_Final.equals ("") && !DT_Final.equals ("null")) {
        sql += " AND Ordens_Compras.DT_ORDEM_Compra <='" + DT_Final + "'";
      }

      if (NR_Ordem_Compra != null && !NR_Ordem_Compra.equals ("") && !NR_Ordem_Compra.equals ("null")) {
        sql += " AND Ordens_Compras.NR_Ordem_Compra =" + NR_Ordem_Compra;
      }
      sql += " ORDER BY Ordens_Compras.NR_Ordem_Compra DESC";

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (sql);
      double tt_previsto=0;
      while (cursor.next ()) {
        Ordem_CompraBean p = new Ordem_CompraBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Unidade (cursor.getInt (2));
        p.setOID_Tipo_Compra (cursor.getInt (3));
        p.setNR_Ordem_Compra (cursor.getInt (4));
        p.setDT_Ordem_Compra (cursor.getString (5));
        p.setDT_Encerramento (cursor.getString (6));
        p.setVL_Previsto (cursor.getDouble (7));
        p.setTX_Observacao (cursor.getString (8));
        p.setDt_Stamp (cursor.getString (9));
        p.setUsuario_Stamp (cursor.getString (10));
        p.setDm_Stamp (cursor.getString (11));
        p.setNM_Tipo_Compra (cursor.getString (12));
        p.setCD_Tipo_Compra (cursor.getString (13));
        p.setCD_Unidade (cursor.getString (14));
        p.setNM_Fantasia (cursor.getString (15));
        p.setDM_Tipo_Despesa (cursor.getString (16));
        p.setOID_Pessoa_Fornecedor (cursor.getString (17));
        p.setNM_Compra1 (cursor.getString (18));
        p.setNM_Compra2 (cursor.getString (19));
        p.setNM_Compra3 (cursor.getString (20));
        p.setNM_Compra4 (cursor.getString (21));
        p.setNM_Compra5 (cursor.getString (22));
        p.setNM_Compra6 (cursor.getString (23));
        p.setNM_Compra7 (cursor.getString (24));
        p.setNM_Compra8 (cursor.getString (25));
        p.setNM_Compra9 (cursor.getString (26));
        p.setNM_Compra10 (cursor.getString (27));
        p.setOID_Veiculo (" ");
        if (cursor.getString (28) != null && cursor.getString (28).length()>4 && !cursor.getString (28).equals("")){  
          p.setOID_Veiculo (cursor.getString (28));
        }
        p.setOID_Pessoa_Funcionario (" ");

        if (cursor.getString (29) != null && cursor.getString (29).length()>4 && !cursor.getString (29).equals("")){  
          p.setOID_Pessoa_Funcionario (cursor.getString (29));

          Statement stmts = conn.createStatement ();
          sql=" SELECT NM_Razao_Social FROM Pessoas WHERE OID_Pessoa ='" + cursor.getString (29) + "'";
          ResultSet cursor2 = stmts.executeQuery (sql);
          while (cursor2.next ()) {
            p.setNM_Pessoa_Funcionario(cursor2.getString("NM_Razao_Social"));
          }
        }

        p.setNM_Condicao_Pagamento (cursor.getString (30));


        p.setNM_Razao_Social (" ");
        if (cursor.getString (31) != null && cursor.getString (31).length()>4 && !cursor.getString (31).equals("")){  
          p.setNM_Razao_Social ((cursor.getString (31)+"                       ").substring(0,25));
        }

        tt_previsto+=p.getVL_Previsto();
        Ordens_Compras_Lista.add (p);
      }
      Ordem_CompraBean p = new Ordem_CompraBean ();

      p.setNR_Ordem_Compra (0);
      p.setDT_Ordem_Compra (" ");
      p.setOID_Veiculo (" ");
      p.setNM_Razao_Social (" ");
      p.setNM_Tipo_Compra ("TOTAIS->>");

      p.setVL_Previsto (tt_previsto);

      Ordens_Compras_Lista.add (p);

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Ordens_Compras_Lista;
  }

  public static void main (String args[]) throws Exception {
    Ordem_CompraBean pp = new Ordem_CompraBean ();
    pp.setOID (1);
    pp.setVL_Previsto (99999999.88);
    pp.insert ();

    Ordem_CompraBean p = getByOID (11);
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

  public void setNM_Compra1 (String NM_Compra1) {
    this.NM_Compra1 = NM_Compra1;
  }

  public String getNM_Compra1 () {
    return NM_Compra1;
  }

  public void setNM_Compra2 (String NM_Compra2) {
    this.NM_Compra2 = NM_Compra2;
  }

  public String getNM_Compra2 () {
    return NM_Compra2;
  }

  public void setNM_Compra3 (String NM_Compra3) {
    this.NM_Compra3 = NM_Compra3;
  }

  public String getNM_Compra3 () {
    return NM_Compra3;
  }

  public void setNM_Compra4 (String NM_Compra4) {
    this.NM_Compra4 = NM_Compra4;
  }

  public String getNM_Compra4 () {
    return NM_Compra4;
  }

  public void setNM_Compra5 (String NM_Compra5) {
    this.NM_Compra5 = NM_Compra5;
  }

  public String getNM_Compra5 () {
    return NM_Compra5;
  }

  public void setNM_Compra6 (String NM_Compra6) {
    this.NM_Compra6 = NM_Compra6;
  }

  public String getNM_Compra6 () {
    return NM_Compra6;
  }

  public void setNM_Compra7 (String NM_Compra7) {
    this.NM_Compra7 = NM_Compra7;
  }

  public String getNM_Compra7 () {
    return NM_Compra7;
  }

  public void setNM_Compra8 (String NM_Compra8) {
    this.NM_Compra8 = NM_Compra8;
  }

  public String getNM_Compra8 () {
    return NM_Compra8;
  }

  public void setNM_Compra9 (String NM_Compra9) {
    this.NM_Compra9 = NM_Compra9;
  }

  public String getNM_Compra9 () {
    return NM_Compra9;
  }

  public void setNM_Compra10 (String NM_Compra10) {
    this.NM_Compra10 = NM_Compra10;
  }

  public String getNM_Compra10 () {
    return NM_Compra10;
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

  public void setOID_Veiculo (String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }

  public String getOID_Veiculo () {
    return OID_Veiculo;
  }

  public void setOID_Pessoa_Funcionario (String OID_Pessoa_Funcionario) {
    this.OID_Pessoa_Funcionario = OID_Pessoa_Funcionario;
  }

  public String getOID_Pessoa_Funcionario () {
    return OID_Pessoa_Funcionario;
  }

  public void setNM_Pessoa_Funcionario (String NM_Pessoa_Funcionario) {
    this.NM_Pessoa_Funcionario = NM_Pessoa_Funcionario;
  }

  public String getNM_Pessoa_Funcionario () {
    return NM_Pessoa_Funcionario;
  }

  public void setNR_CNPJ_CPF_Funcionario (String NR_CNPJ_CPF_Funcionario) {
    this.NR_CNPJ_CPF_Funcionario = NR_CNPJ_CPF_Funcionario;
  }

  public String getNR_CNPJ_CPF_Funcionario () {
    return NR_CNPJ_CPF_Funcionario;
  }
}
