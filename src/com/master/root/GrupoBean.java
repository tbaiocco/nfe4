package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class GrupoBean 
{
	private String CD_Grupo;
	private String NM_Grupo;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	
	public GrupoBean() {} 
	
	public String getCD_Grupo() 
	{
		return CD_Grupo;
	}
	public void setCD_Grupo(String CD_Grupo) 
	{
		this.CD_Grupo = CD_Grupo;
	}
	public String getNM_Grupo() 
	{
		return NM_Grupo;
	}
	public void setNM_Grupo(String NM_Grupo) 
	{
		this.NM_Grupo = NM_Grupo;
	}
	/* 
	 *---------------- Bloco Padr�o para Todas Classes ------------------
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
		 * Abre a conex�o com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conex�o ao gerenciador do driver
			// passando como par�metro o NM_Grupo do DSN 
			// o NM_Grupo de usu�rio e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}		
		/*
		 * Gera um novo c�digo (OID)
		 */
		try 
		{
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(
				"SELECT MAX(OID_Grupo) FROM Grupos");
			
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
		buff.append("INSERT INTO Grupos (OID_Grupo, CD_Grupo, NM_Grupo, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
		buff.append("VALUES (?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setString(2, getCD_Grupo());
			pstmt.setString(3, getNM_Grupo());
			pstmt.setString(4, getDt_Stamp());
			pstmt.setString(5, getUsuario_Stamp());
			pstmt.setString(6, getDm_Stamp());
			pstmt.executeUpdate();
		} catch (Exception e) 
		{
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
		/*
		 * Faz o commit e fecha a conex�o.
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
		 * Abre a conex�o com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conex�o ao gerenciador do driver
			// passando como par�metro o NM_Grupo do DSN 
			// o NM_Grupo de usu�rio e a senha do banco.
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
		buff.append("UPDATE Grupos SET NM_Grupo=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Grupo=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Grupo());
			pstmt.setString(2, getDt_Stamp());
			pstmt.setString(3, getUsuario_Stamp());
			pstmt.setString(4, getDm_Stamp());
			pstmt.setInt(5, getOID());
			pstmt.executeUpdate();
		} catch (Exception e) 
		{
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
		/*
		 * Faz o commit e fecha a conex�o.
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
		 * Abre a conex�o com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conex�o ao gerenciador do driver
			// passando como par�metro o NM_Grupo do DSN 
			// o NM_Grupo de usu�rio e a senha do banco.
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
		buff.append("DELETE FROM Grupos ");
		buff.append("WHERE OID_Grupo=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.executeUpdate();
		} catch (Exception e) 
		{
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
		/*
		 * Faz o commit e fecha a conex�o.
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
	
	public static final GrupoBean getByOID(int oid) 
		throws Exception 
	{
		/*
		 * Abre a conex�o com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conex�o ao gerenciador do driver
			// passando como par�metro o NM_Grupo do DSN 
			// o NM_Grupo de usu�rio e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		GrupoBean p = new GrupoBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Grupo, ");
			buff.append("	CD_Grupo, ");
			buff.append("	NM_Grupo ");
			buff.append("FROM Grupos ");
			buff.append("WHERE OID_Grupo= ");
			buff.append(oid);
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Grupo(cursor.getString(2));
				p.setNM_Grupo(cursor.getString(3));				
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
	
	public static final GrupoBean getByCD_Grupo(String CD_Grupo) 
		throws Exception 
	{
		/*
		 * Abre a conex�o com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conex�o ao gerenciador do driver
			// passando como par�metro o NM_Grupo do DSN 
			// o NM_Grupo de usu�rio e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		GrupoBean p = new GrupoBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Grupo, ");
			buff.append("	CD_Grupo, ");
			buff.append("	NM_Grupo ");
			buff.append("FROM Grupos ");
			buff.append("WHERE CD_Grupo= '");
			buff.append(CD_Grupo);
			buff.append("'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Grupo(cursor.getString(2));
				p.setNM_Grupo(cursor.getString(3));
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
	
	public static final List getByNM_Grupo(String NM_Grupo) 
		throws Exception 
	{
		/*
		 * Abre a conex�o com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conex�o ao gerenciador do driver
			// passando como par�metro o NM_Grupo do DSN 
			// o NM_Grupo de usu�rio e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Grupos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Grupo, ");
			buff.append("	CD_Grupo, ");
			buff.append("	NM_Grupo ");
			buff.append("FROM Grupos ");
			buff.append("WHERE NM_Grupo LIKE'");
			buff.append(NM_Grupo);
			buff.append("%'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				GrupoBean p = new GrupoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Grupo(cursor.getString(2));
				p.setNM_Grupo(cursor.getString(3));
				Grupos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Grupos_Lista;
	}
	
	public static final List getAll()
		throws Exception 
	{
		Connection conn = null;
		try 
		{
			// Pede uma conex�o ao gerenciador do driver
			// passando como par�metro o nome do DSN 
			// o nome de usu�rio e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Grupos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Grupo, ");
			buff.append("	CD_Grupo, ");
			buff.append("	NM_Grupo ");
			buff.append("FROM Grupos");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				GrupoBean p = new GrupoBean(); 
				p.setOID(cursor.getInt(1));
				p.setCD_Grupo(cursor.getString(2));
				p.setNM_Grupo(cursor.getString(3));
				Grupos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Grupos_Lista;
	}	
	
	public static void main(String args[]) 
		throws Exception 
	{
		GrupoBean pp = new GrupoBean();
		pp.setOID(2);
		pp.setCD_Grupo("LLL");
		pp.setNM_Grupo("Litro");
		pp.insert();
		
		GrupoBean p = getByOID(2);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Grupo());
		// //// System.out.println(p.getNM_Grupo());
		
		
		
	}
}


