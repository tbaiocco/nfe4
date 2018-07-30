package com.master.root;

import java.sql.*;
import java.util.*;

import auth.*;
import com.master.util.*;
import com.master.util.ed.Parametro_FixoED;

public class FeriadoBean {
  private String DM_Origem;
  private String NM_Origem;
  private String DT_Feriado;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private long oid;
  private long oid_Origem;
  private String NM_Tipo_Origem;
  protected Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

  public long getOid () {
    return oid;
  }

  public void setOid (long oid) {
    this.oid = oid;
  }

  public long getOid_Origem () {
    return oid_Origem;
  }

  public String getNM_Tipo_Origem () {
    return NM_Tipo_Origem;
  }

  public void setOid_Origem (long oid_Origem) {
    this.oid_Origem = oid_Origem;
  }

  public void setNM_Tipo_Origem (String NM_Tipo_Origem) {
    this.NM_Tipo_Origem = NM_Tipo_Origem;
  }

  public FeriadoBean () {
  }

  public String getDM_Origem () {
    return DM_Origem;
  }

  public void setDM_Origem (String DM_Origem) {
    this.DM_Origem = DM_Origem;
  }

  public String getNM_Origem () {
    return NM_Origem;
  }

  public void setNM_Origem (String NM_Origem) {
    this.NM_Origem = NM_Origem;
  }

  public String getDT_Feriado () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Feriado);
    DT_Feriado = DataFormatada.getDT_FormataData ();
    return DT_Feriado;
  }

  public void setDT_Feriado (String DT_Feriado) {
    this.DT_Feriado = DT_Feriado;
  }

  public long getOID_Origem () {
    return oid_Origem;
  }

  public void setOID_Origem (long n) {
    this.oid_Origem = n;
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

  public void insert () throws Exception {
    Connection conn = OracleConnection2.getWEB ();
    conn.setAutoCommit (false);

    
    String xx = "1" + String.valueOf (System.currentTimeMillis ()).toString ().substring (4 , 13);
    setOID (new Long (xx).longValue ());

    String dt_Feriado = (getDT_Feriado () + "        ").substring (0 , 5);

    String sql =
        " INSERT INTO Feriados" +
        "   (OID_Feriado, " +
        "    OID_Origem, " +
        "    DM_Origem, " +
        "    NM_Origem, " +
        "    DT_Feriado, " +
        "    Dt_Stamp, " +
        "    Usuario_Stamp, " +
        "    Dm_Stamp)" +
        " VALUES (" + getOID () +
        "        ," + getOID_Origem () +
        "        ,'" + getDM_Origem () + "'" +
        "        ,'" + getNM_Origem () + "'" +
        "        ," + JavaUtil.getSQLDate (dt_Feriado) +
        "        ," + JavaUtil.getSQLDate (getDt_Stamp ()) +
        "        ," + getUsuario_Stamp () +
        "        ," + JavaUtil.getSQLStringDef (getDm_Stamp () , "") +
        "        )";
    try {
      PreparedStatement pstmt = conn.prepareStatement (sql);
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
      // passando como parâmetro o NM_Feriados do DSN
      // o NM_Feriados de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Feriados ");
    buff.append ("WHERE OID_Feriado=?");
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

  public static final FeriadoBean getByOid (int oid_Feriado) throws Excecoes {
    Connection conn;
    FeriadoBean p = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , FeriadoBean.class.getName () , "getByRecord(FeriadoBean filtro)");
    }
    try {
      String sql =
          " SELECT Feriados.* " +
          " FROM Feriados " +
          " WHERE Feriados.OID_Feriado = " + oid_Feriado;

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sql);
      while (cursor.next ()) {
        p = new FeriadoBean ();
        p.setOID (cursor.getLong ("OID_Feriado"));
        p.setOID_Origem (cursor.getLong ("OID_Origem"));
        p.setNM_Origem (cursor.getString ("NM_Origem"));
        p.setDM_Origem (cursor.getString ("DM_Origem"));
        p.setDT_Feriado (cursor.getString ("DT_Feriado"));
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , FeriadoBean.class.getName () , "getByOID(long oid)");
    }
    return p;
  }

  public boolean getByVerificaDiaInutil (String dt_Dia , long oid_Cidade) throws Exception {
	    boolean diaUtil = true;

	    if (Data.éSabado (dt_Dia) || Data.éDomingo (dt_Dia)) { //sabado/domingo
	      diaUtil = false;
	    }
	    else {
	      if (oid_Cidade>0) {
		      if (getByFeriado (dt_Dia , oid_Cidade)) {
		        diaUtil = false;
		      }
	      }
	    }
	    return diaUtil;
	  }
  
  public boolean getByVerificaDiaUtil (String dt_Dia , long oid_Cidade) throws Exception {
    boolean diaUtil = false;

    if ("D".equals(parametro_FixoED.getDM_Dia_Nao_Util())) {
        if (Data.éDomingo (dt_Dia)) { //domingo
            diaUtil = true;
          }
    }
    if ("S".equals(parametro_FixoED.getDM_Dia_Nao_Util())) {
        if (Data.éSabado (dt_Dia)) { //sabado
            diaUtil = true;
          }
    }
    if ("SD".equals(parametro_FixoED.getDM_Dia_Nao_Util())) {
        if (Data.éSabado (dt_Dia) || Data.éDomingo (dt_Dia)) { //sabado/domingo
            diaUtil = true;
          }
    }
    if ("SDF".equals(parametro_FixoED.getDM_Dia_Nao_Util())) { //sabado/domingo e feriado
	    if (Data.éSabado (dt_Dia) || Data.éDomingo (dt_Dia)) { 
	      diaUtil = true;
	    }
	    else {
	      if (oid_Cidade>0) {
		      if (getByFeriado (dt_Dia , oid_Cidade)) {
		        diaUtil = true;
		      }
	      }
	    }
    }
    
    return diaUtil;
  }

  public boolean getByFeriado (String dt_Feriado , long oid_Cidade) throws Exception {

    String dia_Feriado = dt_Feriado;
    if (dt_Feriado != null && dt_Feriado.length () > 5) {
      dia_Feriado = dt_Feriado.substring (0 , 5);
    }
    boolean feriado = false;
    String sql = "";
    ResultSet res = null; ;
    ResultSet res2 = null; ;
    int count_feriado = 0;
    Connection conn;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , FeriadoBean.class.getName () , "getByRecord(FeriadoBean filtro)");
    }

    try {

      sql = " SELECT count(oid_Feriado) as count_feriado " +
          " FROM  Feriados ";
      Statement stmt = conn.createStatement ();
      res = stmt.executeQuery (sql);
      while (res.next ()) {
        count_feriado = res.getInt ("count_feriado");
      }
      if (count_feriado > 0) {
        sql = " SELECT oid_Feriado " +
            " FROM  Feriados " +
            " WHERE Feriados.DT_Feriado = '" + dia_Feriado + "'" +
            " AND   Feriados.DM_Origem = 'C' " +
            " AND   Feriados.oid_Origem = " + oid_Cidade;
        stmt = conn.createStatement ();
        res = stmt.executeQuery (sql);
        if (res.next ()) {
          feriado = true;
        }
        else {
          sql = " SELECT Estados.oid_Estado, Paises.oid_Pais " +
              " FROM Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises " +
              " WHERE Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
              " AND Regioes_Estados.OID_Estado = Estados.OID_Estado " +
              " AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais " +
              " AND Regioes_Paises.OID_Pais = Paises.OID_Pais " +
              " AND Cidades.oid_Cidade = " + oid_Cidade;
          res = stmt.executeQuery (sql);
          if (res.next ()) {

            sql = " SELECT oid_Feriado " +
                " FROM  Feriados " +
                " WHERE Feriados.DT_Feriado = '" + dia_Feriado + "'" +
                " AND   Feriados.DM_Origem = 'E' " +
                " AND   Feriados.oid_Origem = " + res.getLong ("oid_Estado");
            stmt = conn.createStatement ();
            res2 = stmt.executeQuery (sql);
            if (res2.next ()) {
              feriado = true;
            }
            else {
              sql = " SELECT oid_Feriado " +
                  " FROM  Feriados " +
                  " WHERE Feriados.DT_Feriado = '" + dia_Feriado + "'" +
                  " AND   Feriados.DM_Origem = 'P' " +
                  " AND   Feriados.oid_Origem = " + res.getLong ("oid_Pais");
              stmt = conn.createStatement ();
              res2 = stmt.executeQuery (sql);
              if (res2.next ()) {
                feriado = true;
              }
            }
          }
        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , FeriadoBean.class.getName () , "getByOID(long oid)");
    }
    return feriado;
  }

  public static final List lista (String dm_Origem) throws Excecoes {
    Connection conn;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , FeriadoBean.class.getName () , "lista");
    }
    List toReturn = new ArrayList ();
    FeriadoBean p;
    try {
      String sql =
          " SELECT Feriados.* " +
          " FROM Feriados " +
          " WHERE 1=1 " +
          " ORDER BY  DM_Origem ";

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sql);
      while (cursor.next ()) {
        p = new FeriadoBean ();
        p.setOID (cursor.getLong ("OID_Feriado"));
        p.setOID_Origem (cursor.getLong ("OID_Origem"));
        p.setNM_Origem (cursor.getString ("NM_Origem"));
        p.setDM_Origem (cursor.getString ("DM_Origem"));
        p.setDT_Feriado (cursor.getString ("DT_Feriado"));
        p.setNM_Tipo_Origem ("CIDADE");
        if ("E".equals (cursor.getString ("DM_Origem"))) {
          p.setNM_Tipo_Origem ("ESTADO");
        }
        if ("P".equals (cursor.getString ("DM_Origem"))) {
          p.setNM_Tipo_Origem ("PAIS");
        }
        toReturn.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , FeriadoBean.class.getName () , "lista");
    }
    return toReturn;
  }

}
