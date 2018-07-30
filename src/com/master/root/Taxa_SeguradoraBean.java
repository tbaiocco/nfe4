package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.master.util.JavaUtil;

import auth.OracleConnection2;

public class Taxa_SeguradoraBean
{
	private String DM_Origem;
	private String DM_Destino;
	private long oid_Taxa_Seguro;
	private long oid_Origem;
	private long oid_Destino;
	private long oid_Seguradora;
	private double PC_RCTRC;
	private double PC_Desconto_RCTRC;
	private double PC_RCTR_VI;
	private double PC_RCTR_DC;
	
	private String CD_Seguradora;
	private String CD_Origem;
	private String CD_Destino;
	
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;

	public Taxa_SeguradoraBean() {}

	
	
    /**
     * @return Returns the dM_Destino.
     */
    public String getDM_Destino() {
        return DM_Destino;
    }
    /**
     * @param destino The dM_Destino to set.
     */
    public void setDM_Destino(String destino) {
        DM_Destino = destino;
    }
    /**
     * @return Returns the dM_Origem.
     */
    public String getDM_Origem() {
        return DM_Origem;
    }
    /**
     * @param origem The dM_Origem to set.
     */
    public void setDM_Origem(String origem) {
        DM_Origem = origem;
    }
    /**
     * @return Returns the oid_Destino.
     */
    public long getOid_Destino() {
        return oid_Destino;
    }
    /**
     * @param oid_Destino The oid_Destino to set.
     */
    public void setOid_Destino(long oid_Destino) {
        this.oid_Destino = oid_Destino;
    }
    /**
     * @return Returns the oid_Origem.
     */
    public long getOid_Origem() {
        return oid_Origem;
    }
    /**
     * @param oid_Origem The oid_Origem to set.
     */
    public void setOid_Origem(long oid_Origem) {
        this.oid_Origem = oid_Origem;
    }
    /**
     * @return Returns the oid_Seguradora.
     */
    public long getOid_Seguradora() {
        return oid_Seguradora;
    }
    /**
     * @param oid_Seguradora The oid_Seguradora to set.
     */
    public void setOid_Seguradora(long oid_Seguradora) {
        this.oid_Seguradora = oid_Seguradora;
    }
    /**
     * @return Returns the oid_Taxa_Seguro.
     */
    public long getOid_Taxa_Seguro() {
        return oid_Taxa_Seguro;
    }
    /**
     * @param oid_Taxa_Seguro The oid_Taxa_Seguro to set.
     */
    public void setOid_Taxa_Seguro(long oid_Taxa_Seguro) {
        this.oid_Taxa_Seguro = oid_Taxa_Seguro;
    }
    /**
     * @return Returns the pC_Desconto_RCTRC.
     */
    public double getPC_Desconto_RCTRC() {
        return PC_Desconto_RCTRC;
    }
    /**
     * @param desconto_RCTRC The pC_Desconto_RCTRC to set.
     */
    public void setPC_Desconto_RCTRC(double desconto_RCTRC) {
        PC_Desconto_RCTRC = desconto_RCTRC;
    }
    /**
     * @return Returns the pC_RCTR_DC.
     */
    public double getPC_RCTR_DC() {
        return PC_RCTR_DC;
    }
    /**
     * @param pc_rctr_dc The pC_RCTR_DC to set.
     */
    public void setPC_RCTR_DC(double pc_rctr_dc) {
        PC_RCTR_DC = pc_rctr_dc;
    }
    /**
     * @return Returns the pC_RCTR_VI.
     */
    public double getPC_RCTR_VI() {
        return PC_RCTR_VI;
    }
    /**
     * @param pc_rctr_vi The pC_RCTR_VI to set.
     */
    public void setPC_RCTR_VI(double pc_rctr_vi) {
        PC_RCTR_VI = pc_rctr_vi;
    }
    /**
     * @return Returns the pC_RCTRC.
     */
    public double getPC_RCTRC() {
        return PC_RCTRC;
    }
    /**
     * @param pc_rctrc The pC_RCTRC to set.
     */
    public void setPC_RCTRC(double pc_rctrc) {
        PC_RCTRC = pc_rctrc;
    }
        
    /**
     * @return Returns the cD_Destino.
     */
    public String getCD_Destino() {
        return CD_Destino;
    }
    /**
     * @param destino The cD_Destino to set.
     */
    public void setCD_Destino(String destino) {
        CD_Destino = destino;
    }
    /**
     * @return Returns the cD_Origem.
     */
    public String getCD_Origem() {
        return CD_Origem;
    }
    /**
     * @param origem The cD_Origem to set.
     */
    public void setCD_Origem(String origem) {
        CD_Origem = origem;
    }
    /**
     * @return Returns the cD_Seguradora.
     */
    public String getCD_Seguradora() {
        return CD_Seguradora;
    }
    /**
     * @param seguradora The cD_Seguradora to set.
     */
    public void setCD_Seguradora(String seguradora) {
        CD_Seguradora = seguradora;
    }
	/*
	 *---------------- Bloco Padrão para Todas Classes ------------------
	 */
    
	public String getUsuario_Stamp()
	{
		return Usuario_Stamp;
	}
	public void setUsuario_Stamp(String Usuario_Stamp)
	{
		this.Usuario_Stamp = Usuario_Stamp;
	}
	public String getDt_Stamp()
	{
		return Dt_Stamp;
	}
	public void setDt_Stamp(String Dt_Stamp)
	{
		this.Dt_Stamp = Dt_Stamp;
	}
	public String getDm_Stamp()
	{
		return Dm_Stamp;
	}
	public void setDm_Stamp(String Dm_Stamp)
	{
		this.Dm_Stamp = Dm_Stamp;
	}
	public int getOID()
	{
		return oid;
	}
	public void setOID(int n)
	{
		this.oid = n;
	}

	public void insert() throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Seguradora do DSN
			// o NM_Seguradora de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		/*
		 * Gera um novo código (OID)
		 */
		try
		{
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(
				"SELECT MAX(OID_taxa_Seguro) FROM taxas_Seguros");

			while (cursor.next())
			{
				int oid = cursor.getInt(1);
				setOID(oid + 1);
			}
			cursor.close();
			stmt.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		/*
		 * Define o insert.
		 */
		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO taxas_Seguros (OID_taxa_Seguro, " +
				    "oid_Seguradora, DM_Origem, oid_Origem, " +
				    "DM_Destino, oid_Destino, pc_rctrc, pc_desconto_rctrc, " +
				    "pc_rctr_vi, pc_rctr_dc) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setLong(2, getOid_Seguradora());
			pstmt.setString(3, "E");
			pstmt.setLong(4, getOid_Origem());
			pstmt.setString(5, "P");
			pstmt.setLong(6, getOid_Destino());
			pstmt.setDouble(7, getPC_RCTRC());
			pstmt.setDouble(8, getPC_Desconto_RCTRC());
			pstmt.setDouble(9, getPC_RCTR_VI());
			pstmt.setDouble(10, getPC_RCTR_DC());
			pstmt.executeUpdate();
		} catch (Exception e)
		{
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
		/*
		 * Faz o commit e fecha a conexão.
		 */
		try
		{
			conn.commit();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	public void update() throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Seguradora do DSN
			// o NM_Seguradora de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		/*
		 * Define o update.
		 */
		StringBuffer buff = new StringBuffer();
		buff.append("UPDATE taxas_Seguros set " +
				    "oid_Seguradora=?, oid_Origem=?, " +
				    "oid_Destino=?, pc_rctrc=?, pc_desconto_rctrc=?, " +
				    "pc_rctr_vi=?, pc_rctr_dc=? ");
		buff.append("WHERE OID_taxa_Seguro=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setLong(1, getOid_Seguradora());
			pstmt.setLong(2, getOid_Origem());
			pstmt.setLong(3, getOid_Destino());
			pstmt.setDouble(4, getPC_RCTRC());
			pstmt.setDouble(5, getPC_Desconto_RCTRC());
			pstmt.setDouble(6, getPC_RCTR_VI());
			pstmt.setDouble(7, getPC_RCTR_DC());
			pstmt.setLong(8, getOid_Taxa_Seguro());
			pstmt.executeUpdate();
		} catch (Exception e)
		{
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
		/*
		 * Faz o commit e fecha a conexão.
		 */
		try
		{
			conn.commit();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	public void delete() throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Seguradora do DSN
			// o NM_Seguradora de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		/*
		 * Define o DELETE.
		 */
		StringBuffer buff = new StringBuffer();
		buff.append("DELETE FROM taxas_Seguros ");
		buff.append("WHERE OID_taxa_Seguro=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setLong(1, getOid_Taxa_Seguro());
			pstmt.executeUpdate();
		} catch (Exception e)
		{
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
		/*
		 * Faz o commit e fecha a conexão.
		 */
		try
		{
			conn.commit();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	public static final Taxa_SeguradoraBean getByOID(long oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Seguradora do DSN
			// o NM_Seguradora de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Taxa_SeguradoraBean p = new Taxa_SeguradoraBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Seguradora, ");
			buff.append("	oid_Origem, ");
			buff.append("	oid_Destino, ");
			buff.append("	pc_rctrc, ");
			buff.append("	pc_desconto_rctrc, ");
			buff.append("	pc_rctr_vi, ");
			buff.append("	pc_rctr_dc, ");
			buff.append("	OID_taxa_Seguro ");
			buff.append("FROM taxas_Seguros ");
			buff.append("WHERE OID_taxa_Seguro= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOid_Seguradora(cursor.getLong(1));
				p.setOid_Origem(cursor.getLong(2));
				p.setOid_Destino(cursor.getLong(3));
				p.setPC_RCTRC(cursor.getDouble(4));
				p.setPC_Desconto_RCTRC(cursor.getDouble(5));
				p.setPC_RCTR_VI(cursor.getDouble(6));
				p.setPC_RCTR_DC(cursor.getDouble(7));
				p.setOid_Taxa_Seguro(cursor.getLong(8));
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return p;
	}

	public static final List Lista(String origem, String destino, String seguradora)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Seguradora do DSN
			// o NM_Seguradora de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Taxas_Seguros_Lista = new ArrayList();
		try
		{
		    StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Seguradora, ");
			buff.append("	oid_Origem, ");
			buff.append("	oid_Destino, ");
			buff.append("	pc_rctrc, ");
			buff.append("	pc_desconto_rctrc, ");
			buff.append("	pc_rctr_vi, ");
			buff.append("	pc_rctr_dc, ");
			buff.append("	OID_taxa_Seguro ");
			buff.append("FROM taxas_Seguros ");
			buff.append("WHERE 1=1 ");
			if (JavaUtil.doValida(origem)){
			    buff.append(" AND oid_origem="+origem);
			}
			if (JavaUtil.doValida(destino)){
			    buff.append(" AND oid_destino="+destino);
			}
			if (JavaUtil.doValida(seguradora)){
			    buff.append(" AND oid_seguradora="+seguradora);
			}

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
			    Taxa_SeguradoraBean p = new Taxa_SeguradoraBean();
				p.setOid_Seguradora(cursor.getLong(1));
				p.setOid_Origem(cursor.getLong(2));
				p.setOid_Destino(cursor.getLong(3));
				p.setPC_RCTRC(cursor.getDouble(4));
				p.setPC_Desconto_RCTRC(cursor.getDouble(5));
				p.setPC_RCTR_VI(cursor.getDouble(6));
				p.setPC_RCTR_DC(cursor.getDouble(7));
				p.setOid_Taxa_Seguro(cursor.getLong(8));
				
				SeguradoraBean s = SeguradoraBean.getByOID(cursor.getInt(1));
				p.setCD_Seguradora(s.getCD_Seguradora());
				EstadoBean e = EstadoBean.getByOID(cursor.getInt(2));
				p.setCD_Origem(e.getCD_Estado());
				PaisBean d = PaisBean.getByOID(cursor.getInt(3));
				p.setCD_Destino(d.getCD_Pais());
				
				Taxas_Seguros_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Taxas_Seguros_Lista;
	}

	public static final List getAll()
		throws Exception
	{
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o nome do DSN
			// o nome de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Taxas_Seguros_Lista = new ArrayList();
		try
		{
		    StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Seguradora, ");
			buff.append("	oid_Origem, ");
			buff.append("	oid_Destino, ");
			buff.append("	pc_rctrc, ");
			buff.append("	pc_desconto_rctrc, ");
			buff.append("	pc_rctr_vi, ");
			buff.append("	pc_rctr_dc, ");
			buff.append("	OID_taxa_Seguro ");
			buff.append("FROM taxas_Seguros ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
			    Taxa_SeguradoraBean p = new Taxa_SeguradoraBean();
				p.setOid_Seguradora(cursor.getLong(1));
				p.setOid_Origem(cursor.getLong(2));
				p.setOid_Destino(cursor.getLong(3));
				p.setPC_RCTRC(cursor.getDouble(4));
				p.setPC_Desconto_RCTRC(cursor.getDouble(5));
				p.setPC_RCTR_VI(cursor.getDouble(6));
				p.setPC_RCTR_DC(cursor.getDouble(7));
				p.setOid_Taxa_Seguro(cursor.getLong(8));
				
				SeguradoraBean s = SeguradoraBean.getByOID(cursor.getInt(1));
				p.setCD_Seguradora(s.getCD_Seguradora());
				EstadoBean e = EstadoBean.getByOID(cursor.getInt(2));
				p.setCD_Origem(e.getCD_Estado());
				PaisBean d = PaisBean.getByOID(cursor.getInt(3));
				p.setCD_Destino(d.getCD_Pais());
				
				Taxas_Seguros_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Taxas_Seguros_Lista;
	}
	
	public static final Taxa_SeguradoraBean getPC_to_CRT(String origem, String destino, String seguradora)
	throws Exception
{
	/*
	 * Abre a conexão com o banco
	 */
	Connection conn = null;
	try
	{
		// Pede uma conexão ao gerenciador do driver
		// passando como parâmetro o NM_Seguradora do DSN
		// o NM_Seguradora de usuário e a senha do banco.
		conn = OracleConnection2.getWEB();
		conn.setAutoCommit(false);
	} catch (Exception e)
	{
		e.printStackTrace();
		throw e;
	}

	Taxa_SeguradoraBean p = new Taxa_SeguradoraBean();
	try
	{
	    StringBuffer buff = new StringBuffer();
		buff.append("SELECT OID_Seguradora, ");
		buff.append("	oid_Origem, ");
		buff.append("	oid_Destino, ");
		buff.append("	pc_rctrc, ");
		buff.append("	pc_desconto_rctrc, ");
		buff.append("	pc_rctr_vi, ");
		buff.append("	pc_rctr_dc, ");
		buff.append("	OID_taxa_Seguro ");
		buff.append("FROM taxas_Seguros ");
		buff.append("WHERE 1=1 ");
		if (JavaUtil.doValida(origem)){
		    buff.append(" AND oid_origem="+origem);
		}
		if (JavaUtil.doValida(destino)){
		    buff.append(" AND oid_destino="+destino);
		}
		if (JavaUtil.doValida(seguradora)){
		    buff.append(" AND oid_seguradora="+seguradora);
		}
// System.out.println(buff.toString());
		Statement stmt = conn.createStatement();
		ResultSet cursor =
			stmt.executeQuery(buff.toString());

		while (cursor.next())
		{
			p.setOid_Seguradora(cursor.getLong(1));
			p.setOid_Origem(cursor.getLong(2));
			p.setOid_Destino(cursor.getLong(3));
			p.setPC_RCTRC(cursor.getDouble(4));
			p.setPC_Desconto_RCTRC(cursor.getDouble(5));
			p.setPC_RCTR_VI(cursor.getDouble(6));
			p.setPC_RCTR_DC(cursor.getDouble(7));
			p.setOid_Taxa_Seguro(cursor.getLong(8));
			
			SeguradoraBean s = SeguradoraBean.getByOID(cursor.getInt(1));
			p.setCD_Seguradora(s.getCD_Seguradora());
			EstadoBean e = EstadoBean.getByOID(cursor.getInt(2));
			p.setCD_Origem(e.getCD_Estado());
			PaisBean d = PaisBean.getByOID(cursor.getInt(3));
			p.setCD_Destino(d.getCD_Pais());
			
		}
		cursor.close();
		stmt.close();
		conn.close();
	} catch (Exception e)
	{
		e.printStackTrace();
	}
	return p;
}

	public static void main(String args[])
		throws Exception
	{
		
	}

}