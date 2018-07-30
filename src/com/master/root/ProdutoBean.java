package com.master.root;

import java.io.*;
import java.sql.*;
import java.util.*;

import auth.*;
import com.master.util.*;

public class ProdutoBean {
  private String CD_Produto;
  private String NM_Produto;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private String DM_Unidade;
  private String DM_Tipo;
  private String DM_Classificacao;
  private int oid_Produto_Tabela_Frete;
  private String NM_Produto_Tabela_Frete;
  private String CD_Produto_Tabela_Frete;

  public ProdutoBean () {}

  public String getCD_Produto () {
    return CD_Produto;
  }

  public void setCD_Produto (String CD_Produto) {
    this.CD_Produto = CD_Produto;
  }

  public String getNM_Produto () {
    return NM_Produto;
  }

  public void setNM_Produto (String NM_Produto) {
    this.NM_Produto = NM_Produto;
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
      // passando como parâmetro o NM_Produto do DSN
      // o NM_Produto de usuário e a senha do banco.
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
          "SELECT MAX(OID_Produto) FROM Produtos");

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
    buff.append ("INSERT INTO Produtos (OID_Produto, CD_Produto, NM_Produto, DM_Unidade, DM_Tipo, DM_Classificacao, oid_Produto_Tabela_Frete) ");
    buff.append ("VALUES (?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setString (2 , getCD_Produto ());
      pstmt.setString (3 , getNM_Produto ());
      pstmt.setString (4 , getDM_Unidade ());
      pstmt.setString (5 , getDM_Tipo ());
      pstmt.setString (6 , getDM_Classificacao ());
      pstmt.setInt (7 , getOID ());

      // System.out.println(pstmt.toString());

      pstmt.executeUpdate ();

      buff.delete (0 , buff.length ());

      buff.append ("INSERT INTO Referencias (OID_Produto, OID_Referencia, CD_Referencia ) ");
      buff.append ("VALUES (?,?,?)");

      pstmt = conn.prepareStatement (buff.toString ());

      pstmt.setInt (1 , getOID ());
      pstmt.setInt (2 , getOID ());
      pstmt.setString (3 , getCD_Produto ());
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
      // passando como parâmetro o NM_Produto do DSN
      // o NM_Produto de usuário e a senha do banco.
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
    buff.append ("UPDATE Produtos SET NM_Produto=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, DM_Unidade=?, DM_Tipo=?, oid_Produto_Tabela_Frete=? , DM_Classificacao=? ");
    buff.append ("WHERE OID_Produto=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {

      if (getOid_Produto_Tabela_Frete()<=0) {
        setOid_Produto_Tabela_Frete(getOID ());
      }

      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getNM_Produto ());
      pstmt.setString (2 , getDt_Stamp ());
      pstmt.setString (3 , getUsuario_Stamp ());
      pstmt.setString (4 , getDm_Stamp ());
      pstmt.setString (5 , getDM_Unidade ());
      pstmt.setString (6 , getDM_Tipo ());
      pstmt.setInt (7 , getOid_Produto_Tabela_Frete ());
      pstmt.setString (8 , getDM_Classificacao ());
      pstmt.setInt (9 , getOID ());

      // System.out.println(pstmt.toString());


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
      // passando como parâmetro o NM_Produto do DSN
      // o NM_Produto de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    StringBuffer buff = new StringBuffer ();

    buff.append ("DELETE FROM Referencias ");
    buff.append ("WHERE OID_Produto=?");

    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.executeUpdate ();

      buff.delete (0 , buff.length ());

      buff.append ("DELETE FROM Produtos ");
      buff.append ("WHERE OID_Produto=?");

      pstmt = conn.prepareStatement (buff.toString ());

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

  public static final ProdutoBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Produto do DSN
      // o NM_Produto de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    ProdutoBean p = new ProdutoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Produtos.OID_Produto, ");
      buff.append ("	Produtos.CD_Produto, ");
      buff.append ("	Produtos.NM_Produto,");
      buff.append ("	Produtos.DM_Tipo   ,");
      buff.append ("	Produtos.oid_Produto_Tabela_Frete,");
      buff.append ("	Produto_Tabela_Frete.CD_Produto as CD_Produto_Tabela_Frete, ");
      buff.append ("	Produto_Tabela_Frete.NM_Produto as NM_Produto_Tabela_Frete, ");
      buff.append ("	Produtos.DM_Classificacao   ,");
      buff.append ("	Produtos.DM_Unidade ");
      buff.append ("FROM Produtos, produtos Produto_Tabela_Frete  ");
      buff.append ("WHERE Produtos.oid_Produto_Tabela_Frete = Produto_Tabela_Frete.oid_Produto ");
      buff.append ("AND   Produtos.OID_Produto= ");
      buff.append (oid);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Produto (cursor.getString (2));
        p.setNM_Produto (cursor.getString (3));
        p.setDM_Tipo (cursor.getString (4));
        p.setOid_Produto_Tabela_Frete(cursor.getInt (5));
        p.setCD_Produto_Tabela_Frete (cursor.getString (6));
        p.setNM_Produto_Tabela_Frete (cursor.getString (7));
        p.setDM_Classificacao (cursor.getString (8));
        p.setDM_Unidade (cursor.getString (9));
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

  public static final ProdutoBean getByCD_Produto (String CD_Produto) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Produto do DSN
      // o NM_Produto de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    ProdutoBean p = new ProdutoBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Produto, ");
      buff.append ("	CD_Produto, ");
      buff.append ("	NM_Produto,");
      buff.append ("	DM_Unidade ");
      buff.append ("FROM Produtos ");
      buff.append ("WHERE CD_Produto= '");
      buff.append (CD_Produto);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setCD_Produto (cursor.getString (2));
        p.setNM_Produto (cursor.getString (3));
        p.setDM_Unidade (cursor.getString (4));

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

  public static final List getByNM_Produto (String NM_Produto) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Produto do DSN
      // o NM_Produto de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Produtos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Produtos.OID_Produto, ");
      buff.append ("	Produtos.CD_Produto, ");
      buff.append ("	Produtos.NM_Produto,");
      buff.append ("	Produtos.DM_Unidade, ");
      buff.append ("	Produtos.DM_Tipo ");
      buff.append (" FROM Produtos, produtos Produto_Tabela_Frete  ");
      buff.append (" WHERE Produtos.oid_Produto_Tabela_Frete = Produto_Tabela_Frete.oid_Produto ");
      buff.append (" AND Produtos.oid_Produto_Tabela_Frete is not null ");
      buff.append (" AND Produtos.NM_Produto LIKE'");
      buff.append (NM_Produto);
      buff.append ("%' ORDER BY Produtos.NM_Produto");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        ProdutoBean p = new ProdutoBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Produto (cursor.getString (2));
        p.setNM_Produto (cursor.getString (3));
        p.setDM_Unidade (cursor.getString (4));
        p.setDM_Tipo(nm_Tipo_Produto(cursor.getString (5)));

        Produtos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Produtos_Lista;
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

    List Produtos_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Produtos.OID_Produto, ");
      buff.append ("	Produtos.CD_Produto, ");
      buff.append ("	Produtos.NM_Produto,");
      buff.append ("	Produtos.DM_Unidade, ");
      buff.append ("	Produtos.DM_Tipo     ");
      buff.append (" FROM Produtos, produtos Produto_Tabela_Frete  ");
      buff.append (" WHERE Produtos.oid_Produto_Tabela_Frete = Produto_Tabela_Frete.oid_Produto ");
      buff.append (" AND Produtos.oid_Produto_Tabela_Frete not null ");
      buff.append (" ORDER BY NM_Produto");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        ProdutoBean p = new ProdutoBean ();
        p.setOID (cursor.getInt (1));
        p.setCD_Produto (cursor.getString (2));
        p.setNM_Produto (cursor.getString (3));
        p.setDM_Unidade (cursor.getString (4));
        p.setDM_Tipo(nm_Tipo_Produto(cursor.getString (5)));
        Produtos_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Produtos_Lista;
  }

  public void importa_Produto () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Produto do DSN
      // o NM_Produto de usuário e a senha do banco.
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

      ManipulaArquivo man = new ManipulaArquivo (";");

      BufferedReader buff = man.leArquivo ("C:\\temp\\arquivo.txt");

      //Tod a esta rotina terá de ser feita para ler uma linha
      //e depois ler cada elemento
      //separado pelo delimitador
      StringTokenizer st = null;
      String a = null;
      while ( (a = buff.readLine ()) != null) {

        st = new StringTokenizer (a , ";");
        while (st.hasMoreTokens ()) {

          this.CD_Produto = st.nextToken ();
          this.NM_Produto = st.nextToken ();
          // //// System.out.println(this.CD_Produto);
          // //// System.out.println(this.NM_Produto);
          this.insert ();
        }
      }

    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }


  public static String nm_Tipo_Produto (String dm_Tipo_Produto) {


    String NM_Tipo_Produto = "Diversos";
          if ("Q01".equals(dm_Tipo_Produto))
            NM_Tipo_Produto="Produto Quimico";
          if ("Q02".equals(dm_Tipo_Produto))
            NM_Tipo_Produto="Produto Quimico Perigoso";
          if ("Q03".equals(dm_Tipo_Produto))
            NM_Tipo_Produto="Resinas Termoplásticas";
          if ("A01".equals(dm_Tipo_Produto))
            NM_Tipo_Produto="Alimentos";
          if ("M01".equals(dm_Tipo_Produto))
            NM_Tipo_Produto="Ferros/Metais";
          if ("M02".equals(dm_Tipo_Produto))
            NM_Tipo_Produto="Madeiras";
          if ("P01".equals(dm_Tipo_Produto))
            NM_Tipo_Produto="Papel/Celulose";
          if ("R01".equals(dm_Tipo_Produto))
            NM_Tipo_Produto="Residuos";
          if ("E01".equals(dm_Tipo_Produto))
            NM_Tipo_Produto="Equipamentos/Máquinas";
          if ("E02".equals(dm_Tipo_Produto))
            NM_Tipo_Produto="Eletrônicos";
          if ("B01".equals(dm_Tipo_Produto))
            NM_Tipo_Produto="Borrachas";


    return NM_Tipo_Produto.toUpperCase();
  }


  public static void main (String args[]) throws Exception {
    ProdutoBean pp = new ProdutoBean ();
    //pp.setOID(2);
    //pp.setCD_Produto("LLL");
    //pp.setNM_Produto("Litro");
    pp.importa_Produto ();

//		ProdutoBean p = getByOID(2);
//		// //// System.out.println(p.getOID());
//		// //// System.out.println(p.getCD_Produto());
//		// //// System.out.println(p.getNM_Produto());



  }

  public void setDM_Unidade (String DM_Unidade) {
    this.DM_Unidade = DM_Unidade;
  }

  public String getDM_Unidade () {
    return DM_Unidade;
  }

  public String getDM_Tipo () {
    return DM_Tipo;
  }

  public void setDM_Tipo (String DM_Tipo) {
    this.DM_Tipo = DM_Tipo;
  }

  public void setOid_Produto_Tabela_Frete (int oid_Produto_Tabela_Frete) {
    this.oid_Produto_Tabela_Frete = oid_Produto_Tabela_Frete;
  }

  public void setDM_Classificacao (String DM_Classificacao) {
    this.DM_Classificacao = DM_Classificacao;
  }

  public void setCD_Produto_Tabela_Frete (String CD_Produto_Tabela_Frete) {
    this.CD_Produto_Tabela_Frete = CD_Produto_Tabela_Frete;
  }

  public void setNM_Produto_Tabela_Frete (String NM_Produto_Tabela_Frete) {
    this.NM_Produto_Tabela_Frete = NM_Produto_Tabela_Frete;
  }

  public int getOid_Produto_Tabela_Frete () {
    return oid_Produto_Tabela_Frete;
  }

  public String getDM_Classificacao () {
    return DM_Classificacao;
  }

  public String getCD_Produto_Tabela_Frete () {
    return CD_Produto_Tabela_Frete;
  }

  public String getNM_Produto_Tabela_Frete () {
    return NM_Produto_Tabela_Frete;
  }
}