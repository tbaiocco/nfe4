package com.master.root;

import java.sql.*;
import java.util.*;

import auth.*;
import com.master.util.*;

public class Movimento_EstoqueBean
    extends JavaUtil {

  private String DT_Movimento;
  private double NR_Quantidade;
  private double NR_Quantidade_Estoque;
  private double VL_Unitario;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private long oid;
  private long oid_Estoque;
  private String DM_Movimento;
  private String DM_Destino;
  private String NM_Movimento;
  private String NM_Destino;
  private long oid_Ordem_Servico;
  private String oid_Veiculo;
  private String NM_Fornecedor;
  private String NR_Nota_Fiscal;
  private String TX_Observacao;
  private long oid_Item_Solicitacao_Entrega;

  private String oid_Nota_Fiscal;

  public Movimento_EstoqueBean () {}

  public String getDT_Movimento () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Movimento);
    DT_Movimento = DataFormatada.getDT_FormataData ();

    return DT_Movimento;
  }

  public void setDT_Movimento (String DT_Movimento) {
    this.DT_Movimento = DT_Movimento;
  }

  public double getNR_Quantidade () {
    return NR_Quantidade;
  }

  public void setNR_Quantidade (double NR_Quantidade) {
    this.NR_Quantidade = NR_Quantidade;
  }

  public double getVL_Unitario () {
    return VL_Unitario;
  }

  public void setVL_Unitario (double VL_Unitario) {
    this.VL_Unitario = VL_Unitario;
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

  public long getOID () {
    return oid;
  }

  public void setOID (long n) {
    this.oid = n;
  }

  public long getOID_Estoque () {
    return oid_Estoque;
  }

  public void setOID_Estoque (long n) {
    this.oid_Estoque = n;
  }

  public void insert () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NR_Quantidade do DSN
      // o NR_Quantidade de usuário e a senha do banco.
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
          "SELECT MAX(OID_Movimento_Estoque) FROM Movimentos_Estoques");

      while (cursor.next ()) {
        long oid = cursor.getLong (1);
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
    buff.append ("INSERT INTO Movimentos_Estoques (OID_Movimento_Estoque, OID_Estoque, DT_Movimento, NR_Quantidade, VL_Unitario, Dt_Stamp, Usuario_Stamp, Dm_Stamp, DM_Movimento, DM_Destino, NM_Destino, OID_Veiculo, NM_Fornecedor, oid_Nota_Fiscal, NR_Nota_Fiscal, DT_Ordem, oid_item_solicitacao_entrega) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setLong (1 , getOID ());
      pstmt.setLong (2 , getOID_Estoque ());
      pstmt.setDate (3 , FormataData.formataDataTB (getDT_Movimento ()));
      pstmt.setDouble (4 , getNR_Quantidade ());
      pstmt.setDouble (5 , getVL_Unitario ());
      pstmt.setDate (6 , FormataData.formataDataTB (getDt_Stamp ()));
      pstmt.setString (7 , getUsuario_Stamp ());
      pstmt.setString (8 , getDm_Stamp ());
      if ("S".equals (getDM_Movimento ())) {
        pstmt.setString (9 , "S");
        pstmt.setString (10 , "C");
        pstmt.setString (11 , "Saída");
        pstmt.setString (12 , getOid_Veiculo ());
        pstmt.setString (13 , "--");
        pstmt.setString (14 , "");
        pstmt.setString (15 , "0");
      }
      if ("E".equals (getDM_Movimento ())) {
        pstmt.setString (9 , "E");
        pstmt.setString (10 , "C");
        pstmt.setString (11 , "Entrada");
        pstmt.setString (12 , "");
        pstmt.setString (13 , (doValida(getNM_Fornecedor()) ? getNM_Fornecedor() : ""));
        pstmt.setString (14 , getOid_Nota_Fiscal());
        pstmt.setString (15 , (doValida(getNR_Nota_Fiscal()) ? getNR_Nota_Fiscal() : "0"));


      }
      if ("A".equals (getDM_Movimento ())) {
        pstmt.setString (9 , "X");
        pstmt.setString (10 , "A");
        pstmt.setString (11 , "Ajuste");
        pstmt.setString (12 , "");
        pstmt.setString (13 , "AJUSTE ESTOQUE");
        pstmt.setString (14 , "");
        pstmt.setString (15 , "");
      }

      pstmt.setString (16 ,String.valueOf (Data.getDataYMD()+Data.getHoraHM()+Data.getMileSegundo()));

      pstmt.setLong (17 , getOid_Item_Solicitacao_Entrega());

      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }

    if ("E".equals (getDM_Movimento ())) {
      buff = new StringBuffer ();
      buff.append ("UPDATE Estoques SET VL_Custo=?, VL_Qtde_Atual=(VL_Qtde_Atual+?) ");
      buff.append ("WHERE OID_Estoque=? ");
      /*
       * Define os dados do SQL
       * e executa o insert no banco.
       */
      try {
        PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
        pstmt.setDouble (1 , getVL_Unitario ());
        pstmt.setDouble (2 , getNR_Quantidade ());
        pstmt.setLong (3 , getOID_Estoque ());
        pstmt.executeUpdate ();
      }
      catch (Exception e) {
        conn.rollback ();
        e.printStackTrace ();
        throw e;
      }
    }

        // System.out.println("DM MOV->> " + getDM_Movimento ());

    if ("A".equals (getDM_Movimento ())) {
      buff = new StringBuffer ();
      buff.append ("UPDATE Estoques SET VL_Custo=?, VL_Qtde_Atual=? ");
      buff.append ("WHERE OID_Estoque=? ");
      /*
       * Define os dados do SQL
       * e executa o insert no banco.
       */
      try {
        PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
        pstmt.setDouble (1 , getVL_Unitario ());
        pstmt.setDouble (2 , getNR_Quantidade ());
        pstmt.setLong (3 , getOID_Estoque ());
        // System.out.println(pstmt.toString());

        pstmt.executeUpdate ();
      }
      catch (Exception e) {
        conn.rollback ();
        e.printStackTrace ();
        throw e;
      }
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
      // passando como parâmetro o NR_Quantidade do DSN
      // o NR_Quantidade de usuário e a senha do banco.
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
    buff.append ("UPDATE Movimentos_Estoques SET DT_Movimento=?, NR_Quantidade=?, VL_Unitario=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, NR_Nota_Fiscal=?, NM_Fornecedor=? ");
    buff.append ("WHERE OID_Movimento_Estoque=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setDate (1 , FormataData.formataDataTB (getDT_Movimento ()));
      pstmt.setDouble (2 , getNR_Quantidade ());
      pstmt.setDouble (3 , getVL_Unitario ());
      pstmt.setDate (4 , FormataData.formataDataTB (getDt_Stamp ()));
      pstmt.setString (5 , getUsuario_Stamp ());
      pstmt.setString (6 , getDm_Stamp ());
      pstmt.setString (7 , getNR_Nota_Fiscal ());
      pstmt.setString (8 , getNM_Fornecedor ());
      pstmt.setLong (9 , getOID ());
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
      // passando como parâmetro o NR_Quantidade do DSN
      // o NR_Quantidade de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Movimentos_Estoques ");
    buff.append ("WHERE OID_Movimento_Estoque=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setLong (1 , getOID ());
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

  public static final Movimento_EstoqueBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NR_Quantidade do DSN
      // o NR_Quantidade de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Movimento_EstoqueBean p = new Movimento_EstoqueBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                       ");
      buff.append ("   OID_Movimento_Estoque,    ");
      buff.append ("   OID_Estoque,              ");
      buff.append ("	DT_Movimento,             ");
      buff.append ("	NR_Quantidade,            ");
      buff.append ("	VL_Unitario,              ");
      buff.append ("	DM_Movimento,             ");
      buff.append ("	DM_Destino,               ");
      buff.append ("	NM_Destino,               ");
      buff.append ("	NM_Fornecedor,               ");
      buff.append ("   OID_Ordem_Servico,     ");
      buff.append ("   NR_Nota_Fiscal,     ");
      buff.append ("   OID_Usuario              ");
      buff.append ("FROM Movimentos_Estoques     ");
      buff.append ("WHERE OID_Movimento_Estoque= ");
      buff.append (oid);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {

    	System.out.println("MOV EST");

        p.setOID (cursor.getLong (1));
        p.setOID_Estoque (cursor.getLong (2));
        p.setDT_Movimento (cursor.getString (3));
        p.setNR_Quantidade (cursor.getDouble (4));
        p.setVL_Unitario (cursor.getDouble (5));
        p.setDM_Movimento (cursor.getString (6));
        p.setDM_Destino (cursor.getString (7));
        p.setNM_Destino (cursor.getString (8));
        p.setOid_Ordem_Servico (cursor.getLong (10));

        p.setNM_Fornecedor (" ");
        p.setNR_Nota_Fiscal (" ");

        if ("E".equals(cursor.getString (6))) {
	        p.setNM_Fornecedor (cursor.getString (9));
	        p.setNR_Nota_Fiscal (cursor.getString (11));
        }

        if (cursor.getLong("oid_Usuario")>0) {
            String sql =  	" SELECT NM_Usuario  " +
				          	" FROM   Usuarios " +
				          	" WHERE  Usuarios.oid_Usuario = " + cursor.getLong("oid_Usuario");
            Statement stmt3 = conn.createStatement ();
            ResultSet resUser = stmt3.executeQuery (sql);
            if (resUser.next ()) {
                p.setTX_Observacao("Lancado por:" + resUser.getString("NM_Usuario")	);
		    }
        }


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

  public static final Movimento_EstoqueBean getByDT_Movimento (String DT_Movimento) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NR_Quantidade do DSN
      // o NR_Quantidade de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Movimento_EstoqueBean p = new Movimento_EstoqueBean ();
    try {
      StringBuffer buff = new StringBuffer ();
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
      buff.append ("WHERE DT_Movimento = '");
      buff.append (DT_Movimento);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getLong (1));
        p.setOID_Estoque (cursor.getLong (2));
        p.setDT_Movimento (cursor.getString (3));
        p.setNR_Quantidade (cursor.getDouble (4));
        p.setVL_Unitario (cursor.getDouble (5));
        p.setDM_Movimento (cursor.getString (6));
        p.setDM_Destino (cursor.getString (7));
        p.setNM_Destino (cursor.getString (8));
        p.setOid_Ordem_Servico (cursor.getLong (9));
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

  public static final List getByOID_Estoque (int oid) throws Exception  {

	    return getByOID_Estoque(oid, "");

  }


  public static final List getByOID_Estoque (int oid_Estoque, String DM_Movimento) throws Exception {
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

    List Movimentos_Estoques_Lista = new ArrayList ();
    try {

      String dt_Movimento="" , dt_Ordem="01/01/2000";
      String sql ="";
      double qt_estoque=0;


      if ("".equals(DM_Movimento)) {
	      sql =
	    	  //" SELECT DT_Movimento, DT_Ordem, NR_Quantidade, oid_movimento_estoque, VL_Unitario  FROM Movimentos_Estoques WHERE OID_Estoque = " + oid_Estoque + 	" AND DM_movimento='X'  ORDER BY dt_movimento DESC LIMIT 1  ";
	    	    " SELECT DT_Movimento, DT_Ordem, NR_Quantidade, oid_movimento_estoque, VL_Unitario  FROM Movimentos_Estoques WHERE OID_Estoque = " + oid_Estoque + 	" AND DM_movimento='X'  AND DT_Ordem IS NOT NULL ORDER BY DT_Ordem DESC  LIMIT 1  ";

	      Statement stmtAj = conn.createStatement ();
	      ResultSet  resAj=  stmtAj.executeQuery (sql);

          while (resAj.next ()) {
	    	  dt_Movimento=resAj.getString("DT_Movimento");
	      	  dt_Ordem=resAj.getString("DT_Ordem");
	    	  qt_estoque=resAj.getDouble("NR_Quantidade");

	    	  Movimento_EstoqueBean p = new Movimento_EstoqueBean ();
	    	  p.setOID (resAj.getLong ("oid_movimento_estoque"));
	    	  p.setOID_Estoque (oid_Estoque);
	    	  p.setDT_Movimento (dt_Movimento);
	    	  p.setNR_Quantidade (qt_estoque);
	    	  p.setVL_Unitario (resAj.getDouble("VL_Unitario"));
	    	  p.setDM_Movimento ("X");
	    	  p.setDM_Destino (" ");
	    	  p.setNM_Destino (" ");
	    	  p.setOid_Ordem_Servico (0);
	    	  p.setOid_Veiculo (" ");
	          p.setNR_Nota_Fiscal("");
	          p.setNM_Fornecedor (" ");
	          p.setNM_Movimento ("*AJUSTE*");
	          Movimentos_Estoques_Lista.add (p);
	      }
      }
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                       ");
      buff.append ("   OID_Movimento_Estoque,    ");
      buff.append ("   OID_Estoque,              ");
      buff.append ("	DT_Movimento,             ");
      buff.append ("	NR_Quantidade,            ");
      buff.append ("	VL_Unitario,              ");
      buff.append ("	DM_Movimento,             ");
      buff.append ("	DM_Destino,               ");
      buff.append ("	NM_Destino,               ");
      buff.append ("	NM_Fornecedor,               ");
      buff.append ("   OID_Ordem_Servico,     ");
      buff.append ("   OID_Veiculo,     ");
      buff.append ("   OID_Nota_Fiscal,     ");
      buff.append ("   NR_Nota_Fiscal     ");
      buff.append ("FROM Movimentos_Estoques     ");
      buff.append ("WHERE OID_Estoque = ");
      buff.append (oid_Estoque  );

      if (!"".equals(dt_Movimento)) {
          buff.append (" AND Movimentos_Estoques.dt_Movimento >= '" + dt_Movimento + "'");
          ///buff.append (" AND Movimentos_Estoques.dt_Ordem 	   > '" + dt_Ordem + "'");
          buff.append (" AND Movimentos_Estoques.DM_Movimento <> 'X'");
      }

      if (!"".equals(DM_Movimento)) {
          buff.append (" AND Movimentos_Estoques.DM_Movimento = '" + DM_Movimento + "'");
      }

      buff.append (" ORDER BY DT_Movimento, DM_Movimento  " );//  DM_Movimento DESC" );

      Statement stmt = conn.createStatement ();
      Statement stmt2 = conn.createStatement ();
      ResultSet cursor =  stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {

    	Movimento_EstoqueBean p = new Movimento_EstoqueBean ();
        p.setOID (cursor.getLong (1));
        p.setOID_Estoque (cursor.getLong (2));
        p.setDT_Movimento (cursor.getString (3));
        p.setNR_Quantidade (cursor.getDouble (4));
        p.setVL_Unitario (cursor.getDouble (5));
        p.setDM_Movimento (cursor.getString (6));
        p.setDM_Destino (cursor.getString (7));
        p.setNM_Destino (cursor.getString (8));
        p.setOid_Ordem_Servico (cursor.getLong (10));
        p.setOid_Veiculo (" ");
        if ("S".equals (p.getDM_Movimento ())) {
          if (cursor.getString (11) != null && cursor.getString (11).length () > 4) {
            p.setOid_Veiculo (cursor.getString (11));
          }
          else {

            if (p.getOid_Ordem_Servico () > 0) {
              sql = " SELECT oid_Veiculo FROM Ordens_Servicos WHERE Ordens_Servicos.oid_Veiculo is not null AND Ordens_Servicos.oid_Ordem_Servico = " + p.getOid_Ordem_Servico ();
              ResultSet res = stmt2.executeQuery (sql);
              if (res.next ()) {
                p.setOid_Veiculo (res.getString ("oid_Veiculo"));
              }
            }
          }
        }
        p.setNM_Fornecedor (" ");
        p.setNR_Nota_Fiscal (" ");

        if (cursor.getString ("NR_Nota_Fiscal") != null && cursor.getString ("NR_Nota_Fiscal").length()>0){
            p.setNR_Nota_Fiscal(cursor.getString ("NR_Nota_Fiscal"));
        }

        if (cursor.getString (9) != null && cursor.getString (9).length()>4){
          if (cursor.getString (12) != null && cursor.getString (12).length()>4){
        	  sql = "SELECT nr_nota_fiscal from notas_fiscais_transacoes where oid_nota_fiscal = '" + cursor.getString (12) + "'";
        	  ResultSet res = stmt2.executeQuery(sql);
              if (res.next()) {
                  p.setNR_Nota_Fiscal (res.getString(1));
              }
            } else {
              p.setNM_Fornecedor (cursor.getString (9));
            }
        }

        if ("E".equals(p.getDM_Movimento()))   p.setNM_Movimento ("ENTRADA");
        if ("S".equals(p.getDM_Movimento()))   p.setNM_Movimento ("SAIDA");
        if ("C".equals(p.getDM_Movimento()))   p.setNM_Movimento ("CANCELADO");


        if ("E".equals (p.getDM_Movimento())) {
        	qt_estoque += p.getNR_Quantidade();
        }
        if ("S".equals (p.getDM_Movimento())) {
        	qt_estoque -= p.getNR_Quantidade();
        }

        //if (atual<0) atual=0;
	    p.setNR_Quantidade_Estoque(qt_estoque);

        Movimentos_Estoques_Lista.add (p);
      }
      cursor.close ();
      stmt2.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Movimentos_Estoques_Lista;
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

    List Movimentos_Estoques_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
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
      buff.append ("FROM Movimentos_Estoques   ORDER BY  DT_Movimento desc, DM_Movimento ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Movimento_EstoqueBean p = new Movimento_EstoqueBean ();
        p.setOID (cursor.getLong (1));
        p.setOID_Estoque (cursor.getLong (2));
        p.setDT_Movimento (cursor.getString (3));
        p.setNR_Quantidade (cursor.getDouble (4));
        p.setVL_Unitario (cursor.getDouble (5));
        p.setDM_Movimento (cursor.getString (6));
        p.setDM_Destino (cursor.getString (7));
        p.setNM_Destino (cursor.getString (8));
        p.setOid_Ordem_Servico (cursor.getLong (9));


        Movimentos_Estoques_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Movimentos_Estoques_Lista;
  }

  public void setDM_Movimento (String DM_Movimento) {
    this.DM_Movimento = DM_Movimento;
  }

  public String getDM_Movimento () {
    return DM_Movimento;
  }

  public void setDM_Destino (String DM_Destino) {
    this.DM_Destino = DM_Destino;
  }

  public String getDM_Destino () {
    return DM_Destino;
  }

  public void setNM_Destino (String NM_Destino) {
    this.NM_Destino = NM_Destino;
  }

  public String getNM_Destino () {
    return NM_Destino;
  }

  public void setOid_Ordem_Servico (long oid_Ordem_Servico) {
    this.oid_Ordem_Servico = oid_Ordem_Servico;
  }

  public void setOid_Veiculo (String oid_Veiculo) {
    this.oid_Veiculo = oid_Veiculo;
  }

  public void setNM_Fornecedor (String NM_Fornecedor) {
    this.NM_Fornecedor = NM_Fornecedor;
  }

  public long getOid_Ordem_Servico () {
    return oid_Ordem_Servico;
  }

  public String getOid_Veiculo () {
    return oid_Veiculo;
  }

  public String getNM_Fornecedor () {
    return NM_Fornecedor;
  }

public String getOid_Nota_Fiscal() {
	return oid_Nota_Fiscal;
}

public void setOid_Nota_Fiscal(String oid_Nota_Fiscal) {
	this.oid_Nota_Fiscal = oid_Nota_Fiscal;
}

public String getNR_Nota_Fiscal() {
	return NR_Nota_Fiscal;
}

public void setNR_Nota_Fiscal(String nota_Fiscal) {
	NR_Nota_Fiscal = nota_Fiscal;
}

public String getNM_Movimento() {
	return NM_Movimento;
}

public void setNM_Movimento(String movimento) {
	NM_Movimento = movimento;
}

public String getTX_Observacao() {
	return TX_Observacao;
}

public void setTX_Observacao(String observacao) {
	TX_Observacao = observacao;
}

public double getNR_Quantidade_Estoque() {
	return NR_Quantidade_Estoque;
}

public void setNR_Quantidade_Estoque(double quantidade_Estoque) {
	NR_Quantidade_Estoque = quantidade_Estoque;
}

public long getOid_Item_Solicitacao_Entrega() {
	return oid_Item_Solicitacao_Entrega;
}

public void setOid_Item_Solicitacao_Entrega(long oid_Item_Solicitacao_Entrega) {
	this.oid_Item_Solicitacao_Entrega = oid_Item_Solicitacao_Entrega;
}
}
