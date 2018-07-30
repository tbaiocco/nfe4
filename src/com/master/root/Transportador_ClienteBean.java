package com.master.root;

import java.sql.*;
import java.util.*;

import auth.*;
import com.master.ed.*;
import com.master.util.*;

public class Transportador_ClienteBean extends MasterED {
  /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String DM_Destino;
	private String NM_Destino;
	private String DM_Origem;
	private String NM_Origem;
	private long oid;
	private long oid_Origem;
	private long oid_Destino;

	private String oid_Cliente;
	private String NM_Razao_Social_Cliente;
	private String oid_Transportador;
	private String NM_Razao_Social_Transportador;

	//nao usados
	private long OID_Modal;
	private String NM_Modal;
	private String CD_Modal;
	private long NR_Prazo_Entrega;
	private String DM_Prazo;


  public void insert () throws Exception {
    Connection conn = OracleConnection2.getWEB ();
    conn.setAutoCommit (false);


    String xx = String.valueOf (System.currentTimeMillis ()).toString ().substring (6 , 13);
    setOid(new Long (xx).longValue ());

    try {
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery ("SELECT oid_Transportador_Cliente FROM Transportadores_Clientes WHERE oid_Transportador_Cliente=" + getOid());
      if (cursor.next ()) {
        cursor = stmt.executeQuery ("SELECT OID_Transportador_Cliente FROM Transportadores_Clientes order by OID_Transportador_Cliente desc limit 1");
        while (cursor.next ()) {
          long oid = cursor.getLong (1);
          setOid(oid + 1);
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
        " INSERT INTO Transportadores_Clientes" +
        "   (OID_Transportador_Cliente, " +
        "    OID_Cliente, " +
        "    OID_Transportador, " +
        "    OID_Origem, " +
        "    OID_Destino, " +
        "    DM_Origem, " +
        "    NM_Origem, " +
        "    DM_Destino, " +
        "    NM_Destino) " +
        " VALUES (" + getOid() +
        "        ,'" + getOid_Cliente() + "'" +
        "        ,'" + getOid_Transportador() + "'" +
        "        ," + getOid_Origem() +
        "        ," + getOid_Destino() +
        "        ,'" + getDM_Origem () + "'" +
        "        ,'" + getNM_Origem () + "'" +
        "        ,'" + getDM_Destino () + "'" +
        "        ,'" + getNM_Destino () + "')";
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
        " UPDATE Transportadores_Clientes" +
        "    SET OID_Cliente='" + getOid_Cliente() + "'" +
        "    ,OID_Origem=" + getOid_Origem() +
        "    ,DM_Origem='" + getDM_Origem () + "'" +
        "    ,NM_Origem='" + getNM_Origem () + "'" +
        "    ,OID_Transportador='" + getOid_Transportador() + "'" +
        "    ,OID_Destino=" + getOid_Destino() +
        "    ,DM_Destino='" + getDM_Destino () + "'" +
        "    ,NM_Destino='" + getNM_Destino () + "'" +
        " WHERE OID_Transportador_Cliente=" + getOid();
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
      // passando como parâmetro o NM_Transportador_Cliente do DSN
      // o NM_Transportador_Cliente de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Transportadores_Clientes ");
    buff.append ("WHERE OID_Transportador_Cliente=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setLong (1 , getOid());
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


  public static final Transportador_ClienteBean getByOID (long oid) throws Exception {
    if (oid > 0) {
      Transportador_ClienteBean filtro = new Transportador_ClienteBean ();
      filtro.setOid(oid);
      List lista = getByRecord(filtro);
      Iterator iterator = lista.iterator ();
      if (iterator.hasNext ()) {
        return (Transportador_ClienteBean) iterator.next ();
      }
      else {
        return new Transportador_ClienteBean ();
      }
    }
    else {
      return new Transportador_ClienteBean ();
    }
  }

  public static final List getAll () throws Exception {
    Transportador_ClienteBean filtro = new Transportador_ClienteBean ();
    return getByRecord(filtro);
  }

  public static final List getByNR_CNPJ_CPF_Lista (String NR_CNPJ_CPF) throws Exception {
      Transportador_ClienteBean filtro = new Transportador_ClienteBean ();
      filtro.setOid_Cliente(NR_CNPJ_CPF);
      return getByRecord(filtro);
  }

  public static final List getByNR_CNPJ_CPF_Lista (String NR_CNPJ_CPF , String NR_CNPJ_CPF_Transportador, int oid_Destino) throws Exception {
	  Transportador_ClienteBean filtro = new Transportador_ClienteBean ();
	  if (JavaUtil.doValida(NR_CNPJ_CPF)) {
	      filtro.setOid_Cliente(NR_CNPJ_CPF);
	  }
	  if (JavaUtil.doValida(NR_CNPJ_CPF_Transportador)) {
	      filtro.setOid_Transportador(NR_CNPJ_CPF_Transportador);
	  }
	  if (JavaUtil.doValida(String.valueOf(oid_Destino))) {
		  filtro.setOid_Destino(oid_Destino);
	  }
	  return getByRecord (filtro);

  }

  public static final List getByNR_CNPJ_CPF_Lista (String NR_CNPJ_CPF, String NR_CNPJ_CPF_Transportador, int oid_Origem, int oid_Destino) throws Exception {
	  Transportador_ClienteBean filtro = new Transportador_ClienteBean ();
	  if (JavaUtil.doValida(NR_CNPJ_CPF)) {
	      filtro.setOid_Cliente(NR_CNPJ_CPF);
	  }
	  if (JavaUtil.doValida(NR_CNPJ_CPF_Transportador)) {
	      filtro.setOid_Transportador(NR_CNPJ_CPF_Transportador);
	  }
	  if (JavaUtil.doValida(String.valueOf(oid_Origem))) {
		  filtro.setOid_Origem(oid_Origem);
	  }
	  if (JavaUtil.doValida(String.valueOf(oid_Destino))) {
		  filtro.setOid_Destino(oid_Destino);
	  }
	  return getByRecord (filtro);

  }

  public static final List getByRecord (Transportador_ClienteBean filtro) throws Excecoes {
    Connection conn;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , Transportador_ClienteBean.class.getName () , "getByRecord(Transportador_ClienteBean filtro)");
    }
    List toReturn = new ArrayList ();
    Transportador_ClienteBean p;
    try {
      String sql =
          " SELECT Transportadores_Clientes.OID_Transportador_Cliente " +
          "       ,Transportadores_Clientes.OID_Cliente " +
          "       ,Transportadores_Clientes.OID_Transportador " +
          "       ,Pessoa_Cliente.NM_Razao_Social" +
          "       ,Pessoa_Transportador.NM_Razao_Social as nm_razao_social_transportador" +
          "       ,Transportadores_Clientes.OID_Origem " +
          "       ,Transportadores_Clientes.NM_Origem " +
          "       ,Transportadores_Clientes.DM_Origem " +
          "       ,Transportadores_Clientes.OID_Destino " +
          "       ,Transportadores_Clientes.NM_Destino " +
          "       ,Transportadores_Clientes.DM_Destino " +
          " FROM  Transportadores_Clientes " +
          "		  left join Pessoas as Pessoa_Cliente " +
          "        on Transportadores_Clientes.OID_Cliente = Pessoa_Cliente.OID_Pessoa " +
          "		  left join Pessoas as Pessoa_Transportador " +
          "        on Transportadores_Clientes.OID_Transportador = Pessoa_Transportador.OID_Pessoa" +
          " WHERE 1=1 ";

      if (filtro.getOid() > 0) {
        sql += " AND Transportadores_Clientes.OID_Transportador_Cliente = " + filtro.getOid();
      }

      if (JavaUtil.doValida (filtro.getOid_Cliente())) {
        sql += " AND Transportadores_Clientes.OID_Cliente = '" + filtro.getOid_Cliente() + "'";
      }
      if (JavaUtil.doValida (filtro.getOid_Transportador())) {
          sql += " AND Transportadores_Clientes.OID_Transportador = '" + filtro.getOid_Transportador() + "'";
        }

      if (JavaUtil.doValida (filtro.getDM_Origem ())) {
          sql += " AND Transportadores_Clientes.DM_Origem = '" + filtro.getDM_Origem () + "'";
        }

      if (JavaUtil.doValida (filtro.getDM_Destino ())) {
          sql += " AND Transportadores_Clientes.DM_Destino = '" + filtro.getDM_Destino () + "'";
        }

      if (filtro.getOid_Origem() > 0) {
          sql += " AND Transportadores_Clientes.OID_Origem = " + filtro.getOid_Origem();
      }

      if (filtro.getOid_Destino() > 0) {
        sql += " AND Transportadores_Clientes.OID_Destino = " + filtro.getOid_Destino();
      }

      sql += " ORDER BY Pessoa_Transportador.NM_Razao_Social, Pessoa_Cliente.NM_Razao_Social, NM_DESTINO";

//System.out.println (sql);

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sql);
      while (cursor.next ()) {
        p = new Transportador_ClienteBean ();

        p.setOid(cursor.getLong("oid_Transportador_Cliente"));
        p.setOid_Cliente(cursor.getString("oid_cliente"));
    	p.setNM_Razao_Social_Cliente("----");
        if (cursor.getString("NM_Razao_Social")!=null) {
        	p.setNM_Razao_Social_Cliente(cursor.getString ("NM_Razao_Social"));
        }
        p.setOid_Transportador(cursor.getString("oid_transportador"));
        p.setNM_Razao_Social_Transportador("----");
        if (cursor.getString("NM_Razao_Social_Transportador")!=null) {
        	p.setNM_Razao_Social_Transportador(cursor.getString ("NM_Razao_Social_Transportador"));
        }
        p.setOid_Origem(cursor.getLong("oid_origem"));
        p.setNM_Origem(cursor.getString("nm_origem"));
        p.setDM_Origem(cursor.getString("dm_origem"));
        p.setOid_Destino(cursor.getLong("oid_destino"));
        p.setNM_Destino(cursor.getString("nm_destino"));
        p.setDM_Destino(cursor.getString("dm_destino"));

        // System.out.println(" Regiao Entrega getByRecord ->>" +cursor.getLong ("NR_Prazo_Entrega"));

        toReturn.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , Transportador_ClienteBean.class.getName () , "getByOID(long oid)");
    }
    return toReturn;
  }

	public String getCD_Modal() {
		return CD_Modal;
	}

	public void setCD_Modal(String modal) {
		CD_Modal = modal;
	}

	public String getDM_Destino() {
		return DM_Destino;
	}

	public void setDM_Destino(String destino) {
		DM_Destino = destino;
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

	public String getNM_Destino() {
		return NM_Destino;
	}

	public void setNM_Destino(String destino) {
		NM_Destino = destino;
	}

	public String getNM_Modal() {
		return NM_Modal;
	}

	public void setNM_Modal(String modal) {
		NM_Modal = modal;
	}

	public String getNM_Origem() {
		return NM_Origem;
	}

	public void setNM_Origem(String origem) {
		NM_Origem = origem;
	}

	public String getNM_Razao_Social_Cliente() {
		return NM_Razao_Social_Cliente;
	}

	public void setNM_Razao_Social_Cliente(String razao_Social_Cliente) {
		NM_Razao_Social_Cliente = razao_Social_Cliente;
	}

	public String getNM_Razao_Social_Transportador() {
		return NM_Razao_Social_Transportador;
	}

	public void setNM_Razao_Social_Transportador(String razao_Social_Transportador) {
		NM_Razao_Social_Transportador = razao_Social_Transportador;
	}

	public long getNR_Prazo_Entrega() {
		return NR_Prazo_Entrega;
	}

	public void setNR_Prazo_Entrega(long prazo_Entrega) {
		NR_Prazo_Entrega = prazo_Entrega;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getOid_Cliente() {
		return oid_Cliente;
	}

	public void setOid_Cliente(String oid_Cliente) {
		this.oid_Cliente = oid_Cliente;
	}

	public long getOid_Destino() {
		return oid_Destino;
	}

	public void setOid_Destino(long oid_Destino) {
		this.oid_Destino = oid_Destino;
	}

	public long getOID_Modal() {
		return OID_Modal;
	}

	public void setOID_Modal(long modal) {
		OID_Modal = modal;
	}

	public long getOid_Origem() {
		return oid_Origem;
	}

	public void setOid_Origem(long oid_Origem) {
		this.oid_Origem = oid_Origem;
	}

	public String getOid_Transportador() {
		return oid_Transportador;
	}

	public void setOid_Transportador(String oid_Transportador) {
		this.oid_Transportador = oid_Transportador;
	}

}
