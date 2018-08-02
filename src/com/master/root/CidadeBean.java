package com.master.root;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.OracleConnection2;
import br.cte.model.Empresa;

import com.master.ed.CidadeED;
import com.master.rn.CidadeRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.ManipulaArquivo;
import com.master.util.Mensagens;

public class CidadeBean
    extends JavaUtil {
  private String NM_Cidade;
  private String NM_Codigo_Aereo;
  private String NM_Codigo_IBGE;
  private String DM_Tipo_Localizacao;
  private String DM_Suframa;
  private String DM_Fluvial;
  private String CD_Regiao_Estado;
  private String NM_Regiao_Estado;
  private String CD_Estado;
  private String NM_Estado;
  private String CD_Regiao_Pais;
  private String NM_Regiao_Pais;
  private String CD_Pais;
  private String NM_Pais;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private String CD_Cidade;
  private int oid_Regiao_Estado;
  private int oid_Estado;
  private int oid_Regiao_Pais;
  private int oid_Pais;
  private int OID_Subregiao;

  public CidadeBean () {
    NM_Cidade = "";
    CD_Cidade = "";
    NM_Codigo_Aereo = "";
    DM_Tipo_Localizacao = "";
    DM_Suframa = "";
    NM_Regiao_Estado = "";
    CD_Regiao_Estado = "";
    NM_Estado = "";
    CD_Estado = "";
    NM_Regiao_Pais = "";
    CD_Regiao_Pais = "";
    NM_Pais = "";
    CD_Pais = "";
    Usuario_Stamp = "";
    Dm_Stamp = "";
  }

  public String getCD_Cidade () {
    return CD_Cidade;
  }

  public void setCD_Cidade (String CD_Cidade) {
    this.CD_Cidade = CD_Cidade;
  }

  public String getNM_Cidade () {
    return NM_Cidade;
  }

  public void setNM_Cidade (String NM_Cidade) {
    this.NM_Cidade = NM_Cidade;
  }

  public String getNM_Codigo_Aereo () {
    return NM_Codigo_Aereo;
  }

  public void setNM_Codigo_Aereo (String NM_Codigo_Aereo) {
    this.NM_Codigo_Aereo = NM_Codigo_Aereo;
  }

  public String getDM_Tipo_Localizacao () {
    return DM_Tipo_Localizacao;
  }

  public void setDM_Tipo_Localizacao (String DM_Tipo_Localizacao) {
    this.DM_Tipo_Localizacao = DM_Tipo_Localizacao;
  }

  public String getDM_Suframa () {
    return DM_Suframa;
  }

  public void setDM_Suframa (String DM_Suframa) {
    this.DM_Suframa = DM_Suframa;
  }

  public String getCD_Estado () {
    return CD_Estado;
  }

  public void setCD_Estado (String CD_Estado) {
    this.CD_Estado = CD_Estado;
  }

  public String getNM_Estado () {
    return NM_Estado;
  }

  public void setNM_Estado (String NM_Estado) {
    this.NM_Estado = NM_Estado;
  }

  public String getCD_Regiao_Estado () {
    return CD_Regiao_Estado;
  }

  public void setCD_Regiao_Estado (String CD_Regiao_Estado) {
    this.CD_Regiao_Estado = CD_Regiao_Estado;
  }

  public String getNM_Regiao_Estado () {
    return NM_Regiao_Estado;
  }

  public void setNM_Regiao_Estado (String NM_Regiao_Estado) {
    this.NM_Regiao_Estado = NM_Regiao_Estado;
  }

  public String getCD_Pais () {
    return CD_Pais;
  }

  public void setCD_Pais (String CD_Pais) {
    this.CD_Pais = CD_Pais;
  }

  public String getNM_Pais () {
    return NM_Pais;
  }

  public void setNM_Pais (String NM_Pais) {
    this.NM_Pais = NM_Pais;
  }

  public String getCD_Regiao_Pais () {
    return CD_Regiao_Pais;
  }

  public void setCD_Regiao_Pais (String CD_Regiao_Pais) {
    this.CD_Regiao_Pais = CD_Regiao_Pais;
  }

  public String getNM_Regiao_Pais () {
    return NM_Regiao_Pais;
  }

  public void setNM_Regiao_Pais (String NM_Regiao_Pais) {
    this.NM_Regiao_Pais = NM_Regiao_Pais;
  }

  /*
   *---------------- Bloco Padr�o para Todas Classes ------------------
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

  public int getOID_Regiao_Estado () {
    return oid_Regiao_Estado;
  }

  public void setOID_Regiao_Estado (int n) {
    this.oid_Regiao_Estado = n;
  }

  public int getOID_Estado () {
    return oid_Estado;
  }

  public void setOID_Estado (int n) {
    this.oid_Estado = n;
  }

  public int getOID_Pais () {
    return oid_Pais;
  }

  public void setOID_Pais (int n) {
    this.oid_Pais = n;
  }

  public int getOID_Regiao_Pais () {
    return oid_Regiao_Pais;
  }

  public void setOID_Regiao_Pais (int n) {
    this.oid_Regiao_Pais = n;
  }

  public void insert () throws Exception {
    /*
     * Abre a conex�o com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conex�o ao gerenciador do driver
      // passando como par�metro o NM_Cidade do DSN
      // o NM_Cidade de usu�rio e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Gera um novo c�digo (OID)
     */
    try {
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (
          "SELECT MAX(OID_Cidade) FROM Cidades");

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
    buff.append ("INSERT INTO Cidades (OID_Cidade, OID_Regiao_Estado, CD_Cidade, NM_Cidade, NM_Codigo_Aereo, DM_Tipo_Localizacao, DM_Suframa, Dt_Stamp, Usuario_Stamp, Dm_Stamp, DM_Fluvial) ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
      pstmt.setInt (2 , getOID_Regiao_Estado ());
      pstmt.setString (3 , getCD_Cidade ());
      pstmt.setString (4 , getNM_Cidade ());
      pstmt.setString (5 , getNM_Codigo_Aereo ());
      pstmt.setString (6 , getDM_Tipo_Localizacao ());
      pstmt.setString (7 , getDM_Suframa ());
      pstmt.setString (8 , getDt_Stamp ());
      pstmt.setString (9 , getUsuario_Stamp ());
      pstmt.setString (10 , getDm_Stamp ());
      pstmt.setString (11 , getDM_Fluvial ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conex�o.
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

  public void insert (String nm_cidade , String cd_estado) throws Exception {
    /*
     * Abre a conex�o com o banco
     */

    nm_cidade = (nm_cidade + "                                          ").substring (0 , 40);
    nm_cidade.trim ().toUpperCase ();
    cd_estado.toUpperCase ();

    Connection conn = null;
    try {
      // Pede uma conex�o ao gerenciador do driver
      // passando como par�metro o NM_Cidade do DSN
      // o NM_Cidade de usu�rio e a senha do banco.
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
          " SELECT oid_Regiao_Estado FROM Regioes_Estados, Estados " +
          " WHERE Regioes_Estados.oid_Estado = Estados.oid_Estado " +
          " AND Estados.cd_Estado='" + cd_estado + "'");

      while (cursor.next ()) {

        setOid_Regiao_Estado (cursor.getInt ("oid_Regiao_Estado"));
        setNM_Cidade (nm_cidade);
        setCD_Cidade ("");
        setDM_Fluvial ("N");
        setDM_Suframa ("N");
        setDM_Tipo_Localizacao ("I");
        setNM_Codigo_Aereo ("");
        this.insert ();
      }
      cursor.close ();
      stmt.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conex�o.
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
     * Abre a conex�o com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conex�o ao gerenciador do driver
      // passando como par�metro o NM_Cidade do DSN
      // o NM_Cidade de usu�rio e a senha do banco.
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
    buff.append ("UPDATE Cidades SET NM_Cidade=?, NM_Codigo_Aereo=?, DM_Tipo_Localizacao=?, DM_Suframa=?, OID_Regiao_Estado=?, DM_Fluvial=?, CD_Cidade=?, NM_Codigo_IBGE=?  ");
    buff.append ("WHERE OID_Cidade=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getNM_Cidade ());
      pstmt.setString (2 , getNM_Codigo_Aereo ());
      pstmt.setString (3 , getDM_Tipo_Localizacao ());
      pstmt.setString (4 , getDM_Suframa ());
      pstmt.setInt (5 , getOID_Regiao_Estado ());
      pstmt.setString (6 , getDM_Fluvial ());
      pstmt.setString (7 , getCD_Cidade ());
      pstmt.setString (8 , getNM_Codigo_IBGE ());
      pstmt.setInt (9 , getOID ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conex�o.
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
     * Abre a conex�o com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conex�o ao gerenciador do driver
      // passando como par�metro o NM_Cidade do DSN
      // o NM_Cidade de usu�rio e a senha do banco.
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
    buff.append ("DELETE FROM Cidades ");
    buff.append ("WHERE OID_Cidade=?");
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
     * Faz o commit e fecha a conex�o.
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

  public static final CidadeBean getByOID (Empresa empresa, int oid) throws Exception {
    // Abre a conex�o com o banco
    Connection conn = OracleConnection2.getWEB (empresa);
    conn.setAutoCommit (false); 

    CidadeBean p = new CidadeBean ();
    StringBuffer buff = new StringBuffer ();
    buff.append ("SELECT OID_Cidade, ");
    buff.append ("	Cidades.NM_Cidade, ");
    buff.append ("	Cidades.CD_Cidade, ");
    buff.append ("	Cidades.NM_Codigo_Aereo, ");
    buff.append ("	Cidades.DM_Tipo_Localizacao, ");
    buff.append ("	Cidades.DM_Suframa, ");
    buff.append ("	Regioes_Estados.OID_Regiao_Estado, ");
    buff.append ("	Regioes_Estados.NM_Regiao_Estado, ");
    buff.append ("	Regioes_Estados.CD_Regiao_Estado, ");
    buff.append ("	Estados.OID_Estado, ");
    buff.append ("	Estados.NM_Estado, ");
    buff.append ("	Estados.CD_Estado, ");
    buff.append ("	Regioes_Paises.OID_Regiao_Pais, ");
    buff.append ("	Regioes_Paises.NM_Regiao_Pais, ");
    buff.append ("	Regioes_Paises.CD_Regiao_Pais, ");
    buff.append ("	Paises.OID_Pais, ");
    buff.append ("	Paises.NM_Pais, ");
    buff.append ("	Paises.CD_Pais, ");
    buff.append ("	Regioes_Estados.OID_Subregiao, ");
    buff.append ("	Cidades.NM_Codigo_IBGE, ");
    buff.append ("	Cidades.DM_Fluvial ");
    buff.append (" FROM Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
    buff.append (" WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
    buff.append (" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
    buff.append (" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
    buff.append (" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
    buff.append (" AND Cidades.OID_Cidade= ");
    buff.append (oid);
    Statement stmt = conn.createStatement ();
    ResultSet cursor = stmt.executeQuery (buff.toString ());
    try {
      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setNM_Cidade (cursor.getString (2));
        p.setCD_Cidade (cursor.getString (3));
        p.setNM_Codigo_Aereo (cursor.getString (4));
        p.setDM_Tipo_Localizacao (cursor.getString (5));
        p.setDM_Suframa (cursor.getString (6));
        p.setOID_Regiao_Estado (cursor.getInt (7));
        p.setNM_Regiao_Estado (cursor.getString (8));
        p.setCD_Regiao_Estado (cursor.getString (9));
        p.setOID_Estado (cursor.getInt (10));
        p.setNM_Estado (cursor.getString (11));
        p.setCD_Estado (cursor.getString (12));
        p.setOID_Regiao_Pais (cursor.getInt (13));
        p.setNM_Regiao_Pais (cursor.getString (14));
        p.setCD_Regiao_Pais (cursor.getString (15));
        p.setOID_Pais (cursor.getInt (16));
        p.setNM_Pais (cursor.getString (17));
        p.setCD_Pais (cursor.getString (18));
        p.setOID_Subregiao (cursor.getInt (19));
        p.setNM_Codigo_IBGE (cursor.getString (20));
        if (cursor.getString (20) ==null || "null".equals(cursor.getString (20))) {
            p.setNM_Codigo_IBGE (" ");
        }
        
        p.setDM_Fluvial (cursor.getString (21));
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , CidadeBean.class.getName () , "getByOID(int oid)");
    }
    return p;
  }

  public static final CidadeBean getByCD_Cidade (String CD_Cidade) throws Exception {
    // Abre a conex�o com o banco
    Connection conn = OracleConnection2.getWEB ();
    conn.setAutoCommit (false);

    CidadeBean p = new CidadeBean ();
    StringBuffer buff = new StringBuffer ();
    buff.append ("SELECT OID_Cidade, ");
    buff.append ("	Cidades.NM_Cidade, ");
    buff.append ("	Cidades.CD_Cidade, ");
    buff.append ("	Cidades.NM_Codigo_Aereo, ");
    buff.append ("	Cidades.DM_Tipo_Localizacao, ");
    buff.append ("	Cidades.DM_Suframa, ");
    buff.append ("	Regioes_Estados.OID_Regiao_Estado, ");
    buff.append ("	Regioes_Estados.NM_Regiao_Estado, ");
    buff.append ("	Regioes_Estados.CD_Regiao_Estado, ");
    buff.append ("	Estados.OID_Estado, ");
    buff.append ("	Estados.NM_Estado, ");
    buff.append ("	Estados.CD_Estado, ");
    buff.append ("	Regioes_Paises.OID_Regiao_Pais, ");
    buff.append ("	Regioes_Paises.NM_Regiao_Pais, ");
    buff.append ("	Regioes_Paises.CD_Regiao_Pais, ");
    buff.append ("	Paises.OID_Pais, ");
    buff.append ("	Paises.NM_Pais, ");
    buff.append ("	Paises.CD_Pais, ");
    buff.append ("	Regioes_Estados.OID_Subregiao, ");
    buff.append ("	Cidades.DM_Fluvial ");
    buff.append (" FROM Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
    buff.append (" WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
    buff.append (" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
    buff.append (" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
    buff.append (" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
    buff.append (" AND Cidades.CD_Cidade= '");
    buff.append (CD_Cidade);
    buff.append ("'");

    Statement stmt = conn.createStatement ();
    ResultSet cursor = stmt.executeQuery (buff.toString ());
    try {
      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setNM_Cidade (cursor.getString (2));
        p.setCD_Cidade (cursor.getString (3));
        p.setNM_Codigo_Aereo (cursor.getString (4));
        p.setDM_Tipo_Localizacao (cursor.getString (5));
        p.setDM_Suframa (cursor.getString (6));
        p.setOID_Regiao_Estado (cursor.getInt (7));
        p.setNM_Regiao_Estado (cursor.getString (8));
        p.setCD_Regiao_Estado (cursor.getString (9));
        p.setOID_Estado (cursor.getInt (10));
        p.setNM_Estado (cursor.getString (11));
        p.setCD_Estado (cursor.getString (12));
        p.setOID_Regiao_Pais (cursor.getInt (13));
        p.setNM_Regiao_Pais (cursor.getString (14));
        p.setCD_Regiao_Pais (cursor.getString (15));
        p.setOID_Pais (cursor.getInt (16));
        p.setNM_Pais (cursor.getString (17));
        p.setCD_Pais (cursor.getString (18));
        p.setOID_Subregiao (cursor.getInt (19));
        p.setDM_Fluvial (cursor.getString (20));
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , CidadeBean.class.getName () , "getByCD_Cidade(String CD_Cidade)");
    }
    return p;
  }

  public static final CidadeBean getByCidade (String NM_Cidade , String CD_Estado) throws Exception {
    // Abre a conex�o com o banco
    Connection conn = OracleConnection2.getWEB ();
    conn.setAutoCommit (false);

    //NM_Cidade=(NM_Cidade+"                                                              ").substring(0,40);

    CidadeBean p = new CidadeBean ();
    StringBuffer buff = new StringBuffer ();
    buff.append ("SELECT OID_Cidade, ");
    buff.append ("	Cidades.NM_Cidade, ");
    buff.append ("	Cidades.CD_Cidade, ");
    buff.append ("	Cidades.NM_Codigo_Aereo, ");
    buff.append ("	Cidades.DM_Tipo_Localizacao, ");
    buff.append ("	Cidades.DM_Suframa, ");
    buff.append ("	Regioes_Estados.OID_Regiao_Estado, ");
    buff.append ("	Regioes_Estados.NM_Regiao_Estado, ");
    buff.append ("	Regioes_Estados.CD_Regiao_Estado, ");
    buff.append ("	Estados.OID_Estado, ");
    buff.append ("	Estados.NM_Estado, ");
    buff.append ("	Estados.CD_Estado, ");
    buff.append ("	Regioes_Paises.OID_Regiao_Pais, ");
    buff.append ("	Regioes_Paises.NM_Regiao_Pais, ");
    buff.append ("	Regioes_Paises.CD_Regiao_Pais, ");
    buff.append ("	Paises.OID_Pais, ");
    buff.append ("	Paises.NM_Pais, ");
    buff.append ("	Paises.CD_Pais ");
    buff.append (" FROM Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
    buff.append (" WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
    buff.append (" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
    buff.append (" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
    buff.append (" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
    buff.append (" AND Cidades.NM_Cidade= '" + NM_Cidade + "'");
    buff.append (" AND Estados.CD_Estado= '" + CD_Estado + "'");

    Statement stmt = conn.createStatement ();
    ResultSet cursor = stmt.executeQuery (buff.toString ());
    try {
      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setNM_Cidade (cursor.getString (2));
        p.setCD_Cidade (cursor.getString (3));
        p.setNM_Codigo_Aereo (cursor.getString (4));
        p.setDM_Tipo_Localizacao (cursor.getString (5));
        p.setDM_Suframa (cursor.getString (6));
        p.setOID_Regiao_Estado (cursor.getInt (7));
        p.setNM_Regiao_Estado (cursor.getString (8));
        p.setCD_Regiao_Estado (cursor.getString (9));
        p.setOID_Estado (cursor.getInt (10));
        p.setNM_Estado (cursor.getString (11));
        p.setCD_Estado (cursor.getString (12));
        p.setOID_Regiao_Pais (cursor.getInt (13));
        p.setNM_Regiao_Pais (cursor.getString (14));
        p.setCD_Regiao_Pais (cursor.getString (15));
        p.setOID_Pais (cursor.getInt (16));
        p.setNM_Pais (cursor.getString (17));
        p.setCD_Pais (cursor.getString (18));
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , CidadeBean.class.getName () , "getByCD_Cidade(String CD_Cidade)");
    }
    return p;
  }

  public static final List getByNM_Cidade (String NM_Cidade) throws Exception {
    /*
     * Abre a conex�o com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conex�o ao gerenciador do driver
      // passando como par�metro o NM_Cidade do DSN
      // o NM_Cidade de usu�rio e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Cidade_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Cidade, ");
      buff.append ("	Cidades.NM_Cidade, ");
      buff.append ("	Cidades.CD_Cidade, ");
      buff.append ("	Cidades.NM_Codigo_Aereo, ");
      buff.append ("	Cidades.DM_Tipo_Localizacao, ");
      buff.append ("	Cidades.DM_Suframa, ");
      buff.append ("	Regioes_Estados.OID_Regiao_Estado, ");
      buff.append ("	Regioes_Estados.NM_Regiao_Estado, ");
      buff.append ("	Regioes_Estados.CD_Regiao_Estado, ");
      buff.append ("	Estados.OID_Estado, ");
      buff.append ("	Estados.NM_Estado, ");
      buff.append ("	Estados.CD_Estado, ");
      buff.append ("	Regioes_Paises.OID_Regiao_Pais, ");
      buff.append ("	Regioes_Paises.NM_Regiao_Pais, ");
      buff.append ("	Regioes_Paises.CD_Regiao_Pais, ");
      buff.append ("	Paises.OID_Pais, ");
      buff.append ("	Paises.NM_Pais, ");
      buff.append ("	Paises.CD_Pais ");
      buff.append (" FROM Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
      buff.append (" WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
      buff.append (" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
      buff.append (" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
      buff.append (" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
      buff.append (" AND Cidades.NM_Cidade LIKE '");
      buff.append (NM_Cidade);
      buff.append ("%'");
      buff.append (" Order by Cidades.NM_Cidade ");

//			// System.out.println("CidadeBean.getByNM_Cidade() sql = " + buff.toString());

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {

//				// System.out.println("CidadeBean.getByNM_Cidade() oid_cidade = "+cursor.getInt(1));

        CidadeBean p = new CidadeBean ();
        p.setOID (cursor.getInt (1));
        p.setNM_Cidade (cursor.getString (2));
        p.setCD_Cidade (cursor.getString (3));
        p.setNM_Codigo_Aereo (cursor.getString (4));
        p.setDM_Tipo_Localizacao (cursor.getString (5));
        p.setDM_Suframa (cursor.getString (6));
        p.setOID_Regiao_Estado (cursor.getInt (7));
        p.setNM_Regiao_Estado (cursor.getString (8));
        p.setCD_Regiao_Estado (cursor.getString (9));
        p.setOID_Estado (cursor.getInt (10));
        p.setNM_Estado (cursor.getString (11));
        p.setCD_Estado (cursor.getString (12));
        p.setOID_Regiao_Pais (cursor.getInt (13));
        p.setNM_Regiao_Pais (cursor.getString (14));
        p.setCD_Regiao_Pais (cursor.getString (15));
        p.setOID_Pais (cursor.getInt (16));
        p.setNM_Pais (cursor.getString (17));
        p.setCD_Pais (cursor.getString (18));
        Cidade_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Cidade_Lista;
  }

  public static final List getByCidade (String NM_Cidade , String oid_Pais , String oid_Regiao_Pais , String oid_Estado , String oid_Regiao_Estado) throws Exception {
    /*
     * Abre a conex�o com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conex�o ao gerenciador do driver
      // passando como par�metro o NM_Cidade do DSN
      // o NM_Cidade de usu�rio e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Cidade_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Cidade, ");
      buff.append ("	Cidades.NM_Cidade, ");
      buff.append ("	Cidades.CD_Cidade, ");
      buff.append ("	Cidades.NM_Codigo_Aereo, ");
      buff.append ("	Cidades.DM_Tipo_Localizacao, ");
      buff.append ("	Cidades.DM_Suframa, ");
      buff.append ("	Regioes_Estados.OID_Regiao_Estado, ");
      buff.append ("	Regioes_Estados.NM_Regiao_Estado, ");
      buff.append ("	Regioes_Estados.CD_Regiao_Estado, ");
      buff.append ("	Estados.OID_Estado, ");
      buff.append ("	Estados.NM_Estado, ");
      buff.append ("	Estados.CD_Estado, ");
      buff.append ("	Regioes_Paises.OID_Regiao_Pais, ");
      buff.append ("	Regioes_Paises.NM_Regiao_Pais, ");
      buff.append ("	Regioes_Paises.CD_Regiao_Pais, ");
      buff.append ("	Paises.OID_Pais, ");
      buff.append ("	Paises.NM_Pais, ");
      buff.append ("	Paises.CD_Pais ");
      buff.append (" FROM Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
      buff.append (" WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
      buff.append (" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
      buff.append (" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
      buff.append (" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
      buff.append (" AND Cidades.NM_Cidade LIKE'");
      buff.append (NM_Cidade);
      buff.append ("%'");
      if (oid_Pais != null && !oid_Pais.equals ("") && !oid_Pais.equals ("null")) {
        buff.append (" AND Paises.OID_Pais= ");
        buff.append (oid_Pais);
        buff.append ("");
      }
      if (oid_Regiao_Pais != null && !oid_Regiao_Pais.equals ("") && !oid_Regiao_Pais.equals ("null")) {
        buff.append (" AND Regioes_Paises.oid_Regiao_Pais= ");
        buff.append (oid_Regiao_Pais);
        buff.append ("");
      }
      if (oid_Estado != null && !oid_Estado.equals ("") && !oid_Estado.equals ("null")) {
        buff.append (" AND Estados.oid_Estado= ");
        buff.append (oid_Estado);
        buff.append ("");
      }
      if (oid_Regiao_Estado != null && !oid_Regiao_Estado.equals ("") && !oid_Regiao_Estado.equals ("null")) {
        buff.append (" AND Regioes_Estados.oid_Regiao_Estado= ");
        buff.append (oid_Regiao_Estado);
        buff.append ("");
      }

      buff.append (" Order by Cidades.NM_Cidade ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        CidadeBean p = new CidadeBean ();
        p.setOID (cursor.getInt (1));
        p.setNM_Cidade (cursor.getString (2));
        p.setCD_Cidade (cursor.getString (3));
        p.setNM_Codigo_Aereo (cursor.getString (4));
        p.setDM_Tipo_Localizacao (cursor.getString (5));
        p.setDM_Suframa (cursor.getString (6));
        p.setOID_Regiao_Estado (cursor.getInt (7));
        p.setNM_Regiao_Estado (cursor.getString (8));
        p.setCD_Regiao_Estado (cursor.getString (9));
        p.setOID_Estado (cursor.getInt (10));
        p.setNM_Estado (cursor.getString (11));
        p.setCD_Estado (cursor.getString (12));
        p.setOID_Regiao_Pais (cursor.getInt (13));
        p.setNM_Regiao_Pais (cursor.getString (14));
        p.setCD_Regiao_Pais (cursor.getString (15));
        p.setOID_Pais (cursor.getInt (16));
        p.setNM_Pais (cursor.getString (17));
        p.setCD_Pais (cursor.getString (18));
        Cidade_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Cidade_Lista;
  }

  public static final List getAll () throws Exception {
    Connection conn = null;
    try {
      // Pede uma conex�o ao gerenciador do driver
      // passando como par�metro o nome do DSN
      // o nome de usu�rio e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Cidade_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT OID_Cidade, ");
      buff.append ("	Cidades.NM_Cidade, ");
      buff.append ("	Cidades.CD_Cidade, ");
      buff.append ("	Cidades.NM_Codigo_Aereo, ");
      buff.append ("	Cidades.DM_Tipo_Localizacao, ");
      buff.append ("	Cidades.DM_Suframa, ");
      buff.append ("	Regioes_Estados.OID_Regiao_Estado, ");
      buff.append ("	Regioes_Estados.NM_Regiao_Estado, ");
      buff.append ("	Regioes_Estados.CD_Regiao_Estado, ");
      buff.append ("	Estados.OID_Estado, ");
      buff.append ("	Estados.NM_Estado, ");
      buff.append ("	Estados.CD_Estado, ");
      buff.append ("	Regioes_Paises.OID_Regiao_Pais, ");
      buff.append ("	Regioes_Paises.NM_Regiao_Pais, ");
      buff.append ("	Regioes_Paises.CD_Regiao_Pais, ");
      buff.append ("	Paises.OID_Pais, ");
      buff.append ("	Paises.NM_Pais, ");
      buff.append ("	Paises.CD_Pais ");
      buff.append (" FROM Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
      buff.append (" WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
      buff.append (" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
      buff.append (" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
      buff.append (" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
      buff.append (" Order by Cidades.NM_Cidade ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        CidadeBean p = new CidadeBean ();
        p.setOID (cursor.getInt (1));
        p.setNM_Cidade (cursor.getString (2));
        p.setCD_Cidade (cursor.getString (3));
        p.setNM_Codigo_Aereo (cursor.getString (4));
        p.setDM_Tipo_Localizacao (cursor.getString (5));
        p.setDM_Suframa (cursor.getString (6));
        p.setOID_Regiao_Estado (cursor.getInt (7));
        p.setNM_Regiao_Estado (cursor.getString (8));
        p.setCD_Regiao_Estado (cursor.getString (9));
        p.setOID_Estado (cursor.getInt (10));
        p.setNM_Estado (cursor.getString (11));
        p.setCD_Estado (cursor.getString (12));
        p.setOID_Regiao_Pais (cursor.getInt (13));
        p.setNM_Regiao_Pais (cursor.getString (14));
        p.setCD_Regiao_Pais (cursor.getString (15));
        p.setOID_Pais (cursor.getInt (16));
        p.setNM_Pais (cursor.getString (17));
        p.setCD_Pais (cursor.getString (18));
        Cidade_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Cidade_Lista;
  }

  public void importa_Cidade () throws Exception {
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
      ManipulaArquivo man = new ManipulaArquivo (";");

      BufferedReader buff = man.leArquivo ("C:\\temp\\cidade.txt");

      //Tod a esta rotina ter� de ser feita para ler uma linha
      //e depois ler cada elemento
      //separado pelo delimitador
      StringTokenizer st = null;
      String a = null;
      String Pula_Campo = null;
      while ( (a = buff.readLine ()) != null) {

        st = new StringTokenizer (a , ",");
        while (st.hasMoreTokens ()) {
          Pula_Campo = st.nextToken ();
          Pula_Campo = st.nextToken ();
          this.oid = new Integer (st.nextToken ()).intValue ();
          this.oid_Regiao_Estado = new Integer (st.nextToken ()).intValue ();
          this.CD_Cidade = st.nextToken ();
          this.setNM_Codigo_Aereo (this.getCD_Cidade ());
          this.NM_Cidade = st.nextToken ();
          Pula_Campo = st.nextToken ();
          this.DM_Tipo_Localizacao = st.nextToken ();

        }
        Connection conn2 = null;

        conn2 = OracleConnection2.getWEB ();
        conn2.setAutoCommit (false);

        StringBuffer buff2 = new StringBuffer ();
        buff2.append ("INSERT INTO Cidades (OID_Cidade, OID_Regiao_Estado, CD_Cidade, NM_Cidade, NM_CODIGO_AEREO, DM_Tipo_Localizacao) ");
        buff2.append ("VALUES (?,?,?,?,?,?)");
        try {
          PreparedStatement pstmt =
              conn2.prepareStatement (buff2.toString ());
          pstmt.setInt (1 , this.getOID ());
          pstmt.setInt (2 , this.getOID_Regiao_Estado ());
          pstmt.setString (3 , this.getCD_Cidade ());
          pstmt.setString (4 , this.getNM_Cidade ());
          pstmt.setString (5 , this.getNM_Codigo_Aereo ());
          pstmt.setString (6 , this.getDM_Tipo_Localizacao ());
          pstmt.executeUpdate ();
          conn2.commit ();
          conn2.close ();

        }
        catch (Exception e) {
          conn.rollback ();
          e.printStackTrace ();
          throw e;
        }
        /*
         * Faz o commit e fecha a conex�o.
         */
        try {
          conn.commit ();
        }
        catch (Exception e) {
          e.printStackTrace ();
          throw e;
        }
      }
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void retiraAspaNomeCidade () throws Exception {

    Connection conn = null;
    String sql = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    try {
      sql = " SELECT * FROM cidades where nm_cidade like '%\\'%' ";

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sql);

      while (cursor.next ()) {
        int oid_Cidade = cursor.getInt ("oid_cidade");
        String nm_Cidade = (cursor.getString ("nm_cidade")).replace ('\'' , '�');

//				// System.out.println("CidadeBean.retiraAspasNomeCidade() oid_Cidade="+oid_Cidade+" nm_Cidade="+nm_Cidade);

//				StringBuffer buff = new StringBuffer();
//				buff.append("UPDATE Cidades SET NM_Cidade=? ");
//				buff.append("WHERE OID_Cidade=?");
//
//				PreparedStatement pstmt = conn.prepareStatement(buff.toString());
//					pstmt.setString(1, nm_Cidade);
//					pstmt.setInt(2, oid_Cidade);
//					pstmt.executeUpdate();
      }

      cursor.close ();
      stmt.close ();

    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }

    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  //*** Relat�rio de Movimentos de Ordem de Servico
   public void relCidades (HttpServletRequest request , HttpServletResponse response) throws Excecoes {
     String Relatorio = request.getParameter ("Relatorio");
     String oid_Regiao_Estado = request.getParameter ("oid_Regiao_Estado");
     String oid_Estado = request.getParameter ("oid_Estado");

     String descFiltro = "";

     if (!JavaUtil.doValida (Relatorio)) {
       throw new Mensagens ("Nome do Relat�rio n�o informado!");
     }

     CidadeED ed = new CidadeED (response , Relatorio);
     if (doValida (oid_Regiao_Estado)) {
       ed.setOid_regiao_estado (Integer.parseInt (oid_Regiao_Estado));
     }
     if (doValida (oid_Estado)) {
       ed.setOid_estado (Integer.parseInt (oid_Estado));
     }

     ed.setDescFiltro (descFiltro);
     new CidadeRN ().relCidades (ed);
   }

  public void setOID_Subregiao (int OID_Subregiao) {
    this.OID_Subregiao = OID_Subregiao;
  }

  public int getOID_Subregiao () {
    return OID_Subregiao;
  }

  public String getDM_Fluvial () {
    return DM_Fluvial;
  }

  public void setDM_Fluvial (String DM_Fluvial) {
    this.DM_Fluvial = DM_Fluvial;
  }

  public void setOid (int oid) {
    this.oid = oid;
  }

  public int getOid () {
    return oid;
  }

  public int getOid_Pais () {
    return oid_Pais;
  }

  public void setOid_Pais (int oid_Pais) {
    this.oid_Pais = oid_Pais;
  }

  public void setOid_Estado (int oid_Estado) {
    this.oid_Estado = oid_Estado;
  }

  public int getOid_Estado () {
    return oid_Estado;
  }

  public int getOid_Regiao_Estado () {
    return oid_Regiao_Estado;
  }

  public void setOid_Regiao_Estado (int oid_Regiao_Estado) {
    this.oid_Regiao_Estado = oid_Regiao_Estado;
  }

  public int getOid_Regiao_Pais () {
    return oid_Regiao_Pais;
  }

  public void setOid_Regiao_Pais (int oid_Regiao_Pais) {
    this.oid_Regiao_Pais = oid_Regiao_Pais;
  }

public String getNM_Codigo_IBGE() {
	return NM_Codigo_IBGE;
}

public void setNM_Codigo_IBGE(String codigo_IBGE) {
	NM_Codigo_IBGE = codigo_IBGE;
}
}