package com.master.root;

import java.sql.*;
import java.util.*;

import auth.*;
import com.master.ed.*;
import com.master.rn.*;
import com.master.util.*;

public class Regiao_EntregaBean {
  private String DM_Destino;
  private String NM_Destino;
  private String DM_Origem;
  private String NM_Origem;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private long oid;
  private long oid_Origem;
  private long oid_Destino;
  private long OID_Modal;
  private String oid_Pessoa;
  private String NM_Razao_Social;
  private String NM_Modal;
  private String CD_Modal;
  private long NR_Prazo_Entrega;
  private String DM_Prazo;

  public long getOid () {
    return oid;
  }

  public void setOid (long oid) {
    this.oid = oid;
  }

  public long getOid_Destino () {
    return oid_Destino;
  }

  public void setOid_Destino (long oid_Destino) {
    this.oid_Destino = oid_Destino;
  }

  public String getOid_Pessoa () {
    return oid_Pessoa;
  }

  public void setOid_Pessoa (String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }


  public long getNR_Prazo_Entrega () {
    return NR_Prazo_Entrega;
  }

  public void setNR_Prazo_Entrega (long NR_Prazo_Entrega) {
    this.NR_Prazo_Entrega = NR_Prazo_Entrega;
  }

  public String getDM_Destino () {
    return DM_Destino;
  }

  public void setDM_Destino (String DM_Destino) {
    this.DM_Destino = DM_Destino;
  }

  public String getNM_Destino () {
    return NM_Destino;
  }

  public void setNM_Destino (String NM_Destino) {
    this.NM_Destino = NM_Destino;
  }

  public long getOID_Destino () {
    return oid_Destino;
  }

  public void setOID_Destino (long n) {
    this.oid_Destino = n;
  }

  public long getOID_Modal () {
    return OID_Modal;
  }

  public void setOID_Modal (long n) {
    this.OID_Modal = n;
  }

  public String getCD_Modal () {
    return CD_Modal;
  }

  public void setCD_Modal (String CD_Modal) {
    this.CD_Modal = CD_Modal;
  }

  public String getNM_Modal () {
    return NM_Modal;
  }

  public void setNM_Modal (String NM_Modal) {
    this.NM_Modal = NM_Modal;
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

    
    String xx = String.valueOf (System.currentTimeMillis ()).toString ().substring (4 , 13);
    setOID (new Long (xx).longValue ());

    try {
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery ("SELECT oid_Regiao_Entrega FROM Regioes_Entregas WHERE oid_Regiao_Entrega=" + getOID ());
      if (cursor.next ()) {
        cursor = stmt.executeQuery ("SELECT MAX(OID_Regiao_Entrega) FROM Regioes_Entregas");
        while (cursor.next ()) {
          long oid = cursor.getLong (1);
          setOID (oid + 1);
        }
      }
      cursor.close ();
      stmt.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    // System.out.println ("System.setOID() getDM_Destino" + getDM_Destino ());

    String sql =
        " INSERT INTO Regioes_Entregas" +
        "   (OID_Regiao_Entrega, " +
        "    OID_Modal, " +
        "    OID_Pessoa, " +
        "    OID_Origem, " +
        "    OID_Destino, " +
        "    DM_Prazo, " +
        "    DM_Origem, " +
        "    NM_Origem, " +
        "    DM_Destino, " +
        "    NM_Destino, " +
        "    Dt_Stamp, " +
        "    Usuario_Stamp, " +
        "    Dm_Stamp, " +
        "    NR_Prazo_Entrega)" +
        " VALUES (" + getOID () +
        "        ," + getOID_Modal () +
        "        ,'" + getOID_Pessoa () + "'" +
        "        ," + getOID_Origem () +
        "        ," + getOID_Destino () +
        "        ,'" + getDM_Prazo () + "'" +
        "        ,'" + getDM_Origem () + "'" +
        "        ,'" + getNM_Origem () + "'" +
        "        ,'" + getDM_Destino () + "'" +
        "        ,'" + getNM_Destino () + "'" +
        "        ," + JavaUtil.getSQLDate (getDt_Stamp ()) +
        "        ," + getUsuario_Stamp () +
        "        ," + JavaUtil.getSQLStringDef (getDm_Stamp () , "") +
        "        ," + getNR_Prazo_Entrega () + ")";
    try {
      // System.out.println (sql);
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

  public void update () throws Exception {
    Connection conn = OracleConnection2.getWEB ();
    conn.setAutoCommit (false);
    String sql =
        " UPDATE Regioes_Entregas" +
        "    SET OID_Modal=" + getOID_Modal () +
        "    ,OID_Origem=" + getOID_Origem () +
        "    ,DM_Prazo='" + getDM_Prazo () + "'" +
        "    ,DM_Origem='" + getDM_Origem () + "'" +
        "    ,NM_Origem='" + getNM_Origem () + "'" +
        "    ,OID_Pessoa='" + getOID_Pessoa () + "'" +
        "    ,OID_Destino=" + getOID_Destino () +
        "    ,DM_Destino='" + getDM_Destino () + "'" +
        "    ,NM_Destino='" + getNM_Destino () + "'" +
        "    ,NR_Prazo_Entrega=" + getNR_Prazo_Entrega () +
        " WHERE OID_Regiao_Entrega=" + getOID ();
    // System.out.println (sql);
    try {
      PreparedStatement pstmt = conn.prepareStatement (sql);
      pstmt.executeUpdate ();
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

  public void delete () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Regiao_Entrega do DSN
      // o NM_Regiao_Entrega de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Regioes_Entregas ");
    buff.append ("WHERE OID_Regiao_Entrega=?");
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


  public static final Regiao_EntregaBean getByOID (long oid) throws Exception {
    if (oid > 0) {
      Regiao_EntregaBean filtro = new Regiao_EntregaBean ();
      filtro.setOID (oid);
      List lista = getByRecord (filtro);
      Iterator iterator = lista.iterator ();
      if (iterator.hasNext ()) {
        return (Regiao_EntregaBean) iterator.next ();
      }
      else {
        return new Regiao_EntregaBean ();
      }
    }
    else {
      return new Regiao_EntregaBean ();
    }
  }

  public static final List getAll () throws Exception {
    Regiao_EntregaBean filtro = new Regiao_EntregaBean ();
    return getByRecord (filtro);
  }

  public static final List getByNR_CNPJ_CPF_Lista (String NR_CNPJ_CPF) throws Exception {
      Regiao_EntregaBean filtro = new Regiao_EntregaBean ();
      filtro.setOID_Pessoa (NR_CNPJ_CPF);
      return getByRecord (filtro);
  }

  public static final List getByNR_CNPJ_CPF_Lista (String NR_CNPJ_CPF , int oid_Modal, int oid_Destino) throws Exception {
    if (JavaUtil.doValida (NR_CNPJ_CPF)) {
      Regiao_EntregaBean filtro = new Regiao_EntregaBean ();
      filtro.setOID_Pessoa (NR_CNPJ_CPF);
      filtro.setOID_Modal (oid_Modal);
      filtro.setOID_Destino (oid_Destino);
      return getByRecord (filtro);
    }
    else {
      return new ArrayList ();
    }
  }

  public long getPrazoEntrega (long oid_Modal, long oid_Origem, long oid_Destino, String DM_Origem, String DM_Destino) throws Exception {
	  
	long nr_prazo_entrega=0;
    Connection conn;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , Regiao_EntregaBean.class.getName () , "getByRecord(Regiao_EntregaBean filtro)");
    }
    List toReturn = new ArrayList ();
    Regiao_EntregaBean p;
    try {
      String sql =
          " SELECT Regioes_Entregas.NR_Prazo_Entrega, Regioes_Entregas.DM_Prazo " +
          " FROM  Regioes_Entregas, Modal, Modal Modal_Grupo  " +
          " WHERE Regioes_Entregas.oid_Modal = Modal.oid_Modal " +
          " AND Modal.DM_Grupo_Servico = Modal_Grupo.DM_Grupo_Servico " +
          " AND Modal_Grupo.OID_Modal = " + oid_Modal +
          " AND Regioes_Entregas.DM_Origem = '" + DM_Origem + "'" +
          " AND Regioes_Entregas.DM_Destino = '" + DM_Destino + "'" +
          " AND Regioes_Entregas.OID_Origem = " + oid_Origem +
          " AND Regioes_Entregas.OID_Destino = " + oid_Destino;
      // System.out.println (sql);

      Statement stmt = conn.createStatement ();
      ResultSet res = stmt.executeQuery (sql);
      if (res.next ()) {
    	  nr_prazo_entrega = res.getLong("NR_Prazo_Entrega");
    	  if ("D".equals(res.getString("DM_Prazo"))) {
    		 nr_prazo_entrega=nr_prazo_entrega*24;
    	  }
      }
	
	///
    // System.out.println(" getPrazoEntrega->>" +nr_prazo_entrega);
    res.close ();
    stmt.close ();
    conn.close ();
  }
  catch (SQLException e) {
    throw new Excecoes (e.getMessage () , e , Regiao_EntregaBean.class.getName () , "getByOID(long oid)");
  }

	
	return nr_prazo_entrega;
  }

  public static final List getByRecord (Regiao_EntregaBean filtro) throws Excecoes {
    Connection conn;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , Regiao_EntregaBean.class.getName () , "getByRecord(Regiao_EntregaBean filtro)");
    }
    List toReturn = new ArrayList ();
    Regiao_EntregaBean p;
    try {
      String sql =
          " SELECT Regioes_Entregas.OID_Regiao_Entrega " +
          "       ,Regioes_Entregas.OID_Modal " +
          "       ,Regioes_Entregas.OID_Pessoa " +
          "       ,Pessoas.NM_Razao_Social" +
          "       ,Modal.CD_Modal " +
          "       ,Modal.NM_Modal " +
          "       ,Regioes_Entregas.OID_Origem " +
          "       ,Regioes_Entregas.NM_Origem " +
          "       ,Regioes_Entregas.DM_Origem " +
          "       ,Regioes_Entregas.OID_Destino " +
          "       ,Regioes_Entregas.NM_Destino " +
          "       ,Regioes_Entregas.DM_Destino " +
          "       ,Regioes_Entregas.DM_Prazo " +
          "       ,Regioes_Entregas.NR_Prazo_Entrega " +
          " FROM  Modal, Regioes_Entregas left join Pessoas " +
          "        on Regioes_Entregas.OID_Pessoa = Pessoas.OID_Pessoa " +
          " WHERE Regioes_Entregas.OID_Modal = Modal.OID_Modal ";

      if (filtro.getOID () > 0) {
        sql += " AND Regioes_Entregas.OID_Regiao_Entrega = " + filtro.getOID ();
      }

      if (JavaUtil.doValida (filtro.getOID_Pessoa ())) {
        sql += " AND Regioes_Entregas.OID_Pessoa = '" + filtro.getOID_Pessoa () + "'";
      }

      if (JavaUtil.doValida (filtro.getDM_Origem ())) {
          sql += " AND Regioes_Entregas.DM_Origem = '" + filtro.getDM_Origem () + "'";
        }

      if (JavaUtil.doValida (filtro.getDM_Destino ())) {
          sql += " AND Regioes_Entregas.DM_Destino = '" + filtro.getDM_Destino () + "'";
        }
      
      if (filtro.getOID_Modal () > 0) {
        sql += " AND Regioes_Entregas.OID_Modal = " + filtro.getOID_Modal ();
      }

      if (filtro.getOID_Origem () > 0) {
          sql += " AND Regioes_Entregas.OID_Origem = " + filtro.getOID_Origem ();
      }

      if (filtro.getOID_Destino () > 0) {
        sql += " AND Regioes_Entregas.OID_Destino = " + filtro.getOID_Destino ();
      }

      sql += " ORDER BY Pessoas.NM_Razao_Social, NM_DESTINO";

      // System.out.println (sql);

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sql);
      while (cursor.next ()) {
        p = new Regiao_EntregaBean ();

        p.setOID (cursor.getLong (1));
        p.setOID_Modal (cursor.getLong (2));
        p.setOID_Pessoa (cursor.getString (3));
    	p.setNM_Razao_Social ("----");
        if (cursor.getString ("NM_Razao_Social")!=null) {
        	p.setNM_Razao_Social (cursor.getString ("NM_Razao_Social"));
        }
        p.setCD_Modal (cursor.getString (5));
        p.setNM_Modal (cursor.getString (6));
        p.setOID_Origem (cursor.getLong (7));
        p.setNM_Origem (cursor.getString (8));
        p.setDM_Origem (cursor.getString (9));
        p.setOID_Destino (cursor.getLong (10));
        p.setNM_Destino (cursor.getString (11));
        p.setDM_Destino (cursor.getString (12));
        p.setDM_Prazo (cursor.getString (13));
        p.setNR_Prazo_Entrega (cursor.getLong ("NR_Prazo_Entrega"));

        // System.out.println(" Regiao Entrega getByRecord ->>" +cursor.getLong ("NR_Prazo_Entrega"));
        
        toReturn.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , Regiao_EntregaBean.class.getName () , "getByOID(long oid)");
    }
    return toReturn;
  }

public String getNM_Origem() {
	return NM_Origem;
}

public void setNM_Origem(String origem) {
	NM_Origem = origem;
}

public long getOID_Origem() {
	return oid_Origem;
}

public void setOID_Origem(long oid_Origem) {
	this.oid_Origem = oid_Origem;
}

public String getDM_Origem() {
	return DM_Origem;
}

public void setDM_Origem(String origem) {
	DM_Origem = origem;
}

public String getDM_Prazo() {
	return DM_Prazo;
}

public void setDM_Prazo(String prazo) {
	DM_Prazo = prazo;
}



}
