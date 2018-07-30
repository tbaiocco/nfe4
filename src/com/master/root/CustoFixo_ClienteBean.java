package com.master.root;

import java.sql.*;
import java.util.*;

import auth.*;

public class CustoFixo_ClienteBean {
  private String DT_CustoFixo;
  private double VL_Custo_Fixo;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_Tipo_Movimento;
  private String NM_Tipo_Movimento;
  private String oid_Cliente;
  private String oid_Destinatario;
  private String DM_Aplicacao;
  private String DM_Tipo_Peso_Taxas;
  private double PE_Custo_Fixo;
  private String NM_Destinatario;

  public String getDM_Tipo_Peso_Taxas() {
	return DM_Tipo_Peso_Taxas;
}

public void setDM_Tipo_Peso_Taxas(String tipo_Peso_Taxas) {
	DM_Tipo_Peso_Taxas = tipo_Peso_Taxas;
}

public String getNM_Destinatario() {
	return NM_Destinatario;
}

public void setNM_Destinatario(String destinatario) {
	NM_Destinatario = destinatario;
}

public int getOid() {
	return oid;
}

public void setOid(int oid) {
	this.oid = oid;
}

public String getOid_Cliente() {
	return oid_Cliente;
}

public void setOid_Cliente(String oid_Cliente) {
	this.oid_Cliente = oid_Cliente;
}

public int getOid_Tipo_Movimento() {
	return oid_Tipo_Movimento;
}

public void setOid_Tipo_Movimento(int oid_Tipo_Movimento) {
	this.oid_Tipo_Movimento = oid_Tipo_Movimento;
}

public CustoFixo_ClienteBean () {
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

  public String getOID_Cliente () {
    return oid_Cliente;
  }

  public void setOID_Cliente (String oid_Cliente) {
    this.oid_Cliente = oid_Cliente;
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

  public int getOID_Tipo_Movimento () {
    return oid_Tipo_Movimento;
  }

  public void setOID_Tipo_Movimento (int n) {
    this.oid_Tipo_Movimento = n;
  }

  public String getNM_Tipo_Movimento () {
    return NM_Tipo_Movimento;
  }

  public void setNM_Tipo_Movimento (String NM_Tipo_Movimento) {
    this.NM_Tipo_Movimento = NM_Tipo_Movimento;
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
          "SELECT MAX(OID_Custo_Fixo_Cliente) FROM Custos_Fixos_Clientes");

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
    buff.append ("INSERT INTO Custos_Fixos_Clientes (OID_Custo_Fixo_Cliente, OID_Cliente, OID_Tipo_Movimento, VL_Custo_Fixo, PE_Custo_Fixo, DT_Stamp, Usuario_Stamp, Dm_Stamp, DM_Aplicacao, oid_Destinatario, DM_Tipo_Peso_Taxas) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setString (2 , getOID_Cliente ());
      pstmt.setInt (3 , getOID_Tipo_Movimento ());
      pstmt.setDouble (4 , getVL_Custo_Fixo ());
      pstmt.setDouble (5 , getPE_Custo_Fixo ());
      pstmt.setString (6 , getDt_Stamp ());
      pstmt.setString (7 , getUsuario_Stamp ());
      pstmt.setString (8 , getDm_Stamp ());
      pstmt.setString (9 , getDM_Aplicacao ());
      pstmt.setString (10 , getOid_Destinatario ());
      pstmt.setString (11 , getDM_Tipo_Peso_Taxas ());
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
    buff.append (" UPDATE Custos_Fixos_Clientes SET OID_Tipo_Movimento=?, VL_Custo_Fixo=?, PE_Custo_Fixo=? , DM_Aplicacao=?, DM_Tipo_Peso_Taxas=?  ");
    buff.append (" WHERE OID_Custo_Fixo_Cliente=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());

      pstmt.setInt (1 , getOID_Tipo_Movimento ());
      pstmt.setDouble (2 , getVL_Custo_Fixo ());
      pstmt.setDouble (3 , getPE_Custo_Fixo ());
      pstmt.setString (4 , getDM_Aplicacao ());
      pstmt.setString (5 , getDM_Tipo_Peso_Taxas ());
      pstmt.setInt (6 , getOID ());
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
    buff.append ("DELETE FROM Custos_Fixos_Clientes ");
    buff.append ("WHERE OID_Custo_Fixo_Cliente=?");
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

  public static final CustoFixo_ClienteBean getByOID (String oid)

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

    CustoFixo_ClienteBean p = new CustoFixo_ClienteBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Custos_Fixos_Clientes.OID_Custo_Fixo_Cliente, ");
      buff.append ("	Custos_Fixos_Clientes.OID_Tipo_Movimento, ");
      buff.append ("	Custos_Fixos_Clientes.OID_Cliente, ");
      buff.append ("	Custos_Fixos_Clientes.VL_Custo_Fixo, ");
      buff.append ("	Custos_Fixos_Clientes.PE_Custo_Fixo, ");
      buff.append ("	Custos_Fixos_Clientes.DM_Aplicacao, ");
      buff.append ("	Custos_Fixos_Clientes.DM_Tipo_Peso_Taxas, ");
      buff.append ("	Custos_Fixos_Clientes.OID_Destinatario, ");
      buff.append ("	Tipos_Movimentos.NM_Tipo_Movimento  ");
      buff.append (" FROM Custos_Fixos_Clientes, ");
      buff.append ("      Tipos_Movimentos ");
      buff.append (" WHERE (Custos_Fixos_Clientes.oid_Tipo_Movimento 		     = Tipos_Movimentos.oid_Tipo_Movimento )");
      buff.append ("AND OID_Custo_Fixo_Cliente= '");
      buff.append (oid);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Tipo_Movimento (cursor.getInt (2));
        p.setOID_Cliente (cursor.getString (3));
        p.setVL_Custo_Fixo (cursor.getDouble (4));
        p.setPE_Custo_Fixo (cursor.getDouble (5));
        p.setDM_Aplicacao (cursor.getString (6));
        p.setDM_Tipo_Peso_Taxas (cursor.getString (7));
        p.setOid_Destinatario (cursor.getString (8));
        p.setNM_Tipo_Movimento (cursor.getString (9));
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

  public static final CustoFixo_ClienteBean getByCustoFixo (String oid_Cliente, String data_CustoFixo)

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

    CustoFixo_ClienteBean p = new CustoFixo_ClienteBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Custos_Fixos_Clientes.OID_Custo_Fixo_Cliente, ");
      buff.append ("	Custos_Fixos_Clientes.OID_Tipo_Movimento, ");
      buff.append ("	Custos_Fixos_Clientes.OID_Cliente, ");
      buff.append ("	Custos_Fixos_Clientes.VL_Custo_Fixo, ");
      buff.append ("	Custos_Fixos_Clientes.OID_Destinatario, ");
      buff.append ("	Tipos_Movimentos.NM_Tipo_Movimento, ");
      buff.append ("	Custos_Fixos_Clientes.DM_Tipo_Peso_Taxas ");
      buff.append (" FROM Custos_Fixos_Clientes, ");
      buff.append ("      Tipos_Movimentos ");
      buff.append (" WHERE (Custos_Fixos_Clientes.oid_Tipo_Movimento 		     = Tipos_Movimentos.oid_Tipo_Movimento )");
      buff.append ("AND OID_Cliente= '");
      buff.append (oid_Cliente);
      buff.append ("'");
      buff.append ("AND DT_CustoFixo= '");
      buff.append (data_CustoFixo);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Tipo_Movimento (cursor.getInt (2));
        p.setOID_Cliente (cursor.getString (3));
        p.setVL_Custo_Fixo (cursor.getDouble (4));
        p.setOid_Destinatario (cursor.getString (5));
        p.setNM_Tipo_Movimento (cursor.getString (6));
        p.setDM_Tipo_Peso_Taxas (cursor.getString (7));
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


  public static final List getAll (String oid_Cliente) throws Exception {
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

    List CustoFixo_Clientes_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Custos_Fixos_Clientes.OID_Custo_Fixo_Cliente, ");
      buff.append ("	Custos_Fixos_Clientes.OID_Tipo_Movimento, ");
      buff.append ("	Custos_Fixos_Clientes.OID_Cliente, ");
      buff.append ("	Custos_Fixos_Clientes.VL_Custo_Fixo, ");
      buff.append ("	Custos_Fixos_Clientes.PE_Custo_Fixo, ");
      buff.append ("	Custos_Fixos_Clientes.OID_Destinatario, ");
      buff.append ("	Tipos_Movimentos.NM_Tipo_Movimento,  ");
      buff.append ("	Pessoas.NM_Razao_Social  ");
      buff.append (" FROM Tipos_Movimentos, Custos_Fixos_Clientes  ");
      buff.append ("      left join Pessoas on  Custos_Fixos_Clientes.OID_Destinatario = Pessoas.oid_Pessoa ");
      buff.append (" WHERE Custos_Fixos_Clientes.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento ");
      if (oid_Cliente != null && oid_Cliente.length()>5) {
        buff.append (" AND   Custos_Fixos_Clientes.oid_Cliente ='" + oid_Cliente + "'");
      }
      buff.append (" ORDER BY Pessoas.NM_Razao_Social ");

      // System.out.println(buff);
      
      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        CustoFixo_ClienteBean p = new CustoFixo_ClienteBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Tipo_Movimento (cursor.getInt (2));
        p.setOID_Cliente (cursor.getString (3));
        p.setVL_Custo_Fixo (cursor.getDouble (4));
        p.setPE_Custo_Fixo (cursor.getDouble (5));
        p.setOid_Destinatario (cursor.getString (6));
        p.setNM_Tipo_Movimento (cursor.getString (7));
    	p.setNM_Destinatario("-----");
        if (cursor.getString (8)!=null && cursor.getString (8).length()>4)
        	p.setNM_Destinatario(cursor.getString (8));
        CustoFixo_Clientes_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return CustoFixo_Clientes_Lista;
  }

  public static void main (String args[]) throws Exception {

  }

  public void setDM_Aplicacao (String DM_Aplicacao) {
    this.DM_Aplicacao = DM_Aplicacao;
  }

  public String getDM_Aplicacao () {
    return DM_Aplicacao;
  }

  public void setPE_Custo_Fixo (double PE_Custo_Fixo) {
    this.PE_Custo_Fixo = PE_Custo_Fixo;
  }

  public double getPE_Custo_Fixo () {
    return PE_Custo_Fixo;
  }

public String getOid_Destinatario() {
	return oid_Destinatario;
}

public void setOid_Destinatario(String oid_Destinatario) {
	this.oid_Destinatario = oid_Destinatario;
}

}
