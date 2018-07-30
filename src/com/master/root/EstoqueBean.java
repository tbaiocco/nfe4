package com.master.root;

import java.sql.*;
import java.util.*;

import auth.*;
import com.master.util.*;

public class EstoqueBean {
  private String CD_Estoque;
  private String NM_Estoque;
  private String NM_Referencia_Fabrica;
  private double VL_Qtde_Minima;
  private double VL_Qtde_Maxima;
  private double VL_Qtde_Atual;
  private double VL_Custo;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_Grupo_Estoque;
  private String NM_Grupo_Estoque;
  private String TX_Observacao;
  private String CD_Deposito;
  private String NM_Rua;
  private String NR_Endereco;
  private String NR_Apartamento;

  public EstoqueBean () {
    NM_Referencia_Fabrica = " ";
    VL_Qtde_Minima = 0;
    VL_Qtde_Maxima = 0;
    VL_Qtde_Atual = 0;
    VL_Custo = 0;
  }

  public String getCD_Estoque () {
    return CD_Estoque;
  }

  public void setCD_Estoque (String CD_Estoque) {
    this.CD_Estoque = CD_Estoque;
  }

  public String getNM_Estoque () {
    return NM_Estoque;
  }

  public void setNM_Estoque (String NM_Estoque) {
    this.NM_Estoque = NM_Estoque;
  }

  public String getNM_Referencia_Fabrica () {
    return NM_Referencia_Fabrica;
  }

  public void setNM_Referencia_Fabrica (String NM_Referencia_Fabrica) {
    this.NM_Referencia_Fabrica = NM_Referencia_Fabrica;
  }

  public double getVL_Qtde_Minima () {
    return VL_Qtde_Minima;
  }

  public void setVL_Qtde_Minima (double VL_Qtde_Minima) {
    this.VL_Qtde_Minima = VL_Qtde_Minima;
  }

  public double getVL_Qtde_Maxima () {
    return VL_Qtde_Maxima;
  }

  public void setVL_Qtde_Maxima (double VL_Qtde_Maxima) {
    this.VL_Qtde_Maxima = VL_Qtde_Maxima;
  }

  public double getVL_Qtde_Atual () {
    return VL_Qtde_Atual;
  }

  public void setVL_Qtde_Atual (double VL_Qtde_Atual) {
    this.VL_Qtde_Atual = VL_Qtde_Atual;
  }

  public double getVL_Custo () {
    return VL_Custo;
  }

  public void setVL_Custo (double VL_Custo) {
    this.VL_Custo = VL_Custo;
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

  public int getOID_Grupo_Estoque () {
    return oid_Grupo_Estoque;
  }

  public void setOID_Grupo_Estoque (int n) {
    this.oid_Grupo_Estoque = n;
  }

  public void insert () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Estoque do DSN
      // o NM_Estoque de usuário e a senha do banco.
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
          "SELECT MAX(OID_Estoque) FROM Estoques");

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
    buff.append ("INSERT INTO Estoques (OID_Estoque, OID_Grupo_Estoque, CD_Estoque, NM_Estoque, NM_Referencia_Fabrica, VL_Qtde_Minima, VL_Qtde_Maxima, VL_Qtde_Atual, VL_Custo, Dt_Stamp, Usuario_Stamp, Dm_Stamp, TX_Observacao, CD_Deposito, NM_Rua, NR_Endereco, NR_Apartamento) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt    (1 , getOID ());
      pstmt.setInt    (2 , getOID_Grupo_Estoque ());
      pstmt.setString (3 , getCD_Estoque ());
      pstmt.setString (4 , getNM_Estoque ());
      pstmt.setString (5 , getNM_Referencia_Fabrica ());
      pstmt.setDouble (6 , getVL_Qtde_Minima ());
      pstmt.setDouble (7 , getVL_Qtde_Maxima ());
      pstmt.setDouble (8 , getVL_Qtde_Atual ());
      pstmt.setDouble (9 , getVL_Custo ());
      pstmt.setDate   (10 , FormataData.formataDataTB (getDt_Stamp ()));
      pstmt.setString (11 , getUsuario_Stamp ());
      pstmt.setString (12 , getDm_Stamp ());
      pstmt.setString (13 , getTX_Observacao ());
      pstmt.setString (14 , getCD_Deposito ());
      pstmt.setString (15 , getNM_Rua ());
      pstmt.setString (16 , getNR_Endereco ());
      pstmt.setString (17 , getNR_Apartamento ());
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
      // passando como parâmetro o NM_Estoque do DSN
      // o NM_Estoque de usuário e a senha do banco.
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
    buff.append ("UPDATE Estoques SET OID_Grupo_Estoque=?, NM_Estoque=?, NM_Referencia_Fabrica=?, VL_Qtde_Minima=?, VL_Qtde_Maxima=?, VL_Qtde_Atual=?, VL_Custo=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, TX_Observacao=?, CD_Deposito=?, NM_Rua=?, NR_Endereco=?, NR_Apartamento=? ");
    buff.append ("WHERE OID_Estoque=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID_Grupo_Estoque ());
      pstmt.setString (2 , getNM_Estoque ());
      pstmt.setString (3 , getNM_Referencia_Fabrica ());
      pstmt.setDouble (4 , getVL_Qtde_Minima ());
      pstmt.setDouble (5 , getVL_Qtde_Maxima ());
      pstmt.setDouble (6 , getVL_Qtde_Atual ());
      pstmt.setDouble (7 , getVL_Custo ());
      pstmt.setDate (8 , FormataData.formataDataTB (getDt_Stamp ()));
      pstmt.setString (9 , getUsuario_Stamp ());
      pstmt.setString (10 , getDm_Stamp ());
      pstmt.setString (11 , getTX_Observacao ());
      pstmt.setString (12 , getCD_Deposito ());
      pstmt.setString (13 , getNM_Rua ());
      pstmt.setString (14 , getNR_Endereco ());
      pstmt.setString (15 , getNR_Apartamento ());
      pstmt.setInt (16 , getOID ());
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
      // passando como parâmetro o NM_Estoque do DSN
      // o NM_Estoque de usuário e a senha do banco.
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

    buff = new StringBuffer ();
    buff.append ("DELETE FROM Movimentos_Estoques ");
    buff.append ("WHERE OID_Estoque=?");
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

    buff = new StringBuffer ();
    buff.append ("DELETE FROM Estoques ");
    buff.append ("WHERE OID_Estoque=?");
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

  public static final EstoqueBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Estoque do DSN
      // o NM_Estoque de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    EstoqueBean p = new EstoqueBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Estoque,       ");
      buff.append ("   OID_Grupo_Estoque,     ");
      buff.append ("	CD_Estoque,            ");
      buff.append ("	NM_Estoque,            ");
      buff.append ("	NM_Referencia_Fabrica, ");
      buff.append ("	VL_Qtde_Minima,        ");
      buff.append ("	VL_Qtde_Maxima,        ");
      buff.append ("	VL_Qtde_Atual,         ");
      buff.append ("	TX_Observacao,         ");
      buff.append ("	CD_Deposito,           ");
      buff.append ("	NM_Rua,      		   ");
      buff.append ("	NR_Endereco,           ");
      buff.append ("	NR_Apartamento,        ");
      buff.append ("	VL_Custo               ");
      buff.append ("FROM Estoques             ");
      buff.append ("WHERE OID_Estoque =       ");
      buff.append (oid);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Grupo_Estoque (cursor.getInt (2));
        p.setCD_Estoque (cursor.getString (3));
        p.setNM_Estoque (cursor.getString (4));
        p.setNM_Referencia_Fabrica (cursor.getString (5));
        p.setVL_Qtde_Minima (cursor.getDouble (6));
        p.setVL_Qtde_Maxima (cursor.getDouble (7));
        p.setVL_Qtde_Atual (cursor.getDouble (8));
        p.setTX_Observacao (cursor.getString (9));
        p.setCD_Deposito (cursor.getString (10));
        p.setNM_Rua (cursor.getString (11));
        p.setNR_Endereco (cursor.getString (12));
        p.setNR_Apartamento (cursor.getString (13));
        p.setVL_Custo (cursor.getDouble (14));

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

  public static final EstoqueBean getByCD_Estoque (String CD_Estoque) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Estoque do DSN
      // o NM_Estoque de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    EstoqueBean p = new EstoqueBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Estoque,       ");
      buff.append ("   OID_Grupo_Estoque,     ");
      buff.append ("	CD_Estoque,            ");
      buff.append ("	NM_Estoque,            ");
      buff.append ("	NM_Referencia_Fabrica, ");
      buff.append ("	VL_Qtde_Minima,        ");
      buff.append ("	VL_Qtde_Maxima,        ");
      buff.append ("	VL_Qtde_Atual,         ");
      buff.append ("	VL_Custo               ");
      buff.append ("FROM Estoques ");
      buff.append ("WHERE CD_Estoque= '");
      buff.append (CD_Estoque);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Grupo_Estoque (cursor.getInt (2));
        p.setCD_Estoque (cursor.getString (3));
        p.setNM_Estoque (cursor.getString (4));
        p.setNM_Referencia_Fabrica (cursor.getString (5));
        p.setVL_Qtde_Minima (cursor.getDouble (6));
        p.setVL_Qtde_Maxima (cursor.getDouble (7));
        p.setVL_Qtde_Atual (cursor.getDouble (8));
        p.setVL_Custo (cursor.getDouble (9));
      }

      double entrada = 0 , saida = 0 , atual = 0 , ajuste = 0;

      buff.delete (0 , buff.length ());
      buff.append ("SELECT                       ");
      buff.append ("   OID_Movimento_Estoque,    ");
      buff.append ("   OID_Estoque,              ");
      buff.append ("	DT_Movimento,             ");
      buff.append ("	NR_Quantidade,            ");
      buff.append ("	VL_Unitario,              ");
      buff.append ("	DM_Movimento,             ");
      buff.append ("	DM_Destino,               ");
      buff.append ("	NM_Destino,               ");
      buff.append ("   OID_Ordem_Servico     ");
      buff.append ("FROM Movimentos_Estoques     ");
      buff.append ("WHERE OID_Estoque = ");
      buff.append (p.getOID ());
      buff.append ("ORDER BY Movimentos_Estoques.OID_Movimento_Estoque ");

      stmt = conn.createStatement ();
      cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        if (cursor.getString (6).equals ("E")) {
          entrada = entrada + cursor.getDouble (4);
        }
        if (cursor.getString (6).equals ("S")) {
          saida = saida + cursor.getDouble (4);
        }
        if (cursor.getString (6).equals ("A")) {
          ajuste = cursor.getDouble (4);
          entrada = 0;
          saida = 0;
        }

      }
      atual = ajuste + entrada - saida;
      if (atual < 0) {
        atual = 0;
      }

      p.setVL_Qtde_Atual (atual);

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return p;
  }

  public static final List getByNM_Estoque (String NM_Estoque) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Estoque do DSN
      // o NM_Estoque de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Estoques_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append (" SELECT OID_Estoque,       ");
      buff.append ("   Grupos_Estoques.OID_Grupo_Estoque,     ");
      buff.append ("	CD_Estoque,            ");
      buff.append ("	NM_Estoque,            ");
      buff.append ("	NM_Referencia_Fabrica, ");
      buff.append ("	VL_Qtde_Minima,        ");
      buff.append ("	VL_Qtde_Maxima,        ");
      buff.append ("	VL_Qtde_Atual,         ");
      buff.append ("	VL_Custo,              ");
      buff.append ("	NM_Grupo_Estoque       ");
      buff.append (" FROM Estoques, Grupos_Estoques WHERE Estoques.oid_grupo_estoque = Grupos_Estoques.oid_grupo_estoque ");
      buff.append (" AND  NM_Estoque LIKE'");
      buff.append (NM_Estoque);
      buff.append ("%' ORDER BY NM_Grupo_Estoque, NM_Estoque");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        EstoqueBean p = new EstoqueBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Grupo_Estoque (cursor.getInt (2));
        p.setCD_Estoque (cursor.getString (3));
        p.setNM_Estoque (cursor.getString (4));
        p.setNM_Referencia_Fabrica (cursor.getString (5));
        p.setVL_Qtde_Minima (cursor.getDouble (6));
        p.setVL_Qtde_Maxima (cursor.getDouble (7));
        p.setVL_Qtde_Atual (cursor.getDouble (8));
        p.setVL_Custo (cursor.getDouble (9));
        p.setNM_Grupo_Estoque (cursor.getString (10));

        Estoques_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Estoques_Lista;
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

    List Estoques_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Estoque,       ");
      buff.append ("   Grupos_Estoques.OID_Grupo_Estoque,     ");
      buff.append ("	CD_Estoque,            ");
      buff.append ("	NM_Estoque,            ");
      buff.append ("	NM_Referencia_Fabrica, ");
      buff.append ("	VL_Qtde_Minima,        ");
      buff.append ("	VL_Qtde_Maxima,        ");
      buff.append ("	VL_Qtde_Atual,         ");
      buff.append ("	VL_Custo ,             ");
      buff.append ("	NM_Grupo_Estoque       ");
      buff.append (" FROM Estoques, Grupos_Estoques WHERE Estoques.oid_grupo_estoque = Grupos_Estoques.oid_grupo_estoque ORDER by ORDER BY NM_Grupo_Estoque, NM_Estoque");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        EstoqueBean p = new EstoqueBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Grupo_Estoque (cursor.getInt (2));
        p.setCD_Estoque (cursor.getString (3));
        p.setNM_Estoque (cursor.getString (4));
        p.setNM_Referencia_Fabrica (cursor.getString (5));
        p.setVL_Qtde_Minima (cursor.getDouble (6));
        p.setVL_Qtde_Maxima (cursor.getDouble (7));
        p.setVL_Qtde_Atual (cursor.getDouble (8));
        p.setVL_Custo (cursor.getDouble (9));
        p.setNM_Grupo_Estoque (cursor.getString (10));
        Estoques_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Estoques_Lista;
  }

  public static void main (String args[]) throws Exception {
    EstoqueBean pp = new EstoqueBean ();
    pp.setOID_Grupo_Estoque (2);
    pp.setCD_Estoque ("LLL");
    pp.setNM_Estoque ("Litro");
    pp.insert ();

    EstoqueBean p = getByOID (1);
    // //// System.out.println(p.getOID());
    // //// System.out.println(p.getCD_Estoque());
    // //// System.out.println(p.getNM_Estoque());
    // //// System.out.println(p.getVL_Qtde_Minima());
  }

  public void setNM_Grupo_Estoque (String NM_Grupo_Estoque) {
    this.NM_Grupo_Estoque = NM_Grupo_Estoque;
  }

  public String getNM_Grupo_Estoque () {
    return NM_Grupo_Estoque;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

public String getCD_Deposito() {
	return CD_Deposito;
}

public void setCD_Deposito(String deposito) {
	CD_Deposito = deposito;
}

public String getNM_Rua() {
	return NM_Rua;
}

public void setNM_Rua(String rua) {
	NM_Rua = rua;
}

public String getNR_Apartamento() {
	return NR_Apartamento;
}

public void setNR_Apartamento(String apartamento) {
	NR_Apartamento = apartamento;
}

public String getNR_Endereco() {
	return NR_Endereco;
}

public void setNR_Endereco(String endereco) {
	NR_Endereco = endereco;
}
}
